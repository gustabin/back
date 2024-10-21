/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.web.view;

import java.util.ArrayList;
import java.util.List;

import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.dto.DomicilioDTO;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.dto.DomiciliosDTO;

/**
 * The Class DomiciliosView.
 */
public class DomiciliosView {

    /** The domicilios view. */
    private List<DomicilioView> domiciliosView;
    
    /** The es pop up. */
    private boolean esPopUp;

    /**
	 * Instantiates a new domicilios view.
	 */
    public DomiciliosView() {
        super();
    }

    /**
	 * Instantiates a new domicilios view.
	 *
	 * @param domiciliosDTO
	 *            the domicilios DTO
	 */
    public DomiciliosView(DomiciliosDTO domiciliosDTO) {
        this.esPopUp = domiciliosDTO.isEsPopUp();
        this.domiciliosView = new ArrayList<DomicilioView>();
        for (DomicilioDTO domicilioDTO : domiciliosDTO.getDomiciliosDTO()) {
            this.domiciliosView.add(new DomicilioView(domicilioDTO));
        }
    }

    /**
     * Gets the domicilios view.
     *
     * @return the domiciliosView
     */
    public List<DomicilioView> getDomiciliosView() {
        return domiciliosView;
    }

    /**
     * Sets the domicilios view.
     *
     * @param domiciliosView
     *            the domiciliosView to set
     */
    public void setDomiciliosView(List<DomicilioView> domiciliosView) {
        this.domiciliosView = domiciliosView;
    }

    /**
	 * Checks if is es pop up.
	 *
	 * @return true, if is es pop up
	 */
    public boolean isEsPopUp() {
        return esPopUp;
    }

    /**
	 * Sets the es pop up.
	 *
	 * @param esPopUp
	 *            the new es pop up
	 */
    public void setEsPopUp(boolean esPopUp) {
        this.esPopUp = esPopUp;
    }

}
