package ar.com.santanderrio.obp.servicios.cuentas.bo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.generated.webservices.alias.AliasCbuException;
import ar.com.santanderrio.obp.generated.webservices.alias.Error;
import ar.com.santanderrio.obp.generated.webservices.alias.RequestAlias;
import ar.com.santanderrio.obp.generated.webservices.alias.RequestConsultaCBU;
import ar.com.santanderrio.obp.generated.webservices.alias.RequestModificaAlias;
import ar.com.santanderrio.obp.generated.webservices.alias.ResponseAlias;
import ar.com.santanderrio.obp.servicios.alias.dao.AliasCbuDAO;
import ar.com.santanderrio.obp.servicios.alias.exception.AliasCBUCuentaInactivaException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.compraventa.dao.NUPDAO;
import ar.com.santanderrio.obp.servicios.comun.constantes.BancoEnum;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.dao.MensajeDAO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sucursales.dao.ConsultarSucursalesDAO;
import ar.com.santanderrio.obp.servicios.comun.sucursales.entities.Sucursal;
import ar.com.santanderrio.obp.servicios.cuentas.bo.impl.AliasCuentaBOImpl;
import ar.com.santanderrio.obp.servicios.cuentas.bo.impl.CasuisticaAliasCuentaImpl;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DetalleCBUDTO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DetalleDocumentoDTO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.NupDTO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ComprobanteAltaCBUDTO;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.DetalleCBUView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;

/**
 * 
 * @author dante.omar.olmedo
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class AliasCuentaBOTest {

    @InjectMocks
    private AliasCuentaBO aliasCuenta = new AliasCuentaBOImpl();

    @Mock
    private AliasCbuDAO aliasCBU;

    @InjectMocks
    @Spy
    private CasuisticaAliasCuenta casuisticaAlias = new CasuisticaAliasCuentaImpl();

    /** The respuesta factory. */
    @InjectMocks
    @Spy
    private RespuestaFactory respuestaFactory = new RespuestaFactory();

    /** The mensaje DAO. */
    @Mock
    private MensajeDAO mensajeDAO;

    @Mock
    private SesionCliente sesionCliente;

    @Mock
    private MensajeBO mensajeBO;

    @Mock
    private NUPDAO nupDao;

    @Mock
    private ConsultarSucursalesDAO consultarSucursalesDao;

    @Test
    public void obtenerAliasCBUOkTest() throws DAOException, AliasCBUCuentaInactivaException, AliasCbuException {
        ResponseAlias response = new ResponseAlias();
        response.setEstado("OK");
        response.setAlias("aliasCuenta");
        Mockito.when(aliasCBU.obtenerAliasDesdeCBU(Matchers.any(RequestConsultaCBU.class))).thenReturn(response);
        Respuesta<DetalleCBUView> res = aliasCuenta.obtenerAliasCBU("0720033531000000837170", "180.166.8.241",
                "21587183",
                "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36");
        Assert.assertEquals("aliasCuenta", res.getRespuesta().getAliasCbu());
    }

    @Test
    public void obtenerAliasCBUExceptionTest() throws DAOException, AliasCBUCuentaInactivaException, AliasCbuException {
        Mensaje mensaje = new Mensaje();
        ResponseAlias response = new ResponseAlias();
        response.setEstado("ERROR");
        Mockito.when(aliasCBU.obtenerAliasDesdeCBU(Matchers.any(RequestConsultaCBU.class)))
                .thenThrow(new DAOException());
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        Respuesta<DetalleCBUView> res = aliasCuenta.obtenerAliasCBU("0720033531000000837170", "180.166.8.241",
                "21587183",
                "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36");
        Assert.assertEquals(EstadoRespuesta.WARNING, res.getEstadoRespuesta());
    }

    @Test
    public void confirmarAltaAliasOkTest() throws DAOException {
        ResponseAlias responseAlias = new ResponseAlias();
        Cliente cliente = new Cliente();
        cliente.setDni("37866881");
        Cuenta cuenta = new Cuenta();
        cuenta.setTipoCuentaEnum(TipoCuenta.CAJA_AHORRO_PESOS);
        DetalleCBUDTO detalleCBUDTO = new DetalleCBUDTO();
        detalleCBUDTO.setDetallesTerminal("datosTerminal");
        detalleCBUDTO.setIp("188.16.2.123");
        detalleCBUDTO.setCliente(cliente);
        detalleCBUDTO.setCuenta(cuenta);

        responseAlias.setEstado("OK");
        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("pepe");
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        Mockito.when(aliasCBU.altaAlias(Matchers.any(RequestAlias.class))).thenReturn(responseAlias);
        Respuesta<ComprobanteAltaCBUDTO> res = aliasCuenta.confirmarAltaAlias(detalleCBUDTO);
        Assert.assertEquals(EstadoRespuesta.OK, res.getEstadoRespuesta());
    }

    @Test
    public void confirmarAltaAliasEditarOkTest() throws DAOException {
        ResponseAlias responseAlias = new ResponseAlias();
        Cliente cliente = new Cliente();
        Cuenta cuenta = new Cuenta();
        cuenta.setTipoCuentaEnum(TipoCuenta.CAJA_AHORRO_PESOS);
        cliente.setDni("37866881");

        DetalleCBUDTO detalleCBUDTO = new DetalleCBUDTO();
        detalleCBUDTO.setDetallesTerminal("datosTerminal");
        detalleCBUDTO.setIp("188.16.2.123");
        detalleCBUDTO.setCliente(cliente);
        detalleCBUDTO.setCuenta(cuenta);
        detalleCBUDTO.setAliasAnterior("teresa");

        responseAlias.setEstado("OK");
        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("pepe");
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        Mockito.when(aliasCBU.modificarAlias(Matchers.any(RequestModificaAlias.class))).thenReturn(responseAlias);
        Respuesta<ComprobanteAltaCBUDTO> res = aliasCuenta.confirmarAltaAlias(detalleCBUDTO);
        Assert.assertEquals(EstadoRespuesta.OK, res.getEstadoRespuesta());
    }

    @Test
    public void confirmarAltaAliasErrorCuentaInactivaOkTest() throws DAOException {
        ResponseAlias responseAlias = new ResponseAlias();
        Cliente cliente = new Cliente();
        cliente.setDni("37866881");
        Cuenta cuenta = new Cuenta();
        cuenta.setTipoCuentaEnum(TipoCuenta.CAJA_AHORRO_PESOS);
        DetalleCBUDTO detalleCBUDTO = new DetalleCBUDTO();
        detalleCBUDTO.setDetallesTerminal("datosTerminal");
        detalleCBUDTO.setIp("188.16.2.123");
        detalleCBUDTO.setCliente(cliente);
        detalleCBUDTO.setCuenta(cuenta);

        responseAlias.setEstado("ERROR");
        Error elError = new Error();
        elError.setCodigo("0210");
        responseAlias.setError(elError);
        Mockito.when(aliasCBU.altaAlias(Matchers.any(RequestAlias.class))).thenReturn(responseAlias);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());
        Mockito.when(mensajeDAO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());
        Respuesta<ComprobanteAltaCBUDTO> res = aliasCuenta.confirmarAltaAlias(detalleCBUDTO);
        Assert.assertEquals(EstadoRespuesta.OK, res.getEstadoRespuesta());
    }

    @Test
    public void confirmarAltaAliasErrorYaTieneAliasTest() throws DAOException {
        ResponseAlias responseAlias = new ResponseAlias();

        Cliente cliente = new Cliente();
        cliente.setDni("37866881");
        Cuenta cuenta = new Cuenta();
        cuenta.setTipoCuentaEnum(TipoCuenta.CAJA_AHORRO_PESOS);
        DetalleCBUDTO detalleCBUDTO = new DetalleCBUDTO();
        detalleCBUDTO.setDetallesTerminal("datosTerminal");
        detalleCBUDTO.setIp("188.16.2.123");
        detalleCBUDTO.setCliente(cliente);
        detalleCBUDTO.setCuenta(cuenta);

        responseAlias.setEstado("ERROR");
        Error elError = new Error();
        elError.setCodigo("0240");
        responseAlias.setError(elError);
        Mockito.when(aliasCBU.altaAlias(Matchers.any(RequestAlias.class))).thenReturn(responseAlias);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());
        Respuesta<ComprobanteAltaCBUDTO> res = aliasCuenta.confirmarAltaAlias(detalleCBUDTO);
        Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
    }

    @Test
    public void confirmarAltaAliasErrorAliasUsadoTest() throws DAOException {
        ResponseAlias responseAlias = new ResponseAlias();

        Cliente cliente = new Cliente();
        cliente.setDni("37866881");
        Cuenta cuenta = new Cuenta();
        cuenta.setTipoCuentaEnum(TipoCuenta.CAJA_AHORRO_PESOS);
        DetalleCBUDTO detalleCBUDTO = new DetalleCBUDTO();
        detalleCBUDTO.setDetallesTerminal("datosTerminal");
        detalleCBUDTO.setIp("188.16.2.123");
        detalleCBUDTO.setCliente(cliente);
        detalleCBUDTO.setCuenta(cuenta);

        responseAlias.setEstado("ERROR");
        Error elError = new Error();
        elError.setCodigo("0290");
        responseAlias.setError(elError);
        Mockito.when(aliasCBU.altaAlias(Matchers.any(RequestAlias.class))).thenReturn(responseAlias);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());
        Respuesta<ComprobanteAltaCBUDTO> res = aliasCuenta.confirmarAltaAlias(detalleCBUDTO);
        Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
    }

    @Test
    public void confirmarAltaAliasErrorGeneralTest() throws DAOException {
        ResponseAlias responseAlias = new ResponseAlias();
        Cliente cliente = new Cliente();
        cliente.setDni("37866881");
        Cuenta cuenta = new Cuenta();
        cuenta.setTipoCuentaEnum(TipoCuenta.CAJA_AHORRO_PESOS);
        DetalleCBUDTO detalleCBUDTO = new DetalleCBUDTO();
        detalleCBUDTO.setDetallesTerminal("datosTerminal");
        detalleCBUDTO.setIp("188.16.2.123");
        detalleCBUDTO.setCliente(cliente);
        detalleCBUDTO.setCuenta(cuenta);

        responseAlias.setEstado("ERROR");
        Error elError = new Error();
        elError.setCodigo("A8");
        responseAlias.setError(elError);
        Mockito.when(aliasCBU.altaAlias(Matchers.any(RequestAlias.class))).thenReturn(responseAlias);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());
        Respuesta<ComprobanteAltaCBUDTO> res = aliasCuenta.confirmarAltaAlias(detalleCBUDTO);

        Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
    }

    @Test
    public void obtenerDatosClienteOKTest() throws DAOException {
        Cliente cliente = new Cliente();
        ArrayList<Cuenta> listaCuentas = new ArrayList<Cuenta>();
        Cuenta cuenta = new Cuenta();
        NupDTO nup = new NupDTO();
        Sucursal sucursal = new Sucursal();
        String cbu = "0123456789012345678901";
        String alias = "alias";
        Map<String, DetalleDocumentoDTO> mapa = new HashMap<String, DetalleDocumentoDTO>();
        DetalleDocumentoDTO detalleDocumento = new DetalleDocumentoDTO();
        cliente.setNombre("Juan");
        cliente.setApellido1("Perez");
        cuenta.setCbu("0123456789012345678901");
        cuenta.setTipoCuenta("00");
        cuenta.setNroSucursal("0082");
        cuenta.setTipoCuentaEnum(TipoCuenta.CAJA_AHORRO_PESOS);
        cuenta.setNroCuentaProducto("231237123");
        detalleDocumento.setTipoDocumento("T ");
        detalleDocumento.setNroDocumento("37161999");
        mapa.put(detalleDocumento.getTipoDocumento(), detalleDocumento);
        nup.setDetalleDocumento(mapa);
        nup.setTieneError(false);
        listaCuentas.add(cuenta);
        cliente.setCuentas(listaCuentas);
        sucursal.setIdSucursal("0082");
        sucursal.setDescripcion("Monte Grande");
        Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
        Mockito.when(nupDao.obtenerNUP(Matchers.any(Cliente.class))).thenReturn(nup);
        Mockito.when(consultarSucursalesDao.consultarSucursalPorId(Matchers.anyString())).thenReturn(sucursal);
        Respuesta<DetalleCBUView> respuesta = aliasCuenta.obtenerDatosCliente(cbu, alias);

        Assert.assertNotNull(respuesta);
        Assert.assertEquals(BancoEnum.SANTANDER_RIO.getDescripcion(), respuesta.getRespuesta().getNombreBanco());
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }

    @Test
    public void obtenerDatosClienteDAOExceptionTest() throws DAOException {
        Cliente cliente = new Cliente();
        ArrayList<Cuenta> listaCuentas = new ArrayList<Cuenta>();
        Cuenta cuenta = new Cuenta();
        NupDTO nup = new NupDTO();
        Sucursal sucursal = new Sucursal();
        String cbu = "0123456789012345678901";
        String alias = null;
        Map<String, DetalleDocumentoDTO> mapa = new HashMap<String, DetalleDocumentoDTO>();
        DetalleDocumentoDTO detalleDocumento = new DetalleDocumentoDTO();
        cliente.setNombre("Juan");
        cliente.setApellido1("Perez");
        cuenta.setCbu("0123456789012345678901");
        cuenta.setTipoCuenta("00");
        cuenta.setNroSucursal("0082");
        cuenta.setTipoCuentaEnum(TipoCuenta.CAJA_AHORRO_PESOS);
        cuenta.setNroCuentaProducto("231237123");
        detalleDocumento.setTipoDocumento("T ");
        detalleDocumento.setNroDocumento("37161999");
        mapa.put(detalleDocumento.getTipoDocumento(), detalleDocumento);
        nup.setDetalleDocumento(mapa);
        nup.setTieneError(false);
        listaCuentas.add(cuenta);
        cliente.setCuentas(listaCuentas);
        sucursal.setIdSucursal("0082");
        sucursal.setDescripcion("Monte Grande");
        Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());
        Mockito.when(nupDao.obtenerNUP(cliente)).thenThrow(new DAOException());

        Respuesta<DetalleCBUView> respuesta = aliasCuenta.obtenerDatosCliente(cbu, alias);

        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
    }

    @Test
    public void obtenerDatosClienteSinSucursalErrorTest() throws DAOException {
        Cliente cliente = new Cliente();
        ArrayList<Cuenta> listaCuentas = new ArrayList<Cuenta>();
        Cuenta cuenta = new Cuenta();
        NupDTO nup = new NupDTO();
        String cbu = "0123456789012345678901";
        String alias = null;
        Map<String, DetalleDocumentoDTO> mapa = new HashMap<String, DetalleDocumentoDTO>();
        DetalleDocumentoDTO detalleDocumento = new DetalleDocumentoDTO();
        cliente.setNombre("Juan");
        cliente.setApellido1("Perez");
        cuenta.setCbu("0123456789012345678901");
        cuenta.setTipoCuenta("00");
        cuenta.setNroSucursal(null);
        cuenta.setTipoCuentaEnum(TipoCuenta.CAJA_AHORRO_PESOS);
        cuenta.setNroCuentaProducto("231237123");
        detalleDocumento.setTipoDocumento("T ");
        detalleDocumento.setNroDocumento("37161999");
        mapa.put(detalleDocumento.getTipoDocumento(), detalleDocumento);
        nup.setDetalleDocumento(mapa);
        nup.setTieneError(false);
        listaCuentas.add(cuenta);
        cliente.setCuentas(listaCuentas);
        Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
        Mockito.when(nupDao.obtenerNUP(Matchers.any(Cliente.class))).thenReturn(nup);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());
        Respuesta<DetalleCBUView> respuesta = aliasCuenta.obtenerDatosCliente(cbu, alias);
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
    }

    @Test
    public void obtenerDatosClienteFalloTipoDocTest() throws DAOException {
        Cliente cliente = new Cliente();
        ArrayList<Cuenta> listaCuentas = new ArrayList<Cuenta>();
        Cuenta cuenta = new Cuenta();
        NupDTO nup = new NupDTO();
        Sucursal sucursal = new Sucursal();
        String cbu = "0123456789012345678901";
        Map<String, DetalleDocumentoDTO> mapa = new HashMap<String, DetalleDocumentoDTO>();
        DetalleDocumentoDTO detalleDocumento = new DetalleDocumentoDTO();
        Mensaje mensaje = new Mensaje();
        String alias = null;
        String textoMensaje = "Error";
        mensaje.setMensaje(textoMensaje);
        cliente.setNombre("Juan");
        cliente.setApellido1("Perez");
        cuenta.setCbu("0123456789012345678901");
        cuenta.setTipoCuenta("00");
        cuenta.setNroSucursal("0082");
        cuenta.setNroCuentaProducto("231237123");
        cuenta.setTipoCuentaEnum(TipoCuenta.CAJA_AHORRO_PESOS);
        detalleDocumento.setTipoDocumento("P ");
        detalleDocumento.setNroDocumento("37161999");
        mapa.put(detalleDocumento.getTipoDocumento(), detalleDocumento);
        nup.setDetalleDocumento(mapa);
        nup.setTieneError(false);
        listaCuentas.add(cuenta);
        cliente.setCuentas(listaCuentas);
        sucursal.setIdSucursal("0082");
        sucursal.setDescripcion("Monte Grande");
        Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
        Mockito.when(nupDao.obtenerNUP(Matchers.any(Cliente.class))).thenReturn(nup);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        Mockito.when(consultarSucursalesDao.consultarSucursalPorId(Matchers.anyString())).thenReturn(sucursal);
        Respuesta<DetalleCBUView> respuesta = aliasCuenta.obtenerDatosCliente(cbu, alias);

        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
    }

    @Test
    public void crearRespuestaEditarOKTest() {
        ResponseAlias alias = new ResponseAlias();
        String aliasCBU = "aliasTest";
        String numeroCuenta = "1234567";
        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("pepe");
        alias.setEstado("OK");
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        Respuesta<ComprobanteAltaCBUDTO> respuesta = casuisticaAlias.crearRespuestaEditar(alias, aliasCBU, numeroCuenta,
                null);
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);

    }

    @Test
    public void crearRespuestaEditarAliasEnUsoTest() {
        ResponseAlias alias = new ResponseAlias();
        String aliasCBU = "aliasTest";
        String numeroCuenta = "1234567";
        Mensaje mensaje = new Mensaje();
        Error error = new Error();
        error.setCodigo("0290");
        error.setMensaje("lalala");
        mensaje.setMensaje("pepe");
        alias.setError(error);
        alias.setEstado("ERROR");
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        Respuesta<ComprobanteAltaCBUDTO> respuesta = casuisticaAlias.crearRespuestaEditar(alias, aliasCBU, numeroCuenta,
                null);
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
        Assert.assertEquals(respuesta.getItemsMensajeRespuesta().get(0).getTag(), "aliasEnUso");
        Assert.assertEquals(respuesta.getItemsMensajeRespuesta().get(0).getTipoError(),
                TipoError.ERROR_ALIAS_USADO.getDescripcion());
    }

    @Test
    public void crearRespuestaAliasNoEspecificadoTest() {
        ResponseAlias alias = new ResponseAlias();
        String aliasCBU = "aliasTest";
        String numeroCuenta = "1234567";
        Mensaje mensaje = new Mensaje();
        Error error = new Error();
        error.setCodigo("0320");
        error.setMensaje("lalala");
        mensaje.setMensaje("pepe");
        alias.setError(error);
        alias.setEstado("ERROR");
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        Respuesta<ComprobanteAltaCBUDTO> respuesta = casuisticaAlias.crearRespuestaEditar(alias, aliasCBU, numeroCuenta,
                null);
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
        Assert.assertEquals(respuesta.getItemsMensajeRespuesta().get(0).getTag(), "aliasNoEspecificado");
        Assert.assertEquals(respuesta.getItemsMensajeRespuesta().get(0).getTipoError(),
                TipoError.ERROR_ALIAS_NO_ESPECIFICADO.getDescripcion());

    }

    @Test
    public void crearRespuestaAliasEnUsoTest() {
        ResponseAlias alias = new ResponseAlias();
        String aliasCBU = "aliasTest";
        String numeroCuenta = "1234567";
        Mensaje mensaje = new Mensaje();
        Error error = new Error();
        error.setCodigo("0420");
        error.setMensaje("lalala");
        mensaje.setMensaje("pepe");
        alias.setError(error);
        alias.setEstado("ERROR");
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        Respuesta<ComprobanteAltaCBUDTO> respuesta = casuisticaAlias.crearRespuestaEditar(alias, aliasCBU, numeroCuenta,
                null);
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
        Assert.assertEquals(respuesta.getItemsMensajeRespuesta().get(0).getTag(), "aliasConCuitDiferente");
        Assert.assertEquals(respuesta.getItemsMensajeRespuesta().get(0).getTipoError(),
                TipoError.ERROR_ALIAS_USADO.getDescripcion());

    }

    @Test
    public void crearRespuestaErrorReasignacionTest() {
        ResponseAlias alias = new ResponseAlias();
        String aliasCBU = "aliasTest";
        String numeroCuenta = "1234567";
        Mensaje mensaje = new Mensaje();
        Error error = new Error();
        error.setCodigo("0235");
        error.setMensaje("lalala");
        mensaje.setMensaje("pepe");
        alias.setError(error);
        alias.setEstado("CONFIRMAR");
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        Mockito.when(mensajeDAO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_ASIGNACION_ALIAS_OK))
                .thenReturn(mensaje);
        Respuesta<ComprobanteAltaCBUDTO> respuesta = casuisticaAlias.crearRespuestaEditar(alias, aliasCBU, numeroCuenta,
                null);
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.WARNING);
        Assert.assertEquals(respuesta.getItemsMensajeRespuesta().get(0).getTipoError(),
                TipoError.REASIGNACION_ALIAS_CBU.getDescripcion());

    }

}
