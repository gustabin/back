package ar.com.santanderrio.obp.servicios.logoutmobile.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.logoutmobile.dao.LogoutMobileDAO;
import ar.com.santanderrio.obp.servicios.logoutmobile.entities.LogoutMobileOutEntity;

@Component
public class LogoutMobileBOImpl implements LogoutMobileBO{

	
	@Autowired
	private LogoutMobileDAO logoutMobileDAO;
	
	private static final String RESPUESTA_ERRONEA = "1";
		
	@Override
	public String consumirServicioLogout(Cliente cliente) throws BusinessException {
		
		try {
			String datosEncriptados = logoutMobileDAO.getDatoEncriptado(cliente);
			LogoutMobileOutEntity respuesta = logoutMobileDAO.logoutMobile(datosEncriptados);
			
			if (RESPUESTA_ERRONEA.equals(respuesta.getRespuesta())) {
				throw new BusinessException();
			}
			
			return respuesta.getMsjCodigo();
		} catch (DAOException e) {
			throw new BusinessException(e);
		}
	}

}
