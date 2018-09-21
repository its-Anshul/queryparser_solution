package com.stackroute.datamunger.query.parser;

//import com.sun.tools.javac.util.List;
import java.util.List;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class QueryParser {

	
	/*
	 * This method will parse the queryString and will return the object of
	 * QueryParameter class
	 */
	public QueryParameter parseQuery(String queryString) {
	
	    QueryParameter queryParameter = new QueryParameter();
	    /*
		 * extract the name of the file from the query. File name can be found after the
		 * "from" clause.
		 */

        //System.out.println(queryString);
        String[] arrOfStr = queryString.split(" where |\\ order by |\\ group by ");
        String baseQuerry = arrOfStr[0];
        queryParameter.setBaseQuerry(baseQuerry);
        //return baseQuerry;


        String[] arrOfSt = queryString.split("from ");
        String[] fileName = arrOfSt[1].split(" ");
        queryParameter.setFileName(fileName[0]);
		/*
		 * extract the order by fields from the query string. Please note that we will
		 * need to extract the field(s) after "order by" clause in the query, if at all
		 * the order by clause exists. For eg: select city,winner,team1,team2 from
		 * data/ipl.csv order by city from the query mentioned above, we need to extract
		 * "city". Please note that we can have more than one order by fields.
		 */

        String[] arrOfStr1 = queryString.split(" order by ");
        if(arrOfStr1.length == 1) {
            queryParameter.setOrderByFields(null);
        }
        else {
            String afterOrderBy = arrOfStr1[1];
            //System.out.print("AfterOrderBy**" + AfterOrderBy);
            List<String> orderByField = new ArrayList<String>();
            String[] OrderByFields = afterOrderBy.split(",");
            for (int i = 0; i < OrderByFields.length; i++)
                orderByField.add(OrderByFields[i]);
            queryParameter.setOrderByFields(orderByField);
        }



        /*
		 * extract the group by fields from the query string. Please note that we will
		 * need to extract the field(s) after "group by" clause in the query, if at all
		 * the group by clause exists. For eg: select city,max(win_by_runs) from
		 * data/ipl.csv group by city from the query mentioned above, we need to extract
		 * "city". Please note that we can have more than one group by fields.
		 */

        String [] beforeOrder = queryString.split(" order by ");
        String[] arrOfStr2 = beforeOrder[0].split(" group by ");
        if(arrOfStr2.length == 1)
            queryParameter.setGroupByFields(null);
        else {
            String afterGroupBy = arrOfStr2[1];
            List<String> groupByFields = new ArrayList<>();
            String[] groupByFields1 = afterGroupBy.split(",");
            for (int i = 0; i < groupByFields1.length; i++)
                groupByFields.add(groupByFields1[i]);
            queryParameter.setGroupByFields(groupByFields);
        }


        /*
		 * extract the selected fields from the query string. Please note that we will
		 * need to extract the field(s) after "select" clause followed by a space from
		 * the query string. For eg: select city,win_by_runs from data/ipl.csv from the
		 * query mentioned above, we need to extract "city" and "win_by_runs". Please
		 * note that we might have a field containing name "from_date" or "from_hrs".
		 * Hence, consider this while parsing.
		 */

        String[] arrOfStr3 = queryString.split(" from ");
        String[] notanswer = arrOfStr3[0].split("select ");
        //System.out.println(notanswer[1]);
        String[] answer = notanswer[1].split(",");
        List<String> fields = new ArrayList<String>();
        for(int i=0;i < answer.length;i++)
        {
            //System.out.println(answer[i]);
            answer[i] = answer[i].trim();
            fields.add(answer[i]);
        }

        queryParameter.setFields(fields);
       // fields.clear();
        /*
		 * extract the conditions from the query string(if exists). for each condition,
		 * we need to capture the following: 
		 * 1. Name of field 
		 * 2. condition 
		 * 3. value
		 * 
		 * For eg: select city,winner,team1,team2,player_of_match from data/ipl.csv
		 * where season >= 2008 or toss_decision != bat
		 * 
		 * here, for the first condition, "season>=2008" we need to capture: 
		 * 1. Name of field: season 
		 * 2. condition: >= 
		 * 3. value: 2008
		 * 
		 * the query might contain multiple conditions separated by OR/AND operators.
		 * Please consider this while parsing the conditions.
		 * 
		 */
        String NotBaseQuerry;
        String[] arrOfStr4 =  queryString.split(" where ");
        if(arrOfStr4.length == 1)
            NotBaseQuerry = null;
        else
            NotBaseQuerry = arrOfStr4[1];
        //System.out.println(queryString);
        //System.out.println(NotBaseQuerry);
        List<Restriction> restrictions = new ArrayList<Restriction>();
        if(NotBaseQuerry == null)
             restrictions = null;
        else {
           // System.out.println(NotBaseQuerry);
            String[] arrOfStr5 = NotBaseQuerry.split(" and |\\ not |\\ or ");
            for (int i = 0; i < arrOfStr5.length; i++) {
                //System.out.println(arrOfStr5[i]);

                String regex = "";
                if(arrOfStr5[i].contains(">="))
                    regex = regex + ">=";
                else if(arrOfStr5[i].contains("<="))
                    regex = regex + "<=";
                else if(arrOfStr5[i].contains(">"))
                    regex = regex + ">";
                else if(arrOfStr5[i].contains("<"))
                    regex = regex + "<";
                else if(arrOfStr5[i].contains("!="))
                    regex = regex + "!=";
                else
                    regex = regex + "=";

                System.out.println(regex);
                StringTokenizer st3 = new StringTokenizer(arrOfStr5[i],regex);
                //System.out.println(st3.toString());
                String[] answer1 = new String[2];
                int x=0;
                while (st3.hasMoreTokens())
                {
                    //System.out.println(st3.nextToken());
                    answer1[x] = st3.nextToken().trim();
                    System.out.println(answer1[x]);
                    x++;
                }
                Restriction obj = new Restriction(answer1[0], answer1[1], regex);
               // System.out.println(obj);
                restrictions.add(obj);
                //System.out.println(restrictions + "\n");
                obj = null;


                //String[] answer1 = arrOfStr5[i].split(" |\\'");
                //System.out.print(answer[0]);
                //System.out.print(answer[1]);
                //System.out.print(answer[2]);
                //String[] Answer = NotBaseQuerry.split("\\'");
             //   System.out.println("obj " + obj);
                //System.out.println(obj.getPropertyName());
                //System.out.println(restrictions.get(i).getPropertyName());
            }

            //System.out.println("List data" + restrictions);
          //  restrictions.clear();
            //System.out.println(restrictions.get(0).getPropertyName());
            //System.out.println(restrictions.get(1).getPropertyName());
            queryParameter.setRestrictions(restrictions);
        }

        /*
		 * extract the logical operators(AND/OR) from the query, if at all it is
		 * present. For eg: select city,winner,team1,team2,player_of_match from
		 * data/ipl.csv where season >= 2008 or toss_decision != bat and city =
		 * bangalore
		 * 
		 * the query mentioned above in the example should return a List of Strings
		 * containing [or,and]
		 */
        List<String> logicalOperators = new ArrayList<String>();

        if(NotBaseQuerry==null)
             logicalOperators = null;
        else {
            String[] arrOfStr6 = NotBaseQuerry.split(" ");
            if (arrOfStr6.length <= 1)
                logicalOperators = null;

            for (int i = 0; i < arrOfStr6.length; i++) {
                if (arrOfStr6[i].equals("and") || arrOfStr6[i].equals("or") || arrOfStr6[i].equals("not")) {
                    logicalOperators.add(arrOfStr6[i]);
                }
            }
            queryParameter.setLogicalOperators(logicalOperators);
        }



        /*
		 * extract the aggregate functions from the query. The presence of the aggregate
		 * functions can determined if we have either "min" or "max" or "sum" or "count"
		 * or "avg" followed by opening braces"(" after "select" clause in the query
		 * string. in case it is present, then we will have to extract the same. For
		 * each aggregate functions, we need to know the following: 
		 * 1. type of aggregate function(min/max/count/sum/avg) 
		 * 2. field on which the aggregate function is being applied
		 * 
		 * Please note that more than one aggregate function can be present in a query
		 * 
		 * 
		 */

        List<AggregateFunction> listOfAggregateFunction= new ArrayList<AggregateFunction>();
        String[] splitSpace = queryString.split(" ");
        for(int i=0;i<splitSpace.length;i++)
        {
            if(splitSpace[i].contains("count") || splitSpace[i].contains("sum") || splitSpace[i].contains("avg") || splitSpace[i].contains("min") || splitSpace[i].contains("max"))
            {

                //System.out.println(SplitSpace[i]);
                String[] getAgreggate = splitSpace[i].split(",");

                for (int j = 0; j < getAgreggate.length; j++) {
                    //  System.out.println(GetAgreggate[j]);
                    if(getAgreggate[j].contains("count") || getAgreggate[j].contains("sum") || getAgreggate[j].contains("avg") || getAgreggate[j].contains("min") || getAgreggate[j].contains("max")) {
                        String[] agg = getAgreggate[j].split("\\(");
                        String[] aggFun = agg[1].split("\\)");
                        AggregateFunction obj = new AggregateFunction(aggFun[0], agg[0]);
                        listOfAggregateFunction.add(obj);
                    }
                }

            }
        }
        queryParameter.setAggregateFunctions(listOfAggregateFunction);



        return queryParameter;
	}
	
	
}
