/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.entities;

import org.beanio.annotation.Field;
import org.beanio.annotation.Record;

/**
 * The Class DudaDTO.
 */
@Record
public class DudaEntity {

	/** The dud sec. */
	@Field
	private String dudSec;

	/** The dud prov. */
	@Field
	private String dudProv;

	/** The dud local. */
	@Field
	private String dudLocal;

	/** The dud barrio. */
	@Field
	private String dudBarrio;

	/** The dud nomcal. */
	@Field
	private String dudNomcal;

	/** The dud desde. */
	@Field
	private String dudDesde;

	/** The dud hasta. */
	@Field
	private String dudHasta;

	/** The dud CPA. */
	@Field
	private String dudCPA;

	/**
	 * Gets the dud sec.
	 *
	 * @return the dudSec
	 */
	public String getDudSec() {
		return dudSec;
	}

	/**
	 * Sets the dud sec.
	 *
	 * @param dudSec
	 *            the dudSec to set
	 */
	public void setDudSec(String dudSec) {
		this.dudSec = dudSec;
	}

	/**
	 * Gets the dud prov.
	 *
	 * @return the dudProv
	 */
	public String getDudProv() {
		return dudProv;
	}

	/**
	 * Sets the dud prov.
	 *
	 * @param dudProv
	 *            the dudProv to set
	 */
	public void setDudProv(String dudProv) {
		this.dudProv = dudProv;
	}

	/**
	 * Gets the dud local.
	 *
	 * @return the dudLocal
	 */
	public String getDudLocal() {
		return dudLocal;
	}

	/**
	 * Sets the dud local.
	 *
	 * @param dudLocal
	 *            the dudLocal to set
	 */
	public void setDudLocal(String dudLocal) {
		this.dudLocal = dudLocal;
	}

	/**
	 * Gets the dud barrio.
	 *
	 * @return the dudBarrio
	 */
	public String getDudBarrio() {
		return dudBarrio;
	}

	/**
	 * Sets the dud barrio.
	 *
	 * @param dudBarrio
	 *            the dudBarrio to set
	 */
	public void setDudBarrio(String dudBarrio) {
		this.dudBarrio = dudBarrio;
	}

	/**
	 * Gets the dud nomcal.
	 *
	 * @return the dudNomcal
	 */
	public String getDudNomcal() {
		return dudNomcal;
	}

	/**
	 * Sets the dud nomcal.
	 *
	 * @param dudNomcal
	 *            the dudNomcal to set
	 */
	public void setDudNomcal(String dudNomcal) {
		this.dudNomcal = dudNomcal;
	}

	/**
	 * Gets the dud desde.
	 *
	 * @return the dudDesde
	 */
	public String getDudDesde() {
		return dudDesde;
	}

	/**
	 * Sets the dud desde.
	 *
	 * @param dudDesde
	 *            the dudDesde to set
	 */
	public void setDudDesde(String dudDesde) {
		this.dudDesde = dudDesde;
	}

	/**
	 * Gets the dud hasta.
	 *
	 * @return the dudHasta
	 */
	public String getDudHasta() {
		return dudHasta;
	}

	/**
	 * Sets the dud hasta.
	 *
	 * @param dudHasta
	 *            the dudHasta to set
	 */
	public void setDudHasta(String dudHasta) {
		this.dudHasta = dudHasta;
	}

	/**
	 * Gets the dud CPA.
	 *
	 * @return the dudCPA
	 */
	public String getDudCPA() {
		return dudCPA;
	}

	/**
	 * Sets the dud CPA.
	 *
	 * @param dudCPA
	 *            the dudCPA to set
	 */
	public void setDudCPA(String dudCPA) {
		this.dudCPA = dudCPA;
	}

}
