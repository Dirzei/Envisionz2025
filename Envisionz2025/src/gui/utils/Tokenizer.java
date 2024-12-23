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
import java.util.StringTokenizer;
import java.util.Vector;

public abstract class Tokenizer {
	private Tokenizer(){
	}
	public static String[] tokenize(String string) {
		Vector<String> vector = new Vector<String>();
		StringTokenizer stringtokenizer = new StringTokenizer(string);
		while (stringtokenizer.hasMoreTokens()) vector.addElement(stringtokenizer.nextToken());
		String[] strings = new String[vector.size()];
		for (int i = 0; i < strings.length; i++) strings[i] = (String) vector.elementAt(i);
		return strings;
	}
	public static String[] tokenize(String string, String delimiter) {
		Vector<String> vector = new Vector<String>();
		StringTokenizer stringtokenizer = new StringTokenizer(string, delimiter);
		while (stringtokenizer.hasMoreTokens()) vector.addElement(stringtokenizer.nextToken());
		String[] strings = new String[vector.size()];
		for (int i = 0; i < strings.length; i++) strings[i] = (String) vector.elementAt(i);
		return strings;
	}
}

