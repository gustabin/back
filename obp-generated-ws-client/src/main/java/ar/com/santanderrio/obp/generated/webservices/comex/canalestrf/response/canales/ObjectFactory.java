
package ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales;

import java.math.BigDecimal;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.domain.ArrayOfAgendaCuenta;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.domain.ArrayOfConcepto;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.domain.ArrayOfConceptosTipo;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.domain.ArrayOfConsNif;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.domain.ArrayOfDespacho;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.domain.ArrayOfDetalleTrfDespacho;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.domain.ArrayOfDetalleTrfImagen;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.domain.ArrayOfDocumentoConcepto;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.domain.ArrayOfMotExcep;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.domain.ArrayOfOperacion;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.domain.ArrayOfRechazo;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.domain.ArrayOfSwift;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.domain.ArrayOfSwiftBody;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.domain.ArrayOfTiposConcepto;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales package. 
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

    private final static QName _DesvinculaAgendaCuentaResponse_QNAME = new QName("Response/Canales", "DesvinculaAgendaCuentaResponse");
    private final static QName _ConsultaMotExcepcionResponse_QNAME = new QName("Response/Canales", "ConsultaMotExcepcionResponse");
    private final static QName _ProcesarTransferenciaOBPResponse_QNAME = new QName("Response/Canales", "ProcesarTransferenciaOBPResponse");
    private final static QName _ValidarPosicionResponse_QNAME = new QName("Response/Canales", "ValidarPosicionResponse");
    private final static QName _ProcesarTransferenciaBPMResponse_QNAME = new QName("Response/Canales", "ProcesarTransferenciaBPMResponse");
    private final static QName _ObtenerMsgSwiftResponse_QNAME = new QName("Response/Canales", "ObtenerMsgSwiftResponse");
    private final static QName _ConsultaImagenTrfResponse_QNAME = new QName("Response/Canales", "ConsultaImagenTrfResponse");
    private final static QName _ConsultaNifResponse_QNAME = new QName("Response/Canales", "ConsultaNifResponse");
    private final static QName _CargaDocResponse_QNAME = new QName("Response/Canales", "CargaDocResponse");
    private final static QName _ConsultaAgendaCuentaResponse_QNAME = new QName("Response/Canales", "ConsultaAgendaCuentaResponse");
    private final static QName _ConsultaTiposConceptoResponse_QNAME = new QName("Response/Canales", "ConsultaTiposConceptoResponse");
    private final static QName _ConsultaDetalleTrfOBPResponse_QNAME = new QName("Response/Canales", "ConsultaDetalleTrfOBPResponse");
    private final static QName _ClonarImagenesResponse_QNAME = new QName("Response/Canales", "ClonarImagenesResponse");
    private final static QName _BorrarDocResponse_QNAME = new QName("Response/Canales", "BorrarDocResponse");
    private final static QName _ProcesarTransferenciaResponse_QNAME = new QName("Response/Canales", "ProcesarTransferenciaResponse");
    private final static QName _ConsultaDetalleTrfResponse_QNAME = new QName("Response/Canales", "ConsultaDetalleTrfResponse");
    private final static QName _ValidarNIFxNUPResponse_QNAME = new QName("Response/Canales", "ValidarNIFxNUPResponse");
    private final static QName _CargaAgendaCuentaResponse_QNAME = new QName("Response/Canales", "CargaAgendaCuentaResponse");
    private final static QName _ConceptosPorTipoResponse_QNAME = new QName("Response/Canales", "ConceptosPorTipoResponse");
    private final static QName _BuscarDespachosResponse_QNAME = new QName("Response/Canales", "BuscarDespachosResponse");
    private final static QName _ProcesarNifResponse_QNAME = new QName("Response/Canales", "ProcesarNifResponse");
    private final static QName _ConsultaConceptoResponse_QNAME = new QName("Response/Canales", "ConsultaConceptoResponse");
    private final static QName _DesvinculaAgendaResponse_QNAME = new QName("Response/Canales", "DesvinculaAgendaResponse");
    private final static QName _CargaAgendaResponse_QNAME = new QName("Response/Canales", "CargaAgendaResponse");
    private final static QName _ConsultaOperacionesResponse_QNAME = new QName("Response/Canales", "ConsultaOperacionesResponse");
    private final static QName _CargaAgendaResponseIdAgendaNuevo_QNAME = new QName("Response/Canales", "Id_Agenda_Nuevo");
    private final static QName _ConceptosPorTipoResponseRegistros_QNAME = new QName("Response/Canales", "Registros");
    private final static QName _ConsultaDetalleTrfResponseEmpVinculadaOp1_QNAME = new QName("Response/Canales", "Emp_Vinculada_Op1");
    private final static QName _ConsultaDetalleTrfResponseEmpVinculadaOp2_QNAME = new QName("Response/Canales", "Emp_Vinculada_Op2");
    private final static QName _ConsultaDetalleTrfResponseEmpVinculadaOp3_QNAME = new QName("Response/Canales", "Emp_Vinculada_Op3");
    private final static QName _ConsultaDetalleTrfResponseRegistroInpi_QNAME = new QName("Response/Canales", "Registro_Inpi");
    private final static QName _ConsultaDetalleTrfResponseEmpVinculadaOp4_QNAME = new QName("Response/Canales", "Emp_Vinculada_Op4");
    private final static QName _ConsultaDetalleTrfResponseOpcionNoIva_QNAME = new QName("Response/Canales", "Opcion_No_Iva");
    private final static QName _ConsultaDetalleTrfResponseAlicuotaIva_QNAME = new QName("Response/Canales", "Alicuota_Iva");
    private final static QName _ConsultaDetalleTrfResponseConcepto_QNAME = new QName("Response/Canales", "Concepto");
    private final static QName _ConsultaDetalleTrfResponseEmpVinculadaOp5_QNAME = new QName("Response/Canales", "Emp_Vinculada_Op5");
    private final static QName _ConsultaDetalleTrfResponseMontoRetGanacias_QNAME = new QName("Response/Canales", "Monto_Ret_Ganacias");
    private final static QName _ConsultaDetalleTrfResponseAlicuotaGanancias_QNAME = new QName("Response/Canales", "Alicuota_Ganancias");
    private final static QName _ConsultaDetalleTrfResponseImporteTransferencia_QNAME = new QName("Response/Canales", "Importe_Transferencia");
    private final static QName _ConsultaDetalleTrfResponseDeclaracionAdicional_QNAME = new QName("Response/Canales", "Declaracion_Adicional");
    private final static QName _ConsultaDetalleTrfResponseNoRetencionPais_QNAME = new QName("Response/Canales", "No_Retencion_Pais");
    private final static QName _ConsultaDetalleTrfResponsePosArancelaria_QNAME = new QName("Response/Canales", "Pos_Arancelaria");
    private final static QName _ConsultaDetalleTrfResponseNroDocumentoCliente_QNAME = new QName("Response/Canales", "Nro_Documento_Cliente");
    private final static QName _ConsultaDetalleTrfResponseInstVendido_QNAME = new QName("Response/Canales", "Inst_Vendido");
    private final static QName _ConsultaDetalleTrfResponseCtaDebito_QNAME = new QName("Response/Canales", "Cta_debito");
    private final static QName _ConsultaDetalleTrfResponseFechaEmbarque_QNAME = new QName("Response/Canales", "Fecha_Embarque");
    private final static QName _ConsultaDetalleTrfResponseInstRecibido_QNAME = new QName("Response/Canales", "Inst_Recibido");
    private final static QName _ConsultaDetalleTrfResponseNifGan_QNAME = new QName("Response/Canales", "Nif_gan");
    private final static QName _ConsultaDetalleTrfResponseNroTransferenciaRel_QNAME = new QName("Response/Canales", "Nro_Transferencia_Rel");
    private final static QName _ConsultaDetalleTrfResponseMoneda_QNAME = new QName("Response/Canales", "Moneda");
    private final static QName _ConsultaDetalleTrfResponseIdBeneficiario_QNAME = new QName("Response/Canales", "Id_Beneficiario");
    private final static QName _ConsultaDetalleTrfResponseAplicaIntFinan_QNAME = new QName("Response/Canales", "Aplica_Int_Finan");
    private final static QName _ConsultaDetalleTrfResponseBaseImpIva_QNAME = new QName("Response/Canales", "Base_Imp_Iva");
    private final static QName _ConsultaDetalleTrfResponseConDj4834_QNAME = new QName("Response/Canales", "Con_Dj4834");
    private final static QName _ConsultaDetalleTrfResponseCondicionIva_QNAME = new QName("Response/Canales", "Condicion_Iva");
    private final static QName _ConsultaDetalleTrfResponseBaseImpGanancias_QNAME = new QName("Response/Canales", "Base_Imp_Ganancias");
    private final static QName _ConsultaDetalleTrfResponseCargoGanancias_QNAME = new QName("Response/Canales", "Cargo_Ganancias");
    private final static QName _ConsultaDetalleTrfResponseMontoRetIva_QNAME = new QName("Response/Canales", "Monto_Ret_Iva");
    private final static QName _ConsultaDetalleTrfResponseTextoDj4834_QNAME = new QName("Response/Canales", "Texto_Dj4834");
    private final static QName _ConsultaDetalleTrfResponseTrfVigente_QNAME = new QName("Response/Canales", "Trf_Vigente");
    private final static QName _ConsultaDetalleTrfResponseRetieneGanancias_QNAME = new QName("Response/Canales", "Retiene_Ganancias");
    private final static QName _ConsultaDetalleTrfResponseIdBancoIntermediario_QNAME = new QName("Response/Canales", "Id_Banco_Intermediario");
    private final static QName _ConsultaDetalleTrfResponseIdCT_QNAME = new QName("Response/Canales", "Id_CT");
    private final static QName _ConsultaDetalleTrfResponseRetieneIva_QNAME = new QName("Response/Canales", "Retiene_Iva");
    private final static QName _ConsultaDetalleTrfResponseAceptaDdjj_QNAME = new QName("Response/Canales", "Acepta_Ddjj");
    private final static QName _ConsultaDetalleTrfResponseInvAcre_QNAME = new QName("Response/Canales", "Inv_acre");
    private final static QName _ConsultaDetalleTrfResponseMotivoRechazo_QNAME = new QName("Response/Canales", "Motivo_Rechazo");
    private final static QName _ConsultaDetalleTrfResponseNoRetencionMotivo_QNAME = new QName("Response/Canales", "No_Retencion_Motivo");
    private final static QName _ConsultaDetalleTrfResponseDobleImpGanancias_QNAME = new QName("Response/Canales", "Doble_Imp_Ganancias");
    private final static QName _ConsultaDetalleTrfResponseDobleImpArticulo_QNAME = new QName("Response/Canales", "Doble_Imp_Articulo");
    private final static QName _ConsultaDetalleTrfResponseOpcionNoRetencion_QNAME = new QName("Response/Canales", "Opcion_No_Retencion");
    private final static QName _ConsultaDetalleTrfResponseOtrosNoIva_QNAME = new QName("Response/Canales", "Otros_No_Iva");
    private final static QName _ConsultaDetalleTrfResponseTipoTransferencia_QNAME = new QName("Response/Canales", "Tipo_Transferencia");
    private final static QName _ConsultaDetalleTrfResponseOpcionIntFinan_QNAME = new QName("Response/Canales", "Opcion_Int_Finan");
    private final static QName _ConsultaDetalleTrfResponseOpcionGan_QNAME = new QName("Response/Canales", "Opcion_Gan");
    private final static QName _ConsultaDetalleTrfResponseIdConcepto_QNAME = new QName("Response/Canales", "Id_Concepto");
    private final static QName _ConsultaDetalleTrfResponseCuentaDebito_QNAME = new QName("Response/Canales", "Cuenta_Debito");
    private final static QName _ConsultaDetalleTrfResponseImagenes_QNAME = new QName("Response/Canales", "Imagenes");
    private final static QName _ConsultaDetalleTrfResponseEstadoTransferencia_QNAME = new QName("Response/Canales", "Estado_Transferencia");
    private final static QName _ConsultaDetalleTrfResponseDobleImpPais_QNAME = new QName("Response/Canales", "Doble_Imp_Pais");
    private final static QName _ConsultaDetalleTrfResponseIva_QNAME = new QName("Response/Canales", "Iva");
    private final static QName _ConsultaDetalleTrfResponseReferenciaCliente_QNAME = new QName("Response/Canales", "Referencia_Cliente");
    private final static QName _ConsultaDetalleTrfResponseIdCondVenta_QNAME = new QName("Response/Canales", "Id_Cond_Venta");
    private final static QName _ConsultaDetalleTrfResponseCtaAltair_QNAME = new QName("Response/Canales", "Cta_Altair");
    private final static QName _ConsultaDetalleTrfResponseNoRetencionArt14_QNAME = new QName("Response/Canales", "No_Retencion_Art14");
    private final static QName _ConsultaDetalleTrfResponseGastos_QNAME = new QName("Response/Canales", "Gastos");
    private final static QName _ConsultaDetalleTrfResponseNoRetencionArticulo_QNAME = new QName("Response/Canales", "No_Retencion_Articulo");
    private final static QName _ConsultaDetalleTrfResponseIdAlicuota_QNAME = new QName("Response/Canales", "Id_Alicuota");
    private final static QName _ConsultaDetalleTrfResponseCuentaBeneficiario_QNAME = new QName("Response/Canales", "Cuenta_Beneficiario");
    private final static QName _ConsultaDetalleTrfResponseTipoDocumentoCliente_QNAME = new QName("Response/Canales", "Tipo_Documento_Cliente");
    private final static QName _ConsultaDetalleTrfResponseOtrosIntFinan_QNAME = new QName("Response/Canales", "Otros_Int_Finan");
    private final static QName _ConsultaDetalleTrfResponseDespachos_QNAME = new QName("Response/Canales", "Despachos");
    private final static QName _ConsultaDetalleTrfResponseFechaEmbEstimada_QNAME = new QName("Response/Canales", "Fecha_Emb_Estimada");
    private final static QName _ConsultaDetalleTrfResponseCodigoConcepto_QNAME = new QName("Response/Canales", "Codigo_Concepto");
    private final static QName _ConsultaDetalleTrfResponseNroSolicitud_QNAME = new QName("Response/Canales", "Nro_Solicitud");
    private final static QName _ConsultaDetalleTrfResponseCuitBenef_QNAME = new QName("Response/Canales", "Cuit_Benef");
    private final static QName _ConsultaDetalleTrfResponseIdBancoBeneficiario_QNAME = new QName("Response/Canales", "Id_Banco_Beneficiario");
    private final static QName _ConsultaDetalleTrfResponseObservaciones_QNAME = new QName("Response/Canales", "Observaciones");
    private final static QName _ConsultaDetalleTrfResponseCuentaBcoIntermediario_QNAME = new QName("Response/Canales", "Cuenta_Bco_Intermediario");
    private final static QName _ConsultaDetalleTrfResponseNoCorrespIntFinan_QNAME = new QName("Response/Canales", "No_Corresp_Int_Finan");
    private final static QName _ConsultaConceptoResponseConCuitBenef_QNAME = new QName("Response/Canales", "Con_Cuit_Benef");
    private final static QName _ConsultaConceptoResponseDeclaraImpuestos_QNAME = new QName("Response/Canales", "Declara_Impuestos");
    private final static QName _ConsultaConceptoResponseTipoConcepto_QNAME = new QName("Response/Canales", "Tipo_Concepto");
    private final static QName _ConsultaConceptoResponseAvanzaVinculada_QNAME = new QName("Response/Canales", "Avanza_vinculada");
    private final static QName _ConsultaConceptoResponseMostrarCom4834_QNAME = new QName("Response/Canales", "Mostrar_Com4834");
    private final static QName _ConsultaConceptoResponseIngresaDespachos_QNAME = new QName("Response/Canales", "Ingresa_Despachos");
    private final static QName _ConsultaConceptoResponseAyudaDocumentacion_QNAME = new QName("Response/Canales", "Ayuda_Documentacion");
    private final static QName _ConsultaConceptoResponseCon4237_QNAME = new QName("Response/Canales", "Con_4237");
    private final static QName _ConsultaConceptoResponseDocumentos_QNAME = new QName("Response/Canales", "Documentos");
    private final static QName _ConsultaConceptoResponseEditaImpo_QNAME = new QName("Response/Canales", "Edita_Impo");
    private final static QName _ConsultaConceptoResponseConPosAran_QNAME = new QName("Response/Canales", "Con_Pos_Aran");
    private final static QName _ConsultaConceptoResponseIngresaNif_QNAME = new QName("Response/Canales", "Ingresa_Nif");
    private final static QName _ConsultaConceptoResponseInvAcre_QNAME = new QName("Response/Canales", "Inv_Acre");
    private final static QName _ConsultaConceptoResponseMostarEmpVinculada_QNAME = new QName("Response/Canales", "Mostar_Emp_Vinculada");
    private final static QName _ConsultaConceptoResponseMontoCom4834_QNAME = new QName("Response/Canales", "Monto_Com4834");
    private final static QName _ConsultaConceptoResponseMontoEmpVinculada_QNAME = new QName("Response/Canales", "Monto_Emp_Vinculada");
    private final static QName _ConsultaConceptoResponseConFormInver_QNAME = new QName("Response/Canales", "Con_Form_Inver");
    private final static QName _ConsultaConceptoResponseDeclaraciones_QNAME = new QName("Response/Canales", "Declaraciones");
    private final static QName _ConsultaConceptoResponseMonedaCuenta_QNAME = new QName("Response/Canales", "Moneda_Cuenta");
    private final static QName _ConsultaConceptoResponseDescripcionConcepto_QNAME = new QName("Response/Canales", "Descripcion_Concepto");
    private final static QName _ConsultaConceptoResponseConNIFGan_QNAME = new QName("Response/Canales", "Con_NIF_Gan");
    private final static QName _ConsultaConceptoResponseDeclaracionJurada_QNAME = new QName("Response/Canales", "Declaracion_Jurada");
    private final static QName _ConsultaConceptoResponseConFechaEmbEst_QNAME = new QName("Response/Canales", "Con_Fecha_Emb_Est");
    private final static QName _ConsultaConceptoResponseConFechaEmbarque_QNAME = new QName("Response/Canales", "Con_Fecha_Embarque");
    private final static QName _ValidarPosicionResponseEsValida_QNAME = new QName("Response/Canales", "es_valida");
    private final static QName _ConsultaImagenTrfResponseDataImagen_QNAME = new QName("Response/Canales", "Data_Imagen");
    private final static QName _CargaDocResponseHoja_QNAME = new QName("Response/Canales", "Hoja");
    private final static QName _CargaDocResponseNroTransferencia_QNAME = new QName("Response/Canales", "Nro_Transferencia");
    private final static QName _ObtenerMsgSwiftResponseDetalle_QNAME = new QName("Response/Canales", "Detalle");
    private final static QName _ObtenerMsgSwiftResponseCabecera_QNAME = new QName("Response/Canales", "Cabecera");
    private final static QName _ConsultaDetalleTrfOBPResponseBancoBeneficiario_QNAME = new QName("Response/Canales", "Banco_Beneficiario");
    private final static QName _ConsultaDetalleTrfOBPResponseBeneficiarioPais_QNAME = new QName("Response/Canales", "Beneficiario_Pais");
    private final static QName _ConsultaDetalleTrfOBPResponseRechazos_QNAME = new QName("Response/Canales", "Rechazos");
    private final static QName _ConsultaDetalleTrfOBPResponseEmpVinculada_QNAME = new QName("Response/Canales", "Emp_Vinculada");
    private final static QName _ConsultaDetalleTrfOBPResponseBeneficiarioDomicilio_QNAME = new QName("Response/Canales", "Beneficiario_Domicilio");
    private final static QName _ConsultaDetalleTrfOBPResponseBeneficiario_QNAME = new QName("Response/Canales", "Beneficiario");
    private final static QName _ConsultaDetalleTrfOBPResponseVinculo_QNAME = new QName("Response/Canales", "Vinculo");
    private final static QName _ConsultaDetalleTrfOBPResponseBancoIntermediario_QNAME = new QName("Response/Canales", "Banco_Intermediario");
    private final static QName _ProcesarTransferenciaResponseNroTransf_QNAME = new QName("Response/Canales", "Nro_Transf");
    private final static QName _ValidarNIFxNUPResponseExisteNif_QNAME = new QName("Response/Canales", "existe_nif");
    private final static QName _ClonarImagenesResponseNroForm_QNAME = new QName("Response/Canales", "Nro_Form");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ConsultaImagenTrfResponse }
     * 
     */
    public ConsultaImagenTrfResponse createConsultaImagenTrfResponse() {
        return new ConsultaImagenTrfResponse();
    }

    /**
     * Create an instance of {@link CargaAgendaCuentaResponse }
     * 
     */
    public CargaAgendaCuentaResponse createCargaAgendaCuentaResponse() {
        return new CargaAgendaCuentaResponse();
    }

    /**
     * Create an instance of {@link ConsultaDetalleTrfResponse }
     * 
     */
    public ConsultaDetalleTrfResponse createConsultaDetalleTrfResponse() {
        return new ConsultaDetalleTrfResponse();
    }

    /**
     * Create an instance of {@link ConsultaAgendaCuentaResponse }
     * 
     */
    public ConsultaAgendaCuentaResponse createConsultaAgendaCuentaResponse() {
        return new ConsultaAgendaCuentaResponse();
    }

    /**
     * Create an instance of {@link ObtenerMsgSwiftResponse }
     * 
     */
    public ObtenerMsgSwiftResponse createObtenerMsgSwiftResponse() {
        return new ObtenerMsgSwiftResponse();
    }

    /**
     * Create an instance of {@link ValidarNIFxNUPResponse }
     * 
     */
    public ValidarNIFxNUPResponse createValidarNIFxNUPResponse() {
        return new ValidarNIFxNUPResponse();
    }

    /**
     * Create an instance of {@link ConsultaNifResponse }
     * 
     */
    public ConsultaNifResponse createConsultaNifResponse() {
        return new ConsultaNifResponse();
    }

    /**
     * Create an instance of {@link CargaAgendaResponse }
     * 
     */
    public CargaAgendaResponse createCargaAgendaResponse() {
        return new CargaAgendaResponse();
    }

    /**
     * Create an instance of {@link ConsultaConceptoResponse }
     * 
     */
    public ConsultaConceptoResponse createConsultaConceptoResponse() {
        return new ConsultaConceptoResponse();
    }

    /**
     * Create an instance of {@link ConsultaMotExcepcionResponse }
     * 
     */
    public ConsultaMotExcepcionResponse createConsultaMotExcepcionResponse() {
        return new ConsultaMotExcepcionResponse();
    }

    /**
     * Create an instance of {@link CargaDocResponse }
     * 
     */
    public CargaDocResponse createCargaDocResponse() {
        return new CargaDocResponse();
    }

    /**
     * Create an instance of {@link ConsultaTiposConceptoResponse }
     * 
     */
    public ConsultaTiposConceptoResponse createConsultaTiposConceptoResponse() {
        return new ConsultaTiposConceptoResponse();
    }

    /**
     * Create an instance of {@link ClonarImagenesResponse }
     * 
     */
    public ClonarImagenesResponse createClonarImagenesResponse() {
        return new ClonarImagenesResponse();
    }

    /**
     * Create an instance of {@link ConceptosPorTipoResponse }
     * 
     */
    public ConceptosPorTipoResponse createConceptosPorTipoResponse() {
        return new ConceptosPorTipoResponse();
    }

    /**
     * Create an instance of {@link DesvinculaAgendaCuentaResponse }
     * 
     */
    public DesvinculaAgendaCuentaResponse createDesvinculaAgendaCuentaResponse() {
        return new DesvinculaAgendaCuentaResponse();
    }

    /**
     * Create an instance of {@link ProcesarTransferenciaBPMResponse }
     * 
     */
    public ProcesarTransferenciaBPMResponse createProcesarTransferenciaBPMResponse() {
        return new ProcesarTransferenciaBPMResponse();
    }

    /**
     * Create an instance of {@link ConsultaOperacionesResponse }
     * 
     */
    public ConsultaOperacionesResponse createConsultaOperacionesResponse() {
        return new ConsultaOperacionesResponse();
    }

    /**
     * Create an instance of {@link ProcesarNifResponse }
     * 
     */
    public ProcesarNifResponse createProcesarNifResponse() {
        return new ProcesarNifResponse();
    }

    /**
     * Create an instance of {@link ConsultaDetalleTrfOBPResponse }
     * 
     */
    public ConsultaDetalleTrfOBPResponse createConsultaDetalleTrfOBPResponse() {
        return new ConsultaDetalleTrfOBPResponse();
    }

    /**
     * Create an instance of {@link BuscarDespachosResponse }
     * 
     */
    public BuscarDespachosResponse createBuscarDespachosResponse() {
        return new BuscarDespachosResponse();
    }

    /**
     * Create an instance of {@link BorrarDocResponse }
     * 
     */
    public BorrarDocResponse createBorrarDocResponse() {
        return new BorrarDocResponse();
    }

    /**
     * Create an instance of {@link DesvinculaAgendaResponse }
     * 
     */
    public DesvinculaAgendaResponse createDesvinculaAgendaResponse() {
        return new DesvinculaAgendaResponse();
    }

    /**
     * Create an instance of {@link ValidarPosicionResponse }
     * 
     */
    public ValidarPosicionResponse createValidarPosicionResponse() {
        return new ValidarPosicionResponse();
    }

    /**
     * Create an instance of {@link ProcesarTransferenciaOBPResponse }
     * 
     */
    public ProcesarTransferenciaOBPResponse createProcesarTransferenciaOBPResponse() {
        return new ProcesarTransferenciaOBPResponse();
    }

    /**
     * Create an instance of {@link ProcesarTransferenciaResponse }
     * 
     */
    public ProcesarTransferenciaResponse createProcesarTransferenciaResponse() {
        return new ProcesarTransferenciaResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DesvinculaAgendaCuentaResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "DesvinculaAgendaCuentaResponse")
    public JAXBElement<DesvinculaAgendaCuentaResponse> createDesvinculaAgendaCuentaResponse(DesvinculaAgendaCuentaResponse value) {
        return new JAXBElement<DesvinculaAgendaCuentaResponse>(_DesvinculaAgendaCuentaResponse_QNAME, DesvinculaAgendaCuentaResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultaMotExcepcionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "ConsultaMotExcepcionResponse")
    public JAXBElement<ConsultaMotExcepcionResponse> createConsultaMotExcepcionResponse(ConsultaMotExcepcionResponse value) {
        return new JAXBElement<ConsultaMotExcepcionResponse>(_ConsultaMotExcepcionResponse_QNAME, ConsultaMotExcepcionResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ProcesarTransferenciaOBPResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "ProcesarTransferenciaOBPResponse")
    public JAXBElement<ProcesarTransferenciaOBPResponse> createProcesarTransferenciaOBPResponse(ProcesarTransferenciaOBPResponse value) {
        return new JAXBElement<ProcesarTransferenciaOBPResponse>(_ProcesarTransferenciaOBPResponse_QNAME, ProcesarTransferenciaOBPResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ValidarPosicionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "ValidarPosicionResponse")
    public JAXBElement<ValidarPosicionResponse> createValidarPosicionResponse(ValidarPosicionResponse value) {
        return new JAXBElement<ValidarPosicionResponse>(_ValidarPosicionResponse_QNAME, ValidarPosicionResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ProcesarTransferenciaBPMResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "ProcesarTransferenciaBPMResponse")
    public JAXBElement<ProcesarTransferenciaBPMResponse> createProcesarTransferenciaBPMResponse(ProcesarTransferenciaBPMResponse value) {
        return new JAXBElement<ProcesarTransferenciaBPMResponse>(_ProcesarTransferenciaBPMResponse_QNAME, ProcesarTransferenciaBPMResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObtenerMsgSwiftResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "ObtenerMsgSwiftResponse")
    public JAXBElement<ObtenerMsgSwiftResponse> createObtenerMsgSwiftResponse(ObtenerMsgSwiftResponse value) {
        return new JAXBElement<ObtenerMsgSwiftResponse>(_ObtenerMsgSwiftResponse_QNAME, ObtenerMsgSwiftResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultaImagenTrfResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "ConsultaImagenTrfResponse")
    public JAXBElement<ConsultaImagenTrfResponse> createConsultaImagenTrfResponse(ConsultaImagenTrfResponse value) {
        return new JAXBElement<ConsultaImagenTrfResponse>(_ConsultaImagenTrfResponse_QNAME, ConsultaImagenTrfResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultaNifResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "ConsultaNifResponse")
    public JAXBElement<ConsultaNifResponse> createConsultaNifResponse(ConsultaNifResponse value) {
        return new JAXBElement<ConsultaNifResponse>(_ConsultaNifResponse_QNAME, ConsultaNifResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CargaDocResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "CargaDocResponse")
    public JAXBElement<CargaDocResponse> createCargaDocResponse(CargaDocResponse value) {
        return new JAXBElement<CargaDocResponse>(_CargaDocResponse_QNAME, CargaDocResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultaAgendaCuentaResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "ConsultaAgendaCuentaResponse")
    public JAXBElement<ConsultaAgendaCuentaResponse> createConsultaAgendaCuentaResponse(ConsultaAgendaCuentaResponse value) {
        return new JAXBElement<ConsultaAgendaCuentaResponse>(_ConsultaAgendaCuentaResponse_QNAME, ConsultaAgendaCuentaResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultaTiposConceptoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "ConsultaTiposConceptoResponse")
    public JAXBElement<ConsultaTiposConceptoResponse> createConsultaTiposConceptoResponse(ConsultaTiposConceptoResponse value) {
        return new JAXBElement<ConsultaTiposConceptoResponse>(_ConsultaTiposConceptoResponse_QNAME, ConsultaTiposConceptoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultaDetalleTrfOBPResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "ConsultaDetalleTrfOBPResponse")
    public JAXBElement<ConsultaDetalleTrfOBPResponse> createConsultaDetalleTrfOBPResponse(ConsultaDetalleTrfOBPResponse value) {
        return new JAXBElement<ConsultaDetalleTrfOBPResponse>(_ConsultaDetalleTrfOBPResponse_QNAME, ConsultaDetalleTrfOBPResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ClonarImagenesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "ClonarImagenesResponse")
    public JAXBElement<ClonarImagenesResponse> createClonarImagenesResponse(ClonarImagenesResponse value) {
        return new JAXBElement<ClonarImagenesResponse>(_ClonarImagenesResponse_QNAME, ClonarImagenesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BorrarDocResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "BorrarDocResponse")
    public JAXBElement<BorrarDocResponse> createBorrarDocResponse(BorrarDocResponse value) {
        return new JAXBElement<BorrarDocResponse>(_BorrarDocResponse_QNAME, BorrarDocResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ProcesarTransferenciaResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "ProcesarTransferenciaResponse")
    public JAXBElement<ProcesarTransferenciaResponse> createProcesarTransferenciaResponse(ProcesarTransferenciaResponse value) {
        return new JAXBElement<ProcesarTransferenciaResponse>(_ProcesarTransferenciaResponse_QNAME, ProcesarTransferenciaResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultaDetalleTrfResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "ConsultaDetalleTrfResponse")
    public JAXBElement<ConsultaDetalleTrfResponse> createConsultaDetalleTrfResponse(ConsultaDetalleTrfResponse value) {
        return new JAXBElement<ConsultaDetalleTrfResponse>(_ConsultaDetalleTrfResponse_QNAME, ConsultaDetalleTrfResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ValidarNIFxNUPResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "ValidarNIFxNUPResponse")
    public JAXBElement<ValidarNIFxNUPResponse> createValidarNIFxNUPResponse(ValidarNIFxNUPResponse value) {
        return new JAXBElement<ValidarNIFxNUPResponse>(_ValidarNIFxNUPResponse_QNAME, ValidarNIFxNUPResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CargaAgendaCuentaResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "CargaAgendaCuentaResponse")
    public JAXBElement<CargaAgendaCuentaResponse> createCargaAgendaCuentaResponse(CargaAgendaCuentaResponse value) {
        return new JAXBElement<CargaAgendaCuentaResponse>(_CargaAgendaCuentaResponse_QNAME, CargaAgendaCuentaResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConceptosPorTipoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "ConceptosPorTipoResponse")
    public JAXBElement<ConceptosPorTipoResponse> createConceptosPorTipoResponse(ConceptosPorTipoResponse value) {
        return new JAXBElement<ConceptosPorTipoResponse>(_ConceptosPorTipoResponse_QNAME, ConceptosPorTipoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BuscarDespachosResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "BuscarDespachosResponse")
    public JAXBElement<BuscarDespachosResponse> createBuscarDespachosResponse(BuscarDespachosResponse value) {
        return new JAXBElement<BuscarDespachosResponse>(_BuscarDespachosResponse_QNAME, BuscarDespachosResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ProcesarNifResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "ProcesarNifResponse")
    public JAXBElement<ProcesarNifResponse> createProcesarNifResponse(ProcesarNifResponse value) {
        return new JAXBElement<ProcesarNifResponse>(_ProcesarNifResponse_QNAME, ProcesarNifResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultaConceptoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "ConsultaConceptoResponse")
    public JAXBElement<ConsultaConceptoResponse> createConsultaConceptoResponse(ConsultaConceptoResponse value) {
        return new JAXBElement<ConsultaConceptoResponse>(_ConsultaConceptoResponse_QNAME, ConsultaConceptoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DesvinculaAgendaResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "DesvinculaAgendaResponse")
    public JAXBElement<DesvinculaAgendaResponse> createDesvinculaAgendaResponse(DesvinculaAgendaResponse value) {
        return new JAXBElement<DesvinculaAgendaResponse>(_DesvinculaAgendaResponse_QNAME, DesvinculaAgendaResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CargaAgendaResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "CargaAgendaResponse")
    public JAXBElement<CargaAgendaResponse> createCargaAgendaResponse(CargaAgendaResponse value) {
        return new JAXBElement<CargaAgendaResponse>(_CargaAgendaResponse_QNAME, CargaAgendaResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultaOperacionesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "ConsultaOperacionesResponse")
    public JAXBElement<ConsultaOperacionesResponse> createConsultaOperacionesResponse(ConsultaOperacionesResponse value) {
        return new JAXBElement<ConsultaOperacionesResponse>(_ConsultaOperacionesResponse_QNAME, ConsultaOperacionesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Id_Agenda_Nuevo", scope = CargaAgendaResponse.class)
    public JAXBElement<Long> createCargaAgendaResponseIdAgendaNuevo(Long value) {
        return new JAXBElement<Long>(_CargaAgendaResponseIdAgendaNuevo_QNAME, Long.class, CargaAgendaResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfConceptosTipo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Registros", scope = ConceptosPorTipoResponse.class)
    public JAXBElement<ArrayOfConceptosTipo> createConceptosPorTipoResponseRegistros(ArrayOfConceptosTipo value) {
        return new JAXBElement<ArrayOfConceptosTipo>(_ConceptosPorTipoResponseRegistros_QNAME, ArrayOfConceptosTipo.class, ConceptosPorTipoResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfMotExcep }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Registros", scope = ConsultaMotExcepcionResponse.class)
    public JAXBElement<ArrayOfMotExcep> createConsultaMotExcepcionResponseRegistros(ArrayOfMotExcep value) {
        return new JAXBElement<ArrayOfMotExcep>(_ConceptosPorTipoResponseRegistros_QNAME, ArrayOfMotExcep.class, ConsultaMotExcepcionResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Emp_Vinculada_Op1", scope = ConsultaDetalleTrfResponse.class)
    public JAXBElement<Integer> createConsultaDetalleTrfResponseEmpVinculadaOp1(Integer value) {
        return new JAXBElement<Integer>(_ConsultaDetalleTrfResponseEmpVinculadaOp1_QNAME, Integer.class, ConsultaDetalleTrfResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Emp_Vinculada_Op2", scope = ConsultaDetalleTrfResponse.class)
    public JAXBElement<Integer> createConsultaDetalleTrfResponseEmpVinculadaOp2(Integer value) {
        return new JAXBElement<Integer>(_ConsultaDetalleTrfResponseEmpVinculadaOp2_QNAME, Integer.class, ConsultaDetalleTrfResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Emp_Vinculada_Op3", scope = ConsultaDetalleTrfResponse.class)
    public JAXBElement<Integer> createConsultaDetalleTrfResponseEmpVinculadaOp3(Integer value) {
        return new JAXBElement<Integer>(_ConsultaDetalleTrfResponseEmpVinculadaOp3_QNAME, Integer.class, ConsultaDetalleTrfResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Registro_Inpi", scope = ConsultaDetalleTrfResponse.class)
    public JAXBElement<Integer> createConsultaDetalleTrfResponseRegistroInpi(Integer value) {
        return new JAXBElement<Integer>(_ConsultaDetalleTrfResponseRegistroInpi_QNAME, Integer.class, ConsultaDetalleTrfResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Emp_Vinculada_Op4", scope = ConsultaDetalleTrfResponse.class)
    public JAXBElement<Integer> createConsultaDetalleTrfResponseEmpVinculadaOp4(Integer value) {
        return new JAXBElement<Integer>(_ConsultaDetalleTrfResponseEmpVinculadaOp4_QNAME, Integer.class, ConsultaDetalleTrfResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Opcion_No_Iva", scope = ConsultaDetalleTrfResponse.class)
    public JAXBElement<Integer> createConsultaDetalleTrfResponseOpcionNoIva(Integer value) {
        return new JAXBElement<Integer>(_ConsultaDetalleTrfResponseOpcionNoIva_QNAME, Integer.class, ConsultaDetalleTrfResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Alicuota_Iva", scope = ConsultaDetalleTrfResponse.class)
    public JAXBElement<BigDecimal> createConsultaDetalleTrfResponseAlicuotaIva(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_ConsultaDetalleTrfResponseAlicuotaIva_QNAME, BigDecimal.class, ConsultaDetalleTrfResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Concepto", scope = ConsultaDetalleTrfResponse.class)
    public JAXBElement<String> createConsultaDetalleTrfResponseConcepto(String value) {
        return new JAXBElement<String>(_ConsultaDetalleTrfResponseConcepto_QNAME, String.class, ConsultaDetalleTrfResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Emp_Vinculada_Op5", scope = ConsultaDetalleTrfResponse.class)
    public JAXBElement<Integer> createConsultaDetalleTrfResponseEmpVinculadaOp5(Integer value) {
        return new JAXBElement<Integer>(_ConsultaDetalleTrfResponseEmpVinculadaOp5_QNAME, Integer.class, ConsultaDetalleTrfResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Monto_Ret_Ganacias", scope = ConsultaDetalleTrfResponse.class)
    public JAXBElement<BigDecimal> createConsultaDetalleTrfResponseMontoRetGanacias(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_ConsultaDetalleTrfResponseMontoRetGanacias_QNAME, BigDecimal.class, ConsultaDetalleTrfResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Alicuota_Ganancias", scope = ConsultaDetalleTrfResponse.class)
    public JAXBElement<BigDecimal> createConsultaDetalleTrfResponseAlicuotaGanancias(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_ConsultaDetalleTrfResponseAlicuotaGanancias_QNAME, BigDecimal.class, ConsultaDetalleTrfResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Importe_Transferencia", scope = ConsultaDetalleTrfResponse.class)
    public JAXBElement<BigDecimal> createConsultaDetalleTrfResponseImporteTransferencia(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_ConsultaDetalleTrfResponseImporteTransferencia_QNAME, BigDecimal.class, ConsultaDetalleTrfResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Declaracion_Adicional", scope = ConsultaDetalleTrfResponse.class)
    public JAXBElement<Integer> createConsultaDetalleTrfResponseDeclaracionAdicional(Integer value) {
        return new JAXBElement<Integer>(_ConsultaDetalleTrfResponseDeclaracionAdicional_QNAME, Integer.class, ConsultaDetalleTrfResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "No_Retencion_Pais", scope = ConsultaDetalleTrfResponse.class)
    public JAXBElement<String> createConsultaDetalleTrfResponseNoRetencionPais(String value) {
        return new JAXBElement<String>(_ConsultaDetalleTrfResponseNoRetencionPais_QNAME, String.class, ConsultaDetalleTrfResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Pos_Arancelaria", scope = ConsultaDetalleTrfResponse.class)
    public JAXBElement<String> createConsultaDetalleTrfResponsePosArancelaria(String value) {
        return new JAXBElement<String>(_ConsultaDetalleTrfResponsePosArancelaria_QNAME, String.class, ConsultaDetalleTrfResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Nro_Documento_Cliente", scope = ConsultaDetalleTrfResponse.class)
    public JAXBElement<String> createConsultaDetalleTrfResponseNroDocumentoCliente(String value) {
        return new JAXBElement<String>(_ConsultaDetalleTrfResponseNroDocumentoCliente_QNAME, String.class, ConsultaDetalleTrfResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Inst_Vendido", scope = ConsultaDetalleTrfResponse.class)
    public JAXBElement<String> createConsultaDetalleTrfResponseInstVendido(String value) {
        return new JAXBElement<String>(_ConsultaDetalleTrfResponseInstVendido_QNAME, String.class, ConsultaDetalleTrfResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Cta_debito", scope = ConsultaDetalleTrfResponse.class)
    public JAXBElement<String> createConsultaDetalleTrfResponseCtaDebito(String value) {
        return new JAXBElement<String>(_ConsultaDetalleTrfResponseCtaDebito_QNAME, String.class, ConsultaDetalleTrfResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Fecha_Embarque", scope = ConsultaDetalleTrfResponse.class)
    public JAXBElement<String> createConsultaDetalleTrfResponseFechaEmbarque(String value) {
        return new JAXBElement<String>(_ConsultaDetalleTrfResponseFechaEmbarque_QNAME, String.class, ConsultaDetalleTrfResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Inst_Recibido", scope = ConsultaDetalleTrfResponse.class)
    public JAXBElement<String> createConsultaDetalleTrfResponseInstRecibido(String value) {
        return new JAXBElement<String>(_ConsultaDetalleTrfResponseInstRecibido_QNAME, String.class, ConsultaDetalleTrfResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Nif_gan", scope = ConsultaDetalleTrfResponse.class)
    public JAXBElement<String> createConsultaDetalleTrfResponseNifGan(String value) {
        return new JAXBElement<String>(_ConsultaDetalleTrfResponseNifGan_QNAME, String.class, ConsultaDetalleTrfResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Nro_Transferencia_Rel", scope = ConsultaDetalleTrfResponse.class)
    public JAXBElement<Long> createConsultaDetalleTrfResponseNroTransferenciaRel(Long value) {
        return new JAXBElement<Long>(_ConsultaDetalleTrfResponseNroTransferenciaRel_QNAME, Long.class, ConsultaDetalleTrfResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Moneda", scope = ConsultaDetalleTrfResponse.class)
    public JAXBElement<String> createConsultaDetalleTrfResponseMoneda(String value) {
        return new JAXBElement<String>(_ConsultaDetalleTrfResponseMoneda_QNAME, String.class, ConsultaDetalleTrfResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Id_Beneficiario", scope = ConsultaDetalleTrfResponse.class)
    public JAXBElement<String> createConsultaDetalleTrfResponseIdBeneficiario(String value) {
        return new JAXBElement<String>(_ConsultaDetalleTrfResponseIdBeneficiario_QNAME, String.class, ConsultaDetalleTrfResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Aplica_Int_Finan", scope = ConsultaDetalleTrfResponse.class)
    public JAXBElement<Integer> createConsultaDetalleTrfResponseAplicaIntFinan(Integer value) {
        return new JAXBElement<Integer>(_ConsultaDetalleTrfResponseAplicaIntFinan_QNAME, Integer.class, ConsultaDetalleTrfResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Base_Imp_Iva", scope = ConsultaDetalleTrfResponse.class)
    public JAXBElement<BigDecimal> createConsultaDetalleTrfResponseBaseImpIva(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_ConsultaDetalleTrfResponseBaseImpIva_QNAME, BigDecimal.class, ConsultaDetalleTrfResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Con_Dj4834", scope = ConsultaDetalleTrfResponse.class)
    public JAXBElement<Integer> createConsultaDetalleTrfResponseConDj4834(Integer value) {
        return new JAXBElement<Integer>(_ConsultaDetalleTrfResponseConDj4834_QNAME, Integer.class, ConsultaDetalleTrfResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Condicion_Iva", scope = ConsultaDetalleTrfResponse.class)
    public JAXBElement<String> createConsultaDetalleTrfResponseCondicionIva(String value) {
        return new JAXBElement<String>(_ConsultaDetalleTrfResponseCondicionIva_QNAME, String.class, ConsultaDetalleTrfResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Base_Imp_Ganancias", scope = ConsultaDetalleTrfResponse.class)
    public JAXBElement<BigDecimal> createConsultaDetalleTrfResponseBaseImpGanancias(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_ConsultaDetalleTrfResponseBaseImpGanancias_QNAME, BigDecimal.class, ConsultaDetalleTrfResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Cargo_Ganancias", scope = ConsultaDetalleTrfResponse.class)
    public JAXBElement<String> createConsultaDetalleTrfResponseCargoGanancias(String value) {
        return new JAXBElement<String>(_ConsultaDetalleTrfResponseCargoGanancias_QNAME, String.class, ConsultaDetalleTrfResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Monto_Ret_Iva", scope = ConsultaDetalleTrfResponse.class)
    public JAXBElement<BigDecimal> createConsultaDetalleTrfResponseMontoRetIva(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_ConsultaDetalleTrfResponseMontoRetIva_QNAME, BigDecimal.class, ConsultaDetalleTrfResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Texto_Dj4834", scope = ConsultaDetalleTrfResponse.class)
    public JAXBElement<String> createConsultaDetalleTrfResponseTextoDj4834(String value) {
        return new JAXBElement<String>(_ConsultaDetalleTrfResponseTextoDj4834_QNAME, String.class, ConsultaDetalleTrfResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Trf_Vigente", scope = ConsultaDetalleTrfResponse.class)
    public JAXBElement<String> createConsultaDetalleTrfResponseTrfVigente(String value) {
        return new JAXBElement<String>(_ConsultaDetalleTrfResponseTrfVigente_QNAME, String.class, ConsultaDetalleTrfResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Retiene_Ganancias", scope = ConsultaDetalleTrfResponse.class)
    public JAXBElement<Integer> createConsultaDetalleTrfResponseRetieneGanancias(Integer value) {
        return new JAXBElement<Integer>(_ConsultaDetalleTrfResponseRetieneGanancias_QNAME, Integer.class, ConsultaDetalleTrfResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Id_Banco_Intermediario", scope = ConsultaDetalleTrfResponse.class)
    public JAXBElement<String> createConsultaDetalleTrfResponseIdBancoIntermediario(String value) {
        return new JAXBElement<String>(_ConsultaDetalleTrfResponseIdBancoIntermediario_QNAME, String.class, ConsultaDetalleTrfResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Id_CT", scope = ConsultaDetalleTrfResponse.class)
    public JAXBElement<Long> createConsultaDetalleTrfResponseIdCT(Long value) {
        return new JAXBElement<Long>(_ConsultaDetalleTrfResponseIdCT_QNAME, Long.class, ConsultaDetalleTrfResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Retiene_Iva", scope = ConsultaDetalleTrfResponse.class)
    public JAXBElement<Integer> createConsultaDetalleTrfResponseRetieneIva(Integer value) {
        return new JAXBElement<Integer>(_ConsultaDetalleTrfResponseRetieneIva_QNAME, Integer.class, ConsultaDetalleTrfResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Acepta_Ddjj", scope = ConsultaDetalleTrfResponse.class)
    public JAXBElement<Integer> createConsultaDetalleTrfResponseAceptaDdjj(Integer value) {
        return new JAXBElement<Integer>(_ConsultaDetalleTrfResponseAceptaDdjj_QNAME, Integer.class, ConsultaDetalleTrfResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Inv_acre", scope = ConsultaDetalleTrfResponse.class)
    public JAXBElement<String> createConsultaDetalleTrfResponseInvAcre(String value) {
        return new JAXBElement<String>(_ConsultaDetalleTrfResponseInvAcre_QNAME, String.class, ConsultaDetalleTrfResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Motivo_Rechazo", scope = ConsultaDetalleTrfResponse.class)
    public JAXBElement<String> createConsultaDetalleTrfResponseMotivoRechazo(String value) {
        return new JAXBElement<String>(_ConsultaDetalleTrfResponseMotivoRechazo_QNAME, String.class, ConsultaDetalleTrfResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "No_Retencion_Motivo", scope = ConsultaDetalleTrfResponse.class)
    public JAXBElement<String> createConsultaDetalleTrfResponseNoRetencionMotivo(String value) {
        return new JAXBElement<String>(_ConsultaDetalleTrfResponseNoRetencionMotivo_QNAME, String.class, ConsultaDetalleTrfResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Doble_Imp_Ganancias", scope = ConsultaDetalleTrfResponse.class)
    public JAXBElement<String> createConsultaDetalleTrfResponseDobleImpGanancias(String value) {
        return new JAXBElement<String>(_ConsultaDetalleTrfResponseDobleImpGanancias_QNAME, String.class, ConsultaDetalleTrfResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Doble_Imp_Articulo", scope = ConsultaDetalleTrfResponse.class)
    public JAXBElement<String> createConsultaDetalleTrfResponseDobleImpArticulo(String value) {
        return new JAXBElement<String>(_ConsultaDetalleTrfResponseDobleImpArticulo_QNAME, String.class, ConsultaDetalleTrfResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Opcion_No_Retencion", scope = ConsultaDetalleTrfResponse.class)
    public JAXBElement<Integer> createConsultaDetalleTrfResponseOpcionNoRetencion(Integer value) {
        return new JAXBElement<Integer>(_ConsultaDetalleTrfResponseOpcionNoRetencion_QNAME, Integer.class, ConsultaDetalleTrfResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Otros_No_Iva", scope = ConsultaDetalleTrfResponse.class)
    public JAXBElement<String> createConsultaDetalleTrfResponseOtrosNoIva(String value) {
        return new JAXBElement<String>(_ConsultaDetalleTrfResponseOtrosNoIva_QNAME, String.class, ConsultaDetalleTrfResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Short }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Tipo_Transferencia", scope = ConsultaDetalleTrfResponse.class)
    public JAXBElement<Short> createConsultaDetalleTrfResponseTipoTransferencia(Short value) {
        return new JAXBElement<Short>(_ConsultaDetalleTrfResponseTipoTransferencia_QNAME, Short.class, ConsultaDetalleTrfResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Opcion_Int_Finan", scope = ConsultaDetalleTrfResponse.class)
    public JAXBElement<Integer> createConsultaDetalleTrfResponseOpcionIntFinan(Integer value) {
        return new JAXBElement<Integer>(_ConsultaDetalleTrfResponseOpcionIntFinan_QNAME, Integer.class, ConsultaDetalleTrfResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Short }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Opcion_Gan", scope = ConsultaDetalleTrfResponse.class)
    public JAXBElement<Short> createConsultaDetalleTrfResponseOpcionGan(Short value) {
        return new JAXBElement<Short>(_ConsultaDetalleTrfResponseOpcionGan_QNAME, Short.class, ConsultaDetalleTrfResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Id_Concepto", scope = ConsultaDetalleTrfResponse.class)
    public JAXBElement<Integer> createConsultaDetalleTrfResponseIdConcepto(Integer value) {
        return new JAXBElement<Integer>(_ConsultaDetalleTrfResponseIdConcepto_QNAME, Integer.class, ConsultaDetalleTrfResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Cuenta_Debito", scope = ConsultaDetalleTrfResponse.class)
    public JAXBElement<String> createConsultaDetalleTrfResponseCuentaDebito(String value) {
        return new JAXBElement<String>(_ConsultaDetalleTrfResponseCuentaDebito_QNAME, String.class, ConsultaDetalleTrfResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfDetalleTrfImagen }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Imagenes", scope = ConsultaDetalleTrfResponse.class)
    public JAXBElement<ArrayOfDetalleTrfImagen> createConsultaDetalleTrfResponseImagenes(ArrayOfDetalleTrfImagen value) {
        return new JAXBElement<ArrayOfDetalleTrfImagen>(_ConsultaDetalleTrfResponseImagenes_QNAME, ArrayOfDetalleTrfImagen.class, ConsultaDetalleTrfResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Estado_Transferencia", scope = ConsultaDetalleTrfResponse.class)
    public JAXBElement<String> createConsultaDetalleTrfResponseEstadoTransferencia(String value) {
        return new JAXBElement<String>(_ConsultaDetalleTrfResponseEstadoTransferencia_QNAME, String.class, ConsultaDetalleTrfResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Doble_Imp_Pais", scope = ConsultaDetalleTrfResponse.class)
    public JAXBElement<String> createConsultaDetalleTrfResponseDobleImpPais(String value) {
        return new JAXBElement<String>(_ConsultaDetalleTrfResponseDobleImpPais_QNAME, String.class, ConsultaDetalleTrfResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Iva", scope = ConsultaDetalleTrfResponse.class)
    public JAXBElement<String> createConsultaDetalleTrfResponseIva(String value) {
        return new JAXBElement<String>(_ConsultaDetalleTrfResponseIva_QNAME, String.class, ConsultaDetalleTrfResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Referencia_Cliente", scope = ConsultaDetalleTrfResponse.class)
    public JAXBElement<String> createConsultaDetalleTrfResponseReferenciaCliente(String value) {
        return new JAXBElement<String>(_ConsultaDetalleTrfResponseReferenciaCliente_QNAME, String.class, ConsultaDetalleTrfResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Id_Cond_Venta", scope = ConsultaDetalleTrfResponse.class)
    public JAXBElement<Long> createConsultaDetalleTrfResponseIdCondVenta(Long value) {
        return new JAXBElement<Long>(_ConsultaDetalleTrfResponseIdCondVenta_QNAME, Long.class, ConsultaDetalleTrfResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Cta_Altair", scope = ConsultaDetalleTrfResponse.class)
    public JAXBElement<String> createConsultaDetalleTrfResponseCtaAltair(String value) {
        return new JAXBElement<String>(_ConsultaDetalleTrfResponseCtaAltair_QNAME, String.class, ConsultaDetalleTrfResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "No_Retencion_Art14", scope = ConsultaDetalleTrfResponse.class)
    public JAXBElement<String> createConsultaDetalleTrfResponseNoRetencionArt14(String value) {
        return new JAXBElement<String>(_ConsultaDetalleTrfResponseNoRetencionArt14_QNAME, String.class, ConsultaDetalleTrfResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Gastos", scope = ConsultaDetalleTrfResponse.class)
    public JAXBElement<BigDecimal> createConsultaDetalleTrfResponseGastos(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_ConsultaDetalleTrfResponseGastos_QNAME, BigDecimal.class, ConsultaDetalleTrfResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "No_Retencion_Articulo", scope = ConsultaDetalleTrfResponse.class)
    public JAXBElement<String> createConsultaDetalleTrfResponseNoRetencionArticulo(String value) {
        return new JAXBElement<String>(_ConsultaDetalleTrfResponseNoRetencionArticulo_QNAME, String.class, ConsultaDetalleTrfResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Short }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Id_Alicuota", scope = ConsultaDetalleTrfResponse.class)
    public JAXBElement<Short> createConsultaDetalleTrfResponseIdAlicuota(Short value) {
        return new JAXBElement<Short>(_ConsultaDetalleTrfResponseIdAlicuota_QNAME, Short.class, ConsultaDetalleTrfResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Cuenta_Beneficiario", scope = ConsultaDetalleTrfResponse.class)
    public JAXBElement<String> createConsultaDetalleTrfResponseCuentaBeneficiario(String value) {
        return new JAXBElement<String>(_ConsultaDetalleTrfResponseCuentaBeneficiario_QNAME, String.class, ConsultaDetalleTrfResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Tipo_Documento_Cliente", scope = ConsultaDetalleTrfResponse.class)
    public JAXBElement<String> createConsultaDetalleTrfResponseTipoDocumentoCliente(String value) {
        return new JAXBElement<String>(_ConsultaDetalleTrfResponseTipoDocumentoCliente_QNAME, String.class, ConsultaDetalleTrfResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Otros_Int_Finan", scope = ConsultaDetalleTrfResponse.class)
    public JAXBElement<String> createConsultaDetalleTrfResponseOtrosIntFinan(String value) {
        return new JAXBElement<String>(_ConsultaDetalleTrfResponseOtrosIntFinan_QNAME, String.class, ConsultaDetalleTrfResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfDetalleTrfDespacho }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Despachos", scope = ConsultaDetalleTrfResponse.class)
    public JAXBElement<ArrayOfDetalleTrfDespacho> createConsultaDetalleTrfResponseDespachos(ArrayOfDetalleTrfDespacho value) {
        return new JAXBElement<ArrayOfDetalleTrfDespacho>(_ConsultaDetalleTrfResponseDespachos_QNAME, ArrayOfDetalleTrfDespacho.class, ConsultaDetalleTrfResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Fecha_Emb_Estimada", scope = ConsultaDetalleTrfResponse.class)
    public JAXBElement<String> createConsultaDetalleTrfResponseFechaEmbEstimada(String value) {
        return new JAXBElement<String>(_ConsultaDetalleTrfResponseFechaEmbEstimada_QNAME, String.class, ConsultaDetalleTrfResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Codigo_Concepto", scope = ConsultaDetalleTrfResponse.class)
    public JAXBElement<String> createConsultaDetalleTrfResponseCodigoConcepto(String value) {
        return new JAXBElement<String>(_ConsultaDetalleTrfResponseCodigoConcepto_QNAME, String.class, ConsultaDetalleTrfResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Nro_Solicitud", scope = ConsultaDetalleTrfResponse.class)
    public JAXBElement<String> createConsultaDetalleTrfResponseNroSolicitud(String value) {
        return new JAXBElement<String>(_ConsultaDetalleTrfResponseNroSolicitud_QNAME, String.class, ConsultaDetalleTrfResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Cuit_Benef", scope = ConsultaDetalleTrfResponse.class)
    public JAXBElement<String> createConsultaDetalleTrfResponseCuitBenef(String value) {
        return new JAXBElement<String>(_ConsultaDetalleTrfResponseCuitBenef_QNAME, String.class, ConsultaDetalleTrfResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Id_Banco_Beneficiario", scope = ConsultaDetalleTrfResponse.class)
    public JAXBElement<String> createConsultaDetalleTrfResponseIdBancoBeneficiario(String value) {
        return new JAXBElement<String>(_ConsultaDetalleTrfResponseIdBancoBeneficiario_QNAME, String.class, ConsultaDetalleTrfResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Observaciones", scope = ConsultaDetalleTrfResponse.class)
    public JAXBElement<String> createConsultaDetalleTrfResponseObservaciones(String value) {
        return new JAXBElement<String>(_ConsultaDetalleTrfResponseObservaciones_QNAME, String.class, ConsultaDetalleTrfResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Cuenta_Bco_Intermediario", scope = ConsultaDetalleTrfResponse.class)
    public JAXBElement<String> createConsultaDetalleTrfResponseCuentaBcoIntermediario(String value) {
        return new JAXBElement<String>(_ConsultaDetalleTrfResponseCuentaBcoIntermediario_QNAME, String.class, ConsultaDetalleTrfResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "No_Corresp_Int_Finan", scope = ConsultaDetalleTrfResponse.class)
    public JAXBElement<Integer> createConsultaDetalleTrfResponseNoCorrespIntFinan(Integer value) {
        return new JAXBElement<Integer>(_ConsultaDetalleTrfResponseNoCorrespIntFinan_QNAME, Integer.class, ConsultaDetalleTrfResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfTiposConcepto }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Registros", scope = ConsultaTiposConceptoResponse.class)
    public JAXBElement<ArrayOfTiposConcepto> createConsultaTiposConceptoResponseRegistros(ArrayOfTiposConcepto value) {
        return new JAXBElement<ArrayOfTiposConcepto>(_ConceptosPorTipoResponseRegistros_QNAME, ArrayOfTiposConcepto.class, ConsultaTiposConceptoResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Con_Cuit_Benef", scope = ConsultaConceptoResponse.class)
    public JAXBElement<String> createConsultaConceptoResponseConCuitBenef(String value) {
        return new JAXBElement<String>(_ConsultaConceptoResponseConCuitBenef_QNAME, String.class, ConsultaConceptoResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Id_Concepto", scope = ConsultaConceptoResponse.class)
    public JAXBElement<Integer> createConsultaConceptoResponseIdConcepto(Integer value) {
        return new JAXBElement<Integer>(_ConsultaDetalleTrfResponseIdConcepto_QNAME, Integer.class, ConsultaConceptoResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Declara_Impuestos", scope = ConsultaConceptoResponse.class)
    public JAXBElement<String> createConsultaConceptoResponseDeclaraImpuestos(String value) {
        return new JAXBElement<String>(_ConsultaConceptoResponseDeclaraImpuestos_QNAME, String.class, ConsultaConceptoResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Tipo_Concepto", scope = ConsultaConceptoResponse.class)
    public JAXBElement<String> createConsultaConceptoResponseTipoConcepto(String value) {
        return new JAXBElement<String>(_ConsultaConceptoResponseTipoConcepto_QNAME, String.class, ConsultaConceptoResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Avanza_vinculada", scope = ConsultaConceptoResponse.class)
    public JAXBElement<String> createConsultaConceptoResponseAvanzaVinculada(String value) {
        return new JAXBElement<String>(_ConsultaConceptoResponseAvanzaVinculada_QNAME, String.class, ConsultaConceptoResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Mostrar_Com4834", scope = ConsultaConceptoResponse.class)
    public JAXBElement<String> createConsultaConceptoResponseMostrarCom4834(String value) {
        return new JAXBElement<String>(_ConsultaConceptoResponseMostrarCom4834_QNAME, String.class, ConsultaConceptoResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Ingresa_Despachos", scope = ConsultaConceptoResponse.class)
    public JAXBElement<String> createConsultaConceptoResponseIngresaDespachos(String value) {
        return new JAXBElement<String>(_ConsultaConceptoResponseIngresaDespachos_QNAME, String.class, ConsultaConceptoResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Ayuda_Documentacion", scope = ConsultaConceptoResponse.class)
    public JAXBElement<String> createConsultaConceptoResponseAyudaDocumentacion(String value) {
        return new JAXBElement<String>(_ConsultaConceptoResponseAyudaDocumentacion_QNAME, String.class, ConsultaConceptoResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Con_4237", scope = ConsultaConceptoResponse.class)
    public JAXBElement<String> createConsultaConceptoResponseCon4237(String value) {
        return new JAXBElement<String>(_ConsultaConceptoResponseCon4237_QNAME, String.class, ConsultaConceptoResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfDocumentoConcepto }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Documentos", scope = ConsultaConceptoResponse.class)
    public JAXBElement<ArrayOfDocumentoConcepto> createConsultaConceptoResponseDocumentos(ArrayOfDocumentoConcepto value) {
        return new JAXBElement<ArrayOfDocumentoConcepto>(_ConsultaConceptoResponseDocumentos_QNAME, ArrayOfDocumentoConcepto.class, ConsultaConceptoResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Edita_Impo", scope = ConsultaConceptoResponse.class)
    public JAXBElement<String> createConsultaConceptoResponseEditaImpo(String value) {
        return new JAXBElement<String>(_ConsultaConceptoResponseEditaImpo_QNAME, String.class, ConsultaConceptoResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Con_Pos_Aran", scope = ConsultaConceptoResponse.class)
    public JAXBElement<String> createConsultaConceptoResponseConPosAran(String value) {
        return new JAXBElement<String>(_ConsultaConceptoResponseConPosAran_QNAME, String.class, ConsultaConceptoResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Ingresa_Nif", scope = ConsultaConceptoResponse.class)
    public JAXBElement<String> createConsultaConceptoResponseIngresaNif(String value) {
        return new JAXBElement<String>(_ConsultaConceptoResponseIngresaNif_QNAME, String.class, ConsultaConceptoResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Inv_Acre", scope = ConsultaConceptoResponse.class)
    public JAXBElement<String> createConsultaConceptoResponseInvAcre(String value) {
        return new JAXBElement<String>(_ConsultaConceptoResponseInvAcre_QNAME, String.class, ConsultaConceptoResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Mostar_Emp_Vinculada", scope = ConsultaConceptoResponse.class)
    public JAXBElement<String> createConsultaConceptoResponseMostarEmpVinculada(String value) {
        return new JAXBElement<String>(_ConsultaConceptoResponseMostarEmpVinculada_QNAME, String.class, ConsultaConceptoResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Monto_Com4834", scope = ConsultaConceptoResponse.class)
    public JAXBElement<BigDecimal> createConsultaConceptoResponseMontoCom4834(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_ConsultaConceptoResponseMontoCom4834_QNAME, BigDecimal.class, ConsultaConceptoResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Monto_Emp_Vinculada", scope = ConsultaConceptoResponse.class)
    public JAXBElement<BigDecimal> createConsultaConceptoResponseMontoEmpVinculada(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_ConsultaConceptoResponseMontoEmpVinculada_QNAME, BigDecimal.class, ConsultaConceptoResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Con_Form_Inver", scope = ConsultaConceptoResponse.class)
    public JAXBElement<String> createConsultaConceptoResponseConFormInver(String value) {
        return new JAXBElement<String>(_ConsultaConceptoResponseConFormInver_QNAME, String.class, ConsultaConceptoResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfConcepto }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Declaraciones", scope = ConsultaConceptoResponse.class)
    public JAXBElement<ArrayOfConcepto> createConsultaConceptoResponseDeclaraciones(ArrayOfConcepto value) {
        return new JAXBElement<ArrayOfConcepto>(_ConsultaConceptoResponseDeclaraciones_QNAME, ArrayOfConcepto.class, ConsultaConceptoResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Moneda_Cuenta", scope = ConsultaConceptoResponse.class)
    public JAXBElement<String> createConsultaConceptoResponseMonedaCuenta(String value) {
        return new JAXBElement<String>(_ConsultaConceptoResponseMonedaCuenta_QNAME, String.class, ConsultaConceptoResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Descripcion_Concepto", scope = ConsultaConceptoResponse.class)
    public JAXBElement<String> createConsultaConceptoResponseDescripcionConcepto(String value) {
        return new JAXBElement<String>(_ConsultaConceptoResponseDescripcionConcepto_QNAME, String.class, ConsultaConceptoResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Con_NIF_Gan", scope = ConsultaConceptoResponse.class)
    public JAXBElement<String> createConsultaConceptoResponseConNIFGan(String value) {
        return new JAXBElement<String>(_ConsultaConceptoResponseConNIFGan_QNAME, String.class, ConsultaConceptoResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Declaracion_Jurada", scope = ConsultaConceptoResponse.class)
    public JAXBElement<String> createConsultaConceptoResponseDeclaracionJurada(String value) {
        return new JAXBElement<String>(_ConsultaConceptoResponseDeclaracionJurada_QNAME, String.class, ConsultaConceptoResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Con_Fecha_Emb_Est", scope = ConsultaConceptoResponse.class)
    public JAXBElement<String> createConsultaConceptoResponseConFechaEmbEst(String value) {
        return new JAXBElement<String>(_ConsultaConceptoResponseConFechaEmbEst_QNAME, String.class, ConsultaConceptoResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Con_Fecha_Embarque", scope = ConsultaConceptoResponse.class)
    public JAXBElement<String> createConsultaConceptoResponseConFechaEmbarque(String value) {
        return new JAXBElement<String>(_ConsultaConceptoResponseConFechaEmbarque_QNAME, String.class, ConsultaConceptoResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfDespacho }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Registros", scope = BuscarDespachosResponse.class)
    public JAXBElement<ArrayOfDespacho> createBuscarDespachosResponseRegistros(ArrayOfDespacho value) {
        return new JAXBElement<ArrayOfDespacho>(_ConceptosPorTipoResponseRegistros_QNAME, ArrayOfDespacho.class, BuscarDespachosResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfConsNif }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Registros", scope = ConsultaNifResponse.class)
    public JAXBElement<ArrayOfConsNif> createConsultaNifResponseRegistros(ArrayOfConsNif value) {
        return new JAXBElement<ArrayOfConsNif>(_ConceptosPorTipoResponseRegistros_QNAME, ArrayOfConsNif.class, ConsultaNifResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "es_valida", scope = ValidarPosicionResponse.class)
    public JAXBElement<String> createValidarPosicionResponseEsValida(String value) {
        return new JAXBElement<String>(_ValidarPosicionResponseEsValida_QNAME, String.class, ValidarPosicionResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Data_Imagen", scope = ConsultaImagenTrfResponse.class)
    public JAXBElement<byte[]> createConsultaImagenTrfResponseDataImagen(byte[] value) {
        return new JAXBElement<byte[]>(_ConsultaImagenTrfResponseDataImagen_QNAME, byte[].class, ConsultaImagenTrfResponse.class, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Id_CT", scope = ConsultaImagenTrfResponse.class)
    public JAXBElement<BigDecimal> createConsultaImagenTrfResponseIdCT(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_ConsultaDetalleTrfResponseIdCT_QNAME, BigDecimal.class, ConsultaImagenTrfResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Hoja", scope = CargaDocResponse.class)
    public JAXBElement<Integer> createCargaDocResponseHoja(Integer value) {
        return new JAXBElement<Integer>(_CargaDocResponseHoja_QNAME, Integer.class, CargaDocResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Nro_Transferencia", scope = CargaDocResponse.class)
    public JAXBElement<Integer> createCargaDocResponseNroTransferencia(Integer value) {
        return new JAXBElement<Integer>(_CargaDocResponseNroTransferencia_QNAME, Integer.class, CargaDocResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfSwiftBody }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Detalle", scope = ObtenerMsgSwiftResponse.class)
    public JAXBElement<ArrayOfSwiftBody> createObtenerMsgSwiftResponseDetalle(ArrayOfSwiftBody value) {
        return new JAXBElement<ArrayOfSwiftBody>(_ObtenerMsgSwiftResponseDetalle_QNAME, ArrayOfSwiftBody.class, ObtenerMsgSwiftResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfSwift }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Cabecera", scope = ObtenerMsgSwiftResponse.class)
    public JAXBElement<ArrayOfSwift> createObtenerMsgSwiftResponseCabecera(ArrayOfSwift value) {
        return new JAXBElement<ArrayOfSwift>(_ObtenerMsgSwiftResponseCabecera_QNAME, ArrayOfSwift.class, ObtenerMsgSwiftResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfOperacion }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Registros", scope = ConsultaOperacionesResponse.class)
    public JAXBElement<ArrayOfOperacion> createConsultaOperacionesResponseRegistros(ArrayOfOperacion value) {
        return new JAXBElement<ArrayOfOperacion>(_ConceptosPorTipoResponseRegistros_QNAME, ArrayOfOperacion.class, ConsultaOperacionesResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Emp_Vinculada_Op2", scope = ConsultaDetalleTrfOBPResponse.class)
    public JAXBElement<Integer> createConsultaDetalleTrfOBPResponseEmpVinculadaOp2(Integer value) {
        return new JAXBElement<Integer>(_ConsultaDetalleTrfResponseEmpVinculadaOp2_QNAME, Integer.class, ConsultaDetalleTrfOBPResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Emp_Vinculada_Op3", scope = ConsultaDetalleTrfOBPResponse.class)
    public JAXBElement<Integer> createConsultaDetalleTrfOBPResponseEmpVinculadaOp3(Integer value) {
        return new JAXBElement<Integer>(_ConsultaDetalleTrfResponseEmpVinculadaOp3_QNAME, Integer.class, ConsultaDetalleTrfOBPResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Registro_Inpi", scope = ConsultaDetalleTrfOBPResponse.class)
    public JAXBElement<Integer> createConsultaDetalleTrfOBPResponseRegistroInpi(Integer value) {
        return new JAXBElement<Integer>(_ConsultaDetalleTrfResponseRegistroInpi_QNAME, Integer.class, ConsultaDetalleTrfOBPResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Emp_Vinculada_Op4", scope = ConsultaDetalleTrfOBPResponse.class)
    public JAXBElement<Integer> createConsultaDetalleTrfOBPResponseEmpVinculadaOp4(Integer value) {
        return new JAXBElement<Integer>(_ConsultaDetalleTrfResponseEmpVinculadaOp4_QNAME, Integer.class, ConsultaDetalleTrfOBPResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Opcion_No_Iva", scope = ConsultaDetalleTrfOBPResponse.class)
    public JAXBElement<Integer> createConsultaDetalleTrfOBPResponseOpcionNoIva(Integer value) {
        return new JAXBElement<Integer>(_ConsultaDetalleTrfResponseOpcionNoIva_QNAME, Integer.class, ConsultaDetalleTrfOBPResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Concepto", scope = ConsultaDetalleTrfOBPResponse.class)
    public JAXBElement<String> createConsultaDetalleTrfOBPResponseConcepto(String value) {
        return new JAXBElement<String>(_ConsultaDetalleTrfResponseConcepto_QNAME, String.class, ConsultaDetalleTrfOBPResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Alicuota_Iva", scope = ConsultaDetalleTrfOBPResponse.class)
    public JAXBElement<BigDecimal> createConsultaDetalleTrfOBPResponseAlicuotaIva(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_ConsultaDetalleTrfResponseAlicuotaIva_QNAME, BigDecimal.class, ConsultaDetalleTrfOBPResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Emp_Vinculada_Op5", scope = ConsultaDetalleTrfOBPResponse.class)
    public JAXBElement<Integer> createConsultaDetalleTrfOBPResponseEmpVinculadaOp5(Integer value) {
        return new JAXBElement<Integer>(_ConsultaDetalleTrfResponseEmpVinculadaOp5_QNAME, Integer.class, ConsultaDetalleTrfOBPResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Banco_Beneficiario", scope = ConsultaDetalleTrfOBPResponse.class)
    public JAXBElement<String> createConsultaDetalleTrfOBPResponseBancoBeneficiario(String value) {
        return new JAXBElement<String>(_ConsultaDetalleTrfOBPResponseBancoBeneficiario_QNAME, String.class, ConsultaDetalleTrfOBPResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Monto_Ret_Ganacias", scope = ConsultaDetalleTrfOBPResponse.class)
    public JAXBElement<BigDecimal> createConsultaDetalleTrfOBPResponseMontoRetGanacias(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_ConsultaDetalleTrfResponseMontoRetGanacias_QNAME, BigDecimal.class, ConsultaDetalleTrfOBPResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Alicuota_Ganancias", scope = ConsultaDetalleTrfOBPResponse.class)
    public JAXBElement<BigDecimal> createConsultaDetalleTrfOBPResponseAlicuotaGanancias(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_ConsultaDetalleTrfResponseAlicuotaGanancias_QNAME, BigDecimal.class, ConsultaDetalleTrfOBPResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Beneficiario_Pais", scope = ConsultaDetalleTrfOBPResponse.class)
    public JAXBElement<String> createConsultaDetalleTrfOBPResponseBeneficiarioPais(String value) {
        return new JAXBElement<String>(_ConsultaDetalleTrfOBPResponseBeneficiarioPais_QNAME, String.class, ConsultaDetalleTrfOBPResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Importe_Transferencia", scope = ConsultaDetalleTrfOBPResponse.class)
    public JAXBElement<BigDecimal> createConsultaDetalleTrfOBPResponseImporteTransferencia(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_ConsultaDetalleTrfResponseImporteTransferencia_QNAME, BigDecimal.class, ConsultaDetalleTrfOBPResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Declaracion_Adicional", scope = ConsultaDetalleTrfOBPResponse.class)
    public JAXBElement<Integer> createConsultaDetalleTrfOBPResponseDeclaracionAdicional(Integer value) {
        return new JAXBElement<Integer>(_ConsultaDetalleTrfResponseDeclaracionAdicional_QNAME, Integer.class, ConsultaDetalleTrfOBPResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfRechazo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Rechazos", scope = ConsultaDetalleTrfOBPResponse.class)
    public JAXBElement<ArrayOfRechazo> createConsultaDetalleTrfOBPResponseRechazos(ArrayOfRechazo value) {
        return new JAXBElement<ArrayOfRechazo>(_ConsultaDetalleTrfOBPResponseRechazos_QNAME, ArrayOfRechazo.class, ConsultaDetalleTrfOBPResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Emp_Vinculada", scope = ConsultaDetalleTrfOBPResponse.class)
    public JAXBElement<Integer> createConsultaDetalleTrfOBPResponseEmpVinculada(Integer value) {
        return new JAXBElement<Integer>(_ConsultaDetalleTrfOBPResponseEmpVinculada_QNAME, Integer.class, ConsultaDetalleTrfOBPResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "No_Retencion_Pais", scope = ConsultaDetalleTrfOBPResponse.class)
    public JAXBElement<String> createConsultaDetalleTrfOBPResponseNoRetencionPais(String value) {
        return new JAXBElement<String>(_ConsultaDetalleTrfResponseNoRetencionPais_QNAME, String.class, ConsultaDetalleTrfOBPResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Pos_Arancelaria", scope = ConsultaDetalleTrfOBPResponse.class)
    public JAXBElement<String> createConsultaDetalleTrfOBPResponsePosArancelaria(String value) {
        return new JAXBElement<String>(_ConsultaDetalleTrfResponsePosArancelaria_QNAME, String.class, ConsultaDetalleTrfOBPResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Nro_Documento_Cliente", scope = ConsultaDetalleTrfOBPResponse.class)
    public JAXBElement<String> createConsultaDetalleTrfOBPResponseNroDocumentoCliente(String value) {
        return new JAXBElement<String>(_ConsultaDetalleTrfResponseNroDocumentoCliente_QNAME, String.class, ConsultaDetalleTrfOBPResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Inst_Vendido", scope = ConsultaDetalleTrfOBPResponse.class)
    public JAXBElement<String> createConsultaDetalleTrfOBPResponseInstVendido(String value) {
        return new JAXBElement<String>(_ConsultaDetalleTrfResponseInstVendido_QNAME, String.class, ConsultaDetalleTrfOBPResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Cta_debito", scope = ConsultaDetalleTrfOBPResponse.class)
    public JAXBElement<String> createConsultaDetalleTrfOBPResponseCtaDebito(String value) {
        return new JAXBElement<String>(_ConsultaDetalleTrfResponseCtaDebito_QNAME, String.class, ConsultaDetalleTrfOBPResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Fecha_Embarque", scope = ConsultaDetalleTrfOBPResponse.class)
    public JAXBElement<String> createConsultaDetalleTrfOBPResponseFechaEmbarque(String value) {
        return new JAXBElement<String>(_ConsultaDetalleTrfResponseFechaEmbarque_QNAME, String.class, ConsultaDetalleTrfOBPResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Inst_Recibido", scope = ConsultaDetalleTrfOBPResponse.class)
    public JAXBElement<String> createConsultaDetalleTrfOBPResponseInstRecibido(String value) {
        return new JAXBElement<String>(_ConsultaDetalleTrfResponseInstRecibido_QNAME, String.class, ConsultaDetalleTrfOBPResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Beneficiario_Domicilio", scope = ConsultaDetalleTrfOBPResponse.class)
    public JAXBElement<String> createConsultaDetalleTrfOBPResponseBeneficiarioDomicilio(String value) {
        return new JAXBElement<String>(_ConsultaDetalleTrfOBPResponseBeneficiarioDomicilio_QNAME, String.class, ConsultaDetalleTrfOBPResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Nro_Transferencia_Rel", scope = ConsultaDetalleTrfOBPResponse.class)
    public JAXBElement<Long> createConsultaDetalleTrfOBPResponseNroTransferenciaRel(Long value) {
        return new JAXBElement<Long>(_ConsultaDetalleTrfResponseNroTransferenciaRel_QNAME, Long.class, ConsultaDetalleTrfOBPResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Moneda", scope = ConsultaDetalleTrfOBPResponse.class)
    public JAXBElement<String> createConsultaDetalleTrfOBPResponseMoneda(String value) {
        return new JAXBElement<String>(_ConsultaDetalleTrfResponseMoneda_QNAME, String.class, ConsultaDetalleTrfOBPResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Aplica_Int_Finan", scope = ConsultaDetalleTrfOBPResponse.class)
    public JAXBElement<Integer> createConsultaDetalleTrfOBPResponseAplicaIntFinan(Integer value) {
        return new JAXBElement<Integer>(_ConsultaDetalleTrfResponseAplicaIntFinan_QNAME, Integer.class, ConsultaDetalleTrfOBPResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Base_Imp_Iva", scope = ConsultaDetalleTrfOBPResponse.class)
    public JAXBElement<BigDecimal> createConsultaDetalleTrfOBPResponseBaseImpIva(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_ConsultaDetalleTrfResponseBaseImpIva_QNAME, BigDecimal.class, ConsultaDetalleTrfOBPResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Con_Dj4834", scope = ConsultaDetalleTrfOBPResponse.class)
    public JAXBElement<Integer> createConsultaDetalleTrfOBPResponseConDj4834(Integer value) {
        return new JAXBElement<Integer>(_ConsultaDetalleTrfResponseConDj4834_QNAME, Integer.class, ConsultaDetalleTrfOBPResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Condicion_Iva", scope = ConsultaDetalleTrfOBPResponse.class)
    public JAXBElement<String> createConsultaDetalleTrfOBPResponseCondicionIva(String value) {
        return new JAXBElement<String>(_ConsultaDetalleTrfResponseCondicionIva_QNAME, String.class, ConsultaDetalleTrfOBPResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Base_Imp_Ganancias", scope = ConsultaDetalleTrfOBPResponse.class)
    public JAXBElement<BigDecimal> createConsultaDetalleTrfOBPResponseBaseImpGanancias(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_ConsultaDetalleTrfResponseBaseImpGanancias_QNAME, BigDecimal.class, ConsultaDetalleTrfOBPResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Cargo_Ganancias", scope = ConsultaDetalleTrfOBPResponse.class)
    public JAXBElement<String> createConsultaDetalleTrfOBPResponseCargoGanancias(String value) {
        return new JAXBElement<String>(_ConsultaDetalleTrfResponseCargoGanancias_QNAME, String.class, ConsultaDetalleTrfOBPResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Monto_Ret_Iva", scope = ConsultaDetalleTrfOBPResponse.class)
    public JAXBElement<BigDecimal> createConsultaDetalleTrfOBPResponseMontoRetIva(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_ConsultaDetalleTrfResponseMontoRetIva_QNAME, BigDecimal.class, ConsultaDetalleTrfOBPResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Texto_Dj4834", scope = ConsultaDetalleTrfOBPResponse.class)
    public JAXBElement<String> createConsultaDetalleTrfOBPResponseTextoDj4834(String value) {
        return new JAXBElement<String>(_ConsultaDetalleTrfResponseTextoDj4834_QNAME, String.class, ConsultaDetalleTrfOBPResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Trf_Vigente", scope = ConsultaDetalleTrfOBPResponse.class)
    public JAXBElement<String> createConsultaDetalleTrfOBPResponseTrfVigente(String value) {
        return new JAXBElement<String>(_ConsultaDetalleTrfResponseTrfVigente_QNAME, String.class, ConsultaDetalleTrfOBPResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Retiene_Ganancias", scope = ConsultaDetalleTrfOBPResponse.class)
    public JAXBElement<Integer> createConsultaDetalleTrfOBPResponseRetieneGanancias(Integer value) {
        return new JAXBElement<Integer>(_ConsultaDetalleTrfResponseRetieneGanancias_QNAME, Integer.class, ConsultaDetalleTrfOBPResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Id_CT", scope = ConsultaDetalleTrfOBPResponse.class)
    public JAXBElement<Long> createConsultaDetalleTrfOBPResponseIdCT(Long value) {
        return new JAXBElement<Long>(_ConsultaDetalleTrfResponseIdCT_QNAME, Long.class, ConsultaDetalleTrfOBPResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Retiene_Iva", scope = ConsultaDetalleTrfOBPResponse.class)
    public JAXBElement<Integer> createConsultaDetalleTrfOBPResponseRetieneIva(Integer value) {
        return new JAXBElement<Integer>(_ConsultaDetalleTrfResponseRetieneIva_QNAME, Integer.class, ConsultaDetalleTrfOBPResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Acepta_Ddjj", scope = ConsultaDetalleTrfOBPResponse.class)
    public JAXBElement<Integer> createConsultaDetalleTrfOBPResponseAceptaDdjj(Integer value) {
        return new JAXBElement<Integer>(_ConsultaDetalleTrfResponseAceptaDdjj_QNAME, Integer.class, ConsultaDetalleTrfOBPResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "No_Retencion_Motivo", scope = ConsultaDetalleTrfOBPResponse.class)
    public JAXBElement<String> createConsultaDetalleTrfOBPResponseNoRetencionMotivo(String value) {
        return new JAXBElement<String>(_ConsultaDetalleTrfResponseNoRetencionMotivo_QNAME, String.class, ConsultaDetalleTrfOBPResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Doble_Imp_Ganancias", scope = ConsultaDetalleTrfOBPResponse.class)
    public JAXBElement<String> createConsultaDetalleTrfOBPResponseDobleImpGanancias(String value) {
        return new JAXBElement<String>(_ConsultaDetalleTrfResponseDobleImpGanancias_QNAME, String.class, ConsultaDetalleTrfOBPResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Doble_Imp_Articulo", scope = ConsultaDetalleTrfOBPResponse.class)
    public JAXBElement<String> createConsultaDetalleTrfOBPResponseDobleImpArticulo(String value) {
        return new JAXBElement<String>(_ConsultaDetalleTrfResponseDobleImpArticulo_QNAME, String.class, ConsultaDetalleTrfOBPResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Opcion_No_Retencion", scope = ConsultaDetalleTrfOBPResponse.class)
    public JAXBElement<Integer> createConsultaDetalleTrfOBPResponseOpcionNoRetencion(Integer value) {
        return new JAXBElement<Integer>(_ConsultaDetalleTrfResponseOpcionNoRetencion_QNAME, Integer.class, ConsultaDetalleTrfOBPResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Otros_No_Iva", scope = ConsultaDetalleTrfOBPResponse.class)
    public JAXBElement<String> createConsultaDetalleTrfOBPResponseOtrosNoIva(String value) {
        return new JAXBElement<String>(_ConsultaDetalleTrfResponseOtrosNoIva_QNAME, String.class, ConsultaDetalleTrfOBPResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Short }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Tipo_Transferencia", scope = ConsultaDetalleTrfOBPResponse.class)
    public JAXBElement<Short> createConsultaDetalleTrfOBPResponseTipoTransferencia(Short value) {
        return new JAXBElement<Short>(_ConsultaDetalleTrfResponseTipoTransferencia_QNAME, Short.class, ConsultaDetalleTrfOBPResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Opcion_Int_Finan", scope = ConsultaDetalleTrfOBPResponse.class)
    public JAXBElement<Integer> createConsultaDetalleTrfOBPResponseOpcionIntFinan(Integer value) {
        return new JAXBElement<Integer>(_ConsultaDetalleTrfResponseOpcionIntFinan_QNAME, Integer.class, ConsultaDetalleTrfOBPResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Id_Concepto", scope = ConsultaDetalleTrfOBPResponse.class)
    public JAXBElement<Integer> createConsultaDetalleTrfOBPResponseIdConcepto(Integer value) {
        return new JAXBElement<Integer>(_ConsultaDetalleTrfResponseIdConcepto_QNAME, Integer.class, ConsultaDetalleTrfOBPResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Cuenta_Debito", scope = ConsultaDetalleTrfOBPResponse.class)
    public JAXBElement<String> createConsultaDetalleTrfOBPResponseCuentaDebito(String value) {
        return new JAXBElement<String>(_ConsultaDetalleTrfResponseCuentaDebito_QNAME, String.class, ConsultaDetalleTrfOBPResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfDetalleTrfImagen }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Imagenes", scope = ConsultaDetalleTrfOBPResponse.class)
    public JAXBElement<ArrayOfDetalleTrfImagen> createConsultaDetalleTrfOBPResponseImagenes(ArrayOfDetalleTrfImagen value) {
        return new JAXBElement<ArrayOfDetalleTrfImagen>(_ConsultaDetalleTrfResponseImagenes_QNAME, ArrayOfDetalleTrfImagen.class, ConsultaDetalleTrfOBPResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Beneficiario", scope = ConsultaDetalleTrfOBPResponse.class)
    public JAXBElement<String> createConsultaDetalleTrfOBPResponseBeneficiario(String value) {
        return new JAXBElement<String>(_ConsultaDetalleTrfOBPResponseBeneficiario_QNAME, String.class, ConsultaDetalleTrfOBPResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Estado_Transferencia", scope = ConsultaDetalleTrfOBPResponse.class)
    public JAXBElement<String> createConsultaDetalleTrfOBPResponseEstadoTransferencia(String value) {
        return new JAXBElement<String>(_ConsultaDetalleTrfResponseEstadoTransferencia_QNAME, String.class, ConsultaDetalleTrfOBPResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Doble_Imp_Pais", scope = ConsultaDetalleTrfOBPResponse.class)
    public JAXBElement<String> createConsultaDetalleTrfOBPResponseDobleImpPais(String value) {
        return new JAXBElement<String>(_ConsultaDetalleTrfResponseDobleImpPais_QNAME, String.class, ConsultaDetalleTrfOBPResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Referencia_Cliente", scope = ConsultaDetalleTrfOBPResponse.class)
    public JAXBElement<String> createConsultaDetalleTrfOBPResponseReferenciaCliente(String value) {
        return new JAXBElement<String>(_ConsultaDetalleTrfResponseReferenciaCliente_QNAME, String.class, ConsultaDetalleTrfOBPResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Id_Cond_Venta", scope = ConsultaDetalleTrfOBPResponse.class)
    public JAXBElement<Long> createConsultaDetalleTrfOBPResponseIdCondVenta(Long value) {
        return new JAXBElement<Long>(_ConsultaDetalleTrfResponseIdCondVenta_QNAME, Long.class, ConsultaDetalleTrfOBPResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Cta_Altair", scope = ConsultaDetalleTrfOBPResponse.class)
    public JAXBElement<String> createConsultaDetalleTrfOBPResponseCtaAltair(String value) {
        return new JAXBElement<String>(_ConsultaDetalleTrfResponseCtaAltair_QNAME, String.class, ConsultaDetalleTrfOBPResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "No_Retencion_Art14", scope = ConsultaDetalleTrfOBPResponse.class)
    public JAXBElement<String> createConsultaDetalleTrfOBPResponseNoRetencionArt14(String value) {
        return new JAXBElement<String>(_ConsultaDetalleTrfResponseNoRetencionArt14_QNAME, String.class, ConsultaDetalleTrfOBPResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Gastos", scope = ConsultaDetalleTrfOBPResponse.class)
    public JAXBElement<BigDecimal> createConsultaDetalleTrfOBPResponseGastos(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_ConsultaDetalleTrfResponseGastos_QNAME, BigDecimal.class, ConsultaDetalleTrfOBPResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "No_Retencion_Articulo", scope = ConsultaDetalleTrfOBPResponse.class)
    public JAXBElement<String> createConsultaDetalleTrfOBPResponseNoRetencionArticulo(String value) {
        return new JAXBElement<String>(_ConsultaDetalleTrfResponseNoRetencionArticulo_QNAME, String.class, ConsultaDetalleTrfOBPResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Short }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Id_Alicuota", scope = ConsultaDetalleTrfOBPResponse.class)
    public JAXBElement<Short> createConsultaDetalleTrfOBPResponseIdAlicuota(Short value) {
        return new JAXBElement<Short>(_ConsultaDetalleTrfResponseIdAlicuota_QNAME, Short.class, ConsultaDetalleTrfOBPResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Cuenta_Beneficiario", scope = ConsultaDetalleTrfOBPResponse.class)
    public JAXBElement<String> createConsultaDetalleTrfOBPResponseCuentaBeneficiario(String value) {
        return new JAXBElement<String>(_ConsultaDetalleTrfResponseCuentaBeneficiario_QNAME, String.class, ConsultaDetalleTrfOBPResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Tipo_Documento_Cliente", scope = ConsultaDetalleTrfOBPResponse.class)
    public JAXBElement<String> createConsultaDetalleTrfOBPResponseTipoDocumentoCliente(String value) {
        return new JAXBElement<String>(_ConsultaDetalleTrfResponseTipoDocumentoCliente_QNAME, String.class, ConsultaDetalleTrfOBPResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Otros_Int_Finan", scope = ConsultaDetalleTrfOBPResponse.class)
    public JAXBElement<String> createConsultaDetalleTrfOBPResponseOtrosIntFinan(String value) {
        return new JAXBElement<String>(_ConsultaDetalleTrfResponseOtrosIntFinan_QNAME, String.class, ConsultaDetalleTrfOBPResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Vinculo", scope = ConsultaDetalleTrfOBPResponse.class)
    public JAXBElement<String> createConsultaDetalleTrfOBPResponseVinculo(String value) {
        return new JAXBElement<String>(_ConsultaDetalleTrfOBPResponseVinculo_QNAME, String.class, ConsultaDetalleTrfOBPResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Codigo_Concepto", scope = ConsultaDetalleTrfOBPResponse.class)
    public JAXBElement<String> createConsultaDetalleTrfOBPResponseCodigoConcepto(String value) {
        return new JAXBElement<String>(_ConsultaDetalleTrfResponseCodigoConcepto_QNAME, String.class, ConsultaDetalleTrfOBPResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Nro_Solicitud", scope = ConsultaDetalleTrfOBPResponse.class)
    public JAXBElement<String> createConsultaDetalleTrfOBPResponseNroSolicitud(String value) {
        return new JAXBElement<String>(_ConsultaDetalleTrfResponseNroSolicitud_QNAME, String.class, ConsultaDetalleTrfOBPResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Cuenta_Bco_Intermediario", scope = ConsultaDetalleTrfOBPResponse.class)
    public JAXBElement<String> createConsultaDetalleTrfOBPResponseCuentaBcoIntermediario(String value) {
        return new JAXBElement<String>(_ConsultaDetalleTrfResponseCuentaBcoIntermediario_QNAME, String.class, ConsultaDetalleTrfOBPResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "No_Corresp_Int_Finan", scope = ConsultaDetalleTrfOBPResponse.class)
    public JAXBElement<Integer> createConsultaDetalleTrfOBPResponseNoCorrespIntFinan(Integer value) {
        return new JAXBElement<Integer>(_ConsultaDetalleTrfResponseNoCorrespIntFinan_QNAME, Integer.class, ConsultaDetalleTrfOBPResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Banco_Intermediario", scope = ConsultaDetalleTrfOBPResponse.class)
    public JAXBElement<String> createConsultaDetalleTrfOBPResponseBancoIntermediario(String value) {
        return new JAXBElement<String>(_ConsultaDetalleTrfOBPResponseBancoIntermediario_QNAME, String.class, ConsultaDetalleTrfOBPResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Nro_Transf", scope = ProcesarTransferenciaResponse.class)
    public JAXBElement<Integer> createProcesarTransferenciaResponseNroTransf(Integer value) {
        return new JAXBElement<Integer>(_ProcesarTransferenciaResponseNroTransf_QNAME, Integer.class, ProcesarTransferenciaResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "existe_nif", scope = ValidarNIFxNUPResponse.class)
    public JAXBElement<String> createValidarNIFxNUPResponseExisteNif(String value) {
        return new JAXBElement<String>(_ValidarNIFxNUPResponseExisteNif_QNAME, String.class, ValidarNIFxNUPResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfAgendaCuenta }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Registros", scope = ConsultaAgendaCuentaResponse.class)
    public JAXBElement<ArrayOfAgendaCuenta> createConsultaAgendaCuentaResponseRegistros(ArrayOfAgendaCuenta value) {
        return new JAXBElement<ArrayOfAgendaCuenta>(_ConceptosPorTipoResponseRegistros_QNAME, ArrayOfAgendaCuenta.class, ConsultaAgendaCuentaResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Nro_Transf", scope = ProcesarTransferenciaBPMResponse.class)
    public JAXBElement<Integer> createProcesarTransferenciaBPMResponseNroTransf(Integer value) {
        return new JAXBElement<Integer>(_ProcesarTransferenciaResponseNroTransf_QNAME, Integer.class, ProcesarTransferenciaBPMResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Nro_Transf", scope = ProcesarTransferenciaOBPResponse.class)
    public JAXBElement<Integer> createProcesarTransferenciaOBPResponseNroTransf(Integer value) {
        return new JAXBElement<Integer>(_ProcesarTransferenciaResponseNroTransf_QNAME, Integer.class, ProcesarTransferenciaOBPResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Canales", name = "Nro_Form", scope = ClonarImagenesResponse.class)
    public JAXBElement<Integer> createClonarImagenesResponseNroForm(Integer value) {
        return new JAXBElement<Integer>(_ClonarImagenesResponseNroForm_QNAME, Integer.class, ClonarImagenesResponse.class, value);
    }

}
