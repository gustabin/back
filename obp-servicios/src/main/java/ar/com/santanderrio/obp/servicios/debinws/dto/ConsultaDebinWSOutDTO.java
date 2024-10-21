/*
 * 
 */
package ar.com.santanderrio.obp.servicios.debinws.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * ConsultaDebineOutDTO.
 *
 * @author Silvina_Luque
 */
public class ConsultaDebinWSOutDTO {
    
    /** debinesDTO. */
    private List<DebinWSDTO> debinesDTO = new ArrayList<DebinWSDTO>();
    
    /** numero de pagina siguiente. */
    private int siguientePagina;

    /**
	 * Gets the debines DTO.
	 *
	 * @return the debines DTO
	 */
    public List<DebinWSDTO> getDebinesDTO() {
        return debinesDTO;
    }

    /**
	 * Sets the debines DTO.
	 *
	 * @param debinesDTO
	 *            the new debines DTO
	 */
    public void setDebinesDTO(List<DebinWSDTO> debinesDTO) {
        this.debinesDTO = debinesDTO;
    }

    /**
	 * Gets the siguiente pagina.
	 *
	 * @return the siguiente pagina
	 */
    public int getSiguientePagina() {
        return siguientePagina;
    }

    /**
	 * Sets the siguiente pagina.
	 *
	 * @param siguientePagina
	 *            the new siguiente pagina
	 */
    public void setSiguientePagina(int siguientePagina) {
        this.siguientePagina = siguientePagina;
    }
    

    
}
