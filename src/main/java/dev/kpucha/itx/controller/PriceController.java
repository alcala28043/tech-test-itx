package dev.kpucha.itx.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;
import org.springframework.web.server.ResponseStatusException;

import dev.kpucha.itx.dto.PrioritizedPriceResponseDTO;
import dev.kpucha.itx.service.PriceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/prices")
@Tag(name = "Prices", description = "ITX - Prices API")
public class PriceController {

    @Autowired
    private PriceService priceService;
	
    @Operation(summary="Finds the price for a product id and brand id on a given date", description="Finds the price for a product id and brand id on a given date, returning the one with the highest priority if there is more than one.")
    @ApiResponses(value= {
    		@ApiResponse(responseCode = "200", 
    					 description = "Prioritized price found"),
    		@ApiResponse(responseCode = "400", 
    					 description = "Bad request", 
    					 content = @Content(
    							 schema = @Schema(implementation=String.class),
    							 examples = @ExampleObject(
    									 value="400 BAD_REQUEST Required request parameter 'brandId' for method parameter type Integer is not present"))),
    		@ApiResponse(responseCode = "404", 
    					 description = "Prioritized price not found", 
    					 content=@Content(
    							 schema = @Schema(implementation = String.class),
    							 examples = @ExampleObject(
    									 value = "404 NOT_FOUND 'Prioritized price not found for the given parameters'"))),
    		@ApiResponse(responseCode = "500", 
    					 description = "Internal Server error getting prioritized price", 
    					 content=@Content(
    							 schema = @Schema(implementation = String.class),
    							 examples = @ExampleObject(
    									 value = "500 INTERNAL_SERVER_ERROR 'Something went wrong'")))
    })
	@GetMapping(value="/prioritized", produces="application/json")
	public ResponseEntity<PrioritizedPriceResponseDTO> getPrioritizedPrice(
	        @RequestParam @Parameter(description = "Product ID", example = "35455") Integer productId,
	        @RequestParam @Parameter(description = "Brand ID", example = "1") Integer brandId,
	        @RequestParam @Parameter(description = "Date with format yyyy-MM-dd HH:mm:ss", example = "2020-06-14 18:00:00") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime date
	    ) {
    	
    	try {
    		PrioritizedPriceResponseDTO price = priceService.findPrioritizedPrice(productId, brandId, date);
    		if (price == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Prioritized price not found for the given parameters");
            }
            return ResponseEntity.ok(price);
		} catch (InternalServerError e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
		}
	}

}
