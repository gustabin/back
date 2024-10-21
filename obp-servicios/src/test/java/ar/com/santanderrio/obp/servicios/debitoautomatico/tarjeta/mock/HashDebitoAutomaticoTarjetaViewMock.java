/**
 * 
 */
package ar.com.santanderrio.obp.servicios.debitoautomatico.tarjeta.mock;

import ar.com.santanderrio.obp.servicios.pagoautomatico.view.HashDebitoAutomaticoTarjetaView;

/**
 * The Class HashDebitoAutomaticoTarjetaViewMock.
 *
 * @author florencia.n.martinez
 */
public class HashDebitoAutomaticoTarjetaViewMock {

    /**
     * Completa la informacion para el hash in para Visa.
     *
     * @return the hash debito automatico tarjeta view
     */
    public static HashDebitoAutomaticoTarjetaView completarInfoHashInVisa() {
        HashDebitoAutomaticoTarjetaView hash = new HashDebitoAutomaticoTarjetaView();
        hash.setNombreFantasia("Telecom");
        hash.setNroIdentificacion("5796228542");
        hash.setNumeroTarjetaEnmascarado("VISA XXXX-1234");
        hash.setCuit("20-30321396-5");
        return hash;
    }

    /**
     * Completa la informacion para el hash in para Amex.
     *
     * @return the hash debito automatico tarjeta view
     */
    public static HashDebitoAutomaticoTarjetaView completarInfoHashInAmex() {
        HashDebitoAutomaticoTarjetaView hash = new HashDebitoAutomaticoTarjetaView();
        hash.setNombreFantasia("Telecom");
        hash.setNroIdentificacion("5796228542");
        hash.setNumeroTarjetaEnmascarado("AMEX XXXX-21696");
        hash.setCuit("20-30321396-5");
        return hash;
    }

    /**
     * Completa la informacion para el hash in para Amex II.
     *
     * @return the hash debito automatico tarjeta view
     */
    public static HashDebitoAutomaticoTarjetaView completarInfoHashInAmexII() {
        HashDebitoAutomaticoTarjetaView hash = new HashDebitoAutomaticoTarjetaView();
        hash.setNombreFantasia("Telecom");
        hash.setNroIdentificacion("5796228512");
        hash.setNumeroTarjetaEnmascarado("AMEX XXXX-21696");
        hash.setCuit("20-30321396-5");
        return hash;
    }
}
