/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.cuentasaldo.dao;

import java.util.Date;

/**
 * Clase que Modela Resultado del store.
 */
public class RtaMovimientosCuentaBP {
	
	/** The sucursal. */
	private String sucursal;
		
	/** The cuenta. */
	private String cuenta;
	
	/** The divisa. */
	private String divisa;

	/** The numero mov. */
	private String numeroMov;

	/** The fecha valor. */
	private String fechaValor;
	
	/** The fecha operacion. */
	private String fechaOperacion;
	
	/** The fecha operacion date. */
	private Date fechaOperacionDate;
	
	/** The concepto. */
	private String concepto;

	/** The texto del apunte. */
	private String textoDelApunte;
	
	/** The saldo inicio.*/
	private String saldoInicio;

	/** The ingresos.*/
	private String ingresos;
	
	/**The egresos.*/
	private String egresos;
	
	/** The saldoFinal. */
	private String saldoFinal;
	
	/** The indicador Movimiento. */
	private String indMov;

	
	/**
	 * Instantiates a new rta movimientos cuenta BP.
	 *
	 * @param builder
	 *            the builder
	 */
	public RtaMovimientosCuentaBP(RtaMovimientosCuentaBPBuilder builder) {
		this.sucursal = builder.sucursal;
		this.cuenta = builder.cuenta;
		this.divisa = builder.divisa;
		this.numeroMov = builder.numeroMov;
		this.fechaValor = builder.fechaValor;
		this.fechaOperacion = builder.fechaOperacion;
		this.concepto = builder.concepto;
		this.textoDelApunte = builder.textoDelApunte;
		this.saldoInicio = builder.saldoInicio;
		this.ingresos = builder.ingresos;
		this.egresos = builder.egresos;
		this.saldoFinal = builder.saldoFinal;
		this.indMov = builder.indMov;
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
	 * Gets the cuenta.
	 *
	 * @return the cuenta
	 */
	public String getCuenta() {
		return cuenta;
	}


	/**
	 * Gets the divisa.
	 *
	 * @return the divisa
	 */
	public String getDivisa() {
		return divisa;
	}


	/**
	 * Gets the numero mov.
	 *
	 * @return the numeroMov
	 */
	public String getNumeroMov() {
		return numeroMov;
	}


	/**
	 * Gets the fecha valor.
	 *
	 * @return the fechaValor
	 */
	public String getFechaValor() {
		return fechaValor;
	}


	/**
	 * Gets the fecha operacion.
	 *
	 * @return the fechaOperacion
	 */
	public String getFechaOperacion() {
		return fechaOperacion;
	}


	/**
	 * Gets the concepto.
	 *
	 * @return the concepto
	 */
	public String getConcepto() {
		return concepto;
	}


	/**
	 * Gets the texto del apunte.
	 *
	 * @return the textoDelApunte
	 */
	public String getTextoDelApunte() {
		return textoDelApunte;
	}


	/**
	 * Gets the saldo inicio.
	 *
	 * @return the saldoInicio
	 */
	public String getSaldoInicio() {
		return saldoInicio;
	}

	/**
	 * Gets the ingresos.
	 *
	 * @return the ingresos
	 */
	public String getIngresos() {
		return ingresos;
	}


	/**
	 * Gets the egresos.
	 *
	 * @return the egresos
	 */
	public String getEgresos() {
		return egresos;
	}


	/**
	 * Gets the saldo final.
	 *
	 * @return the saldoFinal
	 */
	public String getSaldoFinal() {
		return saldoFinal;
	}


	/**
	 * Gets the ind mov.
	 *
	 * @return the indMov
	 */
	public String getIndMov() {
		return indMov;
	}

	/**
	 * Gets the fecha operacion date.
	 *
	 * @return the fechaOperacionDate
	 */
	public Date getFechaOperacionDate() {
		return fechaOperacionDate;
	}


	/**
	 * Sets the fecha operacion date.
	 *
	 * @param fechaOperacionDate
	 *            the fechaOperacionDate to set
	 */
	public void setFechaOperacionDate(Date fechaOperacionDate) {
		this.fechaOperacionDate = fechaOperacionDate;
	}

	/**
	 * The Class RtaMovimientosCuentaBPBuilder.
	 */
	public static class RtaMovimientosCuentaBPBuilder {
		
		/** The sucursal. */
		private String sucursal;
			
		/** The cuenta. */
		private String cuenta;
		
		/** The divisa. */
		private String divisa;

		/** The numero mov. */
		private String numeroMov;

		/** The fecha valor. */
		private String fechaValor;
		
		/** The fecha operacion. */
		private String fechaOperacion;
		
		/** The concepto. */
		private String concepto;

		/** The texto del apunte. */
		private String textoDelApunte;
		
		/** The saldo inicio.*/
		private String saldoInicio;

		/** The ingresos.*/
		private String ingresos;
		
		/**The egresos.*/
		private String egresos;
		
		/** The saldoFinal. */
		private String saldoFinal;
		
		/** The indicador Movimiento. */
		private String indMov;
		
		
		/**
		 * Instantiates a new rta movimientos cuenta BP builder.
		 */
		public RtaMovimientosCuentaBPBuilder() {
			super();
		}
		
		
		/**
		 * Sucursal.
		 *
		 * @param sucursal
		 *            the sucursal
		 * @return the rta movimientos cuenta BP builder
		 */
		public RtaMovimientosCuentaBPBuilder sucursal(String sucursal) {
			this.sucursal = sucursal;
			return this;
		}
		
		/**
		 * Cuenta.
		 *
		 * @param cuenta
		 *            the cuenta
		 * @return the rta movimientos cuenta BP builder
		 */
		public RtaMovimientosCuentaBPBuilder cuenta(String cuenta) {
			this.cuenta = cuenta;
			return this;
		}
		
		/**
		 * Divisa.
		 *
		 * @param divisa
		 *            the divisa
		 * @return the rta movimientos cuenta BP builder
		 */
		public RtaMovimientosCuentaBPBuilder divisa(String divisa) {
			this.divisa = divisa;
			return this;
		}
		
		/**
		 * Numero mov.
		 *
		 * @param numeroMov
		 *            the numero mov
		 * @return the rta movimientos cuenta BP builder
		 */
		public RtaMovimientosCuentaBPBuilder numeroMov(String numeroMov) {
			this.numeroMov = numeroMov;
			return this;
		}
		
		/**
		 * Fecha valor.
		 *
		 * @param fechaValor
		 *            the fecha valor
		 * @return the rta movimientos cuenta BP builder
		 */
		public RtaMovimientosCuentaBPBuilder fechaValor(String fechaValor) {
			this.fechaValor = fechaValor;
			return this;
		}
		
		/**
		 * Fecha operacion.
		 *
		 * @param fechaOperacion
		 *            the fecha operacion
		 * @return the rta movimientos cuenta BP builder
		 */
		public RtaMovimientosCuentaBPBuilder fechaOperacion(String fechaOperacion) {
			this.fechaOperacion = fechaOperacion;
			return this;
		}
		
		/**
		 * Concepto.
		 *
		 * @param concepto
		 *            the concepto
		 * @return the rta movimientos cuenta BP builder
		 */
		public RtaMovimientosCuentaBPBuilder concepto(String concepto) {
			this.concepto = concepto;
			return this;
		}
		
		/**
		 * Texto del apunte.
		 *
		 * @param textoDelApunte
		 *            the texto del apunte
		 * @return the rta movimientos cuenta BP builder
		 */
		public RtaMovimientosCuentaBPBuilder textoDelApunte(String textoDelApunte) {
			this.textoDelApunte = textoDelApunte;
			return this;
		}
		
		/**
		 * Saldo inicio.
		 *
		 * @param saldoInicio
		 *            the saldo inicio
		 * @return the rta movimientos cuenta BP builder
		 */
		public RtaMovimientosCuentaBPBuilder saldoInicio(String saldoInicio) {
			this.saldoInicio = saldoInicio;
			return this;
		}
		
		/**
		 * Ingresos.
		 *
		 * @param ingresos
		 *            the ingresos
		 * @return the rta movimientos cuenta BP builder
		 */
		public RtaMovimientosCuentaBPBuilder ingresos(String ingresos) {
			this.ingresos = ingresos;
			return this;
		}
		
		/**
		 * Egresos.
		 *
		 * @param egresos
		 *            the egresos
		 * @return the rta movimientos cuenta BP builder
		 */
		public RtaMovimientosCuentaBPBuilder egresos(String egresos) {
			this.egresos = egresos;
			return this;
		}
		
		/**
		 * Saldo final.
		 *
		 * @param saldoFinal
		 *            the saldo final
		 * @return the rta movimientos cuenta BP builder
		 */
		public RtaMovimientosCuentaBPBuilder saldoFinal(String saldoFinal) {
			this.saldoFinal = saldoFinal;
			return this;
		}
		
		/**
		 * Ind mov.
		 *
		 * @param indMov
		 *            the ind mov
		 * @return the rta movimientos cuenta BP builder
		 */
		public RtaMovimientosCuentaBPBuilder indMov(String indMov) {
			this.indMov = indMov;
			return this;
		}
		
		/**
		 * Builds the.
		 *
		 * @return the rta movimientos cuenta BP
		 */
		public RtaMovimientosCuentaBP build() {
			return (new RtaMovimientosCuentaBP(this));
		}
		
	}
	
	
	
}