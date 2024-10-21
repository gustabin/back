/**
 * 
 */
package ar.com.santanderrio.obp.base.webservice;

import ar.com.santanderrio.obp.base.dao.DAOException;

// TODO: Auto-generated Javadoc
/**
 * Gestionar la configuracion del ws: 1-Soliciar un Service para consumir las
 * operaciones del ws. 2-Aplicar logica de negocio sobre el service. 3-liberar
 * el recurso solicitado en el punto 1.
 *
 * @author sergio.e.goldentair
 * @param <T>
 *            the generic type
 */
public interface GestionarWS<T> {

	/**
	 * Solicitar un recuso para poder consumir operaciones del ws. Debe recibir
	 * el codigo el cual sera utilizado como prefijo para buscar props en base o
	 * properties file. NO puede faltar la invocacion a {@link liberarPort} si
	 * este metodo es utilizado.
	 *
	 * @return Servicio
	 * @throws DAOException
	 *             the DAO exception
	 */
	T obtenerPort() throws DAOException;

	/**
	 * Este metodo DEBE ser invocado desde un bloque finally para asegurar que
	 * se libere el recurso solicitado.
	 *
	 * @param port
	 *            the port
	 * @throws DAOException
	 *             the DAO exception
	 */
	void liberarPort(T port) throws DAOException;
}
