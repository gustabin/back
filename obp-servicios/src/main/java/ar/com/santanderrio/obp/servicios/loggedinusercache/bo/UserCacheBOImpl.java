package ar.com.santanderrio.obp.servicios.loggedinusercache.bo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.loggedinusercache.dao.UserCacheDAO;
import ar.com.santanderrio.obp.servicios.loggedinusercache.entities.CacheServiceEnum;

@Component
public class UserCacheBOImpl implements UserCacheBO {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserCacheBOImpl.class);
    
    @Autowired
    UserCacheDAO userCacheDAO;

    @Override
    public void clearCache(String token) {
        
        try {
            userCacheDAO.clearCache(token);
        } catch (DAOException e) {
        	LOGGER.error("Fail to clear cache");
        }
    }

    @Override
    public <T> T getCache(String token, CacheServiceEnum cacheService) throws DAOException {
        return userCacheDAO.getCache(token, cacheService);
    }

    @Override
    public void setCache(String token, Object body) throws DAOException {
        userCacheDAO.setCache(token, body);
    }
    
}
