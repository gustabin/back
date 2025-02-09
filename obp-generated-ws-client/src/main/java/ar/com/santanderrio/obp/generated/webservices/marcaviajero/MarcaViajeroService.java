package ar.com.santanderrio.obp.generated.webservices.marcaviajero;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 3.0.1
 * 2018-05-04T16:29:50.899-03:00
 * Generated source version: 3.0.1
 * 
 */
@WebService(targetNamespace = "http://visa.com.ar/visahome/integration/ws/mv", name = "MarcaViajeroService")
@XmlSeeAlso({ObjectFactory.class})
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface MarcaViajeroService {

    @WebResult(name = "obtenerTarjetasDelSocioResponse", targetNamespace = "http://visa.com.ar/visahome/integration/ws/mv", partName = "tarjetas")
    @WebMethod(action = "http://visa.com.ar/visahome/integration/ws/mv/obtenerTarjetasDelSocio")
    public ObtenerTarjetasDelSocioResponse obtenerTarjetasDelSocio(
        @WebParam(partName = "socio", name = "obtenerTarjetasDelSocioRequest", targetNamespace = "http://visa.com.ar/visahome/integration/ws/mv")
        ObtenerTarjetasDelSocioRequest socio
    );

    @WebResult(name = "echoViajeResponse", targetNamespace = "http://visa.com.ar/visahome/integration/ws/mv", partName = "message")
    @WebMethod(action = "http://visa.com.ar/visahome/integration/ws/mv/echoViaje")
    public EchoViajeResponse echoViaje(
        @WebParam(partName = "message", name = "echoViajeRequest", targetNamespace = "http://visa.com.ar/visahome/integration/ws/mv")
        EchoViajeRequest message
    );

    @WebResult(name = "eliminarViajeResponse", targetNamespace = "http://visa.com.ar/visahome/integration/ws/mv", partName = "response")
    @WebMethod(action = "http://visa.com.ar/visahome/integration/ws/mv/eliminarViaje")
    public EliminarViajeResponse eliminarViaje(
        @WebParam(partName = "request", name = "eliminarViajeRequest", targetNamespace = "http://visa.com.ar/visahome/integration/ws/mv")
        EliminarViajeRequest request
    );

    @WebResult(name = "confirmarViajeResponse", targetNamespace = "http://visa.com.ar/visahome/integration/ws/mv", partName = "parameters")
    @WebMethod(action = "http://visa.com.ar/visahome/integration/ws/mv/confirmarViaje")
    public ConfirmarViajeResponse confirmarViaje(
        @WebParam(partName = "parameters", name = "confirmarViajeRequest", targetNamespace = "http://visa.com.ar/visahome/integration/ws/mv")
        ConfirmarViajeRequest parameters
    );

    @WebResult(name = "obtenerPaisesResponse", targetNamespace = "http://visa.com.ar/visahome/integration/ws/mv", partName = "parameters")
    @WebMethod(action = "http://visa.com.ar/visahome/integration/ws/mv/obtenerPaises")
    public ObtenerPaisesResponse obtenerPaises(
        @WebParam(partName = "parameters", name = "obtenerPaisesRequest", targetNamespace = "http://visa.com.ar/visahome/integration/ws/mv")
        ObtenerPaisesRequest parameters
    );

    @WebResult(name = "obtenerViajesResponse", targetNamespace = "http://visa.com.ar/visahome/integration/ws/mv", partName = "tarjetas")
    @WebMethod(action = "http://visa.com.ar/visahome/integration/ws/mv/obtenerViajes")
    public ObtenerViajesResponse obtenerViajes(
        @WebParam(partName = "socio", name = "obtenerViajesRequest", targetNamespace = "http://visa.com.ar/visahome/integration/ws/mv")
        ObtenerViajesRequest socio
    );
}
