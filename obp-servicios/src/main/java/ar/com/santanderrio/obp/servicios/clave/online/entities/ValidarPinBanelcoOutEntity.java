package ar.com.santanderrio.obp.servicios.clave.online.entities;

import org.beanio.annotation.Field;
import org.beanio.annotation.Record;

@Record
public class ValidarPinBanelcoOutEntity {

	@Field
	private String headerTrama;

	@Field(handlerName = "codigoRetornoExtendidoHandler")
	private String codigoRetornoExtendido;

	
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
	
}
