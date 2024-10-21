/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.comprobantes.dao.entity.EmpresasAdheridasDebitoAutoInEntity;
import ar.com.santanderrio.obp.servicios.comprobantes.dao.entity.EmpresasAdheridasDebitoAutoOutEntity;

/**
 * Acceso al servicio para retornar las empresas a las cuales el cliente esta
 * adherido para el débito automático.
 * 
 * @author dante.omar.olmedo
 *
 */
public interface ConsultaAdhesionDebitoAutomaticoCuentaDAO {
	/**
	 * Realiza la consulta llamando al servicio de IATX correspondiente.
	 *
	 * @param entity the entity
	 * @return the consulta comprobante out entity
	 * @throws DAOException the DAO exception
	 */
	EmpresasAdheridasDebitoAutoOutEntity consultar(EmpresasAdheridasDebitoAutoInEntity entity) throws DAOException;
}
