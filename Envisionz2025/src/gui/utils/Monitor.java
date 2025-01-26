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

import javax.swing.JButton;


public class Monitor extends Thread {

	//private volatile terminateRequest;
	private boolean terminateRequest;
	private final long profileInterval = 500;
	//private final int maxDepth = 5;

	private JButton displayButton;
	
	public Monitor(JButton button){
		this.displayButton = button;
	}
	
	public void run() {
		Management mngmt = new Management();
		try {
			while (!terminateRequest) {
				displayButton.setText(mngmt.getMemoryStatusString());
				//System.out.println(mngmt.getMemoryStatusString());
				Thread.sleep(profileInterval);
			}
		} catch (InterruptedException iex) {
			Thread.currentThread();
			Thread.interrupted();
			throw new RuntimeException("Profiler interrupted");
		}
	}
	
	/*public void run() {
		ThreadMXBean threadBean = ManagementFactory.getThreadMXBean();
		try {
			while (!terminateRequest) {
				long[] threadIds = threadBean.getAllThreadIds();
				ThreadInfo[] infs = threadBean.getThreadInfo(threadIds, maxDepth);

				for (ThreadInfo inf : infs) {
					StackTraceElement[] str = inf.getStackTrace();
					if (str == null)
						continue;
					StackTraceElement el = mostInterestingElement(str);
					System.out.println(el);
					if (el != null)
						incrementCountOf(el);
				}

				Thread.sleep(profileInterval);
			}
		} catch (InterruptedException iex) {
			Thread.currentThread().interrupted();
			throw new RuntimeException("Profiler interrupted");
		}
	}
	
	private StackTraceElement mostInterestingElement(StackTraceElement[] st) {
		for (int n = st.length,i=0; i < n; i++) {
			StackTraceElement el = st[i];
			if (el.getLineNumber() >= 0)
				return el;
		}
		return null;
	}

	private static class ProfileInfo {
		private int tickCount;
		
		public ProfileInfo(){
			System.out.println("Test");
		}
	}

	private Map<StackTraceElement,ProfileInfo> lineCounts =
			new HashMap<StackTraceElement,ProfileInfo>(500);

	private void incrementCountOf(StackTraceElement el) {
		ProfileInfo inf = lineCounts.get(el);
		if (inf == null) {
			lineCounts.put(el, inf = new ProfileInfo());
		}
		inf.tickCount++;
	}*/
}
