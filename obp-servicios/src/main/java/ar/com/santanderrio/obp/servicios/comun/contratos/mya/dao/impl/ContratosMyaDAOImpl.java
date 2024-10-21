/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.contratos.mya.dao.impl;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.*;

import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.dao.impl.BaseDatoDAOImpl;
import ar.com.santanderrio.obp.base.security.aop.annotation.TargetSystem;
import ar.com.santanderrio.obp.base.security.credential.DataBase;
import ar.com.santanderrio.obp.servicios.comun.contratos.mya.dao.ContratosMyaDAO;
import ar.com.santanderrio.obp.servicios.comun.contratos.mya.entity.ConsultaCliente;
import ar.com.santanderrio.obp.servicios.comun.contratos.mya.entity.ConsultaClienteParam;
import ar.com.santanderrio.obp.servicios.comun.contratos.mya.entity.ContratoParam;
import ar.com.santanderrio.obp.servicios.comun.contratos.mya.entity.RegistroClienteParam;
import oracle.jdbc.OracleTypes;

/**
 * The Class ContratosMyaDAOImpl.
 */
@Repository
@TargetSystem(DataBase.ESTADISTICAS)
public class ContratosMyaDAOImpl extends BaseDatoDAOImpl implements ContratosMyaDAO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ContratosMyaDAOImpl.class);

	/** Constante COD_RESULTADO_OK. */
	private static final String COD_RESULTADO_OK = "0";

	/** The Constant CONST_CANAL_ACTIVACION. */
	private static final String CONST_CANAL_ACTIVACION = "R";

	/** The Constant CONST_CANAL_VENTA. */
	private static final String CONST_CANAL_VENTA = "A";

	/** The Constant CONST_CANAL_VENTA. */
	private static final String CONST_ACEPTACION = "1";

	/** SCHEMA. */
	private static final String MYA_SCHEMA = "hbank";

	/** PACKAGE contrato. */
	// private static final String MYA_PACKAGE = "HB_PKG_CONTRATOS";
	private static final String MYA_PACKAGE = "HB_PKG_ESTADISTICAS";

	/** PROCEDURE consultar clientes. */
	private static final String CONSULTAR_CLIENTES_PROCEDURE = "prc_consultar_clientes";

	/** The Constant IN_CONSULTAR_CLIENTES_DNI. */
	private static final String IN_CONSULTAR_CLIENTES_DNI = "p_dni";

	/** The Constant IN_CONSULTAR_CLIENTES_FECHA_NACIMIENTO. */
	private static final String IN_CONSULTAR_CLIENTES_FECHA_NACIMIENTO = "p_fecha_nac";

	/** The Constant IN_CONSULTAR_CLIENTES_NUP. */
	private static final String IN_CONSULTAR_CLIENTES_NUP = "p_nup";

	/** The Constant OUT_CONSULTAR_CLIENTES_CURSOR. */
	private static final String OUT_CONSULTAR_CLIENTES_CURSOR = "p_cursor";

	/** PROCEDURE crear registro. */
	private static final String CREAR_REGISTRO_PROCEDURE = "prc_crear_registro";

	/** The Constant IN_CREAR_REGISTRO_DNI. */
	private static final String IN_CREAR_REGISTRO_DNI = "p_dni";

	/** The Constant IN_CREAR_REGISTRO_FECHA_NAC. */
	private static final String IN_CREAR_REGISTRO_FECHA_NAC = "p_fecha_nac";

	/** The Constant IN_CREAR_REGISTRO_NOMBRE. */
	private static final String IN_CREAR_REGISTRO_NOMBRE = "p_nombre";

	/** The Constant IN_CREAR_REGISTRO_APELLIDO. */
	private static final String IN_CREAR_REGISTRO_APELLIDO = "p_apellido";

	/** The Constant IN_CREAR_REGISTRO_FECHA_ALTA. */
	private static final String IN_CREAR_REGISTRO_FECHA_ALTA = "p_fecha_alta";

	/** The Constant IN_CREAR_REGISTRO_CANAL_VENTA. */
	private static final String IN_CREAR_REGISTRO_CANAL_VENTA = "p_canal_venta";

	/** The Constant IN_CREAR_REGISTRO_FECHA_INICIO. */
	private static final String IN_CREAR_REGISTRO_FECHA_INICIO = "p_fecha_inicio";

	/** The Constant IN_CREAR_REGISTRO_SUCURSAL_ORIGEN. */
	private static final String IN_CREAR_REGISTRO_SUCURSAL_ORIGEN = "p_suc_origen";

	/** The Constant IN_CREAR_REGISTRO_ALTA_REGISTRO. */
	private static final String IN_CREAR_REGISTRO_ALTA_REGISTRO = "p_alta_reg";

	/** The Constant IN_CREAR_REGISTRO_ACEPTACION. */
	private static final String IN_CREAR_REGISTRO_ACEPTACION = "p_aceptacion";

	/** The Constant IN_CREAR_REGISTRO_CANAL_ACTIV. */
	private static final String IN_CREAR_REGISTRO_CANAL_ACTIV = "p_canal_activ";

	/** The Constant IN_CREAR_REGISTRO_NUP. */
	private static final String IN_CREAR_REGISTRO_NUP = "p_nup";

	/** The Constant OUT_CREAR_REGISTRO_SALIDA. */
	private static final String OUT_CREAR_REGISTRO_SALIDA = "p_salida";

	/** PROCEDURE crear contrato. */
	private static final String ACTUALIZAR_CONTRATO_PROCEDURE = "prc_actualizar_contrato";

	/** The Constant IN_ACTUALIZAR_CONTRATO_DNI. */
	private static final String IN_ACTUALIZAR_CONTRATO_DNI = "p_dni";

	/** The Constant IN_ACTUALIZAR_CONTRATO_FECHA_NAC. */
	private static final String IN_ACTUALIZAR_CONTRATO_FECHA_NAC = "p_fecha_nac";

	/** The Constant IN_ACTUALIZAR_CONTRATO_NUP. */
	private static final String IN_ACTUALIZAR_CONTRATO_NUP = "p_nup";

	/** The Constant IN_ACTUALIZAR_CONTRATO_FECHA_ACTUAL. */
	private static final String IN_ACTUALIZAR_CONTRATO_FECHA_ACTUAL = "p_fecha_actual";

	/** The Constant OUT_ACTUALIZAR_CONTRATO_FECHA_P_SALIDA. */
	private static final String OUT_ACTUALIZAR_CONTRATO_FECHA_P_SALIDA = "p_salida";

	@Autowired
	private SesionParametros sesionParametros;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.comun.contratos.mya.dao.ContratosMyaDAO
	 * #actualizarContrato(ar.com.santanderrio.obp.servicios.comun.contratos.mya
	 * .entity.ContratoParam)
	 */
	@Override
	public boolean actualizarContrato(ContratoParam contrato) throws DAOException {
		LOGGER.info("Se consultara el procedure {} con el contrato {}.", ACTUALIZAR_CONTRATO_PROCEDURE, contrato);
		boolean retorno = false;
		List<SqlParameter> parametros = new ArrayList<SqlParameter>();
		parametros.add(new SqlParameter(IN_ACTUALIZAR_CONTRATO_DNI, Types.VARCHAR));
		parametros.add(new SqlParameter(IN_ACTUALIZAR_CONTRATO_FECHA_NAC, Types.VARCHAR));
		parametros.add(new SqlParameter(IN_ACTUALIZAR_CONTRATO_NUP, Types.VARCHAR));
		parametros.add(new SqlParameter(IN_ACTUALIZAR_CONTRATO_FECHA_ACTUAL, Types.VARCHAR));
		parametros.add(new SqlOutParameter(OUT_ACTUALIZAR_CONTRATO_FECHA_P_SALIDA, Types.NUMERIC));

		SqlParameterSource in = new MapSqlParameterSource().addValue(IN_ACTUALIZAR_CONTRATO_DNI, contrato.getDni())
				.addValue(IN_ACTUALIZAR_CONTRATO_FECHA_NAC, contrato.getFechaNacimiento())
				.addValue(IN_ACTUALIZAR_CONTRATO_NUP, contrato.getNup())
				.addValue(IN_ACTUALIZAR_CONTRATO_FECHA_ACTUAL, buildFechaActual());
		try {
			Map<String, Object> respuesta = consultar(MYA_SCHEMA, MYA_PACKAGE, ACTUALIZAR_CONTRATO_PROCEDURE, in,
					parametros.toArray(new SqlParameter[parametros.size()]));
			BigDecimal codRetorno = (BigDecimal) respuesta.get(OUT_CREAR_REGISTRO_SALIDA);
			LOGGER.info("Procedure ejecutado {} salida {}.", ACTUALIZAR_CONTRATO_PROCEDURE, codRetorno);
			if (!COD_RESULTADO_OK.equals(codRetorno.toString())) {
				LOGGER.info("Error en store {}.{}.{}. al actualizar el registro< {}.", MYA_SCHEMA, MYA_PACKAGE,
						ACTUALIZAR_CONTRATO_PROCEDURE, contrato);
				throw new DAOException("Error al actualizar el registro en el store " + ACTUALIZAR_CONTRATO_PROCEDURE);
			} else {
				retorno = true;
				LOGGER.info("Se actualizo el registro {} correctamente.", contrato);
			}
		} catch (SQLException sqle) {
			LOGGER.error("Error en store {}.{}.{} al actualizar el contrato {}.", MYA_SCHEMA, MYA_PACKAGE,
					ACTUALIZAR_CONTRATO_PROCEDURE, contrato, sqle);
			throw new DAOException(sqle);
		} catch (RuntimeException ex) {
			LOGGER.error("Error en base del store {}.{}.{} al actualizar el contrato {}.", MYA_SCHEMA, MYA_PACKAGE,
					ACTUALIZAR_CONTRATO_PROCEDURE, contrato, ex);
			throw new DAOException(ex);
		}
		return retorno;

	}

	/**
	 * Builds the fecha actual.
	 *
	 * @return the string
	 */
	private String buildFechaActual() {
		SimpleDateFormat fmt = new SimpleDateFormat("dd-MM-yyyy");
		String dateFormatted = fmt.format(new Date());
		return dateFormatted;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.comun.contratos.mya.dao.ContratosMyaDAO
	 * #crearRegistro(ar.com.santanderrio.obp.servicios.comun.contratos.mya.
	 * entity.RegistroClienteParam)
	 */
	@Override
	public boolean crearRegistro(RegistroClienteParam registroCliente) throws DAOException {
		LOGGER.info("Se consultara el procedure {} con el contrato {}.", CREAR_REGISTRO_PROCEDURE, registroCliente);
		List<SqlParameter> parametros = new ArrayList<SqlParameter>();
		boolean retorno = false;

		Date fecha = new Date();
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		String fechaHoy = format.format(fecha);

		parametros.add(new SqlParameter(IN_CREAR_REGISTRO_DNI, Types.VARCHAR));
		parametros.add(new SqlParameter(IN_CREAR_REGISTRO_FECHA_NAC, Types.VARCHAR));
		parametros.add(new SqlParameter(IN_CREAR_REGISTRO_NOMBRE, Types.VARCHAR));
		parametros.add(new SqlParameter(IN_CREAR_REGISTRO_APELLIDO, Types.VARCHAR));
		parametros.add(new SqlParameter(IN_CREAR_REGISTRO_FECHA_ALTA, Types.VARCHAR));// ?
		parametros.add(new SqlParameter(IN_CREAR_REGISTRO_CANAL_VENTA, Types.VARCHAR));// R
		parametros.add(new SqlParameter(IN_CREAR_REGISTRO_FECHA_INICIO, Types.VARCHAR));// ?
		parametros.add(new SqlParameter(IN_CREAR_REGISTRO_SUCURSAL_ORIGEN, Types.VARCHAR));
		parametros.add(new SqlParameter(IN_CREAR_REGISTRO_ALTA_REGISTRO, Types.VARCHAR));
		parametros.add(new SqlParameter(IN_CREAR_REGISTRO_ACEPTACION, Types.VARCHAR));
		parametros.add(new SqlParameter(IN_CREAR_REGISTRO_CANAL_ACTIV, Types.VARCHAR));
		parametros.add(new SqlParameter(IN_CREAR_REGISTRO_NUP, Types.VARCHAR));
		parametros.add(new SqlOutParameter(OUT_CREAR_REGISTRO_SALIDA, Types.NUMERIC));

		SqlParameterSource in = new MapSqlParameterSource().addValue(IN_CREAR_REGISTRO_DNI, registroCliente.getDni())
				.addValue(IN_CREAR_REGISTRO_FECHA_NAC, registroCliente.getFechaNacimiento())
				.addValue(IN_CREAR_REGISTRO_NOMBRE, registroCliente.getNombre())
				.addValue(IN_CREAR_REGISTRO_APELLIDO, registroCliente.getApellido())
				.addValue(IN_CREAR_REGISTRO_FECHA_ALTA, fechaHoy)
				.addValue(IN_CREAR_REGISTRO_CANAL_VENTA, CONST_CANAL_VENTA)
				.addValue(IN_CREAR_REGISTRO_FECHA_INICIO, fechaHoy)
				.addValue(IN_CREAR_REGISTRO_SUCURSAL_ORIGEN, registroCliente.getSucursalOrigen())
				.addValue(IN_CREAR_REGISTRO_ALTA_REGISTRO, fechaHoy)
				.addValue(IN_CREAR_REGISTRO_ACEPTACION, CONST_ACEPTACION)
				.addValue(IN_CREAR_REGISTRO_CANAL_ACTIV, CONST_CANAL_ACTIVACION)
				.addValue(IN_CREAR_REGISTRO_NUP, registroCliente.getNup())
				.addValue(IN_CREAR_REGISTRO_FECHA_NAC, registroCliente.getFechaNacimiento());
		try {
			Map<String, Object> respuesta = consultar(MYA_SCHEMA, MYA_PACKAGE, CREAR_REGISTRO_PROCEDURE, in,
					parametros.toArray(new SqlParameter[parametros.size()]));
			BigDecimal codRetorno = (BigDecimal) respuesta.get(OUT_CREAR_REGISTRO_SALIDA);
			LOGGER.info("Procedure ejecutado {} salida {}.", CREAR_REGISTRO_PROCEDURE, codRetorno);
			if (!COD_RESULTADO_OK.equals(codRetorno.toString())) {
				LOGGER.info("Error en store {}.{}.{}. al crear el registro< {}.", MYA_SCHEMA, MYA_PACKAGE,
						CREAR_REGISTRO_PROCEDURE, registroCliente);
				throw new DAOException("Error al crear el registro en el store " + CREAR_REGISTRO_PROCEDURE);
			} else {
				retorno = true;
				LOGGER.info("Se registro el registro {} correctamente.", registroCliente);
			}
		} catch (SQLException sqle) {
			LOGGER.error("Error en store {}.{}.{} al crear el registro {}.", MYA_SCHEMA, MYA_PACKAGE,
					CREAR_REGISTRO_PROCEDURE, registroCliente, sqle);
			throw new DAOException(sqle);
		} catch (RuntimeException ex) {
			LOGGER.error("Error en base del store {}.{}.{} al crear el registro {}.", MYA_SCHEMA, MYA_PACKAGE,
					CREAR_REGISTRO_PROCEDURE, registroCliente, ex);
			throw new DAOException(ex);
		}
		return retorno;
	}

	/**
	 * Metodo que invoca al store de consultaClientes.
	 *
	 * @param consultaClienteParam
	 *            the consulta cliente param
	 * @return the consulta cliente
	 * @throws DAOException
	 *             the DAO exception
	 */
	public ConsultaCliente consultaClientes(ConsultaClienteParam consultaClienteParam) throws DAOException {
		LOGGER.info("Se consultara el procedure {} con el contrato {}.", CONSULTAR_CLIENTES_PROCEDURE,
				consultaClienteParam);
		List<SqlParameter> parametros = new ArrayList<SqlParameter>();
		ConsultaCliente consultaCliente = new ConsultaCliente();
		parametros.add(new SqlParameter(IN_CONSULTAR_CLIENTES_DNI, Types.VARCHAR));
		parametros.add(new SqlParameter(IN_CONSULTAR_CLIENTES_FECHA_NACIMIENTO, Types.VARCHAR));
		parametros.add(new SqlParameter(IN_CONSULTAR_CLIENTES_NUP, Types.VARCHAR));
		parametros.add(new SqlOutParameter(OUT_CONSULTAR_CLIENTES_CURSOR, OracleTypes.CURSOR, new RowMapper<String>() {
			@Override
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getString(1);
			}
		}));

		SqlParameterSource in = new MapSqlParameterSource()
				.addValue(IN_CONSULTAR_CLIENTES_DNI, consultaClienteParam.getDni())
				.addValue(IN_CONSULTAR_CLIENTES_FECHA_NACIMIENTO, consultaClienteParam.getFechaNacimiento())
				.addValue(IN_CONSULTAR_CLIENTES_NUP, consultaClienteParam.getNup());
		try {
			Map<String, Object> respuesta = consultar(MYA_SCHEMA, MYA_PACKAGE, CONSULTAR_CLIENTES_PROCEDURE, in,
					parametros.toArray(new SqlParameter[parametros.size()]));
			List<String> data = (List<String>) respuesta.get(OUT_CONSULTAR_CLIENTES_CURSOR);
			LOGGER.info("Procedure ejecutado {} salida {}.", CONSULTAR_CLIENTES_PROCEDURE, data);
			if (data.size() > 0) {
				consultaCliente.setAceptacionContrato(data.get(0));
				/*
				 * consultaCliente.setFechaInicioUso(data.get(1));
				 * consultaCliente.setFechaNacimiento(data.get(2));
				 * consultaCliente.setCanalActivacion(data.get(3));
				 */
			}  else {
				LOGGER.info("No hay valores retornados para el store {} con dni {}, fecha {}, nup {}.",
						CONSULTAR_CLIENTES_PROCEDURE, consultaClienteParam.getDni(),
						consultaClienteParam.getFechaNacimiento(), consultaClienteParam.getNup());
				consultaCliente.setAceptacionContrato("0");
				sesionParametros.setExisteClienteTyC(Boolean.TRUE);
			}
		} catch (SQLException sqle) {
			LOGGER.error("Error en store {}.{}.{} al consultar clientes {}.", MYA_SCHEMA, MYA_PACKAGE,
					CONSULTAR_CLIENTES_PROCEDURE, consultaCliente, sqle);
			throw new DAOException(sqle);
		} catch (RuntimeException ex) {
			LOGGER.error("Error en base del store {}.{}.{} al consultar clientes {}.", MYA_SCHEMA, MYA_PACKAGE,
					CONSULTAR_CLIENTES_PROCEDURE, consultaCliente, ex);
			throw new DAOException(ex);
		}
		return consultaCliente;
	}

}
