package ar.com.santanderrio.obp.servicios.tenencias.entity;



import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class DatosRespuestaFondosRimp {
	
	@JsonProperty("Especie")
	private String especie;
	
	@JsonProperty("Cuenta")
	private String cuenta;
	
	@JsonProperty("FechaSolicitud")
	private String fecha;
	
	@JsonProperty("Concepto")
	private String concepto;
	
	@JsonProperty("Comprobante")
	private String comprobante;
	
	@JsonProperty("Cantidad")
	private String cantidad;
	
	@JsonProperty("Cotizacion")
	private String cotizacion;
	
	@JsonProperty("Importe")
	private String importe;
	
	@JsonProperty("Divisa")
	private String divisa;
	
	@JsonProperty("CuentaOperativa")
	private String cuentaOperativa;
	
	@JsonProperty("Sucursal")
	private String sucursal;
	
	@JsonProperty("GastoEntrada")
	private double gastoEntrada;
	
	@JsonProperty("GastoSalida")
	private double gastoSalida;
	
	@JsonProperty("Participantes")
	private List<ParticipantesEntity> participantes;
	
	@JsonProperty("FechaLiquidacion")
	private String fecha_liq;
	

	/**
	 * @return the fecha_liq
	 */
	public String getFecha_liq() {
		return fecha_liq;
	}

	/**
	 * @param fecha_liq the fecha_liq to set
	 */
	public void setFecha_liq(String fecha_liq) {
		this.fecha_liq = fecha_liq;
	}

	/**
	 * @return the gastosEntrada
	 */
	public double getGastoEntrada() {
		return gastoEntrada;
	}

	/**
	 * @param gastosEntrada the gastosEntrada to set
	 */
	public void setGastoEntrada(double gastoEntrada) {
		this.gastoEntrada = gastoEntrada;
	}

	/**
	 * @return the gastosSalida
	 */
	public double getGastoSalida() {
		return gastoSalida;
	}

	/**
	 * @param gastosSalida the gastosSalida to set
	 */
	public void setGastoSalida(double gastoSalida) {
		this.gastoSalida = gastoSalida;
	}

	
	/**
	 * @return the participantes
	 */
	public List<ParticipantesEntity> getParticipantes() {
		return participantes;
	}

	/**
	 * @param participantes the participantes to set
	 */
	public void setParticipantes(List<ParticipantesEntity> participantes) {
		this.participantes = participantes;
	}

	public String getCuentaOperativa() {
		return cuentaOperativa;
	}

	public void setCuentaOperativa(String cuentaOperativa) {
		this.cuentaOperativa = cuentaOperativa;
	}

	public String getSucursal() {
		return sucursal;
	}

	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}

	public String getEspecie() {
		return especie;
	}

	public void setEspecie(String especie) {
		this.especie = especie;
	}

	public String getCuenta() {
		return cuenta;
	}

	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public String getComprobante() {
		return comprobante;
	}

	public void setComprobante(String comprobante) {
		this.comprobante = comprobante;
	}

	public String getCantidad() {
		return cantidad;
	}

	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}

	public String getCotizacion() {
		return cotizacion;
	}

	public void setCotizacion(String cotizacion) {
		this.cotizacion = cotizacion;
	}

	public String getImporte() {
		return importe;
	}

	public void setImporte(String importe) {
		this.importe = importe;
	}

	public String getDivisa() {
		return divisa;
	}

	public void setDivisa(String divisa) {
		this.divisa = divisa;
	}
	
	
	

}
