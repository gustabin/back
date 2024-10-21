/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.view;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.springframework.util.ResourceUtils;

import ar.com.santanderrio.obp.servicios.comprobantes.web.view.DetalleComprobanteView;

/**
 * The Class DetalleCuentaCBUBancaPrivadaView.
 */
public class DetalleCuentaCBUBancaPrivadaView extends DetalleComprobanteView {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The tipoIdentificacion. */
	private String tipoIdentificacion;

	/** The cuit. */
	private String cuit;

	/** The identificacionCliente. */
	private String identificacionCliente;

	/** The nombre cliente. */
	private String nombreCliente;

	/** The cbu. */
	private String cbu;

	/** The numeroCuenta. */
	private String numeroCuenta;

	/** The numeroSucursal. */
	private String numeroSucursal;

	/** The tipoCuenta. */
	private String tipoCuenta;

	/** The nombreBanco. */
	private String nombreBanco;

	/** The pathCuentas. */
	private final String pathCuentas = "classpath:/report/cuentas/";

	/** The transferencias jasper. */
	private final String detalleCBUCuenta = "detallecbucuenta.jasper";

	/** The FECHA_ACTUAL_KEY. */
	private static final String FECHA_ACTUAL_KEY = "FECHA_ACTUAL";

	/** The FORMATO_HORA. */
	private static final String FORMATO_HORA = "HH:mm";

	/** The FORMATO_FECHA. */
	private static final String FORMATO_FECHA = "dd/MM/yyyy";

	/** The SUCURSAL_KEY. */
	private static final String SUCURSAL_KEY = "SUCURSAL";

	/** The TIPO_CUENTA_KEY. */
	private static final String TIPO_CUENTA_KEY = "TIPO_CUENTA";

	/** The CBU_KEY. */
	private static final String CBU_KEY = "CBU";

	/** The BANCO_KEY. */
	private static final String BANCO_KEY = "BANCO";

	/** The CUENTA_KEY. */
	private static final String CUENTA_KEY = "CUENTA";

	/** The CUIL_KEY. */
	private static final String CUIL_KEY = "CUIL";

	/** The NOMBRE_KEY. */
	private static final String NOMBRE_KEY = "NOMBRE";

	/** The DETALLE_DE_CBU_Y_CUENTA. */
	private static final String DETALLE_DE_CBU_Y_CUENTA = "Detalle de CBU y Cuenta";

	/**
	 * Gets the tipo identificacion.
	 *
	 * @return the tipoIdentificacion
	 */
	public String getTipoIdentificacion() {
		return tipoIdentificacion;
	}

	/**
	 * Sets the tipo identificacion.
	 *
	 * @param tipoIdentificacion
	 *            the tipoIdentificacion to set
	 */
	public void setTipoIdentificacion(String tipoIdentificacion) {
		this.tipoIdentificacion = tipoIdentificacion;
	}

	/**
	 * Gets the cuit.
	 *
	 * @return the cuit
	 */
	public String getCuit() {
		return cuit;
	}

	/**
	 * Sets the cuit.
	 *
	 * @param cuit
	 *            the cuit to set
	 */
	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

	/**
	 * Gets the identificacion cliente.
	 *
	 * @return the identificacionCliente
	 */
	public String getIdentificacionCliente() {
		return identificacionCliente;
	}

	/**
	 * Sets the identificacion cliente.
	 *
	 * @param identificacionCliente
	 *            the identificacionCliente to set
	 */
	public void setIdentificacionCliente(String identificacionCliente) {
		this.identificacionCliente = identificacionCliente;
	}

	/**
	 * Gets the nombre cliente.
	 *
	 * @return the nombreCliente
	 */
	public String getNombreCliente() {
		return nombreCliente;
	}

	/**
	 * Sets the nombre cliente.
	 *
	 * @param nombreCliente
	 *            the nombreCliente to set
	 */
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
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
	 * Gets the numero cuenta.
	 *
	 * @return the numeroCuenta
	 */
	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	/**
	 * Sets the numero cuenta.
	 *
	 * @param numeroCuenta
	 *            the numeroCuenta to set
	 */
	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	/**
	 * Gets the numero sucursal.
	 *
	 * @return the numeroSucursal
	 */
	public String getNumeroSucursal() {
		return numeroSucursal;
	}

	/**
	 * Sets the numero sucursal.
	 *
	 * @param numeroSucursal
	 *            the numeroSucursal to set
	 */
	public void setNumeroSucursal(String numeroSucursal) {
		this.numeroSucursal = numeroSucursal;
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
	 * Gets the nombre banco.
	 *
	 * @return the nombreBanco
	 */
	public String getNombreBanco() {
		return nombreBanco;
	}

	/**
	 * Sets the nombre banco.
	 *
	 * @param nombreBanco
	 *            the nombreBanco to set
	 */
	public void setNombreBanco(String nombreBanco) {
		this.nombreBanco = nombreBanco;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.web.view.
	 * DetalleComprobanteView#obtenerParametrosPDF()
	 */
	@Override
	public HashMap<String, Object> obtenerParametrosPDF() throws IOException {
		this.setTituloComprobante(DETALLE_DE_CBU_Y_CUENTA);
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put(NOMBRE_KEY, nombreCliente);
		if (identificacionCliente != null) {
			parametros.put(CUIL_KEY, "CUIT/CUIL: " + identificacionCliente);
		}
		parametros.put(CUENTA_KEY, numeroCuenta);
		parametros.put(BANCO_KEY, nombreBanco);
		parametros.put(CBU_KEY, cbu);
		parametros.put(TIPO_CUENTA_KEY, tipoCuenta);
		parametros.put(SUCURSAL_KEY, numeroSucursal);
		Date fechaComprobante = new Date();
		DateFormat formatoFecha = new SimpleDateFormat(FORMATO_FECHA);
		String fecha = formatoFecha.format(fechaComprobante);
		DateFormat formatoHora = new SimpleDateFormat(FORMATO_HORA);
		String hora = formatoHora.format(fechaComprobante);
		parametros.put(FECHA_ACTUAL_KEY, fecha + " - " + hora);
		return parametros;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.web.view.
	 * DetalleComprobanteView#obtenerJasper()
	 */
	@Override
	public String obtenerJasper() throws FileNotFoundException {
		return ResourceUtils.getFile(pathCuentas + detalleCBUCuenta).getPath();
	}

}
