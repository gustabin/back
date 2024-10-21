/**
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.bo;

import java.util.List;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;
import ar.com.santanderrio.obp.servicios.tarjetas.bo.impl.TarjetaBOUtilsException;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.DisponiblesYConsumoDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ResumenTarjetaDTO;

/**
 * The Interface SelectorYCabeceraBO.
 *
 */
public interface SelectorYCabeceraBO {

	/**
	 * Obtiene la tarjetas.
	 *
	 * @param cliente
	 *            the cliente
	 * @return Respuesta<DisponiblesYConsumo>
	 */
	Respuesta<DisponiblesYConsumoDTO> obtenerTarjetas(Cliente cliente);

	/**
	 * Obtener tooltip favorito.
	 *
	 * @return the string
	 */
	String obtenerTooltipFavorito();

	/**
	 * Obtener tooltip no favorito.
	 *
	 * @return the string
	 */
	String obtenerTooltipNoFavorito();

	/**
	 * Obtener marca de tarjeta.
	 *
	 * @param identificacionCuenta
	 *            the identificacion cuenta
	 * @param cliente
	 *            the cliente
	 * @return the string
	 * @throws BusinessException
	 *             the business exception
	 */
	String obtenerMarcaDeTarjeta(IdentificacionCuenta identificacionCuenta, Cliente cliente) throws BusinessException;

	List<ResumenTarjetaDTO> generarListaResumenDTO(List<Cuenta> productosVisa, List<Cuenta> productosAmex,
            List<Cuenta> productosMasterCard) throws TarjetaBOUtilsException, BusinessException;
	
}
