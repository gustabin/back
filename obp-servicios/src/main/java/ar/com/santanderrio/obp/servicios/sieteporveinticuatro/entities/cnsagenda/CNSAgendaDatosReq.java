/**
 * 
 */
package ar.com.santanderrio.obp.servicios.sieteporveinticuatro.entities.cnsagenda;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import ar.com.santanderrio.obp.servicios.sieteporveinticuatro.entities.SietePorVeintiCuatroDatosReq;

/**
 * The Class CNSAgendaDatosReq.
 *
 * @author sergio.e.goldentair
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class CNSAgendaDatosReq extends SietePorVeintiCuatroDatosReq {

	/** The nup. */
	@XmlElement(name = "NUP")
	private String nup;

	/** The tx nombre. */
	@XmlElement
	private String txNombre;

	/** The tx version. */
	@XmlElement
	private String txVersion;

	/** The fecha ejec desde. */
	@XmlElement
	private String fechaEjecDesde;

	/** The fecha ejec hasta. */
	@XmlElement
	private String fechaEjecHasta;

	/** The estado. */
	@XmlElement
	private String estado;

	/**
	 * Gets the nup.
	 *
	 * @return the nup
	 */
	public String getNup() {
		return nup;
	}

	/**
	 * Sets the nup.
	 *
	 * @param nup
	 *            the nup to set
	 */
	public void setNup(String nup) {
		this.nup = nup;
	}

	/**
	 * Gets the tx nombre.
	 *
	 * @return the txNombre
	 */
	public String getTxNombre() {
		return txNombre;
	}

	/**
	 * Sets the tx nombre.
	 *
	 * @param txNombre
	 *            the txNombre to set
	 */
	public void setTxNombre(String txNombre) {
		this.txNombre = txNombre;
	}

	/**
	 * Gets the tx version.
	 *
	 * @return the txVersion
	 */
	public String getTxVersion() {
		return txVersion;
	}

	/**
	 * Sets the tx version.
	 *
	 * @param txVersion
	 *            the txVersion to set
	 */
	public void setTxVersion(String txVersion) {
		this.txVersion = txVersion;
	}

	/**
	 * Gets the fecha ejec desde.
	 *
	 * @return the fechaEjecDesde
	 */
	public String getFechaEjecDesde() {
		return fechaEjecDesde;
	}

	/**
	 * Sets the fecha ejec desde.
	 *
	 * @param fechaEjecDesde
	 *            the fechaEjecDesde to set
	 */
	public void setFechaEjecDesde(String fechaEjecDesde) {
		this.fechaEjecDesde = fechaEjecDesde;
	}

	/**
	 * Gets the fecha ejec hasta.
	 *
	 * @return the fechaEjecHasta
	 */
	public String getFechaEjecHasta() {
		return fechaEjecHasta;
	}

	/**
	 * Sets the fecha ejec hasta.
	 *
	 * @param fechaEjecHasta
	 *            the fechaEjecHasta to set
	 */
	public void setFechaEjecHasta(String fechaEjecHasta) {
		this.fechaEjecHasta = fechaEjecHasta;
	}

	/**
	 * Gets the estado.
	 *
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * Sets the estado.
	 *
	 * @param estado
	 *            the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

}
