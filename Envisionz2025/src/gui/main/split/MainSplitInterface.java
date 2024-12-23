package gui.main.split;

import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JPanel;

import gui.GUIframe;
import gui.datablock.MyDataBlock;
import gui.main.tree.TreeMethods;

public interface MainSplitInterface {

	public TreeMethods getTree();

	public int getTabIndex();

	public GUIframe getOwner();
	
	public MyDataBlock getDataBlock();
	
	public List<String> getDblColumnNames(String tableName);

	public List<String> getAllColumnNames(String tableName);

	public List<String> getGeneratedColumnNames(String tableName);

	public List<String> getGeneratedColumnExpr(String tableName);

	public boolean containsPanel(String name);
	
	public JPanel getCardMasterPanel();
	
	public void deleteAllCardsFromLayout();
	
	public void deleteCardFromLayout(String name);
	
	public void terminateTableModel();
	
	public void showMainTree();

	public void addSubNodeToParent(String parent, String name);
	
	public int getCounter();
	
	public void increaseCounter();

	public void setDividerLocation(int paramInt, MouseEvent event, int type);

}
