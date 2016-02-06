package jtar;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Main {

    public static void main(String[] args) throws Exception {
        File archive = new File("test.tar");

        DataInputStream dis = new DataInputStream(new FileInputStream(archive));

        while (true) {
            try {
                Header h = new Header();
                h.readRecord(dis);

                System.out.println(h.toString());

                byte[] b = new byte[h.getSize()];
                dis.readFully(b);

                System.out.println("Writing " + h.getName());
                FileOutputStream fos = new FileOutputStream(h.getName());
                fos.write(b);
                fos.close();
            } catch (Exception ex) {
                ex.printStackTrace();
                break;
            }
        }

        dis.close();
    }

}
