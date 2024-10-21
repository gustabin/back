/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.cuentatitulo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
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
import ar.com.santanderrio.obp.servicios.inversiones.cuentatitulo.entity.CuentaBP;
import ar.com.santanderrio.obp.servicios.inversiones.cuentatitulo.entity.CuentaTituloInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.cuentatitulo.entity.CuentaTituloOutEntity;
import oracle.jdbc.OracleTypes;

/**
 * The Class CuentaTituloDAOImpl.
 *
 * @author marcelo.ruiz
 */
@Component
@TargetSystem(DataBase.BPRIV)
public class CuentaTituloDAOImpl extends BaseDatoDAOImpl implements CuentaTituloDAO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(CuentaTituloDAOImpl.class);

	/** PARAMETRO ENTRADA DNI. */
	private static final String IN_NUP = "P_NUP";

	/** SCHEMA. */
	private static final String CONTRATO_SCHEMA = "BCAINV";

	/** PACKAGE contrato. */
	private static final String CONTRATO_PACKAGE = "PKG_BP_ONLINE_BANKING";

	/** PROCEDURE load. */
	private static final String CONTRATO_LOAD_ATITS = "load_atits";

	/** Codigo estado resultado. */
	private static final String OUT_RETORNO = "P_RETORNO";

	/** nombre del parametro que referencia al cursor retornado. */
	private static final String OUT_COD_RETORNO = "COD_RETORNO";

	/** nombre del parametro que referencia al cursor retornado. */
	private static final String OUT_LOAD_ATITS_CURSOR = "P_CURSOR";

	/** nombre del parametro que referencia al cursor retornado. */
	private static final String OUT_DESCRIPCION = "P_DESCRIPCION";

	/** Constante COD_RETORNO_OK. */
	private static final String COD_RETORNO_OK = "OK";

	/**
	 * Ejecutar stored procedure busqueda.
	 *
	 * @param cuentaTituloInEntity
	 *            the cuenta titulo in entity
	 * @return the list
	 * @throws DAOException
	 *             the DAO exception
	 */
	@SuppressWarnings({ "unchecked" })
	public CuentaTituloOutEntity obtenerCuentasTitulo(CuentaTituloInEntity cuentaTituloInEntity) throws DAOException {

		CuentaTituloOutEntity rta = new CuentaTituloOutEntity();

		List<SqlParameter> parametros = new ArrayList<SqlParameter>();
		parametros.add(new SqlParameter(IN_NUP, Types.VARCHAR));
		parametros.add(new SqlOutParameter(OUT_RETORNO, Types.VARCHAR));
		parametros.add(new SqlOutParameter(OUT_COD_RETORNO, Types.INTEGER));
		parametros.add(new SqlOutParameter(OUT_DESCRIPCION, Types.VARCHAR));
		parametros.add(addSqlOutCursorParameter());

		SqlParameterSource in = new MapSqlParameterSource().addValue(IN_NUP,
				cuentaTituloInEntity.getCliente().getNup());

		try {
			Map<String, Object> respuesta = consultar(CONTRATO_SCHEMA, CONTRATO_PACKAGE, CONTRATO_LOAD_ATITS, in,
					parametros.toArray(new SqlParameter[parametros.size()]));

			String codRetorno = (String) respuesta.get(OUT_RETORNO);

			if (COD_RETORNO_OK.equals(codRetorno)) {
				List<CuentaBP> rtaTitulos = (List<CuentaBP>) respuesta.get(OUT_LOAD_ATITS_CURSOR);
				rta.relacionesOperativaTitulo(rtaTitulos);
			} else {
				throw new DAOException((String) respuesta.get(OUT_DESCRIPCION), codRetorno);
			}
		} catch (RuntimeException sqle) {
			LOGGER.error("Error al cargar las cuentas titulo. ", sqle);
			throw new DAOException("ERROR TEMPORAL");
		} catch (SQLException sqle) {
			LOGGER.error("Error al consumir el store {}.{}.{} con dni {}, fecha {}, campo {} y sinonimo {}.",
					CONTRATO_SCHEMA, CONTRATO_PACKAGE, CONTRATO_LOAD_ATITS, cuentaTituloInEntity.getCliente().getNup(),
					sqle);
		}
		return rta;
	}

	/**
	 * Adds the sql out cursor parameter.
	 *
	 * @return the sql parameter
	 */
	private SqlParameter addSqlOutCursorParameter() {
		return new SqlOutParameter(OUT_LOAD_ATITS_CURSOR, OracleTypes.CURSOR, new ResultSetExtractor<List<CuentaBP>>() {
			@Override
			public List<CuentaBP> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<CuentaBP> rtaTitulos = new ArrayList<CuentaBP>();
				while (rs.next()) {
					String cuentaBP = rs.getString("CUENTA_BP");
					String cuentaTIT = rs.getString("CUENTA_ATIT");
					rtaTitulos.add(new CuentaBP(cuentaTIT.toString(), cuentaBP.toString()));

				}
				return rtaTitulos;
			}
		});
	}

}
