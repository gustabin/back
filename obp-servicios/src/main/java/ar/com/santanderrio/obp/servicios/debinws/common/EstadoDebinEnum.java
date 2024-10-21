/*
 * 
 */
package ar.com.santanderrio.obp.servicios.debinws.common;

import java.util.HashMap;

import java.util.Map;

/**
 * The Enum EstadoDebinEnum.
 */
public enum EstadoDebinEnum {

    /** INICIADO_Y_ACREDITADO. */
    INICIADO_Y_ACREDITADO("INICIADO_Y_ACREDITADO", null,"Iniciado y acreditado"),
	
    /** INICIADO. */
    INICIADO("INICIADO", "1","Iniciado"),

    /** VENCIDO. */
    VENCIDO("VENCIDO", "2","Vencido"),

    /** EN_CURSO. */
    EN_CURSO("EN CURSO", "3","En curso"),
    
    /** Acreditado. */
    ACREDITADO("ACREDITADO", "4","Acreditado"),

    /** ERROR_DATOS. */
    ERROR_DATOS("ERROR DATOS", "5","Error datos"),

    /** ERROR_DEBITO. */
    ERROR_DEBITO("ERROR DEBITO", "6","Error débito"),
    
    /** SIN_SALDO. */
    SIN_SALDO("SIN SALDO", "7","Sin saldo"),
    
    /** RECHAZO_CLIENTE. */
    RECHAZO_CLIENTE("RECHAZO DE CLIENTE","8","Rechazo cliente"),
   
    /** ELIMINADO. */
    ELIMINADO("ELIMINADO", "9","Eliminado"),
    
    /** ERROR_ACREDITACION. */
    ERROR_ACREDITACION("ERROR ACREDITACION", "10", "Error acreditación"),

    /** SIN_GARANTIA. */
    SIN_GARANTIA("SIN GARANTIA", "11", "Sin garantía");
	

   
    /** The descripcion. */
    String descripcion;

    /** The id. */
    String id;
    
    /** Descripcion para mostrar. */
    String descView;

    /**
	 * EstadoDebinEnum.
	 *
	 * @param descripcion
	 *            the descripcion
	 * @param id
	 *            the id
	 * @param descView
	 *            the desc view
	 */
    EstadoDebinEnum(String descripcion, String id, String descView) {
        this.descripcion = descripcion;
        this.id = id;
        this.descView = descView;
    }

    /**
     * Gets the id.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Gets the descripcion.
     *
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }
    
    /**
	 * Gets the desc view.
	 *
	 * @return the desc view
	 */
    public String getDescView() {
        return descView;
    }
    
    /**
	 * obtenerEstadoPorId.
	 *
	 * @param id
	 *            the estado
	 * @return the estado debin WS enum
	 */
    public static EstadoDebinEnum obtenerEstadoPorId(String id) {
        EstadoDebinEnum[] values = EstadoDebinEnum.values();
        for (EstadoDebinEnum estadoDebin : values) {
            if (estadoDebin.getId() != null && estadoDebin.getId().equals(id)) {
                return estadoDebin;
            }
        }
        return null;
    }
    
    /**
	 * obtenerEstadoPorDescripcion.
	 *
	 * @param estado
	 *            the estado
	 * @return the estado debin WS enum
	 */
    public static EstadoDebinEnum obtenerEstadoPorDescripcion(String estado) {
        EstadoDebinEnum[] values = EstadoDebinEnum.values();
        for (EstadoDebinEnum estadoDebin : values) {
            if (estado.trim().equalsIgnoreCase(estadoDebin.getDescripcion())) {
                return estadoDebin;
            }
        }
        return null;
    }
    
     /**
      *  obtenerEstadosRecibidos  
      * @return
      */
     public static Map<String,String> obtenerEstadosRecibidos(){
         Map<String,String> estados = new HashMap<String, String>();
         estados.put(ACREDITADO.getId(), ACREDITADO.getDescripcion());        
         estados.put(INICIADO.getId(), INICIADO.getDescripcion());
         estados.put(SIN_GARANTIA.getId(), SIN_GARANTIA.getDescripcion());
         estados.put(SIN_SALDO .getId(), SIN_SALDO.getDescripcion());
         estados.put(ELIMINADO.getId(), ELIMINADO.getDescripcion());
         estados.put(RECHAZO_CLIENTE.getId(), ELIMINADO.getDescripcion());
         estados.put(ERROR_ACREDITACION.getId(), ERROR_ACREDITACION.getDescripcion());
         estados.put(ERROR_DATOS.getId(), ERROR_DATOS.getDescripcion());

         return estados;
         
     }
     
     /**
      * obtenerEstadosEnviados
      * @return
      */
     public static Map<String,String> obtenerEstadosEnviados(){
         Map<String,String> estados = new HashMap<String, String>();
         estados.put(ACREDITADO.getId(), ACREDITADO.getDescripcion());        
         estados.put(INICIADO.getId(), INICIADO.getDescripcion());
         estados.put(SIN_GARANTIA.getId(), SIN_GARANTIA.getDescripcion());
         estados.put(SIN_SALDO .getId(), SIN_SALDO.getDescripcion());
         estados.put(ELIMINADO.getId(), ELIMINADO.getDescripcion());
         estados.put(RECHAZO_CLIENTE.getId(), ELIMINADO.getDescripcion());
         estados.put(ERROR_ACREDITACION.getId(), ERROR_ACREDITACION.getDescripcion());
         estados.put(ERROR_DATOS.getId(), ERROR_DATOS.getDescripcion());
         return estados;
     }
    
}
