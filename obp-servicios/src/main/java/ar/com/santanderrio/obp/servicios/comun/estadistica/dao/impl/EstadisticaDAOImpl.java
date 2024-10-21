/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.estadistica.dao.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.dao.impl.BaseDatoDAOImpl;
import ar.com.santanderrio.obp.base.security.aop.annotation.TargetSystem;
import ar.com.santanderrio.obp.base.security.credential.DataBase;
import ar.com.santanderrio.obp.servicios.comun.estadistica.dao.EstadisticaDAO;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.entities.Estadistica;

/**
 * The Class EstadisticaDAOImpl.
 */
@Repository
@TargetSystem(DataBase.ESTADISTICAS)
public class EstadisticaDAOImpl extends BaseDatoDAOImpl implements EstadisticaDAO {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(EstadisticaDAOImpl.class);

    /** SCHEMA. */
    private static final String ESTADISTICA_SCHEMA = "hbank";

    /** PACKAGE Estadistica. */
    private static final String ESTADISTICA_PACKAGE = "HB_PKG_ESTADISTICAS";

    /** PROCEDURE Estadistica. */
    private static final String ESTADISTICA_PROCEDURE = "prc_registro_operacion";

    /** The Constant P_DEVICE. */
    private static final String IN_P_DEVICE = "p_device";

    private static final String IN_P_CS_ID = "p_cs_id";

    /** The Constant P_DNI. */
    private static final String IN_ESTADISTICA_DNI = "p_dni";
    /** The Constant FECHA. */
    private static final String IN_ESTADISTICA_FECHA = "p_fecha_actual";
    /** The Constant SINONIMO. */
    private static final String IN_ESTADISTICA_SINONIMO = "p_sinonimo";
    /** The Constant CODIGO. */
    private static final String IN_ESTADISTICA_CODIGO = "p_codigo";
    /** The Constant ESTADO. */
    private static final String IN_ESTADISTICA_ESTADO = "p_estado";
    /** The Constant ID. */
    private static final String IN_ESTADISTICA_ID = "p_pid";
    /** The Constant TIPO STAD. */
    private static final String IN_ESTADISTICA_TIPO = "p_tipo_stad";
    /** The Constant NRO COMPROBANTE. */
    private static final String IN_ESTADISTICA_NRO_COMPROBANTE = "p_nro_comprobante";
    /** The Constant IMPORTE. */
    private static final String IN_ESTADISTICA_IMPORTE = "p_importe";
    /** The Constant MONEDA. */
    private static final String IN_ESTADISTICA_MONEDA = "p_moneda";
    /** The Constant CTA ORIGEN. */
    private static final String IN_ESTADISTICA_CTA_ORIGEN = "p_cta_origen";
    /** The Constant CTA DESTINO. */
    private static final String IN_ESTADISTICA_CTA_DESTINO = "p_cta_destino";
    /** The Constant CANAL. */

    private static final String IN_ESTADISTICA_CANAL = "p_canal_stad";
    /** The Constant NUP. */
    private static final String IN_ESTADISTICA_NUP = "p_nup";
    /** The Constant TV. */
    private static final String IN_ESTADISTICA_TV = "p_tv";
    /** The Constant IP. */
    private static final String IN_ESTADISTICA_IP = "p_ip";
    /** The Constant LOG SRV. */
    private static final String IN_ESTADISTICA_LOG_SRV = "p_log_srv";
    /** The Constant LOG NAME. */
    private static final String IN_ESTADISTICA_LOG_NAME = "p_log_name";
    /** The Constant SALIDA. */
    private static final String OUT_ESTADISTICA_SALIDA = "p_salida";

    /** The Constant CONST_TIPO_STAD. */
    private static final String CONST_TIPO_STAD = "R";

    /** The Constant CONST_CANAL_STAD. */
    private static final String CONST_CANAL_STAD = "R";

    /** The Constant COD_RETORNO_OK. */
    private static final String COD_RETORNO_OK = "0";
    
    @Value("${REGISTRO_OPERACION.VERSION:4}")
	private String store_version="2";

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.comun.estadistica.dao.EstadisticaDAO#
     * add(ar.com.santanderrio.obp.servicios.comun.estadisticas.entities.
     * Estadistica)
     */
    @Override
    public void add(Estadistica estadistica) throws DAOException {
        if (estadistica != null) {
            LOGGER.info("Se consultara el procedure {} con la estadistica {}.", ESTADISTICA_PROCEDURE+store_version, estadistica);
            List<SqlParameter> parametros = new ArrayList<SqlParameter>();
            parametros.add(new SqlParameter(IN_ESTADISTICA_DNI, Types.VARCHAR));
            parametros.add(new SqlParameter(IN_ESTADISTICA_FECHA, Types.VARCHAR));
            parametros.add(new SqlParameter(IN_ESTADISTICA_SINONIMO, Types.VARCHAR));
            parametros.add(new SqlParameter(IN_ESTADISTICA_CODIGO, Types.VARCHAR));
            parametros.add(new SqlParameter(IN_ESTADISTICA_ESTADO, Types.VARCHAR));
            parametros.add(new SqlParameter(IN_ESTADISTICA_ID, Types.VARCHAR));
            parametros.add(new SqlParameter(IN_ESTADISTICA_TIPO, Types.VARCHAR));
            parametros.add(new SqlParameter(IN_ESTADISTICA_NRO_COMPROBANTE, Types.VARCHAR));
            parametros.add(new SqlParameter(IN_ESTADISTICA_IMPORTE, Types.VARCHAR));
            parametros.add(new SqlParameter(IN_ESTADISTICA_MONEDA, Types.VARCHAR));
            parametros.add(new SqlParameter(IN_ESTADISTICA_CTA_ORIGEN, Types.VARCHAR));
            parametros.add(new SqlParameter(IN_ESTADISTICA_CTA_DESTINO, Types.VARCHAR));
            parametros.add(new SqlParameter(IN_ESTADISTICA_CANAL, Types.VARCHAR));
            parametros.add(new SqlParameter(IN_ESTADISTICA_NUP, Types.VARCHAR));
            parametros.add(new SqlParameter(IN_ESTADISTICA_TV, Types.VARCHAR));
            parametros.add(new SqlParameter(IN_ESTADISTICA_IP, Types.VARCHAR));
            parametros.add(new SqlParameter(IN_ESTADISTICA_LOG_SRV, Types.VARCHAR));
            parametros.add(new SqlParameter(IN_ESTADISTICA_LOG_NAME, Types.VARCHAR));
            if (store_version.equals("3")){
            	parametros.add(new SqlParameter(IN_P_DEVICE, Types.VARCHAR));
            } else if(store_version.equals("4")){
                parametros.add(new SqlParameter(IN_P_DEVICE, Types.VARCHAR));
                parametros.add(new SqlParameter(IN_P_CS_ID, Types.VARCHAR));
            }
            parametros.add(new SqlOutParameter(OUT_ESTADISTICA_SALIDA, Types.NUMERIC));

            SqlParameterSource in = new MapSqlParameterSource()
                    .addValue(IN_ESTADISTICA_DNI, estadistica.getClienteDNI())
                    .addValue(IN_ESTADISTICA_FECHA, estadistica.getFecha())
                    .addValue(IN_ESTADISTICA_SINONIMO, estadistica.getFechaNacimiento())
                    .addValue(IN_ESTADISTICA_CODIGO, estadistica.getCodigoTransaccion())
                    .addValue(IN_ESTADISTICA_ESTADO, estadistica.getCodigoError())
                    .addValue(IN_ESTADISTICA_ID, estadistica.getClientePID())
                    .addValue(IN_ESTADISTICA_TIPO, CONST_TIPO_STAD)
                    .addValue(IN_ESTADISTICA_NRO_COMPROBANTE, estadistica.getNroComprobante())
                    .addValue(IN_ESTADISTICA_IMPORTE, estadistica.getImporte())
                    .addValue(IN_ESTADISTICA_MONEDA, estadistica.getMoneda())
                    .addValue(IN_ESTADISTICA_CTA_ORIGEN, estadistica.getNroCtaOrigen())
                    .addValue(IN_ESTADISTICA_CTA_DESTINO, estadistica.getNroCtaDestino())
                    .addValue(IN_ESTADISTICA_CANAL, CONST_CANAL_STAD)
                    .addValue(IN_ESTADISTICA_NUP, estadistica.getClienteNUP().trim())
                    .addValue(IN_ESTADISTICA_TV, estadistica.getTipoTeclado())
                    .addValue(IN_ESTADISTICA_IP, estadistica.getIp())
                    .addValue(IN_ESTADISTICA_LOG_SRV, estadistica.getHostName())
                    .addValue(IN_ESTADISTICA_LOG_NAME, estadistica.getFileName());
                    if (store_version.equals("3")){
                    	((MapSqlParameterSource)in).addValue(IN_P_DEVICE, estadistica.getDispositivo());
                    } else if(store_version.equals("4")){
                        ((MapSqlParameterSource)in).addValue(IN_P_DEVICE, estadistica.getDispositivo());
                        ((MapSqlParameterSource)in).addValue(IN_P_CS_ID, estadistica.getCsId());
                    }
            try {
                Map<String, Object> respuesta = consultar(ESTADISTICA_SCHEMA, ESTADISTICA_PACKAGE,
                        ESTADISTICA_PROCEDURE+store_version, in, parametros.toArray(new SqlParameter[parametros.size()]));
                BigDecimal codRetorno = (BigDecimal) respuesta.get(OUT_ESTADISTICA_SALIDA);
                if (!COD_RETORNO_OK.equals(codRetorno.toString())) {
                    LOGGER.info("Error en store {}.{}.{}. al agregar estadistica {}.", ESTADISTICA_SCHEMA,
                            ESTADISTICA_PACKAGE, ESTADISTICA_PROCEDURE+store_version, estadistica);
                    throw new DAOException("Error al consultar el store " + ESTADISTICA_PROCEDURE+store_version);
                } else {
                    LOGGER.info("Se registro la estadistica {} correctamente.", estadistica);
                }
            } catch (SQLException sqle) {
                LOGGER.error("Error en store {}.{}.{} al agregar estadistica {}.", ESTADISTICA_SCHEMA,
                        ESTADISTICA_PACKAGE, ESTADISTICA_PROCEDURE+store_version, estadistica, sqle);
                throw new DAOException(sqle);
            } catch (RuntimeException ex) {
                LOGGER.error("Error en base del store {}.{}.{} al agregar estadistica {}.", ESTADISTICA_SCHEMA,
                        ESTADISTICA_PACKAGE, ESTADISTICA_PROCEDURE+store_version, estadistica, ex);
                throw new DAOException(ex);
            }
        }
    }
}
