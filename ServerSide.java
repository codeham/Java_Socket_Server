import java.io.*;
import java.net.*;

public class ServerSide {

	public static void main(String[] args) throws IOException {
		ServerSide ss = new ServerSide();
		ss.run();
	}

	public void run() throws IOException {
		ServerSocket serverSocket = new ServerSocket(9999);
		try {
			// new socket bound to the same local port (server)
			Socket clientSocket = serverSocket.accept();

			// Confirmation of connection
			System.out.println("Connected to port: " + clientSocket.getLocalPort());
			System.out.println("Connected to IP: " + clientSocket.getInetAddress());

			BufferedReader br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			String echo = br.readLine();
			System.out.println("PRINT: " + echo);
			PrintStream out = new PrintStream(clientSocket.getOutputStream());
			out.println(echo);
			
			// Close off streams || buffers
			br.close();
			out.close();
			clientSocket.close();
			serverSocket.close();
			System.out.println("Server Closed");
		} catch (IOException e) {
			System.out.println(e);
		}

	}
}