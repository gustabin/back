/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.dto;

import java.math.BigDecimal;

/**
 * The Class TenenciaFondosSuscritosDTO.
 */
public class TenenciaFondosSuscritosDTO implements Comparable<TenenciaFondosSuscritosDTO> {

	/** The codigo fondo. */
	private String codigoFondo;

	/** The tipo fondo. */
	private String tipoFondo;

	/** The nombre fondo. */
	private String nombreFondo;

	/** The cantidad cuotapartes. */
	private BigDecimal cantidadCuotapartes;

	/** The valor cuotaparte. */
	private BigDecimal valorCuotaparte;

	/** The valuacion. */
	private BigDecimal valuacion;

	/** The resultado. */
	private BigDecimal resultado;

	/** The codigo agrupador. */
	private String codigoAgrupador;

	/** The orden agrupacion. */
	private String ordenAgrupacion;

	/** the url cartera. */
	private String urlCartera;

	/** The habilitar suscripcion. */
	private String habilitarSuscripcion;

	/** The habilitar rescate. */
	private String habilitarRescate;

	/** The habilitar transferencia. */
	private String habilitarTransferencia;

	/** The tenencia valuada reexp. */
	private BigDecimal tenenciaValuadaReexp;
	
	/** The tenencia valuada al costo. */
	private BigDecimal tenenciaValuadaAlCosto;

	/** The ex citi. */
	private String exCiti;
	
	/** The precio compra. */
	private BigDecimal precioCompra;
	
	private boolean fondoNuevo;

	/**
	 * Gets the codigo fondo.
	 *
	 * @return the codigoFondo
	 */
	public String getCodigoFondo() {
		return codigoFondo;
	}

	/**
	 * Sets the codigo fondo.
	 *
	 * @param codigoFondo
	 *            the codigoFondo to set
	 */
	public void setCodigoFondo(String codigoFondo) {
		this.codigoFondo = codigoFondo;
	}

	/**
	 * Gets the tipo fondo.
	 *
	 * @return the tipoFondo
	 */
	public String getTipoFondo() {
		return tipoFondo;
	}

	/**
	 * Sets the tipo fondo.
	 *
	 * @param tipoFondo
	 *            the tipoFondo to set
	 */
	public void setTipoFondo(String tipoFondo) {
		this.tipoFondo = tipoFondo;
	}

	/**
	 * Gets the nombre fondo.
	 *
	 * @return the nombreFondo
	 */
	public String getNombreFondo() {
		return nombreFondo;
	}

	/**
	 * Sets the nombre fondo.
	 *
	 * @param nombreFondo
	 *            the nombreFondo to set
	 */
	public void setNombreFondo(String nombreFondo) {
		this.nombreFondo = nombreFondo;
	}

	/**
	 * Gets the cantidad cuotapartes.
	 *
	 * @return the cantidadCuotapartes
	 */
	public BigDecimal getCantidadCuotapartes() {
		return cantidadCuotapartes;
	}

	/**
	 * Sets the cantidad cuotapartes.
	 *
	 * @param cantidadCuotapartes
	 *            the cantidadCuotapartes to set
	 */
	public void setCantidadCuotapartes(BigDecimal cantidadCuotapartes) {
		this.cantidadCuotapartes = cantidadCuotapartes;
	}

	/**
	 * Gets the valor cuotaparte.
	 *
	 * @return the valorCuotaparte
	 */
	public BigDecimal getValorCuotaparte() {
		return valorCuotaparte;
	}

	/**
	 * Sets the valor cuotaparte.
	 *
	 * @param valorCuotaparte
	 *            the valorCuotaparte to set
	 */
	public void setValorCuotaparte(BigDecimal valorCuotaparte) {
		this.valorCuotaparte = valorCuotaparte;
	}

	/**
	 * Gets the valuacion.
	 *
	 * @return the valuacion
	 */
	public BigDecimal getValuacion() {
		return valuacion;
	}

	/**
	 * Sets the valuacion.
	 *
	 * @param valuacion
	 *            the valuacion to set
	 */
	public void setValuacion(BigDecimal valuacion) {
		this.valuacion = valuacion;
	}

	/**
	 * Gets the resultado.
	 *
	 * @return the resultado
	 */
	public BigDecimal getResultado() {
		return resultado;
	}

	/**
	 * Sets the resultado.
	 *
	 * @param resultado
	 *            the resultado to set
	 */
	public void setResultado(BigDecimal resultado) {
		this.resultado = resultado;
	}

	/**
	 * Gets the codigo agrupador.
	 *
	 * @return the codigoAgrupador
	 */
	public String getCodigoAgrupador() {
		return codigoAgrupador;
	}

	/**
	 * Sets the codigo agrupador.
	 *
	 * @param codigoAgrupador
	 *            the codigoAgrupador to set
	 */
	public void setCodigoAgrupador(String codigoAgrupador) {
		this.codigoAgrupador = codigoAgrupador;
	}

	/**
	 * Gets the orden agrupacion.
	 *
	 * @return the ordenAgrupacion
	 */
	public String getOrdenAgrupacion() {
		return ordenAgrupacion;
	}

	/**
	 * Sets the orden agrupacion.
	 *
	 * @param ordenAgrupacion
	 *            the ordenAgrupacion to set
	 */
	public void setOrdenAgrupacion(String ordenAgrupacion) {
		this.ordenAgrupacion = ordenAgrupacion;
	}

	/**
	 * Gets the url cartera.
	 *
	 * @return the urlCartera
	 */
	public String getUrlCartera() {
		return urlCartera;
	}

	/**
	 * Sets the url cartera.
	 *
	 * @param urlCartera
	 *            the urlCartera to set
	 */
	public void setUrlCartera(String urlCartera) {
		this.urlCartera = urlCartera;
	}

	/**
	 * Gets the habilitar suscripcion.
	 *
	 * @return the habilitarSuscripcion
	 */
	public String getHabilitarSuscripcion() {
		return habilitarSuscripcion;
	}

	/**
	 * Sets the habilitar suscripcion.
	 *
	 * @param habilitarSuscripcion
	 *            the habilitarSuscripcion to set
	 */
	public void setHabilitarSuscripcion(String habilitarSuscripcion) {
		this.habilitarSuscripcion = habilitarSuscripcion;
	}

	/**
	 * Gets the habilitar rescate.
	 *
	 * @return the habilitarRescate
	 */
	public String getHabilitarRescate() {
		return habilitarRescate;
	}

	/**
	 * Sets the habilitar rescate.
	 *
	 * @param habilitarRescate
	 *            the habilitarRescate to set
	 */
	public void setHabilitarRescate(String habilitarRescate) {
		this.habilitarRescate = habilitarRescate;
	}

	/**
	 * Gets the habilitar transferencia.
	 *
	 * @return the habilitarTransferencia
	 */
	public String getHabilitarTransferencia() {
		return habilitarTransferencia;
	}

	/**
	 * Sets the habilitar transferencia.
	 *
	 * @param habilitarTransferencia
	 *            the habilitarTransferencia to set
	 */
	public void setHabilitarTransferencia(String habilitarTransferencia) {
		this.habilitarTransferencia = habilitarTransferencia;
	}

	/**
	 * Gets the tenencia valuada reexp.
	 *
	 * @return the tenenciaValuadaReexp
	 */
	public BigDecimal getTenenciaValuadaReexp() {
		return tenenciaValuadaReexp;
	}

	/**
	 * Sets the tenencia valuada reexp.
	 *
	 * @param tenenciaValuadaReexp
	 *            the tenenciaValuadaReexp to set
	 */
	public void setTenenciaValuadaReexp(BigDecimal tenenciaValuadaReexp) {
		this.tenenciaValuadaReexp = tenenciaValuadaReexp;
	}

	/**
	 * Gets the tenencia valuada al costo.
	 *
	 * @return the tenenciaValuadaAlCosto
	 */
	public BigDecimal getTenenciaValuadaAlCosto() {
		return tenenciaValuadaAlCosto;
	}

	/**
	 * Sets the tenencia valuada al costo.
	 *
	 * @param tenenciaValuadaAlCosto
	 *            the tenenciaValuadaAlCosto to set
	 */
	public void setTenenciaValuadaAlCosto(BigDecimal tenenciaValuadaAlCosto) {
		this.tenenciaValuadaAlCosto = tenenciaValuadaAlCosto;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(TenenciaFondosSuscritosDTO tenenciaFondoSuscritoDTO) {
		if (Integer.valueOf(this.getCodigoAgrupador().trim()) > Integer
				.valueOf(tenenciaFondoSuscritoDTO.getCodigoAgrupador().trim()))
			return 1;
		else if (Integer.valueOf(this.getCodigoAgrupador().trim())
				.equals(Integer.valueOf(tenenciaFondoSuscritoDTO.getCodigoAgrupador().trim()))) {
			if (Integer.valueOf(this.getOrdenAgrupacion().trim()) >= Integer
					.valueOf(tenenciaFondoSuscritoDTO.getOrdenAgrupacion().trim()))
				return 1;
			else
				return -1;
		} else
			return -1;
	}

    /**
	 * Gets the ex citi.
	 *
	 * @return the ex citi
	 */
    public String getExCiti() {
        return exCiti;
    }

    /**
	 * Sets the ex citi.
	 *
	 * @param exCiti
	 *            the new ex citi
	 */
    public void setExCiti(String exCiti) {
        this.exCiti = exCiti;
    }

    /**
	 * Gets the precio compra.
	 *
	 * @return the precio compra
	 */
    public BigDecimal getPrecioCompra() {
        return precioCompra;
    }

    /**
	 * Sets the precio compra.
	 *
	 * @param precioCompra
	 *            the new precio compra
	 */
    public void setPrecioCompra(BigDecimal precioCompra) {
        this.precioCompra = precioCompra;
    }

	public boolean isFondoNuevo() {
		return fondoNuevo;
	}

	public void setFondoNuevo(boolean fondoNuevo) {
		this.fondoNuevo = fondoNuevo;
	}
}
