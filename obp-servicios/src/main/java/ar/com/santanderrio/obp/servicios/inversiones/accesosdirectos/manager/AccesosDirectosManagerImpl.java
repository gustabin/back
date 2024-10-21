package ar.com.santanderrio.obp.servicios.inversiones.accesosdirectos.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.inversiones.accesosdirectos.bo.AccesosDirectosBO;
import ar.com.santanderrio.obp.servicios.inversiones.accesosdirectos.entity.AccesoDirectoProductoEntity;

@Component
public class AccesosDirectosManagerImpl implements AccesosDirectosManager {
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(AccesosDirectosManagerImpl.class);

	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/** The sesion cliente. */
	@Autowired
	SesionCliente sesionCliente;
	
	@Autowired
	private AccesosDirectosBO accesosDirectosBO;
	

	@Override
	public Respuesta<AccesoDirectoProductoEntity> obtenerAccesoDirecto() {
		
		Respuesta<AccesoDirectoProductoEntity> respuesta;
		AccesoDirectoProductoEntity accesoDirectoView=accesosDirectosBO.obtenerAccesoDirecto();
		
		if (accesoDirectoView == null) {
			return respuestaFactory.crearRespuestaWarning("", TipoError.WARNING,
					CodigoMensajeConstantes.FALLO_DE_SERVICIO_ERROR);
		}else {
			respuesta=respuestaFactory.crearRespuestaOk(AccesoDirectoProductoEntity.class,accesoDirectoView);
			}
		
		return respuesta;
	}
}

	
