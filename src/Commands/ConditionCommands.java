package Commands;

import java.security.PublicKey;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import ServerClient.DataReaderServer;
import expression.ShuntingYard;


public abstract class ConditionCommands{
	
	 DataReaderServer server;
	 ShuntingYard sy;
	 CommandHandler ch;
	    
	    public ConditionCommands(DataReaderServer server,CommandHandler ch)
	    {
	    	this.server = server;
	    	sy = new ShuntingYard();
	    	this.ch = ch;
	    }

 protected List<String[]> convertArgumentsToListOfCommands(String string) {
	
		
	 List<String> listOfCommands2 = new LinkedList<>();
	 List<String> listOfLines = Arrays.asList(string.split("/n"));
	 listOfCommands2 =  listOfLines.subList(1,listOfLines.size()-2);

     return listOfCommands2
             .stream()
             .map(line -> line.split("((?<=[a-zA-Z0-9={}])\\s(?=[a-zA-Z0-9={}(]))|[\\n\\r]+|((?<=[{}])|(?=[{}]))|((?<=[=])|(?=[=]))"))
             .collect(Collectors.toList());
}

 protected boolean condition(String variable, String operator,String value) {
		
		switch(operator) {
		
			case "<":  return sy.calc(variable,ch).calculate() < sy.calc(value, ch).calculate();
			case ">":  return sy.calc(variable,ch).calculate() > sy.calc(value,ch).calculate();
			case "<=": return sy.calc(variable,ch).calculate() <= sy.calc(value, ch).calculate();
            case ">=": return sy.calc(variable,ch).calculate() >= sy.calc(value, ch).calculate();
            case "==": return sy.calc(variable,ch).calculate() == sy.calc(value, ch).calculate();
            case "!=": return sy.calc(variable,ch).calculate() != sy.calc(value, ch).calculate();
               
            default:
                return false;
        }
			
		}
		
}




