/*
 * 
 */
package ar.com.santanderrio.obp.servicios.clientes.bo;

import javax.servlet.http.HttpServletRequest;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.clientes.entities.CredencialCliente;
import ar.com.santanderrio.obp.base.clientes.entities.ResumenCliente;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.CambioUsuarioEntity;
import ar.com.santanderrio.obp.servicios.exception.RobotException;

/**
 * The Interface CredencialesBO.
 */
public interface CredencialesBO {

    /**
     * Validar credenciales.
     *
     * @param cc
     *            the cc
     * @return the respuesta
     * @throws BusinessException
     *             the business exception
     */
    Respuesta<ResumenCliente> validarCredenciales(CredencialCliente cc) throws BusinessException;

    /**
     * Login CUE confirmar usuario.
     *
     * @param credencialCliente
     *            the credencial cliente
     * @return the respuesta
     */
    Respuesta<ResumenCliente> loginConfirmarUsuario(CredencialCliente credencialCliente);

    /**
     * Login definir usuario.
     *
     * @param credencialCliente
     *            the credencial cliente
     * @return the respuesta
     */
    Respuesta<ResumenCliente> loginDefinirUsuario(CredencialCliente credencialCliente);

    /**
     * Cambio usuario pendiente.
     *
     * @param credencialCliente
     *            the credencial cliente
     * @return the respuesta
     */
    Respuesta<ResumenCliente> cambioUsuarioPendiente(CredencialCliente credencialCliente);

    /**
	 * cambioUsuario.
	 *
	 * @param request
	 *            the request
	 * @param entity
	 *            the entity
	 * @param esSoloUsuario
	 *            the es solo usuario
	 * @return the respuesta
	 * @throws RobotException
	 *             the robot exception
	 */
    Respuesta<ResumenCliente> cambioUsuario(HttpServletRequest request, CambioUsuarioEntity entity,
            boolean esSoloUsuario);

	/**
	 * Valida las credenciales cuando viene de app
	 * @param cc
	 * @return
	 */
	Respuesta<ResumenCliente> validarCredencialesForApp(CredencialCliente cc) throws BusinessException;

}
