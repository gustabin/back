/**
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.bo.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.excel.dao.ReporteDAO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.cuentas.bo.ChequesYValoresPendientesDeConfirmacionBO;
import ar.com.santanderrio.obp.servicios.cuentas.dao.ChequesYValoresCreditosPendientesDeConfirmacionDAO;
import ar.com.santanderrio.obp.servicios.cuentas.dao.ChequesYValoresDebitosPendientesDeConfirmacionDAO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.AbstractCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DetalleMovimientoChequesYValoresEntity;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;

/**
 * The Class ChequesYValoresPendientesDeConfirmacionBOImpl.
 *
 * @author B039542
 */
@Component
public class ChequesYValoresPendientesDeConfirmacionBOImpl implements ChequesYValoresPendientesDeConfirmacionBO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ChequesYValoresPendientesDeConfirmacionBOImpl.class);

	/** The valores debitos dao. */
	@Autowired
	private ChequesYValoresDebitosPendientesDeConfirmacionDAO valoresDebitosDAO;

	/** The valores creditos dao. */
	@Autowired
	private ChequesYValoresCreditosPendientesDeConfirmacionDAO valoresCreditosDAO;

	/** The reporte dao. */
	@Autowired
	private ReporteDAO reporteDAO;

	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.cuentas.bo.
	 * ChequesYValoresPendientesDeConfirmacionBO#
	 * obtenerCreditosPendientesDeConfirmacionPorCuenta(ar.com.santanderrio.obp.
	 * cuentas.entities.AbstractCuenta)
	 */
	@Override
	public Respuesta<List<DetalleMovimientoChequesYValoresEntity>> obtenerCreditosPendientesDeConfirmacionPorCuenta(
			AbstractCuenta cuenta) {
		Respuesta<List<DetalleMovimientoChequesYValoresEntity>> respuesta = null;
		try {
			List<DetalleMovimientoChequesYValoresEntity> creditosPendientesList = valoresCreditosDAO
					.obtenerCreditosPendientesDeConfirmacionPorCuenta(cuenta);
			respuesta = generarRespuestaOK(creditosPendientesList);
		} catch (DAOException e) {
			LOGGER.error(e.getMessage(), e);
			respuesta = generarRespuestaError(e.getMessage());
		} catch (RuntimeException e) {
			LOGGER.error(e.getMessage(), e);
			respuesta = generarRespuestaError(e.getMessage());
		}

		return respuesta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.cuentas.bo.
	 * ChequesYValoresPendientesDeConfirmacionBO#
	 * obtenerDebitosPendientesDeConfirmacionPorCuenta(ar.com.santanderrio.obp.
	 * cuentas.entities.AbstractCuenta)
	 */
	@Override
	public Respuesta<List<DetalleMovimientoChequesYValoresEntity>> obtenerDebitosPendientesDeConfirmacionPorCuenta(
			AbstractCuenta cuenta) {
		Respuesta<List<DetalleMovimientoChequesYValoresEntity>> respuesta = null;
		try {
			List<DetalleMovimientoChequesYValoresEntity> creditosPendientesList = valoresDebitosDAO
					.obtenerDebitosPendientesDeConfirmacionPorCuenta(cuenta);
			respuesta = generarRespuestaOK(creditosPendientesList);
		} catch (DAOException e) {
			LOGGER.error(e.getMessage(), e);
			respuesta = respuestaFactory.crearRespuestaError("", TipoError.ERROR_MOVIMIENTOS_VALORES_PENDIENTES,
	                CodigoMensajeConstantes.CODIGO_ERROR_MOVIMIENTOS_VALORES_PENDIENTES);
		}

		return respuesta;
	}

	/**
	 * Generar respuesta error.
	 *
	 * @param mensajeError
	 *            the mensaje error
	 * @return the respuesta
	 */
	private Respuesta<List<DetalleMovimientoChequesYValoresEntity>> generarRespuestaError(String mensajeError) {
		Respuesta<List<DetalleMovimientoChequesYValoresEntity>> respuesta = new Respuesta<List<DetalleMovimientoChequesYValoresEntity>>();
		respuesta.setRespuesta(null);
		respuesta.setRespuestaVacia(true);
		respuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);
		List<ItemMensajeRespuesta> mensajesList = new ArrayList<ItemMensajeRespuesta>();
		ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
		itemMensajeRespuesta.setTipoError(TipoError.ERROR_GENERICO.getDescripcion());
		itemMensajeRespuesta.setMensaje(mensajeError);
		mensajesList.add(itemMensajeRespuesta);
		respuesta.setItemMensajeRespuesta(mensajesList);
		return respuesta;
	}

	/**
	 * Generar respuesta ok.
	 *
	 * @param creditosPendientesList
	 *            the creditos pendientes list
	 * @return the respuesta
	 */
	private Respuesta<List<DetalleMovimientoChequesYValoresEntity>> generarRespuestaOK(
			List<DetalleMovimientoChequesYValoresEntity> creditosPendientesList) {
		Respuesta<List<DetalleMovimientoChequesYValoresEntity>> respuesta = new Respuesta<List<DetalleMovimientoChequesYValoresEntity>>();
		respuesta.setRespuesta(creditosPendientesList);
		respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
		respuesta.setRespuestaVacia(false);
		return respuesta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.cuentas.bo.
	 * ChequesYValoresPendientesDeConfirmacionBO#obtenerReporte(java.lang.
	 * Object, java.lang.String,
	 * ar.com.santanderrio.obp.clientes.entities.Cliente)
	 */
	@Override
	public Respuesta<Reporte> obtenerReporte(Object object, String proceso, Cliente cliente) {
		return reporteDAO.obtenerReporte(object, proceso, cliente,true);
	}
}
