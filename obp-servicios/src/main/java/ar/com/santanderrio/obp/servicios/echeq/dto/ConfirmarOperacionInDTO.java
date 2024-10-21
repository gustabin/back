package ar.com.santanderrio.obp.servicios.echeq.dto;

import java.math.BigDecimal;

import ar.com.santanderrio.obp.servicios.echeq.enums.OperacionEcheqEnum;

public class ConfirmarOperacionInDTO {

	private String jSessionId;
	private String id;
	private String idCesion;

	private OperacionEcheqEnum operacion;

	private Boolean ingresoDesdeRecibidos = Boolean.FALSE;
	private Boolean ingresoDesdeEmitidos = Boolean.FALSE;
	private Boolean ingresoDesdeEndosados = Boolean.FALSE;
	private Boolean ingresoDesdeCedidos = Boolean.FALSE;

	private String motivoRepudio;
	private String motivoAnulacion;

	private String modalidad;
	private String tipoEndoso;
	private String tipoSolicitud;

	private String cuentaSeleccionada;
	private String fechaPago;
	private BigDecimal importe;

	private BeneficiarioDTO beneficiario;
	private String direccion;

	public String getjSessionId() {
		return jSessionId;
	}

	public void setjSessionId(String jSessionId) {
		this.jSessionId = jSessionId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIdCesion() {
		return idCesion;
	}

	public void setIdCesion(String idCesion) {
		this.idCesion = idCesion;
	}

	public OperacionEcheqEnum getOperacion() {
		return operacion;
	}

	public void setOperacion(OperacionEcheqEnum operacion) {
		this.operacion = operacion;
	}

	public Boolean getIngresoDesdeRecibidos() {
		return ingresoDesdeRecibidos;
	}

	public void setIngresoDesdeRecibidos(Boolean ingresoDesdeRecibidos) {
		this.ingresoDesdeRecibidos = ingresoDesdeRecibidos;
	}

	public Boolean getIngresoDesdeEmitidos() {
		return ingresoDesdeEmitidos;
	}

	public void setIngresoDesdeEmitidos(Boolean ingresoDesdeEmitidos) {
		this.ingresoDesdeEmitidos = ingresoDesdeEmitidos;
	}

	public Boolean getIngresoDesdeEndosados() {
		return ingresoDesdeEndosados;
	}

	public void setIngresoDesdeEndosados(Boolean ingresoDesdeEndosados) {
		this.ingresoDesdeEndosados = ingresoDesdeEndosados;
	}

	public Boolean getIngresoDesdeCedidos() {
		return ingresoDesdeCedidos;
	}

	public void setIngresoDesdeCedidos(Boolean ingresoDesdeCedidos) {
		this.ingresoDesdeCedidos = ingresoDesdeCedidos;
	}

	public String getMotivoRepudio() {
		return motivoRepudio;
	}

	public void setMotivoRepudio(String motivoRepudio) {
		this.motivoRepudio = motivoRepudio;
	}

	public String getMotivoAnulacion() {
		return motivoAnulacion;
	}

	public void setMotivoAnulacion(String motivoAnulacion) {
		this.motivoAnulacion = motivoAnulacion;
	}

	public String getModalidad() {
		return modalidad;
	}

	public void setModalidad(String modalidad) {
		this.modalidad = modalidad;
	}

	public String getTipoEndoso() {
		return tipoEndoso;
	}

	public void setTipoEndoso(String tipoEndoso) {
		this.tipoEndoso = tipoEndoso;
	}

	public String getTipoSolicitud() {
		return tipoSolicitud;
	}

	public void setTipoSolicitud(String tipoSolicitud) {
		this.tipoSolicitud = tipoSolicitud;
	}

	public String getCuentaSeleccionada() {
		return cuentaSeleccionada;
	}

	public void setCuentaSeleccionada(String cuentaSeleccionada) {
		this.cuentaSeleccionada = cuentaSeleccionada;
	}

	public String getFechaPago() {
		return fechaPago;
	}

	public void setFechaPago(String fechaPago) {
		this.fechaPago = fechaPago;
	}

	public BigDecimal getImporte() {
		return importe;
	}

	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}

	public BeneficiarioDTO getBeneficiario() {
		return beneficiario;
	}

	public void setBeneficiario(BeneficiarioDTO beneficiario) {
		this.beneficiario = beneficiario;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
}
