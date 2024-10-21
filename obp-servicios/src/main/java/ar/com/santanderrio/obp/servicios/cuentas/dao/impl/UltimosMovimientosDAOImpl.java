/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequest;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequestData;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.config.ws.CacheConstants;
import ar.com.santanderrio.obp.servicios.cuentas.dao.UltimosMovimientosDAO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.ConsultaUltimosMovimientos;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Movimiento;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoConsultaMovimientos;
import ar.com.santanderrio.obp.servicios.cuentas.entities.UltimosMovimientosDTO;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxComm;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxResponse;

/**
 * The Class UltimosMovimientosDAOImpl.
 */
@Component
public class UltimosMovimientosDAOImpl implements UltimosMovimientosDAO {
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(UltimosMovimientosDAOImpl.class);

	/** The iatx comm. */
	@Autowired
	private IatxComm iatxComm;

	/** The Constant LONGITUD_TIPO_CUENTA. */
	private static final int LONGITUD_TIPO_CUENTA = 2;

	/** The Constant LONGITUD_NUMERO_SUCURSAL. */
	private static final int LONGITUD_NUMERO_SUCURSAL = 4;

	/** The Constant LONGITUD_NUMERO_CUENTA_PRODUCTO. */
	private static final int LONGITUD_NUMERO_CUENTA_PRODUCTO = 12;

	/** The Constant LONGITUD_CANTIDAD_DE_MOVIMIENTOS. */
	private static final int LONGITUD_CANTIDAD_DE_MOVIMIENTOS = 3;

	/** The Constant ERROR_CODIGO_SIN_MOVIMIENTO. */
	private static final int ERROR_CODIGO_SIN_MOVIMIENTO = 10002001;

	/** The prefijo cnsctamovs. */
	@Value("${SERVICIO.PREFIJO.CNSCTAMOVS}")
	private String prefijoCnsctamovs;

	/** The version160. */
	@Value("${SERVICIO.VERSION.160}")
	private String version160;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.cuentas.dao.UltimosMovimientosDAO#
	 * consultarExtractoUltimosMovimientos(ar.com.santanderrio.obp.cuentas.
	 * entities.ConsultaUltimosMovimientos)
	 */
	@Override
	@Cacheable(cacheNames = CacheConstants.Names.CACHE_NYO_CNSCTAMOVS, key = "#consultaUltimosMovimientos.cuenta.cliente.nup.concat(#consultaUltimosMovimientos.cuenta.nroCuentaProducto).concat(#consultaUltimosMovimientos.fechaDesdeHastaAsString())", condition = "#consultaUltimosMovimientos.tipoConsulta.tipo=='C'", unless="#result.cantidadMovientos == null")
	public UltimosMovimientosDTO consultarExtractoUltimosMovimientos(
	        ConsultaUltimosMovimientos consultaUltimosMovimientos) throws DAOException {
		if (consultaUltimosMovimientos.getTipoConsulta() != null && consultaUltimosMovimientos.getTipoConsulta()
		        .equals(TipoConsultaMovimientos.MOVIMIENTO_CONSOLIDADOS)) {
			LOGGER.info("Se cachea {} tipo C con nup {}, cuenta {} y fecha {}{}",
			        consultaUltimosMovimientos.getCuenta().getCliente().getNup(),
			        consultaUltimosMovimientos.getCuenta().getNroCuentaProducto(),
			        consultaUltimosMovimientos.getFechaDesde(), consultaUltimosMovimientos.getFechaHasta());
		} else {
			LOGGER.info("Esta invocacion no se debe cachear por no ser tipo C.");
		}
		UltimosMovimientosDTO ultimosMovimientos = new UltimosMovimientosDTO();
		IatxRequest request = buildIatxRequest(consultaUltimosMovimientos);
		IatxResponse iatxResponse;

		try {
			iatxResponse = iatxComm.exec(request);
			if (iatxResponse.getEstadoRespuesta().equals(EstadoRespuesta.OK)
			        && (iatxResponse.getErrorCode() != ERROR_CODIGO_SIN_MOVIMIENTO)) {
				ultimosMovimientos = buildUltimosMovimientos(iatxResponse,
				        consultaUltimosMovimientos.getTipoConsulta());
				LOGGER.info("Consulta de movimientos Ok.");
			} else if (!iatxResponse.getEstadoRespuesta().equals(EstadoRespuesta.OK)
			        && iatxResponse.getErrorCode() != ERROR_CODIGO_SIN_MOVIMIENTO) {
				LOGGER.error("Error consulta de movimientos.", iatxResponse.getErrorCode());
				throw new DAOException("Error IATX: " + iatxResponse.getErrorCode());
			}
		} catch (IatxException e) {
			LOGGER.error("Error consulta de movimientos.", e);
			throw new DAOException(e);
		}

		return ultimosMovimientos;
	}

	/**
	 * Builds the iatx request.
	 *
	 * @param consultaUltimosMovimientos the consulta ultimos movimientos
	 * @return the iatx request
	 */
	private IatxRequest buildIatxRequest(ConsultaUltimosMovimientos consultaUltimosMovimientos) {
		IatxRequest request = new IatxRequest(prefijoCnsctamovs, version160);
		IatxRequestData requestData = new IatxRequestData(consultaUltimosMovimientos.getCuenta().getCliente());

		// tipo Cuenta
		requestData.addBodyValue(
		        StringUtils.leftPad(consultaUltimosMovimientos.getCuenta().getTipoCuenta(), LONGITUD_TIPO_CUENTA, '0'));
		// nro Cuenta - Nro_Sucursal - ID 4411
		requestData.addBodyValue(StringUtils.leftPad(consultaUltimosMovimientos.getCuenta().getNroSucursal(),
		        LONGITUD_NUMERO_SUCURSAL, '0'));
		// nro Cuenta - Nro_Cuenta - ID 4411
		requestData.addBodyValue(StringUtils.leftPad(
		        String.valueOf(Integer.parseInt(consultaUltimosMovimientos.getCuenta().getNroCuentaProducto())),
		        LONGITUD_NUMERO_CUENTA_PRODUCTO, '0'));
		// fecha Desde Formato 20150501
		requestData.addBodyValue(format(consultaUltimosMovimientos.getFechaDesde()));
		// fecha Hasta Formato 20150531
		requestData.addBodyValue(format(consultaUltimosMovimientos.getFechaHasta()));
		// cantidad de movimientos
		requestData.addBodyValue(StringUtils.leftPad(consultaUltimosMovimientos.getCantidadMovimientos().toString(),
		        LONGITUD_CANTIDAD_DE_MOVIMIENTOS, '0'));
		// Tipo Consulta
		requestData.addBodyValue(consultaUltimosMovimientos.getTipoConsulta().getTipo());
		// orden - reemplazar 3
		requestData.addBodyValue(consultaUltimosMovimientos.getOrdenMovimientos().getOrden());
		// indicador de apertura
		requestData.addBodyValue(consultaUltimosMovimientos.getIndicador());

		request.setData(requestData);
		return request;
	}

	/**
	 * Format.
	 *
	 * @param date the date
	 * @return the string
	 */
	private String format(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		String formated = "        ";
		if (date != null) {
			formated = format.format(date);
		}
		return formated;
	}

	/**
	 * Builds the ultimos movimientos.
	 *
	 * @param iatxResponse the iatx response
	 * @param tipoConsulta the tipo consulta
	 * @return the ultimos movimientos
	 */
	private UltimosMovimientosDTO buildUltimosMovimientos(IatxResponse iatxResponse,
	        TipoConsultaMovimientos tipoConsulta) {
		UltimosMovimientosDTO ultimosMovimientos = new UltimosMovimientosDTO();
		String fechSaldo = iatxResponse.getNextData();
		if (Integer.parseInt(fechSaldo) > 0) {
			ultimosMovimientos.setFechaSaldo(fechSaldo);
		}
		ultimosMovimientos.setSaldoFinalPesos(iatxResponse.getNextData());
		ultimosMovimientos.setSaldoFinalDolares(iatxResponse.getNextData());
		ultimosMovimientos.setSaldoACTE(iatxResponse.getNextData());
		ultimosMovimientos.setSaldoACAD(iatxResponse.getNextData());
		ultimosMovimientos.setSaldoACCD(iatxResponse.getNextData());
		ultimosMovimientos.setSaldoACAD(iatxResponse.getNextData());
		ultimosMovimientos.setCantidadMovientos(iatxResponse.getNextData());

		List<Movimiento> movimientos = new ArrayList<Movimiento>();
		int cantidadMovimientos = Integer.parseInt(ultimosMovimientos.getCantidadMovientos());

		for (int i = 0; i < cantidadMovimientos; i++) {
			Movimiento movimiento = new Movimiento();
			movimiento.setFechaMovimiento(iatxResponse.getNextData());
			movimiento.setHoraMovimiento(iatxResponse.getNextData());
			movimiento.setNumeroComprobante(iatxResponse.getNextData());
			movimiento.setClaseMovimiento(iatxResponse.getNextData());
			movimiento.setImporteMovimiento(iatxResponse.getNextData());
			movimiento.setDescripcionMovimiento(iatxResponse.getNextData());
			movimiento.setDescripcionAdicionalMovimiento(iatxResponse.getNextData());
			movimiento.setSucursalOrigen(iatxResponse.getNextData());
			movimiento.setMonedaMovimiento(iatxResponse.getNextData());
			movimiento.setSaldoCuenta(iatxResponse.getNextData());
			movimiento.setCodigoMovimiento(iatxResponse.getNextData());
			movimiento.setSubCodigoMovimiento(iatxResponse.getNextData());
			movimiento.setFechaValor(iatxResponse.getNextData());
			movimiento.setCodigoAltaIr(iatxResponse.getNextData());
			movimiento.setIndicadorOberservacion(iatxResponse.getNextData());
			movimiento.setObservacion(iatxResponse.getNextData());
			movimiento.setIndicadorMovimiento(iatxResponse.getNextData());
			movimiento.setCanal(iatxResponse.getNextData());
			movimiento.setReferenciaInterna(iatxResponse.getNextData());
			movimiento.setIndicadorMovimientoAutomatico(iatxResponse.getNextData());
			movimiento.setUsuario(iatxResponse.getNextData());
			movimiento.setTipoConsulta(tipoConsulta);
			movimientos.add(movimiento);
		}
		ultimosMovimientos.setMovimientos(movimientos);
		return ultimosMovimientos;

	}

}