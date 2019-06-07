package Commands;

import java.util.HashMap;



public class PrintCommand implements Command {  //gets data from datareaderserver and prints it
	CommandHandler ch;
	
	public PrintCommand(CommandHandler ch) {
		this.ch=ch;
	}
	

	@Override
	public void doCommand(String[] s, HashMap<String, Double> symbolTable) {
		//print "done"
		if(!ch.symbltablBind.containsKey(s[1]))
			System.out.println(s[1].trim());
		else
			System.out.println(ch.simulatorVars.get(ch.symbltablBind.get(s[1].trim())));
		try {
			Thread.sleep(250);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
