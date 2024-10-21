/*
 * 
 */

package ar.com.santanderrio.obp.servicios.inversiones.plazofijo.sei;

import java.util.List;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.inversiones.comun.TipoBancaEnum;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.DetallePFInteresanteInView;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.FrecuenciaCobroPFInteresanteView;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.RendimientoBPrivView;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.RendimientoView;
import ar.com.santanderrio.obp.servicios.inversiones.ordenes.entity.ConfirmarOrdenInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.ordenes.web.view.ConfirmarOrdenPFView;
import ar.com.santanderrio.obp.servicios.inversiones.ordenes.web.view.OrdenesView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.RecomendacionResponseEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.manager.PlazoFijoManager;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.AccionesAlVencimientoInView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.AccionesAlVencimientoOutView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.AltaComprobantePlazoFijoView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.CalcularInteresesInView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.ComprobanteCancelacionPrecancelable;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.ComprobanteCancelacionPrecancelableUVA;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.ComprobanteConstitucionPlazoFijo;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.ComprobanteModificacionAccionVencimiento;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.ComprobantePlazoFijoInView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.ComprobantePlazoFijoOutView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.ComprobantePrecancelarUVA;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.ConfigPlazoFijoInView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.ConfigPlazoFijoOutView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.ConfirmacionPlazoFijoOutView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.ConsultaTasasPlazosFijoView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.DetalleCobroInteresesViewResponse;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.DetalleInteresesViewRequest;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.FinalizarPlazoFijoInView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.FinalizarPlazoFijoView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.FinalizarPrecancelableOutView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.FinalizarPrecancelarUVAView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.InteresesView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.ModificarAccionInView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.ModificarAccionOutView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.ObtenerOrdenesViewRequest;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.ObtenerTenenciasPlazoFijoInView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.RecomendacionInView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.SimulacionPrecancelableOutView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.SimularPlazoFijoInView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.SimularPlazoFijoOutView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.SimularPrecancelarInView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.SimularPrecancelarUVAView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.SolicitarPrecancelarInView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.SolicitarPrecancelarOutView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.TenenciaPlazoFijoBprivView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.TenenciaPlazoFijoView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.VerComprobanteModificacionVencimientoView;

/**
 * The Class PlazoFijoSEIImpl.
 *
 * @author
 */
@Component("plazoFijoSEI")
public class PlazoFijoSEIImpl implements PlazoFijoSEI {

    /** The fondo manager. */
    @Autowired
    private PlazoFijoManager plazoFijoManager;

    /**
     * {@inheritDoc}
     */
    @Override
    public Respuesta<SimularPlazoFijoOutView> simularPlazoFijo(SimularPlazoFijoInView inView) {
        return plazoFijoManager.simularPlazoFijo(inView);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Respuesta<ConfigPlazoFijoOutView> configuracionPlazoFijo(ConfigPlazoFijoInView configPlazoFijoInView) {
        return plazoFijoManager.configuracionPlazoFijo(configPlazoFijoInView);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.inversiones.plazofijo.sei.PlazoFijoSEI#
     * accionesAlVencimiento(ar.com.santanderrio.obp.servicios.inversiones.
     * plazofijo.view.AccionesAlVencimientoInView)
     */
    @Override
    public Respuesta<AccionesAlVencimientoOutView> accionesAlVencimiento(AccionesAlVencimientoInView inView) {
        return plazoFijoManager.accionesAlVencimiento(inView);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.inversiones.plazofijo.sei.PlazoFijoSEI#
     * grabarEstadisticaPlazoFijo()
     */
    @Override
    public Respuesta<Void> grabarEstadisticaPlazoFijo() {
        return plazoFijoManager.grabarEstadisticaPlazoFijo();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.inversiones.plazofijo.sei.PlazoFijoSEI#
     * grabarEstadisticaPlazoFijoBPriv()
     */
    @Override
    public Respuesta<Void> grabarEstadisticaPlazoFijoBPriv() {
        return plazoFijoManager.grabarEstadisticaPlazoFijoBPriv();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.inversiones.plazofijo.sei.PlazoFijoSEI#
     * confirmacionPlazoFijo(ar.com.santanderrio.obp.servicios.inversiones.
     * plazofijo.view.AccionesAlVencimientoInView)
     */
    @Override
    public Respuesta<ConfirmacionPlazoFijoOutView> confirmacionPlazoFijo(AccionesAlVencimientoInView codigoPlazoFijo) {
        return plazoFijoManager.confirmacionPlazoFijo(codigoPlazoFijo.getCodigoPlazo(), codigoPlazoFijo.getBanca());
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.inversiones.plazofijo.sei.PlazoFijoSEI#
     * confirmacionPlazoFijo(ar.com.santanderrio.obp.servicios.inversiones.
     * plazofijo.view.AccionesAlVencimientoInView)
     */
    @Override
	public Respuesta<ComprobantePlazoFijoOutView> verComprobante(
			ComprobantePlazoFijoInView comprobantePlazoFijoInView) {
        return plazoFijoManager.verComprobante(comprobantePlazoFijoInView);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.inversiones.plazofijo.sei.PlazoFijoSEI#
     * finalizarPlazoFijo(ar.com.santanderrio.obp.servicios.inversiones.
     * plazofijo.view.FinalizarPlazoFijoInView)
     */
    @Override
    public Respuesta<FinalizarPlazoFijoView> finalizarPlazoFijo(FinalizarPlazoFijoInView finalizarPlazoFijoInView) {
        return plazoFijoManager.finalizarPlazoFijo(finalizarPlazoFijoInView);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.inversiones.plazofijo.sei.PlazoFijoSEI#
     * calcularIntereses(ar.com.santanderrio.obp.servicios.inversiones.plazofijo
     * .view.CalcularInteresesInView)
     */
    @Override
    public Respuesta<InteresesView> calcularIntereses(CalcularInteresesInView inView) {
        return plazoFijoManager.calcularIntereses(inView);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.inversiones.plazofijo.sei.PlazoFijoSEI#
     * consultarTasas()
     */
    @Override
    public Respuesta<ConsultaTasasPlazosFijoView> consultarTasas(ConsultaTasasPlazosFijoView consultaTasasPlazosFijoView) {
        return plazoFijoManager.consultarTasas(consultaTasasPlazosFijoView);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.inversiones.plazofijo.sei.PlazoFijoSEI#
     * obtenerTenenciasPlazoFijo(ar.com.santanderrio.obp.servicios.inversiones.
     * plazofijo.view.ObtenerTenenciasPlazoFijoInView)
     */
    @Override
    public Respuesta<TenenciaPlazoFijoView> obtenerTenenciasPlazoFijo(ObtenerTenenciasPlazoFijoInView inview) {
        return plazoFijoManager.obtenerTenenciasPlazoFijo(inview);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.inversiones.plazofijo.sei.PlazoFijoSEI#
     * obtenerTenenciasPlazoFijoBPriv(ar.com.santanderrio.obp.servicios.
     * inversiones.plazofijo.view.ObtenerTenenciasPlazoFijoInView)
     */
    @Override
	public Respuesta<TenenciaPlazoFijoBprivView> obtenerTenenciasPlazoFijoBPriv(
			ObtenerTenenciasPlazoFijoInView inview) {
        return plazoFijoManager.obtenerTenenciasPlazoFijoBPriv(inview);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.inversiones.plazofijo.sei.PlazoFijoSEI#
     * grabarEstadisticaVerDetalle()
     */
    @Override
    public Respuesta<ConfirmacionPlazoFijoOutView> verDetalle(AccionesAlVencimientoInView inView) {
        return plazoFijoManager.verDetalle(inView);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.inversiones.plazofijo.sei.PlazoFijoSEI#
     * grabarEstadisticaVerDetalleBPriv()
     */
    @Override
    public Respuesta<ConfirmacionPlazoFijoOutView> verDetalleBPriv(AccionesAlVencimientoInView inView) {
        return plazoFijoManager.verDetalleBPriv(inView);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.inversiones.plazofijo.sei.PlazoFijoSEI#
     * solicitarPrecancelar(ar.com.santanderrio.obp.servicios.inversiones.
     * plazofijo.view.SolicitarPrecancelarInView)
     */
    @Override
    public Respuesta<SolicitarPrecancelarOutView> solicitarPrecancelar(SolicitarPrecancelarInView inview) {
        return plazoFijoManager.solicitarPrecancelar(inview);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.inversiones.plazofijo.sei.PlazoFijoSEI#
     * simularPrecancelar(ar.com.santanderrio.obp.servicios.inversiones.
     * plazofijo.view.SimularPrecancelarInView)
     */
    @Override
    public Respuesta<SimulacionPrecancelableOutView> simularPrecancelar(SimularPrecancelarInView inview) {
        return plazoFijoManager.simularPrecancelacion(inview);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.inversiones.plazofijo.sei.PlazoFijoSEI#
     * finalizarPrecancelar(ar.com.santanderrio.obp.servicios.inversiones.
     * plazofijo.view.SimularPrecancelarInView)
     */
    @Override
    public Respuesta<FinalizarPrecancelableOutView> finalizarPrecancelar(SimularPrecancelarInView inview) {
        return plazoFijoManager.finalizarPrecancelacion(inview);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.inversiones.plazofijo.sei.PlazoFijoSEI#
     * verComprobantePrecancelacion(ar.com.santanderrio.obp.servicios.
     * inversiones.plazofijo.view.ComprobantePlazoFijoInView)
     */
    @Override
	public Respuesta<ComprobantePlazoFijoOutView> verComprobantePrecancelacion(
			ComprobantePlazoFijoInView comprobantePlazoFijoInView) {
        return plazoFijoManager.verComprobantePrecancelacion(comprobantePlazoFijoInView);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.inversiones.plazofijo.sei.PlazoFijoSEI#
     * grabarEstadisticaModificarAccion()
     */
    @Override
    public Respuesta<Void> grabarEstadisticaModificarAccion() {
        return plazoFijoManager.grabarEstadisticaModificarAccion();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.inversiones.plazofijo.sei.PlazoFijoSEI#
     * modificarAccion(ar.com.santanderrio.obp.servicios.inversiones.plazofijo.
     * view.ModificarAccionInView)
     */
    @Override
    public Respuesta<ModificarAccionOutView> modificarAccion(ModificarAccionInView inView) {
        return plazoFijoManager.modificarAccion(inView);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.inversiones.plazofijo.sei.PlazoFijoSEI#
     * verComprobanteModificarAccion()
     */
    @Override
    public Respuesta<ComprobantePlazoFijoOutView> verComprobanteModificarAccion(VerComprobanteModificacionVencimientoView inView) {
        return plazoFijoManager.verComprobanteModificarAccion(inView);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.inversiones.plazofijo.sei.PlazoFijoSEI#
     * obtenerDetalleIntereses(ar.com.santanderrio.obp.servicios.inversiones.
     * plazofijo.view.DetalleInteresesViewRequest)
     */
    @Override
	public Respuesta<DetalleCobroInteresesViewResponse> obtenerDetalleIntereses(
			DetalleInteresesViewRequest viewRequest) {
        return plazoFijoManager.obtenerDetalleIntereses(viewRequest);
    }

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.inversiones.plazofijo.sei.PlazoFijoSEI#
	 * grabarEstadisticaDetallePFVencidos()
	 */
	@Override
	public Respuesta<Void> grabarEstadisticaDetallePFVencidos() {
		return plazoFijoManager.grabarEstadisticaDetallePFVencidos();
		
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.plazofijo.sei.PlazoFijoSEI#descargarComprobanteConstitucionPDF(ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.ComprobanteConstitucionPlazoFijo)
	 */
	@Override
	public Respuesta<ReporteView> descargarComprobanteConstitucionPDF(ComprobanteConstitucionPlazoFijo viewRequest) {
		return plazoFijoManager.descargarComprobanteSuscripcionPDF(viewRequest);
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.plazofijo.sei.PlazoFijoSEI#descargarComprobanteModificacionAccionVencimientoPDF(ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.ComprobanteModificacionAccionVencimiento)
	 */
	@Override
	public Respuesta<ReporteView> descargarComprobanteModificacionAccionVencimientoPDF(
			ComprobanteModificacionAccionVencimiento viewRequest) {
		return plazoFijoManager.descargarComprobanteModificacionAccionVencimientoPDF(viewRequest);
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.plazofijo.sei.PlazoFijoSEI#descargarComprobanteCancelacionPrecancelable(ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.ComprobanteCancelacionPrecancelable)
	 */
	@Override
	public Respuesta<ReporteView> descargarComprobanteCancelacionPrecancelable(
			ComprobanteCancelacionPrecancelable viewRequest) {
		return plazoFijoManager.descargarComprobanteCancelacionPrecancelable(viewRequest);
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.plazofijo.sei.PlazoFijoSEI#obtenerOrdenesPorCuenta(ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.ObtenerOrdenesViewRequest)
	 */
	@Override
	public Respuesta<OrdenesView> obtenerOrdenesPorCuenta(ObtenerOrdenesViewRequest cuenta) {
		return plazoFijoManager.obtenerOrdenesPorCuenta(cuenta);
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.plazofijo.sei.PlazoFijoSEI#grabarEstadisticaDetalleOrdenes()
	 */
	@Override
	public Respuesta<Void> grabarEstadisticaDetalleOrdenes() {
		return plazoFijoManager.grabarEstadisticaDetalleOrdenes();
	}
	
	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.plazofijo.sei.PlazoFijoSEI#grabarEstadisticaConfirmarOrdenes()
	 */
	@Override
	public Respuesta<Void> grabarEstadisticaConfirmarOrdenes() {
		return plazoFijoManager.grabarEstadisticaConfirmarOrdenes();
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.plazofijo.sei.PlazoFijoSEI#confirmarOrden(ar.com.santanderrio.obp.servicios.inversiones.ordenes.entity.ConfirmarOrdenInEntity)
	 */
	@Override
	public Respuesta<ConfirmarOrdenPFView> confirmarOrden(ConfirmarOrdenInEntity entity) {
		return plazoFijoManager.confirmarOrden(entity);
	}


    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.inversiones.plazofijo.sei.PlazoFijoSEI#
     * altaComprobante(ar.com.santanderrio.obp.servicios.inversiones.
     * plazofijo.view.AltaComprobantePlazoFijoView)
     */
    @Override
    public Respuesta<AltaComprobantePlazoFijoView> altaComprobante(AltaComprobantePlazoFijoView viewRequest) {
        return plazoFijoManager.altaComprobante(viewRequest);
    }

    
	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.plazofijo.sei.PlazoFijoSEI#obtenerRendimiento()
	 */
	@Override
	public Respuesta<RendimientoView> obtenerRendimiento() {
		return plazoFijoManager.obtenerRendimiento();
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.plazofijo.sei.PlazoFijoSEI#obtenerRendimientoBPriv()
	 */
	@Override
	public Respuesta<RendimientoBPrivView> obtenerRendimientoBPriv() {		
		return plazoFijoManager.obtenerRendimientoBPriv();
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.plazofijo.sei.PlazoFijoSEI#obtenerDetallePFInteresanteBP(ar.com.santanderrio.obp.servicios.inversiones.comun.view.DetallePFInteresanteInView)
	 */
	@Override
	public Respuesta<List<FrecuenciaCobroPFInteresanteView>> obtenerDetallePFInteresanteBP(DetallePFInteresanteInView detalleIn) {
		return plazoFijoManager.obtenerDetallePFInteresanteBP(detalleIn);
	}

	@Override
	public Response exportarMovimientosRTL() {
		return plazoFijoManager.exportarMovimientos(TipoBancaEnum.BANCA_PERSONAL);
	}

	@Override
	public Response exportarMovimientosBP() {
		return plazoFijoManager.exportarMovimientos(TipoBancaEnum.BANCA_PRIVADA);
	}

	@Override
	public Respuesta<SimularPrecancelarUVAView> simularPrecancelarUVA(SimularPrecancelarInView request) {
		return plazoFijoManager.simularPrecancelarUVA(request);
	}

	@Override
	public Respuesta<FinalizarPrecancelarUVAView> finalizarPrecancelarUVA(SimularPrecancelarInView request) {
		return plazoFijoManager.finalizarPrecancelarUVA(request);
	}

	@Override
	public Respuesta<Void> verComprobantePrecancelarUVA(ComprobantePrecancelarUVA request) {
		return plazoFijoManager.verComprobantePrecancelarUVA(request.getBanca());
	}

	@Override
	public Respuesta<ReporteView> descargarComprobantePrecancelarUVA(ComprobanteCancelacionPrecancelableUVA viewRequest) {
		return plazoFijoManager.descargarComprobanteCancelacionPrecancelableUVA(viewRequest);
	}
	
	@Override
	public Respuesta<RecomendacionResponseEntity> obtenerRecomendacion(RecomendacionInView viewRequest) {
		return plazoFijoManager.obtenerRecomendacionManager(viewRequest);
	}
	
}
