/*
 * 
 */
package ar.com.santanderrio.obp.servicios.sieteporveinticuatro.version1.entities;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for anonymous complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DATOSRESULTADO">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="IdSistema" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="Severidad" type="{http://www.w3.org/2001/XMLSchema}String"/>
 *                   &lt;element name="CodRet" type="{http://www.w3.org/2001/XMLSchema}String"/>
 *                   &lt;element name="CantidadDescripcionStatusResultado" type="{http://www.w3.org/2001/XMLSchema}String"/>
 *                   &lt;element name="DescripcionStatusResultado" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="TextoMensaje" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                           &lt;/sequence>
 *                           &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}String" />
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="CantidadRegistro" type="{http://www.w3.org/2001/XMLSchema}String"/>
 *                   &lt;element name="Registro" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="DatosAdicionales">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="DatosAdicionales">
 *                                         &lt;complexType>
 *                                           &lt;complexContent>
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                               &lt;sequence>
 *                                                 &lt;element name="DatosSueldos">
 *                                                   &lt;complexType>
 *                                                     &lt;complexContent>
 *                                                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                                         &lt;sequence>
 *                                                           &lt;element name="TipoPago" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                                           &lt;element name="NumeroCUIL" type="{http://www.w3.org/2001/XMLSchema}String"/>
 *                                                           &lt;element name="Destinatario" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                                           &lt;element name="DescripcionConcepto" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                                           &lt;element name="TipoCUIL" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                                         &lt;/sequence>
 *                                                       &lt;/restriction>
 *                                                     &lt;/complexContent>
 *                                                   &lt;/complexType>
 *                                                 &lt;/element>
 *                                               &lt;/sequence>
 *                                             &lt;/restriction>
 *                                           &lt;/complexContent>
 *                                         &lt;/complexType>
 *                                       &lt;/element>
 *                                     &lt;/sequence>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                             &lt;element name="NroRecurrencia" type="{http://www.w3.org/2001/XMLSchema}String"/>
 *                             &lt;element name="Version" type="{http://www.w3.org/2001/XMLSchema}String"/>
 *                             &lt;element name="TipoAgendamiento" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="Nombre" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="DATOSENTRADA">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="CodigoCredito" type="{http://www.w3.org/2001/XMLSchema}String"/>
 *                                       &lt;element name="UsuarioTipo" type="{http://www.w3.org/2001/XMLSchema}String"/>
 *                                       &lt;element name="LogueoAgendaHistorica" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                       &lt;element name="NroCtaDebito" type="{http://www.w3.org/2001/XMLSchema}String"/>
 *                                       &lt;element name="Importe" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *                                       &lt;element name="SubcodigoCredito" type="{http://www.w3.org/2001/XMLSchema}String"/>
 *                                       &lt;element name="IdSesionCnt" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                       &lt;element name="IndAuten" type="{http://www.w3.org/2001/XMLSchema}String"/>
 *                                       &lt;element name="SubcanalId" type="{http://www.w3.org/2001/XMLSchema}String"/>
 *                                       &lt;element name="UsuarioAtrib" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                       &lt;element name="FechaNac" type="{http://www.w3.org/2001/XMLSchema}String"/>
 *                                       &lt;element name="TipoPersona" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                       &lt;element name="CanalId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                       &lt;element name="ComprobanteCredito" type="{http://www.w3.org/2001/XMLSchema}String"/>
 *                                       &lt;element name="SucCtaCredito" type="{http://www.w3.org/2001/XMLSchema}String"/>
 *                                       &lt;element name="EcoDatosEntrada" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                       &lt;element name="NroCtaCredito" type="{http://www.w3.org/2001/XMLSchema}String"/>
 *                                       &lt;element name="SucCtaDebito" type="{http://www.w3.org/2001/XMLSchema}String"/>
 *                                       &lt;element name="TipoCtaDebito" type="{http://www.w3.org/2001/XMLSchema}String"/>
 *                                       &lt;element name="TipoCtaCredito" type="{http://www.w3.org/2001/XMLSchema}String"/>
 *                                       &lt;element name="TipoId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                       &lt;element name="CanalVersion" type="{http://www.w3.org/2001/XMLSchema}String"/>
 *                                       &lt;element name="IdCliente" type="{http://www.w3.org/2001/XMLSchema}String"/>
 *                                       &lt;element name="CodigoDebito" type="{http://www.w3.org/2001/XMLSchema}String"/>
 *                                       &lt;element name="LimiteSobregiro" type="{http://www.w3.org/2001/XMLSchema}String"/>
 *                                       &lt;element name="VersionXML" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                       &lt;element name="SubcodigoDebito" type="{http://www.w3.org/2001/XMLSchema}String"/>
 *                                     &lt;/sequence>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                             &lt;element name="FechaBaseRecurrencia" type="{http://www.w3.org/2001/XMLSchema}String"/>
 *                             &lt;element name="FrecRecurrencia" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="TipoRecurrencia" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="FechaAgendamiento" type="{http://www.w3.org/2001/XMLSchema}String"/>
 *                             &lt;element name="SubcanalTipo" type="{http://www.w3.org/2001/XMLSchema}String"/>
 *                             &lt;element name="MaxRecurrencia" type="{http://www.w3.org/2001/XMLSchema}String"/>
 *                             &lt;element name="FechaEjecucion" type="{http://www.w3.org/2001/XMLSchema}String"/>
 *                             &lt;element name="CanalTipo" type="{http://www.w3.org/2001/XMLSchema}String"/>
 *                             &lt;element name="IdTransaccion" type="{http://www.w3.org/2001/XMLSchema}String"/>
 *                           &lt;/sequence>
 *                           &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}String" />
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "datosresultado" })
@XmlRootElement(name = "XML")
public class XMLResponseEntity {

	/** The datosresultado. */
	@XmlElement(name = "DATOSRESULTADO", required = true)
	protected XMLResponseEntity.DATOSRESULTADO datosresultado;

	/**
	 * Gets the value of the datosresultado property.
	 * 
	 * @return possible object is {@link XMLResponseEntity.DATOSRESULTADO }
	 * 
	 */
	public XMLResponseEntity.DATOSRESULTADO getDATOSRESULTADO() {
		return datosresultado;
	}

	/**
	 * Sets the value of the datosresultado property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLResponseEntity.DATOSRESULTADO }
	 * 
	 */
	public void setDATOSRESULTADO(XMLResponseEntity.DATOSRESULTADO value) {
		this.datosresultado = value;
	}

	/**
	 * <p>
	 * Java class for anonymous complex type.
	 * 
	 * <p>
	 * The following schema fragment specifies the expected content contained
	 * within this class.
	 * 
	 * <pre>
	 * &lt;complexType>
	 *   &lt;complexContent>
	 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
	 *       &lt;sequence>
	 *         &lt;element name="IdSistema" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="Severidad" type="{http://www.w3.org/2001/XMLSchema}String"/>
	 *         &lt;element name="CodRet" type="{http://www.w3.org/2001/XMLSchema}String"/>
	 *         &lt;element name="CantidadDescripcionStatusResultado" type="{http://www.w3.org/2001/XMLSchema}String"/>
	 *         &lt;element name="DescripcionStatusResultado" maxOccurs="unbounded" minOccurs="0">
	 *           &lt;complexType>
	 *             &lt;complexContent>
	 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
	 *                 &lt;sequence>
	 *                   &lt;element name="TextoMensaje" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *                 &lt;/sequence>
	 *                 &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}String" />
	 *               &lt;/restriction>
	 *             &lt;/complexContent>
	 *           &lt;/complexType>
	 *         &lt;/element>
	 *         &lt;element name="CantidadRegistro" type="{http://www.w3.org/2001/XMLSchema}String"/>
	 *         &lt;element name="Registro" maxOccurs="unbounded" minOccurs="0">
	 *           &lt;complexType>
	 *             &lt;complexContent>
	 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
	 *                 &lt;sequence>
	 *                   &lt;element name="DatosAdicionales">
	 *                     &lt;complexType>
	 *                       &lt;complexContent>
	 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
	 *                           &lt;sequence>
	 *                             &lt;element name="DatosAdicionales">
	 *                               &lt;complexType>
	 *                                 &lt;complexContent>
	 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
	 *                                     &lt;sequence>
	 *                                       &lt;element name="DatosSueldos">
	 *                                         &lt;complexType>
	 *                                           &lt;complexContent>
	 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
	 *                                               &lt;sequence>
	 *                                                 &lt;element name="TipoPago" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *                                                 &lt;element name="NumeroCUIL" type="{http://www.w3.org/2001/XMLSchema}String"/>
	 *                                                 &lt;element name="Destinatario" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *                                                 &lt;element name="DescripcionConcepto" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *                                                 &lt;element name="TipoCUIL" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *                                               &lt;/sequence>
	 *                                             &lt;/restriction>
	 *                                           &lt;/complexContent>
	 *                                         &lt;/complexType>
	 *                                       &lt;/element>
	 *                                     &lt;/sequence>
	 *                                   &lt;/restriction>
	 *                                 &lt;/complexContent>
	 *                               &lt;/complexType>
	 *                             &lt;/element>
	 *                           &lt;/sequence>
	 *                         &lt;/restriction>
	 *                       &lt;/complexContent>
	 *                     &lt;/complexType>
	 *                   &lt;/element>
	 *                   &lt;element name="NroRecurrencia" type="{http://www.w3.org/2001/XMLSchema}String"/>
	 *                   &lt;element name="Version" type="{http://www.w3.org/2001/XMLSchema}String"/>
	 *                   &lt;element name="TipoAgendamiento" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *                   &lt;element name="Nombre" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *                   &lt;element name="DATOSENTRADA">
	 *                     &lt;complexType>
	 *                       &lt;complexContent>
	 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
	 *                           &lt;sequence>
	 *                             &lt;element name="CodigoCredito" type="{http://www.w3.org/2001/XMLSchema}String"/>
	 *                             &lt;element name="UsuarioTipo" type="{http://www.w3.org/2001/XMLSchema}String"/>
	 *                             &lt;element name="LogueoAgendaHistorica" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *                             &lt;element name="NroCtaDebito" type="{http://www.w3.org/2001/XMLSchema}String"/>
	 *                             &lt;element name="Importe" type="{http://www.w3.org/2001/XMLSchema}float"/>
	 *                             &lt;element name="SubcodigoCredito" type="{http://www.w3.org/2001/XMLSchema}String"/>
	 *                             &lt;element name="IdSesionCnt" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *                             &lt;element name="IndAuten" type="{http://www.w3.org/2001/XMLSchema}String"/>
	 *                             &lt;element name="SubcanalId" type="{http://www.w3.org/2001/XMLSchema}String"/>
	 *                             &lt;element name="UsuarioAtrib" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *                             &lt;element name="FechaNac" type="{http://www.w3.org/2001/XMLSchema}String"/>
	 *                             &lt;element name="TipoPersona" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *                             &lt;element name="CanalId" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *                             &lt;element name="ComprobanteCredito" type="{http://www.w3.org/2001/XMLSchema}String"/>
	 *                             &lt;element name="SucCtaCredito" type="{http://www.w3.org/2001/XMLSchema}String"/>
	 *                             &lt;element name="EcoDatosEntrada" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *                             &lt;element name="NroCtaCredito" type="{http://www.w3.org/2001/XMLSchema}String"/>
	 *                             &lt;element name="SucCtaDebito" type="{http://www.w3.org/2001/XMLSchema}String"/>
	 *                             &lt;element name="TipoCtaDebito" type="{http://www.w3.org/2001/XMLSchema}String"/>
	 *                             &lt;element name="TipoCtaCredito" type="{http://www.w3.org/2001/XMLSchema}String"/>
	 *                             &lt;element name="TipoId" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *                             &lt;element name="CanalVersion" type="{http://www.w3.org/2001/XMLSchema}String"/>
	 *                             &lt;element name="IdCliente" type="{http://www.w3.org/2001/XMLSchema}String"/>
	 *                             &lt;element name="CodigoDebito" type="{http://www.w3.org/2001/XMLSchema}String"/>
	 *                             &lt;element name="LimiteSobregiro" type="{http://www.w3.org/2001/XMLSchema}String"/>
	 *                             &lt;element name="VersionXML" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *                             &lt;element name="SubcodigoDebito" type="{http://www.w3.org/2001/XMLSchema}String"/>
	 *                           &lt;/sequence>
	 *                         &lt;/restriction>
	 *                       &lt;/complexContent>
	 *                     &lt;/complexType>
	 *                   &lt;/element>
	 *                   &lt;element name="FechaBaseRecurrencia" type="{http://www.w3.org/2001/XMLSchema}String"/>
	 *                   &lt;element name="FrecRecurrencia" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *                   &lt;element name="TipoRecurrencia" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *                   &lt;element name="FechaAgendamiento" type="{http://www.w3.org/2001/XMLSchema}String"/>
	 *                   &lt;element name="SubcanalTipo" type="{http://www.w3.org/2001/XMLSchema}String"/>
	 *                   &lt;element name="MaxRecurrencia" type="{http://www.w3.org/2001/XMLSchema}String"/>
	 *                   &lt;element name="FechaEjecucion" type="{http://www.w3.org/2001/XMLSchema}String"/>
	 *                   &lt;element name="CanalTipo" type="{http://www.w3.org/2001/XMLSchema}String"/>
	 *                   &lt;element name="IdTransaccion" type="{http://www.w3.org/2001/XMLSchema}String"/>
	 *                 &lt;/sequence>
	 *                 &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}String" />
	 *               &lt;/restriction>
	 *             &lt;/complexContent>
	 *           &lt;/complexType>
	 *         &lt;/element>
	 *       &lt;/sequence>
	 *     &lt;/restriction>
	 *   &lt;/complexContent>
	 * &lt;/complexType>
	 * </pre>
	 * 
	 * 
	 */
	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType(name = "", propOrder = { "idSistema", "severidad", "codRet", "cantidadDescripcionStatusResultado",
			"descripcionStatusResultado", "cantidadRegistro", "registro", "nroOpCanal", "cotizacion", "importeDestino",
			"comprobanteBackEnd", "idRecibo", "importeOrigen", "idTransaccion", "fechaCompensacion", "nroSecuencia",
			"importeDebitado", "codigoEstado", "nroComprobante" })
	public static class DATOSRESULTADO {

		/** The id sistema. */
		@XmlElement(name = "IdSistema")
		protected String idSistema;

		/** The severidad. */
		@XmlElement(name = "Severidad")
		protected String severidad;

		/** The cod ret. */
		@XmlElement(name = "CodRet")
		protected String codRet;

		/** Campos para Tx RioRio. */
		/** The nro op canal. */
		@XmlElement(name = "NroOpCanal")
		protected String nroOpCanal;

		/** The cotizacion. */
		@XmlElement(name = "Cotizacion")
		protected String cotizacion;

		/** The importe destino. */
		@XmlElement(name = "ImporteDestino")
		protected String importeDestino;

		/** The comprobante back end. */
		@XmlElement(name = "ComprobanteBackEnd")
		protected String comprobanteBackEnd;

		/** The id recibo. */
		@XmlElement(name = "IdRecibo")
		protected String idRecibo;

		/** The importe origen. */
		@XmlElement(name = "ImporteOrigen")
		protected String importeOrigen;

		/** The idtransaccion. */
		@XmlElement(name = "IdTransaccion")
		protected String idTransaccion;

		/** TODO: Campos Otros Bancos:. */

		@XmlElement(name = "FechaCompensacion")
		protected String fechaCompensacion;

		/** The nro secuencia. */
		@XmlElement(name = "NroSecuencia")
		protected String nroSecuencia;

		/** The importe debitado. */
		@XmlElement(name = "ImporteDebitado")
		protected String importeDebitado;

		/** The codigo estado. */
		@XmlElement(name = "CodigoEstado")
		protected String codigoEstado;

		/** The nro comprobante. */
		@XmlElement(name = "NroComprobante")
		protected String nroComprobante;

		/** The cantidad descripcion status resultado. */
		@XmlElement(name = "CantidadDescripcionStatusResultado")
		protected String cantidadDescripcionStatusResultado;

		/** The descripcion status resultado. */
		@XmlElement(name = "DescripcionStatusResultado")
		protected List<XMLResponseEntity.DATOSRESULTADO.DescripcionStatusResultado> descripcionStatusResultado;

		/** The cantidad registro. */
		@XmlElement(name = "CantidadRegistro")
		protected String cantidadRegistro;

		/** The registro. */
		@XmlElement(name = "Registro")
		protected List<XMLResponseEntity.DATOSRESULTADO.Registro> registro;

		/**
		 * Gets the value of the idSistema property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getIdSistema() {
			return idSistema;
		}

		/**
		 * Sets the value of the idSistema property.
		 * 
		 * @param value
		 *            allowed object is {@link String }
		 * 
		 */
		public void setIdSistema(String value) {
			this.idSistema = value;
		}

		/**
		 * Gets the value of the severidad property.
		 *
		 * @return the severidad
		 */
		public String getSeveridad() {
			return severidad;
		}

		/**
		 * Sets the value of the severidad property.
		 *
		 * @param value
		 *            the new severidad
		 */
		public void setSeveridad(String value) {
			this.severidad = value;
		}

		/**
		 * Gets the value of the codRet property.
		 *
		 * @return the cod ret
		 */
		public String getCodRet() {
			return codRet;
		}

		/**
		 * Sets the value of the codRet property.
		 *
		 * @param value
		 *            the new cod ret
		 */
		public void setCodRet(String value) {
			this.codRet = value;
		}

		/**
		 * Gets the value of the cantidadDescripcionStatusResultado property.
		 *
		 * @return the cantidad descripcion status resultado
		 */
		public String getCantidadDescripcionStatusResultado() {
			return cantidadDescripcionStatusResultado;
		}

		/**
		 * Sets the value of the cantidadDescripcionStatusResultado property.
		 *
		 * @param value
		 *            the new cantidad descripcion status resultado
		 */
		public void setCantidadDescripcionStatusResultado(String value) {
			this.cantidadDescripcionStatusResultado = value;
		}

		/**
		 * Gets the value of the descripcionStatusResultado property.
		 * 
		 * <p>
		 * This accessor method returns a reference to the live list, not a
		 * snapshot. Therefore any modification you make to the returned list
		 * will be present inside the JAXB object. This is why there is not a
		 * <CODE>set</CODE> method for the descripcionStatusResultado property.
		 * 
		 * <p>
		 * For example, to add a new item, do as follows:
		 * 
		 * <pre>
		 * getDescripcionStatusResultado().add(newItem);
		 * </pre>
		 * 
		 * 
		 * <p>
		 * Objects of the following type(s) are allowed in the list
		 * {@link XMLResponseEntity.DATOSRESULTADO.DescripcionStatusResultado }
		 *
		 * @return the descripcion status resultado
		 */
		public List<XMLResponseEntity.DATOSRESULTADO.DescripcionStatusResultado> getDescripcionStatusResultado() {
			if (descripcionStatusResultado == null) {
				descripcionStatusResultado = new ArrayList<XMLResponseEntity.DATOSRESULTADO.DescripcionStatusResultado>();
			}
			return this.descripcionStatusResultado;
		}

		/**
		 * Gets the value of the cantidadRegistro property.
		 *
		 * @return the cantidad registro
		 */
		public String getCantidadRegistro() {
			return cantidadRegistro;
		}

		/**
		 * Sets the value of the cantidadRegistro property.
		 *
		 * @param value
		 *            the new cantidad registro
		 */
		public void setCantidadRegistro(String value) {
			this.cantidadRegistro = value;
		}

		/**
		 * Gets the value of the registro property.
		 * 
		 * <p>
		 * This accessor method returns a reference to the live list, not a
		 * snapshot. Therefore any modification you make to the returned list
		 * will be present inside the JAXB object. This is why there is not a
		 * <CODE>set</CODE> method for the registro property.
		 * 
		 * <p>
		 * For example, to add a new item, do as follows:
		 * 
		 * <pre>
		 * getRegistro().add(newItem);
		 * </pre>
		 * 
		 * 
		 * <p>
		 * Objects of the following type(s) are allowed in the list
		 * {@link XMLResponseEntity.DATOSRESULTADO.Registro }
		 *
		 * @return the registro
		 */
		public List<XMLResponseEntity.DATOSRESULTADO.Registro> getRegistro() {
			if (registro == null) {
				registro = new ArrayList<XMLResponseEntity.DATOSRESULTADO.Registro>();
			}
			return this.registro;
		}

		/**
		 * <p>
		 * Java class for anonymous complex type.
		 * 
		 * <p>
		 * The following schema fragment specifies the expected content
		 * contained within this class.
		 * 
		 * <pre>
		 * &lt;complexType>
		 *   &lt;complexContent>
		 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
		 *       &lt;sequence>
		 *         &lt;element name="TextoMensaje" type="{http://www.w3.org/2001/XMLSchema}string"/>
		 *       &lt;/sequence>
		 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}String" />
		 *     &lt;/restriction>
		 *   &lt;/complexContent>
		 * &lt;/complexType>
		 * </pre>
		 * 
		 * 
		 */
		@XmlAccessorType(XmlAccessType.FIELD)
		@XmlType(name = "", propOrder = { "textoMensaje" })
		public static class DescripcionStatusResultado {

			/** The texto mensaje. */
			@XmlElement(name = "TextoMensaje", required = true)
			protected String textoMensaje;

			/** The id. */
			@XmlAttribute
			protected String id;

			/**
			 * Gets the value of the textoMensaje property.
			 * 
			 * @return possible object is {@link String }
			 * 
			 */
			public String getTextoMensaje() {
				return textoMensaje;
			}

			/**
			 * Sets the value of the textoMensaje property.
			 * 
			 * @param value
			 *            allowed object is {@link String }
			 * 
			 */
			public void setTextoMensaje(String value) {
				this.textoMensaje = value;
			}

			/**
			 * Gets the value of the id property.
			 * 
			 * @return possible object is {@link String }
			 * 
			 */
			public String getId() {
				return id;
			}

			/**
			 * Sets the value of the id property.
			 * 
			 * @param value
			 *            allowed object is {@link String }
			 * 
			 */
			public void setId(String value) {
				this.id = value;
			}

		}

		/**
		 * <p>
		 * Java class for anonymous complex type.
		 * 
		 * <p>
		 * The following schema fragment specifies the expected content
		 * contained within this class.
		 * 
		 * <pre>
		 * &lt;complexType>
		 *   &lt;complexContent>
		 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
		 *       &lt;sequence>
		 *         &lt;element name="DatosAdicionales">
		 *           &lt;complexType>
		 *             &lt;complexContent>
		 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
		 *                 &lt;sequence>
		 *                   &lt;element name="DatosAdicionales">
		 *                     &lt;complexType>
		 *                       &lt;complexContent>
		 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
		 *                           &lt;sequence>
		 *                             &lt;element name="DatosSueldos">
		 *                               &lt;complexType>
		 *                                 &lt;complexContent>
		 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
		 *                                     &lt;sequence>
		 *                                       &lt;element name="TipoPago" type="{http://www.w3.org/2001/XMLSchema}string"/>
		 *                                       &lt;element name="NumeroCUIL" type="{http://www.w3.org/2001/XMLSchema}String"/>
		 *                                       &lt;element name="Destinatario" type="{http://www.w3.org/2001/XMLSchema}string"/>
		 *                                       &lt;element name="DescripcionConcepto" type="{http://www.w3.org/2001/XMLSchema}string"/>
		 *                                       &lt;element name="TipoCUIL" type="{http://www.w3.org/2001/XMLSchema}string"/>
		 *                                     &lt;/sequence>
		 *                                   &lt;/restriction>
		 *                                 &lt;/complexContent>
		 *                               &lt;/complexType>
		 *                             &lt;/element>
		 *                           &lt;/sequence>
		 *                         &lt;/restriction>
		 *                       &lt;/complexContent>
		 *                     &lt;/complexType>
		 *                   &lt;/element>
		 *                 &lt;/sequence>
		 *               &lt;/restriction>
		 *             &lt;/complexContent>
		 *           &lt;/complexType>
		 *         &lt;/element>
		 *         &lt;element name="NroRecurrencia" type="{http://www.w3.org/2001/XMLSchema}String"/>
		 *         &lt;element name="Version" type="{http://www.w3.org/2001/XMLSchema}String"/>
		 *         &lt;element name="TipoAgendamiento" type="{http://www.w3.org/2001/XMLSchema}string"/>
		 *         &lt;element name="Nombre" type="{http://www.w3.org/2001/XMLSchema}string"/>
		 *         &lt;element name="DATOSENTRADA">
		 *           &lt;complexType>
		 *             &lt;complexContent>
		 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
		 *                 &lt;sequence>
		 *                   &lt;element name="CodigoCredito" type="{http://www.w3.org/2001/XMLSchema}String"/>
		 *                   &lt;element name="UsuarioTipo" type="{http://www.w3.org/2001/XMLSchema}String"/>
		 *                   &lt;element name="LogueoAgendaHistorica" type="{http://www.w3.org/2001/XMLSchema}string"/>
		 *                   &lt;element name="NroCtaDebito" type="{http://www.w3.org/2001/XMLSchema}String"/>
		 *                   &lt;element name="Importe" type="{http://www.w3.org/2001/XMLSchema}float"/>
		 *                   &lt;element name="SubcodigoCredito" type="{http://www.w3.org/2001/XMLSchema}String"/>
		 *                   &lt;element name="IdSesionCnt" type="{http://www.w3.org/2001/XMLSchema}string"/>
		 *                   &lt;element name="IndAuten" type="{http://www.w3.org/2001/XMLSchema}String"/>
		 *                   &lt;element name="SubcanalId" type="{http://www.w3.org/2001/XMLSchema}String"/>
		 *                   &lt;element name="UsuarioAtrib" type="{http://www.w3.org/2001/XMLSchema}string"/>
		 *                   &lt;element name="FechaNac" type="{http://www.w3.org/2001/XMLSchema}String"/>
		 *                   &lt;element name="TipoPersona" type="{http://www.w3.org/2001/XMLSchema}string"/>
		 *                   &lt;element name="CanalId" type="{http://www.w3.org/2001/XMLSchema}string"/>
		 *                   &lt;element name="ComprobanteCredito" type="{http://www.w3.org/2001/XMLSchema}String"/>
		 *                   &lt;element name="SucCtaCredito" type="{http://www.w3.org/2001/XMLSchema}String"/>
		 *                   &lt;element name="EcoDatosEntrada" type="{http://www.w3.org/2001/XMLSchema}string"/>
		 *                   &lt;element name="NroCtaCredito" type="{http://www.w3.org/2001/XMLSchema}String"/>
		 *                   &lt;element name="SucCtaDebito" type="{http://www.w3.org/2001/XMLSchema}String"/>
		 *                   &lt;element name="TipoCtaDebito" type="{http://www.w3.org/2001/XMLSchema}String"/>
		 *                   &lt;element name="TipoCtaCredito" type="{http://www.w3.org/2001/XMLSchema}String"/>
		 *                   &lt;element name="TipoId" type="{http://www.w3.org/2001/XMLSchema}string"/>
		 *                   &lt;element name="CanalVersion" type="{http://www.w3.org/2001/XMLSchema}String"/>
		 *                   &lt;element name="IdCliente" type="{http://www.w3.org/2001/XMLSchema}String"/>
		 *                   &lt;element name="CodigoDebito" type="{http://www.w3.org/2001/XMLSchema}String"/>
		 *                   &lt;element name="LimiteSobregiro" type="{http://www.w3.org/2001/XMLSchema}String"/>
		 *                   &lt;element name="VersionXML" type="{http://www.w3.org/2001/XMLSchema}string"/>
		 *                   &lt;element name="SubcodigoDebito" type="{http://www.w3.org/2001/XMLSchema}String"/>
		 *                 &lt;/sequence>
		 *               &lt;/restriction>
		 *             &lt;/complexContent>
		 *           &lt;/complexType>
		 *         &lt;/element>
		 *         &lt;element name="FechaBaseRecurrencia" type="{http://www.w3.org/2001/XMLSchema}String"/>
		 *         &lt;element name="FrecRecurrencia" type="{http://www.w3.org/2001/XMLSchema}string"/>
		 *         &lt;element name="TipoRecurrencia" type="{http://www.w3.org/2001/XMLSchema}string"/>
		 *         &lt;element name="FechaAgendamiento" type="{http://www.w3.org/2001/XMLSchema}String"/>
		 *         &lt;element name="SubcanalTipo" type="{http://www.w3.org/2001/XMLSchema}String"/>
		 *         &lt;element name="MaxRecurrencia" type="{http://www.w3.org/2001/XMLSchema}String"/>
		 *         &lt;element name="FechaEjecucion" type="{http://www.w3.org/2001/XMLSchema}String"/>
		 *         &lt;element name="CanalTipo" type="{http://www.w3.org/2001/XMLSchema}String"/>
		 *         &lt;element name="IdTransaccion" type="{http://www.w3.org/2001/XMLSchema}String"/>
		 *       &lt;/sequence>
		 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}String" />
		 *     &lt;/restriction>
		 *   &lt;/complexContent>
		 * &lt;/complexType>
		 * </pre>
		 * 
		 * 
		 */
		@XmlAccessorType(XmlAccessType.FIELD)
		@XmlType(name = "", propOrder = { "datosAdicionales", "nroRecurrencia", "version", "tipoAgendamiento", "nombre",
				"nombreGuardado", "datosentrada", "fechaBaseRecurrencia", "frecRecurrencia", "tipoRecurrencia",
				"fechaAgendamiento", "subcanalTipo", "maxRecurrencia", "fechaEjecucion", "canalTipo", "idTransaccion",
				"xmlEntrada", "xmlResultado" })
		public static class Registro {

			/** The datos adicionales. */
			@XmlElement(name = "DatosAdicionales", required = true)
			protected XMLResponseEntity.DATOSRESULTADO.Registro.DatosAdicional datosAdicionales;

			/** The nro recurrencia. */
			@XmlElement(name = "NroRecurrencia")
			protected String nroRecurrencia;

			/** The version. */
			@XmlElement(name = "Version")
			protected String version;

			/** The tipo agendamiento. */
			@XmlElement(name = "TipoAgendamiento", required = true)
			protected String tipoAgendamiento;

			/** The nombre. */
			@XmlElement(name = "Nombre", required = true)
			protected String nombre;

			/** The nombre guardado. */
			@XmlElement(name = "NombreGuardado")
			protected String nombreGuardado;

			/** The datosentrada. */
			@XmlElement(name = "DATOSENTRADA", required = true)
			protected XMLResponseEntity.DATOSRESULTADO.Registro.DATOSENTRADA datosentrada;

			/** The fecha base recurrencia. */
			@XmlElement(name = "FechaBaseRecurrencia")
			protected String fechaBaseRecurrencia;

			/** The frec recurrencia. */
			@XmlElement(name = "FrecRecurrencia", required = true)
			protected String frecRecurrencia;

			/** The tipo recurrencia. */
			@XmlElement(name = "TipoRecurrencia", required = true)
			protected String tipoRecurrencia;

			/** The fecha agendamiento. */
			@XmlElement(name = "FechaAgendamiento")
			protected String fechaAgendamiento;

			/** The subcanal tipo. */
			@XmlElement(name = "SubcanalTipo")
			protected String subcanalTipo;

			/** The max recurrencia. */
			@XmlElement(name = "MaxRecurrencia")
			protected String maxRecurrencia;

			/** The fecha ejecucion. */
			@XmlElement(name = "FechaEjecucion")
			protected String fechaEjecucion;

			/** The canal tipo. */
			@XmlElement(name = "CanalTipo")
			protected String canalTipo;

			/** The id transaccion. */
			@XmlElement(name = "IdTransaccion")
			protected String idTransaccion;

			/** The id. */
			@XmlAttribute
			protected String id;

			/** The xml entrada. */
			@XmlElement(name = "XMLEntrada")
			protected XMLResponseEntity.DATOSRESULTADO.Registro.XMLEntrada xmlEntrada;

			/** The xml resultado. */
			@XmlElement(name = "XMLResultado")
			protected XMLResponseEntity.DATOSRESULTADO.Registro.XMLResultado xmlResultado;

			/**
			 * Gets the xml entrada.
			 *
			 * @return the xml entrada
			 */
			public XMLResponseEntity.DATOSRESULTADO.Registro.XMLEntrada getXmlEntrada() {
				return xmlEntrada;
			}

			/**
			 * Sets the xml entrada.
			 *
			 * @param xmlEntrada
			 *            the new xml entrada
			 */
			public void setXmlEntrada(XMLResponseEntity.DATOSRESULTADO.Registro.XMLEntrada xmlEntrada) {
				this.xmlEntrada = xmlEntrada;
			}

			/**
			 * Gets the xml resultado.
			 *
			 * @return the xml resultado
			 */
			public XMLResponseEntity.DATOSRESULTADO.Registro.XMLResultado getXmlResultado() {
				return xmlResultado;
			}

			/**
			 * Sets the xml resultado.
			 *
			 * @param xmlResultado
			 *            the new xml resultado
			 */
			public void setXmlResultado(XMLResponseEntity.DATOSRESULTADO.Registro.XMLResultado xmlResultado) {
				this.xmlResultado = xmlResultado;
			}

			/**
			 * Gets the nombre guardado.
			 *
			 * @return the nombre guardado
			 */
			public String getNombreGuardado() {
				return nombreGuardado;
			}

			/**
			 * Sets the nombre guardado.
			 *
			 * @param nombreGuardado
			 *            the new nombre guardado
			 */
			public void setNombreGuardado(String nombreGuardado) {
				this.nombreGuardado = nombreGuardado;
			}

			/**
			 * Gets the datosentrada.
			 *
			 * @return the datosentrada
			 */
			public XMLResponseEntity.DATOSRESULTADO.Registro.DATOSENTRADA getDatosentrada() {
				return datosentrada;
			}

			/**
			 * Sets the datosentrada.
			 *
			 * @param datosentrada
			 *            the new datosentrada
			 */
			public void setDatosentrada(XMLResponseEntity.DATOSRESULTADO.Registro.DATOSENTRADA datosentrada) {
				this.datosentrada = datosentrada;
			}

			/**
			 * Gets the value of the datosAdicionales property.
			 * 
			 * @return possible object is
			 *         {@link XMLResponseEntity.DATOSRESULTADO.Registro.DatosAdicionales }
			 * 
			 */
			public XMLResponseEntity.DATOSRESULTADO.Registro.DatosAdicional getDatosAdicionales() {
				return datosAdicionales;
			}

			/**
			 * Sets the value of the datosAdicionales property.
			 * 
			 * @param value
			 *            allowed object is
			 *            {@link XMLResponseEntity.DATOSRESULTADO.Registro.DatosAdicionales }
			 * 
			 */
			public void setDatosAdicionales(XMLResponseEntity.DATOSRESULTADO.Registro.DatosAdicional value) {
				this.datosAdicionales = value;
			}

			/**
			 * Gets the value of the nroRecurrencia property.
			 *
			 * @return the nro recurrencia
			 */
			public String getNroRecurrencia() {
				return nroRecurrencia;
			}

			/**
			 * Sets the value of the nroRecurrencia property.
			 *
			 * @param value
			 *            the new nro recurrencia
			 */
			public void setNroRecurrencia(String value) {
				this.nroRecurrencia = value;
			}

			/**
			 * Gets the value of the version property.
			 *
			 * @return the version
			 */
			public String getVersion() {
				return version;
			}

			/**
			 * Sets the value of the version property.
			 *
			 * @param value
			 *            the new version
			 */
			public void setVersion(String value) {
				this.version = value;
			}

			/**
			 * Gets the value of the tipoAgendamiento property.
			 * 
			 * @return possible object is {@link String }
			 * 
			 */
			public String getTipoAgendamiento() {
				return tipoAgendamiento;
			}

			/**
			 * Sets the value of the tipoAgendamiento property.
			 * 
			 * @param value
			 *            allowed object is {@link String }
			 * 
			 */
			public void setTipoAgendamiento(String value) {
				this.tipoAgendamiento = value;
			}

			/**
			 * Gets the value of the nombre property.
			 * 
			 * @return possible object is {@link String }
			 * 
			 */
			public String getNombre() {
				return nombre;
			}

			/**
			 * Sets the value of the nombre property.
			 * 
			 * @param value
			 *            allowed object is {@link String }
			 * 
			 */
			public void setNombre(String value) {
				this.nombre = value;
			}

			/**
			 * Gets the value of the datosentrada property.
			 * 
			 * @return possible object is
			 *         {@link XMLResponseEntity.DATOSRESULTADO.Registro.DATOSENTRADA }
			 * 
			 */
			public XMLResponseEntity.DATOSRESULTADO.Registro.DATOSENTRADA getDATOSENTRADA() {
				return datosentrada;
			}

			/**
			 * Sets the value of the datosentrada property.
			 * 
			 * @param value
			 *            allowed object is
			 *            {@link XMLResponseEntity.DATOSRESULTADO.Registro.DATOSENTRADA }
			 * 
			 */
			public void setDATOSENTRADA(XMLResponseEntity.DATOSRESULTADO.Registro.DATOSENTRADA value) {
				this.datosentrada = value;
			}

			/**
			 * Gets the value of the fechaBaseRecurrencia property.
			 *
			 * @return the fecha base recurrencia
			 */
			public String getFechaBaseRecurrencia() {
				return fechaBaseRecurrencia;
			}

			/**
			 * Sets the value of the fechaBaseRecurrencia property.
			 *
			 * @param value
			 *            the new fecha base recurrencia
			 */
			public void setFechaBaseRecurrencia(String value) {
				this.fechaBaseRecurrencia = value;
			}

			/**
			 * Gets the value of the frecRecurrencia property.
			 * 
			 * @return possible object is {@link String }
			 * 
			 */
			public String getFrecRecurrencia() {
				return frecRecurrencia;
			}

			/**
			 * Sets the value of the frecRecurrencia property.
			 * 
			 * @param value
			 *            allowed object is {@link String }
			 * 
			 */
			public void setFrecRecurrencia(String value) {
				this.frecRecurrencia = value;
			}

			/**
			 * Gets the value of the tipoRecurrencia property.
			 * 
			 * @return possible object is {@link String }
			 * 
			 */
			public String getTipoRecurrencia() {
				return tipoRecurrencia;
			}

			/**
			 * Sets the value of the tipoRecurrencia property.
			 * 
			 * @param value
			 *            allowed object is {@link String }
			 * 
			 */
			public void setTipoRecurrencia(String value) {
				this.tipoRecurrencia = value;
			}

			/**
			 * Gets the value of the fechaAgendamiento property.
			 *
			 * @return the fecha agendamiento
			 */
			public String getFechaAgendamiento() {
				return fechaAgendamiento;
			}

			/**
			 * Sets the value of the fechaAgendamiento property.
			 *
			 * @param value
			 *            the new fecha agendamiento
			 */
			public void setFechaAgendamiento(String value) {
				this.fechaAgendamiento = value;
			}

			/**
			 * Gets the value of the subcanalTipo property.
			 *
			 * @return the subcanal tipo
			 */
			public String getSubcanalTipo() {
				return subcanalTipo;
			}

			/**
			 * Sets the value of the subcanalTipo property.
			 *
			 * @param value
			 *            the new subcanal tipo
			 */
			public void setSubcanalTipo(String value) {
				this.subcanalTipo = value;
			}

			/**
			 * Gets the value of the maxRecurrencia property.
			 *
			 * @return the max recurrencia
			 */
			public String getMaxRecurrencia() {
				return maxRecurrencia;
			}

			/**
			 * Sets the value of the maxRecurrencia property.
			 *
			 * @param value
			 *            the new max recurrencia
			 */
			public void setMaxRecurrencia(String value) {
				this.maxRecurrencia = value;
			}

			/**
			 * Gets the value of the fechaEjecucion property.
			 *
			 * @return the fecha ejecucion
			 */
			public String getFechaEjecucion() {
				return fechaEjecucion;
			}

			/**
			 * Sets the value of the fechaEjecucion property.
			 *
			 * @param value
			 *            the new fecha ejecucion
			 */
			public void setFechaEjecucion(String value) {
				this.fechaEjecucion = value;
			}

			/**
			 * Gets the value of the canalTipo property.
			 *
			 * @return the canal tipo
			 */
			public String getCanalTipo() {
				return canalTipo;
			}

			/**
			 * Sets the value of the canalTipo property.
			 *
			 * @param value
			 *            the new canal tipo
			 */
			public void setCanalTipo(String value) {
				this.canalTipo = value;
			}

			/**
			 * Gets the value of the idTransaccion property.
			 *
			 * @return the id transaccion
			 */
			public String getIdTransaccion() {
				return idTransaccion;
			}

			/**
			 * Sets the value of the idTransaccion property.
			 *
			 * @param value
			 *            the new id transaccion
			 */
			public void setIdTransaccion(String value) {
				this.idTransaccion = value;
			}

			/**
			 * Gets the value of the id property.
			 * 
			 * @return possible object is {@link String }
			 * 
			 */
			public String getId() {
				return id;
			}

			/**
			 * Sets the value of the id property.
			 * 
			 * @param value
			 *            allowed object is {@link String }
			 * 
			 */
			public void setId(String value) {
				this.id = value;
			}

			/**
			 * <p>
			 * Java class for anonymous complex type.
			 * 
			 * <p>
			 * The following schema fragment specifies the expected content
			 * contained within this class.
			 * 
			 * <pre>
			 * &lt;complexType>
			 *   &lt;complexContent>
			 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
			 *       &lt;sequence>
			 *         &lt;element name="CONFIG" minOccurs="0">
			 *           &lt;complexType>
			 *             &lt;complexContent>
			 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
			 *                 &lt;sequence>
			 *                   &lt;element name="VersionXML" type="{http://www.w3.org/2001/XMLSchema}string"/>
			 *                   &lt;element name="LogueoDatosRespuesta" type="{http://www.w3.org/2001/XMLSchema}string"/>
			 *                 &lt;/sequence>
			 *               &lt;/restriction>
			 *             &lt;/complexContent>
			 *           &lt;/complexType>
			 *         &lt;/element>
			 *         &lt;element name="META" minOccurs="0">
			 *           &lt;complexType>
			 *             &lt;complexContent>
			 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
			 *                 &lt;sequence>
			 *                   &lt;element name="MPCronos" type="{http://www.w3.org/2001/XMLSchema}short"/>
			 *                   &lt;element name="UsuarioId" type="{http://www.w3.org/2001/XMLSchema}string"/>
			 *                   &lt;element name="UsuarioPwd" type="{http://www.w3.org/2001/XMLSchema}string"/>
			 *                   &lt;element name="CanalTipo" type="{http://www.w3.org/2001/XMLSchema}byte"/>
			 *                   &lt;element name="IdTransaccion" type="{http://www.w3.org/2001/XMLSchema}long"/>
			 *                   &lt;element name="ModoEjecucion" type="{http://www.w3.org/2001/XMLSchema}string"/>
			 *                   &lt;element name="FechaEjecucion" type="{http://www.w3.org/2001/XMLSchema}int"/>
			 *                   &lt;element name="NUP" type="{http://www.w3.org/2001/XMLSchema}int"/>
			 *                   &lt;element name="SubcanalTipo" type="{http://www.w3.org/2001/XMLSchema}byte"/>
			 *                   &lt;element name="Nombre" type="{http://www.w3.org/2001/XMLSchema}string"/>
			 *                   &lt;element name="Version" type="{http://www.w3.org/2001/XMLSchema}short"/>
			 *                   &lt;element name="Fecha" type="{http://www.w3.org/2001/XMLSchema}int"/>
			 *                   &lt;element name="Hora" type="{http://www.w3.org/2001/XMLSchema}int"/>
			 *                   &lt;element name="NroRecurrencia" type="{http://www.w3.org/2001/XMLSchema}byte"/>
			 *                 &lt;/sequence>
			 *               &lt;/restriction>
			 *             &lt;/complexContent>
			 *           &lt;/complexType>
			 *         &lt;/element>
			 *         &lt;element name="DATOSENTRADA" minOccurs="0">
			 *           &lt;complexType>
			 *             &lt;complexContent>
			 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
			 *                 &lt;sequence>
			 *                   &lt;element name="InformacDiscrecional" type="{http://www.w3.org/2001/XMLSchema}string"/>
			 *                   &lt;element name="Cbu" type="{http://www.w3.org/2001/XMLSchema}integer"/>
			 *                   &lt;element name="CaracteristicaCtaCredito" type="{http://www.w3.org/2001/XMLSchema}byte"/>
			 *                   &lt;element name="MonedaTransferencia" type="{http://www.w3.org/2001/XMLSchema}byte"/>
			 *                   &lt;element name="LongCtaDesBane" type="{http://www.w3.org/2001/XMLSchema}byte"/>
			 *                   &lt;element name="Cuit3" type="{http://www.w3.org/2001/XMLSchema}string"/>
			 *                   &lt;element name="Cuit2" type="{http://www.w3.org/2001/XMLSchema}string"/>
			 *                   &lt;element name="UsuarioTipo" type="{http://www.w3.org/2001/XMLSchema}byte"/>
			 *                   &lt;element name="Cuit1" type="{http://www.w3.org/2001/XMLSchema}long"/>
			 *                   &lt;element name="MarcaGravado" type="{http://www.w3.org/2001/XMLSchema}string"/>
			 *                   &lt;element name="LogueoAgendaHistorica" type="{http://www.w3.org/2001/XMLSchema}string"/>
			 *                   &lt;element name="NroCtaDebito" type="{http://www.w3.org/2001/XMLSchema}int"/>
			 *                   &lt;element name="IndicadorFuncion" type="{http://www.w3.org/2001/XMLSchema}string"/>
			 *                   &lt;element name="Fiid" type="{http://www.w3.org/2001/XMLSchema}byte"/>
			 *                   &lt;element name="IdSesionCnt" type="{http://www.w3.org/2001/XMLSchema}string"/>
			 *                   &lt;element name="IndAuten" type="{http://www.w3.org/2001/XMLSchema}byte"/>
			 *                   &lt;element name="Titular" type="{http://www.w3.org/2001/XMLSchema}string"/>
			 *                   &lt;element name="TpoCtaFromBane" type="{http://www.w3.org/2001/XMLSchema}byte"/>
			 *                   &lt;element name="SubcanalId" type="{http://www.w3.org/2001/XMLSchema}byte"/>
			 *                   &lt;element name="CtaBane" type="{http://www.w3.org/2001/XMLSchema}long"/>
			 *                   &lt;element name="User" type="{http://www.w3.org/2001/XMLSchema}byte"/>
			 *                   &lt;element name="PlazoAcreditacion" type="{http://www.w3.org/2001/XMLSchema}byte"/>
			 *                   &lt;element name="DigitoCtaCredito" type="{http://www.w3.org/2001/XMLSchema}byte"/>
			 *                   &lt;element name="UsuarioAtrib" type="{http://www.w3.org/2001/XMLSchema}string"/>
			 *                   &lt;element name="FechaNac" type="{http://www.w3.org/2001/XMLSchema}int"/>
			 *                   &lt;element name="TipoPersona" type="{http://www.w3.org/2001/XMLSchema}string"/>
			 *                   &lt;element name="DescConcepto" type="{http://www.w3.org/2001/XMLSchema}string"/>
			 *                   &lt;element name="MarcaLimite" type="{http://www.w3.org/2001/XMLSchema}string"/>
			 *                   &lt;element name="ReferenciaUnivoca" type="{http://www.w3.org/2001/XMLSchema}string"/>
			 *                   &lt;element name="IdentificBeneficiario" type="{http://www.w3.org/2001/XMLSchema}long"/>
			 *                   &lt;element name="FirmanteCtaDebito" type="{http://www.w3.org/2001/XMLSchema}byte"/>
			 *                   &lt;element name="CanalId" type="{http://www.w3.org/2001/XMLSchema}string"/>
			 *                   &lt;element name="CodConcepto" type="{http://www.w3.org/2001/XMLSchema}byte"/>
			 *                   &lt;element name="SucCtaCredito" type="{http://www.w3.org/2001/XMLSchema}byte"/>
			 *                   &lt;element name="BancoReceptor" type="{http://www.w3.org/2001/XMLSchema}string"/>
			 *                   &lt;element name="IPMaquina" type="{http://www.w3.org/2001/XMLSchema}long"/>
			 *                   &lt;element name="Periodica" type="{http://www.w3.org/2001/XMLSchema}string"/>
			 *                   &lt;element name="NombreCtaCredito" type="{http://www.w3.org/2001/XMLSchema}string"/>
			 *                   &lt;element name="EcoDatosEntrada" type="{http://www.w3.org/2001/XMLSchema}string"/>
			 *                   &lt;element name="NroCtaCredito" type="{http://www.w3.org/2001/XMLSchema}long"/>
			 *                   &lt;element name="SucCtaDebito" type="{http://www.w3.org/2001/XMLSchema}byte"/>
			 *                   &lt;element name="TipoCtaDebito" type="{http://www.w3.org/2001/XMLSchema}byte"/>
			 *                   &lt;element name="CantidadDias" type="{http://www.w3.org/2001/XMLSchema}byte"/>
			 *                   &lt;element name="EntidadCtaCredito" type="{http://www.w3.org/2001/XMLSchema}byte"/>
			 *                   &lt;element name="BancoDestino" type="{http://www.w3.org/2001/XMLSchema}string"/>
			 *                   &lt;element name="ImporteTransferencia" type="{http://www.w3.org/2001/XMLSchema}float"/>
			 *                   &lt;element name="TipoId" type="{http://www.w3.org/2001/XMLSchema}string"/>
			 *                   &lt;element name="CanalVersion" type="{http://www.w3.org/2001/XMLSchema}byte"/>
			 *                   &lt;element name="IdCliente" type="{http://www.w3.org/2001/XMLSchema}int"/>
			 *                   &lt;element name="TipoTransferencia" type="{http://www.w3.org/2001/XMLSchema}byte"/>
			 *                   &lt;element name="VersionXML" type="{http://www.w3.org/2001/XMLSchema}string"/>
			 *                   &lt;element name="TpoCtaToBane" type="{http://www.w3.org/2001/XMLSchema}byte"/>
			 *                 &lt;/sequence>
			 *               &lt;/restriction>
			 *             &lt;/complexContent>
			 *           &lt;/complexType>
			 *         &lt;/element>
			 *         &lt;element name="DatosAdicionales" minOccurs="0">
			 *           &lt;complexType>
			 *             &lt;complexContent>
			 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
			 *                 &lt;sequence>
			 *                   &lt;element name="DatosMensAvisos">
			 *                     &lt;complexType>
			 *                       &lt;complexContent>
			 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
			 *                           &lt;sequence>
			 *                             &lt;element name="Concepto" type="{http://www.w3.org/2001/XMLSchema}string"/>
			 *                             &lt;element name="TitularCredito" type="{http://www.w3.org/2001/XMLSchema}string"/>
			 *                             &lt;element name="DescripcionAdicional3" type="{http://www.w3.org/2001/XMLSchema}string"/>
			 *                             &lt;element name="DescripcionAdicional2" type="{http://www.w3.org/2001/XMLSchema}string"/>
			 *                             &lt;element name="DescripcionAdicional1" type="{http://www.w3.org/2001/XMLSchema}string"/>
			 *                             &lt;element name="MailClienteReply" type="{http://www.w3.org/2001/XMLSchema}string"/>
			 *                             &lt;element name="ConceptoAdicional3" type="{http://www.w3.org/2001/XMLSchema}string"/>
			 *                             &lt;element name="ConceptoAdicional2" type="{http://www.w3.org/2001/XMLSchema}string"/>
			 *                             &lt;element name="InfoAdicional" type="{http://www.w3.org/2001/XMLSchema}string"/>
			 *                             &lt;element name="ConceptoAdicional1" type="{http://www.w3.org/2001/XMLSchema}string"/>
			 *                             &lt;element name="AnotacionesPersonales" type="{http://www.w3.org/2001/XMLSchema}string"/>
			 *                             &lt;element name="Comentario" type="{http://www.w3.org/2001/XMLSchema}string"/>
			 *                             &lt;element name="MailCredito" type="{http://www.w3.org/2001/XMLSchema}string"/>
			 *                             &lt;element name="TitularDebito" type="{http://www.w3.org/2001/XMLSchema}string"/>
			 *                           &lt;/sequence>
			 *                         &lt;/restriction>
			 *                       &lt;/complexContent>
			 *                     &lt;/complexType>
			 *                   &lt;/element>
			 *                 &lt;/sequence>
			 *               &lt;/restriction>
			 *             &lt;/complexContent>
			 *           &lt;/complexType>
			 *         &lt;/element>
			 *         &lt;element name="Sesion" type="{http://www.w3.org/2001/XMLSchema}byte" minOccurs="0"/>
			 *         &lt;element name="SubcanalId" type="{http://www.w3.org/2001/XMLSchema}byte" minOccurs="0"/>
			 *         &lt;element name="DescConcepto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
			 *         &lt;element name="Cuit3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
			 *         &lt;element name="Cuit2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
			 *         &lt;element name="TipoValid" type="{http://www.w3.org/2001/XMLSchema}byte" minOccurs="0"/>
			 *         &lt;element name="Cuit1" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
			 *         &lt;element name="IPMaquina" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
			 *         &lt;element name="FormatoPar" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
			 *         &lt;element name="Version" type="{http://www.w3.org/2001/XMLSchema}short" minOccurs="0"/>
			 *         &lt;element name="NroCtaDebito" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
			 *         &lt;element name="PlazoAcreditacion" type="{http://www.w3.org/2001/XMLSchema}byte" minOccurs="0"/>
			 *         &lt;element name="SucCtaDebito" type="{http://www.w3.org/2001/XMLSchema}byte" minOccurs="0"/>
			 *         &lt;element name="SucCtaCredito" type="{http://www.w3.org/2001/XMLSchema}byte" minOccurs="0"/>
			 *         &lt;element name="IdRegOp" type="{http://www.w3.org/2001/XMLSchema}byte" minOccurs="0"/>
			 *         &lt;element name="NroCtaCredito" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
			 *         &lt;element name="CtaBane" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
			 *         &lt;element name="TipoPersona" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
			 *         &lt;element name="UsuarioId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
			 *         &lt;element name="CanalId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
			 *         &lt;element name="IndAuten" type="{http://www.w3.org/2001/XMLSchema}byte" minOccurs="0"/>
			 *         &lt;element name="ImporteTransferencia" type="{http://www.w3.org/2001/XMLSchema}float" minOccurs="0"/>
			 *         &lt;element name="DiasAvisoPrevio" type="{http://www.w3.org/2001/XMLSchema}byte" minOccurs="0"/>
			 *         &lt;element name="IdentificBeneficiario" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
			 *         &lt;element name="Agenda" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
			 *         &lt;element name="Direccion_Email" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
			 *         &lt;element name="NroRecurrencia" type="{http://www.w3.org/2001/XMLSchema}byte" minOccurs="0"/>
			 *         &lt;element name="MarcaLimite" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
			 *         &lt;element name="TipoAgendamiento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
			 *         &lt;element name="TipoOp" type="{http://www.w3.org/2001/XMLSchema}byte" minOccurs="0"/>
			 *         &lt;element name="NUP" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
			 *         &lt;element name="FechaEjecucion" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
			 *         &lt;element name="TipoId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
			 *         &lt;element name="FechaNac" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
			 *         &lt;element name="EcoDatosEntrada" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
			 *         &lt;element name="Cbu" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
			 *         &lt;element name="Hora" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
			 *         &lt;element name="CantidadDias" type="{http://www.w3.org/2001/XMLSchema}byte" minOccurs="0"/>
			 *         &lt;element name="CanalTipoOriginal" type="{http://www.w3.org/2001/XMLSchema}byte" minOccurs="0"/>
			 *         &lt;element name="NroSecMM" type="{http://www.w3.org/2001/XMLSchema}byte" minOccurs="0"/>
			 *         &lt;element name="EntidadCtaCredito" type="{http://www.w3.org/2001/XMLSchema}byte" minOccurs="0"/>
			 *         &lt;element name="CanalVersion" type="{http://www.w3.org/2001/XMLSchema}byte" minOccurs="0"/>
			 *         &lt;element name="ReferenciaUnivoca" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
			 *         &lt;element name="ModoEjecucion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
			 *         &lt;element name="IndOpRev" type="{http://www.w3.org/2001/XMLSchema}byte" minOccurs="0"/>
			 *         &lt;element name="Periodica" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
			 *         &lt;element name="TipoMensaje" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
			 *         &lt;element name="IdSolAut" type="{http://www.w3.org/2001/XMLSchema}byte" minOccurs="0"/>
			 *         &lt;element name="LogueoXML" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
			 *         &lt;element name="FrecRecurrencia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
			 *         &lt;element name="CaracteristicaCtaCredito" type="{http://www.w3.org/2001/XMLSchema}byte" minOccurs="0"/>
			 *         &lt;element name="LongParam" type="{http://www.w3.org/2001/XMLSchema}byte" minOccurs="0"/>
			 *         &lt;element name="TipoCtaDebito" type="{http://www.w3.org/2001/XMLSchema}byte" minOccurs="0"/>
			 *         &lt;element name="DelimParam" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
			 *         &lt;element name="LogueoDatosRespuesta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
			 *         &lt;element name="TipoEjec" type="{http://www.w3.org/2001/XMLSchema}byte" minOccurs="0"/>
			 *         &lt;element name="InformacDiscrecional" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
			 *         &lt;element name="IndSincro" type="{http://www.w3.org/2001/XMLSchema}byte" minOccurs="0"/>
			 *         &lt;element name="TipoRecurrencia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
			 *         &lt;element name="IndicadorFuncion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
			 *         &lt;element name="UsuarioAtrib" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
			 *         &lt;element name="IdSesionTx" type="{http://www.w3.org/2001/XMLSchema}byte" minOccurs="0"/>
			 *         &lt;element name="VersionSvc" type="{http://www.w3.org/2001/XMLSchema}short" minOccurs="0"/>
			 *         &lt;element name="MaxRecurrencia" type="{http://www.w3.org/2001/XMLSchema}byte" minOccurs="0"/>
			 *         &lt;element name="IndAutenAut" type="{http://www.w3.org/2001/XMLSchema}byte" minOccurs="0"/>
			 *         &lt;element name="TipoSeg" type="{http://www.w3.org/2001/XMLSchema}byte" minOccurs="0"/>
			 *         &lt;element name="IndBTLS" type="{http://www.w3.org/2001/XMLSchema}byte" minOccurs="0"/>
			 *         &lt;element name="User" type="{http://www.w3.org/2001/XMLSchema}byte" minOccurs="0"/>
			 *         &lt;element name="AutReq" type="{http://www.w3.org/2001/XMLSchema}byte" minOccurs="0"/>
			 *         &lt;element name="UsuarioPwd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
			 *         &lt;element name="IncluirTramas" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
			 *         &lt;element name="InfoAdicional" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
			 *         &lt;element name="TipoIdAut05" type="{http://www.w3.org/2001/XMLSchema}byte" minOccurs="0"/>
			 *         &lt;element name="TipoIdAut04" type="{http://www.w3.org/2001/XMLSchema}byte" minOccurs="0"/>
			 *         &lt;element name="TipoIdAut03" type="{http://www.w3.org/2001/XMLSchema}byte" minOccurs="0"/>
			 *         &lt;element name="IdAut05" type="{http://www.w3.org/2001/XMLSchema}byte" minOccurs="0"/>
			 *         &lt;element name="TipoIdAut02" type="{http://www.w3.org/2001/XMLSchema}byte" minOccurs="0"/>
			 *         &lt;element name="TpoCtaFromBane" type="{http://www.w3.org/2001/XMLSchema}byte" minOccurs="0"/>
			 *         &lt;element name="IdAut04" type="{http://www.w3.org/2001/XMLSchema}byte" minOccurs="0"/>
			 *         &lt;element name="TipoIdAut01" type="{http://www.w3.org/2001/XMLSchema}byte" minOccurs="0"/>
			 *         &lt;element name="CodConcepto" type="{http://www.w3.org/2001/XMLSchema}byte" minOccurs="0"/>
			 *         &lt;element name="IdAut03" type="{http://www.w3.org/2001/XMLSchema}byte" minOccurs="0"/>
			 *         &lt;element name="TipoResp" type="{http://www.w3.org/2001/XMLSchema}byte" minOccurs="0"/>
			 *         &lt;element name="FechaBaseRecurrencia" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
			 *         &lt;element name="BancoReceptor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
			 *         &lt;element name="IdAut02" type="{http://www.w3.org/2001/XMLSchema}byte" minOccurs="0"/>
			 *         &lt;element name="TpoCtaToBane" type="{http://www.w3.org/2001/XMLSchema}byte" minOccurs="0"/>
			 *         &lt;element name="IdAut01" type="{http://www.w3.org/2001/XMLSchema}byte" minOccurs="0"/>
			 *         &lt;element name="UsuarioTipo" type="{http://www.w3.org/2001/XMLSchema}byte" minOccurs="0"/>
			 *         &lt;element name="MarcaGravado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
			 *         &lt;element name="LONGPARAM" type="{http://www.w3.org/2001/XMLSchema}short" minOccurs="0"/>
			 *         &lt;element name="TipoCompr" type="{http://www.w3.org/2001/XMLSchema}byte" minOccurs="0"/>
			 *         &lt;element name="VersionHdr" type="{http://www.w3.org/2001/XMLSchema}short" minOccurs="0"/>
			 *         &lt;element name="BancoDestino" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
			 *         &lt;element name="IdServicio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
			 *         &lt;element name="TipoAut05" type="{http://www.w3.org/2001/XMLSchema}byte" minOccurs="0"/>
			 *         &lt;element name="TipoAut04" type="{http://www.w3.org/2001/XMLSchema}byte" minOccurs="0"/>
			 *         &lt;element name="LongCtaDesBane" type="{http://www.w3.org/2001/XMLSchema}byte" minOccurs="0"/>
			 *         &lt;element name="TipoAut03" type="{http://www.w3.org/2001/XMLSchema}byte" minOccurs="0"/>
			 *         &lt;element name="CantAut" type="{http://www.w3.org/2001/XMLSchema}byte" minOccurs="0"/>
			 *         &lt;element name="TipoAut02" type="{http://www.w3.org/2001/XMLSchema}byte" minOccurs="0"/>
			 *         &lt;element name="TipoAut01" type="{http://www.w3.org/2001/XMLSchema}byte" minOccurs="0"/>
			 *         &lt;element name="SubcanalTipo" type="{http://www.w3.org/2001/XMLSchema}byte" minOccurs="0"/>
			 *         &lt;element name="Titular" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
			 *         &lt;element name="AccionMM" type="{http://www.w3.org/2001/XMLSchema}byte" minOccurs="0"/>
			 *         &lt;element name="FirmanteCtaDebito" type="{http://www.w3.org/2001/XMLSchema}byte" minOccurs="0"/>
			 *         &lt;element name="DigitoCtaCredito" type="{http://www.w3.org/2001/XMLSchema}byte" minOccurs="0"/>
			 *         &lt;element name="IdCliente" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
			 *         &lt;element name="MonedaTransferencia" type="{http://www.w3.org/2001/XMLSchema}byte" minOccurs="0"/>
			 *         &lt;element name="PinAut05" type="{http://www.w3.org/2001/XMLSchema}byte" minOccurs="0"/>
			 *         &lt;element name="Comentario" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
			 *         &lt;element name="PinAut04" type="{http://www.w3.org/2001/XMLSchema}byte" minOccurs="0"/>
			 *         &lt;element name="PinAut03" type="{http://www.w3.org/2001/XMLSchema}byte" minOccurs="0"/>
			 *         &lt;element name="PinAut02" type="{http://www.w3.org/2001/XMLSchema}byte" minOccurs="0"/>
			 *         &lt;element name="PinAut01" type="{http://www.w3.org/2001/XMLSchema}byte" minOccurs="0"/>
			 *         &lt;element name="LogueoAgendaHistorica" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
			 *         &lt;element name="CanalTipo" type="{http://www.w3.org/2001/XMLSchema}byte" minOccurs="0"/>
			 *         &lt;element name="TipoTransferencia" type="{http://www.w3.org/2001/XMLSchema}byte" minOccurs="0"/>
			 *         &lt;element name="TitularCredito" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
			 *         &lt;element name="Fecha" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
			 *         &lt;element name="Nombre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
			 *         &lt;element name="NroOpCanal" type="{http://www.w3.org/2001/XMLSchema}byte" minOccurs="0"/>
			 *         &lt;element name="Fiid" type="{http://www.w3.org/2001/XMLSchema}byte" minOccurs="0"/>
			 *         &lt;element name="FuncAut" type="{http://www.w3.org/2001/XMLSchema}byte" minOccurs="0"/>
			 *         &lt;element name="NroReq" type="{http://www.w3.org/2001/XMLSchema}byte" minOccurs="0"/>
			 *       &lt;/sequence>
			 *     &lt;/restriction>
			 *   &lt;/complexContent>
			 * &lt;/complexType>
			 * </pre>
			 * 
			 * 
			 */
			@XmlAccessorType(XmlAccessType.FIELD)
			@XmlType(name = "", propOrder = { "config", "meta", "datosentrada", "datosAdicionales", "sesion",
					"subcanalId", "descConcepto", "cuit3", "cuit2", "tipoValid", "cuit1", "ipMaquina", "formatoPar",
					"version", "nroCtaDebito", "plazoAcreditacion", "sucCtaDebito", "sucCtaCredito", "idRegOp",
					"nroCtaCredito", "ctaBane", "tipoPersona", "usuarioId", "canalId", "indAuten", "importeDebito",
					"importeTransferencia", "diasAvisoPrevio", "identificBeneficiario", "agenda", "direccionEmail",
					"nroRecurrencia", "marcaLimite", "tipoAgendamiento", "tipoOp", "nup", "fechaEjecucion", "tipoId",
					"fechaNac", "ecoDatosEntrada", "cbu", "hora", "cantidadDias", "canalTipoOriginal", "nroSecMM",
					"entidadCtaCredito", "canalVersion", "referenciaUnivoca", "modoEjecucion", "indOpRev", "periodica",
					"tipoMensaje", "idSolAut", "logueoXML", "frecRecurrencia", "caracteristicaCtaCredito", "longParam",
					"tipoCtaDebito", "delimParam", "logueoDatosRespuesta", "tipoEjec", "informacDiscrecional",
					"indSincro", "tipoRecurrencia", "indicadorFuncion", "usuarioAtrib", "idSesionTx", "versionSvc",
					"maxRecurrencia", "indAutenAut", "tipoSeg", "indBTLS", "user", "autReq", "usuarioPwd",
					"incluirTramas", "infoAdicional", "tipoIdAut05", "tipoIdAut04", "tipoIdAut03", "idAut05",
					"tipoIdAut02", "tpoCtaFromBane", "idAut04", "tipoIdAut01", "codConcepto", "idAut03", "tipoResp",
					"fechaBaseRecurrencia", "bancoReceptor", "idAut02", "tpoCtaToBane", "idAut01", "usuarioTipo",
					"marcaGravado", "longparam", "tipoCompr", "versionHdr", "bancoDestino", "idServicio", "tipoAut05",
					"tipoAut04", "longCtaDesBane", "tipoAut03", "cantAut", "tipoAut02", "tipoAut01", "subcanalTipo",
					"titular", "accionMM", "firmanteCtaDebito", "digitoCtaCredito", "idCliente", "monedaTransferencia",
					"pinAut05", "comentario", "pinAut04", "pinAut03", "pinAut02", "pinAut01", "logueoAgendaHistorica",
					"canalTipo", "tipoTransferencia", "titularCredito", "fecha", "nombre", "nroOpCanal", "fiid",
					"funcAut", "nroReq", "nombreCtaCredito", "importe", "tipoCtaCredito", "anotacionesPersonales",
					"mailCredito", "comentarios", "comprobanteBackEnd", "codigoConcepto", "descripcionConcepto",
					"cuentaPropia", "indTransfDiferida" })
			public static class XMLEntrada {

				/** The config. */
				@XmlElement(name = "CONFIG")
				protected XMLResponseEntity.DATOSRESULTADO.Registro.XMLEntrada.CONFIG config;

				/** The meta. */
				@XmlElement(name = "META")
				protected XMLResponseEntity.DATOSRESULTADO.Registro.XMLEntrada.META meta;

				/** The datosentrada. */
				@XmlElement(name = "DATOSENTRADA")
				protected XMLResponseEntity.DATOSRESULTADO.Registro.XMLEntrada.DATOSENTRADA datosentrada;

				/** The datos adicionales. */
				@XmlElement(name = "DatosAdicionales")
				protected XMLResponseEntity.DATOSRESULTADO.Registro.XMLEntrada.DatosAdicionales datosAdicionales;

				/** The sesion. */
				@XmlElement(name = "Sesion")
				protected Byte sesion;

				/** The subcanal id. */
				@XmlElement(name = "SubcanalId")
				protected Byte subcanalId;

				/** The desc concepto. */
				@XmlElement(name = "DescConcepto")
				protected String descConcepto;

				/** The descripcion concepto. */
				@XmlElement(name = "DescripcionConcepto")
				protected String descripcionConcepto;

				/** The cuit 3. */
				@XmlElement(name = "Cuit3")
				protected String cuit3;

				/** The cuit 2. */
				@XmlElement(name = "Cuit2")
				protected String cuit2;

				/** The tipo valid. */
				@XmlElement(name = "TipoValid")
				protected Byte tipoValid;

				/** The cuit 1. */
				@XmlElement(name = "Cuit1")
				protected String cuit1;

				/** The ip maquina. */
				@XmlElement(name = "IPMaquina")
				protected Long ipMaquina;

				/** The formato par. */
				@XmlElement(name = "FormatoPar")
				protected String formatoPar;

				/** The version. */
				@XmlElement(name = "Version")
				protected Short version;

				/** The nro cta debito. */
				@XmlElement(name = "NroCtaDebito")
				protected String nroCtaDebito;

				/** The plazo acreditacion. */
				@XmlElement(name = "PlazoAcreditacion")
				protected String plazoAcreditacion;

				/** The suc cta debito. */
				@XmlElement(name = "SucCtaDebito")
				protected String sucCtaDebito;

				/** The suc cta credito. */
				@XmlElement(name = "SucCtaCredito")
				protected String sucCtaCredito;

				/** The id reg op. */
				@XmlElement(name = "IdRegOp")
				protected Byte idRegOp;

				/** The nro cta credito. */
				@XmlElement(name = "NroCtaCredito")
				protected String nroCtaCredito;

				/** The cta bane. */
				@XmlElement(name = "CtaBane")
				protected Long ctaBane;

				/** The tipo persona. */
				@XmlElement(name = "TipoPersona")
				protected String tipoPersona;

				/** The usuario id. */
				@XmlElement(name = "UsuarioId")
				protected String usuarioId;

				/** The indTransfDiferida. */
				@XmlElement(name = "IndTransfDiferida")
				protected String indTransfDiferida;

				/** The canal id. */
				@XmlElement(name = "CanalId")
				protected String canalId;

				/** The ind auten. */
				@XmlElement(name = "IndAuten")
				protected Byte indAuten;

				/** The importe debito. */
				@XmlElement(name = "ImporteDebito")
				protected String importeDebito;

				/** The importe transferencia. */
				@XmlElement(name = "ImporteTransferencia")
				protected String importeTransferencia;

				/** The dias aviso previo. */
				@XmlElement(name = "DiasAvisoPrevio")
				protected Byte diasAvisoPrevio;

				/** The identific beneficiario. */
				@XmlElement(name = "IdentificBeneficiario")
				protected String identificBeneficiario;

				/** The agenda. */
				@XmlElement(name = "Agenda")
				protected String agenda;

				/** The direccion email. */
				@XmlElement(name = "Direccion_Email")
				protected String direccionEmail;

				/** The nro recurrencia. */
				@XmlElement(name = "NroRecurrencia")
				protected Byte nroRecurrencia;

				/** The marca limite. */
				@XmlElement(name = "MarcaLimite")
				protected String marcaLimite;

				/** The tipo agendamiento. */
				@XmlElement(name = "TipoAgendamiento")
				protected String tipoAgendamiento;

				/** The tipo op. */
				@XmlElement(name = "TipoOp")
				protected Byte tipoOp;

				/** The nup. */
				@XmlElement(name = "NUP")
				protected Integer nup;

				/** The fecha ejecucion. */
				@XmlElement(name = "FechaEjecucion")
				protected Long fechaEjecucion;

				/** The tipo id. */
				@XmlElement(name = "TipoId")
				protected String tipoId;

				/** The tipo cuenta propia. */
				@XmlElement(name = "CuentaPropia")
				protected String cuentaPropia;

				/** The fecha nac. */
				@XmlElement(name = "FechaNac")
				protected Integer fechaNac;

				/** The eco datos entrada. */
				@XmlElement(name = "EcoDatosEntrada")
				protected String ecoDatosEntrada;

				/** The cbu. */
				@XmlElement(name = "Cbu")
				protected String cbu;

				/** The hora. */
				@XmlElement(name = "Hora")
				protected Integer hora;

				/** The cantidad dias. */
				@XmlElement(name = "CantidadDias")
				protected Byte cantidadDias;

				/** The canal tipo original. */
				@XmlElement(name = "CanalTipoOriginal")
				protected Byte canalTipoOriginal;

				/** The nro sec MM. */
				@XmlElement(name = "NroSecMM")
				protected Byte nroSecMM;

				/** The entidad cta credito. */
				@XmlElement(name = "EntidadCtaCredito")
				protected Byte entidadCtaCredito;

				/** The canal version. */
				@XmlElement(name = "CanalVersion")
				protected Byte canalVersion;

				/** The referencia univoca. */
				@XmlElement(name = "ReferenciaUnivoca")
				protected String referenciaUnivoca;

				/** The modo ejecucion. */
				@XmlElement(name = "ModoEjecucion")
				protected String modoEjecucion;

				/** The ind op rev. */
				@XmlElement(name = "IndOpRev")
				protected Byte indOpRev;

				/** The periodica. */
				@XmlElement(name = "Periodica")
				protected String periodica;

				/** The tipo mensaje. */
				@XmlElement(name = "TipoMensaje")
				protected String tipoMensaje;

				/** The id sol aut. */
				@XmlElement(name = "IdSolAut")
				protected Byte idSolAut;

				/** The logueo XML. */
				@XmlElement(name = "LogueoXML")
				protected String logueoXML;

				/** The frec recurrencia. */
				@XmlElement(name = "FrecRecurrencia")
				protected String frecRecurrencia;

				/** The caracteristica cta credito. */
				@XmlElement(name = "CaracteristicaCtaCredito")
				protected String caracteristicaCtaCredito;

				/** The long param. */
				@XmlElement(name = "LongParam")
				protected Byte longParam;

				/** The tipo cta debito. */
				@XmlElement(name = "TipoCtaDebito")
				protected String tipoCtaDebito;

				/** The delim param. */
				@XmlElement(name = "DelimParam")
				protected String delimParam;

				/** The logueo datos respuesta. */
				@XmlElement(name = "LogueoDatosRespuesta")
				protected String logueoDatosRespuesta;

				/** The tipo ejec. */
				@XmlElement(name = "TipoEjec")
				protected Byte tipoEjec;

				/** The informac discrecional. */
				@XmlElement(name = "InformacDiscrecional")
				protected String informacDiscrecional;

				/** The ind sincro. */
				@XmlElement(name = "IndSincro")
				protected Byte indSincro;

				/** The tipo recurrencia. */
				@XmlElement(name = "TipoRecurrencia")
				protected String tipoRecurrencia;

				/** The indicador funcion. */
				@XmlElement(name = "IndicadorFuncion")
				protected String indicadorFuncion;

				/** The usuario atrib. */
				@XmlElement(name = "UsuarioAtrib")
				protected String usuarioAtrib;

				/** The id sesion tx. */
				@XmlElement(name = "IdSesionTx")
				protected Byte idSesionTx;

				/** The version svc. */
				@XmlElement(name = "VersionSvc")
				protected Short versionSvc;

				/** The max recurrencia. */
				@XmlElement(name = "MaxRecurrencia")
				protected Byte maxRecurrencia;

				/** The ind auten aut. */
				@XmlElement(name = "IndAutenAut")
				protected Byte indAutenAut;

				/** The tipo seg. */
				@XmlElement(name = "TipoSeg")
				protected Byte tipoSeg;

				/** The ind BTLS. */
				@XmlElement(name = "IndBTLS")
				protected Byte indBTLS;

				/** The user. */
				@XmlElement(name = "User")
				protected Byte user;

				/** The aut req. */
				@XmlElement(name = "AutReq")
				protected Byte autReq;

				/** The usuario pwd. */
				@XmlElement(name = "UsuarioPwd")
				protected String usuarioPwd;

				/** The incluir tramas. */
				@XmlElement(name = "IncluirTramas")
				protected String incluirTramas;

				/** The info adicional. */
				@XmlElement(name = "InfoAdicional")
				protected String infoAdicional;

				/** The tipo id aut 05. */
				@XmlElement(name = "TipoIdAut05")
				protected Byte tipoIdAut05;

				/** The tipo id aut 04. */
				@XmlElement(name = "TipoIdAut04")
				protected Byte tipoIdAut04;

				/** The tipo id aut 03. */
				@XmlElement(name = "TipoIdAut03")
				protected Byte tipoIdAut03;

				/** The id aut 05. */
				@XmlElement(name = "IdAut05")
				protected Byte idAut05;

				/** The tipo id aut 02. */
				@XmlElement(name = "TipoIdAut02")
				protected Byte tipoIdAut02;

				/** The tpo cta from bane. */
				@XmlElement(name = "TpoCtaFromBane")
				protected Byte tpoCtaFromBane;

				/** The id aut 04. */
				@XmlElement(name = "IdAut04")
				protected Byte idAut04;

				/** The tipo id aut 01. */
				@XmlElement(name = "TipoIdAut01")
				protected Byte tipoIdAut01;

				/** The cod concepto. */
				@XmlElement(name = "CodConcepto")
				protected String codConcepto;

				/** The codigo concepto. */
				@XmlElement(name = "CodigoConcepto")
				protected String codigoConcepto;

				/** The id aut 03. */
				@XmlElement(name = "IdAut03")
				protected Byte idAut03;

				/** The tipo resp. */
				@XmlElement(name = "TipoResp")
				protected Byte tipoResp;

				/** The fecha base recurrencia. */
				@XmlElement(name = "FechaBaseRecurrencia")
				protected Integer fechaBaseRecurrencia;

				/** The banco receptor. */
				@XmlElement(name = "BancoReceptor")
				protected String bancoReceptor;

				/** The id aut 02. */
				@XmlElement(name = "IdAut02")
				protected Byte idAut02;

				/** The tpo cta to bane. */
				@XmlElement(name = "TpoCtaToBane")
				protected Byte tpoCtaToBane;

				/** The id aut 01. */
				@XmlElement(name = "IdAut01")
				protected Byte idAut01;

				/** The usuario tipo. */
				@XmlElement(name = "UsuarioTipo")
				protected Byte usuarioTipo;

				/** The marca gravado. */
				@XmlElement(name = "MarcaGravado")
				protected String marcaGravado;

				/** The longparam. */
				@XmlElement(name = "LONGPARAM")
				protected Short longparam;

				/** The tipo compr. */
				@XmlElement(name = "TipoCompr")
				protected Byte tipoCompr;

				/** The version hdr. */
				@XmlElement(name = "VersionHdr")
				protected Short versionHdr;

				/** The banco destino. */
				@XmlElement(name = "BancoDestino")
				protected String bancoDestino;

				/** The id servicio. */
				@XmlElement(name = "IdServicio")
				protected String idServicio;

				/** The tipo aut 05. */
				@XmlElement(name = "TipoAut05")
				protected Byte tipoAut05;

				/** The tipo aut 04. */
				@XmlElement(name = "TipoAut04")
				protected Byte tipoAut04;

				/** The long cta des bane. */
				@XmlElement(name = "LongCtaDesBane")
				protected Byte longCtaDesBane;

				/** The tipo aut 03. */
				@XmlElement(name = "TipoAut03")
				protected Byte tipoAut03;

				/** The cant aut. */
				@XmlElement(name = "CantAut")
				protected Byte cantAut;

				/** The tipo aut 02. */
				@XmlElement(name = "TipoAut02")
				protected Byte tipoAut02;

				/** The tipo aut 01. */
				@XmlElement(name = "TipoAut01")
				protected Byte tipoAut01;

				/** The subcanal tipo. */
				@XmlElement(name = "SubcanalTipo")
				protected Byte subcanalTipo;

				/** The titular. */
				@XmlElement(name = "Titular")
				protected String titular;

				/** The accion MM. */
				@XmlElement(name = "AccionMM")
				protected Byte accionMM;

				/** The firmante cta debito. */
				@XmlElement(name = "FirmanteCtaDebito")
				protected Byte firmanteCtaDebito;

				/** The digito cta credito. */
				@XmlElement(name = "DigitoCtaCredito")
				protected Byte digitoCtaCredito;

				/** The id cliente. */
				@XmlElement(name = "IdCliente")
				protected Integer idCliente;

				/** The moneda transferencia. */
				@XmlElement(name = "MonedaTransferencia")
				protected String monedaTransferencia;

				/** The pin aut 05. */
				@XmlElement(name = "PinAut05")
				protected Byte pinAut05;

				/** The comentario. */
				@XmlElement(name = "Comentario")
				protected String comentario;

				/** The comentarios. */
				@XmlElement(name = "Comentarios")
				protected String comentarios;

				/** The pin aut 04. */
				@XmlElement(name = "PinAut04")
				protected Byte pinAut04;

				/** The pin aut 03. */
				@XmlElement(name = "PinAut03")
				protected Byte pinAut03;

				/** The pin aut 02. */
				@XmlElement(name = "PinAut02")
				protected Byte pinAut02;

				/** The pin aut 01. */
				@XmlElement(name = "PinAut01")
				protected Byte pinAut01;

				/** The logueo agenda historica. */
				@XmlElement(name = "LogueoAgendaHistorica")
				protected String logueoAgendaHistorica;

				/** The canal tipo. */
				@XmlElement(name = "CanalTipo")
				protected Byte canalTipo;

				/** The tipo transferencia. */
				@XmlElement(name = "TipoTransferencia")
				protected Byte tipoTransferencia;

				/** The titular credito. */
				@XmlElement(name = "TitularCredito")
				protected String titularCredito;

				/** The fecha. */
				@XmlElement(name = "Fecha")
				protected Integer fecha;

				/** The nombre. */
				@XmlElement(name = "Nombre")
				protected String nombre;

				/** The nro op canal. */
				@XmlElement(name = "NroOpCanal")
				protected Byte nroOpCanal;

				/** The fiid. */
				@XmlElement(name = "Fiid")
				protected Byte fiid;

				/** The func aut. */
				@XmlElement(name = "FuncAut")
				protected Byte funcAut;

				/** The nro req. */
				@XmlElement(name = "NroReq")
				protected Byte nroReq;

				/** The nombre cta credito. */
				@XmlElement(name = "NombreCtaCredito")
				protected String nombreCtaCredito;

				/** The importe. */
				@XmlElement(name = "Importe")
				protected String importe;

				/** The tipo cta credito. */
				@XmlElement(name = "TipoCtaCredito")
				protected String tipoCtaCredito;

				/** The anotaciones personales. */
				@XmlElement(name = "AnotacionesPersonales")
				protected String anotacionesPersonales;

				/** The mail credito. */
				@XmlElement(name = "MailCredito")
				protected String mailCredito;

				/** The comprobante back end. */
				@XmlElement(name = "ComprobanteBackEnd")
				protected String comprobanteBackEnd;

				/**
				 * Gets the value of the config property.
				 * 
				 * @return possible object is
				 *         {@link XMLResponseEntity.DATOSRESULTADO.Registro.XMLEntrada.CONFIG }
				 * 
				 */
				public XMLResponseEntity.DATOSRESULTADO.Registro.XMLEntrada.CONFIG getCONFIG() {
					return config;
				}

				/**
				 * Sets the value of the config property.
				 * 
				 * @param value
				 *            allowed object is
				 *            {@link XMLResponseEntity.DATOSRESULTADO.Registro.XMLEntrada.CONFIG }
				 * 
				 */
				public void setCONFIG(XMLResponseEntity.DATOSRESULTADO.Registro.XMLEntrada.CONFIG value) {
					this.config = value;
				}

				/**
				 * Gets the value of the meta property.
				 * 
				 * @return possible object is
				 *         {@link XMLResponseEntity.DATOSRESULTADO.Registro.XMLEntrada.META }
				 * 
				 */
				public XMLResponseEntity.DATOSRESULTADO.Registro.XMLEntrada.META getMETA() {
					return meta;
				}

				/**
				 * Sets the value of the meta property.
				 * 
				 * @param value
				 *            allowed object is
				 *            {@link XMLResponseEntity.DATOSRESULTADO.Registro.XMLEntrada.META }
				 * 
				 */
				public void setMETA(XMLResponseEntity.DATOSRESULTADO.Registro.XMLEntrada.META value) {
					this.meta = value;
				}

				/**
				 * Gets the value of the datosentrada property.
				 * 
				 * @return possible object is
				 *         {@link XMLResponseEntity.DATOSRESULTADO.Registro.XMLEntrada.DATOSENTRADA }
				 * 
				 */
				public XMLResponseEntity.DATOSRESULTADO.Registro.XMLEntrada.DATOSENTRADA getDATOSENTRADA() {
					return datosentrada;
				}

				/**
				 * Sets the value of the datosentrada property.
				 * 
				 * @param value
				 *            allowed object is
				 *            {@link XMLResponseEntity.DATOSRESULTADO.Registro.XMLEntrada.DATOSENTRADA }
				 * 
				 */
				public void setDATOSENTRADA(XMLResponseEntity.DATOSRESULTADO.Registro.XMLEntrada.DATOSENTRADA value) {
					this.datosentrada = value;
				}

				/**
				 * Gets the value of the datosAdicionales property.
				 * 
				 * @return possible object is
				 *         {@link XMLResponseEntity.DATOSRESULTADO.Registro.XMLEntrada.DatosAdicionales }
				 * 
				 */
				public XMLResponseEntity.DATOSRESULTADO.Registro.XMLEntrada.DatosAdicionales getDatosAdicionales() {
					return datosAdicionales;
				}

				/**
				 * Sets the value of the datosAdicionales property.
				 * 
				 * @param value
				 *            allowed object is
				 *            {@link XMLResponseEntity.DATOSRESULTADO.Registro.XMLEntrada.DatosAdicionales }
				 * 
				 */
				public void setDatosAdicionales(
						XMLResponseEntity.DATOSRESULTADO.Registro.XMLEntrada.DatosAdicionales value) {
					this.datosAdicionales = value;
				}

				/**
				 * Gets the value of the sesion property.
				 * 
				 * @return possible object is {@link Byte }
				 * 
				 */
				public Byte getSesion() {
					return sesion;
				}

				/**
				 * Sets the value of the sesion property.
				 * 
				 * @param value
				 *            allowed object is {@link Byte }
				 * 
				 */
				public void setSesion(Byte value) {
					this.sesion = value;
				}

				/**
				 * Gets the anotaciones personales.
				 *
				 * @return the anotaciones personales
				 */
				public String getAnotacionesPersonales() {
					return anotacionesPersonales;
				}

				/**
				 * Sets the anotaciones personales.
				 *
				 * @param anotacionesPersonales
				 *            the new anotaciones personales
				 */
				public void setAnotacionesPersonales(String anotacionesPersonales) {
					this.anotacionesPersonales = anotacionesPersonales;
				}

				/**
				 * Gets the importe debito.
				 *
				 * @return the importe debito
				 */
				public String getImporteDebito() {
					return importeDebito;
				}

				/**
				 * Sets the importe debito.
				 *
				 * @param importeDebito
				 *            the new importe debito
				 */
				public void setImporteDebito(String importeDebito) {
					this.importeDebito = importeDebito;
				}

				/**
				 * Gets the value of the subcanalId property.
				 * 
				 * @return possible object is {@link Byte }
				 * 
				 */
				public Byte getSubcanalId() {
					return subcanalId;
				}

				/**
				 * Sets the value of the subcanalId property.
				 * 
				 * @param value
				 *            allowed object is {@link Byte }
				 * 
				 */
				public void setSubcanalId(Byte value) {
					this.subcanalId = value;
				}

				/**
				 * Gets the value of the descConcepto property.
				 * 
				 * @return possible object is {@link String }
				 * 
				 */
				public String getDescConcepto() {
					return descConcepto;
				}

				/**
				 * Sets the value of the descConcepto property.
				 * 
				 * @param value
				 *            allowed object is {@link String }
				 * 
				 */
				public void setDescConcepto(String value) {
					this.descConcepto = value;
				}

				/**
				 * Gets the descripcion concepto.
				 *
				 * @return the descripcion concepto
				 */
				public String getDescripcionConcepto() {
					return descripcionConcepto;
				}

				/**
				 * Sets the descripcion concepto.
				 *
				 * @param descripcionConcepto
				 *            the new descripcion concepto
				 */
				public void setDescripcionConcepto(String descripcionConcepto) {
					this.descripcionConcepto = descripcionConcepto;
				}

				/**
				 * Gets the codigo concepto.
				 *
				 * @return the codigo concepto
				 */
				public String getCodigoConcepto() {
					return codigoConcepto;
				}

				/**
				 * Sets the codigo concepto.
				 *
				 * @param codigoConcepto
				 *            the new codigo concepto
				 */
				public void setCodigoConcepto(String codigoConcepto) {
					this.codigoConcepto = codigoConcepto;
				}

				/**
				 * Gets the comentarios.
				 *
				 * @return the comentarios
				 */
				public String getComentarios() {
					return comentarios;
				}

				/**
				 * Sets the comentarios.
				 *
				 * @param comentarios
				 *            the new comentarios
				 */
				public void setComentarios(String comentarios) {
					this.comentarios = comentarios;
				}

				/**
				 * Gets the value of the cuit3 property.
				 * 
				 * @return possible object is {@link String }
				 * 
				 */
				public String getCuit3() {
					return cuit3;
				}

				/**
				 * Sets the value of the cuit3 property.
				 * 
				 * @param value
				 *            allowed object is {@link String }
				 * 
				 */
				public void setCuit3(String value) {
					this.cuit3 = value;
				}

				/**
				 * Gets the datosentrada.
				 *
				 * @return the datosentrada
				 */
				public XMLResponseEntity.DATOSRESULTADO.Registro.XMLEntrada.DATOSENTRADA getDatosentrada() {
					return datosentrada;
				}

				/**
				 * Sets the datosentrada.
				 *
				 * @param datosentrada
				 *            the new datosentrada
				 */
				public void setDatosentrada(
						XMLResponseEntity.DATOSRESULTADO.Registro.XMLEntrada.DATOSENTRADA datosentrada) {
					this.datosentrada = datosentrada;
				}

				/**
				 * Gets the nombre cta credito.
				 *
				 * @return the nombre cta credito
				 */
				public String getNombreCtaCredito() {
					return nombreCtaCredito;
				}

				/**
				 * Sets the nombre cta credito.
				 *
				 * @param nombreCtaCredito
				 *            the new nombre cta credito
				 */
				public void setNombreCtaCredito(String nombreCtaCredito) {
					this.nombreCtaCredito = nombreCtaCredito;
				}

				/**
				 * Gets the value of the cuit2 property.
				 * 
				 * @return possible object is {@link String }
				 * 
				 */
				public String getCuit2() {
					return cuit2;
				}

				/**
				 * Sets the value of the cuit2 property.
				 * 
				 * @param value
				 *            allowed object is {@link String }
				 * 
				 */
				public void setCuit2(String value) {
					this.cuit2 = value;
				}

				/**
				 * Gets the importe.
				 *
				 * @return the importe
				 */
				public String getImporte() {
					return importe;
				}

				/**
				 * Sets the importe.
				 *
				 * @param importe
				 *            the new importe
				 */
				public void setImporte(String importe) {
					this.importe = importe;
				}

				/**
				 * Gets the value of the tipoValid property.
				 * 
				 * @return possible object is {@link Byte }
				 * 
				 */
				public Byte getTipoValid() {
					return tipoValid;
				}

				/**
				 * Sets the value of the tipoValid property.
				 * 
				 * @param value
				 *            allowed object is {@link Byte }
				 * 
				 */
				public void setTipoValid(Byte value) {
					this.tipoValid = value;
				}

				/**
				 * Gets the value of the cuit1 property.
				 * 
				 * @return possible object is {@link Long }
				 * 
				 */
				public String getCuit1() {
					return cuit1;
				}

				/**
				 * Sets the value of the cuit1 property.
				 * 
				 * @param value
				 *            allowed object is {@link Long }
				 * 
				 */
				public void setCuit1(String value) {
					this.cuit1 = value;
				}

				/**
				 * Gets the value of the ipMaquina property.
				 * 
				 * @return possible object is {@link Long }
				 * 
				 */
				public Long getIPMaquina() {
					return ipMaquina;
				}

				/**
				 * Sets the value of the ipMaquina property.
				 * 
				 * @param value
				 *            allowed object is {@link Long }
				 * 
				 */
				public void setIPMaquina(Long value) {
					this.ipMaquina = value;
				}

				/**
				 * Gets the value of the formatoPar property.
				 * 
				 * @return possible object is {@link String }
				 * 
				 */
				public String getFormatoPar() {
					return formatoPar;
				}

				/**
				 * Sets the value of the formatoPar property.
				 * 
				 * @param value
				 *            allowed object is {@link String }
				 * 
				 */
				public void setFormatoPar(String value) {
					this.formatoPar = value;
				}

				/**
				 * Gets the mail credito.
				 *
				 * @return the mail credito
				 */
				public String getMailCredito() {
					return mailCredito;
				}

				/**
				 * Sets the mail credito.
				 *
				 * @param mailCredito
				 *            the new mail credito
				 */
				public void setMailCredito(String mailCredito) {
					this.mailCredito = mailCredito;
				}

				/**
				 * Gets the value of the version property.
				 * 
				 * @return possible object is {@link Short }
				 * 
				 */
				public Short getVersion() {
					return version;
				}

				/**
				 * Sets the value of the version property.
				 * 
				 * @param value
				 *            allowed object is {@link Short }
				 * 
				 */
				public void setVersion(Short value) {
					this.version = value;
				}

				/**
				 * Gets the value of the nroCtaDebito property.
				 * 
				 * @return possible object is {@link Integer }
				 * 
				 */
				public String getNroCtaDebito() {
					return nroCtaDebito;
				}

				/**
				 * Sets the value of the nroCtaDebito property.
				 * 
				 * @param value
				 *            allowed object is {@link Integer }
				 * 
				 */
				public void setNroCtaDebito(String value) {
					this.nroCtaDebito = value;
				}

				/**
				 * Gets the value of the plazoAcreditacion property.
				 * 
				 * @return possible object is {@link Byte }
				 * 
				 */
				public String getPlazoAcreditacion() {
					return plazoAcreditacion;
				}

				/**
				 * Sets the value of the plazoAcreditacion property.
				 * 
				 * @param value
				 *            allowed object is {@link Byte }
				 * 
				 */
				public void setPlazoAcreditacion(String value) {
					this.plazoAcreditacion = value;
				}

				/**
				 * Gets the tipo cta credito.
				 *
				 * @return the tipo cta credito
				 */
				public String getTipoCtaCredito() {
					return tipoCtaCredito;
				}

				/**
				 * Sets the tipo cta credito.
				 *
				 * @param tipoCtaCredito
				 *            the new tipo cta credito
				 */
				public void setTipoCtaCredito(String tipoCtaCredito) {
					this.tipoCtaCredito = tipoCtaCredito;
				}

				/**
				 * Gets the value of the sucCtaDebito property.
				 * 
				 * @return possible object is {@link Byte }
				 * 
				 */
				public String getSucCtaDebito() {
					return sucCtaDebito;
				}

				/**
				 * Sets the value of the sucCtaDebito property.
				 * 
				 * @param value
				 *            allowed object is {@link Byte }
				 * 
				 */
				public void setSucCtaDebito(String value) {
					this.sucCtaDebito = value;
				}

				/**
				 * Gets the value of the sucCtaCredito property.
				 * 
				 * @return possible object is {@link Byte }
				 * 
				 */
				public String getSucCtaCredito() {
					return sucCtaCredito;
				}

				/**
				 * Sets the value of the sucCtaCredito property.
				 * 
				 * @param value
				 *            allowed object is {@link Byte }
				 * 
				 */
				public void setSucCtaCredito(String value) {
					this.sucCtaCredito = value;
				}

				/**
				 * Gets the value of the idRegOp property.
				 * 
				 * @return possible object is {@link Byte }
				 * 
				 */
				public Byte getIdRegOp() {
					return idRegOp;
				}

				/**
				 * Sets the value of the idRegOp property.
				 * 
				 * @param value
				 *            allowed object is {@link Byte }
				 * 
				 */
				public void setIdRegOp(Byte value) {
					this.idRegOp = value;
				}

				/**
				 * Gets the value of the nroCtaCredito property.
				 * 
				 * @return possible object is {@link Long }
				 * 
				 */
				public String getNroCtaCredito() {
					return nroCtaCredito;
				}

				/**
				 * Sets the value of the nroCtaCredito property.
				 * 
				 * @param value
				 *            allowed object is {@link Long }
				 * 
				 */
				public void setNroCtaCredito(String value) {
					this.nroCtaCredito = value;
				}

				/**
				 * Gets the value of the ctaBane property.
				 * 
				 * @return possible object is {@link Long }
				 * 
				 */
				public Long getCtaBane() {
					return ctaBane;
				}

				/**
				 * Sets the value of the ctaBane property.
				 * 
				 * @param value
				 *            allowed object is {@link Long }
				 * 
				 */
				public void setCtaBane(Long value) {
					this.ctaBane = value;
				}

				/**
				 * Gets the value of the tipoPersona property.
				 * 
				 * @return possible object is {@link String }
				 * 
				 */
				public String getTipoPersona() {
					return tipoPersona;
				}

				/**
				 * Sets the value of the tipoPersona property.
				 * 
				 * @param value
				 *            allowed object is {@link String }
				 * 
				 */
				public void setTipoPersona(String value) {
					this.tipoPersona = value;
				}

				/**
				 * Gets the value of the usuarioId property.
				 * 
				 * @return possible object is {@link String }
				 * 
				 */
				public String getUsuarioId() {
					return usuarioId;
				}

				/**
				 * Sets the value of the usuarioId property.
				 * 
				 * @param value
				 *            allowed object is {@link String }
				 * 
				 */
				public void setUsuarioId(String value) {
					this.usuarioId = value;
				}

				/**
				 * Gets the value of the canalId property.
				 * 
				 * @return possible object is {@link String }
				 * 
				 */
				public String getCanalId() {
					return canalId;
				}

				/**
				 * Sets the value of the canalId property.
				 * 
				 * @param value
				 *            allowed object is {@link String }
				 * 
				 */
				public void setCanalId(String value) {
					this.canalId = value;
				}

				/**
				 * Gets the value of the indAuten property.
				 * 
				 * @return possible object is {@link Byte }
				 * 
				 */
				public Byte getIndAuten() {
					return indAuten;
				}

				/**
				 * Sets the value of the indAuten property.
				 * 
				 * @param value
				 *            allowed object is {@link Byte }
				 * 
				 */
				public void setIndAuten(Byte value) {
					this.indAuten = value;
				}

				/**
				 * Gets the value of the importeTransferencia property.
				 * 
				 * @return possible object is {@link Float }
				 * 
				 */
				public String getImporteTransferencia() {
					return importeTransferencia;
				}

				/**
				 * Sets the value of the importeTransferencia property.
				 * 
				 * @param value
				 *            allowed object is {@link Float }
				 * 
				 */
				public void setImporteTransferencia(String value) {
					this.importeTransferencia = value;
				}

				/**
				 * Gets the value of the diasAvisoPrevio property.
				 * 
				 * @return possible object is {@link Byte }
				 * 
				 */
				public Byte getDiasAvisoPrevio() {
					return diasAvisoPrevio;
				}

				/**
				 * Sets the value of the diasAvisoPrevio property.
				 * 
				 * @param value
				 *            allowed object is {@link Byte }
				 * 
				 */
				public void setDiasAvisoPrevio(Byte value) {
					this.diasAvisoPrevio = value;
				}

				/**
				 * Gets the value of the identificBeneficiario property.
				 * 
				 * @return possible object is {@link Long }
				 * 
				 */
				public String getIdentificBeneficiario() {
					return identificBeneficiario;
				}

				/**
				 * Sets the value of the identificBeneficiario property.
				 * 
				 * @param value
				 *            allowed object is {@link Long }
				 * 
				 */
				public void setIdentificBeneficiario(String value) {
					this.identificBeneficiario = value;
				}

				/**
				 * Gets the value of the agenda property.
				 * 
				 * @return possible object is {@link String }
				 * 
				 */
				public String getAgenda() {
					return agenda;
				}

				/**
				 * Sets the value of the agenda property.
				 * 
				 * @param value
				 *            allowed object is {@link String }
				 * 
				 */
				public void setAgenda(String value) {
					this.agenda = value;
				}

				/**
				 * Gets the ind transf diferida.
				 *
				 * @return the ind transf diferida
				 */
				public String getIndTransfDiferida() {
					return indTransfDiferida;
				}

				/**
				 * Sets the ind transf diferida.
				 *
				 * @param indTransfDiferida
				 *            the new ind transf diferida
				 */
				public void setIndTransfDiferida(String indTransfDiferida) {
					this.indTransfDiferida = indTransfDiferida;
				}

				/**
				 * Gets the cuenta propia.
				 *
				 * @return the cuenta propia
				 */
				public String getCuentaPropia() {
					return cuentaPropia;
				}

				/**
				 * Sets the cuenta propia.
				 *
				 * @param cuentaPropia
				 *            the new cuenta propia
				 */
				public void setCuentaPropia(String cuentaPropia) {
					this.cuentaPropia = cuentaPropia;
				}

				/**
				 * Gets the value of the direccionEmail property.
				 * 
				 * @return possible object is {@link String }
				 * 
				 */
				public String getDireccionEmail() {
					return direccionEmail;
				}

				/**
				 * Sets the value of the direccionEmail property.
				 * 
				 * @param value
				 *            allowed object is {@link String }
				 * 
				 */
				public void setDireccionEmail(String value) {
					this.direccionEmail = value;
				}

				/**
				 * Gets the value of the nroRecurrencia property.
				 * 
				 * @return possible object is {@link Byte }
				 * 
				 */
				public Byte getNroRecurrencia() {
					return nroRecurrencia;
				}

				/**
				 * Sets the value of the nroRecurrencia property.
				 * 
				 * @param value
				 *            allowed object is {@link Byte }
				 * 
				 */
				public void setNroRecurrencia(Byte value) {
					this.nroRecurrencia = value;
				}

				/**
				 * Gets the value of the marcaLimite property.
				 * 
				 * @return possible object is {@link String }
				 * 
				 */
				public String getMarcaLimite() {
					return marcaLimite;
				}

				/**
				 * Sets the value of the marcaLimite property.
				 * 
				 * @param value
				 *            allowed object is {@link String }
				 * 
				 */
				public void setMarcaLimite(String value) {
					this.marcaLimite = value;
				}

				/**
				 * Gets the value of the tipoAgendamiento property.
				 * 
				 * @return possible object is {@link String }
				 * 
				 */
				public String getTipoAgendamiento() {
					return tipoAgendamiento;
				}

				/**
				 * Sets the value of the tipoAgendamiento property.
				 * 
				 * @param value
				 *            allowed object is {@link String }
				 * 
				 */
				public void setTipoAgendamiento(String value) {
					this.tipoAgendamiento = value;
				}

				/**
				 * Gets the value of the tipoOp property.
				 * 
				 * @return possible object is {@link Byte }
				 * 
				 */
				public Byte getTipoOp() {
					return tipoOp;
				}

				/**
				 * Sets the value of the tipoOp property.
				 * 
				 * @param value
				 *            allowed object is {@link Byte }
				 * 
				 */
				public void setTipoOp(Byte value) {
					this.tipoOp = value;
				}

				/**
				 * Gets the value of the nup property.
				 * 
				 * @return possible object is {@link Integer }
				 * 
				 */
				public Integer getNUP() {
					return nup;
				}

				/**
				 * Sets the value of the nup property.
				 * 
				 * @param value
				 *            allowed object is {@link Integer }
				 * 
				 */
				public void setNUP(Integer value) {
					this.nup = value;
				}

				/**
				 * Gets the value of the fechaEjecucion property.
				 * 
				 * @return possible object is {@link Long }
				 * 
				 */
				public Long getFechaEjecucion() {
					return fechaEjecucion;
				}

				/**
				 * Sets the value of the fechaEjecucion property.
				 * 
				 * @param value
				 *            allowed object is {@link Long }
				 * 
				 */
				public void setFechaEjecucion(Long value) {
					this.fechaEjecucion = value;
				}

				/**
				 * Gets the value of the tipoId property.
				 * 
				 * @return possible object is {@link String }
				 * 
				 */
				public String getTipoId() {
					return tipoId;
				}

				/**
				 * Sets the value of the tipoId property.
				 * 
				 * @param value
				 *            allowed object is {@link String }
				 * 
				 */
				public void setTipoId(String value) {
					this.tipoId = value;
				}

				/**
				 * Gets the value of the fechaNac property.
				 * 
				 * @return possible object is {@link Integer }
				 * 
				 */
				public Integer getFechaNac() {
					return fechaNac;
				}

				/**
				 * Sets the value of the fechaNac property.
				 * 
				 * @param value
				 *            allowed object is {@link Integer }
				 * 
				 */
				public void setFechaNac(Integer value) {
					this.fechaNac = value;
				}

				/**
				 * Gets the value of the ecoDatosEntrada property.
				 * 
				 * @return possible object is {@link String }
				 * 
				 */
				public String getEcoDatosEntrada() {
					return ecoDatosEntrada;
				}

				/**
				 * Sets the value of the ecoDatosEntrada property.
				 * 
				 * @param value
				 *            allowed object is {@link String }
				 * 
				 */
				public void setEcoDatosEntrada(String value) {
					this.ecoDatosEntrada = value;
				}

				/**
				 * Gets the value of the cbu property.
				 * 
				 * @return possible object is {@link BigInteger }
				 * 
				 */
				public String getCbu() {
					return cbu;
				}

				/**
				 * Sets the value of the cbu property.
				 * 
				 * @param value
				 *            allowed object is {@link BigInteger }
				 * 
				 */
				public void setCbu(String value) {
					this.cbu = value;
				}

				/**
				 * Gets the value of the hora property.
				 * 
				 * @return possible object is {@link Integer }
				 * 
				 */
				public Integer getHora() {
					return hora;
				}

				/**
				 * Sets the value of the hora property.
				 * 
				 * @param value
				 *            allowed object is {@link Integer }
				 * 
				 */
				public void setHora(Integer value) {
					this.hora = value;
				}

				/**
				 * Gets the value of the cantidadDias property.
				 * 
				 * @return possible object is {@link Byte }
				 * 
				 */
				public Byte getCantidadDias() {
					return cantidadDias;
				}

				/**
				 * Sets the value of the cantidadDias property.
				 * 
				 * @param value
				 *            allowed object is {@link Byte }
				 * 
				 */
				public void setCantidadDias(Byte value) {
					this.cantidadDias = value;
				}

				/**
				 * Gets the value of the canalTipoOriginal property.
				 * 
				 * @return possible object is {@link Byte }
				 * 
				 */
				public Byte getCanalTipoOriginal() {
					return canalTipoOriginal;
				}

				/**
				 * Sets the value of the canalTipoOriginal property.
				 * 
				 * @param value
				 *            allowed object is {@link Byte }
				 * 
				 */
				public void setCanalTipoOriginal(Byte value) {
					this.canalTipoOriginal = value;
				}

				/**
				 * Gets the value of the nroSecMM property.
				 * 
				 * @return possible object is {@link Byte }
				 * 
				 */
				public Byte getNroSecMM() {
					return nroSecMM;
				}

				/**
				 * Sets the value of the nroSecMM property.
				 * 
				 * @param value
				 *            allowed object is {@link Byte }
				 * 
				 */
				public void setNroSecMM(Byte value) {
					this.nroSecMM = value;
				}

				/**
				 * Gets the value of the entidadCtaCredito property.
				 * 
				 * @return possible object is {@link Byte }
				 * 
				 */
				public Byte getEntidadCtaCredito() {
					return entidadCtaCredito;
				}

				/**
				 * Sets the value of the entidadCtaCredito property.
				 * 
				 * @param value
				 *            allowed object is {@link Byte }
				 * 
				 */
				public void setEntidadCtaCredito(Byte value) {
					this.entidadCtaCredito = value;
				}

				/**
				 * Gets the value of the canalVersion property.
				 * 
				 * @return possible object is {@link Byte }
				 * 
				 */
				public Byte getCanalVersion() {
					return canalVersion;
				}

				/**
				 * Sets the value of the canalVersion property.
				 * 
				 * @param value
				 *            allowed object is {@link Byte }
				 * 
				 */
				public void setCanalVersion(Byte value) {
					this.canalVersion = value;
				}

				/**
				 * Gets the value of the referenciaUnivoca property.
				 * 
				 * @return possible object is {@link String }
				 * 
				 */
				public String getReferenciaUnivoca() {
					return referenciaUnivoca;
				}

				/**
				 * Sets the value of the referenciaUnivoca property.
				 * 
				 * @param value
				 *            allowed object is {@link String }
				 * 
				 */
				public void setReferenciaUnivoca(String value) {
					this.referenciaUnivoca = value;
				}

				/**
				 * Gets the value of the modoEjecucion property.
				 * 
				 * @return possible object is {@link String }
				 * 
				 */
				public String getModoEjecucion() {
					return modoEjecucion;
				}

				/**
				 * Sets the value of the modoEjecucion property.
				 * 
				 * @param value
				 *            allowed object is {@link String }
				 * 
				 */
				public void setModoEjecucion(String value) {
					this.modoEjecucion = value;
				}

				/**
				 * Gets the value of the indOpRev property.
				 * 
				 * @return possible object is {@link Byte }
				 * 
				 */
				public Byte getIndOpRev() {
					return indOpRev;
				}

				/**
				 * Sets the value of the indOpRev property.
				 * 
				 * @param value
				 *            allowed object is {@link Byte }
				 * 
				 */
				public void setIndOpRev(Byte value) {
					this.indOpRev = value;
				}

				/**
				 * Gets the value of the periodica property.
				 * 
				 * @return possible object is {@link String }
				 * 
				 */
				public String getPeriodica() {
					return periodica;
				}

				/**
				 * Sets the value of the periodica property.
				 * 
				 * @param value
				 *            allowed object is {@link String }
				 * 
				 */
				public void setPeriodica(String value) {
					this.periodica = value;
				}

				/**
				 * Gets the value of the tipoMensaje property.
				 * 
				 * @return possible object is {@link String }
				 * 
				 */
				public String getTipoMensaje() {
					return tipoMensaje;
				}

				/**
				 * Sets the value of the tipoMensaje property.
				 * 
				 * @param value
				 *            allowed object is {@link String }
				 * 
				 */
				public void setTipoMensaje(String value) {
					this.tipoMensaje = value;
				}

				/**
				 * Gets the value of the idSolAut property.
				 * 
				 * @return possible object is {@link Byte }
				 * 
				 */
				public Byte getIdSolAut() {
					return idSolAut;
				}

				/**
				 * Sets the value of the idSolAut property.
				 * 
				 * @param value
				 *            allowed object is {@link Byte }
				 * 
				 */
				public void setIdSolAut(Byte value) {
					this.idSolAut = value;
				}

				/**
				 * Gets the value of the logueoXML property.
				 * 
				 * @return possible object is {@link String }
				 * 
				 */
				public String getLogueoXML() {
					return logueoXML;
				}

				/**
				 * Sets the value of the logueoXML property.
				 * 
				 * @param value
				 *            allowed object is {@link String }
				 * 
				 */
				public void setLogueoXML(String value) {
					this.logueoXML = value;
				}

				/**
				 * Gets the value of the frecRecurrencia property.
				 * 
				 * @return possible object is {@link String }
				 * 
				 */
				public String getFrecRecurrencia() {
					return frecRecurrencia;
				}

				/**
				 * Sets the value of the frecRecurrencia property.
				 * 
				 * @param value
				 *            allowed object is {@link String }
				 * 
				 */
				public void setFrecRecurrencia(String value) {
					this.frecRecurrencia = value;
				}

				/**
				 * Gets the value of the caracteristicaCtaCredito property.
				 * 
				 * @return possible object is {@link Byte }
				 * 
				 */
				public String getCaracteristicaCtaCredito() {
					return caracteristicaCtaCredito;
				}

				/**
				 * Sets the value of the caracteristicaCtaCredito property.
				 * 
				 * @param value
				 *            allowed object is {@link Byte }
				 * 
				 */
				public void setCaracteristicaCtaCredito(String value) {
					this.caracteristicaCtaCredito = value;
				}

				/**
				 * Gets the value of the longParam property.
				 * 
				 * @return possible object is {@link Byte }
				 * 
				 */
				public Byte getLongParam() {
					return longParam;
				}

				/**
				 * Sets the value of the longParam property.
				 * 
				 * @param value
				 *            allowed object is {@link Byte }
				 * 
				 */
				public void setLongParam(Byte value) {
					this.longParam = value;
				}

				/**
				 * Gets the value of the tipoCtaDebito property.
				 * 
				 * @return possible object is {@link Byte }
				 * 
				 */
				public String getTipoCtaDebito() {
					return tipoCtaDebito;
				}

				/**
				 * Sets the value of the tipoCtaDebito property.
				 * 
				 * @param value
				 *            allowed object is {@link Byte }
				 * 
				 */
				public void setTipoCtaDebito(String value) {
					this.tipoCtaDebito = value;
				}

				/**
				 * Gets the value of the delimParam property.
				 * 
				 * @return possible object is {@link String }
				 * 
				 */
				public String getDelimParam() {
					return delimParam;
				}

				/**
				 * Sets the value of the delimParam property.
				 * 
				 * @param value
				 *            allowed object is {@link String }
				 * 
				 */
				public void setDelimParam(String value) {
					this.delimParam = value;
				}

				/**
				 * Gets the value of the logueoDatosRespuesta property.
				 * 
				 * @return possible object is {@link String }
				 * 
				 */
				public String getLogueoDatosRespuesta() {
					return logueoDatosRespuesta;
				}

				/**
				 * Sets the value of the logueoDatosRespuesta property.
				 * 
				 * @param value
				 *            allowed object is {@link String }
				 * 
				 */
				public void setLogueoDatosRespuesta(String value) {
					this.logueoDatosRespuesta = value;
				}

				/**
				 * Gets the value of the tipoEjec property.
				 * 
				 * @return possible object is {@link Byte }
				 * 
				 */
				public Byte getTipoEjec() {
					return tipoEjec;
				}

				/**
				 * Sets the value of the tipoEjec property.
				 * 
				 * @param value
				 *            allowed object is {@link Byte }
				 * 
				 */
				public void setTipoEjec(Byte value) {
					this.tipoEjec = value;
				}

				/**
				 * Gets the value of the informacDiscrecional property.
				 * 
				 * @return possible object is {@link String }
				 * 
				 */
				public String getInformacDiscrecional() {
					return informacDiscrecional;
				}

				/**
				 * Sets the value of the informacDiscrecional property.
				 * 
				 * @param value
				 *            allowed object is {@link String }
				 * 
				 */
				public void setInformacDiscrecional(String value) {
					this.informacDiscrecional = value;
				}

				/**
				 * Gets the value of the indSincro property.
				 * 
				 * @return possible object is {@link Byte }
				 * 
				 */
				public Byte getIndSincro() {
					return indSincro;
				}

				/**
				 * Sets the value of the indSincro property.
				 * 
				 * @param value
				 *            allowed object is {@link Byte }
				 * 
				 */
				public void setIndSincro(Byte value) {
					this.indSincro = value;
				}

				/**
				 * Gets the value of the tipoRecurrencia property.
				 * 
				 * @return possible object is {@link String }
				 * 
				 */
				public String getTipoRecurrencia() {
					return tipoRecurrencia;
				}

				/**
				 * Sets the value of the tipoRecurrencia property.
				 * 
				 * @param value
				 *            allowed object is {@link String }
				 * 
				 */
				public void setTipoRecurrencia(String value) {
					this.tipoRecurrencia = value;
				}

				/**
				 * Gets the value of the indicadorFuncion property.
				 * 
				 * @return possible object is {@link String }
				 * 
				 */
				public String getIndicadorFuncion() {
					return indicadorFuncion;
				}

				/**
				 * Sets the value of the indicadorFuncion property.
				 * 
				 * @param value
				 *            allowed object is {@link String }
				 * 
				 */
				public void setIndicadorFuncion(String value) {
					this.indicadorFuncion = value;
				}

				/**
				 * Gets the value of the usuarioAtrib property.
				 * 
				 * @return possible object is {@link String }
				 * 
				 */
				public String getUsuarioAtrib() {
					return usuarioAtrib;
				}

				/**
				 * Sets the value of the usuarioAtrib property.
				 * 
				 * @param value
				 *            allowed object is {@link String }
				 * 
				 */
				public void setUsuarioAtrib(String value) {
					this.usuarioAtrib = value;
				}

				/**
				 * Gets the value of the idSesionTx property.
				 * 
				 * @return possible object is {@link Byte }
				 * 
				 */
				public Byte getIdSesionTx() {
					return idSesionTx;
				}

				/**
				 * Sets the value of the idSesionTx property.
				 * 
				 * @param value
				 *            allowed object is {@link Byte }
				 * 
				 */
				public void setIdSesionTx(Byte value) {
					this.idSesionTx = value;
				}

				/**
				 * Gets the value of the versionSvc property.
				 * 
				 * @return possible object is {@link Short }
				 * 
				 */
				public Short getVersionSvc() {
					return versionSvc;
				}

				/**
				 * Sets the value of the versionSvc property.
				 * 
				 * @param value
				 *            allowed object is {@link Short }
				 * 
				 */
				public void setVersionSvc(Short value) {
					this.versionSvc = value;
				}

				/**
				 * Gets the value of the maxRecurrencia property.
				 * 
				 * @return possible object is {@link Byte }
				 * 
				 */
				public Byte getMaxRecurrencia() {
					return maxRecurrencia;
				}

				/**
				 * Sets the value of the maxRecurrencia property.
				 * 
				 * @param value
				 *            allowed object is {@link Byte }
				 * 
				 */
				public void setMaxRecurrencia(Byte value) {
					this.maxRecurrencia = value;
				}

				/**
				 * Gets the value of the indAutenAut property.
				 * 
				 * @return possible object is {@link Byte }
				 * 
				 */
				public Byte getIndAutenAut() {
					return indAutenAut;
				}

				/**
				 * Sets the value of the indAutenAut property.
				 * 
				 * @param value
				 *            allowed object is {@link Byte }
				 * 
				 */
				public void setIndAutenAut(Byte value) {
					this.indAutenAut = value;
				}

				/**
				 * Gets the value of the tipoSeg property.
				 * 
				 * @return possible object is {@link Byte }
				 * 
				 */
				public Byte getTipoSeg() {
					return tipoSeg;
				}

				/**
				 * Sets the value of the tipoSeg property.
				 * 
				 * @param value
				 *            allowed object is {@link Byte }
				 * 
				 */
				public void setTipoSeg(Byte value) {
					this.tipoSeg = value;
				}

				/**
				 * Gets the value of the indBTLS property.
				 * 
				 * @return possible object is {@link Byte }
				 * 
				 */
				public Byte getIndBTLS() {
					return indBTLS;
				}

				/**
				 * Sets the value of the indBTLS property.
				 * 
				 * @param value
				 *            allowed object is {@link Byte }
				 * 
				 */
				public void setIndBTLS(Byte value) {
					this.indBTLS = value;
				}

				/**
				 * Gets the value of the user property.
				 * 
				 * @return possible object is {@link Byte }
				 * 
				 */
				public Byte getUser() {
					return user;
				}

				/**
				 * Sets the value of the user property.
				 * 
				 * @param value
				 *            allowed object is {@link Byte }
				 * 
				 */
				public void setUser(Byte value) {
					this.user = value;
				}

				/**
				 * Gets the value of the autReq property.
				 * 
				 * @return possible object is {@link Byte }
				 * 
				 */
				public Byte getAutReq() {
					return autReq;
				}

				/**
				 * Sets the value of the autReq property.
				 * 
				 * @param value
				 *            allowed object is {@link Byte }
				 * 
				 */
				public void setAutReq(Byte value) {
					this.autReq = value;
				}

				/**
				 * Gets the value of the usuarioPwd property.
				 * 
				 * @return possible object is {@link String }
				 * 
				 */
				public String getUsuarioPwd() {
					return usuarioPwd;
				}

				/**
				 * Sets the value of the usuarioPwd property.
				 * 
				 * @param value
				 *            allowed object is {@link String }
				 * 
				 */
				public void setUsuarioPwd(String value) {
					this.usuarioPwd = value;
				}

				/**
				 * Gets the value of the incluirTramas property.
				 * 
				 * @return possible object is {@link String }
				 * 
				 */
				public String getIncluirTramas() {
					return incluirTramas;
				}

				/**
				 * Sets the value of the incluirTramas property.
				 * 
				 * @param value
				 *            allowed object is {@link String }
				 * 
				 */
				public void setIncluirTramas(String value) {
					this.incluirTramas = value;
				}

				/**
				 * Gets the value of the infoAdicional property.
				 * 
				 * @return possible object is {@link String }
				 * 
				 */
				public String getInfoAdicional() {
					return infoAdicional;
				}

				/**
				 * Sets the value of the infoAdicional property.
				 * 
				 * @param value
				 *            allowed object is {@link String }
				 * 
				 */
				public void setInfoAdicional(String value) {
					this.infoAdicional = value;
				}

				/**
				 * Gets the comprobante back end.
				 *
				 * @return the comprobante back end
				 */
				public String getComprobanteBackEnd() {
					return comprobanteBackEnd;
				}

				/**
				 * Sets the comprobante back end.
				 *
				 * @param comprobanteBackEnd
				 *            the new comprobante back end
				 */
				public void setComprobanteBackEnd(String comprobanteBackEnd) {
					this.comprobanteBackEnd = comprobanteBackEnd;
				}

				/**
				 * Gets the value of the tipoIdAut05 property.
				 * 
				 * @return possible object is {@link Byte }
				 * 
				 */
				public Byte getTipoIdAut05() {
					return tipoIdAut05;
				}

				/**
				 * Sets the value of the tipoIdAut05 property.
				 * 
				 * @param value
				 *            allowed object is {@link Byte }
				 * 
				 */
				public void setTipoIdAut05(Byte value) {
					this.tipoIdAut05 = value;
				}

				/**
				 * Gets the value of the tipoIdAut04 property.
				 * 
				 * @return possible object is {@link Byte }
				 * 
				 */
				public Byte getTipoIdAut04() {
					return tipoIdAut04;
				}

				/**
				 * Sets the value of the tipoIdAut04 property.
				 * 
				 * @param value
				 *            allowed object is {@link Byte }
				 * 
				 */
				public void setTipoIdAut04(Byte value) {
					this.tipoIdAut04 = value;
				}

				/**
				 * Gets the value of the tipoIdAut03 property.
				 * 
				 * @return possible object is {@link Byte }
				 * 
				 */
				public Byte getTipoIdAut03() {
					return tipoIdAut03;
				}

				/**
				 * Sets the value of the tipoIdAut03 property.
				 * 
				 * @param value
				 *            allowed object is {@link Byte }
				 * 
				 */
				public void setTipoIdAut03(Byte value) {
					this.tipoIdAut03 = value;
				}

				/**
				 * Gets the value of the idAut05 property.
				 * 
				 * @return possible object is {@link Byte }
				 * 
				 */
				public Byte getIdAut05() {
					return idAut05;
				}

				/**
				 * Sets the value of the idAut05 property.
				 * 
				 * @param value
				 *            allowed object is {@link Byte }
				 * 
				 */
				public void setIdAut05(Byte value) {
					this.idAut05 = value;
				}

				/**
				 * Gets the value of the tipoIdAut02 property.
				 * 
				 * @return possible object is {@link Byte }
				 * 
				 */
				public Byte getTipoIdAut02() {
					return tipoIdAut02;
				}

				/**
				 * Sets the value of the tipoIdAut02 property.
				 * 
				 * @param value
				 *            allowed object is {@link Byte }
				 * 
				 */
				public void setTipoIdAut02(Byte value) {
					this.tipoIdAut02 = value;
				}

				/**
				 * Gets the value of the tpoCtaFromBane property.
				 * 
				 * @return possible object is {@link Byte }
				 * 
				 */
				public Byte getTpoCtaFromBane() {
					return tpoCtaFromBane;
				}

				/**
				 * Sets the value of the tpoCtaFromBane property.
				 * 
				 * @param value
				 *            allowed object is {@link Byte }
				 * 
				 */
				public void setTpoCtaFromBane(Byte value) {
					this.tpoCtaFromBane = value;
				}

				/**
				 * Gets the value of the idAut04 property.
				 * 
				 * @return possible object is {@link Byte }
				 * 
				 */
				public Byte getIdAut04() {
					return idAut04;
				}

				/**
				 * Sets the value of the idAut04 property.
				 * 
				 * @param value
				 *            allowed object is {@link Byte }
				 * 
				 */
				public void setIdAut04(Byte value) {
					this.idAut04 = value;
				}

				/**
				 * Gets the value of the tipoIdAut01 property.
				 * 
				 * @return possible object is {@link Byte }
				 * 
				 */
				public Byte getTipoIdAut01() {
					return tipoIdAut01;
				}

				/**
				 * Sets the value of the tipoIdAut01 property.
				 * 
				 * @param value
				 *            allowed object is {@link Byte }
				 * 
				 */
				public void setTipoIdAut01(Byte value) {
					this.tipoIdAut01 = value;
				}

				/**
				 * Gets the value of the codConcepto property.
				 * 
				 * @return possible object is {@link Byte }
				 * 
				 */
				public String getCodConcepto() {
					return codConcepto;
				}

				/**
				 * Sets the value of the codConcepto property.
				 * 
				 * @param value
				 *            allowed object is {@link Byte }
				 * 
				 */
				public void setCodConcepto(String value) {
					this.codConcepto = value;
				}

				/**
				 * Gets the value of the idAut03 property.
				 * 
				 * @return possible object is {@link Byte }
				 * 
				 */
				public Byte getIdAut03() {
					return idAut03;
				}

				/**
				 * Sets the value of the idAut03 property.
				 * 
				 * @param value
				 *            allowed object is {@link Byte }
				 * 
				 */
				public void setIdAut03(Byte value) {
					this.idAut03 = value;
				}

				/**
				 * Gets the value of the tipoResp property.
				 * 
				 * @return possible object is {@link Byte }
				 * 
				 */
				public Byte getTipoResp() {
					return tipoResp;
				}

				/**
				 * Sets the value of the tipoResp property.
				 * 
				 * @param value
				 *            allowed object is {@link Byte }
				 * 
				 */
				public void setTipoResp(Byte value) {
					this.tipoResp = value;
				}

				/**
				 * Gets the value of the fechaBaseRecurrencia property.
				 * 
				 * @return possible object is {@link Integer }
				 * 
				 */
				public Integer getFechaBaseRecurrencia() {
					return fechaBaseRecurrencia;
				}

				/**
				 * Sets the value of the fechaBaseRecurrencia property.
				 * 
				 * @param value
				 *            allowed object is {@link Integer }
				 * 
				 */
				public void setFechaBaseRecurrencia(Integer value) {
					this.fechaBaseRecurrencia = value;
				}

				/**
				 * Gets the value of the bancoReceptor property.
				 * 
				 * @return possible object is {@link String }
				 * 
				 */
				public String getBancoReceptor() {
					return bancoReceptor;
				}

				/**
				 * Sets the value of the bancoReceptor property.
				 * 
				 * @param value
				 *            allowed object is {@link String }
				 * 
				 */
				public void setBancoReceptor(String value) {
					this.bancoReceptor = value;
				}

				/**
				 * Gets the value of the idAut02 property.
				 * 
				 * @return possible object is {@link Byte }
				 * 
				 */
				public Byte getIdAut02() {
					return idAut02;
				}

				/**
				 * Sets the value of the idAut02 property.
				 * 
				 * @param value
				 *            allowed object is {@link Byte }
				 * 
				 */
				public void setIdAut02(Byte value) {
					this.idAut02 = value;
				}

				/**
				 * Gets the value of the tpoCtaToBane property.
				 * 
				 * @return possible object is {@link Byte }
				 * 
				 */
				public Byte getTpoCtaToBane() {
					return tpoCtaToBane;
				}

				/**
				 * Sets the value of the tpoCtaToBane property.
				 * 
				 * @param value
				 *            allowed object is {@link Byte }
				 * 
				 */
				public void setTpoCtaToBane(Byte value) {
					this.tpoCtaToBane = value;
				}

				/**
				 * Gets the value of the idAut01 property.
				 * 
				 * @return possible object is {@link Byte }
				 * 
				 */
				public Byte getIdAut01() {
					return idAut01;
				}

				/**
				 * Sets the value of the idAut01 property.
				 * 
				 * @param value
				 *            allowed object is {@link Byte }
				 * 
				 */
				public void setIdAut01(Byte value) {
					this.idAut01 = value;
				}

				/**
				 * Gets the value of the usuarioTipo property.
				 * 
				 * @return possible object is {@link Byte }
				 * 
				 */
				public Byte getUsuarioTipo() {
					return usuarioTipo;
				}

				/**
				 * Sets the value of the usuarioTipo property.
				 * 
				 * @param value
				 *            allowed object is {@link Byte }
				 * 
				 */
				public void setUsuarioTipo(Byte value) {
					this.usuarioTipo = value;
				}

				/**
				 * Gets the value of the marcaGravado property.
				 * 
				 * @return possible object is {@link String }
				 * 
				 */
				public String getMarcaGravado() {
					return marcaGravado;
				}

				/**
				 * Sets the value of the marcaGravado property.
				 * 
				 * @param value
				 *            allowed object is {@link String }
				 * 
				 */
				public void setMarcaGravado(String value) {
					this.marcaGravado = value;
				}

				/**
				 * Gets the value of the longparam property.
				 * 
				 * @return possible object is {@link Short }
				 * 
				 */
				public Short getLONGPARAM() {
					return longparam;
				}

				/**
				 * Sets the value of the longparam property.
				 * 
				 * @param value
				 *            allowed object is {@link Short }
				 * 
				 */
				public void setLONGPARAM(Short value) {
					this.longparam = value;
				}

				/**
				 * Gets the value of the tipoCompr property.
				 * 
				 * @return possible object is {@link Byte }
				 * 
				 */
				public Byte getTipoCompr() {
					return tipoCompr;
				}

				/**
				 * Sets the value of the tipoCompr property.
				 * 
				 * @param value
				 *            allowed object is {@link Byte }
				 * 
				 */
				public void setTipoCompr(Byte value) {
					this.tipoCompr = value;
				}

				/**
				 * Gets the value of the versionHdr property.
				 * 
				 * @return possible object is {@link Short }
				 * 
				 */
				public Short getVersionHdr() {
					return versionHdr;
				}

				/**
				 * Sets the value of the versionHdr property.
				 * 
				 * @param value
				 *            allowed object is {@link Short }
				 * 
				 */
				public void setVersionHdr(Short value) {
					this.versionHdr = value;
				}

				/**
				 * Gets the value of the bancoDestino property.
				 * 
				 * @return possible object is {@link String }
				 * 
				 */
				public String getBancoDestino() {
					return bancoDestino;
				}

				/**
				 * Sets the value of the bancoDestino property.
				 * 
				 * @param value
				 *            allowed object is {@link String }
				 * 
				 */
				public void setBancoDestino(String value) {
					this.bancoDestino = value;
				}

				/**
				 * Gets the value of the idServicio property.
				 * 
				 * @return possible object is {@link String }
				 * 
				 */
				public String getIdServicio() {
					return idServicio;
				}

				/**
				 * Sets the value of the idServicio property.
				 * 
				 * @param value
				 *            allowed object is {@link String }
				 * 
				 */
				public void setIdServicio(String value) {
					this.idServicio = value;
				}

				/**
				 * Gets the value of the tipoAut05 property.
				 * 
				 * @return possible object is {@link Byte }
				 * 
				 */
				public Byte getTipoAut05() {
					return tipoAut05;
				}

				/**
				 * Sets the value of the tipoAut05 property.
				 * 
				 * @param value
				 *            allowed object is {@link Byte }
				 * 
				 */
				public void setTipoAut05(Byte value) {
					this.tipoAut05 = value;
				}

				/**
				 * Gets the value of the tipoAut04 property.
				 * 
				 * @return possible object is {@link Byte }
				 * 
				 */
				public Byte getTipoAut04() {
					return tipoAut04;
				}

				/**
				 * Sets the value of the tipoAut04 property.
				 * 
				 * @param value
				 *            allowed object is {@link Byte }
				 * 
				 */
				public void setTipoAut04(Byte value) {
					this.tipoAut04 = value;
				}

				/**
				 * Gets the value of the longCtaDesBane property.
				 * 
				 * @return possible object is {@link Byte }
				 * 
				 */
				public Byte getLongCtaDesBane() {
					return longCtaDesBane;
				}

				/**
				 * Sets the value of the longCtaDesBane property.
				 * 
				 * @param value
				 *            allowed object is {@link Byte }
				 * 
				 */
				public void setLongCtaDesBane(Byte value) {
					this.longCtaDesBane = value;
				}

				/**
				 * Gets the value of the tipoAut03 property.
				 * 
				 * @return possible object is {@link Byte }
				 * 
				 */
				public Byte getTipoAut03() {
					return tipoAut03;
				}

				/**
				 * Sets the value of the tipoAut03 property.
				 * 
				 * @param value
				 *            allowed object is {@link Byte }
				 * 
				 */
				public void setTipoAut03(Byte value) {
					this.tipoAut03 = value;
				}

				/**
				 * Gets the value of the cantAut property.
				 * 
				 * @return possible object is {@link Byte }
				 * 
				 */
				public Byte getCantAut() {
					return cantAut;
				}

				/**
				 * Sets the value of the cantAut property.
				 * 
				 * @param value
				 *            allowed object is {@link Byte }
				 * 
				 */
				public void setCantAut(Byte value) {
					this.cantAut = value;
				}

				/**
				 * Gets the value of the tipoAut02 property.
				 * 
				 * @return possible object is {@link Byte }
				 * 
				 */
				public Byte getTipoAut02() {
					return tipoAut02;
				}

				/**
				 * Sets the value of the tipoAut02 property.
				 * 
				 * @param value
				 *            allowed object is {@link Byte }
				 * 
				 */
				public void setTipoAut02(Byte value) {
					this.tipoAut02 = value;
				}

				/**
				 * Gets the value of the tipoAut01 property.
				 * 
				 * @return possible object is {@link Byte }
				 * 
				 */
				public Byte getTipoAut01() {
					return tipoAut01;
				}

				/**
				 * Sets the value of the tipoAut01 property.
				 * 
				 * @param value
				 *            allowed object is {@link Byte }
				 * 
				 */
				public void setTipoAut01(Byte value) {
					this.tipoAut01 = value;
				}

				/**
				 * Gets the value of the subcanalTipo property.
				 * 
				 * @return possible object is {@link Byte }
				 * 
				 */
				public Byte getSubcanalTipo() {
					return subcanalTipo;
				}

				/**
				 * Sets the value of the subcanalTipo property.
				 * 
				 * @param value
				 *            allowed object is {@link Byte }
				 * 
				 */
				public void setSubcanalTipo(Byte value) {
					this.subcanalTipo = value;
				}

				/**
				 * Gets the value of the titular property.
				 * 
				 * @return possible object is {@link String }
				 * 
				 */
				public String getTitular() {
					return titular;
				}

				/**
				 * Sets the value of the titular property.
				 * 
				 * @param value
				 *            allowed object is {@link String }
				 * 
				 */
				public void setTitular(String value) {
					this.titular = value;
				}

				/**
				 * Gets the value of the accionMM property.
				 * 
				 * @return possible object is {@link Byte }
				 * 
				 */
				public Byte getAccionMM() {
					return accionMM;
				}

				/**
				 * Sets the value of the accionMM property.
				 * 
				 * @param value
				 *            allowed object is {@link Byte }
				 * 
				 */
				public void setAccionMM(Byte value) {
					this.accionMM = value;
				}

				/**
				 * Gets the value of the firmanteCtaDebito property.
				 * 
				 * @return possible object is {@link Byte }
				 * 
				 */
				public Byte getFirmanteCtaDebito() {
					return firmanteCtaDebito;
				}

				/**
				 * Sets the value of the firmanteCtaDebito property.
				 * 
				 * @param value
				 *            allowed object is {@link Byte }
				 * 
				 */
				public void setFirmanteCtaDebito(Byte value) {
					this.firmanteCtaDebito = value;
				}

				/**
				 * Gets the value of the digitoCtaCredito property.
				 * 
				 * @return possible object is {@link Byte }
				 * 
				 */
				public Byte getDigitoCtaCredito() {
					return digitoCtaCredito;
				}

				/**
				 * Sets the value of the digitoCtaCredito property.
				 * 
				 * @param value
				 *            allowed object is {@link Byte }
				 * 
				 */
				public void setDigitoCtaCredito(Byte value) {
					this.digitoCtaCredito = value;
				}

				/**
				 * Gets the value of the idCliente property.
				 * 
				 * @return possible object is {@link Integer }
				 * 
				 */
				public Integer getIdCliente() {
					return idCliente;
				}

				/**
				 * Sets the value of the idCliente property.
				 * 
				 * @param value
				 *            allowed object is {@link Integer }
				 * 
				 */
				public void setIdCliente(Integer value) {
					this.idCliente = value;
				}

				/**
				 * Gets the value of the monedaTransferencia property.
				 * 
				 * @return possible object is {@link Byte }
				 * 
				 */
				public String getMonedaTransferencia() {
					return monedaTransferencia;
				}

				/**
				 * Sets the value of the monedaTransferencia property.
				 * 
				 * @param value
				 *            allowed object is {@link Byte }
				 * 
				 */
				public void setMonedaTransferencia(String value) {
					this.monedaTransferencia = value;
				}

				/**
				 * Gets the value of the pinAut05 property.
				 * 
				 * @return possible object is {@link Byte }
				 * 
				 */
				public Byte getPinAut05() {
					return pinAut05;
				}

				/**
				 * Sets the value of the pinAut05 property.
				 * 
				 * @param value
				 *            allowed object is {@link Byte }
				 * 
				 */
				public void setPinAut05(Byte value) {
					this.pinAut05 = value;
				}

				/**
				 * Gets the value of the comentario property.
				 * 
				 * @return possible object is {@link String }
				 * 
				 */
				public String getComentario() {
					return comentario;
				}

				/**
				 * Sets the value of the comentario property.
				 * 
				 * @param value
				 *            allowed object is {@link String }
				 * 
				 */
				public void setComentario(String value) {
					this.comentario = value;
				}

				/**
				 * Gets the value of the pinAut04 property.
				 * 
				 * @return possible object is {@link Byte }
				 * 
				 */
				public Byte getPinAut04() {
					return pinAut04;
				}

				/**
				 * Sets the value of the pinAut04 property.
				 * 
				 * @param value
				 *            allowed object is {@link Byte }
				 * 
				 */
				public void setPinAut04(Byte value) {
					this.pinAut04 = value;
				}

				/**
				 * Gets the value of the pinAut03 property.
				 * 
				 * @return possible object is {@link Byte }
				 * 
				 */
				public Byte getPinAut03() {
					return pinAut03;
				}

				/**
				 * Sets the value of the pinAut03 property.
				 * 
				 * @param value
				 *            allowed object is {@link Byte }
				 * 
				 */
				public void setPinAut03(Byte value) {
					this.pinAut03 = value;
				}

				/**
				 * Gets the value of the pinAut02 property.
				 * 
				 * @return possible object is {@link Byte }
				 * 
				 */
				public Byte getPinAut02() {
					return pinAut02;
				}

				/**
				 * Sets the value of the pinAut02 property.
				 * 
				 * @param value
				 *            allowed object is {@link Byte }
				 * 
				 */
				public void setPinAut02(Byte value) {
					this.pinAut02 = value;
				}

				/**
				 * Gets the value of the pinAut01 property.
				 * 
				 * @return possible object is {@link Byte }
				 * 
				 */
				public Byte getPinAut01() {
					return pinAut01;
				}

				/**
				 * Sets the value of the pinAut01 property.
				 * 
				 * @param value
				 *            allowed object is {@link Byte }
				 * 
				 */
				public void setPinAut01(Byte value) {
					this.pinAut01 = value;
				}

				/**
				 * Gets the value of the logueoAgendaHistorica property.
				 * 
				 * @return possible object is {@link String }
				 * 
				 */
				public String getLogueoAgendaHistorica() {
					return logueoAgendaHistorica;
				}

				/**
				 * Sets the value of the logueoAgendaHistorica property.
				 * 
				 * @param value
				 *            allowed object is {@link String }
				 * 
				 */
				public void setLogueoAgendaHistorica(String value) {
					this.logueoAgendaHistorica = value;
				}

				/**
				 * Gets the value of the canalTipo property.
				 * 
				 * @return possible object is {@link Byte }
				 * 
				 */
				public Byte getCanalTipo() {
					return canalTipo;
				}

				/**
				 * Sets the value of the canalTipo property.
				 * 
				 * @param value
				 *            allowed object is {@link Byte }
				 * 
				 */
				public void setCanalTipo(Byte value) {
					this.canalTipo = value;
				}

				/**
				 * Gets the value of the tipoTransferencia property.
				 * 
				 * @return possible object is {@link Byte }
				 * 
				 */
				public Byte getTipoTransferencia() {
					return tipoTransferencia;
				}

				/**
				 * Sets the value of the tipoTransferencia property.
				 * 
				 * @param value
				 *            allowed object is {@link Byte }
				 * 
				 */
				public void setTipoTransferencia(Byte value) {
					this.tipoTransferencia = value;
				}

				/**
				 * Gets the value of the titularCredito property.
				 * 
				 * @return possible object is {@link String }
				 * 
				 */
				public String getTitularCredito() {
					return titularCredito;
				}

				/**
				 * Sets the value of the titularCredito property.
				 * 
				 * @param value
				 *            allowed object is {@link String }
				 * 
				 */
				public void setTitularCredito(String value) {
					this.titularCredito = value;
				}

				/**
				 * Gets the value of the fecha property.
				 * 
				 * @return possible object is {@link Integer }
				 * 
				 */
				public Integer getFecha() {
					return fecha;
				}

				/**
				 * Sets the value of the fecha property.
				 * 
				 * @param value
				 *            allowed object is {@link Integer }
				 * 
				 */
				public void setFecha(Integer value) {
					this.fecha = value;
				}

				/**
				 * Gets the value of the nombre property.
				 * 
				 * @return possible object is {@link String }
				 * 
				 */
				public String getNombre() {
					return nombre;
				}

				/**
				 * Sets the value of the nombre property.
				 * 
				 * @param value
				 *            allowed object is {@link String }
				 * 
				 */
				public void setNombre(String value) {
					this.nombre = value;
				}

				/**
				 * Gets the value of the nroOpCanal property.
				 * 
				 * @return possible object is {@link Byte }
				 * 
				 */
				public Byte getNroOpCanal() {
					return nroOpCanal;
				}

				/**
				 * Sets the value of the nroOpCanal property.
				 * 
				 * @param value
				 *            allowed object is {@link Byte }
				 * 
				 */
				public void setNroOpCanal(Byte value) {
					this.nroOpCanal = value;
				}

				/**
				 * Gets the value of the fiid property.
				 * 
				 * @return possible object is {@link Byte }
				 * 
				 */
				public Byte getFiid() {
					return fiid;
				}

				/**
				 * Sets the value of the fiid property.
				 * 
				 * @param value
				 *            allowed object is {@link Byte }
				 * 
				 */
				public void setFiid(Byte value) {
					this.fiid = value;
				}

				/**
				 * Gets the value of the funcAut property.
				 * 
				 * @return possible object is {@link Byte }
				 * 
				 */
				public Byte getFuncAut() {
					return funcAut;
				}

				/**
				 * Sets the value of the funcAut property.
				 * 
				 * @param value
				 *            allowed object is {@link Byte }
				 * 
				 */
				public void setFuncAut(Byte value) {
					this.funcAut = value;
				}

				/**
				 * Gets the value of the nroReq property.
				 * 
				 * @return possible object is {@link Byte }
				 * 
				 */
				public Byte getNroReq() {
					return nroReq;
				}

				/**
				 * Sets the value of the nroReq property.
				 * 
				 * @param value
				 *            allowed object is {@link Byte }
				 * 
				 */
				public void setNroReq(Byte value) {
					this.nroReq = value;
				}

				/**
				 * <p>
				 * Java class for anonymous complex type.
				 * 
				 * <p>
				 * The following schema fragment specifies the expected content
				 * contained within this class.
				 * 
				 * <pre>
				 * &lt;complexType>
				 *   &lt;complexContent>
				 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
				 *       &lt;sequence>
				 *         &lt;element name="VersionXML" type="{http://www.w3.org/2001/XMLSchema}string"/>
				 *         &lt;element name="LogueoDatosRespuesta" type="{http://www.w3.org/2001/XMLSchema}string"/>
				 *       &lt;/sequence>
				 *     &lt;/restriction>
				 *   &lt;/complexContent>
				 * &lt;/complexType>
				 * </pre>
				 * 
				 * 
				 */
				@XmlAccessorType(XmlAccessType.FIELD)
				@XmlType(name = "", propOrder = { "versionXML", "logueoDatosRespuesta" })
				public static class CONFIG {

					/** The version XML. */
					@XmlElement(name = "VersionXML", required = true)
					protected String versionXML;

					/** The logueo datos respuesta. */
					@XmlElement(name = "LogueoDatosRespuesta", required = true)
					protected String logueoDatosRespuesta;

					/**
					 * Gets the value of the versionXML property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getVersionXML() {
						return versionXML;
					}

					/**
					 * Sets the value of the versionXML property.
					 * 
					 * @param value
					 *            allowed object is {@link String }
					 * 
					 */
					public void setVersionXML(String value) {
						this.versionXML = value;
					}

					/**
					 * Gets the value of the logueoDatosRespuesta property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getLogueoDatosRespuesta() {
						return logueoDatosRespuesta;
					}

					/**
					 * Sets the value of the logueoDatosRespuesta property.
					 * 
					 * @param value
					 *            allowed object is {@link String }
					 * 
					 */
					public void setLogueoDatosRespuesta(String value) {
						this.logueoDatosRespuesta = value;
					}

				}

				/**
				 * <p>
				 * Java class for anonymous complex type.
				 * 
				 * <p>
				 * The following schema fragment specifies the expected content
				 * contained within this class.
				 * 
				 * <pre>
				 * &lt;complexType>
				 *   &lt;complexContent>
				 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
				 *       &lt;sequence>
				 *         &lt;element name="InformacDiscrecional" type="{http://www.w3.org/2001/XMLSchema}string"/>
				 *         &lt;element name="Cbu" type="{http://www.w3.org/2001/XMLSchema}integer"/>
				 *         &lt;element name="CaracteristicaCtaCredito" type="{http://www.w3.org/2001/XMLSchema}byte"/>
				 *         &lt;element name="MonedaTransferencia" type="{http://www.w3.org/2001/XMLSchema}byte"/>
				 *         &lt;element name="LongCtaDesBane" type="{http://www.w3.org/2001/XMLSchema}byte"/>
				 *         &lt;element name="Cuit3" type="{http://www.w3.org/2001/XMLSchema}string"/>
				 *         &lt;element name="Cuit2" type="{http://www.w3.org/2001/XMLSchema}string"/>
				 *         &lt;element name="UsuarioTipo" type="{http://www.w3.org/2001/XMLSchema}byte"/>
				 *         &lt;element name="Cuit1" type="{http://www.w3.org/2001/XMLSchema}long"/>
				 *         &lt;element name="MarcaGravado" type="{http://www.w3.org/2001/XMLSchema}string"/>
				 *         &lt;element name="LogueoAgendaHistorica" type="{http://www.w3.org/2001/XMLSchema}string"/>
				 *         &lt;element name="NroCtaDebito" type="{http://www.w3.org/2001/XMLSchema}int"/>
				 *         &lt;element name="IndicadorFuncion" type="{http://www.w3.org/2001/XMLSchema}string"/>
				 *         &lt;element name="Fiid" type="{http://www.w3.org/2001/XMLSchema}byte"/>
				 *         &lt;element name="IdSesionCnt" type="{http://www.w3.org/2001/XMLSchema}string"/>
				 *         &lt;element name="IndAuten" type="{http://www.w3.org/2001/XMLSchema}byte"/>
				 *         &lt;element name="Titular" type="{http://www.w3.org/2001/XMLSchema}string"/>
				 *         &lt;element name="TpoCtaFromBane" type="{http://www.w3.org/2001/XMLSchema}byte"/>
				 *         &lt;element name="SubcanalId" type="{http://www.w3.org/2001/XMLSchema}byte"/>
				 *         &lt;element name="CtaBane" type="{http://www.w3.org/2001/XMLSchema}long"/>
				 *         &lt;element name="User" type="{http://www.w3.org/2001/XMLSchema}byte"/>
				 *         &lt;element name="PlazoAcreditacion" type="{http://www.w3.org/2001/XMLSchema}byte"/>
				 *         &lt;element name="DigitoCtaCredito" type="{http://www.w3.org/2001/XMLSchema}byte"/>
				 *         &lt;element name="UsuarioAtrib" type="{http://www.w3.org/2001/XMLSchema}string"/>
				 *         &lt;element name="FechaNac" type="{http://www.w3.org/2001/XMLSchema}int"/>
				 *         &lt;element name="TipoPersona" type="{http://www.w3.org/2001/XMLSchema}string"/>
				 *         &lt;element name="DescConcepto" type="{http://www.w3.org/2001/XMLSchema}string"/>
				 *         &lt;element name="MarcaLimite" type="{http://www.w3.org/2001/XMLSchema}string"/>
				 *         &lt;element name="ReferenciaUnivoca" type="{http://www.w3.org/2001/XMLSchema}string"/>
				 *         &lt;element name="IdentificBeneficiario" type="{http://www.w3.org/2001/XMLSchema}long"/>
				 *         &lt;element name="FirmanteCtaDebito" type="{http://www.w3.org/2001/XMLSchema}byte"/>
				 *         &lt;element name="CanalId" type="{http://www.w3.org/2001/XMLSchema}string"/>
				 *         &lt;element name="CodConcepto" type="{http://www.w3.org/2001/XMLSchema}byte"/>
				 *         &lt;element name="SucCtaCredito" type="{http://www.w3.org/2001/XMLSchema}byte"/>
				 *         &lt;element name="BancoReceptor" type="{http://www.w3.org/2001/XMLSchema}string"/>
				 *         &lt;element name="IPMaquina" type="{http://www.w3.org/2001/XMLSchema}long"/>
				 *         &lt;element name="Periodica" type="{http://www.w3.org/2001/XMLSchema}string"/>
				 *         &lt;element name="NombreCtaCredito" type="{http://www.w3.org/2001/XMLSchema}string"/>
				 *         &lt;element name="EcoDatosEntrada" type="{http://www.w3.org/2001/XMLSchema}string"/>
				 *         &lt;element name="NroCtaCredito" type="{http://www.w3.org/2001/XMLSchema}long"/>
				 *         &lt;element name="SucCtaDebito" type="{http://www.w3.org/2001/XMLSchema}byte"/>
				 *         &lt;element name="TipoCtaDebito" type="{http://www.w3.org/2001/XMLSchema}byte"/>
				 *         &lt;element name="CantidadDias" type="{http://www.w3.org/2001/XMLSchema}byte"/>
				 *         &lt;element name="EntidadCtaCredito" type="{http://www.w3.org/2001/XMLSchema}byte"/>
				 *         &lt;element name="BancoDestino" type="{http://www.w3.org/2001/XMLSchema}string"/>
				 *         &lt;element name="ImporteTransferencia" type="{http://www.w3.org/2001/XMLSchema}float"/>
				 *         &lt;element name="TipoId" type="{http://www.w3.org/2001/XMLSchema}string"/>
				 *         &lt;element name="CanalVersion" type="{http://www.w3.org/2001/XMLSchema}byte"/>
				 *         &lt;element name="IdCliente" type="{http://www.w3.org/2001/XMLSchema}int"/>
				 *         &lt;element name="TipoTransferencia" type="{http://www.w3.org/2001/XMLSchema}byte"/>
				 *         &lt;element name="VersionXML" type="{http://www.w3.org/2001/XMLSchema}string"/>
				 *         &lt;element name="TpoCtaToBane" type="{http://www.w3.org/2001/XMLSchema}byte"/>
				 *       &lt;/sequence>
				 *     &lt;/restriction>
				 *   &lt;/complexContent>
				 * &lt;/complexType>
				 * </pre>
				 * 
				 * 
				 */
				@XmlAccessorType(XmlAccessType.FIELD)
				@XmlType(name = "", propOrder = { "informacDiscrecional", "cbu", "caracteristicaCtaCredito",
						"monedaTransferencia", "longCtaDesBane", "cuit3", "cuit2", "usuarioTipo", "cuit1",
						"marcaGravado", "logueoAgendaHistorica", "nroCtaDebito", "indicadorFuncion", "fiid",
						"idSesionCnt", "indAuten", "titular", "tpoCtaFromBane", "subcanalId", "ctaBane", "user",
						"plazoAcreditacion", "digitoCtaCredito", "usuarioAtrib", "fechaNac", "tipoPersona",
						"descConcepto", "marcaLimite", "referenciaUnivoca", "identificBeneficiario",
						"firmanteCtaDebito", "canalId", "codConcepto", "sucCtaCredito", "bancoReceptor", "ipMaquina",
						"periodica", "nombreCtaCredito", "ecoDatosEntrada", "nroCtaCredito", "sucCtaDebito",
						"tipoCtaDebito", "cantidadDias", "entidadCtaCredito", "bancoDestino", "importeTransferencia",
						"tipoId", "canalVersion", "idCliente", "tipoTransferencia", "versionXML", "tpoCtaToBane",
						"importeDebito", "importe", "tipoCtaCredito", "codigoConcepto", "descripcionConcepto",
						"anotacionesPersonales", "mailCredito", "comentarios", "comentario", "comprobanteBackEnd",
						"cuentaPropia", "datosAdicionales", "indTransfDiferida", "titularCredito" })
				public static class DATOSENTRADA {

					/** The datos adicionales. */
					@XmlElement(name = "DatosAdicionales")
					protected XMLResponseEntity.DATOSRESULTADO.Registro.XMLEntrada.DatosAdicionales datosAdicionales;

					/** The informac discrecional. */
					@XmlElement(name = "InformacDiscrecional", required = true)
					protected String informacDiscrecional;

					/** The cbu. */
					@XmlElement(name = "Cbu", required = true)
					protected String cbu;

					/** The caracteristica cta credito. */
					@XmlElement(name = "CaracteristicaCtaCredito")
					protected String caracteristicaCtaCredito;

					/** The moneda transferencia. */
					@XmlElement(name = "MonedaTransferencia")
					protected String monedaTransferencia;

					/** The long cta des bane. */
					@XmlElement(name = "LongCtaDesBane")
					protected byte longCtaDesBane;

					/** The cuit 3. */
					@XmlElement(name = "Cuit3", required = true)
					protected String cuit3;

					/** The cuit 2. */
					@XmlElement(name = "Cuit2", required = true)
					protected String cuit2;

					/** The usuario tipo. */
					@XmlElement(name = "UsuarioTipo")
					protected byte usuarioTipo;

					/** The cuit 1. */
					@XmlElement(name = "Cuit1")
					protected String cuit1;

					/** The cuit 1. */
					@XmlElement(name = "TitularCredito")
					protected String titularCredito;

					/** The marca gravado. */
					@XmlElement(name = "MarcaGravado", required = true)
					protected String marcaGravado;

					/** The logueo agenda historica. */
					@XmlElement(name = "LogueoAgendaHistorica", required = true)
					protected String logueoAgendaHistorica;

					/** The nro cta debito. */
					@XmlElement(name = "NroCtaDebito")
					protected String nroCtaDebito;

					/** The indicador funcion. */
					@XmlElement(name = "IndicadorFuncion", required = true)
					protected String indicadorFuncion;

					/** The indicador funcion. */
					@XmlElement(name = "IndTransfDiferida", required = true)
					protected String indTransfDiferida;

					/** The fiid. */
					@XmlElement(name = "Fiid")
					protected byte fiid;

					/** The id sesion cnt. */
					@XmlElement(name = "IdSesionCnt", required = true)
					protected String idSesionCnt;

					/** The ind auten. */
					@XmlElement(name = "IndAuten")
					protected byte indAuten;

					/** The titular. */
					@XmlElement(name = "Titular", required = true)
					protected String titular;

					/** The tpo cta from bane. */
					@XmlElement(name = "TpoCtaFromBane")
					protected byte tpoCtaFromBane;

					/** The subcanal id. */
					@XmlElement(name = "SubcanalId")
					protected byte subcanalId;

					/** The cta bane. */
					@XmlElement(name = "CtaBane")
					protected long ctaBane;

					/** The user. */
					@XmlElement(name = "User")
					protected byte user;

					/** The plazo acreditacion. */
					@XmlElement(name = "PlazoAcreditacion")
					protected String plazoAcreditacion;

					/** The digito cta credito. */
					@XmlElement(name = "DigitoCtaCredito")
					protected byte digitoCtaCredito;

					/** The usuario atrib. */
					@XmlElement(name = "UsuarioAtrib", required = true)
					protected String usuarioAtrib;

					/** The fecha nac. */
					@XmlElement(name = "FechaNac")
					protected int fechaNac;

					/** The tipo persona. */
					@XmlElement(name = "TipoPersona", required = true)
					protected String tipoPersona;

					/** The desc concepto. */
					@XmlElement(name = "DescConcepto", required = true)
					protected String descConcepto;

					/** The marca limite. */
					@XmlElement(name = "MarcaLimite", required = true)
					protected String marcaLimite;

					/** The referencia univoca. */
					@XmlElement(name = "ReferenciaUnivoca", required = true)
					protected String referenciaUnivoca;

					/** The identific beneficiario. */
					@XmlElement(name = "IdentificBeneficiario")
					protected String identificBeneficiario;

					/** The firmante cta debito. */
					@XmlElement(name = "FirmanteCtaDebito")
					protected byte firmanteCtaDebito;

					/** The canal id. */
					@XmlElement(name = "CanalId", required = true)
					protected String canalId;

					/** The cod concepto. */
					@XmlElement(name = "CodConcepto")
					protected byte codConcepto;

					/** The codigo concepto. */
					@XmlElement(name = "CodigoConcepto")
					protected String codigoConcepto;

					/** The descripcion concepto. */
					@XmlElement(name = "DescripcionConcepto")
					protected String descripcionConcepto;

					/** The suc cta credito. */
					@XmlElement(name = "SucCtaCredito")
					protected String sucCtaCredito;

					/** The banco receptor. */
					@XmlElement(name = "BancoReceptor", required = true)
					protected String bancoReceptor;

					/** The ip maquina. */
					@XmlElement(name = "IPMaquina")
					protected long ipMaquina;

					/** The periodica. */
					@XmlElement(name = "Periodica", required = true)
					protected String periodica;

					/** The nombre cta credito. */
					@XmlElement(name = "NombreCtaCredito", required = true)
					protected String nombreCtaCredito;

					/** The eco datos entrada. */
					@XmlElement(name = "EcoDatosEntrada", required = true)
					protected String ecoDatosEntrada;

					/** The nro cta credito. */
					@XmlElement(name = "NroCtaCredito")
					protected String nroCtaCredito;

					/** The suc cta debito. */
					@XmlElement(name = "SucCtaDebito")
					protected String sucCtaDebito;

					/** The tipo cta debito. */
					@XmlElement(name = "TipoCtaDebito")
					protected String tipoCtaDebito;

					/** The tipo cta credito. */
					@XmlElement(name = "TipoCtaCredito")
					protected String tipoCtaCredito;

					/** The cantidad dias. */
					@XmlElement(name = "CantidadDias")
					protected byte cantidadDias;

					/** The entidad cta credito. */
					@XmlElement(name = "EntidadCtaCredito")
					protected byte entidadCtaCredito;

					/** The banco destino. */
					@XmlElement(name = "BancoDestino", required = true)
					protected String bancoDestino;

					/** The importe transferencia. */
					@XmlElement(name = "ImporteTransferencia")
					protected String importeTransferencia;

					/** The tipo id. */
					@XmlElement(name = "TipoId", required = true)
					protected String tipoId;

					/** The canal version. */
					@XmlElement(name = "CanalVersion")
					protected byte canalVersion;

					/** The id cliente. */
					@XmlElement(name = "IdCliente")
					protected int idCliente;

					/** The tipo transferencia. */
					@XmlElement(name = "TipoTransferencia")
					protected byte tipoTransferencia;

					/** The version XML. */
					@XmlElement(name = "VersionXML", required = true)
					protected String versionXML;

					/** The tpo cta to bane. */
					@XmlElement(name = "TpoCtaToBane")
					protected byte tpoCtaToBane;

					/** The importe debito. */
					@XmlElement(name = "ImporteDebito")
					protected String importeDebito;

					/** The importe. */
					@XmlElement(name = "Importe")
					protected String importe;

					/** The anotaciones personales. */
					@XmlElement(name = "AnotacionesPersonales")
					protected String anotacionesPersonales;

					/** The mail credito. */
					@XmlElement(name = "MailCredito")
					protected String mailCredito;

					/** The comentarios. */
					@XmlElement(name = "Comentarios")
					protected String comentarios;

					/** The comentario. */
					@XmlElement(name = "Comentario")
					protected String comentario;

					/** The comentario. */
					@XmlElement(name = "CuentaPropia")
					protected String cuentaPropia;

					/** The comprobante back end. */
					@XmlElement(name = "ComprobanteBackEnd")
					protected String comprobanteBackEnd;

					/**
					 * Gets the value of the informacDiscrecional property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getInformacDiscrecional() {
						return informacDiscrecional;
					}

					/**
					 * Sets the value of the informacDiscrecional property.
					 * 
					 * @param value
					 *            allowed object is {@link String }
					 * 
					 */
					public void setInformacDiscrecional(String value) {
						this.informacDiscrecional = value;
					}

					/**
					 * Gets the value of the cbu property.
					 * 
					 * @return possible object is {@link BigInteger }
					 * 
					 */
					public String getCbu() {
						return cbu;
					}

					/**
					 * Sets the value of the cbu property.
					 * 
					 * @param value
					 *            allowed object is {@link BigInteger }
					 * 
					 */
					public void setCbu(String value) {
						this.cbu = value;
					}

					/**
					 * Gets the importe debito.
					 *
					 * @return the importe debito
					 */
					public String getImporteDebito() {
						return importeDebito;
					}

					/**
					 * Sets the importe debito.
					 *
					 * @param importeDebito
					 *            the new importe debito
					 */
					public void setImporteDebito(String importeDebito) {
						this.importeDebito = importeDebito;
					}

					/**
					 * Gets the ind transf diferida.
					 *
					 * @return the ind transf diferida
					 */
					public String getIndTransfDiferida() {
						return indTransfDiferida;
					}

					/**
					 * Sets the ind transf diferida.
					 *
					 * @param indTransfDiferida
					 *            the new ind transf diferida
					 */
					public void setIndTransfDiferida(String indTransfDiferida) {
						this.indTransfDiferida = indTransfDiferida;
					}

					/**
					 * Gets the cuenta propia.
					 *
					 * @return the cuenta propia
					 */
					public String getCuentaPropia() {
						return cuentaPropia;
					}

					/**
					 * Sets the cuenta propia.
					 *
					 * @param cuentaPropia
					 *            the new cuenta propia
					 */
					public void setCuentaPropia(String cuentaPropia) {
						this.cuentaPropia = cuentaPropia;
					}

					/**
					 * Gets the importe.
					 *
					 * @return the importe
					 */
					public String getImporte() {
						return importe;
					}

					/**
					 * Sets the importe.
					 *
					 * @param importe
					 *            the new importe
					 */
					public void setImporte(String importe) {
						this.importe = importe;
					}

					/**
					 * Gets the comentario.
					 *
					 * @return the comentario
					 */
					public String getComentario() {
						return comentario;
					}

					/**
					 * Sets the comentario.
					 *
					 * @param comentario
					 *            the new comentario
					 */
					public void setComentario(String comentario) {
						this.comentario = comentario;
					}

					/**
					 * Gets the value of the caracteristicaCtaCredito property.
					 *
					 * @return the caracteristica cta credito
					 */
					public String getCaracteristicaCtaCredito() {
						return caracteristicaCtaCredito;
					}

					/**
					 * Sets the value of the caracteristicaCtaCredito property.
					 *
					 * @param value
					 *            the new caracteristica cta credito
					 */
					public void setCaracteristicaCtaCredito(String value) {
						this.caracteristicaCtaCredito = value;
					}

					/**
					 * Gets the value of the monedaTransferencia property.
					 *
					 * @return the moneda transferencia
					 */
					public String getMonedaTransferencia() {
						return monedaTransferencia;
					}

					/**
					 * Sets the value of the monedaTransferencia property.
					 *
					 * @param value
					 *            the new moneda transferencia
					 */
					public void setMonedaTransferencia(String value) {
						this.monedaTransferencia = value;
					}

					/**
					 * Gets the value of the longCtaDesBane property.
					 *
					 * @return the long cta des bane
					 */
					public byte getLongCtaDesBane() {
						return longCtaDesBane;
					}

					/**
					 * Sets the value of the longCtaDesBane property.
					 *
					 * @param value
					 *            the new long cta des bane
					 */
					public void setLongCtaDesBane(byte value) {
						this.longCtaDesBane = value;
					}

					/**
					 * Gets the value of the cuit3 property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getCuit3() {
						return cuit3;
					}

					/**
					 * Sets the value of the cuit3 property.
					 * 
					 * @param value
					 *            allowed object is {@link String }
					 * 
					 */
					public void setCuit3(String value) {
						this.cuit3 = value;
					}

					/**
					 * Gets the value of the cuit2 property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getCuit2() {
						return cuit2;
					}

					/**
					 * Sets the value of the cuit2 property.
					 * 
					 * @param value
					 *            allowed object is {@link String }
					 * 
					 */
					public void setCuit2(String value) {
						this.cuit2 = value;
					}

					/**
					 * Gets the value of the usuarioTipo property.
					 *
					 * @return the usuario tipo
					 */
					public byte getUsuarioTipo() {
						return usuarioTipo;
					}

					/**
					 * Sets the value of the usuarioTipo property.
					 *
					 * @param value
					 *            the new usuario tipo
					 */
					public void setUsuarioTipo(byte value) {
						this.usuarioTipo = value;
					}

					/**
					 * Gets the value of the cuit1 property.
					 *
					 * @return the cuit 1
					 */
					public String getCuit1() {
						return cuit1;
					}

					/**
					 * Sets the value of the cuit1 property.
					 *
					 * @param value
					 *            the new cuit 1
					 */
					public void setCuit1(String value) {
						this.cuit1 = value;
					}

					/**
					 * Gets the value of the marcaGravado property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getMarcaGravado() {
						return marcaGravado;
					}

					/**
					 * Sets the value of the marcaGravado property.
					 * 
					 * @param value
					 *            allowed object is {@link String }
					 * 
					 */
					public void setMarcaGravado(String value) {
						this.marcaGravado = value;
					}

					/**
					 * Gets the value of the logueoAgendaHistorica property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getLogueoAgendaHistorica() {
						return logueoAgendaHistorica;
					}

					/**
					 * Sets the value of the logueoAgendaHistorica property.
					 * 
					 * @param value
					 *            allowed object is {@link String }
					 * 
					 */
					public void setLogueoAgendaHistorica(String value) {
						this.logueoAgendaHistorica = value;
					}

					/**
					 * Gets the value of the nroCtaDebito property.
					 *
					 * @return the nro cta debito
					 */
					public String getNroCtaDebito() {
						return nroCtaDebito;
					}

					/**
					 * Sets the value of the nroCtaDebito property.
					 *
					 * @param value
					 *            the new nro cta debito
					 */
					public void setNroCtaDebito(String value) {
						this.nroCtaDebito = value;
					}

					/**
					 * Gets the titular credito.
					 *
					 * @return the titular credito
					 */
					public String getTitularCredito() {
						return titularCredito;
					}

					/**
					 * Sets the titular credito.
					 *
					 * @param titularCredito
					 *            the new titular credito
					 */
					public void setTitularCredito(String titularCredito) {
						this.titularCredito = titularCredito;
					}

					/**
					 * Gets the value of the indicadorFuncion property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getIndicadorFuncion() {
						return indicadorFuncion;
					}

					/**
					 * Sets the value of the indicadorFuncion property.
					 * 
					 * @param value
					 *            allowed object is {@link String }
					 * 
					 */
					public void setIndicadorFuncion(String value) {
						this.indicadorFuncion = value;
					}

					/**
					 * Gets the value of the fiid property.
					 *
					 * @return the fiid
					 */
					public byte getFiid() {
						return fiid;
					}

					/**
					 * Sets the value of the fiid property.
					 *
					 * @param value
					 *            the new fiid
					 */
					public void setFiid(byte value) {
						this.fiid = value;
					}

					/**
					 * Gets the value of the idSesionCnt property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getIdSesionCnt() {
						return idSesionCnt;
					}

					/**
					 * Sets the value of the idSesionCnt property.
					 * 
					 * @param value
					 *            allowed object is {@link String }
					 * 
					 */
					public void setIdSesionCnt(String value) {
						this.idSesionCnt = value;
					}

					/**
					 * Gets the value of the indAuten property.
					 *
					 * @return the ind auten
					 */
					public byte getIndAuten() {
						return indAuten;
					}

					/**
					 * Sets the value of the indAuten property.
					 *
					 * @param value
					 *            the new ind auten
					 */
					public void setIndAuten(byte value) {
						this.indAuten = value;
					}

					/**
					 * Gets the value of the titular property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getTitular() {
						return titular;
					}

					/**
					 * Sets the value of the titular property.
					 * 
					 * @param value
					 *            allowed object is {@link String }
					 * 
					 */
					public void setTitular(String value) {
						this.titular = value;
					}

					/**
					 * Gets the descripcion concepto.
					 *
					 * @return the descripcion concepto
					 */
					public String getDescripcionConcepto() {
						return descripcionConcepto;
					}

					/**
					 * Sets the descripcion concepto.
					 *
					 * @param descripcionConcepto
					 *            the new descripcion concepto
					 */
					public void setDescripcionConcepto(String descripcionConcepto) {
						this.descripcionConcepto = descripcionConcepto;
					}

					/**
					 * Gets the anotaciones personales.
					 *
					 * @return the anotaciones personales
					 */
					public String getAnotacionesPersonales() {
						return anotacionesPersonales;
					}

					/**
					 * Sets the anotaciones personales.
					 *
					 * @param anotacionesPersonales
					 *            the new anotaciones personales
					 */
					public void setAnotacionesPersonales(String anotacionesPersonales) {
						this.anotacionesPersonales = anotacionesPersonales;
					}

					/**
					 * Gets the mail credito.
					 *
					 * @return the mail credito
					 */
					public String getMailCredito() {
						return mailCredito;
					}

					/**
					 * Sets the mail credito.
					 *
					 * @param mailCredito
					 *            the new mail credito
					 */
					public void setMailCredito(String mailCredito) {
						this.mailCredito = mailCredito;
					}

					/**
					 * Gets the comentarios.
					 *
					 * @return the comentarios
					 */
					public String getComentarios() {
						return comentarios;
					}

					/**
					 * Sets the comentarios.
					 *
					 * @param comentarios
					 *            the new comentarios
					 */
					public void setComentarios(String comentarios) {
						this.comentarios = comentarios;
					}

					/**
					 * Gets the comprobante back end.
					 *
					 * @return the comprobante back end
					 */
					public String getComprobanteBackEnd() {
						return comprobanteBackEnd;
					}

					/**
					 * Sets the comprobante back end.
					 *
					 * @param comprobanteBackEnd
					 *            the new comprobante back end
					 */
					public void setComprobanteBackEnd(String comprobanteBackEnd) {
						this.comprobanteBackEnd = comprobanteBackEnd;
					}

					/**
					 * Gets the value of the tpoCtaFromBane property.
					 *
					 * @return the tpo cta from bane
					 */
					public byte getTpoCtaFromBane() {
						return tpoCtaFromBane;
					}

					/**
					 * Sets the value of the tpoCtaFromBane property.
					 *
					 * @param value
					 *            the new tpo cta from bane
					 */
					public void setTpoCtaFromBane(byte value) {
						this.tpoCtaFromBane = value;
					}

					/**
					 * Gets the value of the subcanalId property.
					 *
					 * @return the subcanal id
					 */
					public byte getSubcanalId() {
						return subcanalId;
					}

					/**
					 * Sets the value of the subcanalId property.
					 *
					 * @param value
					 *            the new subcanal id
					 */
					public void setSubcanalId(byte value) {
						this.subcanalId = value;
					}

					/**
					 * Gets the value of the ctaBane property.
					 *
					 * @return the cta bane
					 */
					public long getCtaBane() {
						return ctaBane;
					}

					/**
					 * Sets the value of the ctaBane property.
					 *
					 * @param value
					 *            the new cta bane
					 */
					public void setCtaBane(long value) {
						this.ctaBane = value;
					}

					/**
					 * Gets the value of the user property.
					 *
					 * @return the user
					 */
					public byte getUser() {
						return user;
					}

					/**
					 * Sets the value of the user property.
					 *
					 * @param value
					 *            the new user
					 */
					public void setUser(byte value) {
						this.user = value;
					}

					/**
					 * Gets the value of the plazoAcreditacion property.
					 *
					 * @return the plazo acreditacion
					 */
					public String getPlazoAcreditacion() {
						return plazoAcreditacion;
					}

					/**
					 * Sets the value of the plazoAcreditacion property.
					 *
					 * @param value
					 *            the new plazo acreditacion
					 */
					public void setPlazoAcreditacion(String value) {
						this.plazoAcreditacion = value;
					}

					/**
					 * Gets the value of the digitoCtaCredito property.
					 *
					 * @return the digito cta credito
					 */
					public byte getDigitoCtaCredito() {
						return digitoCtaCredito;
					}

					/**
					 * Sets the value of the digitoCtaCredito property.
					 *
					 * @param value
					 *            the new digito cta credito
					 */
					public void setDigitoCtaCredito(byte value) {
						this.digitoCtaCredito = value;
					}

					/**
					 * Gets the value of the usuarioAtrib property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getUsuarioAtrib() {
						return usuarioAtrib;
					}

					/**
					 * Sets the value of the usuarioAtrib property.
					 * 
					 * @param value
					 *            allowed object is {@link String }
					 * 
					 */
					public void setUsuarioAtrib(String value) {
						this.usuarioAtrib = value;
					}

					/**
					 * Gets the value of the fechaNac property.
					 *
					 * @return the fecha nac
					 */
					public int getFechaNac() {
						return fechaNac;
					}

					/**
					 * Sets the value of the fechaNac property.
					 *
					 * @param value
					 *            the new fecha nac
					 */
					public void setFechaNac(int value) {
						this.fechaNac = value;
					}

					/**
					 * Gets the value of the tipoPersona property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getTipoPersona() {
						return tipoPersona;
					}

					/**
					 * Sets the value of the tipoPersona property.
					 * 
					 * @param value
					 *            allowed object is {@link String }
					 * 
					 */
					public void setTipoPersona(String value) {
						this.tipoPersona = value;
					}

					/**
					 * Gets the value of the descConcepto property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getDescConcepto() {
						return descConcepto;
					}

					/**
					 * Sets the value of the descConcepto property.
					 * 
					 * @param value
					 *            allowed object is {@link String }
					 * 
					 */
					public void setDescConcepto(String value) {
						this.descConcepto = value;
					}

					/**
					 * Gets the value of the marcaLimite property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getMarcaLimite() {
						return marcaLimite;
					}

					/**
					 * Sets the value of the marcaLimite property.
					 * 
					 * @param value
					 *            allowed object is {@link String }
					 * 
					 */
					public void setMarcaLimite(String value) {
						this.marcaLimite = value;
					}

					/**
					 * Gets the value of the referenciaUnivoca property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getReferenciaUnivoca() {
						return referenciaUnivoca;
					}

					/**
					 * Sets the value of the referenciaUnivoca property.
					 * 
					 * @param value
					 *            allowed object is {@link String }
					 * 
					 */
					public void setReferenciaUnivoca(String value) {
						this.referenciaUnivoca = value;
					}

					/**
					 * Gets the value of the identificBeneficiario property.
					 *
					 * @return the identific beneficiario
					 */
					public String getIdentificBeneficiario() {
						return identificBeneficiario;
					}

					/**
					 * Sets the value of the identificBeneficiario property.
					 *
					 * @param value
					 *            the new identific beneficiario
					 */
					public void setIdentificBeneficiario(String value) {
						this.identificBeneficiario = value;
					}

					/**
					 * Gets the value of the firmanteCtaDebito property.
					 *
					 * @return the firmante cta debito
					 */
					public byte getFirmanteCtaDebito() {
						return firmanteCtaDebito;
					}

					/**
					 * Sets the value of the firmanteCtaDebito property.
					 *
					 * @param value
					 *            the new firmante cta debito
					 */
					public void setFirmanteCtaDebito(byte value) {
						this.firmanteCtaDebito = value;
					}

					/**
					 * Gets the value of the canalId property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getCanalId() {
						return canalId;
					}

					/**
					 * Sets the value of the canalId property.
					 * 
					 * @param value
					 *            allowed object is {@link String }
					 * 
					 */
					public void setCanalId(String value) {
						this.canalId = value;
					}

					/**
					 * Gets the value of the codConcepto property.
					 *
					 * @return the cod concepto
					 */
					public byte getCodConcepto() {
						return codConcepto;
					}

					/**
					 * Sets the value of the codConcepto property.
					 *
					 * @param value
					 *            the new cod concepto
					 */
					public void setCodConcepto(byte value) {
						this.codConcepto = value;
					}

					/**
					 * Gets the value of the sucCtaCredito property.
					 *
					 * @return the suc cta credito
					 */
					public String getSucCtaCredito() {
						return sucCtaCredito;
					}

					/**
					 * Sets the value of the sucCtaCredito property.
					 *
					 * @param value
					 *            the new suc cta credito
					 */
					public void setSucCtaCredito(String value) {
						this.sucCtaCredito = value;
					}

					/**
					 * Gets the value of the bancoReceptor property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getBancoReceptor() {
						return bancoReceptor;
					}

					/**
					 * Sets the value of the bancoReceptor property.
					 * 
					 * @param value
					 *            allowed object is {@link String }
					 * 
					 */
					public void setBancoReceptor(String value) {
						this.bancoReceptor = value;
					}

					/**
					 * Gets the value of the ipMaquina property.
					 *
					 * @return the IP maquina
					 */
					public long getIPMaquina() {
						return ipMaquina;
					}

					/**
					 * Sets the value of the ipMaquina property.
					 *
					 * @param value
					 *            the new IP maquina
					 */
					public void setIPMaquina(long value) {
						this.ipMaquina = value;
					}

					/**
					 * Gets the value of the periodica property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getPeriodica() {
						return periodica;
					}

					/**
					 * Sets the value of the periodica property.
					 * 
					 * @param value
					 *            allowed object is {@link String }
					 * 
					 */
					public void setPeriodica(String value) {
						this.periodica = value;
					}

					/**
					 * Gets the value of the nombreCtaCredito property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getNombreCtaCredito() {
						return nombreCtaCredito;
					}

					/**
					 * Sets the value of the nombreCtaCredito property.
					 * 
					 * @param value
					 *            allowed object is {@link String }
					 * 
					 */
					public void setNombreCtaCredito(String value) {
						this.nombreCtaCredito = value;
					}

					/**
					 * Gets the value of the ecoDatosEntrada property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getEcoDatosEntrada() {
						return ecoDatosEntrada;
					}

					/**
					 * Sets the value of the ecoDatosEntrada property.
					 * 
					 * @param value
					 *            allowed object is {@link String }
					 * 
					 */
					public void setEcoDatosEntrada(String value) {
						this.ecoDatosEntrada = value;
					}

					/**
					 * Gets the value of the nroCtaCredito property.
					 *
					 * @return the nro cta credito
					 */
					public String getNroCtaCredito() {
						return nroCtaCredito;
					}

					/**
					 * Sets the value of the nroCtaCredito property.
					 *
					 * @param value
					 *            the new nro cta credito
					 */
					public void setNroCtaCredito(String value) {
						this.nroCtaCredito = value;
					}

					/**
					 * Gets the value of the sucCtaDebito property.
					 *
					 * @return the suc cta debito
					 */
					public String getSucCtaDebito() {
						return sucCtaDebito;
					}

					/**
					 * Sets the value of the sucCtaDebito property.
					 *
					 * @param value
					 *            the new suc cta debito
					 */
					public void setSucCtaDebito(String value) {
						this.sucCtaDebito = value;
					}

					/**
					 * Gets the value of the tipoCtaDebito property.
					 *
					 * @return the tipo cta debito
					 */
					public String getTipoCtaDebito() {
						return tipoCtaDebito;
					}

					/**
					 * Sets the value of the tipoCtaDebito property.
					 *
					 * @param value
					 *            the new tipo cta debito
					 */
					public void setTipoCtaDebito(String value) {
						this.tipoCtaDebito = value;
					}

					/**
					 * Gets the value of the cantidadDias property.
					 *
					 * @return the cantidad dias
					 */
					public byte getCantidadDias() {
						return cantidadDias;
					}

					/**
					 * Sets the value of the cantidadDias property.
					 *
					 * @param value
					 *            the new cantidad dias
					 */
					public void setCantidadDias(byte value) {
						this.cantidadDias = value;
					}

					/**
					 * Gets the value of the entidadCtaCredito property.
					 *
					 * @return the entidad cta credito
					 */
					public byte getEntidadCtaCredito() {
						return entidadCtaCredito;
					}

					/**
					 * Sets the value of the entidadCtaCredito property.
					 *
					 * @param value
					 *            the new entidad cta credito
					 */
					public void setEntidadCtaCredito(byte value) {
						this.entidadCtaCredito = value;
					}

					/**
					 * Gets the codigo concepto.
					 *
					 * @return the codigo concepto
					 */
					public String getCodigoConcepto() {
						return codigoConcepto;
					}

					/**
					 * Sets the codigo concepto.
					 *
					 * @param codigoConcepto
					 *            the new codigo concepto
					 */
					public void setCodigoConcepto(String codigoConcepto) {
						this.codigoConcepto = codigoConcepto;
					}

					/**
					 * Gets the tipo cta credito.
					 *
					 * @return the tipo cta credito
					 */
					public String getTipoCtaCredito() {
						return tipoCtaCredito;
					}

					/**
					 * Sets the tipo cta credito.
					 *
					 * @param tipoCtaCredito
					 *            the new tipo cta credito
					 */
					public void setTipoCtaCredito(String tipoCtaCredito) {
						this.tipoCtaCredito = tipoCtaCredito;
					}

					/**
					 * Gets the value of the bancoDestino property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getBancoDestino() {
						return bancoDestino;
					}

					/**
					 * Sets the value of the bancoDestino property.
					 * 
					 * @param value
					 *            allowed object is {@link String }
					 * 
					 */
					public void setBancoDestino(String value) {
						this.bancoDestino = value;
					}

					/**
					 * Gets the value of the importeTransferencia property.
					 *
					 * @return the importe transferencia
					 */
					public String getImporteTransferencia() {
						return importeTransferencia;
					}

					/**
					 * Sets the value of the importeTransferencia property.
					 *
					 * @param value
					 *            the new importe transferencia
					 */
					public void setImporteTransferencia(String value) {
						this.importeTransferencia = value;
					}

					/**
					 * Gets the value of the tipoId property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getTipoId() {
						return tipoId;
					}

					/**
					 * Sets the value of the tipoId property.
					 * 
					 * @param value
					 *            allowed object is {@link String }
					 * 
					 */
					public void setTipoId(String value) {
						this.tipoId = value;
					}

					/**
					 * Gets the value of the canalVersion property.
					 *
					 * @return the canal version
					 */
					public byte getCanalVersion() {
						return canalVersion;
					}

					/**
					 * Sets the value of the canalVersion property.
					 *
					 * @param value
					 *            the new canal version
					 */
					public void setCanalVersion(byte value) {
						this.canalVersion = value;
					}

					/**
					 * Gets the value of the idCliente property.
					 *
					 * @return the id cliente
					 */
					public int getIdCliente() {
						return idCliente;
					}

					/**
					 * Sets the value of the idCliente property.
					 *
					 * @param value
					 *            the new id cliente
					 */
					public void setIdCliente(int value) {
						this.idCliente = value;
					}

					/**
					 * Gets the value of the tipoTransferencia property.
					 *
					 * @return the tipo transferencia
					 */
					public byte getTipoTransferencia() {
						return tipoTransferencia;
					}

					/**
					 * Sets the value of the tipoTransferencia property.
					 *
					 * @param value
					 *            the new tipo transferencia
					 */
					public void setTipoTransferencia(byte value) {
						this.tipoTransferencia = value;
					}

					/**
					 * Gets the value of the versionXML property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getVersionXML() {
						return versionXML;
					}

					/**
					 * Sets the value of the versionXML property.
					 * 
					 * @param value
					 *            allowed object is {@link String }
					 * 
					 */
					public void setVersionXML(String value) {
						this.versionXML = value;
					}

					/**
					 * Gets the datos adicionales.
					 *
					 * @return the datos adicionales
					 */
					public XMLResponseEntity.DATOSRESULTADO.Registro.XMLEntrada.DatosAdicionales getDatosAdicionales() {
						return datosAdicionales;
					}

					/**
					 * Sets the datos adicionales.
					 *
					 * @param datosAdicionales
					 *            the new datos adicionales
					 */
					public void setDatosAdicionales(
							XMLResponseEntity.DATOSRESULTADO.Registro.XMLEntrada.DatosAdicionales datosAdicionales) {
						this.datosAdicionales = datosAdicionales;
					}

					/**
					 * Gets the value of the tpoCtaToBane property.
					 *
					 * @return the tpo cta to bane
					 */
					public byte getTpoCtaToBane() {
						return tpoCtaToBane;
					}

					/**
					 * Sets the value of the tpoCtaToBane property.
					 *
					 * @param value
					 *            the new tpo cta to bane
					 */
					public void setTpoCtaToBane(byte value) {
						this.tpoCtaToBane = value;
					}

				}

				/**
				 * <p>
				 * Java class for anonymous complex type.
				 * 
				 * <p>
				 * The following schema fragment specifies the expected content
				 * contained within this class.
				 * 
				 * <pre>
				 * &lt;complexType>
				 *   &lt;complexContent>
				 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
				 *       &lt;sequence>
				 *         &lt;element name="DatosMensAvisos">
				 *           &lt;complexType>
				 *             &lt;complexContent>
				 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
				 *                 &lt;sequence>
				 *                   &lt;element name="Concepto" type="{http://www.w3.org/2001/XMLSchema}string"/>
				 *                   &lt;element name="TitularCredito" type="{http://www.w3.org/2001/XMLSchema}string"/>
				 *                   &lt;element name="DescripcionAdicional3" type="{http://www.w3.org/2001/XMLSchema}string"/>
				 *                   &lt;element name="DescripcionAdicional2" type="{http://www.w3.org/2001/XMLSchema}string"/>
				 *                   &lt;element name="DescripcionAdicional1" type="{http://www.w3.org/2001/XMLSchema}string"/>
				 *                   &lt;element name="MailClienteReply" type="{http://www.w3.org/2001/XMLSchema}string"/>
				 *                   &lt;element name="ConceptoAdicional3" type="{http://www.w3.org/2001/XMLSchema}string"/>
				 *                   &lt;element name="ConceptoAdicional2" type="{http://www.w3.org/2001/XMLSchema}string"/>
				 *                   &lt;element name="InfoAdicional" type="{http://www.w3.org/2001/XMLSchema}string"/>
				 *                   &lt;element name="ConceptoAdicional1" type="{http://www.w3.org/2001/XMLSchema}string"/>
				 *                   &lt;element name="AnotacionesPersonales" type="{http://www.w3.org/2001/XMLSchema}string"/>
				 *                   &lt;element name="Comentario" type="{http://www.w3.org/2001/XMLSchema}string"/>
				 *                   &lt;element name="MailCredito" type="{http://www.w3.org/2001/XMLSchema}string"/>
				 *                   &lt;element name="TitularDebito" type="{http://www.w3.org/2001/XMLSchema}string"/>
				 *                 &lt;/sequence>
				 *               &lt;/restriction>
				 *             &lt;/complexContent>
				 *           &lt;/complexType>
				 *         &lt;/element>
				 *       &lt;/sequence>
				 *     &lt;/restriction>
				 *   &lt;/complexContent>
				 * &lt;/complexType>
				 * </pre>
				 * 
				 * 
				 */
				@XmlAccessorType(XmlAccessType.FIELD)
				@XmlType(name = "", propOrder = { "datosMensAvisos", "datosSueldos" })
				public static class DatosAdicionales {

					/** The datos mens avisos. */
					@XmlElement(name = "DatosMensAvisos", required = true)
					protected XMLResponseEntity.DATOSRESULTADO.Registro.XMLEntrada.DatosAdicionales.DatosMensAvisos datosMensAvisos;

					/** The datos sueldos. */
					@XmlElement(name = "DatosSueldos")
					protected XMLResponseEntity.DATOSRESULTADO.Registro.DatosAdicional.DatosAdicionales.DatosSueldos datosSueldos;

					/**
					 * Gets the value of the datosMensAvisos property.
					 * 
					 * @return possible object is
					 *         {@link XMLResponseEntity.DATOSRESULTADO.Registro.XMLEntrada.DatosAdicionales.DatosMensAvisos }
					 * 
					 */
					public XMLResponseEntity.DATOSRESULTADO.Registro.XMLEntrada.DatosAdicionales.DatosMensAvisos getDatosMensAvisos() {
						return datosMensAvisos;
					}

					/**
					 * Sets the value of the datosMensAvisos property.
					 * 
					 * @param value
					 *            allowed object is
					 *            {@link XMLResponseEntity.DATOSRESULTADO.Registro.XMLEntrada.DatosAdicionales.DatosMensAvisos }
					 * 
					 */
					public void setDatosMensAvisos(
							XMLResponseEntity.DATOSRESULTADO.Registro.XMLEntrada.DatosAdicionales.DatosMensAvisos value) {
						this.datosMensAvisos = value;
					}

					/**
					 * Gets the datos sueldos.
					 *
					 * @return the datos sueldos
					 */
					public XMLResponseEntity.DATOSRESULTADO.Registro.DatosAdicional.DatosAdicionales.DatosSueldos getDatosSueldos() {
						return datosSueldos;
					}

					/**
					 * Sets the datos sueldos.
					 *
					 * @param datosSueldos
					 *            the new datos sueldos
					 */
					public void setDatosSueldos(
							XMLResponseEntity.DATOSRESULTADO.Registro.DatosAdicional.DatosAdicionales.DatosSueldos datosSueldos) {
						this.datosSueldos = datosSueldos;
					}

					/**
					 * <p>
					 * Java class for anonymous complex type.
					 * 
					 * <p>
					 * The following schema fragment specifies the expected
					 * content contained within this class.
					 * 
					 * <pre>
					 * &lt;complexType>
					 *   &lt;complexContent>
					 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
					 *       &lt;sequence>
					 *         &lt;element name="Concepto" type="{http://www.w3.org/2001/XMLSchema}string"/>
					 *         &lt;element name="TitularCredito" type="{http://www.w3.org/2001/XMLSchema}string"/>
					 *         &lt;element name="DescripcionAdicional3" type="{http://www.w3.org/2001/XMLSchema}string"/>
					 *         &lt;element name="DescripcionAdicional2" type="{http://www.w3.org/2001/XMLSchema}string"/>
					 *         &lt;element name="DescripcionAdicional1" type="{http://www.w3.org/2001/XMLSchema}string"/>
					 *         &lt;element name="MailClienteReply" type="{http://www.w3.org/2001/XMLSchema}string"/>
					 *         &lt;element name="ConceptoAdicional3" type="{http://www.w3.org/2001/XMLSchema}string"/>
					 *         &lt;element name="ConceptoAdicional2" type="{http://www.w3.org/2001/XMLSchema}string"/>
					 *         &lt;element name="InfoAdicional" type="{http://www.w3.org/2001/XMLSchema}string"/>
					 *         &lt;element name="ConceptoAdicional1" type="{http://www.w3.org/2001/XMLSchema}string"/>
					 *         &lt;element name="AnotacionesPersonales" type="{http://www.w3.org/2001/XMLSchema}string"/>
					 *         &lt;element name="Comentario" type="{http://www.w3.org/2001/XMLSchema}string"/>
					 *         &lt;element name="MailCredito" type="{http://www.w3.org/2001/XMLSchema}string"/>
					 *         &lt;element name="TitularDebito" type="{http://www.w3.org/2001/XMLSchema}string"/>
					 *       &lt;/sequence>
					 *     &lt;/restriction>
					 *   &lt;/complexContent>
					 * &lt;/complexType>
					 * </pre>
					 * 
					 * 
					 */
					@XmlAccessorType(XmlAccessType.FIELD)
					@XmlType(name = "", propOrder = { "concepto", "titularCredito", "descripcionAdicional3",
							"descripcionAdicional2", "descripcionAdicional1", "mailClienteReply", "conceptoAdicional3",
							"conceptoAdicional2", "infoAdicional", "conceptoAdicional1", "anotacionesPersonales",
							"comentario", "mailCredito", "titularDebito" })
					public static class DatosMensAvisos {

						/** The concepto. */
						@XmlElement(name = "Concepto", required = true)
						protected String concepto;

						/** The titular credito. */
						@XmlElement(name = "TitularCredito", required = true)
						protected String titularCredito;

						/** The descripcion adicional 3. */
						@XmlElement(name = "DescripcionAdicional3", required = true)
						protected String descripcionAdicional3;

						/** The descripcion adicional 2. */
						@XmlElement(name = "DescripcionAdicional2", required = true)
						protected String descripcionAdicional2;

						/** The descripcion adicional 1. */
						@XmlElement(name = "DescripcionAdicional1", required = true)
						protected String descripcionAdicional1;

						/** The mail cliente reply. */
						@XmlElement(name = "MailClienteReply", required = true)
						protected String mailClienteReply;

						/** The concepto adicional 3. */
						@XmlElement(name = "ConceptoAdicional3", required = true)
						protected String conceptoAdicional3;

						/** The concepto adicional 2. */
						@XmlElement(name = "ConceptoAdicional2", required = true)
						protected String conceptoAdicional2;

						/** The info adicional. */
						@XmlElement(name = "InfoAdicional", required = true)
						protected String infoAdicional;

						/** The concepto adicional 1. */
						@XmlElement(name = "ConceptoAdicional1", required = true)
						protected String conceptoAdicional1;

						/** The anotaciones personales. */
						@XmlElement(name = "AnotacionesPersonales", required = true)
						protected String anotacionesPersonales;

						/** The comentario. */
						@XmlElement(name = "Comentario", required = true)
						protected String comentario;

						/** The mail credito. */
						@XmlElement(name = "MailCredito", required = true)
						protected String mailCredito;

						/** The titular debito. */
						@XmlElement(name = "TitularDebito", required = true)
						protected String titularDebito;

						/**
						 * Gets the value of the concepto property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getConcepto() {
							return concepto;
						}

						/**
						 * Sets the value of the concepto property.
						 * 
						 * @param value
						 *            allowed object is {@link String }
						 * 
						 */
						public void setConcepto(String value) {
							this.concepto = value;
						}

						/**
						 * Gets the value of the titularCredito property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getTitularCredito() {
							return titularCredito;
						}

						/**
						 * Sets the value of the titularCredito property.
						 * 
						 * @param value
						 *            allowed object is {@link String }
						 * 
						 */
						public void setTitularCredito(String value) {
							this.titularCredito = value;
						}

						/**
						 * Gets the value of the descripcionAdicional3 property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getDescripcionAdicional3() {
							return descripcionAdicional3;
						}

						/**
						 * Sets the value of the descripcionAdicional3 property.
						 * 
						 * @param value
						 *            allowed object is {@link String }
						 * 
						 */
						public void setDescripcionAdicional3(String value) {
							this.descripcionAdicional3 = value;
						}

						/**
						 * Gets the value of the descripcionAdicional2 property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getDescripcionAdicional2() {
							return descripcionAdicional2;
						}

						/**
						 * Sets the value of the descripcionAdicional2 property.
						 * 
						 * @param value
						 *            allowed object is {@link String }
						 * 
						 */
						public void setDescripcionAdicional2(String value) {
							this.descripcionAdicional2 = value;
						}

						/**
						 * Gets the value of the descripcionAdicional1 property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getDescripcionAdicional1() {
							return descripcionAdicional1;
						}

						/**
						 * Sets the value of the descripcionAdicional1 property.
						 * 
						 * @param value
						 *            allowed object is {@link String }
						 * 
						 */
						public void setDescripcionAdicional1(String value) {
							this.descripcionAdicional1 = value;
						}

						/**
						 * Gets the value of the mailClienteReply property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getMailClienteReply() {
							return mailClienteReply;
						}

						/**
						 * Sets the value of the mailClienteReply property.
						 * 
						 * @param value
						 *            allowed object is {@link String }
						 * 
						 */
						public void setMailClienteReply(String value) {
							this.mailClienteReply = value;
						}

						/**
						 * Gets the value of the conceptoAdicional3 property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getConceptoAdicional3() {
							return conceptoAdicional3;
						}

						/**
						 * Sets the value of the conceptoAdicional3 property.
						 * 
						 * @param value
						 *            allowed object is {@link String }
						 * 
						 */
						public void setConceptoAdicional3(String value) {
							this.conceptoAdicional3 = value;
						}

						/**
						 * Gets the value of the conceptoAdicional2 property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getConceptoAdicional2() {
							return conceptoAdicional2;
						}

						/**
						 * Sets the value of the conceptoAdicional2 property.
						 * 
						 * @param value
						 *            allowed object is {@link String }
						 * 
						 */
						public void setConceptoAdicional2(String value) {
							this.conceptoAdicional2 = value;
						}

						/**
						 * Gets the value of the infoAdicional property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getInfoAdicional() {
							return infoAdicional;
						}

						/**
						 * Sets the value of the infoAdicional property.
						 * 
						 * @param value
						 *            allowed object is {@link String }
						 * 
						 */
						public void setInfoAdicional(String value) {
							this.infoAdicional = value;
						}

						/**
						 * Gets the value of the conceptoAdicional1 property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getConceptoAdicional1() {
							return conceptoAdicional1;
						}

						/**
						 * Sets the value of the conceptoAdicional1 property.
						 * 
						 * @param value
						 *            allowed object is {@link String }
						 * 
						 */
						public void setConceptoAdicional1(String value) {
							this.conceptoAdicional1 = value;
						}

						/**
						 * Gets the value of the anotacionesPersonales property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getAnotacionesPersonales() {
							return anotacionesPersonales;
						}

						/**
						 * Sets the value of the anotacionesPersonales property.
						 * 
						 * @param value
						 *            allowed object is {@link String }
						 * 
						 */
						public void setAnotacionesPersonales(String value) {
							this.anotacionesPersonales = value;
						}

						/**
						 * Gets the value of the comentario property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getComentario() {
							return comentario;
						}

						/**
						 * Sets the value of the comentario property.
						 * 
						 * @param value
						 *            allowed object is {@link String }
						 * 
						 */
						public void setComentario(String value) {
							this.comentario = value;
						}

						/**
						 * Gets the value of the mailCredito property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getMailCredito() {
							return mailCredito;
						}

						/**
						 * Sets the value of the mailCredito property.
						 * 
						 * @param value
						 *            allowed object is {@link String }
						 * 
						 */
						public void setMailCredito(String value) {
							this.mailCredito = value;
						}

						/**
						 * Gets the value of the titularDebito property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getTitularDebito() {
							return titularDebito;
						}

						/**
						 * Sets the value of the titularDebito property.
						 * 
						 * @param value
						 *            allowed object is {@link String }
						 * 
						 */
						public void setTitularDebito(String value) {
							this.titularDebito = value;
						}

					}

				}

				/**
				 * <p>
				 * Java class for anonymous complex type.
				 * 
				 * <p>
				 * The following schema fragment specifies the expected content
				 * contained within this class.
				 * 
				 * <pre>
				 * &lt;complexType>
				 *   &lt;complexContent>
				 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
				 *       &lt;sequence>
				 *         &lt;element name="MPCronos" type="{http://www.w3.org/2001/XMLSchema}short"/>
				 *         &lt;element name="UsuarioId" type="{http://www.w3.org/2001/XMLSchema}string"/>
				 *         &lt;element name="UsuarioPwd" type="{http://www.w3.org/2001/XMLSchema}string"/>
				 *         &lt;element name="CanalTipo" type="{http://www.w3.org/2001/XMLSchema}byte"/>
				 *         &lt;element name="IdTransaccion" type="{http://www.w3.org/2001/XMLSchema}long"/>
				 *         &lt;element name="ModoEjecucion" type="{http://www.w3.org/2001/XMLSchema}string"/>
				 *         &lt;element name="FechaEjecucion" type="{http://www.w3.org/2001/XMLSchema}int"/>
				 *         &lt;element name="NUP" type="{http://www.w3.org/2001/XMLSchema}int"/>
				 *         &lt;element name="SubcanalTipo" type="{http://www.w3.org/2001/XMLSchema}byte"/>
				 *         &lt;element name="Nombre" type="{http://www.w3.org/2001/XMLSchema}string"/>
				 *         &lt;element name="Version" type="{http://www.w3.org/2001/XMLSchema}short"/>
				 *         &lt;element name="Fecha" type="{http://www.w3.org/2001/XMLSchema}int"/>
				 *         &lt;element name="Hora" type="{http://www.w3.org/2001/XMLSchema}int"/>
				 *         &lt;element name="NroRecurrencia" type="{http://www.w3.org/2001/XMLSchema}byte"/>
				 *       &lt;/sequence>
				 *     &lt;/restriction>
				 *   &lt;/complexContent>
				 * &lt;/complexType>
				 * </pre>
				 * 
				 * 
				 */
				@XmlAccessorType(XmlAccessType.FIELD)
				@XmlType(name = "", propOrder = { "mpCronos", "usuarioId", "usuarioPwd", "canalTipo", "idTransaccion",
						"modoEjecucion", "fechaEjecucion", "nup", "subcanalTipo", "nombre", "version", "fecha", "hora",
						"nroRecurrencia" })
				public static class META {

					/** The mp cronos. */
					@XmlElement(name = "MPCronos")
					protected short mpCronos;

					/** The usuario id. */
					@XmlElement(name = "UsuarioId", required = true)
					protected String usuarioId;

					/** The usuario pwd. */
					@XmlElement(name = "UsuarioPwd", required = true)
					protected String usuarioPwd;

					/** The canal tipo. */
					@XmlElement(name = "CanalTipo")
					protected byte canalTipo;

					/** The id transaccion. */
					@XmlElement(name = "IdTransaccion")
					protected long idTransaccion;

					/** The modo ejecucion. */
					@XmlElement(name = "ModoEjecucion", required = true)
					protected String modoEjecucion;

					/** The fecha ejecucion. */
					@XmlElement(name = "FechaEjecucion")
					protected int fechaEjecucion;

					/** The nup. */
					@XmlElement(name = "NUP")
					protected int nup;

					/** The subcanal tipo. */
					@XmlElement(name = "SubcanalTipo")
					protected byte subcanalTipo;

					/** The nombre. */
					@XmlElement(name = "Nombre", required = true)
					protected String nombre;

					/** The version. */
					@XmlElement(name = "Version")
					protected short version;

					/** The fecha. */
					@XmlElement(name = "Fecha")
					protected int fecha;

					/** The hora. */
					@XmlElement(name = "Hora")
					protected int hora;

					/** The nro recurrencia. */
					@XmlElement(name = "NroRecurrencia")
					protected byte nroRecurrencia;

					/**
					 * Gets the value of the mpCronos property.
					 *
					 * @return the MP cronos
					 */
					public short getMPCronos() {
						return mpCronos;
					}

					/**
					 * Sets the value of the mpCronos property.
					 *
					 * @param value
					 *            the new MP cronos
					 */
					public void setMPCronos(short value) {
						this.mpCronos = value;
					}

					/**
					 * Gets the value of the usuarioId property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getUsuarioId() {
						return usuarioId;
					}

					/**
					 * Sets the value of the usuarioId property.
					 * 
					 * @param value
					 *            allowed object is {@link String }
					 * 
					 */
					public void setUsuarioId(String value) {
						this.usuarioId = value;
					}

					/**
					 * Gets the value of the usuarioPwd property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getUsuarioPwd() {
						return usuarioPwd;
					}

					/**
					 * Sets the value of the usuarioPwd property.
					 * 
					 * @param value
					 *            allowed object is {@link String }
					 * 
					 */
					public void setUsuarioPwd(String value) {
						this.usuarioPwd = value;
					}

					/**
					 * Gets the value of the canalTipo property.
					 *
					 * @return the canal tipo
					 */
					public byte getCanalTipo() {
						return canalTipo;
					}

					/**
					 * Sets the value of the canalTipo property.
					 *
					 * @param value
					 *            the new canal tipo
					 */
					public void setCanalTipo(byte value) {
						this.canalTipo = value;
					}

					/**
					 * Gets the value of the idTransaccion property.
					 *
					 * @return the id transaccion
					 */
					public long getIdTransaccion() {
						return idTransaccion;
					}

					/**
					 * Sets the value of the idTransaccion property.
					 *
					 * @param value
					 *            the new id transaccion
					 */
					public void setIdTransaccion(long value) {
						this.idTransaccion = value;
					}

					/**
					 * Gets the value of the modoEjecucion property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getModoEjecucion() {
						return modoEjecucion;
					}

					/**
					 * Sets the value of the modoEjecucion property.
					 * 
					 * @param value
					 *            allowed object is {@link String }
					 * 
					 */
					public void setModoEjecucion(String value) {
						this.modoEjecucion = value;
					}

					/**
					 * Gets the value of the fechaEjecucion property.
					 *
					 * @return the fecha ejecucion
					 */
					public int getFechaEjecucion() {
						return fechaEjecucion;
					}

					/**
					 * Sets the value of the fechaEjecucion property.
					 *
					 * @param value
					 *            the new fecha ejecucion
					 */
					public void setFechaEjecucion(int value) {
						this.fechaEjecucion = value;
					}

					/**
					 * Gets the value of the nup property.
					 *
					 * @return the nup
					 */
					public int getNUP() {
						return nup;
					}

					/**
					 * Sets the value of the nup property.
					 *
					 * @param value
					 *            the new nup
					 */
					public void setNUP(int value) {
						this.nup = value;
					}

					/**
					 * Gets the value of the subcanalTipo property.
					 *
					 * @return the subcanal tipo
					 */
					public byte getSubcanalTipo() {
						return subcanalTipo;
					}

					/**
					 * Sets the value of the subcanalTipo property.
					 *
					 * @param value
					 *            the new subcanal tipo
					 */
					public void setSubcanalTipo(byte value) {
						this.subcanalTipo = value;
					}

					/**
					 * Gets the value of the nombre property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getNombre() {
						return nombre;
					}

					/**
					 * Sets the value of the nombre property.
					 * 
					 * @param value
					 *            allowed object is {@link String }
					 * 
					 */
					public void setNombre(String value) {
						this.nombre = value;
					}

					/**
					 * Gets the value of the version property.
					 *
					 * @return the version
					 */
					public short getVersion() {
						return version;
					}

					/**
					 * Sets the value of the version property.
					 *
					 * @param value
					 *            the new version
					 */
					public void setVersion(short value) {
						this.version = value;
					}

					/**
					 * Gets the value of the fecha property.
					 *
					 * @return the fecha
					 */
					public int getFecha() {
						return fecha;
					}

					/**
					 * Sets the value of the fecha property.
					 *
					 * @param value
					 *            the new fecha
					 */
					public void setFecha(int value) {
						this.fecha = value;
					}

					/**
					 * Gets the value of the hora property.
					 *
					 * @return the hora
					 */
					public int getHora() {
						return hora;
					}

					/**
					 * Sets the value of the hora property.
					 *
					 * @param value
					 *            the new hora
					 */
					public void setHora(int value) {
						this.hora = value;
					}

					/**
					 * Gets the value of the nroRecurrencia property.
					 *
					 * @return the nro recurrencia
					 */
					public byte getNroRecurrencia() {
						return nroRecurrencia;
					}

					/**
					 * Sets the value of the nroRecurrencia property.
					 *
					 * @param value
					 *            the new nro recurrencia
					 */
					public void setNroRecurrencia(byte value) {
						this.nroRecurrencia = value;
					}

				}

			}

			/**
			 * <p>
			 * Java class for anonymous complex type.
			 * 
			 * <p>
			 * The following schema fragment specifies the expected content
			 * contained within this class.
			 * 
			 * <pre>
			 * &lt;complexType>
			 *   &lt;complexContent>
			 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
			 *       &lt;choice maxOccurs="unbounded" minOccurs="0">
			 *         &lt;element name="Severidad" type="{http://www.w3.org/2001/XMLSchema}byte"/>
			 *         &lt;element name="CodRet" type="{http://www.w3.org/2001/XMLSchema}byte"/>
			 *         &lt;element name="NroOpCanal" type="{http://www.w3.org/2001/XMLSchema}byte"/>
			 *         &lt;element name="FechaCompensacion" type="{http://www.w3.org/2001/XMLSchema}long"/>
			 *         &lt;element name="NroComprobante" type="{http://www.w3.org/2001/XMLSchema}int"/>
			 *         &lt;element name="NroSecuencia" type="{http://www.w3.org/2001/XMLSchema}short"/>
			 *         &lt;element name="ComprobanteBackEnd" type="{http://www.w3.org/2001/XMLSchema}int"/>
			 *         &lt;element name="IdRecibo" type="{http://www.w3.org/2001/XMLSchema}long"/>
			 *         &lt;element name="ImporteDebitado" type="{http://www.w3.org/2001/XMLSchema}float"/>
			 *         &lt;element name="CodigoEstado" type="{http://www.w3.org/2001/XMLSchema}short"/>
			 *       &lt;/choice>
			 *     &lt;/restriction>
			 *   &lt;/complexContent>
			 * &lt;/complexType>
			 * </pre>
			 * 
			 * 
			 */
			@XmlAccessorType(XmlAccessType.FIELD)
			@XmlType(name = "", propOrder = { "cotizacion", "importeDestino", "codRet", "nroOpCanal",
					"comprobanteBackEnd", "severidad", "idRecibo", "importeOrigen", "importeDebitado" })
			public static class XMLResultado {

				/** The cotizacion. */
				@XmlElement(name = "Cotizacion")
				protected String cotizacion;

				/** The importe destino. */
				@XmlElement(name = "ImporteDestino")
				protected String importeDestino;

				/** The cod ret. */
				@XmlElement(name = "CodRet")
				protected String codRet;

				/** The nro op canal. */
				@XmlElement(name = "NroOpCanal")
				protected String nroOpCanal;

				/** The comprobante back end. */
				@XmlElement(name = "ComprobanteBackEnd")
				protected String comprobanteBackEnd;

				/** The severidad. */
				@XmlElement(name = "Severidad")
				protected String severidad;

				/** The id recibo. */
				@XmlElement(name = "IdRecibo")
				protected String idRecibo;

				/** The importe origen. */
				@XmlElement(name = "ImporteOrigen")
				protected String importeOrigen;

				/** The importe debitado. */
				@XmlElement(name = "ImporteDebitado")
				protected String importeDebitado;

				/**
				 * Gets the severidad.
				 *
				 * @return the severidad
				 */
				public String getSeveridad() {
					return severidad;
				}

				/**
				 * Sets the severidad.
				 *
				 * @param severidad
				 *            the severidad to set
				 */
				public void setSeveridad(String severidad) {
					this.severidad = severidad;
				}

				/**
				 * Gets the cod ret.
				 *
				 * @return the codRet
				 */
				public String getCodRet() {
					return codRet;
				}

				/**
				 * Sets the cod ret.
				 *
				 * @param codRet
				 *            the codRet to set
				 */
				public void setCodRet(String codRet) {
					this.codRet = codRet;
				}

				/**
				 * Gets the nro op canal.
				 *
				 * @return the nroOpCanal
				 */
				public String getNroOpCanal() {
					return nroOpCanal;
				}

				/**
				 * Sets the nro op canal.
				 *
				 * @param nroOpCanal
				 *            the nroOpCanal to set
				 */
				public void setNroOpCanal(String nroOpCanal) {
					this.nroOpCanal = nroOpCanal;
				}

				/**
				 * Gets the comprobante back end.
				 *
				 * @return the comprobanteBackEnd
				 */
				public String getComprobanteBackEnd() {
					return comprobanteBackEnd;
				}

				/**
				 * Sets the comprobante back end.
				 *
				 * @param comprobanteBackEnd
				 *            the comprobanteBackEnd to set
				 */
				public void setComprobanteBackEnd(String comprobanteBackEnd) {
					this.comprobanteBackEnd = comprobanteBackEnd;
				}

				/**
				 * Gets the id recibo.
				 *
				 * @return the idRecibo
				 */
				public String getIdRecibo() {
					return idRecibo;
				}

				/**
				 * Sets the id recibo.
				 *
				 * @param idRecibo
				 *            the idRecibo to set
				 */
				public void setIdRecibo(String idRecibo) {
					this.idRecibo = idRecibo;
				}

				/**
				 * Gets the importe origen.
				 *
				 * @return the importe origen
				 */
				public String getImporteOrigen() {
					return importeOrigen;
				}

				/**
				 * Sets the importe origen.
				 *
				 * @param importeOrigen
				 *            the new importe origen
				 */
				public void setImporteOrigen(String importeOrigen) {
					this.importeOrigen = importeOrigen;
				}

				/**
				 * Gets the importe debitado.
				 *
				 * @return the importe debitado
				 */
				public String getImporteDebitado() {
					return importeDebitado;
				}

				/**
				 * Sets the importe debitado.
				 *
				 * @param importeDebitado
				 *            the new importe debitado
				 */
				public void setImporteDebitado(String importeDebitado) {
					this.importeDebitado = importeDebitado;
				}

			}

			/**
			 * <p>
			 * Java class for anonymous complex type.
			 * 
			 * <p>
			 * The following schema fragment specifies the expected content
			 * contained within this class.
			 * 
			 * <pre>
			 * &lt;complexType>
			 *   &lt;complexContent>
			 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
			 *       &lt;sequence>
			 *         &lt;element name="CodigoCredito" type="{http://www.w3.org/2001/XMLSchema}String"/>
			 *         &lt;element name="UsuarioTipo" type="{http://www.w3.org/2001/XMLSchema}String"/>
			 *         &lt;element name="LogueoAgendaHistorica" type="{http://www.w3.org/2001/XMLSchema}string"/>
			 *         &lt;element name="NroCtaDebito" type="{http://www.w3.org/2001/XMLSchema}String"/>
			 *         &lt;element name="Importe" type="{http://www.w3.org/2001/XMLSchema}float"/>
			 *         &lt;element name="SubcodigoCredito" type="{http://www.w3.org/2001/XMLSchema}String"/>
			 *         &lt;element name="IdSesionCnt" type="{http://www.w3.org/2001/XMLSchema}string"/>
			 *         &lt;element name="IndAuten" type="{http://www.w3.org/2001/XMLSchema}String"/>
			 *         &lt;element name="SubcanalId" type="{http://www.w3.org/2001/XMLSchema}String"/>
			 *         &lt;element name="UsuarioAtrib" type="{http://www.w3.org/2001/XMLSchema}string"/>
			 *         &lt;element name="FechaNac" type="{http://www.w3.org/2001/XMLSchema}String"/>
			 *         &lt;element name="TipoPersona" type="{http://www.w3.org/2001/XMLSchema}string"/>
			 *         &lt;element name="CanalId" type="{http://www.w3.org/2001/XMLSchema}string"/>
			 *         &lt;element name="ComprobanteCredito" type="{http://www.w3.org/2001/XMLSchema}String"/>
			 *         &lt;element name="SucCtaCredito" type="{http://www.w3.org/2001/XMLSchema}String"/>
			 *         &lt;element name="EcoDatosEntrada" type="{http://www.w3.org/2001/XMLSchema}string"/>
			 *         &lt;element name="NroCtaCredito" type="{http://www.w3.org/2001/XMLSchema}String"/>
			 *         &lt;element name="SucCtaDebito" type="{http://www.w3.org/2001/XMLSchema}String"/>
			 *         &lt;element name="TipoCtaDebito" type="{http://www.w3.org/2001/XMLSchema}String"/>
			 *         &lt;element name="TipoCtaCredito" type="{http://www.w3.org/2001/XMLSchema}String"/>
			 *         &lt;element name="TipoId" type="{http://www.w3.org/2001/XMLSchema}string"/>
			 *         &lt;element name="CanalVersion" type="{http://www.w3.org/2001/XMLSchema}String"/>
			 *         &lt;element name="IdCliente" type="{http://www.w3.org/2001/XMLSchema}String"/>
			 *         &lt;element name="CodigoDebito" type="{http://www.w3.org/2001/XMLSchema}String"/>
			 *         &lt;element name="LimiteSobregiro" type="{http://www.w3.org/2001/XMLSchema}String"/>
			 *         &lt;element name="VersionXML" type="{http://www.w3.org/2001/XMLSchema}string"/>
			 *         &lt;element name="SubcodigoDebito" type="{http://www.w3.org/2001/XMLSchema}String"/>
			 *       &lt;/sequence>
			 *     &lt;/restriction>
			 *   &lt;/complexContent>
			 * &lt;/complexType>
			 * </pre>
			 * 
			 * 
			 */
			@XmlAccessorType(XmlAccessType.FIELD)
			@XmlType(name = "", propOrder = { "nroComprobante", "cotizacionTransferencia", "indicadorLimiteMax",
					"importeDebito", "indicadorAfectarDisponible", "importeCredito", "nombreCtaCredito", "cuentaPropia",
					"codigoConcepto", "indTransfDiferida", "descripcionConcepto", "descConcepto", "codigoCredito",
					"usuarioTipo", "logueoAgendaHistorica", "nroCtaDebito", "importe", "subcodigoCredito",
					"idSesionCnt", "indAuten", "subcanalId", "usuarioAtrib", "fechaNac", "tipoPersona", "canalId",
					"comprobanteCredito", "sucCtaCredito", "ecoDatosEntrada", "nroCtaCredito", "sucCtaDebito",
					"tipoCtaDebito", "tipoCtaCredito", "tipoId", "canalVersion", "idCliente", "codigoDebito",
					"limiteSobregiro", "versionXML", "subcodigoDebito", "informacDiscrecional", "cbu",
					"caracteristicaCtaCredito", "monedaTransferencia", "longCtaDesBane", "cuit3", "cuit2", "cuit1",
					"marcaGravado", "indicadorFuncion", "fiid", "titular", "tpoCtaFromBane", "ctaBane", "user",
					"plazoAcreditacion", "digitoCtaCredito", "marcaLimite", "referenciaUnivoca",
					"identificBeneficiario", "firmanteCtaDebito", "codConcepto", "bancoReceptor", "iPMaquina",
					"periodica", "cantidadDias", "entidadCtaCredito", "bancoDestino", "importeTransferencia",
					"tipoTransferencia", "tpoCtaToBane" })
			public static class DATOSENTRADA {

				/** The nro comprobante. */
				@XmlElement(name = "NroComprobante")
				protected String nroComprobante;

				/** The cotizacion transferencia. */
				@XmlElement(name = "CotizacionTransferencia")
				protected String cotizacionTransferencia;

				/** The indicador limite max. */
				@XmlElement(name = "IndicadorLimiteMax")
				protected String indicadorLimiteMax;

				/** The importe debito. */
				@XmlElement(name = "ImporteDebito")
				protected String importeDebito;

				/** The indicador afectar disponible. */
				@XmlElement(name = "IndicadorAfectarDisponible")
				protected String indicadorAfectarDisponible;

				/** The importe credito. */
				@XmlElement(name = "ImporteCredito")
				protected String importeCredito;

				/** The nombre cta credito. */
				@XmlElement(name = "NombreCtaCredito")
				protected String nombreCtaCredito;

				/** The cuenta propia. */
				@XmlElement(name = "CuentaPropia")
				protected String cuentaPropia;

				/** The codigo concepto. */
				@XmlElement(name = "CodigoConcepto")
				protected String codigoConcepto;

				/** The ind transf diferida. */
				@XmlElement(name = "IndTransfDiferida")
				protected String indTransfDiferida;

				/** The descripcion concepto. */
				@XmlElement(name = "DescripcionConcepto")
				protected String descripcionConcepto;

				/** The desc concepto. */
				@XmlElement(name = "DescConcepto")
				protected String descConcepto;
				///
				///

				/** The informac discrecional. */
				@XmlElement(name = "InformacDiscrecional")
				private String informacDiscrecional;

				/** The cbu. */
				@XmlElement(name = "Cbu")
				private String cbu;

				/** The caracteristica cta credito. */
				@XmlElement(name = "CaracteristicaCtaCredito")
				private String caracteristicaCtaCredito;

				/** The moneda transferencia. */
				@XmlElement(name = "MonedaTransferencia")
				private String monedaTransferencia;

				/** The long cta des bane. */
				@XmlElement(name = "LongCtaDesBane")
				private String longCtaDesBane;

				/** The cuit 3. */
				@XmlElement(name = "Cuit3")
				private String cuit3;

				/** The cuit 2. */
				@XmlElement(name = "Cuit2")
				private String cuit2;

				/** The cuit 1. */
				@XmlElement(name = "Cuit1")
				private String cuit1;

				/** The marca gravado. */
				@XmlElement(name = "MarcaGravado")
				private String marcaGravado;

				/** The indicador funcion. */
				@XmlElement(name = "IndicadorFuncion")
				private String indicadorFuncion;

				/** The fiid. */
				@XmlElement(name = "Fiid")
				private String fiid;

				/** The titular. */
				@XmlElement(name = "Titular")
				private String titular;

				/** The tpo cta from bane. */
				@XmlElement(name = "TpoCtaFromBane")
				private String tpoCtaFromBane;

				/** The cta bane. */
				@XmlElement(name = "CtaBane")
				private String ctaBane;

				/** The user. */
				@XmlElement(name = "User")
				private String user;

				/** The plazo acreditacion. */
				@XmlElement(name = "PlazoAcreditacion")
				private String plazoAcreditacion;

				/** The digito cta credito. */
				@XmlElement(name = "DigitoCtaCredito")
				private String digitoCtaCredito;

				/** The marca limite. */
				@XmlElement(name = "MarcaLimite")
				private String marcaLimite;

				/** The referencia univoca. */
				@XmlElement(name = "ReferenciaUnivoca")
				private String referenciaUnivoca;

				/** The identific beneficiario. */
				@XmlElement(name = "IdentificBeneficiario")
				private String identificBeneficiario;

				/** The firmante cta debito. */
				@XmlElement(name = "FirmanteCtaDebito")
				private String firmanteCtaDebito;

				/** The cod concepto. */
				@XmlElement(name = "CodConcepto")
				private String codConcepto;

				/** The banco receptor. */
				@XmlElement(name = "BancoReceptor")
				private String bancoReceptor;

				/** The i P maquina. */
				@XmlElement(name = "IPMaquina")
				private String iPMaquina;

				/** The periodica. */
				@XmlElement(name = "Periodica")
				private String periodica;

				/** The cantidad dias. */
				@XmlElement(name = "CantidadDias")
				private String cantidadDias;

				/** The entidad cta credito. */
				@XmlElement(name = "EntidadCtaCredito")
				private String entidadCtaCredito;

				/** The banco destino. */
				@XmlElement(name = "BancoDestino")
				private String bancoDestino;

				/** The importe transferencia. */
				@XmlElement(name = "ImporteTransferencia")
				private String importeTransferencia;

				/** The tipo transferencia. */
				@XmlElement(name = "TipoTransferencia")
				private String tipoTransferencia;

				/** The tpo cta to bane. */
				@XmlElement(name = "TpoCtaToBane")
				private String tpoCtaToBane;

				///
				///

				/** The codigo credito. */
				@XmlElement(name = "CodigoCredito")
				protected String codigoCredito;

				/** The usuario tipo. */
				@XmlElement(name = "UsuarioTipo")
				protected String usuarioTipo;

				/** The logueo agenda historica. */
				@XmlElement(name = "LogueoAgendaHistorica", required = true)
				protected String logueoAgendaHistorica;

				/** The nro cta debito. */
				@XmlElement(name = "NroCtaDebito")
				protected String nroCtaDebito;

				/** The importe. */
				@XmlElement(name = "Importe")
				protected float importe;

				/** The subcodigo credito. */
				@XmlElement(name = "SubcodigoCredito")
				protected String subcodigoCredito;

				/** The id sesion cnt. */
				@XmlElement(name = "IdSesionCnt", required = true)
				protected String idSesionCnt;

				/** The ind auten. */
				@XmlElement(name = "IndAuten")
				protected String indAuten;

				/** The subcanal id. */
				@XmlElement(name = "SubcanalId")
				protected String subcanalId;

				/** The usuario atrib. */
				@XmlElement(name = "UsuarioAtrib", required = true)
				protected String usuarioAtrib;

				/** The fecha nac. */
				@XmlElement(name = "FechaNac")
				protected String fechaNac;

				/** The tipo persona. */
				@XmlElement(name = "TipoPersona", required = true)
				protected String tipoPersona;

				/** The canal id. */
				@XmlElement(name = "CanalId", required = true)
				protected String canalId;

				/** The comprobante credito. */
				@XmlElement(name = "ComprobanteCredito")
				protected String comprobanteCredito;

				/** The suc cta credito. */
				@XmlElement(name = "SucCtaCredito")
				protected String sucCtaCredito;

				/** The eco datos entrada. */
				@XmlElement(name = "EcoDatosEntrada", required = true)
				protected String ecoDatosEntrada;

				/** The nro cta credito. */
				@XmlElement(name = "NroCtaCredito")
				protected String nroCtaCredito;

				/** The suc cta debito. */
				@XmlElement(name = "SucCtaDebito")
				protected String sucCtaDebito;

				/** The tipo cta debito. */
				@XmlElement(name = "TipoCtaDebito")
				protected String tipoCtaDebito;

				/**
				 * Gets the nro comprobante.
				 *
				 * @return the nroComprobante
				 */
				public String getNroComprobante() {
					return nroComprobante;
				}

				/**
				 * Sets the nro comprobante.
				 *
				 * @param nroComprobante
				 *            the nroComprobante to set
				 */
				public void setNroComprobante(String nroComprobante) {
					this.nroComprobante = nroComprobante;
				}

				/**
				 * Gets the cotizacion transferencia.
				 *
				 * @return the cotizacionTransferencia
				 */
				public String getCotizacionTransferencia() {
					return cotizacionTransferencia;
				}

				/**
				 * Sets the cotizacion transferencia.
				 *
				 * @param cotizacionTransferencia
				 *            the cotizacionTransferencia to set
				 */
				public void setCotizacionTransferencia(String cotizacionTransferencia) {
					this.cotizacionTransferencia = cotizacionTransferencia;
				}

				/**
				 * Gets the indicador limite max.
				 *
				 * @return the indicadorLimiteMax
				 */
				public String getIndicadorLimiteMax() {
					return indicadorLimiteMax;
				}

				/**
				 * Sets the indicador limite max.
				 *
				 * @param indicadorLimiteMax
				 *            the indicadorLimiteMax to set
				 */
				public void setIndicadorLimiteMax(String indicadorLimiteMax) {
					this.indicadorLimiteMax = indicadorLimiteMax;
				}

				/**
				 * Gets the importe debito.
				 *
				 * @return the importeDebito
				 */
				public String getImporteDebito() {
					return importeDebito;
				}

				/**
				 * Sets the importe debito.
				 *
				 * @param importeDebito
				 *            the importeDebito to set
				 */
				public void setImporteDebito(String importeDebito) {
					this.importeDebito = importeDebito;
				}

				/**
				 * Gets the indicador afectar disponible.
				 *
				 * @return the indicadorAfectarDisponible
				 */
				public String getIndicadorAfectarDisponible() {
					return indicadorAfectarDisponible;
				}

				/**
				 * Sets the indicador afectar disponible.
				 *
				 * @param indicadorAfectarDisponible
				 *            the indicadorAfectarDisponible to set
				 */
				public void setIndicadorAfectarDisponible(String indicadorAfectarDisponible) {
					this.indicadorAfectarDisponible = indicadorAfectarDisponible;
				}

				/**
				 * Gets the importe credito.
				 *
				 * @return the importeCredito
				 */
				public String getImporteCredito() {
					return importeCredito;
				}

				/**
				 * Sets the importe credito.
				 *
				 * @param importeCredito
				 *            the importeCredito to set
				 */
				public void setImporteCredito(String importeCredito) {
					this.importeCredito = importeCredito;
				}

				/**
				 * Gets the nombre cta credito.
				 *
				 * @return the nombreCtaCredito
				 */
				public String getNombreCtaCredito() {
					return nombreCtaCredito;
				}

				/**
				 * Sets the nombre cta credito.
				 *
				 * @param nombreCtaCredito
				 *            the nombreCtaCredito to set
				 */
				public void setNombreCtaCredito(String nombreCtaCredito) {
					this.nombreCtaCredito = nombreCtaCredito;
				}

				/**
				 * Gets the cuenta propia.
				 *
				 * @return the cuentaPropia
				 */
				public String getCuentaPropia() {
					return cuentaPropia;
				}

				/**
				 * Sets the cuenta propia.
				 *
				 * @param cuentaPropia
				 *            the cuentaPropia to set
				 */
				public void setCuentaPropia(String cuentaPropia) {
					this.cuentaPropia = cuentaPropia;
				}

				/**
				 * Gets the codigo concepto.
				 *
				 * @return the codigoConcepto
				 */
				public String getCodigoConcepto() {
					return codigoConcepto;
				}

				/**
				 * Sets the codigo concepto.
				 *
				 * @param codigoConcepto
				 *            the codigoConcepto to set
				 */
				public void setCodigoConcepto(String codigoConcepto) {
					this.codigoConcepto = codigoConcepto;
				}

				/**
				 * Gets the ind transf diferida.
				 *
				 * @return the indTransfDiferida
				 */
				public String getIndTransfDiferida() {
					return indTransfDiferida;
				}

				/**
				 * Sets the ind transf diferida.
				 *
				 * @param indTransfDiferida
				 *            the indTransfDiferida to set
				 */
				public void setIndTransfDiferida(String indTransfDiferida) {
					this.indTransfDiferida = indTransfDiferida;
				}

				/**
				 * Gets the descripcion concepto.
				 *
				 * @return the descripcionConcepto
				 */
				public String getDescripcionConcepto() {
					return descripcionConcepto;
				}

				/**
				 * Sets the descripcion concepto.
				 *
				 * @param descripcionConcepto
				 *            the descripcionConcepto to set
				 */
				public void setDescripcionConcepto(String descripcionConcepto) {
					this.descripcionConcepto = descripcionConcepto;
				}

				/** The tipo cta credito. */
				@XmlElement(name = "TipoCtaCredito")
				protected String tipoCtaCredito;

				/** The tipo id. */
				@XmlElement(name = "TipoId", required = true)
				protected String tipoId;

				/** The canal version. */
				@XmlElement(name = "CanalVersion")
				protected String canalVersion;

				/** The id cliente. */
				@XmlElement(name = "IdCliente")
				protected String idCliente;

				/** The codigo debito. */
				@XmlElement(name = "CodigoDebito")
				protected String codigoDebito;

				/** The limite sobregiro. */
				@XmlElement(name = "LimiteSobregiro")
				protected String limiteSobregiro;

				/** The version XML. */
				@XmlElement(name = "VersionXML", required = true)
				protected String versionXML;

				/** The subcodigo debito. */
				@XmlElement(name = "SubcodigoDebito")
				protected String subcodigoDebito;

				/**
				 * Gets the value of the codigoCredito property.
				 *
				 * @return the codigo credito
				 */
				public String getCodigoCredito() {
					return codigoCredito;
				}

				/**
				 * Sets the value of the codigoCredito property.
				 *
				 * @param value
				 *            the new codigo credito
				 */
				public void setCodigoCredito(String value) {
					this.codigoCredito = value;
				}

				/**
				 * Gets the value of the usuarioTipo property.
				 *
				 * @return the usuario tipo
				 */
				public String getUsuarioTipo() {
					return usuarioTipo;
				}

				/**
				 * Sets the value of the usuarioTipo property.
				 *
				 * @param value
				 *            the new usuario tipo
				 */
				public void setUsuarioTipo(String value) {
					this.usuarioTipo = value;
				}

				/**
				 * Gets the value of the logueoAgendaHistorica property.
				 * 
				 * @return possible object is {@link String }
				 * 
				 */
				public String getLogueoAgendaHistorica() {
					return logueoAgendaHistorica;
				}

				/**
				 * Sets the value of the logueoAgendaHistorica property.
				 * 
				 * @param value
				 *            allowed object is {@link String }
				 * 
				 */
				public void setLogueoAgendaHistorica(String value) {
					this.logueoAgendaHistorica = value;
				}

				/**
				 * Gets the value of the nroCtaDebito property.
				 *
				 * @return the nro cta debito
				 */
				public String getNroCtaDebito() {
					return nroCtaDebito;
				}

				/**
				 * Sets the value of the nroCtaDebito property.
				 *
				 * @param value
				 *            the new nro cta debito
				 */
				public void setNroCtaDebito(String value) {
					this.nroCtaDebito = value;
				}

				/**
				 * Gets the value of the importe property.
				 *
				 * @return the importe
				 */
				public float getImporte() {
					return importe;
				}

				/**
				 * Sets the value of the importe property.
				 *
				 * @param value
				 *            the new importe
				 */
				public void setImporte(float value) {
					this.importe = value;
				}

				/**
				 * Gets the value of the subcodigoCredito property.
				 *
				 * @return the subcodigo credito
				 */
				public String getSubcodigoCredito() {
					return subcodigoCredito;
				}

				/**
				 * Sets the value of the subcodigoCredito property.
				 *
				 * @param value
				 *            the new subcodigo credito
				 */
				public void setSubcodigoCredito(String value) {
					this.subcodigoCredito = value;
				}

				/**
				 * Gets the value of the idSesionCnt property.
				 * 
				 * @return possible object is {@link String }
				 * 
				 */
				public String getIdSesionCnt() {
					return idSesionCnt;
				}

				/**
				 * Sets the value of the idSesionCnt property.
				 * 
				 * @param value
				 *            allowed object is {@link String }
				 * 
				 */
				public void setIdSesionCnt(String value) {
					this.idSesionCnt = value;
				}

				/**
				 * Gets the value of the indAuten property.
				 *
				 * @return the ind auten
				 */
				public String getIndAuten() {
					return indAuten;
				}

				/**
				 * Sets the value of the indAuten property.
				 *
				 * @param value
				 *            the new ind auten
				 */
				public void setIndAuten(String value) {
					this.indAuten = value;
				}

				/**
				 * Gets the value of the subcanalId property.
				 *
				 * @return the subcanal id
				 */
				public String getSubcanalId() {
					return subcanalId;
				}

				/**
				 * Sets the value of the subcanalId property.
				 *
				 * @param value
				 *            the new subcanal id
				 */
				public void setSubcanalId(String value) {
					this.subcanalId = value;
				}

				/**
				 * Gets the value of the usuarioAtrib property.
				 * 
				 * @return possible object is {@link String }
				 * 
				 */
				public String getUsuarioAtrib() {
					return usuarioAtrib;
				}

				/**
				 * Sets the value of the usuarioAtrib property.
				 * 
				 * @param value
				 *            allowed object is {@link String }
				 * 
				 */
				public void setUsuarioAtrib(String value) {
					this.usuarioAtrib = value;
				}

				/**
				 * Gets the value of the fechaNac property.
				 *
				 * @return the fecha nac
				 */
				public String getFechaNac() {
					return fechaNac;
				}

				/**
				 * Sets the value of the fechaNac property.
				 *
				 * @param value
				 *            the new fecha nac
				 */
				public void setFechaNac(String value) {
					this.fechaNac = value;
				}

				/**
				 * Gets the value of the tipoPersona property.
				 * 
				 * @return possible object is {@link String }
				 * 
				 */
				public String getTipoPersona() {
					return tipoPersona;
				}

				/**
				 * Sets the value of the tipoPersona property.
				 * 
				 * @param value
				 *            allowed object is {@link String }
				 * 
				 */
				public void setTipoPersona(String value) {
					this.tipoPersona = value;
				}

				/**
				 * Gets the value of the canalId property.
				 * 
				 * @return possible object is {@link String }
				 * 
				 */
				public String getCanalId() {
					return canalId;
				}

				/**
				 * Sets the value of the canalId property.
				 * 
				 * @param value
				 *            allowed object is {@link String }
				 * 
				 */
				public void setCanalId(String value) {
					this.canalId = value;
				}

				/**
				 * Gets the value of the comprobanteCredito property.
				 *
				 * @return the comprobante credito
				 */
				public String getComprobanteCredito() {
					return comprobanteCredito;
				}

				/**
				 * Sets the value of the comprobanteCredito property.
				 *
				 * @param value
				 *            the new comprobante credito
				 */
				public void setComprobanteCredito(String value) {
					this.comprobanteCredito = value;
				}

				/**
				 * Gets the value of the sucCtaCredito property.
				 *
				 * @return the suc cta credito
				 */
				public String getSucCtaCredito() {
					return sucCtaCredito;
				}

				/**
				 * Sets the value of the sucCtaCredito property.
				 *
				 * @param value
				 *            the new suc cta credito
				 */
				public void setSucCtaCredito(String value) {
					this.sucCtaCredito = value;
				}

				/**
				 * Gets the value of the ecoDatosEntrada property.
				 * 
				 * @return possible object is {@link String }
				 * 
				 */
				public String getEcoDatosEntrada() {
					return ecoDatosEntrada;
				}

				/**
				 * Sets the value of the ecoDatosEntrada property.
				 * 
				 * @param value
				 *            allowed object is {@link String }
				 * 
				 */
				public void setEcoDatosEntrada(String value) {
					this.ecoDatosEntrada = value;
				}

				/**
				 * Gets the value of the nroCtaCredito property.
				 *
				 * @return the nro cta credito
				 */
				public String getNroCtaCredito() {
					return nroCtaCredito;
				}

				/**
				 * Sets the value of the nroCtaCredito property.
				 *
				 * @param value
				 *            the new nro cta credito
				 */
				public void setNroCtaCredito(String value) {
					this.nroCtaCredito = value;
				}

				/**
				 * Gets the value of the sucCtaDebito property.
				 *
				 * @return the suc cta debito
				 */
				public String getSucCtaDebito() {
					return sucCtaDebito;
				}

				/**
				 * Sets the value of the sucCtaDebito property.
				 *
				 * @param value
				 *            the new suc cta debito
				 */
				public void setSucCtaDebito(String value) {
					this.sucCtaDebito = value;
				}

				/**
				 * Gets the value of the tipoCtaDebito property.
				 *
				 * @return the tipo cta debito
				 */
				public String getTipoCtaDebito() {
					return tipoCtaDebito;
				}

				/**
				 * Sets the value of the tipoCtaDebito property.
				 *
				 * @param value
				 *            the new tipo cta debito
				 */
				public void setTipoCtaDebito(String value) {
					this.tipoCtaDebito = value;
				}

				/**
				 * Gets the value of the tipoCtaCredito property.
				 *
				 * @return the tipo cta credito
				 */
				public String getTipoCtaCredito() {
					return tipoCtaCredito;
				}

				/**
				 * Sets the value of the tipoCtaCredito property.
				 *
				 * @param value
				 *            the new tipo cta credito
				 */
				public void setTipoCtaCredito(String value) {
					this.tipoCtaCredito = value;
				}

				/**
				 * Gets the value of the tipoId property.
				 * 
				 * @return possible object is {@link String }
				 * 
				 */
				public String getTipoId() {
					return tipoId;
				}

				/**
				 * Sets the value of the tipoId property.
				 * 
				 * @param value
				 *            allowed object is {@link String }
				 * 
				 */
				public void setTipoId(String value) {
					this.tipoId = value;
				}

				/**
				 * Gets the value of the canalVersion property.
				 *
				 * @return the canal version
				 */
				public String getCanalVersion() {
					return canalVersion;
				}

				/**
				 * Sets the value of the canalVersion property.
				 *
				 * @param value
				 *            the new canal version
				 */
				public void setCanalVersion(String value) {
					this.canalVersion = value;
				}

				/**
				 * Gets the value of the idCliente property.
				 *
				 * @return the id cliente
				 */
				public String getIdCliente() {
					return idCliente;
				}

				/**
				 * Sets the value of the idCliente property.
				 *
				 * @param value
				 *            the new id cliente
				 */
				public void setIdCliente(String value) {
					this.idCliente = value;
				}

				/**
				 * Gets the value of the codigoDebito property.
				 *
				 * @return the codigo debito
				 */
				public String getCodigoDebito() {
					return codigoDebito;
				}

				/**
				 * Sets the value of the codigoDebito property.
				 *
				 * @param value
				 *            the new codigo debito
				 */
				public void setCodigoDebito(String value) {
					this.codigoDebito = value;
				}

				/**
				 * Gets the value of the limiteSobregiro property.
				 *
				 * @return the limite sobregiro
				 */
				public String getLimiteSobregiro() {
					return limiteSobregiro;
				}

				/**
				 * Sets the value of the limiteSobregiro property.
				 *
				 * @param value
				 *            the new limite sobregiro
				 */
				public void setLimiteSobregiro(String value) {
					this.limiteSobregiro = value;
				}

				/**
				 * Gets the value of the versionXML property.
				 * 
				 * @return possible object is {@link String }
				 * 
				 */
				public String getVersionXML() {
					return versionXML;
				}

				/**
				 * Sets the value of the versionXML property.
				 * 
				 * @param value
				 *            allowed object is {@link String }
				 * 
				 */
				public void setVersionXML(String value) {
					this.versionXML = value;
				}

				/**
				 * Gets the value of the subcodigoDebito property.
				 *
				 * @return the subcodigo debito
				 */
				public String getSubcodigoDebito() {
					return subcodigoDebito;
				}

				/**
				 * Sets the value of the subcodigoDebito property.
				 *
				 * @param value
				 *            the new subcodigo debito
				 */
				public void setSubcodigoDebito(String value) {
					this.subcodigoDebito = value;
				}

				/**
				 * Gets the informac discrecional.
				 *
				 * @return the informacDiscrecional
				 */
				public String getInformacDiscrecional() {
					return informacDiscrecional;
				}

				/**
				 * Sets the informac discrecional.
				 *
				 * @param informacDiscrecional
				 *            the informacDiscrecional to set
				 */
				public void setInformacDiscrecional(String informacDiscrecional) {
					this.informacDiscrecional = informacDiscrecional;
				}

				/**
				 * Gets the cbu.
				 *
				 * @return the cbu
				 */
				public String getCbu() {
					return cbu;
				}

				/**
				 * Sets the cbu.
				 *
				 * @param cbu
				 *            the cbu to set
				 */
				public void setCbu(String cbu) {
					this.cbu = cbu;
				}

				/**
				 * Gets the caracteristica cta credito.
				 *
				 * @return the caracteristicaCtaCredito
				 */
				public String getCaracteristicaCtaCredito() {
					return caracteristicaCtaCredito;
				}

				/**
				 * Sets the caracteristica cta credito.
				 *
				 * @param caracteristicaCtaCredito
				 *            the caracteristicaCtaCredito to set
				 */
				public void setCaracteristicaCtaCredito(String caracteristicaCtaCredito) {
					this.caracteristicaCtaCredito = caracteristicaCtaCredito;
				}

				/**
				 * Gets the moneda transferencia.
				 *
				 * @return the monedaTransferencia
				 */
				public String getMonedaTransferencia() {
					return monedaTransferencia;
				}

				/**
				 * Sets the moneda transferencia.
				 *
				 * @param monedaTransferencia
				 *            the monedaTransferencia to set
				 */
				public void setMonedaTransferencia(String monedaTransferencia) {
					this.monedaTransferencia = monedaTransferencia;
				}

				/**
				 * Gets the long cta des bane.
				 *
				 * @return the longCtaDesBane
				 */
				public String getLongCtaDesBane() {
					return longCtaDesBane;
				}

				/**
				 * Sets the long cta des bane.
				 *
				 * @param longCtaDesBane
				 *            the longCtaDesBane to set
				 */
				public void setLongCtaDesBane(String longCtaDesBane) {
					this.longCtaDesBane = longCtaDesBane;
				}

				/**
				 * Gets the cuit 3.
				 *
				 * @return the cuit3
				 */
				public String getCuit3() {
					return cuit3;
				}

				/**
				 * Sets the cuit 3.
				 *
				 * @param cuit3
				 *            the cuit3 to set
				 */
				public void setCuit3(String cuit3) {
					this.cuit3 = cuit3;
				}

				/**
				 * Gets the cuit 2.
				 *
				 * @return the cuit2
				 */
				public String getCuit2() {
					return cuit2;
				}

				/**
				 * Sets the cuit 2.
				 *
				 * @param cuit2
				 *            the cuit2 to set
				 */
				public void setCuit2(String cuit2) {
					this.cuit2 = cuit2;
				}

				/**
				 * Gets the cuit 1.
				 *
				 * @return the cuit1
				 */
				public String getCuit1() {
					return cuit1;
				}

				/**
				 * Sets the cuit 1.
				 *
				 * @param cuit1
				 *            the cuit1 to set
				 */
				public void setCuit1(String cuit1) {
					this.cuit1 = cuit1;
				}

				/**
				 * Gets the marca gravado.
				 *
				 * @return the marcaGravado
				 */
				public String getMarcaGravado() {
					return marcaGravado;
				}

				/**
				 * Sets the marca gravado.
				 *
				 * @param marcaGravado
				 *            the marcaGravado to set
				 */
				public void setMarcaGravado(String marcaGravado) {
					this.marcaGravado = marcaGravado;
				}

				/**
				 * Gets the indicador funcion.
				 *
				 * @return the indicadorFuncion
				 */
				public String getIndicadorFuncion() {
					return indicadorFuncion;
				}

				/**
				 * Sets the indicador funcion.
				 *
				 * @param indicadorFuncion
				 *            the indicadorFuncion to set
				 */
				public void setIndicadorFuncion(String indicadorFuncion) {
					this.indicadorFuncion = indicadorFuncion;
				}

				/**
				 * Gets the fiid.
				 *
				 * @return the fiid
				 */
				public String getFiid() {
					return fiid;
				}

				/**
				 * Sets the fiid.
				 *
				 * @param fiid
				 *            the fiid to set
				 */
				public void setFiid(String fiid) {
					this.fiid = fiid;
				}

				/**
				 * Gets the titular.
				 *
				 * @return the titular
				 */
				public String getTitular() {
					return titular;
				}

				/**
				 * Sets the titular.
				 *
				 * @param titular
				 *            the titular to set
				 */
				public void setTitular(String titular) {
					this.titular = titular;
				}

				/**
				 * Gets the tpo cta from bane.
				 *
				 * @return the tpoCtaFromBane
				 */
				public String getTpoCtaFromBane() {
					return tpoCtaFromBane;
				}

				/**
				 * Sets the tpo cta from bane.
				 *
				 * @param tpoCtaFromBane
				 *            the tpoCtaFromBane to set
				 */
				public void setTpoCtaFromBane(String tpoCtaFromBane) {
					this.tpoCtaFromBane = tpoCtaFromBane;
				}

				/**
				 * Gets the cta bane.
				 *
				 * @return the ctaBane
				 */
				public String getCtaBane() {
					return ctaBane;
				}

				/**
				 * Sets the cta bane.
				 *
				 * @param ctaBane
				 *            the ctaBane to set
				 */
				public void setCtaBane(String ctaBane) {
					this.ctaBane = ctaBane;
				}

				/**
				 * Gets the user.
				 *
				 * @return the user
				 */
				public String getUser() {
					return user;
				}

				/**
				 * Sets the user.
				 *
				 * @param user
				 *            the user to set
				 */
				public void setUser(String user) {
					this.user = user;
				}

				/**
				 * Gets the plazo acreditacion.
				 *
				 * @return the plazoAcreditacion
				 */
				public String getPlazoAcreditacion() {
					return plazoAcreditacion;
				}

				/**
				 * Sets the plazo acreditacion.
				 *
				 * @param plazoAcreditacion
				 *            the plazoAcreditacion to set
				 */
				public void setPlazoAcreditacion(String plazoAcreditacion) {
					this.plazoAcreditacion = plazoAcreditacion;
				}

				/**
				 * Gets the digito cta credito.
				 *
				 * @return the digitoCtaCredito
				 */
				public String getDigitoCtaCredito() {
					return digitoCtaCredito;
				}

				/**
				 * Sets the digito cta credito.
				 *
				 * @param digitoCtaCredito
				 *            the digitoCtaCredito to set
				 */
				public void setDigitoCtaCredito(String digitoCtaCredito) {
					this.digitoCtaCredito = digitoCtaCredito;
				}

				/**
				 * Gets the marca limite.
				 *
				 * @return the marcaLimite
				 */
				public String getMarcaLimite() {
					return marcaLimite;
				}

				/**
				 * Sets the marca limite.
				 *
				 * @param marcaLimite
				 *            the marcaLimite to set
				 */
				public void setMarcaLimite(String marcaLimite) {
					this.marcaLimite = marcaLimite;
				}

				/**
				 * Gets the referencia univoca.
				 *
				 * @return the referenciaUnivoca
				 */
				public String getReferenciaUnivoca() {
					return referenciaUnivoca;
				}

				/**
				 * Sets the referencia univoca.
				 *
				 * @param referenciaUnivoca
				 *            the referenciaUnivoca to set
				 */
				public void setReferenciaUnivoca(String referenciaUnivoca) {
					this.referenciaUnivoca = referenciaUnivoca;
				}

				/**
				 * Gets the identific beneficiario.
				 *
				 * @return the identificBeneficiario
				 */
				public String getIdentificBeneficiario() {
					return identificBeneficiario;
				}

				/**
				 * Sets the identific beneficiario.
				 *
				 * @param identificBeneficiario
				 *            the identificBeneficiario to set
				 */
				public void setIdentificBeneficiario(String identificBeneficiario) {
					this.identificBeneficiario = identificBeneficiario;
				}

				/**
				 * Gets the firmante cta debito.
				 *
				 * @return the firmanteCtaDebito
				 */
				public String getFirmanteCtaDebito() {
					return firmanteCtaDebito;
				}

				/**
				 * Sets the firmante cta debito.
				 *
				 * @param firmanteCtaDebito
				 *            the firmanteCtaDebito to set
				 */
				public void setFirmanteCtaDebito(String firmanteCtaDebito) {
					this.firmanteCtaDebito = firmanteCtaDebito;
				}

				/**
				 * Gets the cod concepto.
				 *
				 * @return the codConcepto
				 */
				public String getCodConcepto() {
					return codConcepto;
				}

				/**
				 * Sets the cod concepto.
				 *
				 * @param codConcepto
				 *            the codConcepto to set
				 */
				public void setCodConcepto(String codConcepto) {
					this.codConcepto = codConcepto;
				}

				/**
				 * Gets the banco receptor.
				 *
				 * @return the bancoReceptor
				 */
				public String getBancoReceptor() {
					return bancoReceptor;
				}

				/**
				 * Sets the banco receptor.
				 *
				 * @param bancoReceptor
				 *            the bancoReceptor to set
				 */
				public void setBancoReceptor(String bancoReceptor) {
					this.bancoReceptor = bancoReceptor;
				}

				/**
				 * Gets the i P maquina.
				 *
				 * @return the iPMaquina
				 */
				public String getiPMaquina() {
					return iPMaquina;
				}

				/**
				 * Sets the i P maquina.
				 *
				 * @param iPMaquina
				 *            the iPMaquina to set
				 */
				public void setiPMaquina(String iPMaquina) {
					this.iPMaquina = iPMaquina;
				}

				/**
				 * Gets the periodica.
				 *
				 * @return the periodica
				 */
				public String getPeriodica() {
					return periodica;
				}

				/**
				 * Sets the periodica.
				 *
				 * @param periodica
				 *            the periodica to set
				 */
				public void setPeriodica(String periodica) {
					this.periodica = periodica;
				}

				/**
				 * Gets the cantidad dias.
				 *
				 * @return the cantidadDias
				 */
				public String getCantidadDias() {
					return cantidadDias;
				}

				/**
				 * Sets the cantidad dias.
				 *
				 * @param cantidadDias
				 *            the cantidadDias to set
				 */
				public void setCantidadDias(String cantidadDias) {
					this.cantidadDias = cantidadDias;
				}

				/**
				 * Gets the entidad cta credito.
				 *
				 * @return the entidadCtaCredito
				 */
				public String getEntidadCtaCredito() {
					return entidadCtaCredito;
				}

				/**
				 * Sets the entidad cta credito.
				 *
				 * @param entidadCtaCredito
				 *            the entidadCtaCredito to set
				 */
				public void setEntidadCtaCredito(String entidadCtaCredito) {
					this.entidadCtaCredito = entidadCtaCredito;
				}

				/**
				 * Gets the banco destino.
				 *
				 * @return the bancoDestino
				 */
				public String getBancoDestino() {
					return bancoDestino;
				}

				/**
				 * Sets the banco destino.
				 *
				 * @param bancoDestino
				 *            the bancoDestino to set
				 */
				public void setBancoDestino(String bancoDestino) {
					this.bancoDestino = bancoDestino;
				}

				/**
				 * Gets the importe transferencia.
				 *
				 * @return the importeTransferencia
				 */
				public String getImporteTransferencia() {
					return importeTransferencia;
				}

				/**
				 * Sets the importe transferencia.
				 *
				 * @param importeTransferencia
				 *            the importeTransferencia to set
				 */
				public void setImporteTransferencia(String importeTransferencia) {
					this.importeTransferencia = importeTransferencia;
				}

				/**
				 * Gets the tipo transferencia.
				 *
				 * @return the tipoTransferencia
				 */
				public String getTipoTransferencia() {
					return tipoTransferencia;
				}

				/**
				 * Sets the tipo transferencia.
				 *
				 * @param tipoTransferencia
				 *            the tipoTransferencia to set
				 */
				public void setTipoTransferencia(String tipoTransferencia) {
					this.tipoTransferencia = tipoTransferencia;
				}

				/**
				 * Gets the tpo cta to bane.
				 *
				 * @return the tpoCtaToBane
				 */
				public String getTpoCtaToBane() {
					return tpoCtaToBane;
				}

				/**
				 * Sets the tpo cta to bane.
				 *
				 * @param tpoCtaToBane
				 *            the tpoCtaToBane to set
				 */
				public void setTpoCtaToBane(String tpoCtaToBane) {
					this.tpoCtaToBane = tpoCtaToBane;
				}

				/**
				 * Gets the desc concepto.
				 *
				 * @return the descConcepto
				 */
				public String getDescConcepto() {
					return descConcepto;
				}

				/**
				 * Sets the desc concepto.
				 *
				 * @param descConcepto
				 *            the descConcepto to set
				 */
				public void setDescConcepto(String descConcepto) {
					this.descConcepto = descConcepto;
				}

			}

			/**
			 * <p>
			 * Java class for anonymous complex type.
			 * 
			 * <p>
			 * The following schema fragment specifies the expected content
			 * contained within this class.
			 * 
			 * <pre>
			 * &lt;complexType>
			 *   &lt;complexContent>
			 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
			 *       &lt;sequence>
			 *         &lt;element name="DatosAdicionales">
			 *           &lt;complexType>
			 *             &lt;complexContent>
			 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
			 *                 &lt;sequence>
			 *                   &lt;element name="DatosSueldos">
			 *                     &lt;complexType>
			 *                       &lt;complexContent>
			 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
			 *                           &lt;sequence>
			 *                             &lt;element name="TipoPago" type="{http://www.w3.org/2001/XMLSchema}string"/>
			 *                             &lt;element name="NumeroCUIL" type="{http://www.w3.org/2001/XMLSchema}String"/>
			 *                             &lt;element name="Destinatario" type="{http://www.w3.org/2001/XMLSchema}string"/>
			 *                             &lt;element name="DescripcionConcepto" type="{http://www.w3.org/2001/XMLSchema}string"/>
			 *                             &lt;element name="TipoCUIL" type="{http://www.w3.org/2001/XMLSchema}string"/>
			 *                           &lt;/sequence>
			 *                         &lt;/restriction>
			 *                       &lt;/complexContent>
			 *                     &lt;/complexType>
			 *                   &lt;/element>
			 *                 &lt;/sequence>
			 *               &lt;/restriction>
			 *             &lt;/complexContent>
			 *           &lt;/complexType>
			 *         &lt;/element>
			 *       &lt;/sequence>
			 *     &lt;/restriction>
			 *   &lt;/complexContent>
			 * &lt;/complexType>
			 * </pre>
			 * 
			 * 
			 */
			@XmlAccessorType(XmlAccessType.FIELD)
			@XmlType(name = "", propOrder = { "datosAdicionales" })
			public static class DatosAdicional {

				/** The datos adicionales. */
				@XmlElement(name = "DatosAdicionales", required = true)
				protected XMLResponseEntity.DATOSRESULTADO.Registro.DatosAdicional.DatosAdicionales datosAdicionales;

				/**
				 * Gets the value of the datosAdicionales property.
				 * 
				 * @return possible object is
				 *         {@link XMLResponseEntity.DATOSRESULTADO.Registro.DatosAdicionales.DatosAdicionales }
				 * 
				 */
				public XMLResponseEntity.DATOSRESULTADO.Registro.DatosAdicional.DatosAdicionales getDatosAdicionales() {
					return datosAdicionales;
				}

				/**
				 * Sets the value of the datosAdicionales property.
				 * 
				 * @param value
				 *            allowed object is
				 *            {@link XMLResponseEntity.DATOSRESULTADO.Registro.DatosAdicionales.DatosAdicionales }
				 * 
				 */
				public void setDatosAdicionales(
						XMLResponseEntity.DATOSRESULTADO.Registro.DatosAdicional.DatosAdicionales value) {
					this.datosAdicionales = value;
				}

				/**
				 * <p>
				 * Java class for anonymous complex type.
				 * 
				 * <p>
				 * The following schema fragment specifies the expected content
				 * contained within this class.
				 * 
				 * <pre>
				 * &lt;complexType>
				 *   &lt;complexContent>
				 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
				 *       &lt;sequence>
				 *         &lt;element name="DatosSueldos">
				 *           &lt;complexType>
				 *             &lt;complexContent>
				 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
				 *                 &lt;sequence>
				 *                   &lt;element name="TipoPago" type="{http://www.w3.org/2001/XMLSchema}string"/>
				 *                   &lt;element name="NumeroCUIL" type="{http://www.w3.org/2001/XMLSchema}String"/>
				 *                   &lt;element name="Destinatario" type="{http://www.w3.org/2001/XMLSchema}string"/>
				 *                   &lt;element name="DescripcionConcepto" type="{http://www.w3.org/2001/XMLSchema}string"/>
				 *                   &lt;element name="TipoCUIL" type="{http://www.w3.org/2001/XMLSchema}string"/>
				 *                 &lt;/sequence>
				 *               &lt;/restriction>
				 *             &lt;/complexContent>
				 *           &lt;/complexType>
				 *         &lt;/element>
				 *       &lt;/sequence>
				 *     &lt;/restriction>
				 *   &lt;/complexContent>
				 * &lt;/complexType>
				 * </pre>
				 * 
				 * 
				 */
				@XmlAccessorType(XmlAccessType.FIELD)
				@XmlType(name = "", propOrder = { "datosMensAvisos", "datosSueldos" })
				public static class DatosAdicionales {

					/** The datos mens avisos. */
					@XmlElement(name = "DatosMensAvisos")
					protected XMLResponseEntity.DATOSRESULTADO.Registro.DatosAdicional.DatosAdicionales.DatosMensAvisos datosMensAvisos;

					/** The datos sueldos. */
					@XmlElement(name = "DatosSueldos")
					protected XMLResponseEntity.DATOSRESULTADO.Registro.DatosAdicional.DatosAdicionales.DatosSueldos datosSueldos;

					/**
					 * Gets the value of the datosSueldos property.
					 * 
					 * @return possible object is
					 *         {@link XMLResponseEntity.DATOSRESULTADO.Registro.DatosAdicionales.DatosAdicionales.DatosSueldos }
					 * 
					 */
					public XMLResponseEntity.DATOSRESULTADO.Registro.DatosAdicional.DatosAdicionales.DatosSueldos getDatosSueldos() {
						return datosSueldos;
					}

					/**
					 * Sets the value of the datosSueldos property.
					 * 
					 * @param value
					 *            allowed object is
					 *            {@link XMLResponseEntity.DATOSRESULTADO.Registro.DatosAdicionales.DatosAdicionales.DatosSueldos }
					 * 
					 */
					public void setDatosSueldos(
							XMLResponseEntity.DATOSRESULTADO.Registro.DatosAdicional.DatosAdicionales.DatosSueldos value) {
						this.datosSueldos = value;
					}

					/**
					 * The Class DatosMensAvisos.
					 *
					 * @author B041299
					 */
					@XmlAccessorType(XmlAccessType.FIELD)
					@XmlType(name = "", propOrder = { "concepto", "TitularCredito", "descripcionAdicional3",
							"descripcionAdicional2", "descripcionAdicional1", "mailClienteReply", "conceptoAdicional3",
							"conceptoAdicional2", "infoAdicional", "conceptoAdicional1", "anotacionesPersonales",
							"comentario", "mailCredito", "titularDebito" })
					public static class DatosMensAvisos {

						/** The concepto. */
						@XmlElement(name = "Concepto")
						protected String concepto;

						/** The Titular credito. */
						@XmlElement(name = "TitularCredito")
						protected String TitularCredito;

						/** The descripcion adicional 3. */
						@XmlElement(name = "DescripcionAdicional3")
						protected String descripcionAdicional3;

						/** The descripcion adicional 2. */
						@XmlElement(name = "DescripcionAdicional2")
						protected String descripcionAdicional2;

						/** The descripcion adicional 1. */
						@XmlElement(name = "DescripcionAdicional1")
						protected String descripcionAdicional1;

						/** The mail cliente reply. */
						@XmlElement(name = "MailClienteReply")
						protected String mailClienteReply;

						/** The concepto adicional 3. */
						@XmlElement(name = "ConceptoAdicional3")
						protected String conceptoAdicional3;

						/** The concepto adicional 2. */
						@XmlElement(name = "ConceptoAdicional2")
						protected String conceptoAdicional2;

						/** The info adicional. */
						@XmlElement(name = "InfoAdicional")
						protected String infoAdicional;

						/** The concepto adicional 1. */
						@XmlElement(name = "ConceptoAdicional1")
						protected String conceptoAdicional1;

						/** The anotaciones personales. */
						@XmlElement(name = "AnotacionesPersonales")
						protected String anotacionesPersonales;

						/** The comentario. */
						@XmlElement(name = "Comentario")
						protected String comentario;

						/** The mail credito. */
						@XmlElement(name = "MailCredito")
						protected String mailCredito;

						/** The titular debito. */
						@XmlElement(name = "TitularDebito")
						protected String titularDebito;

						/**
						 * Gets the concepto.
						 *
						 * @return the concepto
						 */
						public String getConcepto() {
							return concepto;
						}

						/**
						 * Sets the concepto.
						 *
						 * @param concepto
						 *            the concepto to set
						 */
						public void setConcepto(String concepto) {
							this.concepto = concepto;
						}

						/**
						 * Gets the titular credito.
						 *
						 * @return the titularCredito
						 */
						public String getTitularCredito() {
							return TitularCredito;
						}

						/**
						 * Sets the titular credito.
						 *
						 * @param titularCredito
						 *            the titularCredito to set
						 */
						public void setTitularCredito(String titularCredito) {
							TitularCredito = titularCredito;
						}

						/**
						 * Gets the descripcion adicional 3.
						 *
						 * @return the descripcionAdicional3
						 */
						public String getDescripcionAdicional3() {
							return descripcionAdicional3;
						}

						/**
						 * Sets the descripcion adicional 3.
						 *
						 * @param descripcionAdicional3
						 *            the descripcionAdicional3 to set
						 */
						public void setDescripcionAdicional3(String descripcionAdicional3) {
							this.descripcionAdicional3 = descripcionAdicional3;
						}

						/**
						 * Gets the descripcion adicional 2.
						 *
						 * @return the descripcionAdicional2
						 */
						public String getDescripcionAdicional2() {
							return descripcionAdicional2;
						}

						/**
						 * Sets the descripcion adicional 2.
						 *
						 * @param descripcionAdicional2
						 *            the descripcionAdicional2 to set
						 */
						public void setDescripcionAdicional2(String descripcionAdicional2) {
							this.descripcionAdicional2 = descripcionAdicional2;
						}

						/**
						 * Gets the descripcion adicional 1.
						 *
						 * @return the descripcionAdicional1
						 */
						public String getDescripcionAdicional1() {
							return descripcionAdicional1;
						}

						/**
						 * Sets the descripcion adicional 1.
						 *
						 * @param descripcionAdicional1
						 *            the descripcionAdicional1 to set
						 */
						public void setDescripcionAdicional1(String descripcionAdicional1) {
							this.descripcionAdicional1 = descripcionAdicional1;
						}

						/**
						 * Gets the mail cliente reply.
						 *
						 * @return the mailClienteReply
						 */
						public String getMailClienteReply() {
							return mailClienteReply;
						}

						/**
						 * Sets the mail cliente reply.
						 *
						 * @param mailClienteReply
						 *            the mailClienteReply to set
						 */
						public void setMailClienteReply(String mailClienteReply) {
							this.mailClienteReply = mailClienteReply;
						}

						/**
						 * Gets the concepto adicional 3.
						 *
						 * @return the conceptoAdicional3
						 */
						public String getConceptoAdicional3() {
							return conceptoAdicional3;
						}

						/**
						 * Sets the concepto adicional 3.
						 *
						 * @param conceptoAdicional3
						 *            the conceptoAdicional3 to set
						 */
						public void setConceptoAdicional3(String conceptoAdicional3) {
							this.conceptoAdicional3 = conceptoAdicional3;
						}

						/**
						 * Gets the concepto adicional 2.
						 *
						 * @return the conceptoAdicional2
						 */
						public String getConceptoAdicional2() {
							return conceptoAdicional2;
						}

						/**
						 * Sets the concepto adicional 2.
						 *
						 * @param conceptoAdicional2
						 *            the conceptoAdicional2 to set
						 */
						public void setConceptoAdicional2(String conceptoAdicional2) {
							this.conceptoAdicional2 = conceptoAdicional2;
						}

						/**
						 * Gets the info adicional.
						 *
						 * @return the infoAdicional
						 */
						public String getInfoAdicional() {
							return infoAdicional;
						}

						/**
						 * Sets the info adicional.
						 *
						 * @param infoAdicional
						 *            the infoAdicional to set
						 */
						public void setInfoAdicional(String infoAdicional) {
							this.infoAdicional = infoAdicional;
						}

						/**
						 * Gets the concepto adicional 1.
						 *
						 * @return the conceptoAdicional1
						 */
						public String getConceptoAdicional1() {
							return conceptoAdicional1;
						}

						/**
						 * Sets the concepto adicional 1.
						 *
						 * @param conceptoAdicional1
						 *            the conceptoAdicional1 to set
						 */
						public void setConceptoAdicional1(String conceptoAdicional1) {
							this.conceptoAdicional1 = conceptoAdicional1;
						}

						/**
						 * Gets the anotaciones personales.
						 *
						 * @return the anotacionesPersonales
						 */
						public String getAnotacionesPersonales() {
							return anotacionesPersonales;
						}

						/**
						 * Sets the anotaciones personales.
						 *
						 * @param anotacionesPersonales
						 *            the anotacionesPersonales to set
						 */
						public void setAnotacionesPersonales(String anotacionesPersonales) {
							this.anotacionesPersonales = anotacionesPersonales;
						}

						/**
						 * Gets the comentario.
						 *
						 * @return the comentario
						 */
						public String getComentario() {
							return comentario;
						}

						/**
						 * Sets the comentario.
						 *
						 * @param comentario
						 *            the comentario to set
						 */
						public void setComentario(String comentario) {
							this.comentario = comentario;
						}

						/**
						 * Gets the mail credito.
						 *
						 * @return the mailCredito
						 */
						public String getMailCredito() {
							return mailCredito;
						}

						/**
						 * Sets the mail credito.
						 *
						 * @param mailCredito
						 *            the mailCredito to set
						 */
						public void setMailCredito(String mailCredito) {
							this.mailCredito = mailCredito;
						}

						/**
						 * Gets the titular debito.
						 *
						 * @return the titularDebito
						 */
						public String getTitularDebito() {
							return titularDebito;
						}

						/**
						 * Sets the titular debito.
						 *
						 * @param titularDebito
						 *            the titularDebito to set
						 */
						public void setTitularDebito(String titularDebito) {
							this.titularDebito = titularDebito;
						}
					}

					/**
					 * <p>
					 * Java class for anonymous complex type.
					 * 
					 * <p>
					 * The following schema fragment specifies the expected
					 * content contained within this class.
					 * 
					 * <pre>
					 * &lt;complexType>
					 *   &lt;complexContent>
					 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
					 *       &lt;sequence>
					 *         &lt;element name="TipoPago" type="{http://www.w3.org/2001/XMLSchema}string"/>
					 *         &lt;element name="NumeroCUIL" type="{http://www.w3.org/2001/XMLSchema}String"/>
					 *         &lt;element name="Destinatario" type="{http://www.w3.org/2001/XMLSchema}string"/>
					 *         &lt;element name="DescripcionConcepto" type="{http://www.w3.org/2001/XMLSchema}string"/>
					 *         &lt;element name="TipoCUIL" type="{http://www.w3.org/2001/XMLSchema}string"/>
					 *       &lt;/sequence>
					 *     &lt;/restriction>
					 *   &lt;/complexContent>
					 * &lt;/complexType>
					 * </pre>
					 * 
					 * 
					 */
					@XmlAccessorType(XmlAccessType.FIELD)
					@XmlType(name = "", propOrder = { "tipoPago", "numeroCUIL", "destinatario", "descripcionConcepto",
							"tipoCUIL" })
					public static class DatosSueldos {

						/** The tipo pago. */
						@XmlElement(name = "TipoPago", required = true)
						protected String tipoPago;

						/** The numero CUIL. */
						@XmlElement(name = "NumeroCUIL")
						protected String numeroCUIL;

						/** The destinatario. */
						@XmlElement(name = "Destinatario", required = true)
						protected String destinatario;

						/** The descripcion concepto. */
						@XmlElement(name = "DescripcionConcepto", required = true)
						protected String descripcionConcepto;

						/** The tipo CUIL. */
						@XmlElement(name = "TipoCUIL", required = true)
						protected String tipoCUIL;

						/**
						 * Gets the value of the tipoPago property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getTipoPago() {
							return tipoPago;
						}

						/**
						 * Sets the value of the tipoPago property.
						 * 
						 * @param value
						 *            allowed object is {@link String }
						 * 
						 */
						public void setTipoPago(String value) {
							this.tipoPago = value;
						}

						/**
						 * Gets the value of the numeroCUIL property.
						 *
						 * @return the numero CUIL
						 */
						public String getNumeroCUIL() {
							return numeroCUIL;
						}

						/**
						 * Sets the value of the numeroCUIL property.
						 *
						 * @param value
						 *            the new numero CUIL
						 */
						public void setNumeroCUIL(String value) {
							this.numeroCUIL = value;
						}

						/**
						 * Gets the value of the destinatario property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getDestinatario() {
							return destinatario;
						}

						/**
						 * Sets the value of the destinatario property.
						 * 
						 * @param value
						 *            allowed object is {@link String }
						 * 
						 */
						public void setDestinatario(String value) {
							this.destinatario = value;
						}

						/**
						 * Gets the value of the descripcionConcepto property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getDescripcionConcepto() {
							return descripcionConcepto;
						}

						/**
						 * Sets the value of the descripcionConcepto property.
						 * 
						 * @param value
						 *            allowed object is {@link String }
						 * 
						 */
						public void setDescripcionConcepto(String value) {
							this.descripcionConcepto = value;
						}

						/**
						 * Gets the value of the tipoCUIL property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getTipoCUIL() {
							return tipoCUIL;
						}

						/**
						 * Sets the value of the tipoCUIL property.
						 * 
						 * @param value
						 *            allowed object is {@link String }
						 * 
						 */
						public void setTipoCUIL(String value) {
							this.tipoCUIL = value;
						}

					}

					/**
					 * Gets the datos mens avisos.
					 *
					 * @return the datosMensAvisos
					 */
					public XMLResponseEntity.DATOSRESULTADO.Registro.DatosAdicional.DatosAdicionales.DatosMensAvisos getDatosMensAvisos() {
						return datosMensAvisos;
					}

					/**
					 * Sets the datos mens avisos.
					 *
					 * @param datosMensAvisos
					 *            the datosMensAvisos to set
					 */
					public void setDatosMensAvisos(
							XMLResponseEntity.DATOSRESULTADO.Registro.DatosAdicional.DatosAdicionales.DatosMensAvisos datosMensAvisos) {
						this.datosMensAvisos = datosMensAvisos;
					}

				}

			}

		}

		/**
		 * Gets the nro op canal.
		 *
		 * @return the nroOpCanal
		 */
		public String getNroOpCanal() {
			return nroOpCanal;
		}

		/**
		 * Sets the nro op canal.
		 *
		 * @param nroOpCanal
		 *            the nroOpCanal to set
		 */
		public void setNroOpCanal(String nroOpCanal) {
			this.nroOpCanal = nroOpCanal;
		}

		/**
		 * Gets the cotizacion.
		 *
		 * @return the cotizacion
		 */
		public String getCotizacion() {
			return cotizacion;
		}

		/**
		 * Sets the cotizacion.
		 *
		 * @param cotizacion
		 *            the cotizacion to set
		 */
		public void setCotizacion(String cotizacion) {
			this.cotizacion = cotizacion;
		}

		/**
		 * Gets the importe destino.
		 *
		 * @return the importeDestino
		 */
		public String getImporteDestino() {
			return importeDestino;
		}

		/**
		 * Sets the importe destino.
		 *
		 * @param importeDestino
		 *            the importeDestino to set
		 */
		public void setImporteDestino(String importeDestino) {
			this.importeDestino = importeDestino;
		}

		/**
		 * Gets the comprobante back end.
		 *
		 * @return the comprobanteBackEnd
		 */
		public String getComprobanteBackEnd() {
			return comprobanteBackEnd;
		}

		/**
		 * Sets the comprobante back end.
		 *
		 * @param comprobanteBackEnd
		 *            the comprobanteBackEnd to set
		 */
		public void setComprobanteBackEnd(String comprobanteBackEnd) {
			this.comprobanteBackEnd = comprobanteBackEnd;
		}

		/**
		 * Gets the id recibo.
		 *
		 * @return the idRecibo
		 */
		public String getIdRecibo() {
			return idRecibo;
		}

		/**
		 * Sets the id recibo.
		 *
		 * @param idRecibo
		 *            the idRecibo to set
		 */
		public void setIdRecibo(String idRecibo) {
			this.idRecibo = idRecibo;
		}

		/**
		 * Gets the importe origen.
		 *
		 * @return the importeOrigen
		 */
		public String getImporteOrigen() {
			return importeOrigen;
		}

		/**
		 * Sets the importe origen.
		 *
		 * @param importeOrigen
		 *            the importeOrigen to set
		 */
		public void setImporteOrigen(String importeOrigen) {
			this.importeOrigen = importeOrigen;
		}

		/**
		 * Gets the id transaccion.
		 *
		 * @return the idtransaccion
		 */
		public String getIdTransaccion() {
			return idTransaccion;
		}

		/**
		 * Sets the idtransaccion.
		 *
		 * @param idtransaccion
		 *            the idtransaccion to set
		 */
		public void setIdtransaccion(String idtransaccion) {
			this.idTransaccion = idtransaccion;
		}

		/**
		 * Sets the descripcion status resultado.
		 *
		 * @param descripcionStatusResultado
		 *            the descripcionStatusResultado to set
		 */
		public void setDescripcionStatusResultado(
				List<XMLResponseEntity.DATOSRESULTADO.DescripcionStatusResultado> descripcionStatusResultado) {
			this.descripcionStatusResultado = descripcionStatusResultado;
		}

		/**
		 * Sets the registro.
		 *
		 * @param registro
		 *            the registro to set
		 */
		public void setRegistro(List<XMLResponseEntity.DATOSRESULTADO.Registro> registro) {
			this.registro = registro;
		}

		/**
		 * Gets the fecha compensacion.
		 *
		 * @return the fechaCompensacion
		 */
		public String getFechaCompensacion() {
			return fechaCompensacion;
		}

		/**
		 * Sets the fecha compensacion.
		 *
		 * @param fechaCompensacion
		 *            the fechaCompensacion to set
		 */
		public void setFechaCompensacion(String fechaCompensacion) {
			this.fechaCompensacion = fechaCompensacion;
		}

		/**
		 * Gets the nro secuencia.
		 *
		 * @return the nroSecuencia
		 */
		public String getNroSecuencia() {
			return nroSecuencia;
		}

		/**
		 * Sets the nro secuencia.
		 *
		 * @param nroSecuencia
		 *            the nroSecuencia to set
		 */
		public void setNroSecuencia(String nroSecuencia) {
			this.nroSecuencia = nroSecuencia;
		}

		/**
		 * Gets the importe debitado.
		 *
		 * @return the importeDebitado
		 */
		public String getImporteDebitado() {
			return importeDebitado;
		}

		/**
		 * Sets the importe debitado.
		 *
		 * @param importeDebitado
		 *            the importeDebitado to set
		 */
		public void setImporteDebitado(String importeDebitado) {
			this.importeDebitado = importeDebitado;
		}

		/**
		 * Gets the codigo estado.
		 *
		 * @return the codigoEstado
		 */
		public String getCodigoEstado() {
			return codigoEstado;
		}

		/**
		 * Sets the codigo estado.
		 *
		 * @param codigoEstado
		 *            the codigoEstado to set
		 */
		public void setCodigoEstado(String codigoEstado) {
			this.codigoEstado = codigoEstado;
		}

		/**
		 * Gets the nro comprobante.
		 *
		 * @return the nroComprobante
		 */
		public String getNroComprobante() {
			return nroComprobante;
		}

		/**
		 * Sets the nro comprobante.
		 *
		 * @param nroComprobante
		 *            the nroComprobante to set
		 */
		public void setNroComprobante(String nroComprobante) {
			this.nroComprobante = nroComprobante;
		}

		/**
		 * Sets the id transaccion.
		 *
		 * @param idTransaccion
		 *            the idTransaccion to set
		 */
		public void setIdTransaccion(String idTransaccion) {
			this.idTransaccion = idTransaccion;
		}
	}

}