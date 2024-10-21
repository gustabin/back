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
 * The Class SimulacionCompraVentaDTO.
 *
 * @author sabrina.cis
 */
public class SimulacionCompraVentaDTO {

	/** The importe a comprar dolares. */
	private BigDecimal importeCompraDolar;

	/** The importe venta dolar. */
	private BigDecimal importeVentaDolar;

	/** The cotizacion. */
	private BigDecimal cotizacion;

	/** The importe debitar pesos. */
	private BigDecimal importeDebitarPesos;

	/** The importe acreditar pesos. */
	private BigDecimal importeAcreditarPesos;

	/** The es compra. */
	private Boolean esCompra = Boolean.TRUE;

	/** The es venta. */
	private Boolean esVenta = Boolean.FALSE;

	/** The fecha. */
	private Date fecha;

	/** The legales. */
	private String legales;

	/** The legales 2. */
	private String legales2;

	/** The numeroOperacion. */
	private String numeroOperacion;

	/** The numeroComprobante. */
	private String numeroComprobante;

	/** The nupNumDoc. */
	private String nupNumDoc;

	/** The nupTipo. */
	private String nupTipo;

	/** The numero cuenta origen. */
	private String numeroCuentaOrigen;

	/** The tipo cuenta origen. */
	private String tipoCuentaOrigen;

	/** the alias origen *. */
	private String aliasOrigen;

	/** The numero cuenta destino. */
	private String numeroCuentaDestino;

	/** The tipo cuenta destino. */
	private String tipoCuentaDestino;

	/** the alias destino *. */
	private String aliasDestino;
	
	/** the importeImpuesto *. */
	private BigDecimal importeImpuesto;
	
	/** the muestraImpuestos *. */
	private Boolean muestraImpuestos;
	
    private BigDecimal importeImpuesto2;

    private String conceptoImpuesto2;

    private String impuesto2;

    private String regimenImpositivo2;

    private BigDecimal porcentajeImpuesto2;

	private BigDecimal impuestoBienes;

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
	 * Gets the legales.
	 *
	 * @return the legales
	 */
	public String getLegales() {
		return legales;
	}

	/**
	 * Sets the legales.
	 *
	 * @param legales
	 *            the legales to set
	 */
	public void setLegales(String legales) {
		this.legales = legales;
	}

	/**
	 * Gets the legales 2.
	 *
	 * @return the legales 2
	 */
	public String getLegales2() {
		return legales2;
	}

	/**
	 * Sets the legales 2.
	 *
	 * @param legales2 the new legales 2
	 */
	public void setLegales2(String legales2) {
		this.legales2 = legales2;
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
	 * Gets the es venta.
	 *
	 * @return the esVenta
	 */
	public Boolean getEsVenta() {
		return esVenta;
	}

	/**
	 * Sets the es venta.
	 *
	 * @param esVenta
	 *            the esVenta to set
	 */
	public void setEsVenta(Boolean esVenta) {
		this.esVenta = esVenta;
	}

	/**
	 * Gets the numero cuenta origen.
	 *
	 * @return the numeroCuentaOrigen
	 */
	public String getNumeroCuentaOrigen() {
		return numeroCuentaOrigen;
	}

	/**
	 * Sets the numero cuenta origen.
	 *
	 * @param numeroCuentaOrigen
	 *            the numeroCuentaOrigen to set
	 */
	public void setNumeroCuentaOrigen(String numeroCuentaOrigen) {
		this.numeroCuentaOrigen = numeroCuentaOrigen;
	}

	/**
	 * Gets the tipo cuenta origen.
	 *
	 * @return the tipoCuentaOrigen
	 */
	public String getTipoCuentaOrigen() {
		return tipoCuentaOrigen;
	}

	/**
	 * Sets the tipo cuenta origen.
	 *
	 * @param tipoCuentaOrigen
	 *            the tipoCuentaOrigen to set
	 */
	public void setTipoCuentaOrigen(String tipoCuentaOrigen) {
		this.tipoCuentaOrigen = tipoCuentaOrigen;
	}

	/**
	 * Gets the numero cuenta destino.
	 *
	 * @return the numeroCuentaDestino
	 */
	public String getNumeroCuentaDestino() {
		return numeroCuentaDestino;
	}

	/**
	 * Sets the numero cuenta destino.
	 *
	 * @param numeroCuentaDestino
	 *            the numeroCuentaDestino to set
	 */
	public void setNumeroCuentaDestino(String numeroCuentaDestino) {
		this.numeroCuentaDestino = numeroCuentaDestino;
	}

	/**
	 * Gets the tipo cuenta destino.
	 *
	 * @return the tipoCuentaDestino
	 */
	public String getTipoCuentaDestino() {
		return tipoCuentaDestino;
	}

	/**
	 * Sets the tipo cuenta destino.
	 *
	 * @param tipoCuentaDestino
	 *            the tipoCuentaDestino to set
	 */
	public void setTipoCuentaDestino(String tipoCuentaDestino) {
		this.tipoCuentaDestino = tipoCuentaDestino;
	}

	/**
	 * Gets the nup num doc.
	 *
	 * @return the nup num doc
	 */
	public String getNupNumDoc() {
		return nupNumDoc;
	}

	/**
	 * Sets the nup num doc.
	 *
	 * @param nupNumDoc
	 *            the new nup num doc
	 */
	public void setNupNumDoc(String nupNumDoc) {
		this.nupNumDoc = nupNumDoc;
	}

	/**
	 * Gets the nup tipo.
	 *
	 * @return the nup tipo
	 */
	public String getNupTipo() {
		return nupTipo;
	}

	/**
	 * Sets the nup tipo.
	 *
	 * @param nupTipo
	 *            the new nup tipo
	 */
	public void setNupTipo(String nupTipo) {
		this.nupTipo = nupTipo;
	}

	/**
	 * Gets the importe compra dolar.
	 *
	 * @return the importeCompraDolar
	 */
	public BigDecimal getImporteCompraDolar() {
		return importeCompraDolar;
	}

	/**
	 * Sets the importe compra dolar.
	 *
	 * @param importeCompraDolar
	 *            the importeCompraDolar to set
	 */
	public void setImporteCompraDolar(BigDecimal importeCompraDolar) {
		this.importeCompraDolar = importeCompraDolar;
	}

	/**
	 * Gets the importe venta dolar.
	 *
	 * @return the importeVentaDolar
	 */
	public BigDecimal getImporteVentaDolar() {
		return importeVentaDolar;
	}

	/**
	 * Sets the importe venta dolar.
	 *
	 * @param importeVentaDolar
	 *            the importeVentaDolar to set
	 */
	public void setImporteVentaDolar(BigDecimal importeVentaDolar) {
		this.importeVentaDolar = importeVentaDolar;
	}

	/**
	 * Gets the cotizacion.
	 *
	 * @return the cotizacion
	 */
	public BigDecimal getCotizacion() {
		return cotizacion;
	}

	/**
	 * Sets the cotizacion.
	 *
	 * @param cotizacion
	 *            the cotizacion to set
	 */
	public void setCotizacion(BigDecimal cotizacion) {
		this.cotizacion = cotizacion;
	}

	/**
	 * Gets the importe debitar pesos.
	 *
	 * @return the importeDebitarPesos
	 */
	public BigDecimal getImporteDebitarPesos() {
		return importeDebitarPesos;
	}

	/**
	 * Sets the importe debitar pesos.
	 *
	 * @param importeDebitarPesos
	 *            the importeDebitarPesos to set
	 */
	public void setImporteDebitarPesos(BigDecimal importeDebitarPesos) {
		this.importeDebitarPesos = importeDebitarPesos;
	}

	/**
	 * Gets the importe acreditar pesos.
	 *
	 * @return the importeAcreditarPesos
	 */
	public BigDecimal getImporteAcreditarPesos() {
		return importeAcreditarPesos;
	}

	/**
	 * Sets the importe acreditar pesos.
	 *
	 * @param importeAcreditarPesos
	 *            the importeAcreditarPesos to set
	 */
	public void setImporteAcreditarPesos(BigDecimal importeAcreditarPesos) {
		this.importeAcreditarPesos = importeAcreditarPesos;
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
	 * Gets the alias origen.
	 *
	 * @return the alias origen
	 */
	public String getAliasOrigen() {
		return aliasOrigen;
	}

	/**
	 * Sets the alias cuenta origen.
	 *
	 * @param aliasOrigen
	 *            the new alias origen
	 */
	public void setAliasOrigen(String aliasOrigen) {
		this.aliasOrigen = aliasOrigen;
	}

	/**
	 * Gets the alias destino.
	 *
	 * @return the alias destino
	 */
	public String getAliasDestino() {
		return aliasDestino;
	}

	/**
	 * Sets the alias cuenta destino.
	 *
	 * @param aliasDestino
	 *            the new alias destino
	 */
	public void setAliasDestino(String aliasDestino) {
		this.aliasDestino = aliasDestino;
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

	public BigDecimal getImpuestoBienes() {
		return impuestoBienes;
	}

	public void setImpuestoBienes(BigDecimal impuestoBienes) {
		this.impuestoBienes = impuestoBienes;
	}

    /**
	 * HashCode.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(cotizacion);
		hcb.append(esCompra);
		hcb.append(esVenta);
		hcb.append(fecha);
		hcb.append(importeAcreditarPesos);
		hcb.append(importeCompraDolar);
		hcb.append(importeDebitarPesos);
		hcb.append(importeVentaDolar);
		hcb.append(legales);
		hcb.append(numeroComprobante);
		hcb.append(numeroCuentaDestino);
		hcb.append(numeroCuentaOrigen);
		hcb.append(numeroOperacion);
		hcb.append(tipoCuentaDestino);
		hcb.append(tipoCuentaOrigen);
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
		SimulacionCompraVentaDTO other = (SimulacionCompraVentaDTO) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(cotizacion, other.getCotizacion());
		eb.append(esCompra, other.getEsCompra());
		eb.append(esVenta, other.getEsVenta());
		eb.append(fecha, other.getFecha());
		eb.append(importeAcreditarPesos, other.getImporteAcreditarPesos());
		eb.append(importeCompraDolar, other.getImporteCompraDolar());
		eb.append(importeDebitarPesos, other.getImporteDebitarPesos());
		eb.append(importeVentaDolar, other.getImporteVentaDolar());
		eb.append(legales, other.getLegales());
		eb.append(numeroComprobante, other.getNumeroComprobante());
		eb.append(numeroCuentaDestino, other.getNumeroCuentaDestino());
		eb.append(numeroCuentaOrigen, other.getNumeroCuentaOrigen());
		eb.append(numeroOperacion, other.getNumeroOperacion());
		eb.append(tipoCuentaDestino, other.getTipoCuentaDestino());
		eb.append(tipoCuentaOrigen, other.getTipoCuentaOrigen());
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
		return new ToStringBuilder(this).append("importeCompraDolar", importeCompraDolar)
				.append("importeVentaDolar", importeVentaDolar).append("cotizacion", cotizacion)
				.append("importeDebitarPesos", importeDebitarPesos)
				.append("importeAcreditarPesos", importeAcreditarPesos).append("esCompra", esCompra)
				.append("esVenta", esVenta).append("fecha", fecha).append("legales", legales)
				.append("numeroOperacion", numeroOperacion).append("numeroComprobante", numeroComprobante)
				.append("numeroCuentaOrigen", numeroCuentaOrigen).append("tipoCuentaOrigen", tipoCuentaOrigen)
				.append("numeroCuentaDestino", numeroCuentaDestino).append("tipoCuentaDestino", tipoCuentaDestino)
				.append("nupTipo", nupTipo).append("nupNumDoc", nupNumDoc).toString();
	}

}