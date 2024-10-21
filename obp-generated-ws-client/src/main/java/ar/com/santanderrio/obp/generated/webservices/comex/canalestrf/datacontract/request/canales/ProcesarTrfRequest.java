
package ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.request.canales;

import java.math.BigDecimal;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.request.FirmaRequest;


/**
 * <p>Java class for ProcesarTrfRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ProcesarTrfRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request}FirmaRequest">
 *       &lt;sequence>
 *         &lt;element name="Acepta_Ddjj" type="{http://www.w3.org/2001/XMLSchema}short" minOccurs="0"/>
 *         &lt;element name="Alicuota_Ganancias" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="Alicuota_Iva" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="Aplica_Int_Finan" type="{http://www.w3.org/2001/XMLSchema}short" minOccurs="0"/>
 *         &lt;element name="Autogestion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Base_Imp_Ganancias" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="Base_Imp_Iva" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="Cargo_Ganancias" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Con_Dj4834" type="{http://www.w3.org/2001/XMLSchema}short" minOccurs="0"/>
 *         &lt;element name="Concepto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Condicion_Iva" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Cta_altair" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Cuenta_Bco_Intermediario" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Cuenta_Beneficiario" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Cuenta_Debito" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Cuit_Benef" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Declaracion_Adicional" type="{http://www.w3.org/2001/XMLSchema}short" minOccurs="0"/>
 *         &lt;element name="Despachos" type="{http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf}ArrayOfCargaDespachosRequest" minOccurs="0"/>
 *         &lt;element name="Doble_Imp_Articulo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Doble_Imp_Ganancias" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Doble_Imp_Pais" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Emp_Vinculada_Op1" type="{http://www.w3.org/2001/XMLSchema}short" minOccurs="0"/>
 *         &lt;element name="Emp_Vinculada_Op2" type="{http://www.w3.org/2001/XMLSchema}short" minOccurs="0"/>
 *         &lt;element name="Emp_Vinculada_Op3" type="{http://www.w3.org/2001/XMLSchema}short" minOccurs="0"/>
 *         &lt;element name="Emp_Vinculada_Op4" type="{http://www.w3.org/2001/XMLSchema}short" minOccurs="0"/>
 *         &lt;element name="Emp_Vinculada_Op5" type="{http://www.w3.org/2001/XMLSchema}short" minOccurs="0"/>
 *         &lt;element name="Estado_Transferencia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Fecha_Emb_Estimada" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Fecha_Embarque" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Gastos" type="{http://www.w3.org/2001/XMLSchema}short" minOccurs="0"/>
 *         &lt;element name="Id_Alicuota" type="{http://www.w3.org/2001/XMLSchema}short" minOccurs="0"/>
 *         &lt;element name="Id_Banco_Beneficiario" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Id_Banco_Intermediario" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Id_Beneficiario" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Id_Cond_Venta" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="Importe_Transferencia" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="Inst_Recibido" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Inst_Vendido" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Inv_Acre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Iva" type="{http://www.w3.org/2001/XMLSchema}short" minOccurs="0"/>
 *         &lt;element name="Moneda" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Monto_Ret_Ganacias" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="Monto_Ret_Iva" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="Motivo_Rechazo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Nif_gan" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="No_Corresp_Int_Finan" type="{http://www.w3.org/2001/XMLSchema}short" minOccurs="0"/>
 *         &lt;element name="No_Retencion_Art14" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="No_Retencion_Articulo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="No_Retencion_Motivo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="No_Retencion_Pais" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Nro_Documento_Cliente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Nro_Form_Inv" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Nro_Transferencia" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="Nup_Cliente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Observaciones" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Opcion_Gan" type="{http://www.w3.org/2001/XMLSchema}short" minOccurs="0"/>
 *         &lt;element name="Opcion_Int_Finan" type="{http://www.w3.org/2001/XMLSchema}short" minOccurs="0"/>
 *         &lt;element name="Opcion_No_Iva" type="{http://www.w3.org/2001/XMLSchema}short" minOccurs="0"/>
 *         &lt;element name="Opcion_No_Retencion" type="{http://www.w3.org/2001/XMLSchema}short" minOccurs="0"/>
 *         &lt;element name="Otros_Int_Finan" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Otros_No_Iva" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="P_NRO_FORM_REL" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="Pos_Arancelaria" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Referencia_Cliente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Registro_Inpi" type="{http://www.w3.org/2001/XMLSchema}short" minOccurs="0"/>
 *         &lt;element name="Retiene_Ganancias" type="{http://www.w3.org/2001/XMLSchema}short" minOccurs="0"/>
 *         &lt;element name="Retiene_Iva" type="{http://www.w3.org/2001/XMLSchema}short" minOccurs="0"/>
 *         &lt;element name="Texto_Dj4834" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Tipo_Documento_Cliente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Tipo_Operacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Tipo_Transferencia" type="{http://www.w3.org/2001/XMLSchema}short" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProcesarTrfRequest", propOrder = {
    "aceptaDdjj",
    "alicuotaGanancias",
    "alicuotaIva",
    "aplicaIntFinan",
    "autogestion",
    "baseImpGanancias",
    "baseImpIva",
    "cargoGanancias",
    "conDj4834",
    "concepto",
    "condicionIva",
    "ctaAltair",
    "cuentaBcoIntermediario",
    "cuentaBeneficiario",
    "cuentaDebito",
    "cuitBenef",
    "declaracionAdicional",
    "despachos",
    "dobleImpArticulo",
    "dobleImpGanancias",
    "dobleImpPais",
    "empVinculadaOp1",
    "empVinculadaOp2",
    "empVinculadaOp3",
    "empVinculadaOp4",
    "empVinculadaOp5",
    "estadoTransferencia",
    "fechaEmbEstimada",
    "fechaEmbarque",
    "gastos",
    "idAlicuota",
    "idBancoBeneficiario",
    "idBancoIntermediario",
    "idBeneficiario",
    "idCondVenta",
    "importeTransferencia",
    "instRecibido",
    "instVendido",
    "invAcre",
    "iva",
    "moneda",
    "montoRetGanacias",
    "montoRetIva",
    "motivoRechazo",
    "nifGan",
    "noCorrespIntFinan",
    "noRetencionArt14",
    "noRetencionArticulo",
    "noRetencionMotivo",
    "noRetencionPais",
    "nroDocumentoCliente",
    "nroFormInv",
    "nroTransferencia",
    "nupCliente",
    "observaciones",
    "opcionGan",
    "opcionIntFinan",
    "opcionNoIva",
    "opcionNoRetencion",
    "otrosIntFinan",
    "otrosNoIva",
    "pnroformrel",
    "posArancelaria",
    "referenciaCliente",
    "registroInpi",
    "retieneGanancias",
    "retieneIva",
    "textoDj4834",
    "tipoDocumentoCliente",
    "tipoOperacion",
    "tipoTransferencia"
})
public class ProcesarTrfRequest
    extends FirmaRequest
{

    @XmlElementRef(name = "Acepta_Ddjj", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", type = JAXBElement.class)
    protected JAXBElement<Short> aceptaDdjj;
    @XmlElementRef(name = "Alicuota_Ganancias", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", type = JAXBElement.class)
    protected JAXBElement<BigDecimal> alicuotaGanancias;
    @XmlElementRef(name = "Alicuota_Iva", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", type = JAXBElement.class)
    protected JAXBElement<BigDecimal> alicuotaIva;
    @XmlElementRef(name = "Aplica_Int_Finan", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", type = JAXBElement.class)
    protected JAXBElement<Short> aplicaIntFinan;
    @XmlElementRef(name = "Autogestion", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", type = JAXBElement.class)
    protected JAXBElement<String> autogestion;
    @XmlElementRef(name = "Base_Imp_Ganancias", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", type = JAXBElement.class)
    protected JAXBElement<BigDecimal> baseImpGanancias;
    @XmlElementRef(name = "Base_Imp_Iva", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", type = JAXBElement.class)
    protected JAXBElement<BigDecimal> baseImpIva;
    @XmlElementRef(name = "Cargo_Ganancias", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", type = JAXBElement.class)
    protected JAXBElement<String> cargoGanancias;
    @XmlElementRef(name = "Con_Dj4834", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", type = JAXBElement.class)
    protected JAXBElement<Short> conDj4834;
    @XmlElementRef(name = "Concepto", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", type = JAXBElement.class)
    protected JAXBElement<String> concepto;
    @XmlElementRef(name = "Condicion_Iva", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", type = JAXBElement.class)
    protected JAXBElement<String> condicionIva;
    @XmlElementRef(name = "Cta_altair", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", type = JAXBElement.class)
    protected JAXBElement<String> ctaAltair;
    @XmlElementRef(name = "Cuenta_Bco_Intermediario", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", type = JAXBElement.class)
    protected JAXBElement<String> cuentaBcoIntermediario;
    @XmlElementRef(name = "Cuenta_Beneficiario", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", type = JAXBElement.class)
    protected JAXBElement<String> cuentaBeneficiario;
    @XmlElementRef(name = "Cuenta_Debito", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", type = JAXBElement.class)
    protected JAXBElement<String> cuentaDebito;
    @XmlElementRef(name = "Cuit_Benef", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", type = JAXBElement.class)
    protected JAXBElement<String> cuitBenef;
    @XmlElementRef(name = "Declaracion_Adicional", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", type = JAXBElement.class)
    protected JAXBElement<Short> declaracionAdicional;
    @XmlElementRef(name = "Despachos", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", type = JAXBElement.class)
    protected JAXBElement<ArrayOfCargaDespachosRequest> despachos;
    @XmlElementRef(name = "Doble_Imp_Articulo", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", type = JAXBElement.class)
    protected JAXBElement<String> dobleImpArticulo;
    @XmlElementRef(name = "Doble_Imp_Ganancias", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", type = JAXBElement.class)
    protected JAXBElement<String> dobleImpGanancias;
    @XmlElementRef(name = "Doble_Imp_Pais", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", type = JAXBElement.class)
    protected JAXBElement<String> dobleImpPais;
    @XmlElementRef(name = "Emp_Vinculada_Op1", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", type = JAXBElement.class)
    protected JAXBElement<Short> empVinculadaOp1;
    @XmlElementRef(name = "Emp_Vinculada_Op2", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", type = JAXBElement.class)
    protected JAXBElement<Short> empVinculadaOp2;
    @XmlElementRef(name = "Emp_Vinculada_Op3", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", type = JAXBElement.class)
    protected JAXBElement<Short> empVinculadaOp3;
    @XmlElementRef(name = "Emp_Vinculada_Op4", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", type = JAXBElement.class)
    protected JAXBElement<Short> empVinculadaOp4;
    @XmlElementRef(name = "Emp_Vinculada_Op5", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", type = JAXBElement.class)
    protected JAXBElement<Short> empVinculadaOp5;
    @XmlElementRef(name = "Estado_Transferencia", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", type = JAXBElement.class)
    protected JAXBElement<String> estadoTransferencia;
    @XmlElementRef(name = "Fecha_Emb_Estimada", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", type = JAXBElement.class)
    protected JAXBElement<String> fechaEmbEstimada;
    @XmlElementRef(name = "Fecha_Embarque", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", type = JAXBElement.class)
    protected JAXBElement<String> fechaEmbarque;
    @XmlElementRef(name = "Gastos", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", type = JAXBElement.class)
    protected JAXBElement<Short> gastos;
    @XmlElementRef(name = "Id_Alicuota", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", type = JAXBElement.class)
    protected JAXBElement<Short> idAlicuota;
    @XmlElementRef(name = "Id_Banco_Beneficiario", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", type = JAXBElement.class)
    protected JAXBElement<String> idBancoBeneficiario;
    @XmlElementRef(name = "Id_Banco_Intermediario", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", type = JAXBElement.class)
    protected JAXBElement<String> idBancoIntermediario;
    @XmlElementRef(name = "Id_Beneficiario", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", type = JAXBElement.class)
    protected JAXBElement<String> idBeneficiario;
    @XmlElementRef(name = "Id_Cond_Venta", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", type = JAXBElement.class)
    protected JAXBElement<Long> idCondVenta;
    @XmlElementRef(name = "Importe_Transferencia", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", type = JAXBElement.class)
    protected JAXBElement<BigDecimal> importeTransferencia;
    @XmlElementRef(name = "Inst_Recibido", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", type = JAXBElement.class)
    protected JAXBElement<String> instRecibido;
    @XmlElementRef(name = "Inst_Vendido", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", type = JAXBElement.class)
    protected JAXBElement<String> instVendido;
    @XmlElementRef(name = "Inv_Acre", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", type = JAXBElement.class)
    protected JAXBElement<String> invAcre;
    @XmlElementRef(name = "Iva", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", type = JAXBElement.class)
    protected JAXBElement<Short> iva;
    @XmlElementRef(name = "Moneda", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", type = JAXBElement.class)
    protected JAXBElement<String> moneda;
    @XmlElementRef(name = "Monto_Ret_Ganacias", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", type = JAXBElement.class)
    protected JAXBElement<BigDecimal> montoRetGanacias;
    @XmlElementRef(name = "Monto_Ret_Iva", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", type = JAXBElement.class)
    protected JAXBElement<BigDecimal> montoRetIva;
    @XmlElementRef(name = "Motivo_Rechazo", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", type = JAXBElement.class)
    protected JAXBElement<String> motivoRechazo;
    @XmlElementRef(name = "Nif_gan", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", type = JAXBElement.class)
    protected JAXBElement<Long> nifGan;
    @XmlElementRef(name = "No_Corresp_Int_Finan", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", type = JAXBElement.class)
    protected JAXBElement<Short> noCorrespIntFinan;
    @XmlElementRef(name = "No_Retencion_Art14", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", type = JAXBElement.class)
    protected JAXBElement<String> noRetencionArt14;
    @XmlElementRef(name = "No_Retencion_Articulo", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", type = JAXBElement.class)
    protected JAXBElement<String> noRetencionArticulo;
    @XmlElementRef(name = "No_Retencion_Motivo", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", type = JAXBElement.class)
    protected JAXBElement<String> noRetencionMotivo;
    @XmlElementRef(name = "No_Retencion_Pais", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", type = JAXBElement.class)
    protected JAXBElement<String> noRetencionPais;
    @XmlElementRef(name = "Nro_Documento_Cliente", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", type = JAXBElement.class)
    protected JAXBElement<String> nroDocumentoCliente;
    @XmlElementRef(name = "Nro_Form_Inv", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", type = JAXBElement.class)
    protected JAXBElement<String> nroFormInv;
    @XmlElementRef(name = "Nro_Transferencia", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", type = JAXBElement.class)
    protected JAXBElement<Integer> nroTransferencia;
    @XmlElementRef(name = "Nup_Cliente", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", type = JAXBElement.class)
    protected JAXBElement<String> nupCliente;
    @XmlElementRef(name = "Observaciones", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", type = JAXBElement.class)
    protected JAXBElement<String> observaciones;
    @XmlElementRef(name = "Opcion_Gan", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", type = JAXBElement.class)
    protected JAXBElement<Short> opcionGan;
    @XmlElementRef(name = "Opcion_Int_Finan", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", type = JAXBElement.class)
    protected JAXBElement<Short> opcionIntFinan;
    @XmlElementRef(name = "Opcion_No_Iva", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", type = JAXBElement.class)
    protected JAXBElement<Short> opcionNoIva;
    @XmlElementRef(name = "Opcion_No_Retencion", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", type = JAXBElement.class)
    protected JAXBElement<Short> opcionNoRetencion;
    @XmlElementRef(name = "Otros_Int_Finan", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", type = JAXBElement.class)
    protected JAXBElement<String> otrosIntFinan;
    @XmlElementRef(name = "Otros_No_Iva", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", type = JAXBElement.class)
    protected JAXBElement<String> otrosNoIva;
    @XmlElementRef(name = "P_NRO_FORM_REL", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", type = JAXBElement.class)
    protected JAXBElement<Long> pnroformrel;
    @XmlElementRef(name = "Pos_Arancelaria", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", type = JAXBElement.class)
    protected JAXBElement<String> posArancelaria;
    @XmlElementRef(name = "Referencia_Cliente", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", type = JAXBElement.class)
    protected JAXBElement<String> referenciaCliente;
    @XmlElementRef(name = "Registro_Inpi", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", type = JAXBElement.class)
    protected JAXBElement<Short> registroInpi;
    @XmlElementRef(name = "Retiene_Ganancias", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", type = JAXBElement.class)
    protected JAXBElement<Short> retieneGanancias;
    @XmlElementRef(name = "Retiene_Iva", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", type = JAXBElement.class)
    protected JAXBElement<Short> retieneIva;
    @XmlElementRef(name = "Texto_Dj4834", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", type = JAXBElement.class)
    protected JAXBElement<String> textoDj4834;
    @XmlElementRef(name = "Tipo_Documento_Cliente", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", type = JAXBElement.class)
    protected JAXBElement<String> tipoDocumentoCliente;
    @XmlElementRef(name = "Tipo_Operacion", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", type = JAXBElement.class)
    protected JAXBElement<String> tipoOperacion;
    @XmlElementRef(name = "Tipo_Transferencia", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", type = JAXBElement.class)
    protected JAXBElement<Short> tipoTransferencia;

    /**
     * Gets the value of the aceptaDdjj property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Short }{@code >}
     *     
     */
    public JAXBElement<Short> getAceptaDdjj() {
        return aceptaDdjj;
    }

    /**
     * Sets the value of the aceptaDdjj property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Short }{@code >}
     *     
     */
    public void setAceptaDdjj(JAXBElement<Short> value) {
        this.aceptaDdjj = value;
    }

    /**
     * Gets the value of the alicuotaGanancias property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public JAXBElement<BigDecimal> getAlicuotaGanancias() {
        return alicuotaGanancias;
    }

    /**
     * Sets the value of the alicuotaGanancias property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public void setAlicuotaGanancias(JAXBElement<BigDecimal> value) {
        this.alicuotaGanancias = value;
    }

    /**
     * Gets the value of the alicuotaIva property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public JAXBElement<BigDecimal> getAlicuotaIva() {
        return alicuotaIva;
    }

    /**
     * Sets the value of the alicuotaIva property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public void setAlicuotaIva(JAXBElement<BigDecimal> value) {
        this.alicuotaIva = value;
    }

    /**
     * Gets the value of the aplicaIntFinan property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Short }{@code >}
     *     
     */
    public JAXBElement<Short> getAplicaIntFinan() {
        return aplicaIntFinan;
    }

    /**
     * Sets the value of the aplicaIntFinan property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Short }{@code >}
     *     
     */
    public void setAplicaIntFinan(JAXBElement<Short> value) {
        this.aplicaIntFinan = value;
    }

    /**
     * Gets the value of the autogestion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getAutogestion() {
        return autogestion;
    }

    /**
     * Sets the value of the autogestion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setAutogestion(JAXBElement<String> value) {
        this.autogestion = value;
    }

    /**
     * Gets the value of the baseImpGanancias property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public JAXBElement<BigDecimal> getBaseImpGanancias() {
        return baseImpGanancias;
    }

    /**
     * Sets the value of the baseImpGanancias property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public void setBaseImpGanancias(JAXBElement<BigDecimal> value) {
        this.baseImpGanancias = value;
    }

    /**
     * Gets the value of the baseImpIva property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public JAXBElement<BigDecimal> getBaseImpIva() {
        return baseImpIva;
    }

    /**
     * Sets the value of the baseImpIva property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public void setBaseImpIva(JAXBElement<BigDecimal> value) {
        this.baseImpIva = value;
    }

    /**
     * Gets the value of the cargoGanancias property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCargoGanancias() {
        return cargoGanancias;
    }

    /**
     * Sets the value of the cargoGanancias property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCargoGanancias(JAXBElement<String> value) {
        this.cargoGanancias = value;
    }

    /**
     * Gets the value of the conDj4834 property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Short }{@code >}
     *     
     */
    public JAXBElement<Short> getConDj4834() {
        return conDj4834;
    }

    /**
     * Sets the value of the conDj4834 property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Short }{@code >}
     *     
     */
    public void setConDj4834(JAXBElement<Short> value) {
        this.conDj4834 = value;
    }

    /**
     * Gets the value of the concepto property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getConcepto() {
        return concepto;
    }

    /**
     * Sets the value of the concepto property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setConcepto(JAXBElement<String> value) {
        this.concepto = value;
    }

    /**
     * Gets the value of the condicionIva property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCondicionIva() {
        return condicionIva;
    }

    /**
     * Sets the value of the condicionIva property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCondicionIva(JAXBElement<String> value) {
        this.condicionIva = value;
    }

    /**
     * Gets the value of the ctaAltair property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCtaAltair() {
        return ctaAltair;
    }

    /**
     * Sets the value of the ctaAltair property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCtaAltair(JAXBElement<String> value) {
        this.ctaAltair = value;
    }

    /**
     * Gets the value of the cuentaBcoIntermediario property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCuentaBcoIntermediario() {
        return cuentaBcoIntermediario;
    }

    /**
     * Sets the value of the cuentaBcoIntermediario property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCuentaBcoIntermediario(JAXBElement<String> value) {
        this.cuentaBcoIntermediario = value;
    }

    /**
     * Gets the value of the cuentaBeneficiario property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCuentaBeneficiario() {
        return cuentaBeneficiario;
    }

    /**
     * Sets the value of the cuentaBeneficiario property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCuentaBeneficiario(JAXBElement<String> value) {
        this.cuentaBeneficiario = value;
    }

    /**
     * Gets the value of the cuentaDebito property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCuentaDebito() {
        return cuentaDebito;
    }

    /**
     * Sets the value of the cuentaDebito property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCuentaDebito(JAXBElement<String> value) {
        this.cuentaDebito = value;
    }

    /**
     * Gets the value of the cuitBenef property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCuitBenef() {
        return cuitBenef;
    }

    /**
     * Sets the value of the cuitBenef property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCuitBenef(JAXBElement<String> value) {
        this.cuitBenef = value;
    }

    /**
     * Gets the value of the declaracionAdicional property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Short }{@code >}
     *     
     */
    public JAXBElement<Short> getDeclaracionAdicional() {
        return declaracionAdicional;
    }

    /**
     * Sets the value of the declaracionAdicional property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Short }{@code >}
     *     
     */
    public void setDeclaracionAdicional(JAXBElement<Short> value) {
        this.declaracionAdicional = value;
    }

    /**
     * Gets the value of the despachos property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfCargaDespachosRequest }{@code >}
     *     
     */
    public JAXBElement<ArrayOfCargaDespachosRequest> getDespachos() {
        return despachos;
    }

    /**
     * Sets the value of the despachos property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfCargaDespachosRequest }{@code >}
     *     
     */
    public void setDespachos(JAXBElement<ArrayOfCargaDespachosRequest> value) {
        this.despachos = value;
    }

    /**
     * Gets the value of the dobleImpArticulo property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDobleImpArticulo() {
        return dobleImpArticulo;
    }

    /**
     * Sets the value of the dobleImpArticulo property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDobleImpArticulo(JAXBElement<String> value) {
        this.dobleImpArticulo = value;
    }

    /**
     * Gets the value of the dobleImpGanancias property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDobleImpGanancias() {
        return dobleImpGanancias;
    }

    /**
     * Sets the value of the dobleImpGanancias property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDobleImpGanancias(JAXBElement<String> value) {
        this.dobleImpGanancias = value;
    }

    /**
     * Gets the value of the dobleImpPais property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDobleImpPais() {
        return dobleImpPais;
    }

    /**
     * Sets the value of the dobleImpPais property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDobleImpPais(JAXBElement<String> value) {
        this.dobleImpPais = value;
    }

    /**
     * Gets the value of the empVinculadaOp1 property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Short }{@code >}
     *     
     */
    public JAXBElement<Short> getEmpVinculadaOp1() {
        return empVinculadaOp1;
    }

    /**
     * Sets the value of the empVinculadaOp1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Short }{@code >}
     *     
     */
    public void setEmpVinculadaOp1(JAXBElement<Short> value) {
        this.empVinculadaOp1 = value;
    }

    /**
     * Gets the value of the empVinculadaOp2 property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Short }{@code >}
     *     
     */
    public JAXBElement<Short> getEmpVinculadaOp2() {
        return empVinculadaOp2;
    }

    /**
     * Sets the value of the empVinculadaOp2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Short }{@code >}
     *     
     */
    public void setEmpVinculadaOp2(JAXBElement<Short> value) {
        this.empVinculadaOp2 = value;
    }

    /**
     * Gets the value of the empVinculadaOp3 property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Short }{@code >}
     *     
     */
    public JAXBElement<Short> getEmpVinculadaOp3() {
        return empVinculadaOp3;
    }

    /**
     * Sets the value of the empVinculadaOp3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Short }{@code >}
     *     
     */
    public void setEmpVinculadaOp3(JAXBElement<Short> value) {
        this.empVinculadaOp3 = value;
    }

    /**
     * Gets the value of the empVinculadaOp4 property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Short }{@code >}
     *     
     */
    public JAXBElement<Short> getEmpVinculadaOp4() {
        return empVinculadaOp4;
    }

    /**
     * Sets the value of the empVinculadaOp4 property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Short }{@code >}
     *     
     */
    public void setEmpVinculadaOp4(JAXBElement<Short> value) {
        this.empVinculadaOp4 = value;
    }

    /**
     * Gets the value of the empVinculadaOp5 property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Short }{@code >}
     *     
     */
    public JAXBElement<Short> getEmpVinculadaOp5() {
        return empVinculadaOp5;
    }

    /**
     * Sets the value of the empVinculadaOp5 property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Short }{@code >}
     *     
     */
    public void setEmpVinculadaOp5(JAXBElement<Short> value) {
        this.empVinculadaOp5 = value;
    }

    /**
     * Gets the value of the estadoTransferencia property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getEstadoTransferencia() {
        return estadoTransferencia;
    }

    /**
     * Sets the value of the estadoTransferencia property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setEstadoTransferencia(JAXBElement<String> value) {
        this.estadoTransferencia = value;
    }

    /**
     * Gets the value of the fechaEmbEstimada property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFechaEmbEstimada() {
        return fechaEmbEstimada;
    }

    /**
     * Sets the value of the fechaEmbEstimada property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFechaEmbEstimada(JAXBElement<String> value) {
        this.fechaEmbEstimada = value;
    }

    /**
     * Gets the value of the fechaEmbarque property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFechaEmbarque() {
        return fechaEmbarque;
    }

    /**
     * Sets the value of the fechaEmbarque property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFechaEmbarque(JAXBElement<String> value) {
        this.fechaEmbarque = value;
    }

    /**
     * Gets the value of the gastos property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Short }{@code >}
     *     
     */
    public JAXBElement<Short> getGastos() {
        return gastos;
    }

    /**
     * Sets the value of the gastos property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Short }{@code >}
     *     
     */
    public void setGastos(JAXBElement<Short> value) {
        this.gastos = value;
    }

    /**
     * Gets the value of the idAlicuota property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Short }{@code >}
     *     
     */
    public JAXBElement<Short> getIdAlicuota() {
        return idAlicuota;
    }

    /**
     * Sets the value of the idAlicuota property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Short }{@code >}
     *     
     */
    public void setIdAlicuota(JAXBElement<Short> value) {
        this.idAlicuota = value;
    }

    /**
     * Gets the value of the idBancoBeneficiario property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIdBancoBeneficiario() {
        return idBancoBeneficiario;
    }

    /**
     * Sets the value of the idBancoBeneficiario property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIdBancoBeneficiario(JAXBElement<String> value) {
        this.idBancoBeneficiario = value;
    }

    /**
     * Gets the value of the idBancoIntermediario property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIdBancoIntermediario() {
        return idBancoIntermediario;
    }

    /**
     * Sets the value of the idBancoIntermediario property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIdBancoIntermediario(JAXBElement<String> value) {
        this.idBancoIntermediario = value;
    }

    /**
     * Gets the value of the idBeneficiario property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIdBeneficiario() {
        return idBeneficiario;
    }

    /**
     * Sets the value of the idBeneficiario property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIdBeneficiario(JAXBElement<String> value) {
        this.idBeneficiario = value;
    }

    /**
     * Gets the value of the idCondVenta property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Long }{@code >}
     *     
     */
    public JAXBElement<Long> getIdCondVenta() {
        return idCondVenta;
    }

    /**
     * Sets the value of the idCondVenta property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Long }{@code >}
     *     
     */
    public void setIdCondVenta(JAXBElement<Long> value) {
        this.idCondVenta = value;
    }

    /**
     * Gets the value of the importeTransferencia property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public JAXBElement<BigDecimal> getImporteTransferencia() {
        return importeTransferencia;
    }

    /**
     * Sets the value of the importeTransferencia property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public void setImporteTransferencia(JAXBElement<BigDecimal> value) {
        this.importeTransferencia = value;
    }

    /**
     * Gets the value of the instRecibido property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getInstRecibido() {
        return instRecibido;
    }

    /**
     * Sets the value of the instRecibido property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setInstRecibido(JAXBElement<String> value) {
        this.instRecibido = value;
    }

    /**
     * Gets the value of the instVendido property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getInstVendido() {
        return instVendido;
    }

    /**
     * Sets the value of the instVendido property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setInstVendido(JAXBElement<String> value) {
        this.instVendido = value;
    }

    /**
     * Gets the value of the invAcre property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getInvAcre() {
        return invAcre;
    }

    /**
     * Sets the value of the invAcre property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setInvAcre(JAXBElement<String> value) {
        this.invAcre = value;
    }

    /**
     * Gets the value of the iva property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Short }{@code >}
     *     
     */
    public JAXBElement<Short> getIva() {
        return iva;
    }

    /**
     * Sets the value of the iva property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Short }{@code >}
     *     
     */
    public void setIva(JAXBElement<Short> value) {
        this.iva = value;
    }

    /**
     * Gets the value of the moneda property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getMoneda() {
        return moneda;
    }

    /**
     * Sets the value of the moneda property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setMoneda(JAXBElement<String> value) {
        this.moneda = value;
    }

    /**
     * Gets the value of the montoRetGanacias property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public JAXBElement<BigDecimal> getMontoRetGanacias() {
        return montoRetGanacias;
    }

    /**
     * Sets the value of the montoRetGanacias property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public void setMontoRetGanacias(JAXBElement<BigDecimal> value) {
        this.montoRetGanacias = value;
    }

    /**
     * Gets the value of the montoRetIva property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public JAXBElement<BigDecimal> getMontoRetIva() {
        return montoRetIva;
    }

    /**
     * Sets the value of the montoRetIva property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public void setMontoRetIva(JAXBElement<BigDecimal> value) {
        this.montoRetIva = value;
    }

    /**
     * Gets the value of the motivoRechazo property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getMotivoRechazo() {
        return motivoRechazo;
    }

    /**
     * Sets the value of the motivoRechazo property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setMotivoRechazo(JAXBElement<String> value) {
        this.motivoRechazo = value;
    }

    /**
     * Gets the value of the nifGan property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Long }{@code >}
     *     
     */
    public JAXBElement<Long> getNifGan() {
        return nifGan;
    }

    /**
     * Sets the value of the nifGan property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Long }{@code >}
     *     
     */
    public void setNifGan(JAXBElement<Long> value) {
        this.nifGan = value;
    }

    /**
     * Gets the value of the noCorrespIntFinan property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Short }{@code >}
     *     
     */
    public JAXBElement<Short> getNoCorrespIntFinan() {
        return noCorrespIntFinan;
    }

    /**
     * Sets the value of the noCorrespIntFinan property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Short }{@code >}
     *     
     */
    public void setNoCorrespIntFinan(JAXBElement<Short> value) {
        this.noCorrespIntFinan = value;
    }

    /**
     * Gets the value of the noRetencionArt14 property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNoRetencionArt14() {
        return noRetencionArt14;
    }

    /**
     * Sets the value of the noRetencionArt14 property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNoRetencionArt14(JAXBElement<String> value) {
        this.noRetencionArt14 = value;
    }

    /**
     * Gets the value of the noRetencionArticulo property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNoRetencionArticulo() {
        return noRetencionArticulo;
    }

    /**
     * Sets the value of the noRetencionArticulo property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNoRetencionArticulo(JAXBElement<String> value) {
        this.noRetencionArticulo = value;
    }

    /**
     * Gets the value of the noRetencionMotivo property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNoRetencionMotivo() {
        return noRetencionMotivo;
    }

    /**
     * Sets the value of the noRetencionMotivo property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNoRetencionMotivo(JAXBElement<String> value) {
        this.noRetencionMotivo = value;
    }

    /**
     * Gets the value of the noRetencionPais property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNoRetencionPais() {
        return noRetencionPais;
    }

    /**
     * Sets the value of the noRetencionPais property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNoRetencionPais(JAXBElement<String> value) {
        this.noRetencionPais = value;
    }

    /**
     * Gets the value of the nroDocumentoCliente property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNroDocumentoCliente() {
        return nroDocumentoCliente;
    }

    /**
     * Sets the value of the nroDocumentoCliente property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNroDocumentoCliente(JAXBElement<String> value) {
        this.nroDocumentoCliente = value;
    }

    /**
     * Gets the value of the nroFormInv property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNroFormInv() {
        return nroFormInv;
    }

    /**
     * Sets the value of the nroFormInv property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNroFormInv(JAXBElement<String> value) {
        this.nroFormInv = value;
    }

    /**
     * Gets the value of the nroTransferencia property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public JAXBElement<Integer> getNroTransferencia() {
        return nroTransferencia;
    }

    /**
     * Sets the value of the nroTransferencia property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public void setNroTransferencia(JAXBElement<Integer> value) {
        this.nroTransferencia = value;
    }

    /**
     * Gets the value of the nupCliente property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNupCliente() {
        return nupCliente;
    }

    /**
     * Sets the value of the nupCliente property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNupCliente(JAXBElement<String> value) {
        this.nupCliente = value;
    }

    /**
     * Gets the value of the observaciones property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getObservaciones() {
        return observaciones;
    }

    /**
     * Sets the value of the observaciones property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setObservaciones(JAXBElement<String> value) {
        this.observaciones = value;
    }

    /**
     * Gets the value of the opcionGan property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Short }{@code >}
     *     
     */
    public JAXBElement<Short> getOpcionGan() {
        return opcionGan;
    }

    /**
     * Sets the value of the opcionGan property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Short }{@code >}
     *     
     */
    public void setOpcionGan(JAXBElement<Short> value) {
        this.opcionGan = value;
    }

    /**
     * Gets the value of the opcionIntFinan property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Short }{@code >}
     *     
     */
    public JAXBElement<Short> getOpcionIntFinan() {
        return opcionIntFinan;
    }

    /**
     * Sets the value of the opcionIntFinan property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Short }{@code >}
     *     
     */
    public void setOpcionIntFinan(JAXBElement<Short> value) {
        this.opcionIntFinan = value;
    }

    /**
     * Gets the value of the opcionNoIva property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Short }{@code >}
     *     
     */
    public JAXBElement<Short> getOpcionNoIva() {
        return opcionNoIva;
    }

    /**
     * Sets the value of the opcionNoIva property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Short }{@code >}
     *     
     */
    public void setOpcionNoIva(JAXBElement<Short> value) {
        this.opcionNoIva = value;
    }

    /**
     * Gets the value of the opcionNoRetencion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Short }{@code >}
     *     
     */
    public JAXBElement<Short> getOpcionNoRetencion() {
        return opcionNoRetencion;
    }

    /**
     * Sets the value of the opcionNoRetencion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Short }{@code >}
     *     
     */
    public void setOpcionNoRetencion(JAXBElement<Short> value) {
        this.opcionNoRetencion = value;
    }

    /**
     * Gets the value of the otrosIntFinan property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOtrosIntFinan() {
        return otrosIntFinan;
    }

    /**
     * Sets the value of the otrosIntFinan property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOtrosIntFinan(JAXBElement<String> value) {
        this.otrosIntFinan = value;
    }

    /**
     * Gets the value of the otrosNoIva property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOtrosNoIva() {
        return otrosNoIva;
    }

    /**
     * Sets the value of the otrosNoIva property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOtrosNoIva(JAXBElement<String> value) {
        this.otrosNoIva = value;
    }

    /**
     * Gets the value of the pnroformrel property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Long }{@code >}
     *     
     */
    public JAXBElement<Long> getPNROFORMREL() {
        return pnroformrel;
    }

    /**
     * Sets the value of the pnroformrel property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Long }{@code >}
     *     
     */
    public void setPNROFORMREL(JAXBElement<Long> value) {
        this.pnroformrel = value;
    }

    /**
     * Gets the value of the posArancelaria property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPosArancelaria() {
        return posArancelaria;
    }

    /**
     * Sets the value of the posArancelaria property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPosArancelaria(JAXBElement<String> value) {
        this.posArancelaria = value;
    }

    /**
     * Gets the value of the referenciaCliente property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getReferenciaCliente() {
        return referenciaCliente;
    }

    /**
     * Sets the value of the referenciaCliente property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setReferenciaCliente(JAXBElement<String> value) {
        this.referenciaCliente = value;
    }

    /**
     * Gets the value of the registroInpi property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Short }{@code >}
     *     
     */
    public JAXBElement<Short> getRegistroInpi() {
        return registroInpi;
    }

    /**
     * Sets the value of the registroInpi property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Short }{@code >}
     *     
     */
    public void setRegistroInpi(JAXBElement<Short> value) {
        this.registroInpi = value;
    }

    /**
     * Gets the value of the retieneGanancias property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Short }{@code >}
     *     
     */
    public JAXBElement<Short> getRetieneGanancias() {
        return retieneGanancias;
    }

    /**
     * Sets the value of the retieneGanancias property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Short }{@code >}
     *     
     */
    public void setRetieneGanancias(JAXBElement<Short> value) {
        this.retieneGanancias = value;
    }

    /**
     * Gets the value of the retieneIva property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Short }{@code >}
     *     
     */
    public JAXBElement<Short> getRetieneIva() {
        return retieneIva;
    }

    /**
     * Sets the value of the retieneIva property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Short }{@code >}
     *     
     */
    public void setRetieneIva(JAXBElement<Short> value) {
        this.retieneIva = value;
    }

    /**
     * Gets the value of the textoDj4834 property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTextoDj4834() {
        return textoDj4834;
    }

    /**
     * Sets the value of the textoDj4834 property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTextoDj4834(JAXBElement<String> value) {
        this.textoDj4834 = value;
    }

    /**
     * Gets the value of the tipoDocumentoCliente property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTipoDocumentoCliente() {
        return tipoDocumentoCliente;
    }

    /**
     * Sets the value of the tipoDocumentoCliente property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTipoDocumentoCliente(JAXBElement<String> value) {
        this.tipoDocumentoCliente = value;
    }

    /**
     * Gets the value of the tipoOperacion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTipoOperacion() {
        return tipoOperacion;
    }

    /**
     * Sets the value of the tipoOperacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTipoOperacion(JAXBElement<String> value) {
        this.tipoOperacion = value;
    }

    /**
     * Gets the value of the tipoTransferencia property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Short }{@code >}
     *     
     */
    public JAXBElement<Short> getTipoTransferencia() {
        return tipoTransferencia;
    }

    /**
     * Sets the value of the tipoTransferencia property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Short }{@code >}
     *     
     */
    public void setTipoTransferencia(JAXBElement<Short> value) {
        this.tipoTransferencia = value;
    }

}
