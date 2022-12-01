package com.mecavia.site.serviceimplies;



import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mecavia.site.dto.ProductDto;
import com.mecavia.site.dto.ProductVariantDto;
import com.mecavia.site.entity.Colour;
import com.mecavia.site.entity.Product;
import com.mecavia.site.entity.Size;
import com.mecavia.site.repo.ColourRepo;
import com.mecavia.site.repo.ProductRepo;
import com.mecavia.site.repo.SizeRepo;
import com.mecavia.site.util.VarList;


@Service
@Transactional
public class ProductService  implements com.mecavia.site.service.ProductService {
	@Autowired
	private ProductRepo productRepo;
	
	@Autowired
	private SizeRepo sizerepo;
	
	@Autowired
	private ColourRepo colourrepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PCSMappingService pcsmservice;
	
	@Autowired
	private ProductDto productDto;
	
	@Override
	public String saveProduct(ProductDto productDto) {
		if(productRepo.existsById(productDto.getId())) {
			return VarList.RSP_DUPLICATED;
		}else {
			productRepo.save(modelMapper.map(productDto, Product.class));
			return VarList.RSP_SUCCESS;
		}
	}

	@Override
	public String updateProduct(ProductDto productDto) {
		if(productRepo.existsById(productDto.getId())) {
			productRepo.save(modelMapper.map(productDto, Product.class));
			return VarList.RSP_SUCCESS;
		}else {
			return VarList.RSP_NO_DATA_FOUND;
		}
	}

	@Override
	public ProductDto savevariant(ProductVariantDto productVariantDto) {
			Product product = productRepo.findById(productVariantDto.getProductid()).get();
			modelMapper.map(product,productDto);
			if(sizerepo.existsById(productVariantDto.getSizeid())) {
				Size size = sizerepo.findById(productVariantDto.getSizeid()).get();
				productDto.addvariantDTO(size);
			}
			if(colourrepo.existsById(productVariantDto.getColourid())) {
				Colour colour = colourrepo.findById(productVariantDto.getColourid()).get();
				productDto.addvariantDTO(colour);
			}
			try {
				productRepo.save(modelMapper.map(productDto, Product.class));
				pcsmservice.addPCSMapping(productVariantDto.getProductid());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			return productDto;
	}

	@Override
	public ProductDto deductvariant(ProductVariantDto productVariantDto) {
		Product product = productRepo.findById(productVariantDto.getProductid()).get();
		modelMapper.map(product,productDto);
		if(sizerepo.existsById(productVariantDto.getSizeid())) {
			Size size = sizerepo.findById(productVariantDto.getSizeid()).get();
			productDto.removevariantDTO(size);
		}
		if(colourrepo.existsById(productVariantDto.getColourid())) {
			Colour colour = colourrepo.findById(productVariantDto.getColourid()).get();
			productDto.removevariantDTO(colour);
		}
		try {
			productRepo.save(modelMapper.map(productDto, Product.class));
			pcsmservice.removePCSMapping(productVariantDto);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return productDto;
	}
	
	
}
