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
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Hashtable;

import javax.swing.AbstractButton;
import javax.swing.Action;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
//import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.border.LineBorder;

import gui.utils.Tokenizer;

public class GUItoolbar extends GUIcommands {

	private final int xyScaleValue = 30;

	private JToolBar toolbar;

	private JButton memButton;

	public GUItoolbar(GUIframe owner) {
		super(owner);
		setSpecialButtons(createSpecialButtons());
	}

	public JButton getMemoryButton(){
		return this.memButton;
	}

	public void setVisible(boolean visible){
		this.toolbar.setVisible(visible);
	}
	public boolean isVisible(){
		return this.toolbar.isVisible();
	}

	public JToolBar createToolBars(String name, boolean moveable) {
		String toolBars = getResourceString("toolbars");
		toolbar = new JToolBar();
		toolbar.setName(name);
		String label = getResourceString(toolBars + labelSuffix);
		toolbar.setName(label);
		toolbar.putClientProperty("JToolBar.isRollover", Boolean.TRUE);
		String[] toolKeys = Tokenizer.tokenize(getResourceString(toolBars));
		for (int i = 0; i < toolKeys.length; i++) {
			if (toolKeys[i].equals("-")) {
				toolbar.add(Box.createRigidArea(new Dimension(7, 7)));
			} else {
				toolbar.add(createTool(toolKeys[i]));
			}
		}
		toolbar.add(Box.createGlue());
		toolbar.setFloatable(moveable);

		return toolbar;

	}
	/*public JPanel createToolbar(String key, String label) {
		JPanel toolbarPanel = new JPanel();
		toolbarPanel.setName(label);
		toolbarPanel.putClientProperty("JToolBar.isRollover", Boolean.TRUE);
		String[] toolKeys = Tokenizer.tokenize(getResourceString(key));
		for (int i = 0; i < toolKeys.length; i++) {
			if (toolKeys[i].equals("-")) {
				toolbarPanel.add(Box.createHorizontalStrut(5));
			} else {
				toolbarPanel.add(createTool(toolKeys[i]));
			}
		}
		toolbarPanel.add(Box.createHorizontalGlue());
		new java.util.Properties ();
		return toolbarPanel;
	}*/
	public Component createTool(String key) {
		return createToolbarButton(key);
	}

	private int getXYscalValue(){
		return this.xyScaleValue;
	}

	protected Component createToolbarButton(String key) {
		//System.out.println(key);
		if (!key.equals("memory")){
			Object specialButton = getSpecialButton(key);
			if (specialButton instanceof JComponent && !(specialButton instanceof AbstractButton))
				return (JComponent) specialButton;
			else {
				AbstractButton b = null;
				URL url = getResource(key + imageToolSuffix);
				ImageIcon icon = new ImageIcon(url);
				Image image = icon.getImage().getScaledInstance(this.getXYscalValue(), this.getXYscalValue(), java.awt.Image.SCALE_SMOOTH);
				ImageIcon scaledIcon = new ImageIcon(image);
				if (specialButton instanceof AbstractButton) {
					b = (AbstractButton) specialButton;
					//b.setIcon(new ImageIcon(url));
					b.setIcon(scaledIcon);
				} else {
					//b = new JButton(new ImageIcon(url)) {
					b = new JButton(scaledIcon) {
						/**
						 * 
						 */
						private static final long serialVersionUID = 8485604456334902406L;

						public float getAlignmentY() {
							return 0.5f;
						}
					};
				}
				b.setMargin(new Insets(1, 1, 1, 1));
				b.setRequestFocusEnabled(false);
				b.setFocusable(false);
				if (specialButton == null) {
					String astr = getResourceString(key + actionSuffix);
					if (astr == null) astr = key;
					Action a = getAction(astr);

					if (a != null) {
						b.setActionCommand(astr);
						b.addActionListener(new EventRedirector(a));
						b.setEnabled(a.isEnabled());
						a.addPropertyChangeListener(createActionChangeListener(b));
					} else b.setEnabled(false);
				}
				// Tooltip
				String tip = getResourceString(key + tipSuffix);
				tip = (tip != null) ? tip : getResourceString(key + labelSuffix);
				String accel = getResourceString(key + accelSuffix);
				accel = (accel != null) ? " (" + accel + ")" : "";
				b.setToolTipText(tip + accel);
				//String tooltip = b.getToolTipText();

				return b;
			}
		} else {
			memButton = new JButton("xxxx / yyyyMB (xxx.yy%)");
			memButton.setBorderPainted(true);
			memButton.setFocusPainted(false);
			memButton.setBorder(new LineBorder(Color.BLACK));

			String astr = getResourceString(key + actionSuffix);
			if (astr == null) astr = key;
			Action a = getAction(astr);

			if (a != null) {
				memButton.setActionCommand(astr);
				memButton.addActionListener(new EventRedirector(a));
				memButton.setEnabled(a.isEnabled());
				memButton.addPropertyChangeListener(createActionChangeListener(memButton));
			} else {
				memButton.setEnabled(false);
			}
			return memButton;
		}
	}


	private Hashtable<String, JButton> createSpecialButtons(){
		Hashtable<String, JButton> hash = new Hashtable<String, JButton>();

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
