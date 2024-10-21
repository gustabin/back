package ar.com.santanderrio.obp.servicios.prestamos.view;

import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

@JsonSerialize(include = Inclusion.NON_NULL)
public class CancelacionPrestamoView {

	private String montoTotal;
	private String capital;
	private String intereses;
	private String iva;
	private String ingresosBrutos;
	private String otrosImpuestos;
	private String comisiones;
	private String gastos;
	private String seguro;
	private String cotizacionCoeficiente;
	private String fechaCotizacionCoeficiente;
	private String capitalUVA;
	private String interesUVA;
	private String importeTotalUVA;
	private Boolean mostrarPrimerStack;
	private List<CuentaView> listaCuentas;
	private String mensajeFeedbackOK;
	private String numeroComprobante;
	private String fechaHora;
	private String mensajeInformativo;
	private String legalOtrosImpuestos;

	//Dont use this, only for backwards compatibility
	public CancelacionPrestamoView() {}

	private CancelacionPrestamoView(Builder builder) {
		this.montoTotal = builder.montoTotal;
		this.capital = builder.capital;
		this.intereses = builder.intereses;
		this.iva = builder.iva;
		this.ingresosBrutos = builder.ingresosBrutos;
		this.otrosImpuestos = builder.otrosImpuestos;
		this.comisiones = builder.comisiones;
		this.gastos = builder.gastos;
		this.seguro = builder.seguro;
		this.cotizacionCoeficiente = builder.cotizacionCoeficiente;
		this.fechaCotizacionCoeficiente = builder.fechaCotizacionCoeficiente;
		this.capitalUVA = builder.capitalUVA;
		this.interesUVA = builder.interesUVA;
		this.importeTotalUVA = builder.importeTotalUVA;
		this.mostrarPrimerStack = builder.mostrarPrimerStack;
		this.listaCuentas = builder.listaCuentas;
		this.mensajeFeedbackOK = builder.mensajeFeedbackOK;
		this.numeroComprobante = builder.numeroComprobante;
		this.fechaHora = builder.fechaHora;
		this.mensajeInformativo = builder.mensajeInformativo;
		this.legalOtrosImpuestos = builder.legalOtrosImpuestos;
	}

	public static Builder builder () {
		return new Builder();
	}
	
	public String getMontoTotal() {
		return montoTotal;
	}

	public void setMontoTotal(String montoTotal) {
		this.montoTotal = montoTotal;
	}

	public String getCapital() {
		return capital;
	}

	public void setCapital(String capital) {
		this.capital = capital;
	}

	public String getIntereses() {
		return intereses;
	}

	public void setIntereses(String intereses) {
		this.intereses = intereses;
	}

	public Boolean getMostrarPrimerStack() {
		return mostrarPrimerStack;
	}

	public void setMostrarPrimerStack(Boolean mostrarPrimerStack) {
		this.mostrarPrimerStack = mostrarPrimerStack;
	}

	public List<CuentaView> getListaCuentas() {
		return listaCuentas;
	}

	public void setListaCuentas(List<CuentaView> listaCuentas) {
		this.listaCuentas = listaCuentas;
	}

	public String getMensajeFeedbackOK() {
		return mensajeFeedbackOK;
	}

	public void setMensajeFeedbackOK(String mensajeFeedbackOK) {
		this.mensajeFeedbackOK = mensajeFeedbackOK;
	}

	public String getNumeroComprobante() {
		return numeroComprobante;
	}

	public void setNumeroComprobante(String numeroComprobante) {
		this.numeroComprobante = numeroComprobante;
	}

	public String getIva() {
		return iva;
	}

	public void setIva(String iva) {
		this.iva = iva;
	}

	public String getIngresosBrutos() {
		return ingresosBrutos;
	}

	public void setIngresosBrutos(String ingresosBrutos) {
		this.ingresosBrutos = ingresosBrutos;
	}

	public String getOtrosImpuestos() {
		return otrosImpuestos;
	}

	public void setOtrosImpuestos(String otrosImpuestos) {
		this.otrosImpuestos = otrosImpuestos;
	}

	public String getComisiones() {
		return comisiones;
	}

	public void setComisiones(String comisiones) {
		this.comisiones = comisiones;
	}

	public String getGastos() {
		return gastos;
	}

	public void setGastos(String gastos) {
		this.gastos = gastos;
	}

	public String getSeguro() {
		return seguro;
	}

	public void setSeguro(String seguro) {
		this.seguro = seguro;
	}

	public String getCotizacionCoeficiente() {
		return cotizacionCoeficiente;
	}

	public void setCotizacionCoeficiente(String cotizacionCoeficiente) {
		this.cotizacionCoeficiente = cotizacionCoeficiente;
	}

	public String getFechaCotizacionCoeficiente() {
		return fechaCotizacionCoeficiente;
	}

	public void setFechaCotizacionCoeficiente(String fechaCotizacionCoeficiente) {
		this.fechaCotizacionCoeficiente = fechaCotizacionCoeficiente;
	}

	public String getCapitalUVA() {
		return capitalUVA;
	}

	public void setCapitalUVA(String capitalUVA) {
		this.capitalUVA = capitalUVA;
	}

	public String getInteresUVA() {
		return interesUVA;
	}

	public void setInteresUVA(String interesUVA) {
		this.interesUVA = interesUVA;
	}

	public String getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(String fechaHora) {
		this.fechaHora = fechaHora;
	}

	public String getImporteTotalUVA() {
		return importeTotalUVA;
	}

	public void setImporteTotalUVA(String importeTotalUVA) {
		this.importeTotalUVA = importeTotalUVA;
	}

	public String getMensajeInformativo() {
		return mensajeInformativo;
	}

	public void setMensajeInformativo() {
		this.mensajeInformativo = mensajeInformativo;
	}

	public String getLegalOtrosImpuestos() {
		return legalOtrosImpuestos;
	}

	public void setLegalOtrosImpuestos(String legalOtrosImpuestos) {
		this.legalOtrosImpuestos = legalOtrosImpuestos;
	}

	public static class Builder {
		private String montoTotal;
		private String capital;
		private String intereses;
		private String iva;
		private String ingresosBrutos;
		private String otrosImpuestos;
		private String comisiones;
		private String gastos;
		private String seguro;
		private String fechaCotizacionCoeficiente;
		private String cotizacionCoeficiente;
		private String capitalUVA;
		private String interesUVA;
		private String importeTotalUVA;
		private Boolean mostrarPrimerStack;
		private List<CuentaView> listaCuentas;
		private String mensajeFeedbackOK;
		private String numeroComprobante;
		private String fechaHora;
		private String mensajeInformativo;
		public String legalOtrosImpuestos;

		public Builder montoTotal(String montoTotal) {
			this.montoTotal = montoTotal;
			return this;
		}

		public Builder capital(String capital) {
			this.capital = capital;
			return this;
		}

		public Builder intereses(String intereses) {
			this.intereses = intereses;
			return this;
		}

		public Builder iva(String iva) {
			this.iva = iva;
			return this;
		}

		public Builder ingresosBrutos(String ingresosBrutos) {
			this.ingresosBrutos = ingresosBrutos;
			return this;
		}

		public Builder otrosImpuestos(String otrosImpuestos) {
			this.otrosImpuestos = otrosImpuestos;
			return this;
		}

		public Builder comisiones(String comisiones) {
			this.comisiones = comisiones;
			return this;
		}

		public Builder gastos(String gastos) {
			this.gastos = gastos;
			return this;
		}

		public Builder seguro(String seguro) {
			this.seguro = seguro;
			return this;
		}

		public Builder cotizacionCoeficiente(String cotizacionCoeficiente) {
			this.cotizacionCoeficiente = cotizacionCoeficiente;
			return this;
		}

		public Builder fechaCotizacionCoeficiente(String fechaCotizacionCoeficiente) {
			this.fechaCotizacionCoeficiente = fechaCotizacionCoeficiente;
			return this;
		}

		public Builder capitalUVA(String capitalUVA) {
			this.capitalUVA = capitalUVA;
			return this;
		}

		public Builder interesUVA(String interesUVA) {
			this.interesUVA = interesUVA;
			return this;
		}

		public Builder importeTotalUVA(String importeTotalUVA) {
			this.importeTotalUVA = importeTotalUVA;
			return this;
		}

		public Builder mostrarPrimerStack(Boolean mostrarPrimerStack) {
			this.mostrarPrimerStack = mostrarPrimerStack;
			return this;
		}

		public Builder listaCuentas(List<CuentaView> listaCuentas) {
			this.listaCuentas = listaCuentas;
			return this;
		}

		public Builder mensajeFeedbackOK(String mensajeFeedbackOK) {
			this.mensajeFeedbackOK = mensajeFeedbackOK;
			return this;
		}

		public Builder numeroComprobante(String numeroComprobante) {
			this.numeroComprobante = numeroComprobante;
			return this;
		}

		public Builder fechaHora(String fechaHora) {
			this.fechaHora = fechaHora;
			return this;
		}

		public Builder mensajeInformativo(String mensajeInformativo) {
			this.mensajeInformativo = mensajeInformativo;
			return this;
		}
		
		public Builder legalOtrosImpuestos(String legal) {
			this.legalOtrosImpuestos = legal;
			return this;
		}

		public CancelacionPrestamoView build() {
			return new CancelacionPrestamoView(this);
		}
	}
}
