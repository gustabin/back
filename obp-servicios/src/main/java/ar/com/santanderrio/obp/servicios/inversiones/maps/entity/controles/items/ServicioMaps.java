package ar.com.santanderrio.obp.servicios.inversiones.maps.entity.controles.items;

import javax.validation.constraints.NotNull;

import org.codehaus.jackson.annotate.JsonProperty;

import ar.com.santanderrio.obp.servicios.comun.utils.ValidationEntity;
import ar.com.santanderrio.obp.servicios.inversiones.maps.exception.ControlMapValidationException;

public class ServicioMaps extends ItemGenericoMaps{

    /** The icono. */
    @NotNull
    @JsonProperty("Icono")
    private String icono;
    /** The footer. */
    @NotNull
    @JsonProperty("Footer")
    private String footer;
    /** The cantidad adhesiones. */
    @NotNull
    @JsonProperty("CantAdhesiones")
    private String cantidadAdhesiones;
    /** The tipo servicio. */
    @NotNull
    @JsonProperty("TipoServicio")
    private String tipoServicio;
    /** The titulo. */
    @NotNull
    @JsonProperty("Titulo")
    private String titulo;
    /** The bloqueado. */
    @JsonProperty("Bloqueado")
    protected Boolean bloqueado = Boolean.FALSE;

    @JsonProperty("Posicion")
    private Integer posicion;

	public String getIcono() {
		return icono;
	}

	public void setIcono(String icono) {
		this.icono = icono;
	}

	public String getFooter() {
		return footer;
	}

	public void setFooter(String footer) {
		this.footer = footer;
	}

	public String getCantidadAdhesiones() {
		return cantidadAdhesiones;
	}

	public void setCantidadAdhesiones(String cantidadAdhesiones) {
		this.cantidadAdhesiones = cantidadAdhesiones;
	}

	public String getTipoServicio() {
		return tipoServicio;
	}

	public void setTipoServicio(String tipoServicio) {
		this.tipoServicio = tipoServicio;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Boolean getBloqueado() {
		return bloqueado;
	}

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
	
	public Integer posicionInt() {
	    if(posicion ==null) {
	        return 1;
	    }
	    return posicion;
	}
	
	@Override
	public void validate() throws ControlMapValidationException {
		if(!ValidationEntity.validate(this)) {
	            throw new ControlMapValidationException("Validation constraints");
        }
		try {
			Integer.parseInt(cantidadAdhesiones);
		} catch (NumberFormatException e) {
			throw new ControlMapValidationException("cantidad adhesiones invalida");
		}
	}

}
