package dev.kpucha.itx.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.kpucha.itx.dto.PrioritizedPriceResponseDTO;
import dev.kpucha.itx.service.PriceService;

@RestController
@RequestMapping("/prices")
public class PriceController {

    @Autowired
    private PriceService priceService;
	
	@GetMapping(value="/prioritized", produces="application/json")
	public ResponseEntity<PrioritizedPriceResponseDTO> getPrioritizedPrice(
	        @RequestParam Integer productId,
	        @RequestParam Integer brandId,
	        @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime date
	    ) {
		PrioritizedPriceResponseDTO price = priceService.findPrioritizedPrice(productId, brandId, date);
		if (price == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(price);
	}

}
