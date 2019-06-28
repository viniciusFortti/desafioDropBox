package desafio.ftp.ftpserver.ServerFTP;


import org.apache.ftpserver.ftplet.UserManager;
import org.apache.ftpserver.usermanager.impl.PropertiesUserManager;


import java.io.File;
import java.net.URL;

public class PropertiesUserManagerFactory implements UserManagerFactory {

    private String adminName = "admin";

    private File userDataFile;

    private URL userDataURL;

    private PasswordEncryptor passwordEncryptor = new Md5PasswordEncryptor();

    public UserManager createUserManager() {
        if (userDataURL != null) {
            return new PropertiesUserManager((org.apache.ftpserver.usermanager.PasswordEncryptor) passwordEncryptor, userDataURL,
                    adminName);
        } else {

            return new PropertiesUserManager((org.apache.ftpserver.usermanager.PasswordEncryptor) passwordEncryptor, userDataFile,
                    adminName);
        }
    }

    /**
     * Get the admin name.
     * @return The admin user name
     */
    public String getAdminName() {
        return adminName;
    }

    /**
     * Set the name to use as the administrator of the server. The default value
     * is "admin".
     *
     * @param adminName
     *            The administrator user name
     */
    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    /**
     * Retrieve the file used to load and store users
     * @return The file
     */
    public File getFile() {
        return userDataFile;
    }

    /**
     * Set the file used to store and read users.
     *
     * @param propFile
     *            A file containing users
     */
    public void setFile(File propFile) {
        this.userDataFile = propFile;
    }

    /**
     * Retrieve the URL used to load and store users
     * @return The {@link URL}
     */
    public URL getUrl() {
        return userDataURL;
    }

    /**
     * Set the URL used to store and read users.
     *
     * @param userDataURL
     *            A {@link URL} containing users
     */
    public void setUrl(URL userDataURL) {
        this.userDataURL = userDataURL;
    }

    /**
     * Retrieve the password encryptor used by user managers created by this factory
     * @return The password encryptor. Default to {@link Md5PasswordEncryptor}
     *  if no other has been provided
     */
    public PasswordEncryptor getPasswordEncryptor() {
        return passwordEncryptor;
    }

    /**
     * Set the password encryptor to use by user managers created by this factory
     * @param passwordEncryptor The password encryptor
     */
    public void setPasswordEncryptor(PasswordEncryptor passwordEncryptor) {
        this.passwordEncryptor = passwordEncryptor;
    }
}
