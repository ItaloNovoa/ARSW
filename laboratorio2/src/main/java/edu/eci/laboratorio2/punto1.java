package edu.eci.laboratorio2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class punto1 {
	public static void main(String[] args) throws Exception {
		URL google = new URL("http://www.google.com/");

		try (BufferedReader reader = new BufferedReader(new InputStreamReader(google.openStream()))) {
			String inputLine = null;
			System.out.println("salida");
			System.out.println("protocolo "+google.getProtocol());
			System.out.println("Authority "+google.getAuthority());
			System.out.println("Host: "+google.getHost());
			System.out.println("Port: "+google.getPort());    
			System.out.println("path: "+google.getPath());
			System.out.println("Query: "+google.getQuery());
			System.out.println("File : "+google.getFile());
			System.out.println("Ref: "+google.getRef());

		} catch (IOException x) {
			System.err.println(x);

		}

	}
}
