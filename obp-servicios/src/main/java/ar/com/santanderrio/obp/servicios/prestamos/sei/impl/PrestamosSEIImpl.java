package ar.com.santanderrio.obp.servicios.prestamos.sei.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.prestamos.sei.PrestamosSEI;
import ar.com.santanderrio.obp.servicios.prestamos.view.CancelacionAnticipadaInView;
import ar.com.santanderrio.obp.servicios.prestamos.view.CancelacionAnticipadaOutView;
import ar.com.santanderrio.obp.servicios.prestamos.view.LiquidacionPrestamoInView;
import ar.com.santanderrio.obp.servicios.prestamos.view.PagoCuotaInView;
import ar.com.santanderrio.obp.servicios.prestamos.view.PagoCuotaOutView;
import ar.com.santanderrio.obp.servicios.prestamos.view.SolicitudPrestamoInView;
import ar.com.santanderrio.obp.servicios.prestamos.view.SolicitudPrestamoOutView;
import ar.com.santanderrio.obp.servicios.prestamos.web.manager.CancelacionAnticipadaManager;
import ar.com.santanderrio.obp.servicios.prestamos.web.manager.PagoCuotaManager;
import ar.com.santanderrio.obp.servicios.prestamos.web.manager.SolicitudLiquidacionPrestamoManager;

@Component("prestamosSei")
public class PrestamosSEIImpl implements PrestamosSEI {

	@Autowired
	private SolicitudLiquidacionPrestamoManager solicitudLiquidacionPrestamoManager;

	@Autowired
	private PagoCuotaManager pagoCuotaManager;
	
	@Autowired
	private CancelacionAnticipadaManager cancelacionAnticipadaManager;

	@Override
	public Respuesta<SolicitudPrestamoOutView> solicitar(SolicitudPrestamoInView solicitudPrestamoInView) {
		return solicitudLiquidacionPrestamoManager.solicitar(solicitudPrestamoInView);
	}

	@Override
	public Respuesta<SolicitudPrestamoOutView> liquidar(LiquidacionPrestamoInView liquidacionPrestamoInView) {
		return solicitudLiquidacionPrestamoManager.liquidar(liquidacionPrestamoInView);
	}

	@Override
	public Respuesta<PagoCuotaOutView> pagar(String loanNumber, PagoCuotaInView pagoCuotaView) {
		return pagoCuotaManager.pagar(loanNumber, pagoCuotaView);
	}

	@Override
	public Respuesta<CancelacionAnticipadaOutView> cancelacionAnticipada(String numeroPrestamo,
			CancelacionAnticipadaInView cancelacionAnticipadaInView) {
		return cancelacionAnticipadaManager.cancelarAnticipadamente(numeroPrestamo, cancelacionAnticipadaInView);
	}

}
