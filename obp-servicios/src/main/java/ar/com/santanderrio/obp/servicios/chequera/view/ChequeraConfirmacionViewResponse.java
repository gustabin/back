/*
 * 
 */
package ar.com.santanderrio.obp.servicios.chequera.view;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The Class ChequeraConfirmacionViewResponse.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ChequeraConfirmacionViewResponse {

	/** The fecha hora. */
	private String fechaHora;

	/** The moneda. */
	private String moneda;

	/** The sucursal entrega. */
	private String sucursalEntrega;

	/** The domicilio sucursal. */
	private String domicilioSucursal;

	/** The localidad sucursal. */
	private String localidadSucursal;

	/** The mensaje. */
	private String mensaje;

	/** The chequeras. */
	private List<ChequeraConfirmacionView> chequeras;

	/**
	 * Gets the fecha hora.
	 *
	 * @return the fecha hora
	 */
	public String getFechaHora() {
		return fechaHora;
	}

	/**
	 * Sets the fecha hora.
	 *
	 * @param fechaHora
	 *            the new fecha hora
	 */
	public void setFechaHora(String fechaHora) {
		this.fechaHora = fechaHora;
	}

	/**
	 * Gets the moneda.
	 *
	 * @return the moneda
	 */
	public String getMoneda() {
		return moneda;
	}

	/**
	 * Sets the moneda.
	 *
	 * @param moneda
	 *            the new moneda
	 */
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	/**
	 * Gets the sucursal entrega.
	 *
	 * @return the sucursal entrega
	 */
	public String getSucursalEntrega() {
		return sucursalEntrega;
	}

	/**
	 * Sets the sucursal entrega.
	 *
	 * @param sucursalEntrega
	 *            the new sucursal entrega
	 */
	public void setSucursalEntrega(String sucursalEntrega) {
		this.sucursalEntrega = sucursalEntrega;
	}

	/**
	 * Gets the domicilio sucursal.
	 *
	 * @return the domicilio sucursal
	 */
	public String getDomicilioSucursal() {
		return domicilioSucursal;
	}

	/**
	 * Sets the domicilio sucursal.
	 *
	 * @param domicilioSucursal
	 *            the new domicilio sucursal
	 */
	public void setDomicilioSucursal(String domicilioSucursal) {
		this.domicilioSucursal = domicilioSucursal;
	}

	/**
	 * Gets the localidad sucursal.
	 *
	 * @return the localidad sucursal
	 */
	public String getLocalidadSucursal() {
		return localidadSucursal;
	}

	/**
	 * Sets the localidad sucursal.
	 *
	 * @param localidadSucursal
	 *            the new localidad sucursal
	 */
	public void setLocalidadSucursal(String localidadSucursal) {
		this.localidadSucursal = localidadSucursal;
	}

	/**
	 * Gets the mensaje.
	 *
	 * @return the mensaje
	 */
	public String getMensaje() {
		return mensaje;
	}

	/**
	 * Sets the mensaje.
	 *
	 * @param mensaje
	 *            the new mensaje
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	/**
	 * Gets the chequeras.
	 *
	 * @return the chequeras
	 */
	public List<ChequeraConfirmacionView> getChequeras() {
		return chequeras;
	}

	/**
	 * Sets the chequeras.
	 *
	 * @param chequeras
	 *            the new chequeras
	 */
	public void setChequeras(List<ChequeraConfirmacionView> chequeras) {
		this.chequeras = chequeras;
	}
}