/**
 * 
 */
package ar.com.santanderrio.obp.servicios.status.sei;

import java.util.Arrays;
import java.util.List;

import org.apache.cxf.jaxrs.ext.MessageContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.base.http.web.util.NetworkUtil;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.status.manager.StatusManager;
import ar.com.santanderrio.obp.servicios.status.view.StatusView;

/**
 * The Class StatusSEIImpl.
 *
 * @author sergio.e.goldentair
 */
@Component("statusSEI")
public class StatusSEIImpl implements StatusSEI {

	/** The status allowed ips. */
	@Value("${STATUS.ALLOWED_IPS:180.166.92.59}")
	private String statusAllowedIps;

	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/** Acceder la manager de estado del servidor. */
	@Autowired
	private StatusManager statusManager;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.status.sei.StatusSEI#getStatus(org.apache.
	 * cxf.jaxrs.ext.MessageContext)
	 */
	@Override
	public Respuesta<StatusView> getStatus(MessageContext mc) {
		String remoteIp = NetworkUtil.getRemoteIp(mc.getHttpServletRequest());
		List<String> allowedIps = Arrays.asList(statusAllowedIps.split("\\|"));
		if (allowedIps.contains(remoteIp)) {
			return statusManager.getStatus();
		} else {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_GENERICO);
		}
	}

}
