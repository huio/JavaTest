package cf.fcff.FTPTest;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.*;
import java.net.URL;

/**
 * FTP上传下载工具类--支持断点下载
 *
 * @author csl
 */
public class FtpContinueUtils {


    public static void main(String[] args) {
        try {
            FtpContinueUtils.downloadFile("ftpUser", "huihui", "ftp://192.168.2.3/iisstart.png", "./");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String ftpAddr = null;//FTP的地址端口
    private static String fileDir = null;//下载的服务器路径

    /**
     * Description: 向FTP服务器上传文件
     *
     * @param url        FTP服务器hostname
     * @param port       FTP服务器端口
     * @param username   FTP登录账号
     * @param password   FTP登录密码
     * @param serverPath FTP服务器保存目录
     * @param localPath  上传到FTP服务器上的文件名
     * @return 成功返回true，否则返回false
     * @throws Exception
     */
    public static void uploadFile(String url, int port,
                                  String username, String password,
                                  String serverPath, String localPath) throws Exception {
        FTPClient ftp = new FTPClient();
        try {
            ftp.connect(url, port);// 连接FTP服务器
            // 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
            ftp.login(username, password);// 登录
            ftp.setFileType(FTPClient.BINARY_FILE_TYPE);//设置文件类型，不然图片会模糊

            int reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
            }
            upload(serverPath, localPath, ftp);
            ftp.logout();
        } catch (IOException e) {
            throw e;
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException ioe) {
                }
            }
        }
    }

    /**
     * 将本地filename文件或者文件夹上传到服务器path
     *
     * @param path 服务器存储路径
     * @param ftp  ftp
     * @throws IOException
     * @throws FileNotFoundException
     */
    private static void upload(String path, String local, FTPClient ftp) throws IOException, FileNotFoundException {
        boolean b = ftp.changeWorkingDirectory(path);
        if (!b) {
            boolean c = ftp.makeDirectory(path);
            if (c) {
                ftp.changeWorkingDirectory(path);
            }
        }
        File file = new File(local);
        if (file.isDirectory()) {//文件夹
            ftp.makeDirectory(file.getName());
            ftp.changeWorkingDirectory(file.getName());
            String[] files = file.list();
            for (int i = 0; files != null && i < files.length; i++) {
                File file1 = new File(file.getPath() + File.separator + files[i]);
                if (file1.isDirectory()) {
                    upload(path + "/" + file.getName(), file1.getAbsolutePath(), ftp);
                } else {
                    File file2 = new File(file.getPath() + File.separator + files[i]);
                    FileInputStream input = new FileInputStream(file2);
//                    ftp.enterRemotePassiveMode();
//                    ftp.enterLocalActiveMode();
                    ftp.enterLocalPassiveMode();
                    ftp.storeFile(file2.getName(), input);
                    input.close();
                }
            }
            ftp.changeToParentDirectory();
        } else {//文件
            FileInputStream fis = new FileInputStream(file);
            ftp.storeFile(file.getName(), fis);
            fis.close();
        }
    }

    /**
     * ftp服务器下载文件(支持断点续传)
     *
     * @param serverPath 服务器端的路径 “/”表示home路径
     * @param localPath  存在本地的路径
     * @throws Exception 异常
     */
    public static boolean downloadFile(
            String username, String password, String serverPath, String localPath) throws Exception {
        boolean compDownFlag = false;
        FTPClient ftp = new FTPClient();
        try {
            if (handlerUrl(serverPath)) {
                URL url = new URL(ftpAddr);
                int port = url.getPort() < 1 ? 21 : url.getPort();
                ftp.connect(url.getHost(), port);//连接
                ftp.login(username, password);//登陆
                ftp.setDataTimeout(30);//设置数据传输时间为30秒
                ftp.setFileType(FTPClient.BINARY_FILE_TYPE);//设置文件类型，不然图片会模糊
                ftp.enterLocalPassiveMode(); //设置模式为被动模式，默认为主动模式
                compDownFlag = download(fileDir, localPath, ftp);
                ftp.logout();
            }

        } catch (Exception e) {
            throw e;
        } finally {
            if (ftp != null) {
                try {
                    ftp.disconnect();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return compDownFlag;
    }

    /**
     * 下载 将服务器serverPath路劲下的文件下载到localPath
     *
     * @param serverPath
     * @param localPath
     * @param ftp
     * @throws IOException
     * @throws FileNotFoundException
     */
    private static boolean download(String serverPath, String localPath, FTPClient ftp) throws IOException, FileNotFoundException {
        boolean compDownFlag = false;//完全下载
        boolean b = ftp.changeWorkingDirectory(serverPath);//切换到下载目录
        String filename = "";
        if (!b) {//不能切换，就直接下载该文件
            filename = serverPath.substring(serverPath.lastIndexOf("/") + 1, serverPath.length());
            serverPath = new String(serverPath.getBytes(), 0, serverPath.lastIndexOf("/") + 1);
            boolean c = ftp.changeWorkingDirectory(serverPath);
            if (!c) {
                return false;
            }
        }


        FTPFile[] files = ftp.listFiles();

        if (files != null && files.length > 0) {
            File file = new File(localPath);
            if (!file.exists()) {//本地目录
                file.mkdirs();
            }

            //单独下载一个文件
            if (!filename.equals("")) {
                for (FTPFile ftpFile : files) {
                    if (ftpFile.getName().endsWith(filename)) {
                        compDownFlag = downAFile(ftp, file, ftpFile);
                        return compDownFlag;
                    }
                }
            }


            for (FTPFile ftpFile : files) {

                if (filename != null && !filename.trim().equals("")) {
                    if (!ftpFile.getName().endsWith(filename)) {
                        continue;
                    }
                }

                if (ftpFile.getType() == FTPFile.FILE_TYPE) {//下载文件
                    compDownFlag = downAFile(ftp, file, ftpFile);
                } else if (ftpFile.getType() == FTPFile.DIRECTORY_TYPE) {//文件夹，递归下载
                    String server = serverPath + File.separator + ftpFile.getName();
                    String local = localPath + File.separator + ftpFile.getName();
                    File localFile = new File(local);
                    if (!localFile.exists()) {
                        localFile.mkdirs();
                    }

                    download(server, local, ftp);
                }
            }
        }
        ftp.changeToParentDirectory();
        return compDownFlag;
    }

    private static boolean handlerUrl(String ftpUrl) {
        boolean flag = false;
        if (ftpUrl != null && ftpUrl.length() > 0) {
            String[] urlArray = ftpUrl.split("ftp://");
            if (urlArray.length == 2) {
                String temp = urlArray[1];
                String addr = temp.substring(0, temp.indexOf("/"));
                fileDir = temp.substring(temp.indexOf("/"));
                ftpAddr = "ftp://" + addr;

                String[] tempArray = temp.split("/");
                if (tempArray.length > 0) {
                    flag = true;
                }
            }
        }
        return flag;
    }

    /**
     * 下载一个ftpFile
     *
     * @param ftp
     * @param file    parentFile
     * @param ftpFile ftpFile
     */
    private static boolean downAFile(FTPClient ftp, File file, FTPFile ftpFile)
            throws IOException {
        boolean compDownFlag = false;
        String name = ftpFile.getName();
        File f = new File(file, name);

        RandomAccessFile raf = new RandomAccessFile(f, "rw");
        long length = raf.length();
        long total = ftpFile.getSize();
        if (length < total) {
            raf.seek(length);
            ftp.setRestartOffset(length);
            InputStream in = ftp.retrieveFileStream(new String(name.getBytes("GBK"), "iso-8859-1"));

            byte[] buffer = new byte[1024];
            int len = 0;
            while ((in != null) && (len = in.read(buffer)) != -1) {
                raf.write(buffer, 0, len);
            }
            length = raf.length();
            if (total == length) {
                compDownFlag = true;
            }
            if (in != null) {
                in.close();
            }
            ftp.completePendingCommand();
        } else {//length>=total 文件已存在
            compDownFlag = true;
        }
        return compDownFlag;
    }

}
