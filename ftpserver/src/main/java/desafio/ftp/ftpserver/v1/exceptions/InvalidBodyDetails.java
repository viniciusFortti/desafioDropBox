package desafio.ftp.ftpserver.v1.exceptions;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class InvalidBodyDetails {

    private String title;
    private int status;
    private String detail;
    private long timestamp;
    private String developerMessage;


}
