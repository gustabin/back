package ar.com.santanderrio.obp.servicios.rsa.web.manager.impl;

import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.clientes.entities.ResumenCliente;
import ar.com.santanderrio.obp.base.comun.OperacionesRSAEnum;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.generated.webservices.rsa.ActionCode;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.RsaDTO;
import ar.com.santanderrio.obp.servicios.comun.challenge.entities.TipoDesafioEnum;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.rsa.bo.RsaBO;
import ar.com.santanderrio.obp.servicios.rsa.entities.RsaAnalyzeRequestData;
import ar.com.santanderrio.obp.servicios.rsa.entities.RsaAnalyzeResponseData;
import ar.com.santanderrio.obp.servicios.rsa.entities.RsaGenericRequestData;
import ar.com.santanderrio.obp.servicios.rsa.entities.RsaGenericResponseData;

/**
 * The Class RsaManagerImplTest.
 */
@RunWith(MockitoJUnitRunner.class)
public class RsaManagerImplTest {

    /** The rsa manager impl. */
    @InjectMocks
    private RsaManagerImpl rsaManagerImpl;

    /** The operacion de riesgo. */
    @Mock
    private RsaDTO operacionDeRiesgo;

    /** The sesion parametros. */
    @Mock
    private SesionParametros sesionParametros;

    /** The estados rsa. */
    @Mock
    Map<OperacionesRSAEnum, Boolean> estadosRsa;

    /** The sesion cliente. */
    @Mock
    SesionCliente sesionCliente;

    /** The resumen cliente. */
    @Mock
    ResumenCliente resumenCliente;

    /** The rsa BO. */
    @Mock
    RsaBO rsaBO;

    /**
     * Inits the.
     */
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        Mockito.when(sesionParametros.getRsaEstado()).thenReturn(estadosRsa);
        Mockito.when(sesionParametros.getRsaGenericRequestData()).thenReturn(Mockito.mock(RsaGenericRequestData.class));
        Mockito.when(sesionParametros.getRsaGenericResponseData())
                .thenReturn(Mockito.mock(RsaGenericResponseData.class));

        Mockito.when(sesionCliente.getResumenCliente()).thenReturn(resumenCliente);
    }

    /**
     * Analizar operacion de riesgo test.
     *
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void analizarOperacionDeRiesgoTest() throws BusinessException {
        Respuesta<ActionCode> analizarOperacionDeRiesgo = null;
        TipoDesafioEnum desafioAplicable = TipoDesafioEnum.TOKEN;
        Respuesta<RsaAnalyzeResponseData> respuestaRsaAnalyze = Mockito.mock(Respuesta.class);
        RsaAnalyzeResponseData rsaAnalyzeResponseData = Mockito.mock(RsaAnalyzeResponseData.class);
        Mockito.when(respuestaRsaAnalyze.getRespuesta()).thenReturn(rsaAnalyzeResponseData);
        Mockito.when(rsaBO.analizar(Matchers.any(RsaAnalyzeRequestData.class))).thenReturn(respuestaRsaAnalyze);

        // ServicioActivo
        Mockito.when(estadosRsa.get(Matchers.any())).thenReturn(true);
        // ALLOW
        Mockito.when(rsaAnalyzeResponseData.getActionCode()).thenReturn(ActionCode.ALLOW);
        analizarOperacionDeRiesgo = rsaManagerImpl.analizar(operacionDeRiesgo, desafioAplicable);
        Assert.assertEquals(EstadoRespuesta.OK, analizarOperacionDeRiesgo.getEstadoRespuesta());
        Assert.assertEquals(ActionCode.ALLOW, analizarOperacionDeRiesgo.getRespuesta());
        // DENY
        Mockito.when(rsaAnalyzeResponseData.getActionCode()).thenReturn(ActionCode.DENY);
        analizarOperacionDeRiesgo = rsaManagerImpl.analizar(operacionDeRiesgo, desafioAplicable);
        Assert.assertEquals(EstadoRespuesta.OK, analizarOperacionDeRiesgo.getEstadoRespuesta());
        Assert.assertEquals(ActionCode.DENY, analizarOperacionDeRiesgo.getRespuesta());
        // CHALLENGE
        Mockito.when(rsaAnalyzeResponseData.getActionCode()).thenReturn(ActionCode.CHALLENGE);
        analizarOperacionDeRiesgo = rsaManagerImpl.analizar(operacionDeRiesgo, desafioAplicable);
        Assert.assertEquals(EstadoRespuesta.OK, analizarOperacionDeRiesgo.getEstadoRespuesta());
        Assert.assertEquals(ActionCode.CHALLENGE, analizarOperacionDeRiesgo.getRespuesta());

        // ServicioInactivo
        Mockito.when(estadosRsa.get(Matchers.any())).thenReturn(false);
        analizarOperacionDeRiesgo = rsaManagerImpl.analizar(operacionDeRiesgo, desafioAplicable);
        Assert.assertEquals(EstadoRespuesta.WARNING, analizarOperacionDeRiesgo.getEstadoRespuesta());
        Assert.assertEquals(ActionCode.CHALLENGE, analizarOperacionDeRiesgo.getRespuesta());

        // FalloRSA
        Mockito.when(estadosRsa.get(Matchers.any())).thenReturn(true);
        Mockito.when(respuestaRsaAnalyze.getEstadoRespuesta()).thenReturn(EstadoRespuesta.ERROR);
        analizarOperacionDeRiesgo = rsaManagerImpl.analizar(operacionDeRiesgo, desafioAplicable);
        Assert.assertEquals(EstadoRespuesta.WARNING, analizarOperacionDeRiesgo.getEstadoRespuesta());
        Assert.assertEquals(ActionCode.CHALLENGE, analizarOperacionDeRiesgo.getRespuesta());
    }

}
