/*
 * 
 */

package ar.com.santanderrio.obp.servicios.sieteporveinticuatro.version1.entities;

import ar.com.santanderrio.obp.servicios.transferencias.web.util.TransferenciaUtil;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element name="CONFIG">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="VersionXML" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="EcoDatosEntrada" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="META">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Nombre" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="Version" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *                   &lt;element name="Cliente">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="TipoPersona" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="TipoId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="IdCliente" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                             &lt;element name="FechaNac" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                             &lt;element name="NUP" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="Usuario">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="UsuarioTipo" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *                             &lt;element name="UsuarioId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="UsuarioPwd" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="UsuarioAtrib" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="IndAuten" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *                   &lt;element name="IdSesionCnt" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="Canal">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="CanalTipo" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *                             &lt;element name="CanalId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="CanalVersion" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="Subcanal">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="SubcanalTipo" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *                             &lt;element name="SubcanalId" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="ModoEjecucion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="DATOSENTRADA">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Parametros">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="NombreGuardado" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="NUPGuardado" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                             &lt;element name="FechaEjecucionDesde" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                             &lt;element name="FechaEjecucionHasta" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
@XmlType(name = "", propOrder = { "config", "meta", "datosentrada", "datosAdicionales" })
@XmlRootElement(name = "XML")
public class XMLRequestEntity {

	/** The config. */
	@XmlElement(name = "CONFIG", required = true)
	protected XMLRequestEntity.CONFIG config;

	/** The meta. */
	@XmlElement(name = "META", required = true)
	protected XMLRequestEntity.META meta;

	/** The datosentrada. */
	@XmlElement(name = "DATOSENTRADA", required = true)
	protected XMLRequestEntity.DATOSENTRADA datosentrada;

	/** The datosAdicionales. */
	@XmlElement(name = "DatosAdicionales", required = false)
	protected XMLRequestEntity.DATOSADICIONALES datosAdicionales;

	/**
	 * Gets the value of the config property.
	 * 
	 * @return possible object is {@link XMLRequestEntity.CONFIG }
	 * 
	 */
	public XMLRequestEntity.CONFIG getCONFIG() {
		return config;
	}

	/**
	 * Sets the value of the config property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLRequestEntity.CONFIG }
	 * 
	 */
	public void setCONFIG(XMLRequestEntity.CONFIG value) {
		this.config = value;
	}

	/**
	 * Gets the value of the meta property.
	 * 
	 * @return possible object is {@link XMLRequestEntity.META }
	 * 
	 */
	public XMLRequestEntity.META getMETA() {
		return meta;
	}

	/**
	 * Sets the value of the meta property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLRequestEntity.META }
	 * 
	 */
	public void setMETA(XMLRequestEntity.META value) {
		this.meta = value;
	}

	/**
	 * Gets the value of the datosentrada property.
	 * 
	 * @return possible object is {@link XMLRequestEntity.DATOSENTRADA }
	 * 
	 */
	public XMLRequestEntity.DATOSENTRADA getDATOSENTRADA() {
		return datosentrada;
	}

	/**
	 * Sets the value of the datosentrada property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLRequestEntity.DATOSENTRADA }
	 * 
	 */
	public void setDATOSENTRADA(XMLRequestEntity.DATOSENTRADA value) {
		this.datosentrada = value;
	}

	/**
	 * Gets the datos adicionales.
	 *
	 * @return the datos adicionales
	 */
	public XMLRequestEntity.DATOSADICIONALES getDatosAdicionales() {
		return datosAdicionales;
	}

	/**
	 * Sets the datos adicionales.
	 *
	 * @param datosAdicionales
	 *            the new datos adicionales
	 */
	public void setDatosAdicionales(XMLRequestEntity.DATOSADICIONALES datosAdicionales) {
		this.datosAdicionales = datosAdicionales;
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
	 *         &lt;element name="VersionXML" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="EcoDatosEntrada" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *       &lt;/sequence>
	 *     &lt;/restriction>
	 *   &lt;/complexContent>
	 * &lt;/complexType>
	 * </pre>
	 * 
	 * 
	 */
	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType(name = "", propOrder = { "versionXML", "ecoDatosEntrada" })
	public static class CONFIG {

		/** The version XML. */
		@XmlElement(name = "VersionXML", required = true)
		protected String versionXML;

		/** The eco datos entrada. */
		@XmlElement(name = "EcoDatosEntrada", required = true)
		protected String ecoDatosEntrada;

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
	 *         &lt;element name="Parametros">
	 *           &lt;complexType>
	 *             &lt;complexContent>
	 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
	 *                 &lt;sequence>
	 *                   &lt;element name="NombreGuardado" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *                   &lt;element name="NUPGuardado" type="{http://www.w3.org/2001/XMLSchema}int"/>
	 *                   &lt;element name="FechaEjecucionDesde" type="{http://www.w3.org/2001/XMLSchema}int"/>
	 *                   &lt;element name="FechaEjecucionHasta" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
	@XmlType(name = "", propOrder = { "parametros" })
	public static class DATOSENTRADA {

		/** The parametros. */
		@XmlElement(name = "Parametros", required = true)
		protected XMLRequestEntity.DATOSENTRADA.Parametros parametros;

		/**
		 * Gets the value of the parametros property.
		 * 
		 * @return possible object is
		 *         {@link XMLRequestEntity.DATOSENTRADA.Parametros }
		 * 
		 */
		public XMLRequestEntity.DATOSENTRADA.Parametros getParametros() {
			return parametros;
		}

		/**
		 * Sets the value of the parametros property.
		 * 
		 * @param value
		 *            allowed object is
		 *            {@link XMLRequestEntity.DATOSENTRADA.Parametros }
		 * 
		 */
		public void setParametros(XMLRequestEntity.DATOSENTRADA.Parametros value) {
			this.parametros = value;
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
		 *         &lt;element name="NombreGuardado" type="{http://www.w3.org/2001/XMLSchema}string"/>
		 *         &lt;element name="NUPGuardado" type="{http://www.w3.org/2001/XMLSchema}int"/>
		 *         &lt;element name="FechaEjecucionDesde" type="{http://www.w3.org/2001/XMLSchema}int"/>
		 *         &lt;element name="FechaEjecucionHasta" type="{http://www.w3.org/2001/XMLSchema}int"/>
		 *       &lt;/sequence>
		 *     &lt;/restriction>
		 *   &lt;/complexContent>
		 * &lt;/complexType>
		 * </pre>
		 * 
		 * 
		 */
		@XmlAccessorType(XmlAccessType.FIELD)
		@XmlType(name = "", propOrder = { "nombreGuardado", "nupGuardado", "fechaEjecucionDesde", "fechaEjecucionHasta",
				"tipoCtaDebito", "sucCtaDebito", "nroCtaDebito", "codigoDebito", "subcodigoDebito", "tipoCtaCredito",
				"sucCtaCredito", "nroCtaCredito", "codigoCredito", "subcodigoCredito", "comprobanteCredito", "importe",
				"limiteSobregiro", "importeDebito", "ejecutadoPorAgendamiento", "nombreCtaCredito",
				"cotizacionTransferencia", "codigoConcepto", "codConcepto", "nroComprobante", "indicadorLimiteMax",
				"indicadorAfectarDisponible", "descripcionConcepto", "descConcepto", "importeCredito", "cuentaPropia",
				"modoEjecucionGuardado", "estado", "accion", "informacDiscrecional", "cbu", "caracteristicaCtaCredito",
				"monedaTransferencia", "longCtaDesBane", "cuit3", "cuit2", "cuit1", "marcaGravado", "indicadorFuncion",
				"fiid", "titular", "tpoCtaFromBane", "ctaBane", "user", "plazoAcreditacion", "digitoCtaCredito",
				"marcaLimite", "referenciaUnivoca", "identificBeneficiario", "firmanteCtaDebito", "bancoReceptor",
				"iPMaquina", "periodica", "cantidadDias", "entidadCtaCredito", "bancoDestino", "importeTransferencia",
				"tipoTransferencia", "tpoCtaToBane", "indTransfDiferida" })
		public static class Parametros {

			/** The nombre guardado. */
			@XmlElement(name = "NombreGuardado", required = true)
			protected String nombreGuardado;

			/** The nup guardado. */
			@XmlElement(name = "NUPGuardado")
			protected String nupGuardado;

			/** The fecha ejecucion desde. */
			@XmlElement(name = "FechaEjecucionDesde")
			protected String fechaEjecucionDesde;

			/** The fecha ejecucion hasta. */
			@XmlElement(name = "FechaEjecucionHasta")
			protected String fechaEjecucionHasta;

			/** The tipo cta debito. */
			@XmlElement(name = "TipoCtaDebito")
			protected String tipoCtaDebito;

			/** The suc cta debito. */
			@XmlElement(name = "SucCtaDebito")
			protected String sucCtaDebito;

			/** The nro cta debito. */
			@XmlElement(name = "NroCtaDebito")
			protected String nroCtaDebito;

			/** The codigo debito. */
			@XmlElement(name = "CodigoDebito")
			protected String codigoDebito;

			/** The subcodigo debito. */
			@XmlElement(name = "SubcodigoDebito")
			protected String subcodigoDebito;

			/** The tipo cta credito. */
			@XmlElement(name = "TipoCtaCredito")
			protected String tipoCtaCredito;

			/** The suc cta credito. */
			@XmlElement(name = "SucCtaCredito")
			protected String sucCtaCredito;

			/** The nro cta credito. */
			@XmlElement(name = "NroCtaCredito")
			protected String nroCtaCredito;

			/** The codigo credito. */
			@XmlElement(name = "CodigoCredito")
			protected String codigoCredito;

			/** The subcodigo credito. */
			@XmlElement(name = "SubcodigoCredito")
			protected String subcodigoCredito;

			/** The comprobante credito. */
			@XmlElement(name = "ComprobanteCredito")
			protected String comprobanteCredito;

			/** The importe. */
			@XmlElement(name = "Importe")
			protected String importe;

			/** The limite sobregiro. */
			@XmlElement(name = "LimiteSobregiro")
			protected String limiteSobregiro;

			/** The importe debito. */
			@XmlElement(name = "ImporteDebito")
			protected String importeDebito;

			/** The ejecutado por agendamiento. */
			@XmlElement(name = "EjecutadoPorAgendamiento")
			protected String ejecutadoPorAgendamiento;

			// RR

			/** The nombre cta credito. */
			@XmlElement(name = "NombreCtaCredito")
			protected String nombreCtaCredito;

			/** The cotizacion transferencia. */
			@XmlElement(name = "CotizacionTransferencia")
			protected String cotizacionTransferencia;

			/** The codigo concepto. */
			@XmlElement(name = "CodigoConcepto")
			protected String codigoConcepto;

			/** The cod concepto. */
			@XmlElement(name = "CodConcepto")
			protected String codConcepto;

			/** The nro comprobante. */
			@XmlElement(name = "NroComprobante")
			protected String nroComprobante;

			/** The indicador limite max. */
			@XmlElement(name = "IndicadorLimiteMax")
			protected String indicadorLimiteMax;

			/** The indicador afectar disponible. */
			@XmlElement(name = "IndicadorAfectarDisponible")
			protected String indicadorAfectarDisponible;

			/** The descripcion concepto. */
			@XmlElement(name = "DescripcionConcepto")
			protected String descripcionConcepto;

			/** The desc concepto. */
			@XmlElement(name = "DescConcepto")
			protected String descConcepto;

			/** The importe credito. */
			@XmlElement(name = "ImporteCredito")
			protected String importeCredito;

			/** The cuenta propia. */
			@XmlElement(name = "CuentaPropia")
			protected String cuentaPropia;

			/** The modo ejecucion guardado. */
			@XmlElement(name = "ModoEjecucionGuardado")
			protected String modoEjecucionGuardado;

			/** The estado. */
			@XmlElement(name = "Estado")
			protected String estado;

			/** The accion. */
			@XmlElement(name = "Accion")
			protected String accion;

			// OB

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

			/** The ind transf diferida. */
			@XmlElement(name = "IndTransfDiferida")
			private String indTransfDiferida;

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
			 * Gets the value of the nombreGuardado property.
			 * 
			 * @return possible object is {@link String }
			 * 
			 */
			public String getNombreGuardado() {
				return nombreGuardado;
			}

			/**
			 * Sets the value of the nombreGuardado property.
			 * 
			 * @param value
			 *            allowed object is {@link String }
			 * 
			 */
			public void setNombreGuardado(String value) {
				this.nombreGuardado = value;
			}

			/**
			 * Gets the value of the nupGuardado property.
			 *
			 * @return the NUP guardado
			 */
			public String getNUPGuardado() {
				return nupGuardado;
			}

			/**
			 * Sets the value of the nupGuardado property.
			 *
			 * @param value
			 *            the new NUP guardado
			 */
			public void setNUPGuardado(String value) {
				this.nupGuardado = value;
			}

			/**
			 * Gets the value of the fechaEjecucionDesde property.
			 *
			 * @return the fecha ejecucion desde
			 */
			public String getFechaEjecucionDesde() {
				return fechaEjecucionDesde;
			}

			/**
			 * Sets the value of the fechaEjecucionDesde property.
			 *
			 * @param value
			 *            the new fecha ejecucion desde
			 */
			public void setFechaEjecucionDesde(String value) {
				this.fechaEjecucionDesde = value;
			}

			/**
			 * Gets the value of the fechaEjecucionHasta property.
			 *
			 * @return the fecha ejecucion hasta
			 */
			public String getFechaEjecucionHasta() {
				return fechaEjecucionHasta;
			}

			/**
			 * Sets the value of the fechaEjecucionHasta property.
			 *
			 * @param value
			 *            the new fecha ejecucion hasta
			 */
			public void setFechaEjecucionHasta(String value) {
				this.fechaEjecucionHasta = value;
			}

			/**
			 * Gets the value of the nupGuardado property.
			 *
			 * @return the nup guardado
			 */
			public String getNupGuardado() {
				return nupGuardado;
			}

			/**
			 * Sets the value of the nupGuardado property.
			 *
			 * @param nupGuardado
			 *            the new nup guardado
			 */
			public void setNupGuardado(String nupGuardado) {
				this.nupGuardado = nupGuardado;
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
			 * @param tipoCtaDebito
			 *            the new tipo cta debito
			 */
			public void setTipoCtaDebito(String tipoCtaDebito) {
				this.tipoCtaDebito = tipoCtaDebito;
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
			 * @param sucCtaDebito
			 *            the new suc cta debito
			 */
			public void setSucCtaDebito(String sucCtaDebito) {
				this.sucCtaDebito = sucCtaDebito;
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
			 * @param nroCtaDebito
			 *            the new nro cta debito
			 */
			public void setNroCtaDebito(String nroCtaDebito) {
				this.nroCtaDebito = nroCtaDebito;
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
			 * @param codigoDebito
			 *            the new codigo debito
			 */
			public void setCodigoDebito(String codigoDebito) {
				this.codigoDebito = codigoDebito;
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
			 * @param subcodigoDebito
			 *            the new subcodigo debito
			 */
			public void setSubcodigoDebito(String subcodigoDebito) {
				this.subcodigoDebito = subcodigoDebito;
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
			 * @param tipoCtaCredito
			 *            the new tipo cta credito
			 */
			public void setTipoCtaCredito(String tipoCtaCredito) {
				this.tipoCtaCredito = tipoCtaCredito;
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
			 * @param sucCtaCredito
			 *            the new suc cta credito
			 */
			public void setSucCtaCredito(String sucCtaCredito) {
				this.sucCtaCredito = sucCtaCredito;
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
			 * @param nroCtaCredito
			 *            the new nro cta credito
			 */
			public void setNroCtaCredito(String nroCtaCredito) {
				this.nroCtaCredito = nroCtaCredito;
			}

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
			 * @param codigoCredito
			 *            the new codigo credito
			 */
			public void setCodigoCredito(String codigoCredito) {
				this.codigoCredito = codigoCredito;
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
			 * @param subcodigoCredito
			 *            the new subcodigo credito
			 */
			public void setSubcodigoCredito(String subcodigoCredito) {
				this.subcodigoCredito = subcodigoCredito;
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
			 * @param comprobanteCredito
			 *            the new comprobante credito
			 */
			public void setComprobanteCredito(String comprobanteCredito) {
				this.comprobanteCredito = comprobanteCredito;
			}

			/**
			 * Gets the value of the importe property.
			 *
			 * @return the importe
			 */
			public String getImporte() {
				return importe;
			}

			/**
			 * Sets the value of the importe property.
			 *
			 * @param importe
			 *            the new importe
			 */
			public void setImporte(String importe) {
				this.importe = importe;
			}

			/**
			 * Sets the value of the limiteSobregiro property.
			 *
			 * @return the limite sobregiro
			 */
			public String getLimiteSobregiro() {
				return this.limiteSobregiro;
			}

			/**
			 * Sets the value of the limiteSobregiro property.
			 *
			 * @param limiteSobregiro
			 *            the new limite sobregiro
			 */
			public void setLimiteSobregiro(String limiteSobregiro) {
				this.limiteSobregiro = limiteSobregiro;
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
			 * Gets the ejecutado por agendamiento.
			 *
			 * @return the ejecutado por agendamiento
			 */
			public String getEjecutadoPorAgendamiento() {
				return ejecutadoPorAgendamiento;
			}

			/**
			 * Sets the ejecutado por agendamiento.
			 *
			 * @param ejecutadoPorAgendamiento
			 *            the new ejecutado por agendamiento
			 */
			public void setEjecutadoPorAgendamiento(String ejecutadoPorAgendamiento) {
				this.ejecutadoPorAgendamiento = ejecutadoPorAgendamiento;
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

				this.iPMaquina = TransferenciaUtil.getIpSinPuntosYFormatoFijo(iPMaquina);
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
			 * Gets the modo ejecucion guardado.
			 *
			 * @return the modo ejecucion guardado
			 */
			public String getModoEjecucionGuardado() {
				return modoEjecucionGuardado;
			}

			/**
			 * Sets the modo ejecucion guardado.
			 *
			 * @param modoEjecucionGuardado
			 *            the new modo ejecucion guardado
			 */
			public void setModoEjecucionGuardado(String modoEjecucionGuardado) {
				this.modoEjecucionGuardado = modoEjecucionGuardado;
			}

			/**
			 * Gets the estado.
			 *
			 * @return the estado
			 */
			public String getEstado() {
				return estado;
			}

			/**
			 * Sets the estado.
			 *
			 * @param estado
			 *            the new estado
			 */
			public void setEstado(String estado) {
				this.estado = estado;
			}

			/**
			 * Gets the accion.
			 *
			 * @return the accion
			 */
			public String getAccion() {
				return accion;
			}

			/**
			 * Sets the accion.
			 *
			 * @param accion
			 *            the new accion
			 */
			public void setAccion(String accion) {
				this.accion = accion;
			}

		}

	}

	/**
	 * The Class DATOSADICIONALES.
	 */
	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType(name = "", propOrder = { "datosSueldos", "datosMensAvisos" })
	public static class DATOSADICIONALES {

		/** The parametros. */
		@XmlElement(name = "DatosSueldos", required = false)
		protected XMLRequestEntity.DATOSADICIONALES.DatosSueldos datosSueldos;

		/**
		 * Gets the datos sueldos.
		 *
		 * @return the datos sueldos
		 */
		public XMLRequestEntity.DATOSADICIONALES.DatosSueldos getDatosSueldos() {
			return datosSueldos;
		}

		/**
		 * Sets the datos sueldos.
		 *
		 * @param datosSueldos
		 *            the new datos sueldos
		 */
		public void setDatosSueldos(XMLRequestEntity.DATOSADICIONALES.DatosSueldos datosSueldos) {
			this.datosSueldos = datosSueldos;
		}

		/** The parametros. */
		@XmlElement(name = "DatosMensAvisos", required = false)
		protected XMLRequestEntity.DATOSADICIONALES.DatosMensAvisos datosMensAvisos;

		/**
		 * Gets the datos mens avisos.
		 *
		 * @return the datosMensAvisos
		 */
		public XMLRequestEntity.DATOSADICIONALES.DatosMensAvisos getDatosMensAvisos() {
			return datosMensAvisos;
		}

		/**
		 * Sets the datos mens avisos.
		 *
		 * @param datosMensAvisos
		 *            the datosMensAvisos to set
		 */
		public void setDatosMensAvisos(XMLRequestEntity.DATOSADICIONALES.DatosMensAvisos datosMensAvisos) {
			this.datosMensAvisos = datosMensAvisos;
		}

		/**
		 * The Class DatosMensAvisos.
		 *
		 * @author B041299
		 */
		@XmlAccessorType(XmlAccessType.FIELD)
		@XmlType(name = "", propOrder = { "concepto", "TitularCredito", "descripcionAdicional3",
				"descripcionAdicional2", "descripcionAdicional1", "mailClienteReply", "conceptoAdicional3",
				"conceptoAdicional2", "infoAdicional", "conceptoAdicional1", "anotacionesPersonales", "comentario",
				"mailCredito", "titularDebito" })
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
		 * The Class DatosSueldos.
		 */
		@XmlAccessorType(XmlAccessType.FIELD)
		@XmlType(name = "", propOrder = { "descripcionConcepto", "destinatario", "tipoPago", "numeroCUIL", "tipoCUIL" })
		public static class DatosSueldos {

			/** The descripcion concepto. */
			@XmlElement(name = "DescripcionConcepto", required = true)
			protected String descripcionConcepto;

			/** The destinatario. */
			@XmlElement(name = "Destinatario", required = true)
			protected String destinatario;

			/** The tipo pago. */
			@XmlElement(name = "TipoPago", required = true)
			protected String tipoPago;

			/** The numero CUIL. */
			@XmlElement(name = "NumeroCUIL", required = true)
			protected String numeroCUIL;

			/** The tipo CUIL. */
			@XmlElement(name = "TipoCUIL", required = true)
			protected String tipoCUIL;

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
			 * Gets the destinatario.
			 *
			 * @return the destinatario
			 */
			public String getDestinatario() {
				return destinatario;
			}

			/**
			 * Sets the destinatario.
			 *
			 * @param destinatario
			 *            the new destinatario
			 */
			public void setDestinatario(String destinatario) {
				this.destinatario = destinatario;
			}

			/**
			 * Gets the tipo pago.
			 *
			 * @return the tipo pago
			 */
			public String getTipoPago() {
				return tipoPago;
			}

			/**
			 * Sets the tipo pago.
			 *
			 * @param tipoPago
			 *            the new tipo pago
			 */
			public void setTipoPago(String tipoPago) {
				this.tipoPago = tipoPago;
			}

			/**
			 * Gets the numero CUIL.
			 *
			 * @return the numero CUIL
			 */
			public String getNumeroCUIL() {
				return numeroCUIL;
			}

			/**
			 * Sets the numero CUIL.
			 *
			 * @param numeroCUIL
			 *            the new numero CUIL
			 */
			public void setNumeroCUIL(String numeroCUIL) {
				this.numeroCUIL = numeroCUIL;
			}

			/**
			 * Gets the tipo CUIL.
			 *
			 * @return the tipo CUIL
			 */
			public String getTipoCUIL() {
				return tipoCUIL;
			}

			/**
			 * Sets the tipo CUIL.
			 *
			 * @param tipoCUIL
			 *            the new tipo CUIL
			 */
			public void setTipoCUIL(String tipoCUIL) {
				this.tipoCUIL = tipoCUIL;
			}
		}

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
	 *         &lt;element name="Nombre" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="Version" type="{http://www.w3.org/2001/XMLSchema}byte"/>
	 *         &lt;element name="Cliente">
	 *           &lt;complexType>
	 *             &lt;complexContent>
	 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
	 *                 &lt;sequence>
	 *                   &lt;element name="TipoPersona" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *                   &lt;element name="TipoId" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *                   &lt;element name="IdCliente" type="{http://www.w3.org/2001/XMLSchema}int"/>
	 *                   &lt;element name="FechaNac" type="{http://www.w3.org/2001/XMLSchema}int"/>
	 *                   &lt;element name="NUP" type="{http://www.w3.org/2001/XMLSchema}int"/>
	 *                 &lt;/sequence>
	 *               &lt;/restriction>
	 *             &lt;/complexContent>
	 *           &lt;/complexType>
	 *         &lt;/element>
	 *         &lt;element name="Usuario">
	 *           &lt;complexType>
	 *             &lt;complexContent>
	 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
	 *                 &lt;sequence>
	 *                   &lt;element name="UsuarioTipo" type="{http://www.w3.org/2001/XMLSchema}byte"/>
	 *                   &lt;element name="UsuarioId" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *                   &lt;element name="UsuarioPwd" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *                   &lt;element name="UsuarioAtrib" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *                 &lt;/sequence>
	 *               &lt;/restriction>
	 *             &lt;/complexContent>
	 *           &lt;/complexType>
	 *         &lt;/element>
	 *         &lt;element name="IndAuten" type="{http://www.w3.org/2001/XMLSchema}byte"/>
	 *         &lt;element name="IdSesionCnt" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="Canal">
	 *           &lt;complexType>
	 *             &lt;complexContent>
	 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
	 *                 &lt;sequence>
	 *                   &lt;element name="CanalTipo" type="{http://www.w3.org/2001/XMLSchema}byte"/>
	 *                   &lt;element name="CanalId" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *                   &lt;element name="CanalVersion" type="{http://www.w3.org/2001/XMLSchema}byte"/>
	 *                 &lt;/sequence>
	 *               &lt;/restriction>
	 *             &lt;/complexContent>
	 *           &lt;/complexType>
	 *         &lt;/element>
	 *         &lt;element name="Subcanal">
	 *           &lt;complexType>
	 *             &lt;complexContent>
	 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
	 *                 &lt;sequence>
	 *                   &lt;element name="SubcanalTipo" type="{http://www.w3.org/2001/XMLSchema}byte"/>
	 *                   &lt;element name="SubcanalId" type="{http://www.w3.org/2001/XMLSchema}byte"/>
	 *                 &lt;/sequence>
	 *               &lt;/restriction>
	 *             &lt;/complexContent>
	 *           &lt;/complexType>
	 *         &lt;/element>
	 *         &lt;element name="ModoEjecucion" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *       &lt;/sequence>
	 *     &lt;/restriction>
	 *   &lt;/complexContent>
	 * &lt;/complexType>
	 * </pre>
	 * 
	 * 
	 */
	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType(name = "", propOrder = { "nombre", "version", "cliente", "usuario", "indAuten", "idSesionCnt", "canal",
			"subcanal", "recurrencias", "modoEjecucion", "idTransaccion", "nroRecurrencia", "tipoAgendamiento",
			"fechaEjecucion", "logueoAgendaHistorica" })
	public static class META {

		/** The nombre. */
		@XmlElement(name = "Nombre", required = true)
		protected String nombre;

		/** The version. */
		@XmlElement(name = "Version")
		protected String version;

		/** The cliente. */
		@XmlElement(name = "Cliente", required = true)
		protected XMLRequestEntity.META.Cliente cliente;

		/** The usuario. */
		@XmlElement(name = "Usuario", required = true)
		protected XMLRequestEntity.META.Usuario usuario;

		/** The ind auten. */
		@XmlElement(name = "IndAuten")
		protected String indAuten;

		/** The id sesion cnt. */
		@XmlElement(name = "IdSesionCnt", required = true)
		protected String idSesionCnt;

		/** The canal. */
		@XmlElement(name = "Canal", required = true)
		protected XMLRequestEntity.META.Canal canal;

		/** The subcanal. */
		@XmlElement(name = "Subcanal", required = true)
		protected XMLRequestEntity.META.Subcanal subcanal;

		/** The modo ejecucion. */
		@XmlElement(name = "ModoEjecucion", required = true)
		protected String modoEjecucion;

		/** The id transaccion. */
		@XmlElement(name = "IdTransaccion")
		protected String idTransaccion;

		/** The nro recurrencia. */
		@XmlElement(name = "NroRecurrencia")
		protected String nroRecurrencia;

		/** The recurrencias. */
		@XmlElement(name = "Recurrencias")
		protected XMLRequestEntity.META.Recurrencias recurrencias;

		/** The tipo agendamiento. */
		@XmlElement(name = "TipoAgendamiento")
		protected String tipoAgendamiento;

		/** The fecha ejecucion. */
		@XmlElement(name = "FechaEjecucion")
		protected String fechaEjecucion;

		/** The logueo agenda historica. */
		@XmlElement(name = "LogueoAgendaHistorica")
		protected String logueoAgendaHistorica;

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
		 * Gets the value of the cliente property.
		 * 
		 * @return possible object is {@link XMLRequestEntity.META.Cliente }
		 * 
		 */
		public XMLRequestEntity.META.Cliente getCliente() {
			return cliente;
		}

		/**
		 * Sets the value of the cliente property.
		 * 
		 * @param value
		 *            allowed object is {@link XMLRequestEntity.META.Cliente }
		 * 
		 */
		public void setCliente(XMLRequestEntity.META.Cliente value) {
			this.cliente = value;
		}

		/**
		 * Gets the value of the usuario property.
		 * 
		 * @return possible object is {@link XMLRequestEntity.META.Usuario }
		 * 
		 */
		public XMLRequestEntity.META.Usuario getUsuario() {
			return usuario;
		}

		/**
		 * Sets the value of the usuario property.
		 * 
		 * @param value
		 *            allowed object is {@link XMLRequestEntity.META.Usuario }
		 * 
		 */
		public void setUsuario(XMLRequestEntity.META.Usuario value) {
			this.usuario = value;
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
		 * Gets the value of the canal property.
		 * 
		 * @return possible object is {@link XMLRequestEntity.META.Canal }
		 * 
		 */
		public XMLRequestEntity.META.Canal getCanal() {
			return canal;
		}

		/**
		 * Sets the value of the canal property.
		 * 
		 * @param value
		 *            allowed object is {@link XMLRequestEntity.META.Canal }
		 * 
		 */
		public void setCanal(XMLRequestEntity.META.Canal value) {
			this.canal = value;
		}

		/**
		 * Gets the value of the subcanal property.
		 * 
		 * @return possible object is {@link XMLRequestEntity.META.Subcanal }
		 * 
		 */
		public XMLRequestEntity.META.Subcanal getSubcanal() {
			return subcanal;
		}

		/**
		 * Sets the value of the subcanal property.
		 * 
		 * @param value
		 *            allowed object is {@link XMLRequestEntity.META.Subcanal }
		 * 
		 */
		public void setSubcanal(XMLRequestEntity.META.Subcanal value) {
			this.subcanal = value;
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
		 * Gets the id transaccion.
		 *
		 * @return the id transaccion
		 */
		public String getIdTransaccion() {
			return idTransaccion;
		}

		/**
		 * Sets the id transaccion.
		 *
		 * @param idTransaccion
		 *            the new id transaccion
		 */
		public void setIdTransaccion(String idTransaccion) {
			this.idTransaccion = idTransaccion;
		}

		/**
		 * Gets the nro recurrencia.
		 *
		 * @return the nro recurrencia
		 */
		public String getNroRecurrencia() {
			return nroRecurrencia;
		}

		/**
		 * Sets the nro recurrencia.
		 *
		 * @param nroRecurrencia
		 *            the new nro recurrencia
		 */
		public void setNroRecurrencia(String nroRecurrencia) {
			this.nroRecurrencia = nroRecurrencia;
		}

		/**
		 * Gets the recurrencias.
		 *
		 * @return the recurrencias
		 */
		public XMLRequestEntity.META.Recurrencias getRecurrencias() {
			return recurrencias;
		}

		/**
		 * Sets the recurrencias.
		 *
		 * @param recurrencias
		 *            the new recurrencias
		 */
		public void setRecurrencias(XMLRequestEntity.META.Recurrencias recurrencias) {
			this.recurrencias = recurrencias;
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
		 *         &lt;element name="CanalTipo" type="{http://www.w3.org/2001/XMLSchema}byte"/>
		 *         &lt;element name="CanalId" type="{http://www.w3.org/2001/XMLSchema}string"/>
		 *         &lt;element name="CanalVersion" type="{http://www.w3.org/2001/XMLSchema}byte"/>
		 *       &lt;/sequence>
		 *     &lt;/restriction>
		 *   &lt;/complexContent>
		 * &lt;/complexType>
		 * </pre>
		 * 
		 * 
		 */
		@XmlAccessorType(XmlAccessType.FIELD)
		@XmlType(name = "", propOrder = { "canalTipo", "canalId", "canalVersion" })
		public static class Canal {

			/** The canal tipo. */
			@XmlElement(name = "CanalTipo")
			protected String canalTipo;

			/** The canal id. */
			@XmlElement(name = "CanalId", required = true)
			protected String canalId;

			/** The canal version. */
			@XmlElement(name = "CanalVersion")
			protected String canalVersion;

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
		 *         &lt;element name="TipoPersona" type="{http://www.w3.org/2001/XMLSchema}string"/>
		 *         &lt;element name="TipoId" type="{http://www.w3.org/2001/XMLSchema}string"/>
		 *         &lt;element name="IdCliente" type="{http://www.w3.org/2001/XMLSchema}int"/>
		 *         &lt;element name="FechaNac" type="{http://www.w3.org/2001/XMLSchema}int"/>
		 *         &lt;element name="NUP" type="{http://www.w3.org/2001/XMLSchema}int"/>
		 *       &lt;/sequence>
		 *     &lt;/restriction>
		 *   &lt;/complexContent>
		 * &lt;/complexType>
		 * </pre>
		 * 
		 * 
		 */
		@XmlAccessorType(XmlAccessType.FIELD)
		@XmlType(name = "", propOrder = { "tipoPersona", "tipoId", "idCliente", "fechaNac", "nup" })
		public static class Cliente {

			/** The tipo persona. */
			@XmlElement(name = "TipoPersona", required = true)
			protected String tipoPersona;

			/** The tipo id. */
			@XmlElement(name = "TipoId", required = true)
			protected String tipoId;

			/** The id cliente. */
			@XmlElement(name = "IdCliente")
			protected String idCliente;

			/** The fecha nac. */
			@XmlElement(name = "FechaNac")
			protected String fechaNac;

			/** The nup. */
			@XmlElement(name = "NUP")
			protected String nup;

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
			 * Gets the value of the nup property.
			 *
			 * @return the nup
			 */
			public String getNUP() {
				return nup;
			}

			/**
			 * Sets the value of the nup property.
			 *
			 * @param value
			 *            the new nup
			 */
			public void setNUP(String value) {
				this.nup = value;
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
		 *         &lt;element name="SubcanalTipo" type="{http://www.w3.org/2001/XMLSchema}byte"/>
		 *         &lt;element name="SubcanalId" type="{http://www.w3.org/2001/XMLSchema}byte"/>
		 *       &lt;/sequence>
		 *     &lt;/restriction>
		 *   &lt;/complexContent>
		 * &lt;/complexType>
		 * </pre>
		 * 
		 * 
		 */
		@XmlAccessorType(XmlAccessType.FIELD)
		@XmlType(name = "", propOrder = { "subcanalTipo", "subcanalId" })
		public static class Subcanal {

			/** The subcanal tipo. */
			@XmlElement(name = "SubcanalTipo")
			protected String subcanalTipo;

			/** The subcanal id. */
			@XmlElement(name = "SubcanalId")
			protected String subcanalId;

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
		 *         &lt;element name="UsuarioTipo" type="{http://www.w3.org/2001/XMLSchema}byte"/>
		 *         &lt;element name="UsuarioId" type="{http://www.w3.org/2001/XMLSchema}string"/>
		 *         &lt;element name="UsuarioPwd" type="{http://www.w3.org/2001/XMLSchema}string"/>
		 *         &lt;element name="UsuarioAtrib" type="{http://www.w3.org/2001/XMLSchema}string"/>
		 *       &lt;/sequence>
		 *     &lt;/restriction>
		 *   &lt;/complexContent>
		 * &lt;/complexType>
		 * </pre>
		 * 
		 * 
		 */
		@XmlAccessorType(XmlAccessType.FIELD)
		@XmlType(name = "", propOrder = { "usuarioTipo", "usuarioId", "usuarioPwd", "usuarioAtrib" })
		public static class Usuario {

			/** The usuario tipo. */
			@XmlElement(name = "UsuarioTipo")
			protected String usuarioTipo;

			/** The usuario id. */
			@XmlElement(name = "UsuarioId", required = true)
			protected String usuarioId;

			/** The usuario pwd. */
			@XmlElement(name = "UsuarioPwd", required = true)
			protected String usuarioPwd;

			/** The usuario atrib. */
			@XmlElement(name = "UsuarioAtrib", required = true)
			protected String usuarioAtrib;

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

		}

		/**
		 * The Class Recurrencias.
		 */
		@XmlAccessorType(XmlAccessType.FIELD)
		@XmlType(name = "", propOrder = { "tipoRecurrencia", "fechaBaseRecurrencia", "frecRecurrencia",
				"maxRecurrencia" })
		public static class Recurrencias {

			/** The tipo recurrencia. */
			@XmlElement(name = "TipoRecurrencia")
			protected String tipoRecurrencia;

			/** The fecha base recurrencia. */
			@XmlElement(name = "FechaBaseRecurrencia")
			protected String fechaBaseRecurrencia;

			/** The frec recurrencia. */
			@XmlElement(name = "FrecRecurrencia")
			protected String frecRecurrencia;

			/** The max recurrencia. */
			@XmlElement(name = "MaxRecurrencia")
			protected String maxRecurrencia;

			/**
			 * Gets the tipo recurrencia.
			 *
			 * @return the tipo recurrencia
			 */
			public String getTipoRecurrencia() {
				return tipoRecurrencia;
			}

			/**
			 * Sets the tipo recurrencia.
			 *
			 * @param tipoRecurrencia
			 *            the new tipo recurrencia
			 */
			public void setTipoRecurrencia(String tipoRecurrencia) {
				this.tipoRecurrencia = tipoRecurrencia;
			}

			/**
			 * Gets the fecha base recurrencia.
			 *
			 * @return the fecha base recurrencia
			 */
			public String getFechaBaseRecurrencia() {
				return fechaBaseRecurrencia;
			}

			/**
			 * Sets the fecha base recurrencia.
			 *
			 * @param fechaBaseRecurrencia
			 *            the new fecha base recurrencia
			 */
			public void setFechaBaseRecurrencia(String fechaBaseRecurrencia) {
				this.fechaBaseRecurrencia = fechaBaseRecurrencia;
			}

			/**
			 * Gets the frec recurrencia.
			 *
			 * @return the frec recurrencia
			 */
			public String getFrecRecurrencia() {
				return frecRecurrencia;
			}

			/**
			 * Sets the frec recurrencia.
			 *
			 * @param frecRecurrencia
			 *            the new frec recurrencia
			 */
			public void setFrecRecurrencia(String frecRecurrencia) {
				this.frecRecurrencia = frecRecurrencia;
			}

			/**
			 * Gets the max recurrencia.
			 *
			 * @return the max recurrencia
			 */
			public String getMaxRecurrencia() {
				return maxRecurrencia;
			}

			/**
			 * Sets the max recurrencia.
			 *
			 * @param maxRecurrencia
			 *            the new max recurrencia
			 */
			public void setMaxRecurrencia(String maxRecurrencia) {
				this.maxRecurrencia = maxRecurrencia;
			}

		}

		/**
		 * Gets the tipo agendamiento.
		 *
		 * @return the tipo agendamiento
		 */
		public String getTipoAgendamiento() {
			return tipoAgendamiento;
		}

		/**
		 * Sets the tipo agendamiento.
		 *
		 * @param tipoAgendamiento
		 *            the new tipo agendamiento
		 */
		public void setTipoAgendamiento(String tipoAgendamiento) {
			this.tipoAgendamiento = tipoAgendamiento;
		}

		/**
		 * Gets the fecha ejecucion.
		 *
		 * @return the fecha ejecucion
		 */
		public String getFechaEjecucion() {
			return fechaEjecucion;
		}

		/**
		 * Sets the fecha ejecucion.
		 *
		 * @param fechaEjecucion
		 *            the new fecha ejecucion
		 */
		public void setFechaEjecucion(String fechaEjecucion) {
			this.fechaEjecucion = fechaEjecucion;
		}

		/**
		 * Gets the logueo agenda historica.
		 *
		 * @return the logueo agenda historica
		 */
		public String getLogueoAgendaHistorica() {
			return logueoAgendaHistorica;
		}

		/**
		 * Sets the logueo agenda historica.
		 *
		 * @param logueoAgendaHistorica
		 *            the new logueo agenda historica
		 */
		public void setLogueoAgendaHistorica(String logueoAgendaHistorica) {
			this.logueoAgendaHistorica = logueoAgendaHistorica;
		}
	}

}
