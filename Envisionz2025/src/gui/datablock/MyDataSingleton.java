package gui.datablock;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//import com.sun.corba.se.impl.io.OptionalDataException;

public class MyDataSingleton implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8541864843973656697L;
	
	private static MyDataSingleton _instance;
	private List<MyDataBlock> dataStructure = new ArrayList<MyDataBlock>();
	
	public MyDataSingleton(){
		super();
	}
	
	public synchronized static MyDataSingleton getInstance(){
		if (_instance==null){
			_instance = new MyDataSingleton();
		}
		return _instance;
	}
	
	public synchronized static MyDataSingleton getInstance(String fileAndPath) {
		try {
			initialize(fileAndPath);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		if (_instance==null) {
			_instance = new MyDataSingleton();
		}
		return _instance;
	}
	
	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}
	
	public List<MyDataBlock> getDataStructure(){
		return this.dataStructure;
	}
	
	public synchronized static void persist(String fileAndPath) throws IOException {
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try {
			fos = new FileOutputStream(fileAndPath);
			out = new ObjectOutputStream(fos);
			out.writeObject(getInstance());
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex2 ) {
			ex2.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (IOException ex3) {
				ex3.printStackTrace();
			}
		}
	}
	
	protected static void initialize(String fileAndPath) throws IOException {
		FileInputStream fis = null;
		ObjectInputStream in = null;
		try {
			fis = new FileInputStream(fileAndPath);
			in = new ObjectInputStream(fis);
			_instance = (MyDataSingleton)in.readObject();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		//} catch (OptionalDataException ex) {
		//	ex.printStackTrace();
		} catch (IOException ex2 ) {
			ex2.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException ex3) {
				ex3.printStackTrace();
			}
		}
	}
}

