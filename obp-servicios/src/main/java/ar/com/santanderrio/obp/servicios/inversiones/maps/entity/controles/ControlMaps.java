/**
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.maps.entity.controles;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.codehaus.jackson.annotate.JsonTypeInfo.As;
import org.codehaus.jackson.annotate.JsonTypeInfo.Id;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import ar.com.santanderrio.obp.servicios.comun.utils.ValidationEntity;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.controles.items.ItemComprobante;
import ar.com.santanderrio.obp.servicios.inversiones.maps.exception.ControlMapValidationException;

/**
 * @author B042134
 *
 */
@JsonTypeInfo(use = Id.NAME, property = "Tipo", include = As.PROPERTY, defaultImpl = UnknownControl.class)
@JsonSubTypes(value = { @JsonSubTypes.Type(value = LegalControl.class, name = "legal"),
		@JsonSubTypes.Type(value = ServicioControl.class, name = "servicio"),
		@JsonSubTypes.Type(value = FormularioControl.class, name = "formulario"),
		@JsonSubTypes.Type(value = ListaControl.class, name = "lista"),
		@JsonSubTypes.Type(value = InputTextControl.class, name = "input-text"),
		@JsonSubTypes.Type(value = InputNumberControl.class, name = "input-number"),
		@JsonSubTypes.Type(value = ImporteCompuestoControl.class, name = "importe-compuesto"),
		@JsonSubTypes.Type(value = ConsultaAdhesionControl.class, name = "consulta-adhesiones"),
		@JsonSubTypes.Type(value = FechaCompuestaControl.class, name = "fecha-compuesta"),
		@JsonSubTypes.Type(value = FechaControl.class, name = "fecha"),
		@JsonSubTypes.Type(value = MonedaControl.class, name = "moneda"),
		@JsonSubTypes.Type(value = CuentaOperativaControl.class, name = "cuenta-operativa"),
		@JsonSubTypes.Type(value = CuentaTitulosControl.class, name = "cuenta-titulos"),
		@JsonSubTypes.Type(value = DisclaimerControl.class, name = "disclaimer"),
		@JsonSubTypes.Type(value = FondoControl.class, name = "fondo"),
		@JsonSubTypes.Type(value = FondoCompuestoControl.class, name = "fondo-compuesto") })
public abstract class ControlMaps {
	@NotNull
	@JsonProperty("Id")
	protected String id;

	@NotNull
	@JsonProperty("Nombre")
	protected String nombre;

	@JsonProperty("Implementa")
	protected String implementa;

	@JsonProperty("Etiqueta")
	protected String etiqueta;

	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	@JsonProperty("Ayuda")
	protected String ayuda;

	@JsonProperty("Requerido")
	protected Boolean requerido;

	@JsonProperty("Bloqueado")
	protected Boolean bloqueado = Boolean.FALSE;

	@JsonProperty("Posicion")
	protected Integer posicion;

	@JsonProperty("Error")
	protected Integer error;

	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	@JsonProperty("Error_desc")
	protected String errorDesc;

	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	@JsonProperty("Error_tecnico")
	protected String errorTecnico;

	@JsonProperty("Validado")
	protected Boolean validado;

	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	@JsonProperty("Informacion")
	protected String informacion;

	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	@JsonProperty("Config")
	protected String config;

	public static final String TIPO_CONTROL = "control";
	public static final String TIPO_LEGAL = "legal";
	public static final String TIPO_DISCLAIMER = "disclaimer";

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the implementa
	 */
	public String getImplementa() {
		return implementa;
	}

	/**
	 * @param implementa the implementa to set
	 */
	public void setImplementa(String implementa) {
		this.implementa = implementa;
	}

	/**
	 * @return the etiqueta
	 */
	public String getEtiqueta() {
		return etiqueta;
	}

	/**
	 * @param etiqueta the etiqueta to set
	 */
	public void setEtiqueta(String etiqueta) {
		this.etiqueta = etiqueta;
	}

	/**
	 * @return the ayuda
	 */
	public String getAyuda() {
		return ayuda;
	}

	/**
	 * @param ayuda the ayuda to set
	 */
	public void setAyuda(String ayuda) {
		this.ayuda = ayuda;
	}

	/**
	 * @return the requerido
	 */
	public Boolean getRequerido() {
		return requerido;
	}

	/**
	 * @param requerido the requerido to set
	 */
	public void setRequerido(Boolean requerido) {
		this.requerido = requerido;
	}

	/**
	 * @return the bloqueado
	 */
	public Boolean getBloqueado() {
		return bloqueado;
	}

	/**
	 * @param bloqueado the bloqueado to set
	 */
	public void setBloqueado(Boolean bloqueado) {
		this.bloqueado = bloqueado;
	}

	/**
	 * @return the posicion
	 */
	public Integer getPosicion() {
		return posicion;
	}

	/**
	 * @param posicion the posicion to set
	 */
	public void setPosicion(Integer posicion) {
		this.posicion = posicion;
	}

	/**
	 * @return the error
	 */
	public Integer getError() {
		return error;
	}

	/**
	 * @param error the error to set
	 */
	public void setError(Integer error) {
		this.error = error;
	}

	/**
	 * @return the errorDesc
	 */
	public String getErrorDesc() {
		return errorDesc;
	}

	/**
	 * @param errorDesc the errorDesc to set
	 */
	public void setErrorDesc(String errorDesc) {
		this.errorDesc = errorDesc;
	}

	/**
	 * @return the errorTecnico
	 */
	public String getErrorTecnico() {
		return errorTecnico;
	}

	/**
	 * @param errorTecnico the errorTecnico to set
	 */
	public void setErrorTecnico(String errorTecnico) {
		this.errorTecnico = errorTecnico;
	}

	/**
	 * @return the validado
	 */
	public Boolean getValidado() {
		return validado;
	}

	/**
	 * @param validado the validado to set
	 */
	public void setValidado(Boolean validado) {
		this.validado = validado;
	}

	/**
	 * @return the informacion
	 */
	public String getInformacion() {
		return informacion;
	}

	/**
	 * @param informacion the informacion to set
	 */
	public void setInformacion(String informacion) {
		this.informacion = informacion;
	}

	/**
	 * @return the config
	 */
	public String getConfig() {
		return config;
	}

	/**
	 * @param config the config to set
	 */
	public void setConfig(String config) {
		this.config = config;
	}

	public Integer cantidadAdhesiones() {
		return 0;
	}

	public String padreId() {
		return null;
	}

	public void validate() throws ControlMapValidationException {
		if (!Boolean.TRUE.equals(this.getBloqueado()) && !ValidationEntity.validate(this)) {
			throw new ControlMapValidationException("Validation constraints");
		}
	}

	public String obtenerAlias() {
		return null;
	}

	public String obtenerDescripcionDinamica() {
		return null;
	}

	public String obtenerEstadoAdhesion() {
		return null;
	}

	public String obtenerFechaDesde() {
		return null;
	}

	public String obtenerFechaHasta() {
		return null;
	}

	public ItemComprobante imprimirComprobante() {
		ItemComprobante itemComprobante = new ItemComprobante();
		itemComprobante.setEtiqueta(this.getEtiqueta());
		itemComprobante.setValor(this.valorComprobante());
		return itemComprobante;
	}

	public String tipoComprobante() {
		return TIPO_CONTROL;
	}

	public String valorComprobante() {
		return StringUtils.EMPTY;
	}

}
