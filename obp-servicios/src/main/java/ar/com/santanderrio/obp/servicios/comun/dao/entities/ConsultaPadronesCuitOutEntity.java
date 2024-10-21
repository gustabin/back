/**
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.dao.entities;

import java.util.ArrayList;
import java.util.List;


/**
 * The Class ConsultaPadronCuitOutEntity.
 *
 * @author alejandro_leal
 */
public class ConsultaPadronesCuitOutEntity {

	/** The codigo retorno extendido. */
	private String codigoRetornoExtendido;

	/** Nro de Cuit BCRA *. */
	private Integer cantidadBcra;
	
    /** Cantidad de ocurrencias encontradas en este padrón *. */
    private Integer cantidadABA;
    
    /** Lista de Cuits padron BCRA. */
    List<PadronBCRAOutEntity> padronBCRA;
    
    /** Lista de Cuits padron ABA. */
    List<PadronABAOutEntity> padronABA;

	/** The tiene error. */
	private boolean tieneError;

	/**
	 * Instantiates a new consulta padron cuit out entity.
	 */

	public ConsultaPadronesCuitOutEntity() {
        super();
    }

    /**
	 * Instantiates a new consulta padron cuit out entity.
	 *
	 * @param i
	 *            the i
	 * @param tieneError
	 *            the tiene error
	 */
	public ConsultaPadronesCuitOutEntity(String i, boolean tieneError) {
		this.codigoRetornoExtendido = i;
		this.tieneError = tieneError;
	}

	/**
	 * Checks if is tiene error.
	 *
	 * @return true, if is tiene error
	 */
	public boolean isTieneError() {
		return tieneError;
	}

	/**
	 * Sets the tiene error.
	 *
	 * @param tieneError
	 *            the new tiene error
	 */
	public void setTieneError(boolean tieneError) {
		this.tieneError = tieneError;
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
	 * Gets the cantidad bcra.
	 *
	 * @return the cantidad bcra
	 */
	public Integer getCantidadBcra() {
		return cantidadBcra;
	}

	/**
	 * Sets the cantidad bcra.
	 *
	 * @param cantidadBcra
	 *            the new cantidad bcra
	 */
	public void setCantidadBcra(Integer cantidadBcra) {
		this.cantidadBcra = cantidadBcra;
	}
	
	
    /**
     * Gets the cantidad ABA.
     *
     * @return the cantidad ABA
     */
    public Integer getCantidadABA() {
        return cantidadABA;
    }

    /**
     * Sets the cantidad ABA.
     *
     * @param cantidadABA
     *            the new cantidad ABA
     */
    public void setCantidadABA(Integer cantidadABA) {
        this.cantidadABA = cantidadABA;
    }

    /**
     * @return the padronBCRA
     */
    public List<PadronBCRAOutEntity> getPadronBCRA() {
        if(this.padronBCRA==  null) {
            this.padronBCRA = new ArrayList<PadronBCRAOutEntity>();
        }
        return padronBCRA;
    }

    /**
     * @param padronBCRA the padronBCRA to set
     */
    public void setPadronBCRA(List<PadronBCRAOutEntity> padronBCRA) {
        this.padronBCRA = padronBCRA;
    }

    /**
     * @return the padronABA
     */
    public List<PadronABAOutEntity> getPadronABA() {
        if(this.padronABA==  null) {
            this.padronABA = new ArrayList<PadronABAOutEntity>();
        }
        return padronABA;
    }

    /**
     * @param padronABA the padronABA to set
     */
    public void setPadronABA(List<PadronABAOutEntity> padronABA) {
        this.padronABA = padronABA;
    }

    
}
