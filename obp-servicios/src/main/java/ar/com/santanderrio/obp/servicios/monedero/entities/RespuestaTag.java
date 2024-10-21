/*
 * 
 */

package ar.com.santanderrio.obp.servicios.monedero.entities;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;

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
 *         &lt;element name="Resultado" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Paginacion">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="TotalReg" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="Datos">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Dato" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="ObtenerTagsDataResponse">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="ID_Tag" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                                       &lt;element name="Tag" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *                                       &lt;element name="ID_Banco" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *                                       &lt;element name="Apellido" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                       &lt;element name="Nombre" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                       &lt;element name="ClteLimiteMensualRecarga" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *                                       &lt;element name="ClteModulorecarga" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *                                       &lt;element name="ClteCodTipoFrecuencia" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                       &lt;element name="BcoLimiteRecargaMensualTAG" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *                                       &lt;element name="ClteCantFrecuencia" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *                                       &lt;element name="Saldo" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *                                       &lt;element name="FechaSaldo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                       &lt;element name="LimiteDisponible" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *                                       &lt;element name="MedioDeRecarga">
 *                                         &lt;complexType>
 *                                           &lt;complexContent>
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                               &lt;sequence>
 *                                                 &lt;element name="Tipo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                                 &lt;element name="ID_CuentaVitual" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                                 &lt;element name="ID_Tarjeta" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                                 &lt;element name="ID_MarcaTarjeta" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                                 &lt;element name="Ult4DigitosTarjetas" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                               &lt;/sequence>
 *                                             &lt;/restriction>
 *                                           &lt;/complexContent>
 *                                         &lt;/complexType>
 *                                       &lt;/element>
 *                                       &lt;element name="Categoria" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *                                       &lt;element name="Estado" type="{http://www.w3.org/2001/XMLSchema}byte"/>
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
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "resultado", "paginacion", "datos" })
@XmlRootElement(name = "Respuesta")
public class RespuestaTag {

	/** The resultado. */
	@XmlElement(name = "Resultado", required = true)
	protected String resultado;

	/** The paginacion. */
	@XmlElement(name = "Paginacion", required = true)
	protected RespuestaTag.Paginacion paginacion;

	/** The datos. */
	@XmlElement(name = "Datos", required = true)
	protected RespuestaTag.Datos datos;

	/**
	 * Gets the value of the resultado property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getResultado() {
		return resultado;
	}

	/**
	 * Sets the value of the resultado property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setResultado(String value) {
		this.resultado = value;
	}

	/**
	 * Gets the value of the paginacion property.
	 * 
	 * @return possible object is {@link Respuesta.Paginacion }
	 * 
	 */
	public RespuestaTag.Paginacion getPaginacion() {
		return paginacion;
	}

	/**
	 * Sets the value of the paginacion property.
	 * 
	 * @param value
	 *            allowed object is {@link Respuesta.Paginacion }
	 * 
	 */
	public void setPaginacion(RespuestaTag.Paginacion value) {
		this.paginacion = value;
	}

	/**
	 * Gets the value of the datos property.
	 * 
	 * @return possible object is {@link Respuesta.Datos }
	 * 
	 */
	public RespuestaTag.Datos getDatos() {
		return datos;
	}

	/**
	 * Sets the value of the datos property.
	 * 
	 * @param value
	 *            allowed object is {@link Respuesta.Datos }
	 * 
	 */
	public void setDatos(RespuestaTag.Datos value) {
		this.datos = value;
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
	 *         &lt;element name="Dato" maxOccurs="unbounded" minOccurs="0">
	 *           &lt;complexType>
	 *             &lt;complexContent>
	 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
	 *                 &lt;sequence>
	 *                   &lt;element name="ObtenerTagsDataResponse">
	 *                     &lt;complexType>
	 *                       &lt;complexContent>
	 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
	 *                           &lt;sequence>
	 *                             &lt;element name="ID_Tag" type="{http://www.w3.org/2001/XMLSchema}int"/>
	 *                             &lt;element name="Tag" type="{http://www.w3.org/2001/XMLSchema}long"/>
	 *                             &lt;element name="ID_Banco" type="{http://www.w3.org/2001/XMLSchema}short"/>
	 *                             &lt;element name="Apellido" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *                             &lt;element name="Nombre" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *                             &lt;element name="ClteLimiteMensualRecarga" type="{http://www.w3.org/2001/XMLSchema}float"/>
	 *                             &lt;element name="ClteModulorecarga" type="{http://www.w3.org/2001/XMLSchema}float"/>
	 *                             &lt;element name="ClteCodTipoFrecuencia" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *                             &lt;element name="BcoLimiteRecargaMensualTAG" type="{http://www.w3.org/2001/XMLSchema}float"/>
	 *                             &lt;element name="ClteCantFrecuencia" type="{http://www.w3.org/2001/XMLSchema}byte"/>
	 *                             &lt;element name="Saldo" type="{http://www.w3.org/2001/XMLSchema}float"/>
	 *                             &lt;element name="FechaSaldo" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *                             &lt;element name="LimiteDisponible" type="{http://www.w3.org/2001/XMLSchema}float"/>
	 *                             &lt;element name="MedioDeRecarga">
	 *                               &lt;complexType>
	 *                                 &lt;complexContent>
	 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
	 *                                     &lt;sequence>
	 *                                       &lt;element name="Tipo" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *                                       &lt;element name="ID_CuentaVitual" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *                                       &lt;element name="ID_Tarjeta" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *                                       &lt;element name="ID_MarcaTarjeta" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *                                       &lt;element name="Ult4DigitosTarjetas" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *                                     &lt;/sequence>
	 *                                   &lt;/restriction>
	 *                                 &lt;/complexContent>
	 *                               &lt;/complexType>
	 *                             &lt;/element>
	 *                             &lt;element name="Categoria" type="{http://www.w3.org/2001/XMLSchema}byte"/>
	 *                             &lt;element name="Estado" type="{http://www.w3.org/2001/XMLSchema}byte"/>
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
	@XmlType(name = "", propOrder = { "dato" })
	public static class Datos {

		/** The dato. */
		@XmlElement(name = "Dato")
		protected List<RespuestaTag.Datos.Dato> dato;

		/**
		 * Gets the value of the dato property.
		 * 
		 * <p>
		 * This accessor method returns a reference to the live list, not a
		 * snapshot. Therefore any modification you make to the returned list
		 * will be present inside the JAXB object. This is why there is not a
		 * <CODE>set</CODE> method for the dato property.
		 * 
		 * <p>
		 * For example, to add a new item, do as follows:
		 * 
		 * <pre>
		 * getDato().add(newItem);
		 * </pre>
		 * 
		 * 
		 * <p>
		 * Objects of the following type(s) are allowed in the list
		 * {@link Respuesta.Datos.Dato }
		 *
		 * @return the dato
		 */
		public List<RespuestaTag.Datos.Dato> getDato() {
			if (dato == null) {
				dato = new ArrayList<RespuestaTag.Datos.Dato>();
			}
			return this.dato;
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
		 *         &lt;element name="ObtenerTagsDataResponse">
		 *           &lt;complexType>
		 *             &lt;complexContent>
		 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
		 *                 &lt;sequence>
		 *                   &lt;element name="ID_Tag" type="{http://www.w3.org/2001/XMLSchema}int"/>
		 *                   &lt;element name="Tag" type="{http://www.w3.org/2001/XMLSchema}long"/>
		 *                   &lt;element name="ID_Banco" type="{http://www.w3.org/2001/XMLSchema}short"/>
		 *                   &lt;element name="Apellido" type="{http://www.w3.org/2001/XMLSchema}string"/>
		 *                   &lt;element name="Nombre" type="{http://www.w3.org/2001/XMLSchema}string"/>
		 *                   &lt;element name="ClteLimiteMensualRecarga" type="{http://www.w3.org/2001/XMLSchema}float"/>
		 *                   &lt;element name="ClteModulorecarga" type="{http://www.w3.org/2001/XMLSchema}float"/>
		 *                   &lt;element name="ClteCodTipoFrecuencia" type="{http://www.w3.org/2001/XMLSchema}string"/>
		 *                   &lt;element name="BcoLimiteRecargaMensualTAG" type="{http://www.w3.org/2001/XMLSchema}float"/>
		 *                   &lt;element name="ClteCantFrecuencia" type="{http://www.w3.org/2001/XMLSchema}byte"/>
		 *                   &lt;element name="Saldo" type="{http://www.w3.org/2001/XMLSchema}float"/>
		 *                   &lt;element name="FechaSaldo" type="{http://www.w3.org/2001/XMLSchema}string"/>
		 *                   &lt;element name="LimiteDisponible" type="{http://www.w3.org/2001/XMLSchema}float"/>
		 *                   &lt;element name="MedioDeRecarga">
		 *                     &lt;complexType>
		 *                       &lt;complexContent>
		 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
		 *                           &lt;sequence>
		 *                             &lt;element name="Tipo" type="{http://www.w3.org/2001/XMLSchema}string"/>
		 *                             &lt;element name="ID_CuentaVitual" type="{http://www.w3.org/2001/XMLSchema}string"/>
		 *                             &lt;element name="ID_Tarjeta" type="{http://www.w3.org/2001/XMLSchema}string"/>
		 *                             &lt;element name="ID_MarcaTarjeta" type="{http://www.w3.org/2001/XMLSchema}string"/>
		 *                             &lt;element name="Ult4DigitosTarjetas" type="{http://www.w3.org/2001/XMLSchema}string"/>
		 *                           &lt;/sequence>
		 *                         &lt;/restriction>
		 *                       &lt;/complexContent>
		 *                     &lt;/complexType>
		 *                   &lt;/element>
		 *                   &lt;element name="Categoria" type="{http://www.w3.org/2001/XMLSchema}byte"/>
		 *                   &lt;element name="Estado" type="{http://www.w3.org/2001/XMLSchema}byte"/>
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
		@XmlType(name = "", propOrder = { "obtenerTagsDataResponse" })
		public static class Dato {

			/** The obtener tags data response. */
			@XmlElement(name = "ObtenerTagsDataResponse", required = true)
			protected RespuestaTag.Datos.Dato.ObtenerTagsDataResponse obtenerTagsDataResponse;

			/**
			 * Gets the value of the obtenerTagsDataResponse property.
			 * 
			 * @return possible object is
			 *         {@link Respuesta.Datos.Dato.ObtenerTagsDataResponse }
			 * 
			 */
			public RespuestaTag.Datos.Dato.ObtenerTagsDataResponse getObtenerTagsDataResponse() {
				return obtenerTagsDataResponse;
			}

			/**
			 * Sets the value of the obtenerTagsDataResponse property.
			 * 
			 * @param value
			 *            allowed object is
			 *            {@link Respuesta.Datos.Dato.ObtenerTagsDataResponse }
			 * 
			 */
			public void setObtenerTagsDataResponse(RespuestaTag.Datos.Dato.ObtenerTagsDataResponse value) {
				this.obtenerTagsDataResponse = value;
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
			 *         &lt;element name="ID_Tag" type="{http://www.w3.org/2001/XMLSchema}int"/>
			 *         &lt;element name="Tag" type="{http://www.w3.org/2001/XMLSchema}long"/>
			 *         &lt;element name="ID_Banco" type="{http://www.w3.org/2001/XMLSchema}short"/>
			 *         &lt;element name="Apellido" type="{http://www.w3.org/2001/XMLSchema}string"/>
			 *         &lt;element name="Nombre" type="{http://www.w3.org/2001/XMLSchema}string"/>
			 *         &lt;element name="ClteLimiteMensualRecarga" type="{http://www.w3.org/2001/XMLSchema}float"/>
			 *         &lt;element name="ClteModulorecarga" type="{http://www.w3.org/2001/XMLSchema}float"/>
			 *         &lt;element name="ClteCodTipoFrecuencia" type="{http://www.w3.org/2001/XMLSchema}string"/>
			 *         &lt;element name="BcoLimiteRecargaMensualTAG" type="{http://www.w3.org/2001/XMLSchema}float"/>
			 *         &lt;element name="ClteCantFrecuencia" type="{http://www.w3.org/2001/XMLSchema}byte"/>
			 *         &lt;element name="Saldo" type="{http://www.w3.org/2001/XMLSchema}float"/>
			 *         &lt;element name="FechaSaldo" type="{http://www.w3.org/2001/XMLSchema}string"/>
			 *         &lt;element name="LimiteDisponible" type="{http://www.w3.org/2001/XMLSchema}float"/>
			 *         &lt;element name="MedioDeRecarga">
			 *           &lt;complexType>
			 *             &lt;complexContent>
			 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
			 *                 &lt;sequence>
			 *                   &lt;element name="Tipo" type="{http://www.w3.org/2001/XMLSchema}string"/>
			 *                   &lt;element name="ID_CuentaVitual" type="{http://www.w3.org/2001/XMLSchema}string"/>
			 *                   &lt;element name="ID_Tarjeta" type="{http://www.w3.org/2001/XMLSchema}string"/>
			 *                   &lt;element name="ID_MarcaTarjeta" type="{http://www.w3.org/2001/XMLSchema}string"/>
			 *                   &lt;element name="Ult4DigitosTarjetas" type="{http://www.w3.org/2001/XMLSchema}string"/>
			 *                 &lt;/sequence>
			 *               &lt;/restriction>
			 *             &lt;/complexContent>
			 *           &lt;/complexType>
			 *         &lt;/element>
			 *         &lt;element name="Categoria" type="{http://www.w3.org/2001/XMLSchema}byte"/>
			 *         &lt;element name="Estado" type="{http://www.w3.org/2001/XMLSchema}byte"/>
			 *       &lt;/sequence>
			 *     &lt;/restriction>
			 *   &lt;/complexContent>
			 * &lt;/complexType>
			 * </pre>
			 * 
			 * 
			 */
			@XmlAccessorType(XmlAccessType.FIELD)
			@XmlType(name = "", propOrder = { "idTag", "tag", "idBanco", "apellido", "nombre",
					"clteLimiteMensualRecarga", "clteModulorecarga", "clteCodTipoFrecuencia",
					"bcoLimiteRecargaMensualTAG", "clteCantFrecuencia", "saldo", "fechaSaldo", "limiteDisponible",
					"medioDeRecarga", "categoria", "estado" })
			public static class ObtenerTagsDataResponse {

				/** The id tag. */
				@XmlElement(name = "ID_Tag")
				protected int idTag;

				/** The tag. */
				@XmlElement(name = "Tag")
				protected long tag;

				/** The id banco. */
				@XmlElement(name = "ID_Banco")
				protected short idBanco;

				/** The apellido. */
				@XmlElement(name = "Apellido", required = true)
				protected String apellido;

				/** The nombre. */
				@XmlElement(name = "Nombre", required = true)
				protected String nombre;

				/** The clte limite mensual recarga. */
				@XmlElement(name = "ClteLimiteMensualRecarga")
				protected float clteLimiteMensualRecarga;

				/** The clte modulorecarga. */
				@XmlElement(name = "ClteModulorecarga")
				protected float clteModulorecarga;

				/** The clte cod tipo frecuencia. */
				@XmlElement(name = "ClteCodTipoFrecuencia", required = true)
				protected String clteCodTipoFrecuencia;

				/** The bco limite recarga mensual TAG. */
				@XmlElement(name = "BcoLimiteRecargaMensualTAG")
				protected float bcoLimiteRecargaMensualTAG;

				/** The clte cant frecuencia. */
				@XmlElement(name = "ClteCantFrecuencia")
				protected byte clteCantFrecuencia;

				/** The saldo. */
				@XmlElement(name = "Saldo")
				protected float saldo;

				/** The fecha saldo. */
				@XmlElement(name = "FechaSaldo", required = true)
				protected String fechaSaldo;

				/** The limite disponible. */
				@XmlElement(name = "LimiteDisponible")
				protected float limiteDisponible;

				/** The medio de recarga. */
				@XmlElement(name = "MedioDeRecarga", required = true)
				protected RespuestaTag.Datos.Dato.ObtenerTagsDataResponse.MedioDeRecarga medioDeRecarga;

				/** The categoria. */
				@XmlElement(name = "Categoria")
				protected byte categoria;

				/** The estado. */
				@XmlElement(name = "Estado")
				protected byte estado;

				/**
				 * Gets the value of the idTag property.
				 *
				 * @return the ID tag
				 */
				public int getIDTag() {
					return idTag;
				}

				/**
				 * Sets the value of the idTag property.
				 *
				 * @param value
				 *            the new ID tag
				 */
				public void setIDTag(int value) {
					this.idTag = value;
				}

				/**
				 * Gets the value of the tag property.
				 *
				 * @return the tag
				 */
				public long getTag() {
					return tag;
				}

				/**
				 * Sets the value of the tag property.
				 *
				 * @param value
				 *            the new tag
				 */
				public void setTag(long value) {
					this.tag = value;
				}

				/**
				 * Gets the value of the idBanco property.
				 *
				 * @return the ID banco
				 */
				public short getIDBanco() {
					return idBanco;
				}

				/**
				 * Sets the value of the idBanco property.
				 *
				 * @param value
				 *            the new ID banco
				 */
				public void setIDBanco(short value) {
					this.idBanco = value;
				}

				/**
				 * Gets the value of the apellido property.
				 * 
				 * @return possible object is {@link String }
				 * 
				 */
				public String getApellido() {
					return apellido;
				}

				/**
				 * Sets the value of the apellido property.
				 * 
				 * @param value
				 *            allowed object is {@link String }
				 * 
				 */
				public void setApellido(String value) {
					this.apellido = value;
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
				 * Gets the value of the clteLimiteMensualRecarga property.
				 *
				 * @return the clte limite mensual recarga
				 */
				public float getClteLimiteMensualRecarga() {
					return clteLimiteMensualRecarga;
				}

				/**
				 * Sets the value of the clteLimiteMensualRecarga property.
				 *
				 * @param value
				 *            the new clte limite mensual recarga
				 */
				public void setClteLimiteMensualRecarga(float value) {
					this.clteLimiteMensualRecarga = value;
				}

				/**
				 * Gets the value of the clteModulorecarga property.
				 *
				 * @return the clte modulorecarga
				 */
				public float getClteModulorecarga() {
					return clteModulorecarga;
				}

				/**
				 * Sets the value of the clteModulorecarga property.
				 *
				 * @param value
				 *            the new clte modulorecarga
				 */
				public void setClteModulorecarga(float value) {
					this.clteModulorecarga = value;
				}

				/**
				 * Gets the value of the clteCodTipoFrecuencia property.
				 * 
				 * @return possible object is {@link String }
				 * 
				 */
				public String getClteCodTipoFrecuencia() {
					return clteCodTipoFrecuencia;
				}

				/**
				 * Sets the value of the clteCodTipoFrecuencia property.
				 * 
				 * @param value
				 *            allowed object is {@link String }
				 * 
				 */
				public void setClteCodTipoFrecuencia(String value) {
					this.clteCodTipoFrecuencia = value;
				}

				/**
				 * Gets the value of the bcoLimiteRecargaMensualTAG property.
				 *
				 * @return the bco limite recarga mensual TAG
				 */
				public float getBcoLimiteRecargaMensualTAG() {
					return bcoLimiteRecargaMensualTAG;
				}

				/**
				 * Sets the value of the bcoLimiteRecargaMensualTAG property.
				 *
				 * @param value
				 *            the new bco limite recarga mensual TAG
				 */
				public void setBcoLimiteRecargaMensualTAG(float value) {
					this.bcoLimiteRecargaMensualTAG = value;
				}

				/**
				 * Gets the value of the clteCantFrecuencia property.
				 *
				 * @return the clte cant frecuencia
				 */
				public byte getClteCantFrecuencia() {
					return clteCantFrecuencia;
				}

				/**
				 * Sets the value of the clteCantFrecuencia property.
				 *
				 * @param value
				 *            the new clte cant frecuencia
				 */
				public void setClteCantFrecuencia(byte value) {
					this.clteCantFrecuencia = value;
				}

				/**
				 * Gets the value of the saldo property.
				 *
				 * @return the saldo
				 */
				public float getSaldo() {
					return saldo;
				}

				/**
				 * Sets the value of the saldo property.
				 *
				 * @param value
				 *            the new saldo
				 */
				public void setSaldo(float value) {
					this.saldo = value;
				}

				/**
				 * Gets the value of the fechaSaldo property.
				 * 
				 * @return possible object is {@link String }
				 * 
				 */
				public String getFechaSaldo() {
					return fechaSaldo;
				}

				/**
				 * Sets the value of the fechaSaldo property.
				 * 
				 * @param value
				 *            allowed object is {@link String }
				 * 
				 */
				public void setFechaSaldo(String value) {
					this.fechaSaldo = value;
				}

				/**
				 * Gets the value of the limiteDisponible property.
				 *
				 * @return the limite disponible
				 */
				public float getLimiteDisponible() {
					return limiteDisponible;
				}

				/**
				 * Sets the value of the limiteDisponible property.
				 *
				 * @param value
				 *            the new limite disponible
				 */
				public void setLimiteDisponible(float value) {
					this.limiteDisponible = value;
				}

				/**
				 * Gets the value of the medioDeRecarga property.
				 * 
				 * @return possible object is
				 *         {@link Respuesta.Datos.Dato.ObtenerTagsDataResponse.MedioDeRecarga }
				 * 
				 */
				public RespuestaTag.Datos.Dato.ObtenerTagsDataResponse.MedioDeRecarga getMedioDeRecarga() {
					return medioDeRecarga;
				}

				/**
				 * Sets the value of the medioDeRecarga property.
				 * 
				 * @param value
				 *            allowed object is
				 *            {@link Respuesta.Datos.Dato.ObtenerTagsDataResponse.MedioDeRecarga }
				 * 
				 */
				public void setMedioDeRecarga(RespuestaTag.Datos.Dato.ObtenerTagsDataResponse.MedioDeRecarga value) {
					this.medioDeRecarga = value;
				}

				/**
				 * Gets the value of the categoria property.
				 *
				 * @return the categoria
				 */
				public byte getCategoria() {
					return categoria;
				}

				/**
				 * Sets the value of the categoria property.
				 *
				 * @param value
				 *            the new categoria
				 */
				public void setCategoria(byte value) {
					this.categoria = value;
				}

				/**
				 * Gets the value of the estado property.
				 *
				 * @return the estado
				 */
				public byte getEstado() {
					return estado;
				}

				/**
				 * Sets the value of the estado property.
				 *
				 * @param value
				 *            the new estado
				 */
				public void setEstado(byte value) {
					this.estado = value;
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
				 *         &lt;element name="Tipo" type="{http://www.w3.org/2001/XMLSchema}string"/>
				 *         &lt;element name="ID_CuentaVitual" type="{http://www.w3.org/2001/XMLSchema}string"/>
				 *         &lt;element name="ID_Tarjeta" type="{http://www.w3.org/2001/XMLSchema}string"/>
				 *         &lt;element name="ID_MarcaTarjeta" type="{http://www.w3.org/2001/XMLSchema}string"/>
				 *         &lt;element name="Ult4DigitosTarjetas" type="{http://www.w3.org/2001/XMLSchema}string"/>
				 *       &lt;/sequence>
				 *     &lt;/restriction>
				 *   &lt;/complexContent>
				 * &lt;/complexType>
				 * </pre>
				 * 
				 * 
				 */
				@XmlAccessorType(XmlAccessType.FIELD)
				@XmlType(name = "", propOrder = { "tipo", "idCuentaVitual", "idTarjeta", "idMarcaTarjeta",
						"ult4DigitosTarjetas" })
				public static class MedioDeRecarga {

					/** The tipo. */
					@XmlElement(name = "Tipo", required = true)
					protected String tipo;

					/** The id cuenta vitual. */
					@XmlElement(name = "ID_CuentaVitual", required = true)
					protected String idCuentaVitual;

					/** The id tarjeta. */
					@XmlElement(name = "ID_Tarjeta", required = true)
					protected String idTarjeta;

					/** The id marca tarjeta. */
					@XmlElement(name = "ID_MarcaTarjeta", required = true)
					protected String idMarcaTarjeta;

					/** The ult 4 digitos tarjetas. */
					@XmlElement(name = "Ult4DigitosTarjetas", required = true)
					protected String ult4DigitosTarjetas;

					/**
					 * Gets the value of the tipo property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getTipo() {
						return tipo;
					}

					/**
					 * Sets the value of the tipo property.
					 * 
					 * @param value
					 *            allowed object is {@link String }
					 * 
					 */
					public void setTipo(String value) {
						this.tipo = value;
					}

					/**
					 * Gets the value of the idCuentaVitual property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getIDCuentaVitual() {
						return idCuentaVitual;
					}

					/**
					 * Sets the value of the idCuentaVitual property.
					 * 
					 * @param value
					 *            allowed object is {@link String }
					 * 
					 */
					public void setIDCuentaVitual(String value) {
						this.idCuentaVitual = value;
					}

					/**
					 * Gets the value of the idTarjeta property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getIDTarjeta() {
						return idTarjeta;
					}

					/**
					 * Sets the value of the idTarjeta property.
					 * 
					 * @param value
					 *            allowed object is {@link String }
					 * 
					 */
					public void setIDTarjeta(String value) {
						this.idTarjeta = value;
					}

					/**
					 * Gets the value of the idMarcaTarjeta property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getIDMarcaTarjeta() {
						return idMarcaTarjeta;
					}

					/**
					 * Sets the value of the idMarcaTarjeta property.
					 * 
					 * @param value
					 *            allowed object is {@link String }
					 * 
					 */
					public void setIDMarcaTarjeta(String value) {
						this.idMarcaTarjeta = value;
					}

					/**
					 * Gets the value of the ult4DigitosTarjetas property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getUlt4DigitosTarjetas() {
						return ult4DigitosTarjetas;
					}

					/**
					 * Sets the value of the ult4DigitosTarjetas property.
					 * 
					 * @param value
					 *            allowed object is {@link String }
					 * 
					 */
					public void setUlt4DigitosTarjetas(String value) {
						this.ult4DigitosTarjetas = value;
					}

				}

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
	 *         &lt;element name="TotalReg" type="{http://www.w3.org/2001/XMLSchema}byte"/>
	 *       &lt;/sequence>
	 *     &lt;/restriction>
	 *   &lt;/complexContent>
	 * &lt;/complexType>
	 * </pre>
	 * 
	 * 
	 */
	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType(name = "", propOrder = { "totalReg" })
	public static class Paginacion {

		/** The total reg. */
		@XmlElement(name = "TotalReg")
		protected byte totalReg;

		/**
		 * Gets the value of the totalReg property.
		 *
		 * @return the total reg
		 */
		public byte getTotalReg() {
			return totalReg;
		}

		/**
		 * Sets the value of the totalReg property.
		 *
		 * @param value
		 *            the new total reg
		 */
		public void setTotalReg(byte value) {
			this.totalReg = value;
		}

	}

}
