package com.mecavia.site.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mecavia.site.entity.Stock;
import com.mecavia.site.util.Status;

public interface StockRepo extends JpaRepository<Stock, Integer> {
	boolean existsByPcsmidAndStatus(int pcsmid,Status status);
	Stock findStockByPcsmidAndStatus(int pcsmid,Status status);
	boolean existsByIdAndStatus(int id,Status status);
}
