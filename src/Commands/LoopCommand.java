package Commands;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import ServerClient.DataReaderServer;



public class LoopCommand extends ConditionCommands implements Command{
	
	DataReaderServer server;
	CommandHandler ch;
	public LoopCommand(DataReaderServer server, CommandHandler ch) {
		super(server,ch);
		this.server = server;
		this.ch = ch;
	}
	
	@Override
	public void doCommand(String[] str, HashMap<String, Double> symbolTable) {
		
		String[]  temp =str[1].split(" ");
		
		String variable = temp[0];
		String operator = temp[1];
		String value = temp[2];
		StringBuilder sb = new StringBuilder();
		for(String s: str)
		{
			sb.append(s+" ");
		}
		
		
        List<String[]> listOfCommands = new LinkedList<>();
        listOfCommands = convertArgumentsToListOfCommands(sb.toString());
        
        while(condition(variable,  operator, value))
        //{
        	//listOfCommands.forEach((String[] command)->ch.commandHashMap.get(command[0].replace(",",""))
    			//	.doCommand(command,symbolTable));
        //}	
        	for (String[] strings : listOfCommands) {
        		strings = Arrays.stream(strings).filter(value1 -> !value1.isEmpty()).toArray(String[]::new);
        		//System.out.println(strings[0]); 
        		Command c= ch.commandHashMap.get(strings[0].trim());
        		if(c== null)
        			c = ch.commandHashMap.get("assignment");
        		 c.doCommand(strings, symbolTable);
			}
        
	}
	
}

	 
	