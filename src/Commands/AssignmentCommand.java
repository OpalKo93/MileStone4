package Commands;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import ServerClient.Client;
import expression.ShuntingYard;


public class AssignmentCommand  implements Command {  //gets data from symboltable

	CommandHandler ch;
	ShuntingYard sy;
	Client c;
	
	public  AssignmentCommand(CommandHandler ch,Client c) {
		this.ch = ch;
		sy = new ShuntingYard();
		this.c = c;
	}
	@Override
	public void doCommand(String[] s, HashMap<String, Double> symbolTable) {
		 s[0] = s[0].trim();
		if(!s[2].equals("bind"))  // x = 7
		{
			if(ch.symbltablBind.containsKey(s[0])) {
				ch.simulatorVars.put(ch.symbltablBind.get(s[0]),sy.calc(s[s.length-1],ch).calculate());
					if(c.getSocket() != null) 
						c.setPathValue(ch.symbltablBind.get(s[0].trim()), sy.calc(s[s.length-1],ch).calculate());
			}
			else
			{
				symbolTable.put(s[0],sy.calc(s[s.length-1],ch).calculate());
			}
		}
		else     // x = bind simX
			ch.symbltablBind.put(s[0],s[s.length-1]);	
	}
}
