/**
 * 
 */
package ar.com.santanderrio.obp.servicios.sieteporveinticuatro.entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Response para el servicio CNSAGENDA.
 * 
 * @author Manuel.Vargas B041299
 *
 */

@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CNSAGENDA")
public class V1SietePorVeintiCuatroResponse {

	/** The cod de retorno. */
	@XmlElement(required = true)
	protected String codRet;

	/** The severidad. */
	@XmlElement(required = true)
	protected String severidad;

	/** The id cantidad de registros. */
	@XmlElement(required = true)
	protected String cantidadRegistro;

	//

	/**
	 * Gets the cod ret.
	 *
	 * @return the codRet
	 */
	public String getCodRet() {
		return codRet;
	}

	/**
	 * Sets the cod ret.
	 *
	 * @param codRet
	 *            the codRet to set
	 */
	public void setCodRet(String codRet) {
		this.codRet = codRet;
	}

	/**
	 * Gets the severidad.
	 *
	 * @return the severidad
	 */
	public String getSeveridad() {
		return severidad;
	}

	/**
	 * Sets the severidad.
	 *
	 * @param severidad
	 *            the severidad to set
	 */
	public void setSeveridad(String severidad) {
		this.severidad = severidad;
	}

	/**
	 * Gets the cantidad registro.
	 *
	 * @return the cantidadRegistro
	 */
	public String getCantidadRegistro() {
		return cantidadRegistro;
	}

	/**
	 * Sets the cantidad registro.
	 *
	 * @param cantidadRegistro
	 *            the cantidadRegistro to set
	 */
	public void setCantidadRegistro(String cantidadRegistro) {
		this.cantidadRegistro = cantidadRegistro;
	}
}
