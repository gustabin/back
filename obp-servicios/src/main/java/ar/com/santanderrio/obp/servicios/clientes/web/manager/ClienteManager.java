/**
 * 
 */
package ar.com.santanderrio.obp.servicios.clientes.web.manager;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import org.springframework.security.core.Authentication;

import ar.com.santanderrio.base.web.view.FeedbackMensajeView;
import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.clientes.entities.CredencialCliente;
import ar.com.santanderrio.obp.base.clientes.entities.ResumenCliente;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.web.view.CambioDatosContactoResponse;
import ar.com.santanderrio.obp.servicios.clientes.web.view.CambioDatosContactoView;
import ar.com.santanderrio.obp.servicios.clientes.web.view.CambioUsuarioView;
import ar.com.santanderrio.obp.servicios.clientes.web.view.MarcaAPNHResponse;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.login.view.LoginResponseView;
import ar.com.santanderrio.obp.servicios.login.view.LoginView;

/**
 * The Interface ClienteManager.
 *
 * @author Jonatan_Bocian
 */
public interface ClienteManager {

    /**
     * Obtiene los datos de un cliente.
     *
     * @param resumenCliente
     *            the resumen cliente
     * @return the respuesta
     */
    Respuesta<Cliente> obtenerCliente(ResumenCliente resumenCliente);

    /**
     * Obtener cliente sesion.
     *
     * @return the cliente
     */
    ResumenCliente obtenerClienteSesion();

    /**
     * retona parametros de session para el cliente.
     *
     * @return the sesion parametros
     */
    SesionParametros obtenerSesionParametros();

    /**
	 * Custom authenticate.
	 *
	 * @param authentication
	 *            the authentication
	 * @param loginView
	 *            the login view
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the respuesta
	 */
    Respuesta<LoginResponseView> login(Authentication authentication, LoginView loginView, HttpServletRequest request, HttpServletResponse response);

	/**
	 * Login donde se trata el token APP
	 * @param credencialCliente
	 * @param rsaGenericRequestData
	 * @param loginView
	 * @param request
	 * @param response
	 * @return
	 */
	Respuesta<LoginResponseView> loginTokenApp(Authentication authentication, LoginView loginView, HttpServletRequest request, HttpServletResponse response);
	
    /**
	 * Realizar control sesion obtener cliente con saldo.
	 *
	 * @param resumenCliente
	 *            the resumen cliente
	 * @param credencialCliente
	 *            the credencial cliente
	 * @param loginView
	 *            the login view
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the respuesta
	 */
    Respuesta<LoginResponseView> realizarControlSesionObtenerClienteConSaldo(ResumenCliente resumenCliente,
            CredencialCliente credencialCliente, LoginView loginView, HttpServletRequest request, HttpServletResponse response);

    /**
     * Login CUE confirmar usuario.
     *
     * @param credencialCliente
     *            the credencial cliente
     * @param request
	 *            the request
     * @return the respuesta
     */
    Respuesta<FeedbackMensajeView> loginConfirmarUsuario(CredencialCliente credencialCliente, HttpServletRequest request);

    /**
     * Login definir usuario.
     *
     * @param credencialCliente
     *            the credencial cliente
     * @return the respuesta
     */
    Respuesta<FeedbackMensajeView> loginDefinirUsuario(CredencialCliente credencialCliente);

    /**
     * Cambio usuario pendiente.
     *
     * @param credencialCliente
     *            the credencial cliente
     * @return the respuesta
     */
    Respuesta<FeedbackMensajeView> cambioUsuarioPendiente(CredencialCliente credencialCliente);

    /**
	 * Cambio usuario.
	 *
	 * @param request
	 *            the request
	 * @param cambioUsuarioView
	 *            the cambio usuario view
	 * @return the respuesta
	 */
    Respuesta<CambioUsuarioView> cambioUsuario(HttpServletRequest request, CambioUsuarioView cambioUsuarioView);

    /**
     * Gets the marca APNH.
     *
     * @return the marca APNH
     */
    Respuesta<MarcaAPNHResponse> getMarcaAPNH();

    /**
     * Cambio datos contacto.
     *
     * @param cambioDatosContacto
     *            the cambio datos contacto
     * @param isMail
     *            the is mail
     * @return the respuesta
     */
    Respuesta<CambioDatosContactoView> cambioDatosContacto(CambioDatosContactoView cambioDatosContacto,
            boolean isMail);

    /**
     * Gets the datos contacto.
     *
     * @return the datos contacto
     */
    Respuesta<CambioDatosContactoResponse> getDatosContacto();
    
    /**
	 * Obtiene la antiguedad en dias del ultimo cambio de clave y token
	 * @param nup
	 * @return
	 * @throws BusinessException
	 */
	Respuesta<List<BigDecimal>> obtenerAntiguedadDiasUltCambioClaveToken(Long nup);

	Respuesta<Void> getEstadoCensoEconomico();

	Respuesta<ReporteView> descargarDatosPersonalesPDF(ReporteView reporteView);
}
