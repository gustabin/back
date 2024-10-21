/**
 * 
 */
package ar.com.santanderrio.obp.servicios.debitoautomatico.tarjeta.dao.impl;

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
import ar.com.santanderrio.obp.servicios.debitoautomatico.tarjeta.dao.DebitoAutomaticoTarjetaDAO;
import ar.com.santanderrio.obp.servicios.debitoautomatico.tarjeta.entities.ClienteDebitoTarjetaInEntity;
import ar.com.santanderrio.obp.servicios.debitoautomatico.tarjeta.entities.SolicitudAdhesionDebitoTarjetaEntity;

/**
 * The Class DebitoAutomaticoTarjetaDAOImpl.
 *
 * @author florencia.n.martinez
 */
@Repository
@TargetSystem(DataBase.PORTAL)
public class DebitoAutomaticoTarjetaDAOImpl extends BaseDatoDAOImpl implements DebitoAutomaticoTarjetaDAO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(DebitoAutomaticoTarjetaDAOImpl.class);

	/** The Constant ADHESION_DEBITO_TARJETA_PROCEDURE. */
	private static final String ADHESION_DEBITO_TARJETA_PROCEDURE = "SP_Ins_AdhDebitoAutomatico";

	/** The Constant CONSULTA_PROCEDURE. */
	private static final String CONSULTA_PROCEDURE = "Se consultara el procedure {}.";

	/** The Constant IN_ADHESION_DEBITO_TARJETA_TIPO_DOCUMENTO. */
	private static final String IN_ADHESION_DEBITO_TARJETA_TIPO_DOCUMENTO = "tipoDocumento";

	/** The Constant IN_ADHESION_DEBITO_TARJETA_NRO_DOCUMENTO. */
	private static final String IN_ADHESION_DEBITO_TARJETA_NRO_DOCUMENTO = "nroDocumento";

	/** The Constant IN_ADHESION_DEBITO_TARJETA_NOMBRE. */
	private static final String IN_ADHESION_DEBITO_TARJETA_NOMBRE = "nombre";

	/** The Constant IN_ADHESION_DEBITO_TARJETA_APELLIDO. */
	private static final String IN_ADHESION_DEBITO_TARJETA_APELLIDO = "apellido";

	/** The Constant IN_ADHESION_DEBITO_TARJETA_NRO_NUP. */
	private static final String IN_ADHESION_DEBITO_TARJETA_NRO_NUP = "nro_nup";

	/** The Constant IN_ADHESION_DEBITO_TARJETA_NRO_CUENTA_VISA. */
	private static final String IN_ADHESION_DEBITO_TARJETA_NRO_CUENTA_VISA = "nroCuentaVisa";

	/** The Constant IN_ADHESION_DEBITO_TARJETA_NRO_TARJETA_VISA. */
	private static final String IN_ADHESION_DEBITO_TARJETA_NRO_TARJETA_VISA = "nroTarjetaVisa";

	/** The Constant IN_ADHESION_DEBITO_TARJETA_RUBRO. */
	private static final String IN_ADHESION_DEBITO_TARJETA_RUBRO = "rubro";

	/** The Constant IN_ADHESION_DEBITO_TARJETA_EMPRESA_SERVICIO. */
	private static final String IN_ADHESION_DEBITO_TARJETA_EMPRESA_SERVICIO = "empresaServicio";

	/** The Constant IN_ADHESION_DEBITO_TARJETA_IDENTIFICADOR. */
	private static final String IN_ADHESION_DEBITO_TARJETA_IDENTIFICADOR = "identificador";

	/** The Constant IN_ADHESION_DEBITO_TARJETA_COD_AREA_TELEFONO. */
	private static final String IN_ADHESION_DEBITO_TARJETA_COD_AREA_TELEFONO = "codAreaTelefono";

	/** The Constant IN_ADHESION_DEBITO_TARJETA_NRO_TELEFONO. */
	private static final String IN_ADHESION_DEBITO_TARJETA_NRO_TELEFONO = "nroTelefono";

	/** The Constant IN_ADHESION_DEBITO_TARJETA_MONTO_MAXIMO. */
	private static final String IN_ADHESION_DEBITO_TARJETA_MONTO_MAXIMO = "montoMaximo";

	/** The Constant IN_ADHESION_DEBITO_TARJETA_EMAIL. */
	private static final String IN_ADHESION_DEBITO_TARJETA_EMAIL = "email";

	/** The Constant IN_ADHESION_DEBITO_TARJETA_FEC_NACIMIENTO. */
	private static final String IN_ADHESION_DEBITO_TARJETA_FEC_NACIMIENTO = "fechaNacimiento";

	/** The Constant OUT_ADHESION_DEBITO_TARJETA_IDE_SOLICITUD. */
	private static final String OUT_ADHESION_DEBITO_TARJETA_IDE_SOLICITUD = "p_IdeSolicitud";

	/** The Constant ESPACIO_STRING. */
	private static final String ESPACIO_STRING = " ";

	/** The Constant ADHESION_DEBITO_TARJETA_PACKAGE. */
	private static final String ADHESION_DEBITO_TARJETA_PACKAGE = "PK_VBPRINTER";

	/** The Constant ADHESION_DEBITO_TARJETA_SCHEMA. */
	private static final String ADHESION_DEBITO_TARJETA_SCHEMA = "PORTAL";

	/** The Constant COD_RETORNO_ERROR. */
	private static final String COD_RETORNO_ERROR = "0";

	/** The Constant MENSAJE_ERROR_EN_STORE. */
	private static final String MENSAJE_ERROR_EN_STORE = "Error en store {}.{}.{}. al solicitar la adhesion a debito automatico en tarjeta de {}.";

	/** The Constant ERROR_CONSULTA_STORE. */
	private static final String ERROR_CONSULTA_STORE = "Error al consultar el store ";

	/** The Constant ADHESION_OK. */
	private static final String ADHESION_OK = "Se registro la adhesion del debito automatico en tarjeta de {} correctamente.";

	/** The Constant ERROR_EN_BASE. */
	private static final String ERROR_EN_BASE = "Error en base del store {}.{}.{} al solicitar la adhesion a debito automatico en tarjeta de {}.";

	/** The Constant ERROR_TIMEOUT. */
	private static final String ERROR_TIMEOUT = "Error de Timeout.";

	/**
	 * Solicita la adhesion de un servicio a la tarjeta de credito.
	 *
	 * @param datosClienteDebito
	 *            the datos cliente debito
	 * @return the solicitud adhesion debito tarjeta entity
	 * @throws DAOException
	 *             the DAO exception
	 * @throws QueryTimeoutException
	 *             the query timeout exception
	 */
	@Override
	public SolicitudAdhesionDebitoTarjetaEntity solicitarAdhesionTarjeta(
			ClienteDebitoTarjetaInEntity datosClienteDebito) throws DAOException, QueryTimeoutException {
		LOGGER.info(CONSULTA_PROCEDURE, ADHESION_DEBITO_TARJETA_PROCEDURE);
		List<SqlParameter> parametros = new ArrayList<SqlParameter>();
		parametros.add(new SqlParameter(IN_ADHESION_DEBITO_TARJETA_TIPO_DOCUMENTO, Types.VARCHAR));
		parametros.add(new SqlParameter(IN_ADHESION_DEBITO_TARJETA_NRO_DOCUMENTO, Types.VARCHAR));
		parametros.add(new SqlParameter(IN_ADHESION_DEBITO_TARJETA_NOMBRE, Types.VARCHAR));
		parametros.add(new SqlParameter(IN_ADHESION_DEBITO_TARJETA_APELLIDO, Types.VARCHAR));
		parametros.add(new SqlParameter(IN_ADHESION_DEBITO_TARJETA_NRO_NUP, Types.VARCHAR));
		parametros.add(new SqlParameter(IN_ADHESION_DEBITO_TARJETA_NRO_CUENTA_VISA, Types.VARCHAR));
		parametros.add(new SqlParameter(IN_ADHESION_DEBITO_TARJETA_NRO_TARJETA_VISA, Types.VARCHAR));
		parametros.add(new SqlParameter(IN_ADHESION_DEBITO_TARJETA_RUBRO, Types.VARCHAR));
		parametros.add(new SqlParameter(IN_ADHESION_DEBITO_TARJETA_EMPRESA_SERVICIO, Types.VARCHAR));
		parametros.add(new SqlParameter(IN_ADHESION_DEBITO_TARJETA_IDENTIFICADOR, Types.VARCHAR));
		parametros.add(new SqlParameter(IN_ADHESION_DEBITO_TARJETA_COD_AREA_TELEFONO, Types.VARCHAR));
		parametros.add(new SqlParameter(IN_ADHESION_DEBITO_TARJETA_NRO_TELEFONO, Types.VARCHAR));
		parametros.add(new SqlParameter(IN_ADHESION_DEBITO_TARJETA_MONTO_MAXIMO, Types.VARCHAR));
		parametros.add(new SqlParameter(IN_ADHESION_DEBITO_TARJETA_EMAIL, Types.VARCHAR));
		parametros.add(new SqlParameter(IN_ADHESION_DEBITO_TARJETA_FEC_NACIMIENTO, Types.VARCHAR));
		parametros.add(new SqlOutParameter(OUT_ADHESION_DEBITO_TARJETA_IDE_SOLICITUD, Types.NUMERIC));

		SqlParameterSource in = new MapSqlParameterSource()
				.addValue(IN_ADHESION_DEBITO_TARJETA_TIPO_DOCUMENTO, datosClienteDebito.getCliente().getTipoDocumento())
				.addValue(IN_ADHESION_DEBITO_TARJETA_NRO_DOCUMENTO, datosClienteDebito.getCliente().getDni())
				.addValue(IN_ADHESION_DEBITO_TARJETA_NOMBRE, datosClienteDebito.getCliente().getNombre())
				.addValue(IN_ADHESION_DEBITO_TARJETA_APELLIDO,
						datosClienteDebito.getCliente().getApellido1().concat(ESPACIO_STRING)
								.concat(datosClienteDebito.getCliente().getApellido2()).trim())
				.addValue(IN_ADHESION_DEBITO_TARJETA_NRO_NUP, datosClienteDebito.getCliente().getNup())
				.addValue(IN_ADHESION_DEBITO_TARJETA_NRO_CUENTA_VISA, datosClienteDebito.getNroCuentaVisa())
				.addValue(IN_ADHESION_DEBITO_TARJETA_NRO_TARJETA_VISA, datosClienteDebito.getNroTarjetaVisa())
				.addValue(IN_ADHESION_DEBITO_TARJETA_RUBRO, datosClienteDebito.getRubro())
				.addValue(IN_ADHESION_DEBITO_TARJETA_EMPRESA_SERVICIO, datosClienteDebito.getEmpresaServicio())
				.addValue(IN_ADHESION_DEBITO_TARJETA_IDENTIFICADOR, datosClienteDebito.getIdentificador())
				.addValue(IN_ADHESION_DEBITO_TARJETA_COD_AREA_TELEFONO, datosClienteDebito.getCodAreaTelefono())
				.addValue(IN_ADHESION_DEBITO_TARJETA_NRO_TELEFONO, datosClienteDebito.getNroTelefono())
				.addValue(IN_ADHESION_DEBITO_TARJETA_MONTO_MAXIMO, datosClienteDebito.getMontoMaximo())
				.addValue(IN_ADHESION_DEBITO_TARJETA_EMAIL, datosClienteDebito.getEmail())
				.addValue(IN_ADHESION_DEBITO_TARJETA_FEC_NACIMIENTO,
						datosClienteDebito.getCliente().getFechaNacimiento());

		try {
			Map<String, Object> respuesta = consultar(ADHESION_DEBITO_TARJETA_SCHEMA, ADHESION_DEBITO_TARJETA_PACKAGE,
					ADHESION_DEBITO_TARJETA_PROCEDURE, in, parametros.toArray(new SqlParameter[parametros.size()]));
			BigDecimal codRetorno = (BigDecimal) respuesta.get(OUT_ADHESION_DEBITO_TARJETA_IDE_SOLICITUD);

			if (!COD_RETORNO_ERROR.equals(codRetorno.toString())) {
				LOGGER.info(ADHESION_OK, datosClienteDebito);
				return new SolicitudAdhesionDebitoTarjetaEntity(codRetorno);
			} else {
				LOGGER.info(MENSAJE_ERROR_EN_STORE, ADHESION_DEBITO_TARJETA_SCHEMA, ADHESION_DEBITO_TARJETA_PACKAGE,
						ADHESION_DEBITO_TARJETA_PROCEDURE, datosClienteDebito);
				throw new DAOException(ERROR_CONSULTA_STORE.concat(ADHESION_DEBITO_TARJETA_PROCEDURE));
			}
		} catch (DataAccessResourceFailureException dae) {
			LOGGER.error(ERROR_EN_BASE, ADHESION_DEBITO_TARJETA_SCHEMA, ADHESION_DEBITO_TARJETA_PACKAGE,
					ADHESION_DEBITO_TARJETA_PROCEDURE, datosClienteDebito, dae);
			throw new DAOException(dae);
		} catch (SQLException sqle) {
			LOGGER.error(MENSAJE_ERROR_EN_STORE, ADHESION_DEBITO_TARJETA_SCHEMA, ADHESION_DEBITO_TARJETA_PACKAGE,
					ADHESION_DEBITO_TARJETA_PROCEDURE, datosClienteDebito, sqle);
			throw new DAOException(sqle);
		} catch (UncategorizedSQLException ex) {
			LOGGER.error(ERROR_EN_BASE, ADHESION_DEBITO_TARJETA_SCHEMA, ADHESION_DEBITO_TARJETA_PACKAGE,
					ADHESION_DEBITO_TARJETA_PROCEDURE, datosClienteDebito, ex);
			throw new DAOException(ex);
		} catch (InvalidDataAccessApiUsageException invalidData) {
			LOGGER.error(MENSAJE_ERROR_EN_STORE, ADHESION_DEBITO_TARJETA_SCHEMA, ADHESION_DEBITO_TARJETA_PACKAGE,
					ADHESION_DEBITO_TARJETA_PROCEDURE, datosClienteDebito, invalidData);
			throw new DAOException(invalidData);
		} catch (IllegalStateException iex) {
			LOGGER.error(MENSAJE_ERROR_EN_STORE, ADHESION_DEBITO_TARJETA_SCHEMA, ADHESION_DEBITO_TARJETA_PACKAGE,
					ADHESION_DEBITO_TARJETA_PROCEDURE, datosClienteDebito, iex);
			throw new DAOException(iex);
		} catch (QueryTimeoutException qte) {
			LOGGER.error(ERROR_TIMEOUT, ADHESION_DEBITO_TARJETA_SCHEMA, ADHESION_DEBITO_TARJETA_PACKAGE,
					ADHESION_DEBITO_TARJETA_PROCEDURE, datosClienteDebito, qte);
			throw new QueryTimeoutException(qte.getMessage());
		}
	}

}
