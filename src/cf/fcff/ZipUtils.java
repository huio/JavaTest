/*
 * ************************************************************
 * 文件：ZipUtils.java  模块：app  项目：OneCard
 * 当前修改时间：2019年05月30日 16:41:11
 * 上次修改时间：2019年05月30日 16:41:11
 * 作者：H
 * https://fcff.cf
 * Copyright (c) 2019
 * ************************************************************
 */

package cf.fcff;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * @author H
 */
public class ZipUtils {
    /**
     *
     * @param filePath 待解压文件路径
     * @param path 解压路径
     * @return 是否成功
     */
    public static boolean unzip(String filePath, String path) {
        return unzip(filePath != null ? new File(filePath) : null, path);
    }

    /**
     * 解压文件
     * @param file 待解压的文件
     * @param path 解压路径
     * @return 是否成功
     */
    public static boolean unzip(File file, String path) {
        boolean isSuccess = false;
        File directory = new File(path);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        try {
            FileInputStream fis = new FileInputStream(file);
            ZipInputStream zis = new ZipInputStream(fis, Charset.forName("GBK"));
            ZipEntry zipEntry = zis.getNextEntry();
            while ((zipEntry) != null) {
                File child = new File(zipEntry.getName());
                if (zipEntry.isDirectory()) {
                    child.mkdirs();
                    continue;
                }
                FileOutputStream fos = new FileOutputStream(child);
                byte[] b = new byte[10240];
                int l;
                while ((l = zis.read(b)) != -1) {
                    fos.write(b, 0, l);
                }
                fos.flush();
                fos.close();
                zipEntry = zis.getNextEntry();
            }
            fis.close();
            isSuccess = true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isSuccess;
    }

    public static boolean zip(String filePath, String path) {
        return zip(filePath != null ? new File(filePath) : null, path);
    }

    /**
     *
     * @param file 待压缩文件
     * @param path 压缩文件保存路径
     * @return 是否成功
     */
    public static boolean zip(File file, String path) {
        boolean isSuccess = false;
        try {
            FileInputStream fis = new FileInputStream(file);
            FileOutputStream fos = new FileOutputStream(path + file.getName() + ".zip");
            ZipOutputStream zos = new ZipOutputStream(fos);
            byte[] b = new byte[10240];
            int l;
            while ((l = fis.read(b)) != -1) {
                zos.write(b, 0, l);
            }
            zos.flush();
            zos.close();
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isSuccess;
    }

    public static void main(String[] args) {
        long time = System.currentTimeMillis();
//        unzip("458831bcbd268e873e1ce168affcd04c.zip", "");
//        zip("bytes", "");
/*
        try {
            ZipFile zipFile = new ZipFile("lingala.zip");
            ZipParameters zipParameters = new ZipParameters();
            zipFile.addFolder("lib",zipParameters);
//            zipFile.createZipFileFromFolder("lib",zipParameters,false,0);
        } catch (ZipException e) {
            e.printStackTrace();
        }
*/

/*
        if (isSuccess) {
            System.out.println("删除成功");
        }
*/
        System.out.println("耗时："+(System.currentTimeMillis()-time)+"ms");
    }
}
