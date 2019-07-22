package desafio.ftp.ftpserver.v1.DTO;

import desafio.ftp.ftpserver.v1.model.Usuario;
import lombok.Data;
import org.apache.commons.net.ftp.FTPFile;

import java.util.List;

@Data
public class ArquivoDTO {
    private String name;
    private String size;
    private String date;


    public ArquivoDTO(FTPFile ftpFile) {
        this.setName(ftpFile.getName());
        this.setSize(ftpFile.getSize() + " kb");
        this.setDate(ftpFile.getRawListing());
    }
}
