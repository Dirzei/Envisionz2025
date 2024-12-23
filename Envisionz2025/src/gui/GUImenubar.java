/* ============================================================
 * ENVISIONZ : A Data Visualization Tool for large sets of data
 * ============================================================
 * 
 * (C)opyright 2018-2020, by Dirk Zeitz.  All rights reserved.
 * 
 * dirk.zeitz@yahoo.com
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 * [Oracle and Java are registered trademarks of Oracle and/or its affiliates. 
 * Other names may be trademarks of their respective owners.]
 * 
 * If you do not wish to be bound by the terms of the GPL, an alternative
 * commercial license can be purchased.  For details, please contact the author
 * 
 * 
 */
package gui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.Hashtable;

import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import gui.utils.Tokenizer;

public class GUImenubar extends GUIcommands {
	
	public GUImenubar(GUIframe owner) {
		super(owner);
		setSpecialItems(createSpecialItems());	
	}
	/**
	 * This is the menubar creator method.
	 */
	public JMenuBar createMenubar(){
		JMenuBar mb = new JMenuBar();
		
		String[] menuKeys = Tokenizer.tokenize(getResourceString("menubar"));
		for (int i = 0; i < menuKeys.length; i++) {
			String[] itemKeys = Tokenizer.tokenize(getResourceString(menuKeys[i]));
			JMenu m = createMenu(menuKeys[i], itemKeys);
			if (m != null) mb.add(m);
		}
		return mb;
	}
	public JMenu createMenu(String key, String[] itemKeys) {
		JMenu menu = new JMenu(getResourceString(key + labelSuffix));
		for (int i = 0; i < itemKeys.length; i++) {
			if (itemKeys[i].equals("-")) {
				menu.addSeparator();
			} else {
				JMenuItem mi = createMenuItem(itemKeys[i]);
				menu.add(mi);
			}
		}
		URL url = getResource(key + imageMenuSuffix);
		if (url != null) {
			menu.setHorizontalTextPosition(JButton.RIGHT);
			menu.setIcon(new ImageIcon(url));
		}
		return menu;
	}
	public JMenuItem createMenuItem(String cmd) {
		String subMenu = getResourceString(cmd + menuSuffix);
		if (subMenu != null) {
			String[] itemKeys = Tokenizer.tokenize(subMenu);
			return createMenu(cmd, itemKeys);
		} else {
			JMenuItem mi;
			Object specialItem = getSpecialItem(cmd);
			if (specialItem instanceof JMenuItem) {
				mi = (JMenuItem) specialItem;
				mi.setText(getResourceString(cmd + labelSuffix));
			} else
				mi = new JMenuItem(getResourceString(cmd + labelSuffix));
			URL url = getResource(cmd + imageMenuSuffix);
			if (url != null) {
				mi.setHorizontalTextPosition(JButton.RIGHT);
				mi.setIcon(new ImageIcon(url));
			}
			String accel = getResourceString(cmd + accelSuffix);
			if (accel != null) {
				try {
					int mask = 0;
					if (accel.startsWith("CTRL")) {
						mask += ActionEvent.CTRL_MASK;
						accel = accel.substring(5);
					}
					if (accel.startsWith("SHIFT")) {
						mask += ActionEvent.SHIFT_MASK;
						accel = accel.substring(6);
					}
					if (accel.startsWith("ALT")) {
						mask += ActionEvent.ALT_MASK;
						accel = accel.substring(4);
					}
					int key = KeyEvent.class.getField("VK_" + accel).getInt(null);
					mi.setAccelerator(KeyStroke.getKeyStroke(key, mask));
				} catch (Exception e) {
					// ignore
				}
			}
			String mnemonic = getResourceString(cmd + mnemonicSuffix);
			if (mnemonic != null && mnemonic.length() > 0)
				mi.setMnemonic(mnemonic.toCharArray()[0]);
			if (specialItem == null) {
				String astr = getResourceString(cmd + actionSuffix);
				if (astr == null) {
					astr = cmd;
				}
				mi.setActionCommand(astr);
				Action a = getAction(astr);
				if (a != null) {
					mi.addActionListener(new EventRedirector(a));
					a.addPropertyChangeListener(createActionChangeListener(mi));
					mi.setEnabled(a.isEnabled());
				} else {
					mi.setEnabled(false);
				}
			}
			//mi.setHorizontalTextPosition(SwingConstants.LEFT);
			//mi.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
			putInMenuItem(cmd, mi);
			return mi;
		}
	}

	private Hashtable<String, JMenuItem> createSpecialItems(){
		Hashtable<String, JMenuItem> hash = new Hashtable<String, JMenuItem>();
		
		/*hash.put("windowslaf", new WindowsLafListener(getOwner(), true, 
				 				false, "windowslaf").getCheckBox());
		hash.put("metallaf", new MetalLafListener(getOwner(), true, 
 				false, "metallaf").getCheckBox());
		hash.put("motiflaf", new MotifLafListener(getOwner(), true, 
 				false, "motiflaf").getCheckBox());*/

		return hash;
	}
	
	/** 
	 * @author Dirk Zeitz
	 */
	class EventRedirector implements ActionListener {
		private Action action; 
		
		public EventRedirector(Action action){
			this.action = action;
		}
		
	    public void actionPerformed(ActionEvent e) {
	        action.actionPerformed(e);
	    }
	}

}
