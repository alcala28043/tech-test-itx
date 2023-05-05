package dev.kpucha.itx.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.kpucha.itx.model.Price;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {
	
    /**
     * Busca el precio para un producto y cadena en unas fechas determinada, 
     * devolviendo el que tenga mayor prioridad en caso de haber más de uno.
     *
     * @param productId Identificador del producto.
     * @param brandId Identificador de la cadena.
     * @param startDate Fecha de inicio de la vigencia del precio.
     * @param endDate Fecha de fin de la vigencia del precio.
     * @return Optional con el precio encontrado.
     */
	Optional<Price> findFirstByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
	            Integer productId, Integer brandId, LocalDateTime startDate, LocalDateTime endDate);
    /**
     * Busca el precio para un producto y cadena en una fecha determinada, 
     * devolviendo el que tenga mayor prioridad en caso de haber más de uno.
     * Este método utiliza la consulta definida en la interfaz como predeterminada.
     *
     * @param productId Identificador del producto.
     * @param brandId Identificador de la cadena.
     * @param date Fecha para la que se quiere buscar el precio.
     * @return Optional con el precio encontrado.
     */
    default Optional<Price> findByProductIdAndBrandIdAndDate(Integer productId, Integer brandId, LocalDateTime date) {
        return findFirstByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
                productId, brandId, date, date);
    }

}
