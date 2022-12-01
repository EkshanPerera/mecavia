package com.mecavia.site.serviceimplies;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mecavia.site.dto.ProductDto;
import com.mecavia.site.dto.ShrinkedProductDto;
import com.mecavia.site.repo.ProductRepo;

@Service
@Transactional
public class ExtendedProductService implements com.mecavia.site.service.ExtendedProductService{
	
	@Autowired
	private ProductRepo productRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public List<ShrinkedProductDto> getProducts() {
		return productRepo.getShrinkedProducts();
	}
	
	
	@Override
	public ShrinkedProductDto getShrinkedProduct(int id) {
		if(productRepo.existsById(id)) {
			return productRepo.getShrinkedProduct(id);
		}else {
			throw new NullPointerException();
		}
		
	}
	

	@Override
	public ProductDto getProduct(int id) {
		if(productRepo.existsById(id)) {
			return modelMapper.map(productRepo.findByid(id), ProductDto.class);
		}else {
			throw new NullPointerException();
		}
		
	}
}
