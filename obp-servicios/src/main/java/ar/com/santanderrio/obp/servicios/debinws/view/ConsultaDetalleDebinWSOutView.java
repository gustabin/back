package ar.com.santanderrio.obp.servicios.debinws.view;

/**
 * The Class ConsultaDetalleDebinWSOutView.
 */
public class ConsultaDetalleDebinWSOutView {

	/** The importe. */
	private String importe;

	/** The moneda. */
	private String moneda;

	/** The estado. */
	private String estado;

	/** The fecha solicitud. */
	private String fechaSolicitud;

	/** The fecha vencimiento. */
	private String fechaVencimiento;

	/** The cuenta debito alias. */
	private String cuentaDebitoAlias;

	/** The cuenta debito descripcion. */
	private String cuentaDebitoDescripcion;

	/** The cuenta debito numero. */
	private String cuentaDebitoNumero;

	/** The nombre solicitante. */
	private String nombreSolicitante;

	/** The cuit solicitante. */
	private String cuitSolicitante;

	/** The cbu solicitante. */
	private String cbuSolicitante;

	/** The alias solicitante. */
	private String aliasSolicitante;

	/** The descripcion. */
	private String descripcion;

	/** The concepto. */
	private String concepto;

	/** The debin id. */
	private String debinId;

	/** The nombre destinatario. */
	private String nombreDestinatario;

	/** The cuenta acreditacion alias. */
	private String cuentaAcreditacionAlias;

	/** The cuenta acreditacion descripcion. */
	private String cuentaAcreditacionDescripcion;

	/** The cuenta acreditacion numero. */
	private String cuentaAcreditacionNumero;

	/** The cuenta continuadora debito numero. */
	private String cuentaContinuadoraDebitoNumero;

	/** The cuit destinatario. */
	private String cuitDestinatario;

	/** The cbu destinatario. */
	private String cbuDestinatario;

	/** The alias destinatario. */
	private String aliasDestinatario;

	/** The mensaje sin desafio. */
	private String mensajeSinDesafio;

	/** The url ayuda. */
	private String urlAyuda;

	/** The tiene metodo desafio. */
	private boolean tieneMetodoDesafio;

	/** The permite pagar. */
	private boolean permitePagar;

	/** The permite rechazar. */
	private boolean permiteRechazar;

	/** The permite eliminar. */
	private boolean permiteEliminar;
	
	/** The is concepto valido. */
	private boolean isConceptoValido;
	
	private boolean isDebinRec;

	/**
	 * Gets the cbu destinatario.
	 *
	 * @return the cbu destinatario
	 */
	public String getCbuDestinatario() {
		return cbuDestinatario;
	}

	/**
	 * Sets the cbu destinatario.
	 *
	 * @param cbuDestinatario the new cbu destinatario
	 */
	public void setCbuDestinatario(String cbuDestinatario) {
		this.cbuDestinatario = cbuDestinatario;
	}

	/**
	 * Gets the alias destinatario.
	 *
	 * @return the alias destinatario
	 */
	public String getAliasDestinatario() {
		return aliasDestinatario;
	}

	/**
	 * Sets the alias destinatario.
	 *
	 * @param aliasDestinatario the new alias destinatario
	 */
	public void setAliasDestinatario(String aliasDestinatario) {
		this.aliasDestinatario = aliasDestinatario;
	}

	/**
	 * Gets the nombre destinatario.
	 *
	 * @return the nombre destinatario
	 */
	public String getNombreDestinatario() {
		return nombreDestinatario;
	}

	/**
	 * Sets the nombre destinatario.
	 *
	 * @param nombreDestinatario the new nombre destinatario
	 */
	public void setNombreDestinatario(String nombreDestinatario) {
		this.nombreDestinatario = nombreDestinatario;
	}

	/**
	 * Gets the cuit destinatario.
	 *
	 * @return the cuit destinatario
	 */
	public String getCuitDestinatario() {
		return cuitDestinatario;
	}

	/**
	 * Sets the cuit destinatario.
	 *
	 * @param cuitDestinatario the new cuit destinatario
	 */
	public void setCuitDestinatario(String cuitDestinatario) {
		this.cuitDestinatario = cuitDestinatario;
	}

	/**
	 * Gets the cuenta acreditacion alias.
	 *
	 * @return the cuenta acreditacion alias
	 */
	public String getCuentaAcreditacionAlias() {
		return cuentaAcreditacionAlias;
	}

	/**
	 * Sets the cuenta acreditacion alias.
	 *
	 * @param cuentaAcreditacionAlias the new cuenta acreditacion alias
	 */
	public void setCuentaAcreditacionAlias(String cuentaAcreditacionAlias) {
		this.cuentaAcreditacionAlias = cuentaAcreditacionAlias;
	}

	/**
	 * Gets the cuenta acreditacion descripcion.
	 *
	 * @return the cuenta acreditacion descripcion
	 */
	public String getCuentaAcreditacionDescripcion() {
		return cuentaAcreditacionDescripcion;
	}

	/**
	 * Sets the cuenta acreditacion descripcion.
	 *
	 * @param cuentaAcreditacionDescripcion the new cuenta acreditacion descripcion
	 */
	public void setCuentaAcreditacionDescripcion(String cuentaAcreditacionDescripcion) {
		this.cuentaAcreditacionDescripcion = cuentaAcreditacionDescripcion;
	}

	/**
	 * Gets the cuenta acreditacion numero.
	 *
	 * @return the cuenta acreditacion numero
	 */
	public String getCuentaAcreditacionNumero() {
		return cuentaAcreditacionNumero;
	}

	/**
	 * Sets the cuenta acreditacion numero.
	 *
	 * @param cuentaAcreditacionNumero the new cuenta acreditacion numero
	 */
	public void setCuentaAcreditacionNumero(String cuentaAcreditacionNumero) {
		this.cuentaAcreditacionNumero = cuentaAcreditacionNumero;
	}

	/**
	 * Gets the cuenta continuadora debito numero.
	 *
	 * @return the cuenta continuadora debito numero
	 */
	public String getCuentaContinuadoraDebitoNumero() {
		return cuentaContinuadoraDebitoNumero;
	}

	/**
	 * Sets the cuenta continuadora debito numero.
	 *
	 * @param cuentaContinuadoraDebitoNumero the new cuenta continuadora debito numero
	 */
	public void setCuentaContinuadoraDebitoNumero(String cuentaContinuadoraDebitoNumero) {
		this.cuentaContinuadoraDebitoNumero = cuentaContinuadoraDebitoNumero;
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
	 * @param importe the new importe
	 */
	public void setImporte(String importe) {
		this.importe = importe;
	}

	/**
	 * Checks if is permite pagar.
	 *
	 * @return true, if is permite pagar
	 */
	public boolean isPermitePagar() {
		return permitePagar;
	}

	/**
	 * Sets the permite pagar.
	 *
	 * @param permitePagar the new permite pagar
	 */
	public void setPermitePagar(boolean permitePagar) {
		this.permitePagar = permitePagar;
	}

	/**
	 * Checks if is permite rechazar.
	 *
	 * @return true, if is permite rechazar
	 */
	public boolean isPermiteRechazar() {
		return permiteRechazar;
	}

	/**
	 * Sets the permite rechazar.
	 *
	 * @param permiteRechazar the new permite rechazar
	 */
	public void setPermiteRechazar(boolean permiteRechazar) {
		this.permiteRechazar = permiteRechazar;
	}

	/**
	 * Checks if is permite eliminar.
	 *
	 * @return true, if is permite eliminar
	 */
	public boolean isPermiteEliminar() {
		return permiteEliminar;
	}

	/**
	 * Sets the permite eliminar.
	 *
	 * @param permiteEliminar the new permite eliminar
	 */
	public void setPermiteEliminar(boolean permiteEliminar) {
		this.permiteEliminar = permiteEliminar;
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
	 * @param estado the new estado
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * Gets the fecha solicitud.
	 *
	 * @return the fecha solicitud
	 */
	public String getFechaSolicitud() {
		return fechaSolicitud;
	}

	/**
	 * Sets the fecha solicitud.
	 *
	 * @param fechaSolicitud the new fecha solicitud
	 */
	public void setFechaSolicitud(String fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}

	/**
	 * Gets the fecha vencimiento.
	 *
	 * @return the fecha vencimiento
	 */
	public String getFechaVencimiento() {
		return fechaVencimiento;
	}

	/**
	 * Sets the fecha vencimiento.
	 *
	 * @param fechaVencimiento the new fecha vencimiento
	 */
	public void setFechaVencimiento(String fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	/**
	 * Gets the cuenta debito descripcion.
	 *
	 * @return the cuenta debito descripcion
	 */
	public String getCuentaDebitoDescripcion() {
		return cuentaDebitoDescripcion;
	}

	/**
	 * Sets the cuenta debito descripcion.
	 *
	 * @param cuentaDebitoDescripcion the new cuenta debito descripcion
	 */
	public void setCuentaDebitoDescripcion(String cuentaDebitoDescripcion) {
		this.cuentaDebitoDescripcion = cuentaDebitoDescripcion;
	}

	/**
	 * Gets the cuenta debito numero.
	 *
	 * @return the cuenta debito numero
	 */
	public String getCuentaDebitoNumero() {
		return cuentaDebitoNumero;
	}

	/**
	 * Sets the cuenta debito numero.
	 *
	 * @param cuentaDebitoNumero the new cuenta debito numero
	 */
	public void setCuentaDebitoNumero(String cuentaDebitoNumero) {
		this.cuentaDebitoNumero = cuentaDebitoNumero;
	}

	/**
	 * Gets the cuit solicitante.
	 *
	 * @return the cuit solicitante
	 */
	public String getCuitSolicitante() {
		return cuitSolicitante;
	}

	/**
	 * Sets the cuit solicitante.
	 *
	 * @param cuitSolicitante the new cuit solicitante
	 */
	public void setCuitSolicitante(String cuitSolicitante) {
		this.cuitSolicitante = cuitSolicitante;
	}

	/**
	 * Gets the nombre solicitante.
	 *
	 * @return the nombre solicitante
	 */
	public String getNombreSolicitante() {
		return nombreSolicitante;
	}

	/**
	 * Sets the nombre solicitante.
	 *
	 * @param nombreSolicitante the new nombre solicitante
	 */
	public void setNombreSolicitante(String nombreSolicitante) {
		this.nombreSolicitante = nombreSolicitante;
	}

	/**
	 * Gets the cbu solicitante.
	 *
	 * @return the cbu solicitante
	 */
	public String getCbuSolicitante() {
		return cbuSolicitante;
	}

	/**
	 * Sets the cbu solicitante.
	 *
	 * @param cbuSolicitante the new cbu solicitante
	 */
	public void setCbuSolicitante(String cbuSolicitante) {
		this.cbuSolicitante = cbuSolicitante;
	}

	/**
	 * Gets the moneda.
	 *
	 * @return the moneda
	 */
	public String getMoneda() {
		return moneda;
	}

	/**
	 * Sets the moneda.
	 *
	 * @param moneda the new moneda
	 */
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	/**
	 * Gets the alias solicitante.
	 *
	 * @return the alias solicitante
	 */
	public String getAliasSolicitante() {
		return aliasSolicitante;
	}

	/**
	 * Sets the alias solicitante.
	 *
	 * @param aliasSolicitante the new alias solicitante
	 */
	public void setAliasSolicitante(String aliasSolicitante) {
		this.aliasSolicitante = aliasSolicitante;
	}

	/**
	 * Gets the descripcion.
	 *
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Sets the descripcion.
	 *
	 * @param descripcion the new descripcion
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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
	 * @param concepto the new concepto
	 */
	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	/**
	 * Gets the debin id.
	 *
	 * @return the debin id
	 */
	public String getDebinId() {
		return debinId;
	}

	/**
	 * Sets the debin id.
	 *
	 * @param debinId the new debin id
	 */
	public void setDebinId(String debinId) {
		this.debinId = debinId;
	}

	/**
	 * Gets the cuenta debito alias.
	 *
	 * @return the cuenta debito alias
	 */
	public String getCuentaDebitoAlias() {
		return cuentaDebitoAlias;
	}

	/**
	 * Sets the cuenta debito alias.
	 *
	 * @param cuentaDebitoAlias the new cuenta debito alias
	 */
	public void setCuentaDebitoAlias(String cuentaDebitoAlias) {
		this.cuentaDebitoAlias = cuentaDebitoAlias;
	}

	/**
	 * Gets the mensaje sin desafio.
	 *
	 * @return the mensaje sin desafio
	 */
	public String getMensajeSinDesafio() {
		return mensajeSinDesafio;
	}

	/**
	 * Sets the mensaje sin desafio.
	 *
	 * @param mensajeSinDesafio the new mensaje sin desafio
	 */
	public void setMensajeSinDesafio(String mensajeSinDesafio) {
		this.mensajeSinDesafio = mensajeSinDesafio;
	}

	/**
	 * Gets the url ayuda.
	 *
	 * @return the url ayuda
	 */
	public String getUrlAyuda() {
		return urlAyuda;
	}

	/**
	 * Sets the url ayuda.
	 *
	 * @param urlAyuda the new url ayuda
	 */
	public void setUrlAyuda(String urlAyuda) {
		this.urlAyuda = urlAyuda;
	}

	/**
	 * Gets the tiene metodo desafio.
	 *
	 * @return the tiene metodo desafio
	 */
	public boolean getTieneMetodoDesafio() {
		return tieneMetodoDesafio;
	}

	/**
	 * Sets the tiene metodo desafio.
	 *
	 * @param tieneMetodoDesafio the new tiene metodo desafio
	 */
	public void setTieneMetodoDesafio(boolean tieneMetodoDesafio) {
		this.tieneMetodoDesafio = tieneMetodoDesafio;
	}

	/**
	 * Checks if is concepto valido.
	 *
	 * @return true, if is concepto valido
	 */
	public boolean isConceptoValido() {
		return isConceptoValido;
	}

	/**
	 * Sets the concepto valido.
	 *
	 * @param isConceptoValido
	 *            the new concepto valido
	 */
	public void setConceptoValido(boolean isConceptoValido) {
		this.isConceptoValido = isConceptoValido;
	}

	public boolean getIsDebinRec() {
		return isDebinRec;
	}

	public void setIsDebinRec(boolean isDebinRec) {
		this.isDebinRec = isDebinRec;
	}

}
