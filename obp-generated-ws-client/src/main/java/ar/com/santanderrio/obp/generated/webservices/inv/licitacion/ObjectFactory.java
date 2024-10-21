
package ar.com.santanderrio.obp.generated.webservices.inv.licitacion;

import java.math.BigDecimal;
import java.math.BigInteger;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ar.com.santanderrio.obp.generated.webservices.inv.licitacion package. 
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

    private final static QName _ConsultarOrdenResponseConsultarOrdenResult_QNAME = new QName("http://tempuri.org/", "ConsultarOrdenResult");
    private final static QName _Double_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "double");
    private final static QName _DateTime_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "dateTime");
    private final static QName _Float_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "float");
    private final static QName _AnyType_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "anyType");
    private final static QName _LicitacionesServiceFault_QNAME = new QName("http://schemas.datacontract.org/2004/07/Isban.BEL.ServiceContracts", "LicitacionesServiceFault");
    private final static QName _UnsignedLong_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "unsignedLong");
    private final static QName _Decimal_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "decimal");
    private final static QName _Duration_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "duration");
    private final static QName _Long_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "long");
    private final static QName _Char_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "char");
    private final static QName _UnsignedInt_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "unsignedInt");
    private final static QName _String_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "string");
    private final static QName _UnsignedShort_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "unsignedShort");
    private final static QName _Byte_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "byte");
    private final static QName _Guid_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "guid");
    private final static QName _Int_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "int");
    private final static QName _Short_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "short");
    private final static QName _UnsignedByte_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "unsignedByte");
    private final static QName _QName_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "QName");
    private final static QName _AnyURI_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "anyURI");
    private final static QName _Base64Binary_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "base64Binary");
    private final static QName _Boolean_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "boolean");
    private final static QName _ObtenerSaldoCuentasTitulosResponseObtenerSaldoCuentasTitulosResult_QNAME = new QName("http://tempuri.org/", "ObtenerSaldoCuentasTitulosResult");
    private final static QName _ReversarOrdenResponseReversarOrdenResult_QNAME = new QName("http://tempuri.org/", "ReversarOrdenResult");
    private final static QName _ObtenerTiposPliegoResponseObtenerTiposPliegoResult_QNAME = new QName("http://tempuri.org/", "ObtenerTiposPliegoResult");
    private final static QName _ObtenerSaldoCuentasCustodiaInput_QNAME = new QName("http://tempuri.org/", "input");
    private final static QName _LicitacionesServiceFaultTipo_QNAME = new QName("http://schemas.datacontract.org/2004/07/Isban.BEL.ServiceContracts", "Tipo");
    private final static QName _LicitacionesServiceFaultMessage_QNAME = new QName("http://schemas.datacontract.org/2004/07/Isban.BEL.ServiceContracts", "Message");
    private final static QName _ObtenerCuentasTitulosResponseObtenerCuentasTitulosResult_QNAME = new QName("http://tempuri.org/", "ObtenerCuentasTitulosResult");
    private final static QName _ConsultarTenenciaRenovableResponseConsultarTenenciaRenovableResult_QNAME = new QName("http://tempuri.org/", "ConsultarTenenciaRenovableResult");
    private final static QName _DownloadArchivoResponseDownloadArchivoResult_QNAME = new QName("http://tempuri.org/", "DownloadArchivoResult");
    private final static QName _ObtenerCanalTramoResponseObtenerCanalTramoResult_QNAME = new QName("http://tempuri.org/", "ObtenerCanalTramoResult");
    private final static QName _SimularOrdenResponseSimularOrdenResult_QNAME = new QName("http://tempuri.org/", "SimularOrdenResult");
    private final static QName _ConfirmarOrdenResponseConfirmarOrdenResult_QNAME = new QName("http://tempuri.org/", "ConfirmarOrdenResult");
    private final static QName _ObtenerSaldoCuentasCustodiaResponseObtenerSaldoCuentasCustodiaResult_QNAME = new QName("http://tempuri.org/", "ObtenerSaldoCuentasCustodiaResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ar.com.santanderrio.obp.generated.webservices.inv.licitacion
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ConsultarOrdenResponse }
     * 
     */
    public ConsultarOrdenResponse createConsultarOrdenResponse() {
        return new ConsultarOrdenResponse();
    }

    /**
     * Create an instance of {@link ObtenerSaldoCuentasTitulosResponse }
     * 
     */
    public ObtenerSaldoCuentasTitulosResponse createObtenerSaldoCuentasTitulosResponse() {
        return new ObtenerSaldoCuentasTitulosResponse();
    }

    /**
     * Create an instance of {@link ReversarOrdenResponse }
     * 
     */
    public ReversarOrdenResponse createReversarOrdenResponse() {
        return new ReversarOrdenResponse();
    }

    /**
     * Create an instance of {@link ObtenerTiposPliegoResponse }
     * 
     */
    public ObtenerTiposPliegoResponse createObtenerTiposPliegoResponse() {
        return new ObtenerTiposPliegoResponse();
    }

    /**
     * Create an instance of {@link ObtenerSaldoCuentasCustodia }
     * 
     */
    public ObtenerSaldoCuentasCustodia createObtenerSaldoCuentasCustodia() {
        return new ObtenerSaldoCuentasCustodia();
    }

    /**
     * Create an instance of {@link ObtenerCuentasTitulos }
     * 
     */
    public ObtenerCuentasTitulos createObtenerCuentasTitulos() {
        return new ObtenerCuentasTitulos();
    }

    /**
     * Create an instance of {@link ConfirmarOrden }
     * 
     */
    public ConfirmarOrden createConfirmarOrden() {
        return new ConfirmarOrden();
    }

    /**
     * Create an instance of {@link LicitacionesServiceFault }
     * 
     */
    public LicitacionesServiceFault createLicitacionesServiceFault() {
        return new LicitacionesServiceFault();
    }

    /**
     * Create an instance of {@link SimularOrden }
     * 
     */
    public SimularOrden createSimularOrden() {
        return new SimularOrden();
    }

    /**
     * Create an instance of {@link ObtenerCuentasTitulosResponse }
     * 
     */
    public ObtenerCuentasTitulosResponse createObtenerCuentasTitulosResponse() {
        return new ObtenerCuentasTitulosResponse();
    }

    /**
     * Create an instance of {@link ConsultarTenenciaRenovableResponse }
     * 
     */
    public ConsultarTenenciaRenovableResponse createConsultarTenenciaRenovableResponse() {
        return new ConsultarTenenciaRenovableResponse();
    }

    /**
     * Create an instance of {@link ConsultarTenenciaRenovable }
     * 
     */
    public ConsultarTenenciaRenovable createConsultarTenenciaRenovable() {
        return new ConsultarTenenciaRenovable();
    }

    /**
     * Create an instance of {@link ConsultarOrden }
     * 
     */
    public ConsultarOrden createConsultarOrden() {
        return new ConsultarOrden();
    }

    /**
     * Create an instance of {@link DownloadArchivoResponse }
     * 
     */
    public DownloadArchivoResponse createDownloadArchivoResponse() {
        return new DownloadArchivoResponse();
    }

    /**
     * Create an instance of {@link ObtenerTiposPliego }
     * 
     */
    public ObtenerTiposPliego createObtenerTiposPliego() {
        return new ObtenerTiposPliego();
    }

    /**
     * Create an instance of {@link ObtenerCanalTramoResponse }
     * 
     */
    public ObtenerCanalTramoResponse createObtenerCanalTramoResponse() {
        return new ObtenerCanalTramoResponse();
    }

    /**
     * Create an instance of {@link DownloadArchivo }
     * 
     */
    public DownloadArchivo createDownloadArchivo() {
        return new DownloadArchivo();
    }

    /**
     * Create an instance of {@link SimularOrdenResponse }
     * 
     */
    public SimularOrdenResponse createSimularOrdenResponse() {
        return new SimularOrdenResponse();
    }

    /**
     * Create an instance of {@link ReversarOrden }
     * 
     */
    public ReversarOrden createReversarOrden() {
        return new ReversarOrden();
    }

    /**
     * Create an instance of {@link ObtenerSaldoCuentasTitulos }
     * 
     */
    public ObtenerSaldoCuentasTitulos createObtenerSaldoCuentasTitulos() {
        return new ObtenerSaldoCuentasTitulos();
    }

    /**
     * Create an instance of {@link ConfirmarOrdenResponse }
     * 
     */
    public ConfirmarOrdenResponse createConfirmarOrdenResponse() {
        return new ConfirmarOrdenResponse();
    }

    /**
     * Create an instance of {@link ObtenerSaldoCuentasCustodiaResponse }
     * 
     */
    public ObtenerSaldoCuentasCustodiaResponse createObtenerSaldoCuentasCustodiaResponse() {
        return new ObtenerSaldoCuentasCustodiaResponse();
    }

    /**
     * Create an instance of {@link ObtenerCanalTramo }
     * 
     */
    public ObtenerCanalTramo createObtenerCanalTramo() {
        return new ObtenerCanalTramo();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "ConsultarOrdenResult", scope = ConsultarOrdenResponse.class)
    public JAXBElement<String> createConsultarOrdenResponseConsultarOrdenResult(String value) {
        return new JAXBElement<String>(_ConsultarOrdenResponseConsultarOrdenResult_QNAME, String.class, ConsultarOrdenResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "double")
    public JAXBElement<Double> createDouble(Double value) {
        return new JAXBElement<Double>(_Double_QNAME, Double.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "dateTime")
    public JAXBElement<XMLGregorianCalendar> createDateTime(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_DateTime_QNAME, XMLGregorianCalendar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Float }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "float")
    public JAXBElement<Float> createFloat(Float value) {
        return new JAXBElement<Float>(_Float_QNAME, Float.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "anyType")
    public JAXBElement<Object> createAnyType(Object value) {
        return new JAXBElement<Object>(_AnyType_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LicitacionesServiceFault }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Isban.BEL.ServiceContracts", name = "LicitacionesServiceFault")
    public JAXBElement<LicitacionesServiceFault> createLicitacionesServiceFault(LicitacionesServiceFault value) {
        return new JAXBElement<LicitacionesServiceFault>(_LicitacionesServiceFault_QNAME, LicitacionesServiceFault.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "unsignedLong")
    public JAXBElement<BigInteger> createUnsignedLong(BigInteger value) {
        return new JAXBElement<BigInteger>(_UnsignedLong_QNAME, BigInteger.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "decimal")
    public JAXBElement<BigDecimal> createDecimal(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_Decimal_QNAME, BigDecimal.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Duration }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "duration")
    public JAXBElement<Duration> createDuration(Duration value) {
        return new JAXBElement<Duration>(_Duration_QNAME, Duration.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "long")
    public JAXBElement<Long> createLong(Long value) {
        return new JAXBElement<Long>(_Long_QNAME, Long.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "char")
    public JAXBElement<Integer> createChar(Integer value) {
        return new JAXBElement<Integer>(_Char_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "unsignedInt")
    public JAXBElement<Long> createUnsignedInt(Long value) {
        return new JAXBElement<Long>(_UnsignedInt_QNAME, Long.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "string")
    public JAXBElement<String> createString(String value) {
        return new JAXBElement<String>(_String_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "unsignedShort")
    public JAXBElement<Integer> createUnsignedShort(Integer value) {
        return new JAXBElement<Integer>(_UnsignedShort_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Byte }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "byte")
    public JAXBElement<Byte> createByte(Byte value) {
        return new JAXBElement<Byte>(_Byte_QNAME, Byte.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "guid")
    public JAXBElement<String> createGuid(String value) {
        return new JAXBElement<String>(_Guid_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "int")
    public JAXBElement<Integer> createInt(Integer value) {
        return new JAXBElement<Integer>(_Int_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Short }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "short")
    public JAXBElement<Short> createShort(Short value) {
        return new JAXBElement<Short>(_Short_QNAME, Short.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Short }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "unsignedByte")
    public JAXBElement<Short> createUnsignedByte(Short value) {
        return new JAXBElement<Short>(_UnsignedByte_QNAME, Short.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QName }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "QName")
    public JAXBElement<QName> createQName(QName value) {
        return new JAXBElement<QName>(_QName_QNAME, QName.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "anyURI")
    public JAXBElement<String> createAnyURI(String value) {
        return new JAXBElement<String>(_AnyURI_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "base64Binary")
    public JAXBElement<byte[]> createBase64Binary(byte[] value) {
        return new JAXBElement<byte[]>(_Base64Binary_QNAME, byte[].class, null, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "boolean")
    public JAXBElement<Boolean> createBoolean(Boolean value) {
        return new JAXBElement<Boolean>(_Boolean_QNAME, Boolean.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "ObtenerSaldoCuentasTitulosResult", scope = ObtenerSaldoCuentasTitulosResponse.class)
    public JAXBElement<String> createObtenerSaldoCuentasTitulosResponseObtenerSaldoCuentasTitulosResult(String value) {
        return new JAXBElement<String>(_ObtenerSaldoCuentasTitulosResponseObtenerSaldoCuentasTitulosResult_QNAME, String.class, ObtenerSaldoCuentasTitulosResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "ReversarOrdenResult", scope = ReversarOrdenResponse.class)
    public JAXBElement<String> createReversarOrdenResponseReversarOrdenResult(String value) {
        return new JAXBElement<String>(_ReversarOrdenResponseReversarOrdenResult_QNAME, String.class, ReversarOrdenResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "ObtenerTiposPliegoResult", scope = ObtenerTiposPliegoResponse.class)
    public JAXBElement<String> createObtenerTiposPliegoResponseObtenerTiposPliegoResult(String value) {
        return new JAXBElement<String>(_ObtenerTiposPliegoResponseObtenerTiposPliegoResult_QNAME, String.class, ObtenerTiposPliegoResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "input", scope = ObtenerSaldoCuentasCustodia.class)
    public JAXBElement<String> createObtenerSaldoCuentasCustodiaInput(String value) {
        return new JAXBElement<String>(_ObtenerSaldoCuentasCustodiaInput_QNAME, String.class, ObtenerSaldoCuentasCustodia.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "input", scope = ObtenerCuentasTitulos.class)
    public JAXBElement<String> createObtenerCuentasTitulosInput(String value) {
        return new JAXBElement<String>(_ObtenerSaldoCuentasCustodiaInput_QNAME, String.class, ObtenerCuentasTitulos.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "input", scope = ConfirmarOrden.class)
    public JAXBElement<String> createConfirmarOrdenInput(String value) {
        return new JAXBElement<String>(_ObtenerSaldoCuentasCustodiaInput_QNAME, String.class, ConfirmarOrden.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Isban.BEL.ServiceContracts", name = "Tipo", scope = LicitacionesServiceFault.class)
    public JAXBElement<String> createLicitacionesServiceFaultTipo(String value) {
        return new JAXBElement<String>(_LicitacionesServiceFaultTipo_QNAME, String.class, LicitacionesServiceFault.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Isban.BEL.ServiceContracts", name = "Message", scope = LicitacionesServiceFault.class)
    public JAXBElement<String> createLicitacionesServiceFaultMessage(String value) {
        return new JAXBElement<String>(_LicitacionesServiceFaultMessage_QNAME, String.class, LicitacionesServiceFault.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "input", scope = SimularOrden.class)
    public JAXBElement<String> createSimularOrdenInput(String value) {
        return new JAXBElement<String>(_ObtenerSaldoCuentasCustodiaInput_QNAME, String.class, SimularOrden.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "ObtenerCuentasTitulosResult", scope = ObtenerCuentasTitulosResponse.class)
    public JAXBElement<String> createObtenerCuentasTitulosResponseObtenerCuentasTitulosResult(String value) {
        return new JAXBElement<String>(_ObtenerCuentasTitulosResponseObtenerCuentasTitulosResult_QNAME, String.class, ObtenerCuentasTitulosResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "ConsultarTenenciaRenovableResult", scope = ConsultarTenenciaRenovableResponse.class)
    public JAXBElement<String> createConsultarTenenciaRenovableResponseConsultarTenenciaRenovableResult(String value) {
        return new JAXBElement<String>(_ConsultarTenenciaRenovableResponseConsultarTenenciaRenovableResult_QNAME, String.class, ConsultarTenenciaRenovableResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "input", scope = ConsultarTenenciaRenovable.class)
    public JAXBElement<String> createConsultarTenenciaRenovableInput(String value) {
        return new JAXBElement<String>(_ObtenerSaldoCuentasCustodiaInput_QNAME, String.class, ConsultarTenenciaRenovable.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "input", scope = ConsultarOrden.class)
    public JAXBElement<String> createConsultarOrdenInput(String value) {
        return new JAXBElement<String>(_ObtenerSaldoCuentasCustodiaInput_QNAME, String.class, ConsultarOrden.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "DownloadArchivoResult", scope = DownloadArchivoResponse.class)
    public JAXBElement<String> createDownloadArchivoResponseDownloadArchivoResult(String value) {
        return new JAXBElement<String>(_DownloadArchivoResponseDownloadArchivoResult_QNAME, String.class, DownloadArchivoResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "input", scope = ObtenerTiposPliego.class)
    public JAXBElement<String> createObtenerTiposPliegoInput(String value) {
        return new JAXBElement<String>(_ObtenerSaldoCuentasCustodiaInput_QNAME, String.class, ObtenerTiposPliego.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "ObtenerCanalTramoResult", scope = ObtenerCanalTramoResponse.class)
    public JAXBElement<String> createObtenerCanalTramoResponseObtenerCanalTramoResult(String value) {
        return new JAXBElement<String>(_ObtenerCanalTramoResponseObtenerCanalTramoResult_QNAME, String.class, ObtenerCanalTramoResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "input", scope = DownloadArchivo.class)
    public JAXBElement<String> createDownloadArchivoInput(String value) {
        return new JAXBElement<String>(_ObtenerSaldoCuentasCustodiaInput_QNAME, String.class, DownloadArchivo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "SimularOrdenResult", scope = SimularOrdenResponse.class)
    public JAXBElement<String> createSimularOrdenResponseSimularOrdenResult(String value) {
        return new JAXBElement<String>(_SimularOrdenResponseSimularOrdenResult_QNAME, String.class, SimularOrdenResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "input", scope = ReversarOrden.class)
    public JAXBElement<String> createReversarOrdenInput(String value) {
        return new JAXBElement<String>(_ObtenerSaldoCuentasCustodiaInput_QNAME, String.class, ReversarOrden.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "input", scope = ObtenerSaldoCuentasTitulos.class)
    public JAXBElement<String> createObtenerSaldoCuentasTitulosInput(String value) {
        return new JAXBElement<String>(_ObtenerSaldoCuentasCustodiaInput_QNAME, String.class, ObtenerSaldoCuentasTitulos.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "ConfirmarOrdenResult", scope = ConfirmarOrdenResponse.class)
    public JAXBElement<String> createConfirmarOrdenResponseConfirmarOrdenResult(String value) {
        return new JAXBElement<String>(_ConfirmarOrdenResponseConfirmarOrdenResult_QNAME, String.class, ConfirmarOrdenResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "ObtenerSaldoCuentasCustodiaResult", scope = ObtenerSaldoCuentasCustodiaResponse.class)
    public JAXBElement<String> createObtenerSaldoCuentasCustodiaResponseObtenerSaldoCuentasCustodiaResult(String value) {
        return new JAXBElement<String>(_ObtenerSaldoCuentasCustodiaResponseObtenerSaldoCuentasCustodiaResult_QNAME, String.class, ObtenerSaldoCuentasCustodiaResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "input", scope = ObtenerCanalTramo.class)
    public JAXBElement<String> createObtenerCanalTramoInput(String value) {
        return new JAXBElement<String>(_ObtenerSaldoCuentasCustodiaInput_QNAME, String.class, ObtenerCanalTramo.class, value);
    }

}
