/**
 * 
 */
package ar.com.santanderrio.obp.servicios.alias.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.webservice.GestionarWS;
import ar.com.santanderrio.obp.generated.webservices.alias.AliasBCRAClient;
import ar.com.santanderrio.obp.generated.webservices.alias.AliasCbuException;
import ar.com.santanderrio.obp.generated.webservices.alias.ConsultarDatosTitularidad;
import ar.com.santanderrio.obp.generated.webservices.alias.ConsultarDatosTitularidadExtendido;
import ar.com.santanderrio.obp.generated.webservices.alias.ConsultarDatosTitularidadExtendidoResponse;
import ar.com.santanderrio.obp.generated.webservices.alias.ConsultarDatosTitularidadResponse;
import ar.com.santanderrio.obp.generated.webservices.alias.CuentaDTO;
import ar.com.santanderrio.obp.generated.webservices.alias.Error;
import ar.com.santanderrio.obp.generated.webservices.alias.MonedaDTO;
import ar.com.santanderrio.obp.generated.webservices.alias.RequestAlias;
import ar.com.santanderrio.obp.generated.webservices.alias.RequestConsultaAlias;
import ar.com.santanderrio.obp.generated.webservices.alias.RequestConsultaCBU;
import ar.com.santanderrio.obp.generated.webservices.alias.RequestModificaAlias;
import ar.com.santanderrio.obp.generated.webservices.alias.ResponseAlias;
import ar.com.santanderrio.obp.generated.webservices.alias.TipoCuentaDTO;
import ar.com.santanderrio.obp.generated.webservices.alias.Titularidad;
import ar.com.santanderrio.obp.generated.webservices.alias.TitularidadExtendido;
import ar.com.santanderrio.obp.servicios.alias.exception.AliasCBUCuentaInactivaException;

import java.util.Arrays;

/**
 * The Class AliasCbuDAOMockImpl.
 */
 //@Component("aliasCbuDAO")
public class AliasCbuDAOMockImpl implements AliasCbuDAO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(AliasCbuDAOMockImpl.class);

	/** The ws alias client. */
	@Autowired
	@Qualifier("gestionAlias")
	private GestionarWS<AliasBCRAClient> wsAliasClient;

	/** The Constant CBU_OBTENER_ALIAS_ERROR_160. */
	private static final String CBU_OBTENER_ALIAS_ERROR_160 = "0720000720000003156186";

	/** The Constant CBU_OBTENER_ALIAS_ERROR_170. */
	private static final String CBU_OBTENER_ALIAS_ERROR_170 = "0720201030000032433701";

	/** The Constant CBU_ELIMINAR_ALIAS_ERROR_B55. */
	private static final String CBU_ELIMINAR_ALIAS_ERROR_B55 = "0720033531000000837170";

	/** The Constant ALTA_ALIAS_ERROR_REASIGNA. */
	private static final String ALTA_ALIAS_ERROR_REASIGNA = "0720033588000036125322";

	/** The Constant ALIAS_ASIGNADO_CUENTA_INACTIVA. */
	private static final String ALIAS_ASIGNADO_CUENTA_INACTIVA = "0160";

	/** The Constant ALIAS_ASIGNADO_CUENTA_SIN_ALIAS. */
	private static final String ALIAS_ASIGNADO_CUENTA_SIN_ALIAS = "0170";

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

		ResponseAlias respAlias = new ResponseAlias();
		if (!CBU_OBTENER_ALIAS_ERROR_160.equals(request.getCbu())
				&& !CBU_OBTENER_ALIAS_ERROR_170.equals(request.getCbu())) {
			respAlias = respuestaAliasOk(request.getCbu());
		}
		if (CBU_OBTENER_ALIAS_ERROR_160.equals(request.getCbu())) {
			respAlias.setAlias(null);
			respAlias.setCbu(null);
			respAlias.setError(getError160());
			respAlias.setEstado("ERROR");
		}
		if (CBU_OBTENER_ALIAS_ERROR_170.equals(request.getCbu())) {
			respAlias.setAlias(null);
			respAlias.setCbu(null);
			respAlias.setError(getError170());
			respAlias.setEstado("ERROR");
		}
		LOGGER.info("Respuesta Mockeada obtenerAlias desde CBU: {}", respAlias.toString());
		Error error = respAlias.getError();
		if (error != null) {
			if (ALIAS_ASIGNADO_CUENTA_INACTIVA.equals(error.getCodigo())) {
				throw new AliasCBUCuentaInactivaException(error.getMensaje());
			} else {
				throw new AliasCbuException(error.getMensaje(), null);
			}
		}
		return respAlias;
	}

	/**
	 * Gets the error 170.
	 *
	 * @return the error 170
	 */
	private Error getError170() {
		Error error = new Error();
		error.setCodigo(ALIAS_ASIGNADO_CUENTA_SIN_ALIAS);
		error.setMensaje("CBU encontrado y cuenta activa, pero no tiene alias asignado");
		return error;
	}

	/**
	 * Gets the error 160.
	 *
	 * @return the error 160
	 */
	private Error getError160() {
		Error error = new Error();
		error.setCodigo("0160");
		error.setMensaje("CBU encontrado con Alias asignado, pero la cuenta no estÃ¡ activa");
		return error;
	}

	/**
	 * Gets the error baja.
	 *
	 * @return the error baja
	 */
	private Error getErrorBaja() {
		Error error = new Error();
		error.setCodigo("B55");
		error.setMensaje("Transacción inválida.");
		return error;
	}

	/**
	 * Gets the error alta.
	 *
	 * @return the error alta
	 */
	private Error getErrorAlta() {
		Error error = new Error();
		error.setCodigo("0210");
		error.setMensaje("Transacción inválida.");
		return error;
	}

	/**
	 * Gets the error modifica.
	 *
	 * @return the error modifica
	 */
	private Error getErrorModifica() {
		Error error = new Error();
		error.setCodigo("0210");
		error.setMensaje("Transacción inválida.");
		return error;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.alias.dao.AliasCbuDAO#altaAlias(ar.com.
	 * santanderrio.obp.generated.webservices.alias.RequestAlias)
	 */
	@Override
	public ResponseAlias altaAlias(RequestAlias request) throws DAOException {

		ResponseAlias respAlias = null;

		if (CBU_OBTENER_ALIAS_ERROR_170.equals(request.getCbu()) && (request.getReasigna() == null)) {

			respAlias = new ResponseAlias();
			respAlias.setAlias("ALIAS" + request.getCbu().substring(16, 21));
			respAlias.setCbu(request.getCbu());
			respAlias.setEstado("CONFIRMAR");
			respAlias.setReasigna("22323");
			respAlias.setError(getErrorAlta());
		} else {

			respAlias = new ResponseAlias();
			respAlias.setAlias("ALIAS" + request.getCbu().substring(16, 21));
			respAlias.setCbu(request.getCbu());
			respAlias.setEstado("OK");
		}

		LOGGER.info("Respuesta {}:", respAlias.toString());
		return respAlias;
	}

	/**
	 * Respuesta alias ok.
	 *
	 * @param cbu
	 *            the cbu
	 * @return the response alias
	 */
	private ResponseAlias respuestaAliasOk(String cbu) {

		ResponseAlias respAlias = new ResponseAlias();
		respAlias.setAlias("ALIAS" + cbu.substring(16, 21));
		respAlias.setCbu(cbu);
		respAlias.setEstado("OK");
		return respAlias;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.alias.dao.AliasCbuDAO#modificarAlias(ar
	 * .com.santanderrio.obp.generated.webservices.alias.RequestModificaAlias)
	 */
	@Override
	public ResponseAlias modificarAlias(RequestModificaAlias request) throws DAOException {

		ResponseAlias respAlias = null;

		if (ALTA_ALIAS_ERROR_REASIGNA.equals(request.getCbu()) && (request.getReasigna() == null)) {

			respAlias = new ResponseAlias();

			respAlias.setReasigna("32323");
			respAlias.setAlias("ALIAS" + request.getCbu().substring(16, 21));
			respAlias.setCbu(request.getCbu());
			respAlias.setEstado("CONFIRMAR");
			respAlias.setError(getErrorModifica());

		} else {

			respAlias = new ResponseAlias();
			respAlias.setAlias("ALIAS" + request.getCbu().substring(16, 21));
			respAlias.setCbu(request.getCbu());
			respAlias.setEstado("OK");
		}
		LOGGER.info("Respuesta {}: ", respAlias.toString());
		return respAlias;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.alias.dao.AliasCbuDAO#bajaAlias(ar.com.
	 * santanderrio.obp.generated.webservices.alias.RequestAlias)
	 */
	@Override
	public ResponseAlias bajaAlias(RequestAlias request) throws DAOException {
		ResponseAlias respAlias = new ResponseAlias();

		if (CBU_ELIMINAR_ALIAS_ERROR_B55.equals(request.getCbu())) {
			respAlias.setAlias(null);
			respAlias.setCbu(null);
			respAlias.setError(getErrorBaja());
			respAlias.setEstado("ERROR");
		} else {
			respAlias = respuestaAliasOk(request.getCbu());
		}
		LOGGER.info("Respuesta Mockeada eliminarAlias: {}", respAlias.toString());
		return respAlias;
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
		return null;
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
		CuentaDTO c = new CuentaDTO();
		MonedaDTO moneda = new MonedaDTO();
		TipoCuentaDTO tipoCuentaDTO = new TipoCuentaDTO();
		TitularidadExtendido t = new TitularidadExtendido();
		if("PruebaNadia".equals(req.getAlias())) {
			moneda.setCodigo("1");
			moneda.setDescripcion("$");
			c.setDisponible(0.0);
			c.setLimite(0.0);
			c.setNumero("250-3549140");
//			c.setNumeroCBU("0720100088000020052940");
			c.setNumeroCBU("0720250888000035491404");
			c.setSaldo(0.0);
			c.setMoneda(moneda);//
			tipoCuentaDTO.setCodigo("0");
			tipoCuentaDTO.setDescripcion("CC");
			c.setTipo(tipoCuentaDTO);//
			t.setAlias("PruebaNadia");
			t.setCuits(Arrays.asList("20169406910"));
			t.setFiidBanco("RIOP");
			t.setFiidOrigenLink("");
			t.setNombreBanco("BANCO SANTANDER RIO");
			t.setNombreTitular("ALESSO SALVAY CAIFAS JONAS");
			t.setCtaDestino(c);
		} else if("FILA.MITO.TROMPO".equals(req.getAlias())) {
			moneda.setCodigo("0");
			moneda.setDescripcion("u$s");
			c.setDisponible(0.0);
			c.setLimite(0.0);
			c.setNumeroCBU("0005748836584938572710");
			c.setSaldo(0.0);
			c.setMoneda(moneda);//
			tipoCuentaDTO.setCodigo("0");
			tipoCuentaDTO.setDescripcion("");
			c.setTipo(tipoCuentaDTO);//
			t.setAlias("FILA.MITO.TROMPO");
			t.setCuits(Arrays.asList("20118450486"));
			t.setFiidBanco("SDMR");
			t.setFiidOrigenLink("");
			t.setNombreBanco("Nombre banco");
			t.setNombreTitular("Nombre titular");
			t.setCtaDestino(c);
		}
		ConsultarDatosTitularidadExtendidoResponse res = new ConsultarDatosTitularidadExtendidoResponse();
		res.setCodigo("0");
		res.setMensaje("");
		res.setTitularidadExtendido(t);
		return res;
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.alias.dao.AliasCbuDAO#consultarDatosTitularidad(ar.com.santanderrio.obp.generated.webservices.alias.ConsultarDatosTitularidad)
	 */
	@Override
	public ConsultarDatosTitularidadResponse consultarDatosTitularidad(ConsultarDatosTitularidad req)
			throws DAOException {
		Titularidad t = new Titularidad();
		t.setCuits("20118450486");
		t.setFiidBanco("SDMR");
		t.setFiidOrigenLink("");
		t.setNombreBanco("Patagonia");
		t.setNombreTitular("JRULID GOMFUL SABU");
		CuentaDTO c = new CuentaDTO();
		c.setDisponible(0.0);
		c.setLimite(0.0);
		c.setNumeroCBU("0005748836584938572710");
		c.setSaldo(0.0);
		MonedaDTO m = new MonedaDTO();
		m.setCodigo("1");
		m.setDescripcion("$");
		c.setMoneda(m);
		TipoCuentaDTO tipoCuentaDTO = new TipoCuentaDTO();
		tipoCuentaDTO.setCodigo("2");
		tipoCuentaDTO.setDescripcion("CV");
		c.setTipo(tipoCuentaDTO);		
		ConsultarDatosTitularidadResponse res = new ConsultarDatosTitularidadResponse();
		res.setCodigo("0");
		res.setMensaje("");
		res.setTitularidad(t);
		return res;
	}

}
