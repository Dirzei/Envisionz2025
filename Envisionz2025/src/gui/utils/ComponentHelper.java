package gui.utils;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;

public class ComponentHelper {

	public static void centerComponent(Component c) {
		centerComponent(c, null);
	}
	
	public static void centerComponent(Component c, Component p) {
		if (c!=null) {
			Dimension d = (p!=null ? p.getSize() : Toolkit.getDefaultToolkit().getScreenSize());
			c.setLocation(Math.max(0, (d.getSize().width - c.getSize().width)/2), 
					      Math.max(0, (d.getSize().height - c.getSize().height)/2));
		}
	}
	
	public static double sizeInMegaBytes(File file) {
		return (double) file.length() / (1024 * 1024);
	}
	
	public static double sizeInKiloBytes(File file) {
		return (double) file.length() / 1024;
	}
}
