package web;

import java.net.*;
import java.io.*;
public class HttpServer {
	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(35000);
		} catch (IOException e) {
			System.err.println("Could not listen on port: 35000.");
			System.exit(1);
		}
		Socket clientSocket = null;
		while(true) {
			try {
				System.out.println("Listo para recibir ...");
				clientSocket = serverSocket.accept();
			} catch (IOException e) {
				System.err.println("Accept failed.");
				System.exit(1);
			}
			PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			String inputLine, outputLine;
			int j =1;
			String get="";
			while ((inputLine = in .readLine()) != null) {
				if(j==1) {
					get=inputLine.substring(5,(inputLine.length()-" HTTP/1.1".length()));
					System.out.println(get);
					j++;
				}
				System.out.println("Received: " + inputLine);
				if (! in .ready()) {
					break;
				}
			}
			BufferedReader b=null;
			
			try {
				FileReader f = new FileReader(System.getProperty("C:\\Users\\ItaloPC\\Desktop\\ARSW\\laboratorio2\\")+get);
	   	     	b = new BufferedReader(f);
			} catch (Exception e) {
				System.out.println("no se pudo");
			}
			
			
			while ((inputLine = b.readLine()) != null) {				
				out.println(inputLine);
				if (! in .ready()) {
					break;
				}
			}
			
			outputLine = "<!DOCTYPE html>" + "<html>" + "<head>" + "<meta charset=\"UTF-8\">" + "<title>Title of the document</title>\n" + "</head>" + "<body>" + "My Web Site" + "</body>" + "</html>" + inputLine;
			out.println(outputLine);
			out.close(); in .close();
			clientSocket.close();
			
			
		}
	
		//serverSocket.close();
	}
}
