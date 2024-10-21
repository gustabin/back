/*
 * 
 */
package ar.com.santanderrio.obp.servicios.billetera.dao.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.comun.FilePath;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.types.ExternalPropertyType;
import ar.com.santanderrio.obp.servicios.billetera.dao.ConsultarXmlDAO;

/**
 * The Class ConsultarXmlDAOImpl.
 *
 */
@Component
@Scope("singleton")
public class ConsultarXmlDAOImpl implements ConsultarXmlDAO {

	/** The file path. */
	@Autowired
	private FilePath filePath;

	/** The localidades. */
	private List<EntidadLocalidad> localidades = null;

	/** The nacionalidades. */
	private List<ConsultarXmlDAOImpl.Entidad> nacionalidades = null;

	/** The provincias. */
	private List<ConsultarXmlDAOImpl.Entidad> provincias = null;

	/**
	 * Consultar localidad por id.
	 *
	 * @param idProvCpf
	 *            the id prov cpf
	 * @return the entidad localidad
	 * @throws DAOException
	 *             the DAO exception
	 * @see ar.com.santanderrio.obp.servicios.billetera.dao.ConsultarXmlDAO#consultarLocalidadPorId(java.lang.String)
	 */
	@Override
	public EntidadLocalidad consultarLocalidadPorId(String idProvCpf) throws DAOException {
		if (localidades == null) {
			try {
				localidades = generarLocalidades();
			} catch (FileNotFoundException e) {
				throw new DAOException(e);
			} catch (JAXBException e) {
				throw new DAOException(e);
			} catch (IOException e) {
				throw new DAOException(e);
			}
		}
		return buscarEntidadLocalidad(localidades, idProvCpf);
	}

	/**
	 * Consultar nacionalidad por id.
	 *
	 * @param idCpf
	 *            the id cpf
	 * @return the entidad
	 * @throws DAOException
	 *             the DAO exception
	 * @see ar.com.santanderrio.obp.servicios.billetera.dao.ConsultarXmlDAO#consultarNacionalidadPorId(java.lang.String)
	 */
	@Override
	public Entidad consultarNacionalidadPorId(String idCpf) throws DAOException {
		if (nacionalidades == null) {
			try {
				nacionalidades = generarNacionalidades();
			} catch (FileNotFoundException e) {
				throw new DAOException(e);
			} catch (JAXBException e) {
				throw new DAOException(e);
			} catch (IOException e) {
				throw new DAOException(e);
			}
		}
		return buscarEntidad(nacionalidades, idCpf);
	}

	/**
	 * Consultar provincia por id.
	 *
	 * @param idCpf
	 *            the id cpf
	 * @return the entidad
	 * @throws DAOException
	 *             the DAO exception
	 * @see ar.com.santanderrio.obp.servicios.billetera.dao.ConsultarXmlDAO#consultarProvinciaPorId(java.lang.String)
	 */
	@Override
	public Entidad consultarProvinciaPorId(String idCpf) throws DAOException {
		if (provincias == null) {
			try {
				provincias = generarProvincias();
			} catch (FileNotFoundException e) {
				throw new DAOException(e);
			} catch (JAXBException e) {
				throw new DAOException(e);
			} catch (IOException e) {
				throw new DAOException(e);
			}
		}
		return buscarEntidad(provincias, idCpf);
	}

	/**
	 * Buscar entidad por id.
	 *
	 * @param entidades
	 *            the entidades
	 * @param id
	 *            the id
	 * @return the entidad
	 */
	private Entidad buscarEntidad(List<Entidad> entidades, String id) {
		for (Entidad entidad : entidades) {
			if (entidad.getIdCpf().equals(id)) {
				return entidad;
			}
		}
		return null;
	}

	/**
	 * Buscar entidad localidad por id.
	 *
	 * @param entidades
	 *            the entidades
	 * @param id
	 *            the id
	 * @return the entidad localidad
	 */
	private EntidadLocalidad buscarEntidadLocalidad(List<EntidadLocalidad> entidades, String id) {
		for (EntidadLocalidad entidad : entidades) {
			if (entidad.getIdProvCpf().equals(id)) {
				return entidad;
			}
		}
		return null;
	}

	/**
	 * Generar localidades.
	 *
	 * @return the list
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws JAXBException
	 *             the JAXB exception
	 */
	private List<EntidadLocalidad> generarLocalidades() throws IOException, JAXBException {
		File file = new File(filePath.getFilePath() + ExternalPropertyType.FILE_LOCALIDADES.getName());

		JAXBContext jaxbContext = JAXBContext.newInstance(Localidades.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		Localidades localidadesXml = (Localidades) unmarshaller.unmarshal(file);

		return localidadesXml.getEntidades();
	}

	/**
	 * Generar nacionalidades.
	 *
	 * @return the list
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws JAXBException
	 *             the JAXB exception
	 */
	private List<Entidad> generarNacionalidades() throws IOException, JAXBException {
		File file = new File(filePath.getFilePath() + ExternalPropertyType.FILE_NACIONALIDADES.getName());

		JAXBContext jaxbContext = JAXBContext.newInstance(Nacionalidades.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		Nacionalidades nacionalidadesXml = (Nacionalidades) unmarshaller.unmarshal(file);

		return nacionalidadesXml.getEntidades();
	}

	/**
	 * Generar provincias.
	 *
	 * @return the list
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws JAXBException
	 *             the JAXB exception
	 */
	private List<Entidad> generarProvincias() throws IOException, JAXBException {
		File file = new File(filePath.getFilePath() + ExternalPropertyType.FILE_PROVINCIAS.getName());

		JAXBContext jaxbContext = JAXBContext.newInstance(Provincias.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		Provincias provinciasXml = (Provincias) unmarshaller.unmarshal(file);

		return provinciasXml.getEntidades();
	}

}
