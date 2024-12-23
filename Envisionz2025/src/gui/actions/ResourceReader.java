package gui.actions;

import java.net.URL;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import gui.utils.GUIconstants;

public class ResourceReader implements GUIconstants {
	/**
	 * resources holds the link to the resource file
	 */
	private ResourceBundle resources;

	/**
	 * Empty constructor for ReasourceReader. If you want to use this you
	 * have to use setResourceBundle(String name) to define a location for the 
	 * resources. If not the standard resource 
	 * "resources.default.properties" is used. 
	 */
	public ResourceReader(){
		this(null);
	}
	/**
	 * @param resourceFile holds the resource file name and path.
	 */
	public ResourceReader(String resourceFile){
		if (resourceFile==null){
			resources = getResourceBundle(GUIconstants.DEFAULT_RESOURCE_BUNDLE);
		} else {
			resources = getResourceBundle(resourceFile);
		}
	}
	/**
	 * @param resourceFile location and filename of the resource file
	 * @param type represents the application type (developer, team, sales) 
	 */
	public ResourceReader(String resourceFile, int type){
		if (resourceFile==null){
			resources = getResourceBundle(GUIconstants.DEFAULT_RESOURCE_BUNDLE);
		} else {
			resources = getResourceBundle(resourceFile);
		}		
	}
	public ResourceBundle getResourceBundle(){
		return resources;
	}
	public void setResourceBundle(ResourceBundle bundle){
		this.resources = bundle;
	}
	private ResourceBundle getResourceBundle(String pathAndName){
		try{
			return ResourceBundle.getBundle(pathAndName,Locale.getDefault());
		} catch (MissingResourceException ex1){
			System.err.println("Error in class ResourceReader! Resource file does not exist! " +
							   pathAndName+
							   "\n"+"Try to load default resource bundle");
			return ResourceBundle.getBundle(GUIconstants.DEFAULT_RESOURCE_BUNDLE,
											Locale.getDefault());
		} catch (NullPointerException ex2){
			if (pathAndName==null){
				System.err.println("Error in class ResourceReader! " +
								   "Resource file does not exist!"+ pathAndName);
			}
			if (Locale.getDefault()==null){
				System.err.println("Error in class ResourceReader! " +
						   		   "Default Locale is null!");
			} else {
				return ResourceBundle.getBundle(GUIconstants.DEFAULT_RESOURCE_BUNDLE,
												Locale.getDefault());	
			}
			return null;
		}
	}
	/**
	 * @param string represents the resource name
	 * @return String returns the value of the resource name
	 */
	public String getResourceString(String string) {
		String resourceName;
		try {
			resourceName = resources.getString(string);
		} catch (MissingResourceException ex1) {
			if ((!string.trim().endsWith("Menu"))&&
				(!string.trim().endsWith("Accel"))&&
				(!string.trim().endsWith("Mnemonic"))&&
				(!string.trim().endsWith("Action"))&&
				(!string.trim().endsWith("Tooltip"))){
				System.err.println("Missing Resource Exception in Class " +
								   "ReasourceReader for ResourceString: "+string);
			}
			return null;
		} catch (NullPointerException ex2){
			System.err.println("Error in class ResourceReader! Key is not valid!");		
			return null;
		}
		return resourceName;
	}
	/**
	 * @param string represents the resource name
	 * @return URL returns the URL of the resource
	 */
	public URL getResource(String string) {
		String resourceName = getResourceString(string);
		if (resourceName != null) {
			URL url = this.getClass().getClassLoader().getResource(resourceName);
			return url;
		}
		return null;
	}
}
