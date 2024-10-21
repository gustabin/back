package ar.com.santanderrio.obp.servicios.consent.service;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.entities.Estadistica;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionConsentSSO;
import ar.com.santanderrio.obp.servicios.consent.dto.ConsentDTO;
import ar.com.santanderrio.obp.servicios.consent.dto.UserInfoDTO;
import ar.com.santanderrio.obp.servicios.consent.exceptions.UserNotFoundException;
import ar.com.santanderrio.obp.servicios.consent.usecase.GetUserConsentsUseCase;
import ar.com.santanderrio.obp.servicios.consent.usecase.RevokeUserConsentUseCase;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SSOConsentServiceImpl implements SSOConsentService {
    private static final Logger logger = LoggerFactory.getLogger(SSOConsentServiceImpl.class);

    @Autowired
    RespuestaFactory respuestaFactory;

    @Autowired
    EstadisticaManager estadisticaManager;

    @Autowired
    GetUserConsentsUseCase userConsentsUseCase;

    @Autowired
    RevokeUserConsentUseCase revokeUserConsentUseCase;

    @Autowired
    SesionConsentSSO sesionConsentSSO;

    @Override
    public Respuesta<UserInfoDTO> getUserConsents(final Cliente cliente) {
        Respuesta<UserInfoDTO> respuesta;
        Estadistica estadistica = new Estadistica();
        estadistica.setCodigoTransaccion(EstadisticasConstants.SSO_GET_CONSENTS);
        try {
            final UserInfoDTO userInfoDTO = userConsentsUseCase.execute(cliente);
            respuesta = respuestaFactory.crearRespuestaOk(userInfoDTO);
            estadistica.setCodigoError("1");
        } catch (UserNotFoundException userNotFoundException) {
            logger.info("User not found on sso");
            respuesta = respuestaFactory.crearRespuestaWarning(
                    userNotFoundException.getEmptyUserInfoDTO(),
                    null,
                    TipoError.CONSENT_USER_NOT_FOUND,
                    null);
            estadistica.setCodigoError("3");
        } catch (BusinessException boException) {
            logger.info("Error getting user consents", boException);
            respuesta = respuestaFactory.crearRespuestaError(null, TipoError.ERROR_GENERICO, "");
            estadistica.setCodigoError("2");
        }
        estadisticaManager.add(estadistica);
        return respuesta;
    }

    @Override
    public Respuesta<Void> revokeUserConsent(final String userId, final String consentedClientId) {
        Respuesta<Void> respuesta;
        Estadistica estadistica = buildEstadisticaRevoke(consentedClientId);
        try {
            revokeUserConsentUseCase.execute(userId, consentedClientId);
            respuesta = respuestaFactory.crearRespuestaOk(Void.class);
            estadistica.setCodigoError("1");
        } catch (BusinessException boException) {
            logger.info("Error revoking user consents", boException);
            respuesta = respuestaFactory.crearRespuestaError(null, TipoError.ERROR_GENERICO, "");
            estadistica.setCodigoError("2");
        }
        estadisticaManager.add(estadistica);
        return respuesta;
    }

    private Estadistica buildEstadisticaRevoke(String consentedClientId) {
        Estadistica estadistica = new Estadistica();
        estadistica.setCodigoTransaccion(EstadisticasConstants.SSO_REVOKE_CONSENT);
        estadistica.setNroComprobante(consentedClientId);
        try {
            List<String> consentedAccounts = getFirstConsentById(consentedClientId).getConsentedAccounts();
            String accountNumber = (consentedAccounts != null && !consentedAccounts.isEmpty())
                    ? consentedAccounts.get(0)
                    : "not specified";
            estadistica.setNroCtaOrigen(accountNumber);
        } catch (BusinessException boException) {
            //silently digest
            estadistica.setNroCtaOrigen("not specified");
        }
        return estadistica;
    }

    private ConsentDTO getFirstConsentById(String consentClientId) throws BusinessException {
        for (ConsentDTO consentDTO: sesionConsentSSO.getUserConsents()) {
            if (consentClientId.equals(consentDTO.getClientId())) {
               return consentDTO;
            }
        }
        throw new BusinessException("consent not found");
    }
}
