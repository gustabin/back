/**
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.web.manager;

import java.util.ArrayList;
import java.util.List;

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

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.generated.webservices.visa.planv.InformacionPlanV;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.impl.MensajeBOImpl;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuentaTarjeta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.tarjetas.bo.SelectorYCabeceraBO;
import ar.com.santanderrio.obp.servicios.tarjetas.bo.impl.FinanciacionTarjetaBOImpl;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.DetalleTarjetaDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.DisponiblesYConsumoDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.FinanciacionTarjetaDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.FinanciacionTarjetaView;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ResumenTarjetaDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.SimulacionPlanVDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.web.manager.impl.FinanciacionTarjetaManagerImpl;
import ar.com.santanderrio.obp.servicios.tarjetas.web.view.TarjetaView;

/**
 * Test para {@link FinanciacionTarjetaManager}.
 *
 * @author emilio.watemberg
 * @since Aug 18, 2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class FinanciacionTarjetaManagerTest {

    /** The pagos manager. */
    @InjectMocks
    private FinanciacionTarjetaManagerImpl financiacionTarjetaManager;

    /** The financiacion tarjeta BO. */
    @Mock
    private FinanciacionTarjetaBOImpl financiacionTarjetaBO;

    /** The respuesta factory. */
    @Spy
    private RespuestaFactory respuestaFactory;

    /** The estadistica manager. */
    @Mock
    private EstadisticaManager estadisticaManager;

    /** The mock sesion cliente. */
    @Mock
    private SesionCliente sesionCliente;

    /** The selector Y cabecera BO. */
    @Mock
    private SelectorYCabeceraBO selectorYCabeceraBO;

    /** The mock mensaje BO. */
    @Mock
    private MensajeBOImpl mockMensajeBO;

    /** Lista de tarjetas. */
    List<TarjetaView> tarjetas = new ArrayList<TarjetaView>();

    /** The transferencia respuesta WARNING. */
    Respuesta<FinanciacionTarjetaView> financiacionRespuestaWARNING = new Respuesta<FinanciacionTarjetaView>();

    /** The transferencia respuesta OK. */
    Respuesta<FinanciacionTarjetaView> financiacionRespuestaOK = new Respuesta<FinanciacionTarjetaView>();

    /** The transferencia respuesta ERROR. */
    Respuesta<FinanciacionTarjetaView> financiacionRespuestaERROR = new Respuesta<FinanciacionTarjetaView>();

    /** The respuesta financiacion tarjeta DTO. */
    Respuesta<FinanciacionTarjetaDTO> respuestaFinanciacionTarjetaDTO = new Respuesta<FinanciacionTarjetaDTO>();

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

        TarjetaView visa = new TarjetaView();
        visa.setMarca("VISA");
        visa.setNumero("1111222233334444");

        TarjetaView amex = new TarjetaView();
        amex.setMarca("AMEX");
        amex.setNumero("11111222223333344444");

        tarjetas.add(visa);
        tarjetas.add(amex);

    }

    /**
     * Solicitar financiacion tarjeta test.
     * 
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void solicitarFinanciacionTarjetaTest() throws BusinessException {

        FinanciacionTarjetaView financiacionTarjetaViewRequest = new FinanciacionTarjetaView();
        financiacionTarjetaViewRequest.setTarjetas(tarjetas);
        financiacionTarjetaViewRequest.setNroCuentaProductoSeleccionado("7836483111");

        Cliente cliente = new Cliente();
        cliente.setDni("30338256");
        Cuenta cuentaVisa = new Cuenta();
        cuentaVisa.setTipoCuentaEnum(TipoCuenta.VISA);
        cuentaVisa.setTipoCuenta("07");
        cuentaVisa.setNroCuentaProducto("7836483111");
        cuentaVisa.setNroTarjetaCredito("4509950146886867");

        List<Cuenta> listCuentas = new ArrayList<Cuenta>();
        listCuentas.add(cuentaVisa);
        cliente.setCuentas(listCuentas);

        Respuesta<DisponiblesYConsumoDTO> respuestaDisponiblesYConsumoDTO = new Respuesta<DisponiblesYConsumoDTO>();
        DisponiblesYConsumoDTO disponiblesYConsumoDTO = new DisponiblesYConsumoDTO();
        List<ResumenTarjetaDTO> resumenes = new ArrayList<ResumenTarjetaDTO>();
        ResumenTarjetaDTO resumenTarjetaDTO = new ResumenTarjetaDTO();
        DetalleTarjetaDTO detalleTarjetaDTO = new DetalleTarjetaDTO();
        detalleTarjetaDTO.setIsFavorita(true);
        detalleTarjetaDTO.setIsTitular(true);
        detalleTarjetaDTO.setMarca(TipoCuentaTarjeta.TIPOCTA_VISA.getDescripcion());
        detalleTarjetaDTO.setTipoCuenta(TipoCuentaTarjeta.TIPOCTA_VISA.getCodigo());
        detalleTarjetaDTO.setNroTarjeta("4509950146886867");
        resumenTarjetaDTO.setDetalle(detalleTarjetaDTO);
        resumenes.add(resumenTarjetaDTO);
        disponiblesYConsumoDTO.setResumenes(resumenes);
        respuestaDisponiblesYConsumoDTO.setRespuesta(disponiblesYConsumoDTO);
        respuestaDisponiblesYConsumoDTO.setEstadoRespuesta(EstadoRespuesta.OK);

        Respuesta<FinanciacionTarjetaDTO> respuestaFinanciacionTarjetaDTO = new Respuesta<FinanciacionTarjetaDTO>();
        FinanciacionTarjetaDTO financiacionTarjetaDTO = new FinanciacionTarjetaDTO();
        InformacionPlanV informacionPlanV = new InformacionPlanV();
        informacionPlanV.setCuotasMaximo(12);
        informacionPlanV.setCuotasMinimo(3);
        informacionPlanV.setMontoMaximo(10000);
        informacionPlanV.setMontoMaximo(100);
        informacionPlanV.setTNAHasta3Cuotas(27);
        informacionPlanV.setTNAHasta6Cuotas(28);
        informacionPlanV.setTNAHasta12Cuotas(29);
        SimulacionPlanVDTO simulacionPlanVDTO = new SimulacionPlanVDTO();
        simulacionPlanVDTO.setImporteCuota("000001000");
        simulacionPlanVDTO.setCostoFinancieroTotal("022230");
        financiacionTarjetaDTO.setInformacionPlanV(informacionPlanV);
        financiacionTarjetaDTO.setSimulacionPlanVDTO(simulacionPlanVDTO);
        respuestaFinanciacionTarjetaDTO.setRespuesta(financiacionTarjetaDTO);
        respuestaFinanciacionTarjetaDTO.setEstadoRespuesta(EstadoRespuesta.OK);

        Mensaje mensajeInformativo = new Mensaje();
        mensajeInformativo.setMensaje("Mensaje informativo");

        Mockito.when(financiacionTarjetaBO.solicitarFinanciacionTarjeta(Matchers.any(Cuenta.class)))
                .thenReturn(respuestaFinanciacionTarjetaDTO);
        Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
        Mockito.when(selectorYCabeceraBO.obtenerTarjetas(Matchers.any(Cliente.class)))
                .thenReturn(respuestaDisponiblesYConsumoDTO);
        Mockito.when(this.mockMensajeBO.obtenerMensajePorCodigo(Mockito.anyString())).thenReturn(mensajeInformativo);
        Mockito.when(estadisticaManager.add(Matchers.anyString(), Matchers.anyString())).thenReturn(true);

        Respuesta<FinanciacionTarjetaView> respuesta = financiacionTarjetaManager
                .solicitarFinanciacionTarjeta(financiacionTarjetaViewRequest);

        FinanciacionTarjetaView financiacionTarjetaViewResponse = new FinanciacionTarjetaView();
        financiacionTarjetaViewResponse
                .setCft(financiacionTarjetaDTO.getSimulacionPlanVDTO().getCostoFinancieroTotal());
        financiacionTarjetaViewResponse
                .setImporteCuota(financiacionTarjetaDTO.getSimulacionPlanVDTO().getImporteCuota());
        financiacionTarjetaViewResponse.setCuotasDisponibles(financiacionTarjetaDTO.getInformacionPlanV());
        financiacionTarjetaViewResponse.setImporteFinanciar("0.0");
        financiacionTarjetaViewResponse.setCantidadCuotas("12");
        financiacionTarjetaViewResponse.setImporteMaximoFinanciar("0.0");
        financiacionTarjetaViewResponse.setCft("22,23");
        financiacionTarjetaViewResponse.setMensajeInformativo(mensajeInformativo.getMensaje());
        financiacionRespuestaOK.setRespuesta(financiacionTarjetaViewResponse);

        // verificaciones
        Mockito.verify(estadisticaManager).add(EstadisticasConstants.FINANCIACION_TARJETA_VISA,
                EstadisticasConstants.CODIGO_ESTADISTICAS_OK);

        Assert.assertNotNull(respuesta);
        Assert.assertEquals(financiacionRespuestaOK.getRespuesta().getCantidadCuotas(),
                respuesta.getRespuesta().getCantidadCuotas());
        Assert.assertEquals(financiacionRespuestaOK.getRespuesta().getCft(), respuesta.getRespuesta().getCft());
        Assert.assertEquals(financiacionRespuestaOK.getEstadoRespuesta(), respuesta.getEstadoRespuesta());

    }

    /**
     * Ejecutar financiacion tarjeta test.
     * 
     */
    @Test
    public void ejecutarFinanciacionTarjetaTest() {

    }

    /**
     * Simular financiacion tarjeta test.
     * 
     */
    @Test
    public void simularFinanciacionTarjetaTest() {
        FinanciacionTarjetaView financiacionTarjetaViewRequest = new FinanciacionTarjetaView();
        financiacionTarjetaViewRequest.setTarjetas(tarjetas);
        financiacionTarjetaViewRequest.setNroCuentaProductoSeleccionado("7836483111");

        Respuesta<FinanciacionTarjetaDTO> respuestaFinanciacionTarjetaDTO = new Respuesta<FinanciacionTarjetaDTO>();
        FinanciacionTarjetaDTO financiacionTarjetaDTO = new FinanciacionTarjetaDTO();
        InformacionPlanV informacionPlanV = new InformacionPlanV();
        informacionPlanV.setCuotasMaximo(12);
        informacionPlanV.setCuotasMinimo(3);
        informacionPlanV.setMontoMaximo(10000);
        informacionPlanV.setMontoMaximo(100);
        informacionPlanV.setTNAHasta3Cuotas(27);
        informacionPlanV.setTNAHasta6Cuotas(28);
        informacionPlanV.setTNAHasta12Cuotas(29);
        SimulacionPlanVDTO simulacionPlanVDTO = new SimulacionPlanVDTO();
        simulacionPlanVDTO.setImporteCuota("000001000");
        simulacionPlanVDTO.setCostoFinancieroTotal("022230");
        financiacionTarjetaDTO.setInformacionPlanV(informacionPlanV);
        financiacionTarjetaDTO.setSimulacionPlanVDTO(simulacionPlanVDTO);
        respuestaFinanciacionTarjetaDTO.setRespuesta(financiacionTarjetaDTO);
        respuestaFinanciacionTarjetaDTO.setEstadoRespuesta(EstadoRespuesta.OK);

        Mensaje mensajeInformativo = new Mensaje();
        mensajeInformativo.setMensaje("Mensaje informativo");

        Mockito.when(financiacionTarjetaBO.simularFinanciacionTarjeta(Matchers.any(FinanciacionTarjetaView.class),
                Matchers.any(Cuenta.class))).thenReturn(respuestaFinanciacionTarjetaDTO);
        Mockito.when(this.mockMensajeBO.obtenerMensajePorCodigo(Mockito.anyString())).thenReturn(mensajeInformativo);

        Cliente cliente = new Cliente();
        cliente.setDni("30338256");
        Cuenta cuentaVisa = new Cuenta();
        cuentaVisa.setTipoCuentaEnum(ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta.VISA);
        cuentaVisa.setNroCuentaProducto("7836483111");
        Cuenta cuentaAmex = new Cuenta();
        cuentaAmex.setTipoCuentaEnum(ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta.AMEX);

        List<Cuenta> listCuentas = new ArrayList<Cuenta>();
        listCuentas.add(cuentaVisa);
        listCuentas.add(cuentaAmex);
        cliente.setCuentas(listCuentas);

        Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
        Respuesta<FinanciacionTarjetaView> respuesta = financiacionTarjetaManager
                .simularFinanciacionTarjeta(financiacionTarjetaViewRequest);

        FinanciacionTarjetaView financiacionTarjetaViewResponse = new FinanciacionTarjetaView();
        financiacionTarjetaViewResponse
                .setCft(financiacionTarjetaDTO.getSimulacionPlanVDTO().getCostoFinancieroTotal());
        financiacionTarjetaViewResponse
                .setImporteCuota(financiacionTarjetaDTO.getSimulacionPlanVDTO().getImporteCuota());
        financiacionTarjetaViewResponse.setCuotasDisponibles(financiacionTarjetaDTO.getInformacionPlanV());
        financiacionTarjetaViewResponse.setImporteFinanciar("0.0");
        financiacionTarjetaViewResponse.setCantidadCuotas("12");
        financiacionTarjetaViewResponse.setImporteMaximoFinanciar("0.0");
        financiacionTarjetaViewResponse.setCft("22,23");
        financiacionTarjetaViewResponse.setMensajeInformativo(mensajeInformativo.getMensaje());
        financiacionRespuestaOK.setRespuesta(financiacionTarjetaViewResponse);

        Assert.assertNotNull(respuesta);
        Assert.assertEquals(financiacionRespuestaOK.getRespuesta().getCantidadCuotas(),
                respuesta.getRespuesta().getCantidadCuotas());
        Assert.assertEquals(financiacionRespuestaOK.getRespuesta().getCft(), respuesta.getRespuesta().getCft());
        Assert.assertEquals(financiacionRespuestaOK.getEstadoRespuesta(), respuesta.getEstadoRespuesta());

    }

    /**
     * Comprobante financiacion tarjeta.
     */
    @Test
    public void comprobanteFinanciacionTarjeta() {
        FinanciacionTarjetaView datos = new FinanciacionTarjetaView();
        Respuesta<FinanciacionTarjetaView> respuesta;

        datos.setMarcaTarjetaSeleccionada(
                ar.com.santanderrio.obp.servicios.tarjetas.entities.TipoCuenta.VISA.getDescripcion());
        respuesta = financiacionTarjetaManager.comprobanteFinanciacionTarjeta(datos);
        Assert.assertNotNull(respuesta);
        Assert.assertTrue(EstadoRespuesta.OK.equals(respuesta.getEstadoRespuesta()));
        Mockito.verify(estadisticaManager).add(EstadisticasConstants.COMPROBANTE_FINANCIACION_VISA,
                EstadisticasConstants.CODIGO_ESTADISTICAS_OK);

        datos.setMarcaTarjetaSeleccionada(
                ar.com.santanderrio.obp.servicios.tarjetas.entities.TipoCuenta.AMEX.getDescripcion());
        respuesta = financiacionTarjetaManager.comprobanteFinanciacionTarjeta(datos);
        Assert.assertNotNull(respuesta);
        Assert.assertTrue(EstadoRespuesta.OK.equals(respuesta.getEstadoRespuesta()));
        Mockito.verify(estadisticaManager).add(EstadisticasConstants.COMPROBANTE_FINANCIACION_AMEX,
                EstadisticasConstants.CODIGO_ESTADISTICAS_OK);

        datos.setMarcaTarjetaSeleccionada("OTRA_COSA");
        respuesta = financiacionTarjetaManager.comprobanteFinanciacionTarjeta(datos);
        Assert.assertNotNull(respuesta);
        Assert.assertTrue(EstadoRespuesta.ERROR.equals(respuesta.getEstadoRespuesta()));

    }

}
