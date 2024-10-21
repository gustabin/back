/*
 * 
 */
package ar.com.santanderrio.obp.servicios.login.sei.impl;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.ext.MessageContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.base.web.view.FeedbackMensajeView;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.login.manager.LoginManager;
import ar.com.santanderrio.obp.servicios.login.sei.LoginSEI;
import ar.com.santanderrio.obp.servicios.login.view.CredencialesLoginView;
import ar.com.santanderrio.obp.servicios.login.view.CsidView;
import ar.com.santanderrio.obp.servicios.login.view.DatosMyaView;
import ar.com.santanderrio.obp.servicios.login.view.InicioLoginView;
import ar.com.santanderrio.obp.servicios.login.view.LogOutResponseView;
import ar.com.santanderrio.obp.servicios.login.view.LoginResponseView;
import ar.com.santanderrio.obp.servicios.login.view.LoginView;
import ar.com.santanderrio.obp.servicios.monitoreo.manager.MonitoreoManager;

// TODO: Auto-generated Javadoc
/**
 * The Class LoginSEIImpl.
 */
/**
 * @author sergio.e.goldentair
 *
 */
@Component("loginSEI")
public class LoginSEIImpl implements LoginSEI {

    /** The login manager. */
    @Autowired
    private LoginManager loginManager;

    /** The monitoreo manager. */
    @Autowired
    private MonitoreoManager monitoreoManager;

    /**
     * Login.
     *
     * @param datoEntrada the dato entrada
     * @param mc the mc
     * @return the respuesta
     */
    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.login.sei.LoginSEI#login(java.lang.String,
     * org.apache.cxf.jaxrs.ext.MessageContext)
     */
    @Override
    public Respuesta<LoginResponseView> login(String datoEntrada, org.apache.cxf.jaxrs.ext.MessageContext mc) {
        loginManager.cargarFlagFreeTbanco(mc);
        return loginManager.login(datoEntrada, mc.getHttpServletRequest(), mc.getHttpServletResponse());
    }

    /**
     * Login OBE.
     *
     * @param loginView the login view
     * @param mc the mc
     * @return the respuesta
     */
    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.login.sei.LoginSEI#loginOBE(ar.com.santanderrio.obp.servicios.login.view.LoginView, org.apache.cxf.jaxrs.ext.MessageContext)
     */
    @Override
    public Respuesta<LoginResponseView> loginOBE(LoginView loginView, org.apache.cxf.jaxrs.ext.MessageContext mc) {
        loginManager.cargarFlagFreeTbanco(mc);
        return loginManager.loginOBE(loginView, mc.getHttpServletRequest(), mc.getHttpServletResponse()); 
    }

    /**
     * Login APP.
     *
     * @param loginView the login view
     * @param mc the mc
     * @return the respuesta
     */
    @Override
    public Respuesta<LoginResponseView> loginAPP(LoginView loginView, org.apache.cxf.jaxrs.ext.MessageContext mc) {
        loginManager.cargarFlagFreeTbanco(mc);
        return loginManager.loginAPP(loginView, mc.getHttpServletRequest(), mc.getHttpServletResponse()); 
    }
    
    /**
     * Do login homonimo.
     *
     * @param datoEntrada the dato entrada
     * @param mc the mc
     * @return the respuesta
     */
    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.login.sei.LoginSEI#doLoginHomonimo(java.
     * lang.String, org.apache.cxf.jaxrs.ext.MessageContext)
     */
    @Override
    public Respuesta<LoginResponseView> doLoginHomonimo(String datoEntrada,
            org.apache.cxf.jaxrs.ext.MessageContext mc) {
        loginManager.cargarFlagFreeTbanco(mc);
        return loginManager.login(datoEntrada, mc.getHttpServletRequest(), mc.getHttpServletResponse());
    }

    /**
     * Logout.
     *
     * @param mc the mc
     * @return the respuesta
     */
    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.login.sei.LoginSEI#logout(org.apache.
     * cxf.jaxrs.ext.MessageContext)
     */
    @Override
    public Respuesta<LogOutResponseView> logout(org.apache.cxf.jaxrs.ext.MessageContext mc) {
        return loginManager.logout(mc.getHttpServletRequest());
    }

    /**
     * Refresh.
     *
     * @param mc the mc
     * @return the respuesta
     */
    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.login.sei.LoginSEI#refresh(org.apache.
     * cxf.jaxrs.ext.MessageContext)
     */
    @Override
    public Respuesta<LoginResponseView> refresh(org.apache.cxf.jaxrs.ext.MessageContext mc) {
        return loginManager.refresh(mc.getHttpServletRequest(), mc.getHttpServletResponse());
    }

    /**
     * Obtener inicio login.
     *
     * @return the respuesta
     */
    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.login.sei.LoginSEI#obtenerInicioLogin()
     */
    @Override
    public Respuesta<InicioLoginView> obtenerInicioLogin() {
        return loginManager.obtenerIncioLogin();
    }

    /**
     * Inicio clave usuario expirados.
     *
     * @param mc the mc
     * @return the respuesta
     */
    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.login.sei.LoginSEI#
     * inicioClaveUsuarioExpirados(org.apache.cxf.jaxrs.ext.MessageContext)
     */
    @Override
    public Respuesta<Void> inicioClaveUsuarioExpirados(MessageContext mc) {
        return loginManager.grabarEstadisticaUsuarioClaveExpiradas();
    }

    /**
     * Login CUE confirmar usuario.
     *
     * @param datoEntrada the dato entrada
     * @param mc the mc
     * @return the respuesta
     */
    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.login.sei.LoginSEI#loginCUEConfirmarUsuario
     * (java.lang.String, org.apache.cxf.jaxrs.ext.MessageContext)
     */
    @Override
    public Respuesta<FeedbackMensajeView> loginCUEConfirmarUsuario(String datoEntrada, MessageContext mc) {
        loginManager.cargarFlagFreeTbanco(mc);
        return loginManager.loginConfirmarUsuario(datoEntrada, mc);
    }

    /**
     * Login CUE confirmar clave.
     *
     * @param datoEntrada the dato entrada
     * @param mc the mc
     * @return the respuesta
     */
    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.login.sei.LoginSEI#loginCUEConfirmarClave(
     * java.lang.String, org.apache.cxf.jaxrs.ext.MessageContext)
     */
    @Override
    public Respuesta<Void> loginCUEConfirmarClave(String datoEntrada, MessageContext mc) {
        return loginManager.loginConfirmarClave(datoEntrada, mc);
    }

    /**
     * Login ingreso home.
     *
     * @param mc the mc
     * @return the respuesta
     */
    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.login.sei.LoginSEI#loginIngresoHome(org
     * .apache.cxf.jaxrs.ext.MessageContext)
     */
    @Override
    public Respuesta<LoginResponseView> loginIngresoHome(MessageContext mc) {
        return loginManager.loginIngresoHome(mc.getHttpServletRequest(), mc.getHttpServletResponse());
    }

    /**
     * Login definir usuario.
     *
     * @param datoEntrada the dato entrada
     * @param mc the mc
     * @return the respuesta
     */
    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.login.sei.LoginSEI#loginDefinirUsuario(java
     * .lang.String, org.apache.cxf.jaxrs.ext.MessageContext)
     */
    @Override
    public Respuesta<FeedbackMensajeView> loginDefinirUsuario(String datoEntrada, MessageContext mc) {
        loginManager.cargarFlagFreeTbanco(mc);
        return loginManager.loginDefinirUsuario(mc.getHttpServletRequest(), datoEntrada);

    }

    /**
     * Cambio usuario pendiente.
     *
     * @param credencialesLoginView the credenciales login view
     * @param mc the mc
     * @return the respuesta
     */
    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.login.sei.LoginSEI#
     * cambioUsuarioPendiente(ar.com.santanderrio.obp.servicios.login.view.
     * CredencialesLoginView, org.apache.cxf.jaxrs.ext.MessageContext)
     */
    @Override
    public Respuesta<FeedbackMensajeView> cambioUsuarioPendiente(CredencialesLoginView credencialesLoginView,
            MessageContext mc) {
        loginManager.cargarFlagFreeTbanco(mc);
        return loginManager.cambioUsuarioPendiente(mc.getHttpServletRequest(), credencialesLoginView);
    }

    /**
     * Confirmar datos mya.
     *
     * @param datosMyaView the datos mya view
     * @param mc the mc
     * @return the respuesta
     */
    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.login.sei.LoginSEI#confirmarDatosMya(ar
     * .com.santanderrio.obp.servicios.login.view.DatosMyaView,
     * org.apache.cxf.jaxrs.ext.MessageContext)
     */
    @Override
    public Respuesta<LoginResponseView> confirmarDatosMya(DatosMyaView datosMyaView, MessageContext mc) {
        return loginManager.confirmarDatosMya(datosMyaView, mc.getHttpServletRequest());
    }

    /**
     * Obtener terminos condiciones PDF.
     *
     * @return the respuesta
     */
    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.login.sei.LoginSEI#
     * obtenerTerminosCondicionesPDF()
     */
    @Override
    public Respuesta<ReporteView> obtenerTerminosCondicionesPDF() {
        return loginManager.obtenerTerminosCondicionesPDF();
    }

    /**
     * Release.
     *
     * @param mc the mc
     * @return the respuesta
     */
    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.login.sei.LoginSEI#release(org.apache.
     * cxf.jaxrs.ext.MessageContext)
     */
    @Override
    public Respuesta<LogOutResponseView> release(MessageContext mc) {
        return loginManager.release(mc.getHttpServletRequest());
    }

    /**
     * Monitoreo.
     *
     * @return the response
     */
    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.login.sei.LoginSEI#monitoreo()
     */
    @Override
    public Response monitoreo() {
        return Response.status(Response.Status.OK).entity(monitoreoManager.obtenerHTML())
                .header("Content-Type", MediaType.TEXT_HTML).build();
    }

	@Override
	public Respuesta<CsidView> obtenerNuevoCsid() {
		return loginManager.obtenerNuevoCsid();
	}

}