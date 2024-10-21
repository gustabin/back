/*
 * 
 */
package ar.com.santanderrio.obp.servicios.segmento.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.generated.webservices.segmento.GetClientSegmentDataResponse;
import ar.com.santanderrio.obp.generated.webservices.segmento.GetClientSegmentSelectDataResponse;
import ar.com.santanderrio.obp.generated.webservices.segmento.GetClientSegmentSelectLaborableDataResponse;
import ar.com.santanderrio.obp.generated.webservices.segmento.GetClientSelectSegmentResponse;

/**
 * The Interface SegmentoClienteDAO.
 */
public interface SegmentoClienteDAO {

	/**
	 * Invoca el web service de segmento. <br>
	 * Toma los valores de las propiedades <br>
	 * KEYSTORE.SEGMENTO.PATH = path <br>
	 * KEYSTORE.SEGMENTO.TYPE = JKS <br>
	 * KEYSTORE.SEGMENTO.IDSEGURIDAD = id de la base de datos <br>
	 * KEYSTORE.SEGMENTO.KEY.IDSEGURIDAD = id de la base de datos <br>
	 *
	 * @param nup
	 *            del cliente en sesion
	 * @param channel
	 *            numero del canal - al momento es 04
	 * @param subchannel
	 *            numero del subcanal - al momento es 99
	 * @param user
	 *            usuario de RACF - no es la representacion de la cadena de
	 *            texto que ingreso el cliente sino el que retorno el CICS.
	 * @param password
	 *            password de RACF - no es la representacion de la cadena de
	 *            texto que ingreso el cliente sino el que retorno el CICS.
	 * @return the cliente segmento
	 * @throws DAOException
	 *             the DAO exception
	 */
	GetClientSegmentDataResponse getClienteSegmento(String nup, String channel, String subchannel, String user,
			String password) throws DAOException;

	GetClientSegmentSelectDataResponse getClientSelectSegment(String nup, String channel, String subchannel,
			String user, String password) throws DAOException;
	GetClientSegmentSelectLaborableDataResponse getClientSelectSegmentLaborable(String nup, String channel, String subchannel, String user,
			String password) throws DAOException;
}
