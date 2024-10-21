
package ar.com.santanderrio.obp.generated.webservices.discador;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Trx6000CnspersfisResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Trx6000CnspersfisResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}ResponseBase">
 *       &lt;sequence>
 *         &lt;element name="Antiguedad_domicilio_anterior" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Apellido1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Apellido2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Apellidos_nombre_madre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Apellidos_nombre_padre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Banca" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Campania" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CanalCaptacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CanalVenta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Cargo_empresa" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CodigoActividad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CodigoActividadRIU" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CodigoEmpresaGrupo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CodigoSujeto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Codigo_AFIP" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Codigo_Entidad_Prevision" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Codigo_actividad_BCRA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Codigo_actividad_secundaria" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Codigo_clanae_1997" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Codigo_clanae_2010" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CondicionCliente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Cony_NUP" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Division" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Doc2CodPais" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Doc2Condicion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Doc2ExpedidoPor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Doc2FecVto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Doc2FechaExpedicion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Doc2LugarExpedicion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Doc2MarcaVerificacionContraPadron" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Doc2Nro" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Doc2NroSec" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Doc2TimestampUltModif" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Doc2Tipo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DocPpalCodigoPaisProvincia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DocPpalCondicion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DocPpalExpedidoPor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DocPpalFecExpedicion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DocPpalFecVto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DocPpalLugarExpedicion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DocPpalMarcaVerificacionContraPadronExterno" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DocPpalNro" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DocPpalNroSec" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DocPpalTimestampUltModif" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DocPpalTipo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DomPpalTipoVia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DomPpalTiposDomicilios" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Dom_lab_calle" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Dom_lab_campo_var1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Dom_lab_campo_var2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Dom_lab_cod_pais" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Dom_lab_cod_postal_1_1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Dom_lab_cod_postal_2_4" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Dom_lab_cod_postal_6_3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Dom_lab_cod_provincia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Dom_lab_comuna" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Dom_lab_depto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Dom_lab_desc_comuna" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Dom_lab_desc_localidad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Dom_lab_entre_calle" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Dom_lab_fec_verificacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Dom_lab_localidad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Dom_lab_manzana" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Dom_lab_marca_correspondencia_devuelta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Dom_lab_marca_domicilio_erroneo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Dom_lab_marca_normalizacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Dom_lab_motivo_devolucion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Dom_lab_nro" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Dom_lab_nro_escalera" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Dom_lab_nro_portal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Dom_lab_nro_secuencia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Dom_lab_piso" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Dom_lab_ruta_cartero" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Dom_lab_suc_casilla_correos" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Dom_lab_suc_correo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Dom_lab_tipo_construccion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Dom_lab_tipo_domicilio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Dom_lab_tipo_nucleo_urbano" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Dom_lab_tipo_via" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Dom_lab_titularidad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Dom_lab_torre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Dom_lab_y_calle" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Dom_ppal_calle" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Dom_ppal_campo_var1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Dom_ppal_campo_var2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Dom_ppal_cod_pais" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Dom_ppal_cod_postal_1_1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Dom_ppal_cod_postal_2_4" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Dom_ppal_cod_provincia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Dom_ppal_comuna" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Dom_ppal_depto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Dom_ppal_desc_comuna" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Dom_ppal_desc_localidad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Dom_ppal_entre_calle" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Dom_ppal_fec_verificacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Dom_ppal_localidad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Dom_ppal_manzana" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Dom_ppal_manzana_6_3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Dom_ppal_marca_correspondencia_devuelta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Dom_ppal_marca_domicilio_erroneo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Dom_ppal_marca_normalizacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Dom_ppal_motivo_devolucion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Dom_ppal_nro" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Dom_ppal_nro_escalera" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Dom_ppal_nro_portal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Dom_ppal_piso" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Dom_ppal_ruta_cartero" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Dom_ppal_suc_casilla_correos" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Dom_ppal_suc_correo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Dom_ppal_timestamp_ult_modif" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Dom_ppal_tipo_construccion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Dom_ppal_tipo_nucleo_urbano" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Dom_ppal_titularidad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Dom_ppal_torre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Dom_ppal_y_calle" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Ejecutivo_cuenta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EstadoCivil" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EstadoCliente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Estrato" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Fax_dom_caracteristica" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Fax_dom_clase_tel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Fax_dom_internos" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Fax_dom_nro" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Fax_dom_nro_sec_dom" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Fax_dom_nro_secuencia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Fax_dom_observaciones" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Fax_dom_prefijo_larga_distancia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Fax_dom_timestamp_ult_modif" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Fax_dom_tipo_tel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Fax_lab_caracteristica" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Fax_lab_clase_tel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Fax_lab_internos" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Fax_lab_nro" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Fax_lab_nro_sec_dom" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Fax_lab_nro_sec_tel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Fax_lab_observaciones" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Fax_lab_prefijo_larga_distancia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Fax_lab_timestamp_ult_modif" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Fax_lab_tipo_tel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FecIngreso" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FechaAlta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FechaInicioActividad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FechaNacimiento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Fecha_Fallecimiento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Fecha_ingreso_a_Empresa" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FormaJuridica" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Gerente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Idioma" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Ing1_fec_act_ingreso" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Ing1_importe" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Ing1_importe_hasta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Ing1_moneda" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Ing1_timestamp_ult_modif" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Ing1_tipo_ingreso" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Ing2_fec_act_ingreso" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Ing2_importe" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Ing2_importe_hasta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Ing2_moneda" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Ing2_timestamp_ult_modif" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Ing2_tipo_ingreso" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Ing3_fec_act_ingreso" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Ing3_importe" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Ing3_importe_hasta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Ing3_moneda" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Ing3_timestamp_ult_modif" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Ing3_tipo_ingreso" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="JefeArea" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Lugar_nacimiento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MarcaClienteBancaPrivada" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Marca_Relacion_Dependencia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Marca_empleado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NUP2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Nacionalidad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NaturalezaJuridica" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NivelAcceso" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Nivel_estudios" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Nombre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NombreFantasia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Nombre_empresa" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Numero_documento_conyuge" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Numero_hijos" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Oficial1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Oficial2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Oficial3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Oficial4" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Oficial5" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PaisNacimiento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PaisResidencia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Personas_cargo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PertenenciaGrupo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Profesion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Ramo_empresa" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Regimen_matrimonial" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RelacionesEntrePersonas" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Sector" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Secuencia_dom_empresa_empleadora" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Secuencia_tel_empresa_empleadora" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Sexo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SubSegmento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SucAdministradora" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Team_leader" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Tel_lab_caracteristica" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Tel_lab_clase_tel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Tel_lab_internos" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Tel_lab_nro" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Tel_lab_nro_sec_dom" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Tel_lab_nro_secuencia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Tel_lab_observaciones" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Tel_lab_prefijo_larga_distancia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Tel_lab_timestamp_ult_modif" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Tel_lab_tipo_tel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Tel_ppal_caracteristica_telefonica" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Tel_ppal_clase_tel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Tel_ppal_internos" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Tel_ppal_nro" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Tel_ppal_observaciones" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Tel_ppal_prefijo_larga_distancia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Tel_ppal_secuencia_dom" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Tel_ppal_tipo_tel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TenerAvisos" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TenerContratos" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TiempoResidenciaPais" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TimestampUltModifDatosBasicos" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Timestamp_Cod_Actividad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Timestamp_ult_modif_datos_comp_pf" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Timestamp_ult_modif_dom_lab" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Timestamp_ult_modif_tel_ppal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Timestamp_ult_modif_unidad_control" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TipoOcupacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TipoPersona" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Tipo_Empresa_Empleadora" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Tipo_documento_conyuge" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Tipo_relacion_banco_cliente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Tipo_relacion_laboral_CF" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Ultimo_titulo_adquirido" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="UnidadNegocio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Uso1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Uso3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Uso4" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Uso5" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="VinculacionGrupoSantander" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Trx6000CnspersfisResponse", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", propOrder = {
    "antiguedadDomicilioAnterior",
    "apellido1",
    "apellido2",
    "apellidosNombreMadre",
    "apellidosNombrePadre",
    "banca",
    "campania",
    "canalCaptacion",
    "canalVenta",
    "cargoEmpresa",
    "codigoActividad",
    "codigoActividadRIU",
    "codigoEmpresaGrupo",
    "codigoSujeto",
    "codigoAFIP",
    "codigoEntidadPrevision",
    "codigoActividadBCRA",
    "codigoActividadSecundaria",
    "codigoClanae1997",
    "codigoClanae2010",
    "condicionCliente",
    "conyNUP",
    "division",
    "doc2CodPais",
    "doc2Condicion",
    "doc2ExpedidoPor",
    "doc2FecVto",
    "doc2FechaExpedicion",
    "doc2LugarExpedicion",
    "doc2MarcaVerificacionContraPadron",
    "doc2Nro",
    "doc2NroSec",
    "doc2TimestampUltModif",
    "doc2Tipo",
    "docPpalCodigoPaisProvincia",
    "docPpalCondicion",
    "docPpalExpedidoPor",
    "docPpalFecExpedicion",
    "docPpalFecVto",
    "docPpalLugarExpedicion",
    "docPpalMarcaVerificacionContraPadronExterno",
    "docPpalNro",
    "docPpalNroSec",
    "docPpalTimestampUltModif",
    "docPpalTipo",
    "domPpalTipoVia",
    "domPpalTiposDomicilios",
    "domLabCalle",
    "domLabCampoVar1",
    "domLabCampoVar2",
    "domLabCodPais",
    "domLabCodPostal11",
    "domLabCodPostal24",
    "domLabCodPostal63",
    "domLabCodProvincia",
    "domLabComuna",
    "domLabDepto",
    "domLabDescComuna",
    "domLabDescLocalidad",
    "domLabEntreCalle",
    "domLabFecVerificacion",
    "domLabLocalidad",
    "domLabManzana",
    "domLabMarcaCorrespondenciaDevuelta",
    "domLabMarcaDomicilioErroneo",
    "domLabMarcaNormalizacion",
    "domLabMotivoDevolucion",
    "domLabNro",
    "domLabNroEscalera",
    "domLabNroPortal",
    "domLabNroSecuencia",
    "domLabPiso",
    "domLabRutaCartero",
    "domLabSucCasillaCorreos",
    "domLabSucCorreo",
    "domLabTipoConstruccion",
    "domLabTipoDomicilio",
    "domLabTipoNucleoUrbano",
    "domLabTipoVia",
    "domLabTitularidad",
    "domLabTorre",
    "domLabYCalle",
    "domPpalCalle",
    "domPpalCampoVar1",
    "domPpalCampoVar2",
    "domPpalCodPais",
    "domPpalCodPostal11",
    "domPpalCodPostal24",
    "domPpalCodProvincia",
    "domPpalComuna",
    "domPpalDepto",
    "domPpalDescComuna",
    "domPpalDescLocalidad",
    "domPpalEntreCalle",
    "domPpalFecVerificacion",
    "domPpalLocalidad",
    "domPpalManzana",
    "domPpalManzana63",
    "domPpalMarcaCorrespondenciaDevuelta",
    "domPpalMarcaDomicilioErroneo",
    "domPpalMarcaNormalizacion",
    "domPpalMotivoDevolucion",
    "domPpalNro",
    "domPpalNroEscalera",
    "domPpalNroPortal",
    "domPpalPiso",
    "domPpalRutaCartero",
    "domPpalSucCasillaCorreos",
    "domPpalSucCorreo",
    "domPpalTimestampUltModif",
    "domPpalTipoConstruccion",
    "domPpalTipoNucleoUrbano",
    "domPpalTitularidad",
    "domPpalTorre",
    "domPpalYCalle",
    "ejecutivoCuenta",
    "estadoCivil",
    "estadoCliente",
    "estrato",
    "faxDomCaracteristica",
    "faxDomClaseTel",
    "faxDomInternos",
    "faxDomNro",
    "faxDomNroSecDom",
    "faxDomNroSecuencia",
    "faxDomObservaciones",
    "faxDomPrefijoLargaDistancia",
    "faxDomTimestampUltModif",
    "faxDomTipoTel",
    "faxLabCaracteristica",
    "faxLabClaseTel",
    "faxLabInternos",
    "faxLabNro",
    "faxLabNroSecDom",
    "faxLabNroSecTel",
    "faxLabObservaciones",
    "faxLabPrefijoLargaDistancia",
    "faxLabTimestampUltModif",
    "faxLabTipoTel",
    "fecIngreso",
    "fechaAlta",
    "fechaInicioActividad",
    "fechaNacimiento",
    "fechaFallecimiento",
    "fechaIngresoAEmpresa",
    "formaJuridica",
    "gerente",
    "idioma",
    "ing1FecActIngreso",
    "ing1Importe",
    "ing1ImporteHasta",
    "ing1Moneda",
    "ing1TimestampUltModif",
    "ing1TipoIngreso",
    "ing2FecActIngreso",
    "ing2Importe",
    "ing2ImporteHasta",
    "ing2Moneda",
    "ing2TimestampUltModif",
    "ing2TipoIngreso",
    "ing3FecActIngreso",
    "ing3Importe",
    "ing3ImporteHasta",
    "ing3Moneda",
    "ing3TimestampUltModif",
    "ing3TipoIngreso",
    "jefeArea",
    "lugarNacimiento",
    "marcaClienteBancaPrivada",
    "marcaRelacionDependencia",
    "marcaEmpleado",
    "nup2",
    "nacionalidad",
    "naturalezaJuridica",
    "nivelAcceso",
    "nivelEstudios",
    "nombre",
    "nombreFantasia",
    "nombreEmpresa",
    "numeroDocumentoConyuge",
    "numeroHijos",
    "oficial1",
    "oficial2",
    "oficial3",
    "oficial4",
    "oficial5",
    "paisNacimiento",
    "paisResidencia",
    "personasCargo",
    "pertenenciaGrupo",
    "profesion",
    "ramoEmpresa",
    "regimenMatrimonial",
    "relacionesEntrePersonas",
    "sector",
    "secuenciaDomEmpresaEmpleadora",
    "secuenciaTelEmpresaEmpleadora",
    "sexo",
    "subSegmento",
    "sucAdministradora",
    "teamLeader",
    "telLabCaracteristica",
    "telLabClaseTel",
    "telLabInternos",
    "telLabNro",
    "telLabNroSecDom",
    "telLabNroSecuencia",
    "telLabObservaciones",
    "telLabPrefijoLargaDistancia",
    "telLabTimestampUltModif",
    "telLabTipoTel",
    "telPpalCaracteristicaTelefonica",
    "telPpalClaseTel",
    "telPpalInternos",
    "telPpalNro",
    "telPpalObservaciones",
    "telPpalPrefijoLargaDistancia",
    "telPpalSecuenciaDom",
    "telPpalTipoTel",
    "tenerAvisos",
    "tenerContratos",
    "tiempoResidenciaPais",
    "timestampUltModifDatosBasicos",
    "timestampCodActividad",
    "timestampUltModifDatosCompPf",
    "timestampUltModifDomLab",
    "timestampUltModifTelPpal",
    "timestampUltModifUnidadControl",
    "tipoOcupacion",
    "tipoPersona",
    "tipoEmpresaEmpleadora",
    "tipoDocumentoConyuge",
    "tipoRelacionBancoCliente",
    "tipoRelacionLaboralCF",
    "ultimoTituloAdquirido",
    "unidadNegocio",
    "uso1",
    "uso3",
    "uso4",
    "uso5",
    "vinculacionGrupoSantander"
})
public class Trx6000CnspersfisResponse
    extends ResponseBase
{

    @XmlElementRef(name = "Antiguedad_domicilio_anterior", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> antiguedadDomicilioAnterior;
    @XmlElementRef(name = "Apellido1", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> apellido1;
    @XmlElementRef(name = "Apellido2", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> apellido2;
    @XmlElementRef(name = "Apellidos_nombre_madre", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> apellidosNombreMadre;
    @XmlElementRef(name = "Apellidos_nombre_padre", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> apellidosNombrePadre;
    @XmlElementRef(name = "Banca", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> banca;
    @XmlElementRef(name = "Campania", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> campania;
    @XmlElementRef(name = "CanalCaptacion", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> canalCaptacion;
    @XmlElementRef(name = "CanalVenta", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> canalVenta;
    @XmlElementRef(name = "Cargo_empresa", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> cargoEmpresa;
    @XmlElementRef(name = "CodigoActividad", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> codigoActividad;
    @XmlElementRef(name = "CodigoActividadRIU", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> codigoActividadRIU;
    @XmlElementRef(name = "CodigoEmpresaGrupo", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> codigoEmpresaGrupo;
    @XmlElementRef(name = "CodigoSujeto", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> codigoSujeto;
    @XmlElementRef(name = "Codigo_AFIP", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> codigoAFIP;
    @XmlElementRef(name = "Codigo_Entidad_Prevision", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> codigoEntidadPrevision;
    @XmlElementRef(name = "Codigo_actividad_BCRA", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> codigoActividadBCRA;
    @XmlElementRef(name = "Codigo_actividad_secundaria", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> codigoActividadSecundaria;
    @XmlElementRef(name = "Codigo_clanae_1997", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> codigoClanae1997;
    @XmlElementRef(name = "Codigo_clanae_2010", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> codigoClanae2010;
    @XmlElementRef(name = "CondicionCliente", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> condicionCliente;
    @XmlElementRef(name = "Cony_NUP", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> conyNUP;
    @XmlElementRef(name = "Division", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> division;
    @XmlElementRef(name = "Doc2CodPais", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> doc2CodPais;
    @XmlElementRef(name = "Doc2Condicion", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> doc2Condicion;
    @XmlElementRef(name = "Doc2ExpedidoPor", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> doc2ExpedidoPor;
    @XmlElementRef(name = "Doc2FecVto", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> doc2FecVto;
    @XmlElementRef(name = "Doc2FechaExpedicion", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> doc2FechaExpedicion;
    @XmlElementRef(name = "Doc2LugarExpedicion", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> doc2LugarExpedicion;
    @XmlElementRef(name = "Doc2MarcaVerificacionContraPadron", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> doc2MarcaVerificacionContraPadron;
    @XmlElementRef(name = "Doc2Nro", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> doc2Nro;
    @XmlElementRef(name = "Doc2NroSec", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> doc2NroSec;
    @XmlElementRef(name = "Doc2TimestampUltModif", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> doc2TimestampUltModif;
    @XmlElementRef(name = "Doc2Tipo", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> doc2Tipo;
    @XmlElementRef(name = "DocPpalCodigoPaisProvincia", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> docPpalCodigoPaisProvincia;
    @XmlElementRef(name = "DocPpalCondicion", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> docPpalCondicion;
    @XmlElementRef(name = "DocPpalExpedidoPor", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> docPpalExpedidoPor;
    @XmlElementRef(name = "DocPpalFecExpedicion", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> docPpalFecExpedicion;
    @XmlElementRef(name = "DocPpalFecVto", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> docPpalFecVto;
    @XmlElementRef(name = "DocPpalLugarExpedicion", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> docPpalLugarExpedicion;
    @XmlElementRef(name = "DocPpalMarcaVerificacionContraPadronExterno", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> docPpalMarcaVerificacionContraPadronExterno;
    @XmlElementRef(name = "DocPpalNro", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> docPpalNro;
    @XmlElementRef(name = "DocPpalNroSec", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> docPpalNroSec;
    @XmlElementRef(name = "DocPpalTimestampUltModif", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> docPpalTimestampUltModif;
    @XmlElementRef(name = "DocPpalTipo", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> docPpalTipo;
    @XmlElementRef(name = "DomPpalTipoVia", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> domPpalTipoVia;
    @XmlElementRef(name = "DomPpalTiposDomicilios", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> domPpalTiposDomicilios;
    @XmlElementRef(name = "Dom_lab_calle", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> domLabCalle;
    @XmlElementRef(name = "Dom_lab_campo_var1", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> domLabCampoVar1;
    @XmlElementRef(name = "Dom_lab_campo_var2", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> domLabCampoVar2;
    @XmlElementRef(name = "Dom_lab_cod_pais", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> domLabCodPais;
    @XmlElementRef(name = "Dom_lab_cod_postal_1_1", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> domLabCodPostal11;
    @XmlElementRef(name = "Dom_lab_cod_postal_2_4", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> domLabCodPostal24;
    @XmlElementRef(name = "Dom_lab_cod_postal_6_3", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> domLabCodPostal63;
    @XmlElementRef(name = "Dom_lab_cod_provincia", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> domLabCodProvincia;
    @XmlElementRef(name = "Dom_lab_comuna", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> domLabComuna;
    @XmlElementRef(name = "Dom_lab_depto", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> domLabDepto;
    @XmlElementRef(name = "Dom_lab_desc_comuna", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> domLabDescComuna;
    @XmlElementRef(name = "Dom_lab_desc_localidad", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> domLabDescLocalidad;
    @XmlElementRef(name = "Dom_lab_entre_calle", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> domLabEntreCalle;
    @XmlElementRef(name = "Dom_lab_fec_verificacion", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> domLabFecVerificacion;
    @XmlElementRef(name = "Dom_lab_localidad", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> domLabLocalidad;
    @XmlElementRef(name = "Dom_lab_manzana", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> domLabManzana;
    @XmlElementRef(name = "Dom_lab_marca_correspondencia_devuelta", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> domLabMarcaCorrespondenciaDevuelta;
    @XmlElementRef(name = "Dom_lab_marca_domicilio_erroneo", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> domLabMarcaDomicilioErroneo;
    @XmlElementRef(name = "Dom_lab_marca_normalizacion", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> domLabMarcaNormalizacion;
    @XmlElementRef(name = "Dom_lab_motivo_devolucion", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> domLabMotivoDevolucion;
    @XmlElementRef(name = "Dom_lab_nro", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> domLabNro;
    @XmlElementRef(name = "Dom_lab_nro_escalera", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> domLabNroEscalera;
    @XmlElementRef(name = "Dom_lab_nro_portal", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> domLabNroPortal;
    @XmlElementRef(name = "Dom_lab_nro_secuencia", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> domLabNroSecuencia;
    @XmlElementRef(name = "Dom_lab_piso", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> domLabPiso;
    @XmlElementRef(name = "Dom_lab_ruta_cartero", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> domLabRutaCartero;
    @XmlElementRef(name = "Dom_lab_suc_casilla_correos", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> domLabSucCasillaCorreos;
    @XmlElementRef(name = "Dom_lab_suc_correo", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> domLabSucCorreo;
    @XmlElementRef(name = "Dom_lab_tipo_construccion", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> domLabTipoConstruccion;
    @XmlElementRef(name = "Dom_lab_tipo_domicilio", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> domLabTipoDomicilio;
    @XmlElementRef(name = "Dom_lab_tipo_nucleo_urbano", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> domLabTipoNucleoUrbano;
    @XmlElementRef(name = "Dom_lab_tipo_via", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> domLabTipoVia;
    @XmlElementRef(name = "Dom_lab_titularidad", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> domLabTitularidad;
    @XmlElementRef(name = "Dom_lab_torre", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> domLabTorre;
    @XmlElementRef(name = "Dom_lab_y_calle", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> domLabYCalle;
    @XmlElementRef(name = "Dom_ppal_calle", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> domPpalCalle;
    @XmlElementRef(name = "Dom_ppal_campo_var1", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> domPpalCampoVar1;
    @XmlElementRef(name = "Dom_ppal_campo_var2", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> domPpalCampoVar2;
    @XmlElementRef(name = "Dom_ppal_cod_pais", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> domPpalCodPais;
    @XmlElementRef(name = "Dom_ppal_cod_postal_1_1", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> domPpalCodPostal11;
    @XmlElementRef(name = "Dom_ppal_cod_postal_2_4", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> domPpalCodPostal24;
    @XmlElementRef(name = "Dom_ppal_cod_provincia", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> domPpalCodProvincia;
    @XmlElementRef(name = "Dom_ppal_comuna", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> domPpalComuna;
    @XmlElementRef(name = "Dom_ppal_depto", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> domPpalDepto;
    @XmlElementRef(name = "Dom_ppal_desc_comuna", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> domPpalDescComuna;
    @XmlElementRef(name = "Dom_ppal_desc_localidad", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> domPpalDescLocalidad;
    @XmlElementRef(name = "Dom_ppal_entre_calle", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> domPpalEntreCalle;
    @XmlElementRef(name = "Dom_ppal_fec_verificacion", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> domPpalFecVerificacion;
    @XmlElementRef(name = "Dom_ppal_localidad", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> domPpalLocalidad;
    @XmlElementRef(name = "Dom_ppal_manzana", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> domPpalManzana;
    @XmlElementRef(name = "Dom_ppal_manzana_6_3", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> domPpalManzana63;
    @XmlElementRef(name = "Dom_ppal_marca_correspondencia_devuelta", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> domPpalMarcaCorrespondenciaDevuelta;
    @XmlElementRef(name = "Dom_ppal_marca_domicilio_erroneo", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> domPpalMarcaDomicilioErroneo;
    @XmlElementRef(name = "Dom_ppal_marca_normalizacion", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> domPpalMarcaNormalizacion;
    @XmlElementRef(name = "Dom_ppal_motivo_devolucion", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> domPpalMotivoDevolucion;
    @XmlElementRef(name = "Dom_ppal_nro", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> domPpalNro;
    @XmlElementRef(name = "Dom_ppal_nro_escalera", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> domPpalNroEscalera;
    @XmlElementRef(name = "Dom_ppal_nro_portal", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> domPpalNroPortal;
    @XmlElementRef(name = "Dom_ppal_piso", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> domPpalPiso;
    @XmlElementRef(name = "Dom_ppal_ruta_cartero", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> domPpalRutaCartero;
    @XmlElementRef(name = "Dom_ppal_suc_casilla_correos", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> domPpalSucCasillaCorreos;
    @XmlElementRef(name = "Dom_ppal_suc_correo", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> domPpalSucCorreo;
    @XmlElementRef(name = "Dom_ppal_timestamp_ult_modif", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> domPpalTimestampUltModif;
    @XmlElementRef(name = "Dom_ppal_tipo_construccion", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> domPpalTipoConstruccion;
    @XmlElementRef(name = "Dom_ppal_tipo_nucleo_urbano", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> domPpalTipoNucleoUrbano;
    @XmlElementRef(name = "Dom_ppal_titularidad", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> domPpalTitularidad;
    @XmlElementRef(name = "Dom_ppal_torre", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> domPpalTorre;
    @XmlElementRef(name = "Dom_ppal_y_calle", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> domPpalYCalle;
    @XmlElementRef(name = "Ejecutivo_cuenta", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> ejecutivoCuenta;
    @XmlElementRef(name = "EstadoCivil", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> estadoCivil;
    @XmlElementRef(name = "EstadoCliente", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> estadoCliente;
    @XmlElementRef(name = "Estrato", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> estrato;
    @XmlElementRef(name = "Fax_dom_caracteristica", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> faxDomCaracteristica;
    @XmlElementRef(name = "Fax_dom_clase_tel", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> faxDomClaseTel;
    @XmlElementRef(name = "Fax_dom_internos", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> faxDomInternos;
    @XmlElementRef(name = "Fax_dom_nro", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> faxDomNro;
    @XmlElementRef(name = "Fax_dom_nro_sec_dom", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> faxDomNroSecDom;
    @XmlElementRef(name = "Fax_dom_nro_secuencia", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> faxDomNroSecuencia;
    @XmlElementRef(name = "Fax_dom_observaciones", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> faxDomObservaciones;
    @XmlElementRef(name = "Fax_dom_prefijo_larga_distancia", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> faxDomPrefijoLargaDistancia;
    @XmlElementRef(name = "Fax_dom_timestamp_ult_modif", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> faxDomTimestampUltModif;
    @XmlElementRef(name = "Fax_dom_tipo_tel", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> faxDomTipoTel;
    @XmlElementRef(name = "Fax_lab_caracteristica", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> faxLabCaracteristica;
    @XmlElementRef(name = "Fax_lab_clase_tel", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> faxLabClaseTel;
    @XmlElementRef(name = "Fax_lab_internos", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> faxLabInternos;
    @XmlElementRef(name = "Fax_lab_nro", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> faxLabNro;
    @XmlElementRef(name = "Fax_lab_nro_sec_dom", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> faxLabNroSecDom;
    @XmlElementRef(name = "Fax_lab_nro_sec_tel", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> faxLabNroSecTel;
    @XmlElementRef(name = "Fax_lab_observaciones", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> faxLabObservaciones;
    @XmlElementRef(name = "Fax_lab_prefijo_larga_distancia", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> faxLabPrefijoLargaDistancia;
    @XmlElementRef(name = "Fax_lab_timestamp_ult_modif", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> faxLabTimestampUltModif;
    @XmlElementRef(name = "Fax_lab_tipo_tel", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> faxLabTipoTel;
    @XmlElementRef(name = "FecIngreso", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> fecIngreso;
    @XmlElementRef(name = "FechaAlta", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> fechaAlta;
    @XmlElementRef(name = "FechaInicioActividad", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> fechaInicioActividad;
    @XmlElementRef(name = "FechaNacimiento", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> fechaNacimiento;
    @XmlElementRef(name = "Fecha_Fallecimiento", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> fechaFallecimiento;
    @XmlElementRef(name = "Fecha_ingreso_a_Empresa", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> fechaIngresoAEmpresa;
    @XmlElementRef(name = "FormaJuridica", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> formaJuridica;
    @XmlElementRef(name = "Gerente", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> gerente;
    @XmlElementRef(name = "Idioma", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> idioma;
    @XmlElementRef(name = "Ing1_fec_act_ingreso", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> ing1FecActIngreso;
    @XmlElementRef(name = "Ing1_importe", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> ing1Importe;
    @XmlElementRef(name = "Ing1_importe_hasta", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> ing1ImporteHasta;
    @XmlElementRef(name = "Ing1_moneda", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> ing1Moneda;
    @XmlElementRef(name = "Ing1_timestamp_ult_modif", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> ing1TimestampUltModif;
    @XmlElementRef(name = "Ing1_tipo_ingreso", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> ing1TipoIngreso;
    @XmlElementRef(name = "Ing2_fec_act_ingreso", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> ing2FecActIngreso;
    @XmlElementRef(name = "Ing2_importe", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> ing2Importe;
    @XmlElementRef(name = "Ing2_importe_hasta", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> ing2ImporteHasta;
    @XmlElementRef(name = "Ing2_moneda", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> ing2Moneda;
    @XmlElementRef(name = "Ing2_timestamp_ult_modif", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> ing2TimestampUltModif;
    @XmlElementRef(name = "Ing2_tipo_ingreso", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> ing2TipoIngreso;
    @XmlElementRef(name = "Ing3_fec_act_ingreso", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> ing3FecActIngreso;
    @XmlElementRef(name = "Ing3_importe", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> ing3Importe;
    @XmlElementRef(name = "Ing3_importe_hasta", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> ing3ImporteHasta;
    @XmlElementRef(name = "Ing3_moneda", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> ing3Moneda;
    @XmlElementRef(name = "Ing3_timestamp_ult_modif", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> ing3TimestampUltModif;
    @XmlElementRef(name = "Ing3_tipo_ingreso", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> ing3TipoIngreso;
    @XmlElementRef(name = "JefeArea", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> jefeArea;
    @XmlElementRef(name = "Lugar_nacimiento", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> lugarNacimiento;
    @XmlElementRef(name = "MarcaClienteBancaPrivada", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> marcaClienteBancaPrivada;
    @XmlElementRef(name = "Marca_Relacion_Dependencia", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> marcaRelacionDependencia;
    @XmlElementRef(name = "Marca_empleado", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> marcaEmpleado;
    @XmlElementRef(name = "NUP2", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> nup2;
    @XmlElementRef(name = "Nacionalidad", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> nacionalidad;
    @XmlElementRef(name = "NaturalezaJuridica", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> naturalezaJuridica;
    @XmlElementRef(name = "NivelAcceso", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> nivelAcceso;
    @XmlElementRef(name = "Nivel_estudios", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> nivelEstudios;
    @XmlElementRef(name = "Nombre", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> nombre;
    @XmlElementRef(name = "NombreFantasia", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> nombreFantasia;
    @XmlElementRef(name = "Nombre_empresa", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> nombreEmpresa;
    @XmlElementRef(name = "Numero_documento_conyuge", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> numeroDocumentoConyuge;
    @XmlElementRef(name = "Numero_hijos", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> numeroHijos;
    @XmlElementRef(name = "Oficial1", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> oficial1;
    @XmlElementRef(name = "Oficial2", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> oficial2;
    @XmlElementRef(name = "Oficial3", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> oficial3;
    @XmlElementRef(name = "Oficial4", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> oficial4;
    @XmlElementRef(name = "Oficial5", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> oficial5;
    @XmlElementRef(name = "PaisNacimiento", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> paisNacimiento;
    @XmlElementRef(name = "PaisResidencia", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> paisResidencia;
    @XmlElementRef(name = "Personas_cargo", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> personasCargo;
    @XmlElementRef(name = "PertenenciaGrupo", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> pertenenciaGrupo;
    @XmlElementRef(name = "Profesion", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> profesion;
    @XmlElementRef(name = "Ramo_empresa", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> ramoEmpresa;
    @XmlElementRef(name = "Regimen_matrimonial", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> regimenMatrimonial;
    @XmlElementRef(name = "RelacionesEntrePersonas", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> relacionesEntrePersonas;
    @XmlElementRef(name = "Sector", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> sector;
    @XmlElementRef(name = "Secuencia_dom_empresa_empleadora", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> secuenciaDomEmpresaEmpleadora;
    @XmlElementRef(name = "Secuencia_tel_empresa_empleadora", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> secuenciaTelEmpresaEmpleadora;
    @XmlElementRef(name = "Sexo", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> sexo;
    @XmlElementRef(name = "SubSegmento", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> subSegmento;
    @XmlElementRef(name = "SucAdministradora", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> sucAdministradora;
    @XmlElementRef(name = "Team_leader", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> teamLeader;
    @XmlElementRef(name = "Tel_lab_caracteristica", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> telLabCaracteristica;
    @XmlElementRef(name = "Tel_lab_clase_tel", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> telLabClaseTel;
    @XmlElementRef(name = "Tel_lab_internos", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> telLabInternos;
    @XmlElementRef(name = "Tel_lab_nro", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> telLabNro;
    @XmlElementRef(name = "Tel_lab_nro_sec_dom", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> telLabNroSecDom;
    @XmlElementRef(name = "Tel_lab_nro_secuencia", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> telLabNroSecuencia;
    @XmlElementRef(name = "Tel_lab_observaciones", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> telLabObservaciones;
    @XmlElementRef(name = "Tel_lab_prefijo_larga_distancia", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> telLabPrefijoLargaDistancia;
    @XmlElementRef(name = "Tel_lab_timestamp_ult_modif", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> telLabTimestampUltModif;
    @XmlElementRef(name = "Tel_lab_tipo_tel", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> telLabTipoTel;
    @XmlElementRef(name = "Tel_ppal_caracteristica_telefonica", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> telPpalCaracteristicaTelefonica;
    @XmlElementRef(name = "Tel_ppal_clase_tel", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> telPpalClaseTel;
    @XmlElementRef(name = "Tel_ppal_internos", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> telPpalInternos;
    @XmlElementRef(name = "Tel_ppal_nro", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> telPpalNro;
    @XmlElementRef(name = "Tel_ppal_observaciones", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> telPpalObservaciones;
    @XmlElementRef(name = "Tel_ppal_prefijo_larga_distancia", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> telPpalPrefijoLargaDistancia;
    @XmlElementRef(name = "Tel_ppal_secuencia_dom", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> telPpalSecuenciaDom;
    @XmlElementRef(name = "Tel_ppal_tipo_tel", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> telPpalTipoTel;
    @XmlElementRef(name = "TenerAvisos", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> tenerAvisos;
    @XmlElementRef(name = "TenerContratos", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> tenerContratos;
    @XmlElementRef(name = "TiempoResidenciaPais", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> tiempoResidenciaPais;
    @XmlElementRef(name = "TimestampUltModifDatosBasicos", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> timestampUltModifDatosBasicos;
    @XmlElementRef(name = "Timestamp_Cod_Actividad", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> timestampCodActividad;
    @XmlElementRef(name = "Timestamp_ult_modif_datos_comp_pf", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> timestampUltModifDatosCompPf;
    @XmlElementRef(name = "Timestamp_ult_modif_dom_lab", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> timestampUltModifDomLab;
    @XmlElementRef(name = "Timestamp_ult_modif_tel_ppal", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> timestampUltModifTelPpal;
    @XmlElementRef(name = "Timestamp_ult_modif_unidad_control", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> timestampUltModifUnidadControl;
    @XmlElementRef(name = "TipoOcupacion", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> tipoOcupacion;
    @XmlElementRef(name = "TipoPersona", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> tipoPersona;
    @XmlElementRef(name = "Tipo_Empresa_Empleadora", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> tipoEmpresaEmpleadora;
    @XmlElementRef(name = "Tipo_documento_conyuge", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> tipoDocumentoConyuge;
    @XmlElementRef(name = "Tipo_relacion_banco_cliente", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> tipoRelacionBancoCliente;
    @XmlElementRef(name = "Tipo_relacion_laboral_CF", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> tipoRelacionLaboralCF;
    @XmlElementRef(name = "Ultimo_titulo_adquirido", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> ultimoTituloAdquirido;
    @XmlElementRef(name = "UnidadNegocio", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> unidadNegocio;
    @XmlElementRef(name = "Uso1", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> uso1;
    @XmlElementRef(name = "Uso3", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> uso3;
    @XmlElementRef(name = "Uso4", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> uso4;
    @XmlElementRef(name = "Uso5", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> uso5;
    @XmlElementRef(name = "VinculacionGrupoSantander", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6000Cnspersfis", type = JAXBElement.class)
    protected JAXBElement<String> vinculacionGrupoSantander;

    /**
     * Gets the value of the antiguedadDomicilioAnterior property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getAntiguedadDomicilioAnterior() {
        return antiguedadDomicilioAnterior;
    }

    /**
     * Sets the value of the antiguedadDomicilioAnterior property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setAntiguedadDomicilioAnterior(JAXBElement<String> value) {
        this.antiguedadDomicilioAnterior = value;
    }

    /**
     * Gets the value of the apellido1 property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getApellido1() {
        return apellido1;
    }

    /**
     * Sets the value of the apellido1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setApellido1(JAXBElement<String> value) {
        this.apellido1 = value;
    }

    /**
     * Gets the value of the apellido2 property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getApellido2() {
        return apellido2;
    }

    /**
     * Sets the value of the apellido2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setApellido2(JAXBElement<String> value) {
        this.apellido2 = value;
    }

    /**
     * Gets the value of the apellidosNombreMadre property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getApellidosNombreMadre() {
        return apellidosNombreMadre;
    }

    /**
     * Sets the value of the apellidosNombreMadre property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setApellidosNombreMadre(JAXBElement<String> value) {
        this.apellidosNombreMadre = value;
    }

    /**
     * Gets the value of the apellidosNombrePadre property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getApellidosNombrePadre() {
        return apellidosNombrePadre;
    }

    /**
     * Sets the value of the apellidosNombrePadre property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setApellidosNombrePadre(JAXBElement<String> value) {
        this.apellidosNombrePadre = value;
    }

    /**
     * Gets the value of the banca property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getBanca() {
        return banca;
    }

    /**
     * Sets the value of the banca property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setBanca(JAXBElement<String> value) {
        this.banca = value;
    }

    /**
     * Gets the value of the campania property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCampania() {
        return campania;
    }

    /**
     * Sets the value of the campania property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCampania(JAXBElement<String> value) {
        this.campania = value;
    }

    /**
     * Gets the value of the canalCaptacion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCanalCaptacion() {
        return canalCaptacion;
    }

    /**
     * Sets the value of the canalCaptacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCanalCaptacion(JAXBElement<String> value) {
        this.canalCaptacion = value;
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
     * Gets the value of the cargoEmpresa property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCargoEmpresa() {
        return cargoEmpresa;
    }

    /**
     * Sets the value of the cargoEmpresa property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCargoEmpresa(JAXBElement<String> value) {
        this.cargoEmpresa = value;
    }

    /**
     * Gets the value of the codigoActividad property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCodigoActividad() {
        return codigoActividad;
    }

    /**
     * Sets the value of the codigoActividad property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCodigoActividad(JAXBElement<String> value) {
        this.codigoActividad = value;
    }

    /**
     * Gets the value of the codigoActividadRIU property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCodigoActividadRIU() {
        return codigoActividadRIU;
    }

    /**
     * Sets the value of the codigoActividadRIU property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCodigoActividadRIU(JAXBElement<String> value) {
        this.codigoActividadRIU = value;
    }

    /**
     * Gets the value of the codigoEmpresaGrupo property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCodigoEmpresaGrupo() {
        return codigoEmpresaGrupo;
    }

    /**
     * Sets the value of the codigoEmpresaGrupo property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCodigoEmpresaGrupo(JAXBElement<String> value) {
        this.codigoEmpresaGrupo = value;
    }

    /**
     * Gets the value of the codigoSujeto property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCodigoSujeto() {
        return codigoSujeto;
    }

    /**
     * Sets the value of the codigoSujeto property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCodigoSujeto(JAXBElement<String> value) {
        this.codigoSujeto = value;
    }

    /**
     * Gets the value of the codigoAFIP property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCodigoAFIP() {
        return codigoAFIP;
    }

    /**
     * Sets the value of the codigoAFIP property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCodigoAFIP(JAXBElement<String> value) {
        this.codigoAFIP = value;
    }

    /**
     * Gets the value of the codigoEntidadPrevision property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCodigoEntidadPrevision() {
        return codigoEntidadPrevision;
    }

    /**
     * Sets the value of the codigoEntidadPrevision property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCodigoEntidadPrevision(JAXBElement<String> value) {
        this.codigoEntidadPrevision = value;
    }

    /**
     * Gets the value of the codigoActividadBCRA property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCodigoActividadBCRA() {
        return codigoActividadBCRA;
    }

    /**
     * Sets the value of the codigoActividadBCRA property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCodigoActividadBCRA(JAXBElement<String> value) {
        this.codigoActividadBCRA = value;
    }

    /**
     * Gets the value of the codigoActividadSecundaria property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCodigoActividadSecundaria() {
        return codigoActividadSecundaria;
    }

    /**
     * Sets the value of the codigoActividadSecundaria property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCodigoActividadSecundaria(JAXBElement<String> value) {
        this.codigoActividadSecundaria = value;
    }

    /**
     * Gets the value of the codigoClanae1997 property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCodigoClanae1997() {
        return codigoClanae1997;
    }

    /**
     * Sets the value of the codigoClanae1997 property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCodigoClanae1997(JAXBElement<String> value) {
        this.codigoClanae1997 = value;
    }

    /**
     * Gets the value of the codigoClanae2010 property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCodigoClanae2010() {
        return codigoClanae2010;
    }

    /**
     * Sets the value of the codigoClanae2010 property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCodigoClanae2010(JAXBElement<String> value) {
        this.codigoClanae2010 = value;
    }

    /**
     * Gets the value of the condicionCliente property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCondicionCliente() {
        return condicionCliente;
    }

    /**
     * Sets the value of the condicionCliente property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCondicionCliente(JAXBElement<String> value) {
        this.condicionCliente = value;
    }

    /**
     * Gets the value of the conyNUP property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getConyNUP() {
        return conyNUP;
    }

    /**
     * Sets the value of the conyNUP property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setConyNUP(JAXBElement<String> value) {
        this.conyNUP = value;
    }

    /**
     * Gets the value of the division property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDivision() {
        return division;
    }

    /**
     * Sets the value of the division property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDivision(JAXBElement<String> value) {
        this.division = value;
    }

    /**
     * Gets the value of the doc2CodPais property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDoc2CodPais() {
        return doc2CodPais;
    }

    /**
     * Sets the value of the doc2CodPais property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDoc2CodPais(JAXBElement<String> value) {
        this.doc2CodPais = value;
    }

    /**
     * Gets the value of the doc2Condicion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDoc2Condicion() {
        return doc2Condicion;
    }

    /**
     * Sets the value of the doc2Condicion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDoc2Condicion(JAXBElement<String> value) {
        this.doc2Condicion = value;
    }

    /**
     * Gets the value of the doc2ExpedidoPor property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDoc2ExpedidoPor() {
        return doc2ExpedidoPor;
    }

    /**
     * Sets the value of the doc2ExpedidoPor property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDoc2ExpedidoPor(JAXBElement<String> value) {
        this.doc2ExpedidoPor = value;
    }

    /**
     * Gets the value of the doc2FecVto property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDoc2FecVto() {
        return doc2FecVto;
    }

    /**
     * Sets the value of the doc2FecVto property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDoc2FecVto(JAXBElement<String> value) {
        this.doc2FecVto = value;
    }

    /**
     * Gets the value of the doc2FechaExpedicion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDoc2FechaExpedicion() {
        return doc2FechaExpedicion;
    }

    /**
     * Sets the value of the doc2FechaExpedicion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDoc2FechaExpedicion(JAXBElement<String> value) {
        this.doc2FechaExpedicion = value;
    }

    /**
     * Gets the value of the doc2LugarExpedicion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDoc2LugarExpedicion() {
        return doc2LugarExpedicion;
    }

    /**
     * Sets the value of the doc2LugarExpedicion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDoc2LugarExpedicion(JAXBElement<String> value) {
        this.doc2LugarExpedicion = value;
    }

    /**
     * Gets the value of the doc2MarcaVerificacionContraPadron property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDoc2MarcaVerificacionContraPadron() {
        return doc2MarcaVerificacionContraPadron;
    }

    /**
     * Sets the value of the doc2MarcaVerificacionContraPadron property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDoc2MarcaVerificacionContraPadron(JAXBElement<String> value) {
        this.doc2MarcaVerificacionContraPadron = value;
    }

    /**
     * Gets the value of the doc2Nro property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDoc2Nro() {
        return doc2Nro;
    }

    /**
     * Sets the value of the doc2Nro property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDoc2Nro(JAXBElement<String> value) {
        this.doc2Nro = value;
    }

    /**
     * Gets the value of the doc2NroSec property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDoc2NroSec() {
        return doc2NroSec;
    }

    /**
     * Sets the value of the doc2NroSec property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDoc2NroSec(JAXBElement<String> value) {
        this.doc2NroSec = value;
    }

    /**
     * Gets the value of the doc2TimestampUltModif property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDoc2TimestampUltModif() {
        return doc2TimestampUltModif;
    }

    /**
     * Sets the value of the doc2TimestampUltModif property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDoc2TimestampUltModif(JAXBElement<String> value) {
        this.doc2TimestampUltModif = value;
    }

    /**
     * Gets the value of the doc2Tipo property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDoc2Tipo() {
        return doc2Tipo;
    }

    /**
     * Sets the value of the doc2Tipo property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDoc2Tipo(JAXBElement<String> value) {
        this.doc2Tipo = value;
    }

    /**
     * Gets the value of the docPpalCodigoPaisProvincia property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDocPpalCodigoPaisProvincia() {
        return docPpalCodigoPaisProvincia;
    }

    /**
     * Sets the value of the docPpalCodigoPaisProvincia property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDocPpalCodigoPaisProvincia(JAXBElement<String> value) {
        this.docPpalCodigoPaisProvincia = value;
    }

    /**
     * Gets the value of the docPpalCondicion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDocPpalCondicion() {
        return docPpalCondicion;
    }

    /**
     * Sets the value of the docPpalCondicion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDocPpalCondicion(JAXBElement<String> value) {
        this.docPpalCondicion = value;
    }

    /**
     * Gets the value of the docPpalExpedidoPor property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDocPpalExpedidoPor() {
        return docPpalExpedidoPor;
    }

    /**
     * Sets the value of the docPpalExpedidoPor property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDocPpalExpedidoPor(JAXBElement<String> value) {
        this.docPpalExpedidoPor = value;
    }

    /**
     * Gets the value of the docPpalFecExpedicion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDocPpalFecExpedicion() {
        return docPpalFecExpedicion;
    }

    /**
     * Sets the value of the docPpalFecExpedicion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDocPpalFecExpedicion(JAXBElement<String> value) {
        this.docPpalFecExpedicion = value;
    }

    /**
     * Gets the value of the docPpalFecVto property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDocPpalFecVto() {
        return docPpalFecVto;
    }

    /**
     * Sets the value of the docPpalFecVto property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDocPpalFecVto(JAXBElement<String> value) {
        this.docPpalFecVto = value;
    }

    /**
     * Gets the value of the docPpalLugarExpedicion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDocPpalLugarExpedicion() {
        return docPpalLugarExpedicion;
    }

    /**
     * Sets the value of the docPpalLugarExpedicion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDocPpalLugarExpedicion(JAXBElement<String> value) {
        this.docPpalLugarExpedicion = value;
    }

    /**
     * Gets the value of the docPpalMarcaVerificacionContraPadronExterno property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDocPpalMarcaVerificacionContraPadronExterno() {
        return docPpalMarcaVerificacionContraPadronExterno;
    }

    /**
     * Sets the value of the docPpalMarcaVerificacionContraPadronExterno property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDocPpalMarcaVerificacionContraPadronExterno(JAXBElement<String> value) {
        this.docPpalMarcaVerificacionContraPadronExterno = value;
    }

    /**
     * Gets the value of the docPpalNro property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDocPpalNro() {
        return docPpalNro;
    }

    /**
     * Sets the value of the docPpalNro property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDocPpalNro(JAXBElement<String> value) {
        this.docPpalNro = value;
    }

    /**
     * Gets the value of the docPpalNroSec property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDocPpalNroSec() {
        return docPpalNroSec;
    }

    /**
     * Sets the value of the docPpalNroSec property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDocPpalNroSec(JAXBElement<String> value) {
        this.docPpalNroSec = value;
    }

    /**
     * Gets the value of the docPpalTimestampUltModif property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDocPpalTimestampUltModif() {
        return docPpalTimestampUltModif;
    }

    /**
     * Sets the value of the docPpalTimestampUltModif property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDocPpalTimestampUltModif(JAXBElement<String> value) {
        this.docPpalTimestampUltModif = value;
    }

    /**
     * Gets the value of the docPpalTipo property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDocPpalTipo() {
        return docPpalTipo;
    }

    /**
     * Sets the value of the docPpalTipo property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDocPpalTipo(JAXBElement<String> value) {
        this.docPpalTipo = value;
    }

    /**
     * Gets the value of the domPpalTipoVia property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDomPpalTipoVia() {
        return domPpalTipoVia;
    }

    /**
     * Sets the value of the domPpalTipoVia property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDomPpalTipoVia(JAXBElement<String> value) {
        this.domPpalTipoVia = value;
    }

    /**
     * Gets the value of the domPpalTiposDomicilios property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDomPpalTiposDomicilios() {
        return domPpalTiposDomicilios;
    }

    /**
     * Sets the value of the domPpalTiposDomicilios property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDomPpalTiposDomicilios(JAXBElement<String> value) {
        this.domPpalTiposDomicilios = value;
    }

    /**
     * Gets the value of the domLabCalle property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDomLabCalle() {
        return domLabCalle;
    }

    /**
     * Sets the value of the domLabCalle property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDomLabCalle(JAXBElement<String> value) {
        this.domLabCalle = value;
    }

    /**
     * Gets the value of the domLabCampoVar1 property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDomLabCampoVar1() {
        return domLabCampoVar1;
    }

    /**
     * Sets the value of the domLabCampoVar1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDomLabCampoVar1(JAXBElement<String> value) {
        this.domLabCampoVar1 = value;
    }

    /**
     * Gets the value of the domLabCampoVar2 property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDomLabCampoVar2() {
        return domLabCampoVar2;
    }

    /**
     * Sets the value of the domLabCampoVar2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDomLabCampoVar2(JAXBElement<String> value) {
        this.domLabCampoVar2 = value;
    }

    /**
     * Gets the value of the domLabCodPais property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDomLabCodPais() {
        return domLabCodPais;
    }

    /**
     * Sets the value of the domLabCodPais property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDomLabCodPais(JAXBElement<String> value) {
        this.domLabCodPais = value;
    }

    /**
     * Gets the value of the domLabCodPostal11 property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDomLabCodPostal11() {
        return domLabCodPostal11;
    }

    /**
     * Sets the value of the domLabCodPostal11 property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDomLabCodPostal11(JAXBElement<String> value) {
        this.domLabCodPostal11 = value;
    }

    /**
     * Gets the value of the domLabCodPostal24 property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDomLabCodPostal24() {
        return domLabCodPostal24;
    }

    /**
     * Sets the value of the domLabCodPostal24 property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDomLabCodPostal24(JAXBElement<String> value) {
        this.domLabCodPostal24 = value;
    }

    /**
     * Gets the value of the domLabCodPostal63 property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDomLabCodPostal63() {
        return domLabCodPostal63;
    }

    /**
     * Sets the value of the domLabCodPostal63 property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDomLabCodPostal63(JAXBElement<String> value) {
        this.domLabCodPostal63 = value;
    }

    /**
     * Gets the value of the domLabCodProvincia property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDomLabCodProvincia() {
        return domLabCodProvincia;
    }

    /**
     * Sets the value of the domLabCodProvincia property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDomLabCodProvincia(JAXBElement<String> value) {
        this.domLabCodProvincia = value;
    }

    /**
     * Gets the value of the domLabComuna property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDomLabComuna() {
        return domLabComuna;
    }

    /**
     * Sets the value of the domLabComuna property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDomLabComuna(JAXBElement<String> value) {
        this.domLabComuna = value;
    }

    /**
     * Gets the value of the domLabDepto property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDomLabDepto() {
        return domLabDepto;
    }

    /**
     * Sets the value of the domLabDepto property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDomLabDepto(JAXBElement<String> value) {
        this.domLabDepto = value;
    }

    /**
     * Gets the value of the domLabDescComuna property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDomLabDescComuna() {
        return domLabDescComuna;
    }

    /**
     * Sets the value of the domLabDescComuna property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDomLabDescComuna(JAXBElement<String> value) {
        this.domLabDescComuna = value;
    }

    /**
     * Gets the value of the domLabDescLocalidad property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDomLabDescLocalidad() {
        return domLabDescLocalidad;
    }

    /**
     * Sets the value of the domLabDescLocalidad property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDomLabDescLocalidad(JAXBElement<String> value) {
        this.domLabDescLocalidad = value;
    }

    /**
     * Gets the value of the domLabEntreCalle property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDomLabEntreCalle() {
        return domLabEntreCalle;
    }

    /**
     * Sets the value of the domLabEntreCalle property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDomLabEntreCalle(JAXBElement<String> value) {
        this.domLabEntreCalle = value;
    }

    /**
     * Gets the value of the domLabFecVerificacion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDomLabFecVerificacion() {
        return domLabFecVerificacion;
    }

    /**
     * Sets the value of the domLabFecVerificacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDomLabFecVerificacion(JAXBElement<String> value) {
        this.domLabFecVerificacion = value;
    }

    /**
     * Gets the value of the domLabLocalidad property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDomLabLocalidad() {
        return domLabLocalidad;
    }

    /**
     * Sets the value of the domLabLocalidad property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDomLabLocalidad(JAXBElement<String> value) {
        this.domLabLocalidad = value;
    }

    /**
     * Gets the value of the domLabManzana property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDomLabManzana() {
        return domLabManzana;
    }

    /**
     * Sets the value of the domLabManzana property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDomLabManzana(JAXBElement<String> value) {
        this.domLabManzana = value;
    }

    /**
     * Gets the value of the domLabMarcaCorrespondenciaDevuelta property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDomLabMarcaCorrespondenciaDevuelta() {
        return domLabMarcaCorrespondenciaDevuelta;
    }

    /**
     * Sets the value of the domLabMarcaCorrespondenciaDevuelta property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDomLabMarcaCorrespondenciaDevuelta(JAXBElement<String> value) {
        this.domLabMarcaCorrespondenciaDevuelta = value;
    }

    /**
     * Gets the value of the domLabMarcaDomicilioErroneo property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDomLabMarcaDomicilioErroneo() {
        return domLabMarcaDomicilioErroneo;
    }

    /**
     * Sets the value of the domLabMarcaDomicilioErroneo property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDomLabMarcaDomicilioErroneo(JAXBElement<String> value) {
        this.domLabMarcaDomicilioErroneo = value;
    }

    /**
     * Gets the value of the domLabMarcaNormalizacion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDomLabMarcaNormalizacion() {
        return domLabMarcaNormalizacion;
    }

    /**
     * Sets the value of the domLabMarcaNormalizacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDomLabMarcaNormalizacion(JAXBElement<String> value) {
        this.domLabMarcaNormalizacion = value;
    }

    /**
     * Gets the value of the domLabMotivoDevolucion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDomLabMotivoDevolucion() {
        return domLabMotivoDevolucion;
    }

    /**
     * Sets the value of the domLabMotivoDevolucion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDomLabMotivoDevolucion(JAXBElement<String> value) {
        this.domLabMotivoDevolucion = value;
    }

    /**
     * Gets the value of the domLabNro property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDomLabNro() {
        return domLabNro;
    }

    /**
     * Sets the value of the domLabNro property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDomLabNro(JAXBElement<String> value) {
        this.domLabNro = value;
    }

    /**
     * Gets the value of the domLabNroEscalera property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDomLabNroEscalera() {
        return domLabNroEscalera;
    }

    /**
     * Sets the value of the domLabNroEscalera property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDomLabNroEscalera(JAXBElement<String> value) {
        this.domLabNroEscalera = value;
    }

    /**
     * Gets the value of the domLabNroPortal property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDomLabNroPortal() {
        return domLabNroPortal;
    }

    /**
     * Sets the value of the domLabNroPortal property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDomLabNroPortal(JAXBElement<String> value) {
        this.domLabNroPortal = value;
    }

    /**
     * Gets the value of the domLabNroSecuencia property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDomLabNroSecuencia() {
        return domLabNroSecuencia;
    }

    /**
     * Sets the value of the domLabNroSecuencia property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDomLabNroSecuencia(JAXBElement<String> value) {
        this.domLabNroSecuencia = value;
    }

    /**
     * Gets the value of the domLabPiso property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDomLabPiso() {
        return domLabPiso;
    }

    /**
     * Sets the value of the domLabPiso property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDomLabPiso(JAXBElement<String> value) {
        this.domLabPiso = value;
    }

    /**
     * Gets the value of the domLabRutaCartero property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDomLabRutaCartero() {
        return domLabRutaCartero;
    }

    /**
     * Sets the value of the domLabRutaCartero property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDomLabRutaCartero(JAXBElement<String> value) {
        this.domLabRutaCartero = value;
    }

    /**
     * Gets the value of the domLabSucCasillaCorreos property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDomLabSucCasillaCorreos() {
        return domLabSucCasillaCorreos;
    }

    /**
     * Sets the value of the domLabSucCasillaCorreos property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDomLabSucCasillaCorreos(JAXBElement<String> value) {
        this.domLabSucCasillaCorreos = value;
    }

    /**
     * Gets the value of the domLabSucCorreo property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDomLabSucCorreo() {
        return domLabSucCorreo;
    }

    /**
     * Sets the value of the domLabSucCorreo property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDomLabSucCorreo(JAXBElement<String> value) {
        this.domLabSucCorreo = value;
    }

    /**
     * Gets the value of the domLabTipoConstruccion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDomLabTipoConstruccion() {
        return domLabTipoConstruccion;
    }

    /**
     * Sets the value of the domLabTipoConstruccion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDomLabTipoConstruccion(JAXBElement<String> value) {
        this.domLabTipoConstruccion = value;
    }

    /**
     * Gets the value of the domLabTipoDomicilio property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDomLabTipoDomicilio() {
        return domLabTipoDomicilio;
    }

    /**
     * Sets the value of the domLabTipoDomicilio property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDomLabTipoDomicilio(JAXBElement<String> value) {
        this.domLabTipoDomicilio = value;
    }

    /**
     * Gets the value of the domLabTipoNucleoUrbano property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDomLabTipoNucleoUrbano() {
        return domLabTipoNucleoUrbano;
    }

    /**
     * Sets the value of the domLabTipoNucleoUrbano property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDomLabTipoNucleoUrbano(JAXBElement<String> value) {
        this.domLabTipoNucleoUrbano = value;
    }

    /**
     * Gets the value of the domLabTipoVia property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDomLabTipoVia() {
        return domLabTipoVia;
    }

    /**
     * Sets the value of the domLabTipoVia property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDomLabTipoVia(JAXBElement<String> value) {
        this.domLabTipoVia = value;
    }

    /**
     * Gets the value of the domLabTitularidad property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDomLabTitularidad() {
        return domLabTitularidad;
    }

    /**
     * Sets the value of the domLabTitularidad property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDomLabTitularidad(JAXBElement<String> value) {
        this.domLabTitularidad = value;
    }

    /**
     * Gets the value of the domLabTorre property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDomLabTorre() {
        return domLabTorre;
    }

    /**
     * Sets the value of the domLabTorre property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDomLabTorre(JAXBElement<String> value) {
        this.domLabTorre = value;
    }

    /**
     * Gets the value of the domLabYCalle property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDomLabYCalle() {
        return domLabYCalle;
    }

    /**
     * Sets the value of the domLabYCalle property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDomLabYCalle(JAXBElement<String> value) {
        this.domLabYCalle = value;
    }

    /**
     * Gets the value of the domPpalCalle property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDomPpalCalle() {
        return domPpalCalle;
    }

    /**
     * Sets the value of the domPpalCalle property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDomPpalCalle(JAXBElement<String> value) {
        this.domPpalCalle = value;
    }

    /**
     * Gets the value of the domPpalCampoVar1 property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDomPpalCampoVar1() {
        return domPpalCampoVar1;
    }

    /**
     * Sets the value of the domPpalCampoVar1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDomPpalCampoVar1(JAXBElement<String> value) {
        this.domPpalCampoVar1 = value;
    }

    /**
     * Gets the value of the domPpalCampoVar2 property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDomPpalCampoVar2() {
        return domPpalCampoVar2;
    }

    /**
     * Sets the value of the domPpalCampoVar2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDomPpalCampoVar2(JAXBElement<String> value) {
        this.domPpalCampoVar2 = value;
    }

    /**
     * Gets the value of the domPpalCodPais property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDomPpalCodPais() {
        return domPpalCodPais;
    }

    /**
     * Sets the value of the domPpalCodPais property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDomPpalCodPais(JAXBElement<String> value) {
        this.domPpalCodPais = value;
    }

    /**
     * Gets the value of the domPpalCodPostal11 property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDomPpalCodPostal11() {
        return domPpalCodPostal11;
    }

    /**
     * Sets the value of the domPpalCodPostal11 property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDomPpalCodPostal11(JAXBElement<String> value) {
        this.domPpalCodPostal11 = value;
    }

    /**
     * Gets the value of the domPpalCodPostal24 property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDomPpalCodPostal24() {
        return domPpalCodPostal24;
    }

    /**
     * Sets the value of the domPpalCodPostal24 property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDomPpalCodPostal24(JAXBElement<String> value) {
        this.domPpalCodPostal24 = value;
    }

    /**
     * Gets the value of the domPpalCodProvincia property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDomPpalCodProvincia() {
        return domPpalCodProvincia;
    }

    /**
     * Sets the value of the domPpalCodProvincia property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDomPpalCodProvincia(JAXBElement<String> value) {
        this.domPpalCodProvincia = value;
    }

    /**
     * Gets the value of the domPpalComuna property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDomPpalComuna() {
        return domPpalComuna;
    }

    /**
     * Sets the value of the domPpalComuna property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDomPpalComuna(JAXBElement<String> value) {
        this.domPpalComuna = value;
    }

    /**
     * Gets the value of the domPpalDepto property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDomPpalDepto() {
        return domPpalDepto;
    }

    /**
     * Sets the value of the domPpalDepto property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDomPpalDepto(JAXBElement<String> value) {
        this.domPpalDepto = value;
    }

    /**
     * Gets the value of the domPpalDescComuna property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDomPpalDescComuna() {
        return domPpalDescComuna;
    }

    /**
     * Sets the value of the domPpalDescComuna property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDomPpalDescComuna(JAXBElement<String> value) {
        this.domPpalDescComuna = value;
    }

    /**
     * Gets the value of the domPpalDescLocalidad property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDomPpalDescLocalidad() {
        return domPpalDescLocalidad;
    }

    /**
     * Sets the value of the domPpalDescLocalidad property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDomPpalDescLocalidad(JAXBElement<String> value) {
        this.domPpalDescLocalidad = value;
    }

    /**
     * Gets the value of the domPpalEntreCalle property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDomPpalEntreCalle() {
        return domPpalEntreCalle;
    }

    /**
     * Sets the value of the domPpalEntreCalle property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDomPpalEntreCalle(JAXBElement<String> value) {
        this.domPpalEntreCalle = value;
    }

    /**
     * Gets the value of the domPpalFecVerificacion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDomPpalFecVerificacion() {
        return domPpalFecVerificacion;
    }

    /**
     * Sets the value of the domPpalFecVerificacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDomPpalFecVerificacion(JAXBElement<String> value) {
        this.domPpalFecVerificacion = value;
    }

    /**
     * Gets the value of the domPpalLocalidad property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDomPpalLocalidad() {
        return domPpalLocalidad;
    }

    /**
     * Sets the value of the domPpalLocalidad property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDomPpalLocalidad(JAXBElement<String> value) {
        this.domPpalLocalidad = value;
    }

    /**
     * Gets the value of the domPpalManzana property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDomPpalManzana() {
        return domPpalManzana;
    }

    /**
     * Sets the value of the domPpalManzana property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDomPpalManzana(JAXBElement<String> value) {
        this.domPpalManzana = value;
    }

    /**
     * Gets the value of the domPpalManzana63 property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDomPpalManzana63() {
        return domPpalManzana63;
    }

    /**
     * Sets the value of the domPpalManzana63 property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDomPpalManzana63(JAXBElement<String> value) {
        this.domPpalManzana63 = value;
    }

    /**
     * Gets the value of the domPpalMarcaCorrespondenciaDevuelta property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDomPpalMarcaCorrespondenciaDevuelta() {
        return domPpalMarcaCorrespondenciaDevuelta;
    }

    /**
     * Sets the value of the domPpalMarcaCorrespondenciaDevuelta property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDomPpalMarcaCorrespondenciaDevuelta(JAXBElement<String> value) {
        this.domPpalMarcaCorrespondenciaDevuelta = value;
    }

    /**
     * Gets the value of the domPpalMarcaDomicilioErroneo property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDomPpalMarcaDomicilioErroneo() {
        return domPpalMarcaDomicilioErroneo;
    }

    /**
     * Sets the value of the domPpalMarcaDomicilioErroneo property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDomPpalMarcaDomicilioErroneo(JAXBElement<String> value) {
        this.domPpalMarcaDomicilioErroneo = value;
    }

    /**
     * Gets the value of the domPpalMarcaNormalizacion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDomPpalMarcaNormalizacion() {
        return domPpalMarcaNormalizacion;
    }

    /**
     * Sets the value of the domPpalMarcaNormalizacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDomPpalMarcaNormalizacion(JAXBElement<String> value) {
        this.domPpalMarcaNormalizacion = value;
    }

    /**
     * Gets the value of the domPpalMotivoDevolucion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDomPpalMotivoDevolucion() {
        return domPpalMotivoDevolucion;
    }

    /**
     * Sets the value of the domPpalMotivoDevolucion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDomPpalMotivoDevolucion(JAXBElement<String> value) {
        this.domPpalMotivoDevolucion = value;
    }

    /**
     * Gets the value of the domPpalNro property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDomPpalNro() {
        return domPpalNro;
    }

    /**
     * Sets the value of the domPpalNro property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDomPpalNro(JAXBElement<String> value) {
        this.domPpalNro = value;
    }

    /**
     * Gets the value of the domPpalNroEscalera property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDomPpalNroEscalera() {
        return domPpalNroEscalera;
    }

    /**
     * Sets the value of the domPpalNroEscalera property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDomPpalNroEscalera(JAXBElement<String> value) {
        this.domPpalNroEscalera = value;
    }

    /**
     * Gets the value of the domPpalNroPortal property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDomPpalNroPortal() {
        return domPpalNroPortal;
    }

    /**
     * Sets the value of the domPpalNroPortal property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDomPpalNroPortal(JAXBElement<String> value) {
        this.domPpalNroPortal = value;
    }

    /**
     * Gets the value of the domPpalPiso property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDomPpalPiso() {
        return domPpalPiso;
    }

    /**
     * Sets the value of the domPpalPiso property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDomPpalPiso(JAXBElement<String> value) {
        this.domPpalPiso = value;
    }

    /**
     * Gets the value of the domPpalRutaCartero property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDomPpalRutaCartero() {
        return domPpalRutaCartero;
    }

    /**
     * Sets the value of the domPpalRutaCartero property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDomPpalRutaCartero(JAXBElement<String> value) {
        this.domPpalRutaCartero = value;
    }

    /**
     * Gets the value of the domPpalSucCasillaCorreos property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDomPpalSucCasillaCorreos() {
        return domPpalSucCasillaCorreos;
    }

    /**
     * Sets the value of the domPpalSucCasillaCorreos property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDomPpalSucCasillaCorreos(JAXBElement<String> value) {
        this.domPpalSucCasillaCorreos = value;
    }

    /**
     * Gets the value of the domPpalSucCorreo property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDomPpalSucCorreo() {
        return domPpalSucCorreo;
    }

    /**
     * Sets the value of the domPpalSucCorreo property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDomPpalSucCorreo(JAXBElement<String> value) {
        this.domPpalSucCorreo = value;
    }

    /**
     * Gets the value of the domPpalTimestampUltModif property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDomPpalTimestampUltModif() {
        return domPpalTimestampUltModif;
    }

    /**
     * Sets the value of the domPpalTimestampUltModif property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDomPpalTimestampUltModif(JAXBElement<String> value) {
        this.domPpalTimestampUltModif = value;
    }

    /**
     * Gets the value of the domPpalTipoConstruccion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDomPpalTipoConstruccion() {
        return domPpalTipoConstruccion;
    }

    /**
     * Sets the value of the domPpalTipoConstruccion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDomPpalTipoConstruccion(JAXBElement<String> value) {
        this.domPpalTipoConstruccion = value;
    }

    /**
     * Gets the value of the domPpalTipoNucleoUrbano property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDomPpalTipoNucleoUrbano() {
        return domPpalTipoNucleoUrbano;
    }

    /**
     * Sets the value of the domPpalTipoNucleoUrbano property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDomPpalTipoNucleoUrbano(JAXBElement<String> value) {
        this.domPpalTipoNucleoUrbano = value;
    }

    /**
     * Gets the value of the domPpalTitularidad property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDomPpalTitularidad() {
        return domPpalTitularidad;
    }

    /**
     * Sets the value of the domPpalTitularidad property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDomPpalTitularidad(JAXBElement<String> value) {
        this.domPpalTitularidad = value;
    }

    /**
     * Gets the value of the domPpalTorre property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDomPpalTorre() {
        return domPpalTorre;
    }

    /**
     * Sets the value of the domPpalTorre property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDomPpalTorre(JAXBElement<String> value) {
        this.domPpalTorre = value;
    }

    /**
     * Gets the value of the domPpalYCalle property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDomPpalYCalle() {
        return domPpalYCalle;
    }

    /**
     * Sets the value of the domPpalYCalle property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDomPpalYCalle(JAXBElement<String> value) {
        this.domPpalYCalle = value;
    }

    /**
     * Gets the value of the ejecutivoCuenta property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getEjecutivoCuenta() {
        return ejecutivoCuenta;
    }

    /**
     * Sets the value of the ejecutivoCuenta property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setEjecutivoCuenta(JAXBElement<String> value) {
        this.ejecutivoCuenta = value;
    }

    /**
     * Gets the value of the estadoCivil property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getEstadoCivil() {
        return estadoCivil;
    }

    /**
     * Sets the value of the estadoCivil property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setEstadoCivil(JAXBElement<String> value) {
        this.estadoCivil = value;
    }

    /**
     * Gets the value of the estadoCliente property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getEstadoCliente() {
        return estadoCliente;
    }

    /**
     * Sets the value of the estadoCliente property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setEstadoCliente(JAXBElement<String> value) {
        this.estadoCliente = value;
    }

    /**
     * Gets the value of the estrato property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getEstrato() {
        return estrato;
    }

    /**
     * Sets the value of the estrato property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setEstrato(JAXBElement<String> value) {
        this.estrato = value;
    }

    /**
     * Gets the value of the faxDomCaracteristica property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFaxDomCaracteristica() {
        return faxDomCaracteristica;
    }

    /**
     * Sets the value of the faxDomCaracteristica property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFaxDomCaracteristica(JAXBElement<String> value) {
        this.faxDomCaracteristica = value;
    }

    /**
     * Gets the value of the faxDomClaseTel property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFaxDomClaseTel() {
        return faxDomClaseTel;
    }

    /**
     * Sets the value of the faxDomClaseTel property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFaxDomClaseTel(JAXBElement<String> value) {
        this.faxDomClaseTel = value;
    }

    /**
     * Gets the value of the faxDomInternos property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFaxDomInternos() {
        return faxDomInternos;
    }

    /**
     * Sets the value of the faxDomInternos property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFaxDomInternos(JAXBElement<String> value) {
        this.faxDomInternos = value;
    }

    /**
     * Gets the value of the faxDomNro property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFaxDomNro() {
        return faxDomNro;
    }

    /**
     * Sets the value of the faxDomNro property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFaxDomNro(JAXBElement<String> value) {
        this.faxDomNro = value;
    }

    /**
     * Gets the value of the faxDomNroSecDom property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFaxDomNroSecDom() {
        return faxDomNroSecDom;
    }

    /**
     * Sets the value of the faxDomNroSecDom property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFaxDomNroSecDom(JAXBElement<String> value) {
        this.faxDomNroSecDom = value;
    }

    /**
     * Gets the value of the faxDomNroSecuencia property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFaxDomNroSecuencia() {
        return faxDomNroSecuencia;
    }

    /**
     * Sets the value of the faxDomNroSecuencia property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFaxDomNroSecuencia(JAXBElement<String> value) {
        this.faxDomNroSecuencia = value;
    }

    /**
     * Gets the value of the faxDomObservaciones property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFaxDomObservaciones() {
        return faxDomObservaciones;
    }

    /**
     * Sets the value of the faxDomObservaciones property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFaxDomObservaciones(JAXBElement<String> value) {
        this.faxDomObservaciones = value;
    }

    /**
     * Gets the value of the faxDomPrefijoLargaDistancia property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFaxDomPrefijoLargaDistancia() {
        return faxDomPrefijoLargaDistancia;
    }

    /**
     * Sets the value of the faxDomPrefijoLargaDistancia property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFaxDomPrefijoLargaDistancia(JAXBElement<String> value) {
        this.faxDomPrefijoLargaDistancia = value;
    }

    /**
     * Gets the value of the faxDomTimestampUltModif property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFaxDomTimestampUltModif() {
        return faxDomTimestampUltModif;
    }

    /**
     * Sets the value of the faxDomTimestampUltModif property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFaxDomTimestampUltModif(JAXBElement<String> value) {
        this.faxDomTimestampUltModif = value;
    }

    /**
     * Gets the value of the faxDomTipoTel property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFaxDomTipoTel() {
        return faxDomTipoTel;
    }

    /**
     * Sets the value of the faxDomTipoTel property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFaxDomTipoTel(JAXBElement<String> value) {
        this.faxDomTipoTel = value;
    }

    /**
     * Gets the value of the faxLabCaracteristica property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFaxLabCaracteristica() {
        return faxLabCaracteristica;
    }

    /**
     * Sets the value of the faxLabCaracteristica property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFaxLabCaracteristica(JAXBElement<String> value) {
        this.faxLabCaracteristica = value;
    }

    /**
     * Gets the value of the faxLabClaseTel property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFaxLabClaseTel() {
        return faxLabClaseTel;
    }

    /**
     * Sets the value of the faxLabClaseTel property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFaxLabClaseTel(JAXBElement<String> value) {
        this.faxLabClaseTel = value;
    }

    /**
     * Gets the value of the faxLabInternos property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFaxLabInternos() {
        return faxLabInternos;
    }

    /**
     * Sets the value of the faxLabInternos property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFaxLabInternos(JAXBElement<String> value) {
        this.faxLabInternos = value;
    }

    /**
     * Gets the value of the faxLabNro property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFaxLabNro() {
        return faxLabNro;
    }

    /**
     * Sets the value of the faxLabNro property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFaxLabNro(JAXBElement<String> value) {
        this.faxLabNro = value;
    }

    /**
     * Gets the value of the faxLabNroSecDom property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFaxLabNroSecDom() {
        return faxLabNroSecDom;
    }

    /**
     * Sets the value of the faxLabNroSecDom property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFaxLabNroSecDom(JAXBElement<String> value) {
        this.faxLabNroSecDom = value;
    }

    /**
     * Gets the value of the faxLabNroSecTel property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFaxLabNroSecTel() {
        return faxLabNroSecTel;
    }

    /**
     * Sets the value of the faxLabNroSecTel property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFaxLabNroSecTel(JAXBElement<String> value) {
        this.faxLabNroSecTel = value;
    }

    /**
     * Gets the value of the faxLabObservaciones property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFaxLabObservaciones() {
        return faxLabObservaciones;
    }

    /**
     * Sets the value of the faxLabObservaciones property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFaxLabObservaciones(JAXBElement<String> value) {
        this.faxLabObservaciones = value;
    }

    /**
     * Gets the value of the faxLabPrefijoLargaDistancia property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFaxLabPrefijoLargaDistancia() {
        return faxLabPrefijoLargaDistancia;
    }

    /**
     * Sets the value of the faxLabPrefijoLargaDistancia property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFaxLabPrefijoLargaDistancia(JAXBElement<String> value) {
        this.faxLabPrefijoLargaDistancia = value;
    }

    /**
     * Gets the value of the faxLabTimestampUltModif property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFaxLabTimestampUltModif() {
        return faxLabTimestampUltModif;
    }

    /**
     * Sets the value of the faxLabTimestampUltModif property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFaxLabTimestampUltModif(JAXBElement<String> value) {
        this.faxLabTimestampUltModif = value;
    }

    /**
     * Gets the value of the faxLabTipoTel property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFaxLabTipoTel() {
        return faxLabTipoTel;
    }

    /**
     * Sets the value of the faxLabTipoTel property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFaxLabTipoTel(JAXBElement<String> value) {
        this.faxLabTipoTel = value;
    }

    /**
     * Gets the value of the fecIngreso property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFecIngreso() {
        return fecIngreso;
    }

    /**
     * Sets the value of the fecIngreso property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFecIngreso(JAXBElement<String> value) {
        this.fecIngreso = value;
    }

    /**
     * Gets the value of the fechaAlta property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFechaAlta() {
        return fechaAlta;
    }

    /**
     * Sets the value of the fechaAlta property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFechaAlta(JAXBElement<String> value) {
        this.fechaAlta = value;
    }

    /**
     * Gets the value of the fechaInicioActividad property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFechaInicioActividad() {
        return fechaInicioActividad;
    }

    /**
     * Sets the value of the fechaInicioActividad property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFechaInicioActividad(JAXBElement<String> value) {
        this.fechaInicioActividad = value;
    }

    /**
     * Gets the value of the fechaNacimiento property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * Sets the value of the fechaNacimiento property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFechaNacimiento(JAXBElement<String> value) {
        this.fechaNacimiento = value;
    }

    /**
     * Gets the value of the fechaFallecimiento property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFechaFallecimiento() {
        return fechaFallecimiento;
    }

    /**
     * Sets the value of the fechaFallecimiento property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFechaFallecimiento(JAXBElement<String> value) {
        this.fechaFallecimiento = value;
    }

    /**
     * Gets the value of the fechaIngresoAEmpresa property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFechaIngresoAEmpresa() {
        return fechaIngresoAEmpresa;
    }

    /**
     * Sets the value of the fechaIngresoAEmpresa property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFechaIngresoAEmpresa(JAXBElement<String> value) {
        this.fechaIngresoAEmpresa = value;
    }

    /**
     * Gets the value of the formaJuridica property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFormaJuridica() {
        return formaJuridica;
    }

    /**
     * Sets the value of the formaJuridica property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFormaJuridica(JAXBElement<String> value) {
        this.formaJuridica = value;
    }

    /**
     * Gets the value of the gerente property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getGerente() {
        return gerente;
    }

    /**
     * Sets the value of the gerente property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setGerente(JAXBElement<String> value) {
        this.gerente = value;
    }

    /**
     * Gets the value of the idioma property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIdioma() {
        return idioma;
    }

    /**
     * Sets the value of the idioma property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIdioma(JAXBElement<String> value) {
        this.idioma = value;
    }

    /**
     * Gets the value of the ing1FecActIngreso property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIng1FecActIngreso() {
        return ing1FecActIngreso;
    }

    /**
     * Sets the value of the ing1FecActIngreso property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIng1FecActIngreso(JAXBElement<String> value) {
        this.ing1FecActIngreso = value;
    }

    /**
     * Gets the value of the ing1Importe property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIng1Importe() {
        return ing1Importe;
    }

    /**
     * Sets the value of the ing1Importe property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIng1Importe(JAXBElement<String> value) {
        this.ing1Importe = value;
    }

    /**
     * Gets the value of the ing1ImporteHasta property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIng1ImporteHasta() {
        return ing1ImporteHasta;
    }

    /**
     * Sets the value of the ing1ImporteHasta property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIng1ImporteHasta(JAXBElement<String> value) {
        this.ing1ImporteHasta = value;
    }

    /**
     * Gets the value of the ing1Moneda property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIng1Moneda() {
        return ing1Moneda;
    }

    /**
     * Sets the value of the ing1Moneda property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIng1Moneda(JAXBElement<String> value) {
        this.ing1Moneda = value;
    }

    /**
     * Gets the value of the ing1TimestampUltModif property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIng1TimestampUltModif() {
        return ing1TimestampUltModif;
    }

    /**
     * Sets the value of the ing1TimestampUltModif property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIng1TimestampUltModif(JAXBElement<String> value) {
        this.ing1TimestampUltModif = value;
    }

    /**
     * Gets the value of the ing1TipoIngreso property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIng1TipoIngreso() {
        return ing1TipoIngreso;
    }

    /**
     * Sets the value of the ing1TipoIngreso property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIng1TipoIngreso(JAXBElement<String> value) {
        this.ing1TipoIngreso = value;
    }

    /**
     * Gets the value of the ing2FecActIngreso property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIng2FecActIngreso() {
        return ing2FecActIngreso;
    }

    /**
     * Sets the value of the ing2FecActIngreso property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIng2FecActIngreso(JAXBElement<String> value) {
        this.ing2FecActIngreso = value;
    }

    /**
     * Gets the value of the ing2Importe property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIng2Importe() {
        return ing2Importe;
    }

    /**
     * Sets the value of the ing2Importe property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIng2Importe(JAXBElement<String> value) {
        this.ing2Importe = value;
    }

    /**
     * Gets the value of the ing2ImporteHasta property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIng2ImporteHasta() {
        return ing2ImporteHasta;
    }

    /**
     * Sets the value of the ing2ImporteHasta property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIng2ImporteHasta(JAXBElement<String> value) {
        this.ing2ImporteHasta = value;
    }

    /**
     * Gets the value of the ing2Moneda property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIng2Moneda() {
        return ing2Moneda;
    }

    /**
     * Sets the value of the ing2Moneda property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIng2Moneda(JAXBElement<String> value) {
        this.ing2Moneda = value;
    }

    /**
     * Gets the value of the ing2TimestampUltModif property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIng2TimestampUltModif() {
        return ing2TimestampUltModif;
    }

    /**
     * Sets the value of the ing2TimestampUltModif property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIng2TimestampUltModif(JAXBElement<String> value) {
        this.ing2TimestampUltModif = value;
    }

    /**
     * Gets the value of the ing2TipoIngreso property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIng2TipoIngreso() {
        return ing2TipoIngreso;
    }

    /**
     * Sets the value of the ing2TipoIngreso property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIng2TipoIngreso(JAXBElement<String> value) {
        this.ing2TipoIngreso = value;
    }

    /**
     * Gets the value of the ing3FecActIngreso property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIng3FecActIngreso() {
        return ing3FecActIngreso;
    }

    /**
     * Sets the value of the ing3FecActIngreso property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIng3FecActIngreso(JAXBElement<String> value) {
        this.ing3FecActIngreso = value;
    }

    /**
     * Gets the value of the ing3Importe property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIng3Importe() {
        return ing3Importe;
    }

    /**
     * Sets the value of the ing3Importe property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIng3Importe(JAXBElement<String> value) {
        this.ing3Importe = value;
    }

    /**
     * Gets the value of the ing3ImporteHasta property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIng3ImporteHasta() {
        return ing3ImporteHasta;
    }

    /**
     * Sets the value of the ing3ImporteHasta property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIng3ImporteHasta(JAXBElement<String> value) {
        this.ing3ImporteHasta = value;
    }

    /**
     * Gets the value of the ing3Moneda property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIng3Moneda() {
        return ing3Moneda;
    }

    /**
     * Sets the value of the ing3Moneda property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIng3Moneda(JAXBElement<String> value) {
        this.ing3Moneda = value;
    }

    /**
     * Gets the value of the ing3TimestampUltModif property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIng3TimestampUltModif() {
        return ing3TimestampUltModif;
    }

    /**
     * Sets the value of the ing3TimestampUltModif property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIng3TimestampUltModif(JAXBElement<String> value) {
        this.ing3TimestampUltModif = value;
    }

    /**
     * Gets the value of the ing3TipoIngreso property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIng3TipoIngreso() {
        return ing3TipoIngreso;
    }

    /**
     * Sets the value of the ing3TipoIngreso property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIng3TipoIngreso(JAXBElement<String> value) {
        this.ing3TipoIngreso = value;
    }

    /**
     * Gets the value of the jefeArea property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getJefeArea() {
        return jefeArea;
    }

    /**
     * Sets the value of the jefeArea property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setJefeArea(JAXBElement<String> value) {
        this.jefeArea = value;
    }

    /**
     * Gets the value of the lugarNacimiento property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getLugarNacimiento() {
        return lugarNacimiento;
    }

    /**
     * Sets the value of the lugarNacimiento property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setLugarNacimiento(JAXBElement<String> value) {
        this.lugarNacimiento = value;
    }

    /**
     * Gets the value of the marcaClienteBancaPrivada property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getMarcaClienteBancaPrivada() {
        return marcaClienteBancaPrivada;
    }

    /**
     * Sets the value of the marcaClienteBancaPrivada property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setMarcaClienteBancaPrivada(JAXBElement<String> value) {
        this.marcaClienteBancaPrivada = value;
    }

    /**
     * Gets the value of the marcaRelacionDependencia property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getMarcaRelacionDependencia() {
        return marcaRelacionDependencia;
    }

    /**
     * Sets the value of the marcaRelacionDependencia property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setMarcaRelacionDependencia(JAXBElement<String> value) {
        this.marcaRelacionDependencia = value;
    }

    /**
     * Gets the value of the marcaEmpleado property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getMarcaEmpleado() {
        return marcaEmpleado;
    }

    /**
     * Sets the value of the marcaEmpleado property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setMarcaEmpleado(JAXBElement<String> value) {
        this.marcaEmpleado = value;
    }

    /**
     * Gets the value of the nup2 property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNUP2() {
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
    public void setNUP2(JAXBElement<String> value) {
        this.nup2 = value;
    }

    /**
     * Gets the value of the nacionalidad property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNacionalidad() {
        return nacionalidad;
    }

    /**
     * Sets the value of the nacionalidad property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNacionalidad(JAXBElement<String> value) {
        this.nacionalidad = value;
    }

    /**
     * Gets the value of the naturalezaJuridica property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNaturalezaJuridica() {
        return naturalezaJuridica;
    }

    /**
     * Sets the value of the naturalezaJuridica property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNaturalezaJuridica(JAXBElement<String> value) {
        this.naturalezaJuridica = value;
    }

    /**
     * Gets the value of the nivelAcceso property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNivelAcceso() {
        return nivelAcceso;
    }

    /**
     * Sets the value of the nivelAcceso property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNivelAcceso(JAXBElement<String> value) {
        this.nivelAcceso = value;
    }

    /**
     * Gets the value of the nivelEstudios property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNivelEstudios() {
        return nivelEstudios;
    }

    /**
     * Sets the value of the nivelEstudios property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNivelEstudios(JAXBElement<String> value) {
        this.nivelEstudios = value;
    }

    /**
     * Gets the value of the nombre property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNombre() {
        return nombre;
    }

    /**
     * Sets the value of the nombre property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNombre(JAXBElement<String> value) {
        this.nombre = value;
    }

    /**
     * Gets the value of the nombreFantasia property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNombreFantasia() {
        return nombreFantasia;
    }

    /**
     * Sets the value of the nombreFantasia property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNombreFantasia(JAXBElement<String> value) {
        this.nombreFantasia = value;
    }

    /**
     * Gets the value of the nombreEmpresa property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNombreEmpresa() {
        return nombreEmpresa;
    }

    /**
     * Sets the value of the nombreEmpresa property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNombreEmpresa(JAXBElement<String> value) {
        this.nombreEmpresa = value;
    }

    /**
     * Gets the value of the numeroDocumentoConyuge property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNumeroDocumentoConyuge() {
        return numeroDocumentoConyuge;
    }

    /**
     * Sets the value of the numeroDocumentoConyuge property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNumeroDocumentoConyuge(JAXBElement<String> value) {
        this.numeroDocumentoConyuge = value;
    }

    /**
     * Gets the value of the numeroHijos property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNumeroHijos() {
        return numeroHijos;
    }

    /**
     * Sets the value of the numeroHijos property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNumeroHijos(JAXBElement<String> value) {
        this.numeroHijos = value;
    }

    /**
     * Gets the value of the oficial1 property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOficial1() {
        return oficial1;
    }

    /**
     * Sets the value of the oficial1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOficial1(JAXBElement<String> value) {
        this.oficial1 = value;
    }

    /**
     * Gets the value of the oficial2 property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOficial2() {
        return oficial2;
    }

    /**
     * Sets the value of the oficial2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOficial2(JAXBElement<String> value) {
        this.oficial2 = value;
    }

    /**
     * Gets the value of the oficial3 property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOficial3() {
        return oficial3;
    }

    /**
     * Sets the value of the oficial3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOficial3(JAXBElement<String> value) {
        this.oficial3 = value;
    }

    /**
     * Gets the value of the oficial4 property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOficial4() {
        return oficial4;
    }

    /**
     * Sets the value of the oficial4 property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOficial4(JAXBElement<String> value) {
        this.oficial4 = value;
    }

    /**
     * Gets the value of the oficial5 property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOficial5() {
        return oficial5;
    }

    /**
     * Sets the value of the oficial5 property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOficial5(JAXBElement<String> value) {
        this.oficial5 = value;
    }

    /**
     * Gets the value of the paisNacimiento property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPaisNacimiento() {
        return paisNacimiento;
    }

    /**
     * Sets the value of the paisNacimiento property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPaisNacimiento(JAXBElement<String> value) {
        this.paisNacimiento = value;
    }

    /**
     * Gets the value of the paisResidencia property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPaisResidencia() {
        return paisResidencia;
    }

    /**
     * Sets the value of the paisResidencia property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPaisResidencia(JAXBElement<String> value) {
        this.paisResidencia = value;
    }

    /**
     * Gets the value of the personasCargo property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPersonasCargo() {
        return personasCargo;
    }

    /**
     * Sets the value of the personasCargo property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPersonasCargo(JAXBElement<String> value) {
        this.personasCargo = value;
    }

    /**
     * Gets the value of the pertenenciaGrupo property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPertenenciaGrupo() {
        return pertenenciaGrupo;
    }

    /**
     * Sets the value of the pertenenciaGrupo property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPertenenciaGrupo(JAXBElement<String> value) {
        this.pertenenciaGrupo = value;
    }

    /**
     * Gets the value of the profesion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getProfesion() {
        return profesion;
    }

    /**
     * Sets the value of the profesion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setProfesion(JAXBElement<String> value) {
        this.profesion = value;
    }

    /**
     * Gets the value of the ramoEmpresa property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getRamoEmpresa() {
        return ramoEmpresa;
    }

    /**
     * Sets the value of the ramoEmpresa property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setRamoEmpresa(JAXBElement<String> value) {
        this.ramoEmpresa = value;
    }

    /**
     * Gets the value of the regimenMatrimonial property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getRegimenMatrimonial() {
        return regimenMatrimonial;
    }

    /**
     * Sets the value of the regimenMatrimonial property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setRegimenMatrimonial(JAXBElement<String> value) {
        this.regimenMatrimonial = value;
    }

    /**
     * Gets the value of the relacionesEntrePersonas property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getRelacionesEntrePersonas() {
        return relacionesEntrePersonas;
    }

    /**
     * Sets the value of the relacionesEntrePersonas property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setRelacionesEntrePersonas(JAXBElement<String> value) {
        this.relacionesEntrePersonas = value;
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
     * Gets the value of the secuenciaDomEmpresaEmpleadora property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSecuenciaDomEmpresaEmpleadora() {
        return secuenciaDomEmpresaEmpleadora;
    }

    /**
     * Sets the value of the secuenciaDomEmpresaEmpleadora property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSecuenciaDomEmpresaEmpleadora(JAXBElement<String> value) {
        this.secuenciaDomEmpresaEmpleadora = value;
    }

    /**
     * Gets the value of the secuenciaTelEmpresaEmpleadora property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSecuenciaTelEmpresaEmpleadora() {
        return secuenciaTelEmpresaEmpleadora;
    }

    /**
     * Sets the value of the secuenciaTelEmpresaEmpleadora property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSecuenciaTelEmpresaEmpleadora(JAXBElement<String> value) {
        this.secuenciaTelEmpresaEmpleadora = value;
    }

    /**
     * Gets the value of the sexo property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSexo() {
        return sexo;
    }

    /**
     * Sets the value of the sexo property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSexo(JAXBElement<String> value) {
        this.sexo = value;
    }

    /**
     * Gets the value of the subSegmento property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSubSegmento() {
        return subSegmento;
    }

    /**
     * Sets the value of the subSegmento property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSubSegmento(JAXBElement<String> value) {
        this.subSegmento = value;
    }

    /**
     * Gets the value of the sucAdministradora property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSucAdministradora() {
        return sucAdministradora;
    }

    /**
     * Sets the value of the sucAdministradora property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSucAdministradora(JAXBElement<String> value) {
        this.sucAdministradora = value;
    }

    /**
     * Gets the value of the teamLeader property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTeamLeader() {
        return teamLeader;
    }

    /**
     * Sets the value of the teamLeader property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTeamLeader(JAXBElement<String> value) {
        this.teamLeader = value;
    }

    /**
     * Gets the value of the telLabCaracteristica property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTelLabCaracteristica() {
        return telLabCaracteristica;
    }

    /**
     * Sets the value of the telLabCaracteristica property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTelLabCaracteristica(JAXBElement<String> value) {
        this.telLabCaracteristica = value;
    }

    /**
     * Gets the value of the telLabClaseTel property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTelLabClaseTel() {
        return telLabClaseTel;
    }

    /**
     * Sets the value of the telLabClaseTel property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTelLabClaseTel(JAXBElement<String> value) {
        this.telLabClaseTel = value;
    }

    /**
     * Gets the value of the telLabInternos property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTelLabInternos() {
        return telLabInternos;
    }

    /**
     * Sets the value of the telLabInternos property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTelLabInternos(JAXBElement<String> value) {
        this.telLabInternos = value;
    }

    /**
     * Gets the value of the telLabNro property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTelLabNro() {
        return telLabNro;
    }

    /**
     * Sets the value of the telLabNro property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTelLabNro(JAXBElement<String> value) {
        this.telLabNro = value;
    }

    /**
     * Gets the value of the telLabNroSecDom property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTelLabNroSecDom() {
        return telLabNroSecDom;
    }

    /**
     * Sets the value of the telLabNroSecDom property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTelLabNroSecDom(JAXBElement<String> value) {
        this.telLabNroSecDom = value;
    }

    /**
     * Gets the value of the telLabNroSecuencia property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTelLabNroSecuencia() {
        return telLabNroSecuencia;
    }

    /**
     * Sets the value of the telLabNroSecuencia property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTelLabNroSecuencia(JAXBElement<String> value) {
        this.telLabNroSecuencia = value;
    }

    /**
     * Gets the value of the telLabObservaciones property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTelLabObservaciones() {
        return telLabObservaciones;
    }

    /**
     * Sets the value of the telLabObservaciones property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTelLabObservaciones(JAXBElement<String> value) {
        this.telLabObservaciones = value;
    }

    /**
     * Gets the value of the telLabPrefijoLargaDistancia property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTelLabPrefijoLargaDistancia() {
        return telLabPrefijoLargaDistancia;
    }

    /**
     * Sets the value of the telLabPrefijoLargaDistancia property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTelLabPrefijoLargaDistancia(JAXBElement<String> value) {
        this.telLabPrefijoLargaDistancia = value;
    }

    /**
     * Gets the value of the telLabTimestampUltModif property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTelLabTimestampUltModif() {
        return telLabTimestampUltModif;
    }

    /**
     * Sets the value of the telLabTimestampUltModif property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTelLabTimestampUltModif(JAXBElement<String> value) {
        this.telLabTimestampUltModif = value;
    }

    /**
     * Gets the value of the telLabTipoTel property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTelLabTipoTel() {
        return telLabTipoTel;
    }

    /**
     * Sets the value of the telLabTipoTel property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTelLabTipoTel(JAXBElement<String> value) {
        this.telLabTipoTel = value;
    }

    /**
     * Gets the value of the telPpalCaracteristicaTelefonica property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTelPpalCaracteristicaTelefonica() {
        return telPpalCaracteristicaTelefonica;
    }

    /**
     * Sets the value of the telPpalCaracteristicaTelefonica property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTelPpalCaracteristicaTelefonica(JAXBElement<String> value) {
        this.telPpalCaracteristicaTelefonica = value;
    }

    /**
     * Gets the value of the telPpalClaseTel property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTelPpalClaseTel() {
        return telPpalClaseTel;
    }

    /**
     * Sets the value of the telPpalClaseTel property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTelPpalClaseTel(JAXBElement<String> value) {
        this.telPpalClaseTel = value;
    }

    /**
     * Gets the value of the telPpalInternos property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTelPpalInternos() {
        return telPpalInternos;
    }

    /**
     * Sets the value of the telPpalInternos property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTelPpalInternos(JAXBElement<String> value) {
        this.telPpalInternos = value;
    }

    /**
     * Gets the value of the telPpalNro property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTelPpalNro() {
        return telPpalNro;
    }

    /**
     * Sets the value of the telPpalNro property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTelPpalNro(JAXBElement<String> value) {
        this.telPpalNro = value;
    }

    /**
     * Gets the value of the telPpalObservaciones property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTelPpalObservaciones() {
        return telPpalObservaciones;
    }

    /**
     * Sets the value of the telPpalObservaciones property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTelPpalObservaciones(JAXBElement<String> value) {
        this.telPpalObservaciones = value;
    }

    /**
     * Gets the value of the telPpalPrefijoLargaDistancia property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTelPpalPrefijoLargaDistancia() {
        return telPpalPrefijoLargaDistancia;
    }

    /**
     * Sets the value of the telPpalPrefijoLargaDistancia property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTelPpalPrefijoLargaDistancia(JAXBElement<String> value) {
        this.telPpalPrefijoLargaDistancia = value;
    }

    /**
     * Gets the value of the telPpalSecuenciaDom property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTelPpalSecuenciaDom() {
        return telPpalSecuenciaDom;
    }

    /**
     * Sets the value of the telPpalSecuenciaDom property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTelPpalSecuenciaDom(JAXBElement<String> value) {
        this.telPpalSecuenciaDom = value;
    }

    /**
     * Gets the value of the telPpalTipoTel property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTelPpalTipoTel() {
        return telPpalTipoTel;
    }

    /**
     * Sets the value of the telPpalTipoTel property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTelPpalTipoTel(JAXBElement<String> value) {
        this.telPpalTipoTel = value;
    }

    /**
     * Gets the value of the tenerAvisos property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTenerAvisos() {
        return tenerAvisos;
    }

    /**
     * Sets the value of the tenerAvisos property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTenerAvisos(JAXBElement<String> value) {
        this.tenerAvisos = value;
    }

    /**
     * Gets the value of the tenerContratos property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTenerContratos() {
        return tenerContratos;
    }

    /**
     * Sets the value of the tenerContratos property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTenerContratos(JAXBElement<String> value) {
        this.tenerContratos = value;
    }

    /**
     * Gets the value of the tiempoResidenciaPais property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTiempoResidenciaPais() {
        return tiempoResidenciaPais;
    }

    /**
     * Sets the value of the tiempoResidenciaPais property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTiempoResidenciaPais(JAXBElement<String> value) {
        this.tiempoResidenciaPais = value;
    }

    /**
     * Gets the value of the timestampUltModifDatosBasicos property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTimestampUltModifDatosBasicos() {
        return timestampUltModifDatosBasicos;
    }

    /**
     * Sets the value of the timestampUltModifDatosBasicos property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTimestampUltModifDatosBasicos(JAXBElement<String> value) {
        this.timestampUltModifDatosBasicos = value;
    }

    /**
     * Gets the value of the timestampCodActividad property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTimestampCodActividad() {
        return timestampCodActividad;
    }

    /**
     * Sets the value of the timestampCodActividad property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTimestampCodActividad(JAXBElement<String> value) {
        this.timestampCodActividad = value;
    }

    /**
     * Gets the value of the timestampUltModifDatosCompPf property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTimestampUltModifDatosCompPf() {
        return timestampUltModifDatosCompPf;
    }

    /**
     * Sets the value of the timestampUltModifDatosCompPf property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTimestampUltModifDatosCompPf(JAXBElement<String> value) {
        this.timestampUltModifDatosCompPf = value;
    }

    /**
     * Gets the value of the timestampUltModifDomLab property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTimestampUltModifDomLab() {
        return timestampUltModifDomLab;
    }

    /**
     * Sets the value of the timestampUltModifDomLab property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTimestampUltModifDomLab(JAXBElement<String> value) {
        this.timestampUltModifDomLab = value;
    }

    /**
     * Gets the value of the timestampUltModifTelPpal property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTimestampUltModifTelPpal() {
        return timestampUltModifTelPpal;
    }

    /**
     * Sets the value of the timestampUltModifTelPpal property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTimestampUltModifTelPpal(JAXBElement<String> value) {
        this.timestampUltModifTelPpal = value;
    }

    /**
     * Gets the value of the timestampUltModifUnidadControl property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTimestampUltModifUnidadControl() {
        return timestampUltModifUnidadControl;
    }

    /**
     * Sets the value of the timestampUltModifUnidadControl property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTimestampUltModifUnidadControl(JAXBElement<String> value) {
        this.timestampUltModifUnidadControl = value;
    }

    /**
     * Gets the value of the tipoOcupacion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTipoOcupacion() {
        return tipoOcupacion;
    }

    /**
     * Sets the value of the tipoOcupacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTipoOcupacion(JAXBElement<String> value) {
        this.tipoOcupacion = value;
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
     * Gets the value of the tipoEmpresaEmpleadora property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTipoEmpresaEmpleadora() {
        return tipoEmpresaEmpleadora;
    }

    /**
     * Sets the value of the tipoEmpresaEmpleadora property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTipoEmpresaEmpleadora(JAXBElement<String> value) {
        this.tipoEmpresaEmpleadora = value;
    }

    /**
     * Gets the value of the tipoDocumentoConyuge property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTipoDocumentoConyuge() {
        return tipoDocumentoConyuge;
    }

    /**
     * Sets the value of the tipoDocumentoConyuge property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTipoDocumentoConyuge(JAXBElement<String> value) {
        this.tipoDocumentoConyuge = value;
    }

    /**
     * Gets the value of the tipoRelacionBancoCliente property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTipoRelacionBancoCliente() {
        return tipoRelacionBancoCliente;
    }

    /**
     * Sets the value of the tipoRelacionBancoCliente property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTipoRelacionBancoCliente(JAXBElement<String> value) {
        this.tipoRelacionBancoCliente = value;
    }

    /**
     * Gets the value of the tipoRelacionLaboralCF property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTipoRelacionLaboralCF() {
        return tipoRelacionLaboralCF;
    }

    /**
     * Sets the value of the tipoRelacionLaboralCF property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTipoRelacionLaboralCF(JAXBElement<String> value) {
        this.tipoRelacionLaboralCF = value;
    }

    /**
     * Gets the value of the ultimoTituloAdquirido property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getUltimoTituloAdquirido() {
        return ultimoTituloAdquirido;
    }

    /**
     * Sets the value of the ultimoTituloAdquirido property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setUltimoTituloAdquirido(JAXBElement<String> value) {
        this.ultimoTituloAdquirido = value;
    }

    /**
     * Gets the value of the unidadNegocio property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getUnidadNegocio() {
        return unidadNegocio;
    }

    /**
     * Sets the value of the unidadNegocio property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setUnidadNegocio(JAXBElement<String> value) {
        this.unidadNegocio = value;
    }

    /**
     * Gets the value of the uso1 property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getUso1() {
        return uso1;
    }

    /**
     * Sets the value of the uso1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setUso1(JAXBElement<String> value) {
        this.uso1 = value;
    }

    /**
     * Gets the value of the uso3 property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getUso3() {
        return uso3;
    }

    /**
     * Sets the value of the uso3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setUso3(JAXBElement<String> value) {
        this.uso3 = value;
    }

    /**
     * Gets the value of the uso4 property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getUso4() {
        return uso4;
    }

    /**
     * Sets the value of the uso4 property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setUso4(JAXBElement<String> value) {
        this.uso4 = value;
    }

    /**
     * Gets the value of the uso5 property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getUso5() {
        return uso5;
    }

    /**
     * Sets the value of the uso5 property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setUso5(JAXBElement<String> value) {
        this.uso5 = value;
    }

    /**
     * Gets the value of the vinculacionGrupoSantander property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getVinculacionGrupoSantander() {
        return vinculacionGrupoSantander;
    }

    /**
     * Sets the value of the vinculacionGrupoSantander property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setVinculacionGrupoSantander(JAXBElement<String> value) {
        this.vinculacionGrupoSantander = value;
    }

}
