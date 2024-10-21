/*
 * 
 */
package ar.com.santanderrio.obp.servicios.iatx.dao.impl;

import java.util.Map;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequest;
import ar.com.santanderrio.obp.base.iatx.helpers.IatxSender;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.security.PropertyMap;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxComm;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxResponse;
import iatx.Iatx;
import iatx.context.IATXContext;

/**
 * The Class IatxCommImpl.
 */
/*
 * afterPropertiesSet hereda el throws Exception de Spring
 */
@Component
@Scope("singleton")
public class IatxCommImpl implements IatxComm, InitializingBean {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(IatxCommImpl.class);

	/** The Constant LISTAVACIA. */
	private static final String LISTAVACIA = "LISTAVACIA";

	/** The Constant PINVENCIDO. */
	private static final String PINVENCIDO = "PINVENCIDO";

	/** The Constant SINONIMO. */
	private static final String SINONIMO = "SINONIMO";

	/** The Constant CLAVEINVALIDA. */
	private static final String CLAVEINVALIDA = "CLAVEINVALIDA";

	/** The Constant FUERADEHORARIO. */
	private static final String FUERADEHORARIO = "FUERADEHORARIO";

	/** The Constant USUARIOBLOQUEADO. */
	private static final String USUARIOBLOQUEADO = "USUARIOBLOQUEADO";

	/** The Constant USUARIOYADEFINIDO. */
	private static final String USUARIOYADEFINIDO = "USUARIOYADEFINIDO";

	/** The Constant NOCONTESTO. */
	private static final String NOCONTESTO = "NOCONTESTO";

	/** The Constant WARNING. */
	private static final String WARNING = "WARNING";

	/** The Constant WARNINGOK. */
	private static final String WARNINGOK = "WARNINGOK";

	/** The Constant VENCIMIENTOPIN. */
	/*
	 * ID 3673 - Vencimiento Pin
	 */
	private static final String VENCIMIENTOPIN = "VENCIMIENTOPIN";
	
	/** The Constant USUARIO_BLOQUEADO_DESC. */
	private static final String USUARIO_BLOQUEADO_DESC = "La clave o el usuario se encuentran bloqueados.";

	/** The Constant USUARIO_BLOQUEADO_DESC_LINEA_2. */
	private static final String USUARIO_BLOQUEADO_DESC_LINEA_2 = "Por favor accede desde la plataforma actual de Online Banking para solucionarlo.";

	/** The Constant FEPARMNODISPONIBLE. */
	private static final String FEPARMNODISPONIBLE = "FEPARMNODISPONIBLE";
	
	/** The programa. */
	@Value("${IATX.PROGRAMA}")
	private String programa;

	/** The timeout. */
	@Value("${IATX.TIMEOUT}")
	private Integer timeout;

	/** The trans id. */
	@Value("${IATX.TRANSID}")
	private String transId;

	/** The log. */
	@Value("${IATX.LOG}")
	private Integer log;

	/** The dir log. */
	@Value("${IATX.DIRLOG}")
	private String dirLog;

	/** The gate name. */
	@Value("${IATX.GATENAME}")
	private String gateName;

	/** The port gate. */
	@Value("${IATX.GATEPORT}")
	private Integer portGate;

	/** The cics. */
	@Value("${IATX.CICS}")
	private String cics;

	/** The iatx sender. */
	@Autowired
	private IatxSender iatxSender;

	/** The property map. */
	@Autowired
	private PropertyMap propertyMap;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.iatx.dao.IatxComm#exec(ar.com.santanderrio.base.
	 * iatx.entities.IatxRequest)
	 */
	@Override
	public IatxResponse exec(IatxRequest request) throws IatxException {

		boolean reLlamada = false;

		IatxResponse iatxResponse = null;
		while (true) {

			if (reLlamada) {
				LOGGER.info("RELLAMADA");
			}

			// El sender loguea el contenido de la trama.
			String tramaResponse;
			tramaResponse = iatxSender.send(request);
			StringBuffer tramaAnterior = new StringBuffer();
			if (reLlamada) {
				tramaAnterior.append(iatxResponse.getTrama());
			}
			
			iatxResponse = processResponse(tramaResponse, iatxResponse);
			if (reLlamada) {
			//	iatxResponse.setTrama(concatenarTramaBody(tramaAnterior.toString(), iatxResponse.getIatxBody()));
			} else {
				iatxResponse.setTrama(tramaResponse);
			}

			if (iatxResponse.getErrorCode() != 0) {
				setMensajeDeError(iatxResponse);
			}
			// invocacion al analyze

			if ("000".equals(iatxResponse.getNroMjeMultiple())) {
				// no hay mas mensajes
				break;
			} else {
				// continua
				reLlamada = true;
				request.getData().setAccionMjeMultiple("01");
				request.getData().setNroMjeMultiple(iatxResponse.getNroMjeMultiple());
				request.getData().setIdSesionTransaccional(iatxResponse.getIdSesionTransaccional());
			}
		}
		return iatxResponse;
	}

	/**
	 * Concatenar trama body.
	 *
	 * @param tramaAnterior
	 *            the trama anterior
	 * @param iatxBody
	 *            the iatx body
	 * @return the string
	 */
	private String concatenarTramaBody(String tramaAnterior, Vector<String> iatxBody) {
		StringBuffer buffer = new StringBuffer(tramaAnterior);

		for (String elemento : iatxBody) {
			buffer.append(elemento).append('õ');
		}

		return buffer.toString();
	}

	/**
	 * Setter para mensaje de error.
	 *
	 * @param iatxResponse
	 *            el nuevo mensaje de error
	 * @return void
	 */
	private void setMensajeDeError(IatxResponse iatxResponse) {
		String key = iatxResponse.getErrorCode() + "." + iatxResponse.getErrorSystem();
		String res = (String) getDataSourcesNames().get(key);
		if (LISTAVACIA.equals(res)) {
			iatxResponse.setMensajeDeNegocio(LISTAVACIA);
		} else if (PINVENCIDO.equals(res)) {
			iatxResponse.setMensajeDeNegocio(PINVENCIDO);
		} else if (SINONIMO.equals(res)) {
			iatxResponse.setMensajeDeNegocio(SINONIMO);
		} else if (CLAVEINVALIDA.equals(res)) {
			iatxResponse.setMensajeDeNegocio(CLAVEINVALIDA);
		} else if (FUERADEHORARIO.equals(res)) {
			iatxResponse.setMensajeDeNegocio(FUERADEHORARIO);
		} else {
			setOtherMensajesNegocio(res, iatxResponse);
		}
		return;
	}

	/**
	 * Sets the other mensajes negocio.
	 *
	 * @param res
	 *            the res
	 * @param iatxResponse
	 *            the iatx response
	 */
	private void setOtherMensajesNegocio(String res, IatxResponse iatxResponse) {
		if (USUARIOBLOQUEADO.equals(res)) {
			iatxResponse.setMensajeDeNegocio(USUARIO_BLOQUEADO_DESC);
			iatxResponse.setMensajeDeNegocioLinea2(USUARIO_BLOQUEADO_DESC_LINEA_2);
		} else if (USUARIOYADEFINIDO.equals(res)) {
			iatxResponse.setMensajeDeNegocio(USUARIOYADEFINIDO);
		} else if (NOCONTESTO.equals(res)) {
			iatxResponse.setMensajeDeNegocio(NOCONTESTO);
		} else if (WARNING.equals(res)) {
			iatxResponse.setMensajeDeNegocio(WARNING);
			iatxResponse.setEstadoRespuesta(EstadoRespuesta.OK);
		} else if (WARNINGOK.equals(res)) {
			iatxResponse.setMensajeDeNegocio(WARNINGOK);
			iatxResponse.setEstadoRespuesta(EstadoRespuesta.OK);
			// ID 3673 - Vencimiento pin - ini
		} else if (VENCIMIENTOPIN.equals(res)) {
			iatxResponse.setMensajeDeNegocio(VENCIMIENTOPIN);
		} else if (FEPARMNODISPONIBLE.equals(res)) {
			iatxResponse.setMensajeDeNegocio(FEPARMNODISPONIBLE);
		} else {
			// tirar new SIErrorException
		}
	}

	/**
	 * Process response.
	 *
	 * @param iatxRes
	 *            the iatx res
	 * @param iatxResponse
	 *            the iatx response
	 * @return the iatx response
	 */
	private IatxResponse processResponse(String iatxRes, IatxResponse iatxResponse) {
		return IatxResponseParser.parseResponse(iatxRes, iatxResponse);
	}

	/**
	 * Inits the context.
	 *
	 * @return the IATX context
	 */
	private IATXContext initContext() {
		synchronized (this) {
			IATXContext context = new IATXContext();
			context.setCics(cics);
			context.setPrograma(programa);
			Long milisegEnMinutos = TimeUnit.MILLISECONDS.toSeconds(timeout);
			context.setTimeout(milisegEnMinutos.intValue());
			context.setTransId(transId);
			context.setLog(log);
			context.setDirLog(dirLog);
			context.setGateName(gateName);
			context.setPortGate(portGate);

			Iatx.configure(context);
			return context;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		initContext();
	}

	/**
	 * Hace la transformacion de environment a un hash.
	 *
	 * @return the data sources names
	 */
	private Map<String, Object> getDataSourcesNames() {
		return propertyMap.getProperties();
	}
}
