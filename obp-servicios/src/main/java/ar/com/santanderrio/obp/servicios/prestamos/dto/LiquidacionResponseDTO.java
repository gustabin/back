package ar.com.santanderrio.obp.servicios.prestamos.dto;

public class LiquidacionResponseDTO extends LiquidacionPrestamoBaseDTO {

	private String tipoCuenta;
	private String sucursalCuenta;
	private String numeroCuenta;
	private String fechaPrimerVencimiento;
	private String importeSolicitado;
	private String importeCuota;
	private String importeNetoAcreditado;
	private String plazo;
	private String cftConImpuestos;
	private String cftSinImpuestos;
	private String tea;
	private String tna;
	private String tipoTasa;
	private String numeroPrestamo;
	private String numeroComprobante;
	private String montoTotalUva;
	private String cuotaPuraUva;
	private String primerCuotaUva;
	private String cotizacionUva;
	private String fechaCotizacionUva;

	public String getTipoCuenta() {
		return tipoCuenta;
	}

	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	public String getSucursalCuenta() {
		return sucursalCuenta;
	}

	public void setSucursalCuenta(String sucursalCuenta) {
		this.sucursalCuenta = sucursalCuenta;
	}

	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public String getFechaPrimerVencimiento() {
		return fechaPrimerVencimiento;
	}

	public void setFechaPrimerVencimiento(String fechaPrimerVencimiento) {
		this.fechaPrimerVencimiento = fechaPrimerVencimiento;
	}

	public String getImporteSolicitado() {
		return importeSolicitado;
	}

	public void setImporteSolicitado(String importeSolicitado) {
		this.importeSolicitado = importeSolicitado;
	}

	public String getImporteCuota() {
		return importeCuota;
	}

	public void setImporteCuota(String importeCuota) {
		this.importeCuota = importeCuota;
	}

	public String getImporteNetoAcreditado() {
		return importeNetoAcreditado;
	}

	public void setImporteNetoAcreditado(String importeNetoAcreditado) {
		this.importeNetoAcreditado = importeNetoAcreditado;
	}

	public String getPlazo() {
		return plazo;
	}

	public void setPlazo(String plazo) {
		this.plazo = plazo;
	}

	public String getCftConImpuestos() {
		return cftConImpuestos;
	}

	public void setCftConImpuestos(String cftConImpuestos) {
		this.cftConImpuestos = cftConImpuestos;
	}

	public String getCftSinImpuestos() {
		return cftSinImpuestos;
	}

	public void setCftSinImpuestos(String cftSinImpuestos) {
		this.cftSinImpuestos = cftSinImpuestos;
	}

	public String getTea() {
		return tea;
	}

	public void setTea(String tea) {
		this.tea = tea;
	}

	public String getTna() {
		return tna;
	}

	public void setTna(String tna) {
		this.tna = tna;
	}

	public String getTipoTasa() {
		return tipoTasa;
	}

	public void setTipoTasa(String tipoTasa) {
		this.tipoTasa = tipoTasa;
	}

	public String getNumeroPrestamo() {
		return numeroPrestamo;
	}

	public void setNumeroPrestamo(String numeroPrestamo) {
		this.numeroPrestamo = numeroPrestamo;
	}

	public String getNumeroComprobante() {
		return numeroComprobante;
	}

	public void setNumeroComprobante(String numeroComprobante) {
		this.numeroComprobante = numeroComprobante;
	}

	public String getMontoTotalUva() {
		return montoTotalUva;
	}

	public void setMontoTotalUva(String montoTotalUva) {
		this.montoTotalUva = montoTotalUva;
	}

	public String getCuotaPuraUva() {
		return cuotaPuraUva;
	}

	public void setCuotaPuraUva(String cuotaPuraUva) {
		this.cuotaPuraUva = cuotaPuraUva;
	}

	public String getPrimerCuotaUva() {
		return primerCuotaUva;
	}

	public void setPrimerCuotaUva(String primerCuotaUva) {
		this.primerCuotaUva = primerCuotaUva;
	}

	public String getCotizacionUva() {
		return cotizacionUva;
	}

	public void setCotizacionUva(String cotizacionUva) {
		this.cotizacionUva = cotizacionUva;
	}

	public String getFechaCotizacionUva() {
		return fechaCotizacionUva;
	}

	public void setFechaCotizacionUva(String fechaCotizacionUva) {
		this.fechaCotizacionUva = fechaCotizacionUva;
	}

}
