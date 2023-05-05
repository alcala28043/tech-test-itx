package dev.kpucha.itx.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import dev.kpucha.itx.dto.PrioritizedPriceResponseDTO;
import dev.kpucha.itx.model.Price;

@Mapper
public interface PriceMapper {

	PriceMapper INSTANCE = Mappers.getMapper(PriceMapper.class);
	
	PrioritizedPriceResponseDTO priceToPrioritizedPriceResponseDTO(Price price);
}
