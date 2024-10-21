/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.entities;

import java.util.Date;

import ar.com.santanderrio.obp.base.entities.Entity;

/**
 * The Class TarjetaRecargableEntity.
 */
public class TarjetaRecargableEntity extends Entity {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -8118583168188802743L;

	/** Tipo_Tarjeta. */
	private String tipoTarjeta;

	/** Nro_Cuenta_Tarjeta. */
	private String nroCuentaTarjeta;

	/** Tipo_Cuenta_Débito. */
	private String tipoCuentaDebito;

	/** Nro_Cuenta_Débito. */
	private String nroCuentaDebito;

	/** Nro_Cuenta_Débito. */
	private String sucursalCuentaDebito;

	/** Nro_Firmante. */
	private String nroFirmante;

	/** Forma_Pago. */
	private String formaPago;

	/** Importe Agtendamiento. */
	private String importeAgendamiento;

	/** Fecha proximo agendamiento. */
	private Date fechaProximoAgendamiento;

	/**
	 * Gets the tipo tarjeta.
	 *
	 * @return the tipo tarjeta
	 */
	public String getTipoTarjeta() {
		return tipoTarjeta;
	}

	/**
	 * Sets the tipo tarjeta.
	 *
	 * @param tipoTarjeta
	 *            the new tipo tarjeta
	 */
	public void setTipoTarjeta(String tipoTarjeta) {
		this.tipoTarjeta = tipoTarjeta;
	}

	/**
	 * Gets the nro cuenta tarjeta.
	 *
	 * @return the nro cuenta tarjeta
	 */
	public String getNroCuentaTarjeta() {
		return nroCuentaTarjeta;
	}

	/**
	 * Sets the nro cuenta tarjeta.
	 *
	 * @param nroCuentaTarjeta
	 *            the new nro cuenta tarjeta
	 */
	public void setNroCuentaTarjeta(String nroCuentaTarjeta) {
		this.nroCuentaTarjeta = nroCuentaTarjeta;
	}

	/**
	 * Gets the tipo cuenta debito.
	 *
	 * @return the tipo cuenta debito
	 */
	public String getTipoCuentaDebito() {
		return tipoCuentaDebito;
	}

	/**
	 * Sets the tipo cuenta debito.
	 *
	 * @param tipoCuentaDebito
	 *            the new tipo cuenta debito
	 */
	public void setTipoCuentaDebito(String tipoCuentaDebito) {
		this.tipoCuentaDebito = tipoCuentaDebito;
	}

	/**
	 * Gets the nro cuenta debito.
	 *
	 * @return the nro cuenta debito
	 */
	public String getNroCuentaDebito() {
		return nroCuentaDebito;
	}

	/**
	 * Sets the nro cuenta debito.
	 *
	 * @param nroCuentaDebito
	 *            the new nro cuenta debito
	 */
	public void setNroCuentaDebito(String nroCuentaDebito) {
		this.nroCuentaDebito = nroCuentaDebito;
	}

	/**
	 * Gets the nro firmante.
	 *
	 * @return the nro firmante
	 */
	public String getNroFirmante() {
		return nroFirmante;
	}

	/**
	 * Sets the nro firmante.
	 *
	 * @param nroFirmante
	 *            the new nro firmante
	 */
	public void setNroFirmante(String nroFirmante) {
		this.nroFirmante = nroFirmante;
	}

	/**
	 * Gets the forma pago.
	 *
	 * @return the forma pago
	 */
	public String getFormaPago() {
		return formaPago;
	}

	/**
	 * Sets the forma pago.
	 *
	 * @param formaPago
	 *            the new forma pago
	 */
	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}

	/**
	 * Gets the importe agendamiento.
	 *
	 * @return the importe agendamiento
	 */
	public String getImporteAgendamiento() {
		return importeAgendamiento;
	}

	/**
	 * Sets the importe agendamiento.
	 *
	 * @param importeAgendamiento
	 *            the new importe agendamiento
	 */
	public void setImporteAgendamiento(String importeAgendamiento) {
		this.importeAgendamiento = importeAgendamiento;
	}

	/**
	 * Gets the fecha proximo agendamiento.
	 *
	 * @return the fecha proximo agendamiento
	 */
	public Date getFechaProximoAgendamiento() {
		return fechaProximoAgendamiento;
	}

	/**
	 * Sets the fecha proximo agendamiento.
	 *
	 * @param fechaProximoAgendamiento
	 *            the new fecha proximo agendamiento
	 */
	public void setFechaProximoAgendamiento(Date fechaProximoAgendamiento) {
		this.fechaProximoAgendamiento = fechaProximoAgendamiento;
	}

	/**
	 * Gets the sucursal cuenta debito.
	 *
	 * @return the sucursal cuenta debito
	 */
	public String getSucursalCuentaDebito() {
		return sucursalCuentaDebito;
	}

	/**
	 * Sets the sucursal cuenta debito.
	 *
	 * @param sucursalCuentaDebito
	 *            the new sucursal cuenta debito
	 */
	public void setSucursalCuentaDebito(String sucursalCuentaDebito) {
		this.sucursalCuentaDebito = sucursalCuentaDebito;
	}

}
