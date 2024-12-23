package gui.datablock;

import java.util.ArrayList;
import java.util.List;

public class EPDSfileContent {

	private int type;
	private int date;
	private int time;
	private List<String> runNamesList = new ArrayList<String>();
	private List<Integer> runList = new ArrayList<Integer>();
	private List<Boolean> convergedList = new ArrayList<Boolean>();
	
	String dirName;
	String masterPath;
	
	/**
	 * 0 MultiRun
	 * 1 SingleRun
	 * @param value
	 */
	public void setType(int value) { this.type = value; }
	public int getType() { return this.type; }
	
	public void setDate(int value) { this.date = value; }
	public int getDate() { return this.date; }

	public void setTime(int value) { this.time = value; }
	public int getTime() { return this.time; }

	public void addRunNameToList(String value) { this.runNamesList.add(value); }
	public void setRunNamesList(List<String> valueList) { this.runNamesList = valueList; }
	public List<String> getRunNamesList() { return this.runNamesList; }
	
	public void addRunToList(int value) { this.runList.add(value); }
	public void setRunList(List<Integer> valueList) { this.runList = valueList; }
	public List<Integer> getRunList() { return this.runList; }

	public void addConvergedToList(boolean value) { this.convergedList.add(value); }
	public void setConvergedList(List<Boolean> valueList) { this.convergedList = valueList; }
	public List<Boolean> getConvergedList() { return this.convergedList; }

	public void setDirName(String name) { this.dirName = name; }
	public String getDirName() { return this.dirName; }
	
	public void setMasterPath(String name) { this.masterPath = name; }
	public String getMasterPath() { return this.masterPath; }
	
}
