/*
 * 
 */
package ar.com.santanderrio.obp.servicios.chat.dao;


import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.chat.dto.ConectarInDTO;
import ar.com.santanderrio.obp.servicios.chat.dto.DesconectarInDTO;
import ar.com.santanderrio.obp.servicios.chat.entities.ChatConfigEntity;
import ar.com.santanderrio.obp.servicios.chat.entities.ChatEmailRequestEntity;
import ar.com.santanderrio.obp.servicios.chat.entities.ChatEmailResponseEntity;
import ar.com.santanderrio.obp.servicios.chat.entities.ChatResponseEntity;

// TODO: Auto-generated Javadoc
/**
 * The Interface ChatDAO.
 */
public interface ChatDAO {

	/**
	 * Obtener configuracion.
	 *
	 * @param nup
	 *            the nup
	 * @return the chat config entity
	 * @throws DAOException
	 *             the DAO exception
	 */
	ChatConfigEntity obtenerConfiguracion(String nup) throws DAOException;

	/**
	 * Conectar genesys.
	 *
	 * @param conectarInDTO the conectar in DTO
	 * @return the chat message entity
	 * @throws DAOException the DAO exception
	 */
	ChatResponseEntity conectarGenesys(ConectarInDTO conectarInDTO) throws DAOException;
	
	/**
	 * Desconectar genesys.
	 *
	 * @param desconectarInDto the desconectar in dto
	 * @return the response chat entity
	 * @throws DAOException the DAO exception
	 */
	ChatResponseEntity desconectarGenesys(DesconectarInDTO desconectarInDto) throws DAOException;

	/**
	 * Enviar email genesys.
	 *
	 * @param requestChatEmailEntity the request chat email entity
	 * @return the response chat entity
	 * @throws DAOException the DAO exception
	 */
	ChatEmailResponseEntity enviarEmailGenesys(ChatEmailRequestEntity requestChatEmailEntity) throws DAOException;

}
