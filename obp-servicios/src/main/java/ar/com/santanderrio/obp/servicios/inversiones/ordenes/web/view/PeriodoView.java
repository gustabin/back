/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.ordenes.web.view;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * La clase Tipo.
 *
 * @author luis.ventocilla
 * @author emilio.watemberg
 * @see {@link Filtro}
 * @since Wed 8, 2017
 */
@XmlRootElement(name = "periodoView", namespace = "bean")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PeriodoView extends Filtro {

	/**
	 * Instantiates a new periodo view.
	 *
	 * @param nombre
	 *            the nombre
	 * @param valor
	 *            the valor
	 */
	public PeriodoView(String nombre, String valor) {
		super(valor, nombre);

	}

}
