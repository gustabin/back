/*
 * 
 */
package ar.com.santanderrio.obp.servicios.descuento.cheques.web.manager.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.descuento.cheques.entities.MensajesDescuentoEntity;
import ar.com.santanderrio.obp.servicios.descuento.cheques.web.manager.CoordinadorDescuentoChequesManager;
import ar.com.santanderrio.obp.servicios.descuento.cheques.web.manager.DescuentoChequesManager;
import ar.com.santanderrio.obp.servicios.descuento.cheques.web.view.DatosCesionView;
import ar.com.santanderrio.obp.servicios.descuento.cheques.web.view.OperacionesPrecargadasView;

/**
 * The Class CoordinadorDescuentoChequesManagerImpl.
 */
@Component
public class CoordinadorDescuentoChequesManagerImpl implements CoordinadorDescuentoChequesManager {

	/** The descuento cheques manager. */
	@Autowired
	private DescuentoChequesManager descuentoChequesManager;
	
	/** The Mensaje bo. */
	@Autowired
	private MensajeBO mensajeBO;
	
	/** The cabecera ok. */
	private boolean cabeceraOk;
	
	/** The grilla ok. */
	private boolean grillaOk;
	
	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.descuento.cheques.web.manager.CoordinadorDescuentoChequesManager#operacionesCabecera(ar.com.santanderrio.obp.servicios.descuento.cheques.web.view.OperacionesPrecargadasView)
	 */
	@Override
	public Respuesta<OperacionesPrecargadasView> operacionesCabecera(OperacionesPrecargadasView operacionIn) {
		Respuesta<OperacionesPrecargadasView> respuesta = new Respuesta<OperacionesPrecargadasView>();
		respuesta.setRespuesta(new OperacionesPrecargadasView());
		respuesta.setItemMensajeRespuesta(new ArrayList<ItemMensajeRespuesta>());
		ItemMensajeRespuesta item = new ItemMensajeRespuesta();
		if (operacionIn.getPrimerLlamada()) {
			Respuesta<DatosCesionView> respuestaCabecera = descuentoChequesManager.obtenerMontoCesion();
			obtenerCasuisticaCabecera(respuestaCabecera, respuesta, item);
		}
		Respuesta<OperacionesPrecargadasView> respuestaGrilla = descuentoChequesManager
				.obtenerOperacionesPrecargadas(operacionIn);
		obtenerCasuisticaGrilla(respuestaGrilla, respuesta, item);
		setearEstadoRespuesta(operacionIn.getPrimerLlamada(), cabeceraOk, grillaOk, respuesta);
		return respuesta;
	}
	
	

	/**
	 * Setear estado respuesta.
	 *
	 * @param primerLlamada
	 *            the primer llamada
	 * @param cabeceraOk
	 *            the cabecera ok
	 * @param grillaOk
	 *            the grilla ok
	 * @param respuesta
	 *            the respuesta
	 */
	private void setearEstadoRespuesta(boolean primerLlamada, boolean cabeceraOk, boolean grillaOk,
			Respuesta<OperacionesPrecargadasView> respuesta) {
		if(primerLlamada){
			if(cabeceraOk && grillaOk){
				respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
			} else if(!cabeceraOk && grillaOk){
				respuesta.setEstadoRespuesta(EstadoRespuesta.WARNING);
			} else {
				respuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);
			}
		} else {
			if(grillaOk){
				respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
			} else {
				respuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);
			}
		}
		
	}

	/**
	 * Obtener casuistica cabecera.
	 *
	 * @param respuestaCabecera
	 *            the respuesta cabecera
	 * @param respuesta
	 *            the respuesta
	 * @param item
	 *            the item
	 */
	private void obtenerCasuisticaCabecera(Respuesta<DatosCesionView> respuestaCabecera,
			Respuesta<OperacionesPrecargadasView> respuesta, ItemMensajeRespuesta item) {
		if (EstadoRespuesta.OK.equals(respuestaCabecera.getEstadoRespuesta())) {
			respuesta.getRespuesta().setCabecera(respuestaCabecera.getRespuesta());
			cabeceraOk = true;
		} else {
			respuesta.getRespuesta().setCabecera(crearCabeceraConMensajesError());
			respuesta.getItemsMensajeRespuesta().add(respuestaCabecera.getItemsMensajeRespuesta().get(0));
			cabeceraOk= false;
		}
	}
	
	/**
	 * Crear cabecera con mensajes error.
	 *
	 * @return the datos cesion view
	 */
	private DatosCesionView crearCabeceraConMensajesError() {
		DatosCesionView cabecera = new DatosCesionView();
		MensajesDescuentoEntity mensajes = new MensajesDescuentoEntity();
		mensajes.setErrorNoCalifica(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.DESCUENTO_CHEQUES_CLIENTE_NO_CALIFICA).getMensaje());
		mensajes.setErrorNoBYP(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.ERROR_DETALLE_OPERACIONES_PRECARGADAS).getMensaje());
		mensajes.setAvisoLimiteDiario(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.DESCUENTO_CHEQUES_AVISO_LIMITE_DIARIO).getMensaje());
		cabecera.setMensajes(mensajes);
		return cabecera;
	}

	/**
	 * Obtener casuistica grilla.
	 *
	 * @param respuestaGrilla
	 *            the respuesta grilla
	 * @param respuesta
	 *            the respuesta
	 * @param item
	 *            the item
	 */
	private void obtenerCasuisticaGrilla(Respuesta<OperacionesPrecargadasView> respuestaGrilla,
			Respuesta<OperacionesPrecargadasView> respuesta, ItemMensajeRespuesta item) {
		if(EstadoRespuesta.OK.equals(respuestaGrilla.getEstadoRespuesta())){
			respuesta.getRespuesta().setOperaciones(respuestaGrilla.getRespuesta().getOperaciones());
			respuesta.getRespuesta().setHayRellamada(respuestaGrilla.getRespuesta().getHayRellamada());
			grillaOk = true;
		} else {
			respuesta.getItemsMensajeRespuesta().add(respuestaGrilla.getItemsMensajeRespuesta().get(0));
			grillaOk = false;
		}
		
	}
	
	

}
