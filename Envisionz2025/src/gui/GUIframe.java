package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.prefs.Preferences;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextPane;

import gui.datablock.MyDataBlock;
import gui.datablock.MyDataSingleton;
import gui.utils.GuiGlassPane;
import gui.utils.WindowClosingAdapter;
import gui.utils.tabpaneClose.CloseTabbedPane;

public class GUIframe extends MasterFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7117962170293374298L;
	
	public String WINDOW_FULL_ID = "WINDOW_FULL";
	public boolean DEFAULT_WINDOW_FULL = false;
	
	private GUImenubar menubar;
	private GUItoolbar toolbar;

	private JPanel toolPanel;
	
	private GuiGlassPane glassPane;
	private JSplitPane mainSplit;
	
	private static CloseTabbedPane tabpane;
	private static MyDataSingleton dataSingleton;
	
	/**
	 * Preferences Object
	 */
	private Preferences guiPreferences;
	
	public GUIframe(String title, ImageIcon icon) {		
		super(title, icon.getImage());		
		this.toolPanel = new JPanel();
		this.toolPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.getContentPane().add(toolPanel, BorderLayout.NORTH);
	}	
	
	public void setPreferences() {
		guiPreferences = Preferences.userRoot();
		if (guiPreferences.getBoolean(WINDOW_FULL_ID, DEFAULT_WINDOW_FULL)){
			this.setExtendedState(JFrame.MAXIMIZED_BOTH);
			this.setVisible(true);
		} else {
			showCentered(100);
		}
	}
	
	public void setFrameContents() {
		menubar = new GUImenubar(this);
		this.setJMenuBar(menubar.createMenubar());
		
		this.addWindowListener(new WindowClosingAdapter(this));
		
		this.setGeneralToolbar(new GUItoolbar(this));
		
		GUIframe.dataSingleton = MyDataSingleton.getInstance();
		
		this.glassPane = new GuiGlassPane(getJMenuBar(), getContentPane());
		this.glassPane.setOpaque(false);
		super.getRootPane().setGlassPane(this.glassPane);
		
		this.intitializeMainPanel();
	}
		
	private void intitializeMainPanel() {
		tabpane = new CloseTabbedPane();
		
		tabpane.addCloseListener(new TabClosingListener(this));
		
		this.mainSplit = new JSplitPane();
		this.mainSplit.setOneTouchExpandable(true);
		this.mainSplit.setOrientation(JSplitPane.VERTICAL_SPLIT);
		this.mainSplit.setDividerSize(10);
		
		JTextPane textPane = new JTextPane();
		JScrollPane textScroll = new JScrollPane(textPane);
		
		this.mainSplit.setTopComponent(tabpane);
		this.mainSplit.setBottomComponent(textScroll);
		this.mainSplit.getBottomComponent().setMinimumSize(new Dimension());
		this.mainSplit.getBottomComponent().setPreferredSize(new Dimension());
		this.mainSplit.setDividerLocation(1.);
		this.mainSplit.setResizeWeight(1.);
		
		super.getContentPane().add(this.mainSplit, BorderLayout.CENTER);
	}
	
	public void setGeneralToolbar(GUItoolbar toolbarIN){
		toolbar = toolbarIN;
		toolPanel.add(toolbar.createToolBars("General", false));
	}

	public GUImenubar getMyMenubar() { return menubar; }
	public GUItoolbar getMyToolbar() { return this.toolbar; }
	
	public static MyDataSingleton getDataSingleton() {
		return GUIframe.dataSingleton;
	}
	
	public static MyDataBlock getDataBlock() {
		if (GUIframe.dataSingleton.getDataStructure().size()>0) {
			return (MyDataBlock)GUIframe.dataSingleton.getDataStructure().get(getSelectedTabIndex());
		} else {
			return null;
		}
	}
	
	public static int getSelectedTabIndex() {
		return tabpane.getSelectedIndex();
	}
	
	@Override
	public GuiGlassPane getGlassPane() {
		return this.glassPane;
	}
	
	public void setBusy(boolean status, String from) {
		this.glassPane.setProgress(0);
		this.glassPane.setProgressText("");
		this.glassPane.setVisible(status);
	}
	
	public CloseTabbedPane getMainTabPane() { return GUIframe.tabpane; }
	
	public boolean isTabAlreadyExisting(String tabName) {
		if (this.getMainTabPane().getTabCount()>0) {
			for (int i=0; i<this.getMainTabPane().getTabCount(); i++) {
				if (this.getMainTabPane().getTitleAt(i).trim().toUpperCase().equals(tabName.trim().toUpperCase())) {
					return true;
				}
			}
		} else {
			return false;
		}
		return false;
	}
}
