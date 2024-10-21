/*
 * 
 */
package ar.com.santanderrio.obp.servicios.trackingtarjetas.entities;

import java.util.List;

import org.beanio.annotation.Field;
import org.beanio.annotation.Record;
import org.beanio.annotation.Segment;

/**
 * ConsultaTarjetasMonederoOutEntity.
 *
 * @author Silvina_Luque
 */

@Record
public class ConsultaTarjetasMonederoOutEntity {
    
    /** headerTrama. */
    @Field
    private String headerTrama;
    
    /** codigoRetornoExtendido. */
    @Field(handlerName = "codigoRetornoExtendidoHandler")
    private String codigoRetornoExtendido;
       
    /** cantidadTarjetas. */
    @Field
    private Long cantidadTarjetas;
    
    /** tarjetasMonedero. */
    @Segment(occursRef = "cantidadTarjetas")
    private List<TarjetaMonederoEntity> tarjetasMonedero;

    /**
	 * Gets the header trama.
	 *
	 * @return the header trama
	 */
    public String getHeaderTrama() {
        return headerTrama;
    }

    /**
	 * Sets the header trama.
	 *
	 * @param headerTrama
	 *            the new header trama
	 */
    public void setHeaderTrama(String headerTrama) {
        this.headerTrama = headerTrama;
    }

    /**
	 * Gets the codigo retorno extendido.
	 *
	 * @return the codigo retorno extendido
	 */
    public String getCodigoRetornoExtendido() {
        return codigoRetornoExtendido;
    }

    /**
	 * Sets the codigo retorno extendido.
	 *
	 * @param codigoRetornoExtendido
	 *            the new codigo retorno extendido
	 */
    public void setCodigoRetornoExtendido(String codigoRetornoExtendido) {
        this.codigoRetornoExtendido = codigoRetornoExtendido;
    }

    /**
	 * Gets the cantidad tarjetas.
	 *
	 * @return the cantidad tarjetas
	 */
    public Long getCantidadTarjetas() {
        return cantidadTarjetas;
    }

    /**
	 * Sets the cantidad tarjetas.
	 *
	 * @param cantidadTarjetas
	 *            the new cantidad tarjetas
	 */
    public void setCantidadTarjetas(Long cantidadTarjetas) {
        this.cantidadTarjetas = cantidadTarjetas;
    }

    /**
	 * Gets the tarjetas monedero.
	 *
	 * @return the tarjetas monedero
	 */
    public List<TarjetaMonederoEntity> getTarjetasMonedero() {
        return tarjetasMonedero;
    }

    /**
	 * Sets the tarjetas monedero.
	 *
	 * @param tarjetasMonedero
	 *            the new tarjetas monedero
	 */
    public void setTarjetasMonedero(List<TarjetaMonederoEntity> tarjetasMonedero) {
        this.tarjetasMonedero = tarjetasMonedero;
    }



    
    
}
