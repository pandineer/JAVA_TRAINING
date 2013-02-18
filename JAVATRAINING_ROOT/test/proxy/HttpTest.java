package proxy;

import java.io.BufferedInputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpTest
{

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        URL url = null;
        HttpURLConnection http = null;
        BufferedInputStream bis = null;
        try
        {
            url = new URL("http://yahoo.co.jp");
            http = (HttpURLConnection)url.openConnection();
            http.setRequestMethod("GET");
            http.connect();
            bis = new BufferedInputStream(http.getInputStream());
            int data;
            while((data = bis.read()) != -1)
            {
                System.out.write(data);
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }

}
