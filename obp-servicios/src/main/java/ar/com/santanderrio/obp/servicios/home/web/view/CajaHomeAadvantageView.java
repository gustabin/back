/*
 * 
 */
package ar.com.santanderrio.obp.servicios.home.web.view;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

/**
 * The Class CajaHomeAadvantageView.
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class CajaHomeAadvantageView extends Caja {

    /** The Constant AADVANTAGE. */
    private static final String AADVANTAGE_TITULO = "AAdvantage®";

    /** The Constant AADVANTAGE_ENCABEZADO. */
    private static final String AADVANTAGE_ENCABEZADO = "AADVANTAGE";

    /** The Constant AADVANTAGE_DESCRIPCION. */
    private static final String AADVANTAGE_DESCRIPCION = "Sumá millas con tus tarjetas de crédito";

    /** The is aadvantage. */
    private Boolean isAadvantage;

    /** The titulo. */
    private String titulo;

    /** The encabezado. */
    private String encabezado;

    /** The descripcion. */
    private String descripcion;

    /**
     * Instantiates a new caja home aadvantage view.
     */
    public CajaHomeAadvantageView() {
        super();
        this.titulo = AADVANTAGE_ENCABEZADO;
        this.encabezado = AADVANTAGE_TITULO;
        this.descripcion = AADVANTAGE_DESCRIPCION;
        this.isAadvantage = true;
    }

    /**
     * Gets the titulo.
     *
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Sets the titulo.
     *
     * @param titulo
     *            the new titulo
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Gets the encabezado.
     *
     * @return the encabezado
     */
    public String getEncabezado() {
        return encabezado;
    }

    /**
     * Sets the encabezado.
     *
     * @param encabezado
     *            the new encabezado
     */
    public void setEncabezado(String encabezado) {
        this.encabezado = encabezado;
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
     * Sets the descripcion.
     *
     * @param descripcion
     *            the new descripcion
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Gets the checks if is aadvantage.
     *
     * @return the checks if is aadvantage
     */
    public Boolean getIsAadvantage() {
        return isAadvantage;
    }

    /**
     * Sets the checks if is aadvantage.
     *
     * @param isAadvantage
     *            the new checks if is aadvantage
     */
    public void setIsAadvantage(Boolean isAadvantage) {
        this.isAadvantage = isAadvantage;
    }

}
