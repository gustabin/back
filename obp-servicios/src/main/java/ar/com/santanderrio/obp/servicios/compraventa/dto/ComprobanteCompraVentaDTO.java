/*
 * 
 */
package ar.com.santanderrio.obp.servicios.compraventa.dto;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * The Class ComprobanteCompraVentaDTO.
 *
 * @author sabrina.cis
 */
public class ComprobanteCompraVentaDTO {

	/** The cuenta origen numero. */
	private String cuentaOrigenNumero;

	/** The cuenta origen tipo. */
	private String cuentaOrigenTipo;

	/** The cuenta destino numero. */
	private String cuentaDestinoNumero;

	/** The cuenta destino tipo. */
	private String cuentaDestinoTipo;

	/** The cotizacion aplicada. */
	private BigDecimal cotizacionAplicada;

	/** The importe pesos debitado. */
	private BigDecimal importePesosDebitado;

	/** The importe dolares debitado. */
	private BigDecimal importeDolaresDebitado;

	/** The importe dolar acreditado. */
	private BigDecimal importeDolarAcreditado;

	/** The importe pesos acreditado. */
	private BigDecimal importePesosAcreditado;

	/** The numero operacion. */
	private String numeroOperacion;

	/** The numero comprobante. */
	private String numeroComprobante;

	/** The fecha. */
	private Date fecha;

	/** The hora. */
	private String hora;

	/** The mensaje comprobante. */
	private String mensajeComprobante;

	/** The legales compra. */
	private String legalesCompra;

	/** The legales venta. */
	private String legalesVenta;

	/** legal compra uno. */
	private String legalCompraUno;

	/** legal compra dos. */
	private String legalCompraDos;

	/** The fecha hora servicio. */
	private String fechaHoraServicio;

	/** The es compra. */
	private Boolean esCompra = Boolean.FALSE;
	
	/** the importeImpuesto *. */
	private BigDecimal importeImpuesto;
	
	/** the muestraImpuestos *. */
	private Boolean muestraImpuestos;
	
    private BigDecimal importeImpuesto2;

    private String conceptoImpuesto2;

    private String impuesto2;

    private String regimenImpositivo2;

    private BigDecimal porcentajeImpuesto2;

	private BigDecimal impuestoBienes2;
	/**
	 * Gets the cuenta origen numero.
	 *
	 * @return the cuentaOrigenNumero
	 */
	public String getCuentaOrigenNumero() {
		return cuentaOrigenNumero;
	}

	/**
	 * Sets the cuenta origen numero.
	 *
	 * @param cuentaOrigenNumero
	 *            the cuentaOrigenNumero to set
	 */
	public void setCuentaOrigenNumero(String cuentaOrigenNumero) {
		this.cuentaOrigenNumero = cuentaOrigenNumero;
	}

	/**
	 * Gets the cuenta origen tipo.
	 *
	 * @return the cuentaOrigenTipo
	 */
	public String getCuentaOrigenTipo() {
		return cuentaOrigenTipo;
	}

	/**
	 * Sets the cuenta origen tipo.
	 *
	 * @param cuentaOrigenTipo
	 *            the cuentaOrigenTipo to set
	 */
	public void setCuentaOrigenTipo(String cuentaOrigenTipo) {
		this.cuentaOrigenTipo = cuentaOrigenTipo;
	}

	/**
	 * Gets the cuenta destino numero.
	 *
	 * @return the cuentaDestinoNumero
	 */
	public String getCuentaDestinoNumero() {
		return cuentaDestinoNumero;
	}

	/**
	 * Sets the cuenta destino numero.
	 *
	 * @param cuentaDestinoNumero
	 *            the cuentaDestinoNumero to set
	 */
	public void setCuentaDestinoNumero(String cuentaDestinoNumero) {
		this.cuentaDestinoNumero = cuentaDestinoNumero;
	}

	/**
	 * Gets the cuenta destino tipo.
	 *
	 * @return the cuentaDestinoTipo
	 */
	public String getCuentaDestinoTipo() {
		return cuentaDestinoTipo;
	}

	/**
	 * Sets the cuenta destino tipo.
	 *
	 * @param cuentaDestinoTipo
	 *            the cuentaDestinoTipo to set
	 */
	public void setCuentaDestinoTipo(String cuentaDestinoTipo) {
		this.cuentaDestinoTipo = cuentaDestinoTipo;
	}

	/**
	 * Gets the cotizacion aplicada.
	 *
	 * @return the cotizacionAplicada
	 */
	public BigDecimal getCotizacionAplicada() {
		return cotizacionAplicada;
	}

	/**
	 * Sets the cotizacion aplicada.
	 *
	 * @param cotizacionAplicada
	 *            the cotizacionAplicada to set
	 */
	public void setCotizacionAplicada(BigDecimal cotizacionAplicada) {
		this.cotizacionAplicada = cotizacionAplicada;
	}

	/**
	 * Gets the numero operacion.
	 *
	 * @return the numeroOperacion
	 */
	public String getNumeroOperacion() {
		return numeroOperacion;
	}

	/**
	 * Sets the numero operacion.
	 *
	 * @param numeroOperacion
	 *            the numeroOperacion to set
	 */
	public void setNumeroOperacion(String numeroOperacion) {
		this.numeroOperacion = numeroOperacion;
	}

	/**
	 * Gets the numero comprobante.
	 *
	 * @return the numeroComprobante
	 */
	public String getNumeroComprobante() {
		return numeroComprobante;
	}

	/**
	 * Sets the numero comprobante.
	 *
	 * @param numeroComprobante
	 *            the numeroComprobante to set
	 */
	public void setNumeroComprobante(String numeroComprobante) {
		this.numeroComprobante = numeroComprobante;
	}

	/**
	 * Gets the hora.
	 *
	 * @return the hora
	 */
	public String getHora() {
		return hora;
	}

	/**
	 * Sets the hora.
	 *
	 * @param hora
	 *            the hora to set
	 */
	public void setHora(String hora) {
		this.hora = hora;
	}

	/**
	 * Gets the mensaje comprobante.
	 *
	 * @return the mensajeComprobante
	 */
	public String getMensajeComprobante() {
		return mensajeComprobante;
	}

	/**
	 * Sets the mensaje comprobante.
	 *
	 * @param mensajeComprobante
	 *            the mensajeComprobante to set
	 */
	public void setMensajeComprobante(String mensajeComprobante) {
		this.mensajeComprobante = mensajeComprobante;
	}

	/**
	 * Gets the legales compra.
	 *
	 * @return the legalesCompra
	 */
	public String getLegalesCompra() {
		return legalesCompra;
	}

	/**
	 * Sets the legales compra.
	 *
	 * @param legalesCompra
	 *            the legalesCompra to set
	 */
	public void setLegalesCompra(String legalesCompra) {
		this.legalesCompra = legalesCompra;
	}

	/**
	 * Gets the legales venta.
	 *
	 * @return the legalesVenta
	 */
	public String getLegalesVenta() {
		return legalesVenta;
	}

	/**
	 * Sets the legales venta.
	 *
	 * @param legalesVenta
	 *            the legalesVenta to set
	 */
	public void setLegalesVenta(String legalesVenta) {
		this.legalesVenta = legalesVenta;
	}

	/**
	 * Gets the legal compra uno.
	 *
	 * @return the legal compra uno
	 */
	public String getLegalCompraUno() {
		return legalCompraUno;
	}

	/**
	 * Sets the legal compra uno.
	 *
	 * @param legalCompraUno
	 *            the new legal compra uno
	 */
	public void setLegalCompraUno(String legalCompraUno) {
		this.legalCompraUno = legalCompraUno;
	}

	/**
	 * Gets the legal compra dos.
	 *
	 * @return the legal compra dos
	 */
	public String getLegalCompraDos() {
		return legalCompraDos;
	}

	/**
	 * Sets the legal compra dos.
	 *
	 * @param legalCompraDos
	 *            the new legal compra dos
	 */
	public void setLegalCompraDos(String legalCompraDos) {
		this.legalCompraDos = legalCompraDos;
	}

	/**
	 * Gets the importe pesos debitado.
	 *
	 * @return the importePesosDebitado
	 */
	public BigDecimal getImportePesosDebitado() {
		return importePesosDebitado;
	}

	/**
	 * Sets the importe pesos debitado.
	 *
	 * @param importePesosDebitado
	 *            the importePesosDebitado to set
	 */
	public void setImportePesosDebitado(BigDecimal importePesosDebitado) {
		this.importePesosDebitado = importePesosDebitado;
	}

	/**
	 * Gets the importe dolares debitado.
	 *
	 * @return the importeDolaresDebitado
	 */
	public BigDecimal getImporteDolaresDebitado() {
		return importeDolaresDebitado;
	}

	/**
	 * Sets the importe dolares debitado.
	 *
	 * @param importeDolaresDebitado
	 *            the importeDolaresDebitado to set
	 */
	public void setImporteDolaresDebitado(BigDecimal importeDolaresDebitado) {
		this.importeDolaresDebitado = importeDolaresDebitado;
	}

	/**
	 * Gets the importe dolar acreditado.
	 *
	 * @return the importeDolarAcreditado
	 */
	public BigDecimal getImporteDolarAcreditado() {
		return importeDolarAcreditado;
	}

	/**
	 * Sets the importe dolar acreditado.
	 *
	 * @param importeDolarAcreditado
	 *            the importeDolarAcreditado to set
	 */
	public void setImporteDolarAcreditado(BigDecimal importeDolarAcreditado) {
		this.importeDolarAcreditado = importeDolarAcreditado;
	}

	/**
	 * Gets the importe pesos acreditado.
	 *
	 * @return the importePesosAcreditado
	 */
	public BigDecimal getImportePesosAcreditado() {
		return importePesosAcreditado;
	}

	/**
	 * Sets the importe pesos acreditado.
	 *
	 * @param importePesosAcreditado
	 *            the importePesosAcreditado to set
	 */
	public void setImportePesosAcreditado(BigDecimal importePesosAcreditado) {
		this.importePesosAcreditado = importePesosAcreditado;
	}

	/**
	 * Gets the fecha.
	 *
	 * @return the fecha
	 */
	public Date getFecha() {
		return fecha;
	}

	/**
	 * Sets the fecha.
	 *
	 * @param fecha
	 *            the fecha to set
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	/**
	 * Gets the es compra.
	 *
	 * @return the esCompra
	 */
	public Boolean getEsCompra() {
		return esCompra;
	}

	/**
	 * Sets the es compra.
	 *
	 * @param esCompra
	 *            the esCompra to set
	 */
	public void setEsCompra(Boolean esCompra) {
		this.esCompra = esCompra;
	}

	/**
	 * Gets the fecha hora servicio.
	 *
	 * @return the fecha hora servicio
	 */
	public String getFechaHoraServicio() {
		return fechaHoraServicio;
	}

	/**
	 * Sets the fecha hora servicio.
	 *
	 * @param fechaHoraServicio
	 *            the new fecha hora servicio
	 */
	public void setFechaHoraServicio(String fechaHoraServicio) {
		this.fechaHoraServicio = fechaHoraServicio;
	}

	/**
	 * @return the importeImpuesto
	 */
	public BigDecimal getImporteImpuesto() {
		return importeImpuesto;
	}

	/**
	 * @param importeImpuesto the importeImpuesto to set
	 */
	public void setImporteImpuesto(BigDecimal importeImpuesto) {
		this.importeImpuesto = importeImpuesto;
	}

	/**
	 * @return the muestraImpuestos
	 */
	public Boolean getMuestraImpuestos() {
		return muestraImpuestos;
	}

	/**
	 * @param muestraImpuestos the muestraImpuestos to set
	 */
	public void setMuestraImpuestos(Boolean muestraImpuestos) {
		this.muestraImpuestos = muestraImpuestos;
	}

	/**
     * @return the importeImpuesto2
     */
    public BigDecimal getImporteImpuesto2() {
        return importeImpuesto2;
    }

    /**
     * @param importeImpuesto2 the importeImpuesto2 to set
     */
    public void setImporteImpuesto2(BigDecimal importeImpuesto2) {
        this.importeImpuesto2 = importeImpuesto2;
    }

    /**
     * @return the conceptoImpuesto2
     */
    public String getConceptoImpuesto2() {
        return conceptoImpuesto2;
    }

    /**
     * @param conceptoImpuesto2 the conceptoImpuesto2 to set
     */
    public void setConceptoImpuesto2(String conceptoImpuesto2) {
        this.conceptoImpuesto2 = conceptoImpuesto2;
    }

    /**
     * @return the impuesto2
     */
    public String getImpuesto2() {
        return impuesto2;
    }

    /**
     * @param impuesto2 the impuesto2 to set
     */
    public void setImpuesto2(String impuesto2) {
        this.impuesto2 = impuesto2;
    }

    /**
     * @return the regimenImpositivo2
     */
    public String getRegimenImpositivo2() {
        return regimenImpositivo2;
    }

    /**
     * @param regimenImpositivo2 the regimenImpositivo2 to set
     */
    public void setRegimenImpositivo2(String regimenImpositivo2) {
        this.regimenImpositivo2 = regimenImpositivo2;
    }

    /**
     * @return the porcentajeImpuesto2
     */
    public BigDecimal getPorcentajeImpuesto2() {
        return porcentajeImpuesto2;
    }

    /**
     * @param porcentajeImpuesto2 the porcentajeImpuesto2 to set
     */
    public void setPorcentajeImpuesto2(BigDecimal porcentajeImpuesto2) {
        this.porcentajeImpuesto2 = porcentajeImpuesto2;
    }

	public BigDecimal getImpuestoBienes2() {
		return impuestoBienes2;
	}

	public void setImpuestoBienes2(BigDecimal impuestoBienes2) {
		this.impuestoBienes2 = impuestoBienes2;
	}

    /**
	 * HashCode.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(cotizacionAplicada);
		hcb.append(cuentaDestinoNumero);
		hcb.append(cuentaDestinoTipo);
		hcb.append(cuentaOrigenNumero);
		hcb.append(cuentaOrigenTipo);
		hcb.append(esCompra);
		hcb.append(fecha);
		hcb.append(hora);
		hcb.append(importeDolarAcreditado);
		hcb.append(importeDolaresDebitado);
		hcb.append(importePesosAcreditado);
		hcb.append(importePesosDebitado);
		hcb.append(legalCompraDos);
		hcb.append(legalCompraUno);
		hcb.append(legalesCompra);
		hcb.append(legalesVenta);
		hcb.append(mensajeComprobante);
		hcb.append(numeroComprobante);
		hcb.append(numeroOperacion);
		return hcb.toHashCode();
	}

	/**
	 * Equals.
	 *
	 * @param obj
	 *            the obj
	 * @return true, if successful
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
		ComprobanteCompraVentaDTO other = (ComprobanteCompraVentaDTO) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(cotizacionAplicada, other.getCotizacionAplicada());
		eb.append(cuentaDestinoNumero, other.getCuentaDestinoNumero());
		eb.append(cuentaDestinoTipo, other.getCuentaDestinoTipo());
		eb.append(cuentaOrigenNumero, other.getCuentaOrigenNumero());
		eb.append(cuentaOrigenTipo, other.getCuentaOrigenTipo());
		eb.append(fecha, other.getFecha());
		eb.append(hora, other.getHora());
		eb.append(importeDolarAcreditado, other.getImporteDolarAcreditado());
		eb.append(importeDolaresDebitado, other.getImporteDolaresDebitado());
		eb.append(importePesosAcreditado, other.getImportePesosAcreditado());
		eb.append(importePesosDebitado, other.getImportePesosDebitado());
		eb.append(mensajeComprobante, other.getMensajeComprobante());
		eb.append(numeroComprobante, other.getNumeroComprobante());
		eb.append(numeroOperacion, other.getNumeroOperacion());
		return eb.isEquals();
	}

	/**
	 * ToString.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		ToStringBuilder.setDefaultStyle(ToStringStyle.SHORT_PREFIX_STYLE);
		return new ToStringBuilder(this).append("cuentaOrigenNumero", cuentaOrigenNumero)
				.append("cuentaOrigenTipo", cuentaOrigenTipo).append("cuentaDestinoNumero", cuentaDestinoNumero)
				.append("cuentaDestinoTipo", cuentaDestinoTipo).append("cotizacionAplicada", cotizacionAplicada)
				.append("importePesosDebitado", importePesosDebitado)
				.append("importeDolaresDebitado", importeDolaresDebitado)
				.append("importeDolarAcreditado", importeDolarAcreditado)
				.append("importePesosAcreditado", importePesosAcreditado).append("numeroOperacion", numeroOperacion)
				.append("numeroComprobante", numeroComprobante).append("fecha", fecha).append("hora", hora)
				.append("mensajeComprobante", mensajeComprobante).append("legalesCompra", legalesCompra)
				.append("legalesVenta", legalesVenta).append("legalCompraUno", legalCompraUno)
				.append("legalCompraDos", legalCompraDos).append("esCompra", esCompra).toString();
	}

}