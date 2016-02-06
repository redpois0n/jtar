package jtar;

import java.io.File;
import java.io.FileInputStream;

public class Main {

    public static void main(String[] args) throws Exception {
        File archive = new File("test.tar");

        TarInputStream tis = new TarInputStream(new FileInputStream(archive));

        for (Header h : tis.getHeaders()) {
            System.out.println(h.toString());
        }
    }

}
