/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.ordenes.dao;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.QueryTimeoutException;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.dao.impl.BaseDatoDAOImpl;
import ar.com.santanderrio.obp.base.security.aop.annotation.TargetSystem;
import ar.com.santanderrio.obp.base.security.credential.DataBase;
import ar.com.santanderrio.obp.servicios.inversiones.ordenes.entity.ConfirmarOrdenInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.ordenes.entity.ConfirmarOrdenOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.ordenes.entity.Orden;
import ar.com.santanderrio.obp.servicios.inversiones.ordenes.entity.OrdenInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.ordenes.entity.OrdenOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.ordenes.entity.RtaConfirmarOrden;
import ar.com.santanderrio.obp.servicios.tarjetas.exceptions.SimulacionDAOException;
import ar.com.santanderrio.obp.servicios.transferencias.exception.TimeOutException;
import oracle.jdbc.OracleTypes;

/**
 * The Class OrdenDAOBprivImpl.
 *
 * @author marcelo.ruiz
 */
@Component
@TargetSystem(DataBase.BPRIV)
public class OrdenDAOBprivImpl extends BaseDatoDAOImpl implements OrdenDAOBpriv {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(OrdenDAOBprivImpl.class);

    /** SCHEMA. */
    private static final String CONTRATO_SCHEMA = "BCAINV";

    /** PACKAGE contrato. */
    private static final String CONTRATO_PACKAGE = "PKG_BP_ONLINE_BANKING";

    /** PROCEDURE SIMULAR. */
    private static final String CONTRATO_LOAD_ORDENES = "LOAD_ORDENES";

    /** CONFIRMAR ORDEN. */
    private static final String CONTRATO_CONFIRMAR_ORDEN = "SAVE_ORDEN_RESPALDO";

    /** The Constant IN_CUENTA. */
    private static final String IN_CUENTA = "P_CUENTA";

    /** The Constant CODIGO_LOGGER. */
    private static final String CODIGO_LOGGER = "%s : %s";

    /** The Constant IN_COD_SIST. */
    private static final String IN_COD_SIST = "P_COD_SIST";

    /** The Constant IN_P_MANT_REG. */
    private static final String IN_P_MANT_REG = "P_MANT_REG";

    /** The Constant IN_FECHA_DESDE. */
    private static final String IN_FECHA_DESDE = "P_FECHAVALOR_DESDE";

    /** The Constant IN_FECHA_HASTA. */
    private static final String IN_FECHA_HASTA = "P_FECHAVALOR_HASTA";

    /** The Constant IN_OPERACIONES. */
    private static final String IN_OPERACIONES = "P_OPERACIONES";

    /** The Constant OUT_RETORNO. */
    private static final String OUT_RETORNO = "P_RETORNO";

    /** The Constant OUT_COD_RETORNO. */
    private static final String OUT_COD_RETORNO = "P_COD_RETORNO";

    /** The Constant OUT_DESCRIPCION. */
    private static final String OUT_DESCRIPCION = "P_DESCRIPCION";

    /** The Constant OUT_CURSOR. */
    private static final String OUT_CURSOR = "P_CURSOR";

    /** The Constant P_RETORNO_OK. */
    private static final String P_RETORNO_OK = "OK";

    /** The Constant P_RETORNO_ER. */
    private static final String P_RETORNO_ER = "ER";

    /** LISTA DE CAMPOS DE ORDENES. */
    private static final String FIELD_NUM_ORDEN = "P_NUM_ORDEN";

    /** The Constant FIELD_COD_SIST. */
    private static final String FIELD_COD_SIST = "P_COD_SIST";

    /** The Constant FIELD_TIPO_ORD. */
    private static final String FIELD_TIPO_ORD = "P_TIPO_ORD";

    /** The Constant FIELD_ESPECIE. */
    private static final String FIELD_ESPECIE = "P_ESPECIE";

    /** The Constant FIELD_MONE_LIQ. */
    private static final String FIELD_MONE_LIQ = "P_MONE_LIQ";

    /** The Constant FIELD_FECHA_ORDEN. */
    private static final String FIELD_FECHA_ORDEN = "P_FECHA_ORDEN";

    /** The Constant FIELD_FECHA_LIQ. */
    private static final String FIELD_FECHA_LIQ = "P_FECHA_LIQ";

    /** The Constant FIELD_CANT_TITU_8. */
    private static final String FIELD_CANT_TITU_8 = "P_CANT_TITU_8";

    /** The Constant FIELD_PREC_UNI_8. */
    private static final String FIELD_PREC_UNI_8 = "P_PREC_UNI_8";

    /** The Constant FIELD_CAPITAL. */
    private static final String FIELD_CAPITAL = "P_CAPITAL";

    /** The Constant FIELD_DETALLE. */
    private static final String FIELD_DETALLE = "P_DETALLE";

    /** The Constant FIELD_ESTADO. */
    private static final String FIELD_ESTADO = "P_ESTADO";

    /** The Constant FIELD_HORA_EMISION. */
    private static final String FIELD_HORA_EMISION = "P_HORA_EMISION";

    /** The Constant FIELD_DESC_ESPECIE. */
    private static final String FIELD_DESC_ESPECIE = "P_DESC_ESPECIE";

    /** The Constant FIELD_NUM_ATIT. */
    private static final String FIELD_NUM_ATIT = "P_NUM_ATIT";

    /** The Constant FIELD_RESPALDABLE. */
    private static final String FIELD_RESPALDABLE = "P_RESPALDABLE";

    /** The Constant FIELD_PLAZO. */
    private static final String FIELD_PLAZO = "P_PLAZO";

    /** The Constant FIELD_TNA. */
    private static final String FIELD_TNA = "P_TNA";

    /** The Constant FIELD_COD_ACCION_VTO. */
    private static final String FIELD_COD_ACCION_VTO = "P_COD_ACCION_VTO";

    /** The Constant FIELD_ACCION_VTO. */
    private static final String FIELD_ACCION_VTO = "P_ACCION_VTO";

    /** The Constant FIELD_INT_DEVENG_ESPEC. */
    private static final String FIELD_INT_DEVENG_ESPEC = "P_INT_DEVENG_ESPEC";

    /** The Constant FIELD_DERECHOS. */
    private static final String FIELD_DERECHOS = "DERECHOS";

    /** The Constant FIELD_IMPUESTO. */
    private static final String FIELD_IMPUESTO = "IMPUESTO";

    /** The Constant FIELD_MERCADO_ORIGEN. */
    private static final String FIELD_MERCADO_ORIGEN = "MERCADO_ORIGEN";

    /** The Constant FIELD_MERCADO_OPERACION. */
    private static final String FIELD_MERCADO_OPERACION = "MERCADO_OPERACION";

    /** The Constant FIELD_SUC_CTA_PLAZO. */
    private static final String FIELD_SUC_CTA_PLAZO = "P_SUC_CTA_PLAZO";

    /** The Constant FIELD_NUM_CTA_PLAZO. */
    private static final String FIELD_NUM_CTA_PLAZO = "NUM_CTA_PLAZO";

    /** The Constant FIELD_SECUENCIA. */
    private static final String FIELD_SECUENCIA = "SECUENCIA";

    /** The Constant FIELD_NUM_FONDO_FM. */
    private static final String FIELD_NUM_FONDO_FM = "NUM_FONDO_FM";

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.inversiones.ordenes.dao.OrdenDAOBpriv#
     * cargarOrdenes(ar.com.santanderrio.obp.servicios.inversiones.ordenes.
     * entity.OrdenInEntity)
     */
    @SuppressWarnings("unchecked")
    @Override
    public OrdenOutEntity cargarOrdenes(OrdenInEntity inEntity) throws DAOException {
        OrdenOutEntity loadOrdenes = new OrdenOutEntity();

        List<SqlParameter> parametros = new ArrayList<SqlParameter>();
        parametros.add(new SqlParameter(IN_CUENTA, Types.VARCHAR));
        parametros.add(new SqlParameter(IN_COD_SIST, Types.VARCHAR));
        parametros.add(new SqlParameter(IN_FECHA_DESDE, Types.VARCHAR));
        parametros.add(new SqlParameter(IN_FECHA_HASTA, Types.VARCHAR));
        parametros.add(new SqlParameter(IN_OPERACIONES, Types.VARCHAR));

        parametros.add(new SqlOutParameter((String) OUT_RETORNO, Types.VARCHAR));
        parametros.add(new SqlOutParameter((String) OUT_COD_RETORNO, Types.INTEGER));
        parametros.add(new SqlOutParameter((String) OUT_DESCRIPCION, Types.VARCHAR));
        parametros.add(new SqlOutParameter((String) OUT_CURSOR, OracleTypes.CURSOR));

        SqlParameterSource in = new MapSqlParameterSource().addValue(IN_CUENTA, inEntity.getCuenta())
                .addValue(IN_COD_SIST, inEntity.getCodigoSis()).addValue(IN_FECHA_DESDE, inEntity.getFechaDesde())
                .addValue(IN_FECHA_HASTA, inEntity.getFechaHasta()).addValue(IN_OPERACIONES, inEntity.getOperaciones());

        try {
            Map<String, Object> respuesta = consultar(CONTRATO_SCHEMA, CONTRATO_PACKAGE, CONTRATO_LOAD_ORDENES, in,
                    parametros.toArray(new SqlParameter[parametros.size()]));
            String retorno = (String) respuesta.get(OUT_RETORNO);
            LOGGER.info(OUT_RETORNO + " : " + retorno);
            if (P_RETORNO_OK.equals(retorno)) {
                loadOrdenes = parsearRespuesta((List<Map<String, Object>>) respuesta.get(OUT_CURSOR));
            } else {
                String descError = (String) respuesta.get(OUT_DESCRIPCION);
                if (P_RETORNO_ER.equals(retorno)) {
                    LOGGER.debug("Error cargando ordenes " + descError);
                    Integer codRetorno = (Integer) respuesta.get(OUT_COD_RETORNO);
                    throw new DAOException(descError, codRetorno.toString());
                }
            }
        } catch (SQLException sqle) {
            manejarErrorInesperado(sqle, CONTRATO_LOAD_ORDENES);
        } catch (UncategorizedSQLException sqle) {
            manejarErrorInesperado(sqle, CONTRATO_LOAD_ORDENES);
        }
        return loadOrdenes;
    }

    /**
     * Parsear respuesta.
     *
     * @param ordenes
     *            the ordenes
     * @return the orden out entity
     */
    private OrdenOutEntity parsearRespuesta(List<Map<String, Object>> ordenes) {
        Iterator<Map<String, Object>> iterator = ordenes.iterator();
        List<Orden> ordenesEntity = new ArrayList<Orden>();
        while (iterator.hasNext()) {
            ordenesEntity.add(generarOrden(iterator.next()));
        }
        OrdenOutEntity out = new OrdenOutEntity();
        out.setOrdenes(ordenesEntity);
        return out;
    }

    /**
     * Generar orden.
     *
     * @param map
     *            the map
     * @return the orden
     */
    private Orden generarOrden(Map<String, Object> map) {
        Orden orden = new Orden();
        orden.setAccionVto(
                map.get(FIELD_COD_ACCION_VTO) != null ? ((BigDecimal) map.get(FIELD_COD_ACCION_VTO)).toString() : null);
        orden.setNroOrden((String) map.get(FIELD_NUM_ORDEN));
        orden.setCodSitema((String) map.get(FIELD_COD_SIST));
        orden.setTipoOrden((String) map.get(FIELD_TIPO_ORD));
        orden.setEspecie((String) map.get(FIELD_ESPECIE));
        orden.setMonedaLiq((String) map.get(FIELD_MONE_LIQ));
        orden.setFechaOrden((Date) map.get(FIELD_FECHA_ORDEN));
        orden.setFechaLiq((Date) map.get(FIELD_FECHA_LIQ));
        orden.setCantTitulo((BigDecimal) map.get(FIELD_CANT_TITU_8));
        orden.setPrecioUni((BigDecimal) map.get(FIELD_PREC_UNI_8));
        orden.setCapital((BigDecimal) map.get(FIELD_CAPITAL));
        orden.setDetalle((String) map.get(FIELD_DETALLE));
        orden.setHoraEmision((String) map.get(FIELD_HORA_EMISION));
        orden.setDerechos((BigDecimal) map.get(FIELD_DERECHOS));
        orden.setImpuesto((BigDecimal) map.get(FIELD_IMPUESTO));
        orden.setDescripcionEspecie((String) map.get(FIELD_DESC_ESPECIE));
        orden.setNroAtit((BigDecimal) map.get(FIELD_NUM_ATIT));
        orden.setRespaldable((String) map.get(FIELD_RESPALDABLE));
        orden.setPlazo(map.get(FIELD_PLAZO) != null ? ((BigDecimal) map.get(FIELD_PLAZO)).toString() : null);
        orden.setTna(map.get(FIELD_TNA) != null ? ((BigDecimal) map.get(FIELD_TNA)).toString() : null);
        orden.setCodAccionVto(
                map.get(FIELD_COD_ACCION_VTO) != null ? ((BigDecimal) map.get(FIELD_COD_ACCION_VTO)).toString() : null);
        orden.setAccionVto((String) map.get(FIELD_ACCION_VTO));
        orden.setSucursalCtaPlazo((String) map.get(FIELD_SUC_CTA_PLAZO));
        orden.setNumCtaPlazo((String) map.get(FIELD_NUM_CTA_PLAZO));
        orden.setSecuencia((String) map.get(FIELD_SECUENCIA));
        orden.setNroFondo((BigDecimal) map.get(FIELD_NUM_FONDO_FM));
        orden.setEstado((String) map.get(FIELD_ESTADO));
        orden.setIntDevengEspec((String) map.get(FIELD_INT_DEVENG_ESPEC));
        orden.setMercadoOperacion((String) map.get(FIELD_MERCADO_OPERACION));
        orden.setMercadoOrigen((String) map.get(FIELD_MERCADO_ORIGEN));
        return orden;

    }

    /**
     * Manejar error inesperado.
     *
     * @param sqle
     *            the sqle
     * @param contrato
     *            the contrato
     * @throws DAOException
     *             the DAO exception
     */
    private void manejarErrorInesperado(Exception sqle, String contrato) throws DAOException {
        LOGGER.error("Error al consumir el store {}.{}.{} ", CONTRATO_SCHEMA, CONTRATO_PACKAGE, contrato, sqle);
        throw new SimulacionDAOException(sqle);
    }

    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.inversiones.ordenes.dao.OrdenDAOBpriv#confirmarOrden(ar.com.santanderrio.obp.servicios.inversiones.ordenes.entity.ConfirmarOrdenInEntity)
     */
    @Override
    public ConfirmarOrdenOutEntity confirmarOrden(ConfirmarOrdenInEntity in) throws DAOException {

        ConfirmarOrdenOutEntity confirmarOrdenOutEntity = new ConfirmarOrdenOutEntity();
        LOGGER.info("Ejecuta confirmar orden para la cuenta: {} y numero de orden: {}", in.getCuenta(),
                in.getNumeroOrden());
        RtaConfirmarOrden rta = ejecutarStoredConfirmarOrden(in);
        confirmarOrdenOutEntity.setRespuesta(rta);
        return confirmarOrdenOutEntity;

    }

    /**
	 * Ejecutar stored confirmar orden.
	 *
	 * @param inEntity
	 *            the in entity
	 * @return the rta confirmar orden
	 * @throws DAOException
	 *             the DAO exception
	 */
    private RtaConfirmarOrden ejecutarStoredConfirmarOrden(ConfirmarOrdenInEntity inEntity) throws DAOException {

        RtaConfirmarOrden rtaConfirmarOrden = new RtaConfirmarOrden();
        List<SqlParameter> parametros = new ArrayList<SqlParameter>();

        parametros.add(new SqlParameter(IN_CUENTA, Types.VARCHAR));
        parametros.add(new SqlParameter(FIELD_NUM_ORDEN, Types.VARCHAR));
        parametros.add(new SqlParameter(IN_COD_SIST, Types.VARCHAR));
        parametros.add(new SqlParameter(IN_P_MANT_REG, Types.VARCHAR));
        parametros.add(new SqlOutParameter((String) OUT_RETORNO, Types.VARCHAR));
        parametros.add(new SqlOutParameter((String) OUT_COD_RETORNO, Types.INTEGER));
        parametros.add(new SqlOutParameter((String) OUT_DESCRIPCION, Types.VARCHAR));

        LOGGER.info("SP : %s, %s, %s", CONTRATO_SCHEMA, CONTRATO_PACKAGE, CONTRATO_CONFIRMAR_ORDEN);

        LOGGER.info(CODIGO_LOGGER, IN_CUENTA, inEntity.getCuenta());
        LOGGER.info(CODIGO_LOGGER, FIELD_NUM_ORDEN, inEntity.getNumeroOrden());
        LOGGER.info(CODIGO_LOGGER, IN_COD_SIST, inEntity.getCodigoSist());
        LOGGER.info(CODIGO_LOGGER, IN_P_MANT_REG, inEntity.getRegMant());

        SqlParameterSource in = new MapSqlParameterSource().addValue(IN_CUENTA, inEntity.getCuenta())
                .addValue(FIELD_NUM_ORDEN, inEntity.getNumeroOrden()).addValue(IN_COD_SIST, inEntity.getCodigoSist())
                .addValue(IN_P_MANT_REG, inEntity.getRegMant());
        try {
            Map<String, Object> respuesta = consultar(CONTRATO_SCHEMA, CONTRATO_PACKAGE, CONTRATO_CONFIRMAR_ORDEN, in,
                    parametros.toArray(new SqlParameter[parametros.size()]));

            Integer codRetorno = (Integer) respuesta.get(OUT_COD_RETORNO);
            String retorno = (String) respuesta.get(OUT_RETORNO);
            LOGGER.info(OUT_RETORNO + " : " + retorno);
            if (P_RETORNO_OK.equals(retorno)) {
                rtaConfirmarOrden = parsearRespuestansferencia(respuesta);

            } else {
                String descError = (String) respuesta.get(OUT_DESCRIPCION);
                if (P_RETORNO_ER.equals(retorno)) {
                    LOGGER.debug("Error cargando ordenes " + descError);
                    throw new DAOException(descError, codRetorno.toString());
                }
            }
        } catch (SQLException sqle) {
            manejarErrorInesperado(sqle, CONTRATO_CONFIRMAR_ORDEN);
        } catch (UncategorizedSQLException sqle) {
            manejarErrorInesperado(sqle, CONTRATO_CONFIRMAR_ORDEN);
        } catch (QueryTimeoutException sqle) {
            LOGGER.error("Error al consumir el store {}.{}.{} ", CONTRATO_SCHEMA, CONTRATO_PACKAGE,
                    CONTRATO_CONFIRMAR_ORDEN, sqle);
            throw new TimeOutException("Error de timeOut en query.");
        }
        return rtaConfirmarOrden;
    }

    /**
     * Armo el objeto respuesta con el mapa que retorno la ejecucion del SP.
     *
     * @param respuesta
     *            the respuesta
     * @return the RtaConfirmarOrden.
     */
    private RtaConfirmarOrden parsearRespuestansferencia(Map<String, Object> respuesta) {
        RtaConfirmarOrden rtaConfirmarOrden = new RtaConfirmarOrden();
        rtaConfirmarOrden.setCodigoResultado(null);
        rtaConfirmarOrden.setDescrResultado((String) respuesta.get(OUT_DESCRIPCION));
        rtaConfirmarOrden.setRetorno((String) respuesta.get(OUT_RETORNO));
        return rtaConfirmarOrden;
    }

}
