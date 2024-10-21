package ar.com.santanderrio.obp.servicios.delete.account.sei.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.delete.account.sei.DeleteAccountSEI;
import ar.com.santanderrio.obp.servicios.delete.account.web.manager.DeleteAccountWebManager;
import ar.com.santanderrio.obp.servicios.delete.account.web.manager.DiscadorWebManager;
import ar.com.santanderrio.obp.servicios.delete.account.web.view.DeleteAccountView;
import ar.com.santanderrio.obp.servicios.delete.account.web.view.DiscadorView;
import ar.com.santanderrio.obp.servicios.delete.account.web.view.ProductosBajaResponseView;
import ar.com.santanderrio.obp.servicios.delete.account.web.view.SolicitarBajaCuentaView;
import ar.com.santanderrio.obp.servicios.delete.account.web.view.SolicitudDeleteAccountView;
import ar.com.santanderrio.obp.servicios.delete.account.web.view.SolicitudDiscadorView;

@Component
public class DeleteAccountSEIImpl implements DeleteAccountSEI {

	@Autowired
	private DeleteAccountWebManager deleteCuentaManager;
	
	@Autowired
	private DiscadorWebManager discadorManager;
	
	@Override
	public Respuesta<DeleteAccountView> deleteAccount(SolicitudDeleteAccountView inView) {
		return deleteCuentaManager.deleteCuenta(inView);
	}

	@Override
	public Respuesta<ProductosBajaResponseView> getProductosBaja() {
		return deleteCuentaManager.getProductosBaja();
	}
	
	@Override
	public Respuesta<SolicitarBajaCuentaView> solicitarBajaCuenta() {
		return deleteCuentaManager.solicitarBajaCuenta();
	}

	@Override
	public Respuesta<DiscadorView> solicitarDiscador(SolicitudDiscadorView inView) {
		return discadorManager.solicitarDiscador(inView);
	}

	@Override
	public Respuesta<ReporteView> generarComprobantePDF() {
		return deleteCuentaManager.generarComprobantePDF();
	}

	@Override
	public Respuesta<ReporteView> generarDetallePDF() {
		return deleteCuentaManager.generarInvalidantesPDF();
	}

}
