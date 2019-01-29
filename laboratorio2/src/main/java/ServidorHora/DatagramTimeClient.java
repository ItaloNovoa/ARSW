package ServidorHora;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;



public class DatagramTimeClient {
	private  String fechaFinal="el servidor aun no a estado activo";

	public class task extends TimerTask{

		@Override
		public void run() {
			
			
			try {
				DatagramSocket socket;
				socket = new DatagramSocket(4445);
				socket.close();
				System.out.println("la ultima fecha en server es->"+"Date: " + fechaFinal);
			} catch (SocketException ex) {
				byte[] sendBuf = new byte[256];
				try {
					DatagramSocket socket = new DatagramSocket();
					byte[] buf = new byte[256];
					InetAddress address = InetAddress.getByName("127.0.0.1");
					DatagramPacket packet = new DatagramPacket(buf, buf.length, address, 4445);
					socket.send(packet);

					packet = new DatagramPacket(buf, buf.length);
					socket.receive(packet);			
					String received = new String(packet.getData(), 0, packet.getLength());
					fechaFinal=received;

				} catch (SocketException ex1) {				
					Logger.getLogger(DatagramTimeClient.class.getName()).log(Level.SEVERE, null, ex);				
				} catch (UnknownHostException ex1) {				
					Logger.getLogger(DatagramTimeClient.class.getName()).log(Level.SEVERE, null, ex);				
				} catch (IOException ex1) {				
					Logger.getLogger(DatagramTimeClient.class.getName()).log(Level.SEVERE, null, ex);				
				}finally {
					System.out.println("Date: " + fechaFinal);
				}
			}			
			
		}		
	}	

	public static void main(String[] args) {
		DatagramTimeClient cl1= new DatagramTimeClient();
		// creating timer task, timer
		TimerTask tasknew = cl1.new task();
		Timer timer = new Timer();
		// scheduling the task at fixed rate delay
		timer.scheduleAtFixedRate(tasknew,0,5000);

	}

	private void setultimafecha(String fF) {
		fechaFinal=fF;
	}

}
