package com.example.artroo;

import java.io.Serializable;
//import java.math.BigDecimal;

public class DataForMain implements Serializable{

	 String name;
	 String amount;

	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    public String getAmount() {
	        return amount;
	    }

	    public void setAmount(String amount) {
	        this.amount = amount;
	    }

}
