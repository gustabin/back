/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequest;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequestData;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.legal.dao.LegalDAO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.dao.MensajeDAO;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxComm;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxResponse;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.AdhesionTarjetaDebitoAutomaticoDAO;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.DatosAdhesionDebitoTarjeta;
import ar.com.santanderrio.obp.servicios.tarjetas.view.ComprobanteFeedbackView;

/**
 * Clase AdhesionTarjetasDebitoAutomaticoDAOImpl.
 *
 * @author mariano.g.pulera
 */

@Component
public class AdhesionTarjetaDebitoAutomaticoDAOImpl implements AdhesionTarjetaDebitoAutomaticoDAO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(AdhesionTarjetaDebitoAutomaticoDAOImpl.class);

	/** The iatx comm. */
	@Autowired
	private IatxComm iatxComm;

	/** The legal dao. */
	@Autowired
	private LegalDAO legalDao;

	/** The mensaje dao. */
	@Autowired
	private MensajeDAO mensajeDao;

	/** The servicio pag tjc. */
	@Value("${SERVICIO.PREFIJO.CMBADHETAR}")
	private String servicioCmbadhetar;

	/** The version pag tjc. */
	@Value("${SERVICIO.VERSION.CMBADHETAR}")
	private String versionCmbadhetar;

	/** The Constant CODIGO_RETORNO_OK. */
	private static final int CODIGO_RETORNO_OK = 0;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.tarjetas.dao.
	 * AdhesionTarjetaDebitoAutomaticoDAO#adherirTarjetaDebitoAutomatico(ar.com.
	 * santanderrio.obp.servicios.clientes.entities.Cliente,
	 * ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.
	 * DatosAdhesionDebitoTarjeta)
	 */
	@Override
	public ComprobanteFeedbackView adherirTarjetaDebitoAutomatico(Cliente cliente,
			DatosAdhesionDebitoTarjeta datosAdhesionDebito) throws DAOException {

		IatxRequest request = new IatxRequest(servicioCmbadhetar, versionCmbadhetar);
		try {
			IatxRequestData requestData = new IatxRequestData(cliente);

			requestData.addBodyValue(datosAdhesionDebito.getTipoTarjeta());
			requestData.addBodyValue(datosAdhesionDebito.getNroCuentaTarjeta());
			requestData.addBodyValue(datosAdhesionDebito.getTipoCuentaDebito());
			requestData.addBodyValue(datosAdhesionDebito.getSucursalCuentaDebito());
			requestData.addBodyValue(datosAdhesionDebito.getNroCuentaDebito());
			requestData.addBodyValue(datosAdhesionDebito.getNroFirmante());
			requestData.addBodyValue(datosAdhesionDebito.getFormaPago());

			request.setData(requestData);
			IatxResponse iatxResponse = iatxComm.exec(request);

			int codigoDeRetorno = iatxResponse.getErrorCode();

			if (CODIGO_RETORNO_OK == codigoDeRetorno) {
				ComprobanteFeedbackView datosFeedback = new ComprobanteFeedbackView();
				SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy - HH:mm");
				SimpleDateFormat formatoFechaHoraTrama = new SimpleDateFormat("yyyyMMddHHmmss");
				String fechaHoraSinFormato = iatxResponse.getFechaHoraReq();
				Date fechaHora = null;

				try {
					fechaHora = formatoFechaHoraTrama.parse(fechaHoraSinFormato);
				} catch (java.text.ParseException ex) {
					LOGGER.error("Ha ocurrido un error al parsear la fecha", ex);
				}

				datosFeedback.setNroDeComprobante(iatxResponse.getNroComprobante());
				datosFeedback.setFechaHora(dateFormatter.format(fechaHora));
				datosFeedback.setLegalesSEUO(legalDao.obtenerLegal("1005"));
				return datosFeedback;

			} else {
				LOGGER.debug("Error en Iatx:" + iatxResponse.getErrorCode());
				throw new DAOException("No se pudo adherir la tarjeta al debito automatico");
			}

		} catch (IatxException e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException("TIMEOUT");
		}
	}

}
