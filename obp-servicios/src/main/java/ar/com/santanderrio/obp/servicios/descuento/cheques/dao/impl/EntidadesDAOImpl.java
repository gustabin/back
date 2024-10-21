/*
 * 
 */
package ar.com.santanderrio.obp.servicios.descuento.cheques.dao.impl;

import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.types.ExternalPropertyType;
import ar.com.santanderrio.obp.servicios.comun.dao.ArchivoDeRecursosDAO;
import ar.com.santanderrio.obp.servicios.descuento.cheques.dao.EntidadesDAO;

/**
 * The Class EntidadesDAOImpl.
 */
@Component
public class EntidadesDAOImpl implements EntidadesDAO{

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(EntidadesDAOImpl.class);

	/** Lista de pessubempresas. **/
	private List<String> entidadesList;
	
	/** The file path. */
	@Autowired
	private ArchivoDeRecursosDAO archivoResource;
	
	/**
	 * On startup.
	 */
	@PostConstruct
	public void onStartup() {
		try {
			entidadesList = archivoResource.leerArchivo(ExternalPropertyType.FILE_ENTIDADES);
		} catch (DAOException e) {
			LOGGER.error("No se puede cargar el archivo {},", ExternalPropertyType.FILE_ENTIDADES, e);
		}
	}
	
	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.descuento.cheques.dao.EntidadesDAO#obtenerIndiceCodigoBancario(int)
	 */
	@Override
	public String obtenerIndiceCodigoBancario(int indice) {
		String [] codigosBancarios = entidadesList.get(0).split("\\|");
		if(codigosBancarios.length >= indice){
			return codigosBancarios[indice];
		}
		return null;
	}

}
