/**
 * 
 */
package ar.com.santanderrio.obp.servicios.agenda.destinatarios.mock;

import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.view.ConfiguracionAltaDestinatarioInView;

/**
 * The Class ConfiguracionAltaDestinatarioInViewMock.
 *
 * @author florencia.n.martinez
 */
public final class ConfiguracionAltaDestinatarioInViewMock {

    private ConfiguracionAltaDestinatarioInViewMock() {
        throw new IllegalAccessError("Clase para testing");
    }
    
    /**
     * Completar info para alias valido.
     *
     * @return the configuracion alta destinatario in view
     */
    public static ConfiguracionAltaDestinatarioInView completarInfoParaAliasValido() {
        ConfiguracionAltaDestinatarioInView inView = new ConfiguracionAltaDestinatarioInView();
        inView.setAlias("cesalbert7");
        inView.setIsPesos(Boolean.TRUE);
        return inView;
    }

    /**
     * Completar info para alias invalido.
     *
     * @return the configuracion alta destinatario in view
     */
    public static ConfiguracionAltaDestinatarioInView completarInfoParaAliasInvalido() {
        ConfiguracionAltaDestinatarioInView inView = new ConfiguracionAltaDestinatarioInView();
        inView.setAlias("cesalbert7 ");
        inView.setIsPesos(Boolean.TRUE);
        return inView;
    }

    /**
     * Completar info para nro cuenta valido.
     *
     * @return the configuracion alta destinatario in view
     */
    public static ConfiguracionAltaDestinatarioInView completarInfoParaNroCuentaValido() {
        ConfiguracionAltaDestinatarioInView inView = new ConfiguracionAltaDestinatarioInView();
        inView.setNroCuenta("072-123456/7");
        inView.setIsPesos(Boolean.TRUE);
        return inView;
    }
}
