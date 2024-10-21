/*
 * 
 */
package ar.com.santanderrio.obp.servicios.terminoscondiciones.dao.impl;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.comun.FilePath;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.types.ExternalPropertyType;
import ar.com.santanderrio.obp.servicios.pagoautomatico.entities.TerminosCondiciones;
import ar.com.santanderrio.obp.servicios.terminoscondiciones.dao.TerminosCondicionesDAO;

/**
 * Implementacion que carga los terminos y condiciones para mandar a vista.
 *
 * @author mariano.g.pulera
 */

@Component
public class TerminosCondicionesDAOImpl implements TerminosCondicionesDAO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(TerminosCondicionesDAOImpl.class);

	/** The archivo. */
	private TerminosCondiciones archivo;

	/** The file path. */
	@Autowired
	private FilePath filePath;

	/**
	 * Cargar terminos condiciones.
	 */
	@PostConstruct
	private void cargarTerminosCondiciones() {

		if (archivo == null) {

			FileInputStream file = null;
			DataInputStream in = null;
			BufferedReader br = null;
			archivo = new TerminosCondiciones();

			try {
				LOGGER.info("Se busca el archivo de terminos y condiciones a la ruta dada");
				file = new FileInputStream(
						filePath.getFilePath() + ExternalPropertyType.FILE_TERMINOS_CONDICIONES.getName());

				in = new DataInputStream(file);
				br = new BufferedReader(new InputStreamReader(in, "UTF-8"));

				LOGGER.info("Se llenan los atributos titulo y cuerpo leyendo del archivo");
				archivo.setTitulo(br.readLine());
				archivo.setCuerpo(br.readLine());

			} catch (IOException e) {
				LOGGER.error("Error al acceder al archivo", e);
			} finally {
				try {
					if (file != null) {
						file.close();
					}
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.terminoscondiciones.dao.
	 * TerminosCondicionesDAO#obtenerTerminosCondiciones()
	 */
	@Override
	public TerminosCondiciones obtenerTerminosCondiciones() throws DAOException {

		if (archivo == null) {
			throw new DAOException("No se puede cargar el archivo");
		}

		return archivo;

	}

}
