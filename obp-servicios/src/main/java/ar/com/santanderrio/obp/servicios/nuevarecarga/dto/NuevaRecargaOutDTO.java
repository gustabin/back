/*
 * 
 */
package ar.com.santanderrio.obp.servicios.nuevarecarga.dto;

import java.util.Date;

import org.springframework.beans.BeanUtils;

import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.nuevopago.entities.PagoMisCuentasDTO;
import ar.com.santanderrio.obp.servicios.nuevopago.entities.PagoPMC;
import ar.com.santanderrio.obp.servicios.pagos.entities.PagoInEntity;

/**
 * The Class NuevaRecargaOutDTO.
 */
public class NuevaRecargaOutDTO {
	
	/** The estado pago. */
	private Integer estadoPago = null;

	/** The codigo empresa. */
	private String codigoEmpresa;

	/** The identificacion. */
	private String identificacion;

	/** The tipo monto. */
	private String tipoMonto;

	/** The tipo seleccion. */
	private String tipoSeleccion;

	/** The fecha de pago. */
	private Date fechaDePago;

	/** The numero factura. */
	private String numeroFactura;

	/** The tipo cuenta. */
	private String tipoCuenta;

	/** The sucursal cuenta. */
	private String sucursalCuenta;

	/** The numero cuenta. */
	private String numeroCuenta;

	/** The moneda. */
	private String moneda;

	/** The monto. */
	private String monto;

	/** The mensaje. */
	private String mensaje;

	/** The tipo error. */
	private TipoError tipoError;

	/** The comprobante por servicio. */
	private String comprobantePorServicio;

	/** The numero control. */
	private String numeroControl;

	/** The fecha vencimiento. */
	private String fechaVencimiento;

	/** The nombre empresa. */
	private String nombreEmpresa;

	/** The descripcion tipo cuenta. */
	private String descripcionTipoCuenta;
	
    /** The todos OK. */
    private Boolean respuestaOK;
    
    /** The nro comprobante. */
    private String nroComprobante;
    
    /** The nro control. */
    private String nroControl;
    
    /** The label identificacion. */
    private String labelIdentificacion;
    
    /** The dato adicional. */
    private String datoAdicional;
    
    /** The label dato adicional. */
    private String labelDatoAdicional;
    

	/**
	 * Instantiates a new nueva recarga out DTO.
	 *
	 * @param pagoPMC the pago PMC
	 */
	public NuevaRecargaOutDTO(PagoPMC pagoPMC) {
		BeanUtils.copyProperties(pagoPMC, this);
		this.nroComprobante = pagoPMC.getComprobantePorServicio();
		this.nroControl = pagoPMC.getNumeroControl();
		this.fechaVencimiento = "-/-/-";
	}

	/**
	 * Instantiates a new nueva recarga out DTO.
	 */
	public NuevaRecargaOutDTO() {
		super();
	}

	/**
	 * Instantiates a new nueva recarga out DTO.
	 *
	 * @param pagoPMC
	 *            the pago PMC
	 * @param pagoInEntity
	 *            the pago in entity
	 * @param pagoMisCuentasDTO
	 *            the pago mis cuentas DTO
	 */
	public NuevaRecargaOutDTO(PagoPMC pagoPMC, PagoInEntity pagoInEntity, PagoMisCuentasDTO pagoMisCuentasDTO) {
	    BeanUtils.copyProperties(pagoPMC, this);
        this.nroComprobante = pagoPMC.getComprobantePorServicio();
        this.nroControl = pagoPMC.getNumeroControl();
        this.fechaVencimiento = "-/-/-";
        this.labelIdentificacion = "";
    }

    /**
	 * Gets the estado pago.
	 *
	 * @return the estado pago
	 */
	public Integer getEstadoPago() {
		return estadoPago;
	}

	/**
	 * Sets the estado pago.
	 *
	 * @param estadoPago the new estado pago
	 */
	public void setEstadoPago(Integer estadoPago) {
		this.estadoPago = estadoPago;
	}

	/**
	 * Gets the codigo empresa.
	 *
	 * @return the codigo empresa
	 */
	public String getCodigoEmpresa() {
		return codigoEmpresa;
	}

	/**
	 * Sets the codigo empresa.
	 *
	 * @param codigoEmpresa the new codigo empresa
	 */
	public void setCodigoEmpresa(String codigoEmpresa) {
		this.codigoEmpresa = codigoEmpresa;
	}

	/**
	 * Gets the identificacion.
	 *
	 * @return the identificacion
	 */
	public String getIdentificacion() {
		return identificacion;
	}

	/**
	 * Sets the identificacion.
	 *
	 * @param identificacion the new identificacion
	 */
	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	/**
	 * Gets the tipo monto.
	 *
	 * @return the tipo monto
	 */
	public String getTipoMonto() {
		return tipoMonto;
	}

	/**
	 * Sets the tipo monto.
	 *
	 * @param tipoMonto the new tipo monto
	 */
	public void setTipoMonto(String tipoMonto) {
		this.tipoMonto = tipoMonto;
	}

	/**
	 * Gets the tipo seleccion.
	 *
	 * @return the tipo seleccion
	 */
	public String getTipoSeleccion() {
		return tipoSeleccion;
	}

	/**
	 * Sets the tipo seleccion.
	 *
	 * @param tipoSeleccion the new tipo seleccion
	 */
	public void setTipoSeleccion(String tipoSeleccion) {
		this.tipoSeleccion = tipoSeleccion;
	}

	/**
	 * Gets the fecha de pago.
	 *
	 * @return the fecha de pago
	 */
	public Date getFechaDePago() {
		return fechaDePago;
	}

	/**
	 * Sets the fecha de pago.
	 *
	 * @param fechaDePago the new fecha de pago
	 */
	public void setFechaDePago(Date fechaDePago) {
		this.fechaDePago = fechaDePago;
	}

	/**
	 * Gets the numero factura.
	 *
	 * @return the numero factura
	 */
	public String getNumeroFactura() {
		return numeroFactura;
	}

	/**
	 * Sets the numero factura.
	 *
	 * @param numeroFactura the new numero factura
	 */
	public void setNumeroFactura(String numeroFactura) {
		this.numeroFactura = numeroFactura;
	}

	/**
	 * Gets the tipo cuenta.
	 *
	 * @return the tipo cuenta
	 */
	public String getTipoCuenta() {
		return tipoCuenta;
	}

	/**
	 * Sets the tipo cuenta.
	 *
	 * @param tipoCuenta the new tipo cuenta
	 */
	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	/**
	 * Gets the sucursal cuenta.
	 *
	 * @return the sucursal cuenta
	 */
	public String getSucursalCuenta() {
		return sucursalCuenta;
	}

	/**
	 * Sets the sucursal cuenta.
	 *
	 * @param sucursalCuenta the new sucursal cuenta
	 */
	public void setSucursalCuenta(String sucursalCuenta) {
		this.sucursalCuenta = sucursalCuenta;
	}

	/**
	 * Gets the numero cuenta.
	 *
	 * @return the numero cuenta
	 */
	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	/**
	 * Sets the numero cuenta.
	 *
	 * @param numeroCuenta the new numero cuenta
	 */
	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
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
	 * Gets the monto.
	 *
	 * @return the monto
	 */
	public String getMonto() {
		return monto;
	}

	/**
	 * Sets the monto.
	 *
	 * @param monto the new monto
	 */
	public void setMonto(String monto) {
		this.monto = monto;
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
	 * @param mensaje the new mensaje
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	/**
	 * Gets the tipo error.
	 *
	 * @return the tipo error
	 */
	public TipoError getTipoError() {
		return tipoError;
	}

	/**
	 * Sets the tipo error.
	 *
	 * @param tipoError the new tipo error
	 */
	public void setTipoError(TipoError tipoError) {
		this.tipoError = tipoError;
	}

	/**
	 * Gets the comprobante por servicio.
	 *
	 * @return the comprobante por servicio
	 */
	public String getComprobantePorServicio() {
		return comprobantePorServicio;
	}

	/**
	 * Sets the comprobante por servicio.
	 *
	 * @param comprobantePorServicio the new comprobante por servicio
	 */
	public void setComprobantePorServicio(String comprobantePorServicio) {
		this.comprobantePorServicio = comprobantePorServicio;
	}

	/**
	 * Gets the numero control.
	 *
	 * @return the numero control
	 */
	public String getNumeroControl() {
		return numeroControl;
	}

	/**
	 * Sets the numero control.
	 *
	 * @param numeroControl the new numero control
	 */
	public void setNumeroControl(String numeroControl) {
		this.numeroControl = numeroControl;
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
	 * Gets the nombre empresa.
	 *
	 * @return the nombre empresa
	 */
	public String getNombreEmpresa() {
		return nombreEmpresa;
	}

	/**
	 * Sets the nombre empresa.
	 *
	 * @param nombreEmpresa the new nombre empresa
	 */
	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
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
	 * @param descripcionTipoCuenta the new descripcion tipo cuenta
	 */
	public void setDescripcionTipoCuenta(String descripcionTipoCuenta) {
		this.descripcionTipoCuenta = descripcionTipoCuenta;
	}

	/**
	 * Gets the respuesta OK.
	 *
	 * @return the respuesta OK
	 */
	public Boolean getRespuestaOK() {
		return respuestaOK;
	}

	/**
	 * Sets the respuesta OK.
	 *
	 * @param respuestaOK the new respuesta OK
	 */
	public void setRespuestaOK(Boolean respuestaOK) {
		this.respuestaOK = respuestaOK;
	}

	/**
	 * Gets the nro comprobante.
	 *
	 * @return the nro comprobante
	 */
	public String getNroComprobante() {
		return nroComprobante;
	}

	/**
	 * Sets the nro comprobante.
	 *
	 * @param nroComprobante the new nro comprobante
	 */
	public void setNroComprobante(String nroComprobante) {
		this.nroComprobante = nroComprobante;
	}

	/**
	 * Gets the nro control.
	 *
	 * @return the nro control
	 */
	public String getNroControl() {
		return nroControl;
	}

	/**
	 * Sets the nro control.
	 *
	 * @param nroControl the new nro control
	 */
	public void setNroControl(String nroControl) {
		this.nroControl = nroControl;
	}

    /**
	 * Gets the label identificacion.
	 *
	 * @return the label identificacion
	 */
    public String getLabelIdentificacion() {
        return labelIdentificacion;
    }

    /**
	 * Sets the label identificacion.
	 *
	 * @param labelIdentificacion
	 *            the new label identificacion
	 */
    public void setLabelIdentificacion(String labelIdentificacion) {
        this.labelIdentificacion = labelIdentificacion;
    }

    /**
	 * Gets the dato adicional.
	 *
	 * @return the dato adicional
	 */
    public String getDatoAdicional() {
        return datoAdicional;
    }

    /**
	 * Sets the dato adicional.
	 *
	 * @param datoAdicional
	 *            the new dato adicional
	 */
    public void setDatoAdicional(String datoAdicional) {
        this.datoAdicional = datoAdicional;
    }

    /**
	 * Gets the label dato adicional.
	 *
	 * @return the label dato adicional
	 */
    public String getLabelDatoAdicional() {
        return labelDatoAdicional;
    }

    /**
	 * Sets the label dato adicional.
	 *
	 * @param labelDatoAdicional
	 *            the new label dato adicional
	 */
    public void setLabelDatoAdicional(String labelDatoAdicional) {
        this.labelDatoAdicional = labelDatoAdicional;
    }
	
	
	
}
