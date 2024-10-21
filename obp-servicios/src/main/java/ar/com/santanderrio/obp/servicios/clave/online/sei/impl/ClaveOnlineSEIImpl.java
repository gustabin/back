/*
 * 
 */
package ar.com.santanderrio.obp.servicios.clave.online.sei.impl;

import org.apache.cxf.jaxrs.ext.MessageContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clave.online.manager.ClaveOnlineManager;
import ar.com.santanderrio.obp.servicios.clave.online.sei.ClaveOnlineSEI;
import ar.com.santanderrio.obp.servicios.clave.online.view.HashView;
import ar.com.santanderrio.obp.servicios.clave.online.view.MetodoAutenticacionView;
import ar.com.santanderrio.obp.servicios.clave.online.view.TarjetaBanelcoView;
import ar.com.santanderrio.obp.servicios.clientes.entities.CelularMyaYCompaniasTelResponse;
import ar.com.santanderrio.obp.servicios.clientes.web.view.CambioDatosContactoView;

/**
 * The Class ClaveOnlineSEIImpl.
 */
@Component("claveOnlineSEI")
public class ClaveOnlineSEIImpl implements ClaveOnlineSEI {

    /** The claveOnline manager. */
    @Autowired
    private ClaveOnlineManager claveOnlineManager;

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.clave.online.sei.ClaveOnlineSEI#
     * solucionarProblemas(org.apache.cxf.jaxrs.ext.MessageContext)
     */
    @Override
    public Respuesta<Mensaje> solucionarProblemas(MessageContext mc) {

        return claveOnlineManager.grabarEstadisticaSolucionar();
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.clave.online.sei.ClaveOnlineSEI#
     * identificarClienteSolucionar(org.apache.cxf.jaxrs.ext.MessageContext)
     */
    @Override
    public Respuesta<Void> identificarClienteSolucionar(MessageContext mc) {

        return claveOnlineManager.identificarClienteSolucionar(mc);
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.clave.online.sei.ClaveOnlineSEI#
     * identificarClienteRegistrar(org.apache.cxf.jaxrs.ext.MessageContext)
     */
    @Override
    public Respuesta<Void> identificarClienteRegistrar(MessageContext mc) {

        return claveOnlineManager.identificarClienteRegistrar(mc);
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.clave.online.sei.ClaveOnlineSEI#
     * identificarClienteRenovar(org.apache.cxf.jaxrs.ext.MessageContext)
     */
    @Override
    public Respuesta<Void> identificarClienteRenovar(MessageContext mc) {

        return claveOnlineManager.identificarClienteRenovar(mc);
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.clave.online.sei.ClaveOnlineSEI#
     * confirmarDatos(org.apache.cxf.jaxrs.ext.MessageContext, java.lang.String)
     */
    @Override
    public Respuesta<MetodoAutenticacionView> confirmarDatos(MessageContext mc, String datoEntrada) {
        return claveOnlineManager.confirmarDatos(mc.getHttpServletRequest(), datoEntrada);
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.clave.online.sei.ClaveOnlineSEI#
     * confirmarRespuesta(org.apache.cxf.jaxrs.ext.MessageContext, java.lang.String)
     */
    @Override
    public Respuesta<MetodoAutenticacionView> confirmarRespuesta(MessageContext mc, String datoEntrada) {
        return claveOnlineManager.confirmarRespuesta(mc.getHttpServletRequest(), datoEntrada);
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.clave.online.sei.ClaveOnlineSEI#
     * confirmarMetodoAutenticacion(org.apache.cxf.jaxrs.ext.MessageContext,
     * java.lang.String)
     */
    @Override
    public Respuesta<MetodoAutenticacionView> confirmarMetodoAutenticacion(MessageContext mc, String datoEntrada) {
        return claveOnlineManager.confirmarMetodoAutenticacion(mc, datoEntrada);
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.clave.online.sei.ClaveOnlineSEI#
     * reenviarSms(org.apache.cxf.jaxrs.ext.MessageContext)
     */
    @Override
    public Respuesta<MetodoAutenticacionView> reenviarSms(MessageContext mc) {
        return claveOnlineManager.enviarSMS(mc.getHttpServletRequest(), true,false);
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.clave.online.sei.ClaveOnlineSEI#
     * reintentarPreguntas(org.apache.cxf.jaxrs.ext.MessageContext)
     */
    @Override
    public Respuesta<MetodoAutenticacionView> reintentarPreguntas(MessageContext mc) {
        return claveOnlineManager.reintentarPreguntas(mc.getHttpServletRequest());
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.clave.online.sei.ClaveOnlineSEI#
     * validarHash(org.apache.cxf.jaxrs.ext.MessageContext,
     * ar.com.santanderrio.obp.servicios.clave.online.view.HashView)
     */
    @Override
    public Respuesta<Void> validarHash(MessageContext mc, HashView hash) {
        return claveOnlineManager.validarHash(hash);
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.clave.online.sei.ClaveOnlineSEI#
     * obtenerHash(org.apache.cxf.jaxrs.ext.MessageContext)
     */
    @Override
    public Respuesta<HashView> obtenerHash(MessageContext mc) {
        return claveOnlineManager.generarHash();
    }

	@Override
	public Respuesta<MetodoAutenticacionView> confirmarRespuestaTelefono(MessageContext mc, String datoEntrada) {
        return claveOnlineManager.confirmarRespuestaTelefono(mc.getHttpServletRequest(), datoEntrada);

	}

	

	@Override
	public Respuesta<CambioDatosContactoView> actualizarNumeroCelular(CambioDatosContactoView cambioDatosContacto) {
		return claveOnlineManager.actualizarNumeroCelular(cambioDatosContacto);
	}

	@Override
	public Respuesta<CelularMyaYCompaniasTelResponse> obtenerNumeroCelularYcompanias() {
		return this.claveOnlineManager.obtenerNumeroCelularYcompanias();
	}

	@Override
	public Respuesta<TarjetaBanelcoView> obtenerTarjetaParaValidarPin(MessageContext mc) {
		return claveOnlineManager.obtenerTarjetaParaValidarPin(mc.getHttpServletRequest());
	}

	@Override
	public Respuesta<TarjetaBanelcoView> validarPinBanelco(MessageContext mc, TarjetaBanelcoView banelcoView) {
		return claveOnlineManager.validarPinBanelco(mc.getHttpServletRequest(), banelcoView);
	}

}