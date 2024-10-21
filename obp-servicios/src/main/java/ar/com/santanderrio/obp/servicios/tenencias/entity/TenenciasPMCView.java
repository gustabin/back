/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tenencias.entity;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonManagedReference;

import com.auth0.jwt.internal.com.fasterxml.jackson.annotation.JsonIgnore;

import ar.com.santanderrio.obp.base.comun.OperacionesRSAEnum;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.RsaDTO;
import ar.com.santanderrio.obp.servicios.token.externos.view.TokenView;

/**
 * The Class TenenciasPMCView.
 */
public class TenenciasPMCView extends RsaDTO implements Serializable {
	
    /** The Serial Id. */
    private static final long serialVersionUID = 1L;
    /** The desafio. */
    @JsonIgnore
    @JsonManagedReference
    private AutentificacionDTO desafio;
    
    /** Se usa para RSA. */
    private boolean celularMyA = false;
    
    /** The tokenView. */
    private TokenView tokenView;
    
    /** The url volver. */
    private String urlVolver;
    
    /** The url cancelar. */
    private String urlCancelar;
    
    /** The url error operacion. */
    private String urlErrorOperacion;
    
    /** The url error. */
    private String urlError;
    
    /** The url. */
    private String url;
    
    /** The mensaje. */
    private String mensaje;
       
	/**
	 * Instantiates a new tenencias PMC view.
	 */
	public TenenciasPMCView() {
		super(OperacionesRSAEnum.TENENCIAS);
	}

	/**
	 * Gets the desafio.
	 *
	 * @return the desafio
	 */
	public AutentificacionDTO getDesafio() {
		return desafio;
	}

	/**
	 * Sets the desafio.
	 *
	 * @param desafio
	 *            the new desafio
	 */
	public void setDesafio(AutentificacionDTO desafio) {
		this.desafio = desafio;
	}

	/**
	 * Checks if is celular my A.
	 *
	 * @return true, if is celular my A
	 */
	public boolean isCelularMyA() {
		return celularMyA;
	}

	/**
	 * Sets the celular my A.
	 *
	 * @param celularMyA
	 *            the new celular my A
	 */
	public void setCelularMyA(boolean celularMyA) {
		this.celularMyA = celularMyA;
	}

	/**
	 * Gets the token view.
	 *
	 * @return the token view
	 */
	public TokenView getTokenView() {
		return tokenView;
	}

	/**
	 * Sets the token view.
	 *
	 * @param tokenView
	 *            the new token view
	 */
	public void setTokenView(TokenView tokenView) {
		this.tokenView = tokenView;
	}

	/**
	 * Gets the url volver.
	 *
	 * @return the url volver
	 */
	public String getUrlVolver() {
		return urlVolver;
	}

	/**
	 * Sets the url volver.
	 *
	 * @param urlVolver
	 *            the new url volver
	 */
	public void setUrlVolver(String urlVolver) {
		this.urlVolver = urlVolver;
	}

	/**
	 * Gets the url cancelar.
	 *
	 * @return the url cancelar
	 */
	public String getUrlCancelar() {
		return urlCancelar;
	}

	/**
	 * Sets the url cancelar.
	 *
	 * @param urlCancelar
	 *            the new url cancelar
	 */
	public void setUrlCancelar(String urlCancelar) {
		this.urlCancelar = urlCancelar;
	}

	/**
	 * Gets the url error operacion.
	 *
	 * @return the url error operacion
	 */
	public String getUrlErrorOperacion() {
		return urlErrorOperacion;
	}

	/**
	 * Sets the url error operacion.
	 *
	 * @param urlErrorOperacion
	 *            the new url error operacion
	 */
	public void setUrlErrorOperacion(String urlErrorOperacion) {
		this.urlErrorOperacion = urlErrorOperacion;
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
	 * Gets the url error.
	 *
	 * @return the url error
	 */
	public String getUrlError() {
		return urlError;
	}

	/**
	 * Sets the url error.
	 *
	 * @param urlError
	 *            the new url error
	 */
	public void setUrlError(String urlError) {
		this.urlError = urlError;
	}

	/**
	 * Gets the url.
	 *
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * Sets the url.
	 *
	 * @param url
	 *            the new url
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	
}
