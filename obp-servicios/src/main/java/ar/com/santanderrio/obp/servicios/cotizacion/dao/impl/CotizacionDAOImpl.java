/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cotizacion.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequest;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequestData;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.cotizacion.dao.CotizacionDAO;
import ar.com.santanderrio.obp.servicios.cotizacion.dto.CotizacionParametrosEntity;
import ar.com.santanderrio.obp.servicios.cotizacion.entities.CotizacionEntity;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxComm;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxResponse;

/**
 * Clase PagosTarjetaDAOImpl.
 *
 * @author mariano.g.pulera
 */

@Component
public class CotizacionDAOImpl implements CotizacionDAO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(CotizacionDAOImpl.class);

	/** The iatx comm. */
	@Autowired
	private IatxComm iatxComm;

	/** The Constant SERVICIO_CNSTJCCOTI. */
	private static final String SERVICIO_CNSTJCCOTI = "CNSTJCCOTI";

	/** The Constant VERSION_CNSTJCCOTI. */
	private static final String VERSION_CNSTJCCOTI = "100";

	/** The Constant CODIGO_RETORNO_OK. */
	private static final int CODIGO_RETORNO_OK = 0;

	/**
	 * Este metodo prepara la consulta al servicio para obtener la cotizacion y
	 * otros datos.
	 *
	 * @param cliente
	 *            El cliente a consultar
	 * @param cotizacionParametros
	 *            Los datos que se necesitan para la peticion
	 * @return El objeto CotizacionEntity con los datos del servicio
	 * @throws DAOException
	 *             Si ocurrio algun error en la ejecucion del servicio
	 */
	public CotizacionEntity obtenerDatosCotizacion(Cliente cliente, CotizacionParametrosEntity cotizacionParametros)
			throws DAOException {

		IatxRequest request = new IatxRequest(SERVICIO_CNSTJCCOTI, VERSION_CNSTJCCOTI);
		try {
			IatxRequestData requestData = new IatxRequestData(cliente);

			requestData.addBodyValue(cotizacionParametros.getNroCuentaTarjeta());
			requestData.addBodyValue(cotizacionParametros.getTipoTarjeta());
			requestData.addBodyValue(cotizacionParametros.getNroTarjeta());
			requestData.addBodyValue(cotizacionParametros.getImportePesos().replaceAll("\\-", "0"));
			requestData.addBodyValue(cotizacionParametros.getImporteDolares().replaceAll("\\-", "0"));
			requestData.addBodyValue(cotizacionParametros.getMonedaPago());

			request.setData(requestData);
			IatxResponse iatxResponse = iatxComm.exec(request);
			// Cod_retorno
			int codigoDeRetorno = iatxResponse.getErrorCode();

			if (CODIGO_RETORNO_OK == codigoDeRetorno) {
				return parsearDatosCotizacion(iatxResponse);
			} else {
				LOGGER.debug("Error en Iatx:" + iatxResponse.getErrorCode());
				throw new DAOException("No se pudo extraer los datos de cotizacion");
			}

		} catch (IatxException e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException(e);
		}
	}

	/**
	 * Recibe la trama, luego de ejecutar el servicio, y la parsea, extrayendo
	 * los datos y armando el objeto de los datos de cotizacion para devolver.
	 *
	 * @param iatxResponse
	 *            La respuesta IATX
	 * @return El objeto de cotizacion ya parseado
	 * @throws DAOException
	 *             En caso de algun error
	 */
	public CotizacionEntity parsearDatosCotizacion(IatxResponse iatxResponse) throws DAOException {

		LOGGER.info("Se inicia el parseo para obtener los datos de cotizacion");

		CotizacionEntity cotizacionEntity = new CotizacionEntity();

		// DatosEntity referidos a la tarjeta
		cotizacionEntity.setNumeroCuentaTarjeta(iatxResponse.getNextData().trim());
		cotizacionEntity.setTipoTarjeta(iatxResponse.getNextData().trim());
		cotizacionEntity.setNumeroTarjeta(iatxResponse.getNextData().trim());

		// IMPORTE PESOS
		// Lo que se debe en pesos
		cotizacionEntity.setImportePesos(iatxResponse.getNextData().trim());

		// IMPORTE DOLARES
		// Lo que se debe en dolares
		cotizacionEntity.setImporteDolares(iatxResponse.getNextData().trim());

		// IMPORTE PESOS CONVERTIDO
		// Si la moneda de pago es en dolares, devuelve los pesos del atributo
		// "importePesos"
		// convertido a dolares. Si la moneda de pago es pesos, devuelve el
		// mismo valor
		// de dicho atributo, ya que no hay nada que convertir
		cotizacionEntity.setImportePesosConvertido(iatxResponse.getNextData().trim());

		// IMPORTE DOLARES CONVERTIDO
		// Si la moneda de pago es en dolares, devuelve el mismo valor del
		// atributo "importeDolares"
		// ya que no hay nada que convertir. Si la moneda de pago es pesos,
		// devuelve los dolares
		// de ese atributo convertidos a pesos.
		cotizacionEntity.setImporteDolaresConvertido(iatxResponse.getNextData().trim());

		// COTIZACION DOLARES
		// Devuelve la cotizacion del dia, y es la que utiliza el servicio para
		// convertir
		// los importes de mas arriba.
		cotizacionEntity.setCotizacionDolares(iatxResponse.getNextData().trim());

		// MONEDA PAGO
		// Es la moneda en la que se hara el pago, y la utilizada como
		// referencia para convertir
		// los importes de mas arriba segun sea necesario.
		cotizacionEntity.setMonedaPago(iatxResponse.getNextData().trim());

		// IMPORTE TOTAL CONVERTIDO
		// Devuelve la suma total de dolares y pesos a pagar, convertidos en la
		// moneda que corresponda
		cotizacionEntity.setImporteTotalConvertido(iatxResponse.getNextData().trim());

		return cotizacionEntity;

	}
}
