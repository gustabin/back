package ar.com.santanderrio.obp.servicios.loggedinusercache.bo;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.loggedinusercache.entities.CacheServiceEnum;

public interface UserCacheBO {

    <T> T getCache(String token, CacheServiceEnum cacheService) throws DAOException;
    
    void setCache(String token, Object body) throws DAOException;

    void clearCache(String initialJWT);
}
