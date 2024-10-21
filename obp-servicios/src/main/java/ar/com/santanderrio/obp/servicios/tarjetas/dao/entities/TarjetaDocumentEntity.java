/**
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.dao.entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * The Class TarjetaDocumentEntity. Clase que representa el tag
 * /tarjetas/tarjeta/document/
 *
 * @author sergio.e.goldentair
 */
@XmlRootElement(name = "document")
@XmlAccessorType(XmlAccessType.FIELD)
public class TarjetaDocumentEntity {

	/** The String sessionID. */
	@XmlAttribute(name = "sessionID")
	private String sessionID;

	/** The DatosEntity datos. */
	@XmlElement(name = "datos")
	private DatosEntity datos;

	/** The DatosCuentaEntity datosCuenta. */
	@XmlElement(name = "datosCuenta")
	private DatosCuentaEntity datosCuenta;

	/** The SaldoEnCuentaEntity saldoEnCuenta. */
	@XmlElement(name = "saldoenCuenta")
	private SaldoEnCuentaEntity saldoEnCuenta;

	/** The UltimosConsumos ultimosMovimientos. */
	@XmlElement(name = "movimientos")
	private UltimosConsumosEntity ultimosMovimientos;

	/** The Class AutorizacionesEntity. */
	@XmlElement(name = "autorizaciones")
	private AutorizacionesEntity autorizaciones;

	/** The cuotas pendientes. */
	@XmlElement(name = "CuotasPendientes")
	private CuotasPendientesEntity cuotasPendientes;

	/**
	 * Gets the datos.
	 *
	 * @return the datos
	 */
	public DatosEntity getDatos() {
		return datos;
	}

	/**
	 * Sets the datos.
	 *
	 * @param datos
	 *            the datos to set
	 */
	public void setDatos(DatosEntity datos) {
		this.datos = datos;
	}

	/**
	 * Gets the datosCuenta.
	 *
	 * @return the datosCuenta
	 */
	public DatosCuentaEntity getDatosCuenta() {
		return datosCuenta;
	}

	/**
	 * Sets the datosCuenta.
	 *
	 * @param datosCuenta
	 *            the datosCuenta to set
	 */
	public void setDatosCuenta(DatosCuentaEntity datosCuenta) {
		this.datosCuenta = datosCuenta;
	}

	/**
	 * Gets the session ID.
	 *
	 * @return the sessionID
	 */
	public String getSessionID() {
		return sessionID;
	}

	/**
	 * Sets the session ID.
	 *
	 * @param sessionID
	 *            the sessionID to set
	 */
	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}

	/**
	 * Gets the saldo en cuenta.
	 *
	 * @return the saldoEnCuenta
	 */
	public SaldoEnCuentaEntity getSaldoEnCuenta() {
		return saldoEnCuenta;
	}

	/**
	 * Sets the saldo en cuenta.
	 *
	 * @param saldoEnCuenta
	 *            the saldoEnCuenta to set
	 */
	public void setSaldoEnCuenta(SaldoEnCuentaEntity saldoEnCuenta) {
		this.saldoEnCuenta = saldoEnCuenta;
	}

	/**
	 * Getter de ultimosMovimientos.
	 * 
	 * @return the ultimosMovimientos
	 */
	public UltimosConsumosEntity getUltimosMovimientos() {
		return ultimosMovimientos;
	}

	/**
	 * Setter de ultimosMovimientos.
	 * 
	 * @param ultimosMovimientos
	 *            the ultimosMovimientos to set
	 */
	public void setUltimosMovimientos(UltimosConsumosEntity ultimosMovimientos) {
		this.ultimosMovimientos = ultimosMovimientos;
	}

	/**
	 * Gets the autorizaciones.
	 *
	 * @return the autorizaciones
	 */
	public AutorizacionesEntity getAutorizaciones() {
		return autorizaciones;
	}

	/**
	 * Sets the autorizaciones.
	 *
	 * @param autorizaciones
	 *            the new autorizaciones
	 */
	public void setAutorizaciones(AutorizacionesEntity autorizaciones) {
		this.autorizaciones = autorizaciones;
	}

	/**
	 * Gets the cuotas pendientes.
	 *
	 * @return the cuotas pendientes
	 */
	public CuotasPendientesEntity getCuotasPendientes() {
		return cuotasPendientes;
	}

	/**
	 * Sets the cuotas pendientes.
	 *
	 * @param cuotasPendientes
	 *            the new cuotas pendientes
	 */
	public void setCuotasPendientes(CuotasPendientesEntity cuotasPendientes) {
		this.cuotasPendientes = cuotasPendientes;
	}

	/**
	 * HashCode.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(sessionID);
		hcb.append(datos);
		hcb.append(saldoEnCuenta);
		hcb.append(ultimosMovimientos);
		hcb.append(autorizaciones);
		hcb.append(cuotasPendientes);
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
		TarjetaDocumentEntity other = (TarjetaDocumentEntity) obj;

		EqualsBuilder eb = new EqualsBuilder();
		eb.append(sessionID, other.getSessionID());
		eb.append(datos, other.getDatos());
		eb.append(saldoEnCuenta, other.getSaldoEnCuenta());
		eb.append(ultimosMovimientos, other.getUltimosMovimientos());
		eb.append(autorizaciones, other.getAutorizaciones());
		eb.append(cuotasPendientes, other.getCuotasPendientes());

		return eb.isEquals();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TarjetaDocumentEntity [sessionID=" + sessionID + ", datos=" + datos + ", saldoEnCuenta=" + saldoEnCuenta + ", datosCuenta=" + datosCuenta
				+ ", ultimosMovimientos=" + ultimosMovimientos + ", autorizaciones=" + autorizaciones
				+ ", cuotasPendientes=" + cuotasPendientes + "]";
	}
}
