/*
 * 
 */
package ar.com.santanderrio.obp.servicios.agenda.destinatarios.bo.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.bo.CasuisticaAltaDestinatarios;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.dto.ComprobanteAltaDestinatarioDTO;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.dto.ConfiguracionAltaDestinatarioCBUDTO;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.AgendaDestinatarioOutEntity;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.ValidacionCuentaInCBUEntity;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.ValidacionCuentaOutCBUEntity;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.util.ErrorAgendaDestinatariosEnum;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;

/**
 * The Class CasuisticaAltaDestinatarioImpl.
 */
@Component
public class CasuisticaAltaDestinatarioImpl implements CasuisticaAltaDestinatarios {

    /** The respuestaFactory. */
    @Autowired
    private RespuestaFactory respuestaFactory;

    /** The Constant CODIGO_OK. */
    private static final String CODIGO_OK = "00000000";

    /** The Constant CODIGO_ERROR_36. */
    private static final String CODIGO_ERROR_36 = "10000036";

    /** The Constant CODIGO_ERROR_56. */
    private static final String CODIGO_ERROR_56 = "10000056";

    /** The Constant CODIGO_ERROR_57. */
    private static final String CODIGO_ERROR_57 = "10000057";

    /** The Constant CODIGO_ERROR_505. */
    private static final String CODIGO_ERROR_505 = "10000505";

    /** The Constant CODIGO_ERROR_65. */
    private static final String CODIGO_ERROR_65 = "10000065";

    /** The Constant CODIGO_ERROR_66. */
    private static final String CODIGO_ERROR_66 = "10000066";

    /** The Constant CODIGO_ERROR_70. */
    private static final String CODIGO_ERROR_70 = "10000070";

    /** The Constant CODIGO_ERROR_72. */
    private static final String CODIGO_ERROR_72 = "10000072";

    /** The Constant CODIGO_ERROR_74. */
    private static final String CODIGO_ERROR_74 = "10000074";

    /** The Constant CODIGO_ERROR_76. */
    private static final String CODIGO_ERROR_76 = "10000076";

    /** The Constant CODIGO_ERROR_29. */
    private static final String CODIGO_ERROR_29 = "10011629";

    /** The Constant CODIGO_ERROR_46. */
    private static final String CODIGO_ERROR_46 = "10011646";

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.agenda.destinatarios.bo.
     * CasuisticaAltaDestinatarios#crearRespuestaConfiguracionCbu(ar.com.
     * santanderrio.obp.servicios.agenda.destinatarios.entity.
     * ValidacionCuentaOutCBUEntity)
     */
    @Override
    public Respuesta<ConfiguracionAltaDestinatarioCBUDTO> crearRespuestaConfiguracionCbu(
            ValidacionCuentaOutCBUEntity dto) {
        if (CODIGO_OK.equals(dto.getCodigoRetornoExtendido())) {
            return crearOkSiParametrosNoEnBlanco(dto);
        }
        if (esErrorServicioManual(dto.getCodigoRetornoExtendido())) {
            return crearError(ErrorAgendaDestinatariosEnum.ERROR_CARGA_MANUAL);
        } else if (esErrorMismaPantalla(dto.getCodigoRetornoExtendido())) {
            return crearError(ErrorAgendaDestinatariosEnum.ERROR_SERVICIO_CBU);
        }
        return crearError(ErrorAgendaDestinatariosEnum.ERROR_SERVICIO_CBU);
    }

    /**
     * Crear ok si parametros no en blanco.
     *
     * @param dto
     *            the dto
     * @return the respuesta
     */
    private Respuesta<ConfiguracionAltaDestinatarioCBUDTO> crearOkSiParametrosNoEnBlanco(
            ValidacionCuentaOutCBUEntity dto) {
        if (camposEnBlanco(dto)) {
            return crearError(ErrorAgendaDestinatariosEnum.ERROR_CARGA_MANUAL);
        }
        return respuestaFactory.crearRespuestaOk(ConfiguracionAltaDestinatarioCBUDTO.class,
                new ConfiguracionAltaDestinatarioCBUDTO(dto, false, dto.getCuentaDestino()));
    }

    /**
     * devuelve true en caso de que los parametros clave sean espacios y false
     * en caso contrario.
     *
     * @param dto
     *            the dto
     * @return boolean
     */
    private boolean camposEnBlanco(ValidacionCuentaOutCBUEntity dto) {
        return StringUtils.isBlank(dto.getCuit()) || StringUtils.isBlank(dto.getBancoReceptor())
                || StringUtils.isBlank(dto.getTitular());
    }

    /**
     * Devuelve true si el codigo de error del servicio corresponde con alguno
     * de los dados en el dtf para carga no manual.
     *
     * @param codigoError
     *            the codigo error
     * @return true, if successful
     */
    private boolean esErrorMismaPantalla(String codigoError) {
        List<String> codigosError = new ArrayList<String>(
                Arrays.asList(CODIGO_ERROR_36, CODIGO_ERROR_56, CODIGO_ERROR_57, CODIGO_ERROR_505));
        return codigosError.contains(StringUtils.right(codigoError, 8));
    }

    /**
     * Devuelve true si el codigo de error del servicio corresponde con alguno
     * de los dados en el dtf para carga manual.
     *
     * @param codigoError
     *            the codigo error
     * @return true, if successful
     */
    private boolean esErrorServicioManual(String codigoError) {
        List<String> codigosError = new ArrayList<String>(Arrays.asList(CODIGO_ERROR_65, CODIGO_ERROR_66,
                CODIGO_ERROR_70, CODIGO_ERROR_72, CODIGO_ERROR_74, CODIGO_ERROR_76));
        return codigosError.contains(StringUtils.right(codigoError, 8));
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.agenda.destinatarios.bo.
     * CasuisticaAltaDestinatarios#crearDerivacionAltaRio(java.lang.String)
     */
    @Override
    public Respuesta<ConfiguracionAltaDestinatarioCBUDTO> crearDerivacionAltaRio(String numeroCuenta) {
        return respuestaFactory.crearRespuestaOk(ConfiguracionAltaDestinatarioCBUDTO.class,
                new ConfiguracionAltaDestinatarioCBUDTO(new ValidacionCuentaOutCBUEntity(), true, numeroCuenta));
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.agenda.destinatarios.bo.
     * CasuisticaAltaDestinatarios#crearErrorCbuInvalido()
     */
    @Override
    public Respuesta<ConfiguracionAltaDestinatarioCBUDTO> crearErrorCbuInvalido() {
        return crearError(ErrorAgendaDestinatariosEnum.CBU_INVALIDO);
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.agenda.destinatarios.bo.
     * CasuisticaAltaDestinatarios#crearErrorCuentaPropia()
     */
    @Override
    public Respuesta<ConfiguracionAltaDestinatarioCBUDTO> crearErrorCuentaPropia() {
        return crearError(ErrorAgendaDestinatariosEnum.CBU_CUENTA_PROPIA);
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.agenda.destinatarios.bo.
     * CasuisticaAltaDestinatarios#crearErrorSinMedioDePago()
     */
    @Override
    public Respuesta<ConfiguracionAltaDestinatarioCBUDTO> crearErrorSinMedioDePago() {
        return crearError(ErrorAgendaDestinatariosEnum.SIN_MEDIO_DE_PAGO);
    }

    /**
     * crea el error usando el tag, tipo de error y codigo de mensaje de
     * ErrorAgendaDestinatariosEnum.
     *
     * @param error
     *            the error
     * @return the respuesta
     */
    private Respuesta<ConfiguracionAltaDestinatarioCBUDTO> crearError(ErrorAgendaDestinatariosEnum error) {
        return respuestaFactory.crearRespuestaError(error.getTag(), error.getTipoError(), error.getCodigoMensaje());
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.agenda.destinatarios.bo.
     * CasuisticaAltaDestinatarios#crearRespuestaErrorServicio()
     */
    @Override
    public Respuesta<ConfiguracionAltaDestinatarioCBUDTO> crearRespuestaErrorServicio() {
        return crearError(ErrorAgendaDestinatariosEnum.ERROR_SERVICIO_CBU);
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.agenda.destinatarios.bo.
     * CasuisticaAltaDestinatarios#crearRespuestaErrorServicioEnvioEfectivo()
     */
    @Override
    public Respuesta<ComprobanteAltaDestinatarioDTO> crearRespuestaErrorServicioEnvioEfectivo() {
        Respuesta<ConfiguracionAltaDestinatarioCBUDTO> res = crearError(
                ErrorAgendaDestinatariosEnum.ERROR_SERVICIO_CBU_ENVIO_EFECTIVO);
        return respuestaFactory.crearRespuestaError(ComprobanteAltaDestinatarioDTO.class,
                res.getItemsMensajeRespuesta());
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.agenda.destinatarios.bo.
     * CasuisticaAltaDestinatarios#crearRespuestaEnvioEfectivo(ar.com.
     * santanderrio.obp.servicios.agenda.destinatarios.entity.
     * AgendaDestinatarioOutEntity)
     */
    @Override
    public Respuesta<ComprobanteAltaDestinatarioDTO> crearRespuestaEnvioEfectivo(
            AgendaDestinatarioOutEntity outEntity) {
        if (outEntity == null) {
            return crearRespuestaErrorServicioEnvioEfectivo();
        }
        if (CODIGO_OK.equals(outEntity.getCodigoRetornoExtendido())) {
            return respuestaFactory.crearRespuestaOk(ComprobanteAltaDestinatarioDTO.class,
                    new ComprobanteAltaDestinatarioDTO(outEntity));

        } else if (CODIGO_ERROR_29.equals(outEntity.getCodigoRetornoExtendido())
                || CODIGO_ERROR_46.equals(outEntity.getCodigoRetornoExtendido())) {
            Respuesta<ConfiguracionAltaDestinatarioCBUDTO> res = crearError(
                    ErrorAgendaDestinatariosEnum.ERROR_YA_AGENDADO_ENVIO_EFECTIVO);
            return respuestaFactory.crearRespuestaError(ComprobanteAltaDestinatarioDTO.class,
                    res.getItemsMensajeRespuesta());
        }
        return crearRespuestaErrorServicioEnvioEfectivo();
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.agenda.destinatarios.bo.
     * CasuisticaAltaDestinatarios#validarInEntity(ar.com.santanderrio.obp.
     * servicios.agenda.destinatarios.entity.ValidacionCuentaInCBUEntity)
     */
    @Override
    public Respuesta<ValidacionCuentaInCBUEntity> validarInEntity(ValidacionCuentaInCBUEntity inEntity) {
        return respuestaFactory.validate(inEntity);
    }

}
