package ar.com.santanderrio.obp.servicios.consent.service;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.api.common.config.ApiException;
import ar.com.santanderrio.obp.servicios.api.common.config.ErrorResponse;
import ar.com.santanderrio.obp.servicios.api.sso.SSOAssertions;
import ar.com.santanderrio.obp.servicios.api.sso.consent.ConsentApi;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.entities.Estadistica;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionConsentSSO;
import ar.com.santanderrio.obp.servicios.consent.dto.ConsentDTO;
import ar.com.santanderrio.obp.servicios.consent.dto.UserInfoDTO;
import ar.com.santanderrio.obp.servicios.consent.usecase.GetUserConsentsUseCase;
import ar.com.santanderrio.obp.servicios.consent.usecase.RevokeUserConsentUseCase;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ConsentServiceTests {

    @InjectMocks
    SSOConsentServiceImpl ssoConsentService;

    @InjectMocks
    @Spy
    RespuestaFactory respuestaFactory = new RespuestaFactory();

    @Mock
    MensajeBO mensajeBO;

    @Mock
    EstadisticaManager estadisticaManager;

    @Mock
    GetUserConsentsUseCase userConsentsUseCase;

    @Spy
    @InjectMocks
    RevokeUserConsentUseCase revokeUserConsentUseCase = new RevokeUserConsentUseCase();

    @Mock
    ConsentApi consentApi;

    @Spy
    SesionConsentSSO sesionConsentSSO = new SesionConsentSSO();

    ArgumentCaptor<Estadistica> captorEstadistica = ArgumentCaptor.forClass(Estadistica.class);

    @Test
    public void getUserConsents_ok() throws BusinessException {
        //setup
        mockUserConsentsUseCase();
        mockSaveEstadistica();

        //execution
        Respuesta<UserInfoDTO> respuesta = ssoConsentService.getUserConsents(SSOAssertions.cliente);
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());

        UserInfoDTO userInfoDTO = respuesta.getRespuesta();
        Assert.assertEquals(SSOAssertions.userModel.getId(), userInfoDTO.getUserId());
        Assert.assertNotNull(userInfoDTO.getConsents());
        Assert.assertFalse(userInfoDTO.getConsents().isEmpty());
        ConsentDTO consentDTO = userInfoDTO.getConsents().get(0);
        Assert.assertEquals(SSOAssertions.clientModel.getName(), consentDTO.getClientName());
        Assert.assertEquals(SSOAssertions.consentModel.getClientId(), consentDTO.getClientId());
        Assert.assertEquals(SSOAssertions.consentModel.getCreatedDate(), consentDTO.getCreatedDate());
        Assert.assertEquals(SSOAssertions.userModel.getAttributes().get("pkce-test").size(), consentDTO.getConsentedAccounts().size());
        Assert.assertEquals(SSOAssertions.userModel.getAttributes().get("pkce-test").get(0), consentDTO.getConsentedAccounts().get(0));

        verify(estadisticaManager, times(1)).add(captorEstadistica.capture());
        Assert.assertEquals(EstadisticasConstants.SSO_GET_CONSENTS,
                captorEstadistica.getValue().getCodigoTransaccion());
        Assert.assertEquals("1",
                captorEstadistica.getValue().getCodigoError());
    }

    @Test
    public void getUserConsents_BOException() throws BusinessException {
        //setup
        mockUserConsentsUseCase_BOException();
        mockSaveEstadistica();

        //execution
        Respuesta<UserInfoDTO> respuesta = ssoConsentService.getUserConsents(SSOAssertions.cliente);
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
        Assert.assertEquals(TipoError.ERROR_GENERICO.getDescripcion(),
                respuesta.getItemsMensajeRespuesta().get(0).getTipoError());

        verify(estadisticaManager, times(1)).add(captorEstadistica.capture());
        Assert.assertEquals(EstadisticasConstants.SSO_GET_CONSENTS,
                captorEstadistica.getValue().getCodigoTransaccion());
        Assert.assertEquals("2",
                captorEstadistica.getValue().getCodigoError());
    }

    private void mockUserConsentsUseCase() throws BusinessException {
        when(userConsentsUseCase.execute(any(Cliente.class)))
                .thenReturn(SSOAssertions.userInfoDto);
    }

    private void mockUserConsentsUseCase_BOException() throws BusinessException {
        BusinessException exception = new BusinessException("there was an error executing /users requests");
        when(userConsentsUseCase.execute(any(Cliente.class)))
                .thenThrow(exception);
    }

    @Test
    public void revokeUserConsent_ok() {
        //setup
        mockSaveEstadistica();
        List<ConsentDTO> consentList = new ArrayList<ConsentDTO>();
        consentList.add(SSOAssertions.consentDTO);
        sesionConsentSSO.setUserConsents(consentList);

        //execution
        Respuesta<Void> respuesta = ssoConsentService.revokeUserConsent(SSOAssertions.userInfoDto.getUserId(),
                SSOAssertions.userInfoDto.getConsents().get(0).getClientId());
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
        Assert.assertEquals(0, sesionConsentSSO.getUserConsents().size());

        verify(estadisticaManager, times(1)).add(captorEstadistica.capture());
        Assert.assertEquals(EstadisticasConstants.SSO_REVOKE_CONSENT,
                captorEstadistica.getValue().getCodigoTransaccion());
        Assert.assertEquals("1",
                captorEstadistica.getValue().getCodigoError());
        Assert.assertEquals(SSOAssertions.consentDTO.getClientId(),
                captorEstadistica.getValue().getNroComprobante());
        Assert.assertEquals(SSOAssertions.userInfoDto.getConsents().get(0).getConsentedAccounts().get(0),
                captorEstadistica.getValue().getNroCtaOrigen());
        Assert.assertEquals(SSOAssertions.userModel.getAttributes().get("pkce-test").get(0),
                captorEstadistica.getValue().getNroCtaOrigen());
    }

    @Test
    public void revokeUserConsent_revokeFailure() throws BusinessException {
        //setup
        mockSaveEstadistica();
        mockRevokeConsent_failure();

        List<ConsentDTO> consentList = new ArrayList<ConsentDTO>();
        consentList.add(SSOAssertions.consentDTO);
        sesionConsentSSO.setUserConsents(consentList);

        //execution
        Respuesta<Void> respuesta = ssoConsentService.revokeUserConsent(SSOAssertions.userInfoDto.getUserId(),
                SSOAssertions.userInfoDto.getConsents().get(0).getClientId());
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
        Assert.assertEquals(0, sesionConsentSSO.getUserConsents().size());

        verify(estadisticaManager, times(1)).add(captorEstadistica.capture());
        Assert.assertEquals(EstadisticasConstants.SSO_REVOKE_CONSENT,
                captorEstadistica.getValue().getCodigoTransaccion());
        Assert.assertEquals("2",
                captorEstadistica.getValue().getCodigoError());
        Assert.assertEquals(SSOAssertions.consentDTO.getClientId(),
                captorEstadistica.getValue().getNroComprobante());
    }

    @Test
    public void revokeUserConsent_estadisticaNotBuilt() throws BusinessException {
        //setup
        mockSaveEstadistica();
        mockRevokeConsentUseCase();
        sesionConsentSSO.setUserConsents(Collections.<ConsentDTO>emptyList());

        //execution
        Respuesta<Void> respuesta = ssoConsentService.revokeUserConsent(SSOAssertions.userInfoDto.getUserId(),
                SSOAssertions.userInfoDto.getConsents().get(0).getClientId());
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
        Assert.assertEquals(0, sesionConsentSSO.getUserConsents().size());

        verify(estadisticaManager, times(1)).add(captorEstadistica.capture());
        Assert.assertEquals(EstadisticasConstants.SSO_REVOKE_CONSENT,
                captorEstadistica.getValue().getCodigoTransaccion());
        Assert.assertEquals("1",
                captorEstadistica.getValue().getCodigoError());
        Assert.assertEquals(SSOAssertions.consentDTO.getClientId(),
                captorEstadistica.getValue().getNroComprobante());
        Assert.assertEquals("not specified",
                captorEstadistica.getValue().getNroCtaOrigen());
    }

    public void revokeUserConsent_estadisticaNotBuilt2() throws BusinessException {
        //setup
        mockSaveEstadistica();
        mockRevokeConsentUseCase();
        sesionConsentSSO.setUserConsents(Collections.<ConsentDTO>emptyList());

        //execution
        Respuesta<Void> respuesta = ssoConsentService.revokeUserConsent(SSOAssertions.userInfoDto.getUserId(),
                SSOAssertions.userInfoDto.getConsents().get(0).getClientId());
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
        Assert.assertEquals(0, sesionConsentSSO.getUserConsents().size());

        verify(estadisticaManager, times(1)).add(captorEstadistica.capture());
        Assert.assertEquals(EstadisticasConstants.SSO_REVOKE_CONSENT,
                captorEstadistica.getValue().getCodigoTransaccion());
        Assert.assertEquals("1",
                captorEstadistica.getValue().getCodigoError());
        Assert.assertEquals("not specified",
                captorEstadistica.getValue().getNroComprobante());
    }

    private void mockSaveEstadistica() {
        when(estadisticaManager.add(any(Estadistica.class)))
                .thenReturn(true);
    }

    private void mockRevokeConsent_failure() throws BusinessException {
        ApiException apiException = new ApiException(ErrorResponse.emptyErrorResponse(),
                new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
        doThrow(apiException).when(consentApi).revokeConsentByUserId(anyString(), anyString());
    }

    private void mockRevokeConsentUseCase() throws BusinessException {
        doNothing().when(revokeUserConsentUseCase).execute(anyString(), anyString());
    }
}
