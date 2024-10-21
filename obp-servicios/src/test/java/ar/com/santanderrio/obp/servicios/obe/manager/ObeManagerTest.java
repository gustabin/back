package ar.com.santanderrio.obp.servicios.obe.manager;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;

import org.apache.cxf.jaxrs.ext.MessageContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.login.manager.LoginManager;
import ar.com.santanderrio.obp.servicios.login.view.LogOutResponseView;
import ar.com.santanderrio.obp.servicios.obe.bo.ObeToken;
import ar.com.santanderrio.obp.servicios.token.externos.TokenExternoDTO;
import ar.com.santanderrio.obp.servicios.token.externos.dto.TokenDTO;
import ar.com.santanderrio.obp.servicios.token.externos.view.TokenView;

@RunWith(MockitoJUnitRunner.class)
public class ObeManagerTest {

    /** The login manager. */
    @InjectMocks
    private ObeManagerImpl obeManager;

    @Mock
    private LoginManager loginManager;

    @Mock
    private EstadisticaManager estadisticaManager;

    /** The seguro BO. */
    @Mock
    private ObeToken seguroToken;
    @Mock
    private SesionCliente sesionCliente;

    @Test
    public void obtenerTokenEncriptado() {
        MessageContext mc = Mockito.mock(MessageContext.class);
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        when(mc.getHttpServletRequest()).thenReturn(request);

        String token = "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";
        String url = "http://des4z1.ar.bsch:9190/CanalCryptServlet";

        Respuesta<TokenDTO> respuestaMensaje = new Respuesta<TokenDTO>();
        TokenDTO tokenDTO = new TokenDTO();
        tokenDTO.setTokenFirmado(token);
        tokenDTO.setUrl(url);
        respuestaMensaje.setRespuesta(tokenDTO);
        respuestaMensaje.setEstadoRespuesta(EstadoRespuesta.OK);
        when(sesionCliente.getCliente()).thenReturn(null);

        when(seguroToken.armarToken(Matchers.any(MessageContext.class), Matchers.any(TokenExternoDTO.class)))
                .thenReturn(respuestaMensaje);

        Respuesta<LogOutResponseView> respuestaLogout = new Respuesta<LogOutResponseView>();
        respuestaLogout.setEstadoRespuesta(EstadoRespuesta.OK);
        when(loginManager.logout(request)).thenReturn(respuestaLogout);

        @SuppressWarnings("rawtypes")
        Respuesta respuesta = obeManager.obtenerTokenEncriptado(mc);

        assertNotNull(respuesta);
        assertNotNull(respuesta.getRespuesta());
        assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
        assertThat(respuesta.getRespuesta(), instanceOf(TokenView.class));
        TokenView tokenView = (TokenView) respuesta.getRespuesta();

        assertEquals(token, tokenView.getTokenFirmado());
        assertEquals(url, tokenView.getUrl());
    }
}
