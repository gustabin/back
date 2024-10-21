/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tenencias.dao;

import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.dao.impl.BaseDatoDAOImpl;
import ar.com.santanderrio.obp.base.security.aop.annotation.TargetSystem;
import ar.com.santanderrio.obp.base.security.credential.DataBase;
import ar.com.santanderrio.obp.servicios.tenencias.entity.TipClienteCitiTenenciasOutEntity;

/**
 * The Class TenenciasCitiDAOImpl.
 */
@Component
@TargetSystem(DataBase.ESTADISTICAS)
public class TenenciasCitiDAOImpl extends BaseDatoDAOImpl implements TenenciasCitiDAO {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(TenenciasDAOImpl.class);

    /** PROCEDURE CONSULTAR_TENENCIAS_2017. */
    /** SCHEMA. */
    private static final String TENENCIAS_2017_SCHEMA = "HBANK";

    /** PACKAGE Tenencias 2017. */
    private static final String TENENCIAS_2017_PACKAGE = "HB_PKG_TENENCIAS_2017";

    /** PROCEDURE Tenencias 2017. */
    private static final String TENENCIAS_2017_PROCEDURE = "CONSULTAR_TENENCIAS_2017";

    /** The Constant NUP. */
    private static final String TENENCIAS2017_NUP = "L_NUP";

    /** The Constant IN_OUT_TIPO_CLIENTE. */
    private static final String IN_OUT_TENENCIAS2017_TIPO_CLIENTE = "TIPO";

    /** The Constant IN_OUT_FIRMANTE_PRESTAMOS. */
    private static final String IN_OUT_TENENCIAS2017_CODRESULTADO = "p_resultado";

    /** The Constant IN_OUT_FIRMANTE_PRESTAMOS. */
    private static final String IN_OUT_TENENCIAS2017_ERROTEC = "p_err_tecnico";

    /** The Constant IN_OUT_FIRMANTE_PRESTAMOS. */
    private static final String IN_OUT_TENENCIAS2017_ERROAMIG = "p_err_amigable";

    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.tenencias.dao.TenenciasCitiDAO#obtenerTipoCLienteTenencias(java.lang.String, java.lang.String, java.lang.String)
     */
    public TipClienteCitiTenenciasOutEntity obtenerTipoCLienteTenencias(String nup, String anioDesde, String anioHasta)
            throws DAOException {
        LOGGER.debug("Se consultara el procedure {} con los datos nup: {}", TENENCIAS_2017_PROCEDURE, nup);

        List<SqlParameter> parametros = new ArrayList<SqlParameter>();

        parametros.add(new SqlParameter(TENENCIAS2017_NUP, Types.LONGVARCHAR));
        parametros.add(new SqlOutParameter(IN_OUT_TENENCIAS2017_TIPO_CLIENTE, Types.VARCHAR));
        parametros.add(new SqlOutParameter(IN_OUT_TENENCIAS2017_CODRESULTADO, Types.VARCHAR));
        parametros.add(new SqlOutParameter(IN_OUT_TENENCIAS2017_ERROTEC, Types.VARCHAR));
        parametros.add(new SqlOutParameter(IN_OUT_TENENCIAS2017_ERROAMIG, Types.VARCHAR));

        SqlParameterSource in = new MapSqlParameterSource().addValue(TENENCIAS2017_NUP, Long.parseLong(nup));
        try {
            Map<String, Object> respuesta = consultar(TENENCIAS_2017_SCHEMA, TENENCIAS_2017_PACKAGE,
                    TENENCIAS_2017_PROCEDURE, in, parametros.toArray(new SqlParameter[parametros.size()]));
            TipClienteCitiTenenciasOutEntity tipoClienteCiti = new TipClienteCitiTenenciasOutEntity();
            tipoClienteCiti.setCodResultado(respuesta.get(IN_OUT_TENENCIAS2017_CODRESULTADO).toString());
            tipoClienteCiti.setDescErrorAmig(respuesta.get(IN_OUT_TENENCIAS2017_ERROAMIG).toString());
            tipoClienteCiti.setDescErrorTec(respuesta.get(IN_OUT_TENENCIAS2017_ERROTEC).toString());
            tipoClienteCiti.setTipCliente(respuesta.get(IN_OUT_TENENCIAS2017_TIPO_CLIENTE).toString());
            return tipoClienteCiti;
        } catch (SQLException sqle) {
            LOGGER.error(sqle.getMessage(), sqle);
            throw new DAOException(sqle);
        } catch (UncategorizedSQLException sqle) {
            LOGGER.error(sqle.getMessage(), sqle);
            throw new DAOException(sqle);
        } catch (RuntimeException ex) {
            LOGGER.error(ex.getMessage(), ex);
            throw new DAOException(ex);
        }
    }

}
