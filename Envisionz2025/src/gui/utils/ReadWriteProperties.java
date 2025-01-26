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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

public class ReadWriteProperties {

	public ReadWriteProperties(){
	}
	
	public void writeSingleProperty(String key, String value){
		HashMap<String, String> map = this.getProperties();
		value = value.replace("\\", "/");
		map.put(key, value);
		this.writePropertiesFile(map);
	}
	
	public void writePropertiesFile(HashMap<String, String> props){
		String user_dir = System.getProperty("user.home");
		String propDir = new StringBuilder().append(user_dir).append(File.separator).append("envisionz").toString();
		String propFile = new StringBuilder().append(propDir).append(File.separator).append("config.properties").toString();

		File pDir = new File(propDir);
		
		if (pDir.exists()){
			try {
				BufferedWriter bw = new BufferedWriter(new FileWriter(propFile, false));
				bw.write(new StringBuilder().append("USER_HOME").append("=").append(props.get("USER_HOME")).append("\n").toString());
				bw.write(new StringBuilder().append("PROGRAM_DIR").append("=").append(props.get("PROGRAM_DIR")).append("\n").toString());
				bw.write(new StringBuilder().append("LAST_DIR").append("=").append(props.get("LAST_DIR")).append("\n").toString());	
				bw.write(new StringBuilder().append("DBTEMP_DIR").append("=").append(props.get("DBTEMP_DIR")).append("\n").toString());	
				bw.write(new StringBuilder().append("DB_TRACE").append("=").append(props.get("DB_TRACE")).append("\n").toString());	
				bw.write(new StringBuilder().append("PHOBOS_CONFIG_DIR").append("=").append(props.get("PHOBOS_CONFIG_DIR")).append("\n").toString());	
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				
			}
		}
	}
	
	public HashMap<String, String> getProperties(){
		HashMap<String, String> map = new HashMap<String, String>();

		String user_dir = System.getProperty("user.home");
		String propDir = new StringBuilder().append(user_dir).append(File.separator).append("envisionz").toString();
		String propFile = new StringBuilder().append(propDir).append(File.separator).append("config.properties").toString();
		
		File pDir = new File(propDir);
		
		if (pDir.exists()){
			File pFile = new File(propFile);
			if (pFile.exists()){
				Properties props = new Properties();
				try {
					InputStream inputStream = new FileInputStream(pFile);
					props.load(inputStream);
					if (props.containsKey("USER_HOME")){
						map.put("USER_HOME", props.getProperty("USER_HOME"));
					} else {
						map.put("USER_HOME", System.getProperty("user.home"));
					}
					if (props.containsKey("PROGRAM_DIR")){
						map.put("PROGRAM_DIR", props.getProperty("PROGRAM_DIR"));
					} else {
						map.put("PROGRAM_DIR", System.getProperty("user.dir"));
					}
					if (props.containsKey("LAST_DIR")){
						map.put("LAST_DIR", props.getProperty("LAST_DIR"));
					} else {
						map.put("LAST_DIR", System.getProperty("user.dir"));
					}
					if (props.containsKey("DBTEMP_DIR")){
						map.put("DBTEMP_DIR", props.getProperty("DBTEMP_DIR"));
					} else {
						map.put("DBTEMP_DIR", System.getProperty("java.io.tmpdir"));
					}
					if (props.containsKey("DB_TRACE")){
						map.put("DB_TRACE", props.getProperty("DB_TRACE"));
					} else {
						map.put("DB_TRACE", "1");
					}
					if (props.containsKey("PHOBOS_CONFIG_DIR")){
						map.put("PHOBOS_CONFIG_DIR", props.getProperty("PHOBOS_CONFIG_DIR"));
					} else {
						map.put("PHOBOS_CONFIG_DIR", " ");
					}
					inputStream.close();
				} catch (FileNotFoundException ex){
					ex.printStackTrace();					
				} catch (IOException ex) {
					ex.printStackTrace();
				} finally {
					
				}
			} else {
				map.put("USER_HOME", System.getProperty("user.home"));
				map.put("PROGRAM_DIR", System.getProperty("user.dir"));
				map.put("LAST_DIR", System.getProperty("user.dir"));
				map.put("DBTEMP_DIR", System.getProperty("java.io.tmpdir"));
				map.put("DB_TRACE", "1");
				map.put("PHOBOS_CONFIG_DIR", " ");
				try {
					BufferedWriter bw = new BufferedWriter(new FileWriter(propFile, true));
					String dummy = System.getProperty("user.home");
					//dummy = dummy.replace("\\", "\\\\");
					dummy = dummy.replace("\\", "/");
					bw.write(new StringBuilder().append("USER_HOME").append("=").append(dummy).append("\n").toString());
					dummy = System.getProperty("user.dir");
					//dummy = dummy.replace("\\", "\\\\");
					dummy = dummy.replace("\\", "/");
					bw.write(new StringBuilder().append("PROGRAM_DIR").append("=").append(dummy).append("\n").toString());
					dummy = System.getProperty("user.dir");
					//dummy = dummy.replace("\\", "\\\\");
					dummy = dummy.replace("\\", "/");
					bw.write(new StringBuilder().append("LAST_DIR").append("=").append(dummy).append("\n").toString());	
					dummy = System.getProperty("java.io.tmpdir");
					dummy = dummy.replace("\\", "/");
					bw.write(new StringBuilder().append("DBTEMP_DIR").append("=").append(dummy).append("\n").toString());	
					bw.write(new StringBuilder().append("DB_TRACE").append("=").append("1").append("\n").toString());
					bw.write(new StringBuilder().append("PHOBOS_CONFIG_DIR").append("=").append(" ").append("\n").toString());
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					
				}
			}
		} else {
			map.put("USER_HOME", System.getProperty("user.home"));
			map.put("PROGRAM_DIR", System.getProperty("user.dir"));
			map.put("LAST_DIR", System.getProperty("user.dir"));
			map.put("DBTEMP_DIR", System.getProperty("java.io.tmpdir"));
			map.put("DB_TRACE", "1");
			map.put("PHOBOS_CONFIG_DIR", " ");
			pDir.mkdirs();
			try {
				BufferedWriter bw = new BufferedWriter(new FileWriter(propFile, true));
				/*bw.write(new StringBuilder().append("USER_HOME").append("=").append(System.getProperty("user.home")).append("\n").toString());
				bw.write(new StringBuilder().append("WORK_DIR").append("=").append(System.getProperty("user.dir")).append("\n").toString());
				bw.write(new StringBuilder().append("LAST_DIR").append("=").append(System.getProperty("user.dir")).append("\n").toString());*/
				String dummy = System.getProperty("user.home");
				//dummy = dummy.replace("\\", "\\\\");
				dummy = dummy.replace("\\", "/");
				bw.write(new StringBuilder().append("USER_HOME").append("=").append(dummy).append("\n").toString());
				dummy = System.getProperty("user.dir");
				//dummy = dummy.replace("\\", "\\\\");
				dummy = dummy.replace("\\", "/");
				bw.write(new StringBuilder().append("PROGRAM_DIR").append("=").append(dummy).append("\n").toString());
				dummy = System.getProperty("user.dir");
				//dummy = dummy.replace("\\", "\\\\");
				dummy = dummy.replace("\\", "/");
				bw.write(new StringBuilder().append("LAST_DIR").append("=").append(dummy).append("\n").toString());	
				dummy = System.getProperty("java.io.tmpdir");
				dummy = dummy.replace("\\", "/");
				bw.write(new StringBuilder().append("DBTEMP_DIR").append("=").append(dummy).append("\n").toString());	
				bw.write(new StringBuilder().append("DB_TRACE").append("=").append("1").append("\n").toString());
				bw.write(new StringBuilder().append("PHOBOS_CONFIG_DIR").append("=").append(" ").append("\n").toString());
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				
			}
		}	
		return map;
	}
}
