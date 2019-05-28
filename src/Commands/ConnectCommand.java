package Commands;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ServerClient.Client;


public class ConnectCommand implements Command {

    Client c;
	
    public ConnectCommand(Client c) {
	this.c =c;
 }
    
	@Override
	public void doCommand(String[] s, HashMap<String, Double> symbolTable) {
	
	String ip = s[1];
	int port = Integer.parseInt(s[2]);	
	c.connect(ip, port);
	
	}
}
