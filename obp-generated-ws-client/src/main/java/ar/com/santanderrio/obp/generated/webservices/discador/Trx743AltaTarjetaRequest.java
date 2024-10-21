
package ar.com.santanderrio.obp.generated.webservices.discador;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;
import ar.com.santanderrio.obp.generated.webservices.discador.contracts.request.common.AutorizacionRequest;
import ar.com.santanderrio.obp.generated.webservices.discador.contracts.request.common.TDatosClienteRequest;


/**
 * <p>Java class for Trx743AltaTarjetaRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Trx743AltaTarjetaRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}RequestBase">
 *       &lt;sequence>
 *         &lt;element name="ApellidoYNombre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ApellidoYNombreEmbozado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AplicacionCuentaRelacionada" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Area" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Autorizacion" type="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Request}AutorizacionRequest" minOccurs="0"/>
 *         &lt;element name="AutorizacionRequerida" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Bonificacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CanalVenta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CantidadTarjetas" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Cargo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CargoMb" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CargoRenovacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CargoRenovacionCuotas" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CentroDeCosto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Cliente" type="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Request}TDatosClienteRequest" minOccurs="0"/>
 *         &lt;element name="CodigoCartera" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CodigoCategoria" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CodigoCompra" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CodigoDeComercio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CodigoProducto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CodigoSucursal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CompaniaSeguro" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ControlLimitePorTarjeta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CuartaLinea" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CuentaDePagoFirmante" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CuentaDePagoNumero" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CuentaDePagoTipoDeCuenta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CuentaPagoAplicacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CuentaPagoSucursal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CuotasBonificacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DuracionTarjeta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Empresa" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FirmanteCuentaRelacionada" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FormaPago" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Funcion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="GrupoAfinidad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ImporteLimiteCompra" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ImporteLimiteCredito" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Marca" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ModeloLiquidacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NroCuentaDeb" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NroCuentaTarjeta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NumeroDeCuentaCuentaRelacionada" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Nup" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Nup2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OrigenOperacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Planta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PlazoPagoMinimo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PorcentajeLimiteAdelanto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PorcentajeLimiteCompra" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PorcentajeLimiteCuotas" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PorcentajeLimiteTarjeta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Sector" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SecuenciaDomicilio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SecuenciaTelefono" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SucursalCuentaRelacionada" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SucursalDeb" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SucursalUsuario" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TipoCuentaCuentaRelacionada" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TipoCuentaDeb" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TipoDeAutorizado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TipoDeTarjeta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TipoPaquete" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TipoPersona" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="UsuarioOperacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="UsuarioVenta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Trx743AltaTarjetaRequest", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx743", propOrder = {
    "apellidoYNombre",
    "apellidoYNombreEmbozado",
    "aplicacionCuentaRelacionada",
    "area",
    "autorizacion",
    "autorizacionRequerida",
    "bonificacion",
    "canalVenta",
    "cantidadTarjetas",
    "cargo",
    "cargoMb",
    "cargoRenovacion",
    "cargoRenovacionCuotas",
    "centroDeCosto",
    "cliente",
    "codigoCartera",
    "codigoCategoria",
    "codigoCompra",
    "codigoDeComercio",
    "codigoProducto",
    "codigoSucursal",
    "companiaSeguro",
    "controlLimitePorTarjeta",
    "cuartaLinea",
    "cuentaDePagoFirmante",
    "cuentaDePagoNumero",
    "cuentaDePagoTipoDeCuenta",
    "cuentaPagoAplicacion",
    "cuentaPagoSucursal",
    "cuotasBonificacion",
    "duracionTarjeta",
    "empresa",
    "firmanteCuentaRelacionada",
    "formaPago",
    "funcion",
    "grupoAfinidad",
    "importeLimiteCompra",
    "importeLimiteCredito",
    "marca",
    "modeloLiquidacion",
    "nroCuentaDeb",
    "nroCuentaTarjeta",
    "numeroDeCuentaCuentaRelacionada",
    "nup",
    "nup2",
    "origenOperacion",
    "planta",
    "plazoPagoMinimo",
    "porcentajeLimiteAdelanto",
    "porcentajeLimiteCompra",
    "porcentajeLimiteCuotas",
    "porcentajeLimiteTarjeta",
    "sector",
    "secuenciaDomicilio",
    "secuenciaTelefono",
    "sucursalCuentaRelacionada",
    "sucursalDeb",
    "sucursalUsuario",
    "tipoCuentaCuentaRelacionada",
    "tipoCuentaDeb",
    "tipoDeAutorizado",
    "tipoDeTarjeta",
    "tipoPaquete",
    "tipoPersona",
    "usuarioOperacion",
    "usuarioVenta"
})
public class Trx743AltaTarjetaRequest
    extends RequestBase
{

    @XmlElementRef(name = "ApellidoYNombre", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx743", type = JAXBElement.class)
    protected JAXBElement<String> apellidoYNombre;
    @XmlElementRef(name = "ApellidoYNombreEmbozado", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx743", type = JAXBElement.class)
    protected JAXBElement<String> apellidoYNombreEmbozado;
    @XmlElementRef(name = "AplicacionCuentaRelacionada", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx743", type = JAXBElement.class)
    protected JAXBElement<String> aplicacionCuentaRelacionada;
    @XmlElementRef(name = "Area", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx743", type = JAXBElement.class)
    protected JAXBElement<String> area;
    @XmlElementRef(name = "Autorizacion", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx743", type = JAXBElement.class)
    protected JAXBElement<AutorizacionRequest> autorizacion;
    @XmlElementRef(name = "AutorizacionRequerida", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx743", type = JAXBElement.class)
    protected JAXBElement<String> autorizacionRequerida;
    @XmlElementRef(name = "Bonificacion", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx743", type = JAXBElement.class)
    protected JAXBElement<String> bonificacion;
    @XmlElementRef(name = "CanalVenta", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx743", type = JAXBElement.class)
    protected JAXBElement<String> canalVenta;
    @XmlElementRef(name = "CantidadTarjetas", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx743", type = JAXBElement.class)
    protected JAXBElement<String> cantidadTarjetas;
    @XmlElementRef(name = "Cargo", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx743", type = JAXBElement.class)
    protected JAXBElement<String> cargo;
    @XmlElementRef(name = "CargoMb", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx743", type = JAXBElement.class)
    protected JAXBElement<String> cargoMb;
    @XmlElementRef(name = "CargoRenovacion", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx743", type = JAXBElement.class)
    protected JAXBElement<String> cargoRenovacion;
    @XmlElementRef(name = "CargoRenovacionCuotas", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx743", type = JAXBElement.class)
    protected JAXBElement<String> cargoRenovacionCuotas;
    @XmlElementRef(name = "CentroDeCosto", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx743", type = JAXBElement.class)
    protected JAXBElement<String> centroDeCosto;
    @XmlElementRef(name = "Cliente", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx743", type = JAXBElement.class)
    protected JAXBElement<TDatosClienteRequest> cliente;
    @XmlElementRef(name = "CodigoCartera", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx743", type = JAXBElement.class)
    protected JAXBElement<String> codigoCartera;
    @XmlElementRef(name = "CodigoCategoria", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx743", type = JAXBElement.class)
    protected JAXBElement<String> codigoCategoria;
    @XmlElementRef(name = "CodigoCompra", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx743", type = JAXBElement.class)
    protected JAXBElement<String> codigoCompra;
    @XmlElementRef(name = "CodigoDeComercio", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx743", type = JAXBElement.class)
    protected JAXBElement<String> codigoDeComercio;
    @XmlElementRef(name = "CodigoProducto", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx743", type = JAXBElement.class)
    protected JAXBElement<String> codigoProducto;
    @XmlElementRef(name = "CodigoSucursal", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx743", type = JAXBElement.class)
    protected JAXBElement<String> codigoSucursal;
    @XmlElementRef(name = "CompaniaSeguro", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx743", type = JAXBElement.class)
    protected JAXBElement<String> companiaSeguro;
    @XmlElementRef(name = "ControlLimitePorTarjeta", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx743", type = JAXBElement.class)
    protected JAXBElement<String> controlLimitePorTarjeta;
    @XmlElementRef(name = "CuartaLinea", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx743", type = JAXBElement.class)
    protected JAXBElement<String> cuartaLinea;
    @XmlElementRef(name = "CuentaDePagoFirmante", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx743", type = JAXBElement.class)
    protected JAXBElement<String> cuentaDePagoFirmante;
    @XmlElementRef(name = "CuentaDePagoNumero", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx743", type = JAXBElement.class)
    protected JAXBElement<String> cuentaDePagoNumero;
    @XmlElementRef(name = "CuentaDePagoTipoDeCuenta", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx743", type = JAXBElement.class)
    protected JAXBElement<String> cuentaDePagoTipoDeCuenta;
    @XmlElementRef(name = "CuentaPagoAplicacion", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx743", type = JAXBElement.class)
    protected JAXBElement<String> cuentaPagoAplicacion;
    @XmlElementRef(name = "CuentaPagoSucursal", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx743", type = JAXBElement.class)
    protected JAXBElement<String> cuentaPagoSucursal;
    @XmlElementRef(name = "CuotasBonificacion", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx743", type = JAXBElement.class)
    protected JAXBElement<String> cuotasBonificacion;
    @XmlElementRef(name = "DuracionTarjeta", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx743", type = JAXBElement.class)
    protected JAXBElement<String> duracionTarjeta;
    @XmlElementRef(name = "Empresa", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx743", type = JAXBElement.class)
    protected JAXBElement<String> empresa;
    @XmlElementRef(name = "FirmanteCuentaRelacionada", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx743", type = JAXBElement.class)
    protected JAXBElement<String> firmanteCuentaRelacionada;
    @XmlElementRef(name = "FormaPago", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx743", type = JAXBElement.class)
    protected JAXBElement<String> formaPago;
    @XmlElementRef(name = "Funcion", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx743", type = JAXBElement.class)
    protected JAXBElement<String> funcion;
    @XmlElementRef(name = "GrupoAfinidad", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx743", type = JAXBElement.class)
    protected JAXBElement<String> grupoAfinidad;
    @XmlElementRef(name = "ImporteLimiteCompra", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx743", type = JAXBElement.class)
    protected JAXBElement<String> importeLimiteCompra;
    @XmlElementRef(name = "ImporteLimiteCredito", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx743", type = JAXBElement.class)
    protected JAXBElement<String> importeLimiteCredito;
    @XmlElementRef(name = "Marca", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx743", type = JAXBElement.class)
    protected JAXBElement<String> marca;
    @XmlElementRef(name = "ModeloLiquidacion", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx743", type = JAXBElement.class)
    protected JAXBElement<String> modeloLiquidacion;
    @XmlElementRef(name = "NroCuentaDeb", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx743", type = JAXBElement.class)
    protected JAXBElement<String> nroCuentaDeb;
    @XmlElementRef(name = "NroCuentaTarjeta", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx743", type = JAXBElement.class)
    protected JAXBElement<String> nroCuentaTarjeta;
    @XmlElementRef(name = "NumeroDeCuentaCuentaRelacionada", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx743", type = JAXBElement.class)
    protected JAXBElement<String> numeroDeCuentaCuentaRelacionada;
    @XmlElementRef(name = "Nup", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx743", type = JAXBElement.class)
    protected JAXBElement<String> nup;
    @XmlElementRef(name = "Nup2", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx743", type = JAXBElement.class)
    protected JAXBElement<String> nup2;
    @XmlElementRef(name = "OrigenOperacion", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx743", type = JAXBElement.class)
    protected JAXBElement<String> origenOperacion;
    @XmlElementRef(name = "Planta", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx743", type = JAXBElement.class)
    protected JAXBElement<String> planta;
    @XmlElementRef(name = "PlazoPagoMinimo", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx743", type = JAXBElement.class)
    protected JAXBElement<String> plazoPagoMinimo;
    @XmlElementRef(name = "PorcentajeLimiteAdelanto", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx743", type = JAXBElement.class)
    protected JAXBElement<String> porcentajeLimiteAdelanto;
    @XmlElementRef(name = "PorcentajeLimiteCompra", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx743", type = JAXBElement.class)
    protected JAXBElement<String> porcentajeLimiteCompra;
    @XmlElementRef(name = "PorcentajeLimiteCuotas", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx743", type = JAXBElement.class)
    protected JAXBElement<String> porcentajeLimiteCuotas;
    @XmlElementRef(name = "PorcentajeLimiteTarjeta", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx743", type = JAXBElement.class)
    protected JAXBElement<String> porcentajeLimiteTarjeta;
    @XmlElementRef(name = "Sector", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx743", type = JAXBElement.class)
    protected JAXBElement<String> sector;
    @XmlElementRef(name = "SecuenciaDomicilio", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx743", type = JAXBElement.class)
    protected JAXBElement<String> secuenciaDomicilio;
    @XmlElementRef(name = "SecuenciaTelefono", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx743", type = JAXBElement.class)
    protected JAXBElement<String> secuenciaTelefono;
    @XmlElementRef(name = "SucursalCuentaRelacionada", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx743", type = JAXBElement.class)
    protected JAXBElement<String> sucursalCuentaRelacionada;
    @XmlElementRef(name = "SucursalDeb", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx743", type = JAXBElement.class)
    protected JAXBElement<String> sucursalDeb;
    @XmlElementRef(name = "SucursalUsuario", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx743", type = JAXBElement.class)
    protected JAXBElement<String> sucursalUsuario;
    @XmlElementRef(name = "TipoCuentaCuentaRelacionada", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx743", type = JAXBElement.class)
    protected JAXBElement<String> tipoCuentaCuentaRelacionada;
    @XmlElementRef(name = "TipoCuentaDeb", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx743", type = JAXBElement.class)
    protected JAXBElement<String> tipoCuentaDeb;
    @XmlElementRef(name = "TipoDeAutorizado", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx743", type = JAXBElement.class)
    protected JAXBElement<String> tipoDeAutorizado;
    @XmlElementRef(name = "TipoDeTarjeta", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx743", type = JAXBElement.class)
    protected JAXBElement<String> tipoDeTarjeta;
    @XmlElementRef(name = "TipoPaquete", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx743", type = JAXBElement.class)
    protected JAXBElement<String> tipoPaquete;
    @XmlElementRef(name = "TipoPersona", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx743", type = JAXBElement.class)
    protected JAXBElement<String> tipoPersona;
    @XmlElementRef(name = "UsuarioOperacion", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx743", type = JAXBElement.class)
    protected JAXBElement<String> usuarioOperacion;
    @XmlElementRef(name = "UsuarioVenta", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx743", type = JAXBElement.class)
    protected JAXBElement<String> usuarioVenta;

    /**
     * Gets the value of the apellidoYNombre property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getApellidoYNombre() {
        return apellidoYNombre;
    }

    /**
     * Sets the value of the apellidoYNombre property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setApellidoYNombre(JAXBElement<String> value) {
        this.apellidoYNombre = value;
    }

    /**
     * Gets the value of the apellidoYNombreEmbozado property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getApellidoYNombreEmbozado() {
        return apellidoYNombreEmbozado;
    }

    /**
     * Sets the value of the apellidoYNombreEmbozado property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setApellidoYNombreEmbozado(JAXBElement<String> value) {
        this.apellidoYNombreEmbozado = value;
    }

    /**
     * Gets the value of the aplicacionCuentaRelacionada property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getAplicacionCuentaRelacionada() {
        return aplicacionCuentaRelacionada;
    }

    /**
     * Sets the value of the aplicacionCuentaRelacionada property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setAplicacionCuentaRelacionada(JAXBElement<String> value) {
        this.aplicacionCuentaRelacionada = value;
    }

    /**
     * Gets the value of the area property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getArea() {
        return area;
    }

    /**
     * Sets the value of the area property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setArea(JAXBElement<String> value) {
        this.area = value;
    }

    /**
     * Gets the value of the autorizacion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link AutorizacionRequest }{@code >}
     *     
     */
    public JAXBElement<AutorizacionRequest> getAutorizacion() {
        return autorizacion;
    }

    /**
     * Sets the value of the autorizacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link AutorizacionRequest }{@code >}
     *     
     */
    public void setAutorizacion(JAXBElement<AutorizacionRequest> value) {
        this.autorizacion = value;
    }

    /**
     * Gets the value of the autorizacionRequerida property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getAutorizacionRequerida() {
        return autorizacionRequerida;
    }

    /**
     * Sets the value of the autorizacionRequerida property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setAutorizacionRequerida(JAXBElement<String> value) {
        this.autorizacionRequerida = value;
    }

    /**
     * Gets the value of the bonificacion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getBonificacion() {
        return bonificacion;
    }

    /**
     * Sets the value of the bonificacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setBonificacion(JAXBElement<String> value) {
        this.bonificacion = value;
    }

    /**
     * Gets the value of the canalVenta property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCanalVenta() {
        return canalVenta;
    }

    /**
     * Sets the value of the canalVenta property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCanalVenta(JAXBElement<String> value) {
        this.canalVenta = value;
    }

    /**
     * Gets the value of the cantidadTarjetas property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCantidadTarjetas() {
        return cantidadTarjetas;
    }

    /**
     * Sets the value of the cantidadTarjetas property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCantidadTarjetas(JAXBElement<String> value) {
        this.cantidadTarjetas = value;
    }

    /**
     * Gets the value of the cargo property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCargo() {
        return cargo;
    }

    /**
     * Sets the value of the cargo property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCargo(JAXBElement<String> value) {
        this.cargo = value;
    }

    /**
     * Gets the value of the cargoMb property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCargoMb() {
        return cargoMb;
    }

    /**
     * Sets the value of the cargoMb property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCargoMb(JAXBElement<String> value) {
        this.cargoMb = value;
    }

    /**
     * Gets the value of the cargoRenovacion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCargoRenovacion() {
        return cargoRenovacion;
    }

    /**
     * Sets the value of the cargoRenovacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCargoRenovacion(JAXBElement<String> value) {
        this.cargoRenovacion = value;
    }

    /**
     * Gets the value of the cargoRenovacionCuotas property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCargoRenovacionCuotas() {
        return cargoRenovacionCuotas;
    }

    /**
     * Sets the value of the cargoRenovacionCuotas property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCargoRenovacionCuotas(JAXBElement<String> value) {
        this.cargoRenovacionCuotas = value;
    }

    /**
     * Gets the value of the centroDeCosto property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCentroDeCosto() {
        return centroDeCosto;
    }

    /**
     * Sets the value of the centroDeCosto property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCentroDeCosto(JAXBElement<String> value) {
        this.centroDeCosto = value;
    }

    /**
     * Gets the value of the cliente property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link TDatosClienteRequest }{@code >}
     *     
     */
    public JAXBElement<TDatosClienteRequest> getCliente() {
        return cliente;
    }

    /**
     * Sets the value of the cliente property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link TDatosClienteRequest }{@code >}
     *     
     */
    public void setCliente(JAXBElement<TDatosClienteRequest> value) {
        this.cliente = value;
    }

    /**
     * Gets the value of the codigoCartera property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCodigoCartera() {
        return codigoCartera;
    }

    /**
     * Sets the value of the codigoCartera property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCodigoCartera(JAXBElement<String> value) {
        this.codigoCartera = value;
    }

    /**
     * Gets the value of the codigoCategoria property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCodigoCategoria() {
        return codigoCategoria;
    }

    /**
     * Sets the value of the codigoCategoria property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCodigoCategoria(JAXBElement<String> value) {
        this.codigoCategoria = value;
    }

    /**
     * Gets the value of the codigoCompra property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCodigoCompra() {
        return codigoCompra;
    }

    /**
     * Sets the value of the codigoCompra property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCodigoCompra(JAXBElement<String> value) {
        this.codigoCompra = value;
    }

    /**
     * Gets the value of the codigoDeComercio property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCodigoDeComercio() {
        return codigoDeComercio;
    }

    /**
     * Sets the value of the codigoDeComercio property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCodigoDeComercio(JAXBElement<String> value) {
        this.codigoDeComercio = value;
    }

    /**
     * Gets the value of the codigoProducto property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCodigoProducto() {
        return codigoProducto;
    }

    /**
     * Sets the value of the codigoProducto property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCodigoProducto(JAXBElement<String> value) {
        this.codigoProducto = value;
    }

    /**
     * Gets the value of the codigoSucursal property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCodigoSucursal() {
        return codigoSucursal;
    }

    /**
     * Sets the value of the codigoSucursal property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCodigoSucursal(JAXBElement<String> value) {
        this.codigoSucursal = value;
    }

    /**
     * Gets the value of the companiaSeguro property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCompaniaSeguro() {
        return companiaSeguro;
    }

    /**
     * Sets the value of the companiaSeguro property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCompaniaSeguro(JAXBElement<String> value) {
        this.companiaSeguro = value;
    }

    /**
     * Gets the value of the controlLimitePorTarjeta property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getControlLimitePorTarjeta() {
        return controlLimitePorTarjeta;
    }

    /**
     * Sets the value of the controlLimitePorTarjeta property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setControlLimitePorTarjeta(JAXBElement<String> value) {
        this.controlLimitePorTarjeta = value;
    }

    /**
     * Gets the value of the cuartaLinea property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCuartaLinea() {
        return cuartaLinea;
    }

    /**
     * Sets the value of the cuartaLinea property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCuartaLinea(JAXBElement<String> value) {
        this.cuartaLinea = value;
    }

    /**
     * Gets the value of the cuentaDePagoFirmante property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCuentaDePagoFirmante() {
        return cuentaDePagoFirmante;
    }

    /**
     * Sets the value of the cuentaDePagoFirmante property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCuentaDePagoFirmante(JAXBElement<String> value) {
        this.cuentaDePagoFirmante = value;
    }

    /**
     * Gets the value of the cuentaDePagoNumero property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCuentaDePagoNumero() {
        return cuentaDePagoNumero;
    }

    /**
     * Sets the value of the cuentaDePagoNumero property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCuentaDePagoNumero(JAXBElement<String> value) {
        this.cuentaDePagoNumero = value;
    }

    /**
     * Gets the value of the cuentaDePagoTipoDeCuenta property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCuentaDePagoTipoDeCuenta() {
        return cuentaDePagoTipoDeCuenta;
    }

    /**
     * Sets the value of the cuentaDePagoTipoDeCuenta property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCuentaDePagoTipoDeCuenta(JAXBElement<String> value) {
        this.cuentaDePagoTipoDeCuenta = value;
    }

    /**
     * Gets the value of the cuentaPagoAplicacion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCuentaPagoAplicacion() {
        return cuentaPagoAplicacion;
    }

    /**
     * Sets the value of the cuentaPagoAplicacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCuentaPagoAplicacion(JAXBElement<String> value) {
        this.cuentaPagoAplicacion = value;
    }

    /**
     * Gets the value of the cuentaPagoSucursal property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCuentaPagoSucursal() {
        return cuentaPagoSucursal;
    }

    /**
     * Sets the value of the cuentaPagoSucursal property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCuentaPagoSucursal(JAXBElement<String> value) {
        this.cuentaPagoSucursal = value;
    }

    /**
     * Gets the value of the cuotasBonificacion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCuotasBonificacion() {
        return cuotasBonificacion;
    }

    /**
     * Sets the value of the cuotasBonificacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCuotasBonificacion(JAXBElement<String> value) {
        this.cuotasBonificacion = value;
    }

    /**
     * Gets the value of the duracionTarjeta property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDuracionTarjeta() {
        return duracionTarjeta;
    }

    /**
     * Sets the value of the duracionTarjeta property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDuracionTarjeta(JAXBElement<String> value) {
        this.duracionTarjeta = value;
    }

    /**
     * Gets the value of the empresa property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getEmpresa() {
        return empresa;
    }

    /**
     * Sets the value of the empresa property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setEmpresa(JAXBElement<String> value) {
        this.empresa = value;
    }

    /**
     * Gets the value of the firmanteCuentaRelacionada property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFirmanteCuentaRelacionada() {
        return firmanteCuentaRelacionada;
    }

    /**
     * Sets the value of the firmanteCuentaRelacionada property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFirmanteCuentaRelacionada(JAXBElement<String> value) {
        this.firmanteCuentaRelacionada = value;
    }

    /**
     * Gets the value of the formaPago property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFormaPago() {
        return formaPago;
    }

    /**
     * Sets the value of the formaPago property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFormaPago(JAXBElement<String> value) {
        this.formaPago = value;
    }

    /**
     * Gets the value of the funcion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFuncion() {
        return funcion;
    }

    /**
     * Sets the value of the funcion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFuncion(JAXBElement<String> value) {
        this.funcion = value;
    }

    /**
     * Gets the value of the grupoAfinidad property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getGrupoAfinidad() {
        return grupoAfinidad;
    }

    /**
     * Sets the value of the grupoAfinidad property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setGrupoAfinidad(JAXBElement<String> value) {
        this.grupoAfinidad = value;
    }

    /**
     * Gets the value of the importeLimiteCompra property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getImporteLimiteCompra() {
        return importeLimiteCompra;
    }

    /**
     * Sets the value of the importeLimiteCompra property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setImporteLimiteCompra(JAXBElement<String> value) {
        this.importeLimiteCompra = value;
    }

    /**
     * Gets the value of the importeLimiteCredito property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getImporteLimiteCredito() {
        return importeLimiteCredito;
    }

    /**
     * Sets the value of the importeLimiteCredito property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setImporteLimiteCredito(JAXBElement<String> value) {
        this.importeLimiteCredito = value;
    }

    /**
     * Gets the value of the marca property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getMarca() {
        return marca;
    }

    /**
     * Sets the value of the marca property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setMarca(JAXBElement<String> value) {
        this.marca = value;
    }

    /**
     * Gets the value of the modeloLiquidacion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getModeloLiquidacion() {
        return modeloLiquidacion;
    }

    /**
     * Sets the value of the modeloLiquidacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setModeloLiquidacion(JAXBElement<String> value) {
        this.modeloLiquidacion = value;
    }

    /**
     * Gets the value of the nroCuentaDeb property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNroCuentaDeb() {
        return nroCuentaDeb;
    }

    /**
     * Sets the value of the nroCuentaDeb property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNroCuentaDeb(JAXBElement<String> value) {
        this.nroCuentaDeb = value;
    }

    /**
     * Gets the value of the nroCuentaTarjeta property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNroCuentaTarjeta() {
        return nroCuentaTarjeta;
    }

    /**
     * Sets the value of the nroCuentaTarjeta property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNroCuentaTarjeta(JAXBElement<String> value) {
        this.nroCuentaTarjeta = value;
    }

    /**
     * Gets the value of the numeroDeCuentaCuentaRelacionada property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNumeroDeCuentaCuentaRelacionada() {
        return numeroDeCuentaCuentaRelacionada;
    }

    /**
     * Sets the value of the numeroDeCuentaCuentaRelacionada property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNumeroDeCuentaCuentaRelacionada(JAXBElement<String> value) {
        this.numeroDeCuentaCuentaRelacionada = value;
    }

    /**
     * Gets the value of the nup property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNup() {
        return nup;
    }

    /**
     * Sets the value of the nup property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNup(JAXBElement<String> value) {
        this.nup = value;
    }

    /**
     * Gets the value of the nup2 property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNup2() {
        return nup2;
    }

    /**
     * Sets the value of the nup2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNup2(JAXBElement<String> value) {
        this.nup2 = value;
    }

    /**
     * Gets the value of the origenOperacion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOrigenOperacion() {
        return origenOperacion;
    }

    /**
     * Sets the value of the origenOperacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOrigenOperacion(JAXBElement<String> value) {
        this.origenOperacion = value;
    }

    /**
     * Gets the value of the planta property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPlanta() {
        return planta;
    }

    /**
     * Sets the value of the planta property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPlanta(JAXBElement<String> value) {
        this.planta = value;
    }

    /**
     * Gets the value of the plazoPagoMinimo property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPlazoPagoMinimo() {
        return plazoPagoMinimo;
    }

    /**
     * Sets the value of the plazoPagoMinimo property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPlazoPagoMinimo(JAXBElement<String> value) {
        this.plazoPagoMinimo = value;
    }

    /**
     * Gets the value of the porcentajeLimiteAdelanto property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPorcentajeLimiteAdelanto() {
        return porcentajeLimiteAdelanto;
    }

    /**
     * Sets the value of the porcentajeLimiteAdelanto property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPorcentajeLimiteAdelanto(JAXBElement<String> value) {
        this.porcentajeLimiteAdelanto = value;
    }

    /**
     * Gets the value of the porcentajeLimiteCompra property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPorcentajeLimiteCompra() {
        return porcentajeLimiteCompra;
    }

    /**
     * Sets the value of the porcentajeLimiteCompra property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPorcentajeLimiteCompra(JAXBElement<String> value) {
        this.porcentajeLimiteCompra = value;
    }

    /**
     * Gets the value of the porcentajeLimiteCuotas property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPorcentajeLimiteCuotas() {
        return porcentajeLimiteCuotas;
    }

    /**
     * Sets the value of the porcentajeLimiteCuotas property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPorcentajeLimiteCuotas(JAXBElement<String> value) {
        this.porcentajeLimiteCuotas = value;
    }

    /**
     * Gets the value of the porcentajeLimiteTarjeta property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPorcentajeLimiteTarjeta() {
        return porcentajeLimiteTarjeta;
    }

    /**
     * Sets the value of the porcentajeLimiteTarjeta property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPorcentajeLimiteTarjeta(JAXBElement<String> value) {
        this.porcentajeLimiteTarjeta = value;
    }

    /**
     * Gets the value of the sector property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSector() {
        return sector;
    }

    /**
     * Sets the value of the sector property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSector(JAXBElement<String> value) {
        this.sector = value;
    }

    /**
     * Gets the value of the secuenciaDomicilio property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSecuenciaDomicilio() {
        return secuenciaDomicilio;
    }

    /**
     * Sets the value of the secuenciaDomicilio property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSecuenciaDomicilio(JAXBElement<String> value) {
        this.secuenciaDomicilio = value;
    }

    /**
     * Gets the value of the secuenciaTelefono property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSecuenciaTelefono() {
        return secuenciaTelefono;
    }

    /**
     * Sets the value of the secuenciaTelefono property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSecuenciaTelefono(JAXBElement<String> value) {
        this.secuenciaTelefono = value;
    }

    /**
     * Gets the value of the sucursalCuentaRelacionada property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSucursalCuentaRelacionada() {
        return sucursalCuentaRelacionada;
    }

    /**
     * Sets the value of the sucursalCuentaRelacionada property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSucursalCuentaRelacionada(JAXBElement<String> value) {
        this.sucursalCuentaRelacionada = value;
    }

    /**
     * Gets the value of the sucursalDeb property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSucursalDeb() {
        return sucursalDeb;
    }

    /**
     * Sets the value of the sucursalDeb property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSucursalDeb(JAXBElement<String> value) {
        this.sucursalDeb = value;
    }

    /**
     * Gets the value of the sucursalUsuario property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSucursalUsuario() {
        return sucursalUsuario;
    }

    /**
     * Sets the value of the sucursalUsuario property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSucursalUsuario(JAXBElement<String> value) {
        this.sucursalUsuario = value;
    }

    /**
     * Gets the value of the tipoCuentaCuentaRelacionada property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTipoCuentaCuentaRelacionada() {
        return tipoCuentaCuentaRelacionada;
    }

    /**
     * Sets the value of the tipoCuentaCuentaRelacionada property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTipoCuentaCuentaRelacionada(JAXBElement<String> value) {
        this.tipoCuentaCuentaRelacionada = value;
    }

    /**
     * Gets the value of the tipoCuentaDeb property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTipoCuentaDeb() {
        return tipoCuentaDeb;
    }

    /**
     * Sets the value of the tipoCuentaDeb property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTipoCuentaDeb(JAXBElement<String> value) {
        this.tipoCuentaDeb = value;
    }

    /**
     * Gets the value of the tipoDeAutorizado property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTipoDeAutorizado() {
        return tipoDeAutorizado;
    }

    /**
     * Sets the value of the tipoDeAutorizado property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTipoDeAutorizado(JAXBElement<String> value) {
        this.tipoDeAutorizado = value;
    }

    /**
     * Gets the value of the tipoDeTarjeta property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTipoDeTarjeta() {
        return tipoDeTarjeta;
    }

    /**
     * Sets the value of the tipoDeTarjeta property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTipoDeTarjeta(JAXBElement<String> value) {
        this.tipoDeTarjeta = value;
    }

    /**
     * Gets the value of the tipoPaquete property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTipoPaquete() {
        return tipoPaquete;
    }

    /**
     * Sets the value of the tipoPaquete property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTipoPaquete(JAXBElement<String> value) {
        this.tipoPaquete = value;
    }

    /**
     * Gets the value of the tipoPersona property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTipoPersona() {
        return tipoPersona;
    }

    /**
     * Sets the value of the tipoPersona property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTipoPersona(JAXBElement<String> value) {
        this.tipoPersona = value;
    }

    /**
     * Gets the value of the usuarioOperacion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getUsuarioOperacion() {
        return usuarioOperacion;
    }

    /**
     * Sets the value of the usuarioOperacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setUsuarioOperacion(JAXBElement<String> value) {
        this.usuarioOperacion = value;
    }

    /**
     * Gets the value of the usuarioVenta property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getUsuarioVenta() {
        return usuarioVenta;
    }

    /**
     * Sets the value of the usuarioVenta property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setUsuarioVenta(JAXBElement<String> value) {
        this.usuarioVenta = value;
    }

}
