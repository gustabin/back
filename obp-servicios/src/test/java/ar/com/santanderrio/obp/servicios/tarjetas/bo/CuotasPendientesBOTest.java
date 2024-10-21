/**
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.bo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.AbstractCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.tarjetas.bo.impl.CuotasPendientesBOImpl;
import ar.com.santanderrio.obp.servicios.tarjetas.bo.impl.TarjetaBOUtilsException;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.TarjetaDAO;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.CuotasPendientesEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.DatosEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.DetalleCuotaPendienteEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.ErrorTarjetasEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.RetornoTarjetasEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.TarjetaCuotasPendientesEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.TarjetaDocumentEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.TarjetaEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.TarjetasEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.TipoDocumento;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.Totales;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.CuotasPendientesDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.OperacionTarjetaWSEnum;
import ar.com.santanderrio.obp.servicios.tarjetas.util.ParseadorWSCuotasPendientes;
import ar.com.santanderrio.obp.servicios.tarjetas.util.impl.ParseadorVisaException;

/**
 * The Class CuotasPendientesBOTest.
 *
 * @author florencia.n.martinez
 */
@RunWith(MockitoJUnitRunner.class)
public class CuotasPendientesBOTest {

    /** The cuotas pendientes BO. */
    @InjectMocks
    private CuotasPendientesBO cuotasPendientesBO = new CuotasPendientesBOImpl();

    /** The obtencion datos cuotas pendiente. */
    @Mock
    private ParseadorWSCuotasPendientes parseador;

    /** The respuesta factory. */
    @Mock
    private RespuestaFactory respuestaFactory;

    /** The tarjetas BO. */
    @Mock
    private TarjetasBO tarjetasBO;

    /** The cuenta BO. */
    @Mock
    private CuentaBO cuentaBO;

    /** The tarjeta DAO. */
    @Mock
    private TarjetaDAO tarjetaDAO;

    /** The error tarjetas entity. */
    private ErrorTarjetasEntity errorTarjetasEntity = null;

    /** The Constant ERROR_DISTINTO_A_SIN_MOVIMIENTOS. */
    private static final String ERROR_DISTINTO_A_SIN_MOVIMIENTOS = "112107333"; 
    
    /** The exception. */
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    /**
     * Obtener cuotas pendientes con respuesta OK Titular.
     *
     * @throws BusinessException
     *             the business exception
     * @throws DAOException
     *             the DAO exception
     * @throws ParseadorVisaException
     *             the parseador visa exception
     * @throws TarjetaBOUtilsException
     *             the tarjeta BO utils exception
     * @throws NumberFormatException
     *             the parseador visa exception
     */

    @Test
    public void obtenerCuotasPendientesDAOExceptionTest()
            throws BusinessException, DAOException, ParseadorVisaException, TarjetaBOUtilsException {
        Cliente cliente = new Cliente();
        IdentificacionCuenta identificacionCuenta = new IdentificacionCuenta();
        AbstractCuenta cuenta = (AbstractCuenta) obtenerCuenta();
        List<String> listaCodigoTarjeta = new ArrayList<String>();
        Respuesta<Object> resPrueba = new Respuesta<Object>();
        ItemMensajeRespuesta itemRes = new ItemMensajeRespuesta();
        List<ItemMensajeRespuesta> listItemRes = new ArrayList<ItemMensajeRespuesta>();

        listaCodigoTarjeta.add("1234");
        itemRes.setMensaje("Paso por flujo de Error via daoException");
        listItemRes.add(itemRes);
        resPrueba.setItemMensajeRespuesta(listItemRes);

        Mockito.when(cuentaBO.buscarCuentaPorId(Matchers.any(Cliente.class), Matchers.any(IdentificacionCuenta.class)))
                .thenReturn(cuenta);
        Mockito.when(tarjetaDAO.obtenerTarjetasDeVisaWS(Matchers.any(Cuenta.class),
                Matchers.any(OperacionTarjetaWSEnum.class),Matchers.any(Cliente.class))).thenThrow(new DAOException());
        Mockito.when(respuestaFactory.crearRespuestaError(Matchers.anyString(), Matchers.any(TipoError.class),
                Matchers.anyString())).thenReturn(resPrueba);

        Respuesta<CuotasPendientesDTO> respuesta = cuotasPendientesBO.obtenerCuotasPendientes(identificacionCuenta,
                cliente);

        Assert.assertEquals(resPrueba.getItemsMensajeRespuesta().get(0).getMensaje(),
                respuesta.getItemsMensajeRespuesta().get(0).getMensaje());

    }

    /**
     * Obtener cuotas pendientes exception test.
     *
     * @throws BusinessException
     *             the business exception
     * @throws DAOException
     *             the DAO exception
     * @throws ParseadorVisaException
     *             the parseador visa exception
     * @throws TarjetaBOUtilsException
     *             the tarjeta BO utils exception
     */
    @Test(expected = BusinessException.class)
    public void obtenerCuotasPendientesExceptionTest()
            throws BusinessException, DAOException, ParseadorVisaException, TarjetaBOUtilsException {
        Cliente cliente = new Cliente();
        IdentificacionCuenta identificacionCuenta = new IdentificacionCuenta();
        AbstractCuenta cuenta = (AbstractCuenta) obtenerCuenta();
        List<String> listaCodigoTarjeta = new ArrayList<String>();
        Respuesta<Object> resPrueba = new Respuesta<Object>();
        ItemMensajeRespuesta itemRes = new ItemMensajeRespuesta();
        List<ItemMensajeRespuesta> listItemRes = new ArrayList<ItemMensajeRespuesta>();

        listaCodigoTarjeta.add("1234");
        itemRes.setMensaje("Paso por flujo de Error en obtener cuotas via Exception");
        listItemRes.add(itemRes);
        resPrueba.setItemMensajeRespuesta(listItemRes);

        Mockito.when(cuentaBO.buscarCuentaPorId(Matchers.any(Cliente.class), Matchers.any(IdentificacionCuenta.class)))
                .thenReturn(cuenta);
        Mockito.when(tarjetaDAO.obtenerTarjetasDeVisaWS(Matchers.any(Cuenta.class),
                Matchers.any(OperacionTarjetaWSEnum.class),Matchers.any(Cliente.class))).thenReturn(obtenerRetornoWS(errorTarjetasEntity));
        Mockito.when(parseador.tieneErrorDeCredenciales(Matchers.any(RetornoTarjetasEntity.class)))
                .thenThrow(new ParseadorVisaException());
        Mockito.when(respuestaFactory.crearRespuestaError(Matchers.anyString(), Matchers.any(TipoError.class),
                Matchers.anyString())).thenReturn(resPrueba);

        Respuesta<CuotasPendientesDTO> respuesta = cuotasPendientesBO.obtenerCuotasPendientes(identificacionCuenta,
                cliente);

        Assert.assertEquals(resPrueba.getItemsMensajeRespuesta().get(0).getMensaje(),
                respuesta.getItemsMensajeRespuesta().get(0).getMensaje());

    }

    /**
     * Obtener cuotas pendientes error de credenciales test.
     *
     * @throws BusinessException
     *             the business exception
     * @throws DAOException
     *             the DAO exception
     * @throws ParseadorVisaException
     *             the parseador visa exception
     * @throws TarjetaBOUtilsException
     *             the tarjeta BO utils exception
     */
    @Test
    public void obtenerCuotasPendientesErrorDeCredencialesTest()
            throws BusinessException, DAOException, ParseadorVisaException, TarjetaBOUtilsException {
        Cliente cliente = new Cliente();
        IdentificacionCuenta identificacionCuenta = new IdentificacionCuenta();
        AbstractCuenta cuenta = (AbstractCuenta) obtenerCuenta();
        List<String> listaCodigoTarjeta = new ArrayList<String>();
        Respuesta<Object> resPrueba = new Respuesta<Object>();
        ItemMensajeRespuesta itemRes = new ItemMensajeRespuesta();
        List<ItemMensajeRespuesta> listItemRes = new ArrayList<ItemMensajeRespuesta>();

        listaCodigoTarjeta.add("1234");
        itemRes.setMensaje("Paso por flujo de Error en obtener cuotas");
        listItemRes.add(itemRes);
        resPrueba.setItemMensajeRespuesta(listItemRes);

        Mockito.when(cuentaBO.buscarCuentaPorId(Matchers.any(Cliente.class), Matchers.any(IdentificacionCuenta.class)))
                .thenReturn(cuenta);
        Mockito.when(tarjetaDAO.obtenerTarjetasDeVisaWS(Matchers.any(Cuenta.class),
                Matchers.any(OperacionTarjetaWSEnum.class),Matchers.any(Cliente.class))).thenReturn(obtenerRetornoWS(errorTarjetasEntity));
        Mockito.when(parseador.tieneErrorDeCredenciales(Matchers.any(RetornoTarjetasEntity.class))).thenReturn(true);
        Mockito.when(respuestaFactory.crearRespuestaError(Matchers.anyString(), Matchers.any(TipoError.class),
                Matchers.anyString())).thenReturn(resPrueba);

        Respuesta<CuotasPendientesDTO> respuesta = cuotasPendientesBO.obtenerCuotasPendientes(identificacionCuenta,
                cliente);

        Assert.assertEquals(resPrueba.getItemsMensajeRespuesta().get(0).getMensaje(),
                respuesta.getItemsMensajeRespuesta().get(0).getMensaje());

    }

    /**
     * Armar respuesta cuotas pendientes exception test.
     *
     * @throws BusinessException
     *             the business exception
     * @throws DAOException
     *             the DAO exception
     * @throws ParseadorVisaException
     *             the parseador visa exception
     * @throws TarjetaBOUtilsException
     *             the tarjeta BO utils exception
     */
    @Test
    public void armarRespuestaCuotasPendientesExceptionTest()
            throws BusinessException, DAOException, ParseadorVisaException, TarjetaBOUtilsException {
        Cliente cliente = new Cliente();
        IdentificacionCuenta identificacionCuenta = new IdentificacionCuenta();
        AbstractCuenta cuenta = (AbstractCuenta) obtenerCuenta();
        List<String> listaCodigoTarjeta = new ArrayList<String>();
        Respuesta<Object> resPrueba = new Respuesta<Object>();
        ItemMensajeRespuesta itemRes = new ItemMensajeRespuesta();
        List<ItemMensajeRespuesta> listItemRes = new ArrayList<ItemMensajeRespuesta>();

        listaCodigoTarjeta.add("1234");
        itemRes.setMensaje("Paso por flujo de Error");
        listItemRes.add(itemRes);
        resPrueba.setItemMensajeRespuesta(listItemRes);

        Mockito.when(cuentaBO.buscarCuentaPorId(Matchers.any(Cliente.class), Matchers.any(IdentificacionCuenta.class)))
                .thenReturn(cuenta);
        Mockito.when(tarjetaDAO.obtenerTarjetasDeVisaWS(Matchers.any(Cuenta.class),
                Matchers.any(OperacionTarjetaWSEnum.class),Matchers.any(Cliente.class))).thenReturn(obtenerRetornoWS(errorTarjetasEntity));
        Mockito.when(parseador.tieneErrorDeCredenciales(Matchers.any(RetornoTarjetasEntity.class))).thenReturn(false);
        Mockito.when(parseador.obtenerTarjetaPorNroTarjetaActiva(Matchers.any(RetornoTarjetasEntity.class),
                Matchers.anyString()))
                .thenReturn(obtenerTarjetaDesdeTarjetaDocument(
                        obtenerTarjetaDocument(obtenerDatosTarjetaUno(), obtenerCuotasPendientes()), errorTarjetasEntity));
        Mockito.when(parseador.obtenerTotalCuotasPendientes(Matchers.any(TarjetaEntity.class)))
                .thenThrow(new TarjetaBOUtilsException());
        Mockito.when(respuestaFactory.crearRespuestaError(Matchers.anyString(), Matchers.any(TipoError.class),
                Matchers.anyString())).thenReturn(resPrueba);

        Respuesta<CuotasPendientesDTO> respuesta = cuotasPendientesBO.obtenerCuotasPendientes(identificacionCuenta,
                cliente);

        Assert.assertEquals(resPrueba.getItemsMensajeRespuesta().get(0).getMensaje(),
                respuesta.getItemsMensajeRespuesta().get(0).getMensaje());

    }

    /**
     * Crear respuesta tarjeta entity null sin consumos test.
     *
     * @throws BusinessException
     *             the business exception
     * @throws DAOException
     *             the DAO exception
     * @throws ParseadorVisaException
     *             the parseador visa exception
     */
    @Test
    public void crearRespuestaTarjetaEntityNullSinConsumosTest()
            throws BusinessException, DAOException, ParseadorVisaException {
        Cliente cliente = new Cliente();
        IdentificacionCuenta identificacionCuenta = new IdentificacionCuenta();
        AbstractCuenta cuenta = (AbstractCuenta) obtenerCuenta();
        List<String> listaCodigoTarjeta = new ArrayList<String>();
        Respuesta<Object> resPrueba = new Respuesta<Object>();
        ItemMensajeRespuesta itemRes = new ItemMensajeRespuesta();
        List<ItemMensajeRespuesta> listItemRes = new ArrayList<ItemMensajeRespuesta>();

        listaCodigoTarjeta.add("1234");
        itemRes.setMensaje("Paso por flujo de Error");
        listItemRes.add(itemRes);
        resPrueba.setItemMensajeRespuesta(listItemRes);
        Mockito.when(tarjetaDAO.obtenerTarjetasDeVisaWS(Matchers.any(Cuenta.class),
                Matchers.any(OperacionTarjetaWSEnum.class),Matchers.any(Cliente.class))).thenReturn(obtenerRetornoWS(errorTarjetasEntity));
        Mockito.when(cuentaBO.buscarCuentaPorId(Matchers.any(Cliente.class), Matchers.any(IdentificacionCuenta.class)))
                .thenReturn(cuenta);
        Mockito.when(tarjetaDAO.obtenerTarjetasDeVisaWS(Matchers.any(Cuenta.class),
                Matchers.any(OperacionTarjetaWSEnum.class),Matchers.any(Cliente.class))).thenReturn(obtenerRetornoWS(errorTarjetasEntity));
        Mockito.when(parseador.tieneErrorDeCredenciales(Matchers.any(RetornoTarjetasEntity.class))).thenReturn(false);
        Mockito.when(parseador.tarjetaNotieneConsumos(Matchers.any(RetornoTarjetasEntity.class))).thenReturn(true);
        Mockito.when(parseador.obtenerTarjetaPorNroTarjetaActiva(Matchers.any(RetornoTarjetasEntity.class),
                Matchers.anyString())).thenReturn(null);
        Mockito.when(respuestaFactory.crearRespuestaError(Matchers.anyString(), Matchers.any(TipoError.class),
                Matchers.anyString())).thenReturn(resPrueba);

        Respuesta<CuotasPendientesDTO> respuesta = cuotasPendientesBO.obtenerCuotasPendientes(identificacionCuenta,
                cliente);

        Assert.assertEquals(resPrueba.getItemsMensajeRespuesta().get(0).getMensaje(),
                respuesta.getItemsMensajeRespuesta().get(0).getMensaje());

    }

    /**
     * Crear respuesta parseador visa exception test.
     *
     * @throws BusinessException
     *             the business exception
     * @throws DAOException
     *             the DAO exception
     * @throws ParseadorVisaException
     *             the parseador visa exception
     */
    @Test
    public void crearRespuestaParseadorVisaExceptionTest()
            throws BusinessException, DAOException, ParseadorVisaException {
        Cliente cliente = new Cliente();
        IdentificacionCuenta identificacionCuenta = new IdentificacionCuenta();
        AbstractCuenta cuenta = (AbstractCuenta) obtenerCuenta();
        List<String> listaCodigoTarjeta = new ArrayList<String>();
        Respuesta<Object> resPrueba = new Respuesta<Object>();
        ItemMensajeRespuesta itemRes = new ItemMensajeRespuesta();
        List<ItemMensajeRespuesta> listItemRes = new ArrayList<ItemMensajeRespuesta>();

        listaCodigoTarjeta.add("1234");
        itemRes.setMensaje("Paso por flujo de Error por ParseadorVisaException");
        listItemRes.add(itemRes);
        resPrueba.setItemMensajeRespuesta(listItemRes);

        Mockito.when(cuentaBO.buscarCuentaPorId(Matchers.any(Cliente.class), Matchers.any(IdentificacionCuenta.class)))
                .thenReturn(cuenta);
        Mockito.when(tarjetaDAO.obtenerTarjetasDeVisaWS(Matchers.any(Cuenta.class),
                Matchers.any(OperacionTarjetaWSEnum.class),Matchers.any(Cliente.class))).thenReturn(obtenerRetornoWS(errorTarjetasEntity));
        Mockito.when(parseador.tieneErrorDeCredenciales(Matchers.any(RetornoTarjetasEntity.class))).thenReturn(false);
        Mockito.when(parseador.obtenerTarjetaPorNroTarjetaActiva(Matchers.any(RetornoTarjetasEntity.class),
                Matchers.anyString())).thenThrow(new ParseadorVisaException());
        Mockito.when(respuestaFactory.crearRespuestaError(Matchers.anyString(), Matchers.any(TipoError.class),
                Matchers.anyString())).thenReturn(resPrueba);

        Respuesta<CuotasPendientesDTO> respuesta = cuotasPendientesBO.obtenerCuotasPendientes(identificacionCuenta,
                cliente);

        Assert.assertEquals(resPrueba.getItemsMensajeRespuesta().get(0).getMensaje(),
                respuesta.getItemsMensajeRespuesta().get(0).getMensaje());

    }

    /**
     * Crear respuesta tarjeta entity null con consumos test.
     *
     * @throws BusinessException
     *             the business exception
     * @throws DAOException
     *             the DAO exception
     * @throws ParseadorVisaException
     *             the parseador visa exception
     */
    @Test
    public void crearRespuestaTarjetaEntityNullConConsumosTest()
            throws BusinessException, DAOException, ParseadorVisaException {
        Cliente cliente = new Cliente();
        IdentificacionCuenta identificacionCuenta = new IdentificacionCuenta();
        AbstractCuenta cuenta = (AbstractCuenta) obtenerCuenta();
        List<String> listaCodigoTarjeta = new ArrayList<String>();
        Respuesta<Object> resPrueba = new Respuesta<Object>();
        ItemMensajeRespuesta itemRes = new ItemMensajeRespuesta();
        List<ItemMensajeRespuesta> listItemRes = new ArrayList<ItemMensajeRespuesta>();

        listaCodigoTarjeta.add("1234");
        itemRes.setMensaje("Paso por flujo de Error");
        listItemRes.add(itemRes);
        resPrueba.setItemMensajeRespuesta(listItemRes);

        Mockito.when(cuentaBO.buscarCuentaPorId(Matchers.any(Cliente.class), Matchers.any(IdentificacionCuenta.class)))
                .thenReturn(cuenta);
        Mockito.when(tarjetaDAO.obtenerTarjetasDeVisaWS(Matchers.any(Cuenta.class),
                Matchers.any(OperacionTarjetaWSEnum.class),Matchers.any(Cliente.class))).thenReturn(obtenerRetornoWS(errorTarjetasEntity));
        Mockito.when(parseador.tieneErrorDeCredenciales(Matchers.any(RetornoTarjetasEntity.class))).thenReturn(false);
        Mockito.when(parseador.tarjetaNotieneConsumos(Matchers.any(RetornoTarjetasEntity.class))).thenReturn(false);
        Mockito.when(parseador.obtenerTarjetaPorNroTarjetaActiva(Matchers.any(RetornoTarjetasEntity.class),
                Matchers.anyString())).thenReturn(null);
        Mockito.when(respuestaFactory.crearRespuestaError(Matchers.anyString(), Matchers.any(TipoError.class),
                Matchers.anyString())).thenReturn(resPrueba);

        Respuesta<CuotasPendientesDTO> respuesta = cuotasPendientesBO.obtenerCuotasPendientes(identificacionCuenta,
                cliente);

        Assert.assertEquals(resPrueba.getItemsMensajeRespuesta().get(0).getMensaje(),
                respuesta.getItemsMensajeRespuesta().get(0).getMensaje());

    }

    /**
     * Obtener cuotas pendientes ok con tarjeta cuotas pendiente condicional
     * tarjeta entity false test.
     *
     * @throws BusinessException
     *             the business exception
     * @throws DAOException
     *             the DAO exception
     * @throws ParseadorVisaException
     *             the parseador visa exception
     * @throws TarjetaBOUtilsException
     *             the tarjeta BO utils exception
     */
    @Test
    public void obtenerCuotasPendientesOkConTarjetaCuotasPendienteCondicionalTarjetaEntityFalseTest()
            throws BusinessException, DAOException, ParseadorVisaException, TarjetaBOUtilsException {
        // Una copia del test de abajo, pero con los condicionales de la tarjeta
        // entity de completarMostrarNombreAdicional en false
        Cliente cliente = new Cliente();
        IdentificacionCuenta identificacionCuenta = new IdentificacionCuenta();
        AbstractCuenta cuenta = (AbstractCuenta) obtenerCuenta();
        List<String> listaCodigoTarjeta = new ArrayList<String>();
        List<TarjetaCuotasPendientesEntity> listaTarjetaEntity = obtenerListaTarjetasCuotasPendientes();
        Respuesta<CuotasPendientesDTO> resPrueba = new Respuesta<CuotasPendientesDTO>();

        listaCodigoTarjeta.add("1234");
        resPrueba.setEstadoRespuesta(EstadoRespuesta.OK);
        listaTarjetaEntity.get(0).setCodigoTarjeta("1231237894794");
        ;

        Mockito.when(tarjetaDAO.obtenerTarjetasDeVisaWS(Matchers.any(Cuenta.class),
                Matchers.any(OperacionTarjetaWSEnum.class),Matchers.any(Cliente.class))).thenReturn(obtenerRetornoWS(errorTarjetasEntity));
        Mockito.when(cuentaBO.buscarCuentaPorId(Matchers.any(Cliente.class), Matchers.any(IdentificacionCuenta.class)))
                .thenReturn(cuenta);
        Mockito.when(tarjetaDAO.obtenerTarjetasDeVisaWS(Matchers.any(Cuenta.class),
                Matchers.any(OperacionTarjetaWSEnum.class),Matchers.any(Cliente.class))).thenReturn(obtenerRetornoWS(errorTarjetasEntity));
        Mockito.when(parseador.tieneErrorDeCredenciales(Matchers.any(RetornoTarjetasEntity.class))).thenReturn(false);
        Mockito.when(parseador.obtenerTarjetaPorNroTarjetaActiva(Matchers.any(RetornoTarjetasEntity.class),
                Matchers.anyString()))
                .thenReturn(obtenerTarjetaDesdeTarjetaDocument(
                        obtenerTarjetaDocument(obtenerDatosTarjetaUno(), obtenerCuotasPendientes()), errorTarjetasEntity));
        Mockito.when(parseador.obtenerTotalCuotasPendientes(Matchers.any(TarjetaEntity.class)))
                .thenReturn(new BigDecimal("316.32"));
        Mockito.when(parseador.obtenerCodigosDeTarjetas(Matchers.any(TarjetaEntity.class)))
                .thenReturn(listaCodigoTarjeta);
        Mockito.when(parseador.obtenerTarjetaPorUltimosCuatroNros(Matchers.any(RetornoTarjetasEntity.class),
                Matchers.anyString()))
                .thenReturn(obtenerTarjetaDesdeTarjetaDocument(
                        obtenerTarjetaDocument(obtenerDatosTarjetaUno(), obtenerCuotasPendientes()), errorTarjetasEntity));
        Mockito.when(parseador.esCategoriaTitular(Matchers.any(TarjetaEntity.class))).thenReturn(false);
        Mockito.when(parseador.esCategoriaAdicional(Matchers.any(TarjetaEntity.class))).thenReturn(false);
        Mockito.when(parseador.obtenerNombre(Matchers.any(TarjetaEntity.class))).thenReturn("pepe");
        Mockito.when(parseador.obtenerTarjetaActiva(Matchers.any(TarjetaEntity.class))).thenReturn("123411242392");
        Mockito.when(parseador.obtenerTotalCuotasPendientes(Matchers.any(TarjetaEntity.class)))
                .thenReturn(new BigDecimal("316.32"));
        Mockito.when(respuestaFactory.crearRespuestaOk(Matchers.eq(CuotasPendientesDTO.class),
                Matchers.any(CuotasPendientesDTO.class))).thenReturn(resPrueba);
        Mockito.when(parseador.obtenerListaCuotasPendientes(Matchers.any(TarjetaEntity.class)))
                .thenReturn(listaTarjetaEntity);

        Respuesta<CuotasPendientesDTO> respuesta = cuotasPendientesBO.obtenerCuotasPendientes(identificacionCuenta,
                cliente);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }

    /**
     * Obtener cuotas pendientes ok con tarjeta cuotas pendiente test.
     *
     * @throws BusinessException
     *             the business exception
     * @throws DAOException
     *             the DAO exception
     * @throws ParseadorVisaException
     *             the parseador visa exception
     * @throws TarjetaBOUtilsException
     *             the tarjeta BO utils exception
     */
    @Test
    public void obtenerCuotasPendientesOkConTarjetaCuotasPendienteTest()
            throws BusinessException, DAOException, ParseadorVisaException, TarjetaBOUtilsException {
        // Una copia del test de abajo, pero con
        // parseador.obtenerListaCuotasPendientes(tarjeta) mockeado, de forma
        // tal de acceder al for de TarjetaCuotasPendientesEntity
        Cliente cliente = new Cliente();
        IdentificacionCuenta identificacionCuenta = new IdentificacionCuenta();
        AbstractCuenta cuenta = (AbstractCuenta) obtenerCuenta();
        List<String> listaCodigoTarjeta = new ArrayList<String>();
        List<TarjetaCuotasPendientesEntity> listaTarjetaEntity = obtenerListaTarjetasCuotasPendientes();
        Respuesta<CuotasPendientesDTO> resPrueba = new Respuesta<CuotasPendientesDTO>();

        listaCodigoTarjeta.add("1234");
        resPrueba.setEstadoRespuesta(EstadoRespuesta.OK);

        Mockito.when(tarjetaDAO.obtenerTarjetasDeVisaWS(Matchers.any(Cuenta.class),
                Matchers.any(OperacionTarjetaWSEnum.class),Matchers.any(Cliente.class))).thenReturn(obtenerRetornoWS(errorTarjetasEntity));
        Mockito.when(cuentaBO.buscarCuentaPorId(Matchers.any(Cliente.class), Matchers.any(IdentificacionCuenta.class)))
                .thenReturn(cuenta);
        Mockito.when(tarjetaDAO.obtenerTarjetasDeVisaWS(Matchers.any(Cuenta.class),
                Matchers.any(OperacionTarjetaWSEnum.class),Matchers.any(Cliente.class))).thenReturn(obtenerRetornoWS(errorTarjetasEntity));
        Mockito.when(parseador.tieneErrorDeCredenciales(Matchers.any(RetornoTarjetasEntity.class))).thenReturn(false);
        Mockito.when(parseador.obtenerTarjetaPorNroTarjetaActiva(Matchers.any(RetornoTarjetasEntity.class),
                Matchers.anyString()))
                .thenReturn(obtenerTarjetaDesdeTarjetaDocument(
                        obtenerTarjetaDocument(obtenerDatosTarjetaUno(), obtenerCuotasPendientes()), errorTarjetasEntity));
        Mockito.when(parseador.obtenerTotalCuotasPendientes(Matchers.any(TarjetaEntity.class)))
                .thenReturn(new BigDecimal("316.32"));
        Mockito.when(parseador.obtenerCodigosDeTarjetas(Matchers.any(TarjetaEntity.class)))
                .thenReturn(listaCodigoTarjeta);
        Mockito.when(parseador.obtenerTarjetaPorUltimosCuatroNros(Matchers.any(RetornoTarjetasEntity.class),
                Matchers.anyString()))
                .thenReturn(obtenerTarjetaDesdeTarjetaDocument(
                        obtenerTarjetaDocument(obtenerDatosTarjetaUno(), obtenerCuotasPendientes()), errorTarjetasEntity));
        Mockito.when(parseador.esCategoriaTitular(Matchers.any(TarjetaEntity.class))).thenReturn(true);
        Mockito.when(parseador.esCategoriaAdicional(Matchers.any(TarjetaEntity.class))).thenReturn(true);
        Mockito.when(parseador.obtenerNombre(Matchers.any(TarjetaEntity.class))).thenReturn("pepe");
        Mockito.when(parseador.obtenerTarjetaActiva(Matchers.any(TarjetaEntity.class))).thenReturn("123411242392");
        Mockito.when(parseador.obtenerTotalCuotasPendientes(Matchers.any(TarjetaEntity.class)))
                .thenReturn(new BigDecimal("316.32"));
        Mockito.when(respuestaFactory.crearRespuestaOk(Matchers.eq(CuotasPendientesDTO.class),
                Matchers.any(CuotasPendientesDTO.class))).thenReturn(resPrueba);
        Mockito.when(parseador.obtenerListaCuotasPendientes(Matchers.any(TarjetaEntity.class)))
                .thenReturn(listaTarjetaEntity);

        Respuesta<CuotasPendientesDTO> respuesta = cuotasPendientesBO.obtenerCuotasPendientes(identificacionCuenta,
                cliente);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }

    /**
     * Obtener cuotas pendientes ok test.
     *
     * @throws BusinessException
     *             the business exception
     * @throws DAOException
     *             the DAO exception
     * @throws ParseadorVisaException
     *             the parseador visa exception
     * @throws TarjetaBOUtilsException
     *             the tarjeta BO utils exception
     */
    @Test
    public void obtenerCuotasPendientesOkTest()
            throws BusinessException, DAOException, ParseadorVisaException, TarjetaBOUtilsException {
        Cliente cliente = new Cliente();
        IdentificacionCuenta identificacionCuenta = new IdentificacionCuenta();
        AbstractCuenta cuenta = (AbstractCuenta) obtenerCuenta();
        List<String> listaCodigoTarjeta = new ArrayList<String>();
        Respuesta<CuotasPendientesDTO> resPrueba = new Respuesta<CuotasPendientesDTO>();

        listaCodigoTarjeta.add("1234");
        resPrueba.setEstadoRespuesta(EstadoRespuesta.OK);

        Mockito.when(tarjetaDAO.obtenerTarjetasDeVisaWS(Matchers.any(Cuenta.class),
                Matchers.any(OperacionTarjetaWSEnum.class),Matchers.any(Cliente.class))).thenReturn(obtenerRetornoWS(errorTarjetasEntity));
        Mockito.when(cuentaBO.buscarCuentaPorId(Matchers.any(Cliente.class), Matchers.any(IdentificacionCuenta.class)))
                .thenReturn(cuenta);
        Mockito.when(tarjetaDAO.obtenerTarjetasDeVisaWS(Matchers.any(Cuenta.class),
                Matchers.any(OperacionTarjetaWSEnum.class),Matchers.any(Cliente.class))).thenReturn(obtenerRetornoWS(errorTarjetasEntity));
        Mockito.when(parseador.tieneErrorDeCredenciales(Matchers.any(RetornoTarjetasEntity.class))).thenReturn(false);
        Mockito.when(parseador.obtenerTarjetaPorNroTarjetaActiva(Matchers.any(RetornoTarjetasEntity.class),
                Matchers.anyString()))
                .thenReturn(obtenerTarjetaDesdeTarjetaDocument(
                        obtenerTarjetaDocument(obtenerDatosTarjetaUno(), obtenerCuotasPendientes()), errorTarjetasEntity));
        Mockito.when(parseador.obtenerTotalCuotasPendientes(Matchers.any(TarjetaEntity.class)))
                .thenReturn(new BigDecimal("316.32"));
        Mockito.when(parseador.obtenerCodigosDeTarjetas(Matchers.any(TarjetaEntity.class)))
                .thenReturn(listaCodigoTarjeta);
        Mockito.when(parseador.obtenerTarjetaPorUltimosCuatroNros(Matchers.any(RetornoTarjetasEntity.class),
                Matchers.anyString()))
                .thenReturn(obtenerTarjetaDesdeTarjetaDocument(
                        obtenerTarjetaDocument(obtenerDatosTarjetaUno(), obtenerCuotasPendientes()), errorTarjetasEntity));
        Mockito.when(parseador.esCategoriaTitular(Matchers.any(TarjetaEntity.class))).thenReturn(true);
        Mockito.when(parseador.obtenerNombre(Matchers.any(TarjetaEntity.class))).thenReturn("pepe");
        Mockito.when(parseador.obtenerTarjetaActiva(Matchers.any(TarjetaEntity.class))).thenReturn("123411242392");
        Mockito.when(parseador.obtenerTotalCuotasPendientes(Matchers.any(TarjetaEntity.class)))
                .thenReturn(new BigDecimal("316.32"));
        Mockito.when(respuestaFactory.crearRespuestaOk(Matchers.eq(CuotasPendientesDTO.class),
                Matchers.any(CuotasPendientesDTO.class))).thenReturn(resPrueba);

        Respuesta<CuotasPendientesDTO> respuesta = cuotasPendientesBO.obtenerCuotasPendientes(identificacionCuenta,
                cliente);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
        /*
         * Assert.assertEquals(new BigDecimal("316.32"),
         * respuesta.getRespuesta().getTotalCuotasPendientes());
         * Assert.assertEquals(new BigDecimal("316.32"),
         * respuesta.getRespuesta().getTarjetasCuotasPendientes().get(0).
         * getTotal()); Assert.assertEquals("VISA",
         * respuesta.getRespuesta().getTarjetasCuotasPendientes().get(0).
         * getMarca()); Assert.assertEquals("XXXX-2392",
         * respuesta.getRespuesta().getTarjetasCuotasPendientes().get(0).
         * getNumero());
         */

    }

    /**
     * Generar mocks.
     *
     * @param cuenta the cuenta
     * @param errorTarjetasEntity the error tarjetas entity
     * @param listaCodigoTarjeta the lista codigo tarjeta
     * @param resPruebaError the res prueba error
     * @throws DAOException the DAO exception
     * @throws TarjetaBOUtilsException the tarjeta BO utils exception
     */
    private void generarMocks(AbstractCuenta cuenta,
            ErrorTarjetasEntity errorTarjetasEntity,
            List<String> listaCodigoTarjeta, Respuesta<Object> resPruebaError)
            throws DAOException, TarjetaBOUtilsException {
        Mockito.when(
                tarjetaDAO.obtenerTarjetasDeVisaWS(Matchers.any(Cuenta.class),
                        Matchers.any(OperacionTarjetaWSEnum.class),
                        Matchers.any(Cliente.class)))
                .thenReturn(obtenerRetornoWS(errorTarjetasEntity));
        Mockito.when(cuentaBO.buscarCuentaPorId(Matchers.any(Cliente.class),
                Matchers.any(IdentificacionCuenta.class))).thenReturn(cuenta);
        Mockito.when(parseador.tieneErrorDeCredenciales(
                Matchers.any(RetornoTarjetasEntity.class))).thenReturn(false);
        Mockito.when(parseador.obtenerTarjetaPorNroTarjetaActiva(
                Matchers.any(RetornoTarjetasEntity.class),
                Matchers.anyString())).thenReturn(null);
        Mockito.when(parseador.obtenerTotalCuotasPendientes(
                Matchers.any(TarjetaEntity.class)))
                .thenReturn(new BigDecimal("316.32"));
        Mockito.when(parseador
                .obtenerCodigosDeTarjetas(Matchers.any(TarjetaEntity.class)))
                .thenReturn(listaCodigoTarjeta);
        Mockito.when(parseador.obtenerTarjetaPorUltimosCuatroNros(
                Matchers.any(RetornoTarjetasEntity.class),
                Matchers.anyString()))
                .thenReturn(obtenerTarjetaDesdeTarjetaDocument(
                        obtenerTarjetaDocument(obtenerDatosTarjetaUno(),
                                obtenerCuotasPendientes()),
                        errorTarjetasEntity));
        Mockito.when(
                parseador.esCategoriaTitular(Matchers.any(TarjetaEntity.class)))
                .thenReturn(true);
        Mockito.when(parseador.obtenerNombre(Matchers.any(TarjetaEntity.class)))
                .thenReturn("pepe");
        Mockito.when(parseador
                .obtenerTarjetaActiva(Matchers.any(TarjetaEntity.class)))
                .thenReturn("123411242392");
        Mockito.when(parseador.obtenerTotalCuotasPendientes(
                Matchers.any(TarjetaEntity.class)))
                .thenReturn(new BigDecimal("316.32"));
        Mockito.when(respuestaFactory.crearRespuestaError(Matchers.anyString(),
                Matchers.any(TipoError.class), Matchers.anyString()))
                .thenReturn(resPruebaError);
    }

    /**
     * Obtener cuotas pendientes sin consumos test.
     *
     * @throws BusinessException the business exception
     * @throws DAOException the DAO exception
     * @throws ParseadorVisaException the parseador visa exception
     * @throws TarjetaBOUtilsException the tarjeta BO utils exception
     */
    @Test
    public void obtenerCuotasPendientesSinConsumosTest()
            throws BusinessException, DAOException, ParseadorVisaException,
            TarjetaBOUtilsException {
        Cliente cliente = new Cliente();
        IdentificacionCuenta identificacionCuenta = new IdentificacionCuenta();
        AbstractCuenta cuenta = (AbstractCuenta) obtenerCuenta();
        List<String> listaCodigoTarjeta = new ArrayList<String>();
        listaCodigoTarjeta.add("1234");
        Respuesta<Object> resPruebaError = new Respuesta<Object>();
        resPruebaError.setEstadoRespuesta(EstadoRespuesta.ERROR);

        generarMocks(cuenta, errorTarjetasEntity, listaCodigoTarjeta,
                resPruebaError);

        Respuesta<CuotasPendientesDTO> respuesta = cuotasPendientesBO
                .obtenerCuotasPendientes(identificacionCuenta, cliente);
        Assert.assertEquals(EstadoRespuesta.ERROR,
                respuesta.getEstadoRespuesta());
    }

    /**
     * Obtener cuotas pendientes con consumos test.
     *
     * @throws BusinessException the business exception
     * @throws DAOException the DAO exception
     * @throws ParseadorVisaException the parseador visa exception
     * @throws TarjetaBOUtilsException the tarjeta BO utils exception
     */
    @Test
    public void obtenerCuotasPendientesConConsumosTest()
            throws BusinessException, DAOException, ParseadorVisaException,
            TarjetaBOUtilsException {
        Cliente cliente = new Cliente();
        IdentificacionCuenta identificacionCuenta = new IdentificacionCuenta();
        Cuenta cuenta = new Cuenta();
        cuenta.setTipoCuentaEnum(TipoCuenta.VISA);
        cuenta.setCodigoTitularidad("TI");
        cuenta.setNroTarjetaCredito("4509950140722392");
        AbstractCuenta cuentaAbstracta = (AbstractCuenta) cuenta;
        List<String> listaCodigoTarjeta = new ArrayList<String>();
        listaCodigoTarjeta.add("1234");
        Respuesta<Object> resPruebaError = new Respuesta<Object>();
        resPruebaError.setEstadoRespuesta(EstadoRespuesta.ERROR);

        generarMocks(cuentaAbstracta, errorTarjetasEntity, listaCodigoTarjeta,
                resPruebaError);

        Respuesta<CuotasPendientesDTO> respuesta = cuotasPendientesBO
                .obtenerCuotasPendientes(identificacionCuenta, cliente);
        Assert.assertEquals(EstadoRespuesta.ERROR,
                respuesta.getEstadoRespuesta());
    }

    /**
     * Obtener cuotas pendientes error distinto A sin consumos test.
     *
     * @throws BusinessException the business exception
     * @throws DAOException the DAO exception
     * @throws ParseadorVisaException the parseador visa exception
     * @throws TarjetaBOUtilsException the tarjeta BO utils exception
     */
    @Test
    public void obtenerCuotasPendientesErrorDistintoASinConsumosTest()
            throws BusinessException, DAOException, ParseadorVisaException,
            TarjetaBOUtilsException {
        Cliente cliente = new Cliente();
        IdentificacionCuenta identificacionCuenta = new IdentificacionCuenta();
        AbstractCuenta cuenta = (AbstractCuenta) obtenerCuenta();
        List<String> listaCodigoTarjeta = new ArrayList<String>();
        listaCodigoTarjeta.add("1234");
        Respuesta<Object> resPruebaError = new Respuesta<Object>();
        resPruebaError.setEstadoRespuesta(EstadoRespuesta.ERROR);
        ErrorTarjetasEntity errorTarjetasEntity = new ErrorTarjetasEntity();
        errorTarjetasEntity.setCodigo(ERROR_DISTINTO_A_SIN_MOVIMIENTOS);

        generarMocks(cuenta, errorTarjetasEntity, listaCodigoTarjeta,
                resPruebaError);

        Respuesta<CuotasPendientesDTO> respuesta = cuotasPendientesBO
                .obtenerCuotasPendientes(identificacionCuenta, cliente);
        Assert.assertEquals(EstadoRespuesta.ERROR,
                respuesta.getEstadoRespuesta());
    }

    /**
     * Obtener cuenta.
     *
     * @return the cuenta
     */
    private Cuenta obtenerCuenta() {
        Cuenta cuenta = new Cuenta();
        cuenta.setTipoCuentaEnum(TipoCuenta.VISA);
        cuenta.setCodigoTitularidad("TI");
        return cuenta;
    }

    /**
     * Obtener retorno WS.
     *
     * @return the retorno WS
     */
    private RetornoTarjetasEntity obtenerRetornoWS(ErrorTarjetasEntity errorTarjetasEntity) {
        RetornoTarjetasEntity retornoWS = new RetornoTarjetasEntity();
        retornoWS.setTarjetas(obtenerTarjetas(errorTarjetasEntity));
        return retornoWS;
    }

    /**
     * Obtener tarjetas.
     *
     * @return the tarjetas
     */
    private TarjetasEntity obtenerTarjetas(ErrorTarjetasEntity errorTarjetasEntity) {
        TarjetasEntity tarjetas = new TarjetasEntity();
        tarjetas.setTarjetaList(obtenerListaTarjetas(errorTarjetasEntity));
        return tarjetas;
    }

    /**
     * Obtener lista tarjetas.
     *
     * @return the list
     */
    private List<TarjetaEntity> obtenerListaTarjetas(ErrorTarjetasEntity errorTarjetasEntity) {
        List<TarjetaEntity> tarjetaList = new ArrayList<TarjetaEntity>();
        tarjetaList.add(obtenerTarjetaDesdeTarjetaDocument(
                obtenerTarjetaDocument(obtenerDatosTarjetaUno(), obtenerCuotasPendientes()), errorTarjetasEntity));
        return tarjetaList;
    }

    /**
     * Obtener tarjeta desde tarjeta document.
     *
     * @param tarjetaDocument
     *            the tarjeta document
     * @return the tarjeta
     */
    private TarjetaEntity obtenerTarjetaDesdeTarjetaDocument(TarjetaDocumentEntity tarjetaDocument, ErrorTarjetasEntity errorTarjetasEntity) {
        TarjetaEntity tarjeta = new TarjetaEntity();
        tarjeta.setTarjetaDocument(tarjetaDocument);
        tarjeta.setError(errorTarjetasEntity);
        return tarjeta;
    }

    /**
     * Obtener tarjeta document.
     *
     * @param datos
     *            the datos
     * @param cuotasPendientes
     *            the cuotas pendientes
     * @return the tarjeta document
     */
    private TarjetaDocumentEntity obtenerTarjetaDocument(DatosEntity datos, CuotasPendientesEntity cuotasPendientes) {
        TarjetaDocumentEntity tarjetaDocument = new TarjetaDocumentEntity();
        tarjetaDocument.setDatos(datos);
        tarjetaDocument.setCuotasPendientes(cuotasPendientes);
        return tarjetaDocument;
    }

    /**
     * Obtener cuotas pendientes.
     *
     * @return the cuotas pendientes
     */
    private CuotasPendientesEntity obtenerCuotasPendientes() {
        CuotasPendientesEntity cuotasPendientes = new CuotasPendientesEntity();
        cuotasPendientes.setTotales(obtenerTotales("0,00", "316,32"));
        cuotasPendientes.setTarjetaList(obtenerListaTarjetasCuotasPendientes());
        return cuotasPendientes;
    }

    /**
     * Obtener la lista de tarjetas con cuotas pendientes.
     *
     * @return the list
     */
    private List<TarjetaCuotasPendientesEntity> obtenerListaTarjetasCuotasPendientes() {
        List<TarjetaCuotasPendientesEntity> tarjetaCuotasPendientesDTOList = new ArrayList<TarjetaCuotasPendientesEntity>();
        tarjetaCuotasPendientesDTOList.add(obtenerTarjetaCuotasPendientes("XXXX XXXX XXXX2392", "0,00", "316,32",
                obtenerListaDetalleCuotaPendienteTarjeta()));
        return tarjetaCuotasPendientesDTOList;
    }

    /**
     * Obtener tarjeta con cuotas pendientes.
     *
     * @param codigoTarjeta
     *            the codigo tarjeta
     * @param dolares
     *            the dolares
     * @param pesos
     *            the pesos
     * @param detallesCuotas
     *            the detalles cuotas
     * @return the tarjeta cuotas pendientes DTO
     */
    private TarjetaCuotasPendientesEntity obtenerTarjetaCuotasPendientes(String codigoTarjeta, String dolares,
            String pesos, List<DetalleCuotaPendienteEntity> detallesCuotas) {
        TarjetaCuotasPendientesEntity tarjetaDTO = new TarjetaCuotasPendientesEntity();
        tarjetaDTO.setCodigoTarjeta(codigoTarjeta);
        tarjetaDTO.setPesos(pesos);
        tarjetaDTO.setDolares(dolares);
        tarjetaDTO.setDetalleCuotaPendienteList(detallesCuotas);
        return tarjetaDTO;
    }

    /**
     * Obtener la lista de detalles de cuota pendiente por tarjeta.
     *
     * @return the list
     */
    private List<DetalleCuotaPendienteEntity> obtenerListaDetalleCuotaPendienteTarjeta() {
        List<DetalleCuotaPendienteEntity> detalleList = new ArrayList<DetalleCuotaPendienteEntity>();
        detalleList.add(obtenerDetalleCuotaPendiente("23431780", "12", "02", "WWW.GARBARINO.COM.AR               ",
                "07/07/2015", "316,32", "pesos"));
        return detalleList;
    }

    /**
     * Obtener cada detalle de cuota pendiente.
     *
     * @param comprobante
     *            the comprobante
     * @param cuotas
     *            the cuotas
     * @param cuotasPendientes
     *            the cuotas pendientes
     * @param establecimiento
     *            the establecimiento
     * @param fecha
     *            the fecha
     * @param importe
     *            the importe
     * @param moneda
     *            the moneda
     * @return the detalle cuota pendiente
     */
    private DetalleCuotaPendienteEntity obtenerDetalleCuotaPendiente(String comprobante, String cuotas,
            String cuotasPendientes, String establecimiento, String fecha, String importe, String moneda) {
        DetalleCuotaPendienteEntity detalleCuotaPendiente = new DetalleCuotaPendienteEntity();
        detalleCuotaPendiente.setEstablecimiento(establecimiento);
        detalleCuotaPendiente.setFecha(fecha);
        detalleCuotaPendiente.setComprobante(comprobante);
        detalleCuotaPendiente.setCuotasPendientes(cuotasPendientes);
        detalleCuotaPendiente.setMoneda(moneda);
        detalleCuotaPendiente.setImporte(importe);
        return detalleCuotaPendiente;
    }

    /**
     * Obtener totales de las cuotas pendientes.
     *
     * @param dolares
     *            the dolares
     * @param pesos
     *            the pesos
     * @return the totales
     */
    private Totales obtenerTotales(String dolares, String pesos) {
        Totales totales = new Totales();
        totales.setDolares(dolares);
        totales.setPesos(pesos);
        return totales;
    }

    /**
     * Obtener datos de la tarjeta.
     *
     * @return the datos
     */
    private DatosEntity obtenerDatosTarjetaUno() {
        DatosEntity datos = new DatosEntity();
        datos.setAffinityGroup("Grupo_Afinidad");
        datos.setApellido("SPORLEDER");
        datos.setCategoria("0");
        datos.setCodTipoTarjeta("CHIP EMV C/CONTAC");
        datos.setCodigoSucursal("201");
        datos.setCuenta("413034030");
        datos.setFechaDesde("04/05/2015");
        datos.setFechaNacimiento("18/11/1984");
        datos.setHabiente("SPORLEDER/BELEN");
        datos.setNombre("BELEN");
        datos.setDocumento("31303514");
        datos.setProducto("Gold");
        datos.setTarjetaActiva("4509950140722392");
        datos.setTarjetaProdu("CR0202");
        datos.setTelefono("8060529");
        datos.setTipoDocumento(obtenerTipoDocumento("1", "DNI"));
        datos.setTipoTarjetaDetalle("CHIP EMV C/CONTAC");
        datos.setTitular("SPORLEDER/BELEN");
        datos.setVencimiento("1608");
        return datos;
    }

    /**
     * Obtener el objeto TipoDocumento.
     *
     * @param codigo
     *            the codigo
     * @param valor
     *            the valor
     * @return the tipo documento
     */
    private TipoDocumento obtenerTipoDocumento(String codigo, String valor) {
        TipoDocumento tipoDocumento = new TipoDocumento();
        tipoDocumento.setCodigo(codigo);
        tipoDocumento.setValor(valor);
        return tipoDocumento;
    }

}
