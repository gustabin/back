package ar.com.santanderrio.obp.servicios.campaniabeneficios.dao;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.dao.QueryTimeoutException;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.dao.impl.BaseDatoDAOImpl;
import ar.com.santanderrio.obp.base.security.aop.annotation.TargetSystem;
import ar.com.santanderrio.obp.base.security.credential.DataBase;
import ar.com.santanderrio.obp.servicios.campaniabeneficios.dto.SuscCampaniaBeneficiosInDTO;
import ar.com.santanderrio.obp.servicios.campaniabeneficios.entities.RepuestaSPSuscCampaniaBeneficiosEntity;

/**
 * The Class CampaniaBeneficiosDAOImpl.
 */
@Repository
@TargetSystem(DataBase.ESTADISTICAS)
public class CampaniaBeneficiosDAOImpl extends BaseDatoDAOImpl implements CampaniaBeneficiosDAO {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(CampaniaBeneficiosDAOImpl.class);

    /** The Constant SCHEMA NAME. */
    private static final String SCHEMA_NAME = "HBANK";

    /** The Constant PACKAGE_NAME. */
    private static final String PACKAGE_NAME = "HB_PKG_CAMPANIAS";

    /** The Constant PROCEDURE_SUSCRIBIR. */
    private static final String PROCEDURE_SUSCRIBIR = "SUSCRIBIR";

    /** The Constant P_ID_CAMPANIA. */
    private static final String P_ID_CAMPANIA = "P_ID_CAMPANIA";

    /** The Constant P NUP. */
    private static final String P_NUP = "P_NUP";

    /** The Constant LOGGER. */
    private static final String P_RESPUESTA = "P_RESPUESTA";

    /** The Constant P_PROGRAMA. */
    private static final String P_PROGRAMA = "P_PROGRAMA";

    /** The Constant P_COMENTARIOS. */
    private static final String P_COMENTARIOS = "P_COMENTARIOS";

    /** The Constant P_RESULTADO. */
    private static final String P_RESULTADO = "p_resultado";

    /** The Constant P_SUSCRIPTO. */
    private static final String P_SUSCRIPTO = "p_suscripto";

    /** The P_ERROR_TECNICO. */
    private static final String P_ERROR_TECNICO = "p_err_tecnico";

    /** The P_ERROR_AMIGABLE. */
    private static final String P_ERROR_AMIGABLE = "p_err_amigable";

    /** The Constant COD_RETORNO_OK. */
    private static final BigDecimal COD_RETORNO_OK = new BigDecimal("0");

    /** The Constant ERROR_EN_BASE. */
    private static final String ERROR_EN_BASE = "Error en base del store {}.{}.{} al solicitar informacion.";

    /** The Constant MENSAJE_ERROR_EN_STORE. */
    private static final String MENSAJE_ERROR_EN_STORE = "Error en store {}.{}.{}. al solicitar la informacion.";

    /** The Constant ERROR_TIMEOUT. */
    private static final String ERROR_TIMEOUT = "Error de Timeout.";

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.campaniabeneficios.dao.
     * CampaniaBeneficiosDAO#suscribirCampaniaBeneficios(ar.com.santanderrio.obp.
     * servicios.campaniabeneficios.dto.SuscCampaniaBeneficiosInDTO)
     */
    @Override
    public RepuestaSPSuscCampaniaBeneficiosEntity suscribirCampaniaBeneficios(SuscCampaniaBeneficiosInDTO inDTO)
            throws DAOException {
        String comentarios = inDTO.getVariable1Char();
        Long idcampania = Long.parseLong(inDTO.getIdcampania());
        Long nup = Long.parseLong(inDTO.getNup());
        List<SqlParameter> parametros = new ArrayList<SqlParameter>();
        parametros.add(new SqlParameter(P_ID_CAMPANIA, Types.NUMERIC));
        parametros.add(new SqlParameter(P_NUP, Types.NUMERIC));
        parametros.add(new SqlParameter(P_RESPUESTA, Types.VARCHAR));
        parametros.add(new SqlParameter(P_PROGRAMA, Types.VARCHAR));
        parametros.add(new SqlParameter(P_COMENTARIOS, Types.VARCHAR));

        parametros.add(new SqlOutParameter(P_RESULTADO, Types.VARCHAR));
        parametros.add(new SqlOutParameter(P_SUSCRIPTO, Types.VARCHAR));
        parametros.add(new SqlOutParameter(P_ERROR_TECNICO, Types.VARCHAR));
        parametros.add(new SqlOutParameter(P_ERROR_AMIGABLE, Types.VARCHAR));

        SqlParameterSource in = new MapSqlParameterSource().addValue(P_ID_CAMPANIA, idcampania).addValue(P_NUP, nup)
                .addValue(P_RESPUESTA, inDTO.getRespuesta()).addValue(P_PROGRAMA, inDTO.getPrograma())
                .addValue(P_COMENTARIOS, comentarios);
        try {
            Map<String, Object> respuesta = consultar(SCHEMA_NAME, PACKAGE_NAME, PROCEDURE_SUSCRIBIR, in,
                    parametros.toArray(new SqlParameter[parametros.size()]));
            BigDecimal codRetorno = new BigDecimal((String) respuesta.get(P_RESULTADO));
            if (COD_RETORNO_OK.equals(codRetorno)) {
                return new RepuestaSPSuscCampaniaBeneficiosEntity((String) respuesta.get(P_SUSCRIPTO),
                        (String) respuesta.get(P_ERROR_TECNICO), (String) respuesta.get(P_ERROR_AMIGABLE));
            } else {
                throw new DAOException((String) respuesta.get(P_ERROR_AMIGABLE));
            }
        } catch (DataAccessResourceFailureException dae) {
            LOGGER.error(ERROR_EN_BASE, SCHEMA_NAME, PACKAGE_NAME, PROCEDURE_SUSCRIBIR, dae);
            throw new DAOException(dae);
        } catch (SQLException sqle) {
            LOGGER.error(MENSAJE_ERROR_EN_STORE, SCHEMA_NAME, PACKAGE_NAME, PROCEDURE_SUSCRIBIR, sqle);
            throw new DAOException(sqle);
        } catch (UncategorizedSQLException ex) {
            LOGGER.error(ERROR_EN_BASE, SCHEMA_NAME, PACKAGE_NAME, PROCEDURE_SUSCRIBIR, ex);
            throw new DAOException(ex);
        } catch (InvalidDataAccessApiUsageException invalidData) {
            LOGGER.error(MENSAJE_ERROR_EN_STORE, SCHEMA_NAME, PACKAGE_NAME, PROCEDURE_SUSCRIBIR, invalidData);
            throw new DAOException(invalidData);
        } catch (IllegalStateException iex) {
            LOGGER.error(MENSAJE_ERROR_EN_STORE, SCHEMA_NAME, PACKAGE_NAME, PROCEDURE_SUSCRIBIR, iex);
            throw new DAOException(iex);
        } catch (QueryTimeoutException qte) {
            LOGGER.error(ERROR_TIMEOUT, SCHEMA_NAME, PACKAGE_NAME, PROCEDURE_SUSCRIBIR, qte);
            throw new DAOException(qte.getMessage());
        }
    }

}
