/**
 * 
 */
package ar.com.santanderrio.obp.servicios.comun;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.comun.FilePath;
import ar.com.santanderrio.obp.base.types.ExternalPropertyType;

/**
 * Posibilita generar un pid file principalmente para manejar actualizaciones
 * que se ejecutan en diferentes instancias y no se pueden solapar.
 * 
 * @author sergio.e.goldentair
 *
 */
@Component
public class PidFileUtil {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(PidFileUtil.class);

    /**
     * Extension de los pid files
     */
    private static final String PID_EXTENSION = ".pid";

    /** The file path. */
    @Autowired
    private FilePath filePath;

    /**
     * Se encarga en caso de que algun pid fallara al limpiarse de purgarlos.
     */
    @PostConstruct
    private void limpiarPids() {
        List<ExternalPropertyType> listExternalPropertyType = Arrays.asList(ExternalPropertyType.FILE_SUCURSALES,
                ExternalPropertyType.FILE_MEDIOSDEPAGO);
        for (ExternalPropertyType externalPropertyType : listExternalPropertyType) {
            File pidFile = getFileForPid(externalPropertyType);
            if (isPidActivo(pidFile)) {
                borrarPid(pidFile, true);
            }
        }
    }

    /**
     * Obtener file con el que se va a generar el control de pid.
     * 
     * @param externalPropertyType
     * @return
     */
    public File getFileForPid(ExternalPropertyType externalPropertyType) {
        return new File(filePath.getFilePath() + getCleanName(externalPropertyType) + PID_EXTENSION);
    }

    /**
     * Obtener el nombre del file sobre el que se desea operar sin la extension ya
     * que luego se le appendeara el pid.
     * 
     * @param externalPropertyType
     * @return
     */
    private String getCleanName(ExternalPropertyType externalPropertyType) {
        return StringUtils.substringBefore(externalPropertyType.getName(), ".");
    }

    /**
     * Crear archivo pid gestionado desde el consumidor y notificar si la operacion
     * fue exitosa.
     * 
     * @param pidFile
     * @return boolean
     * @throws IOException
     */
    public boolean crearPid(File pidFile) throws IOException {
        boolean isCreado = pidFile.createNewFile();
        LOGGER.info("Se creara el pid file {} con status {}", pidFile.getName(), isCreado);
        return isCreado;
    }

    /**
     * Indicar si existe el pid en estos casos lo recomendado es no realizar ninguna
     * operacion ya que se esta realizando en otro flujo.
     * 
     * @param pidFile
     * @return
     */
    public boolean isPidActivo(final File pidFile) {
        return pidFile.exists();
    }

    /**
     * Limpiar el pid para que otro lo pueda escribir.
     * 
     * @param pidFile
     * @param fileCreado
     */
    public void borrarPid(File pidFile, boolean fileCreado) {
        if (fileCreado) {
            try {
                boolean limpioPid = pidFile.delete();
                LOGGER.info("Se limpio el pid de sucursales {}.", limpioPid);
            } catch (SecurityException e) {
                LOGGER.error("No se pudo limpiar el pid de sucursales.", e);
            }
        } else {
            LOGGER.debug("No se limpia el pid ya que esta siendo utilizado");
        }
    }

}
