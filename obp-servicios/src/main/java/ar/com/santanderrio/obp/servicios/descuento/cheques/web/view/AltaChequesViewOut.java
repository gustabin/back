/*
 * 
 */
package ar.com.santanderrio.obp.servicios.descuento.cheques.web.view;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.ResourceUtils;

import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.descuento.cheques.entities.ChequeSimuladoDTO;
import ar.com.santanderrio.obp.servicios.descuento.cheques.entities.ChequesAceptadosDTO;
import ar.com.santanderrio.obp.servicios.descuento.cheques.entities.ChequesAltaDTO;
import ar.com.santanderrio.obp.servicios.descuento.cheques.entities.ChequesRechazadosDTO;
import ar.com.santanderrio.obp.servicios.descuento.cheques.entities.ChequesSimuladosDTO;
import ar.com.santanderrio.obp.servicios.descuento.cheques.entities.DetalleOperacionesPrecargadasDTO;

/**
 * The Class AltaChequesViewOut.
 */
public class AltaChequesViewOut {

	/** The importe acreditar. */
	private String importeAcreditar;

	/** The importe total. */
	private String importeTotal;

	/** The cuenta debito. */
	private String cuentaDebito;

	/** The cuenta credito. */
	private String cuentaCredito;

	/** The numero de operacion. */
	private String numeroDeOperacion;

	/** The cheques descontados. */
	private String chequesDescontados;

	/** The importe total cheque. */
	private String importeTotalCheque;

	/** The importe cheque. */
	private String importeCheque;

	/** The importe impuestos. */
	private String importeImpuestos;

	/** The importe intereses. */
	private String importeIntereses;

	/** The importe neto. */
	private String importeNeto;

	/** The importe acreditar cheque. */
	private String importeAcreditarCheque;

	/** The lista aceptados. */
	private List<AltaChequeAceptadoViewOut> listaAceptados = new ArrayList<AltaChequeAceptadoViewOut>();

	/** The lista rechazados. */
	private List<AltaChequeRechazadoViewOut> listaRechazados = new ArrayList<AltaChequeRechazadoViewOut>();

	/** The comision adic. */
	private String comisionAdic;

	/** The tasa aplicada. */
	private String tasaAplicada;

	/** The tasa efectiva anual. */
	private String tasaEfectivaAnual;

	/** The costo financiero total. */
	private String costoFinancieroTotal;

	/** The fecha alta. */
	private String fechaAlta;

	/** The legal 1. */
	private String legal1;

	/** The legal 2. */
	private String legal2;

	/** The legal 3. */
	private String legal3;

	/** The legal 4. */
	private String legal4;
	
	private String legal5;

	/** The mensaje feedback. */
	private String mensajeFeedback;

	/** The fecha hora. */
	private String fechaHora;

	/** The es simulacion. */
	private Boolean esSimulacion = Boolean.FALSE;

	/** The path. */
	protected static final String PATH = "classpath:/report/descuentoCheques/";

	/** The titulo. */
	protected static final String TITULO_KEY = "TITULO_COMPROBANTE";

	/** The Constant NUMERO_TRAMITE_KEY. */
	protected static final String NUMERO_TRAMITE_KEY = "NUMERO_TRAMITE";

	/** The Constant IMPORTE_ACREDITAR_KEY. */
	protected static final String IMPORTE_ACREDITAR_KEY = "IMPORTE_ACREDITAR";

	/** The Constant IMPORTE_TOTAL. */
	protected static final String IMPORTE_TOTAL = "IMPORTE_TOTAL";

	/** The Constant CUENTA_KEY. */
	protected static final String CUENTA_KEY = "CUENTA";

	/** The Constant NUMERO_OPERACION_KEY. */
	protected static final String NUMERO_OPERACION_KEY = "NUMERO_OPERACION";

	/** The Constant TOTAL_CHEQUES_DESCONTADOS_KEY. */
	protected static final String TOTAL_CHEQUES_DESCONTADOS_KEY = "TOTAL_CHEQUES_DESCONTADOS";

	/** The Constant TOTAL_KEY. */
	protected static final String TOTAL_KEY = "TOTAL";

	/** The Constant FECHA_KEY. */
	protected static final String FECHA_KEY = "FECHA";

	/** The Constant TOTAL_CHEQUES_KEY. */
	protected static final String TOTAL_CHEQUES_KEY = "TOTAL_CHEQUES";

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

	/** The Constant LEGAL_PIE_4_KEY. */
	protected static final String LEGAL_PIE_4_KEY = "LEGAL_PIE_4";

	/** The Constant FECHA_ACTUAL_KEY. */
	protected static final String FECHA_ACTUAL_KEY = "FECHA_ACTUAL";

	/** The pmc servicio. */
	private static final String DESCARGAR_OPERACION_JASPER = "operacion-alta-cheques.jasper";

	/**
	 * Instantiates a new alta cheques view out.
	 */
	public AltaChequesViewOut() {
		super();
	}

	/**
	 * Instantiates a new alta cheques view out.
	 *
	 * @param respuesta
	 *            the respuesta
	 */
	public AltaChequesViewOut(ChequesSimuladosDTO respuesta) {
		importeAcreditar = ISBANStringUtils.formatearSaldo(respuesta.getImporteAcreditar());
		if(respuesta.getMensajeFeedback()!=null) {
			importeTotal = ISBANStringUtils.formatearSaldo(respuesta.getImporteTotal());
			cuentaCredito = respuesta.getCuentaDebito().toString();
			numeroDeOperacion = respuesta.getNumeroDeOperacion().toString();
			chequesDescontados = String.valueOf(respuesta.getChequesDescontados());
			importeTotalCheque = ISBANStringUtils.formatearSaldo(respuesta.getImporteCheque());
			importeImpuestos = ISBANStringUtils.formatearSaldo(respuesta.getImporteImpuestos());
			importeIntereses = ISBANStringUtils.formatearSaldo(respuesta.getImporteIntereses());
			importeAcreditarCheque = ISBANStringUtils.formatearSaldo(respuesta.getImporteNeto());
			for (ChequeSimuladoDTO chequesAceptados : respuesta.getListaAceptados()) {
				listaAceptados.add(new AltaChequeAceptadoViewOut(chequesAceptados, true));
			}
			for (ChequeSimuladoDTO chequesRechazados : respuesta.getListaRechazados()) {
				listaRechazados.add(new AltaChequeRechazadoViewOut(chequesRechazados));
			}
			comisionAdic = ISBANStringUtils.formatearSaldo(respuesta.getComisionAdic());
			tasaAplicada = respuesta.getTasaAplicada();
			tasaEfectivaAnual = respuesta.getTasaEfectivaAnual();
			esSimulacion = true;
			costoFinancieroTotal = respuesta.getCostoFinancieroTotal();
			mensajeFeedback = respuesta.getMensajeFeedback();
			if (respuesta.getFechaAlta() != null) {
				fechaAlta = ISBANStringUtils.formatearFecha(respuesta.getFechaAlta());
			}
			legal1 = respuesta.getLegal1();
			legal2 = respuesta.getLegal2();
			legal3 = respuesta.getLegal3();
			legal4 = respuesta.getLegal4();
			legal5 = respuesta.getLegal5();
			fechaHora = ISBANStringUtils.formatearFechaConHoraParaComprobante(new Date());	
		}else {
			importeTotal = ISBANStringUtils.formatearSaldo(respuesta.getImporteTotal());
			cuentaDebito = respuesta.getCuentaDebito().toString();
			numeroDeOperacion = respuesta.getNumeroDeOperacion().toString();
			chequesDescontados = String.valueOf(respuesta.getChequesDescontados());
			importeCheque = ISBANStringUtils.formatearSaldo(respuesta.getImporteCheque());
			importeImpuestos = ISBANStringUtils.formatearSaldo(respuesta.getImporteImpuestos());
			importeIntereses = ISBANStringUtils.formatearSaldo(respuesta.getImporteIntereses());
			importeNeto = ISBANStringUtils.formatearSaldo(respuesta.getImporteNeto());
			for(ChequeSimuladoDTO chequesAceptados: respuesta.getListaAceptados()) {
				listaAceptados.add(new AltaChequeAceptadoViewOut(chequesAceptados,false));
			}
			for(ChequeSimuladoDTO chequesRechazados: respuesta.getListaRechazados()) {
				listaRechazados.add(new AltaChequeRechazadoViewOut(chequesRechazados));
			}
			comisionAdic = ISBANStringUtils.formatearSaldo(respuesta.getComisionAdic());
			tasaAplicada = respuesta.getTasaAplicada();
			tasaEfectivaAnual = respuesta.getTasaEfectivaAnual();
			costoFinancieroTotal = respuesta.getCostoFinancieroTotal();
			legal1 = respuesta.getLegal1();
			legal2 = respuesta.getLegal2();
			legal3 = respuesta.getLegal3();
			legal5 = respuesta.getLegal5();
		}
	}

	/**
	 * Instantiates a new alta cheques view out.
	 *
	 * @param respuesta
	 *            the respuesta
	 */
	public AltaChequesViewOut(ChequesAltaDTO respuesta) {
		importeAcreditar = ISBANStringUtils.formatearSaldo(respuesta.getImporteAcreditar());
		importeTotal = ISBANStringUtils.formatearSaldo(respuesta.getImporteTotal());
		cuentaCredito = respuesta.getCuentaCredito().toString();
		chequesDescontados = String.valueOf(respuesta.getChequesDescontados());
		importeTotalCheque = ISBANStringUtils.formatearSaldo(respuesta.getImporteTotalCheque());
		importeImpuestos = ISBANStringUtils.formatearSaldo(respuesta.getImporteImpuestos());
		importeIntereses = ISBANStringUtils.formatearSaldo(respuesta.getImporteIntereses());
		importeAcreditarCheque = ISBANStringUtils.formatearSaldo(respuesta.getImporteAAcreditar());
		for (ChequeSimuladoDTO chequesAceptados : respuesta.getListaAceptados()) {
			listaAceptados.add(new AltaChequeAceptadoViewOut(chequesAceptados, false));
		}
		comisionAdic = ISBANStringUtils.formatearSaldo(respuesta.getComisionAdic());
		tasaAplicada = respuesta.getTasaAplicada();
		tasaEfectivaAnual = respuesta.getTasaEfectivaAnual();
		costoFinancieroTotal = respuesta.getCostoFinancieroTotal();
		fechaAlta = ISBANStringUtils.formatearFecha(respuesta.getFechaAlta());
		numeroDeOperacion = respuesta.getNumeroDeOperacion().toString();
		legal1 = respuesta.getLegal1();
		legal2 = respuesta.getLegal2();
		legal3 = respuesta.getLegal3();
		legal4 = respuesta.getLegal4();
		mensajeFeedback = respuesta.getMensajeFeedback();
		fechaHora = ISBANStringUtils.formatearFechaConHoraParaComprobante(new Date());
	}

	/**
	 * Instantiates a new alta cheques view out.
	 *
	 * @param respuesta
	 *            the respuesta
	 */
	public AltaChequesViewOut(DetalleOperacionesPrecargadasDTO respuesta) {
		importeNeto = ISBANStringUtils.formatearSaldo(respuesta.getImporteAcreditar());
		importeCheque = ISBANStringUtils.formatearSaldo(respuesta.getTotal());
		cuentaDebito = respuesta.getCuenta().toString();
		numeroDeOperacion = respuesta.getNumeroOperacion();
		chequesDescontados = String.valueOf(respuesta.getTotalChequesDescontados());
		importeTotalCheque = ISBANStringUtils.formatearSaldo(respuesta.getTotal());
		importeImpuestos = ISBANStringUtils.formatearSaldo(respuesta.getImpuesto());
		importeIntereses = ISBANStringUtils.formatearSaldo(respuesta.getIntereses());
		importeAcreditarCheque = ISBANStringUtils.formatearSaldo(respuesta.getaAcreditar());
		for (ChequesAceptadosDTO altaChequeAceptadoViewOut : respuesta.getChequesAceptados()) {
			listaAceptados.add(new AltaChequeAceptadoViewOut(altaChequeAceptadoViewOut));
		}
		for (ChequesRechazadosDTO chequesRechazados : respuesta.getChequesRechazados()) {
			listaRechazados.add(new AltaChequeRechazadoViewOut(chequesRechazados));
		}
		comisionAdic = ISBANStringUtils.formatearSaldo(respuesta.getComAdminCheques());
		tasaAplicada = respuesta.getTasaNominalAnual();
		tasaEfectivaAnual = respuesta.getTasaEfectivaAnual();
		costoFinancieroTotal = respuesta.getCostoFinancieroTotal();
		legal1 = respuesta.getLegalPie1();
		legal2 = respuesta.getLegalPie2();
		legal3 = respuesta.getLegalPie3();

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
	 * Gets the importe total.
	 *
	 * @return the importe total
	 */
	public String getImporteTotal() {
		return importeTotal;
	}

	/**
	 * Sets the importe total.
	 *
	 * @param importeTotal
	 *            the new importe total
	 */
	public void setImporteTotal(String importeTotal) {
		this.importeTotal = importeTotal;
	}

	/**
	 * Gets the cuenta debito.
	 *
	 * @return the cuenta debito
	 */
	public String getCuentaDebito() {
		return cuentaDebito;
	}

	/**
	 * Sets the cuenta debito.
	 *
	 * @param cuentaDebito
	 *            the new cuenta debito
	 */
	public void setCuentaDebito(String cuentaDebito) {
		this.cuentaDebito = cuentaDebito;
	}

	/**
	 * Gets the numero de operacion.
	 *
	 * @return the numero de operacion
	 */
	public String getNumeroDeOperacion() {
		return numeroDeOperacion;
	}

	/**
	 * Sets the numero de operacion.
	 *
	 * @param numeroDeOperacion
	 *            the new numero de operacion
	 */
	public void setNumeroDeOperacion(String numeroDeOperacion) {
		this.numeroDeOperacion = numeroDeOperacion;
	}

	/**
	 * Gets the cheques descontados.
	 *
	 * @return the cheques descontados
	 */
	public String getChequesDescontados() {
		return chequesDescontados;
	}

	/**
	 * Sets the cheques descontados.
	 *
	 * @param chequesDescontados
	 *            the new cheques descontados
	 */
	public void setChequesDescontados(String chequesDescontados) {
		this.chequesDescontados = chequesDescontados;
	}

	/**
	 * Gets the importe cheque.
	 *
	 * @return the importe cheque
	 */
	public String getImporteCheque() {
		return importeCheque;
	}

	/**
	 * Sets the importe cheque.
	 *
	 * @param importeCheque
	 *            the new importe cheque
	 */
	public void setImporteCheque(String importeCheque) {
		this.importeCheque = importeCheque;
	}

	/**
	 * Gets the importe impuestos.
	 *
	 * @return the importe impuestos
	 */
	public String getImporteImpuestos() {
		return importeImpuestos;
	}

	/**
	 * Sets the importe impuestos.
	 *
	 * @param importeImpuestos
	 *            the new importe impuestos
	 */
	public void setImporteImpuestos(String importeImpuestos) {
		this.importeImpuestos = importeImpuestos;
	}

	/**
	 * Gets the importe intereses.
	 *
	 * @return the importe intereses
	 */
	public String getImporteIntereses() {
		return importeIntereses;
	}

	/**
	 * Sets the importe intereses.
	 *
	 * @param importeIntereses
	 *            the new importe intereses
	 */
	public void setImporteIntereses(String importeIntereses) {
		this.importeIntereses = importeIntereses;
	}

	/**
	 * Gets the importe neto.
	 *
	 * @return the importe neto
	 */
	public String getImporteNeto() {
		return importeNeto;
	}

	/**
	 * Sets the importe neto.
	 *
	 * @param importeNeto
	 *            the new importe neto
	 */
	public void setImporteNeto(String importeNeto) {
		this.importeNeto = importeNeto;
	}

	/**
	 * Gets the lista aceptados.
	 *
	 * @return the lista aceptados
	 */
	public List<AltaChequeAceptadoViewOut> getListaAceptados() {
		return listaAceptados;
	}

	/**
	 * Sets the lista aceptados.
	 *
	 * @param listaAceptados
	 *            the new lista aceptados
	 */
	public void setListaAceptados(List<AltaChequeAceptadoViewOut> listaAceptados) {
		this.listaAceptados = listaAceptados;
	}

	/**
	 * Gets the lista rechazados.
	 *
	 * @return the lista rechazados
	 */
	public List<AltaChequeRechazadoViewOut> getListaRechazados() {
		return listaRechazados;
	}

	/**
	 * Sets the lista rechazados.
	 *
	 * @param listaRechazados
	 *            the new lista rechazados
	 */
	public void setListaRechazados(List<AltaChequeRechazadoViewOut> listaRechazados) {
		this.listaRechazados = listaRechazados;
	}

	/**
	 * Gets the comision adic.
	 *
	 * @return the comision adic
	 */
	public String getComisionAdic() {
		return comisionAdic;
	}

	/**
	 * Sets the comision adic.
	 *
	 * @param comisionAdic
	 *            the new comision adic
	 */
	public void setComisionAdic(String comisionAdic) {
		this.comisionAdic = comisionAdic;
	}

	/**
	 * Gets the tasa aplicada.
	 *
	 * @return the tasa aplicada
	 */
	public String getTasaAplicada() {
		return tasaAplicada;
	}

	/**
	 * Sets the tasa aplicada.
	 *
	 * @param tasaAplicada
	 *            the new tasa aplicada
	 */
	public void setTasaAplicada(String tasaAplicada) {
		this.tasaAplicada = tasaAplicada;
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
	 * Gets the legal 1.
	 *
	 * @return the legal 1
	 */
	public String getLegal1() {
		return legal1;
	}

	/**
	 * Sets the legal 1.
	 *
	 * @param legal1
	 *            the new legal 1
	 */
	public void setLegal1(String legal1) {
		this.legal1 = legal1;
	}

	/**
	 * Gets the legal 2.
	 *
	 * @return the legal 2
	 */
	public String getLegal2() {
		return legal2;
	}

	/**
	 * Sets the legal 2.
	 *
	 * @param legal2
	 *            the new legal 2
	 */
	public void setLegal2(String legal2) {
		this.legal2 = legal2;
	}

	/**
	 * Gets the legal 3.
	 *
	 * @return the legal 3
	 */
	public String getLegal3() {
		return legal3;
	}

	/**
	 * Sets the legal 3.
	 *
	 * @param legal3
	 *            the new legal 3
	 */
	public void setLegal3(String legal3) {
		this.legal3 = legal3;
	}

	/**
	 * Gets the mensaje feedback.
	 *
	 * @return the mensaje feedback
	 */
	public String getMensajeFeedback() {
		return mensajeFeedback;
	}

	/**
	 * Sets the mensaje feedback.
	 *
	 * @param mensajeFeedback
	 *            the new mensaje feedback
	 */
	public void setMensajeFeedback(String mensajeFeedback) {
		this.mensajeFeedback = mensajeFeedback;
	}

	/**
	 * Gets the legal 4.
	 *
	 * @return the legal 4
	 */
	public String getLegal4() {
		return legal4;
	}

	/**
	 * Sets the legal 4.
	 *
	 * @param legal4
	 *            the new legal 4
	 */
	public void setLegal4(String legal4) {
		this.legal4 = legal4;
	}
	
	public String getLegal5() {
		return legal5;
	}

	public void setLegal5(String legal5) {
		this.legal5 = legal5;
	}

	/**
	 * Gets the cuenta credito.
	 *
	 * @return the cuenta credito
	 */
	public String getCuentaCredito() {
		return cuentaCredito;
	}

	/**
	 * Sets the cuenta credito.
	 *
	 * @param cuentaCredito
	 *            the new cuenta credito
	 */
	public void setCuentaCredito(String cuentaCredito) {
		this.cuentaCredito = cuentaCredito;
	}

	/**
	 * Gets the importe total cheque.
	 *
	 * @return the importe total cheque
	 */
	public String getImporteTotalCheque() {
		return importeTotalCheque;
	}

	/**
	 * Sets the importe total cheque.
	 *
	 * @param importeTotalCheque
	 *            the new importe total cheque
	 */
	public void setImporteTotalCheque(String importeTotalCheque) {
		this.importeTotalCheque = importeTotalCheque;
	}

	/**
	 * Gets the importe acreditar cheque.
	 *
	 * @return the importe acreditar cheque
	 */
	public String getImporteAcreditarCheque() {
		return importeAcreditarCheque;
	}

	/**
	 * Sets the importe acreditar cheque.
	 *
	 * @param importeAcreditarCheque
	 *            the new importe acreditar cheque
	 */
	public void setImporteAcreditarCheque(String importeAcreditarCheque) {
		this.importeAcreditarCheque = importeAcreditarCheque;
	}

	/**
	 * Gets the fecha alta.
	 *
	 * @return the fecha alta
	 */
	public String getFechaAlta() {
		return fechaAlta;
	}

	/**
	 * Sets the fecha alta.
	 *
	 * @param fechaAlta
	 *            the new fecha alta
	 */
	public void setFechaAlta(String fechaAlta) {
		this.fechaAlta = fechaAlta;
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
		parametros.put(IMPORTE_ACREDITAR_KEY, "$" + importeAcreditar);
		parametros.put(IMPORTE_TOTAL, "Importe total $" + importeTotal);
		parametros.put(CUENTA_KEY, "Cuenta " + cuentaCredito);
		// Pedido de maqueta
		if (0 == Integer.valueOf(chequesDescontados)) {
			parametros.put(TOTAL_CHEQUES_DESCONTADOS_KEY, null);
		} else {
			parametros.put(TOTAL_CHEQUES_DESCONTADOS_KEY, chequesDescontados);
		}
		parametros.put(TOTAL_CHEQUES_KEY, "Total $" + importeTotalCheque);
		parametros.put(IMPUESTO_KEY, "Impuestos $" + importeImpuestos);
		parametros.put(INTERESES_KEY, "Intereses $" + importeIntereses);
		parametros.put(A_ACREDITAR_KEY, "A acreditar $" + importeAcreditarCheque);
		parametros.put(CHEQUES_ACEPTADOS_KEY, agregarLabelsAceptados(listaAceptados));

		parametros.put(NUMERO_OPERACION_KEY, numeroDeOperacion);
		if (0 == listaRechazados.size()) {
			parametros.put(TOTAL_CHEQUES_RECHAZADOS_KEY, null);
		} else {
			parametros.put(TOTAL_CHEQUES_RECHAZADOS_KEY, listaRechazados.size());
		}

		parametros.put(CHEQUES_RECHAZADOS_KEY, listaRechazados);
		parametros.put(COM_ADMIN_CHEQUES_KEY, "$" + comisionAdic);
		parametros.put(TASA_NOMINAL_ANUAL_KEY, tasaAplicada);
		parametros.put(TASA_EFECTIVA_ANUAL_KEY, tasaEfectivaAnual);
		parametros.put(COSTO_FINANCIERO_TOTAL_KEY, costoFinancieroTotal);
		parametros.put(FECHA_KEY, fechaAlta);
		parametros.put(LEGAL_PIE_1_KEY, legal1);
		parametros.put(LEGAL_PIE_2_KEY, "*" + legal2);
		parametros.put(LEGAL_PIE_3_KEY, "**" + legal3);
		parametros.put(LEGAL_PIE_4_KEY, "(1)" + legal4);
		parametros.put(FECHA_ACTUAL_KEY, fechaHora);
		return parametros;
	}

	/**
	 * Agregar labels aceptados.
	 *
	 * @param listaAceptados
	 *            the lista aceptados
	 * @return the list
	 */
	private List<AltaChequeAceptadoViewOut> agregarLabelsAceptados(List<AltaChequeAceptadoViewOut> listaAceptados) {
		for (AltaChequeAceptadoViewOut cheque : listaAceptados) {
			cheque.setImporteTotal("Total $" + cheque.getImporteTotal());
			cheque.setImporteImpuestos("Impuestos $" + cheque.getImporteImpuestos());
			cheque.setImporteAAcreditar("A acreditar $" + cheque.getImporteAAcreditar());
			cheque.setImporteIntereses("Intereses $" + cheque.getImporteIntereses());
		}
		return listaAceptados;
	}

	/**
	 * Gets the fecha hora.
	 *
	 * @return the fecha hora
	 */
	public String getFechaHora() {
		return fechaHora;
	}

	/**
	 * Sets the fecha hora.
	 *
	 * @param fechaHora
	 *            the new fecha hora
	 */
	public void setFechaHora(String fechaHora) {
		this.fechaHora = fechaHora;
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
