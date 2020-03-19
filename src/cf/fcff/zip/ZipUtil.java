package cf.fcff.zip;


import java.io.*;
import java.util.List;

public class ZipUtil {
    /**
     * 解压,处理下载的zip工具包文件
     *
     * @param directory 要解压到的目录
     * @param zip       工具包文件
     * @throws Exception * 操作失败时抛出异常
     */
    public static String unzipFile(String directory, File zip) {
        String apk_name = null;
        ZipInputStream zis = null;
        try {
            zis = new ZipInputStream(new FileInputStream(zip));
            ZipEntry ze = zis.getNextEntry();
            while (ze != null) {
                String name = ze.getName();
                if (ze.isDirectory()) {//目录判断
                    File child = new File(directory + File.separator + name);
                    child.mkdirs();
                } else {
                    File child = new File(directory + File.separator + name);
                    FileOutputStream output = null;
                    try {
                        output = new FileOutputStream(child);
                        byte[] buffer = new byte[10240];
                        int bytesRead = 0;
                        while ((bytesRead = zis.read(buffer)) > 0) {
                            output.write(buffer, 0, bytesRead);
                        }
                        output.flush();
                        apk_name = name;
                    } catch (Exception e) {
                        System.out.println("解压zip子目录出错：" + e);
                    } finally {
                        if (output != null) {
                            output.close();
                        }
                    }
                }
                ze = zis.getNextEntry();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (zis != null) {
                try {
                    zis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return apk_name;
    }

    /**
     * 压缩文件
     *
     * @param filePath 待压缩的文件路径
     * @return 压缩后的文件
     */
    public static File zipFile(String filePath) {
        File target = null;
        File source = new File(filePath);
        if (source.exists()) {
            // 压缩文件名=源文件名.zip
            String zipName = source.getName() + ".zip";
            target = new File(source.getParent(), zipName);
            if (target.exists()) {
                target.delete(); // 删除旧的文件
            }
            FileOutputStream fos = null;
            ZipOutputStream zos = null;
            try {
                fos = new FileOutputStream(target);
                zos = new ZipOutputStream(new BufferedOutputStream(fos));
                // 添加对应的文件Entry
                addEntry("/", source, zos);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                if (zos != null) {
                    try {
                        zos.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (fos != null) {
                    try {
                        fos.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return target;
    }

    /**
     * 扫描添加文件Entry
     *
     * @param base   基路径
     * @param source 源文件
     * @param zos    Zip文件输出流
     * @throws IOException
     */
    private static void addEntry(String base, File source, ZipOutputStream zos) throws IOException {
        // 按目录分级，形如：/aaa/bbb.txt
        String entry = base + source.getName();
        if (source.isDirectory()) {
            for (File file : source.listFiles()) {
                // 递归列出目录下的所有文件，添加文件Entry
                addEntry(entry + "/", file, zos);
            }
        } else {
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                byte[] buffer = new byte[1024 * 10];
                fis = new FileInputStream(source);
                bis = new BufferedInputStream(fis, buffer.length);
                int read = 0;
                zos.putNextEntry(new ZipEntry(entry));
                while ((read = bis.read(buffer, 0, buffer.length)) != -1) {
                    zos.write(buffer, 0, read);
                }
                zos.closeEntry();
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * 压缩多个文件
     *
     * @param files   String[]
     * @param zipname 压缩文件的输出路径
     * @return file 不成功返回null
     */
    public static File zipFile(List<String> files, String zipname) {
        if (files == null || files.size() == 0) {
            return null;
        }
        File zipFile = new File(zipname);

        try {
            OutputStream os = new BufferedOutputStream(new FileOutputStream(zipFile));
            ZipOutputStream zos = new ZipOutputStream(os);
            byte[] buf = new byte[1024];
            int len;
            for (int i = 0; i < files.size(); i++) {
                File file = new File(files.get(i));
                if (!file.isFile()) continue;
                ZipEntry ze = new ZipEntry(file.getName());
                zos.putNextEntry(ze);
                BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
                while ((len = bis.read(buf)) > 0) {
                    zos.write(buf, 0, len);
                }
                zos.closeEntry();
            }
            zos.closeEntry();
            zos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return zipFile;
    }

    public static void main(String[] args) {
        File file = new File("458831bcbd268e873e1ce168affcd04c.zip");
        System.out.println(file.getAbsolutePath());

        String apk_name = unzipFile("./", file);
        System.out.println(apk_name);

    }
}
