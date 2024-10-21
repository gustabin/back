package ar.com.santanderrio.obp.servicios.campaniabeneficios.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.campaniabeneficios.dto.SuscCampaniaBeneficiosInDTO;
import ar.com.santanderrio.obp.servicios.campaniabeneficios.entities.RepuestaSPSuscCampaniaBeneficiosEntity;

/**
 * The Interface ConsultaCampaniaBeneficiosDAO.
 */
public interface ConsultaCampaniaBeneficiosDAO {

    /**
     * Suscribir campania beneficios.
     *
     * @param SuscCampaniaBeneficiosInDTO
     *            inDTO
     * @return the repuesta SP susc campania beneficios entity
     * @throws DAOException
     *             the DAO exception
     */
    RepuestaSPSuscCampaniaBeneficiosEntity consultaCampaniaBeneficiosDAO(SuscCampaniaBeneficiosInDTO inDTO)
            throws DAOException;

}
