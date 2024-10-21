package ar.com.santanderrio.obp.servicios.segmento.dto;

import java.util.ArrayList;
import java.util.List;

import org.beanio.annotation.Field;
import org.beanio.annotation.Record;
import org.beanio.annotation.Segment;

@Record
public class CajaSeguridadEntity {

	@Field
	private String headerTrama;
	
	@Field(handlerName = "codigoRetornoExtendidoHandler")
	private String codigoRetorno;
	
	@Field
	private String indicadorRellamada;
	
	@Field
	private String claveRellamada;
	
	@Field
	private Long cantidadRegistros;
	
	@Segment(occursRef = "cantidadRegistros")
	private List<CajaSeguridadInformacion> listaCajas = new ArrayList<CajaSeguridadInformacion>();

	private boolean hayError = false;
	
	
	public String getHeaderTrama() {
		return headerTrama;
	}

	public void setHeaderTrama(String headerTrama) {
		this.headerTrama = headerTrama;
	}

	public String getCodigoRetorno() {
		return codigoRetorno;
	}

	public void setCodigoRetorno(String codigoRetorno) {
		this.codigoRetorno = codigoRetorno;
	}

	public String getIndicadorRellamada() {
		return indicadorRellamada;
	}

	public void setIndicadorRellamada(String indicadorRellamada) {
		this.indicadorRellamada = indicadorRellamada;
	}

	public String getClaveRellamada() {
		return claveRellamada;
	}

	public void setClaveRellamada(String claveRellamada) {
		this.claveRellamada = claveRellamada;
	}

	public Long getCantidadRegistros() {
		return cantidadRegistros;
	}

	public void setCantidadRegistros(Long cantidadRegistros) {
		this.cantidadRegistros = cantidadRegistros;
	}

	public List<CajaSeguridadInformacion> getListaCajas() {
		return listaCajas;
	}

	public void setListaCajas(List<CajaSeguridadInformacion> listaCajas) {
		this.listaCajas = listaCajas;
	}

	public boolean isHayError() {
		return hayError;
	}

	public void setHayError(boolean hayError) {
		this.hayError = hayError;
	}
		
}
