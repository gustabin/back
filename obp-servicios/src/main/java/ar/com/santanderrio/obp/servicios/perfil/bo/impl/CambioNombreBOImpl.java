package ar.com.santanderrio.obp.servicios.perfil.bo.impl;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.generated.webservices.productos.ArrayOf158770343432493182NillableInfoRequeridaWS;
import ar.com.santanderrio.obp.generated.webservices.productos.ResultadoAltaWS;
import ar.com.santanderrio.obp.servicios.comun.mensaje.dao.MensajeDAO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.perfil.bo.CambioNombreBO;
import ar.com.santanderrio.obp.servicios.perfil.dto.CambioNombreDTO;
import ar.com.santanderrio.obp.servicios.producto.dao.SolicitudProductoDAO;

@Component
public class CambioNombreBOImpl implements CambioNombreBO{

	private static final Logger LOGGER = LoggerFactory.getLogger(CambioNombreBOImpl.class);

	/** The sesion cliente. */
	@Autowired
	private SesionCliente sesionCliente;

	/** The solicitud producto DAO. */
	@Autowired
	private SolicitudProductoDAO solicitudProductoDAO;

	/** The mensaje dao. */
	@Autowired
	private MensajeDAO mensajeDao;

	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;
	
	// TODO para llamar correctamente al altaGestion falta el primer parametro, el codigo de operacion, ese que puse es falopa
	@Override
	public Respuesta<CambioNombreDTO> solicitarCambioNombreCliente(String nuevoNombre) {

		ArrayOf158770343432493182NillableInfoRequeridaWS infoReq = new ArrayOf158770343432493182NillableInfoRequeridaWS();
		CambioNombreDTO cambioNombreDTO = new CambioNombreDTO();
		ResultadoAltaWS resultadoWS;
		try {
			resultadoWS = solicitudProductoDAO.altaGestion(116, "F",
					Integer.valueOf(sesionCliente.getCliente().getNup()), "NEIT", "HBAN0002", 35,
					StringUtils.isBlank(nuevoNombre) ? " " : nuevoNombre, infoReq);
			
			if (resultadoWS.getCodRetorno() != 0) {
				LOGGER.error("Error en AltaGestion - CodRetorno:" + resultadoWS.getCodRetorno() + " -"
						+ resultadoWS.getDescRetorno());
				return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_GENERICO,
						CodigoMensajeConstantes.ERROR_GENERICO);
			}
			
			String mensaje = mensajeDao.obtenerMensajePorCodigo(CodigoMensajeConstantes.CAMBIO_NOMBRE_AUTOPERCIBIDO_FEEDBACK_OK).getMensaje();
			cambioNombreDTO.setMensajeOK(StringUtils.isBlank(mensaje) ? StringUtils.EMPTY : mensaje);
			cambioNombreDTO.setNumeroComprobante(String.valueOf(resultadoWS.getIdeGestionNro()));
			
		} catch (NumberFormatException e) {
			LOGGER.debug("No se pudo solicitar el cambio de nombre", e);
			return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_GENERICO);
		} catch (DAOException e) {
			LOGGER.debug("No se pudo solicitar el cambio de nombre", e);
			return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_GENERICO);
		}
		
		return respuestaFactory.crearRespuestaOk(cambioNombreDTO);
		
		
	}

}
