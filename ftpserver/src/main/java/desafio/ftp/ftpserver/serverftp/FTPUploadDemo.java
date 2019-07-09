package desafio.ftp.ftpserver.serverftp;

import org.apache.commons.net.ftp.FTPClient;

import java.io.IOException;
import java.io.InputStream;

public class FTPUploadDemo {
    public static void main(String[] args) {
        FTPClient client = new FTPClient();
        String filename = "test.txt";

        // Read the file from resources folder.
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try (InputStream is = classLoader.getResourceAsStream(filename)) {
            client.connect("ftp://localhost:2021");
            client.login("vinicius", "vinicius");

            // Store file to server
            client.storeFile(filename, is);
            client.logout();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                client.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
