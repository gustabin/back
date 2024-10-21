/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tenencias.helpers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.entities.Estadistica;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;

/**
 * The Class TenenciasEstadisticaHelper.
 */
@Component("tenenciasEstadisticaHelper")
public class TenenciasEstadisticaHelper {

	/** The Constant CONST_2007 *. */
	private static final int CONST_2007 = 2007;

	/** The Constant CONST_2008 *. */
	private static final int CONST_2008 = 2008;

	/** The Constant CONST_2009 *. */
	private static final int CONST_2009 = 2009;

	/** The Constant CONST_2010 *. */
	private static final int CONST_2010 = 2010;

	/** The Constant CONST_2011 *. */
	private static final int CONST_2011 = 2011;

	/** The Constant CONST_2012 *. */
	private static final int CONST_2012 = 2012;

	/** The Constant CONST_2013 *. */
	private static final int CONST_2013 = 2013;

//	/** The Constant CONST_2014 *. */
//	private static final int CONST_2014 = 2014;
//
//	/** The Constant CONST_2015 *. */
//	private static final int CONST_2015 = 2015;
//
//	/** The Constant CONST_2016 *. */
//	private static final int CONST_2016 = 2016;
//
//	/** The Constant CONST_2017 *. */
//	private static final int CONST_2017 = 2017;

	/** The Constant DET_TARJ_CREDITO *. */
	private static final int DET_TARJ_CREDITO = 1;

	/** The Constant DET_PRESTAMO *. */
	private static final int DET_PRESTAMO = 3;

	/** The Constant DET_INVERSIONES *. */
	private static final int DET_INVERSIONES = 5;

	/** The Constant DET_IMPUESTOS *. */
	private static final int DET_IMPUESTOS = 6;

	/** The anio. */
	int anio;

	/** The estadistica manager. */
	@Autowired
	private EstadisticaManager estadisticaManager;

	/**
	 * Constructor que recibe el anio.
	 */
	public TenenciasEstadisticaHelper() {

	}

	/**
	 * Sets the anio.
	 *
	 * @param anio
	 *            the new anio
	 */
	public void setAnio(String anio) {
		this.anio = Integer.parseInt(anio);
	}

	/**
	 * Rep estadistica tenencia.
	 *
	 * @param error
	 *            the error
	 */
	public void repEstadisticaTenencia(boolean error) {
		String codigoTransaccion = "";
		switch (anio) {
		case CONST_2007:
			codigoTransaccion = EstadisticasConstants.INFORMACION_IMPOSITIVA_07;
			break;
		case CONST_2008:
			codigoTransaccion = EstadisticasConstants.INFORMACION_IMPOSITIVA_08;
			break;
		case CONST_2009:
			codigoTransaccion = EstadisticasConstants.INFORMACION_IMPOSITIVA_09;
			break;
		case CONST_2010:
			codigoTransaccion = EstadisticasConstants.INFORMACION_IMPOSITIVA_10;
			break;
		case CONST_2011:
			codigoTransaccion = EstadisticasConstants.INFORMACION_IMPOSITIVA_11;
			break;
		case CONST_2012:
			codigoTransaccion = EstadisticasConstants.INFORMACION_IMPOSITIVA_12;
			break;
		case CONST_2013:
			codigoTransaccion = EstadisticasConstants.INFORMACION_IMPOSITIVA_13;
			break;
//		case CONST_2014:
//			codigoTransaccion = EstadisticasConstants.INFORMACION_IMPOSITIVA_14;
//			break;
//		case CONST_2015:
//			codigoTransaccion = EstadisticasConstants.INFORMACION_IMPOSITIVA_14;
//			break;
//		case CONST_2016:
//			codigoTransaccion = EstadisticasConstants.INFORMACION_IMPOSITIVA_14;
//			break;
//		case CONST_2017:
//			codigoTransaccion = EstadisticasConstants.INFORMACION_IMPOSITIVA_14;
//			break;
		default:
			codigoTransaccion = EstadisticasConstants.INFORMACION_IMPOSITIVA_14;
			break;
		}

		Estadistica estadistica = new Estadistica();
		estadistica.setNroComprobante(String.valueOf(anio));
		
		if (error) {
			estadisticaManager.add(estadistica, codigoTransaccion, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		} else {
			estadisticaManager.add(estadistica, codigoTransaccion, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		}

	}

	/**
	 * Rep estadistica det tenencia.
	 *
	 * @param tipoDetalle
	 *            the tipo detalle
	 * @param error
	 *            the error
	 */
	public void repEstadisticaDetTenencia(int tipoDetalle, boolean error) {

		String codigoMonedaExtranjera = "";
		String codigoPF = "";
		String codigoPFIntPer = "";
		String codigoPFAjusPer = "";
		String codigoPFIntDev = "";
		String codigoPFRet = "";
		String codigoAjusDev = "";
		String codigoDetFondos = "";
		String codigoDetAcciones = "";
		String codigoDetBonos = "";
		String codigoDetPrestamos = "";
		String codigoDetImpuestos = "";
		String codError = "";

		Estadistica estadistica = new Estadistica();
		estadistica.setNroComprobante(String.valueOf(anio));
		
		if (error) {
			codError = EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR;
		} else {
			codError = EstadisticasConstants.CODIGO_ESTADISTICAS_OK;
		}

		switch (anio) {
		case CONST_2007:
			codigoMonedaExtranjera = EstadisticasConstants.INFORMACION_IMPOSITIVA_07_DET_MONEDA_EXTRANJERA;
			codigoPF = EstadisticasConstants.INFORMACION_IMPOSITIVA_07_DET_PLAZO_FIJO;
			codigoPFIntPer = EstadisticasConstants.INFORMACION_IMPOSITIVA_07_DET_PF_INT_PERCIBIDOS;
			codigoPFAjusPer = EstadisticasConstants.INFORMACION_IMPOSITIVA_07_DET_PF_AJUS_CER_PERCIBIDOS;
			codigoPFIntDev = EstadisticasConstants.INFORMACION_IMPOSITIVA_07_DET_PF_INT_DEVENGADOS;
			codigoPFRet = EstadisticasConstants.INFORMACION_IMPOSITIVA_07_DET_PF_RET_IMP_GANANCIAS;
			codigoAjusDev = EstadisticasConstants.INFORMACION_IMPOSITIVA_07_AJUS_CER_DEVENGADOS;
			codigoDetFondos = EstadisticasConstants.INFORMACION_IMPOSITIVA_07_DET_FONDOS;
			codigoDetAcciones = EstadisticasConstants.INFORMACION_IMPOSITIVA_07_DET_ACCIONES;
			codigoDetBonos = EstadisticasConstants.INFORMACION_IMPOSITIVA_07_DET_BONOS;
			codigoDetPrestamos = EstadisticasConstants.INFORMACION_IMPOSITIVA_07_DET_PRESTAMOS;
			codigoDetImpuestos = EstadisticasConstants.INFORMACION_IMPOSITIVA_07_DET_IMPUESTOS;
			break;
		case CONST_2008:
			codigoMonedaExtranjera = EstadisticasConstants.INFORMACION_IMPOSITIVA_08_DET_MONEDA_EXTRANJERA;
			codigoPF = EstadisticasConstants.INFORMACION_IMPOSITIVA_08_DET_PLAZO_FIJO;
			codigoPFIntPer = EstadisticasConstants.INFORMACION_IMPOSITIVA_08_DET_PF_INT_PERCIBIDOS;
			codigoPFAjusPer = EstadisticasConstants.INFORMACION_IMPOSITIVA_08_DET_PF_AJUS_CER_PERCIBIDOS;
			codigoPFIntDev = EstadisticasConstants.INFORMACION_IMPOSITIVA_08_DET_PF_INT_DEVENGADOS;
			codigoPFRet = EstadisticasConstants.INFORMACION_IMPOSITIVA_08_DET_PF_RET_IMP_GANANCIAS;
			codigoAjusDev = EstadisticasConstants.INFORMACION_IMPOSITIVA_08_AJUS_CER_DEVENGADOS;
			codigoDetFondos = EstadisticasConstants.INFORMACION_IMPOSITIVA_08_DET_FONDOS;
			codigoDetAcciones = EstadisticasConstants.INFORMACION_IMPOSITIVA_08_DET_ACCIONES;
			codigoDetBonos = EstadisticasConstants.INFORMACION_IMPOSITIVA_08_DET_BONOS;
			codigoDetPrestamos = EstadisticasConstants.INFORMACION_IMPOSITIVA_08_DET_PRESTAMOS;
			codigoDetImpuestos = EstadisticasConstants.INFORMACION_IMPOSITIVA_08_DET_IMPUESTOS;
			break;
		case CONST_2009:
			codigoMonedaExtranjera = EstadisticasConstants.INFORMACION_IMPOSITIVA_09_DET_MONEDA_EXTRANJERA;
			codigoPF = EstadisticasConstants.INFORMACION_IMPOSITIVA_09_DET_PLAZO_FIJO;
			codigoPFIntPer = EstadisticasConstants.INFORMACION_IMPOSITIVA_09_DET_PF_INT_PERCIBIDOS;
			codigoPFAjusPer = EstadisticasConstants.INFORMACION_IMPOSITIVA_09_DET_PF_AJUS_CER_PERCIBIDOS;
			codigoPFIntDev = EstadisticasConstants.INFORMACION_IMPOSITIVA_09_DET_PF_INT_DEVENGADOS;
			codigoPFRet = EstadisticasConstants.INFORMACION_IMPOSITIVA_09_DET_PF_RET_IMP_GANANCIAS;
			codigoAjusDev = EstadisticasConstants.INFORMACION_IMPOSITIVA_09_AJUS_CER_DEVENGADOS;
			codigoDetFondos = EstadisticasConstants.INFORMACION_IMPOSITIVA_09_DET_FONDOS;
			codigoDetAcciones = EstadisticasConstants.INFORMACION_IMPOSITIVA_09_DET_ACCIONES;
			codigoDetBonos = EstadisticasConstants.INFORMACION_IMPOSITIVA_09_DET_BONOS;
			codigoDetPrestamos = EstadisticasConstants.INFORMACION_IMPOSITIVA_09_DET_PRESTAMOS;
			codigoDetImpuestos = EstadisticasConstants.INFORMACION_IMPOSITIVA_09_DET_IMPUESTOS;
			break;
		case CONST_2010:
			codigoMonedaExtranjera = EstadisticasConstants.INFORMACION_IMPOSITIVA_10_DET_MONEDA_EXTRANJERA;
			codigoPF = EstadisticasConstants.INFORMACION_IMPOSITIVA_10_DET_PLAZO_FIJO;
			codigoPFIntPer = EstadisticasConstants.INFORMACION_IMPOSITIVA_10_DET_PF_INT_PERCIBIDOS;
			codigoPFAjusPer = EstadisticasConstants.INFORMACION_IMPOSITIVA_10_DET_PF_AJUS_CER_PERCIBIDOS;
			codigoPFIntDev = EstadisticasConstants.INFORMACION_IMPOSITIVA_10_DET_PF_INT_DEVENGADOS;
			codigoPFRet = EstadisticasConstants.INFORMACION_IMPOSITIVA_10_DET_PF_RET_IMP_GANANCIAS;
			codigoAjusDev = EstadisticasConstants.INFORMACION_IMPOSITIVA_10_AJUS_CER_DEVENGADOS;
			codigoDetFondos = EstadisticasConstants.INFORMACION_IMPOSITIVA_10_DET_FONDOS;
			codigoDetAcciones = EstadisticasConstants.INFORMACION_IMPOSITIVA_10_DET_ACCIONES;
			codigoDetBonos = EstadisticasConstants.INFORMACION_IMPOSITIVA_10_DET_BONOS;
			codigoDetPrestamos = EstadisticasConstants.INFORMACION_IMPOSITIVA_10_DET_PRESTAMOS;
			codigoDetImpuestos = EstadisticasConstants.INFORMACION_IMPOSITIVA_10_DET_IMPUESTOS;
			break;
		case CONST_2011:
			codigoMonedaExtranjera = EstadisticasConstants.INFORMACION_IMPOSITIVA_11_DET_MONEDA_EXTRANJERA;
			codigoPF = EstadisticasConstants.INFORMACION_IMPOSITIVA_11_DET_PLAZO_FIJO;
			codigoPFIntPer = EstadisticasConstants.INFORMACION_IMPOSITIVA_11_DET_PF_INT_PERCIBIDOS;
			codigoPFAjusPer = EstadisticasConstants.INFORMACION_IMPOSITIVA_11_DET_PF_AJUS_CER_PERCIBIDOS;
			codigoPFIntDev = EstadisticasConstants.INFORMACION_IMPOSITIVA_11_DET_PF_INT_DEVENGADOS;
			codigoPFRet = EstadisticasConstants.INFORMACION_IMPOSITIVA_11_DET_PF_RET_IMP_GANANCIAS;
			codigoAjusDev = EstadisticasConstants.INFORMACION_IMPOSITIVA_11_AJUS_CER_DEVENGADOS;
			codigoDetFondos = EstadisticasConstants.INFORMACION_IMPOSITIVA_11_DET_FONDOS;
			codigoDetAcciones = EstadisticasConstants.INFORMACION_IMPOSITIVA_11_DET_ACCIONES;
			codigoDetBonos = EstadisticasConstants.INFORMACION_IMPOSITIVA_11_DET_BONOS;
			codigoDetPrestamos = EstadisticasConstants.INFORMACION_IMPOSITIVA_11_DET_PRESTAMOS;
			codigoDetImpuestos = EstadisticasConstants.INFORMACION_IMPOSITIVA_11_DET_IMPUESTOS;
			break;
		case CONST_2012:
			codigoMonedaExtranjera = EstadisticasConstants.INFORMACION_IMPOSITIVA_12_DET_MONEDA_EXTRANJERA;
			codigoPF = EstadisticasConstants.INFORMACION_IMPOSITIVA_12_DET_PLAZO_FIJO;
			codigoPFIntPer = EstadisticasConstants.INFORMACION_IMPOSITIVA_12_DET_PF_INT_PERCIBIDOS;
			codigoPFAjusPer = EstadisticasConstants.INFORMACION_IMPOSITIVA_12_DET_PF_AJUS_CER_PERCIBIDOS;
			codigoPFIntDev = EstadisticasConstants.INFORMACION_IMPOSITIVA_12_DET_PF_INT_DEVENGADOS;
			codigoPFRet = EstadisticasConstants.INFORMACION_IMPOSITIVA_12_DET_PF_RET_IMP_GANANCIAS;
			codigoAjusDev = EstadisticasConstants.INFORMACION_IMPOSITIVA_12_AJUS_CER_DEVENGADOS;
			codigoDetFondos = EstadisticasConstants.INFORMACION_IMPOSITIVA_12_DET_FONDOS;
			codigoDetAcciones = EstadisticasConstants.INFORMACION_IMPOSITIVA_12_DET_ACCIONES;
			codigoDetBonos = EstadisticasConstants.INFORMACION_IMPOSITIVA_12_DET_BONOS;
			codigoDetPrestamos = EstadisticasConstants.INFORMACION_IMPOSITIVA_12_DET_PRESTAMOS;
			codigoDetImpuestos = EstadisticasConstants.INFORMACION_IMPOSITIVA_12_DET_IMPUESTOS;
			break;
		case CONST_2013:
			codigoMonedaExtranjera = EstadisticasConstants.INFORMACION_IMPOSITIVA_13_DET_MONEDA_EXTRANJERA;
			codigoPF = EstadisticasConstants.INFORMACION_IMPOSITIVA_13_DET_PLAZO_FIJO;
			codigoPFIntPer = EstadisticasConstants.INFORMACION_IMPOSITIVA_13_DET_PF_INT_PERCIBIDOS;
			codigoPFAjusPer = EstadisticasConstants.INFORMACION_IMPOSITIVA_13_DET_PF_AJUS_CER_PERCIBIDOS;
			codigoPFIntDev = EstadisticasConstants.INFORMACION_IMPOSITIVA_13_DET_PF_INT_DEVENGADOS;
			codigoPFRet = EstadisticasConstants.INFORMACION_IMPOSITIVA_13_DET_PF_RET_IMP_GANANCIAS;
			codigoAjusDev = EstadisticasConstants.INFORMACION_IMPOSITIVA_13_AJUS_CER_DEVENGADOS;
			codigoDetFondos = EstadisticasConstants.INFORMACION_IMPOSITIVA_13_DET_FONDOS;
			codigoDetAcciones = EstadisticasConstants.INFORMACION_IMPOSITIVA_13_DET_ACCIONES;
			codigoDetBonos = EstadisticasConstants.INFORMACION_IMPOSITIVA_13_DET_BONOS;
			codigoDetPrestamos = EstadisticasConstants.INFORMACION_IMPOSITIVA_13_DET_PRESTAMOS;
			codigoDetImpuestos = EstadisticasConstants.INFORMACION_IMPOSITIVA_13_DET_IMPUESTOS;
			break;
//		case CONST_2014:
//			codigoMonedaExtranjera = EstadisticasConstants.INFORMACION_IMPOSITIVA_14_DET_MONEDA_EXTRANJERA;
//			codigoPF = EstadisticasConstants.INFORMACION_IMPOSITIVA_14_DET_PLAZO_FIJO;
//			codigoPFIntPer = EstadisticasConstants.INFORMACION_IMPOSITIVA_14_DET_PF_INT_PERCIBIDOS;
//			codigoPFAjusPer = EstadisticasConstants.INFORMACION_IMPOSITIVA_14_DET_PF_AJUS_CER_PERCIBIDOS;
//			codigoPFIntDev = EstadisticasConstants.INFORMACION_IMPOSITIVA_14_DET_PF_INT_DEVENGADOS;
//			codigoPFRet = EstadisticasConstants.INFORMACION_IMPOSITIVA_14_DET_PF_RET_IMP_GANANCIAS;
//			codigoAjusDev = EstadisticasConstants.INFORMACION_IMPOSITIVA_14_AJUS_CER_DEVENGADOS;
//			codigoDetFondos = EstadisticasConstants.INFORMACION_IMPOSITIVA_14_DET_FONDOS;
//			codigoDetAcciones = EstadisticasConstants.INFORMACION_IMPOSITIVA_14_DET_ACCIONES;
//			codigoDetBonos = EstadisticasConstants.INFORMACION_IMPOSITIVA_14_DET_BONOS;
//			codigoDetPrestamos = EstadisticasConstants.INFORMACION_IMPOSITIVA_14_DET_PRESTAMOS;
//			codigoDetImpuestos = EstadisticasConstants.INFORMACION_IMPOSITIVA_14_DET_IMPUESTOS;
//			break;
//		case CONST_2015:
//			codigoMonedaExtranjera = EstadisticasConstants.INFORMACION_IMPOSITIVA_14_DET_MONEDA_EXTRANJERA;
//			codigoPF = EstadisticasConstants.INFORMACION_IMPOSITIVA_14_DET_PLAZO_FIJO;
//			codigoPFIntPer = EstadisticasConstants.INFORMACION_IMPOSITIVA_14_DET_PF_INT_PERCIBIDOS;
//			codigoPFAjusPer = EstadisticasConstants.INFORMACION_IMPOSITIVA_14_DET_PF_AJUS_CER_PERCIBIDOS;
//			codigoPFIntDev = EstadisticasConstants.INFORMACION_IMPOSITIVA_14_DET_PF_INT_DEVENGADOS;
//			codigoPFRet = EstadisticasConstants.INFORMACION_IMPOSITIVA_14_DET_PF_RET_IMP_GANANCIAS;
//			codigoAjusDev = EstadisticasConstants.INFORMACION_IMPOSITIVA_14_AJUS_CER_DEVENGADOS;
//			codigoDetFondos = EstadisticasConstants.INFORMACION_IMPOSITIVA_14_DET_FONDOS;
//			codigoDetAcciones = EstadisticasConstants.INFORMACION_IMPOSITIVA_14_DET_ACCIONES;
//			codigoDetBonos = EstadisticasConstants.INFORMACION_IMPOSITIVA_14_DET_BONOS;
//			codigoDetPrestamos = EstadisticasConstants.INFORMACION_IMPOSITIVA_14_DET_PRESTAMOS;
//			codigoDetImpuestos = EstadisticasConstants.INFORMACION_IMPOSITIVA_14_DET_IMPUESTOS;
//			break;
//		case CONST_2016:
//			codigoMonedaExtranjera = EstadisticasConstants.INFORMACION_IMPOSITIVA_14_DET_MONEDA_EXTRANJERA;
//			codigoPF = EstadisticasConstants.INFORMACION_IMPOSITIVA_14_DET_PLAZO_FIJO;
//			codigoPFIntPer = EstadisticasConstants.INFORMACION_IMPOSITIVA_14_DET_PF_INT_PERCIBIDOS;
//			codigoPFAjusPer = EstadisticasConstants.INFORMACION_IMPOSITIVA_14_DET_PF_AJUS_CER_PERCIBIDOS;
//			codigoPFIntDev = EstadisticasConstants.INFORMACION_IMPOSITIVA_14_DET_PF_INT_DEVENGADOS;
//			codigoPFRet = EstadisticasConstants.INFORMACION_IMPOSITIVA_14_DET_PF_RET_IMP_GANANCIAS;
//			codigoAjusDev = EstadisticasConstants.INFORMACION_IMPOSITIVA_14_AJUS_CER_DEVENGADOS;
//			codigoDetFondos = EstadisticasConstants.INFORMACION_IMPOSITIVA_14_DET_FONDOS;
//			codigoDetAcciones = EstadisticasConstants.INFORMACION_IMPOSITIVA_14_DET_ACCIONES;
//			codigoDetBonos = EstadisticasConstants.INFORMACION_IMPOSITIVA_14_DET_BONOS;
//			codigoDetPrestamos = EstadisticasConstants.INFORMACION_IMPOSITIVA_14_DET_PRESTAMOS;
//			codigoDetImpuestos = EstadisticasConstants.INFORMACION_IMPOSITIVA_14_DET_IMPUESTOS;
//			break;
//		case CONST_2017:
//			codigoMonedaExtranjera = EstadisticasConstants.INFORMACION_IMPOSITIVA_14_DET_MONEDA_EXTRANJERA;
//			codigoPF = EstadisticasConstants.INFORMACION_IMPOSITIVA_14_DET_PLAZO_FIJO;
//			codigoPFIntPer = EstadisticasConstants.INFORMACION_IMPOSITIVA_14_DET_PF_INT_PERCIBIDOS;
//			codigoPFAjusPer = EstadisticasConstants.INFORMACION_IMPOSITIVA_14_DET_PF_AJUS_CER_PERCIBIDOS;
//			codigoPFIntDev = EstadisticasConstants.INFORMACION_IMPOSITIVA_14_DET_PF_INT_DEVENGADOS;
//			codigoPFRet = EstadisticasConstants.INFORMACION_IMPOSITIVA_14_DET_PF_RET_IMP_GANANCIAS;
//			codigoAjusDev = EstadisticasConstants.INFORMACION_IMPOSITIVA_14_AJUS_CER_DEVENGADOS;
//			codigoDetFondos = EstadisticasConstants.INFORMACION_IMPOSITIVA_14_DET_FONDOS;
//			codigoDetAcciones = EstadisticasConstants.INFORMACION_IMPOSITIVA_14_DET_ACCIONES;
//			codigoDetBonos = EstadisticasConstants.INFORMACION_IMPOSITIVA_14_DET_BONOS;
//			codigoDetPrestamos = EstadisticasConstants.INFORMACION_IMPOSITIVA_14_DET_PRESTAMOS;
//			codigoDetImpuestos = EstadisticasConstants.INFORMACION_IMPOSITIVA_14_DET_IMPUESTOS;
//			break;
		default:
			codigoMonedaExtranjera = EstadisticasConstants.INFORMACION_IMPOSITIVA_14_DET_MONEDA_EXTRANJERA;
			codigoPF = EstadisticasConstants.INFORMACION_IMPOSITIVA_14_DET_PLAZO_FIJO;
			codigoPFIntPer = EstadisticasConstants.INFORMACION_IMPOSITIVA_14_DET_PF_INT_PERCIBIDOS;
			codigoPFAjusPer = EstadisticasConstants.INFORMACION_IMPOSITIVA_14_DET_PF_AJUS_CER_PERCIBIDOS;
			codigoPFIntDev = EstadisticasConstants.INFORMACION_IMPOSITIVA_14_DET_PF_INT_DEVENGADOS;
			codigoPFRet = EstadisticasConstants.INFORMACION_IMPOSITIVA_14_DET_PF_RET_IMP_GANANCIAS;
			codigoAjusDev = EstadisticasConstants.INFORMACION_IMPOSITIVA_14_AJUS_CER_DEVENGADOS;
			codigoDetFondos = EstadisticasConstants.INFORMACION_IMPOSITIVA_14_DET_FONDOS;
			codigoDetAcciones = EstadisticasConstants.INFORMACION_IMPOSITIVA_14_DET_ACCIONES;
			codigoDetBonos = EstadisticasConstants.INFORMACION_IMPOSITIVA_14_DET_BONOS;
			codigoDetPrestamos = EstadisticasConstants.INFORMACION_IMPOSITIVA_14_DET_PRESTAMOS;
			codigoDetImpuestos = EstadisticasConstants.INFORMACION_IMPOSITIVA_14_DET_IMPUESTOS;
			break;
		}
		switch (tipoDetalle) {
		case DET_TARJ_CREDITO:
			estadisticaManager.add(estadistica, codigoMonedaExtranjera, codError);
			break;
		case DET_INVERSIONES:
			estadisticaManager.add(estadistica, codigoPF, codError);
			estadisticaManager.add(estadistica, codigoPFIntPer, codError);
			estadisticaManager.add(estadistica, codigoPFAjusPer, codError);
			estadisticaManager.add(estadistica, codigoPFIntDev, codError);
			estadisticaManager.add(estadistica, codigoPFRet, codError);
			estadisticaManager.add(estadistica, codigoAjusDev, codError);
			estadisticaManager.add(estadistica, codigoPFIntDev, codError);
			estadisticaManager.add(estadistica, codigoDetFondos, codError);
			estadisticaManager.add(estadistica, codigoDetAcciones, codError);
			estadisticaManager.add(estadistica, codigoDetBonos, codError);
			break;
		case DET_PRESTAMO:
			estadisticaManager.add(estadistica, codigoDetPrestamos, codError);
			break;
		case DET_IMPUESTOS:
			estadisticaManager.add(estadistica, codigoDetImpuestos, codError);
			break;
		}

	}

	/**
	 * Rep estadistica excel tenencia.
	 *
	 * @param error
	 *            the error
	 */
	public void repEstadisticaExcelTenencia(boolean error) {
		String codigoDownload = "";
		String codigoDownloadPF = "";
		String codigoDownloadFondo = "";
		String codigoDownloadAcciones = "";
		String codigoDownloadBonos = "";
		String codigoDownloadPrestamos = "";
		String codigoDownloadImpuestos = "";
		String codError = "";
		if (error) {
			codError = EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR;
		} else {
			codError = EstadisticasConstants.CODIGO_ESTADISTICAS_OK;
		}
		
		Estadistica estadistica = new Estadistica();
		estadistica.setNroComprobante(String.valueOf(anio));

		switch (anio) {
		case CONST_2007:
			codigoDownload = EstadisticasConstants.INFORMACION_IMPOSITIVA_07_DOWNLOAD;
			codigoDownloadPF = EstadisticasConstants.INFORMACION_IMPOSITIVA_07_DOWNLOAD_PLAZO_FIJO;
			codigoDownloadFondo = EstadisticasConstants.INFORMACION_IMPOSITIVA_07_DOWNLOAD_FONDOS;
			codigoDownloadAcciones = EstadisticasConstants.INFORMACION_IMPOSITIVA_07_DOWNLOAD_ACCIONES;
			codigoDownloadBonos = EstadisticasConstants.INFORMACION_IMPOSITIVA_07_DOWNLOAD_BONOS;
			codigoDownloadPrestamos = EstadisticasConstants.INFORMACION_IMPOSITIVA_07_DOWNLOAD_PRESTAMOS;
			codigoDownloadImpuestos = EstadisticasConstants.INFORMACION_IMPOSITIVA_07_DOWNLOAD_IMPUESTOS;
			break;
		case CONST_2008:
			codigoDownload = EstadisticasConstants.INFORMACION_IMPOSITIVA_08_DOWNLOAD;
			codigoDownloadPF = EstadisticasConstants.INFORMACION_IMPOSITIVA_08_DOWNLOAD_PLAZO_FIJO;
			codigoDownloadFondo = EstadisticasConstants.INFORMACION_IMPOSITIVA_08_DOWNLOAD_FONDOS;
			codigoDownloadAcciones = EstadisticasConstants.INFORMACION_IMPOSITIVA_08_DOWNLOAD_ACCIONES;
			codigoDownloadBonos = EstadisticasConstants.INFORMACION_IMPOSITIVA_08_DOWNLOAD_BONOS;
			codigoDownloadPrestamos = EstadisticasConstants.INFORMACION_IMPOSITIVA_08_DOWNLOAD_PRESTAMOS;
			codigoDownloadImpuestos = EstadisticasConstants.INFORMACION_IMPOSITIVA_08_DOWNLOAD_IMPUESTOS;
			break;
		case CONST_2009:
			codigoDownload = EstadisticasConstants.INFORMACION_IMPOSITIVA_09_DOWNLOAD;
			codigoDownloadPF = EstadisticasConstants.INFORMACION_IMPOSITIVA_09_DOWNLOAD_PLAZO_FIJO;
			codigoDownloadFondo = EstadisticasConstants.INFORMACION_IMPOSITIVA_09_DOWNLOAD_FONDOS;
			codigoDownloadAcciones = EstadisticasConstants.INFORMACION_IMPOSITIVA_09_DOWNLOAD_ACCIONES;
			codigoDownloadBonos = EstadisticasConstants.INFORMACION_IMPOSITIVA_09_DOWNLOAD_BONOS;
			codigoDownloadPrestamos = EstadisticasConstants.INFORMACION_IMPOSITIVA_09_DOWNLOAD_PRESTAMOS;
			codigoDownloadImpuestos = EstadisticasConstants.INFORMACION_IMPOSITIVA_09_DOWNLOAD_IMPUESTOS;
			break;
		case CONST_2010:
			codigoDownload = EstadisticasConstants.INFORMACION_IMPOSITIVA_10_DOWNLOAD;
			codigoDownloadPF = EstadisticasConstants.INFORMACION_IMPOSITIVA_10_DOWNLOAD_PLAZO_FIJO;
			codigoDownloadFondo = EstadisticasConstants.INFORMACION_IMPOSITIVA_10_DOWNLOAD_FONDOS;
			codigoDownloadAcciones = EstadisticasConstants.INFORMACION_IMPOSITIVA_10_DOWNLOAD_ACCIONES;
			codigoDownloadBonos = EstadisticasConstants.INFORMACION_IMPOSITIVA_10_DOWNLOAD_BONOS;
			codigoDownloadPrestamos = EstadisticasConstants.INFORMACION_IMPOSITIVA_10_DOWNLOAD_PRESTAMOS;
			codigoDownloadImpuestos = EstadisticasConstants.INFORMACION_IMPOSITIVA_10_DOWNLOAD_IMPUESTOS;
			break;
		case CONST_2011:
			codigoDownload = EstadisticasConstants.INFORMACION_IMPOSITIVA_11_DOWNLOAD;
			codigoDownloadPF = EstadisticasConstants.INFORMACION_IMPOSITIVA_11_DOWNLOAD_PLAZO_FIJO;
			codigoDownloadFondo = EstadisticasConstants.INFORMACION_IMPOSITIVA_11_DOWNLOAD_FONDOS;
			codigoDownloadAcciones = EstadisticasConstants.INFORMACION_IMPOSITIVA_11_DOWNLOAD_ACCIONES;
			codigoDownloadBonos = EstadisticasConstants.INFORMACION_IMPOSITIVA_11_DOWNLOAD_BONOS;
			codigoDownloadPrestamos = EstadisticasConstants.INFORMACION_IMPOSITIVA_11_DOWNLOAD_PRESTAMOS;
			codigoDownloadImpuestos = EstadisticasConstants.INFORMACION_IMPOSITIVA_11_DOWNLOAD_IMPUESTOS;
			break;
		case CONST_2012:
			codigoDownload = EstadisticasConstants.INFORMACION_IMPOSITIVA_12_DOWNLOAD;
			codigoDownloadPF = EstadisticasConstants.INFORMACION_IMPOSITIVA_12_DOWNLOAD_PLAZO_FIJO;
			codigoDownloadFondo = EstadisticasConstants.INFORMACION_IMPOSITIVA_12_DOWNLOAD_FONDOS;
			codigoDownloadAcciones = EstadisticasConstants.INFORMACION_IMPOSITIVA_12_DOWNLOAD_ACCIONES;
			codigoDownloadBonos = EstadisticasConstants.INFORMACION_IMPOSITIVA_12_DOWNLOAD_BONOS;
			codigoDownloadPrestamos = EstadisticasConstants.INFORMACION_IMPOSITIVA_12_DOWNLOAD_PRESTAMOS;
			codigoDownloadImpuestos = EstadisticasConstants.INFORMACION_IMPOSITIVA_12_DOWNLOAD_IMPUESTOS;
			break;
		case CONST_2013:
			codigoDownload = EstadisticasConstants.INFORMACION_IMPOSITIVA_13_DOWNLOAD;
			codigoDownloadPF = EstadisticasConstants.INFORMACION_IMPOSITIVA_13_DOWNLOAD_PLAZO_FIJO;
			codigoDownloadFondo = EstadisticasConstants.INFORMACION_IMPOSITIVA_13_DOWNLOAD_FONDOS;
			codigoDownloadAcciones = EstadisticasConstants.INFORMACION_IMPOSITIVA_13_DOWNLOAD_ACCIONES;
			codigoDownloadBonos = EstadisticasConstants.INFORMACION_IMPOSITIVA_13_DOWNLOAD_BONOS;
			codigoDownloadPrestamos = EstadisticasConstants.INFORMACION_IMPOSITIVA_13_DOWNLOAD_PRESTAMOS;
			codigoDownloadImpuestos = EstadisticasConstants.INFORMACION_IMPOSITIVA_13_DOWNLOAD_IMPUESTOS;
			break;
//		case CONST_2014:
//			codigoDownload = EstadisticasConstants.INFORMACION_IMPOSITIVA_14_DOWNLOAD;
//			codigoDownloadPF = EstadisticasConstants.INFORMACION_IMPOSITIVA_14_DOWNLOAD_PLAZO_FIJO;
//			codigoDownloadFondo = EstadisticasConstants.INFORMACION_IMPOSITIVA_14_DOWNLOAD_FONDOS;
//			codigoDownloadAcciones = EstadisticasConstants.INFORMACION_IMPOSITIVA_14_DOWNLOAD_ACCIONES;
//			codigoDownloadBonos = EstadisticasConstants.INFORMACION_IMPOSITIVA_14_DOWNLOAD_BONOS;
//			codigoDownloadPrestamos = EstadisticasConstants.INFORMACION_IMPOSITIVA_14_DOWNLOAD_PRESTAMOS;
//			codigoDownloadImpuestos = EstadisticasConstants.INFORMACION_IMPOSITIVA_14_DOWNLOAD_IMPUESTOS;
//			break;
//		case CONST_2015:
//			codigoDownload = EstadisticasConstants.INFORMACION_IMPOSITIVA_14_DOWNLOAD;
//			codigoDownloadPF = EstadisticasConstants.INFORMACION_IMPOSITIVA_14_DOWNLOAD_PLAZO_FIJO;
//			codigoDownloadFondo = EstadisticasConstants.INFORMACION_IMPOSITIVA_14_DOWNLOAD_FONDOS;
//			codigoDownloadAcciones = EstadisticasConstants.INFORMACION_IMPOSITIVA_14_DOWNLOAD_ACCIONES;
//			codigoDownloadBonos = EstadisticasConstants.INFORMACION_IMPOSITIVA_14_DOWNLOAD_BONOS;
//			codigoDownloadPrestamos = EstadisticasConstants.INFORMACION_IMPOSITIVA_14_DOWNLOAD_PRESTAMOS;
//			codigoDownloadImpuestos = EstadisticasConstants.INFORMACION_IMPOSITIVA_14_DOWNLOAD_IMPUESTOS;
//			break;
//		case CONST_2016:
//			codigoDownload = EstadisticasConstants.INFORMACION_IMPOSITIVA_14_DOWNLOAD;
//			codigoDownloadPF = EstadisticasConstants.INFORMACION_IMPOSITIVA_14_DOWNLOAD_PLAZO_FIJO;
//			codigoDownloadFondo = EstadisticasConstants.INFORMACION_IMPOSITIVA_14_DOWNLOAD_FONDOS;
//			codigoDownloadAcciones = EstadisticasConstants.INFORMACION_IMPOSITIVA_14_DOWNLOAD_ACCIONES;
//			codigoDownloadBonos = EstadisticasConstants.INFORMACION_IMPOSITIVA_14_DOWNLOAD_BONOS;
//			codigoDownloadPrestamos = EstadisticasConstants.INFORMACION_IMPOSITIVA_14_DOWNLOAD_PRESTAMOS;
//			codigoDownloadImpuestos = EstadisticasConstants.INFORMACION_IMPOSITIVA_14_DOWNLOAD_IMPUESTOS;
//			break;
//		case CONST_2017:
//			codigoDownload = EstadisticasConstants.INFORMACION_IMPOSITIVA_14_DOWNLOAD;
//			codigoDownloadPF = EstadisticasConstants.INFORMACION_IMPOSITIVA_14_DOWNLOAD_PLAZO_FIJO;
//			codigoDownloadFondo = EstadisticasConstants.INFORMACION_IMPOSITIVA_14_DOWNLOAD_FONDOS;
//			codigoDownloadAcciones = EstadisticasConstants.INFORMACION_IMPOSITIVA_14_DOWNLOAD_ACCIONES;
//			codigoDownloadBonos = EstadisticasConstants.INFORMACION_IMPOSITIVA_14_DOWNLOAD_BONOS;
//			codigoDownloadPrestamos = EstadisticasConstants.INFORMACION_IMPOSITIVA_14_DOWNLOAD_PRESTAMOS;
//			codigoDownloadImpuestos = EstadisticasConstants.INFORMACION_IMPOSITIVA_14_DOWNLOAD_IMPUESTOS;
//			break;
		default:
			codigoDownload = EstadisticasConstants.INFORMACION_IMPOSITIVA_14_DOWNLOAD;
			codigoDownloadPF = EstadisticasConstants.INFORMACION_IMPOSITIVA_14_DOWNLOAD_PLAZO_FIJO;
			codigoDownloadFondo = EstadisticasConstants.INFORMACION_IMPOSITIVA_14_DOWNLOAD_FONDOS;
			codigoDownloadAcciones = EstadisticasConstants.INFORMACION_IMPOSITIVA_14_DOWNLOAD_ACCIONES;
			codigoDownloadBonos = EstadisticasConstants.INFORMACION_IMPOSITIVA_14_DOWNLOAD_BONOS;
			codigoDownloadPrestamos = EstadisticasConstants.INFORMACION_IMPOSITIVA_14_DOWNLOAD_PRESTAMOS;
			codigoDownloadImpuestos = EstadisticasConstants.INFORMACION_IMPOSITIVA_14_DOWNLOAD_IMPUESTOS;
			break;
		}

		estadisticaManager.add(estadistica, codigoDownload, codError);
		estadisticaManager.add(estadistica, codigoDownloadPF, codError);
		estadisticaManager.add(estadistica, codigoDownloadFondo, codError);
		estadisticaManager.add(estadistica, codigoDownloadAcciones, codError);
		estadisticaManager.add(estadistica, codigoDownloadBonos, codError);
		estadisticaManager.add(estadistica, codigoDownloadPrestamos, codError);
		estadisticaManager.add(estadistica, codigoDownloadImpuestos, codError);
	}

}
