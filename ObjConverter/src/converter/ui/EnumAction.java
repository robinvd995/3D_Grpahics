package converter.ui;

public enum EnumAction {

	NEW("New", "newfile"),
	OPEN("Open", "openfile"),
	SAVE("Save", "savefile"),
	EXIT("Exit", "exit");
	
	private final String actionName;
	private final String actionCommand;
	
	private EnumAction(String actionName, String actionCommand){
		this.actionName = actionName;
		this.actionCommand = actionCommand;
	}
	
	public String getActionCommand(){
		return actionCommand;
	}
	
	public String getActionName(){
		return actionName;
	}
	
	public static EnumAction getActionFromCommand(String command){
		for(EnumAction action : values()){
			if(command.equals(action.getActionCommand())){
				return action;
			}
		}
		return null;
	}
}
