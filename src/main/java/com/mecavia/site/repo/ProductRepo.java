package com.mecavia.site.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mecavia.site.dto.ShrinkedProductDto;
import com.mecavia.site.entity.Product;

public interface ProductRepo extends JpaRepository<Product, Integer> {
	Product findByid(int id);
	@Query("select new com.mecavia.site.dto.ShrinkedProductDto(id,code,name,img1URL,status) from Product")
	List<ShrinkedProductDto> getShrinkedProducts();
	@Query("select new com.mecavia.site.dto.ShrinkedProductDto(id,code,name,img1URL,status) from Product where id=?1")
	ShrinkedProductDto getShrinkedProduct(int id);
}
