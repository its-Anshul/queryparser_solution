package com.stackroute.datamunger.reader;

import com.stackroute.datamunger.query.*;
import com.stackroute.datamunger.query.parser.QueryParameter;
import com.stackroute.datamunger.query.parser.Restriction;

//import com.sun.org.apache.xpath.internal.operations.String;

import java.util.*;
//import com.sun.tools.javac.util.List;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
//import java.util.regex.Pattern;

public class CsvQueryProcessor implements QueryProcessingEngine {
	/*
	 * This method will take QueryParameter object as a parameter which contains the
	 * parsed query and will process and populate the ResultSet
	 */

	DataSet dataSet = new DataSet();
	long l = 1l;
	QueryParameter queryParameter = new QueryParameter();


	public DataSet getResultSet(QueryParameter queryParameter) {

	    this.queryParameter = queryParameter;
	    String fileName = queryParameter.getFileName();


	    //System.out.println(queryParameter.getFields());

		/*
		 * initialize BufferedReader to read from the file which is mentioned in
		 * QueryParameter. Consider Handling Exception related to file reading.
		 */


		/*
		 * read the first line which contains the header. Please note that the headers
		 * can contain spaces in between them. For eg: city, winner
		 */

        String line = new String();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(fileName));
            //line = reader.readLine();
            line = reader.readLine();
            reader.close();
        }catch(FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Header header = new Header();
        String[] headers;
        headers = line.split(",");
        //System.out.println(headers.length);
        //List<String> headers1 = new ArrayList<>();
        header.setHeaders(headers);



        /*
		 * read the next line which contains the first row of data. We are reading this
		 * line so that we can determine the data types of all the fields. Please note
		 * that ipl.csv file contains null value in the last column. If you do not
		 * consider this while splitting, this might cause exceptions later
		 */

        String line1 = new String();
        BufferedReader reader1;
        try {
            reader1 = new BufferedReader(new FileReader(fileName));
            //line = reader.readLine();
            //line = reader.readLine();

            for(int i=0;i<2;i++)
            {
                line1 = reader1.readLine();
            }
            //line = reader.readLine();

            reader1.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Scanner scanner = new Scanner(fileName);
        DataTypeDefinitions dataTypeDefinitions = new DataTypeDefinitions();
        dataTypeDefinitions.setDataType(line1);
//        return dataTypeDefinitions;



        /*
		 * populate the header Map object from the header array. header map is having
		 * data type <String,Integer> to contain the header and it's index.
		 */



		/*
		 * We have read the first line of text already and kept it in an array. Now, we
		 * can populate the RowDataTypeDefinition Map object. RowDataTypeDefinition map
		 * is having data type <Integer,String> to contain the index of the field and
		 * it's data type. To find the dataType by the field value, we will use
		 * getDataType() method of DataTypeDefinitions class
		 */


		/*
		 * once we have the header and dataTypeDefinitions maps populated, we can start
		 * reading from the first line. We will read one line at a time, then check
		 * whether the field values satisfy the conditions mentioned in the query,if
		 * yes, then we will add it to the resultSet. Otherwise, we will continue to
		 * read the next line. We will continue this till we have read till the last
		 * line of the CSV file.
		 */


            List<Integer> fieldsnumber = new ArrayList<>();
            List<Integer> conditionOnFields = new ArrayList<>();
            HashMap<String , Integer> headers1 = header.getHeaders();
            List<Restriction> restrictions = queryParameter.getRestrictions();
            //System.out.println(restrictions.size());
            if(restrictions != null) {

                for (int y = 0; y < restrictions.size(); y++) {
                    conditionOnFields.add(headers1.get(restrictions.get(y).getPropertyName()));
                }

                for (int z = 0; z < queryParameter.getFields().size(); z++) {
                    fieldsnumber.add(header.get(queryParameter.getFields().get(z)));
                }
            }





		/* reset the buffered reader so that it can start reading from the first line */

		/*
		 * skip the first line as it is already read earlier which contained the header
		 */

		/* read one line at a time from the CSV file till we have any lines left */

		/*
		 * once we have read one line, we will split it into a String Array. This array
		 * will continue all the fields of the row. Please note that fields might
		 * contain spaces in between. Also, few fields might be empty.
		 */

		/*
		 * if there are where condition(s) in the query, test the row fields against
		 * those conditions to check whether the selected row satifies the conditions
		 */

		//DataSet dataSet = new DataSet();

        String line2 = new String();
        BufferedReader reader2 = null;
        try {
            reader2 = new BufferedReader(new FileReader(fileName));
            //line = reader.readLine();
            //line = reader.readLine();

            for (int i = 0; i < 2; i++) {
                line2 = reader2.readLine();
            }
            //line = reader.readLine();

            //List<Boolean> answers = null;

            do{
                Row row = new Row();
                //System.out.println("sfbdjfjkd");
                boolean answer = true;
                //System.out.println(line2);
                String[] string = line2.split("," , -1);
                Filter filter = new Filter();
                List<Boolean> booleans = new ArrayList<Boolean>();
                if(restrictions == null)
                {

                }
                else {
                    for (int m = 0; m < restrictions.size(); m++) {
                        int fieldLocation = header.get(restrictions.get(m).getPropertyName());
                        //System.out.println(restrictions.get(m));
                        //System.out.println(string[fieldLocation]);
                        //System.out.println(dataTypeDefinitions.getDataType().get(fieldLocation));
                        booleans.add(filter.evaluateExpression(restrictions.get(m), string[fieldLocation], dataTypeDefinitions.getDataType().get(fieldLocation)));
                        //System.out.println("sdfjhedfuj");
                    }
                }

                //System.out.println(queryParameter.getLogicalOperators());
                if(booleans.size()==0)
                {

                }
                else{
                if(queryParameter.getLogicalOperators().size() == 0 )
                {
                        System.out.println(booleans.get(0));
                        answer = booleans.get(0);
                }
                else {
                    int n=0;
                    //for (int n = 0; n < queryParameter.getLogicalOperators().size(); n++) {
                        if (queryParameter.getLogicalOperators().get(n).equals("and") && booleans.get(n) == true && booleans.get(n + 1) == true) {
                            {
                                //System.out.println(queryParameter.getLogicalOperators().get(n) + "\n" + booleans.get(n) + "\n" + booleans.get(n+1));
                                //System.out.println(line2);
                                answer = true;
                            }
                        } else if (queryParameter.getLogicalOperators().get(n).equals("or") && (booleans.get(n) == true || booleans.get(n + 1) == true)) {
                            answer = true;
                        } else
                            answer = false;

                }
                }
                System.out.println(answer);

                if(answer == true)
                {
                //    String string1 = "";
                    int o;
                    //System.out.println(queryParameter.getFields().get(0));

                    if(queryParameter.getFields().get(0).equals("*"))
                    {

                        for (int i=0; i<headers.length; i++) {
                            row.setRow(headers[i] , string[i] );
                        }
                    }
                    else {
                           // System.out.println(":fjkdhjfhhdfhjds");
                        for (o = 0; o < queryParameter.getFields().size() ; o++) {
                            //System.out.println(header.getHeaders());
                            int fieldLocation = header.getHeaders().get(queryParameter.getFields().get(o));
                            String let = queryParameter.getFields().get(o);;
                            //System.out.println(string[fieldLocation] + "\n");
                            row.setRow(queryParameter.getFields().get(o),string[fieldLocation]);
                            //System.out.println(queryParameter.getFields().get(o) + string[fieldLocation]);
                            //System.out.println(dataSet);
                            //System.out.println(row );

                            //    string1 = string1 + string[fieldLocation] + ", ";
                        //    let = null;
                            //System.out.println(row.getRow());
                        }


                        //    int fieldLocation = header.get(queryParameter.getFields().get(o));
                  //      string1 = string1 + string[fieldLocation];
                    }

                    //System.out.println( queryParameter.getFields().get(1));

                    //System.out.println("1");
                    //Row row1 = new Row();
                    //row1 = row.getRow();
                    //System.out.println(row.getRow().toString());

                   //row.clear();
                    // queryParameter.getFields()

                }

                System.out.println(row);
                if(!row.isEmpty())
                    dataSet.setDataSet(row,l++);
            //    dataSet.put(l,row);
//                row.clear();


                //System.out.println(dataSet + "\n");

                //System.out.println("EOL\n");

              //  line2 = reader2.readLine();
            }while ((line2 = reader2.readLine()) != null);


        reader2.close();

        }catch (Exception e){
            System.out.println(e);
        }


        /*
		 * from QueryParameter object, read one condition at a time and evaluate the
		 * same. For evaluating the conditions, we will use evaluateExpressions() method
		 * of Filter class. Please note that evaluation of expression will be done
		 * differently based on the data type of the field. In case the query is having
		 * multiple conditions, you need to evaluate the overall expression i.e. if we
		 * have OR operator between two conditions, then the row will be selected if any
		 * of the condition is satisfied. However, in case of AND operator, the row will
		 * be selected only if both of them are satisfied.
		 */

		/*
		 * check for multiple conditions in where clause for eg: where salary>20000 and
		 * city=Bangalore for eg: where salary>20000 or city=Bangalore and dept!=Sales
		 */

		/*
		 * if the overall condition expression evaluates to true, then we need to check
		 * if all columns are to be selected(select *) or few columns are to be
		 * selected(select col1,col2). In either of the cases, we will have to populate
		 * the row map object. Row Map object is having type <String,String> to contain
		 * field Index and field value for the selected fields. Once the row object is
		 * populated, add it to DataSet Map Object. DataSet Map object is having type
		 * <Long,Row> to hold the rowId (to be manually generated by incrementing a Long
		 * variable) and it's corresponding Row Object.
		 */

		/* return dataset object */
		//row.clear

        System.out.println("data set" + dataSet);
        return dataSet;
	}

}
