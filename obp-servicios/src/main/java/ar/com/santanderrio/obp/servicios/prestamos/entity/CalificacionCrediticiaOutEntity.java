/**
 * 
 */
package ar.com.santanderrio.obp.servicios.prestamos.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.beanio.annotation.Field;
import org.beanio.annotation.Record;

/**
 * Objeto utilizado para retornar del DAO.
 *
 * @author
 *
 */
@Record
public class CalificacionCrediticiaOutEntity {

	/** The Constant DELIMITER. */
	public static final String DELIMITER = "õ";

	/** The header trama. */
	@Field
	private String headerTrama;

	/** The codigo retorno extendido. */
	@Field(handlerName = "codigoRetornoExtendidoHandler")
	private String codigoRetornoExtendido;

	/** The porcentaje acuerdo. */
	@Field
	private String porcentajeAcuerdo;

	/** The porcentaje limite compra TC. */
	@Field
	private String porcentajeLimiteCompraTC;

	/** The porcentaje prestamo. */
	@Field
	private String porcentajePrestamo;

	/** The porcentaje cheque. */
	@Field
	private String porcentajeCheque;

	/** The porcentaje afectacion ingresos. */
	@Field
	private String porcentajeAfectacionIngresos;

	/** The porcentaje afectacion limite compra TC. */
	@Field
	private String porcentajeAfectacionLimiteCompraTC;

	/** The porcentaje afectacion acuerdo. */
	@Field
	private String porcentajeAfectacionAcuerdo;

	/** The importe ingresos. */
	@Field
	private String importeIngresos;

	/** The codigo segmento. */
	@Field
	private String codigoSegmento;

	/** The importe disponible prestamo. */
	@Field
	private String importeDisponiblePrestamo;

	/** The importe disponible cuota. */
	@Field
	private String importeDisponibleCuota;

	/** The importe disponible prestamo dolares. */
	@Field
	private String importeDisponiblePrestamoDolares;

	/** The importe disponible cuota dolares. */
	@Field
	private String importeDisponibleCuotaDolares;

	/** The codigo inhabilitado. */
	@Field
	private String codigoInhabilitado;

	/** The descripcion inhabilitado. */
	@Field
	private String descripcionInhabilitado;

	/** The porcentaje limite amex. */
	@Field
	private String porcentajeLimiteAmex;

	/** The porcentaje limite acuerdo. */
	@Field
	private String porcentajeLimiteAcuerdo;

	/** The porcentaje limite compra visa. */
	@Field
	private String porcentajeLimiteCompraVisa;

	/** The porcentaje limite prestamo. */
	@Field
	private String porcentajeLimitePrestamo;

	/** The porcentaje limite cheque. */
	@Field
	private String porcentajeLimiteCheque;

	/** The porcentaje limite compra amex. */
	@Field
	private String porcentajeLimiteCompraAmex;
	
	/**
	 * Gets the header trama.
	 *
	 * @return the header trama
	 */
	public String getHeaderTrama() {
		return headerTrama;
	}

	/**
	 * Sets the header trama.
	 *
	 * @param headerTrama
	 *            the new header trama
	 */
	public void setHeaderTrama(String headerTrama) {
		this.headerTrama = headerTrama;
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
	 * Gets the porcentaje acuerdo.
	 *
	 * @return the porcentaje acuerdo
	 */
	public String getPorcentajeAcuerdo() {
		return porcentajeAcuerdo;
	}

	/**
	 * Sets the porcentaje acuerdo.
	 *
	 * @param porcentajeAcuerdo
	 *            the new porcentaje acuerdo
	 */
	public void setPorcentajeAcuerdo(String porcentajeAcuerdo) {
		this.porcentajeAcuerdo = porcentajeAcuerdo;
	}

	/**
	 * Gets the porcentaje limite compra TC.
	 *
	 * @return the porcentaje limite compra TC
	 */
	public String getPorcentajeLimiteCompraTC() {
		return porcentajeLimiteCompraTC;
	}

	/**
	 * Sets the porcentaje limite compra TC.
	 *
	 * @param porcentajeLimiteCompraTC
	 *            the new porcentaje limite compra TC
	 */
	public void setPorcentajeLimiteCompraTC(String porcentajeLimiteCompraTC) {
		this.porcentajeLimiteCompraTC = porcentajeLimiteCompraTC;
	}

	/**
	 * Gets the porcentaje prestamo.
	 *
	 * @return the porcentaje prestamo
	 */
	public String getPorcentajePrestamo() {
		return porcentajePrestamo;
	}

	/**
	 * Sets the porcentaje prestamo.
	 *
	 * @param porcentajePrestamo
	 *            the new porcentaje prestamo
	 */
	public void setPorcentajePrestamo(String porcentajePrestamo) {
		this.porcentajePrestamo = porcentajePrestamo;
	}

	/**
	 * Gets the porcentaje cheque.
	 *
	 * @return the porcentaje cheque
	 */
	public String getPorcentajeCheque() {
		return porcentajeCheque;
	}

	/**
	 * Sets the porcentaje cheque.
	 *
	 * @param porcentajeCheque
	 *            the new porcentaje cheque
	 */
	public void setPorcentajeCheque(String porcentajeCheque) {
		this.porcentajeCheque = porcentajeCheque;
	}

	/**
	 * Gets the porcentaje afectacion ingresos.
	 *
	 * @return the porcentaje afectacion ingresos
	 */
	public String getPorcentajeAfectacionIngresos() {
		return porcentajeAfectacionIngresos;
	}

	/**
	 * Sets the porcentaje afectacion ingresos.
	 *
	 * @param porcentajeAfectacionIngresos
	 *            the new porcentaje afectacion ingresos
	 */
	public void setPorcentajeAfectacionIngresos(String porcentajeAfectacionIngresos) {
		this.porcentajeAfectacionIngresos = porcentajeAfectacionIngresos;
	}

	/**
	 * Gets the porcentaje afectacion limite compra TC.
	 *
	 * @return the porcentaje afectacion limite compra TC
	 */
	public String getPorcentajeAfectacionLimiteCompraTC() {
		return porcentajeAfectacionLimiteCompraTC;
	}

	/**
	 * Sets the porcentaje afectacion limite compra TC.
	 *
	 * @param porcentajeAfectacionLimiteCompraTC
	 *            the new porcentaje afectacion limite compra TC
	 */
	public void setPorcentajeAfectacionLimiteCompraTC(String porcentajeAfectacionLimiteCompraTC) {
		this.porcentajeAfectacionLimiteCompraTC = porcentajeAfectacionLimiteCompraTC;
	}

	/**
	 * Gets the porcentaje afectacion acuerdo.
	 *
	 * @return the porcentaje afectacion acuerdo
	 */
	public String getPorcentajeAfectacionAcuerdo() {
		return porcentajeAfectacionAcuerdo;
	}

	/**
	 * Sets the porcentaje afectacion acuerdo.
	 *
	 * @param porcentajeAfectacionAcuerdo
	 *            the new porcentaje afectacion acuerdo
	 */
	public void setPorcentajeAfectacionAcuerdo(String porcentajeAfectacionAcuerdo) {
		this.porcentajeAfectacionAcuerdo = porcentajeAfectacionAcuerdo;
	}

	/**
	 * Gets the importe ingresos.
	 *
	 * @return the importe ingresos
	 */
	public String getImporteIngresos() {
		return importeIngresos;
	}

	/**
	 * Sets the importe ingresos.
	 *
	 * @param importeIngresos
	 *            the new importe ingresos
	 */
	public void setImporteIngresos(String importeIngresos) {
		this.importeIngresos = importeIngresos;
	}

	/**
	 * Gets the codigo segmento.
	 *
	 * @return the codigo segmento
	 */
	public String getCodigoSegmento() {
		return codigoSegmento;
	}

	/**
	 * Sets the codigo segmento.
	 *
	 * @param codigoSegmento
	 *            the new codigo segmento
	 */
	public void setCodigoSegmento(String codigoSegmento) {
		this.codigoSegmento = codigoSegmento;
	}

	/**
	 * Gets the importe disponible prestamo.
	 *
	 * @return the importe disponible prestamo
	 */
	public String getImporteDisponiblePrestamo() {
		return importeDisponiblePrestamo;
	}

	/**
	 * Sets the importe disponible prestamo.
	 *
	 * @param importeDisponiblePrestamo
	 *            the new importe disponible prestamo
	 */
	public void setImporteDisponiblePrestamo(String importeDisponiblePrestamo) {
		this.importeDisponiblePrestamo = importeDisponiblePrestamo;
	}

	/**
	 * Gets the importe disponible cuota.
	 *
	 * @return the importe disponible cuota
	 */
	public String getImporteDisponibleCuota() {
		return importeDisponibleCuota;
	}

	/**
	 * Sets the importe disponible cuota.
	 *
	 * @param importeDisponibleCuota
	 *            the new importe disponible cuota
	 */
	public void setImporteDisponibleCuota(String importeDisponibleCuota) {
		this.importeDisponibleCuota = importeDisponibleCuota;
	}

	/**
	 * Gets the importe disponible prestamo dolares.
	 *
	 * @return the importe disponible prestamo dolares
	 */
	public String getImporteDisponiblePrestamoDolares() {
		return importeDisponiblePrestamoDolares;
	}

	/**
	 * Sets the importe disponible prestamo dolares.
	 *
	 * @param importeDisponiblePrestamoDolares
	 *            the new importe disponible prestamo dolares
	 */
	public void setImporteDisponiblePrestamoDolares(String importeDisponiblePrestamoDolares) {
		this.importeDisponiblePrestamoDolares = importeDisponiblePrestamoDolares;
	}

	/**
	 * Gets the importe disponible cuota dolares.
	 *
	 * @return the importe disponible cuota dolares
	 */
	public String getImporteDisponibleCuotaDolares() {
		return importeDisponibleCuotaDolares;
	}

	/**
	 * Sets the importe disponible cuota dolares.
	 *
	 * @param importeDisponibleCuotaDolares
	 *            the new importe disponible cuota dolares
	 */
	public void setImporteDisponibleCuotaDolares(String importeDisponibleCuotaDolares) {
		this.importeDisponibleCuotaDolares = importeDisponibleCuotaDolares;
	}

	/**
	 * Gets the codigo inhabilitado.
	 *
	 * @return the codigo inhabilitado
	 */
	public String getCodigoInhabilitado() {
		return codigoInhabilitado;
	}

	/**
	 * Sets the codigo inhabilitado.
	 *
	 * @param codigoInhabilitado
	 *            the new codigo inhabilitado
	 */
	public void setCodigoInhabilitado(String codigoInhabilitado) {
		this.codigoInhabilitado = codigoInhabilitado;
	}

	/**
	 * Gets the descripcion inhabilitado.
	 *
	 * @return the descripcion inhabilitado
	 */
	public String getDescripcionInhabilitado() {
		return descripcionInhabilitado;
	}

	/**
	 * Sets the descripcion inhabilitado.
	 *
	 * @param descripcionInhabilitado
	 *            the new descripcion inhabilitado
	 */
	public void setDescripcionInhabilitado(String descripcionInhabilitado) {
		this.descripcionInhabilitado = descripcionInhabilitado;
	}

	/**
	 * Gets the porcentaje limite amex.
	 *
	 * @return the porcentaje limite amex
	 */
	public String getPorcentajeLimiteAmex() {
		return porcentajeLimiteAmex;
	}

	/**
	 * Sets the porcentaje limite amex.
	 *
	 * @param porcentajeLimiteAmex
	 *            the new porcentaje limite amex
	 */
	public void setPorcentajeLimiteAmex(String porcentajeLimiteAmex) {
		this.porcentajeLimiteAmex = porcentajeLimiteAmex;
	}

	/**
	 * Gets the porcentaje limite acuerdo.
	 *
	 * @return the porcentaje limite acuerdo
	 */
	public String getPorcentajeLimiteAcuerdo() {
		return porcentajeLimiteAcuerdo;
	}

	/**
	 * Sets the porcentaje limite acuerdo.
	 *
	 * @param porcentajeLimiteAcuerdo
	 *            the new porcentaje limite acuerdo
	 */
	public void setPorcentajeLimiteAcuerdo(String porcentajeLimiteAcuerdo) {
		this.porcentajeLimiteAcuerdo = porcentajeLimiteAcuerdo;
	}

	/**
	 * Gets the porcentaje limite compra visa.
	 *
	 * @return the porcentaje limite compra visa
	 */
	public String getPorcentajeLimiteCompraVisa() {
		return porcentajeLimiteCompraVisa;
	}

	/**
	 * Sets the porcentaje limite compra visa.
	 *
	 * @param porcentajeLimiteCompraVisa
	 *            the new porcentaje limite compra visa
	 */
	public void setPorcentajeLimiteCompraVisa(String porcentajeLimiteCompraVisa) {
		this.porcentajeLimiteCompraVisa = porcentajeLimiteCompraVisa;
	}

	/**
	 * Gets the porcentaje limite prestamo.
	 *
	 * @return the porcentaje limite prestamo
	 */
	public String getPorcentajeLimitePrestamo() {
		return porcentajeLimitePrestamo;
	}

	/**
	 * Sets the porcentaje limite prestamo.
	 *
	 * @param porcentajeLimitePrestamo
	 *            the new porcentaje limite prestamo
	 */
	public void setPorcentajeLimitePrestamo(String porcentajeLimitePrestamo) {
		this.porcentajeLimitePrestamo = porcentajeLimitePrestamo;
	}

	/**
	 * Gets the porcentaje limite cheque.
	 *
	 * @return the porcentaje limite cheque
	 */
	public String getPorcentajeLimiteCheque() {
		return porcentajeLimiteCheque;
	}

	/**
	 * Sets the porcentaje limite cheque.
	 *
	 * @param porcentajeLimiteCheque
	 *            the new porcentaje limite cheque
	 */
	public void setPorcentajeLimiteCheque(String porcentajeLimiteCheque) {
		this.porcentajeLimiteCheque = porcentajeLimiteCheque;
	}

	/**
	 * Gets the porcentaje limite compra amex.
	 *
	 * @return the porcentaje limite compra amex
	 */
	public String getPorcentajeLimiteCompraAmex() {
		return porcentajeLimiteCompraAmex;
	}

	/**
	 * Sets the porcentaje limite compra amex.
	 *
	 * @param porcentajeLimiteCompraAmex
	 *            the new porcentaje limite compra amex
	 */
	public void setPorcentajeLimiteCompraAmex(String porcentajeLimiteCompraAmex) {
		this.porcentajeLimiteCompraAmex = porcentajeLimiteCompraAmex;
	}

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

		CalificacionCrediticiaOutEntity other = (CalificacionCrediticiaOutEntity) obj;
		EqualsBuilder eb = new EqualsBuilder();
		return eb
				// agregar los appends que corresponda segun los atributos
				// cargados, Ej: .append(extra, other.getExtra())
				.isEquals();
	}

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


}