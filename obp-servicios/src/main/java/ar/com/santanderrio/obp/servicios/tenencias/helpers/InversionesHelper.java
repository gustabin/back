/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tenencias.helpers;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.tenencias.dto.PlazoFijoDTO;
import ar.com.santanderrio.obp.servicios.tenencias.view.CustodiaResumenView;
import ar.com.santanderrio.obp.servicios.tenencias.view.CustodiaView;
import ar.com.santanderrio.obp.servicios.tenencias.view.FirmanteView;
import ar.com.santanderrio.obp.servicios.tenencias.view.FondoPendienteView;
import ar.com.santanderrio.obp.servicios.tenencias.view.FondoResumenView;
import ar.com.santanderrio.obp.servicios.tenencias.view.FondoView;
import ar.com.santanderrio.obp.servicios.tenencias.view.InversionesView;
import ar.com.santanderrio.obp.servicios.tenencias.view.PlazoFijoView;
import ar.com.santanderrio.obp.servicios.tenencias.view.TenenciasView;
import ar.com.santanderrio.obp.servicios.tenencias.view.TipoSaldoView;

/**
 * The Class InversionesHelper.
 */
public final class InversionesHelper {

	/** The Constant CONST_MIL. */
	private static final String CONST_MIL = "1000";

	/** The Constant CONST_CERO. */
	private static final String CONST_CERO = "0.00";

	/** The Constant MON_ARS. */
	private static final String MON_ARS = "ARS";

	/** The Constant MON_USD. */
	private static final String MON_USD = "USD";

	/** The Constant CERO. */
	private static final String CERO = "0";

	/** The Constant GUION. */
	private static final String GUION = "-";

	/** The Constant BARRA. */
	private static final String BARRA = "/";

	/** The Constant VACIO. */
	private static final String VACIO = "";

	/**
	 * Instantiates a new inversiones helper.
	 */
	private InversionesHelper() {
	}

	/**
	 * Agregar custodias.
	 *
	 * @param inversiones
	 *            the inversiones
	 * @param custodias
	 *            the custodias
	 * @param firmantesCustodia
	 *            the firmantes custodia
	 */
	public static void agregarCustodias(InversionesView inversiones, List<CustodiaView> custodias,
			List<FirmanteView> firmantesCustodia) {
		for (CustodiaView custodia : custodias) {
			List<FirmanteView> firmantes = new ArrayList<FirmanteView>();
			for (FirmanteView firmante : firmantesCustodia) {
				if (custodia.getCuenta().equals(String.valueOf(firmante.getCuenta()))) {
					firmantes.add(firmante);
				}
			}
			custodia.setFirmantes(firmantes);
			custodia.setCuenta(TenenciasHelper.nroCuentaCanonico(custodia.getCuenta()));
			custodia.setDivisa(TenenciasHelper.monedaCanonico(custodia.getDivisa()));
			custodia.setValorResidual(ISBANStringUtils.formatearConComaYVariosDecimales2(String.valueOf(
					Double.parseDouble(custodia.getValorNominal()) * Double.parseDouble(custodia.getPorcResidual())), 4));
			custodia.setImporte(ISBANStringUtils.formatearConComaYDosDecimales2(custodia.getImporte()));
			custodia.setValorNominal(ISBANStringUtils.formatearConComaYVariosDecimales2(custodia.getValorNominal(), 4));

			if ("MON".equals(custodia.getEspeTipo()) || "CEF".equals(custodia.getEspeTipo())) {
				if (inversiones.getMonedaExtranjera() == null) {
					inversiones.setMonedaExtranjera(new ArrayList<CustodiaResumenView>());
				}
				agregarCustodia(custodia, inversiones.getMonedaExtranjera());
			} else if ("BON".equals(custodia.getEspeTipo())) {
				if (inversiones.getBonos() == null) {
					inversiones.setBonos(new ArrayList<CustodiaResumenView>());
				}
				agregarCustodia(custodia, inversiones.getBonos());
			} else if ("SHS".equals(custodia.getEspeTipo())) {
				if (inversiones.getAcciones() == null) {
					inversiones.setAcciones(new ArrayList<CustodiaResumenView>());
				}
				agregarCustodia(custodia, inversiones.getAcciones());
			} else if ("FON".equals(custodia.getEspeTipo())) {
				if (inversiones.getFondosExtranjeros() == null) {
					inversiones.setFondosExtranjeros(new ArrayList<CustodiaResumenView>());
				}
				agregarCustodia(custodia, inversiones.getFondosExtranjeros());
			}
		}
	}

	/**
	 * Agregar custodia.
	 *
	 * @param custodia
	 *            the custodia
	 * @param lista
	 *            the lista
	 */
	public static void agregarCustodia(CustodiaView custodia, List<CustodiaResumenView> lista) {
		int flag = 0;
		for (CustodiaResumenView resumen : lista) {
			if (resumen.getCuenta().equals(custodia.getCuenta())) {
				flag = 1;
				resumen.getCustodias().add(custodia);
				break;
			}
		}

		if (flag == 0) {
			CustodiaResumenView newResumen = new CustodiaResumenView();
			newResumen.setAnio(custodia.getAnio());
			newResumen.setCuenta(custodia.getCuenta());
			newResumen.setCustodias(new ArrayList<CustodiaView>());
			newResumen.getCustodias().add(custodia);
			lista.add(newResumen);
		}
	}

	/**
	 * Agregar plazo fijos.
	 *
	 * @param inversiones
	 *            the inversiones
	 * @param plazoFijos
	 *            the plazo fijos
	 * @param firmantesPlazoFijos
	 *            the firmantes plazo fijos
	 */
	public static void agregarPlazoFijos(InversionesView inversiones, List<PlazoFijoDTO> plazoFijos,
			List<FirmanteView> firmantesPlazoFijos) {
		Map<String, PlazoFijoView> auxMap = new HashMap<String, PlazoFijoView>();
		List<PlazoFijoView> resultPF = new ArrayList<PlazoFijoView>();
		for (PlazoFijoDTO plazoFijo : plazoFijos) {
			if (plazoFijo.getPenumcon().trim().isEmpty()) {
				if ("TENENCIA".equals(plazoFijo.getConcepto()) && MON_ARS.equals(plazoFijo.getDivisa())) {
					inversiones.setTotalTenenciasPlazosFijos(
							String.valueOf(new BigDecimal(inversiones.getTotalTenenciasPlazosFijos()).add(
									new BigDecimal(plazoFijo.getImporte()))));
				} else if ("TENENCIA".equals(plazoFijo.getConcepto()) && MON_USD.equals(plazoFijo.getDivisa())) {
					inversiones.setTotalTenenciasPlazosFijosUS(
							String.valueOf(new BigDecimal(inversiones.getTotalTenenciasPlazosFijosUS()).add(
									new BigDecimal(plazoFijo.getImporte()))));
				}
			} else if (!plazoFijo.getPenumcon().trim().isEmpty()) {
				agregarPFResumen(auxMap, plazoFijo, firmantesPlazoFijos);
			}
		}
		for (Map.Entry<String, PlazoFijoView> entry : auxMap.entrySet()) {
			resultPF.add(entry.getValue());
		}
		inversiones.setPlazoFijos(resultPF);
	}
	
	/**
	 * Agregar PF resumen.
	 *
	 * @param map
	 *            the map
	 * @param plazoFijo
	 *            the plazo fijo
	 * @param firmantesPlazoFijos
	 *            the firmantes plazo fijos
	 */
	private static void agregarPFResumen(Map<String, PlazoFijoView> map, PlazoFijoDTO plazoFijo,
			List<FirmanteView> firmantesPlazoFijos) {
		PlazoFijoView selectedPF;
		if (map.containsKey(plazoFijo.getPenumcon())) {
			selectedPF = map.get(plazoFijo.getPenumcon());
		} else {
			PlazoFijoView newPlazoFijo = new PlazoFijoView();
			newPlazoFijo.setPenumcon(plazoFijo.getPenumcon());
			newPlazoFijo.setDivisa(TenenciasHelper.monedaCanonico(plazoFijo.getDivisa()));
			newPlazoFijo.setAnio(plazoFijo.getAnio());
			List<FirmanteView> firmantes = new ArrayList<FirmanteView>();
			for (FirmanteView firmante : firmantesPlazoFijos) {
				if(plazoFijo.getPenumcon().substring(2).equals(firmante.getPenumcom())){
				firmantes.add(firmante);
				}
			}
			newPlazoFijo.setFirmantes(firmantes);
			newPlazoFijo.setTenencia(new ArrayList<TipoSaldoView>());
			newPlazoFijo.setIntDevengado(new ArrayList<TipoSaldoView>());
			newPlazoFijo.setIntPercibido(new ArrayList<TipoSaldoView>());

			newPlazoFijo.setTotalDevengado("0");
			newPlazoFijo.setTotalPercibido("0");
			newPlazoFijo.setTotalTenencia("0");
			newPlazoFijo.setTotalDevengadoUS("0");
			newPlazoFijo.setTotalPercibidoUS("0");
			newPlazoFijo.setTotalTenenciaUS("0");
			newPlazoFijo.setTotalAjustePorCer("0,00");
			newPlazoFijo.setTotalAjustePorCerUS("0,00");
			newPlazoFijo.setTotalAjustePorCerDev("0,00");
			newPlazoFijo.setTotalAjustePorCerDevUs("0,00");
			
			map.put(newPlazoFijo.getPenumcon(), newPlazoFijo);
			selectedPF = newPlazoFijo;
		}
		if (plazoFijo.getTipo().trim().isEmpty()) {
			if ("AJUCOB".equals(plazoFijo.getConcepto()) && MON_ARS.equals(plazoFijo.getDivisa())) {
				selectedPF.setTotalAjustePorCer(ISBANStringUtils.formatearConComaYDosDecimales(plazoFijo.getImporte()));
			} else if ("AJUCOB".equals(plazoFijo.getConcepto()) && MON_USD.equals(plazoFijo.getDivisa())) {
				selectedPF.setTotalAjustePorCerUS(ISBANStringUtils.formatearConComaYDosDecimales(plazoFijo.getImporte()));
			} else if ("AJUDEV".equals(plazoFijo.getConcepto()) && MON_USD.equals(plazoFijo.getDivisa())) {
				selectedPF.setTotalAjustePorCerDevUs(ISBANStringUtils.formatearConComaYDosDecimales(plazoFijo.getImporte()));
			}else if ("AJUDEV".equals(plazoFijo.getConcepto()) && MON_ARS.equals(plazoFijo.getDivisa())) {
				selectedPF.setTotalAjustePorCerDev(ISBANStringUtils.formatearConComaYDosDecimales(plazoFijo.getImporte()));
			}
		} else {
			agregarPFTipoSaldos(selectedPF, plazoFijo);
		}
		// agregarPFTipoSaldos(selectedPF, plazoFijo);
	}

	/**
	 * Agregar PF tipo saldos.
	 *
	 * @param selectedPF
	 *            the selected PF
	 * @param plazoFijo
	 *            the plazo fijo
	 */
	private static void agregarPFTipoSaldos(PlazoFijoView selectedPF, PlazoFijoDTO plazoFijo) {
		int flag = 0;
		if ("TENENCIA".equals(plazoFijo.getConcepto())) {
			for (TipoSaldoView tipoSaldo : selectedPF.getTenencia()) {
				if (plazoFijo.getTipo() == tipoSaldo.getTipo()) {
					flag = 1;
					tipoSaldo.setSaldo(String.valueOf(new BigDecimal(tipoSaldo.getSaldo()).add(new BigDecimal(plazoFijo.getImporte()))));
					break;
				}
			}
			if (flag == 0) {
				TipoSaldoView tipoSaldo = new TipoSaldoView();
				tipoSaldo.setSaldo(plazoFijo.getImporte());
				tipoSaldo.setTipo(plazoFijo.getTipo());
				selectedPF.getTenencia().add(tipoSaldo);
			}
			if (MON_ARS.equals(plazoFijo.getDivisa())) {
				selectedPF.setTotalTenencia(String.valueOf(new BigDecimal(plazoFijo.getImporte()).add(
						new BigDecimal(selectedPF.getTotalTenencia()))));
			} else {
				selectedPF.setTotalTenenciaUS(String.valueOf(new BigDecimal(plazoFijo.getImporte()).add(
				        new BigDecimal(selectedPF.getTotalTenenciaUS()))));
			}
		} else if ("INTDEV".equals(plazoFijo.getConcepto())) {
			for (TipoSaldoView tipoSaldo : selectedPF.getIntDevengado()) {
				if (plazoFijo.getTipo() == tipoSaldo.getTipo()) {
					flag = 1;
					tipoSaldo.setSaldo(String.valueOf(new BigDecimal(tipoSaldo.getSaldo()).add(new BigDecimal(plazoFijo.getImporte()))));
					break;
				}
			}
			if (flag == 0) {
				TipoSaldoView tipoSaldo = new TipoSaldoView();
				tipoSaldo.setSaldo(plazoFijo.getImporte());
				tipoSaldo.setTipo(plazoFijo.getTipo());
				selectedPF.getIntDevengado().add(tipoSaldo);
			}
			if (MON_ARS.equals(plazoFijo.getDivisa())) {
				selectedPF.setTotalDevengado(String.valueOf(new BigDecimal(plazoFijo.getImporte()).add(
						new BigDecimal(selectedPF.getTotalDevengado()))));
			} else {
				selectedPF.setTotalDevengadoUS(String.valueOf(new BigDecimal(plazoFijo.getImporte()).add(
						new BigDecimal(selectedPF.getTotalDevengadoUS()))));
			}
		} else if ("INTCOB".equals(plazoFijo.getConcepto())) {
			for (TipoSaldoView tipoSaldo : selectedPF.getIntPercibido()) {
				if (plazoFijo.getTipo() == tipoSaldo.getTipo()) {
					flag = 1;
					tipoSaldo.setSaldo(String.valueOf( new BigDecimal(tipoSaldo.getSaldo()).add(new BigDecimal(plazoFijo.getImporte()))));
					break;
				}
			}
			if (flag == 0) {
				TipoSaldoView tipoSaldo = new TipoSaldoView();
				tipoSaldo.setSaldo(plazoFijo.getImporte());
				tipoSaldo.setTipo(plazoFijo.getTipo());
				selectedPF.getIntPercibido().add(tipoSaldo);
			}
			if (MON_ARS.equals(plazoFijo.getDivisa())) {
				selectedPF.setTotalPercibido(String.valueOf(new BigDecimal(plazoFijo.getImporte()).add(
						new BigDecimal(selectedPF.getTotalPercibido()))));
			} else {
				selectedPF.setTotalPercibidoUS(String.valueOf(new BigDecimal(plazoFijo.getImporte()).add(
						new BigDecimal(selectedPF.getTotalPercibidoUS()))));
			}
		}
	}

	/**
	 * Agregar fondos.
	 *
	 * @param inversiones
	 *            the inversiones
	 * @param fondos
	 *            the fondos
	 * @param firmantesFondos
	 *            the firmantes fondos
	 */
	public static void agregarFondos(InversionesView inversiones, List<FondoView> fondos,
			List<FirmanteView> firmantesFondos) {

		if (inversiones.getFondos() == null) {
			inversiones.setFondos(new ArrayList<FondoResumenView>());
		}

		for (FondoView fondo : fondos) {
			fondo.setCuenta(TenenciasHelper.nroCuentaCanonico(fondo.getCuenta()));
			fondo.setDivisa(TenenciasHelper.monedaCanonico(fondo.getDivisa()));

			String auxValor = fondo.getImporte().trim().isEmpty() ? CONST_CERO : fondo.getImporte().trim();
			BigDecimal valor = new BigDecimal(auxValor);
			valor = valor.divide(new BigDecimal(CONST_MIL), 2, BigDecimal.ROUND_DOWN);

			fondo.setImporte(ISBANStringUtils.formatearSaldo(valor));

			auxValor = fondo.getCotiAfip().trim().isEmpty() ? CONST_CERO : fondo.getCotiAfip().trim();
			valor = new BigDecimal(auxValor);
			valor = valor.divide(new BigDecimal(CONST_MIL), 6, BigDecimal.ROUND_CEILING);
			fondo.setCotiAfip(formatearSaldo6Decimales(valor));

			BigDecimal cuota = new BigDecimal(fondo.getSaldoCuotas());
			fondo.setSaldoCuotas(formatearSaldo4Decimales(cuota));

			int flag = 0;
			for (FondoResumenView resumen : inversiones.getFondos()) {
				if (resumen.getCuenta().equals(fondo.getCuenta())) {
					flag = 1;
					resumen.getFondos().add(fondo);
					break;
				}
			}

			if (flag == 0) {
				FondoResumenView newResumen = new FondoResumenView();
				newResumen.setAnio(fondo.getAnio());
				newResumen.setCuenta(fondo.getCuenta());
				newResumen.setFondos(new ArrayList<FondoView>());
				newResumen.getFondos().add(fondo);
				List<FirmanteView> firmantes = new ArrayList<FirmanteView>();
				for (FirmanteView firmante : firmantesFondos) {
					if (newResumen.getCuenta().equals(String.valueOf(firmante.getCuenta()))) {
						firmantes.add(firmante);
					}
				}
				newResumen.setFirmantes(firmantes);
				inversiones.getFondos().add(newResumen);
			}
		}

	}
	
	/**
	 * Agregar fondos Resumen Impositivo.
	 *
	 * @param inversiones
	 *            the inversiones
	 * @param fondos
	 *            the fondos
	 * @param firmantesFondos
	 *            the firmantes fondos
	 */
	public static void agregarFondosRimp(InversionesView inversiones, List<FondoView> fondos,
			List<FirmanteView> firmantesFondos) {

		if (inversiones.getFondos() == null) {
			inversiones.setFondos(new ArrayList<FondoResumenView>());
		}

		for (FondoView fondo : fondos) {
			fondo.setCuenta(TenenciasHelper.nroCuentaCanonico(fondo.getCuenta()));
			fondo.setDivisa(TenenciasHelper.monedaCanonico(fondo.getDivisa()));

			String auxValor = fondo.getImporte().trim().isEmpty() ? CONST_CERO : fondo.getImporte().trim();
			fondo.setImporte(auxValor);
			auxValor = fondo.getCotiAfip().trim().isEmpty() ? CONST_CERO : fondo.getCotiAfip().trim();
			fondo.setCotiAfip(auxValor);
			fondo.setSaldoCuotas(fondo.getSaldoCuotas());
			int flag = 0;
			for (FondoResumenView resumen : inversiones.getFondos()) {
				if (resumen.getCuenta().equals(fondo.getCuenta())) {
					flag = 1;
					resumen.getFondos().add(fondo);
					break;
				}
			}

			if (flag == 0) {
				FondoResumenView newResumen = new FondoResumenView();
				newResumen.setAnio(fondo.getAnio());
				newResumen.setCuenta(fondo.getCuenta());
				newResumen.setFondos(new ArrayList<FondoView>());
				newResumen.getFondos().add(fondo);
				List<FirmanteView> firmantes = new ArrayList<FirmanteView>();
				for (FirmanteView firmante : firmantesFondos) {
					if (newResumen.getCuenta().equals(String.valueOf(firmante.getCuenta()))) {
						firmantes.add(firmante);
					}
				}
				newResumen.setFirmantes(firmantes);
				inversiones.getFondos().add(newResumen);
			}
		}

	}

	/**
	 * Agregar fondos pendientes.
	 *
	 * @param inversiones
	 *            the inversiones
	 * @param fondosPendientes
	 *            the fondos pendientes
	 */
	public static void agregarFondosPendientes(InversionesView inversiones, List<FondoPendienteView> fondosPendientes) {
		for (FondoPendienteView fondoPendiente : fondosPendientes) {
			fondoPendiente.setCuenta(TenenciasHelper.nroCuentaCanonico(fondoPendiente.getCuenta()));
			fondoPendiente.setDivisa(TenenciasHelper.monedaCanonico(fondoPendiente.getDivisa()));
			String auxValor = fondoPendiente.getCotiAfip().trim().isEmpty() ? CONST_CERO
					: fondoPendiente.getCotiAfip().trim();
			BigDecimal valor = new BigDecimal(auxValor);
			valor = valor.divide(new BigDecimal(CONST_MIL), 6, BigDecimal.ROUND_CEILING);
			fondoPendiente.setCotiAfip(valor.toString());

		}
		inversiones.setFondosPendientes(fondosPendientes);
	}

	/**
	 * Formatear PF.
	 *
	 * @param view
	 *            the view
	 */
	public static void formatearPF(TenenciasView view) {
		if (view.getInversiones().getPlazoFijos() != null) {
			for (PlazoFijoView pf : view.getInversiones().getPlazoFijos()) {
				for (TipoSaldoView ts : pf.getTenencia()) {
					ts.setSaldo(ISBANStringUtils.formatearConComaYDosDecimales2(ts.getSaldo()));
				}
				for (TipoSaldoView ts : pf.getIntDevengado()) {
					ts.setSaldo(ISBANStringUtils.formatearConComaYDosDecimales2(ts.getSaldo()));
				}
				for (TipoSaldoView ts : pf.getIntPercibido()) {
					ts.setSaldo(ISBANStringUtils.formatearConComaYDosDecimales2(ts.getSaldo()));
				}
				pf.setTotalDevengado(ISBANStringUtils.formatearConComaYDosDecimales2(pf.getTotalDevengado()));
				pf.setTotalPercibido(ISBANStringUtils.formatearConComaYDosDecimales2(pf.getTotalPercibido()));
				pf.setTotalTenencia(ISBANStringUtils.formatearConComaYDosDecimales2(pf.getTotalTenencia()));
				pf.setTotalDevengadoUS(ISBANStringUtils.formatearConComaYDosDecimales2(pf.getTotalDevengadoUS()));
				pf.setTotalPercibidoUS(ISBANStringUtils.formatearConComaYDosDecimales2(pf.getTotalPercibidoUS()));
				pf.setTotalTenenciaUS(ISBANStringUtils.formatearConComaYDosDecimales2(pf.getTotalTenenciaUS()));
			}
			view.getInversiones().setTotalTenenciasPlazosFijos(ISBANStringUtils
					.formatearConComaYDosDecimales2(view.getInversiones().getTotalTenenciasPlazosFijos()));
			view.getInversiones().setTotalTenenciasPlazosFijosUS(ISBANStringUtils
					.formatearConComaYDosDecimales2(view.getInversiones().getTotalTenenciasPlazosFijosUS()));
		}
	}

	/**
	 * Formatear saldo 4 decimales.
	 *
	 * @param saldo
	 *            the saldo
	 * @return the string
	 */
	public static String formatearSaldo4Decimales(BigDecimal saldo) {
		BigDecimal abs = saldo == null ? BigDecimal.ZERO : saldo.abs();
		DecimalFormatSymbols simbolos = new DecimalFormatSymbols(Locale.getDefault());
		simbolos.setDecimalSeparator(',');
		simbolos.setGroupingSeparator('.');
		DecimalFormat df = new DecimalFormat("#,##0.0000", simbolos);
		return df.format(abs);
	}

	/**
	 * Formatear saldo 6 decimales.
	 *
	 * @param saldo
	 *            the saldo
	 * @return the string
	 */
	public static String formatearSaldo6Decimales(BigDecimal saldo) {
		BigDecimal abs = saldo == null ? BigDecimal.ZERO : saldo.abs();
		DecimalFormatSymbols simbolos = new DecimalFormatSymbols(Locale.getDefault());
		simbolos.setDecimalSeparator(',');
		simbolos.setGroupingSeparator('.');
		DecimalFormat df = new DecimalFormat("#,##0.000000", simbolos);
		return df.format(abs);
	}

	/**
	 * Determina si una cuenta es vacia.
	 * 
	 * @param saldo
	 *            the saldo
	 * @return the string
	 */
	public static Boolean isCuentaVacia(String cuenta) {
		String cuentaParseada = cuenta.replace(CERO, VACIO).replace(GUION, VACIO).replace(BARRA, VACIO);
		if (StringUtils.isBlank(cuentaParseada)) {
			return true;
		} else {
			return false;
		}
	}
}
