package ar.com.santanderrio.obp.servicios.agenda.destinatarios.mock;

import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.view.ConfiguracionAltaDestinatarioCBUOutView;
import ar.com.santanderrio.obp.servicios.comun.constantes.BancoEnum;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;

/**
 * The Class ConfiguracionAltaDestinatarioCBUOutViewMock.
 *
 * @author florencia.n.martinez
 */
public final class ConfiguracionAltaDestinatarioCBUOutViewMock {

    private ConfiguracionAltaDestinatarioCBUOutViewMock() {
        throw new IllegalAccessError("Clase para testing");
    }
    
    /**
     * Completar info rio.
     *
     * @return the configuracion alta destinatario CBU out view
     */
    public static ConfiguracionAltaDestinatarioCBUOutView completarInfoRio() {
        ConfiguracionAltaDestinatarioCBUOutView view = new ConfiguracionAltaDestinatarioCBUOutView();
        view.setTitular("MUÑOZ, CÉSAR ALBERTO");
        view.setNroCuenta("072-123456/7");
        view.setTipoCuenta(TipoCuenta.CUENTA_UNICA.getDescripcionConMoneda());
        view.setBanco(BancoEnum.SANTANDER_RIO.getDescripcion());
        view.setNumeroCuil("30-12123345-7");
        view.setIsRio(Boolean.TRUE);
        return view;
    }

    /**
     * Completar info no rio.
     *
     * @return the configuracion alta destinatario CBU out view
     */
    public static ConfiguracionAltaDestinatarioCBUOutView completarInfoNoRio() {
        ConfiguracionAltaDestinatarioCBUOutView view = new ConfiguracionAltaDestinatarioCBUOutView();
        view.setTitular("MUÑOZ, CÉSAR ALBERTO");
        view.setCbu("0721234567574653567885");
        view.setBanco("Banco Nación");
        view.setNumeroCuil("30-12123345-7");
        view.setIsRio(Boolean.FALSE);
        return view;
    }
}
