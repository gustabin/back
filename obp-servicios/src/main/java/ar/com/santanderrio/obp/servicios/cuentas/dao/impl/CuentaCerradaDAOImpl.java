/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.dao.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequest;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequestData;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.cuentas.dao.CuentaCerradaDAO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.CuentaCerrada;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxComm;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxResponse;

/**
 * The Class CuentaCerradaDAOImpl.
 */
@Component
public class CuentaCerradaDAOImpl implements CuentaCerradaDAO {

	/** The iatx comm. */
	@Autowired
	private IatxComm iatxComm;

	/** The Constant HOUR_DAY. */
	// Milisegundos al dia
	private static final long HOUR_DAY = 24;

	/** The Constant MINUTES_HOUR. */
	private static final long MINUTES_HOUR = 60;

	/** The Constant SECONDS_MINUTE. */
	private static final long SECONDS_MINUTE = 60;

	/** The Constant MILISECONDS. */
	private static final long MILISECONDS = 1000;

	/** The Constant MILLSECS_PER_DAY. */
	private static final long MILLSECS_PER_DAY = HOUR_DAY * MINUTES_HOUR * SECONDS_MINUTE * MILISECONDS;

	/** The Constant CANT_DIAS_SIZE. */
	private static final int CANT_DIAS_SIZE = 4;
	
	/** The Constant VERSION_CNSCONTCAN. */
	private static final String VERSION_CNSCONTCAN = "120"; // Cierre de sucursales

	/** The servicio cnscontcan. */
	@Value("${SERVICIO.PREFIJO.CNSCONTCAN}")
	private String servicioCnscontcan;

	/** The version cnscontcan. */
	@Value("${SERVICIO.VERSION.100}")
	private String versionCnscontcan;

	/** The cantidad meses cuenta cerrada. */
	private String cantidadMesesCuentaCerrada = "18";

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.cuentas.dao.CuentaCerradaDAO#
	 * obtenerCuentasCerradas(ar.com.santanderrio.obp.clientes.entities.Cliente)
	 */
	@Override
	public Respuesta<List<CuentaCerrada>> obtenerCuentasCerradas(Cliente cliente) throws DAOException {
		Respuesta<List<CuentaCerrada>> respuestaCuentasCerradas = new Respuesta<List<CuentaCerrada>>();
		ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
		try {
			Date fechaHoy = new Date();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			calendar.add(Calendar.MONTH, Integer.parseInt(cantidadMesesCuentaCerrada));
			Date fechaFuturo = calendar.getTime();

			String cantDias = String.valueOf(((int) ((fechaFuturo.getTime() - fechaHoy.getTime()) / MILLSECS_PER_DAY)));
			// ID 4276 - Migracion de cuentas

			IatxRequestData requestData = new IatxRequestData(cliente);

			// NRO_NUP
			requestData.addBodyValue(cliente.getNup());
			// APLICATIVO (PTM - prestamos / MON - cuentas)
			requestData.addBodyValue("MON");
			// CANT_DIAS (hbconfig RESUMENONDEMAND.MESES)
			requestData.addBodyValue(StringUtils.leftPad(cantDias, CANT_DIAS_SIZE, "0"));
			// MOT_CAN (To - Todos / CS - canal 04)
			requestData.addBodyValue("CS");

			IatxRequest request = new IatxRequest(servicioCnscontcan, VERSION_CNSCONTCAN);
			request.setData(requestData);

			IatxResponse iatxResponse = iatxComm.exec(request);

			int cantCtas = Integer.parseInt(iatxResponse.getNextData());
			List<CuentaCerrada> cuentasCerradas = new ArrayList<CuentaCerrada>(cantCtas);
			for (int i = 0; i < cantCtas; i++) {
				CuentaCerrada cuentaCerrada = buildCuentaCerrada(iatxResponse, cliente);
				cuentasCerradas.add(cuentaCerrada);
			}

			itemMensajeRespuesta.setMensaje(iatxResponse.getErrorMessage());
			if (iatxResponse.getTipoError() != null) {
				itemMensajeRespuesta.setTipoError(iatxResponse.getTipoError().getDescripcion());
			}

			respuestaCuentasCerradas.add(itemMensajeRespuesta);
			respuestaCuentasCerradas.setRespuesta(cuentasCerradas);
			respuestaCuentasCerradas.setEstadoRespuesta(iatxResponse.getEstadoRespuesta());

		} catch (IatxException e) {
			itemMensajeRespuesta.setMensaje(e.getMessage());
			itemMensajeRespuesta.setTipoError(TipoError.ERROR_GENERICO.getDescripcion());

			respuestaCuentasCerradas.add(itemMensajeRespuesta);
			respuestaCuentasCerradas.setEstadoRespuesta(EstadoRespuesta.ERROR);

		} catch (RuntimeException e) {
			throw new DAOException(e);
		}
		return respuestaCuentasCerradas;
	}

	/**
	 * Builds the cuenta cerrada.
	 *
	 * @param iatxResponse
	 *            the iatx response
	 * @param cliente
	 *            the cliente
	 * @return the cuenta cerrada
	 */
	private CuentaCerrada buildCuentaCerrada(IatxResponse iatxResponse, Cliente cliente) {
		CuentaCerrada cuentaCerrada = new CuentaCerrada();

		cuentaCerrada.setCliente(cliente);

		cuentaCerrada.setNroSucursal(iatxResponse.getNextData());
		cuentaCerrada.setNroCuentaProducto(iatxResponse.getNextData());
		cuentaCerrada.setProductoAltair(iatxResponse.getNextData());
		cuentaCerrada.setSubproductoAltair(iatxResponse.getNextData());
		cuentaCerrada.setCalidadDeParticipacion(iatxResponse.getNextData());
		cuentaCerrada.setOrdenDeParticipacion(iatxResponse.getNextData());
		cuentaCerrada.setFechaBajaContrato(iatxResponse.getNextData());
		cuentaCerrada.setEstadoRelacion(iatxResponse.getNextData());
		cuentaCerrada.setResponsabilidadEnIntervencion(iatxResponse.getNextData());
		cuentaCerrada.setMotivoBaja(iatxResponse.getNextData());
		cuentaCerrada.setTimestamp(iatxResponse.getNextData());
		cuentaCerrada.setJerarquiaCuenta(iatxResponse.getNextData());
		cuentaCerrada.setFechaAltaContrato(iatxResponse.getNextData());
		cuentaCerrada.setTipoCuenta(iatxResponse.getNextData());
		cuentaCerrada.setCbu(iatxResponse.getNextData());
		cuentaCerrada.setCodigoTitularidad(cuentaCerrada.getCalidadDeParticipacion().substring(0, 1));
		iatxResponse.getNextData();                              					// MORIA
		iatxResponse.getNextData();                              					// TIPO_PRESTAMO
		cuentaCerrada.setNroSucursalContinuadora(iatxResponse.getNextData());       // SUCURSAL CONTINUADORA
		cuentaCerrada.setNroCuentaProductoContinuador(iatxResponse.getNextData());  // CONTRATO CONTINUADOR
		return cuentaCerrada;
	}

}
