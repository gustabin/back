package ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.view;

import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

@JsonSerialize(include = Inclusion.NON_NULL)
public class PromesaPagoView {

	private String mensajePromesaPagoVencida;

	private String montoTotalAdeudado;
	
	private String mensajeDescripcion;
	
	private String ayudaPromesaPago;
	
	private String ayudaDescubierto;
	
	private String mensajeErrorDeudas;
	
	private List<DeudaProductoPromesaPagoView> listaDeudas;
	
	private Boolean errorParcial = Boolean.FALSE;

	private Boolean errorTotal = Boolean.FALSE;
	
	private Boolean mostrarBotonPromesaPago = Boolean.TRUE;
	
	private List<FechaPagoPP> listaFechasPago;
	
	private double doubleMontoTotalAdeudado;
	
	
	
	public String getMensajePromesaPagoVencida() {
		return mensajePromesaPagoVencida;
	}

	public void setMensajePromesaPagoVencida(String mensajePromesaPagoVencida) {
		this.mensajePromesaPagoVencida = mensajePromesaPagoVencida;
	}

	public String getMontoTotalAdeudado() {
		return montoTotalAdeudado;
	}

	public void setMontoTotalAdeudado(String montoTotalAdeudado) {
		this.montoTotalAdeudado = montoTotalAdeudado;
	}

	public String getMensajeDescripcion() {
		return mensajeDescripcion;
	}

	public void setMensajeDescripcion(String mensajeDescripcion) {
		this.mensajeDescripcion = mensajeDescripcion;
	}

	public String getAyudaPromesaPago() {
		return ayudaPromesaPago;
	}

	public void setAyudaPromesaPago(String ayudaPromesaPago) {
		this.ayudaPromesaPago = ayudaPromesaPago;
	}

	public String getAyudaDescubierto() {
		return ayudaDescubierto;
	}

	public void setAyudaDescubierto(String ayudaDescubierto) {
		this.ayudaDescubierto = ayudaDescubierto;
	}

	public List<DeudaProductoPromesaPagoView> getListaDeudas() {
		return listaDeudas;
	}

	public void setListaDeudas(List<DeudaProductoPromesaPagoView> listaDeudas) {
		this.listaDeudas = listaDeudas;
	}

	public Boolean getErrorParcial() {
		return errorParcial;
	}

	public void setErrorParcial(Boolean errorParcial) {
		this.errorParcial = errorParcial;
	}

	public String getMensajeErrorDeudas() {
		return mensajeErrorDeudas;
	}

	public void setMensajeErrorDeudas(String mensajeErrorDeudas) {
		this.mensajeErrorDeudas = mensajeErrorDeudas;
	}

	public Boolean getMostrarBotonPromesaPago() {
		return mostrarBotonPromesaPago;
	}

	public void setMostrarBotonPromesaPago(Boolean mostrarBotonPromesaPago) {
		this.mostrarBotonPromesaPago = mostrarBotonPromesaPago;
	}

	public Boolean getErrorTotal() {
		return errorTotal;
	}

	public void setErrorTotal(Boolean errorTotal) {
		this.errorTotal = errorTotal;
	}

	public List<FechaPagoPP> getListaFechasPago() {
		return listaFechasPago;
	}

	public void setListaFechasPago(List<FechaPagoPP> listaFechasPago) {
		this.listaFechasPago = listaFechasPago;
	}

	public double getDoubleMontoTotalAdeudado() {
		return doubleMontoTotalAdeudado;
	}

	public void setDoubleMontoTotalAdeudado(double doubleMontoTotalAdeudado) {
		this.doubleMontoTotalAdeudado = doubleMontoTotalAdeudado;
	}	
						
}
