import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
 
public class TestForWifiDevices {
 public static void main(String[] args) throws IOException {
  URL url = new URL("http://localhost:8080/resttest/services/Order /3");
  HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
  httpCon.setDoOutput(true);
  httpCon.setRequestMethod("POST");
  OutputStreamWriter out = new OutputStreamWriter(
      httpCon.getOutputStream());
  System.out.println(httpCon.getResponseCode());
  System.out.println(httpCon.getResponseMessage());
  out.close();
 }
}