package ar.com.santanderrio.obp.servicios.debin.manager;

import org.apache.commons.lang.StringUtils;
import org.apache.cxf.jaxrs.ext.MessageContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.CoordenadasOperacionDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.manager.AutentificacionManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.debin.bo.DebinTokenBOImpl;
import ar.com.santanderrio.obp.servicios.debin.entities.DebinView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.login.dto.CredencialesMya;
import ar.com.santanderrio.obp.servicios.tarjetacoordenadas.entities.PedidoCoordenada;
import ar.com.santanderrio.obp.servicios.tarjetacoordenadas.entities.TarjetaCoordenadaOperacionEnum;
import ar.com.santanderrio.obp.servicios.token.externos.AbstractTokenEntesExternosManager;
import ar.com.santanderrio.obp.servicios.token.externos.TokenExternoDTO;
import ar.com.santanderrio.obp.servicios.token.externos.view.TokenView;

/**
 * The Class DebinTokenManagerImpl.
 *
 * @author sergio.e.goldentair
 */
@Component("debinTokenManager")
public class DebinTokenManagerImpl extends AbstractTokenEntesExternosManager implements DebinTokenManager {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(DebinTokenManagerImpl.class);

	/** The obe token. */
	@Autowired
	private DebinTokenBOImpl debinTokenBO;
	
	/** The sesion cliente. */
	@Autowired
	private SesionCliente sesionCliente;
	
	/** The sesion parametros. */
	@Autowired
	private SesionParametros sesionParametros;
	
	/** The autentificacion manager. */
	@Autowired
	private AutentificacionManager autentificacionManager;

    /** The respuestaFactory. */
    @Autowired
    private RespuestaFactory respuestaFactory;

    /** The valor desafio. */
    @Value("${TRJCOORD.OPERAINDISTINTO.DEBIN}")
    private Integer desafioDebin;

    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.debin.manager.DebinTokenManager#obtenerTokenEncriptado(ar.com.santanderrio.obp.servicios.debin.entities.DebinView, org.apache.cxf.jaxrs.ext.MessageContext)
     */
    @Override
    public Respuesta<DebinView> obtenerTokenEncriptado(DebinView debinView, MessageContext mc) {
        AutentificacionDTO autentificacionDTO;

        Respuesta<DebinView> estadoDesafio = autentificacionManager.verificarEstadoDesafio(debinView.getDesafio(),
                desafioDebin);
        if (EstadoRespuesta.OK.equals(estadoDesafio.getEstadoRespuesta())) {
            autentificacionDTO = debinView.getDesafio();
        } else if (EstadoRespuesta.WARNING.equals(estadoDesafio.getEstadoRespuesta())) {
            autentificacionDTO = generarAutentificacionDTO(debinView);
        } else {
            return estadoDesafio;
        }

        Respuesta<AutentificacionDTO> rstaAutentificacionDTO = autentificacionManager
                .ejecutarValidacionRSA(autentificacionDTO);
        debinView.setDesafio(rstaAutentificacionDTO.getRespuesta());
        Respuesta<DebinView> respuestaEjecutarValidacion = respuestaFactory.transformar(debinView,
                rstaAutentificacionDTO);
        if (!EstadoRespuesta.OK.equals(respuestaEjecutarValidacion.getEstadoRespuesta())) {
            return respuestaEjecutarValidacion;
        }
        LOGGER.info("Validacion de token correcta para saltar a prisma (DEBIN).");
        TokenExternoDTO tokenExternoDTO = new TokenExternoDTO(sesionCliente.getCliente());
        Respuesta<TokenView> respuestaToken = this.armarRespuesta(mc, debinTokenBO, tokenExternoDTO);

        Respuesta<DebinView> respuestaDebin = respuestaFactory.crearRespuestaOk(DebinView.class);
        DebinView respuestaView = new DebinView();
        respuestaView.setTipoOperacion(debinView.getTipoOperacion());
        respuestaView.setTokenView(respuestaToken.getRespuesta());
        respuestaDebin.setRespuesta(respuestaView);
        //estadisticaManager.add(EstadisticasConstants.ACCESO_PORTAL_DEBIN, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        return respuestaDebin;
    }

    /**
	 * Generar autentificacion DTO.
	 *
	 * @param datosConfirmacionRecarga
	 *            the datos confirmacion recarga
	 * @return the autentificacion DTO
	 */
    private AutentificacionDTO generarAutentificacionDTO(DebinView datosConfirmacionRecarga) {
        LOGGER.info("Se carga la informacion de autentificationDTO para Debin.");
        AutentificacionDTO autentificacionDTO = new AutentificacionDTO();
        autentificacionDTO.setOperacion(desafioDebin);
        autentificacionDTO.setCoordenadasOperacion(new CoordenadasOperacionDTO(
                new PedidoCoordenada(TarjetaCoordenadaOperacionEnum.VALIDACION, "192168001001")));
        // Estadisticas
        autentificacionDTO.setCodigoEstadisticaSolicitudToken(EstadisticasConstants.SOLICITUD_TOKEN_DEBIN);
        autentificacionDTO.setCodigoEstadisticaValidacionToken(EstadisticasConstants.VALIDACION_TOKEN_DEBIN);
        autentificacionDTO.setCodigoEstadisticaSolicitudCoordenadas(EstadisticasConstants.SOLICITUD_COORDENADAS_DEBIN);
        autentificacionDTO
                .setCodigoEstadisticaValidacionCoordenadas(EstadisticasConstants.VALIDACION_COORDENADAS_DEBIN);
        CredencialesMya credencialesMya = sesionParametros.getCredencialesMya();
        boolean isCelular = credencialesMya != null && StringUtils.isNotBlank(credencialesMya.getCelular());
        datosConfirmacionRecarga.setCelularMyA(isCelular);
        autentificacionDTO.setRsaDTO(datosConfirmacionRecarga);
        return autentificacionDTO;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.debin.manager.DebinTokenManager#
     * habilitado()
     */
    @Override
    public Respuesta<Boolean> habilitado() {
        LOGGER.info("Validar si el usuario tiene los desafios previo a solicitarlos para Debin.");
        AutentificacionDTO autentificacionDTO = new AutentificacionDTO();
        autentificacionDTO.setOperacion(desafioDebin);
        return autentificacionManager.tieneAlgunDesafioHabilitadoParaLaOperacion(autentificacionDTO);
    }

}
