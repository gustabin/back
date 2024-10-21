/**
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.web.view;

import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import ar.com.santanderrio.obp.servicios.nuevopago.entities.PagoMisCuentasView;

/**
 * The Class PagoMultipleView. Extiende de {@link PagoMisCuentasView} ya que ese
 * DTO se utiliza a las solicitudes asociadas a nuevo pago. Esta clase que se
 * utiliza para representar los datos de un solo pago multiple.
 * 
 * @author ignacio.valek
 * @author manuel.vargas
 * @author emilio.watemberg
 * @since Dec 29, 2016
 * 
 */
@XmlRootElement(name = "pagoMultipleView", namespace = "bean")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PagoMultipleView extends PagoMisCuentasView {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5989915634272260628L;

	/** The pago id. */
	private int pagoId;

	/** The nombre empresa. */
	private String nombreEmpresa;

	/** The informacion adicional. */
	private String informacionAdicional;

	/** The fecha vencimiento. */
	private String fechaVencimiento;

	/** The descripcion tipo cuenta. */
	private String descripcionTipoCuenta;

	/** The tipo de seleccion. */
	@Pattern(regexp = "[I][F]")
	private String tipoDeSeleccion;

	/** The es recargable. */
	private boolean isRecarga = false;

	/** The datos adicionales. */
	private String datosAdicionales;

	/** The fecha de pago, de la ejecucion de pago. */
	private String fechaPago;

	/** The numero de control. A4. El usuario confima la operacion */
	private String numeroDeControl;

	/** The comprobante del servicio. A12. */
	private String comprobantePorServicio;

	/** The estado pago. TRUE | False */
	private boolean esPagoOk;

	/** The mensaje. NULL o dato */
	private String mensaje;

	/** The medio de pago. */
	private String medioDePago;

	/** The id comprobante. */
	private String idComprobante;

	/** Se usa para tilium. */
	private String tipoError;
	
    /** The numeroVep. */
    private String numeroVep;
    
    /** The cuit vep. */
    private String cuitVep;
    
    /** The elemento adicional. */
    private String elementoAdicional;
    
    /** The saldo cuenta origen. */
    private String saldoCuentaOrigen;
    
    
    /**
	 * Gets the numero vep.
	 *
	 * @return the numero vep
	 */
    public String getNumeroVep() {
		return numeroVep;
	}

	/**
	 * Sets the numero vep.
	 *
	 * @param numeroVep
	 *            the new numero vep
	 */
	public void setNumeroVep(String numeroVep) {
		this.numeroVep = numeroVep;
	}

	/**
	 * Gets the anticipo.
	 *
	 * @return the anticipo
	 */
	public String getAnticipo() {
		return anticipo;
	}

	/**
	 * Sets the anticipo.
	 *
	 * @param anticipo
	 *            the new anticipo
	 */
	public void setAnticipo(String anticipo) {
		this.anticipo = anticipo;
	}

	/**
	 * Gets the periodo.
	 *
	 * @return the periodo
	 */
	public String getPeriodo() {
		return periodo;
	}

	/**
	 * Sets the periodo.
	 *
	 * @param periodo
	 *            the new periodo
	 */
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	/** The anticipo. */
    private String anticipo;    

    /** The periodo. */
    private String periodo;

	/**
	 * Gets the tipo de seleccion.
	 *
	 * @return the tipoDeSeleccion
	 */
	public String getTipoDeSeleccion() {
		return tipoDeSeleccion;
	}

	/**
	 * Sets the tipo de seleccion.
	 *
	 * @param tipoDeSeleccion
	 *            the tipoDeSeleccion to set
	 */
	public void setTipoDeSeleccion(String tipoDeSeleccion) {
		this.tipoDeSeleccion = tipoDeSeleccion;
	}

	/**
	 * Checks if is recarga.
	 *
	 * @return true, if is recarga
	 */
	public boolean isRecarga() {
		return isRecarga;
	}

	/**
	 * Sets the recarga.
	 *
	 * @param isRecarga
	 *            the new recarga
	 */
	public void setRecarga(boolean isRecarga) {
		this.isRecarga = isRecarga;
	}

	/**
	 * Gets the datos adicionales.
	 *
	 * @return the datos adicionales
	 */
	public String getDatosAdicionales() {
		return datosAdicionales;
	}

	/**
	 * Sets the datos adicionales.
	 *
	 * @param datosAdicionales
	 *            the new datos adicionales
	 */
	public void setDatosAdicionales(String datosAdicionales) {
		this.datosAdicionales = datosAdicionales;
	}

	/**
	 * Gets the numero de control.
	 *
	 * @return the numeroDeControl
	 */
	public String getNumeroDeControl() {
		return numeroDeControl;
	}

	/**
	 * Sets the numero de control.
	 *
	 * @param numeroDeControl
	 *            the numeroDeControl to set
	 */
	public void setNumeroDeControl(String numeroDeControl) {
		this.numeroDeControl = numeroDeControl;
	}

	/**
	 * Gets the comprobante por servicio.
	 *
	 * @return the comprobanteDelServicio
	 */
	public String getComprobantePorServicio() {
		return comprobantePorServicio;
	}

	/**
	 * Sets the comprobante por servicio.
	 *
	 * @param comprobanteDelServicio
	 *            the comprobanteDelServicio to set
	 */
	public void setComprobantePorServicio(String comprobanteDelServicio) {
		this.comprobantePorServicio = comprobanteDelServicio;
	}

	/**
	 * Gets the mensaje.
	 *
	 * @return the mensaje
	 */
	public String getMensaje() {
		return mensaje;
	}

	/**
	 * Sets the mensaje.
	 *
	 * @param mensaje
	 *            the mensaje to set
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	/**
	 * Gets the fecha pago.
	 *
	 * @return the fechaPago
	 */
	public String getFechaPago() {
		return fechaPago;
	}

	/**
	 * Sets the fecha pago.
	 *
	 * @param fechaPago
	 *            the fechaPago to set
	 */
	public void setFechaPago(String fechaPago) {
		this.fechaPago = fechaPago;
	}

	/**
	 * Gets the es pago ok.
	 *
	 * @return the esPagoOk
	 */
	public boolean getEsPagoOk() {
		return esPagoOk;
	}

	/**
	 * Sets the es pago ok.
	 *
	 * @param esPagoOk
	 *            the esPagoOk to set
	 */
	public void setEsPagoOk(boolean esPagoOk) {
		this.esPagoOk = esPagoOk;
	}

	/**
	 * Gets the pago id.
	 *
	 * @return the pago id
	 */
	public int getPagoId() {
		return pagoId;
	}

	/**
	 * Sets the pago id.
	 *
	 * @param pagoId
	 *            the new pago id
	 */
	public void setPagoId(int pagoId) {
		this.pagoId = pagoId;
	}

	/**
	 * Gets the nombre empresa.
	 *
	 * @return the nombreEmpresa
	 */
	public String getNombreEmpresa() {
		return nombreEmpresa;
	}

	/**
	 * Sets the nombre empresa.
	 *
	 * @param nombreEmpresa
	 *            the nombreEmpresa to set
	 */
	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}

	/**
	 * Gets the informacion adicional.
	 *
	 * @return the informacionAdicional
	 */
	public String getInformacionAdicional() {
		return informacionAdicional;
	}

	/**
	 * Sets the informacion adicional.
	 *
	 * @param informacionAdicional
	 *            the informacionAdicional to set
	 */
	public void setInformacionAdicional(String informacionAdicional) {
		this.informacionAdicional = informacionAdicional;
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
	 * @param fechaVencimiento
	 *            the fechaVencimiento to set
	 */
	public void setFechaVencimiento(String fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	/**
	 * Gets the descripcion tipo cuenta.
	 *
	 * @return the descripcionTipoCuenta
	 */
	public String getDescripcionTipoCuenta() {
		return descripcionTipoCuenta;
	}

	/**
	 * Sets the descripcion tipo cuenta.
	 *
	 * @param descripcionTipoCuenta
	 *            the descripcionTipoCuenta to set
	 */
	public void setDescripcionTipoCuenta(String descripcionTipoCuenta) {
		this.descripcionTipoCuenta = descripcionTipoCuenta;
	}

	/**
	 * Gets the medio de pago.
	 *
	 * @return the medioDePago
	 */
	public String getMedioDePago() {
		return medioDePago;
	}

	/**
	 * Sets the medio de pago.
	 *
	 * @param medioDePago
	 *            the medioDePago to set
	 */
	public void setMedioDePago(String medioDePago) {
		this.medioDePago = medioDePago;
	}

	/**
	 * Gets the id comprobante.
	 *
	 * @return the id comprobante
	 */
	public String getIdComprobante() {
		return idComprobante;
	}

	/**
	 * Sets the id comprobante.
	 *
	 * @param idComprobante
	 *            the new id comprobante
	 */
	public void setIdComprobante(String idComprobante) {
		this.idComprobante = idComprobante;
	}

	/**
	 * Gets the tipo error.
	 *
	 * @return the tipo error
	 */
	public String getTipoError() {
		return tipoError;
	}

	/**
	 * Sets the tipo error.
	 *
	 * @param tipoError
	 *            the new tipo error
	 */
	public void setTipoError(String tipoError) {
		this.tipoError = tipoError;
	}

	/**
	 * Gets the cuit vep.
	 *
	 * @return the cuit vep
	 */
	public String getCuitVep() {
		return cuitVep;
	}

	/**
	 * Sets the cuit vep.
	 *
	 * @param cuitVep
	 *            the new cuit vep
	 */
	public void setCuitVep(String cuitVep) {
		this.cuitVep = cuitVep;
	}

    /**
	 * Gets the elemento adicional.
	 *
	 * @return the elemento adicional
	 */
    public String getElementoAdicional() {
        return elementoAdicional;
    }

    /**
	 * Sets the elemento adicional.
	 *
	 * @param elementoAdicional
	 *            the new elemento adicional
	 */
    public void setElementoAdicional(String elementoAdicional) {
        this.elementoAdicional = elementoAdicional;
    }

	/**
	 * Gets the saldo cuenta origen.
	 *
	 * @return the saldoCuentaOrigen
	 */
	public String getSaldoCuentaOrigen() {
		return saldoCuentaOrigen;
	}

	/**
	 * Sets the saldo cuenta origen.
	 *
	 * @param saldoCuentaOrigen
	 *            the saldoCuentaOrigen to set
	 */
	public void setSaldoCuentaOrigen(String saldoCuentaOrigen) {
		this.saldoCuentaOrigen = saldoCuentaOrigen;
	}

}
