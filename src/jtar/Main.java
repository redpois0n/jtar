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

                System.out.println("Processing " + h.getName());
                System.out.println(h.toString());

                FileOutputStream fos = new FileOutputStream(h.getName());

                byte[] b = new byte[512];

                int read;
                int total = 0;

                while ((read = dis.read(b)) != -1 && total + read < h.getSize()) {
                    total += read;

                    fos.write(b, 0, 512);
                }

                fos.close();
            } catch (Exception ex) {
                ex.printStackTrace();
                break;
            }
        }

        dis.close();
    }

}
