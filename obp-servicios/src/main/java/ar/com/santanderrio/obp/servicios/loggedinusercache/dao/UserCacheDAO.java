package ar.com.santanderrio.obp.servicios.loggedinusercache.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.loggedinusercache.entities.CacheServiceEnum;

public interface UserCacheDAO {
    
    <T> T getCache(String token, CacheServiceEnum cacheService) throws DAOException;
    
    void setCache(String token, Object body) throws DAOException;

    void clearCache(String initialJWT) throws DAOException;
    
}
