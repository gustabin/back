package ar.com.santanderrio.obp.servicios.campaniabeneficios.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.campaniabeneficios.dto.SuscCampaniaBeneficiosInDTO;
import ar.com.santanderrio.obp.servicios.campaniabeneficios.entities.RepuestaSPSuscCampaniaBeneficiosEntity;

/**
 * The Interface CampaniaBeneficiosDAO.
 */
public interface CampaniaBeneficiosDAO {

    /**
     * Suscribir campania beneficios.
     *
     * @param SuscCampaniaBeneficiosInDTO
     *            inDTO
     * @return the repuesta SP susc campania beneficios entity
     * @throws DAOException
     *             the DAO exception
     */
    RepuestaSPSuscCampaniaBeneficiosEntity suscribirCampaniaBeneficios(SuscCampaniaBeneficiosInDTO inDTO)
            throws DAOException;

}
