package ar.com.santanderrio.obp.servicios.debinws.bo;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.exceptions.ISBANRuntimeException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.reporte.entities.TipoArchivoEnum;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.generated.webservices.debin.Error;
import ar.com.santanderrio.obp.generated.webservices.debin.*;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.Segmento;
import ar.com.santanderrio.obp.servicios.comun.ContadorIntentos;
import ar.com.santanderrio.obp.servicios.comun.estadistica.bo.EstadisticaBO;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.impl.EstadisticaManagerImpl;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.dao.MensajeDAO;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.debinapi.dao.DebinApiDAO;
import ar.com.santanderrio.obp.servicios.debinapi.dto.*;
import ar.com.santanderrio.obp.servicios.debinapi.utils.DebinApiMapper;
import ar.com.santanderrio.obp.servicios.debinws.bo.impl.DebinWSBOImpl;
import ar.com.santanderrio.obp.servicios.debinws.common.EstadoDebinEnum;
import ar.com.santanderrio.obp.servicios.debinws.dao.DebinWSDAO;
import ar.com.santanderrio.obp.servicios.debinws.dto.*;
import ar.com.santanderrio.obp.servicios.debinws.mock.DebinWSMock;
import ar.com.santanderrio.obp.servicios.debinws.view.PagarDebinWSView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DebinWSBOTest {

    public static final String MONEDA_PRISMA = "032";
    @InjectMocks
    private DebinWSBO debinWSBO = new DebinWSBOImpl();

    @Mock
    private DebinApiDAO debinApiDAO;

    @Mock
    private DebinWSDAO debinWSDAO;

    @InjectMocks
    @Spy
    private final DebinApiMapper debinApiMapper = new DebinApiMapper();

    @Mock
    private SesionParametros sesionParametros;

    @Mock
    private SesionCliente sesionCliente;

    @InjectMocks
    @Spy
    private RespuestaFactory respuestaFactory = new RespuestaFactory();

    @Mock
    private MensajeBO mensajeBO;

    @Mock
    private MensajeDAO mensajeDAO;

    @InjectMocks
    @Spy
    private EstadisticaManager estadisticaManager = new EstadisticaManagerImpl();

    @Mock
    private EstadisticaBO estadisticaBO;

    
    private Mensaje mensaje = new Mensaje();

    /** The cliente. */
    private Cliente cliente = new Cliente();
    
    @Before
    public void init() {
        mensaje.setMensaje("Mensaje");
        MockitoAnnotations.initMocks(this);
        cliente = new Cliente();
        List<Cuenta> cuentas = new ArrayList<Cuenta>();
        Cuenta cuenta = new Cuenta();
        cuenta.setCbu("0720033520000000819954");
        cuenta.setAlias("asdasdasd");
        cuenta.setNroSucursal("02");
        cuenta.setTipoCuenta("2");
        cuenta.setNroCuentaProducto("3423423423432");
        cuentas.add(cuenta );
        cliente.setCuentas(cuentas );
        Segmento segmento = new Segmento();
        segmento.setSelect(false);
        segmento.setDuo(false);
        segmento.setPyme(false);
        segmento.setUniversidad(false);
        cliente.setNombre("Silvina");
        cliente.setApellido1("Luque");
        cliente.setApellido2("M");
        cliente.setSegmento(segmento);
        cliente.setNup("123456789");
        cliente.setNumeroCUILCUIT("27216775134");
        cliente.setDni("21677513");
        cliente.setTipoDocumento("N");

        ReflectionTestUtils.setField(debinWSBO, "debinApiEnabled", Boolean.FALSE);
        ReflectionTestUtils.setField(debinApiMapper, "strConcepts", "01-Alquileres-1-ALQ|02-Cuotas-2-CUO|03-Expensas-3-EXP|04-Facturas-4-FAC|05-Préstamos-5-PRE|06-Seguros-6-SEG|07-Honorarios-7-HON|08-Haberes-8-HAB|09-Varios-9-VAR|0M-Plazo Fijo Web-M-PLF");
        ReflectionTestUtils.setField(debinApiMapper, "emptyListCode", "85");
        ReflectionTestUtils.setField(debinApiMapper, "okListCode", "00");
    }
    
    @Test
    public void consultaDebinOkTest() throws DAOException {
        //***CALLING PAYMENT PROCESSOR **************************************************************************************
        ConsultaDebinWSInDTO input = new ConsultaDebinWSInDTO();

        EstadoDebinWSBusquedaDTO estadoBusquedaDTO = new EstadoDebinWSBusquedaDTO();
        estadoBusquedaDTO.setNroPagina(1);
        estadoBusquedaDTO.setEstado(EstadoDebinEnum.ACREDITADO);
        input.setPrimerEstadoBusqueda(estadoBusquedaDTO);
        input.setCanal("E");
        input.setNroDocumento("00021587183");
        input.setTipoDocumento("N");
        GregorianCalendar cal1 = new GregorianCalendar(2018, 3, 12);
        input.setFechaDesde(cal1.getTime());
        GregorianCalendar cal2 = new GregorianCalendar(2018, 3, 19);
        input.setFechaHasta(cal2.getTime());
        input.setConsultaComprador(true);

        //response WS
        ResponseListaDebin paymentProcessorResponse = getDefaultResponseListaDebin();
        Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
        Mockito.when(debinWSDAO.listaDebinNew(Matchers.any(RequestListaDebin.class))).thenReturn(paymentProcessorResponse);
        
        Respuesta<ConsultaDebinWSOutDTO> responseUsingDebinWSDAO  = debinWSBO.consultaDebin(input);
        Assert.assertEquals(EstadoRespuesta.OK, responseUsingDebinWSDAO .getEstadoRespuesta());
        assertResponse(responseUsingDebinWSDAO , paymentProcessorResponse,paymentProcessorResponse.getDebines().size(), EstadoRespuesta.OK);

        verify(debinWSDAO, times(1)).listaDebinNew(any(RequestListaDebin.class));
        verify(debinApiDAO, times(0)).getDebinList(any(DebinListRequest.class));
        Mockito.reset(debinWSDAO);
        //*** CALLING DEBIN-API **************************************************************************************
        ReflectionTestUtils.setField(debinWSBO, "debinApiEnabled", Boolean.TRUE);
        DebinListDTO debinListDTO = getDefaultDebinListDTO(paymentProcessorResponse);
        Mockito.when(debinApiDAO.getDebinList(Matchers.any(DebinListRequest.class))).thenReturn(debinListDTO);

        Respuesta<ConsultaDebinWSOutDTO>  responseUsingDebinApi = debinWSBO.consultaDebin(input);
        //ASSERTS
        assertResponse(responseUsingDebinApi, paymentProcessorResponse,paymentProcessorResponse.getDebines().size(), EstadoRespuesta.OK);
        verify(debinWSDAO, times(0)).listaDebinNew(any(RequestListaDebin.class));
        verify(debinApiDAO, times(1)).getDebinList(any(DebinListRequest.class));

    }

    @Test
    public void consultaDebinOkTest_retrying() throws DAOException {
        //***CALLING PAYMENT PROCESSOR **************************************************************************************
        ConsultaDebinWSInDTO input = new ConsultaDebinWSInDTO();

        EstadoDebinWSBusquedaDTO estadoBusquedaDTO = new EstadoDebinWSBusquedaDTO();
        estadoBusquedaDTO.setNroPagina(1);
        estadoBusquedaDTO.setEstado(EstadoDebinEnum.ACREDITADO);
        input.setPrimerEstadoBusqueda(estadoBusquedaDTO);
        input.setCanal("E");
        input.setNroDocumento("00021587183");
        input.setTipoDocumento("N");
        GregorianCalendar cal1 = new GregorianCalendar(2018, 3, 12);
        input.setFechaDesde(cal1.getTime());
        GregorianCalendar cal2 = new GregorianCalendar(2018, 3, 19);
        input.setFechaHasta(cal2.getTime());
        input.setConsultaComprador(true);

        //response WS
        ResponseListaDebin paymentProcessorResponse = getDefaultResponseListaDebin();
        Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
        Mockito.when(debinWSDAO.listaDebinNew(Matchers.any(RequestListaDebin.class))).thenReturn(paymentProcessorResponse);

        Respuesta<ConsultaDebinWSOutDTO> responseUsingDebinWSDAO  = debinWSBO.consultaDebin(input);
        Assert.assertEquals(EstadoRespuesta.OK, responseUsingDebinWSDAO .getEstadoRespuesta());
        assertResponse(responseUsingDebinWSDAO , paymentProcessorResponse,paymentProcessorResponse.getDebines().size(), EstadoRespuesta.OK);

        verify(debinWSDAO, times(1)).listaDebinNew(any(RequestListaDebin.class));
        verify(debinApiDAO, times(0)).getDebinList(any(DebinListRequest.class));
        Mockito.reset(debinWSDAO);


        //*** CALLING DEBIN-API **************************************************************************************
        ReflectionTestUtils.setField(debinWSBO, "debinApiEnabled", Boolean.TRUE);
        Mockito.when(debinApiDAO.getDebinList(Matchers.any(DebinListRequest.class))).thenThrow(new DAOException("un error", "80"));

        //Como falló se llama al segundo provider y mockeamos para que este sí devuelva ok
        when(debinWSDAO.listaDebinNew(Matchers.any(RequestListaDebin.class))).thenReturn(paymentProcessorResponse);

        Respuesta<ConsultaDebinWSOutDTO>  responseUsingDebinApi = debinWSBO.consultaDebin(input);
        //ASSERTS
        assertResponse(responseUsingDebinApi, paymentProcessorResponse,paymentProcessorResponse.getDebines().size(), EstadoRespuesta.OK);
        verify(debinWSDAO, times(1)).listaDebinNew(any(RequestListaDebin.class));
        verify(debinApiDAO, times(1)).getDebinList(any(DebinListRequest.class));

    }

    @Test
    public void consultaDebinSegundoLlamadoOkTest() throws DAOException {
        //***CALLING PAYMENT PROCESSOR **************************************************************************************
        ConsultaDebinWSInDTO input = new ConsultaDebinWSInDTO();

        EstadoDebinWSBusquedaDTO estadoBusquedaDTO = new EstadoDebinWSBusquedaDTO();
        estadoBusquedaDTO.setNroPagina(1);
        estadoBusquedaDTO.setEstado(EstadoDebinEnum.ACREDITADO);
        input.setPrimerEstadoBusqueda(estadoBusquedaDTO);
        input.setCanal("E");
        input.setNroDocumento("00021587183");
        input.setTipoDocumento("N");
        GregorianCalendar cal1 = new GregorianCalendar(2018, 3, 12);
        input.setFechaDesde(cal1.getTime());
        GregorianCalendar cal2 = new GregorianCalendar(2018, 3, 19);
        input.setFechaHasta(cal2.getTime());
        input.setConsultaComprador(true);
        input.setSegundoEstadoBusqueda(estadoBusquedaDTO);
        //response WS
        ResponseListaDebin paymentProcessorResponse = getDefaultResponseListaDebin();

        Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
        Mockito.when(debinWSDAO.listaDebinNew(Matchers.any(RequestListaDebin.class))).thenReturn(paymentProcessorResponse);
        
        Respuesta<ConsultaDebinWSOutDTO> responseUsingDebinWSDAO  = debinWSBO.consultaDebin(input);
        //ASSERTS
        assertResponse(responseUsingDebinWSDAO , paymentProcessorResponse,8, EstadoRespuesta.OK);
        Assert.assertEquals(0, responseUsingDebinWSDAO .getRespuesta().getSiguientePagina());

        verify(debinWSDAO, times(2)).listaDebinNew(any(RequestListaDebin.class));
        verify(debinApiDAO, times(0)).getDebinList(any(DebinListRequest.class));
        Mockito.reset(debinWSDAO);

        //*** CALLING DEBIN-API **************************************************************************************
        ReflectionTestUtils.setField(debinWSBO, "debinApiEnabled", Boolean.TRUE);
        DebinListDTO debinListDTO = getDefaultDebinListDTO(paymentProcessorResponse);
        Mockito.when(debinApiDAO.getDebinList(Matchers.any(DebinListRequest.class))).thenReturn(debinListDTO);

        Respuesta<ConsultaDebinWSOutDTO>  responseUsingDebinApi = debinWSBO.consultaDebin(input);
        //ASSERTS
        assertResponse(responseUsingDebinApi, paymentProcessorResponse,8, EstadoRespuesta.OK);
        verify(debinWSDAO, times(0)).listaDebinNew(any(RequestListaDebin.class));
        verify(debinApiDAO, times(2)).getDebinList(any(DebinListRequest.class));


    }

    private void assertResponse(Respuesta<ConsultaDebinWSOutDTO> response,  ResponseListaDebin responseMocked, int expectedSize, EstadoRespuesta estadoRespuesta){
        Assert.assertEquals(estadoRespuesta, response.getEstadoRespuesta());
        Assert.assertEquals(0, response.getRespuesta().getSiguientePagina());

        List<DebinWSDTO> debins =  response.getRespuesta().getDebinesDTO();
        Assert.assertEquals(expectedSize, debins.size());

        final int responseListMockedSize = responseMocked.getDebines().size();
        for (int totalResponsesMocked = 0; totalResponsesMocked < debins.size()/ responseListMockedSize; ++totalResponsesMocked) {
            for (int i = 0; i < responseListMockedSize; ++i) {
                DebinWSDTO debinWSDTO = debins.get(i + (responseListMockedSize * totalResponsesMocked));
                ResumenDebinDTO debinReference = responseMocked.getDebines().get(i);

                Assert.assertEquals(debinReference.getId(), debinWSDTO.getDebinId());
                Assert.assertEquals(EstadoDebinEnum.ACREDITADO, debinWSDTO.getEstado());
                Assert.assertEquals(debinReference.getConcepto(), debinWSDTO.getConcepto());
                Assert.assertEquals(debinReference.getVendedor().getCuit(), debinWSDTO.getCuitSolicitante());
                Assert.assertEquals(debinReference.getImporte(), debinWSDTO.getImporte());
                Assert.assertEquals(CurrencyType.fromString(debinReference.getMoneda()), debinWSDTO.getMoneda());
                Assert.assertEquals(debinReference.getComprador().getTitular(), debinWSDTO.getNombreDestinatario());
                Assert.assertEquals(debinReference.getVendedor().getTitular(), debinWSDTO.getNombreSolicitante());
                Assert.assertEquals(debinReference.getVencimiento().toGregorianCalendar().getTime(), debinWSDTO.getFechaVencimiento());
                Assert.assertEquals(debinReference.getFechaCreacion().toGregorianCalendar().getTime(), debinWSDTO.getFechaCreacion());
            }
        }

    }

    private DebinListDTO getDefaultDebinListDTO( ResponseListaDebin responseListaDebin) {
        DebinListDTO debinListDTO = new DebinListDTO();
        debinListDTO.setTotalPages(2);
        List<DebinDetailDTO> debins = new ArrayList<DebinDetailDTO>();
        for (int i = 0; i < responseListaDebin.getDebines().size(); ++i) {

            DebinDetailDTO debin = new DebinDetailDTO();
            ResumenDebinDTO resumenDebinDTO = responseListaDebin.getDebines().get(i);
            debin.setAmount(new BigDecimal(resumenDebinDTO.getImporte()));
            debin.setAssociatedOperationId(resumenDebinDTO.getId());
            debin.setConcept(ConceptType.HON);
            debin.setCurrency(CurrencyType.fromString(resumenDebinDTO.getMoneda()));
            debin.setDebinType(DebinType.SPOT);

            DateTime expirationDate = new DateTime(resumenDebinDTO.getVencimiento().toGregorianCalendar().getTime(), DateTimeZone.UTC);
            debin.setExpirationDate(expirationDate.toString());

            DateTime creationDate = new DateTime(resumenDebinDTO.getFechaCreacion().toGregorianCalendar().getTime(), DateTimeZone.UTC);
            debin.setCreationDate(creationDate.toString());

            debin.setId(resumenDebinDTO.getId());
            debin.setRefund(false);
            //BUYER
            DebinBuyerDTO debinBuyerDTO = new DebinBuyerDTO();
            debinBuyerDTO.setCuit(resumenDebinDTO.getComprador().getCuit());
            debinBuyerDTO.setFancyName(resumenDebinDTO.getComprador().getTitular());

            DebinAccountDTO debinAccountDTO = new DebinAccountDTO();
            debinAccountDTO.setCbu(resumenDebinDTO.getComprador().getCuenta().getCbu());
            debinBuyerDTO.setDebinAccountDTO(debinAccountDTO);
            debin.setDebinBuyerDTO(debinBuyerDTO);
            //SELLER
            DebinSellerDTO debinSellerDTO = new DebinSellerDTO();
            debinSellerDTO.setCuit(resumenDebinDTO.getVendedor().getCuit());

            DebinAccountDTO debinAccountSellerDTO = new DebinAccountDTO();
            debinAccountSellerDTO.setCbu(resumenDebinDTO.getVendedor().getCuenta().getCbu());
            debinSellerDTO.setDebinAccountDTO(debinAccountSellerDTO);
            debinSellerDTO.setFancyName(resumenDebinDTO.getVendedor().getTitular());
            debin.setDebinSellerDTO(debinSellerDTO);

            DebinStatusDTO debinStatusDTO = new DebinStatusDTO();
            debinStatusDTO.setCode(DebinStatus.CREDITED.toString());
            debin.setDebinStatusDTO(debinStatusDTO);

            debins.add(debin);
        }

        debinListDTO.setDebins(debins);
        return debinListDTO;
    }

    private static ResponseListaDebin getDefaultResponseListaDebin(){
        ResponseListaDebin response = new ResponseListaDebin();
        response.setSiguientePagina(2);
        List<ResumenDebinDTO> debines = new ArrayList<ResumenDebinDTO>();

        ResumenDebinDTO resumenDebinDTO1 = new ResumenDebinDTO();
        EstadoDebinDTO estadoDebin = new EstadoDebinDTO();
        estadoDebin.setCodigo("0");
        estadoDebin.setDescripcion("ACREDITADO");
        resumenDebinDTO1.setEstadoDebin(estadoDebin);
        CompradorDebinDTO comprador = new CompradorDebinDTO();
        comprador.setCuit("33123423432");
        comprador.setTitular("Andres Calamaro");
        comprador.setDescripcion("La desc del comprador");
        CuentaDebinDTO cuenta = new CuentaDebinDTO();
        cuenta.setCbu("0720000788000006013640");
        comprador.setCuenta(cuenta);
        resumenDebinDTO1.setComprador(comprador);
        VendedorDebinDTO vendedor = new VendedorDebinDTO();
        vendedor.setTitular("Charly Gacria");
        vendedor.setCuit("234234323332");
        CuentaDebinDTO vendedorCuenta = new CuentaDebinDTO();
        vendedorCuenta.setCbu("0720175888000037232054");
        vendedor.setCuenta(cuenta);
        resumenDebinDTO1.setVendedor(vendedor);
        resumenDebinDTO1.setImporte("3434.44");
        resumenDebinDTO1.setMoneda(MONEDA_PRISMA);
        XMLGregorianCalendar c = new XMLGregorianCalendarImpl();
        resumenDebinDTO1.setVencimiento(c);
        XMLGregorianCalendar d = new XMLGregorianCalendarImpl();
        resumenDebinDTO1.setFechaCreacion(d);
        resumenDebinDTO1.setId("1");
        resumenDebinDTO1.setConcepto("07");
        debines.add(resumenDebinDTO1);

        ResumenDebinDTO resumenDebinDTO2 = new ResumenDebinDTO();
        resumenDebinDTO2.setEstadoDebin(estadoDebin);
        resumenDebinDTO2.setComprador(comprador);
        resumenDebinDTO2.setVendedor(vendedor);
        resumenDebinDTO2.setImporte("11111.00");
        resumenDebinDTO2.setMoneda(MONEDA_PRISMA);
        resumenDebinDTO2.setVencimiento(c);
        resumenDebinDTO2.setFechaCreacion(d);
        resumenDebinDTO2.setId("2");
        resumenDebinDTO2.setConcepto("07");

        debines.add(resumenDebinDTO2);

        ResumenDebinDTO resumenDebinDTO3 = new ResumenDebinDTO();
        resumenDebinDTO3.setEstadoDebin(estadoDebin);
        resumenDebinDTO3.setComprador(comprador);
        resumenDebinDTO3.setVendedor(vendedor);
        resumenDebinDTO3.setImporte("4444.00");
        resumenDebinDTO3.setMoneda(MONEDA_PRISMA);
        resumenDebinDTO3.setId("3");
        resumenDebinDTO3.setVencimiento(c);
        resumenDebinDTO3.setFechaCreacion(d);
        resumenDebinDTO3.setConcepto("07");
        debines.add(resumenDebinDTO3);

        ResumenDebinDTO resumenDebinDTO4 = new ResumenDebinDTO();
        resumenDebinDTO4.setEstadoDebin(estadoDebin);
        resumenDebinDTO4.setComprador(comprador);
        resumenDebinDTO4.setVendedor(vendedor);
        resumenDebinDTO4.setImporte("444477");
        resumenDebinDTO4.setMoneda(MONEDA_PRISMA);
        resumenDebinDTO4.setVencimiento(c);
        resumenDebinDTO4.setFechaCreacion(d);
        resumenDebinDTO4.setId("4");
        resumenDebinDTO4.setConcepto("07");
        debines.add(resumenDebinDTO4);

        Error error = new Error();
        error.setCodigo("00");
        error.setMensaje("Ok");
        response.setError(error );
        response.setDebines(debines);
        return response;
    }
    
    @Test
    public void consultaDebinSegundoLlamadoWarning() throws DAOException {
        //***CALLING PAYMENT PROCESSOR **************************************************************************************

        ConsultaDebinWSInDTO input = new ConsultaDebinWSInDTO();

        EstadoDebinWSBusquedaDTO primerEstadoBusquedaDTO = new EstadoDebinWSBusquedaDTO();
        primerEstadoBusquedaDTO.setNroPagina(1);
        primerEstadoBusquedaDTO.setEstado(EstadoDebinEnum.ACREDITADO);
        input.setPrimerEstadoBusqueda(primerEstadoBusquedaDTO);
        input.setCanal("E");
        input.setNroDocumento("00021587183");
        input.setTipoDocumento("N");
        GregorianCalendar cal1 = new GregorianCalendar(2018, 3, 12);
        input.setFechaDesde(cal1.getTime());
        GregorianCalendar cal2 = new GregorianCalendar(2018, 3, 19);
        input.setFechaHasta(cal2.getTime());
        input.setConsultaComprador(true);
        
        EstadoDebinWSBusquedaDTO segundoEstadoBusquedaDTO = new EstadoDebinWSBusquedaDTO();
        segundoEstadoBusquedaDTO.setEstado(EstadoDebinEnum.ERROR_DEBITO);
        input.setSegundoEstadoBusqueda(segundoEstadoBusquedaDTO);
        //response WS
        ResponseListaDebin response =getDefaultResponseListaDebin();
        ResponseListaDebin response2 =getDefaultResponseListaDebin();

        Error error2 = new Error();
        error2.setCodigo("80");
        error2.setMensaje("Error");
        response2.setError(error2);

        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(debinWSDAO.listaDebinNew(Matchers.any(RequestListaDebin.class))).thenReturn(response,response2);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);

        Respuesta<ConsultaDebinWSOutDTO> responseUsingDebinWSDAO = debinWSBO.consultaDebin(input);

        Assert.assertEquals(EstadoRespuesta.WARNING, responseUsingDebinWSDAO.getEstadoRespuesta());
        verify(debinWSDAO, times(2)).listaDebinNew(any(RequestListaDebin.class));
        verify(debinApiDAO, times(0)).getDebinList(any(DebinListRequest.class));
        Mockito.reset(debinWSDAO);

        //*** CALLING DEBIN-API **************************************************************************************
        ReflectionTestUtils.setField(debinWSBO, "debinApiEnabled", Boolean.TRUE);
        DebinListDTO debinListDTO = getDefaultDebinListDTO(response);

        DebinListDTO debinListDTO2 = getDefaultDebinListDTO(response);
        debinListDTO2.setDebins(null);

        //It returns a debinListDTO the first time is called (with primerEstadoBusqueda) , and the second time (with segundoEstadoBusqueda) an exception
        doReturn(debinListDTO).doThrow(new DAOException("un error", "80")).when(debinApiDAO).getDebinList(Matchers.any(DebinListRequest.class));
        //como falla la segunda llamada a debinApiDAO reintentamos llamando al otro provider
        when(debinWSDAO.listaDebinNew(Matchers.any(RequestListaDebin.class))).thenReturn(response2);

        Respuesta<ConsultaDebinWSOutDTO>  responseUsingDebinApi = debinWSBO.consultaDebin(input);
        //ASSERTS
        Assert.assertEquals(EstadoRespuesta.WARNING, responseUsingDebinApi.getEstadoRespuesta());
        verify(debinWSDAO, times(1)).listaDebinNew(any(RequestListaDebin.class));
        verify(debinApiDAO, times(2)).getDebinList(any(DebinListRequest.class));


    }


    @Test
    public void consultaDebinSegundoLlamadoError() throws DAOException {

        //***CALLING PAYMENT PROCESSOR **************************************************************************************
        ConsultaDebinWSInDTO input = new ConsultaDebinWSInDTO();

        EstadoDebinWSBusquedaDTO estadoBusquedaDTO = new EstadoDebinWSBusquedaDTO();
        estadoBusquedaDTO.setNroPagina(1);
        estadoBusquedaDTO.setEstado(EstadoDebinEnum.ACREDITADO);
        input.setPrimerEstadoBusqueda(estadoBusquedaDTO);
        input.setCanal("E");
        input.setNroDocumento("00021587183");
        input.setTipoDocumento("N");
        GregorianCalendar cal1 = new GregorianCalendar(2018, 3, 12);
        input.setFechaDesde(cal1.getTime());
        GregorianCalendar cal2 = new GregorianCalendar(2018, 3, 19);
        input.setFechaHasta(cal2.getTime());
        input.setConsultaComprador(true);
        
        EstadoDebinWSBusquedaDTO estadoBusquedaDTO2 = new EstadoDebinWSBusquedaDTO();
        estadoBusquedaDTO2.setEstado(EstadoDebinEnum.ERROR_DEBITO);
        input.setSegundoEstadoBusqueda(estadoBusquedaDTO2);

        //response WS
        ResponseListaDebin response =getDefaultResponseListaDebin();
        ResponseListaDebin response2 =getDefaultResponseListaDebin();

        Error error = new Error();
        error.setCodigo("00");
        error.setMensaje("Ok");
        response.setError(error );

        Error error2 = new Error();
        error2.setCodigo("80");
        error2.setMensaje("Error");
        response2.setError(error2);

        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(debinWSDAO.listaDebinNew(Matchers.any(RequestListaDebin.class))).thenReturn(response).thenThrow(new DAOException());
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        
        Respuesta<ConsultaDebinWSOutDTO> responseUsingDebinWSDAO  = debinWSBO.consultaDebin(input);

        Assert.assertEquals(EstadoRespuesta.WARNING, responseUsingDebinWSDAO .getEstadoRespuesta());
        assertResponse(responseUsingDebinWSDAO , response,4, EstadoRespuesta.WARNING);
        verify(debinWSDAO, times(2)).listaDebinNew(any(RequestListaDebin.class));
        verify(debinApiDAO, times(0)).getDebinList(any(DebinListRequest.class));

        Mockito.reset(debinWSDAO);

        //***CALLING DEBIN-API **************************************************************************************
        ReflectionTestUtils.setField(debinWSBO, "debinApiEnabled", Boolean.TRUE);
        DebinListDTO debinListDTO = getDefaultDebinListDTO(response);

        DebinListDTO debinListDTO2 = getDefaultDebinListDTO(response);
        debinListDTO2.setDebins(null);

        doReturn(debinListDTO).doThrow(new DAOException("un error", "80")).when(debinApiDAO).getDebinList(Matchers.any(DebinListRequest.class));

        //Como falló el segundo llamado
        when(debinWSDAO.listaDebinNew(Matchers.any(RequestListaDebin.class))).thenReturn(response2);

        Respuesta<ConsultaDebinWSOutDTO>  responseUsingDebinApi = debinWSBO.consultaDebin(input);
        //ASSERTS
        Assert.assertEquals(EstadoRespuesta.WARNING, responseUsingDebinApi.getEstadoRespuesta());
        assertResponse(responseUsingDebinApi , response,4, EstadoRespuesta.WARNING);

        verify(debinWSDAO, times(1)).listaDebinNew(any(RequestListaDebin.class));
        verify(debinApiDAO, times(2)).getDebinList(any(DebinListRequest.class));
    }

    @Test
    public void consultaDebinErrorWS() throws DAOException {
        //***CALLING PAYMENT PROCESSOR **************************************************************************************

        Cliente cliente = new Cliente();
        cliente.setNumeroCUILCUIT("20215871836");
        sesionCliente.setCliente(cliente);
        sesionCliente.setIpCliente("180.166.8.241");

        ConsultaDebinWSInDTO input = new ConsultaDebinWSInDTO();

        EstadoDebinWSBusquedaDTO estadoBusquedaDTO = new EstadoDebinWSBusquedaDTO();
        estadoBusquedaDTO.setNroPagina(1);
        estadoBusquedaDTO.setEstado(EstadoDebinEnum.ACREDITADO);
        input.setPrimerEstadoBusqueda(estadoBusquedaDTO);
        input.setCanal("E");
        input.setNroDocumento("00021587183");
        input.setTipoDocumento("N");
        GregorianCalendar cal1 = new GregorianCalendar(2018, 3, 12);
        input.setFechaDesde(cal1.getTime());
        GregorianCalendar cal2 = new GregorianCalendar(2018, 3, 19);
        input.setFechaHasta(cal2.getTime());
        input.setConsultaComprador(true);

        //response WS
        ResponseListaDebin response =getDefaultResponseListaDebin();

        Error error = new Error();
        error.setCodigo("83");
        error.setMensaje("Debin no existe");
        response.setError(error );

        Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        Mockito.when(mensajeDAO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        Mockito.when(debinWSDAO.listaDebinNew(Matchers.any(RequestListaDebin.class))).thenReturn(response);
        
        Respuesta<ConsultaDebinWSOutDTO> responseUsingDebinWSDAO  = debinWSBO.consultaDebin(input);

        Assert.assertEquals(EstadoRespuesta.ERROR, responseUsingDebinWSDAO .getEstadoRespuesta());
        verify(debinWSDAO, times(1)).listaDebinNew(any(RequestListaDebin.class));
        verify(debinApiDAO, times(0)).getDebinList(any(DebinListRequest.class));
        Mockito.reset(debinWSDAO);

        //***CALLING DEBIN-API **************************************************************************************
        ReflectionTestUtils.setField(debinWSBO, "debinApiEnabled", Boolean.TRUE);

        doThrow(new DAOException("un error", "83")).when(debinApiDAO).getDebinList(Matchers.any(DebinListRequest.class));
        //Reintenta llamando al otro provider
        when(debinWSDAO.listaDebinNew(Matchers.any(RequestListaDebin.class))).thenReturn(response);

        Respuesta<ConsultaDebinWSOutDTO>  responseUsingDebinApi = debinWSBO.consultaDebin(input);
        //ASSERTS
        Assert.assertEquals(EstadoRespuesta.ERROR, responseUsingDebinApi.getEstadoRespuesta());
        verify(debinWSDAO, times(1)).listaDebinNew(any(RequestListaDebin.class));
        verify(debinApiDAO, times(1)).getDebinList(any(DebinListRequest.class));
    }

    @Test
    public void consultaDebinDAOExceptionTest() throws DAOException {
        //***CALLING PAYMENT PROCESSOR **************************************************************************************
        Cliente cliente = new Cliente();
        cliente.setNumeroCUILCUIT("20215871836");
        sesionCliente.setCliente(cliente);
        sesionCliente.setIpCliente("180.166.8.241");

        ConsultaDebinWSInDTO input = new ConsultaDebinWSInDTO();

        EstadoDebinWSBusquedaDTO estadoBusquedaDTO = new EstadoDebinWSBusquedaDTO();
        estadoBusquedaDTO.setNroPagina(1);
        estadoBusquedaDTO.setEstado(EstadoDebinEnum.ACREDITADO);
        input.setPrimerEstadoBusqueda(estadoBusquedaDTO);
        input.setCanal("E");
        input.setNroDocumento("00021587183");
        input.setTipoDocumento("N");
        GregorianCalendar cal1 = new GregorianCalendar(2018, 3, 12);
        input.setFechaDesde(cal1.getTime());
        GregorianCalendar cal2 = new GregorianCalendar(2018, 3, 19);
        input.setFechaHasta(cal2.getTime());
        input.setConsultaComprador(true);

        Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        Mockito.when(mensajeDAO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        Mockito.when(debinWSDAO.listaDebinNew(Matchers.any(RequestListaDebin.class))).thenThrow(new DAOException());
        
        Respuesta<ConsultaDebinWSOutDTO> responseUsingDebinWSDAO  = debinWSBO.consultaDebin(input);

        Assert.assertEquals(EstadoRespuesta.ERROR, responseUsingDebinWSDAO .getEstadoRespuesta());
        verify(debinWSDAO, times(1)).listaDebinNew(any(RequestListaDebin.class));
        verify(debinApiDAO, times(0)).getDebinList(any(DebinListRequest.class));
        Mockito.reset(debinWSDAO);

        //***CALLING DEBIN-API **************************************************************************************
        ReflectionTestUtils.setField(debinWSBO, "debinApiEnabled", Boolean.TRUE);

        doThrow(new DAOException()).when(debinApiDAO).getDebinList(Matchers.any(DebinListRequest.class));

        //como debinApiDAO falla va a llamar a debinWSDAO
        ResponseListaDebin response2 =getDefaultResponseListaDebin();
        Error error2 = new Error();
        error2.setCodigo("99");
        error2.setMensaje("Error");
        response2.setError(error2);
        when(debinWSDAO.listaDebinNew(Matchers.any(RequestListaDebin.class))).thenReturn(response2,response2);

        Respuesta<ConsultaDebinWSOutDTO>  responseUsingDebinApi = debinWSBO.consultaDebin(input);
        //ASSERTS
        Assert.assertEquals(EstadoRespuesta.ERROR, responseUsingDebinApi.getEstadoRespuesta());
        verify(debinWSDAO, times(1)).listaDebinNew(any(RequestListaDebin.class));
        verify(debinApiDAO, times(1)).getDebinList(any(DebinListRequest.class));
    }

    @Test
    public void consultaDetalleDebinOkTest() throws DAOException {
        ResponseDebin responseDebin = new ResponseDebin();
        responseDebin.setPreautorizado(false);
        CompradorDebinDTO comprador = new CompradorDebinDTO();
        CuentaDebinDTO cuentaComprador = new CuentaDebinDTO();
        cuentaComprador.setCbu("0720033520000000819954");
        comprador.setCuenta(cuentaComprador );
        responseDebin.setComprador(comprador);
        DetalleDebinDTO detalleDebin = new DetalleDebinDTO();
        EstadoDebinDTO estadoDTO = new EstadoDebinDTO();
        estadoDTO.setCodigo("1");
        estadoDTO.setDescripcion("ACREDITADO");
        detalleDebin.setEstadoDebin(estadoDTO );
        detalleDebin.setConcepto("02");
        GregorianCalendar cal = new GregorianCalendar(2018,
                1, 27);
        detalleDebin.setFechaCreacion(new XMLGregorianCalendarImpl(cal));
        GregorianCalendar cal2 = new GregorianCalendar(2018, 5, 27);
        detalleDebin.setFechaExpiracion(new XMLGregorianCalendarImpl(cal2));
        detalleDebin.setDescripcion("Compra de patineta voladora");
        detalleDebin.setIdDebin("dxcsd234vsdd");
        detalleDebin.setImporte("345.56");
        detalleDebin.setMoneda(MONEDA_PRISMA);
        responseDebin.setDetalleDebin(detalleDebin);
        Error error = new Error();
        error.setCodigo("00");
        error.setMensaje("El mensaje");
        responseDebin.setError(error);
        responseDebin.setEstado("ACREDITADO");
        VendedorDebinDTO vendedor = new VendedorDebinDTO();
        CuentaDebinDTO cuenta = new CuentaDebinDTO();
        cuenta.setCbu("0720000788000006013640");
        cuenta.setAlias("AliasVendedor");
        vendedor.setCuenta(cuenta );
        vendedor.setCuit("12345678912");
        vendedor.setTitular("Juan Perez");
        responseDebin.setVendedor(vendedor);

        Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
        Mockito.when(debinWSDAO.consultaDebinNew(Matchers.any(RequestDebin.class))).thenReturn(responseDebin);

        Respuesta<ConsultaDetalleDebinWSOutDTO> respuesta = debinWSBO.consultaDetalleDebin(DebinWSMock.obtenerConsultaDetalleDebinWSInDTO());

        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }

    @Test
    public void consultaDetalleDebinErrorWSTest() throws DAOException {
        ResponseDebin responseDebin = new ResponseDebin();
        Error error = new Error();
        error.setCodigo("83");
        error.setMensaje("Debin no existe");
        responseDebin.setError(error );
      
        Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        Mockito.when(mensajeDAO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        Mockito.when(debinWSDAO.consultaDebinNew(Matchers.any(RequestDebin.class))).thenReturn(responseDebin);

        Respuesta<ConsultaDetalleDebinWSOutDTO> respuesta = debinWSBO.consultaDetalleDebin(DebinWSMock.obtenerConsultaDetalleDebinWSInDTO());

        Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
    }

    @Test
    public void consultaDetalleDebinExceptionTest() throws DAOException {       
        Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        Mockito.when(mensajeDAO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        Mockito.when(debinWSDAO.consultaDebinNew(Matchers.any(RequestDebin.class))).thenThrow(new DAOException());

        Respuesta<ConsultaDetalleDebinWSOutDTO> respuesta = debinWSBO.consultaDetalleDebin(DebinWSMock.obtenerConsultaDetalleDebinWSInDTO());

        Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
    }
    
    @Test
    public void consultaDetalleTimeOut() throws DAOException {
        DAOException error = new DAOException();
        error.setErrorCode("099");
        
        Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        Mockito.when(mensajeDAO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        Mockito.when(debinWSDAO.consultaDebinNew(Matchers.any(RequestDebin.class))).thenThrow(error);
        
        Respuesta<ConsultaDetalleDebinWSOutDTO> respuesta = debinWSBO.consultaDetalleDebin(DebinWSMock.obtenerConsultaDetalleDebinWSInDTO());

        Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
    }
    
    @Test
    public void consultaDetalleErrorCUIT() throws DAOException {
        DAOException error = new DAOException();
        error.setErrorCode("098");
        
        Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        Mockito.when(mensajeDAO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        Mockito.when(debinWSDAO.consultaDebinNew(Matchers.any(RequestDebin.class))).thenThrow(error);

        Respuesta<ConsultaDetalleDebinWSOutDTO> respuesta = debinWSBO.consultaDetalleDebin(DebinWSMock.obtenerConsultaDetalleDebinWSInDTO());

        Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
    }

    @Test
    public void descargarComprobanteOkTest() {
        Reporte reportePrueba = new Reporte();
        reportePrueba.setNombre("Comprobante_EliminacionDebin.pdf");
        reportePrueba.setTipoArchivo(TipoArchivoEnum.PDF);

        ComprobanteInDTO input = new ComprobanteInDTO();
        input.setImporte("3000.5");
        input.setFechaSolicitud("16/02/2018");
        input.setFechaVencimiento("25/02/2018");
        input.setNumeroCuenta("100-123456/7");
        input.setTipoCuenta("Cuenta única");
        input.setSolicitante("Loca Bohemia SRL");
        input.setCuit("30246789545");
        input.setCbu("2245678965432177772250");
        input.setAlias("ejemploalias123");
        input.setDescripcion("Compra de helicoptero");
        input.setConcepto("02");
        input.setIdDebin("CdAS241FSFZ23dw");
        input.setComprobante("0123456789");
        input.setFecha("20/03/2018 15:53");
        input.setTipoComprobante("EliminacionDebin");

        Mockito.when(debinWSDAO.descargarComprobante(Matchers.any(ComprobanteInDTO.class))).thenReturn(reportePrueba);
        
        Respuesta<Reporte> respuesta = debinWSBO.descargarComprobante(input);

        Assert.assertNotNull(respuesta);
        Assert.assertEquals(reportePrueba.getNombre(), respuesta.getRespuesta().getNombre());
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }

    @Test
    public void descargarComprobanteErrorTest() {
        Reporte reportePrueba = new Reporte();
        reportePrueba.setNombre("Comprobante_EliminacionDebin.pdf");
        reportePrueba.setTipoArchivo(TipoArchivoEnum.PDF);

        ComprobanteInDTO input = new ComprobanteInDTO();
        input.setImporte("3000.5");
        input.setFechaSolicitud("16/02/2018");
        input.setFechaVencimiento("25/02/2018");
        input.setNumeroCuenta("100-123456/7");
        input.setTipoCuenta("Cuenta única");
        input.setSolicitante("Loca Bohemia SRL");
        input.setCuit("30246789545");
        input.setCbu("2245678965432177772250");
        input.setAlias("ejemploalias123");
        input.setDescripcion("Compra de helicoptero");
        input.setConcepto("02");
        input.setIdDebin("CdAS241FSFZ23dw");
        input.setComprobante("0123456789");
        input.setFecha("20/03/2018 15:53");
        input.setTipoComprobante("EliminacionDebin");

        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        Mockito.when(debinWSDAO.descargarComprobante(Matchers.any(ComprobanteInDTO.class))).thenThrow(new ISBANRuntimeException());

        Respuesta<Reporte> respuesta = debinWSBO.descargarComprobante(input);

        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
    }
    
    @Test
    public void eliminarDebinOK() throws DAOException {
        Response responseEliminar = new Response();
        responseEliminar.setEstado("");
        
        Error error = new Error();
        error.setCodigo("00");
        error.setMensaje("Ok");
        responseEliminar.setError(error);
        
        when(sesionParametros.getDetalleDebin()).thenReturn(DebinWSMock.obtenerDetalleOutBO());
        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(debinWSDAO.eliminarDebin(Matchers.any(RequestDebin.class))).thenReturn(responseEliminar);
        
        Respuesta<DebinWSEliminarOutDTO> respuesta = debinWSBO.eliminarDebin();
        
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }
    
    @Test
    public void eliminarDebinError() throws DAOException {
        Response responseEliminar = new Response();
        responseEliminar.setEstado("");
        
        Error error = new Error();
        error.setCodigo("00");
        error.setMensaje("Ok");
        responseEliminar.setError(error);
        
        when(sesionParametros.getDetalleDebin()).thenReturn(DebinWSMock.obtenerDetalleOutBO());
        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(debinWSDAO.eliminarDebin(Matchers.any(RequestDebin.class))).thenThrow(new DAOException());
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        when(sesionParametros.getContador()).thenReturn(new ContadorIntentos(2));
        
        Respuesta<DebinWSEliminarOutDTO> respuesta = debinWSBO.eliminarDebin();
        
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
    }
    
    @Test
    public void eliminarDebinErrorTimeOut() throws DAOException {
        Response responseEliminar = new Response();
        responseEliminar.setEstado("");
        
        Error error = new Error();
        error.setCodigo("00");
        error.setMensaje("Ok");
        responseEliminar.setError(error);
        
        DAOException e = new DAOException();
        e.setErrorCode("099");
        
        when(sesionParametros.getDetalleDebin()).thenReturn(DebinWSMock.obtenerDetalleOutBO());
        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(debinWSDAO.eliminarDebin(Matchers.any(RequestDebin.class))).thenThrow(e);
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        when(sesionParametros.getContador()).thenReturn(new ContadorIntentos(-1));    
        
        Respuesta<DebinWSEliminarOutDTO> respuesta = debinWSBO.eliminarDebin();
        
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
    }

    @Test
    public void eliminarDebinErrorCodigo() throws DAOException {
        Response responseEliminar = new Response();
        responseEliminar.setEstado("");
        
        Error error = new Error();
        error.setCodigo("10");
        error.setMensaje("Error");
        responseEliminar.setError(error);
        
        when(sesionParametros.getDetalleDebin()).thenReturn(DebinWSMock.obtenerDetalleOutBO());
        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(debinWSDAO.eliminarDebin(Matchers.any(RequestDebin.class))).thenReturn(responseEliminar);
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        when(sesionParametros.getContador()).thenReturn(new ContadorIntentos(2));
        
        Respuesta<DebinWSEliminarOutDTO> respuesta = debinWSBO.eliminarDebin();
        
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
    }
    
    @Test
    public void rechazarDebinOK() throws DAOException{
        Response responseWSConfirmacionDebitoV3 = new Response();
   
        responseWSConfirmacionDebitoV3.setEstado("OK");
        
        Error error = new Error();
        error.setCodigo("00");
        error.setMensaje("Ok");
        responseWSConfirmacionDebitoV3.setError(error);
        
        when(sesionParametros.getDetalleDebin()).thenReturn(DebinWSMock.obtenerDetalleOutBO());
        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(debinWSDAO.confirmacionDebitoV3(Matchers.any(RequestConfirmacionDebitoV3.class))).thenReturn(responseWSConfirmacionDebitoV3);
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        
        Respuesta<RechazarDebinWSOutDTO> respuesta = debinWSBO.rechazarDebin();
        
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }
    
    @Test
    public void rechazarDebinErrorEstado() throws DAOException{
        Response responseWSConfirmacionDebitoV3 = new Response();
   
        responseWSConfirmacionDebitoV3.setEstado("");
        
        Error error = new Error();
        error.setCodigo("00");
        error.setMensaje("Ok");
        responseWSConfirmacionDebitoV3.setError(error);
        
        when(sesionParametros.getDetalleDebin()).thenReturn(DebinWSMock.obtenerDetalleOutBO());
        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(debinWSDAO.confirmacionDebitoV3(Matchers.any(RequestConfirmacionDebitoV3.class))).thenReturn(responseWSConfirmacionDebitoV3);
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        when(sesionParametros.getContador()).thenReturn(new ContadorIntentos(2));
        
        Respuesta<RechazarDebinWSOutDTO> respuesta = debinWSBO.rechazarDebin();
        
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
    }
    
    @Test
    public void rechazarDebinErrorTimeOut() throws DAOException{
        Response responseWSConfirmacionDebitoV3 = new Response();
   
        responseWSConfirmacionDebitoV3.setEstado("");
        
        Error error = new Error();
        error.setCodigo("00");
        error.setMensaje("Ok");
        responseWSConfirmacionDebitoV3.setError(error);
        
        DAOException e = new DAOException();
        e.setErrorCode("099");
        
        when(sesionParametros.getDetalleDebin()).thenReturn(DebinWSMock.obtenerDetalleOutBO());
        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(debinWSDAO.confirmacionDebitoV3(Matchers.any(RequestConfirmacionDebitoV3.class))).thenThrow(e);
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        when(sesionParametros.getContador()).thenReturn(new ContadorIntentos(2));
        
        Respuesta<RechazarDebinWSOutDTO> respuesta = debinWSBO.rechazarDebin();
        
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
    }
    
    @Test
    public void rechazarDebinErrorDAO() throws DAOException{
        when(sesionParametros.getDetalleDebin()).thenReturn(DebinWSMock.obtenerDetalleOutBO());
        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(debinWSDAO.confirmacionDebitoV3(Matchers.any(RequestConfirmacionDebitoV3.class))).thenThrow(new DAOException());
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        when(sesionParametros.getContador()).thenReturn(new ContadorIntentos(2));
        
        Respuesta<RechazarDebinWSOutDTO> respuesta = debinWSBO.rechazarDebin();
        
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
    }

    @Test
    public void pagarDebinOkAdherido() throws DAOException {
        Response responseWSConfirmacionDebitoV3 = new Response();
        responseWSConfirmacionDebitoV3.setEstado("OK");
        
        PagarDebinWSView pagarDebinWSIn = new PagarDebinWSView();
        
        when(debinWSDAO.confirmacionDebitoV3(Matchers.any(RequestConfirmacionDebitoV3.class))).thenReturn(responseWSConfirmacionDebitoV3);
        when(sesionParametros.getDetalleDebin()).thenReturn(DebinWSMock.obtenerDetalleOutBO());
        when(sesionCliente.getCliente()).thenReturn(cliente);
        
        
        Respuesta<PagarDebinWSView> respuesta = debinWSBO.pagarDebin(pagarDebinWSIn);
        
        
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }
    
    @Test
    public void pagarDebinOkNoAdherido() throws DAOException {
        Response responseWSAdhesion = new Response();
        Response responseWSConfirmacionDebitoV3 = new Response();
           
        responseWSConfirmacionDebitoV3.setEstado("OK");
        
        Error error = new Error();
        error.setCodigo("00");
        error.setMensaje("Ok");
        responseWSConfirmacionDebitoV3.setError(error);
        responseWSAdhesion.setEstado("OK");
        responseWSAdhesion.setError(error);
        
        ConsultaDetalleDebinWSOutDTO consultaDebin = DebinWSMock.obtenerDetalleOutBO();
        consultaDebin.setEstado("10");
        
        
        
        PagarDebinWSView pagarDebinWSIn = new PagarDebinWSView();
        
        when(debinWSDAO.adhesionComprador(Matchers.any(RequestAdhesion.class))).thenReturn(responseWSAdhesion);
        when(debinWSDAO.confirmacionDebitoV3(Matchers.any(RequestConfirmacionDebitoV3.class))).thenReturn(responseWSConfirmacionDebitoV3);
        when(sesionParametros.getDetalleDebin()).thenReturn(consultaDebin);
        when(sesionCliente.getCliente()).thenReturn(cliente);
        
        
        Respuesta<PagarDebinWSView> respuesta = debinWSBO.pagarDebin(pagarDebinWSIn);
        
        
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }
    
    @Test
    public void pagarDebinErrorConfirmacionDebitoTimeOut() throws DAOException {
        DAOException e = new DAOException();
        e.setErrorCode("099");
        
        PagarDebinWSView pagarDebinWSIn = new PagarDebinWSView();
        
        when(debinWSDAO.confirmacionDebitoV3(Matchers.any(RequestConfirmacionDebitoV3.class))).thenThrow(e);
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        when(sesionParametros.getDetalleDebin()).thenReturn(DebinWSMock.obtenerDetalleOutBO());
        when(sesionCliente.getCliente()).thenReturn(cliente);
        
        Respuesta<PagarDebinWSView> respuesta = debinWSBO.pagarDebin(pagarDebinWSIn);
        
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
    }
    
    @Test
    public void pagarDebinErrorConfirmacionDebito() throws DAOException {
        PagarDebinWSView pagarDebinWSIn = new PagarDebinWSView();
        
        when(debinWSDAO.confirmacionDebitoV3(Matchers.any(RequestConfirmacionDebitoV3.class))).thenThrow( new DAOException());
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        when(sesionParametros.getDetalleDebin()).thenReturn(DebinWSMock.obtenerDetalleOutBO());
        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(sesionParametros.getContador()).thenReturn(new ContadorIntentos(2));
        
        Respuesta<PagarDebinWSView> respuesta = debinWSBO.pagarDebin(pagarDebinWSIn);
        
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
    }
    
    @Test
    public void pagarDebinErrorCodigoNoOk() throws DAOException {
        Response responseWSConfirmacionDebitoV3 = new Response();
        responseWSConfirmacionDebitoV3.setEstado("ERROR");

        PagarDebinWSView pagarDebinWSIn = new PagarDebinWSView();
        
        when(debinWSDAO.confirmacionDebitoV3(Matchers.any(RequestConfirmacionDebitoV3.class))).thenReturn(responseWSConfirmacionDebitoV3);
        when(sesionParametros.getDetalleDebin()).thenReturn(DebinWSMock.obtenerDetalleOutBO());
        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(sesionParametros.getContador()).thenReturn(new ContadorIntentos(2));
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        
        Respuesta<PagarDebinWSView> respuesta = debinWSBO.pagarDebin(pagarDebinWSIn);
        
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
    }
    
    @Test
    public void pagarDebinErrorAdhesionTimeOut() throws DAOException {        
        DAOException e = new DAOException();
        e.setErrorCode("099");
        ConsultaDetalleDebinWSOutDTO consultaDebin = DebinWSMock.obtenerDetalleOutBO();
        consultaDebin.setEstado("10");
            
        PagarDebinWSView pagarDebinWSIn = new PagarDebinWSView();
        
        when(debinWSDAO.adhesionComprador(Matchers.any(RequestAdhesion.class))).thenThrow(e);
        when(sesionParametros.getDetalleDebin()).thenReturn(consultaDebin);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        when(sesionCliente.getCliente()).thenReturn(cliente);
        
        Respuesta<PagarDebinWSView> respuesta = debinWSBO.pagarDebin(pagarDebinWSIn);
        
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
    }
    
    @Test
    public void pagarDebinErrorAdhesionEstado() throws DAOException {
        Response responseWSAdhesion = new Response();
        responseWSAdhesion.setEstado("ERROR");
        
        ConsultaDetalleDebinWSOutDTO consultaDebin = DebinWSMock.obtenerDetalleOutBO();
        consultaDebin.setEstado("10");
        PagarDebinWSView pagarDebinWSIn = new PagarDebinWSView();
        
        when(debinWSDAO.adhesionComprador(Matchers.any(RequestAdhesion.class))).thenReturn(responseWSAdhesion);
        when(sesionParametros.getDetalleDebin()).thenReturn(consultaDebin);
        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(sesionParametros.getContador()).thenReturn(new ContadorIntentos(2));
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        
        Respuesta<PagarDebinWSView> respuesta = debinWSBO.pagarDebin(pagarDebinWSIn);
        
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
    }
    
    @Test
    public void pagarDebinErrorAdhesion() throws DAOException {
        ConsultaDetalleDebinWSOutDTO consultaDebin = DebinWSMock.obtenerDetalleOutBO();
        consultaDebin.setEstado("10");
            
        PagarDebinWSView pagarDebinWSIn = new PagarDebinWSView();
        
        when(debinWSDAO.adhesionComprador(Matchers.any(RequestAdhesion.class))).thenThrow(new DAOException());
        when(sesionParametros.getDetalleDebin()).thenReturn(consultaDebin);
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(sesionParametros.getContador()).thenReturn(new ContadorIntentos(2));
                
        Respuesta<PagarDebinWSView> respuesta = debinWSBO.pagarDebin(pagarDebinWSIn);
        
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
    }

}
