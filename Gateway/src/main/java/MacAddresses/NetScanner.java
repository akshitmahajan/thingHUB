package MacAddresses;
import java.io.*;
import java.net.*;
import java.util.*;
/**
 * Created by IntelliJ IDEA.
 * User: masoumeh nourollahi
 */
public class NetScanner {
    static byte [] netid=new byte[4];
    static byte [] bcid=new byte[4];
    static List inetAddresses;
    private static String y;
    static String  substring="error";
    public static void main(String[] args) {
          try{
             Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
                  for (NetworkInterface netint : Collections.list(nets))
                           displayInfo(netint);
                           pinging();
       }catch(java.net.SocketException s){
          System.out.print('/');  //
           System.exit(0);
       } catch (IOException e) {
           e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
       }
    }
   static void displayInfo(NetworkInterface netint) throws IOException {
        inetAddresses = netint.getInterfaceAddresses();
        System.out.println("some information about my link:");
        System.out.printf("Display name: %s\n", netint.getDisplayName());
        System.out.printf("Name: %s\n", netint.getName());
        System.out.printf("Up? %s\n", netint.isUp());
        System.out.printf("Loopback? %s\n", netint.isLoopback());
        System.out.printf("PointToPoint? %s\n", netint.isPointToPoint());
        System.out.printf("Supports multicast? %s\n", netint.supportsMulticast());
        System.out.printf("Virtual? %s\n", netint.isVirtual());
        System.out.printf("Hardware address: %s\n",Arrays.toString(netint.getHardwareAddress()));
        System.out.printf("MTU: %s\n", netint.getMTU());
        System.out.printf("\n");
        System.out.println("my ip address is: "+((InterfaceAddress)inetAddresses.get(0)).getAddress());
        System.out.println("the sunnetmask address is: "+((InterfaceAddress)inetAddresses.get(0)).getNetworkPrefixLength());
        System.out.println("the broadcast address of this subnet is:"+bcid);
        }
    static void pinging() throws IOException {
           Socket mySocket;
           if(!inetAddresses.isEmpty()){
           bcid =((InterfaceAddress)inetAddresses.get(0)).getBroadcast().getAddress(); //gets the last ip address in subnet
           netaddress(
                   ((InterfaceAddress)inetAddresses.get(0)).getNetworkPrefixLength(),  //creats the mask field
                   ((InterfaceAddress)inetAddresses.get(0)).getAddress().getAddress()  //creats the myip field
           );
           byte [] pingip=netid;
           for(;;){
           InetAddress ping =InetAddress.getByAddress(pingip);
        //   boolean reachable=ping.isReachable(5000);     // This command is doing the same work as the ping command in windows
           try{
               Process ps = Runtime.getRuntime().exec("ping.exe " + ping);
                                         BufferedReader br = new BufferedReader(new InputStreamReader(ps.getInputStream()));
                                         StringBuilder sb = new StringBuilder();
                                         String line;
                                         while ((line = br.readLine()) != null) {
                                             sb.append(line);
                                             sb.append("\n");
                                         }
                                   y= sb.toString();
                                         System.out.print(sb.toString());
                                     } catch (IOException e) {
                                         e.printStackTrace();
                                     }
           if(!contains(y,substring)){
               System.out.println("The ip address "+ping+"is reachable");
               for(int port =0;port<65536;port++){
                   try{
                       mySocket=new Socket(ping,port);
                       System.out.println("The port"+port +"on ip:"+ ping + "is free.");
                       mySocket.close();
                   }catch(IOException c){
                       System.out.println("The port"+port +"on ip:"+ ping + "is closed.");
                   }
               }
           }
          else
               System.out.println("The pinged ip addres is unreachable, probably it is not up in your subnet");
            pingip[3]++;
            if(pingip[3]==(byte)255)
               pingip[2]++;
            if(pingip[2]==(byte)255)         //here we ping ips in our subnet from the first ip address to the last one
               pingip[1]++;
            if(pingip[1]==(byte)255)
               pingip[0]++;
            if(pingip[0]==(byte)127)        //beacuase the ip addresses that are not in class A have at least 2 bytes of net ids
            break;
            if(pingip[0]==bcid[0] & pingip[1]==bcid[1] & pingip[2]==bcid[2] & pingip[3]==bcid[3])
            break;
           }
       }
           else
               System.out.println("null pointer exception");
       }
    static void netaddress(short masklength,byte[] sysip)// throws IOException
         {
            int k=masklength;
            String ipstring="";
            int ip1=sysip[0];
            int ip2=sysip[1];
            int ip3=sysip[2];
            int ip4=sysip[3];
            int j=8;
             for(int i=0;i<32;i++){
                 if (j==0) {
                 ipstring+=".";
                 j=8;
                 }
                 if(k>0){
                     ipstring+="1";
                     k--;
                 }
                 else ipstring+="0";
                 j--;
             }
                int firstpart,secondpart,thirdpart;
                firstpart=ipstring.indexOf('.');
                netid[0]=(byte)(ip1& Integer.valueOf((ipstring.substring(0,firstpart)),10));
                ipstring=ipstring.substring((firstpart+1));
                secondpart=ipstring.indexOf('.');
                netid[1]=(byte)(ip2& Integer.valueOf((ipstring.substring(0,secondpart)),10));
                ipstring=ipstring.substring((secondpart+1));
                thirdpart=ipstring.indexOf('.');
                netid[2]=(byte)(ip3& Integer.valueOf((ipstring.substring(0,thirdpart)),10));
                netid[3]=(byte)(ip4& Integer.valueOf((ipstring.substring(thirdpart+1)),10));
                 System.out.println("The first ip address in this subnet is:"+ipstring);
        }
        public static boolean contains(String y, String substring) {
    if(y.indexOf(substring) != -1)
   return true;
    else { return false; }
        }
}