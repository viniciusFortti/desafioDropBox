package desafio.ftp.ftpserver.service;

import org.apache.commons.net.ftp.FTPClient;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.SocketException;

public class FTPConnect {

    public static void main (String[] args) throws SocketException, IOException {



        FTPClient ftp = new FTPClient();
        ftp.connect( "ftp://localhost:8081" );


        FileInputStream arqEnviar =

                new FileInputStream("/test/serverftp/test.txt");

        if (ftp.storeFile ("test.txt", arqEnviar))

            System.out.println("Arquivo armazenado com sucesso!");

        else

            System.out.println ("Erro ao armazenar o arquivo.");



    }

}
