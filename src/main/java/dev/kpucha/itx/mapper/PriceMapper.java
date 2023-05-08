package dev.kpucha.itx.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import dev.kpucha.itx.dto.PrioritizedPriceResponseDTO;
import dev.kpucha.itx.model.Price;

@Mapper
public interface PriceMapper {

	PriceMapper INSTANCE = Mappers.getMapper(PriceMapper.class);

	@Mapping(target="productId", source="id.productId")
	@Mapping(target="brandId", source="id.brandId")
	@Mapping(target="startDate", source="id.startDate", dateFormat = "yyyy-MM-dd HH:mm:ss")
	@Mapping(target="endDate", source="id.endDate", dateFormat = "yyyy-MM-dd HH:mm:ss") 
	@Mapping(source="price", target="price", numberFormat="$#.00")
	PrioritizedPriceResponseDTO priceToPrioritizedPriceResponseDTO(Price price);
}
