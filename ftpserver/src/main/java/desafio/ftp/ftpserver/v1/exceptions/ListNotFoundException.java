package desafio.ftp.ftpserver.v1.exceptions;

import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ListNotFoundException extends RuntimeException {
    public ListNotFoundException(String message) {
            super(message);
    }
}


