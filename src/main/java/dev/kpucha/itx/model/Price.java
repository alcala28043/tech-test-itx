package dev.kpucha.itx.model;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PRICES")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Price implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -5598896589378577473L;

	@EmbeddedId
	private PriceId id;

    @Column(name = "PRICE_LIST")
    private Integer priceList;
    @Column(name = "PRICE")
    private BigDecimal price;
    @Column(name = "CURR")
    private String currency;
    
    
}
