package ar.com.santanderrio.obp.servicios.ejecutivoselect.dao;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.ejecutivoselect.entities.ConsultaTelefonoOperadorInEntity;
import ar.com.santanderrio.obp.servicios.ejecutivoselect.entities.ConsultaTelefonoOperadorOutEntity;


/**
 * The Interface EjecutivoSelectDAO.
 */
public interface EjecutivoSelectDAO {

	/**
	 * Notificar llamada por tel.
	 *
	 * @param entityIn the entity in
	 * @return the consulta telefono operador out entity
	 * @throws DAOException the DAO exception
	 */
	ConsultaTelefonoOperadorOutEntity notificarLlamadaPorTel(ConsultaTelefonoOperadorInEntity entityIn) throws DAOException;

	

}



