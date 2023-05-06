package dev.kpucha.itx.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "PRICES")
@AllArgsConstructor
@NoArgsConstructor
public class Price {
	
	@EmbeddedId
	private PriceId id;

    @Column(name = "PRICE_LIST")
    private Integer priceList;
    @Column(name = "PRICE")
    private BigDecimal price;
    @Column(name = "CURR")
    private String currency;
}
