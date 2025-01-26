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

import gui.GUIcommands;


public class MemoryActionListener extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5397049925568475786L;

	public GUIcommands ownerObj;
	
	public MemoryActionListener(GUIcommands owner){
		super("memViewAction");
		this.ownerObj = owner;
	}
	
	@Override
	public void actionPerformed(ActionEvent paramActionEvent) {
		System.out.println("MemoryAction");
		Runtime.getRuntime().gc();
	}

}
