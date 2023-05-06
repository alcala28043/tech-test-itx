package dev.kpucha.itx.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import dev.kpucha.itx.model.Price;
import dev.kpucha.itx.model.PriceId;

public interface PriceRepository extends JpaRepository<Price, PriceId> {
	
    /**
     * Busca el precio para un producto y cadena en una fecha determinada, 
     * devolviendo el que tenga mayor prioridad en caso de haber más de uno.
     * Este método utiliza la consulta definida en la interfaz como predeterminada.
     *
     * @param productId Identificador del producto
     * @param brandId Identificador de la cadena
     * @param date Fecha para la que se quiere buscar el precio
     * @return Optional con el precio encontrado
     */
	  @Query("SELECT p FROM Price p WHERE p.id.productId = :productId AND p.id.brandId = :brandId AND p.id.startDate <= :date AND p.id.endDate >= :date ORDER BY p.id.priority DESC LIMIT 1")	
     Optional<Price> findPrioritizedPrice(@Param("productId") Integer productId, @Param("brandId") Integer brandId, @Param("date") LocalDateTime date);

}
