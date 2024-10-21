package ar.com.santanderrio.obp.servicios.debinws.sei.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.debinws.dto.RechazarDebinWSOutDTO;
import ar.com.santanderrio.obp.servicios.debinws.manager.DebinWSAdhesionManager;
import ar.com.santanderrio.obp.servicios.debinws.manager.DebinWSManager;
import ar.com.santanderrio.obp.servicios.debinws.manager.DebinWSSolicitudesManager;
import ar.com.santanderrio.obp.servicios.debinws.sei.DebinWSSEI;
import ar.com.santanderrio.obp.servicios.debinws.view.ConfiguracionGrillaDebinWSView;
import ar.com.santanderrio.obp.servicios.debinws.view.ConsultaDebinWSInView;
import ar.com.santanderrio.obp.servicios.debinws.view.ConsultaDebinWSOutView;
import ar.com.santanderrio.obp.servicios.debinws.view.ConsultaDetalleDebinWSInView;
import ar.com.santanderrio.obp.servicios.debinws.view.ConsultaDetalleDebinWSOutView;
import ar.com.santanderrio.obp.servicios.debinws.view.ConsultarAdhesionDebinesView;
import ar.com.santanderrio.obp.servicios.debinws.view.CuentasAdheridasDebinOutView;
import ar.com.santanderrio.obp.servicios.debinws.view.DebinWSEliminarOutView;
import ar.com.santanderrio.obp.servicios.debinws.view.GestionarAdhesionDebinesView;
import ar.com.santanderrio.obp.servicios.debinws.view.PagarDebinWSView;
import ar.com.santanderrio.obp.servicios.debinws.view.SolicitarDebinView;
import ar.com.santanderrio.obp.servicios.debinws.view.ValidarCbuAliasDebinInView;
import ar.com.santanderrio.obp.servicios.debinws.view.ValidarCbuAliasDebinOutView;

/**
 * DebinWSSEIImpl.
 */
@Component("debinWSSEI")
public class DebinWSSEIImpl implements DebinWSSEI {

    /** The Constant PAGO. */
    private static final String PAGO = "ConfirmacionDebito";

    /** The Constant RECHAZO. */
    private static final String RECHAZO = "RechazoDebin";

    /** The Constant ELIMINACION. */
    private static final String ELIMINACION = "EliminacionDebin";

    /** The debin manager. */
    @Autowired
    @Qualifier("debinWSManagerImpl")
    private DebinWSManager debinWSManager;

    /** The debin adhesion manager. */
    @Autowired
    @Qualifier("debinWSAdhesionManagerImpl")
    private DebinWSAdhesionManager debinWSAdhesionManager;
       
    /** The debin manager solicitudes */
    @Autowired
    @Qualifier("debinWSSolicitudesManagerImpl")
    private DebinWSSolicitudesManager debinWSSolicitudesManager;

    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.debinws.sei.DebinWSSEI#consultaDebin(ar.com.santanderrio.obp.servicios.debinws.view.ConsultaDebinWSInView)
     */
    @Override
    public Respuesta<ConsultaDebinWSOutView> consultaDebin(ConsultaDebinWSInView consultaDebinInView) {
        return debinWSManager.consultaDebin(consultaDebinInView);

    }

    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.debinws.sei.DebinWSSEI#consultaDetalleDebin(ar.com.santanderrio.obp.servicios.debinws.view.ConsultaDetalleDebinWSInView)
     */
    @Override
    public Respuesta<ConsultaDetalleDebinWSOutView> consultaDetalleDebin(ConsultaDetalleDebinWSInView consultaDetalleDebinWSOutView) {
        return debinWSManager.consultaDetalleDebin(consultaDetalleDebinWSOutView);
    }

    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.debinws.sei.DebinWSSEI#eliminarDebin()
     */
    @Override
    public Respuesta<DebinWSEliminarOutView> eliminarDebin() {
        return debinWSManager.eliminarDebin();
    }

    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.debinws.sei.DebinWSSEI#descargarComprobanteEliminar()
     */
    @Override
    public Respuesta<Reporte> descargarComprobanteEliminar() {
        return debinWSManager.descargarComprobante(ELIMINACION);
    }

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.debinws.sei.DebinWSSEI#pagarDebin(ar.com.santanderrio.obp.servicios.debinws.view.PagarDebinWSView)
	 */
	@Override
	public Respuesta<PagarDebinWSView> pagarDebin(PagarDebinWSView pagarDebinWSView) {
		return debinWSManager.pagarDebin(pagarDebinWSView);
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.debinws.sei.DebinWSSEI#descargarComprobantePago()
	 */
	@Override
    public Respuesta<Reporte> descargarComprobantePago() {
        return debinWSManager.descargarComprobante(PAGO);
    }

    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.debinws.sei.DebinWSSEI#rechazarDebin()
     */
    @Override
    public Respuesta<RechazarDebinWSOutDTO> rechazarDebin() {
        return debinWSManager.rechazarDebin();
    }

    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.debinws.sei.DebinWSSEI#descargarComprobanteRechazo()
     */
    @Override
    public Respuesta<Reporte> descargarComprobanteRechazo() {
        return debinWSManager.descargarComprobante(RECHAZO);
    }

    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.debinws.sei.DebinWSSEI#buscarCuentasParaAdhesionDebines()
     */
    @Override
    public Respuesta<ConsultarAdhesionDebinesView> buscarCuentasParaAdhesionDebines() {
        return debinWSAdhesionManager.buscarCuentasParaAdhesionDebines();
    }

    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.debinws.sei.DebinWSSEI#gestionarAdhesionDebines(ar.com.santanderrio.obp.servicios.debinws.view.GestionarAdhesionDebinesView)
     */
    @Override
    public Respuesta<GestionarAdhesionDebinesView> gestionarAdhesionDebines(GestionarAdhesionDebinesView gestionAdhesionView) {
        return debinWSAdhesionManager.gestionarAdhesionDebines(gestionAdhesionView);
    }

    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.debinws.sei.DebinWSSEI#descargarComprobanteAdhesion()
     */
    @Override
    public Respuesta<Reporte> descargarComprobanteAdhesion() {
        return debinWSAdhesionManager.descargarComprobanteAdhesion();
    }
	
	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.debinws.sei.DebinWSSEI#ingresoSolicitarDebin()
	 */
	@Override
    public Respuesta<CuentasAdheridasDebinOutView> ingresoSolicitarDebin() {
        return debinWSSolicitudesManager.ingresoSolicitarDebin();
    }

    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.debinws.sei.DebinWSSEI#validarCbuAliasDebin(ar.com.santanderrio.obp.servicios.debinws.view.ValidarCbuAliasDebinInView, org.apache.cxf.jaxrs.ext.MessageContext)
     */
    @Override
    public Respuesta<ValidarCbuAliasDebinOutView> validarCbuAliasDebin(ValidarCbuAliasDebinInView validarCbuAliasInView, org.apache.cxf.jaxrs.ext.MessageContext mc) {
        return debinWSSolicitudesManager.validarCbuAliasDebin(validarCbuAliasInView, mc.getHttpHeaders().getRequestHeaders().get("User-Agent").get(0));
    }

    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.debinws.sei.DebinWSSEI#solicitarDebin(ar.com.santanderrio.obp.servicios.debinws.view.SolicitarDebinView)
     */
    @Override
    public Respuesta<SolicitarDebinView> solicitarDebin(SolicitarDebinView solicitarDebinInView) {
        return debinWSSolicitudesManager.solicitarDebin(solicitarDebinInView);
    }

    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.debinws.sei.DebinWSSEI#descargarComprobanteSolcitar()
     */
    @Override
    public Respuesta<Reporte> descargarComprobanteSolcitar() {
        return debinWSSolicitudesManager.descargarComprobanteSolicitar();
    }

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.debinws.sei.DebinWSSEI#configuracionGrillaDebinWSView()
	 */
	@Override
	public Respuesta<ConfiguracionGrillaDebinWSView> configuracionGrillaDebinWSView() {
		return debinWSManager.configuracionGrillaDebinWSView();
	}

}