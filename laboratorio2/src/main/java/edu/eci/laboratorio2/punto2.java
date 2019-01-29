package edu.eci.laboratorio2;
import java.io.*;
import java.net.*;
import java.util.Scanner;


public class punto2 {
	public static void main(String[] args) throws Exception {
                Scanner scan = new Scanner(System.in);
                String a = scan.nextLine();                
                scan.close();
		URL url = new URL(a);


		FileWriter fichero = null;
		PrintWriter pw = null;
		try
		{
                        //ingrese ubicación de destino del html
			fichero = new FileWriter("C:\\Users\\ItaloPC\\Documents\\resultado.html");
			pw = new PrintWriter(fichero);
			try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()))) {
				String inputLine = null;
				while ((inputLine = reader.readLine()) != null) {					
					pw.println( inputLine);
				}				
			} catch (IOException x) {
				System.err.println(x);				

			} }catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {

					if (null != fichero)
						fichero.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}		
	}

}