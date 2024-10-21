/*
 * 
 */
package ar.com.santanderrio.obp.servicios.prestamos.view;

import java.io.FileNotFoundException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.springframework.util.ResourceUtils;

import ar.com.santanderrio.obp.servicios.comprobantes.web.view.ReportComprobanteView;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.inversiones.ordenes.web.view.CuentaView;
import ar.com.santanderrio.obp.servicios.pagos.web.view.UvaView;

/**
 * The Class DetalleCuotaPrestamoView.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@JsonSerialize(include = Inclusion.NON_NULL)
public class DetalleCuotaPrestamoView extends ReportComprobanteView {

	/** The cuenta. */
	private CuentaView cuenta;

	/** The tasas. */
	private TasaValorView tasas;

	/** The uvas. */
	private List<UvaView> uvas;

	/** The importe cuota. */
	private String importeCuota;

	/** The tipo prestamo. */
	private String tipoPrestamo;

	/** The numero prestamo. */
	private String numeroPrestamo;

	/** The numero cuota. */
	private String numeroCuota;

	/** The coeficiente. */
	private String coeficiente;

	/** The capital. */
	private String capital;

	/** The intereses compensatorios periodo. */
	private String interesesCompensatoriosPeriodo;

	/** The cargo seguro vida saldo deudor. */
	private String cargoSeguroVidaSaldoDeudor;

	/** The otros impuestos. */
	private String otrosImpuestos;

	/** The fecha vencimiento. */
	private String fechaVencimiento;

	/** The plazo prestamo. */
	private String plazoPrestamo;

	/** The saldo anterior capital sin ajustar. */
	private String saldoAnteriorCapitalSinAjustar;

	/** The ajuste capital mora. */
	private String ajusteCapitalMora;

	/** The intereses punitorios. */
	private String interesesPunitorios;

	/** The cargo seguro vida. */
	private String cargoSeguroVida;

	/** The seguro incendios. */
	private String seguroIncendios;

	/** The seguro bien. */
	private String seguroBien;

	/** The titular. */
	private String titular;

	/** The cuil. */
	private String cuil;

	/** The condiciones iva. */
	private String condicionesIva;

	/** The gastos administrativos. */
	private String gastosAdministrativos;

	/** The capital ajustado. */
	private String capitalAjustado;

	/** The saldo anterior. */
	private String saldoAnterior;

	/** The importe solicitado. */
	private String importeSolicitado;

	/** The destino prestamo. */
	private String destinoPrestamo;

	/** The saldo posterior. */
	private String saldoPosterior;
	
	/** The tiene otros impuestos. */
    private Boolean tieneOtrosImpuestos = false;
    
    /** The tiene otros impuestos. */
    private Boolean tieneSeguro = false;
    
    /** The fecha alta prestamo. */
    private String fechaAltaPrestamo;
    
    /** The importe solicitado uvas. */
    private String importeSolicitadoUvas;
    
    /** The cotizacion uva. */
    private String cotizacionUva;
    
    /** The fecha cotizacion uva. */
    private String fechaCotizacionUva;
    
    /** The is uva. */
    private Boolean isUva;

    /** The prestamos jasper. */
    protected final String prestamosJasper = "detalle-prestamo.jasper";
    
    /** The titulo prestamo. */
    protected final String tituloPrestamo = "Detalle de préstamo";
    
    /** The importe acreditar key. */
    protected final String importeAcreditarKey = "IMPORTE_ACREDITAR";

	/** The importe cuota key. */
	protected final String importeCuotaKey = "IMPORTE_CUOTA";
	
	/** The importe solicitado key. */
	protected final String importeSolicitadoKey = "IMPORTE_SOLICITADO";

	/** The tipo prestamo key. */
	protected final String tipoPrestamoKey = "TIPO_PRESTAMO";

	/** The numero prestamo key. */
	protected final String numeroPrestamoKey = "NUMERO_PRESTAMO";

	/** The cuenta debito key. */
	protected final String cuentaDebitoKey = "CUENTA_DEBITO";

	/** The nro cuota key. */
	protected final String nroCuotaKey = "NRO_CUOTA";
	
	/** The plazo cuota key. */
	protected final String plazoCuotaKey = "PLAZO_CUOTA";

	/** The seguro key. */
	protected final String seguroKey = "SEGURO";

	/** The titulo seguro key. */
	protected final String tituloSeguroKey = "TITULO_SEGURO";

	/** The titulo otros impuestos key. */
	protected final String tituloOtrosImpuestosKey = "TITULO_OTROS_IMPUESTOS";

	/** The tasa efectiva anual key. */
	protected final String tasaEfectivaAnualKey = "TASA_EFECTIVA_ANUAL";

	/** The tasa nominal anual key. */
	protected final String tasaNominalAnualKey = "TASA_NOMINAL_ANUAL";

	/** The fecha vencimiento key. */
	protected final String fechaVencimientoKey = "FECHA_VENCIMIENTO";
	
	/** The motivo key. */
	protected final String motivoKey = "MOTIVO";

	/** The fecha vencimiento key. */
	protected final String fechaAltaDelPrestamoKey = "FECHA_ALTA_DEL_PRESTAMO";
	
	/** The importe solicitado uvas key. */
    private String importeSolicitadoUvasKey = "IMPORTE_SOLICITADO_UVAS";
    
    /** The cotizacion uva key. */
    private String cotizacionUvaKey = "COTIZACION_UVA";
    
    /** The fecha cotizacion uva key. */
    private String fechaCotizacionUvaKey = "FECHA_COTIZACION_UVA";
	
	/** The costo efectivo key. */
	protected final String costoEfectivoKey = "COSTO_EFECTIVO_CON_IMPUESTOS";

	/** The costo efectivo sin impuestos key. */
	protected final String costoEfectivoSinImpuestosKey = "COSTO_EFECTIVO_SIN_IMPUESTOS";

	/** The legal 1 key. */
	protected final String legal1Key = "LEGAL1";

	/** The legal 2 key. */
	protected final String legal2Key = "LEGAL2";

	/** The legal 3 key. */
	protected final String legal3Key = "LEGAL3";
	
	/** The super prestamo personal. */
	protected final String superPrestamoPersonal = "Súper Préstamo Personal";
	
	/** The super prestamo uva. */
	protected final String superPrestamoUva = "Súper Préstamo Personal UVA";
	
	/** The super prestamo hipotecario. */
	protected final String superPrestamoHipotecario = "Súper Préstamo Hipotecario";
	
	/** The super prestamo prendario. */
	protected final String superPrestamoPrendario = "Súper Préstamo Prendario";
	
	/** The personal. */
	protected final String personal = "PERSONAL";
	
	/** The hipotecario. */
	protected final String hipotecario = "HIPOTECARIO";
	
	/** The prendario. */
	protected final String prendario = "PRENDARIO";
	
	private String legalPlazoPrestamo;
	
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
	 * Gets the numero cuota.
	 *
	 * @return the numero cuota
	 */
	public String getNumeroCuota() {
		return numeroCuota;
	}

	/**
	 * Sets the numero cuota.
	 *
	 * @param numeroCuota
	 *            the new numero cuota
	 */
	public void setNumeroCuota(String numeroCuota) {
		this.numeroCuota = numeroCuota;
	}

	/**
	 * Gets the coeficiente.
	 *
	 * @return the coeficiente
	 */
	public String getCoeficiente() {
		return coeficiente;
	}

	/**
	 * Sets the coeficiente.
	 *
	 * @param coeficiente
	 *            the new coeficiente
	 */
	public void setCoeficiente(String coeficiente) {
		this.coeficiente = coeficiente;
	}

	/**
	 * Gets the importe cuota.
	 *
	 * @return the importe cuota
	 */
	public String getImporteCuota() {
		return importeCuota;
	}

	/**
	 * Sets the importe cuota.
	 *
	 * @param importeCuota
	 *            the new importe cuota
	 */
	public void setImporteCuota(String importeCuota) {
		this.importeCuota = importeCuota;
	}

	/**
	 * Gets the capital.
	 *
	 * @return the capital
	 */
	public String getCapital() {
		return capital;
	}

	/**
	 * Sets the capital.
	 *
	 * @param capital
	 *            the new capital
	 */
	public void setCapital(String capital) {
		this.capital = capital;
	}

	/**
	 * Gets the intereses compensatorios periodo.
	 *
	 * @return the intereses compensatorios periodo
	 */
	public String getInteresesCompensatoriosPeriodo() {
		return interesesCompensatoriosPeriodo;
	}

	/**
	 * Sets the intereses compensatorios periodo.
	 *
	 * @param interesesCompensatoriosPeriodo
	 *            the new intereses compensatorios periodo
	 */
	public void setInteresesCompensatoriosPeriodo(String interesesCompensatoriosPeriodo) {
		this.interesesCompensatoriosPeriodo = interesesCompensatoriosPeriodo;
	}

	/**
	 * Gets the cargo seguro vida saldo deudor.
	 *
	 * @return the cargo seguro vida saldo deudor
	 */
	public String getCargoSeguroVidaSaldoDeudor() {
		return cargoSeguroVidaSaldoDeudor;
	}

	/**
	 * Sets the cargo seguro vida saldo deudor.
	 *
	 * @param cargoSeguroVidaSaldoDeudor
	 *            the new cargo seguro vida saldo deudor
	 */
	public void setCargoSeguroVidaSaldoDeudor(String cargoSeguroVidaSaldoDeudor) {
		this.cargoSeguroVidaSaldoDeudor = cargoSeguroVidaSaldoDeudor;
		this.tieneSeguro = cargoSeguroVidaSaldoDeudor != null;
	}

	/**
	 * Gets the otros impuestos.
	 *
	 * @return the otros impuestos
	 */
	public String getOtrosImpuestos() {
		return otrosImpuestos;
	}

	/**
	 * Sets the otros impuestos.
	 *
	 * @param otrosImpuestos
	 *            the new otros impuestos
	 */
	public void setOtrosImpuestos(String otrosImpuestos) {
		this.otrosImpuestos = otrosImpuestos;
		this.tieneOtrosImpuestos = otrosImpuestos != null;
	}

	/**
	 * Gets the saldo anterior.
	 *
	 * @return the saldo anterior
	 */
	public String getSaldoAnterior() {
		return saldoAnterior;
	}

	/**
	 * Sets the saldo anterior.
	 *
	 * @param saldoAnterior
	 *            the new saldo anterior
	 */
	public void setSaldoAnterior(String saldoAnterior) {
		this.saldoAnterior = saldoAnterior;
	}

	/**
	 * Gets the fecha vencimiento.
	 *
	 * @return the fecha vencimiento
	 */
	public String getFechaVencimiento() {
		return fechaVencimiento;
	}

	/**
	 * Sets the fecha vencimiento.
	 *
	 * @param fechaVencimiento
	 *            the new fecha vencimiento
	 */
	public void setFechaVencimiento(String fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	/**
	 * Gets the cuenta.
	 *
	 * @return the cuenta
	 */
	public CuentaView getCuenta() {
		return cuenta;
	}

	/**
	 * Sets the cuenta.
	 *
	 * @param cuenta
	 *            the new cuenta
	 */
	public void setCuenta(CuentaView cuenta) {
		this.cuenta = cuenta;
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
	 * Gets the ajuste capital mora.
	 *
	 * @return the ajuste capital mora
	 */
	public String getAjusteCapitalMora() {
		return ajusteCapitalMora;
	}

	/**
	 * Sets the ajuste capital mora.
	 *
	 * @param ajusteCapitalMora
	 *            the new ajuste capital mora
	 */
	public void setAjusteCapitalMora(String ajusteCapitalMora) {
		this.ajusteCapitalMora = ajusteCapitalMora;
	}

	/**
	 * Gets the intereses punitorios.
	 *
	 * @return the intereses punitorios
	 */
	public String getInteresesPunitorios() {
		return interesesPunitorios;
	}

	/**
	 * Sets the intereses punitorios.
	 *
	 * @param interesesPunitorios
	 *            the new intereses punitorios
	 */
	public void setInteresesPunitorios(String interesesPunitorios) {
		this.interesesPunitorios = interesesPunitorios;
	}

	/**
	 * Gets the cargo seguro vida.
	 *
	 * @return the cargo seguro vida
	 */
	public String getCargoSeguroVida() {
		return cargoSeguroVida;
	}

	/**
	 * Sets the cargo seguro vida.
	 *
	 * @param cargoSeguroVida
	 *            the new cargo seguro vida
	 */
	public void setCargoSeguroVida(String cargoSeguroVida) {
		this.cargoSeguroVida = cargoSeguroVida;
	}

	/**
	 * Gets the seguro incendios.
	 *
	 * @return the seguro incendios
	 */
	public String getSeguroIncendios() {
		return seguroIncendios;
	}

	/**
	 * Sets the seguro incendios.
	 *
	 * @param seguroIncendios
	 *            the new seguro incendios
	 */
	public void setSeguroIncendios(String seguroIncendios) {
		this.seguroIncendios = seguroIncendios;
	}

	/**
	 * Gets the seguro bien.
	 *
	 * @return the seguro bien
	 */
	public String getSeguroBien() {
		return seguroBien;
	}

	/**
	 * Sets the seguro bien.
	 *
	 * @param seguroBien
	 *            the new seguro bien
	 */
	public void setSeguroBien(String seguroBien) {
		this.seguroBien = seguroBien;
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
	 * Gets the gastos administrativos.
	 *
	 * @return the gastos administrativos
	 */
	public String getGastosAdministrativos() {
		return gastosAdministrativos;
	}

	/**
	 * Sets the gastos administrativos.
	 *
	 * @param gastosAdministrativos
	 *            the new gastos administrativos
	 */
	public void setGastosAdministrativos(String gastosAdministrativos) {
		this.gastosAdministrativos = gastosAdministrativos;
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
	 * Gets the tasas.
	 *
	 * @return the tasas
	 */
	public TasaValorView getTasas() {
		return tasas;
	}

	/**
	 * Sets the tasas.
	 *
	 * @param tasas
	 *            the new tasas
	 */
	public void setTasas(TasaValorView tasas) {
		this.tasas = tasas;
	}

	/**
	 * Gets the importe solicitado.
	 *
	 * @return the importe solicitado
	 */
	public String getImporteSolicitado() {
		return importeSolicitado;
	}

	/**
	 * Sets the importe solicitado.
	 *
	 * @param importeSolicitado
	 *            the new importe solicitado
	 */
	public void setImporteSolicitado(String importeSolicitado) {
		this.importeSolicitado = importeSolicitado;
	}

	/**
	 * Gets the destino prestamo.
	 *
	 * @return the destino prestamo
	 */
	public String getDestinoPrestamo() {
		return destinoPrestamo;
	}

	/**
	 * Sets the destino prestamo.
	 *
	 * @param destinoPrestamo
	 *            the new destino prestamo
	 */
	public void setDestinoPrestamo(String destinoPrestamo) {
		this.destinoPrestamo = destinoPrestamo;
	}

	/**
	 * Gets the saldo posterior.
	 *
	 * @return the saldo posterior
	 */
	public String getSaldoPosterior() {
		return saldoPosterior;
	}

	/**
	 * Sets the saldo posterior.
	 *
	 * @param saldoPosterior
	 *            the new saldo posterior
	 */
	public void setSaldoPosterior(String saldoPosterior) {
		this.saldoPosterior = saldoPosterior;
	}

	/**
	 * Gets the tiene otros impuestos.
	 *
	 * @return the tiene otros impuestos
	 */
	public Boolean getTieneOtrosImpuestos() {
        return tieneOtrosImpuestos;
    }

    /**
	 * Sets the tiene otros impuestos.
	 *
	 * @param tieneOtrosImpuestos
	 *            the new tiene otros impuestos
	 */
    public void setTieneOtrosImpuestos(Boolean tieneOtrosImpuestos) {
        this.tieneOtrosImpuestos = tieneOtrosImpuestos;
    }

    /**
	 * Gets the tiene seguro.
	 *
	 * @return the tiene seguro
	 */
    public Boolean getTieneSeguro() {
        return tieneSeguro;
    }

    /**
	 * Sets the tiene seguro.
	 *
	 * @param tieneSeguro
	 *            the new tiene seguro
	 */
    public void setTieneSeguro(Boolean tieneSeguro) {
        this.tieneSeguro = tieneSeguro;
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
	 * Gets the fecha alta prestamo.
	 *
	 * @return the fecha alta prestamo
	 */
	public String getFechaAltaPrestamo() {
		return fechaAltaPrestamo;
	}

	/**
	 * Sets the fecha alta prestamo.
	 *
	 * @param fechaAltaPrestamo
	 *            the fecha alta prestamo
	 */
	public void setFechaAltaPrestamo(String fechaAltaPrestamo) {
		this.fechaAltaPrestamo = fechaAltaPrestamo;
	}
	
	/**
	 * Gets the importe solicitado uvas.
	 *
	 * @return the importe solicitado uvas
	 */
	public String getImporteSolicitadoUvas() {
		return importeSolicitadoUvas;
	}

	/**
	 * Sets the importe solicitado uvas.
	 *
	 * @param importeSolicitadoUvas
	 *            the importe solicitado uvas
	 */
	public void setImporteSolicitadoUvas(String importeSolicitadoUvas) {
		this.importeSolicitadoUvas = importeSolicitadoUvas;
	}
	
	/**
	 * Gets the cotizacion uva.
	 *
	 * @return the cotizacion uva
	 */
	public String getCotizacionUva() {
		return cotizacionUva;
	}

	/**
	 * Sets the cotizacion uva.
	 *
	 * @param cotizacionUva
	 *            the cotizacion uva
	 */
	public void setCotizacionUva(String cotizacionUva) {
		this.cotizacionUva = cotizacionUva;
	}
	
	/**
	 * Gets the fecha cotizacion uva.
	 *
	 * @return the fecha cotizacion uva
	 */
	public String getFechaCotizacionUva() {
		return fechaCotizacionUva;
	}

	/**
	 * Sets the fecha cotizacion uva.
	 *
	 * @param fechaCotizacionUva
	 *            the fecha cotizacion uva
	 */
	public void setFechaCotizacionUva(String fechaCotizacionUva) {
		this.fechaCotizacionUva = fechaCotizacionUva;
	}

	/**
	 * Gets the is uva.
	 *
	 * @return the is uva
	 */
	public Boolean getIsUva() {
		return isUva;
	}

	/**
	 * Sets the is uva.
	 *
	 * @param isUva
	 *            the is uva
	 */
	public void setIsUva(Boolean isUva) {
		this.isUva = isUva;
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
		
		if (superPrestamoPersonal.equals(tipoPrestamo) || personal.equals(tipoPrestamo)){
			parametros.put(tipoPrestamoKey, superPrestamoPersonal);
			tipoPrestamo = "PERSONAL";
		}
		else if (superPrestamoHipotecario.equals(tipoPrestamo) || hipotecario.equals(tipoPrestamo)){
			parametros.put(tipoPrestamoKey, superPrestamoHipotecario);
			tipoPrestamo = "HIPOTECARIO";
		}
		else if (superPrestamoPrendario.equals(tipoPrestamo) || prendario.equals(tipoPrestamo)){
			parametros.put(tipoPrestamoKey, superPrestamoPrendario);
			tipoPrestamo = "PRENDARIO";
		}
		
		if (this.isUva) {
			parametros.put(importeSolicitadoUvasKey, this.getImporteSolicitadoUvas());
			parametros.put(cotizacionUvaKey, this.getCotizacionUva());
			parametros.put(fechaCotizacionUvaKey, this.getFechaCotizacionUva());
		}
		
		tituloComprobante = tituloPrestamo;
		parametros.put(tituloComprobanteKey, tituloComprobante);
		parametros.put(importeAcreditarKey, " ");
		parametros.put(importeSolicitadoKey, this.getImporteSolicitado());
		parametros.put(numeroPrestamoKey, this.getNumeroPrestamo());
		String cuentaDebito = "<b>";
		String descripcionTipoCuenta = StringUtils.EMPTY;
		String alias = StringUtils.EMPTY;
		if (!StringUtils.isBlank(this.getCuenta().getDescripcionTipoCuenta())) {
			descripcionTipoCuenta = this.getCuenta().getDescripcionTipoCuenta();
		}
		
		if (!StringUtils.isBlank(this.getCuenta().getAlias())) {
			alias = this.getCuenta().getAlias();
			cuentaDebito = cuentaDebito + "Cuenta \"" + this.getCuenta().getAlias() + "\"</b><br/>";
		}
		
		if (alias.isEmpty() && !descripcionTipoCuenta.isEmpty()) {
			cuentaDebito = cuentaDebito + this.getCuenta().getDescripcionTipoCuenta() + "</b><br/>";
		} else if (!alias.isEmpty() && !descripcionTipoCuenta.isEmpty()) {
			cuentaDebito = cuentaDebito + this.getCuenta().getDescripcionTipoCuenta() + "<br/>";
		}
		
		cuentaDebito = cuentaDebito + this.getCuenta().getNumeroFormateado();
		parametros.put(cuentaDebitoKey, cuentaDebito);
		parametros.put(fechaAltaDelPrestamoKey, this.getFechaAltaPrestamo());
		parametros.put(plazoCuotaKey, this.getPlazoPrestamo());
		parametros.put(tasaEfectivaAnualKey, this.getTasas().getTasaEfectivaAnual());
		parametros.put(tasaNominalAnualKey, this.getTasas().getTasaNominalAnual());
		parametros.put(motivoKey, this.getDestinoPrestamo());
		parametros.put(costoEfectivoKey,
				"CFTEA C/Imp: " + obtenerCampoOVacio(this.getTasas().getCostoFinancieroTotalAnual()));
		parametros.put(costoEfectivoSinImpuestosKey,
				"CFTEA S/Imp: " + obtenerCampoOVacio(this.getTasas().getCostoFinancieroTotalAnualSinImpuestos()));
		
		if(this.getLegalPlazoPrestamo() != null && !this.getLegalPlazoPrestamo().isEmpty()) {
			parametros.put(legal1Key, "(1) " + this.getLegalPlazoPrestamo());
		}
		
		parametros.put(fechaHoraActualKey, ISBANStringUtils.formatearFechaConHoraParaComprobante(new Date()));
		return parametros;
	}

	/**
	 * Obtener campo O vacio.
	 *
	 * @param valor
	 *            the valor
	 * @return the string
	 */
	private String obtenerCampoOVacio(String valor) {
		if (valor == null) {
			return "";
		}
		return valor;
	}

	public String getLegalPlazoPrestamo() {
		return legalPlazoPrestamo;
	}

	public void setLegalPlazoPrestamo(String legalPlazoPrestamo) {
		this.legalPlazoPrestamo = legalPlazoPrestamo;
	}
}
