package dev.kpucha.itx.service;

import java.time.LocalDateTime;
import java.util.Optional;

import dev.kpucha.itx.model.Price;

public interface PriceService {

	Optional<Price> findByProductIdAndBrandIdAndDate(Integer productId, Integer brandId, LocalDateTime date);
}
