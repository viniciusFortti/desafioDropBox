package desafio.ftp.ftpserver.v1.login;

import org.apache.ftpserver.ftplet.*;

import java.io.IOException;

public class Login implements Ftplet {


    @Override
    public FtpletResult beforeCommand(FtpSession session, FtpRequest request){
        String command = request.getCommand();

        if ((command.contains("USER") || command.contains("PASS"))) {
            UserManagerCustom.criarNovoUsuario(request,command);
            return FtpletResult.DEFAULT;
        }
        return FtpletResult.DEFAULT;
    }

    @Override
    public void init(FtpletContext ftpletContext) throws FtpException {}

    @Override
    public void destroy() {}

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