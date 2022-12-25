package com.mecavia.site.serviceimplies;



import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mecavia.site.dto.InventoryDto;
import com.mecavia.site.dto.StockDto;
import com.mecavia.site.entity.Stock;
import com.mecavia.site.repo.StockRepo;
import com.mecavia.site.service.StockServive;
import com.mecavia.site.util.InventoryId;
import com.mecavia.site.util.Status;
import com.mecavia.site.util.VarList;

@Service
@Transactional
public class StockService implements StockServive {
	
	@Autowired
	private StockRepo stockRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PCSMappingService pcsmService;
	
	@Autowired
	private InventoryId inventoryId;
	
	@Autowired
	private InventoryDto inventoryDto;
	
	@Autowired
	private InventoryService inventoryService;
	
	@Autowired
	private VarList varList;
	

	
	
	@Override
	public String saveSock(StockDto stock) {
		stock.setPcsmid(pcsmService.getPCSM(stock.getPcsmdto()).getId());
		stock.setOpeningdate(varList.getSYSDATE());
		if(stockRepo.existsById(stock.getId()) || stockRepo.existsByPcsmidAndStatus(stock.getPcsmid(), stock.getStatus())) {
			return VarList.RSP_DUPLICATED;
		}else {
			try {
				inventoryDto = inventoryService.getInventory(stock.getPcsmid(),Status.CLOSED.ordinal());
				if(inventoryDto!=null) {
					inventoryDto.setItemcount(stock.getOpeningcount()+inventoryDto.getItemcount());
					stock.setOpeningcount(inventoryDto.getItemcount());
					inventoryService.removeFromInventory(stock.getPcsmid(),Status.CLOSED.ordinal());
				}else {
					inventoryDto = new InventoryDto();
					inventoryDto.setItemcount(stock.getOpeningcount());
				}
			}catch(Exception e) {
				System.out.println(e.getMessage());
				inventoryDto.setItemcount(stock.getOpeningcount());
			}
			
			stock = modelMapper.map(stockRepo.saveAndFlush(modelMapper.map(stock, Stock.class)),StockDto.class);
			
			inventoryId.setStockid(stock.getId());
			inventoryId.setStatus(stock.getStatus());
			inventoryDto.setInventoryId(inventoryId);
			inventoryDto.setPrice(stock.getOpeningprice());
			inventoryDto.setPcsmid(stock.getPcsmid());
			
			try {
				inventoryService.addToInventory(inventoryDto);
				return VarList.RSP_SUCCESS;
			} catch (Exception e) {
				System.out.println(e.getMessage());
				return VarList.RSP_ERROR;
			}
			
		}
	}
	
	
	

	@Override
	public String closeStock(StockDto stock) {
		if(!stockRepo.existsByIdAndStatus(stock.getId(),stock.getStatus())) {
			inventoryId.setStockid(stock.getId());
			inventoryId.setStatus(Status.OPENED);
			try {
				inventoryDto = inventoryService.getInventory(inventoryId);
				stock = modelMapper.map(stockRepo.findById(stock.getId()).get(),StockDto.class);
				stock.setColosingcount(inventoryDto.getItemcount());
				stock.setClosingprice(inventoryDto.getPrice());
				stock.setColosingdate(varList.getSYSDATE());
				stock.setStatus(stock.getStatus());
				stockRepo.save(modelMapper.map(stock, Stock.class));
				
				try {
					inventoryService.coloseInventory(inventoryId);
					return VarList.RSP_SUCCESS;
				} catch (Exception e) {
					System.out.println(e.getMessage());
					return VarList.RSP_ERROR;
				}
				
				
			}catch(Exception e) {
				System.out.println(e.getMessage());
				return VarList.RSP_ERROR;
			}
		}else {
			return VarList.RSP_DUPLICATED;
		}
	}

	


	@Override
	public List<StockDto> getStocks() {
		// TODO Auto-generated method stub
		return null;
	}




	@Override
	public StockDto getStock(StockDto stockDto) {
		if(stockRepo.existsByPcsmidAndStatus(stockDto.getPcsmid(), stockDto.getStatus())){
			return modelMapper.map(stockRepo.findStockByPcsmidAndStatus(stockDto.getPcsmid(), stockDto.getStatus()),StockDto.class);
		}else {
			return null;
		}
	}

}
