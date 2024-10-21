
package ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador;

/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
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
 * 2020-07-17T10:36:30.810-03:00
 * Generated source version: 2.7.6
 * 
 */
public final class IDireccionador_BasicHttpBindingIDireccionador_Client {

    private static final QName SERVICE_NAME = new QName("http://tempuri.org/", "Direccionador");

    private IDireccionador_BasicHttpBindingIDireccionador_Client() {
    }

    public static void main(String args[]) throws java.lang.Exception {
        URL wsdlURL = Direccionador.WSDL_LOCATION;
        if (args.length > 0 && args[0] != null && !"".equals(args[0])) { 
            File wsdlFile = new File(args[0]);
            try {
                if (wsdlFile.exists()) {
                    wsdlURL = wsdlFile.toURI().toURL();
                } else {
                    wsdlURL = new URL(args[0]);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
      
        Direccionador ss = new Direccionador(wsdlURL, SERVICE_NAME);
        IDireccionador port = ss.getBasicHttpBindingIDireccionador();  
        
        {
        System.out.println("Invoking getAltaCitaTransactionConMotivoSvc...");
        java.lang.String _getAltaCitaTransactionConMotivoSvc_valor = "";
        ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.datacontracts.GetAltaCitaConMotivoSvcResponse _getAltaCitaTransactionConMotivoSvc__return = port.getAltaCitaTransactionConMotivoSvc(_getAltaCitaTransactionConMotivoSvc_valor);
        System.out.println("getAltaCitaTransactionConMotivoSvc.result=" + _getAltaCitaTransactionConMotivoSvc__return);


        }
        {
        System.out.println("Invoking getBajaTurnoSvc...");
        java.lang.String _getBajaTurnoSvc_valor = "";
        ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.datacontracts.GetBajaTurnoSvcResponse _getBajaTurnoSvc__return = port.getBajaTurnoSvc(_getBajaTurnoSvc_valor);
        System.out.println("getBajaTurnoSvc.result=" + _getBajaTurnoSvc__return);


        }
        {
        System.out.println("Invoking getMotivosSvc...");
        java.lang.String _getMotivosSvc_valor = "";
        ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.datacontracts.GetMotivosSvcResponse _getMotivosSvc__return = port.getMotivosSvc(_getMotivosSvc_valor);
        System.out.println("getMotivosSvc.result=" + _getMotivosSvc__return);


        }
        {
        System.out.println("Invoking getConsultaCitasPlataformaSvc...");
        java.lang.String _getConsultaCitasPlataformaSvc_valor = "";
        ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.datacontracts.GetConsultaCitasPlataformaSvcResponse _getConsultaCitasPlataformaSvc__return = port.getConsultaCitasPlataformaSvc(_getConsultaCitasPlataformaSvc_valor);
        System.out.println("getConsultaCitasPlataformaSvc.result=" + _getConsultaCitasPlataformaSvc__return);


        }
        {
        System.out.println("Invoking getConsultaCitaSvc...");
        java.lang.String _getConsultaCitaSvc_valor = "";
        ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.datacontracts.GetConsultaCitaSvcResponse _getConsultaCitaSvc__return = port.getConsultaCitaSvc(_getConsultaCitaSvc_valor);
        System.out.println("getConsultaCitaSvc.result=" + _getConsultaCitaSvc__return);


        }
        {
        System.out.println("Invoking getMotivosPorSucursalYSectorCliNoCliSvc...");
        java.lang.String _getMotivosPorSucursalYSectorCliNoCliSvc_valor = "";
        ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.datacontracts.GetMotivosPorSucursalYSectorCliNoCliSvcResponse _getMotivosPorSucursalYSectorCliNoCliSvc__return = port.getMotivosPorSucursalYSectorCliNoCliSvc(_getMotivosPorSucursalYSectorCliNoCliSvc_valor);
        System.out.println("getMotivosPorSucursalYSectorCliNoCliSvc.result=" + _getMotivosPorSucursalYSectorCliNoCliSvc__return);


        }
        {
        System.out.println("Invoking getAltaCitaTransactionCliNoCliSvc...");
        java.lang.String _getAltaCitaTransactionCliNoCliSvc_valor = "";
        ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.datacontracts.GetAltaCitaTransactionCliNoCliSvcResponse _getAltaCitaTransactionCliNoCliSvc__return = port.getAltaCitaTransactionCliNoCliSvc(_getAltaCitaTransactionCliNoCliSvc_valor);
        System.out.println("getAltaCitaTransactionCliNoCliSvc.result=" + _getAltaCitaTransactionCliNoCliSvc__return);


        }
        {
        System.out.println("Invoking getConsultaCitaConEmailCuitSvc...");
        java.lang.String _getConsultaCitaConEmailCuitSvc_valor = "";
        ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.datacontracts.GetConsultaCitaConEmailCuitSvcResponse _getConsultaCitaConEmailCuitSvc__return = port.getConsultaCitaConEmailCuitSvc(_getConsultaCitaConEmailCuitSvc_valor);
        System.out.println("getConsultaCitaConEmailCuitSvc.result=" + _getConsultaCitaConEmailCuitSvc__return);


        }
        {
        System.out.println("Invoking getCancelacionCitasSvc...");
        java.lang.String _getCancelacionCitasSvc_valor = "";
        ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.datacontracts.GetCancelacionCitasSvcResponse _getCancelacionCitasSvc__return = port.getCancelacionCitasSvc(_getCancelacionCitasSvc_valor);
        System.out.println("getCancelacionCitasSvc.result=" + _getCancelacionCitasSvc__return);


        }
        {
        System.out.println("Invoking getAltaCitaTransactionConEmailCuitSvc...");
        java.lang.String _getAltaCitaTransactionConEmailCuitSvc_valor = "";
        ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.datacontracts.GetAltaCitaTransactionConEmailCuitSvcResponse _getAltaCitaTransactionConEmailCuitSvc__return = port.getAltaCitaTransactionConEmailCuitSvc(_getAltaCitaTransactionConEmailCuitSvc_valor);
        System.out.println("getAltaCitaTransactionConEmailCuitSvc.result=" + _getAltaCitaTransactionConEmailCuitSvc__return);


        }
        {
        System.out.println("Invoking getConsultaCitaConMotivoSvc...");
        java.lang.String _getConsultaCitaConMotivoSvc_valor = "";
        ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.datacontracts.GetConsultaCitaConMotivoSvcResponse _getConsultaCitaConMotivoSvc__return = port.getConsultaCitaConMotivoSvc(_getConsultaCitaConMotivoSvc_valor);
        System.out.println("getConsultaCitaConMotivoSvc.result=" + _getConsultaCitaConMotivoSvc__return);


        }
        {
        System.out.println("Invoking getTurnoEncSvc...");
        java.lang.String _getTurnoEncSvc_valor = "";
        ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.datacontracts.GetTurnoEncSvcResponse _getTurnoEncSvc__return = port.getTurnoEncSvc(_getTurnoEncSvc_valor);
        System.out.println("getTurnoEncSvc.result=" + _getTurnoEncSvc__return);


        }
        {
        System.out.println("Invoking getAltaCitaTransactionSvc...");
        java.lang.String _getAltaCitaTransactionSvc_valor = "";
        ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.datacontracts.GetAltaCitaSvcResponse _getAltaCitaTransactionSvc__return = port.getAltaCitaTransactionSvc(_getAltaCitaTransactionSvc_valor);
        System.out.println("getAltaCitaTransactionSvc.result=" + _getAltaCitaTransactionSvc__return);


        }
        {
        System.out.println("Invoking getConsultaHorariosDisponiblesSvc...");
        java.lang.String _getConsultaHorariosDisponiblesSvc_valor = "";
        ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.datacontracts.GetConsultaHorariosDisponiblesSvcResponse _getConsultaHorariosDisponiblesSvc__return = port.getConsultaHorariosDisponiblesSvc(_getConsultaHorariosDisponiblesSvc_valor);
        System.out.println("getConsultaHorariosDisponiblesSvc.result=" + _getConsultaHorariosDisponiblesSvc__return);


        }
        {
        System.out.println("Invoking getConsultaSucursalesSvc...");
        java.lang.String _getConsultaSucursalesSvc_valor = "";
        ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.datacontracts.GetConsultaSucursalesSvcResponse _getConsultaSucursalesSvc__return = port.getConsultaSucursalesSvc(_getConsultaSucursalesSvc_valor);
        System.out.println("getConsultaSucursalesSvc.result=" + _getConsultaSucursalesSvc__return);


        }
        {
        System.out.println("Invoking getModificacionCitaTransactionSvc...");
        java.lang.String _getModificacionCitaTransactionSvc_valor = "";
        ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.datacontracts.GetModificacionCitaSvcResponse _getModificacionCitaTransactionSvc__return = port.getModificacionCitaTransactionSvc(_getModificacionCitaTransactionSvc_valor);
        System.out.println("getModificacionCitaTransactionSvc.result=" + _getModificacionCitaTransactionSvc__return);


        }
        {
        System.out.println("Invoking getAltaCitaTransactionSinNupSvc...");
        java.lang.String _getAltaCitaTransactionSinNupSvc_valor = "";
        ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.datacontracts.GetAltaCitaSinNupSvcResponse _getAltaCitaTransactionSinNupSvc__return = port.getAltaCitaTransactionSinNupSvc(_getAltaCitaTransactionSinNupSvc_valor);
        System.out.println("getAltaCitaTransactionSinNupSvc.result=" + _getAltaCitaTransactionSinNupSvc__return);


        }
        {
        System.out.println("Invoking setTurnoEncSvc...");
        java.lang.String _setTurnoEncSvc_valor = "";
        ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.datacontracts.SetTurnoEncSvcResponse _setTurnoEncSvc__return = port.setTurnoEncSvc(_setTurnoEncSvc_valor);
        System.out.println("setTurnoEncSvc.result=" + _setTurnoEncSvc__return);


        }
        {
        System.out.println("Invoking getConsultaCitasCajaSvc...");
        java.lang.String _getConsultaCitasCajaSvc_valor = "";
        ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.datacontracts.GetConsultaCitasCajaSvcResponse _getConsultaCitasCajaSvc__return = port.getConsultaCitasCajaSvc(_getConsultaCitasCajaSvc_valor);
        System.out.println("getConsultaCitasCajaSvc.result=" + _getConsultaCitasCajaSvc__return);


        }
        {
        System.out.println("Invoking setAltaSucursalSvc...");
        java.lang.String _setAltaSucursalSvc_valor = "";
        ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.datacontracts.SetAltaSucursalSvcResponse _setAltaSucursalSvc__return = port.setAltaSucursalSvc(_setAltaSucursalSvc_valor);
        System.out.println("setAltaSucursalSvc.result=" + _setAltaSucursalSvc__return);


        }

        System.exit(0);
    }

}
