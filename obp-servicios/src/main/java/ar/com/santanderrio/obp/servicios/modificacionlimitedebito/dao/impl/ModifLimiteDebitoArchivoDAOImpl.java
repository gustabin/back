/*
 * 
 */
package ar.com.santanderrio.obp.servicios.modificacionlimitedebito.dao.impl;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.comun.FilePath;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.types.ExternalPropertyType;
import ar.com.santanderrio.obp.servicios.modificacionlimitedebito.dao.ModifLimiteDebitoArchivoDAO;
import ar.com.santanderrio.obp.servicios.modificacionlimitedebito.entities.LimiteClase;
import ar.com.santanderrio.obp.servicios.modificacionlimitedebito.entities.LimiteDebito;

/**
 * The Class ModifLimiteDebitoArchivoDAOImpl.
 */
@Component
public class ModifLimiteDebitoArchivoDAOImpl implements ModifLimiteDebitoArchivoDAO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ModifLimiteDebitoArchivoDAOImpl.class);

	/** The Constant CLASES_BANELCO. */
	private static final String CLASES_BANELCO = "CLASE.BANELCO";

	/** The Constant LIMITE_COMPRA. */
	private static final String LIMITE_COMPRA = "LIMITE.COMPRA";

	/** The Constant LIMITE_EXTRACCION. */
	private static final String LIMITE_EXTRACCION = "LIMITE.EXTRACCION";

	/** The Constant PIPE. */
	private static final String PIPE = "|";

	/** The file path. */
	@Autowired
	private FilePath filePath;

	/**
	 * Lee el archivo seteando montos de limites con sus repectivas clases por
	 * cada tipo de operacion.
	 *
	 * @return lista de operaciones con los pares de monto/clase asociados a
	 *         cada una de las mismas
	 * @throws DAOException
	 *             the DAO exception
	 */
	@Override
	public List<LimiteDebito> cargarLimitesDebito() throws DAOException {

		List<LimiteDebito> limites = leerArchivoSeparandoLimitesPorOperacion();
		LimiteDebito clasesBanelco = obtenerClasesBanelco(limites);
		limites = establecerClasePorMonto(limites, clasesBanelco);

		return limites;
	}

	/**
	 * Lee el archivo clasesbanelco.txt
	 *
	 * @return lista de montos de limites asociados a cada operacion leida del
	 *         archivo
	 * @throws DAOException
	 *             the DAO exception
	 */
	private List<LimiteDebito> leerArchivoSeparandoLimitesPorOperacion() throws DAOException {

		FileInputStream file = null;
		DataInputStream in = null;
		BufferedReader br = null;
		List<LimiteDebito> limites = new ArrayList<LimiteDebito>();

		try {
			String location = filePath.getFilePath() + ExternalPropertyType.FILE_CLASES_BANELCO.getName();
			LOGGER.info("Ubicacion del archivo de clases banelco {}", location);
			file = new FileInputStream(new File(location));
			in = new DataInputStream(file);
			Reader reader = new InputStreamReader(in, Charset.defaultCharset());
			br = new BufferedReader(reader);
			String textLine;

			// lee y separa las lineas del archivo en tipo de operacion y sus
			// limites asociados
			while ((textLine = br.readLine()) != null) {
				if (!textLine.contains("#") && textLine.length() > 0 && textLine.indexOf('=') != -1) {
					LimiteDebito limiteDebito = getLimiteLine(textLine);
					limites.add(limiteDebito);
				}
			}
			return limites;
		} catch (FileNotFoundException e) {
			LOGGER.error("No se encuentra el archivo - cargarLimitesDebito", e);
			throw new DAOException(e);
		} catch (IOException e) {
			LOGGER.error("IOException - cargarLimitesDebito", e);
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
				LOGGER.error("IOException - cargarLimitesDebito", e);
			}
		}
	}

	/**
	 * Separa y setea los montos asociados a cada tipo de operacion.
	 *
	 * @param strLine
	 *            linea leida del archivo
	 * @return objeto que contiene el tipo de operacion y los montos de limites
	 *         asociados
	 */
	private LimiteDebito getLimiteLine(String strLine) {
		LimiteDebito limite = new LimiteDebito();
		String[] temp = StringUtils.split(strLine, PIPE);
		limite.setClave(temp[0].substring(0, temp[0].indexOf('=')).trim());
		limite.setValor(new ArrayList<LimiteClase>());
		temp[0] = temp[0].replaceAll("[^0-9]", "");

		for (int i = 0; i < temp.length; i++) {
			LimiteClase lc = new LimiteClase(temp[i]);
			limite.getValores().add(i, lc);
		}

		return limite;
	}

	/**
	 * Sets the indice porlimite.
	 *
	 * @param limites
	 *            the limites
	 * @param clasesBanelco
	 *            the clases banelco
	 * @return the list
	 */
	private List<LimiteDebito> establecerClasePorMonto(List<LimiteDebito> limites, LimiteDebito clasesBanelco) {

		for (LimiteDebito limite : limites) {
			String operacion = limite.getClave();
			if (operacion.equalsIgnoreCase(LIMITE_EXTRACCION) || operacion.equalsIgnoreCase(LIMITE_COMPRA)) {
				for (int i = 0; i < clasesBanelco.getValores().size(); i++) {
					limite.getValores().get(i).setClase(clasesBanelco.getValores().get(i).getLimite());
				}
			}
		}
		return limites;
	}

	/**
	 * Obtiene linea del archivo que contiene las clases banelco.
	 *
	 * @param limites
	 *            the limites
	 * @return clasesBanelco
	 */
	private LimiteDebito obtenerClasesBanelco(List<LimiteDebito> limites) {
		LimiteDebito clasesBanelco = new LimiteDebito();

		for (LimiteDebito limite : limites) {
			if (limite.getClave().equalsIgnoreCase(CLASES_BANELCO)) {
				clasesBanelco = limite;
				break;
			}
		}
		return clasesBanelco;
	}

}
