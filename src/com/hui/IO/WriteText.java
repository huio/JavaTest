package com.hui.IO;

import java.io.*;

/**
 * Created by H on 2018/8/21.
 */
public class WriteText {
    public static void main(String[] args) throws IOException {
        File file = new File("WriteText.txt");
        String filePath = "WriteText.txt";

/*
        FileWriter fileWriter = new FileWriter(filePath);
        fileWriter.write("FileWriter");
        fileWriter.close();//不关闭就不会写进去
*/

/*
        PrintWriter printWriter = new PrintWriter(filePath);
        printWriter.write("PrintWriter write(String s)");
        printWriter.print("PrintWriter print(String s)");
        printWriter.close();//不关闭就不会写进去
*/

/*
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write("FileWriter fileWriter = new FileWriter(file);");
        bufferedWriter.newLine();
        bufferedWriter.write("BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);");
        bufferedWriter.append("append");
        bufferedWriter.newLine();
        bufferedWriter.close();
*/

/*
        FileReader fileReader = new FileReader(file);
        int c;
        while ((c = fileReader.read()) != -1) {
            System.out.println((char) c);//每行一个字符
        }
*/


/*
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(line);
        }
*/

    }
}
