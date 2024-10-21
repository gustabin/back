package ar.com.santanderrio.obp.servicios.tarjetas.dao.impl;

import javax.xml.soap.SOAPFault;
import javax.xml.ws.soap.SOAPFaultException;

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

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.entities.Entity;
import ar.com.santanderrio.obp.base.signer.TokenSigner;
import ar.com.santanderrio.obp.base.webservice.GestionarWSAbstract;
import ar.com.santanderrio.obp.generated.webservices.visa.planv.ConfirmacionSolicitudPlanV;
import ar.com.santanderrio.obp.generated.webservices.visa.planv.InformacionPlanV;
import ar.com.santanderrio.obp.generated.webservices.visa.planv.PlanVService;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.DatosConfirmacionFinanciacionTarjetaDTO;

/**
 * The Class ConsultaFinanciacionDAOImplTest.
 */
@RunWith(MockitoJUnitRunner.class)
public class ConsultaFinanciacionDAOImplTest {

    /** The consulta financiacion DAO. */
    @InjectMocks
    ConsultaFinanciacionDAOImpl consultaFinanciacionDAO;

    /** The gestor plan V. */
    @Mock
    GestionarWSAbstract<PlanVService> gestorPlanV;

    /** The plan V service. */
    @Mock
    PlanVService planVService;

    /** The informacion plan V. */
    @Mock
    InformacionPlanV informacionPlanV;

    /** The token signer. */
    @Mock
    TokenSigner tokenSigner;

    /** The consumir solicitud plan V. */
    @Mock
    ConfirmacionSolicitudPlanV consumirSolicitudPlanV;

    /**
     * Inits the.
     *
     * @throws DAOException
     *             the DAO exception
     */
    @Before
    public void init() throws DAOException {
        MockitoAnnotations.initMocks(this);
        Mockito.when(gestorPlanV.obtenerPort()).thenReturn(planVService);
        Mockito.when(tokenSigner.obtenerTokenFirmado(Matchers.anyString(), Matchers.any(Entity.class)))
                .thenReturn("TOKENTOKENTOKENTOKEN");
    }

    /**
     * Obtener informacion plan V test.
     *
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void obtenerInformacionPlanVTest() throws DAOException {
        Mockito.when(planVService.getInformacionPlanV(Matchers.anyString())).thenReturn(informacionPlanV);
        Mockito.when(informacionPlanV.getCodigoRespuesta()).thenReturn("0000");
        Cuenta cuenta = Mockito.mock(Cuenta.class);

        InformacionPlanV respuestaDAO = consultaFinanciacionDAO.obtenerInformacionPlanV(cuenta);

        Assert.assertNotNull(respuestaDAO);
        Assert.assertTrue("0000".equals(respuestaDAO.getCodigoRespuesta()));
    }

    /**
     * Obtener informacion plan V test error.
     *
     * @throws DAOException
     *             the DAO exception
     */
    @Test(expected = DAOException.class)
    public void obtenerInformacionPlanVTestError() throws DAOException {
        SOAPFaultException soapFaultException = Mockito.mock(SOAPFaultException.class);
        Mockito.when(soapFaultException.getFault()).thenReturn(Mockito.mock(SOAPFault.class));
        Mockito.when(planVService.getInformacionPlanV(Matchers.anyString())).thenThrow(soapFaultException);
        Cuenta cuenta = Mockito.mock(Cuenta.class);

        consultaFinanciacionDAO.obtenerInformacionPlanV(cuenta);
    }

    /**
     * Ejecutar financiacion tarjeta test.
     * 
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void ejecutarFinanciacionTarjetaTest() throws DAOException {
        Mockito.when(planVService.confirmarSolicitudPlanV(Matchers.anyString())).thenReturn(consumirSolicitudPlanV);
        Mockito.when(consumirSolicitudPlanV.getCodigoRespuesta()).thenReturn("0000");
        Cuenta cuenta = Mockito.mock(Cuenta.class);
        DatosConfirmacionFinanciacionTarjetaDTO datos = new DatosConfirmacionFinanciacionTarjetaDTO();
        ConfirmacionSolicitudPlanV confirmacionSolicitudPlanV = consultaFinanciacionDAO
                .ejecutarFinanciacionTarjeta(datos);
        Assert.assertNotNull(confirmacionSolicitudPlanV);
        Assert.assertTrue("0000".equals(confirmacionSolicitudPlanV.getCodigoRespuesta()));
    }

    /**
     * Ejecutar Error en confirmacion de financiacion tarjeta test.
     * 
     * @throws DAOException
     *             the DAO exception
     */
    @Test(expected = DAOException.class)
    public void ejecutarFinanciacionTarjetaTestError() throws DAOException {
        SOAPFaultException soapFaultException = Mockito.mock(SOAPFaultException.class);
        Mockito.when(soapFaultException.getFault()).thenReturn(Mockito.mock(SOAPFault.class));
        Mockito.when(planVService.confirmarSolicitudPlanV(Matchers.anyString())).thenThrow(soapFaultException);
        Cuenta cuenta = Mockito.mock(Cuenta.class);
        DatosConfirmacionFinanciacionTarjetaDTO datos = new DatosConfirmacionFinanciacionTarjetaDTO();
        consultaFinanciacionDAO.ejecutarFinanciacionTarjeta(datos);
    }
}
