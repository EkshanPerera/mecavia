package com.mecavia.site.controllerimplies;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mecavia.site.dto.ProductDto;
import com.mecavia.site.dto.ProductVariantDto;
import com.mecavia.site.dto.ResponseDto;
import com.mecavia.site.dto.ShrinkedProductDto;
import com.mecavia.site.serviceimplies.ExtendedProductService;
import com.mecavia.site.serviceimplies.ProductService;
import com.mecavia.site.util.VarList;

@RestController
@RequestMapping
@CrossOrigin
public class ProductController implements com.mecavia.site.controller.ProductController {
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ResponseDto responseDto;
	
	@Autowired
	private ExtendedProductService extendedProductService;
	@Override
	public ResponseEntity<ResponseDto> saveproduct(ProductDto productDto) {
		try {
			String res = productService.saveProduct(productDto);
			if(res.equals("00")) {
				responseDto.setCode(VarList.RSP_SUCCESS);
				responseDto.setMessage("success");
				responseDto.setContent(productDto);
				return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.ACCEPTED);
			}else {
				responseDto.setCode(VarList.RSP_FAIL);
				responseDto.setMessage("fail");
				responseDto.setContent(null);
				return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			responseDto.setCode(VarList.RSP_FAIL);
			responseDto.setMessage("INTERNAL SERVER ERROR");
			responseDto.setContent(e.getMessage());
			return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<ResponseDto> updateproduct(ProductDto productDto) {
		try {
			String res = productService.updateProduct(productDto);
			if(res.equals("00")) {
				responseDto.setCode(VarList.RSP_SUCCESS);
				responseDto.setMessage("success");
				responseDto.setContent(productDto);
				return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.ACCEPTED);
			}else {
				responseDto.setCode(VarList.RSP_FAIL);
				responseDto.setMessage("fail");
				responseDto.setContent(null);
				return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			responseDto.setCode(VarList.RSP_FAIL);
			responseDto.setMessage("INTERNAL SERVER ERROR");
			responseDto.setContent(e.getMessage());
			return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<ResponseDto> addvariant(ProductVariantDto productVariantDto) {
		try {
			ProductDto productDto  = productService.savevariant(productVariantDto);
			if(productDto!=null) {
				responseDto.setCode(VarList.RSP_SUCCESS);
				responseDto.setMessage("success");
				responseDto.setContent(productDto);
				return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.ACCEPTED);
			}else {
				responseDto.setCode(VarList.RSP_FAIL);
				responseDto.setMessage("fail");
				responseDto.setContent(null);
				return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			responseDto.setCode(VarList.RSP_FAIL);
			responseDto.setMessage("INTERNAL SERVER ERROR");
			responseDto.setContent(e.getMessage());
			return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<ResponseDto> removevariant(ProductVariantDto productVariantDto) {
		try {
			ProductDto productDto  = productService.deductvariant(productVariantDto);
			if(productDto!=null) {
				responseDto.setCode(VarList.RSP_SUCCESS);
				responseDto.setMessage("success");
				responseDto.setContent(productDto);
				return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.ACCEPTED);
			}else {
				responseDto.setCode(VarList.RSP_FAIL);
				responseDto.setMessage("fail");
				responseDto.setContent(null);
				return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			responseDto.setCode(VarList.RSP_FAIL);
			responseDto.setMessage("INTERNAL SERVER ERROR");
			responseDto.setContent(e.getMessage());
			return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<ResponseDto> getproducts() {
		try {
			List<ShrinkedProductDto> productlist = extendedProductService.getProducts();
			if(productlist.isEmpty()) {
				responseDto.setCode(VarList.RSP_NO_DATA_FOUND);
				responseDto.setMessage("no data found");
				responseDto.setContent(null);
				return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.NOT_FOUND);
			}else {
				responseDto.setCode(VarList.RSP_SUCCESS);
				responseDto.setMessage("success");
				responseDto.setContent(productlist);
				return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.ACCEPTED);
			}
		}catch ( Exception e){
			responseDto.setCode(VarList.RSP_NO_DATA_FOUND);
			responseDto.setMessage("internal server error");
			responseDto.setContent(null);
			return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	

}
