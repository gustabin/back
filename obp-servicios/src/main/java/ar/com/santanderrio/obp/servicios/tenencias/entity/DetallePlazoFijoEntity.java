/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tenencias.entity;

/**
 * The Class DetallePlazoFijoEntity.
 */
public class DetallePlazoFijoEntity {

	/** The anio. */
	private String anio;

	/** The pecodpro. */
	private String pecodpro;

	/** The pecodsub. */
	private String pecodsub;

	/** The pecodofi. */
	private String pecodofi;

	/** The penumcon. */
	private String penumcon;

	/** The certificado. */
	private String certificado;

	/** The divisa. */
	private String divisa;

	/** The saldo. */
	private String saldo;

	/** The int cobrado. */
	private String intCobrado;

	/** The aju cobrado. */
	private String ajuCobrado;

	/** The int devengado. */
	private String intDevengado;

	/** The aju devengado. */
	private String ajuDevengado;

	/** The ret ganancias. */
	private String retGanancias;

	/** The estado. */
	private String estado;

	/** The cuenta debito tipo. */
	private String cuentaDebitoTipo;

	/** The cuenta debito suc. */
	private String cuentaDebitoSuc;

	/** The cuenta debito nro. */
	private String cuentaDebitoNro;

	/** The cuenta credito tipo. */
	private String cuentaCreditoTipo;

	/** The cuenta credito suc. */
	private String cuentaCreditoSuc;

	/** The cuenta credito nro. */
	private String cuentaCreditoNro;

	/** The plazo. */
	private String plazo;

	/** The fecha vencimiento. */
	private String fechaVencimiento;

	/** The fecha constitucion. */
	private String fechaConstitucion;

	/** The tipo de plazo fijo. */
	private String tipo;

	/** The capital invertido. */
	private String capitalInvertido;

	/** The tasa nom anual. */
	private String tasaNominalAnual;

	/** The tasa efectiva anual */
	private String tasaEfectivaAnual;

	/** The interesesTotales */
	private String interesesTotales;

	/** The accion al vencimiento */
	private String accionAlVencimiento;

	/** The frecuencia cobro */
	private String frecuenciaCobroInteres;

	/** The frecuencia precancelacion */
	private String frecuenciaPrecancelacion;

	/** The fecha Minina cancelacion */
	private String fechaMinimaCancelacion;

	/** The fecha solicitud cancelacion */
	private String fechaSolicitudCancelacion;

	/** The fecha liquidacion */
	private String fechaLiquidacion;

	/** The clausula ajuste */
	private String clausulaAjuste;

	/** The impuestos */
	private String impuestos;

	/** The custodia */
	private String custodia;

	/** The modalidad */
	private String modalidad;

	/** The participantes */
	private String Participante;

	/** The cuenta Inversor */
	private String cuentaInversor;
	
	/** The cuenta Inversor */
	private String nombrePlazoFijo;

	/**
	 * @return the nombrePlazoFijo
	 */
	public String getNombrePlazoFijo() {
		return nombrePlazoFijo;
	}

	/**
	 * @param nombrePlazoFijo the nombrePlazoFijo to set
	 */
	public void setNombrePlazoFijo(String nombrePlazoFijo) {
		this.nombrePlazoFijo = nombrePlazoFijo;
	}

	/**
	 * @return the cuentaInversor
	 */
	public String getCuentaInversor() {
		return cuentaInversor;
	}

	/**
	 * @param cuentaInversor the cuentaInversor to set
	 */
	public void setCuentaInversor(String cuentaInversor) {
		this.cuentaInversor = cuentaInversor;
	}

	/**
	 * @return the capitalInvertido
	 */
	public String getCapitalInvertido() {
		return capitalInvertido;
	}

	/**
	 * @param capitalInvertido the capitalInvertido to set
	 */
	public void setCapitalInvertido(String capitalInvertido) {
		this.capitalInvertido = capitalInvertido;
	}

	/**
	 * @return the tasaNominalAnual
	 */
	public String getTasaNominalAnual() {
		return tasaNominalAnual;
	}

	/**
	 * @param tasaNominalAnual the tasaNominalAnual to set
	 */
	public void setTasaNominalAnual(String tasaNominalAnual) {
		this.tasaNominalAnual = tasaNominalAnual;
	}

	/**
	 * @return the tasaEfectivaAnual
	 */
	public String getTasaEfectivaAnual() {
		return tasaEfectivaAnual;
	}

	/**
	 * @param tasaEfectivaAnual the tasaEfectivaAnual to set
	 */
	public void setTasaEfectivaAnual(String tasaEfectivaAnual) {
		this.tasaEfectivaAnual = tasaEfectivaAnual;
	}

	/**
	 * @return the interesesTotales
	 */
	public String getInteresesTotales() {
		return interesesTotales;
	}

	/**
	 * @param interesesTotales the interesesTotales to set
	 */
	public void setInteresesTotales(String interesesTotales) {
		this.interesesTotales = interesesTotales;
	}

	/**
	 * @return the accionAlVencimiento
	 */
	public String getAccionAlVencimiento() {
		return accionAlVencimiento;
	}

	/**
	 * @param accionAlVencimiento the accionAlVencimiento to set
	 */
	public void setAccionAlVencimiento(String accionAlVencimiento) {
		this.accionAlVencimiento = accionAlVencimiento;
	}

	/**
	 * @return the frecuenciaCobroInteres
	 */
	public String getFrecuenciaCobroInteres() {
		return frecuenciaCobroInteres;
	}

	/**
	 * @param frecuenciaCobroInteres the frecuenciaCobroInteres to set
	 */
	public void setFrecuenciaCobroInteres(String frecuenciaCobroInteres) {
		this.frecuenciaCobroInteres = frecuenciaCobroInteres;
	}

	/**
	 * @return the frecuenciaPrecancelacion
	 */
	public String getFrecuenciaPrecancelacion() {
		return frecuenciaPrecancelacion;
	}

	/**
	 * @param frecuenciaPrecancelacion the frecuenciaPrecancelacion to set
	 */
	public void setFrecuenciaPrecancelacion(String frecuenciaPrecancelacion) {
		this.frecuenciaPrecancelacion = frecuenciaPrecancelacion;
	}

	/**
	 * @return the fechaMinimaCancelacion
	 */
	public String getFechaMinimaCancelacion() {
		return fechaMinimaCancelacion;
	}

	/**
	 * @param fechaMinimaCancelacion the fechaMinimaCancelacion to set
	 */
	public void setFechaMinimaCancelacion(String fechaMinimaCancelacion) {
		this.fechaMinimaCancelacion = fechaMinimaCancelacion;
	}

	/**
	 * @return the fechaSolicitudCancelacion
	 */
	public String getFechaSolicitudCancelacion() {
		return fechaSolicitudCancelacion;
	}

	/**
	 * @param fechaSolicitudCancelacion the fechaSolicitudCancelacion to set
	 */
	public void setFechaSolicitudCancelacion(String fechaSolicitudCancelacion) {
		this.fechaSolicitudCancelacion = fechaSolicitudCancelacion;
	}

	/**
	 * @return the fechaLiquidacion
	 */
	public String getFechaLiquidacion() {
		return fechaLiquidacion;
	}

	/**
	 * @param fechaLiquidacion the fechaLiquidacion to set
	 */
	public void setFechaLiquidacion(String fechaLiquidacion) {
		this.fechaLiquidacion = fechaLiquidacion;
	}

	/**
	 * @return the clausulaAjuste
	 */
	public String getClausulaAjuste() {
		return clausulaAjuste;
	}

	/**
	 * @param clausulaAjuste the clausulaAjuste to set
	 */
	public void setClausulaAjuste(String clausulaAjuste) {
		this.clausulaAjuste = clausulaAjuste;
	}

	/**
	 * @return the impuestos
	 */
	public String getImpuestos() {
		return impuestos;
	}

	/**
	 * @param impuestos the impuestos to set
	 */
	public void setImpuestos(String impuestos) {
		this.impuestos = impuestos;
	}

	/**
	 * @return the custodia
	 */
	public String getCustodia() {
		return custodia;
	}

	/**
	 * @param custodia the custodia to set
	 */
	public void setCustodia(String custodia) {
		this.custodia = custodia;
	}

	/**
	 * @return the modalidad
	 */
	public String getModalidad() {
		return modalidad;
	}

	/**
	 * @param modalidad the modalidad to set
	 */
	public void setModalidad(String modalidad) {
		this.modalidad = modalidad;
	}

	/**
	 * @return the participante
	 */
	public String getParticipante() {
		return Participante;
	}

	/**
	 * @param participante the participante to set
	 */
	public void setParticipante(String participante) {
		Participante = participante;
	}

	/**
	 * Gets the anio.
	 *
	 * @return the anio
	 */
	public String getAnio() {
		return anio;
	}

	/**
	 * Sets the anio.
	 *
	 * @param anio the anio to set
	 */
	public void setAnio(String anio) {
		this.anio = anio;
	}

	/**
	 * Gets the pecodpro.
	 *
	 * @return the pecodpro
	 */
	public String getPecodpro() {
		return pecodpro;
	}

	/**
	 * Sets the pecodpro.
	 *
	 * @param pecodpro the pecodpro to set
	 */
	public void setPecodpro(String pecodpro) {
		this.pecodpro = pecodpro;
	}

	/**
	 * Gets the pecodsub.
	 *
	 * @return the pecodsub
	 */
	public String getPecodsub() {
		return pecodsub;
	}

	/**
	 * Sets the pecodsub.
	 *
	 * @param pecodsub the pecodsub to set
	 */
	public void setPecodsub(String pecodsub) {
		this.pecodsub = pecodsub;
	}

	/**
	 * Gets the pecodofi.
	 *
	 * @return the pecodofi
	 */
	public String getPecodofi() {
		return pecodofi;
	}

	/**
	 * Sets the pecodofi.
	 *
	 * @param pecodofi the pecodofi to set
	 */
	public void setPecodofi(String pecodofi) {
		this.pecodofi = pecodofi;
	}

	/**
	 * Gets the penumcon.
	 *
	 * @return the penumcon
	 */
	public String getPenumcon() {
		return penumcon;
	}

	/**
	 * Sets the penumcon.
	 *
	 * @param penumcon the penumcon to set
	 */
	public void setPenumcon(String penumcon) {
		this.penumcon = penumcon;
	}

	/**
	 * Gets the certificado.
	 *
	 * @return the certificado
	 */
	public String getCertificado() {
		return certificado;
	}

	/**
	 * Sets the certificado.
	 *
	 * @param certificado the certificado to set
	 */
	public void setCertificado(String certificado) {
		this.certificado = certificado;
	}

	/**
	 * Gets the divisa.
	 *
	 * @return the divisa
	 */
	public String getDivisa() {
		return divisa;
	}

	/**
	 * Sets the divisa.
	 *
	 * @param divisa the divisa to set
	 */
	public void setDivisa(String divisa) {
		this.divisa = divisa;
	}

	/**
	 * Gets the saldo.
	 *
	 * @return the saldo
	 */
	public String getSaldo() {
		return saldo;
	}

	/**
	 * Sets the saldo.
	 *
	 * @param saldo the saldo to set
	 */
	public void setSaldo(String saldo) {
		this.saldo = saldo;
	}

	/**
	 * Gets the int cobrado.
	 *
	 * @return the intCobrado
	 */
	public String getIntCobrado() {
		return intCobrado;
	}

	/**
	 * Sets the int cobrado.
	 *
	 * @param intCobrado the intCobrado to set
	 */
	public void setIntCobrado(String intCobrado) {
		this.intCobrado = intCobrado;
	}

	/**
	 * Gets the aju cobrado.
	 *
	 * @return the ajuCobrado
	 */
	public String getAjuCobrado() {
		return ajuCobrado;
	}

	/**
	 * Sets the aju cobrado.
	 *
	 * @param ajuCobrado the ajuCobrado to set
	 */
	public void setAjuCobrado(String ajuCobrado) {
		this.ajuCobrado = ajuCobrado;
	}

	/**
	 * Gets the int devengado.
	 *
	 * @return the intDevengado
	 */
	public String getIntDevengado() {
		return intDevengado;
	}

	/**
	 * Sets the int devengado.
	 *
	 * @param intDevengado the intDevengado to set
	 */
	public void setIntDevengado(String intDevengado) {
		this.intDevengado = intDevengado;
	}

	/**
	 * Gets the aju devengado.
	 *
	 * @return the ajuDevengado
	 */
	public String getAjuDevengado() {
		return ajuDevengado;
	}

	/**
	 * Sets the aju devengado.
	 *
	 * @param ajuDevengado the ajuDevengado to set
	 */
	public void setAjuDevengado(String ajuDevengado) {
		this.ajuDevengado = ajuDevengado;
	}

	/**
	 * Gets the ret ganancias.
	 *
	 * @return the retGanancias
	 */
	public String getRetGanancias() {
		return retGanancias;
	}

	/**
	 * Sets the ret ganancias.
	 *
	 * @param retGanancias the retGanancias to set
	 */
	public void setRetGanancias(String retGanancias) {
		this.retGanancias = retGanancias;
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
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * Gets the cuenta debito tipo.
	 *
	 * @return the cuentaDebitoTipo
	 */
	public String getCuentaDebitoTipo() {
		return cuentaDebitoTipo;
	}

	/**
	 * Sets the cuenta debito tipo.
	 *
	 * @param cuentaDebitoTipo the cuentaDebitoTipo to set
	 */
	public void setCuentaDebitoTipo(String cuentaDebitoTipo) {
		this.cuentaDebitoTipo = cuentaDebitoTipo;
	}

	/**
	 * Gets the cuenta debito suc.
	 *
	 * @return the cuentaDebitoSuc
	 */
	public String getCuentaDebitoSuc() {
		return cuentaDebitoSuc;
	}

	/**
	 * Sets the cuenta debito suc.
	 *
	 * @param cuentaDebitoSuc the cuentaDebitoSuc to set
	 */
	public void setCuentaDebitoSuc(String cuentaDebitoSuc) {
		this.cuentaDebitoSuc = cuentaDebitoSuc;
	}

	/**
	 * Gets the cuenta debito nro.
	 *
	 * @return the cuentaDebitoNro
	 */
	public String getCuentaDebitoNro() {
		return cuentaDebitoNro;
	}

	/**
	 * Sets the cuenta debito nro.
	 *
	 * @param cuentaDebitoNro the cuentaDebitoNro to set
	 */
	public void setCuentaDebitoNro(String cuentaDebitoNro) {
		this.cuentaDebitoNro = cuentaDebitoNro;
	}

	/**
	 * Gets the cuenta credito tipo.
	 *
	 * @return the cuentaCreditoTipo
	 */
	public String getCuentaCreditoTipo() {
		return cuentaCreditoTipo;
	}

	/**
	 * Sets the cuenta credito tipo.
	 *
	 * @param cuentaCreditoTipo the cuentaCreditoTipo to set
	 */
	public void setCuentaCreditoTipo(String cuentaCreditoTipo) {
		this.cuentaCreditoTipo = cuentaCreditoTipo;
	}

	/**
	 * Gets the cuenta credito suc.
	 *
	 * @return the cuentaCreditoSuc
	 */
	public String getCuentaCreditoSuc() {
		return cuentaCreditoSuc;
	}

	/**
	 * Sets the cuenta credito suc.
	 *
	 * @param cuentaCreditoSuc the cuentaCreditoSuc to set
	 */
	public void setCuentaCreditoSuc(String cuentaCreditoSuc) {
		this.cuentaCreditoSuc = cuentaCreditoSuc;
	}

	/**
	 * Gets the cuenta credito nro.
	 *
	 * @return the cuentaCreditoNro
	 */
	public String getCuentaCreditoNro() {
		return cuentaCreditoNro;
	}

	/**
	 * Sets the cuenta credito nro.
	 *
	 * @param cuentaCreditoNro the cuentaCreditoNro to set
	 */
	public void setCuentaCreditoNro(String cuentaCreditoNro) {
		this.cuentaCreditoNro = cuentaCreditoNro;
	}

	/**
	 * Gets the plazo.
	 *
	 * @return the plazo
	 */
	public String getPlazo() {
		return plazo;
	}

	/**
	 * Sets the plazo.
	 *
	 * @param plazo the plazo to set
	 */
	public void setPlazo(String plazo) {
		this.plazo = plazo;
	}

	/**
	 * Gets the fecha vencimiento.
	 *
	 * @return the fechaVencimiento
	 */
	public String getFechaVencimiento() {
		return fechaVencimiento;
	}

	/**
	 * Sets the fecha vencimiento.
	 *
	 * @param fechaVencimiento the fechaVencimiento to set
	 */
	public void setFechaVencimiento(String fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	/**
	 * Gets the fecha constitucion.
	 *
	 * @return the fechaConstitucion
	 */
	public String getFechaConstitucion() {
		return fechaConstitucion;
	}

	/**
	 * Sets the fecha constitucion.
	 *
	 * @param fechaConstitucion the fechaConstitucion to set
	 */
	public void setFechaConstitucion(String fechaConstitucion) {
		this.fechaConstitucion = fechaConstitucion;
	}

	/**
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DetallePlazoFijoEntity [anio=" + anio + ", pecodpro=" + pecodpro + ", pecodsub=" + pecodsub
				+ ", pecodofi=" + pecodofi + ", penumcon=" + penumcon + ", certificado=" + certificado + ", divisa="
				+ divisa + ", saldo=" + saldo + ", intCobrado=" + intCobrado + ", ajuCobrado=" + ajuCobrado
				+ ", intDevengado=" + intDevengado + ", ajuDevengado=" + ajuDevengado + ", retGanancias=" + retGanancias
				+ ", estado=" + estado + ", cuentaDebitoTipo=" + cuentaDebitoTipo + ", cuentaDebitoSuc="
				+ cuentaDebitoSuc + ", cuentaDebitoNro=" + cuentaDebitoNro + ", cuentaCreditoTipo=" + cuentaCreditoTipo
				+ ", cuentaCreditoSuc=" + cuentaCreditoSuc + ", cuentaCreditoNro=" + cuentaCreditoNro + ", plazo="
				+ plazo + ", fechaVencimiento=" + fechaVencimiento + ", fechaConstitucion=" + fechaConstitucion
				+ ", tipo=" + tipo + "]";
	}

}
