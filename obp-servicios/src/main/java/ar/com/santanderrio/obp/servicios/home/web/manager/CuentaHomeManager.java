/*
 * 
 */
package ar.com.santanderrio.obp.servicios.home.web.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.home.web.view.SaldosConsolidadosView;

/**
 * Grupo asociado a Cuentas.
 *
 * @author B039543
 */
public interface CuentaHomeManager extends GrupoHomeManager {

	/**
	 * Obtiene los saldos consolidados de todas las cuentas monetarias, no
	 * privadas y no cerradas.
	 *
	 * @return the respuesta
	 */
	Respuesta<SaldosConsolidadosView> obtenerSaldosCuentas();

}
