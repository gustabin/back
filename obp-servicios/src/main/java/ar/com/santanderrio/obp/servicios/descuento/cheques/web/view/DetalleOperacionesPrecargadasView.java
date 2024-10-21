/*
 * 
 */
package ar.com.santanderrio.obp.servicios.descuento.cheques.web.view;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.ResourceUtils;

import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.descuento.cheques.entities.ChequesAceptadosDTO;
import ar.com.santanderrio.obp.servicios.descuento.cheques.entities.ChequesRechazadosDTO;
import ar.com.santanderrio.obp.servicios.descuento.cheques.entities.DetalleOperacionesPrecargadasDTO;

/**
 * The Class DetalleOperacionesPrecargadasView.
 */
public class DetalleOperacionesPrecargadasView {
	
	/** The es simulacion. */
	private Boolean esSimulacion = Boolean.FALSE;
	
	/** The titulo. */
	private String titulo;
	
	/** The numero tramite. */
	private String numeroTramite;
	
	/** The importe acreditar. */
	private String importeAcreditar;
	
	/** The cuenta. */
	private String cuenta;
	
	/** The numero operacion. */
	private String numeroOperacion;
	
	/** The total cheques descontados. */
	private String totalChequesDescontados;
	
	/** The total. */
	private String total;
	
	/** The impuesto. */
	private String impuesto;
	
	/** The intereses. */
	private String intereses;
	
	/** The a acreditar. */
	private String aAcreditar;
	
	/** The cheques aceptados. */
	private List<ChequesAceptadosView> chequesAceptados = new ArrayList<ChequesAceptadosView>();
	
	/** The importe. */
	private String importe;
	
	/** The total cheques rechazados. */
	private String totalChequesRechazados;
	
	/** The cheques rechazados. */
	private List<ChequesRechazadosView> chequesRechazados = new ArrayList<ChequesRechazadosView>();

	/** The com admin cheques. */
	private String comAdminCheques;
	
	/** The tasa nominal anual. */
	private String tasaNominalAnual;
	
	/** The tasa efectiva anual. */
	private String tasaEfectivaAnual;
	
	/** The costo financiero total. */
	private String costoFinancieroTotal;
	
	/** The mensaje informativo. */
	private String mensajeInformativo;
	
	/** The legal pie 1. */
	private String legalPie1;
	
	/** The legal pie 2. */
	private String legalPie2;
	
	/** The legal pie 3. */
	private String legalPie3;
	
	/** The path. */
	protected static final String PATH = "classpath:/report/descuentoCheques/";
	
	/** The titulo. */
	protected static final String TITULO_KEY = "TITULO_COMPROBANTE";
	
	/** The Constant NUMERO_TRAMITE_KEY. */
	protected static final String NUMERO_TRAMITE_KEY = "NUMERO_TRAMITE";
	
	/** The Constant IMPORTE_ACREDITAR_KEY. */
	protected static final String IMPORTE_ACREDITAR_KEY = "IMPORTE_ACREDITAR";
	
	/** The Constant CUENTA_KEY. */
	protected static final String CUENTA_KEY = "CUENTA";
	
	/** The Constant NUMERO_OPERACION_KEY. */
	protected static final String NUMERO_OPERACION_KEY = "NUMERO_OPERACION";
	
	/** The Constant TOTAL_CHEQUES_DESCONTADOS_KEY. */
	protected static final String TOTAL_CHEQUES_DESCONTADOS_KEY = "TOTAL_DESCONTADOS";
	
	/** The Constant TOTAL_KEY. */
	protected static final String TOTAL_KEY = "TOTAL";
	
	/** The Constant IMPUESTO_KEY. */
	protected static final String IMPUESTO_KEY = "IMPUESTO";
	
	/** The Constant INTERESES_KEY. */
	protected static final String INTERESES_KEY = "INTERESES";
	
	/** The Constant A_ACREDITAR_KEY. */
	protected static final String A_ACREDITAR_KEY = "A_ACREDITAR";
	
	/** The Constant CHEQUES_ACEPTADOS_KEY. */
	protected static final String CHEQUES_ACEPTADOS_KEY = "CHEQUES_ACEPTADOS";
	
	/** The Constant IMPORTE_KEY. */
	protected static final String IMPORTE_KEY = "IMPORTE";

	/** The Constant TOTAL_CHEQUES_RECHAZADOS_KEY. */
	protected static final String TOTAL_CHEQUES_RECHAZADOS_KEY = "TOTAL_CHEQUES_RECHAZADOS";
	
	/** The Constant CHEQUES_RECHAZADOS_KEY. */
	protected static final String CHEQUES_RECHAZADOS_KEY = "CHEQUES_RECHAZADOS";
	
	/** The Constant COM_ADMIN_CHEQUES_KEY. */
	protected static final String COM_ADMIN_CHEQUES_KEY = "COM_ADMIN_CHEQUES";

	/** The Constant TASA_NOMINAL_ANUAL_KEY. */
	protected static final String TASA_NOMINAL_ANUAL_KEY = "TASA_NOMINAL_ANUAL";
	
	/** The Constant TASA_EFECTIVA_ANUAL_KEY. */
	protected static final String TASA_EFECTIVA_ANUAL_KEY = "TASA_EFECTIVA_ANUAL";
	
	/** The Constant COSTO_FINANCIERO_TOTAL_KEY. */
	protected static final String COSTO_FINANCIERO_TOTAL_KEY = "COSTO_FINANCIERO_TOTAL";
	
	/** The Constant MENSAJE_INFORMATIVO_KEY. */
	protected static final String MENSAJE_INFORMATIVO_KEY = "MENSAJE_INFORMATIVO";
	
	/** The Constant LEGAL_PIE_1_KEY. */
	protected static final String LEGAL_PIE_1_KEY = "LEGAL_PIE_1";
	
	/** The Constant LEGAL_PIE_2_KEY. */
	protected static final String LEGAL_PIE_2_KEY = "LEGAL_PIE_2";
	
	/** The Constant LEGAL_PIE_3_KEY. */
	protected static final String LEGAL_PIE_3_KEY = "LEGAL_PIE_3";
	
	
	/** The pmc servicio. */
	private static final String DESCARGAR_OPERACION_JASPER = "operacion-descuento-cheques.jasper";

	
	/**
	 * Instantiates a new detalle operaciones precargadas view.
	 *
	 * @param respuesta
	 *            the respuesta
	 */
	public DetalleOperacionesPrecargadasView(DetalleOperacionesPrecargadasDTO respuesta) {
		importeAcreditar = ISBANStringUtils.formatearSaldo(respuesta.getImporteAcreditar());
		cuenta = respuesta.getCuenta().toString();
		numeroOperacion = respuesta.getNumeroOperacion();
		totalChequesDescontados = String.valueOf(respuesta.getTotalChequesDescontados());
		total = ISBANStringUtils.formatearSaldo(respuesta.getTotal());
		impuesto = ISBANStringUtils.formatearSaldo(respuesta.getImpuesto());
		intereses = ISBANStringUtils.formatearSaldo(respuesta.getIntereses());
		aAcreditar = ISBANStringUtils.formatearSaldo(respuesta.getaAcreditar());
		importe = ISBANStringUtils.formatearSaldo(respuesta.getImporte());
		totalChequesRechazados = String.valueOf(respuesta.getTotalChequesRechazados());
		comAdminCheques = ISBANStringUtils.formatearSaldo(respuesta.getComAdminCheques());
		tasaNominalAnual = respuesta.getTasaNominalAnual();
		tasaEfectivaAnual = respuesta.getTasaEfectivaAnual();
		costoFinancieroTotal = respuesta.getCostoFinancieroTotal();
		for(ChequesAceptadosDTO chequeAceptados : respuesta.getChequesAceptados()){
			chequesAceptados.add(new ChequesAceptadosView(chequeAceptados));
		}
		for(ChequesRechazadosDTO chequeRechazados : respuesta.getChequesRechazados()){
			chequesRechazados.add(new ChequesRechazadosView(chequeRechazados));
		}
		mensajeInformativo = respuesta.getMensajeInformativo();
		legalPie1 = respuesta.getLegalPie1();
		legalPie2 = respuesta.getLegalPie2();
		legalPie3 = respuesta.getLegalPie3();
	}

	/**
	 * Instantiates a new detalle operaciones precargadas view.
	 */
	public DetalleOperacionesPrecargadasView() {
		super();
	}

	/**
	 * Gets the titulo.
	 *
	 * @return the titulo
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * Sets the titulo.
	 *
	 * @param titulo
	 *            the new titulo
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	/**
	 * Gets the importe acreditar.
	 *
	 * @return the importe acreditar
	 */
	public String getImporteAcreditar() {
		return importeAcreditar;
	}

	/**
	 * Sets the importe acreditar.
	 *
	 * @param importeAcreditar
	 *            the new importe acreditar
	 */
	public void setImporteAcreditar(String importeAcreditar) {
		this.importeAcreditar = importeAcreditar;
	}

	/**
	 * Gets the cuenta.
	 *
	 * @return the cuenta
	 */
	public String getCuenta() {
		return cuenta;
	}

	/**
	 * Sets the cuenta.
	 *
	 * @param cuenta
	 *            the new cuenta
	 */
	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	/**
	 * Gets the numero operacion.
	 *
	 * @return the numero operacion
	 */
	public String getNumeroOperacion() {
		return numeroOperacion;
	}

	/**
	 * Sets the numero operacion.
	 *
	 * @param numeroOperacion
	 *            the new numero operacion
	 */
	public void setNumeroOperacion(String numeroOperacion) {
		this.numeroOperacion = numeroOperacion;
	}

	/**
	 * Gets the total cheques descontados.
	 *
	 * @return the total cheques descontados
	 */
	public String getTotalChequesDescontados() {
		return totalChequesDescontados;
	}

	/**
	 * Sets the total cheques descontados.
	 *
	 * @param totalChequesDescontados
	 *            the new total cheques descontados
	 */
	public void setTotalChequesDescontados(String totalChequesDescontados) {
		this.totalChequesDescontados = totalChequesDescontados;
	}

	/**
	 * Gets the total.
	 *
	 * @return the total
	 */
	public String getTotal() {
		return total;
	}

	/**
	 * Sets the total.
	 *
	 * @param total
	 *            the new total
	 */
	public void setTotal(String total) {
		this.total = total;
	}

	/**
	 * Gets the impuesto.
	 *
	 * @return the impuesto
	 */
	public String getImpuesto() {
		return impuesto;
	}

	/**
	 * Sets the impuesto.
	 *
	 * @param impuesto
	 *            the new impuesto
	 */
	public void setImpuesto(String impuesto) {
		this.impuesto = impuesto;
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
	 *            the new intereses
	 */
	public void setIntereses(String intereses) {
		this.intereses = intereses;
	}

	/**
	 * Gets the a acreditar.
	 *
	 * @return the a acreditar
	 */
	public String getaAcreditar() {
		return aAcreditar;
	}

	/**
	 * Sets the a acreditar.
	 *
	 * @param aAcreditar
	 *            the new a acreditar
	 */
	public void setaAcreditar(String aAcreditar) {
		this.aAcreditar = aAcreditar;
	}

	/**
	 * Gets the cheques aceptados.
	 *
	 * @return the cheques aceptados
	 */
	public List<ChequesAceptadosView> getChequesAceptados() {
		return chequesAceptados;
	}

	/**
	 * Sets the cheques aceptados.
	 *
	 * @param chequesAceptados
	 *            the new cheques aceptados
	 */
	public void setChequesAceptados(List<ChequesAceptadosView> chequesAceptados) {
		this.chequesAceptados = chequesAceptados;
	}

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
	 *            the new importe
	 */
	public void setImporte(String importe) {
		this.importe = importe;
	}

	/**
	 * Gets the total cheques rechazados.
	 *
	 * @return the total cheques rechazados
	 */
	public String getTotalChequesRechazados() {
		return totalChequesRechazados;
	}

	/**
	 * Sets the total cheques rechazados.
	 *
	 * @param totalChequesRechazados
	 *            the new total cheques rechazados
	 */
	public void setTotalChequesRechazados(String totalChequesRechazados) {
		this.totalChequesRechazados = totalChequesRechazados;
	}

	/**
	 * Gets the cheques rechazados.
	 *
	 * @return the cheques rechazados
	 */
	public List<ChequesRechazadosView> getChequesRechazados() {
		return chequesRechazados;
	}

	/**
	 * Sets the cheques rechazados.
	 *
	 * @param chequesRechazados
	 *            the new cheques rechazados
	 */
	public void setChequesRechazados(List<ChequesRechazadosView> chequesRechazados) {
		this.chequesRechazados = chequesRechazados;
	}

	/**
	 * Gets the com admin cheques.
	 *
	 * @return the com admin cheques
	 */
	public String getComAdminCheques() {
		return comAdminCheques;
	}

	/**
	 * Sets the com admin cheques.
	 *
	 * @param comAdminCheques
	 *            the new com admin cheques
	 */
	public void setComAdminCheques(String comAdminCheques) {
		this.comAdminCheques = comAdminCheques;
	}

	/**
	 * Gets the tasa nominal anual.
	 *
	 * @return the tasa nominal anual
	 */
	public String getTasaNominalAnual() {
		return tasaNominalAnual;
	}

	/**
	 * Sets the tasa nominal anual.
	 *
	 * @param tasaNominalAnual
	 *            the new tasa nominal anual
	 */
	public void setTasaNominalAnual(String tasaNominalAnual) {
		this.tasaNominalAnual = tasaNominalAnual;
	}

	/**
	 * Gets the tasa efectiva anual.
	 *
	 * @return the tasa efectiva anual
	 */
	public String getTasaEfectivaAnual() {
		return tasaEfectivaAnual;
	}

	/**
	 * Sets the tasa efectiva anual.
	 *
	 * @param tasaEfectivaAnual
	 *            the new tasa efectiva anual
	 */
	public void setTasaEfectivaAnual(String tasaEfectivaAnual) {
		this.tasaEfectivaAnual = tasaEfectivaAnual;
	}

	/**
	 * Gets the costo financiero total.
	 *
	 * @return the costo financiero total
	 */
	public String getCostoFinancieroTotal() {
		return costoFinancieroTotal;
	}

	/**
	 * Sets the costo financiero total.
	 *
	 * @param costoFinancieroTotal
	 *            the new costo financiero total
	 */
	public void setCostoFinancieroTotal(String costoFinancieroTotal) {
		this.costoFinancieroTotal = costoFinancieroTotal;
	}

	/**
	 * Gets the numero tramite.
	 *
	 * @return the numero tramite
	 */
	public String getNumeroTramite() {
		return numeroTramite;
	}

	/**
	 * Sets the numero tramite.
	 *
	 * @param numeroTramite
	 *            the new numero tramite
	 */
	public void setNumeroTramite(String numeroTramite) {
		this.numeroTramite = numeroTramite;
	}
	
	/**
	 * Gets the mensaje informativo.
	 *
	 * @return the mensaje informativo
	 */
	public String getMensajeInformativo() {
		return mensajeInformativo;
	}

	/**
	 * Sets the mensaje informativo.
	 *
	 * @param mensajeInformativo
	 *            the new mensaje informativo
	 */
	public void setMensajeInformativo(String mensajeInformativo) {
		this.mensajeInformativo = mensajeInformativo;
	}

	/**
	 * Gets the legal pie 1.
	 *
	 * @return the legal pie 1
	 */
	public String getLegalPie1() {
		return legalPie1;
	}

	/**
	 * Sets the legal pie 1.
	 *
	 * @param legalPie1
	 *            the new legal pie 1
	 */
	public void setLegalPie1(String legalPie1) {
		this.legalPie1 = legalPie1;
	}

	/**
	 * Gets the legal pie 2.
	 *
	 * @return the legal pie 2
	 */
	public String getLegalPie2() {
		return legalPie2;
	}

	/**
	 * Sets the legal pie 2.
	 *
	 * @param legalPie2
	 *            the new legal pie 2
	 */
	public void setLegalPie2(String legalPie2) {
		this.legalPie2 = legalPie2;
	}

	/**
	 * Gets the legal pie 3.
	 *
	 * @return the legal pie 3
	 */
	public String getLegalPie3() {
		return legalPie3;
	}

	/**
	 * Sets the legal pie 3.
	 *
	 * @param legalPie3
	 *            the new legal pie 3
	 */
	public void setLegalPie3(String legalPie3) {
		this.legalPie3 = legalPie3;
	}
	
	/**
	 * Obtener jasper.
	 *
	 * @return the string
	 * @throws FileNotFoundException
	 *             the file not found exception
	 */
	public String obtenerJasper() throws FileNotFoundException {
		return ResourceUtils.getFile(PATH + DESCARGAR_OPERACION_JASPER).getPath();
	}

	/**
	 * Obtener parametros PDF.
	 *
	 * @return the map
	 */
	public Map<String, Object> obtenerParametrosPDF() {
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put(TITULO_KEY, "Comprobante de solicitud de descuento de cheques");
		parametros.put(NUMERO_TRAMITE_KEY, numeroTramite);
		parametros.put(IMPORTE_ACREDITAR_KEY, "$"+importeAcreditar);
		parametros.put(CUENTA_KEY,"Cuenta " + cuenta);
		parametros.put(NUMERO_OPERACION_KEY, numeroOperacion);
		//Pedido de maqueta
		if(0 == Integer.valueOf(totalChequesDescontados)){
			parametros.put(TOTAL_CHEQUES_DESCONTADOS_KEY, null);
		}else{
			parametros.put(TOTAL_CHEQUES_DESCONTADOS_KEY, totalChequesDescontados);	
		}
		parametros.put(IMPUESTO_KEY,"Impuesto: $" + impuesto);
		parametros.put(INTERESES_KEY,"Intereses: $" + intereses);
		parametros.put(A_ACREDITAR_KEY, "A acreditar: $" +aAcreditar);
		parametros.put(CHEQUES_ACEPTADOS_KEY, agregarLabelsAceptados(chequesAceptados));
		if(0 == Double.valueOf(importe.replace(".", "").replace(",", "."))){
			parametros.put(IMPORTE_KEY, "");
		} else {
			parametros.put(IMPORTE_KEY,"$" + importe);
		}
		if(0 == Double.valueOf(total.replace(".", "").replace(",", "."))){
			parametros.put(TOTAL_KEY, "");
		} else {
			parametros.put(TOTAL_KEY,"Total: $" + total);
		}
		
		if(0 == Integer.valueOf(totalChequesRechazados)){
			parametros.put(TOTAL_CHEQUES_RECHAZADOS_KEY, null);
		}else{
			parametros.put(TOTAL_CHEQUES_RECHAZADOS_KEY, totalChequesRechazados);	
		}
		
		parametros.put(CHEQUES_RECHAZADOS_KEY, chequesRechazados);
		parametros.put(COM_ADMIN_CHEQUES_KEY, "$"+comAdminCheques);
		parametros.put(TASA_NOMINAL_ANUAL_KEY, tasaNominalAnual);
		parametros.put(TASA_EFECTIVA_ANUAL_KEY, tasaEfectivaAnual);
		parametros.put(COSTO_FINANCIERO_TOTAL_KEY, costoFinancieroTotal);
		parametros.put(MENSAJE_INFORMATIVO_KEY, mensajeInformativo);
		parametros.put(LEGAL_PIE_1_KEY, "(1) " + legalPie1);
		parametros.put(LEGAL_PIE_2_KEY, "(2) " + legalPie2);
		parametros.put(LEGAL_PIE_3_KEY, "(3) " + legalPie3);
		return parametros;
	}
	
	/**
	 * Agregar labels aceptados.
	 *
	 * @param chequesAceptados
	 *            the cheques aceptados
	 * @return the list
	 */
	private List<ChequesAceptadosView> agregarLabelsAceptados(List<ChequesAceptadosView> chequesAceptados) {
		for(ChequesAceptadosView cheque : chequesAceptados) {
			cheque.setImporteTotal("Total: $" + cheque.getImporteTotal());
			cheque.setImporteImpuestos("Impuestos: $" + cheque.getImporteImpuestos());
			cheque.setImporteAAcreditar("A acreditar: $" + cheque.getImporteAAcreditar());
			cheque.setImporteIntereses("Intereses: $" + cheque.getImporteIntereses());
		}
		return chequesAceptados;
	}

	/**
	 * Gets the es simulacion.
	 *
	 * @return the es simulacion
	 */
	public Boolean getEsSimulacion() {
		return esSimulacion;
	}

	/**
	 * Sets the es simulacion.
	 *
	 * @param esSimulacion
	 *            the new es simulacion
	 */
	public void setEsSimulacion(Boolean esSimulacion) {
		this.esSimulacion = esSimulacion;
	}


	
}
