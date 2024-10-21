/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class TenenciaValuadaPlazoFijoEntity.
 */
public class TenenciaValuadaPlazoFijoEntity {

	/** The tipo. */
	private String tipo;

	/** The fecha vencimiento. */
	private String fechaVencimiento;

	/** The capital inicial. */
	private String capitalInicial;

	/** The capital inicio Expresadi Indice. */
	private String capitalInicioExpresadoIndice;

	/** The tna. */
	private String tna;

	/** The tenencia valuada hasta hoy. */
	private String tenenciaValuadaHastaHoy;

	/** The resultado. */
	private String resultado;

	/** The prioridad. */
	private String prioridad;

	/** The tenencia valuada. */
	private String tenenciaValuada;

	/** The impuestos. */
	private List<MontoPlazoFijo> impuestos = new ArrayList<MontoPlazoFijo>();

	/** The intereses. */
	private List<MontoPlazoFijo> intereses = new ArrayList<MontoPlazoFijo>();

	/** The fecha constitucion. */
	private String fechaConstitucion;

	/** The nombre accion al vencimiento. */
	private String nombreAccionAlVencimiento;

	/** The numero cuenta destino. */
	private String numeroCuentaDestino;

	/** The codigo accion. */
	private String codigoAccion;

	/** The sucursal radicacion. */
	private String sucursalRadicacion;

	/** The canal. */
	private String canal;

	/** The numero comprobante. */
	private String numeroComprobante;

	/** The plazo vigencia. */
	private String plazoVigencia;

	/** The numero cuenta debito. */
	private String numeroCuentaDebito;

	/** The descripcion tipo cuenta debito. */
	private String descripcionTipoCuentaDebito;

	/** The intereses netos de impuestos. */
	private String interesesNetosDeImpuestos;

	/** The interes. */
	private String interes;

	/** The frecuencia cobro interes. */
	private String frecuenciaCobroInteres;

	/** The cuenta plazo. */
	private String cuentaPlazo;

	/** The secuencia. */
	private String secuencia;

	/** The tipo cuenta destino. */
	private String tipoCuentaDestino;

	/** The descripcion tipo cuenta destino. */
	private String descripcionTipoCuentaDestino;

	/** The alias cuenta destino. */
	private String aliasCuentaDestino;

	/** The alias cuenta debito. */
	private String aliasCuentaDebito;

	/** The acciones al vencimiento. */
	private List<AccionAlVencimientoOutEntity> accionesAlVencimiento = new ArrayList<AccionAlVencimientoOutEntity>();

	/** The monto cobrar. */
	private String montoCobrar;

	/** The codigo plazo fijo. */
	private String codigoPlazoFijo;

	/** The impuesto CER. */
	private String impuestoCER;

	/** The retribucion fija. */
	private String retribucionFija;

	/**
	 * Gets the tipo.
	 *
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * Sets the tipo.
	 *
	 * @param tipo
	 *            the new tipo
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
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
	 * @param fechaVencimiento
	 *            the new fecha vencimiento
	 */
	public void setFechaVencimiento(String fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	/**
	 * Gets the capital inicial.
	 *
	 * @return the capital inicial
	 */
	public String getCapitalInicial() {
		return capitalInicial;
	}

	/**
	 * Sets the capital inicial.
	 *
	 * @param capitalInicial
	 *            the new capital inicial
	 */
	public void setCapitalInicial(String capitalInicial) {
		this.capitalInicial = capitalInicial;
	}

	/**
	 * Gets the capital inicio expresado indice.
	 *
	 * @return the capital inicio expresado indice
	 */
	public String getCapitalInicioExpresadoIndice() {
		return capitalInicioExpresadoIndice;
	}

	/**
	 * Sets the capital inicio expresado indice.
	 *
	 * @param capitalInicioExpresadoIndice
	 *            the new capital inicio expresado indice
	 */
	public void setCapitalInicioExpresadoIndice(String capitalInicioExpresadoIndice) {
		this.capitalInicioExpresadoIndice = capitalInicioExpresadoIndice;
	}

	/**
	 * Gets the tna.
	 *
	 * @return the tna
	 */
	public String getTna() {
		return tna;
	}

	/**
	 * Sets the tna.
	 *
	 * @param tna
	 *            the new tna
	 */
	public void setTna(String tna) {
		this.tna = tna;
	}

	/**
	 * Gets the tenencia valuada hasta hoy.
	 *
	 * @return the tenencia valuada hasta hoy
	 */
	public String getTenenciaValuadaHastaHoy() {
		return tenenciaValuadaHastaHoy;
	}

	/**
	 * Sets the tenencia valuada hasta hoy.
	 *
	 * @param tenenciaValuadaHastaHoy
	 *            the new tenencia valuada hasta hoy
	 */
	public void setTenenciaValuadaHastaHoy(String tenenciaValuadaHastaHoy) {
		this.tenenciaValuadaHastaHoy = tenenciaValuadaHastaHoy;
	}

	/**
	 * Gets the resultado.
	 *
	 * @return the resultado
	 */
	public String getResultado() {
		return resultado;
	}

	/**
	 * Sets the resultado.
	 *
	 * @param resultado
	 *            the new resultado
	 */
	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	/**
	 * Gets the prioridad.
	 *
	 * @return the prioridad
	 */
	public String getPrioridad() {
		return prioridad;
	}

	/**
	 * Sets the prioridad.
	 *
	 * @param prioridad
	 *            the new prioridad
	 */
	public void setPrioridad(String prioridad) {
		this.prioridad = prioridad;
	}

	/**
	 * Gets the tenencia valuada.
	 *
	 * @return the tenencia valuada
	 */
	public String getTenenciaValuada() {
		return tenenciaValuada;
	}

	/**
	 * Sets the tenencia valuada.
	 *
	 * @param tenenciaValuada
	 *            the new tenencia valuada
	 */
	public void setTenenciaValuada(String tenenciaValuada) {
		this.tenenciaValuada = tenenciaValuada;
	}

	/**
	 * Gets the impuestos.
	 *
	 * @return the impuestos
	 */
	public List<MontoPlazoFijo> getImpuestos() {
		return impuestos;
	}

	/**
	 * Sets the impuestos.
	 *
	 * @param impuestos
	 *            the new impuestos
	 */
	public void setImpuestos(List<MontoPlazoFijo> impuestos) {
		this.impuestos = impuestos;
	}

	/**
	 * Gets the intereses.
	 *
	 * @return the intereses
	 */
	public List<MontoPlazoFijo> getIntereses() {
		return intereses;
	}

	/**
	 * Sets the intereses.
	 *
	 * @param intereses
	 *            the new intereses
	 */
	public void setIntereses(List<MontoPlazoFijo> intereses) {
		this.intereses = intereses;
	}

	/**
	 * Gets the fecha constitucion.
	 *
	 * @return the fecha constitucion
	 */
	public String getFechaConstitucion() {
		return fechaConstitucion;
	}

	/**
	 * Sets the fecha constitucion.
	 *
	 * @param fechaConstitucion
	 *            the new fecha constitucion
	 */
	public void setFechaConstitucion(String fechaConstitucion) {
		this.fechaConstitucion = fechaConstitucion;
	}

	/**
	 * Gets the nombre accion al vencimiento.
	 *
	 * @return the nombre accion al vencimiento
	 */
	public String getNombreAccionAlVencimiento() {
		return nombreAccionAlVencimiento;
	}

	/**
	 * Sets the nombre accion al vencimiento.
	 *
	 * @param nombreAccionAlVencimiento
	 *            the new nombre accion al vencimiento
	 */
	public void setNombreAccionAlVencimiento(String nombreAccionAlVencimiento) {
		this.nombreAccionAlVencimiento = nombreAccionAlVencimiento;
	}

	/**
	 * Gets the numero cuenta destino.
	 *
	 * @return the numero cuenta destino
	 */
	public String getNumeroCuentaDestino() {
		return numeroCuentaDestino;
	}

	/**
	 * Sets the numero cuenta destino.
	 *
	 * @param numeroCuentaDestino
	 *            the new numero cuenta destino
	 */
	public void setNumeroCuentaDestino(String numeroCuentaDestino) {
		this.numeroCuentaDestino = numeroCuentaDestino;
	}

	/**
	 * Gets the codigo accion.
	 *
	 * @return the codigo accion
	 */
	public String getCodigoAccion() {
		return codigoAccion;
	}

	/**
	 * Sets the codigo accion.
	 *
	 * @param codigoAccion
	 *            the new codigo accion
	 */
	public void setCodigoAccion(String codigoAccion) {
		this.codigoAccion = codigoAccion;
	}

	/**
	 * Gets the sucursal radicacion.
	 *
	 * @return the sucursal radicacion
	 */
	public String getSucursalRadicacion() {
		return sucursalRadicacion;
	}

	/**
	 * Sets the sucursal radicacion.
	 *
	 * @param sucursalRadicacion
	 *            the new sucursal radicacion
	 */
	public void setSucursalRadicacion(String sucursalRadicacion) {
		this.sucursalRadicacion = sucursalRadicacion;
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
	 *            the new canal
	 */
	public void setCanal(String canal) {
		this.canal = canal;
	}

	/**
	 * Gets the numero comprobante.
	 *
	 * @return the numero comprobante
	 */
	public String getNumeroComprobante() {
		return numeroComprobante;
	}

	/**
	 * Sets the numero comprobante.
	 *
	 * @param numeroComprobante
	 *            the new numero comprobante
	 */
	public void setNumeroComprobante(String numeroComprobante) {
		this.numeroComprobante = numeroComprobante;
	}

	/**
	 * Gets the plazo vigencia.
	 *
	 * @return the plazo vigencia
	 */
	public String getPlazoVigencia() {
		return plazoVigencia;
	}

	/**
	 * Sets the plazo vigencia.
	 *
	 * @param plazoVigencia
	 *            the new plazo vigencia
	 */
	public void setPlazoVigencia(String plazoVigencia) {
		this.plazoVigencia = plazoVigencia;
	}

	/**
	 * Gets the numero cuenta debito.
	 *
	 * @return the numero cuenta debito
	 */
	public String getNumeroCuentaDebito() {
		return numeroCuentaDebito;
	}

	/**
	 * Sets the numero cuenta debito.
	 *
	 * @param numeroCuentaDebito
	 *            the new numero cuenta debito
	 */
	public void setNumeroCuentaDebito(String numeroCuentaDebito) {
		this.numeroCuentaDebito = numeroCuentaDebito;
	}

	/**
	 * Gets the descripcion tipo cuenta debito.
	 *
	 * @return the descripcion tipo cuenta debito
	 */
	public String getDescripcionTipoCuentaDebito() {
		return descripcionTipoCuentaDebito;
	}

	/**
	 * Sets the descripcion tipo cuenta debito.
	 *
	 * @param descripcionTipoCuentaDebito
	 *            the new descripcion tipo cuenta debito
	 */
	public void setDescripcionTipoCuentaDebito(String descripcionTipoCuentaDebito) {
		this.descripcionTipoCuentaDebito = descripcionTipoCuentaDebito;
	}

	/**
	 * Gets the intereses netos de impuestos.
	 *
	 * @return the intereses netos de impuestos
	 */
	public String getInteresesNetosDeImpuestos() {
		return interesesNetosDeImpuestos;
	}

	/**
	 * Sets the intereses netos de impuestos.
	 *
	 * @param interesesNetosDeImpuestos
	 *            the new intereses netos de impuestos
	 */
	public void setInteresesNetosDeImpuestos(String interesesNetosDeImpuestos) {
		this.interesesNetosDeImpuestos = interesesNetosDeImpuestos;
	}

	/**
	 * Gets the interes.
	 *
	 * @return the interes
	 */
	public String getInteres() {
		return interes;
	}

	/**
	 * Sets the interes.
	 *
	 * @param interes
	 *            the new interes
	 */
	public void setInteres(String interes) {
		this.interes = interes;
	}

	/**
	 * Gets the frecuencia cobro interes.
	 *
	 * @return the frecuencia cobro interes
	 */
	public String getFrecuenciaCobroInteres() {
		return frecuenciaCobroInteres;
	}

	/**
	 * Sets the frecuencia cobro interes.
	 *
	 * @param frecuenciaCobroInteres
	 *            the new frecuencia cobro interes
	 */
	public void setFrecuenciaCobroInteres(String frecuenciaCobroInteres) {
		this.frecuenciaCobroInteres = frecuenciaCobroInteres;
	}

	/**
	 * Gets the cuenta plazo.
	 *
	 * @return the cuenta plazo
	 */
	public String getCuentaPlazo() {
		return cuentaPlazo;
	}

	/**
	 * Sets the cuenta plazo.
	 *
	 * @param cuentaPlazo
	 *            the new cuenta plazo
	 */
	public void setCuentaPlazo(String cuentaPlazo) {
		this.cuentaPlazo = cuentaPlazo;
	}

	/**
	 * Gets the secuencia.
	 *
	 * @return the secuencia
	 */
	public String getSecuencia() {
		return secuencia;
	}

	/**
	 * Sets the secuencia.
	 *
	 * @param secuencia
	 *            the new secuencia
	 */
	public void setSecuencia(String secuencia) {
		this.secuencia = secuencia;
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
	 * Gets the descripcion tipo cuenta destino.
	 *
	 * @return the descripcion tipo cuenta destino
	 */
	public String getDescripcionTipoCuentaDestino() {
		return descripcionTipoCuentaDestino;
	}

	/**
	 * Sets the descripcion tipo cuenta destino.
	 *
	 * @param descripcionTipoCuentaDestino
	 *            the new descripcion tipo cuenta destino
	 */
	public void setDescripcionTipoCuentaDestino(String descripcionTipoCuentaDestino) {
		this.descripcionTipoCuentaDestino = descripcionTipoCuentaDestino;
	}

	/**
	 * Gets the alias cuenta destino.
	 *
	 * @return the alias cuenta destino
	 */
	public String getAliasCuentaDestino() {
		return aliasCuentaDestino;
	}

	/**
	 * Sets the alias cuenta destino.
	 *
	 * @param aliasCuentaDestino
	 *            the new alias cuenta destino
	 */
	public void setAliasCuentaDestino(String aliasCuentaDestino) {
		this.aliasCuentaDestino = aliasCuentaDestino;
	}

	/**
	 * Gets the alias cuenta debito.
	 *
	 * @return the alias cuenta debito
	 */
	public String getAliasCuentaDebito() {
		return aliasCuentaDebito;
	}

	/**
	 * Sets the alias cuenta debito.
	 *
	 * @param aliasCuentaDebito
	 *            the new alias cuenta debito
	 */
	public void setAliasCuentaDebito(String aliasCuentaDebito) {
		this.aliasCuentaDebito = aliasCuentaDebito;
	}

	/**
	 * Gets the acciones al vencimiento.
	 *
	 * @return the acciones al vencimiento
	 */
	public List<AccionAlVencimientoOutEntity> getAccionesAlVencimiento() {
		return accionesAlVencimiento;
	}

	/**
	 * Sets the acciones al vencimiento.
	 *
	 * @param accionesAlVencimiento
	 *            the new acciones al vencimiento
	 */
	public void setAccionesAlVencimiento(List<AccionAlVencimientoOutEntity> accionesAlVencimiento) {
		this.accionesAlVencimiento = accionesAlVencimiento;
	}

	/**
	 * Gets the monto cobrar.
	 *
	 * @return the monto cobrar
	 */
	public String getMontoCobrar() {
		return montoCobrar;
	}

	/**
	 * Sets the monto cobrar.
	 *
	 * @param montoCobrar
	 *            the new monto cobrar
	 */
	public void setMontoCobrar(String montoCobrar) {
		this.montoCobrar = montoCobrar;
	}

	/**
	 * Gets the codigo plazo fijo.
	 *
	 * @return the codigo plazo fijo
	 */
	public String getCodigoPlazoFijo() {
		return codigoPlazoFijo;
	}

	/**
	 * Sets the codigo plazo fijo.
	 *
	 * @param codigoPlazoFijo
	 *            the new codigo plazo fijo
	 */
	public void setCodigoPlazoFijo(String codigoPlazoFijo) {
		this.codigoPlazoFijo = codigoPlazoFijo;
	}

	/**
	 * Gets the impuesto CER.
	 *
	 * @return the impuesto CER
	 */
	public String getImpuestoCER() {
		return impuestoCER;
	}

	/**
	 * Sets the impuesto CER.
	 *
	 * @param impuestoCER
	 *            the new impuesto CER
	 */
	public void setImpuestoCER(String impuestoCER) {
		this.impuestoCER = impuestoCER;
	}

	/**
	 * Gets the retribucion fija.
	 *
	 * @return the retribucion fija
	 */
	public String getRetribucionFija() {
		return retribucionFija;
	}

	/**
	 * Sets the retribucion fija.
	 *
	 * @param retribucionFija
	 *            the new retribucion fija
	 */
	public void setRetribucionFija(String retribucionFija) {
		this.retribucionFija = retribucionFija;
	}

}
