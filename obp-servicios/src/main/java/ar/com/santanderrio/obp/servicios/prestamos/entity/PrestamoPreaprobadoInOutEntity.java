package ar.com.santanderrio.obp.servicios.prestamos.entity;

import java.math.BigDecimal;
import java.util.Date;

public class PrestamoPreaprobadoInOutEntity {

	// Campos del prestamo en BD
	private Long id;
	private Long nup;
	private Date fechaInicioVig;
	private Date fechaFinVig;
	private String divisa;
	private Integer cantCuotasMin;
	private Integer cantCuotasMax;
	private BigDecimal importeMin;
	private BigDecimal importeMax;
	private String tipoTasa;
	private BigDecimal tna;
	private BigDecimal cftaSinImp;
	private BigDecimal cftaConImp;
	private BigDecimal entidadUg;
	private BigDecimal productoUg;
	private BigDecimal subproductoUg;
	private String habilitado;
	private String solicitado;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getNup() {
		return nup;
	}

	public void setNup(Long nup) {
		this.nup = nup;
	}

	public Date getFechaInicioVig() {
		return fechaInicioVig;
	}

	public void setFechaInicioVig(Date fechaInicioVig) {
		this.fechaInicioVig = fechaInicioVig;
	}

	public Date getFechaFinVig() {
		return fechaFinVig;
	}

	public void setFechaFinVig(Date fechaFinVig) {
		this.fechaFinVig = fechaFinVig;
	}

	public String getDivisa() {
		return divisa;
	}

	public void setDivisa(String divisa) {
		this.divisa = divisa;
	}

	public Integer getCantCuotasMin() {
		return cantCuotasMin;
	}

	public void setCantCuotasMin(Integer cantCuotasMin) {
		this.cantCuotasMin = cantCuotasMin;
	}

	public Integer getCantCuotasMax() {
		return cantCuotasMax;
	}

	public void setCantCuotasMax(Integer cantCuotasMax) {
		this.cantCuotasMax = cantCuotasMax;
	}

	public BigDecimal getImporteMin() {
		return importeMin;
	}

	public void setImporteMin(BigDecimal importeMin) {
		this.importeMin = importeMin;
	}

	public BigDecimal getImporteMax() {
		return importeMax;
	}

	public void setImporteMax(BigDecimal importeMax) {
		this.importeMax = importeMax;
	}

	public String getTipoTasa() {
		return tipoTasa;
	}

	public void setTipoTasa(String tipoTasa) {
		this.tipoTasa = tipoTasa;
	}

	public BigDecimal getTna() {
		return tna;
	}

	public void setTna(BigDecimal tna) {
		this.tna = tna;
	}

	public BigDecimal getCftaSinImp() {
		return cftaSinImp;
	}

	public void setCftaSinImp(BigDecimal cftaSinImp) {
		this.cftaSinImp = cftaSinImp;
	}

	public BigDecimal getCftaConImp() {
		return cftaConImp;
	}

	public void setCftaConImp(BigDecimal cftaConImp) {
		this.cftaConImp = cftaConImp;
	}

	public BigDecimal getEntidadUg() {
		return entidadUg;
	}

	public void setEntidadUg(BigDecimal entidadUg) {
		this.entidadUg = entidadUg;
	}

	public BigDecimal getProductoUg() {
		return productoUg;
	}

	public void setProductoUg(BigDecimal productoUg) {
		this.productoUg = productoUg;
	}

	public BigDecimal getSubproductoUg() {
		return subproductoUg;
	}

	public void setSubproductoUg(BigDecimal subproductoUg) {
		this.subproductoUg = subproductoUg;
	}

	public String getHabilitado() {
		return habilitado;
	}

	public void setHabilitado(String habilitado) {
		this.habilitado = habilitado;
	}

	public String getSolicitado() {
		return solicitado;
	}

	public void setSolicitado(String solicitado) {
		this.solicitado = solicitado;
	}

}
