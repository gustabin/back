package ar.com.santanderrio.obp.servicios.adhesionwomen.entities;

import java.util.ArrayList;
import java.util.List;

import org.beanio.annotation.Field;
import org.beanio.annotation.Record;
import org.beanio.annotation.Segment;

@Record
public class TarjetaWomenEntity {

	@Field
	private String headerTrama;
	
	@Field(handlerName = "codigoRetornoExtendidoHandler")
	private String codigoRetorno;
	
	@Field
	private String claveReenganche;
	
	@Field
	private String hayMasDatos;
	
	@Field
	private String tipoCuenta;
	
	@Field
	private String numeroCuentaTC;
	
	@Field
	private Long cantidadElementosEnviados;
	
	@Segment(occursRef = "cantidadElementosEnviados")
	private List<DatosTarjetaWomenEntity> listaTarjetas = new ArrayList<DatosTarjetaWomenEntity>();

	
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

	public String getClaveReenganche() {
		return claveReenganche;
	}

	public void setClaveReenganche(String claveReenganche) {
		this.claveReenganche = claveReenganche;
	}

	public String getHayMasDatos() {
		return hayMasDatos;
	}

	public void setHayMasDatos(String hayMasDatos) {
		this.hayMasDatos = hayMasDatos;
	}

	public String getTipoCuenta() {
		return tipoCuenta;
	}

	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	public String getNumeroCuentaTC() {
		return numeroCuentaTC;
	}

	public void setNumeroCuentaTC(String numeroCuentaTC) {
		this.numeroCuentaTC = numeroCuentaTC;
	}

	public Long getCantidadElementosEnviados() {
		return cantidadElementosEnviados;
	}

	public void setCantidadElementosEnviados(Long cantidadElementosEnviados) {
		this.cantidadElementosEnviados = cantidadElementosEnviados;
	}

	public List<DatosTarjetaWomenEntity> getListaTarjetas() {
		return listaTarjetas;
	}

	public void setListaTarjetas(List<DatosTarjetaWomenEntity> listaTarjetas) {
		this.listaTarjetas = listaTarjetas;
	}
	
}
