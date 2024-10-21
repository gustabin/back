package ar.com.santanderrio.obp.servicios.transferencias.feature;

import ar.com.santanderrio.obp.generated.webservices.alias.CuentaDTO;
import ar.com.santanderrio.obp.generated.webservices.alias.TitularidadExtendido;

public class TransferenciaPorAliasBOFeature {
    public static TitularidadExtendido getTitularidadExtendido1() {
        TitularidadExtendido titularidadExtendido = new TitularidadExtendido();
        titularidadExtendido.setCtaDestino(getCuentaDTO1());
        return titularidadExtendido;
    }

    private static CuentaDTO getCuentaDTO1() {
        CuentaDTO cuentaDTO = new CuentaDTO();
        cuentaDTO.setNumeroCBU("0140353203613052625775");
        cuentaDTO.setFiidOrigenLink("0014");
        return cuentaDTO;
    }

    public static TitularidadExtendido getTitularidadExtendido2() {
        TitularidadExtendido titularidadExtendido = new TitularidadExtendido();
        titularidadExtendido.setCtaDestino(getCuentaDTO2());
        return titularidadExtendido;
    }

    private static CuentaDTO getCuentaDTO2() {
        CuentaDTO cuentaDTO = new CuentaDTO();
        cuentaDTO.setNumeroCBU("0110314230031414907965");
        cuentaDTO.setFiidOrigenLink("0011");
        return cuentaDTO;
    }

    public static TitularidadExtendido getTitularidadExtendido3() {
        TitularidadExtendido titularidadExtendido = new TitularidadExtendido();
        titularidadExtendido.setCtaDestino(getCuentaDTO3());
        return titularidadExtendido;
    }

    private static CuentaDTO getCuentaDTO3() {
        CuentaDTO cuentaDTO = new CuentaDTO();
        cuentaDTO.setNumeroCBU("0000003100086246878061");
        cuentaDTO.setFiidOrigenLink("");
        return cuentaDTO;
    }

    public static TitularidadExtendido getTitularidadExtendido4() {
        TitularidadExtendido titularidadExtendido = new TitularidadExtendido();
        titularidadExtendido.setCtaDestino(getCuentaDTO4());
        return titularidadExtendido;
    }

    private static CuentaDTO getCuentaDTO4() {
        CuentaDTO cuentaDTO = new CuentaDTO();
        cuentaDTO.setNumeroCBU("0070999030004058604895");
        cuentaDTO.setFiidOrigenLink(null);
        return cuentaDTO;
    }

    public static TitularidadExtendido getTitularidadExtendido5() {
        TitularidadExtendido titularidadExtendido = new TitularidadExtendido();
        titularidadExtendido.setCtaDestino(getCuentaDTO5());
        return titularidadExtendido;
    }

    private static CuentaDTO getCuentaDTO5() {
        CuentaDTO cuentaDTO = new CuentaDTO();
        cuentaDTO.setNumeroCBU("0140353203613052625775");
        cuentaDTO.setFiidOrigenLink("0011");
        return cuentaDTO;
    }
}