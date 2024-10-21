/*
 * 
 */
package ar.com.santanderrio.obp.servicios.clave.online.manager;

import javax.servlet.http.HttpServletRequest;

import org.apache.cxf.jaxrs.ext.MessageContext;

import ar.com.santanderrio.obp.base.clientes.entities.CredencialCliente;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clave.online.entities.CredencialesClaveOnline;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.SesionExpiradaException;
import ar.com.santanderrio.obp.servicios.clave.online.view.AltaClaveOnlineView;
import ar.com.santanderrio.obp.servicios.clave.online.view.HashView;
import ar.com.santanderrio.obp.servicios.clave.online.view.MetodoAutenticacionView;
import ar.com.santanderrio.obp.servicios.clave.online.view.PreguntaAutenticacionView;
import ar.com.santanderrio.obp.servicios.clave.online.view.TarjetaBanelcoView;
import ar.com.santanderrio.obp.servicios.clientes.entities.CelularMyaYCompaniasTelResponse;
import ar.com.santanderrio.obp.servicios.clientes.web.view.CambioDatosContactoView;

/**
 * Created by B043069.
 */
public interface ClaveOnlineManager {

    /**
     * Solucionar problemas.
     *
     * @return the respuesta
     */

    Respuesta<Mensaje> grabarEstadisticaSolucionar();

    /**
     * Obtiene la pregunta solicitada.
     *
     * @return the pregunta autenticacion view
     */
    PreguntaAutenticacionView obtenerPreguntaSeguridadView();

    /**
     * Identificar cliente solucionar.
     *
     * @param mc
     *            the mc
     * @return the respuesta
     */
    Respuesta<Void> identificarClienteSolucionar(MessageContext mc);

    /**
     * Identificar cliente registrar.
     *
     * @param mc
     *            the mc
     * @return the respuesta
     */
    Respuesta<Void> identificarClienteRegistrar(MessageContext mc);

    /**
     * Identificar cliente renovar.
     *
     * @param mc
     *            the mc
     * @return the respuesta
     */
    Respuesta<Void> identificarClienteRenovar(MessageContext mc);

    /**
	 * Confirmar respuesta.
	 *
	 * @param request
	 *            the request
	 * @param datoEntrada
	 *            the dato entrada
	 * @return the respuesta
	 */
    Respuesta<MetodoAutenticacionView> confirmarRespuesta(HttpServletRequest request, String datoEntrada);

    /**
     * Identificar cliente.
     *
     * @param credencialesClaveOnline
     *            the credenciales clave online
     * @param request
     *            the request
     * @return the respuesta
     */
    Respuesta<Void> identificarCliente(CredencialesClaveOnline credencialesClaveOnline, HttpServletRequest request);

    /**
	 * Confirmar datos.
	 *
	 * @param request
	 *            the request
	 * @param datoEntrada
	 *            the dato entrada
	 * @return the respuesta
	 */
    Respuesta<MetodoAutenticacionView> confirmarDatos(HttpServletRequest request, String datoEntrada);

    /**
     * Enviar SMS.
     *
     * @param request
     *            the request
     * @param reenvio
     *            the reenvio
     * @return the respuesta
     */
    Respuesta<MetodoAutenticacionView> enviarSMS(HttpServletRequest request, Boolean reenvio, Boolean checkWhatsapp);

    /**
	 * Confirmar metodo autenticacion.
	 *
	 * @param mc
	 *            the mc
	 * @param datoEntrada
	 *            the dato entrada
	 * @return the respuesta
	 */
    Respuesta<MetodoAutenticacionView> confirmarMetodoAutenticacion(MessageContext mc, String datoEntrada);

    /**
     * Reintentar preguntas.
     *
     * @param request
     *            the request
     * @return the respuesta
     */
    Respuesta<MetodoAutenticacionView> reintentarPreguntas(HttpServletRequest request);

    /**
     * Alta SGI clave.
     *
     * @param credencialCliente
     *            the credencial cliente
     * @param mc
     *            *
     * @return the respuesta
     */
    Respuesta<AltaClaveOnlineView> altaSGIClave(CredencialCliente credencialCliente, MessageContext mc);

    /**
     * Controlar time out sesion.
     *
     * @param request
     *            the request
     * @throws SesionExpiradaException
     *             the sesion expirada exception
     */
    void controlarTimeOutSesion(HttpServletRequest request) throws SesionExpiradaException;

    /**
     * Crear respuesta sesion expirada.
     *
     * @return the respuesta
     */
    Respuesta<MetodoAutenticacionView> crearRespuestaSesionExpirada();

    /**
     * Validar hash.
     *
     * @param hash
     *            the hash
     * @return the respuesta
     */
    Respuesta<Void> validarHash(HashView hash);

    /**
     * *.
     *
     * @return the respuesta
     */
    Respuesta<HashView> generarHash();
    
    /**
     * 
     * @param request
     * @param datoEntrada
     * @return
     */
    Respuesta<MetodoAutenticacionView> confirmarRespuestaTelefono(HttpServletRequest request, String datoEntrada);

	
	
	/**
	 * 
	 * @param cambioDatosContacto
	 * @return
	 */
	Respuesta<CambioDatosContactoView> actualizarNumeroCelular(CambioDatosContactoView cambioDatosContacto);

	/**
	 * 
	 * @return
	 */
	Respuesta<CelularMyaYCompaniasTelResponse> obtenerNumeroCelularYcompanias();

	Respuesta<TarjetaBanelcoView> obtenerTarjetaParaValidarPin(HttpServletRequest request);
	
	Respuesta<TarjetaBanelcoView> validarPinBanelco(HttpServletRequest request, TarjetaBanelcoView banelcoView);


}
