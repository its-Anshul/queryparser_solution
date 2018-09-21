package com.stackroute.datamunger.query;

//import com.sun.org.apache.xpath.internal.operations.String;

import java.util.HashMap;

//Contains the row object as ColumnName/Value. Hence, HashMap is being used
public class Row extends HashMap<String, String>{

	/**
	 * 
	 */
	//private static long serialVersionUID = 1L;

	//HashMap<String , String> row = new HashMap<>();

    //public HashMap<String, String> getRow() {
      //  return  row;
    //}

    public void setRow(String field , String value) {
        //this.row = row;
        //System.out.println(field);
        this.put(field,value);

        //System.out.println(row);

    }

}
