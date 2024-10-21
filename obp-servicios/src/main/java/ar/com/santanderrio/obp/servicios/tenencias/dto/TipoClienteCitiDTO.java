/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tenencias.dto;


/**
 * Objeto utilizado junto a Respuesta<T> desde el BO al Manager.
 * 
 * @author desa
 *
 */
public class TipoClienteCitiDTO {

	/** The tipCliente. */
	private String tipCliente;
	/** The codResultado. */
	private String codResultado;
	/** The descErrorTecnico. */
	private String descErrorTecnico;
	/** The descErrorAmigable. */
	private String descErrorAmigable;

	

	/**
	 * Gets the cod resultado.
	 *
	 * @return the cod resultado
	 */
	public String getCodResultado() {
		return codResultado;
	}

	/**
	 * Sets the cod resultado.
	 *
	 * @param codResultado
	 *            the new cod resultado
	 */
	public void setCodResultado(String codResultado) {
		this.codResultado = codResultado;
	}

	/**
	 * Gets the desc error tecnico.
	 *
	 * @return the desc error tecnico
	 */
	public String getDescErrorTecnico() {
		return descErrorTecnico;
	}

	/**
	 * Sets the desc error tecnico.
	 *
	 * @param descErrorTecnico
	 *            the new desc error tecnico
	 */
	public void setDescErrorTecnico(String descErrorTecnico) {
		this.descErrorTecnico = descErrorTecnico;
	}

	/**
	 * Gets the desc error amigable.
	 *
	 * @return the desc error amigable
	 */
	public String getDescErrorAmigable() {
		return descErrorAmigable;
	}

	/**
	 * Sets the desc error amigable.
	 *
	 * @param descErrorAmigable
	 *            the new desc error amigable
	 */
	public void setDescErrorAmigable(String descErrorAmigable) {
		this.descErrorAmigable = descErrorAmigable;
	}

	/**
	 * Gets the tip cliente.
	 *
	 * @return the tip cliente
	 */
	public String getTipCliente() {
		return tipCliente;
	}

	/**
	 * Sets the tip cliente.
	 *
	 * @param tipCliente
	 *            the new tip cliente
	 */
	public void setTipCliente(String tipCliente) {
		this.tipCliente = tipCliente;
	}

	
}
