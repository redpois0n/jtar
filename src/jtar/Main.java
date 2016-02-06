package jtar;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Main {

    public static void main(String[] args) throws Exception {
        File archive = new File("test.tar");

        TarInputStream tis = new TarInputStream(new FileInputStream(archive));

        int i = 0;
        for (Header h : tis.getHeaders()) {
            System.out.println(h.toString());

            byte[] b = new byte[h.getSize()];
            ((DataInputStream) tis.open(h)).readFully(b);
            tis.closeEntry();

            FileOutputStream fos = new FileOutputStream("test" + ++i);
            fos.write(b);
            fos.close();
        }
    }

}
