/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view;

import java.math.BigDecimal;

/**
 * The Class FinalizarPlazoFijoView.
 */
public class FinalizarPlazoFijoView {

	/** The mensaje. */
	private String mensaje;

	/** The numero Comprobante. */
	private String numeroComprobante;

	/** The fecha Constitucion. */
	private String fechaConstitucion;

	/** The Minimo dias Precancelar. */
	private String minimoDiasPrecancelar;
	
    /** The importe saldo inic ur. */
    private BigDecimal saldoInicUr;
    
    /** The importe cotizacion codigo ur. */
    private BigDecimal cotizacionCodigoUr;

	/**
	 * Gets the mensaje.
	 *
	 * @return the mensaje
	 */
	public String getMensaje() {
		return mensaje;
	}

	/**
	 * Sets the mensaje.
	 *
	 * @param mensaje
	 *            the mensaje to set
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	/**
	 * Gets the numero comprobante.
	 *
	 * @return the numero comprobante
	 */
	public String getNumeroComprobante() {
		return numeroComprobante;
	}

	/**
	 * Sets the numero comprobante.
	 *
	 * @param numeroComprobante
	 *            the new numero comprobante
	 */
	public void setNumeroComprobante(String numeroComprobante) {
		this.numeroComprobante = numeroComprobante;
	}

	/**
	 * Gets the fecha constitucion.
	 *
	 * @return the fecha constitucion
	 */
	public String getFechaConstitucion() {
		return fechaConstitucion;
	}

	/**
	 * Sets the fecha constitucion.
	 *
	 * @param fechaConstitucion
	 *            the new fecha constitucion
	 */
	public void setFechaConstitucion(String fechaConstitucion) {
		this.fechaConstitucion = fechaConstitucion;
	}

	/**
	 * Gets the minimo dias precancelar.
	 *
	 * @return the minimo dias precancelar
	 */
	public String getMinimoDiasPrecancelar() {
		return minimoDiasPrecancelar;
	}

	/**
	 * Sets the minimo dias precancelar.
	 *
	 * @param minimoDiasPrecancelar
	 *            the new minimo dias precancelar
	 */
	public void setMinimoDiasPrecancelar(String minimoDiasPrecancelar) {
		this.minimoDiasPrecancelar = minimoDiasPrecancelar;
	}

    /**
	 * Gets the saldo inic ur.
	 *
	 * @return the saldoInicUr
	 */
    public BigDecimal getSaldoInicUr() {
        return saldoInicUr;
    }

    /**
	 * Sets the saldo inic ur.
	 *
	 * @param saldoInicUr
	 *            the saldoInicUr to set
	 */
    public void setSaldoInicUr(BigDecimal saldoInicUr) {
        this.saldoInicUr = saldoInicUr;
    }

    /**
	 * Gets the cotizacion codigo ur.
	 *
	 * @return the cotizacionCodigoUr
	 */
    public BigDecimal getCotizacionCodigoUr() {
        return cotizacionCodigoUr;
    }

    /**
	 * Sets the cotizacion codigo ur.
	 *
	 * @param cotizacionCodigoUr
	 *            the cotizacionCodigoUr to set
	 */
    public void setCotizacionCodigoUr(BigDecimal cotizacionCodigoUr) {
        this.cotizacionCodigoUr = cotizacionCodigoUr;
    }

	
}
