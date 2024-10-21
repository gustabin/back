/**
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.dao.entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * The Class DatosExtra. Clase que representa el tag
 * /tarjetas/tarjeta/liquidacion/datosExtra/
 * 
 * @author florencia.n.martinez
 *
 */
@XmlRootElement(name = "datosExtra")
@XmlAccessorType(XmlAccessType.FIELD)
public class DatosExtra {

	/** The String dni. */
	@XmlElement(name = "DNI")
	private String dni;

	/** The String calle. */
	@XmlElement(name = "calle")
	private String calle;

	/** The String cart. */
	@XmlElement(name = "cart")
	private String cart;

	/** The String cp. */
	@XmlElement(name = "cp")
	private String cp;

	/** The String cuit. */
	@XmlElement(name = "cuit")
	private String cuit;

	/** The String departamento. */
	@XmlElement(name = "departamento")
	private String departamento;

	/** The String dptoNumero. */
	@XmlElement(name = "dptoNumero")
	private String dptoNumero;

	/** The String grupo. */
	@XmlElement(name = "grupo")
	private String grupo;

	/** The String iva. */
	@XmlElement(name = "iva")
	private String iva;

	/** The String leyendaIva. */
	@XmlElement(name = "leyendaIVA")
	private String leyendaIva;

	/** The String localidad. */
	@XmlElement(name = "licalidad")
	private String localidad;

	/** The String documento. */
	@XmlElement(name = "documento")
	private String documento;

	/** The String numero. */
	@XmlElement(name = "numero")
	private String numero;

	/** The String numeroTarjeta. */
	@XmlElement(name = "numeroTarjeta")
	private String numeroTarjeta;

	/** The String piso. */
	@XmlElement(name = "piso")
	private String piso;

	/** The String puerta. */
	@XmlElement(name = "puerta")
	private String puerta;

	/** The String sucursal. */
	@XmlElement(name = "sucursal")
	private String sucursal;

	/**
	 * Gets the dni.
	 *
	 * @return the dni
	 */
	public String getDni() {
		return dni;
	}

	/**
	 * Sets the dni.
	 *
	 * @param dni
	 *            the dni to set
	 */
	public void setDni(String dni) {
		this.dni = dni;
	}

	/**
	 * Gets the calle.
	 *
	 * @return the calle
	 */
	public String getCalle() {
		return calle;
	}

	/**
	 * Sets the calle.
	 *
	 * @param calle
	 *            the calle to set
	 */
	public void setCalle(String calle) {
		this.calle = calle;
	}

	/**
	 * Gets the cart.
	 *
	 * @return the cart
	 */
	public String getCart() {
		return cart;
	}

	/**
	 * Sets the cart.
	 *
	 * @param cart
	 *            the cart to set
	 */
	public void setCart(String cart) {
		this.cart = cart;
	}

	/**
	 * Gets the cp.
	 *
	 * @return the cp
	 */
	public String getCp() {
		return cp;
	}

	/**
	 * Sets the cp.
	 *
	 * @param cp
	 *            the cp to set
	 */
	public void setCp(String cp) {
		this.cp = cp;
	}

	/**
	 * Gets the cuit.
	 *
	 * @return the cuit
	 */
	public String getCuit() {
		return cuit;
	}

	/**
	 * Sets the cuit.
	 *
	 * @param cuit
	 *            the cuit to set
	 */
	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

	/**
	 * Gets the departamento.
	 *
	 * @return the departamento
	 */
	public String getDepartamento() {
		return departamento;
	}

	/**
	 * Sets the departamento.
	 *
	 * @param departamento
	 *            the departamento to set
	 */
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	/**
	 * Gets the dpto numero.
	 *
	 * @return the dptoNumero
	 */
	public String getDptoNumero() {
		return dptoNumero;
	}

	/**
	 * Sets the dpto numero.
	 *
	 * @param dptoNumero
	 *            the dptoNumero to set
	 */
	public void setDptoNumero(String dptoNumero) {
		this.dptoNumero = dptoNumero;
	}

	/**
	 * Gets the grupo.
	 *
	 * @return the grupo
	 */
	public String getGrupo() {
		return grupo;
	}

	/**
	 * Sets the grupo.
	 *
	 * @param grupo
	 *            the grupo to set
	 */
	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	/**
	 * Gets the iva.
	 *
	 * @return the iva
	 */
	public String getIva() {
		return iva;
	}

	/**
	 * Sets the iva.
	 *
	 * @param iva
	 *            the iva to set
	 */
	public void setIva(String iva) {
		this.iva = iva;
	}

	/**
	 * Gets the leyenda iva.
	 *
	 * @return the leyendaIva
	 */
	public String getLeyendaIva() {
		return leyendaIva;
	}

	/**
	 * Sets the leyenda iva.
	 *
	 * @param leyendaIva
	 *            the leyendaIva to set
	 */
	public void setLeyendaIva(String leyendaIva) {
		this.leyendaIva = leyendaIva;
	}

	/**
	 * Gets the localidad.
	 *
	 * @return the localidad
	 */
	public String getLocalidad() {
		return localidad;
	}

	/**
	 * Sets the localidad.
	 *
	 * @param localidad
	 *            the localidad to set
	 */
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	/**
	 * Gets the documento.
	 *
	 * @return the documento
	 */
	public String getDocumento() {
		return documento;
	}

	/**
	 * Sets the documento.
	 *
	 * @param documento
	 *            the documento to set
	 */
	public void setDocumento(String documento) {
		this.documento = documento;
	}

	/**
	 * Gets the numero.
	 *
	 * @return the numero
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * Sets the numero.
	 *
	 * @param numero
	 *            the numero to set
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}

	/**
	 * Gets the numero tarjeta.
	 *
	 * @return the numeroTarjeta
	 */
	public String getNumeroTarjeta() {
		return numeroTarjeta;
	}

	/**
	 * Sets the numero tarjeta.
	 *
	 * @param numeroTarjeta
	 *            the numeroTarjeta to set
	 */
	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}

	/**
	 * Gets the piso.
	 *
	 * @return the piso
	 */
	public String getPiso() {
		return piso;
	}

	/**
	 * Sets the piso.
	 *
	 * @param piso
	 *            the piso to set
	 */
	public void setPiso(String piso) {
		this.piso = piso;
	}

	/**
	 * Gets the puerta.
	 *
	 * @return the puerta
	 */
	public String getPuerta() {
		return puerta;
	}

	/**
	 * Sets the puerta.
	 *
	 * @param puerta
	 *            the puerta to set
	 */
	public void setPuerta(String puerta) {
		this.puerta = puerta;
	}

	/**
	 * Gets the sucursal.
	 *
	 * @return the sucursal
	 */
	public String getSucursal() {
		return sucursal;
	}

	/**
	 * Sets the sucursal.
	 *
	 * @param sucursal
	 *            the sucursal to set
	 */
	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}

	/**
	 * HashCode.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(dni);
		hcb.append(calle);
		hcb.append(cart);
		hcb.append(cp);
		hcb.append(cuit);
		hcb.append(departamento);
		hcb.append(dptoNumero);
		hcb.append(grupo);
		hcb.append(iva);
		hcb.append(leyendaIva);
		hcb.append(localidad);
		hcb.append(documento);
		hcb.append(numero);
		hcb.append(numeroTarjeta);
		hcb.append(piso);
		hcb.append(puerta);
		hcb.append(sucursal);
		return hcb.toHashCode();
	}

	/**
	 * Equals.
	 *
	 * @param obj
	 *            the obj
	 * @return true, if successful
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		DatosExtra other = (DatosExtra) obj;

		EqualsBuilder eb = new EqualsBuilder();
		eb.append(dni, other.getDni());
		eb.append(calle, other.getCalle());
		eb.append(cart, other.getCart());
		eb.append(cp, other.getCp());
		eb.append(cuit, other.getCuit());
		eb.append(departamento, other.getDepartamento());
		eb.append(dptoNumero, other.getDptoNumero());
		eb.append(grupo, other.getGrupo());
		eb.append(iva, other.getIva());
		eb.append(leyendaIva, other.getLeyendaIva());
		eb.append(localidad, other.getLocalidad());
		eb.append(documento, other.getDocumento());
		eb.append(numero, other.getNumero());
		eb.append(numeroTarjeta, other.getNumeroTarjeta());
		eb.append(piso, other.getPiso());
		eb.append(puerta, other.getPuerta());
		eb.append(sucursal, other.getSucursal());
		return eb.isEquals();
	}

	/**
	 * ToString.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "DatosExtra [dni=" + dni + ", calle=" + calle + ", cart=" + cart + ", cp=" + cp + ", cuit=" + cuit
				+ ", departamento=" + departamento + ", dptoNumero=" + dptoNumero + ", grupo=" + grupo + ", iva=" + iva
				+ ", leyendaIva=" + leyendaIva + ", localidad=" + localidad + ", documento=" + documento + ", numero="
				+ numero + ", numeroTarjeta=" + numeroTarjeta + ", piso=" + piso + ", puerta=" + puerta + ", sucursal="
				+ sucursal + "]";
	}

}
