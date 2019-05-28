package Commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import ServerClient.DataReaderServer;



public class IfCommand extends ConditionCommands implements Command {

	DataReaderServer server;
	CommandHandler ch;
	public IfCommand(DataReaderServer server, CommandHandler ch) {
		super(server,ch);
		this.server = server;
		this.ch = ch;
	}
	
	@Override
	public void doCommand(String[] str, HashMap<String, Double> symbolTable) {
		String variable = str[1];
		String operator = str[2];
		String value = str[3];
		
		StringBuilder sb = new StringBuilder();
		for(String s: str)
		{
			sb.append(s+" ");
		}
		
		
        List<String[]> listOfCommands = new LinkedList<>();
        listOfCommands = convertArgumentsToListOfCommands(sb.toString());
        
        if(condition(variable,  operator, value))
        {
        	//listOfCommands.forEach((String[] command)->ch.commandHashMap.get(command[0].replace(",",""))
    			//	.doCommand(command,symbolTable));
        	for (String[] strings : listOfCommands) {
        		    
        		    strings = Arrays.stream(strings).filter(value1 -> !value1.isEmpty()).toArray(String[]::new);
            		Command c= ch.commandHashMap.get(strings[0].replace(",",""));
            		c.doCommand(strings, symbolTable);
    			}
            
        }	
		
	}

}
