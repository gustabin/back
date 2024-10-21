/**
 * 
 */
package ar.com.santanderrio.obp.servicios.monitoreo.bo;

/**
 * Gesion de la logica de negocio requerida para poder dar respuesta al estado
 * de la los backend IATX y BD.
 * 
 * @author sergio.e.goldentair
 *
 */
public interface MonitoreoBO {

    /**
	 * Validar base.
	 *
	 * @return true, if successful
	 */
    boolean validarBase();

    /**
	 * Validar iatx.
	 *
	 * @return true, if successful
	 */
    public boolean validarIatx();

}
