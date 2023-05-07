package dev.kpucha.itx.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrioritizedPriceResponseDTO {
	private Integer productId;
	private Integer brandId;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	private Integer priceList;
	private BigDecimal price;
	private String currency;
}
