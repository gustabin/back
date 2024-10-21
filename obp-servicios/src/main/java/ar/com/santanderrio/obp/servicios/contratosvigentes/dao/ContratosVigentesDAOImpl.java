package ar.com.santanderrio.obp.servicios.contratosvigentes.dao;

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
import ar.com.santanderrio.obp.servicios.perfil.entities.ProductoContratoEntity;
import oracle.jdbc.OracleTypes;

@Repository
@TargetSystem(DataBase.ESTADISTICAS)
public class ContratosVigentesDAOImpl extends BaseDatoDAOImpl implements ContratosVigentesDAO {

	private static final String OUT_PRODUCTO = "p_cursor";
	private static final String OUT_RESULTADO = "p_resultado";
	private static final String OUT_ERROR_TECNICO = "p_err_tecnico";
	private static final String OUT_ERROR_AMIGABLE = "p_err_amigable";
	private static final String SCHEMA_NAME = "hbank";
	private static final String PACKAGE_NAME = "HB_PKG_LISTA_PRODUCTOS_CONT";
	private static final String PROCEDURE_LISTAR_PRODUCTOS = "prc_listar_productos";
	private static final String RESULTADO_OK = "0";
	private static final Logger LOGGER = LoggerFactory.getLogger(ContratosVigentesDAOImpl.class);
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ProductoContratoEntity> obtenerProductosContratos() throws DAOException {
		
		List<ProductoContratoEntity> productoBonificado = new ArrayList<ProductoContratoEntity>();
 
		try {
            List<SqlParameter> parametros = new ArrayList<SqlParameter>();

            parametros.add(new SqlOutParameter(OUT_PRODUCTO, OracleTypes.CURSOR,
                    new BeanPropertyRowMapper<ProductoContratoEntity>(ProductoContratoEntity.class)));

    		parametros.add(new SqlOutParameter(OUT_RESULTADO, Types.VARCHAR));
    		parametros.add(new SqlOutParameter(OUT_ERROR_TECNICO, Types.VARCHAR));
    		parametros.add(new SqlOutParameter(OUT_ERROR_AMIGABLE, Types.VARCHAR));

            SqlParameterSource in = new MapSqlParameterSource();
            
            Map<String, Object> respuesta = consultar(SCHEMA_NAME, PACKAGE_NAME, PROCEDURE_LISTAR_PRODUCTOS, in,
					parametros.toArray(new SqlParameter[parametros.size()]));

            
            String codRetorno = (String) respuesta.get(OUT_RESULTADO);

            if (RESULTADO_OK.equals(codRetorno)) {   
            	productoBonificado = (ArrayList<ProductoContratoEntity>) respuesta.get(OUT_PRODUCTO);
            	LOGGER.info("devolviendo {} productos", productoBonificado.size());
				if (!productoBonificado.isEmpty()) {
					return productoBonificado;
				} else {
					LOGGER.error("Error buscando productos");
					throw new DAOException("no se encuentran productos");
				}
            } else {
            	LOGGER.error("Error buscando productos");
            	throw new DAOException("no se encuentran productos");
            }
            
        } catch (SQLException e) {
            throw new DAOException(e);
        } catch (UncategorizedSQLException e) {
            throw new DAOException(e);
        } catch (InvalidDataAccessApiUsageException e) {
            throw new DAOException(e);
        }
		
	}

	
	
}
