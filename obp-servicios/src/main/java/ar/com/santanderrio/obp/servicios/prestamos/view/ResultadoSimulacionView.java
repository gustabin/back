/*
 * 
 */
package ar.com.santanderrio.obp.servicios.prestamos.view;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.simuladorprestamo.entity.DestinoPrestamo;

/**
 * The Class ResultadoSimulacionView.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ResultadoSimulacionView {

	/** The importe. */
	private String importe;

	/** The datos del prestamo. */
	private List<ItemView> datosDelPrestamo;

	/** The datos primera cuota. */
	private List<ItemView> datosPrimeraCuota;

	/** The tasas. */
	private List<ItemView> tasas;
	
	private String legal1simulacionConfirmacion;
	
	private String legal2simulacionConfirmacion;
	
	private String legal3simulacionConfirmacion;
	

	/**
	 * Instantiates a new resultado simulacion view.
	 *
	 * @param builder
	 *            the builder
	 */
	private ResultadoSimulacionView(ResultadoSimulacionViewBuilder builder) {
		this.importe = builder.importe;
		this.datosDelPrestamo = builder.datosDelPrestamo;
		this.datosPrimeraCuota = builder.datosPrimeraCuota;
		this.tasas = builder.tasas;
	}

	/**
	 * Gets the datos del prestamo.
	 *
	 * @return the datos del prestamo
	 */
	public List<ItemView> getDatosDelPrestamo() {
		return datosDelPrestamo;
	}

	/**
	 * Gets the datos primera cuota.
	 *
	 * @return the datos primera cuota
	 */
	public List<ItemView> getDatosPrimeraCuota() {
		return datosPrimeraCuota;
	}

	/**
	 * Gets the tasas.
	 *
	 * @return the tasas
	 */
	public List<ItemView> getTasas() {
		return tasas;
	}

	/**
	 * Gets the importe.
	 *
	 * @return the importe
	 */
	public String getImporte() {
		return importe;
	}

	/**
	 * The Class ResultadoSimulacionViewBuilder.
	 */
	public static class ResultadoSimulacionViewBuilder {

		/** The importe. */
		private String importe;

		/** The datos del prestamo. */
		private List<ItemView> datosDelPrestamo = new ArrayList<ItemView>();

		/** The datos primera cuota. */
		private List<ItemView> datosPrimeraCuota = new ArrayList<ItemView>();

		/** The tasas. */
		private List<ItemView> tasas = new ArrayList<ItemView>();

		/**
		 * Instantiates a new resultado simulacion view builder.
		 */
		public ResultadoSimulacionViewBuilder() {
			super();
		}

		/**
		 * Importe.
		 *
		 * @param importe
		 *            the importe
		 * @return the resultado simulacion view builder
		 */
		public ResultadoSimulacionViewBuilder importe(BigDecimal importe) {
			this.importe = "$ " + ISBANStringUtils.formatearSaldo(importe);
			return this;
		}

		/**
		 * Importe neto item.
		 *
		 * @param importe
		 *            the importe
		 * @return the resultado simulacion view builder
		 */
		public ResultadoSimulacionViewBuilder importeNetoItem(String importe) {
			this.datosDelPrestamo.add(new ItemView("Importe neto a acreditar", formatearSaldoDeTrama(importe)));
			return this;
		}

		/**
		 * Cuenta destino item.
		 *
		 * @param cuenta
		 *            the cuenta
		 * @return the resultado simulacion view builder
		 */
		public ResultadoSimulacionViewBuilder cuentaDestinoItem(Cuenta cuenta) {
			this.datosDelPrestamo.add(new ItemView("Cuenta destino", cuenta.obtenerNroCuentaFormateado(),
					cuenta.getTipoCuentaEnum().getDescripcion()));
			return this;
		}

		/**
		 * Cantidad cuotas item.
		 *
		 * @param cuotas
		 *            the cuotas
		 * @return the resultado simulacion view builder
		 */
		public ResultadoSimulacionViewBuilder cantidadCuotasItem(String cuotas) {
			this.datosDelPrestamo.add(new ItemView("Cantidad de cuotas", cuotas));
			return this;
		}

		/**
		 * Motivo prestamo item.
		 *
		 * @param destinoPrestmo
		 *            the destino prestmo
		 * @return the resultado simulacion view builder
		 */
		public ResultadoSimulacionViewBuilder motivoPrestamoItem(DestinoPrestamo destinoPrestmo) {
			if (destinoPrestmo != null) {
				this.datosDelPrestamo.add(new ItemView("Motivo del préstamo", destinoPrestmo.getDescripcionUG()));
			}
			return this;
		}

		/**
		 * Importe primera cuota item.
		 *
		 * @param importeCuota
		 *            the importe cuota
		 * @return the resultado simulacion view builder
		 */
		public ResultadoSimulacionViewBuilder importePrimeraCuotaItem(String importeCuota) {
			this.datosPrimeraCuota.add(new ItemView("Importe primera cuota", formatearSaldoDeTrama(importeCuota)));
			return this;
		}

		/**
		 * Fecha primer pago item.
		 *
		 * @param fecha
		 *            the fecha
		 * @return the resultado simulacion view builder
		 */
		public ResultadoSimulacionViewBuilder fechaPrimerPagoItem(String fecha) {
			this.datosPrimeraCuota.add(new ItemView("Fecha de primer pago", fecha));
			return this;
		}

		/**
		 * Capital intereses periodo item.
		 *
		 * @param capital
		 *            the capital
		 * @return the resultado simulacion view builder
		 */
		public ResultadoSimulacionViewBuilder capitalInteresesPeriodoItem(String capital) {
			this.datosPrimeraCuota.add(
					new ItemView("Capital e intereses compensatorios del período", formatearSaldoDeTrama(capital)));
			return this;
		}

		/**
		 * Cargo seguro vida item.
		 *
		 * @param cargos
		 *            the cargos
		 * @return the resultado simulacion view builder
		 */
		public ResultadoSimulacionViewBuilder cargoSeguroVidaItem(String cargos) {
			if (!cargos.matches("0+")) {
				this.datosPrimeraCuota.add(new ItemView("Cargos por seguro de vida", formatearSaldoDeTrama(cargos)));
			}
			return this;
		}

		/**
		 * Iva item.
		 *
		 * @param iva
		 *            the iva
		 * @return the resultado simulacion view builder
		 */
		public ResultadoSimulacionViewBuilder ivaItem(String iva) {
			this.datosPrimeraCuota.add(new ItemView("IVA", formatearSaldoDeTrama(iva)));
			return this;
		}

		/**
		 * Otros impuestos item.
		 *
		 * @param otrosImpuestos
		 *            the otros impuestos
		 * @return the resultado simulacion view builder
		 */
		public ResultadoSimulacionViewBuilder otrosImpuestosItem(String otrosImpuestos) {
			this.datosPrimeraCuota.add(new ItemView("Otros impuestos", formatearSaldoDeTrama(otrosImpuestos)));
			return this;
		}

		/**
		 * Tipo tasa item.
		 *
		 * @param tasa
		 *            the tasa
		 * @return the resultado simulacion view builder
		 */
		public ResultadoSimulacionViewBuilder tipoTasaItem(String tasa) {
			if ("F".equals(tasa)) {
				this.tasas.add(new ItemView("Tipo de tasa", "Fija"));
			} else {
				this.tasas.add(new ItemView("Tipo de tasa", "Variable"));
			}
			return this;
		}

		/**
		 * Tna item.
		 *
		 * @param tna
		 *            the tna
		 * @return the resultado simulacion view builder
		 */
		public ResultadoSimulacionViewBuilder tnaItem(String tna) {
			this.tasas.add(new ItemView("Tasa Nominal Anual (T.N.A)", formatearPorcentajeDeTrama(tna)));
			return this;
		}

		/**
		 * Tea item.
		 *
		 * @param tea
		 *            the tea
		 * @return the resultado simulacion view builder
		 */
		public ResultadoSimulacionViewBuilder teaItem(String tea) {
			this.tasas.add(new ItemView("Tasa Efectiva Anual (T.E.A)", formatearPorcentajeDeTrama(tea)));
			return this;
		}

		/**
		 * Cftna item.
		 *
		 * @param cftna
		 *            the cftna
		 * @return the resultado simulacion view builder
		 */
		public ResultadoSimulacionViewBuilder cftnaItem(String cftna) {
			this.tasas.add(new ItemView("Costo Financiero Total Efectivo Anual (Con Impuestos)", formatearPorcentajeDeTrama(cftna)));
			return this;
		}

		/**
		 * Cftna sin imp item.
		 *
		 * @param cftnaSinImp
		 *            the cftna sin imp
		 * @return the resultado simulacion view builder
		 */
		public ResultadoSimulacionViewBuilder cftnaSinImpItem(String cftnaSinImp) {
			this.tasas.add(new ItemView("Costo Financiero Total Efectivo Anual (Sin Impuestos)",
					formatearPorcentajeDeTrama(cftnaSinImp)));
			return this;
		}

		/**
		 * Importe en pesos item.
		 *
		 * @param importe
		 *            the importe
		 * @return the resultado simulacion view builder
		 */
		public ResultadoSimulacionViewBuilder importeEnPesosItem(BigDecimal importe) {
			this.datosDelPrestamo.add(new ItemView("Importe en pesos", "$ " + ISBANStringUtils.formatearSaldo(importe)));
			return this;
		}
		
		/**
		 * Importe en Uvas.
		 *
		 * @param importe
		 *            the importe
		 * @return the resultado simulacion view builder
		 */
		public ResultadoSimulacionViewBuilder importeEnUvas(String importe) {
			this.datosDelPrestamo.add(new ItemView("Importe en UVAs", formatearSaldoDeTramaSinSigno(importe)));
			return this;
		}		
		
		/**
		 * Importe neto uva item.
		 *
		 * @param importe
		 *            the importe
		 * @return the resultado simulacion view builder
		 */
		public ResultadoSimulacionViewBuilder importeNetoUvaItem(String importe) {
			this.datosDelPrestamo.add(new ItemView("Importe neto a acreditar en pesos", formatearSaldoDeTrama(importe)));
			return this;
		}
		
		/**
		 * Importe primera cuota en Uvas.
		 *
		 * @param importeCuota
		 *            the importe cuota
		 * @return the resultado simulacion view builder
		 */
		public ResultadoSimulacionViewBuilder importePrimeraCuotaEnUvas(String importeCuota) {
			this.datosPrimeraCuota.add(new ItemView("Importe primera cuota en UVAs", formatearSaldoDeTramaSinSigno(importeCuota)));
			return this;
		}
		
		/**
		 * Fecha de vencimiento Uva item.
		 *
		 * @param fecha
		 *            the fecha
		 * @return the resultado simulacion view builder
		 */
		public ResultadoSimulacionViewBuilder fechaVencimientoUvaItem(String fecha) {
			this.datosPrimeraCuota.add(new ItemView("Fecha de vencimiento", fecha));
			return this;
		}
		
		/**
		 * Importe cuota cte.
		 *
		 * @param importeCuota
		 *            the importe cuota
		 * @return the resultado simulacion view builder
		 */
		public ResultadoSimulacionViewBuilder importeCuotaCte(String importeCuota) {
			this.datosPrimeraCuota.add(new ItemView("Valor próximas cuotas en UVAs", formatearSaldoDeTramaSinSigno(importeCuota)));
			return this;
		}
		
		/**
		 * Cotizacion cambio.
		 *
		 * @param cotizCambio
		 *            the cotiz cambio
		 * @return the resultado simulacion view builder
		 */
		public ResultadoSimulacionViewBuilder cotizCambioItem(String cotizCambio) {
			this.datosPrimeraCuota.add(new ItemView("Cotización de UVA", "$" + formatearCotizacionDeTrama(cotizCambio)));
			return this;
		}
		
		/**
		 * Fecha cotizacion.
		 *
		 * @param fecha
		 *            the fecha
		 * @return the resultado simulacion view builder
		 */
		public ResultadoSimulacionViewBuilder fechaCotizItem(String fecha) {
			Date dt = ISBANStringUtils.formatearFecha(fecha, "yyyy-MM-dd");
			this.datosPrimeraCuota.add(new ItemView("Fecha de cotización de UVA", 
					ISBANStringUtils.formatearFecha(dt, "dd/MM/yyyy")));
			return this;
		}
		
		/**
		 * Tna Uva item.
		 *
		 * @param tna
		 *            the tna
		 * @return the resultado simulacion view builder
		 */
		public ResultadoSimulacionViewBuilder tnaUvaItem(String tna) {
			this.tasas.add(new ItemView("Tasa Nominal Anual (T.N.A)", formatearPorcentajeDeTrama(tna)));
			return this;
		}

		/**
		 * Tea Uva item.
		 *
		 * @param tea
		 *            the tea
		 * @return the resultado simulacion view builder
		 */
		public ResultadoSimulacionViewBuilder teaUvaItem(String tea) {
			this.tasas.add(new ItemView("Tasa Efectiva Anual (T.E.A)", formatearPorcentajeDeTrama(tea)));
			return this;
		}
		
		/**
		 * Gastos de otorgamiento.
		 *
		 * @param isGold 
		 *            the is gold
		 * @param gastosOtorgamiento 
		 *            the gastos otorgamiento
		 * @return the resultado simulacion view builder
		 */
		public ResultadoSimulacionViewBuilder gastosDeOtorgamiento(Boolean isGold, String gastosOtorgamiento) {
			if (isGold) {
				this.datosDelPrestamo.add(new ItemView("Gastos de otorgamiento", formatearSaldoDeTrama(gastosOtorgamiento)));
			}
			return this;
		}
		
		/**
		 * Builds the.
		 *
		 * @return the resultado simulacion view
		 */
		public ResultadoSimulacionView build() {
			return new ResultadoSimulacionView(this);
		}

		/**
		 * Formatear saldo de trama.
		 *
		 * @param numero
		 *            the numero
		 * @return the string
		 */
		private String formatearSaldoDeTrama(String numero) {
			StringBuilder numeroBuilder = new StringBuilder(numero);
			return "$ " + ISBANStringUtils
					.formatearSaldo(new BigDecimal(numeroBuilder.insert(numeroBuilder.length() - 4, ".").toString()));
		}

		/**
		 * Formatear porcentaje de trama.
		 *
		 * @param numero
		 *            the numero
		 * @return the string
		 */
		private String formatearPorcentajeDeTrama(String numero) {
			String parteUno = numero.substring(0, 3);
			String parteDos = numero.substring(3, 5);
			parteUno = ISBANStringUtils.eliminarCeros(parteUno);
			return parteUno + "," + parteDos + " %";
		}

		/**
		 * Formatear saldo de trama.
		 *
		 * @param numero
		 *            the numero
		 * @return the string
		 */
		private String formatearSaldoDeTramaSinSigno(String numero) {
			StringBuilder numeroBuilder = new StringBuilder(numero);
			return ISBANStringUtils
					.formatearSaldo(new BigDecimal(numeroBuilder.insert(numeroBuilder.length() - 4, ".").toString()));
		}

		/**
         * Formatear cotizacion de trama.
         *
         * @param numero
         *            the numero
         * @return the string
         */
        private String formatearCotizacionDeTrama(String numero) {
            StringBuilder numeroBuilder = new StringBuilder(numero);
            return ISBANStringUtils
                    .formatearSaldo(new BigDecimal(numeroBuilder.insert(numeroBuilder.length() - 5, ".").toString()));
        }
	}

	public String getLegal1simulacionConfirmacion() {
		return legal1simulacionConfirmacion;
	}

	public void setLegal1simulacionConfirmacion(String legal1simulacion) {
		this.legal1simulacionConfirmacion = legal1simulacion;
	}

	public String getLegal2simulacionConfirmacion() {
		return legal2simulacionConfirmacion;
	}

	public void setLegal2simulacionConfirmacion(String legal2simulacion) {
		this.legal2simulacionConfirmacion = legal2simulacion;
	}

	public String getLegal3simulacionConfirmacion() {
		return legal3simulacionConfirmacion;
	}

	public void setLegal3simulacionConfirmacion(String legal3simulacion) {
		this.legal3simulacionConfirmacion = legal3simulacion;
	}
}