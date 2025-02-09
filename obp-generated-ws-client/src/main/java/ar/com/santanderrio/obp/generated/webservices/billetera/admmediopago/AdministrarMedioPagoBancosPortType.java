package ar.com.santanderrio.obp.generated.webservices.billetera.admmediopago;

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
 * 2015-10-14T17:18:43.444-03:00
 * Generated source version: 2.7.6
 * 
 */
@WebService(targetNamespace = "http://billetera.prismamp.com.ar/administrarMedioPago/", name = "AdministrarMedioPagoBancosPortType")
@XmlSeeAlso({ObjectFactory.class})
public interface AdministrarMedioPagoBancosPortType {

    @WebResult(name = "MediosPagoResponse", targetNamespace = "")
    @Action(input = "//billetera.prismamp.com.ar/administrarMedioPago/administrarMedioPago", output = "//billetera.prismamp.com.ar/administrarMedioPago/administrarMedioPago/administrarMedioPagoResponse")
    @RequestWrapper(localName = "administrarMedioPago", targetNamespace = "http://billetera.prismamp.com.ar/administrarMedioPago/", className = "com.rio.ijhb.fep.pagos.billetera.client.admmediopago.AdministrarMedioPago")
    @WebMethod(action = "http://billetera.prismamp.com.ar/administrarMedioPago/administrarMedioPago")
    @ResponseWrapper(localName = "administrarMedioPagoResponse", targetNamespace = "http://billetera.prismamp.com.ar/administrarMedioPago/", className = "com.rio.ijhb.fep.pagos.billetera.client.admmediopago.AdministrarMedioPagoResponse")
    public ar.com.santanderrio.obp.generated.webservices.billetera.admmediopago.MediosPagoResponse administrarMedioPago(
        @WebParam(name = "idCuenta", targetNamespace = "")
        int idCuenta,
        @WebParam(name = "banco", targetNamespace = "")
        java.lang.String banco,
        @WebParam(name = "canal", targetNamespace = "")
        java.lang.String canal,
        @WebParam(name = "MediosPago", targetNamespace = "")
        ar.com.santanderrio.obp.generated.webservices.billetera.admmediopago.MediosPago mediosPago
    );
}
