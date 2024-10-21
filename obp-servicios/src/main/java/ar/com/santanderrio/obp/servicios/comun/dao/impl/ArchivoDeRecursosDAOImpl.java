/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.dao.impl;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.comun.FilePath;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.types.ExternalPropertyType;
import ar.com.santanderrio.obp.servicios.comun.dao.ArchivoDeRecursosDAO;

/**
 * The Class ArchivoDeRecursosDAOImpl.
 */
@Component
public class ArchivoDeRecursosDAOImpl implements ArchivoDeRecursosDAO {
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ArchivoDeRecursosDAOImpl.class);

	/** The file path. */
	@Autowired
	private FilePath filePath;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comun.dao.ArchivoDeRecursosDAO#
	 * leerArchivo(ar.com.santanderrio.obp.base.types.ExternalPropertyType)
	 */
	public List<String> leerArchivo(ExternalPropertyType externalPropertyType) throws DAOException {
		return leerArchivo(externalPropertyType.getName());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comun.dao.ArchivoDeRecursosDAO#
	 * leerArchivo(ar.com.santanderrio.obp.base.types.ExternalPropertyType)
	 */
	public List<String> leerArchivo(String filename) throws DAOException {
		FileInputStream file = null;
		DataInputStream in = null;
		BufferedReader br = null;

		try {
			LOGGER.info("Se busca el " + filename + " a la ruta dada");
			file = new FileInputStream(filePath.getFilePath() + filename);

			in = new DataInputStream(file);
			br = new BufferedReader(new InputStreamReader(in, "UTF-8"));

			List<String> data = new LinkedList<String>();

			LOGGER.info("Se lee lineas del archivo de texto");
			String lineaTexto = br.readLine();
			while (lineaTexto != null) {
				if (StringUtils.isNotBlank(lineaTexto)) {
					data.add(lineaTexto);
				}
				lineaTexto = br.readLine();
			}

			return data;

		} catch (IOException e) {
			LOGGER.error("Error al acceder al archivo: " + filename, e);
			throw new DAOException(e.getMessage());
		} finally {
			try {
				if (file != null) {
					file.close();
				}

				if (in != null) {
					in.close();
				}

				if (br != null) {
					br.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
}
