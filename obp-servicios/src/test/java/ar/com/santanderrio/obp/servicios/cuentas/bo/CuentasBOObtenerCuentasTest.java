package ar.com.santanderrio.obp.servicios.cuentas.bo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.cuentas.bo.impl.CuentaBOImpl;
import ar.com.santanderrio.obp.servicios.cuentas.dao.AliasFavoritoProductoDAO;
import ar.com.santanderrio.obp.servicios.cuentas.dao.DetalleCuentaDAO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.AliasFavoritoProducto;
import ar.com.santanderrio.obp.servicios.cuentas.entities.ConsultaDetalleCuentaInEntity;
import ar.com.santanderrio.obp.servicios.cuentas.entities.ConsultaDetalleCuentaOutEntity;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.CuentaCerrada;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DetalleCuentaEntity;
import ar.com.santanderrio.obp.servicios.cuentas.entities.ResumenDetalleCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.pagos.dao.BanelcoDAO;
import ar.com.santanderrio.obp.servicios.pagos.entities.CuentaPagoMisCuentas;

/**
 * The Class CuentasBOObtenerCuentasTest.
 */
@RunWith(MockitoJUnitRunner.class)
public class CuentasBOObtenerCuentasTest {

    /** The cuentas BO. */
    @InjectMocks
    private CuentaBOImpl cuentasBO = new CuentaBOImpl();

    /** The detalle cuenta DAO. */
    @Mock
    private DetalleCuentaDAO detalleCuentaDAO;

    /** The cuenta cerrada BO. */
    @Mock
    private CuentaCerradaBO cuentaCerradaBO;

    /** The mensaje BO. */
    @Mock
    private MensajeBO mensajeBO;

    /** The banelco DAO. */
    @Mock
    private BanelcoDAO banelcoDAO;

    /** The alias favorito producto DAO. */
    @Mock
    private AliasFavoritoProductoDAO aliasFavoritoProductoDAO;

    /** The respuesta factory. */
    @InjectMocks
    @Spy
    private RespuestaFactory respuestaFactory = new RespuestaFactory();

    /**
     * Obtener cuentas test.
     *
     * @throws DAOException
     *             the DAO exception
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void obtenerCuentasTest() throws DAOException, BusinessException {
        // When
        Cliente cliente = new Cliente();
        cliente.setNup("2");
        List<Cuenta> cuentas = new ArrayList<Cuenta>();
        Cuenta cuenta1 = new Cuenta();
        Cuenta cuenta2 = new Cuenta();
        Cuenta cuenta3 = new Cuenta();
        Cuenta cuenta4 = new Cuenta();
        Cuenta cuenta5 = new Cuenta();
        Respuesta<ConsultaDetalleCuentaOutEntity> respuestaDetalleCuenta1 = new Respuesta<ConsultaDetalleCuentaOutEntity>();
        Respuesta<ConsultaDetalleCuentaOutEntity> respuestaDetalleCuenta2 = new Respuesta<ConsultaDetalleCuentaOutEntity>();
        Respuesta<ConsultaDetalleCuentaOutEntity> respuestaDetalleCuenta3 = new Respuesta<ConsultaDetalleCuentaOutEntity>();
        Respuesta<ConsultaDetalleCuentaOutEntity> respuestaDetalleCuenta4 = new Respuesta<ConsultaDetalleCuentaOutEntity>();
        Respuesta<ConsultaDetalleCuentaOutEntity> respuestaDetalleCuenta5 = new Respuesta<ConsultaDetalleCuentaOutEntity>();
        ConsultaDetalleCuentaOutEntity detalleCuenta1 = new ConsultaDetalleCuentaOutEntity();
        ConsultaDetalleCuentaOutEntity detalleCuenta2 = new ConsultaDetalleCuentaOutEntity();
        ConsultaDetalleCuentaOutEntity detalleCuenta3 = new ConsultaDetalleCuentaOutEntity();
        ConsultaDetalleCuentaOutEntity detalleCuenta4 = new ConsultaDetalleCuentaOutEntity();
        ConsultaDetalleCuentaOutEntity detalleCuenta5 = new ConsultaDetalleCuentaOutEntity();

        // Caja de Ahorros
        cuenta1.setTipoCuenta("1");
        cuenta1.setTipoCuentaEnum(TipoCuenta.CAJA_AHORRO_PESOS);
        // Cuenta Corriente
        cuenta2.setTipoCuenta("0");
        cuenta2.setTipoCuentaEnum(TipoCuenta.CUENTA_CORRIENTE_PESOS);
        // Cuenta Unica
        cuenta3.setTipoCuenta("2");
        cuenta3.setTipoCuentaEnum(TipoCuenta.CUENTA_UNICA);
        // Cuenta Unica con Traspaso
        cuenta4.setTipoCuenta("2");
        cuenta4.setTipoCuentaEnum(TipoCuenta.CUENTA_UNICA);
        // Cuenta No Pesos
        cuenta5.setTipoCuenta("3");
        cuenta5.setTipoCuentaEnum(TipoCuenta.CUENTA_CORRIENTE_DOLARES);
        cuentas.add(cuenta1);
        cuentas.add(cuenta2);
        cuentas.add(cuenta3);
        cuentas.add(cuenta4);
        cuentas.add(cuenta5);
        cliente.setCuentas(cuentas);
        detalleCuenta1.setIndicadorDireccionaCAPeticionCambio("  ");
        detalleCuenta1.setSaldoCuenta("100000");
        detalleCuenta1.setLimiteAcuerdoCC("10000");
        detalleCuenta1.setSaldoACTE("5000");
        detalleCuenta2.setIndicadorDireccionaCAPeticionCambio("  ");
        detalleCuenta2.setSaldoCuenta("20000");
        detalleCuenta2.setLimiteAcuerdoCC("5000");
        detalleCuenta2.setSaldoACTE("2500");
        detalleCuenta3.setIndicadorDireccionaCAPeticionCambio("  ");
        detalleCuenta3.setSaldoCuenta("1000");
        detalleCuenta3.setLimiteAcuerdoCC("5000");
        detalleCuenta3.setSaldoACTE("2500");
        detalleCuenta3.setSaldoACAH("3500");
        detalleCuenta3.setSaldoCuentaUSD("2000");
        detalleCuenta4.setIndicadorDireccionaCAPeticionCambio("A ");
        detalleCuenta4.setSaldoCuenta("1000");
        detalleCuenta4.setLimiteAcuerdoCC("5000");
        detalleCuenta4.setSaldoACTE("2500");
        detalleCuenta4.setSaldoACAH("3500");
        detalleCuenta4.setSaldoCuentaUSD("2000");
        detalleCuenta5.setIndicadorDireccionaCAPeticionCambio("A ");
        detalleCuenta5.setSaldoCuenta("1000");
        detalleCuenta5.setLimiteAcuerdoCC("5000");
        detalleCuenta5.setSaldoACTE("2500");
        detalleCuenta5.setSaldoACAH("3500");
        detalleCuenta5.setSaldoCuentaUSD("2000");
        respuestaDetalleCuenta1.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaDetalleCuenta1.setRespuesta(detalleCuenta1);
        respuestaDetalleCuenta2.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaDetalleCuenta2.setRespuesta(detalleCuenta2);
        respuestaDetalleCuenta3.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaDetalleCuenta3.setRespuesta(detalleCuenta3);
        respuestaDetalleCuenta4.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaDetalleCuenta4.setRespuesta(detalleCuenta4);
        respuestaDetalleCuenta5.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaDetalleCuenta5.setRespuesta(detalleCuenta5);
        when(detalleCuentaDAO.consultaDetalleCuenta(Matchers.any(ConsultaDetalleCuentaInEntity.class)))
                .thenReturn(detalleCuenta1, detalleCuenta2, detalleCuenta3, detalleCuenta4, detalleCuenta5);
        when(aliasFavoritoProductoDAO.obtenerAliasFavoritoNup(Matchers.anyLong())).thenReturn(null);

        Respuesta<List<ResumenDetalleCuenta>> respuestaDetalleCuentas = cuentasBO.obtenerInfoCuentas(cliente);

        assertNotNull(respuestaDetalleCuentas);
        assertEquals(EstadoRespuesta.OK, respuestaDetalleCuentas.getEstadoRespuesta());
        assertTrue(respuestaDetalleCuentas.getRespuesta().size() > 0);
        assertEquals(respuestaDetalleCuentas.getRespuesta().get(0).getSaldoPesos(), BigDecimal.valueOf(100000, 2));
        assertEquals(respuestaDetalleCuentas.getRespuesta().get(0).getLimiteDescubierto(),
                BigDecimal.valueOf(10000, 2));
        assertEquals(respuestaDetalleCuentas.getRespuesta().get(0).getTipoCuenta(),
                TipoCuenta.CAJA_AHORRO_PESOS.getCodigo().toString());
        assertEquals(respuestaDetalleCuentas.getRespuesta().get(1).getSaldoPesos(), BigDecimal.valueOf(20000, 2));
        assertEquals(respuestaDetalleCuentas.getRespuesta().get(1).getLimiteDescubierto(), BigDecimal.valueOf(5000, 2));
        assertEquals(respuestaDetalleCuentas.getRespuesta().get(1).getTipoCuenta(),
                TipoCuenta.CUENTA_CORRIENTE_PESOS.getCodigo().toString());
        assertEquals(respuestaDetalleCuentas.getRespuesta().get(3).getSaldoDolares(), BigDecimal.valueOf(2000, 2));
        assertEquals(respuestaDetalleCuentas.getRespuesta().get(3).getTipoCuenta(),
                TipoCuenta.CUENTA_UNICA.getCodigo().toString());
    }

    /**
     * Crear cliente.
     *
     * @return the cliente
     */
    private Cliente crearCliente() {
        Cliente cliente = new Cliente();
        cliente.setNup("123456789");
        List<Cuenta> cuentas = new ArrayList<Cuenta>();
        Cuenta cuenta1 = new Cuenta();
        Cuenta cuenta2 = new Cuenta();
        Cuenta cuenta3 = new Cuenta();
        Cuenta cuenta4 = new Cuenta();
        Cuenta cuenta5 = new Cuenta();

        // Caja de Ahorros
        cuenta1.setTipoCuenta("1");
        cuenta1.setTipoCuentaEnum(TipoCuenta.CAJA_AHORRO_PESOS);
        cuenta1.setNroCuentaProducto("000000001");
        cuenta1.setNroSucursal("01");
        cuenta1.setNroCuentaProducto("01");
        // Cuenta Corriente
        cuenta2.setTipoCuenta("0");
        cuenta2.setTipoCuentaEnum(TipoCuenta.CUENTA_CORRIENTE_PESOS);
        cuenta2.setNroCuentaProducto("000000002");
        cuenta2.setNroSucursal("02");
        cuenta2.setNroCuentaProducto("02");
        // Cuenta Unica
        cuenta3.setTipoCuenta("2");
        cuenta3.setTipoCuentaEnum(TipoCuenta.CUENTA_UNICA);
        cuenta3.setNroCuentaProducto("000000003");
        cuenta3.setNroSucursal("03");
        cuenta3.setNroCuentaProducto("03");
        // Cuenta Unica con Traspaso
        cuenta4.setTipoCuenta("2");
        cuenta4.setTipoCuentaEnum(TipoCuenta.CUENTA_UNICA);
        cuenta4.setNroCuentaProducto("000000004");
        cuenta4.setNroSucursal("04");
        cuenta4.setNroCuentaProducto("04");
        // Cuenta No Pesos
        cuenta5.setTipoCuenta("3");
        cuenta5.setTipoCuentaEnum(TipoCuenta.CUENTA_CORRIENTE_DOLARES);
        cuenta5.setNroCuentaProducto("000000005");
        cuenta5.setNroSucursal("05");
        cuenta5.setNroCuentaProducto("05");
        cuentas.add(cuenta1);
        cuentas.add(cuenta2);
        cuentas.add(cuenta3);
        cuentas.add(cuenta4);
        cuentas.add(cuenta5);
        cliente.setCuentas(cuentas);
        return cliente;
    }

    /**
     * Crear alias favorito list.
     *
     * @return the list
     */
    public List<AliasFavoritoProducto> crearAliasFavoritoList() {
        AliasFavoritoProducto aliasFavorito1 = new AliasFavoritoProducto();
        AliasFavoritoProducto aliasFavorito2 = new AliasFavoritoProducto();
        AliasFavoritoProducto aliasFavorito3 = new AliasFavoritoProducto();
        AliasFavoritoProducto aliasFavorito4 = new AliasFavoritoProducto();
        AliasFavoritoProducto aliasFavorito5 = new AliasFavoritoProducto();
        List<AliasFavoritoProducto> aliasFavoritoList = new ArrayList<AliasFavoritoProducto>();
        aliasFavorito1.setAlias("Uno_Ahorro_Pesos");
        aliasFavorito1.setFavorita(true);
        aliasFavorito1.setNroCtaProducto("000000001");
        aliasFavorito2.setAlias("Dos_Corriente_Pesos");
        aliasFavorito2.setFavorita(false);
        aliasFavorito2.setNroCtaProducto("000000002");
        aliasFavorito3.setAlias("Tres_Cuenta_Unica");
        aliasFavorito3.setFavorita(false);
        aliasFavorito3.setNroCtaProducto("000000003");
        aliasFavorito4.setAlias("Cuatro_Cuenta_Unica");
        aliasFavorito4.setFavorita(false);
        aliasFavorito4.setNroCtaProducto("000000004");
        aliasFavorito5.setAlias("Cinco_Corriente_Dolares");
        aliasFavorito5.setFavorita(false);
        aliasFavorito5.setNroCtaProducto("000000005");
        aliasFavoritoList.add(aliasFavorito1);
        aliasFavoritoList.add(aliasFavorito2);
        aliasFavoritoList.add(aliasFavorito3);
        return aliasFavoritoList;
    }

    /**
     * Obtener info cuentas OK test.
     *
     * @throws BusinessException
     *             the business exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void obtenerInfoCuentasOKTest() throws BusinessException, DAOException {
        Cliente cliente = crearCliente();
        List<AliasFavoritoProducto> aliasFavoritos = crearAliasFavoritoList();
        ConsultaDetalleCuentaOutEntity detalleCuenta = new ConsultaDetalleCuentaOutEntity();
        Respuesta<List<ResumenDetalleCuenta>> respuesta;
        when(aliasFavoritoProductoDAO.obtenerAliasFavoritoNup(Matchers.anyLong())).thenReturn(aliasFavoritos);
        when(detalleCuentaDAO.consultaDetalleCuenta(Matchers.any(ConsultaDetalleCuentaInEntity.class)))
                .thenReturn(detalleCuenta);

        respuesta = cuentasBO.obtenerInfoCuentas(cliente);
        assertNotNull(respuesta);
        assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }

    /**
     * Obtener info cuentas warning test.
     *
     * @throws BusinessException
     *             the business exception
     * @throws DAOException
     *             the DAO exception
     */
    @SuppressWarnings("unchecked")
    @Test
    public void obtenerInfoCuentasWarningTest() throws BusinessException, DAOException {
        // preparacion de datos
        Cliente cliente = crearCliente();
        List<AliasFavoritoProducto> aliasFavoritos = crearAliasFavoritoList();
        ConsultaDetalleCuentaOutEntity detalleCuenta = new ConsultaDetalleCuentaOutEntity();
        Mensaje mensaje = new Mensaje();
        Respuesta<List<ResumenDetalleCuenta>> respuesta;
        when(aliasFavoritoProductoDAO.obtenerAliasFavoritoNup(Matchers.anyLong())).thenReturn(aliasFavoritos);
        when(detalleCuentaDAO.consultaDetalleCuenta(Matchers.any(ConsultaDetalleCuentaInEntity.class)))
                .thenReturn(detalleCuenta).thenThrow(DAOException.class).thenReturn(detalleCuenta);
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);

        respuesta = cuentasBO.obtenerInfoCuentas(cliente);
        assertNotNull(respuesta);
        assertEquals(EstadoRespuesta.WARNING, respuesta.getEstadoRespuesta());
    }

    /**
     * Obtener cuentas cerradas sesion test.
     *
     * @throws BusinessException
     *             the business exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void obtenerCuentasCerradasSesionTest() throws BusinessException, DAOException {

        Cliente cliente = new Cliente();
        List<CuentaCerrada> cuentasCerradas = new ArrayList<CuentaCerrada>();
        CuentaCerrada cuentaCerrada = new CuentaCerrada();
        Respuesta<List<CuentaCerrada>> respuestaCuentaCerrada = new Respuesta<List<CuentaCerrada>>();
        Respuesta<DetalleCuentaEntity> respuestaDetalleCuenta = new Respuesta<DetalleCuentaEntity>();
        DetalleCuentaEntity detalleCuenta = new DetalleCuentaEntity();

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, -3);
        Date dateBefore30Days = cal.getTime();

        SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyyMMdd");
        String fecha = simpleFormat.format(dateBefore30Days);
        // Caja de Ahorros
        cuentaCerrada.setTipoCuenta("1");
        cuentaCerrada.setNroSucursal("000");
        cuentaCerrada.setNroCuentaProducto("1234567");
        cuentaCerrada.setFechaBajaContrato(fecha);
        cuentasCerradas.add(cuentaCerrada);
        cliente.setCuentasCerradas(cuentasCerradas);
        respuestaCuentaCerrada.setRespuesta(cuentasCerradas);
        respuestaCuentaCerrada.setEstadoRespuesta(EstadoRespuesta.OK);
        detalleCuenta.setDireccionaCA("B");
        detalleCuenta.setSaldoPesos("100000");
        detalleCuenta.setLimiteAcuerdoCtaCtePesos("10000");
        detalleCuenta.setSaldoACTE("5000");
        respuestaDetalleCuenta.setRespuesta(detalleCuenta);

        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("");
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        when(cuentaCerradaBO.obtenerCuentasCerradas(Matchers.any(Cliente.class))).thenReturn(respuestaCuentaCerrada);
        when(detalleCuentaDAO.consultaDetalleCuenta(Matchers.any(ConsultaDetalleCuentaInEntity.class)))
                .thenThrow(new DAOException());

        Respuesta<List<ResumenDetalleCuenta>> respuestaDetalleCuentas = cuentasBO.obtenerInfoCuentas(cliente);

        assertNotNull(respuestaDetalleCuentas);
        assertEquals(EstadoRespuesta.OK, respuestaDetalleCuentas.getEstadoRespuesta());
    }

    /**
     * Obtener cuentas cerradas test.
     *
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void obtenerCuentasCerradasTest() throws BusinessException {

        Cliente cliente = new Cliente();
        List<CuentaCerrada> cuentasCerradas = new ArrayList<CuentaCerrada>();
        CuentaCerrada cuentaCerrada = new CuentaCerrada();
        Respuesta<List<CuentaCerrada>> respuestaCuentaCerrada = new Respuesta<List<CuentaCerrada>>();
        Respuesta<DetalleCuentaEntity> respuestaDetalleCuenta = new Respuesta<DetalleCuentaEntity>();
        DetalleCuentaEntity detalleCuenta = new DetalleCuentaEntity();
        Mensaje mensaje = new Mensaje();
        Respuesta<Mensaje> respuestaMensaje = new Respuesta<Mensaje>();

        // Caja de Ahorros
        cuentaCerrada.setTipoCuenta("1");
        cuentaCerrada.setFechaBajaContrato("2010-09-01");
        cuentaCerrada.setNroSucursal("000");
        cuentaCerrada.setNroCuentaProducto("1234567");
        cuentasCerradas.add(cuentaCerrada);
        respuestaCuentaCerrada.setRespuesta(cuentasCerradas);
        respuestaCuentaCerrada.setEstadoRespuesta(EstadoRespuesta.OK);
        detalleCuenta.setDireccionaCA("B");
        detalleCuenta.setSaldoPesos("100000");
        detalleCuenta.setLimiteAcuerdoCtaCtePesos("10000");
        detalleCuenta.setSaldoACTE("5000");
        respuestaDetalleCuenta.setRespuesta(detalleCuenta);
        mensaje.setMensaje("Mensaje de Warning");
        respuestaMensaje.setRespuesta(mensaje);

        when(cuentaCerradaBO.obtenerCuentasCerradas(Matchers.any(Cliente.class))).thenReturn(respuestaCuentaCerrada);
        when(detalleCuentaDAO.obtenerDetalleCuenta(Matchers.any(Cuenta.class))).thenReturn(respuestaDetalleCuenta);
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);

        Respuesta<List<ResumenDetalleCuenta>> respuestaDetalleCuentas = cuentasBO.obtenerInfoCuentas(cliente);

        assertNotNull(respuestaDetalleCuentas);
        assertEquals(EstadoRespuesta.WARNING, respuestaDetalleCuentas.getEstadoRespuesta());
        assertTrue(respuestaDetalleCuentas.getRespuesta().size() > 0);
    }

    /**
     * Obtener cuentas warning test.
     *
     * @throws BusinessException
     *             the business exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void obtenerCuentasWarningTest() throws BusinessException, DAOException {

        Cliente cliente = new Cliente();
        cliente.setNup("2");
        List<Cuenta> cuentas = new ArrayList<Cuenta>();
        Cuenta cuenta = new Cuenta();
        Respuesta<DetalleCuentaEntity> respuestaDetalleCuenta = new Respuesta<DetalleCuentaEntity>();
        DetalleCuentaEntity detalleCuenta = new DetalleCuentaEntity();
        Respuesta<Mensaje> respuestaMensaje = new Respuesta<Mensaje>();
        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("Mensaje de Error");
        respuestaMensaje.setRespuesta(mensaje);

        when(detalleCuentaDAO.consultaDetalleCuenta(Matchers.any(ConsultaDetalleCuentaInEntity.class)))
                .thenThrow(new DAOException());
        when(aliasFavoritoProductoDAO.obtenerAliasFavoritoNup(Matchers.any(Long.class))).thenReturn(null);

        // Caja de Ahorros
        cuenta.setTipoCuenta("1");
        cuenta.setTipoCuentaEnum(TipoCuenta.CAJA_AHORRO_PESOS);
        cuenta.setNroSucursal("000");
        cuenta.setNroCuentaProducto("1234567");
        cuentas.add(cuenta);
        cliente.setCuentas(cuentas);
        detalleCuenta.setDireccionaCA("B");
        detalleCuenta.setSaldoPesos("100000");
        detalleCuenta.setLimiteAcuerdoCtaCtePesos("10000");
        detalleCuenta.setSaldoACTE("5000");
        respuestaDetalleCuenta.setEstadoRespuesta(EstadoRespuesta.WARNING);
        respuestaDetalleCuenta.setRespuesta(detalleCuenta);

        when(detalleCuentaDAO.obtenerDetalleCuenta(Matchers.any(Cuenta.class))).thenReturn(respuestaDetalleCuenta);
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);

        Respuesta<List<ResumenDetalleCuenta>> respuestaDetalleCuentas = cuentasBO.obtenerInfoCuentas(cliente);

        assertNotNull(respuestaDetalleCuentas);
        assertEquals(EstadoRespuesta.WARNING, respuestaDetalleCuentas.getEstadoRespuesta());
        assertTrue(respuestaDetalleCuentas.getRespuesta().size() > 0);
        assertEquals("Mensaje de Error", respuestaDetalleCuentas.getItemsMensajeRespuesta().get(0).getMensaje());
    }

    /**
     * Obtener cuentas banelco.
     *
     * @throws BusinessException
     *             the business exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void obtenerCuentasBanelco() throws BusinessException, DAOException {
        // When
        Cliente cliente = new Cliente();
        List<Cuenta> cuentas = new ArrayList<Cuenta>();
        List<CuentaPagoMisCuentas> cuentasBanelco = new ArrayList<CuentaPagoMisCuentas>();
        Cuenta cuenta1 = new Cuenta();
        Cuenta cuenta2 = new Cuenta();
        Cuenta cuenta3 = new Cuenta();
        CuentaPagoMisCuentas cuentab1 = new CuentaPagoMisCuentas("CAP", "000404266/2", "000004042662");
        CuentaPagoMisCuentas cuentab2 = new CuentaPagoMisCuentas("CCP", "000404270/9", "000004042709");
        CuentaPagoMisCuentas cuentab3 = new CuentaPagoMisCuentas("CU", "000160014/8", "000001600148");
        CuentaPagoMisCuentas cuentab4 = new CuentaPagoMisCuentas("CU", "099160014/8", "000001600148");

        cuenta1.setTipoCuenta("02");
        cuenta1.setTipoCuentaEnum(TipoCuenta.CUENTA_UNICA_PESOS);
        cuenta1.setNroCuentaProducto("0000000001600148");
        cuenta1.setNroSucursal("0099");
        cuenta1.setNroTarjetaCredito("00000000000000000000");
        cuenta1.setSaldoCuenta("");
        cuenta1.setSaldoCUPesos("8457707.60");
        cuenta1.setSaldoCUDls("9490505.58");

        cuenta2.setTipoCuenta("05");
        cuenta2.setTipoCuentaEnum(TipoCuenta.BANELCO);
        cuenta2.setNroCuentaProducto("0000000000000000");
        cuenta2.setNroSucursal("0099");
        cuenta2.setNroTarjetaCredito("00004517660046063029");
        cuenta2.setSaldoCuenta("0.00");
        cuenta2.setSaldoCUPesos("");
        cuenta2.setSaldoCUDls("");

        cuenta3.setTipoCuenta("07");
        cuenta3.setTipoCuentaEnum(TipoCuenta.VISA);
        cuenta3.setNroCuentaProducto("0000000016216611");
        cuenta3.setNroSucursal("0099");
        cuenta3.setNroTarjetaCredito("00004509950008806378");
        cuenta3.setSaldoCuenta("0.00");
        cuenta3.setSaldoCUPesos("");
        cuenta3.setSaldoCUDls("");

        cuentas.add(cuenta1);
        cuentas.add(cuenta2);
        cuentas.add(cuenta3);
        cliente.setCuentas(cuentas);

        cuentab1.setNroSucursal("000");
        cuentab2.setNroSucursal("000");
        cuentab3.setNroSucursal("000");
        cuentab4.setNroSucursal("099");

        cuentasBanelco.add(cuentab1);
        cuentasBanelco.add(cuentab2);
        cuentasBanelco.add(cuentab3);
        cuentasBanelco.add(cuentab4);
        when(banelcoDAO.obtenerCuentasBanelcoHabilitadas(Matchers.any(Cliente.class))).thenReturn(cuentasBanelco);
        // Then
        List<Cuenta> respuesta = cuentasBO.obtenerCuentasBanelcoPesos(cliente);
        // Expected
        assertNotNull(respuesta);
        assertEquals(1, respuesta.size());
        assertEquals("0000000001600148", respuesta.get(0).getNroCuentaProducto());
    }

    /**
     * Obtener cuentas banelco pesos sin cuentas banelco test.
     *
     * @throws BusinessException
     *             the business exception
     * @throws DAOException
     *             the DAO exception
     */
    @SuppressWarnings("unused")
    @Test(expected = BusinessException.class)
    public void obtenerCuentasBanelcoPesosSinCuentasBanelcoTest() throws BusinessException, DAOException {
        // When
        Cliente cliente = new Cliente();
        List<Cuenta> cuentas = new ArrayList<Cuenta>();
        Cuenta cuenta1 = new Cuenta();
        Cuenta cuenta2 = new Cuenta();
        Cuenta cuenta3 = new Cuenta();
        cuenta1.setTipoCuenta("02");
        cuenta1.setTipoCuentaEnum(TipoCuenta.CUENTA_UNICA_PESOS);
        cuenta1.setNroCuentaProducto("0000000001600148");
        cuenta1.setNroSucursal("0099");
        cuenta1.setNroTarjetaCredito("00000000000000000000");
        cuenta1.setSaldoCuenta("");
        cuenta1.setSaldoCUPesos("8457707.60");
        cuenta1.setSaldoCUDls("9490505.58");
        cuenta2.setTipoCuenta("05");
        cuenta2.setTipoCuentaEnum(TipoCuenta.BANELCO);
        cuenta2.setNroCuentaProducto("0000000000000000");
        cuenta2.setNroSucursal("0099");
        cuenta2.setNroTarjetaCredito("00004517660046063029");
        cuenta2.setSaldoCuenta("0.00");
        cuenta2.setSaldoCUPesos("");
        cuenta2.setSaldoCUDls("");
        cuenta3.setTipoCuenta("07");
        cuenta3.setTipoCuentaEnum(TipoCuenta.VISA);
        cuenta3.setNroCuentaProducto("0000000016216611");
        cuenta3.setNroSucursal("0099");
        cuenta3.setNroTarjetaCredito("00004509950008806378");
        cuenta3.setSaldoCuenta("0.00");
        cuenta3.setSaldoCUPesos("");
        cuenta3.setSaldoCUDls("");
        cuentas.add(cuenta1);
        cuentas.add(cuenta2);
        cuentas.add(cuenta3);
        cliente.setCuentas(cuentas);

        // When
        Mockito.when(banelcoDAO.obtenerCuentasBanelcoHabilitadas(Matchers.any(Cliente.class)))
                .thenReturn(new ArrayList<CuentaPagoMisCuentas>());

        // Then
        List<Cuenta> respuesta = cuentasBO.obtenerCuentasBanelcoPesos(cliente);
    }

}
