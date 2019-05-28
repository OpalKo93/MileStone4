package Commands;

import java.util.HashMap;

import ServerClient.Client;
import expression.ShuntingYard;
import interpreter.MyInterpreter;


public class ReturnCommand implements Command {

	Client c;
	ShuntingYard sy;
	CommandHandler ch;
	
	public ReturnCommand(Client c,CommandHandler ch) {
		this.c = c;
		this.ch = ch;
		sy = new ShuntingYard();
	}
	
	@Override
	public void doCommand(String[] s, HashMap<String, Double> symbolTable) {
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=1;i<s.length;i++)
			sb.append(s[i]);
		
		MyInterpreter.returnValue =  sy.calc(sb.toString(),ch).calculate();
			}
}
