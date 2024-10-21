/* Tests para PagoPendienteBO
 * 
 * Manuel Vargas B041299
 */
package ar.com.santanderrio.obp.servicios.pagos.bo;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ResultadoTransaccion;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.IdentificacionCuentaView;
import ar.com.santanderrio.obp.servicios.pagos.entities.PagoPendiente;
import ar.com.santanderrio.obp.servicios.pagos.entities.TipoDeModificacion;
import ar.com.santanderrio.obp.servicios.pagos.entities.TipoDePagoEnum;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.DatosTarjeta;

/**
 * The Class PagoPendienteBOTest.
 */
@RunWith(MockitoJUnitRunner.class)
public class PagoPendienteBOTest {

    /** The cliente. */
    Cliente cliente = new Cliente();

    /** The respuesta BO. */
    Respuesta<List<PagoPendiente>> respuestaBO = new Respuesta<List<PagoPendiente>>();

    /** The respuesta BO pendientes. */
    Respuesta<List<PagoPendiente>> respuestaBOPendientes = new Respuesta<List<PagoPendiente>>();

    /** The respuesta BO prestamos. */
    Respuesta<List<PagoPendiente>> respuestaBOPrestamos = new Respuesta<List<PagoPendiente>>();

    /** The respuesta B otarjetas. */
    Respuesta<List<PagoPendiente>> respuestaBOtarjetas = new Respuesta<List<PagoPendiente>>();

    /** The pagos pendientes BO. */
    @Mock
    private PagosPendientesBO pagosPendientesBO;

    /**
     * Inits the mocks for cliente, deudas de prestamos, tarjetas y penidentes.
     */
    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
        //
        cliente.setApellido1("Ola");
        cliente.setNombre("M");
        cliente.setDni("30011002");
        //
        List<PagoPendiente> listadeudas = new ArrayList<PagoPendiente>();
        respuestaBO.setEstadoRespuesta(EstadoRespuesta.WARNING);
        respuestaBO.setRespuestaVacia(false);
        respuestaBO.setId(1L);
        PagoPendiente pago1 = new PagoPendiente();
        pago1.setNombreEmpresa("Préstamo Personal");
        pago1.setCodigoClienteEmpresa("008068147996       ");
        pago1.setImporte(new BigDecimal("708.65"));
        pago1.setImporteUSS(new BigDecimal("0.00"));
        pago1.setTipoPago(TipoDePagoEnum.PRESTAMOSPAGOPUNTUAL);
        IdentificacionCuentaView unMedioDePago = new IdentificacionCuentaView();
        unMedioDePago.setNumeroDeCuenta("07-79846/4");
        unMedioDePago.setAliasDeCuenta("Universidad");
        unMedioDePago.setTipoDeCuenta("Cuenta Corriente");
        listadeudas.add(pago1);
        PagoPendiente pago2 = new PagoPendiente();
        pago2.setNombreEmpresa("REMO");
        pago2.setCodigoEmpresa("REMO");
        pago2.setCodigoClienteEmpresa("1131535698       ");
        pago2.setTipoPago(TipoDePagoEnum.PRESTAMOSPAGOPUNTUAL);
        listadeudas.add(pago2);
        respuestaBO.setRespuesta(listadeudas);
        //
        List<PagoPendiente> deudas = new ArrayList<PagoPendiente>();
        respuestaBOPrestamos.setEstadoRespuesta(EstadoRespuesta.WARNING);
        respuestaBOPrestamos.setRespuestaVacia(false);
        respuestaBOPrestamos.setId(1L);
        PagoPendiente pagoPendiente1 = new PagoPendiente();
        pagoPendiente1.setNombreEmpresa("Préstamo Personal");
        pagoPendiente1.setCodigoClienteEmpresa("008068147996       ");
        pagoPendiente1.setImporte(new BigDecimal("708.65"));
        pagoPendiente1.setImporteUSS(new BigDecimal("0.00"));
        pagoPendiente1.setTipoPago(TipoDePagoEnum.PRESTAMOSPAGOPUNTUAL);
        IdentificacionCuentaView medioDePago = new IdentificacionCuentaView();
        medioDePago.setNumeroDeCuenta("07-79846/4");
        medioDePago.setAliasDeCuenta("Universidad");
        medioDePago.setTipoDeCuenta("Cuenta Corriente");
        deudas.add(pagoPendiente1);
        PagoPendiente pagoPendiente2 = new PagoPendiente();
        pagoPendiente2.setNombreEmpresa("REMO");
        pagoPendiente2.setCodigoEmpresa("REMO");
        pagoPendiente2.setCodigoClienteEmpresa("1131535698       ");
        pagoPendiente1.setTipoPago(TipoDePagoEnum.PRESTAMOSPAGOPUNTUAL);
        deudas.add(pagoPendiente2);
        respuestaBOPrestamos.setRespuesta(deudas);
        //
        List<PagoPendiente> deudasTarjeta = new ArrayList<PagoPendiente>();
        respuestaBOtarjetas.setEstadoRespuesta(EstadoRespuesta.WARNING);
        respuestaBOtarjetas.setRespuestaVacia(false);
        respuestaBOtarjetas.setId(1L);
        PagoPendiente pagoPendienteTarjeta1 = new PagoPendiente();
        pagoPendienteTarjeta1.setNombreEmpresa("Préstamo Personal");
        pagoPendienteTarjeta1.setCodigoClienteEmpresa("008068147996       ");
        pagoPendienteTarjeta1.setVencimiento(new Date("22/11/2014"));
        pagoPendienteTarjeta1.setImporte(new BigDecimal("708.65"));
        pagoPendienteTarjeta1.setImporteUSS(new BigDecimal("0.00"));
        pagoPendienteTarjeta1.setTipoPago(TipoDePagoEnum.TARJETADEBITOAUTOMATICOPAGOTOTAL);
        IdentificacionCuentaView medioDePagotarjeta = new IdentificacionCuentaView();
        medioDePagotarjeta.setNumeroDeCuenta("07-79846/4");
        medioDePagotarjeta.setAliasDeCuenta("Universidad");
        medioDePagotarjeta.setTipoDeCuenta("Cuenta Corriente");
        deudasTarjeta.add(pagoPendienteTarjeta1);
        PagoPendiente pagoPendienteTarjeta2 = new PagoPendiente();
        pagoPendienteTarjeta2.setNombreEmpresa("REMO");
        pagoPendienteTarjeta2.setCodigoEmpresa("REMO");
        pagoPendienteTarjeta2.setCodigoClienteEmpresa("1131535698       ");
        pagoPendienteTarjeta2.setTipoPago(TipoDePagoEnum.TARJETADEBITOAUTOMATICOPAGOMINIMO);
        deudasTarjeta.add(pagoPendienteTarjeta2);
        respuestaBOtarjetas.setRespuesta(deudasTarjeta);
        //
        List<PagoPendiente> deudasPendientes = new ArrayList<PagoPendiente>();
        respuestaBOPendientes.setEstadoRespuesta(EstadoRespuesta.WARNING);
        respuestaBOPendientes.setRespuestaVacia(false);
        respuestaBOPendientes.setId(1L);
        PagoPendiente pagoPendientePendientes1 = new PagoPendiente();
        pagoPendientePendientes1.setNombreEmpresa("Préstamo Personal");
        pagoPendientePendientes1.setCodigoClienteEmpresa("008068147996       ");
        pagoPendientePendientes1.setVencimiento(new Date("22/11/2014"));
        pagoPendientePendientes1.setImporte(new BigDecimal("708.65"));
        pagoPendientePendientes1.setImporteUSS(new BigDecimal("0.00"));
        pagoPendientePendientes1.setTipoPago(TipoDePagoEnum.TARJETADEBITOAUTOMATICOPAGOTOTAL);
        IdentificacionCuentaView medioDePagoPendientes = new IdentificacionCuentaView();
        medioDePagoPendientes.setNumeroDeCuenta("07-79846/4");
        medioDePagoPendientes.setAliasDeCuenta("Universidad");
        medioDePagoPendientes.setTipoDeCuenta("Cuenta Corriente");
        deudasPendientes.add(pagoPendientePendientes1);
        PagoPendiente pagoPendientePendientes2 = new PagoPendiente();
        pagoPendientePendientes2.setNombreEmpresa("REMO");
        pagoPendientePendientes2.setCodigoEmpresa("REMO");
        pagoPendientePendientes2.setCodigoClienteEmpresa("1131535698       ");
        pagoPendientePendientes2.setTipoPago(TipoDePagoEnum.TARJETADEBITOAUTOMATICOPAGOMINIMO);
        deudasPendientes.add(pagoPendientePendientes2);
        respuestaBOPendientes.setRespuesta(deudasPendientes);
    }

    /**
     * Obtener pagos pendientes. Firma: Respuesta<List<PagoPendiente>>
     * obtenerPagosPendientes(Cliente cliente) throws BusinessException
     * 
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void obtenerPagosPendientes() throws BusinessException {

        Mockito.when(this.pagosPendientesBO.obtenerPagosPendientes(Matchers.any(Cliente.class)))
                .thenReturn(respuestaBO);

        Respuesta<List<PagoPendiente>> resultadoPendientes = this.pagosPendientesBO.obtenerPagosPendientes(cliente);

        //
        assertNotNull(resultadoPendientes);
        assertNotNull(resultadoPendientes.getEstadoRespuesta());
        assertTrue(!resultadoPendientes.isRespuestaVacia());
    }

    /**
     * Gets the pagos pendientes-prestamo. Firma: Respuesta<List<PagoPendiente>>
     * getPagosPendientesPrestamo(Cliente cliente) return the pagos pendientes
     * prestamo
     *
     * @return the pagos pendientes prestamo
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void getPagosPendientesPrestamo() throws BusinessException {
        Mockito.when(this.pagosPendientesBO.getPagosPendientesPrestamo(Matchers.any(Cliente.class)))
                .thenReturn(respuestaBOPrestamos);
        // BO call
        Respuesta<List<PagoPendiente>> resultadoPendientes = this.pagosPendientesBO.getPagosPendientesPrestamo(cliente);
        // verify
        assertNotNull(resultadoPendientes);
        assertNotNull(resultadoPendientes.getEstadoRespuesta());
        assertTrue(!resultadoPendientes.isRespuestaVacia());
    }

    /**
     * Obtiene pagos pendientes de tarjetas. Firma: Respuesta<List
     * <PagoPendiente>> getPagosPendientesTarjetas(Cliente cliente) throws
     * BusinessException
     *
     * @return the pagos pendientes tarjetas
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void getPagosPendientesTarjetas() throws BusinessException {
        Mockito.when(this.pagosPendientesBO.getPagosPendientesTarjetas(Matchers.any(Cliente.class)))
                .thenReturn(respuestaBOtarjetas);
        // BO call
        Respuesta<List<PagoPendiente>> resultadoPendientes = this.pagosPendientesBO.getPagosPendientesTarjetas(cliente);
        // verify
        assertNotNull(resultadoPendientes);
        assertNotNull(resultadoPendientes.getEstadoRespuesta());
        assertTrue(!resultadoPendientes.isRespuestaVacia());
    }

    /**
     * Consulta si tiene pagos programados. Firma: Boolean tienePagosProgramados
     * (Cliente cliente, DatosTarjeta datosTarjeta, String fechaLimite)
     *
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void tienePagosProgramados() throws BusinessException {
        DatosTarjeta datosTarjeta = new DatosTarjeta();
        String fechaLimite = new String();
        //
        boolean respuestaBO = true;
        //
        Mockito.when(this.pagosPendientesBO.tienePagosProgramados(Matchers.any(Cliente.class),
                Matchers.any(DatosTarjeta.class), Matchers.any(String.class))).thenReturn(respuestaBO);
        // BO call
        boolean resultado = this.pagosPendientesBO.tienePagosProgramados(cliente, datosTarjeta, fechaLimite);
        // verify
        assertTrue(resultado);
    }

    /**
     * Modificar adhesion a Pago Pendiente.
     * 
     * Respuesta<ResultadoTransaccion> modificarAdhesion (Cliente cliente,
     * PagoPendiente servicioAdherido, Cuenta cuentaDebito, BigDecimal
     * limiteAdhesion, TipoDeModificacion tipoDeModificacion)
     */
    @Test
    public void modificarAdhesion() {
        PagoPendiente pagoPendiente = new PagoPendiente();
        pagoPendiente.setNombreEmpresa("ENOR");
        pagoPendiente.setCodigoClienteEmpresa("008068147996       ");
        Cuenta cuentaDebito = new Cuenta();
        cuentaDebito.setAlias("papa");
        cuentaDebito.setNroCuentaProducto("023-123456/7");
        BigDecimal limiteAdhesion = new BigDecimal("505");
        //
        Respuesta<ResultadoTransaccion> respuestaBO = new Respuesta<ResultadoTransaccion>();
        respuestaBO.setId(1L);
        respuestaBO.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaBO.setRespuestaVacia(false);
        //
        Mockito.when(this.pagosPendientesBO.modificarAdhesion(Matchers.any(Cliente.class),
                Matchers.any(PagoPendiente.class), Matchers.any(Cuenta.class), Matchers.any(BigDecimal.class),
                Matchers.any(TipoDeModificacion.class))).thenReturn(respuestaBO);
        // BO call
        Respuesta<ResultadoTransaccion> resultadoPagoAutomatico = this.pagosPendientesBO.modificarAdhesion(cliente,
                pagoPendiente, cuentaDebito, limiteAdhesion, TipoDeModificacion.MOD_CUENTA_DEBITO);
        // verify
        assertNotNull(resultadoPagoAutomatico);
        assertNotNull(resultadoPagoAutomatico.getEstadoRespuesta());
        assertTrue(!resultadoPagoAutomatico.isRespuestaVacia());
    }
}
