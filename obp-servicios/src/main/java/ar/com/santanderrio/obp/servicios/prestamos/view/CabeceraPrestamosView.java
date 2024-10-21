/**
 * 
 */
package ar.com.santanderrio.obp.servicios.prestamos.view;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.PrestamosDisponibles;
import ar.com.santanderrio.obp.servicios.simuladorprestamo.view.ConfiguracionPrestamoView;

// TODO: Auto-generated Javadoc
/**
 * Objeto utilizado para el Input del PrestamoSEI.
 * 
 * @author
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class CabeceraPrestamosView {
    // agregar los atributos segun el negocio que corrspondan. Ej: private
    /** The linea total. */
    // String extra;
    private String lineaTotal;

    /** The linea disponible. */
    private String lineaDisponible;

    /** The saldo pesos value. */
    private Double lineaTotalValue;

    /** The saldo dolares value. */
    private Double lineaDisponibleValue;
    
    /** Saldo disponible para PreAprobado MonoProducto*/
    private String lineaPreaprobadoMonoproducto;

    /** The ayuda linea total. */
    private String ayudaLineaTotal;

    /** The ayuda linea disponible. */
    private String ayudaLineaDisponible;

    /** The habilitar pago prestamo. */
    private Boolean habilitarPagoPrestamo;

    /** The prestamos disponibles. */
    private PrestamosDisponibles prestamosDisponibles;

    /** The legales simulador prestamo. */
    private String legalesSimuladorPrestamo;

    /** The legales simulador prestamo 2. */
    private String legalesSimuladorPrestamo2;
    
    /** The legales simulador prestamo confirmacion. */
    private String legalesSimuladorPrestamoConfirmacion;

    /** The legales simulador prestamo confirmacion 2. */
    private String legalesSimuladorPrestamoConfirmacion2;

	/** The configuraciones simulador. */
	private Respuesta<List<ConfiguracionPrestamoView>> configuracionSimulador;
	
	/** The mensaje ayuda simulador 1. */
	private String mensajeAyudaSimulador1;
	
	/** The mensaje ayuda simulador 2. */
	private String mensajeAyudaSimulador2;
	
	/** The mensaje ayuda simulador 3. */
	private String mensajeAyudaSimulador3;
	
	/** The mensaje solicitar ayuda uva. */
	private String[] mensajeSolicitarAyudaUva;

	/** The legales terminos y condiciones. */
	private String legalesTerminosYCondiciones;
	
	/** The is advance. */
	private Boolean isAdvance;
	
	/** The legal aplazamiento de vencimientos. */
	private String legalAplazamientoDeVencimientos;
	
	private String mensajeAyudaPreaprobado;
	
	private String mensajeAumentoLineaCrediticia;
	
	private String ayudaLineaPreaprobado;
	
	private String tipoDeATP;
	
	private String mensajeContextual;

	private Boolean fueraHorarioOperaciones;

	
	/**
	 * Gets the linea total.
	 *
	 * @return linea total crediticia
	 */
	public String getLineaTotal() {
		return lineaTotal;
	}

    /**
     * Sets the linea total.
     *
     * @param lineaTotal
     *            the new linea total
     */
    public void setLineaTotal(String lineaTotal) {
        this.lineaTotal = lineaTotal;
    }

    /**
     * Gets the linea disponible.
     *
     * @return the linea disponible
     */
    public String getLineaDisponible() {
        return lineaDisponible;
    }

    /**
     * Sets the linea disponible.
     *
     * @param lineaDisponible
     *            the new linea disponible
     */
    public void setLineaDisponible(String lineaDisponible) {
        this.lineaDisponible = lineaDisponible;
    }

    /**
     * Gets the ayuda linea total.
     *
     * @return ayudaLineaTotal
     */
    public String getAyudaLineaTotal() {
        return ayudaLineaTotal;
    }

    /**
     * Sets the ayuda linea total.
     *
     * @param ayudaLineaTotal
     *            the new ayuda linea total
     */
    public void setAyudaLineaTotal(String ayudaLineaTotal) {
        this.ayudaLineaTotal = ayudaLineaTotal;
    }

    /**
     * Gets the ayuda linea disponible.
     *
     * @return ayudaLineaDisponible
     */
    public String getAyudaLineaDisponible() {
        return ayudaLineaDisponible;
    }

    /**
     * Sets the ayuda linea disponible.
     *
     * @param ayudaLineaDisponible
     *            the new ayuda linea disponible
     */
    public void setAyudaLineaDisponible(String ayudaLineaDisponible) {
        this.ayudaLineaDisponible = ayudaLineaDisponible;
    }

    /**
     * Gets the linea total value.
     *
     * @return lineaTotalValue
     */
    public Double getLineaTotalValue() {
        return lineaTotalValue;
    }

    /**
     * Sets the linea total value.
     *
     * @param lineaTotalValue
     *            the new linea total value
     */
    public void setLineaTotalValue(Double lineaTotalValue) {
        this.lineaTotalValue = lineaTotalValue;
    }

    /**
     * Gets the linea disponible value.
     *
     * @return lineaDisponibleValue
     */
    public Double getLineaDisponibleValue() {
        return lineaDisponibleValue;
    }

    /**
     * Sets the linea disponible value.
     *
     * @param lineaDisponibleValue
     *            the new linea disponible value
     */
    public void setLineaDisponibleValue(Double lineaDisponibleValue) {
        this.lineaDisponibleValue = lineaDisponibleValue;
    }

    /**
     * Obtiene Linea Preaprobado Monoproducto
     * @return
     */
    public String getLineaPreaprobadoMonoproducto() {
		return lineaPreaprobadoMonoproducto;
	}

    /**
     * Setea Linea Preaprobado Monoproducto
     * @param lineaPreaprobadoMonoproducto
     */
	public void setLineaPreaprobadoMonoproducto(String lineaPreaprobadoMonoproducto) {
		this.lineaPreaprobadoMonoproducto = lineaPreaprobadoMonoproducto;
	}

	/**
     * Gets the habilitar pago prestamo.
     *
     * @return the habilitar pago prestamo
     */
    public Boolean getHabilitarPagoPrestamo() {
        return habilitarPagoPrestamo;
    }

    /**
     * Sets the habilitar pago prestamo.
     *
     * @param habilitarPagoPrestamo
     *            the new habilitar pago prestamo
     */
    public void setHabilitarPagoPrestamo(Boolean habilitarPagoPrestamo) {
        this.habilitarPagoPrestamo = habilitarPagoPrestamo;
    }

    /**
     * Gets the prestamos disponibles.
     *
     * @return the prestamos disponibles
     */
    public PrestamosDisponibles getPrestamosDisponibles() {
        return prestamosDisponibles;
    }

    /**
     * Sets the prestamos disponibles.
     *
     * @param prestamosDisponibles
     *            the new prestamos disponibles
     */
    public void setPrestamosDisponibles(PrestamosDisponibles prestamosDisponibles) {
        this.prestamosDisponibles = prestamosDisponibles;
    }

    /**
     * Gets the legales simulador prestamo.
     *
     * @return the legales simulador prestamo
     */
    public String getLegalesSimuladorPrestamo() {
        return legalesSimuladorPrestamo;
    }

    /**
     * Sets the legales simulador prestamo.
     *
     * @param legalesSimuladorPrestamo
     *            the new legales simulador prestamo
     */
    public void setLegalesSimuladorPrestamo(String legalesSimuladorPrestamo) {
        this.legalesSimuladorPrestamo = legalesSimuladorPrestamo;
    }

    /**
     * Gets the configuracion simulador.
     *
     * @return the configuracion simulador
     */
    public Respuesta<List<ConfiguracionPrestamoView>> getConfiguracionSimulador() {
        return configuracionSimulador;
    }

    /**
     * Sets the configuracion simulador.
     *
     * @param configuracionSimulador
     *            the new configuracion simulador
     */
    public void setConfiguracionSimulador(Respuesta<List<ConfiguracionPrestamoView>> configuracionSimulador) {
        this.configuracionSimulador = configuracionSimulador;
    }

    /**
     * Gets the legales simulador prestamo 2.
     *
     * @return the legales simulador prestamo 2
     */
    public String getLegalesSimuladorPrestamo2() {
        return legalesSimuladorPrestamo2;
    }

    /**
     * Sets the legales simulador prestamo 2.
     *
     * @param legalesSimuladorPrestamo2
     *            the new legales simulador prestamo 2
     */
    public void setLegalesSimuladorPrestamo2(String legalesSimuladorPrestamo2) {
        this.legalesSimuladorPrestamo2 = legalesSimuladorPrestamo2;
    }
    
    /**
     * Gets the legales simulador prestamo confirmacion.
     *
     * @return the legales simulador prestamo confirmacion
     */
    public String getLegalesSimuladorPrestamoConfirmacion() {
        return legalesSimuladorPrestamoConfirmacion;
    }

    /**
     * Sets the legales simulador prestamo confirmacion.
     *
     * @param legalesSimuladorPrestamoConfirmacion
     *            the new legales simulador prestamo confirmacion
     */
    public void setLegalesSimuladorPrestamoConfirmacion(String legalesSimuladorPrestamoConfirmacion) {
        this.legalesSimuladorPrestamoConfirmacion = legalesSimuladorPrestamoConfirmacion;
    }

    /**
     * Gets the legales simulador prestamo confirmacion 2.
     *
     * @return the legales simulador prestamo confirmacion 2
     */
    public String getLegalesSimuladorPrestamoConfirmacion2() {
        return legalesSimuladorPrestamoConfirmacion2;
    }

    /**
     * Sets the legales simulador prestamo confirmacion 2.
     *
     * @param legalesSimuladorPrestamoConfirmacion2
     *            the new legales simulador prestamo confirmacion 2
     */
    public void setLegalesSimuladorPrestamoConfirmacion2(String legalesSimuladorPrestamoConfirmacion2) {
        this.legalesSimuladorPrestamoConfirmacion2 = legalesSimuladorPrestamoConfirmacion2;
    }

	/**
	 * Gets the mensaje ayuda simulador 1.
	 *
	 * @return the mensaje ayuda simulador 1.
	 */
	public String getMensajeAyudaSimulador1() {
		return mensajeAyudaSimulador1;
	}

	/**
	 * Sets the mensaje ayuda simulador 1.
	 *
	 * @param mensajeAyudaSimulador1 the new mensaje ayuda simulador 1.
	 */
	public void setMensajeAyudaSimulador1(String mensajeAyudaSimulador1) {
		this.mensajeAyudaSimulador1 = mensajeAyudaSimulador1;
	}

	/**
	 * Gets the mensaje ayuda simulador 2.
	 *
	 * @return the mensaje ayuda simulador 2.
	 */
	public String getMensajeAyudaSimulador2() {
		return mensajeAyudaSimulador2;
	}

	/**
	 * Sets the mensaje ayuda simulador 2.
	 *
	 * @param mensajeAyudaSimulador2 the new mensaje ayuda simulador 2.
	 */
	public void setMensajeAyudaSimulador2(String mensajeAyudaSimulador2) {
		this.mensajeAyudaSimulador2 = mensajeAyudaSimulador2;
	}

	/**
	 * Gets the mensaje ayuda simulador 3.
	 *
	 * @return the mensaje ayuda simulador 3.
	 */
	public String getMensajeAyudaSimulador3() {
		return mensajeAyudaSimulador3;
	}

	/**
	 * Sets the mensaje ayuda simulador 3.
	 *
	 * @param mensajeAyudaSimulador3 the new mensaje ayuda simulador 3.
	 */
	public void setMensajeAyudaSimulador3(String mensajeAyudaSimulador3) {
		this.mensajeAyudaSimulador3 = mensajeAyudaSimulador3;
	}
	
	/**
	 * Sets the mensaje solicitar ayuda uva.
	 *
	 * @param mensajeSolicitarAyudaUva the new solicitar ayuda uva.
	 */
	public void setMensajeSolicitarAyudaUva(String[] mensajeSolicitarAyudaUva) {
		this.mensajeSolicitarAyudaUva = mensajeSolicitarAyudaUva;
	}

	/**
	 * Gets the legales terminos y condiciones.
	 *
	 * @return the legales terminos y condiciones.
	 */
	public String getLegalesTerminosYCondiciones() {
		return legalesTerminosYCondiciones;
	}

	/**
	 * Sets the legales terminos y condiciones.
	 *
	 * @param legalesTerminosYCondiciones
	 *            the new legales terminos Y condiciones
	 */
	public void setLegalesTerminosYCondiciones(String legalesTerminosYCondiciones) {
		this.legalesTerminosYCondiciones = legalesTerminosYCondiciones;
	}
	
	/**
	 * Gets the is advance.
	 *
	 * @return the is advance.
	 */
	public Boolean getIsAdvance() {
		return isAdvance;
	}

	/**
	 * Sets the is advance.
	 *
	 * @param isAdvance
	 *            the new is advance
	 */
	public void setIsAdvance(Boolean isAdvance) {
		this.isAdvance = isAdvance;
	}
	
	
	
	/**
	 * Gets the legal aplazamiento de vencimientos.
	 *
	 * @return the legal aplazamiento de vencimientos
	 */
	public String getLegalAplazamientoDeVencimientos() {
		return legalAplazamientoDeVencimientos;
	}

	/**
	 * Sets the legal aplazamiento de vencimientos.
	 *
	 * @param legalAplazamientoDeVencimientos the new legal aplazamiento de vencimientos
	 */
	public void setLegalAplazamientoDeVencimientos(String legalAplazamientoDeVencimientos) {
		this.legalAplazamientoDeVencimientos = legalAplazamientoDeVencimientos;
	}

	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				// agregar los append con los atributos que correspondan
				// .append(extra)
				.toHashCode();
	}

    /**
     * Equals.
     *
     * @param obj the obj
     * @return true, if successful
     */
    @SuppressWarnings("unused")
	/*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }

        CabeceraPrestamosView other = (CabeceraPrestamosView) obj;
        EqualsBuilder eb = new EqualsBuilder();
        return eb
                // agregar los appends que corresponda segun los atributos
                // cargados, Ej: .append(extra, other.getExtra())
                .isEquals();
    }

    /**
     * To string.
     *
     * @return the string
     */
    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                // agregar los appends con los atributos que corresponda, ej:
                // .append("Extra", extra)
                .toString();
    }

    /**
     * Error con legales.
     *
     * @return true, if successful
     */
    public boolean errorConLegales() {
        return (legalesSimuladorPrestamo2 == null || legalesSimuladorPrestamoConfirmacion == null
                || legalesSimuladorPrestamoConfirmacion2 == null);
    }

	public String getMensajeAyudaPreaprobado() {
		return mensajeAyudaPreaprobado;
	}

	public void setMensajeAyudaPreaprobado(String mensajeAyudaPreaprobado) {
		this.mensajeAyudaPreaprobado = mensajeAyudaPreaprobado;
	}

	public String getMensajeAumentoLineaCrediticia() {
		return mensajeAumentoLineaCrediticia;
	}

	public void setMensajeAumentoLineaCrediticia(String mensajeAumentoLineaCrediticia) {
		this.mensajeAumentoLineaCrediticia = mensajeAumentoLineaCrediticia;
	}

	public String getAyudaLineaPreaprobado() {
		return ayudaLineaPreaprobado;
	}

	public void setAyudaLineaPreaprobado(String ayudaLineaPreaprobado) {
		this.ayudaLineaPreaprobado = ayudaLineaPreaprobado;
	}

	public String getTipoDeATP() {
		return tipoDeATP;
	}

	public void setTipoDeATP(String tipoDeATP) {
		this.tipoDeATP = tipoDeATP;
	}

	public String getMensajeContextual() {
		return mensajeContextual;
	}

	public void setMensajeContextual(String mensajeContextual) {
		this.mensajeContextual = mensajeContextual;
	}

	public Boolean getFueraHorarioOperaciones() {
		return fueraHorarioOperaciones;
	}

	public void setFueraHorarioOperaciones(Boolean fueraHorarioOperaciones) {
		this.fueraHorarioOperaciones = fueraHorarioOperaciones;
	}
}
