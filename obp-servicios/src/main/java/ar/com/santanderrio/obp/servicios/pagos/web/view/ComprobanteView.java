/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.web.view;

import java.io.FileNotFoundException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.util.ResourceUtils;

import ar.com.santanderrio.obp.servicios.comprobantes.web.view.ReportComprobanteView;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;

/**
 * The Class ComprobanteView.
 */
public class ComprobanteView extends ReportComprobanteView{

	/** The numero prestamo. */
	private String numeroPrestamo;

	/** The titular. */
	private String titular;

	/** The nro inscripcion. */
	private String nroInscripcion;

	/** The tipo inscripcion. */
	private String tipoInscripcion;

	/** The condicion IVA. */
	private String condicionIVA;

	/** The nro cuenta asociada. */
	private String nroCuentaAsociada;

	/** The cuota prestamo. */
	private String cuotaPrestamo;

	/** The plazo prestamo. */
	private String plazoPrestamo;

	/** The importe value. */
	private String importeValue;

	/** The tasas. */
	private List<TasaView> tasas;

	/** The importes. */
	private List<ImporteView> importes;

	/** The fecha de vencimiento. */
	private String fechaDeVencimiento;

	/** The fecha hora comprobante. */
	private String fechaHoraComprobante;

	/** The divisa. */
	private String divisa;

	/** The tipo prestamo label. */
	private String tipoPrestamoLabel;

	/** The tipo cuenta. */
	private String tipoCuenta;

	/** The nro cuenta. */
	private String nroCuenta;

	/** The nro comprobante. */
	private String nroComprobante;

	/** The saldo anterior capital sin ajustar. */
	private String saldoAnteriorCapitalSinAjustar;

	/** The legal comprobante. */
	private String legalComprobante;

	/** The uvas. */
	private List<UvaView> uvas;

	/** The saldo deuda capital. */
	private String saldoDeudaCapital;
	
	/** The capital ajustado. */
	private String capitalAjustado;
	
	/** The capital ajustado. */
	private Boolean showCapitalAjustado;
	
	/** The prestamos jasper. */
	protected final String prestamosJasper = "pago-prestamo.jasper";
	
	/** The importe acreditar. */
	protected final String importeAcreditarKey = "IMPORTE_ACREDITAR";
	
	/** The tipo prestamo key. */
	protected final String tipoPrestamoKey = "TIPO_PRESTAMO";
	
	/** The nro prestamo key. */
	protected final String nroPrestamoKey = "NUMERO_PRESTAMO";
	
	/** The titular key. */
	protected final String titularKey = "TITULAR";
	
	/** The tipo doc key. */
	protected final String tipoDocKey = "TIPO_DOC";
	
	/** The nro doc key. */
	protected final String nroDocKey = "NRO_DOC";
	
	/** The condicion IVA key. */
	protected final String condicionIVAKey = "CONDICION_IVA";
	
	/** The nro cuenta debito key. */
	protected final String nroCuentaDebitoKey = "NRO_CUENTA_DEBITO";
	
	/** The tipo cuenta debito key. */
	protected final String tipoCuentaDebitoKey = "TIPO_CUENTA_DEBITO";
	
	/** The cuota prestamo key. */
	protected final String cuotaPrestamoKey = "CUOTA_PRESTAMO";
	
	/** The plazo prestamo key. */
	protected final String plazoPrestamoKey = "PLAZO_PRESTAMO";
		
	/** The importe de la cuota key. */
	protected final String importeDeLaCuotaKey = "IMPORTE_CUOTA";
	
	/** The ubas key. */
	protected final String ubasKey = "UBAS";
	
	/** The capital ajustado key. */
	protected final String capitalAjustadoKey = "CAPITAL_AJUSTADO";
	
	/** The importes key. */
	protected final String importesKey = "IMPORTES";
	
	/** The tasas key. */
	protected final String tasasKey = "TASAS";
	
	/** The fecha vencimiento key. */
	protected final String fechaVencimientoKey = "FECHA_VENCIMIENTO";
	
	/** The nro comprobante key. */
	protected final String nroComprobanteKey = "NRO_COMPROBANTE";
	
	/** The titulo prestamo. */
	protected final String tituloPrestamo = "Comprobante de pago de cuota de Préstamo";
	
	/** The super prestamo. */
	protected final String superPrestamo = "Súper Préstamo Personal";
	
	/** The super prestamo uva. */
	protected final String superPrestamoUva = "Súper Préstamo Personal UVA";
	
	/**
	 * Gets the nro cuenta.
	 *
	 * @return the nroCuenta
	 */
	public String getNroCuenta() {
		return nroCuenta;
	}

	/**
	 * Sets the nro cuenta.
	 *
	 * @param nroCuenta
	 *            the nroCuenta to set
	 */
	public void setNroCuenta(String nroCuenta) {
		this.nroCuenta = nroCuenta;
	}

	/**
	 * Gets the tipo prestamo label.
	 *
	 * @return the tipoPrestamoLabel
	 */
	public String getTipoPrestamoLabel() {
		return tipoPrestamoLabel;
	}

	/**
	 * Sets the tipo prestamo label.
	 *
	 * @param tipoPrestamoLabel
	 *            the tipoPrestamoLabel to set
	 */
	public void setTipoPrestamoLabel(String tipoPrestamoLabel) {
		this.tipoPrestamoLabel = tipoPrestamoLabel;
	}

	/**
	 * Gets the numero prestamo.
	 *
	 * @return the numeroPrestamo
	 */
	public String getNumeroPrestamo() {
		return numeroPrestamo;
	}

	/**
	 * Sets the numero prestamo.
	 *
	 * @param numeroPrestamo
	 *            the numeroPrestamo to set
	 */
	public void setNumeroPrestamo(String numeroPrestamo) {
		this.numeroPrestamo = numeroPrestamo;
	}

	/**
	 * Gets the titular.
	 *
	 * @return the titular
	 */
	public String getTitular() {
		return titular;
	}

	/**
	 * Sets the titular.
	 *
	 * @param titular
	 *            the titular to set
	 */
	public void setTitular(String titular) {
		this.titular = titular;
	}

	/**
	 * Gets the condicion IVA.
	 *
	 * @return the condicionIVA
	 */
	public String getCondicionIVA() {
		return condicionIVA;
	}

	/**
	 * Sets the condicion IVA.
	 *
	 * @param condicionIVA
	 *            the condicionIVA to set
	 */
	public void setCondicionIVA(String condicionIVA) {
		this.condicionIVA = condicionIVA;
	}

	/**
	 * Gets the nro cuenta asociada.
	 *
	 * @return the nroCuentaAsociada
	 */
	public String getNroCuentaAsociada() {
		return nroCuentaAsociada;
	}

	/**
	 * Sets the nro cuenta asociada.
	 *
	 * @param nroCuentaAsociada
	 *            the nroCuentaAsociada to set
	 */
	public void setNroCuentaAsociada(String nroCuentaAsociada) {
		this.nroCuentaAsociada = nroCuentaAsociada;
	}

	/**
	 * Gets the cuota prestamo.
	 *
	 * @return the cuotaPrestamo
	 */
	public String getCuotaPrestamo() {
		return cuotaPrestamo;
	}

	/**
	 * Sets the cuota prestamo.
	 *
	 * @param cuotaPrestamo
	 *            the cuotaPrestamo to set
	 */
	public void setCuotaPrestamo(String cuotaPrestamo) {
		this.cuotaPrestamo = cuotaPrestamo;
	}

	/**
	 * Gets the plazo prestamo.
	 *
	 * @return the plazoPrestamo
	 */
	public String getPlazoPrestamo() {
		return plazoPrestamo;
	}

	/**
	 * Sets the plazo prestamo.
	 *
	 * @param plazoPrestamo
	 *            the plazoPrestamo to set
	 */
	public void setPlazoPrestamo(String plazoPrestamo) {
		this.plazoPrestamo = plazoPrestamo;
	}

	/**
	 * Gets the importe value.
	 *
	 * @return the importeValue
	 */
	public String getImporteValue() {
		return importeValue;
	}

	/**
	 * Sets the importe value.
	 *
	 * @param importeValue
	 *            the importeValue to set
	 */
	public void setImporteValue(String importeValue) {
		this.importeValue = importeValue;
	}

	/**
	 * Gets the tasas.
	 *
	 * @return the tasas
	 */
	public List<TasaView> getTasas() {
		return tasas;
	}

	/**
	 * Sets the tasas.
	 *
	 * @param tasas
	 *            the tasas to set
	 */
	public void setTasas(List<TasaView> tasas) {
		this.tasas = tasas;
	}

	/**
	 * Gets the importes.
	 *
	 * @return the importes
	 */
	public List<ImporteView> getImportes() {
		return importes;
	}

	/**
	 * Sets the importes.
	 *
	 * @param importes
	 *            the importes to set
	 */
	public void setImportes(List<ImporteView> importes) {
		this.importes = importes;
	}

	/**
	 * Gets the fecha de vencimiento.
	 *
	 * @return the fechaDeVencimiento
	 */
	public String getFechaDeVencimiento() {
		return fechaDeVencimiento;
	}

	/**
	 * Sets the fecha de vencimiento.
	 *
	 * @param fechaDeVencimiento
	 *            the fechaDeVencimiento to set
	 */
	public void setFechaDeVencimiento(String fechaDeVencimiento) {
		this.fechaDeVencimiento = fechaDeVencimiento;
	}

	/**
	 * Gets the fecha hora comprobante.
	 *
	 * @return the fechaHoraComprobante
	 */
	public String getFechaHoraComprobante() {
		return fechaHoraComprobante;
	}

	/**
	 * Sets the fecha hora comprobante.
	 *
	 * @param fechaHoraComprobante
	 *            the fechaHoraComprobante to set
	 */
	public void setFechaHoraComprobante(String fechaHoraComprobante) {
		this.fechaHoraComprobante = fechaHoraComprobante;
	}

	/**
	 * Gets the divisa.
	 *
	 * @return the divisa
	 */
	public String getDivisa() {
		return divisa;
	}

	/**
	 * Sets the divisa.
	 *
	 * @param divisa
	 *            the divisa to set
	 */
	public void setDivisa(String divisa) {
		this.divisa = divisa;
	}


	/**
	 * Gets the tipo cuenta.
	 *
	 * @return the tipoCuenta
	 */
	public String getTipoCuenta() {
		return tipoCuenta;
	}

	/**
	 * Sets the tipo cuenta.
	 *
	 * @param tipoCuenta
	 *            the tipoCuenta to set
	 */
	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	/**
	 * Gets the nro comprobante.
	 *
	 * @return the nro comprobante
	 */
	public String getNroComprobante() {
		return nroComprobante;
	}

	/**
	 * Sets the nro comprobante.
	 *
	 * @param nroComprobante
	 *            the new nro comprobante
	 */
	public void setNroComprobante(String nroComprobante) {
		this.nroComprobante = nroComprobante;
	}

	/**
	 * Gets the nro inscripcion.
	 *
	 * @return the nro inscripcion
	 */
	public String getNroInscripcion() {
		return nroInscripcion;
	}

	/**
	 * Sets the nro inscripcion.
	 *
	 * @param nroInscripcion
	 *            the new nro inscripcion
	 */
	public void setNroInscripcion(String nroInscripcion) {
		this.nroInscripcion = nroInscripcion;
	}

	/**
	 * Gets the tipo inscripcion.
	 *
	 * @return the tipo inscripcion
	 */
	public String getTipoInscripcion() {
		return tipoInscripcion;
	}

	/**
	 * Sets the tipo inscripcion.
	 *
	 * @param tipoInscripcion
	 *            the new tipo inscripcion
	 */
	public void setTipoInscripcion(String tipoInscripcion) {
		this.tipoInscripcion = tipoInscripcion;
	}

	/**
	 * Gets the saldo anterior capital sin ajustar.
	 *
	 * @return the saldo anterior capital sin ajustar
	 */
	public String getSaldoAnteriorCapitalSinAjustar() {
		return saldoAnteriorCapitalSinAjustar;
	}

	/**
	 * Sets the saldo anterior capital sin ajustar.
	 *
	 * @param saldoAnteriorCapitalSinAjustar
	 *            the new saldo anterior capital sin ajustar
	 */
	public void setSaldoAnteriorCapitalSinAjustar(String saldoAnteriorCapitalSinAjustar) {
		this.saldoAnteriorCapitalSinAjustar = saldoAnteriorCapitalSinAjustar;
	}

	/**
	 * Gets the legal comprobante.
	 *
	 * @return the legal comprobante
	 */
	public String getLegalComprobante() {
		return legalComprobante;
	}

	/**
	 * Sets the legal comprobante.
	 *
	 * @param legalComprobante
	 *            the new legal comprobante
	 */
	public void setLegalComprobante(String legalComprobante) {
		this.legalComprobante = legalComprobante;
	}

	/**
	 * Gets the uvas.
	 *
	 * @return the uvas
	 */
	public List<UvaView> getUvas() {
		return uvas;
	}

	/**
	 * Sets the uvas.
	 *
	 * @param uvas
	 *            the new uvas
	 */
	public void setUvas(List<UvaView> uvas) {
		this.uvas = uvas;
	}

	/**
	 * Gets the saldo deuda capital.
	 *
	 * @return the saldo deuda capital
	 */
	public String getSaldoDeudaCapital() {
		return saldoDeudaCapital;
	}

	/**
	 * Sets the saldo deuda capital.
	 *
	 * @param saldoDeudaCapital
	 *            the new saldo deuda capital
	 */
	public void setSaldoDeudaCapital(String saldoDeudaCapital) {
		this.saldoDeudaCapital = saldoDeudaCapital;
	}
	
	/**
	 * Gets the capital ajustado.
	 *
	 * @return the capital ajustado
	 */
	public String getCapitalAjustado() {
		return capitalAjustado;
	}

	/**
	 * Sets the capital ajustado.
	 *
	 * @param capitalAjustado
	 *            the new capital ajustado
	 */
	public void setCapitalAjustado(String capitalAjustado) {
		this.capitalAjustado = capitalAjustado;
	}

	/**
     * Gets the show capital ajustado.
     *
     * @return the showCapitalAjustado
     */
	public Boolean getShowCapitalAjustado() {
		return showCapitalAjustado;
	}

	/**
     * Sets the show capital ajustado.
     *
     * @param showCapitalAjustado the showCapitalAjustado to set
     */
	public void setShowCapitalAjustado(Boolean showCapitalAjustado) {
		this.showCapitalAjustado = showCapitalAjustado;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.web.view.
	 * DetalleComprobanteTransferenciaView#obtenerParametrosPDF()
	 */
	@Override
	public HashMap<String, Object> obtenerParametrosPDF() {
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		tituloComprobante = tituloPrestamo;
		parametros.put(tituloComprobanteKey, tituloPrestamo);
		parametros.put(importeAcreditarKey, agregarDivisaONull(importeValue,divisa));
		parametros.put(tipoPrestamoKey, "Super "+tipoPrestamoLabel);
		parametros.put(nroPrestamoKey, numeroPrestamo);
		parametros.put(titularKey, titular);
		parametros.put(tipoDocKey, tipoInscripcion);
		parametros.put(nroDocKey, nroInscripcion);
		parametros.put(condicionIVAKey, condicionIVA);
		parametros.put(tipoCuentaDebitoKey,tipoCuenta);
		parametros.put(nroCuentaDebitoKey, nroCuenta);
		parametros.put(cuotaPrestamoKey, cuotaPrestamo);
		parametros.put(plazoPrestamoKey, plazoPrestamo);
		parametros.put(ubasKey, uvas);
		parametros.put(capitalAjustadoKey, capitalAjustado);
		//ARRANCA IMPORTE DE LA CUOTA
		agregarDivisasPorcentajes();
		parametros.put(importeDeLaCuotaKey, agregarDivisaONull(importeValue, divisa));
		parametros.put(importesKey, importes);
		parametros.put(tasasKey, tasas);
		parametros.put(fechaVencimientoKey, fechaDeVencimiento);
		parametros.put(nroComprobanteKey, nroComprobante);
		parametros.put(fechaHoraActualKey, ISBANStringUtils.formatearFechaConHoraParaComprobante(new Date()));
		return parametros;
	}
	
	/**
	 * Agregar divisas porcentajes.
	 */
	private void agregarDivisasPorcentajes() {
		for(ImporteView importe: importes) {
			importe.setImporte(agregarDivisaONull(importe.getImporte(),divisa));
		}
		for(TasaView tasa : tasas) {
			tasa.setTasa(tasa.getTasa()+" %");
		}
		
	}

	/**
	 * Agregar divisa O null.
	 *
	 * @param importe
	 *            the importe
	 * @param divisa
	 *            the divisa
	 * @return the string
	 */
	private String agregarDivisaONull(String importe, String divisa) {
		if(importe==null || divisa == null) {
			return null;
		}
		return divisa+" "+importe;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.web.view.
	 * DetalleComprobanteTransferenciaView#obtenerJasper()
	 */
	@Override
	public String obtenerJasper() throws FileNotFoundException {
		return ResourceUtils.getFile(path + prestamosJasper).getPath();
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.web.view.ReportComprobanteView#obtenerParametrosPDF(boolean)
	 */
	@Override
	public HashMap<String, Object> obtenerParametrosPDF(boolean isUva) {
		HashMap<String, Object> parametros = new HashMap<String, Object>();
//		if (superPrestamoUva.equals(tipoPrestamo)) {
		if (isUva) {
			parametros.put(tipoPrestamoKey, superPrestamoUva);
			tipoPrestamo = "UVA";
		} 
		else {
			parametros.put(tipoPrestamoKey, "Super "+tipoPrestamoLabel);
		}
		tituloComprobante = tituloPrestamo;
		parametros.put(tituloComprobanteKey, tituloPrestamo);
		parametros.put(importeAcreditarKey, agregarDivisaONull(importeValue,divisa));
		parametros.put(nroPrestamoKey, numeroPrestamo);
		parametros.put(titularKey, titular);
		parametros.put(tipoDocKey, tipoInscripcion);
		parametros.put(nroDocKey, nroInscripcion);
		parametros.put(condicionIVAKey, condicionIVA);
		parametros.put(tipoCuentaDebitoKey,tipoCuenta);
		parametros.put(nroCuentaDebitoKey, nroCuenta);
		parametros.put(cuotaPrestamoKey, cuotaPrestamo);
		parametros.put(plazoPrestamoKey, plazoPrestamo);
		parametros.put(ubasKey, uvas);
		parametros.put(capitalAjustadoKey, capitalAjustado);
		//ARRANCA IMPORTE DE LA CUOTA
		agregarDivisasPorcentajes();
		parametros.put(importeDeLaCuotaKey, agregarDivisaONull(importeValue, divisa));
		parametros.put(importesKey, importes);
		// Fix para poner letra negrita, solo en Costo Financiero
		for (TasaView tasa : tasas) {
			if (tasa.getLabel().contains("Costo Financiero")) {
				tasa.setLabel("<b>" + tasa.getLabel() + "</b>");
			}
		}
		parametros.put(tasasKey, tasas);
		parametros.put(fechaVencimientoKey, fechaDeVencimiento);
		parametros.put(nroComprobanteKey, nroComprobante);
		parametros.put(fechaHoraActualKey, ISBANStringUtils.formatearFechaConHoraParaComprobante(new Date()));
		return parametros;
	}
}
