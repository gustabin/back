/**
 * 
 */
package ar.com.santanderrio.obp.servicios.nuevopago.mock;

import ar.com.santanderrio.obp.servicios.pagos.web.view.ConsultaConfiguracionView;

/**
 * The Class ConsultaConfiguracionRecargaViewMock.
 *
 * @author florencia.n.martinez
 */
public class ConsultaConfiguracionRecargaViewMock {

    /**
     * Completar info.
     *
     * @param fiid
     *            the fiid
     * @param puntoAcceso
     *            the punto acceso
     * @param monto
     *            the monto
     * @return the consulta configuracion recarga view
     */
    public static ConsultaConfiguracionView completarInfo(String fiid, String puntoAcceso, String monto) {
        ConsultaConfiguracionView view = new ConsultaConfiguracionView();
        view.setFiid(fiid);
        view.setPuntoDeAcceso(puntoAcceso);
        view.setMonto(monto);
        return view;
    }

}
