/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.sei;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.inversiones.comun.TipoBancaEnum;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dto.AdhesionPDCOutDTO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dto.AdhesionPDCRequestDTO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dto.FinalizarAdhesionPDC;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.manager.PoderCompraManager;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.ComprobanteActivarPoderCompra;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.ComprobanteOrdenCompraView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.EstadisticaComprobantePoderCompra;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.FinalizarAdhesionPDCResponse;

/**
 * The Class PoderCompraSEIImpl.
 */
@Component("poderCompraSei")
public class PoderCompraSEIImpl implements PoderCompraSEI{

	/** The poder compra manager. */
	@Autowired
	private PoderCompraManager poderCompraManager;
	
	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.sei.PoderCompraSEI#simularAdhesionPDC(ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dto.AdhesionPDCRequestDTO)
	 */
	@Override
	public Respuesta<AdhesionPDCOutDTO> simularAdhesionPDC(AdhesionPDCRequestDTO request) {
		return poderCompraManager.simularAdhesionPDC(request);
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.sei.PoderCompraSEI#simularAdhesionPDCBPriv(ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dto.AdhesionPDCRequestDTO)
	 */
	@Override
	public Respuesta<AdhesionPDCOutDTO> simularAdhesionPDCBPriv(AdhesionPDCRequestDTO request) {
		return poderCompraManager.simularAdhesionPDCBPriv(request);
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.sei.PoderCompraSEI#finalizarAdhesionPDC(ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dto.FinalizarAdhesionPDC)
	 */
	@Override
	public Respuesta<FinalizarAdhesionPDCResponse> finalizarAdhesionPDC(FinalizarAdhesionPDC request) {
		return poderCompraManager.finalizarAdhesionPDC(request);
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.sei.PoderCompraSEI#finalizarAdhesionPDCBPriv(ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dto.FinalizarAdhesionPDC)
	 */
	@Override
	public Respuesta<FinalizarAdhesionPDCResponse> finalizarAdhesionPDCBPriv(FinalizarAdhesionPDC request) {
		return poderCompraManager.finalizarAdhesionPDCBPriv(request);
	}
	
	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.sei.PoderCompraSEI#verComprobanteActivarPoderComprar(ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.EstadisticaComprobantePoderCompra)
	 */
	@Override
	public Respuesta<ComprobanteOrdenCompraView> verComprobanteActivarPoderComprar(EstadisticaComprobantePoderCompra request) {
		return poderCompraManager.verComprobanteActivarPoderComprar(request);
	}


	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.sei.PoderCompraSEI#descargarComprobanteActivarPoderComprar(ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.ComprobanteActivarPoderCompra)
	 */
	@Override
	public Respuesta<ReporteView> descargarComprobanteActivarPoderComprar(ComprobanteActivarPoderCompra request) {
		return poderCompraManager.descargarAceptarPoderCompra(request,TipoBancaEnum.BANCA_PERSONAL);
	}

	@Override
	public Respuesta<AdhesionPDCOutDTO> configurarAdhesionPoderCompra(AdhesionPDCRequestDTO request) {
		return poderCompraManager.configurarAdhesionPoderCompra(request);
	}

	
	@Override
	public Respuesta<FinalizarAdhesionPDCResponse> confirmarAdhesionPDC(FinalizarAdhesionPDC request) {
		return poderCompraManager.confirmarAdhesionPDC(request);
	}
	
	
	@Override
	public Respuesta<Void> verComprobanteActivarPoderComprarVTV() {
		return poderCompraManager.verComprobanteActivarPoderComprarVTV();
	}
	
	@Override
	public Respuesta<ReporteView> descargarComprobanteActivarPoderComprarVTV(ComprobanteActivarPoderCompra request) {
		return poderCompraManager.descargarComprobanteActivarPoderComprarVTV(request);
	}
}
