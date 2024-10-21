package ar.com.santanderrio.obp.servicios.cuentas.bo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
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
public class CuentasBOObtenerNuevasCuentasTest {

    /** The cuentas BO. */
    @InjectMocks
    private CuentaBOImpl cuentasBO = new CuentaBOImpl();

    /** The detalle cuenta DAO. */
    @Mock
    private DetalleCuentaDAO detalleCuentaDAO;

    /** The cuenta cerrada BO. */
    @Mock
    private CuentaCerradaBO cuentaCerradaBO;
    
    @Mock
    private AliasFavoritoProductoDAO aliasFavoritoProductoDAO;

    /** The mensaje BO. */
    @Mock
    private MensajeBO mensajeBO;
    
    @Mock
    private BanelcoDAO banelcoDAO;
    
    @Spy
    @InjectMocks
    private RespuestaFactory respuestaFactory = new RespuestaFactory();

    /**
     * Obtener cuentas test.
     * @throws DAOException 
     * @throws BusinessException 
     */
    @Test
    public void obtenerCuentasTest() throws DAOException, BusinessException {
        // When
        Cliente cliente = new Cliente();
        cliente.setNup("323");
        List<Cuenta> cuentas = new ArrayList<Cuenta>();
        Cuenta cuenta1 = new Cuenta();
        Cuenta cuenta2 = new Cuenta();
        Cuenta cuenta3 = new Cuenta();
        Cuenta cuenta4 = new Cuenta();
        Cuenta cuenta5 = new Cuenta();
        ConsultaDetalleCuentaOutEntity consultaDetalleCuentaOutEntity = new ConsultaDetalleCuentaOutEntity();
        // Caja de Ahorros
        cuenta1.setTipoCuenta("1");
        cuenta1.setTipoCuentaEnum(TipoCuenta.CAJA_AHORRO_PESOS);
        cuenta1.setNroCuentaProducto("1");
        cuenta1.setTraspasoAutomaticoActivo(Boolean.FALSE);
        // Cuenta Corriente
        cuenta2.setTipoCuenta("1");
        cuenta2.setTipoCuentaEnum(TipoCuenta.CUENTA_CORRIENTE_PESOS);
        cuenta2.setNroCuentaProducto("2");
        cuenta2.setTraspasoAutomaticoActivo(Boolean.FALSE);
        // Cuenta Unica
        cuenta3.setTipoCuenta("2");
        cuenta3.setTipoCuentaEnum(TipoCuenta.CUENTA_UNICA);
        cuenta3.setNroCuentaProducto("3");
        cuenta3.setTraspasoAutomaticoActivo(Boolean.FALSE);
        // Cuenta Unica con Traspaso
        cuenta4.setTipoCuenta("2");
        cuenta4.setTipoCuentaEnum(TipoCuenta.CUENTA_UNICA);
        cuenta4.setNroCuentaProducto("4");
        cuenta4.setTraspasoAutomaticoActivo(Boolean.FALSE);
        // Cuenta No Pesos
        cuenta5.setTipoCuenta("3");
        cuenta5.setTipoCuentaEnum(TipoCuenta.CUENTA_CORRIENTE_DOLARES);
        cuenta5.setNroCuentaProducto("5");
        cuenta5.setTraspasoAutomaticoActivo(Boolean.FALSE);
        cuentas.add(cuenta1);
        cuentas.add(cuenta2);
        cuentas.add(cuenta3);
        cuentas.add(cuenta4);
        cuentas.add(cuenta5);
        cliente.setCuentas(cuentas);
        consultaDetalleCuentaOutEntity.setSaldoACAH("350000");
        consultaDetalleCuentaOutEntity.setSaldoACTE("350000");
        consultaDetalleCuentaOutEntity.setSaldoCuenta("100000");
        consultaDetalleCuentaOutEntity.setLimiteAcuerdoCC("100000");
        consultaDetalleCuentaOutEntity.setLimiteAcuerdoCCUSD("100000");
        consultaDetalleCuentaOutEntity.setSaldoCuentaUSD("100000");
        consultaDetalleCuentaOutEntity.setLimiteAcuerdoCC("100000");
        
        when(detalleCuentaDAO.consultaDetalleCuenta(Matchers.any(ConsultaDetalleCuentaInEntity.class))).thenReturn(consultaDetalleCuentaOutEntity);
        // Then
        
        List<AliasFavoritoProducto> listaAliasFavoritos = new ArrayList<AliasFavoritoProducto>();
        AliasFavoritoProducto aliasFavoritoProducto  = new AliasFavoritoProducto();
        aliasFavoritoProducto.setAlias("Alias1");
        aliasFavoritoProducto.setNroCtaProducto("1");
        aliasFavoritoProducto.setFavorita(Boolean.FALSE);
        listaAliasFavoritos.add(aliasFavoritoProducto);
        AliasFavoritoProducto aliasFavoritoProducto2  = new AliasFavoritoProducto();
        aliasFavoritoProducto2.setAlias("Alias2");
        aliasFavoritoProducto2.setNroCtaProducto("2");
        aliasFavoritoProducto2.setFavorita(Boolean.FALSE);
        listaAliasFavoritos.add(aliasFavoritoProducto2);
        AliasFavoritoProducto aliasFavoritoProducto3  = new AliasFavoritoProducto();
        aliasFavoritoProducto3.setAlias("Alias3");
        aliasFavoritoProducto3.setNroCtaProducto("3");
        aliasFavoritoProducto3.setFavorita(Boolean.FALSE);
        listaAliasFavoritos.add(aliasFavoritoProducto3);
        AliasFavoritoProducto aliasFavoritoProducto4  = new AliasFavoritoProducto();
        aliasFavoritoProducto4.setAlias("Alias4");
        aliasFavoritoProducto4.setNroCtaProducto("4");
        aliasFavoritoProducto4.setFavorita(Boolean.TRUE);
        listaAliasFavoritos.add(aliasFavoritoProducto4);
        AliasFavoritoProducto aliasFavoritoProducto5  = new AliasFavoritoProducto();
        aliasFavoritoProducto5.setAlias("Alias5");
        aliasFavoritoProducto5.setNroCtaProducto("5");
        aliasFavoritoProducto5.setFavorita(Boolean.FALSE);
        listaAliasFavoritos.add(aliasFavoritoProducto5);
        
        when(aliasFavoritoProductoDAO.obtenerAliasFavoritoNup(Matchers.any(Long.class))).thenReturn(listaAliasFavoritos);

        Respuesta<List<ResumenDetalleCuenta>> respuestaDetalleCuentas = cuentasBO.obtenerInfoCuentas(cliente);
        // Expected
        assertNotNull(respuestaDetalleCuentas);
        assertEquals(EstadoRespuesta.OK, respuestaDetalleCuentas.getEstadoRespuesta());
        assertTrue(respuestaDetalleCuentas.getRespuesta().size() > 0);
        assertEquals(respuestaDetalleCuentas.getRespuesta().get(0).getSaldoPesos(), BigDecimal.valueOf(100000,2));
        assertEquals(respuestaDetalleCuentas.getRespuesta().get(0).getTipoCuenta(),
                TipoCuenta.CAJA_AHORRO_PESOS.getCodigo().toString());
        assertEquals(respuestaDetalleCuentas.getRespuesta().get(1).getSaldoPesos(), BigDecimal.valueOf(100000, 2));
        assertEquals(respuestaDetalleCuentas.getRespuesta().get(1).getAlias(), "Alias2");
        assertEquals(respuestaDetalleCuentas.getRespuesta().get(1).getLimiteDescubierto(), BigDecimal.valueOf(100000, 2));
        assertEquals(respuestaDetalleCuentas.getRespuesta().get(2).getTipoCuenta(),
                TipoCuenta.CUENTA_UNICA.getCodigo().toString());
    }


    /**
     * Obtener cuentas cerradas test.
     * @throws BusinessException 
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
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);

        Respuesta<List<ResumenDetalleCuenta>> respuestaDetalleCuentas = cuentasBO.obtenerInfoCuentas(cliente);

        assertNotNull(respuestaDetalleCuentas);
        assertEquals(respuestaDetalleCuentas.getEstadoRespuesta(), EstadoRespuesta.WARNING);
        assertTrue(respuestaDetalleCuentas.getRespuesta().size() > 0);
    }


    
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
        assertEquals(respuesta.size(), 1);
        assertEquals(respuesta.get(0).getNroCuentaProducto(), "0000000001600148");
    }
}
