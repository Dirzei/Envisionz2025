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
package gui.utils;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

public class GuiProgressPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5986492732097821293L;

	private final static int PBAR_MIN = 0;
	private final static int PBAR_MAX = 100;

	private JProgressBar pbar;
	private JTextField tField;
	
	public GuiProgressPanel(){
		super();
		      
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		this.pbar = new JProgressBar();
		this.pbar.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.pbar.setMinimum(PBAR_MIN);
		this.pbar.setMaximum(PBAR_MAX);
		this.pbar.setVisible(true);
		this.pbar.setStringPainted(true);
		this.pbar.setValue(0);
		this.pbar.setMaximumSize(new Dimension(150, 20));
		this.pbar.setPreferredSize(new Dimension(150, 20));
		this.add(this.pbar);

		this.tField = new JTextField(40);
		this.tField.setOpaque(false);
		this.tField.setEditable(false);
		this.tField.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.add(this.tField);
	}
	
	public void updateProgressBar(int value){
		this.pbar.setValue(value);
		this.pbar.update(this.pbar.getGraphics());
	}
	
	public void updateTextField(String text){
		this.tField.setText(text);
		this.tField.revalidate();
	}
}
