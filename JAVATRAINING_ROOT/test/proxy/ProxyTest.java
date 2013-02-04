package proxy;

import java.net.Authenticator;
import java.net.InetAddress;


public class ProxyTest
{

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        System.setProperty("http.proxyHost", "proxy.ricoh.co.jp");
        System.setProperty("http.proxyPort", "8080");

        Authenticator httpAuthenticator = new HttpAuthenticator("p000507390", "password");
        Authenticator.setDefault(httpAuthenticator);

        try
        {
            // IPやホスト名などを指定
            InetAddress inetAddress = InetAddress.getByName( "10.61.90.47" );
            // InetAddress inetAddress = InetAddress.getByName( "yahoo.co.jp" );

            // isReachableメソッドでpingが実現できます。引数はタイムアウト(ミリ秒指定)。
            boolean pingdata = inetAddress.isReachable( 1000 );
            System.out.println("結果:" + pingdata );
        }
        catch(Exception e)
        {
            System.out.println(e);
        }

    }

}
