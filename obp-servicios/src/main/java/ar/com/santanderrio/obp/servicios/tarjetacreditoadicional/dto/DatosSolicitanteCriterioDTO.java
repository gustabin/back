/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.dto;

import java.util.List;

import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.web.view.DatosSolicitanteCriterioView;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.web.view.TarjetaCandidataView;

/**
 * The Class DatosSolicitanteCriterioView.
 */
public class DatosSolicitanteCriterioDTO {

    /** The tarjetas candidatas. */
    private List<TarjetaCandidataView> tarjetasCandidatasSeleccionadas;

    /** The doc tipo. */
    private String docTipo;

    /** The doc. */
    private String doc;

    /** The fecha nacimiento. */
    private String fechaNacimiento;

    /** The adicional. */
    private boolean adicional;

    /**
	 * Instantiates a new datos solicitante criterio DTO.
	 */
    public DatosSolicitanteCriterioDTO() {
        super();
    }

    /**
	 * Instantiates a new datos solicitante criterio DTO.
	 *
	 * @param datosSolicitanteCriterioView
	 *            the datos solicitante criterio view
	 */
    public DatosSolicitanteCriterioDTO(DatosSolicitanteCriterioView datosSolicitanteCriterioView) {
        this.docTipo = datosSolicitanteCriterioView.getDocTipo();
        this.doc = datosSolicitanteCriterioView.getDoc();
        this.fechaNacimiento = datosSolicitanteCriterioView.getFechaNacimiento();
    }

    /**
     * Gets the tarjetas candidatas seleccionadas.
     *
     * @return the tarjetasCandidatasSeleccionadas
     */
    public List<TarjetaCandidataView> getTarjetasCandidatasSeleccionadas() {
        return tarjetasCandidatasSeleccionadas;
    }

    /**
     * Sets the tarjetas candidatas seleccionadas.
     *
     * @param tarjetasCandidatasSeleccionadas
     *            the tarjetasCandidatasSeleccionadas to set
     */
    public void setTarjetasCandidatasSeleccionadas(List<TarjetaCandidataView> tarjetasCandidatasSeleccionadas) {
        this.tarjetasCandidatasSeleccionadas = tarjetasCandidatasSeleccionadas;
    }

    /**
     * Gets the doc tipo.
     *
     * @return the docTipo
     */
    public String getDocTipo() {
        return docTipo;
    }

    /**
     * Sets the doc tipo.
     *
     * @param docTipo
     *            the docTipo to set
     */
    public void setDocTipo(String docTipo) {
        this.docTipo = docTipo;
    }

    /**
     * Gets the doc.
     *
     * @return the doc
     */
    public String getDoc() {
        return doc;
    }

    /**
     * Sets the doc.
     *
     * @param doc
     *            the doc to set
     */
    public void setDoc(String doc) {
        this.doc = doc;
    }

    /**
     * Gets the fecha nacimiento.
     *
     * @return the fechaNacimiento
     */
    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * Sets the fecha nacimiento.
     *
     * @param fechaNacimiento
     *            the fechaNacimiento to set
     */
    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * Checks if is adicional.
     *
     * @return the adicional
     */
    public boolean isAdicional() {
        return adicional;
    }

    /**
     * Sets the adicional.
     *
     * @param adicional
     *            the adicional to set
     */
    public void setAdicional(boolean adicional) {
        this.adicional = adicional;
    }

}
