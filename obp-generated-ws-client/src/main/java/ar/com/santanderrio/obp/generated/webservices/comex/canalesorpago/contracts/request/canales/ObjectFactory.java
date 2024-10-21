
package ar.com.santanderrio.obp.generated.webservices.comex.canalesorpago.contracts.request.canales;

import java.math.BigDecimal;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ar.com.santanderrio.obp.generated.webservices.comex.canalesorpago.contracts.request.canales package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ProcesarOrPagoOBPRequest_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesOrPago", "ProcesarOrPagoOBPRequest");
    private final static QName _ProcesarOrPagoOBPRequestNroDocCliente_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesOrPago", "Nro_Doc_Cliente");
    private final static QName _ProcesarOrPagoOBPRequestCuentaCredito_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesOrPago", "Cuenta_Credito");
    private final static QName _ProcesarOrPagoOBPRequestConcepto_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesOrPago", "Concepto");
    private final static QName _ProcesarOrPagoOBPRequestNroForm_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesOrPago", "Nro_Form");
    private final static QName _ProcesarOrPagoOBPRequestEmpresaVinculada_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesOrPago", "Empresa_Vinculada");
    private final static QName _ProcesarOrPagoOBPRequestRazonSocial_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesOrPago", "Razon_Social");
    private final static QName _ProcesarOrPagoOBPRequestNroOperacion_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesOrPago", "Nro_Operacion");
    private final static QName _ProcesarOrPagoOBPRequestAceptaDdjj_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesOrPago", "Acepta_Ddjj");
    private final static QName _ProcesarOrPagoOBPRequestNupCliente_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesOrPago", "Nup_Cliente");
    private final static QName _ProcesarOrPagoOBPRequestTipoDocCliente_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesOrPago", "Tipo_Doc_Cliente");
    private final static QName _ProcesarOrPagoOBPRequestImportePago_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesOrPago", "Importe_Pago");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ar.com.santanderrio.obp.generated.webservices.comex.canalesorpago.contracts.request.canales
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ProcesarOrPagoOBPRequest }
     * 
     */
    public ProcesarOrPagoOBPRequest createProcesarOrPagoOBPRequest() {
        return new ProcesarOrPagoOBPRequest();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ProcesarOrPagoOBPRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesOrPago", name = "ProcesarOrPagoOBPRequest")
    public JAXBElement<ProcesarOrPagoOBPRequest> createProcesarOrPagoOBPRequest(ProcesarOrPagoOBPRequest value) {
        return new JAXBElement<ProcesarOrPagoOBPRequest>(_ProcesarOrPagoOBPRequest_QNAME, ProcesarOrPagoOBPRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesOrPago", name = "Nro_Doc_Cliente", scope = ProcesarOrPagoOBPRequest.class)
    public JAXBElement<String> createProcesarOrPagoOBPRequestNroDocCliente(String value) {
        return new JAXBElement<String>(_ProcesarOrPagoOBPRequestNroDocCliente_QNAME, String.class, ProcesarOrPagoOBPRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesOrPago", name = "Cuenta_Credito", scope = ProcesarOrPagoOBPRequest.class)
    public JAXBElement<String> createProcesarOrPagoOBPRequestCuentaCredito(String value) {
        return new JAXBElement<String>(_ProcesarOrPagoOBPRequestCuentaCredito_QNAME, String.class, ProcesarOrPagoOBPRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesOrPago", name = "Concepto", scope = ProcesarOrPagoOBPRequest.class)
    public JAXBElement<String> createProcesarOrPagoOBPRequestConcepto(String value) {
        return new JAXBElement<String>(_ProcesarOrPagoOBPRequestConcepto_QNAME, String.class, ProcesarOrPagoOBPRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesOrPago", name = "Nro_Form", scope = ProcesarOrPagoOBPRequest.class)
    public JAXBElement<Integer> createProcesarOrPagoOBPRequestNroForm(Integer value) {
        return new JAXBElement<Integer>(_ProcesarOrPagoOBPRequestNroForm_QNAME, Integer.class, ProcesarOrPagoOBPRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Short }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesOrPago", name = "Empresa_Vinculada", scope = ProcesarOrPagoOBPRequest.class)
    public JAXBElement<Short> createProcesarOrPagoOBPRequestEmpresaVinculada(Short value) {
        return new JAXBElement<Short>(_ProcesarOrPagoOBPRequestEmpresaVinculada_QNAME, Short.class, ProcesarOrPagoOBPRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesOrPago", name = "Razon_Social", scope = ProcesarOrPagoOBPRequest.class)
    public JAXBElement<String> createProcesarOrPagoOBPRequestRazonSocial(String value) {
        return new JAXBElement<String>(_ProcesarOrPagoOBPRequestRazonSocial_QNAME, String.class, ProcesarOrPagoOBPRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesOrPago", name = "Nro_Operacion", scope = ProcesarOrPagoOBPRequest.class)
    public JAXBElement<String> createProcesarOrPagoOBPRequestNroOperacion(String value) {
        return new JAXBElement<String>(_ProcesarOrPagoOBPRequestNroOperacion_QNAME, String.class, ProcesarOrPagoOBPRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Short }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesOrPago", name = "Acepta_Ddjj", scope = ProcesarOrPagoOBPRequest.class)
    public JAXBElement<Short> createProcesarOrPagoOBPRequestAceptaDdjj(Short value) {
        return new JAXBElement<Short>(_ProcesarOrPagoOBPRequestAceptaDdjj_QNAME, Short.class, ProcesarOrPagoOBPRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesOrPago", name = "Nup_Cliente", scope = ProcesarOrPagoOBPRequest.class)
    public JAXBElement<String> createProcesarOrPagoOBPRequestNupCliente(String value) {
        return new JAXBElement<String>(_ProcesarOrPagoOBPRequestNupCliente_QNAME, String.class, ProcesarOrPagoOBPRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesOrPago", name = "Tipo_Doc_Cliente", scope = ProcesarOrPagoOBPRequest.class)
    public JAXBElement<String> createProcesarOrPagoOBPRequestTipoDocCliente(String value) {
        return new JAXBElement<String>(_ProcesarOrPagoOBPRequestTipoDocCliente_QNAME, String.class, ProcesarOrPagoOBPRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesOrPago", name = "Importe_Pago", scope = ProcesarOrPagoOBPRequest.class)
    public JAXBElement<BigDecimal> createProcesarOrPagoOBPRequestImportePago(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_ProcesarOrPagoOBPRequestImportePago_QNAME, BigDecimal.class, ProcesarOrPagoOBPRequest.class, value);
    }

}
