/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.entities;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.beanio.annotation.Field;
import org.beanio.annotation.Record;
import org.beanio.annotation.Segment;

/**
 * Objeto utilizado para retornar del DAO.
 * 
 * @author pablo.d.gargaglione
 *
 */
@Record
public class IntervinientesOutEntity {

	/** The Constant DELIMITER. */
	public static final String DELIMITER = "õ";

	/** The header trama. */
	@Field
	private String headerTrama;

	/** The codigo retorno extendido. */
	@Field
	private String codigoRetornoExtendido;

	/** The domicilio calle. */
	@Field
	private String domicilioCalle;

	/** The domicilio numero. */
	@Field
	private String domicilioNumero;

	/** The domicilio dto. */
	@Field
	private String domicilioDto;

	/** The domicilio piso. */
	@Field
	private String domicilioPiso;

	/** The domicilio localidad. */
	@Field
	private String domicilioLocalidad;

	/** The domicilio codigo postal. */
	@Field
	private String domicilioCodigopostal;

	/** The domicilio patente. */
	@Field
	private String domicilioPatente;

	/** The domicilio Manzana. */
	@Field
	private String domicilioManzana;

	/** The domicilio provincia. */
	@Field
	private String domicilioProvincia;

	/** The domicilio codigo del pais. */
	@Field
	private String domicilioCodigoPais;

	/** The domicilio telefono. */
	@Field
	private String domicilioTelefono;

	/** The domicilio actividad. */
	@Field
	private String domicilioActividad;

	/** The cantidad repeticiones. */
	@Field()
	private Long cantidadRepeticiones;

	/** The lista result. */
	@Segment(occursRef = "cantidadRepeticiones")
	private List<IntervinientesEntity> listaRepeticiones = new ArrayList<IntervinientesEntity>();

	/**
	 * Gets the header trama.
	 *
	 * @return the header trama
	 */
	public String getHeaderTrama() {
		return headerTrama;
	}

	/**
	 * Sets the header trama.
	 *
	 * @param headerTrama
	 *            the new header trama
	 */
	public void setHeaderTrama(String headerTrama) {
		this.headerTrama = headerTrama;
	}

	/**
	 * Gets the codigo retorno extendido.
	 *
	 * @return the codigo retorno extendido
	 */
	public String getCodigoRetornoExtendido() {
		return codigoRetornoExtendido;
	}

	/**
	 * Sets the codigo retorno extendido.
	 *
	 * @param codigoRetornoExtendido
	 *            the new codigo retorno extendido
	 */
	public void setCodigoRetornoExtendido(String codigoRetornoExtendido) {
		this.codigoRetornoExtendido = codigoRetornoExtendido;
	}

	/**
	 * Gets the domicilio calle.
	 *
	 * @return the domicilio calle
	 */
	public String getDomicilioCalle() {
		return domicilioCalle;
	}

	/**
	 * Sets the domicilio calle.
	 *
	 * @param domicilioCalle
	 *            the new domicilio calle
	 */
	public void setDomicilioCalle(String domicilioCalle) {
		this.domicilioCalle = domicilioCalle;
	}

	/**
	 * Gets the domicilio numero.
	 *
	 * @return the domicilio numero
	 */
	public String getDomicilioNumero() {
		return domicilioNumero;
	}

	/**
	 * Sets the domicilio numero.
	 *
	 * @param domicilioNumero
	 *            the new domicilio numero
	 */
	public void setDomicilioNumero(String domicilioNumero) {
		this.domicilioNumero = domicilioNumero;
	}

	/**
	 * Gets the domicilio dto.
	 *
	 * @return the domicilio dto
	 */
	public String getDomicilioDto() {
		return domicilioDto;
	}

	/**
	 * Sets the domicilio dto.
	 *
	 * @param domicilioDto
	 *            the new domicilio dto
	 */
	public void setDomicilioDto(String domicilioDto) {
		this.domicilioDto = domicilioDto;
	}

	/**
	 * Gets the domicilio piso.
	 *
	 * @return the domicilio piso
	 */
	public String getDomicilioPiso() {
		return domicilioPiso;
	}

	/**
	 * Sets the domicilio piso.
	 *
	 * @param domicilioPiso
	 *            the new domicilio piso
	 */
	public void setDomicilioPiso(String domicilioPiso) {
		this.domicilioPiso = domicilioPiso;
	}

	/**
	 * Gets the domicilio localidad.
	 *
	 * @return the domicilio localidad
	 */
	public String getDomicilioLocalidad() {
		return domicilioLocalidad;
	}

	/**
	 * Sets the domicilio localidad.
	 *
	 * @param domicilioLocalidad
	 *            the new domicilio localidad
	 */
	public void setDomicilioLocalidad(String domicilioLocalidad) {
		this.domicilioLocalidad = domicilioLocalidad;
	}

	/**
	 * Gets the domicilio codigopostal.
	 *
	 * @return the domicilio codigopostal
	 */
	public String getDomicilioCodigopostal() {
		return domicilioCodigopostal;
	}

	/**
	 * Sets the domicilio codigopostal.
	 *
	 * @param domicilioCodigopostal
	 *            the new domicilio codigopostal
	 */
	public void setDomicilioCodigopostal(String domicilioCodigopostal) {
		this.domicilioCodigopostal = domicilioCodigopostal;
	}

	/**
	 * Gets the domicilio patente.
	 *
	 * @return the domicilio patente
	 */
	public String getDomicilioPatente() {
		return domicilioPatente;
	}

	/**
	 * Sets the domicilio patente.
	 *
	 * @param domicilioPatente
	 *            the new domicilio patente
	 */
	public void setDomicilioPatente(String domicilioPatente) {
		this.domicilioPatente = domicilioPatente;
	}

	/**
	 * Gets the domicilio manzana.
	 *
	 * @return the domicilio manzana
	 */
	public String getDomicilioManzana() {
		return domicilioManzana;
	}

	/**
	 * Sets the domicilio manzana.
	 *
	 * @param domicilioManzana
	 *            the new domicilio manzana
	 */
	public void setDomicilioManzana(String domicilioManzana) {
		this.domicilioManzana = domicilioManzana;
	}

	/**
	 * Gets the domicilio provincia.
	 *
	 * @return the domicilio provincia
	 */
	public String getDomicilioProvincia() {
		return domicilioProvincia;
	}

	/**
	 * Sets the domicilio provincia.
	 *
	 * @param domicilioProvincia
	 *            the new domicilio provincia
	 */
	public void setDomicilioProvincia(String domicilioProvincia) {
		this.domicilioProvincia = domicilioProvincia;
	}

	/**
	 * Gets the domicilio codigo pais.
	 *
	 * @return the domicilio codigo pais
	 */
	public String getDomicilioCodigoPais() {
		return domicilioCodigoPais;
	}

	/**
	 * Sets the domicilio codigo pais.
	 *
	 * @param domicilioCodigoPais
	 *            the new domicilio codigo pais
	 */
	public void setDomicilioCodigoPais(String domicilioCodigoPais) {
		this.domicilioCodigoPais = domicilioCodigoPais;
	}

	/**
	 * Gets the domicilio telefono.
	 *
	 * @return the domicilio telefono
	 */
	public String getDomicilioTelefono() {
		return domicilioTelefono;
	}

	/**
	 * Sets the domicilio telefono.
	 *
	 * @param domicilioTelefono
	 *            the new domicilio telefono
	 */
	public void setDomicilioTelefono(String domicilioTelefono) {
		this.domicilioTelefono = domicilioTelefono;
	}

	/**
	 * Gets the domicilio actividad.
	 *
	 * @return the domicilio actividad
	 */
	public String getDomicilioActividad() {
		return domicilioActividad;
	}

	/**
	 * Sets the domicilio actividad.
	 *
	 * @param domicilioActividad
	 *            the new domicilio actividad
	 */
	public void setDomicilioActividad(String domicilioActividad) {
		this.domicilioActividad = domicilioActividad;
	}

	/**
	 * Gets the cantidad repeticiones.
	 *
	 * @return the cantidad repeticiones
	 */
	public Long getCantidadRepeticiones() {
		return cantidadRepeticiones;
	}

	/**
	 * Sets the cantidad repeticiones.
	 *
	 * @param cantidadRepeticiones
	 *            the new cantidad repeticiones
	 */
	public void setCantidadRepeticiones(Long cantidadRepeticiones) {
		this.cantidadRepeticiones = cantidadRepeticiones;
	}

	/**
	 * Gets the lista repeticiones.
	 *
	 * @return the lista repeticiones
	 */
	public List<IntervinientesEntity> getListaRepeticiones() {
		return listaRepeticiones;
	}

	/**
	 * Sets the lista repeticiones.
	 *
	 * @param listaRepeticiones
	 *            the new lista repeticiones
	 */
	public void setListaRepeticiones(List<IntervinientesEntity> listaRepeticiones) {
		this.listaRepeticiones = listaRepeticiones;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("headerTrama", headerTrama)
				.append("codigoRetornoExtendido", codigoRetornoExtendido)
				.append("cantidadRepeticiones", cantidadRepeticiones).append("domicilioCalle", domicilioCalle)
				.append("domicilioCalle", domicilioCalle).append("domicilioDto", domicilioDto)
				.append("domicilioPiso", domicilioPiso).append("domicilioLocalidad", domicilioLocalidad)
				.append("domicilioCodigopostal", domicilioCodigopostal).append("domicilioPatente", domicilioPatente)
				.append("domicilioManzana", domicilioManzana).append("domicilioProvincia", domicilioProvincia)
				.append("domicilioCodigoPais", domicilioCodigoPais).append("domicilioTelefono", domicilioTelefono)
				.append("domicilioActividad", domicilioActividad).append("listaResult", listaRepeticiones).toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(codigoRetornoExtendido);
		hcb.append(cantidadRepeticiones);
		hcb.append(domicilioCalle);
		hcb.append(domicilioNumero);
		hcb.append(domicilioDto);
		hcb.append(domicilioPiso);
		hcb.append(domicilioLocalidad);
		hcb.append(domicilioCodigopostal);
		hcb.append(domicilioPatente);
		hcb.append(domicilioManzana);
		hcb.append(domicilioProvincia);
		hcb.append(domicilioCodigoPais);
		hcb.append(domicilioTelefono);
		hcb.append(domicilioActividad);
		hcb.append(listaRepeticiones);
		return hcb.toHashCode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		IntervinientesOutEntity other = (IntervinientesOutEntity) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(codigoRetornoExtendido, other.codigoRetornoExtendido);
		eb.append(listaRepeticiones, other.listaRepeticiones);
		eb.append(domicilioCalle, other.domicilioCalle);
		eb.append(domicilioNumero, other.domicilioNumero);
		eb.append(domicilioDto, other.domicilioDto);
		eb.append(domicilioPiso, other.domicilioPiso);
		eb.append(domicilioLocalidad, other.domicilioLocalidad);
		eb.append(domicilioCodigopostal, other.domicilioCodigopostal);
		eb.append(domicilioPatente, other.domicilioPatente);
		eb.append(domicilioManzana, other.domicilioManzana);
		eb.append(domicilioProvincia, other.domicilioProvincia);
		eb.append(domicilioCodigoPais, other.domicilioCodigoPais);
		eb.append(domicilioTelefono, other.domicilioTelefono);
		eb.append(domicilioActividad, other.domicilioActividad);
		eb.append(cantidadRepeticiones, other.cantidadRepeticiones);
		return eb.isEquals();
	}

}
