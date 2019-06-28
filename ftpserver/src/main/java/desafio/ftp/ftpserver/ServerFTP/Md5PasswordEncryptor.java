package desafio.ftp.ftpserver.ServerFTP;

import org.apache.ftpserver.util.EncryptUtils;


/**
 * Password encryptor that hashes the password using MD5. Please note that this form
 * of encryption is sensitive to lookup attacks.
 *
 * @author <a href="http://mina.apache.org">Apache MINA Project</a>
 */
public class Md5PasswordEncryptor implements PasswordEncryptor {

    /**
     * Hashes the password using MD5
     */
    public String encrypt(String password) {
        return EncryptUtils.encryptMD5(password);
    }

    /**
     * {@inheritDoc}
     */
    public boolean matches(String passwordToCheck, String storedPassword) {
        if(storedPassword == null) {
            throw new NullPointerException("storedPassword can not be null");
        }
        if(passwordToCheck == null) {
            throw new NullPointerException("passwordToCheck can not be null");
        }

        return encrypt(passwordToCheck).equalsIgnoreCase(storedPassword);
    }

}
