package Commands;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ServerClient.DataReaderServer;


public class OpenServerCommand implements Command {

	DataReaderServer server;
	
	public OpenServerCommand(DataReaderServer server) {
	
		this.server = server;
	}
	
	@Override
	public void doCommand(String[] s, HashMap<String, Double> symbolTable) {
		
		int port = Integer.parseInt(s[1]);
        server.openServer(port);
	}
	
	
}
