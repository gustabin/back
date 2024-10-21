package ar.com.santanderrio.obp.servicios.comun.bo;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.comun.dao.entities.ConsultaPadronCuitInEntity;
import ar.com.santanderrio.obp.servicios.comun.dao.entities.PadronOutEntity;

public interface ConsultaPadronCuitBO {
    
    
    PadronOutEntity consultaPadron(ConsultaPadronCuitInEntity entity) throws DAOException;

}
