/*
 * 
 */
package ar.com.santanderrio.obp.servicios.descuento.cheques.sei.impl;

import javax.activity.InvalidActivityException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.descuento.cheques.sei.DescuentoChequesSEI;
import ar.com.santanderrio.obp.servicios.descuento.cheques.web.manager.AltaChequesManager;
import ar.com.santanderrio.obp.servicios.descuento.cheques.web.manager.CoordinadorDescuentoChequesManager;
import ar.com.santanderrio.obp.servicios.descuento.cheques.web.manager.DescuentoChequesManager;
import ar.com.santanderrio.obp.servicios.descuento.cheques.web.view.AltaChequesViewIn;
import ar.com.santanderrio.obp.servicios.descuento.cheques.web.view.AltaChequesViewOut;
import ar.com.santanderrio.obp.servicios.descuento.cheques.web.view.DatosCesionView;
import ar.com.santanderrio.obp.servicios.descuento.cheques.web.view.DetalleHistorialOperacionesView;
import ar.com.santanderrio.obp.servicios.descuento.cheques.web.view.DetalleOperacionesPrecargadasView;
import ar.com.santanderrio.obp.servicios.descuento.cheques.web.view.HistorialOperacionesView;
import ar.com.santanderrio.obp.servicios.descuento.cheques.web.view.OperacionesPrecargadasView;
import ar.com.santanderrio.obp.servicios.descuento.cheques.web.view.TasasIndicativasView;

/**
 * The Class DescuentoChequesSEIImpl.
 *
 * @author dante.omar.olmedo
 */
@Component
public class DescuentoChequesSEIImpl implements DescuentoChequesSEI{

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(DescuentoChequesSEIImpl.class);
    
    @Autowired
    private DescuentoChequesManager debitoAutomaticotarjetaManager;
    
    @Autowired
    private CoordinadorDescuentoChequesManager coordinadorManager;
	
	@Autowired
	private AltaChequesManager altaManager;

    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.descuento.cheques.sei.DescuentoChequesSEI#obtenerCabeceraInicio()
     */
    @Override
    public Respuesta<DatosCesionView> obtenerCabeceraInicio() {
        LOGGER.info("Post OK: /obtenerCabecera.");
        return debitoAutomaticotarjetaManager.obtenerMontoCesion();
    }

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.descuento.cheques.sei.DescuentoChequesSEI#consultarTasasIndicativas()
	 */
	@Override
	public Respuesta<TasasIndicativasView> consultarTasasIndicativas() {
		LOGGER.info("Post OK: /consultaTasas.");
        return debitoAutomaticotarjetaManager.obtenerTasasIndicativas();
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.descuento.cheques.sei.DescuentoChequesSEI#consultarOperacionesPrecargadas(ar.com.santanderrio.obp.servicios.descuento.cheques.web.view.OperacionesPrecargadasView)
	 */
	@Override
	public Respuesta<OperacionesPrecargadasView> consultarOperacionesPrecargadas(
			OperacionesPrecargadasView operacionIn) {
		LOGGER.info("Post OK: /consultaOperacionesPrecargadas.");
		return coordinadorManager.operacionesCabecera(operacionIn);
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.descuento.cheques.sei.DescuentoChequesSEI#eliminarOperacion(ar.com.santanderrio.obp.servicios.descuento.cheques.web.view.DetalleOperacionesPrecargadasView)
	 */
	@Override
	public Respuesta<Void> eliminarOperacion(DetalleOperacionesPrecargadasView detalleIn) {
		LOGGER.info("Post OK: /eliminarOperacion.");
		return debitoAutomaticotarjetaManager.eliminarOperacion(detalleIn.getNumeroTramite());
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.descuento.cheques.sei.DescuentoChequesSEI#obtenerDetalleOperacionesPrecargadas(ar.com.santanderrio.obp.servicios.descuento.cheques.web.view.DetalleOperacionesPrecargadasView)
	 */
	@Override
	public Respuesta<DetalleOperacionesPrecargadasView> obtenerDetalleOperacionesPrecargadas(
			DetalleOperacionesPrecargadasView operacionIn) throws InvalidActivityException {
		LOGGER.info("Post OK: /consultaDetalleOperacionesPrecargadas.");
		return debitoAutomaticotarjetaManager.obtenerDetalleOperacionesPrecargadas(operacionIn);
	}
	
	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.descuento.cheques.sei.DescuentoChequesSEI#consultarHistorialOperaciones(ar.com.santanderrio.obp.servicios.descuento.cheques.web.view.HistorialOperacionesView)
	 */
	@Override
	public Respuesta<HistorialOperacionesView> consultarHistorialOperaciones(
			HistorialOperacionesView operacionIn) {
		LOGGER.info("Post OK: /consultaHistorialOperaciones.");
		return debitoAutomaticotarjetaManager.obtenerHistorialOperaciones(operacionIn);
	}
	
	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.descuento.cheques.sei.DescuentoChequesSEI#obtenerDetalleHistorialOperaciones(ar.com.santanderrio.obp.servicios.descuento.cheques.web.view.DetalleOperacionesPrecargadasView)
	 */
	@Override
	public Respuesta<DetalleHistorialOperacionesView> obtenerDetalleHistorialOperaciones(
			DetalleOperacionesPrecargadasView operacionIn) throws InvalidActivityException {
		LOGGER.info("Post OK: /consultaDetalleOperacionesPrecargadas.");
		return debitoAutomaticotarjetaManager.obtenerDetalleHistorialOperaciones(operacionIn);
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.descuento.cheques.sei.DescuentoChequesSEI#descargarOperacionPDF()
	 */
	@Override
	public Respuesta<ReporteView> descargarOperacionPDF() {
		LOGGER.info("Post OK: /descargarOperacionPDF.");
		return debitoAutomaticotarjetaManager.descargarOperacionPDF();
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.descuento.cheques.sei.DescuentoChequesSEI#grabarInicioSolicitud(ar.com.santanderrio.obp.servicios.descuento.cheques.web.view.DatosCesionView)
	 */
	@Override
	public void grabarInicioSolicitud(DatosCesionView view) {
		LOGGER.info("Post OK: /grabarInicioSolicitud.");
		altaManager.grabarInicioSolicitud(view);
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.descuento.cheques.sei.DescuentoChequesSEI#simularAltaCheques(ar.com.santanderrio.obp.servicios.descuento.cheques.web.view.AltaChequesViewIn)
	 */
	@Override
	public Respuesta<AltaChequesViewOut> simularAltaCheques(AltaChequesViewIn chequesView) {
		LOGGER.info("Post OK: /simularAltaCheques.");
		return altaManager.simularAltaCheques(chequesView);
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.descuento.cheques.sei.DescuentoChequesSEI#efectuarAltaCheques()
	 */
	@Override
	public Respuesta<AltaChequesViewOut> efectuarAltaCheques() {
		LOGGER.info("Post OK: /efectuarAltaCheques.");
		return altaManager.efectuarAltaCheques();
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.descuento.cheques.sei.DescuentoChequesSEI#visualizarAltaCheques(ar.com.santanderrio.obp.servicios.descuento.cheques.web.view.AltaChequesViewOut)
	 */
	@Override
	public Respuesta<AltaChequesViewOut> visualizarAltaCheques(AltaChequesViewOut chequesView) {
		LOGGER.info("Post OK: /visualizacionAltaCheques.");
		return altaManager.visualizarAltaCheques(chequesView);
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.descuento.cheques.sei.DescuentoChequesSEI#descargarAltaChequesPDF(ar.com.santanderrio.obp.servicios.descuento.cheques.web.view.AltaChequesViewOut)
	 */
	@Override
	public Respuesta<ReporteView> descargarAltaChequesPDF(AltaChequesViewOut chequesView) {
		LOGGER.info("Post OK: /descargarAltaChequesPDF.");
		return altaManager.descargarPDF(chequesView);
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.descuento.cheques.sei.DescuentoChequesSEI#simulacionDeTasas(ar.com.santanderrio.obp.servicios.descuento.cheques.web.view.AltaChequesViewOut)
	 */
	@Override
	public Respuesta<AltaChequesViewOut> simulacionDeTasas(AltaChequesViewOut chequesView) throws InvalidActivityException {
		LOGGER.info("Post OK: /simularTasas.");
		return debitoAutomaticotarjetaManager.obtenerSimulacionDeTasas(chequesView);
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.descuento.cheques.sei.DescuentoChequesSEI#efectuarSimulacionTasas()
	 */
	@Override
	public Respuesta<AltaChequesViewOut> efectuarSimulacionTasas() {
		LOGGER.info("Post OK: /efectuarTasas.");
		return debitoAutomaticotarjetaManager.obtenerEfectivizacionDeTasas();
	}


}
