/*
 * 
 */
package ar.com.santanderrio.obp.servicios.marcaviajero.bo;

import java.util.List;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.marcaviajero.entities.InfoTarjetas;
import ar.com.santanderrio.obp.servicios.marcaviajero.entities.Tarjeta;
import ar.com.santanderrio.obp.servicios.marcaviajero.entities.TarjetaDelSocio;

/**
 * The Interface DatosTarjetasBO.
 */
public interface DatosTarjetasBO {

	/**
	 * Obtener datos tarjeta.
	 *
	 * @param cliente the cliente
	 * @param tarjetasReenganche the tarjetas reenganche
	 * @param tarjetaWS the tarjeta WS
	 * @return the tarjeta
	 */
	Tarjeta obtenerDatosTarjeta(Cliente cliente, List<InfoTarjetas> tarjetasReenganche,
			ar.com.santanderrio.obp.generated.webservices.marcaviajero.Tarjeta tarjetaWS);

	/**
	 * Preparat info tarjetas.
	 *
	 * @param tarjetas the tarjetas
	 * @return the list
	 */
	List<InfoTarjetas> preparatInfoTarjetas(List<Cuenta> tarjetas);

    /**
	 * Filtrar datos tarjeta.
	 *
	 * @param cliente
	 *            the cliente
	 * @param tarjetas
	 *            the tarjetas
	 * @throws BusinessException
	 *             the business exception
	 */
    void filtrarDatosTarjeta(Cliente cliente, List<TarjetaDelSocio> tarjetas) throws BusinessException;
    
    
}