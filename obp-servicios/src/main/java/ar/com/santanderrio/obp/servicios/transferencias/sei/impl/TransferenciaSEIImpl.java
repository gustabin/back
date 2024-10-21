/*
 * 
 */
package ar.com.santanderrio.obp.servicios.transferencias.sei.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.view.DestinatarioView;
import ar.com.santanderrio.obp.servicios.comun.estadistica.sei.EstadisticaSEI;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.cuentas.web.manager.TipoDeCuentaView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.transferencias.sei.TransferenciaSEI;
import ar.com.santanderrio.obp.servicios.transferencias.web.manager.TransferenciaManager;
import ar.com.santanderrio.obp.servicios.transferencias.web.view.TransferenciaView;

/**
 * The Class TransferenciaSEIImpl.
 */
@Component("transferenciasSEI")
public class TransferenciaSEIImpl extends EstadisticaSEI<TransferenciaView> implements TransferenciaSEI {

	/** The transferencia manager. */
	@Autowired
	private TransferenciaManager transferenciaManager;

	/** The estadistica manager. */
	@Autowired
	private EstadisticaManager estadisticaManager;

	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/*
	 * (non-Javadoc)
	 * 
	 * @see 
	 * ar.com.santanderrio.obp.servicios.transferencias.sei.TransferenciaSEI#
	 * getNuevaTransferencia(ar.com.santanderrio.obp.servicios.transferencias.web.view.TransferenciaView, 
	 * org.apache.cxf.jaxrs.ext.MessageContext)
	 */
	@Override
	public Respuesta<TransferenciaView> getNuevaTransferencia(TransferenciaView transferencia,
            org.apache.cxf.jaxrs.ext.MessageContext mc) {
		return transferenciaManager.solicitarNuevaTransferencia(transferencia,
                mc.getHttpHeaders().getRequestHeaders().get("User-Agent").get(0));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.transferencias.sei.TransferenciaSEI#
	 * ejecutarNuevaTransferencia(ar.com.santanderrio.obp.servicios.
	 * transferencias.web.view.TransferenciaView)
	 */
	@Override
	public Respuesta<TransferenciaView> ejecutarNuevaTransferencia(TransferenciaView transferenciaView, org.apache.cxf.jaxrs.ext.MessageContext mc) {
		return transferenciaManager.ejecutarNuevaTransferencia(transferenciaView);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.transferencias.sei.TransferenciaSEI#
	 * obtenerTiposDeCuenta()
	 */
	@Override
	public Respuesta<List<TipoDeCuentaView>> obtenerTiposDeCuenta() {
		return transferenciaManager.obtenerTiposDeCuenta();
	}

    /*
     * (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.transferencias.sei.TransferenciaSEI#
     * consultarTitularidad(ar.com.santanderrio.obp.servicios.transferencias.web.view.TransferenciaView, 
     * org.apache.cxf.jaxrs.ext.MessageContext)
     */
	@Override
	public Respuesta<TransferenciaView> consultarTitularidad(TransferenciaView transferencia,
			org.apache.cxf.jaxrs.ext.MessageContext mc) {
		return transferenciaManager.consultarTitularidad(transferencia,
				mc.getHttpHeaders().getRequestHeaders().get("User-Agent").get(0));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.transferencias.sei.TransferenciaSEI#
	 * obtenerInformacionDestinatario(ar.com.santanderrio.obp.servicios.
	 * transferencias.web.view.TransferenciaView)
	 */
	@Override
	public Respuesta<TransferenciaView> obtenerInformacionDestinatario(DestinatarioView destinatarioView) {
		return transferenciaManager.obtenerInformacionDestinatario(destinatarioView);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.transferencias.sei.TransferenciaSEI#
	 * validarContratoTransferencia()
	 */
	@Override
	public Respuesta<Boolean> validarContratoTransferencia() {
		return transferenciaManager.validarContratoTransferencia();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.transferencias.sei.TransferenciaSEI#
	 * actualizarSaldo()
	 */
	@Override
	public Respuesta<CuentasView> actualizarSaldo() {
		return transferenciaManager.actualizarSaldo();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.transferencias.sei.TransferenciaSEI#
	 * descargarComprobantePDF()
	 */
	@Override
	public Respuesta<ReporteView> descargarComprobantePDF() {
		return transferenciaManager.descargarComprobantePDF();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.transferencias.sei.TransferenciaSEI#
	 * grabarEstadisticasAccesoAyuda()
	 */
	@Override
	public Respuesta<TransferenciaView> grabarEstadisticasAccesoAyuda() {
		estadisticaManager.add(CodigoMensajeConstantes.CODIGO_NUEVA_TRANSFERENCIA_AYUDA_MAS_INFORMACION,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		return respuestaFactory.crearRespuestaOk(TransferenciaView.class);
	}

	
	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.transferencias.sei.TransferenciaSEI#grabarEstadisticasAccesoTransferencias()
	 */
	@Override
	public void grabarEstadisticasAccesoTransferencias() {
		estadisticaManager.add(CodigoMensajeConstantes.SALTO_TRANSFERENCIA_DESDE_CUENTAS,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.transferencias.sei.TransferenciaSEI#consultarHorarios()
	 */
	@Override
	public Respuesta<TransferenciaView> consultarHorarios() {
		return transferenciaManager.consultarHorarios();
	}
}
