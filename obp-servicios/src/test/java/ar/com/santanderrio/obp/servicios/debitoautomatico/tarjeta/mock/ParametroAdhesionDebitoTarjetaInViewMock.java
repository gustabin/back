/**
 * 
 */
package ar.com.santanderrio.obp.servicios.debitoautomatico.tarjeta.mock;

import ar.com.santanderrio.obp.servicios.debitoautomatico.tarjeta.web.view.ParametroAdhesionDebitoTarjetaInView;

/**
 * The Class ParametroAdhesionDebitoTarjetaInViewMock.
 *
 * @author florencia.n.martinez
 */
public class ParametroAdhesionDebitoTarjetaInViewMock {

    /**
     * Completar info parametros para Visa.
     *
     * @return the parametro adhesion debito tarjeta in view
     */
    public static ParametroAdhesionDebitoTarjetaInView completarInfoParametrosVisa() {
        ParametroAdhesionDebitoTarjetaInView parametro = new ParametroAdhesionDebitoTarjetaInView();
        parametro.setDatosCliente(HashDebitoAutomaticoTarjetaViewMock.completarInfoHashInVisa());
        parametro.setReintentar(Boolean.TRUE);
        return parametro;
    }

    /**
     * Completar info parametros para Amex.
     *
     * @return the parametro adhesion debito tarjeta in view
     */
    public static ParametroAdhesionDebitoTarjetaInView completarInfoParametrosAmex() {
        ParametroAdhesionDebitoTarjetaInView parametro = new ParametroAdhesionDebitoTarjetaInView();
        parametro.setDatosCliente(HashDebitoAutomaticoTarjetaViewMock.completarInfoHashInAmex());
        parametro.setReintentar(Boolean.TRUE);
        return parametro;
    }

    /**
     * Completa info parametros para Amex, con reintentar en false.
     *
     * @return the parametro adhesion debito tarjeta in view
     */
    public static ParametroAdhesionDebitoTarjetaInView completarInfoParametrosAmexReintentarFalse() {
        ParametroAdhesionDebitoTarjetaInView parametro = new ParametroAdhesionDebitoTarjetaInView();
        parametro.setDatosCliente(HashDebitoAutomaticoTarjetaViewMock.completarInfoHashInAmex());
        parametro.setReintentar(Boolean.FALSE);
        return parametro;
    }
}
