package ar.com.santanderrio.obp.servicios.inversiones.maps.entity.controles;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.controles.items.ItemGenericoMaps;
import ar.com.santanderrio.obp.servicios.inversiones.maps.exception.ControlMapValidationException;

public class FechaCompuestaControl extends ControlMaps {

	
	@JsonProperty("Items")
	private List<ControlMaps> items;

	public List<ControlMaps> getItems() {
		return items;
	}

	public void setItems(List<ControlMaps> items) {
		this.items = items;
		
	}

	@Override
	public void validate() throws ControlMapValidationException {
		
	    if(this.bloqueado) {
	        return;
	    }
	    super.validate();
		int contadorFecha = 0;
		int contadorLista = 0;
		if (this.items == null || this.items.size()!= 3) {
		    throw new ControlMapValidationException("Error Validacion Fecha Compuesta");
		}
		for (ControlMaps cm : this.items) {
		    cm.validate();
			if (cm instanceof FechaControl) {
				contadorFecha += 1;
			}
			if (cm instanceof ListaControl) {
				contadorLista += 1;
				validarLista((ListaControl<?>) cm);
			}
		}
		if (!(contadorFecha == 2 && contadorLista == 1)) {
			throw new ControlMapValidationException("Error Validacion Fecha Compuesta");
		}
		
		for (ControlMaps control : this.items) {
			control.validate();
		}
		
	}
	

	
	private void validarLista(ListaControl<?> cm) throws ControlMapValidationException {
		int cont = 0;
		if (cm.getItems().isEmpty()) {
			throw new ControlMapValidationException("Error Validacion Lista items de Fecha Compuesta - Lista vacia");
		}
		for (Object object : cm.getItems()) {
			ItemGenericoMaps itemGenerico = (ItemGenericoMaps) object;
			if (esValorZero(itemGenerico.getValor())) {
				cont = cont + 1;
			}
		}
		ItemGenericoMaps ultimoItem = (ItemGenericoMaps) cm.getItems().get(cm.getItems().size() - 1);
		if (cont != 1 || !esValorZero(ultimoItem.getValor())) {
			throw new ControlMapValidationException("Error Validacion Lista items de Fecha Compuesta");
		}
	}
	
	
	private Boolean esValorZero(Object valor) {
	    if(valor == null) 
	        return Boolean.FALSE;
		return "0.0".equals(valor.toString()) || "0".equals(valor.toString());
	}
		


	@Override
    public String valorComprobante() {
	    
	    if (this.items == null || this.items.get(0) == null) {
	        return null;
	    }
		if (esValorZero(valorLista(listaVigencias()))) {

			
//			String fechaDesde = fechaValorComprobante("fecha-desde");
//			String fechaHasta = fechaValorComprobante("fecha-hasta");
			
			List<String> fechasDesdeHasta = obtenerFechasDesdeHasta();
			
			String fechaDesde = fechasDesdeHasta.get(0);
			String fechaHasta = fechasDesdeHasta.get(1);

			if (null == fechaDesde || null == fechaHasta) {
				return "-";
			}

			if (fechaDesde.equals(fechaHasta)) {
				return fechaDesde;
			}
			return fechaDesde + " - " + fechaHasta;
		}
	    if(listaVigencias() != null) {
	        return listaVigencias().valorComprobante();
	    }
	    return null; 
    }

    private Object valorLista(ListaControl<ItemGenericoMaps> lista) {
        if(lista!= null) {
            return lista.itemSeleccionado().getValor();
        }
        return null;
    }
	
	
	@SuppressWarnings("unchecked")
    private ListaControl<ItemGenericoMaps> listaVigencias() {
	    for(ControlMaps control : this.getItems()) {
	        
	        if (control instanceof ListaControl<?>) {
	            return (ListaControl<ItemGenericoMaps>) control;
	        }
	    }
	    return null;
	}
	
	
//	private String fechaValorComprobante(String fecha) {
//	    for(ControlMaps control : this.getItems()) {
//	        
//	        if (control instanceof FechaControl && fecha.equals(control.getNombre())) {
//	            
//	            return ((FechaControl) control).valorComprobante();
//	        }
//	    }
//	    return null;
//	}
	
	private List<String> obtenerFechasDesdeHasta() {
		List<String> fechas = new ArrayList<String>();
		for (ControlMaps control : this.getItems()) {
			if (control instanceof FechaControl) {
				String itemFecha = ((FechaControl) control).valorComprobante();
				fechas.add(itemFecha);
			}
		}
		return fechas;
	}

}
