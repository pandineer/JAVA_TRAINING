package proxy;

import java.net.Authenticator;
import java.net.PasswordAuthentication;

public class HttpAuthenticator extends Authenticator
{
    private String username;
    private String password;

    public HttpAuthenticator(String username, String password)
    {
        this.username = username;
        this.password = password;
    }

    protected PasswordAuthentication getPAsswordAuthentication()
    {
        return (new PasswordAuthentication(username, password.toCharArray()));
    }
}
