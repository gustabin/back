/**
 * 
 */
package ar.com.santanderrio.obp.servicios.monitoreo.dao;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.dao.QueryTimeoutException;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ar.com.santanderrio.obp.base.security.aop.annotation.TargetSystem;
import ar.com.santanderrio.obp.base.security.credential.DataBase;
import ar.com.santanderrio.obp.base.security.datasource.SystemRoutingDataSource;

/**
 * The Class BaseMonitoreoDAOImpl.
 *
 * @author sergio.e.goldentair
 */
@Repository
@TargetSystem(DataBase.ESTADISTICAS)
public class BaseMonitoreoDAOImpl implements BaseMonitoreoDAO {
    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseMonitoreoDAOImpl.class);

    /** The Constant sqlSelect. */
    private static final String sqlSelect = "select 1 from dual";

    /** The data source. */
    @Autowired
    private SystemRoutingDataSource dataSource;

    /** The time out. */
    @Value("${DB.TIMEOUT:30000}")
    private Long queryTimeout;

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.monitoreo.dao.BaseMonitoreoDAO#
     * isDBDisponible()
     */
    @Override
    public boolean isDBDisponible() {
        LOGGER.debug("Se ejecuta la query {} para validar conectividad.", sqlSelect);
        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
            Long milisegEnSegundos = TimeUnit.MILLISECONDS.toSeconds(queryTimeout);
            jdbcTemplate.setQueryTimeout(milisegEnSegundos.intValue());
            return jdbcTemplate.queryForObject(sqlSelect, Boolean.class);
        } catch (DataAccessResourceFailureException dae) {
            LOGGER.error(
                    "Thrown when a resource fails completely: for example, if we can't connect to a database using JDBC.",
                    dae);
        } catch (UncategorizedSQLException ex) {
            LOGGER.error("Thrown when we can't classify a SQLException into one of our generic.", ex);
        } catch (InvalidDataAccessApiUsageException invalidData) {
            LOGGER.error(
                    "Thrown on incorrect usage of the API, such as failing to \"compile\" a query object that needed compilation before execution.",
                    invalidData);
        } catch (QueryTimeoutException qte) {
            LOGGER.error("Thrown on a query timeout.", qte);
        } catch (RuntimeException rte) {
            LOGGER.error("Runtime.", rte);
        }
        return false;
    }

}
