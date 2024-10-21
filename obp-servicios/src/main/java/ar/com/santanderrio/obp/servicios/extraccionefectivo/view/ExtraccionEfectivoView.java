package ar.com.santanderrio.obp.servicios.extraccionefectivo.view;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionDTO;

@JsonSerialize(include = Inclusion.NON_NULL)
public class ExtraccionEfectivoView {

	private String mensajeOK;
	
	private String fechaVencimiento;
	
	private String codigoTransaccion;
	
	private String numeroComprobante;
	
	private String fechaHoraComprobante;
	
	private int monto;

	private String nroCuenta;
	
	private String email;
	
    private String codigoExtraccion;
	
	private AutentificacionDTO desafio;
	

	public String getMensajeOK() {
		return mensajeOK;
	}
	
	public void setMensajeOK(String mensajeOK) {
		this.mensajeOK = mensajeOK;
	}

	public String getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(String fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public String getCodigoTransaccion() {
		return codigoTransaccion;
	}

	public void setCodigoTransaccion(String codigoTransaccion) {
		this.codigoTransaccion = codigoTransaccion;
	}

	public String getNumeroComprobante() {
		return numeroComprobante;
	}

	public void setNumeroComprobante(String numeroComprobante) {
		this.numeroComprobante = numeroComprobante;
	}

	public String getFechaHoraComprobante() {
		return fechaHoraComprobante;
	}

	public void setFechaHoraComprobante(String fechaHoraComprobante) {
		this.fechaHoraComprobante = fechaHoraComprobante;
	}

	public AutentificacionDTO getDesafio() {
		return desafio;
	}

	public void setDesafio(AutentificacionDTO desafio) {
		this.desafio = desafio;
	}

	public int getMonto() {
		return monto;
	}

	public void setMonto(int monto) {
		this.monto = monto;
	}

	public String getNroCuenta() {
		return nroCuenta;
	}

	public void setNroCuenta(String nroCuenta) {
		this.nroCuenta = nroCuenta;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

    /**
     * @return the codigoExtraccion
     */
    public String getCodigoExtraccion() {
        return codigoExtraccion;
    }

    /**
     * @param codigoExtraccion the codigoExtraccion to set
     */
    public void setCodigoExtraccion(String codigoExtraccion) {
        this.codigoExtraccion = codigoExtraccion;
    }

}
