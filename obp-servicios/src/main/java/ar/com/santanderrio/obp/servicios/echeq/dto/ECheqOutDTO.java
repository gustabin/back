package ar.com.santanderrio.obp.servicios.echeq.dto;

import java.util.List;

public class ECheqOutDTO {

	private String id;
		
	private String fechaEmision;
	
	private String nombreEmisor;

	private String cuitEmisor;

	private String nombreBeneficiario;

	private String cuitBeneficiario;

	private String estado;
	
	private String numeroCheque;
	
	private String importe;
	
	private String monedaSimbolo;
		
	private String fechaPago;

	private List<String> acciones;
	
	private String mensajeWarning;
	
	private String chequeCaracter;
	
	private String cuentaNumero;
	
	private String cuentaTipo;
	
	private String alias;
	
	private String motivo;

	private List<EndosoDTO> endosos;
	
	private List<CesionDTO> cesiones;

	private String chequeTipo;
	
	private String domicilioEmisor;
	
	private String entidadGirada;
	
	private String domicilioPago;
	
	private String cmc7;

	private String modalidad;
	
	private Boolean aplicaLegal = Boolean.FALSE;

	private List<AvalDTO> avalistas;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFechaEmision() {
		return fechaEmision;
	}

	public void setFechaEmision(String fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getNumeroCheque() {
		return numeroCheque;
	}

	public void setNumeroCheque(String numeroCheque) {
		this.numeroCheque = numeroCheque;
	}

	public String getImporte() {
		return importe;
	}

	public void setImporte(String importe) {
		this.importe = importe;
	}

	public String getFechaPago() {
		return fechaPago;
	}

	public void setFechaPago(String fechaPago) {
		this.fechaPago = fechaPago;
	}

	public List<String> getAcciones() {
		return acciones;
	}

	public void setAcciones(List<String> acciones) {
		this.acciones = acciones;
	}

	public String getMonedaSimbolo() {
		return monedaSimbolo;
	}

	public void setMonedaSimbolo(String monedaSimbolo) {
		this.monedaSimbolo = monedaSimbolo;
	}

	public String getMensajeWarning() {
		return mensajeWarning;
	}

	public void setMensajeWarning(String mensajeWarning) {
		this.mensajeWarning = mensajeWarning;
	}

	public String getChequeCaracter() {
		return chequeCaracter;
	}

	public void setChequeCaracter(String chequeCaracter) {
		this.chequeCaracter = chequeCaracter;
	}

	public String getCuentaNumero() {
		return cuentaNumero;
	}

	public void setCuentaNumero(String cuentaNumero) {
		this.cuentaNumero = cuentaNumero;
	}

	public String getCuentaTipo() {
		return cuentaTipo;
	}

	public void setCuentaTipo(String cuentaTipo) {
		this.cuentaTipo = cuentaTipo;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public List<EndosoDTO> getEndosos() {
		return endosos;
	}

	public void setEndosos(List<EndosoDTO> endosos) {
		this.endosos = endosos;
	}

	public List<CesionDTO> getCesiones() {
		return cesiones;
	}

	public void setCesiones(List<CesionDTO> cesiones) {
		this.cesiones = cesiones;
	}

	public Boolean getAplicaLegal() {
		return aplicaLegal;
	}

	public void setAplicaLegal(Boolean aplicaLegal) {
		this.aplicaLegal = aplicaLegal;
	}

	public String getNombreEmisor() {
		return nombreEmisor;
	}

	public void setNombreEmisor(String nombreEmisor) {
		this.nombreEmisor = nombreEmisor;
	}

	public String getCuitEmisor() {
		return cuitEmisor;
	}

	public void setCuitEmisor(String cuitEmisor) {
		this.cuitEmisor = cuitEmisor;
	}

	public String getNombreBeneficiario() {
		return nombreBeneficiario;
	}

	public void setNombreBeneficiario(String nombreBeneficiario) {
		this.nombreBeneficiario = nombreBeneficiario;
	}

	public String getCuitBeneficiario() {
		return cuitBeneficiario;
	}

	public void setCuitBeneficiario(String cuitBeneficiario) {
		this.cuitBeneficiario = cuitBeneficiario;
	}

	public String getChequeTipo() {
		return chequeTipo;
	}

	public void setChequeTipo(String chequeTipo) {
		this.chequeTipo = chequeTipo;
	}

	public String getDomicilioEmisor() {
		return domicilioEmisor;
	}

	public void setDomicilioEmisor(String domicilioEmisor) {
		this.domicilioEmisor = domicilioEmisor;
	}

	public String getEntidadGirada() {
		return entidadGirada;
	}

	public void setEntidadGirada(String entidadGirada) {
		this.entidadGirada = entidadGirada;
	}

	public String getDomicilioPago() {
		return domicilioPago;
	}

	public void setDomicilioPago(String domicilioPago) {
		this.domicilioPago = domicilioPago;
	}

	public String getCmc7() {
		return cmc7;
	}

	public void setCmc7(String cmc7) {
		this.cmc7 = cmc7;
	}

	public String getModalidad() {
		return modalidad;
	}

	public void setModalidad(String modalidad) {
		this.modalidad = modalidad;
	}

	public List<AvalDTO> getAvalistas() {
		return avalistas;
	}

	public void setAvalistas(List<AvalDTO> avalistas) {
		this.avalistas = avalistas;
	}
}
