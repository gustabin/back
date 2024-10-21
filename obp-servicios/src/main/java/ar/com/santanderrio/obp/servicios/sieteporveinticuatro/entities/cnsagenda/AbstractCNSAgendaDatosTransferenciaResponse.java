/*
 * 
 */
package ar.com.santanderrio.obp.servicios.sieteporveinticuatro.entities.cnsagenda;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * The Class AbstractCNSAgendaDatosTransferenciaResponse.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({ CNSAgendaDatosRioRioTransferenciaResponse.class, CNSAgendaDatosOtrosBancosTransferenciaResponse.class })
public class AbstractCNSAgendaDatosTransferenciaResponse {

	/** The nup. */
	@XmlElement(name = "Nup")
	private String nup;

	/** The nro cta debito. */
	@XmlElement(name = "NroCtaDebito")
	private String nroCtaDebito;

	/** The fecha nac. */
	@XmlElement(name = "FechaNac")
	private String fechaNac;

	/** The tipo persona. */
	@XmlElement(name = "TipoPersona")
	private String tipoPersona;

	/** The sucursal cta credito. */
	@XmlElement(name = "SucCtaCredito")
	private String sucursalCtaCredito;

	/** The nombre cta credito. */
	@XmlElement(name = "NombreCtaCredito")
	private String nombreCtaCredito;

	/** The nro cta credito. */
	@XmlElement(name = "NroCtaCredito")
	private String nroCtaCredito;

	/** The sucursal cta debito. */
	@XmlElement(name = "SucCtaDebito")
	private String sucursalCtaDebito;

	/** The tipo cta debito. */
	@XmlElement(name = "TipoCtaDebito")
	private String tipoCtaDebito;

	/** The tipo id. */
	@XmlElement(name = "TipoId")
	private String tipoId;

	/** The id cliente. */
	@XmlElement(name = "IdCliente")
	private String idCliente;

	/** The concepto. */
	@XmlElement(name = "Concepto")
	private String concepto;

	/** The titular credito. */
	@XmlElement(name = "TitularCredito")
	private String titularCredito;

	/** The descripcion adicional 3. */
	@XmlElement(name = "DescripcionAdicional3")
	private String descripcionAdicional3;

	/** The descripcion adicional 2. */
	@XmlElement(name = "DescripcionAdicional2")
	private String descripcionAdicional2;

	/** The descripcion adicional 1. */
	@XmlElement(name = "DescripcionAdicional1")
	private String descripcionAdicional1;

	/** The mail cliente reply. */
	@XmlElement(name = "MailClienteReply")
	private String mailClienteReply;

	/** The concepto adicional 3. */
	@XmlElement(name = "ConceptoAdicional3")
	private String conceptoAdicional3;

	/** The concepto adicional 2. */
	@XmlElement(name = "ConceptoAdicional2")
	private String conceptoAdicional2;

	/** The info adicional. */
	@XmlElement(name = "InfoAdicional")
	private String infoAdicional;

	/** The concepto adicional 1. */
	@XmlElement(name = "ConceptoAdicional1")
	private String conceptoAdicional1;

	/** The anotaciones personales. */
	@XmlElement(name = "AnotacionesPersonales")
	private String anotacionesPersonales;

	/** The comentario. */
	@XmlElement(name = "Comentario")
	private String comentario;

	/** The titular debito. */
	@XmlElement(name = "TitularDebito")
	private String titularDebito;

	/** The codigo concepto. */
	@XmlElement(name = "CodigoConcepto")
	private String codigoConcepto;

	/** The descripcion concepto. */
	@XmlElement(name = "DescripcionConcepto")
	private String descripcionConcepto;

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
	 *            the new nup
	 */
	public void setNup(String nup) {
		this.nup = nup;
	}

	/**
	 * Gets the nro cta debito.
	 *
	 * @return the nro cta debito
	 */
	public String getNroCtaDebito() {
		return nroCtaDebito;
	}

	/**
	 * Sets the nro cta debito.
	 *
	 * @param nroCtaDebito
	 *            the new nro cta debito
	 */
	public void setNroCtaDebito(String nroCtaDebito) {
		this.nroCtaDebito = nroCtaDebito;
	}

	/**
	 * Gets the fecha nac.
	 *
	 * @return the fecha nac
	 */
	public String getFechaNac() {
		return fechaNac;
	}

	/**
	 * Sets the fecha nac.
	 *
	 * @param fechaNac
	 *            the new fecha nac
	 */
	public void setFechaNac(String fechaNac) {
		this.fechaNac = fechaNac;
	}

	/**
	 * Gets the tipo persona.
	 *
	 * @return the tipo persona
	 */
	public String getTipoPersona() {
		return tipoPersona;
	}

	/**
	 * Sets the tipo persona.
	 *
	 * @param tipoPersona
	 *            the new tipo persona
	 */
	public void setTipoPersona(String tipoPersona) {
		this.tipoPersona = tipoPersona;
	}

	/**
	 * Gets the sucursal cta credito.
	 *
	 * @return the sucursal cta credito
	 */
	public String getSucursalCtaCredito() {
		return sucursalCtaCredito;
	}

	/**
	 * Sets the sucursal cta credito.
	 *
	 * @param sucursalCtaCredito
	 *            the new sucursal cta credito
	 */
	public void setSucursalCtaCredito(String sucursalCtaCredito) {
		this.sucursalCtaCredito = sucursalCtaCredito;
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
	 * Gets the nro cta credito.
	 *
	 * @return the nro cta credito
	 */
	public String getNroCtaCredito() {
		return nroCtaCredito;
	}

	/**
	 * Sets the nro cta credito.
	 *
	 * @param nroCtaCredito
	 *            the new nro cta credito
	 */
	public void setNroCtaCredito(String nroCtaCredito) {
		this.nroCtaCredito = nroCtaCredito;
	}

	/**
	 * Gets the sucursal cta debito.
	 *
	 * @return the sucursal cta debito
	 */
	public String getSucursalCtaDebito() {
		return sucursalCtaDebito;
	}

	/**
	 * Sets the sucursal cta debito.
	 *
	 * @param sucursalCtaDebito
	 *            the new sucursal cta debito
	 */
	public void setSucursalCtaDebito(String sucursalCtaDebito) {
		this.sucursalCtaDebito = sucursalCtaDebito;
	}

	/**
	 * Gets the tipo cta debito.
	 *
	 * @return the tipo cta debito
	 */
	public String getTipoCtaDebito() {
		return tipoCtaDebito;
	}

	/**
	 * Sets the tipo cta debito.
	 *
	 * @param tipoCtaDebito
	 *            the new tipo cta debito
	 */
	public void setTipoCtaDebito(String tipoCtaDebito) {
		this.tipoCtaDebito = tipoCtaDebito;
	}

	/**
	 * Gets the tipo id.
	 *
	 * @return the tipo id
	 */
	public String getTipoId() {
		return tipoId;
	}

	/**
	 * Sets the tipo id.
	 *
	 * @param tipoId
	 *            the new tipo id
	 */
	public void setTipoId(String tipoId) {
		this.tipoId = tipoId;
	}

	/**
	 * Gets the id cliente.
	 *
	 * @return the id cliente
	 */
	public String getIdCliente() {
		return idCliente;
	}

	/**
	 * Sets the id cliente.
	 *
	 * @param idCliente
	 *            the new id cliente
	 */
	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}

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
	 *            the new concepto
	 */
	public void setConcepto(String concepto) {
		this.concepto = concepto;
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
	 * Gets the descripcion adicional 3.
	 *
	 * @return the descripcion adicional 3
	 */
	public String getDescripcionAdicional3() {
		return descripcionAdicional3;
	}

	/**
	 * Sets the descripcion adicional 3.
	 *
	 * @param descripcionAdicional3
	 *            the new descripcion adicional 3
	 */
	public void setDescripcionAdicional3(String descripcionAdicional3) {
		this.descripcionAdicional3 = descripcionAdicional3;
	}

	/**
	 * Gets the descripcion adicional 2.
	 *
	 * @return the descripcion adicional 2
	 */
	public String getDescripcionAdicional2() {
		return descripcionAdicional2;
	}

	/**
	 * Sets the descripcion adicional 2.
	 *
	 * @param descripcionAdicional2
	 *            the new descripcion adicional 2
	 */
	public void setDescripcionAdicional2(String descripcionAdicional2) {
		this.descripcionAdicional2 = descripcionAdicional2;
	}

	/**
	 * Gets the descripcion adicional 1.
	 *
	 * @return the descripcion adicional 1
	 */
	public String getDescripcionAdicional1() {
		return descripcionAdicional1;
	}

	/**
	 * Sets the descripcion adicional 1.
	 *
	 * @param descripcionAdicional1
	 *            the new descripcion adicional 1
	 */
	public void setDescripcionAdicional1(String descripcionAdicional1) {
		this.descripcionAdicional1 = descripcionAdicional1;
	}

	/**
	 * Gets the mail cliente reply.
	 *
	 * @return the mail cliente reply
	 */
	public String getMailClienteReply() {
		return mailClienteReply;
	}

	/**
	 * Sets the mail cliente reply.
	 *
	 * @param mailClienteReply
	 *            the new mail cliente reply
	 */
	public void setMailClienteReply(String mailClienteReply) {
		this.mailClienteReply = mailClienteReply;
	}

	/**
	 * Gets the concepto adicional 3.
	 *
	 * @return the concepto adicional 3
	 */
	public String getConceptoAdicional3() {
		return conceptoAdicional3;
	}

	/**
	 * Sets the concepto adicional 3.
	 *
	 * @param conceptoAdicional3
	 *            the new concepto adicional 3
	 */
	public void setConceptoAdicional3(String conceptoAdicional3) {
		this.conceptoAdicional3 = conceptoAdicional3;
	}

	/**
	 * Gets the concepto adicional 2.
	 *
	 * @return the concepto adicional 2
	 */
	public String getConceptoAdicional2() {
		return conceptoAdicional2;
	}

	/**
	 * Sets the concepto adicional 2.
	 *
	 * @param conceptoAdicional2
	 *            the new concepto adicional 2
	 */
	public void setConceptoAdicional2(String conceptoAdicional2) {
		this.conceptoAdicional2 = conceptoAdicional2;
	}

	/**
	 * Gets the info adicional.
	 *
	 * @return the info adicional
	 */
	public String getInfoAdicional() {
		return infoAdicional;
	}

	/**
	 * Sets the info adicional.
	 *
	 * @param infoAdicional
	 *            the new info adicional
	 */
	public void setInfoAdicional(String infoAdicional) {
		this.infoAdicional = infoAdicional;
	}

	/**
	 * Gets the concepto adicional 1.
	 *
	 * @return the concepto adicional 1
	 */
	public String getConceptoAdicional1() {
		return conceptoAdicional1;
	}

	/**
	 * Sets the concepto adicional 1.
	 *
	 * @param conceptoAdicional1
	 *            the new concepto adicional 1
	 */
	public void setConceptoAdicional1(String conceptoAdicional1) {
		this.conceptoAdicional1 = conceptoAdicional1;
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
	 * Gets the titular debito.
	 *
	 * @return the titular debito
	 */
	public String getTitularDebito() {
		return titularDebito;
	}

	/**
	 * Sets the titular debito.
	 *
	 * @param titularDebito
	 *            the new titular debito
	 */
	public void setTitularDebito(String titularDebito) {
		this.titularDebito = titularDebito;
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
}
