package ar.com.santanderrio.obp.servicios.agenda.destinatarios.mock;

import ar.com.santanderrio.obp.generated.webservices.alias.TipoCuentaDTO;

/**
 * The Class TipoCuentaDTOMock.
 *
 * @author florencia.n.martinez
 */
public final class TipoCuentaDTOMock {

    private TipoCuentaDTOMock() {
        throw new IllegalAccessError("Clase para testing");
    }
    
    /**
     * Completar info tipo CC.
     *
     * @return the tipo cuenta DTO
     */
    public static TipoCuentaDTO completarInfoTipoCC() {
        TipoCuentaDTO tipo = new TipoCuentaDTO();
        tipo.setCodigo("0");
        tipo.setDescripcion("CC");
        return tipo;
    }
    
    /**
     * Completar info tipo CA.
     *
     * @return the tipo cuenta DTO
     */
    public static TipoCuentaDTO completarInfoTipoCA() {
        TipoCuentaDTO tipo = new TipoCuentaDTO();
        tipo.setCodigo("1");
        tipo.setDescripcion("CC");
        return tipo;
    }
    
    /**
     * Completar info otro tipo.
     *
     * @return the tipo cuenta DTO
     */
    public static TipoCuentaDTO completarInfoOtroTipo() {
        TipoCuentaDTO tipo = new TipoCuentaDTO();
        tipo.setCodigo("-1");
        tipo.setDescripcion("B");
        return tipo;
    }
}
