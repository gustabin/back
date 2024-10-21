package ar.com.santanderrio.obp.base.security.credential.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.datasource.UserCredentialsDataSourceAdapter;
import org.springframework.util.CollectionUtils;

import ar.com.santanderrio.obp.base.dao.BaseDatoDAO;
import ar.com.santanderrio.obp.base.dao.impl.CustomJdbcTemplate;
import ar.com.santanderrio.obp.base.security.PropertyMap;
import ar.com.santanderrio.obp.base.security.credential.Credential;
import ar.com.santanderrio.obp.base.security.credential.CredentialSecurity;
import ar.com.santanderrio.obp.base.security.credential.CredentialSecurityFactory;
import ar.com.santanderrio.obp.base.security.credential.DataBase;
import oracle.jdbc.OracleTypes;

// TODO: Auto-generated Javadoc
/**
 * The Class CredentialSecurityFactoryImpl.
 *
 * @author Jonatan_Bocian Lee archivo de datasources, por cada datasource que
 *         encuentra ejecutar el sp para traer las credenciales
 */

@Scope("singleton")
public class CredentialSecurityFactoryDAOImpl implements CredentialSecurityFactory, BaseDatoDAO {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(CredentialSecurityFactoryDAOImpl.class);

    /** The Constant SCHEMA_NAME. */
    private static final String SCHEMA_NAME = "SEG02";

    /** The Constant PACKAGE_NAME. */
    private static final String PACKAGE_NAME = "PK_CONSULTA_SEGURIDAD";

    /** The Constant PROCEDURE_NAME. */
    private static final String PROCEDURE_NAME = "SP_CONSULTA_USUARIO_CLAVE";

    /** The Constant IN_PARAM_NAPLICACION. */
    private static final String IN_PARAM_NAPLICACION = "NAPLICACION";

    /** The Constant OUT_PARAM_RESULT. */
    private static final String OUT_PARAM_RESULT = "O_RESULT_SET";

    /** The property map. */
    @Autowired
    private PropertyMap propertyMap;

    /** The data source. */
    private UserCredentialsDataSourceAdapter dataSource;

    /** The seguridad ds. */
    private DataSource seguridadDs;

    /** The db seguridad user. */
    @Value("${DB.SEGURIDAD.USER}")
    private String dbSeguridadUser = "";

    /** The db seguridad password. */
    @Value("${DB.SEGURIDAD.PASSWORD}")
    private String dbSeguridadPasw = "";

    /** The time out. */
    @Value("${DB.TIMEOUT:30000}")
    private Long queryTimeout;

    /** The Constant FIELD_USER. */
    private static final int FIELD_USER = 1;

    /** The Constant FIELD_PASS. */
    private static final int FIELD_PASS = 2;

    /** The Constant FIELD_ERROR. */
    private static final int FIELD_ERROR = 3;

    /** The cs. */
    private CredentialSecurity cs;

    /** The credential map. */
    private Map<String, Credential> credentialMap = new HashMap<String, Credential>();

    /**
     * Constructor.
     *
     * @param dataSource
     *            the data source
     */
    public CredentialSecurityFactoryDAOImpl(DataSource dataSource) {
        this.seguridadDs = dataSource;

    }

    /**
     * Constructor.
     *
     * @param dataSource the data source
     * @param queryTimeout the query timeout
     */
    public CredentialSecurityFactoryDAOImpl(DataSource dataSource, long queryTimeout) {
        this.seguridadDs = dataSource;
        this.dataSource = new UserCredentialsDataSourceAdapter();
        this.dataSource.setTargetDataSource(seguridadDs);
        this.queryTimeout = queryTimeout;
    }

    /**
     * Gets the data source adapter.
     *
     * @return the data source adapter
     */
    @PostConstruct
    public void getDataSourceAdapter() {

        dataSource = new UserCredentialsDataSourceAdapter();
        dataSource.setTargetDataSource(seguridadDs);
        dataSource.setUsername(dbSeguridadUser);
        dataSource.setPassword(dbSeguridadPasw);

    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.base.dao.BaseDatoDAO#consultar(java.lang.String,
     * java.lang.String, java.lang.String,
     * org.springframework.jdbc.core.namedparam.SqlParameterSource,
     * org.springframework.jdbc.core.SqlParameter[])
     */
    @Override
    public Map<String, Object> consultar(String schemaName, String packageName, String procedureName,
            SqlParameterSource in, SqlParameter... parametros) throws SQLException {

        CustomJdbcTemplate jdbcTemplate = new CustomJdbcTemplate(dataSource);
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        Long milisegEnSegundos = TimeUnit.MILLISECONDS.toSeconds(queryTimeout);
        jdbcTemplate.setQueryTimeout(milisegEnSegundos.intValue());
        SimpleJdbcCall procRead = new SimpleJdbcCall(jdbcTemplate).withSchemaName(schemaName)
                .withCatalogName(packageName).withProcedureName(procedureName).declareParameters(parametros)
                .withoutProcedureColumnMetaDataAccess();
        return procRead.execute(in);
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.base.dao.BaseDatoDAO#consultarFuncion(java.lang.
     * String, java.lang.String, java.lang.String,
     * org.springframework.jdbc.core.namedparam.SqlParameterSource,
     * org.springframework.jdbc.core.SqlParameter[])
     */
    @Override
    public Map<String, Object> consultarFuncion(String schemaName, String packageName, String functionName,
            SqlParameterSource in, SqlParameter... parametros) throws SQLException {
        throw new SQLException("No permitida la funcion en este punto.");
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.base.dao.BaseDatoDAO#insertTable(java.lang.String,
     * java.lang.String,
     * org.springframework.jdbc.core.namedparam.MapSqlParameterSource)
     */
    @Override
    public void insertTable(String schemaName, String tableName, MapSqlParameterSource in) throws SQLException {
        throw new SQLException("No permitida la funcion en este punto.");
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.base.security.credential.
     * CredentialSecurityFactory#create()
     */
    @Override
    public CredentialSecurity create() {
        this.cs = new CredentialSecurityImpl();
        Map<String, Object> allData = propertyMap.getProperties();
        for (DataBase database : DataBase.values()) {
            if (DataBase.SEGURIDAD.equals(database)) {
                continue;
            }
            String value = (String) allData.get(database.getIdBaseDatos());

            Credential credential = null;
            try {
                credential = getCredentialById(value);
            } catch (Exception e) {
                // Evaluar que hacer con ORA-01017: invalid
                // username/password; logon denied
                LOGGER.error("No fue posible generar las credenciales para el id: " + database.getIdBaseDatos(), e);
            }
            if (credential != null) {
                if (allData.get(database.getDataSource()) != null) {
                    String jndi = (String) allData.get(database.getDataSource());
                    if (StringUtils.isNotBlank(credential.getJndi())) {
                        LOGGER.info(
                                "ATENCION!!! SE PISA EL JNDI ACTUAL {} CON {} DEL DATASOURCE {}, VALIDAR QUE EL ID {} EN LAS PROPS ESTE CORRECTO Y NO REPETIDO.",
                                credential.getJndi(), jndi, database.getNombreTarget(), value);
                    }
                    credential.setJndi(jndi);
                }
                cs.addCredential(database.getNombreTarget(), credential);
            }
        }

        return cs;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.base.security.credential.
     * CredentialSecurityFactory#getCredentialBySistema(java.lang.String)
     */
    @Override
    public Credential getCredentialBySistema(String sistema) throws SQLException {
        Map<String, Object> allData = propertyMap.getProperties();
        Object oId = allData.get(DataBase.getDataBasePorSistema(sistema).getIdBaseDatos());
        if (oId == null) {
            LOGGER.debug("No se encuentra el sistema :{} en la configuracion", sistema);
            return null;
        }
        String id = oId.toString();
        return getCredentialById(id);
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.base.security.credential.
     * CredentialSecurityFactory#getCredentialById(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    @Override
    public Credential getCredentialById(String dbId) throws SQLException {
        Credential credencial = credentialMap.get(dbId);
        if (credencial != null) {
            return credencial;
        } else if (System.getenv("sec.id." + dbId + ".user") != null 
        		&& System.getenv("sec.id." + dbId + ".pass") != null) {
        	LOGGER.info("Obteniendo credencial desde Secret para la entrada {}.", dbId);
        	Credential credential = new Credential();
            credential.setUsuario(System.getenv("sec.id." + dbId + ".user"));
            credential.setPassword(System.getenv("sec.id." + dbId + ".pass"));
            credentialMap.put(dbId, credential);
            return credential; 
        } else {
            LOGGER.info("Se procede a consultar la base de seguridad con la entrada {}.", dbId);
        }
        List<SqlParameter> parametros = new ArrayList<SqlParameter>();
        parametros.add(new SqlParameter(IN_PARAM_NAPLICACION, Types.NUMERIC));
        parametros.add(new SqlOutParameter(OUT_PARAM_RESULT, OracleTypes.CURSOR,
                new ResultSetExtractor<Map<Integer, String>>() {
                    @Override
                    public Map<Integer, String> extractData(ResultSet rs) throws SQLException {
                        Map<Integer, String> valores = new HashMap<Integer, String>();

                        while (rs.next()) {
                            if (rs.getString(FIELD_USER) != null) {
                                valores.put(FIELD_USER, rs.getString(FIELD_USER));
                            }
                            if (rs.getString(FIELD_PASS) != null) {
                                valores.put(FIELD_PASS, rs.getString(FIELD_PASS));
                            }
                            if (rs.getString(FIELD_USER) == null || rs.getString(FIELD_PASS) == null) {
                                throw new SQLException(rs.getString(FIELD_ERROR));
                            }
                        }
                        return valores;
                    }
                }));

        SqlParameterSource in = new MapSqlParameterSource().addValue(IN_PARAM_NAPLICACION, dbId);
        Map<String, Object> respuesta = consultar(SCHEMA_NAME, PACKAGE_NAME, PROCEDURE_NAME, in,
                parametros.toArray(new SqlParameter[parametros.size()]));

        Map<Integer, String> datosCredencial = (Map<Integer, String>) respuesta.get(OUT_PARAM_RESULT);
        if (datosCredencial != null && !CollectionUtils.isEmpty(datosCredencial)) {
            Credential credential = new Credential();
            credential.setPassword(datosCredencial.get(FIELD_PASS));
            credential.setUsuario(datosCredencial.get(FIELD_USER));
            credentialMap.put(dbId, credential);
            return credential;
        }
        LOGGER.info("Se consulto a la base de seguridad y no hay respuesta.");
        return null;
    }

}
