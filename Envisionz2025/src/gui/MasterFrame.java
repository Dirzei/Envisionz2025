package gui;

import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPopupMenu;

import gui.utils.ComponentHelper;

public class MasterFrame extends  JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4794611330448287305L;

	protected JPopupMenu pop;
	
	public MasterFrame() {
		super();
		this.init();
	}
	
	public MasterFrame(GraphicsConfiguration gc) {
		super(gc);
		this.init();
	}
	
	public MasterFrame(String title) {
		super(title);
		this.init();
	}
	
	public MasterFrame(String title, GraphicsConfiguration gc) {
		super(title, gc);
		this.init();
	}

	public MasterFrame(String title, Image icon) {
		super(title);
		setIconImage(icon);
		this.init();
	}

	protected void init() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	}
	
	public void setSizeAndLocation(int reduction) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize(screenSize.width-reduction, screenSize.height-reduction);
	}
	
	public void showCentered() {
		ComponentHelper.centerComponent(this);
		this.setVisible(true);
	}
	
	public void showCentered(int reduction) {
		this.setSizeAndLocation(reduction);
		ComponentHelper.centerComponent(this);
		this.setVisible(true);
	}

	public void runInitializer(Runnable r) {
		Thread t = new Thread(r);
		t.start();
	}
}
