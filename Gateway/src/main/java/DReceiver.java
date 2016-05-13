import java.net.*;  
public class DReceiver{  
  public static void main(String[] args) throws Exception {  
    DatagramSocket ds = new DatagramSocket(5000);  
    byte[] buf = new byte[1024];  
    DatagramPacket dp = new DatagramPacket(buf, 1024);  
    while(true)
    {
    ds.receive(dp); 
   InetAddress ip=dp.getAddress();
   System.out.println(ip);
    String str = new String(dp.getData(), 0, dp.getLength());  
    System.out.println(str);  
   
  }
    //ds.close();     
}  
}