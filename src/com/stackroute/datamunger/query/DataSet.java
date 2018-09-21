package com.stackroute.datamunger.query;

import java.util.HashMap;
import java.util.LinkedHashMap;

//This class will be acting as the DataSet containing multiple rows
public class DataSet extends LinkedHashMap<Long, Row> {

	//private long num = 1L;


    public void setDataSet( Row row , long l)
    {
       // System.out.println( l+ " data row"  + row);
        this.put( l, row);

    }

}
