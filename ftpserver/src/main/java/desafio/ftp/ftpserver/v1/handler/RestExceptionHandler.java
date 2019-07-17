package desafio.ftp.ftpserver.v1.handler;

import desafio.ftp.ftpserver.v1.exceptions.MultiPartException;
import desafio.ftp.ftpserver.v1.exceptions.MultiPartiDetails;
import desafio.ftp.ftpserver.v1.exceptions.ResourceNotFoundDetails;
import desafio.ftp.ftpserver.v1.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException rfnException) {
        ResourceNotFoundDetails rnfDetails = ResourceNotFoundDetails.Builder
                .newBuilder()
                .timestamp(new Date().getTime())
                .status(HttpStatus.NOT_FOUND.value())
                .title("Erro na Solicitação, usuario ou parametros incorretos.")
                .detail(rfnException.getMessage())
                .developerMessage(rfnException.getClass().getName())
                .build();
        return new ResponseEntity<>(rnfDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MultipartException.class)
    public ResponseEntity<?> handleMultipartException(MultiPartException mtpException) {
        MultiPartiDetails mtpDetails = MultiPartiDetails.Builder
                .newBuilder()
                .timestamp(new Date().getTime())
                .status(HttpStatus.NOT_FOUND.value())
                .title("Erro na Solicitação, arquivo não localizado.")
                .detail(mtpException.getMessage())
                .developerMessage(mtpException.getClass().getName())
                .build();
        return new ResponseEntity<>(mtpDetails, HttpStatus.NOT_FOUND);
    }


}
