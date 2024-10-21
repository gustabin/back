/**
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.web.manager.impl;

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
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.tarjetas.bo.ResumenAnteriorBO;
import ar.com.santanderrio.obp.servicios.tarjetas.view.ResumenAnteriorViewResponse;
import ar.com.santanderrio.obp.servicios.tarjetas.view.TarjetaActivaView;
import ar.com.santanderrio.obp.servicios.tarjetas.web.manager.ResumenAnteriorManager;
import ar.com.santanderrio.obp.servicios.tarjetas.web.util.EstadisticasTarjetasUtil;

// TODO: Auto-generated Javadoc
/**
 * The Class ResumenAnteriorManagerImpl.
 *
 * @author sabrina.cis
 */
@Component("resumenAnteriorManager")
public class ResumenAnteriorManagerImpl implements ResumenAnteriorManager {
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ResumenAnteriorManagerImpl.class);

	/** The Constant SERVICIO_DEVOLVIO_LOG_LABEL. */
	public static final String SERVICIO_DEVOLVIO_LOG_LABEL = "El servicio devolvio: ";

	/** The Constant SERVICIO_DEVOLVIO_LOG_LABEL. */
	public static final String ERROR_ESTADISTICA = "Error al grabar estadistica de Resumenes Anteriores ";

	/** The resumen anterior BO. */
	@Autowired
	private ResumenAnteriorBO resumenAnteriorBO;

	/** The sesion cliente. */
	@Autowired
	private SesionCliente sesionCliente;

	/** The estadistica manager. */
	@Autowired
	private EstadisticaManager estadisticaManager;

	/** The respuestaFactory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/**
	 * Obtener resumenes anteriores.
	 *
	 * @param tarjetaActiva
	 *            the tarjeta activa
	 * @return the respuesta
	 */
	@Override
	public Respuesta<ResumenAnteriorViewResponse> obtenerResumenesAnteriores(TarjetaActivaView tarjetaActiva) {
		try {
			Cliente cliente = sesionCliente.getCliente();
			LOGGER.info("Cliente en Sesion {}.", cliente.toString());
			IdentificacionCuenta idCuenta = new IdentificacionCuenta(tarjetaActiva.getTarjetaActiva());
			return obtenerRespuestaBO(idCuenta, cliente, tarjetaActiva.getId(), tarjetaActiva.getTarjetaActiva());
		} catch (Exception e) {
			LOGGER.info(SERVICIO_DEVOLVIO_LOG_LABEL, e);
			return crearRespuestaError();
		}
	}

	/**
	 * Obtiene la respuesta de BO. En caso de error retorna una respuesta con error
	 *
	 * @param idCuenta
	 *            the id cuenta
	 * @param cliente
	 *            the cliente
	 * @param idInteger
	 *            the id integer
	 * @param tarjetaActiva
	 *            the tarjeta activa
	 * @return the respuesta
	 */
	private Respuesta<ResumenAnteriorViewResponse> obtenerRespuestaBO(IdentificacionCuenta idCuenta, Cliente cliente,
			Integer idInteger, String tarjetaActiva) {
		try {
			Respuesta<ResumenAnteriorViewResponse> respuesta = resumenAnteriorBO.obtenerResumenesAnteriores(idCuenta,
					cliente, tarjetaActiva);
			if (esEstadoRespuestaOK(respuesta) || esEstadoRespuestaWarning(respuesta)) {
				IdentificacionCuenta idCuenta2 = new IdentificacionCuenta();
				idCuenta2.setNroCuentaProducto(respuesta.getRespuesta().getNumeroCuenta().toString());
				idCuenta2.setNroSucursal(idCuenta.getNroSucursal());

				crearEstadisticaOKWarning(idCuenta2, cliente, respuesta.getEstadoRespuesta());
				return respuesta;
			}
			crearRespuestaError(idCuenta, cliente);
			return respuesta;
		} catch (BusinessException e) {
			LOGGER.info(SERVICIO_DEVOLVIO_LOG_LABEL, e);
			return crearRespuestaError(idCuenta, cliente);
		}
	}

	/**
	 * Es estado respuesta OK.
	 *
	 * @param respuesta
	 *            the respuesta
	 * @return the boolean
	 */
	private Boolean esEstadoRespuestaOK(Respuesta<ResumenAnteriorViewResponse> respuesta) {
		return respuesta.getEstadoRespuesta().equals(EstadoRespuesta.OK);
	}

	/**
	 * Es estado respuesta warning.
	 *
	 * @param respuesta
	 *            the respuesta
	 * @return the boolean
	 */
	private Boolean esEstadoRespuestaWarning(Respuesta<ResumenAnteriorViewResponse> respuesta) {
		return respuesta.getEstadoRespuesta().equals(EstadoRespuesta.WARNING);
	}

	/**
	 * Respuesta ERROR TipoError.ERROR_CARGA_CUOTAS_PENDIENTE. (110005)
	 *
	 * @param idCuenta
	 *            the id cuenta
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 */
	private Respuesta<ResumenAnteriorViewResponse> crearRespuestaError(IdentificacionCuenta idCuenta, Cliente cliente) {
		crearEstadisticaERROR(idCuenta, cliente);
		return crearRespuestaError();
	}

	/**
	 * Respuesta ERROR TIPO_ERROR_CARGA_RESUMENES_ANTERIORES. (1157)
	 *
	 * @return the respuesta
	 */
	public Respuesta<ResumenAnteriorViewResponse> crearRespuestaError() {
		return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_GENERICO,
				CodigoMensajeConstantes.CODIGO_ERROR_CARGA_RESUMENES_ANTERIORES);
	}

	/**
	 * Crear estadistica.
	 *
	 * @param idCuenta
	 *            the id cuenta
	 * @param cliente
	 *            the cliente
	 */
	private void crearEstadisticaOKWarning(IdentificacionCuenta idCuenta, Cliente cliente,
			EstadoRespuesta estadoRespuesta) {
		try {
			String marca = resumenAnteriorBO.obtenerMarcaDeTarjeta(idCuenta, cliente);
			estadisticaManager.add(EstadisticasTarjetasUtil.getCodigoEstadisticaResumenesAnteriores(marca),
					EstadoRespuesta.OK.equals(estadoRespuesta) ? EstadisticasTarjetasUtil.getCodigoEstadisticaOk()
							: EstadisticasConstants.CODIGO_ESTADISTICAS_PARCIAL);
		} catch (BusinessException e) {
			LOGGER.info(ERROR_ESTADISTICA, e);
		}
	}

	/**
	 * Crear estadistica.
	 *
	 * @param idCuenta
	 *            the id cuenta
	 * @param cliente
	 *            the cliente
	 */
	private void crearEstadisticaERROR(IdentificacionCuenta idCuenta, Cliente cliente) {
		try {
			String marca = resumenAnteriorBO.obtenerMarcaDeTarjeta(idCuenta, cliente);
			estadisticaManager.add(EstadisticasTarjetasUtil.getCodigoEstadisticaResumenesAnteriores(marca),
					EstadisticasTarjetasUtil.getCodigoEstadisticaError());
		} catch (BusinessException e) {
			LOGGER.info(ERROR_ESTADISTICA, e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.tarjetas.web.manager.
	 * ResumenAnteriorManager#grabarEstadisticaDeResumenesAnteriores(ar.com.
	 * santanderrio.obp.base.respuesta.entities.EstadoRespuesta)
	 */
	@Override
	public void grabarEstadisticaDeResumenesAnteriores(EstadoRespuesta estadoRespuesta) {
		switch (estadoRespuesta) {
		case OK:
			estadisticaManager.add(EstadisticasConstants.CODIGO_ACCESO_RESUMENES_ANTERIORES,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			break;
		case WARNING:
			estadisticaManager.add(EstadisticasConstants.CODIGO_ACCESO_RESUMENES_ANTERIORES,
					EstadisticasConstants.CODIGO_ESTADISTICAS_WARNING);
			break;
		default:
			estadisticaManager.add(EstadisticasConstants.CODIGO_ACCESO_RESUMENES_ANTERIORES,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			break;
		}

	}

}
