/*
 * 
 */
package ar.com.santanderrio.obp.servicios.aadvantage.web.manager.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.aadvantage.bo.AadvantageBO;
import ar.com.santanderrio.obp.servicios.aadvantage.dto.SolicitudMillasAadvantageInDTO;
import ar.com.santanderrio.obp.servicios.aadvantage.dto.SolicitudMillasAadvantageOutDTO;
import ar.com.santanderrio.obp.servicios.aadvantage.web.manager.AadvantageManager;
import ar.com.santanderrio.obp.servicios.aadvantage.web.view.ConsultaMillasAadvantageCuerpoView;
import ar.com.santanderrio.obp.servicios.aadvantage.web.view.ConsultaMillasAadvantageHeaderView;
import ar.com.santanderrio.obp.servicios.aadvantage.web.view.ConsultaMillasAadvantageView;
import ar.com.santanderrio.obp.servicios.aadvantage.web.view.MensajeView;
import ar.com.santanderrio.obp.servicios.aadvantage.web.view.SolicitudMillasAadvantageView;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.legal.bo.LegalBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;

/**
 * The Class AadvantageManagerImpl.
 */
@Component
public class AadvantageManagerImpl implements AadvantageManager {

	/** Logger. */
	private static final Logger LOGGER = LoggerFactory.getLogger(AadvantageManagerImpl.class);
	
	/** The sesion cliente. */
	@Autowired
	private SesionCliente sesionCliente;
	
	/** The sesion parametros. */
	@Autowired
	private SesionParametros sesionParametros;
	
	/** The aadvantage BO. */
	@Autowired
	private AadvantageBO aadvantageBO;
	
	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;
	
	/** The Constant SIN_MILLAS. */
	private static final String SIN_MILLAS = "0";
	
	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.aadvantage.web.manager.AadvantageManager#consultaMillasAadvantage(ar.com.santanderrio.obp.servicios.aadvantage.web.view.SolicitudMillasAadvantageView)
	 */
	@Override
	public Respuesta<ConsultaMillasAadvantageView> consultaMillasAadvantage(SolicitudMillasAadvantageView inView) {
		
		ConsultaMillasAadvantageView respuesta = new ConsultaMillasAadvantageView();
		ConsultaMillasAadvantageHeaderView respuestaHeader = new ConsultaMillasAadvantageHeaderView();
		ConsultaMillasAadvantageCuerpoView respuestaCuerpo = new ConsultaMillasAadvantageCuerpoView();
		
		SolicitudMillasAadvantageInDTO inDTO = new SolicitudMillasAadvantageInDTO();
		Respuesta<SolicitudMillasAadvantageOutDTO> respuestaBO = new Respuesta<SolicitudMillasAadvantageOutDTO>();
		inDTO.setCliente(obtenerCliente());
		inDTO.setMostrarMasMeses(inView.getMostrarMasMeses());
		
		if(sesionParametros.getNumeroSocioAadvantage() != null) {
			respuestaHeader.setMiembro(sesionParametros.getNumeroSocioAadvantage());
		}
		
		respuestaBO = aadvantageBO.consultarMillasAadvantage(inDTO);
		
		if(EstadoRespuesta.ERROR.equals(respuestaBO.getEstadoRespuesta())){
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}else if (EstadoRespuesta.OK.equals(respuestaBO.getEstadoRespuesta()) && StringUtils.EMPTY.equals(respuestaBO.getRespuesta().getMillas())){
			respuestaHeader.setMillas(SIN_MILLAS);
			respuestaCuerpo.setHayMasMillas(false);
			respuestaCuerpo.setMensajeSinMillas(this.obtenerLegales(CodigoMensajeConstantes.CONSULTA_MILLAS_SIN_MILLAS_MENSAJE));
			respuestaCuerpo.setLinkSinMillas(this.obtenerLegales(CodigoMensajeConstantes.CONSULTA_MILLAS_SIN_MILLAS_LINK));
		} else {
			respuestaHeader.setMillas(respuestaBO.getRespuesta().getMillas());
			respuestaCuerpo.setHayMasMillas(respuestaBO.getRespuesta().getHayMasMillas());
			respuestaCuerpo.setGrillaList(respuestaBO.getRespuesta().getListaDetalleMillas());
		}
		
		respuestaHeader.setTooltip(this.obtenerLegales(CodigoMensajeConstantes.CONSULTA_MILLAS_TOOLTIP));
		respuestaHeader.setMensajes(this.obtenerMensajesHeader());
		respuestaCuerpo.setLegales(this.obtenerLegales(CodigoMensajeConstantes.CONSULTA_MILLAS_LEGALES));
		respuestaCuerpo.setTyc(this.obtenerLegalesTyc());
		
		respuesta.setCuerpo(respuestaCuerpo);
		respuesta.setHeader(respuestaHeader);
		
		return respuestaFactory.crearRespuestaOk(respuesta);
	}

	/**
	 * Obtener cliente.
	 *
	 * @return the cliente
	 */
	private Cliente obtenerCliente() {
		Cliente cliente = sesionCliente.getCliente();
		return cliente;
	}
	
	/** The legal BO. */
	@Autowired
	private LegalBO legalBO;
	
	/**
	 * Obtener legales.
	 *
	 * @param codigoMensaje
	 *            the codigo mensaje
	 * @return the string
	 */
	private String obtenerLegales(String codigoMensaje) {
		try {
			return legalBO.obtenerLegal(codigoMensaje);
		} catch (DAOException e) {
			LOGGER.error("Falla al obtenr legales solicitud cambio grupo afinidad.");
			return StringUtils.EMPTY;
		}
	}
	
	/**
	 * Obtener mensajes header.
	 *
	 * @return the list
	 */
	private List<MensajeView> obtenerMensajesHeader(){
		
		List<MensajeView> listaMensajes = new ArrayList<MensajeView>();
		
		MensajeView mensajeParte1 = new MensajeView();
		MensajeView mensajeParte2 = new MensajeView();
		MensajeView mensajeParte3 = new MensajeView();
		MensajeView mensajeParte4 = new MensajeView();
		MensajeView mensajeParte5 = new MensajeView();
		
		mensajeParte1.setTitulo(this.obtenerLegales(CodigoMensajeConstantes.CONSULTA_MILLAS_TITULO1));
		mensajeParte1.setTemplate(this.obtenerLegales(CodigoMensajeConstantes.CONSULTA_MILLAS_TEMPLATE1));
		listaMensajes.add(mensajeParte1);
		
		mensajeParte2.setTitulo(this.obtenerLegales(CodigoMensajeConstantes.CONSULTA_MILLAS_TITULO2));
		mensajeParte2.setTemplate(this.obtenerLegales(CodigoMensajeConstantes.CONSULTA_MILLAS_TEMPLATE2));
		listaMensajes.add(mensajeParte2);
		
		mensajeParte3.setTitulo(this.obtenerLegales(CodigoMensajeConstantes.CONSULTA_MILLAS_TITULO3));
		mensajeParte3.setTemplate(this.obtenerLegales(CodigoMensajeConstantes.CONSULTA_MILLAS_TEMPLATE3));
		listaMensajes.add(mensajeParte3);
		
		mensajeParte4.setTitulo(this.obtenerLegales(CodigoMensajeConstantes.CONSULTA_MILLAS_TITULO4));
		mensajeParte4.setTemplate(this.obtenerLegales(CodigoMensajeConstantes.CONSULTA_MILLAS_TEMPLATE4));
		listaMensajes.add(mensajeParte4);
		
		mensajeParte5.setTitulo(StringUtils.EMPTY);
		mensajeParte5.setTemplate(this.obtenerLegales(CodigoMensajeConstantes.CONSULTA_MILLAS_TEMPLATE5));
		listaMensajes.add(mensajeParte5);
		
		return listaMensajes;
		
	}
	
	/**
	 * Obtener legales tyc.
	 *
	 * @return the string
	 */
	private String obtenerLegalesTyc() {

		StringBuilder mensajeBuilder = new StringBuilder();
		mensajeBuilder.append(this.obtenerLegales(CodigoMensajeConstantes.CAMBIO_AFINIDAD_TERMINOS_Y_CONDICIONES_PARTE1));
		mensajeBuilder.append(this.obtenerLegales(CodigoMensajeConstantes.CAMBIO_AFINIDAD_TERMINOS_Y_CONDICIONES_PARTE2));
		return mensajeBuilder.toString();
	}
}
