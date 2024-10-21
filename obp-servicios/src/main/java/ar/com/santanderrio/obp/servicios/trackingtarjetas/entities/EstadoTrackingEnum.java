/*
 * 
 */

package ar.com.santanderrio.obp.servicios.trackingtarjetas.entities;

/**
 * The Enum EstadoTrackingEnum.
 *
 * @author julian.ariel.karp
 */
public enum EstadoTrackingEnum {
    
    /** The asgmtt. */
    ASGMTT("TT", "1", "6311"),
    
    /** The asgmtr. */
    ASGMTR("TR", "2","6312"),
    
    /** The asgmsu. */
    ASGMSU("SU", "3", "6313"),
    
    /** The asgmde. */
    ASGMDE("DE", "4","6314"),
    
    /** The asgmc6. */
    ASGMC6("C6", "2","6315"),
    
    /** The agsm. */
    AGSM("CL","3","23"), 
    
    /** The andreani14. */
    ANDREANI14("14", "2", "6316"),
    
    /** The andreani15. */
    ANDREANI15("15", "2", "6317"),
    
    /** The andreani16. */
    ANDREANI16("16", "3", "6318"),
    
    /** The andreani17. */
    ANDREANI17("17", "3","6319"),
    
    /** The andreani18. */
    ANDREANI18("18", "2", "6320"),
    
    /** The andreani21. */
    ANDREANI21("21", "2","6321"),
    
    /** The andreani22. */
    ANDREANI22("22", "3","6322"),
    
    /** The andreani23. */
    ANDREANI23("23", "3", "6323"),
    
    /** The andreani24. */
    ANDREANI24("24", "3","6324"),
    
    /** The andreani32. */
    ANDREANI32("32", "3","6325"),
    
    /** The andreani35. */
    ANDREANI35("35","1","6336"),
   
    /** The andreani1219. */
    ANDREANI1219_REIMPRESION("1219", "4","6326"),

    /** The andreani1219. */
    ANDREANI1219_SIN_REIMPRESION("1219", "4","6327"),
   
    /** The andreani1219. */
    ANDREANI1219_REIMPRESION_MONEDERO("1219","4","6328"),
    
    /** The andreani1219. */
    ANDREANI1219_REIMPRESION_RECARGABLE("1219","4","6329"),
    
    /** The error. */
    ERROR("ERROR", "","");
    
    /** Estado proveniente del WS. */
    private String idEstadoWS;
    
    /** Estado que se mostrara por pantalla. */
    private String idEstadoView;
    
    /** Descripcion de estado que se mostrara por pantalla. */
    private String codigoMensajeDB;
    


    /**
	 * Instantiates a new estado tracking enum.
	 *
	 * @param idEstadoWS
	 *            the id estado WS
	 * @param idEstadoView
	 *            the id estado view
	 * @param codigoMensajeDB
	 *            the codigo mensaje DB
	 */
    EstadoTrackingEnum(String idEstadoWS, String idEstadoView, String codigoMensajeDB) {
        this.idEstadoWS = idEstadoWS;
        this.idEstadoView = idEstadoView;
        this.codigoMensajeDB = codigoMensajeDB;
    }

 
    
    /**
	 * Gets the id estado WS.
	 *
	 * @return the id estado WS
	 */
    public String getIdEstadoWS() {
        return idEstadoWS;
    }


    /**
	 * Gets the id estado view.
	 *
	 * @return the id estado view
	 */
    public String getIdEstadoView() {
        return idEstadoView;
    }



    /**
	 * Gets the codigo mensaje DB.
	 *
	 * @return the codigo mensaje DB
	 */
    public String getCodigoMensajeDB() {
        return codigoMensajeDB;
    }



    /**
     * Obtener por estado.
     *
     * @param estado the estado
     * @return the estado tracking enum
     */
    public static EstadoTrackingEnum obtenerPorEstado(String estado) {
        for (EstadoTrackingEnum estadoEnum : values()) {
            if (estadoEnum.getIdEstadoWS().equals(estado)) {
                return estadoEnum;
            }
        }
        return ERROR;
    }
}
