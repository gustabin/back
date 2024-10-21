/**
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.sucursales.dao.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.codec.CharEncoding;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.comun.FilePath;
import ar.com.santanderrio.obp.base.comun.JaxbUtils;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.types.ExternalPropertyType;
import ar.com.santanderrio.obp.servicios.comun.sucursales.dao.ConsultarSucursalesDAO;
import ar.com.santanderrio.obp.servicios.comun.sucursales.entities.Entidad;
import ar.com.santanderrio.obp.servicios.comun.sucursales.entities.Sucursal;
import ar.com.santanderrio.obp.servicios.comun.sucursales.entities.Sucursales;

/**
 * The Class ConsultarSucursalesDAOImpl.
 *
 * @author B039542
 */
@Component
public class ConsultarSucursalesDAOImpl implements ConsultarSucursalesDAO {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConsultarSucursalesDAOImpl.class);

    /**
     * Entrada en el archivo de propiedades para obtener el nombre del archivo de
     * sucursales.
     */
    private static final String SUCURSALES_FILE_NAME = "SUCURSALES_FILE_NAME";

    /** The entidades. */
    private List<Entidad> entidades = null;

    /** The file path. */
    @Autowired
    private FilePath filePath;

    /** The environment. */
    @Autowired
    private Environment environment;

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.comun.sucursales.dao.ConsultarSucursalesDAO#
     * consultarSucursalPorId(java.lang.String)
     */
    @Override
    public Sucursal consultarSucursalPorId(String id) throws DAOException {
        if (entidades == null) {
            try {
                entidades = generarEntidades();
            } catch (JAXBException e) {
                LOGGER.error("No se pudieron cargar las sucursales desde el xml.", e);
                throw new DAOException(e);
            }
        }

        return buscarSucursal(id);
    }

    /**
     * Buscar sucursal en la lista cargada desde el xml.
     *
     * @param id
     *            the id
     * @return the sucursal
     */
    private Sucursal buscarSucursal(final String id) {
        for (Entidad entidad : entidades) {
            List<Sucursal> sucursales = entidad.getSucursales();
            for (Sucursal sucursal : sucursales) {
                if (StringUtils.isNotBlank(sucursal.getIdSucursal()) && sucursal.getIdSucursal().equals(id)) {
                    return sucursal;
                }
            }
        }
        return null;
    }

    /**
     * Generar entidades.
     *
     * @return the list
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     * @throws JAXBException
     *             the JAXB exception
     */
    private List<Entidad> generarEntidades() throws JAXBException {
        File file = new File(filePath.getFilePath() + getSucuFileName());

        JAXBContext jaxbContext = JAXBContext.newInstance(Sucursales.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        Sucursales sucursales = (Sucursales) unmarshaller.unmarshal(file);

        return sucursales.getSucursales();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.comun.sucursales.dao.ConsultarSucursalesDAO
     * #actualizarSucursales(ar.com.santanderrio.obp.servicios.comun.sucursales.
     * entities.Sucursales)
     */
    @Override
    public void actualizarSucursales(Sucursales sucursales) throws DAOException {
        try {
            String sucursalesXml = obtenerSucursalesAsXml(sucursales);
            escribirSucursalesFile(sucursalesXml);
            entidades = sucursales.getSucursales();
        } catch (JAXBException e) {
            LOGGER.error("Error al generar xml con las sucursales obtenidas desde iatx.");
            throw new DAOException(e);
        } catch (IOException e) {
            LOGGER.error("Error al escribir el xml con las sucursales obtenidas desde iatx.");
            throw new DAOException(e);
        }
    }

    /**
     * Generar el archivo de sucursales.
     * 
     * @param sucursalesXml
     * @throws IOException
     */
    private void escribirSucursalesFile(String sucursalesXml) throws IOException {
        File file = new File(filePath.getFilePath() + getSucuFileName());
        FileUtils.writeStringToFile(file, sucursalesXml, CharEncoding.UTF_8);
    }

    /**
     * Obtener el nombre del archivo de sucursales.<br/>
     * Consulta la propiedad SUCURSALES_FILE_NAME si no esta utiliza el valor de
     * {@link ar.com.santanderrio.obp.base.types.ExternalPropertyType#FILE_SUCURSALES
     * sucursales}
     * 
     * @return
     */
    private String getSucuFileName() {
        return environment.getProperty(SUCURSALES_FILE_NAME, ExternalPropertyType.FILE_SUCURSALES.getName());
    }

    /**
     * Obtener el xml que se va escribir en el archivo de sucursales.
     * 
     * @param sucursales
     * @return
     * @throws JAXBException
     */
    private String obtenerSucursalesAsXml(Sucursales sucursales) throws JAXBException {
        return JaxbUtils.transformarObjetoAXml(sucursales, "UTF-8", Boolean.TRUE, Boolean.TRUE, null);
    }

}
