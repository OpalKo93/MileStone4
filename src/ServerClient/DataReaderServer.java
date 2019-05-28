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
						ServerSocket server = new ServerSocket(port);
						System.out.println("opened server");
						String[] name = names.getString();
						while(!stop)
						{
							Socket client = server.accept();
							System.out.println("client connected to server");
							BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
							String line;
							while((line= reader.readLine()) != null) {
								System.out.println("Received from simulator: " + line);
								String[] values = line.split(",");
								for(int index = 0;index<name.length;index++) {
									simulatorVars.put(name[index],Double.parseDouble(values[index]));
								}
								
							}
							client.getInputStream().close();
							client.close();
						}
						server.close();
						
					} catch (IOException e) {
						e.printStackTrace();
					}
			
				}
			
			});
				serverThread.start();
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
	

