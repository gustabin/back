/**
 * 
 */
package ar.com.santanderrio.obp.servicios.debitoautomatico.tarjeta.bo.impl;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.QueryTimeoutException;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.debitoautomatico.tarjeta.bo.DebitoAutomaticoTarjetaBO;
import ar.com.santanderrio.obp.servicios.debitoautomatico.tarjeta.dao.DebitoAutomaticoTarjetaDAO;
import ar.com.santanderrio.obp.servicios.debitoautomatico.tarjeta.dto.DatoClienteDebitoTarjetaInDTO;
import ar.com.santanderrio.obp.servicios.debitoautomatico.tarjeta.dto.DebitoAutomaticoTarjetaDTO;
import ar.com.santanderrio.obp.servicios.debitoautomatico.tarjeta.entities.ClienteDebitoTarjetaInEntity;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;

/**
 * The Class DebitoAutomaticoTarjetaBOImpl.
 *
 * @author florencia.n.martinez
 */
@Component
public class DebitoAutomaticoTarjetaBOImpl implements DebitoAutomaticoTarjetaBO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(DebitoAutomaticoTarjetaBOImpl.class);

	/** The Constant LOGGER_OK. */
	private static final String LOGGER_OK = "Respuesta DTO OK obtenida al llamar a Store Procedure para una nueva adhesion de debito automatico en tarjeta: {}.";

	/** The Constant LOGGER_ERROR. */
	private static final String LOGGER_ERROR = "Error al llamar a Store Procedure para una nueva adhesion de debito automatico en tarjeta: {}.";

	/** The debito tarjeta DAO. */
	@Autowired
	private DebitoAutomaticoTarjetaDAO debitoTarjetaDAO;

	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/** The mensaje BO. */
	@Autowired
	private MensajeBO mensajeBO;

	/**
	 * Obtiene una respuesta DTO de la adhesion al debito automatico en la
	 * tarjeta de credito.
	 *
	 * @param cliente
	 *            the cliente
	 * @param datosClienteDebito
	 *            the datos cliente debito
	 * @param nroTarjetaEnmascarado
	 *            the nro tarjeta enmascarado
	 * @return the respuesta
	 */
	@Override
	public Respuesta<DebitoAutomaticoTarjetaDTO> obtenerAdhesionDebitoTarjeta(Cliente cliente,
			DatoClienteDebitoTarjetaInDTO datosClienteDebito, String nroTarjetaEnmascarado) {
		try {
			
			//Manganeta temporal, borrar cuando el nombre del colegio aparezca bien en el medioDePago.txt
            if(datosClienteDebito.getEmpresaServicio().equalsIgnoreCase("COL SAN ESTEBAN - CUIT 30672972961")){
            	datosClienteDebito.setEmpresaServicio("COL SAN ESTEBAN");
            }
			
			Respuesta<DebitoAutomaticoTarjetaDTO> respuestaDTO = respuestaFactory.crearRespuestaOk(
					DebitoAutomaticoTarjetaDTO.class,
					new DebitoAutomaticoTarjetaDTO(
							debitoTarjetaDAO.solicitarAdhesionTarjeta(
									new ClienteDebitoTarjetaInEntity(cliente, datosClienteDebito)),
							MessageFormat.format(
									mensajeBO
											.obtenerMensajePorCodigo(
													CodigoMensajeConstantes.FEEDBACK_ADHESION_DEBITO_TARJETA)
											.getMensaje(),
									datosClienteDebito.getEmpresaServicio(), nroTarjetaEnmascarado)));
			LOGGER.info(LOGGER_OK, respuestaDTO);
			return respuestaDTO;
		} catch (DAOException e) {
			LOGGER.error(LOGGER_ERROR, e);
			return respuestaFactory.crearRespuestaError(DebitoAutomaticoTarjetaDTO.class,
					crearListaItemMensaje(datosClienteDebito));
		} catch (QueryTimeoutException qte) {
			LOGGER.error(LOGGER_ERROR, qte);
			return respuestaFactory.crearRespuestaError(DebitoAutomaticoTarjetaDTO.class, StringUtils.EMPTY,
					TipoError.TIMEOUT, CodigoMensajeConstantes.ERROR_TIMEOUT_FEEDBACK_ADHESION_DEBITO_TARJETA);
		}
	}

	/**
	 * Crea la lista de item mensaje respuesta con mensaje parametrizado para el
	 * caso de error generico.
	 *
	 * @param datosClienteDebito
	 *            the datos cliente debito
	 * @return the list
	 */
	private List<ItemMensajeRespuesta> crearListaItemMensaje(DatoClienteDebitoTarjetaInDTO datosClienteDebito) {
		List<ItemMensajeRespuesta> items = new ArrayList<ItemMensajeRespuesta>();
		items.add(new ItemMensajeRespuesta(MessageFormat.format(mensajeBO
				.obtenerMensajePorCodigo(CodigoMensajeConstantes.ERROR_FEEDBACK_ADHESION_DEBITO_TARJETA).getMensaje(),
				datosClienteDebito.getEmpresaServicio())));
		items.get(0).setTag(StringUtils.EMPTY);
		items.get(0).setTipoError(TipoError.ERROR_GENERICO.getDescripcion());
		return items;
	}
}
