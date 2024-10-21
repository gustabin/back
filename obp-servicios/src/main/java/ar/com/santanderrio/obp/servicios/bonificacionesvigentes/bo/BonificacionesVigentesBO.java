package ar.com.santanderrio.obp.servicios.bonificacionesvigentes.bo;

import java.util.List;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.servicios.bonificacionesvigentes.view.BonificacionVigenteView;

public interface BonificacionesVigentesBO {

	List<BonificacionVigenteView> obtenerBonificaciones(String nup) throws BusinessException;
	
}
