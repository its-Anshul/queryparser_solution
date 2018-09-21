package com.stackroute.datamunger.query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/*
 * Implementation of DataTypeDefinitions class. This class contains a method getDataTypes() 
 * which will contain the logic for getting the datatype for a given field value. This
 * method will be called from QueryProcessors.   
 * In this assignment, we are going to use Regular Expression to find the 
 * appropriate data type of a field. 
 * Integers: should contain only digits without decimal point 
 * Double: should contain digits as well as decimal point 
 * Date: Dates can be written in many formats in the CSV file. 
 * However, in this assignment,we will test for the following date formats('dd/mm/yyyy',
 * 'mm/dd/yyyy','dd-mon-yy','dd-mon-yyyy','dd-month-yy','dd-month-yyyy','yyyy-mm-dd')
 */
public class DataTypeDefinitions {

    private static Pattern DATE_PATTERN_1 = Pattern.compile(
            "^\\d{4}-\\d{2}-\\d{2}$");

    private static Pattern DATE_PATTERN_2 = Pattern.compile(
            "^\\d{2}-\\d{3}-\\d{4}$");

    private static Pattern DATE_PATTERN_3 = Pattern.compile(
            "^\\d{2}-\\d{3}-\\d{2}$");

    private static Pattern DATE_PATTERN_4 = Pattern.compile(
            "^\\d{2}/\\d{2}/\\d{4}$");

    private static Pattern DATE_PATTERN_5 = Pattern.compile(
            "^\\d{2}-\\d{2}-\\d{4}$");

    //method stub

    Map<Integer , String > dataType = new HashMap<Integer , String>();


    public void setDataType(String input) {


		String[] data = input.split(",",-1);
		//System.out.println("sfdnjdbfjs" + data[17] + "dsfhdsfbhej");
		for(int i=0;i<data.length;i++)
		{
			Pattern pattern = Pattern.compile(".*\\D.*");
			//Pattern pattern1 = Pattern.compile(".*\\-*")


			if(data[i].equals(""))
				this.dataType.put(i , "java.lang.Object");
			else if(DATE_PATTERN_4.matcher(data[i]).matches())
				this.dataType.put(i , "java.util.Date");
			else if(DATE_PATTERN_3.matcher(data[i]).matches())
				this.dataType.put(i , "java.util.Date");
			else if(DATE_PATTERN_2.matcher(data[i]).matches())
				this.dataType.put(i , "java.util.Date");
			else if(DATE_PATTERN_1.matcher(data[i]).matches())
				this.dataType.put(i , "java.util.Date");
			else if(!pattern.matcher(data[i]).matches())
				this.dataType.put(i , "java.lang.Integer");
			else
				this.dataType.put(i , "java.lang.String");
		}



//		return this.dataType;
	}

    public Map<Integer, String> getDataType() {
        return dataType;
    }
}
