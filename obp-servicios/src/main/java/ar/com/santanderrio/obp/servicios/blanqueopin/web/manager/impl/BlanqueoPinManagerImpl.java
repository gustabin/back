package ar.com.santanderrio.obp.servicios.blanqueopin.web.manager.impl;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.blanqueopin.bo.BlanqueoPinBO;
import ar.com.santanderrio.obp.servicios.blanqueopin.dto.BlanqueoPinRSADTO;
import ar.com.santanderrio.obp.servicios.blanqueopin.entities.BlanqueoPinEnum;
import ar.com.santanderrio.obp.servicios.blanqueopin.web.manager.BlanqueoPinManager;
import ar.com.santanderrio.obp.servicios.blanqueopin.web.view.BlanqueoPinInicioView;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.web.manager.ClienteManager;
import ar.com.santanderrio.obp.servicios.comun.ContadorIntentos;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.CoordenadasOperacionDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.manager.AutentificacionManager;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.mensajes.web.manager.MensajeManager;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.perfil.view.BlanqueoPinView;
import ar.com.santanderrio.obp.servicios.tarjetacoordenadas.entities.PedidoCoordenada;
import ar.com.santanderrio.obp.servicios.tarjetacoordenadas.entities.TarjetaCoordenadaOperacionEnum;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.DetalleTarjetaDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.web.view.TarjetaView;

@Component
public class BlanqueoPinManagerImpl implements BlanqueoPinManager {

	private static final Logger LOGGER = LoggerFactory.getLogger(BlanqueoPinManagerImpl.class);
	 
	@Autowired
	private BlanqueoPinBO blanqueoPinBO;

	@Autowired
	private SesionCliente sesionCliente;

	@Autowired
	private MensajeManager msjManager;

	@Autowired
	private RespuestaFactory respuestaFactory;
	
    @Autowired
    private AutentificacionManager autentificacionManager;
    
    @Autowired
    private SesionParametros sesionParametros;
    
    @Autowired
    private EstadisticaManager estadisticaManager;
    
	@Autowired
	private ClienteManager clienteManager;
    
    @Value("${TRJCOORD.OPERAINDISTINTO.BLANQUEOPINES}")
    private String valorDesafio;

    
	@Override
	public Respuesta<BlanqueoPinInicioView> inicioBlanqueoPin() {
		BlanqueoPinInicioView blanqueoInicioView = new BlanqueoPinInicioView();
		List<DetalleTarjetaDTO> listaTarjetasDTO = blanqueoPinBO.obtenerTarjetasBanelco(sesionCliente.getCliente());
		blanqueoInicioView.setTarjetas(obtenerTarjetasView(listaTarjetasDTO));
		blanqueoInicioView.setMensajeInicio(
				msjManager.obtenerMensajePorCodigo(CodigoMensajeConstantes.BLANQUEOPIN_INICIO).getMensaje());
		blanqueoInicioView.setMensajeClaveNumerica(
				msjManager.obtenerMensajePorCodigo(CodigoMensajeConstantes.BLANQUEOPIN_NUMERICA).getMensaje());
		blanqueoInicioView.setMensajeClaveAlfabetica(
				msjManager.obtenerMensajePorCodigo(CodigoMensajeConstantes.BLANQUEOPIN_ALFABETICA).getMensaje());
		blanqueoInicioView.setMensajeAmbasClaves(
				msjManager.obtenerMensajePorCodigo(CodigoMensajeConstantes.BLANQUEOPIN_AMBAS).getMensaje());
		return respuestaFactory.crearRespuestaOk(blanqueoInicioView);
	}

	private List<TarjetaView> obtenerTarjetasView(List<DetalleTarjetaDTO> listaTarjetasDTO) {
		List<TarjetaView> tarjetasView = new ArrayList<TarjetaView>();
		for (DetalleTarjetaDTO dto : listaTarjetasDTO) {
			TarjetaView tarjetaView = new TarjetaView();
			tarjetaView.setMarca(dto.getMarca());
			tarjetaView.setNumero(dto.getNroTarjetaConFormato());
			tarjetasView.add(tarjetaView);
		}
		return tarjetasView;
	}

	@Override
	public Respuesta<BlanqueoPinView> blanquearPinRSA(BlanqueoPinView blanqueoPin) {
		
		Cliente cliente = sesionCliente.getCliente();
		Respuesta<BlanqueoPinView> respuestaRSA = ejecutarValidacionRSA(blanqueoPin);
		Respuesta<BlanqueoPinView> respuesta;
		
        LOGGER.info("Ejecutando validacion de RSA con respuesta: {}.", respuestaRSA);
        if (!EstadoRespuesta.OK.equals(respuestaRSA.getEstadoRespuesta())) {
            return respuestaRSA;
        }

		inicializarReintentos();

        Boolean resultado = Boolean.FALSE;
		try {
			resultado = blanqueoPinBO.blanquearPin(cliente, blanqueoPin, sesionCliente.getIpCliente());
		} catch (BusinessException e) {
			estadisticaManager.add(EstadisticasConstants.BLANQUEO_PINES_FEEDBACK, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
        	return respuestaFactory.crearRespuestaError("", TipoError.BLANQUEO_PIN_TIME_OUT, "1841");
		}
			
		if (resultado) {
			sesionParametros.setPrimerAcceso(true);
			respuesta = armarRespuestaOK(blanqueoPin);
			estadisticaManager.add(EstadisticasConstants.BLANQUEO_PINES_FEEDBACK, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		} else if (sesionParametros.getContador().permiteReintentar()) {
			estadisticaManager.add(EstadisticasConstants.BLANQUEO_PINES_FEEDBACK, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			sesionParametros.setPrimerAcceso(false);
			respuesta = respuestaFactory.crearRespuestaError("", TipoError.BLANQUEO_PIN_PUEDE_REINTENTAR, "1840");
		} else {
			sesionParametros.setPrimerAcceso(true);
			estadisticaManager.add(EstadisticasConstants.BLANQUEO_PINES_FEEDBACK, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
        	respuesta = respuestaFactory.crearRespuestaError("", TipoError.BLANQUEO_PIN_REINTENTOS_AGOTADOS, "1840");
        }
		
        return respuesta;
	}
	
	private Respuesta<BlanqueoPinView> ejecutarValidacionRSA(BlanqueoPinView blanqueoPin) {
		
        AutentificacionDTO autentificacionDTO;
       
        Respuesta<BlanqueoPinView> estadoDesafio = autentificacionManager.verificarEstadoDesafio(blanqueoPin.getDesafio(),
                Integer.parseInt(valorDesafio));
        if (EstadoRespuesta.OK.equals(estadoDesafio.getEstadoRespuesta())) {
            autentificacionDTO = blanqueoPin.getDesafio();
        } else  {
            autentificacionDTO = generarAutentificacionDTO(blanqueoPin.getTipoBlanqueo());
        } 

        Respuesta<AutentificacionDTO> rstaAutentificacion = autentificacionManager
                .ejecutarValidacionRSA(autentificacionDTO);
        blanqueoPin.setDesafio(rstaAutentificacion.getRespuesta());

        return respuestaFactory.transformar(blanqueoPin, rstaAutentificacion);
		
	}
	
    private AutentificacionDTO generarAutentificacionDTO(String tipoBlanqueo) {
        Integer operacion = Integer.parseInt(valorDesafio);
        AutentificacionDTO autentificacionDTO = new AutentificacionDTO();
        autentificacionDTO.setOperacion(operacion);
        autentificacionDTO.setCoordenadasOperacion(new CoordenadasOperacionDTO(
                new PedidoCoordenada(TarjetaCoordenadaOperacionEnum.VALIDACION, "192168001001")));

        // Seteo de estadisticas de RSA
        autentificacionDTO
        	.setCodigoEstadisticaSolicitudCoordenadas(EstadisticasConstants.SOLUCITUD_COORDENADAS_BLANQUEO_PINES);
        autentificacionDTO
        	.setCodigoEstadisticaValidacionCoordenadas(EstadisticasConstants.VALIDACION_COORDENADAS_BLANQUEO_PINES);
        autentificacionDTO
        	.setCodigoEstadisticaValidacionToken(EstadisticasConstants.VALIDACION_TOKEN_BLANQUEO_PINES);
        
        // Datos informados a RSA
        BlanqueoPinRSADTO blanqueoPinRsaDto = new BlanqueoPinRSADTO(operacion, BlanqueoPinEnum.buscarEnumPorTipo(tipoBlanqueo));
        
        cargarDatosClaveToken(blanqueoPinRsaDto);
        autentificacionDTO.setRsaDTO(blanqueoPinRsaDto);

        return autentificacionDTO;
    }
	
	private void cargarDatosClaveToken(BlanqueoPinRSADTO blanqueoPinRsaDto) {
    	Respuesta<List<BigDecimal>> antiguedades = clienteManager.obtenerAntiguedadDiasUltCambioClaveToken(Long.valueOf(sesionCliente.getCliente().getNup()));
		if(antiguedades != null && antiguedades.getRespuesta() != null && !antiguedades.getRespuesta().isEmpty()) {
			if(antiguedades.getRespuesta().get(0) != null) {
				blanqueoPinRsaDto.setCantDiasUltimoCambioClave(antiguedades.getRespuesta().get(0).intValue());
			}
			if(antiguedades.getRespuesta().get(1) != null) {
				blanqueoPinRsaDto.setCantDiasUltimoCambioToken(antiguedades.getRespuesta().get(1).intValue());
			}
		}
    }
    
    private Respuesta<BlanqueoPinView> armarRespuestaOK(BlanqueoPinView blanqueoPin) {
    	
    	BlanqueoPinView blanqueoPinView;
    	BlanqueoPinEnum blanqueoPinEnum = BlanqueoPinEnum.buscarEnumPorTipo(blanqueoPin.getTipoBlanqueo());

    	if (BlanqueoPinEnum.NUMERICO.equals(blanqueoPinEnum)) {
    		blanqueoPinView = armarObjetoOK(CodigoMensajeConstantes.BLANQUEOPIN_OK_NUMERICA, 
    				CodigoMensajeConstantes.BLANQUEOPIN_INF_UNA_CLAVE, blanqueoPin.getNumeroTarjeta());
    	} else if (BlanqueoPinEnum.ALFABETICO.equals(blanqueoPinEnum)) {
    		blanqueoPinView = armarObjetoOK(CodigoMensajeConstantes.BLANQUEOPIN_OK_ALFABETICA, 
    				CodigoMensajeConstantes.BLANQUEOPIN_INF_UNA_CLAVE, blanqueoPin.getNumeroTarjeta());
    	} else {
    		blanqueoPinView = armarObjetoOK(CodigoMensajeConstantes.BLANQUEOPIN_OK_AMBAS_CLAVES, 
    				CodigoMensajeConstantes.BLANQUEOPIN_INF_AMBAS_CLAVES, blanqueoPin.getNumeroTarjeta());
    	}
    	return respuestaFactory.crearRespuestaOk(blanqueoPinView);
    }
    
    private BlanqueoPinView armarObjetoOK (String codigoMensajeOK, String codigoInfOK, String nroTarjeta) {
    	BlanqueoPinView blanqueoPinView = new BlanqueoPinView();

		String mensaje = msjManager.obtenerMensajePorCodigo(codigoMensajeOK).getMensaje();
		blanqueoPinView.setMensajeBlanqueoExitoso(MessageFormat.format(mensaje, nroTarjeta));
		blanqueoPinView.setMensajeInformativo(msjManager.obtenerMensajePorCodigo(codigoInfOK).getMensaje());
    	
    	return blanqueoPinView;
    }
    
    private void inicializarReintentos() {
        if (sesionParametros.getPrimerAcceso()) {
            sesionParametros.setContador(new ContadorIntentos(3));
        }
    }

}
