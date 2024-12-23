package gui;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Hashtable;

import javax.swing.AbstractButton;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenuItem;
import javax.swing.text.TextAction;

import gui.actions.ExitActionListener;
import gui.actions.ResourceReader;

public class GUIcommands extends ResourceReader {
	private Hashtable<Object, Action> commands;
	private Hashtable<String, JMenuItem> specialItems;
	private Hashtable<String, JButton> specialButtons;
	private Hashtable<String, JMenuItem> menuItems;

	private ExitActionListener exit = new ExitActionListener(this, null);
	private TestFuncActionListener testFunc = new TestFuncActionListener();
	private ThemeActionListener theme = new ThemeActionListener(this);
	private MemoryActionListener memViewAction = new MemoryActionListener(this);
	private PropertiesActionListener propAction = new PropertiesActionListener(this);

	private Action[] emptyActions = { };
	//private Action[] defaultActions = { exit };
	private Action[] defaultActions = { exit, testFunc, theme, memViewAction, propAction };
	
	private GUIframe ownedFrom;

	public GUIcommands(GUIframe owner){
		super("resources.menu");
		commands = new Hashtable<Object, Action>();
		menuItems = new Hashtable<String, JMenuItem>();
		this.ownedFrom = owner;
		Action[] actions = getActions();
		for (int i=0; i<actions.length; i++){
			Action action = actions[i];
			commands.put(action.getValue("Name"), action);
		}
	}
	public GUIframe getOwner(){ return this.ownedFrom; }
	public void setCommands(Hashtable<Object, Action> newCommands){ this.commands = newCommands; }
	public void setSpecialItems(Hashtable<String, JMenuItem> newSpecialItems){ this.specialItems = newSpecialItems; }
	public void setSpecialButtons(Hashtable<String, JButton> newSpecialButtons){ this.specialButtons = newSpecialButtons; }
	/**
	 * Returns all possible standard actions of the application.
	 */
	public Action[] getActions(){ return TextAction.augmentList(emptyActions, defaultActions); }
	/**
	 * Returns specified action of Action[].
	 * @param string Action name
	 */
	public Action getAction(String string){ return (Action)commands.get(string); }
	/**
	 * @return Hashtable
	 */
	public Hashtable<String, JMenuItem> getSpecialItems(){	return specialItems; }
	/**
	 * @param name name of item to be returned from Hashtable.
	 * @return Object
	 */
	public Object getSpecialItem(String name){ return specialItems.get(name); }
	/**
	 * @return Hashtable
	 */
	public Hashtable<String, JButton> getSpecialButtons(){ return this.specialButtons; }
	/**
	 * @param name name of button to be returned from Hashtable.
	 * @return Object
	 */
	public Object getSpecialButton(String name){ return specialButtons.get(name); }

	public void putInMenuItem(String cmd, JMenuItem mi){ this.menuItems.put(cmd, mi); }

	public Hashtable<String, JMenuItem> getMenuItems(){ return this.menuItems; }

	public void update(){ updateMenus(); }

	private void updateMenus(){
		((JCheckBoxMenuItem)this.getSpecialItem("showSystemErrWindow")).setEnabled(true);
	}

	public void setInitialMenuState(String type){

	}

	protected PropertyChangeListener createActionChangeListener(AbstractButton b) {
		return new ActionChangeListener(b);
	}
	/**
	 * @author Dirk Zeitz
	 */
	class ActionChangeListener implements PropertyChangeListener {
		private AbstractButton button;
		public ActionChangeListener(AbstractButton button){
			this.button = button;
		}
	    public void propertyChange(PropertyChangeEvent evt) {
	        String string = evt.getPropertyName();
	        if (evt.getPropertyName().equals("Name")&&button instanceof JMenuItem){
	        	button.setText("changeValue");
	        } else if (string.equals("enabled")){
	        	Boolean var_bool = (Boolean)evt.getNewValue();
	        	button.setEnabled(var_bool.booleanValue());
	        }
	    }
	}

	/*public void addMainTab(String paramString, Component paramComponent){
		this.getOwner().getMainTabPane().addTab(paramString, paramComponent);
	}*/
}
