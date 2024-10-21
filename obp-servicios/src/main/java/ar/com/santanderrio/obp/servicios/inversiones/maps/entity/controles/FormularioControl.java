/**
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.maps.entity.controles;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.RequestMap;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.controles.items.ServicioMaps;
import ar.com.santanderrio.obp.servicios.inversiones.maps.exception.ControlMapValidationException;

/**
 * @author sergio.e.goldentair
 *
 */
public class FormularioControl extends ControlMaps implements RequestMap {
    
    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(FormularioControl.class);

    private static final String ESTADO_CONSULTA = "consulta";
    
    @JsonProperty("IdServicio")
    private String idServicio;
    @JsonProperty("Tipo")
    private String tipo;
    @JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
    @JsonProperty("IdSimulacion")
    private String idSimulacion;
    @JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
    @JsonProperty("Comprobante")
    private String comprobante; 
    @NotNull
    @JsonProperty("Estado")
    private String estado; 
    @JsonProperty("FormAnterior")
    private String formAnterior; 
    @JsonProperty("IdAdhesion")
    private Long idAdhesion; 
    @JsonProperty("Titulo")
    private String titulo; 
    @NotNull
    @JsonProperty("Nup")
    private String nup; 
    @NotNull
    @JsonProperty("Segmento")
    private String segmento; 
    @NotNull
    @JsonProperty("Canal")
    private String canal; 
    @NotNull
    @JsonProperty("SubCanal")
    private String subCanal; 
    @JsonProperty("PerfilInversor")
    private String perfilInversor; 
    @JsonProperty("Items")
    private List<ControlMaps> items;

    @JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
    @JsonProperty("FechaComprobante")
    private String fechaComprobante;
    
    private List<String> feriados;
    
    private List<String> puedeRescatar;
    
    private List<String> puedeSuscribir;

    /**
	 * @return the puedeRescatar
	 */
	public List<String> getPuedeRescatar() {
		return puedeRescatar;
	}

	/**
	 * @param puedeRescatar the puedeRescatar to set
	 */
	public void setPuedeRescatar(List<String> puedeRescatar) {
		this.puedeRescatar = puedeRescatar;
	}

	/**
	 * @return the puedeSuscribir
	 */
	public List<String> getPuedeSuscribir() {
		return puedeSuscribir;
	}

	/**
	 * @param puedeSuscribir the puedeSuscribir to set
	 */
	public void setPuedeSuscribir(List<String> puedeSuscribir) {
		this.puedeSuscribir = puedeSuscribir;
	}

	/**
	 * @return the feriados
	 */
	public List<String> getFeriados() {
		return feriados;
	}

	/**
	 * @param feriados the feriados to set
	 */
	public void setFeriados(List<String> feriados) {
		this.feriados = feriados;
	}

	/**
     * @return the idServicio
     */
    public String getIdServicio() {
        return idServicio;
    }

    /**
     * @param idServicio the idServicio to set
     */
    public void setIdServicio(String idServicio) {
        this.idServicio = idServicio;
    }

    /**
     * @return the idSimulacion
     */
    public String getIdSimulacion() {
        return idSimulacion;
    }

    /**
     * @param idSimulacion the idSimulacion to set
     */
    public void setIdSimulacion(String idSimulacion) {
        this.idSimulacion = idSimulacion;
    }

    /**
     * @return the comprobante
     */
    public String getComprobante() {
        return comprobante;
    }

    /**
     * @param comprobante the comprobante to set
     */
    public void setComprobante(String comprobante) {
        this.comprobante = comprobante;
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * @return the formAnterior
     */
    public String getFormAnterior() {
        return formAnterior;
    }

    /**
     * @param formAnterior the formAnterior to set
     */
    public void setFormAnterior(String formAnterior) {
        this.formAnterior = formAnterior;
    }

    /**
     * @return the idAdhesion
     */
    public Long getIdAdhesion() {
        return idAdhesion;
    }

    /**
     * @param idAdhesion the idAdhesion to set
     */
    public void setIdAdhesion(Long idAdhesion) {
        this.idAdhesion = idAdhesion;
    }

    /**
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @return the nup
     */
    public String getNup() {
        return nup;
    }

    /**
     * @param nup the nup to set
     */
    public void setNup(String nup) {
        this.nup = nup;
    }

    /**
     * @return the segmento
     */
    public String getSegmento() {
        return segmento;
    }

    /**
     * @param segmento the segmento to set
     */
    public void setSegmento(String segmento) {
        this.segmento = segmento;
    }

    /**
     * @return the canal
     */
    public String getCanal() {
        return canal;
    }

    /**
     * @param canal the canal to set
     */
    public void setCanal(String canal) {
        this.canal = canal;
    }

    /**
     * @return the subCanal
     */
    public String getSubCanal() {
        return subCanal;
    }

    /**
     * @param subCanal the subCanal to set
     */
    public void setSubCanal(String subCanal) {
        this.subCanal = subCanal;
    }

    /**
     * @return the perfilInversor
     */
    public String getPerfilInversor() {
        return perfilInversor;
    }

    /**
     * @param perfilInversor the perfilInversor to set
     */
    public void setPerfilInversor(String perfilInversor) {
        this.perfilInversor = perfilInversor;
    }

    /**
     * @return the items
     */
    public List<ControlMaps> getItems() {
        return items;
    }

    /**
     * @param items the items to set
     */
    public void setItems(List<ControlMaps> items) {
        this.items = items;
    }



    public void validateConsulta() throws ControlMapValidationException {
        this.validate();
        if (this.estado == null || !ESTADO_CONSULTA.equalsIgnoreCase(this.estado)) {
            throw new ControlMapValidationException("Error estado <> consulta");
        }
    }

    /**
     * Agregar control.
     *
     * @param controlesServicios2 the servicio
     * @throws ControlMapValidationException 
     */
    public void agregarControl(ControlMaps control) throws ControlMapValidationException {
        if( this.items == null) {
            this.items= new ArrayList<ControlMaps>();
        }
        this.items.add(control);
        control.validate();
    }

    /**
     * Gets the cantidad adhesiones.
     *
     * @return the cantidad adhesiones
     */
    public Integer cantidadAdhesiones() {
        
        Integer cantidad = 0;
        for (ControlMaps control: this.items) {
            cantidad += control.cantidadAdhesiones();
        }
        return cantidad;
    }

	public void eliminarServiciosBloqueados() {
		List<ControlMaps> controles = this.getItems();
		
		for(ControlMaps control:controles) {
			
		    if(control instanceof ServicioControl) {
		        List<ServicioMaps> serviciosBloqueados = new ArrayList<ServicioMaps>();

		        for(ServicioMaps servicio : ((ServicioControl) control).getItems()) {
			        if(servicio.getBloqueado()) {
			            serviciosBloqueados.add(servicio);
			        }
			    }
			    ((ServicioControl) control).getItems().removeAll(serviciosBloqueados);
		    }
		    break;
		}
	}
	
	@Override
	public void validate() throws ControlMapValidationException{
	    super.validate();
	    if (("simulacion".equals(estado) || "confirmacion".equals(estado)) && this.error!= 0 ) {
	        LOGGER.error("Formulario con estado {} contiene errres.", estado);
	        throw new ControlMapValidationException("Formulario con errres");
        }
    }   

    public String getTipo() {
        return "formulario";
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the fechaComprobante
     */
    public String getFechaComprobante() {
        return fechaComprobante;
    }

    /**
     * @param fechaComprobante the fechaComprobante to set
     */
    public void setFechaComprobante(String fechaComprobante) {
        this.fechaComprobante = fechaComprobante;
    }
    
}
