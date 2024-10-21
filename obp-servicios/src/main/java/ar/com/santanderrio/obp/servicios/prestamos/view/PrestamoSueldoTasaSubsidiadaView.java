package ar.com.santanderrio.obp.servicios.prestamos.view;

import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import ar.com.santanderrio.obp.servicios.pagos.entities.InfoEmpleadoPrestamoTasaSubsidiada;

@JsonSerialize(include = Inclusion.NON_NULL)
public class PrestamoSueldoTasaSubsidiadaView {

	private List<InfoEmpleadoPrestamoTasaSubsidiada> listaEmpleados;

	private String legalInicio;

	private String legalConfiguracion;

	private String legalConformidad;

	private String mensajeAyudaCbu;

	private String mensajeInformativoConfiguracion;

	private String mensajeCbusIncompletos;

	private List<CuentaView> listaCuentasPesos;

	private String mail;

	private String tasaInteres;

	private Boolean empleadoSinCBU = Boolean.FALSE;

	private String montoFormateado;

	private String mensajeMontoTotalPrestamo;

	private String cbuParaCargar;

	private String cuitParaCargar;

	private String idParaCargar;

	private CuentaView cuentaSeleccionada;

	private String nroComprobante;

	private String mensajeFeedback;

	private int cantEmpleadosSinCbu;

	private String mensajeFeedbackOKCBUAgregado;

	private String tyc;

	private String mensajeLegalTasasConfirmacion;
	
	private String mensajeInformativoConfiguracionSinCbu;
	
	private String fechaHora;
	
	private String tipoDeATP;
	

	public List<InfoEmpleadoPrestamoTasaSubsidiada> getListaEmpleados() {
		return listaEmpleados;
	}

	public void setListaEmpleados(List<InfoEmpleadoPrestamoTasaSubsidiada> listaEmpleados) {
		this.listaEmpleados = listaEmpleados;
	}

	public String getLegalInicio() {
		return legalInicio;
	}

	public void setLegalInicio(String legalInicio) {
		this.legalInicio = legalInicio;
	}

	public String getLegalConfiguracion() {
		return legalConfiguracion;
	}

	public void setLegalConfiguracion(String legalConfiguracion) {
		this.legalConfiguracion = legalConfiguracion;
	}

	public String getLegalConformidad() {
		return legalConformidad;
	}

	public void setLegalConformidad(String legalConformidad) {
		this.legalConformidad = legalConformidad;
	}

	public String getMensajeAyudaCbu() {
		return mensajeAyudaCbu;
	}

	public void setMensajeAyudaCbu(String mensajeAyudaCbu) {
		this.mensajeAyudaCbu = mensajeAyudaCbu;
	}

	public String getMensajeInformativoConfiguracion() {
		return mensajeInformativoConfiguracion;
	}

	public void setMensajeInformativoConfiguracion(String mensajeInformativoConfiguracion) {
		this.mensajeInformativoConfiguracion = mensajeInformativoConfiguracion;
	}

	public String getMensajeCbusIncompletos() {
		return mensajeCbusIncompletos;
	}

	public void setMensajeCbusIncompletos(String mensajeCbusIncompletos) {
		this.mensajeCbusIncompletos = mensajeCbusIncompletos;
	}

	public List<CuentaView> getListaCuentasPesos() {
		return listaCuentasPesos;
	}

	public void setListaCuentasPesos(List<CuentaView> listaCuentasPesos) {
		this.listaCuentasPesos = listaCuentasPesos;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getTasaInteres() {
		return tasaInteres;
	}

	public void setTasaInteres(String tasaInteres) {
		this.tasaInteres = tasaInteres;
	}

	public Boolean getEmpleadoSinCBU() {
		return empleadoSinCBU;
	}

	public void setEmpleadoSinCBU(Boolean empleadoSinCBU) {
		this.empleadoSinCBU = empleadoSinCBU;
	}

	public String getMontoFormateado() {
		return montoFormateado;
	}

	public void setMontoFormateado(String montoFormateado) {
		this.montoFormateado = montoFormateado;
	}

	public String getMensajeMontoTotalPrestamo() {
		return mensajeMontoTotalPrestamo;
	}

	public void setMensajeMontoTotalPrestamo(String mensajeMontoTotalPrestamo) {
		this.mensajeMontoTotalPrestamo = mensajeMontoTotalPrestamo;
	}

	public CuentaView getCuentaSeleccionada() {
		return cuentaSeleccionada;
	}

	public void setCuentaSeleccionada(CuentaView cuentaSeleccionada) {
		this.cuentaSeleccionada = cuentaSeleccionada;
	}

	public String getNroComprobante() {
		return nroComprobante;
	}

	public void setNroComprobante(String nroComprobante) {
		this.nroComprobante = nroComprobante;
	}

	public String getMensajeFeedback() {
		return mensajeFeedback;
	}

	public void setMensajeFeedback(String mensajeFeedback) {
		this.mensajeFeedback = mensajeFeedback;
	}

	public String getCbuParaCargar() {
		return cbuParaCargar;
	}

	public void setCbuParaCargar(String cbuParaCargar) {
		this.cbuParaCargar = cbuParaCargar;
	}

	public String getCuitParaCargar() {
		return cuitParaCargar;
	}

	public void setCuitParaCargar(String cuitParaCargar) {
		this.cuitParaCargar = cuitParaCargar;
	}

	public String getIdParaCargar() {
		return idParaCargar;
	}

	public void setIdParaCargar(String idParaCargar) {
		this.idParaCargar = idParaCargar;
	}

	public int getCantEmpleadosSinCbu() {
		return cantEmpleadosSinCbu;
	}

	public void setCantEmpleadosSinCbu(int cantEmpleadosSinCbu) {
		this.cantEmpleadosSinCbu = cantEmpleadosSinCbu;
	}

	public String getMensajeFeedbackOKCBUAgregado() {
		return mensajeFeedbackOKCBUAgregado;
	}

	public void setMensajeFeedbackOKCBUAgregado(String mensajeFeedbackOKCBUAgregado) {
		this.mensajeFeedbackOKCBUAgregado = mensajeFeedbackOKCBUAgregado;
	}

	public String getTyc() {
		return tyc;
	}

	public void setTyc(String tyc) {
		this.tyc = tyc;
	}

	public String getMensajeLegalTasasConfirmacion() {
		return mensajeLegalTasasConfirmacion;
	}

	public void setMensajeLegalTasasConfirmacion(String mensajeLegalTasasConfirmacion) {
		this.mensajeLegalTasasConfirmacion = mensajeLegalTasasConfirmacion;
	}

	public String getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(String fechaHora) {
		this.fechaHora = fechaHora;
	}


	public String getMensajeInformativoConfiguracionSinCbu() {
		return mensajeInformativoConfiguracionSinCbu;
	}

	public void setMensajeInformativoConfiguracionSinCbu(String mensajeInformativoConfiguracionSinCbu) {
		this.mensajeInformativoConfiguracionSinCbu = mensajeInformativoConfiguracionSinCbu;
	}

	public String getTipoDeATP() {
		return tipoDeATP;
	}

	public void setTipoDeATP(String tipoDeATP) {
		this.tipoDeATP = tipoDeATP;
	}
	
}
