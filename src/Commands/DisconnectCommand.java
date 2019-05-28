package Commands;

import java.util.HashMap;

import ServerClient.Client;

public class DisconnectCommand implements Command {

    Client c;
    
    public DisconnectCommand(Client c) {
		this.c = c;
	}
	
	@Override
	public void doCommand(String[] s, HashMap<String, Double> symbolTable) {
      c.disconnect();
	}

}
