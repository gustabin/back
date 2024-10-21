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
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.santanderrio.obp.servicios.compraventa.bo.impl.CompraVentaDolaresException;
import ar.com.santanderrio.obp.servicios.compraventa.dto.ComprobanteCompraVentaDTO;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionDTO;

/**
 * The Class ComprobanteCompraVentaView.
 *
 * @author sabrina.cis
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include = Inclusion.NON_NULL)
public class ComprobanteCompraVentaView {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ComprobanteCompraVentaView.class);

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

	/** The cotizacion. */
	@Pattern(regexp = "([1-9]\\d|\\d),[0-9]{2}")
	private String cotizacion;

	/** The importe debitar pesos. */
	@Pattern(regexp = "^(([1-9]\\d?((\\.\\d{3}){0,3}))|(\\d)|([1-9]\\d{2}((\\.\\d{3}){0,2})))(,\\d{1,2})$")
	private String importeDebitarPesos;

	/** The importe acreditar pesos. */
	@Pattern(regexp = "^(([1-9]\\d?((\\.\\d{3}){0,3}))|(\\d)|([1-9]\\d{2}((\\.\\d{3}){0,2})))(,\\d{1,2})$")
	private String importeAcreditarPesos;

	/** The es compra. */
	private Boolean esCompra = Boolean.TRUE;

	/** The es venta. */
	private Boolean esVenta = Boolean.FALSE;

	/** The fecha. */
	@Pattern(regexp = "([0-2]\\d|3[0-1])/(1[0-2]|0\\d)/(19|20(\\d{2}))")
	private String fecha;

	/** The hora. */
	@Pattern(regexp = "([0-1][0-9]|2[0-3]):([0-5][0-9])")
	private String hora;

	/** The legales uno. */
	@Pattern(regexp = "[a-z|A-Z|0-9|á|Á|é|É|í|Í|ó|Ó|ú|Ú|ñ|Ñ|$| |.|,|:|;|(|)]+")
	private String legalesUno;

	/** The legales dos. */
	@Pattern(regexp = "[a-z|A-Z|0-9|á|Á|é|É|í|Í|ó|Ó|ú|Ú|ñ|Ñ|$| |.|,|:|;|(|)]+")
	private String legalesDos;

	/** The numero operacion. */
	@Pattern(regexp = "[0-9]+")
	private String numeroOperacion;

	/** The numeroComprobante. */
	@Pattern(regexp = "[0-9]+")
	private String numeroComprobante;

	/** MensajeCompra. */
	private String mensajeCompra;
	
	/** MensajeVenta. */
	private String mensajeVenta;

	/** El numero de cuenta origen en caso de respuesta warning *. */
	private String numeroCuentaError;

	/** El tipo de operacion en caso de respuesta warning *. */
	private String tipoOperacionError;

	/**
	 * Booleano que indica si el usuario puede reintentar llegado a un error.
	 */
	private boolean puedeReintentar;

	/** String que guarda el importe de configuracion. */
	private String montoCache;
	
	private String importeImpuesto;
	
	private Boolean muestraImpuestos;
	
    private String importeImpuesto2;

    private String conceptoImpuesto2;
    
    private String impuesto2;

    private String regimenImpositivo2;

    private String porcentajeImpuesto2;

	private String impuestoBienes;
    
    private AutentificacionDTO desafio;
    
    // retornamos datos que vienen del request si corresponde ingreso de desafio
    
    private String numeroCuentaOrigen;
    
    private String numeroCuentaDestino;
    
    private String importeCredito; 
    
    private String importeDebito;
    
    private boolean isDolar;
    
    // FIN retornamos datos que vienen del request si corresponde ingreso de desafio
    
    private String mensajeFondoCumunInv;
    
	/**
	 * Instantiates a new comprobante compra venta view.
	 */
	public ComprobanteCompraVentaView() {
		super();
	}

	/**
	 * Instantiates a new comprobante compra venta view.
	 *
	 * @param dto
	 *            the dto
	 * @throws CompraVentaDolaresException
	 *             the compra venta dolares exception
	 */
	public ComprobanteCompraVentaView(ComprobanteCompraVentaDTO dto) throws CompraVentaDolaresException {
		try {
			if (dto.getEsCompra()) {
				this.setImporteCompraDolar(dto.getImporteDolarAcreditado());
				this.setImporteDebitarPesos(dto.getImportePesosDebitado());
				this.setEsCompra(Boolean.TRUE);
				this.setEsVenta(Boolean.FALSE);
				this.setImporteImpuesto(dto.getImporteImpuesto());
				this.setMuestraImpuestos(dto.getMuestraImpuestos());
		        this.setImporteImpuesto2(dto.getImporteImpuesto2());
		        this.setConceptoImpuesto2(dto.getConceptoImpuesto2());
		        this.setImpuesto2(dto.getImpuesto2());
		        this.setRegimenImpositivo2(dto.getRegimenImpositivo2());
		        this.setPorcentajeImpuesto2(dto.getPorcentajeImpuesto2());
				this.setImpuestoBienes(dto.getImpuestoBienes2());

			} else {
				this.setImporteVentaDolar(dto.getImporteDolaresDebitado());
				this.setImporteAcreditarPesos(dto.getImportePesosAcreditado());
				this.setEsCompra(Boolean.FALSE);
				this.setEsVenta(Boolean.TRUE);
			}
			this.setNroCuentaOrigen(dto.getCuentaOrigenNumero());
			this.setTipoCuentaOrigen(dto.getCuentaOrigenTipo());
			this.setNroCuentaDestino(dto.getCuentaDestinoNumero());
			this.setTipoCuentaDestino(dto.getCuentaDestinoTipo());
			this.setCotizacion(dto.getCotizacionAplicada());
			this.setFecha(dto.getFecha());
			this.setHora(dto.getHora());
			this.setLegalesUno(dto.getLegalCompraUno());
			this.setLegalesDos(dto.getLegalCompraDos());
			this.setNumeroComprobante(dto.getNumeroComprobante());
			this.setNumeroOperacion(dto.getNumeroOperacion());
		} catch (CompraVentaDolaresException e) {
			LOGGER.error("Error en la conversion de DTO a View en Comprobante.", e);
			throw new CompraVentaDolaresException(e);
		}
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
			LOGGER.error("Error en la conversion de DTO a View en el importe a debitar en pesos.", e);
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
			LOGGER.error("Error en la conversion de DTO a View en el importe a acreditar en pesos.", e);
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
	 * Gets the legales uno.
	 *
	 * @return the legalesUno
	 */
	public String getLegalesUno() {
		return legalesUno;
	}

	/**
	 * Sets the legales uno.
	 *
	 * @param legalesUno
	 *            the legalesUno to set
	 */
	public void setLegalesUno(String legalesUno) {
		this.legalesUno = legalesUno;
	}

	/**
	 * Gets the legales dos.
	 *
	 * @return the legalesDos
	 */
	public String getLegalesDos() {
		return legalesDos;
	}

	/**
	 * Sets the legales dos.
	 *
	 * @param legalesDos
	 *            the legalesDos to set
	 */
	public void setLegalesDos(String legalesDos) {
		this.legalesDos = legalesDos;
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
	 * getMensajeCompra.
	 *
	 * @return the mensaje compra
	 */
	public String getMensajeCompra() {
		return mensajeCompra;
	}

	/**
	 * setMensajeCompra.
	 *
	 * @param mensajeCompra
	 *            the new mensaje compra
	 */
	public void setMensajeCompra(String mensajeCompra) {
		this.mensajeCompra = mensajeCompra;
	}

	public String getMensajeVenta() {
		return mensajeVenta;
	}

	public void setMensajeVenta(String mensajeVenta) {
		this.mensajeVenta = mensajeVenta;
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
	 * Checks if is puede reintentar.
	 *
	 * @return true, if is puede reintentar
	 */
	public boolean isPuedeReintentar() {
		return puedeReintentar;
	}

	/**
	 * Sets the puede reintentar.
	 *
	 * @param puedeReintentar
	 *            the new puede reintentar
	 */
	public void setPuedeReintentar(boolean puedeReintentar) {
		this.puedeReintentar = puedeReintentar;
	}

	/**
	 * Gets the monto cache.
	 *
	 * @return the monto cache
	 */
	public String getMontoCache() {
		return montoCache;
	}

	/**
	 * Sets the monto cache.
	 *
	 * @param montoCache
	 *            the new monto cache
	 */
	public void setMontoCache(String montoCache) {
		this.montoCache = montoCache;
	}

	/**
	 * @return the importeImpuesto
	 */
	public String getImporteImpuesto() {
		return importeImpuesto;
	}

	/**
	 * @param importeImpuesto the importeImpuesto to set
	 * @throws CompraVentaDolaresException 
	 */
	public void setImporteImpuesto(BigDecimal importeImpuesto) throws CompraVentaDolaresException {
		try {
			this.importeImpuesto = ISBANStringUtils.formatearSaldoConSigno(importeImpuesto);
		} catch (Exception e) {
			LOGGER.error("Error en la conversion de DTO a View en importe compra dolar.", e);
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
		hcb.append(hora);
		hcb.append(importeAcreditarPesos);
		hcb.append(importeCompraDolar);
		hcb.append(importeDebitarPesos);
		hcb.append(importeVentaDolar);
		hcb.append(legalesDos);
		hcb.append(legalesUno);
		hcb.append(numeroComprobante);
		hcb.append(numeroOperacion);
		hcb.append(nroCuentaDestino);
		hcb.append(nroCuentaOrigen);
		hcb.append(tipoCuentaDestino);
		hcb.append(tipoCuentaOrigen);
		hcb.append(mensajeCompra);
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
		ComprobanteCompraVentaView other = (ComprobanteCompraVentaView) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(cotizacion, other.getCotizacion());
		eb.append(esCompra, other.getEsCompra());
		eb.append(esVenta, other.getEsVenta());
		eb.append(fecha, other.getFecha());
		eb.append(hora, other.getHora());
		eb.append(importeAcreditarPesos, other.getImporteAcreditarPesos());
		eb.append(importeCompraDolar, other.getImporteCompraDolar());
		eb.append(importeDebitarPesos, other.getImporteDebitarPesos());
		eb.append(importeVentaDolar, other.getImporteVentaDolar());
		eb.append(legalesDos, other.getLegalesDos());
		eb.append(legalesUno, other.getLegalesUno());
		eb.append(numeroComprobante, other.getNumeroComprobante());
		eb.append(numeroOperacion, other.getNumeroOperacion());
		eb.append(nroCuentaDestino, other.getNroCuentaDestino());
		eb.append(nroCuentaOrigen, other.getNroCuentaOrigen());
		eb.append(tipoCuentaDestino, other.getTipoCuentaDestino());
		eb.append(tipoCuentaOrigen, other.getTipoCuentaOrigen());
		eb.append(mensajeCompra, other.getMensajeCompra());
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
				.append("esVenta", esVenta).append("fecha", fecha).append("hora", hora).append("legalesUno", legalesUno)
				.append("legalesDos", legalesDos).append("numeroComprobante", numeroComprobante)
				.append("numeroOperacion", numeroOperacion).append("mensajeCompra", mensajeCompra).toString();
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
            LOGGER.error("Error en la conversion de DTO a View en el importe a debitar en pesos.", e);
            throw new CompraVentaDolaresException(e);
        }    
      }

    /**
     * @return the importeImpuesto2
     */
    public String getImporteImpuesto2() {
        return importeImpuesto2;
    }

    /**
     * @param importeImpuesto2 the importeImpuesto2 to set
     */
    public void setImporteImpuesto2(BigDecimal importeImpuesto2) throws CompraVentaDolaresException {
        try {
            this.importeImpuesto2 = ISBANStringUtils.formatearSaldoConSigno(importeImpuesto2);
        } catch (Exception e) {
            LOGGER.error("Error en la conversion de DTO a View en el importe a debitar en pesos.", e);
            throw new CompraVentaDolaresException(e);
        }
    }

	public String getImpuestoBienes() {
		return impuestoBienes;
	}

	public void setImpuestoBienes(BigDecimal impuestoBienes) throws CompraVentaDolaresException {
		try {
			this.impuestoBienes = ISBANStringUtils.formatearSaldo(impuestoBienes);
		} catch (Exception e) {
			LOGGER.error("Error en la conversion de DTO a View en importeImpuesto.", e);
			throw new CompraVentaDolaresException(e);
		}
	}

	public AutentificacionDTO getDesafio() {
		return desafio;
	}

	public void setDesafio(AutentificacionDTO desafio) {
		this.desafio = desafio;
	}

	public String getNumeroCuentaOrigen() {
		return numeroCuentaOrigen;
	}

	public void setNumeroCuentaOrigen(String numeroCuentaOrigen) {
		this.numeroCuentaOrigen = numeroCuentaOrigen;
	}

	public String getNumeroCuentaDestino() {
		return numeroCuentaDestino;
	}

	public void setNumeroCuentaDestino(String numeroCuentaDestino) {
		this.numeroCuentaDestino = numeroCuentaDestino;
	}

	public String getImporteCredito() {
		return importeCredito;
	}

	public void setImporteCredito(String importeCredito) {
		this.importeCredito = importeCredito;
	}

	public String getImporteDebito() {
		return importeDebito;
	}

	public void setImporteDebito(String importeDebito) {
		this.importeDebito = importeDebito;
	}

	public boolean isDolar() {
		return isDolar;
	}

	public void setDolar(boolean isDolar) {
		this.isDolar = isDolar;
	}

	public String getMensajeFondoCumunInv() {
		return mensajeFondoCumunInv;
	}

	public void setMensajeFondoCumunInv(String mensajeFondoCumunInv) {
		this.mensajeFondoCumunInv = mensajeFondoCumunInv;
	}
}
