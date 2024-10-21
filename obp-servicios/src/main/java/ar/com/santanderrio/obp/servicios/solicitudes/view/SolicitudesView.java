/*
 * 
 */
package ar.com.santanderrio.obp.servicios.solicitudes.view;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import ar.com.santanderrio.obp.servicios.solicitudes.dto.SolicitudesDTO;
import ar.com.santanderrio.obp.servicios.solicitudes.entities.GestionesSolicitudesEnum;
import ar.com.santanderrio.obp.servicios.trackingtarjetas.view.DatosTrackingTarjetaView;
import ar.com.santanderrio.obp.servicios.gestiondecasos.connector.models.InformationObpCardsDto;

/**
 * The Class SolicitudesView.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@JsonSerialize(include = Inclusion.NON_NULL)
public class SolicitudesView {

	/** The gestiones disponibles. */
	private String gestionesDisponibles;

	/** The productos en tracking. */
	private String productosEnTracking;

	/** The es cliente select. */
	private Boolean esClienteSelect;
	
	/** Flag para habilitar tracking de tarjetas. */
    private boolean tieneTracking;
	
//	private String grupoAfinidadCliente;

	/** The gestiones. */
	private List<GestionView> gestiones = new ArrayList<GestionView>();

	/** The action sheet. */
	private List<GestionView> actionSheet = new ArrayList<GestionView>();

	/** The tracking. */
    private List<DatosTrackingTarjetaView> tracking;

    private String grupoAfinidad;

	/** Respuesta API gestion de casos */
	private List<InformationObpCardsView> cardsGestionDeCasos = new ArrayList<InformationObpCardsView>();
	
	/**
	 * Instantiates a new solicitudes view.
	 */
	public SolicitudesView() {
		super();
	}

	
	
	/**
	 * Checks if is tiene tracking.
	 *
	 * @return true, if is tiene tracking
	 */
	public boolean isTieneTracking() {
        return tieneTracking;
    }



    /**
	 * Sets the tiene tracking.
	 *
	 * @param tieneTracking
	 *            the new tiene tracking
	 */
    public void setTieneTracking(boolean tieneTracking) {
        this.tieneTracking = tieneTracking;
    }



    /**
	 * Instantiates a new solicitudes view.
	 *
	 * @param clienteSelect
	 *            the cliente select
	 * @param solicitudesDTO
	 *            the solicitudes DTO
	 */
	public SolicitudesView(Boolean clienteSelect, SolicitudesDTO solicitudesDTO) {
		this.esClienteSelect = clienteSelect;
		this.addGestion(solicitudesDTO.getGestiones());
		this.addAction(solicitudesDTO.getActionSheet());
		this.gestionesDisponibles = String.valueOf(this.gestiones.size());
	}

	/**
	 * Gets the gestiones disponibles.
	 *
	 * @return the gestiones disponibles
	 */
	public String getGestionesDisponibles() {
		return gestionesDisponibles;
	}

	/**
	 * Sets the gestiones disponibles.
	 *
	 * @param gestionesDisponibles
	 *            the new gestiones disponibles
	 */
	public void setGestionesDisponibles(String gestionesDisponibles) {
		this.gestionesDisponibles = gestionesDisponibles;
	}

	/**
	 * Gets the productos en tracking.
	 *
	 * @return the productos en tracking
	 */
	public String getProductosEnTracking() {
		return productosEnTracking;
	}

	/**
	 * Sets the productos en tracking.
	 *
	 * @param productosEnTracking
	 *            the new productos en tracking
	 */
	public void setProductosEnTracking(String productosEnTracking) {
		this.productosEnTracking = productosEnTracking;
	}

	/**
	 * Checks if is es cliente select.
	 *
	 * @return the boolean
	 */
	public Boolean isEsClienteSelect() {
		return esClienteSelect;
	}

	/**
	 * Sets the es cliente select.
	 *
	 * @param esClienteSelect
	 *            the new es cliente select
	 */
	public void setEsClienteSelect(Boolean esClienteSelect) {
		this.esClienteSelect = esClienteSelect;
	}

	/**
	 * Gets the gestiones.
	 *
	 * @return the gestiones
	 */
	public List<GestionView> getGestiones() {
		return gestiones;
	}

	/**
	 * Sets the gestiones.
	 *
	 * @param gestiones
	 *            the new gestiones
	 */
	public void setGestiones(List<GestionView> gestiones) {
		this.gestiones = gestiones;
	}

	/**
	 * Gets the action sheet.
	 *
	 * @return the action sheet
	 */
	public List<GestionView> getActionSheet() {
		return actionSheet;
	}

	/**
	 * Sets the action sheet.
	 *
	 * @param actionSheet
	 *            the new action sheet
	 */
	public void setActionSheet(List<GestionView> actionSheet) {
		this.actionSheet = actionSheet;
	}

	/**
	 * Gets the tracking.
	 *
	 * @return the tracking
	 */
    public List<DatosTrackingTarjetaView> getTracking() {
		return tracking;
	}

	/**
	 * Sets the tracking.
	 *
	 * @param tracking
	 *            the new tracking
	 */
    public void setTracking(List<DatosTrackingTarjetaView> tracking) {
		this.tracking = tracking;
	}

	/**
	 * Adds the gestion.
	 *
	 * @param tipo
	 *            the tipo
	 */
	public void addGestion(String tipo) {
		this.gestiones.add(new GestionView(tipo));
	}

	/**
	 * Adds the gestion.
	 *
	 * @param gestionView
	 *            the gestion view
	 */
	public void addGestion(GestionView gestionView) {
		this.gestiones.add(gestionView);
	}

	/**
	 * Adds the gestion.
	 *
	 * @param tipo
	 *            the tipo
	 */
	public void addGestion(GestionesSolicitudesEnum tipo) {
		this.gestiones.add(new GestionView(tipo.getTipo()));
	}

	/**
	 * Adds the gestion.
	 *
	 * @param gestiones
	 *            the gestiones
	 */
	public void addGestion(List<String> gestiones) {
		for (String gestion : gestiones) {
			addGestion(gestion);
		}
	}

	/**
	 * Adds the action.
	 *
	 * @param tipo
	 *            the tipo
	 */
	public void addAction(String tipo) {
		this.actionSheet.add(new GestionView(tipo));
	}

	/**
	 * Adds the action.
	 *
	 * @param tipo
	 *            the tipo
	 */
	public void addCardGestionDeCasos(List<InformationObpCardsDto> cardGestionDeCasos) {
		for (InformationObpCardsDto informationObpCardsDto : cardGestionDeCasos) {
			this.cardsGestionDeCasos.add(new InformationObpCardsView(informationObpCardsDto));
		}
		
	}


	/**
	 * Adds the action.
	 *
	 * @param gestiones
	 *            the gestiones
	 */
	public void addAction(List<String> gestiones) {
		for (String gestion : gestiones) {
			this.actionSheet.add(new GestionView(gestion));
		}
	}

	public String getGrupoAfinidad() {
		return grupoAfinidad;
	}

	public void setGrupoAfinidad(String grupoAfinidad) {
		this.grupoAfinidad = grupoAfinidad;
	}

	public List<InformationObpCardsView> getCardsGestionDeCasos() {
		return cardsGestionDeCasos;
	}
	
//	public String getGrupoAfinidadCliente() {
//		return grupoAfinidadCliente;
//	}
//
//	public void setGrupoAfinidadCliente(String grupoAfinidadCliente) {
//		this.grupoAfinidadCliente = grupoAfinidadCliente;
//	}

}
