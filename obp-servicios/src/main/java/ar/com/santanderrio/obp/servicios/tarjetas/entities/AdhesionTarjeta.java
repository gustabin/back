/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.entities;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import ar.com.santanderrio.obp.base.entities.Entity;

/**
 * The Class AdhesionTarjeta.
 */
public class AdhesionTarjeta extends Entity {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The sucursal cuenta debito. */
	// N3 Sucursal de la cuenta de d�bito
	private String sucursalCuentaDebito;
	// C2 Tipo de la cuenta de d�bito
	/** The tipo cuenta debito. */
	// FIXME hay algun enum para esto?
	private String tipoCuentaDebito;

	/** The nro cuenta debito. */
	// N7 N�mero de la cuenta de d�bito
	private String nroCuentaDebito;

	/** The nro firmante. */
	// N3 N�mero de Firmante Cta. D�bito autom�tico
	private String nroFirmante;

	/** The forma de pago. */
	// Forma_Pago C2
	private FormaDePagoTarjetaEnum formaDePago;

	/** The importe de agendamiento. */
	// Importe de agendamiento N8
	private BigDecimal importeDeAgendamiento;

	/** The fecha de proxima agenda. */
	// Fecha de proxima agenda N8
	private Date fechaDeProximaAgenda;

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
	 * Gets the forma de pago.
	 *
	 * @return the forma de pago
	 */
	public FormaDePagoTarjetaEnum getFormaDePago() {
		return formaDePago;
	}

	/**
	 * Sets the forma de pago.
	 *
	 * @param formaDePago
	 *            the new forma de pago
	 */
	public void setFormaDePago(FormaDePagoTarjetaEnum formaDePago) {
		this.formaDePago = formaDePago;
	}

	/**
	 * Gets the importe de agendamiento.
	 *
	 * @return the importe de agendamiento
	 */
	public BigDecimal getImporteDeAgendamiento() {
		return importeDeAgendamiento;
	}

	/**
	 * Sets the importe de agendamiento.
	 *
	 * @param importeDeAgendamiento
	 *            the new importe de agendamiento
	 */
	public void setImporteDeAgendamiento(BigDecimal importeDeAgendamiento) {
		this.importeDeAgendamiento = importeDeAgendamiento;
	}

	/**
	 * Gets the fecha de proxima agenda.
	 *
	 * @return the fecha de proxima agenda
	 */
	public Date getFechaDeProximaAgenda() {
		return fechaDeProximaAgenda == null ? null : new Date(fechaDeProximaAgenda.getTime());
	}

	/**
	 * Sets the fecha de proxima agenda.
	 *
	 * @param fechaDeProximaAgenda
	 *            the new fecha de proxima agenda
	 */
	public void setFechaDeProximaAgenda(Date fechaDeProximaAgenda) {
		this.fechaDeProximaAgenda = fechaDeProximaAgenda == null ? null : new Date(fechaDeProximaAgenda.getTime());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.base.entities.Entity#hashCode()
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(fechaDeProximaAgenda);
		hcb.append(formaDePago);
		hcb.append(importeDeAgendamiento);
		hcb.append(nroCuentaDebito);
		hcb.append(nroFirmante);
		hcb.append(sucursalCuentaDebito);
		hcb.append(tipoCuentaDebito);
		return hcb.toHashCode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.base.entities.Entity#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		AdhesionTarjeta other = (AdhesionTarjeta) obj;
		return new EqualsBuilder().append(fechaDeProximaAgenda, other.fechaDeProximaAgenda)
				.append(formaDePago, other.formaDePago).append(importeDeAgendamiento, other.importeDeAgendamiento)
				.append(nroCuentaDebito, other.nroCuentaDebito).append(nroFirmante, other.nroFirmante)
				.append(sucursalCuentaDebito, other.sucursalCuentaDebito)
				.append(tipoCuentaDebito, other.tipoCuentaDebito).isEquals();
	}

}
