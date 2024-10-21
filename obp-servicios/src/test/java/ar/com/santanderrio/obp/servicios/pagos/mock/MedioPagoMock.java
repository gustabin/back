/**
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.mock;

import ar.com.santanderrio.obp.servicios.pagos.entities.MedioPago;

/**
 * The Class MedioPagoMock.
 *
 * @author florencia.n.martinez
 */
public class MedioPagoMock {

    /**
     * Completar info.
     *
     * @param nombreFantasia
     *            the nombre fantasia
     * @return the medio pago
     */
    public static MedioPago completarInfo(String nombreFantasia, Integer tipoPago, String tipoEmpresa) {
        MedioPago medioPago = new MedioPago();
        medioPago.setNombreFantasia(nombreFantasia);
        medioPago.setTipoPago(tipoPago);
        medioPago.setMoneda("$");
        medioPago.setTipoEmpresa(tipoEmpresa);
        return medioPago;
    }
}
