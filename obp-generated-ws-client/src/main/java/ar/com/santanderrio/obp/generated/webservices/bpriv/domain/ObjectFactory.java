
package ar.com.santanderrio.obp.generated.webservices.bpriv.domain;

import java.math.BigDecimal;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ar.com.santanderrio.obp.generated.webservices.bpriv.domain package. 
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

    private final static QName _GrabamovAltair_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Domain", "GrabamovAltair");
    private final static QName _LoadAtits_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Domain", "LoadAtits");
    private final static QName _ArrayOfMovimiento3Ros_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Domain", "ArrayOfMovimiento3ros");
    private final static QName _ArrayOfLoadAtits_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Domain", "ArrayOfLoadAtits");
    private final static QName _Movimiento3Ros_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Domain", "Movimiento3ros");
    private final static QName _LoadRossi_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Domain", "LoadRossi");
    private final static QName _Saldo_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Domain", "Saldo");
    private final static QName _ArrayOfLoadRossi_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Domain", "ArrayOfLoadRossi");
    private final static QName _ControlCuadrePerfil_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Domain", "ControlCuadrePerfil");
    private final static QName _ArrayOfSaldo_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Domain", "ArrayOfSaldo");
    private final static QName _SaldoCAhorroDolares_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Domain", "CAhorroDolares");
    private final static QName _SaldoASESOR_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Domain", "ASESOR");
    private final static QName _SaldoSUCURSAL_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Domain", "SUCURSAL");
    private final static QName _SaldoFECHA_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Domain", "FECHA");
    private final static QName _SaldoDESCRIPCION_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Domain", "DESCRIPCION");
    private final static QName _SaldoCUENTA_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Domain", "CUENTA");
    private final static QName _SaldoCAhorroPesos_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Domain", "CAhorroPesos");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ar.com.santanderrio.obp.generated.webservices.bpriv.domain
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GrabamovAltair }
     * 
     */
    public GrabamovAltair createGrabamovAltair() {
        return new GrabamovAltair();
    }

    /**
     * Create an instance of {@link ArrayOfLoadRossi }
     * 
     */
    public ArrayOfLoadRossi createArrayOfLoadRossi() {
        return new ArrayOfLoadRossi();
    }

    /**
     * Create an instance of {@link ControlCuadrePerfil }
     * 
     */
    public ControlCuadrePerfil createControlCuadrePerfil() {
        return new ControlCuadrePerfil();
    }

    /**
     * Create an instance of {@link ArrayOfLoadAtits }
     * 
     */
    public ArrayOfLoadAtits createArrayOfLoadAtits() {
        return new ArrayOfLoadAtits();
    }

    /**
     * Create an instance of {@link ArrayOfMovimiento3Ros }
     * 
     */
    public ArrayOfMovimiento3Ros createArrayOfMovimiento3Ros() {
        return new ArrayOfMovimiento3Ros();
    }

    /**
     * Create an instance of {@link ArrayOfSaldo }
     * 
     */
    public ArrayOfSaldo createArrayOfSaldo() {
        return new ArrayOfSaldo();
    }

    /**
     * Create an instance of {@link LoadRossi }
     * 
     */
    public LoadRossi createLoadRossi() {
        return new LoadRossi();
    }

    /**
     * Create an instance of {@link Movimiento3Ros }
     * 
     */
    public Movimiento3Ros createMovimiento3Ros() {
        return new Movimiento3Ros();
    }

    /**
     * Create an instance of {@link Saldo }
     * 
     */
    public Saldo createSaldo() {
        return new Saldo();
    }

    /**
     * Create an instance of {@link LoadAtits }
     * 
     */
    public LoadAtits createLoadAtits() {
        return new LoadAtits();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GrabamovAltair }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Domain", name = "GrabamovAltair")
    public JAXBElement<GrabamovAltair> createGrabamovAltair(GrabamovAltair value) {
        return new JAXBElement<GrabamovAltair>(_GrabamovAltair_QNAME, GrabamovAltair.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LoadAtits }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Domain", name = "LoadAtits")
    public JAXBElement<LoadAtits> createLoadAtits(LoadAtits value) {
        return new JAXBElement<LoadAtits>(_LoadAtits_QNAME, LoadAtits.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfMovimiento3Ros }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Domain", name = "ArrayOfMovimiento3ros")
    public JAXBElement<ArrayOfMovimiento3Ros> createArrayOfMovimiento3Ros(ArrayOfMovimiento3Ros value) {
        return new JAXBElement<ArrayOfMovimiento3Ros>(_ArrayOfMovimiento3Ros_QNAME, ArrayOfMovimiento3Ros.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfLoadAtits }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Domain", name = "ArrayOfLoadAtits")
    public JAXBElement<ArrayOfLoadAtits> createArrayOfLoadAtits(ArrayOfLoadAtits value) {
        return new JAXBElement<ArrayOfLoadAtits>(_ArrayOfLoadAtits_QNAME, ArrayOfLoadAtits.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Movimiento3Ros }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Domain", name = "Movimiento3ros")
    public JAXBElement<Movimiento3Ros> createMovimiento3Ros(Movimiento3Ros value) {
        return new JAXBElement<Movimiento3Ros>(_Movimiento3Ros_QNAME, Movimiento3Ros.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LoadRossi }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Domain", name = "LoadRossi")
    public JAXBElement<LoadRossi> createLoadRossi(LoadRossi value) {
        return new JAXBElement<LoadRossi>(_LoadRossi_QNAME, LoadRossi.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Saldo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Domain", name = "Saldo")
    public JAXBElement<Saldo> createSaldo(Saldo value) {
        return new JAXBElement<Saldo>(_Saldo_QNAME, Saldo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfLoadRossi }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Domain", name = "ArrayOfLoadRossi")
    public JAXBElement<ArrayOfLoadRossi> createArrayOfLoadRossi(ArrayOfLoadRossi value) {
        return new JAXBElement<ArrayOfLoadRossi>(_ArrayOfLoadRossi_QNAME, ArrayOfLoadRossi.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ControlCuadrePerfil }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Domain", name = "ControlCuadrePerfil")
    public JAXBElement<ControlCuadrePerfil> createControlCuadrePerfil(ControlCuadrePerfil value) {
        return new JAXBElement<ControlCuadrePerfil>(_ControlCuadrePerfil_QNAME, ControlCuadrePerfil.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfSaldo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Domain", name = "ArrayOfSaldo")
    public JAXBElement<ArrayOfSaldo> createArrayOfSaldo(ArrayOfSaldo value) {
        return new JAXBElement<ArrayOfSaldo>(_ArrayOfSaldo_QNAME, ArrayOfSaldo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Domain", name = "CAhorroDolares", scope = Saldo.class)
    public JAXBElement<BigDecimal> createSaldoCAhorroDolares(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_SaldoCAhorroDolares_QNAME, BigDecimal.class, Saldo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Domain", name = "ASESOR", scope = Saldo.class)
    public JAXBElement<Integer> createSaldoASESOR(Integer value) {
        return new JAXBElement<Integer>(_SaldoASESOR_QNAME, Integer.class, Saldo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Short }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Domain", name = "SUCURSAL", scope = Saldo.class)
    public JAXBElement<Short> createSaldoSUCURSAL(Short value) {
        return new JAXBElement<Short>(_SaldoSUCURSAL_QNAME, Short.class, Saldo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Domain", name = "FECHA", scope = Saldo.class)
    public JAXBElement<String> createSaldoFECHA(String value) {
        return new JAXBElement<String>(_SaldoFECHA_QNAME, String.class, Saldo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Domain", name = "DESCRIPCION", scope = Saldo.class)
    public JAXBElement<String> createSaldoDESCRIPCION(String value) {
        return new JAXBElement<String>(_SaldoDESCRIPCION_QNAME, String.class, Saldo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Domain", name = "CUENTA", scope = Saldo.class)
    public JAXBElement<Long> createSaldoCUENTA(Long value) {
        return new JAXBElement<Long>(_SaldoCUENTA_QNAME, Long.class, Saldo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Domain", name = "CAhorroPesos", scope = Saldo.class)
    public JAXBElement<BigDecimal> createSaldoCAhorroPesos(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_SaldoCAhorroPesos_QNAME, BigDecimal.class, Saldo.class, value);
    }

}
