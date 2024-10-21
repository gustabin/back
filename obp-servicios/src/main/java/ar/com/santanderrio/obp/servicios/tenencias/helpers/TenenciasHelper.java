/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tenencias.helpers;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.tenencias.dto.FirmantePrestamoDTO;
import ar.com.santanderrio.obp.servicios.tenencias.dto.FirmantesDTO;
import ar.com.santanderrio.obp.servicios.tenencias.dto.ImpuestoDTO;
import ar.com.santanderrio.obp.servicios.tenencias.dto.ImpuestoMonedaExtranjeraDTO;
import ar.com.santanderrio.obp.servicios.tenencias.dto.PrestamoDTO;
import ar.com.santanderrio.obp.servicios.tenencias.dto.TenenciasDTO;
import ar.com.santanderrio.obp.servicios.tenencias.exception.CopiarListaException;
import ar.com.santanderrio.obp.servicios.tenencias.view.CuentaView;
import ar.com.santanderrio.obp.servicios.tenencias.view.CustodiaView;
import ar.com.santanderrio.obp.servicios.tenencias.view.FirmantePrestamoView;
import ar.com.santanderrio.obp.servicios.tenencias.view.FirmanteView;
import ar.com.santanderrio.obp.servicios.tenencias.view.FondoPendienteView;
import ar.com.santanderrio.obp.servicios.tenencias.view.FondoView;
import ar.com.santanderrio.obp.servicios.tenencias.view.ImpuestoMonedaExtranjeraResumenView;
import ar.com.santanderrio.obp.servicios.tenencias.view.ImpuestoMonedaExtranjeraView;
import ar.com.santanderrio.obp.servicios.tenencias.view.ImpuestoView;
import ar.com.santanderrio.obp.servicios.tenencias.view.InversionesView;
import ar.com.santanderrio.obp.servicios.tenencias.view.PrestamoView;
import ar.com.santanderrio.obp.servicios.tenencias.view.ResumenCuentaInversionesView;
import ar.com.santanderrio.obp.servicios.tenencias.view.TarjetaView;
import ar.com.santanderrio.obp.servicios.tenencias.view.TenenciasView;

/**
 * The Class TenenciasHelper.
 */
public final class TenenciasHelper {

	/** The Constant ANIO_PL. */
	private static final int ANIO_PL = 2019;

	/** The Constant TIPO_MONEDA_ARS. */
	private static final String TIPO_MONEDA_ARS = "ARS";

	/** The Constant TIPO_MONEDA_USD. */
	private static final String TIPO_MONEDA_USD = "USD";
	
	/** The Constant NUMEROS_SIN_CERO. */
	private static final String NUMEROS_SIN_CERO = "123456789";
	
	/** The Constant BARRA. */
	private static final String GUION = "-";

	/**
	 * Instantiates a new tenencias helper.
	 */
	private TenenciasHelper() {
	}

	/**
	 * Copia una lista generica a otra.
	 *
	 * @param <T>
	 *            the generic type
	 * @param <E>
	 *            the element type
	 * @param inList
	 *            the in list
	 * @param outList
	 *            the out list
	 * @param targetClass
	 *            the target class
	 * @throws CopiarListaException
	 *             the copiar lista exception
	 */
	public static <T, E> void copiarLista(List<T> inList, List<E> outList, Class<E> targetClass)
			throws CopiarListaException {
		for (T inObject : inList) {
			E outObject = null;

			try {
				outObject = targetClass.newInstance();
				BeanUtils.copyProperties(outObject, inObject);
			} catch (InstantiationException e) {
				throw new CopiarListaException("Error al instanciar nuevo objeto " + targetClass.getName(), e);
			} catch (IllegalAccessException e) {
				throw new CopiarListaException("Error al acceder al metodo de " + targetClass.getName(), e);
			} catch (InvocationTargetException e) {
				throw new CopiarListaException("Error al copiar los properties", e);
			}

			outList.add(outObject);
		}

	}

	/**
	 * Agregar cuenta Y saldos.
	 *
	 * @param view
	 *            the view
	 * @param cuentas
	 *            the cuentas
	 * @param firmantesCuenta
	 *            the firmantes cuenta
	 */
	public static void agregarCuentaYSaldos(TenenciasView view, List<CuentaView> cuentas,
			List<FirmanteView> firmantesCuenta) {
		BigDecimal totSaldoArs = BigDecimal.ZERO;
		BigDecimal totSaldoUsd = BigDecimal.ZERO;
		List<CuentaView> cuentasP = new ArrayList<CuentaView>();
		List<CuentaView> cuentasME = new ArrayList<CuentaView>();
		for (CuentaView cuenta : cuentas) {
			List<FirmanteView> firmantes = new ArrayList<FirmanteView>();
			for (FirmanteView firmante : firmantesCuenta) {
				if (cuenta.getCuenta().equals(String.valueOf(firmante.getCuenta()))) {
					firmantes.add(firmante);
				}
			}

			cuenta.setFirmantes(firmantes);
			TipoCuenta tipoCuenta = TipoCuenta.fromCodigo(cuenta.getTipoCuenta());
			cuenta.setCuenta(TenenciasHelper.nroCuentaCanonico(cuenta.getPecodofi(), cuenta.getCuenta()));
			cuenta.setTipoCuenta(tipoCuenta.getDescripcionConMoneda());
			cuenta.setImporte(ISBANStringUtils.formatearConComaYDosDecimales2(cuenta.getImporte()));

			if (cuenta.getDivisa().equals(TIPO_MONEDA_ARS)) {
			    totSaldoArs = totSaldoArs.add(new BigDecimal(cuenta.getSaldo()));
				cuenta.setDivisa(TenenciasHelper.monedaCanonico(cuenta.getDivisa()));
				cuentasP.add(cuenta);
			} else if (cuenta.getDivisa().equals(TIPO_MONEDA_USD)) {
			    totSaldoUsd = totSaldoUsd.add(new BigDecimal(cuenta.getSaldo()));
				cuenta.setDivisa(TenenciasHelper.monedaCanonico(cuenta.getDivisa()));
				cuentasME.add(cuenta);
			}
		}

		view.setSaldoTotalPesos(ISBANStringUtils.formatearConComaYDosDecimales2(String.valueOf(totSaldoArs)));
		view.setSaldoTotalDolares(ISBANStringUtils.formatearConComaYDosDecimales2(String.valueOf(totSaldoUsd)));
		view.setCuentas(cuentasP);
		view.setCuentasME(cuentasME);

	}

	/**
	 * Obtener prestamos view.
	 *
	 * @param lstDTOs
	 *            the lst DT os
	 * @param firmantesDTO
	 *            the firmantes DTO
	 * @return the list
	 * @throws CopiarListaException
	 *             the copiar lista exception
	 */
	public static List<PrestamoView> obtenerPrestamosView(List<PrestamoDTO> lstDTOs,
			List<FirmantePrestamoDTO> firmantesDTO) throws CopiarListaException {
		List<PrestamoView> prestamos = new ArrayList<PrestamoView>();
		TenenciasHelper.copiarLista(lstDTOs, prestamos, PrestamoView.class);
		List<FirmantePrestamoView> firmantesPrestamo = new ArrayList<FirmantePrestamoView>();
		TenenciasHelper.copiarLista(firmantesDTO, firmantesPrestamo, FirmantePrestamoView.class);
		if (!firmantesPrestamo.isEmpty()) {
			for (PrestamoView prestamo : prestamos) {
				List<FirmantePrestamoView> firmantes = new ArrayList<FirmantePrestamoView>();
				for (FirmantePrestamoView firmante : firmantesPrestamo) {
					firmantes.add(firmante);
				}
				prestamo.setFirmantes(firmantes);
				prestamo.setCuenta(nroCuentaCanonico(prestamo.getPecodofi(), prestamo.getCuenta()));
				prestamo.setDivisa(monedaCanonico(prestamo.getDivisa()));
				prestamo.setTipoProducto(tipoProductoPrestamo(prestamo.getTipoProducto()));
				prestamo.setSituacion(situacionPrestamo(prestamo.getSituacion()));
				SimpleDateFormat fechaFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date fecha;
				try {
					fecha = fechaFormat.parse(prestamo.getFechaFormalizacion());
				} catch (ParseException e) {
					throw new CopiarListaException("Error al parsear la fecha", e);
				}
				String fcParseada = ISBANStringUtils.formatearFecha(fecha);
				prestamo.setFechaFormalizacion(fcParseada);

			}

		}
		return prestamos;
	}

	/**
	 * Obtener impuestos view.
	 *
	 * @param lstDTOs
	 *            the lst DT os
	 * @return the list
	 * @throws CopiarListaException
	 *             the copiar lista exception
	 */
	public static List<ImpuestoView> obtenerImpuestosView(List<ImpuestoDTO> lstDTOs) throws CopiarListaException {
		Map<String, String> indexImpuestos = new HashMap<String, String>();
		List<ImpuestoView> impuestos = new ArrayList<ImpuestoView>();
		List<ImpuestoView> impNewLst = new ArrayList<ImpuestoView>();
		TenenciasHelper.copiarLista(lstDTOs, impuestos, ImpuestoView.class);
		int lastPos = 0;
		for (ImpuestoView impuesto : impuestos) {
			String claveBusqueda = impuesto.getAnio() + impuesto.getTipoCuenta() + impuesto.getPecodofi()
					+ impuesto.getCuenta();
			String posArray = indexImpuestos.get(claveBusqueda);
			ImpuestoView imp = new ImpuestoView();
			int pos;
			if (posArray != null) {
				pos = Integer.parseInt(posArray);
				imp = impNewLst.get(pos);
			} else {
				pos = lastPos++;
				impNewLst.add(imp);
				indexImpuestos.put(claveBusqueda, String.valueOf(pos));
				imp.setCuenta(nroCuentaCanonico(impuesto.getPecodofi(), impuesto.getCuenta()));
				if (impuesto.getTipoCuenta() != null) {
					TipoCuenta tipoCuenta = TipoCuenta.fromCodigo(impuesto.getTipoCuenta());
					imp.setTipoCuenta(tipoCuenta.getDescripcionConMoneda());
				}
				imp.setDivisa(monedaCanonico(impuesto.getDivisa()));
				imp.setPecodofi(impuesto.getPecodofi());
			}

			calcularImpuestos(impuesto, imp);

			impNewLst.set(pos, imp);
		}
		impuestos.clear();
		TenenciasHelper.copiarLista(impNewLst, impuestos, ImpuestoView.class);
		return impuestos;
	}

	/**
	 * Calcular impuestos.
	 *
	 * @param impuesto
	 *            the impuesto
	 * @param imp
	 *            the imp
	 */
	private static void calcularImpuestos(ImpuestoView impuesto, ImpuestoView imp) {
		BigDecimal id = new BigDecimal(impuesto.getImpDebito());

		if (id.floatValue() > 0 && !"9.999".equals(impuesto.getAlicuota())) {
			BigDecimal sumBDImpDebito = new BigDecimal(imp.getSumImpDebito());
			imp.setSumImpDebito(sumBDImpDebito.add(id).toString());
		}

		BigDecimal ic = new BigDecimal(impuesto.getImpCredito());
		if (ic.floatValue() > 0) {
			BigDecimal sumBDImpCredito = new BigDecimal(imp.getSumImpCredito());
			imp.setSumImpCredito(sumBDImpCredito.add(ic).toString());
		}

		BigDecimal ic6 = new BigDecimal(impuesto.getImpCredito());
		if (ic6.floatValue() > 0 && "6".equals(impuesto.getAlicuota())) {
			BigDecimal sumBDImpCreditoAlicuota6 = new BigDecimal(imp.getSumImpCreditoAlicuota6());
			imp.setSumImpCreditoAlicuota6(sumBDImpCreditoAlicuota6.add(ic6).toString());
		}

		BigDecimal ic12 = new BigDecimal(impuesto.getImpCredito());
		if (ic12.floatValue() > 0 && "12".equals(impuesto.getAlicuota())) {
			BigDecimal sumBDImpCreditoAlicuota12 = new BigDecimal(imp.getSumImpCreditoAlicuota12());
			imp.setSumImpCreditoAlicuota12(sumBDImpCreditoAlicuota12.add(ic12).toString());
		}

		BigDecimal ico = new BigDecimal(impuesto.getImpComputable());
		if (ico.floatValue() > 0) {
			BigDecimal sumBDImpComputable = new BigDecimal(imp.getSumImpComputable());
			imp.setSumImpComputable(sumBDImpComputable.add(ico).toString());
		}

		if (id.floatValue() > 0 && "9.999".equals(impuesto.getAlicuota())) {
			BigDecimal sumBDIvaDev = new BigDecimal(imp.getIvaDev());
			imp.setIvaDev(sumBDIvaDev.add(id).toString());
		}
	}

	/**
	 * Obtener inversiones.
	 *
	 * @param tenenciasDTO
	 *            the tenencias DTO
	 * @param firmantesDTO
	 *            the firmantes DTO
	 * @return the inversiones view
	 * @throws CopiarListaException
	 *             the copiar lista exception
	 */
	public static InversionesView obtenerInversiones(TenenciasDTO tenenciasDTO, FirmantesDTO firmantesDTO)
			throws CopiarListaException {

		List<CustodiaView> custodias = new ArrayList<CustodiaView>();

		InversionesView inversiones = new InversionesView();
		TenenciasHelper.copiarLista(tenenciasDTO.getCustodiaDTO(), custodias, CustodiaView.class);
		if (!custodias.isEmpty()) {
			List<FirmanteView> firmantesCustodia = new ArrayList<FirmanteView>();
			TenenciasHelper.copiarLista(firmantesDTO.getCustodias(), firmantesCustodia, FirmanteView.class);
			InversionesHelper.agregarCustodias(inversiones, custodias, firmantesCustodia);
		}

		if (!tenenciasDTO.getPlazoFijoDTO().isEmpty()) {
			List<FirmanteView> firmantesPlazoFijos = new ArrayList<FirmanteView>();
			TenenciasHelper.copiarLista(firmantesDTO.getPlazoFijo(), firmantesPlazoFijos, FirmanteView.class);
			InversionesHelper.agregarPlazoFijos(inversiones, tenenciasDTO.getPlazoFijoDTO(), firmantesPlazoFijos);
		}

		List<FondoView> fondos = new ArrayList<FondoView>();
		TenenciasHelper.copiarLista(tenenciasDTO.getFondosDTO(), fondos, FondoView.class);
		if (!fondos.isEmpty()) {
			List<FirmanteView> firmantesFondos = new ArrayList<FirmanteView>();
			TenenciasHelper.copiarLista(firmantesDTO.getFondos(), firmantesFondos, FirmanteView.class);
			if (Integer.parseInt(fondos.get(0).getAnio()) >= ANIO_PL) {
			InversionesHelper.agregarFondosRimp(inversiones, fondos, firmantesFondos);
			}else {
				InversionesHelper.agregarFondos(inversiones, fondos, firmantesFondos);
			}
		}

		List<FondoPendienteView> fondosPendientes = new ArrayList<FondoPendienteView>();
		TenenciasHelper.copiarLista(tenenciasDTO.getFondosPendientesDTO(), fondosPendientes, FondoPendienteView.class);
		if (!fondosPendientes.isEmpty()) {
			InversionesHelper.agregarFondosPendientes(inversiones, fondosPendientes);
		}

		return inversiones;
	}

	/**
	 * Agregar imp moneda extranjera.
	 *
	 * @param tenencias
	 *            the tenencias
	 * @param lstDTOs
	 *            the lst DT os
	 * @throws CopiarListaException
	 *             the copiar lista exception
	 */
	public static void agregarImpMonedaExtranjera(TenenciasView tenencias, List<ImpuestoMonedaExtranjeraDTO> lstDTOs)
			throws CopiarListaException {
		if (lstDTOs != null && lstDTOs.isEmpty()) {
			return;
		}
		if (tenencias.getImpuestoMonedaExtranjera() == null) {
			tenencias.setImpuestoMonedaExtranjera(new ArrayList<ImpuestoMonedaExtranjeraResumenView>());
		}
		List<ImpuestoMonedaExtranjeraView> impuestoMonedaExts = new ArrayList<ImpuestoMonedaExtranjeraView>();
		TenenciasHelper.copiarLista(lstDTOs, impuestoMonedaExts, ImpuestoMonedaExtranjeraView.class);

		int flag = 0;
		for (ImpuestoMonedaExtranjeraView imp : impuestoMonedaExts) {
			imp.setCuenta(nroCuentaCanonico(imp.getPecodofi(), imp.getCuenta()));
			TipoCuenta tipoCuenta = TipoCuenta.fromCodigo(imp.getTipoCuenta());
			imp.setTipoCuenta(tipoCuenta.getDescripcionConMoneda());
			imp.setDivisa(monedaCanonico(imp.getDivisa()));
			for (ImpuestoMonedaExtranjeraResumenView resumen : tenencias.getImpuestoMonedaExtranjera()) {
				if (resumen.getCuenta().equals(imp.getCuenta())) {
					flag = 1;
					resumen.getImpuestosMonedaExtranjera().add(imp);
					break;
				}
			}

			if (flag == 0) {
				ImpuestoMonedaExtranjeraResumenView newResumen = new ImpuestoMonedaExtranjeraResumenView();
				newResumen.setAnio(imp.getAnio());
				newResumen.setCuenta(imp.getCuenta());
				newResumen.setTipoCuenta(imp.getTipoCuenta());
				newResumen.setImpuestosMonedaExtranjera(new ArrayList<ImpuestoMonedaExtranjeraView>());
				newResumen.getImpuestosMonedaExtranjera().add(imp);
				tenencias.getImpuestoMonedaExtranjera().add(newResumen);
			}
		}
		return;
	}

	/**
	 * Nro cuenta canonico.
	 *
	 * @param numero
	 *            the numero
	 * @return the string
	 */
	public static String nroCuentaCanonico(String numero) {
		String numeroTruncado = llenarConCerosIzqOTruncar(numero, 12);
		if (numeroTruncado.isEmpty()) {
			return "";
		}
		return numeroTruncado.substring(4, 11) + "/" + numeroTruncado.substring(11, 12);
	}

	/**
	 * Nro cuenta canonico.
	 *
	 * @param sucursal
	 *            the sucursal
	 * @param numero
	 *            the numero
	 * @return the string
	 */
	public static String nroCuentaCanonico(String sucursal, String numero) {
		String suc = llenarConCerosIzqOTruncar(sucursal, 3);
		if (suc.isEmpty() || numero == null || numero.isEmpty()) {
			return "";
		}
		String nro = llenarConCerosIzqOTruncar(numero, 7);
		return suc + "-" + nro.substring(0, 6) + "/" + nro.substring(6, 7);
	}

	/**
	 * Llenar con ceros izq O truncar.
	 *
	 * @param s
	 *            the s
	 * @param size
	 *            the size
	 * @return the string
	 */
	public static String llenarConCerosIzqOTruncar(String s, int size) {
		if (s == null) {
			return "";
		}
		int l = s.length();
		if (l >= size) {
			return s.substring(l - size, l);
		}
		StringBuilder ceros = new StringBuilder();
		for (int n = 0; n < size - l; ++n) {
			ceros.append("0");
		}
		return ceros + s;
	}

	/**
	 * Moneda canonico.
	 *
	 * @param moneda
	 *            the moneda
	 * @return the string
	 */
	public static String monedaCanonico(String moneda) {
		if ("ARS".equals(moneda) || "ARP".equals(moneda)) {
			return "$";
		} else if ("USD".equals(moneda)) {
			return "U$S";
		} else if ("EUR".equals(moneda)) {
			return "EUR";
		} else {
			return "";
		}
	}

	/**
	 * Tipo producto prestamo.
	 *
	 * @param codigo
	 *            the codigo
	 * @return the string
	 */
	public static String tipoProductoPrestamo(String codigo) {
		if ("HI".equals(codigo)) {
			return "Hipotecario";
		} else {
			return "";
		}
	}

	/**
	 * Situacion prestamo.
	 *
	 * @param codigo
	 *            the codigo
	 * @return the string
	 */
	public static String situacionPrestamo(String codigo) {
		if ("00".equals(codigo)) {
			return "Al día";
		} else if ("10".equals(codigo)) {
			return "Vencido";
		} else {
			return "";
		}
	}

	/**
	 * Formatear saldos tenencias.
	 *
	 * @param view
	 *            the view
	 */
	public static void formatearSaldosTenencias(TenenciasView view) {
		if (view.getCuentas() != null) {
			for (CuentaView cuenta : view.getCuentas()) {
				cuenta.setSaldo(ISBANStringUtils.formatearConComaYDosDecimales2(cuenta.getSaldo()));
				if (cuenta.getIntDevengado() != null && !"".equals(cuenta.getIntDevengado())) {
					cuenta.setImporteIntDev(ISBANStringUtils.formatearConComaYDosDecimales2(cuenta.getIntDevengado()));
					cuenta.setIntDevengado(ISBANStringUtils.formatearConComaYDosDecimales2(cuenta.getIntDevengado()));
				} else {
					cuenta.setImporteIntDev("0,00");
					cuenta.setIntDevengado("0,00");
				}
			}
		}

		if (view.getCuentasME() != null) {
			for (CuentaView cuenta : view.getCuentasME()) {
				cuenta.setSaldo(ISBANStringUtils.formatearConComaYDosDecimales2(cuenta.getSaldo()));
				if (cuenta.getIntDevengado() != null && !"".equals(cuenta.getIntDevengado())) {
					cuenta.setImporteIntDev(ISBANStringUtils.formatearConComaYDosDecimales2(cuenta.getImporteIntDev()));
					cuenta.setIntDevengado(ISBANStringUtils.formatearConComaYDosDecimales2(cuenta.getIntDevengado()));
				} else {
					cuenta.setImporteIntDev("0,00");
					cuenta.setIntDevengado("0,00");
				}
			}
		}

		for (ImpuestoView impuesto : view.getImpuestos()) {
			impuesto.setSumImpComputable(
					ISBANStringUtils.formatearSaldo(new BigDecimal(impuesto.getSumImpComputable())));
			impuesto.setSumImpCredito(ISBANStringUtils.formatearConComaYDosDecimales2(impuesto.getSumImpCredito()));
			impuesto.setSumImpCreditoAlicuota6(
					ISBANStringUtils.formatearConComaYDosDecimales2(impuesto.getSumImpCreditoAlicuota6()));
			impuesto.setSumImpCreditoAlicuota12(
					ISBANStringUtils.formatearConComaYDosDecimales2(impuesto.getSumImpCreditoAlicuota12()));
			impuesto.setSumImpDebito(ISBANStringUtils.formatearConComaYDosDecimales2(impuesto.getSumImpDebito()));
			impuesto.setIvaDev(ISBANStringUtils.formatearConComaYDosDecimales2(impuesto.getIvaDev()));
		}

		if (view.getImpuestoMonedaExtranjera() != null) {
			for (ImpuestoMonedaExtranjeraResumenView impuestoResumen : view.getImpuestoMonedaExtranjera()) {
				for (ImpuestoMonedaExtranjeraView impuesto : impuestoResumen.getImpuestosMonedaExtranjera()) {
					impuesto.setImpDebito(ISBANStringUtils.formatearConComaYDosDecimales2(impuesto.getImpDebito()));
					impuesto.setImpCredito(ISBANStringUtils.formatearConComaYDosDecimales2(impuesto.getImpCredito()));
					impuesto.setCuenta(TenenciasHelper.nroCuentaCanonico(impuesto.getPecodofi(), impuesto.getCuenta()));
					impuesto.setTotalCobrado(
							ISBANStringUtils.formatearConComaYDosDecimales2(impuesto.getTotalCobrado()));
				}
			}
		}

		if (view.getTarjetas() != null) {
			for (TarjetaView tarjeta : view.getTarjetas()) {
				tarjeta.setCuenta(nroCuentaCanonico(tarjeta.getPecodofi(), tarjeta.getCuenta()));
				tarjeta.setImpDebito(ISBANStringUtils.formatearConComaYDosDecimales2(tarjeta.getImpDebito()));
				tarjeta.setImpCredito(ISBANStringUtils.formatearConComaYDosDecimales2(tarjeta.getImpCredito()));
				tarjeta.setImpNeto(ISBANStringUtils.formatearConComaYDosDecimales2(tarjeta.getImpNeto()));
			}
		}

		InversionesHelper.formatearPF(view);

		for (PrestamoView prestamo : view.getPrestamos()) {
			prestamo.setIntDevengado(ISBANStringUtils.formatearConComaYDosDecimales2(prestamo.getIntDevengado()));
			prestamo.setIntCobrado(ISBANStringUtils.formatearConComaYDosDecimales2(prestamo.getIntCobrado()));
			prestamo.setSaldoDeuda(ISBANStringUtils.formatearConComaYDosDecimales2(prestamo.getSaldoDeuda()));
			prestamo.setSaldoVencido(ISBANStringUtils.formatearConComaYDosDecimales2(prestamo.getSaldoVencido()));
			prestamo.setIntVencido(ISBANStringUtils.formatearConComaYDosDecimales2(prestamo.getIntVencido()));
		}
	}
	
	/**
	 * este metodo remplaza los saldos que tengan todos ceros
	 * @param lista
	 */
	public static void formatearCamposQueTenganTodosCeros(List<ResumenCuentaInversionesView> lista) {
		for (ResumenCuentaInversionesView resumenCuentaInversionesView : lista) {
			if (reemplazarCeros(resumenCuentaInversionesView.getCotizacion())) {
				resumenCuentaInversionesView.setCotizacion(GUION);
			} else {
				//resumenCuentaInversionesView.setCotizacion(resumenCuentaInversionesView.getDivisa() + resumenCuentaInversionesView.getCotizacion());
				String cotiDosDecimales = ISBANStringUtils.agregadorDecimalesConservaOrigen4(resumenCuentaInversionesView.getCotizacion());
				resumenCuentaInversionesView.setCotizacion(cotiDosDecimales);
			}
			if (reemplazarCeros(resumenCuentaInversionesView.getImporte())) {
				resumenCuentaInversionesView.setImporte(GUION);
			} else {
				//resumenCuentaInversionesView.setImporte(resumenCuentaInversionesView.getDivisa() + resumenCuentaInversionesView.getImporte());
				resumenCuentaInversionesView.setImporte(resumenCuentaInversionesView.getImporte());
			}
			if (reemplazarCeros(resumenCuentaInversionesView.getNroCtaOri())) {
				resumenCuentaInversionesView.setNroCtaOri(StringUtils.EMPTY);
			}
			if (reemplazarCeros(resumenCuentaInversionesView.getCantidad())) {
				String cantidadDosDecimales = ISBANStringUtils.agregadorDecimalesConservaOrigen(resumenCuentaInversionesView.getCantidad());
				resumenCuentaInversionesView.setCantidad(cantidadDosDecimales);
			}
		}
	}
	/**
	 * Si  el campo pasado por parametro tiene algun numero que no sea cero, devuelve false
	 * @param campo
	 */
	private static boolean reemplazarCeros(String campo) {

		if (!StringUtils.isBlank(campo)) {
			for (int i = 0; i < campo.length(); i++) {
				char c = campo.charAt(i);
				if (NUMEROS_SIN_CERO.indexOf(c) != -1) {
					return false;
				}
			}

		}
		return true;

	}
	
	
}
