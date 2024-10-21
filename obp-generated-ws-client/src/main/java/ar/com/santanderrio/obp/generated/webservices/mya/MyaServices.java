package ar.com.santanderrio.obp.generated.webservices.mya;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import ar.com.santanderrio.obp.generated.webservices.mya.dominio.MyaXmlRequest;
import ar.com.santanderrio.obp.generated.webservices.mya.dominio.MyaXmlResponse;
import ar.com.santanderrio.obp.generated.webservices.mya.v2.GetStatusCliente;
import ar.com.santanderrio.obp.generated.webservices.mya.v2.GetStatusClienteResponse;

/**
 * WebService SOAP operar con MYA. El wsdl define n operaciones pero se puede
 * generar una invocacion general ya que el diferencial esta en el header con el
 * servicio.
 * 
 * @author sergio.e.goldentair
 *
 */
@WebService(targetNamespace = "http://services.mya.ar.bsch", name = "Services")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface MyaServices {

    /**
     * Invocar a las operaciones definidas en el ws Mya.
     * 
     * @param xml
     *            MyaXmlRequest
     * @return MyaXmlResponse
     */
    @WebMethod
    @WebResult(name = "respuesta", targetNamespace = "")
    MyaXmlResponse generalInvoke(@WebParam(name = "xml", targetNamespace = "") MyaXmlRequest xml);
}
