
package ar.com.santanderrio.obp.generated.webservices.bpriv;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
import ar.com.santanderrio.obp.generated.webservices.bpriv.common.servicecontracts.BPCUENTASCMBGRABAMOVPARAMETER;
import ar.com.santanderrio.obp.generated.webservices.bpriv.common.servicecontracts.BPCUENTASCNSATITPARAMETER;
import ar.com.santanderrio.obp.generated.webservices.bpriv.common.servicecontracts.BPCUENTASCNSCUADREPERFILPARAMETER;
import ar.com.santanderrio.obp.generated.webservices.bpriv.common.servicecontracts.BPCUENTASCNSMOVIMIENTOSPARAMETER;
import ar.com.santanderrio.obp.generated.webservices.bpriv.common.servicecontracts.BPCUENTASCNSROSSIPARAMETER;
import ar.com.santanderrio.obp.generated.webservices.bpriv.common.servicecontracts.BPCUENTASCNSSALDOSPARAMETER;
import ar.com.santanderrio.obp.generated.webservices.bpriv.common.servicecontracts.BPORDENESCMBALTAPARAMETER;
import ar.com.santanderrio.obp.generated.webservices.bpriv.common.servicecontracts.BPORDENESCMBESTADOPARAMETER;
import ar.com.santanderrio.obp.generated.webservices.bpriv.common.servicecontracts.InsertarOperacionCambioOBParameter;
import ar.com.santanderrio.obp.generated.webservices.bpriv.common.servicecontracts.InsertarOperacionCambioParameter;
import ar.com.santanderrio.obp.generated.webservices.bpriv.common.servicecontracts.InsertarTransferenciaEntreBancosOBParameter;
import ar.com.santanderrio.obp.generated.webservices.bpriv.common.servicecontracts.InsertarTransferenciaEntreBancosParameter;
import ar.com.santanderrio.obp.generated.webservices.bpriv.common.servicecontracts.InsertarTransferenciaRIORIOOBParameter;
import ar.com.santanderrio.obp.generated.webservices.bpriv.common.servicecontracts.InsertarTransferenciaRIORIOParameter;
import ar.com.santanderrio.obp.generated.webservices.bpriv.domain.ArrayOfLoadAtits;
import ar.com.santanderrio.obp.generated.webservices.bpriv.domain.ArrayOfLoadRossi;
import ar.com.santanderrio.obp.generated.webservices.bpriv.domain.ArrayOfMovimiento3Ros;
import ar.com.santanderrio.obp.generated.webservices.bpriv.domain.ArrayOfSaldo;
import ar.com.santanderrio.obp.generated.webservices.bpriv.domain.ControlCuadrePerfil;
import ar.com.santanderrio.obp.generated.webservices.bpriv.domain.GrabamovAltair;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ar.com.santanderrio.obp.generated.webservices.bpriv package. 
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

    private final static QName _BPCUENTASCMBGRABAMOVParameter_QNAME = new QName("http://tempuri.org/", "Parameter");
    private final static QName _BPCUENTASCNSMOVIMIENTOSResponseBPCUENTASCNSMOVIMIENTOSResult_QNAME = new QName("http://tempuri.org/", "BP_CUENTAS_CNS_MOVIMIENTOSResult");
    private final static QName _InsertarTransferenciaRIORIOOBParameter_QNAME = new QName("http://tempuri.org/", "parameter");
    private final static QName _InsertarTransferenciaRIORIOResponseInsertarTransferenciaRIORIOResult_QNAME = new QName("http://tempuri.org/", "InsertarTransferenciaRIORIOResult");
    private final static QName _InsertarOperacionCambioResponseInsertarOperacionCambioResult_QNAME = new QName("http://tempuri.org/", "InsertarOperacionCambioResult");
    private final static QName _InsertarTransferenciaEntreBancosOBResponseInsertarTransferenciaEntreBancosOBResult_QNAME = new QName("http://tempuri.org/", "InsertarTransferenciaEntreBancosOBResult");
    private final static QName _InsertarTransferenciaRIORIOOBResponseInsertarTransferenciaRIORIOOBResult_QNAME = new QName("http://tempuri.org/", "InsertarTransferenciaRIORIOOBResult");
    private final static QName _BPCUENTASCNSATITResponseBPCUENTASCNSATITResult_QNAME = new QName("http://tempuri.org/", "BP_CUENTAS_CNS_ATITResult");
    private final static QName _InsertarTransferenciaEntreBancosResponseInsertarTransferenciaEntreBancosResult_QNAME = new QName("http://tempuri.org/", "InsertarTransferenciaEntreBancosResult");
    private final static QName _BPORDENESCMBALTAResponseBPORDENESCMBALTAResult_QNAME = new QName("http://tempuri.org/", "BP_ORDENES_CMB_ALTAResult");
    private final static QName _BPCUENTASCNSCUADREPERFILResponseBPCUENTASCNSCUADREPERFILResult_QNAME = new QName("http://tempuri.org/", "BP_CUENTAS_CNS_CUADREPERFILResult");
    private final static QName _BPCUENTASCNSROSSIResponseBPCUENTASCNSROSSIResult_QNAME = new QName("http://tempuri.org/", "BP_CUENTAS_CNS_ROSSIResult");
    private final static QName _InsertarOperacionCambioOBResponseInsertarOperacionCambioOBResult_QNAME = new QName("http://tempuri.org/", "InsertarOperacionCambioOBResult");
    private final static QName _BPCUENTASCNSSALDOSResponseBPCUENTASCNSSALDOSResult_QNAME = new QName("http://tempuri.org/", "BP_CUENTAS_CNS_SALDOSResult");
    private final static QName _BPCUENTASCMBGRABAMOVResponseBPCUENTASCMBGRABAMOVResult_QNAME = new QName("http://tempuri.org/", "BP_CUENTAS_CMB_GRABAMOVResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ar.com.santanderrio.obp.generated.webservices.bpriv
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link BPCUENTASCNSMOVIMIENTOS }
     * 
     */
    public BPCUENTASCNSMOVIMIENTOS createBPCUENTASCNSMOVIMIENTOS() {
        return new BPCUENTASCNSMOVIMIENTOS();
    }

    /**
     * Create an instance of {@link BPORDENESCMBESTADO }
     * 
     */
    public BPORDENESCMBESTADO createBPORDENESCMBESTADO() {
        return new BPORDENESCMBESTADO();
    }

    /**
     * Create an instance of {@link BPCUENTASCNSSALDOS }
     * 
     */
    public BPCUENTASCNSSALDOS createBPCUENTASCNSSALDOS() {
        return new BPCUENTASCNSSALDOS();
    }

    /**
     * Create an instance of {@link InsertarOperacionCambioResponse }
     * 
     */
    public InsertarOperacionCambioResponse createInsertarOperacionCambioResponse() {
        return new InsertarOperacionCambioResponse();
    }

    /**
     * Create an instance of {@link BPCUENTASCMBGRABAMOVResponse }
     * 
     */
    public BPCUENTASCMBGRABAMOVResponse createBPCUENTASCMBGRABAMOVResponse() {
        return new BPCUENTASCMBGRABAMOVResponse();
    }

    /**
     * Create an instance of {@link InsertarTransferenciaEntreBancosOBResponse }
     * 
     */
    public InsertarTransferenciaEntreBancosOBResponse createInsertarTransferenciaEntreBancosOBResponse() {
        return new InsertarTransferenciaEntreBancosOBResponse();
    }

    /**
     * Create an instance of {@link InsertarTransferenciaRIORIOOB }
     * 
     */
    public InsertarTransferenciaRIORIOOB createInsertarTransferenciaRIORIOOB() {
        return new InsertarTransferenciaRIORIOOB();
    }

    /**
     * Create an instance of {@link BPCUENTASCNSROSSIResponse }
     * 
     */
    public BPCUENTASCNSROSSIResponse createBPCUENTASCNSROSSIResponse() {
        return new BPCUENTASCNSROSSIResponse();
    }

    /**
     * Create an instance of {@link BPCUENTASCNSCUADREPERFILResponse }
     * 
     */
    public BPCUENTASCNSCUADREPERFILResponse createBPCUENTASCNSCUADREPERFILResponse() {
        return new BPCUENTASCNSCUADREPERFILResponse();
    }

    /**
     * Create an instance of {@link BPORDENESCMBALTAResponse }
     * 
     */
    public BPORDENESCMBALTAResponse createBPORDENESCMBALTAResponse() {
        return new BPORDENESCMBALTAResponse();
    }

    /**
     * Create an instance of {@link InsertarTransferenciaEntreBancos }
     * 
     */
    public InsertarTransferenciaEntreBancos createInsertarTransferenciaEntreBancos() {
        return new InsertarTransferenciaEntreBancos();
    }

    /**
     * Create an instance of {@link InsertarOperacionCambioOBResponse }
     * 
     */
    public InsertarOperacionCambioOBResponse createInsertarOperacionCambioOBResponse() {
        return new InsertarOperacionCambioOBResponse();
    }

    /**
     * Create an instance of {@link BPCUENTASCNSATITResponse }
     * 
     */
    public BPCUENTASCNSATITResponse createBPCUENTASCNSATITResponse() {
        return new BPCUENTASCNSATITResponse();
    }

    /**
     * Create an instance of {@link InsertarTransferenciaRIORIOResponse }
     * 
     */
    public InsertarTransferenciaRIORIOResponse createInsertarTransferenciaRIORIOResponse() {
        return new InsertarTransferenciaRIORIOResponse();
    }

    /**
     * Create an instance of {@link BPCUENTASCNSMOVIMIENTOSResponse }
     * 
     */
    public BPCUENTASCNSMOVIMIENTOSResponse createBPCUENTASCNSMOVIMIENTOSResponse() {
        return new BPCUENTASCNSMOVIMIENTOSResponse();
    }

    /**
     * Create an instance of {@link BPORDENESCMBESTADOResponse }
     * 
     */
    public BPORDENESCMBESTADOResponse createBPORDENESCMBESTADOResponse() {
        return new BPORDENESCMBESTADOResponse();
    }

    /**
     * Create an instance of {@link BPCUENTASCNSSALDOSResponse }
     * 
     */
    public BPCUENTASCNSSALDOSResponse createBPCUENTASCNSSALDOSResponse() {
        return new BPCUENTASCNSSALDOSResponse();
    }

    /**
     * Create an instance of {@link InsertarOperacionCambio }
     * 
     */
    public InsertarOperacionCambio createInsertarOperacionCambio() {
        return new InsertarOperacionCambio();
    }

    /**
     * Create an instance of {@link BPCUENTASCMBGRABAMOV }
     * 
     */
    public BPCUENTASCMBGRABAMOV createBPCUENTASCMBGRABAMOV() {
        return new BPCUENTASCMBGRABAMOV();
    }

    /**
     * Create an instance of {@link InsertarTransferenciaEntreBancosOB }
     * 
     */
    public InsertarTransferenciaEntreBancosOB createInsertarTransferenciaEntreBancosOB() {
        return new InsertarTransferenciaEntreBancosOB();
    }

    /**
     * Create an instance of {@link BPCUENTASCNSROSSI }
     * 
     */
    public BPCUENTASCNSROSSI createBPCUENTASCNSROSSI() {
        return new BPCUENTASCNSROSSI();
    }

    /**
     * Create an instance of {@link BPCUENTASCNSCUADREPERFIL }
     * 
     */
    public BPCUENTASCNSCUADREPERFIL createBPCUENTASCNSCUADREPERFIL() {
        return new BPCUENTASCNSCUADREPERFIL();
    }

    /**
     * Create an instance of {@link InsertarTransferenciaRIORIOOBResponse }
     * 
     */
    public InsertarTransferenciaRIORIOOBResponse createInsertarTransferenciaRIORIOOBResponse() {
        return new InsertarTransferenciaRIORIOOBResponse();
    }

    /**
     * Create an instance of {@link BPORDENESCMBALTA }
     * 
     */
    public BPORDENESCMBALTA createBPORDENESCMBALTA() {
        return new BPORDENESCMBALTA();
    }

    /**
     * Create an instance of {@link InsertarOperacionCambioOB }
     * 
     */
    public InsertarOperacionCambioOB createInsertarOperacionCambioOB() {
        return new InsertarOperacionCambioOB();
    }

    /**
     * Create an instance of {@link BPCUENTASCNSATIT }
     * 
     */
    public BPCUENTASCNSATIT createBPCUENTASCNSATIT() {
        return new BPCUENTASCNSATIT();
    }

    /**
     * Create an instance of {@link InsertarTransferenciaEntreBancosResponse }
     * 
     */
    public InsertarTransferenciaEntreBancosResponse createInsertarTransferenciaEntreBancosResponse() {
        return new InsertarTransferenciaEntreBancosResponse();
    }

    /**
     * Create an instance of {@link InsertarTransferenciaRIORIO }
     * 
     */
    public InsertarTransferenciaRIORIO createInsertarTransferenciaRIORIO() {
        return new InsertarTransferenciaRIORIO();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BPCUENTASCMBGRABAMOVPARAMETER }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "Parameter", scope = BPCUENTASCMBGRABAMOV.class)
    public JAXBElement<BPCUENTASCMBGRABAMOVPARAMETER> createBPCUENTASCMBGRABAMOVParameter(BPCUENTASCMBGRABAMOVPARAMETER value) {
        return new JAXBElement<BPCUENTASCMBGRABAMOVPARAMETER>(_BPCUENTASCMBGRABAMOVParameter_QNAME, BPCUENTASCMBGRABAMOVPARAMETER.class, BPCUENTASCMBGRABAMOV.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfMovimiento3Ros }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "BP_CUENTAS_CNS_MOVIMIENTOSResult", scope = BPCUENTASCNSMOVIMIENTOSResponse.class)
    public JAXBElement<ArrayOfMovimiento3Ros> createBPCUENTASCNSMOVIMIENTOSResponseBPCUENTASCNSMOVIMIENTOSResult(ArrayOfMovimiento3Ros value) {
        return new JAXBElement<ArrayOfMovimiento3Ros>(_BPCUENTASCNSMOVIMIENTOSResponseBPCUENTASCNSMOVIMIENTOSResult_QNAME, ArrayOfMovimiento3Ros.class, BPCUENTASCNSMOVIMIENTOSResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InsertarTransferenciaRIORIOOBParameter }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "parameter", scope = InsertarTransferenciaRIORIOOB.class)
    public JAXBElement<InsertarTransferenciaRIORIOOBParameter> createInsertarTransferenciaRIORIOOBParameter(InsertarTransferenciaRIORIOOBParameter value) {
        return new JAXBElement<InsertarTransferenciaRIORIOOBParameter>(_InsertarTransferenciaRIORIOOBParameter_QNAME, InsertarTransferenciaRIORIOOBParameter.class, InsertarTransferenciaRIORIOOB.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BPCUENTASCNSSALDOSPARAMETER }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "Parameter", scope = BPCUENTASCNSSALDOS.class)
    public JAXBElement<BPCUENTASCNSSALDOSPARAMETER> createBPCUENTASCNSSALDOSParameter(BPCUENTASCNSSALDOSPARAMETER value) {
        return new JAXBElement<BPCUENTASCNSSALDOSPARAMETER>(_BPCUENTASCMBGRABAMOVParameter_QNAME, BPCUENTASCNSSALDOSPARAMETER.class, BPCUENTASCNSSALDOS.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "InsertarTransferenciaRIORIOResult", scope = InsertarTransferenciaRIORIOResponse.class)
    public JAXBElement<String> createInsertarTransferenciaRIORIOResponseInsertarTransferenciaRIORIOResult(String value) {
        return new JAXBElement<String>(_InsertarTransferenciaRIORIOResponseInsertarTransferenciaRIORIOResult_QNAME, String.class, InsertarTransferenciaRIORIOResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BPCUENTASCNSROSSIPARAMETER }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "Parameter", scope = BPCUENTASCNSROSSI.class)
    public JAXBElement<BPCUENTASCNSROSSIPARAMETER> createBPCUENTASCNSROSSIParameter(BPCUENTASCNSROSSIPARAMETER value) {
        return new JAXBElement<BPCUENTASCNSROSSIPARAMETER>(_BPCUENTASCMBGRABAMOVParameter_QNAME, BPCUENTASCNSROSSIPARAMETER.class, BPCUENTASCNSROSSI.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InsertarTransferenciaEntreBancosOBParameter }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "parameter", scope = InsertarTransferenciaEntreBancosOB.class)
    public JAXBElement<InsertarTransferenciaEntreBancosOBParameter> createInsertarTransferenciaEntreBancosOBParameter(InsertarTransferenciaEntreBancosOBParameter value) {
        return new JAXBElement<InsertarTransferenciaEntreBancosOBParameter>(_InsertarTransferenciaRIORIOOBParameter_QNAME, InsertarTransferenciaEntreBancosOBParameter.class, InsertarTransferenciaEntreBancosOB.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BPORDENESCMBALTAPARAMETER }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "Parameter", scope = BPORDENESCMBALTA.class)
    public JAXBElement<BPORDENESCMBALTAPARAMETER> createBPORDENESCMBALTAParameter(BPORDENESCMBALTAPARAMETER value) {
        return new JAXBElement<BPORDENESCMBALTAPARAMETER>(_BPCUENTASCMBGRABAMOVParameter_QNAME, BPORDENESCMBALTAPARAMETER.class, BPORDENESCMBALTA.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "InsertarOperacionCambioResult", scope = InsertarOperacionCambioResponse.class)
    public JAXBElement<String> createInsertarOperacionCambioResponseInsertarOperacionCambioResult(String value) {
        return new JAXBElement<String>(_InsertarOperacionCambioResponseInsertarOperacionCambioResult_QNAME, String.class, InsertarOperacionCambioResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InsertarOperacionCambioParameter }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "parameter", scope = InsertarOperacionCambio.class)
    public JAXBElement<InsertarOperacionCambioParameter> createInsertarOperacionCambioParameter(InsertarOperacionCambioParameter value) {
        return new JAXBElement<InsertarOperacionCambioParameter>(_InsertarTransferenciaRIORIOOBParameter_QNAME, InsertarOperacionCambioParameter.class, InsertarOperacionCambio.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InsertarOperacionCambioOBParameter }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "parameter", scope = InsertarOperacionCambioOB.class)
    public JAXBElement<InsertarOperacionCambioOBParameter> createInsertarOperacionCambioOBParameter(InsertarOperacionCambioOBParameter value) {
        return new JAXBElement<InsertarOperacionCambioOBParameter>(_InsertarTransferenciaRIORIOOBParameter_QNAME, InsertarOperacionCambioOBParameter.class, InsertarOperacionCambioOB.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BPCUENTASCNSMOVIMIENTOSPARAMETER }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "Parameter", scope = BPCUENTASCNSMOVIMIENTOS.class)
    public JAXBElement<BPCUENTASCNSMOVIMIENTOSPARAMETER> createBPCUENTASCNSMOVIMIENTOSParameter(BPCUENTASCNSMOVIMIENTOSPARAMETER value) {
        return new JAXBElement<BPCUENTASCNSMOVIMIENTOSPARAMETER>(_BPCUENTASCMBGRABAMOVParameter_QNAME, BPCUENTASCNSMOVIMIENTOSPARAMETER.class, BPCUENTASCNSMOVIMIENTOS.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "InsertarTransferenciaEntreBancosOBResult", scope = InsertarTransferenciaEntreBancosOBResponse.class)
    public JAXBElement<String> createInsertarTransferenciaEntreBancosOBResponseInsertarTransferenciaEntreBancosOBResult(String value) {
        return new JAXBElement<String>(_InsertarTransferenciaEntreBancosOBResponseInsertarTransferenciaEntreBancosOBResult_QNAME, String.class, InsertarTransferenciaEntreBancosOBResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BPORDENESCMBESTADOPARAMETER }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "Parameter", scope = BPORDENESCMBESTADO.class)
    public JAXBElement<BPORDENESCMBESTADOPARAMETER> createBPORDENESCMBESTADOParameter(BPORDENESCMBESTADOPARAMETER value) {
        return new JAXBElement<BPORDENESCMBESTADOPARAMETER>(_BPCUENTASCMBGRABAMOVParameter_QNAME, BPORDENESCMBESTADOPARAMETER.class, BPORDENESCMBESTADO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "InsertarTransferenciaRIORIOOBResult", scope = InsertarTransferenciaRIORIOOBResponse.class)
    public JAXBElement<String> createInsertarTransferenciaRIORIOOBResponseInsertarTransferenciaRIORIOOBResult(String value) {
        return new JAXBElement<String>(_InsertarTransferenciaRIORIOOBResponseInsertarTransferenciaRIORIOOBResult_QNAME, String.class, InsertarTransferenciaRIORIOOBResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfLoadAtits }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "BP_CUENTAS_CNS_ATITResult", scope = BPCUENTASCNSATITResponse.class)
    public JAXBElement<ArrayOfLoadAtits> createBPCUENTASCNSATITResponseBPCUENTASCNSATITResult(ArrayOfLoadAtits value) {
        return new JAXBElement<ArrayOfLoadAtits>(_BPCUENTASCNSATITResponseBPCUENTASCNSATITResult_QNAME, ArrayOfLoadAtits.class, BPCUENTASCNSATITResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BPCUENTASCNSCUADREPERFILPARAMETER }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "Parameter", scope = BPCUENTASCNSCUADREPERFIL.class)
    public JAXBElement<BPCUENTASCNSCUADREPERFILPARAMETER> createBPCUENTASCNSCUADREPERFILParameter(BPCUENTASCNSCUADREPERFILPARAMETER value) {
        return new JAXBElement<BPCUENTASCNSCUADREPERFILPARAMETER>(_BPCUENTASCMBGRABAMOVParameter_QNAME, BPCUENTASCNSCUADREPERFILPARAMETER.class, BPCUENTASCNSCUADREPERFIL.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InsertarTransferenciaRIORIOParameter }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "parameter", scope = InsertarTransferenciaRIORIO.class)
    public JAXBElement<InsertarTransferenciaRIORIOParameter> createInsertarTransferenciaRIORIOParameter(InsertarTransferenciaRIORIOParameter value) {
        return new JAXBElement<InsertarTransferenciaRIORIOParameter>(_InsertarTransferenciaRIORIOOBParameter_QNAME, InsertarTransferenciaRIORIOParameter.class, InsertarTransferenciaRIORIO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BPCUENTASCNSATITPARAMETER }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "Parameter", scope = BPCUENTASCNSATIT.class)
    public JAXBElement<BPCUENTASCNSATITPARAMETER> createBPCUENTASCNSATITParameter(BPCUENTASCNSATITPARAMETER value) {
        return new JAXBElement<BPCUENTASCNSATITPARAMETER>(_BPCUENTASCMBGRABAMOVParameter_QNAME, BPCUENTASCNSATITPARAMETER.class, BPCUENTASCNSATIT.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "InsertarTransferenciaEntreBancosResult", scope = InsertarTransferenciaEntreBancosResponse.class)
    public JAXBElement<String> createInsertarTransferenciaEntreBancosResponseInsertarTransferenciaEntreBancosResult(String value) {
        return new JAXBElement<String>(_InsertarTransferenciaEntreBancosResponseInsertarTransferenciaEntreBancosResult_QNAME, String.class, InsertarTransferenciaEntreBancosResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "BP_ORDENES_CMB_ALTAResult", scope = BPORDENESCMBALTAResponse.class)
    public JAXBElement<String> createBPORDENESCMBALTAResponseBPORDENESCMBALTAResult(String value) {
        return new JAXBElement<String>(_BPORDENESCMBALTAResponseBPORDENESCMBALTAResult_QNAME, String.class, BPORDENESCMBALTAResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ControlCuadrePerfil }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "BP_CUENTAS_CNS_CUADREPERFILResult", scope = BPCUENTASCNSCUADREPERFILResponse.class)
    public JAXBElement<ControlCuadrePerfil> createBPCUENTASCNSCUADREPERFILResponseBPCUENTASCNSCUADREPERFILResult(ControlCuadrePerfil value) {
        return new JAXBElement<ControlCuadrePerfil>(_BPCUENTASCNSCUADREPERFILResponseBPCUENTASCNSCUADREPERFILResult_QNAME, ControlCuadrePerfil.class, BPCUENTASCNSCUADREPERFILResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfLoadRossi }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "BP_CUENTAS_CNS_ROSSIResult", scope = BPCUENTASCNSROSSIResponse.class)
    public JAXBElement<ArrayOfLoadRossi> createBPCUENTASCNSROSSIResponseBPCUENTASCNSROSSIResult(ArrayOfLoadRossi value) {
        return new JAXBElement<ArrayOfLoadRossi>(_BPCUENTASCNSROSSIResponseBPCUENTASCNSROSSIResult_QNAME, ArrayOfLoadRossi.class, BPCUENTASCNSROSSIResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "InsertarOperacionCambioOBResult", scope = InsertarOperacionCambioOBResponse.class)
    public JAXBElement<String> createInsertarOperacionCambioOBResponseInsertarOperacionCambioOBResult(String value) {
        return new JAXBElement<String>(_InsertarOperacionCambioOBResponseInsertarOperacionCambioOBResult_QNAME, String.class, InsertarOperacionCambioOBResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InsertarTransferenciaEntreBancosParameter }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "parameter", scope = InsertarTransferenciaEntreBancos.class)
    public JAXBElement<InsertarTransferenciaEntreBancosParameter> createInsertarTransferenciaEntreBancosParameter(InsertarTransferenciaEntreBancosParameter value) {
        return new JAXBElement<InsertarTransferenciaEntreBancosParameter>(_InsertarTransferenciaRIORIOOBParameter_QNAME, InsertarTransferenciaEntreBancosParameter.class, InsertarTransferenciaEntreBancos.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfSaldo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "BP_CUENTAS_CNS_SALDOSResult", scope = BPCUENTASCNSSALDOSResponse.class)
    public JAXBElement<ArrayOfSaldo> createBPCUENTASCNSSALDOSResponseBPCUENTASCNSSALDOSResult(ArrayOfSaldo value) {
        return new JAXBElement<ArrayOfSaldo>(_BPCUENTASCNSSALDOSResponseBPCUENTASCNSSALDOSResult_QNAME, ArrayOfSaldo.class, BPCUENTASCNSSALDOSResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GrabamovAltair }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "BP_CUENTAS_CMB_GRABAMOVResult", scope = BPCUENTASCMBGRABAMOVResponse.class)
    public JAXBElement<GrabamovAltair> createBPCUENTASCMBGRABAMOVResponseBPCUENTASCMBGRABAMOVResult(GrabamovAltair value) {
        return new JAXBElement<GrabamovAltair>(_BPCUENTASCMBGRABAMOVResponseBPCUENTASCMBGRABAMOVResult_QNAME, GrabamovAltair.class, BPCUENTASCMBGRABAMOVResponse.class, value);
    }

}
