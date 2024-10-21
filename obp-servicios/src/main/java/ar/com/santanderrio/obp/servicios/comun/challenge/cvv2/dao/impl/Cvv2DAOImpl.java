/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.challenge.cvv2.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequest;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequestData;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.challenge.cvv2.dao.Cvv2DAO;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxComm;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxResponse;

/**
 * The Class Cvv2DAOImpl.
 */
@Component
public class Cvv2DAOImpl implements Cvv2DAO {

	/** The logger. */
	private static final Logger LOGGER = LoggerFactory.getLogger(Cvv2DAOImpl.class);

	/** The Constant CODIGO_OK. */
	private static final int CODIGO_OK = 0;

	/** The iatx comm. */
	@Autowired
	private IatxComm iatxComm;

	/** The servicio nombre. */
	private String servicioNombre = "VALCVV2VIS";

	/** The servicio version. */
	private String servicioVersion = "100";

	/**
	 * Ejecutar validacion cvv2.
	 *
	 * @param marcaTarjeta
	 *            the marca tarjeta
	 * @param nroTarjeta
	 *            the nro tarjeta
	 * @param vencTarjeta
	 *            the venc tarjeta
	 * @param codSeguridad
	 *            the cod seguridad
	 * @param cliente
	 *            the cliente
	 * @return true, if successful
	 * @throws DAOException
	 *             the DAO exception
	 */
	@Override
	public boolean ejecutarValidacionCvv2(String marcaTarjeta, String nroTarjeta, String vencTarjeta,
			String codSeguridad, Cliente cliente) throws DAOException {
		boolean resultadoValidacion = false;
		IatxResponse iatxResponse;
		try {
			IatxRequest request = generarRequestValidarCvv2(marcaTarjeta, nroTarjeta, vencTarjeta, codSeguridad,
					cliente);
			iatxResponse = iatxComm.exec(request);
			int errorCode = iatxResponse.getErrorCode();
			if (errorCode == CODIGO_OK) {
				resultadoValidacion = true;
				LOGGER.info("Resultado validación por CVV2: OK");
			} else {
				LOGGER.info("Resultado validación por CVV2: Error");
				throw new DAOException(String.valueOf(iatxResponse.getErrorCode()));
			}
		} catch (IatxException e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException(e);
		}
		return resultadoValidacion;
	}

	/**
	 * Generar request validar cvv2.
	 *
	 * @param marcaTarjeta
	 *            the marca tarjeta
	 * @param nroTarjeta
	 *            the nro tarjeta
	 * @param vencTarjeta
	 *            the venc tarjeta
	 * @param codSeguridad
	 *            the cod seguridad
	 * @param cliente
	 *            the cliente
	 * @return the iatx request
	 */
	private IatxRequest generarRequestValidarCvv2(String marcaTarjeta, String nroTarjeta, String vencTarjeta,
			String codSeguridad, Cliente cliente) {
		IatxRequest request = new IatxRequest(servicioNombre, servicioVersion);
		IatxRequestData data = new IatxRequestData(cliente);
		data.addBodyValue(marcaTarjeta);
		data.addBodyValue(nroTarjeta);
		data.addBodyValue(vencTarjeta);
		data.addBodyValue(codSeguridad);

		request.setData(data);
		return request;
	}
}
