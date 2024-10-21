/*
 * 
 */
package ar.com.santanderrio.obp.servicios.prestamos.view;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionDTO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * The Class ResultadoSimulacionView.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResultadoAltaSimulacionPreaprobadoView {

	/** The importe. */
	private String importe;

	/** The datos del prestamo. */
	private List<ItemView> datosDelPrestamo;

	/** The tasas. */
	private List<ItemView> tasas;

	private String legal;

	private String tyc;

	private String mensajeFeedback;
	
	private String nroDeComprobante;
	
	private String nroPrestamo;
	
	private String nroCuota;
	
	private String nroCuenta;

	private String fechaHora;
	
	private AutentificacionDTO desafio;

	private String token;

	public AutentificacionDTO getDesafio() {
		return desafio;
	}

	private boolean openStack;

	public void setDesafio(AutentificacionDTO desafio) {
		this.desafio = desafio;
	}

	/**
	 * Instantiates a new resultado simulacion view.
	 *
	 * @param builder the builder
	 */
	private ResultadoAltaSimulacionPreaprobadoView(ResultadoSimulacionViewBuilder builder) {
		this.importe = builder.importe;
		this.datosDelPrestamo = builder.datosDelPrestamo;
		this.tasas = builder.tasas;
	}

	public ResultadoAltaSimulacionPreaprobadoView(){}
	
	/**
	 * Gets the datos del prestamo.
	 *
	 * @return the datos del prestamo
	 */
	public List<ItemView> getDatosDelPrestamo() {
		return datosDelPrestamo;
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

	public String getLegal() {
		return legal;
	}

	public void setLegal(String legal) {
		this.legal = legal;
	}

	public String getTyc() {
		return tyc;
	}

	public void setTyc(String tyc) {
		this.tyc = tyc;
	}

	public String getMensajeFeedback() {
		return mensajeFeedback;
	}

	public void setMensajeFeedback(String mensajeFeedback) {
		this.mensajeFeedback = mensajeFeedback;
	}

	public String getNroDeComprobante() {
		return nroDeComprobante;
	}

	public void setNroDeComprobante(String nroDeComprobante) {
		this.nroDeComprobante = nroDeComprobante;
	}

	public String getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(String fechaHora) {
		this.fechaHora = fechaHora;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public boolean isOpenStack() {
		return openStack;
	}

	public void setOpenStack(boolean openStack) {
		this.openStack = openStack;
	}

	public String getNroPrestamo() {
		return nroPrestamo;
	}

	public void setNroPrestamo(String nroPrestamo) {
		this.nroPrestamo = nroPrestamo;
	}

	public String getNroCuota() {
		return nroCuota;
	}

	public void setNroCuota(String nroCuota) {
		this.nroCuota = nroCuota;
	}

	public String getNroCuenta() {
		return nroCuenta;
	}

	public void setNroCuenta(String nroCuenta) {
		this.nroCuenta = nroCuenta;
	}

	/**
	 * The Class ResultadoSimulacionViewBuilder.
	 */
	public static class ResultadoSimulacionViewBuilder {

		/** The importe. */
		private String importe;

		/** The datos del prestamo. */
		private List<ItemView> datosDelPrestamo = new ArrayList<ItemView>();

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
		 * @param importe the importe
		 * @return the resultado simulacion view builder
		 */
		public ResultadoSimulacionViewBuilder importe(String importe) {
			this.importe = DivisaEnum.PESO.getSimbolo()
					+ ISBANStringUtils.agregadorDecimales(ISBANStringUtils.formatearNumericoConPuntoComaYDosdecimales(importe,4));
			return this;
		}

		/**
		 * Importe neto item.
		 *
		 * @param importe the importe
		 * @return the resultado simulacion view builder
		 */
		public ResultadoSimulacionViewBuilder importeNetoLegalItem(String importe) {
			this.datosDelPrestamo.add(new ItemView("Importe neto a acreditar", DivisaEnum.PESO.getSimbolo()
					+ ISBANStringUtils.agregadorDecimales(ISBANStringUtils.formatearNumericoConPuntoComaYDosdecimales(importe,4))));
			return this;
		}

		/**
		 * Comision por otorgamiento.
		 * 
		 * @param comisionOtorgamiento
		 * @return the resultado simulacion view builder
		 */
		public ResultadoSimulacionViewBuilder comisionPorOtorgamiento(String comisionOtorgamiento) {
			Double importe = new Double(comisionOtorgamiento);
			if (importe.compareTo(Double.valueOf(0)) > 0)
				this.datosDelPrestamo
						.add(new ItemView("Comisión de otorgamiento", DivisaEnum.PESO.getSimbolo()
								+ISBANStringUtils.formatearNumericoConPuntoComaYDosdecimales(comisionOtorgamiento,4)));
			return this;
		}

		/**
		 * Impuestos.
		 *
		 * @param impuestos the impuestos
		 * @return the resultado simulacion view builder
		 */
		public ResultadoSimulacionViewBuilder impuestos(String impuestos) {
			Double importe = new Double(impuestos);
			if (importe.compareTo(Double.valueOf(0)) > 0)
				this.datosDelPrestamo.add(new ItemView("Impuestos", DivisaEnum.PESO.getSimbolo()
						+ISBANStringUtils.formatearNumericoConPuntoComaYDosdecimales(impuestos,4)));
			return this;
		}

		/**
		 * Cuenta destino item.
		 *
		 * @param cuenta the cuenta
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
		 * @param cuotas the cuotas
		 * @return the resultado simulacion view builder
		 */
		public ResultadoSimulacionViewBuilder cantidadCuotasLegalItem(String cuotas, String legal) {
			this.datosDelPrestamo.add(new ItemView("Cantidad de cuotas " + legal, cuotas));
			return this;
		}

		/**
		 * Motivo prestamo item.
		 *
		 * @param destinoPrestmo the destino prestmo
		 * @return the resultado simulacion view builder
		 */
		public ResultadoSimulacionViewBuilder motivoPrestamoItem(String destinoPrestmo) {
			if (destinoPrestmo != null) {
				this.datosDelPrestamo.add(new ItemView("Destino de los fondos", destinoPrestmo));
			}
			return this;
		}

		/**
		 * Fecha primera cuota.
		 *
		 * @param fecha the fecha
		 * @return the resultado simulacion view builder
		 */
		public ResultadoSimulacionViewBuilder fechaPrimeraCuotaLegalItem(String fecha) {
			this.datosDelPrestamo.add(new ItemView("Fecha de la primera cuota ", fecha));
			return this;
		}

		/**
		 * Capital intereses periodo item.
		 *
		 * @param capital
		 *            the capital
		 * @return the resultado simulacion view builder
		 */
		public ResultadoSimulacionViewBuilder capitalInteresesCuotaConstanteItem(String capital, String legal) {
			this.datosDelPrestamo.add(
					new ItemView("Importe total de la primera cuota " + legal, DivisaEnum.PESO.getSimbolo()
							+ISBANStringUtils.formatearNumericoConPuntoComaYDosdecimales(capital,4)));
			return this;
		}

		/**
		 * Tipo tasa item.
		 *
		 * @param tasa the tasa
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
		 * @param tna the tna
		 * @return the resultado simulacion view builder
		 */
		public ResultadoSimulacionViewBuilder tnaItem(String tna) {
			this.tasas.add(new ItemView("Tasa Nominal Anual (T.N.A)", formatearPorcentajeDeTrama(tna)));
			return this;
		}

		/**
		 * Tea item.
		 *
		 * @param tea the tea
		 * @return the resultado simulacion view builder
		 */
		public ResultadoSimulacionViewBuilder teaItem(String tea) {
			this.tasas.add(new ItemView("Tasa Efectiva Anual (T.E.A)", formatearPorcentajeDeTrama(tea)));
			return this;
		}

		/**
		 * Builds the.
		 *
		 * @return the resultado simulacion view
		 */
		public ResultadoAltaSimulacionPreaprobadoView build() {
			return new ResultadoAltaSimulacionPreaprobadoView(this);
		}

		/**
		 * Formatear porcentaje de trama.
		 *
		 * @param numero the numero
		 * @return the string
		 */
		private String formatearPorcentajeDeTrama(String numero) {
			return ISBANStringUtils.formatearNumericoConPuntoComaYDosdecimales(numero, 6) + " %";
		}

	}
}