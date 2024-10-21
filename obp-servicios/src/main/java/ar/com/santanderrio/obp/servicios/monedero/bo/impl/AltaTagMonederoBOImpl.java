/*
 * 
 */
package ar.com.santanderrio.obp.servicios.monedero.bo.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.compraventa.dao.impl.OperacionFueraHorarioSucursalException;
import ar.com.santanderrio.obp.servicios.comun.ResultadoTransaccion;
import ar.com.santanderrio.obp.servicios.comun.dao.ConsultaInhabilitadosDAO;
import ar.com.santanderrio.obp.servicios.comun.dao.entities.ConsultaInhabilitadosInEntity;
import ar.com.santanderrio.obp.servicios.comun.dao.entities.ConsultaInhabilitadosOutEntity;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.monedero.bo.AltaTagMonederoBO;
import ar.com.santanderrio.obp.servicios.monedero.dao.AltaTagMonederoDAO;
import ar.com.santanderrio.obp.servicios.monedero.dao.ConsultaUnidadControlDAO;
import ar.com.santanderrio.obp.servicios.monedero.dao.MonederoActivacionDAO;
import ar.com.santanderrio.obp.servicios.monedero.entities.ConsultaUnidadControlInEntity;
import ar.com.santanderrio.obp.servicios.monedero.entities.ConsultaUnidadControlOutEntity;
import ar.com.santanderrio.obp.servicios.monedero.entities.DatosAltaTagMonederoEntity;
import ar.com.santanderrio.obp.servicios.monedero.entities.MonederoActivacionInEntities;
import ar.com.santanderrio.obp.servicios.monedero.exception.ErrorAltaSolicitudException;
import ar.com.santanderrio.obp.servicios.monedero.web.view.ComprobanteActivacionTagMonederoView;
import ar.com.santanderrio.obp.servicios.monedero.web.view.ComprobanteAltaTagMonederoView;
import ar.com.santanderrio.obp.servicios.monedero.web.view.DatosParaActivacionView;

/**
 * The Class AltaTagMonederoBOImpl.
 */
@Component
public class AltaTagMonederoBOImpl implements AltaTagMonederoBO {

	/** The alta tag monedero DAO. */
	@Autowired
	private AltaTagMonederoDAO altaTagMonederoDAO;

	/** The consulta unidad control DAO. */
	@Autowired
	private ConsultaUnidadControlDAO consultaUnidadControlDAO;

	/** The consulta inhabilitados DAO. */
	@Autowired
	private ConsultaInhabilitadosDAO consultaInhabilitadosDAO;

	/** The monedero activacion DAO. */
	@Autowired
	private MonederoActivacionDAO monederoActivacionDAO;

	/** The cuenta BO. */
	@Autowired
	private CuentaBO cuentaBO;

	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/** The estadistica manager. */
	@Autowired
	private EstadisticaManager estadisticaManager;

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(AltaTagMonederoBOImpl.class);

	/** The Constant ERROR_ALTA_TAG_MONEDERO. */
	private static final String ERROR_ALTA_TAG_MONEDERO = "ERROR al dar de alta tag monedero";

	/** The Constant ERROR_ALTA_TAG_MONEDERO_TAG. */
	public static final String ERROR_ALTA_TAG_MONEDERO_TAG = "sinDatos";

	/**
	 * Ejecutar alta tag monedero.
	 *
	 * @param datosAltaTagMonedero
	 *            the datos alta tag monedero
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 */

	@Override
	public Respuesta<ComprobanteAltaTagMonederoView> ejecutarAltaTagMonedero(
			DatosAltaTagMonederoEntity datosAltaTagMonedero, Cliente cliente) {
		ComprobanteAltaTagMonederoView comprobanteAltaTagMonederoView = new ComprobanteAltaTagMonederoView();
		try {
			LOGGER.debug("Llamando al DAO para la realizar la operacion getDatosDelSolicitante: {}.",
					datosAltaTagMonedero);
			comprobanteAltaTagMonederoView = altaTagMonederoDAO.ejecutarAltaTagMonedero(datosAltaTagMonedero, cliente);
			estadisticaManager.add(EstadisticasConstants.MONEDERO_ALTA_EXITOSA_MONEDERO_TITULAR,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		} catch (BusinessException e) {
			LOGGER.error(ERROR_ALTA_TAG_MONEDERO, e);

			if (e.getCause() instanceof DAOException) {
				return getRespuestaFactory().crearRespuestaError("", TipoError.ERROR_SOLICITAR_TAG_TITULAR,
						CodigoMensajeConstantes.MENSAJE_ERROR_GENERICO_ALTA_TAG_MONEDERO);

			} else if (e.getCause() instanceof OperacionFueraHorarioSucursalException) {
				return getRespuestaFactory().crearRespuestaError("", TipoError.ERROR_SOLICITAR_TAG_TITULAR,
						CodigoMensajeConstantes.CODIGO_ERROR_HORARIO_DE_OPERACIONES);
						
			} else if (e.getCause() instanceof ErrorAltaSolicitudException) {
				estadisticaManager.add(EstadisticasConstants.ALTA_ERROR_MONEDERO_TITULAR,
						EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
				return getRespuestaFactory().crearRespuestaError("", TipoError.ERROR_SOLICITUD_MONEDERO_DUPLICADO,
						CodigoMensajeConstantes.MENSAJE_ERROR_SOLICITUD_MONEDERO_DUPLICADO);
			}else {
				return getRespuestaFactory().crearRespuestaError("", TipoError.ERROR_GENERICO,
						CodigoMensajeConstantes.MENSAJE_ERROR_GENERICO_ACTIVACION_TAG_MONEDERO);
			}
		}
		return getRespuestaFactory().crearRespuestaOk(ComprobanteAltaTagMonederoView.class,
				comprobanteAltaTagMonederoView);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.monedero.bo.AltaTagMonederoBO#
	 * generarComprobanteAltaTagMonedero(ar.com.santanderrio.obp.servicios.
	 * monedero.web.view.ComprobanteAltaTagMonederoView)
	 */
	@Override
	public Respuesta<Reporte> generarComprobanteAltaTagMonedero(
			ComprobanteAltaTagMonederoView comprobanteAltaTagMonederoView) {
		Respuesta<Reporte> respuesta = new Respuesta<Reporte>();
		Reporte reporte = altaTagMonederoDAO.generarComprobanteAltaTagMonedero(comprobanteAltaTagMonederoView);
		respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
		respuesta.setRespuesta(reporte);
		return respuesta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.monedero.bo.AltaTagMonederoBO#
	 * obtenerDatosSucursal(ar.com.santanderrio.obp.servicios.monedero.entities.
	 * ConsultaUnidadControlInEntity)
	 */
	@Override
	public ConsultaUnidadControlOutEntity obtenerDatosSucursal(
			ConsultaUnidadControlInEntity consultaUnidadControlInEntity) {
		ConsultaUnidadControlOutEntity consultaUnidadControlOutEntity = new ConsultaUnidadControlOutEntity();
		try {
			consultaUnidadControlOutEntity = consultaUnidadControlDAO.consultaUC(consultaUnidadControlInEntity);
		} catch (DAOException e) {
			LOGGER.error("obtenerDatosSucursal", e);
		}
		return consultaUnidadControlOutEntity;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.monedero.bo.AltaTagMonederoBO#
	 * esPersonaHabilitada(ar.com.santanderrio.obp.servicios.monedero.entities.
	 * ConsultaInhabilitadosInEntity)
	 */
	@Override
	public ConsultaInhabilitadosOutEntity esPersonaHabilitada(ConsultaInhabilitadosInEntity entity) {
		ConsultaInhabilitadosOutEntity consultaInhabilitadosOutEntity = new ConsultaInhabilitadosOutEntity();
		try {
			consultaInhabilitadosOutEntity = consultaInhabilitadosDAO.consultaInhabilitados(entity);
		} catch (DAOException e) {
			LOGGER.error("esPersonaHabilitada", e);
		}

		return consultaInhabilitadosOutEntity;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.monedero.bo.AltaTagMonederoBO#
	 * activarMonederoTag(ar.com.santanderrio.obp.servicios.monedero.web.view.
	 * DatosParaActivacionView,
	 * ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
	 */
	@Override
	public Respuesta<ResultadoTransaccion> activarMonederoTag(DatosParaActivacionView datosParaActivacionView,
			Cliente cliente) {

		Respuesta<ResultadoTransaccion> monederoActivacionOutEntities = new Respuesta<ResultadoTransaccion>();
		MonederoActivacionInEntities monederoActivacionInEntity = new MonederoActivacionInEntities();

		monederoActivacionInEntity.setCliente(cliente);
		monederoActivacionInEntity.setCantTarjetaTagPorActivar("001");
		Cuenta cuenta = cuentaBO.obtenerCuentaPorId(cliente, datosParaActivacionView.getIdCuentaSeleccionada());
		monederoActivacionInEntity.setSucursal(cuenta.getNroSucursal());
		monederoActivacionInEntity.setNumTarjetaPorActivar(cuenta.getNroTarjetaCredito().trim());
		try {
			monederoActivacionOutEntities = monederoActivacionDAO.activar(monederoActivacionInEntity);

		} catch (DAOException e) {
			LOGGER.error("activarMonederoTag", e);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_ACTIVAR_TAG,
					CodigoMensajeConstantes.MENSAJE_ERROR_ACTIVAR_TAG);
		}

		return monederoActivacionOutEntities;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.monedero.bo.AltaTagMonederoBO#
	 * generarComprobanteActivacionTagMonedero(ar.com.santanderrio.obp.servicios
	 * .monedero.web.view.ComprobanteActivacionTagMonederoView)
	 */
	@Override
	public Respuesta<Reporte> generarComprobanteActivacionTagMonedero(
			ComprobanteActivacionTagMonederoView comprobanteActivacionTagMonederoView) {
		Respuesta<Reporte> respuesta = new Respuesta<Reporte>();
		Reporte reporte = altaTagMonederoDAO
				.generarComprobanteActivacionTagMonedero(comprobanteActivacionTagMonederoView);
		respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
		respuesta.setRespuesta(reporte);
		return respuesta;
	}

	/**
	 * Gets the respuesta factory.
	 *
	 * @return the respuesta factory
	 */
	public RespuestaFactory getRespuestaFactory() {
		return respuestaFactory;
	}

}
