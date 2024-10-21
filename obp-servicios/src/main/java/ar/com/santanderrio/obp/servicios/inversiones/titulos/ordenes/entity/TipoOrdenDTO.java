/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenes.entity;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

/**
 * The Class TipoOrdenDTO.
 */
public class TipoOrdenDTO implements Comparable<TipoOrdenDTO>{

	/** The especie. */
	private String especie;
	
	/** The fecha. */
	private String fecha;
	
	/** The numero orden. */
	private String numeroOrden;
	
	/** The estado. */
	private String estado;
	
	/** The cantidad nominal. */
	private String cantidadNominal;
	
	/** The precio limite. */
	private String precioLimite;
	
	/** The plazo. */
	private String plazo;
	
	/** The tipo de orden. */
	private String tipoDeOrden;
	
	/** The semaforo. */
	private String semaforo;
	
	/** The fecha ingreso. */
	private String fechaIngreso;
	
	/** The motivo rechazo. */
	private String motivoRechazo;
	
	/** The moneda. */
	private String moneda;
	
	/** The precio concertacion. */
	private String precioConcertacion;
	
	/** The precio referencia. */
	private String precioReferencia;
	
	/** The importe A debitar. */
	private String importeADebitar;
	
	/** The cuenta titulos. */
	private String cuentaTitulos;
	
	/** The cuenta debito. */
	private String cuentaDebito;
	
	/** The canal. */
	private String canal;
	
	/** The hora. */
	private String hora;
	
	/** The especie tipo. */
	private String especieTipo;
	
	/** The instrumento codigo. */
	private String instrumentoCodigo;
	
	/** The descripcion tipo cuenta. */
	private String descripcionTipoCuenta;
	
	/** The ric. */
	private String ric;
	
	/** The cantidad reversada. */
	private String cantidadReversada;
	
	/** The fecha buscar. */
	private String fechaBuscar;
	
	/** The deshabilitar BP. */
	@JsonSerialize(include = Inclusion.NON_NULL)
	private Boolean deshabilitarBP;

	
	
	/**
	 * Gets the especie.
	 *
	 * @return the especie
	 */
	public String getEspecie() {
		return especie;
	}

	/**
	 * Sets the especie.
	 *
	 * @param especie
	 *            the new especie
	 */
	public void setEspecie(String especie) {
		this.especie = especie;
	}

	/**
	 * Gets the fecha.
	 *
	 * @return the fecha
	 */
	public String getFecha() {
		return fecha;
	}

	/**
	 * Sets the fecha.
	 *
	 * @param fecha
	 *            the new fecha
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	/**
	 * Gets the numero orden.
	 *
	 * @return the numero orden
	 */
	public String getNumeroOrden() {
		return numeroOrden;
	}

	/**
	 * Sets the numero orden.
	 *
	 * @param numeroOrden
	 *            the new numero orden
	 */
	public void setNumeroOrden(String numeroOrden) {
		this.numeroOrden = numeroOrden;
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
	 *            the new estado
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * Gets the cantidad nominal.
	 *
	 * @return the cantidad nominal
	 */
	public String getCantidadNominal() {
		return cantidadNominal;
	}

	/**
	 * Sets the cantidad nominal.
	 *
	 * @param cantidadNominal
	 *            the new cantidad nominal
	 */
	public void setCantidadNominal(String cantidadNominal) {
		this.cantidadNominal = cantidadNominal;
	}

	/**
	 * Gets the precio limite.
	 *
	 * @return the precio limite
	 */
	public String getPrecioLimite() {
		return precioLimite;
	}

	/**
	 * Sets the precio limite.
	 *
	 * @param precioLimite
	 *            the new precio limite
	 */
	public void setPrecioLimite(String precioLimite) {
		this.precioLimite = precioLimite;
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
	 * @param plazo
	 *            the new plazo
	 */
	public void setPlazo(String plazo) {
		this.plazo = plazo;
	}

	/**
	 * Gets the tipo de orden.
	 *
	 * @return the tipo de orden
	 */
	public String getTipoDeOrden() {
		return tipoDeOrden;
	}

	/**
	 * Sets the tipo de orden.
	 *
	 * @param tipoDeOrden
	 *            the new tipo de orden
	 */
	public void setTipoDeOrden(String tipoDeOrden) {
		this.tipoDeOrden = tipoDeOrden;
	}

	/**
	 * Gets the semaforo.
	 *
	 * @return the semaforo
	 */
	public String getSemaforo() {
		return semaforo;
	}

	/**
	 * Sets the semaforo.
	 *
	 * @param semaforo
	 *            the new semaforo
	 */
	public void setSemaforo(String semaforo) {
		this.semaforo = semaforo;
	}

	/**
	 * Gets the fecha ingreso.
	 *
	 * @return the fecha ingreso
	 */
	public String getFechaIngreso() {
		return fechaIngreso;
	}

	/**
	 * Sets the fecha ingreso.
	 *
	 * @param fechaIngreso
	 *            the new fecha ingreso
	 */
	public void setFechaIngreso(String fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	/**
	 * Gets the motivo rechazo.
	 *
	 * @return the motivo rechazo
	 */
	public String getMotivoRechazo() {
		return motivoRechazo;
	}

	/**
	 * Sets the motivo rechazo.
	 *
	 * @param motivoRechazo
	 *            the new motivo rechazo
	 */
	public void setMotivoRechazo(String motivoRechazo) {
		this.motivoRechazo = motivoRechazo;
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
	 * @param moneda
	 *            the new moneda
	 */
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	/**
	 * Gets the precio concertacion.
	 *
	 * @return the precio concertacion
	 */
	public String getPrecioConcertacion() {
		return precioConcertacion;
	}

	/**
	 * Sets the precio concertacion.
	 *
	 * @param precioConcertacion
	 *            the new precio concertacion
	 */
	public void setPrecioConcertacion(String precioConcertacion) {
		this.precioConcertacion = precioConcertacion;
	}

	/**
	 * Gets the precio referencia.
	 *
	 * @return the precio referencia
	 */
	public String getPrecioReferencia() {
		return precioReferencia;
	}

	/**
	 * Sets the precio referencia.
	 *
	 * @param precioReferencia
	 *            the new precio referencia
	 */
	public void setPrecioReferencia(String precioReferencia) {
		this.precioReferencia = precioReferencia;
	}

	/**
	 * Gets the importe A debitar.
	 *
	 * @return the importe A debitar
	 */
	public String getImporteADebitar() {
		return importeADebitar;
	}

	/**
	 * Sets the importe A debitar.
	 *
	 * @param importeADebitar
	 *            the new importe A debitar
	 */
	public void setImporteADebitar(String importeADebitar) {
		this.importeADebitar = importeADebitar;
	}

	/**
	 * Gets the cuenta titulos.
	 *
	 * @return the cuenta titulos
	 */
	public String getCuentaTitulos() {
		return cuentaTitulos;
	}

	/**
	 * Sets the cuenta titulos.
	 *
	 * @param cuentaTitulos
	 *            the new cuenta titulos
	 */
	public void setCuentaTitulos(String cuentaTitulos) {
		this.cuentaTitulos = cuentaTitulos;
	}

	/**
	 * Gets the cuenta debito.
	 *
	 * @return the cuenta debito
	 */
	public String getCuentaDebito() {
		return cuentaDebito;
	}

	/**
	 * Sets the cuenta debito.
	 *
	 * @param cuentaDebito
	 *            the new cuenta debito
	 */
	public void setCuentaDebito(String cuentaDebito) {
		this.cuentaDebito = cuentaDebito;
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
	 * Gets the hora.
	 *
	 * @return the hora
	 */
	public String getHora() {
		return hora;
	}

	/**
	 * Sets the hora.
	 *
	 * @param hora
	 *            the new hora
	 */
	public void setHora(String hora) {
		this.hora = hora;
	}

	/**
	 * Gets the especie tipo.
	 *
	 * @return the especie tipo
	 */
	public String getEspecieTipo() {
		return especieTipo;
	}

	/**
	 * Sets the especie tipo.
	 *
	 * @param especieTipo
	 *            the new especie tipo
	 */
	public void setEspecieTipo(String especieTipo) {
		this.especieTipo = especieTipo;
	}

	/**
	 * Gets the instrumento codigo.
	 *
	 * @return the instrumento codigo
	 */
	public String getInstrumentoCodigo() {
		return instrumentoCodigo;
	}

	/**
	 * Sets the instrumento codigo.
	 *
	 * @param instrumentoCodigo
	 *            the new instrumento codigo
	 */
	public void setInstrumentoCodigo(String instrumentoCodigo) {
		this.instrumentoCodigo = instrumentoCodigo;
	}

	/**
	 * Gets the descripcion tipo cuenta.
	 *
	 * @return the descripcion tipo cuenta
	 */
	public String getDescripcionTipoCuenta() {
		return descripcionTipoCuenta;
	}

	/**
	 * Sets the descripcion tipo cuenta.
	 *
	 * @param descripcionTipoCuenta
	 *            the new descripcion tipo cuenta
	 */
	public void setDescripcionTipoCuenta(String descripcionTipoCuenta) {
		this.descripcionTipoCuenta = descripcionTipoCuenta;
	}

	/**
	 * Gets the ric.
	 *
	 * @return the ric
	 */
	public String getRic() {
		return ric;
	}

	/**
	 * Sets the ric.
	 *
	 * @param ric
	 *            the new ric
	 */
	public void setRic(String ric) {
		this.ric = ric;
	}

	/**
	 * Gets the cantidad reversada.
	 *
	 * @return the cantidad reversada
	 */
	public String getCantidadReversada() {
		return cantidadReversada;
	}

	/**
	 * Sets the cantidad reversada.
	 *
	 * @param cantidadReversada
	 *            the new cantidad reversada
	 */
	public void setCantidadReversada(String cantidadReversada) {
		this.cantidadReversada = cantidadReversada;
	}

	/**
	 * Gets the deshabilitar BP.
	 *
	 * @return the deshabilitar BP
	 */
	public Boolean getDeshabilitarBP() {
		return deshabilitarBP;
	}

	/**
	 * Sets the deshabilitar BP.
	 *
	 * @param deshabilitarBP
	 *            the new deshabilitar BP
	 */
	public void setDeshabilitarBP(Boolean deshabilitarBP) {
		this.deshabilitarBP = deshabilitarBP;
	}
		
	/**
	 * Gets the fecha buscar.
	 *
	 * @return the fecha buscar
	 */
	public String getFechaBuscar() {
		return fechaBuscar;
	}

	/**
	 * Sets the fecha buscar.
	 *
	 * @param fechaBuscar
	 *            the new fecha buscar
	 */
	public void setFechaBuscar(String fechaBuscar) {
		this.fechaBuscar = fechaBuscar;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(TipoOrdenDTO o) {
		if (Integer.parseInt(numeroOrden) < Integer.parseInt(o.numeroOrden)) {
			return 1;
		}
		
		if (Integer.parseInt(numeroOrden) > Integer.parseInt(o.numeroOrden)) {
			return -1;
		}
		
		return 0;
	}
	
}
