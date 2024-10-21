/*
 * 
 */
package ar.com.santanderrio.obp.servicios.suscripciones.entities;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The Class ParametrosSuscripcion.
 */
@XmlRootElement(name = "myaParametros")
@XmlAccessorType(XmlAccessType.FIELD)
public class ParametrosSuscripcion {

	/** The lista frecuencias. */
	@XmlElementWrapper(name = "ListaFrecuencias")
	@XmlElement(name = "Frecuencia")
	private List<ParametroFrecuencia> listaFrecuencias;

	/** The lista dias aviso previo. */
	@XmlElementWrapper(name = "ListaDiasAvisoPrevio")
	@XmlElement(name = "DiasAvisoPrevio")
	private List<ParametroDAP> listaDiasAvisoPrevio;

	/** The lista tel celulares. */
	@XmlElementWrapper(name = "ListaTelCelulares")
	@XmlElement(name = "EmpresaCel")
	private List<ParametroEmpresaCel> listaTelCelulares;

	/**
	 * Gets the lista frecuencias.
	 *
	 * @return the lista frecuencias
	 */
	public List<ParametroFrecuencia> getListaFrecuencias() {
		return listaFrecuencias;
	}

	/**
	 * Sets the lista frecuencias.
	 *
	 * @param listaFrecuencias
	 *            the new lista frecuencias
	 */
	public void setListaFrecuencias(List<ParametroFrecuencia> listaFrecuencias) {
		this.listaFrecuencias = listaFrecuencias;
	}

	/**
	 * Gets the lista dias aviso previo.
	 *
	 * @return the lista dias aviso previo
	 */
	public List<ParametroDAP> getListaDiasAvisoPrevio() {
		return listaDiasAvisoPrevio;
	}

	/**
	 * Sets the lista dias aviso previo.
	 *
	 * @param listaDiasAvisoPrevio
	 *            the new lista dias aviso previo
	 */
	public void setListaDiasAvisoPrevio(List<ParametroDAP> listaDiasAvisoPrevio) {
		this.listaDiasAvisoPrevio = listaDiasAvisoPrevio;
	}

	/**
	 * Gets the lista tel celulares.
	 *
	 * @return the lista tel celulares
	 */
	public List<ParametroEmpresaCel> getListaTelCelulares() {
		return listaTelCelulares;
	}

	/**
	 * Sets the lista tel celulares.
	 *
	 * @param listaTelCelulares
	 *            the new lista tel celulares
	 */
	public void setListaTelCelulares(List<ParametroEmpresaCel> listaTelCelulares) {
		this.listaTelCelulares = listaTelCelulares;
	}

}