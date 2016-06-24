import java.net.*;
import java.io.*;
import java.util.Date;
import java.awt.Desktop;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.Iterator;
import java.io.File;

class Main {
	public static void main(String[] args) throws Exception {

		// Listen for a connection from a client
		ServerSocket serverSocket = new ServerSocket(1234);
		if(Desktop.isDesktopSupported())
			Desktop.getDesktop().browse(new URI("http://localhost:1234"));
		else
			System.out.println("Please direct your browser to http://localhost:1234.");
		while(true)
		{
			Socket clientSocket = serverSocket.accept();
			System.out.println("Got a connection!");
			PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			
			// Receive the request from the client
			String inputLine;
			String url = "";
			String post = "";
			boolean isGet = false;
			int postContentLength = 0;
			StringBuilder postRequest = new StringBuilder();
			
			while ((inputLine = in.readLine()) != null) {
				System.out.println("The client said: " + inputLine);
				if(inputLine.contains("GET ")) {
					int end = inputLine.indexOf(" HTTP");
					url = inputLine.substring(4, end);
					isGet = true;
				}
				if(inputLine.contains("POST ")) {
					
				}
				if(inputLine.contains("Content-Length: ")) {
					String sub = inputLine.substring(16, inputLine.length());
					postContentLength = Integer.parseInt(sub);
				}
				if(inputLine.length() < 2){
					while (postRequest.length() < postContentLength) {
						postRequest.append((char) in.read());
					}
 					break;
 				}
			}
			
			String dateString = (new Date()).toGMTString();
			
			// Determine if POST or GET
			String payload = "";
			if (isGet) {
				payload = generateGetReturn(url);
			}
			else {
				payload = generatePostReturn(postRequest.toString());
			}
			
			// Send HTTP headers
			System.out.println("Sending a response...");
			out.print("HTTP/1.1 200 OK\r\n");
			out.print("Content-Type: text/html\r\n");
			out.print("Content-Length: " + Integer.toString(payload.length()) + "\r\n");
			out.print("Date: " + dateString + "\r\n");
			out.print("Last-Modified: " + dateString + "\r\n");
			out.print("Connection: close\r\n");
			out.print("\r\n");

			// Send the payload
			out.println(payload);
			clientSocket.close();
		}
	}
	
	// build page for GETs
	static String generateGetReturn(String url) {
		if(url.contains("favicon.ico")) {
			return "";
		}
		else {
			File subForm = new File("subForm.html");
			if(!subFormExists(subForm))
				return "404 page not found";
			else {
				return htmlFileReader(subForm);
			}
		}
	}
	
	static boolean subFormExists(File subForm) {
		if(subForm.exists())
			return true;
		else
			return false;
	}
	
	static String htmlFileReader(File f) {
		try {
			FileReader fileReader = new FileReader(f);
			BufferedReader buffReader = new BufferedReader(fileReader);
			
			String line = null;
			StringBuilder htmlText = new StringBuilder();
			
			while((line = buffReader.readLine()) != null) {
				htmlText.append(line);
				htmlText.append("\n");
			}
			
			return htmlText.toString();
			
		} catch(FileNotFoundException ex) {
			System.out.println("Couldn't open file " + f);
		} catch(IOException ex) {
			System.out.println("Error reading file " + f);
		}
		
		return "Problem serving html file reader request.  See console log.";
	}	

	// build response to POST request
	static String generatePostReturn(String postRequest) {
		// replace with handler for all POST form submissions
		
		return "post return";
	}
}