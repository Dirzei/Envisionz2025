package gui.actions.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import gui.utils.ReadWriteProperties;

public class PropertiesPanel extends JPanel implements ActionListener, FocusListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8590425260777394480L;

	private final int xyScaleValue = 20;
	
	private String PROGRAM_DIR_CMD = "PROGRAM_DIR";
	private String USR_HOME_CMD = "USER_HOME";
	private String LAST_DIR_CMD = "LAST_DIR";
	private String SELECT_PD_CMD = "SELECT_PROGRAM_DIR";
	private String SELECT_UH_CMD = "SELECT_USERHOME_DIR";
	private String SELECT_LD_CMD = "SELECT_LAST_DIR";
	private String SELECT_DBTEMP_CMD = "DBTEMP_DIR";
	private String DB_TRACE_CMD = "DB_TRACE";
	private String PHOBOS_DIR_CMD = "PHOBOS_CONFIG_DIR";
	
	private JTextField programDirString = new JTextField(40);
	private JTextField tempDBString = new JTextField(40);
	private JTextField userHomeDirString = new JTextField(40);
	private JTextField lastDirDirString = new JTextField(40);
	private JTextField phobosFileDirString = new JTextField(40);
	
	private JComboBox<Object> traceOnOff;
	
	private HashMap<String, String> map;
	
	public PropertiesPanel() {
		super();
		this.setLayout(new BorderLayout());
		ReadWriteProperties rwProps = new ReadWriteProperties();
		this.map = rwProps.getProperties();
		
		this.add(setupPanel(), BorderLayout.CENTER);
	}
	
	public HashMap<String, String> getPropertiesMap() { return this.map; }
	
	private JPanel setupPanel() {
		JPanel panel = new JPanel();
		
		JLabel label1 = new JLabel("Program directory ");
		programDirString.setEditable(false);
		programDirString.setEnabled(false);
		if (map.containsKey(PROGRAM_DIR_CMD)) {
			programDirString.setText(map.get(PROGRAM_DIR_CMD));
		} else {
			programDirString.setText("");
		}
		programDirString.setActionCommand(PROGRAM_DIR_CMD);
		programDirString.setName(PROGRAM_DIR_CMD);
		programDirString.addActionListener(this);
		programDirString.addFocusListener(this);
		
		JLabel label1A = new JLabel("DB temp directory ");
		tempDBString.setEditable(false);
		tempDBString.setEnabled(false);
		if (map.containsKey(SELECT_DBTEMP_CMD)) {
			tempDBString.setText(map.get(SELECT_DBTEMP_CMD));
		} else {
			tempDBString.setText("");
		}
		tempDBString.setActionCommand(SELECT_DBTEMP_CMD);
		tempDBString.setName(SELECT_DBTEMP_CMD);
		tempDBString.addActionListener(this);
		tempDBString.addFocusListener(this);

		JLabel label1B = new JLabel("DB Trace on/off ");
		List<String> dummyList = new ArrayList<String>();
		dummyList.add("off");
		dummyList.add("on");
		DefaultComboBoxModel<Object> model = new DefaultComboBoxModel<Object>(dummyList.toArray());
		this.traceOnOff = new JComboBox<Object>(model);
		this.traceOnOff.setActionCommand(DB_TRACE_CMD);
		this.traceOnOff.addActionListener(this);
		if (map.containsKey(DB_TRACE_CMD)) {
			if (map.get(DB_TRACE_CMD).equals("1")) {
				this.traceOnOff.setSelectedIndex(1);
			} else {
				this.traceOnOff.setSelectedIndex(0);
			}
		} else {
			this.traceOnOff.setSelectedIndex(1);
		}
		
		JLabel label2 = new JLabel("User home directory ");
		userHomeDirString.setEditable(false);
		userHomeDirString.setEnabled(false);
		if (map.containsKey(USR_HOME_CMD)) {
			userHomeDirString.setText(map.get(USR_HOME_CMD));
		} else {
			userHomeDirString.setText("");
		}
		userHomeDirString.setActionCommand(USR_HOME_CMD);
		userHomeDirString.setName(USR_HOME_CMD);
		userHomeDirString.addActionListener(this);
		userHomeDirString.addFocusListener(this);
		
		JLabel label3 = new JLabel("Last directory ");
		lastDirDirString.setEditable(false);
		lastDirDirString.setEnabled(false);
		if (map.containsKey(LAST_DIR_CMD)) {
			lastDirDirString.setText(map.get(LAST_DIR_CMD));
		} else {
			lastDirDirString.setText("");
		}
		lastDirDirString.setActionCommand(LAST_DIR_CMD);
		lastDirDirString.setName(LAST_DIR_CMD);
		lastDirDirString.addActionListener(this);
		lastDirDirString.addFocusListener(this);
		
		JLabel label4 = new JLabel("Phobos config file ");
		phobosFileDirString.setEditable(false);
		phobosFileDirString.setEnabled(false);
		if (map.containsKey(PHOBOS_DIR_CMD)) {
			phobosFileDirString.setText(map.get(PHOBOS_DIR_CMD));
		} else {
			phobosFileDirString.setText("");
		}
		phobosFileDirString.setActionCommand(PHOBOS_DIR_CMD);
		phobosFileDirString.setName(PHOBOS_DIR_CMD);
		phobosFileDirString.addActionListener(this);
		phobosFileDirString.addFocusListener(this);
		
		TitledBorder title1 = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Directory settings");
		title1.setTitleJustification(TitledBorder.LEADING);
		panel.setBorder(title1);
		
		GridBagLayout layout0 = new GridBagLayout();
		panel.setLayout(layout0);
		GridBagConstraints gbc0 = new GridBagConstraints();
		
		gbc0.anchor = GridBagConstraints.WEST;
		gbc0.gridx = 0;
		gbc0.gridy = 1;
		gbc0.gridheight = 1;
		gbc0.gridwidth = 1;
		panel.add(label1, gbc0);
		gbc0.gridx = 1;
		gbc0.gridy = 1;
		gbc0.gridheight = 1;
		gbc0.gridwidth = 1;
		panel.add(programDirString, gbc0);
		
		gbc0.anchor = GridBagConstraints.WEST;
		gbc0.gridx = 0;
		gbc0.gridy = 2;
		gbc0.gridheight = 1;
		gbc0.gridwidth = 1;
		panel.add(label1A, gbc0);
		gbc0.gridx = 1;
		gbc0.gridy = 2;
		gbc0.gridheight = 1;
		gbc0.gridwidth = 1;
		panel.add(tempDBString, gbc0);

		gbc0.anchor = GridBagConstraints.WEST;
		gbc0.gridx = 0;
		gbc0.gridy = 3;
		gbc0.gridheight = 1;
		gbc0.gridwidth = 1;
		panel.add(label1B, gbc0);
		gbc0.gridx = 1;
		gbc0.gridy = 3;
		gbc0.gridheight = 1;
		gbc0.gridwidth = 1;
		panel.add(traceOnOff, gbc0);

		gbc0.anchor = GridBagConstraints.WEST;
		gbc0.gridx = 0;
		gbc0.gridy = 4;
		gbc0.gridheight = 1;
		gbc0.gridwidth = 1;
		panel.add(label2, gbc0);
		gbc0.gridx = 1;
		gbc0.gridy = 4;
		gbc0.gridheight = 1;
		gbc0.gridwidth = 1;
		panel.add(userHomeDirString, gbc0);

		gbc0.anchor = GridBagConstraints.WEST;
		gbc0.gridx = 0;
		gbc0.gridy = 5;
		gbc0.gridheight = 1;
		gbc0.gridwidth = 1;
		panel.add(label3, gbc0);
		gbc0.gridx = 1;
		gbc0.gridy = 5;
		gbc0.gridheight = 1;
		gbc0.gridwidth = 1;
		panel.add(lastDirDirString, gbc0);

		gbc0.anchor = GridBagConstraints.WEST;
		gbc0.gridx = 0;
		gbc0.gridy = 6;
		gbc0.gridheight = 1;
		gbc0.gridwidth = 1;
		panel.add(label4, gbc0);
		gbc0.gridx = 1;
		gbc0.gridy = 6;
		gbc0.gridheight = 1;
		gbc0.gridwidth = 1;
		panel.add(phobosFileDirString, gbc0);

		return panel;
	}
	
	public int getXYscaleValue() { return this.xyScaleValue; }
	
	protected Component createButton(String cmd, String imgURL, String tip) {
		AbstractButton b = null;
		
		URL url = this.getClass().getClassLoader().getResource(imgURL);
		ImageIcon icon = new ImageIcon(url);
		Image image = icon.getImage().getScaledInstance(this.getXYscaleValue(), this.getXYscaleValue(), java.awt.Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon(image);
		b = new JButton(scaledIcon) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 6976198056606380985L;

			public float getAlignmentY() {
				return 0.5f;
			}
		};
		b.setMargin(new Insets(1,1,1,1));
		b.setRequestFocusEnabled(false);
		b.setFocusable(false);
		
		b.setActionCommand(cmd);
		b.addActionListener(this);
		if (tip!=null) {
			b.setToolTipText(tip);
		}
		return b;
	}
	
	@Override
	public void focusGained(FocusEvent event) {
		if (event.getSource() instanceof JTextField) {
			JTextField tf = (JTextField)event.getSource();
			if (tf.getName().equals(PROGRAM_DIR_CMD)){
				tf.setText(map.get(PROGRAM_DIR_CMD));
			} else if (tf.getName().equals(USR_HOME_CMD)){
				tf.setText(map.get(USR_HOME_CMD));
			} else if (tf.getName().equals(LAST_DIR_CMD)){
				tf.setText(map.get(LAST_DIR_CMD));
			} else if (tf.getName().equals(SELECT_DBTEMP_CMD)){
				tf.setText(map.get(SELECT_DBTEMP_CMD));
			}
		}
	}

	@Override
	public void focusLost(FocusEvent event) {
		if (event.getSource() instanceof JTextField) {
			JTextField tf = (JTextField)event.getSource();
			String value = tf.getText().trim();
			if (tf.getName().equals(PROGRAM_DIR_CMD)){
				value = value.replace("\\", "/");
				map.put(PROGRAM_DIR_CMD, value);
			} else if (tf.getName().equals(USR_HOME_CMD)){
				value = value.replace("\\", "/");
				map.put(USR_HOME_CMD, value);
			} else if (tf.getName().equals(LAST_DIR_CMD)){
				value = value.replace("\\", "/");
				map.put(LAST_DIR_CMD, value);
			} else if (tf.getName().equals(SELECT_DBTEMP_CMD)){
				value = value.replace("\\", "/");
				map.put(SELECT_DBTEMP_CMD, value);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		String command = event.getActionCommand();
		
		if (command.equals(PROGRAM_DIR_CMD)) {
			this.requestFocusInWindow();
		} else if (command.equals(USR_HOME_CMD)) {
			this.requestFocusInWindow();
		} else if (command.equals(LAST_DIR_CMD)) {
			this.requestFocusInWindow();
		} else if (command.equals(SELECT_PD_CMD)) {
			JFileChooser openDialog = new JFileChooser();
			openDialog.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		} else if (command.equals(SELECT_UH_CMD)) {
			
		} else if (command.equals(SELECT_LD_CMD)) {
			
		}
		
	}

}
