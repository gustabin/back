package ar.com.santanderrio.obp.servicios.delete.account.web.manager.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.base.webservice.GestionarWS;
import ar.com.santanderrio.obp.generated.webservices.discador.client.IClickToCall;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.delete.account.dao.DiscadorDAO;
import ar.com.santanderrio.obp.servicios.delete.account.web.dto.DiscadorDto;
import ar.com.santanderrio.obp.servicios.delete.account.web.dto.DiscadorRtaDto;
import ar.com.santanderrio.obp.servicios.delete.account.web.dto.GuardarInfoClienteDto;
import ar.com.santanderrio.obp.servicios.delete.account.web.manager.DiscadorWebManager;
import ar.com.santanderrio.obp.servicios.delete.account.web.view.DiscadorView;
import ar.com.santanderrio.obp.servicios.delete.account.web.view.SolicitudDiscadorView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.producto.bo.BajaProductoBO;
import ar.com.santanderrio.obp.servicios.producto.dto.ObtenerBajaProductoDTO;
import ar.com.santanderrio.obp.servicios.producto.dto.ProductoDTO;

@Component
public class DiscadorWebManagerImpl implements DiscadorWebManager {

	private static final Logger LOGGER = LoggerFactory.getLogger(DiscadorWebManagerImpl.class);

	@Autowired
	private SesionCliente sesionCliente;
	
	@Autowired
	@Qualifier("clickToCallWS")
	private GestionarWS<IClickToCall> clickToCallClient;
	
	@Autowired
	private BajaProductoBO bajaProductoBO;
	
	@Autowired
	private RespuestaFactory respuestaFactory;
	
	@Autowired
	private	DiscadorDAO discadorDAO;
	
	@Autowired
	private MensajeBO mensajeBO;
	
	@Autowired
	private SesionParametros sesionParametros;
	
	@Override
	public Respuesta<DiscadorView> solicitarDiscador(SolicitudDiscadorView inView) {
		
		Respuesta<DiscadorView> respuesta = new Respuesta<DiscadorView>();
		DiscadorView outView = new DiscadorView();
		
		Cliente cliente = sesionCliente.getCliente();
		
		LOGGER.info("Iniciando solicitud discador");
		
		String comprobante = "";
		
		GuardarInfoClienteDto infoCliente = new GuardarInfoClienteDto();
		infoCliente.setComentario(inView.getComentario());
		infoCliente.setLlamar(inView.getLlamar());
		infoCliente.setMotivo(inView.getMotivo());
		infoCliente.setNup(cliente.getNup());
		
		String descripcionCta = getDescripcion(inView.getCuenta());
		sesionParametros.setDescCtaAEliminar(descripcionCta);
		
		if (inView.getLlamar().equalsIgnoreCase("Si")) {
			
			if(Boolean.valueOf(inView.getIsSelect())) {
				LOGGER.info("Cliente select, continua el flujo.");
				respuesta = respuestaFactory.crearRespuestaOk(DiscadorView.class);
			} else {
				
				LOGGER.info("No es select, se llama al discador");
				IClickToCall clickToCall = null;

				DiscadorDto discador = new DiscadorDto();
				discador.setNup(cliente.getNup());
				discador.setApellido(cliente.getApellido1());
				discador.setCodCampania("");
				discador.setNombre(cliente.getNombre());
				discador.setNumDoc(cliente.getNroDocCnsDocXNup());
				discador.setTipoDoc(cliente.getTipoDocCnsDocXNup());
			
				Gson gson = new Gson();
				String reqJson = gson.toJson(discador);
				String response = null;
				
				try {
					clickToCall = clickToCallClient.obtenerPort();
					response = clickToCall.grabarDatosClickToCallSinFirma(reqJson);
				} catch (DAOException e) {
					LOGGER.error("Ocurrió un error al llamar al discador", e);
				}
				
				DiscadorRtaDto rtaDiscador = gson.fromJson(response, DiscadorRtaDto.class);
				
				comprobante = getComprobante();
				if(rtaDiscador != null && !rtaDiscador.getResultado().equalsIgnoreCase("0")) {
					return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_GENERICO, CodigoMensajeConstantes.BAJA_CUENTA_ERROR_DISCADOR);
				}
			}
			
			infoCliente.setComprobante(comprobante);
			
			outView.setNumeroCuenta(inView.getCuenta());
			outView.setFechaHora(new SimpleDateFormat("dd/MM/yyyy - HH:mm").format(new Date()));
			outView.setNroDeComprobante(comprobante);
			outView.setMensajeFeedback(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.BAJA_CUENTA_SOLICITUD_DISCADOR_OK).getMensaje());
			
			respuesta = respuestaFactory.crearRespuestaOk(outView);
			
		} else {
			respuesta = respuestaFactory.crearRespuestaOk(DiscadorView.class);
		}
		
		try {
			LOGGER.info("Se guardan los datos en la tabla");
			discadorDAO.guardarInfoDiscador(infoCliente);
		} catch (DAOException e) {
			LOGGER.error("Ocurrió un error al guardar los datos en la tabla", e);
		}
		
		LOGGER.info("Fin proceso discador");
		return respuesta;
	}

	private String getDescripcion(String cuenta) {
		ObtenerBajaProductoDTO prod = bajaProductoBO.obtenerProductosPaquetesCuenta().getRespuesta();
		
		for (ProductoDTO prodDto : prod.getProductos()) {
			if(prodDto.getNumero().equalsIgnoreCase(cuenta)) {
				return prodDto.getDescripcion();
			}
		}
		
		return "";
	}

	private String getComprobante() {
		Date comprobante = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		return sdf.format(comprobante);
	}	
}

