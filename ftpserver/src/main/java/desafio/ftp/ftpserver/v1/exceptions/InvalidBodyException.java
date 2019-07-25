package desafio.ftp.ftpserver.v1.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class InvalidBodyException extends RuntimeException {

    public InvalidBodyException(String message) {
        super(message);
    }
}
