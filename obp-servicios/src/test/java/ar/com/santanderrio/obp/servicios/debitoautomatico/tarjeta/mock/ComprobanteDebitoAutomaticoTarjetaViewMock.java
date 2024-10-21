/**
 * 
 */
package ar.com.santanderrio.obp.servicios.debitoautomatico.tarjeta.mock;

import ar.com.santanderrio.obp.servicios.debitoautomatico.tarjeta.web.view.ComprobanteDebitoAutomaticoTarjetaView;

/**
 * @author florencia.n.martinez
 *
 */
public class ComprobanteDebitoAutomaticoTarjetaViewMock {

    public static ComprobanteDebitoAutomaticoTarjetaView completarInfoComprobanteDebitoAutomaticoTarjetaView() {
        ComprobanteDebitoAutomaticoTarjetaView comprobante = new ComprobanteDebitoAutomaticoTarjetaView();
        comprobante.setEmpresa("Telecom");
        comprobante.setMedioPago("VISA XXXX - 1234");
        comprobante.setNroCuentaCliente("005796228542");
        comprobante.setCuitEmpresa("20-30321396-5");
        comprobante.setNroDeComprobante("123456789");
        comprobante.setFechaHora("26/05/2017 - 14:40");
        return comprobante;
    }
}
