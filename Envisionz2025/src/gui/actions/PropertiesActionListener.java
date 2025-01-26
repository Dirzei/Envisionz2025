package gui.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import gui.GUIcommands;
import gui.actions.panels.PropertiesPanel;
import gui.utils.ReadWriteProperties;

public class PropertiesActionListener extends AbstractAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7003217962713013950L;

	public GUIcommands ownerObj;
	
	public PropertiesActionListener(GUIcommands owner) {
		super("properties");
		this.ownerObj = owner;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		PropertiesPanel props = new PropertiesPanel();
		int value = JOptionPane.showConfirmDialog(this.ownerObj.getOwner(), props, "Properties", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
		if (value == JOptionPane.OK_OPTION) {
			ReadWriteProperties rwProps = new ReadWriteProperties();
			rwProps.writePropertiesFile(props.getPropertiesMap());
		} else {
			
		}
		
	}

}
