/*
 * 
 */
package ar.com.santanderrio.obp.servicios.debinws.dto;

import ar.com.santanderrio.obp.servicios.debinws.common.EstadoDebinEnum;

/**
 * The Class EstadoDebinWSBusquedaDTO.
 */
public class EstadoDebinWSBusquedaDTO {
    
    /** The estado. */
    private EstadoDebinEnum estado;
    
    /** The nro pagina. */
    private int nroPagina;

    /**
	 * Gets the estado.
	 *
	 * @return the estado
	 */
    public EstadoDebinEnum getEstado() {
        return estado;
    }

    /**
	 * Sets the estado.
	 *
	 * @param estado
	 *            the new estado
	 */
    public void setEstado(EstadoDebinEnum estado) {
        this.estado = estado;
    }

    /**
	 * Gets the nro pagina.
	 *
	 * @return the nro pagina
	 */
    public int getNroPagina() {
        return nroPagina;
    }

    /**
	 * Sets the nro pagina.
	 *
	 * @param nroPagina
	 *            the new nro pagina
	 */
    public void setNroPagina(int nroPagina) {
        this.nroPagina = nroPagina;
    }
    
    
    

}
