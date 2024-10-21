/*
 * 
 */
package ar.com.santanderrio.obp.servicios.debitoautomatico;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.RegistroSesion;
import ar.com.santanderrio.obp.servicios.comun.ResultadoTransaccion;
import ar.com.santanderrio.obp.servicios.comun.mensaje.dao.MensajeDAO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.debitoautomatico.bo.DebitoAutomaticoBO;
import ar.com.santanderrio.obp.servicios.pagoautomatico.entities.AdhesionDebitoAutomatico;
import ar.com.santanderrio.obp.servicios.pagos.dao.DebitoAutomaticoDAO;

/**
 * The Class DebitoAutomaticoBOImpl.
 */
@Component
public class DebitoAutomaticoBOImpl implements DebitoAutomaticoBO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(DebitoAutomaticoBOImpl.class);

	/** The debito automatico DAO. */
	@Autowired
	private DebitoAutomaticoDAO debitoAutomaticoDAO;

	/** The mensaje dao. */
	@Autowired
	private MensajeDAO mensajeDAO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.debitoautomatico.bo.DebitoAutomaticoBO#
	 * adherir(ar.com.santanderrio.obp.servicios.clientes.entities.Cliente,
	 * ar.com.santanderrio.obp.servicios.clientes.entities.RegistroSesion,
	 * ar.com.santanderrio.obp.servicios.pagoautomatico.entities.
	 * AdhesionDebitoAutomatico)
	 */
	@Override
	public Respuesta<AdhesionDebitoAutomatico> adherir(Cliente cliente, RegistroSesion registroSesion,
			AdhesionDebitoAutomatico adhesionDebitoAutomatico) throws BusinessException {
		try {
			return debitoAutomaticoDAO.adherir(cliente, adhesionDebitoAutomatico);

		} catch (DAOException e) {
			LOGGER.error("Error al adherir debito", e);
			throw new BusinessException(e);
		}
	}

	/**
	 * Baja adhesion. 9816.
	 *
	 * @param adhesionDebitoAutomatico
	 *            the adhesion debito automatico
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 * @see ar.com.santanderrio.obp.servicios.pagos.bo.PagoDebitoAutomaticoBO#ejecutarBajaAdhesionDebitoAutomatico(ar.com.santanderrio.obp.servicios.pagos.entities.PagoPendiente,
	 *      ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
	 */
	@Override
	public Respuesta<ResultadoTransaccion> bajaAdhesion(AdhesionDebitoAutomatico adhesionDebitoAutomatico,
			Cliente cliente) {
		Respuesta<ResultadoTransaccion> respuestaPagoDebitoAuto = new Respuesta<ResultadoTransaccion>();
		ResultadoTransaccion resultado = new ResultadoTransaccion();
		try {
			resultado = debitoAutomaticoDAO.bajaAdhesion(cliente, adhesionDebitoAutomatico);
			if (EstadoRespuesta.OK.equals(resultado.getEstadoRespuesta())) {
				resultado.setEstadoRespuesta(EstadoRespuesta.OK);
				respuestaPagoDebitoAuto.setItemMensajeRespuesta(
						getMessage(CodigoMensajeConstantes.DEBITO_AUTOMATICO_BAJA_ADHESION_OK));
				respuestaPagoDebitoAuto.setRespuesta(resultado);
				respuestaPagoDebitoAuto.setRespuestaVacia(false);
				respuestaPagoDebitoAuto.setEstadoRespuesta(EstadoRespuesta.OK);
				return respuestaPagoDebitoAuto;
			} else if (EstadoRespuesta.WARNING.equals(resultado.getEstadoRespuesta())) {
				resultado.setEstadoRespuesta(EstadoRespuesta.ERROR);
				respuestaPagoDebitoAuto.setItemMensajeRespuesta(
						getMessage(CodigoMensajeConstantes.DEBITO_AUTOMATICO_BAJA_ADHESION_REPETIDA));
				respuestaPagoDebitoAuto.setRespuesta(resultado);
				respuestaPagoDebitoAuto.setRespuestaVacia(true);
				respuestaPagoDebitoAuto.setEstadoRespuesta(EstadoRespuesta.ERROR);
				return respuestaPagoDebitoAuto;
			} else {
				resultado.setEstadoRespuesta(EstadoRespuesta.WARNING);
				respuestaPagoDebitoAuto.setItemMensajeRespuesta(
						getMessage(CodigoMensajeConstantes.DEBITO_AUTOMATICO_BAJA_ADHESION_ERROR));
				respuestaPagoDebitoAuto.setRespuesta(resultado);
				respuestaPagoDebitoAuto.setRespuestaVacia(true);
				respuestaPagoDebitoAuto.setEstadoRespuesta(EstadoRespuesta.WARNING);
				return respuestaPagoDebitoAuto;
			}
		} catch (DAOException e) {
			String mensaje = e.getMessage();
			LOGGER.error(mensaje, e);
			resultado.setEstadoRespuesta(EstadoRespuesta.ERROR);
			respuestaPagoDebitoAuto
					.setItemMensajeRespuesta(getMessage(CodigoMensajeConstantes.DEBITO_AUTOMATICO_BAJA_ADHESION_ERROR));
			respuestaPagoDebitoAuto.setRespuesta(resultado);
			respuestaPagoDebitoAuto.setRespuestaVacia(true);
			respuestaPagoDebitoAuto.setEstadoRespuesta(EstadoRespuesta.WARNING);
			return respuestaPagoDebitoAuto;
		}
	}

	/**
	 * Gets the message.
	 *
	 * @param codigo
	 *            the codigo
	 * @return the message
	 */
	private List<ItemMensajeRespuesta> getMessage(String codigo) {
		List<ItemMensajeRespuesta> itemMensajeRespuesta = new ArrayList<ItemMensajeRespuesta>();
		Mensaje mensaje = mensajeDAO.obtenerMensajePorCodigo(codigo);
		ItemMensajeRespuesta item = new ItemMensajeRespuesta(mensaje.getMensaje());
		itemMensajeRespuesta.add(item);
		return itemMensajeRespuesta;
	}
}
