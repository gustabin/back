package ar.com.santanderrio.obp.servicios.echeq.view;

import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

/**
 * The Class ECheqView.
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class ECheqView {
	
	/** The id. */
	private String id;
		
	/** The fecha emision. */
	private String fechaEmision;
	
	private String nombreEmisor;

	private String cuitEmisor;

	private String nombreBeneficiario;

	private String cuitBeneficiario;
	
	/** The estado. */
	private String estado;
	
	/** The id. */
	private String numeroCheque;
	
	/** The importe. */
	private String importe;
	
	/** The moneda simbolo. */
	private String monedaSimbolo;
	
	/** The fecha pago. */
	private String fechaPago;
	
	/** The acciones. */
	private List<String> acciones;
	
	/** The mensaje warning. */
	private String mensajeWarning;
	
	private String chequeCaracter;
	
	private String cuentaNumero;
	
	private String cuentaTipo;
	
	private String alias;
	
	private String motivo;
	
	private List<EndosoView> endosos;

	private Boolean aplicaLegal = Boolean.FALSE;
		
	public ECheqView() {

	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the fechaEmision
	 */
	public String getFechaEmision() {
		return fechaEmision;
	}

	/**
	 * @param fechaEmision the fechaEmision to set
	 */
	public void setFechaEmision(String fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * @return the numeroCheque
	 */
	public String getNumeroCheque() {
		return numeroCheque;
	}

	/**
	 * @param numeroCheque the numeroCheque to set
	 */
	public void setNumeroCheque(String numeroCheque) {
		this.numeroCheque = numeroCheque;
	}

	/**
	 * @return the importe
	 */
	public String getImporte() {
		return importe;
	}

	/**
	 * @param importe the importe to set
	 */
	public void setImporte(String importe) {
		this.importe = importe;
	}

	/**
	 * @return the monedaSimbolo
	 */
	public String getMonedaSimbolo() {
		return monedaSimbolo;
	}

	/**
	 * @param monedaSimbolo the monedaSimbolo to set
	 */
	public void setMonedaSimbolo(String monedaSimbolo) {
		this.monedaSimbolo = monedaSimbolo;
	}

	/**
	 * @return the fechaPago
	 */
	public String getFechaPago() {
		return fechaPago;
	}

	/**
	 * @param fechaPago the fechaPago to set
	 */
	public void setFechaPago(String fechaPago) {
		this.fechaPago = fechaPago;
	}

	/**
	 * @return the acciones
	 */
	public List<String> getAcciones() {
		return acciones;
	}

	/**
	 * @param acciones the acciones to set
	 */
	public void setAcciones(List<String> acciones) {
		this.acciones = acciones;
	}

	/**
	 * @return the mensajeWarning
	 */
	public String getMensajeWarning() {
		return mensajeWarning;
	}

	/**
	 * @param mensajeWarning the mensajeWarning to set
	 */
	public void setMensajeWarning(String mensajeWarning) {
		this.mensajeWarning = mensajeWarning;
	}

	public String getChequeCaracter() {
		return chequeCaracter;
	}

	public void setChequeCaracter(String chequeCaracter) {
		this.chequeCaracter = chequeCaracter;
	}

	public String getCuentaNumero() {
		return cuentaNumero;
	}

	public void setCuentaNumero(String cuentaNumero) {
		this.cuentaNumero = cuentaNumero;
	}

	public String getCuentaTipo() {
		return cuentaTipo;
	}

	public void setCuentaTipo(String cuentaTipo) {
		this.cuentaTipo = cuentaTipo;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public List<EndosoView> getEndosos() {
		return endosos;
	}

	public void setEndosos(List<EndosoView> endosos) {
		this.endosos = endosos;
	}

	public String getNombreEmisor() {
		return nombreEmisor;
	}

	public void setNombreEmisor(String nombreEmisor) {
		this.nombreEmisor = nombreEmisor;
	}

	public String getCuitEmisor() {
		return cuitEmisor;
	}

	public void setCuitEmisor(String cuitEmisor) {
		this.cuitEmisor = cuitEmisor;
	}

	public String getNombreBeneficiario() {
		return nombreBeneficiario;
	}

	public void setNombreBeneficiario(String nombreBeneficiario) {
		this.nombreBeneficiario = nombreBeneficiario;
	}

	public String getCuitBeneficiario() {
		return cuitBeneficiario;
	}

	public void setCuitBeneficiario(String cuitBeneficiario) {
		this.cuitBeneficiario = cuitBeneficiario;
	}

	public Boolean getAplicaLegal() {
		return aplicaLegal;
	}

	public void setAplicaLegal(Boolean aplicaLegal) {
		this.aplicaLegal = aplicaLegal;
	}
	
	
}
