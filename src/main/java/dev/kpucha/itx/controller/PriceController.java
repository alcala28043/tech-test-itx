package dev.kpucha.itx.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import dev.kpucha.itx.dto.PrioritizedPriceResponseDTO;
import dev.kpucha.itx.service.PriceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/prices")
public class PriceController {

    @Autowired
    private PriceService priceService;
	
    @Operation(summary="Finds the price for a product id and brand id on a given date, returning the one with the highest priority if there is more than one.")
    @ApiResponses(value= {
    		@ApiResponse(responseCode = "200", description = "Prioritized price found"),
    		@ApiResponse(responseCode = "400", description = "Bad request", content=@Content),
    		@ApiResponse(responseCode = "404", description = "Prioritized price not found for the given parameters", content=@Content),
    		@ApiResponse(responseCode = "500", description = "Internal Server error getting prioritized price not found", content=@Content)
    })
	@GetMapping(value="/prioritized", produces="application/json")
	public ResponseEntity<PrioritizedPriceResponseDTO> getPrioritizedPrice(
	        @RequestParam @Parameter(description = "Product ID", example = "35455") Integer productId,
	        @RequestParam @Parameter(description = "Brand ID", example = "1") Integer brandId,
	        @RequestParam @Parameter(description = "Date with format yyyy-MM-dd HH:mm:ss", example = "2020-06-14 18:00:00") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime date
	    ) {
		PrioritizedPriceResponseDTO price = priceService.findPrioritizedPrice(productId, brandId, date);
		if (price == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Prioritized price not found for the given parameters");
        }
        return ResponseEntity.ok(price);
	}

}
