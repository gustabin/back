package ar.com.santanderrio.obp.servicios.delete.account.web.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.delete.account.web.view.DeleteAccountView;
import ar.com.santanderrio.obp.servicios.delete.account.web.view.ProductosBajaResponseView;
import ar.com.santanderrio.obp.servicios.delete.account.web.view.SolicitarBajaCuentaView;
import ar.com.santanderrio.obp.servicios.delete.account.web.view.SolicitudDeleteAccountView;

/**
 * @author A308529
 *
 */
public interface DeleteAccountWebManager {

	public Respuesta<DeleteAccountView> deleteCuenta(SolicitudDeleteAccountView inView);
	
	public Respuesta<ProductosBajaResponseView> getProductosBaja();
	
	public Respuesta<ReporteView> generarComprobantePDF();
	
	public Respuesta<SolicitarBajaCuentaView> solicitarBajaCuenta();
	
	public Respuesta<ReporteView> generarInvalidantesPDF();
}
