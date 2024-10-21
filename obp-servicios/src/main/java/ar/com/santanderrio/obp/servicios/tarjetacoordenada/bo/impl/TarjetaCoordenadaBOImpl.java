/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetacoordenada.bo.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.bo.impl.AutentificacionBOImpl;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.CoordenadasOperacionDTO;
import ar.com.santanderrio.obp.servicios.comun.challenge.entities.TipoDesafioEnum;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.tarjetacoordenada.bo.TarjetaCoordenadaBO;
import ar.com.santanderrio.obp.servicios.tarjetacoordenadas.entities.PedidoCoordenada;
import ar.com.santanderrio.obp.servicios.tarjetacoordenadas.entities.TarjetaCoordenada;
import ar.com.santanderrio.obp.servicios.tarjetacoordenadas.entities.ValidarCoordenada;
import ar.com.santanderrio.obp.servicios.tarjetascoordenadas.dao.TarjetaCoordenadaDAO;

/**
 * Clase TarjetaCoordenadaBOImpl.
 */
@Component
public class TarjetaCoordenadaBOImpl extends AutentificacionBOImpl implements TarjetaCoordenadaBO {

	/** Constante logger. */
	private static final Logger LOGGER = LoggerFactory.getLogger(TarjetaCoordenadaBOImpl.class);

	/** Variable tarjeta coordenada dao. */
	@Autowired
	private TarjetaCoordenadaDAO tarjetaCoordenadaDAO;

	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/** The Constant MSG_ERROR_SOLICITAR. */
	private static final String MSJ_ERROR_SOLICITAR = "Error al solicitar coordenadas. ";

	/** The Constant MSJ_ERROR_CLIENTE_SIN_COORDENADAS. */
	private static final String MSJ_ERROR_CLIENTE_SIN_COORDENADAS_HABILITADAS = "El usuario no tiene coordenadas habilitadas. ";

	/** The Constant MSJ_ERROR_CLIENTE_SIN_COORDENADAS. */
	private static final String MSJ_OK_CLIENTE_CON_COORDENADAS_HABILITADAS = "El usuario tiene coordenadas habilitadas. ";

	/** The Constant MSG_ERROR_VALIDAR. */
	private static final String MSJ_ERROR_VALIDAR = "Error al validar coordenadas. ";

	/** The Constant MSJ_SOLICITANDO_COORDENADAS. */
	private static final String MSJ_SOLICITANDO_COORDENADAS = "Solicitando coordenadas... ";

	/** The Constant MSJ_EJECUTANDO_COORDENADAS. */
	private static final String MSJ_EJECUTANDO_COORDENADAS = "Ejecutando y validando coordenadas... ";

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.tarjetacoordenada.bo.
	 * TarjetaCoordenadaBO#solicitarCoordenadas(ar.com.santanderrio.obp.
	 * servicios.clientes.entities.Cliente,
	 * ar.com.santanderrio.obp.servicios.tarjetacoordenadas.entities.
	 * PedidoCoordenada)
	 */
	@Override
	@Deprecated // acceder por medio del autentificadorManagerImpl
	public Respuesta<TarjetaCoordenada> solicitarCoordenadas(Cliente cliente, PedidoCoordenada pedidoCoordenada) {
		Respuesta<TarjetaCoordenada> respuesta = null;
		try {
			respuesta = tarjetaCoordenadaDAO.solicitarCoordenadas(cliente, pedidoCoordenada);
		} catch (DAOException e) {
			LOGGER.error(MSJ_ERROR_SOLICITAR, e);
			ItemMensajeRespuesta mensajeRespuesta = new ItemMensajeRespuesta(MSJ_ERROR_SOLICITAR);
			List<ItemMensajeRespuesta> mensajeList = new ArrayList<ItemMensajeRespuesta>();
			mensajeList.add(mensajeRespuesta);
			respuesta = getRespuestaFactory().crearRespuestaError(TarjetaCoordenada.class, mensajeList);
		}
		return respuesta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.tarjetacoordenada.bo.
	 * TarjetaCoordenadaBO#validarCoordenadas(ar.com.santanderrio.obp.servicios.
	 * clientes.entities.Cliente,
	 * ar.com.santanderrio.obp.servicios.tarjetacoordenadas.entities.
	 * ValidarCoordenada)
	 */
	@Override
	@Deprecated // acceder por medio del autentificadorManagerImpl
	public Respuesta<Boolean> validarCoordenadas(Cliente cliente, ValidarCoordenada validarCoordenada) {
		Respuesta<Boolean> respuesta = null;
		try {
			respuesta = tarjetaCoordenadaDAO.validarCoordenadas(cliente, validarCoordenada);
		} catch (DAOException e) {
			LOGGER.error(MSJ_ERROR_SOLICITAR, e);
			ItemMensajeRespuesta mensajeRespuesta = new ItemMensajeRespuesta(MSJ_ERROR_VALIDAR);
			List<ItemMensajeRespuesta> mensajeList = new ArrayList<ItemMensajeRespuesta>();
			mensajeList.add(mensajeRespuesta);
			respuesta = getRespuestaFactory().crearRespuestaError(Boolean.class, mensajeList);
		}
		return respuesta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.tarjetacoordenada.bo.
	 * TarjetaCoordenadaBO#solicitarCoordenadas(ar.com.santanderrio.obp.
	 * servicios.comun.autentificacion.entities.AutentificacionDTO)
	 */
	@Override
    public Respuesta<AutentificacionDTO> solicitarCoordenadas(AutentificacionDTO dto, String codigoEstadisticaSolicitud,
            boolean soloEstaVerificandoSiHayDesafios) {
        try {
            List<String> listaNups = tarjetaCoordenadaDAO.obtener();
            boolean nupClienteEnListado = false;
            for (String nup : listaNups) {
                if (nup.equals(getSesionCliente().getCliente().getNup())) {
                    nupClienteEnListado = true;
                    break;
                }   
            }
            
            LOGGER.info(MSJ_SOLICITANDO_COORDENADAS);
            if (!getSesionCliente().getCliente().tieneTarjetaCoordenadas() || nupClienteEnListado) {
                LOGGER.info(MSJ_ERROR_CLIENTE_SIN_COORDENADAS_HABILITADAS);
                return respuestaFactory.crearRespuestaWarning(TipoDesafioEnum.COORDENADAS.getId(),
                        TipoError.SIN_METODO_DESAFIO, CodigoMensajeConstantes.ADHERIR_METODO_DESAFIO_MENSAJE);
            } else {
                LOGGER.info(MSJ_OK_CLIENTE_CON_COORDENADAS_HABILITADAS);
                if (soloEstaVerificandoSiHayDesafios) {
                    if (dto != null && dto.getTipoDesafio() == null) {
                        dto.setTipoDesafio(TipoDesafioEnum.COORDENADAS);
                    }
                    Respuesta<AutentificacionDTO> respuesta = respuestaFactory
                            .crearRespuestaWarning(AutentificacionDTO.class, dto, TipoError.DESAFIO, null, "");
                    grabarEstadistica(codigoEstadisticaSolicitud, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
                    return respuesta;
                }
                Respuesta<CoordenadasOperacionDTO> respuestaDao = tarjetaCoordenadaDAO
                        .solicitarCoordenadas(getSesionCliente().getCliente(), dto.getCoordenadasOperacion());
                AutentificacionDTO autentificacionDTO = crearAutentificacionDTO(respuestaDao);
                grabarEstadisticaSolicitud(respuestaDao, codigoEstadisticaSolicitud);
                return crearRespuestaCoordenadas(autentificacionDTO, respuestaDao);
            }
        } catch (DAOException e) {
            LOGGER.error(MSJ_ERROR_SOLICITAR, e);
            return crearRespuestaErrorGenerico(codigoEstadisticaSolicitud);
        } catch (NullPointerException e) {
            LOGGER.error(MSJ_ERROR_SOLICITAR, e);
            return crearRespuestaErrorGenerico(codigoEstadisticaSolicitud);
        }
    }

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.tarjetacoordenada.bo.
	 * TarjetaCoordenadaBO#validarCoordenadas(ar.com.santanderrio.obp.servicios.
	 * comun.autentificacion.entities.AutentificacionDTO)
	 */
	@Override
	public Respuesta<AutentificacionDTO> validarCoordenadas(AutentificacionDTO dto,
			String codigoEstadisticaValidacion) {
		try {
			LOGGER.info(MSJ_EJECUTANDO_COORDENADAS);
			Respuesta<CoordenadasOperacionDTO> daoDTO = tarjetaCoordenadaDAO
					.validarCoordenadas(getSesionCliente().getCliente(), dto.getCoordenadasOperacion());

			AutentificacionDTO autentificacionDTO = crearAutentificacionDTO(daoDTO);

			if (esErrorDesafio(daoDTO)) {
				LOGGER.error(MSJ_ERROR_EJECUCION_COORDENADAS);
				autentificacionDTO.setValorNotificarRSA(false);
			}
			grabarEstadistica(daoDTO, codigoEstadisticaValidacion);
			return crearRespuestaCoordenadas(autentificacionDTO, daoDTO);
		} catch (DAOException e) {
			LOGGER.error(MSJ_ERROR_SOLICITAR, e);
			return crearRespuestaErrorGenerico(codigoEstadisticaValidacion);
		} catch (NullPointerException e) {
			LOGGER.error(MSJ_ERROR_SOLICITAR, e);
			return crearRespuestaErrorGenerico(codigoEstadisticaValidacion);
		}
	}

	/**
	 * Es error desafio.
	 *
	 * @param daoDTO
	 *            the dao DTO
	 * @return true, if successful
	 */
	private boolean esErrorDesafio(Respuesta<CoordenadasOperacionDTO> daoDTO) {
		return daoDTO.getEstadoRespuesta().equals(EstadoRespuesta.WARNING) && daoDTO.getItemsMensajeRespuesta().get(0)
				.getTipoError().equals(TipoError.ERROR_DESAFIO.getDescripcion());
	}

	/**
	 * Es reintentos agotados.
	 *
	 * @param daoDTO
	 *            the dao DTO
	 * @return true, if successful
	 */
	private boolean esReintentosAgotados(Respuesta<CoordenadasOperacionDTO> daoDTO) {
		if (EstadoRespuesta.WARNING.equals(daoDTO.getEstadoRespuesta())
				|| EstadoRespuesta.ERROR.equals(daoDTO.getEstadoRespuesta())) {
			return TipoError.ERROR_REINTENTOS_AGOTADOS.getDescripcion()
					.equals(daoDTO.getItemsMensajeRespuesta().get(0).getTipoError());
		}
		return false;
	}

	/**
	 * Obtener autentificacion DTO.
	 *
	 * @param daoDTO
	 *            the dao DTO
	 * @return the autentificacion DTO
	 */
	private AutentificacionDTO crearAutentificacionDTO(Respuesta<CoordenadasOperacionDTO> daoDTO) {
		AutentificacionDTO autentificacionDTO = new AutentificacionDTO();
		autentificacionDTO.setTipoDesafio(TipoDesafioEnum.COORDENADAS);
		autentificacionDTO.setCoordenadasOperacion(daoDTO.getRespuesta());
		autentificacionDTO.setReintentosAgotados(false);
		autentificacionDTO.setValorNotificarRSA(true);
		if (esReintentosAgotados(daoDTO)) {
			LOGGER.error(TipoError.ERROR_REINTENTOS_AGOTADOS.getDescripcion());
			autentificacionDTO.setValorNotificarRSA(false);
			autentificacionDTO.setReintentosAgotados(true);
		}
		return autentificacionDTO;
	}

	/**
	 * Grabar estadistica solicitud.
	 *
	 * @param daoDTO
	 *            the dao DTO
	 * @param codigoEstadistica
	 *            the codigo estadistica
	 */
	private void grabarEstadisticaSolicitud(Respuesta<CoordenadasOperacionDTO> daoDTO, String codigoEstadistica) {
		if (codigoEstadistica != null) {
			if (daoDTO.getEstadoRespuesta().equals(EstadoRespuesta.WARNING)) {
				grabarEstadistica(codigoEstadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			} else {
				grabarEstadistica(codigoEstadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			}
		} else {
			LOGGER.info(LAS_ESTADISTICAS_NO_FUERON_SETEADAS_ANTES_DE_CONSULTAR_EL_DESAFIO);
		}
	}

	/**
	 * Grabar estadistica validacion.
	 *
	 * @param daoDTO
	 *            the dao DTO
	 * @param codigoEstadistica
	 *            the codigo estadistica
	 */
	private void grabarEstadistica(Respuesta<CoordenadasOperacionDTO> daoDTO, String codigoEstadistica) {
		if (codigoEstadistica != null) {
			if (daoDTO.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
				grabarEstadistica(codigoEstadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			} else {
				grabarEstadistica(codigoEstadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			}
		} else {
			LOGGER.info(LAS_ESTADISTICAS_NO_FUERON_SETEADAS_ANTES_DE_CONSULTAR_EL_DESAFIO);
		}
	}

	/**
	 * Crear respuesta.
	 *
	 * @param autentificacionDTO
	 *            the autentificacion DTO
	 * @param daoDTO
	 *            the dao DTO
	 * @return the respuesta
	 */
	private Respuesta<AutentificacionDTO> crearRespuestaCoordenadas(AutentificacionDTO autentificacionDTO,
			Respuesta<CoordenadasOperacionDTO> daoDTO) {
		Respuesta<AutentificacionDTO> respuesta = new Respuesta<AutentificacionDTO>();
		respuesta.setRespuesta(autentificacionDTO);
		respuesta.setEstadoRespuesta(daoDTO.getEstadoRespuesta());
		respuesta.setItemMensajeRespuesta(daoDTO.getItemsMensajeRespuesta());
		respuesta.setRespuestaVacia(daoDTO.isRespuestaVacia());
		return respuesta;
	}



}
