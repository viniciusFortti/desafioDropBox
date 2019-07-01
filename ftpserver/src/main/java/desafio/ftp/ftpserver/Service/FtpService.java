package desafio.ftp.ftpserver.Service;

import org.apache.commons.net.ftp.FTPClient;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class FtpService {

    private Map<String, FTPClient> clients;

    public FtpService() {
        this.clients = new HashMap<>();
    }

    public void addClient(String id, FTPClient client) {
        clients.put(id, client);
    }

    public FTPClient getClient(String id) {
        return clients.get(id);
    }
}
