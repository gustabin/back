/**
 * 
 */
package ar.com.santanderrio.obp.servicios.nuevopago.mock;

import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.mock.AutentificacionDTOMock;
import ar.com.santanderrio.obp.servicios.pagos.entities.NuevoPago;

/**
 * The Class NuevoPagoMock.
 *
 * @author florencia.n.martinez
 */
public class NuevoPagoMock {

    /**
     * Completar info sin desafio.
     *
     * @param isFromCalendarioPago
     *            the is from calendario pago
     * @param reintentar
     *            the reintentar
     * @return the nuevo pago
     */
    public static NuevoPago completarInfoSinDesafio(Boolean isFromCalendarioPago, String reintentar) {
        NuevoPago nuevoPago = new NuevoPago();
        nuevoPago.setIsFromCalendario(isFromCalendarioPago);
        nuevoPago.setFiid("aaa");
        nuevoPago.setCodigoPagoElectronico("1234556666");
        nuevoPago.setCodigoPagoElectronico2("133212344");
        nuevoPago.setNumeroDeAnticipo("444455677");
        nuevoPago.setNumeroDeCuota("2");
        nuevoPago.setNumeroReferenciaPago("4344566556");
        nuevoPago.setMonto("1223.2");
        nuevoPago.setFacturaNumero("0001109253751208000");
        nuevoPago.setReintentar(reintentar);
        nuevoPago.setNumeroDeCuota("000001234567");
        nuevoPago.setMes("6");
        nuevoPago.setAnio("2017");
        nuevoPago.setCuentaSeleccionada("1234567890123456789012");
        nuevoPago.setNombreEmpresa("Unicef");
        nuevoPago.setDesafio(null);
        return nuevoPago;
    }

    /**
     * Completar info con desafio.
     *
     * @param isFromCalendarioPago
     *            the is from calendario pago
     * @param reintentar
     *            the reintentar
     * @return the nuevo pago
     */
    public static NuevoPago completarInfoConDesafio(Boolean isFromCalendarioPago, String reintentar) {
        NuevoPago nuevoPago = NuevoPagoMock.completarInfoSinDesafio(isFromCalendarioPago, reintentar);
        nuevoPago.setDesafio(AutentificacionDTOMock.completarDesafioToken());
        return nuevoPago;
    }
}
