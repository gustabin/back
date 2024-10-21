/*
 * 
 */
package ar.com.santanderrio.obp.servicios.login.manager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.com.santanderrio.obp.servicios.biocatch.dto.BiocatchResponseDataDTO;
import org.apache.cxf.jaxrs.ext.MessageContext;

import ar.com.santanderrio.base.web.view.FeedbackMensajeView;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.login.view.CredencialesLoginView;
import ar.com.santanderrio.obp.servicios.login.view.CsidView;
import ar.com.santanderrio.obp.servicios.login.view.DatosMyaView;
import ar.com.santanderrio.obp.servicios.login.view.InicioLoginView;
import ar.com.santanderrio.obp.servicios.login.view.LogOutResponseView;
import ar.com.santanderrio.obp.servicios.login.view.LoginResponseView;
import ar.com.santanderrio.obp.servicios.login.view.LoginView;

// TODO: Auto-generated Javadoc
/**
 * Interface preparada para gestionar Login de la aplicacion Created by
 * pablo.martin.gore on 9/2/2016.
 */
public interface LoginManager {

    /**
	 * Primer authenticacion dentro de la aplicacion, entra con encriptacion RSA
	 * o json plano si estuviera apagado.
	 *
	 * @param datoEntrada
	 *            the dato entrada
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the respuesta
	 */
    Respuesta<LoginResponseView> login(String datoEntrada, HttpServletRequest request, HttpServletResponse response);

    /**
	 * Poder gestionar el prendido y apagado de la vuelta de obe a tbanco.
	 *
	 * @param loginView
	 *            the login view
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the respuesta
	 */
    
    /**
     * Login APP.
     *
     * @param loginView the login view
     * @param request the request
     * @return the respuesta
     */
    Respuesta<LoginResponseView> loginAPP(LoginView loginView, HttpServletRequest request, HttpServletResponse response);
    
    /**
     * Login OBE.
     *
     * @param loginView the login view
     * @param request the request
     * @return the respuesta
     */
    Respuesta<LoginResponseView> loginOBE(LoginView loginView, HttpServletRequest request, HttpServletResponse response);

    /**
     * logout de la aplicacion.
     *
     * @param request
     *            the request
     * @return the respuesta
     */
    Respuesta<LogOutResponseView> logout(HttpServletRequest request);

    /**
     * Liberar la session en los flujos previos al login ok (antes de llegar a la
     * home).
     *
     * @param request
     *            the request
     * @return the respuesta
     */
    Respuesta<LogOutResponseView> release(HttpServletRequest request);

    /**
     * Refresh token.
     *
     * @param request
     *            the request
     * @param response
	 *            the response
     * @return the respuesta
     */
    Respuesta<LoginResponseView> refresh(HttpServletRequest request, HttpServletResponse response);

    /**
     * Obtener incio login.
     *
     * @return the respuesta
     */
    Respuesta<InicioLoginView> obtenerIncioLogin();

    /**
     * Grabar estadistica usuario clave expiradas.
     *
     * @return the respuesta
     */
    Respuesta<Void> grabarEstadisticaUsuarioClaveExpiradas();

    /**
	 * Login CUE confirmar usuario.
	 *
	 * @param datoEntrada
	 *            the dato entrada
	 * @param mc
	 *            the mc
	 * @return the respuesta
	 */
    Respuesta<FeedbackMensajeView> loginConfirmarUsuario(String datoEntrada, MessageContext mc);

    /**
	 * Login CUE confirmar clave.
	 *
	 * @param datoEntrada
	 *            the dato entrada
	 * @param mc
	 *            the mc
	 * @return the respuesta
	 */
    Respuesta<Void> loginConfirmarClave(String datoEntrada, MessageContext mc);

    /**
     * Login CUE ingreso home.
     *
     * @param request
     *            the request
	 * @param response
	 *            the response
     * @return the respuesta
     */
    Respuesta<LoginResponseView> loginIngresoHome(HttpServletRequest request, HttpServletResponse response);

    /**
	 * Definir nuevo usuario, seginform isAlta.
	 *
	 * @param request
	 *            the request
	 * @param datoEntrada
	 *            the dato entrada
	 * @return the respuesta
	 */
    Respuesta<FeedbackMensajeView> loginDefinirUsuario(HttpServletRequest request, String datoEntrada);

    /**
	 * Cambio usuario pendiente.
	 *
	 * @param request
	 *            the request
	 * @param credencialesLoginView
	 *            the credenciales login view
	 * @return the respuesta
	 */
    Respuesta<FeedbackMensajeView> cambioUsuarioPendiente(HttpServletRequest request,
            CredencialesLoginView credencialesLoginView);

    /**
     * Confirmar datos mya.
     *
     * @param datosMyaView
     *            the datos mya view
     * @param request
     *            the request
     * @return the respuesta
     */
    Respuesta<LoginResponseView> confirmarDatosMya(DatosMyaView datosMyaView, HttpServletRequest request);

    /**
     * Confirmar datos mya.
     *
     * @return the respuesta
     */
    Respuesta<ReporteView> obtenerTerminosCondicionesPDF();

    /**
	 * marcar un flag en sesion si corresponde consultar al store para ver si
	 * salta a obp o se queda en tbanco.
	 *
	 * @param mc
	 *            the mc
	 */
    void cargarFlagFreeTbanco(MessageContext mc);

	/**
	 * Analyze rsa.
	 *
	 * @author juan.pablo.gatto
	 * @return the respuesta
	 */
	Respuesta<Void> analyzeRsa(String lugarIngreso, BiocatchResponseDataDTO biocatchData, boolean fromApp, boolean fromObe);
	
	Respuesta<CsidView> obtenerNuevoCsid();
	
}
