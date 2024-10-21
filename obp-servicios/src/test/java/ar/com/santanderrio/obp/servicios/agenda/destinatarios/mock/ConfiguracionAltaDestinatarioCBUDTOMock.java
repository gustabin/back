package ar.com.santanderrio.obp.servicios.agenda.destinatarios.mock;

import ar.com.santanderrio.obp.servicios.agenda.destinatarios.dto.ConfiguracionAltaDestinatarioCBUDTO;
import ar.com.santanderrio.obp.servicios.comun.constantes.BancoEnum;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;

/**
 * The Class ConfiguracionAltaDestinatarioCBUDTOMock.
 *
 * @author florencia.n.martinez
 */
public final class ConfiguracionAltaDestinatarioCBUDTOMock {
    
    private ConfiguracionAltaDestinatarioCBUDTOMock() {
        throw new IllegalAccessError("Clase para testing");
    }

    /**
     * Completar info rio.
     *
     * @return the configuracion alta destinatario CBUDTO
     */
    public static ConfiguracionAltaDestinatarioCBUDTO completarInfoRio() {
        ConfiguracionAltaDestinatarioCBUDTO dto = new ConfiguracionAltaDestinatarioCBUDTO();
        dto.setTitular("MUÑOZ, CÉSAR ALBERTO");
        dto.setNumeroCuenta("072-123456/7");
        dto.setTipoCuenta(TipoCuenta.CUENTA_UNICA.getDescripcionConMoneda());
        dto.setBanco(BancoEnum.SANTANDER_RIO.getDescripcion());
        dto.setNumeroCuil("30-12123345-7");
        dto.setIsRio(Boolean.TRUE);
        dto.setCbu("0123456789012345678912");
        return dto;
    }

    /**
     * Completar info no rio.
     *
     * @return the configuracion alta destinatario CBUDTO
     */
    public static ConfiguracionAltaDestinatarioCBUDTO completarInfoNoRio() {
        ConfiguracionAltaDestinatarioCBUDTO dto = new ConfiguracionAltaDestinatarioCBUDTO();
        dto.setTitular("MUÑOZ, CÉSAR ALBERTO");
        dto.setBanco("Banco Nación");
        dto.setNumeroCuil("30-12123345-7");
        dto.setIsRio(Boolean.FALSE);
        dto.setCbu("0123456789012345678912");
        return dto;
    }
}
