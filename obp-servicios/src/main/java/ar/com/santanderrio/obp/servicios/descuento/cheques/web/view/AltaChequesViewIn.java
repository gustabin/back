/*
 * 
 */
package ar.com.santanderrio.obp.servicios.descuento.cheques.web.view;

import java.util.ArrayList;
import java.util.List;

import ar.com.santanderrio.obp.servicios.descuento.cheques.entities.ChequesAceptadosDTO;
import ar.com.santanderrio.obp.servicios.descuento.cheques.entities.ChequesRechazadosDTO;
import ar.com.santanderrio.obp.servicios.descuento.cheques.entities.DetalleOperacionesPrecargadasDTO;

/**
 * The Class AltaChequesViewIn.
 */
public class AltaChequesViewIn {
	
	/** The numero tramite. */
	private String numeroTramite;
	
	/** The fecha alta. */
	private String fechaAlta;
	
	/** The lista cheques. */
	private List<AltaChequeViewIn> listaCheques = new ArrayList<AltaChequeViewIn>();
	
	/**
	 * Instantiates a new alta cheques view in.
	 */
	public AltaChequesViewIn() {
		super();
	}

	/**
	 * Instantiates a new alta cheques view in.
	 *
	 * @param detalle
	 *            the detalle
	 */
	public AltaChequesViewIn(DetalleOperacionesPrecargadasDTO detalle) {
		numeroTramite = detalle.getNumeroOperacion();
		fechaAlta = detalle.getFechaAlta();
		for(ChequesAceptadosDTO aceptados : detalle.getChequesAceptados()) {
			listaCheques.add(new AltaChequeViewIn(aceptados));
		}
		for(ChequesRechazadosDTO rechazados :detalle.getChequesRechazados()) {
			listaCheques.add(new AltaChequeViewIn(rechazados));
		}
	}

	/**
	 * Gets the lista cheques.
	 *
	 * @return the lista cheques
	 */
	public List<AltaChequeViewIn> getListaCheques() {
		return listaCheques;
	}

	/**
	 * Sets the lista cheques.
	 *
	 * @param listaCheques
	 *            the new lista cheques
	 */
	public void setListaCheques(List<AltaChequeViewIn> listaCheques) {
		this.listaCheques = listaCheques;
	}

	/**
	 * Gets the numero tramite.
	 *
	 * @return the numero tramite
	 */
	public String getNumeroTramite() {
		return numeroTramite;
	}

	/**
	 * Sets the numero tramite.
	 *
	 * @param numeroTramite
	 *            the new numero tramite
	 */
	public void setNumeroTramite(String numeroTramite) {
		this.numeroTramite = numeroTramite;
	}

	/**
	 * Gets the fecha alta.
	 *
	 * @return the fecha alta
	 */
	public String getFechaAlta() {
		return fechaAlta;
	}

	/**
	 * Sets the fecha alta.
	 *
	 * @param fechaAlta
	 *            the new fecha alta
	 */
	public void setFechaAlta(String fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	
	

}
