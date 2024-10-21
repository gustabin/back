/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.dto;

import java.util.List;

/**
 * The Class DomiciliosDTO.
 */
public class DomiciliosDTO {

    /** The domicilios DTO. */
    private List<DomicilioDTO> domiciliosDTO;

    /** The es pop up. */
    private boolean esPopUp;

    /**
     * Gets the domicilios DTO.
     *
     * @return the domiciliosDTO
     */
    public List<DomicilioDTO> getDomiciliosDTO() {
        return domiciliosDTO;
    }

    /**
     * Sets the domicilios DTO.
     *
     * @param domiciliosDTO
     *            the domiciliosDTO to set
     */
    public void setDomiciliosDTO(List<DomicilioDTO> domiciliosDTO) {
        this.domiciliosDTO = domiciliosDTO;
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
