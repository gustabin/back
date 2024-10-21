package ar.com.santanderrio.obp.servicios.contratosvigentes.dao;

import java.util.List;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.perfil.entities.ProductoContratoEntity;

public interface ContratosVigentesDAO {

	List<ProductoContratoEntity> obtenerProductosContratos() throws DAOException;
	
}
