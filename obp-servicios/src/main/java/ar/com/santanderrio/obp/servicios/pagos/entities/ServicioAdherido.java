/**
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;

/**
 * The Class ServicioAdherido.
 *
 * @author B039636
 */
public class ServicioAdherido implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4993160263999878106L;

	/** The cuit. */
	private String cuit;

	/** The nombre empresa. */
	private String nombreEmpresa;

	/** The numero partida. */
	private String numeroPartida;

	/** The limite adhesion. */
	private BigDecimal limiteAdhesion;

	/** The estado servicio. */
	private int estadoServicio;

	/** The fecha vigencia. */
	private String fechaVigencia;

	/** The codigo aplicacion. */
	private String codigoAplicacion;

	/** The tipo cuenta debito. */
	private TipoCuenta tipoCuentaDebito;

	/** The sucursal. */
	private String sucursal;

	/** The cuenta producto. */
	private String cuentaProducto;

	/** The orden firmante. */
	private String ordenFirmante;

	/** The deudas. */
	private List<ServicioAdheridoDeuda> deudas = new ArrayList<ServicioAdheridoDeuda>();

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
	 * Gets the numero partida.
	 *
	 * @return the numeroPartida
	 */
	public String getNumeroPartida() {
		return numeroPartida;
	}

	/**
	 * Sets the numero partida.
	 *
	 * @param numeroPartida
	 *            the numeroPartida to set
	 */
	public void setNumeroPartida(String numeroPartida) {
		this.numeroPartida = numeroPartida;
	}

	/**
	 * Gets the limite adhesion.
	 *
	 * @return the limiteAdhesion
	 */
	public BigDecimal getLimiteAdhesion() {
		return limiteAdhesion;
	}

	/**
	 * Sets the limite adhesion.
	 *
	 * @param limiteAdhesion
	 *            the limiteAdhesion to set
	 */
	public void setLimiteAdhesion(BigDecimal limiteAdhesion) {
		this.limiteAdhesion = limiteAdhesion;
	}

	/**
	 * Gets the estado servicio.
	 *
	 * @return the estadoServicio
	 */
	public int getEstadoServicio() {
		return estadoServicio;
	}

	/**
	 * Sets the estado servicio.
	 *
	 * @param estadoServicio
	 *            the estadoServicio to set
	 */
	public void setEstadoServicio(int estadoServicio) {
		this.estadoServicio = estadoServicio;
	}

	/**
	 * Gets the fecha vigencia.
	 *
	 * @return the fechaVigencia
	 */
	public String getFechaVigencia() {
		return fechaVigencia;
	}

	/**
	 * Sets the fecha vigencia.
	 *
	 * @param fechaVigencia
	 *            the fechaVigencia to set
	 */
	public void setFechaVigencia(String fechaVigencia) {
		this.fechaVigencia = fechaVigencia;
	}

	/**
	 * Gets the codigo aplicacion.
	 *
	 * @return the codigoAplicacion
	 */
	public String getCodigoAplicacion() {
		return codigoAplicacion;
	}

	/**
	 * Sets the codigo aplicacion.
	 *
	 * @param codigoAplicacion
	 *            the codigoAplicacion to set
	 */
	public void setCodigoAplicacion(String codigoAplicacion) {
		this.codigoAplicacion = codigoAplicacion;
	}

	/**
	 * Gets the tipo cuenta debito.
	 *
	 * @return the tipoCuentaDebito
	 */
	public TipoCuenta getTipoCuentaDebito() {
		return tipoCuentaDebito;
	}

	/**
	 * Sets the tipo cuenta debito.
	 *
	 * @param tipoCuentaDebito
	 *            the tipoCuentaDebito to set
	 */
	public void setTipoCuentaDebito(TipoCuenta tipoCuentaDebito) {
		this.tipoCuentaDebito = tipoCuentaDebito;
	}

	/**
	 * Gets the sucursal.
	 *
	 * @return the sucursal
	 */
	public String getSucursal() {
		return sucursal;
	}

	/**
	 * Sets the sucursal.
	 *
	 * @param sucursal
	 *            the sucursal to set
	 */
	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}

	/**
	 * Gets the cuenta producto.
	 *
	 * @return the cuentaProducto
	 */
	public String getCuentaProducto() {
		return cuentaProducto;
	}

	/**
	 * Sets the cuenta producto.
	 *
	 * @param cuentaProducto
	 *            the cuentaProducto to set
	 */
	public void setCuentaProducto(String cuentaProducto) {
		this.cuentaProducto = cuentaProducto;
	}

	/**
	 * Gets the orden firmante.
	 *
	 * @return the ordenFirmante
	 */
	public String getOrdenFirmante() {
		return ordenFirmante;
	}

	/**
	 * Sets the orden firmante.
	 *
	 * @param ordenFirmante
	 *            the ordenFirmante to set
	 */
	public void setOrdenFirmante(String ordenFirmante) {
		this.ordenFirmante = ordenFirmante;
	}

	/**
	 * Gets the deudas.
	 *
	 * @return the deudas
	 */
	public List<ServicioAdheridoDeuda> getDeudas() {
		return deudas;
	}

	/**
	 * Adds the deuda.
	 *
	 * @param servicioAdheridoDeuda
	 *            the servicio adherido deuda
	 */
	public void addDeuda(ServicioAdheridoDeuda servicioAdheridoDeuda) {
		deudas.add(servicioAdheridoDeuda);
	}

}
