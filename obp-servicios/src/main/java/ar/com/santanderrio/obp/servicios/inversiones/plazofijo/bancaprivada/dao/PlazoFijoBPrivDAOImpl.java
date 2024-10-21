/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.plazofijo.bancaprivada.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.dao.impl.BaseDatoDAOImpl;
import ar.com.santanderrio.obp.base.security.aop.annotation.TargetSystem;
import ar.com.santanderrio.obp.base.security.credential.DataBase;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.bancaprivada.entity.ConsultaPlazoFijoBPrivInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.bancaprivada.entity.ConsultaPlazoFijoBPrivOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.CuentaRossiOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.DetallePFInteresanteInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.FrecuenciaCobroPFInteresanteOutEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.exceptions.SimulacionDAOException;
import oracle.jdbc.OracleTypes;

/**
 * The Class PlazoFijoBPrivDAOImpl.
 *
 * @author juan.pablo.picate
 */
@Component
@TargetSystem(DataBase.BPRIV)
public class PlazoFijoBPrivDAOImpl extends BaseDatoDAOImpl implements PlazoFijoBPrivDAO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(PlazoFijoBPrivDAOImpl.class);

	/** The Constant PARAM_CURSOR. */
	private static final String PARAM_CURSOR = "p_cursor";

	/** cuenta. */
	private static final String IN_CUENTA = "P_CUENTA";

	/** tipo pf. */
	private static final String IN_TIPOPF = "P_TIPO_PF";

	/** codigo del fondo seleccionado. */
	private static final String IN_FECHA = "P_FECHAVALOR";

	/** SCHEMA. */
	private static final String CONTRATO_SCHEMA = "BCAINV";

	/** PACKAGE contrato. */
	private static final String CONTRATO_PACKAGE = "PKG_BP_ONLINE_BANKING";

	/** PROCEDURE CONSULTAR. */
	private static final String CONTRATO_CONSULTAR = "load_plazofijo";
	
    /** PROCEDURE GET_CUENTA_ROSSI. */
    private static final String GET_CUENTA_ROSSI = "GET_CUENTA_ROSSI";
	
    /** The Constant IN_CUENTA_ALTAIR. */
    private static final String IN_CUENTA_ALTAIR = "P_CUENTA_ALTAIR";
    
    /** The Constant OUT_CNO. */
    private static final String OUT_CNO = "P_CNO";
    
    /** The Constant OUT_SUCURSAL_ROSSI. */
    private static final String OUT_SUCURSAL_ROSSI = "P_SUCURSALROSSI";

    /** The Constant OUT_SUCURSAL_RADICACION. */
    private static final String OUT_SUCURSAL_RADICACION = "P_SUCURSALRADICACION";

	/** The Constant OUT_COD_RETORNO. */
	private static final String OUT_RETORNO = "P_RETORNO";

	/** The Constant OUT_COD_RETORNO. */
	private static final String OUT_COD_RETORNO = "P_COD_RETORNO";

	/** The Constant OUT_DESCRIPCION. */
	private static final String OUT_DESCRIPCION = "P_DESCRIPCION";

	/** The Constant COD_RETORNO_OK. */
	private static final String COD_RETORNO_OK = "OK";

	/** The Constant OUT_NUM_ORDEN. */
	private static final String OUT_NUM_ORDEN = "p_num_orden";

	/** The Constant OUT_COD_SIST. */
	private static final String OUT_COD_SIST = "p_cod_sist";

	/** The Constant OUT_COD_SIST. */
	private static final String OUT_TIPO_ORD = "p_tipo_ord";

	/** The Constant OUT_MONE_LIQ. */
	private static final String OUT_MONE_LIQ = "p_mone_liq";

	/** The Constant OUT_FECHA_ORDEN. */
	private static final String OUT_FECHA_ORDEN = "p_fecha_orden";

	/** The Constant OUT_FECHA_LIQ. */
	private static final String OUT_FECHA_LIQ = "p_fecha_liq";

	/** The Constant OUT_CAPITAL. */
	private static final String OUT_CAPITAL = "p_capital";

	/** The Constant OUT_HORA_EMISION. */
	private static final String OUT_HORA_EMISION = "p_hora_emision";

	/** The Constant OUT_TNA. */
	private static final String OUT_TNA = "p_tna";

	/** The Constant OUT_TOT_A_COBR_VTO. */
	private static final String OUT_TOT_A_COBR_VTO = "p_tot_a_cobr_vto";

	/** The Constant OUT_IMPUESTOS. */
	private static final String OUT_IMPUESTOS = "p_impuestos";

	/** The Constant OUT_COD_CERTIFICADO. */
	private static final String OUT_COD_CERTIFICADO = "p_cod_certificado";

	/** The Constant OUT_CERTIFICADO. */
	private static final String OUT_CERTIFICADO = "p_certificado";

	/** The Constant OUT_SUC_RADIC. */
	private static final String OUT_SUC_RADIC = "p_suc_radic";

	/** The Constant OUT_COD_ACCION_VTO. */
	private static final String OUT_COD_ACCION_VTO = "p_cod_accion_vto";

	/** The Constant OUT_ACCION_VTO. */
	private static final String OUT_ACCION_VTO = "p_accion_vto";

	/** The Constant OUT_PZO_MIN_PRE. */
	private static final String OUT_PZO_MIN_PRE = "p_pzo_min_pre";

	/** The Constant OUT_PORCENT_PENALI. */
	private static final String OUT_PORCENT_PENALI = "p_porcent_penali";

	/** The Constant OUT_PLAZO. */
	private static final String OUT_PLAZO = "p_plazo";

	/** The Constant OUT_INTERES. */
	private static final String OUT_INTERES = "p_interes";

	/** The Constant OUT_INT_DEVENG_ESPEC. */
	private static final String OUT_INT_DEVENG_ESPEC = "p_int_deveng_espec";

	/** The Constant OUT_CDSECIDX. */
	private static final String OUT_CDSECIDX = "cdsecidx";

	/** The Constant OUT_NBSECIDX. */
	private static final String OUT_NBSECIDX = "nbsecidx";

	/** The Constant OUT_FHOPERAC. */
	private static final String OUT_FHOPERAC = "fhoperac";

	/** The Constant OUT_IMINTBON. */
	private static final String OUT_IMINTBON = "imintbon";

	/** The Constant OUT_PCCOTORI. */
	private static final String OUT_PCCOTORI = "pccotori";

	/** The Constant OUT_CDCONDIC. */
	private static final String OUT_CDCONDIC = "cdcondic";

	/** The Constant OUT_CDPRODUC. */
	private static final String OUT_CDPRODUC = "cdproduc";

	/** The Constant OUT_DEVENGADO. */
	private static final String OUT_DEVENGADO = "devengado";

	/** The Constant OUT_FECHADEVENGADO. */
	private static final String OUT_FECHADEVENGADO = "fechadevengado";

	/** The Constant OUT_AJUSTE. */
	private static final String OUT_AJUSTE = "ajuste";

	/** The Constant OUT_INT_VAR_DEVENG_DIVA. */
	private static final String OUT_INT_VAR_DEVENG_DIVA = "int_var_deveng_diva";

	/** The Constant OUT_INT_MEN_VAR_DEVENG_DIVA. */
	private static final String OUT_INT_MEN_VAR_DEVENG_DIVA = "int_men_var_deveng_diva";

	/** The Constant PROBLEMA_GENERAL. */
	private static final String PROBLEMA_GENERAL = "20240";

	/** The Constant CUENTA_FECHA_INCORRECTA. */
	private static final String CUENTA_FECHA_INCORRECTA = "20241";

	/** The Constant ERROR_CNO. */
	private static final String ERROR_CNO = "20242";

	/** The Constant ERROR_CNO. */
	private static final String ERROR_CONSULTA_PF = "20243";
	
	/** The Constant IN_NUMERO_ORDEN. */
	private static final String IN_NUMERO_ORDEN = "P_NUM_ORDEN";

	/** The Constant IN_USUARIO. */
	private static final String IN_USUARIO = "P_USUARIO";

	/** The Constant IN_PASSWORD. */
	private static final String IN_PASSWORD = "P_PASSWORD";

	/** The Constant DETALLE_INTERESANTE_SCHEMA. */
	private static final String DETALLE_INTERESANTE_SCHEMA = "BCAINV";
	
	/** The Constant DETALLE_INTERESANTE_PACKAGE. */
	private static final String DETALLE_INTERESANTE_PACKAGE = "PKG_BP_ONLINE_BANKING";
	
	/** The Constant LOAD_PF_INTERES_PERIODICO. */
	private static final String LOAD_PF_INTERES_PERIODICO = "load_pf_interes_periodico";

	/** The Constant MAX_DIGITOS_NRO_CUENTA. */
	private static final int MAX_DIGITOS_NRO_CUENTA = 7;
	

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ConsultaPlazoFijoBPrivOutEntity> obtenerPlazosFijo(ConsultaPlazoFijoBPrivInEntity consultarInEntity)
			throws DAOException {
		List<SqlParameter> parametros = new ArrayList<SqlParameter>();
		parametros.add(new SqlParameter(IN_CUENTA, Types.VARCHAR));
		parametros.add(new SqlParameter(IN_TIPOPF, Types.VARCHAR));
		parametros.add(new SqlParameter(IN_FECHA, Types.DATE));
		parametros.add(new SqlOutParameter(OUT_RETORNO, Types.VARCHAR));
		parametros.add(new SqlOutParameter(OUT_COD_RETORNO, Types.INTEGER));
		parametros.add(new SqlOutParameter(OUT_DESCRIPCION, Types.VARCHAR));
		parametros.add(new SqlOutParameter(PARAM_CURSOR, OracleTypes.CURSOR));
		
		parametros.add(
				new SqlOutParameter(PARAM_CURSOR, OracleTypes.CURSOR, new RowMapper<ConsultaPlazoFijoBPrivOutEntity>() {
					@Override
					public ConsultaPlazoFijoBPrivOutEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
						ConsultaPlazoFijoBPrivOutEntity consultaPF = new ConsultaPlazoFijoBPrivOutEntity();
						consultaPF.setNumOrden(rs.getString(OUT_NUM_ORDEN));
						consultaPF.setCodSist(rs.getString(OUT_COD_SIST));
						consultaPF.setTipoOrd(rs.getString(OUT_TIPO_ORD));
						consultaPF.setMonedaLiq(rs.getString(OUT_MONE_LIQ));
						consultaPF.setFechaOrden(rs.getString(OUT_FECHA_ORDEN));
						consultaPF.setFechaLiq(rs.getString(OUT_FECHA_LIQ));
						consultaPF.setCapital(rs.getString(OUT_CAPITAL));
						consultaPF.setHoraEmision(rs.getString(OUT_HORA_EMISION));
						consultaPF.setTna(rs.getString(OUT_TNA));
						consultaPF.setTotalACobrarVencimiento(rs.getString(OUT_TOT_A_COBR_VTO));
						consultaPF.setImpuestos(rs.getString(OUT_IMPUESTOS));
						consultaPF.setCodCertificado(rs.getString(OUT_COD_CERTIFICADO));
						consultaPF.setCertificado(rs.getString(OUT_CERTIFICADO));
						consultaPF.setSucRadicacion(rs.getString(OUT_SUC_RADIC));
						consultaPF.setCodigoAccionVencimiento(rs.getString(OUT_COD_ACCION_VTO));
						consultaPF.setAccionVencimiento(rs.getString(OUT_ACCION_VTO));
						consultaPF.setPlazoMinimoPrecancelacion(rs.getString(OUT_PZO_MIN_PRE));
						consultaPF.setPorcentajePenalizacion(rs.getString(OUT_PORCENT_PENALI));
						consultaPF.setPlazo(rs.getString(OUT_PLAZO));
						consultaPF.setInteres(rs.getString(OUT_INTERES));
						consultaPF.setInteresDevengamientoEspecial(rs.getString(OUT_INT_DEVENG_ESPEC));
						consultaPF.setCdsecidx(rs.getString(OUT_CDSECIDX));
						consultaPF.setNbsecidx(rs.getString(OUT_NBSECIDX));
						consultaPF.setFechaOperacion(rs.getString(OUT_FHOPERAC));
						consultaPF.setImintbon(rs.getString(OUT_IMINTBON));
						consultaPF.setPccotori(rs.getString(OUT_PCCOTORI));
						consultaPF.setCdcondic(rs.getString(OUT_CDCONDIC));
						consultaPF.setCdproduc(rs.getString(OUT_CDPRODUC));
						consultaPF.setDevengado(rs.getString(OUT_DEVENGADO));
						consultaPF.setFechaDevengado(rs.getString(OUT_FECHADEVENGADO));
						consultaPF.setAjuste(rs.getString(OUT_AJUSTE));
						consultaPF.setIntVarDevengDiva(rs.getString(OUT_INT_VAR_DEVENG_DIVA));
						consultaPF.setIntMenVarDevengDiva(rs.getString(OUT_INT_MEN_VAR_DEVENG_DIVA));
						return consultaPF;
					}

				}));
		parametros.add(new SqlOutParameter(OUT_RETORNO, Types.VARCHAR));

		SqlParameterSource in = new MapSqlParameterSource().addValue(IN_CUENTA, consultarInEntity.getCuenta())
				.addValue(IN_TIPOPF, consultarInEntity.getTipoPF()).addValue(IN_FECHA, consultarInEntity.getFecha());
		try {
			Map<String, Object> respuesta = consultar(CONTRATO_SCHEMA, CONTRATO_PACKAGE, CONTRATO_CONSULTAR, in,
					parametros.toArray(new SqlParameter[parametros.size()]));
			String retorno = (String) respuesta.get(OUT_RETORNO);
			String codRetorno = (String) respuesta.get(OUT_COD_RETORNO);
			String descError = (String) respuesta.get(OUT_DESCRIPCION);
			if (COD_RETORNO_OK.equals(retorno)) {
				List<ConsultaPlazoFijoBPrivOutEntity> consultaPF = (List<ConsultaPlazoFijoBPrivOutEntity>) respuesta
						.get(PARAM_CURSOR);
				return consultaPF;
			} else {
				manejarCasosAlternativos(codRetorno, descError);
				throw new DAOException();
			}
		} catch (Exception exception) {
			LOGGER.error(exception.getMessage(), exception);
			throw new DAOException(exception);
		}
	}

	/**
	 * 
	 * Manejo de errores.
	 *
	 * @param codRetorno
	 *            the cod retorno
	 * @param descError
	 *            the desc error
	 */
	private void manejarCasosAlternativos(String codRetorno, String descError) {
		if (PROBLEMA_GENERAL.equals(codRetorno)) {

		}
		if (CUENTA_FECHA_INCORRECTA.equals(codRetorno)) {

		}
		if (ERROR_CNO.equals(codRetorno)) {

		}
		if (ERROR_CONSULTA_PF.equals(codRetorno)) {

		}
	}
	
	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.plazofijo.bancaprivada.dao.PlazoFijoBPrivDAO#obtenerDetallePFInteresanteBP(ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.DetallePFInteresanteInEntity)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<FrecuenciaCobroPFInteresanteOutEntity> obtenerDetallePFInteresanteBP(DetallePFInteresanteInEntity detalleIn) throws DAOException {

		List<FrecuenciaCobroPFInteresanteOutEntity> listaFrecuencias = new ArrayList<FrecuenciaCobroPFInteresanteOutEntity>();
		
		List<SqlParameter> parametros = new ArrayList<SqlParameter>();
		parametros.add(new SqlParameter(IN_NUMERO_ORDEN, Types.VARCHAR));
		parametros.add(new SqlParameter(IN_USUARIO, Types.VARCHAR));
		parametros.add(new SqlParameter(IN_PASSWORD, Types.VARCHAR));
		parametros.add(new SqlOutParameter(OUT_RETORNO, Types.VARCHAR));
		parametros.add(new SqlOutParameter(OUT_COD_RETORNO, Types.INTEGER));
		parametros.add(new SqlOutParameter(OUT_DESCRIPCION, Types.VARCHAR));
		parametros.add(new SqlOutParameter(PARAM_CURSOR, OracleTypes.CURSOR));
		
		SqlParameterSource in = new MapSqlParameterSource().addValue(IN_NUMERO_ORDEN, detalleIn.getNumeroOrden())
														   .addValue(IN_USUARIO, detalleIn.getUsuarioRacf())
														   .addValue(IN_PASSWORD, detalleIn.getPasswordRacf());
		
		try {
			Map<String, Object> respuesta = consultar(DETALLE_INTERESANTE_SCHEMA, DETALLE_INTERESANTE_PACKAGE, LOAD_PF_INTERES_PERIODICO, in,
					parametros.toArray(new SqlParameter[parametros.size()]));
			
			String codRetorno = (String) respuesta.get(OUT_RETORNO);
			
			if (COD_RETORNO_OK.equals(codRetorno)) {
				List<Object> frecuenciaCobroPFInteresante = (List<Object>) respuesta.get(PARAM_CURSOR);
				for (int i = 0; i < frecuenciaCobroPFInteresante.size(); i++) {
					listaFrecuencias.add(crearFrecuenciaCobroPF((Map<String, Object>) frecuenciaCobroPFInteresante.get(i)));
				}
			} else {
				throw new DAOException((String) respuesta.get(OUT_DESCRIPCION));
			}
		} catch (SQLException e) {
			LOGGER.error("Error al consumir el store {}.{}.{}",
					DETALLE_INTERESANTE_SCHEMA, DETALLE_INTERESANTE_PACKAGE, LOAD_PF_INTERES_PERIODICO);
			throw new DAOException(e);
		}
		return listaFrecuencias;
	}
	
	
	/**
	 * Crear frecuencia cobro PF.
	 *
	 * @param map
	 *            the map
	 * @return the frecuencia cobro PF interesante out entity
	 */
	private FrecuenciaCobroPFInteresanteOutEntity crearFrecuenciaCobroPF(Map<String, Object> map) {
		
		String fechaLiquidacion = "FECHA_ULTIMA_LIQ";
		String capital = "CAPITAL";
		String intereses = "INTERESES";
		String impuesto = "IMPUESTO";
		String total = "TOTAL";
		
		FrecuenciaCobroPFInteresanteOutEntity frecuencia = new FrecuenciaCobroPFInteresanteOutEntity();
		frecuencia.setFechaLiquidacion(map.get(fechaLiquidacion).toString());
		frecuencia.setCapital(map.get(capital).toString());
		frecuencia.setIntereses(map.get(intereses).toString());
		frecuencia.setImpuestos(map.get(impuesto).toString());
		frecuencia.setTotal(map.get(total).toString());
		
		return frecuencia;
	}
	
	/**
	 * Obtener Cuenta Rossi
	 * 
	 * @param nroCuenta
	 * 					the nro cuenta
	 * @return the cuenta Rossi Out Entity
	 */
	@Override
	public CuentaRossiOutEntity getCuentaRossi (String nroCuenta) throws DAOException {
		
		CuentaRossiOutEntity cuentaRossiOutEntity = new CuentaRossiOutEntity();
		List<SqlParameter> parametros = new ArrayList<SqlParameter>();
		
		if (StringUtils.length(nroCuenta) > MAX_DIGITOS_NRO_CUENTA) {
			nroCuenta = StringUtils.right(nroCuenta, MAX_DIGITOS_NRO_CUENTA);
		}
		
	    parametros.add(new SqlParameter(IN_CUENTA_ALTAIR, Types.VARCHAR));
        parametros.add(new SqlOutParameter((String) OUT_CNO, Types.VARCHAR));
        parametros.add(new SqlOutParameter((String) OUT_SUCURSAL_ROSSI, Types.VARCHAR));
        parametros.add(new SqlOutParameter((String) OUT_SUCURSAL_RADICACION, Types.VARCHAR));
        parametros.add(new SqlOutParameter((String) OUT_RETORNO, Types.VARCHAR));
        parametros.add(new SqlOutParameter((String) OUT_COD_RETORNO, Types.INTEGER));
        parametros.add(new SqlOutParameter((String) OUT_DESCRIPCION, Types.VARCHAR));
        
        SqlParameterSource in = new MapSqlParameterSource().addValue(IN_CUENTA_ALTAIR, nroCuenta);
        
        try {
			Map<String, Object> respuesta = consultar(CONTRATO_SCHEMA, CONTRATO_PACKAGE, GET_CUENTA_ROSSI, in,
			        parametros.toArray(new SqlParameter[parametros.size()]));
			 	String retorno = (String) respuesta.get(OUT_RETORNO);
	            LOGGER.info(OUT_RETORNO + " : " + retorno);
	            if (COD_RETORNO_OK.equals(retorno)) {
	            	cuentaRossiOutEntity = parsearRespuestaCuentaRossi(respuesta);
	            } else {
	            	throw new DAOException();
	            }
	            
	        } catch (SQLException sqle) {
	            manejarErrorInesperado(sqle, GET_CUENTA_ROSSI);
	        } catch (UncategorizedSQLException sqle) {
	            manejarErrorInesperado(sqle, GET_CUENTA_ROSSI);
	        }
        
		return cuentaRossiOutEntity;
		
		
	}
	
	 /**
	 * Arma respuesta de la Cuenta Rossi. Se Solicita en ID 7739 en mail del 12/12/2019: dejar la descripcion con 30 espacios vacios.
	 * En el futuro se va a implementar en Retail tambien.
	 * @param respuesta
	 * @return the cuenta Rossi Out Entity
	 */
	private CuentaRossiOutEntity parsearRespuestaCuentaRossi(Map<String, Object> respuesta) {
		 CuentaRossiOutEntity cuentaRossiOutEntity = new CuentaRossiOutEntity();
		 
		 Integer codRetorno;
		 
		 cuentaRossiOutEntity.setCNO((String) respuesta.get(OUT_CNO));
		 cuentaRossiOutEntity.setSucursalRossi((String) respuesta.get(OUT_SUCURSAL_ROSSI));
		 cuentaRossiOutEntity.setSucursalRadicacion((String) respuesta.get(OUT_SUCURSAL_RADICACION));
		 cuentaRossiOutEntity.setRetorno((String) respuesta.get(OUT_RETORNO));
		 codRetorno = (Integer) respuesta.get(OUT_COD_RETORNO);
		 cuentaRossiOutEntity.setCodRetorno(String.valueOf(codRetorno));
		 cuentaRossiOutEntity.setDescripcion(StringUtils.repeat(ISBANStringUtils.ESPACIO_STRING, 30));
		 
		 return cuentaRossiOutEntity;
	}

	private void manejarErrorInesperado(Exception sqle, String contrato) throws DAOException {
	        LOGGER.error("Error al consumir el store {}.{}.{} ", CONTRATO_SCHEMA, CONTRATO_PACKAGE, contrato, sqle);
	        throw new SimulacionDAOException(sqle);
	  }

	
}
