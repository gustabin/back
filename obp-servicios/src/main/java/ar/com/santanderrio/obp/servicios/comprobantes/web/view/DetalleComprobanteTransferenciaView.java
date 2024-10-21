/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.web.view;

import java.io.FileNotFoundException;
import java.util.HashMap;

import org.springframework.util.ResourceUtils;

/**
 * The Class DetalleComprobanteTransferenciaView.
 */
public class DetalleComprobanteTransferenciaView extends DetalleComprobanteView {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The nro cuenta destino. */
	private String nroCuentaDestino;

	/** The tipo cuenta destino. */
	private String tipoCuentaDestino;

	/** The banco. */
	private String banco;

	/** The concepto. */
	private String concepto;

	/** The des concepto. */
	private String desConcepto;

	/** The plazo acreditacion. */
	private String plazoAcreditacion;

	/** The mail. */
	private String mail;

	/** The comentarios. */
	private String comentarios;

	/** The aviso transf mail. */
	private String avisoTransfMail;

	/** The es pago de haberes. */
	private Boolean esPagoDeHaberes = Boolean.FALSE;

	/** The nro operacion programada. */
	private String nroOperacionProgramada;

	/** The numero destino. */
	protected final String numeroDestinoKey = "NUMERO_DESTINO";

	/** The tipo destino. */
	protected final String tipoDestinoKey = "TIPO_DESTINO";

	/** The banco. */
	protected final String bancoKey = "BANCO";

	/** The concepto. */
	protected final String conceptoKey = "CONCEPTO";

	/** The plazo acreditacion. */
	protected final String plazoAcreditacionKey = "PLAZO_ACREDITACION";

	/** The aviso. */
	protected final String avisoKey = "AVISO";

	/** The email. */
	protected final String emailKey = "EMAIL";

	/** The mensaje. */
	protected final String mensajeKey = "MENSAJE";

	/** The paghabon jasper. */
	private final String paghabonJasper = "paghabon.japer";

	/** The nro cuenta destino. */
	private String cuenta;

	/** The nro cuenta destino. */
	protected final String cuentaKey = "CUENTA_DESTINO";

	/** The tipo cuenta. */
	private String tipoCuenta;

	/** The tipo cuenta key. */
	protected final String tipoCuentaKey = "TIPO_CUENTA_DESTINO";

	/** The nro operacion programada key. */
	protected final String nroOperacionProgramadaKey = "NROOPERACIONPROGRAMADA";

	/** The CBU. */
	private String cbu;

	/** The CBU key. */
	protected final String cbuKey = "CBU";

	/** The aliasCBU. */
	private String aliasCBU;

	/** The ALIAS_CBU key. */
	protected final String aliasCBUKey = "ALIAS_CBU";

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
	public String getTipoCuentaDestino() {
		return tipoCuentaDestino;
	}

	/**
	 * Sets the tipo cuenta destino.
	 *
	 * @param tipoCuentaDestino
	 *            the new tipo cuenta destino
	 */
	public void setTipoCuentaDestino(String tipoCuentaDestino) {
		this.tipoCuentaDestino = tipoCuentaDestino;
	}

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
	 * Gets the aviso transf mail.
	 *
	 * @return the aviso transf mail
	 */
	public String getAvisoTransfMail() {
		return avisoTransfMail;
	}

	/**
	 * Sets the aviso transf mail.
	 *
	 * @param avisoTransfMail
	 *            the new aviso transf mail
	 */
	public void setAvisoTransfMail(String avisoTransfMail) {
		this.avisoTransfMail = avisoTransfMail;
	}

	/**
	 * Gets the es pago de haberes.
	 *
	 * @return the esPagoDeHaberes
	 */
	public Boolean getEsPagoDeHaberes() {
		return esPagoDeHaberes;
	}

	/**
	 * Sets the es pago de haberes.
	 *
	 * @param esPagoDeHaberes
	 *            the esPagoDeHaberes to set
	 */
	public void setEsPagoDeHaberes(Boolean esPagoDeHaberes) {
		this.esPagoDeHaberes = esPagoDeHaberes;
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
	 *            the new cuenta
	 */
	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	/**
	 * Gets the tipo cuenta.
	 *
	 * @return the tipoCuenta
	 */
	public String getTipoCuenta() {
		return tipoCuenta;
	}

	/**
	 * Sets the tipo cuenta.
	 *
	 * @param tipoCuenta
	 *            the tipoCuenta to set
	 */
	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	/**
	 * Gets the nro operacion programada.
	 *
	 * @return the nroOperacionProgramada
	 */
	public String getNroOperacionProgramada() {
		return nroOperacionProgramada;
	}

	/**
	 * Sets the nro operacion programada.
	 *
	 * @param nroOperacionProgramada
	 *            the nroOperacionProgramada to set
	 */
	public void setNroOperacionProgramada(String nroOperacionProgramada) {
		this.nroOperacionProgramada = nroOperacionProgramada;
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
	 * @return the aliasCBU
	 */
	public String getAliasCBU() {
		return aliasCBU;
	}

	/**
	 * @param aliasCBU
	 *            the aliasCBU to set
	 */
	public void setAliasCBU(String aliasCBU) {
		this.aliasCBU = aliasCBU;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the numeroDestinoKey
	 */
	public String getNumeroDestinoKey() {
		return numeroDestinoKey;
	}

	/**
	 * @return the tipoDestinoKey
	 */
	public String getTipoDestinoKey() {
		return tipoDestinoKey;
	}

	/**
	 * @return the bancoKey
	 */
	public String getBancoKey() {
		return bancoKey;
	}

	/**
	 * @return the conceptoKey
	 */
	public String getConceptoKey() {
		return conceptoKey;
	}

	/**
	 * @return the plazoAcreditacionKey
	 */
	public String getPlazoAcreditacionKey() {
		return plazoAcreditacionKey;
	}

	/**
	 * @return the avisoKey
	 */
	public String getAvisoKey() {
		return avisoKey;
	}

	/**
	 * @return the emailKey
	 */
	public String getEmailKey() {
		return emailKey;
	}

	/**
	 * @return the mensajeKey
	 */
	public String getMensajeKey() {
		return mensajeKey;
	}

	/**
	 * @return the paghabonJasper
	 */
	public String getPaghabonJasper() {
		return paghabonJasper;
	}

	/**
	 * @return the cuentaKey
	 */
	public String getCuentaKey() {
		return cuentaKey;
	}

	/**
	 * @return the tipoCuentaKey
	 */
	public String getTipoCuentaKey() {
		return tipoCuentaKey;
	}

	/**
	 * @return the nroOperacionProgramadaKey
	 */
	public String getNroOperacionProgramadaKey() {
		return nroOperacionProgramadaKey;
	}

	/**
	 * @return the cbuKey
	 */
	public String getCbuKey() {
		return cbuKey;
	}

	/**
	 * @return the aliasCBUKey
	 */
	public String getAliasCBUKey() {
		return aliasCBUKey;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.web.view.
	 * DetalleComprobanteView#obtenerParametrosPDF()
	 */
	@Override
	public HashMap<String, Object> obtenerParametrosPDF() {
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		ponerParametrosTransferencia(parametros);
		return parametros;
	}

	/**
	 * Poner parametros transferencia.
	 *
	 * @param parametros
	 *            the parametros
	 */
	protected void ponerParametrosTransferencia(HashMap<String, Object> parametros) {
		parametros.put(tituloKey, getDestinatario());
		parametros.put(importeKey, obtenerImporte());
		parametros.put(numeroOrigenKey, getNroCuentaOrigen());
		parametros.put(tipoOrigenKey, getTipoCuentaOrigen());
		parametros.put(numeroDestinoKey, getNroCuentaDestino());
		parametros.put(tipoDestinoKey, getTipoCuentaDestino());
		parametros.put(bancoKey, getBanco());
		parametros.put(destinatarioKey, getDestinatario());
		parametros.put(fechaEjecucionKey, getFecha());
		parametros.put(conceptoKey, getConcepto());
		parametros.put(descripcionKey, getDesConcepto());
		parametros.put(plazoAcreditacionKey, getPlazoAcreditacion());
		parametros.put(numeroComprobanteKey, getNroComprobante());
		parametros.put(avisoKey, getAvisoTransfMail());
		parametros.put(emailKey, getMail());
		parametros.put(mensajeKey, getComentarios());
		parametros.put(cuitKey, getCuit());
		parametros.put(tipoCuitKey, getTipoCuit());
		parametros.put(fechaActualKey, getFechaOperacion());
		parametros.put(cuentaKey, getCuenta());
		parametros.put(tipoCuentaKey, getTipoCuenta());
		parametros.put(nroOperacionProgramadaKey, getNroOperacionProgramada());
		parametros.put(cbuKey, getCbu());
		parametros.put(aliasCBUKey, getAliasCBU());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.web.view.
	 * DetalleComprobanteView#obtenerJasper()
	 */
	@Override
	public String obtenerJasper() throws FileNotFoundException {
		if (esPagoDeHaberes) {
			return ResourceUtils.getFile(path + paghabonJasper).getPath();
		}
		return ResourceUtils.getFile(path + transferenciasJasper).getPath();
	}
}
