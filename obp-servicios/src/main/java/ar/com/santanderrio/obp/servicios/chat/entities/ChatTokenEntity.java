/*
	"NUP=" + nup + "|" + "JSESSIONID=" + jSessionID + "|" + "DOCUMENTO=" + documento + "|" + "APELLIDO=" + apellido + "|" + 
			"NOMBRE=" + nombre + "|" + "SEGMENTOOBP=" + segmento + "|" + "EMAILMYA=" + mail + "|" + "FECHANACIMIENTO=" + fechaNac + "|" + 
			"NROTARJETAVISA=" + trjVisa + "|" + "NROTARJETAAMEX=" + trjAmex + "|" + "NROTARJETADEBITO=" + trjDebito + "|" + 
			"NROCUENTA=" + nroCta;
			
*/
package ar.com.santanderrio.obp.servicios.chat.entities;

/**
 * The Class ChatTokenEntity.
 */
public class ChatTokenEntity {
	
	/** The nup. */
	private String nup;
	
	/** The j session ID. */
	private String jSessionID;
	
	/** The documento. */
	private String documento;
	
	/** The apellido. */
	private String apellido;
	
	/** The nombre. */
	private String nombre;
	
	/** The segmento. */
	private String segmento;
	
	/** The mail. */
	private String mail;
	
	/** The fecha nacimiento. */
	private String fechaNacimiento;
	
	/** The tarjeta visa. */
	private String tarjetaVisa;
	
	/** The tarjeta amex. */
	private String tarjetaAmex;
	
	/** The tarjeta debito. */
	private String tarjetaDebito;
	
	/** The numero cuenta. */
	private String numeroCuenta;
	
	/** The canal. */
	private String canal;
	
	/** The intencion goto. */
	private String intencionGoto;
	
	/** The id GEC. */
	private String idGEC;
	
	/** The id interno GEC. */
	private String idInternoGEC;
	
	/** The Tipo oferta GEC. */
	private String TipoOfertaGEC;
	

	/**
	 * Gets the nup.
	 *
	 * @return the nup
	 */
	public String getNup() {
		return nup;
	}

	/**
	 * Sets the nup.
	 *
	 * @param nup
	 *            the nup to set
	 */
	public void setNup(String nup) {
		this.nup = nup;
	}

	/**
	 * Gets the j session ID.
	 *
	 * @return the jSessionID
	 */
	public String getJSessionID() {
		return jSessionID;
	}

	/**
	 * Sets the j session ID.
	 *
	 * @param jSessionID
	 *            the jSessionID to set
	 */
	public void setJSessionID(String jSessionID) {
		this.jSessionID = jSessionID;
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
	 * Gets the segmento.
	 *
	 * @return the segmento
	 */
	public String getSegmento() {
		return segmento;
	}

	/**
	 * Sets the segmento.
	 *
	 * @param segmento
	 *            the segmento to set
	 */
	public void setSegmento(String segmento) {
		this.segmento = segmento;
	}

	/**
	 * Gets the mail.
	 *
	 * @return the mail
	 */
	public String getMail() {
		return mail;
	}

	/**
	 * Sets the mail.
	 *
	 * @param mail
	 *            the mail to set
	 */
	public void setMail(String mail) {
		this.mail = mail;
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
	 * Gets the tarjeta visa.
	 *
	 * @return the tarjetaVisa
	 */
	public String getTarjetaVisa() {
		return tarjetaVisa;
	}

	/**
	 * Sets the tarjeta visa.
	 *
	 * @param tarjetaVisa
	 *            the tarjetaVisa to set
	 */
	public void setTarjetaVisa(String tarjetaVisa) {
		this.tarjetaVisa = tarjetaVisa;
	}

	/**
	 * Gets the tarjeta amex.
	 *
	 * @return the tarjetaAmex
	 */
	public String getTarjetaAmex() {
		return tarjetaAmex;
	}

	/**
	 * Sets the tarjeta amex.
	 *
	 * @param tarjetaAmex
	 *            the tarjetaAmex to set
	 */
	public void setTarjetaAmex(String tarjetaAmex) {
		this.tarjetaAmex = tarjetaAmex;
	}

	/**
	 * Gets the tarjeta debito.
	 *
	 * @return the tarjetaDebito
	 */
	public String getTarjetaDebito() {
		return tarjetaDebito;
	}

	/**
	 * Sets the tarjeta debito.
	 *
	 * @param tarjetaDebito
	 *            the tarjetaDebito to set
	 */
	public void setTarjetaDebito(String tarjetaDebito) {
		this.tarjetaDebito = tarjetaDebito;
	}

	/**
	 * Gets the numero cuenta.
	 *
	 * @return the numeroCuenta
	 */
	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	/**
	 * Sets the numero cuenta.
	 *
	 * @param numeroCuenta
	 *            the numeroCuenta to set
	 */
	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	/**
	 * Gets the canal.
	 *
	 * @return the canal
	 */
	public String getCanal() {
		return canal;
	}

	/**
	 * Sets the canal.
	 *
	 * @param canal
	 *            the canal to set
	 */
	public void setCanal(String canal) {
		this.canal = canal;
	}

	/**
	 * Gets the intencion goto.
	 *
	 * @return the intencion goto
	 */
	public String getIntencionGoto() {
		return intencionGoto;
	}

	/**
	 * Sets the intencion goto.
	 *
	 * @param intencionGoto the new intencion goto
	 */
	public void setIntencionGoto(String intencionGoto) {
		this.intencionGoto = intencionGoto;
	}

	/**
	 * Gets the id GEC.
	 *
	 * @return the id GEC
	 */
	public String getIdGEC() {
		return idGEC;
	}

	/**
	 * Sets the id GEC.
	 *
	 * @param idGEC the new id GEC
	 */
	public void setIdGEC(String idGEC) {
		this.idGEC = idGEC;
	}

	/**
	 * Gets the id interno GEC.
	 *
	 * @return the id interno GEC
	 */
	public String getIdInternoGEC() {
		return idInternoGEC;
	}

	/**
	 * Sets the id interno GEC.
	 *
	 * @param idInternoGEC the new id interno GEC
	 */
	public void setIdInternoGEC(String idInternoGEC) {
		this.idInternoGEC = idInternoGEC;
	}

	/**
	 * Gets the tipo oferta GEC.
	 *
	 * @return the tipo oferta GEC
	 */
	public String getTipoOfertaGEC() {
		return TipoOfertaGEC;
	}

	/**
	 * Sets the tipo oferta GEC.
	 *
	 * @param tipoOfertaGEC the new tipo oferta GEC
	 */
	public void setTipoOfertaGEC(String tipoOfertaGEC) {
		TipoOfertaGEC = tipoOfertaGEC;
	}

	@Override
	public String toString() {
		return "ChatTokenEntity [nup=" + nup + ", jSessionID=" + jSessionID + ", documento=" + documento + ", apellido="
				+ apellido + ", nombre=" + nombre + ", segmento=" + segmento + ", mail=" + mail + ", fechaNacimiento="
				+ fechaNacimiento + ", tarjetaVisa=" + tarjetaVisa + ", tarjetaAmex=" + tarjetaAmex + ", tarjetaDebito="
				+ tarjetaDebito + ", numeroCuenta=" + numeroCuenta + ", canal=" + canal + ", intencionGoto="
				+ intencionGoto + ", idGEC=" + idGEC + ", idInternoGEC=" + idInternoGEC + ", TipoOfertaGEC="
				+ TipoOfertaGEC + "]";
	}		

}
