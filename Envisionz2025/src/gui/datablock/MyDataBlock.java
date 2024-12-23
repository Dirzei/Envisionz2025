package gui.datablock;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import gui.utils.GUIconstants;

public class MyDataBlock {
	private String mainFileName;
	private String mainFileAndPathName;
	
	private String DB_CONNECTION;
	private Connection conn;
    private Statement stmt;

    private List<String> dblColNames;
    private List<String> allColNames;
    private List<String> dbTableNames;
    private List<String> genColNames;
    private List<String> genColExpr;
    
	private boolean normalDBloaded;
    private boolean activeEPDS;
    private List<EPDSfileContent> epdsFileList;

	public void setMainFileName(String value){ this.mainFileName = value; }
	public String getMainFileName(){ return this.mainFileName; }

	public void setMainFileAndPathName(String value){ this.mainFileAndPathName = value; }
	public String getMainFileAndPathName(){ return this.mainFileAndPathName; }

    public void setNormalDBActive(boolean status){ this.normalDBloaded = status; }
    public boolean isNormalDBActive(){ return this.normalDBloaded; }    

    public void setEPDSActive(boolean status){ this.activeEPDS = status; }
    public boolean isEPDSActive(){ return this.activeEPDS; }

    public void setEPDSFileList(List<EPDSfileContent> contentList){ this.epdsFileList = contentList; }
    public List<EPDSfileContent> getEPDSFileList(){ return this.epdsFileList; }
    public void addEPDSFileToList(EPDSfileContent content){ this.epdsFileList.add(content); }
    public EPDSfileContent getEPDSFileContentAtIndex(int index){ return this.epdsFileList.get(index); }    
    
    public boolean isDBCorrupted() {
    	ResultSet rs;
    	try {
    		rs = this.getStatement().executeQuery("Show tables");
    		this.dbTableNames = new ArrayList<String>();
    		while (rs.next()) {
    			this.dbTableNames.add(rs.getString(1));
    		}
    		rs.close();
    	} catch (SQLException ex) {
    		ex.printStackTrace();	
    	}
    	if (this.dbTableNames!=null) {
    		if (this.dbTableNames.size()>0) {
    			return false;
    		} else {
    			return true;
    		}
    	} else {
    		return true;
    	}
    }
    
	public String getMainDBTableName(int index){ return this.dbTableNames.get(index); }

	public boolean establishDBconnection(){
		boolean retVal = false;
		this.DB_CONNECTION = new StringBuilder().append("jdbc:h2:file:").append(this.mainFileAndPathName).append(GUIconstants.STD_PARAMS_MODEL).toString();
		if (this.getConnection()==null){
			try {
				this.conn = DriverManager.getConnection(this.DB_CONNECTION, GUIconstants.DB_USER, GUIconstants.DB_PASSWORD);
				this.stmt = this.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				retVal = true;
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		} else {
			System.out.println("DB connection already established!");
			retVal = true;
		}
		return retVal;
	}
	
    public String getDBConnectionString(){ return this.DB_CONNECTION; }
	public Connection getConnection(){ return this.conn; }
	public Statement getStatement(){ return this.stmt; }
	public void closeDBConnection(){
    	try{
            if(stmt!=null) stmt.close();
        } catch(SQLException se2) {
        	se2.printStackTrace();
        }
    	try {
    		if(conn!=null) conn.close();
        } catch(SQLException se){
        	se.printStackTrace();
        }
    	this.stmt = null;
    	this.conn = null;
	}

	public int gerConvRunCountForMultiRunNode(String multiRun) {
		int retVal = 0;
		boolean found = false;
		int index = -1;
		for (int i=0; i<this.getEPDSFileList().size(); i++) {
			if (multiRun.toLowerCase().equals(this.getEPDSFileList().get(i).getDirName().toLowerCase()) && !found){
				found = true;
				index = i;
			}
		}
		if (found && index>=0) {
			for (int i=0; i<this.getEPDSFileList().get(index).getRunList().size(); i++) {
				if (this.getEPDSFileList().get(index).getConvergedList().get(i)) {
					retVal++;
				}
			}
		}
		return retVal;
	}
	
	public int getIndexForName(String name){
		int retVal = 0;
		if (this.isEPDSActive()){
			boolean found = false;
			for (int i=0; i<this.getEPDSFileList().size(); i++){
				if (!found){
					if (this.getEPDSFileList().get(i).getDirName().toLowerCase().equals(name.toLowerCase())){
						retVal = i;
						found = true;
					}
				}
			}			
		} else {
			
		}
		return retVal;
	}

	public List<String> getDblColumnNames(String name){ 
		this.dblColNames = new ArrayList<String>();
		
		ResultSet rs;
		try {
			if (this.getStatement()==null){
				this.establishDBconnection();
			}
			rs = this.getStatement().executeQuery("Show tables");
			this.dbTableNames = new ArrayList<String>();
			while(rs.next()) {
				this.dbTableNames.add(rs.getString(1));
		    }
			//this.dbTableNamesIndex = 0;
			rs.close();
			
			int index = this.getIndexForName(name);

	    	this.getStatement().execute("SELECT COLUMN_NAME, DATA_TYPE FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA = 'PUBLIC' AND TABLE_NAME = '"+dbTableNames.get(index)+"'");
			rs = this.getStatement().getResultSet();
			while(rs.next()) {
				if (!rs.getString("COLUMN_NAME").toUpperCase().equals("ID")){
					if (rs.getString("DATA_TYPE").toUpperCase().startsWith("DOUBLE")){
						this.dblColNames.add(rs.getString("COLUMN_NAME"));
					}
				}
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return this.dblColNames;
	}

	public List<String> getAllColumnNames(String name){
		this.allColNames = new ArrayList<String>();
		
		ResultSet rs;
		try {
			rs = this.getStatement().executeQuery("Show tables");
			this.dbTableNames = new ArrayList<String>();
			while(rs.next()) {
				this.dbTableNames.add(rs.getString(1));
		    }
			//this.dbTableNamesIndex = 0;
			rs.close();

			int index = this.getIndexForName(name);
			
	    	this.getStatement().execute("SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA = 'PUBLIC' AND TABLE_NAME = '"+dbTableNames.get(index)+"'");
			rs = this.getStatement().getResultSet();
			while(rs.next()) {
				if (!rs.getString("COLUMN_NAME").toUpperCase().equals("ID")){
					this.allColNames.add(rs.getString("COLUMN_NAME"));
				}
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return this.allColNames;
	}

	public List<String> getGeneratedColumnNames(String name){ 
		this.genColNames = new ArrayList<String>();
		
		ResultSet rs;
		try {
			if (this.getStatement()==null){
				this.establishDBconnection();
			}
			rs = this.getStatement().executeQuery("Show tables");
			this.dbTableNames = new ArrayList<String>();
			while(rs.next()) {
				this.dbTableNames.add(rs.getString(1));
		    }
			//this.dbTableNamesIndex = 0;
			rs.close();
			
			int index = this.getIndexForName(name);

	    	this.getStatement().execute("SELECT COLUMN_NAME, DATA_TYPE FROM INFORMATION_SCHEMA.COLUMNS WHERE IS_GENERATED = 'ALWAYS' AND TABLE_NAME = '"+dbTableNames.get(index)+"'");
			rs = this.getStatement().getResultSet();
			while(rs.next()) {
				if (!rs.getString("COLUMN_NAME").toUpperCase().equals("ID")){
					if (rs.getString("DATA_TYPE").toUpperCase().startsWith("DOUBLE")){
						this.genColNames.add(rs.getString("COLUMN_NAME"));
					}
				}
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return this.genColNames;
	}

	public List<String> getGeneratedColumnExpr(String name){ 
		this.genColExpr = new ArrayList<String>();
		
		ResultSet rs;
		try {
			if (this.getStatement()==null){
				this.establishDBconnection();
			}
			rs = this.getStatement().executeQuery("Show tables");
			this.dbTableNames = new ArrayList<String>();
			while(rs.next()) {
				this.dbTableNames.add(rs.getString(1));
		    }
			//this.dbTableNamesIndex = 0;
			rs.close();
			
			int index = this.getIndexForName(name);

	    	this.getStatement().execute("SELECT COLUMN_NAME, DATA_TYPE, GENERATION_EXPRESSION FROM INFORMATION_SCHEMA.COLUMNS WHERE IS_GENERATED = 'ALWAYS' AND TABLE_NAME = '"+dbTableNames.get(index)+"'");
			rs = this.getStatement().getResultSet();
			while(rs.next()) {
				if (!rs.getString("COLUMN_NAME").toUpperCase().equals("ID")){
					if (rs.getString("DATA_TYPE").toUpperCase().startsWith("DOUBLE")){
						this.genColExpr.add(rs.getString("GENERATION_EXPRESSION"));
					}
				}
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return this.genColExpr;
	}

	public String getColumnVarType(String name, String colName){
		String retVal = new String();
		
		ResultSet rs;
		try {
			rs = this.getStatement().executeQuery("Show tables");
			this.dbTableNames = new ArrayList<String>();
			while(rs.next()) {
				this.dbTableNames.add(rs.getString(1));
		    }
			//this.dbTableNamesIndex = 0;
			rs.close();

			int index = this.getIndexForName(name);
			
			
			//http://www.h2database.com/html/systemtables.html?highlight=COLUMN_NAME&search=column_name#firstFound
	    	this.getStatement().execute("SELECT COLUMN_NAME, DATA_TYPE FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA = 'PUBLIC' AND TABLE_NAME = '"+dbTableNames.get(index)+"'");
			rs = this.getStatement().getResultSet();
			while(rs.next()) {
				if (rs.getString("COLUMN_NAME").toUpperCase().equals(colName)){
					retVal = rs.getString("DATA_TYPE");
				}
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return retVal;
	}

	public List<Double> getDistinctDblValueList(String tableName, String varName){
		List<Double> retList = new ArrayList<Double>();
		ResultSet rs = null;
		if (DB_CONNECTION!=null) {
			try {
				String cmd = new StringBuilder().append("SELECT DISTINCT \"").append(varName).append("\" FROM ").append(tableName).toString();
				System.out.println("getDistinctValueList: "+cmd);
				rs = this.getStatement().executeQuery(cmd);
				rs.last();
				int xVarCount = rs.getRow();
				rs.beforeFirst();
				for (int i=1; i<=xVarCount; i++) {
					rs.absolute(i);
					retList.add(rs.getDouble(varName));
				}
				if (rs!=null){
		    		rs.close();
		    	}
			} catch (SQLException se) {
				se.printStackTrace();
			} finally {
				try {
					if (rs!=null) rs.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
		} else {
			System.out.println("Connection to db not possible - file name is null!");
		}
		return retList;
	}

	public List<String> getDistinctStrValueList(String tableName, String varName){
		List<String> retList = new ArrayList<String>();
		ResultSet rs = null;
		if (DB_CONNECTION!=null) {
			try {
				String cmd = new StringBuilder().append("SELECT DISTINCT \"").append(varName).append("\" FROM ").append(tableName).toString();
				System.out.println("getDistinctValueList: "+cmd);
				rs = this.getStatement().executeQuery(cmd);
				rs.last();
				int xVarCount = rs.getRow();
				rs.beforeFirst();
				for (int i=1; i<=xVarCount; i++) {
					rs.absolute(i);
					if (rs.getString(varName)!=null) {
						retList.add(rs.getString(varName));
					}
				}
				if (rs!=null){
		    		rs.close();
		    	}
			} catch (SQLException se) {
				se.printStackTrace();
			} finally {
				try {
					if (rs!=null) rs.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
		} else {
			System.out.println("Connection to db not possible - file name is null!");
		}
		return retList;
	}

	public String getValuesForNamesInDBcmd(int index, String allVars){
		String cmdStr = "";

		cmdStr = new StringBuilder().append("SELECT ").append(allVars).append(" FROM ").append(dbTableNames.get(index)).toString();

		return cmdStr;
	}

	public HashMap<String, Double> getValuesForResultRow(List<String> varNames, String cmd, int row, HashMap<String, String> aliasMap){
		HashMap<String, Double> retMap = new HashMap<String, Double>();

	    ResultSet rs = null;

	    if (DB_CONNECTION!=null){
	    	try {
	    		rs = this.getStatement().executeQuery(cmd);

	    		rs.absolute(row);

	    		for (int i=0; i<varNames.size(); i++){
	    			retMap.put(aliasMap.get(varNames.get(i)), rs.getDouble(varNames.get(i)));
	    		}
	    		rs.close();
	    	} catch (SQLException se){
	    		se.printStackTrace();
	    	} finally {
	    		try {
	    			if(rs!=null) rs.close();
	    		} catch(SQLException se){
	    			se.printStackTrace();
	    		}
	    	}
	    } else {
	    	System.out.println("Connection to db not possible - file name is null!");
	    }
		return retMap;
	}
	
    public void alterTableWithNewCustomColumn(int index, String colName, String eqn){
    	//String locExtCmd = new StringBuilder().append("ALTER").append(" TABLE ").append(dbTableNames.get(index)).append(" ADD \"").append(colName.trim()).append("\" DOUBLE  AS (").append(eqn).append(")").toString();
    	String locExtCmd = new StringBuilder().append("ALTER").append(" TABLE ").append(dbTableNames.get(index)).append(" ADD COLUMN \"").append(colName.trim()).append("\" DOUBLE GENERATED ALWAYS AS (").append(eqn).append(")").toString();
    	if (colName.trim().startsWith("\"") && colName.trim().endsWith("\"")){
        	//locExtCmd = new StringBuilder().append("ALTER").append(" TABLE ").append(dbTableNames.get(index)).append(" ADD ").append(colName.trim()).append(" DOUBLE  AS (").append(eqn).append(")").toString();
        	locExtCmd = new StringBuilder().append("ALTER").append(" TABLE ").append(dbTableNames.get(index)).append(" ADD COLUMN ").append(colName.trim()).append(" DOUBLE GENERATED ALWAYS AS (").append(eqn).append(")").toString();
    	}
    	System.out.println(locExtCmd);
    	try {
    		this.getStatement().executeUpdate(locExtCmd);
    	} catch (SQLException ex){
    		System.out.println(ex);
    	}
    }

	public List<String> getNonDblColumnNames(int index){
		List<String> retList = new ArrayList<String>();
		ResultSet rs = null;
	    try {
        	rs = this.stmt.executeQuery("SELECT * FROM "+this.getMainDBTableName(index)+" WHERE (\"ID\"=1)");

        	ResultSetMetaData meta = rs.getMetaData();

        	int cols = meta.getColumnCount();
        	for (int i=1; i<=cols; i++){
         		//if (!meta.getColumnTypeName(i).toUpperCase().equals("DOUBLE") && !meta.getColumnTypeName(i).toUpperCase().equals("DOUBLE PRECISION")){
         		if (!meta.getColumnTypeName(i).toUpperCase().equals("DOUBLE")){
         			if (!rs.getMetaData().getColumnName(i).toUpperCase().equals("ID")){
         				retList.add(rs.getMetaData().getColumnName(i));
          			}
        		}
        	}
        	//rs.close();
 		} catch (SQLException se){
        	se.printStackTrace();
        } /*finally {
        	try {
        		if(rs!=null) rs.close();
            } catch(SQLException se){
            	se.printStackTrace();
            }
        }*/
	    return retList;
	}

	public List<DBColumnTypeData> getVarNamesAsListFromDB(int index){
		List<DBColumnTypeData> retList = new ArrayList<DBColumnTypeData>();
		ResultSet rs = null;
	    try {
        	rs = this.getStatement().executeQuery("SELECT * FROM "+this.getMainDBTableName(index)+" WHERE (\"ID\"=1)");

        	ResultSetMetaData meta = rs.getMetaData();

        	int cols = meta.getColumnCount();
        	for (int i=1; i<=cols; i++){
         		//if (!meta.getColumnTypeName(i).toUpperCase().equals("DOUBLE") && !meta.getColumnTypeName(i).toUpperCase().equals("DOUBLE PRECISION")){
             	if (!meta.getColumnTypeName(i).toUpperCase().equals("DOUBLE")){
         			if (!rs.getMetaData().getColumnName(i).toUpperCase().equals("ID")){
         				DBColumnTypeData data = new DBColumnTypeData();
         				data.setName(rs.getMetaData().getColumnName(i));
         				data.setDataType(meta.getColumnTypeName(i));
         				retList.add(data);
          			}
        		}
        	}
        	//rs.close();
 		} catch (SQLException se){
        	se.printStackTrace();
        } /*finally {
        	try {
        		if(rs!=null) rs.close();
            } catch(SQLException se){
            	se.printStackTrace();
            }
        }*/
	    return retList;
	}

	public String getFirstValue(int index, String name){
		String retVal = "";
		ResultSet rs = null;
	    try {
    		String query = "";

       		query = new StringBuilder().append("SELECT ").append("\"").append(name).append("\"").append(" FROM ").append(this.getMainDBTableName(index)).append(" WHERE ID=1").toString();

       		rs = this.getStatement().executeQuery(query);

       		if (rs.next()){
       			retVal = String.valueOf(rs.getObject(new StringBuilder().append(name).toString()));
       		}
    		rs.close();
 	    } catch (SQLException se){
        	se.printStackTrace();
        } finally {
        	try {
        		if(rs!=null) rs.close();
            } catch(SQLException se){
            	se.printStackTrace();
            }
        }
	    return retVal;
	}

	public boolean updateDBcolumn(int index, String name, String type, JPanel owner){
		boolean retMap = false;
	    try {
       		String query = new StringBuilder().append("ALTER TABLE "+this.getMainDBTableName(index)+" ALTER COLUMN ").append("\"").append(name).append("\"").append(" SET DATA TYPE ").append(type).toString();
       		System.out.println(query);
       		this.getStatement().executeUpdate(query);
		} catch (SQLException se){
			JOptionPane.showMessageDialog(owner, "Selected Data Type not compatible with column content!", "Modify error #001",JOptionPane.ERROR_MESSAGE);
        	//se.printStackTrace();
        }
	    return retMap;
	}

	public double getMinMaxForName(String cmd) {
		double dblVal = -9999;
		//List<String> nameList = new ArrayList<String>();
		//nameList.add(colName);
		
		System.out.println("getMinForName - DB CMD: "+cmd);
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery(cmd);
			
			while (rs.next()) {
				dblVal = rs.getDouble("MINMAXVAL");
			}
			
			rs.close();
			stmt.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			try {
				if (rs!=null) {
					rs.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
			try {
				if (stmt!=null) {
					stmt.close();
				}
			} catch (SQLException se2) {
				se2.printStackTrace();
			}
		}
		stmt = null;
		rs = null;
		
		return dblVal;
	}
}
