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
import ar.com.santanderrio.obp.servicios.descuento.cheques.dao.CodigosBancariosDAO;

/**
 * The Class CodigosBancariosDAOImpl.
 */
@Component
public class CodigosBancariosDAOImpl implements CodigosBancariosDAO{

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(CodigosBancariosDAOImpl.class);

	/** Lista de pessubempresas. **/
	private List<String> codigosBancariosList;
	
	/** The file path. */
	@Autowired
	private ArchivoDeRecursosDAO archivoResource;
	
	/**
	 * On startup.
	 */
	@PostConstruct
	public void onStartup() {
		try {
			codigosBancariosList = archivoResource.leerArchivo(ExternalPropertyType.FILE_CODIGOS_BANCARIOS);
		} catch (DAOException e) {
			LOGGER.error("No se puede cargar el archivo {},", ExternalPropertyType.FILE_CODIGOS_BANCARIOS, e);
		}
	}
	
	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.descuento.cheques.dao.CodigosBancariosDAO#obtenerIndiceCodigoBancario(java.lang.String)
	 */
	@Override
	public int obtenerIndiceCodigoBancario(String bancoGirado){
		String [] codigosBancarios = codigosBancariosList.get(0).split("\\|");
		for(int i = 0; i<codigosBancarios.length;i++){
			if(codigosBancarios[i].equals(bancoGirado)){
				return i;
			}
		}
		return -1;
	}

}
