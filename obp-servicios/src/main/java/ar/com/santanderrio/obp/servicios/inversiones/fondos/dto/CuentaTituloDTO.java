/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.dto;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.LicitacionVigente;
import ar.com.santanderrio.obp.servicios.pagos.entities.Interviniente;

/**
 * Clase que modela una cuenta.
 *
 * @author marcelo.ruiz
 */
public class CuentaTituloDTO {

    /** The nro cuenta. */
    private String nroCuenta;

    /** The sucursal. */
    private String sucursal;

    /** The nro cuenta formateado. */
    private String nroCuentaFormateado;

    /** The fondos suscriptos. */
    private List<FondoResumidoDTO> fondosSuscriptos;

    /** Cuenta operativa asociada a la cuenta titulo (solo B.PRIVADA) */
    private String cuentaOperativa;
    
    /** Cuenta operativa asociada Rep a la cuenta titulo */
    private String cuentaOperativaRep;

    /** The cuenta operativa sin formatear. */
    private String cuentaOperativaSinFormatear;
    
    /** The intervinientes. titular y cotitulares */
    private List<Interviniente> intervinientes;
    
	/** The cuenta bloqueada. */
    private boolean cuentaBloqueada = false;
    
    /** "S" puede licitar, "N" no puede licitar. */
    private String puedeLicitar = "S";

    /** The cuenta firmada. */
    private String cuentaFirmada;
    
    private boolean repatriacion;
    
    /** lista de licitaciones vigentes (suscripcion titulos) para la cuenta. */
	List<LicitacionVigente> licitacionesVigentesList = new ArrayList<LicitacionVigente>();

    /**
     * Gets the nro cuenta.
     *
     * @return the nro cuenta
     */
    public String getNroCuenta() {
        return nroCuenta;
    }

    /**
     * Sets the nro cuenta.
     *
     * @param nroCuenta
     *            the new nro cuenta
     */
    public void setNroCuenta(String nroCuenta) {
        this.nroCuenta = nroCuenta;
    }

    /**
     * Gets the sucursal.
     *
     * @return the sucursal
     */
    public String getSucursal() {
        return sucursal;
    }

    /**
     * Sets the sucursal.
     *
     * @param sucursal
     *            the new sucursal
     */
    public void setSucursal(String sucursal) {
        this.sucursal = sucursal;
    }

    /**
     * Gets the nro cuenta formateado.
     *
     * @return the nro cuenta formateado
     */
    public String getNroCuentaFormateado() {
        return nroCuentaFormateado;
    }

    /**
     * Sets the nro cuenta formateado.
     *
     * @param nroCuentaFormateado
     *            the new nro cuenta formateado
     */
    public void setNroCuentaFormateado(String nroCuentaFormateado) {
        this.nroCuentaFormateado = nroCuentaFormateado;
    }

    /**
     * Gets the fondos suscriptos.
     *
     * @return the fondos suscriptos
     */
    public List<FondoResumidoDTO> getFondosSuscriptos() {
        return fondosSuscriptos;
    }

    /**
     * Sets the fondos suscriptos.
     *
     * @param fondosSuscriptos
     *            the new fondos suscriptos
     */
    public void setFondosSuscriptos(List<FondoResumidoDTO> fondosSuscriptos) {
        this.fondosSuscriptos = fondosSuscriptos;
    }

    /**
     * Gets the cuenta operativa.
     *
     * @return the cuenta operativa
     */
    public String getCuentaOperativa() {
        return cuentaOperativa;
    }

    /**
     * Sets the cuenta operativa.
     *
     * @param cuentaOperativa
     *            the new cuenta operativa
     */
    public void setCuentaOperativa(String cuentaOperativa) {
        this.cuentaOperativa = cuentaOperativa;
    }

    /**
     * Gets the cuenta operativa sin formatear.
     *
     * @return the cuenta operativa sin formatear
     */
    public String getCuentaOperativaSinFormatear() {
		if (cuentaOperativaSinFormatear != null) {
    		return cuentaOperativaSinFormatear.trim();
    	}
    	return cuentaOperativaSinFormatear;
    }

    /**
     * Sets the cuenta operativa sin formatear.
     *
     * @param cuentaOperativaSinFormatear
     *            the new cuenta operativa sin formatear
     */
    public void setCuentaOperativaSinFormatear(String cuentaOperativaSinFormatear) {
        this.cuentaOperativaSinFormatear = cuentaOperativaSinFormatear;
    }

    /**
     * Gets the intervinientes.
     *
     * @return the intervinientes
     */
    public List<Interviniente> getIntervinientes() {
		return intervinientes;
	}

	/**
	 * Sets the intervinientes.
	 *
	 * @param intervinientes
	 *            the new intervinientes
	 */
	public void setIntervinientes(List<Interviniente> intervinientes) {
		this.intervinientes = intervinientes;
	}

	
	/**
	 * Gets the licitaciones vigentes list.
	 *
	 * @return the licitacionesVigentesList
	 */
	public List<LicitacionVigente> getLicitacionesVigentesList() {
		return licitacionesVigentesList;
	}

	/**
	 * Sets the licitaciones vigentes list.
	 *
	 * @param licitacionesVigentesList
	 *            the licitacionesVigentesList to set
	 */
	public void setLicitacionesVigentesList(List<LicitacionVigente> licitacionesVigentesList) {
		this.licitacionesVigentesList = licitacionesVigentesList;
	}

	/*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        HashCodeBuilder hcb = new HashCodeBuilder();

        hcb.append(cuentaOperativa);
        hcb.append(cuentaOperativaSinFormatear);
        hcb.append(fondosSuscriptos);
        hcb.append(nroCuenta);
        hcb.append(nroCuentaFormateado);
        hcb.append(sucursal);

        return hcb.toHashCode();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
		if (this == obj) {
            return true;
        }
		if (obj == null) {
            return false;
        }   
		if (getClass() != obj.getClass()) {
            return false;
        }
        CuentaTituloDTO other = (CuentaTituloDTO) obj;

        EqualsBuilder eb = new EqualsBuilder();
        eb.append(cuentaOperativa, other.getCuentaOperativa());
        eb.append(cuentaOperativaSinFormatear, other.getCuentaOperativaSinFormatear());
        eb.append(nroCuenta, other.getNroCuenta());
        eb.append(nroCuentaFormateado, other.getNroCuentaFormateado());
        eb.append(sucursal, other.getSucursal());

        return eb.isEquals();
    }

    /**
     * Checks if is cuenta bloqueada.
     *
     * @return the cuentaBloqueada
     */
    public boolean isCuentaBloqueada() {
        return cuentaBloqueada;
    }

    /**
     * Sets the cuenta bloqueada.
     *
	 * @param cuentaBloqueada
	 *            the cuentaBloqueada to set
     */
    public void setCuentaBloqueada(boolean cuentaBloqueada) {
        this.cuentaBloqueada = cuentaBloqueada;
    }

	/**
	 * Gets the puede licitar.
	 *
	 * @return the puede licitar
	 */
	public String getPuedeLicitar() {
		return puedeLicitar;
	}

	/**
	 * Sets the puede licitar.
	 *
	 * @param puedeLicitar
	 *            the new puede licitar
	 */
	public void setPuedeLicitar(String puedeLicitar) {
		this.puedeLicitar = puedeLicitar;
	}

	/**
	 * Gets the cuenta firmada.
	 *
	 * @return the cuenta firmada
	 */
	public String getCuentaFirmada() {
		return cuentaFirmada;
	}

	/**
	 * Sets the cuenta firmada.
	 *
	 * @param cuentaFirmada
	 *            the new cuenta firmada
	 */
	public void setCuentaFirmada(String cuentaFirmada) {
		this.cuentaFirmada = cuentaFirmada;
	}

	public boolean isRepatriacion() {
		return repatriacion;
	}

	public void setRepatriacion(boolean repatriacion) {
		this.repatriacion = repatriacion;
	}

	public String getCuentaOperativaRep() {
		return cuentaOperativaRep;
	}

	public void setCuentaOperativaRep(String cuentaOperativaRep) {
		this.cuentaOperativaRep = cuentaOperativaRep;
	}
	
	

}
