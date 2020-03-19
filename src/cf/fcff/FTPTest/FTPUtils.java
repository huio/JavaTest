package cf.fcff.FTPTest;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by H on 2018/9/6.
 */
public class FTPUtils {
    private String hostname;

    private int port = 21;

    private String username, password;

    public boolean upload(String serverPath, String filePath) {
        return upload(hostname, port, username, password, serverPath, filePath);
    }

    public boolean download(String serverPath, String filePath) {
        return download(hostname, port, username, password, serverPath, filePath);
    }

    public static FTPClient getFTPClient(String hostname, int port, String username, String password) {

        FTPClient ftp = new FTPClient();

        try {
            ftp.connect(hostname, port);
            System.out.println("Connected to " + hostname + ":" + port + ".");
            System.out.print(ftp.getReplyString());
            ftp.login(username, password);
            ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
            int replyCode = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(replyCode)) {
                ftp.disconnect();
                System.err.println("FTP server refused connection.");
//                System.exit(1);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ftp;
    }

    public static boolean upload(String hostname, int port, String username, String password, String serverPath, String filePath) {
        FTPClient ftp = new FTPClient();
        boolean isSuccess = true;
        try {
            ftp.connect(hostname, port);
            System.out.println("Connected to " + hostname + ":" + port + ".");
            System.out.print(ftp.getReplyString());
            ftp.login(username, password);
            ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
            int replyCode = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(replyCode)) {
                ftp.disconnect();
                System.err.println("FTP server refused connection.");
//                System.exit(1);
            }
            changeWorkingDirectoryCreateIfNotExist(ftp, serverPath);//若切换了工作目录则储存文件不需要接全路径
//            makeDirs(ftp, serverPath);//若不切换工作目录则需要，在储存问件前加上serverPath
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);
//            ftp.enterRemotePassiveMode();
            ftp.storeFile(file.getName(), fis);//makeDir则需要serverPath + "/" + file.getName()
            ftp.logout();
        } catch (IOException e) {
            isSuccess = false;
            e.printStackTrace();
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException ioe) {
                    // do nothing
                }
            }
            return isSuccess;
//            System.exit(error ? 1 : 0);
        }
    }

    public static boolean download(String hostname, int port, String username, String password, String serverPath, String filePath) {

        return false;
    }

    public static boolean changeWorkingDirectoryCreateIfNotExist(FTPClient ftp, String path) {
        if (ftp == null || path == null) return false;
        try {
            String[] paths = path.split("/");
            for (String p : paths) {//二分法切换
                if (!ftp.changeWorkingDirectory(p)) {
                    if (!ftp.makeDirectory(p)) return false;
                    if (!ftp.changeWorkingDirectory(p)) return false;
                }
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean isExist(String hostname, int port, String username, String password, String serverPath) {

        try {
            FTPClient ftpClient = getFTPClient(hostname, port, username, password);
            String fileName;
            if (serverPath.contains("/")) {
                ftpClient.changeWorkingDirectory(serverPath.substring(0, serverPath.lastIndexOf("/")));
                fileName = serverPath.substring(serverPath.lastIndexOf("/")+1);
            } else {
                fileName = serverPath;
            }
            FTPFile[] ftpFiles = ftpClient.listFiles();
            for (FTPFile ftpFile : ftpFiles) {
                if (ftpFile.getName().equals(fileName)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }


    /**
     * make directories,no matter the file is exist or not;
     *
     * @param ftp     FTPClient
     * @param dirPath directory path
     * @return
     */
    public static boolean makeDirs(FTPClient ftp, String dirPath) throws IOException {
        if (ftp == null || dirPath == null) return false;
        try {
            if (ftp.makeDirectory(dirPath)) {
                return true;
            }
            String[] paths = dirPath.split("/");
            String currentPath = ftp.printWorkingDirectory();
            for (String path : paths) {
                if (!ftp.changeWorkingDirectory(path)) {
                    if (!ftp.makeDirectory(path)) return false;
                    if (!ftp.changeWorkingDirectory(path)) return false;
                }
            }
            ftp.changeWorkingDirectory(currentPath);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException(e);
        }
    }


    private FTPUtils() {

    }

    public FTPUtils(String hostname, String username, String password) {
        this.hostname = hostname;
        this.username = username;
        this.password = password;
    }

    public FTPUtils(String hostname, int port, String username, String password) {
        this.hostname = hostname;
        this.port = port;
        this.username = username;
        this.password = password;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static void main(String[] args) {
//        FTPUtils ftpUtils = new FTPUtils();
//        ftpUtils.setHostname("192.168.2.3");
//        ftpUtils.upload("/", "WriteText.txt");
//        String hostname = "192.168.5.243";
//        String hostname = "192.168.2.131";
        String hostname = "192.168.5.8";
        int port = 21;
        String username = "ftp";
        String password = "password";

        String path1 = "ftp://192.168.5.8:2121/1/1.txt";
        String path2 = "192.168.5.8/1/2.txt";
        System.out.println(path2.split(":")[0]);
        System.out.println(path2.split(":")[1]);

/*
        FTPClient ftp = getFTPClient(hostname, port, username, password);
        try {
//            ftp.makeDirectory("/1/1.zip");
            FTPFile[] ftpFiles = ftp.listFiles();
            for (FTPFile ftpFile : ftpFiles) {
                System.out.println(ftpFile.getName());
            }
//            FileOutputStream fos = new FileOutputStream("data.zip");
//            ftp.retrieveFile("03FFD05054EACD948A3094F1BA57BCE9.zip", fos);
        } catch (IOException e) {
            e.printStackTrace();
        }
*/
//        boolean isSuccess = upload(hostname, port, username, password, "4/3/2/1", "WriteText.txt");

/*
        boolean isSuccess = isExist(hostname,port,username,password,"1/data.txt");
        if (isSuccess) {
            System.out.println("Success");
        } else {
            System.out.println("Fail");
        }
*/

/*
        try {
            String path = "newDir/newDir";
            if (!ftp.changeWorkingDirectory(path)) {
                if (makeDirs(ftp, path)) {
                    System.out.println("Success makeDirectory");
                    printArray(ftp.listNames());
                } else {
                    System.out.println("Fail makeDirectory");
                }
            } else {
                System.out.println("Fail changeWorkingDirectory");
                printArray(ftp.listNames());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
*/

    }

    public static void printArray(String[] names) {
        for (String name :
                names) {
            System.out.println(name);
        }
    }
}
