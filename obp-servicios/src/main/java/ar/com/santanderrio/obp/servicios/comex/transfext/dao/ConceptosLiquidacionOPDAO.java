package ar.com.santanderrio.obp.servicios.comex.transfext.dao;

import java.util.List;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.comex.transfext.entities.ConceptoLiquidacionOPEntity;

public interface ConceptosLiquidacionOPDAO {

	/**
	 * Obtiene lista de conceptos liquidacion orden de pago
	 * @return
	 * @throws DAOException
	 */
	List<ConceptoLiquidacionOPEntity> obtenerConceptosLiquidacionOP() throws DAOException;

	/**
	 * Limpia cache de conceptos
	 */
	void limpiarConceptos();

}
