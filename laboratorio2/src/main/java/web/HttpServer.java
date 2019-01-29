package web;

import java.net.*;
import java.io.*;
public class HttpServer {
	public static void main(String[] args) throws FileNotFoundException,IOException  {
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(35000);
		} catch (IOException e) {
			System.err.println("Could not listen on port: 35000.");
			System.exit(1);
		}
		
		while(true) {
			Socket clientSocket = null;
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
					j++;
				}
				System.out.println("Received: " + inputLine);
				if (! in .ready()) {
					break;
				}
			}
			
			String url=System.getProperty("user.dir") + get;
			System.out.println(url);
			try {					
				 BufferedReader readerFile = new BufferedReader(new InputStreamReader(new FileInputStream("C:\\Users\\2135142\\Documents\\ARSW\\laboratorio2datos\\resultado.html"), "UTF8"));
                 out.println("HTTP/2.0 200 OK");
                 out.println("Content-Type: text/html");
                 out.println("\r\n");
                 while (readerFile.ready()) {
                     out.println(readerFile.readLine());
                 }
			}catch (FileNotFoundException e) {
				out.println( "<!DOCTYPE html>" + "<html>" + "<head>" + "<meta charset=\"UTF-8\">" + "<title>Title of the document</title>\n" + "</head>" + "<body>" + "File Not Found" + "</body>" + "</html>" );
			}



			//outputLine = "<!DOCTYPE html>" + "<html>" + "<head>" + "<meta charset=\"UTF-8\">" + "<title>Title of the document</title>\n" + "</head>" + "<body>" + "My Web Site" + "</body>" + "</html>" + inputLine;
			//out.println(outputLine);
			out.close(); in .close();
			clientSocket.close();


		}

		//serverSocket.close();
	}
}
