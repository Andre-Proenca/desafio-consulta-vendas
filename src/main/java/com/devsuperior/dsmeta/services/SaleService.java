package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SaleReportDTO;
import com.devsuperior.dsmeta.dto.SaleSummaryDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;
	
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}

	public List<SaleSummaryDTO> searchSalesSummary(String minDate, String maxDate) {
		LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		LocalDate endDate = maxDate != null ? LocalDate.parse(maxDate) : today;
		LocalDate startDate = minDate != null ? LocalDate.parse(minDate) : endDate.minusYears(1L);
		
		return repository.searchSalesSummary(startDate, endDate);
	}
	
	public Page<SaleReportDTO> searchSalesReport(String name, String minDate, String maxDate, Pageable pageable) {
		LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		LocalDate endDate = maxDate != null ? LocalDate.parse(maxDate) : today;
		LocalDate startDate = minDate != null ? LocalDate.parse(minDate) : endDate.minusYears(1L);
		
		Page<Sale> result = repository.searchSalesReport(name != null ? name : "", startDate, endDate, pageable);
		
		return result.map(x -> new SaleReportDTO(x));
	}
}
