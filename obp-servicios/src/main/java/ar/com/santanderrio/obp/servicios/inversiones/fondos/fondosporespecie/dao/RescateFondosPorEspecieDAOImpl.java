package ar.com.santanderrio.obp.servicios.inversiones.fondos.fondosporespecie.dao;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.impl.BaseDatoDAOImpl;
import ar.com.santanderrio.obp.base.security.aop.annotation.TargetSystem;
import ar.com.santanderrio.obp.base.security.credential.DataBase;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.fondosporespecie.entity.ConfirmarRescateEspecieIn;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.fondosporespecie.entity.ConfirmarRescateEspecieOut;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.fondosporespecie.entity.ConsultarRescateEspecieIn;

@Component
@TargetSystem(DataBase.BPRIV)
public class RescateFondosPorEspecieDAOImpl extends BaseDatoDAOImpl implements RescateFondosPorEspecieDAO {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(RescateFondosPorEspecieDAOImpl.class);

    /** SCHEMA. */
    private static final String SCHEMA = "OPICS";

    /** PACKAGE contrato. */
    private static final String PACKAGE = "PKG_FONDOFORM";

    /** PROCEDURE CONSULTAR. */
    private static final String STORE_CONSULTAR = "EXISTE_FONDO";

    /** PROCEDURE CONFIRMAR. */
    private static final String STORE_CONFIRMAR = "REGISTRAR_FONDO";

    private static final String COD_ERROR = "-1";

    private static final String IN_NUP = "P_NUP";

    private static final String IN_BANCA = "P_BANCA";

    private static final String IN_CODIGO_FONDO = "P_CODIGO_FONDO";

    private static final String IN_CANAL = "P_CANAL";

    private static final String IN_DNI = "P_DNI";

    private static final String IN_CUITCUIL = "P_CUIT_CUIL";

    private static final String IN_ESPECIE = "P_ESPECIE";

    private static final String IN_NOMBRE_FONDO = "P_NOMBRE_FONDO";

    private static final String IN_MONEDA_FONDO = "P_MONEDA_FONDO";

    private static final String IN_CUENTA_TITULO_ORIGEN_FCI = "P_CUENTA_TITULO_ORIGEN_FCI";

    private static final String IN_TIPO_RESCATE = "P_TIPO_RESCATE";

    private static final String IN_MONTO_RESCATE = "P_MONTO_RESCATE";

    private static final String IN_CUENTA_TITULO_DESTINO_FCI = "P_CUENTA_TITULO_DESTINO_FCI";

    private static final String IN_NUP_EMPRESA = "P_NUP_EMPRESA";

    private static final String IN_NUP_FIRMANTE = "P_NUP_FIRMANTE";

    private static final String IN_TIPO_PERSONA = "P_TIPO_PERSONA";

    private static final String IN_SALDO = "P_Saldo";

    private static final String OUT_RESULTADO = "P_RESULTADO";

    private static final String OUT_ERROR_TECNICO = "P_ERR_TECNICO";

    private static final String OUT_ERROR_AMIGABLE = "P_ERR_AMIGABLE";

    private static final String OUT_NRO_ORDEN = "p_Nro_Orden";

    @Override
    public String consultarRescate(ConsultarRescateEspecieIn entity) {
        List<SqlParameter> parametros = new ArrayList<SqlParameter>();
        parametros.add(new SqlParameter(IN_NUP, Types.VARCHAR));
        parametros.add(new SqlParameter(IN_CODIGO_FONDO, Types.VARCHAR));
        parametros.add(new SqlParameter(IN_BANCA, Types.VARCHAR));

        parametros.add(new SqlOutParameter(OUT_RESULTADO, Types.VARCHAR));
        parametros.add(new SqlOutParameter(OUT_ERROR_TECNICO, Types.VARCHAR));

        SqlParameterSource in = new MapSqlParameterSource().addValue(IN_NUP, entity.getNup())
                .addValue(IN_CODIGO_FONDO, entity.getCodigoFondo()).addValue(IN_BANCA, entity.getBanca());

        try {
            Map<String, Object> respuesta = consultar(SCHEMA, PACKAGE, STORE_CONSULTAR, in,
                    parametros.toArray(new SqlParameter[parametros.size()]));
            return (String) respuesta.get(OUT_RESULTADO);
        } catch (Exception ex) {
            LOGGER.error("Error al consumir el store {}.{}.{} ", SCHEMA, PACKAGE, STORE_CONSULTAR, ex);
            return COD_ERROR;
        }
    }

    @Override
    public ConfirmarRescateEspecieOut confirmarRescate(ConfirmarRescateEspecieIn entity) {
        List<SqlParameter> parametros = new ArrayList<SqlParameter>();
        parametros.add(new SqlParameter(IN_NUP, Types.VARCHAR));
        parametros.add(new SqlParameter(IN_CODIGO_FONDO, Types.VARCHAR));
        parametros.add(new SqlParameter(IN_BANCA, Types.VARCHAR));
        parametros.add(new SqlParameter(IN_CANAL, Types.VARCHAR));
        parametros.add(new SqlParameter(IN_DNI, Types.VARCHAR));
        parametros.add(new SqlParameter(IN_CUITCUIL, Types.VARCHAR));
        parametros.add(new SqlParameter(IN_ESPECIE, Types.VARCHAR));
        parametros.add(new SqlParameter(IN_NOMBRE_FONDO, Types.VARCHAR));
        parametros.add(new SqlParameter(IN_MONEDA_FONDO, Types.VARCHAR));
        parametros.add(new SqlParameter(IN_CUENTA_TITULO_ORIGEN_FCI, Types.VARCHAR));
        parametros.add(new SqlParameter(IN_TIPO_RESCATE, Types.VARCHAR));
        parametros.add(new SqlParameter(IN_MONTO_RESCATE, Types.VARCHAR));
        parametros.add(new SqlParameter(IN_CUENTA_TITULO_DESTINO_FCI, Types.VARCHAR));
        parametros.add(new SqlParameter(IN_NUP_EMPRESA, Types.VARCHAR));
        parametros.add(new SqlParameter(IN_NUP_FIRMANTE, Types.VARCHAR));
        parametros.add(new SqlParameter(IN_TIPO_PERSONA, Types.VARCHAR));
        parametros.add(new SqlParameter(IN_SALDO, Types.VARCHAR));
        parametros.add(new SqlOutParameter(OUT_NRO_ORDEN, Types.VARCHAR));
        parametros.add(new SqlOutParameter(OUT_RESULTADO, Types.VARCHAR));
        parametros.add(new SqlOutParameter(OUT_ERROR_TECNICO, Types.VARCHAR));
        parametros.add(new SqlOutParameter(OUT_ERROR_AMIGABLE, Types.VARCHAR));

        SqlParameterSource in = new MapSqlParameterSource().addValue(IN_NUP, entity.getNup())
                .addValue(IN_CODIGO_FONDO, entity.getCodigoFondo()).addValue(IN_BANCA, entity.getBanca())
                .addValue(IN_CANAL, entity.getCanal()).addValue(IN_DNI, entity.getDni())
                .addValue(IN_CUITCUIL, entity.getCuitcuil()).addValue(IN_ESPECIE, entity.getEspecie())
                .addValue(IN_NOMBRE_FONDO, entity.getNombreFondo()).addValue(IN_MONEDA_FONDO, entity.getMonedaFondo())
                .addValue(IN_CUENTA_TITULO_ORIGEN_FCI, entity.getCuentaTituloOrigenFCI())
                .addValue(IN_TIPO_RESCATE, entity.getTipoRescate()).addValue(IN_MONTO_RESCATE, entity.getMontoRescate())
                .addValue(IN_CUENTA_TITULO_DESTINO_FCI, entity.getCuentaTituloDestinoFCI())
                .addValue(IN_NUP_EMPRESA, entity.getNupEmpresa()).addValue(IN_NUP_FIRMANTE, entity.getNupFirmante())
                .addValue(IN_TIPO_PERSONA, entity.getTipoPersona()).addValue(IN_SALDO, entity.getCuotaPartes());

        try {
            Map<String, Object> respuesta = consultar(SCHEMA, PACKAGE, STORE_CONFIRMAR, in,
                    parametros.toArray(new SqlParameter[parametros.size()]));
            return obtenerOutput(respuesta, false);
        } catch (Exception ex) {
            LOGGER.error("Error al consumir el store {}.{}.{} ", SCHEMA, PACKAGE, STORE_CONFIRMAR, ex);
            return obtenerOutput(null, true);
        }
    }

    /**
     * Armar respuesta recibida del backend o notificar -1.
     * 
     * @param respuesta
     * @param conError
     * @return
     */
    private ConfirmarRescateEspecieOut obtenerOutput(Map<String, Object> respuesta, boolean conError) {
        ConfirmarRescateEspecieOut confirmarRescateEspecieOut = new ConfirmarRescateEspecieOut();
        if (conError) {
            confirmarRescateEspecieOut.setCodigoRespuesta(COD_ERROR);
            LOGGER.info("La consulta rescate de fondos rompe y se retorna valor controlado: {}",
                    confirmarRescateEspecieOut.getCodigoRespuesta());
        } else {
            confirmarRescateEspecieOut.setCodigoRespuesta((String) respuesta.get(OUT_RESULTADO));
            confirmarRescateEspecieOut.setNroOrden((String) respuesta.get(OUT_NRO_ORDEN));
            LOGGER.info("La consulta rescate de fondos devuelvoe codigo: {}",
                    confirmarRescateEspecieOut.getCodigoRespuesta());
        }
        return confirmarRescateEspecieOut;
    }

}
