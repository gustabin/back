/*
 * 
 */
package ar.com.santanderrio.obp.servicios.prestamos.dao.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.dao.impl.BaseDatoDAOImpl;
import ar.com.santanderrio.obp.base.security.aop.annotation.TargetSystem;
import ar.com.santanderrio.obp.base.security.credential.DataBase;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.pagos.entities.ConsultaPrestamoTasaCeroEntity;
import ar.com.santanderrio.obp.servicios.pagos.entities.ConsultaPrestamoTasaCeroOutEntity;
import ar.com.santanderrio.obp.servicios.pagos.entities.PrestamoTasaCeroConfigEntity;
import ar.com.santanderrio.obp.servicios.prestamos.dao.PrestamoTasaCeroDAO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.ConfirmarPrestamoTasaCeroInDTO;
import oracle.jdbc.OracleTypes;

/**
 * The Class PrestamoTasaCeroDAOImpl.
 */
@Component
@Repository
@TargetSystem(DataBase.HBOBP)
public class PrestamoTasaCeroDAOImpl extends BaseDatoDAOImpl implements PrestamoTasaCeroDAO {	

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(PrestamoTasaCeroDAOImpl.class);

	/** The Constant PRESTAMOS_TASA_CERO_SCHEMA. */
    private static final String PRESTAMOS_TASA_CERO_SCHEMA = "BBANK";
    
    /** The Constant PRESTAMOS_TASA_CERO_PACKAGE. */
    private static final String PRESTAMOS_TASA_CERO_PACKAGE = "PKG_PRESTAMO_TARJETAS"; 
    
    /** The Constant PRESTAMOS_TASA_CERO_INSERTAR. */
    private static final String PRESTAMOS_TASA_CERO_INSERTAR = "INSERTAR_PRESTAMO";

    /** The Constant PRESTAMOS_TASA_CERO_CONSULTAR. */
    private static final String PRESTAMOS_TASA_CERO_CONSULTAR = "CONSULTAR_CLIENTE";

    /** The Constant PRESTAMOS_TASA_CERO_CONSULTAR_PRESTAMO. */
    private static final String PRESTAMOS_TASA_CERO_CONSULTAR_PRESTAMO = "CONSULTAR_PRESTAMO";

    /** The Constant OUT_RESULTADO. */
    private static final String OUT_RESULTADO = "R_CURSOR";

    /** The Constant OUT_COD_RET. */
    private static final String OUT_COD_RET = "R_COD_RET";

    /** The Constant OUT_DESC_RET. */
    private static final String OUT_DESC_RET = "R_DESC_RET";
    
    /** The Constant IN_NUP. */
    private static final String IN_NUP = "P_NUP";
    
    /** The Constant IN_CUIT. */
    private static final String IN_CUIT = "P_CUIT";
    
    /** The Constant IN_P_NUP_FIRMANTE. */
    private static final String IN_P_NUP_FIRMANTE = "P_NUP_FIRMANTE";
    
    /** The Constant P_NUP_FIRMANTE. */
    private static final String P_NUP_FIRMANTE = "";
    
    /** The Constant IN_P_TIPO_SOLICITUD. */
    private static final String IN_P_TIPO_SOLICITUD = "P_TIPO_SOLICITUD";
    
    /** The Constant P_TIPO_SOLICITUD. */
    private static final String P_TIPO_SOLICITUD = "";
    
    /** The Constant IN_P_TARJETA. */
    private static final String IN_P_TARJETA = "P_TARJETA";
    
    /** The Constant IN_P_TIPO_OPERACION. */
    private static final String IN_P_TIPO_OPERACION = "P_TIPO_OPERACION";
    
    /** The Constant P_TIPO_OPERACION. */
    private static final String P_TIPO_OPERACION = "A";
    
    /** The Constant IN_P_EMAIL. */
    private static final String IN_P_EMAIL = "P_EMAIL";
    
    /** The Constant IN_P_TELEFONO. */
    private static final String IN_P_TELEFONO = "P_TELEFONO";
    
    /** The Constant IN_P_DATOS_EXTRA. */
    private static final String IN_P_DATOS_EXTRA = "P_DATOS_EXTRA";

    /** The Constant IN_P_DATOS_EXTRA. */
    private static final String IN_P_TIPO = "P_TIPO";

	/**
	 * Inicio prestamos tasa cero.
	 *
	 * @param cliente the cliente
	 * @return the prestamo tasa cero config entity
	 * @throws DAOException the DAO exception
	 */
	@SuppressWarnings("unchecked")
	@Override
	public PrestamoTasaCeroConfigEntity inicioPrestamosTasaCero(SesionCliente cliente) throws DAOException {

		String cuit = cliente.getCliente().getNroDocCnsDocXNup();
		Boolean habilitadoPrestamo = true;
        PrestamoTasaCeroConfigEntity prestamoConfig = new PrestamoTasaCeroConfigEntity();
		List<ConsultaPrestamoTasaCeroEntity> listConsultaPrestamoTasaCeroEntity = new ArrayList<ConsultaPrestamoTasaCeroEntity>();

    	try {
            List<SqlParameter> parametros = new ArrayList<SqlParameter>();
            parametros.add(new SqlParameter(IN_CUIT, OracleTypes.VARCHAR));            
            parametros.add(new SqlOutParameter(OUT_RESULTADO, OracleTypes.CURSOR,
                    new BeanPropertyRowMapper<ConsultaPrestamoTasaCeroEntity>(ConsultaPrestamoTasaCeroEntity.class)));
            parametros.add(new SqlOutParameter(OUT_COD_RET, OracleTypes.NUMBER));
            parametros.add(new SqlOutParameter(OUT_DESC_RET, OracleTypes.VARCHAR));

            SqlParameterSource in = new MapSqlParameterSource().addValue(IN_CUIT, cuit);
            Map<String, Object> respuesta = consultar(PRESTAMOS_TASA_CERO_SCHEMA, PRESTAMOS_TASA_CERO_PACKAGE, PRESTAMOS_TASA_CERO_CONSULTAR, in,
                    parametros.toArray(new SqlParameter[parametros.size()]));

            LOGGER.info("inicioPrestamosTasaCero: {} .", respuesta.get(OUT_COD_RET));
            BigDecimal codRetorno = (BigDecimal) respuesta.get(OUT_COD_RET);

			if (BigDecimal.ZERO.equals(codRetorno)) {
				listConsultaPrestamoTasaCeroEntity = (List<ConsultaPrestamoTasaCeroEntity>) respuesta.get(OUT_RESULTADO);
				if (CollectionUtils.isNotEmpty(listConsultaPrestamoTasaCeroEntity)) {
					for (ConsultaPrestamoTasaCeroEntity item : listConsultaPrestamoTasaCeroEntity) {
						prestamoConfig.setHabilitadoPrestamo(habilitadoPrestamo);
						prestamoConfig.setCuit_solicitante(item.getCuit_solicitante());
						prestamoConfig.setEmail(item.getEmail());
						prestamoConfig.setTarjeta(item.getTarjeta());
						prestamoConfig.setImporte_solicitado(item.getImporte_solicitado());
						prestamoConfig.setTipo(item.getTipo());
					}
				} else {
					habilitadoPrestamo = false;
					prestamoConfig.setHabilitadoPrestamo(habilitadoPrestamo);
				}					
			} else {
				habilitadoPrestamo = false;
				prestamoConfig.setHabilitadoPrestamo(habilitadoPrestamo);
			}

        } catch (SQLException e) {
            LOGGER.error("Error al consumir el store {}.{}.{}.", PRESTAMOS_TASA_CERO_SCHEMA, PRESTAMOS_TASA_CERO_PACKAGE, PRESTAMOS_TASA_CERO_CONSULTAR, e);
            throw new DAOException(e);
        } catch (UncategorizedSQLException e) {
            LOGGER.error("Error al consumir el store {}.{}.{}. errores internos.", PRESTAMOS_TASA_CERO_SCHEMA, PRESTAMOS_TASA_CERO_PACKAGE, PRESTAMOS_TASA_CERO_CONSULTAR, e);
            throw new DAOException(e);
        } catch (InvalidDataAccessApiUsageException e) {
            LOGGER.error("Error al consumir el store {}.{}.{}. no hay grants.", PRESTAMOS_TASA_CERO_SCHEMA, PRESTAMOS_TASA_CERO_PACKAGE, PRESTAMOS_TASA_CERO_CONSULTAR, e);
            throw new DAOException(e);
        }
		return prestamoConfig;
	}

	/**
	 * Consultar prestamos tasa cero.
	 *
	 * @param nup the nup
	 * @return true, if successful
	 * @throws DAOException the DAO exception
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean consultarPrestamosTasaCero(String nup) throws DAOException {
    	try {
            List<SqlParameter> parametros = new ArrayList<SqlParameter>();
            parametros.add(new SqlParameter(IN_NUP, OracleTypes.VARCHAR));            
            parametros.add(new SqlOutParameter(OUT_RESULTADO, OracleTypes.CURSOR,
                    new BeanPropertyRowMapper<ConsultaPrestamoTasaCeroOutEntity>(ConsultaPrestamoTasaCeroOutEntity.class)));
            parametros.add(new SqlOutParameter(OUT_COD_RET, OracleTypes.NUMBER));
            parametros.add(new SqlOutParameter(OUT_DESC_RET, OracleTypes.VARCHAR));

            SqlParameterSource in = new MapSqlParameterSource().addValue(IN_NUP, nup);
            Map<String, Object> respuesta = consultar(PRESTAMOS_TASA_CERO_SCHEMA, PRESTAMOS_TASA_CERO_PACKAGE, PRESTAMOS_TASA_CERO_CONSULTAR_PRESTAMO, in,
                    parametros.toArray(new SqlParameter[parametros.size()]));

            LOGGER.info("consultarPrestamosTasaCero: {} .", respuesta.get(OUT_COD_RET));
            BigDecimal codRetorno = (BigDecimal) respuesta.get(OUT_COD_RET);
			if (BigDecimal.ZERO.equals(codRetorno)) {
				List<ConsultaPrestamoTasaCeroOutEntity> listConsultaPrestamoTasaCeroOutEntity = (List<ConsultaPrestamoTasaCeroOutEntity>) respuesta.get(OUT_RESULTADO);
				if (CollectionUtils.isNotEmpty(listConsultaPrestamoTasaCeroOutEntity)) {
					return true;
				}
			}
			return false;
        } catch (SQLException e) {
            LOGGER.error("Error al consumir el store {}.{}.{}.", PRESTAMOS_TASA_CERO_SCHEMA, PRESTAMOS_TASA_CERO_PACKAGE, PRESTAMOS_TASA_CERO_CONSULTAR_PRESTAMO, e);
            throw new DAOException(e);
        } catch (UncategorizedSQLException e) {
            LOGGER.error("Error al consumir el store {}.{}.{}. errores internos.", PRESTAMOS_TASA_CERO_SCHEMA, PRESTAMOS_TASA_CERO_PACKAGE, PRESTAMOS_TASA_CERO_CONSULTAR_PRESTAMO, e);
            throw new DAOException(e);
        } catch (InvalidDataAccessApiUsageException e) {
            LOGGER.error("Error al consumir el store {}.{}.{}. no hay grants.", PRESTAMOS_TASA_CERO_SCHEMA, PRESTAMOS_TASA_CERO_PACKAGE, PRESTAMOS_TASA_CERO_CONSULTAR_PRESTAMO, e);
            throw new DAOException(e);
        }
	}

	/**
	 * Solicitar prestamos tasa cero.
	 *
	 * @param dto the dto
	 * @param cliente the cliente
	 * @return true, if successful
	 * @throws DAOException the DAO exception
	 */
	@Override
	public boolean solicitarPrestamosTasaCero(ConfirmarPrestamoTasaCeroInDTO dto, SesionCliente cliente) throws DAOException {		

		String cuit = cliente.getCliente().getNroDocCnsDocXNup();
		String nup = cliente.getCliente().getNup();		
		String nupFirmante = "";		
		String tipoSolicitud = dto.getTipoSolicitud();
		String tarjeta = dto.getTarjeta();
		String tipoOperacion = dto.getTipoOperacion();
		String mail = dto.getEmail();
		String telefono = dto.getCelular();
		String datosExtra = "";
    	try {
            List<SqlParameter> parametros = new ArrayList<SqlParameter>();
            parametros.add(new SqlParameter(IN_CUIT, OracleTypes.VARCHAR));
            parametros.add(new SqlParameter(IN_NUP, OracleTypes.VARCHAR));
            parametros.add(new SqlParameter(IN_P_NUP_FIRMANTE, OracleTypes.VARCHAR));
            parametros.add(new SqlParameter(IN_P_TIPO_SOLICITUD, OracleTypes.VARCHAR));
            parametros.add(new SqlParameter(IN_P_TARJETA, OracleTypes.VARCHAR));
            parametros.add(new SqlParameter(IN_P_TIPO_OPERACION, OracleTypes.VARCHAR));
            parametros.add(new SqlParameter(IN_P_EMAIL, OracleTypes.VARCHAR));
            parametros.add(new SqlParameter(IN_P_TELEFONO, OracleTypes.VARCHAR));
            parametros.add(new SqlParameter(IN_P_DATOS_EXTRA, OracleTypes.VARCHAR));
                                         
            parametros.add(new SqlOutParameter(OUT_COD_RET, OracleTypes.NUMBER));
            parametros.add(new SqlOutParameter(OUT_DESC_RET, OracleTypes.VARCHAR));

            SqlParameterSource in= new MapSqlParameterSource()
            					   .addValue(IN_CUIT, cuit)
            					   .addValue(IN_NUP, nup)
            					   .addValue(IN_P_NUP_FIRMANTE, nupFirmante)
            					   .addValue(IN_P_TIPO_SOLICITUD, tipoSolicitud)
            					   .addValue(IN_P_TARJETA, tarjeta)
            					   .addValue(IN_P_TIPO_OPERACION, tipoOperacion)
            					   .addValue(IN_P_EMAIL, mail)
            					   .addValue(IN_P_TELEFONO, telefono)
            					   .addValue(IN_P_DATOS_EXTRA, datosExtra);
            
            Map<String, Object> respuesta = consultar(PRESTAMOS_TASA_CERO_SCHEMA, PRESTAMOS_TASA_CERO_PACKAGE, PRESTAMOS_TASA_CERO_INSERTAR, in,
                    parametros.toArray(new SqlParameter[parametros.size()]));

            LOGGER.info("insertarPrestamoTasaCero: {} .", respuesta.get(OUT_COD_RET));
            BigDecimal codRetorno = (BigDecimal) respuesta.get(OUT_COD_RET);

			return BigDecimal.ZERO.equals(codRetorno);

        } catch (SQLException e) {
            LOGGER.error("Error al consumir el store {}.{}.{}.", PRESTAMOS_TASA_CERO_SCHEMA, PRESTAMOS_TASA_CERO_PACKAGE, PRESTAMOS_TASA_CERO_INSERTAR, e);
            throw new DAOException(e);
        } catch (UncategorizedSQLException e) {
            LOGGER.error("Error al consumir el store {}.{}.{}. errores internos.", PRESTAMOS_TASA_CERO_SCHEMA, PRESTAMOS_TASA_CERO_PACKAGE, PRESTAMOS_TASA_CERO_INSERTAR, e);
            throw new DAOException(e);
        } catch (InvalidDataAccessApiUsageException e) {
            LOGGER.error("Error al consumir el store {}.{}.{}. no hay grants.", PRESTAMOS_TASA_CERO_SCHEMA, PRESTAMOS_TASA_CERO_PACKAGE, PRESTAMOS_TASA_CERO_INSERTAR, e);
            throw new DAOException(e);
        }
	}

}
