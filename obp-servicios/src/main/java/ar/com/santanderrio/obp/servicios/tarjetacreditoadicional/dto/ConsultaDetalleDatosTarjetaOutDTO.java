/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.dto;

import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.entities.ConsultaDetalleDatosTarjetaOutEntity;

/**
 * The Class ConsultaDetalleDatosTarjetaOutDTO.
 */
public class ConsultaDetalleDatosTarjetaOutDTO {

    /** The estado. */
    private String estado;

    /** The limite de compra. */
    private String limiteDeCompra;

    /**
	 * Instantiates a new consulta detalle datos tarjeta out DTO.
	 */
    public ConsultaDetalleDatosTarjetaOutDTO() {
        super();
    }

    /**
	 * Instantiates a new consulta detalle datos tarjeta out DTO.
	 *
	 * @param consultaDetalleDatosTarjetaOutEntity
	 *            the consulta detalle datos tarjeta out entity
	 */
    public ConsultaDetalleDatosTarjetaOutDTO(
            ConsultaDetalleDatosTarjetaOutEntity consultaDetalleDatosTarjetaOutEntity) {
        this.estado = consultaDetalleDatosTarjetaOutEntity.getEstado();
        this.limiteDeCompra = consultaDetalleDatosTarjetaOutEntity.getLimiteDeCompra();
    }

    /**
     * Gets the estado.
     *
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Sets the estado.
     *
     * @param estado
     *            the new estado
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Gets the limite de compra.
     *
     * @return the limite de compra
     */
    public String getLimiteDeCompra() {
        return limiteDeCompra;
    }

    /**
     * Sets the limite de compra.
     *
     * @param limiteDeCompra
     *            the new limite de compra
     */
    public void setLimiteDeCompra(String limiteDeCompra) {
        this.limiteDeCompra = limiteDeCompra;
    }
}
