package ar.com.santanderrio.obp.servicios.bonificacionesvigentes.dao;

import java.util.LinkedHashMap;
import java.util.List;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.bonificacionesvigentes.dto.AccountDto;
import ar.com.santanderrio.obp.servicios.bonificacionesvigentes.dto.BonificacionDto;
import ar.com.santanderrio.obp.servicios.bonificacionesvigentes.dto.ListPromocionDto;
import ar.com.santanderrio.obp.servicios.bonificacionesvigentes.dto.ProductoClienteEntity;

public interface BonificacionesVigentesDAO {

	
	List<LinkedHashMap<Object, Object>> obtenerBonificaciones(String nup) throws DAOException;
	
	AccountDto obtenerToken() throws DAOException;

	ProductoClienteEntity obtenerProductoBonificado(String codigoProducto, String codigoSubproducto) throws DAOException;

	List<LinkedHashMap<Object, Object>> obtenerBonificacionesCajaSeguridad(String nup) throws DAOException;
	
	ListPromocionDto obtenerPromociones(String nup) throws DAOException;
	
	List<BonificacionDto> obtenerBonificacionesSeguros(String nup)throws DAOException;
	
}
