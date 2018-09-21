package com.stackroute.datamunger.query.parser;

/* This class is used for storing name of field, aggregate function for
 * each aggregate function
 * generate getter and setter for this class,
 * Also override toString method
 * */

public class AggregateFunction {

	private String field;
	private String function;
	// Write logic for constructor
	public AggregateFunction(final String field, final String function) {
		this.field = field;
		this.function= function;

	}

	public String getFunction(){
		return function;
	}

	public String getField() {
		return field;
	}
}
