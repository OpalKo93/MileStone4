package expression;

import Commands.Command;

public class CommandExpression implements Expression {

	Command c;
	
	public CommandExpression(Command c) {
		this.c = c;
	}

	@Override
	public double calculate() {
		//c.doCommand(str)
		return 0;
	}
	

}
