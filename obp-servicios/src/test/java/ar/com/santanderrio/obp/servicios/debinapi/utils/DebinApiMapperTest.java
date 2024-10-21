package ar.com.santanderrio.obp.servicios.debinapi.utils;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.generated.webservices.debin.CompradorDebinDTO;
import ar.com.santanderrio.obp.generated.webservices.debin.CuentaDebinDTO;
import ar.com.santanderrio.obp.generated.webservices.debin.ResponseListaDebin;
import ar.com.santanderrio.obp.generated.webservices.debin.ResumenDebinDTO;
import ar.com.santanderrio.obp.generated.webservices.debin.VendedorDebinDTO;
import ar.com.santanderrio.obp.servicios.debinapi.dto.*;
import ar.com.santanderrio.obp.servicios.debinapi.mock.DebinApiMock;
import ar.com.santanderrio.obp.servicios.debinws.bo.impl.DebinWSBOImpl;
import ar.com.santanderrio.obp.servicios.debinws.common.EstadoDebinEnum;
import ar.com.santanderrio.obp.servicios.debinws.dto.ConsultaDebinWSInDTO;
import ar.com.santanderrio.obp.servicios.debinws.dto.EstadoDebinWSBusquedaDTO;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

@RunWith(MockitoJUnitRunner.class)
public class DebinApiMapperTest {

    @InjectMocks
    private DebinApiMapper debinApiMapper = new DebinApiMapper();


    @Before
    public void init() {
        ReflectionTestUtils.setField(debinApiMapper, "pageSize", 8);
        ReflectionTestUtils.setField(debinApiMapper, "strConcepts", "01-Alquileres-1-ALQ|02-Cuotas-2-CUO|03-Expensas-3-EXP|04-Facturas-4-FAC|05-Préstamos-5-PRE|06-Seguros-6-SEG|07-Honorarios-7-HON|08-Haberes-8-HAB|09-Varios-9-VAR|0M-Plazo Fijo Web-M-PLF");
        ReflectionTestUtils.setField(debinApiMapper, "emptyListCode", "85");
        ReflectionTestUtils.setField(debinApiMapper, "okListCode", "00");
        ReflectionTestUtils.setField(debinApiMapper, "noMoreItems", 99);
        ReflectionTestUtils.setField(debinApiMapper, "pageSizeRecurrentDebin", 5000);

    }
    @Test
    public void getPaymentProcessorConceptCodeOK(){
        String paymentProcessorConceptCode =  debinApiMapper.getPaymentProcessorConceptCode("VAR" );
        Assert.assertEquals("09", paymentProcessorConceptCode);
    }


    @Test
    public void getPaymentConceptCodeEmptyResult(){
        ReflectionTestUtils.setField(debinApiMapper, "strConcepts", "");

        String paymentProcessorConceptCode =  debinApiMapper.getPaymentProcessorConceptCode("VAR" );
        Assert.assertEquals("", paymentProcessorConceptCode);
    }

    @Test
    public void toResponseListaDebinOK() throws DatatypeConfigurationException{
        //PREPARE
        DebinListDTO debinListDTO = new DebinListDTO();
        debinListDTO.setTotalPages(2);
        List<DebinDetailDTO> debinDetailDTOS = new ArrayList<DebinDetailDTO>();
        debinDetailDTOS.add(DebinApiMock.getDefaultDetailDTO());
        debinListDTO.setDebins(debinDetailDTOS);

        //CALL METHOD
        ResponseListaDebin responseListaDebin = debinApiMapper.toDTO(debinListDTO, 1);

        //ASSERTS
        Assert.assertEquals(1, responseListaDebin.getDebines().size());
        Assert.assertEquals("00",responseListaDebin.getError().getCodigo());
        Assert.assertNull(responseListaDebin.getError().getMensaje());
        assertResponseDTOOK(debinListDTO.getDebins().get(0), responseListaDebin.getDebines().get(0));
        Assert.assertEquals(2, responseListaDebin.getSiguientePagina().intValue());

    }

    @Test
    public void toResponseListaDebinOKWithNullDates() throws DatatypeConfigurationException{
        //PREPARE
        DebinListDTO debinListDTO = new DebinListDTO();
        debinListDTO.setTotalPages(2);
        List<DebinDetailDTO> debinDetailDTOS = new ArrayList<DebinDetailDTO>();
        debinDetailDTOS.add(DebinApiMock.getDefaultDetailDTOWithNullDates());
        debinListDTO.setDebins(debinDetailDTOS);

        //CALL METHOD
        ResponseListaDebin responseListaDebin = debinApiMapper.toDTO(debinListDTO, 1);

        //ASSERTS
        Assert.assertEquals(1, responseListaDebin.getDebines().size());
        Assert.assertEquals("00",responseListaDebin.getError().getCodigo());
        Assert.assertNull(responseListaDebin.getError().getMensaje());
        assertResponseDTOOK(debinListDTO.getDebins().get(0), responseListaDebin.getDebines().get(0));
        Assert.assertEquals(2, responseListaDebin.getSiguientePagina().intValue());

    }

    @Test
    public void toResponseListaDebinEmptyList() throws DatatypeConfigurationException{
        //PREPARE
        DebinListDTO debinListDTO = new DebinListDTO();
        debinListDTO.setTotalPages(0);
        List<DebinDetailDTO> debinDetailDTOS = new ArrayList<DebinDetailDTO>();
        debinListDTO.setDebins(debinDetailDTOS);

        //CALL METHOD
        ResponseListaDebin responseListaDebin = debinApiMapper.toDTO(debinListDTO, 1);

        //ASSERTS
        Assert.assertEquals(0, responseListaDebin.getDebines().size());
        Assert.assertEquals("85",responseListaDebin.getError().getCodigo());
        Assert.assertNull(responseListaDebin.getError().getMensaje());
        Assert.assertEquals(99, responseListaDebin.getSiguientePagina().intValue());
    }

    @Test
    public void toDebinListRequestBuyerModeOK() throws  DAOException{
        //PREPARE

        ConsultaDebinWSInDTO requestPaymentProcessor = new ConsultaDebinWSInDTO();

        EstadoDebinWSBusquedaDTO statusSearchPaymentProcessor = new EstadoDebinWSBusquedaDTO();
        statusSearchPaymentProcessor.setNroPagina(1);
        statusSearchPaymentProcessor.setEstado(EstadoDebinEnum.ACREDITADO);
        requestPaymentProcessor.setPrimerEstadoBusqueda(statusSearchPaymentProcessor);
        requestPaymentProcessor.setCanal("E");
        requestPaymentProcessor.setNroDocumento("00021587183");
        requestPaymentProcessor.setTipoDocumento("N");
        GregorianCalendar cal1 = new GregorianCalendar(2018, 3, 12);
        cal1.setTimeZone(TimeZone.getTimeZone("GMT-3:00"));
        requestPaymentProcessor.setFechaDesde(cal1.getTime());
        GregorianCalendar cal2 = new GregorianCalendar(2018, 3, 19);
        cal2.setTimeZone(TimeZone.getTimeZone("GMT-3:00"));
        requestPaymentProcessor.setFechaHasta(cal2.getTime());
        requestPaymentProcessor.setConsultaComprador(true);
        requestPaymentProcessor.setTipo("debin");
        String clientCuit ="23302005044";
        String clientIp="180.250.241.61";

        //CALL METHOD
        DebinListRequest debinListRequest = debinApiMapper.toDebinListRequest(requestPaymentProcessor,
                statusSearchPaymentProcessor,
                clientCuit,
                clientIp);

        //ASSERTS
        Assert.assertNotNull(debinListRequest);
        Assert.assertEquals("8", debinListRequest.getPageSize());

        Assert.assertEquals("1",debinListRequest.getPageNumber());
        Assert.assertEquals(DebinStatus.CREDITED, debinListRequest.getStatus());
        Assert.assertEquals(clientCuit, debinListRequest.getBuyerCuit());
        Assert.assertEquals(DebinListMode.BUYER, debinListRequest.getMode());
        Assert.assertNull(debinListRequest.getSellerCuit());
        Assert.assertEquals("2018-04-12T03:00:00.000Z",debinListRequest.getCreationFrom());
        Assert.assertEquals("2018-04-19T03:00:00.000Z",debinListRequest.getCreationTo());
        Assert.assertEquals(DebinType.SPOT,debinListRequest.getTypes().get(0));
        Assert.assertEquals(DebinType.RECURRENT,debinListRequest.getTypes().get(1));
        Assert.assertEquals(clientIp, debinListRequest.getClientIp());

    }

    @Test
    public void toDebinListRequestRecurrentDebinOK() throws  DAOException{
        //PREPARE

        ConsultaDebinWSInDTO requestPaymentProcessor = new ConsultaDebinWSInDTO();

        EstadoDebinWSBusquedaDTO statusSearchPaymentProcessor = new EstadoDebinWSBusquedaDTO();
        statusSearchPaymentProcessor.setNroPagina(1);
        statusSearchPaymentProcessor.setEstado(EstadoDebinEnum.ACREDITADO);
        requestPaymentProcessor.setPrimerEstadoBusqueda(statusSearchPaymentProcessor);
        requestPaymentProcessor.setCanal("E");
        requestPaymentProcessor.setNroDocumento("00021587183");
        requestPaymentProcessor.setTipoDocumento("N");
        GregorianCalendar cal1 = new GregorianCalendar(2018, 3, 12);
        cal1.setTimeZone(TimeZone.getTimeZone("GMT-3:00"));
        requestPaymentProcessor.setFechaDesde(cal1.getTime());
        GregorianCalendar cal2 = new GregorianCalendar(2018, 3, 19);
        cal2.setTimeZone(TimeZone.getTimeZone("GMT-3:00"));
        requestPaymentProcessor.setFechaHasta(cal2.getTime());
        requestPaymentProcessor.setConsultaComprador(true);
        requestPaymentProcessor.setTipo("recurrente");
        requestPaymentProcessor.setIdRecurrencia("23670");
        String clientCuit ="23302005044";
        String clientIp="180.250.241.61";


        //CALL METHOD
        DebinListRequest debinListRequest = debinApiMapper.toDebinListRequest(requestPaymentProcessor,
                statusSearchPaymentProcessor,
                clientCuit,
                clientIp);

        //ASSERTS
        Assert.assertNotNull(debinListRequest);
        Assert.assertEquals("1", debinListRequest.getPageNumber());
        Assert.assertEquals("5000", debinListRequest.getPageSize());
        Assert.assertEquals(DebinStatus.CREDITED, debinListRequest.getStatus());
        Assert.assertEquals(clientCuit, debinListRequest.getBuyerCuit());
        Assert.assertEquals(DebinListMode.BUYER, debinListRequest.getMode());
        Assert.assertNull(debinListRequest.getSellerCuit());
        Assert.assertEquals("2018-04-12T03:00:00.000Z",debinListRequest.getCreationFrom());
        Assert.assertEquals("2018-04-19T03:00:00.000Z",debinListRequest.getCreationTo());
        Assert.assertEquals(DebinType.RECURRENT,debinListRequest.getTypes().get(0));
        Assert.assertEquals(clientIp, debinListRequest.getClientIp());

    }

    @Test
    public void toDebinListRequestSellerModeOK() throws  DAOException{
        //PREPARE

        ConsultaDebinWSInDTO requestPaymentProcessor = new ConsultaDebinWSInDTO();

        EstadoDebinWSBusquedaDTO statusSearchPaymentProcessor = new EstadoDebinWSBusquedaDTO();
        statusSearchPaymentProcessor.setNroPagina(1);
        statusSearchPaymentProcessor.setEstado(EstadoDebinEnum.ACREDITADO);
        requestPaymentProcessor.setPrimerEstadoBusqueda(statusSearchPaymentProcessor);
        requestPaymentProcessor.setCanal("E");
        requestPaymentProcessor.setNroDocumento("00021587183");
        requestPaymentProcessor.setTipoDocumento("N");
        GregorianCalendar cal1 = new GregorianCalendar(2018, 3, 12);
        cal1.setTimeZone(TimeZone.getTimeZone("GMT-3:00"));
        requestPaymentProcessor.setFechaDesde(cal1.getTime());
        GregorianCalendar cal2 = new GregorianCalendar(2018, 3, 19);
        cal2.setTimeZone(TimeZone.getTimeZone("GMT-3:00"));
        requestPaymentProcessor.setFechaHasta(cal2.getTime());
        requestPaymentProcessor.setConsultaComprador(false);
        requestPaymentProcessor.setTipo("debin");
        String clientCuit ="23302005044";
        String clientIp="180.250.241.61";

        //CALL METHOD
        DebinListRequest debinListRequest = debinApiMapper.toDebinListRequest(requestPaymentProcessor,
                statusSearchPaymentProcessor,
                clientCuit,
                clientIp);

        //ASSERTS
        Assert.assertNotNull(debinListRequest);
        Assert.assertEquals("8", debinListRequest.getPageSize());

        Assert.assertEquals("1",debinListRequest.getPageNumber());
        Assert.assertEquals(DebinStatus.CREDITED, debinListRequest.getStatus());
        Assert.assertEquals(clientCuit, debinListRequest.getSellerCuit());
        Assert.assertEquals(DebinListMode.SELLER, debinListRequest.getMode());
        Assert.assertNull(debinListRequest.getBuyerCuit());
        Assert.assertEquals("2018-04-12T03:00:00.000Z",debinListRequest.getCreationFrom());
        Assert.assertEquals("2018-04-19T03:00:00.000Z",debinListRequest.getCreationTo());
        Assert.assertEquals(DebinType.SPOT,debinListRequest.getTypes().get(0));
        Assert.assertEquals(DebinType.RECURRENT,debinListRequest.getTypes().get(1));
        Assert.assertEquals(clientIp, debinListRequest.getClientIp());


    }


    @Test
    public void findGenericErrorEmpty(){
        DAOException daoException = new DAOException("error", "CO-0798");
        String genericError  = debinApiMapper.findGenericError(daoException, DebinWSBOImpl.codigosErrorGenerico);
        Assert.assertEquals("", genericError);
    }

    @Test
    public void findGenericErrorOK(){
        DAOException daoException = new DAOException("error", "CO-0763");
        String genericError  = debinApiMapper.findGenericError(daoException, DebinWSBOImpl.codigosErrorGenerico);
        Assert.assertEquals("63", genericError);
    }

    @Test
    public void findGenericErrorOKWithOneDigit(){
        DAOException daoException = new DAOException("error", "CO-0706");
        String genericError  = debinApiMapper.findGenericError(daoException, DebinWSBOImpl.codigosErrorGenerico);
        Assert.assertEquals("6", genericError);
    }

    @Test
    public void findGenericErrorOKWithOneDigitEmpty(){
        DAOException daoException = new DAOException("error", "CO-0776");
        String genericError  = debinApiMapper.findGenericError(daoException, DebinWSBOImpl.codigosErrorGenerico);
        Assert.assertEquals("", genericError);
    }

    private void assertAccountDTOOK(DebinAccountDTO debinAccountDTO , CuentaDebinDTO accountDTO ){

        Assert.assertEquals(debinAccountDTO.getAlias(), accountDTO.getAlias());
        Assert.assertEquals(debinAccountDTO.getBank(), accountDTO.getBanco());
        Assert.assertEquals(debinAccountDTO.getCbu(), accountDTO.getCbu());
        Assert.assertEquals(debinAccountDTO.getCurrency().toString(), accountDTO.getMoneda());
        Assert.assertEquals(debinAccountDTO.getType(), accountDTO.getTipo());
        Assert.assertEquals(debinAccountDTO.getBranch(), accountDTO.getSucursal());
        Assert.assertEquals(debinAccountDTO.getTerminal(), accountDTO.getTerminal());
        Assert.assertEquals(debinAccountDTO.getEndpoint(), accountDTO.getEndpointId());
        Assert.assertEquals(debinAccountDTO.isOwner(), accountDTO.isEsTitular());

    }

    private void assertBuyerDTOOK(DebinBuyerDTO debinBuyerDTO , CompradorDebinDTO buyerDTO){
        Assert.assertEquals(debinBuyerDTO.getFancyName(),buyerDTO.getTitular());
        Assert.assertEquals(debinBuyerDTO.getCuit(), buyerDTO.getCuit());
        Assert.assertNotNull(buyerDTO.getCuenta());
        assertAccountDTOOK(debinBuyerDTO.getDebinAccountDTO(),buyerDTO.getCuenta());
    }

    private void assertSellerDTOOK(  DebinSellerDTO debinSellerDTO , VendedorDebinDTO sellerDTO  ){

        Assert.assertEquals(debinSellerDTO.getFancyName(), sellerDTO.getTitular());
        Assert.assertEquals(debinSellerDTO.getCuit(), sellerDTO.getCuit());
        Assert.assertEquals(debinSellerDTO.getTerminal(), sellerDTO.getTerminal());
        Assert.assertNotNull(sellerDTO.getCuenta());
        assertAccountDTOOK(debinSellerDTO.getDebinAccountDTO(),sellerDTO.getCuenta());

    }



    private void assertResponseDTOOK(DebinDetailDTO debinDetailDTO, ResumenDebinDTO resumenDebinDTO) throws DatatypeConfigurationException  {

        Assert.assertNotNull(resumenDebinDTO.getComprador());
        assertBuyerDTOOK(debinDetailDTO.getDebinBuyerDTO(), resumenDebinDTO.getComprador());

        Assert.assertNotNull(resumenDebinDTO.getVendedor());
        assertSellerDTOOK(debinDetailDTO.getDebinSellerDTO(), resumenDebinDTO.getVendedor());

        Assert.assertEquals(debinDetailDTO.getCurrency(), CurrencyType.valueOf(resumenDebinDTO.getMoneda()));


        String paymentProcessorStatusDescription = DebinStatus.valueOf(debinDetailDTO.getDebinStatusDTO().getCode()).getPaymentProcessorStatus();
        EstadoDebinEnum estadoDebinEnum = EstadoDebinEnum.obtenerEstadoPorDescripcion(paymentProcessorStatusDescription);

        Assert.assertEquals(estadoDebinEnum.getId(), resumenDebinDTO.getEstadoDebin().getCodigo());
        Assert.assertEquals(estadoDebinEnum.getDescView(), resumenDebinDTO.getEstadoDebin().getDescripcion());

        Assert.assertEquals(debinDetailDTO.getId(), resumenDebinDTO.getId());
        Assert.assertEquals(debinDetailDTO.getAmount().toString(), resumenDebinDTO.getImporte());
        Assert.assertEquals(debinDetailDTO.getCurrency(), CurrencyType.valueOf(resumenDebinDTO.getMoneda()));
        Assert.assertEquals(debinDetailDTO.getDebinType().getPaymentProcessorType(), resumenDebinDTO.getTipo());
        Assert.assertEquals(debinApiMapper.getPaymentProcessorConceptCode(debinDetailDTO.getConcept().toString()), resumenDebinDTO.getConcepto());

        if (resumenDebinDTO.getVencimiento() == null) {
            Assert.assertNull(resumenDebinDTO.getVencimiento());
        } else {
            Assert.assertEquals(calcGregDate(debinDetailDTO.getExpirationDate()), resumenDebinDTO.getVencimiento());
        }

        if (resumenDebinDTO.getFechaCreacion() == null) {
            Assert.assertNull(resumenDebinDTO.getFechaCreacion());
        } else {
            Assert.assertEquals(calcGregDate(debinDetailDTO.getCreationDate()), resumenDebinDTO.getFechaCreacion());
        }

        Assert.assertEquals(debinDetailDTO.getRefund(),resumenDebinDTO.isDevolucion());
        Assert.assertEquals(debinDetailDTO.getAssociatedOperationId(), resumenDebinDTO.getIdOperacionOriginal());
    }

    private XMLGregorianCalendar calcGregDate(String strDate) throws DatatypeConfigurationException{
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(DateTime.parse(strDate).toDate());
        return  DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar);

    }

}
