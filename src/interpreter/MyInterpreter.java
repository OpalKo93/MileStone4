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
		simulatorVars.put("instrumentation/airspeed-indicator/indicated-speed-kt",(double)0);
		simulatorVars.put("instrumentation/altimeter/indicated-altitude-ft",(double)0);
		simulatorVars.put("instrumentation/altimeter/pressure-alt-ft",(double)0);
		simulatorVars.put("instrumentation/attitude-indicator/indicated-pitch-deg",(double)0);
		simulatorVars.put("instrumentation/attitude-indicator/indicated-roll-deg",(double)0);
		simulatorVars.put("instrumentation/attitude-indicator/internal-pitch-deg",(double)0);
		simulatorVars.put("instrumentation/attitude-indicator/internal-roll-deg",(double)0);
		simulatorVars.put("instrumentation/encoder/indicated-altitude-ft",(double)0);
		simulatorVars.put("instrumentation/encoder/pressure-alt-ft",(double)0);
		simulatorVars.put("instrumentation/gps/indicated-altitude-ft",(double)0);
		simulatorVars.put("instrumentation/gps/indicated-ground-speed-kt",(double)0);
		simulatorVars.put("instrumentation/gps/indicated-vertical-speed",(double)0);
		simulatorVars.put("instrumentation/heading-indicator/indicated-heading-deg",(double)0);
		simulatorVars.put("instrumentation/magnetic-compass/indicated-heading-deg",(double)0);
		simulatorVars.put("instrumentation/slip-skid-ball/indicated-slip-skid",(double)0);
		simulatorVars.put("instrumentation/turn-indicator/indicated-turn-rate",(double)0);
		simulatorVars.put("instrumentation/vertical-speed-indicator/indicated-speed-fpm",(double)0);
		simulatorVars.put("controls/flight/aileron",(double)0);
		simulatorVars.put("controls/flight/elevator",(double)0);
		simulatorVars.put("controls/flight/rudder",(double)0);
		simulatorVars.put("controls/flight/flaps",(double)0);
		simulatorVars.put("controls/engines/engine/throttle",(double)0);
		simulatorVars.put("engines/engine/rpm",(double)0);
		
		Filenames names;
		DataReaderServer server = new DataReaderServer(simulatorVars ,()-> new String[]{
			"instrumentation/airspeed-indicator/indicated-speed-kt",
			"instrumentation/altimeter/indicated-altitude-ft",
			"instrumentation/altimeter/pressure-alt-ft",
			"instrumentation/attitude-indicator/indicated-pitch-deg",
			"instrumentation/attitude-indicator/indicated-roll-deg",
			"instrumentation/attitude-indicator/internal-pitch-deg",
			"instrumentation/attitude-indicator/internal-roll-deg",
			"instrumentation/encoder/indicated-altitude-ft",
			"instrumentation/encoder/pressure-alt-ft",
			"instrumentation/gps/indicated-altitude-ft",
			"instrumentation/gps/indicated-ground-speed-kt",
			"instrumentation/gps/indicated-vertical-speed",
			"instrumentation/heading-indicator/indicated-heading-deg",
			"instrumentation/magnetic-compass/indicated-heading-deg",
			"instrumentation/slip-skid-ball/indicated-slip-skid",
			"instrumentation/turn-indicator/indicated-turn-rate",
			"instrumentation/vertical-speed-indicator/indicated-speed-fpm",
			"controls/flight/aileron",
			"controls/flight/elevator",
			"controls/flight/rudder",
			"controls/flight/flaps",
			"controls/engines/engine/throttle",
			"engines/engine/rpm",
		});
		
		CommandHandler ch = new CommandHandler(server,client,symbolTable,symbltbleBind,simulatorVars);
		Lexer l = new Lexer();
		Parser p = new Parser(ch,client);	
		
	
			for (String string : lines) {
				p.Parser(l.Lexer(string));
			}
		
		server.stop();
		
	  
	}
	public  void setClient(MyClient c) {
		this.client=c;
	}
}