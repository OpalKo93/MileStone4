package expression;

import java.util.HashMap;

import Commands.CommandHandler;


public class VarNumber implements Expression {

    private String variable;
	CommandHandler ch;
    
	public VarNumber(String value,CommandHandler ch) {
		this.variable=value;
		this.ch = ch;
	}
	
	public void setValue(String value){
		this.variable=value;
	}

	@Override
	public double calculate() {
		if((ch.symbltablBind.containsKey(variable))){
		return ch.simulatorVars.get((ch.symbltablBind.get(variable)));
	}
		return ch.symbolTable.get(variable);
}
}
