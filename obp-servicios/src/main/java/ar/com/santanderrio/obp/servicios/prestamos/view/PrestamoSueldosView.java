/*
 * 
 */
package ar.com.santanderrio.obp.servicios.prestamos.view;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;

// TODO: Auto-generated Javadoc
/**
 * The Class PrestamosView.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class PrestamoSueldosView {

	/** The email. */
	@JsonSerialize(include = Inclusion.NON_NULL)
	private String email;
	
	/** The cuentas pesos. 
	@JsonSerialize(include = Inclusion.NON_NULL)
	private List<Cuenta> cuentasPesos;*/
	
	@JsonSerialize(include = Inclusion.NON_NULL)
	List<CuentaView> cuentasPesos;
	
	/** The prestamo. */
	@JsonSerialize(include = Inclusion.NON_NULL)
	private String montoPrestamo;
	
	/** The solicitado. */
	@JsonSerialize(include = Inclusion.NON_NULL)
	private String solicitado;
	
	/** The legal prestamo sueldos. */
	@JsonSerialize(include = Inclusion.NON_NULL)
	private String legalPrestamoSueldos;
	
	/** The legal prestamo sueldos. */
	@JsonSerialize(include = Inclusion.NON_NULL)
	private String nroComprobante;
	
	/** The Idcuentas pesos. */
	@JsonSerialize(include = Inclusion.NON_NULL)
	private String IdcuentasPesos;

	/** The Idcuentas pesos. */
	@JsonSerialize(include = Inclusion.NON_NULL)
	private String adjuntaDocumentacion;
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
	 * @param montoPrestamo the new monto prestamo
	 */
	public void setMontoPrestamo(String montoPrestamo) {
		this.montoPrestamo = montoPrestamo;
	}

	/**
	 * Gets the legal prestamo sueldos.
	 *
	 * @return the legal prestamo sueldos
	 */
	public String getLegalPrestamoSueldos() {
		return legalPrestamoSueldos;
	}

	/**
	 * Sets the legal prestamo sueldos.
	 *
	 * @param legalPrestamoSueldos the new legal prestamo sueldos
	 */
	public void setLegalPrestamoSueldos(String legalPrestamoSueldos) {
		this.legalPrestamoSueldos = legalPrestamoSueldos;
	}



	/**
	 * Gets the cuentas pesos.
	 *
	 * @return the cuentas pesos
	 */
	public List<CuentaView> getCuentasPesos() {
		return cuentasPesos;
	}

	/**
	 * Sets the cuentas pesos.
	 *
	 * @param cuentasPesos the new cuentas pesos
	 */
	public void setCuentasPesos(List<CuentaView> cuentasPesos) {
		this.cuentasPesos = cuentasPesos;
	}

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email.
	 *
	 * @param email the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the solicitado.
	 *
	 * @return the solicitado
	 */
	public String getSolicitado() {
		return solicitado;
	}

	/**
	 * Sets the solicitado.
	 *
	 * @param solicitado the new solicitado
	 */
	public void setSolicitado(String solicitado) {
		this.solicitado = solicitado;
	}

	/**
	 * Gets the nro comprobante.
	 *
	 * @return the nro comprobante
	 */
	public String getNroComprobante() {
		return nroComprobante;
	}

	/**
	 * Sets the nro comprobante.
	 *
	 * @param nroComprobante the new nro comprobante
	 */
	public void setNroComprobante(String nroComprobante) {
		this.nroComprobante = nroComprobante;
	}

	/**
	 * Gets the idcuentas pesos.
	 *
	 * @return the idcuentas pesos
	 */
	public String getIdcuentasPesos() {
		return IdcuentasPesos;
	}

	/**
	 * Sets the idcuentas pesos.
	 *
	 * @param idcuentasPesos the new idcuentas pesos
	 */
	public void setIdcuentasPesos(String idcuentasPesos) {
		IdcuentasPesos = idcuentasPesos;
	}

	/**
	 * Gets the adjunta documentacion.
	 *
	 * @return the adjunta documentacion
	 */
	public String getAdjuntaDocumentacion() {
		return adjuntaDocumentacion;
	}

	/**
	 * Sets the adjunta documentacion.
	 *
	 * @param adjuntaDocumentacion the new adjunta documentacion
	 */
	public void setAdjuntaDocumentacion(String adjuntaDocumentacion) {
		this.adjuntaDocumentacion = adjuntaDocumentacion;
	}				
	
}
