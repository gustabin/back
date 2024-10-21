/**
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.mock;

import ar.com.santanderrio.obp.servicios.tarjetas.entities.ComprobantePagoTarjeta;

/**
 * The Class ComprobantePagoTarjetaMock.
 *
 * @author florencia.n.martinez
 */
public class ComprobantePagoTarjetaMock {

    /**
     * Completa la info del objeto.
     *
     * @param mensaje
     *            the mensaje
     * @return the comprobante pago tarjeta
     */
    public static ComprobantePagoTarjeta completarInfo(String mensaje) {
        ComprobantePagoTarjeta comprobante = new ComprobantePagoTarjeta();
        comprobante.setMensaje(mensaje);
        comprobante.setNroComprobante("00000001");
        comprobante.setFechaHora("25/04/2017 - 12:45");
        return comprobante;
    }
}
