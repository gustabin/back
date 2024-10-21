/**
 * 
 */
package ar.com.santanderrio.obp.servicios.nuevopago.mock;

import ar.com.santanderrio.obp.servicios.pagos.web.view.PagoPendienteView;

/**
 * @author florencia.n.martinez
 *
 */
public class PagoPendienteViewMock {

    public static PagoPendienteView completarInfo(Boolean isRecargar) {
        PagoPendienteView pagoPendienteView = new PagoPendienteView();
        pagoPendienteView.setIsRecargar(isRecargar);
        return pagoPendienteView;
    }
}
