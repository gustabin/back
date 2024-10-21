package ar.com.santanderrio.obp.servicios.obp.factory;

import javax.validation.constraints.NotNull;

/**
 * The Class AdhesionDebitoAutomaticoView.
 *
 * @author marcelo.ruiz
 */
public class AdhesionDebitoAutomaticoView {

    /** The fiid. */
    @NotNull(message = "${VALIDATION.DEBITOAUTOMATICO.FIID}${validatedValue}")
    private String fiid;

    /**
     * Gets the fiid.
     *
     * @return the fiid
     */
    public String getFiid() {
        return fiid;
    }

    /**
     * Sets the fiid.
     *
     * @param fiid
     *            the new fiid
     */
    public void setFiid(String fiid) {
        this.fiid = fiid;
    }

}
