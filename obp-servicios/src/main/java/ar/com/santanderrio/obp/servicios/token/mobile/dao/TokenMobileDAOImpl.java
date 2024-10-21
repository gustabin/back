/*
 * 
 */
package ar.com.santanderrio.obp.servicios.token.mobile.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.dao.impl.BaseDatoDAOImpl;
import ar.com.santanderrio.obp.base.security.aop.annotation.TargetSystem;
import ar.com.santanderrio.obp.base.security.credential.DataBase;
import ar.com.santanderrio.obp.servicios.token.mobile.entities.TokenMobile;
import oracle.jdbc.OracleTypes;

/**
 * The Class TokenMobileDAOImpl.
 */
@Component
@TargetSystem(DataBase.ESTADISTICAS)
public class TokenMobileDAOImpl extends BaseDatoDAOImpl implements TokenMobileDAO {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(TokenMobileDAOImpl.class);

    /** The Constant IN_NUP. */
    private static final String IN_NUP = "p_nup";

    /** The Constant OUT_TOKEN_MOBILE_RESULTADO. */
    private static final String OUT_TOKEN_MOBILE_RESULTADO = "p_resultado";

    /** The Constant OUT_TOKEN_MOBILE_TECNICO. */
    private static final String OUT_TOKEN_MOBILE_TECNICO = "p_err_tecnico";

    /** The Constant OUT_TOKEN_MOBILE_CURSOR. */
    private static final String OUT_TOKEN_MOBILE_CURSOR = "p_cursor";

    /** The Constant NUP. */
    private static final String NUP = "NUP";

    /** The Constant TOKEN. */
    private static final String TOKEN = "TOKEN";

    /** The Constant HASHSEGURO. */
    private static final String HASHSEGURO = "HASHSEGURO";

    /** The Constant ESTADO. */
    private static final String ESTADO = "ESTADO";

    /** Constante COD_RESULTADO_OK. */
    private static final String COD_RESULTADO_OK = "0";
    /** SCHEMA. */
    private static final String TOKEN_MOBILE_SCHEMA = "hbank";

    /** PACKAGE contrato. */
    private static final String TOKEN_MOBILE_PACKAGE = "HB_PKG_TOKEN_MOBILE";

    /** PROCEDURE contrato. */
    private static final String TOKEN_MOBILE_VER_PROCEDURE = "prc_consultar_token_mobile";

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.token.mobile.dao.TokenMobileDAO#
     * consultarTokenMobile(java.lang.String)
     */
    @Override
    public TokenMobile consultarTokenMobile(String nup) throws DAOException, EmptyResultDataAccessException {
        List<SqlParameter> parametros = new ArrayList<SqlParameter>();
        parametros.add(new SqlParameter(IN_NUP, Types.VARCHAR));
        parametros.add(new SqlOutParameter(OUT_TOKEN_MOBILE_CURSOR, OracleTypes.CURSOR,
                new ResultSetExtractor<Map<String, String>>() {
                    @Override
                    public Map<String, String> extractData(ResultSet rs) throws SQLException {
                        Map<String, String> valores = new HashMap<String, String>();

                        while (rs.next()) {
                            if (rs.getString(NUP) != null) {
                                valores.put(NUP, rs.getString(NUP));
                            }
                            if (rs.getString(TOKEN) != null) {
                                valores.put(TOKEN, rs.getString(TOKEN));
                            }
                            if (rs.getString(HASHSEGURO) != null) {
                                valores.put(HASHSEGURO, rs.getString(HASHSEGURO));
                            }
                            if (rs.getString(ESTADO) != null) {
                                valores.put(ESTADO, rs.getString(ESTADO));
                            }
                        }

                        return valores;
                    }
                }));
        parametros.add(new SqlOutParameter(OUT_TOKEN_MOBILE_RESULTADO, Types.VARCHAR));
        parametros.add(new SqlOutParameter(OUT_TOKEN_MOBILE_TECNICO, Types.VARCHAR));
        SqlParameterSource in = new MapSqlParameterSource().addValue(IN_NUP, nup);
        try {
            Map<String, Object> respuesta = consultar(TOKEN_MOBILE_SCHEMA, TOKEN_MOBILE_PACKAGE,
                    TOKEN_MOBILE_VER_PROCEDURE, in, parametros.toArray(new SqlParameter[parametros.size()]));
            String codRetorno = (String) respuesta.get(OUT_TOKEN_MOBILE_RESULTADO);
            if (COD_RESULTADO_OK.equals(codRetorno)) {
                @SuppressWarnings("unchecked")
                Map<String, String> datosCredencial = (Map<String, String>) respuesta.get(OUT_TOKEN_MOBILE_CURSOR);
                if (datosCredencial != null && !MapUtils.isEmpty(datosCredencial)) {
                    if (StringUtils.isBlank(datosCredencial.get(TOKEN))) {
                        throw new DAOException("Se obtiene un registro de la base sin token cargado.");
                    }
                    return new TokenMobile.TokenMobileBuilder().setNup(datosCredencial.get(NUP))
                            .setToken(datosCredencial.get(TOKEN)).setHashSeguro(datosCredencial.get(HASHSEGURO))
                            .setEstado(datosCredencial.get(ESTADO)).build();
                } else {
                    LOGGER.info("No hay valores retornados al consultar el store {} con nup {}.",
                            TOKEN_MOBILE_VER_PROCEDURE, nup);
                    throw new EmptyResultDataAccessException(1);
                }
            } else {
                LOGGER.info("Error controlado al consultar el store {} con nup {}.", TOKEN_MOBILE_VER_PROCEDURE, nup);
                throw new DAOException("Codigo resultado distinto a cero");
            }
        } catch (SQLException sqle) {
            LOGGER.error("Error al consumir el store {}.{}.{} con nup {}.", TOKEN_MOBILE_SCHEMA, TOKEN_MOBILE_PACKAGE,
                    TOKEN_MOBILE_VER_PROCEDURE, nup, sqle);
            throw new DAOException(sqle);
        }
    }

}
