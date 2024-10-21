package ar.com.santanderrio.obp.servicios.debitoautomatico.entities;

import com.sun.istack.NotNull;

public class ComprobanteAdhesionDebitoAutomatico {

	private String comprobante;
	
	private String importeLimite;
	
	private String medioPago;
	
	/** The nombre medio de pago. */
	private String nombreMedioDePago;
	
	
	private String nombreEmpresa;
	
	private String numeroSocio;
	
	/** The codigo pago electronico. */
	private String codigoPagoElectronico;

	/** The limite. */
	@NotNull
	private String limite;

	/** The fecha hora. */
	private String fechaHora;
	
	/** The pes identificacion. */
	private String pesIdentificacion;
	
	
	public String getPesIdentificacion() {
		return pesIdentificacion;
	}

	public void setPesIdentificacion(String pesIdentificacion) {
		this.pesIdentificacion = pesIdentificacion;
	}

	public String getCodigoPagoElectronico() {
		return codigoPagoElectronico;
	}

	public void setCodigoPagoElectronico(String codigoPagoElectronico) {
		this.codigoPagoElectronico = codigoPagoElectronico;
	}

	public String getLimite() {
		return limite;
	}

	public void setLimite(String limite) {
		this.limite = limite;
	}

	public String getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(String fechaHora) {
		this.fechaHora = fechaHora;
	}

	public String getComprobante() {
		return comprobante;
	}

	public void setComprobante(String comprobante) {
		this.comprobante = comprobante;
	}

	public String getImporteLimite() {
		return importeLimite;
	}

	public void setImporteLimite(String importeLimite) {
		this.importeLimite = importeLimite;
	}

	public String getMedioPago() {
		return medioPago;
	}

	public void setMedioPago(String medioPago) {
		this.medioPago = medioPago;
	}

	public String getNombreEmpresa() {
		return nombreEmpresa;
	}

	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}

	public String getNumeroSocio() {
		return numeroSocio;
	}

	public void setNumeroSocio(String numeroSocio) {
		this.numeroSocio = numeroSocio;
	}
	
	public String getNombreMedioDePago() {
		return nombreMedioDePago;
	}

	public void setNombreMedioDePago(String nombreMedioDePago) {
		this.nombreMedioDePago = nombreMedioDePago;
	}
	
}
