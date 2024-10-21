package ar.com.santanderrio.obp.servicios.cuentas.bo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.compraventa.bo.CompraVentaDolaresUtil;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.dao.MensajeDAO;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.bo.impl.CuentaBOImpl;
import ar.com.santanderrio.obp.servicios.cuentas.dao.DetalleCuentaDAO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.SaldosConsolidadosCuentaDTO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;

/**
 * SaldosCOnsolidados Test.
 */
@RunWith(MockitoJUnitRunner.class)
public class CuentasBOSaldosConsolidadosTest {

    /** The cuentas BO. *
     */
	@InjectMocks
    private CuentaBO cuentasBO = new CuentaBOImpl();

    /** The detalle cuenta DAO. */
    @Mock
    private DetalleCuentaDAO detalleCuentaDAO;

    /** The cuenta cerrada BO. */
    @Mock
    private CuentaCerradaBO cuentaCerradaBO;

    /** The mensaje BO. */
    @Mock
    private MensajeBO mensajeBO;

    /** The mensaje DAO. */
    @Mock
    private MensajeDAO mensajeDAO;
    
    @InjectMocks
    @Spy    
    private RespuestaFactory respuestaFactory = new RespuestaFactory();


    @InjectMocks
    @Spy
    private CompraVentaDolaresUtil compraVentaDolaresUtil = new CompraVentaDolaresUtil();
    
    @Mock
    private SesionParametros sesionParametros;
    
    /**
     * Obtener Saldos Consolidados Actualizados Test OK.
     *
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void obtenerSaldosConsolidadosActualizadosTest() throws BusinessException {

        Cliente cliente = getCliente();
        List<Cuenta> cuentas = new ArrayList<Cuenta>();

        Cuenta cuenta1 = new Cuenta();
        cuenta1.setTipoCuentaEnum(TipoCuenta.CUENTA_UNICA);
        cuenta1.setSaldoCUDls("500");
        cuenta1.setSaldoCUPesos("1000");
        Cuenta cuenta2 = new Cuenta();
        cuenta2.setTipoCuentaEnum(TipoCuenta.CAJA_AHORRO_PESOS);
        cuenta2.setSaldoCuenta("200");
        Cuenta cuenta3 = new Cuenta();
        cuenta3.setTipoCuentaEnum(TipoCuenta.CUENTA_CORRIENTE_DOLARES);
        cuenta3.setSaldoCuenta("250");
        cuentas.add(cuenta1);
        cuentas.add(cuenta2);
        cuentas.add(cuenta3);
        cliente.setCuentas(cuentas);

        Respuesta<SaldosConsolidadosCuentaDTO> respuestaSaldosConsolidados = cuentasBO
                .obtenerSaldosConsolidadosActualizados(cliente);

        assertNotNull(respuestaSaldosConsolidados);
        assertEquals(respuestaSaldosConsolidados.getEstadoRespuesta(), EstadoRespuesta.OK);
        SaldosConsolidadosCuentaDTO saldos = respuestaSaldosConsolidados.getRespuesta();
        assertEquals(saldos.getSaldoPesos(), "1.200,00");
        assertEquals(saldos.getSaldoDolares(), "750,00");
    }

    /**
     * Obtener Saldos Consolidados Actualizados Test Error DAO.
     *
     * @throws BusinessException
     *             the business exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void obtenerSaldosConsolidadosActualizadosDAOErrorTest() throws BusinessException, DAOException {

        Cliente cliente = getCliente();
        List<Cuenta> cuentas = new ArrayList<Cuenta>();

        Cuenta cuenta1 = new Cuenta();
        cuenta1.setTipoCuenta(TipoCuenta.CUENTA_UNICA.getCodigo().toString());
        cuenta1.setSaldoCUDls("500");
        cuenta1.setSaldoCUPesos("1000");
        Cuenta cuenta2 = new Cuenta();
        cuenta2.setTipoCuenta(TipoCuenta.CAJA_AHORRO_PESOS.getCodigo().toString());
        cuenta2.setSaldoCuenta("200");
        Cuenta cuenta3 = new Cuenta();
        cuenta3.setTipoCuenta(TipoCuenta.CUENTA_CORRIENTE_DOLARES.getCodigo().toString());
        cuenta3.setSaldoCuenta("250");
        cuentas.add(cuenta1);
        cuentas.add(cuenta2);
        cuentas.add(cuenta3);
        cliente.setCuentas(cuentas);

        Respuesta<Mensaje> respuesta = new Respuesta<Mensaje>();
        getRespuestaMock(respuesta, EstadoRespuesta.ERROR, true, "Ocurrio un error", null);

        doThrow(new DAOException()).when(detalleCuentaDAO).actualizarSaldo(Matchers.anyListOf(Cuenta.class),
                Matchers.any(Cliente.class), Matchers.anyInt());
        
        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("ERROR-Ocurrio un error");
        
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);


        Respuesta<SaldosConsolidadosCuentaDTO> respuestaSaldosConsolidados = cuentasBO
                .obtenerSaldosConsolidadosActualizados(cliente);

        assertNotNull(respuestaSaldosConsolidados);
        assertEquals(respuestaSaldosConsolidados.getEstadoRespuesta(), EstadoRespuesta.ERROR);
        assertEquals(respuestaSaldosConsolidados.getItemsMensajeRespuesta().get(0).getMensaje(),
                "ERROR-Ocurrio un error");
    }

    /**
     * Obtener saldos consolidados cliente test.
     *
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void obtenerSaldosConsolidadosClienteTest() throws BusinessException {

        Cliente cliente = getCliente();
        List<Cuenta> cuentas = new ArrayList<Cuenta>();
        Cuenta cuenta1 = new Cuenta();
        cuenta1.setTipoCuenta(TipoCuenta.CUENTA_UNICA_PESOS.getCodigo().toString());
        cuenta1.setSaldoCUPesos("500");
        cuenta1.setTipoCuentaEnum(TipoCuenta.CUENTA_UNICA_PESOS);
        cuenta1.setSaldoCUDls("1000");
        Cuenta cuenta2 = new Cuenta();
        cuenta2.setTipoCuenta(TipoCuenta.CAJA_AHORRO_PESOS.getCodigo().toString());
        cuenta2.setTipoCuentaEnum(TipoCuenta.CAJA_AHORRO_PESOS);
        cuenta2.setSaldoCuenta("200");
        Cuenta cuenta3 = new Cuenta();
        cuenta3.setTipoCuenta(TipoCuenta.CUENTA_CORRIENTE_DOLARES.getCodigo().toString());
        cuenta3.setTipoCuentaEnum(TipoCuenta.CUENTA_CORRIENTE_DOLARES);
        cuenta3.setSaldoCuenta("250");
        cuentas.add(cuenta1);
        cuentas.add(cuenta2);
        cuentas.add(cuenta3);
        cliente.setCuentas(cuentas);

        Respuesta<SaldosConsolidadosCuentaDTO> respuestaSaldosConsolidadosDTO = cuentasBO
                .obtenerSaldoConsolidadoCliente(cliente);

        assertNotNull(respuestaSaldosConsolidadosDTO);
        assertEquals(respuestaSaldosConsolidadosDTO.getEstadoRespuesta(), EstadoRespuesta.OK);
        SaldosConsolidadosCuentaDTO saldosConsolidadosDTO = respuestaSaldosConsolidadosDTO.getRespuesta();

        assertEquals(saldosConsolidadosDTO.getSaldoPesos(), "700,00");
        assertEquals(saldosConsolidadosDTO.getSaldoDolares(), "1.250,00");
    }

    /**
     * UTILES TEST *.
     *
     * @param <E>
     *            the element type
     * @param respuesta
     *            the respuesta
     * @param estado
     *            the estado
     * @param isVacia
     *            the is vacia
     * @param mensajeEsperado
     *            the mensaje esperado
     * @param tipoError
     *            the tipo error
     * @return the respuesta mock
     */
    /**
     * Respuesta Generica
     * 
     * @param respuesta
     * @param estado
     * @param isVacia
     * @param mensajeEsperado
     */
    public <E> void getRespuestaMock(Respuesta<E> respuesta, EstadoRespuesta estado, boolean isVacia,
            String mensajeEsperado, TipoError tipoError) {
        respuesta.setEstadoRespuesta(estado);
        respuesta.setRespuestaVacia(isVacia);
        if (EstadoRespuesta.ERROR.equals(estado) || EstadoRespuesta.WARNING.equals(estado)) {
            Respuesta<Mensaje> resMsje = new Respuesta<Mensaje>();
            Mensaje msje = new Mensaje();
            msje.setMensaje(estado.toString() + "-" + mensajeEsperado);
            resMsje.setRespuesta(msje);
            when(mensajeDAO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(msje);
        }

    }

    /**
     * Gets the cliente.
     *
     * @return Cliente Mock
     */
    private Cliente getCliente() {
        Cliente cliente = new Cliente();
        cliente.setNombre("Jorge");
        cliente.setApellido1("Perez");
        return cliente;
    }

}
