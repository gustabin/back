package ar.com.santanderrio.obp.servicios.inversiones.ventameprsa.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.inversiones.ventameprsa.bo.VentaMepRsaBO;
import ar.com.santanderrio.obp.servicios.inversiones.ventameprsa.entity.MepRsaNotification;
import ar.com.santanderrio.obp.servicios.inversiones.ventameprsa.entity.VentaMepRequest;

@Component
public class VentaMepRsaManagerImpl implements VentaMepRsaManager {
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(VentaMepRsaManagerImpl.class);

	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/** The sesion cliente. */
	@Autowired
	SesionCliente sesionCliente;
	
	@Autowired
	private VentaMepRsaBO vtaMepRsaBO;

	@Override
	public Respuesta<MepRsaNotification> notificarVtaMepRsa(VentaMepRequest request) {
		LOGGER.info("Notificacion RSA Operacion Venta MEP.", request);
		Respuesta<MepRsaNotification> respuesta;
		MepRsaNotification resp = vtaMepRsaBO.notificarVtaMep(request);
		
		if (!resp.notified) {
			LOGGER.info("ERROR Notificando RSA Operacion Venta MEP.", request);
			return respuestaFactory.crearRespuestaWarning("", TipoError.WARNING,
					CodigoMensajeConstantes.FALLO_DE_SERVICIO_ERROR);
		}else {
			respuesta = respuestaFactory.crearRespuestaOk(MepRsaNotification.class,resp);
			}
		
		return respuesta;
	}
	

	
}

	
