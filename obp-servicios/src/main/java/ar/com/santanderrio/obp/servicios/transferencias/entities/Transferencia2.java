/*
 * 
 */
package ar.com.santanderrio.obp.servicios.transferencias.entities;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;

/**
 * The Class Transferencia2.
 */
/**
 * Si la clase Transferencia no se usa reemplazarla por esta clase renombrandola a Transferencia
 * @author B043346
 *
 */
public class Transferencia2 {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(Transferencia2.class);
	
	/** The inicio horario prohibido. */
	@Value("${TRANSFERENCIAS.INICIO.HORARIOPROHIBIDO}")
	private String inicioHorarioProhibido;
	
	/** The fin horario prohibido. */
	@Value("${TRANSFERENCIAS.FIN.HORARIOPROHIBIDO}")
	private String finHorarioProhibido;
	
	/** The mensaje DAO. */
	@Autowired
	private MensajeBO mensajeBO;
	
	/**
	 * Obtener mensaje si horario de transferencia no es valido.
	 *
	 * @param <T>
	 *            the generic type
	 * @param respuesta
	 *            the respuesta
	 * @return the item mensaje respuesta
	 */
	public <T> void obtenerMensajeSiHorarioDeTransferenciaNoEsValido(Respuesta<T> respuesta) {
		try {
			if (horarioValidoDeTransferencias()) {
				ItemMensajeRespuesta item = new ItemMensajeRespuesta();
				String mensajeError = mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_TRANSFERENCIA_FUERA_DE_HORARIO)
						.getMensaje();
				item.setTipoError(TipoError.TRANSFERENCIA_FUERA_DE_HORARIO.getDescripcion());
				item.setMensaje(mensajeError);
				if (null == respuesta.getItemsMensajeRespuesta()) {
					List<ItemMensajeRespuesta> listaMensajeRespuesta = new ArrayList<ItemMensajeRespuesta>();
					listaMensajeRespuesta.add(item);
					respuesta.setItemMensajeRespuesta(listaMensajeRespuesta);
				} else {
					respuesta.getItemsMensajeRespuesta().add(item);
				}
			}
		} catch (ParseException e) {
			LOGGER.error("Error al pasear horarios en validacion de transferencias");
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Horario valido de transferencias.
	 *
	 * @return the boolean
	 * @throws ParseException the parse exception
	 */
	public Boolean horarioValidoDeTransferencias() throws ParseException {
		if (!ISBANStringUtils.isNullOrBlank(inicioHorarioProhibido)
				&& !ISBANStringUtils.isNullOrBlank(finHorarioProhibido)) {
			return ISBANStringUtils.compararHoras(inicioHorarioProhibido, finHorarioProhibido);
		}
		return false;
	}
}
