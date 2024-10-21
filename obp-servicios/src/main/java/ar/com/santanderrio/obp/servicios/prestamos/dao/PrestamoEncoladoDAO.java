package ar.com.santanderrio.obp.servicios.prestamos.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.prestamos.entity.PrestamoEncoladoEntity;
import ar.com.santanderrio.obp.servicios.prestamos.entity.PrestamosEncoladosEntity;

public interface PrestamoEncoladoDAO {

    /**
     * Obtiene los prestamos encolados de un cliente.
     *
     * @return Prestamos encolados
     */
    PrestamosEncoladosEntity getPrestamosEncolados(String nup) throws DAOException;


    /**
     * Obtiene el prestamo de un cliente por id
     *
     * @return Prestamos encolados
     */
    PrestamoEncoladoEntity getPrestamoEncolado(String id) throws DAOException;

}
