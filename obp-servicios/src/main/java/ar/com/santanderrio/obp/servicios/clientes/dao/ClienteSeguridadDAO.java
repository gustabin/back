package ar.com.santanderrio.obp.servicios.clientes.dao;

import java.math.BigDecimal;
import java.util.List;

public interface ClienteSeguridadDAO {
	
	/**
	 * Obtiene la antiguedad en dias del ultimo:
	 * - Cambio de clave
	 * - Cambio de token
	 * @param nup
	 * @return
	 */
	List<BigDecimal> obtenerAntiguedadDiasUltCambioClaveToken(Long nup);
	
	/**
	 * Actualiza la fecha de la ultima vez
	 * que el cliente hace cambio de clave en el canal
	 * @return
	 */
	Boolean actualizarFechaActualizacionClave(Long nup);
}
