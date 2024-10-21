/**
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.maps.entity.controles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.santanderrio.obp.servicios.inversiones.maps.exception.ControlMapValidationException;


public class UnknownControl  extends ControlMaps {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(UnknownControl.class);
	
	
    @Override
    public void validate() throws ControlMapValidationException {
    	LOGGER.error("Control Desconocido");
    }

}
