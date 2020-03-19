package cf.fcff;

import java.io.File;

public class FileUtil {
    public static void main(String[] args) {
        File file = new File("test");
        deleteFile(file);
    }

    public static void deleteFile(File file) {
        if (!file.exists()) {
            return;
        }
        if (file.isDirectory()) {
            for (File listFile : file.listFiles()) {
                deleteFile(listFile);
            }
            file.delete();
        } else {
            file.delete();
        }
    }

    public static void deleteFile(String path) {
        if (null == path) {
            return;
        }
        deleteFile(new File(path));
    }
}
