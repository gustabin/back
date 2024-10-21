/**
 * 
 */
package ar.com.santanderrio.obp.servicios.campaniabeneficios.manager;

import org.apache.commons.lang3.StringUtils;

/**
 * @author sergio.e.goldentair
 *
 */
public enum ProgramaLegalEnum {
    CAC("CAC", "CAMPANIA_LINK_1990", "CAMPANIA_LINK_1933"),
    CAS("CAS", "CAMPANIA_LINK_1990", "CAMPANIA_LINK_1933"),
    PIC("PIC", "CAMPANIA_LINK_1990", "CAMPANIA_LINK_1973"),
    PIS("PIS", "CAMPANIA_LINK_1990", "CAMPANIA_LINK_1973"),
    PBC("PBC", "CAMPANIA_LINK_1990", "CAMPANIA_LINK_11006"),
    PBS("PBS", "CAMPANIA_LINK_1990", "CAMPANIA_LINK_11006"),
    PGC("PGC", "CAMPANIA_LINK_1990", "CAMPANIA_LINK_1974"),
    PGS("PGS", "CAMPANIA_LINK_1990", "CAMPANIA_LINK_1974"),
    PPC("PPC", "CAMPANIA_LINK_1990", "CAMPANIA_LINK_1975"),
    PPS("PPS", "CAMPANIA_LINK_1990", "CAMPANIA_LINK_1975"),
    EMPTY(StringUtils.EMPTY, StringUtils.EMPTY, StringUtils.EMPTY);
    
    /**
     * programa utilizado.
     */
    private final String programa;
    /**
     * link para terminos y condiciones.
     */
    private final String linkTyc;

    /**
     * link para comisiones.
     */
    private final String linkComisiones;
    /**
     * @param programa
     * @param codigoMensajeConstantes
     */
    private ProgramaLegalEnum(String programa, String linkTyc, String linkComisiones) {
        this.programa = programa;
        this.linkTyc = linkTyc;
        this.linkComisiones = linkComisiones;
    }
    
    /**
     * Obtener las campanias y en caso de no estar en la lista no incluir el legal
     * correspondiente, esto seria el caso por ejemplo de turbo.
     * 
     * @param programaBusqueda
     * @return
     */
    public static ProgramaLegalEnum obtenerCampaniaLegalDesdePrograma(String programaBusqueda) {
        if(StringUtils.isNotBlank(programaBusqueda)) {
            for (ProgramaLegalEnum campaniaLegal : ProgramaLegalEnum.values()) {
                if (campaniaLegal.programa.equalsIgnoreCase(programaBusqueda)) {
                    return campaniaLegal;
                }
            }
        }
        return EMPTY;
    }

    /**
     * @return the linkTyc
     */
    public String getLinkTyc() {
        return linkTyc;
    }

    /**
     * @return the linkComisiones
     */
    public String getLinkComisiones() {
        return linkComisiones;
    }

}
