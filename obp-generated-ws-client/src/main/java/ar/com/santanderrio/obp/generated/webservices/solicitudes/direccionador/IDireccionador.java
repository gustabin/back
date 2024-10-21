package ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.7.6
 * 2020-07-17T10:36:30.866-03:00
 * Generated source version: 2.7.6
 * 
 */
@WebService(targetNamespace = "http://tempuri.org/", name = "IDireccionador")
@XmlSeeAlso({ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.serialization.ObjectFactory.class, ObjectFactory.class, ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.datacontracts.ObjectFactory.class})
public interface IDireccionador {

    @WebResult(name = "GetAltaCitaTransactionConMotivoSvcResult", targetNamespace = "http://tempuri.org/")
    @Action(input = "http://tempuri.org/IDireccionador/GetAltaCitaTransactionConMotivoSvc", output = "http://tempuri.org/IDireccionador/GetAltaCitaTransactionConMotivoSvcResponse")
    @RequestWrapper(localName = "GetAltaCitaTransactionConMotivoSvc", targetNamespace = "http://tempuri.org/", className = "ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.GetAltaCitaTransactionConMotivoSvc")
    @WebMethod(operationName = "GetAltaCitaTransactionConMotivoSvc", action = "http://tempuri.org/IDireccionador/GetAltaCitaTransactionConMotivoSvc")
    @ResponseWrapper(localName = "GetAltaCitaTransactionConMotivoSvcResponse", targetNamespace = "http://tempuri.org/", className = "ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.GetAltaCitaTransactionConMotivoSvcResponse")
    public ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.datacontracts.GetAltaCitaConMotivoSvcResponse getAltaCitaTransactionConMotivoSvc(
        @WebParam(name = "valor", targetNamespace = "http://tempuri.org/")
        java.lang.String valor
    );

    @WebResult(name = "GetBajaTurnoSvcResult", targetNamespace = "http://tempuri.org/")
    @Action(input = "http://tempuri.org/IDireccionador/GetBajaTurnoSvc", output = "http://tempuri.org/IDireccionador/GetBajaTurnoSvcResponse")
    @RequestWrapper(localName = "GetBajaTurnoSvc", targetNamespace = "http://tempuri.org/", className = "ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.GetBajaTurnoSvc")
    @WebMethod(operationName = "GetBajaTurnoSvc", action = "http://tempuri.org/IDireccionador/GetBajaTurnoSvc")
    @ResponseWrapper(localName = "GetBajaTurnoSvcResponse", targetNamespace = "http://tempuri.org/", className = "ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.GetBajaTurnoSvcResponse")
    public ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.datacontracts.GetBajaTurnoSvcResponse getBajaTurnoSvc(
        @WebParam(name = "valor", targetNamespace = "http://tempuri.org/")
        java.lang.String valor
    );

    @WebResult(name = "GetMotivosSvcResult", targetNamespace = "http://tempuri.org/")
    @Action(input = "http://tempuri.org/IDireccionador/GetMotivosSvc", output = "http://tempuri.org/IDireccionador/GetMotivosSvcResponse")
    @RequestWrapper(localName = "GetMotivosSvc", targetNamespace = "http://tempuri.org/", className = "ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.GetMotivosSvc")
    @WebMethod(operationName = "GetMotivosSvc", action = "http://tempuri.org/IDireccionador/GetMotivosSvc")
    @ResponseWrapper(localName = "GetMotivosSvcResponse", targetNamespace = "http://tempuri.org/", className = "ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.GetMotivosSvcResponse")
    public ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.datacontracts.GetMotivosSvcResponse getMotivosSvc(
        @WebParam(name = "valor", targetNamespace = "http://tempuri.org/")
        java.lang.String valor
    );

    @WebResult(name = "GetConsultaCitasPlataformaSvcResult", targetNamespace = "http://tempuri.org/")
    @Action(input = "http://tempuri.org/IDireccionador/GetConsultaCitasPlataformaSvc", output = "http://tempuri.org/IDireccionador/GetConsultaCitasPlataformaSvcResponse")
    @RequestWrapper(localName = "GetConsultaCitasPlataformaSvc", targetNamespace = "http://tempuri.org/", className = "ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.GetConsultaCitasPlataformaSvc")
    @WebMethod(operationName = "GetConsultaCitasPlataformaSvc", action = "http://tempuri.org/IDireccionador/GetConsultaCitasPlataformaSvc")
    @ResponseWrapper(localName = "GetConsultaCitasPlataformaSvcResponse", targetNamespace = "http://tempuri.org/", className = "ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.GetConsultaCitasPlataformaSvcResponse")
    public ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.datacontracts.GetConsultaCitasPlataformaSvcResponse getConsultaCitasPlataformaSvc(
        @WebParam(name = "valor", targetNamespace = "http://tempuri.org/")
        java.lang.String valor
    );

    @WebResult(name = "GetConsultaCitaSvcResult", targetNamespace = "http://tempuri.org/")
    @Action(input = "http://tempuri.org/IDireccionador/GetConsultaCitaSvc", output = "http://tempuri.org/IDireccionador/GetConsultaCitaSvcResponse")
    @RequestWrapper(localName = "GetConsultaCitaSvc", targetNamespace = "http://tempuri.org/", className = "ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.GetConsultaCitaSvc")
    @WebMethod(operationName = "GetConsultaCitaSvc", action = "http://tempuri.org/IDireccionador/GetConsultaCitaSvc")
    @ResponseWrapper(localName = "GetConsultaCitaSvcResponse", targetNamespace = "http://tempuri.org/", className = "ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.GetConsultaCitaSvcResponse")
    public ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.datacontracts.GetConsultaCitaSvcResponse getConsultaCitaSvc(
        @WebParam(name = "valor", targetNamespace = "http://tempuri.org/")
        java.lang.String valor
    );

    @WebResult(name = "GetMotivosPorSucursalYSectorCliNoCliSvcResult", targetNamespace = "http://tempuri.org/")
    @Action(input = "http://tempuri.org/IDireccionador/GetMotivosPorSucursalYSectorCliNoCliSvc", output = "http://tempuri.org/IDireccionador/GetMotivosPorSucursalYSectorCliNoCliSvcResponse")
    @RequestWrapper(localName = "GetMotivosPorSucursalYSectorCliNoCliSvc", targetNamespace = "http://tempuri.org/", className = "ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.GetMotivosPorSucursalYSectorCliNoCliSvc")
    @WebMethod(operationName = "GetMotivosPorSucursalYSectorCliNoCliSvc", action = "http://tempuri.org/IDireccionador/GetMotivosPorSucursalYSectorCliNoCliSvc")
    @ResponseWrapper(localName = "GetMotivosPorSucursalYSectorCliNoCliSvcResponse", targetNamespace = "http://tempuri.org/", className = "ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.GetMotivosPorSucursalYSectorCliNoCliSvcResponse")
    public ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.datacontracts.GetMotivosPorSucursalYSectorCliNoCliSvcResponse getMotivosPorSucursalYSectorCliNoCliSvc(
        @WebParam(name = "valor", targetNamespace = "http://tempuri.org/")
        java.lang.String valor
    );

    @WebResult(name = "GetAltaCitaTransactionCliNoCliSvcResult", targetNamespace = "http://tempuri.org/")
    @Action(input = "http://tempuri.org/IDireccionador/GetAltaCitaTransactionCliNoCliSvc", output = "http://tempuri.org/IDireccionador/GetAltaCitaTransactionCliNoCliSvcResponse")
    @RequestWrapper(localName = "GetAltaCitaTransactionCliNoCliSvc", targetNamespace = "http://tempuri.org/", className = "ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.GetAltaCitaTransactionCliNoCliSvc")
    @WebMethod(operationName = "GetAltaCitaTransactionCliNoCliSvc", action = "http://tempuri.org/IDireccionador/GetAltaCitaTransactionCliNoCliSvc")
    @ResponseWrapper(localName = "GetAltaCitaTransactionCliNoCliSvcResponse", targetNamespace = "http://tempuri.org/", className = "ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.GetAltaCitaTransactionCliNoCliSvcResponse")
    public ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.datacontracts.GetAltaCitaTransactionCliNoCliSvcResponse getAltaCitaTransactionCliNoCliSvc(
        @WebParam(name = "valor", targetNamespace = "http://tempuri.org/")
        java.lang.String valor
    );

    @WebResult(name = "GetConsultaCitaConEmailCuitSvcResult", targetNamespace = "http://tempuri.org/")
    @Action(input = "http://tempuri.org/IDireccionador/GetConsultaCitaConEmailCuitSvc", output = "http://tempuri.org/IDireccionador/GetConsultaCitaConEmailCuitSvcResponse")
    @RequestWrapper(localName = "GetConsultaCitaConEmailCuitSvc", targetNamespace = "http://tempuri.org/", className = "ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.GetConsultaCitaConEmailCuitSvc")
    @WebMethod(operationName = "GetConsultaCitaConEmailCuitSvc", action = "http://tempuri.org/IDireccionador/GetConsultaCitaConEmailCuitSvc")
    @ResponseWrapper(localName = "GetConsultaCitaConEmailCuitSvcResponse", targetNamespace = "http://tempuri.org/", className = "ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.GetConsultaCitaConEmailCuitSvcResponse")
    public ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.datacontracts.GetConsultaCitaConEmailCuitSvcResponse getConsultaCitaConEmailCuitSvc(
        @WebParam(name = "valor", targetNamespace = "http://tempuri.org/")
        java.lang.String valor
    );

    @WebResult(name = "GetCancelacionCitasSvcResult", targetNamespace = "http://tempuri.org/")
    @Action(input = "http://tempuri.org/IDireccionador/GetCancelacionCitasSvc", output = "http://tempuri.org/IDireccionador/GetCancelacionCitasSvcResponse")
    @RequestWrapper(localName = "GetCancelacionCitasSvc", targetNamespace = "http://tempuri.org/", className = "ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.GetCancelacionCitasSvc")
    @WebMethod(operationName = "GetCancelacionCitasSvc", action = "http://tempuri.org/IDireccionador/GetCancelacionCitasSvc")
    @ResponseWrapper(localName = "GetCancelacionCitasSvcResponse", targetNamespace = "http://tempuri.org/", className = "ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.GetCancelacionCitasSvcResponse")
    public ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.datacontracts.GetCancelacionCitasSvcResponse getCancelacionCitasSvc(
        @WebParam(name = "valor", targetNamespace = "http://tempuri.org/")
        java.lang.String valor
    );

    @WebResult(name = "GetAltaCitaTransactionConEmailCuitSvcResult", targetNamespace = "http://tempuri.org/")
    @Action(input = "http://tempuri.org/IDireccionador/GetAltaCitaTransactionConEmailCuitSvc", output = "http://tempuri.org/IDireccionador/GetAltaCitaTransactionConEmailCuitSvcResponse")
    @RequestWrapper(localName = "GetAltaCitaTransactionConEmailCuitSvc", targetNamespace = "http://tempuri.org/", className = "ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.GetAltaCitaTransactionConEmailCuitSvc")
    @WebMethod(operationName = "GetAltaCitaTransactionConEmailCuitSvc", action = "http://tempuri.org/IDireccionador/GetAltaCitaTransactionConEmailCuitSvc")
    @ResponseWrapper(localName = "GetAltaCitaTransactionConEmailCuitSvcResponse", targetNamespace = "http://tempuri.org/", className = "ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.GetAltaCitaTransactionConEmailCuitSvcResponse")
    public ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.datacontracts.GetAltaCitaTransactionConEmailCuitSvcResponse getAltaCitaTransactionConEmailCuitSvc(
        @WebParam(name = "valor", targetNamespace = "http://tempuri.org/")
        java.lang.String valor
    );

    @WebResult(name = "GetConsultaCitaConMotivoSvcResult", targetNamespace = "http://tempuri.org/")
    @Action(input = "http://tempuri.org/IDireccionador/GetConsultaCitaConMotivoSvc", output = "http://tempuri.org/IDireccionador/GetConsultaCitaConMotivoSvcResponse")
    @RequestWrapper(localName = "GetConsultaCitaConMotivoSvc", targetNamespace = "http://tempuri.org/", className = "ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.GetConsultaCitaConMotivoSvc")
    @WebMethod(operationName = "GetConsultaCitaConMotivoSvc", action = "http://tempuri.org/IDireccionador/GetConsultaCitaConMotivoSvc")
    @ResponseWrapper(localName = "GetConsultaCitaConMotivoSvcResponse", targetNamespace = "http://tempuri.org/", className = "ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.GetConsultaCitaConMotivoSvcResponse")
    public ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.datacontracts.GetConsultaCitaConMotivoSvcResponse getConsultaCitaConMotivoSvc(
        @WebParam(name = "valor", targetNamespace = "http://tempuri.org/")
        java.lang.String valor
    );

    @WebResult(name = "GetTurnoEncSvcResult", targetNamespace = "http://tempuri.org/")
    @Action(input = "http://tempuri.org/IDireccionador/GetTurnoEncSvc", output = "http://tempuri.org/IDireccionador/GetTurnoEncSvcResponse")
    @RequestWrapper(localName = "GetTurnoEncSvc", targetNamespace = "http://tempuri.org/", className = "ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.GetTurnoEncSvc")
    @WebMethod(operationName = "GetTurnoEncSvc", action = "http://tempuri.org/IDireccionador/GetTurnoEncSvc")
    @ResponseWrapper(localName = "GetTurnoEncSvcResponse", targetNamespace = "http://tempuri.org/", className = "ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.GetTurnoEncSvcResponse")
    public ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.datacontracts.GetTurnoEncSvcResponse getTurnoEncSvc(
        @WebParam(name = "valor", targetNamespace = "http://tempuri.org/")
        java.lang.String valor
    );

    @WebResult(name = "GetAltaCitaTransactionSvcResult", targetNamespace = "http://tempuri.org/")
    @Action(input = "http://tempuri.org/IDireccionador/GetAltaCitaTransactionSvc", output = "http://tempuri.org/IDireccionador/GetAltaCitaTransactionSvcResponse")
    @RequestWrapper(localName = "GetAltaCitaTransactionSvc", targetNamespace = "http://tempuri.org/", className = "ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.GetAltaCitaTransactionSvc")
    @WebMethod(operationName = "GetAltaCitaTransactionSvc", action = "http://tempuri.org/IDireccionador/GetAltaCitaTransactionSvc")
    @ResponseWrapper(localName = "GetAltaCitaTransactionSvcResponse", targetNamespace = "http://tempuri.org/", className = "ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.GetAltaCitaTransactionSvcResponse")
    public ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.datacontracts.GetAltaCitaSvcResponse getAltaCitaTransactionSvc(
        @WebParam(name = "valor", targetNamespace = "http://tempuri.org/")
        java.lang.String valor
    );

    @WebResult(name = "GetConsultaHorariosDisponiblesSvcResult", targetNamespace = "http://tempuri.org/")
    @Action(input = "http://tempuri.org/IDireccionador/GetConsultaHorariosDisponiblesSvc", output = "http://tempuri.org/IDireccionador/GetConsultaHorariosDisponiblesSvcResponse")
    @RequestWrapper(localName = "GetConsultaHorariosDisponiblesSvc", targetNamespace = "http://tempuri.org/", className = "ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.GetConsultaHorariosDisponiblesSvc")
    @WebMethod(operationName = "GetConsultaHorariosDisponiblesSvc", action = "http://tempuri.org/IDireccionador/GetConsultaHorariosDisponiblesSvc")
    @ResponseWrapper(localName = "GetConsultaHorariosDisponiblesSvcResponse", targetNamespace = "http://tempuri.org/", className = "ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.GetConsultaHorariosDisponiblesSvcResponse")
    public ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.datacontracts.GetConsultaHorariosDisponiblesSvcResponse getConsultaHorariosDisponiblesSvc(
        @WebParam(name = "valor", targetNamespace = "http://tempuri.org/")
        java.lang.String valor
    );

    @WebResult(name = "GetConsultaSucursalesSvcResult", targetNamespace = "http://tempuri.org/")
    @Action(input = "http://tempuri.org/IDireccionador/GetConsultaSucursalesSvc", output = "http://tempuri.org/IDireccionador/GetConsultaSucursalesSvcResponse")
    @RequestWrapper(localName = "GetConsultaSucursalesSvc", targetNamespace = "http://tempuri.org/", className = "ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.GetConsultaSucursalesSvc")
    @WebMethod(operationName = "GetConsultaSucursalesSvc", action = "http://tempuri.org/IDireccionador/GetConsultaSucursalesSvc")
    @ResponseWrapper(localName = "GetConsultaSucursalesSvcResponse", targetNamespace = "http://tempuri.org/", className = "ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.GetConsultaSucursalesSvcResponse")
    public ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.datacontracts.GetConsultaSucursalesSvcResponse getConsultaSucursalesSvc(
        @WebParam(name = "valor", targetNamespace = "http://tempuri.org/")
        java.lang.String valor
    );

    @WebResult(name = "GetModificacionCitaTransactionSvcResult", targetNamespace = "http://tempuri.org/")
    @Action(input = "http://tempuri.org/IDireccionador/GetModificacionCitaTransactionSvc", output = "http://tempuri.org/IDireccionador/GetModificacionCitaTransactionSvcResponse")
    @RequestWrapper(localName = "GetModificacionCitaTransactionSvc", targetNamespace = "http://tempuri.org/", className = "ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.GetModificacionCitaTransactionSvc")
    @WebMethod(operationName = "GetModificacionCitaTransactionSvc", action = "http://tempuri.org/IDireccionador/GetModificacionCitaTransactionSvc")
    @ResponseWrapper(localName = "GetModificacionCitaTransactionSvcResponse", targetNamespace = "http://tempuri.org/", className = "ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.GetModificacionCitaTransactionSvcResponse")
    public ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.datacontracts.GetModificacionCitaSvcResponse getModificacionCitaTransactionSvc(
        @WebParam(name = "valor", targetNamespace = "http://tempuri.org/")
        java.lang.String valor
    );

    @WebResult(name = "GetAltaCitaTransactionSinNupSvcResult", targetNamespace = "http://tempuri.org/")
    @Action(input = "http://tempuri.org/IDireccionador/GetAltaCitaTransactionSinNupSvc", output = "http://tempuri.org/IDireccionador/GetAltaCitaTransactionSinNupSvcResponse")
    @RequestWrapper(localName = "GetAltaCitaTransactionSinNupSvc", targetNamespace = "http://tempuri.org/", className = "ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.GetAltaCitaTransactionSinNupSvc")
    @WebMethod(operationName = "GetAltaCitaTransactionSinNupSvc", action = "http://tempuri.org/IDireccionador/GetAltaCitaTransactionSinNupSvc")
    @ResponseWrapper(localName = "GetAltaCitaTransactionSinNupSvcResponse", targetNamespace = "http://tempuri.org/", className = "ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.GetAltaCitaTransactionSinNupSvcResponse")
    public ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.datacontracts.GetAltaCitaSinNupSvcResponse getAltaCitaTransactionSinNupSvc(
        @WebParam(name = "valor", targetNamespace = "http://tempuri.org/")
        java.lang.String valor
    );

    @WebResult(name = "SetTurnoEncSvcResult", targetNamespace = "http://tempuri.org/")
    @Action(input = "http://tempuri.org/IDireccionador/SetTurnoEncSvc", output = "http://tempuri.org/IDireccionador/SetTurnoEncSvcResponse")
    @RequestWrapper(localName = "SetTurnoEncSvc", targetNamespace = "http://tempuri.org/", className = "ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.SetTurnoEncSvc")
    @WebMethod(operationName = "SetTurnoEncSvc", action = "http://tempuri.org/IDireccionador/SetTurnoEncSvc")
    @ResponseWrapper(localName = "SetTurnoEncSvcResponse", targetNamespace = "http://tempuri.org/", className = "ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.SetTurnoEncSvcResponse")
    public ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.datacontracts.SetTurnoEncSvcResponse setTurnoEncSvc(
        @WebParam(name = "valor", targetNamespace = "http://tempuri.org/")
        java.lang.String valor
    );

    @WebResult(name = "GetConsultaCitasCajaSvcResult", targetNamespace = "http://tempuri.org/")
    @Action(input = "http://tempuri.org/IDireccionador/GetConsultaCitasCajaSvc", output = "http://tempuri.org/IDireccionador/GetConsultaCitasCajaSvcResponse")
    @RequestWrapper(localName = "GetConsultaCitasCajaSvc", targetNamespace = "http://tempuri.org/", className = "ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.GetConsultaCitasCajaSvc")
    @WebMethod(operationName = "GetConsultaCitasCajaSvc", action = "http://tempuri.org/IDireccionador/GetConsultaCitasCajaSvc")
    @ResponseWrapper(localName = "GetConsultaCitasCajaSvcResponse", targetNamespace = "http://tempuri.org/", className = "ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.GetConsultaCitasCajaSvcResponse")
    public ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.datacontracts.GetConsultaCitasCajaSvcResponse getConsultaCitasCajaSvc(
        @WebParam(name = "valor", targetNamespace = "http://tempuri.org/")
        java.lang.String valor
    );

    @WebResult(name = "SetAltaSucursalSvcResult", targetNamespace = "http://tempuri.org/")
    @Action(input = "http://tempuri.org/IDireccionador/SetAltaSucursalSvc", output = "http://tempuri.org/IDireccionador/SetAltaSucursalSvcResponse")
    @RequestWrapper(localName = "SetAltaSucursalSvc", targetNamespace = "http://tempuri.org/", className = "ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.SetAltaSucursalSvc")
    @WebMethod(operationName = "SetAltaSucursalSvc", action = "http://tempuri.org/IDireccionador/SetAltaSucursalSvc")
    @ResponseWrapper(localName = "SetAltaSucursalSvcResponse", targetNamespace = "http://tempuri.org/", className = "ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.SetAltaSucursalSvcResponse")
    public ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.datacontracts.SetAltaSucursalSvcResponse setAltaSucursalSvc(
        @WebParam(name = "valor", targetNamespace = "http://tempuri.org/")
        java.lang.String valor
    );
}
