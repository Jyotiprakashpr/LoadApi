package com.jp.bindings;

import java.util.Date;

import lombok.Data;

@Data
public class LoadBinding {
	  private Long id;

	    private String loadingPoint;
	    private String unloadingPoint;
	    private String productType;
	    private String truckType;
	    private int noOfTrucks;
	    private int weight;
	    private String comment;
	    private String shipperId;
	    private Date date;

}
