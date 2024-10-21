package ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.view;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

@JsonSerialize(include = Inclusion.NON_NULL)
public class ContratosPaqueteTyC {

	private String paquete;
	
	private String cajaAhorroPesos;
	
	private String tarjetaCredito;
	
	private String comisiones;
	

	public String getPaquete() {
		return paquete;
	}

	public void setPaquete(String paquete) {
		this.paquete = paquete;
	}

	public String getCajaAhorroPesos() {
		return cajaAhorroPesos;
	}

	public void setCajaAhorroPesos(String cajaAhorroPesos) {
		this.cajaAhorroPesos = cajaAhorroPesos;
	}

	public String getTarjetaCredito() {
		return tarjetaCredito;
	}

	public void setTarjetaCredito(String tarjetaCredito) {
		this.tarjetaCredito = tarjetaCredito;
	}

	public String getComisiones() {
		return comisiones;
	}

	public void setComisiones(String comisiones) {
		this.comisiones = comisiones;
	}
		
}
