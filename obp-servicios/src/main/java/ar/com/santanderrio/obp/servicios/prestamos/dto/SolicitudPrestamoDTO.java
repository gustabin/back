package ar.com.santanderrio.obp.servicios.prestamos.dto;

import java.math.BigDecimal;

import ar.com.santanderrio.obp.servicios.prestamos.entity.TipoOfertaEnum;
import ar.com.santanderrio.obp.servicios.prestamos.entity.TipoTasaEnum;

public class SolicitudPrestamoDTO {
	private String nup;
	private BigDecimal montoTotal;
	private TipoOfertaEnum tipoOferta;
	private CuentaDTO cuenta;
	private Integer codigoProducto;
	private Integer codigoSubProducto;
	private String sucursalPaquete;
	private String numeroPaquete;
	private String codigoContable; // preacordado
	private String riesgoContable; // preacordado
	private Integer cantidadCuotas;
	private String fechaPrimerVencimiento;
	private BigDecimal tna;
	private boolean lineaUva;
	private Integer destinoFondos;
	private String codigoInstrumento; // preaprobado
	private String fechaAprobacion; // preaprobado
	private String fechaFormalizacion; // preaprobado
	private TipoTasaEnum tipoTasa;
	private String indiceNegociable; // preaprobado
	private String indicadorIndexacion;
	private String codigoRiesgo;
	private String indicadorBonifCuenta;
	private String codigoAgente;
	private String codigoCondicionAlternativa;
	private String tipoCondicionAlternativa;
	private String codigoModalidadAcreditacion;
	private BigDecimal disponible;
	private String tokenSimulacion;
	private String origenFront;

	public BigDecimal getMontoTotal() {
		return montoTotal;
	}

	public BigDecimal getDisponible() {
		return disponible;
	}

	public void setDisponible(BigDecimal disponible) {
		this.disponible = disponible;
	}

	public void setMontoTotal(BigDecimal montoTotal) {
		this.montoTotal = montoTotal;
	}

	public TipoOfertaEnum getTipoOferta() {
		return tipoOferta;
	}

	public void setTipoOferta(TipoOfertaEnum tipoOferta) {
		this.tipoOferta = tipoOferta;
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

	public String getFechaPrimerVencimiento() {
		return fechaPrimerVencimiento;
	}

	public void setFechaPrimerVencimiento(String fechaPrimerVencimiento) {
		this.fechaPrimerVencimiento = fechaPrimerVencimiento;
	}

	public BigDecimal getTna() {
		return tna;
	}

	public void setTna(BigDecimal tna) {
		this.tna = tna;
	}

	public boolean isLineaUva() {
		return lineaUva;
	}

	public void setLineaUva(boolean lineaUva) {
		this.lineaUva = lineaUva;
	}

	public Integer getDestinoFondos() {
		return destinoFondos;
	}

	public void setDestinoFondos(Integer destinoFondos) {
		this.destinoFondos = destinoFondos;
	}

	public String getCodigoInstrumento() {
		return codigoInstrumento;
	}

	public void setCodigoInstrumento(String codigoInstrumento) {
		this.codigoInstrumento = codigoInstrumento;
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

	public TipoTasaEnum getTipoTasa() {
		return tipoTasa;
	}

	public void setTipoTasa(TipoTasaEnum tipoTasa) {
		this.tipoTasa = tipoTasa;
	}

	public String getIndiceNegociable() {
		return indiceNegociable;
	}

	public void setIndiceNegociable(String indiceNegociable) {
		this.indiceNegociable = indiceNegociable;
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

	public String getCodigoModalidadAcreditacion() {
		return codigoModalidadAcreditacion;
	}

	public void setCodigoModalidadAcreditacion(String codigoModalidadAcreditacion) {
		this.codigoModalidadAcreditacion = codigoModalidadAcreditacion;
	}

	@Override
	public String toString() {
		return "SolicitudPrestamoDTO [montoTotal=" + montoTotal + ", tipoOferta=" + tipoOferta + ", cuenta=" + cuenta
				+ ", codigoProducto=" + codigoProducto + ", codigoSubProducto=" + codigoSubProducto
				+ ", sucursalPaquete=" + sucursalPaquete + ", numeroPaquete=" + numeroPaquete + ", codigoContable="
				+ codigoContable + ", riesgoContable=" + riesgoContable + ", cantidadCuotas=" + cantidadCuotas
				+ ", fechaPrimerVencimiento=" + fechaPrimerVencimiento + ", tna=" + tna + ", lineaUva=" + lineaUva
				+ ", destinoFondos=" + destinoFondos + ", codigoInstrumento=" + codigoInstrumento + ", fechaAprobacion="
				+ fechaAprobacion + ", fechaFormalizacion=" + fechaFormalizacion + ", tipoTasa=" + tipoTasa
				+ ", indiceNegociable=" + indiceNegociable + ", indicadorIndexacion=" + indicadorIndexacion
				+ ", codigoRiesgo=" + codigoRiesgo + ", indicadorBonifCuenta=" + indicadorBonifCuenta
				+ ", codigoAgente=" + codigoAgente + ", codigoCondicionAlternativa=" + codigoCondicionAlternativa
				+ ", tipoCondicionAlternativa=" + tipoCondicionAlternativa + ", codigoModalidadAcreditacion="
				+ codigoModalidadAcreditacion + ", disponible=" + disponible + "]";
	}

	public String getNup() {
		return nup;
	}

	public void setNup(String nup) {
		this.nup = nup;
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
