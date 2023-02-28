package com.edison.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Locale;
import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edison.model.InventoryReport;
import com.edison.model.SalesReport;
import com.edison.service.OrderService;
import com.edison.service.ReportService;

@RestController
public class ReportController {

	@Autowired
	private ReportService reportService;
	
	@GetMapping("/reports/sales")
	public SalesReport generateSalesReport(
			@RequestParam("startDate") String startDate,
			@RequestParam("endDate") String endDate) {
		Date StartDate = Date.valueOf(startDate);
		Date EndDate = Date.valueOf(endDate);
		return reportService.generateSalesReport(StartDate, EndDate);
	}

	@GetMapping("/reports/inventory")
	public InventoryReport generateInventoryReport() {
		return reportService.generateInventoryReport();
	}
}
