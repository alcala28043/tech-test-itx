package dev.kpucha.itx.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.kpucha.itx.dto.PrioritizedPriceResponseDTO;
import dev.kpucha.itx.mapper.PriceMapper;
import dev.kpucha.itx.model.Price;
import dev.kpucha.itx.repository.PriceRepository;
import dev.kpucha.itx.service.PriceService;

@Service
public class PriceServiceImpl implements PriceService {
	
	@Autowired
	PriceRepository priceRepository;

	@Override
	public PrioritizedPriceResponseDTO findPrioritizedPrice(Integer productId, Integer brandId, LocalDateTime date) {
		Optional<Price> optPrice = priceRepository.findPrioritizedPrice(productId, brandId, date);
		if(optPrice.isEmpty()) {
			return null;
		}		
		Price price = optPrice.get();		
		PrioritizedPriceResponseDTO response = PriceMapper.INSTANCE.priceToPrioritizedPriceResponseDTO(price);
		return response;
	}
}
