package ar.com.santanderrio.obp.servicios.tarjetas.bo;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.generated.webservices.visa.planv.InformacionPlanV;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.tarjetas.bo.impl.FinanciacionTarjetaBOImpl;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.impl.ConsultaFinanciacionDAOImpl;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.DatosConfirmacionFinanciacionTarjetaDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.FinanciacionTarjetaDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.SimulacionPlanVDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.SimulacionPlanVRequestDTO;

/**
 * Test para {@link FinanciacionTarjetaBO}.
 * 
 * @author manuel.vargas B041299
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class FinanciacionTarjetaBOTest {

    /** The respuesta factory. */
    @InjectMocks
    @Spy
    private RespuestaFactory respuestaFactory;

    /** The pagos manager. */
    @InjectMocks
    private FinanciacionTarjetaBOImpl financiacionTarjetaBOImpl;

    /** The consulta financiacion DAO. */
    @Mock
    private ConsultaFinanciacionDAOImpl consultaFinanciacionDAO;

    /** The mock sesion cliente. */
    @Mock
    private SesionCliente sesionCliente;

    /** The transferencia respuesta WARNING. */
    Respuesta<FinanciacionTarjetaDTO> financiacionRespuestaWARNING = new Respuesta<FinanciacionTarjetaDTO>();

    /** The transferencia respuesta OK. */
    Respuesta<FinanciacionTarjetaDTO> financiacionRespuestaOK = new Respuesta<FinanciacionTarjetaDTO>();

    /** The transferencia respuesta ERROR. */
    Respuesta<FinanciacionTarjetaDTO> financiacionRespuestaERROR = new Respuesta<FinanciacionTarjetaDTO>();

    /**
     * Inits the mocks.
     */
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);

        financiacionRespuestaWARNING.setEstadoRespuesta(EstadoRespuesta.WARNING);
        financiacionRespuestaWARNING.setRespuestaVacia(false);

        financiacionRespuestaOK.setEstadoRespuesta(EstadoRespuesta.OK);
        financiacionRespuestaOK.setRespuestaVacia(false);

        financiacionRespuestaERROR.setEstadoRespuesta(EstadoRespuesta.ERROR);
        financiacionRespuestaERROR.setRespuestaVacia(true);
    }

    /**
     * Solicitar financiacion tarjeta.
     *
     * @author emilio.watemberg
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void solicitarFinanciacionTarjeta() throws DAOException {

        Cliente cliente = new Cliente();
        cliente.setDni("30338256");
        Cuenta cuentaVisa = new Cuenta();
        cuentaVisa.setTipoCuentaEnum(ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta.VISA);
        cuentaVisa.setNroCuentaProducto("7836483111");
        cuentaVisa.setCbu("23303382569");

        InformacionPlanV informacionPlanV = new InformacionPlanV();
        informacionPlanV.setCuotasMaximo(12);
        informacionPlanV.setCuotasMaximo(3);
        informacionPlanV.setLimiteFinanciacion(50000);
        informacionPlanV.setMontoMaximoAFinanciar(10000);
        informacionPlanV.setTNAHasta3Cuotas(28.0);
        informacionPlanV.setTNAHasta6Cuotas(29.0);
        informacionPlanV.setTNAHasta9Cuotas(30.0);
        informacionPlanV.setTNAHasta12Cuotas(31.0);
        informacionPlanV.setTNAHasta18Cuotas(32.0);
        informacionPlanV.setTNAHasta24Cuotas(33.0);
        informacionPlanV.setTNAHasta36Cuotas(34.0);

        SimulacionPlanVDTO simulacionPlanVDTO = new SimulacionPlanVDTO();
        simulacionPlanVDTO.setImporteCuota("0000010000");
        simulacionPlanVDTO.setCostoFinancieroTotal("022230");

        Mockito.when(consultaFinanciacionDAO.obtenerInformacionPlanV(Matchers.any(Cuenta.class)))
                .thenReturn(informacionPlanV);
        Mockito.when(consultaFinanciacionDAO.simulacionFinanciacionPlanV(Matchers.any(SimulacionPlanVRequestDTO.class),
                Matchers.any(Cliente.class))).thenReturn(simulacionPlanVDTO);
        Mockito.when(respuestaFactory.crearRespuestaOk(Matchers.any(FinanciacionTarjetaDTO.class)))
                .thenReturn(financiacionRespuestaOK);
        Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);

        Respuesta<FinanciacionTarjetaDTO> respuesta = financiacionTarjetaBOImpl
                .solicitarFinanciacionTarjeta(cuentaVisa);

        FinanciacionTarjetaDTO financiacionTarjetaDTO = new FinanciacionTarjetaDTO();
        financiacionTarjetaDTO.setInformacionPlanV(informacionPlanV);
        financiacionTarjetaDTO.setSimulacionPlanVDTO(simulacionPlanVDTO);

        financiacionRespuestaOK.setRespuesta(financiacionTarjetaDTO);
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(financiacionRespuestaOK.getRespuesta().getSimulacionPlanVDTO().getCostoFinancieroTotal(),
                respuesta.getRespuesta().getSimulacionPlanVDTO().getCostoFinancieroTotal());
    }

    /**
     * Ejecutar financiacion tarjeta, resultado Ok.
     * 
     * @author B041299 Manuel.Vargas
     */
    @Test
    public void ejecutarFinanciacionTarjeta() {
        Respuesta<FinanciacionTarjetaDTO> respuesta;
        DatosConfirmacionFinanciacionTarjetaDTO datos = new DatosConfirmacionFinanciacionTarjetaDTO();
        financiacionRespuestaOK.setRespuesta(null);

        Mockito.when(financiacionTarjetaBOImpl
                .ejecutarFinanciacionTarjeta(Matchers.any(DatosConfirmacionFinanciacionTarjetaDTO.class)))
                .thenReturn(financiacionRespuestaOK);

        respuesta = financiacionTarjetaBOImpl.ejecutarFinanciacionTarjeta(datos);

        Assert.assertNotNull(respuesta);
        Assert.assertEquals(financiacionRespuestaOK, respuesta);
    }
}
