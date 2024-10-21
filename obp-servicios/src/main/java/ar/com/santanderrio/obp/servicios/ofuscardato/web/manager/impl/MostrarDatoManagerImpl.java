package ar.com.santanderrio.obp.servicios.ofuscardato.web.manager.impl;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.comun.OperacionesRSAEnum;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.web.manager.ClienteManager;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.CoordenadasOperacionDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.manager.AutentificacionManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.ofuscardato.dto.MostrarDatoDTO;
import ar.com.santanderrio.obp.servicios.ofuscardato.web.manager.MostrarDatoManager;
import ar.com.santanderrio.obp.servicios.ofuscardato.web.view.MailDesafioView;
import ar.com.santanderrio.obp.servicios.ofuscardato.web.view.MostrarMailView;
import ar.com.santanderrio.obp.servicios.tarjetacoordenadas.entities.PedidoCoordenada;
import ar.com.santanderrio.obp.servicios.tarjetacoordenadas.entities.TarjetaCoordenadaOperacionEnum;

@Component
public class MostrarDatoManagerImpl implements MostrarDatoManager {

	/** The logger. */
	private static final Logger LOGGER = LoggerFactory.getLogger(MostrarDatoManagerImpl.class);
	
	/** The autentificacion manager. */
	@Autowired
	private AutentificacionManager autentificacionManager;
	
	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;
	
	/** The valor desafio. */
	@Value("${TRJCOORD.OPERAINDISTINTO.MAILOFUSCADO}")
	private String valorDesafioMailOfuscado;
	
	/** The sesion parametros. */
    @Autowired
    private SesionParametros sesionParametros;
    
    @Autowired
    private ClienteManager clienteManager;
    
    @Autowired
    private SesionCliente sesionCliente;
	
	/** 
	 * Obtiene el mail del cliente sin ofuscar.
	 * @param mailDesafioView
	 * 
	 * */
	@Override
	public Respuesta<MostrarMailView> obtenerMailSinOfuscar(MailDesafioView mailDesafioView) {

		AutentificacionDTO autentificacionDTO;

		Respuesta<MostrarMailView> estadoDesafio = autentificacionManager
				.verificarEstadoDesafio(mailDesafioView.getDesafio(), Integer.parseInt(valorDesafioMailOfuscado));
		if (EstadoRespuesta.OK.equals(estadoDesafio.getEstadoRespuesta())) {
			autentificacionDTO = mailDesafioView.getDesafio();
		} else if (EstadoRespuesta.WARNING.equals(estadoDesafio.getEstadoRespuesta())) {
			autentificacionDTO = cargarAutentificacionDTO();
		} else {
			return estadoDesafio;
		}
		
		Respuesta<AutentificacionDTO> respEjecucionMetodoAutentificacion = autentificacionManager.ejecutarValidacionRSA(autentificacionDTO);
		
		MostrarMailView respuesta = new MostrarMailView();
		respuesta.setDesafio(respEjecucionMetodoAutentificacion.getRespuesta());
		
		if(EstadoRespuesta.ERROR.equals(respEjecucionMetodoAutentificacion.getEstadoRespuesta())) { // DENY
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO_OPEN_STACK, CodigoMensajeConstantes.ERROR_TOTAL);
		}
		
		if(EstadoRespuesta.WARNING.equals(respEjecucionMetodoAutentificacion.getEstadoRespuesta())) { // DENY
			return respuestaFactory.transformar(respuesta, respEjecucionMetodoAutentificacion);
		} 
		
		//session mail
		String email = sesionParametros.getCredencialesMya().getEmail();
		String emailSecundario = sesionParametros.getCredencialesMya().getEmailSecundario();
		
		LOGGER.debug("Mail cliente desofuscado: " + email);
		LOGGER.debug("Mail secuendario cliente desofuscado: " + emailSecundario);
		
		respuesta.setMailPrincipal(email);
		respuesta.setMailSecundario(emailSecundario);
		respuesta.setIsMail(true);
		respuesta.setOpenStack(true);
		
		return respuestaFactory.transformar(respuesta, respEjecucionMetodoAutentificacion);

	}
	
	/**
	 * Cargamos datos para el desafío
	 * @param confirmarClienteVenta
	 * @param ventaDTO
	 * @return
	 */
	private AutentificacionDTO cargarAutentificacionDTO() {
		
		Integer operacion = Integer.parseInt(valorDesafioMailOfuscado);
        AutentificacionDTO autentificacionDTO = new AutentificacionDTO();
        autentificacionDTO.setOperacion(operacion);
        autentificacionDTO.setCoordenadasOperacion(new CoordenadasOperacionDTO(
                new PedidoCoordenada(TarjetaCoordenadaOperacionEnum.VALIDACION, "192168001001")));

        OperacionesRSAEnum tipoOperacion = OperacionesRSAEnum.VENTA_USD;
        
        MostrarDatoDTO mostrarDatoRsaDTO = new MostrarDatoDTO(tipoOperacion);
        cargarDatosClaveToken(mostrarDatoRsaDTO);
        autentificacionDTO.setRsaDTO(mostrarDatoRsaDTO);
        
        //Estaditicas
        autentificacionDTO.setCodigoEstadisticaSolicitudCoordenadas(EstadisticasConstants.SOLUCITUD_COORDENADAS_MOSTAR_MAIL);
		autentificacionDTO.setCodigoEstadisticaValidacionCoordenadas(EstadisticasConstants.VALIDACION_COORDENADAS_MOSTAR_MAIL);
		autentificacionDTO.setCodigoEstadisticaSolicitudToken(EstadisticasConstants.SOLUCITUD_TOKEN_MOSTAR_MAIL);
		autentificacionDTO.setCodigoEstadisticaValidacionToken(EstadisticasConstants.VALIDACION_TOKEN_MOSTAR_MAIL);
        
        return autentificacionDTO;
	}
	
	private void cargarDatosClaveToken(MostrarDatoDTO mostrarDatoRsaDTO) {
    	Respuesta<List<BigDecimal>> antiguedades = clienteManager.obtenerAntiguedadDiasUltCambioClaveToken(Long.valueOf(sesionCliente.getCliente().getNup()));
		if(antiguedades != null && antiguedades.getRespuesta() != null && !antiguedades.getRespuesta().isEmpty()) {
			if(antiguedades.getRespuesta().get(0) != null) {
				mostrarDatoRsaDTO.setCantDiasUltimoCambioClave(antiguedades.getRespuesta().get(0).intValue());
			}
			if(antiguedades.getRespuesta().get(1) != null) {
				mostrarDatoRsaDTO.setCantDiasUltimoCambioToken(antiguedades.getRespuesta().get(1).intValue());
			}
		}
    }

	

}
