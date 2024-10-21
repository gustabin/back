package ar.com.santanderrio.obp.servicios.clave.online.entities;

import org.beanio.annotation.Field;
import org.beanio.annotation.Record;

@Record
public class SeleccionTarjetaBanelcoOutEntity {

	@Field
	private String headerTrama;

	@Field(handlerName = "codigoRetornoExtendidoHandler")
	private String codigoRetornoExtendido;
	
	@Field
	private String numeroTarjeta;

	
	public String getHeaderTrama() {
		return headerTrama;
	}

	public void setHeaderTrama(String headerTrama) {
		this.headerTrama = headerTrama;
	}

	public String getCodigoRetornoExtendido() {
		return codigoRetornoExtendido;
	}

	public void setCodigoRetornoExtendido(String codigoRetornoExtendido) {
		this.codigoRetornoExtendido = codigoRetornoExtendido;
	}

	public String getNumeroTarjeta() {
		return numeroTarjeta;
	}

	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}
	
}
