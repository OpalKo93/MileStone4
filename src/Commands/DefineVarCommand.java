package Commands;

import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import expression.ShuntingYard;


public class DefineVarCommand  implements Command {

	CommandHandler ch;
	ShuntingYard sy;
	
	public DefineVarCommand(CommandHandler ch) {
		sy = new ShuntingYard();
		this.ch = ch;
	}
	
	
	@Override
	public void doCommand(String[] s, HashMap<String, Double> symbolTable) {
	
		if(s.length == 2)    // for var x;
			symbolTable.put(s[1],null);

		else if(s[3].contains("bind") || s[2].contains("bind")) {   
			     // var x = bind abc/d/g/g/ 
			ch.symbltablBind.put(s[1].trim(),(s[s.length-1].substring(5, s[s.length-1].length())).trim());
			   
		 }
		else if (s[2].equals("=")) {     //var x = 3+5
			 symbolTable.put(s[1].trim(), (sy.calc(s[3],ch)).calculate());
		}
	}

	
}