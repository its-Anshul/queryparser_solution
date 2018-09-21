package com.stackroute.datamunger.query.parser;

/*
 * This class is used for storing name of field, condition and value for
 * each conditions
 * generate getter and setter for this class,
 * Also override toString method
 * */

import java.util.HashMap;
import java.util.Map;

public class Restriction {
	private String name;
	private String value;

    @Override
    public String toString() {
        return " Name" + name + "value" + value + "condition" + condition;
    }

    private String condition;

    //Map<String , Integer> restrictions = new HashMap<>();

	// Write logic for constructor
	public Restriction(final String name, final String value, final String condition) {

		this.name = name;
		this.value = value;
		this.condition = condition;
	}

    public  String getPropertyName() {
        return name;
    }

    public  String getCondition() {
        return condition;
    }

    public  String getPropertyValue() {
        return value;
    }
}
