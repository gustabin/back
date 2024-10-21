/*
 * 
 */
package ar.com.santanderrio.obp.servicios.transferencias.entities;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * The Class DatosTransferenciaAgendada.
 */
public class DatosTransferenciaAgendadaEntity {

	/** The nup. */
	private String nup;

	/** The nro cta debito. */
	private String nroCtaDebito;

	/** The fecha nac. */
	private String fechaNac;

	/** The tipo persona. */
	private String tipoPersona;

	/** The sucursal cta credito. */
	private String sucursalCtaCredito;

	/** The nombre cta credito. */
	private String nombreCtaCredito;

	/** The nro cta credito. */
	private String nroCtaCredito;

	/** The sucursal cta debito. */
	private String sucursalCtaDebito;

	/** The tipo cta debito. */
	private String tipoCtaDebito;

	/** The tipo id. */
	private String tipoId;

	/** The id cliente. */
	private String idCliente;

	/** The concepto. */
	private String concepto;

	/** The titular credito. */
	private String titularCredito;

	/** The descripcion adicional 3. */
	private String descripcionAdicional3;

	/** The descripcion adicional 2. */
	private String descripcionAdicional2;

	/** The descripcion adicional 1. */
	private String descripcionAdicional1;

	/** The mail cliente reply. */
	private String mailClienteReply;

	/** The concepto adicional 3. */
	private String conceptoAdicional3;

	/** The concepto adicional 2. */
	private String conceptoAdicional2;

	/** The info adicional. */
	private String infoAdicional;

	/** The concepto adicional 1. */
	private String conceptoAdicional1;

	/** The anotaciones personales. */
	private String anotacionesPersonales;

	/** The comentario. */
	private String comentario;

	/** The titular debito. */
	private String titularDebito;

	/** The codigo concepto. */
	private String codigoConcepto;

	/** The descripcion concepto. */
	private String descripcionConcepto;

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(codigoConcepto).append(anotacionesPersonales).append(comentario).append(concepto)
				.append(conceptoAdicional1).append(conceptoAdicional2).append(conceptoAdicional3)
				.append(descripcionAdicional1).append(descripcionAdicional2).append(descripcionAdicional3)
				.append(descripcionConcepto).append(fechaNac).append(idCliente).append(infoAdicional)
				.append(mailClienteReply).append(nombreCtaCredito).append(nroCtaCredito).append(nroCtaDebito)
				.append(nup).append(sucursalCtaCredito).append(sucursalCtaDebito).append(tipoCtaDebito).append(tipoId)
				.append(tipoPersona).append(titularCredito).append(titularDebito);
		return hcb.hashCode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
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
		DatosTransferenciaAgendadaEntity other = (DatosTransferenciaAgendadaEntity) obj;
		return new EqualsBuilder().append(anotacionesPersonales, other.anotacionesPersonales)
				.append(codigoConcepto, other.codigoConcepto).append(comentario, other.comentario)
				.append(concepto, other.concepto).append(conceptoAdicional1, other.conceptoAdicional1)
				.append(conceptoAdicional2, other.conceptoAdicional2)
				.append(conceptoAdicional3, other.conceptoAdicional3)
				.append(descripcionAdicional1, other.descripcionAdicional1)
				.append(descripcionAdicional2, other.descripcionAdicional2)
				.append(descripcionAdicional3, other.descripcionAdicional3)
				.append(descripcionConcepto, other.descripcionConcepto).append(fechaNac, other.fechaNac)
				.append(idCliente, other.idCliente).append(infoAdicional, other.infoAdicional)
				.append(mailClienteReply, other.mailClienteReply).append(nombreCtaCredito, other.nombreCtaCredito)
				.append(nroCtaCredito, other.nroCtaCredito).append(nroCtaDebito, other.nroCtaDebito)
				.append(nup, other.nup).append(sucursalCtaCredito, other.sucursalCtaCredito)
				.append(sucursalCtaDebito, other.sucursalCtaDebito).append(tipoCtaDebito, other.tipoCtaDebito)
				.append(tipoId, other.tipoId).append(tipoPersona, other.tipoPersona)
				.append(titularCredito, other.titularCredito).append(titularDebito, other.titularDebito).isEquals();
	}

}
