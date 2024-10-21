package ar.com.santanderrio.obp.servicios.getnet.dao;

import java.io.IOException;
import java.util.List;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.exceptions.ISBANRuntimeException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.getnet.dto.GetnetAdhesionDTO;
import ar.com.santanderrio.obp.servicios.getnet.entities.ActualizacionConsultaMarcaAdhesionFinal;
import ar.com.santanderrio.obp.servicios.getnet.entities.ConsultaSitImpositivaOutEntity;
import ar.com.santanderrio.obp.servicios.getnet.entities.GetnetInEntity;
import ar.com.santanderrio.obp.servicios.getnet.entities.GetnetOutEntity;
import ar.com.santanderrio.obp.servicios.getnet.exception.GetnetEmailValidationException;
import ar.com.santanderrio.obp.servicios.getnet.exception.GetnetValidationException;

/**
 * The Interface GetnetDAO.
 */
public interface GetnetDAO {

	/**
     * Chequea si el nup esta habilitado.
     * @param nup
	 *            the nup
     * @return the getnet out entity
     */
	GetnetOutEntity check(String nup) throws DAOException;
	
	/**
     * Recupera el estado del cliente.
     * @param nup
	 *            the nup
     * @return the list getnet out entity
     */
	List<GetnetOutEntity> getPersons(String nup) throws DAOException;
	
	/**
     * Consulta situacion impositiva del cliente.
     * @param cliente
	 *            the nup
     * @return the consulta sit impositiva out entity
     * @throws DAOException
	 * @throws IatxException 
     */
	ConsultaSitImpositivaOutEntity consultarSitImpositiva(Cliente cliente) throws DAOException, IatxException;

	ActualizacionConsultaMarcaAdhesionFinal ActualizacionConsultaMarcaAdhesion(Cliente cliente, Boolean esConsultaExpPoliticamente) 
			throws DAOException, IatxException;
	
	/**
     * Confirma la adhesion del cliente.
     * @param inEntity
	 *            the entity
	 * @param nroCuentaDestino 
	 * @throws DAOException
	 * @throws ISBANRuntimeException
     */
	void postPersons(GetnetInEntity inEntity, String nroCuentaDestino) throws DAOException, ISBANRuntimeException, IOException, GetnetValidationException, GetnetEmailValidationException;
	
	/**
     * Descarga comprobante adhesion
     * @param dto
     * 			the dto
     * @return the reporte
     */
    Reporte descargaComprobanteAdhesion(GetnetAdhesionDTO dto);
    
}
