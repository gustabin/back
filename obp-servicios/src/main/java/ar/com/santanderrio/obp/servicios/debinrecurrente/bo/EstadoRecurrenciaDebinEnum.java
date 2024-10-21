package ar.com.santanderrio.obp.servicios.debinrecurrente.bo;

public enum EstadoRecurrenciaDebinEnum {
    ACTIVA("ACTIVE", "12", "Activa"),

    INACTIVA_DEFINITIVA("INACTIVE_DEF", "13", "Dada de baja"),

    INACTIVA("INACTIVE_TEMP", "14", "Pausada"),

    PENDIENTE("PENDING", "15", "Solicitud pendiente"),

    RECHAZADA("REJECT", "16", "Rechazada");




    /** The descripcion. */
    String descripcion;

    /** The id. */
    String id;

    /** Descripcion para mostrar. */
    String descView;

    /**
     * EstadoRecurrenciaDebinEnum.
     *
     * @param descripcion
     *            the descripcion
     * @param id
     *            the id
     * @param descView
     *            the desc view
     */
    EstadoRecurrenciaDebinEnum(String descripcion, String id, String descView) {
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
     * obtenerEstadoPorDescripcion.
     *
     * @param estado
     *            the estado
     * @return the estado debin WS enum
     */
    public static EstadoRecurrenciaDebinEnum obtenerEstadoPorDescripcion(String estado) {
        EstadoRecurrenciaDebinEnum[] values = EstadoRecurrenciaDebinEnum.values();
        for (EstadoRecurrenciaDebinEnum estadoDebin : values) {
            if (estado.trim().equalsIgnoreCase(estadoDebin.getDescripcion())) {
                return estadoDebin;
            }
        }
        return null;
    }

}
