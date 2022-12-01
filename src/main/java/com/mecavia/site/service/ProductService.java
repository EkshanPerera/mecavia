package com.mecavia.site.service;

import com.mecavia.site.dto.ProductDto;
import com.mecavia.site.dto.ProductVariantDto;

public interface ProductService {
	String saveProduct(ProductDto productDto);
	String updateProduct(ProductDto productDto);
	ProductDto savevariant(ProductVariantDto productVariantDto);
	ProductDto deductvariant(ProductVariantDto productVariantDto);
}
