package ar.com.santanderrio.obp.servicios.marcaviajero.web.manager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

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
import ar.com.santanderrio.obp.generated.webservices.marcaviajero.Fecha;
import ar.com.santanderrio.obp.generated.webservices.marcaviajero.ObtenerPaisesResponse;
import ar.com.santanderrio.obp.generated.webservices.marcaviajero.ObtenerPaisesResponse.Paises;
import ar.com.santanderrio.obp.generated.webservices.marcaviajero.ObtenerTarjetasDelSocioRequest;
import ar.com.santanderrio.obp.generated.webservices.marcaviajero.ObtenerTarjetasDelSocioResponse;
import ar.com.santanderrio.obp.generated.webservices.marcaviajero.ObtenerTarjetasDelSocioResponse.Tarjetas;
import ar.com.santanderrio.obp.generated.webservices.marcaviajero.Pais;
import ar.com.santanderrio.obp.generated.webservices.marcaviajero.TarjetaConFecha;
import ar.com.santanderrio.obp.generated.webservices.marcaviajero.TarjetaConFecha.FechasInhabilitadas;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.dao.DatosSolicitanteDAO;
import ar.com.santanderrio.obp.servicios.comun.dao.entities.DatosSolicitanteCriterioEntity;
import ar.com.santanderrio.obp.servicios.comun.dao.entities.DatosSolicitanteEntity;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.impl.EstadisticaManagerImpl;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.login.dto.CredencialesMya;
import ar.com.santanderrio.obp.servicios.marcaviajero.bo.DatosTarjetasBO;
import ar.com.santanderrio.obp.servicios.marcaviajero.bo.MarcaViajeroBOImpl;
import ar.com.santanderrio.obp.servicios.marcaviajero.dao.MarcaViajeroDAO;
import ar.com.santanderrio.obp.servicios.marcaviajero.entities.NuevoViaje;
import ar.com.santanderrio.obp.servicios.monedero.exception.OperacionFueraHorarioSucursalException;
import ar.com.santanderrio.obp.servicios.monedero.exception.SinAccesoALaInformacionException;

/**
 * The Class CuentaManagerTest.
 */
@RunWith(MockitoJUnitRunner.class)
public class MarcaViajeroBONuevoViajeTest {

    /** The cuenta manager. */
    @InjectMocks
    private MarcaViajeroManagerImpl marcaViajeroManager = new MarcaViajeroManagerImpl();

    /** The marca viajero BO. */
    @InjectMocks
    private MarcaViajeroBOImpl marcaViajeroBO = new MarcaViajeroBOImpl();

    /** The mensaje BO. */
    @Mock
    private MensajeBO mensajeBO;

    /** The estadistica manager. */
    @Mock
    private EstadisticaManagerImpl estadisticaManager;

    /** The sesion cliente. */
    @Mock
    private SesionCliente sesionCliente;

    /** The sesion parametros. */
    @Mock
    private SesionParametros sesionParametros;

    /** The marca viajero DAO. */
    @Mock
    private MarcaViajeroDAO marcaViajeroDAO;

    /** The datos solicitante DAO. */
    @Mock
    private DatosSolicitanteDAO datosSolicitanteDAO;

    /** The paises. */
    @Mock
    private Paises paises;

    /** The tarjetas. */
    @Mock
    private Tarjetas tarjetas;

    /** The fechas inhabilitadas. */
    @Mock
    private FechasInhabilitadas fechasInhabilitadas;

    /** The respuesta factory. */
    @InjectMocks
    @Spy
    private RespuestaFactory respuestaFactory = new RespuestaFactory();

    /** The datos tarjetas BO. */
    @Mock
    private DatosTarjetasBO datosTarjetasBO;
    /** The Constant DURACION_MAXIMA_VIAJE. */
    private static final int DURACION_MAXIMA_VIAJE = 180;

    /** The Constant DIAS_MAX_INICIO_VIAJE. */
    private static final int DIAS_MAX_INICIO_VIAJE = 30;

    /**
     * Nuevo viaje OK test.
     *
     * @throws BusinessException
     *             the business exception
     * @throws DAOException
     *             the DAO exception
     * @throws SinAccesoALaInformacionException
     *             the sin acceso A la informacion exception
     * @throws OperacionFueraHorarioSucursalException
     *             the operacion fuera horario sucursal exception
     * @throws DatatypeConfigurationException
     *             the datatype configuration exception
     */
    @Test
    public void nuevoViajeOKTest() throws BusinessException, DAOException,
            SinAccesoALaInformacionException,
            OperacionFueraHorarioSucursalException,
            DatatypeConfigurationException {
        mockCliente();
        mockearCredencialesMya();
        mockearDatosDelCliente();
        mockearObtenerPaises();
        mockGetPais();
        mockMensajeBO();
        mockearObtenerTarjetasDelSocio();

        Cliente cliente = new Cliente();
        List<Cuenta> cuentas = new ArrayList<Cuenta>();
        Cuenta cuenta = new Cuenta();
        cuenta.setTipoCuenta("07");
        cuentas.add(cuenta);
        
        cliente.setCuentas(cuentas);
        String mailCliente = "";
        Respuesta<NuevoViaje> respuestaNuevoViaje = marcaViajeroBO
                .nuevoViaje(cliente, mailCliente);

        assertNotNull(respuestaNuevoViaje);
        assertEquals(EstadoRespuesta.OK,
                respuestaNuevoViaje.getEstadoRespuesta());
        assertEquals(5,
                respuestaNuevoViaje.getRespuesta().getDestinos().size());
        assertEquals(5, respuestaNuevoViaje.getRespuesta().getTarjetas().get(0)
                .getTarjetasAsociadas().get(0).getFechaInhabilitada().size());
        assertEquals(2, respuestaNuevoViaje.getRespuesta().getTarjetas().size());
    }

    /**
     * Nuevo viaje error test.
     *
     * @throws BusinessException
     *             the business exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void nuevoViajeErrorTest() throws BusinessException, DAOException {
        mockCliente();
        mockearCredencialesMya();
        mockMensajeBO();

        Cliente cliente = new Cliente();
        List<Cuenta> cuentas = new ArrayList<Cuenta>();
        Cuenta cuenta = new Cuenta();
        cuenta.setTipoCuenta("07");
        cuentas.add(cuenta);
        
        cliente.setCuentas(cuentas);
        String mailCliente = "";
        when(marcaViajeroDAO.obtenerPaises()).thenThrow(new DAOException());
        Respuesta<NuevoViaje> respuestaNuevoViaje = marcaViajeroBO
                .nuevoViaje(cliente, mailCliente);
        assertNotNull(respuestaNuevoViaje);
        assertEquals(EstadoRespuesta.ERROR,
                respuestaNuevoViaje.getEstadoRespuesta());
        assertEquals(TipoError.ERROR_GENERICO.toString(), respuestaNuevoViaje
                .getItemsMensajeRespuesta().get(0).getTipoError());
    }

    /**
     * Mock get pais.
     */
    private void mockGetPais() {
        List<Pais> listaPaises;

        listaPaises = new ArrayList<Pais>();
        Pais pais = new Pais();
        pais.setCodPais("ALE");
        pais.setDescripcionPais("ALEMANIA");
        listaPaises.add(pais);

        pais = new Pais();
        pais.setCodPais("ALE");
        pais.setDescripcionPais("ARGENTINA");
        listaPaises.add(pais);

        pais = new Pais();
        pais.setCodPais("BOL");
        pais.setDescripcionPais("BOLIVIA");
        listaPaises.add(pais);

        pais = new Pais();
        pais.setCodPais("ECU");
        pais.setDescripcionPais("ECUADOR");
        listaPaises.add(pais);

        pais = new Pais();
        pais.setCodPais("RUS");
        pais.setDescripcionPais("RUSIA");
        listaPaises.add(pais);
        when(paises.getPais()).thenReturn(listaPaises);
    }

    /**
     * Mockear obtener paises.
     *
     * @throws DAOException
     *             the DAO exception
     */
    private void mockearObtenerPaises() throws DAOException {
        ObtenerPaisesResponse obtenerPaisesResponse = new ObtenerPaisesResponse();
        obtenerPaisesResponse.setPaises(paises);
        when(marcaViajeroDAO.obtenerPaises()).thenReturn(obtenerPaisesResponse);
    }

    /**
     * Mockear obtener tarjetas del socio.
     *
     * @throws DatatypeConfigurationException
     *             the datatype configuration exception
     * @throws DAOException
     *             the DAO exception
     */
    private void mockearObtenerTarjetasDelSocio()
            throws DatatypeConfigurationException, DAOException {
        ObtenerTarjetasDelSocioResponse obtenerTarjetasDelSocioResponse = new ObtenerTarjetasDelSocioResponse();
        obtenerTarjetasDelSocioResponse.setFechaInicioMaxima(agregarDiasXmlGregorianCalendar(new Date(), DIAS_MAX_INICIO_VIAJE));
        obtenerTarjetasDelSocioResponse.setDuracionMaxima(DURACION_MAXIMA_VIAJE);
        obtenerTarjetasDelSocioResponse.setTarjetas(tarjetas);
        when(marcaViajeroDAO.obtenerTarjetasDelSocio(Matchers.any(ObtenerTarjetasDelSocioRequest.class))).thenReturn(obtenerTarjetasDelSocioResponse);

        List<TarjetaConFecha> tarjetaList = new ArrayList<TarjetaConFecha>();
        TarjetaConFecha tarjetaConFecha = new TarjetaConFecha();

        tarjetaConFecha = new TarjetaConFecha();
        tarjetaConFecha.setApellidoNombre("Maria Martinez");
        tarjetaConFecha.setCondicion("TITULAR");
        tarjetaConFecha.setFechasInhabilitadas(fechasInhabilitadas);
        tarjetaConFecha.setProducto("Tarjeta VISA");
        tarjetaConFecha.setUltimosCuatro("1111");
        tarjetaList.add(tarjetaConFecha);
        
        tarjetaConFecha = new TarjetaConFecha();
        tarjetaConFecha.setApellidoNombre("Maria Martinez");
        tarjetaConFecha.setCondicion("TITULAR");
        tarjetaConFecha.setFechasInhabilitadas(fechasInhabilitadas);
        tarjetaConFecha.setProducto("Tarjeta VISA");
        tarjetaConFecha.setUltimosCuatro("2222");
        tarjetaList.add(tarjetaConFecha);
        
        tarjetaConFecha = new TarjetaConFecha();
        tarjetaConFecha.setApellidoNombre("Maria Martinez");
        tarjetaConFecha.setCondicion("ADICIONAL");
        tarjetaConFecha.setFechasInhabilitadas(fechasInhabilitadas);
        tarjetaConFecha.setProducto("Tarjeta VISA");
        tarjetaConFecha.setUltimosCuatro("3333");
        tarjetaList.add(tarjetaConFecha);
        
        tarjetaConFecha = new TarjetaConFecha();
        tarjetaConFecha.setApellidoNombre("Juan Alvarez");
        tarjetaConFecha.setCondicion("TITULAR");
        tarjetaConFecha.setFechasInhabilitadas(fechasInhabilitadas);
        tarjetaConFecha.setProducto("Tarjeta VISA");
        tarjetaConFecha.setUltimosCuatro("4444");
        tarjetaList.add(tarjetaConFecha);
        
        when(tarjetas.getTarjeta()).thenReturn(tarjetaList);

        List<Fecha> fechaList = new ArrayList<Fecha>();

        Fecha fecha = new Fecha();
        fecha.setValue(agregarDiasXmlGregorianCalendar(new Date(), 50));
        fechaList.add(fecha);
        fecha = new Fecha();
        fecha.setValue(agregarDiasXmlGregorianCalendar(new Date(), 51));
        fechaList.add(fecha);
        fecha = new Fecha();
        fecha.setValue(agregarDiasXmlGregorianCalendar(new Date(), 52));
        fechaList.add(fecha);
        fecha = new Fecha();
        fecha.setValue(agregarDiasXmlGregorianCalendar(new Date(), 53));
        fechaList.add(fecha);
        fecha = new Fecha();
        fecha.setValue(agregarDiasXmlGregorianCalendar(new Date(), 53));
        fechaList.add(fecha);

        fecha = new Fecha();
        fecha.setValue(agregarDiasXmlGregorianCalendar(new Date(), 60));
        fechaList.add(fecha);
        fecha = new Fecha();
        fecha.setValue(agregarDiasXmlGregorianCalendar(new Date(), 61));
        fechaList.add(fecha);

        fecha = new Fecha();
        fecha.setValue(agregarDiasXmlGregorianCalendar(new Date(), 65));
        fechaList.add(fecha);

        fecha = new Fecha();
        fecha.setValue(agregarDiasXmlGregorianCalendar(new Date(), 70));
        fechaList.add(fecha);
        fecha = new Fecha();
        fecha.setValue(agregarDiasXmlGregorianCalendar(new Date(), 71));
        fechaList.add(fecha);

        fecha = new Fecha();
        fecha.setValue(agregarDiasXmlGregorianCalendar(new Date(), 80));
        fechaList.add(fecha);

        Collections.shuffle(fechaList);
        when(fechasInhabilitadas.getFecha()).thenReturn(fechaList);
    }

    /**
     * Agregar dias xml gregorian calendar.
     *
     * @param fechaMaxInicioViaje
     *            the fecha max inicio viaje
     * @param diasMaxInicioViaje
     *            the dias max inicio viaje
     * @return the XML gregorian calendar
     * @throws DatatypeConfigurationException
     *             the datatype configuration exception
     */
    private XMLGregorianCalendar agregarDiasXmlGregorianCalendar(
            Date fechaMaxInicioViaje, Integer diasMaxInicioViaje)
            throws DatatypeConfigurationException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaMaxInicioViaje);
        calendar.add(Calendar.DATE, diasMaxInicioViaje);
        fechaMaxInicioViaje = calendar.getTime();
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(fechaMaxInicioViaje);
        XMLGregorianCalendar xmlGregorianCalendar = DatatypeFactory
                .newInstance().newXMLGregorianCalendar(gregorianCalendar);
        return xmlGregorianCalendar;
    }

    /**
     * Mockear datos del cliente.
     *
     * @throws DAOException
     *             the DAO exception
     * @throws SinAccesoALaInformacionException
     *             the sin acceso A la informacion exception
     * @throws OperacionFueraHorarioSucursalException
     *             the operacion fuera horario sucursal exception
     */
    private void mockearDatosDelCliente()
            throws DAOException, SinAccesoALaInformacionException,
            OperacionFueraHorarioSucursalException {
        DatosSolicitanteEntity datosSolicitanteEntity = new DatosSolicitanteEntity();
        when(datosSolicitanteDAO
                .getDatosDelSolicitante(Matchers.any(DatosSolicitanteCriterioEntity.class), Matchers.any(Cliente.class)))
                        .thenReturn(datosSolicitanteEntity);
    }

    /**
     * Mockear credenciales mya.
     */
    private void mockearCredencialesMya() {
        CredencialesMya credencialesMya = new CredencialesMya();
        credencialesMya.setEmail("juan@prueba.org");
        when(sesionParametros.getCredencialesMya()).thenReturn(credencialesMya);
    }

    /**
     * Mock sesion cliente.
     */
    private void mockCliente() {
        Cliente cliente = new Cliente();
        cliente.setNombre("Pedro");
        cliente.setApellido1("Caseres");
        cliente.setApellido2("Guiterrez");
        cliente.setDni("1234");
        cliente.setTipoDocumento("N");
        when(sesionCliente.getCliente()).thenReturn(cliente);
    }

    /**
     * Mock mensaje BO.
     */
    private void mockMensajeBO() {
        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("Mensaje de ayuda marca viajero");
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString()))
                .thenReturn(mensaje);
    }

}
