package ar.com.santanderrio.obp.servicios.prestamos.dto;

import java.math.BigDecimal;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

@JsonSerialize(include = Inclusion.NON_NULL)
public class EncolamientoRequestDTO {

	private String nup;
	private BigDecimal montoTotal;
	private String canal;
	private String tipoPrestamo;
	private CuentaDTO cuenta;
	private Integer codigoProducto;
	private Integer codigoSubProducto;
	private Integer destinoFondos;
	private String sucursalPaquete;
	private String numeroPaquete;
	private String codigoContable;
	private String riesgoContable;
	private Integer cantidadCuotas;
	private String fechaCierre;
	private BigDecimal tna;
	private String lineaUva;
	private String fechaAprobacion;
	private String fechaFormalizacion;
	private String tipoTasa;
	private String indiceNegociable;
	private String indicadorBonifCuenta;
	private String codigoAgente;
	private String codigoModalidadAcreditacion;
	private String codigoInstrumento; // preaprobado
	private String indicadorIndexacion;
	private String codigoRiesgo;
	private String codigoCondicionAlternativa;
	private String tipoCondicionAlternativa;
	private String tokenSimulacion;
	private String origenFront;

	public String getNup() {
		return nup;
	}

	public void setNup(String nup) {
		this.nup = nup;
	}

	public BigDecimal getMontoTotal() {
		return montoTotal;
	}

	public void setMontoTotal(BigDecimal montoTotal) {
		this.montoTotal = montoTotal;
	}

	public String getCanal() {
		return canal;
	}

	public void setCanal(String canal) {
		this.canal = canal;
	}

	public String getTipoPrestamo() {
		return tipoPrestamo;
	}

	public void setTipoPrestamo(String tipoPrestamo) {
		this.tipoPrestamo = tipoPrestamo;
	}

	public CuentaDTO getCuenta() {
		return cuenta;
	}

	public void setCuenta(CuentaDTO cuenta) {
		this.cuenta = cuenta;
	}

	public Integer getCodigoProducto() {
		return codigoProducto;
	}

	public void setCodigoProducto(Integer codigoProducto) {
		this.codigoProducto = codigoProducto;
	}

	public Integer getCodigoSubProducto() {
		return codigoSubProducto;
	}

	public void setCodigoSubProducto(Integer codigoSubProducto) {
		this.codigoSubProducto = codigoSubProducto;
	}

	public String getSucursalPaquete() {
		return sucursalPaquete;
	}

	public void setSucursalPaquete(String sucursalPaquete) {
		this.sucursalPaquete = sucursalPaquete;
	}

	public String getNumeroPaquete() {
		return numeroPaquete;
	}

	public void setNumeroPaquete(String numeroPaquete) {
		this.numeroPaquete = numeroPaquete;
	}

	public String getCodigoContable() {
		return codigoContable;
	}

	public void setCodigoContable(String codigoContable) {
		this.codigoContable = codigoContable;
	}

	public String getRiesgoContable() {
		return riesgoContable;
	}

	public void setRiesgoContable(String riesgoContable) {
		this.riesgoContable = riesgoContable;
	}

	public Integer getCantidadCuotas() {
		return cantidadCuotas;
	}

	public void setCantidadCuotas(Integer cantidadCuotas) {
		this.cantidadCuotas = cantidadCuotas;
	}

	public String getFechaCierre() {
		return fechaCierre;
	}

	public void setFechaCierre(String fechaCierre) {
		this.fechaCierre = fechaCierre;
	}

	public BigDecimal getTna() {
		return tna;
	}

	public void setTna(BigDecimal tna) {
		this.tna = tna;
	}

	public String getLineaUva() {
		return lineaUva;
	}

	public void setLineaUva(String lineaUva) {
		this.lineaUva = lineaUva;
	}

	public Integer getDestinoFondos() {
		return destinoFondos;
	}

	public void setDestinoFondos(Integer destinoFondos) {
		this.destinoFondos = destinoFondos;
	}

	@Override
	public String toString() {
		return "EncolamientoRequestDTO [nup=" + nup + ", montoTotal=" + montoTotal + ", canal=" + canal
				+ ", tipoPrestamo=" + tipoPrestamo + ", cuenta=" + cuenta + ", codigoProducto=" + codigoProducto
				+ ", codigoSubProducto=" + codigoSubProducto + ", destinoFondos=" + destinoFondos + ", sucursalPaquete="
				+ sucursalPaquete + ", numeroPaquete=" + numeroPaquete + ", codigoContable=" + codigoContable
				+ ", riesgoContable=" + riesgoContable + ", cantidadCuotas=" + cantidadCuotas + ", fechaCierre="
				+ fechaCierre + ", tna=" + tna + ", lineaUva=" + lineaUva + "]";
	}

	public String getFechaAprobacion() {
		return fechaAprobacion;
	}

	public void setFechaAprobacion(String fechaAprobacion) {
		this.fechaAprobacion = fechaAprobacion;
	}

	public String getFechaFormalizacion() {
		return fechaFormalizacion;
	}

	public void setFechaFormalizacion(String fechaFormalizacion) {
		this.fechaFormalizacion = fechaFormalizacion;
	}

	public String getTipoTasa() {
		return tipoTasa;
	}

	public void setTipoTasa(String tipoTasa) {
		this.tipoTasa = tipoTasa;
	}

	public String getIndiceNegociable() {
		return indiceNegociable;
	}

	public void setIndiceNegociable(String indiceNegociable) {
		this.indiceNegociable = indiceNegociable;
	}

	public String getIndicadorBonifCuenta() {
		return indicadorBonifCuenta;
	}

	public void setIndicadorBonifCuenta(String indicadorBonifCuenta) {
		this.indicadorBonifCuenta = indicadorBonifCuenta;
	}

	public String getCodigoAgente() {
		return codigoAgente;
	}

	public void setCodigoAgente(String codigoAgente) {
		this.codigoAgente = codigoAgente;
	}

	public String getCodigoModalidadAcreditacion() {
		return codigoModalidadAcreditacion;
	}

	public void setCodigoModalidadAcreditacion(String codigoModalidadAcreditacion) {
		this.codigoModalidadAcreditacion = codigoModalidadAcreditacion;
	}

	public String getCodigoInstrumento() {
		return codigoInstrumento;
	}

	public void setCodigoInstrumento(String codigoInstrumento) {
		this.codigoInstrumento = codigoInstrumento;
	}

	public String getIndicadorIndexacion() {
		return indicadorIndexacion;
	}

	public void setIndicadorIndexacion(String indicadorIndexacion) {
		this.indicadorIndexacion = indicadorIndexacion;
	}

	public String getCodigoRiesgo() {
		return codigoRiesgo;
	}

	public void setCodigoRiesgo(String codigoRiesgo) {
		this.codigoRiesgo = codigoRiesgo;
	}

	public String getCodigoCondicionAlternativa() {
		return codigoCondicionAlternativa;
	}

	public void setCodigoCondicionAlternativa(String codigoCondicionAlternativa) {
		this.codigoCondicionAlternativa = codigoCondicionAlternativa;
	}

	public String getTipoCondicionAlternativa() {
		return tipoCondicionAlternativa;
	}

	public void setTipoCondicionAlternativa(String tipoCondicionAlternativa) {
		this.tipoCondicionAlternativa = tipoCondicionAlternativa;
	}

	public String getTokenSimulacion() {
		return tokenSimulacion;
	}

	public void setTokenSimulacion(String tokenSimulacion) {
		this.tokenSimulacion = tokenSimulacion;
	}

	public String getOrigenFront() {
		return origenFront;
	}

	public void setOrigenFront(String origenFront) {
		this.origenFront = origenFront;
	}

}
