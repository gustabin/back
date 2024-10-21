/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetarecargable.dao.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.dao.QueryTimeoutException;
import org.springframework.jdbc.core.SqlInOutParameter;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.dao.impl.BaseDatoDAOImpl;
import ar.com.santanderrio.obp.base.exceptions.ISBANRuntimeException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.reporte.entities.TipoArchivoEnum;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.base.security.aop.annotation.TargetSystem;
import ar.com.santanderrio.obp.base.security.credential.DataBase;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.tarjetarecargable.dao.TarjetaRecargableDAO;
import ar.com.santanderrio.obp.servicios.tarjetarecargable.entities.SolicitudTarjetaRecargableInEntity;
import ar.com.santanderrio.obp.servicios.tarjetarecargable.entities.SolicitudTarjetaRecargableOutEntity;
import ar.com.santanderrio.obp.servicios.tarjetarecargable.web.view.DatosComprobanteSolicitudTarjetaRecargableView;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.type.WhenNoDataTypeEnum;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 * The Class TarjetaRecargableDAOImpl.
 */
@Repository
@TargetSystem(DataBase.RECLAMOS)
public class TarjetaRecargableDAOImpl extends BaseDatoDAOImpl implements TarjetaRecargableDAO {

    /** Logger TarjetaRecargableDAOImpl. */
    private static final Logger LOGGER = LoggerFactory.getLogger(TarjetaRecargableDAOImpl.class);

    /** The Constant CONSULTA_PROCEDURE. */
    private static final String CONSULTA_PROCEDURE = "Se consultara el procedure {}.";

    /** Parametro de entrada P_Atributos. */
    private static final String IN_ATRIBUTOS = "p_atributos";

    /** Parametro de salida P_Nro_Gestion. */
    private static final String OUT_NRO_GESTION = "p_nro_gestion";

    /** Parametro de salida P_Cod_Ret. */
    private static final String OUT_COD_RET = "p_cod_error";

    /** Parametro de salida P_Desc_Error. */
    private static final String OUT_DESC_ERROR = "p_desc_error";

    /** The Constant OUT_RETURN. */
    private static final String OUT_RETURN = "v_return";

    /** SCHEMA Tarjeta Recargable. */
    private static final String TARJETA_RECARGABLE_SCHEMA = "GEC01";

    /** PACKAGE Tarjeta Recargable. */
    private static final String TARJETA_RECARGABLE_PACKAGE = "PKG_GC_HOMEBANKING";

    /** PROCEDURE Tarjeta Recargable. */
    private static final String TARJETA_RECARGABLE_PROCEDURE = "INSERTAR_GESTION_HB_GOLD";

    /** The Constant COD_RETORNO_OK. */
    private static final BigDecimal COD_RETORNO_OK = BigDecimal.ZERO;

    /** The Constant MENSAJE_ERROR_EN_STORE. */
    private static final String MENSAJE_ERROR_EN_STORE = "Error en store {}.{}.{}. al solicitar la adhesion a debito automatico en tarjeta de {}.";

    /** The Constant ALTA_GESTION_OK. */
    private static final String ALTA_GESTION_OK = "Se dio de alta la solicitud de tarjeta recargable";

    /** The Constant ERROR_EN_BASE. */
    private static final String ERROR_EN_BASE = "Error en base del store {}.{}.{} al solicitar la adhesion a debito automatico en tarjeta de {}.";

    /** The respuesta factory. */
    @Autowired
    private RespuestaFactory respuestaFactory;

    /** The file jasper. */
    @Value("classpath:/report/tarjetarecargable/SolicitudTarjetaRecargable.jasper")
    private Resource fileJasperComprobanteSolicitudTarjetaRecargable;

    /** The logo cierre. */
    @Value("classpath:/report/comprobantes/logo_cierre_comprobante.png")
    private Resource logoCierre;

    /** The logo cabecera. */
    @Value("classpath:/report/comprobantes/logo_cabecera_comprobante.png")
    private Resource logoCabecera;

    /** The logo default. */
    @Value("classpath:/report/comprobantes/logo_visa.png")
    private Resource logoVisa;

    /** The Constant LOGO_CABECERA. */
    private static final String LOGO_CABECERA = "LOGO_CABECERA";

    /** The Constant LOGO_DEBITO. */
    private static final String LOGO_VISA = "LOGO_VISA";

    /** The Constant ESPACIO. */
    private static final String ESPACIO = " ";

    /** The Constant NOMBRE_APELLIDO. */
    private static final String NOMBRE_APELLIDO = "NOMBRE_APELLIDO";

    /** The Constant TIPO_NRO_DOCUMENTO. */
    private static final String TIPO_NRO_DOCUMENTO = "TIPO_NRO_DOCUMENTO";

    /** The Constant FECHA_NACIMIENTO. */
    private static final String FECHA_NACIMIENTO = "FECHA_NACIMIENTO";

    /** The Constant NACIONALIDAD. */
    private static final String NACIONALIDAD = "NACIONALIDAD";

    /** The Constant SEXO. */
    private static final String SEXO = "SEXO";

    /** The Constant ESTADO_CIVIL. */
    private static final String ESTADO_CIVIL = "ESTADO_CIVIL";

    /** The Constant DOMICILIO. */
    private static final String DOMICILIO = "DOMICILIO";

    /** The Constant NUMERO. */
    private static final String NUMERO = "NUMERO";

    /** The Constant PISO_DEPTO. */
    private static final String PISO_DEPTO = "PISO_DEPTO";

    /** The Constant LOCALIDAD. */
    private static final String LOCALIDAD = "LOCALIDAD";

    /** The Constant COD_POSTAL. */
    private static final String COD_POSTAL = "COD_POSTAL";

    /** The Constant PROVINCIA. */
    private static final String PROVINCIA = "PROVINCIA";

    /** The Constant TELEFONO. */
    private static final String TELEFONO = "TELEFONO";

    /** The Constant NRO_COMPROBANTE. */
    private static final String NRO_COMPROBANTE = "NRO_COMPROBANTE";

    /** The Constant FECHA_OPERACION. */
    private static final String FECHA_OPERACION = "FECHA_OPERACION";

    /** The Constant LOGO_CIERRE. */
    private static final String LOGO_CIERRE = "LOGO_CIERRE";

    /** The Constant PDF_EXTENSION. */
    private static final String PDF_EXTENSION = ".pdf";

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.tarjetarecargable.dao.
     * TarjetaRecargableDAO#altaSolicitudTarjetaRecargable(ar.com.santanderrio.
     * obp.servicios.tarjetarecargable.entities. SolicitudTarjetaRecargableInEntity)
     */
    @Override
    public Respuesta<SolicitudTarjetaRecargableOutEntity> altaSolicitudTarjetaRecargable(
            SolicitudTarjetaRecargableInEntity solicitudTarjetaRecargableInEntity)
            throws DAOException, QueryTimeoutException {
        LOGGER.info(CONSULTA_PROCEDURE, TARJETA_RECARGABLE_PROCEDURE);
        SolicitudTarjetaRecargableOutEntity solicitudTarjetaRecargableOutEntity = new SolicitudTarjetaRecargableOutEntity();
        String spAtributos = solicitudTarjetaRecargableInEntity.getSpAtributos();

        SqlParameterSource in = new MapSqlParameterSource().addValue(IN_ATRIBUTOS, spAtributos)
                .addValue(OUT_NRO_GESTION, null).addValue(OUT_COD_RET, 1).addValue(OUT_DESC_ERROR, null);
        try {
            return generarRespuestaAltaSolicitudTarjetaRecargable(solicitudTarjetaRecargableInEntity,
                    solicitudTarjetaRecargableOutEntity, spAtributos, in);
        } catch (SQLException sqle) {
            LOGGER.error(MENSAJE_ERROR_EN_STORE, TARJETA_RECARGABLE_SCHEMA, TARJETA_RECARGABLE_PACKAGE,
                    TARJETA_RECARGABLE_PROCEDURE, spAtributos, sqle);
            throw new DAOException(sqle);
        } catch (DAOException de) {
            LOGGER.error(ERROR_EN_BASE, TARJETA_RECARGABLE_SCHEMA, TARJETA_RECARGABLE_PACKAGE,
                    TARJETA_RECARGABLE_PROCEDURE, spAtributos, de);
            throw new DAOException(de);
        }
    }

    /**
     * Generar respuesta alta solicitud tarjeta recargable.
     *
     * @param solicitudTarjetaRecargableInEntity
     *            the solicitud tarjeta recargable in entity
     * @param solicitudTarjetaRecargableOutEntity
     *            the solicitud tarjeta recargable out entity
     * @param spAtributos
     *            the sp atributos
     * @param in
     *            the in
     * @return the respuesta
     * @throws SQLException
     *             the SQL exception
     * @throws DAOException
     *             the DAO exception
     */
    public Respuesta<SolicitudTarjetaRecargableOutEntity> generarRespuestaAltaSolicitudTarjetaRecargable(
            SolicitudTarjetaRecargableInEntity solicitudTarjetaRecargableInEntity,
            SolicitudTarjetaRecargableOutEntity solicitudTarjetaRecargableOutEntity, String spAtributos,
            SqlParameterSource in) throws SQLException, DAOException {
        LOGGER.info("Se consume el store {}.{}.{}. con parametro de entrada {}", TARJETA_RECARGABLE_SCHEMA,
                TARJETA_RECARGABLE_PACKAGE, TARJETA_RECARGABLE_PROCEDURE,
                solicitudTarjetaRecargableInEntity.getSpAtributos());
        Map<String, Object> respuesta = consultarDB(in);
        BigDecimal codRetorno = (BigDecimal) respuesta.get(OUT_COD_RET);
        if (COD_RETORNO_OK.equals(codRetorno)) {
            LOGGER.info(ALTA_GESTION_OK, spAtributos);
            String nroGestion = (String) respuesta.get(OUT_NRO_GESTION);
            String descError = (String) respuesta.get(OUT_DESC_ERROR);

            solicitudTarjetaRecargableOutEntity.setNroGestion(nroGestion.isEmpty() ? null : nroGestion);
            solicitudTarjetaRecargableOutEntity.setCodRetorno(codRetorno.toString());
            solicitudTarjetaRecargableOutEntity.setDescError(descError.isEmpty() ? null : descError);
            return respuestaFactory.crearRespuestaOk(SolicitudTarjetaRecargableOutEntity.class,
                    solicitudTarjetaRecargableOutEntity);
        } else {
            LOGGER.info(MENSAJE_ERROR_EN_STORE, TARJETA_RECARGABLE_SCHEMA, TARJETA_RECARGABLE_PACKAGE,
                    TARJETA_RECARGABLE_PROCEDURE, spAtributos);
            throw new DAOException((String) respuesta.get(OUT_DESC_ERROR));
        }
    }

    /**
     * Consultar DB.
     *
     * @param in
     *            the in
     * @return the map
     * @throws SQLException
     *             the SQL exception
     */
    public Map<String, Object> consultarDB(SqlParameterSource in) throws SQLException {
        List<SqlParameter> parametros = new ArrayList<SqlParameter>();
        parametros.add(new SqlOutParameter(OUT_RETURN, Types.NUMERIC));
        parametros.add(new SqlParameter(IN_ATRIBUTOS, Types.VARCHAR));
        parametros.add(new SqlInOutParameter(OUT_NRO_GESTION, Types.VARCHAR));
        parametros.add(new SqlInOutParameter(OUT_COD_RET, Types.NUMERIC));
        parametros.add(new SqlInOutParameter(OUT_DESC_ERROR, Types.VARCHAR));

        return consultarFuncion(TARJETA_RECARGABLE_SCHEMA, TARJETA_RECARGABLE_PACKAGE, TARJETA_RECARGABLE_PROCEDURE, in,
                parametros.toArray(new SqlParameter[parametros.size()]));
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.tarjetarecargable.dao.
     * TarjetaRecargableDAO#comprobanteSolicitudTarjetaRecargable(ar.com.
     * santanderrio.obp.servicios.tarjetarecargable.web.view.
     * DatosComprobanteSolicitudTarjetaRecargableView)
     */
    @Override
    public Reporte comprobanteSolicitudTarjetaRecargable(
            DatosComprobanteSolicitudTarjetaRecargableView datosComprobante) {

        Reporte reporte = new Reporte();

        try {
            JasperReport jasperReport = (JasperReport) JRLoader
                    .loadObject(fileJasperComprobanteSolicitudTarjetaRecargable.getInputStream());
            jasperReport.setWhenNoDataType(WhenNoDataTypeEnum.ALL_SECTIONS_NO_DETAIL);

            HashMap<String, Object> parameters = new HashMap<String, Object>();
            parameters.put(LOGO_CABECERA, logoCabecera.getFile().getPath());
            parameters.put(LOGO_VISA, logoVisa.getFile().getPath());
            parameters.put(NOMBRE_APELLIDO,
                    datosComprobante.getNombreAdic() + ESPACIO + datosComprobante.getApellidoAdic());
            parameters.put(TIPO_NRO_DOCUMENTO,
                    datosComprobante.getTipoDocumentoAdic() + ESPACIO + datosComprobante.getNroDocumentoAdic());
            parameters.put(FECHA_NACIMIENTO, datosComprobante.getFechaNacimientoAdic());
            parameters.put(NACIONALIDAD, datosComprobante.getNacionalidad());
            parameters.put(SEXO, datosComprobante.getSexo());
            parameters.put(ESTADO_CIVIL, datosComprobante.getEstadoCivil());
            parameters.put(DOMICILIO, datosComprobante.getDomicilio());
            parameters.put(NUMERO, datosComprobante.getNro());
            parameters.put(PISO_DEPTO, datosComprobante.getPisoDepto());
            parameters.put(LOCALIDAD, datosComprobante.getLocalidadBarrio());
            parameters.put(COD_POSTAL, datosComprobante.getCodigoPostal());
            parameters.put(PROVINCIA, datosComprobante.getProvincia());
            parameters.put(TELEFONO, datosComprobante.getCodArea() + ESPACIO + datosComprobante.getTelefono());
            parameters.put(NRO_COMPROBANTE, datosComprobante.getNroGestion());
            parameters.put(FECHA_OPERACION, datosComprobante.getFechaHora());
            parameters.put(LOGO_CIERRE, logoCierre.getFile().getPath());

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);

            byte[] byteArray = outStream.toByteArray();
            reporte.setBytes(byteArray);
            reporte.setNombre(
                    "Comprobante_Solicitud_Tarjeta_Recargable_" + datosComprobante.getNroGestion() + PDF_EXTENSION);
            reporte.setTipoArchivo(TipoArchivoEnum.PDF);

        } catch (JRException ex) {
            LOGGER.error(ex.getMessage(), ex);
            throw new ISBANRuntimeException(ex);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ISBANRuntimeException(e);
        }
        return reporte;
    }
}
