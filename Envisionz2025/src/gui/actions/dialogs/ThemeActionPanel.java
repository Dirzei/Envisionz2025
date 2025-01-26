package gui.actions.dialogs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;

import com.formdev.flatlaf.intellijthemes.FlatArcDarkIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatArcDarkOrangeIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatArcIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatArcOrangeIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatCarbonIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatCobalt2IJTheme;
import com.formdev.flatlaf.intellijthemes.FlatCyanLightIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatDarkFlatIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatDarkPurpleIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatDraculaIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatGradiantoDarkFuchsiaIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatGradiantoDeepOceanIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatGradiantoMidnightBlueIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatGradiantoNatureGreenIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatGrayIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatGruvboxDarkHardIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatGruvboxDarkMediumIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatGruvboxDarkSoftIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatHiberbeeDarkIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatHighContrastIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatLightFlatIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatMaterialDesignDarkIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatMonocaiIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatMonokaiProIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatNordIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatOneDarkIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatSolarizedDarkIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatSolarizedLightIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatVuesionIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatXcodeDarkIJTheme;

import gui.actions.ThemeActionListener;

public class ThemeActionPanel extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2097961675076801629L;
	
	private static final String LAYOUT_INDEX_CMD = "LAYOUT_INDEX";
	
	private List<String> layoutList = Arrays.asList("Arc", "Arc-Orange", "ArcDark", "ArcDark-Orange", "Carbon", "Cobalt-2", "CyanLight", 
													"DarkFlat", "DarkPurple", "Dracula", "GradiantoDarkFuchsia", "GradiantoDeepOcean", 
													"GradiantoMidnightBlue", "GradiantoNatureGreen", "Gray", "GruvboxDarkHard", "GruvboxDarkMedium", 
													"GruvboxDarkSoft", "HiberbeeDark", "HighContrast", "LightFlat", "MaterialDesignDark", "Monocai", 
													"MonokaiPro", "Nord", "OneDark", "SolarizedDark", "SolarizedLight", "Spacegray", "Vuesion", "Xcode-Dark");
	
	private ThemeActionListener ownerObj;
	
	public ThemeActionPanel(ThemeActionListener listener) {
		super();
		this.ownerObj = listener;
		listener = null;
		this.setLayout(new BorderLayout());
				
		this.add(this.setupDialog(), BorderLayout.CENTER);
	}
	
	private JPanel setupDialog() {
		JPanel panel = new JPanel();
		
		DefaultComboBoxModel<Object> model = new DefaultComboBoxModel<Object>(layoutList.toArray());
		JComboBox<Object> cb = new JComboBox<Object>();
		cb.setModel(model);
		cb.setSelectedIndex(this.ownerObj.getSelectedLayoutIndex());
		cb.setActionCommand(LAYOUT_INDEX_CMD);
		cb.addActionListener(this);
		
		TitledBorder title1 = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Select Layout");
		title1.setTitleJustification(TitledBorder.LEADING);
		panel.setBorder(title1);

		GridBagLayout layout0 = new GridBagLayout();
		panel.setLayout(layout0);
		GridBagConstraints gbc0 = new GridBagConstraints();

		gbc0.anchor = GridBagConstraints.WEST;
		gbc0.gridx = 0;
		gbc0.gridy = 0;
		gbc0.gridheight = 1;
		gbc0.gridwidth = 2;
		panel.add(cb, gbc0);
		
		return panel;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		String command = event.getActionCommand();
		
		if (command.equals(LAYOUT_INDEX_CMD)){
			if (event.getSource() instanceof JComboBox) {
				JComboBox<?> cb = ((JComboBox<?>)event.getSource());
				String name = cb.getSelectedItem().toString();
				
				switch (name) {
				case "Arc" : {
					FlatArcIJTheme.registerCustomDefaultsSource("resources");
					FlatArcIJTheme.setup();
					break;
				}
				case "Arc-Orange": {
					FlatArcOrangeIJTheme.registerCustomDefaultsSource("resources");
					FlatArcOrangeIJTheme.setup();
					break;
				}
				case "ArcDark": {
					FlatArcDarkIJTheme.registerCustomDefaultsSource("resources");
					FlatArcDarkIJTheme.setup();
					break;
				}
				case "ArcDark-Orange" : {
					FlatArcDarkOrangeIJTheme.registerCustomDefaultsSource("resources");
					FlatArcDarkOrangeIJTheme.setup();
					break;
				}
				case "Carbon" : {
					FlatCarbonIJTheme.registerCustomDefaultsSource("resources");
					FlatCarbonIJTheme.setup();
					break;
				}
				case "Cobalt-2" : {
					FlatCobalt2IJTheme.registerCustomDefaultsSource("resources");
					FlatCobalt2IJTheme.setup();
					break;
				}
				case "CyanLight" : {
					FlatCyanLightIJTheme.registerCustomDefaultsSource("resources");
					FlatCyanLightIJTheme.setup();
					break;
				}
				case "DarkFlat" : {
					FlatDarkFlatIJTheme.registerCustomDefaultsSource("resources");
					FlatDarkFlatIJTheme.setup();
					break;
				}
				case "DarkPurple" : {
					FlatDarkPurpleIJTheme.registerCustomDefaultsSource("resources");
					FlatDarkPurpleIJTheme.setup();
					break;
				}
				case "Dracula" : {
					FlatDraculaIJTheme.registerCustomDefaultsSource("resources");
					FlatDraculaIJTheme.setup();
					break;
				}
				case "GradiantoDarkFuchsia" : {
					FlatGradiantoDarkFuchsiaIJTheme.registerCustomDefaultsSource("resources");
					FlatGradiantoDarkFuchsiaIJTheme.setup();
					break;
				}
				case "GradiantoDeepOcean" : {
					FlatGradiantoDeepOceanIJTheme.registerCustomDefaultsSource("resources");
					FlatGradiantoDeepOceanIJTheme.setup();
					break;
				}
				case "GradiantoMidnightBlue" : {
					FlatGradiantoMidnightBlueIJTheme.registerCustomDefaultsSource("resources");
					FlatGradiantoMidnightBlueIJTheme.setup();
					break;
				}
				case "GradiantoNatureGreen" : {
					FlatGradiantoNatureGreenIJTheme.registerCustomDefaultsSource("resources");
					FlatGradiantoNatureGreenIJTheme.setup();
					break;
				}
				case "Gray" : {
					FlatGrayIJTheme.registerCustomDefaultsSource("resources");
					FlatGrayIJTheme.setup();
					break;
				}
				case "GruvboxDarkHard" : {
					FlatGruvboxDarkHardIJTheme.registerCustomDefaultsSource("resources");
					FlatGruvboxDarkHardIJTheme.setup();
					break;
				}
				case "GruvboxDarkMedium" : {
					FlatGruvboxDarkMediumIJTheme.registerCustomDefaultsSource("resources");
					FlatGruvboxDarkMediumIJTheme.setup();
					break;
				}
				case "GruvboxDarkSoft" : {
					FlatGruvboxDarkSoftIJTheme.registerCustomDefaultsSource("resources");
					FlatGruvboxDarkSoftIJTheme.setup();
					break;
				}
				case "HiberbeeDark" : {
					FlatHiberbeeDarkIJTheme.registerCustomDefaultsSource("resources");
					FlatHiberbeeDarkIJTheme.setup();
					break;
				}
				case "HighContrast" : {
					FlatHighContrastIJTheme.registerCustomDefaultsSource("resources");
					FlatHighContrastIJTheme.setup();
					break;
				}
				case "LightFlat" : {
					FlatLightFlatIJTheme.registerCustomDefaultsSource("resources");
					FlatLightFlatIJTheme.setup();
					break;
				}
				case "MaterialDesignDark" : {
					FlatMaterialDesignDarkIJTheme.registerCustomDefaultsSource("resources");
					FlatMaterialDesignDarkIJTheme.setup();
					break;
				}
				case "Monocai" : {
					FlatMonocaiIJTheme.registerCustomDefaultsSource("resources");
					FlatMonocaiIJTheme.setup();
					break;
				}
				case "MonokaiPro" : {
					FlatMonokaiProIJTheme.registerCustomDefaultsSource("resources");
					FlatMonokaiProIJTheme.setup();
					break;
				}
				case "Nord" : {
					FlatNordIJTheme.registerCustomDefaultsSource("resources");
					FlatNordIJTheme.setup();
					break;
				}
				case "OneDark" : {
					FlatOneDarkIJTheme.registerCustomDefaultsSource("resources");
					FlatOneDarkIJTheme.setup();
					break;
				}
				case "SolarizedDark" : {
					FlatSolarizedDarkIJTheme.registerCustomDefaultsSource("resources");
					FlatSolarizedDarkIJTheme.setup();
					break;
				}
				case "SolarizedLight" : {
					FlatSolarizedLightIJTheme.registerCustomDefaultsSource("resources");
					FlatSolarizedLightIJTheme.setup();
					break;
				}
				case "Spacegray" : {
					FlatVuesionIJTheme.registerCustomDefaultsSource("resources");
					FlatVuesionIJTheme.setup();
					break;
				}
				case "Vuesion" : {
					FlatVuesionIJTheme.registerCustomDefaultsSource("resources");
					FlatVuesionIJTheme.setup();
					break;
				}
				case "Xcode-Dark" : {
					FlatXcodeDarkIJTheme.registerCustomDefaultsSource("resources");
					FlatXcodeDarkIJTheme.setup();
					break;
				}
				}
				this.ownerObj.setSelectedLayoutIndex(cb.getSelectedIndex());

				//System.out.println(this.getTopLevelAncestor().getPreferredSize());
				SwingUtilities.updateComponentTreeUI(this.getTopLevelAncestor());
				this.getTopLevelAncestor().setSize(this.getTopLevelAncestor().getPreferredSize());
				SwingUtilities.updateComponentTreeUI(ownerObj.getOwner());
				
				/*System.out.println(UIManager.getDefaults().keys().toString());
				Enumeration<Object> vls = UIManager.getDefaults().keys();
				while (vls.hasMoreElements()) {
					Object element = vls.nextElement();
				    System.out.println(element.toString());
				}*/
			}
		}
	}
}
