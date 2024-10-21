package ar.com.santanderrio.obp.servicios.nuevarecarga.bo;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.nuevarecarga.dao.AgendaCelularDAO;
import ar.com.santanderrio.obp.servicios.nuevarecarga.entity.CompaniaCelularEnum;
import ar.com.santanderrio.obp.servicios.nuevarecarga.web.view.CelularView;

@Component
public class AgendaCelularBOImpl implements AgendaCelularBO{

    private static final int MAX_ALIAS = 50;

	private static final Logger LOGGER = LoggerFactory.getLogger(AgendaCelularBOImpl.class);
	
	@Autowired
	protected EstadisticaManager estadisticaManager;
    
	@Autowired
	AgendaCelularDAO dao;
	
	@Autowired
	RespuestaFactory respuestaFactory;
	
	@Override
	public Respuesta<Void> agregarCelular(Cliente cliente, String nro, String descripcion, String compania) {
		Respuesta<Void> respuesta = new Respuesta<Void>();
		try {
			Long nup = Long.valueOf(cliente.getNup());
			if(descripcion.length() > MAX_ALIAS) {
				descripcion = StringUtils.abbreviate(descripcion, MAX_ALIAS);
			}
			dao.agregarCelular(nup, nro, descripcion, compania);
			respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
			respuesta.setRespuestaVacia(false);
			LOGGER.info("Se ha agregado el celular exitosamente. Cliente {} - celular {} - alias {} - compania {}", nup, nro, descripcion, compania);
			estadisticaManager.add(EstadisticasConstants.RECARGA_CELULARES_AGENDA_AGREGAR, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			return respuesta;
		}catch(DAOException e) {
			LOGGER.error("Error al agregar celular", e);
			estadisticaManager.add(EstadisticasConstants.RECARGA_CELULARES_AGENDA_AGREGAR, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
            respuesta = respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.ERROR_CARGAR_NUEVO_CELULAR,
	                    CodigoMensajeConstantes.ERROR_AGENDA_CELULAR);
			return respuesta;
		}
	}

	@Override
	public Respuesta<Void> actualizarAliasCelular(Cliente cliente, String nro, String descripcion) {
		Respuesta<Void> respuesta = new Respuesta<Void>();
		try {
			Long nup = Long.valueOf(cliente.getNup());
			if(descripcion.length() > MAX_ALIAS) {
				descripcion = StringUtils.abbreviate(descripcion, MAX_ALIAS);
			}
			dao.actualizarAliasCelular(nup, nro, descripcion);
			respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
			respuesta.setRespuestaVacia(false);
			LOGGER.info("Se ha modificado el celular exitosamente. Cliente {} - celular {}", nup, nro);
			estadisticaManager.add(EstadisticasConstants.RECARGA_CELULARES_AGENDA_MODIFICAR, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			return respuesta;
		}catch(DAOException e) {
			LOGGER.error("Error al agregar celular", e);
			estadisticaManager.add(EstadisticasConstants.RECARGA_CELULARES_AGENDA_MODIFICAR, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			respuesta = respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.ERROR_ACTUALIZAR_CELULAR,
					CodigoMensajeConstantes.ERROR_AGENDA_CELULAR);
			return respuesta;
		}
	}
	
	@Override
	public Respuesta<Void> eliminarCelular(Cliente cliente, String nro) {
		Respuesta<Void> respuesta = new Respuesta<Void>();
		try {
			Long nup = Long.valueOf(cliente.getNup());
			dao.eliminarCelular(nup, nro);
			respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
			respuesta.setRespuestaVacia(false);
			LOGGER.info("Se ha eliminado el celular exitosamente. Cliente {} - celular {}", nup, nro);
			estadisticaManager.add(EstadisticasConstants.RECARGA_CELULARES_AGENDA_ELIMINAR, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			return respuesta;
		}catch(DAOException e) {
		 LOGGER.error("Error al eliminar celular", e);
		 estadisticaManager.add(EstadisticasConstants.RECARGA_CELULARES_AGENDA_ELIMINAR, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
           respuesta = respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.ERROR_ELIMINAR_CELULAR,
                   CodigoMensajeConstantes.ERROR_AGENDA_CELULAR);
		return respuesta;
		}
	}

	@Override
	public Respuesta<List <CelularView>> consultarCelulares(Cliente cliente) {
		Respuesta<List <CelularView>> respuesta = new Respuesta<List <CelularView>>();
		Long nup = Long.valueOf(cliente.getNup());
		try {
			respuesta = respuestaFactory.crearRespuestaOk(dao.obtenerCelularesNup(nup));
			for (CelularView celular : respuesta.getRespuesta()) {
				CompaniaCelularEnum companiaCelular = CompaniaCelularEnum.buscarCompania(celular.getCompania().toLowerCase());
				celular.setCompania(companiaCelular.getCodigoEmpresa() + "Agenda");
				celular.setFiid(companiaCelular.getFiid());
				celular.setNombreFantasia(companiaCelular.getNombreFantasia());
			}
			return respuesta;
		} catch(DAOException e) {
			LOGGER.error("Error a consultar celulares de {}", nup);
	         respuesta = respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.ERROR_OBTENER_CELULARES,
	                    CodigoMensajeConstantes.ERROR_AGENDA_CELULAR);
			return respuesta;
		}
	}	
	
	@Override
	public Respuesta<String> obtenerAlias(Cliente cliente, String nro){
		Respuesta<String> respuesta = new Respuesta<String>();
		Long nup = Long.valueOf(cliente.getNup());
		try {
			return respuestaFactory.crearRespuestaOk(dao.obtenerAlias(nup, nro));
		} catch(DAOException e) {
			LOGGER.error("Error a consultar alias de celulares de {} {}", nup, nro);
	         respuesta = respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.ERROR_OBTENER_CELULARES,
	                    CodigoMensajeConstantes.ERROR_AGENDA_CELULAR);
			return respuesta;
		}		
	}
	
	@Override
	public Respuesta<Void> existeCelular(Cliente cliente, String nro) {
		Respuesta<Void> respuesta = new Respuesta<Void>();
		try {
			Long nup = Long.valueOf(cliente.getNup());
			dao.existeCelular(nup, nro);
			respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
			respuesta.setRespuestaVacia(false);
			LOGGER.info("El celular no existe, se procedera a agregarlo", nup, nro);
			return respuesta;
		}catch(DAOException e) {
			LOGGER.error("El celular existe", e);
            respuesta = respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.ERROR_CARGAR_NUEVO_CELULAR,
	                    CodigoMensajeConstantes.ERROR_AGENDA_CELULAR);
			return respuesta;
		}
	}

}
