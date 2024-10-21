package ar.com.santanderrio.obp.servicios.inversiones.maps.entity.controles;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.annotate.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.santanderrio.obp.servicios.inversiones.maps.exception.ControlMapValidationException;

public class FechaControl extends InputTextControl {

    @NotNull
	@JsonProperty("FechaMin")
	private String fechaMin;
    @NotNull
	@JsonProperty("FechaMax")
	private String fechaMax;

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(FechaControl.class);
	
	
	public String getFechaMin() {
		return fechaMin;
	}

	public void setFechaMin(String fechaMin) {
		this.fechaMin = fechaMin;
	}

	public String getFechaMax() {
		return fechaMax;
	}

	public void setFechaMax(String fechaMax) {
		this.fechaMax = fechaMax;
	}

	public String obtenerFechaDesde() {
		if ("fecha-desde".equals(this.getNombre())) {
			if (this.valor != null) {
				try {
					return obtenerFecha(this.valor);
				} catch (ParseException e) {
					LOGGER.error("Error al formatear valor fecha: {}", this.valor);
				}
			}
		}
		return null;
	}

	public String obtenerFechaHasta() {
		if ("fecha-hasta".equals(this.getNombre())) {
			if (this.valor != null) {
				try {
					return obtenerFecha(this.valor);
				} catch (ParseException e) {
					LOGGER.error("Error al formatear valor fecha: {}", this.valor);
				}
			}
		}
		return null;
	}
	
	@Override
	public void validate() throws ControlMapValidationException {
	    super.validate();
	    if(!Boolean.TRUE.equals(this.getBloqueado())) {
	        validarFecha(this.fechaMin);
	        validarFecha(this.fechaMax);
	    }
        if (StringUtils.isNotEmpty(this.valor)) {
            validarFecha(this.valor);
        }
	}

    private void validarFecha(String fecha) throws ControlMapValidationException {
        SimpleDateFormat formatoValido = new SimpleDateFormat("yyyy-MM-dd");
        String datestring = fecha.split("T")[0];
        try {
            formatoValido.parse(datestring);
        } catch (ParseException e) {
            LOGGER.error("Error al formatear valor fecha: {}", this.valor);
            throw new ControlMapValidationException("Error al formatear valor fecha");
        }
    }
	
	
    @Override
    public String valorComprobante() {
	    SimpleDateFormat newformat = new SimpleDateFormat("dd/MM/yyyy");
	    String reformattedStr = "";
	    
	    if(this.getValor() != null){
	        String datestring = this.getValor().split("T")[0];
	        SimpleDateFormat oldformat = new SimpleDateFormat("yyyy-MM-dd");
	        try {
	            reformattedStr = newformat.format(oldformat.parse(datestring));
	        } catch (ParseException e) {
	            LOGGER.error("Error al formatear valor fecha: {}", this.valor);
	            return null;
	        }
	        return reformattedStr;
	    }
        return reformattedStr;
    }
    

	private String obtenerFecha(String fecha) throws ParseException{
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date;
		date = format.parse(fecha);
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		return df.format(date);
	}
}
