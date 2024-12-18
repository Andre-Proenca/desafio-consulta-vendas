package com.devsuperior.dsmeta.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.dsmeta.dto.SaleSummaryDTO;
import com.devsuperior.dsmeta.entities.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long> {
	
	@Query("SELECT new com.devsuperior.dsmeta.dto.SaleSummaryDTO(obj.seller.name, SUM(obj.amount)) " +
			"FROM Sale obj " +
			"WHERE obj.date BETWEEN :minDate AND :maxDate " +
			"GROUP BY obj.seller.name")
	List<SaleSummaryDTO> searchSalesSummary(LocalDate minDate, LocalDate maxDate);
	
	@Query("SELECT obj FROM Sale obj "
			+ "WHERE (:name IS NULL OR UPPER(obj.seller.name) LIKE UPPER(CONCAT('%', :name))) "
			+ "AND obj.date BETWEEN :minDate AND :maxDate")
	Page<Sale> searchSalesReport(String name, LocalDate minDate, LocalDate maxDate, Pageable pageable);

}
