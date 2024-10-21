/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import ar.com.santanderrio.obp.servicios.comprobantes.web.view.DetalleComprobanteTransferenciaView;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;

/**
 * The Class DetalleComprobanteTransferenciaDTO.
 * 
 */
public class DetalleComprobanteTransferenciaDTO extends DetalleComprobanteDTO {

	/** The banco. */
	private String banco;

	/** The plazo acreditacion. */
	private String plazoAcreditacion;

	/** The nro cuenta destino. */
	private String nroCuentaDestino;

	/** The tipo cuenta destino. */
	private TipoCuenta tipoCuentaDestino;

	/** The destinatario. */
	private String destinatario;

	/** The concepto. */
	private String concepto;

	/** The des concepto. */
	private String desConcepto;

	/** The mail. */
	private String mail;

	/** The aviso transferencia. */
	private String avisoTransferencia;

	/** The comentarios. */
	private String comentarios;

	/**
	 * Gets the banco.
	 *
	 * @return the banco
	 */
	public String getBanco() {
		return banco;
	}

	/**
	 * Sets the banco.
	 *
	 * @param banco
	 *            the new banco
	 */
	public void setBanco(String banco) {
		this.banco = banco;
	}

	/**
	 * Gets the plazo acreditacion.
	 *
	 * @return the plazo acreditacion
	 */
	public String getPlazoAcreditacion() {
		return plazoAcreditacion;
	}

	/**
	 * Sets the plazo acreditacion.
	 *
	 * @param plazoAcreditacion
	 *            the new plazo acreditacion
	 */
	public void setPlazoAcreditacion(String plazoAcreditacion) {
		this.plazoAcreditacion = plazoAcreditacion;
	}

	/**
	 * Gets the nro cuenta destino.
	 *
	 * @return the nro cuenta destino
	 */
	public String getNroCuentaDestino() {
		return nroCuentaDestino;
	}

	/**
	 * Sets the nro cuenta destino.
	 *
	 * @param nroCuentaDestino
	 *            the new nro cuenta destino
	 */
	public void setNroCuentaDestino(String nroCuentaDestino) {
		this.nroCuentaDestino = nroCuentaDestino;
	}

	/**
	 * Gets the tipo cuenta destino.
	 *
	 * @return the tipo cuenta destino
	 */
	public TipoCuenta getTipoCuentaDestino() {
		return tipoCuentaDestino;
	}

	/**
	 * Sets the tipo cuenta destino.
	 *
	 * @param tipoCuentaDestino
	 *            the new tipo cuenta destino
	 */
	public void setTipoCuentaDestino(TipoCuenta tipoCuentaDestino) {
		this.tipoCuentaDestino = tipoCuentaDestino;
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
	 * Gets the des concepto.
	 *
	 * @return the des concepto
	 */
	public String getDesConcepto() {
		return desConcepto;
	}

	/**
	 * Sets the des concepto.
	 *
	 * @param desConcepto
	 *            the new des concepto
	 */
	public void setDesConcepto(String desConcepto) {
		this.desConcepto = desConcepto;
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
	 *            the new mail
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}

	/**
	 * Gets the aviso transferencia.
	 *
	 * @return the aviso transferencia
	 */
	public String getAvisoTransferencia() {
		return avisoTransferencia;
	}

	/**
	 * Sets the aviso transferencia.
	 *
	 * @param avisoTransferencia
	 *            the new aviso transferencia
	 */
	public void setAvisoTransferencia(String avisoTransferencia) {
		this.avisoTransferencia = avisoTransferencia;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.comprobantes.dto.DetalleComprobanteDTO#
	 * hashCode()
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hb = new HashCodeBuilder();
		hb.append(avisoTransferencia);
		hb.append(banco);
		hb.append(comentarios);
		hb.append(concepto);
		hb.append(desConcepto);
		hb.append(destinatario);
		hb.append(mail);
		hb.append(nroCuentaDestino);
		hb.append(plazoAcreditacion);
		hb.append(tipoCuentaDestino);
		return hb.toHashCode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.comprobantes.dto.DetalleComprobanteDTO#
	 * equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object a) {
		if (this == a) {
			return true;
		}

		if (a == null) {
			return false;
		}

		if (getClass() != a.getClass()) {
			return false;
		}
		DetalleComprobanteTransferenciaDTO detalle = (DetalleComprobanteTransferenciaDTO) a;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(avisoTransferencia, detalle.getAvisoTransferencia());
		eb.append(banco, detalle.getBanco());
		eb.append(comentarios, detalle.getComentarios());
		eb.append(concepto, detalle.getConcepto());
		eb.append(desConcepto, detalle.getDesConcepto());
		eb.append(destinatario, detalle.getDestinatario());
		eb.append(mail, detalle.getMail());
		eb.append(nroCuentaDestino, detalle.getNroCuentaDestino());
		eb.append(plazoAcreditacion, detalle.getPlazoAcreditacion());
		eb.append(tipoCuentaDestino, detalle.getTipoCuentaDestino());
		return eb.isEquals();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.comprobantes.dto.DetalleComprobanteDTO#
	 * toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("DetalleComprobanteTransferenciaDTO [");
		sb.append(conseguirDatosDetalle());
		sb.append("]");
		return sb.toString();
	}

	/**
	 * Conseguir datos detalle.
	 *
	 * @return the string
	 */
	protected String conseguirDatosDetalle() {
		StringBuilder sb = new StringBuilder();
		sb.append("avisoTransferencia=");
		sb.append(avisoTransferencia);
		sb.append(", banco= ");
		sb.append(banco);
		sb.append(", comentarios= ");
		sb.append(comentarios);
		sb.append(", concepto= ");
		sb.append(concepto);
		sb.append(", desConcepto= ");
		sb.append(desConcepto);
		sb.append(", destinatario= ");
		sb.append(destinatario);
		sb.append(", mail= ");
		sb.append(mail);
		sb.append(", nroCuentaDestino= ");
		sb.append(nroCuentaDestino);
		sb.append(", plazoAcreditacion= ");
		sb.append(plazoAcreditacion);
		sb.append(", tipoCuentaDestino= ");
		sb.append(tipoCuentaDestino);
		return sb.toString();
	}

	/**
	 * Setear num Y tipo cuenta destino.
	 *
	 * @param view
	 *            the view
	 */
	public void setearNumeroYTipoCuentaDestino(DetalleComprobanteTransferenciaView view) {
		view.setNroCuentaDestino(getNroCuentaDestino());
		if (getTipoCuentaDestino() != null)
			view.setTipoCuentaDestino(
					ISBANStringUtils.formatearFraseInicialMayuscula(getTipoCuentaDestino().getDescripcion()));
	}

	/**
	 * Setear num Y tipo cuenta destino.
	 *
	 * @param view
	 *            the view
	 * @param tipo
	 *            the tipo
	 */
	public void setearNumeroYTipoCuentaDestino(DetalleComprobanteTransferenciaView view, String tipo) {
		view.setNroCuentaDestino(getNroCuentaDestino());
		view.setTipoCuentaDestino(tipo);
	}

	/**
	 * Setear concepto Y desconcepto.
	 *
	 * @param view
	 *            the view
	 * @param desconcepto
	 *            the desconcepto
	 */
	public void setearConceptoYDesconcepto(DetalleComprobanteTransferenciaView view, String desconcepto) {
		view.setConcepto(getConcepto());
		view.setDesConcepto(desconcepto);
	}
	
	@Override
    public String obtenerIdentificacionHistorial() {
    	return nroCuentaDestino;
    }


}
