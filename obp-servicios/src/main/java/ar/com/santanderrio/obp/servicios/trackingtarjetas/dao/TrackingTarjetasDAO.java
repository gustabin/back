/**
 * 
 */
package ar.com.santanderrio.obp.servicios.trackingtarjetas.dao;


import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.generated.webservices.solicitudes.andreani.TrackingTarjetasIn;
import ar.com.santanderrio.obp.generated.webservices.solicitudes.andreani.TrackingTarjetasOut;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.trackingtarjetas.entities.ConsultaTarjetasMonederoOutEntity;


/**
 * The Interface TrackingTarjetasDAO.
 *
 * @author julian.ariel.karp
 */
public interface TrackingTarjetasDAO {
    
    /**
     * Consultar las trazas de Andreani.
     * @param inRequest the in request
     * @return the tracking tarjetas out
     * @throws DAOException the DAO exception
     */
    TrackingTarjetasOut consultarTraza(TrackingTarjetasIn inRequest) throws DAOException;

    /**
	 * getDatosTarjetaMonedero.
	 *
	 * @param cliente
	 *            the cliente
	 * @param tipoConsultaMonedero
	 *            the tipo consulta monedero
	 * @return the datos tarjeta monedero
	 * @throws DAOException
	 *             the DAO exception
	 */
    ConsultaTarjetasMonederoOutEntity getDatosTarjetaMonedero(Cliente cliente, String tipoConsultaMonedero)  throws DAOException;
}
