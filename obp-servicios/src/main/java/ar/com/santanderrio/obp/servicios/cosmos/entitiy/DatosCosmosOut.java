package ar.com.santanderrio.obp.servicios.cosmos.entitiy;

import java.io.Serializable;

/**
 * The Class DatosCosmosOut.
 */
public class DatosCosmosOut implements Serializable{

	private static final long serialVersionUID = -7624317628528000121L;

	/** The token hash. */
	public String tokenHash;
	
	public String mensajeError;
	
	public String codError;
	
	public String mensajeErrorCliente;
	
	

	/**
	 * Gets the token hash.
	 *
	 * @return the token hash
	 */
	public String getTokenHash() {
		return tokenHash;
	}

	/**
	 * Sets the token hash.
	 *
	 * @param tokenHash the new token hash
	 */
	public void setTokenHash(String tokenHash) {
		this.tokenHash = tokenHash;
	}

	public String getMensajeError() {
		return mensajeError;
	}

	public void setMensajeError(String mensajeError) {
		this.mensajeError = mensajeError;
	}

	public String getCodError() {
		return codError;
	}

	public void setCodError(String codError) {
		this.codError = codError;
	}

	public String getMensajeErrorCliente() {
		return mensajeErrorCliente;
	}

	public void setMensajeErrorCliente(String mensajeErrorCliente) {
		this.mensajeErrorCliente = mensajeErrorCliente;
	}
	
}
