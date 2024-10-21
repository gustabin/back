package ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.entities;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.dto.OfertaComercialDTO;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.dto.DatosDeDomicilioDTO;

/**
 * The Class InformarGestionACEntity.
 * 
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class InformarGestionACEntity {
	
	/** The nup. */
    @JsonProperty("nup")
    private String nup;

    /** The cod_evento. */
    @JsonProperty("cod_evento")
    private String codEvento;

    /** The nombre_evento. */
    @JsonProperty("nombre_evento")
    private String nombreEvento;

    /** The id. */
    @JsonProperty("id")
    private String id;

    /** The ubicacion. */
    @JsonProperty("ubicacion")
    private String ubicacion;

    /** The orden_prioridad. */
    @JsonProperty("orden_prioridad")
    private String ordenPrioridad;

    /** The observaciones. */
    @JsonProperty("observaciones")
    private String observaciones;

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
     * Gets the id.
     *
     * @return the id
     */
	public String getId() {
		return id;
	}

	/**
     * Sets the id.
     *
     * @param id
     *            the new id
     */
	public void setId(String id) {
		this.id = id;
	}

	/**
     * Gets the ubicacion.
     *
     * @return the ubicacion
     */
	public String getUbicacion() {
		return ubicacion;
	}

	/**
     * Sets the ubicacion.
     *
     * @param ubicacion
     *            the new ubicacion
     */
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	/**
     * Gets the orden prioridad.
     *
     * @return the orden prioridad
     */
	public String getOrdenPrioridad() {
		return ordenPrioridad;
	}

	/**
     * Sets the orden prioridad.
     *
     * @param ordenPrioridad
     *            the new orden prioridad
     */
	public void setOrdenPrioridad(String orden_prioridad) {
		this.ordenPrioridad = orden_prioridad;
	}

	/**
     * Gets the observaciones.
     *
     * @return the observaciones
     */
	public String getObservaciones() {
		return observaciones;
	}

	/**
     * Sets the observaciones.
     *
     * @param observaciones
     *            the new observaciones
     */
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
    
	/**
	 * Instantiates a new informar gestion ac entity.
	 *
	 * @param nup
	 *            the nup
	 * @param domicilioDeReemplazo
	 *            the domicilio de reemplazo
	 */
	public InformarGestionACEntity(String nup, DatosDeDomicilioDTO domicilioDeReemplazo, OfertaComercialDTO ofertaRecambioChip) {
		this.nup = nup;
		this.codEvento = "18";
		this.nombreEvento = "informarGestionAC";
		String id = ofertaRecambioChip.getIdRtd();
		// Se debe mostrar solo lo que viene despues del $
		this.id = id.substring(id.lastIndexOf("$") + 1 );
		if (!StringUtils.isBlank(ofertaRecambioChip.getUbicacionCarrusel())) {
			this.ubicacion = "S";
		} else {
			// Revisar esta ubicacion
			this.ubicacion = "TARJETAS";
		}
		this.ordenPrioridad = ofertaRecambioChip.getOrdenPrioridad();
		this.observaciones = formatearDomicilioAObservaciones(domicilioDeReemplazo);
	}
	
	public InformarGestionACEntity(String nup, OfertaComercialDTO oferta, String ubicacion) {
		this.nup = nup;
		this.codEvento = "18";
		this.nombreEvento = "informarGestionAC";
		String id = oferta.getIdRtd();
		// Se debe mostrar solo lo que viene despues del $
		this.id = id.substring(id.lastIndexOf("$") + 1 );
		if (!StringUtils.isBlank(oferta.getUbicacionCarrusel())) {
			this.ubicacion = "S";
		} else {
			// Revisar esta ubicacion
			this.ubicacion = ubicacion;
		}
		this.ordenPrioridad = oferta.getOrdenPrioridad();
		this.observaciones = "";
	}
	
	private String formatearDomicilioAObservaciones(DatosDeDomicilioDTO domicilio) {
		StringBuilder sb = new StringBuilder();
		return sb.append(domicilio.getTipoDeDomicilio() + "|")
				.append(domicilio.getNroSucursal() + "|")
				.append(domicilio.getNombreCalle() + "|")
				.append(domicilio.getPuerta() + "|")
				.append(domicilio.getPiso() + "|")
				.append(domicilio.getDepartamento() + "|")
				.append(domicilio.getLocalidad() + "|")
				.append(domicilio.getCodigoPostal() + "|")
				.append(domicilio.getProvincia())
				.toString();
	}
	
}
