package expression;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import Commands.CommandHandler;


public class ShuntingYard {
	public  Expression calc(String exp,CommandHandler ch){
		
		Queue<String> queue = new LinkedList<String>();
		Stack<String> stack = new Stack<String>();
		Stack<Expression> stackExp = new Stack<Expression>();
		String lasttoken = "";
		
		if(exp.startsWith("-"))
			exp = "0" + exp;
		//String[] split = exp.split(("((?<=[><()])|(?=[><()]))|((?<=&&)|(?=&&))|((?<===)|(?===))|((?<=!=)|(?=!=))|(((?<=\\|\\|)|(?=\\|\\|)))"));
		String[] split = exp.split("(?<=[-+*/()])|(?=[-+*/()])");
		for (String s : split){
			s = s.trim();
			if(s.isEmpty())
				continue;

			if (isDouble(s)) {
				queue.add(s);
				lasttoken = s;
				continue;
			}
			
			if(s.matches("^[a-zA-Z0-9_]+$")) {
				queue.add(s);	
			}
			
			switch(s) {
		    case "/":
		    case "*":
		    case "(":
		        stack.push(s);
		        break;
		    case "+":
		    case "-":
		    	if(s.equals("-") && lasttoken.matches("^[[\\/\\*\\+\\-\\(]]$"))
				{
					stack.push("~");
					break;
				}
		    	while (!stack.empty() && (!stack.peek().equals("("))) {
		    		queue.add(stack.pop());
		    	}
		        stack.push(s);
		        break;
		    case ")":
		    	while (!stack.peek().equals("(")){
		    		queue.add(stack.pop());
		    	}
		    	stack.pop();
		        break;
			}
			lasttoken = s;
		} //end of loop
		
		while(!stack.isEmpty()){
			queue.add(stack.pop());
		}
		
		for(String str : queue) {
			if (isDouble(str)){
				stackExp.push(new Number(Double.parseDouble(str)));
			}
			else if (str.matches("^[a-zA-Z0-9_]+$")){
				stackExp.push(new VarNumber(str,ch));
			}
			else{
				Expression right = stackExp.pop();
				Expression left = null;
				
				if(str.charAt(0) != '~') {  
					left = stackExp.pop();
				}
			
				switch(str) {
			    case "/":
			    	stackExp.push(new Div(left, right));
			        break;
			    case "*":
			    	stackExp.push(new Mul(left, right));
			        break;
			    case "+":
			    	stackExp.push(new Plus(left, right));
			        break;
			    case "-":
			    	stackExp.push(new Minus(left, right));
			        break;
			    case "~":
			    	stackExp.push(new Mul(new Number(-1), right));
			    	break;
				}
			}
		}
	
		return stackExp.pop();
	}
	
	private static boolean isDouble(String val){
		try {
		    Double.parseDouble(val);
		    return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
		
	}


