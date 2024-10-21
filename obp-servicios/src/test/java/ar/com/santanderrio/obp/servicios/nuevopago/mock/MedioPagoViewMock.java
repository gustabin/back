/**
 * 
 */
package ar.com.santanderrio.obp.servicios.nuevopago.mock;

import ar.com.santanderrio.obp.servicios.pagos.entities.MedioPagoView;

/**
 * The Class MedioPagoViewMock.
 *
 * @author florencia.n.martinez
 */
public class MedioPagoViewMock {

    /**
     * Completar info.
     *
     * @param rubroFantasia
     *            the rubro fantasia
     * @param nombreFantasia
     *            the nombre fantasia
     * @param fiid
     *            the fiid
     * @param tipoNuevoPago
     *            the tipo nuevo pago
     * @return the medio pago view
     */
    public static MedioPagoView completarInfo(String rubroFantasia, String nombreFantasia, String fiid,
            String tipoNuevoPago) {
        MedioPagoView medioPagoView = new MedioPagoView();
        medioPagoView.setRubroFantasia(rubroFantasia);
        medioPagoView.setNombreFantasia(nombreFantasia);
        medioPagoView.setCodigoPagoElectronico("1234567890");
        medioPagoView.setCodigoPagoElectronico2("2345678901");
        medioPagoView.setFiid(fiid);
        medioPagoView.setCuit("23-12345678-9");
        medioPagoView.setTipoNuevoPago(tipoNuevoPago);
        return medioPagoView;
    }
}
