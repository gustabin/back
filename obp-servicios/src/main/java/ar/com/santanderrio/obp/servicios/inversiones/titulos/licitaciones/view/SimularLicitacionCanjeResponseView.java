package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view;

import java.util.List;

import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.pagos.entities.Interviniente;

public class SimularLicitacionCanjeResponseView {

	/** Nro cuenta titulos obtenido a partir del response del servicio */
	private String cuentaTitulos;

	/** Intervinientes para la cuenta titulos */
	private List<Interviniente> intervinientes;

	/** Datos del servicio */
	private String cuentaOperativa;
	private int tipoCuentaOperativa;
	private String descripcionEspecie;
	private String monedaEspecie;
	private String tramo;
	private String especieDestino;
	private String monedaEspecieDestino;
	private String valorNominalACanjear;
	private String tipoPrecio;
	private String tipoPrecioDato;
	private String importeADebitar;
	private String fechaDebitoCuenta;
	private String fechaCierre;
	private String fechaAdjudicacion;
	private String fechaLiquidacion;
	private String tipoCambio;
	private long numeroOrden;
	private String email;
	private String comisiones;
	private String impuestos;
	private String legal;
	private String legalCanal;
	private String moneda;

	private ReporteView archivoCondiciones;

	/** Atributos para eri */
	private String cabecera;
	private String pie;

	private String disclaimer;

	public String getCuentaTitulos() {
		return cuentaTitulos;
	}

	public void setCuentaTitulos(String cuentaTitulos) {
		this.cuentaTitulos = cuentaTitulos;
	}

	public List<Interviniente> getIntervinientes() {
		return intervinientes;
	}

	public void setIntervinientes(List<Interviniente> intervinientes) {
		this.intervinientes = intervinientes;
	}

	public String getCuentaOperativa() {
		return cuentaOperativa;
	}

	public void setCuentaOperativa(String cuentaOperativa) {
		this.cuentaOperativa = cuentaOperativa;
	}

	public String getDescripcionEspecie() {
		return descripcionEspecie;
	}

	public void setDescripcionEspecie(String descripcionEspecie) {
		this.descripcionEspecie = descripcionEspecie;
	}

	public String getMonedaEspecie() {
		return monedaEspecie;
	}

	public void setMonedaEspecie(String monedaEspecie) {
		this.monedaEspecie = monedaEspecie;
	}

	public String getTramo() {
		return tramo;
	}

	public void setTramo(String tramo) {
		this.tramo = tramo;
	}

	public String getEspecieDestino() {
		return especieDestino;
	}

	public void setEspecieDestino(String especieDestino) {
		this.especieDestino = especieDestino;
	}

	public String getMonedaEspecieDestino() {
		return monedaEspecieDestino;
	}

	public void setMonedaEspecieDestino(String monedaEspecieDestino) {
		this.monedaEspecieDestino = monedaEspecieDestino;
	}

	public String getValorNominalACanjear() {
		return valorNominalACanjear;
	}

	public void setValorNominalACanjear(String valorNominalACanjear) {
		this.valorNominalACanjear = valorNominalACanjear;
	}

	public String getTipoPrecio() {
		return tipoPrecio;
	}

	public void setTipoPrecio(String tipoPrecio) {
		this.tipoPrecio = tipoPrecio;
	}

	public String getTipoPrecioDato() {
		return tipoPrecioDato;
	}

	public void setTipoPrecioDato(String tipoPrecioDato) {
		this.tipoPrecioDato = tipoPrecioDato;
	}

	public String getImporteADebitar() {
		return importeADebitar;
	}

	public void setImporteADebitar(String importeADebitar) {
		this.importeADebitar = importeADebitar;
	}

	public String getFechaDebitoCuenta() {
		return fechaDebitoCuenta;
	}

	public void setFechaDebitoCuenta(String fechaDebitoCuenta) {
		this.fechaDebitoCuenta = fechaDebitoCuenta;
	}

	public String getFechaCierre() {
		return fechaCierre;
	}

	public void setFechaCierre(String fechaCierre) {
		this.fechaCierre = fechaCierre;
	}

	public String getFechaAdjudicacion() {
		return fechaAdjudicacion;
	}

	public void setFechaAdjudicacion(String fechaAdjudicacion) {
		this.fechaAdjudicacion = fechaAdjudicacion;
	}

	public String getFechaLiquidacion() {
		return fechaLiquidacion;
	}

	public void setFechaLiquidacion(String fechaLiquidacion) {
		this.fechaLiquidacion = fechaLiquidacion;
	}

	public String getTipoCambio() {
		return tipoCambio;
	}

	public void setTipoCambio(String tipoCambio) {
		this.tipoCambio = tipoCambio;
	}

	public long getNumeroOrden() {
		return numeroOrden;
	}

	public void setNumeroOrden(long numeroOrden) {
		this.numeroOrden = numeroOrden;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getComisiones() {
		return comisiones;
	}

	public void setComisiones(String comisiones) {
		this.comisiones = comisiones;
	}

	public String getImpuestos() {
		return impuestos;
	}

	public void setImpuestos(String impuestos) {
		this.impuestos = impuestos;
	}

	public String getLegal() {
		return legal;
	}

	public void setLegal(String legal) {
		this.legal = legal;
	}

	public String getLegalCanal() {
		return legalCanal;
	}

	public void setLegalCanal(String legalCanal) {
		this.legalCanal = legalCanal;
	}

	public ReporteView getArchivoCondiciones() {
		return archivoCondiciones;
	}

	public void setArchivoCondiciones(ReporteView archivoCondiciones) {
		this.archivoCondiciones = archivoCondiciones;
	}

	public String getCabecera() {
		return cabecera;
	}

	public void setCabecera(String cabecera) {
		this.cabecera = cabecera;
	}

	public String getPie() {
		return pie;
	}

	public void setPie(String pie) {
		this.pie = pie;
	}

	public int getTipoCuentaOperativa() {
		return tipoCuentaOperativa;
	}

	public void setTipoCuentaOperativa(int tipoCuentaOperativa) {
		this.tipoCuentaOperativa = tipoCuentaOperativa;
	}

	public String getDisclaimer() {
		return disclaimer;
	}

	public void setDisclaimer(String disclaimer) {
		this.disclaimer = disclaimer;
	}

	public String getMoneda() {
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

}
