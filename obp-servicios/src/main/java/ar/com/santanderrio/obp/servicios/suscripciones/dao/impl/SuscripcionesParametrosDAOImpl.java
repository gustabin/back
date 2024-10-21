/*
 * 
 */
package ar.com.santanderrio.obp.servicios.suscripciones.dao.impl;

import java.io.File;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.comun.FilePath;
import ar.com.santanderrio.obp.base.types.ExternalPropertyType;
import ar.com.santanderrio.obp.servicios.suscripciones.dao.SuscripcionesParametrosDAO;
import ar.com.santanderrio.obp.servicios.suscripciones.entities.ParametroDAP;
import ar.com.santanderrio.obp.servicios.suscripciones.entities.ParametroFrecuencia;
import ar.com.santanderrio.obp.servicios.suscripciones.entities.ParametrosSuscripcion;

/**
 * The Class SuscripcionesParametrosDAOImpl.
 */
@Component
public class SuscripcionesParametrosDAOImpl implements SuscripcionesParametrosDAO {

	/** The file path. */
	@Autowired
	private FilePath filePath;

	/** The suscripciones. */
	private static ParametrosSuscripcion suscripciones = new ParametrosSuscripcion();

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(SuscripcionesParametrosDAOImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.suscripciones.dao.
	 * SuscripcionesParametrosDAO#init()
	 */
	@PostConstruct
	@Override
	public void init() {
		try {
			cargarParametros();
		} catch (JAXBException e) {
			LOGGER.error("No se puede cargar el archivo de Parametros de Mya", e);
		}
	}

	/**
	 * Cargar parametros.
	 *
	 * @throws JAXBException
	 *             the JAXB exception
	 */
	private void cargarParametros() throws JAXBException {

		File file = new File(filePath.getFilePath() + ExternalPropertyType.FILE_PARAMETROS_MYA_SUSCRIPCIONES.getName());

		JAXBContext jaxbContext = JAXBContext.newInstance(ParametrosSuscripcion.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		SuscripcionesParametrosDAOImpl.suscripciones = (ParametrosSuscripcion) unmarshaller.unmarshal(file);
		LOGGER.info("Archivo de Parametros cargado, cantidad de objetos {}",
				suscripciones.getListaFrecuencias().size());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.suscripciones.dao.
	 * SuscripcionesParametrosDAO#obtenerFrecuencia(java.lang.String)
	 */
	@Override
	public String obtenerFrecuencia(String codigoFrecuencia) {

		for (ParametroFrecuencia parametroFrecuencia : suscripciones.getListaFrecuencias()) {
			if (codigoFrecuencia.equals(parametroFrecuencia.getCodigoFrecuencia())) {
				return parametroFrecuencia.getDescripFrecuencia();
			}
		}
		return StringUtils.EMPTY;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.suscripciones.dao.
	 * SuscripcionesParametrosDAO#obtenerClave(java.lang.String)
	 */
	@Override
	public String obtenerClave(String frecuencia) {

		for (ParametroFrecuencia parametroFrecuencia : suscripciones.getListaFrecuencias()) {
			if (frecuencia.equals(parametroFrecuencia.getDescripFrecuencia())) {
				return parametroFrecuencia.getCodigoFrecuencia();
			}
		}
		return StringUtils.EMPTY;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.suscripciones.dao.
	 * SuscripcionesParametrosDAO#obtenerDiaAvisoPrevio(java.lang.String)
	 */
	@Override
	public String obtenerDiaAvisoPrevio(String codigoDAP) {

		for (ParametroDAP parametroFrecuencia : suscripciones.getListaDiasAvisoPrevio()) {
			if (parametroFrecuencia.getCodigoDap().trim().equals(codigoDAP)) {
				return parametroFrecuencia.getDescripDap();
			}
		}
		return StringUtils.EMPTY;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.suscripciones.dao.
	 * SuscripcionesParametrosDAO#obtenerCodigoDiaAvisoPrevio(java.lang.String)
	 */
	@Override
	public String obtenerCodigoDiaAvisoPrevio(String codigoDAP) {

		for (ParametroDAP parametroFrecuencia : suscripciones.getListaDiasAvisoPrevio()) {
			if (parametroFrecuencia.getCodigoDap().trim().equals(codigoDAP)) {
				return parametroFrecuencia.getCodigoDap();
			}
		}
		return StringUtils.EMPTY;
	}

}
