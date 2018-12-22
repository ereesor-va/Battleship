package main;
import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
public class Server {
	DatagramSocket sock;
	DatagramPacket sendPacket,receivePacket;
	GameControl gm;
	BufferedReader console;
	
	public Server() {
		gm=new GameControl();
		try {
			sock = new DatagramSocket(99);
		}catch(SocketException se) {
			System.out.println("Server: Unable to create socket"); 
			se.printStackTrace();
	        System.exit(1);
		}
	}
		
	public boolean establishConnection() {
		byte[] data=new byte[256];
		receivePacket=new DatagramPacket(data, data.length);
		try {
			sock.receive(receivePacket);
		} catch (IOException e) {
		}
		if(receivePacket.getData()=="letsplayBattleship".getBytes()) {
			data="letsplayBattleship".getBytes();
			sendPacket=new DatagramPacket(data,data.length);
			try {
				sock.send(sendPacket);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return true;
		}else {
			return false;
		}
	}
	
	public void run() {
		console = new BufferedReader(new InputStreamReader(System.in));
		String input="";
		try {
			input=console.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(input.equalsIgnoreCase("test"));
		System.out.println(input.equalsIgnoreCase("run"));
		
		if(input.equalsIgnoreCase("run")) {
			if(establishConnection()){
				gm.startGame(sock);
			}
		}else if(input.equalsIgnoreCase("test")) {
			gm.startGame(sock);
		}
	}
	
	public void shutdown() {
		sock.close();
	}
	
	
	public static void main(String args[]) {
		Server s=new Server();
		s.run();
		s.shutdown();
	}
}

