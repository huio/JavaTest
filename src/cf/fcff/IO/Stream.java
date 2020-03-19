package cf.fcff.IO;

import java.io.*;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;

/**
 * Created by H on 2018/8/21.
 */
public class Stream {
    public static void main(String[] args) {

        File file = new File("play.txt");
        FileInputStream fileInputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        try {
            fileInputStream = new FileInputStream(file);
//        FileReader fileReader = new FileReader(file);
            inputStreamReader = new InputStreamReader(fileInputStream);
            bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line+"\n");
            }
            System.out.println(sb.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
/*
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                inputStreamReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
*/
        }

/*
        //InputStream OutputStream 字节流
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(new byte[10]);//extends InputStream
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();//extends OutputStream


        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);//extends FilterOutputStream
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);//extends FilterInputStream

        File file = new File("");
        try {
            FileInputStream fileInputStream = new FileInputStream(file);//extends InputStream
            FileOutputStream fileOutputStream = new FileOutputStream(file);//extends OutputStream
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        //Reader Writer 字符流
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);//extends Reader
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);//extends Writer
        try {
            FileReader fileReader = new FileReader(file);//extends InputStreamReader
            FileWriter fileWriter = new FileWriter(file);//extends OutputStreamWriter
        } catch (IOException e) {
            e.printStackTrace();
        }

        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);//extends Reader
        BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);//extends Writer
*/
    }

    public byte[] file2bytes(File file) throws IOException {
        byte[] result = null;
        FileInputStream fileInputStream = new FileInputStream(file);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
        return inputStream2bytes(bufferedInputStream);
    }

    public byte[] inputStream2bytes(InputStream inputStream) throws IOException {
        byte[] bytes = new byte[1024];
        int len;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        while ((len = inputStream.read(bytes)) != -1) {
            byteArrayOutputStream.write(bytes, 0, len);
//            byteArrayOutputStream.flush();
        }
//        fileInputStream.close();
//        bufferedInputStream.close();
//        byteArrayOutputStream.close();
        return byteArrayOutputStream.toByteArray();
    }

    public void bytes2File(byte bytes[], File file) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(bytes);
        fileOutputStream.flush();
        fileOutputStream.close();
    }

    public void readTextFile(File file) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
//        FileReader fileReader = new FileReader(file);
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            sb.append(line);
        }
        inputStreamReader.close();
        fileInputStream.close();
        bufferedReader.close();
    }
}
