package com.devsuperior.dsmeta.dto;

import java.time.LocalDate;

import com.devsuperior.dsmeta.entities.Sale;

public class SaleReportDTO {
	
	private Long id;
	private LocalDate date;
	private Double amount;
	private String sellerName;
	
	public SaleReportDTO(Long id, LocalDate date, Double amount, String sellerName) {
		super();
		this.id = id;
		this.date = date;
		this.amount = amount;
		this.sellerName = sellerName;
	}
	
	public SaleReportDTO(Sale entity) {
		id = entity.getId();
		date = entity.getDate();
		amount = entity.getAmount();
		sellerName = entity.getSeller().getName();
	}

	
}
