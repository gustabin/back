/**
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.dao.impl;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.comun.FilePath;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.types.ExternalPropertyType;
import ar.com.santanderrio.obp.servicios.pagos.dao.FiltroAgendaDAO;
import ar.com.santanderrio.obp.servicios.pagos.entities.FiltroAgenda;

/**
 * The Class FiltroAgendaDAOImpl.
 *
 * @author B039636
 */
@Component
public class FiltroAgendaDAOImpl implements FiltroAgendaDAO {

	/** The Constant MAXFILECOLUMNS. */
	private static final int MAXFILECOLUMNS = 2;

	/** The Constant logger. */
	private static final Logger LOGGER = LoggerFactory.getLogger(FiltroAgendaDAOImpl.class);

	/** The Constant PIPE. */
	private static final String PIPE = "|";

	/** The file path. */
	@Autowired
	private FilePath filePath;

	/** The filtros agenda. */
	private static Map<String, FiltroAgenda> filtrosAgenda = new HashMap<String, FiltroAgenda>();

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.pagos.dao.FiltroAgendaDAO#load()
	 */
	@PostConstruct
	public void load() {
		try {
			loadFiltroAgenda();
		} catch (DAOException e) {
			LOGGER.error("Error al levantar el archivo", e);
		}
	}

	/**
	 * Existe empresa.
	 *
	 * @param cuit
	 *            the cuit
	 * @param nombreEmpresa
	 *            the nombre empresa
	 * @return true/false dependiendo si la empresa enviada por parametro existe
	 *         o no en el archivo consultado
	 * @throws DAOException
	 *             the DAO exception
	 */
	@Override
	public boolean existeEmpresa(String cuit, String nombreEmpresa) throws DAOException {
		if (cuit == null) {
			loadFiltroAgenda();
		}
		FiltroAgenda filtroAgenda = filtrosAgenda.get(cuit + nombreEmpresa.trim());
		if (filtroAgenda == null) {
			return false;
		}
		return true;
	}

	/**
	 * Load filtro agenda.
	 *
	 * @throws DAOException
	 *             the DAO exception
	 */
	private void loadFiltroAgenda() throws DAOException {
		FiltroAgendaDAOImpl.filtrosAgenda.clear();
		FileInputStream file = null;
		DataInputStream in = null;
		BufferedReader br = null;
		try {
			file = new FileInputStream(
					new File(filePath.getFilePath() + ExternalPropertyType.FILE_FILTRO_AGENDA.getName()));
			in = new DataInputStream(file);
			Reader reader = new InputStreamReader(in, Charset.defaultCharset());
			br = new BufferedReader(reader);
			String strLine;
			while ((strLine = br.readLine()) != null) {
				FiltroAgenda filtro = getFiltro(strLine);
				filtrosAgenda.put(filtro.getCuit() + filtro.getDescripcion().trim(), filtro);
			}
		} catch (FileNotFoundException e) {
			LOGGER.error("No se encuentra el archivo", e);
			throw new DAOException(e);
		} catch (IOException e) {
			LOGGER.error("IOException", e);
			throw new DAOException(e);
		} finally {
			try {
				if (br != null) {
					br.close();
				}
				if (in != null) {
					in.close();
				}
				if (file != null) {
					file.close();
				}

			} catch (IOException e) {
				LOGGER.error("IOException", e);
			}
		}
	}

	/**
	 * Gets the filtro.
	 *
	 * @param strLine
	 *            the str line
	 * @return the filtro
	 */
	private static FiltroAgenda getFiltro(String strLine) {
		FiltroAgenda filtro = new FiltroAgenda();
		try {
			String[] temp = StringUtils.split(strLine, PIPE);
			for (int i = 0; i <= MAXFILECOLUMNS; i++) {
				switch (i) {
				case 0:
					filtro.setCuit(temp[i]);
					break;
				case 1:
					filtro.setDescripcion(temp[i]);
					break;
				default:
					break;
				}
			}
		} catch (Exception e) {
			LOGGER.error("Exception", e);
		}
		return filtro;
	}
}
