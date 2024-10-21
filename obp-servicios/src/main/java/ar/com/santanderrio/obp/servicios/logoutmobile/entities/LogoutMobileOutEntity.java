/**
 * 
 */
package ar.com.santanderrio.obp.servicios.logoutmobile.entities;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * @author sergio.e.goldentair
 *
 */
public class LogoutMobileOutEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8510262734255315599L;
	@JsonProperty("res")
	private String respuesta;
	@JsonProperty("msjCod")
	private String msjCodigo;
	@JsonProperty("msjTitulo")
	private String msjTitulo;
	@JsonProperty("msjDesc")
	private String msjDescripcion;

	/**
	 * 
	 */
	public LogoutMobileOutEntity() {
		super();
	}

	/**
	 * @return the respuesta
	 */
	public String getRespuesta() {
		return respuesta;
	}

	/**
	 * @param respuesta the respuesta to set
	 */
	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}

	/**
	 * @return the msjCodigo
	 */
	public String getMsjCodigo() {
		return msjCodigo;
	}

	/**
	 * @param msjCodigo the msjCodigo to set
	 */
	public void setMsjCodigo(String msjCodigo) {
		this.msjCodigo = msjCodigo;
	}

	/**
	 * @return the msjTitulo
	 */
	public String getMsjTitulo() {
		return msjTitulo;
	}

	/**
	 * @param msjTitulo the msjTitulo to set
	 */
	public void setMsjTitulo(String msjTitulo) {
		this.msjTitulo = msjTitulo;
	}

	/**
	 * @return the msjDescripcion
	 */
	public String getMsjDescripcion() {
		return msjDescripcion;
	}

	/**
	 * @param msjDescripcion the msjDescripcion to set
	 */
	public void setMsjDescripcion(String msjDescripcion) {
		this.msjDescripcion = msjDescripcion;
	}
}
