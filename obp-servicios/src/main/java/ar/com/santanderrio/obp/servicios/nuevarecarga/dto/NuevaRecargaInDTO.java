/*
 * 
 */
package ar.com.santanderrio.obp.servicios.nuevarecarga.dto;

import org.apache.commons.lang.StringUtils;

import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.nuevarecarga.web.view.ConfirmacionRecargaView;

/**
 * The Class NuevaRecargaInDTO.
 */
public class NuevaRecargaInDTO {

	/** The tipo cuenta. */
	private TipoCuenta tipoCuenta;

	/** The codigo empresa. */
	private String codigoEmpresa;

	/** The identificacion cliente. */
	private String identificacionCliente;

	/** The nro sucursal. */
	private String nroSucursal;

	/** The cbu cuenta. */
	private String cbuCuenta;

	/** The monto id. */
	private String montoId;

	/** The nombre fantasia. */
	private String nombreFantasia;

	/** The monto mensaje feedback. */
	private String montoMensajeFeedback;

	/** The identificacion mensaje feedback. */
	private String identificacionMensajeFeedback;
	
	/** The nro tarjeta. */
	private String nroTarjeta;

    /** The tipo cuenta TC. */
    private TipoCuenta tipoCuentaTC;

	/**
	 * Instantiates a new nueva recarga in DTO.
	 */
	public NuevaRecargaInDTO() {
		super();
	}

	/**
	 * Instantiates a new nueva recarga in DTO.
	 *
	 * @param datosConfirmacionRecarga
	 *            the datos confirmacion recarga
	 * @param nombreFantasia
	 *            the nombre fantasia
	 * @param cuenta
	 *            the cuenta
	 */
	public NuevaRecargaInDTO(ConfirmacionRecargaView datosConfirmacionRecarga, String nombreFantasia, Cuenta cuenta) {
		this.codigoEmpresa = datosConfirmacionRecarga.getCodigoEmpresa();
		this.identificacionCliente = StringUtils.remove(datosConfirmacionRecarga.getIdentificacionCliente(), " ");
		this.cbuCuenta = datosConfirmacionRecarga.getCbuCuenta();
		this.montoId = datosConfirmacionRecarga.getMontoId();
		this.nombreFantasia = nombreFantasia;
		this.montoMensajeFeedback = datosConfirmacionRecarga.obtenerMontoFeedback();
		this.identificacionMensajeFeedback = datosConfirmacionRecarga.getIdentificacionCliente();
		if (cuenta.esTarjetaDeCredito()) {
            this.nroTarjeta = cuenta.getNroTarjetaCredito();
            this.tipoCuentaTC = cuenta.getTipoCuentaEnum();
        }
	}

	/**
	 * Gets the tipo cuenta.
	 *
	 * @return the tipo cuenta
	 */
	public TipoCuenta getTipoCuenta() {
		return tipoCuenta;
	}

	/**
	 * Sets the tipo cuenta.
	 *
	 * @param tipoCuenta
	 *            the new tipo cuenta
	 */
	public void setTipoCuenta(TipoCuenta tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
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
	 * @param codigoEmpresa
	 *            the new codigo empresa
	 */
	public void setCodigoEmpresa(String codigoEmpresa) {
		this.codigoEmpresa = codigoEmpresa;
	}

	/**
	 * Gets the identificacion cliente.
	 *
	 * @return the identificacion cliente
	 */
	public String getIdentificacionCliente() {
		return identificacionCliente;
	}

	/**
	 * Sets the identificacion cliente.
	 *
	 * @param identificacionCliente
	 *            the new identificacion cliente
	 */
	public void setIdentificacionCliente(String identificacionCliente) {
		this.identificacionCliente = identificacionCliente;
	}

	/**
	 * Gets the nro sucursal.
	 *
	 * @return the nro sucursal
	 */
	public String getNroSucursal() {
		return nroSucursal;
	}

	/**
	 * Sets the nro sucursal.
	 *
	 * @param nroSucursal
	 *            the new nro sucursal
	 */
	public void setNroSucursal(String nroSucursal) {
		this.nroSucursal = nroSucursal;
	}

	/**
	 * Gets the cbu cuenta.
	 *
	 * @return the cbu cuenta
	 */
	public String getCbuCuenta() {
		return cbuCuenta;
	}

	/**
	 * Sets the cbu cuenta.
	 *
	 * @param cbuCuenta
	 *            the new cbu cuenta
	 */
	public void setCbuCuenta(String cbuCuenta) {
		this.cbuCuenta = cbuCuenta;
	}

	/**
	 * Gets the monto id.
	 *
	 * @return the monto id
	 */
	public String getMontoId() {
		return this.montoId;
	}

	/**
	 * Sets the monto id.
	 *
	 * @param montoId
	 *            the new monto id
	 */
	public void setMontoId(String montoId) {
		this.montoId = montoId;
	}

	/**
	 * Gets the nombre fantasia.
	 *
	 * @return the nombre fantasia
	 */
	public String getNombreFantasia() {
		return nombreFantasia;
	}

	/**
	 * Sets the nombre fantasia.
	 *
	 * @param nombreFantasia
	 *            the new nombre fantasia
	 */
	public void setNombreFantasia(String nombreFantasia) {
		this.nombreFantasia = nombreFantasia;
	}

	/**
	 * Gets the monto mensaje feedback.
	 *
	 * @return the monto mensaje feedback
	 */
	public String getMontoMensajeFeedback() {
		return montoMensajeFeedback;
	}

	/**
	 * Sets the monto mensaje feedback.
	 *
	 * @param montoMensajeFeedback
	 *            the new monto mensaje feedback
	 */
	public void setMontoMensajeFeedback(String montoMensajeFeedback) {
		this.montoMensajeFeedback = montoMensajeFeedback;
	}

	/**
	 * Gets the identificacion mensaje feedback.
	 *
	 * @return the identificacion mensaje feedback
	 */
	public String getIdentificacionMensajeFeedback() {
		return identificacionMensajeFeedback;
	}

	/**
	 * Sets the identificacion mensaje feedback.
	 *
	 * @param identificacionMensajeFeedback
	 *            the new identificacion mensaje feedback
	 */
	public void setIdentificacionMensajeFeedback(String identificacionMensajeFeedback) {
		this.identificacionMensajeFeedback = identificacionMensajeFeedback;
	}

    /**
	 * Gets the nro tarjeta.
	 *
	 * @return the nroTarjeta
	 */
    public String getNroTarjeta() {
        return nroTarjeta;
    }

    /**
	 * Sets the nro tarjeta.
	 *
	 * @param nroTarjeta
	 *            the nroTarjeta to set
	 */
    public void setNroTarjeta(String nroTarjeta) {
        this.nroTarjeta = nroTarjeta;
    }

    /**
	 * Gets the tipo cuenta TC.
	 *
	 * @return the tipoCuentaTC
	 */
    public TipoCuenta getTipoCuentaTC() {
        return tipoCuentaTC;
    }

    /**
	 * Sets the tipo cuenta TC.
	 *
	 * @param tipoCuentaTC
	 *            the tipoCuentaTC to set
	 */
    public void setTipoCuentaTC(TipoCuenta tipoCuentaTC) {
        this.tipoCuentaTC = tipoCuentaTC;
    }

}
