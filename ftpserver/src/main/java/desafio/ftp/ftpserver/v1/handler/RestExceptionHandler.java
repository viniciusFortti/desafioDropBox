package desafio.ftp.ftpserver.v1.handler;


import desafio.ftp.ftpserver.v1.exceptions.InvalidBodyDetails;
import desafio.ftp.ftpserver.v1.exceptions.InvalidBodyException;
import desafio.ftp.ftpserver.v1.exceptions.ResourceNotFoundDetails;
import desafio.ftp.ftpserver.v1.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException rfnException) {
        ResourceNotFoundDetails rnfDetails = ResourceNotFoundDetails.builder()
                .timestamp(new Date().getTime())
                .status(HttpStatus.NOT_FOUND.value())
                .title("Erro na Solicitação, usuario ou parametros incorretos.")
                .detail(rfnException.getMessage())
                .developerMessage(rfnException.getClass().getName())
                .build();
        return new ResponseEntity<>(rnfDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidBodyException.class)
    public ResponseEntity<?> handleInvalidBodyException(InvalidBodyException invalidBodyException) {
        InvalidBodyDetails invalidBodyDetails = InvalidBodyDetails.builder()
                .timestamp(new Date().getTime())
                .status(HttpStatus.NOT_FOUND.value())
                .title("Erro na Solicitação, usuario ou parametros incorretos.")
                .detail(invalidBodyException.getMessage())
                .developerMessage(invalidBodyException.getClass().getName())
                .build();
        return new ResponseEntity<>(invalidBodyDetails, HttpStatus.BAD_REQUEST);
    }
}
