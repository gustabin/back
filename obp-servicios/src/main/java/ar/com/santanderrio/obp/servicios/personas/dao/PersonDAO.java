package ar.com.santanderrio.obp.servicios.personas.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.personas.dto.Person;

public interface PersonDAO {
    Person findPersonInfoByCuit( String cuit , String source)throws DAOException;
}
