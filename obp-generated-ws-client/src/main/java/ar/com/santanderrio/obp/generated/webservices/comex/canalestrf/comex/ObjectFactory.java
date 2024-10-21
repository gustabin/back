
package ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.request.canales.BorrarDocRequest;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.request.canales.BuscarDespachosRequest;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.request.canales.CargaAgendaCuentaRequest;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.request.canales.CargaAgendaRequest;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.request.canales.CargaDespachosRequest;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.request.canales.CargaDocRequest;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.request.canales.ClonarImagenesRequest;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.request.canales.ConceptosPorTipoRequest;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.request.canales.ConsultaAgendaCuentaRequest;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.request.canales.ConsultaConceptoRequest;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.request.canales.ConsultaDetalleTrfOBPRequest;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.request.canales.ConsultaDetalleTrfRequest;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.request.canales.ConsultaImagenTrfRequest;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.request.canales.ConsultaMotExcepcionRequest;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.request.canales.ConsultaNifRequest;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.request.canales.ConsultaOperacionesRequest;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.request.canales.ConsultaTiposConceptoRequest;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.request.canales.DesvinculaAgendaCuentaRequest;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.request.canales.DesvinculaAgendaRequest;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.request.canales.ObtenerMsgSwiftRequest;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.request.canales.ProcesarNifRequest;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.request.canales.ProcesarTransferenciaOBPRequest;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.request.canales.ProcesarTrfBPMRequest;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.request.canales.ProcesarTrfRequest;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.request.canales.ValidarNIFxNUPRequest;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.request.canales.ValidarPosicionRequest;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex package. 
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

    private final static QName _ConsultaDetalleTrfRequest_QNAME = new QName("Comex", "request");
    private final static QName _ConsultaTiposConceptoResponseConsultaTiposConceptoResult_QNAME = new QName("Comex", "ConsultaTiposConceptoResult");
    private final static QName _ConceptosPorTipoResponseConceptosPorTipoResult_QNAME = new QName("Comex", "ConceptosPorTipoResult");
    private final static QName _ConsultaAgendaCuentaResponseConsultaAgendaCuentaResult_QNAME = new QName("Comex", "ConsultaAgendaCuentaResult");
    private final static QName _ConsultaOperacionesResponseConsultaOperacionesResult_QNAME = new QName("Comex", "ConsultaOperacionesResult");
    private final static QName _DesvinculaAgendaResponseDesvinculaAgendaResult_QNAME = new QName("Comex", "DesvinculaAgendaResult");
    private final static QName _BorrarDocResponseBorrarDocResult_QNAME = new QName("Comex", "BorrarDocResult");
    private final static QName _ClonarImagenesResponseClonarImagenesResult_QNAME = new QName("Comex", "ClonarImagenesResult");
    private final static QName _ConsultaMotExcepcionResponseConsultaMotExcepcionResult_QNAME = new QName("Comex", "ConsultaMotExcepcionResult");
    private final static QName _CargaAgendaCuentaResponseCargaAgendaCuentaResult_QNAME = new QName("Comex", "CargaAgendaCuentaResult");
    private final static QName _ProcesarTransferenciaOBPResponseProcesarTransferenciaOBPResult_QNAME = new QName("Comex", "ProcesarTransferenciaOBPResult");
    private final static QName _ObtenerMsgSwiftResponseObtenerMsgSwiftResult_QNAME = new QName("Comex", "ObtenerMsgSwiftResult");
    private final static QName _ConsultaDetalleTrfOBPResponseConsultaDetalleTrfOBPResult_QNAME = new QName("Comex", "ConsultaDetalleTrfOBPResult");
    private final static QName _ProcesarTransferenciaResponseProcesarTransferenciaResult_QNAME = new QName("Comex", "ProcesarTransferenciaResult");
    private final static QName _ValidarPosicionResponseValidarPosicionResult_QNAME = new QName("Comex", "ValidarPosicionResult");
    private final static QName _ConsultaNifResponseConsultaNifResult_QNAME = new QName("Comex", "ConsultaNifResult");
    private final static QName _ProcesarTransferenciaBPMResponseProcesarTransferenciaBPMResult_QNAME = new QName("Comex", "ProcesarTransferenciaBPMResult");
    private final static QName _CargaAgendaResponseCargaAgendaResult_QNAME = new QName("Comex", "CargaAgendaResult");
    private final static QName _DesvinculaAgendaCuentaResponseDesvinculaAgendaCuentaResult_QNAME = new QName("Comex", "DesvinculaAgendaCuentaResult");
    private final static QName _ValidarNIFxNUPResponseValidarNIFxNUPResult_QNAME = new QName("Comex", "ValidarNIFxNUPResult");
    private final static QName _ConsultaDetalleTrfResponseConsultaDetalleTrfResult_QNAME = new QName("Comex", "ConsultaDetalleTrfResult");
    private final static QName _CargaDocResponseCargaDocResult_QNAME = new QName("Comex", "CargaDocResult");
    private final static QName _BuscarDespachosResponseBuscarDespachosResult_QNAME = new QName("Comex", "BuscarDespachosResult");
    private final static QName _ConsultaConceptoResponseConsultaConceptoResult_QNAME = new QName("Comex", "ConsultaConceptoResult");
    private final static QName _ProcesarNifResponseProcesarNifResult_QNAME = new QName("Comex", "ProcesarNifResult");
    private final static QName _ConsultaImagenTrfResponseConsultaImagenTrfResult_QNAME = new QName("Comex", "ConsultaImagenTrfResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.ConsultaImagenTrfResponse }
     * 
     */
    public ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.ConsultaImagenTrfResponse createConsultaImagenTrfResponse() {
        return new ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.ConsultaImagenTrfResponse();
    }

    /**
     * Create an instance of {@link ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.CargaAgendaCuentaResponse }
     * 
     */
    public ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.CargaAgendaCuentaResponse createCargaAgendaCuentaResponse() {
        return new ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.CargaAgendaCuentaResponse();
    }

    /**
     * Create an instance of {@link ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.ConsultaDetalleTrfResponse }
     * 
     */
    public ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.ConsultaDetalleTrfResponse createConsultaDetalleTrfResponse() {
        return new ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.ConsultaDetalleTrfResponse();
    }

    /**
     * Create an instance of {@link ValidarPosicion }
     * 
     */
    public ValidarPosicion createValidarPosicion() {
        return new ValidarPosicion();
    }

    /**
     * Create an instance of {@link ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.ConsultaAgendaCuentaResponse }
     * 
     */
    public ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.ConsultaAgendaCuentaResponse createConsultaAgendaCuentaResponse() {
        return new ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.ConsultaAgendaCuentaResponse();
    }

    /**
     * Create an instance of {@link ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.ObtenerMsgSwiftResponse }
     * 
     */
    public ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.ObtenerMsgSwiftResponse createObtenerMsgSwiftResponse() {
        return new ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.ObtenerMsgSwiftResponse();
    }

    /**
     * Create an instance of {@link ProcesarTransferenciaOBP }
     * 
     */
    public ProcesarTransferenciaOBP createProcesarTransferenciaOBP() {
        return new ProcesarTransferenciaOBP();
    }

    /**
     * Create an instance of {@link ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.ValidarNIFxNUPResponse }
     * 
     */
    public ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.ValidarNIFxNUPResponse createValidarNIFxNUPResponse() {
        return new ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.ValidarNIFxNUPResponse();
    }

    /**
     * Create an instance of {@link ProcesarTransferencia }
     * 
     */
    public ProcesarTransferencia createProcesarTransferencia() {
        return new ProcesarTransferencia();
    }

    /**
     * Create an instance of {@link ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.ConsultaNifResponse }
     * 
     */
    public ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.ConsultaNifResponse createConsultaNifResponse() {
        return new ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.ConsultaNifResponse();
    }

    /**
     * Create an instance of {@link ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.CargaAgendaResponse }
     * 
     */
    public ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.CargaAgendaResponse createCargaAgendaResponse() {
        return new ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.CargaAgendaResponse();
    }

    /**
     * Create an instance of {@link ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.ConsultaConceptoResponse }
     * 
     */
    public ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.ConsultaConceptoResponse createConsultaConceptoResponse() {
        return new ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.ConsultaConceptoResponse();
    }

    /**
     * Create an instance of {@link CargaDespachos }
     * 
     */
    public CargaDespachos createCargaDespachos() {
        return new CargaDespachos();
    }

    /**
     * Create an instance of {@link ConsultaImagenTrf }
     * 
     */
    public ConsultaImagenTrf createConsultaImagenTrf() {
        return new ConsultaImagenTrf();
    }

    /**
     * Create an instance of {@link CargaAgendaCuenta }
     * 
     */
    public CargaAgendaCuenta createCargaAgendaCuenta() {
        return new CargaAgendaCuenta();
    }

    /**
     * Create an instance of {@link ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.ConsultaMotExcepcionResponse }
     * 
     */
    public ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.ConsultaMotExcepcionResponse createConsultaMotExcepcionResponse() {
        return new ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.ConsultaMotExcepcionResponse();
    }

    /**
     * Create an instance of {@link ConsultaDetalleTrf }
     * 
     */
    public ConsultaDetalleTrf createConsultaDetalleTrf() {
        return new ConsultaDetalleTrf();
    }

    /**
     * Create an instance of {@link ConsultaAgendaCuenta }
     * 
     */
    public ConsultaAgendaCuenta createConsultaAgendaCuenta() {
        return new ConsultaAgendaCuenta();
    }

    /**
     * Create an instance of {@link ObtenerMsgSwift }
     * 
     */
    public ObtenerMsgSwift createObtenerMsgSwift() {
        return new ObtenerMsgSwift();
    }

    /**
     * Create an instance of {@link ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.CargaDocResponse }
     * 
     */
    public ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.CargaDocResponse createCargaDocResponse() {
        return new ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.CargaDocResponse();
    }

    /**
     * Create an instance of {@link ValidarNIFxNUP }
     * 
     */
    public ValidarNIFxNUP createValidarNIFxNUP() {
        return new ValidarNIFxNUP();
    }

    /**
     * Create an instance of {@link ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.ConsultaTiposConceptoResponse }
     * 
     */
    public ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.ConsultaTiposConceptoResponse createConsultaTiposConceptoResponse() {
        return new ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.ConsultaTiposConceptoResponse();
    }

    /**
     * Create an instance of {@link ConsultaNif }
     * 
     */
    public ConsultaNif createConsultaNif() {
        return new ConsultaNif();
    }

    /**
     * Create an instance of {@link CargaAgenda }
     * 
     */
    public CargaAgenda createCargaAgenda() {
        return new CargaAgenda();
    }

    /**
     * Create an instance of {@link ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.ClonarImagenesResponse }
     * 
     */
    public ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.ClonarImagenesResponse createClonarImagenesResponse() {
        return new ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.ClonarImagenesResponse();
    }

    /**
     * Create an instance of {@link ConsultaConcepto }
     * 
     */
    public ConsultaConcepto createConsultaConcepto() {
        return new ConsultaConcepto();
    }

    /**
     * Create an instance of {@link ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.ConceptosPorTipoResponse }
     * 
     */
    public ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.ConceptosPorTipoResponse createConceptosPorTipoResponse() {
        return new ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.ConceptosPorTipoResponse();
    }

    /**
     * Create an instance of {@link ConsultaMotExcepcion }
     * 
     */
    public ConsultaMotExcepcion createConsultaMotExcepcion() {
        return new ConsultaMotExcepcion();
    }

    /**
     * Create an instance of {@link ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.DesvinculaAgendaCuentaResponse }
     * 
     */
    public ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.DesvinculaAgendaCuentaResponse createDesvinculaAgendaCuentaResponse() {
        return new ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.DesvinculaAgendaCuentaResponse();
    }

    /**
     * Create an instance of {@link CargaDoc }
     * 
     */
    public CargaDoc createCargaDoc() {
        return new CargaDoc();
    }

    /**
     * Create an instance of {@link ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.ProcesarTransferenciaBPMResponse }
     * 
     */
    public ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.ProcesarTransferenciaBPMResponse createProcesarTransferenciaBPMResponse() {
        return new ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.ProcesarTransferenciaBPMResponse();
    }

    /**
     * Create an instance of {@link ConsultaTiposConcepto }
     * 
     */
    public ConsultaTiposConcepto createConsultaTiposConcepto() {
        return new ConsultaTiposConcepto();
    }

    /**
     * Create an instance of {@link ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.ConsultaOperacionesResponse }
     * 
     */
    public ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.ConsultaOperacionesResponse createConsultaOperacionesResponse() {
        return new ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.ConsultaOperacionesResponse();
    }

    /**
     * Create an instance of {@link ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.ProcesarNifResponse }
     * 
     */
    public ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.ProcesarNifResponse createProcesarNifResponse() {
        return new ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.ProcesarNifResponse();
    }

    /**
     * Create an instance of {@link ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.ConsultaDetalleTrfOBPResponse }
     * 
     */
    public ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.ConsultaDetalleTrfOBPResponse createConsultaDetalleTrfOBPResponse() {
        return new ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.ConsultaDetalleTrfOBPResponse();
    }

    /**
     * Create an instance of {@link ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.BuscarDespachosResponse }
     * 
     */
    public ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.BuscarDespachosResponse createBuscarDespachosResponse() {
        return new ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.BuscarDespachosResponse();
    }

    /**
     * Create an instance of {@link ClonarImagenes }
     * 
     */
    public ClonarImagenes createClonarImagenes() {
        return new ClonarImagenes();
    }

    /**
     * Create an instance of {@link ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.BorrarDocResponse }
     * 
     */
    public ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.BorrarDocResponse createBorrarDocResponse() {
        return new ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.BorrarDocResponse();
    }

    /**
     * Create an instance of {@link ConceptosPorTipo }
     * 
     */
    public ConceptosPorTipo createConceptosPorTipo() {
        return new ConceptosPorTipo();
    }

    /**
     * Create an instance of {@link ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.DesvinculaAgendaResponse }
     * 
     */
    public ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.DesvinculaAgendaResponse createDesvinculaAgendaResponse() {
        return new ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.DesvinculaAgendaResponse();
    }

    /**
     * Create an instance of {@link DesvinculaAgendaCuenta }
     * 
     */
    public DesvinculaAgendaCuenta createDesvinculaAgendaCuenta() {
        return new DesvinculaAgendaCuenta();
    }

    /**
     * Create an instance of {@link ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.ValidarPosicionResponse }
     * 
     */
    public ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.ValidarPosicionResponse createValidarPosicionResponse() {
        return new ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.ValidarPosicionResponse();
    }

    /**
     * Create an instance of {@link ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.ProcesarTransferenciaOBPResponse }
     * 
     */
    public ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.ProcesarTransferenciaOBPResponse createProcesarTransferenciaOBPResponse() {
        return new ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.ProcesarTransferenciaOBPResponse();
    }

    /**
     * Create an instance of {@link ProcesarTransferenciaBPM }
     * 
     */
    public ProcesarTransferenciaBPM createProcesarTransferenciaBPM() {
        return new ProcesarTransferenciaBPM();
    }

    /**
     * Create an instance of {@link ConsultaOperaciones }
     * 
     */
    public ConsultaOperaciones createConsultaOperaciones() {
        return new ConsultaOperaciones();
    }

    /**
     * Create an instance of {@link ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.ProcesarTransferenciaResponse }
     * 
     */
    public ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.ProcesarTransferenciaResponse createProcesarTransferenciaResponse() {
        return new ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.ProcesarTransferenciaResponse();
    }

    /**
     * Create an instance of {@link ProcesarNif }
     * 
     */
    public ProcesarNif createProcesarNif() {
        return new ProcesarNif();
    }

    /**
     * Create an instance of {@link ConsultaDetalleTrfOBP }
     * 
     */
    public ConsultaDetalleTrfOBP createConsultaDetalleTrfOBP() {
        return new ConsultaDetalleTrfOBP();
    }

    /**
     * Create an instance of {@link BuscarDespachos }
     * 
     */
    public BuscarDespachos createBuscarDespachos() {
        return new BuscarDespachos();
    }

    /**
     * Create an instance of {@link BorrarDoc }
     * 
     */
    public BorrarDoc createBorrarDoc() {
        return new BorrarDoc();
    }

    /**
     * Create an instance of {@link DesvinculaAgenda }
     * 
     */
    public DesvinculaAgenda createDesvinculaAgenda() {
        return new DesvinculaAgenda();
    }

    /**
     * Create an instance of {@link CargaDespachosResponse }
     * 
     */
    public CargaDespachosResponse createCargaDespachosResponse() {
        return new CargaDespachosResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultaDetalleTrfRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Comex", name = "request", scope = ConsultaDetalleTrf.class)
    public JAXBElement<ConsultaDetalleTrfRequest> createConsultaDetalleTrfRequest(ConsultaDetalleTrfRequest value) {
        return new JAXBElement<ConsultaDetalleTrfRequest>(_ConsultaDetalleTrfRequest_QNAME, ConsultaDetalleTrfRequest.class, ConsultaDetalleTrf.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ConsultaTiposConceptoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Comex", name = "ConsultaTiposConceptoResult", scope = ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.ConsultaTiposConceptoResponse.class)
    public JAXBElement<ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ConsultaTiposConceptoResponse> createConsultaTiposConceptoResponseConsultaTiposConceptoResult(ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ConsultaTiposConceptoResponse value) {
        return new JAXBElement<ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ConsultaTiposConceptoResponse>(_ConsultaTiposConceptoResponseConsultaTiposConceptoResult_QNAME, ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ConsultaTiposConceptoResponse.class, ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.ConsultaTiposConceptoResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ConceptosPorTipoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Comex", name = "ConceptosPorTipoResult", scope = ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.ConceptosPorTipoResponse.class)
    public JAXBElement<ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ConceptosPorTipoResponse> createConceptosPorTipoResponseConceptosPorTipoResult(ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ConceptosPorTipoResponse value) {
        return new JAXBElement<ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ConceptosPorTipoResponse>(_ConceptosPorTipoResponseConceptosPorTipoResult_QNAME, ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ConceptosPorTipoResponse.class, ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.ConceptosPorTipoResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultaTiposConceptoRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Comex", name = "request", scope = ConsultaTiposConcepto.class)
    public JAXBElement<ConsultaTiposConceptoRequest> createConsultaTiposConceptoRequest(ConsultaTiposConceptoRequest value) {
        return new JAXBElement<ConsultaTiposConceptoRequest>(_ConsultaDetalleTrfRequest_QNAME, ConsultaTiposConceptoRequest.class, ConsultaTiposConcepto.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ConsultaAgendaCuentaResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Comex", name = "ConsultaAgendaCuentaResult", scope = ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.ConsultaAgendaCuentaResponse.class)
    public JAXBElement<ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ConsultaAgendaCuentaResponse> createConsultaAgendaCuentaResponseConsultaAgendaCuentaResult(ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ConsultaAgendaCuentaResponse value) {
        return new JAXBElement<ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ConsultaAgendaCuentaResponse>(_ConsultaAgendaCuentaResponseConsultaAgendaCuentaResult_QNAME, ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ConsultaAgendaCuentaResponse.class, ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.ConsultaAgendaCuentaResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ConsultaOperacionesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Comex", name = "ConsultaOperacionesResult", scope = ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.ConsultaOperacionesResponse.class)
    public JAXBElement<ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ConsultaOperacionesResponse> createConsultaOperacionesResponseConsultaOperacionesResult(ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ConsultaOperacionesResponse value) {
        return new JAXBElement<ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ConsultaOperacionesResponse>(_ConsultaOperacionesResponseConsultaOperacionesResult_QNAME, ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ConsultaOperacionesResponse.class, ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.ConsultaOperacionesResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultaOperacionesRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Comex", name = "request", scope = ConsultaOperaciones.class)
    public JAXBElement<ConsultaOperacionesRequest> createConsultaOperacionesRequest(ConsultaOperacionesRequest value) {
        return new JAXBElement<ConsultaOperacionesRequest>(_ConsultaDetalleTrfRequest_QNAME, ConsultaOperacionesRequest.class, ConsultaOperaciones.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.DesvinculaAgendaResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Comex", name = "DesvinculaAgendaResult", scope = ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.DesvinculaAgendaResponse.class)
    public JAXBElement<ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.DesvinculaAgendaResponse> createDesvinculaAgendaResponseDesvinculaAgendaResult(ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.DesvinculaAgendaResponse value) {
        return new JAXBElement<ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.DesvinculaAgendaResponse>(_DesvinculaAgendaResponseDesvinculaAgendaResult_QNAME, ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.DesvinculaAgendaResponse.class, ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.DesvinculaAgendaResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.BorrarDocResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Comex", name = "BorrarDocResult", scope = ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.BorrarDocResponse.class)
    public JAXBElement<ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.BorrarDocResponse> createBorrarDocResponseBorrarDocResult(ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.BorrarDocResponse value) {
        return new JAXBElement<ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.BorrarDocResponse>(_BorrarDocResponseBorrarDocResult_QNAME, ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.BorrarDocResponse.class, ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.BorrarDocResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ClonarImagenesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Comex", name = "ClonarImagenesResult", scope = ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.ClonarImagenesResponse.class)
    public JAXBElement<ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ClonarImagenesResponse> createClonarImagenesResponseClonarImagenesResult(ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ClonarImagenesResponse value) {
        return new JAXBElement<ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ClonarImagenesResponse>(_ClonarImagenesResponseClonarImagenesResult_QNAME, ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ClonarImagenesResponse.class, ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.ClonarImagenesResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ConsultaMotExcepcionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Comex", name = "ConsultaMotExcepcionResult", scope = ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.ConsultaMotExcepcionResponse.class)
    public JAXBElement<ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ConsultaMotExcepcionResponse> createConsultaMotExcepcionResponseConsultaMotExcepcionResult(ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ConsultaMotExcepcionResponse value) {
        return new JAXBElement<ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ConsultaMotExcepcionResponse>(_ConsultaMotExcepcionResponseConsultaMotExcepcionResult_QNAME, ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ConsultaMotExcepcionResponse.class, ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.ConsultaMotExcepcionResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.CargaAgendaCuentaResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Comex", name = "CargaAgendaCuentaResult", scope = ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.CargaAgendaCuentaResponse.class)
    public JAXBElement<ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.CargaAgendaCuentaResponse> createCargaAgendaCuentaResponseCargaAgendaCuentaResult(ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.CargaAgendaCuentaResponse value) {
        return new JAXBElement<ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.CargaAgendaCuentaResponse>(_CargaAgendaCuentaResponseCargaAgendaCuentaResult_QNAME, ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.CargaAgendaCuentaResponse.class, ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.CargaAgendaCuentaResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CargaAgendaRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Comex", name = "request", scope = CargaAgenda.class)
    public JAXBElement<CargaAgendaRequest> createCargaAgendaRequest(CargaAgendaRequest value) {
        return new JAXBElement<CargaAgendaRequest>(_ConsultaDetalleTrfRequest_QNAME, CargaAgendaRequest.class, CargaAgenda.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CargaDocRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Comex", name = "request", scope = CargaDoc.class)
    public JAXBElement<CargaDocRequest> createCargaDocRequest(CargaDocRequest value) {
        return new JAXBElement<CargaDocRequest>(_ConsultaDetalleTrfRequest_QNAME, CargaDocRequest.class, CargaDoc.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BuscarDespachosRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Comex", name = "request", scope = BuscarDespachos.class)
    public JAXBElement<BuscarDespachosRequest> createBuscarDespachosRequest(BuscarDespachosRequest value) {
        return new JAXBElement<BuscarDespachosRequest>(_ConsultaDetalleTrfRequest_QNAME, BuscarDespachosRequest.class, BuscarDespachos.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ProcesarTransferenciaOBPResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Comex", name = "ProcesarTransferenciaOBPResult", scope = ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.ProcesarTransferenciaOBPResponse.class)
    public JAXBElement<ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ProcesarTransferenciaOBPResponse> createProcesarTransferenciaOBPResponseProcesarTransferenciaOBPResult(ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ProcesarTransferenciaOBPResponse value) {
        return new JAXBElement<ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ProcesarTransferenciaOBPResponse>(_ProcesarTransferenciaOBPResponseProcesarTransferenciaOBPResult_QNAME, ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ProcesarTransferenciaOBPResponse.class, ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.ProcesarTransferenciaOBPResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultaAgendaCuentaRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Comex", name = "request", scope = ConsultaAgendaCuenta.class)
    public JAXBElement<ConsultaAgendaCuentaRequest> createConsultaAgendaCuentaRequest(ConsultaAgendaCuentaRequest value) {
        return new JAXBElement<ConsultaAgendaCuentaRequest>(_ConsultaDetalleTrfRequest_QNAME, ConsultaAgendaCuentaRequest.class, ConsultaAgendaCuenta.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ProcesarTransferenciaOBPRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Comex", name = "request", scope = ProcesarTransferenciaOBP.class)
    public JAXBElement<ProcesarTransferenciaOBPRequest> createProcesarTransferenciaOBPRequest(ProcesarTransferenciaOBPRequest value) {
        return new JAXBElement<ProcesarTransferenciaOBPRequest>(_ConsultaDetalleTrfRequest_QNAME, ProcesarTransferenciaOBPRequest.class, ProcesarTransferenciaOBP.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultaDetalleTrfOBPRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Comex", name = "request", scope = ConsultaDetalleTrfOBP.class)
    public JAXBElement<ConsultaDetalleTrfOBPRequest> createConsultaDetalleTrfOBPRequest(ConsultaDetalleTrfOBPRequest value) {
        return new JAXBElement<ConsultaDetalleTrfOBPRequest>(_ConsultaDetalleTrfRequest_QNAME, ConsultaDetalleTrfOBPRequest.class, ConsultaDetalleTrfOBP.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ObtenerMsgSwiftResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Comex", name = "ObtenerMsgSwiftResult", scope = ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.ObtenerMsgSwiftResponse.class)
    public JAXBElement<ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ObtenerMsgSwiftResponse> createObtenerMsgSwiftResponseObtenerMsgSwiftResult(ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ObtenerMsgSwiftResponse value) {
        return new JAXBElement<ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ObtenerMsgSwiftResponse>(_ObtenerMsgSwiftResponseObtenerMsgSwiftResult_QNAME, ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ObtenerMsgSwiftResponse.class, ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.ObtenerMsgSwiftResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ConsultaDetalleTrfOBPResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Comex", name = "ConsultaDetalleTrfOBPResult", scope = ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.ConsultaDetalleTrfOBPResponse.class)
    public JAXBElement<ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ConsultaDetalleTrfOBPResponse> createConsultaDetalleTrfOBPResponseConsultaDetalleTrfOBPResult(ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ConsultaDetalleTrfOBPResponse value) {
        return new JAXBElement<ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ConsultaDetalleTrfOBPResponse>(_ConsultaDetalleTrfOBPResponseConsultaDetalleTrfOBPResult_QNAME, ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ConsultaDetalleTrfOBPResponse.class, ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.ConsultaDetalleTrfOBPResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultaImagenTrfRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Comex", name = "request", scope = ConsultaImagenTrf.class)
    public JAXBElement<ConsultaImagenTrfRequest> createConsultaImagenTrfRequest(ConsultaImagenTrfRequest value) {
        return new JAXBElement<ConsultaImagenTrfRequest>(_ConsultaDetalleTrfRequest_QNAME, ConsultaImagenTrfRequest.class, ConsultaImagenTrf.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ProcesarTransferenciaResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Comex", name = "ProcesarTransferenciaResult", scope = ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.ProcesarTransferenciaResponse.class)
    public JAXBElement<ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ProcesarTransferenciaResponse> createProcesarTransferenciaResponseProcesarTransferenciaResult(ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ProcesarTransferenciaResponse value) {
        return new JAXBElement<ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ProcesarTransferenciaResponse>(_ProcesarTransferenciaResponseProcesarTransferenciaResult_QNAME, ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ProcesarTransferenciaResponse.class, ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.ProcesarTransferenciaResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ValidarPosicionRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Comex", name = "request", scope = ValidarPosicion.class)
    public JAXBElement<ValidarPosicionRequest> createValidarPosicionRequest(ValidarPosicionRequest value) {
        return new JAXBElement<ValidarPosicionRequest>(_ConsultaDetalleTrfRequest_QNAME, ValidarPosicionRequest.class, ValidarPosicion.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ValidarNIFxNUPRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Comex", name = "request", scope = ValidarNIFxNUP.class)
    public JAXBElement<ValidarNIFxNUPRequest> createValidarNIFxNUPRequest(ValidarNIFxNUPRequest value) {
        return new JAXBElement<ValidarNIFxNUPRequest>(_ConsultaDetalleTrfRequest_QNAME, ValidarNIFxNUPRequest.class, ValidarNIFxNUP.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ValidarPosicionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Comex", name = "ValidarPosicionResult", scope = ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.ValidarPosicionResponse.class)
    public JAXBElement<ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ValidarPosicionResponse> createValidarPosicionResponseValidarPosicionResult(ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ValidarPosicionResponse value) {
        return new JAXBElement<ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ValidarPosicionResponse>(_ValidarPosicionResponseValidarPosicionResult_QNAME, ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ValidarPosicionResponse.class, ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.ValidarPosicionResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ConsultaNifResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Comex", name = "ConsultaNifResult", scope = ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.ConsultaNifResponse.class)
    public JAXBElement<ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ConsultaNifResponse> createConsultaNifResponseConsultaNifResult(ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ConsultaNifResponse value) {
        return new JAXBElement<ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ConsultaNifResponse>(_ConsultaNifResponseConsultaNifResult_QNAME, ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ConsultaNifResponse.class, ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.ConsultaNifResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ProcesarTransferenciaBPMResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Comex", name = "ProcesarTransferenciaBPMResult", scope = ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.ProcesarTransferenciaBPMResponse.class)
    public JAXBElement<ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ProcesarTransferenciaBPMResponse> createProcesarTransferenciaBPMResponseProcesarTransferenciaBPMResult(ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ProcesarTransferenciaBPMResponse value) {
        return new JAXBElement<ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ProcesarTransferenciaBPMResponse>(_ProcesarTransferenciaBPMResponseProcesarTransferenciaBPMResult_QNAME, ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ProcesarTransferenciaBPMResponse.class, ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.ProcesarTransferenciaBPMResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObtenerMsgSwiftRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Comex", name = "request", scope = ObtenerMsgSwift.class)
    public JAXBElement<ObtenerMsgSwiftRequest> createObtenerMsgSwiftRequest(ObtenerMsgSwiftRequest value) {
        return new JAXBElement<ObtenerMsgSwiftRequest>(_ConsultaDetalleTrfRequest_QNAME, ObtenerMsgSwiftRequest.class, ObtenerMsgSwift.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultaConceptoRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Comex", name = "request", scope = ConsultaConcepto.class)
    public JAXBElement<ConsultaConceptoRequest> createConsultaConceptoRequest(ConsultaConceptoRequest value) {
        return new JAXBElement<ConsultaConceptoRequest>(_ConsultaDetalleTrfRequest_QNAME, ConsultaConceptoRequest.class, ConsultaConcepto.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.CargaAgendaResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Comex", name = "CargaAgendaResult", scope = ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.CargaAgendaResponse.class)
    public JAXBElement<ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.CargaAgendaResponse> createCargaAgendaResponseCargaAgendaResult(ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.CargaAgendaResponse value) {
        return new JAXBElement<ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.CargaAgendaResponse>(_CargaAgendaResponseCargaAgendaResult_QNAME, ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.CargaAgendaResponse.class, ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.CargaAgendaResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DesvinculaAgendaCuentaRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Comex", name = "request", scope = DesvinculaAgendaCuenta.class)
    public JAXBElement<DesvinculaAgendaCuentaRequest> createDesvinculaAgendaCuentaRequest(DesvinculaAgendaCuentaRequest value) {
        return new JAXBElement<DesvinculaAgendaCuentaRequest>(_ConsultaDetalleTrfRequest_QNAME, DesvinculaAgendaCuentaRequest.class, DesvinculaAgendaCuenta.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ProcesarTrfRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Comex", name = "request", scope = ProcesarTransferencia.class)
    public JAXBElement<ProcesarTrfRequest> createProcesarTransferenciaRequest(ProcesarTrfRequest value) {
        return new JAXBElement<ProcesarTrfRequest>(_ConsultaDetalleTrfRequest_QNAME, ProcesarTrfRequest.class, ProcesarTransferencia.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.DesvinculaAgendaCuentaResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Comex", name = "DesvinculaAgendaCuentaResult", scope = ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.DesvinculaAgendaCuentaResponse.class)
    public JAXBElement<ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.DesvinculaAgendaCuentaResponse> createDesvinculaAgendaCuentaResponseDesvinculaAgendaCuentaResult(ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.DesvinculaAgendaCuentaResponse value) {
        return new JAXBElement<ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.DesvinculaAgendaCuentaResponse>(_DesvinculaAgendaCuentaResponseDesvinculaAgendaCuentaResult_QNAME, ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.DesvinculaAgendaCuentaResponse.class, ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.DesvinculaAgendaCuentaResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ValidarNIFxNUPResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Comex", name = "ValidarNIFxNUPResult", scope = ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.ValidarNIFxNUPResponse.class)
    public JAXBElement<ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ValidarNIFxNUPResponse> createValidarNIFxNUPResponseValidarNIFxNUPResult(ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ValidarNIFxNUPResponse value) {
        return new JAXBElement<ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ValidarNIFxNUPResponse>(_ValidarNIFxNUPResponseValidarNIFxNUPResult_QNAME, ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ValidarNIFxNUPResponse.class, ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.ValidarNIFxNUPResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ProcesarTrfBPMRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Comex", name = "request", scope = ProcesarTransferenciaBPM.class)
    public JAXBElement<ProcesarTrfBPMRequest> createProcesarTransferenciaBPMRequest(ProcesarTrfBPMRequest value) {
        return new JAXBElement<ProcesarTrfBPMRequest>(_ConsultaDetalleTrfRequest_QNAME, ProcesarTrfBPMRequest.class, ProcesarTransferenciaBPM.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConceptosPorTipoRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Comex", name = "request", scope = ConceptosPorTipo.class)
    public JAXBElement<ConceptosPorTipoRequest> createConceptosPorTipoRequest(ConceptosPorTipoRequest value) {
        return new JAXBElement<ConceptosPorTipoRequest>(_ConsultaDetalleTrfRequest_QNAME, ConceptosPorTipoRequest.class, ConceptosPorTipo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DesvinculaAgendaRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Comex", name = "request", scope = DesvinculaAgenda.class)
    public JAXBElement<DesvinculaAgendaRequest> createDesvinculaAgendaRequest(DesvinculaAgendaRequest value) {
        return new JAXBElement<DesvinculaAgendaRequest>(_ConsultaDetalleTrfRequest_QNAME, DesvinculaAgendaRequest.class, DesvinculaAgenda.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ConsultaDetalleTrfResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Comex", name = "ConsultaDetalleTrfResult", scope = ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.ConsultaDetalleTrfResponse.class)
    public JAXBElement<ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ConsultaDetalleTrfResponse> createConsultaDetalleTrfResponseConsultaDetalleTrfResult(ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ConsultaDetalleTrfResponse value) {
        return new JAXBElement<ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ConsultaDetalleTrfResponse>(_ConsultaDetalleTrfResponseConsultaDetalleTrfResult_QNAME, ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ConsultaDetalleTrfResponse.class, ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.ConsultaDetalleTrfResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CargaAgendaCuentaRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Comex", name = "request", scope = CargaAgendaCuenta.class)
    public JAXBElement<CargaAgendaCuentaRequest> createCargaAgendaCuentaRequest(CargaAgendaCuentaRequest value) {
        return new JAXBElement<CargaAgendaCuentaRequest>(_ConsultaDetalleTrfRequest_QNAME, CargaAgendaCuentaRequest.class, CargaAgendaCuenta.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultaMotExcepcionRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Comex", name = "request", scope = ConsultaMotExcepcion.class)
    public JAXBElement<ConsultaMotExcepcionRequest> createConsultaMotExcepcionRequest(ConsultaMotExcepcionRequest value) {
        return new JAXBElement<ConsultaMotExcepcionRequest>(_ConsultaDetalleTrfRequest_QNAME, ConsultaMotExcepcionRequest.class, ConsultaMotExcepcion.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ProcesarNifRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Comex", name = "request", scope = ProcesarNif.class)
    public JAXBElement<ProcesarNifRequest> createProcesarNifRequest(ProcesarNifRequest value) {
        return new JAXBElement<ProcesarNifRequest>(_ConsultaDetalleTrfRequest_QNAME, ProcesarNifRequest.class, ProcesarNif.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BorrarDocRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Comex", name = "request", scope = BorrarDoc.class)
    public JAXBElement<BorrarDocRequest> createBorrarDocRequest(BorrarDocRequest value) {
        return new JAXBElement<BorrarDocRequest>(_ConsultaDetalleTrfRequest_QNAME, BorrarDocRequest.class, BorrarDoc.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.CargaDocResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Comex", name = "CargaDocResult", scope = ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.CargaDocResponse.class)
    public JAXBElement<ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.CargaDocResponse> createCargaDocResponseCargaDocResult(ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.CargaDocResponse value) {
        return new JAXBElement<ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.CargaDocResponse>(_CargaDocResponseCargaDocResult_QNAME, ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.CargaDocResponse.class, ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.CargaDocResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.BuscarDespachosResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Comex", name = "BuscarDespachosResult", scope = ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.BuscarDespachosResponse.class)
    public JAXBElement<ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.BuscarDespachosResponse> createBuscarDespachosResponseBuscarDespachosResult(ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.BuscarDespachosResponse value) {
        return new JAXBElement<ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.BuscarDespachosResponse>(_BuscarDespachosResponseBuscarDespachosResult_QNAME, ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.BuscarDespachosResponse.class, ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.BuscarDespachosResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ConsultaConceptoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Comex", name = "ConsultaConceptoResult", scope = ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.ConsultaConceptoResponse.class)
    public JAXBElement<ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ConsultaConceptoResponse> createConsultaConceptoResponseConsultaConceptoResult(ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ConsultaConceptoResponse value) {
        return new JAXBElement<ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ConsultaConceptoResponse>(_ConsultaConceptoResponseConsultaConceptoResult_QNAME, ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ConsultaConceptoResponse.class, ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.ConsultaConceptoResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ProcesarNifResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Comex", name = "ProcesarNifResult", scope = ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.ProcesarNifResponse.class)
    public JAXBElement<ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ProcesarNifResponse> createProcesarNifResponseProcesarNifResult(ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ProcesarNifResponse value) {
        return new JAXBElement<ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ProcesarNifResponse>(_ProcesarNifResponseProcesarNifResult_QNAME, ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ProcesarNifResponse.class, ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.ProcesarNifResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ConsultaImagenTrfResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Comex", name = "ConsultaImagenTrfResult", scope = ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.ConsultaImagenTrfResponse.class)
    public JAXBElement<ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ConsultaImagenTrfResponse> createConsultaImagenTrfResponseConsultaImagenTrfResult(ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ConsultaImagenTrfResponse value) {
        return new JAXBElement<ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ConsultaImagenTrfResponse>(_ConsultaImagenTrfResponseConsultaImagenTrfResult_QNAME, ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ConsultaImagenTrfResponse.class, ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.ConsultaImagenTrfResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ClonarImagenesRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Comex", name = "request", scope = ClonarImagenes.class)
    public JAXBElement<ClonarImagenesRequest> createClonarImagenesRequest(ClonarImagenesRequest value) {
        return new JAXBElement<ClonarImagenesRequest>(_ConsultaDetalleTrfRequest_QNAME, ClonarImagenesRequest.class, ClonarImagenes.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CargaDespachosRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Comex", name = "request", scope = CargaDespachos.class)
    public JAXBElement<CargaDespachosRequest> createCargaDespachosRequest(CargaDespachosRequest value) {
        return new JAXBElement<CargaDespachosRequest>(_ConsultaDetalleTrfRequest_QNAME, CargaDespachosRequest.class, CargaDespachos.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultaNifRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Comex", name = "request", scope = ConsultaNif.class)
    public JAXBElement<ConsultaNifRequest> createConsultaNifRequest(ConsultaNifRequest value) {
        return new JAXBElement<ConsultaNifRequest>(_ConsultaDetalleTrfRequest_QNAME, ConsultaNifRequest.class, ConsultaNif.class, value);
    }

}
