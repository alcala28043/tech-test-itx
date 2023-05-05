package dev.kpucha.itx.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.kpucha.itx.model.Price;
import dev.kpucha.itx.repository.PriceRepository;
import dev.kpucha.itx.service.PriceService;

@Service
public class PriceServiceImpl implements PriceService {
	
	@Autowired
	PriceRepository priceRepository;

	@Override
	public Optional<Price> findByProductIdAndBrandIdAndDate(Integer productId, Integer brandId, LocalDateTime date) {
		Optional<Price> result = priceRepository.findByProductIdAndBrandIdAndDate(productId, brandId, date);
		return result;
	}
}
