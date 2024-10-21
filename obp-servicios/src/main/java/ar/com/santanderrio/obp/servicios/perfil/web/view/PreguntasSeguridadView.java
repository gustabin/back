/*
 * 
 */
package ar.com.santanderrio.obp.servicios.perfil.web.view;

import java.util.List;

import ar.com.santanderrio.obp.base.comun.OperacionesRSAEnum;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.RsaDTOParaDesafio;

/**
 * The Class PreguntasSeguridadView.
 *
 * @author Silvina_Luque
 */
@SuppressWarnings("serial")
public class PreguntasSeguridadView extends RsaDTOParaDesafio {

    /** flag de aviso si ya tiene preguntas cargadas. */
    private boolean tienePreguntasCargadas;

    /** The lista preguntas. */
    private List<PreguntaSeguridadView> listaPreguntas;

    /** Mensaje resultado. */
    private String mensajeFeedback;

    /** The mensaje contextual. */
    private String mensajeContextual;

    /** The permite reintentar. */
    private boolean permiteReintentar = true;

    /**
     * Instantiates a new preguntas seguridad view.
     */
    public PreguntasSeguridadView() {
        super(OperacionesRSAEnum.PREGUNTASSEGURIDAD);
    }

    /**
     * Checks if is tiene preguntas cargadas.
     *
     * @return true, if is tiene preguntas cargadas
     */
    public boolean isTienePreguntasCargadas() {
        return tienePreguntasCargadas;
    }

    /**
     * Sets the tiene preguntas cargadas.
     *
     * @param tienePreguntasCargadas
     *            the new tiene preguntas cargadas
     */
    public void setTienePreguntasCargadas(boolean tienePreguntasCargadas) {
        this.tienePreguntasCargadas = tienePreguntasCargadas;
    }

    /**
     * Gets the lista preguntas.
     *
     * @return the lista preguntas
     */
    public List<PreguntaSeguridadView> getListaPreguntas() {
        return listaPreguntas;
    }

    /**
     * Sets the lista preguntas.
     *
     * @param listaPreguntas
     *            the new lista preguntas
     */
    public void setListaPreguntas(List<PreguntaSeguridadView> listaPreguntas) {
        this.listaPreguntas = listaPreguntas;
    }

    /**
     * Gets the mensaje feedback.
     *
     * @return the mensaje feedback
     */
    public String getMensajeFeedback() {
        return mensajeFeedback;
    }

    /**
     * Sets the mensaje feedback.
     *
     * @param mensajeFeedback
     *            the new mensaje feedback
     */
    public void setMensajeFeedback(String mensajeFeedback) {
        this.mensajeFeedback = mensajeFeedback;
    }

    /**
     * Checks if is permite reintentar.
     *
     * @return true, if is permite reintentar
     */
    public boolean isPermiteReintentar() {
        return permiteReintentar;
    }

    /**
     * Sets the permite reintentar.
     *
     * @param permiteReintentar
     *            the new permite reintentar
     */
    public void setPermiteReintentar(boolean permiteReintentar) {
        this.permiteReintentar = permiteReintentar;
    }

    /**
     * Gets the mensaje contextual.
     *
     * @return the mensaje contextual
     */
    public String getMensajeContextual() {
        return mensajeContextual;
    }

    /**
     * Sets the mensaje contextual.
     *
     * @param mensajeContextual
     *            the new mensaje contextual
     */
    public void setMensajeContextual(String mensajeContextual) {
        this.mensajeContextual = mensajeContextual;
    }

}
