package dev.kpucha.itx;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.client.ResourceAccessException;

import dev.kpucha.itx.controller.PriceController;
import dev.kpucha.itx.dto.PrioritizedPriceResponseDTO;

@SpringJUnitConfig
@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
@DisplayName("Tests de peticiones al endpoint REST")
class PriceControllerIntegrationTest {
	
	private static final DateTimeFormatter DATE_TIME_FORMATTER = 
			DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	private static final Integer PRODUCT_ID = 35455;
	private static final Integer BRAND_ID = 1;
	private static final PrioritizedPriceResponseDTO TEST1_EXPECTED_PRICE = 
			new PrioritizedPriceResponseDTO(
					35455,
					1, 
					LocalDateTime.parse("2020-06-14 00:00:00", DATE_TIME_FORMATTER), 
					LocalDateTime.parse("2020-12-31 23:59:59", DATE_TIME_FORMATTER), 
					1, 
					new BigDecimal(35.50).setScale(2, RoundingMode.HALF_UP), 
					"EUR");
	private static final PrioritizedPriceResponseDTO TEST2_EXPECTED_PRICE = 
			new PrioritizedPriceResponseDTO(
					35455,
					1, 
					LocalDateTime.parse("2020-06-14 15:00:00", DATE_TIME_FORMATTER), 
					LocalDateTime.parse("2020-06-14 18:30:00", DATE_TIME_FORMATTER), 
					2, 
					new BigDecimal(25.45).setScale(2, RoundingMode.HALF_UP), 
					"EUR");
	private static final PrioritizedPriceResponseDTO TEST3_EXPECTED_PRICE = 
			new PrioritizedPriceResponseDTO(
					35455,
					1, 
					LocalDateTime.parse("2020-06-14 00:00:00", DATE_TIME_FORMATTER), 
					LocalDateTime.parse("2020-12-31 23:59:59", DATE_TIME_FORMATTER), 
					1, 
					new BigDecimal(35.50).setScale(2, RoundingMode.HALF_UP), 
					"EUR");
	private static final PrioritizedPriceResponseDTO TEST4_EXPECTED_PRICE = 
			new PrioritizedPriceResponseDTO(
					35455,
					1, 
					LocalDateTime.parse("2020-06-15 00:00:00", DATE_TIME_FORMATTER), 
					LocalDateTime.parse("2020-06-15 11:00:00", DATE_TIME_FORMATTER), 
					3, 
					new BigDecimal(30.50).setScale(2, RoundingMode.HALF_UP), 
					"EUR");
	private static final PrioritizedPriceResponseDTO TEST5_EXPECTED_PRICE = 
			new PrioritizedPriceResponseDTO(
					35455,
					1, 
					LocalDateTime.parse("2020-06-15 16:00:00", DATE_TIME_FORMATTER), 
					LocalDateTime.parse("2020-12-31 23:59:59", DATE_TIME_FORMATTER), 
					4, 
					new BigDecimal(38.95).setScale(2, RoundingMode.HALF_UP), 
					"EUR");
	
    
    @Autowired
    private PriceController princeController;
 
    @Test
    @DisplayName("Test 1: petición a las 10:00 del día 14 del producto 35455   para la brand 1 (ZARA)")
    void test1Product355455brand1day14hour10() {    	
    	LocalDateTime date = LocalDateTime.parse("2020-06-14 10:00:00", DATE_TIME_FORMATTER);
    	ResponseEntity<PrioritizedPriceResponseDTO> price = princeController.getPrioritizedPrice(PRODUCT_ID, BRAND_ID, date);
    	assertEquals(HttpStatus.OK, price.getStatusCode());
    	assertEquals(TEST1_EXPECTED_PRICE, price.getBody());
    }
    @Test
    @DisplayName("Test 2: petición a las 16:00 del día 14 del producto 35455   para la brand 1 (ZARA)")
    void test2Product355455brand1day14hour16() {    	
    	LocalDateTime date = LocalDateTime.parse("2020-06-14 16:00:00", DATE_TIME_FORMATTER);
    	ResponseEntity<PrioritizedPriceResponseDTO> price = princeController.getPrioritizedPrice(PRODUCT_ID, BRAND_ID, date);
    	assertEquals(HttpStatus.OK, price.getStatusCode());
    	assertEquals(TEST2_EXPECTED_PRICE, price.getBody());
    }
    @Test
    @DisplayName("Test 3: petición a las 21:00 del día 14 del producto 35455   para la brand 1 (ZARA)")
    void test3Product355455brand1day14hour21() {   
    	LocalDateTime date = LocalDateTime.parse("2020-06-14 21:00:00", DATE_TIME_FORMATTER);
    	ResponseEntity<PrioritizedPriceResponseDTO> price = princeController.getPrioritizedPrice(PRODUCT_ID, BRAND_ID, date);
    	assertEquals(HttpStatus.OK, price.getStatusCode());
    	assertEquals(TEST3_EXPECTED_PRICE, price.getBody());
    }
    @Test
    @DisplayName("Test 4: petición a las 10:00 del día 15 del producto 35455   para la brand 1 (ZARA)")
    void test4Product355455brand1day15hour10() {  
    	LocalDateTime date = LocalDateTime.parse("2020-06-15 10:00:00", DATE_TIME_FORMATTER);
    	ResponseEntity<PrioritizedPriceResponseDTO> price = princeController.getPrioritizedPrice(PRODUCT_ID, BRAND_ID, date);
    	assertEquals(HttpStatus.OK, price.getStatusCode());
    	assertEquals(TEST4_EXPECTED_PRICE, price.getBody());
    }
    @Test
    @DisplayName("Test 5: petición a las 21:00 del día 16 del producto 35455   para la brand 1 (ZARA)")
    void test5Product355455brand1day16hour21() {  
    	LocalDateTime date = LocalDateTime.parse("2020-06-16 21:00:00", DATE_TIME_FORMATTER);
    	ResponseEntity<PrioritizedPriceResponseDTO> price = princeController.getPrioritizedPrice(PRODUCT_ID, BRAND_ID, date);
    	assertEquals(HttpStatus.OK, price.getStatusCode());
    	assertEquals(TEST5_EXPECTED_PRICE, price.getBody());
    }
    @Test
    @DisplayName("Test 6: petición NOT FOUND")
    void test6NotFound() {  
    	LocalDateTime date = LocalDateTime.parse("1970-01-01 00:00:00", DATE_TIME_FORMATTER); 
    	assertThrows(ResponseStatusException.class, 
    			()-> princeController.getPrioritizedPrice(PRODUCT_ID, BRAND_ID, date));
    }
    @Test
    @DisplayName("Test 7: petición BAD REQUEST")
    void test7BadRequest() {  
    	TestRestTemplate testRestTemplate = new TestRestTemplate();
    	assertThrows(ResourceAccessException.class,
    			() -> testRestTemplate.getForEntity(
            			"http://localhost:8080/prices/prioritized?productId=8764",
            			PrioritizedPriceResponseDTO.class));
    }
}
