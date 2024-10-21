package ar.com.santanderrio.obp.servicios.blanqueopin.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.generated.webservices.banelco.WSTarjetaDTO;
import ar.com.santanderrio.obp.generated.webservices.banelco.WSTerminalDTO;
import ar.com.santanderrio.obp.generated.webservices.banelco.WSTerminalData;
import ar.com.santanderrio.obp.generated.webservices.banelco.WSUserData;
import ar.com.santanderrio.obp.servicios.blanqueopin.entities.BlanqueoPinEnum;

public interface BlanqueoPinDAO {

	/**
	 * Blanqueo de pin numerico, alfanumerico y ambos
	 * 
	 * @param terminalData
	 * @param userData
	 * @param tarjetaDTO
	 * @param tipoBlanqueo
	 * @return
	 * @throws DAOException
	 */
	public String blanquearPin(WSTerminalData terminalData, WSUserData userData, WSTarjetaDTO tarjetaDTO,
			BlanqueoPinEnum tipoBlanqueo) throws DAOException;

	/**
	 * Cambiar clase.
	 *
	 * @param terminalDTO the terminal DTO
	 * @param userData the user data
	 * @param tarjetaDTO the tarjeta DTO
	 * @param clase the clase
	 * @param ip the ip
	 * @param userAgent the user agent
	 * @return the string
	 * @throws DAOException the DAO exception
	 */
	public String cambiarClase(WSTerminalDTO terminalDTO, WSUserData userData, WSTarjetaDTO tarjetaDTO, String clase, String ip, String userAgent) throws DAOException;

}
