package ar.com.santanderrio.obp.servicios.echeq.dao;

import java.util.List;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.generated.webservices.echeq.ResponseFull;
import ar.com.santanderrio.obp.servicios.echeq.entities.BeneficiarioInEntity;
import ar.com.santanderrio.obp.servicios.echeq.entities.ConsultaImporteTotalesInEntity;
import ar.com.santanderrio.obp.servicios.echeq.entities.DetalleECheqInEntity;
import ar.com.santanderrio.obp.servicios.echeq.entities.ECheqEmitidoInEntity;
import ar.com.santanderrio.obp.servicios.echeq.entities.ECheqEndosadoInEntity;
import ar.com.santanderrio.obp.servicios.echeq.entities.ECheqRecibidoInEntity;
import ar.com.santanderrio.obp.servicios.echeq.entities.EcheqCedidoInEntity;
import ar.com.santanderrio.obp.servicios.echeq.entities.RetrieveMarketInfrastructureInOutEntity;

/**
 * The Interface ECheqAmcoDAO.
 */
public interface ECheqAmcoDAO {

	/**
	 * Obtener cheques emitidos.
	 *
	 * @param eCheqEmitidoEntity the e cheq emitido entity
	 * @return the list
	 * @throws DAOException the DAO exception
	 */
	List<ResponseFull> obtenerChequesEmitidos(ECheqEmitidoInEntity eCheqEmitidoEntity) throws DAOException;

	/**
	 * Obtener cheques recibidos.
	 *
	 * @param ECheqRecibidoInEntity the e cheq recibido in entity
	 * @return the list
	 * @throws DAOException the DAO exception
	 */
	List<ResponseFull> obtenerChequesRecibidos(ECheqRecibidoInEntity ECheqRecibidoInEntity) throws DAOException;

	/**
	 * Obtener cheques endosados.
	 *
	 * @param eCheqEndosadoInEntity the e cheq endosado in entity
	 * @return the list
	 * @throws DAOException the DAO exception
	 */
	List<ResponseFull> obtenerChequesEndosados(ECheqEndosadoInEntity eCheqEndosadoInEntity) throws DAOException;

	/**
	 * Obtener cheques cedidos
	 * @param eCheqCedidosInEntity
	 * @return
	 * @throws DAOException
	 */
	List<ResponseFull> obtenerChequesCedidos(EcheqCedidoInEntity eCheqCedidosInEntity) throws DAOException;
	
	/**
	 * Obtener cheques en proceso. 
	 * Son los emitidos erroneos y los que se encuentran en proceso
	 *
	 * @param cuit
	 * @return the list
	 * @throws DAOException the DAO exception
	 */
	List<ResponseFull> obtenerChequesEnProceso(String cuit) throws DAOException;
	
	/**
	 * Verificar cliente bancario.
	 *
	 * @param beneficiarioInEntity the beneficiario in entity
	 * @return the list
	 * @throws DAOException the DAO exception
	 */
	List<ResponseFull> verificarClienteBancario(BeneficiarioInEntity beneficiarioInEntity) throws DAOException;

	/**
	 * Consulta importe totales.
	 *
	 * @param consultaImporteTotalesInEntity the consulta importe totales in entity
	 * @return the list
	 * @throws DAOException the DAO exception
	 */
	List<ResponseFull> consultaImporteTotales(ConsultaImporteTotalesInEntity consultaImporteTotalesInEntity) throws DAOException;

	/**
	 * Obtener detalle.
	 *
	 * @param detalleECheqInEntity the detalle E cheq in entity
	 * @return the list
	 * @throws DAOException the DAO exception
	 */
	List<ResponseFull> obtenerDetalle(DetalleECheqInEntity detalleECheqInEntity) throws DAOException;
	
	/**
	 * Devuelve entidades habilitadas para hacerles un endoso por NEG
	 * @param RetrieveMarketInfrastructureInOutEntity
	 * @return RetrieveMarketInfrastructureInOutEntity
	 * @throws DAOException 
	 */
	List<ResponseFull> obtenerListaEntidadesHabilitadas(String jsessionid) throws DAOException;
}
