package ar.com.santanderrio.obp.servicios.chances.bo.impl;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.chances.bo.ChancesBO;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.bo.impl.GestorEventoComercialBOImpl;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.dao.GestorEventoComercialDAO;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.dto.ChancesDTO;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.entities.ChanceGrillaOutEntity;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.entities.ChanceOutEntity;

/**
 * The Class ChancesBOImpl
 */
@Component
public class ChancesBOImpl implements ChancesBO{
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ChancesBOImpl.class);
    
    /** The respuesta factory. */
    @Autowired
    private RespuestaFactory respuestaFactory;
    
    /** The gestor evento comercial DAO. */
    @Autowired
    private GestorEventoComercialDAO gestorEventoComercialDAO;

	/**
	 * Obtiene chances segun el mes seleccionado.
	 *
	 * @param ChanceView
	 *            the ChanceView
	 * @return the respuesta
	 */
	@Override
	public Respuesta<ChancesDTO> obtenerChancesMes(String dispositivo, Cliente cliente, String fechaSeleccionada) {
		ChanceOutEntity chanceOutEntity = new ChanceOutEntity();
		try {
			chanceOutEntity = gestorEventoComercialDAO.obtenerPremiaciones(dispositivo, cliente);
			
		 } catch (BeansException e) {
			LOGGER.error("Error al obtener chances mes. BO  Beans Exception", e);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.CODIGO_ERROR_GENERICO); 
		 } catch (DAOException e) {
			LOGGER.error("Error al obtener chances mes. BO DAO Exception", e);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.CODIGO_ERROR_GENERICO);
		}
		
		List<ChanceGrillaOutEntity> listaGrilla = chanceOutEntity.getChancesGrillaOutEntity(); 
		//ordeno las chances de mayor periodo a menor periodo
		Collections.sort(listaGrilla,
					new Comparator<ChanceGrillaOutEntity>() {
					@Override
					public int compare(ChanceGrillaOutEntity grillaUno, ChanceGrillaOutEntity grillaDos) {
						return grillaDos.compareTo(grillaUno);
					}
			
		} );
		ChanceGrillaOutEntity chanceGrillaSeleccionada = null;
		for (ChanceGrillaOutEntity chanceGrilla : listaGrilla) {
			if(StringUtils.equalsIgnoreCase(fechaSeleccionada, chanceGrilla.getPeriodo())) {
				chanceGrillaSeleccionada = chanceGrilla;
				break;
			}
		}
		//si no existe chances para la fecha seleccionada
		if(chanceGrillaSeleccionada == null) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.CODIGO_ERROR_GENERICO);
		}
		
		ChancesDTO chancesDTO = new ChancesDTO();
		Boolean resp = GestorEventoComercialBOImpl.mapearChances(chancesDTO, chanceGrillaSeleccionada, null, Boolean.TRUE, Boolean.FALSE);
		if(!resp) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.CODIGO_ERROR_GENERICO);
		}
		return respuestaFactory.crearRespuestaOk(chancesDTO);
	}

}
