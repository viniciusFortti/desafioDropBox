package desafio.ftp.ftpserver.Controller;

import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value ="/uploads")
public class ArquivoController {

    @PostMapping(value = "/up")
    public String uploadArquivo(@RequestParam MultipartFile arquivo, User user){

        return "arquivo show";
    }
}
