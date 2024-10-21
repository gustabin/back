/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * The Class BuscadorEmpresaView.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BuscadorEmpresaView {

	/** The query. */
	private String query;

	/**
	 * Gets the query.
	 *
	 * @return the query
	 */
	public String getQuery() {
		return query;
	}

	/**
	 * Sets the query.
	 *
	 * @param query
	 *            the new query
	 */
	public void setQuery(String query) {
		this.query = query;
	}

}
