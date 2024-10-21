package ar.com.santanderrio.obp.servicios.agenda.destinatarios.mock;

import ar.com.santanderrio.obp.generated.webservices.alias.CuentaDTO;
import ar.com.santanderrio.obp.generated.webservices.alias.TitularidadExtendido;
import ar.com.santanderrio.obp.servicios.comun.constantes.BancoEnum;

import java.util.Arrays;

/**
 * The Class TitularidadExtendidoMock.
 * @author Manuel.Vargas B041299. Agregado de mas datos.
 * @author florencia.n.martinez
 * @version 2
 */
public final class TitularidadExtendidoMock {

    private TitularidadExtendidoMock() {
        throw new IllegalAccessError("Clase para testing");
    }
    
    /**
     * Completar info titularidad rio.
     *
     * @return the titularidad extendido
     */
    public static TitularidadExtendido completarInfoTitularidadRio(CuentaDTO ctaDestino) {
        TitularidadExtendido titExt = new TitularidadExtendido();
        titExt.setAlias("FuncionaAlias014");
        titExt.setCtaDestino(ctaDestino);
        titExt.setCuits(Arrays.asList("27057993575"));
        titExt.setNombreBanco(BancoEnum.SANTANDER_RIO.getDescripcion());
        titExt.setFiidBanco("fiidBanco");
        titExt.setFiidOrigenLink("fiidOrigenLink");
        titExt.setNombreTitular("CUPI CARLOS ALBERTO /FERNANDEZ NORMA ELSA");
        return titExt;
    }
    
    /**
     * Completar info titularidad no rio.
     *
     * @param ctaDestino the cta destino
     * @return the titularidad extendido
     */
    public static TitularidadExtendido completarInfoTitularidadNoRio(CuentaDTO ctaDestino) {
        TitularidadExtendido titExt = new TitularidadExtendido();
        titExt.setAlias("FuncionaAlias014");
        titExt.setCtaDestino(ctaDestino);
        titExt.setCuits(Arrays.asList("27057993575"));
        titExt.setNombreBanco("Banco Nación");
        titExt.setNombreTitular("CUPI CARLOS ALBERTO /FERNANDEZ NORMA ELSA");
        return titExt;
    }
}
