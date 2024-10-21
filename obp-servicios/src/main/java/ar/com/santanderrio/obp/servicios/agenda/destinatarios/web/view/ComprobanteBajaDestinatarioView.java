/**
 * 
 */
package ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.view;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import ar.com.santanderrio.obp.base.comun.OperacionesRSAEnum;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.dto.ComprobanteAltaDestinatarioDTO;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ComprobanteAltaCBUDTO;

/**
 * The Class ComprobanteAltaDestinatarioView.
 *
 */
public class ComprobanteBajaDestinatarioView extends AliasRsaView {

	/** serial version UID. */
	private static final long serialVersionUID = 1L;

	/** The mensaje efectivizacion. */
	private String mensajeEfectivizacion;

	/** The nroComprobante. */
	private String nroComprobante;

	/** The fecha. */
	private String fecha;

	/** The hora. */
	private String hora;

	/** The nombre cliente. */
	private String nombreCliente;

	/** The nombre banco. */
	private String nombreBanco;

	/** The tipo cuenta. */
	private String tipoCuenta;

	/** The numero sucursal. */
	private String numeroSucursal;

	/** The numero cuenta. */
	private String numeroCuenta;

	/** The alias anterior. */
	private String aliasAnterior;

	/** The reasigna. */
	private String reasigna;

	/** The tipo identificacion. */
	private String tipoIdentificacion;

	/** The identificacion cliente. */
	private String identificacionCliente;

	/**
	 * Instantiates a new comprobante baja destinatario view.
	 */
	public ComprobanteBajaDestinatarioView() {
		super(OperacionesRSAEnum.ACTIVO);

	}

	/**
	 * Instantiates a new comprobante alta destinatario view.
	 *
	 * @param dto
	 *            the dto
	 */
	public ComprobanteBajaDestinatarioView(ComprobanteAltaDestinatarioDTO dto) {
		super(OperacionesRSAEnum.ACTIVO);
		this.mensajeEfectivizacion = dto.getMensajeEfectivizacion();
		this.nroComprobante = dto.getNroComprobante();
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		this.fecha = df.format(dto.getFecha());
		this.hora = dto.getHora();
	}

	/**
	 * Instantiates a new comprobante baja destinatario view.
	 *
	 * @param dto
	 *            the dto
	 * @param detalle
	 *            the detalle
	 */
	public ComprobanteBajaDestinatarioView(ComprobanteAltaCBUDTO dto, ComprobanteBajaDestinatarioView detalle) {
		super(OperacionesRSAEnum.ACTIVO);
		this.mensajeEfectivizacion = dto.getMensajeEfectivizacion();
		this.nroComprobante = dto.getNroComprobante();
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		this.fecha = df.format(dto.getFecha());
		this.hora = dto.getHora();
		this.aliasCbu = detalle.getAliasCbu();
		this.nombreCliente = detalle.getNombreCliente();
		this.cuit = detalle.getCuit();
		this.nombreBanco = detalle.getNombreBanco();
		this.tipoCuenta = detalle.getTipoCuenta();
		this.numeroSucursal = detalle.getNumeroSucursal();
		this.numeroCuenta = detalle.getNumeroCuenta();
		this.cbu = detalle.getCbu();
	}

	/**
	 * Gets the mensaje efectivizacion.
	 *
	 * @return the mensajeEfectivizacion
	 */
	public String getMensajeEfectivizacion() {
		return mensajeEfectivizacion;
	}

	/**
	 * Sets the mensaje efectivizacion.
	 *
	 * @param mensajeEfectivizacion
	 *            the mensajeEfectivizacion to set
	 */
	public void setMensajeEfectivizacion(String mensajeEfectivizacion) {
		this.mensajeEfectivizacion = mensajeEfectivizacion;
	}

	/**
	 * Gets the nroComprobante.
	 *
	 * @return the nroComprobante
	 */
	public String getNroComprobante() {
		return nroComprobante;
	}

	/**
	 * Sets the nroComprobante.
	 *
	 * @param comprobante
	 *            the new nro comprobante
	 */
	public void setNroComprobante(String comprobante) {
		this.nroComprobante = comprobante;
	}

	/**
	 * d Gets the fecha.
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
	 */
	public void setFecha(Date fecha) {
		if (fecha != null) {
			this.fecha = ISBANStringUtils.formatearFecha(fecha);
		} else {
			this.fecha = ISBANStringUtils.GUION_STRING;
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
	 * Gets the nombre cliente.
	 *
	 * @return the nombreCliente
	 */
	public String getNombreCliente() {
		return nombreCliente;
	}

	/**
	 * Sets the nombre cliente.
	 *
	 * @param nombreCliente
	 *            the nombreCliente to set
	 */
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	/**
	 * Gets the cuit.
	 *
	 * @return the cuit
	 */
	public String getCuit() {
		return cuit;
	}

	/**
	 * Sets the cuit.
	 *
	 * @param cuit
	 *            the cuit to set
	 */
	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

	/**
	 * Gets the nombre banco.
	 *
	 * @return the nombreBanco
	 */
	public String getNombreBanco() {
		return nombreBanco;
	}

	/**
	 * Sets the nombre banco.
	 *
	 * @param nombreBanco
	 *            the nombreBanco to set
	 */
	public void setNombreBanco(String nombreBanco) {
		this.nombreBanco = nombreBanco;
	}

	/**
	 * Gets the tipo cuenta.
	 *
	 * @return the tipoCuenta
	 */
	public String getTipoCuenta() {
		return tipoCuenta;
	}

	/**
	 * Sets the tipo cuenta.
	 *
	 * @param tipoCuenta
	 *            the tipoCuenta to set
	 */
	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	/**
	 * Gets the numero sucursal.
	 *
	 * @return the numeroSucursal
	 */
	public String getNumeroSucursal() {
		return numeroSucursal;
	}

	/**
	 * Sets the numero sucursal.
	 *
	 * @param numeroSucursal
	 *            the numeroSucursal to set
	 */
	public void setNumeroSucursal(String numeroSucursal) {
		this.numeroSucursal = numeroSucursal;
	}

	/**
	 * Gets the numero cuenta.
	 *
	 * @return the numeroCuenta
	 */
	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	/**
	 * Sets the numero cuenta.
	 *
	 * @param numeroCuenta
	 *            the numeroCuenta to set
	 */
	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	/**
	 * HashCode.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(nroComprobante);
		hcb.append(fecha);
		hcb.append(hora);
		hcb.append(mensajeEfectivizacion);
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
		ComprobanteBajaDestinatarioView other = (ComprobanteBajaDestinatarioView) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(nroComprobante, other.getNroComprobante());
		eb.append(fecha, other.getFecha());
		eb.append(hora, other.getHora());
		eb.append(mensajeEfectivizacion, other.getMensajeEfectivizacion());
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
		return new ToStringBuilder(this).append("mensajeEfectivizacion", mensajeEfectivizacion)
				.append("nroComprobante", nroComprobante).append("fecha", fecha).append("hora", hora).toString();
	}

	/**
	 * Gets the alias anterior.
	 *
	 * @return the aliasAnterior
	 */
	public String getAliasAnterior() {
		return aliasAnterior;
	}

	/**
	 * Sets the alias anterior.
	 *
	 * @param aliasAnterior
	 *            the alias Anterior to set
	 */
	public void setAliasAnterior(String aliasAnterior) {
		this.aliasAnterior = aliasAnterior;
	}

	/**
	 * Gets the tipo identificacion.
	 *
	 * @return the tipoIdentificacion
	 */
	public String getTipoIdentificacion() {
		return tipoIdentificacion;
	}

	/**
	 * Sets the tipo identificacion.
	 *
	 * @param tipoIdentificacion
	 *            the tipoIdentificacion to set
	 */
	public void setTipoIdentificacion(String tipoIdentificacion) {
		this.tipoIdentificacion = tipoIdentificacion;
	}

	/**
	 * Gets the identificacion cliente.
	 *
	 * @return the identificacionCliente
	 */
	public String getIdentificacionCliente() {
		return identificacionCliente;
	}

	/**
	 * Sets the identificacion cliente.
	 *
	 * @param identificacionCliente
	 *            the identificacionCliente to set
	 */
	public void setIdentificacionCliente(String identificacionCliente) {
		this.identificacionCliente = identificacionCliente;
	}

	/**
	 * Gets the reasigna.
	 *
	 * @return the reasigna
	 */
	public String getReasigna() {
		return reasigna;
	}

	/**
	 * Sets the reasigna.
	 *
	 * @param reasigna
	 *            the reasigna to set
	 */
	public void setReasigna(String reasigna) {
		this.reasigna = reasigna;
	}

}