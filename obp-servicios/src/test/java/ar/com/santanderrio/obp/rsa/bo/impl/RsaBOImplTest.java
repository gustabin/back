package ar.com.santanderrio.obp.rsa.bo.impl;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.generated.webservices.rsa.ActionCode;
import ar.com.santanderrio.obp.generated.webservices.rsa.AnalyzeRequest;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.RsaDTO;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.rsa.bo.impl.RsaBOImpl;
import ar.com.santanderrio.obp.servicios.rsa.bo.impl.RsaRequestBuilderFactory;
import ar.com.santanderrio.obp.servicios.rsa.dao.RsaDAO;
import ar.com.santanderrio.obp.servicios.rsa.entities.RsaAnalyzeResponseData;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;



@RunWith(MockitoJUnitRunner.class)
public class RsaBOImplTest {
    @InjectMocks
    private RsaBOImpl rsaBO;
    @Mock
    private RsaRequestBuilderFactory rsaRequestBuilderFactory;
    @Mock
    private RsaDAO rsaDAO;
    @Mock
    private RespuestaFactory respuestaFactory;
    @Mock
    private SesionCliente sesionCliente;


    @Test
    public void whenExecuteAnalizarThenReturnRespuestaOK () throws DAOException {

        Mockito.when(rsaRequestBuilderFactory.getBuilder(Mockito.<Class<RsaDTO>>any())).thenReturn(Fixture.getRsaRequestBuilder());
        Mockito.when(rsaDAO.analizar(Mockito.any(AnalyzeRequest.class))).thenReturn(Fixture.getRsaAnalyzeRequest());
        Mockito.when(respuestaFactory.crearRespuestaOk(Mockito.any(Class.class))).thenReturn(Fixture.getRsaAnalizeResponseData());


        Respuesta<RsaAnalyzeResponseData> respuesta = rsaBO.analizar(Fixture.getRsaAnalyzeRequestData(), Boolean.TRUE);
        Mockito.verify(rsaRequestBuilderFactory, Mockito.times(1)).getBuilder(Mockito.<Class<RsaDTO>>any());
        Mockito.verify(rsaDAO, Mockito.times(1)).analizar(Mockito.any(AnalyzeRequest.class));

        Assert.assertNotNull(respuesta);
        Assert.assertEquals(Fixture.getRsaAnalyzeResponseData().getActionCode(), respuesta.getRespuesta().getActionCode());
    }

    @Test
    public void whenExecuteAnalizarThenReturnRespuestaOKActionCodeDeny () throws DAOException {

        Mockito.when(rsaRequestBuilderFactory.getBuilder(Mockito.<Class<RsaDTO>>any())).thenReturn(Fixture.getRsaRequestBuilder());
        Mockito.when(rsaDAO.analizar(Mockito.any(AnalyzeRequest.class))).thenReturn(Fixture.getRsaAnalyzeResponse1());
        Mockito.when(respuestaFactory.crearRespuestaOk(Mockito.any(Class.class))).thenReturn(Fixture.getRsaAnalizeResponseDataActionCodeDeny());
        Mockito.when(sesionCliente.getTieneTokenRSA()).thenReturn(Boolean.TRUE);


        Respuesta<RsaAnalyzeResponseData> respuesta = rsaBO.analizar(Fixture.getRsaAnalyzeRequestData(), Boolean.FALSE);
        Mockito.verify(rsaRequestBuilderFactory, Mockito.times(1)).getBuilder(Mockito.<Class<RsaDTO>>any());
        Mockito.verify(rsaDAO, Mockito.times(1)).analizar(Mockito.any(AnalyzeRequest.class));

        Assert.assertNotNull(respuesta);
        Assert.assertEquals(ActionCode.DENY, respuesta.getRespuesta().getActionCode());
    }

    @Test
    public void whenExecuteAnalizarThenReturnRespuestaOKModoAprendizajeActionCodeDeny () throws DAOException {

        Mockito.when(rsaRequestBuilderFactory.getBuilder(Mockito.<Class<RsaDTO>>any())).thenReturn(Fixture.getRsaRequestBuilder());
        Mockito.when(rsaDAO.analizar(Mockito.any(AnalyzeRequest.class))).thenReturn(Fixture.getRsaAnalyzeResponse2());
        Mockito.when(respuestaFactory.crearRespuestaOk(Mockito.any(Class.class))).thenReturn(Fixture.getRsaAnalizeResponseDataActionCodeDeny());
        Mockito.when(sesionCliente.getTieneTokenRSA()).thenReturn(Boolean.TRUE);


        Respuesta<RsaAnalyzeResponseData> respuesta = rsaBO.analizar(Fixture.getRsaAnalyzeRequestData(), Boolean.FALSE);
        Mockito.verify(rsaRequestBuilderFactory, Mockito.times(1)).getBuilder(Mockito.<Class<RsaDTO>>any());
        Mockito.verify(rsaDAO, Mockito.times(1)).analizar(Mockito.any(AnalyzeRequest.class));

        Assert.assertNotNull(respuesta);
        Assert.assertEquals(ActionCode.DENY, respuesta.getRespuesta().getActionCode());
    }

    @Test
    public void whenExecuteAnalizarThenThrowsBusinessException () throws DAOException {

        Mockito.when(rsaRequestBuilderFactory.getBuilder(Mockito.<Class<RsaDTO>>any())).thenThrow(BusinessException.class);
         Mockito.when(respuestaFactory.crearRespuestaError(Mockito.<Class<RsaAnalyzeResponseData>>any(), Mockito.anyString(), Mockito.anyString())).thenReturn(Fixture.getRsaAnalizeResponseDataErrorBusinessException());
        Respuesta<RsaAnalyzeResponseData> respuesta = rsaBO.analizar(Fixture.getRsaAnalyzeRequestData(), Boolean.TRUE);
        Mockito.verify(rsaRequestBuilderFactory, Mockito.times(1)).getBuilder(Mockito.<Class<RsaDTO>>any());
        Mockito.verify(respuestaFactory, Mockito.times(1)).crearRespuestaError(Mockito.<Class<RsaAnalyzeResponseData>>any(), Mockito.anyString(), Mockito.anyString());

        Assert.assertNotNull(respuesta);
        Assert.assertEquals(Fixture.getItemMensajeRespuestaDAOException().get(0).getMensaje(), respuesta.getItemsMensajeRespuesta().get(0).getMensaje());
        Assert.assertEquals(Fixture.getItemMensajeRespuestaDAOException().get(0).getTipoError(), respuesta.getItemsMensajeRespuesta().get(0).getTipoError());
    }

    @Test
    public void whenExecuteAnalizarThenThrowsDaoException () throws DAOException {

        Mockito.when(rsaRequestBuilderFactory.getBuilder(Mockito.<Class<RsaDTO>>any())).thenThrow(DAOException.class);
        Mockito.when(rsaDAO.analizar(Mockito.any(AnalyzeRequest.class))).thenReturn(Fixture.getRsaAnalyzeRequest());
        Mockito.when(respuestaFactory.crearRespuestaError(Mockito.<Class<RsaAnalyzeResponseData>>any(), Mockito.anyString(), Mockito.anyString())).thenReturn(Fixture.getRsaAnalizeResponseDataErrorDAOException());
        Respuesta<RsaAnalyzeResponseData> respuesta = rsaBO.analizar(Fixture.getRsaAnalyzeRequestData(), Boolean.TRUE);
        Mockito.verify(rsaRequestBuilderFactory, Mockito.times(1)).getBuilder(Mockito.<Class<RsaDTO>>any());
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(Fixture.getItemMensajeRespuestaDAOException().get(0).getMensaje(), respuesta.getItemsMensajeRespuesta().get(0).getMensaje());
        Assert.assertEquals(Fixture.getItemMensajeRespuestaDAOException().get(0).getTipoError(), respuesta.getItemsMensajeRespuesta().get(0).getTipoError());
    }
}
