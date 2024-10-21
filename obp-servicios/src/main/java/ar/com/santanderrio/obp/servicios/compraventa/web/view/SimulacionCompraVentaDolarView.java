/**
 * 
 */
package ar.com.santanderrio.obp.servicios.compraventa.web.view;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.santanderrio.obp.servicios.compraventa.bo.impl.CompraVentaDolaresException;
import ar.com.santanderrio.obp.servicios.compraventa.dto.SimulacionCompraVentaDTO;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;

/**
 * The Class SimulacionCompraVentaDolarView.
 *
 * @author florencia.n.martinez
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SimulacionCompraVentaDolarView {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(SimulacionCompraVentaDolarView.class);

	/** The importe a comprar dolares. */
	@Pattern(regexp = "^(([1-9]\\d?((\\.\\d{3}){0,3}))|(\\d)|([1-9]\\d{2}((\\.\\d{3}){0,2})))(,\\d{1,2})$")
	private String importeCompraDolar;

	/** The importe venta dolar. */
	@Pattern(regexp = "^(([1-9]\\d?((\\.\\d{3}){0,3}))|(\\d)|([1-9]\\d{2}((\\.\\d{3}){0,2})))(,\\d{1,2})$")
	private String importeVentaDolar;

	/** The nro cuenta origen. */
	@Pattern(regexp = "[0-9]{3}-[0-9]{6}/[0-9]{1}")
	private String nroCuentaOrigen;

	/** The tipo cuenta origen. */
	@Pattern(regexp = "[a-z|A-Z|á|Á|é|É|í|Í|ó|Ó|ú|Ú|ñ|Ñ|$| ]+")
	private String tipoCuentaOrigen;

	/** The nro cuenta destino. */
	@Pattern(regexp = "[0-9]{3}-[0-9]{6}/[0-9]{1}")
	private String nroCuentaDestino;

	/** The tipo cuenta destino. */
	@Pattern(regexp = "[a-z|A-Z|á|Á|é|É|í|Í|ó|Ó|ú|Ú|ñ|Ñ|$| ]+")
	private String tipoCuentaDestino;

	private String errorConversion = "Error en la conversion de DTO a View en la cotizacion.";

	/** The cotizacion. */
	@Pattern(regexp = "([1-9]\\d|\\d),[0-9]{2}")
	private String cotizacion;

	/** The importe debitar pesos. */
	@Pattern(regexp = "^(([1-9]\\d?((\\.\\d{3}){0,3}))|(\\d)|([1-9]\\d{2}((\\.\\d{3}){0,2})))(,\\d{1,2})$")
	private String importeDebitarPesos;

	/** The importe acreditar pesos. */
	@Pattern(regexp = "^(([1-9]\\d?((\\.\\d{3}){0,3}))|(\\d)|([1-9]\\d{2}((\\.\\d{3}){0,2})))(,\\d{1,2})$")
	private String importeAcreditarPesos;

	/** The alias cuenta origen. */
	private String aliasCuentaOrigen;

	/** The alias cuenta destino. */
	private String aliasCuentaDestino;

	/** The es compra. */
	private Boolean esCompra = Boolean.TRUE;

	/** The es venta. */
	private Boolean esVenta = Boolean.FALSE;

	/** The fecha. */
	@Pattern(regexp = "([0-2]\\d|3[0-1])/(1[0-2]|0\\d)/(19|20(\\d{2}))")
	private String fecha;

	/** The legales. */
	@Pattern(regexp = "[a-z|A-Z|0-9|á|Á|é|É|í|Í|ó|Ó|ú|Ú|ñ|Ñ|$| |.|,|:|;|(|)|\n]+")
	private String legales;

	/** The legales 2. */
	private String legales2;

	/** El numero de cuenta origen en caso de respuesta warning *. */
	private String numeroCuentaError;

	/** El tipo de operacion en caso de respuesta warning *. */
	private String tipoOperacionError;
		
	private String importeImpuesto;
	
    private String importeImpuesto2;

    private String conceptoImpuesto2;

    private String impuesto2;

    private String regimenImpositivo2;

    private String porcentajeImpuesto2;
    
	private Boolean muestraImpuestos;
	
	private String impuestoBienes;

	/**
	 * Instantiates a new simulacion compra venta dolar view.
	 */
	public SimulacionCompraVentaDolarView() {
		super();
	}

	/**
	 * Instantiates a new simulacion compra venta dolar view.
	 *
	 * @param dto
	 *            the dto
	 * @throws CompraVentaDolaresException
	 *             the compra venta dolares exception
	 */
	public SimulacionCompraVentaDolarView(SimulacionCompraVentaDTO dto) throws CompraVentaDolaresException {
		super();
		try {
			if (dto.getEsCompra()) {
				this.setImporteCompraDolar(dto.getImporteCompraDolar());
				this.setImporteDebitarPesos(dto.getImporteDebitarPesos());
				this.setImporteImpuesto(dto.getImporteImpuesto());
				this.setMuestraImpuestos(dto.getMuestraImpuestos());
                this.setImporteImpuesto2(dto.getImporteImpuesto2());
                this.setConceptoImpuesto2(dto.getConceptoImpuesto2());
                this.setImpuesto2(dto.getImpuesto2());
                this.setRegimenImpositivo2(dto.getRegimenImpositivo2());
                this.setPorcentajeImpuesto2(dto.getPorcentajeImpuesto2());
				this.setImpuestoBienes(dto.getImpuestoBienes());
			}
			if (dto.getEsVenta()) {
				this.setImporteVentaDolar(dto.getImporteVentaDolar());
				this.setImporteAcreditarPesos(dto.getImporteAcreditarPesos());
			}
			this.setNroCuentaOrigen(dto.getNumeroCuentaOrigen());
			this.setTipoCuentaOrigen(dto.getTipoCuentaOrigen());
			this.setNroCuentaDestino(dto.getNumeroCuentaDestino());
			this.setTipoCuentaDestino(dto.getTipoCuentaDestino());
			this.setCotizacion(dto.getCotizacion());
			this.setFecha(dto.getFecha());
			this.setLegales(dto.getLegales());
			this.setLegales2(dto.getLegales2());
			this.setEsCompra(dto.getEsCompra());
			this.setEsVenta(dto.getEsVenta());
			this.setAliasCuentaOrigen(dto.getAliasOrigen());
			this.setAliasCuentaDestino(dto.getAliasDestino());
		} catch (CompraVentaDolaresException e) {
			LOGGER.error("Error en la conversion de DTO a View.", e);
			throw new CompraVentaDolaresException(e);
		}
	}

	/**
	 * Gets the cotizacion.
	 *
	 * @return the cotizacion
	 */
	public String getCotizacion() {
		return cotizacion;
	}

	/**
	 * Sets the cotizacion.
	 *
	 * @param cotizacion
	 *            the cotizacion to set
	 * @throws CompraVentaDolaresException
	 *             the compra venta dolares exception
	 */
	public void setCotizacion(BigDecimal cotizacion) throws CompraVentaDolaresException {
		try {
			this.cotizacion = ISBANStringUtils.formatearSaldoConSigno(cotizacion);
		} catch (Exception e) {
			LOGGER.error("Error en la conversion de DTO a View en la cotizacion.", e);
			throw new CompraVentaDolaresException(e);
		}
	}

	/**
	 * Gets the fecha.
	 *
	 * @return the fecha
	 */
	public String getFecha() {
		return fecha;
	}

	/**
	 * Sets the fecha.
	 *
	 * @param fecha
	 *            the fecha to set
	 * @throws CompraVentaDolaresException
	 *             the compra venta dolares exception
	 */
	public void setFecha(Date fecha) throws CompraVentaDolaresException {
		try {
			this.fecha = ISBANStringUtils.formatearFechaConAnio(fecha);
		} catch (Exception e) {
			LOGGER.error("Error en la conversion de DTO a View en la fecha.", e);
			throw new CompraVentaDolaresException(e);
		}
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
	 * Gets the importe compra dolar.
	 *
	 * @return the importeCompraDolar
	 */
	public String getImporteCompraDolar() {
		return importeCompraDolar;
	}

	/**
	 * Sets the importe compra dolar.
	 *
	 * @param importeCompraDolar
	 *            the importeCompraDolar to set
	 * @throws CompraVentaDolaresException
	 *             the compra venta dolares exception
	 */
	public void setImporteCompraDolar(BigDecimal importeCompraDolar) throws CompraVentaDolaresException {
		try {
			this.importeCompraDolar = ISBANStringUtils.formatearSaldoConSigno(importeCompraDolar);
		} catch (Exception e) {
			LOGGER.error("Error en la conversion de DTO a View en importe compra dolar.", e);
			throw new CompraVentaDolaresException(e);
		}
	}

	/**
	 * Gets the importe venta dolar.
	 *
	 * @return the importeVentaDolar
	 */
	public String getImporteVentaDolar() {
		return importeVentaDolar;
	}

	/**
	 * Sets the importe venta dolar.
	 *
	 * @param importeVentaDolar
	 *            the importeVentaDolar to set
	 * @throws CompraVentaDolaresException
	 *             the compra venta dolares exception
	 */
	public void setImporteVentaDolar(BigDecimal importeVentaDolar) throws CompraVentaDolaresException {
		try {
			this.importeVentaDolar = ISBANStringUtils.formatearSaldoConSigno(importeVentaDolar);
		} catch (Exception e) {
			LOGGER.error("Error en la conversion de DTO a View en importe venta dolar.", e);
			throw new CompraVentaDolaresException(e);
		}
	}

	/**
	 * Gets the importe debitar pesos.
	 *
	 * @return the importeDebitarPesos
	 */
	public String getImporteDebitarPesos() {
		return importeDebitarPesos;
	}

	/**
	 * Sets the importe debitar pesos.
	 *
	 * @param importeDebitarPesos
	 *            the importeDebitarPesos to set
	 * @throws CompraVentaDolaresException
	 *             the compra venta dolares exception
	 */
	public void setImporteDebitarPesos(BigDecimal importeDebitarPesos) throws CompraVentaDolaresException {
		try {
			this.importeDebitarPesos = ISBANStringUtils.formatearSaldoConSigno(importeDebitarPesos);
		} catch (Exception e) {
			LOGGER.error("Error en la conversion de DTO a View en importe debitar pesos.", e);
			throw new CompraVentaDolaresException(e);
		}
	}

	/**
	 * Gets the importe acreditar pesos.
	 *
	 * @return the importeAcreditarPesos
	 */
	public String getImporteAcreditarPesos() {
		return importeAcreditarPesos;
	}

	/**
	 * Sets the importe acreditar pesos.
	 *
	 * @param importeAcreditarPesos
	 *            the importeAcreditarPesos to set
	 * @throws CompraVentaDolaresException
	 *             the compra venta dolares exception
	 */
	public void setImporteAcreditarPesos(BigDecimal importeAcreditarPesos) throws CompraVentaDolaresException {
		try {
			this.importeAcreditarPesos = ISBANStringUtils.formatearSaldoConSigno(importeAcreditarPesos);
		} catch (Exception e) {
			LOGGER.error("Error en la conversion de DTO a View en importe acreditar pesos.", e);
			throw new CompraVentaDolaresException(e);
		}
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
	 * Gets the nro cuenta origen.
	 *
	 * @return the nroCuentaOrigen
	 */
	public String getNroCuentaOrigen() {
		return nroCuentaOrigen;
	}

	/**
	 * Sets the nro cuenta origen.
	 *
	 * @param nroCuentaOrigen
	 *            the nroCuentaOrigen to set
	 */
	public void setNroCuentaOrigen(String nroCuentaOrigen) {
		this.nroCuentaOrigen = nroCuentaOrigen;
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
	 * Gets the nro cuenta destino.
	 *
	 * @return the nroCuentaDestino
	 */
	public String getNroCuentaDestino() {
		return nroCuentaDestino;
	}

	/**
	 * Sets the nro cuenta destino.
	 *
	 * @param nroCuentaDestino
	 *            the nroCuentaDestino to set
	 */
	public void setNroCuentaDestino(String nroCuentaDestino) {
		this.nroCuentaDestino = nroCuentaDestino;
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
	 * Sets the numero cuenta error.
	 *
	 * @param numeroCuentaError
	 *            the new numero cuenta error
	 */
	public void setNumeroCuentaError(String numeroCuentaError) {
		this.numeroCuentaError = numeroCuentaError;
	}

	/**
	 * Sets the tipo operacion error.
	 *
	 * @param tipoOperacionError
	 *            the new tipo operacion error
	 */
	public void setTipoOperacionError(String tipoOperacionError) {
		this.tipoOperacionError = tipoOperacionError;
	}

	/**
	 * Gets the numero cuenta error.
	 *
	 * @return the numero cuenta error
	 */
	public String getNumeroCuentaError() {
		return numeroCuentaError;
	}

	/**
	 * Gets the tipo operacion error.
	 *
	 * @return the tipo operacion error
	 */
	public String getTipoOperacionError() {
		return tipoOperacionError;
	}

	/**
	 * Gets the alias cuenta origen.
	 *
	 * @return the alias cuenta origen
	 */
	public String getAliasCuentaOrigen() {
		return aliasCuentaOrigen;
	}

	/**
	 * Sets the alias cuenta origen.
	 *
	 * @param aliasCuentaOrigen
	 *            the new alias cuenta origen
	 */
	public void setAliasCuentaOrigen(String aliasCuentaOrigen) {
		this.aliasCuentaOrigen = aliasCuentaOrigen;
	}

	/**
	 * Gets the alias cuenta destino.
	 *
	 * @return the alias cuenta destino
	 */
	public String getAliasCuentaDestino() {
		return aliasCuentaDestino;
	}

	/**
	 * Sets the alias cuenta destino.
	 *
	 * @param aliasCuentaDestino
	 *            the new alias cuenta destino
	 */
	public void setAliasCuentaDestino(String aliasCuentaDestino) {
		this.aliasCuentaDestino = aliasCuentaDestino;
	}

	/**
	 * @return the importeImpuesto
	 */
	public String getImporteImpuesto() {
		return importeImpuesto;
	}
	
	/**
     * @return the importeImpuesto2
     */
    public String getImporteImpuesto2() {
        return importeImpuesto2;
    }

    /**
     * @param importeImpuesto2 the importeImpuesto2 to set
     * @throws CompraVentaDolaresException 
     */
    public void setImporteImpuesto2(BigDecimal importeImpuesto2) throws CompraVentaDolaresException {
        try {
            this.importeImpuesto2 = ISBANStringUtils.formatearSaldoConSigno(importeImpuesto2);
        } catch (Exception e) {
            LOGGER.error("Error en la conversion de DTO a View en el importe a debitar en pesos.", e);
            throw new CompraVentaDolaresException(e);
        }    
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
    public String getPorcentajeImpuesto2() {
        return porcentajeImpuesto2;
    }

    /**
     * @param porcentajeImpuesto2 the porcentajeImpuesto2 to set
     * @throws CompraVentaDolaresException 
     */
    public void setPorcentajeImpuesto2(BigDecimal porcentajeImpuesto2) throws CompraVentaDolaresException {
        try {
            this.porcentajeImpuesto2 = ISBANStringUtils.formatearSaldo(porcentajeImpuesto2);
        } catch (Exception e) {
            LOGGER.error(errorConversion, e);
            throw new CompraVentaDolaresException(e);
        }    
     }

    /**
	 * @param importeImpuesto the importeImpuesto to set
	 * @throws CompraVentaDolaresException 
	 */
	public void setImporteImpuesto(BigDecimal importeImpuesto) throws CompraVentaDolaresException {
		try {
			this.importeImpuesto = ISBANStringUtils.formatearSaldoConSigno(importeImpuesto);
		} catch (Exception e) {
			LOGGER.error(errorConversion, e);
			throw new CompraVentaDolaresException(e);
		}
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

	public String getImpuestoBienes() {
		return impuestoBienes;
	}

	public void setImpuestoBienes(BigDecimal impuestoBienes) throws CompraVentaDolaresException {
		try {
			this.impuestoBienes = ISBANStringUtils.formatearSaldo(impuestoBienes);
		} catch (Exception e) {
			LOGGER.error(errorConversion, e);
			throw new CompraVentaDolaresException(e);
		}
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
		hcb.append(nroCuentaDestino);
		hcb.append(nroCuentaOrigen);
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
		SimulacionCompraVentaDolarView other = (SimulacionCompraVentaDolarView) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(cotizacion, other.getCotizacion());
		eb.append(fecha, other.getFecha());
		eb.append(importeAcreditarPesos, other.getImporteAcreditarPesos());
		eb.append(importeCompraDolar, other.getImporteCompraDolar());
		eb.append(importeDebitarPesos, other.getImporteDebitarPesos());
		eb.append(importeVentaDolar, other.getImporteVentaDolar());
		eb.append(nroCuentaDestino, other.getNroCuentaDestino());
		eb.append(nroCuentaOrigen, other.getNroCuentaOrigen());
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
				.append("importeVentaDolar", importeVentaDolar).append("nroCuentaOrigen", nroCuentaOrigen)
				.append("tipoCuentaOrigen", tipoCuentaOrigen).append("nroCuentaDestino", nroCuentaDestino)
				.append("tipoCuentaDestino", tipoCuentaDestino).append("cotizacion", cotizacion)
				.append("importeDebitarPesos", importeDebitarPesos)
				.append("importeAcreditarPesos", importeAcreditarPesos).append("esCompra", esCompra)
				.append("esVenta", esVenta).append("fecha", fecha).append("legales", legales).toString();
	}
}