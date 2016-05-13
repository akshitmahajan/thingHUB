import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;

public class ApToNormalTimeout 
{
	public static void main(String[] args) throws UnknownHostException, InterruptedException,SocketException 
	{
		Thread.sleep(5000);
		try
		{
			Process p1=Runtime.getRuntime().exec("sudo reboot");
		}
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
