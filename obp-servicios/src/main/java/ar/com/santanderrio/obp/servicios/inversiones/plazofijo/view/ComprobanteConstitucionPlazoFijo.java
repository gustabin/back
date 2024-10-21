/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view;

import java.io.FileNotFoundException;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

import ar.com.santanderrio.obp.servicios.comprobantes.web.view.DetalleComprobanteView;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;

/**
 * The Class ComprobanteConstitucionPlazoFijo.
 */
public class ComprobanteConstitucionPlazoFijo extends DetalleComprobanteView{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ComprobanteConstitucionPlazoFijo.class);
	
	/** The importe. */
	private String importe;
	
	/** The tipo plazo fijo. */
	private String tipoPlazoFijo;
	
	/** The plazo. */
	private String plazo;
	
	/** The moneda. */
	private String moneda;
	
	/** The cuenta debito. */
	private String cuentaDebito;
	
	/** The tipo cuenta debito. */
	private String tipoCuentaDebito;
	
	/** The frecuencia cobro intereses. */
	private String frecuenciaCobroIntereses = "";
	
	/** The tasa nominal anual. */
	private String tasaNominalAnual;
	
	/** The tasa efectiva anual. */
	private String tasaEfectivaAnual;
	
	/** The intereses. */
	private String intereses;
	
	/** The intereses label. */
	private String interesesLabel;
	
	/** The fecha constitucion. */
	private String fechaConstitucion;
	
	/** The fecha vencimiento. */
	private String fechaVencimiento;
	
	/** The accion al vencimiento. */
	private String accionAlVencimiento;
	
	/** The fecha minima cancelacion. */
	private String fechaMinimaCancelacion = "";
	
	/** The porcentaje penalizacion. */
	private String porcentajePenalizacion = "";
	
	/** The comprobante. */
	private String comprobante;
	
	/** The fecha actual. */
	private String fechaActual;
	
	/** The legales. */
	private String legales;
	
	/** The impuestos 1. */
	private String impuestos1;
	
	/** The impuestos label 1. */
	private String impuestosLabel1;
	
	/** The impuestos 2. */
	private String impuestos2;
	
	/** The impuestos label 2. */
	private String impuestosLabel2;
	
	/** The impuestos 3. */
	private String impuestos3;
	
	/** The impuestos label 3. */
	private String impuestosLabel3;
	
	/** The impuestos 4. */
	private String impuestos4;
	
	/** The impuestos label 4. */
	private String impuestosLabel4;
	
	/** The impuestos 5. */
	private String impuestos5;
	
	/** The impuestos label 5. */
	private String impuestosLabel5;
	
	/** The intereses 1. */
	private String intereses1;
	
	/** The intereses label 1. */
	private String interesesLabel1;
	
	/** The intereses 2. */
	private String intereses2;
	
	/** The intereses label 2. */
	private String interesesLabel2;
	
	/** The intereses 3. */
	private String intereses3;
	
	/** The intereses label 3. */
	private String interesesLabel3;
	
	/** The intereses 4. */
	private String intereses4;
	
	/** The intereses label 4. */
	private String interesesLabel4;
	
	/** The intereses 5. */
	private String intereses5;
	
	/** The intereses label 5. */
	private String interesesLabel5;
	
	/** The intereses neto. */
	private String interesesNeto;
	
	/** The tipo PF. */
	private String tipoPF;
	
	/** The saldo inic ur. */
	private String saldoInicUr ;
	
	/** The cotizacion codigo ur. */
	private String cotizacionCodigoUr;

	/** The encabezado. */
	private String encabezado;
	
	/** The importe key. */
	protected final String importeKey = "IMPORTE";
	
	/** The tipo plazo fijo key. */
	protected final String tipoPlazoFijoKey = "TIPO_PLAZO_FIJO";
	
	/** The plazo key. */
	protected final String plazoKey = "PLAZO";
	
	/** The moneda key. */
	protected final String monedaKey = "MONEDA";
	
	/** The cuenta debito key. */
	protected final String cuentaDebitoKey = "CUENTA_DEBITO";
	
	/** The tipo cuenta debito key. */
	protected final String tipoCuentaDebitoKey = "TIPO_CUENTA_DEBITO";
	
	/** The frecuencia cobro intereses key. */
	protected final String frecuenciaCobroInteresesKey = "FRECUENCIA_COBRO_INTERESES";
	
	/** The tasa nominal anual key. */
	protected final String tasaNominalAnualKey = "TASA_NOMINAL_ANUAL";
	
	/** The tasa efectiva anual key. */
	protected final String tasaEfectivaAnualKey = "TASA_EFECTIVA_ANUAL";
	
	/** The intereses key. */
	protected final String interesesKey = "INTERESES";
	
	/** The intereses label key. */
	protected final String interesesLabelKey = "INTERESES_LABEL";
	
	/** The fecha constitucion key. */
	protected final String fechaConstitucionKey = "FECHA_CONSTITUCION";
	
	/** The fecha vencimiento key. */
	protected final String fechaVencimientoKey = "FECHA_VENCIMIENtO";
	
	/** The accion al vencimiento key. */
	protected final String accionAlVencimientoKey = "ACCION_AL_VENCIMIENTO";
	
	/** The fecha minima cancelacion key. */
	protected final String fechaMinimaCancelacionKey = "FECHA_MINIMA_CANCELACION";
	
	/** The porcentaje penalizacion key. */
	protected final String porcentajePenalizacionKey = "PORCENTAJE_PENALIZACION";
	
	/** The comprobante key. */
	protected final String comprobanteKey = "COMPROBANTE";
	
	/** The descripcion key. */
	protected final String descripcionKey = "DESCRIPCION";
	
	/** The fecha actual key. */
	protected final String fechaActualKey = "FECHA_ACTUAL";
	
	/** The legales key. */
	protected final String legalesKey = "LEGALES";
	
	/** The logo pie key. */
	protected final String logoPieKey = "LOGO_PIE";
	
	/** The mensaje capital key. */
	protected final String mensajeCapitalKey ="MENSAJE_CAPITAL";
	
	/** The impuestos key 1. */
	protected final String impuestosKey1 = "IMPUESTOS1";
	
	/** The impuestos label key 1. */
	protected final String impuestosLabelKey1 = "IMPUESTOS_LABEL1";
	
	/** The impuestos key 2. */
	protected final String impuestosKey2 = "IMPUESTOS2";
	
	/** The impuestos label key 2. */
	protected final String impuestosLabelKey2 = "IMPUESTOS_LABEL2";
	
	/** The impuestos key 3. */
	protected final String impuestosKey3 = "IMPUESTOS3";
	
	/** The impuestos label key 3. */
	protected final String impuestosLabelKey3 = "IMPUESTOS_LABEL3";
	
	/** The impuestos key 4. */
	protected final String impuestosKey4 = "IMPUESTOS4";
	
	/** The impuestos label key 4. */
	protected final String impuestosLabelKey4 = "IMPUESTOS_LABEL4";
	
	/** The impuestos key 5. */
	protected final String impuestosKey5 = "IMPUESTOS5";
	
	/** The impuestos label key 5. */
	protected final String impuestosLabelKey5 = "IMPUESTOS_LABEL5";
	
	/** The intereses key 1. */
	protected final String interesesKey1 = "INTERESES1";
	
	/** The intereses label key 1. */
	protected final String interesesLabelKey1 = "INTERESES_LABEL1";
	
	/** The intereses key 2. */
	protected final String interesesKey2 = "INTERESES2";
	
	/** The intereses label key 2. */
	protected final String interesesLabelKey2 = "INTERESES_LABEL2";
	
	/** The intereses key 3. */
	protected final String interesesKey3 = "INTERESES3";
	
	/** The intereses label key 3. */
	protected final String interesesLabelKey3 = "INTERESES_LABEL3";
	
	/** The intereses key 4. */
	protected final String interesesKey4 = "INTERESES4";
	
	/** The intereses label key 4. */
	protected final String interesesLabelKey4 = "INTERESES_LABEL4";
	
	/** The intereses key 5. */
	protected final String interesesKey5 = "INTERESES5";
	
	/** The intereses label key 5. */
	protected final String interesesLabelKey5 = "INTERESES_LABEL5";
	
	/** The intereses neto key. */
	protected final String interesesNetoKey = "INTERESES_NETO";
	
    /** The capital UVA key. */
    protected final String capitalUVAKey = "CAPITAL_UVA";
    
    /** The valor UVA key. */
    protected final String valorUVAKey = "VALOR_UVA";
    
	/** The suscripcion jasper. */
	protected final String suscripcionJasper = "constitucion-plazofijo.jasper";
	
	/** The path logo pie. */
	protected final String pathLogoPie = "logo_cierre_comprobante.png";
	
	/** The path inversiones. */
	protected final String pathInversiones = "inversiones/";
	
	/** The descripcion tradicional. */
	protected final String descripcionTradicional = "Constitución de depósito a Plazo<br/>Fijo nominativo intransferible a<br/>tasa fija.";
	
	/** The descripcion interesante. */
	protected final String descripcionInteresante = "Constitución de depósito a Plazo<br/>Fijo nominativo intransferible con<br/>pago periódico de intereses.";
	
	/** The descripcion precancelable. */
	protected final String descripcionPrecancelable = "Constitución de depósito a Plazo<br/>Fijo nominativo intransferible<br/>con opción de cancelación<br/>anticipada.";
	
    /** The descripcion UVA. */
    protected final String descripcionUVA = "Constitución de depósito a Plazo Fijo nominativo intransferible de valor adquisitivo actualizable por CER Ley 25.287(\"UVA\")";

	/** The mensaje capital. */
	protected final String mensajeCapital ="Capital a invertir en ";
	

	/**
	 * Gets the importe.
	 *
	 * @return the importe
	 */
	public String getImporte() {
		return importe;
	}

	/**
	 * Sets the importe.
	 *
	 * @param importe
	 *            the importe to set
	 */
	public void setImporte(String importe) {
		this.importe = importe;
	}

	/**
	 * Gets the tipo plazo fijo.
	 *
	 * @return the tipoPlazoFijo
	 */
	public String getTipoPlazoFijo() {
		return tipoPlazoFijo;
	}

	/**
	 * Sets the tipo plazo fijo.
	 *
	 * @param tipoPlazoFijo
	 *            the tipoPlazoFijo to set
	 */
	public void setTipoPlazoFijo(String tipoPlazoFijo) {
		this.tipoPlazoFijo = tipoPlazoFijo;
	}

	/**
	 * Gets the plazo.
	 *
	 * @return the plazo
	 */
	public String getPlazo() {
		return plazo;
	}

	/**
	 * Sets the plazo.
	 *
	 * @param plazo
	 *            the plazo to set
	 */
	public void setPlazo(String plazo) {
		this.plazo = plazo;
	}

	/**
	 * Gets the moneda.
	 *
	 * @return the moneda
	 */
	public String getMoneda() {
		return moneda;
	}

	/**
	 * Sets the moneda.
	 *
	 * @param moneda
	 *            the moneda to set
	 */
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	/**
	 * Gets the cuenta debito.
	 *
	 * @return the cuentaDebito
	 */
	public String getCuentaDebito() {
		return cuentaDebito;
	}

	/**
	 * Sets the cuenta debito.
	 *
	 * @param cuentaDebito
	 *            the cuentaDebito to set
	 */
	public void setCuentaDebito(String cuentaDebito) {
		this.cuentaDebito = cuentaDebito;
	}

	/**
	 * Gets the tipo cuenta debito.
	 *
	 * @return the tipoCuentaDebito
	 */
	public String getTipoCuentaDebito() {
		return tipoCuentaDebito;
	}

	/**
	 * Sets the tipo cuenta debito.
	 *
	 * @param tipoCuentaDebito
	 *            the tipoCuentaDebito to set
	 */
	public void setTipoCuentaDebito(String tipoCuentaDebito) {
		this.tipoCuentaDebito = tipoCuentaDebito;
	}

	/**
	 * Gets the frecuencia cobro intereses.
	 *
	 * @return the frecuenciaCobroDeIntereses
	 */
	public String getFrecuenciaCobroIntereses() {
		return frecuenciaCobroIntereses;
	}

	/**
	 * Sets the frecuencia cobro intereses.
	 *
	 * @param frecuenciaCobroIntereses
	 *            the new frecuencia cobro intereses
	 */
	public void setFrecuenciaCobroIntereses(String frecuenciaCobroIntereses) {
		this.frecuenciaCobroIntereses = frecuenciaCobroIntereses;
	}

	/**
	 * Gets the tasa nominal anual.
	 *
	 * @return the tasaNominalAnual
	 */
	public String getTasaNominalAnual() {
		return tasaNominalAnual;
	}

	/**
	 * Sets the tasa nominal anual.
	 *
	 * @param tasaNominalAnual
	 *            the tasaNominalAnual to set
	 */
	public void setTasaNominalAnual(String tasaNominalAnual) {
		this.tasaNominalAnual = tasaNominalAnual;
	}

	/**
	 * Gets the tasa efectiva anual.
	 *
	 * @return the tasaEfectivaAnual
	 */
	public String getTasaEfectivaAnual() {
		return tasaEfectivaAnual;
	}

	/**
	 * Sets the tasa efectiva anual.
	 *
	 * @param tasaEfectivaAnual
	 *            the tasaEfectivaAnual to set
	 */
	public void setTasaEfectivaAnual(String tasaEfectivaAnual) {
		this.tasaEfectivaAnual = tasaEfectivaAnual;
	}

	/**
	 * Gets the intereses.
	 *
	 * @return the intereses
	 */
	public String getIntereses() {
		return intereses;
	}

	/**
	 * Sets the intereses.
	 *
	 * @param intereses
	 *            the intereses to set
	 */
	public void setIntereses(String intereses) {
		this.intereses = intereses;
	}

	
	/**
	 * Gets the intereses label.
	 *
	 * @return the interesesLabel
	 */
	public String getInteresesLabel() {
		return interesesLabel;
	}

	/**
	 * Sets the intereses label.
	 *
	 * @param interesesLabel
	 *            the interesesLabel to set
	 */
	public void setInteresesLabel(String interesesLabel) {
		this.interesesLabel = interesesLabel;
	}

	/**
	 * Gets the fecha constitucion.
	 *
	 * @return the fechaConstitucion
	 */
	public String getFechaConstitucion() {
		return fechaConstitucion;
	}

	/**
	 * Sets the fecha constitucion.
	 *
	 * @param fechaConstitucion
	 *            the fechaConstitucion to set
	 */
	public void setFechaConstitucion(String fechaConstitucion) {
		this.fechaConstitucion = fechaConstitucion;
	}

	/**
	 * Gets the fecha vencimiento.
	 *
	 * @return the fechaVencimiento
	 */
	public String getFechaVencimiento() {
		return fechaVencimiento;
	}

	/**
	 * Sets the fecha vencimiento.
	 *
	 * @param fechaVencimiento
	 *            the fechaVencimiento to set
	 */
	public void setFechaVencimiento(String fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	/**
	 * Gets the accion al vencimiento.
	 *
	 * @return the accionAlVencimiento
	 */
	public String getAccionAlVencimiento() {
		return accionAlVencimiento;
	}

	/**
	 * Sets the accion al vencimiento.
	 *
	 * @param accionAlVencimiento
	 *            the accionAlVencimiento to set
	 */
	public void setAccionAlVencimiento(String accionAlVencimiento) {
		this.accionAlVencimiento = accionAlVencimiento;
	}

	/**
	 * Gets the fecha minima cancelacion.
	 *
	 * @return the fechaMinimaCancelacion
	 */
	public String getFechaMinimaCancelacion() {
		return fechaMinimaCancelacion;
	}

	/**
	 * Sets the fecha minima cancelacion.
	 *
	 * @param fechaMinimaCancelacion
	 *            the fechaMinimaCancelacion to set
	 */
	public void setFechaMinimaCancelacion(String fechaMinimaCancelacion) {
		this.fechaMinimaCancelacion = fechaMinimaCancelacion;
	}

	/**
	 * Gets the porcentaje penalizacion.
	 *
	 * @return the porcentajePenalizacion
	 */
	public String getPorcentajePenalizacion() {
		return porcentajePenalizacion;
	}

	/**
	 * Sets the porcentaje penalizacion.
	 *
	 * @param porcentajePenalizacion
	 *            the porcentajePenalizacion to set
	 */
	public void setPorcentajePenalizacion(String porcentajePenalizacion) {
		this.porcentajePenalizacion = porcentajePenalizacion;
	}

	/**
	 * Gets the comprobante.
	 *
	 * @return the comprobante
	 */
	public String getComprobante() {
		return comprobante;
	}

	/**
	 * Sets the comprobante.
	 *
	 * @param comprobante
	 *            the comprobante to set
	 */
	public void setComprobante(String comprobante) {
		this.comprobante = comprobante;
	}
	
	/**
	 * Gets the fecha actual.
	 *
	 * @return the fechaActual
	 */
	public String getFechaActual() {
		return fechaActual;
	}

	/**
	 * Sets the fecha actual.
	 *
	 * @param fechaActual
	 *            the fechaActual to set
	 */
	public void setFechaActual(String fechaActual) {
		this.fechaActual = fechaActual;
	}

	/**
	 * Gets the legales.
	 *
	 * @return the legales
	 */
	public String getLegales() {
		return legales;
	}

	/**
	 * Sets the legales.
	 *
	 * @param legales
	 *            the legales to set
	 */
	public void setLegales(String legales) {
		this.legales = legales;
	}
	
	/**
	 * Gets the impuestos 1.
	 *
	 * @return the impuestos1
	 */
	public String getImpuestos1() {
		return impuestos1;
	}

	/**
	 * Sets the impuestos 1.
	 *
	 * @param impuestos1
	 *            the impuestos1 to set
	 */
	public void setImpuestos1(String impuestos1) {
		this.impuestos1 = impuestos1;
	}

	/**
	 * Gets the impuestos label 1.
	 *
	 * @return the impuestosLabel1
	 */
	public String getImpuestosLabel1() {
		return impuestosLabel1;
	}

	/**
	 * Sets the impuestos label 1.
	 *
	 * @param impuestosLabel1
	 *            the impuestosLabel1 to set
	 */
	public void setImpuestosLabel1(String impuestosLabel1) {
		this.impuestosLabel1 = impuestosLabel1;
	}

	/**
	 * Gets the impuestos 2.
	 *
	 * @return the impuestos2
	 */
	public String getImpuestos2() {
		return impuestos2;
	}

	/**
	 * Sets the impuestos 2.
	 *
	 * @param impuestos2
	 *            the impuestos2 to set
	 */
	public void setImpuestos2(String impuestos2) {
		this.impuestos2 = impuestos2;
	}

	/**
	 * Gets the impuestos label 2.
	 *
	 * @return the impuestosLabel2
	 */
	public String getImpuestosLabel2() {
		return impuestosLabel2;
	}

	/**
	 * Sets the impuestos label 2.
	 *
	 * @param impuestosLabel2
	 *            the impuestosLabel2 to set
	 */
	public void setImpuestosLabel2(String impuestosLabel2) {
		this.impuestosLabel2 = impuestosLabel2;
	}

	/**
	 * Gets the impuestos 3.
	 *
	 * @return the impuestos3
	 */
	public String getImpuestos3() {
		return impuestos3;
	}

	/**
	 * Sets the impuestos 3.
	 *
	 * @param impuestos3
	 *            the impuestos3 to set
	 */
	public void setImpuestos3(String impuestos3) {
		this.impuestos3 = impuestos3;
	}

	/**
	 * Gets the impuestos label 3.
	 *
	 * @return the impuestosLabel3
	 */
	public String getImpuestosLabel3() {
		return impuestosLabel3;
	}

	/**
	 * Sets the impuestos label 3.
	 *
	 * @param impuestosLabel3
	 *            the impuestosLabel3 to set
	 */
	public void setImpuestosLabel3(String impuestosLabel3) {
		this.impuestosLabel3 = impuestosLabel3;
	}

	/**
	 * Gets the impuestos 4.
	 *
	 * @return the impuestos4
	 */
	public String getImpuestos4() {
		return impuestos4;
	}

	/**
	 * Sets the impuestos 4.
	 *
	 * @param impuestos4
	 *            the impuestos4 to set
	 */
	public void setImpuestos4(String impuestos4) {
		this.impuestos4 = impuestos4;
	}

	/**
	 * Gets the impuestos label 4.
	 *
	 * @return the impuestosLabel4
	 */
	public String getImpuestosLabel4() {
		return impuestosLabel4;
	}

	/**
	 * Sets the impuestos label 4.
	 *
	 * @param impuestosLabel4
	 *            the impuestosLabel4 to set
	 */
	public void setImpuestosLabel4(String impuestosLabel4) {
		this.impuestosLabel4 = impuestosLabel4;
	}

	/**
	 * Gets the impuestos 5.
	 *
	 * @return the impuestos5
	 */
	public String getImpuestos5() {
		return impuestos5;
	}

	/**
	 * Sets the impuestos 5.
	 *
	 * @param impuestos5
	 *            the impuestos5 to set
	 */
	public void setImpuestos5(String impuestos5) {
		this.impuestos5 = impuestos5;
	}

	/**
	 * Gets the impuestos label 5.
	 *
	 * @return the impuestosLabel5
	 */
	public String getImpuestosLabel5() {
		return impuestosLabel5;
	}

	/**
	 * Sets the impuestos label 5.
	 *
	 * @param impuestosLabel5
	 *            the impuestosLabel5 to set
	 */
	public void setImpuestosLabel5(String impuestosLabel5) {
		this.impuestosLabel5 = impuestosLabel5;
	}

	
	/**
	 * Gets the intereses 1.
	 *
	 * @return the intereses1
	 */
	public String getIntereses1() {
		return intereses1;
	}

	/**
	 * Sets the intereses 1.
	 *
	 * @param intereses1
	 *            the intereses1 to set
	 */
	public void setIntereses1(String intereses1) {
		this.intereses1 = intereses1;
	}

	/**
	 * Gets the intereses label 1.
	 *
	 * @return the interesesLabel1
	 */
	public String getInteresesLabel1() {
		return interesesLabel1;
	}

	/**
	 * Sets the intereses label 1.
	 *
	 * @param interesesLabel1
	 *            the interesesLabel1 to set
	 */
	public void setInteresesLabel1(String interesesLabel1) {
		this.interesesLabel1 = interesesLabel1;
	}

	/**
	 * Gets the intereses 2.
	 *
	 * @return the intereses2
	 */
	public String getIntereses2() {
		return intereses2;
	}

	/**
	 * Sets the intereses 2.
	 *
	 * @param intereses2
	 *            the intereses2 to set
	 */
	public void setIntereses2(String intereses2) {
		this.intereses2 = intereses2;
	}

	/**
	 * Gets the intereses label 2.
	 *
	 * @return the interesesLabel2
	 */
	public String getInteresesLabel2() {
		return interesesLabel2;
	}

	/**
	 * Sets the intereses label 2.
	 *
	 * @param interesesLabel2
	 *            the interesesLabel2 to set
	 */
	public void setInteresesLabel2(String interesesLabel2) {
		this.interesesLabel2 = interesesLabel2;
	}

	/**
	 * Gets the intereses 3.
	 *
	 * @return the intereses3
	 */
	public String getIntereses3() {
		return intereses3;
	}

	/**
	 * Sets the intereses 3.
	 *
	 * @param intereses3
	 *            the intereses3 to set
	 */
	public void setIntereses3(String intereses3) {
		this.intereses3 = intereses3;
	}

	/**
	 * Gets the intereses label 3.
	 *
	 * @return the interesesLabel3
	 */
	public String getInteresesLabel3() {
		return interesesLabel3;
	}

	/**
	 * Sets the intereses label 3.
	 *
	 * @param interesesLabel3
	 *            the interesesLabel3 to set
	 */
	public void setInteresesLabel3(String interesesLabel3) {
		this.interesesLabel3 = interesesLabel3;
	}

	/**
	 * Gets the intereses 4.
	 *
	 * @return the intereses4
	 */
	public String getIntereses4() {
		return intereses4;
	}

	/**
	 * Sets the intereses 4.
	 *
	 * @param intereses4
	 *            the intereses4 to set
	 */
	public void setIntereses4(String intereses4) {
		this.intereses4 = intereses4;
	}

	/**
	 * Gets the intereses label 4.
	 *
	 * @return the interesesLabel4
	 */
	public String getInteresesLabel4() {
		return interesesLabel4;
	}

	/**
	 * Sets the intereses label 4.
	 *
	 * @param interesesLabel4
	 *            the interesesLabel4 to set
	 */
	public void setInteresesLabel4(String interesesLabel4) {
		this.interesesLabel4 = interesesLabel4;
	}

	/**
	 * Gets the intereses 5.
	 *
	 * @return the intereses5
	 */
	public String getIntereses5() {
		return intereses5;
	}

	/**
	 * Sets the intereses 5.
	 *
	 * @param intereses5
	 *            the intereses5 to set
	 */
	public void setIntereses5(String intereses5) {
		this.intereses5 = intereses5;
	}

	/**
	 * Gets the intereses label 5.
	 *
	 * @return the interesesLabel5
	 */
	public String getInteresesLabel5() {
		return interesesLabel5;
	}

	/**
	 * Sets the intereses label 5.
	 *
	 * @param interesesLabel5
	 *            the interesesLabel5 to set
	 */
	public void setInteresesLabel5(String interesesLabel5) {
		this.interesesLabel5 = interesesLabel5;
	}
	
	/**
	 * Gets the intereses neto.
	 *
	 * @return the interesesNeto
	 */
	public String getInteresesNeto() {
		return interesesNeto;
	}

	/**
	 * Sets the intereses neto.
	 *
	 * @param interesesNeto
	 *            the interesesNeto to set
	 */
	public void setInteresesNeto(String interesesNeto) {
		this.interesesNeto = interesesNeto;
	}
	
	/**
	 * Gets the tipo PF.
	 *
	 * @return the tipoPF
	 */
	public String getTipoPF() {
		return tipoPF;
	}

	/**
	 * Sets the tipo PF.
	 *
	 * @param tipoPF
	 *            the tipoPF to set
	 */
	public void setTipoPF(String tipoPF) {
		this.tipoPF = tipoPF;
	}
	
    /**
	 * Gets the saldo inic ur.
	 *
	 * @return the saldoInicUr
	 */
    public String getSaldoInicUr() {
        return saldoInicUr;
    }

    /**
	 * Sets the saldo inic ur.
	 *
	 * @param saldoInicUr
	 *            the saldoInicUr to set
	 */
    public void setSaldoInicUr(String saldoInicUr) {
        this.saldoInicUr = saldoInicUr;
    }

    /**
	 * Gets the cotizacion codigo ur.
	 *
	 * @return the cotizacionCodigoUr
	 */
    public String getCotizacionCodigoUr() {
        return cotizacionCodigoUr;
    }

    /**
	 * Sets the cotizacion codigo ur.
	 *
	 * @param cotizacionCodigoUr
	 *            the cotizacionCodigoUr to set
	 */
    public void setCotizacionCodigoUr(String cotizacionCodigoUr) {
        this.cotizacionCodigoUr = cotizacionCodigoUr;
    }

    public String getEncabezado() {
		return encabezado;
	}

	public void setEncabezado(String encabezado) {
		this.encabezado = encabezado;
	}

	/* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.comprobantes.web.view.DetalleComprobanteView#obtenerParametrosPDF()
     */
    @Override
	public HashMap<String, Object> obtenerParametrosPDF() {
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		ponerParametros(parametros);
		return parametros;
	}
	
	/**
	 * Poner parametros.
	 *
	 * @param parametros
	 *            the parametros
	 */
	protected void ponerParametros(HashMap<String, Object> parametros) {
		parametros.put(importeKey, getImporte());
		parametros.put(tipoPlazoFijoKey, getTipoPlazoFijo());
		parametros.put(plazoKey, getPlazo());
		parametros.put(cuentaDebitoKey, getCuentaDebito());
		parametros.put(tipoCuentaDebitoKey, getTipoCuentaDebito());
		parametros.put(frecuenciaCobroInteresesKey, getFrecuenciaCobroIntereses());
		parametros.put(tasaNominalAnualKey, getTasaNominalAnual());
		parametros.put(tasaEfectivaAnualKey, getTasaEfectivaAnual());
		parametros.put(interesesKey, getIntereses());
		parametros.put(fechaConstitucionKey, getFechaConstitucion());
		parametros.put(fechaVencimientoKey, getFechaVencimiento());
		parametros.put(accionAlVencimientoKey, getAccionAlVencimiento());
		parametros.put(fechaMinimaCancelacionKey, getFechaMinimaCancelacion());
		parametros.put(comprobanteKey, getComprobante());
		parametros.put(fechaActualKey, getFechaActual());
		parametros.put(legalesKey, getLegales());
		parametros.put(mensajeCapitalKey, mensajeCapital);
		parametros.put(monedaKey, getMoneda());
		parametros.put(impuestosKey1, getImpuestos1());
		parametros.put(impuestosLabelKey1, getImpuestosLabel1());
		parametros.put(impuestosKey2, getImpuestos2());
		parametros.put(impuestosLabelKey2, getImpuestosLabel2());
		parametros.put(impuestosKey3, getImpuestos3());
		parametros.put(impuestosLabelKey3, getImpuestosLabel3());
		parametros.put(impuestosKey4, getImpuestos4());
		parametros.put(impuestosLabelKey4, getImpuestosLabel4());
		parametros.put(impuestosKey5, getImpuestos5());
		parametros.put(impuestosLabelKey5, getImpuestosLabel5());
		parametros.put(interesesKey1, getIntereses1());
		parametros.put(interesesLabelKey1, getInteresesLabel1());
		parametros.put(interesesKey2, getIntereses2());
		parametros.put(interesesLabelKey2, getInteresesLabel2());
		parametros.put(interesesKey3, getIntereses3());
		parametros.put(interesesLabelKey3, getInteresesLabel3());
		parametros.put(interesesKey4, getIntereses4());
		parametros.put(interesesLabelKey4, getInteresesLabel4());
		parametros.put(interesesKey5, getIntereses5());
		parametros.put(interesesLabelKey5, getInteresesLabel5());
		parametros.put(interesesNetoKey, getInteresesNeto());
		this.setTituloComprobante("Comprobante de constitución de Súper Plazo Fijo");
		parametros = aplicarLogicaCampos(parametros);
		try {
			parametros.put(logoPieKey, ResourceUtils.getFile(path + pathLogoPie).getPath());
		} catch (FileNotFoundException e) {
			LOGGER.error("No se encontro el archivo jasper", e);
		}
	}
	
	/**
	 * Aplicar logica campos.
	 *
	 * @param parametros
	 *            the parametros
	 * @return the hash map
	 */
	protected HashMap<String, Object> aplicarLogicaCampos(HashMap<String, Object> parametros){
		if("Interesante Tasa Fija".equals(getTipoPlazoFijo())){
			parametros.put(interesesLabelKey, "Intereses*");
			parametros.put(descripcionKey, descripcionInteresante);
		}else {
			parametros.put(interesesLabelKey, "Intereses");
		}
		
		if("Tradicional".equals(getTipoPlazoFijo()) || "94".equalsIgnoreCase(getTipoPF())){
			parametros.put(descripcionKey, descripcionTradicional);
		}else if("Precancelable".equals(getTipoPlazoFijo())){
			parametros.put(descripcionKey, descripcionPrecancelable);
			parametros.put(porcentajePenalizacionKey, getPorcentajePenalizacion());
		}else if("UVA".equals(getTipoPlazoFijo())){
            parametros.put(descripcionKey, descripcionUVA);
            String capitalUVA = ISBANStringUtils.formatearConComaYDosDecimales(this.saldoInicUr);
            String valorUVA = ISBANStringUtils.formatearConComaYDosDecimales(this.cotizacionCodigoUr);
            parametros.put(capitalUVAKey, capitalUVA);
            parametros.put(valorUVAKey, valorUVA);
        } if("UVA Precancelable".equals(getTipoPlazoFijo())){
        	parametros.put(descripcionKey, getEncabezado());
			parametros.put(porcentajePenalizacionKey, getPorcentajePenalizacion());
			String capitalUVA = ISBANStringUtils.formatearConComaYDosDecimales(this.saldoInicUr);
            String valorUVA = ISBANStringUtils.formatearConComaYDosDecimales(this.cotizacionCodigoUr);
            parametros.put(capitalUVAKey, capitalUVA);
            parametros.put(valorUVAKey, valorUVA);
        }
		
		return parametros;
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.web.view.DetalleComprobanteView#obtenerJasper()
	 */
	@Override
	public String obtenerJasper() throws FileNotFoundException {
		return ResourceUtils.getFile(path + pathInversiones + suscripcionJasper).getPath();
	}

}
