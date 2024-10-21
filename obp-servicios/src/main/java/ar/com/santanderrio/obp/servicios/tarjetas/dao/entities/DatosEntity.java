/**
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.dao.entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * The Class DatosEntity. Clase que representa el tag
 * /tarjetas/tarjeta/posicionConsolidada/datos/ o el tag
 * /tarjetas/tarjeta/liquidacion/datos/
 *
 * @author sergio.e.goldentair
 */
@XmlRootElement(name = "datos")
@XmlAccessorType(XmlAccessType.FIELD)
public class DatosEntity {

	/** The id. */
	@XmlAttribute(name = "id")
	private String id;

	/** The affinity group. */
	@XmlElement(name = "affinityGroup")
	private String affinityGroup;

	/** The apellido. */
	@XmlElement(name = "apellido")
	private String apellido;

	/** The categoria. */
	@XmlElement(name = "categoria")
	private String categoria;

	/** The cod tipo tarjeta. */
	@XmlElement(name = "codTipoTarjeta")
	private String codTipoTarjeta;

	/** The codigo sucursal. */
	@XmlElement(name = "codigoSucursal")
	private String codigoSucursal;

	/** The cuenta. */
	@XmlElement(name = "cuenta")
	private String cuenta;

	/** The fecha desde. */
	@XmlElement(name = "fechaDesde")
	private String fechaDesde;

	/** The fecha nacimiento. */
	@XmlElement(name = "fechaNacimiento")
	private String fechaNacimiento;

	/** The habiente. */
	@XmlElement(name = "habiente")
	private String habiente;

	/** The nombre. */
	@XmlElement(name = "nombre")
	private String nombre;

	/** The documento. */
	@XmlElement(name = "documento")
	private String documento;

	/** The producto. */
	@XmlElement(name = "producto")
	private String producto;

	/** The tarjeta activa. */
	@XmlElement(name = "tarjetaActiva")
	private String tarjetaActiva;

	/** The tarjeta produ. */
	@XmlElement(name = "tarjetaProdu")
	private String tarjetaProdu;

	/** The telefono. */
	@XmlElement(name = "telefono")
	private String telefono;

	/** The tipo documento. */
	@XmlElement(name = "tipoDocumento")
	private TipoDocumento tipoDocumento;

	/** The tipo tarjeta detalle. */
	@XmlElement(name = "tipoTarjetaDetalle")
	private String tipoTarjetaDetalle;

	/** The titular. */
	@XmlElement(name = "titular")
	private String titular;

	/** The vencimiento. */
	@XmlElement(name = "vencimiento")
	private String vencimiento;

	/** The website. */
	@XmlElement(name = "website")
	private String website;

	/** The email. */
	@XmlElement(name = "email")
	private Email email;

	/** The codigo producto. */
	@XmlElement(name = "codigoProducto")
	private String codigoProducto;

	/** The estado. */
	@XmlElement(name = "estado")
	private String estado;

	/** The logo. */
	@XmlElement(name = "logo")
	private String logo;

	/** The sucursal. */
	@XmlElement(name = "sucursal")
	private String sucursal;

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Gets the affinity group.
	 *
	 * @return the affinityGroup
	 */
	public String getAffinityGroup() {
		return affinityGroup;
	}

	/**
	 * Sets the affinity group.
	 *
	 * @param affinityGroup
	 *            the affinityGroup to set
	 */
	public void setAffinityGroup(String affinityGroup) {
		this.affinityGroup = affinityGroup;
	}

	/**
	 * Gets the apellido.
	 *
	 * @return the apellido
	 */
	public String getApellido() {
		return apellido;
	}

	/**
	 * Sets the apellido.
	 *
	 * @param apellido
	 *            the apellido to set
	 */
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	/**
	 * Gets the categoria.
	 *
	 * @return the categoria
	 */
	public String getCategoria() {
		return categoria;
	}

	/**
	 * Sets the categoria.
	 *
	 * @param categoria
	 *            the categoria to set
	 */
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	/**
	 * Gets the cod tipo tarjeta.
	 *
	 * @return the codTipoTarjeta
	 */
	public String getCodTipoTarjeta() {
		return codTipoTarjeta;
	}

	/**
	 * Sets the cod tipo tarjeta.
	 *
	 * @param codTipoTarjeta
	 *            the codTipoTarjeta to set
	 */
	public void setCodTipoTarjeta(String codTipoTarjeta) {
		this.codTipoTarjeta = codTipoTarjeta;
	}

	/**
	 * Gets the codigo sucursal.
	 *
	 * @return the codigoSucursal
	 */
	public String getCodigoSucursal() {
		return codigoSucursal;
	}

	/**
	 * Sets the codigo sucursal.
	 *
	 * @param codigoSucursal
	 *            the codigoSucursal to set
	 */
	public void setCodigoSucursal(String codigoSucursal) {
		this.codigoSucursal = codigoSucursal;
	}

	/**
	 * Gets the cuenta.
	 *
	 * @return the cuenta
	 */
	public String getCuenta() {
		return cuenta;
	}

	/**
	 * Sets the cuenta.
	 *
	 * @param cuenta
	 *            the cuenta to set
	 */
	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	/**
	 * Gets the fecha desde.
	 *
	 * @return the fechaDesde
	 */
	public String getFechaDesde() {
		return fechaDesde;
	}

	/**
	 * Sets the fecha desde.
	 *
	 * @param fechaDesde
	 *            the fechaDesde to set
	 */
	public void setFechaDesde(String fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	/**
	 * Gets the fecha nacimiento.
	 *
	 * @return the fechaNacimiento
	 */
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	/**
	 * Sets the fecha nacimiento.
	 *
	 * @param fechaNacimiento
	 *            the fechaNacimiento to set
	 */
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	/**
	 * Gets the habiente.
	 *
	 * @return the habiente
	 */
	public String getHabiente() {
		return habiente;
	}

	/**
	 * Sets the habiente.
	 *
	 * @param habiente
	 *            the habiente to set
	 */
	public void setHabiente(String habiente) {
		this.habiente = habiente;
	}

	/**
	 * Gets the nombre.
	 *
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Sets the nombre.
	 *
	 * @param nombre
	 *            the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
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
	 * Gets the producto.
	 *
	 * @return the producto
	 */
	public String getProducto() {
		return producto;
	}

	/**
	 * Sets the producto.
	 *
	 * @param producto
	 *            the producto to set
	 */
	public void setProducto(String producto) {
		this.producto = producto;
	}

	/**
	 * Gets the tarjeta activa.
	 *
	 * @return the tarjetaActiva
	 */
	public String getTarjetaActiva() {
		return tarjetaActiva;
	}

	/**
	 * Sets the tarjeta activa.
	 *
	 * @param tarjetaActiva
	 *            the tarjetaActiva to set
	 */
	public void setTarjetaActiva(String tarjetaActiva) {
		this.tarjetaActiva = tarjetaActiva;
	}

	/**
	 * Gets the tarjeta produ.
	 *
	 * @return the tarjetaProdu
	 */
	public String getTarjetaProdu() {
		return tarjetaProdu;
	}

	/**
	 * Sets the tarjeta produ.
	 *
	 * @param tarjetaProdu
	 *            the tarjetaProdu to set
	 */
	public void setTarjetaProdu(String tarjetaProdu) {
		this.tarjetaProdu = tarjetaProdu;
	}

	/**
	 * Gets the telefono.
	 *
	 * @return the telefono
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * Sets the telefono.
	 *
	 * @param telefono
	 *            the telefono to set
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	/**
	 * Gets the tipo documento.
	 *
	 * @return the tipoDocumento
	 */
	public TipoDocumento getTipoDocumento() {
		return tipoDocumento;
	}

	/**
	 * Sets the tipo documento.
	 *
	 * @param tipoDocumento
	 *            the tipoDocumento to set
	 */
	public void setTipoDocumento(TipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	/**
	 * Gets the tipo tarjeta detalle.
	 *
	 * @return the tipoTarjetaDetalle
	 */
	public String getTipoTarjetaDetalle() {
		return tipoTarjetaDetalle;
	}

	/**
	 * Sets the tipo tarjeta detalle.
	 *
	 * @param tipoTarjetaDetalle
	 *            the tipoTarjetaDetalle to set
	 */
	public void setTipoTarjetaDetalle(String tipoTarjetaDetalle) {
		this.tipoTarjetaDetalle = tipoTarjetaDetalle;
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
	 * Gets the vencimiento.
	 *
	 * @return the vencimiento
	 */
	public String getVencimiento() {
		return vencimiento;
	}

	/**
	 * Sets the vencimiento.
	 *
	 * @param vencimiento
	 *            the vencimiento to set
	 */
	public void setVencimiento(String vencimiento) {
		this.vencimiento = vencimiento;
	}

	/**
	 * Gets the website.
	 *
	 * @return the website
	 */
	public String getWebsite() {
		return website;
	}

	/**
	 * Sets the website.
	 *
	 * @param website
	 *            the website to set
	 */
	public void setWebsite(String website) {
		this.website = website;
	}

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public Email getEmail() {
		return email;
	}

	/**
	 * Sets the email.
	 *
	 * @param email
	 *            the email to set
	 */
	public void setEmail(Email email) {
		this.email = email;
	}

	/**
	 * Gets the codigo producto.
	 *
	 * @return the codigoProducto
	 */
	public String getCodigoProducto() {
		return codigoProducto;
	}

	/**
	 * Sets the codigo producto.
	 *
	 * @param codigoProducto
	 *            the codigoProducto to set
	 */
	public void setCodigoProducto(String codigoProducto) {
		this.codigoProducto = codigoProducto;
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
	 *            the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * Gets the logo.
	 *
	 * @return the logo
	 */
	public String getLogo() {
		return logo;
	}

	/**
	 * Sets the logo.
	 *
	 * @param logo
	 *            the logo to set
	 */
	public void setLogo(String logo) {
		this.logo = logo;
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
		hcb.append(id);
		hcb.append(affinityGroup);
		hcb.append(apellido);
		hcb.append(categoria);
		hcb.append(codTipoTarjeta);
		hcb.append(codigoSucursal);
		hcb.append(cuenta);
		hcb.append(fechaDesde);
		hcb.append(fechaNacimiento);
		hcb.append(habiente);
		hcb.append(nombre);
		hcb.append(documento);
		hcb.append(producto);
		hcb.append(tarjetaActiva);
		hcb.append(tarjetaProdu);
		hcb.append(telefono);
		hcb.append(tipoDocumento);
		hcb.append(tipoTarjetaDetalle);
		hcb.append(titular);
		hcb.append(vencimiento);
		hcb.append(website);
		hcb.append(email);
		hcb.append(codigoProducto);
		hcb.append(estado);
		hcb.append(logo);
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
		DatosEntity other = (DatosEntity) obj;

		EqualsBuilder eb = new EqualsBuilder();
		eb.append(id, other.getId());
		eb.append(affinityGroup, other.getAffinityGroup());
		eb.append(apellido, other.getApellido());
		eb.append(categoria, other.getCategoria());
		eb.append(codTipoTarjeta, other.getCodTipoTarjeta());
		eb.append(codigoSucursal, other.getCodigoSucursal());
		eb.append(cuenta, other.getCuenta());
		eb.append(fechaDesde, other.getFechaDesde());
		eb.append(fechaNacimiento, other.getFechaNacimiento());
		eb.append(habiente, other.getHabiente());
		eb.append(nombre, other.getNombre());
		eb.append(documento, other.getDocumento());
		eb.append(producto, other.getProducto());
		eb.append(tarjetaActiva, other.getTarjetaActiva());
		eb.append(tarjetaProdu, other.getTarjetaProdu());
		eb.append(telefono, other.getTelefono());
		eb.append(tipoDocumento, other.getTipoDocumento());
		eb.append(tipoTarjetaDetalle, other.getTipoTarjetaDetalle());
		eb.append(titular, other.getTitular());
		eb.append(vencimiento, other.getVencimiento());
		eb.append(website, other.getWebsite());
		eb.append(email, other.getEmail());
		eb.append(codigoProducto, other.getCodigoProducto());
		eb.append(estado, other.getEstado());
		eb.append(logo, other.getLogo());
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
		return "DatosEntity [id=" + id + ", affinityGroup=" + affinityGroup + ", apellido=" + apellido + ", categoria="
				+ categoria + ", codTipoTarjeta=" + codTipoTarjeta + ", codigoSucursal=" + codigoSucursal + ", cuenta="
				+ cuenta + ", fechaDesde=" + fechaDesde + ", fechaNacimiento=" + fechaNacimiento + ", habiente="
				+ habiente + ", nombre=" + nombre + ", documento=" + documento + ", producto=" + producto
				+ ", tarjetaActiva=" + tarjetaActiva + ", tarjetaProdu=" + tarjetaProdu + ", telefono=" + telefono
				+ ", tipoDocumento=" + tipoDocumento + ", tipoTarjetaDetalle=" + tipoTarjetaDetalle + ", titular="
				+ titular + ", vencimiento=" + vencimiento + ", website=" + website + ", email=" + email
				+ ", codigoProducto=" + codigoProducto + ", estado=" + estado + ", logo=" + logo + ", sucursal="
				+ sucursal + "]";
	}

}
