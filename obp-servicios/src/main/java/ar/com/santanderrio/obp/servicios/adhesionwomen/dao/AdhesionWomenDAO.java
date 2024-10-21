package ar.com.santanderrio.obp.servicios.adhesionwomen.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.adhesionwomen.entities.TarjetaWomenEntity;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;

public interface AdhesionWomenDAO {

	
	TarjetaWomenEntity consultaDatosTarjetas(Cuenta tarjeta) throws DAOException;
	
}
