/**
 * 
 */
package ar.com.santanderrio.obp.base.dao;

import java.sql.SQLException;
import java.util.Map;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

// TODO: Auto-generated Javadoc
/**
 * DAO base para las operaciones contra los store procedure que consume la app.
 * 
 * @author sergio.e.goldentair
 *
 */
public interface BaseDatoDAO {

    /**
     * Centralizar la ejecucion de store procedure y delegar el cierre de la
     * conexion a spring jdbc. No es keysensitive para los parametros.
     *
     * @param schemaName
     *            the schema name
     * @param packageName
     *            the package name
     * @param procedureName
     *            the procedure name
     * @param in
     *            listado de los parametros de entrada al store
     * @param parametros
     *            estructura de los parametros de entrada
     * @return mapa con el listado de objetos retornado.
     * @throws SQLException
     *             the SQL exception
     */
    Map<String, Object> consultar(String schemaName, String packageName, String procedureName, SqlParameterSource in,
            SqlParameter... parametros) throws SQLException;

    /**
     * Invocar funciones.
     *
     * @param schemaName
     *            the schema name
     * @param packageName
     *            the package name
     * @param functionName
     *            the function name
     * @param in
     *            the in
     * @param parametros
     *            the parametros
     * @return the map
     * @throws SQLException
     *             the SQL exception
     */
    Map<String, Object> consultarFuncion(String schemaName, String packageName, String functionName,
            SqlParameterSource in, SqlParameter... parametros) throws SQLException;

    /**
     * Insert table.
     *
     * @param schemaName the schema name
     * @param tableName the table name
     * @param in the in
     * @throws SQLException the SQL exception
     */
    void insertTable(String schemaName, String tableName, MapSqlParameterSource in) throws SQLException;
}
