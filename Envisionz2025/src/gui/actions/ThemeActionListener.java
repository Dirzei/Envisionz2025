package gui.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import gui.GUIcommands;
import gui.GUIframe;
import gui.actions.dialogs.ThemeActionPanel;

public class ThemeActionListener extends AbstractAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5009127183769583945L;
	
	private GUIcommands ownerObj;
	private int index;
	
	public ThemeActionListener(GUIcommands owner) {
		super("theme");
		this.index = 0;
		this.ownerObj = owner;
		owner = null;
	}
	
	public int getSelectedLayoutIndex() { return this.index; }
	public void setSelectedLayoutIndex(int ind) { this.index = ind; }
	public GUIframe getOwner() { return this.ownerObj.getOwner(); }
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//new ThemeActionDialog(this);
		JOptionPane.showMessageDialog(getOwner(), new ThemeActionPanel(this), "Look & Feel Selection", JOptionPane.PLAIN_MESSAGE);
	}

}
