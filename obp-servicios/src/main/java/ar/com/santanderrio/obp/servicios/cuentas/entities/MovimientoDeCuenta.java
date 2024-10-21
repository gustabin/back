/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.entities;

import java.math.BigDecimal;

import org.apache.commons.lang3.builder.HashCodeBuilder;

import ar.com.santanderrio.obp.base.entities.Entity;
import ar.com.santanderrio.obp.servicios.comun.sucursales.entities.Sucursal;

/**
 * The Class MovimientoDeCuenta.
 */
public class MovimientoDeCuenta extends Entity {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The sucursal. */
	private Sucursal sucursal = new Sucursal();

	/** The tipo de cuenta. */
	private String tipoDeCuenta = "";

	/** The numero de cuenta. */
	private String numeroDeCuenta = "";

	/** The divisa. */
	private DivisaEnum divisa;

	/** The origen transaccion. */
	private String origenTransaccion = "";

	/** The hora. */
	private String hora = "";

	/** The fecha. */
	private String fecha = "";

	/** The tipo de operacion. */
	private TipoOperacionMovimimiento tipoDeOperacion;

	/** The numero de ticket. */
	private String numeroDeTicket = "";

	/** The sucursal origen. */
	private Sucursal sucursalOrigen = new Sucursal();

	/** The usuario banco. */
	private String usuarioBanco = "";

	/** The importe operacion. */
	private BigDecimal importeOperacion;

	/** The estado. */
	private EstadoOperacionMovimimiento estado;

	/**
	 * Gets the sucursal.
	 *
	 * @return the sucursal
	 */
	public Sucursal getSucursal() {
		return sucursal;
	}

	/**
	 * Setter para sucursal.
	 *
	 * @param sucursal
	 *            el nuevo sucursal
	 */
	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}

	/**
	 * Gets the tipo de cuenta.
	 *
	 * @return the tipo de cuenta
	 */
	public String getTipoDeCuenta() {
		return tipoDeCuenta;
	}

	/**
	 * Setter para tipo de cuenta.
	 *
	 * @param tipoDeCuenta
	 *            el nuevo tipo de cuenta
	 */
	public void setTipoDeCuenta(String tipoDeCuenta) {
		this.tipoDeCuenta = tipoDeCuenta;
	}

	/**
	 * Gets the numero de cuenta.
	 *
	 * @return the numero de cuenta
	 */
	public String getNumeroDeCuenta() {
		return numeroDeCuenta;
	}

	/**
	 * Setter para numero de cuenta.
	 *
	 * @param numeroDeCuenta
	 *            el nuevo numero de cuenta
	 */
	public void setNumeroDeCuenta(String numeroDeCuenta) {
		this.numeroDeCuenta = numeroDeCuenta;
	}

	/**
	 * Gets the divisa.
	 *
	 * @return the divisa
	 */
	public DivisaEnum getDivisa() {
		return divisa;
	}

	/**
	 * Setter para divisa.
	 *
	 * @param divisa
	 *            el nuevo divisa
	 */
	public void setDivisa(DivisaEnum divisa) {
		this.divisa = divisa;
	}

	/**
	 * Gets the origen transaccion.
	 *
	 * @return the origen transaccion
	 */
	public String getOrigenTransaccion() {
		return origenTransaccion;
	}

	/**
	 * Setter para origen transaccion.
	 *
	 * @param origenTransaccion
	 *            el nuevo origen transaccion
	 */
	public void setOrigenTransaccion(String origenTransaccion) {
		this.origenTransaccion = origenTransaccion;
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
	 * Setter para hora.
	 *
	 * @param hora
	 *            el nuevo hora
	 */
	public void setHora(String hora) {
		this.hora = hora;
	}

	/**
	 * Gets the tipo de operacion.
	 *
	 * @return the tipo de operacion
	 */
	public TipoOperacionMovimimiento getTipoDeOperacion() {
		return tipoDeOperacion;
	}

	/**
	 * Setter para tipo de operacion.
	 *
	 * @param tipoDeOperacion
	 *            el nuevo tipo de operacion
	 */
	public void setTipoDeOperacion(TipoOperacionMovimimiento tipoDeOperacion) {
		this.tipoDeOperacion = tipoDeOperacion;
	}

	/**
	 * Gets the numero de ticket.
	 *
	 * @return the numero de ticket
	 */
	public String getNumeroDeTicket() {
		return numeroDeTicket;
	}

	/**
	 * Setter para numero de ticket.
	 *
	 * @param numeroDeTicket
	 *            el nuevo numero de ticket
	 */
	public void setNumeroDeTicket(String numeroDeTicket) {
		this.numeroDeTicket = numeroDeTicket;
	}

	/**
	 * Gets the sucursal origen.
	 *
	 * @return the sucursal origen
	 */
	public Sucursal getSucursalOrigen() {
		return sucursalOrigen;
	}

	/**
	 * Setter para sucursal origen.
	 *
	 * @param sucursalOrigen
	 *            el nuevo sucursal origen
	 */
	public void setSucursalOrigen(Sucursal sucursalOrigen) {
		this.sucursalOrigen = sucursalOrigen;
	}

	/**
	 * Gets the usuario banco.
	 *
	 * @return the usuario banco
	 */
	public String getUsuarioBanco() {
		return usuarioBanco;
	}

	/**
	 * Setter para usuario banco.
	 *
	 * @param usuarioBanco
	 *            el nuevo usuario banco
	 */
	public void setUsuarioBanco(String usuarioBanco) {
		this.usuarioBanco = usuarioBanco;
	}

	/**
	 * Gets the importe operacion.
	 *
	 * @return the importe operacion
	 */
	public BigDecimal getImporteOperacion() {
		return importeOperacion;
	}

	/**
	 * Setter para importe operacion.
	 *
	 * @param importeOperacion
	 *            el nuevo importe operacion
	 */
	public void setImporteOperacion(BigDecimal importeOperacion) {
		this.importeOperacion = importeOperacion;
	}

	/**
	 * Gets the estado.
	 *
	 * @return the estado
	 */
	public EstadoOperacionMovimimiento getEstado() {
		return estado;
	}

	/**
	 * Setter para estado.
	 *
	 * @param estado
	 *            el nuevo estado
	 */
	public void setEstado(EstadoOperacionMovimimiento estado) {
		this.estado = estado;
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
	 * Setter para fecha.
	 *
	 * @param fecha
	 *            el nuevo fecha
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder().append(divisa).append(fecha).append(hora).append(numeroDeCuenta)
				.append(numeroDeTicket).append(tipoDeCuenta).append(tipoDeOperacion);
		return hcb.toHashCode();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (!(this.getClass() == obj.getClass())) {
			return false;
		}
		MovimientoDeCuenta other = (MovimientoDeCuenta) obj;
		if (divisa != other.divisa) {
			return false;
		}
		if (fecha == null) {
			if (other.fecha != null) {
				return false;
			}
		} else if (!fecha.equals(other.fecha)) {
			return false;
		}
		if (hora == null) {
			if (other.hora != null) {
				return false;
			}
		} else if (!hora.equals(other.hora)) {
			return false;
		}
		if (numeroDeCuenta == null) {
			if (other.numeroDeCuenta != null) {
				return false;
			}
		} else if (!numeroDeCuenta.equals(other.numeroDeCuenta)) {
			return false;
		}
		if (numeroDeTicket == null) {
			if (other.numeroDeTicket != null) {
				return false;
			}
		} else if (!numeroDeTicket.equals(other.numeroDeTicket)) {
			return false;
		}
		if (tipoDeCuenta == null) {
			if (other.tipoDeCuenta != null) {
				return false;
			}
		} else if (!tipoDeCuenta.equals(other.tipoDeCuenta)) {
			return false;
		}
		if (tipoDeOperacion != other.tipoDeOperacion) {
			return false;
		}
		return true;
	}

}
