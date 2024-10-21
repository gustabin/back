/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.comun;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Enum PeriodoRendimientoTenenciaEnum.
 */
public enum PeriodoRendimientoTenenciaEnum {

	/** Ultimos 30 dias. */
	ULT30DIAS("30D", "Últimos 30 días"),
	
	/** Ultimos 30 dias. */
	ULT60DIAS("60D", "Últimos 60 días"),
	
	/** Ultimos 30 dias. */
	ULT90DIAS("90D", "Últimos 90 días"),
	
	/** Ultimos 30 dias. */
	ULT365DIAS("365D", "Últimos 365 días"),

	/** The custodia. */
	/* Custodia */
	ANIOCURSO("ANIO", "Año en curso");

	/** The codigo. */
	private final String codigo;
	
	/** The codigo. */
	private final String descripcion;
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(PeriodoRendimientoTenenciaEnum.class);
	
	/**
	 * Instantiates a new periodo rendimiento tenencia enum.
	 *
	 * @param codigo
	 *            the codigo
	 * @param descripcion
	 *            the descripcion
	 */
	PeriodoRendimientoTenenciaEnum(String codigo, String descripcion){
		this.codigo = codigo;
		this.descripcion = descripcion;
	}

	/**
	 * Gets the codigo.
	 *
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * Gets the descripcion.
	 *
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}
	
	/**
	 * From codigo string.
	 *
	 * @param codigoPeriodo
	 *            the codigo periodo
	 * @return the periodo rendimiento tenencia enum
	 */
	public static PeriodoRendimientoTenenciaEnum fromCodigoString(String codigoPeriodo){
		for (PeriodoRendimientoTenenciaEnum periodo : PeriodoRendimientoTenenciaEnum.values()) {
			if(periodo.getCodigo().equals(codigoPeriodo)){
				return periodo;
			}
		}
		LOGGER.error("El codigo de periodo rendimiento buscado, no se encuentra dentro de los esperados.");
		return null;
	}

}
