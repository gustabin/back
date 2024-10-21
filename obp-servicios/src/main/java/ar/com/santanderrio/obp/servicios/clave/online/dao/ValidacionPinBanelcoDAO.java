package ar.com.santanderrio.obp.servicios.clave.online.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.clave.online.entities.DatosAutenticacionInEntity;
import ar.com.santanderrio.obp.servicios.clave.online.entities.SeleccionTarjetaBanelcoInEntity;

public interface ValidacionPinBanelcoDAO {

	String obtenerTarjetaParaValidarPin(SeleccionTarjetaBanelcoInEntity seleccionTarjetaIn) throws DAOException;

	String validarPinBanelco(SeleccionTarjetaBanelcoInEntity seleccionTarjetaIn, DatosAutenticacionInEntity datosAutenticacionInEntity) throws DAOException;
	
}
