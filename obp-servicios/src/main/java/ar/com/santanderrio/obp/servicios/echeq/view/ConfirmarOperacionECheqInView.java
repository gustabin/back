package ar.com.santanderrio.obp.servicios.echeq.view;

import ar.com.santanderrio.obp.servicios.echeq.enums.OperacionEcheqEnum;

public class ConfirmarOperacionECheqInView {

	private String id;

	private OperacionEcheqEnum operacion;

	private String cuentaSeleccionada;

	private Boolean ingresoDesdeRecibidos = Boolean.FALSE;

	private Boolean ingresoDesdeEmitidos = Boolean.FALSE;

	private Boolean ingresoDesdeEndosados = Boolean.FALSE;
	
	private Boolean ingresoDesdeCedidos = Boolean.FALSE;

	private String motivoRepudio;

	private String motivoAnulacion;

	private String jSessionId;
	
	private String importe;

	private String direccion;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public OperacionEcheqEnum getOperacion() {
		return operacion;
	}

	public void setOperacion(OperacionEcheqEnum operacion) {
		this.operacion = operacion;
	}

	public String getCuentaSeleccionada() {
		return cuentaSeleccionada;
	}

	public void setCuentaSeleccionada(String cuentaSeleccionada) {
		this.cuentaSeleccionada = cuentaSeleccionada;
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

	public String getjSessionId() {
		return jSessionId;
	}

	public void setjSessionId(String jSessionId) {
		this.jSessionId = jSessionId;
	}

	public String getImporte() {
		return importe;
	}

	public void setImporte(String importe) {
		this.importe = importe;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
}
