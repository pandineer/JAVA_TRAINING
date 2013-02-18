package proxy;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.impl.client.DefaultHttpClient;

public class HttpClientProxyV4 {

    public static void main(String[] args) throws Exception {

        DefaultHttpClient httpClient = new DefaultHttpClient();

     // 以下の2行を追加
     // HttpHost proxy = new HttpHost("192.168.0.101", 8080);
     // httpClient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);

     Credentials credentials = new UsernamePasswordCredentials("user","hoge");
     AuthScope scope = new AuthScope("proxy_hostname", PORT);
     httpClient.getCredentialsProvider().setCredentials(scope, credentials);

     HttpGet request = new HttpGet("http://google.com");
     HttpResponse httpResponse = null;
     try {
         System.out.println("start");
         httpResponse = httpClient.execute(request);
         System.out.println("end");
     } catch (Exception e) {
         System.out.println(e);
         // Log.d(TAG, "Error Execute");
     }
     System.out.println(httpResponse);
    }
}