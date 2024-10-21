package ar.com.santanderrio.obp.generated.webservices.solicitudes.andreani;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService(targetNamespace = "http://services.andreani.wsdeltarj", name = "wsdeltarj")
public interface TrackingTarjetasService {

    @WebMethod
    @WebResult(name = "out", targetNamespace = "")
    TrackingTarjetasOut consultarTraza(@WebParam(name = "in", targetNamespace = "") TrackingTarjetasIn inRequest) throws TrackingTarjetasSecurityException_Exception;
}
