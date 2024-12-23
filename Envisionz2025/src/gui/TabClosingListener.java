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

import java.awt.event.MouseEvent;

import gui.utils.tabpaneClose.CloseListener;

public class TabClosingListener implements CloseListener {

	private GUIframe owner;

	public TabClosingListener(GUIframe ownerObj){
		this.owner = ownerObj;
		ownerObj = null;
	}

	public GUIframe getOwner(){ return this.owner; }

	@Override
	public void closeOperation(MouseEvent e, int overTabIndex) {
		TabClosingWorker worker = new TabClosingWorker(this.getOwner(), this.getOwner().getMainTabPane().getComponentAt(overTabIndex), overTabIndex);
		worker.execute();
		worker = null;
		
		/*Component component = this.getOwner().getMainTabPane().getComponentAt(overTabIndex);
		int indexToClose = overTabIndex;
		//this.owner.getGlassPane().setVisible(true);
		//this.owner.getGlassPane().setProgress(0);
		//this.owner.getGlassPane().setProgressText("Defrag DB file...");
		//this.owner.getGlassPane().setProgress(50);
		System.out.println(component);
		if (component instanceof MainPanel){
			((MainPanel)component).terminateAddDataTableModel();
			((MainPanel)component).terminateTableModel();
			((MainPanel)component).deleteAllCardsFromLayout();
		} else if (component instanceof MainModelsPanel){
			((MainModelsPanel)component).terminateAddDataTableModel();
			((MainModelsPanel)component).terminateTableModel();
			((MainModelsPanel)component).deleteAllCardsFromLayout();
		}
		//this.owner.getGlassPane().setProgress(100);
		this.owner.getMainTabPane().remove(indexToClose);*/
		
	}

}
