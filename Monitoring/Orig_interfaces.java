import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Orig_interfaces
{
	public static void main(String[] args) {
		try {

			String orig_interfaces="\n auto lo \n iface lo inet loopback \n auto eth0 \n allow-hotplug eth0 \n iface eth0 inet manual \n auto wlan0 \n allow-hotplug wlan0 \n iface wlan0 inet manual \n wpa-conf /etc/wpa_supplicant/wpa_supplicant.conf \n";
			File file = new File("/etc/network/interfaces");
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(orig_interfaces);
			bw.close();
			System.out.println("Done");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
