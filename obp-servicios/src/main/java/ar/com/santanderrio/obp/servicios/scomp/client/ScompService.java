package ar.com.santanderrio.obp.servicios.scomp.client;

import ar.com.santanderrio.obp.servicios.scomp.client.entities.*;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * Consultar scomp.<br/>
 * Este servicio fue generado MANUALMENTE no mediante wsdl2java plugin.
 * 
 */
@WebService(targetNamespace = "http://services2.scomp.ar.bsch", name = "scompWS")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface ScompService {

    /**
     * Alta comprobante.
     *
     * @param altaComprobanteRequest
     *            the alta comprobante request
     * @return ComprobanteResponse
     */
    @WebResult(name = "xml", targetNamespace = "http://services2.scomp.ar.bsch")
    @WebMethod
    public ComprobanteResponse altaComprobante(
            @WebParam(partName = "altaComprobanteRequest", name = "cambio", targetNamespace = "") AltaComprobanteRequest altaComprobanteRequest);

    /**
     * Listar los comprobantes que cumplan con los filtros enviados.
     *
     * @param listarComprobantesRequest
     *            the listar comprobantes request
     * @return ComprobantesResponse
     */
    @WebMethod
    @WebResult(name = "xml", targetNamespace = "http://services2.scomp.ar.bsch")
    ComprobanteResponse listarComprobantes(
            @WebParam(partName = "listarComprobantesRequest", name = "consulta", targetNamespace = "") ListarComprobantesRequest listarComprobantesRequest);
    
    @WebMethod
    @WebResult(name = "xml", targetNamespace = "http://services2.scomp.ar.bsch")
    ComprobanteResponse listaComprobantesOrExt(
            @WebParam(partName = "listaComprobantesOrExtRequest", name = "consulta", targetNamespace = "") ListaComprobantesOrExtRequest listaComprobantesOrExtRequest);
    
  
    @WebMethod
    @WebResult(name = "xml", targetNamespace = "http://services2.scomp.ar.bsch")
    ComprobanteResponse detalleComprobante(
            @WebParam(partName = "detalleComprobanteRequest", name = "consulta", targetNamespace = "") DetalleComprobanteRequest detalleComprobante);
    
}
