/*
 * 
 */
package ar.com.santanderrio.obp.servicios.agenda.destinatarios.bo;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.dto.ConfiguracionAltaDestinatarioCBUDTO;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;

/**
 * Alta destinatario por CBU (Otros bancos).
 *
 * @author federico.n.flores
 */
public interface AltaDestinatarioCbuBO {

	/**
	 * Valida CBU ingresado en pantalla de configuracion alta destinatario por
	 * cbu.
	 *
	 * @param cliente
	 *            the cliente
	 * @param cbu
	 *            the cbu
	 * @param isPeso
	 *            the is peso
	 * @param ip
	 *            the ip
	 * @param userAgent
	 *            the user agent
	 * @return the respuesta
	 */
	Respuesta<ConfiguracionAltaDestinatarioCBUDTO> continuarAltaDestinatarioCBU(Cliente cliente, String cbu,
			Boolean isPeso, String ip, String userAgent);

	/**
	 * Obtiene el objeto cuenta por moneda la moneda ingresada.
	 *
	 * @param cliente
	 *            the cliente
	 * @param isPeso
	 *            the is peso
	 * @return the cuenta
	 */
	Cuenta obtenerCuentaPorMoneda(Cliente cliente, Boolean isPeso);

}
