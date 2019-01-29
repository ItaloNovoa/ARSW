package edu.eci.laboratorio2CosSen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DecimalFormat;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class punto3FinalServidor {

	public static void main(String[] args) throws IOException, ScriptException {
		//ALGUNOS ERRORES GENERADOS POR UNAS FUNCIONES 
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(35000);
		} catch (IOException e) {
			System.err.println("Could not listen on port: 35000.");
			System.exit(1);
		}
		Socket clientSocket = null;
		try {
			clientSocket = serverSocket.accept();
		} catch (IOException e) {
			System.err.println("Accept failed.");
			System.exit(1);
		}
		PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
		BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		String inputLine, outputLine;
		String operador="cos";
		double respuesta;

		while ((inputLine = in .readLine()) != null) {
			if(inputLine.contains("fun:")) {
				if(inputLine.contains("cos")) {
					operador="cos";
				}else if(inputLine.contains("sin")) {
					operador="sin";
				}else if(inputLine.contains("tan")) {
					operador="tan";
				}
				inputLine="cambio a ->"+ operador;
			}else {				
				ScriptEngineManager manager = new ScriptEngineManager();
				ScriptEngine engine = manager.getEngineByName("js");	
				Object result; 
				DecimalFormat formato1 = new DecimalFormat("#.00####");
				if (inputLine.contains("π")) {
					int numPi=inputLine.indexOf("π");
					double  radianes= 180;
					if(inputLine.substring(0, numPi).length()!=0) {
						radianes= Double.parseDouble(inputLine.substring(0, numPi))*180;
					}
					result = engine.eval(String.valueOf(radianes)+inputLine.substring(numPi+1,inputLine.length()));					   
					respuesta=Math.toRadians(Double.parseDouble(result.toString()));					  
				}else {
					result = engine.eval(inputLine);						   
					respuesta=Math.toRadians(Double.parseDouble(result.toString()));
				}
				///operadores
				if(operador=="cos") { 					  				   
					inputLine ="Respuesta = "+(String.valueOf(formato1.format(Math.cos(respuesta))));
				}else if(operador=="sin") {
					inputLine="Respuesta = "+String.valueOf(formato1.format(Math.sin(respuesta)));
				}else if(operador=="tan") {
					inputLine="Respuesta = "+String.valueOf(formato1.format(Math.tan(respuesta)));
				}
			}		   

			outputLine =inputLine;
			out.println(outputLine);
			if (outputLine.equals("Respuest1as: Bye.")) break;
		}
		out.close(); in .close();
		clientSocket.close();
		serverSocket.close();
	}
}
