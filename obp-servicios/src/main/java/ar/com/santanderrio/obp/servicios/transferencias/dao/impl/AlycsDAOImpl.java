package ar.com.santanderrio.obp.servicios.transferencias.dao.impl;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.config.ws.CacheConstants;
import ar.com.santanderrio.obp.servicios.comun.dao.ArchivoDeRecursosDAO;
import ar.com.santanderrio.obp.servicios.transferencias.dao.AlycsDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class AlycsDAOImpl implements AlycsDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(AlycsDAOImpl.class);

    @Autowired
    private ArchivoDeRecursosDAO archivoDeRecursosDAO;

    private static final String FILE_ALYCS_CUITS = "alycsCuits.txt";

    @Cacheable(cacheNames = { CacheConstants.Names.CACHE_ALYCS_CUITS })
    @Override
    public List<String> getCuitsAlycs() throws DAOException {
        return archivoDeRecursosDAO.leerArchivo(FILE_ALYCS_CUITS);
    }

    @CacheEvict(value = CacheConstants.Names.CACHE_ALYCS_CUITS, allEntries = true)
    @Override
    public void cleanCacheCuitsAlycs() {

        LOGGER.info("Se limpia la cache que contiene los cuits de Alycs");

    }
}
