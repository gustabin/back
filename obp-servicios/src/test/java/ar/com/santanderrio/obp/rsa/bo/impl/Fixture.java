package ar.com.santanderrio.obp.rsa.bo.impl;

import ar.com.santanderrio.obp.base.comun.OperacionesRSAEnum;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.generated.webservices.rsa.*;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.RsaDTO;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.rsa.bo.impl.RsaRequestBuilder;
import ar.com.santanderrio.obp.servicios.rsa.entities.RsaAnalyzeRequestData;
import ar.com.santanderrio.obp.servicios.rsa.entities.RsaAnalyzeResponseData;
import ar.com.santanderrio.obp.servicios.rsa.entities.RsaGenericRequestData;
import ar.com.santanderrio.obp.servicios.rsa.entities.RsaGenericResponseData;

import java.util.Arrays;
import java.util.List;


public class Fixture {

  public static RsaAnalyzeRequestData getRsaAnalyzeRequestData(){
      RsaAnalyzeRequestData requestData = new RsaAnalyzeRequestData();
      requestData.setRsaDTO(getRsaDTO());
      requestData.setRsaGenericRequestData(getRsaGenericRequestData());
      requestData.setOperacion(OperacionesRSAEnum.PAGODESUELDOS);
      return  requestData;
  }

    public static RsaGenericRequestData getRsaGenericRequestData() {
        RsaGenericRequestData data = new RsaGenericRequestData();
        data.setDevicePrint("222DevicePrint");
        data.setId(222L);
        data.setHttpAccept("TRUE");
        data.setDeviceTokenCookie("AAAAAAAE3333555qqÑÑÑDddDDAAA");
        data.setRsaCookie("RSACOOKIE2222222222222333333333333333");
        data.setHttpAcceptChars("true");
        data.setRemoteIp("128.2.3.336");
        data.setHttpReferrer("333333333333333333333333333333333");
        data.setHttpAcceptLanguage("true");
        data.setRequestDeviceTokenFSO("333TOKEN");
        data.setHttpAcceptEncoding("false");

        return  data;
    }

    public static RsaRequestBuilder getRsaRequestBuilder() {
        RsaRequestBuilder requestBuilder = new RsaRequestBuilder() {
            @Override
            public EventData build(RsaDTO operacionDeRiesgo) {
                return getEventData();
            }
        };
        return requestBuilder;
    }

    public static EventData getEventData() {
      EventData eventData = new EventData();
      eventData.setEventDescription("Event Data Desc");
      eventData.setEventReferenceId("4552");
      eventData.setEventType(EventType.PAYMENT);
      eventData.setTransactionData(getTransactionData());
      return eventData;
    }

    public static TransactionData getTransactionData() {
        TransactionData data = new  TransactionData();
        data.setAmount(getAmount());
        return data;
    }

    public static Amount getAmount() {
      Amount amount = new Amount();
      amount.setAmount(1234L);
      return amount;
    }


    public static RsaDTO getRsaDTO(){
        RsaDTO rsa = new RsaDTO(OperacionesRSAEnum.NUEVO_PAGO) {
            @Override
            public String getId() {
                return super.getId();
            }
        };
        return rsa;
    }


    public static AnalyzeResponse2 getRsaAnalyzeRequest() {
        AnalyzeResponse2 response = new AnalyzeResponse2();
        response.setMessageHeader(getMessageHeader());
        response.setIdentificationData(getIdentificationData());
        return response;
    }

    public static AnalyzeResponse2 getRsaAnalyzeResponse1() {
        AnalyzeResponse2 response = new AnalyzeResponse2();
        response.setMessageHeader(getMessageHeader());
        response.setIdentificationData(getIdentificationData());
        response.setRiskResult(getRiskResult());
        return response;
    }

    private static RiskResult getRiskResult() {
        RiskResult riskResult = new RiskResult();
        riskResult.setRiskScore(1);
        riskResult.setRiskScoreBand("1");
        riskResult.setTriggeredRule(getTriggeredRule());
        return riskResult;
    }

    private static TriggeredRule getTriggeredRule() {
        TriggeredRule triggeredRule = new TriggeredRule();
        triggeredRule.setActionCode(ActionCode.DENY);
        triggeredRule.setRuleId("Pago Haberes");
        triggeredRule.setActionName("DENY");
        return triggeredRule;
    }

    public static AnalyzeResponse2 getRsaAnalyzeResponse2() {
        AnalyzeResponse2 response = new AnalyzeResponse2();
        response.setMessageHeader(getMessageHeader());
        response.setIdentificationData(getIdentificationData());
        response.setRiskResult(getRiskResult2());
        return response;
    }

    private static RiskResult getRiskResult2() {
        RiskResult riskResult = new RiskResult();
        riskResult.setRiskScore(1);
        riskResult.setRiskScoreBand("1");
        riskResult.setTriggeredRule(getTriggeredRule2());
        return riskResult;
    }

    private static TriggeredRule getTriggeredRule2() {
        TriggeredRule triggeredRule = new TriggeredRule();
        triggeredRule.setActionCode(ActionCode.ALLOW);
        triggeredRule.setRuleId("Pago Haberes");
        triggeredRule.setActionName("DENY");
        return triggeredRule;
    }
    

    public static IdentificationData getIdentificationData() {
        IdentificationData identificationData = new IdentificationData();
        identificationData.setClientSessionId("222222222222222222233333S");
        identificationData.setOrgName("Santec S.A");
        identificationData.setUserName("Pedro Test");
        identificationData.setUserStatus(UserStatus.VERIFIED);
        identificationData.setTransactionId("2236");
        identificationData.setUserType(WSUserType.PERSISTENT);
        return identificationData;
    }

    public static MessageHeader getMessageHeader() {
        MessageHeader messageHeader = new MessageHeader();
        messageHeader.setApiType(APIType.WEB_REDIRECT);
        messageHeader.setRequestType(RequestType.CHALLENGE);
        messageHeader.setVersion("Prueba V");
        messageHeader.setRequestId("120");
        messageHeader.setTimeStamp("12:00 27/07/2022");
        return messageHeader;
    }

    public static EventDataList getEventDataList() {
        EventDataList eventDataList = new EventDataList();
        return eventDataList;
    }

    public static EventData getEventData1(){
        EventData eventData = new EventData();
        eventData.setTransactionData(getTransactionData());
        eventData.setEventType(EventType.PAYMENT);
        eventData.setEventDescription("EventData desc");
        return eventData;
    }

    public static Respuesta<RsaAnalyzeResponseData> getRsaAnalizeResponseData() {
        Respuesta<RsaAnalyzeResponseData> response =new Respuesta<RsaAnalyzeResponseData>();
        response.setEstadoRespuesta(EstadoRespuesta.OK);
        response.setRespuesta(getRsaAnalyzeResponseData());
        return response;
    }

    public static Respuesta<RsaAnalyzeResponseData> getRsaAnalizeResponseDataActionCodeDeny() {
        Respuesta<RsaAnalyzeResponseData> response =new Respuesta<RsaAnalyzeResponseData>();
        response.setEstadoRespuesta(EstadoRespuesta.OK);
        response.setRespuesta(getRsaAnalyzeResponseDataActionCodeDeny());
        return response;
    }

    public static Respuesta<RsaAnalyzeResponseData> getRsaAnalizeResponseDataErrorBusinessException() {
        Respuesta<RsaAnalyzeResponseData> response =new Respuesta<RsaAnalyzeResponseData>();
        response.setEstadoRespuesta(EstadoRespuesta.ERROR);
        response.setRespuesta(getRsaAnalyzeResponseDataError());
        response.setItemMensajeRespuesta(getItemMensajeRespuestaBusinessException());

        return response;
    }

    public static Respuesta<RsaAnalyzeResponseData> getRsaAnalizeResponseDataErrorDAOException() {
        Respuesta<RsaAnalyzeResponseData> response =new Respuesta<RsaAnalyzeResponseData>();
        response.setEstadoRespuesta(EstadoRespuesta.ERROR);
        response.setRespuesta(getRsaAnalyzeResponseDataError());
        response.setItemMensajeRespuesta(getItemMensajeRespuestaDAOException());

        return response;
    }


    public static List<ItemMensajeRespuesta> getItemMensajeRespuestaBusinessException() {
        ItemMensajeRespuesta item = new ItemMensajeRespuesta();
        item.setTipoError(TipoError.ERROR_GENERICO.getDescripcion());
        item.setMensaje("Ha ocurrido un error al invocar RSA");
        item.setTag(null);
        return Arrays.asList(item);
    }

    public static List<ItemMensajeRespuesta> getItemMensajeRespuestaDAOException() {
        ItemMensajeRespuesta item = new ItemMensajeRespuesta();
        item.setTipoError(TipoError.ERROR_GENERICO.getDescripcion());
        item.setMensaje("Ha ocurrido un error al invocar RSA");
        item.setTag("001");
        return Arrays.asList(item);
    }
    public static RsaAnalyzeResponseData getRsaAnalyzeResponseData() {
        RsaAnalyzeResponseData response = new RsaAnalyzeResponseData();
        response.setActionCode(ActionCode.CHALLENGE);
        response.setRsaGenericResponseData(getRsaGenericResponseData());
        return response;
    }

    public static RsaAnalyzeResponseData getRsaAnalyzeResponseDataActionCodeDeny() {
        RsaAnalyzeResponseData response = new RsaAnalyzeResponseData();
        response.setActionCode(ActionCode.DENY);
        response.setRsaGenericResponseData(getRsaGenericResponseData());
        return response;
    }


    public static RsaAnalyzeResponseData getRsaAnalyzeResponseDataError() {
        RsaAnalyzeResponseData response = new RsaAnalyzeResponseData();

        return response;
    }

    public static RsaGenericResponseData getRsaGenericResponseData() {
        RsaGenericResponseData data = new RsaGenericResponseData();
        data.setDeviceTokenCookie("device token cookie");
        data.setRsaCookie("222222ddddd3333333");
        return data;
    }

}
