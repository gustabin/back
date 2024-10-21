/**
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.combos.dao.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.comun.FilePath;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.comun.combos.dao.ConsultarImportesDeRecargaDAO;
import ar.com.santanderrio.obp.servicios.comun.combos.entities.ComboType;
import ar.com.santanderrio.obp.servicios.comun.combos.entities.CombosType;
import ar.com.santanderrio.obp.servicios.comun.combos.entities.Opcion;
import ar.com.santanderrio.obp.servicios.comun.combos.entities.OpcionType;
import ar.com.santanderrio.obp.servicios.comun.combos.entities.OpcionesType;

/**
 * The Class ConsultarImportesARecargarDAOImpl.
 *
 * @author B039542
 */
@Component
@Scope("singleton")
public class ConsultarImportesDeRecargaDAOImpl implements ConsultarImportesDeRecargaDAO {
    /** The Constant logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(ConsultarImportesDeRecargaDAOImpl.class);

    /** The file path. */
    @Autowired
    private FilePath filePath;

    /** The entidades. */
    private List<ComboType> listCombo;

    /** The list opcion. */
    private List<Opcion> listOpcion;

    /** The Constant TAG_IMPORTE_A_RECARGAR. */
    private static final String TAG_IMPORTE_A_RECARGAR = "tagMaximoVez";

    /** The Constant TAG_LIMITE_RECARGA_MENSUAL. */
    private static final String TAG_LIMITE_RECARGA_MENSUAL = "tagMaximoMensual";

    /** The Constant TAG_PROVINCIA. */
    private static final String TAG_PROVINCIA = "provincias";

    /** The Constant TAG_PAIS_NACIMIENTO. */
    private static final String TAG_PAIS_NACIMIENTO = "paisNacimiento";

    /**
     * Generar entidades.
     *
     * @return the list
     */
    @PostConstruct
    public void init() {
        try {
            /**
             * File file = new File(filePath.getFilePath() +
             * ExternalPropertyType.FILE_COMBOS.getName()); // Hasta que no se
             * actualice el obp-base se utilizara el string hardcode
             */
            File file = new File(filePath.getFilePath() + "combos.xml");

            JAXBContext jaxbContext = JAXBContext.newInstance(CombosType.class);

            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            CombosType combos = (CombosType) unmarshaller.unmarshal(file);
            this.listCombo = combos.getCombo();

        } catch (JAXBException e) {
            LOGGER.error("No se puede inicializar la lectura de combos.xml", e);
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.comun.combos.dao.
     * ConsultarImportesDeRecargaDAO#obtenerImportesMaximosMensuales()
     */
    @Override
    public List<Opcion> obtenerImportesMaximosMensuales() throws DAOException {

        return this.buscarComboPorTag(TAG_IMPORTE_A_RECARGAR);

    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.comun.combos.dao.
     * ConsultarImportesDeRecargaDAO#obtenerLimitesDeRecargaMensual()
     */
    @Override
    public List<Opcion> obtenerLimitesDeRecargaMensual() throws DAOException {

        return this.buscarComboPorTag(TAG_LIMITE_RECARGA_MENSUAL);

    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.comun.combos.dao.
     * ConsultarImportesDeRecargaDAO#obtenerProvincias()
     */
    @Override
    public List<Opcion> obtenerProvincias() throws DAOException {

        return this.buscarComboPorTag(TAG_PROVINCIA);

    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.comun.combos.dao.
     * ConsultarImportesDeRecargaDAO#obtenerOpcionDescripcion(java.lang.String,
     * java.lang.String)
     */
    @Override
    public String obtenerOpcionDescripcion(String comboTag, String id) {

        List<Opcion> opc = this.buscarComboPorTag(comboTag);

        return this.buscarOpcionPorId(id, opc);
    }

    /**
     * Buscar sucursal.
     *
     * @param id
     *            the id
     * @return the sucursal
     */
    private List<Opcion> buscarComboPorTag(String id) {
        for (ComboType combo : this.listCombo) {
            if (combo.getId().equals(id)) {
                return convertTo(combo.getOpciones());
            }
        }
        return null;
    }

    /**
     * Buscar opcion por id.
     *
     * @param id
     *            the id
     * @param opciones
     *            the opciones
     * @return the string
     */
    private String buscarOpcionPorId(String id, List<Opcion> opciones) {
        for (Opcion opcion : opciones) {
            if (opcion.getId().equals(id)) {
                return opcion.getOpcion();
            }
        }
        return null;
    }

    /**
     * Buscar opcion descripcion.
     *
     * @param comboTagId
     *            the combo tag id
     * @param opcionId
     *            the opcion id
     * @return the string
     */
    private String buscarOpcionDescripcion(String comboTagId, String opcionId) {

        List<Opcion> opciones = this.buscarComboPorTag(comboTagId);
        return buscarOpcionPorId(opcionId, opciones);

    }

    /**
     * Convert to.
     *
     * @param opcionesType
     *            the opciones type
     * @return the list
     */
    private List<Opcion> convertTo(OpcionesType opcionesType) {
        List<Opcion> opciones = new ArrayList<Opcion>();
        for (OpcionType opcionType : opcionesType.getOpcion()) {
            Opcion opcion = new Opcion();
            opcion.setId(opcionType.getId());
            opcion.setOpcion(opcionType.getValue());
            opciones.add(opcion);
        }
        return opciones;
    }

}
