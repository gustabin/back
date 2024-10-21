/*
 * 
 */
package ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.entities;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.dto.OfertaComercialDTO;

/**
 * The Class InteresOfertaComercialEntity.
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class InteresOfertaComercialEntity {

	/** The nup. */
	@JsonProperty("nup")
	private String nup;

	/** The jsession id. */
	@JsonProperty("jsessionid")
	private String jsessionId;

	/** The cod evento. */
	@JsonProperty("cod_evento")
	private String codEvento;

	/** The nombre evento. */
	@JsonProperty("nombre_evento")
	private String nombreEvento;

	/** The id oferta rtd. */
	@JsonProperty("id_oferta_rtd")
	private String idOfertaRtd;

	/** The id oferta interno. */
	@JsonProperty("id_oferta_interno")
	private String idOfertaInterno;

	/** The tipo oferta. */
	@JsonProperty("tipo_oferta")
	private String tipoOferta;

	/** The categoria oferta. */
	@JsonProperty("categoria_oferta")
	private String categoriaOferta;

	/** The grupo control. */
	@JsonProperty("grupo_control")
	private String grupoControl;

	/** The ubicacion carrusel. */
	@JsonProperty("ubicacion_carrusel")
	private String ubicacionCarrusel;

	/** The ubicacion seccion. */
	@JsonProperty("ubicacion_seccion")
	private String ubicacionSeccion;

	/**
	 * Instantiates a new interes oferta comercial entity.
	 */
	public InteresOfertaComercialEntity() {
		super();
	}

	/**
	 * Instantiates a new interes oferta comercial entity.
	 *
	 * @param nup
	 *            the nup
	 * @param jsessionId
	 *            the jsession id
	 * @param ofertaComercialDTO
	 *            the oferta comercial DTO
	 */
	public InteresOfertaComercialEntity(String nup, String jsessionId, OfertaComercialDTO ofertaComercialDTO) {
		this.nup = nup;
		this.jsessionId = jsessionId;
		this.codEvento = "8";
		this.nombreEvento = "informarInteresOferta";
		this.idOfertaRtd = ofertaComercialDTO.getIdRtd();
		this.idOfertaInterno = ofertaComercialDTO.getIdInterno();
		this.tipoOferta = ofertaComercialDTO.getTipoOferta();
		this.categoriaOferta = ofertaComercialDTO.getCategoriaOferta();
		this.grupoControl = ofertaComercialDTO.getGrupoControl();
		this.ubicacionCarrusel = ofertaComercialDTO.getUbicacionCarrusel();
		this.ubicacionSeccion = ofertaComercialDTO.getUbicacionSeccion();
	}

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
	 *            the new nup
	 */
	public void setNup(String nup) {
		this.nup = nup;
	}

	/**
	 * Gets the jsession id.
	 *
	 * @return the jsession id
	 */
	public String getJsessionId() {
		return jsessionId;
	}

	/**
	 * Sets the jsession id.
	 *
	 * @param jsessionid
	 *            the new jsession id
	 */
	public void setJsessionId(String jsessionid) {
		this.jsessionId = jsessionid;
	}

	/**
	 * Gets the cod evento.
	 *
	 * @return the cod evento
	 */
	public String getCodEvento() {
		return codEvento;
	}

	/**
	 * Sets the cod evento.
	 *
	 * @param codEvento
	 *            the new cod evento
	 */
	public void setCodEvento(String codEvento) {
		this.codEvento = codEvento;
	}

	/**
	 * Gets the nombre evento.
	 *
	 * @return the nombre evento
	 */
	public String getNombreEvento() {
		return nombreEvento;
	}

	/**
	 * Sets the nombre evento.
	 *
	 * @param nombreEvento
	 *            the new nombre evento
	 */
	public void setNombreEvento(String nombreEvento) {
		this.nombreEvento = nombreEvento;
	}

	/**
	 * Gets the id oferta rtd.
	 *
	 * @return the id oferta rtd
	 */
	public String getIdOfertaRtd() {
		return idOfertaRtd;
	}

	/**
	 * Sets the id oferta rtd.
	 *
	 * @param idOfertaRtd
	 *            the new id oferta rtd
	 */
	public void setIdOfertaRtd(String idOfertaRtd) {
		this.idOfertaRtd = idOfertaRtd;
	}

	/**
	 * Gets the id oferta interno.
	 *
	 * @return the id oferta interno
	 */
	public String getIdOfertaInterno() {
		return idOfertaInterno;
	}

	/**
	 * Sets the id oferta interno.
	 *
	 * @param idOfertaInterno
	 *            the new id oferta interno
	 */
	public void setIdOfertaInterno(String idOfertaInterno) {
		this.idOfertaInterno = idOfertaInterno;
	}

	/**
	 * Gets the tipo oferta.
	 *
	 * @return the tipo oferta
	 */
	public String getTipoOferta() {
		return tipoOferta;
	}

	/**
	 * Sets the tipo oferta.
	 *
	 * @param tipoOferta
	 *            the new tipo oferta
	 */
	public void setTipoOferta(String tipoOferta) {
		this.tipoOferta = tipoOferta;
	}

	/**
	 * Gets the categoria oferta.
	 *
	 * @return the categoria oferta
	 */
	public String getCategoriaOferta() {
		return categoriaOferta;
	}

	/**
	 * Sets the categoria oferta.
	 *
	 * @param categoriaOferta
	 *            the new categoria oferta
	 */
	public void setCategoriaOferta(String categoriaOferta) {
		this.categoriaOferta = categoriaOferta;
	}

	/**
	 * Gets the grupo control.
	 *
	 * @return the grupo control
	 */
	public String getGrupoControl() {
		return grupoControl;
	}

	/**
	 * Sets the grupo control.
	 *
	 * @param grupoControl
	 *            the new grupo control
	 */
	public void setGrupoControl(String grupoControl) {
		this.grupoControl = grupoControl;
	}

	/**
	 * Gets the ubicacion carrusel.
	 *
	 * @return the ubicacion carrusel
	 */
	public String getUbicacionCarrusel() {
		return ubicacionCarrusel;
	}

	/**
	 * Sets the ubicacion carrusel.
	 *
	 * @param ubicacionCarrusel
	 *            the new ubicacion carrusel
	 */
	public void setUbicacionCarrusel(String ubicacionCarrusel) {
		this.ubicacionCarrusel = ubicacionCarrusel;
	}

	/**
	 * Gets the ubicacion seccion.
	 *
	 * @return the ubicacion seccion
	 */
	public String getUbicacionSeccion() {
		return ubicacionSeccion;
	}

	/**
	 * Sets the ubicacion seccion.
	 *
	 * @param ubicacionSeccion
	 *            the new ubicacion seccion
	 */
	public void setUbicacionSeccion(String ubicacionSeccion) {
		this.ubicacionSeccion = ubicacionSeccion;
	}

}
