package com.stackroute.datamunger.query.parser;

import java.util.List;
/* 
 * This class will contain the elements of the parsed Query String such as conditions,
 * logical operators,aggregate functions, file name, fields group by fields, order by
 * fields, Query Type
 * */

public class QueryParameter {

    String fileName;
    List<String> fields;
    List<Restriction> restrictions;
    String baseQuerry;
    List<AggregateFunction> aggregateFunctions;
    List<String> logicalOperators;
    List<String> groupByFields;
    List<String> orderByFields;
    String queryType;

    public List<AggregateFunction> getAggregateFunctions() {
        return aggregateFunctions;
    }

    public List<Restriction> getRestrictions() {
        return restrictions;
    }

    public List<String> getFields() {
        return fields;
    }

    public List<String> getGroupByFields() {
        return groupByFields;
    }

    public List<String> getLogicalOperators() {
        return logicalOperators;
    }

    public List<String> getOrderByFields() {
        return orderByFields;
    }

    public String getBaseQuery() {
        return baseQuerry;
    }

    public String getFileName() {
        return fileName;
    }

    public String getQUERY_TYPE() {
        return queryType;
    }

    public void setAggregateFunctions(List<AggregateFunction> aggregateFunctions) {
        this.aggregateFunctions = aggregateFunctions;
    }

    public void setBaseQuerry(String baseQuerry) {
        this.baseQuerry = baseQuerry;
    }

    public void setFields(List<String> fields) {
        this.fields = fields;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setGroupByFields(List<String> groupByFields) {
        this.groupByFields = groupByFields;
    }

    public void setLogicalOperators(List<String> logicalOperators) {
        this.logicalOperators = logicalOperators;
    }

    public void setOrderByFields(List<String> orderByFields) {
        this.orderByFields = orderByFields;
    }

    public void setQUERY_TYPE(String querryType) {
        this.queryType = querryType;
    }

    public void setRestrictions(List<Restriction> restrictions) {
        this.restrictions = restrictions;
       // System.out.println(restrictions.get(0).getPropertyName());
    }
}
