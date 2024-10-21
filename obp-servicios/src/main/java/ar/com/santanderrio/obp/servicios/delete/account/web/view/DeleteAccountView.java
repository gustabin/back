package ar.com.santanderrio.obp.servicios.delete.account.web.view;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;

public class DeleteAccountView {
	
	private String identificacionCliente;
	private String numeroCuenta;
	private String mensajeFeedback;
	private String mensajeInformativo;
	private String mensaje;
	private String fechaHora;
	private String nroDeComprobante;
	private String nroControl;
	private String tyc;
	private List<Invalidante> invalidantes;

	@JsonIgnore
	private Boolean keepsAccount;

	@JsonIgnore
	private boolean irASucursal;
	
	@JsonIgnore
	private String cuentaAEliminar;

	public List<Invalidante> getInvalidantes() {
		return invalidantes;
	}

	public void setInvalidantes(List<Invalidante> invalidantes) {
		this.invalidantes = invalidantes;
	}

	public String getIdentificacionCliente() {
		return identificacionCliente;
	}

	public void setIdentificacionCliente(String identificacionCliente) {
		this.identificacionCliente = identificacionCliente;
	}

	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public String getMensajeFeedback() {
		return mensajeFeedback;
	}

	public void setMensajeFeedback(String mensajeFeedback) {
		this.mensajeFeedback = mensajeFeedback;
	}

	public String getNroDeComprobante() {
		return nroDeComprobante;
	}

	public void setNroDeComprobante(String nroDeComprobante) {
		this.nroDeComprobante = nroDeComprobante;
	}

	public String getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(String fechaHora) {
		this.fechaHora = fechaHora;
	}

	public String getNroControl() {
		return nroControl;
	}

	public void setNroControl(String nroControl) {
		this.nroControl = nroControl;
	}

	public String getTyc() {
		return tyc;
	}

	public void setTyc(String tyc) {
		this.tyc = tyc;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getMensajeInformativo() {
		return mensajeInformativo;
	}

	public void setMensajeInformativo(String mensajeInformativo) {
		this.mensajeInformativo = mensajeInformativo;
	}

	public String getCuentaAEliminar() {
		return cuentaAEliminar;
	}

	public void setCuentaAEliminar(String cuentaAEliminar) {
		this.cuentaAEliminar = cuentaAEliminar;
	}

	public Boolean getKeepsAccount() {
		return keepsAccount;
	}

	public void setKeepsAccount(Boolean keepsAccount) {
		this.keepsAccount = keepsAccount;
	}

	public boolean getIrASucursal() {
		return irASucursal;
	}

	public void setIrASucursal(boolean irASucursal) {
		this.irASucursal = irASucursal;
	}
}
