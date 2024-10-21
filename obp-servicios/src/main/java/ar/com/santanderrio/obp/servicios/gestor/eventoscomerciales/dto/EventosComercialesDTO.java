/**
 * 
 */
package ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.dto;

import java.util.*;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.entities.OfertaComercialEntity;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.entities.OfertasComercialesEntity;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.view.RespuestaEncuestaView;

/**
 * The Class OfertaComercialDTO.
 *
 * @author florencia.n.martinez
 */
public class EventosComercialesDTO {

	/** The cantidad puntos. */
	private String cantidadPuntos;

	/** The descripcion. */
	private String descripcion;

	/** The mostrar caja. */
	private Boolean mostrarCaja = Boolean.FALSE;

	/** The jsession id. */
	private String jsessionId;

	/** The ofertas. */
	private List<OfertaComercialDTO> ofertas = new ArrayList<OfertaComercialDTO>();
	
	/** The id encuesta. */
	private String idEncuesta;

	/** The ofertas. */
	private List<RespuestasEncuestaDTO> respuestasEncuesta;
	
	private String lineaCrediticia;
	
	/**
	 * Cargar ofertas.
	 *
	 * @param ofertasComerciales
	 *            the ofertas comerciales
	 */
	public void cargarOfertas(OfertasComercialesEntity ofertasComerciales) {
		Collections.sort(ofertasComerciales.getOfertas(), new Comparator<OfertaComercialEntity>() {
			public int compare(OfertaComercialEntity obj1, OfertaComercialEntity obj2) {
				if (StringUtils.isNotBlank(obj1.getOrdenPrioridad())
						&& StringUtils.isNotBlank(obj2.getOrdenPrioridad())) {
					return obj1.getOrdenPrioridad().compareTo(obj2.getOrdenPrioridad());
				} else if (StringUtils.isNotBlank(obj1.getOrdenPrioridad())) {
					return 1;
				} else if (StringUtils.isNotBlank(obj2.getOrdenPrioridad())) {
					return -1;
				} else {
					return 0;
				}
			}
		});
		for (OfertaComercialEntity ofertaComercial : ofertasComerciales.getOfertas()) {
			this.ofertas.add(new OfertaComercialDTO(ofertaComercial));
		}
	}

	/**
	 * Obtener oferta por id rtd.
	 *
	 * @param idOferta
	 *            the id oferta
	 * @return the oferta comercial DTO
	 */
	public OfertaComercialDTO obtenerOfertaPorIdRtd(String idOferta) {
		if (StringUtils.isNotEmpty(idOferta)) {
			for (OfertaComercialDTO ofertaComercialDTO : ofertas) {
				if (StringUtils.equals(ofertaComercialDTO.getIdRtd(), idOferta)) {
					return ofertaComercialDTO;
				}
			}
		}
		return null;

	}

	/**
	 * Gets the cantidad puntos.
	 *
	 * @return the cantidadPuntos
	 */
	public String getCantidadPuntos() {
		return cantidadPuntos;
	}

	/**
	 * Sets the cantidad puntos.
	 *
	 * @param cantidadPuntos
	 *            the cantidadPuntos to set
	 */
	public void setCantidadPuntos(String cantidadPuntos) {
		this.cantidadPuntos = cantidadPuntos;
	}

	/**
	 * Gets the descripcion.
	 *
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Sets the descripcion.
	 *
	 * @param descripcion
	 *            the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Gets the mostrar caja.
	 *
	 * @return the mostrarCaja
	 */
	public Boolean getMostrarCaja() {
		return mostrarCaja;
	}

	/**
	 * Sets the mostrar caja.
	 *
	 * @param mostrarCaja
	 *            the mostrarCaja to set
	 */
	public void setMostrarCaja(Boolean mostrarCaja) {
		this.mostrarCaja = mostrarCaja;
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
	 * @param jsessionId
	 *            the new jsession id
	 */
	public void setJsessionId(String jsessionId) {
		this.jsessionId = jsessionId;
	}

	/**
	 * Gets the ofertas.
	 *
	 * @return the ofertas
	 */
	public List<OfertaComercialDTO> getOfertas() {
		return ofertas;
	}

	/**
	 * Sets the ofertas.
	 *
	 * @param ofertasCarrusel
	 *            the new ofertas
	 */
	public void setOfertas(List<OfertaComercialDTO> ofertasCarrusel) {
		this.ofertas = ofertasCarrusel;
	}
	
	/**
	 * Gets the id encuesta.
	 *
	 * @return the id encuesta
	 */
	public String getIdEncuesta() {
		return idEncuesta;
	}

	/**
	 * Sets the id encuesta.
	 *
	 * @param idEncuesta
	 *            the new idEncuesta
	 */
	public void setIdEncuesta(String idEncuesta) {
		this.idEncuesta = idEncuesta;
	}

	/**
	 * Gets the respuestas encuesta.
	 *
	 * @return the respuestas encuesta
	 */
	public List<RespuestasEncuestaDTO> getRespuestasEncuesta() {
		return respuestasEncuesta;
	}

	/**
	 * Sets the respuestas encuesta.
	 *
	 * @param respuestasEncuesta
	 *            the new respuestas encuesta
	 */
	public void setRespuestasEncuesta(List<RespuestasEncuestaDTO> respuestasEncuesta) {
		this.respuestasEncuesta = respuestasEncuesta;
	}
	
	public String getLineaCrediticia() {
		return lineaCrediticia;
	}

	public void setLineaCrediticia(String lineaCrediticia) {
		this.lineaCrediticia = lineaCrediticia;
	}

	/**
	 * HashCode.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(cantidadPuntos);
		hcb.append(descripcion);
		hcb.append(mostrarCaja);
		return hcb.toHashCode();
	}

	/**
	 * Equals.
	 *
	 * @param obj
	 *            the obj
	 * @return true, if successful
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EventosComercialesDTO other = (EventosComercialesDTO) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(cantidadPuntos, other.getCantidadPuntos());
		eb.append(descripcion, other.getDescripcion());
		eb.append(mostrarCaja, other.getMostrarCaja());
		return eb.isEquals();
	}

	/**
	 * ToString.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		ToStringBuilder.setDefaultStyle(ToStringStyle.SHORT_PREFIX_STYLE);
		return new ToStringBuilder(this).append("cantidadPuntos", cantidadPuntos).append("descripcion", descripcion)
				.append("mostrarCaja", mostrarCaja).toString();
	}

	/**
	 * Cargar respuestas encuesta.
	 *
	 * @param respuestasEncuestaView
	 *            the respuestas encuesta view
	 */
	public void cargarRespuestasEncuesta(List<RespuestaEncuestaView> respuestasEncuestaView) {
		this.respuestasEncuesta = new ArrayList<RespuestasEncuestaDTO>();
		for (RespuestaEncuestaView respuestaEncuesta : respuestasEncuestaView) {
			this.respuestasEncuesta.add(new RespuestasEncuestaDTO(respuestaEncuesta));
		}
	}
	
	
	
	
	
}
