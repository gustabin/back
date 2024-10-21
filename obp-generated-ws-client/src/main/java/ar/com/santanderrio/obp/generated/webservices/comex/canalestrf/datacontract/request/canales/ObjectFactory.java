
package ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.request.canales;

import java.math.BigDecimal;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.request.canales package. 
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

    private final static QName _ObtenerMsgSwiftRequestProducto_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Producto");
    private final static QName _ObtenerMsgSwiftRequestNroFormulario_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Nro_Formulario");
    private final static QName _ObtenerMsgSwiftRequest_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "ObtenerMsgSwiftRequest");
    private final static QName _ConsultaDetalleTrfOBPRequest_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "ConsultaDetalleTrfOBPRequest");
    private final static QName _ConsultaDetalleTrfRequest_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "ConsultaDetalleTrfRequest");
    private final static QName _BorrarDocRequest_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "BorrarDocRequest");
    private final static QName _ConsultaNifRequest_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "ConsultaNifRequest");
    private final static QName _ConsultaOperacionesRequest_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "ConsultaOperacionesRequest");
    private final static QName _ProcesarNifRequest_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "ProcesarNifRequest");
    private final static QName _ClonarImagenesRequest_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "ClonarImagenesRequest");
    private final static QName _DesvinculaAgendaCuentaRequest_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "DesvinculaAgendaCuentaRequest");
    private final static QName _BuscarDespachosRequest_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "BuscarDespachosRequest");
    private final static QName _ValidarPosicionRequest_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "ValidarPosicionRequest");
    private final static QName _CargaAgendaCuentaRequest_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "CargaAgendaCuentaRequest");
    private final static QName _ProcesarTransferenciaOBPRequest_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "ProcesarTransferenciaOBPRequest");
    private final static QName _CargaDocRequest_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "CargaDocRequest");
    private final static QName _CargaAgendaRequest_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "CargaAgendaRequest");
    private final static QName _CargaDespachosRequest_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "CargaDespachosRequest");
    private final static QName _ConsultaTiposConceptoRequest_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "ConsultaTiposConceptoRequest");
    private final static QName _ConsultaAgendaCuentaRequest_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "ConsultaAgendaCuentaRequest");
    private final static QName _ConceptosPorTipoRequest_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "ConceptosPorTipoRequest");
    private final static QName _ConsultaConceptoRequest_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "ConsultaConceptoRequest");
    private final static QName _ConsultaMotExcepcionRequest_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "ConsultaMotExcepcionRequest");
    private final static QName _ValidarNIFxNUPRequest_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "ValidarNIFxNUPRequest");
    private final static QName _ProcesarTrfRequest_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "ProcesarTrfRequest");
    private final static QName _ArrayOfCargaDespachosRequest_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "ArrayOfCargaDespachosRequest");
    private final static QName _DesvinculaAgendaRequest_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "DesvinculaAgendaRequest");
    private final static QName _ProcesarTrfBPMRequest_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "ProcesarTrfBPMRequest");
    private final static QName _ConsultaImagenTrfRequest_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "ConsultaImagenTrfRequest");
    private final static QName _CargaDespachosRequestSaldoDespacho_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Saldo_Despacho");
    private final static QName _CargaDespachosRequestTipoDeclaracion_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Tipo_Declaracion");
    private final static QName _CargaDespachosRequestBancoSeguimiento_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Banco_Seguimiento");
    private final static QName _CargaDespachosRequestMotivoExcepcion_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Motivo_Excepcion");
    private final static QName _CargaDespachosRequestCargaManual_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Carga_Manual");
    private final static QName _CargaDespachosRequestNroTransferencia_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Nro_Transferencia");
    private final static QName _CargaDespachosRequestNroDeclaracion_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Nro_Declaracion");
    private final static QName _CargaDespachosRequestImporteAplicado_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Importe_Aplicado");
    private final static QName _CargaDespachosRequestFechaEmbarque_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Fecha_Embarque");
    private final static QName _CargaDespachosRequestNroDespacho_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Nro_Despacho");
    private final static QName _CargaDespachosRequestMoneda_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Moneda");
    private final static QName _CargaAgendaRequestAbaInter_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Aba_Inter");
    private final static QName _CargaAgendaRequestNupCliente_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Nup_Cliente");
    private final static QName _CargaAgendaRequestTipoAgenda_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Tipo_Agenda");
    private final static QName _CargaAgendaRequestNombreBenef_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Nombre_Benef");
    private final static QName _CargaAgendaRequestDomicilioBenef_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Domicilio_Benef");
    private final static QName _CargaAgendaRequestActivo_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Activo");
    private final static QName _CargaAgendaRequestBancoInter_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Banco_Inter");
    private final static QName _CargaAgendaRequestBancoBenef_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Banco_Benef");
    private final static QName _CargaAgendaRequestPaisBenef_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Pais_Benef");
    private final static QName _CargaAgendaRequestLocalidadBenef_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Localidad_Benef");
    private final static QName _CargaAgendaRequestAbaBenef_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Aba_Benef");
    private final static QName _DesvinculaAgendaCuentaRequestBanco_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Banco");
    private final static QName _DesvinculaAgendaCuentaRequestCuenta_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Cuenta");
    private final static QName _ProcesarTransferenciaOBPRequestReferenciaCliente_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Referencia_Cliente");
    private final static QName _ProcesarTransferenciaOBPRequestNroDocumentoCliente_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Nro_Documento_Cliente");
    private final static QName _ProcesarTransferenciaOBPRequestInstVendido_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Inst_Vendido");
    private final static QName _ProcesarTransferenciaOBPRequestInstRecibido_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Inst_Recibido");
    private final static QName _ProcesarTransferenciaOBPRequestBeneficiarioDomicilio_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Beneficiario_Domicilio");
    private final static QName _ProcesarTransferenciaOBPRequestTipoPersona_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Tipo_Persona");
    private final static QName _ProcesarTransferenciaOBPRequestNroTransferenciaRel_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Nro_Transferencia_Rel");
    private final static QName _ProcesarTransferenciaOBPRequestGastos_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Gastos");
    private final static QName _ProcesarTransferenciaOBPRequestEmpresaVinculada_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Empresa_Vinculada");
    private final static QName _ProcesarTransferenciaOBPRequestCuentaBeneficiario_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Cuenta_Beneficiario");
    private final static QName _ProcesarTransferenciaOBPRequestTipoDocumentoCliente_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Tipo_Documento_Cliente");
    private final static QName _ProcesarTransferenciaOBPRequestVinculo_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Vinculo");
    private final static QName _ProcesarTransferenciaOBPRequestObservaciones_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Observaciones");
    private final static QName _ProcesarTransferenciaOBPRequestCuentaBcoIntermediario_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Cuenta_Bco_Intermediario");
    private final static QName _ProcesarTransferenciaOBPRequestBancoIntermediario_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Banco_Intermediario");
    private final static QName _ProcesarTransferenciaOBPRequestCtaAltair_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Cta_altair");
    private final static QName _ProcesarTransferenciaOBPRequestAceptaDdjj_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Acepta_Ddjj");
    private final static QName _ProcesarTransferenciaOBPRequestTipoOperacion_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Tipo_Operacion");
    private final static QName _ProcesarTransferenciaOBPRequestConcepto_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Concepto");
    private final static QName _ProcesarTransferenciaOBPRequestBancoBeneficiario_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Banco_Beneficiario");
    private final static QName _ProcesarTransferenciaOBPRequestTipoTransferencia_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Tipo_Transferencia");
    private final static QName _ProcesarTransferenciaOBPRequestBeneficiarioPais_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Beneficiario_Pais");
    private final static QName _ProcesarTransferenciaOBPRequestImporteTransferencia_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Importe_Transferencia");
    private final static QName _ProcesarTransferenciaOBPRequestCanal_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Canal");
    private final static QName _ProcesarTransferenciaOBPRequestDeclaracionAdicional_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Declaracion_Adicional");
    private final static QName _ProcesarTransferenciaOBPRequestCuentaDebito_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Cuenta_Debito");
    private final static QName _ProcesarTransferenciaOBPRequestRazonSocial_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Razon_Social");
    private final static QName _ProcesarTransferenciaOBPRequestBeneficiario_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Beneficiario");
    private final static QName _ProcesarTransferenciaOBPRequestEstadoTransferencia_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Estado_Transferencia");
    private final static QName _CargaDocRequestNombreArchivo_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Nombre_Archivo");
    private final static QName _CargaDocRequestTipoArchivo_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Tipo_Archivo");
    private final static QName _CargaDocRequestImagen_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Imagen");
    private final static QName _ConsultaConceptoRequestIdConcepto_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Id_Concepto");
    private final static QName _ConsultaConceptoRequestCodConcepto_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Cod_Concepto");
    private final static QName _ProcesarTrfBPMRequestRubroBenef_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Rubro_Benef");
    private final static QName _ProcesarTrfBPMRequestGeneraComprob_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Genera_Comprob");
    private final static QName _BuscarDespachosRequestBancoEmisor_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Banco_Emisor");
    private final static QName _BuscarDespachosRequestMontoDesde_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Monto_Desde");
    private final static QName _BuscarDespachosRequestMontoHasta_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Monto_Hasta");
    private final static QName _BuscarDespachosRequestFechaDesde_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Fecha_Desde");
    private final static QName _BuscarDespachosRequestTipoDocCliente_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Tipo_Doc_Cliente");
    private final static QName _BuscarDespachosRequestNroDocCliente_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Nro_Doc_Cliente");
    private final static QName _BuscarDespachosRequestNominado_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Nominado");
    private final static QName _BuscarDespachosRequestFechaHasta_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Fecha_Hasta");
    private final static QName _ConsultaOperacionesRequestNroForm_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Nro_Form");
    private final static QName _ConsultaOperacionesRequestCantidadRegistros_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Cantidad_Registros");
    private final static QName _ConsultaOperacionesRequestCuentaCliente_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Cuenta_Cliente");
    private final static QName _ProcesarNifRequestPaisQueExpide_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Pais_Que_Expide");
    private final static QName _ProcesarNifRequestAccion_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Accion");
    private final static QName _ProcesarNifRequestNacionalidadActual_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Nacionalidad_Actual");
    private final static QName _ProcesarNifRequestCiudad_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Ciudad");
    private final static QName _ProcesarNifRequestNumeroDocumento_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Numero_Documento");
    private final static QName _ProcesarNifRequestTipoDocumento_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Tipo_Documento");
    private final static QName _ProcesarNifRequestNombreBeneficiario_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Nombre_Beneficiario");
    private final static QName _ProcesarNifRequestNif_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Nif");
    private final static QName _ProcesarNifRequestPaisNacimiento_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Pais_Nacimiento");
    private final static QName _ProcesarNifRequestResidenciaTribut_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Residencia_Tribut");
    private final static QName _ProcesarNifRequestProvincia_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Provincia");
    private final static QName _ProcesarNifRequestCuitRepLegal_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Cuit_Rep_Legal");
    private final static QName _ProcesarNifRequestPaisResidencia_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Pais_Residencia");
    private final static QName _ProcesarNifRequestClaveIdentificacion_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Clave_Identificacion");
    private final static QName _ProcesarNifRequestRepresentanteLegal_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Representante_Legal");
    private final static QName _ProcesarNifRequestFechaNacimiento_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Fecha_Nacimiento");
    private final static QName _ProcesarNifRequestDireccion_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Direccion");
    private final static QName _BorrarDocRequestNroHoja_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Nro_Hoja");
    private final static QName _ValidarPosicionRequestPosicion_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Posicion");
    private final static QName _ProcesarTrfRequestDobleImpPais_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Doble_Imp_Pais");
    private final static QName _ProcesarTrfRequestIva_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Iva");
    private final static QName _ProcesarTrfRequestPNROFORMREL_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "P_NRO_FORM_REL");
    private final static QName _ProcesarTrfRequestIdCondVenta_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Id_Cond_Venta");
    private final static QName _ProcesarTrfRequestInvAcre_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Inv_Acre");
    private final static QName _ProcesarTrfRequestNoRetencionArt14_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "No_Retencion_Art14");
    private final static QName _ProcesarTrfRequestNoRetencionArticulo_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "No_Retencion_Articulo");
    private final static QName _ProcesarTrfRequestIdAlicuota_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Id_Alicuota");
    private final static QName _ProcesarTrfRequestOtrosIntFinan_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Otros_Int_Finan");
    private final static QName _ProcesarTrfRequestDespachos_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Despachos");
    private final static QName _ProcesarTrfRequestFechaEmbEstimada_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Fecha_Emb_Estimada");
    private final static QName _ProcesarTrfRequestCuitBenef_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Cuit_Benef");
    private final static QName _ProcesarTrfRequestIdBancoBeneficiario_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Id_Banco_Beneficiario");
    private final static QName _ProcesarTrfRequestNoCorrespIntFinan_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "No_Corresp_Int_Finan");
    private final static QName _ProcesarTrfRequestEmpVinculadaOp1_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Emp_Vinculada_Op1");
    private final static QName _ProcesarTrfRequestEmpVinculadaOp2_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Emp_Vinculada_Op2");
    private final static QName _ProcesarTrfRequestEmpVinculadaOp3_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Emp_Vinculada_Op3");
    private final static QName _ProcesarTrfRequestRegistroInpi_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Registro_Inpi");
    private final static QName _ProcesarTrfRequestNroFormInv_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Nro_Form_Inv");
    private final static QName _ProcesarTrfRequestEmpVinculadaOp4_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Emp_Vinculada_Op4");
    private final static QName _ProcesarTrfRequestOpcionNoIva_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Opcion_No_Iva");
    private final static QName _ProcesarTrfRequestAlicuotaIva_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Alicuota_Iva");
    private final static QName _ProcesarTrfRequestEmpVinculadaOp5_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Emp_Vinculada_Op5");
    private final static QName _ProcesarTrfRequestMontoRetGanacias_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Monto_Ret_Ganacias");
    private final static QName _ProcesarTrfRequestAlicuotaGanancias_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Alicuota_Ganancias");
    private final static QName _ProcesarTrfRequestAutogestion_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Autogestion");
    private final static QName _ProcesarTrfRequestNoRetencionPais_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "No_Retencion_Pais");
    private final static QName _ProcesarTrfRequestPosArancelaria_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Pos_Arancelaria");
    private final static QName _ProcesarTrfRequestNifGan_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Nif_gan");
    private final static QName _ProcesarTrfRequestIdBeneficiario_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Id_Beneficiario");
    private final static QName _ProcesarTrfRequestAplicaIntFinan_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Aplica_Int_Finan");
    private final static QName _ProcesarTrfRequestBaseImpIva_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Base_Imp_Iva");
    private final static QName _ProcesarTrfRequestConDj4834_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Con_Dj4834");
    private final static QName _ProcesarTrfRequestCondicionIva_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Condicion_Iva");
    private final static QName _ProcesarTrfRequestBaseImpGanancias_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Base_Imp_Ganancias");
    private final static QName _ProcesarTrfRequestCargoGanancias_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Cargo_Ganancias");
    private final static QName _ProcesarTrfRequestMontoRetIva_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Monto_Ret_Iva");
    private final static QName _ProcesarTrfRequestTextoDj4834_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Texto_Dj4834");
    private final static QName _ProcesarTrfRequestRetieneGanancias_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Retiene_Ganancias");
    private final static QName _ProcesarTrfRequestIdBancoIntermediario_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Id_Banco_Intermediario");
    private final static QName _ProcesarTrfRequestRetieneIva_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Retiene_Iva");
    private final static QName _ProcesarTrfRequestMotivoRechazo_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Motivo_Rechazo");
    private final static QName _ProcesarTrfRequestNoRetencionMotivo_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "No_Retencion_Motivo");
    private final static QName _ProcesarTrfRequestDobleImpGanancias_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Doble_Imp_Ganancias");
    private final static QName _ProcesarTrfRequestDobleImpArticulo_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Doble_Imp_Articulo");
    private final static QName _ProcesarTrfRequestOpcionNoRetencion_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Opcion_No_Retencion");
    private final static QName _ProcesarTrfRequestOtrosNoIva_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Otros_No_Iva");
    private final static QName _ProcesarTrfRequestOpcionIntFinan_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Opcion_Int_Finan");
    private final static QName _ProcesarTrfRequestOpcionGan_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Opcion_Gan");
    private final static QName _ConsultaImagenTrfRequestIdImagen_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Id_Imagen");
    private final static QName _ConceptosPorTipoRequestTipoConcepto_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Tipo_Concepto");
    private final static QName _ValidarNIFxNUPRequestNup_QNAME = new QName("http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", "Nup");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.request.canales
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ProcesarTransferenciaOBPRequest }
     * 
     */
    public ProcesarTransferenciaOBPRequest createProcesarTransferenciaOBPRequest() {
        return new ProcesarTransferenciaOBPRequest();
    }

    /**
     * Create an instance of {@link ValidarPosicionRequest }
     * 
     */
    public ValidarPosicionRequest createValidarPosicionRequest() {
        return new ValidarPosicionRequest();
    }

    /**
     * Create an instance of {@link ConsultaDetalleTrfRequest }
     * 
     */
    public ConsultaDetalleTrfRequest createConsultaDetalleTrfRequest() {
        return new ConsultaDetalleTrfRequest();
    }

    /**
     * Create an instance of {@link DesvinculaAgendaCuentaRequest }
     * 
     */
    public DesvinculaAgendaCuentaRequest createDesvinculaAgendaCuentaRequest() {
        return new DesvinculaAgendaCuentaRequest();
    }

    /**
     * Create an instance of {@link ConsultaMotExcepcionRequest }
     * 
     */
    public ConsultaMotExcepcionRequest createConsultaMotExcepcionRequest() {
        return new ConsultaMotExcepcionRequest();
    }

    /**
     * Create an instance of {@link CargaAgendaCuentaRequest }
     * 
     */
    public CargaAgendaCuentaRequest createCargaAgendaCuentaRequest() {
        return new CargaAgendaCuentaRequest();
    }

    /**
     * Create an instance of {@link ConsultaImagenTrfRequest }
     * 
     */
    public ConsultaImagenTrfRequest createConsultaImagenTrfRequest() {
        return new ConsultaImagenTrfRequest();
    }

    /**
     * Create an instance of {@link CargaDespachosRequest }
     * 
     */
    public CargaDespachosRequest createCargaDespachosRequest() {
        return new CargaDespachosRequest();
    }

    /**
     * Create an instance of {@link ClonarImagenesRequest }
     * 
     */
    public ClonarImagenesRequest createClonarImagenesRequest() {
        return new ClonarImagenesRequest();
    }

    /**
     * Create an instance of {@link ConsultaDetalleTrfOBPRequest }
     * 
     */
    public ConsultaDetalleTrfOBPRequest createConsultaDetalleTrfOBPRequest() {
        return new ConsultaDetalleTrfOBPRequest();
    }

    /**
     * Create an instance of {@link ConsultaOperacionesRequest }
     * 
     */
    public ConsultaOperacionesRequest createConsultaOperacionesRequest() {
        return new ConsultaOperacionesRequest();
    }

    /**
     * Create an instance of {@link ValidarNIFxNUPRequest }
     * 
     */
    public ValidarNIFxNUPRequest createValidarNIFxNUPRequest() {
        return new ValidarNIFxNUPRequest();
    }

    /**
     * Create an instance of {@link ArrayOfCargaDespachosRequest }
     * 
     */
    public ArrayOfCargaDespachosRequest createArrayOfCargaDespachosRequest() {
        return new ArrayOfCargaDespachosRequest();
    }

    /**
     * Create an instance of {@link ProcesarTrfBPMRequest }
     * 
     */
    public ProcesarTrfBPMRequest createProcesarTrfBPMRequest() {
        return new ProcesarTrfBPMRequest();
    }

    /**
     * Create an instance of {@link CargaDocRequest }
     * 
     */
    public CargaDocRequest createCargaDocRequest() {
        return new CargaDocRequest();
    }

    /**
     * Create an instance of {@link ConsultaAgendaCuentaRequest }
     * 
     */
    public ConsultaAgendaCuentaRequest createConsultaAgendaCuentaRequest() {
        return new ConsultaAgendaCuentaRequest();
    }

    /**
     * Create an instance of {@link ObtenerMsgSwiftRequest }
     * 
     */
    public ObtenerMsgSwiftRequest createObtenerMsgSwiftRequest() {
        return new ObtenerMsgSwiftRequest();
    }

    /**
     * Create an instance of {@link ConsultaConceptoRequest }
     * 
     */
    public ConsultaConceptoRequest createConsultaConceptoRequest() {
        return new ConsultaConceptoRequest();
    }

    /**
     * Create an instance of {@link DesvinculaAgendaRequest }
     * 
     */
    public DesvinculaAgendaRequest createDesvinculaAgendaRequest() {
        return new DesvinculaAgendaRequest();
    }

    /**
     * Create an instance of {@link ConceptosPorTipoRequest }
     * 
     */
    public ConceptosPorTipoRequest createConceptosPorTipoRequest() {
        return new ConceptosPorTipoRequest();
    }

    /**
     * Create an instance of {@link CargaAgendaRequest }
     * 
     */
    public CargaAgendaRequest createCargaAgendaRequest() {
        return new CargaAgendaRequest();
    }

    /**
     * Create an instance of {@link BorrarDocRequest }
     * 
     */
    public BorrarDocRequest createBorrarDocRequest() {
        return new BorrarDocRequest();
    }

    /**
     * Create an instance of {@link BuscarDespachosRequest }
     * 
     */
    public BuscarDespachosRequest createBuscarDespachosRequest() {
        return new BuscarDespachosRequest();
    }

    /**
     * Create an instance of {@link ConsultaNifRequest }
     * 
     */
    public ConsultaNifRequest createConsultaNifRequest() {
        return new ConsultaNifRequest();
    }

    /**
     * Create an instance of {@link ProcesarNifRequest }
     * 
     */
    public ProcesarNifRequest createProcesarNifRequest() {
        return new ProcesarNifRequest();
    }

    /**
     * Create an instance of {@link ProcesarTrfRequest }
     * 
     */
    public ProcesarTrfRequest createProcesarTrfRequest() {
        return new ProcesarTrfRequest();
    }

    /**
     * Create an instance of {@link ConsultaTiposConceptoRequest }
     * 
     */
    public ConsultaTiposConceptoRequest createConsultaTiposConceptoRequest() {
        return new ConsultaTiposConceptoRequest();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Short }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Producto", scope = ObtenerMsgSwiftRequest.class)
    public JAXBElement<Short> createObtenerMsgSwiftRequestProducto(Short value) {
        return new JAXBElement<Short>(_ObtenerMsgSwiftRequestProducto_QNAME, Short.class, ObtenerMsgSwiftRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Nro_Formulario", scope = ObtenerMsgSwiftRequest.class)
    public JAXBElement<Integer> createObtenerMsgSwiftRequestNroFormulario(Integer value) {
        return new JAXBElement<Integer>(_ObtenerMsgSwiftRequestNroFormulario_QNAME, Integer.class, ObtenerMsgSwiftRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObtenerMsgSwiftRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "ObtenerMsgSwiftRequest")
    public JAXBElement<ObtenerMsgSwiftRequest> createObtenerMsgSwiftRequest(ObtenerMsgSwiftRequest value) {
        return new JAXBElement<ObtenerMsgSwiftRequest>(_ObtenerMsgSwiftRequest_QNAME, ObtenerMsgSwiftRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultaDetalleTrfOBPRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "ConsultaDetalleTrfOBPRequest")
    public JAXBElement<ConsultaDetalleTrfOBPRequest> createConsultaDetalleTrfOBPRequest(ConsultaDetalleTrfOBPRequest value) {
        return new JAXBElement<ConsultaDetalleTrfOBPRequest>(_ConsultaDetalleTrfOBPRequest_QNAME, ConsultaDetalleTrfOBPRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultaDetalleTrfRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "ConsultaDetalleTrfRequest")
    public JAXBElement<ConsultaDetalleTrfRequest> createConsultaDetalleTrfRequest(ConsultaDetalleTrfRequest value) {
        return new JAXBElement<ConsultaDetalleTrfRequest>(_ConsultaDetalleTrfRequest_QNAME, ConsultaDetalleTrfRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BorrarDocRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "BorrarDocRequest")
    public JAXBElement<BorrarDocRequest> createBorrarDocRequest(BorrarDocRequest value) {
        return new JAXBElement<BorrarDocRequest>(_BorrarDocRequest_QNAME, BorrarDocRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultaNifRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "ConsultaNifRequest")
    public JAXBElement<ConsultaNifRequest> createConsultaNifRequest(ConsultaNifRequest value) {
        return new JAXBElement<ConsultaNifRequest>(_ConsultaNifRequest_QNAME, ConsultaNifRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultaOperacionesRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "ConsultaOperacionesRequest")
    public JAXBElement<ConsultaOperacionesRequest> createConsultaOperacionesRequest(ConsultaOperacionesRequest value) {
        return new JAXBElement<ConsultaOperacionesRequest>(_ConsultaOperacionesRequest_QNAME, ConsultaOperacionesRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ProcesarNifRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "ProcesarNifRequest")
    public JAXBElement<ProcesarNifRequest> createProcesarNifRequest(ProcesarNifRequest value) {
        return new JAXBElement<ProcesarNifRequest>(_ProcesarNifRequest_QNAME, ProcesarNifRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ClonarImagenesRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "ClonarImagenesRequest")
    public JAXBElement<ClonarImagenesRequest> createClonarImagenesRequest(ClonarImagenesRequest value) {
        return new JAXBElement<ClonarImagenesRequest>(_ClonarImagenesRequest_QNAME, ClonarImagenesRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DesvinculaAgendaCuentaRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "DesvinculaAgendaCuentaRequest")
    public JAXBElement<DesvinculaAgendaCuentaRequest> createDesvinculaAgendaCuentaRequest(DesvinculaAgendaCuentaRequest value) {
        return new JAXBElement<DesvinculaAgendaCuentaRequest>(_DesvinculaAgendaCuentaRequest_QNAME, DesvinculaAgendaCuentaRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BuscarDespachosRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "BuscarDespachosRequest")
    public JAXBElement<BuscarDespachosRequest> createBuscarDespachosRequest(BuscarDespachosRequest value) {
        return new JAXBElement<BuscarDespachosRequest>(_BuscarDespachosRequest_QNAME, BuscarDespachosRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ValidarPosicionRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "ValidarPosicionRequest")
    public JAXBElement<ValidarPosicionRequest> createValidarPosicionRequest(ValidarPosicionRequest value) {
        return new JAXBElement<ValidarPosicionRequest>(_ValidarPosicionRequest_QNAME, ValidarPosicionRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CargaAgendaCuentaRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "CargaAgendaCuentaRequest")
    public JAXBElement<CargaAgendaCuentaRequest> createCargaAgendaCuentaRequest(CargaAgendaCuentaRequest value) {
        return new JAXBElement<CargaAgendaCuentaRequest>(_CargaAgendaCuentaRequest_QNAME, CargaAgendaCuentaRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ProcesarTransferenciaOBPRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "ProcesarTransferenciaOBPRequest")
    public JAXBElement<ProcesarTransferenciaOBPRequest> createProcesarTransferenciaOBPRequest(ProcesarTransferenciaOBPRequest value) {
        return new JAXBElement<ProcesarTransferenciaOBPRequest>(_ProcesarTransferenciaOBPRequest_QNAME, ProcesarTransferenciaOBPRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CargaDocRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "CargaDocRequest")
    public JAXBElement<CargaDocRequest> createCargaDocRequest(CargaDocRequest value) {
        return new JAXBElement<CargaDocRequest>(_CargaDocRequest_QNAME, CargaDocRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CargaAgendaRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "CargaAgendaRequest")
    public JAXBElement<CargaAgendaRequest> createCargaAgendaRequest(CargaAgendaRequest value) {
        return new JAXBElement<CargaAgendaRequest>(_CargaAgendaRequest_QNAME, CargaAgendaRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CargaDespachosRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "CargaDespachosRequest")
    public JAXBElement<CargaDespachosRequest> createCargaDespachosRequest(CargaDespachosRequest value) {
        return new JAXBElement<CargaDespachosRequest>(_CargaDespachosRequest_QNAME, CargaDespachosRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultaTiposConceptoRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "ConsultaTiposConceptoRequest")
    public JAXBElement<ConsultaTiposConceptoRequest> createConsultaTiposConceptoRequest(ConsultaTiposConceptoRequest value) {
        return new JAXBElement<ConsultaTiposConceptoRequest>(_ConsultaTiposConceptoRequest_QNAME, ConsultaTiposConceptoRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultaAgendaCuentaRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "ConsultaAgendaCuentaRequest")
    public JAXBElement<ConsultaAgendaCuentaRequest> createConsultaAgendaCuentaRequest(ConsultaAgendaCuentaRequest value) {
        return new JAXBElement<ConsultaAgendaCuentaRequest>(_ConsultaAgendaCuentaRequest_QNAME, ConsultaAgendaCuentaRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConceptosPorTipoRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "ConceptosPorTipoRequest")
    public JAXBElement<ConceptosPorTipoRequest> createConceptosPorTipoRequest(ConceptosPorTipoRequest value) {
        return new JAXBElement<ConceptosPorTipoRequest>(_ConceptosPorTipoRequest_QNAME, ConceptosPorTipoRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultaConceptoRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "ConsultaConceptoRequest")
    public JAXBElement<ConsultaConceptoRequest> createConsultaConceptoRequest(ConsultaConceptoRequest value) {
        return new JAXBElement<ConsultaConceptoRequest>(_ConsultaConceptoRequest_QNAME, ConsultaConceptoRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultaMotExcepcionRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "ConsultaMotExcepcionRequest")
    public JAXBElement<ConsultaMotExcepcionRequest> createConsultaMotExcepcionRequest(ConsultaMotExcepcionRequest value) {
        return new JAXBElement<ConsultaMotExcepcionRequest>(_ConsultaMotExcepcionRequest_QNAME, ConsultaMotExcepcionRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ValidarNIFxNUPRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "ValidarNIFxNUPRequest")
    public JAXBElement<ValidarNIFxNUPRequest> createValidarNIFxNUPRequest(ValidarNIFxNUPRequest value) {
        return new JAXBElement<ValidarNIFxNUPRequest>(_ValidarNIFxNUPRequest_QNAME, ValidarNIFxNUPRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ProcesarTrfRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "ProcesarTrfRequest")
    public JAXBElement<ProcesarTrfRequest> createProcesarTrfRequest(ProcesarTrfRequest value) {
        return new JAXBElement<ProcesarTrfRequest>(_ProcesarTrfRequest_QNAME, ProcesarTrfRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfCargaDespachosRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "ArrayOfCargaDespachosRequest")
    public JAXBElement<ArrayOfCargaDespachosRequest> createArrayOfCargaDespachosRequest(ArrayOfCargaDespachosRequest value) {
        return new JAXBElement<ArrayOfCargaDespachosRequest>(_ArrayOfCargaDespachosRequest_QNAME, ArrayOfCargaDespachosRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DesvinculaAgendaRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "DesvinculaAgendaRequest")
    public JAXBElement<DesvinculaAgendaRequest> createDesvinculaAgendaRequest(DesvinculaAgendaRequest value) {
        return new JAXBElement<DesvinculaAgendaRequest>(_DesvinculaAgendaRequest_QNAME, DesvinculaAgendaRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ProcesarTrfBPMRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "ProcesarTrfBPMRequest")
    public JAXBElement<ProcesarTrfBPMRequest> createProcesarTrfBPMRequest(ProcesarTrfBPMRequest value) {
        return new JAXBElement<ProcesarTrfBPMRequest>(_ProcesarTrfBPMRequest_QNAME, ProcesarTrfBPMRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultaImagenTrfRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "ConsultaImagenTrfRequest")
    public JAXBElement<ConsultaImagenTrfRequest> createConsultaImagenTrfRequest(ConsultaImagenTrfRequest value) {
        return new JAXBElement<ConsultaImagenTrfRequest>(_ConsultaImagenTrfRequest_QNAME, ConsultaImagenTrfRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Saldo_Despacho", scope = CargaDespachosRequest.class)
    public JAXBElement<Double> createCargaDespachosRequestSaldoDespacho(Double value) {
        return new JAXBElement<Double>(_CargaDespachosRequestSaldoDespacho_QNAME, Double.class, CargaDespachosRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Tipo_Declaracion", scope = CargaDespachosRequest.class)
    public JAXBElement<String> createCargaDespachosRequestTipoDeclaracion(String value) {
        return new JAXBElement<String>(_CargaDespachosRequestTipoDeclaracion_QNAME, String.class, CargaDespachosRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Banco_Seguimiento", scope = CargaDespachosRequest.class)
    public JAXBElement<String> createCargaDespachosRequestBancoSeguimiento(String value) {
        return new JAXBElement<String>(_CargaDespachosRequestBancoSeguimiento_QNAME, String.class, CargaDespachosRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Motivo_Excepcion", scope = CargaDespachosRequest.class)
    public JAXBElement<String> createCargaDespachosRequestMotivoExcepcion(String value) {
        return new JAXBElement<String>(_CargaDespachosRequestMotivoExcepcion_QNAME, String.class, CargaDespachosRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Carga_Manual", scope = CargaDespachosRequest.class)
    public JAXBElement<String> createCargaDespachosRequestCargaManual(String value) {
        return new JAXBElement<String>(_CargaDespachosRequestCargaManual_QNAME, String.class, CargaDespachosRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Nro_Transferencia", scope = CargaDespachosRequest.class)
    public JAXBElement<Integer> createCargaDespachosRequestNroTransferencia(Integer value) {
        return new JAXBElement<Integer>(_CargaDespachosRequestNroTransferencia_QNAME, Integer.class, CargaDespachosRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Nro_Declaracion", scope = CargaDespachosRequest.class)
    public JAXBElement<String> createCargaDespachosRequestNroDeclaracion(String value) {
        return new JAXBElement<String>(_CargaDespachosRequestNroDeclaracion_QNAME, String.class, CargaDespachosRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Importe_Aplicado", scope = CargaDespachosRequest.class)
    public JAXBElement<Double> createCargaDespachosRequestImporteAplicado(Double value) {
        return new JAXBElement<Double>(_CargaDespachosRequestImporteAplicado_QNAME, Double.class, CargaDespachosRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Fecha_Embarque", scope = CargaDespachosRequest.class)
    public JAXBElement<String> createCargaDespachosRequestFechaEmbarque(String value) {
        return new JAXBElement<String>(_CargaDespachosRequestFechaEmbarque_QNAME, String.class, CargaDespachosRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Nro_Despacho", scope = CargaDespachosRequest.class)
    public JAXBElement<String> createCargaDespachosRequestNroDespacho(String value) {
        return new JAXBElement<String>(_CargaDespachosRequestNroDespacho_QNAME, String.class, CargaDespachosRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Moneda", scope = CargaDespachosRequest.class)
    public JAXBElement<String> createCargaDespachosRequestMoneda(String value) {
        return new JAXBElement<String>(_CargaDespachosRequestMoneda_QNAME, String.class, CargaDespachosRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Aba_Inter", scope = CargaAgendaRequest.class)
    public JAXBElement<String> createCargaAgendaRequestAbaInter(String value) {
        return new JAXBElement<String>(_CargaAgendaRequestAbaInter_QNAME, String.class, CargaAgendaRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Nup_Cliente", scope = CargaAgendaRequest.class)
    public JAXBElement<String> createCargaAgendaRequestNupCliente(String value) {
        return new JAXBElement<String>(_CargaAgendaRequestNupCliente_QNAME, String.class, CargaAgendaRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Short }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Tipo_Agenda", scope = CargaAgendaRequest.class)
    public JAXBElement<Short> createCargaAgendaRequestTipoAgenda(Short value) {
        return new JAXBElement<Short>(_CargaAgendaRequestTipoAgenda_QNAME, Short.class, CargaAgendaRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Nombre_Benef", scope = CargaAgendaRequest.class)
    public JAXBElement<String> createCargaAgendaRequestNombreBenef(String value) {
        return new JAXBElement<String>(_CargaAgendaRequestNombreBenef_QNAME, String.class, CargaAgendaRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Domicilio_Benef", scope = CargaAgendaRequest.class)
    public JAXBElement<String> createCargaAgendaRequestDomicilioBenef(String value) {
        return new JAXBElement<String>(_CargaAgendaRequestDomicilioBenef_QNAME, String.class, CargaAgendaRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Activo", scope = CargaAgendaRequest.class)
    public JAXBElement<String> createCargaAgendaRequestActivo(String value) {
        return new JAXBElement<String>(_CargaAgendaRequestActivo_QNAME, String.class, CargaAgendaRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Banco_Inter", scope = CargaAgendaRequest.class)
    public JAXBElement<String> createCargaAgendaRequestBancoInter(String value) {
        return new JAXBElement<String>(_CargaAgendaRequestBancoInter_QNAME, String.class, CargaAgendaRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Banco_Benef", scope = CargaAgendaRequest.class)
    public JAXBElement<String> createCargaAgendaRequestBancoBenef(String value) {
        return new JAXBElement<String>(_CargaAgendaRequestBancoBenef_QNAME, String.class, CargaAgendaRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Pais_Benef", scope = CargaAgendaRequest.class)
    public JAXBElement<String> createCargaAgendaRequestPaisBenef(String value) {
        return new JAXBElement<String>(_CargaAgendaRequestPaisBenef_QNAME, String.class, CargaAgendaRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Localidad_Benef", scope = CargaAgendaRequest.class)
    public JAXBElement<String> createCargaAgendaRequestLocalidadBenef(String value) {
        return new JAXBElement<String>(_CargaAgendaRequestLocalidadBenef_QNAME, String.class, CargaAgendaRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Aba_Benef", scope = CargaAgendaRequest.class)
    public JAXBElement<String> createCargaAgendaRequestAbaBenef(String value) {
        return new JAXBElement<String>(_CargaAgendaRequestAbaBenef_QNAME, String.class, CargaAgendaRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Banco", scope = DesvinculaAgendaCuentaRequest.class)
    public JAXBElement<String> createDesvinculaAgendaCuentaRequestBanco(String value) {
        return new JAXBElement<String>(_DesvinculaAgendaCuentaRequestBanco_QNAME, String.class, DesvinculaAgendaCuentaRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Cuenta", scope = DesvinculaAgendaCuentaRequest.class)
    public JAXBElement<String> createDesvinculaAgendaCuentaRequestCuenta(String value) {
        return new JAXBElement<String>(_DesvinculaAgendaCuentaRequestCuenta_QNAME, String.class, DesvinculaAgendaCuentaRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Nup_Cliente", scope = ConsultaNifRequest.class)
    public JAXBElement<String> createConsultaNifRequestNupCliente(String value) {
        return new JAXBElement<String>(_CargaAgendaRequestNupCliente_QNAME, String.class, ConsultaNifRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Referencia_Cliente", scope = ProcesarTransferenciaOBPRequest.class)
    public JAXBElement<String> createProcesarTransferenciaOBPRequestReferenciaCliente(String value) {
        return new JAXBElement<String>(_ProcesarTransferenciaOBPRequestReferenciaCliente_QNAME, String.class, ProcesarTransferenciaOBPRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Nro_Documento_Cliente", scope = ProcesarTransferenciaOBPRequest.class)
    public JAXBElement<String> createProcesarTransferenciaOBPRequestNroDocumentoCliente(String value) {
        return new JAXBElement<String>(_ProcesarTransferenciaOBPRequestNroDocumentoCliente_QNAME, String.class, ProcesarTransferenciaOBPRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Inst_Vendido", scope = ProcesarTransferenciaOBPRequest.class)
    public JAXBElement<String> createProcesarTransferenciaOBPRequestInstVendido(String value) {
        return new JAXBElement<String>(_ProcesarTransferenciaOBPRequestInstVendido_QNAME, String.class, ProcesarTransferenciaOBPRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Nro_Transferencia", scope = ProcesarTransferenciaOBPRequest.class)
    public JAXBElement<Integer> createProcesarTransferenciaOBPRequestNroTransferencia(Integer value) {
        return new JAXBElement<Integer>(_CargaDespachosRequestNroTransferencia_QNAME, Integer.class, ProcesarTransferenciaOBPRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Inst_Recibido", scope = ProcesarTransferenciaOBPRequest.class)
    public JAXBElement<String> createProcesarTransferenciaOBPRequestInstRecibido(String value) {
        return new JAXBElement<String>(_ProcesarTransferenciaOBPRequestInstRecibido_QNAME, String.class, ProcesarTransferenciaOBPRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Beneficiario_Domicilio", scope = ProcesarTransferenciaOBPRequest.class)
    public JAXBElement<String> createProcesarTransferenciaOBPRequestBeneficiarioDomicilio(String value) {
        return new JAXBElement<String>(_ProcesarTransferenciaOBPRequestBeneficiarioDomicilio_QNAME, String.class, ProcesarTransferenciaOBPRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Tipo_Persona", scope = ProcesarTransferenciaOBPRequest.class)
    public JAXBElement<String> createProcesarTransferenciaOBPRequestTipoPersona(String value) {
        return new JAXBElement<String>(_ProcesarTransferenciaOBPRequestTipoPersona_QNAME, String.class, ProcesarTransferenciaOBPRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Nro_Transferencia_Rel", scope = ProcesarTransferenciaOBPRequest.class)
    public JAXBElement<Long> createProcesarTransferenciaOBPRequestNroTransferenciaRel(Long value) {
        return new JAXBElement<Long>(_ProcesarTransferenciaOBPRequestNroTransferenciaRel_QNAME, Long.class, ProcesarTransferenciaOBPRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Moneda", scope = ProcesarTransferenciaOBPRequest.class)
    public JAXBElement<String> createProcesarTransferenciaOBPRequestMoneda(String value) {
        return new JAXBElement<String>(_CargaDespachosRequestMoneda_QNAME, String.class, ProcesarTransferenciaOBPRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Short }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Gastos", scope = ProcesarTransferenciaOBPRequest.class)
    public JAXBElement<Short> createProcesarTransferenciaOBPRequestGastos(Short value) {
        return new JAXBElement<Short>(_ProcesarTransferenciaOBPRequestGastos_QNAME, Short.class, ProcesarTransferenciaOBPRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Nup_Cliente", scope = ProcesarTransferenciaOBPRequest.class)
    public JAXBElement<String> createProcesarTransferenciaOBPRequestNupCliente(String value) {
        return new JAXBElement<String>(_CargaAgendaRequestNupCliente_QNAME, String.class, ProcesarTransferenciaOBPRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Short }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Empresa_Vinculada", scope = ProcesarTransferenciaOBPRequest.class)
    public JAXBElement<Short> createProcesarTransferenciaOBPRequestEmpresaVinculada(Short value) {
        return new JAXBElement<Short>(_ProcesarTransferenciaOBPRequestEmpresaVinculada_QNAME, Short.class, ProcesarTransferenciaOBPRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Cuenta_Beneficiario", scope = ProcesarTransferenciaOBPRequest.class)
    public JAXBElement<String> createProcesarTransferenciaOBPRequestCuentaBeneficiario(String value) {
        return new JAXBElement<String>(_ProcesarTransferenciaOBPRequestCuentaBeneficiario_QNAME, String.class, ProcesarTransferenciaOBPRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Tipo_Documento_Cliente", scope = ProcesarTransferenciaOBPRequest.class)
    public JAXBElement<String> createProcesarTransferenciaOBPRequestTipoDocumentoCliente(String value) {
        return new JAXBElement<String>(_ProcesarTransferenciaOBPRequestTipoDocumentoCliente_QNAME, String.class, ProcesarTransferenciaOBPRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Vinculo", scope = ProcesarTransferenciaOBPRequest.class)
    public JAXBElement<String> createProcesarTransferenciaOBPRequestVinculo(String value) {
        return new JAXBElement<String>(_ProcesarTransferenciaOBPRequestVinculo_QNAME, String.class, ProcesarTransferenciaOBPRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Observaciones", scope = ProcesarTransferenciaOBPRequest.class)
    public JAXBElement<String> createProcesarTransferenciaOBPRequestObservaciones(String value) {
        return new JAXBElement<String>(_ProcesarTransferenciaOBPRequestObservaciones_QNAME, String.class, ProcesarTransferenciaOBPRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Cuenta_Bco_Intermediario", scope = ProcesarTransferenciaOBPRequest.class)
    public JAXBElement<String> createProcesarTransferenciaOBPRequestCuentaBcoIntermediario(String value) {
        return new JAXBElement<String>(_ProcesarTransferenciaOBPRequestCuentaBcoIntermediario_QNAME, String.class, ProcesarTransferenciaOBPRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Banco_Intermediario", scope = ProcesarTransferenciaOBPRequest.class)
    public JAXBElement<String> createProcesarTransferenciaOBPRequestBancoIntermediario(String value) {
        return new JAXBElement<String>(_ProcesarTransferenciaOBPRequestBancoIntermediario_QNAME, String.class, ProcesarTransferenciaOBPRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Cta_altair", scope = ProcesarTransferenciaOBPRequest.class)
    public JAXBElement<String> createProcesarTransferenciaOBPRequestCtaAltair(String value) {
        return new JAXBElement<String>(_ProcesarTransferenciaOBPRequestCtaAltair_QNAME, String.class, ProcesarTransferenciaOBPRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Short }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Acepta_Ddjj", scope = ProcesarTransferenciaOBPRequest.class)
    public JAXBElement<Short> createProcesarTransferenciaOBPRequestAceptaDdjj(Short value) {
        return new JAXBElement<Short>(_ProcesarTransferenciaOBPRequestAceptaDdjj_QNAME, Short.class, ProcesarTransferenciaOBPRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Tipo_Operacion", scope = ProcesarTransferenciaOBPRequest.class)
    public JAXBElement<String> createProcesarTransferenciaOBPRequestTipoOperacion(String value) {
        return new JAXBElement<String>(_ProcesarTransferenciaOBPRequestTipoOperacion_QNAME, String.class, ProcesarTransferenciaOBPRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Concepto", scope = ProcesarTransferenciaOBPRequest.class)
    public JAXBElement<String> createProcesarTransferenciaOBPRequestConcepto(String value) {
        return new JAXBElement<String>(_ProcesarTransferenciaOBPRequestConcepto_QNAME, String.class, ProcesarTransferenciaOBPRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Banco_Beneficiario", scope = ProcesarTransferenciaOBPRequest.class)
    public JAXBElement<String> createProcesarTransferenciaOBPRequestBancoBeneficiario(String value) {
        return new JAXBElement<String>(_ProcesarTransferenciaOBPRequestBancoBeneficiario_QNAME, String.class, ProcesarTransferenciaOBPRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Short }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Tipo_Transferencia", scope = ProcesarTransferenciaOBPRequest.class)
    public JAXBElement<Short> createProcesarTransferenciaOBPRequestTipoTransferencia(Short value) {
        return new JAXBElement<Short>(_ProcesarTransferenciaOBPRequestTipoTransferencia_QNAME, Short.class, ProcesarTransferenciaOBPRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Beneficiario_Pais", scope = ProcesarTransferenciaOBPRequest.class)
    public JAXBElement<String> createProcesarTransferenciaOBPRequestBeneficiarioPais(String value) {
        return new JAXBElement<String>(_ProcesarTransferenciaOBPRequestBeneficiarioPais_QNAME, String.class, ProcesarTransferenciaOBPRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Importe_Transferencia", scope = ProcesarTransferenciaOBPRequest.class)
    public JAXBElement<BigDecimal> createProcesarTransferenciaOBPRequestImporteTransferencia(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_ProcesarTransferenciaOBPRequestImporteTransferencia_QNAME, BigDecimal.class, ProcesarTransferenciaOBPRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Canal", scope = ProcesarTransferenciaOBPRequest.class)
    public JAXBElement<String> createProcesarTransferenciaOBPRequestCanal(String value) {
        return new JAXBElement<String>(_ProcesarTransferenciaOBPRequestCanal_QNAME, String.class, ProcesarTransferenciaOBPRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Short }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Declaracion_Adicional", scope = ProcesarTransferenciaOBPRequest.class)
    public JAXBElement<Short> createProcesarTransferenciaOBPRequestDeclaracionAdicional(Short value) {
        return new JAXBElement<Short>(_ProcesarTransferenciaOBPRequestDeclaracionAdicional_QNAME, Short.class, ProcesarTransferenciaOBPRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Cuenta_Debito", scope = ProcesarTransferenciaOBPRequest.class)
    public JAXBElement<String> createProcesarTransferenciaOBPRequestCuentaDebito(String value) {
        return new JAXBElement<String>(_ProcesarTransferenciaOBPRequestCuentaDebito_QNAME, String.class, ProcesarTransferenciaOBPRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Razon_Social", scope = ProcesarTransferenciaOBPRequest.class)
    public JAXBElement<String> createProcesarTransferenciaOBPRequestRazonSocial(String value) {
        return new JAXBElement<String>(_ProcesarTransferenciaOBPRequestRazonSocial_QNAME, String.class, ProcesarTransferenciaOBPRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Beneficiario", scope = ProcesarTransferenciaOBPRequest.class)
    public JAXBElement<String> createProcesarTransferenciaOBPRequestBeneficiario(String value) {
        return new JAXBElement<String>(_ProcesarTransferenciaOBPRequestBeneficiario_QNAME, String.class, ProcesarTransferenciaOBPRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Estado_Transferencia", scope = ProcesarTransferenciaOBPRequest.class)
    public JAXBElement<String> createProcesarTransferenciaOBPRequestEstadoTransferencia(String value) {
        return new JAXBElement<String>(_ProcesarTransferenciaOBPRequestEstadoTransferencia_QNAME, String.class, ProcesarTransferenciaOBPRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Observaciones", scope = CargaDocRequest.class)
    public JAXBElement<String> createCargaDocRequestObservaciones(String value) {
        return new JAXBElement<String>(_ProcesarTransferenciaOBPRequestObservaciones_QNAME, String.class, CargaDocRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Nombre_Archivo", scope = CargaDocRequest.class)
    public JAXBElement<String> createCargaDocRequestNombreArchivo(String value) {
        return new JAXBElement<String>(_CargaDocRequestNombreArchivo_QNAME, String.class, CargaDocRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Nro_Transferencia", scope = CargaDocRequest.class)
    public JAXBElement<Integer> createCargaDocRequestNroTransferencia(Integer value) {
        return new JAXBElement<Integer>(_CargaDespachosRequestNroTransferencia_QNAME, Integer.class, CargaDocRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Tipo_Archivo", scope = CargaDocRequest.class)
    public JAXBElement<String> createCargaDocRequestTipoArchivo(String value) {
        return new JAXBElement<String>(_CargaDocRequestTipoArchivo_QNAME, String.class, CargaDocRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Imagen", scope = CargaDocRequest.class)
    public JAXBElement<byte[]> createCargaDocRequestImagen(byte[] value) {
        return new JAXBElement<byte[]>(_CargaDocRequestImagen_QNAME, byte[].class, CargaDocRequest.class, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Producto", scope = ConsultaConceptoRequest.class)
    public JAXBElement<Integer> createConsultaConceptoRequestProducto(Integer value) {
        return new JAXBElement<Integer>(_ObtenerMsgSwiftRequestProducto_QNAME, Integer.class, ConsultaConceptoRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Id_Concepto", scope = ConsultaConceptoRequest.class)
    public JAXBElement<Integer> createConsultaConceptoRequestIdConcepto(Integer value) {
        return new JAXBElement<Integer>(_ConsultaConceptoRequestIdConcepto_QNAME, Integer.class, ConsultaConceptoRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Canal", scope = ConsultaConceptoRequest.class)
    public JAXBElement<String> createConsultaConceptoRequestCanal(String value) {
        return new JAXBElement<String>(_ProcesarTransferenciaOBPRequestCanal_QNAME, String.class, ConsultaConceptoRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Cod_Concepto", scope = ConsultaConceptoRequest.class)
    public JAXBElement<String> createConsultaConceptoRequestCodConcepto(String value) {
        return new JAXBElement<String>(_ConsultaConceptoRequestCodConcepto_QNAME, String.class, ConsultaConceptoRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Banco", scope = CargaAgendaCuentaRequest.class)
    public JAXBElement<String> createCargaAgendaCuentaRequestBanco(String value) {
        return new JAXBElement<String>(_DesvinculaAgendaCuentaRequestBanco_QNAME, String.class, CargaAgendaCuentaRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Cuenta", scope = CargaAgendaCuentaRequest.class)
    public JAXBElement<String> createCargaAgendaCuentaRequestCuenta(String value) {
        return new JAXBElement<String>(_DesvinculaAgendaCuentaRequestCuenta_QNAME, String.class, CargaAgendaCuentaRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Nro_Transferencia", scope = ConsultaDetalleTrfOBPRequest.class)
    public JAXBElement<Integer> createConsultaDetalleTrfOBPRequestNroTransferencia(Integer value) {
        return new JAXBElement<Integer>(_CargaDespachosRequestNroTransferencia_QNAME, Integer.class, ConsultaDetalleTrfOBPRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Nro_Transferencia", scope = ConsultaDetalleTrfRequest.class)
    public JAXBElement<Integer> createConsultaDetalleTrfRequestNroTransferencia(Integer value) {
        return new JAXBElement<Integer>(_CargaDespachosRequestNroTransferencia_QNAME, Integer.class, ConsultaDetalleTrfRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Referencia_Cliente", scope = ProcesarTrfBPMRequest.class)
    public JAXBElement<String> createProcesarTrfBPMRequestReferenciaCliente(String value) {
        return new JAXBElement<String>(_ProcesarTransferenciaOBPRequestReferenciaCliente_QNAME, String.class, ProcesarTrfBPMRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Nro_Documento_Cliente", scope = ProcesarTrfBPMRequest.class)
    public JAXBElement<String> createProcesarTrfBPMRequestNroDocumentoCliente(String value) {
        return new JAXBElement<String>(_ProcesarTransferenciaOBPRequestNroDocumentoCliente_QNAME, String.class, ProcesarTrfBPMRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Inst_Vendido", scope = ProcesarTrfBPMRequest.class)
    public JAXBElement<String> createProcesarTrfBPMRequestInstVendido(String value) {
        return new JAXBElement<String>(_ProcesarTransferenciaOBPRequestInstVendido_QNAME, String.class, ProcesarTrfBPMRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Nro_Transferencia", scope = ProcesarTrfBPMRequest.class)
    public JAXBElement<Integer> createProcesarTrfBPMRequestNroTransferencia(Integer value) {
        return new JAXBElement<Integer>(_CargaDespachosRequestNroTransferencia_QNAME, Integer.class, ProcesarTrfBPMRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Inst_Recibido", scope = ProcesarTrfBPMRequest.class)
    public JAXBElement<String> createProcesarTrfBPMRequestInstRecibido(String value) {
        return new JAXBElement<String>(_ProcesarTransferenciaOBPRequestInstRecibido_QNAME, String.class, ProcesarTrfBPMRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Beneficiario_Domicilio", scope = ProcesarTrfBPMRequest.class)
    public JAXBElement<String> createProcesarTrfBPMRequestBeneficiarioDomicilio(String value) {
        return new JAXBElement<String>(_ProcesarTransferenciaOBPRequestBeneficiarioDomicilio_QNAME, String.class, ProcesarTrfBPMRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Tipo_Persona", scope = ProcesarTrfBPMRequest.class)
    public JAXBElement<String> createProcesarTrfBPMRequestTipoPersona(String value) {
        return new JAXBElement<String>(_ProcesarTransferenciaOBPRequestTipoPersona_QNAME, String.class, ProcesarTrfBPMRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Nro_Transferencia_Rel", scope = ProcesarTrfBPMRequest.class)
    public JAXBElement<Long> createProcesarTrfBPMRequestNroTransferenciaRel(Long value) {
        return new JAXBElement<Long>(_ProcesarTransferenciaOBPRequestNroTransferenciaRel_QNAME, Long.class, ProcesarTrfBPMRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Moneda", scope = ProcesarTrfBPMRequest.class)
    public JAXBElement<String> createProcesarTrfBPMRequestMoneda(String value) {
        return new JAXBElement<String>(_CargaDespachosRequestMoneda_QNAME, String.class, ProcesarTrfBPMRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Short }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Gastos", scope = ProcesarTrfBPMRequest.class)
    public JAXBElement<Short> createProcesarTrfBPMRequestGastos(Short value) {
        return new JAXBElement<Short>(_ProcesarTransferenciaOBPRequestGastos_QNAME, Short.class, ProcesarTrfBPMRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Nup_Cliente", scope = ProcesarTrfBPMRequest.class)
    public JAXBElement<String> createProcesarTrfBPMRequestNupCliente(String value) {
        return new JAXBElement<String>(_CargaAgendaRequestNupCliente_QNAME, String.class, ProcesarTrfBPMRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Cuenta_Beneficiario", scope = ProcesarTrfBPMRequest.class)
    public JAXBElement<String> createProcesarTrfBPMRequestCuentaBeneficiario(String value) {
        return new JAXBElement<String>(_ProcesarTransferenciaOBPRequestCuentaBeneficiario_QNAME, String.class, ProcesarTrfBPMRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Tipo_Documento_Cliente", scope = ProcesarTrfBPMRequest.class)
    public JAXBElement<String> createProcesarTrfBPMRequestTipoDocumentoCliente(String value) {
        return new JAXBElement<String>(_ProcesarTransferenciaOBPRequestTipoDocumentoCliente_QNAME, String.class, ProcesarTrfBPMRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Vinculo", scope = ProcesarTrfBPMRequest.class)
    public JAXBElement<String> createProcesarTrfBPMRequestVinculo(String value) {
        return new JAXBElement<String>(_ProcesarTransferenciaOBPRequestVinculo_QNAME, String.class, ProcesarTrfBPMRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Rubro_Benef", scope = ProcesarTrfBPMRequest.class)
    public JAXBElement<String> createProcesarTrfBPMRequestRubroBenef(String value) {
        return new JAXBElement<String>(_ProcesarTrfBPMRequestRubroBenef_QNAME, String.class, ProcesarTrfBPMRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Genera_Comprob", scope = ProcesarTrfBPMRequest.class)
    public JAXBElement<String> createProcesarTrfBPMRequestGeneraComprob(String value) {
        return new JAXBElement<String>(_ProcesarTrfBPMRequestGeneraComprob_QNAME, String.class, ProcesarTrfBPMRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Observaciones", scope = ProcesarTrfBPMRequest.class)
    public JAXBElement<String> createProcesarTrfBPMRequestObservaciones(String value) {
        return new JAXBElement<String>(_ProcesarTransferenciaOBPRequestObservaciones_QNAME, String.class, ProcesarTrfBPMRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Cuenta_Bco_Intermediario", scope = ProcesarTrfBPMRequest.class)
    public JAXBElement<String> createProcesarTrfBPMRequestCuentaBcoIntermediario(String value) {
        return new JAXBElement<String>(_ProcesarTransferenciaOBPRequestCuentaBcoIntermediario_QNAME, String.class, ProcesarTrfBPMRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Banco_Intermediario", scope = ProcesarTrfBPMRequest.class)
    public JAXBElement<String> createProcesarTrfBPMRequestBancoIntermediario(String value) {
        return new JAXBElement<String>(_ProcesarTransferenciaOBPRequestBancoIntermediario_QNAME, String.class, ProcesarTrfBPMRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Cta_altair", scope = ProcesarTrfBPMRequest.class)
    public JAXBElement<String> createProcesarTrfBPMRequestCtaAltair(String value) {
        return new JAXBElement<String>(_ProcesarTransferenciaOBPRequestCtaAltair_QNAME, String.class, ProcesarTrfBPMRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Short }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Acepta_Ddjj", scope = ProcesarTrfBPMRequest.class)
    public JAXBElement<Short> createProcesarTrfBPMRequestAceptaDdjj(Short value) {
        return new JAXBElement<Short>(_ProcesarTransferenciaOBPRequestAceptaDdjj_QNAME, Short.class, ProcesarTrfBPMRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Tipo_Operacion", scope = ProcesarTrfBPMRequest.class)
    public JAXBElement<String> createProcesarTrfBPMRequestTipoOperacion(String value) {
        return new JAXBElement<String>(_ProcesarTransferenciaOBPRequestTipoOperacion_QNAME, String.class, ProcesarTrfBPMRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Concepto", scope = ProcesarTrfBPMRequest.class)
    public JAXBElement<String> createProcesarTrfBPMRequestConcepto(String value) {
        return new JAXBElement<String>(_ProcesarTransferenciaOBPRequestConcepto_QNAME, String.class, ProcesarTrfBPMRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Banco_Beneficiario", scope = ProcesarTrfBPMRequest.class)
    public JAXBElement<String> createProcesarTrfBPMRequestBancoBeneficiario(String value) {
        return new JAXBElement<String>(_ProcesarTransferenciaOBPRequestBancoBeneficiario_QNAME, String.class, ProcesarTrfBPMRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Short }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Tipo_Transferencia", scope = ProcesarTrfBPMRequest.class)
    public JAXBElement<Short> createProcesarTrfBPMRequestTipoTransferencia(Short value) {
        return new JAXBElement<Short>(_ProcesarTransferenciaOBPRequestTipoTransferencia_QNAME, Short.class, ProcesarTrfBPMRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Beneficiario_Pais", scope = ProcesarTrfBPMRequest.class)
    public JAXBElement<String> createProcesarTrfBPMRequestBeneficiarioPais(String value) {
        return new JAXBElement<String>(_ProcesarTransferenciaOBPRequestBeneficiarioPais_QNAME, String.class, ProcesarTrfBPMRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Importe_Transferencia", scope = ProcesarTrfBPMRequest.class)
    public JAXBElement<BigDecimal> createProcesarTrfBPMRequestImporteTransferencia(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_ProcesarTransferenciaOBPRequestImporteTransferencia_QNAME, BigDecimal.class, ProcesarTrfBPMRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Canal", scope = ProcesarTrfBPMRequest.class)
    public JAXBElement<String> createProcesarTrfBPMRequestCanal(String value) {
        return new JAXBElement<String>(_ProcesarTransferenciaOBPRequestCanal_QNAME, String.class, ProcesarTrfBPMRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Short }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Declaracion_Adicional", scope = ProcesarTrfBPMRequest.class)
    public JAXBElement<Short> createProcesarTrfBPMRequestDeclaracionAdicional(Short value) {
        return new JAXBElement<Short>(_ProcesarTransferenciaOBPRequestDeclaracionAdicional_QNAME, Short.class, ProcesarTrfBPMRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Cuenta_Debito", scope = ProcesarTrfBPMRequest.class)
    public JAXBElement<String> createProcesarTrfBPMRequestCuentaDebito(String value) {
        return new JAXBElement<String>(_ProcesarTransferenciaOBPRequestCuentaDebito_QNAME, String.class, ProcesarTrfBPMRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Razon_Social", scope = ProcesarTrfBPMRequest.class)
    public JAXBElement<String> createProcesarTrfBPMRequestRazonSocial(String value) {
        return new JAXBElement<String>(_ProcesarTransferenciaOBPRequestRazonSocial_QNAME, String.class, ProcesarTrfBPMRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Beneficiario", scope = ProcesarTrfBPMRequest.class)
    public JAXBElement<String> createProcesarTrfBPMRequestBeneficiario(String value) {
        return new JAXBElement<String>(_ProcesarTransferenciaOBPRequestBeneficiario_QNAME, String.class, ProcesarTrfBPMRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Estado_Transferencia", scope = ProcesarTrfBPMRequest.class)
    public JAXBElement<String> createProcesarTrfBPMRequestEstadoTransferencia(String value) {
        return new JAXBElement<String>(_ProcesarTransferenciaOBPRequestEstadoTransferencia_QNAME, String.class, ProcesarTrfBPMRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Banco_Emisor", scope = BuscarDespachosRequest.class)
    public JAXBElement<String> createBuscarDespachosRequestBancoEmisor(String value) {
        return new JAXBElement<String>(_BuscarDespachosRequestBancoEmisor_QNAME, String.class, BuscarDespachosRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Monto_Desde", scope = BuscarDespachosRequest.class)
    public JAXBElement<BigDecimal> createBuscarDespachosRequestMontoDesde(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_BuscarDespachosRequestMontoDesde_QNAME, BigDecimal.class, BuscarDespachosRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Monto_Hasta", scope = BuscarDespachosRequest.class)
    public JAXBElement<BigDecimal> createBuscarDespachosRequestMontoHasta(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_BuscarDespachosRequestMontoHasta_QNAME, BigDecimal.class, BuscarDespachosRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Fecha_Desde", scope = BuscarDespachosRequest.class)
    public JAXBElement<String> createBuscarDespachosRequestFechaDesde(String value) {
        return new JAXBElement<String>(_BuscarDespachosRequestFechaDesde_QNAME, String.class, BuscarDespachosRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Tipo_Doc_Cliente", scope = BuscarDespachosRequest.class)
    public JAXBElement<String> createBuscarDespachosRequestTipoDocCliente(String value) {
        return new JAXBElement<String>(_BuscarDespachosRequestTipoDocCliente_QNAME, String.class, BuscarDespachosRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Nro_Doc_Cliente", scope = BuscarDespachosRequest.class)
    public JAXBElement<String> createBuscarDespachosRequestNroDocCliente(String value) {
        return new JAXBElement<String>(_BuscarDespachosRequestNroDocCliente_QNAME, String.class, BuscarDespachosRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Nro_Despacho", scope = BuscarDespachosRequest.class)
    public JAXBElement<String> createBuscarDespachosRequestNroDespacho(String value) {
        return new JAXBElement<String>(_CargaDespachosRequestNroDespacho_QNAME, String.class, BuscarDespachosRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Nominado", scope = BuscarDespachosRequest.class)
    public JAXBElement<String> createBuscarDespachosRequestNominado(String value) {
        return new JAXBElement<String>(_BuscarDespachosRequestNominado_QNAME, String.class, BuscarDespachosRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Fecha_Hasta", scope = BuscarDespachosRequest.class)
    public JAXBElement<String> createBuscarDespachosRequestFechaHasta(String value) {
        return new JAXBElement<String>(_BuscarDespachosRequestFechaHasta_QNAME, String.class, BuscarDespachosRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Moneda", scope = BuscarDespachosRequest.class)
    public JAXBElement<String> createBuscarDespachosRequestMoneda(String value) {
        return new JAXBElement<String>(_CargaDespachosRequestMoneda_QNAME, String.class, BuscarDespachosRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Monto_Desde", scope = ConsultaOperacionesRequest.class)
    public JAXBElement<BigDecimal> createConsultaOperacionesRequestMontoDesde(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_BuscarDespachosRequestMontoDesde_QNAME, BigDecimal.class, ConsultaOperacionesRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Referencia_Cliente", scope = ConsultaOperacionesRequest.class)
    public JAXBElement<String> createConsultaOperacionesRequestReferenciaCliente(String value) {
        return new JAXBElement<String>(_ProcesarTransferenciaOBPRequestReferenciaCliente_QNAME, String.class, ConsultaOperacionesRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Nro_Form", scope = ConsultaOperacionesRequest.class)
    public JAXBElement<Integer> createConsultaOperacionesRequestNroForm(Integer value) {
        return new JAXBElement<Integer>(_ConsultaOperacionesRequestNroForm_QNAME, Integer.class, ConsultaOperacionesRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Cantidad_Registros", scope = ConsultaOperacionesRequest.class)
    public JAXBElement<Integer> createConsultaOperacionesRequestCantidadRegistros(Integer value) {
        return new JAXBElement<Integer>(_ConsultaOperacionesRequestCantidadRegistros_QNAME, Integer.class, ConsultaOperacionesRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Fecha_Desde", scope = ConsultaOperacionesRequest.class)
    public JAXBElement<String> createConsultaOperacionesRequestFechaDesde(String value) {
        return new JAXBElement<String>(_BuscarDespachosRequestFechaDesde_QNAME, String.class, ConsultaOperacionesRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Tipo_Doc_Cliente", scope = ConsultaOperacionesRequest.class)
    public JAXBElement<String> createConsultaOperacionesRequestTipoDocCliente(String value) {
        return new JAXBElement<String>(_BuscarDespachosRequestTipoDocCliente_QNAME, String.class, ConsultaOperacionesRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Moneda", scope = ConsultaOperacionesRequest.class)
    public JAXBElement<String> createConsultaOperacionesRequestMoneda(String value) {
        return new JAXBElement<String>(_CargaDespachosRequestMoneda_QNAME, String.class, ConsultaOperacionesRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Nup_Cliente", scope = ConsultaOperacionesRequest.class)
    public JAXBElement<String> createConsultaOperacionesRequestNupCliente(String value) {
        return new JAXBElement<String>(_CargaAgendaRequestNupCliente_QNAME, String.class, ConsultaOperacionesRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Cuenta_Cliente", scope = ConsultaOperacionesRequest.class)
    public JAXBElement<String> createConsultaOperacionesRequestCuentaCliente(String value) {
        return new JAXBElement<String>(_ConsultaOperacionesRequestCuentaCliente_QNAME, String.class, ConsultaOperacionesRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Short }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Tipo_Transferencia", scope = ConsultaOperacionesRequest.class)
    public JAXBElement<Short> createConsultaOperacionesRequestTipoTransferencia(Short value) {
        return new JAXBElement<Short>(_ProcesarTransferenciaOBPRequestTipoTransferencia_QNAME, Short.class, ConsultaOperacionesRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Monto_Hasta", scope = ConsultaOperacionesRequest.class)
    public JAXBElement<BigDecimal> createConsultaOperacionesRequestMontoHasta(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_BuscarDespachosRequestMontoHasta_QNAME, BigDecimal.class, ConsultaOperacionesRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Estado_Transferencia", scope = ConsultaOperacionesRequest.class)
    public JAXBElement<String> createConsultaOperacionesRequestEstadoTransferencia(String value) {
        return new JAXBElement<String>(_ProcesarTransferenciaOBPRequestEstadoTransferencia_QNAME, String.class, ConsultaOperacionesRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Fecha_Hasta", scope = ConsultaOperacionesRequest.class)
    public JAXBElement<String> createConsultaOperacionesRequestFechaHasta(String value) {
        return new JAXBElement<String>(_BuscarDespachosRequestFechaHasta_QNAME, String.class, ConsultaOperacionesRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Nro_Doc_Cliente", scope = ConsultaOperacionesRequest.class)
    public JAXBElement<String> createConsultaOperacionesRequestNroDocCliente(String value) {
        return new JAXBElement<String>(_BuscarDespachosRequestNroDocCliente_QNAME, String.class, ConsultaOperacionesRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Pais_Que_Expide", scope = ProcesarNifRequest.class)
    public JAXBElement<String> createProcesarNifRequestPaisQueExpide(String value) {
        return new JAXBElement<String>(_ProcesarNifRequestPaisQueExpide_QNAME, String.class, ProcesarNifRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Accion", scope = ProcesarNifRequest.class)
    public JAXBElement<String> createProcesarNifRequestAccion(String value) {
        return new JAXBElement<String>(_ProcesarNifRequestAccion_QNAME, String.class, ProcesarNifRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Nacionalidad_Actual", scope = ProcesarNifRequest.class)
    public JAXBElement<String> createProcesarNifRequestNacionalidadActual(String value) {
        return new JAXBElement<String>(_ProcesarNifRequestNacionalidadActual_QNAME, String.class, ProcesarNifRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Ciudad", scope = ProcesarNifRequest.class)
    public JAXBElement<String> createProcesarNifRequestCiudad(String value) {
        return new JAXBElement<String>(_ProcesarNifRequestCiudad_QNAME, String.class, ProcesarNifRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Numero_Documento", scope = ProcesarNifRequest.class)
    public JAXBElement<String> createProcesarNifRequestNumeroDocumento(String value) {
        return new JAXBElement<String>(_ProcesarNifRequestNumeroDocumento_QNAME, String.class, ProcesarNifRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Tipo_Documento", scope = ProcesarNifRequest.class)
    public JAXBElement<String> createProcesarNifRequestTipoDocumento(String value) {
        return new JAXBElement<String>(_ProcesarNifRequestTipoDocumento_QNAME, String.class, ProcesarNifRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Nombre_Beneficiario", scope = ProcesarNifRequest.class)
    public JAXBElement<String> createProcesarNifRequestNombreBeneficiario(String value) {
        return new JAXBElement<String>(_ProcesarNifRequestNombreBeneficiario_QNAME, String.class, ProcesarNifRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Nup_Cliente", scope = ProcesarNifRequest.class)
    public JAXBElement<String> createProcesarNifRequestNupCliente(String value) {
        return new JAXBElement<String>(_CargaAgendaRequestNupCliente_QNAME, String.class, ProcesarNifRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Nif", scope = ProcesarNifRequest.class)
    public JAXBElement<String> createProcesarNifRequestNif(String value) {
        return new JAXBElement<String>(_ProcesarNifRequestNif_QNAME, String.class, ProcesarNifRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Pais_Nacimiento", scope = ProcesarNifRequest.class)
    public JAXBElement<String> createProcesarNifRequestPaisNacimiento(String value) {
        return new JAXBElement<String>(_ProcesarNifRequestPaisNacimiento_QNAME, String.class, ProcesarNifRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Residencia_Tribut", scope = ProcesarNifRequest.class)
    public JAXBElement<String> createProcesarNifRequestResidenciaTribut(String value) {
        return new JAXBElement<String>(_ProcesarNifRequestResidenciaTribut_QNAME, String.class, ProcesarNifRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Provincia", scope = ProcesarNifRequest.class)
    public JAXBElement<String> createProcesarNifRequestProvincia(String value) {
        return new JAXBElement<String>(_ProcesarNifRequestProvincia_QNAME, String.class, ProcesarNifRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Cuit_Rep_Legal", scope = ProcesarNifRequest.class)
    public JAXBElement<String> createProcesarNifRequestCuitRepLegal(String value) {
        return new JAXBElement<String>(_ProcesarNifRequestCuitRepLegal_QNAME, String.class, ProcesarNifRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Pais_Residencia", scope = ProcesarNifRequest.class)
    public JAXBElement<String> createProcesarNifRequestPaisResidencia(String value) {
        return new JAXBElement<String>(_ProcesarNifRequestPaisResidencia_QNAME, String.class, ProcesarNifRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Canal", scope = ProcesarNifRequest.class)
    public JAXBElement<String> createProcesarNifRequestCanal(String value) {
        return new JAXBElement<String>(_ProcesarTransferenciaOBPRequestCanal_QNAME, String.class, ProcesarNifRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Clave_Identificacion", scope = ProcesarNifRequest.class)
    public JAXBElement<String> createProcesarNifRequestClaveIdentificacion(String value) {
        return new JAXBElement<String>(_ProcesarNifRequestClaveIdentificacion_QNAME, String.class, ProcesarNifRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Representante_Legal", scope = ProcesarNifRequest.class)
    public JAXBElement<String> createProcesarNifRequestRepresentanteLegal(String value) {
        return new JAXBElement<String>(_ProcesarNifRequestRepresentanteLegal_QNAME, String.class, ProcesarNifRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Fecha_Nacimiento", scope = ProcesarNifRequest.class)
    public JAXBElement<String> createProcesarNifRequestFechaNacimiento(String value) {
        return new JAXBElement<String>(_ProcesarNifRequestFechaNacimiento_QNAME, String.class, ProcesarNifRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Direccion", scope = ProcesarNifRequest.class)
    public JAXBElement<String> createProcesarNifRequestDireccion(String value) {
        return new JAXBElement<String>(_ProcesarNifRequestDireccion_QNAME, String.class, ProcesarNifRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Nro_Hoja", scope = BorrarDocRequest.class)
    public JAXBElement<Integer> createBorrarDocRequestNroHoja(Integer value) {
        return new JAXBElement<Integer>(_BorrarDocRequestNroHoja_QNAME, Integer.class, BorrarDocRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Nro_Transferencia", scope = BorrarDocRequest.class)
    public JAXBElement<Integer> createBorrarDocRequestNroTransferencia(Integer value) {
        return new JAXBElement<Integer>(_CargaDespachosRequestNroTransferencia_QNAME, Integer.class, BorrarDocRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Tipo_Declaracion", scope = ConsultaMotExcepcionRequest.class)
    public JAXBElement<String> createConsultaMotExcepcionRequestTipoDeclaracion(String value) {
        return new JAXBElement<String>(_CargaDespachosRequestTipoDeclaracion_QNAME, String.class, ConsultaMotExcepcionRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Concepto", scope = ValidarPosicionRequest.class)
    public JAXBElement<String> createValidarPosicionRequestConcepto(String value) {
        return new JAXBElement<String>(_ProcesarTransferenciaOBPRequestConcepto_QNAME, String.class, ValidarPosicionRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Posicion", scope = ValidarPosicionRequest.class)
    public JAXBElement<String> createValidarPosicionRequestPosicion(String value) {
        return new JAXBElement<String>(_ValidarPosicionRequestPosicion_QNAME, String.class, ValidarPosicionRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Doble_Imp_Pais", scope = ProcesarTrfRequest.class)
    public JAXBElement<String> createProcesarTrfRequestDobleImpPais(String value) {
        return new JAXBElement<String>(_ProcesarTrfRequestDobleImpPais_QNAME, String.class, ProcesarTrfRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Short }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Iva", scope = ProcesarTrfRequest.class)
    public JAXBElement<Short> createProcesarTrfRequestIva(Short value) {
        return new JAXBElement<Short>(_ProcesarTrfRequestIva_QNAME, Short.class, ProcesarTrfRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Referencia_Cliente", scope = ProcesarTrfRequest.class)
    public JAXBElement<String> createProcesarTrfRequestReferenciaCliente(String value) {
        return new JAXBElement<String>(_ProcesarTransferenciaOBPRequestReferenciaCliente_QNAME, String.class, ProcesarTrfRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "P_NRO_FORM_REL", scope = ProcesarTrfRequest.class)
    public JAXBElement<Long> createProcesarTrfRequestPNROFORMREL(Long value) {
        return new JAXBElement<Long>(_ProcesarTrfRequestPNROFORMREL_QNAME, Long.class, ProcesarTrfRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Nro_Transferencia", scope = ProcesarTrfRequest.class)
    public JAXBElement<Integer> createProcesarTrfRequestNroTransferencia(Integer value) {
        return new JAXBElement<Integer>(_CargaDespachosRequestNroTransferencia_QNAME, Integer.class, ProcesarTrfRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Id_Cond_Venta", scope = ProcesarTrfRequest.class)
    public JAXBElement<Long> createProcesarTrfRequestIdCondVenta(Long value) {
        return new JAXBElement<Long>(_ProcesarTrfRequestIdCondVenta_QNAME, Long.class, ProcesarTrfRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Inv_Acre", scope = ProcesarTrfRequest.class)
    public JAXBElement<String> createProcesarTrfRequestInvAcre(String value) {
        return new JAXBElement<String>(_ProcesarTrfRequestInvAcre_QNAME, String.class, ProcesarTrfRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "No_Retencion_Art14", scope = ProcesarTrfRequest.class)
    public JAXBElement<String> createProcesarTrfRequestNoRetencionArt14(String value) {
        return new JAXBElement<String>(_ProcesarTrfRequestNoRetencionArt14_QNAME, String.class, ProcesarTrfRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Short }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Gastos", scope = ProcesarTrfRequest.class)
    public JAXBElement<Short> createProcesarTrfRequestGastos(Short value) {
        return new JAXBElement<Short>(_ProcesarTransferenciaOBPRequestGastos_QNAME, Short.class, ProcesarTrfRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "No_Retencion_Articulo", scope = ProcesarTrfRequest.class)
    public JAXBElement<String> createProcesarTrfRequestNoRetencionArticulo(String value) {
        return new JAXBElement<String>(_ProcesarTrfRequestNoRetencionArticulo_QNAME, String.class, ProcesarTrfRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Short }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Id_Alicuota", scope = ProcesarTrfRequest.class)
    public JAXBElement<Short> createProcesarTrfRequestIdAlicuota(Short value) {
        return new JAXBElement<Short>(_ProcesarTrfRequestIdAlicuota_QNAME, Short.class, ProcesarTrfRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Cuenta_Beneficiario", scope = ProcesarTrfRequest.class)
    public JAXBElement<String> createProcesarTrfRequestCuentaBeneficiario(String value) {
        return new JAXBElement<String>(_ProcesarTransferenciaOBPRequestCuentaBeneficiario_QNAME, String.class, ProcesarTrfRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Tipo_Documento_Cliente", scope = ProcesarTrfRequest.class)
    public JAXBElement<String> createProcesarTrfRequestTipoDocumentoCliente(String value) {
        return new JAXBElement<String>(_ProcesarTransferenciaOBPRequestTipoDocumentoCliente_QNAME, String.class, ProcesarTrfRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Otros_Int_Finan", scope = ProcesarTrfRequest.class)
    public JAXBElement<String> createProcesarTrfRequestOtrosIntFinan(String value) {
        return new JAXBElement<String>(_ProcesarTrfRequestOtrosIntFinan_QNAME, String.class, ProcesarTrfRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfCargaDespachosRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Despachos", scope = ProcesarTrfRequest.class)
    public JAXBElement<ArrayOfCargaDespachosRequest> createProcesarTrfRequestDespachos(ArrayOfCargaDespachosRequest value) {
        return new JAXBElement<ArrayOfCargaDespachosRequest>(_ProcesarTrfRequestDespachos_QNAME, ArrayOfCargaDespachosRequest.class, ProcesarTrfRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Fecha_Emb_Estimada", scope = ProcesarTrfRequest.class)
    public JAXBElement<String> createProcesarTrfRequestFechaEmbEstimada(String value) {
        return new JAXBElement<String>(_ProcesarTrfRequestFechaEmbEstimada_QNAME, String.class, ProcesarTrfRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Cuit_Benef", scope = ProcesarTrfRequest.class)
    public JAXBElement<String> createProcesarTrfRequestCuitBenef(String value) {
        return new JAXBElement<String>(_ProcesarTrfRequestCuitBenef_QNAME, String.class, ProcesarTrfRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Id_Banco_Beneficiario", scope = ProcesarTrfRequest.class)
    public JAXBElement<String> createProcesarTrfRequestIdBancoBeneficiario(String value) {
        return new JAXBElement<String>(_ProcesarTrfRequestIdBancoBeneficiario_QNAME, String.class, ProcesarTrfRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Observaciones", scope = ProcesarTrfRequest.class)
    public JAXBElement<String> createProcesarTrfRequestObservaciones(String value) {
        return new JAXBElement<String>(_ProcesarTransferenciaOBPRequestObservaciones_QNAME, String.class, ProcesarTrfRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Cuenta_Bco_Intermediario", scope = ProcesarTrfRequest.class)
    public JAXBElement<String> createProcesarTrfRequestCuentaBcoIntermediario(String value) {
        return new JAXBElement<String>(_ProcesarTransferenciaOBPRequestCuentaBcoIntermediario_QNAME, String.class, ProcesarTrfRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Short }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "No_Corresp_Int_Finan", scope = ProcesarTrfRequest.class)
    public JAXBElement<Short> createProcesarTrfRequestNoCorrespIntFinan(Short value) {
        return new JAXBElement<Short>(_ProcesarTrfRequestNoCorrespIntFinan_QNAME, Short.class, ProcesarTrfRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Tipo_Operacion", scope = ProcesarTrfRequest.class)
    public JAXBElement<String> createProcesarTrfRequestTipoOperacion(String value) {
        return new JAXBElement<String>(_ProcesarTransferenciaOBPRequestTipoOperacion_QNAME, String.class, ProcesarTrfRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Short }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Emp_Vinculada_Op1", scope = ProcesarTrfRequest.class)
    public JAXBElement<Short> createProcesarTrfRequestEmpVinculadaOp1(Short value) {
        return new JAXBElement<Short>(_ProcesarTrfRequestEmpVinculadaOp1_QNAME, Short.class, ProcesarTrfRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Short }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Emp_Vinculada_Op2", scope = ProcesarTrfRequest.class)
    public JAXBElement<Short> createProcesarTrfRequestEmpVinculadaOp2(Short value) {
        return new JAXBElement<Short>(_ProcesarTrfRequestEmpVinculadaOp2_QNAME, Short.class, ProcesarTrfRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Short }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Emp_Vinculada_Op3", scope = ProcesarTrfRequest.class)
    public JAXBElement<Short> createProcesarTrfRequestEmpVinculadaOp3(Short value) {
        return new JAXBElement<Short>(_ProcesarTrfRequestEmpVinculadaOp3_QNAME, Short.class, ProcesarTrfRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Short }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Registro_Inpi", scope = ProcesarTrfRequest.class)
    public JAXBElement<Short> createProcesarTrfRequestRegistroInpi(Short value) {
        return new JAXBElement<Short>(_ProcesarTrfRequestRegistroInpi_QNAME, Short.class, ProcesarTrfRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Nro_Form_Inv", scope = ProcesarTrfRequest.class)
    public JAXBElement<String> createProcesarTrfRequestNroFormInv(String value) {
        return new JAXBElement<String>(_ProcesarTrfRequestNroFormInv_QNAME, String.class, ProcesarTrfRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Short }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Emp_Vinculada_Op4", scope = ProcesarTrfRequest.class)
    public JAXBElement<Short> createProcesarTrfRequestEmpVinculadaOp4(Short value) {
        return new JAXBElement<Short>(_ProcesarTrfRequestEmpVinculadaOp4_QNAME, Short.class, ProcesarTrfRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Short }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Opcion_No_Iva", scope = ProcesarTrfRequest.class)
    public JAXBElement<Short> createProcesarTrfRequestOpcionNoIva(Short value) {
        return new JAXBElement<Short>(_ProcesarTrfRequestOpcionNoIva_QNAME, Short.class, ProcesarTrfRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Alicuota_Iva", scope = ProcesarTrfRequest.class)
    public JAXBElement<BigDecimal> createProcesarTrfRequestAlicuotaIva(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_ProcesarTrfRequestAlicuotaIva_QNAME, BigDecimal.class, ProcesarTrfRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Concepto", scope = ProcesarTrfRequest.class)
    public JAXBElement<String> createProcesarTrfRequestConcepto(String value) {
        return new JAXBElement<String>(_ProcesarTransferenciaOBPRequestConcepto_QNAME, String.class, ProcesarTrfRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Short }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Emp_Vinculada_Op5", scope = ProcesarTrfRequest.class)
    public JAXBElement<Short> createProcesarTrfRequestEmpVinculadaOp5(Short value) {
        return new JAXBElement<Short>(_ProcesarTrfRequestEmpVinculadaOp5_QNAME, Short.class, ProcesarTrfRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Monto_Ret_Ganacias", scope = ProcesarTrfRequest.class)
    public JAXBElement<BigDecimal> createProcesarTrfRequestMontoRetGanacias(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_ProcesarTrfRequestMontoRetGanacias_QNAME, BigDecimal.class, ProcesarTrfRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Alicuota_Ganancias", scope = ProcesarTrfRequest.class)
    public JAXBElement<BigDecimal> createProcesarTrfRequestAlicuotaGanancias(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_ProcesarTrfRequestAlicuotaGanancias_QNAME, BigDecimal.class, ProcesarTrfRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Importe_Transferencia", scope = ProcesarTrfRequest.class)
    public JAXBElement<BigDecimal> createProcesarTrfRequestImporteTransferencia(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_ProcesarTransferenciaOBPRequestImporteTransferencia_QNAME, BigDecimal.class, ProcesarTrfRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Short }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Declaracion_Adicional", scope = ProcesarTrfRequest.class)
    public JAXBElement<Short> createProcesarTrfRequestDeclaracionAdicional(Short value) {
        return new JAXBElement<Short>(_ProcesarTransferenciaOBPRequestDeclaracionAdicional_QNAME, Short.class, ProcesarTrfRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Autogestion", scope = ProcesarTrfRequest.class)
    public JAXBElement<String> createProcesarTrfRequestAutogestion(String value) {
        return new JAXBElement<String>(_ProcesarTrfRequestAutogestion_QNAME, String.class, ProcesarTrfRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "No_Retencion_Pais", scope = ProcesarTrfRequest.class)
    public JAXBElement<String> createProcesarTrfRequestNoRetencionPais(String value) {
        return new JAXBElement<String>(_ProcesarTrfRequestNoRetencionPais_QNAME, String.class, ProcesarTrfRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Pos_Arancelaria", scope = ProcesarTrfRequest.class)
    public JAXBElement<String> createProcesarTrfRequestPosArancelaria(String value) {
        return new JAXBElement<String>(_ProcesarTrfRequestPosArancelaria_QNAME, String.class, ProcesarTrfRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Nro_Documento_Cliente", scope = ProcesarTrfRequest.class)
    public JAXBElement<String> createProcesarTrfRequestNroDocumentoCliente(String value) {
        return new JAXBElement<String>(_ProcesarTransferenciaOBPRequestNroDocumentoCliente_QNAME, String.class, ProcesarTrfRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Inst_Vendido", scope = ProcesarTrfRequest.class)
    public JAXBElement<String> createProcesarTrfRequestInstVendido(String value) {
        return new JAXBElement<String>(_ProcesarTransferenciaOBPRequestInstVendido_QNAME, String.class, ProcesarTrfRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Fecha_Embarque", scope = ProcesarTrfRequest.class)
    public JAXBElement<String> createProcesarTrfRequestFechaEmbarque(String value) {
        return new JAXBElement<String>(_CargaDespachosRequestFechaEmbarque_QNAME, String.class, ProcesarTrfRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Inst_Recibido", scope = ProcesarTrfRequest.class)
    public JAXBElement<String> createProcesarTrfRequestInstRecibido(String value) {
        return new JAXBElement<String>(_ProcesarTransferenciaOBPRequestInstRecibido_QNAME, String.class, ProcesarTrfRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Nif_gan", scope = ProcesarTrfRequest.class)
    public JAXBElement<Long> createProcesarTrfRequestNifGan(Long value) {
        return new JAXBElement<Long>(_ProcesarTrfRequestNifGan_QNAME, Long.class, ProcesarTrfRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Moneda", scope = ProcesarTrfRequest.class)
    public JAXBElement<String> createProcesarTrfRequestMoneda(String value) {
        return new JAXBElement<String>(_CargaDespachosRequestMoneda_QNAME, String.class, ProcesarTrfRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Nup_Cliente", scope = ProcesarTrfRequest.class)
    public JAXBElement<String> createProcesarTrfRequestNupCliente(String value) {
        return new JAXBElement<String>(_CargaAgendaRequestNupCliente_QNAME, String.class, ProcesarTrfRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Id_Beneficiario", scope = ProcesarTrfRequest.class)
    public JAXBElement<String> createProcesarTrfRequestIdBeneficiario(String value) {
        return new JAXBElement<String>(_ProcesarTrfRequestIdBeneficiario_QNAME, String.class, ProcesarTrfRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Short }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Aplica_Int_Finan", scope = ProcesarTrfRequest.class)
    public JAXBElement<Short> createProcesarTrfRequestAplicaIntFinan(Short value) {
        return new JAXBElement<Short>(_ProcesarTrfRequestAplicaIntFinan_QNAME, Short.class, ProcesarTrfRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Base_Imp_Iva", scope = ProcesarTrfRequest.class)
    public JAXBElement<BigDecimal> createProcesarTrfRequestBaseImpIva(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_ProcesarTrfRequestBaseImpIva_QNAME, BigDecimal.class, ProcesarTrfRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Short }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Con_Dj4834", scope = ProcesarTrfRequest.class)
    public JAXBElement<Short> createProcesarTrfRequestConDj4834(Short value) {
        return new JAXBElement<Short>(_ProcesarTrfRequestConDj4834_QNAME, Short.class, ProcesarTrfRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Condicion_Iva", scope = ProcesarTrfRequest.class)
    public JAXBElement<String> createProcesarTrfRequestCondicionIva(String value) {
        return new JAXBElement<String>(_ProcesarTrfRequestCondicionIva_QNAME, String.class, ProcesarTrfRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Base_Imp_Ganancias", scope = ProcesarTrfRequest.class)
    public JAXBElement<BigDecimal> createProcesarTrfRequestBaseImpGanancias(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_ProcesarTrfRequestBaseImpGanancias_QNAME, BigDecimal.class, ProcesarTrfRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Cargo_Ganancias", scope = ProcesarTrfRequest.class)
    public JAXBElement<String> createProcesarTrfRequestCargoGanancias(String value) {
        return new JAXBElement<String>(_ProcesarTrfRequestCargoGanancias_QNAME, String.class, ProcesarTrfRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Monto_Ret_Iva", scope = ProcesarTrfRequest.class)
    public JAXBElement<BigDecimal> createProcesarTrfRequestMontoRetIva(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_ProcesarTrfRequestMontoRetIva_QNAME, BigDecimal.class, ProcesarTrfRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Texto_Dj4834", scope = ProcesarTrfRequest.class)
    public JAXBElement<String> createProcesarTrfRequestTextoDj4834(String value) {
        return new JAXBElement<String>(_ProcesarTrfRequestTextoDj4834_QNAME, String.class, ProcesarTrfRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Short }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Retiene_Ganancias", scope = ProcesarTrfRequest.class)
    public JAXBElement<Short> createProcesarTrfRequestRetieneGanancias(Short value) {
        return new JAXBElement<Short>(_ProcesarTrfRequestRetieneGanancias_QNAME, Short.class, ProcesarTrfRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Id_Banco_Intermediario", scope = ProcesarTrfRequest.class)
    public JAXBElement<String> createProcesarTrfRequestIdBancoIntermediario(String value) {
        return new JAXBElement<String>(_ProcesarTrfRequestIdBancoIntermediario_QNAME, String.class, ProcesarTrfRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Short }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Retiene_Iva", scope = ProcesarTrfRequest.class)
    public JAXBElement<Short> createProcesarTrfRequestRetieneIva(Short value) {
        return new JAXBElement<Short>(_ProcesarTrfRequestRetieneIva_QNAME, Short.class, ProcesarTrfRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Cta_altair", scope = ProcesarTrfRequest.class)
    public JAXBElement<String> createProcesarTrfRequestCtaAltair(String value) {
        return new JAXBElement<String>(_ProcesarTransferenciaOBPRequestCtaAltair_QNAME, String.class, ProcesarTrfRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Short }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Acepta_Ddjj", scope = ProcesarTrfRequest.class)
    public JAXBElement<Short> createProcesarTrfRequestAceptaDdjj(Short value) {
        return new JAXBElement<Short>(_ProcesarTransferenciaOBPRequestAceptaDdjj_QNAME, Short.class, ProcesarTrfRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Motivo_Rechazo", scope = ProcesarTrfRequest.class)
    public JAXBElement<String> createProcesarTrfRequestMotivoRechazo(String value) {
        return new JAXBElement<String>(_ProcesarTrfRequestMotivoRechazo_QNAME, String.class, ProcesarTrfRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "No_Retencion_Motivo", scope = ProcesarTrfRequest.class)
    public JAXBElement<String> createProcesarTrfRequestNoRetencionMotivo(String value) {
        return new JAXBElement<String>(_ProcesarTrfRequestNoRetencionMotivo_QNAME, String.class, ProcesarTrfRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Doble_Imp_Ganancias", scope = ProcesarTrfRequest.class)
    public JAXBElement<String> createProcesarTrfRequestDobleImpGanancias(String value) {
        return new JAXBElement<String>(_ProcesarTrfRequestDobleImpGanancias_QNAME, String.class, ProcesarTrfRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Doble_Imp_Articulo", scope = ProcesarTrfRequest.class)
    public JAXBElement<String> createProcesarTrfRequestDobleImpArticulo(String value) {
        return new JAXBElement<String>(_ProcesarTrfRequestDobleImpArticulo_QNAME, String.class, ProcesarTrfRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Short }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Opcion_No_Retencion", scope = ProcesarTrfRequest.class)
    public JAXBElement<Short> createProcesarTrfRequestOpcionNoRetencion(Short value) {
        return new JAXBElement<Short>(_ProcesarTrfRequestOpcionNoRetencion_QNAME, Short.class, ProcesarTrfRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Otros_No_Iva", scope = ProcesarTrfRequest.class)
    public JAXBElement<String> createProcesarTrfRequestOtrosNoIva(String value) {
        return new JAXBElement<String>(_ProcesarTrfRequestOtrosNoIva_QNAME, String.class, ProcesarTrfRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Short }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Tipo_Transferencia", scope = ProcesarTrfRequest.class)
    public JAXBElement<Short> createProcesarTrfRequestTipoTransferencia(Short value) {
        return new JAXBElement<Short>(_ProcesarTransferenciaOBPRequestTipoTransferencia_QNAME, Short.class, ProcesarTrfRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Short }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Opcion_Int_Finan", scope = ProcesarTrfRequest.class)
    public JAXBElement<Short> createProcesarTrfRequestOpcionIntFinan(Short value) {
        return new JAXBElement<Short>(_ProcesarTrfRequestOpcionIntFinan_QNAME, Short.class, ProcesarTrfRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Short }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Opcion_Gan", scope = ProcesarTrfRequest.class)
    public JAXBElement<Short> createProcesarTrfRequestOpcionGan(Short value) {
        return new JAXBElement<Short>(_ProcesarTrfRequestOpcionGan_QNAME, Short.class, ProcesarTrfRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Cuenta_Debito", scope = ProcesarTrfRequest.class)
    public JAXBElement<String> createProcesarTrfRequestCuentaDebito(String value) {
        return new JAXBElement<String>(_ProcesarTransferenciaOBPRequestCuentaDebito_QNAME, String.class, ProcesarTrfRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Estado_Transferencia", scope = ProcesarTrfRequest.class)
    public JAXBElement<String> createProcesarTrfRequestEstadoTransferencia(String value) {
        return new JAXBElement<String>(_ProcesarTransferenciaOBPRequestEstadoTransferencia_QNAME, String.class, ProcesarTrfRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Banco", scope = ConsultaAgendaCuentaRequest.class)
    public JAXBElement<String> createConsultaAgendaCuentaRequestBanco(String value) {
        return new JAXBElement<String>(_DesvinculaAgendaCuentaRequestBanco_QNAME, String.class, ConsultaAgendaCuentaRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Id_Imagen", scope = ConsultaImagenTrfRequest.class)
    public JAXBElement<Integer> createConsultaImagenTrfRequestIdImagen(Integer value) {
        return new JAXBElement<Integer>(_ConsultaImagenTrfRequestIdImagen_QNAME, Integer.class, ConsultaImagenTrfRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Nro_Transferencia", scope = ConsultaImagenTrfRequest.class)
    public JAXBElement<Integer> createConsultaImagenTrfRequestNroTransferencia(Integer value) {
        return new JAXBElement<Integer>(_CargaDespachosRequestNroTransferencia_QNAME, Integer.class, ConsultaImagenTrfRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Producto", scope = ConceptosPorTipoRequest.class)
    public JAXBElement<String> createConceptosPorTipoRequestProducto(String value) {
        return new JAXBElement<String>(_ObtenerMsgSwiftRequestProducto_QNAME, String.class, ConceptosPorTipoRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Canal", scope = ConceptosPorTipoRequest.class)
    public JAXBElement<String> createConceptosPorTipoRequestCanal(String value) {
        return new JAXBElement<String>(_ProcesarTransferenciaOBPRequestCanal_QNAME, String.class, ConceptosPorTipoRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Tipo_Concepto", scope = ConceptosPorTipoRequest.class)
    public JAXBElement<String> createConceptosPorTipoRequestTipoConcepto(String value) {
        return new JAXBElement<String>(_ConceptosPorTipoRequestTipoConcepto_QNAME, String.class, ConceptosPorTipoRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Nup", scope = ValidarNIFxNUPRequest.class)
    public JAXBElement<String> createValidarNIFxNUPRequestNup(String value) {
        return new JAXBElement<String>(_ValidarNIFxNUPRequestNup_QNAME, String.class, ValidarNIFxNUPRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", name = "Nif", scope = ValidarNIFxNUPRequest.class)
    public JAXBElement<String> createValidarNIFxNUPRequestNif(String value) {
        return new JAXBElement<String>(_ProcesarNifRequestNif_QNAME, String.class, ValidarNIFxNUPRequest.class, value);
    }

}
