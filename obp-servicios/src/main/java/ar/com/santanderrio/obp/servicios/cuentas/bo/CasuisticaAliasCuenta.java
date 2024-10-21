/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.bo;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.generated.webservices.alias.ResponseAlias;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ComprobanteAltaCBUDTO;

/**
 * The Interface CasuisticaAliasCuenta.
 */
public interface CasuisticaAliasCuenta {

	/**
	 * Obtener error general.
	 *
	 * @return the respuesta
	 */
	Respuesta<ComprobanteAltaCBUDTO> obtenerErrorGeneral();

	/**
	 * Obtener error general editar alias.
	 *
	 * @return the respuesta
	 */
	Respuesta<ComprobanteAltaCBUDTO> obtenerErrorGeneralEditarAlias();

	/**
	 * Crear respuesta.
	 *
	 * @param response
	 *            the response
	 * @param alias
	 *            the alias
	 * @param nroCuenta
	 *            the nro cuenta
	 * @param reasigna
	 *            the reasigna
	 * @return the respuesta
	 */
	Respuesta<ComprobanteAltaCBUDTO> crearRespuesta(ResponseAlias response, String alias, String nroCuenta,
			String reasigna);

	/**
	 * Crear respuesta editar.
	 *
	 * @param response
	 *            the response
	 * @param aliasCbu
	 *            the alias cbu
	 * @param numeroCuenta
	 *            the numero cuenta
	 * @param reasigna
	 *            the reasigna
	 * @return the respuesta
	 */
	Respuesta<ComprobanteAltaCBUDTO> crearRespuestaEditar(ResponseAlias response, String aliasCbu, String numeroCuenta,
			String reasigna);

	/**
	 * Crear respuesta ok baja.
	 *
	 * @param response
	 *            the response
	 * @param alias
	 *            the alias
	 * @param nroCuenta
	 *            the nro cuenta
	 * @return the respuesta
	 */
	Respuesta<ComprobanteAltaCBUDTO> crearRespuestaOkBaja(ResponseAlias response, String alias, String nroCuenta);
}
