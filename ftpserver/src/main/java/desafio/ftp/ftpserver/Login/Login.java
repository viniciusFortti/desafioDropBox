package desafio.ftp.ftpserver.Login;

import org.apache.ftpserver.ftplet.*;

import java.io.IOException;

public class Login implements Ftplet {


    @Override
    public void init(FtpletContext ftpletContext) throws FtpException {}

    @Override
    public void destroy() {}

    @Override
    public FtpletResult beforeCommand(FtpSession session, FtpRequest request) throws FtpException, IOException {
        String command = request.getCommand();
        if (!command.contains("USER") && !command.contains("PASS") && !command.endsWith(".delete")){
            return null;
        }else {
            if ((command.contains("USER") || command.contains("PASS")) && !request.getArgument().endsWith(".delete")) {
                UserManagerClass.criarNovoUsuario(request, command);
                return null;
            }
        }
        return null;
    }

    @Override
    public FtpletResult afterCommand(FtpSession session, FtpRequest request, FtpReply reply) throws FtpException, IOException {
        return null;
    }

    @Override
    public FtpletResult onConnect(FtpSession session) throws FtpException, IOException {
        return null;
    }

    @Override
    public FtpletResult onDisconnect(FtpSession session) throws FtpException, IOException {
        return null;
    }
}