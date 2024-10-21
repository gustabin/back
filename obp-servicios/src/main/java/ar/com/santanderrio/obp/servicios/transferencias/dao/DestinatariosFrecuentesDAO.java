package ar.com.santanderrio.obp.servicios.transferencias.dao;

import java.util.List;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.transferencias.entities.DestinatariosFrecuentesEntity;

public interface DestinatariosFrecuentesDAO {

	public List<DestinatariosFrecuentesEntity> obtenerDestinatariosFrecuentes(String nup) throws DAOException;

	public String altaDestinatarioFavorito(DestinatariosFrecuentesEntity destinatario) throws DAOException;

	public Boolean bajaDestinatarioFavorito(String id) throws DAOException;

}
