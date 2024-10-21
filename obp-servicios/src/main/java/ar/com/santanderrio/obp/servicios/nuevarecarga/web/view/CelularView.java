package ar.com.santanderrio.obp.servicios.nuevarecarga.web.view;

/**
 * The Class CelularView.
 */
public class CelularView {

	/** The numero. */
	private String numero;
	
	/** The alias. */
	private String alias;
	
	/** The compania. */
	private String compania;
	
	private String nombreFantasia;
	
	private String fiid;
	
	public CelularView (String numero, String alias, String compania) {
		this.numero = numero;
		this.alias = alias;
		this.compania = compania;
	}
	
	public CelularView() {
		super();
	}
	
	/**
	 * Gets the numero.
	 *
	 * @return the numero
	 */
	public String getNumero() {
		return numero;
	}
	
	/**
	 * Sets the numero.
	 *
	 * @param numero the new numero
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	/**
	 * Gets the alias.
	 *
	 * @return the alias
	 */
	public String getAlias() {
		return alias;
	}
	
	/**
	 * Sets the alias.
	 *
	 * @param denominacion the new alias
	 */
	public void setAlias(String denominacion) {
		this.alias = denominacion;
	}
	
	/**
	 * Gets the compania.
	 *
	 * @return the compania
	 */
	public String getCompania() {
		return compania;
	}
	
	/**
	 * Sets the compania.
	 *
	 * @param compania the new compania
	 */
	public void setCompania(String compania) {
		this.compania = compania;
	}

	public String getNombreFantasia() {
		return nombreFantasia;
	}

	public void setNombreFantasia(String nombreFantasia) {
		this.nombreFantasia = nombreFantasia;
	}

	public String getFiid() {
		return fiid;
	}

	public void setFiid(String fiid) {
		this.fiid = fiid;
	}
	
}
