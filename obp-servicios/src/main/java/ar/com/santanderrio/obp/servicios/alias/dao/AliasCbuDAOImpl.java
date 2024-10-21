/**
 * 
 */
package ar.com.santanderrio.obp.servicios.alias.dao;

import org.apache.cxf.binding.soap.SoapFault;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.webservice.GestionarWS;
import ar.com.santanderrio.obp.generated.webservices.alias.AliasBCRAClient;
import ar.com.santanderrio.obp.generated.webservices.alias.AliasCbuException;
import ar.com.santanderrio.obp.generated.webservices.alias.ConsultarDatosTitularidad;
import ar.com.santanderrio.obp.generated.webservices.alias.ConsultarDatosTitularidadExtendido;
import ar.com.santanderrio.obp.generated.webservices.alias.ConsultarDatosTitularidadExtendidoResponse;
import ar.com.santanderrio.obp.generated.webservices.alias.ConsultarDatosTitularidadResponse;
import ar.com.santanderrio.obp.generated.webservices.alias.Error;
import ar.com.santanderrio.obp.generated.webservices.alias.RequestAlias;
import ar.com.santanderrio.obp.generated.webservices.alias.RequestConsultaAlias;
import ar.com.santanderrio.obp.generated.webservices.alias.RequestConsultaCBU;
import ar.com.santanderrio.obp.generated.webservices.alias.RequestModificaAlias;
import ar.com.santanderrio.obp.generated.webservices.alias.ResponseAlias;
import ar.com.santanderrio.obp.generated.webservices.alias.TitularidadExtendidaBCRAClient;
import ar.com.santanderrio.obp.servicios.alias.exception.AliasCBUCuentaInactivaException;

/**
 * The Class AliasCbuDAOImpl.
 *
 * @author sergio.e.goldentair
 */
@Component("aliasCbuDAO")
public class AliasCbuDAOImpl implements AliasCbuDAO {

	/** The Constant ALIAS_ASIGNADO_CUENTA_INACTIVA. */
	private static final String ALIAS_ASIGNADO_CUENTA_INACTIVA = "0160";

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(AliasCbuDAOImpl.class);

	/** Gestionar ws Alias. */
	@Autowired
	@Qualifier("gestionAliasCbu")
	private GestionarWS<AliasBCRAClient> wsAliasClient;

	/** The ws titularidad extendida client. */
	@Autowired
	@Qualifier("gestionTitularidadExtendida")
	private GestionarWS<TitularidadExtendidaBCRAClient> wsTitularidadExtendidaClient;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.alias.dao.AliasCbuDAO#altaAlias(ar.com.
	 * santanderrio.obp.generated.webservices.alias.RequestAlias)
	 */
	@Override
	public ResponseAlias altaAlias(RequestAlias req) throws DAOException {
		AliasBCRAClient services = null;
		try {
			services = wsAliasClient.obtenerPort();
			ResponseAlias respAlias = services.altaAlias(req);
			LOGGER.info("Respuesta {}:", respAlias.toString());
			return respAlias;
		} catch (AliasCbuException e) {
			LOGGER.error("Hubo un error al invocar al ws de Alias en la operacion altaAlias con los datos {}.", req);
			throw new DAOException(e);
		} catch (RuntimeException e) {
			LOGGER.error("Hubo un error al invocar al ws de Alias para la operacion altaAlias con los datos {}.", req);
			throw new DAOException(e);
		} finally {
			wsAliasClient.liberarPort(services);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.alias.dao.AliasCbuDAO#modificarAlias(ar
	 * .com.santanderrio.obp.generated.webservices.alias.RequestModificaAlias)
	 */
	@Override
	public ResponseAlias modificarAlias(RequestModificaAlias req) throws DAOException {
		AliasBCRAClient services = null;
		try {
			services = wsAliasClient.obtenerPort();
			ResponseAlias respAlias = services.modificarAlias(req);
			LOGGER.info("Respuesta {}: ", respAlias.toString());
			return respAlias;
		} catch (AliasCbuException e) {
			LOGGER.error("Hubo un error al invocar al ws de Alias en la operacion altaAlias con los datos {}.", req);
			throw new DAOException(e);
		} catch (RuntimeException e) {
			LOGGER.error("Hubo un error al invocar al ws de Alias para la operacion altaAlias con los datos {}.", req);
			throw new DAOException(e);
		} finally {
			wsAliasClient.liberarPort(services);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.alias.dao.AliasCbuDAO#bajaAlias(ar.com.
	 * santanderrio.obp.generated.webservices.alias.RequestAlias)
	 */
	@Override
	public ResponseAlias bajaAlias(RequestAlias req) throws DAOException {
		AliasBCRAClient services = null;
		try {
			services = wsAliasClient.obtenerPort();
			ResponseAlias respAlias = services.bajaAlias(req);
			LOGGER.info("Respuesta : {}", respAlias);
			return respAlias;
		} catch (AliasCbuException e) {
			LOGGER.error("Hubo un error al invocar al ws de Alias en la operacion bajaAlias con los datos {}.", req);
			throw new DAOException(e);
		} finally {
			wsAliasClient.liberarPort(services);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.alias.dao.AliasCbuDAO#
	 * obtenerCBUDesdeAlias(ar.com.santanderrio.obp.generated.webservices.alias.
	 * RequestConsultaAlias)
	 */
	@Override
	public ResponseAlias obtenerCBUDesdeAlias(RequestConsultaAlias request) throws DAOException {
		AliasBCRAClient services = null;
		try {
			services = wsAliasClient.obtenerPort();
			ResponseAlias respAlias = services.obtenerCBUDesdeAlias(request);
			LOGGER.info("Respuesta: {}", respAlias.toString());
			return respAlias;
		} catch (AliasCbuException e) {
			LOGGER.error(
					"Hubo un error al invocar al ws de Alias para la operacion obtenerCBUDesdeAlias con los datos {}.",
					request, e);
			throw new DAOException(e);
		} finally {
			wsAliasClient.liberarPort(services);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.alias.dao.AliasCbuDAO#
	 * obtenerAliasDesdeCBU(ar.com.santanderrio.obp.generated.webservices.alias.
	 * RequestConsultaCBU)
	 */
	@Override
	public ResponseAlias obtenerAliasDesdeCBU(RequestConsultaCBU request)
			throws DAOException, AliasCBUCuentaInactivaException, AliasCbuException {
		AliasBCRAClient services = null;
		try {
			services = wsAliasClient.obtenerPort();
			ResponseAlias respAlias = services.obtenerAliasDesdeCBU(request);
			LOGGER.info("Respuesta : {}", respAlias.toString());

			Error error = respAlias.getError();
			if (error != null) {
				if (ALIAS_ASIGNADO_CUENTA_INACTIVA.equals(error.getCodigo())) {
					throw new AliasCBUCuentaInactivaException(error.getMensaje());
				} else {
					throw new AliasCbuException(error.getMensaje(), null);
				}
			}

			return respAlias;
		} catch (RuntimeException e) {
			LOGGER.error(
					"Hubo un error al invocar al ws de Alias para la operacion obtenerAliasDesdeCBU con los datos {}.",
					request, e);
			throw new DAOException(e);
		} finally {
			wsAliasClient.liberarPort(services);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.alias.dao.AliasCbuDAO#
	 * consultarDatosTitularidadExtendido(ar.com.santanderrio.obp.generated.
	 * webservices.alias.ConsultarDatosTitularidadExtendido)
	 */
	@Override
	public ConsultarDatosTitularidadExtendidoResponse consultarDatosTitularidadExtendido(
			ConsultarDatosTitularidadExtendido req) throws DAOException {

		TitularidadExtendidaBCRAClient services = null;
		ConsultarDatosTitularidadExtendidoResponse respuestaWS;
		try {
			services = wsTitularidadExtendidaClient.obtenerPort();
			LOGGER.info("Envio: {}", req);
			respuestaWS = services.consultarDatosTitularidadExtendido(req);
			return respuestaWS;
		} catch (SoapFault e) {
			LOGGER.error("Error al invocar al ws.", e);
			ConsultarDatosTitularidadExtendidoResponse respuesta = new ConsultarDatosTitularidadExtendidoResponse();
			respuesta.setCodigo(e.getFaultCode().toString());
			respuesta.setMensaje(e.getMessage());
			return respuesta;
		} catch (javax.xml.ws.soap.SOAPFaultException e) {
			LOGGER.error("Error de timeout al invocar al ws.", e);
			throw new DAOException(e);
		} finally {
			wsTitularidadExtendidaClient.liberarPort(services);
		}
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.alias.dao.AliasCbuDAO#consultarDatosTitularidad(ar.com.santanderrio.obp.generated.webservices.alias.ConsultarDatosTitularidad)
	 */
	@Override
	public ConsultarDatosTitularidadResponse consultarDatosTitularidad(ConsultarDatosTitularidad req) throws DAOException {
		TitularidadExtendidaBCRAClient services = null;
		ConsultarDatosTitularidadResponse respuestaWS;
		try {
			services = wsTitularidadExtendidaClient.obtenerPort();
			LOGGER.info("Envio: {}", req.toString());
			respuestaWS = services.consultarDatosTitularidad(req);
			return respuestaWS;
		} catch (SoapFault e) {
			LOGGER.error("Error al invocar al ws.", e);
			ConsultarDatosTitularidadResponse respuesta = new ConsultarDatosTitularidadResponse();
			respuesta.setCodigo(e.getFaultCode().toString());
			respuesta.setMensaje(e.getMessage());
			return respuesta;
		} catch (javax.xml.ws.soap.SOAPFaultException e) {
			LOGGER.error("Error de timeout al invocar al ws.", e);
			throw new DAOException(e);
		} finally {
			wsTitularidadExtendidaClient.liberarPort(services);
		}
	}

}
