package ar.com.santanderrio.obp.servicios.prestamos.dao;

import java.util.List;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.prestamos.entity.PrestamoNormativoInEntity;
import ar.com.santanderrio.obp.servicios.prestamos.entity.PrestamoNormativoOutEntity;

public interface PrestamoNormativoDAO {
	
	List<PrestamoNormativoOutEntity> consultarNroPrestamoViejo(PrestamoNormativoInEntity inEntity) throws DAOException; 

}
