package ServerClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class DataReaderServer implements Server {

	
    ConcurrentHashMap<String,Double> simulatorVars;
    volatile boolean stop;
	Thread serverThread;
	Filenames names;
    static Object lock = new Object();
    
	public DataReaderServer( ConcurrentHashMap<String,Double> simulatorVars,Filenames names) {
		this.simulatorVars = simulatorVars;
		stop = false;
		this.names = names;
	}
	
		@Override
		public void openServer(int port) {
			String[] name = names.getString();
			serverThread = new Thread(new Runnable() {
				
				@Override
				public void run() {
					try {
						ServerSocket myserver = new ServerSocket(port);
						myserver.setSoTimeout(1000);
						
						String[] name = names.getString();
						
						while(!stop)
						{
							try {
								Socket simulatorclient = myserver.accept();
								//System.out.println("connected to the simulator");
							  synchronized (this) { this.notifyAll(); }
							 
								System.out.println("simulator connected to myserver");
								
								BufferedReader reader = new BufferedReader(new InputStreamReader(simulatorclient.getInputStream()));
								String line;
								while((line= reader.readLine()) != null) {
									System.out.println("Received from simulator: " + line);
									String[] values = line.split(",");
									for(int index = 0;index<name.length;index++) {
										simulatorVars.put(name[index],Double.parseDouble(values[index]));
									}
									
								}
								simulatorclient.getInputStream().close();
								simulatorclient.close();
							} catch(Exception e) {} // If server.accept didn't work, we'll try again
						} // end of loop
						
						myserver.close();
						
					} catch (IOException e) {
						System.out.println("Couldn't open the server ( erorr in class ServerSocket())");
						//e.printStackTrace();
					}
			
				}
			
			});
			serverThread.start();
			
			synchronized (this) {
				try {
					System.out.println("waiting for the simulator to connect");
					this.wait();
				} catch (InterruptedException e) {
				}
			}
		}

		@Override
		public void stop() {
			stop = true;
		}


		@Override
		public Thread getThread() {
			return serverThread;
		}
		
	}
	

