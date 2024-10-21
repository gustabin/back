/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagocompras.dao.impl;

import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.dao.impl.BaseDatoDAOImpl;
import ar.com.santanderrio.obp.base.security.aop.annotation.TargetSystem;
import ar.com.santanderrio.obp.base.security.credential.DataBase;
import ar.com.santanderrio.obp.servicios.pagocompras.dao.AlianzasDAO;
import ar.com.santanderrio.obp.servicios.pagocompras.entities.AlianzaEntity;
import oracle.jdbc.OracleTypes;

/**
 * The Class AlianzasDAOImpl.
 */
@Repository("alianzasDAO")
@TargetSystem(DataBase.ESTADISTICAS)
public class AlianzasDAOImpl extends BaseDatoDAOImpl implements AlianzasDAO {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(AlianzasDAOImpl.class);

    /** The Constant SCHEMA. */
    private static final String SCHEMA = "hbank";

    /** The Constant PACKAGE. */
    private static final String PACKAGE = "HB_PKG_ALIANZAS";

    /** The Constant PROCEDURE_ESTADO. */
    private static final String PROCEDURE_ADHESIONES = "adhesiones_lista";

    /** The Constant IN_TIPO_CLIENTE. */
    private static final String IN_TIPO_CLIENTE = "p_tipo_cliente";

    /** The Constant OUT_CURSOR. */
    private static final String OUT_CURSOR = "p_cursor";

    /** The Constant OUT_RESULTADO. */
    private static final String OUT_RESULTADO = "p_resultado";

    /** The Constant OUT_ERROR_TECNICO. */
    private static final String OUT_ERROR_TECNICO = "p_err_tecnico";

    /** The Constant OUT_ERROR_AMIGABLE. */
    private static final String OUT_ERROR_AMIGABLE = "p_err_amigable";

    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.pagocompras.dao.AlianzasDAO#consultarAlianzas()
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<AlianzaEntity> consultarAlianzas() throws DAOException {
        LOGGER.info("Obtener listado de alianzas desde la base de datos.");
        List<SqlParameter> parametros = new ArrayList<SqlParameter>();
        parametros.add(new SqlParameter(IN_TIPO_CLIENTE, Types.VARCHAR));
        parametros.add(new SqlOutParameter(OUT_RESULTADO, Types.VARCHAR));
        parametros.add(new SqlOutParameter(OUT_ERROR_TECNICO, Types.VARCHAR));
        parametros.add(new SqlOutParameter(OUT_ERROR_AMIGABLE, Types.VARCHAR));
        parametros.add(new SqlOutParameter(OUT_CURSOR, OracleTypes.CURSOR,
                new BeanPropertyRowMapper<AlianzaEntity>(AlianzaEntity.class)));

        SqlParameterSource in = new MapSqlParameterSource().addValue(IN_TIPO_CLIENTE, "");
        try {
            Map<String, Object> respuesta = consultar(SCHEMA, PACKAGE, PROCEDURE_ADHESIONES, in,
                    parametros.toArray(new SqlParameter[parametros.size()]));
            String codRetorno = (String) respuesta.get(OUT_RESULTADO);
            if ("0".equals(codRetorno)) {
                LOGGER.info("Se retorna la lista de alianzas.");
                return (List<AlianzaEntity>) respuesta.get(OUT_CURSOR);
            } else {
                throw new DAOException("No se puede obtener el listado de alianzas de la base.");
            }
        } catch (SQLException sqle) {
            LOGGER.error("Error al consumir el store {}.{}.{}.", SCHEMA, PACKAGE, PROCEDURE_ADHESIONES, sqle);
            throw new DAOException(sqle);
        } catch (UncategorizedSQLException usqle) {
            LOGGER.error("Error al consumir el store {}.{}.{}. errores internos.", SCHEMA, PACKAGE,
                    PROCEDURE_ADHESIONES, usqle);
            throw new DAOException(usqle);
        } catch (InvalidDataAccessApiUsageException idaaue) {
            LOGGER.error("Error al consumir el store {}.{}.{}. no hay grants.", SCHEMA, PACKAGE, PROCEDURE_ADHESIONES,
                    idaaue);
            throw new DAOException(idaaue);
        }
    }
}
