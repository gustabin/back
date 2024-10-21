/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.dao.impl;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.types.ExternalPropertyType;
import ar.com.santanderrio.obp.servicios.comprobantes.dao.SubEmpresasDAO;
import ar.com.santanderrio.obp.servicios.comun.dao.ArchivoDeRecursosDAO;

/**
 * The Class SubEmpresasDAOImpl.
 */
@Component
public class SubEmpresasDAOImpl implements SubEmpresasDAO {
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(SubEmpresasDAOImpl.class);

	/** Lista de pessubempresas. **/
	private List<String> pesSubempresasList;

	/** The file path. */
	@Autowired
	private ArchivoDeRecursosDAO archivoResource;

	/**
	 * On startup.
	 */
	@PostConstruct
	public void onStartup() {
		try {
			pesSubempresasList = archivoResource.leerArchivo(ExternalPropertyType.FILE_PES_SUBEMPRESAS);
		} catch (DAOException e) {
			LOGGER.error("No se puede cargar el archivo {},", ExternalPropertyType.FILE_PES_SUBEMPRESAS, e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.simuladorprestamo.dao.
	 * DestinoPrestamoDAO#obtener()
	 */
	@Override
	public List<String[]> obtenerSubEmpresas() throws DAOException {
		List<String[]> destinosPrestamo = new LinkedList<String[]>();

		for (String lineaTexto : pesSubempresasList) {
			destinosPrestamo.add(lineaTexto.split("|"));
		}
		return destinosPrestamo;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.dao.SubEmpresasDAO#
	 * obtenerSubEmpresa(java.lang.String)
	 */
	@Override
	public String obtenerSubEmpresa(String empresa) throws DAOException {
		for (String lineaTexto : pesSubempresasList) {
			String[] codigos = lineaTexto.split("\\|");
			boolean estaEnLinea = false;
			for (String codigo : codigos) {
				if (empresa.trim().equals(codigo.trim())) {
					estaEnLinea = true;
				}
			}
			if (estaEnLinea) {
				return codigos[0].trim();
			}
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.dao.SubEmpresasDAO#
	 * cargoPesSubempresasFile()
	 */
	@Override
	public boolean cargoPesSubempresasFile() {
		return pesSubempresasList != null && pesSubempresasList.size() > 0;
	}
}
