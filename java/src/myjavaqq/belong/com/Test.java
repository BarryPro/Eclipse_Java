package myjavaqq.belong.com;

import java.io.File;

public class Test {

    public static void main(String[] args) {
        File file=new File("D:\\JavaFile\\666");
        GZipFiles.GZip(file);
        GZipFiles.shutdown();
    }
}