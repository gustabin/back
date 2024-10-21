/*
 * 
 */
package ar.com.santanderrio.obp.servicios.prestamos.view;

import java.util.Calendar;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.joda.time.DateTimeComparator;

import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.prestamos.dto.PrestamoCanceladoDTO;

/**
 * Objeto utilizado para el Input del PrestamoSEI.
 * 
 * @author
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@JsonSerialize(include = Inclusion.NON_NULL)
public class PrestamoView {

	/** The id. */
	private String id;

	/** The fecha vencimiento. */
	private String fechaVencimiento;

	/** The tipo prestamo. */
	private String tipoPrestamo;

	/** The has alias. */
	private Boolean hasAlias;

	/** The alias. */
	private String alias;

	/** The nro prestamo. */
	private String nroPrestamo;

	/** The cuota. */
	private String cuota;

	/** The plazo. */
	private String plazo;

	/** The importe maxima cuota. */
	private String importeMaximaCuota;

	/** The is cuota vencida. */
	private Boolean isCuotaVencida;

	/** The no tiene cuotas pagas. */
	private Boolean noTieneCuotasPagas;

	/** The is ultima cuota. */
	private Boolean isUltimaCuota;

	/** The is cancelar feature enabled. */
	private Boolean isCancelarEnabled = Boolean.FALSE;

	private Boolean isShowHelpEnabled = Boolean.FALSE;

	/** The fecha inicio. */
	private String fechaInicio;

	/** The fecha vencimiento mobile. */
	private String fechaVencimientoMobile;

	/** The fecha inicio mobile. */
	private String fechaInicioMobile;

	/** The monto prestamo. */
	private String montoPrestamo;

	/** The motivo prestamo. */
	private String motivoPrestamo;

	/** The fecha finalizacion mobile. */
	private String fechaFinalizacionMobile;

	/** The importe total. */
	private String importeTotal;

	/** The cbu. */
	private String cbu;

	/** The num vin. */
	private String numVin;

	/** The is isUva. */
	private boolean isUva;
	
	/** the tipoError. */
	private String tipoError;
	
	/**
	 * Sets the uva.
	 *
	 * @param isUva
	 *            the new uva
	 */
	public void setUva(boolean isUva) {
		this.isUva = isUva;
	}

	/**
	 * Instantiates a new prestamo view.
	 */
	public PrestamoView() {
		super();
	}

	/**
	 * Instantiates a new prestamo view.
	 *
	 * @param prestamo
	 *            the prestamo
	 */
	public PrestamoView(PrestamoCanceladoDTO prestamo) {
		final String sinDatos = "-";
		this.tipoPrestamo = prestamo.getDescripcion();

		this.fechaVencimiento = prestamo.getFechaBajaDelContrato();
		this.fechaVencimientoMobile = prestamo.getFechaBajaDelContratoMobile();

		this.fechaInicio = prestamo.getFechaAltaContrato();
		this.fechaInicioMobile = prestamo.getFechaAltaContratoMobile();
		this.plazo = prestamo.getPlazo() == null ? sinDatos : prestamo.getPlazo().toString();
		this.importeTotal = prestamo.getImporteTotal() == null ? sinDatos
				: "$ " + ISBANStringUtils.formatearSaldo(prestamo.getImporteTotal());
		this.nroPrestamo = prestamo.getNumerPrestamo();
		this.cbu = prestamo.getCbu();
		this.motivoPrestamo = prestamo.getMotivoPrestamo();
		this.isUva = false;
		this.isCancelarEnabled = false;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id
	 *            the new id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Gets the fecha vencimiento.
	 *
	 * @return the fecha vencimiento
	 */
	public String getFechaVencimiento() {
		return fechaVencimiento;
	}

	/**
	 * Sets the fecha vencimiento.
	 *
	 * @param fechaVencimiento
	 *            the new fecha vencimiento
	 */
	public void setFechaVencimiento(String fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	/**
	 * Gets the alias.
	 *
	 * @return the alias
	 */
	public String getAlias() {
		return alias;
	}

	/**
	 * Sets the alias.
	 *
	 * @param alias
	 *            the new alias
	 */
	public void setAlias(String alias) {
		this.alias = alias;
	}

	/**
	 * Gets the nro prestamo.
	 *
	 * @return the nro prestamo
	 */
	public String getNroPrestamo() {
		return nroPrestamo;
	}

	/**
	 * Sets the nro prestamo.
	 *
	 * @param nroPrestamo
	 *            the new nro prestamo
	 */
	public void setNroPrestamo(String nroPrestamo) {
		this.nroPrestamo = nroPrestamo;
	}

	/**
	 * Gets the cuota.
	 *
	 * @return the cuota
	 */
	public String getCuota() {
		return cuota;
	}

	/**
	 * Sets the cuota.
	 *
	 * @param cuota
	 *            the new cuota
	 */
	public void setCuota(String cuota) {
		this.cuota = cuota;
	}

	/**
	 * Gets the plazo.
	 *
	 * @return the plazo
	 */
	public String getPlazo() {
		return plazo;
	}

	/**
	 * Sets the plazo.
	 *
	 * @param plazo
	 *            the new plazo
	 */
	public void setPlazo(String plazo) {
		this.plazo = plazo;
	}

	/**
	 * Gets the impote maxima cuota.
	 *
	 * @return the impote maxima cuota
	 */
	public String getImpoteMaximaCuota() {
		return importeMaximaCuota;
	}

	/**
	 * Sets the importe maxima cuota.
	 *
	 * @param importeMaximaCuota
	 *            the new importe maxima cuota
	 */
	public void setImporteMaximaCuota(String importeMaximaCuota) {
		this.importeMaximaCuota = importeMaximaCuota;
	}

	/**
	 * Gets the checks for alias.
	 *
	 * @return the checks for alias
	 */
	public Boolean getHasAlias() {
		return hasAlias;
	}

	/**
	 * Sets the checks for alias.
	 *
	 * @param hasAlias
	 *            the new checks for alias
	 */
	public void setHasAlias(Boolean hasAlias) {
		this.hasAlias = hasAlias;
	}

	/**
	 * Gets the importe maxima cuota.
	 *
	 * @return the importe maxima cuota
	 */
	public String getImporteMaximaCuota() {
		return importeMaximaCuota;
	}

	/**
	 * Gets the tipo prestamo.
	 *
	 * @return the tipo prestamo
	 */
	public String getTipoPrestamo() {
		return tipoPrestamo;
	}

	/**
	 * Sets the tipo prestamo.
	 *
	 * @param tipoPrestamo
	 *            the new tipo prestamo
	 */
	public void setTipoPrestamo(String tipoPrestamo) {
		this.tipoPrestamo = tipoPrestamo;
	}

	/**
	 * Gets the checks if is cuota vencida.
	 *
	 * @return the checks if is cuota vencida
	 */
	public Boolean getIsCuotaVencida() {
		return isCuotaVencida;
	}

	/**
	 * Sets the checks if is cuota vencida.
	 *
	 * @param fechaVencimiento
	 *            the new checks if is cuota vencida
	 */
	public void setIsCuotaVencida(Date fechaVencimiento) {
		this.isCuotaVencida = DateTimeComparator.getDateOnlyInstance().compare(fechaVencimiento,
				DateUtils.truncate(Calendar.getInstance().getTime(), java.util.Calendar.DAY_OF_MONTH)) == -1;
	}

	/**
	 * Gets the no tiene cuotas pagas.
	 *
	 * @return the no tiene cuotas pagas
	 */
	public Boolean getNoTieneCuotasPagas() {
		return noTieneCuotasPagas;
	}

	/**
	 * Sets the no tiene cuotas pagas.
	 *
	 * @param noTieneCuotasPagas
	 *            the new no tiene cuotas pagas
	 */
	public void setNoTieneCuotasPagas(Boolean noTieneCuotasPagas) {
		this.noTieneCuotasPagas = noTieneCuotasPagas;
	}

	/**
	 * Gets the checks if is ultima cuota.
	 *
	 * @return the checks if is ultima cuota
	 */
	public Boolean getIsUltimaCuota() {
		return isUltimaCuota;
	}

	/**
	 * Sets the checks if is ultima cuota.
	 *
	 * @param isUltimaCuota
	 *            the new checks if is ultima cuota
	 */
	public void setIsUltimaCuota(Boolean isUltimaCuota) {
		this.isUltimaCuota = isUltimaCuota;
	}

	public Boolean getCancelarEnabled() {
		return isCancelarEnabled;
	}

	public void setCancelarEnabled(Boolean cancelarEnabled) {
		isCancelarEnabled = cancelarEnabled;
	}
	
	public Boolean getShowHelpEnabled() {
		return isShowHelpEnabled;
	}
	
	public void setShowHelpEnabled(Boolean isShowHelpEnabled) {
		this.isShowHelpEnabled = isShowHelpEnabled;
	}

	/**
	 * Gets the fecha inicio.
	 *
	 * @return the fecha inicio
	 */
	public String getFechaInicio() {
		return fechaInicio;
	}

	/**
	 * Sets the fecha inicio.
	 *
	 * @param fechaInicio
	 *            the new fecha inicio
	 */
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	/**
	 * Gets the fecha vencimiento mobile.
	 *
	 * @return the fecha vencimiento mobile
	 */
	public String getFechaVencimientoMobile() {
		return fechaVencimientoMobile;
	}

	/**
	 * Sets the fecha vencimiento mobile.
	 *
	 * @param fechaVencimientoMobile
	 *            the new fecha vencimiento mobile
	 */
	public void setFechaVencimientoMobile(String fechaVencimientoMobile) {
		this.fechaVencimientoMobile = fechaVencimientoMobile;
	}

	/**
	 * Gets the fecha inicio mobile.
	 *
	 * @return the fecha inicio mobile
	 */
	public String getFechaInicioMobile() {
		return fechaInicioMobile;
	}

	/**
	 * Sets the fecha inicio mobile.
	 *
	 * @param fechaInicioMobile
	 *            the new fecha inicio mobile
	 */
	public void setFechaInicioMobile(String fechaInicioMobile) {
		this.fechaInicioMobile = fechaInicioMobile;
	}

	/**
	 * Gets the fecha finalizacion mobile.
	 *
	 * @return the fecha finalizacion mobile
	 */
	public String getFechaFinalizacionMobile() {
		return fechaFinalizacionMobile;
	}

	/**
	 * Sets the fecha finalizacion mobile.
	 *
	 * @param fechaFinalizacionMobile
	 *            the new fecha finalizacion mobile
	 */
	public void setFechaFinalizacionMobile(String fechaFinalizacionMobile) {
		this.fechaFinalizacionMobile = fechaFinalizacionMobile;
	}

	/**
	 * Gets the importe total.
	 *
	 * @return the importe total
	 */
	public String getImporteTotal() {
		return importeTotal;
	}

	/**
	 * Sets the importe total.
	 *
	 * @param importeTotal
	 *            the new importe total
	 */
	public void setImporteTotal(String importeTotal) {
		this.importeTotal = importeTotal;
	}

	/**
	 * Gets the monto prestamo.
	 *
	 * @return the monto prestamo
	 */
	public String getMontoPrestamo() {
		return montoPrestamo;
	}

	/**
	 * Sets the monto prestamo.
	 *
	 * @param montoPrestamo
	 *            the new monto prestamo
	 */
	public void setMontoPrestamo(String montoPrestamo) {
		this.montoPrestamo = montoPrestamo;
	}

	/**
	 * Gets the motivo prestamo.
	 *
	 * @return the motivo prestamo
	 */
	public String getMotivoPrestamo() {
		return motivoPrestamo;
	}

	/**
	 * Sets the motivo prestamo.
	 *
	 * @param motivoPrestamo
	 *            the new motivo prestamo
	 */
	public void setMotivoPrestamo(String motivoPrestamo) {
		this.motivoPrestamo = motivoPrestamo;
	}

	/**
	 * Gets the cbu.
	 *
	 * @return the cbu
	 */
	public String getCbu() {
		return cbu;
	}

	/**
	 * Sets the cbu.
	 *
	 * @param cbu
	 *            the new cbu
	 */
	public void setCbu(String cbu) {
		this.cbu = cbu;
	}

	/**
	 * Sets the checks if is cuota vencida.
	 *
	 * @param isCuotaVencida
	 *            the new checks if is cuota vencida
	 */
	public void setIsCuotaVencida(Boolean isCuotaVencida) {
		this.isCuotaVencida = isCuotaVencida;
	}

	/**
	 * Gets the num vin.
	 *
	 * @return the numVin
	 */
	public String getNumVin() {
		return numVin;
	}

	/**
	 * Sets the num vin.
	 *
	 * @param numVin
	 *            the numVin to set
	 */
	public void setNumVin(String numVin) {
		this.numVin = numVin;
	}
		
	/**
	 * Sets the num vin.
	 *
	 * @param isUva
	 *            the isUva to set
	 */
	public void setIsUva(boolean isUva) {
		this.isUva = isUva;
	}
	
	/**
	 * Gets the isUva.
	 *
	 * @return the get if is uva
	 */
	public boolean getIsUva() {
		return isUva;
	}
	
	/**
	 * Gets the tipoError.
	 *
	 * @return the get errorType
	 */
	public String getTipoError() {
		return tipoError;
	}

	/**
	 * Sets the tipo error.
	 *
	 * @param tipoError
	 *            the new tipo error
	 */
	public void setTipoError(String tipoError) {
		this.tipoError = tipoError;
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

		PrestamoView other = (PrestamoView) obj;
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
