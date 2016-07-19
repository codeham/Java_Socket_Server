import java.net.*;
import java.util.Scanner;
import java.io.*;

/*
 * args[0] = IP Addrr.
 * args[1] = Name of the input-data-file
 * args[2] = Same data as args[1], contains the echo from server
 */
public class ClientSide {

	public static void main(String[] args) {
		ClientSide newClient = new ClientSide();
		if (args.length == 3) {
			newClient.run(args[0], args[1], args[2]);
		} else {
			System.out.println("Error Invalid Input From Command Line");
			return;
		}
	}

	public void run(String hostIP, String inputFile, String outputFile) {
		try {
			// Connect to ServerSocket on server side
			Socket s = new Socket(hostIP, 9999);
			if (s.isConnected()) {
				System.out.println("Server Connected Success...");
			}
			// New file from the command line
			Scanner k = new Scanner(new File(inputFile));

			// Parse through the file and store in a string
			String content = "";
			while(k.hasNextLine()){
				content = content + k.nextLine();
			}
			//Send out to server side
			PrintStream out = new PrintStream(s.getOutputStream());
			out.println(content);
			
			//Receive from server
			BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			String echo = br.readLine();
			System.out.println("PRINT: " + echo);
			
			//Create file
			BufferedWriter writeFile = new BufferedWriter(new FileWriter(new File(outputFile)));
			writeFile.write(echo);
			writeFile.close();
			
			//Close off open streams || buffers
			s.close();
			k.close();
			br.close();
			writeFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}