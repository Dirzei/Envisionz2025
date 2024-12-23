package gui.utils;

public interface GUIconstants {
	public static String DEFAULT_RESOURCE_BUNDLE = "resources.default";
	
	/**
	 * labelSuffix is for declaration of labels in property files.
	 */
	public static final String labelSuffix = "Label";
	/**
	 * imageToolSuffix is for declaration of images in property files.
	 */
	public static final String imageToolSuffix = "ImageTool";
	/**
	 * imageMenuSuffix is for declaration menu images in property files.
	 */
	public static final String imageMenuSuffix = "ImageMenu";
	/**
	 * menuSuffix is for declaration of menus in property files.
	 */
	public static final String menuSuffix = "Menu";
	/**
	 * accelSuffix is for declaration of key shortcuts in property files.
	 */
	public static final String accelSuffix = "Accel";
	/**
	 * mnemonicSuffix is for declaration of mnemonics in property files.
	 */
	public static final String mnemonicSuffix = "Mnemonic";
	/**
	 * actionSuffix is for declaration of actions in property files.
	 */
	public static final String actionSuffix = "Action";
	/** tipSuffix is for declaration of tool tips in property files.
	 *
	 */
	public static final String tipSuffix = "Tooltip";
	
	public static final int xyScaleValue = 20;
	public static final int xyScaleValueBIG = 30;
	
	public static final String SELECT_FILE = "SELECT_FILE";
	public static final String DELIMITER = "DELIMITER";
	public static final String DATE = "DATE";
	public static final String DIRECTION = "DIRECTION";
	public static final String CONVERTDBL = "CONVERTDBL";
	public static final String BTRANSFER = "BTRANSFER";
	public static final String OK_BUTTON_CMD = "OK_BUTTON";
	public static final String CANCEL_BUTTON_CMD = "CANCEL_BUTTON";
	public static final String SHOW_CMD = "SHOW";
	
	public static final String DB_DRIVER = "org.h2.Driver";
	public static final String DB_USER = "";
	public static final String DB_PASSWORD = "";
	
	public static final String STD_PARAMS = ";CACHE_SIZE=262144;LOCK_MODE=0;AUTO_SERVER=TRUE;TACE_LEVEL=0;COMPRESS=TRUE";
	public static final String STD_PARAMS_MODEL = ";CACHE_SIZE=262144;LOCK_MODE=0;AUTO_SERVER=TRUE;DEFRAG_ALWAYS=TRUE;TACE_LEVEL=0;COMPRESS=TRUE";
	
	public static final String STD_DB_TBL_NAME = "TEST_TABLE";
}
