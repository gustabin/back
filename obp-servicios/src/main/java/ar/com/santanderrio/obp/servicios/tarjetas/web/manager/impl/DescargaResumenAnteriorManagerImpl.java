/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.web.manager.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionTarjetas;
import ar.com.santanderrio.obp.servicios.cuentas.bo.ResumenMensualCuentaBO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.resumen.entities.ReporteResumenPuntual;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ReporteSeleccionado;
import ar.com.santanderrio.obp.servicios.tarjetas.web.manager.DescargaResumenAnteriorManager;

/**
 * The Class DescargaResumenAnteriorManagerImpl.
 */
@Component
public class DescargaResumenAnteriorManagerImpl implements DescargaResumenAnteriorManager {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(DescargaResumenAnteriorManagerImpl.class);

	/** The sesion tarjetas. */
	@Autowired
	private SesionTarjetas sesionTarjetas;

	/** The resumen mensual cuenta BO impl. */
	@Autowired
	private ResumenMensualCuentaBO resumenMensualCuentaBOImpl;

	/** The sesion cliente. */
	@Autowired
	private SesionCliente sesionCliente;

	/** The sesion cliente. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/** The sesion cliente. */
	@Autowired
	private EstadisticaManager estadisticaManager;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.tarjetas.web.manager.
	 * DescargaResumenAnteriorManager#
	 * exportarOnDemandPuntualResumenConEstadistica(java.lang.String,
	 * java.lang.Integer, java.lang.String)
	 */
	@Override
	public Respuesta<ReporteView> exportarOnDemandPuntualResumenConEstadistica(String nroCuenta, Integer list,
			String string) {
		Respuesta<ReporteView> res = exportarOnDemandPuntualResumen(nroCuenta, list, string);
		grabarEstadisticaSegunCantidad(res.getEstadoRespuesta(), 1);
		return res;
	}

	/**
	 * Grabar estadistica segun cantidad.
	 *
	 * @param estadoRespuesta
	 *            the estado respuesta
	 * @param i
	 *            the i
	 */
	private void grabarEstadisticaSegunCantidad(EstadoRespuesta estadoRespuesta, int i) {
		String estadistica = estadisticaPorCantItems(i);
		if (estadoRespuesta.equals(EstadoRespuesta.OK)) {
			estadisticaManager.add(estadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		}
		if (estadoRespuesta.equals(EstadoRespuesta.WARNING)) {
			estadisticaManager.add(estadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR_PARCIAL);
		}

		if (estadoRespuesta.equals(EstadoRespuesta.ERROR)) {
			estadisticaManager.add(estadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		}

	}

	/**
	 * Exportar on demand puntual resumen.
	 *
	 * @param nroCuenta
	 *            the nro cuenta
	 * @param list
	 *            the list
	 * @param nombre
	 *            the nombre
	 * @return the respuesta
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.tarjetas.web.manager.
	 * DescargaResumenAnteriorManager#exportarOnDemandPuntualResumen(java.lang.
	 * String)
	 */
	public Respuesta<ReporteView> exportarOnDemandPuntualResumen(String nroCuenta, Integer list, String nombre) {
		Respuesta<ReporteView> respuestaView = new Respuesta<ReporteView>();
		if (sesionTarjetas.getListaVistos() == null) {
			sesionTarjetas.setListaVistos(new HashMap<String, Set<Integer>>());
		}
		if (!sesionTarjetas.getListaVistos().containsKey(nroCuenta)) {
			sesionTarjetas.getListaVistos().put(nroCuenta, new HashSet<Integer>());
		}
		try {
			List<ReporteSeleccionado> resumenesSeleccionados = sesionTarjetas.getReportesSelccionados();
			if (resumenesSeleccionados != null && !resumenesSeleccionados.isEmpty()) {
				Cliente cliente = sesionCliente.getCliente();
				LOGGER.info("Cliente en Sesion {}.", cliente.toString());
				IdentificacionCuenta idCuenta = new IdentificacionCuenta(nroCuenta);
				Respuesta<ReporteResumenPuntual> reportePuntual = resumenMensualCuentaBOImpl
						.obtenerResumenPuntualPDF(resumenesSeleccionados, idCuenta, cliente, list);
				respuestaView = Respuesta.copy(ReporteView.class, reportePuntual);
				respuestaView.setRespuesta(ReporteView.fromReporte(reportePuntual.getRespuesta()));
				if (respuestaView.getEstadoRespuesta().equals(EstadoRespuesta.OK)
						&& !sesionTarjetas.getListaVistos().get(nroCuenta).contains(list)) {
					sesionTarjetas.getListaVistos().get(nroCuenta).add(list);
				}
				return respuestaView;
			}
			return armarRespuestaError();
		} catch (BusinessException bex) {
			LOGGER.error("Error al exportar pdf en la tarjeta {}.", nroCuenta, bex);
			return armarRespuestaError();
		}
	}

	/**
	 * Armar respuesta error.
	 *
	 * @return the respuesta
	 */
	private Respuesta<ReporteView> armarRespuestaError() {
		return respuestaFactory.crearRespuestaError("", TipoError.ERROR_REINTENTOS_AGOTADOS,
				CodigoMensajeConstantes.CODIGO_ERROR_ERROR_DESCARGA_RESUMEN);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.tarjetas.web.manager.
	 * DescargaResumenAnteriorManager#exportarOnDemandMultipleResumen(java.lang.
	 * String, java.lang.Integer, int, java.lang.String)
	 */
	@Override
	public Respuesta<ReporteView> exportarOnDemandMultipleResumen(String numeroCuenta, Integer fechas, int cantLlamadas,
			String nombre) {
		Respuesta<ReporteView> resPuntualByID = exportarOnDemandPuntualResumen(numeroCuenta, fechas, nombre);
		if (sesionTarjetas.getContadorParaEstadistica() == 0) {
			sesionTarjetas.setContadorParaEstadistica(cantLlamadas);
		}
		if (resPuntualByID.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
			if (sesionTarjetas.getContadorParaEstadistica() == 1) {
				estadisticaManager.add(estadisticaPorCantItems(cantLlamadas),
						EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			}
			sesionTarjetas.setContadorParaEstadistica(sesionTarjetas.getContadorParaEstadistica() - 1);
			return resPuntualByID;
		}
		sesionTarjetas.setCantidadErrores(sesionTarjetas.getCantidadErrores() + 1);
		if (sesionTarjetas.getContadorParaEstadistica() == 1) {
			estadisticaManager.add(estadisticaPorCantItems(cantLlamadas),
					errorPorCantidadErrores(sesionTarjetas.getCantidadErrores(), cantLlamadas));
			sesionTarjetas.setCantidadErrores(0);
		}
		sesionTarjetas.setContadorParaEstadistica(sesionTarjetas.getContadorParaEstadistica() - 1);
		resPuntualByID.getItemsMensajeRespuesta().add(0,
				resumenMensualCuentaBOImpl.obtenerMensajeErrorPorCantidadFallidos(cantLlamadas));
		return resPuntualByID;
	}

	/**
	 * Error por cantidad errores.
	 *
	 * @param cantidadErrores
	 *            the cantidad errores
	 * @param cantLlamadas
	 *            the cant llamadas
	 * @return the string
	 */
	private String errorPorCantidadErrores(int cantidadErrores, int cantLlamadas) {
		if (cantidadErrores == cantLlamadas) {
			return EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR;
		}
		return EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR_PARCIAL;
	}

	/**
	 * Estadistica por cant items.
	 *
	 * @param size
	 *            the size
	 * @return the string
	 */
	private String estadisticaPorCantItems(int size) {
		if (size < 2) {
			return EstadisticasConstants.CODIGO_DESCARGA_INDIVIDUAL_RESUMEN;
		}
		return EstadisticasConstants.CODIGO_DESCARGA_MULTIPLE_RESUMEN;
	}

}
