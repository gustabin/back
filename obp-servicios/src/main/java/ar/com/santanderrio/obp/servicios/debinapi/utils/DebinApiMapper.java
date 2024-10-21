package ar.com.santanderrio.obp.servicios.debinapi.utils;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.generated.webservices.debin.CompradorDebinDTO;
import ar.com.santanderrio.obp.generated.webservices.debin.CuentaDebinDTO;
import ar.com.santanderrio.obp.generated.webservices.debin.Error;
import ar.com.santanderrio.obp.generated.webservices.debin.EstadoDebinDTO;
import ar.com.santanderrio.obp.generated.webservices.debin.ResponseListaDebin;
import ar.com.santanderrio.obp.generated.webservices.debin.ResumenDebinDTO;
import ar.com.santanderrio.obp.generated.webservices.debin.VendedorDebinDTO;
import ar.com.santanderrio.obp.servicios.debinapi.dao.DebinApiConfig;
import ar.com.santanderrio.obp.servicios.debinapi.dto.DebinAccountDTO;
import ar.com.santanderrio.obp.servicios.debinapi.dto.DebinBuyerDTO;
import ar.com.santanderrio.obp.servicios.debinapi.dto.DebinDetailDTO;
import ar.com.santanderrio.obp.servicios.debinapi.dto.DebinListDTO;
import ar.com.santanderrio.obp.servicios.debinapi.dto.DebinListMode;
import ar.com.santanderrio.obp.servicios.debinapi.dto.DebinListRequest;
import ar.com.santanderrio.obp.servicios.debinapi.dto.DebinSellerDTO;
import ar.com.santanderrio.obp.servicios.debinapi.dto.DebinStatus;
import ar.com.santanderrio.obp.servicios.debinapi.dto.DebinType;
import ar.com.santanderrio.obp.servicios.debinws.common.EstadoDebinEnum;
import ar.com.santanderrio.obp.servicios.debinws.dto.ConsultaDebinWSInDTO;
import ar.com.santanderrio.obp.servicios.debinws.dto.EstadoDebinWSBusquedaDTO;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

/** It converts data from debinAPi to paymentProcessor and
 *  from paymentProcessor to debinApi
 * */
@Component
public class DebinApiMapper {
    protected static final Logger LOGGER = LoggerFactory.getLogger(DebinApiMapper.class);

    @Value("${"+ DebinApiConfig.DEFAULT_PAGE_SIZE + ":8}")
    private int pageSize;

    @Value("${"+ DebinApiConfig.DEFAULT_PAGE_SIZE_RECURRENT_DEBIN + ":5000}")
    private int pageSizeRecurrentDebin;

    @Value("${DEBINWS.CONCEPTOS}")
    private String strConcepts;

    @Value("${"+ DebinApiConfig.EMPTY_LIST_CODE_EQUIVALENT + ":85}")
    private String emptyListCode;

    @Value("${"+ DebinApiConfig.OK_CODE_EQUIVALENT + ":00}")
    private String okListCode;

    @Value("${"+ DebinApiConfig.NO_MORE_ITEMS + ":99}")
    private Integer noMoreItems;

    public ResponseListaDebin toDTO(DebinListDTO debinListDTO, int pageNumber)  throws DatatypeConfigurationException{
        ResponseListaDebin response = new ResponseListaDebin();
        response.setEstado("");
        Error error = new Error();

        Integer nextPage = ( debinListDTO.getTotalPages() > pageNumber )? pageNumber +1: noMoreItems;
        response.setSiguientePagina(nextPage);
        if(debinListDTO.getDebins() == null || debinListDTO.getDebins().size() == 0){
            error.setCodigo(emptyListCode);
        }else {
            error.setCodigo(okListCode);
            List<ResumenDebinDTO> debines = new ArrayList<ResumenDebinDTO>();
            for (DebinDetailDTO debin : debinListDTO.getDebins()) {
                debines.add(toDTO(debin));

            }
            response.setDebines(debines);
        }
        response.setError(error);

        return  response;
    }

    public DebinListRequest toDebinListRequest(ConsultaDebinWSInDTO consultaDebinInDTO,
                                               EstadoDebinWSBusquedaDTO estadoBusqueda,
                                               String clientCuit, String clientIp) throws  DAOException  {
        LOGGER.info("DebinWSBOImpl generarRequestListaDebin DebinAPI");

        DebinListRequest debinListRequest = new DebinListRequest();
        debinListRequest.setClientIp(clientIp);
        List<DebinType> debinTypes = new ArrayList<DebinType>();
        if ("debin".equalsIgnoreCase(consultaDebinInDTO.getTipo())){
            debinTypes.add(DebinType.SPOT);
            debinTypes.add(DebinType.RECURRENT);
        }else {
            debinTypes.add(DebinType.fromPaymentProcessorType(consultaDebinInDTO.getTipo()));
        }
        debinListRequest.setTypes(debinTypes);

        if(DebinType.RECURRENT.equals(DebinType.fromPaymentProcessorType(consultaDebinInDTO.getTipo())) &&
                consultaDebinInDTO.getIdRecurrencia() != null){

            debinListRequest.setRecurrenceId(consultaDebinInDTO.getIdRecurrencia());
            debinListRequest.setMode(DebinListMode.BUYER);
            debinListRequest.setBuyerCuit(clientCuit);
            debinListRequest.setPageSize(String.valueOf(pageSizeRecurrentDebin));
            debinListRequest.setPageNumber("1");

        }
        else{

            if (consultaDebinInDTO.getConsultaComprador()) {
                debinListRequest.setMode(DebinListMode.BUYER);
                debinListRequest.setBuyerCuit(clientCuit);
            } else {
                debinListRequest.setMode(DebinListMode.SELLER);
                debinListRequest.setSellerCuit(clientCuit);
            }

            debinListRequest.setPageNumber(String.valueOf(estadoBusqueda.getNroPagina()));
            debinListRequest.setPageSize(String.valueOf(pageSize));
        }

        debinListRequest.setStatus(DebinStatus.fromPaymentProcessorStatus(estadoBusqueda.getEstado().getDescripcion()));

        DateTime dateFrom = new DateTime(consultaDebinInDTO.getFechaDesde(), DateTimeZone.UTC);
        debinListRequest.setCreationFrom(dateFrom.toString());

        DateTime dateTo = new DateTime(consultaDebinInDTO.getFechaHasta(), DateTimeZone.UTC);
        debinListRequest.setCreationTo(dateTo.toString());

        LOGGER.info("DebinWSBOImpl generarRequestListaDebin OK");

        return debinListRequest;
    }


    public String findGenericError(DAOException daoException, String[] genericError){
        String genericErrorFound = "";
        if (StringUtils.isNotBlank(daoException.getErrorCode())){

            for( int i= 0; i<genericError.length && StringUtils.isBlank(genericErrorFound); ++i){
                boolean withOneDigit = genericError[i].length() == 1;

                String errorCode = daoException.getErrorCode();
                String errorFound = errorCode.length()>2? errorCode.substring(errorCode.length()-2):"";
                if (withOneDigit) {
                    genericErrorFound = ('0' + genericError[i]).equals(errorFound)? genericError[i]:"";
                }else {
                    genericErrorFound = genericError[i].equals(errorFound)? errorFound:"";
                }
            }

        }
        return  genericErrorFound;
    }


    private ResumenDebinDTO toDTO(DebinDetailDTO debin) throws DatatypeConfigurationException{
        ResumenDebinDTO resumenDebinDTO = new ResumenDebinDTO();
        resumenDebinDTO.setComprador(toDTO(debin.getDebinBuyerDTO()));

        resumenDebinDTO.setVendedor(toDTO(debin.getDebinSellerDTO()));

        resumenDebinDTO.setMoneda(debin.getCurrency().toString());

        String paymentProcessorStatusDescription = DebinStatus.valueOf(debin.getDebinStatusDTO().getCode()).getPaymentProcessorStatus();
        EstadoDebinEnum estadoDebinEnum = EstadoDebinEnum.obtenerEstadoPorDescripcion(paymentProcessorStatusDescription);
        EstadoDebinDTO estadoDebinDTO = new EstadoDebinDTO();
        estadoDebinDTO.setCodigo(estadoDebinEnum.getId());
        estadoDebinDTO.setDescripcion(estadoDebinEnum.getDescView());
        resumenDebinDTO.setEstadoDebin(estadoDebinDTO);

        resumenDebinDTO.setId(debin.getId());
        resumenDebinDTO.setImporte(String.valueOf(debin.getAmount()));

        resumenDebinDTO.setTipo(debin.getDebinType().getPaymentProcessorType());
        if (debin.getConcept() != null) {
            resumenDebinDTO.setConcepto(getPaymentProcessorConceptCode(debin.getConcept().toString()));
        }

        if(debin.getExpirationDate() != null){
            GregorianCalendar c = new GregorianCalendar();
            c.setTime(DateTime.parse(debin.getExpirationDate()).toDate());
            XMLGregorianCalendar expirationDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);

            resumenDebinDTO.setVencimiento(expirationDate);
        }

        if(debin.getCreationDate() != null){
            GregorianCalendar gregorianCalendar = new GregorianCalendar();
            gregorianCalendar.setTime(DateTime.parse(debin.getCreationDate()).toDate());
            XMLGregorianCalendar creationDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar);

            resumenDebinDTO.setFechaCreacion(creationDate);
        }

        resumenDebinDTO.setDevolucion(debin.getRefund());
        resumenDebinDTO.setIdOperacionOriginal(debin.getAssociatedOperationId());
        return resumenDebinDTO;
    }

    private static VendedorDebinDTO toDTO(DebinSellerDTO debinSellerDTO) {
        CuentaDebinDTO accountDTO = toDTO(debinSellerDTO.getDebinAccountDTO());

        VendedorDebinDTO vendedorDebinDTO = new VendedorDebinDTO();
        vendedorDebinDTO.setCodigo("");
        vendedorDebinDTO.setTitular(debinSellerDTO.getFancyName());
        vendedorDebinDTO.setCuit(debinSellerDTO.getCuit());
        vendedorDebinDTO.setTerminal(debinSellerDTO.getTerminal());
        vendedorDebinDTO.setCuenta(accountDTO);

        return vendedorDebinDTO;
    }

    private static CompradorDebinDTO toDTO(DebinBuyerDTO debinBuyerDTO){
        CuentaDebinDTO accountDTO = toDTO(debinBuyerDTO.getDebinAccountDTO());

        CompradorDebinDTO compradorDebinDTO = new CompradorDebinDTO();
        compradorDebinDTO.setCodigo("");
        compradorDebinDTO.setTitular(debinBuyerDTO.getFancyName());
        compradorDebinDTO.setCuit(debinBuyerDTO.getCuit());
        compradorDebinDTO.setDescripcion(debinBuyerDTO.getFancyName());
        compradorDebinDTO.setEstadoComprador(null);
        compradorDebinDTO.setCuenta(accountDTO);
        return compradorDebinDTO;
    }

    private static CuentaDebinDTO toDTO( DebinAccountDTO accountDebin){
        CuentaDebinDTO cuentaDebinDTO = new CuentaDebinDTO();
        cuentaDebinDTO.setAlias(accountDebin.getAlias());
        cuentaDebinDTO.setBanco(accountDebin.getBank());
        cuentaDebinDTO.setCbu(accountDebin.getCbu());
        cuentaDebinDTO.setEsTitular(false);
        if (accountDebin.getCurrency() !=null){
            cuentaDebinDTO.setMoneda(accountDebin.getCurrency().toString());
        }
        cuentaDebinDTO.setTipo(accountDebin.getType());
        cuentaDebinDTO.setNumero("");
        cuentaDebinDTO.setSucursal(accountDebin.getBranch());
        cuentaDebinDTO.setTerminal(accountDebin.getTerminal());
        cuentaDebinDTO.setAlta(null);
        cuentaDebinDTO.setEndpointId(accountDebin.getEndpoint());
        cuentaDebinDTO.setEsTitular(accountDebin.isOwner());

        return cuentaDebinDTO;
    }

    /**
     * It returns a payment processor code from a debin-api concept
     *
     * */
    public String getPaymentProcessorConceptCode(String concept) {
        String paymentProcessorConceptCode = "";
        String [] conceptsData= strConcepts.split("\\|");
        try{
            for  (int  i = 0; i< conceptsData.length && paymentProcessorConceptCode.equals(""); ++i) {
                String[] conceptDetails = conceptsData[i].split("-");
                String possiblePaymentProcessorCode = conceptDetails[0];
                if(concept.equalsIgnoreCase(conceptDetails[3])){
                    paymentProcessorConceptCode = possiblePaymentProcessorCode;
                }
            }

        }catch (Exception e){
            LOGGER.warn("could not find a payment processor code for concept " + concept);
        }
        return paymentProcessorConceptCode;
    }
}
