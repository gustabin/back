/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cambiogrupoafiinidad.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequest;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequestData;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.servicios.cambiogrupoafiinidad.dao.CambioGrupoAfinidadConsultaTarjetaDAO;
import ar.com.santanderrio.obp.servicios.cambiogrupoafiinidad.entities.ConsultaDatosTarjetaInEntity;
import ar.com.santanderrio.obp.servicios.cambiogrupoafiinidad.entities.ConsultaDatosTarjetaOutEntity;
import ar.com.santanderrio.obp.servicios.cambiogrupoafiinidad.entities.TarjetaDatos;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.exception.RobotException;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxComm;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxResponse;

/**
 * The Class CambioGrupoAfinidadConsultaTarjetaDAOImpl.
 */
@Component
public class CambioGrupoAfinidadConsultaTarjetaDAOImpl implements CambioGrupoAfinidadConsultaTarjetaDAO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(CambioGrupoAfinidadConsultaTarjetaDAOImpl.class);
	
	/** The iatx comm. */
	@Autowired
	private IatxComm iatxComm;
	
	/** The Constant LONGITUD_TIPO_CUENTA. */
	private static final int LONGITUD_MARCA = 1;

	/** The Constant LONGITUD_TIPO_CUENTA. */
	private static final int LONGITUD_TIPO_CUENTA = 1;

	/** The Constant LONGITUD_CUENTA. */
	private static final int LONGITUD_CUENTA = 5;
	
	/** The Constant OPCION. */
	private static final String OPCION = "A";

	/** The Constant CATEGORIA. */
	private static final String CATEGORIA = "P";
	
	/** The Constant TARJETA_INVALIDA. */
	private static final String TARJETA_INVALIDA = "0000000000000000";
	
	/** The Constant LONGITUD_FIELDSET. */
	private static final int LONGITUD_FIELDSET = 43;
	
	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.cambiogrupoafiinidad.dao.CambioGrupoAfinidadConsultaTarjetaDAO#consultaDatosTarjetas(ar.com.santanderrio.obp.servicios.cambiogrupoafiinidad.entities.ConsultaDatosTarjetaInEntity)
	 */
	@Override
	public ConsultaDatosTarjetaOutEntity consultaDatosTarjetas(ConsultaDatosTarjetaInEntity in) throws DAOException {
		ConsultaDatosTarjetaOutEntity respuesta = new ConsultaDatosTarjetaOutEntity();
		IatxRequest request = buildIatxRequest(in);
		IatxResponse iatxResponse = null;
		try {

			iatxResponse = iatxComm.exec(request);
			if (iatxResponse.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
				respuesta = buildDatosTarjeta(iatxResponse);
			} else {
				throw new DAOException("Error" + iatxResponse.getErrorCode());
			}
		} catch (IatxException e) {
			LOGGER.error(e.getMessage(), e);
			throw new RobotException(TipoError.ERROR_GENERICO.getDescripcion());
		} catch (RuntimeException e) {
			LOGGER.error(e.getMessage(), e);
			throw new RobotException(TipoError.ERROR_GENERICO.getDescripcion());
		}
		
		return respuesta;
	}
	
	/**
	 * buildIatxRequest.
	 *
	 * @param inTarjeta
	 *            the in tarjeta
	 * @return the iatx request
	 */
	private IatxRequest buildIatxRequest(ConsultaDatosTarjetaInEntity inTarjeta) {
		IatxRequest requestIatx = new IatxRequest("CNSTJCDATC", "100");
		IatxRequestData requestDataIatx = new IatxRequestData(inTarjeta.getCliente());
		requestDataIatx.addBodyValue(StringUtils.leftPad(inTarjeta.getMarca(), LONGITUD_MARCA, '0'));
		requestDataIatx.addBodyValue(StringUtils.leftPad(inTarjeta.getTipoCuenta(), LONGITUD_TIPO_CUENTA, '0'));
		requestDataIatx.addBodyValue(StringUtils.leftPad(inTarjeta.getNroCuenta().substring(5), LONGITUD_CUENTA, '0'));
		requestDataIatx.addBodyValue("    ");
		requestDataIatx.addBodyValue("000");
		requestDataIatx.addBodyValue("0");
		requestDataIatx.addBodyValue("0000000000000");
		requestDataIatx.addBodyValue("00");
		requestDataIatx.addBodyValue(TARJETA_INVALIDA);
		requestDataIatx.addBodyValue(OPCION);
		requestDataIatx.addBodyValue(CATEGORIA);
		requestDataIatx.addBodyValue(TARJETA_INVALIDA);
		requestIatx.setData(requestDataIatx);
		return requestIatx;
	}
	
	 /**
	 * ConsultaDatosTarjetasOut.
	 *
	 * @param iatxResponse
	 *            the iatx response
	 * @return the consulta datos tarjeta out entity
	 */
	private ConsultaDatosTarjetaOutEntity buildDatosTarjeta(IatxResponse iatxResponse) {
		ConsultaDatosTarjetaOutEntity out = new ConsultaDatosTarjetaOutEntity();
		List<TarjetaDatos> tarjetasOut = new ArrayList<TarjetaDatos>();
		int cant = Integer.parseInt(iatxResponse.getData(5));
		int next = 6;
		for (int n = 0; n < cant; ++n) {
			TarjetaDatos tarjetaOut = new TarjetaDatos();
			tarjetaOut.setNroTarjeta(iatxResponse.getData(next));
			tarjetaOut.setApliCtaRelacionada(iatxResponse.getData(next + 1));
			tarjetaOut.setSucursalCtaRelacionada(iatxResponse.getData(next + 2));
			tarjetaOut.setTipoCuenta(iatxResponse.getData(3));
			tarjetaOut.setNroCuenta(iatxResponse.getData(4));
			tarjetaOut.setEstadoTarjeta(iatxResponse.getData(next + 6));
			tarjetaOut.setApellidoNombreEmbozado(iatxResponse.getData(next + 27));
			tarjetasOut.add(tarjetaOut);
			next += LONGITUD_FIELDSET;
		}
		out.setTarjetas(tarjetasOut);
		return out;
	}
	
}
