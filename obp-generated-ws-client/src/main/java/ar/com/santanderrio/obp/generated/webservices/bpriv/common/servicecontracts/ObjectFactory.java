
package ar.com.santanderrio.obp.generated.webservices.bpriv.common.servicecontracts;

import java.math.BigDecimal;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ar.com.santanderrio.obp.generated.webservices.bpriv.common.servicecontracts package. 
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

    private final static QName _BPCUENTASCNSMOVIMIENTOSPARAMETER_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", "BP_CUENTAS_CNS_MOVIMIENTOS_PARAMETER");
    private final static QName _BPORDENESCMBESTADOPARAMETER_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", "BP_ORDENES_CMB_ESTADO_PARAMETER");
    private final static QName _InsertarOperacionCambioOBParameter_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", "InsertarOperacionCambioOBParameter");
    private final static QName _BPCUENTASCNSATITPARAMETER_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", "BP_CUENTAS_CNS_ATIT_PARAMETER");
    private final static QName _BPORDENESCMBALTAPARAMETER_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", "BP_ORDENES_CMB_ALTA_PARAMETER");
    private final static QName _InsertarOperacionCambioParameter_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", "InsertarOperacionCambioParameter");
    private final static QName _InsertarTransferenciaEntreBancosOBParameter_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", "InsertarTransferenciaEntreBancosOBParameter");
    private final static QName _InsertarTransferenciaEntreBancosParameter_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", "InsertarTransferenciaEntreBancosParameter");
    private final static QName _BPCUENTASCNSSALDOSPARAMETER_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", "BP_CUENTAS_CNS_SALDOS_PARAMETER");
    private final static QName _InsertarTransferenciaRIORIOParameter_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", "InsertarTransferenciaRIORIOParameter");
    private final static QName _InsertarTransferenciaRIORIOOBParameter_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", "InsertarTransferenciaRIORIOOBParameter");
    private final static QName _BPCUENTASCNSROSSIPARAMETER_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", "BP_CUENTAS_CNS_ROSSI_PARAMETER");
    private final static QName _BPCUENTASCNSCUADREPERFILPARAMETER_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", "BP_CUENTAS_CNS_CUADREPERFIL_PARAMETER");
    private final static QName _BPCUENTASCMBGRABAMOVPARAMETER_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", "BP_CUENTAS_CMB_GRABAMOV_PARAMETER");
    private final static QName _BPORDENESCMBESTADOPARAMETERTipoOrd_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", "TipoOrd");
    private final static QName _BPORDENESCMBESTADOPARAMETERCodSist_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", "CodSist");
    private final static QName _BPORDENESCMBESTADOPARAMETERNumOrden_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", "NumOrden");
    private final static QName _BPORDENESCMBESTADOPARAMETERCostOrd_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", "CostOrd");
    private final static QName _BPORDENESCMBESTADOPARAMETEREstado_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", "Estado");
    private final static QName _BPORDENESCMBESTADOPARAMETERBr_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", "Br");
    private final static QName _InsertarTransferenciaEntreBancosOBParameterOper_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", "Oper");
    private final static QName _InsertarTransferenciaEntreBancosOBParameterCuentaAltairDest_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", "CuentaAltairDest");
    private final static QName _InsertarTransferenciaEntreBancosOBParameterEntidadDest_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", "EntidadDest");
    private final static QName _InsertarTransferenciaEntreBancosOBParameterModoTransf_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", "ModoTransf");
    private final static QName _InsertarTransferenciaEntreBancosOBParameterObservaciones_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", "Observaciones");
    private final static QName _InsertarTransferenciaEntreBancosOBParameterCuentaAltairOrig_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", "CuentaAltairOrig");
    private final static QName _InsertarTransferenciaEntreBancosOBParameterConcepto_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", "Concepto");
    private final static QName _InsertarTransferenciaEntreBancosOBParameterTipoDocDest_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", "TipoDocDest");
    private final static QName _InsertarTransferenciaEntreBancosOBParameterImporte_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", "Importe");
    private final static QName _InsertarTransferenciaEntreBancosOBParameterSucursalDest_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", "SucursalDest");
    private final static QName _InsertarTransferenciaEntreBancosOBParameterTipoCuentaDest_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", "TipoCuentaDest");
    private final static QName _InsertarTransferenciaEntreBancosOBParameterCbuDest_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", "CbuDest");
    private final static QName _InsertarTransferenciaEntreBancosOBParameterBeneficiario_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", "Beneficiario");
    private final static QName _InsertarTransferenciaEntreBancosOBParameterDocumentoOrig_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", "DocumentoOrig");
    private final static QName _InsertarTransferenciaEntreBancosOBParameterDocumentoDest_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", "DocumentoDest");
    private final static QName _InsertarTransferenciaEntreBancosOBParameterMoneda_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", "Moneda");
    private final static QName _BPCUENTASCNSMOVIMIENTOSPARAMETERCuenta_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", "Cuenta");
    private final static QName _BPCUENTASCNSMOVIMIENTOSPARAMETERCanal_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", "Canal");
    private final static QName _BPCUENTASCNSMOVIMIENTOSPARAMETERDivisa_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", "Divisa");
    private final static QName _BPORDENESCMBALTAPARAMETERNumMae_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", "NumMae");
    private final static QName _BPORDENESCMBALTAPARAMETERSector_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", "Sector");
    private final static QName _BPORDENESCMBALTAPARAMETERHoraEmision_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", "HoraEmision");
    private final static QName _BPORDENESCMBALTAPARAMETERFcitipocomision_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", "Fcitipocomision");
    private final static QName _BPORDENESCMBALTAPARAMETERPreciomercado_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", "Preciomercado");
    private final static QName _BPORDENESCMBALTAPARAMETEREspecie_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", "Especie");
    private final static QName _BPORDENESCMBALTAPARAMETERCheckMiami_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", "CheckMiami");
    private final static QName _BPORDENESCMBALTAPARAMETERSsbremanente8_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", "Ssbremanente8");
    private final static QName _BPORDENESCMBALTAPARAMETERFciagente_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", "Fciagente");
    private final static QName _BPORDENESCMBALTAPARAMETERXmonto_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", "Xmonto");
    private final static QName _BPORDENESCMBALTAPARAMETERPrecUni8_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", "PrecUni8");
    private final static QName _BPORDENESCMBALTAPARAMETERPlazonormal_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", "Plazonormal");
    private final static QName _BPORDENESCMBALTAPARAMETERIntereses8_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", "Intereses8");
    private final static QName _BPORDENESCMBALTAPARAMETERNumMercap_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", "NumMercap");
    private final static QName _BPORDENESCMBALTAPARAMETERSesionIVR_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", "SesionIVR");
    private final static QName _BPORDENESCMBALTAPARAMETERFcitipocambio_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", "Fcitipocambio");
    private final static QName _BPORDENESCMBALTAPARAMETERPlazo_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", "Plazo");
    private final static QName _BPORDENESCMBALTAPARAMETEROperModi_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", "OperModi");
    private final static QName _BPORDENESCMBALTAPARAMETERFcifprevresc_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", "Fcifprevresc");
    private final static QName _BPORDENESCMBALTAPARAMETERModo_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", "Modo");
    private final static QName _BPORDENESCMBALTAPARAMETERTasaBp_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", "TasaBp");
    private final static QName _BPORDENESCMBALTAPARAMETERCapital_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", "Capital");
    private final static QName _BPORDENESCMBALTAPARAMETERPreciolimite8_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", "Preciolimite8");
    private final static QName _BPORDENESCMBALTAPARAMETERCno_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", "Cno");
    private final static QName _BPORDENESCMBALTAPARAMETERFcifacredita_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", "Fcifacredita");
    private final static QName _BPORDENESCMBALTAPARAMETERFechaRecep_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", "FechaRecep");
    private final static QName _BPORDENESCMBALTAPARAMETERCantTitu8_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", "CantTitu8");
    private final static QName _BPORDENESCMBALTAPARAMETERMoneLiq_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", "MoneLiq");
    private final static QName _BPORDENESCMBALTAPARAMETERFciplan_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", "Fciplan");
    private final static QName _BPORDENESCMBALTAPARAMETERDetalle_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", "Detalle");
    private final static QName _BPORDENESCMBALTAPARAMETERFechaOrden_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", "FechaOrden");
    private final static QName _BPORDENESCMBALTAPARAMETERNUP_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", "NUP");
    private final static QName _BPORDENESCMBALTAPARAMETERValorExcedido_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", "ValorExcedido");
    private final static QName _BPORDENESCMBALTAPARAMETERFcifondo_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", "Fcifondo");
    private final static QName _BPORDENESCMBALTAPARAMETEROperAlta_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", "OperAlta");
    private final static QName _BPORDENESCMBALTAPARAMETERTipoPlazo_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", "TipoPlazo");
    private final static QName _BPORDENESCMBALTAPARAMETERTasaNomAnual_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", "TasaNomAnual");
    private final static QName _BPORDENESCMBALTAPARAMETERNroMovCust_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", "NroMovCust");
    private final static QName _BPORDENESCMBALTAPARAMETERValorInterno8_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", "ValorInterno8");
    private final static QName _BPORDENESCMBALTAPARAMETERImpreso_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", "Impreso");
    private final static QName _BPORDENESCMBALTAPARAMETERFechaLiq_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", "FechaLiq");
    private final static QName _BPORDENESCMBALTAPARAMETERFechaEnvio_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", "FechaEnvio");
    private final static QName _BPORDENESCMBALTAPARAMETERRenAutomatica_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", "RenAutomatica");
    private final static QName _InsertarTransferenciaRIORIOOBParameterCuitDestino_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", "CuitDestino");
    private final static QName _InsertarTransferenciaRIORIOOBParameterSucursalOrig_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", "SucursalOrig");
    private final static QName _InsertarTransferenciaRIORIOOBParameterComision_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", "Comision");
    private final static QName _InsertarTransferenciaRIORIOOBParameterCbuDestino_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", "CbuDestino");
    private final static QName _BPCUENTASCNSCUADREPERFILPARAMETERCodSubSist_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", "CodSubSist");
    private final static QName _BPCUENTASCNSCUADREPERFILPARAMETERMonto_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", "Monto");
    private final static QName _BPCUENTASCNSCUADREPERFILPARAMETERPrecio_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", "Precio");
    private final static QName _BPCUENTASCNSCUADREPERFILPARAMETEROpcion_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", "Opcion");
    private final static QName _BPCUENTASCNSCUADREPERFILPARAMETERLimitado_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", "Limitado");
    private final static QName _InsertarOperacionCambioOBParameterNup_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", "Nup");
    private final static QName _InsertarOperacionCambioOBParameterCuentaDest_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", "CuentaDest");
    private final static QName _InsertarOperacionCambioOBParameterBoleto_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", "Boleto");
    private final static QName _InsertarOperacionCambioOBParameterNroCuitCuil_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", "NroCuitCuil");
    private final static QName _InsertarOperacionCambioOBParameterCuentaOri_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", "CuentaOri");
    private final static QName _InsertarOperacionCambioOBParameterFciTipoCambio_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", "FciTipoCambio");
    private final static QName _InsertarOperacionCambioOBParameterSucuDest_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", "SucuDest");
    private final static QName _InsertarOperacionCambioOBParameterSucuOri_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", "SucuOri");
    private final static QName _InsertarOperacionCambioOBParameterCuitCuil_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", "CuitCuil");
    private final static QName _InsertarTransferenciaRIORIOParameterOrigenOrden_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", "OrigenOrden");
    private final static QName _BPCUENTASCNSROSSIPARAMETERCuentaAltair_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", "CuentaAltair");
    private final static QName _BPCUENTASCNSROSSIPARAMETERSucursal_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", "Sucursal");
    private final static QName _BPCUENTASCNSROSSIPARAMETERSubproducto_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", "Subproducto");
    private final static QName _BPCUENTASCNSROSSIPARAMETERProducto_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", "Producto");
    private final static QName _BPCUENTASCNSATITPARAMETERCuentaBP_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", "CuentaBP");
    private final static QName _BPCUENTASCMBGRABAMOVPARAMETERMovObservacion_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", "MovObservacion");
    private final static QName _BPCUENTASCMBGRABAMOVPARAMETERTipoMovimiento_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", "TipoMovimiento");
    private final static QName _BPCUENTASCMBGRABAMOVPARAMETEROrigen_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", "Origen");
    private final static QName _BPCUENTASCMBGRABAMOVPARAMETERDivisaAltair_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", "DivisaAltair");
    private final static QName _BPCUENTASCMBGRABAMOVPARAMETERSucursalAltair_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", "SucursalAltair");
    private final static QName _BPCUENTASCMBGRABAMOVPARAMETERAceptaBatch_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", "AceptaBatch");
    private final static QName _BPCUENTASCMBGRABAMOVPARAMETERNuApunte_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", "NuApunte");
    private final static QName _BPCUENTASCMBGRABAMOVPARAMETERPasswordSesion_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", "PasswordSesion");
    private final static QName _BPCUENTASCMBGRABAMOVPARAMETERCodAltair_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", "CodAltair");
    private final static QName _BPCUENTASCMBGRABAMOVPARAMETERUsuarioSesion_QNAME = new QName("http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", "UsuarioSesion");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ar.com.santanderrio.obp.generated.webservices.bpriv.common.servicecontracts
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link BPCUENTASCNSMOVIMIENTOSPARAMETER }
     * 
     */
    public BPCUENTASCNSMOVIMIENTOSPARAMETER createBPCUENTASCNSMOVIMIENTOSPARAMETER() {
        return new BPCUENTASCNSMOVIMIENTOSPARAMETER();
    }

    /**
     * Create an instance of {@link BPORDENESCMBESTADOPARAMETER }
     * 
     */
    public BPORDENESCMBESTADOPARAMETER createBPORDENESCMBESTADOPARAMETER() {
        return new BPORDENESCMBESTADOPARAMETER();
    }

    /**
     * Create an instance of {@link BPCUENTASCNSSALDOSPARAMETER }
     * 
     */
    public BPCUENTASCNSSALDOSPARAMETER createBPCUENTASCNSSALDOSPARAMETER() {
        return new BPCUENTASCNSSALDOSPARAMETER();
    }

    /**
     * Create an instance of {@link InsertarTransferenciaRIORIOOBParameter }
     * 
     */
    public InsertarTransferenciaRIORIOOBParameter createInsertarTransferenciaRIORIOOBParameter() {
        return new InsertarTransferenciaRIORIOOBParameter();
    }

    /**
     * Create an instance of {@link InsertarTransferenciaEntreBancosParameter }
     * 
     */
    public InsertarTransferenciaEntreBancosParameter createInsertarTransferenciaEntreBancosParameter() {
        return new InsertarTransferenciaEntreBancosParameter();
    }

    /**
     * Create an instance of {@link InsertarOperacionCambioParameter }
     * 
     */
    public InsertarOperacionCambioParameter createInsertarOperacionCambioParameter() {
        return new InsertarOperacionCambioParameter();
    }

    /**
     * Create an instance of {@link BPCUENTASCMBGRABAMOVPARAMETER }
     * 
     */
    public BPCUENTASCMBGRABAMOVPARAMETER createBPCUENTASCMBGRABAMOVPARAMETER() {
        return new BPCUENTASCMBGRABAMOVPARAMETER();
    }

    /**
     * Create an instance of {@link InsertarTransferenciaEntreBancosOBParameter }
     * 
     */
    public InsertarTransferenciaEntreBancosOBParameter createInsertarTransferenciaEntreBancosOBParameter() {
        return new InsertarTransferenciaEntreBancosOBParameter();
    }

    /**
     * Create an instance of {@link BPCUENTASCNSROSSIPARAMETER }
     * 
     */
    public BPCUENTASCNSROSSIPARAMETER createBPCUENTASCNSROSSIPARAMETER() {
        return new BPCUENTASCNSROSSIPARAMETER();
    }

    /**
     * Create an instance of {@link BPCUENTASCNSCUADREPERFILPARAMETER }
     * 
     */
    public BPCUENTASCNSCUADREPERFILPARAMETER createBPCUENTASCNSCUADREPERFILPARAMETER() {
        return new BPCUENTASCNSCUADREPERFILPARAMETER();
    }

    /**
     * Create an instance of {@link BPORDENESCMBALTAPARAMETER }
     * 
     */
    public BPORDENESCMBALTAPARAMETER createBPORDENESCMBALTAPARAMETER() {
        return new BPORDENESCMBALTAPARAMETER();
    }

    /**
     * Create an instance of {@link InsertarOperacionCambioOBParameter }
     * 
     */
    public InsertarOperacionCambioOBParameter createInsertarOperacionCambioOBParameter() {
        return new InsertarOperacionCambioOBParameter();
    }

    /**
     * Create an instance of {@link BPCUENTASCNSATITPARAMETER }
     * 
     */
    public BPCUENTASCNSATITPARAMETER createBPCUENTASCNSATITPARAMETER() {
        return new BPCUENTASCNSATITPARAMETER();
    }

    /**
     * Create an instance of {@link InsertarTransferenciaRIORIOParameter }
     * 
     */
    public InsertarTransferenciaRIORIOParameter createInsertarTransferenciaRIORIOParameter() {
        return new InsertarTransferenciaRIORIOParameter();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BPCUENTASCNSMOVIMIENTOSPARAMETER }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "BP_CUENTAS_CNS_MOVIMIENTOS_PARAMETER")
    public JAXBElement<BPCUENTASCNSMOVIMIENTOSPARAMETER> createBPCUENTASCNSMOVIMIENTOSPARAMETER(BPCUENTASCNSMOVIMIENTOSPARAMETER value) {
        return new JAXBElement<BPCUENTASCNSMOVIMIENTOSPARAMETER>(_BPCUENTASCNSMOVIMIENTOSPARAMETER_QNAME, BPCUENTASCNSMOVIMIENTOSPARAMETER.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BPORDENESCMBESTADOPARAMETER }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "BP_ORDENES_CMB_ESTADO_PARAMETER")
    public JAXBElement<BPORDENESCMBESTADOPARAMETER> createBPORDENESCMBESTADOPARAMETER(BPORDENESCMBESTADOPARAMETER value) {
        return new JAXBElement<BPORDENESCMBESTADOPARAMETER>(_BPORDENESCMBESTADOPARAMETER_QNAME, BPORDENESCMBESTADOPARAMETER.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InsertarOperacionCambioOBParameter }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "InsertarOperacionCambioOBParameter")
    public JAXBElement<InsertarOperacionCambioOBParameter> createInsertarOperacionCambioOBParameter(InsertarOperacionCambioOBParameter value) {
        return new JAXBElement<InsertarOperacionCambioOBParameter>(_InsertarOperacionCambioOBParameter_QNAME, InsertarOperacionCambioOBParameter.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BPCUENTASCNSATITPARAMETER }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "BP_CUENTAS_CNS_ATIT_PARAMETER")
    public JAXBElement<BPCUENTASCNSATITPARAMETER> createBPCUENTASCNSATITPARAMETER(BPCUENTASCNSATITPARAMETER value) {
        return new JAXBElement<BPCUENTASCNSATITPARAMETER>(_BPCUENTASCNSATITPARAMETER_QNAME, BPCUENTASCNSATITPARAMETER.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BPORDENESCMBALTAPARAMETER }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "BP_ORDENES_CMB_ALTA_PARAMETER")
    public JAXBElement<BPORDENESCMBALTAPARAMETER> createBPORDENESCMBALTAPARAMETER(BPORDENESCMBALTAPARAMETER value) {
        return new JAXBElement<BPORDENESCMBALTAPARAMETER>(_BPORDENESCMBALTAPARAMETER_QNAME, BPORDENESCMBALTAPARAMETER.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InsertarOperacionCambioParameter }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "InsertarOperacionCambioParameter")
    public JAXBElement<InsertarOperacionCambioParameter> createInsertarOperacionCambioParameter(InsertarOperacionCambioParameter value) {
        return new JAXBElement<InsertarOperacionCambioParameter>(_InsertarOperacionCambioParameter_QNAME, InsertarOperacionCambioParameter.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InsertarTransferenciaEntreBancosOBParameter }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "InsertarTransferenciaEntreBancosOBParameter")
    public JAXBElement<InsertarTransferenciaEntreBancosOBParameter> createInsertarTransferenciaEntreBancosOBParameter(InsertarTransferenciaEntreBancosOBParameter value) {
        return new JAXBElement<InsertarTransferenciaEntreBancosOBParameter>(_InsertarTransferenciaEntreBancosOBParameter_QNAME, InsertarTransferenciaEntreBancosOBParameter.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InsertarTransferenciaEntreBancosParameter }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "InsertarTransferenciaEntreBancosParameter")
    public JAXBElement<InsertarTransferenciaEntreBancosParameter> createInsertarTransferenciaEntreBancosParameter(InsertarTransferenciaEntreBancosParameter value) {
        return new JAXBElement<InsertarTransferenciaEntreBancosParameter>(_InsertarTransferenciaEntreBancosParameter_QNAME, InsertarTransferenciaEntreBancosParameter.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BPCUENTASCNSSALDOSPARAMETER }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "BP_CUENTAS_CNS_SALDOS_PARAMETER")
    public JAXBElement<BPCUENTASCNSSALDOSPARAMETER> createBPCUENTASCNSSALDOSPARAMETER(BPCUENTASCNSSALDOSPARAMETER value) {
        return new JAXBElement<BPCUENTASCNSSALDOSPARAMETER>(_BPCUENTASCNSSALDOSPARAMETER_QNAME, BPCUENTASCNSSALDOSPARAMETER.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InsertarTransferenciaRIORIOParameter }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "InsertarTransferenciaRIORIOParameter")
    public JAXBElement<InsertarTransferenciaRIORIOParameter> createInsertarTransferenciaRIORIOParameter(InsertarTransferenciaRIORIOParameter value) {
        return new JAXBElement<InsertarTransferenciaRIORIOParameter>(_InsertarTransferenciaRIORIOParameter_QNAME, InsertarTransferenciaRIORIOParameter.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InsertarTransferenciaRIORIOOBParameter }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "InsertarTransferenciaRIORIOOBParameter")
    public JAXBElement<InsertarTransferenciaRIORIOOBParameter> createInsertarTransferenciaRIORIOOBParameter(InsertarTransferenciaRIORIOOBParameter value) {
        return new JAXBElement<InsertarTransferenciaRIORIOOBParameter>(_InsertarTransferenciaRIORIOOBParameter_QNAME, InsertarTransferenciaRIORIOOBParameter.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BPCUENTASCNSROSSIPARAMETER }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "BP_CUENTAS_CNS_ROSSI_PARAMETER")
    public JAXBElement<BPCUENTASCNSROSSIPARAMETER> createBPCUENTASCNSROSSIPARAMETER(BPCUENTASCNSROSSIPARAMETER value) {
        return new JAXBElement<BPCUENTASCNSROSSIPARAMETER>(_BPCUENTASCNSROSSIPARAMETER_QNAME, BPCUENTASCNSROSSIPARAMETER.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BPCUENTASCNSCUADREPERFILPARAMETER }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "BP_CUENTAS_CNS_CUADREPERFIL_PARAMETER")
    public JAXBElement<BPCUENTASCNSCUADREPERFILPARAMETER> createBPCUENTASCNSCUADREPERFILPARAMETER(BPCUENTASCNSCUADREPERFILPARAMETER value) {
        return new JAXBElement<BPCUENTASCNSCUADREPERFILPARAMETER>(_BPCUENTASCNSCUADREPERFILPARAMETER_QNAME, BPCUENTASCNSCUADREPERFILPARAMETER.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BPCUENTASCMBGRABAMOVPARAMETER }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "BP_CUENTAS_CMB_GRABAMOV_PARAMETER")
    public JAXBElement<BPCUENTASCMBGRABAMOVPARAMETER> createBPCUENTASCMBGRABAMOVPARAMETER(BPCUENTASCMBGRABAMOVPARAMETER value) {
        return new JAXBElement<BPCUENTASCMBGRABAMOVPARAMETER>(_BPCUENTASCMBGRABAMOVPARAMETER_QNAME, BPCUENTASCMBGRABAMOVPARAMETER.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "TipoOrd", scope = BPORDENESCMBESTADOPARAMETER.class)
    public JAXBElement<String> createBPORDENESCMBESTADOPARAMETERTipoOrd(String value) {
        return new JAXBElement<String>(_BPORDENESCMBESTADOPARAMETERTipoOrd_QNAME, String.class, BPORDENESCMBESTADOPARAMETER.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "CodSist", scope = BPORDENESCMBESTADOPARAMETER.class)
    public JAXBElement<String> createBPORDENESCMBESTADOPARAMETERCodSist(String value) {
        return new JAXBElement<String>(_BPORDENESCMBESTADOPARAMETERCodSist_QNAME, String.class, BPORDENESCMBESTADOPARAMETER.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "NumOrden", scope = BPORDENESCMBESTADOPARAMETER.class)
    public JAXBElement<String> createBPORDENESCMBESTADOPARAMETERNumOrden(String value) {
        return new JAXBElement<String>(_BPORDENESCMBESTADOPARAMETERNumOrden_QNAME, String.class, BPORDENESCMBESTADOPARAMETER.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "CostOrd", scope = BPORDENESCMBESTADOPARAMETER.class)
    public JAXBElement<String> createBPORDENESCMBESTADOPARAMETERCostOrd(String value) {
        return new JAXBElement<String>(_BPORDENESCMBESTADOPARAMETERCostOrd_QNAME, String.class, BPORDENESCMBESTADOPARAMETER.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "Estado", scope = BPORDENESCMBESTADOPARAMETER.class)
    public JAXBElement<String> createBPORDENESCMBESTADOPARAMETEREstado(String value) {
        return new JAXBElement<String>(_BPORDENESCMBESTADOPARAMETEREstado_QNAME, String.class, BPORDENESCMBESTADOPARAMETER.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "Br", scope = BPORDENESCMBESTADOPARAMETER.class)
    public JAXBElement<String> createBPORDENESCMBESTADOPARAMETERBr(String value) {
        return new JAXBElement<String>(_BPORDENESCMBESTADOPARAMETERBr_QNAME, String.class, BPORDENESCMBESTADOPARAMETER.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "TipoOrd", scope = InsertarTransferenciaEntreBancosOBParameter.class)
    public JAXBElement<String> createInsertarTransferenciaEntreBancosOBParameterTipoOrd(String value) {
        return new JAXBElement<String>(_BPORDENESCMBESTADOPARAMETERTipoOrd_QNAME, String.class, InsertarTransferenciaEntreBancosOBParameter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "Oper", scope = InsertarTransferenciaEntreBancosOBParameter.class)
    public JAXBElement<String> createInsertarTransferenciaEntreBancosOBParameterOper(String value) {
        return new JAXBElement<String>(_InsertarTransferenciaEntreBancosOBParameterOper_QNAME, String.class, InsertarTransferenciaEntreBancosOBParameter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "CuentaAltairDest", scope = InsertarTransferenciaEntreBancosOBParameter.class)
    public JAXBElement<String> createInsertarTransferenciaEntreBancosOBParameterCuentaAltairDest(String value) {
        return new JAXBElement<String>(_InsertarTransferenciaEntreBancosOBParameterCuentaAltairDest_QNAME, String.class, InsertarTransferenciaEntreBancosOBParameter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "EntidadDest", scope = InsertarTransferenciaEntreBancosOBParameter.class)
    public JAXBElement<String> createInsertarTransferenciaEntreBancosOBParameterEntidadDest(String value) {
        return new JAXBElement<String>(_InsertarTransferenciaEntreBancosOBParameterEntidadDest_QNAME, String.class, InsertarTransferenciaEntreBancosOBParameter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "ModoTransf", scope = InsertarTransferenciaEntreBancosOBParameter.class)
    public JAXBElement<String> createInsertarTransferenciaEntreBancosOBParameterModoTransf(String value) {
        return new JAXBElement<String>(_InsertarTransferenciaEntreBancosOBParameterModoTransf_QNAME, String.class, InsertarTransferenciaEntreBancosOBParameter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "Observaciones", scope = InsertarTransferenciaEntreBancosOBParameter.class)
    public JAXBElement<String> createInsertarTransferenciaEntreBancosOBParameterObservaciones(String value) {
        return new JAXBElement<String>(_InsertarTransferenciaEntreBancosOBParameterObservaciones_QNAME, String.class, InsertarTransferenciaEntreBancosOBParameter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "CuentaAltairOrig", scope = InsertarTransferenciaEntreBancosOBParameter.class)
    public JAXBElement<String> createInsertarTransferenciaEntreBancosOBParameterCuentaAltairOrig(String value) {
        return new JAXBElement<String>(_InsertarTransferenciaEntreBancosOBParameterCuentaAltairOrig_QNAME, String.class, InsertarTransferenciaEntreBancosOBParameter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "Concepto", scope = InsertarTransferenciaEntreBancosOBParameter.class)
    public JAXBElement<String> createInsertarTransferenciaEntreBancosOBParameterConcepto(String value) {
        return new JAXBElement<String>(_InsertarTransferenciaEntreBancosOBParameterConcepto_QNAME, String.class, InsertarTransferenciaEntreBancosOBParameter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "TipoDocDest", scope = InsertarTransferenciaEntreBancosOBParameter.class)
    public JAXBElement<String> createInsertarTransferenciaEntreBancosOBParameterTipoDocDest(String value) {
        return new JAXBElement<String>(_InsertarTransferenciaEntreBancosOBParameterTipoDocDest_QNAME, String.class, InsertarTransferenciaEntreBancosOBParameter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "Importe", scope = InsertarTransferenciaEntreBancosOBParameter.class)
    public JAXBElement<BigDecimal> createInsertarTransferenciaEntreBancosOBParameterImporte(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_InsertarTransferenciaEntreBancosOBParameterImporte_QNAME, BigDecimal.class, InsertarTransferenciaEntreBancosOBParameter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "SucursalDest", scope = InsertarTransferenciaEntreBancosOBParameter.class)
    public JAXBElement<String> createInsertarTransferenciaEntreBancosOBParameterSucursalDest(String value) {
        return new JAXBElement<String>(_InsertarTransferenciaEntreBancosOBParameterSucursalDest_QNAME, String.class, InsertarTransferenciaEntreBancosOBParameter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "TipoCuentaDest", scope = InsertarTransferenciaEntreBancosOBParameter.class)
    public JAXBElement<String> createInsertarTransferenciaEntreBancosOBParameterTipoCuentaDest(String value) {
        return new JAXBElement<String>(_InsertarTransferenciaEntreBancosOBParameterTipoCuentaDest_QNAME, String.class, InsertarTransferenciaEntreBancosOBParameter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "CbuDest", scope = InsertarTransferenciaEntreBancosOBParameter.class)
    public JAXBElement<String> createInsertarTransferenciaEntreBancosOBParameterCbuDest(String value) {
        return new JAXBElement<String>(_InsertarTransferenciaEntreBancosOBParameterCbuDest_QNAME, String.class, InsertarTransferenciaEntreBancosOBParameter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "Beneficiario", scope = InsertarTransferenciaEntreBancosOBParameter.class)
    public JAXBElement<String> createInsertarTransferenciaEntreBancosOBParameterBeneficiario(String value) {
        return new JAXBElement<String>(_InsertarTransferenciaEntreBancosOBParameterBeneficiario_QNAME, String.class, InsertarTransferenciaEntreBancosOBParameter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "DocumentoOrig", scope = InsertarTransferenciaEntreBancosOBParameter.class)
    public JAXBElement<String> createInsertarTransferenciaEntreBancosOBParameterDocumentoOrig(String value) {
        return new JAXBElement<String>(_InsertarTransferenciaEntreBancosOBParameterDocumentoOrig_QNAME, String.class, InsertarTransferenciaEntreBancosOBParameter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "DocumentoDest", scope = InsertarTransferenciaEntreBancosOBParameter.class)
    public JAXBElement<String> createInsertarTransferenciaEntreBancosOBParameterDocumentoDest(String value) {
        return new JAXBElement<String>(_InsertarTransferenciaEntreBancosOBParameterDocumentoDest_QNAME, String.class, InsertarTransferenciaEntreBancosOBParameter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "Moneda", scope = InsertarTransferenciaEntreBancosOBParameter.class)
    public JAXBElement<String> createInsertarTransferenciaEntreBancosOBParameterMoneda(String value) {
        return new JAXBElement<String>(_InsertarTransferenciaEntreBancosOBParameterMoneda_QNAME, String.class, InsertarTransferenciaEntreBancosOBParameter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "Cuenta", scope = BPCUENTASCNSMOVIMIENTOSPARAMETER.class)
    public JAXBElement<String> createBPCUENTASCNSMOVIMIENTOSPARAMETERCuenta(String value) {
        return new JAXBElement<String>(_BPCUENTASCNSMOVIMIENTOSPARAMETERCuenta_QNAME, String.class, BPCUENTASCNSMOVIMIENTOSPARAMETER.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "Canal", scope = BPCUENTASCNSMOVIMIENTOSPARAMETER.class)
    public JAXBElement<String> createBPCUENTASCNSMOVIMIENTOSPARAMETERCanal(String value) {
        return new JAXBElement<String>(_BPCUENTASCNSMOVIMIENTOSPARAMETERCanal_QNAME, String.class, BPCUENTASCNSMOVIMIENTOSPARAMETER.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "Divisa", scope = BPCUENTASCNSMOVIMIENTOSPARAMETER.class)
    public JAXBElement<String> createBPCUENTASCNSMOVIMIENTOSPARAMETERDivisa(String value) {
        return new JAXBElement<String>(_BPCUENTASCNSMOVIMIENTOSPARAMETERDivisa_QNAME, String.class, BPCUENTASCNSMOVIMIENTOSPARAMETER.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "NumMae", scope = BPORDENESCMBALTAPARAMETER.class)
    public JAXBElement<String> createBPORDENESCMBALTAPARAMETERNumMae(String value) {
        return new JAXBElement<String>(_BPORDENESCMBALTAPARAMETERNumMae_QNAME, String.class, BPORDENESCMBALTAPARAMETER.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "NumOrden", scope = BPORDENESCMBALTAPARAMETER.class)
    public JAXBElement<String> createBPORDENESCMBALTAPARAMETERNumOrden(String value) {
        return new JAXBElement<String>(_BPORDENESCMBESTADOPARAMETERNumOrden_QNAME, String.class, BPORDENESCMBALTAPARAMETER.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "Estado", scope = BPORDENESCMBALTAPARAMETER.class)
    public JAXBElement<String> createBPORDENESCMBALTAPARAMETEREstado(String value) {
        return new JAXBElement<String>(_BPORDENESCMBESTADOPARAMETEREstado_QNAME, String.class, BPORDENESCMBALTAPARAMETER.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "Sector", scope = BPORDENESCMBALTAPARAMETER.class)
    public JAXBElement<String> createBPORDENESCMBALTAPARAMETERSector(String value) {
        return new JAXBElement<String>(_BPORDENESCMBALTAPARAMETERSector_QNAME, String.class, BPORDENESCMBALTAPARAMETER.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "HoraEmision", scope = BPORDENESCMBALTAPARAMETER.class)
    public JAXBElement<String> createBPORDENESCMBALTAPARAMETERHoraEmision(String value) {
        return new JAXBElement<String>(_BPORDENESCMBALTAPARAMETERHoraEmision_QNAME, String.class, BPORDENESCMBALTAPARAMETER.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "Fcitipocomision", scope = BPORDENESCMBALTAPARAMETER.class)
    public JAXBElement<BigDecimal> createBPORDENESCMBALTAPARAMETERFcitipocomision(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_BPORDENESCMBALTAPARAMETERFcitipocomision_QNAME, BigDecimal.class, BPORDENESCMBALTAPARAMETER.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "Preciomercado", scope = BPORDENESCMBALTAPARAMETER.class)
    public JAXBElement<String> createBPORDENESCMBALTAPARAMETERPreciomercado(String value) {
        return new JAXBElement<String>(_BPORDENESCMBALTAPARAMETERPreciomercado_QNAME, String.class, BPORDENESCMBALTAPARAMETER.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "Especie", scope = BPORDENESCMBALTAPARAMETER.class)
    public JAXBElement<String> createBPORDENESCMBALTAPARAMETEREspecie(String value) {
        return new JAXBElement<String>(_BPORDENESCMBALTAPARAMETEREspecie_QNAME, String.class, BPORDENESCMBALTAPARAMETER.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "CheckMiami", scope = BPORDENESCMBALTAPARAMETER.class)
    public JAXBElement<String> createBPORDENESCMBALTAPARAMETERCheckMiami(String value) {
        return new JAXBElement<String>(_BPORDENESCMBALTAPARAMETERCheckMiami_QNAME, String.class, BPORDENESCMBALTAPARAMETER.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "Ssbremanente8", scope = BPORDENESCMBALTAPARAMETER.class)
    public JAXBElement<BigDecimal> createBPORDENESCMBALTAPARAMETERSsbremanente8(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_BPORDENESCMBALTAPARAMETERSsbremanente8_QNAME, BigDecimal.class, BPORDENESCMBALTAPARAMETER.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "Fciagente", scope = BPORDENESCMBALTAPARAMETER.class)
    public JAXBElement<BigDecimal> createBPORDENESCMBALTAPARAMETERFciagente(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_BPORDENESCMBALTAPARAMETERFciagente_QNAME, BigDecimal.class, BPORDENESCMBALTAPARAMETER.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "Xmonto", scope = BPORDENESCMBALTAPARAMETER.class)
    public JAXBElement<String> createBPORDENESCMBALTAPARAMETERXmonto(String value) {
        return new JAXBElement<String>(_BPORDENESCMBALTAPARAMETERXmonto_QNAME, String.class, BPORDENESCMBALTAPARAMETER.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "CodSist", scope = BPORDENESCMBALTAPARAMETER.class)
    public JAXBElement<String> createBPORDENESCMBALTAPARAMETERCodSist(String value) {
        return new JAXBElement<String>(_BPORDENESCMBESTADOPARAMETERCodSist_QNAME, String.class, BPORDENESCMBALTAPARAMETER.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "PrecUni8", scope = BPORDENESCMBALTAPARAMETER.class)
    public JAXBElement<BigDecimal> createBPORDENESCMBALTAPARAMETERPrecUni8(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_BPORDENESCMBALTAPARAMETERPrecUni8_QNAME, BigDecimal.class, BPORDENESCMBALTAPARAMETER.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "Observaciones", scope = BPORDENESCMBALTAPARAMETER.class)
    public JAXBElement<String> createBPORDENESCMBALTAPARAMETERObservaciones(String value) {
        return new JAXBElement<String>(_InsertarTransferenciaEntreBancosOBParameterObservaciones_QNAME, String.class, BPORDENESCMBALTAPARAMETER.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "Plazonormal", scope = BPORDENESCMBALTAPARAMETER.class)
    public JAXBElement<String> createBPORDENESCMBALTAPARAMETERPlazonormal(String value) {
        return new JAXBElement<String>(_BPORDENESCMBALTAPARAMETERPlazonormal_QNAME, String.class, BPORDENESCMBALTAPARAMETER.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "Intereses8", scope = BPORDENESCMBALTAPARAMETER.class)
    public JAXBElement<BigDecimal> createBPORDENESCMBALTAPARAMETERIntereses8(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_BPORDENESCMBALTAPARAMETERIntereses8_QNAME, BigDecimal.class, BPORDENESCMBALTAPARAMETER.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "NumMercap", scope = BPORDENESCMBALTAPARAMETER.class)
    public JAXBElement<String> createBPORDENESCMBALTAPARAMETERNumMercap(String value) {
        return new JAXBElement<String>(_BPORDENESCMBALTAPARAMETERNumMercap_QNAME, String.class, BPORDENESCMBALTAPARAMETER.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "SesionIVR", scope = BPORDENESCMBALTAPARAMETER.class)
    public JAXBElement<BigDecimal> createBPORDENESCMBALTAPARAMETERSesionIVR(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_BPORDENESCMBALTAPARAMETERSesionIVR_QNAME, BigDecimal.class, BPORDENESCMBALTAPARAMETER.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "Fcitipocambio", scope = BPORDENESCMBALTAPARAMETER.class)
    public JAXBElement<BigDecimal> createBPORDENESCMBALTAPARAMETERFcitipocambio(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_BPORDENESCMBALTAPARAMETERFcitipocambio_QNAME, BigDecimal.class, BPORDENESCMBALTAPARAMETER.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "Plazo", scope = BPORDENESCMBALTAPARAMETER.class)
    public JAXBElement<String> createBPORDENESCMBALTAPARAMETERPlazo(String value) {
        return new JAXBElement<String>(_BPORDENESCMBALTAPARAMETERPlazo_QNAME, String.class, BPORDENESCMBALTAPARAMETER.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "OperModi", scope = BPORDENESCMBALTAPARAMETER.class)
    public JAXBElement<String> createBPORDENESCMBALTAPARAMETEROperModi(String value) {
        return new JAXBElement<String>(_BPORDENESCMBALTAPARAMETEROperModi_QNAME, String.class, BPORDENESCMBALTAPARAMETER.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "CostOrd", scope = BPORDENESCMBALTAPARAMETER.class)
    public JAXBElement<String> createBPORDENESCMBALTAPARAMETERCostOrd(String value) {
        return new JAXBElement<String>(_BPORDENESCMBESTADOPARAMETERCostOrd_QNAME, String.class, BPORDENESCMBALTAPARAMETER.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "Fcifprevresc", scope = BPORDENESCMBALTAPARAMETER.class)
    public JAXBElement<XMLGregorianCalendar> createBPORDENESCMBALTAPARAMETERFcifprevresc(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_BPORDENESCMBALTAPARAMETERFcifprevresc_QNAME, XMLGregorianCalendar.class, BPORDENESCMBALTAPARAMETER.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "Modo", scope = BPORDENESCMBALTAPARAMETER.class)
    public JAXBElement<String> createBPORDENESCMBALTAPARAMETERModo(String value) {
        return new JAXBElement<String>(_BPORDENESCMBALTAPARAMETERModo_QNAME, String.class, BPORDENESCMBALTAPARAMETER.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "TasaBp", scope = BPORDENESCMBALTAPARAMETER.class)
    public JAXBElement<BigDecimal> createBPORDENESCMBALTAPARAMETERTasaBp(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_BPORDENESCMBALTAPARAMETERTasaBp_QNAME, BigDecimal.class, BPORDENESCMBALTAPARAMETER.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "Capital", scope = BPORDENESCMBALTAPARAMETER.class)
    public JAXBElement<BigDecimal> createBPORDENESCMBALTAPARAMETERCapital(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_BPORDENESCMBALTAPARAMETERCapital_QNAME, BigDecimal.class, BPORDENESCMBALTAPARAMETER.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "Preciolimite8", scope = BPORDENESCMBALTAPARAMETER.class)
    public JAXBElement<BigDecimal> createBPORDENESCMBALTAPARAMETERPreciolimite8(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_BPORDENESCMBALTAPARAMETERPreciolimite8_QNAME, BigDecimal.class, BPORDENESCMBALTAPARAMETER.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "Cno", scope = BPORDENESCMBALTAPARAMETER.class)
    public JAXBElement<String> createBPORDENESCMBALTAPARAMETERCno(String value) {
        return new JAXBElement<String>(_BPORDENESCMBALTAPARAMETERCno_QNAME, String.class, BPORDENESCMBALTAPARAMETER.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "Fcifacredita", scope = BPORDENESCMBALTAPARAMETER.class)
    public JAXBElement<XMLGregorianCalendar> createBPORDENESCMBALTAPARAMETERFcifacredita(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_BPORDENESCMBALTAPARAMETERFcifacredita_QNAME, XMLGregorianCalendar.class, BPORDENESCMBALTAPARAMETER.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "FechaRecep", scope = BPORDENESCMBALTAPARAMETER.class)
    public JAXBElement<XMLGregorianCalendar> createBPORDENESCMBALTAPARAMETERFechaRecep(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_BPORDENESCMBALTAPARAMETERFechaRecep_QNAME, XMLGregorianCalendar.class, BPORDENESCMBALTAPARAMETER.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "CantTitu8", scope = BPORDENESCMBALTAPARAMETER.class)
    public JAXBElement<BigDecimal> createBPORDENESCMBALTAPARAMETERCantTitu8(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_BPORDENESCMBALTAPARAMETERCantTitu8_QNAME, BigDecimal.class, BPORDENESCMBALTAPARAMETER.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "MoneLiq", scope = BPORDENESCMBALTAPARAMETER.class)
    public JAXBElement<String> createBPORDENESCMBALTAPARAMETERMoneLiq(String value) {
        return new JAXBElement<String>(_BPORDENESCMBALTAPARAMETERMoneLiq_QNAME, String.class, BPORDENESCMBALTAPARAMETER.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "Fciplan", scope = BPORDENESCMBALTAPARAMETER.class)
    public JAXBElement<BigDecimal> createBPORDENESCMBALTAPARAMETERFciplan(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_BPORDENESCMBALTAPARAMETERFciplan_QNAME, BigDecimal.class, BPORDENESCMBALTAPARAMETER.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "Detalle", scope = BPORDENESCMBALTAPARAMETER.class)
    public JAXBElement<String> createBPORDENESCMBALTAPARAMETERDetalle(String value) {
        return new JAXBElement<String>(_BPORDENESCMBALTAPARAMETERDetalle_QNAME, String.class, BPORDENESCMBALTAPARAMETER.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "FechaOrden", scope = BPORDENESCMBALTAPARAMETER.class)
    public JAXBElement<XMLGregorianCalendar> createBPORDENESCMBALTAPARAMETERFechaOrden(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_BPORDENESCMBALTAPARAMETERFechaOrden_QNAME, XMLGregorianCalendar.class, BPORDENESCMBALTAPARAMETER.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "TipoOrd", scope = BPORDENESCMBALTAPARAMETER.class)
    public JAXBElement<String> createBPORDENESCMBALTAPARAMETERTipoOrd(String value) {
        return new JAXBElement<String>(_BPORDENESCMBESTADOPARAMETERTipoOrd_QNAME, String.class, BPORDENESCMBALTAPARAMETER.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "NUP", scope = BPORDENESCMBALTAPARAMETER.class)
    public JAXBElement<String> createBPORDENESCMBALTAPARAMETERNUP(String value) {
        return new JAXBElement<String>(_BPORDENESCMBALTAPARAMETERNUP_QNAME, String.class, BPORDENESCMBALTAPARAMETER.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Short }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "ValorExcedido", scope = BPORDENESCMBALTAPARAMETER.class)
    public JAXBElement<Short> createBPORDENESCMBALTAPARAMETERValorExcedido(Short value) {
        return new JAXBElement<Short>(_BPORDENESCMBALTAPARAMETERValorExcedido_QNAME, Short.class, BPORDENESCMBALTAPARAMETER.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "Fcifondo", scope = BPORDENESCMBALTAPARAMETER.class)
    public JAXBElement<BigDecimal> createBPORDENESCMBALTAPARAMETERFcifondo(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_BPORDENESCMBALTAPARAMETERFcifondo_QNAME, BigDecimal.class, BPORDENESCMBALTAPARAMETER.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "OperAlta", scope = BPORDENESCMBALTAPARAMETER.class)
    public JAXBElement<String> createBPORDENESCMBALTAPARAMETEROperAlta(String value) {
        return new JAXBElement<String>(_BPORDENESCMBALTAPARAMETEROperAlta_QNAME, String.class, BPORDENESCMBALTAPARAMETER.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "TipoPlazo", scope = BPORDENESCMBALTAPARAMETER.class)
    public JAXBElement<String> createBPORDENESCMBALTAPARAMETERTipoPlazo(String value) {
        return new JAXBElement<String>(_BPORDENESCMBALTAPARAMETERTipoPlazo_QNAME, String.class, BPORDENESCMBALTAPARAMETER.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "TasaNomAnual", scope = BPORDENESCMBALTAPARAMETER.class)
    public JAXBElement<BigDecimal> createBPORDENESCMBALTAPARAMETERTasaNomAnual(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_BPORDENESCMBALTAPARAMETERTasaNomAnual_QNAME, BigDecimal.class, BPORDENESCMBALTAPARAMETER.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "NroMovCust", scope = BPORDENESCMBALTAPARAMETER.class)
    public JAXBElement<BigDecimal> createBPORDENESCMBALTAPARAMETERNroMovCust(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_BPORDENESCMBALTAPARAMETERNroMovCust_QNAME, BigDecimal.class, BPORDENESCMBALTAPARAMETER.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "ValorInterno8", scope = BPORDENESCMBALTAPARAMETER.class)
    public JAXBElement<BigDecimal> createBPORDENESCMBALTAPARAMETERValorInterno8(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_BPORDENESCMBALTAPARAMETERValorInterno8_QNAME, BigDecimal.class, BPORDENESCMBALTAPARAMETER.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "Impreso", scope = BPORDENESCMBALTAPARAMETER.class)
    public JAXBElement<String> createBPORDENESCMBALTAPARAMETERImpreso(String value) {
        return new JAXBElement<String>(_BPORDENESCMBALTAPARAMETERImpreso_QNAME, String.class, BPORDENESCMBALTAPARAMETER.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "FechaLiq", scope = BPORDENESCMBALTAPARAMETER.class)
    public JAXBElement<XMLGregorianCalendar> createBPORDENESCMBALTAPARAMETERFechaLiq(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_BPORDENESCMBALTAPARAMETERFechaLiq_QNAME, XMLGregorianCalendar.class, BPORDENESCMBALTAPARAMETER.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "FechaEnvio", scope = BPORDENESCMBALTAPARAMETER.class)
    public JAXBElement<XMLGregorianCalendar> createBPORDENESCMBALTAPARAMETERFechaEnvio(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_BPORDENESCMBALTAPARAMETERFechaEnvio_QNAME, XMLGregorianCalendar.class, BPORDENESCMBALTAPARAMETER.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "RenAutomatica", scope = BPORDENESCMBALTAPARAMETER.class)
    public JAXBElement<String> createBPORDENESCMBALTAPARAMETERRenAutomatica(String value) {
        return new JAXBElement<String>(_BPORDENESCMBALTAPARAMETERRenAutomatica_QNAME, String.class, BPORDENESCMBALTAPARAMETER.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "Br", scope = BPORDENESCMBALTAPARAMETER.class)
    public JAXBElement<String> createBPORDENESCMBALTAPARAMETERBr(String value) {
        return new JAXBElement<String>(_BPORDENESCMBESTADOPARAMETERBr_QNAME, String.class, BPORDENESCMBALTAPARAMETER.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "TipoOrd", scope = InsertarTransferenciaRIORIOOBParameter.class)
    public JAXBElement<String> createInsertarTransferenciaRIORIOOBParameterTipoOrd(String value) {
        return new JAXBElement<String>(_BPORDENESCMBESTADOPARAMETERTipoOrd_QNAME, String.class, InsertarTransferenciaRIORIOOBParameter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "Oper", scope = InsertarTransferenciaRIORIOOBParameter.class)
    public JAXBElement<String> createInsertarTransferenciaRIORIOOBParameterOper(String value) {
        return new JAXBElement<String>(_InsertarTransferenciaEntreBancosOBParameterOper_QNAME, String.class, InsertarTransferenciaRIORIOOBParameter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "CuentaAltairDest", scope = InsertarTransferenciaRIORIOOBParameter.class)
    public JAXBElement<String> createInsertarTransferenciaRIORIOOBParameterCuentaAltairDest(String value) {
        return new JAXBElement<String>(_InsertarTransferenciaEntreBancosOBParameterCuentaAltairDest_QNAME, String.class, InsertarTransferenciaRIORIOOBParameter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "CuitDestino", scope = InsertarTransferenciaRIORIOOBParameter.class)
    public JAXBElement<String> createInsertarTransferenciaRIORIOOBParameterCuitDestino(String value) {
        return new JAXBElement<String>(_InsertarTransferenciaRIORIOOBParameterCuitDestino_QNAME, String.class, InsertarTransferenciaRIORIOOBParameter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "Observaciones", scope = InsertarTransferenciaRIORIOOBParameter.class)
    public JAXBElement<String> createInsertarTransferenciaRIORIOOBParameterObservaciones(String value) {
        return new JAXBElement<String>(_InsertarTransferenciaEntreBancosOBParameterObservaciones_QNAME, String.class, InsertarTransferenciaRIORIOOBParameter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "CuentaAltairOrig", scope = InsertarTransferenciaRIORIOOBParameter.class)
    public JAXBElement<String> createInsertarTransferenciaRIORIOOBParameterCuentaAltairOrig(String value) {
        return new JAXBElement<String>(_InsertarTransferenciaEntreBancosOBParameterCuentaAltairOrig_QNAME, String.class, InsertarTransferenciaRIORIOOBParameter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "Concepto", scope = InsertarTransferenciaRIORIOOBParameter.class)
    public JAXBElement<String> createInsertarTransferenciaRIORIOOBParameterConcepto(String value) {
        return new JAXBElement<String>(_InsertarTransferenciaEntreBancosOBParameterConcepto_QNAME, String.class, InsertarTransferenciaRIORIOOBParameter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "SucursalOrig", scope = InsertarTransferenciaRIORIOOBParameter.class)
    public JAXBElement<String> createInsertarTransferenciaRIORIOOBParameterSucursalOrig(String value) {
        return new JAXBElement<String>(_InsertarTransferenciaRIORIOOBParameterSucursalOrig_QNAME, String.class, InsertarTransferenciaRIORIOOBParameter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "Importe", scope = InsertarTransferenciaRIORIOOBParameter.class)
    public JAXBElement<BigDecimal> createInsertarTransferenciaRIORIOOBParameterImporte(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_InsertarTransferenciaEntreBancosOBParameterImporte_QNAME, BigDecimal.class, InsertarTransferenciaRIORIOOBParameter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "SucursalDest", scope = InsertarTransferenciaRIORIOOBParameter.class)
    public JAXBElement<String> createInsertarTransferenciaRIORIOOBParameterSucursalDest(String value) {
        return new JAXBElement<String>(_InsertarTransferenciaEntreBancosOBParameterSucursalDest_QNAME, String.class, InsertarTransferenciaRIORIOOBParameter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "Comision", scope = InsertarTransferenciaRIORIOOBParameter.class)
    public JAXBElement<BigDecimal> createInsertarTransferenciaRIORIOOBParameterComision(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_InsertarTransferenciaRIORIOOBParameterComision_QNAME, BigDecimal.class, InsertarTransferenciaRIORIOOBParameter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "Beneficiario", scope = InsertarTransferenciaRIORIOOBParameter.class)
    public JAXBElement<String> createInsertarTransferenciaRIORIOOBParameterBeneficiario(String value) {
        return new JAXBElement<String>(_InsertarTransferenciaEntreBancosOBParameterBeneficiario_QNAME, String.class, InsertarTransferenciaRIORIOOBParameter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "Moneda", scope = InsertarTransferenciaRIORIOOBParameter.class)
    public JAXBElement<String> createInsertarTransferenciaRIORIOOBParameterMoneda(String value) {
        return new JAXBElement<String>(_InsertarTransferenciaEntreBancosOBParameterMoneda_QNAME, String.class, InsertarTransferenciaRIORIOOBParameter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "CbuDestino", scope = InsertarTransferenciaRIORIOOBParameter.class)
    public JAXBElement<String> createInsertarTransferenciaRIORIOOBParameterCbuDestino(String value) {
        return new JAXBElement<String>(_InsertarTransferenciaRIORIOOBParameterCbuDestino_QNAME, String.class, InsertarTransferenciaRIORIOOBParameter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "TipoOrd", scope = BPCUENTASCNSCUADREPERFILPARAMETER.class)
    public JAXBElement<String> createBPCUENTASCNSCUADREPERFILPARAMETERTipoOrd(String value) {
        return new JAXBElement<String>(_BPORDENESCMBESTADOPARAMETERTipoOrd_QNAME, String.class, BPCUENTASCNSCUADREPERFILPARAMETER.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "Especie", scope = BPCUENTASCNSCUADREPERFILPARAMETER.class)
    public JAXBElement<String> createBPCUENTASCNSCUADREPERFILPARAMETEREspecie(String value) {
        return new JAXBElement<String>(_BPORDENESCMBALTAPARAMETEREspecie_QNAME, String.class, BPCUENTASCNSCUADREPERFILPARAMETER.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "CodSubSist", scope = BPCUENTASCNSCUADREPERFILPARAMETER.class)
    public JAXBElement<String> createBPCUENTASCNSCUADREPERFILPARAMETERCodSubSist(String value) {
        return new JAXBElement<String>(_BPCUENTASCNSCUADREPERFILPARAMETERCodSubSist_QNAME, String.class, BPCUENTASCNSCUADREPERFILPARAMETER.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "CodSist", scope = BPCUENTASCNSCUADREPERFILPARAMETER.class)
    public JAXBElement<String> createBPCUENTASCNSCUADREPERFILPARAMETERCodSist(String value) {
        return new JAXBElement<String>(_BPORDENESCMBESTADOPARAMETERCodSist_QNAME, String.class, BPCUENTASCNSCUADREPERFILPARAMETER.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "Cuenta", scope = BPCUENTASCNSCUADREPERFILPARAMETER.class)
    public JAXBElement<String> createBPCUENTASCNSCUADREPERFILPARAMETERCuenta(String value) {
        return new JAXBElement<String>(_BPCUENTASCNSMOVIMIENTOSPARAMETERCuenta_QNAME, String.class, BPCUENTASCNSCUADREPERFILPARAMETER.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "Canal", scope = BPCUENTASCNSCUADREPERFILPARAMETER.class)
    public JAXBElement<String> createBPCUENTASCNSCUADREPERFILPARAMETERCanal(String value) {
        return new JAXBElement<String>(_BPCUENTASCNSMOVIMIENTOSPARAMETERCanal_QNAME, String.class, BPCUENTASCNSCUADREPERFILPARAMETER.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "Monto", scope = BPCUENTASCNSCUADREPERFILPARAMETER.class)
    public JAXBElement<BigDecimal> createBPCUENTASCNSCUADREPERFILPARAMETERMonto(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_BPCUENTASCNSCUADREPERFILPARAMETERMonto_QNAME, BigDecimal.class, BPCUENTASCNSCUADREPERFILPARAMETER.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "Precio", scope = BPCUENTASCNSCUADREPERFILPARAMETER.class)
    public JAXBElement<BigDecimal> createBPCUENTASCNSCUADREPERFILPARAMETERPrecio(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_BPCUENTASCNSCUADREPERFILPARAMETERPrecio_QNAME, BigDecimal.class, BPCUENTASCNSCUADREPERFILPARAMETER.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "Opcion", scope = BPCUENTASCNSCUADREPERFILPARAMETER.class)
    public JAXBElement<String> createBPCUENTASCNSCUADREPERFILPARAMETEROpcion(String value) {
        return new JAXBElement<String>(_BPCUENTASCNSCUADREPERFILPARAMETEROpcion_QNAME, String.class, BPCUENTASCNSCUADREPERFILPARAMETER.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "Moneda", scope = BPCUENTASCNSCUADREPERFILPARAMETER.class)
    public JAXBElement<String> createBPCUENTASCNSCUADREPERFILPARAMETERMoneda(String value) {
        return new JAXBElement<String>(_InsertarTransferenciaEntreBancosOBParameterMoneda_QNAME, String.class, BPCUENTASCNSCUADREPERFILPARAMETER.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "Limitado", scope = BPCUENTASCNSCUADREPERFILPARAMETER.class)
    public JAXBElement<String> createBPCUENTASCNSCUADREPERFILPARAMETERLimitado(String value) {
        return new JAXBElement<String>(_BPCUENTASCNSCUADREPERFILPARAMETERLimitado_QNAME, String.class, BPCUENTASCNSCUADREPERFILPARAMETER.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "TipoOrd", scope = InsertarOperacionCambioOBParameter.class)
    public JAXBElement<String> createInsertarOperacionCambioOBParameterTipoOrd(String value) {
        return new JAXBElement<String>(_BPORDENESCMBESTADOPARAMETERTipoOrd_QNAME, String.class, InsertarOperacionCambioOBParameter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "Xmonto", scope = InsertarOperacionCambioOBParameter.class)
    public JAXBElement<String> createInsertarOperacionCambioOBParameterXmonto(String value) {
        return new JAXBElement<String>(_BPORDENESCMBALTAPARAMETERXmonto_QNAME, String.class, InsertarOperacionCambioOBParameter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "Oper", scope = InsertarOperacionCambioOBParameter.class)
    public JAXBElement<String> createInsertarOperacionCambioOBParameterOper(String value) {
        return new JAXBElement<String>(_InsertarTransferenciaEntreBancosOBParameterOper_QNAME, String.class, InsertarOperacionCambioOBParameter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "Nup", scope = InsertarOperacionCambioOBParameter.class)
    public JAXBElement<String> createInsertarOperacionCambioOBParameterNup(String value) {
        return new JAXBElement<String>(_InsertarOperacionCambioOBParameterNup_QNAME, String.class, InsertarOperacionCambioOBParameter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "PrecUni8", scope = InsertarOperacionCambioOBParameter.class)
    public JAXBElement<BigDecimal> createInsertarOperacionCambioOBParameterPrecUni8(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_BPORDENESCMBALTAPARAMETERPrecUni8_QNAME, BigDecimal.class, InsertarOperacionCambioOBParameter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "Capital", scope = InsertarOperacionCambioOBParameter.class)
    public JAXBElement<BigDecimal> createInsertarOperacionCambioOBParameterCapital(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_BPORDENESCMBALTAPARAMETERCapital_QNAME, BigDecimal.class, InsertarOperacionCambioOBParameter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "CuentaDest", scope = InsertarOperacionCambioOBParameter.class)
    public JAXBElement<String> createInsertarOperacionCambioOBParameterCuentaDest(String value) {
        return new JAXBElement<String>(_InsertarOperacionCambioOBParameterCuentaDest_QNAME, String.class, InsertarOperacionCambioOBParameter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "Boleto", scope = InsertarOperacionCambioOBParameter.class)
    public JAXBElement<String> createInsertarOperacionCambioOBParameterBoleto(String value) {
        return new JAXBElement<String>(_InsertarOperacionCambioOBParameterBoleto_QNAME, String.class, InsertarOperacionCambioOBParameter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "NroCuitCuil", scope = InsertarOperacionCambioOBParameter.class)
    public JAXBElement<String> createInsertarOperacionCambioOBParameterNroCuitCuil(String value) {
        return new JAXBElement<String>(_InsertarOperacionCambioOBParameterNroCuitCuil_QNAME, String.class, InsertarOperacionCambioOBParameter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "Especie", scope = InsertarOperacionCambioOBParameter.class)
    public JAXBElement<String> createInsertarOperacionCambioOBParameterEspecie(String value) {
        return new JAXBElement<String>(_BPORDENESCMBALTAPARAMETEREspecie_QNAME, String.class, InsertarOperacionCambioOBParameter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "ValorInterno8", scope = InsertarOperacionCambioOBParameter.class)
    public JAXBElement<BigDecimal> createInsertarOperacionCambioOBParameterValorInterno8(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_BPORDENESCMBALTAPARAMETERValorInterno8_QNAME, BigDecimal.class, InsertarOperacionCambioOBParameter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "CantTitu8", scope = InsertarOperacionCambioOBParameter.class)
    public JAXBElement<BigDecimal> createInsertarOperacionCambioOBParameterCantTitu8(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_BPORDENESCMBALTAPARAMETERCantTitu8_QNAME, BigDecimal.class, InsertarOperacionCambioOBParameter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "CuentaOri", scope = InsertarOperacionCambioOBParameter.class)
    public JAXBElement<String> createInsertarOperacionCambioOBParameterCuentaOri(String value) {
        return new JAXBElement<String>(_InsertarOperacionCambioOBParameterCuentaOri_QNAME, String.class, InsertarOperacionCambioOBParameter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "NumMercap", scope = InsertarOperacionCambioOBParameter.class)
    public JAXBElement<String> createInsertarOperacionCambioOBParameterNumMercap(String value) {
        return new JAXBElement<String>(_BPORDENESCMBALTAPARAMETERNumMercap_QNAME, String.class, InsertarOperacionCambioOBParameter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "FciTipoCambio", scope = InsertarOperacionCambioOBParameter.class)
    public JAXBElement<BigDecimal> createInsertarOperacionCambioOBParameterFciTipoCambio(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_InsertarOperacionCambioOBParameterFciTipoCambio_QNAME, BigDecimal.class, InsertarOperacionCambioOBParameter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "MoneLiq", scope = InsertarOperacionCambioOBParameter.class)
    public JAXBElement<String> createInsertarOperacionCambioOBParameterMoneLiq(String value) {
        return new JAXBElement<String>(_BPORDENESCMBALTAPARAMETERMoneLiq_QNAME, String.class, InsertarOperacionCambioOBParameter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "SucuDest", scope = InsertarOperacionCambioOBParameter.class)
    public JAXBElement<String> createInsertarOperacionCambioOBParameterSucuDest(String value) {
        return new JAXBElement<String>(_InsertarOperacionCambioOBParameterSucuDest_QNAME, String.class, InsertarOperacionCambioOBParameter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "SucuOri", scope = InsertarOperacionCambioOBParameter.class)
    public JAXBElement<String> createInsertarOperacionCambioOBParameterSucuOri(String value) {
        return new JAXBElement<String>(_InsertarOperacionCambioOBParameterSucuOri_QNAME, String.class, InsertarOperacionCambioOBParameter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "CuitCuil", scope = InsertarOperacionCambioOBParameter.class)
    public JAXBElement<String> createInsertarOperacionCambioOBParameterCuitCuil(String value) {
        return new JAXBElement<String>(_InsertarOperacionCambioOBParameterCuitCuil_QNAME, String.class, InsertarOperacionCambioOBParameter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "TipoOrd", scope = InsertarTransferenciaRIORIOParameter.class)
    public JAXBElement<String> createInsertarTransferenciaRIORIOParameterTipoOrd(String value) {
        return new JAXBElement<String>(_BPORDENESCMBESTADOPARAMETERTipoOrd_QNAME, String.class, InsertarTransferenciaRIORIOParameter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "Oper", scope = InsertarTransferenciaRIORIOParameter.class)
    public JAXBElement<String> createInsertarTransferenciaRIORIOParameterOper(String value) {
        return new JAXBElement<String>(_InsertarTransferenciaEntreBancosOBParameterOper_QNAME, String.class, InsertarTransferenciaRIORIOParameter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "CuentaAltairDest", scope = InsertarTransferenciaRIORIOParameter.class)
    public JAXBElement<String> createInsertarTransferenciaRIORIOParameterCuentaAltairDest(String value) {
        return new JAXBElement<String>(_InsertarTransferenciaEntreBancosOBParameterCuentaAltairDest_QNAME, String.class, InsertarTransferenciaRIORIOParameter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "CuitDestino", scope = InsertarTransferenciaRIORIOParameter.class)
    public JAXBElement<String> createInsertarTransferenciaRIORIOParameterCuitDestino(String value) {
        return new JAXBElement<String>(_InsertarTransferenciaRIORIOOBParameterCuitDestino_QNAME, String.class, InsertarTransferenciaRIORIOParameter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "Observaciones", scope = InsertarTransferenciaRIORIOParameter.class)
    public JAXBElement<String> createInsertarTransferenciaRIORIOParameterObservaciones(String value) {
        return new JAXBElement<String>(_InsertarTransferenciaEntreBancosOBParameterObservaciones_QNAME, String.class, InsertarTransferenciaRIORIOParameter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "CuentaAltairOrig", scope = InsertarTransferenciaRIORIOParameter.class)
    public JAXBElement<String> createInsertarTransferenciaRIORIOParameterCuentaAltairOrig(String value) {
        return new JAXBElement<String>(_InsertarTransferenciaEntreBancosOBParameterCuentaAltairOrig_QNAME, String.class, InsertarTransferenciaRIORIOParameter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "Concepto", scope = InsertarTransferenciaRIORIOParameter.class)
    public JAXBElement<String> createInsertarTransferenciaRIORIOParameterConcepto(String value) {
        return new JAXBElement<String>(_InsertarTransferenciaEntreBancosOBParameterConcepto_QNAME, String.class, InsertarTransferenciaRIORIOParameter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "SucursalOrig", scope = InsertarTransferenciaRIORIOParameter.class)
    public JAXBElement<String> createInsertarTransferenciaRIORIOParameterSucursalOrig(String value) {
        return new JAXBElement<String>(_InsertarTransferenciaRIORIOOBParameterSucursalOrig_QNAME, String.class, InsertarTransferenciaRIORIOParameter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "Importe", scope = InsertarTransferenciaRIORIOParameter.class)
    public JAXBElement<BigDecimal> createInsertarTransferenciaRIORIOParameterImporte(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_InsertarTransferenciaEntreBancosOBParameterImporte_QNAME, BigDecimal.class, InsertarTransferenciaRIORIOParameter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "OrigenOrden", scope = InsertarTransferenciaRIORIOParameter.class)
    public JAXBElement<String> createInsertarTransferenciaRIORIOParameterOrigenOrden(String value) {
        return new JAXBElement<String>(_InsertarTransferenciaRIORIOParameterOrigenOrden_QNAME, String.class, InsertarTransferenciaRIORIOParameter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "SucursalDest", scope = InsertarTransferenciaRIORIOParameter.class)
    public JAXBElement<String> createInsertarTransferenciaRIORIOParameterSucursalDest(String value) {
        return new JAXBElement<String>(_InsertarTransferenciaEntreBancosOBParameterSucursalDest_QNAME, String.class, InsertarTransferenciaRIORIOParameter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "Comision", scope = InsertarTransferenciaRIORIOParameter.class)
    public JAXBElement<BigDecimal> createInsertarTransferenciaRIORIOParameterComision(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_InsertarTransferenciaRIORIOOBParameterComision_QNAME, BigDecimal.class, InsertarTransferenciaRIORIOParameter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "Beneficiario", scope = InsertarTransferenciaRIORIOParameter.class)
    public JAXBElement<String> createInsertarTransferenciaRIORIOParameterBeneficiario(String value) {
        return new JAXBElement<String>(_InsertarTransferenciaEntreBancosOBParameterBeneficiario_QNAME, String.class, InsertarTransferenciaRIORIOParameter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "Moneda", scope = InsertarTransferenciaRIORIOParameter.class)
    public JAXBElement<String> createInsertarTransferenciaRIORIOParameterMoneda(String value) {
        return new JAXBElement<String>(_InsertarTransferenciaEntreBancosOBParameterMoneda_QNAME, String.class, InsertarTransferenciaRIORIOParameter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "CbuDestino", scope = InsertarTransferenciaRIORIOParameter.class)
    public JAXBElement<String> createInsertarTransferenciaRIORIOParameterCbuDestino(String value) {
        return new JAXBElement<String>(_InsertarTransferenciaRIORIOOBParameterCbuDestino_QNAME, String.class, InsertarTransferenciaRIORIOParameter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "TipoOrd", scope = InsertarOperacionCambioParameter.class)
    public JAXBElement<String> createInsertarOperacionCambioParameterTipoOrd(String value) {
        return new JAXBElement<String>(_BPORDENESCMBESTADOPARAMETERTipoOrd_QNAME, String.class, InsertarOperacionCambioParameter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "Xmonto", scope = InsertarOperacionCambioParameter.class)
    public JAXBElement<String> createInsertarOperacionCambioParameterXmonto(String value) {
        return new JAXBElement<String>(_BPORDENESCMBALTAPARAMETERXmonto_QNAME, String.class, InsertarOperacionCambioParameter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "Oper", scope = InsertarOperacionCambioParameter.class)
    public JAXBElement<String> createInsertarOperacionCambioParameterOper(String value) {
        return new JAXBElement<String>(_InsertarTransferenciaEntreBancosOBParameterOper_QNAME, String.class, InsertarOperacionCambioParameter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "Nup", scope = InsertarOperacionCambioParameter.class)
    public JAXBElement<String> createInsertarOperacionCambioParameterNup(String value) {
        return new JAXBElement<String>(_InsertarOperacionCambioOBParameterNup_QNAME, String.class, InsertarOperacionCambioParameter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "PrecUni8", scope = InsertarOperacionCambioParameter.class)
    public JAXBElement<BigDecimal> createInsertarOperacionCambioParameterPrecUni8(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_BPORDENESCMBALTAPARAMETERPrecUni8_QNAME, BigDecimal.class, InsertarOperacionCambioParameter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "Capital", scope = InsertarOperacionCambioParameter.class)
    public JAXBElement<BigDecimal> createInsertarOperacionCambioParameterCapital(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_BPORDENESCMBALTAPARAMETERCapital_QNAME, BigDecimal.class, InsertarOperacionCambioParameter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "CuentaDest", scope = InsertarOperacionCambioParameter.class)
    public JAXBElement<String> createInsertarOperacionCambioParameterCuentaDest(String value) {
        return new JAXBElement<String>(_InsertarOperacionCambioOBParameterCuentaDest_QNAME, String.class, InsertarOperacionCambioParameter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "Boleto", scope = InsertarOperacionCambioParameter.class)
    public JAXBElement<String> createInsertarOperacionCambioParameterBoleto(String value) {
        return new JAXBElement<String>(_InsertarOperacionCambioOBParameterBoleto_QNAME, String.class, InsertarOperacionCambioParameter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "OrigenOrden", scope = InsertarOperacionCambioParameter.class)
    public JAXBElement<String> createInsertarOperacionCambioParameterOrigenOrden(String value) {
        return new JAXBElement<String>(_InsertarTransferenciaRIORIOParameterOrigenOrden_QNAME, String.class, InsertarOperacionCambioParameter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "NroCuitCuil", scope = InsertarOperacionCambioParameter.class)
    public JAXBElement<String> createInsertarOperacionCambioParameterNroCuitCuil(String value) {
        return new JAXBElement<String>(_InsertarOperacionCambioOBParameterNroCuitCuil_QNAME, String.class, InsertarOperacionCambioParameter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "Especie", scope = InsertarOperacionCambioParameter.class)
    public JAXBElement<String> createInsertarOperacionCambioParameterEspecie(String value) {
        return new JAXBElement<String>(_BPORDENESCMBALTAPARAMETEREspecie_QNAME, String.class, InsertarOperacionCambioParameter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "ValorInterno8", scope = InsertarOperacionCambioParameter.class)
    public JAXBElement<BigDecimal> createInsertarOperacionCambioParameterValorInterno8(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_BPORDENESCMBALTAPARAMETERValorInterno8_QNAME, BigDecimal.class, InsertarOperacionCambioParameter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "CantTitu8", scope = InsertarOperacionCambioParameter.class)
    public JAXBElement<BigDecimal> createInsertarOperacionCambioParameterCantTitu8(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_BPORDENESCMBALTAPARAMETERCantTitu8_QNAME, BigDecimal.class, InsertarOperacionCambioParameter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "CuentaOri", scope = InsertarOperacionCambioParameter.class)
    public JAXBElement<String> createInsertarOperacionCambioParameterCuentaOri(String value) {
        return new JAXBElement<String>(_InsertarOperacionCambioOBParameterCuentaOri_QNAME, String.class, InsertarOperacionCambioParameter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "NumMercap", scope = InsertarOperacionCambioParameter.class)
    public JAXBElement<String> createInsertarOperacionCambioParameterNumMercap(String value) {
        return new JAXBElement<String>(_BPORDENESCMBALTAPARAMETERNumMercap_QNAME, String.class, InsertarOperacionCambioParameter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "FciTipoCambio", scope = InsertarOperacionCambioParameter.class)
    public JAXBElement<BigDecimal> createInsertarOperacionCambioParameterFciTipoCambio(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_InsertarOperacionCambioOBParameterFciTipoCambio_QNAME, BigDecimal.class, InsertarOperacionCambioParameter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "MoneLiq", scope = InsertarOperacionCambioParameter.class)
    public JAXBElement<String> createInsertarOperacionCambioParameterMoneLiq(String value) {
        return new JAXBElement<String>(_BPORDENESCMBALTAPARAMETERMoneLiq_QNAME, String.class, InsertarOperacionCambioParameter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "SucuDest", scope = InsertarOperacionCambioParameter.class)
    public JAXBElement<String> createInsertarOperacionCambioParameterSucuDest(String value) {
        return new JAXBElement<String>(_InsertarOperacionCambioOBParameterSucuDest_QNAME, String.class, InsertarOperacionCambioParameter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "SucuOri", scope = InsertarOperacionCambioParameter.class)
    public JAXBElement<String> createInsertarOperacionCambioParameterSucuOri(String value) {
        return new JAXBElement<String>(_InsertarOperacionCambioOBParameterSucuOri_QNAME, String.class, InsertarOperacionCambioParameter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "CuitCuil", scope = InsertarOperacionCambioParameter.class)
    public JAXBElement<String> createInsertarOperacionCambioParameterCuitCuil(String value) {
        return new JAXBElement<String>(_InsertarOperacionCambioOBParameterCuitCuil_QNAME, String.class, InsertarOperacionCambioParameter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "CuentaAltair", scope = BPCUENTASCNSROSSIPARAMETER.class)
    public JAXBElement<String> createBPCUENTASCNSROSSIPARAMETERCuentaAltair(String value) {
        return new JAXBElement<String>(_BPCUENTASCNSROSSIPARAMETERCuentaAltair_QNAME, String.class, BPCUENTASCNSROSSIPARAMETER.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "Divisa", scope = BPCUENTASCNSROSSIPARAMETER.class)
    public JAXBElement<String> createBPCUENTASCNSROSSIPARAMETERDivisa(String value) {
        return new JAXBElement<String>(_BPCUENTASCNSMOVIMIENTOSPARAMETERDivisa_QNAME, String.class, BPCUENTASCNSROSSIPARAMETER.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "Sucursal", scope = BPCUENTASCNSROSSIPARAMETER.class)
    public JAXBElement<String> createBPCUENTASCNSROSSIPARAMETERSucursal(String value) {
        return new JAXBElement<String>(_BPCUENTASCNSROSSIPARAMETERSucursal_QNAME, String.class, BPCUENTASCNSROSSIPARAMETER.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "Subproducto", scope = BPCUENTASCNSROSSIPARAMETER.class)
    public JAXBElement<String> createBPCUENTASCNSROSSIPARAMETERSubproducto(String value) {
        return new JAXBElement<String>(_BPCUENTASCNSROSSIPARAMETERSubproducto_QNAME, String.class, BPCUENTASCNSROSSIPARAMETER.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "Producto", scope = BPCUENTASCNSROSSIPARAMETER.class)
    public JAXBElement<String> createBPCUENTASCNSROSSIPARAMETERProducto(String value) {
        return new JAXBElement<String>(_BPCUENTASCNSROSSIPARAMETERProducto_QNAME, String.class, BPCUENTASCNSROSSIPARAMETER.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "CuentaBP", scope = BPCUENTASCNSATITPARAMETER.class)
    public JAXBElement<BigDecimal> createBPCUENTASCNSATITPARAMETERCuentaBP(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_BPCUENTASCNSATITPARAMETERCuentaBP_QNAME, BigDecimal.class, BPCUENTASCNSATITPARAMETER.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "Cuenta", scope = BPCUENTASCNSSALDOSPARAMETER.class)
    public JAXBElement<String> createBPCUENTASCNSSALDOSPARAMETERCuenta(String value) {
        return new JAXBElement<String>(_BPCUENTASCNSMOVIMIENTOSPARAMETERCuenta_QNAME, String.class, BPCUENTASCNSSALDOSPARAMETER.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "Canal", scope = BPCUENTASCNSSALDOSPARAMETER.class)
    public JAXBElement<String> createBPCUENTASCNSSALDOSPARAMETERCanal(String value) {
        return new JAXBElement<String>(_BPCUENTASCNSMOVIMIENTOSPARAMETERCanal_QNAME, String.class, BPCUENTASCNSSALDOSPARAMETER.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "Modo", scope = BPCUENTASCMBGRABAMOVPARAMETER.class)
    public JAXBElement<String> createBPCUENTASCMBGRABAMOVPARAMETERModo(String value) {
        return new JAXBElement<String>(_BPORDENESCMBALTAPARAMETERModo_QNAME, String.class, BPCUENTASCMBGRABAMOVPARAMETER.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "CuentaAltair", scope = BPCUENTASCMBGRABAMOVPARAMETER.class)
    public JAXBElement<BigDecimal> createBPCUENTASCMBGRABAMOVPARAMETERCuentaAltair(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_BPCUENTASCNSROSSIPARAMETERCuentaAltair_QNAME, BigDecimal.class, BPCUENTASCMBGRABAMOVPARAMETER.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "MovObservacion", scope = BPCUENTASCMBGRABAMOVPARAMETER.class)
    public JAXBElement<String> createBPCUENTASCMBGRABAMOVPARAMETERMovObservacion(String value) {
        return new JAXBElement<String>(_BPCUENTASCMBGRABAMOVPARAMETERMovObservacion_QNAME, String.class, BPCUENTASCMBGRABAMOVPARAMETER.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "TipoMovimiento", scope = BPCUENTASCMBGRABAMOVPARAMETER.class)
    public JAXBElement<String> createBPCUENTASCMBGRABAMOVPARAMETERTipoMovimiento(String value) {
        return new JAXBElement<String>(_BPCUENTASCMBGRABAMOVPARAMETERTipoMovimiento_QNAME, String.class, BPCUENTASCMBGRABAMOVPARAMETER.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "Origen", scope = BPCUENTASCMBGRABAMOVPARAMETER.class)
    public JAXBElement<String> createBPCUENTASCMBGRABAMOVPARAMETEROrigen(String value) {
        return new JAXBElement<String>(_BPCUENTASCMBGRABAMOVPARAMETEROrigen_QNAME, String.class, BPCUENTASCMBGRABAMOVPARAMETER.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "DivisaAltair", scope = BPCUENTASCMBGRABAMOVPARAMETER.class)
    public JAXBElement<String> createBPCUENTASCMBGRABAMOVPARAMETERDivisaAltair(String value) {
        return new JAXBElement<String>(_BPCUENTASCMBGRABAMOVPARAMETERDivisaAltair_QNAME, String.class, BPCUENTASCMBGRABAMOVPARAMETER.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "SucursalAltair", scope = BPCUENTASCMBGRABAMOVPARAMETER.class)
    public JAXBElement<BigDecimal> createBPCUENTASCMBGRABAMOVPARAMETERSucursalAltair(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_BPCUENTASCMBGRABAMOVPARAMETERSucursalAltair_QNAME, BigDecimal.class, BPCUENTASCMBGRABAMOVPARAMETER.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "AceptaBatch", scope = BPCUENTASCMBGRABAMOVPARAMETER.class)
    public JAXBElement<String> createBPCUENTASCMBGRABAMOVPARAMETERAceptaBatch(String value) {
        return new JAXBElement<String>(_BPCUENTASCMBGRABAMOVPARAMETERAceptaBatch_QNAME, String.class, BPCUENTASCMBGRABAMOVPARAMETER.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "Importe", scope = BPCUENTASCMBGRABAMOVPARAMETER.class)
    public JAXBElement<BigDecimal> createBPCUENTASCMBGRABAMOVPARAMETERImporte(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_InsertarTransferenciaEntreBancosOBParameterImporte_QNAME, BigDecimal.class, BPCUENTASCMBGRABAMOVPARAMETER.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "NuApunte", scope = BPCUENTASCMBGRABAMOVPARAMETER.class)
    public JAXBElement<BigDecimal> createBPCUENTASCMBGRABAMOVPARAMETERNuApunte(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_BPCUENTASCMBGRABAMOVPARAMETERNuApunte_QNAME, BigDecimal.class, BPCUENTASCMBGRABAMOVPARAMETER.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "PasswordSesion", scope = BPCUENTASCMBGRABAMOVPARAMETER.class)
    public JAXBElement<String> createBPCUENTASCMBGRABAMOVPARAMETERPasswordSesion(String value) {
        return new JAXBElement<String>(_BPCUENTASCMBGRABAMOVPARAMETERPasswordSesion_QNAME, String.class, BPCUENTASCMBGRABAMOVPARAMETER.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "CodAltair", scope = BPCUENTASCMBGRABAMOVPARAMETER.class)
    public JAXBElement<BigDecimal> createBPCUENTASCMBGRABAMOVPARAMETERCodAltair(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_BPCUENTASCMBGRABAMOVPARAMETERCodAltair_QNAME, BigDecimal.class, BPCUENTASCMBGRABAMOVPARAMETER.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "UsuarioSesion", scope = BPCUENTASCMBGRABAMOVPARAMETER.class)
    public JAXBElement<String> createBPCUENTASCMBGRABAMOVPARAMETERUsuarioSesion(String value) {
        return new JAXBElement<String>(_BPCUENTASCMBGRABAMOVPARAMETERUsuarioSesion_QNAME, String.class, BPCUENTASCMBGRABAMOVPARAMETER.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "TipoOrd", scope = InsertarTransferenciaEntreBancosParameter.class)
    public JAXBElement<String> createInsertarTransferenciaEntreBancosParameterTipoOrd(String value) {
        return new JAXBElement<String>(_BPORDENESCMBESTADOPARAMETERTipoOrd_QNAME, String.class, InsertarTransferenciaEntreBancosParameter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "Oper", scope = InsertarTransferenciaEntreBancosParameter.class)
    public JAXBElement<String> createInsertarTransferenciaEntreBancosParameterOper(String value) {
        return new JAXBElement<String>(_InsertarTransferenciaEntreBancosOBParameterOper_QNAME, String.class, InsertarTransferenciaEntreBancosParameter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "CuentaAltairDest", scope = InsertarTransferenciaEntreBancosParameter.class)
    public JAXBElement<String> createInsertarTransferenciaEntreBancosParameterCuentaAltairDest(String value) {
        return new JAXBElement<String>(_InsertarTransferenciaEntreBancosOBParameterCuentaAltairDest_QNAME, String.class, InsertarTransferenciaEntreBancosParameter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "EntidadDest", scope = InsertarTransferenciaEntreBancosParameter.class)
    public JAXBElement<String> createInsertarTransferenciaEntreBancosParameterEntidadDest(String value) {
        return new JAXBElement<String>(_InsertarTransferenciaEntreBancosOBParameterEntidadDest_QNAME, String.class, InsertarTransferenciaEntreBancosParameter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "ModoTransf", scope = InsertarTransferenciaEntreBancosParameter.class)
    public JAXBElement<String> createInsertarTransferenciaEntreBancosParameterModoTransf(String value) {
        return new JAXBElement<String>(_InsertarTransferenciaEntreBancosOBParameterModoTransf_QNAME, String.class, InsertarTransferenciaEntreBancosParameter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "Observaciones", scope = InsertarTransferenciaEntreBancosParameter.class)
    public JAXBElement<String> createInsertarTransferenciaEntreBancosParameterObservaciones(String value) {
        return new JAXBElement<String>(_InsertarTransferenciaEntreBancosOBParameterObservaciones_QNAME, String.class, InsertarTransferenciaEntreBancosParameter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "CuentaAltairOrig", scope = InsertarTransferenciaEntreBancosParameter.class)
    public JAXBElement<String> createInsertarTransferenciaEntreBancosParameterCuentaAltairOrig(String value) {
        return new JAXBElement<String>(_InsertarTransferenciaEntreBancosOBParameterCuentaAltairOrig_QNAME, String.class, InsertarTransferenciaEntreBancosParameter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "Concepto", scope = InsertarTransferenciaEntreBancosParameter.class)
    public JAXBElement<String> createInsertarTransferenciaEntreBancosParameterConcepto(String value) {
        return new JAXBElement<String>(_InsertarTransferenciaEntreBancosOBParameterConcepto_QNAME, String.class, InsertarTransferenciaEntreBancosParameter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "TipoDocDest", scope = InsertarTransferenciaEntreBancosParameter.class)
    public JAXBElement<String> createInsertarTransferenciaEntreBancosParameterTipoDocDest(String value) {
        return new JAXBElement<String>(_InsertarTransferenciaEntreBancosOBParameterTipoDocDest_QNAME, String.class, InsertarTransferenciaEntreBancosParameter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "OrigenOrden", scope = InsertarTransferenciaEntreBancosParameter.class)
    public JAXBElement<String> createInsertarTransferenciaEntreBancosParameterOrigenOrden(String value) {
        return new JAXBElement<String>(_InsertarTransferenciaRIORIOParameterOrigenOrden_QNAME, String.class, InsertarTransferenciaEntreBancosParameter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "Importe", scope = InsertarTransferenciaEntreBancosParameter.class)
    public JAXBElement<BigDecimal> createInsertarTransferenciaEntreBancosParameterImporte(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_InsertarTransferenciaEntreBancosOBParameterImporte_QNAME, BigDecimal.class, InsertarTransferenciaEntreBancosParameter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "SucursalDest", scope = InsertarTransferenciaEntreBancosParameter.class)
    public JAXBElement<String> createInsertarTransferenciaEntreBancosParameterSucursalDest(String value) {
        return new JAXBElement<String>(_InsertarTransferenciaEntreBancosOBParameterSucursalDest_QNAME, String.class, InsertarTransferenciaEntreBancosParameter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "TipoCuentaDest", scope = InsertarTransferenciaEntreBancosParameter.class)
    public JAXBElement<String> createInsertarTransferenciaEntreBancosParameterTipoCuentaDest(String value) {
        return new JAXBElement<String>(_InsertarTransferenciaEntreBancosOBParameterTipoCuentaDest_QNAME, String.class, InsertarTransferenciaEntreBancosParameter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "CbuDest", scope = InsertarTransferenciaEntreBancosParameter.class)
    public JAXBElement<String> createInsertarTransferenciaEntreBancosParameterCbuDest(String value) {
        return new JAXBElement<String>(_InsertarTransferenciaEntreBancosOBParameterCbuDest_QNAME, String.class, InsertarTransferenciaEntreBancosParameter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "Beneficiario", scope = InsertarTransferenciaEntreBancosParameter.class)
    public JAXBElement<String> createInsertarTransferenciaEntreBancosParameterBeneficiario(String value) {
        return new JAXBElement<String>(_InsertarTransferenciaEntreBancosOBParameterBeneficiario_QNAME, String.class, InsertarTransferenciaEntreBancosParameter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "DocumentoOrig", scope = InsertarTransferenciaEntreBancosParameter.class)
    public JAXBElement<String> createInsertarTransferenciaEntreBancosParameterDocumentoOrig(String value) {
        return new JAXBElement<String>(_InsertarTransferenciaEntreBancosOBParameterDocumentoOrig_QNAME, String.class, InsertarTransferenciaEntreBancosParameter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "DocumentoDest", scope = InsertarTransferenciaEntreBancosParameter.class)
    public JAXBElement<String> createInsertarTransferenciaEntreBancosParameterDocumentoDest(String value) {
        return new JAXBElement<String>(_InsertarTransferenciaEntreBancosOBParameterDocumentoDest_QNAME, String.class, InsertarTransferenciaEntreBancosParameter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", name = "Moneda", scope = InsertarTransferenciaEntreBancosParameter.class)
    public JAXBElement<String> createInsertarTransferenciaEntreBancosParameterMoneda(String value) {
        return new JAXBElement<String>(_InsertarTransferenciaEntreBancosOBParameterMoneda_QNAME, String.class, InsertarTransferenciaEntreBancosParameter.class, value);
    }

}
