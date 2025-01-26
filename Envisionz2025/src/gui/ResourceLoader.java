package gui;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JWindow;
import javax.swing.SwingWorker;

import gui.utils.Monitor;

public class ResourceLoader extends SwingWorker<Object, Object> {

	private JWindow window;
	private final GUIlauncher app = new GUIlauncher("Test", new ImageIcon(GUIlauncher.class.getResource("eye.png")));
	
	public ResourceLoader(JWindow window) {
		this.window = window;
	}
	
	@Override
	protected Object doInBackground() throws Exception {
		//Wait a little while, maybe whiler loading resources
		try {
			Thread.sleep(2000);
		} catch (Exception e) {
			
		}
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		final int width = (int)(dimension.getWidth()/2.);
		final int height = (int)(dimension.getHeight()/2);
		
		//final GUIlauncher app = new GUIlauncher("Test", new ImageIcon(GUIlauncher.class.getResources("eye.png")));
		app.runInitializer(new Runnable(){
			public void run() {
				app.pack();
				app.setSize(width, height);
				app.setFrameContents();
				app.setPreferences();
				
				Monitor myThread = new Monitor(app.getMyToolbar().getMemoryButton());
				myThread.start();
			}
		});
		return null;
	}
	
	public GUIframe getFrame() {
		return this.app;
	}
	
	@Override
	protected void done() {
		window.setVisible(false);
	}
}
