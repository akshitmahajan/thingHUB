import java.net.InetAddress;
import java.net.NetworkInterface;

public class MacAddress {
  public static void main(String[] args) throws Exception {
    InetAddress address = InetAddress.getByName("192.168.0.104");

    NetworkInterface ni = NetworkInterface.getByInetAddress(address);
    byte[] mac = ni.getHardwareAddress();
    StringBuilder sb = new StringBuilder();
    
    for (int i = 3; i < mac.length; i++) {
      System.out.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : "");
      sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "" : ""));
    }
    String macAdd = new String(sb);
    macAdd="thingHUB-"+macAdd;
    System.out.println("\n"+macAdd); 
	 /* try {
		  InetAddress address1 = InetAddress.getByName("216.58.220.36");
      boolean reachable = address1.isReachable(10000);

      System.out.println("Is host reachable? " + reachable);
  } catch (Exception e){
      e.printStackTrace();
  }*/
  }
}