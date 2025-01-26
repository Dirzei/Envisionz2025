package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.intellijthemes.FlatArcIJTheme;

public class GUIlauncher extends GUIframe {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6909382847126945923L;
	
	private static String defaultLookAndFeelName;
	
	public static void showSplash() {
		JWindow window = new JWindow();
		JPanel content = (JPanel)window.getContentPane();
		content.setBackground(Color.WHITE);
		//Set the window´s bounds, centering the window
		int width = 700;
		int height = 450;
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (screen.width - width) / 2;
		int y = (screen.height - height) / 2;
		window.setBounds(x, y, width, height);
		// Build the splash screen
		JLabel label = new JLabel(new ImageIcon(GUIlauncher.class.getResource("splash.png")));
		content.add(label, BorderLayout.CENTER);
		label.setLayout(new GridBagLayout());
		JLabel label2 = new JLabel(new ImageIcon(GUIlauncher.class.getResource("wait.gif")));
		content.add(label2, BorderLayout.SOUTH);
		Color oraRed = new Color(0, 0, 0, 255);
		content.setBorder(BorderFactory.createLineBorder(oraRed, 1));
		// Display it
		window.setVisible(true);
		window.toFront();
		ResourceLoader loader = new ResourceLoader(window);
		loader.execute();
	}
	
	public GUIlauncher(String title, ImageIcon icon) {
		super(title, icon);
	}
	
	public static void main(String[] args) {
		String javaVersion = System.getProperty("java.version");
		if (javaVersion.compareTo("1.8")<0) {
			JOptionPane.showMessageDialog(null, "WARNING: Application must be run "+
		                                        "with a 1.8 or higher Jave VM!",
		                                        "Java VM Warning",
		                                        JOptionPane.WARNING_MESSAGE);
			System.exit(0);
		}
		
		defaultLookAndFeelName = UIManager.getSystemLookAndFeelClassName();
		try {
			//UIManager.setLookAndFeel(defaultLookAndFellName);
			FlatLaf.registerCustomDefaultsSource("resources");
			FlatArcIJTheme.setup();
			
			UIManager.put("ToolTip.background", Color.ORANGE);
			UIManager.put("ToolTip.foreground", Color.BLACK);
		} catch (Exception ex1) {
			try {
				//UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
				UIManager.setLookAndFeel(defaultLookAndFeelName);
			} catch (Exception ex2) {
				JOptionPane.showMessageDialog(null, ex2, "UI Manager Exception",
						                                 JOptionPane.ERROR_MESSAGE);
				System.exit(0);
			}
		}
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				showSplash();
			}
		});
	}
}
