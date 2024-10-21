/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetacoordenadas.entities;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import ar.com.santanderrio.obp.base.entities.Entity;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.CoordenadasOperacionDTO;

/**
 * Entidad que representa a una tarjeta de coordenadas para la validacion se
 * seguridad.
 *
 * @author marcelo.ruiz
 */
@Deprecated // usar CoordenadasOperacionDTO y acceder por medio de
			// AutentificacionManager.
// Cambiar para que IATX para que carge CoordenadasOperacionDTO y borrar luego
// esta clase
public class TarjetaCoordenada extends Entity {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 4217087019809946300L;

	/** The Constant COORDENADAS. */
	public static final String COORDENADAS = "COORDENADAS";

	/** The Constant COLUMNAS. */
	static final String[] COLUMNAS = { "A", "B", "C", "D", "E", "F", "G", "H", "I" };

	/** The numero. */
	private String numero;

	/** The fila X 1. */
	private String filaX1;

	/** The columna Y 1. */
	private String columnaY1;

	/** The coordenada valor 1. */
	private String coordenadaValor1;

	/** The fila X 2. */
	private String filaX2;

	/** The columna Y 2. */
	private String columnaY2;

	/** The coordenada valor 2. */
	private String coordenadaValor2;

	/** The descripcion 1. */
	private String descripcion1;

	/** The descripcion 2. */
	private String descripcion2;

	/** The is chaleng ok. */
	private boolean isChalengOk = false;

	/** The mensaje coordenadas. */
	private String mensajeCoordenadas;

	/**
	 * Instantiates a new tarjeta coordenada.
	 */
	public TarjetaCoordenada() {
		super();
	}

	/**
	 * Instantiates a new tarjeta coordenada.
	 *
	 * @param numero
	 *            the numero
	 * @param columnaY1
	 *            the columna Y 1
	 * @param filaX1
	 *            the fila X 1
	 * @param columnaY2
	 *            the columna Y 2
	 * @param filaX2
	 *            the fila X 2
	 */
	public TarjetaCoordenada(String numero, String columnaY1, String filaX1, String columnaY2, String filaX2) {
		this.numero = numero;
		this.filaX1 = filaX1;
		this.columnaY1 = columnaY1;

		this.descripcion1 = COLUMNAS[Integer.parseInt(columnaY1)] + (Integer.parseInt(filaX1) + 1);

		this.filaX2 = filaX2;
		this.columnaY2 = columnaY2;

		this.descripcion2 = COLUMNAS[Integer.parseInt(columnaY2)] + (Integer.parseInt(filaX2) + 1);
	}

	/**
	 * Instantiates a new tarjeta coordenada.
	 *
	 * @param coordenadasOperacion
	 *            the coordenadas operacion
	 */
	public TarjetaCoordenada(CoordenadasOperacionDTO coordenadasOperacion) {
		this.columnaY1 = coordenadasOperacion.getColumnaY1();
		this.columnaY2 = coordenadasOperacion.getColumnaY2();
		this.filaX1 = coordenadasOperacion.getFilaX1();
		this.filaX2 = coordenadasOperacion.getFilaX2();
		this.descripcion1 = coordenadasOperacion.getCoordenada1();
		this.descripcion2 = coordenadasOperacion.getCoordenada2();
		this.numero = coordenadasOperacion.getNumero();
		this.coordenadaValor1 = coordenadasOperacion.getIngresoCoordenada1();
		this.coordenadaValor2 = coordenadasOperacion.getIngresoCoordenada2();
	}

	/**
	 * Gets the numero.
	 *
	 * @return the numero
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * Gets the fila X 1.
	 *
	 * @return the fila X 1
	 */
	public String getFilaX1() {
		return filaX1;
	}

	/**
	 * Gets the columna Y 1.
	 *
	 * @return the columna Y 1
	 */
	public String getColumnaY1() {
		return columnaY1;
	}

	/**
	 * Gets the coordenada valor 1.
	 *
	 * @return the coordenada valor 1
	 */
	public String getCoordenadaValor1() {
		return coordenadaValor1;
	}

	/**
	 * Sets the coordenada valor 1.
	 *
	 * @param coordenadaValor1
	 *            the new coordenada valor 1
	 */
	public void setCoordenadaValor1(String coordenadaValor1) {
		this.coordenadaValor1 = coordenadaValor1;
	}

	/**
	 * Gets the fila X 2.
	 *
	 * @return the fila X 2
	 */
	public String getFilaX2() {
		return filaX2;
	}

	/**
	 * Gets the columna Y 2.
	 *
	 * @return the columna Y 2
	 */
	public String getColumnaY2() {
		return columnaY2;
	}

	/**
	 * Gets the coordenada valor 2.
	 *
	 * @return the coordenada valor 2
	 */
	public String getCoordenadaValor2() {
		return coordenadaValor2;
	}

	/**
	 * Sets the coordenada valor 2.
	 *
	 * @param coordenadaValor2
	 *            the new coordenada valor 2
	 */
	public void setCoordenadaValor2(String coordenadaValor2) {
		this.coordenadaValor2 = coordenadaValor2;
	}

	/**
	 * Gets the descripcion 1.
	 *
	 * @return the descripcion 1
	 */
	public String getDescripcion1() {
		return descripcion1;
	}

	/**
	 * Gets the descripcion 2.
	 *
	 * @return the descripcion 2
	 */
	public String getDescripcion2() {
		return descripcion2;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.base.entities.Entity#hashCode()
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(getDescripcion2());
		hcb.append(getDescripcion1());
		hcb.append(getCoordenadaValor2());
		hcb.append(getCoordenadaValor1());
		hcb.append(getColumnaY2());
		hcb.append(getFilaX2());
		hcb.append(getColumnaY1());
		hcb.append(getFilaX1());
		hcb.append(getNumero());
		return hcb.toHashCode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.base.entities.Entity#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		TarjetaCoordenada other = (TarjetaCoordenada) obj;
		return new EqualsBuilder().append(numero, other.numero).isEquals();
	}

	/**
	 * Sets the numero.
	 *
	 * @param numero
	 *            the new numero
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}

	/**
	 * Sets the fila X 1.
	 *
	 * @param filaX1
	 *            the new fila X 1
	 */
	public void setFilaX1(String filaX1) {
		this.filaX1 = filaX1;
	}

	/**
	 * Sets the columna Y 1.
	 *
	 * @param columnaY1
	 *            the new columna Y 1
	 */
	public void setColumnaY1(String columnaY1) {
		this.columnaY1 = columnaY1;
	}

	/**
	 * Sets the fila X 2.
	 *
	 * @param filaX2
	 *            the new fila X 2
	 */
	public void setFilaX2(String filaX2) {
		this.filaX2 = filaX2;
	}

	/**
	 * Sets the columna Y 2.
	 *
	 * @param columnaY2
	 *            the new columna Y 2
	 */
	public void setColumnaY2(String columnaY2) {
		this.columnaY2 = columnaY2;
	}

	/**
	 * Sets the descripcion 1.
	 *
	 * @param descripcion1
	 *            the new descripcion 1
	 */
	public void setDescripcion1(String descripcion1) {
		this.descripcion1 = descripcion1;
	}

	/**
	 * Sets the descripcion 2.
	 *
	 * @param descripcion2
	 *            the new descripcion 2
	 */
	public void setDescripcion2(String descripcion2) {
		this.descripcion2 = descripcion2;
	}

	/**
	 * Checks if is chaleng ok.
	 *
	 * @return the isChalengOk
	 */
	public boolean isChalengOk() {
		return isChalengOk;
	}

	/**
	 * Sets the chaleng ok.
	 *
	 * @param isChalengOk
	 *            the isChalengOk to set
	 */
	public void setChalengOk(boolean isChalengOk) {
		this.isChalengOk = isChalengOk;
	}

	/**
	 * Gets the mensaje coordenadas.
	 *
	 * @return the mensaje coordenadas
	 */
	public String getMensajeCoordenadas() {
		return mensajeCoordenadas;
	}

	/**
	 * Sets the mensaje coordenadas.
	 *
	 * @param mensajeCoordenadas
	 *            the new mensaje coordenadas
	 */
	public void setMensajeCoordenadas(String mensajeCoordenadas) {
		this.mensajeCoordenadas = mensajeCoordenadas;
	}

}
