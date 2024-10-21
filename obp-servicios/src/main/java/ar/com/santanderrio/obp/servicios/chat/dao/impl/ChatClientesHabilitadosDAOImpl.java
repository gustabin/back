/**
 * 
 */
package ar.com.santanderrio.obp.servicios.chat.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.chat.dao.ChatClientesHabilitadosDAO;
import ar.com.santanderrio.obp.servicios.comun.dao.ArchivoDeRecursosDAO;

/**
 * The Class ChatClientesHabilitadosDAOImpl.
 *
 * @author Federico_Puente
 */
@Component
public class ChatClientesHabilitadosDAOImpl implements ChatClientesHabilitadosDAO {
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ChatClientesHabilitadosDAOImpl.class);

	/** Lista de clientes habilitados. **/
	private List<String> clienteHabilitados;
	
	/** The ruta archivo. */
	/*  The Ruta Archivo */
	@Value("${CHAT.CLIENTES.HAB.PATH}")
	private String rutaArchivo;
	
	/** The file path. */
	@Autowired
	private ArchivoDeRecursosDAO archivoResource;

	/**
	 * Obtener lista clientes habilitados.
	 *
	 * @return the list
	 * @throws DAOException
	 *             the DAO exception
	 */
	public List<String> obtenerListaClientesHabilitados() throws DAOException {
		if (clienteHabilitados == null) {
			clienteHabilitados = archivoResource.leerArchivo(rutaArchivo);
		}
		
		return clienteHabilitados;
	}

}


