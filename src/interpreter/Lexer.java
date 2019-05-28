package interpreter;


public class Lexer {
	
	private boolean inLoop;
	
	private StringBuilder whileBlock;
	
	public Lexer() {
		inLoop = false;
		whileBlock = new StringBuilder();
	}
	
	public String[] Lexer(String line)
	{
		String[] sb;
		String line1 = line.trim().replaceAll(" +", " ");
		
		if(line1.startsWith("while") || line1.startsWith("if")) {
			inLoop = true;
			whileBlock.append(line+ " /n ");
		}
		else if(inLoop ==true){
		
			whileBlock.append(line1+" /n ");
			
			if(line1.endsWith("}")) {
				inLoop = false;
				sb = whileBlock.toString().split("((?<=[a-zA-Z0-9={}])\\s(?=[a-zA-Z0-9={}(]))|[\\n\\r]+|((?<=[{}])|(?=[{}]))|((?<=[=])|(?=[=]))");
				whileBlock = new StringBuilder();
				return sb;
			}
				
		}
		
		return line1.split("((?<=[a-zA-Z0-9={}])\\s(?=[a-zA-Z0-9={}(]))|[\\n\\r]+|((?<=[{}])|(?=[{}]))|((?<=[=])|(?=[=]))");
	}
	

}
