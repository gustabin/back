/*
 * 
 */
package ar.com.santanderrio.obp.servicios.descuento.cheques.entities;

import org.beanio.annotation.Field;
import org.beanio.annotation.Record;

/**
 * The Class DatosCesionEntity.
 */
@Record
public class DatosCesionEntity  {

    /** The header trama. */
    @Field
    private String headerTrama;

    /** Codigo retorno extendido. */
    @Field(handlerName = "codigoRetornoExtendidoHandler")
    private String codigoRetornoExtendido;

    /** Indicador de calificacion de cesion. */
    @Field
    private String calificadoS;
    
    /** The linea S. */
    @Field
    private String lineaS;
    
    /** The monto disponible S. */
    @Field
    private String montoDisponibleS;
    
    /** The tipo linea S. */
    @Field
    private String tipoLineaS;

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
	 * Gets the calificado S.
	 *
	 * @return the calificado S
	 */
    public String getCalificadoS() {
        return calificadoS;
    }

    /**
	 * Sets the calificado S.
	 *
	 * @param calificadoS
	 *            the new calificado S
	 */
    public void setCalificadoS(String calificadoS) {
        this.calificadoS = calificadoS;
    }

    /**
	 * Gets the linea S.
	 *
	 * @return the linea S
	 */
    public String getLineaS() {
        return lineaS;
    }

    /**
	 * Sets the linea S.
	 *
	 * @param lineaS
	 *            the new linea S
	 */
    public void setLineaS(String lineaS) {
        this.lineaS = lineaS;
    }

    /**
	 * Gets the monto disponible S.
	 *
	 * @return the monto disponible S
	 */
    public String getMontoDisponibleS() {
        return montoDisponibleS;
    }

    /**
	 * Sets the monto disponible S.
	 *
	 * @param montoDisponibleS
	 *            the new monto disponible S
	 */
    public void setMontoDisponibleS(String montoDisponibleS) {
        this.montoDisponibleS = montoDisponibleS;
    }

    /**
	 * Gets the tipo linea S.
	 *
	 * @return the tipo linea S
	 */
    public String getTipoLineaS() {
        return tipoLineaS;
    }

    /**
	 * Sets the tipo linea S.
	 *
	 * @param tipoLineaS
	 *            the new tipo linea S
	 */
    public void setTipoLineaS(String tipoLineaS) {
        this.tipoLineaS = tipoLineaS;
    }


    
}
