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
public class PrestamoSueldosConfirmacionView {

	/** The email. */
	@JsonSerialize(include = Inclusion.NON_NULL)
	private String email;
	
	/** The Idcuentas pesos. */
	@JsonSerialize(include = Inclusion.NON_NULL)
	private String IdcuentasPesos;
	

	/** The monto prestamo seleccion. */
	@JsonSerialize(include = Inclusion.NON_NULL)
	private String montoPrestamoSeleccion;
	
	/** The adjunta documentacion. */
	@JsonSerialize(include = Inclusion.NON_NULL)
	private String adjuntaDocumentacion;


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
	 * Gets the monto prestamo seleccion.
	 *
	 * @return the monto prestamo seleccion
	 */
	public String getMontoPrestamoSeleccion() {
		return montoPrestamoSeleccion;
	}


	/**
	 * Sets the monto prestamo seleccion.
	 *
	 * @param montoPrestamoSeleccion the new monto prestamo seleccion
	 */
	public void setMontoPrestamoSeleccion(String montoPrestamoSeleccion) {
		this.montoPrestamoSeleccion = montoPrestamoSeleccion;
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
