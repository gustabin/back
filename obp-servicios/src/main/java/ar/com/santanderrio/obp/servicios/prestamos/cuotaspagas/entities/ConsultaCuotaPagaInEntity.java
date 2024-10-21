/*
 * 
 */
package ar.com.santanderrio.obp.servicios.prestamos.cuotaspagas.entities;

import javax.validation.constraints.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;

/**
 * The Class ConsultaCuotaPagaInEntity.
 *
 * @author florencia.n.martinez
 */
public class ConsultaCuotaPagaInEntity {

	/** The Constant COBR. */
	private static final String COBR = "COBR";

	/** The Constant TRES_ENTERO. */
	private static final Integer TRES_ENTERO = 3;

	/** The Constant CERO_STRING. */
	private static final String CERO_STRING = "0";

	/** The Constant ESPACIO_STRING. */
	private static final String ESPACIO_STRING = " ";

	/** The num registros. */
	@Pattern(regexp = "000")
	private String numRegistros;

	/** The oficina. */
	@Pattern(regexp = "[a-zA-B0-9]{4}")
	private String oficina;

	/** The cuenta. */
	@Pattern(regexp = "[a-zA-B0-9]{12}")
	private String cuenta;

	/** The cod evento. */
	@Pattern(regexp = "COBR")
	private String codEvento;

	/** The fec inicio. */
	@Pattern(regexp = "(19([0-9]{2})|20([0-9]{2})|21([0-9]{2}))-(0[1-9]|1[0-2])-(0[1-9]|1[0-9]|2[0-9]|3[0-1])")
	private String fecInicio;

	/** The fec fin. */
	@Pattern(regexp = "(20([0-9]{2})|21([0-9]{2}))-(0[1-9]|1[0-2])-(0[1-9]|1[0-9]|2[0-9]|3[0-1])")
	private String fecFin;

	/** The tipomov. */
	// TODO Espero definicion de campo
	@Pattern(regexp = "[ ]{1}|[a-zA-B0-9]{1}")
	private String tipomov;

	/** The timestamp. */
	// TODO Espero definicion de campo
	@Pattern(regexp = "[ ]{1}|[a-zA-B0-9.-]{26}")
	private String timestamp;

	/** The codconli. */
	// TODO Espero definicion de campo
	@Pattern(regexp = "[ ]{3}|[a-zA-B0-9]{3}")
	private String codconli;

	/** The num secuencia. */
	// TODO Espero definicion de campo
	@Pattern(regexp = "[ ]{1}|[0-9]{3}")
	private String numSecuencia;

	/**
	 * Instancia un nuevo objeto ConsultaCuotaPagaInEntity con los valores para
	 * la llamada al servicio "CNSPMOHIST 120" la primera vez.
	 *
	 * @param nroSucursal
	 *            the nro sucursal
	 * @param nroCuentaProducto
	 *            the nro cuenta producto
	 * @param fechaInicio
	 *            the fecha inicio
	 * @param fechaFin
	 *            the fecha fin
	 */
	public ConsultaCuotaPagaInEntity(String nroSucursal, String nroCuentaProducto, String fechaInicio,
			String fechaFin) {
		this.numRegistros = StringUtils.repeat(CERO_STRING, TRES_ENTERO);
		this.oficina = ISBANStringUtils.formateadorConCerosIzq(nroSucursal, 4);
		this.cuenta = ISBANStringUtils.formateadorConCerosIzq(nroCuentaProducto, 12);
		this.codEvento = COBR;
		this.fecFin = fechaFin;
		this.fecInicio = fechaInicio;
		this.tipomov = " ";
		this.timestamp = " ";
		this.codconli = StringUtils.repeat(ESPACIO_STRING, TRES_ENTERO);
		this.numSecuencia = " ";
	}

	/**
	 * Instancia un nuevo objeto ConsultaCuotaPagaInEntity con los valores para
	 * la rellamada al servicio "CNSPMOHIST 120" a partir del objeto
	 * ConsultaCuotaPagaOutEntity.
	 *
	 * @param outEntity
	 *            the out entity
	 */
	public void setRellamada(ConsultaCuotaPagaOutEntity outEntity) {
		this.timestamp = outEntity.getTimestamp();
		this.fecInicio = outEntity.getTimestamp().substring(0, 10);
		this.codconli = outEntity.getCodconli();
		this.numSecuencia = outEntity.getNumSecuencia();
	}

	/**
	 * Instancia un nuevo objeto ConsultaCuotaPagaInEntity.
	 */
	public ConsultaCuotaPagaInEntity() {
		super();
	}

	/**
	 * Gets the num registros.
	 *
	 * @return the numRegistros
	 */
	public String getNumRegistros() {
		return numRegistros;
	}

	/**
	 * Sets the num registros.
	 *
	 * @param numRegistros
	 *            the numRegistros to set
	 */
	public void setNumRegistros(String numRegistros) {
		this.numRegistros = numRegistros;
	}

	/**
	 * Gets the oficina.
	 *
	 * @return the oficina
	 */
	public String getOficina() {
		return oficina;
	}

	/**
	 * Sets the oficina.
	 *
	 * @param oficina
	 *            the oficina to set
	 */
	public void setOficina(String oficina) {
		this.oficina = oficina;
	}

	/**
	 * Gets the cuenta.
	 *
	 * @return the cuenta
	 */
	public String getCuenta() {
		return cuenta;
	}

	/**
	 * Sets the cuenta.
	 *
	 * @param cuenta
	 *            the cuenta to set
	 */
	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	/**
	 * Gets the cod evento.
	 *
	 * @return the codEvento
	 */
	public String getCodEvento() {
		return codEvento;
	}

	/**
	 * Sets the cod evento.
	 *
	 * @param codEvento
	 *            the codEvento to set
	 */
	public void setCodEvento(String codEvento) {
		this.codEvento = codEvento;
	}

	/**
	 * Gets the fec inicio.
	 *
	 * @return the fecInicio
	 */
	public String getFecInicio() {
		return fecInicio;
	}

	/**
	 * Sets the fec inicio.
	 *
	 * @param fecInicio
	 *            the fecInicio to set
	 */
	public void setFecInicio(String fecInicio) {
		this.fecInicio = fecInicio;
	}

	/**
	 * Gets the fec fin.
	 *
	 * @return the fecFin
	 */
	public String getFecFin() {
		return fecFin;
	}

	/**
	 * Sets the fec fin.
	 *
	 * @param fecFin
	 *            the fecFin to set
	 */
	public void setFecFin(String fecFin) {
		this.fecFin = fecFin;
	}

	/**
	 * Gets the tipomov.
	 *
	 * @return the tipomov
	 */
	public String getTipomov() {
		return tipomov;
	}

	/**
	 * Sets the tipomov.
	 *
	 * @param tipomov
	 *            the tipomov to set
	 */
	public void setTipomov(String tipomov) {
		this.tipomov = tipomov;
	}

	/**
	 * Gets the timestamp.
	 *
	 * @return the timestamp
	 */
	public String getTimestamp() {
		return timestamp;
	}

	/**
	 * Sets the timestamp.
	 *
	 * @param timestamp
	 *            the timestamp to set
	 */
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * Gets the codconli.
	 *
	 * @return the codconli
	 */
	public String getCodconli() {
		return codconli;
	}

	/**
	 * Sets the codconli.
	 *
	 * @param codconli
	 *            the codconli to set
	 */
	public void setCodconli(String codconli) {
		this.codconli = codconli;
	}

	/**
	 * Gets the num secuencia.
	 *
	 * @return the numSecuencia
	 */
	public String getNumSecuencia() {
		return numSecuencia;
	}

	/**
	 * Sets the num secuencia.
	 *
	 * @param numSecuencia
	 *            the numSecuencia to set
	 */
	public void setNumSecuencia(String numSecuencia) {
		this.numSecuencia = numSecuencia;
	}

	/**
	 * HashCode.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(codEvento);
		hcb.append(codconli);
		hcb.append(cuenta);
		hcb.append(fecFin);
		hcb.append(fecInicio);
		hcb.append(numRegistros);
		hcb.append(numSecuencia);
		hcb.append(oficina);
		hcb.append(timestamp);
		hcb.append(tipomov);
		return hcb.toHashCode();
	}

	/**
	 * Equal.
	 *
	 * @param obj
	 *            the obj
	 * @return true, if successful
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ConsultaCuotaPagaInEntity other = (ConsultaCuotaPagaInEntity) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(codEvento, other.getCodEvento());
		eb.append(codconli, other.getCodconli());
		eb.append(cuenta, other.getCuenta());
		eb.append(fecFin, other.getFecFin());
		eb.append(fecInicio, other.getFecInicio());
		eb.append(numRegistros, other.getNumRegistros());
		eb.append(numSecuencia, other.getNumSecuencia());
		eb.append(oficina, other.getOficina());
		eb.append(timestamp, other.getTimestamp());
		eb.append(tipomov, other.getTipomov());
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
		return new ToStringBuilder(this).append("numRegistros", numRegistros).append("oficina", oficina)
				.append("cuenta", cuenta).append("codEvento", codEvento).append("fecInicio", fecInicio)
				.append("fecFin", fecFin).append("tipomov", tipomov).append("timestamp", timestamp)
				.append("codconli", codconli).append("numSecuencia", numSecuencia).toString();
	}

}