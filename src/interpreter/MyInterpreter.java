package interpreter;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

import Commands.CommandHandler;
import ServerClient.DataReaderServer;
import ServerClient.Filenames;
import ServerClient.MyClient;



public class MyInterpreter {
	
	public static Double returnValue;
	public static  MyClient client;
	
	public static  void interpret(String[] lines){
		
		
		HashMap<String,Double> symbolTable = new HashMap<>();   // x = 5
		HashMap<String,String> symbltbleBind = new HashMap<>();  // x = bind simX
		ConcurrentHashMap<String,Double> simulatorVars = new ConcurrentHashMap<>();  // simX = 5
		simulatorVars.put("controls/flight/speedbrake",(double)0);
		simulatorVars.put("controls/engines/current-engine/throttle",(double)0);
		simulatorVars.put("instrumentation/heading-indicator/offset-deg",(double)0);
		simulatorVars.put("instrumentation/airspeed-indicator/indicated-speed-kt",(double)0);
		simulatorVars.put("instrumentation/attitude-indicator/indicated-roll-deg",(double)0);
		simulatorVars.put("instrumentation/attitude-indicator/internal-pitch-deg",(double)0);
		simulatorVars.put("controls/flight/rudder",(double)0);
		simulatorVars.put("controls/flight/aileron",(double)0);
		simulatorVars.put("controls/flight/elevator",(double)0);
		simulatorVars.put("instrumentation/altimeter/indicated-altitude-ft",(double)0);
		
		Filenames names;
		DataReaderServer server = new DataReaderServer(simulatorVars ,()-> new String[]{
		"controls/flight/speedbrake",
		"controls/engines/current-engine/throttle",
		"instrumentation/heading-indicator/offset-deg",
		"instrumentation/airspeed-indicator/indicated-speed-kt",
		"instrumentation/attitude-indicator/indicated-roll-deg",
		"instrumentation/attitude-indicator/internal-pitch-deg",
		"controls/flight/rudder",
		"controls/flight/aileron",
		"controls/flight/elevator",
		"instrumentation/altimeter/indicated-altitude-ft"
		});
		
		CommandHandler ch = new CommandHandler(server,client,symbolTable,symbltbleBind,simulatorVars);
		Lexer l = new Lexer();
		Parser p = new Parser(ch,client);	
		
	
			for (String string : lines) {
				p.Parser(l.Lexer(string));
			}
		
		server.stop();
		
		
	  //return returnValue.intValue();
	  
	}
	public  void setClient(MyClient c) {
		this.client=c;
	}
}