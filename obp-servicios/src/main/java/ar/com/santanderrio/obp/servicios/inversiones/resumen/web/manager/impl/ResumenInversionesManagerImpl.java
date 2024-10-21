/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.resumen.web.manager.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SessionResumenInversiones;
import ar.com.santanderrio.obp.servicios.cuentas.entities.AbstractCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.CuentaBancaPrivada;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.inversiones.resumen.bo.ResumenMensualInversionesBO;
import ar.com.santanderrio.obp.servicios.inversiones.resumen.web.manager.ResumenInversionesManager;
import ar.com.santanderrio.obp.servicios.inversiones.resumen.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.inversiones.resumen.web.view.ResumenInversionesView;
import ar.com.santanderrio.obp.servicios.inversiones.resumen.web.view.ResumenMensualInversionesView;
import ar.com.santanderrio.obp.servicios.inversiones.resumen.web.view.ResumenesInversionesView;
import ar.com.santanderrio.obp.servicios.inversiones.resumen.web.view.ResumenesMensualesInversionesView;
import ar.com.santanderrio.obp.servicios.resumen.entities.ReporteResumenFinanciacion;
import ar.com.santanderrio.obp.servicios.resumen.entities.ReporteResumenMensualInversiones;
import ar.com.santanderrio.obp.servicios.resumen.entities.ResumenMensualInversiones;
import ar.com.santanderrio.obp.servicios.tenencias.dto.ResumenFinancieroDTO;
import ar.com.santanderrio.obp.servicios.tenencias.view.ReporteFinancieroView;
import ar.com.santanderrio.obp.servicios.tenencias.view.TenenciasInView;

/**
 * The Class ResumenInversionesManagerImpl.
 */
@Component
public class ResumenInversionesManagerImpl implements ResumenInversionesManager {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ResumenInversionesManagerImpl.class);

	/** The estadistica manager. */
	@Autowired
	private EstadisticaManager estadisticaManager;

	/** The resumen mensual inversiones BO. */
	@Autowired
	private ResumenMensualInversionesBO resumenMensualInversionesBO;

	/** The sesion resumen inversiones. */
	@Autowired
	private SessionResumenInversiones sesionResumenInversiones;

	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/** The sesion cliente. */
	@Autowired
	SesionCliente sesionCliente;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.resumen.web.manager.
	 * ResumenInversionesManager#obtenerListaResumen(java.lang.String, boolean)
	 */
	@Override
	public Respuesta<ResumenesMensualesInversionesView> obtenerListaResumen(String nroCuenta, boolean isBP) {
		Respuesta<List<ResumenMensualInversiones>> resumenes = null;
		try {
			AbstractCuenta cuenta = obtenerCuenta(nroCuenta, isBP);
			// Obtiene los resumenes o los carga desde session si es necesario
			Respuesta<List<ResumenMensualInversiones>> resumenesSesionList = sesionResumenInversiones
					.getResumenesByCuenta(cuenta);
			if (resumenesSesionList == null || !EstadoRespuesta.OK.equals(resumenesSesionList.getEstadoRespuesta())) {
				resumenes = resumenMensualInversionesBO.obtenerListaResumen(cuenta, isBP);
				Respuesta<ResumenesMensualesInversionesView> respResumenMensual = armarRespuestaResumenesView(resumenes,
						cuenta, isBP);
				if (!EstadoRespuesta.ERROR.equals(respResumenMensual.getEstadoRespuesta())) {
					sesionResumenInversiones.setResumenesMensualesDisponiblesByCuenta(resumenes, cuenta);
				}
				return respResumenMensual;
			} else {
				return armarRespuestaResumenesView(resumenesSesionList, cuenta, isBP);
			}
		} catch (BusinessException ex) {
			LOGGER.error(ex.getMessage(), ex);
			String estCode = isBP ? EstadisticasConstants.VER_RESUMENES_INV_BP
					: EstadisticasConstants.VER_RESUMENES_INV;
			estadisticaManager.add(estCode, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return respuestaFactory.crearRespuestaError(null, TipoError.CODIGO_ERROR_LISTA_RESUMENES_ERROR,
					CodigoMensajeConstantes.ERROR_LISTA_RESUMENES_INV);
		}
	}

	/**
	 * Obtener cuenta.
	 *
	 * @param nroCuenta
	 *     the nro cuenta
	 * @param isBP
	 *     the is BP
	 * @return the cuenta
	 */
	private AbstractCuenta obtenerCuenta(String nroCuenta, boolean isBP) {
		Cliente cliente = sesionCliente.getCliente();
		if (!isBP) {
			for (Cuenta cuenta : cliente.getCuentasRetail()) {
				if (ISBANStringUtils.formatearNroCuentaProductoConSucursal(cuenta).equals(nroCuenta)) {
					return cuenta;
				}
			}
		} else {
			for (CuentaBancaPrivada cuenta : cliente.getCuentasBancaPrivada()) {
				if (ISBANStringUtils.formatearNroCuentaProductoConSucursal(cuenta.getCuentaOperativa()).equals(nroCuenta)) {
					return cuenta.getCuentaTitulo();
				}
			}
		}
		return null;
	}

	/**
	 * Armar respuesta resumenes mensuales view.
	 *
	 * @param respuestaResumenesList
	 *     the respuesta resumenes list
	 * @param cuenta
	 *     the cuenta
	 * @param isBP
	 *     the is BP
	 * @return the respuesta
	 */
	private Respuesta<ResumenesMensualesInversionesView> armarRespuestaResumenesView(
			Respuesta<List<ResumenMensualInversiones>> respuestaResumenesList, AbstractCuenta cuenta, boolean isBP) {
		List<ResumenMensualInversionesView> resumenViewList = new ArrayList<ResumenMensualInversionesView>();
		Respuesta<ResumenesMensualesInversionesView> respuestaView = new Respuesta<ResumenesMensualesInversionesView>();
		String estCode = isBP ? EstadisticasConstants.VER_RESUMENES_INV_BP : EstadisticasConstants.VER_RESUMENES_INV;
		if (EstadoRespuesta.OK.equals(respuestaResumenesList.getEstadoRespuesta())) {
			List<ResumenMensualInversiones> resumenesList = respuestaResumenesList.getRespuesta();
			for (ResumenMensualInversiones resumen : resumenesList) {
				ResumenMensualInversionesView resumenView = new ResumenMensualInversionesView();
				resumenView.setPeriodo(resumen.getPeriodo());
				resumenView.setVisto(resumen.getVisto());
				resumenView.setId(resumen.getId());
				resumenViewList.add(resumenView);
			}
			ResumenesMensualesInversionesView resumenesView = new ResumenesMensualesInversionesView();
			String nroCuenta = ISBANStringUtils.sacarCerosYBlancosIzq(cuenta.getNroCuentaProducto());
			resumenesView.setNumeroCuenta(nroCuenta);
			resumenesView.setResumenes(resumenViewList);
			respuestaView.setEstadoRespuesta(EstadoRespuesta.OK);
			respuestaView.setRespuesta(resumenesView);
			respuestaView.setRespuestaVacia(false);
			estadisticaManager.add(estCode, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		} else if (EstadoRespuesta.WARNING.equals(respuestaResumenesList.getEstadoRespuesta())) {
			respuestaView.setEstadoRespuesta(EstadoRespuesta.WARNING);
			respuestaView.setRespuestaVacia(true);
			respuestaView.setItemMensajeRespuesta(respuestaResumenesList.getItemsMensajeRespuesta());
			estadisticaManager.add(estCode, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		} else {
			respuestaView.setEstadoRespuesta(EstadoRespuesta.ERROR);
			respuestaView.setRespuestaVacia(true);
			respuestaView.setItemMensajeRespuesta(respuestaResumenesList.getItemsMensajeRespuesta());
			estadisticaManager.add(estCode, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		}
		return respuestaView;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.resumen.web.manager.
	 * ResumenInversionesManager#obtenerResumenesPDF(ar.com.santanderrio.obp.
	 * servicios.inversiones.resumen.web.view.ResumenInversionesView)
	 */
	@Override
	public Respuesta<ReporteView> obtenerResumenesPDF(ResumenInversionesView view) {
		Respuesta<ReporteView> respuestaView = null;
		AbstractCuenta cuenta = obtenerCuenta(view.getNumeroCuenta(), view.getBancaPrivada());
		List<ResumenMensualInversiones> resumenesSeleccionados = sesionResumenInversiones
				.getResumenesByIds(Arrays.asList(view.getId()), cuenta);
		Respuesta<ReporteResumenMensualInversiones> reporteMensual = resumenMensualInversionesBO
				.obtenerResumenesPDF(resumenesSeleccionados, cuenta, view.getBancaPrivada());
		respuestaView = Respuesta.copy(ReporteView.class, reporteMensual);
		if (reporteMensual.getRespuesta() != null) {
			ReporteView resumenesMensualesView = ReporteView.fromReporte(reporteMensual.getRespuesta());
			respuestaView.setRespuesta(resumenesMensualesView);
		}
		String estCode = view.getBancaPrivada() ? EstadisticasConstants.VER_RESUMENES_DESCARGA_SIMPLE_INV_BP
				: EstadisticasConstants.VER_RESUMENES_DESCARGA_SIMPLE_INV;
		if (EstadoRespuesta.OK.equals(respuestaView.getEstadoRespuesta())
				|| EstadoRespuesta.WARNING.equals(respuestaView.getEstadoRespuesta())) {
			sesionResumenInversiones.marcarVistos(resumenesSeleccionados, cuenta);
			estadisticaManager.add(estCode, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		} else {
			estadisticaManager.add(estCode, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		}
		return respuestaView;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.resumen.web.manager.
	 * ResumenInversionesManager#obtenerResumenDescargaMultiple(java.util.List,
	 * java.lang.String, boolean, int)
	 */
	@Override
	public Respuesta<ReporteView> obtenerResumenDescargaMultiple(List<String> ids, String nroCuenta, boolean isBP,
			int cantidadADescargar) {
		Respuesta<ReporteView> respuestaView = null;
		String estCode = isBP ? EstadisticasConstants.VER_RESUMENES_DESCARGA_MULTIPLE_INV_BP
				: EstadisticasConstants.VER_RESUMENES_DESCARGA_MULTIPLE_INV;
		try {
			AbstractCuenta cuenta = obtenerCuenta(nroCuenta, isBP);
			List<ResumenMensualInversiones> resumenesSeleccionados = sesionResumenInversiones.getResumenesByIds(ids,
					cuenta);
			Respuesta<ReporteResumenMensualInversiones> reporteMensual = resumenMensualInversionesBO
					.obtenerResumenDescargaMultiple(resumenesSeleccionados, cuenta, isBP, cantidadADescargar);
			respuestaView = Respuesta.copy(ReporteView.class, reporteMensual);
			if (reporteMensual.getRespuesta() != null) {
				ReporteView resumenesMensualesView = ReporteView.fromReporte(reporteMensual.getRespuesta());
				respuestaView.setRespuesta(resumenesMensualesView);
			}
			if (EstadoRespuesta.OK.equals(respuestaView.getEstadoRespuesta())) {
				sesionResumenInversiones.marcarVistos(resumenesSeleccionados, cuenta);
				estadisticaManager.add(estCode, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			} else {
				estadisticaManager.add(estCode, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			}
		} catch (BusinessException ex) {
			LOGGER.error(ex.getMessage(), ex);
			estadisticaManager.add(estCode, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		}
		return respuestaView;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.resumen.web.manager.
	 * ResumenInversionesManager#obtenerCuentas()
	 */
	@Override
	public Respuesta<ResumenesInversionesView> obtenerCuentas() {
		Cliente cliente = sesionCliente.getCliente();
		List<String> cuentasBP = new ArrayList<String>();
		List<String> cuentasRTL = new ArrayList<String>();
		for (Cuenta cuenta : cliente.getCuentasRetail()) {
			String nroCta = ISBANStringUtils.formatearNroCuentaProductoConSucursal(cuenta);
			cuentasRTL.add(nroCta);
		}
		for (CuentaBancaPrivada cuenta : cliente.getCuentasBancaPrivada()) {			
			String nroCta = ISBANStringUtils.formatearNroCuentaProductoConSucursal(cuenta.getCuentaOperativa());
			cuentasBP.add(nroCta);
		}
		ResumenesInversionesView resumenesView = new ResumenesInversionesView();
		resumenesView.setCuentasBP(cuentasBP);
		resumenesView.setCuentasRTL(cuentasRTL);
		return respuestaFactory.crearRespuestaOk(resumenesView);
	}

	@Override
	public Respuesta<ReporteFinancieroView> obtenerResumenFinancieroTenenciasPDF(TenenciasInView view) {
		Respuesta<ReporteFinancieroView> respuestaView = null;
		String tipoConsulta = view.getTipo() != null ? view.getTipo() : "";
		boolean ConsultaBP = false;
		if (tipoConsulta.equals("BP")) {
			ConsultaBP = true;	
		}
		String anio = view.getAnio().substring(2, 4);
		String periodoAnual = "ANUAL_" + view.getAnio(); 
		String sucResumenFinanciacion = "000";
		String fechaDesde = "01/01/"+anio;
		String fechahasta = "31/12/"+anio;
		
		//TenenciasResumenFinancieroView resumenFinView = new TenenciasResumenFinancieroView();
		ResumenFinancieroDTO resumenFinDTO = new ResumenFinancieroDTO();
		resumenFinDTO.setNup(sesionCliente.getCliente().getNup());
		resumenFinDTO.setBancaPrivada(ConsultaBP);
		resumenFinDTO.setPeriodo(periodoAnual);
		resumenFinDTO.setFechaDesde(fechaDesde);
		resumenFinDTO.setFechaHasta(fechahasta);
		resumenFinDTO.setSucursal(sucResumenFinanciacion);
		
		Respuesta<ReporteResumenFinanciacion> resumenFiananciero = resumenMensualInversionesBO.obtenerResumenFinancieroTenenciasPDF(resumenFinDTO);
		respuestaView = Respuesta.copy(ReporteFinancieroView.class, resumenFiananciero);
		if (resumenFiananciero.getRespuesta() != null) {
			ReporteFinancieroView resumenFinancieroView = ReporteFinancieroView.fromReporte(resumenFiananciero.getRespuesta());
			respuestaView.setRespuesta(resumenFinancieroView);
		}
		String estCode = sesionCliente.getCliente().getClienteBancaPrivada() ? EstadisticasConstants.DESCARGA_RESUMEN_FINANCIERO_BP
				: EstadisticasConstants.DESCARGA_RESUMEN_FINANCIERO;
		if (EstadoRespuesta.OK.equals(respuestaView.getEstadoRespuesta())
				|| EstadoRespuesta.WARNING.equals(respuestaView.getEstadoRespuesta())) {
//			sesionResumenInversiones.marcarVistos(resumenesSeleccionados, cuenta);
			estadisticaManager.add(estCode, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		} else {
			estadisticaManager.add(estCode, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		}
		return respuestaView;
	}
}
