package proxy;

import java.net.Authenticator;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.URL;

public class ProxyAuthenticator extends Authenticator {
  private String proxyhost;
  private String userid;
  private String password;

  public ProxyAuthenticator(String proxyhost, String userid, String password) {
    this.proxyhost = proxyhost;
    this.userid = userid;
    this.password = password;
  }

  @Override
  public URL getRequestingURL() {
    if (this.proxyhost == null) return null;
    try {
      return new URL(this.proxyhost);
    } catch (MalformedURLException e) {
      return null;
    }
  }

  @Override
  protected RequestorType getRequestorType() {
    return RequestorType.PROXY;
  }

  @Override
  protected PasswordAuthentication getPasswordAuthentication() {
    if (this.userid == null || this.password == null) return null;
    return new PasswordAuthentication(userid, password.toCharArray());
  }

  public static void main(String[] args)
  {
      ProxyAuthenticator test = new ProxyAuthenticator("proxy_hostname:PORT", "username", "password");
  }
}