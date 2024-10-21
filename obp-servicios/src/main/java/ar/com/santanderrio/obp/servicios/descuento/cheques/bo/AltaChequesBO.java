/*
 * 
 */
package ar.com.santanderrio.obp.servicios.descuento.cheques.bo;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.descuento.cheques.entities.ChequesAltaDTO;
import ar.com.santanderrio.obp.servicios.descuento.cheques.entities.ChequesSimuladosDTO;
import ar.com.santanderrio.obp.servicios.descuento.cheques.entities.DatosCesionDTO;
import ar.com.santanderrio.obp.servicios.descuento.cheques.web.view.AltaChequesViewIn;

/**
 * The Interface AltaChequesBO.
 */
public interface AltaChequesBO {

	/**
	 * Simular alta cheques.
	 *
	 * @param cliente the cliente
	 * @param subProdPaquete the sub prod paquete
	 * @param datoCesion the dato cesion
	 * @param cuentaSeleccionada the cuenta seleccionada
	 * @param cheques the cheques
	 * @return the respuesta
	 */
	Respuesta<ChequesSimuladosDTO> simularAltaCheques(Cliente cliente, String subProdPaquete,
			DatosCesionDTO datoCesion, Cuenta cuentaSeleccionada, AltaChequesViewIn cheques);
	
	/**
	 * Efectuar alta cheques.
	 *
	 * @param cliente the cliente
	 * @param nroTramite the nro tramite
	 * @param nroCuenta the nro cuenta
	 * @return the respuesta
	 */
	Respuesta<ChequesAltaDTO> efectuarAltaCheques(Cliente cliente, String nroTramite, String nroCuenta);

}
