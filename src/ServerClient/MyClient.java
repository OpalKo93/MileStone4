package ServerClient;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class MyClient implements Client {
	
	private Socket socket;
     
	@Override
	public void connect(String ip, int port) {
		
		try {
			socket = new Socket(ip,port);
			System.out.println("connected to the flight simulator!");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void setPathValue(String path, Double value) {
		
		try {
			PrintWriter OutToFlightGear = new PrintWriter(socket.getOutputStream());
			OutToFlightGear.println("set " + path +" " + value);
			System.out.println("sending to simulator : "+ "set " + path +" " + value);
			OutToFlightGear.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}

	@Override
	public void close() {
		
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void disconnect() {
		
		try {
			PrintWriter OutToFlightGear = new PrintWriter(socket.getOutputStream());
			OutToFlightGear.println("bye");
			System.out.println("sending to simulator : "+ "bye");
			OutToFlightGear.flush();
			close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}

	
	

	@Override
	public Socket getSocket() {
		return socket;
	}
	}
		


