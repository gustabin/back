
package ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales;

import java.math.BigDecimal;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.response.BaseResponse;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.domain.ArrayOfDetalleTrfImagen;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.domain.ArrayOfRechazo;


/**
 * <p>Java class for ConsultaDetalleTrfOBPResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ConsultaDetalleTrfOBPResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Response}BaseResponse">
 *       &lt;sequence>
 *         &lt;element name="Acepta_Ddjj" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="Alicuota_Ganancias" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="Alicuota_Iva" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="Aplica_Int_Finan" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="Banco_Beneficiario" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Banco_Intermediario" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Base_Imp_Ganancias" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="Base_Imp_Iva" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="Beneficiario" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Beneficiario_Domicilio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Beneficiario_Pais" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Cargo_Ganancias" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Codigo_Concepto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Con_Dj4834" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="Concepto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Condicion_Iva" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Cta_Altair" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Cta_debito" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Cuenta_Bco_Intermediario" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Cuenta_Beneficiario" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Cuenta_Debito" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Declaracion_Adicional" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="Doble_Imp_Articulo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Doble_Imp_Ganancias" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Doble_Imp_Pais" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Emp_Vinculada" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="Emp_Vinculada_Op2" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="Emp_Vinculada_Op3" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="Emp_Vinculada_Op4" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="Emp_Vinculada_Op5" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="Estado_Transferencia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Fecha_Embarque" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Gastos" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="Id_Alicuota" type="{http://www.w3.org/2001/XMLSchema}short" minOccurs="0"/>
 *         &lt;element name="Id_CT" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="Id_Concepto" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="Id_Cond_Venta" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="Imagenes" type="{Domain}ArrayOfDetalleTrfImagen" minOccurs="0"/>
 *         &lt;element name="Importe_Transferencia" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="Inst_Recibido" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Inst_Vendido" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Moneda" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Monto_Ret_Ganacias" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="Monto_Ret_Iva" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="No_Corresp_Int_Finan" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="No_Retencion_Art14" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="No_Retencion_Articulo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="No_Retencion_Motivo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="No_Retencion_Pais" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Nro_Documento_Cliente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Nro_Solicitud" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Nro_Transferencia_Rel" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="Opcion_Int_Finan" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="Opcion_No_Iva" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="Opcion_No_Retencion" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="Otros_Int_Finan" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Otros_No_Iva" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Pos_Arancelaria" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Rechazos" type="{Domain}ArrayOfRechazo" minOccurs="0"/>
 *         &lt;element name="Referencia_Cliente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Registro_Inpi" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="Retiene_Ganancias" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="Retiene_Iva" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="Texto_Dj4834" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Tipo_Documento_Cliente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Tipo_Transferencia" type="{http://www.w3.org/2001/XMLSchema}short" minOccurs="0"/>
 *         &lt;element name="Trf_Vigente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Vinculo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConsultaDetalleTrfOBPResponse", propOrder = {
    "aceptaDdjj",
    "alicuotaGanancias",
    "alicuotaIva",
    "aplicaIntFinan",
    "bancoBeneficiario",
    "bancoIntermediario",
    "baseImpGanancias",
    "baseImpIva",
    "beneficiario",
    "beneficiarioDomicilio",
    "beneficiarioPais",
    "cargoGanancias",
    "codigoConcepto",
    "conDj4834",
    "concepto",
    "condicionIva",
    "ctaAltair",
    "ctaDebito",
    "cuentaBcoIntermediario",
    "cuentaBeneficiario",
    "cuentaDebito",
    "declaracionAdicional",
    "dobleImpArticulo",
    "dobleImpGanancias",
    "dobleImpPais",
    "empVinculada",
    "empVinculadaOp2",
    "empVinculadaOp3",
    "empVinculadaOp4",
    "empVinculadaOp5",
    "estadoTransferencia",
    "fechaEmbarque",
    "gastos",
    "idAlicuota",
    "idCT",
    "idConcepto",
    "idCondVenta",
    "imagenes",
    "importeTransferencia",
    "instRecibido",
    "instVendido",
    "moneda",
    "montoRetGanacias",
    "montoRetIva",
    "noCorrespIntFinan",
    "noRetencionArt14",
    "noRetencionArticulo",
    "noRetencionMotivo",
    "noRetencionPais",
    "nroDocumentoCliente",
    "nroSolicitud",
    "nroTransferenciaRel",
    "opcionIntFinan",
    "opcionNoIva",
    "opcionNoRetencion",
    "otrosIntFinan",
    "otrosNoIva",
    "posArancelaria",
    "rechazos",
    "referenciaCliente",
    "registroInpi",
    "retieneGanancias",
    "retieneIva",
    "textoDj4834",
    "tipoDocumentoCliente",
    "tipoTransferencia",
    "trfVigente",
    "vinculo"
})
public class ConsultaDetalleTrfOBPResponse
    extends BaseResponse
{

    @XmlElementRef(name = "Acepta_Ddjj", namespace = "Response/Canales", type = JAXBElement.class)
    protected JAXBElement<Integer> aceptaDdjj;
    @XmlElementRef(name = "Alicuota_Ganancias", namespace = "Response/Canales", type = JAXBElement.class)
    protected JAXBElement<BigDecimal> alicuotaGanancias;
    @XmlElementRef(name = "Alicuota_Iva", namespace = "Response/Canales", type = JAXBElement.class)
    protected JAXBElement<BigDecimal> alicuotaIva;
    @XmlElementRef(name = "Aplica_Int_Finan", namespace = "Response/Canales", type = JAXBElement.class)
    protected JAXBElement<Integer> aplicaIntFinan;
    @XmlElementRef(name = "Banco_Beneficiario", namespace = "Response/Canales", type = JAXBElement.class)
    protected JAXBElement<String> bancoBeneficiario;
    @XmlElementRef(name = "Banco_Intermediario", namespace = "Response/Canales", type = JAXBElement.class)
    protected JAXBElement<String> bancoIntermediario;
    @XmlElementRef(name = "Base_Imp_Ganancias", namespace = "Response/Canales", type = JAXBElement.class)
    protected JAXBElement<BigDecimal> baseImpGanancias;
    @XmlElementRef(name = "Base_Imp_Iva", namespace = "Response/Canales", type = JAXBElement.class)
    protected JAXBElement<BigDecimal> baseImpIva;
    @XmlElementRef(name = "Beneficiario", namespace = "Response/Canales", type = JAXBElement.class)
    protected JAXBElement<String> beneficiario;
    @XmlElementRef(name = "Beneficiario_Domicilio", namespace = "Response/Canales", type = JAXBElement.class)
    protected JAXBElement<String> beneficiarioDomicilio;
    @XmlElementRef(name = "Beneficiario_Pais", namespace = "Response/Canales", type = JAXBElement.class)
    protected JAXBElement<String> beneficiarioPais;
    @XmlElementRef(name = "Cargo_Ganancias", namespace = "Response/Canales", type = JAXBElement.class)
    protected JAXBElement<String> cargoGanancias;
    @XmlElementRef(name = "Codigo_Concepto", namespace = "Response/Canales", type = JAXBElement.class)
    protected JAXBElement<String> codigoConcepto;
    @XmlElementRef(name = "Con_Dj4834", namespace = "Response/Canales", type = JAXBElement.class)
    protected JAXBElement<Integer> conDj4834;
    @XmlElementRef(name = "Concepto", namespace = "Response/Canales", type = JAXBElement.class)
    protected JAXBElement<String> concepto;
    @XmlElementRef(name = "Condicion_Iva", namespace = "Response/Canales", type = JAXBElement.class)
    protected JAXBElement<String> condicionIva;
    @XmlElementRef(name = "Cta_Altair", namespace = "Response/Canales", type = JAXBElement.class)
    protected JAXBElement<String> ctaAltair;
    @XmlElementRef(name = "Cta_debito", namespace = "Response/Canales", type = JAXBElement.class)
    protected JAXBElement<String> ctaDebito;
    @XmlElementRef(name = "Cuenta_Bco_Intermediario", namespace = "Response/Canales", type = JAXBElement.class)
    protected JAXBElement<String> cuentaBcoIntermediario;
    @XmlElementRef(name = "Cuenta_Beneficiario", namespace = "Response/Canales", type = JAXBElement.class)
    protected JAXBElement<String> cuentaBeneficiario;
    @XmlElementRef(name = "Cuenta_Debito", namespace = "Response/Canales", type = JAXBElement.class)
    protected JAXBElement<String> cuentaDebito;
    @XmlElementRef(name = "Declaracion_Adicional", namespace = "Response/Canales", type = JAXBElement.class)
    protected JAXBElement<Integer> declaracionAdicional;
    @XmlElementRef(name = "Doble_Imp_Articulo", namespace = "Response/Canales", type = JAXBElement.class)
    protected JAXBElement<String> dobleImpArticulo;
    @XmlElementRef(name = "Doble_Imp_Ganancias", namespace = "Response/Canales", type = JAXBElement.class)
    protected JAXBElement<String> dobleImpGanancias;
    @XmlElementRef(name = "Doble_Imp_Pais", namespace = "Response/Canales", type = JAXBElement.class)
    protected JAXBElement<String> dobleImpPais;
    @XmlElementRef(name = "Emp_Vinculada", namespace = "Response/Canales", type = JAXBElement.class)
    protected JAXBElement<Integer> empVinculada;
    @XmlElementRef(name = "Emp_Vinculada_Op2", namespace = "Response/Canales", type = JAXBElement.class)
    protected JAXBElement<Integer> empVinculadaOp2;
    @XmlElementRef(name = "Emp_Vinculada_Op3", namespace = "Response/Canales", type = JAXBElement.class)
    protected JAXBElement<Integer> empVinculadaOp3;
    @XmlElementRef(name = "Emp_Vinculada_Op4", namespace = "Response/Canales", type = JAXBElement.class)
    protected JAXBElement<Integer> empVinculadaOp4;
    @XmlElementRef(name = "Emp_Vinculada_Op5", namespace = "Response/Canales", type = JAXBElement.class)
    protected JAXBElement<Integer> empVinculadaOp5;
    @XmlElementRef(name = "Estado_Transferencia", namespace = "Response/Canales", type = JAXBElement.class)
    protected JAXBElement<String> estadoTransferencia;
    @XmlElementRef(name = "Fecha_Embarque", namespace = "Response/Canales", type = JAXBElement.class)
    protected JAXBElement<String> fechaEmbarque;
    @XmlElementRef(name = "Gastos", namespace = "Response/Canales", type = JAXBElement.class)
    protected JAXBElement<BigDecimal> gastos;
    @XmlElementRef(name = "Id_Alicuota", namespace = "Response/Canales", type = JAXBElement.class)
    protected JAXBElement<Short> idAlicuota;
    @XmlElementRef(name = "Id_CT", namespace = "Response/Canales", type = JAXBElement.class)
    protected JAXBElement<Long> idCT;
    @XmlElementRef(name = "Id_Concepto", namespace = "Response/Canales", type = JAXBElement.class)
    protected JAXBElement<Integer> idConcepto;
    @XmlElementRef(name = "Id_Cond_Venta", namespace = "Response/Canales", type = JAXBElement.class)
    protected JAXBElement<Long> idCondVenta;
    @XmlElementRef(name = "Imagenes", namespace = "Response/Canales", type = JAXBElement.class)
    protected JAXBElement<ArrayOfDetalleTrfImagen> imagenes;
    @XmlElementRef(name = "Importe_Transferencia", namespace = "Response/Canales", type = JAXBElement.class)
    protected JAXBElement<BigDecimal> importeTransferencia;
    @XmlElementRef(name = "Inst_Recibido", namespace = "Response/Canales", type = JAXBElement.class)
    protected JAXBElement<String> instRecibido;
    @XmlElementRef(name = "Inst_Vendido", namespace = "Response/Canales", type = JAXBElement.class)
    protected JAXBElement<String> instVendido;
    @XmlElementRef(name = "Moneda", namespace = "Response/Canales", type = JAXBElement.class)
    protected JAXBElement<String> moneda;
    @XmlElementRef(name = "Monto_Ret_Ganacias", namespace = "Response/Canales", type = JAXBElement.class)
    protected JAXBElement<BigDecimal> montoRetGanacias;
    @XmlElementRef(name = "Monto_Ret_Iva", namespace = "Response/Canales", type = JAXBElement.class)
    protected JAXBElement<BigDecimal> montoRetIva;
    @XmlElementRef(name = "No_Corresp_Int_Finan", namespace = "Response/Canales", type = JAXBElement.class)
    protected JAXBElement<Integer> noCorrespIntFinan;
    @XmlElementRef(name = "No_Retencion_Art14", namespace = "Response/Canales", type = JAXBElement.class)
    protected JAXBElement<String> noRetencionArt14;
    @XmlElementRef(name = "No_Retencion_Articulo", namespace = "Response/Canales", type = JAXBElement.class)
    protected JAXBElement<String> noRetencionArticulo;
    @XmlElementRef(name = "No_Retencion_Motivo", namespace = "Response/Canales", type = JAXBElement.class)
    protected JAXBElement<String> noRetencionMotivo;
    @XmlElementRef(name = "No_Retencion_Pais", namespace = "Response/Canales", type = JAXBElement.class)
    protected JAXBElement<String> noRetencionPais;
    @XmlElementRef(name = "Nro_Documento_Cliente", namespace = "Response/Canales", type = JAXBElement.class)
    protected JAXBElement<String> nroDocumentoCliente;
    @XmlElementRef(name = "Nro_Solicitud", namespace = "Response/Canales", type = JAXBElement.class)
    protected JAXBElement<String> nroSolicitud;
    @XmlElementRef(name = "Nro_Transferencia_Rel", namespace = "Response/Canales", type = JAXBElement.class)
    protected JAXBElement<Long> nroTransferenciaRel;
    @XmlElementRef(name = "Opcion_Int_Finan", namespace = "Response/Canales", type = JAXBElement.class)
    protected JAXBElement<Integer> opcionIntFinan;
    @XmlElementRef(name = "Opcion_No_Iva", namespace = "Response/Canales", type = JAXBElement.class)
    protected JAXBElement<Integer> opcionNoIva;
    @XmlElementRef(name = "Opcion_No_Retencion", namespace = "Response/Canales", type = JAXBElement.class)
    protected JAXBElement<Integer> opcionNoRetencion;
    @XmlElementRef(name = "Otros_Int_Finan", namespace = "Response/Canales", type = JAXBElement.class)
    protected JAXBElement<String> otrosIntFinan;
    @XmlElementRef(name = "Otros_No_Iva", namespace = "Response/Canales", type = JAXBElement.class)
    protected JAXBElement<String> otrosNoIva;
    @XmlElementRef(name = "Pos_Arancelaria", namespace = "Response/Canales", type = JAXBElement.class)
    protected JAXBElement<String> posArancelaria;
    @XmlElementRef(name = "Rechazos", namespace = "Response/Canales", type = JAXBElement.class)
    protected JAXBElement<ArrayOfRechazo> rechazos;
    @XmlElementRef(name = "Referencia_Cliente", namespace = "Response/Canales", type = JAXBElement.class)
    protected JAXBElement<String> referenciaCliente;
    @XmlElementRef(name = "Registro_Inpi", namespace = "Response/Canales", type = JAXBElement.class)
    protected JAXBElement<Integer> registroInpi;
    @XmlElementRef(name = "Retiene_Ganancias", namespace = "Response/Canales", type = JAXBElement.class)
    protected JAXBElement<Integer> retieneGanancias;
    @XmlElementRef(name = "Retiene_Iva", namespace = "Response/Canales", type = JAXBElement.class)
    protected JAXBElement<Integer> retieneIva;
    @XmlElementRef(name = "Texto_Dj4834", namespace = "Response/Canales", type = JAXBElement.class)
    protected JAXBElement<String> textoDj4834;
    @XmlElementRef(name = "Tipo_Documento_Cliente", namespace = "Response/Canales", type = JAXBElement.class)
    protected JAXBElement<String> tipoDocumentoCliente;
    @XmlElementRef(name = "Tipo_Transferencia", namespace = "Response/Canales", type = JAXBElement.class)
    protected JAXBElement<Short> tipoTransferencia;
    @XmlElementRef(name = "Trf_Vigente", namespace = "Response/Canales", type = JAXBElement.class)
    protected JAXBElement<String> trfVigente;
    @XmlElementRef(name = "Vinculo", namespace = "Response/Canales", type = JAXBElement.class)
    protected JAXBElement<String> vinculo;

    /**
     * Gets the value of the aceptaDdjj property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public JAXBElement<Integer> getAceptaDdjj() {
        return aceptaDdjj;
    }

    /**
     * Sets the value of the aceptaDdjj property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public void setAceptaDdjj(JAXBElement<Integer> value) {
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
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public JAXBElement<Integer> getAplicaIntFinan() {
        return aplicaIntFinan;
    }

    /**
     * Sets the value of the aplicaIntFinan property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public void setAplicaIntFinan(JAXBElement<Integer> value) {
        this.aplicaIntFinan = value;
    }

    /**
     * Gets the value of the bancoBeneficiario property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getBancoBeneficiario() {
        return bancoBeneficiario;
    }

    /**
     * Sets the value of the bancoBeneficiario property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setBancoBeneficiario(JAXBElement<String> value) {
        this.bancoBeneficiario = value;
    }

    /**
     * Gets the value of the bancoIntermediario property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getBancoIntermediario() {
        return bancoIntermediario;
    }

    /**
     * Sets the value of the bancoIntermediario property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setBancoIntermediario(JAXBElement<String> value) {
        this.bancoIntermediario = value;
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
     * Gets the value of the beneficiario property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getBeneficiario() {
        return beneficiario;
    }

    /**
     * Sets the value of the beneficiario property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setBeneficiario(JAXBElement<String> value) {
        this.beneficiario = value;
    }

    /**
     * Gets the value of the beneficiarioDomicilio property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getBeneficiarioDomicilio() {
        return beneficiarioDomicilio;
    }

    /**
     * Sets the value of the beneficiarioDomicilio property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setBeneficiarioDomicilio(JAXBElement<String> value) {
        this.beneficiarioDomicilio = value;
    }

    /**
     * Gets the value of the beneficiarioPais property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getBeneficiarioPais() {
        return beneficiarioPais;
    }

    /**
     * Sets the value of the beneficiarioPais property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setBeneficiarioPais(JAXBElement<String> value) {
        this.beneficiarioPais = value;
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
     * Gets the value of the codigoConcepto property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCodigoConcepto() {
        return codigoConcepto;
    }

    /**
     * Sets the value of the codigoConcepto property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCodigoConcepto(JAXBElement<String> value) {
        this.codigoConcepto = value;
    }

    /**
     * Gets the value of the conDj4834 property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public JAXBElement<Integer> getConDj4834() {
        return conDj4834;
    }

    /**
     * Sets the value of the conDj4834 property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public void setConDj4834(JAXBElement<Integer> value) {
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
     * Gets the value of the ctaDebito property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCtaDebito() {
        return ctaDebito;
    }

    /**
     * Sets the value of the ctaDebito property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCtaDebito(JAXBElement<String> value) {
        this.ctaDebito = value;
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
     * Gets the value of the declaracionAdicional property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public JAXBElement<Integer> getDeclaracionAdicional() {
        return declaracionAdicional;
    }

    /**
     * Sets the value of the declaracionAdicional property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public void setDeclaracionAdicional(JAXBElement<Integer> value) {
        this.declaracionAdicional = value;
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
     * Gets the value of the empVinculada property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public JAXBElement<Integer> getEmpVinculada() {
        return empVinculada;
    }

    /**
     * Sets the value of the empVinculada property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public void setEmpVinculada(JAXBElement<Integer> value) {
        this.empVinculada = value;
    }

    /**
     * Gets the value of the empVinculadaOp2 property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public JAXBElement<Integer> getEmpVinculadaOp2() {
        return empVinculadaOp2;
    }

    /**
     * Sets the value of the empVinculadaOp2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public void setEmpVinculadaOp2(JAXBElement<Integer> value) {
        this.empVinculadaOp2 = value;
    }

    /**
     * Gets the value of the empVinculadaOp3 property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public JAXBElement<Integer> getEmpVinculadaOp3() {
        return empVinculadaOp3;
    }

    /**
     * Sets the value of the empVinculadaOp3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public void setEmpVinculadaOp3(JAXBElement<Integer> value) {
        this.empVinculadaOp3 = value;
    }

    /**
     * Gets the value of the empVinculadaOp4 property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public JAXBElement<Integer> getEmpVinculadaOp4() {
        return empVinculadaOp4;
    }

    /**
     * Sets the value of the empVinculadaOp4 property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public void setEmpVinculadaOp4(JAXBElement<Integer> value) {
        this.empVinculadaOp4 = value;
    }

    /**
     * Gets the value of the empVinculadaOp5 property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public JAXBElement<Integer> getEmpVinculadaOp5() {
        return empVinculadaOp5;
    }

    /**
     * Sets the value of the empVinculadaOp5 property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public void setEmpVinculadaOp5(JAXBElement<Integer> value) {
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
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public JAXBElement<BigDecimal> getGastos() {
        return gastos;
    }

    /**
     * Sets the value of the gastos property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public void setGastos(JAXBElement<BigDecimal> value) {
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
     * Gets the value of the idCT property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Long }{@code >}
     *     
     */
    public JAXBElement<Long> getIdCT() {
        return idCT;
    }

    /**
     * Sets the value of the idCT property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Long }{@code >}
     *     
     */
    public void setIdCT(JAXBElement<Long> value) {
        this.idCT = value;
    }

    /**
     * Gets the value of the idConcepto property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public JAXBElement<Integer> getIdConcepto() {
        return idConcepto;
    }

    /**
     * Sets the value of the idConcepto property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public void setIdConcepto(JAXBElement<Integer> value) {
        this.idConcepto = value;
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
     * Gets the value of the imagenes property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfDetalleTrfImagen }{@code >}
     *     
     */
    public JAXBElement<ArrayOfDetalleTrfImagen> getImagenes() {
        return imagenes;
    }

    /**
     * Sets the value of the imagenes property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfDetalleTrfImagen }{@code >}
     *     
     */
    public void setImagenes(JAXBElement<ArrayOfDetalleTrfImagen> value) {
        this.imagenes = value;
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
     * Gets the value of the noCorrespIntFinan property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public JAXBElement<Integer> getNoCorrespIntFinan() {
        return noCorrespIntFinan;
    }

    /**
     * Sets the value of the noCorrespIntFinan property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public void setNoCorrespIntFinan(JAXBElement<Integer> value) {
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
     * Gets the value of the nroSolicitud property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNroSolicitud() {
        return nroSolicitud;
    }

    /**
     * Sets the value of the nroSolicitud property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNroSolicitud(JAXBElement<String> value) {
        this.nroSolicitud = value;
    }

    /**
     * Gets the value of the nroTransferenciaRel property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Long }{@code >}
     *     
     */
    public JAXBElement<Long> getNroTransferenciaRel() {
        return nroTransferenciaRel;
    }

    /**
     * Sets the value of the nroTransferenciaRel property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Long }{@code >}
     *     
     */
    public void setNroTransferenciaRel(JAXBElement<Long> value) {
        this.nroTransferenciaRel = value;
    }

    /**
     * Gets the value of the opcionIntFinan property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public JAXBElement<Integer> getOpcionIntFinan() {
        return opcionIntFinan;
    }

    /**
     * Sets the value of the opcionIntFinan property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public void setOpcionIntFinan(JAXBElement<Integer> value) {
        this.opcionIntFinan = value;
    }

    /**
     * Gets the value of the opcionNoIva property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public JAXBElement<Integer> getOpcionNoIva() {
        return opcionNoIva;
    }

    /**
     * Sets the value of the opcionNoIva property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public void setOpcionNoIva(JAXBElement<Integer> value) {
        this.opcionNoIva = value;
    }

    /**
     * Gets the value of the opcionNoRetencion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public JAXBElement<Integer> getOpcionNoRetencion() {
        return opcionNoRetencion;
    }

    /**
     * Sets the value of the opcionNoRetencion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public void setOpcionNoRetencion(JAXBElement<Integer> value) {
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
     * Gets the value of the rechazos property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfRechazo }{@code >}
     *     
     */
    public JAXBElement<ArrayOfRechazo> getRechazos() {
        return rechazos;
    }

    /**
     * Sets the value of the rechazos property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfRechazo }{@code >}
     *     
     */
    public void setRechazos(JAXBElement<ArrayOfRechazo> value) {
        this.rechazos = value;
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
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public JAXBElement<Integer> getRegistroInpi() {
        return registroInpi;
    }

    /**
     * Sets the value of the registroInpi property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public void setRegistroInpi(JAXBElement<Integer> value) {
        this.registroInpi = value;
    }

    /**
     * Gets the value of the retieneGanancias property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public JAXBElement<Integer> getRetieneGanancias() {
        return retieneGanancias;
    }

    /**
     * Sets the value of the retieneGanancias property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public void setRetieneGanancias(JAXBElement<Integer> value) {
        this.retieneGanancias = value;
    }

    /**
     * Gets the value of the retieneIva property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public JAXBElement<Integer> getRetieneIva() {
        return retieneIva;
    }

    /**
     * Sets the value of the retieneIva property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public void setRetieneIva(JAXBElement<Integer> value) {
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

    /**
     * Gets the value of the trfVigente property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTrfVigente() {
        return trfVigente;
    }

    /**
     * Sets the value of the trfVigente property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTrfVigente(JAXBElement<String> value) {
        this.trfVigente = value;
    }

    /**
     * Gets the value of the vinculo property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getVinculo() {
        return vinculo;
    }

    /**
     * Sets the value of the vinculo property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setVinculo(JAXBElement<String> value) {
        this.vinculo = value;
    }

}
