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


import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.text.DecimalFormat;

import javax.swing.JTextField;

public class Management {

	private DecimalFormat df = new DecimalFormat("#.##");
	private MemoryMXBean memory;
	//private Locale currentLocale;
	//private NumberFormat format;
	
	public Management() {
		memory = ManagementFactory.getMemoryMXBean();
    	//currentLocale = Locale.getDefault();
    	//format = NumberFormat.getInstance(currentLocale);
	}

	public String getHeapMemory(int unit){
		long initial = 0L; 
		long current = 1L;
		long maximum = 1L;
		double percent;
		String ret = "";
		String unitString = "";
		
		
		switch (unit){
		case 0: {
			initial = memory.getHeapMemoryUsage().getInit();
			current = memory.getHeapMemoryUsage().getUsed();
			maximum = memory.getHeapMemoryUsage().getMax();
			unitString = "B";
			break;
		}
		case 1: {
			initial = memory.getHeapMemoryUsage().getInit()/1024;
			current = memory.getHeapMemoryUsage().getUsed()/1024;
			maximum = memory.getHeapMemoryUsage().getMax()/1024;
			unitString = "KB";
			break;
		}
		case 2: {
			initial = memory.getHeapMemoryUsage().getInit()/(1024*1024);
			current = memory.getHeapMemoryUsage().getUsed()/(1024*1024);
			maximum = memory.getHeapMemoryUsage().getMax()/(1024*1024);		
			unitString = "MB";
			break;			
		}
		}
		percent = ((double)current/(double)maximum);
		ret = "Init/Current/Maximum (percent) "+initial+"/"+current+"/"+maximum+" "+unitString+" ("+String.valueOf(df.format(percent))+"%)";
		return ret;
	}
	
	public String getUsedPercentage(){
		//long initial = memory.getHeapMemoryUsage().getInit(); 
		long current = memory.getHeapMemoryUsage().getUsed();
		long maximum = memory.getHeapMemoryUsage().getMax();
		double percent = ((double)current/(double)maximum)*100d;
		System.out.println("Available Heap Memory (total): "+maximum/(1024*1024)+" MB"+" : current: "+(current/(1024*1024))+" MB");
		String ret = String.valueOf(df.format(percent))+"%";
		return ret;
	}
	
	public String getMemoryStatusString(){
		long current = memory.getHeapMemoryUsage().getUsed();
		long maximum = memory.getHeapMemoryUsage().getMax();
		double percent = ((double)current/(double)maximum)*100d;
		String retStr = new StringBuilder().append("  ").append(current/(1024*1024)).append(" / ").append(maximum/(1024*1024)).append("MB (").append(df.format(percent)).append("%)  ").toString();
		return retStr;
	}
	
	public int getUsedPercentageInt(){
		//long initial = memory.getHeapMemoryUsage().getInit(); 
		long current = memory.getHeapMemoryUsage().getUsed();
		long maximum = memory.getHeapMemoryUsage().getMax();
		int percent = (int)(current/maximum*100);
		return percent;
	}
	
	public long getFreeHeapMemorySize(){
		long current = memory.getHeapMemoryUsage().getUsed();
		long maximum = memory.getHeapMemoryUsage().getMax();
		return (maximum-current);
		
	}
	
	public void updateProgress(JTextField progressField){
		progressField.setText(this.getUsedPercentage());
	}
}
