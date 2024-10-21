/*
 * 
 */
package ar.com.santanderrio.obp.servicios.simuladorprestamo.dto;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.prestamos.entity.CalificacionCrediticiaOutEntity;
import ar.com.santanderrio.obp.servicios.prestamos.entity.PrestamoPermitidoEntity;
import ar.com.santanderrio.obp.servicios.prestamos.entity.PrestamoPermitidoOutEntity;
import ar.com.santanderrio.obp.servicios.prestamos.sei.RangoCuota;
import ar.com.santanderrio.obp.servicios.prestamos.view.LimitesPrestamoView;

/**
 * The Class ConfiguracionPrestamoDTO.
 */
public class ConfiguracionPrestamoDTO {

	/** The calificacion crediticia. */
	private CalificacionCrediticiaOutEntity calificacionCrediticia;

	/** The prestamo permitido. */
	private PrestamoPermitidoOutEntity prestamoPermitido;

	/** The importe maximo. */
	private String importeMaximo;

	/** The importe maximo formateado. */
	private String importeMaximoFormateado;

	/** The importe minimo. */
	private String importeMinimo;

	/** The importe minimo formateado. */
	private String importeMinimoFormateado;

	/** The linea disponible. */
	private String lineaDisponible;

	/** The linea total. */
	private String lineaTotal;

	/** The cuota minima. */
	private String cuotaMinima;

	/** The cuota maxima. */
	private String cuotaMaxima;

	/** The rangos de cuotas. */
	private List<RangoCuota> rangosDeCuotas;

	/** The fecha inicio pago min. */
	private String fechaInicioPagoMin;

	/** The fecha inicio pago max. */
	private String fechaInicioPagoMax;

	/** The importe mensaje inhabilitado. */
	private String mensajeInhabilitado;

	/** The limites. */
	private List<LimitesPrestamoView> limites;
	
	/** Default constructor. */
	public ConfiguracionPrestamoDTO() {
		super();
	}

	/**
	 * Instantiates a new configuracion prestamo DTO.
	 *
	 * @param calificacionCrediticia
	 *            the calificacion crediticia
	 * @param prestamoPermitido
	 *            the prestamo permitido
	 * @param rangosDeCuotas
	 *            the rangos de cuotas
	 * @throws BusinessException
	 *             the business exception
	 */
	public ConfiguracionPrestamoDTO(CalificacionCrediticiaOutEntity calificacionCrediticia,
			PrestamoPermitidoOutEntity prestamoPermitido, List<RangoCuota> rangosDeCuotas) throws BusinessException {

		this.calificacionCrediticia = calificacionCrediticia;
		this.prestamoPermitido = prestamoPermitido;
		this.rangosDeCuotas = rangosDeCuotas;
		if (rangosDeCuotas.size()>0) {
			this.cuotaMinima = this.rangosDeCuotas.get(0).getCantMinCuotas();
			this.cuotaMaxima = this.rangosDeCuotas.get(this.rangosDeCuotas.size() - 1).getCantMaxCuotas();
		}
		formatearRangosDeCuotas();

		if ("001".equals(calificacionCrediticia.getCodigoInhabilitado())&& prestamoPermitido != null) {
			this.lineaTotal = "$ " + ISBANStringUtils
					.formatearSaldosConCerosYSignos(calificacionCrediticia.getPorcentajeLimitePrestamo());
			Map<String, String> importesPerm = getPmoPermMinMax(prestamoPermitido.getListaResult());
			BigDecimal maxImpPrest = formatearValor(importesPerm.get("importePermMax"), 4);
			BigDecimal importeDisponiblePrestamo = formatearValor(calificacionCrediticia.getImporteDisponiblePrestamo(),
					2);
			if (maxImpPrest.compareTo(importeDisponiblePrestamo) == (-1)) {
				this.lineaDisponible = formatearValorCuatroDecimales(maxImpPrest.toString());
				this.importeMaximo = maxImpPrest.toString();
				this.importeMaximoFormateado = "$ " + formatearValorCuatroDecimales(maxImpPrest.toString());
			} else if (BigDecimal.ZERO.equals(importeDisponiblePrestamo)) {
				this.lineaDisponible = "$ 0,00";
				this.importeMaximo = "0";
				this.importeMaximoFormateado = "$ 0,00";
			} else {
				this.lineaDisponible = "$ "
						+ ISBANStringUtils.formatearSaldosConCerosYSignos(importeDisponiblePrestamo.toString());
				this.importeMaximo = importeDisponiblePrestamo.toString();
				this.importeMaximoFormateado = "$ "
						+ ISBANStringUtils.formatearSaldosConCerosYSignos(importeDisponiblePrestamo.toString());
			}
			String valorMinimo = importesPerm.get("importePermMin");
			this.importeMinimo = formatearValorParaCalcular(ISBANStringUtils.eliminarCeros(valorMinimo), 4);
			this.importeMinimoFormateado = "$ " + formatearValorCuatroDecimales(valorMinimo);
		} else {
			this.importeMinimo = StringUtils.EMPTY;
			this.importeMinimoFormateado = StringUtils.EMPTY;
			this.importeMaximo = StringUtils.EMPTY;
			this.importeMaximoFormateado = StringUtils.EMPTY;
			this.lineaDisponible = StringUtils.EMPTY;
			this.lineaTotal = StringUtils.EMPTY;
		}
	}

	/**
	 * Gets the pmo perm min max.
	 *
	 * @param listaPerm the lista perm
	 * @return the pmo perm min max
	 */
	private Map<String, String> getPmoPermMinMax(List<PrestamoPermitidoEntity> listaPerm) {
	    BigDecimal importe1 = null;
        BigDecimal importe2 = null;
        PrestamoPermitidoEntity prestamoMayor = null;
        PrestamoPermitidoEntity prestamoMenor = null;

        for (PrestamoPermitidoEntity prestamoPermitidoEntity : listaPerm) {
            if (prestamoMayor == null) {
                prestamoMayor = prestamoPermitidoEntity;
            } else {
                importe1 = ISBANStringUtils.stringToBigDecimal(prestamoMayor.getMaxImpPrest(), 13, 4, false);
                importe2 = ISBANStringUtils.stringToBigDecimal(prestamoPermitidoEntity.getMaxImpPrest(), 13, 4,
                        false);
                if (importe1.compareTo(importe2) < 0) {
                    prestamoMayor = prestamoPermitidoEntity;
                }
            }
            if (prestamoMenor == null) {
                prestamoMenor = prestamoPermitidoEntity;
            } else {
                importe1 = ISBANStringUtils.stringToBigDecimal(prestamoMenor.getMinImpPrest(), 13, 4, false);
                importe2 = ISBANStringUtils.stringToBigDecimal(prestamoPermitidoEntity.getMinImpPrest(), 13, 4,
                        false);
                if (importe1.compareTo(importe2) > 0) {
                    prestamoMenor = prestamoPermitidoEntity;
                }
            }
        }

        Map<String, String> retMap = new HashMap<String, String>();
        retMap.put("importePermMin", prestamoMenor.getMinImpPrest());
        retMap.put("importePermMax", prestamoMayor.getMaxImpPrest());

        return retMap;
    }

    /**
	 * Gets the fecha inicio pago min.
	 *
	 * @return the fecha inicio pago min
	 */
	public String getFechaInicioPagoMin() {
		return fechaInicioPagoMin;
	}

	/**
	 * Sets the fecha inicio pago min.
	 *
	 * @param fechaInicioPagoMin
	 *            the new fecha inicio pago min
	 */
	public void setFechaInicioPagoMin(String fechaInicioPagoMin) {
		this.fechaInicioPagoMin = fechaInicioPagoMin;
	}

	/**
	 * Gets the fecha inicio pago max.
	 *
	 * @return the fecha inicio pago max
	 */
	public String getFechaInicioPagoMax() {
		return fechaInicioPagoMax;
	}

	/**
	 * Sets the fecha inicio pago max.
	 *
	 * @param fechaInicioPagoMax
	 *            the new fecha inicio pago max
	 */
	public void setFechaInicioPagoMax(String fechaInicioPagoMax) {
		this.fechaInicioPagoMax = fechaInicioPagoMax;
	}

	/**
	 * Gets the mensaje inhabilitado.
	 *
	 * @return the mensaje inhabilitado
	 */
	public String getMensajeInhabilitado() {
		return mensajeInhabilitado;
	}

	/**
	 * Sets the mensaje inhabilitado.
	 *
	 * @param mensajeInhabilitado
	 *            the new mensaje inhabilitado
	 */
	public void setMensajeInhabilitado(String mensajeInhabilitado) {
		this.mensajeInhabilitado = mensajeInhabilitado;
	}

	/**
	 * Gets the importe maximo.
	 *
	 * @return the importe maximo
	 */
	public String getImporteMaximo() {
		return importeMaximo;
	}

	/**
	 * Gets the calificacion crediticia.
	 *
	 * @return the calificacion crediticia
	 */
	public CalificacionCrediticiaOutEntity getCalificacionCrediticia() {
		return calificacionCrediticia;
	}

	/**
	 * Gets the prestamo permitido.
	 *
	 * @return the prestamo permitido
	 */
	public PrestamoPermitidoOutEntity getPrestamoPermitido() {
		return prestamoPermitido;
	}

	/**
	 * Gets the importe maximo formateado.
	 *
	 * @return the importe maximo formateado
	 */
	public String getImporteMaximoFormateado() {
		return importeMaximoFormateado;
	}

	/**
	 * Gets the importe minimo.
	 *
	 * @return the importe minimo
	 */
	public String getImporteMinimo() {
		return importeMinimo;
	}

	/**
	 * Gets the importe minimo formateado.
	 *
	 * @return the importe minimo formateado
	 */
	public String getImporteMinimoFormateado() {
		return importeMinimoFormateado;
	}

	/**
	 * Gets the linea disponible.
	 *
	 * @return the linea disponible
	 */
	public String getLineaDisponible() {
		return lineaDisponible;
	}

	/**
	 * Gets the linea total.
	 *
	 * @return the linea total
	 */
	public String getLineaTotal() {
		return lineaTotal;
	}

	/**
	 * Formatear valor.
	 *
	 * @param valor
	 *            the valor
	 * @param cantidadLugares
	 *            the cantidad lugares
	 * @return the big decimal
	 */
	private BigDecimal formatearValor(String valor, int cantidadLugares) {
		if (("0").equals(ISBANStringUtils.eliminarCeros(valor))) {
			return BigDecimal.ZERO;
		}
		String numeroFormat = ISBANStringUtils.eliminarCeros(valor);
		String numeroParteUno = numeroFormat.substring(0, numeroFormat.length() - cantidadLugares);
		String numeroParteDos = numeroFormat.substring(numeroFormat.length() - cantidadLugares);
		String numeroParaConvertir = numeroParteUno + "." + numeroParteDos;
		return new BigDecimal(numeroParaConvertir);
	}

	/**
	 * Formatear valor cuatro decimales.
	 *
	 * @param valor
	 *            the valor
	 * @return the string
	 */
	private String formatearValorCuatroDecimales(String valor) {
		String importe = valor.substring(0, valor.length() - 2);
		return ISBANStringUtils.formatearSaldosConCerosYSignos(importe);
	}

	/**
	 * Formatear valor para calcular.
	 *
	 * @param valor
	 *            the valor
	 * @param cantidadLugares
	 *            the cantidad lugares
	 * @return the string
	 */
	private String formatearValorParaCalcular(String valor, int cantidadLugares) {
		String importe = valor.substring(0, valor.length() - cantidadLugares);
		return importe.replaceAll("\\.", "");
	}

	/**
	 * Importe minimo to big decimal.
	 *
	 * @return the big decimal
	 */
	public BigDecimal importeMinimoToBigDecimal() {
		return new BigDecimal(this.importeMinimo);
	}

	/**
	 * Importe maximo to big decimal.
	 *
	 * @return the big decimal
	 */
	public BigDecimal importeMaximoToBigDecimal() {
		return new BigDecimal(this.importeMaximo);
	}

	/**
	 * Gets the cuota minima.
	 *
	 * @return the cuota minima
	 */
	public String getCuotaMinima() {
		return cuotaMinima;
	}

	/**
	 * Gets the cuota maxima.
	 *
	 * @return the cuota maxima
	 */
	public String getCuotaMaxima() {
		return cuotaMaxima;
	}

	/**
	 * Gets the rangos de cuotas.
	 *
	 * @return the rangos de cuotas
	 */
	public List<RangoCuota> getRangosDeCuotas() {
		return rangosDeCuotas;
	}

	/**
	 * Gets the limites.
	 *
	 * @return the limites
	 */
	public List<LimitesPrestamoView> getLimites() {
		return limites;
	}

	/**
	 * Sets the limites.
	 *
	 * @param limites
	 *            the new limites
	 */
	public void setLimites(List<LimitesPrestamoView> limites) {
		this.limites = limites;
	}

	/**
	 * evalua si el importeMaximo asignado al cliente es suficiente para
	 * solicitar un prestamo con minimo importe.
	 * si no se seteo importeMaximo e importeMinimo, retorna false
	 *
	 * @return true, if successful
	 */
	public boolean lineaCrediticiaSinMontoPermitido() {
		try {
			BigDecimal bigImporteMaximo = new BigDecimal(this.importeMaximo);
			BigDecimal bigImporteMinimo = new BigDecimal(this.importeMinimo);

			if ((1) == bigImporteMaximo.compareTo(bigImporteMinimo)) {
				return true;
			}
			return false;
		}catch (NumberFormatException nfe) {
			return false;
		}
	}

	/**
	 * Formatear rangos de cuotas.
	 */
	private void formatearRangosDeCuotas() {
		for (RangoCuota rango : this.rangosDeCuotas) {
			if ("F".equals(rango.getTipoDeTasa())) {
				rango.setTipoDeTasa("Fija");
			} else {
				rango.setTipoDeTasa("Variable");
			}
			String tna = rango.getTna();
			String tnaParteUno = ISBANStringUtils.eliminarCeros(tna.substring(0, 3));
	        String tnaParteDos = tna.substring(3, 5);
	        String tnaFormateada = tnaParteUno + "," + tnaParteDos + " %";
			rango.setTna(tnaFormateada);
		}
	}

}