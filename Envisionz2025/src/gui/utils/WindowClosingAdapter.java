package gui.utils;

import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import gui.GUIframe;
import gui.actions.ExitActionListener;

public class WindowClosingAdapter extends WindowAdapter {
	private Object ownedFrom;
	
	public WindowClosingAdapter(Object owner) {
		this(owner, false);
	}
	public WindowClosingAdapter(Object owner, boolean exitSystem) {
		this.ownedFrom = owner;
	}
	public void WindowClosing(WindowEvent event) {
		if (ownedFrom instanceof GUIframe) {
			ExitActionListener eA = new ExitActionListener(null, (GUIframe)ownedFrom);
			if (eA.close(new ActionEvent(this, event.getID(), "exit"))) {
				event.getWindow().setVisible(false);
				event.getWindow().dispose();
				System.exit(0);
			}
		}
	}
	public void windowActivated(WindowEvent e) {
		
	}
	public void windowGainedFocus(WindowEvent e) {
		if (ownedFrom instanceof JFrame) ((JFrame)ownedFrom).repaint();
	}
}
