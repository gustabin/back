/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.web.view;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

/**
 * The Class DetallePrestamoView.
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class DetallePrestamoView {

	/** The tasas. */
	private List<TasaView> tasas;

	/** The importes. */
	private List<ImporteView> importes;

	/** The uvas. */
	private List<UvaView> uvas;

	/** The tipo prestamo label. */
	private String tipoPrestamoLabel;

	/** The numero prestamo. */
	private String numeroPrestamo;

	/** The alias prestamo. */
	private String aliasPrestamo;

	/** The numero cuenta. */
	private String numeroCuenta;

	/** The alias cuenta. */
	private String aliasCuenta;

	/** The cuota prestamo. */
	private String cuotaPrestamo;

	/** The plazo prestamo. */
	private String plazoPrestamo;

	/** The divisa. */
	private String divisa;

	/** The saldo anterior capital sin ajustar. */
	private String saldoAnteriorCapitalSinAjustar;

	/** The coeficiente indexacion recibo en termino. */
	private String coeficienteIndexacionReciboEnTermino;

	/** The show coeficiente indexacion recibo en termino. */
	private boolean showCoeficienteIndexacionReciboEnTermino;

	/** The capital ajustodo sobre la cuota. */
	private String capitalAjustodoSobreLaCuota;

	/** The show capital ajustodo sobre la cuota. */
	private boolean showCapitalAjustodoSobreLaCuota;

	/** The fecha de vencimiento. */
	private String fechaDeVencimiento;

	/** The importe value. */
	private String importeValue;

	/** The tipo prestamo. */
	private String tipoPrestamo;

	/** The tipo cuenta. */
	private String tipoCuenta;

	/** The legal detalle prestamo. */
	private String legalDetallePrestamo;

	/** Atributos de prestamos prendarios. */

	private String titular;

	/** The cuil. */
	private String cuil;

	/** The condiciones iva. */
	private String condicionesIva;

	/** The coeficiente prendario. */
	private String coeficientePrendario;

	/** The aclaracion referencias. */
	private List<String> aclaracionReferencias = new ArrayList<String>();
	
	/** The tiene otros impuestos. */
	private Boolean tieneOtrosImpuestos = Boolean.FALSE;

	/** The capital ajustado. */
	private String capitalAjustado;
	
	/** The show capital ajustado. */
	private Boolean showCapitalAjustado;
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
	 *            the new tasas
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
	 *            the new importes
	 */
	public void setImportes(List<ImporteView> importes) {
		this.importes = importes;
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
	 * Gets the tipo prestamo label.
	 *
	 * @return the tipo prestamo label
	 */
	public String getTipoPrestamoLabel() {
		return tipoPrestamoLabel;
	}

	/**
	 * Sets the tipo prestamo label.
	 *
	 * @param tipoPrestamoLabel
	 *            the new tipo prestamo label
	 */
	public void setTipoPrestamoLabel(String tipoPrestamoLabel) {
		this.tipoPrestamoLabel = tipoPrestamoLabel;
	}

	/**
	 * Gets the numero prestamo.
	 *
	 * @return the numero prestamo
	 */
	public String getNumeroPrestamo() {
		return numeroPrestamo;
	}

	/**
	 * Sets the numero prestamo.
	 *
	 * @param numeroPrestamo
	 *            the new numero prestamo
	 */
	public void setNumeroPrestamo(String numeroPrestamo) {
		this.numeroPrestamo = numeroPrestamo;
	}

	/**
	 * Gets the alias prestamo.
	 *
	 * @return the alias prestamo
	 */
	public String getAliasPrestamo() {
		return aliasPrestamo;
	}

	/**
	 * Sets the alias prestamo.
	 *
	 * @param aliasPrestamo
	 *            the new alias prestamo
	 */
	public void setAliasPrestamo(String aliasPrestamo) {
		this.aliasPrestamo = aliasPrestamo;
	}

	/**
	 * Gets the numero cuenta.
	 *
	 * @return the numero cuenta
	 */
	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	/**
	 * Sets the numero cuenta.
	 *
	 * @param numeroCuenta
	 *            the new numero cuenta
	 */
	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	/**
	 * Gets the alias cuenta.
	 *
	 * @return the alias cuenta
	 */
	public String getAliasCuenta() {
		return aliasCuenta;
	}

	/**
	 * Sets the alias cuenta.
	 *
	 * @param aliasCuenta
	 *            the new alias cuenta
	 */
	public void setAliasCuenta(String aliasCuenta) {
		this.aliasCuenta = aliasCuenta;
	}

	/**
	 * Gets the cuota prestamo.
	 *
	 * @return the cuota prestamo
	 */
	public String getCuotaPrestamo() {
		return cuotaPrestamo;
	}

	/**
	 * Sets the cuota prestamo.
	 *
	 * @param cuotaPrestamo
	 *            the new cuota prestamo
	 */
	public void setCuotaPrestamo(String cuotaPrestamo) {
		this.cuotaPrestamo = cuotaPrestamo;
	}

	/**
	 * Gets the plazo prestamo.
	 *
	 * @return the plazo prestamo
	 */
	public String getPlazoPrestamo() {
		return plazoPrestamo;
	}

	/**
	 * Sets the plazo prestamo.
	 *
	 * @param plazoPrestamo
	 *            the new plazo prestamo
	 */
	public void setPlazoPrestamo(String plazoPrestamo) {
		this.plazoPrestamo = plazoPrestamo;
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
	 *            the new divisa
	 */
	public void setDivisa(String divisa) {
		this.divisa = divisa;
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
	 * Gets the coeficiente indexacion recibo en termino.
	 *
	 * @return the coeficiente indexacion recibo en termino
	 */
	public String getCoeficienteIndexacionReciboEnTermino() {
		return coeficienteIndexacionReciboEnTermino;
	}

	/**
	 * Sets the coeficiente indexacion recibo en termino.
	 *
	 * @param coeficienteIndexacionReciboEnTermino
	 *            the new coeficiente indexacion recibo en termino
	 */
	public void setCoeficienteIndexacionReciboEnTermino(String coeficienteIndexacionReciboEnTermino) {
		this.coeficienteIndexacionReciboEnTermino = coeficienteIndexacionReciboEnTermino;
	}

	/**
	 * Checks if is show coeficiente indexacion recibo en termino.
	 *
	 * @return true, if is show coeficiente indexacion recibo en termino
	 */
	public boolean isShowCoeficienteIndexacionReciboEnTermino() {
		return showCoeficienteIndexacionReciboEnTermino;
	}

	/**
	 * Sets the show coeficiente indexacion recibo en termino.
	 *
	 * @param showCoeficienteIndexacionReciboEnTermino
	 *            the new show coeficiente indexacion recibo en termino
	 */
	public void setShowCoeficienteIndexacionReciboEnTermino(boolean showCoeficienteIndexacionReciboEnTermino) {
		this.showCoeficienteIndexacionReciboEnTermino = showCoeficienteIndexacionReciboEnTermino;
	}

	/**
	 * Gets the capital ajustodo sobre la cuota.
	 *
	 * @return the capital ajustodo sobre la cuota
	 */
	public String getCapitalAjustodoSobreLaCuota() {
		return capitalAjustodoSobreLaCuota;
	}

	/**
	 * Sets the capital ajustodo sobre la cuota.
	 *
	 * @param capitalAjustodoSobreLaCuota
	 *            the new capital ajustodo sobre la cuota
	 */
	public void setCapitalAjustodoSobreLaCuota(String capitalAjustodoSobreLaCuota) {
		this.capitalAjustodoSobreLaCuota = capitalAjustodoSobreLaCuota;
	}

	/**
	 * Checks if is show capital ajustodo sobre la cuota.
	 *
	 * @return true, if is show capital ajustodo sobre la cuota
	 */
	public boolean isShowCapitalAjustodoSobreLaCuota() {
		return showCapitalAjustodoSobreLaCuota;
	}

	/**
	 * Sets the show capital ajustodo sobre la cuota.
	 *
	 * @param showCapitalAjustodoSobreLaCuota
	 *            the new show capital ajustodo sobre la cuota
	 */
	public void setShowCapitalAjustodoSobreLaCuota(boolean showCapitalAjustodoSobreLaCuota) {
		this.showCapitalAjustodoSobreLaCuota = showCapitalAjustodoSobreLaCuota;
	}

	/**
	 * Gets the fecha de vencimiento.
	 *
	 * @return the fecha de vencimiento
	 */
	public String getFechaDeVencimiento() {
		return fechaDeVencimiento;
	}

	/**
	 * Sets the fecha de vencimiento.
	 *
	 * @param fechaDeVencimiento
	 *            the new fecha de vencimiento
	 */
	public void setFechaDeVencimiento(String fechaDeVencimiento) {
		this.fechaDeVencimiento = fechaDeVencimiento;
	}

	/**
	 * Gets the importe value.
	 *
	 * @return the importe value
	 */
	public String getImporteValue() {
		return importeValue;
	}

	/**
	 * Sets the importe value.
	 *
	 * @param importeValue
	 *            the new importe value
	 */
	public void setImporteValue(String importeValue) {
		this.importeValue = importeValue;
	}

	/**
	 * Gets the tipo prestamo.
	 *
	 * @return the tipo prestamo
	 */
	public String getTipoPrestamo() {
		return tipoPrestamo;
	}

	/**
	 * Sets the tipo prestamo.
	 *
	 * @param tipoPrestamo
	 *            the new tipo prestamo
	 */
	public void setTipoPrestamo(String tipoPrestamo) {
		this.tipoPrestamo = tipoPrestamo;
	}

	/**
	 * Gets the tipo cuenta.
	 *
	 * @return the tipo cuenta
	 */
	public String getTipoCuenta() {
		return tipoCuenta;
	}

	/**
	 * Sets the tipo cuenta.
	 *
	 * @param tipoCuenta
	 *            the new tipo cuenta
	 */
	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	/**
	 * Gets the legal detalle prestamo.
	 *
	 * @return the legal detalle prestamo
	 */
	public String getLegalDetallePrestamo() {
		return legalDetallePrestamo;
	}

	/**
	 * Sets the legal detalle prestamo.
	 *
	 * @param legalDetallePrestamo
	 *            the new legal detalle prestamo
	 */
	public void setLegalDetallePrestamo(String legalDetallePrestamo) {
		this.legalDetallePrestamo = legalDetallePrestamo;
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
	 *            the new titular
	 */
	public void setTitular(String titular) {
		this.titular = titular;
	}

	/**
	 * Gets the cuil.
	 *
	 * @return the cuil
	 */
	public String getCuil() {
		return cuil;
	}

	/**
	 * Sets the cuil.
	 *
	 * @param cuil
	 *            the new cuil
	 */
	public void setCuil(String cuil) {
		this.cuil = cuil;
	}

	/**
	 * Gets the condiciones iva.
	 *
	 * @return the condiciones iva
	 */
	public String getCondicionesIva() {
		return condicionesIva;
	}

	/**
	 * Sets the condiciones iva.
	 *
	 * @param condicionesIva
	 *            the new condiciones iva
	 */
	public void setCondicionesIva(String condicionesIva) {
		this.condicionesIva = condicionesIva;
	}

	/**
	 * Gets the coeficiente prendario.
	 *
	 * @return the coeficiente prendario
	 */
	public String getCoeficientePrendario() {
		return coeficientePrendario;
	}

	/**
	 * Sets the coeficiente prendario.
	 *
	 * @param coeficientePrendario
	 *            the new coeficiente prendario
	 */
	public void setCoeficientePrendario(String coeficientePrendario) {
		this.coeficientePrendario = coeficientePrendario;
	}

	/**
	 * Gets the aclaracion referencias.
	 *
	 * @return the aclaracion referencias
	 */
	public List<String> getAclaracionReferencias() {
		return aclaracionReferencias;
	}

	/**
	 * Sets the aclaracion referencias.
	 *
	 * @param aclaracionReferencias
	 *            the new aclaracion referencias
	 */
	public void setAclaracionReferencias(List<String> aclaracionReferencias) {
		this.aclaracionReferencias = aclaracionReferencias;
	}

	/**
	 * Agregar aclaracion referencia.
	 *
	 * @param aclaracionReferencia
	 *            the aclaracion referencia
	 */
	public void agregarAclaracionReferencia(String aclaracionReferencia) {
		this.aclaracionReferencias.add(aclaracionReferencia);
	}

    /**
     * Gets the tiene otros impuestos.
     *
     * @return the tieneOtrosImpuestos
     */
    public Boolean getTieneOtrosImpuestos() {
        return tieneOtrosImpuestos;
    }

    /**
     * Sets the tiene otros impuestos.
     *
     * @param tieneOtrosImpuestos the tieneOtrosImpuestos to set
     */
    public void setTieneOtrosImpuestos(Boolean tieneOtrosImpuestos) {
        this.tieneOtrosImpuestos = tieneOtrosImpuestos;
    }

    /**
     * Gets the capital ajustado.
     *
     * @return the capitalAjustado
     */
	public String getCapitalAjustado() {
		return capitalAjustado;
	}

	/**
     * Sets the capital ajustado.
     *
     * @param capitalAjustado the capitalAjustado to set
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
    
}
