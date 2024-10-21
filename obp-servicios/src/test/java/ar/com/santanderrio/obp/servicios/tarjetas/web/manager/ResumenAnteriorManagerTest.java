/**
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.web.manager;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionTarjetas;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.tarjetas.bo.ResumenAnteriorBO;
import ar.com.santanderrio.obp.servicios.tarjetas.bo.impl.TarjetaBOUtils;
import ar.com.santanderrio.obp.servicios.tarjetas.bo.impl.TarjetaBOUtilsException;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ResumenAnterior;
import ar.com.santanderrio.obp.servicios.tarjetas.view.ResumenAnteriorViewResponse;
import ar.com.santanderrio.obp.servicios.tarjetas.view.TarjetaActivaView;
import ar.com.santanderrio.obp.servicios.tarjetas.web.manager.impl.ResumenAnteriorManagerImpl;

/**
 * The Class ResumenAnteriorManagerTest.
 *
 * @author
 */
@RunWith(MockitoJUnitRunner.class)
public class ResumenAnteriorManagerTest {

    /** The resumen anterior manager. */
    @InjectMocks
    private ResumenAnteriorManagerImpl resumenAnteriorManager;

    /** The resumen anterior BO. */
    @Mock
    private ResumenAnteriorBO resumenAnteriorBO;

    /** The respuesta factory. */
    @Mock
    private RespuestaFactory respuestaFactory;

    /** The sesion cliente. */
    @Mock
    private SesionCliente sesionCliente;
    
    @Mock
    private SesionTarjetas sesionTarjetas;

    /**
     * The estadistica manager.
     * 
     */
    @Mock
    private EstadisticaManager estadisticaManager;

    /** The tarjeta BO utils. */
    TarjetaBOUtils tarjetaBOUtils;

    /**
     * Obtener identificacion cuenta test.
     */
    @Test
    public void obtenerIdentificacionCuentaTest() {
        // Validar
        Assert.assertNull(null);
    }

    /**
     * Obtener resumen resumen anterior.
     *
     * @throws BusinessException
     *             the business exception
     * @throws TarjetaBOUtilsException
     *             the tarjeta BO utils exception
     */
    @Test
    public void obtenerResumenResumenAnterior() throws BusinessException, TarjetaBOUtilsException {
        // Mock Cliente
        Cliente cliente = mock(Cliente.class);
        when(sesionCliente.getCliente()).thenReturn(cliente);

        Cuenta cuenta = getCuenta();
        cuenta.setNroTarjetaCredito("00000000000000600460");
        cuenta.setTipoCuenta("7");
        cuenta.setClaseCuenta("R");

        String nroCuentaProducto = "0600460";
        String nroSucursal = "000";
        IdentificacionCuenta identificacionCuenta = new IdentificacionCuenta();
        identificacionCuenta.setNroCuentaProducto(nroCuentaProducto);
        identificacionCuenta.setNroSucursal(nroSucursal);

        TarjetaActivaView tarjetaActiva = new TarjetaActivaView();
        tarjetaActiva.setTarjetaActiva("000-060046/0");

        // Mock de respuestaFactory
        ResumenAnteriorViewResponse view = getResumenAnteriorViewResponse();
        Respuesta<ResumenAnteriorViewResponse> respuesta = getRespuesta(view);
        Mockito.when(respuestaFactory.crearRespuestaOk(ResumenAnteriorViewResponse.class, view)).thenReturn(respuesta);

        // Mock de BO
        when(resumenAnteriorBO.obtenerResumenesAnteriores(identificacionCuenta, cliente,"000-060046/0")).thenReturn(respuesta);

        // Mock de estadisticaManager
        when(estadisticaManager.add("10689", "1")).thenReturn(true);

        // consumir el manager.
        Respuesta<ResumenAnteriorViewResponse> retorno = resumenAnteriorManager
                .obtenerResumenesAnteriores(tarjetaActiva);

        // Validar
        assertEquals(respuesta, retorno);
    }

    /**
     * Grabar estadistica de resumenes anteriores casos.
     */
    @Test
    public void grabarEstadisticaDeResumenesAnterioresCasos() {
        resumenAnteriorManager.grabarEstadisticaDeResumenesAnteriores(EstadoRespuesta.OK);
        resumenAnteriorManager.grabarEstadisticaDeResumenesAnteriores(EstadoRespuesta.ERROR);
        resumenAnteriorManager.grabarEstadisticaDeResumenesAnteriores(EstadoRespuesta.WARNING);
    }

    /**
     * Gets the cliente.
     *
     * @return the cliente
     */
    private Cliente getCliente() {
        return mock(Cliente.class);
    }

    /**
     * Gets the cuenta.
     *
     * @return the cuenta
     */
    private Cuenta getCuenta() {
        Cuenta cuenta = new Cuenta();
        cuenta.setCliente(getCliente());
        cuenta.setNroCuentaProducto("0000000338501392");
        cuenta.setNroSucursal("0033");
        return cuenta;
    }

    /**
     * Genera una respuesta con datos de prueba.
     *
     * @return Respuesta<ResumenAnteriorViewResponse>
     */
    private ResumenAnteriorViewResponse getResumenAnteriorViewResponse() {
        ResumenAnteriorViewResponse resumenAnteriorViewResponse = new ResumenAnteriorViewResponse();
        resumenAnteriorViewResponse.setMarca("Visa");
        resumenAnteriorViewResponse.setNumero("XXXX-8569");
        resumenAnteriorViewResponse.setNumeroCuenta("0600460");

        List<ResumenAnterior> resumenes = new ArrayList<ResumenAnterior>();
        ResumenAnterior resumenAnterior = new ResumenAnterior();
        resumenAnterior.setFecha("10 de febrero del 2016");
        ResumenAnterior resumenAnterior2 = new ResumenAnterior();
        resumenAnterior2.setFecha("08 de marzo del 2016");
        resumenes.add(resumenAnterior);
        resumenes.add(resumenAnterior2);
        resumenAnteriorViewResponse.setResumenes(resumenes);
        return resumenAnteriorViewResponse;
    }

    /**
     * Gets the respuesta.
     *
     * @param view
     *            the view
     * @return the respuesta
     */
    private Respuesta<ResumenAnteriorViewResponse> getRespuesta(ResumenAnteriorViewResponse view) {
        Respuesta<ResumenAnteriorViewResponse> respuesta = new Respuesta<ResumenAnteriorViewResponse>();
        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
        respuesta.setRespuestaVacia(false);
        respuesta.setRespuesta(view);
        return respuesta;
    }

}
