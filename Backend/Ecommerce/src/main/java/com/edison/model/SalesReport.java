package com.edison.model;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalesReport {
	
	private Date startDate;

	private Date endDate;

	private double totalSales;



}
