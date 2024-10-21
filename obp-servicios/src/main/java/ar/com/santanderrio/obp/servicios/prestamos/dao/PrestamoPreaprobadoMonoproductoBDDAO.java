package ar.com.santanderrio.obp.servicios.prestamos.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.prestamos.entity.PrestamoPermitidoEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PrestamoPreaprobadoMonoproductoBDDAO {
	
	/**
	 * Consulta las ofertas de prestamos preaprobados para un cliente
	 */
	List<PrestamoPermitidoEntity> consultarPrestamoPreaprobadoMonoproducto(Cliente cliente) throws DAOException;
	
	/**
	 * Consulta el importe maximo de las ofertas por nup del cliente
	 * @param nup
	 * @return
	 * @throws DAOException
	 */
	PrestamoPermitidoEntity consultarValorMaximoOferta(Cliente cliente) throws DAOException;

}
