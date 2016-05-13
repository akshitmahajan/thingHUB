import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReadSSID {
	 public static void main(String args[]) throws IOException {


	
	
	// Open the file
	FileInputStream fstream;
	try {
		fstream = new FileInputStream("abc.txt");
	
	BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

	String strLine;

	//Read File Line By Line
	while ((strLine = br.readLine()) != null)   {
	  // Print the content on the console
	  System.out.println (strLine);
	}

	//Close the input stream
	br.close();
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

}
}