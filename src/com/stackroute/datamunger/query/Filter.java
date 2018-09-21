package com.stackroute.datamunger.query;

import com.stackroute.datamunger.query.parser.Restriction;

//This class contains methods to evaluate expressions
public class Filter {
	
	/* 
	 * The evaluateExpression() method of this class is responsible for evaluating 
	 * the expressions mentioned in the query. It has to be noted that the process 
	 * of evaluating expressions will be different for different data types. there 
	 * are 6 operators that can exist within a query i.e. >=,<=,<,>,!=,= This method 
	 * should be able to evaluate all of them. 
	 * Note: while evaluating string expressions, please handle uppercase and lowercase 
	 * 
	 */

	//String property;
	String operator;
	String value;
	String fieldValue;
	String fieldDataType;

	public boolean evaluateExpression(Restriction restriction , String fieldValue , String fieldDataType){

	   // this.property = restriction.getPropertyName();
	    this.operator = restriction.getCondition();
	    //this.operator = restriction.getPropertyValue();
	    this.fieldValue = restriction.getPropertyValue();
	    this.value = fieldValue;
	    this.fieldDataType = fieldDataType;

	    boolean status;

	    if(operator.equals("="))
	        status = equalTo();
	    else if(operator.equals("!="))
	        status = notEqualTo();
	    else if (operator.equals(">="))
	        status = greaterThanEquals();
	    else if (operator.equals("<="))
	        status = lessThanEquals();
	    else if(operator.equals(">"))
	        status = greaterThan();
	    else
	        status = lessThan();

	    return status;

	}

    //Method containing implementation of equalTo operator


    private boolean equalTo(){
	    if(this.fieldValue.equals(value) )
	        return true;
	    else
	        return false;

    }
	

    //Method containing implementation of notEqualTo operator

    private boolean notEqualTo(){
        if(this.fieldValue.equals(value))
            return false;
        else
            return true;

    }

    private boolean greaterThan(){
	    if(Integer.parseInt(this.fieldValue) < Integer.parseInt(value))
        {
           // System.out.println("sfjbdjkbf");
            return true;
        }
        else
            return false;

    }


    private boolean lessThan(){
         //System.out.println(this.fieldValue + "sredrf" + Integer.parseInt(value));
        if(Integer.parseInt(this.fieldValue) > Integer.parseInt(value))
            return true;
        else
            return false;

    }


    private boolean greaterThanEquals(){
        if(Integer.parseInt(this.fieldValue) <= Integer.parseInt(value))
            return true;
        else
            return false;

    }


    private boolean lessThanEquals(){
        if(Integer.parseInt(this.fieldValue) >= Integer.parseInt(value))
            return true;
        else
            return false;

    }











    //Method containing implementation of greaterThan operator
	
	
	
	
	
	
	
	//Method containing implementation of greaterThanOrEqualTo operator
	
	
	
	
	
	
	//Method containing implementation of lessThan operator
	  
	
	
	
	
	//Method containing implementation of lessThanOrEqualTo operator
	
}
