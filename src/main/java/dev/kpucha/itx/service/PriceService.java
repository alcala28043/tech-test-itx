package dev.kpucha.itx.service;

import java.time.LocalDateTime;

import dev.kpucha.itx.dto.PrioritizedPriceResponseDTO;

public interface PriceService {

    /**
     * Busca el precio para un producto y cadena en una fecha determinada, 
     * devolviendo el que tenga mayor prioridad en caso de haber más de uno.
     *
     * @param productId Identificador del producto
     * @param brandId Identificador de la cadena
     * @param date Fecha para la que se quiere buscar el precio
     * @return PrioritizedPriceResponseDTO con el precio encontrado
     */
	PrioritizedPriceResponseDTO findPrioritizedPrice(Integer productId, Integer brandId, LocalDateTime date);
}
