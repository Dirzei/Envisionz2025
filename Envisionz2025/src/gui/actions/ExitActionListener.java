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
package gui.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import gui.GUIframe;
import gui.GUIcommands;

public class ExitActionListener extends AbstractAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1484359407258406480L;
	
	public Object ownerObj;
	
	public ExitActionListener(GUIcommands owner, GUIframe owner2){
		super("exit");
		if (owner!=null){
			this.ownerObj = owner;
			owner = null;
		} else {
			this.ownerObj = owner2;
			owner2 = null;
		}
	}
	
	public void actionPerformed(ActionEvent event) {
		if (ownerObj!=null) {
			if (ownerObj instanceof GUIframe) {
				if (JOptionPane.showConfirmDialog((GUIframe)ownerObj, "Are you sure to close this application?", "Really closing?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)==JOptionPane.YES_OPTION){
					System.exit(0);
				}				
			} else if (ownerObj instanceof GUIcommands) {
				if (JOptionPane.showConfirmDialog(((GUIcommands)ownerObj).getOwner(), "Are you sure to close this application?", "Really closing?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)==JOptionPane.YES_OPTION){
					System.exit(0);
				}
			}
		}
	}
	
	public boolean close(ActionEvent evt) {	
		if (ownerObj !=null) {
			if (ownerObj instanceof GUIframe) {
				if (JOptionPane.showConfirmDialog((GUIframe)ownerObj, "Are you sure to close this application?", "Really closing?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)==JOptionPane.YES_OPTION){
					return true;
				} else {
					return false;
				}				
			} else if (ownerObj instanceof GUIcommands) {
				if (JOptionPane.showConfirmDialog(((GUIcommands)ownerObj).getOwner(), "Are you sure to close this application?", "Really closing?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)==JOptionPane.YES_OPTION){
					return true;
				} else {
					return false;
				}
			} else {
				if (JOptionPane.showConfirmDialog(null, "Are you sure to close this application?", "Really closing?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)==JOptionPane.YES_OPTION){
					return true;
				} else {
					return false;
				}
			}
		} else {
			if (JOptionPane.showConfirmDialog(null, "Are you sure to close this application?", "Really closing?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)==JOptionPane.YES_OPTION){
				return true;
			} else {
				return false;
			}
		}
	}
}
