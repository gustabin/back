/*
 * 
 */
package ar.com.santanderrio.obp.servicios.perfil.sei.impl;

import java.util.List;

import org.apache.cxf.jaxrs.ext.MessageContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.base.web.view.FeedbackMensajeView;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.blanqueopin.web.manager.BlanqueoPinManager;
import ar.com.santanderrio.obp.servicios.blanqueopin.web.view.BlanqueoPinInicioView;
import ar.com.santanderrio.obp.servicios.bonificacionesvigentes.view.BonificacionVigenteView;
import ar.com.santanderrio.obp.servicios.clientes.web.manager.ClaveUsuarioManager;
import ar.com.santanderrio.obp.servicios.clientes.web.manager.ClienteManager;
import ar.com.santanderrio.obp.servicios.clientes.web.view.CambioClaveUsuarioView;
import ar.com.santanderrio.obp.servicios.clientes.web.view.CambioDatosContactoResponse;
import ar.com.santanderrio.obp.servicios.clientes.web.view.CambioDatosContactoView;
import ar.com.santanderrio.obp.servicios.clientes.web.view.CambioUsuarioView;
import ar.com.santanderrio.obp.servicios.clientes.web.view.ComprobanteClaveUsuarioView;
import ar.com.santanderrio.obp.servicios.clientes.web.view.MarcaAPNHResponse;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.login.manager.MyaManager;
import ar.com.santanderrio.obp.servicios.mya.web.view.ConfirmarMailViewIn;
import ar.com.santanderrio.obp.servicios.mya.web.view.DescargaContratoViewIn;
import ar.com.santanderrio.obp.servicios.mya.web.view.InicioGeneralSuscripcionMyAView;
import ar.com.santanderrio.obp.servicios.mya.web.view.InicioProductoMyAViewIn;
import ar.com.santanderrio.obp.servicios.mya.web.view.InicioProductoSuscripcionMyAView;
import ar.com.santanderrio.obp.servicios.mya.web.view.MyaUpdateMensajeView;
import ar.com.santanderrio.obp.servicios.perfil.entities.ContratosPerfil;
import ar.com.santanderrio.obp.servicios.perfil.manager.CambioDomicilioManager;
import ar.com.santanderrio.obp.servicios.perfil.manager.ContratosManager;
import ar.com.santanderrio.obp.servicios.perfil.manager.PerfilManager;
import ar.com.santanderrio.obp.servicios.perfil.manager.PreguntasSeguridadManager;
import ar.com.santanderrio.obp.servicios.perfil.sei.PerfilSEI;
import ar.com.santanderrio.obp.servicios.perfil.view.BlanqueoPinView;
import ar.com.santanderrio.obp.servicios.perfil.view.CambioNombreView;
import ar.com.santanderrio.obp.servicios.perfil.view.ConfirmarMailView;
import ar.com.santanderrio.obp.servicios.perfil.view.ConsultaPerfil;
import ar.com.santanderrio.obp.servicios.perfil.view.LogoutAppView;
import ar.com.santanderrio.obp.servicios.perfil.web.view.CambioDomicilioView;
import ar.com.santanderrio.obp.servicios.perfil.web.view.ComprobanteCambioDomicilioView;
import ar.com.santanderrio.obp.servicios.perfil.web.view.ComprobantePreguntasSeguridadView;
import ar.com.santanderrio.obp.servicios.perfil.web.view.PerfilDetalleDeudorView;
import ar.com.santanderrio.obp.servicios.perfil.web.view.PerfilView;
import ar.com.santanderrio.obp.servicios.perfil.web.view.PreguntasSeguridadView;

/**
 * The Class PerfilSEIImpl.
 */
@Component
public class PerfilSEIImpl implements PerfilSEI {

    /** The perfil manager. */
    @Autowired
    private PerfilManager perfilManager;

    /** The cliente manager. */
    @Autowired
    private ClienteManager clienteManager;

    /** The clave usuario manager. */
    @Autowired
    private ClaveUsuarioManager claveUsuarioManager;

    /** The cambio domicilio manager. */
    @Autowired
    private CambioDomicilioManager cambioDomicilioManager;

    /** The preguntas seguridad manager. */
    @Autowired
    private PreguntasSeguridadManager preguntasSeguridadManager;

    /** The mya manager. */
    @Autowired
    private MyaManager myaManager;

    /** The contratos manager. */
    @Autowired
    private ContratosManager contratosManager;
    
    @Autowired
    private BlanqueoPinManager blanqueoPinManager;
    
    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.perfil.sei.PerfilSEI#obtenerPerfil()
     */
    @Override
    public Respuesta<PerfilView> obtenerPerfil() {
        return perfilManager.obtenerPerfil();
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.perfil.sei.PerfilSEI#
     * obtenerDetalleDeudor(
     * ar.com.santanderrio.obp.servicios.perfil.view.ConsultaPerfil)
     */
    @Override
    public Respuesta<PerfilDetalleDeudorView> obtenerDetalleDeudor(ConsultaPerfil consultaPerfil) {
        return perfilManager.obtenerDetalleDeudor(consultaPerfil);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.perfil.sei.PerfilSEI#getMarcaAPNH()
     */
    @Override
    public Respuesta<MarcaAPNHResponse> getMarcaAPNH() {
        return clienteManager.getMarcaAPNH();
    }

    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.perfil.sei.PerfilSEI#cambioUsuario(org.apache.cxf.jaxrs.ext.MessageContext, ar.com.santanderrio.obp.servicios.clientes.web.view.CambioUsuarioView)
     */
    @Override
    public Respuesta<CambioUsuarioView> cambioUsuario(MessageContext mc, CambioUsuarioView cambioUsuarioView) {
        return clienteManager.cambioUsuario(mc.getHttpServletRequest(), cambioUsuarioView);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.perfil.sei.PerfilSEI#cambioClaveUsuario
     * (org.apache.cxf.jaxrs.ext.MessageContext,
     * ar.com.santanderrio.obp.servicios.clientes.web.view.
     * CambioClaveUsuarioView)
     */
    @Override
    public Respuesta<CambioClaveUsuarioView> cambioClaveUsuario(MessageContext mc,
            CambioClaveUsuarioView cambioClaveUsuarioView) {
        return claveUsuarioManager.cambioUsuarioClave(mc.getHttpServletRequest(), cambioClaveUsuarioView);
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.clientes.sei.ClienteSEI#
     * cambioDatosContacto (ar.com.santanderrio.obp.servicios.clientes.web.view.
     * CambioDatosContactoView)
     */
    @Override
    public Respuesta<CambioDatosContactoView> cambioDatosMail(CambioDatosContactoView cambioDatosContacto) {
        return clienteManager.cambioDatosContacto(cambioDatosContacto, Boolean.TRUE);
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.clientes.sei.ClienteSEI#
     * cambioDatosContacto (ar.com.santanderrio.obp.servicios.clientes.web.view.
     * CambioDatosContactoView)
     */
    @Override
    public Respuesta<CambioDatosContactoView> cambioDatosCelular(CambioDatosContactoView cambioDatosContacto) {
        return clienteManager.cambioDatosContacto(cambioDatosContacto, Boolean.FALSE);
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.clientes.sei.ClienteSEI#
     * getDatosContacto()
     */
    @Override
    public Respuesta<CambioDatosContactoResponse> getDatosContacto() {
        return clienteManager.getDatosContacto();
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.perfil.sei.PerfilSEI#
     * getComprobanteClaveUsuario()
     */
    @Override
    public Respuesta<ComprobanteClaveUsuarioView> getComprobanteClaveUsuario() {
        return claveUsuarioManager.getComprobanteClaveUsuario();
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.perfil.sei.PerfilSEI#
     * descargarComprobante( java.lang.String)
     */
    @Override
    public Respuesta<ReporteView> descargarComprobante(String tipoComprobante) {
        return claveUsuarioManager.descargarComprobante(tipoComprobante);
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.perfil.sei.PerfilSEI#
     * obtenerInfoAdicionalDomTel(ar.com.santanderrio.obp.servicios.perfil.web.
     * view. CambioDomicilioView)
     */
    @Override
    public Respuesta<FeedbackMensajeView> obtenerInfoAdicionalDomTel(CambioDomicilioView cambioDomicilioView) {
        return cambioDomicilioManager.obtenerInfoAdicionalDomTel(cambioDomicilioView);
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.perfil.sei.PerfilSEI#
     * guardarCambioDomicilio
     * (ar.com.santanderrio.obp.servicios.perfil.web.view.CambioDomicilioView)
     */
    @Override
    public Respuesta<CambioDomicilioView> guardarCambioDomicilio(CambioDomicilioView cambioDomicilioView) {
        return cambioDomicilioManager.guardarCambioDomicilio(cambioDomicilioView);
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.perfil.sei.PerfilSEI#
     * normalizarDomicilio(ar
     * .com.santanderrio.obp.servicios.perfil.web.view.CambioDomicilioView)
     */
    @Override
    public Respuesta<List<CambioDomicilioView>> normalizarDomicilio(CambioDomicilioView cambioDomicilioView) {
        return cambioDomicilioManager.normalizarDomicilio(cambioDomicilioView);
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.perfil.sei.PerfilSEI#
     * verComprobanteCambioDomicilio()
     */
    @Override
    public Respuesta<ComprobanteCambioDomicilioView> verComprobanteCambioDomicilio() {
        return cambioDomicilioManager.verComprobante();
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.perfil.sei.PerfilSEI#
     * descargarComprobanteCambioDomicilio()
     */
    @Override
    public Respuesta<ReporteView> descargarComprobanteCambioDomicilio() {
        return cambioDomicilioManager.descargarComprobante();
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.perfil.sei.PerfilSEI#
     * consultarPreguntasSeguridad()
     */
    @Override
    public Respuesta<PreguntasSeguridadView> consultarPreguntasSeguridad() {
        return preguntasSeguridadManager.consultaPreguntasSeguridad();
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.perfil.sei.PerfilSEI#
     * guardarPreguntasSeguridad(ar.com.santanderrio.obp.servicios.perfil.web.
     * view. PreguntasSeguridadView)
     */
    @Override
    public Respuesta<PreguntasSeguridadView> guardarPreguntasSeguridad(PreguntasSeguridadView preguntasSeguridadView) {
        return preguntasSeguridadManager.guardarPreguntasSeguridad(preguntasSeguridadView);
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.perfil.sei.PerfilSEI#
     * verComprobantePreguntasSeguridad()
     */
    @Override
    public Respuesta<ComprobantePreguntasSeguridadView> verComprobantePreguntasSeguridad() {
        return preguntasSeguridadManager.verComprobante();
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.perfil.sei.PerfilSEI#
     * descargarComprobantePreguntasSeguridad()
     */
    @Override
    public Respuesta<ReporteView> descargarComprobantePreguntasSeguridad() {
        return preguntasSeguridadManager.descargarComprobante();
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.perfil.sei.PerfilSEI#
     * inicioGeneralSuscripcionMyA()
     */
    @Override
    public Respuesta<InicioGeneralSuscripcionMyAView> inicioGeneralSuscripcionMyA() {
        return myaManager.obtenerInicioMensajesAvisos();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.perfil.sei.PerfilSEI#inicioProductoMyA(
     * ar.com.santanderrio.obp.servicios.mya.web.view.InicioProductoMyAViewIn)
     */
    @Override
    public Respuesta<InicioProductoSuscripcionMyAView> inicioProductoMyA(InicioProductoMyAViewIn viewIn) {
        return myaManager.obtenerInicioProductoMyA(viewIn.getProducto());
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.perfil.sei.PerfilSEI#updateMensajes(ar.
     * com.santanderrio.obp.servicios.mya.web.view.MyaUpdateMensajeView)
     */
    @Override
    public Respuesta<Void> updateMensajes(MyaUpdateMensajeView datos) {
        return myaManager.updateMensajes(datos);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.perfil.sei.PerfilSEI#consultaContratos(
     * )
     */
    @Override
    public Respuesta<ContratosPerfil> consultaContratos() {
        return contratosManager.consultaContratos();
    }

    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.perfil.sei.PerfilSEI#estadisticaDescargaContrato(ar.com.santanderrio.obp.servicios.mya.web.view.DescargaContratoViewIn)
     */
    @Override
    public Respuesta<Void> estadisticaDescargaContrato(DescargaContratoViewIn viewIn) {
        return contratosManager.estadisticaDescargaContrato(viewIn.getStatContratoError());
    }

	@Override
	public Respuesta<BlanqueoPinInicioView> inicioBlanqueoPin() {
		return blanqueoPinManager.inicioBlanqueoPin();
	}

	@Override
	public Respuesta<BlanqueoPinView> blanqueoPinRSA(BlanqueoPinView blanqueoPin) {
		return blanqueoPinManager.blanquearPinRSA(blanqueoPin);
	}

	@Override
	public Respuesta<LogoutAppView> logoutAppConfiguracion() {
		return perfilManager.logoutAppConfiguracion();
	}
	
	
	@Override
	public Respuesta<LogoutAppView> logoutAppFeedback() {
		return perfilManager.logoutAppFeedback();
	}

	@Override
	public Respuesta<ConfirmarMailView> confirmarEmail(ConfirmarMailViewIn confirmarMailIn) {
		return perfilManager.confirmarEmail(confirmarMailIn);
	}

	@Override
	public Respuesta<List<BonificacionVigenteView>> obtenerBonificaciones() {
		return perfilManager.obtenerBonificaciones();
	}

	@Override
	public Respuesta<Void> grabarEstadisticaVerBonificaciones() {
		return perfilManager.grabarEstadisticaVerBonificaciones();
	}

	@Override
	public Respuesta<CambioNombreView> cambioNombreConfig() {
		return perfilManager.cambioNombreConfig();
	}

	@Override
	public Respuesta<CambioNombreView> cambioNombreFeedback(CambioNombreView cambioNombreView) {
		return perfilManager.cambioNombreFeedback(cambioNombreView.getNombreElegido());
	}

    @Override
    public Respuesta<ReporteView> descargarDatosPersonalesPDF(ReporteView reporteView) {
        return clienteManager.descargarDatosPersonalesPDF(reporteView);
    }

}
