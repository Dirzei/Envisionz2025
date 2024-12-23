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

import gui.main.split.MainSplitInterface;

import java.awt.Component;

import javax.swing.SwingWorker;


public class TabClosingWorker extends SwingWorker<Integer, Integer> {

	private GUIframe owner;
	private Component component;
	private int indexToClose;

	public TabClosingWorker(GUIframe ownerObj, Component comp, int index){
		owner = ownerObj;
		ownerObj = null;
		this.component = comp;
		comp = null;
		indexToClose = index;
	}

	@Override
	protected void done(){
		/*this.owner.getGlassPane().setVisible(false);
		this.owner.getGlassPane().setProgress(0);
		this.owner.getGlassPane().setProgressText("");*/
		this.owner.setBusy(false, "TabClosingWorker(done)");
		owner = null;
		component = null;
		Runtime.getRuntime().gc();
	}

	@Override
	protected Integer doInBackground() throws Exception {
		/*this.owner.getGlassPane().setVisible(true);
		this.owner.getGlassPane().setProgress(0);
		this.owner.getGlassPane().setProgressText("Defrag DB file...");
		this.owner.getGlassPane().setProgress(50);**/
		this.owner.setBusy(true, "TabClosingWorker(doInBackground)");
		this.owner.getGlassPane().setProgressText("Defrag DB file...");
		this.owner.getGlassPane().setProgress(50);
		if (this.component instanceof MainSplitInterface){
			int index = ((MainSplitInterface)this.component).getTabIndex();
			System.out.println("Index to close: "+index);
			//((MainPanel)this.component).terminateAddDataTableModel();
			((MainSplitInterface)this.component).terminateTableModel();
			((MainSplitInterface)this.component).deleteAllCardsFromLayout();
			GUIframe.getDataSingleton().getDataStructure().remove(index);
		} /*else if (this.component instanceof MainModelsPanel){
			((MainModelsPanel)this.component).terminateAddDataTableModel();
			((MainModelsPanel)this.component).terminateTableModel();
			((MainModelsPanel)this.component).deleteAllCardsFromLayout();
		}*/
		this.owner.getGlassPane().setProgress(100);
		this.owner.getMainTabPane().remove(indexToClose);

		return null;
	}
}
