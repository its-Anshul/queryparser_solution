package com.stackroute.datamunger.query;

import java.util.HashMap;

//Header class containing a Collection containing the headers
public class Header extends HashMap<String, Integer> {

	/**
	 * 
	 */
	private static long serialVersionUID = 1L;

	//HashMap<String , Integer> headers = new HashMap<>();

    public HashMap<String, Integer> getHeaders() {
        return this;
    }

    public void setHeaders(String[] headers) {

        for(int i=0;i<headers.length;i++)
        {
            this.put(headers[i] , i);
        }

    }

    @Override
    public Integer get(Object key) {
        return super.get(key);
    }
}
