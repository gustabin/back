/**
 * 
 */
package ar.com.santanderrio.obp.servicios.debitoautomatico.tarjeta.mock;

import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.debitoautomatico.tarjeta.dto.DatoClienteDebitoTarjetaInDTO;

/**
 * The Class DatoClienteDebitoTarjetaInDTOMock.
 *
 * @author florencia.n.martinez
 */
public class DatoClienteDebitoTarjetaInDTOMock {

    /**
     * Completa info de DatoClienteDebitoTarjetaInDTO.
     *
     * @return the dato cliente debito tarjeta in DTO
     */
    public static DatoClienteDebitoTarjetaInDTO completarInfo() {
        return new DatoClienteDebitoTarjetaInDTO(HashDebitoAutomaticoTarjetaViewMock.completarInfoHashInVisa(), obtenerCuentaVisa());
    }

    /**
     * Obtener cuenta visa.
     *
     * @return the cuenta
     */
    private static Cuenta obtenerCuentaVisa() {
        Cuenta cuenta = new Cuenta();
        cuenta.setNroCuentaProducto("0000000025798066");
        cuenta.setNroTarjetaCredito("23445565435465671234");
        return cuenta;
    }
}
