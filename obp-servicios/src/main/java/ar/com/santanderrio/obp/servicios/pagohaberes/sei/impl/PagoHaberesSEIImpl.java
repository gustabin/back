/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagohaberes.sei.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.base.web.view.FeedbackMensajeView;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.pagohaberes.entities.ComprobanteAdhesionEmpleadoEntity;
import ar.com.santanderrio.obp.servicios.pagohaberes.entities.ComprobantePagoHaberesCBUEntity;
import ar.com.santanderrio.obp.servicios.pagohaberes.entities.ComprobantePagoHaberesPagoSimpleMultipleEntity;
import ar.com.santanderrio.obp.servicios.pagohaberes.entities.ImporteCuentasView;
import ar.com.santanderrio.obp.servicios.pagohaberes.sei.PagoHaberesSEI;
import ar.com.santanderrio.obp.servicios.pagohaberes.web.manager.PagoHaberesManager;
import ar.com.santanderrio.obp.servicios.pagohaberes.web.view.AgregarEmpleadoView;
import ar.com.santanderrio.obp.servicios.pagohaberes.web.view.DatosDestinatarioView;
import ar.com.santanderrio.obp.servicios.pagohaberes.web.view.PagoHaberesEliminarView;
import ar.com.santanderrio.obp.servicios.pagohaberes.web.view.PagoHaberesView;
import ar.com.santanderrio.obp.servicios.pagohaberes.web.view.PagoInformadoView;
import ar.com.santanderrio.obp.servicios.pagohaberes.web.view.ValidacionesPagoPorCBUView;
import ar.com.santanderrio.obp.servicios.pagohaberes.web.view.ValidacionesRespuestaEntity;
import ar.com.santanderrio.obp.servicios.pagohaberes.web.view.ValidarPagoSimpleMultipleView;

/**
 * The Class PagoHaberesSEIImpl.
 */
@Component("pagoHaberesSEI")
public class PagoHaberesSEIImpl implements PagoHaberesSEI {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(PagoHaberesSEIImpl.class);

	/** The manager. */
	@Autowired
	private PagoHaberesManager manager;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.pagohaberes.sei.PagoHaberesSEI#
	 * obtenerPagoHaberes()
	 */
	@Override
	public Respuesta<PagoHaberesView> obtenerPagoHaberes() {
		LOGGER.info("Post OK: /obtenerPagoHaberes.");
		return manager.obtenerPagoHaberes();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.pagohaberes.sei.PagoHaberesSEI#
	 * eliminarEmpleado(ar.com.santanderrio.obp.servicios.pagohaberes.web.view.
	 * PagoHaberesEliminarView)
	 */
	@Override
	public Respuesta<FeedbackMensajeView> eliminarEmpleado(PagoHaberesEliminarView pagoHaberesEliminarView) {
		LOGGER.info("Post OK: /eliminarEmpleado.");
		return manager.eliminarEmpleado(pagoHaberesEliminarView);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.pagohaberes.sei.PagoHaberesSEI#
	 * eliminarPagoProgramado(ar.com.santanderrio.obp.servicios.pagohaberes.web.
	 * view.PagoHaberesEliminarView)
	 */
	@Override
	public Respuesta<FeedbackMensajeView> eliminarPagoProgramado(PagoHaberesEliminarView pagoHaberesEliminarView) {
		LOGGER.info("Post OK: /eliminarPagoProgramado.");
		return manager.eliminarPagoProgramado(pagoHaberesEliminarView);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.pagohaberes.sei.PagoHaberesSEI#
	 * validarEmpleado(ar.com.santanderrio.obp.servicios.pagohaberes.web.view.
	 * PagoHaberesValidarView)
	 */
	@Override
	public Respuesta<PagoInformadoView> validarEmpleado(PagoInformadoView pagoInformadoView) {
		LOGGER.info("Post OK: /validarEmpleado.");
		return manager.validarEmpleado(pagoInformadoView);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.pagohaberes.sei.PagoHaberesSEI#
	 * agregarEmpleado(ar.com.santanderrio.obp.servicios.pagohaberes.web.view.
	 * PagoHaberesAgregarView)
	 */
	@Override
	public Respuesta<ComprobanteAdhesionEmpleadoEntity> agregarEmpleado(PagoInformadoView pagoInformadoView) {
		LOGGER.info("Post OK: /agregarEmpleado.");
		return manager.agregarEmpleado(pagoInformadoView);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.pagohaberes.sei.PagoHaberesSEI#
	 * eliminarPagoProgramado(ar.com.santanderrio.obp.servicios.pagohaberes.web.
	 * view.verDetalleEmpleado)
	 */
	@Override
	public Respuesta<Void> verDetalleEmpleado() {
		LOGGER.info("Post OK: /verDetalleEmpleado.");
		return manager.verDetalleEmpleado();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.pagohaberes.sei.PagoHaberesSEI#
	 * eliminarPagoProgramado(ar.com.santanderrio.obp.servicios.pagohaberes.web.
	 * view.verDetallePagoAgendado)
	 */
	@Override
	public Respuesta<Void> verDetallePagoAgendado() {
		LOGGER.info("Post OK: /verDetallePagoAgendado.");
		return manager.verDetallePagoAgendado();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.pagohaberes.sei.PagoHaberesSEI#
	 * obtenerCuentas()
	 */
	@Override
	public Respuesta<AgregarEmpleadoView> obtenerCuentas() {
		LOGGER.info("Post OK: /obtenerCuentas.");
		return manager.obtenerCuentas();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.pagohaberes.sei.PagoHaberesSEI#
	 * validacionesPagoPorCBU()
	 */
	@Override
	public Respuesta<DatosDestinatarioView> validacionesPagoPorCBU(ValidacionesPagoPorCBUView validacionesPagoPorCBU) {
		LOGGER.info("Post OK: /validacionesPagoPorCBU.");
		return manager.validacionesPagoPorCBU(validacionesPagoPorCBU);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.pagohaberes.sei.PagoHaberesSEI#
	 * validarCBU()
	 */
	@Override
	public Respuesta<ComprobantePagoHaberesCBUEntity> pagoHaberesCBU(
			ComprobantePagoHaberesCBUEntity comprobantePagoHaberesCBU) {
		LOGGER.info("Post OK: /pagoHaberesCBU.");
		return manager.pagoHaberesCBU(comprobantePagoHaberesCBU);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.pagohaberes.sei.
	 * PagoHaberesSEI#generarComprobanteAdhesion(ar.com.santanderrio.obp
	 * .servicios.pagohaberes.entities.ComprobanteAdhesionEmpleado)
	 */
	@Override
	public Respuesta<ReporteView> generarComprobanteAdhesion(
			ComprobanteAdhesionEmpleadoEntity comprobanteAdhesionEmpleado) {
		return manager.generarComprobanteAdhesion(comprobanteAdhesionEmpleado);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.pagohaberes.sei.PagoHaberesSEI#
	 * generarComprobantePagoPorCBU(ar.com.santanderrio.obp.servicios.
	 * pagohaberes.entities.ComprobantePagoHaberesCBU)
	 */
	@Override
	public Respuesta<ReporteView> generarComprobantePagoPorCBU(
			ComprobantePagoHaberesCBUEntity comprobantePagoHaberesCBU) {
		return manager.generarComprobantePagoPorCBU(comprobantePagoHaberesCBU);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.pagohaberes.sei.PagoHaberesSEI#
	 * validacionesPagoSimpleMultiple()
	 */
	@Override
	public Respuesta<ValidacionesRespuestaEntity> validacionesPagoSimpleMultiple(
			ValidarPagoSimpleMultipleView validarPagoSimpleMultipleView) {
		LOGGER.info("Post OK: /validacionesPagoSimpleMultiple.");
		return manager.validacionesPagoSimpleMultiple(validarPagoSimpleMultipleView);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.pagohaberes.sei.PagoHaberesSEI#
	 * realizarPagoHaberes()
	 */
	@Override
	public Respuesta<ComprobantePagoHaberesPagoSimpleMultipleEntity> realizarPagoHaberes(
			ComprobantePagoHaberesPagoSimpleMultipleEntity comprobantePagoHaberesPagoSimpleMultiple) {
		LOGGER.info("Post OK: /realizarPagoHaberes.");
		return manager.realizarPagoHaberes(comprobantePagoHaberesPagoSimpleMultiple);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.pagohaberes.sei.PagoHaberesSEI#
	 * obtenerImportesCuentasDebitar()
	 */
	@Override
	public Respuesta<List<ImporteCuentasView>> obtenerImportesCuentasDebitar(
			List<ImporteCuentasView> importeCuentasView) {
		LOGGER.info("Post OK: /obtenerImportesCuentasDebitar.");
		return manager.obtenerImportesCuentasDebitar(importeCuentasView);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.pagohaberes.sei.PagoHaberesSEI#
	 * generarComprobantePagoSimpleMultiple()
	 */
	@Override
	public Respuesta<ReporteView> generarComprobantePagoSimpleMultiple(ComprobantePagoHaberesCBUEntity comprobante) {
		LOGGER.info("Post OK: /generarComprobantePagoSimpleMultiple.");
		return manager.generarComprobantePagoSimpleMultiple(comprobante);
	}

    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.pagohaberes.sei.PagoHaberesSEI#obtenerLimitesHorarios()
     */
    @Override
    public Respuesta<String> obtenerLimitesHorarios() {
        return manager.obtenerLimitesHorarios();
    }
}
