/**
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.bo.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.comun.FilePath;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.lucene.LuceneIndexerType;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.base.types.ExternalPropertyType;
import ar.com.santanderrio.obp.servicios.comun.PidFileUtil;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.lucene.MediosDePagoTextFileIndexer;
import ar.com.santanderrio.obp.servicios.pagos.bo.BuscadorEmpresaIndexadoBO;
import ar.com.santanderrio.obp.servicios.pagos.dao.BuscadorEmpresaDAO;
import ar.com.santanderrio.obp.servicios.pagos.entities.MedioPago;

/**
 * @author sergio.e.goldentair
 *
 */
@Component
public class BuscadorEmpresaIndexadoBOImpl implements BuscadorEmpresaIndexadoBO {
    /** The Constant logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(BuscadorEmpresaIndexadoBOImpl.class);

    /** The medios pago DAO. */
    @Autowired
    private BuscadorEmpresaDAO buscadorEmpresaDAO;

    /** The text file indexer. */
    @Autowired
    private MediosDePagoTextFileIndexer textFileIndexer;

    /** The respuesta factory. */
    @Autowired
    private RespuestaFactory respuestaFactory;

    /** The base path. */
    @Value("${LUCENE.BASEPATH}")
    private String basePath;

    /** The pidfile path. */
    @Autowired
    private PidFileUtil pidFileUtil;

    /** The file path. */
    @Autowired
    private FilePath filePath;

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.pagos.bo.BuscadorEmpresaIndexadoBO#
     * limpiarMediosDePago()
     */
    @Override
    public Respuesta<Boolean> limpiarMediosDePago() {
        boolean status = reindexar();
        LOGGER.info("El procesamiento de la limpieza de medios de pago termino {}", status);
        boolean refrescOK = touchMediosDePagoRefresh(status);
        return respuestaFactory.crearRespuestaOk(refrescOK);
    }

    /**
     * Se refresca el archivo para que las instancias puedan actualizar la memoria
     * con los medios de pago.
     * 
     * @param status
     */
    private boolean touchMediosDePagoRefresh(boolean status) {
        if (status) {
            String filePathName = filePath.getFilePath() + "mediosDePago.refresh";
            try {
                FileUtils.touch(new File(filePathName));
                LOGGER.info("Se realiza touch sobre {}, las instancias refrescaran el mapa de medios de pago.",
                        filePathName);
                return true;
            } catch (IOException e) {
                LOGGER.error(
                        "No se pudo hacer touch sobre {}, las instancias NO refrescaran el mapa de medios de pago.",
                        filePathName, e);
            }
        }
        return false;
    }

    /**
     * Reindexa lucene.
     * 
     * @return
     */
    private boolean reindexar() {
        File pidFile = pidFileUtil.getFileForPid(ExternalPropertyType.FILE_MEDIOSDEPAGO);
        boolean pidCreado = false;
        boolean status = true;
        if (!pidFileUtil.isPidActivo(pidFile)) {
            try {
                pidCreado = pidFileUtil.crearPid(pidFile);
                if (pidCreado) {
                	String location = basePath + LuceneIndexerType.MEDIO_DE_PAGO.getName();
                	LOGGER.info("La ubicacion de la carpeta de indexado es {}", location);
                	textFileIndexer.init(location);
                    textFileIndexer.deleteAll();
                    List<MedioPago> medioPagos = buscadorEmpresaDAO.findAllMediosDePago();
                    for (MedioPago pago : medioPagos) {
                        textFileIndexer.toIndex(pago, location);
                    }
                    textFileIndexer.commit();
                    textFileIndexer.closeAll();
                    Thread.sleep(3000);
                }
            } catch (IOException e) {
                LOGGER.error("No se pudo borrar el indexado", e);
                status = false;
            } catch (DAOException e) {
                LOGGER.error("No se puede reindexar", e);
                status = false;
            } catch (Exception e) {
                LOGGER.error("No se efectua la limpieza ya que esta siendo procesado por otro servidor {}",
                        ExternalPropertyType.FILE_MEDIOSDEPAGO.getName(), e);
                status = false;
            } finally {
                pidFileUtil.borrarPid(pidFile, pidCreado);
            }
        }
        return status;
    }
}
