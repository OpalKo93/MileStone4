package Commands;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;

import Commands.AssignmentCommand;
import Commands.ConnectCommand;
import Commands.DefineVarCommand;
import Commands.DisconnectCommand;
import Commands.IfCommand;
import Commands.LoopCommand;
import Commands.OpenServerCommand;
import Commands.PrintCommand;
import Commands.ReturnCommand;
import ServerClient.DataReaderServer;
import ServerClient.MyClient;


public class CommandHandler {
	
	DataReaderServer server;
	MyClient client;
    public HashMap<String, Command> commandHashMap;
    public ConcurrentHashMap<String,Double> simulatorVars;
	public HashMap<String,String> symbltablBind;
	public HashMap<String,Double> symbolTable;
    
	public CommandHandler(DataReaderServer server,MyClient client,HashMap<String,Double> symbolTable ,HashMap<String,String> symbltablBind,ConcurrentHashMap<String,Double> simulatorVars)
	{
		 this.server = server;
		 this.client = client;
		 this.symbltablBind = symbltablBind;
		 this.simulatorVars = simulatorVars;
		 this.symbolTable = symbolTable;
		 commandHashMap = new HashMap<>();
         commandHashMap.put("var", new DefineVarCommand(this));
         commandHashMap.put("openDataServer", new OpenServerCommand(server));
         commandHashMap.put("connect", new ConnectCommand(client));
         commandHashMap.put("while",new LoopCommand(server,this));
         commandHashMap.put("if",new IfCommand(server,this));
         commandHashMap.put("print", new PrintCommand(this));
         commandHashMap.put("disconnect", new DisconnectCommand(client));
         commandHashMap.put("return", new ReturnCommand(client,this));
         commandHashMap.put("assignment", new AssignmentCommand(this,client));
         commandHashMap.put("sleep", new SleepCommand());
	}
	
	void setServer(DataReaderServer server)
	{
		this.server = server;
	}
   	
}
