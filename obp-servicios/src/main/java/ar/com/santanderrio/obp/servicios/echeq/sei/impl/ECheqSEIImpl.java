package ar.com.santanderrio.obp.servicios.echeq.sei.impl;

import org.apache.cxf.jaxrs.ext.MessageContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.comun.OperacionesRSAEnum;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.echeq.enums.OperacionEcheqEnum;
import ar.com.santanderrio.obp.servicios.echeq.manager.ECheqManager;
import ar.com.santanderrio.obp.servicios.echeq.sei.ECheqSEI;
import ar.com.santanderrio.obp.servicios.echeq.view.BeneficiarioView;
import ar.com.santanderrio.obp.servicios.echeq.view.ConfirmarAdhesionECheqInView;
import ar.com.santanderrio.obp.servicios.echeq.view.ConfirmarAltaEndosarECheqView;
import ar.com.santanderrio.obp.servicios.echeq.view.ConfirmarOperacionECheqInView;
import ar.com.santanderrio.obp.servicios.echeq.view.ConfirmarOperacionECheqOutView;
import ar.com.santanderrio.obp.servicios.echeq.view.GrillaECheqInView;
import ar.com.santanderrio.obp.servicios.echeq.view.GrillaECheqOutView;
import ar.com.santanderrio.obp.servicios.echeq.view.IngresoECheqOutView;
import ar.com.santanderrio.obp.servicios.echeq.view.IngresoOperacionECheqView;

/**
 * The Class ECheqSEIImpl.
 *
 * @author IT Resources
 */
@Component("eCheqSEI")
public class ECheqSEIImpl implements ECheqSEI {

    @Autowired
    private ECheqManager eCheqManager;
    
    @Override
    public Respuesta<IngresoECheqOutView> ingresoModulo() {
        return eCheqManager.ingresoModulo();
    }

    @Override
    public Respuesta<GrillaECheqOutView> consultar(GrillaECheqInView grillaECheqInView, MessageContext mc) {
    	grillaECheqInView.setjSessionId(mc.getHttpServletRequest().getSession().getId());
    	return eCheqManager.obtenerECheqs(grillaECheqInView);
    }

    @Override
    public Respuesta<ConfirmarOperacionECheqOutView> confirmar(ConfirmarOperacionECheqInView confirmarOperacionECheqInView, MessageContext mc) {
        confirmarOperacionECheqInView.setjSessionId(mc.getHttpServletRequest().getSession().getId());
        return eCheqManager.confirmar(confirmarOperacionECheqInView);
    }

    @Override
    public Respuesta<ConfirmarOperacionECheqOutView> confirmarAdhesion(ConfirmarAdhesionECheqInView confirmarAdhesionInView) {
        return eCheqManager.confirmarAdhesion(confirmarAdhesionInView);
    }

    @Override
    public Respuesta<ConfirmarAltaEndosarECheqView> confirmarAlta(ConfirmarAltaEndosarECheqView confirmarAltaEndosarECheqInView, MessageContext mc) {
        confirmarAltaEndosarECheqInView.setjSessionId(mc.getHttpServletRequest().getSession().getId());
        confirmarAltaEndosarECheqInView.setOperacion(OperacionEcheqEnum.ALTA);
        confirmarAltaEndosarECheqInView.setTipoOperacion(OperacionesRSAEnum.ECHEQ_ALTA);
        return eCheqManager.confirmarAltaEndosar(confirmarAltaEndosarECheqInView);
    }

    @Override
    public Respuesta<ConfirmarAltaEndosarECheqView> confirmarEndoso(ConfirmarAltaEndosarECheqView confirmarAltaEndosarECheqInView, MessageContext mc) {
        confirmarAltaEndosarECheqInView.setjSessionId(mc.getHttpServletRequest().getSession().getId());
        confirmarAltaEndosarECheqInView.setOperacion(OperacionEcheqEnum.ENDOSAR);
        confirmarAltaEndosarECheqInView.setTipoOperacion(OperacionesRSAEnum.ECHEQ_ENDOSAR);
        return eCheqManager.confirmarAltaEndosar(confirmarAltaEndosarECheqInView);
    }
    
    @Override
    public Respuesta<ConfirmarAltaEndosarECheqView> confirmarAltaCED(
    		ConfirmarAltaEndosarECheqView confirmarAltaEndosarECheqInView, MessageContext mc) {
    	confirmarAltaEndosarECheqInView.setjSessionId(mc.getHttpServletRequest().getSession().getId());
        confirmarAltaEndosarECheqInView.setOperacion(OperacionEcheqEnum.EMITIR_CED);
        confirmarAltaEndosarECheqInView.setTipoOperacion(OperacionesRSAEnum.ECHEQ_EMITIR_CED);
        return eCheqManager.confirmarAltaEndosar(confirmarAltaEndosarECheqInView);
    }

    @Override
    public Respuesta<BeneficiarioView> validarBeneficiario(BeneficiarioView beneficiarioInView, MessageContext mc) {
    	beneficiarioInView.setjSessionId(mc.getHttpServletRequest().getSession().getId());
        return eCheqManager.validarBeneficiario(beneficiarioInView);
    }

    @Override
    public Respuesta<IngresoOperacionECheqView> ingresoOperacion(IngresoOperacionECheqView ingresoOperacionECheqInView, MessageContext mc) {
    	ingresoOperacionECheqInView.setjSessionId(mc.getHttpServletRequest().getSession().getId());
        return eCheqManager.ingresoOperacion(ingresoOperacionECheqInView);
    }

    @Override
    public Respuesta<ReporteView> descargarComprobanteAlta() {
        return eCheqManager.descargarComprobante(OperacionEcheqEnum.ALTA);
    }
    
    @Override
    public Respuesta<ReporteView> descargarComprobanteEndoso() {
        return eCheqManager.descargarComprobante(OperacionEcheqEnum.ENDOSAR);
    }

    @Override
    public Respuesta<ReporteView> descargarDetalle() {
        return eCheqManager.descargarComprobante(OperacionEcheqEnum.VER_DETALLE);
    }
    
    @Override
    public Respuesta<ReporteView> descargarComprobanteAltaCED() {
    	return eCheqManager.descargarComprobante(OperacionEcheqEnum.EMITIR_CED);
    }

}
