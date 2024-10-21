/**
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.view;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * Objeto utilizado para el Input del FondoSEI.
 * 
 * @author
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties
public class CuentasConsultaFondoView {

	/** The tipo banca. */
	@NotNull
	private String tipoBanca;

	/** The cuentas titulo. */
	@NotNull
	private List<CuentaTituloView> cuentasTitulo;

	/** The fondos totales. */
	private List<FondoResumidoView> fondosTotales;

	/** The mensaje informacion. */
	private String mensajeInformacion;

	/** The texto buscador. */
	private String textoBuscador;
	
	
	private boolean fondoSeleccionado;
	
	/**
	 * @return the fondoSeleccionado
	 */
	public boolean isFondoSeleccionado() {
		return fondoSeleccionado;
	}

	/**
	 * @param fondoSeleccionado the fondoSeleccionado to set
	 */
	public void setFondoSeleccionado(boolean fondoSeleccionado) {
		this.fondoSeleccionado = fondoSeleccionado;
	}

	/**
	 * Gets the tipo banca.
	 *
	 * @return the tipo banca
	 */
	public String getTipoBanca() {
		return tipoBanca;
	}

	/**
	 * Sets the tipo banca.
	 *
	 * @param tipoBanca
	 *            the new tipo banca
	 */
	public void setTipoBanca(String tipoBanca) {
		this.tipoBanca = tipoBanca;
	}

	/**
	 * Gets the cuentas titulo.
	 *
	 * @return the cuentas titulo
	 */
	public List<CuentaTituloView> getCuentasTitulo() {
		return cuentasTitulo;
	}

	/**
	 * Sets the cuentas titulo.
	 *
	 * @param cuentasTitulo
	 *            the new cuentas titulo
	 */
	public void setCuentasTitulo(List<CuentaTituloView> cuentasTitulo) {
		this.cuentasTitulo = cuentasTitulo;
	}

	/**
	 * Gets the fondos totales.
	 *
	 * @return the fondos totales
	 */
	public List<FondoResumidoView> getFondosTotales() {
		return fondosTotales;
	}

	/**
	 * Sets the fondos totales.
	 *
	 * @param fondosTotales
	 *            the new fondos totales
	 */
	public void setFondosTotales(List<FondoResumidoView> fondosTotales) {
		this.fondosTotales = fondosTotales;
	}

	/**
	 * Gets the mensaje informacion.
	 *
	 * @return the mensajeInformacion
	 */
	public String getMensajeInformacion() {
		return mensajeInformacion;
	}

	/**
	 * Sets the mensaje informacion.
	 *
	 * @param mensajeInformacion
	 *            the mensajeInformacion to set
	 */
	public void setMensajeInformacion(String mensajeInformacion) {
		this.mensajeInformacion = mensajeInformacion;
	}

	/**
	 * Gets the texto buscador.
	 *
	 * @return the textoBuscador
	 */
	public String getTextoBuscador() {
		return textoBuscador;
	}

	/**
	 * Sets the texto buscador.
	 *
	 * @param textoBuscador
	 *            the textoBuscador to set
	 */
	public void setTextoBuscador(String textoBuscador) {
		this.textoBuscador = textoBuscador;
	}

}
