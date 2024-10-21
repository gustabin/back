package ar.com.santanderrio.obp.servicios.inversiones.ventameprsa.bo;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.bo.ClienteBO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.CoordenadasOperacionDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.manager.AutentificacionManager;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.inversiones.ventameprsa.dao.VentaMepRSADAO;
import ar.com.santanderrio.obp.servicios.inversiones.ventameprsa.dto.MepRsaDTO;
import ar.com.santanderrio.obp.servicios.inversiones.ventameprsa.entity.MepRsaNotification;
import ar.com.santanderrio.obp.servicios.inversiones.ventameprsa.entity.VentaMepRequest;
import ar.com.santanderrio.obp.servicios.tarjetacoordenadas.entities.PedidoCoordenada;
import ar.com.santanderrio.obp.servicios.tarjetacoordenadas.entities.TarjetaCoordenadaOperacionEnum;

@Component
public class VentaMepRsaBOImpl implements VentaMepRsaBO{
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(VentaMepRsaBOImpl.class);
	
	@Autowired VentaMepRSADAO vtamepRsaDao;
	
	@Autowired
    private RespuestaFactory respuestaFactory;

	@Autowired
    private EstadisticaManager estadisticaManager;
	
	@Autowired
	protected SesionCliente sesionCliente;
	
	@Autowired
	private AutentificacionManager autentificacionManager;
	
	@Autowired
	private ClienteBO clienteBO;
	

	@Override
	public MepRsaNotification notificarVtaMep(VentaMepRequest request) {		
		
		MepRsaNotification res = new MepRsaNotification();		
		Respuesta<MepRsaDTO> resp = ejecutarValidacionRSA(this.transformMepRequest(request));
		LOGGER.info(resp.toString());
		if(EstadoRespuesta.OK.equals(resp.getEstadoRespuesta())){
			res.notified = true;
		}else {			
			res.notified = false;			
		}		
		
		return res;		
	}
	
	private MepRsaDTO transformMepRequest(VentaMepRequest req) {
		MepRsaDTO dto = new MepRsaDTO(true,"");
		dto.setAmount(req.getAmount());
		dto.setAccount(req.getOperativeAccount());
		return dto;
	}
	
	private Respuesta<MepRsaDTO> ejecutarValidacionRSA(MepRsaDTO mepRsaDto) {
		LOGGER.info("INICIO validacion por RSA - VENTA MEP");
		AutentificacionDTO autentificacionDTO;

		Respuesta<MepRsaDTO> estadoDesafio = autentificacionManager.verificarEstadoDesafio(mepRsaDto.getDesafio(),2);
		LOGGER.info("*ESTADO DESAFIO: "+estadoDesafio);
		LOGGER.info("*estadoDesafio.getEstadoRespuesta(): "+estadoDesafio.getEstadoRespuesta());

		if (EstadoRespuesta.OK.equals(estadoDesafio.getEstadoRespuesta())) {
			autentificacionDTO = mepRsaDto.getDesafio();
		} else if ( EstadoRespuesta.WARNING.equals(estadoDesafio.getEstadoRespuesta())) {
			autentificacionDTO = generarAutentificacionDTO(mepRsaDto);
		} else {
			return estadoDesafio;
		}
		autentificacionDTO.setValorNotificarRSA(true);
		// Transformacion respuesta RSA
		Respuesta<AutentificacionDTO> rstaAutentificacion = autentificacionManager
				.ejecutarValidacionRSA(autentificacionDTO);
		mepRsaDto.setDesafio(rstaAutentificacion.getRespuesta());

		Respuesta<MepRsaDTO> respuesta = respuestaFactory.transformar(mepRsaDto, rstaAutentificacion);

		LOGGER.info("FIN validacion por RSA - VENTA MEP");
		return respuesta;
	}
	
	private AutentificacionDTO generarAutentificacionDTO(MepRsaDTO mepRsaDto) {
		LOGGER.info("Se carga la informacion de autentificationDTO");
		Integer operacion = 2;
		AutentificacionDTO autentificacionDTO = new AutentificacionDTO();
		autentificacionDTO.setOperacion(operacion);
		autentificacionDTO.setCoordenadasOperacion(new CoordenadasOperacionDTO(
				new PedidoCoordenada(TarjetaCoordenadaOperacionEnum.VALIDACION, "192168001001")));

		// Carga de estadisticas
			autentificacionDTO.setCodigoEstadisticaSolicitudToken(EstadisticasConstants.INGRESO_FUNCION_VENTA_TITULOS_VALORES);
				//Datos informados a RSA		
			
		cargarUltimosDiasDesdeCambioDeClaveToken(mepRsaDto);
		autentificacionDTO.setRsaDTO(mepRsaDto);

		return autentificacionDTO;
	}
	
	public void cargarUltimosDiasDesdeCambioDeClaveToken(MepRsaDTO mepRSADTO) {

		try {
			Respuesta<List<BigDecimal>> antiguedades = clienteBO.obtenerAntiguedadDiasUltCambioClaveToken(Long.valueOf(sesionCliente.getCliente().getNup()));
			if(EstadoRespuesta.OK.equals(antiguedades.getEstadoRespuesta())) {
				estadisticaManager.add(EstadisticasConstants.CNS_CLIENTE_CONTROL_SEGURIDAD, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
				if(antiguedades.getRespuesta() != null && !antiguedades.getRespuesta().isEmpty()) {
					if(antiguedades.getRespuesta().get(0) != null) {
						mepRSADTO.setCantDiasUltimoCambioClave(antiguedades.getRespuesta().get(0).intValue());
						LOGGER.info("Valor de ultimos dias cambio clave: {}", antiguedades.getRespuesta().get(0).intValue());
					}
					if(antiguedades.getRespuesta().get(1) != null) {
						mepRSADTO.setCantDiasUltimoCambioToken(antiguedades.getRespuesta().get(1).intValue());
						LOGGER.info("Valor de ultimos dias cambio token: {}", antiguedades.getRespuesta().get(1).intValue());
					}
				}
			} else {
				estadisticaManager.add(EstadisticasConstants.CNS_CLIENTE_CONTROL_SEGURIDAD, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			}
		} catch (NumberFormatException e) {
			LOGGER.info("ERROR: No se ha podido recuperar antigüedad de cambio de clave y token.", e);
		} catch (BusinessException e) {
			LOGGER.info("ERROR: No se ha podido recuperar antigüedad de cambio de clave y token.", e);
		}

	}


	


}
