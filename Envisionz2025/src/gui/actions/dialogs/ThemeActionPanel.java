package gui.actions.dialogs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import com.formdev.flatlaf.FlatLaf;
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
import com.formdev.flatlaf.intellijthemes.FlatSpacegrayIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatVuesionIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatXcodeDarkIJTheme;

import gui.actions.ThemeActionListener;

public class ThemeActionPanel extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2097961675076801629L;
	
	private static final String LAYOUT_INDEX_CMD = "LAYOUT_INDEX";
	
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
		
		
		JRadioButton rb1 = new JRadioButton("Arc");
		rb1.setActionCommand(LAYOUT_INDEX_CMD);
		rb1.setName("Arc");
		rb1.addActionListener(this);
		JRadioButton rb2 = new JRadioButton("Arc - Orange");
		rb2.setActionCommand(LAYOUT_INDEX_CMD);
		rb2.setName("Arc-Orange");
		rb2.addActionListener(this);
		JRadioButton rb3 = new JRadioButton("Arc Dark");
		rb3.setActionCommand(LAYOUT_INDEX_CMD);
		rb3.setName("ArcDark");
		rb3.addActionListener(this);
		JRadioButton rb4 = new JRadioButton("Arc Dark - Orange");
		rb4.setActionCommand(LAYOUT_INDEX_CMD);
		rb4.setName("ArcDark-Orange");
		rb4.addActionListener(this);
		JRadioButton rb5 = new JRadioButton("Carbon");
		rb5.setActionCommand(LAYOUT_INDEX_CMD);
		rb5.setName("Carbon");
		rb5.addActionListener(this);
		JRadioButton rb6 = new JRadioButton("Cobalt - 2");
		rb6.setActionCommand(LAYOUT_INDEX_CMD);
		rb6.setName("Cobalt-2");
		rb6.addActionListener(this);
		JRadioButton rb7 = new JRadioButton("Cyan Light");
		rb7.setActionCommand(LAYOUT_INDEX_CMD);
		rb7.setName("CyanLight");
		rb7.addActionListener(this);
		JRadioButton rb8 = new JRadioButton("Dark Flat");
		rb8.setActionCommand(LAYOUT_INDEX_CMD);
		rb8.setName("DarkFlat");
		rb8.addActionListener(this);
		JRadioButton rb9 = new JRadioButton("Dark Purple");
		rb9.setActionCommand(LAYOUT_INDEX_CMD);
		rb9.setName("DarkPurple");
		rb9.addActionListener(this);
		JRadioButton rb10 = new JRadioButton("Dracula");
		rb10.setActionCommand(LAYOUT_INDEX_CMD);
		rb10.setName("Dracula");
		rb10.addActionListener(this);
		JRadioButton rb11 = new JRadioButton("Gradianto Dark Fuchsia");
		rb11.setActionCommand(LAYOUT_INDEX_CMD);
		rb11.setName("GradiantoDarkFuchsia");
		rb11.addActionListener(this);
		JRadioButton rb12 = new JRadioButton("Gradianto Deep Ocean");
		rb12.setActionCommand(LAYOUT_INDEX_CMD);
		rb12.setName("GradiantoDeepOcean");
		rb12.addActionListener(this);
		JRadioButton rb13 = new JRadioButton("Gradianto Midnight Blue");
		rb13.setActionCommand(LAYOUT_INDEX_CMD);
		rb13.setName("GradiantoMidnightBlue");
		rb13.addActionListener(this);
		JRadioButton rb14 = new JRadioButton("Gradianto Nature Green");
		rb14.setActionCommand(LAYOUT_INDEX_CMD);
		rb14.setName("GradiantoNatureGreen");
		rb14.addActionListener(this);
		JRadioButton rb15 = new JRadioButton("Gray");
		rb15.setActionCommand(LAYOUT_INDEX_CMD);
		rb15.setName("Gray");
		rb15.addActionListener(this);
		JRadioButton rb16 = new JRadioButton("Gruvbox Dark Hard");
		rb16.setActionCommand(LAYOUT_INDEX_CMD);
		rb16.setName("GruvboxDarkHard");
		rb16.addActionListener(this);
		JRadioButton rb17 = new JRadioButton("Gruvbox Dark Medium");
		rb17.setActionCommand(LAYOUT_INDEX_CMD);
		rb17.setName("GruvboxDarkMedium");
		rb17.addActionListener(this);
		JRadioButton rb18 = new JRadioButton("Gruvbox Dark Soft");
		rb18.setActionCommand(LAYOUT_INDEX_CMD);
		rb18.setName("GruvboxDarkSoft");
		rb18.addActionListener(this);
		JRadioButton rb19 = new JRadioButton("Hiberbee Dark");
		rb19.setActionCommand(LAYOUT_INDEX_CMD);
		rb19.setName("HiberbeeDark");
		rb19.addActionListener(this);
		JRadioButton rb20 = new JRadioButton("High Contrast");
		rb20.setActionCommand(LAYOUT_INDEX_CMD);
		rb20.setName("HighContrast");
		rb20.addActionListener(this);
		JRadioButton rb21 = new JRadioButton("Light Flat");
		rb21.setActionCommand(LAYOUT_INDEX_CMD);
		rb21.setName("LightFlat");
		rb21.addActionListener(this);
		JRadioButton rb22 = new JRadioButton("Material Design Dark");
		rb22.setActionCommand(LAYOUT_INDEX_CMD);
		rb22.setName("MaterialDesignDark");
		rb22.addActionListener(this);
		JRadioButton rb23 = new JRadioButton("Monocai");
		rb23.setActionCommand(LAYOUT_INDEX_CMD);
		rb23.setName("Monocai");
		rb23.addActionListener(this);
		JRadioButton rb24 = new JRadioButton("Monocai Pro");
		rb24.setActionCommand(LAYOUT_INDEX_CMD);
		rb24.setName("MonocaiPro");
		rb24.addActionListener(this);
		JRadioButton rb25 = new JRadioButton("Nord");
		rb25.setActionCommand(LAYOUT_INDEX_CMD);
		rb25.setName("Nord");
		rb25.addActionListener(this);
		JRadioButton rb26 = new JRadioButton("One Dark");
		rb26.setActionCommand(LAYOUT_INDEX_CMD);
		rb26.setName("OneDark");
		rb26.addActionListener(this);
		JRadioButton rb27 = new JRadioButton("Solarized Dark");
		rb27.setActionCommand(LAYOUT_INDEX_CMD);
		rb27.setName("SolarizedDark");
		rb27.addActionListener(this);
		JRadioButton rb28 = new JRadioButton("Solarized Light");
		rb28.setActionCommand(LAYOUT_INDEX_CMD);
		rb28.setName("SolarizedLight");
		rb28.addActionListener(this);
		JRadioButton rb29 = new JRadioButton("Spacegray");
		rb29.setActionCommand(LAYOUT_INDEX_CMD);
		rb29.setName("Spacegray");
		rb29.addActionListener(this);
		JRadioButton rb30 = new JRadioButton("Vuesion");
		rb30.setActionCommand(LAYOUT_INDEX_CMD);
		rb30.setName("Vuesion");
		rb30.addActionListener(this);
		JRadioButton rb31 = new JRadioButton("Xcode - Dark");
		rb31.setActionCommand(LAYOUT_INDEX_CMD);
		rb31.setName("Xcode-Dark");
		rb31.addActionListener(this);

		ButtonGroup bg = new ButtonGroup();
		bg.add(rb1);
		bg.add(rb2);
		bg.add(rb3);
		bg.add(rb4);
		bg.add(rb5);
		bg.add(rb6);
		bg.add(rb7);
		bg.add(rb8);
		bg.add(rb9);
		bg.add(rb10);
		bg.add(rb11);
		bg.add(rb12);
		bg.add(rb13);
		bg.add(rb14);
		bg.add(rb15);
		bg.add(rb16);
		bg.add(rb17);
		bg.add(rb18);
		bg.add(rb19);
		bg.add(rb20);
		bg.add(rb21);
		bg.add(rb22);
		bg.add(rb23);
		bg.add(rb24);
		bg.add(rb25);
		bg.add(rb26);
		bg.add(rb27);
		bg.add(rb28);
		bg.add(rb29);
		bg.add(rb30);
		bg.add(rb31);
		
		if (UIManager.getLookAndFeel()!=null) {
			switch (UIManager.getLookAndFeel().getName().toLowerCase().trim()) {
			case "arc": {
				rb1.setSelected(true);
				break;
			}
			case "arc - orange": {
				rb2.setSelected(true);
				break;
			}
			case "arc dark": {
				rb3.setSelected(true);
				break;
			}
			case "arc dark - orange": {
				rb4.setSelected(true);
				break;
			}
			case "carbon": {
				rb5.setSelected(true);
				break;
			}
			case "cobalt 2": {
				rb6.setSelected(true);
				break;
			}
			case "cyan light": {
				rb7.setSelected(true);
				break;
			}
			case "dark flat": {
				rb8.setSelected(true);
				break;
			}
			case "dark purple": {
				rb9.setSelected(true);
				break;
			}
			case "draclua": {
				rb10.setSelected(true);
				break;
			}
			case "gradianto dark fuchsia": {
				rb11.setSelected(true);
				break;
			}
			case "gradianto deep ocean": {
				rb12.setSelected(true);
				break;
			}
			case "gradianto midnight blue": {
				rb13.setSelected(true);
				break;
			}
			case "gradianto nature green": {
				rb14.setSelected(true);
				break;
			}
			case "gray": {
				rb15.setSelected(true);
				break;
			}
			case "gruvbox dark hard": {
				rb16.setSelected(true);
				break;
			}
			case "gruvbox dark medium": {
				rb17.setSelected(true);
				break;
			}
			case "gruvbox dark soft": {
				rb18.setSelected(true);
				break;
			}
			case "hiberbee dark": {
				rb19.setSelected(true);
				break;
			}
			case "high contrast": {
				rb20.setSelected(true);
				break;
			}
			case "light flat": {
				rb21.setSelected(true);
				break;
			}
			case "material design dark": {
				rb22.setSelected(true);
				break;
			}
			case "moncai": {
				rb23.setSelected(true);
				break;
			}
			case "monkai pro": {
				rb24.setSelected(true);
				break;
			}
			case "nord": {
				rb25.setSelected(true);
				break;
			}
			case "one dark": {
				rb26.setSelected(true);
				break;
			}
			case "solarized dark": {
				rb27.setSelected(true);
				break;
			}
			case "solarized light": {
				rb28.setSelected(true);
				break;
			}
			case "spacegray": {
				rb29.setSelected(true);
				break;
			}
			case "vuesion": {
				rb30.setSelected(true);
				break;
			}
			case "xcode - dark": {
				rb31.setSelected(true);
				break;
			}
			}
		}
		
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
		gbc0.gridwidth = 1;
		panel.add(rb1, gbc0);
		gbc0.anchor = GridBagConstraints.WEST;
		gbc0.gridx = 1;
		gbc0.gridy = 0;
		gbc0.gridheight = 1;
		gbc0.gridwidth = 1;
		panel.add(rb2, gbc0);
		gbc0.anchor = GridBagConstraints.WEST;
		gbc0.gridx = 0;
		gbc0.gridy = 1;
		gbc0.gridheight = 1;
		gbc0.gridwidth = 1;
		panel.add(rb3, gbc0);
		gbc0.anchor = GridBagConstraints.WEST;
		gbc0.gridx = 1;
		gbc0.gridy = 1;
		gbc0.gridheight = 1;
		gbc0.gridwidth = 1;
		panel.add(rb4, gbc0);
		gbc0.anchor = GridBagConstraints.WEST;
		gbc0.gridx = 0;
		gbc0.gridy = 2;
		gbc0.gridheight = 1;
		gbc0.gridwidth = 1;
		panel.add(rb5, gbc0);
		gbc0.anchor = GridBagConstraints.WEST;
		gbc0.gridx = 0;
		gbc0.gridy = 3;
		gbc0.gridheight = 1;
		gbc0.gridwidth = 1;
		panel.add(rb6, gbc0);
		gbc0.anchor = GridBagConstraints.WEST;
		gbc0.gridx = 0;
		gbc0.gridy = 4;
		gbc0.gridheight = 1;
		gbc0.gridwidth = 1;
		panel.add(rb7, gbc0);
		gbc0.anchor = GridBagConstraints.WEST;
		gbc0.gridx = 0;
		gbc0.gridy = 5;
		gbc0.gridheight = 1;
		gbc0.gridwidth = 1;
		panel.add(rb8, gbc0);
		gbc0.anchor = GridBagConstraints.WEST;
		gbc0.gridx = 1;
		gbc0.gridy = 5;
		gbc0.gridheight = 1;
		gbc0.gridwidth = 1;
		panel.add(rb9, gbc0);
		gbc0.anchor = GridBagConstraints.WEST;
		gbc0.gridx = 0;
		gbc0.gridy = 6;
		gbc0.gridheight = 1;
		gbc0.gridwidth = 1;
		panel.add(rb10, gbc0);
		gbc0.anchor = GridBagConstraints.WEST;
		gbc0.gridx = 0;
		gbc0.gridy = 7;
		gbc0.gridheight = 1;
		gbc0.gridwidth = 1;
		panel.add(rb11, gbc0);
		gbc0.anchor = GridBagConstraints.WEST;
		gbc0.gridx = 1;
		gbc0.gridy = 7;
		gbc0.gridheight = 1;
		gbc0.gridwidth = 1;
		panel.add(rb12, gbc0);
		gbc0.anchor = GridBagConstraints.WEST;
		gbc0.gridx = 2;
		gbc0.gridy = 7;
		gbc0.gridheight = 1;
		gbc0.gridwidth = 1;
		panel.add(rb13, gbc0);
		gbc0.anchor = GridBagConstraints.WEST;
		gbc0.gridx = 3;
		gbc0.gridy = 7;
		gbc0.gridheight = 1;
		gbc0.gridwidth = 1;
		panel.add(rb14, gbc0);
		gbc0.anchor = GridBagConstraints.WEST;
		gbc0.gridx = 0;
		gbc0.gridy = 8;
		gbc0.gridheight = 1;
		gbc0.gridwidth = 1;
		panel.add(rb15, gbc0);
		gbc0.anchor = GridBagConstraints.WEST;
		gbc0.gridx = 0;
		gbc0.gridy = 9;
		gbc0.gridheight = 1;
		gbc0.gridwidth = 1;
		panel.add(rb16, gbc0);
		gbc0.anchor = GridBagConstraints.WEST;
		gbc0.gridx = 1;
		gbc0.gridy = 9;
		gbc0.gridheight = 1;
		gbc0.gridwidth = 1;
		panel.add(rb17, gbc0);
		gbc0.anchor = GridBagConstraints.WEST;
		gbc0.gridx = 2;
		gbc0.gridy = 9;
		gbc0.gridheight = 1;
		gbc0.gridwidth = 1;
		panel.add(rb18, gbc0);
		gbc0.anchor = GridBagConstraints.WEST;
		gbc0.gridx = 0;
		gbc0.gridy = 10;
		gbc0.gridheight = 1;
		gbc0.gridwidth = 1;
		panel.add(rb19, gbc0);
		gbc0.anchor = GridBagConstraints.WEST;
		gbc0.gridx = 0;
		gbc0.gridy = 11;
		gbc0.gridheight = 1;
		gbc0.gridwidth = 1;
		panel.add(rb20, gbc0);
		gbc0.anchor = GridBagConstraints.WEST;
		gbc0.gridx = 0;
		gbc0.gridy = 12;
		gbc0.gridheight = 1;
		gbc0.gridwidth = 1;
		panel.add(rb21, gbc0);
		gbc0.anchor = GridBagConstraints.WEST;
		gbc0.gridx = 0;
		gbc0.gridy = 13;
		gbc0.gridheight = 1;
		gbc0.gridwidth = 1;
		panel.add(rb22, gbc0);
		gbc0.anchor = GridBagConstraints.WEST;
		gbc0.gridx = 0;
		gbc0.gridy = 14;
		gbc0.gridheight = 1;
		gbc0.gridwidth = 1;
		panel.add(rb23, gbc0);
		gbc0.anchor = GridBagConstraints.WEST;
		gbc0.gridx = 1;
		gbc0.gridy = 14;
		gbc0.gridheight = 1;
		gbc0.gridwidth = 1;
		panel.add(rb24, gbc0);
		gbc0.anchor = GridBagConstraints.WEST;
		gbc0.gridx = 0;
		gbc0.gridy = 15;
		gbc0.gridheight = 1;
		gbc0.gridwidth = 1;
		panel.add(rb25, gbc0);
		gbc0.anchor = GridBagConstraints.WEST;
		gbc0.gridx = 0;
		gbc0.gridy = 16;
		gbc0.gridheight = 1;
		gbc0.gridwidth = 1;
		panel.add(rb26, gbc0);
		gbc0.anchor = GridBagConstraints.WEST;
		gbc0.gridx = 0;
		gbc0.gridy = 17;
		gbc0.gridheight = 1;
		gbc0.gridwidth = 1;
		panel.add(rb27, gbc0);
		gbc0.anchor = GridBagConstraints.WEST;
		gbc0.gridx = 1;
		gbc0.gridy = 17;
		gbc0.gridheight = 1;
		gbc0.gridwidth = 1;
		panel.add(rb28, gbc0);
		gbc0.anchor = GridBagConstraints.WEST;
		gbc0.gridx = 0;
		gbc0.gridy = 18;
		gbc0.gridheight = 1;
		gbc0.gridwidth = 1;
		panel.add(rb29, gbc0);
		gbc0.anchor = GridBagConstraints.WEST;
		gbc0.gridx = 0;
		gbc0.gridy = 19;
		gbc0.gridheight = 1;
		gbc0.gridwidth = 1;
		panel.add(rb30, gbc0);
		gbc0.anchor = GridBagConstraints.WEST;
		gbc0.gridx = 0;
		gbc0.gridy = 20;
		gbc0.gridheight = 1;
		gbc0.gridwidth = 1;
		panel.add(rb31, gbc0);

		return panel;
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		String command = event.getActionCommand();
		
		if (command.equals(LAYOUT_INDEX_CMD)){
			if (event.getSource() instanceof JRadioButton) {
				JRadioButton cb = ((JRadioButton)event.getSource());
				String name = cb.getName();
				
				switch (name) {
				case "Arc" : {
					FlatLaf.registerCustomDefaultsSource("resources");
					FlatArcIJTheme.setup();
					break;
				}
				case "Arc-Orange": {
					FlatLaf.registerCustomDefaultsSource("resources");
					FlatArcOrangeIJTheme.setup();
					break;
				}
				case "ArcDark": {
					FlatLaf.registerCustomDefaultsSource("resources");
					FlatArcDarkIJTheme.setup();
					break;
				}
				case "ArcDark-Orange" : {
					FlatLaf.registerCustomDefaultsSource("resources");
					FlatArcDarkOrangeIJTheme.setup();
					break;
				}
				case "Carbon" : {
					FlatLaf.registerCustomDefaultsSource("resources");
					FlatCarbonIJTheme.setup();
					break;
				}
				case "Cobalt-2" : {
					FlatLaf.registerCustomDefaultsSource("resources");
					FlatCobalt2IJTheme.setup();
					break;
				}
				case "CyanLight" : {
					FlatLaf.registerCustomDefaultsSource("resources");
					FlatCyanLightIJTheme.setup();
					break;
				}
				case "DarkFlat" : {
					FlatLaf.registerCustomDefaultsSource("resources");
					FlatDarkFlatIJTheme.setup();
					break;
				}
				case "DarkPurple" : {
					FlatLaf.registerCustomDefaultsSource("resources");
					FlatDarkPurpleIJTheme.setup();
					break;
				}
				case "Dracula" : {
					FlatLaf.registerCustomDefaultsSource("resources");
					FlatDraculaIJTheme.setup();
					break;
				}
				case "GradiantoDarkFuchsia" : {
					FlatLaf.registerCustomDefaultsSource("resources");
					FlatGradiantoDarkFuchsiaIJTheme.setup();
					break;
				}
				case "GradiantoDeepOcean" : {
					FlatLaf.registerCustomDefaultsSource("resources");
					FlatGradiantoDeepOceanIJTheme.setup();
					break;
				}
				case "GradiantoMidnightBlue" : {
					FlatLaf.registerCustomDefaultsSource("resources");
					FlatGradiantoMidnightBlueIJTheme.setup();
					break;
				}
				case "GradiantoNatureGreen" : {
					FlatLaf.registerCustomDefaultsSource("resources");
					FlatGradiantoNatureGreenIJTheme.setup();
					break;
				}
				case "Gray" : {
					FlatLaf.registerCustomDefaultsSource("resources");
					FlatGrayIJTheme.setup();
					break;
				}
				case "GruvboxDarkHard" : {
					FlatLaf.registerCustomDefaultsSource("resources");
					FlatGruvboxDarkHardIJTheme.setup();
					break;
				}
				case "GruvboxDarkMedium" : {
					FlatLaf.registerCustomDefaultsSource("resources");
					FlatGruvboxDarkMediumIJTheme.setup();
					break;
				}
				case "GruvboxDarkSoft" : {
					FlatLaf.registerCustomDefaultsSource("resources");
					FlatGruvboxDarkSoftIJTheme.setup();
					break;
				}
				case "HiberbeeDark" : {
					FlatLaf.registerCustomDefaultsSource("resources");
					FlatHiberbeeDarkIJTheme.setup();
					break;
				}
				case "HighContrast" : {
					FlatLaf.registerCustomDefaultsSource("resources");
					FlatHighContrastIJTheme.setup();
					break;
				}
				case "LightFlat" : {
					FlatLaf.registerCustomDefaultsSource("resources");
					FlatLightFlatIJTheme.setup();
					break;
				}
				case "MaterialDesignDark" : {
					FlatLaf.registerCustomDefaultsSource("resources");
					FlatMaterialDesignDarkIJTheme.setup();
					break;
				}
				case "Monocai" : {
					FlatLaf.registerCustomDefaultsSource("resources");
					FlatMonocaiIJTheme.setup();
					break;
				}
				case "MonocaiPro" : {
					FlatLaf.registerCustomDefaultsSource("resources");
					FlatMonokaiProIJTheme.setup();
					break;
				}
				case "Nord" : {
					FlatLaf.registerCustomDefaultsSource("resources");
					FlatNordIJTheme.setup();
					break;
				}
				case "OneDark" : {
					FlatLaf.registerCustomDefaultsSource("resources");
					FlatOneDarkIJTheme.setup();
					break;
				}
				case "SolarizedDark" : {
					FlatLaf.registerCustomDefaultsSource("resources");
					FlatSolarizedDarkIJTheme.setup();
					break;
				}
				case "SolarizedLight" : {
					FlatLaf.registerCustomDefaultsSource("resources");
					FlatSolarizedLightIJTheme.setup();
					break;
				}
				case "Spacegray" : {
					FlatLaf.registerCustomDefaultsSource("resources");
					FlatSpacegrayIJTheme.setup();
					break;
				}
				case "Vuesion" : {
					FlatLaf.registerCustomDefaultsSource("resources");
					FlatVuesionIJTheme.setup();
					break;
				}
				case "Xcode-Dark" : {
					FlatLaf.registerCustomDefaultsSource("resources");
					FlatXcodeDarkIJTheme.setup();
					break;
				}
				}
				//this.ownerObj.setSelectedLayoutIndex(cb.getSelectedIndex());

				SwingUtilities.updateComponentTreeUI(this.getTopLevelAncestor());
				this.getTopLevelAncestor().setSize(this.getTopLevelAncestor().getPreferredSize());
				SwingUtilities.updateComponentTreeUI(ownerObj.getOwner());
			}
		}
	}
}
