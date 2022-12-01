package com.mecavia.site.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.mecavia.site.dto.ProductDto;
import com.mecavia.site.dto.ProductVariantDto;
import com.mecavia.site.dto.ResponseDto;

public interface ProductController {
	@PostMapping("/saveproduct")
	ResponseEntity<ResponseDto> saveproduct(@RequestBody ProductDto productDto);
	@PutMapping("/updateproduct")
	ResponseEntity<ResponseDto> updateproduct(@RequestBody ProductDto productDto);
	@PutMapping("/addvariant")
	ResponseEntity<ResponseDto> addvariant(@RequestBody ProductVariantDto productVariantDto);
	@PutMapping("/removevariant")
	ResponseEntity<ResponseDto> removevariant(@RequestBody ProductVariantDto productVariantDto);
	@GetMapping("/getproducts")
	ResponseEntity<ResponseDto> getproducts();
}
