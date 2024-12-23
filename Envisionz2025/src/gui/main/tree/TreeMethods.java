package gui.main.tree;

import java.util.Vector;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import gui.main.split.MainSplitInterface;

public interface TreeMethods {
	
	public MainSplitInterface getOwner();
	
	public TreeModel getModel();
	
	public void setSelectionRow(int row);
	
	public Object getLastSelectedPathComponent();
	
	public void addNewNode(int startPos, String parentName, String name, Vector<Object> p);
	
	public void addSubNodeToParent(String parent, String name);
	
	public String deleteNodes();
	
	public DefaultMutableTreeNode searchNode(String nodeStr);
	
	public TreePath getLeadSelectionPath();
	
	public void setSelectionPath(TreePath path);
}
