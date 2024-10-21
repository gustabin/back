/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.bo.impl;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.generated.webservices.alias.ResponseAlias;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.cuentas.bo.CasuisticaAliasCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ComprobanteAltaCBUDTO;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;

/**
 * The Class CasuisticaAliasCuentaImpl.
 */
@Component
public class CasuisticaAliasCuentaImpl implements CasuisticaAliasCuenta {

	/** The Constant ALIAS_AGREGADO_CUENTA_INACTIVA. */
	private static final String ALIAS_AGREGADO_CUENTA_INACTIVA = "1516";

	/** The Constant ALIAS_NO_ESPECIF. */
	private static final String ALIAS_NO_ESPECIF = "1506";

	/** The Constant ALIAS_YA_ASIGNADO_CUIT_DIF. */
	private static final String ALIAS_YA_ASIGNADO_CUIT_DIF = "1541";

	/** The Constant ALIAS_USADO. */
	private static final String ALIAS_USADO = "1501";

	/** The Constant RESPONSE_OK. */
	private static final String RESPONSE_OK = "OK";

	/** The Constant RESPONSE_CONFIRMAR. */
	private static final String RESPONSE_CONFIRMAR = "CONFIRMAR";

	/** The Constant RESPONSE_REASIGNA. */
	private static final String RESPONSE_REASIGNA = "REASIGNA";

	/** The Constant RESPONSE_ERROR. */
	private static final String RESPONSE_ERROR = "ERROR";

	/** The Constant ALIAS_AGREGADO_INACTIVA. */
	private static final String ALIAS_AGREGADO_INACTIVA = "0210";

	/** The Constant ALIAS_AGREGADO_INACTIVA2. */
	private static final String ALIAS_AGREGADO_INACTIVA2 = "0310";

	/** The Constant ALIAS_NO_ESPECIFICADO. */
	private static final String ALIAS_NO_ESPECIFICADO = "0320";

	/** The Constant YA_TIENE_ALIAS. */
	private static final String YA_TIENE_ALIAS = "0240";

	/** The Constant ALIAS_YA_USADO. */
	private static final String ALIAS_YA_USADO = "0290";

	/** The Constant ALIAS_YA_ASIGNADO_CUIT_DIFERENTE. */
	/*
	 * private static final String ALIAS_YA_USADO2 = "0410"; private static
	 * final String ALIAS_YA_USADO3 = "0420";
	 */
	private static final String ALIAS_YA_ASIGNADO_CUIT_DIFERENTE = "0420";

	/** The Constant ALIAS_AGREGADO_INACTIVA3. */
	private static final String ALIAS_AGREGADO_INACTIVA3 = "0410";

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(CasuisticaAliasCuentaImpl.class);

	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/** The mensaje BO. */
	@Autowired
	private MensajeBO mensajeBO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.cuentas.bo.CasuisticaAliasCuenta#
	 * obtenerErrorGeneral()
	 */
	@Override
	public Respuesta<ComprobanteAltaCBUDTO> obtenerErrorGeneral() {
		return respuestaFactory.crearRespuestaError("errorGeneral", TipoError.ERROR_GENERICO_ALIAS_CBU, "1487");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.cuentas.bo.CasuisticaAliasCuenta#
	 * obtenerErrorGeneralEditarAlias()
	 */
	@Override
	public Respuesta<ComprobanteAltaCBUDTO> obtenerErrorGeneralEditarAlias() {
		return respuestaFactory.crearRespuestaError("aliasNoEspecificado", TipoError.ERROR_ALIAS_NO_ESPECIFICADO,
				ALIAS_NO_ESPECIF);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.cuentas.bo.CasuisticaAliasCuenta#
	 * crearRespuesta(ar.com.santanderrio.obp.generated.webservices.alias.
	 * ResponseAlias, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Respuesta<ComprobanteAltaCBUDTO> crearRespuesta(ResponseAlias response, String alias, String nroCuenta,
			String reasigna) {
		if (RESPONSE_OK.equals(response.getEstado())) {
			String codigo = CodigoMensajeConstantes.CODIGO_ASIGNACION_ALIAS_OK;
			if (reasigna != null) {
				codigo = CodigoMensajeConstantes.CODIGO_REASIGNACION_ALIAS_OK;
			}
			return respuestaFactory.crearRespuestaOk(ComprobanteAltaCBUDTO.class,
					new ComprobanteAltaCBUDTO(getMensajePorCodigo(codigo), alias, nroCuenta));
		} else {
			if (RESPONSE_CONFIRMAR.equals(response.getEstado()) || RESPONSE_REASIGNA.equals(response.getEstado())) {
				Respuesta<ComprobanteAltaCBUDTO> respuesta = respuestaFactory.crearRespuestaWarning(StringUtils.EMPTY,
						TipoError.REASIGNACION_ALIAS_CBU, CodigoMensajeConstantes.REASIGNACION_ALIAS_CBU);
				ComprobanteAltaCBUDTO comprobanteAltaCBUDTO = new ComprobanteAltaCBUDTO(
						getMensajePorCodigo(CodigoMensajeConstantes.CODIGO_ASIGNACION_ALIAS_OK), alias, nroCuenta);
				comprobanteAltaCBUDTO.setReasigna(response.getReasigna());
				respuesta.setRespuesta(comprobanteAltaCBUDTO);
				return respuesta;
			}
			Boolean reasignacion = Boolean.FALSE;
			if (reasigna != null) {
				reasignacion = Boolean.TRUE;
			}
			return crearRespuestaError(response.getError().getCodigo(), alias, nroCuenta, reasignacion);
		}
	}

	/**
	 * Crear respuesta error.
	 *
	 * @param codigo
	 *            the codigo
	 * @param alias
	 *            the alias
	 * @param nroCuenta
	 *            the nro cuenta
	 * @param reasigna
	 *            the reasigna
	 * @return the respuesta
	 */
	private Respuesta<ComprobanteAltaCBUDTO> crearRespuestaError(String codigo, String alias, String nroCuenta,
			Boolean reasigna) {
		Respuesta<ComprobanteAltaCBUDTO> res;
		if (ALIAS_AGREGADO_INACTIVA.equals(codigo) || ALIAS_AGREGADO_INACTIVA2.equals(codigo)
				|| ALIAS_AGREGADO_INACTIVA3.equals(codigo)) {
			res = respuestaFactory.crearRespuestaOk(ComprobanteAltaCBUDTO.class,
					new ComprobanteAltaCBUDTO(getMensajePorCodigo(ALIAS_AGREGADO_CUENTA_INACTIVA), alias, nroCuenta));
		} else if (YA_TIENE_ALIAS.equals(codigo)) {
			res = respuestaFactory.crearRespuestaError("cbuTieneAlias", TipoError.ERROR_YA_TIENE_ALIAS,
					CodigoMensajeConstantes.CODIGO_YA_TIENE_ALIAS);
		} else if (ALIAS_YA_USADO.equals(codigo)) {
			res = respuestaFactory.crearRespuestaError("aliasEnUso", TipoError.ERROR_ALIAS_USADO, ALIAS_USADO);
		} else if (ALIAS_YA_ASIGNADO_CUIT_DIFERENTE.equals(codigo)) {
			res = respuestaFactory.crearRespuestaError("aliasEnUso", TipoError.ERROR_ALIAS_USADO,
					ALIAS_YA_ASIGNADO_CUIT_DIF);
		} else if (reasigna) {
			res = respuestaFactory.crearRespuestaError("aliasEnUso", TipoError.ERROR_ALIAS_USADO,
					CodigoMensajeConstantes.CODIGO_ERROR_REASIGNACION_ALIAS);
		} else {
			res = obtenerErrorGeneral();
		}
		return res;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.cuentas.bo.CasuisticaAliasCuenta#
	 * crearRespuestaEditar(ar.com.santanderrio.obp.generated.webservices.alias.
	 * ResponseAlias, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Respuesta<ComprobanteAltaCBUDTO> crearRespuestaEditar(ResponseAlias response, String aliasCbu,
			String numeroCuenta, String reasigna) {
		if (RESPONSE_OK.equals(response.getEstado())) {
			String codigo = CodigoMensajeConstantes.CODIGO_ASIGNACION_ALIAS_OK;
			if (reasigna != null) {
				codigo = CodigoMensajeConstantes.CODIGO_REASIGNACION_ALIAS_OK;
			}
			return respuestaFactory.crearRespuestaOk(ComprobanteAltaCBUDTO.class,
					new ComprobanteAltaCBUDTO(getMensajePorCodigo(codigo), aliasCbu, numeroCuenta));
		} else if (RESPONSE_CONFIRMAR.equals(response.getEstado()) || RESPONSE_REASIGNA.equals(response.getEstado())) {
			Respuesta<ComprobanteAltaCBUDTO> respuesta = respuestaFactory.crearRespuestaWarning(StringUtils.EMPTY,
					TipoError.REASIGNACION_ALIAS_CBU, CodigoMensajeConstantes.REASIGNACION_ALIAS_CBU);
			ComprobanteAltaCBUDTO comprobanteAltaCBUDTO = new ComprobanteAltaCBUDTO(
					getMensajePorCodigo(CodigoMensajeConstantes.CODIGO_ASIGNACION_ALIAS_OK), aliasCbu, numeroCuenta);
			comprobanteAltaCBUDTO.setReasigna(response.getReasigna());
			respuesta.setRespuesta(comprobanteAltaCBUDTO);
			return respuesta;
		} else {
			if (RESPONSE_ERROR.equals(response.getEstado())) {
				LOGGER.debug("EditarAliasCBU=tipo de error:" + response.getEstado() + "--- Mensaje error:"
						+ response.getError().getMensaje() + "---Codigo error" + response.getError().getCodigo() + ";");
			}
			return crearRespuestaErrorEditar(response.getError().getCodigo(), aliasCbu, numeroCuenta, reasigna);
		}
	}

	/**
	 * Crear respuesta error editar.
	 *
	 * @param codigo
	 *            the codigo
	 * @param aliasCbu
	 *            the alias cbu
	 * @param nroCuenta
	 *            the nro cuenta
	 * @param reasigna
	 *            the reasigna
	 * @return the respuesta
	 */
	private Respuesta<ComprobanteAltaCBUDTO> crearRespuestaErrorEditar(String codigo, String aliasCbu, String nroCuenta,
			String reasigna) {
		Respuesta<ComprobanteAltaCBUDTO> res;
		if (ALIAS_AGREGADO_INACTIVA2.equals(codigo) || ALIAS_AGREGADO_INACTIVA3.equals(codigo)) {
			if (reasigna != null) {
				codigo = CodigoMensajeConstantes.CODIGO_REASIGNACION_ALIAS_OK;
			}
			return respuestaFactory.crearRespuestaOk(ComprobanteAltaCBUDTO.class,
					new ComprobanteAltaCBUDTO(getMensajePorCodigo(codigo), aliasCbu, nroCuenta));
		} else if (ALIAS_YA_USADO.equals(codigo)) {
			res = respuestaFactory.crearRespuestaError("aliasEnUso", TipoError.ERROR_ALIAS_USADO, ALIAS_USADO);
		} else if (ALIAS_NO_ESPECIFICADO.equals(codigo)) {
			res = respuestaFactory.crearRespuestaError("aliasNoEspecificado", TipoError.ERROR_ALIAS_NO_ESPECIFICADO,
					ALIAS_NO_ESPECIF);
		} else if (ALIAS_YA_ASIGNADO_CUIT_DIFERENTE.equals(codigo)) {
			res = respuestaFactory.crearRespuestaError("aliasConCuitDiferente", TipoError.ERROR_ALIAS_USADO,
					ALIAS_YA_ASIGNADO_CUIT_DIF);
		} else {
			res = obtenerErrorGeneralEditarAlias();
		}
		return res;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.cuentas.bo.CasuisticaAliasCuenta#
	 * crearRespuestaOkBaja(ar.com.santanderrio.obp.generated.webservices.alias.
	 * ResponseAlias, java.lang.String, java.lang.String)
	 */
	@Override
	public Respuesta<ComprobanteAltaCBUDTO> crearRespuestaOkBaja(ResponseAlias response, String alias,
			String nroCuenta) {

		return respuestaFactory.crearRespuestaOk(ComprobanteAltaCBUDTO.class, new ComprobanteAltaCBUDTO(
				getMensajePorCodigo(CodigoMensajeConstantes.RESPUESTA_OK_ELIMINAR_ALIAS_CBU), alias, nroCuenta));
	}

	/**
	 * Gets the mensaje por codigo.
	 *
	 * @param codigoMensaje
	 *            the codigo mensaje
	 * @return the mensaje por codigo
	 */
	private String getMensajePorCodigo(String codigoMensaje) {

		return mensajeBO.obtenerMensajePorCodigo(codigoMensaje).getMensaje();
	}

}
