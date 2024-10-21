package ar.com.santanderrio.obp.servicios.inversiones.resumen.web.view;

/**
 * The Class ResumenMensualInversionesView.
 */
public class ResumenMensualInversionesView {

    /** The id. */
    private Long id;

    /** The visto. */
    private Boolean visto;

    /** The periodo. */
    private String periodo;

    /**
     * Gets the id.
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the id.
     *
     * @param id
     *            the new id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the visto.
     *
     * @return the visto
     */
    public Boolean getVisto() {
        return visto;
    }

    /**
     * Sets the visto.
     *
     * @param visto
     *            the new visto
     */
    public void setVisto(Boolean visto) {
        this.visto = visto;
    }

    /**
     * @return the periodo
     */
    public String getPeriodo() {
        return periodo;
    }

    /**
     * @param periodo
     *            the periodo to set
     */
    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }
}
