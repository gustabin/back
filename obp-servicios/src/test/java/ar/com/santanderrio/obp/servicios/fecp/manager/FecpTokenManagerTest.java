package ar.com.santanderrio.obp.servicios.fecp.manager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.fecp.bo.FecpToken;
import ar.com.santanderrio.obp.servicios.token.externos.dto.TokenDTO;
import ar.com.santanderrio.obp.servicios.token.externos.view.TokenView;

@RunWith(MockitoJUnitRunner.class)
public class FecpTokenManagerTest {

    @InjectMocks
    private FecpTokenManagerImpl fecpManager;

    /** The fecp BO. */
    @Mock
    private FecpToken fecpBO;
    @Mock
    private SesionCliente sesionCliente;

    @Test
    public void obtenerTokenEncriptado() {
        String token = "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";
        String url   = "http://180.166.119.46:9082/fecp/inicial.htm";
        
        Respuesta<TokenDTO> respuestaMensaje = new Respuesta<TokenDTO>();
        TokenDTO tokenDTO = new TokenDTO();
        tokenDTO.setTokenFirmado(token);
        tokenDTO.setUrl(url);
        respuestaMensaje.setRespuesta(tokenDTO);
        respuestaMensaje.setEstadoRespuesta(EstadoRespuesta.OK);

        when(fecpBO.obtenerHash(sesionCliente)).thenReturn(respuestaMensaje);
        when(sesionCliente.getCliente()).thenReturn(null);

        Respuesta<TokenView> respuesta = fecpManager.obtenerTokenEncriptado();

        assertNotNull(respuesta);
        assertNotNull(respuesta.getRespuesta());
        assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
        assertEquals(respuesta.getRespuesta().getTokenFirmado(), token);
        assertEquals(respuesta.getRespuesta().getUrl(), url);
    }
}
