/*
 * 
 */
package ar.com.santanderrio.obp.servicios.configuration;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import ar.com.santanderrio.obp.base.comun.FilePath;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.lucene.LuceneIndexerType;
import ar.com.santanderrio.obp.base.types.ExternalPropertyType;
import ar.com.santanderrio.obp.servicios.comun.PidFileUtil;
import ar.com.santanderrio.obp.servicios.lucene.MediosDePagoTextFileIndexer;
import ar.com.santanderrio.obp.servicios.pagos.dao.BuscadorEmpresaDAO;
import ar.com.santanderrio.obp.servicios.pagos.entities.MedioPago;

/**
 * Esta clase esta manejara la parametrizacion como el seteo del profile de la
 * aplicacion.
 * 
 * @author pablo.martin.gore
 *
 */

@Configuration
public class ObpCustomInitializer {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(ObpCustomInitializer.class);

    /** The base path. */
    @Value("${LUCENE.BASEPATH}")
    private String basePath;

    /** The poll interval. */
    @Value("${LUCENE.POLL_INTERVAL:10000}")
    private String pollInterval;

    /** The text file indexer. */
    @Autowired
    private MediosDePagoTextFileIndexer textFileIndexer;

    /** The pago DAO. */
    @Autowired
    private BuscadorEmpresaDAO buscadorEmpresaDAO;

    /** The file path. */
    @Autowired
    private FilePath filePath;

    /** The pid file util. */
    @Autowired
    private PidFileUtil pidFileUtil;

    /**
     * On startup.
     */
    @PostConstruct
    public void onStartup() {
        FileAlterationMonitor monitor = new FileAlterationMonitor(Long.parseLong(pollInterval));
        String location = basePath + LuceneIndexerType.MEDIO_DE_PAGO.getName();
        File pidFile = null;
        boolean pidValido = false;
        try {
            if (existeIndice(location)) {
            	if (debeActualizarIndice(location)) {
            		pidFile = pidFileUtil.getFileForPid(ExternalPropertyType.FILE_MEDIOSDEPAGO);
                    if (!pidFileUtil.isPidActivo(pidFile)) {
                    	pidValido = pidFileUtil.crearPid(pidFile);
                    } else if (isPrevDate(pidFile)) {
                		pidValido = pidFile.setLastModified(new Date().getTime());
                    }
            		if (pidValido) {
                    	textFileIndexer.init(location);
                    	textFileIndexer.deleteAll();
                    	inicializarIndice(location);
                    }
            	} else {
	                LOGGER.info(
	                        "Ya existe la carpeta {} donde se guardan los indices, borrela para regenerar los indices nuevamente.",
	                        location);
            	}
            } else {
                inicializarIndice(location);
            }
        } catch (Throwable e) {
            LOGGER.error("No se corta la ejecucion de creacion del bean", e);
        } finally {
            pidFileUtil.borrarPid(pidFile, pidValido);
        }

        IOFileFilter iOFileFilter = FileFilterUtils.and(FileFilterUtils.fileFileFilter(),
                FileFilterUtils.nameFileFilter("mediosDePago.refresh"));
        FileAlterationObserver observer = new FileAlterationObserver(filePath.getFilePath(), iOFileFilter);
        FileAlterationListener listener = new FileAlterationListenerAdaptor() {
            @Override
            public void onFileChange(File file) {
                LOGGER.info("Se detecto un cambio en {}", file);
                buscadorEmpresaDAO.init();
            }
        };
        observer.addListener(listener);
        monitor.addObserver(observer);
        try {
            monitor.start();
        } catch (Exception e) {
            LOGGER.error("Error iniciando el monitos de archivo de medios de pago", e);
        }

    }

	/**
	 * Inicializar indice.
	 *
	 * @param location the location
	 * @throws DAOException the DAO exception
	 */
	private void inicializarIndice(String location) throws DAOException {
		LOGGER.info("La ubicacion de la carpeta de indexado es {}", location);
		List<MedioPago> medioPagos = buscadorEmpresaDAO.findAllMediosDePago();
		for (MedioPago pago : medioPagos) {
		    textFileIndexer.toIndex(pago, location);
		}
		textFileIndexer.commit();
		textFileIndexer.closeAll();
	}

    /**
     * Debe actualizar indice.
     *
     * @param location the location
     * @return true, if successful
     */
    private boolean debeActualizarIndice(String location) {
    	File f = new File(location);
    	return isPrevDate(f);
	}

	/**
	 * Checks if is prev date.
	 *
	 * @param f the f
	 * @return true, if is prev date
	 */
	private boolean isPrevDate(File f) {
		Date lastModified = new Date(f.lastModified());
		return !DateUtils.isSameDay(lastModified, new Date());
	}

	/**
     * Existe indice.
     *
     * @param location
     *            the location
     * @return true, if successful
     */
    private boolean existeIndice(String location) {
        return new File(location).exists();
    }
}