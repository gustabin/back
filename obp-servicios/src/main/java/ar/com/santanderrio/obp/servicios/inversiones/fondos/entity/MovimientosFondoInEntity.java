/**
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.time.DateUtils;

/**
 * Objeto utilizado para ingresar al DAO.
 * 
 * @author
 *
 */
public class MovimientosFondoInEntity {

	/** The Constant CODIGO_AGENTE_CONSTANTE. */
	private static final String CODIGO_AGENTE_CONSTANTE = "001";

	/** The Constant CODIGO_CANAL_CONSTANTE. */
	private static final String CODIGO_CANAL_CONSTANTE = "991";

	/** The Constant OCHO_ESPACIOS_EN_BLANCO. */
	private static final String OCHO_ESPACIOS_EN_BLANCO = "        ";

	/** The codigo fondo. */
	// N3
	private String codigoFondo;

	/** The codigo cliente. */
	// CODIGO AGENTE (N3) + NRO CUENTA TITULO (N8)
	private String codigoCliente;

	/** The codigo agente. */
	// CONSTANTE 001
	private String codigoAgente = CODIGO_AGENTE_CONSTANTE;

	/** The codigo canal. */
	// CONSTANTE 991
	private String codigoCanal = CODIGO_CANAL_CONSTANTE;

	/** The codigo operador. */
	private String codigoOperador = OCHO_ESPACIOS_EN_BLANCO;

	/** The fecha desde. */
	// FORMATO DDMMAAAA
	private String fechaDesde;

	/** The fecha hasta. */
	// FORMATO DDMMAAAA
	private String fechaHasta;

	/**
	 * Instantiates a new movimientos fondo in entity.
	 *
	 * @param maxDias
	 *            the max dias
	 */
	public MovimientosFondoInEntity(int maxDias) {
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
		Date ayer = DateUtils.addDays(new Date(), -1);
		Date desde = DateUtils.addDays(new Date(), -maxDias);
		this.fechaDesde = sdf.format(desde);
		this.fechaHasta = sdf.format(ayer);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				// agregar los append con los atributos que correspondan
				// .append(extra)
				.toHashCode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (obj.getClass() != getClass()) {
			return false;
		}

		MovimientosFondoInEntity other = (MovimientosFondoInEntity) obj;
		EqualsBuilder eb = new EqualsBuilder();
		return eb
				// agregar los appends que corresponda segun los atributos
				// cargados, Ej: .append(extra, other.getExtra())
				.isEquals();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this)
				// agregar los appends con los atributos que corresponda, ej:
				// .append("Extra", extra)
				.toString();
	}

	/**
	 * Gets the codigo fondo.
	 *
	 * @return the codigo fondo
	 */
	public String getCodigoFondo() {
		return codigoFondo;
	}

	/**
	 * Sets the codigo fondo.
	 *
	 * @param codigoFondo
	 *            the new codigo fondo
	 */
	public void setCodigoFondo(String codigoFondo) {
		this.codigoFondo = codigoFondo;
	}

	/**
	 * Gets the codigo cliente.
	 *
	 * @return the codigo cliente
	 */
	public String getCodigoCliente() {
		return codigoCliente;
	}

	/**
	 * Sets the codigo cliente.
	 *
	 * @param codigoCliente
	 *            the new codigo cliente
	 */
	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	/**
	 * Gets the codigo agente.
	 *
	 * @return the codigo agente
	 */
	public String getCodigoAgente() {
		return codigoAgente;
	}

	/**
	 * Sets the codigo agente.
	 *
	 * @param codigoAgente
	 *            the new codigo agente
	 */
	public void setCodigoAgente(String codigoAgente) {
		this.codigoAgente = codigoAgente;
	}

	/**
	 * Gets the codigo canal.
	 *
	 * @return the codigo canal
	 */
	public String getCodigoCanal() {
		return codigoCanal;
	}

	/**
	 * Sets the codigo canal.
	 *
	 * @param codigoCanal
	 *            the new codigo canal
	 */
	public void setCodigoCanal(String codigoCanal) {
		this.codigoCanal = codigoCanal;
	}

	/**
	 * Gets the codigo operador.
	 *
	 * @return the codigo operador
	 */
	public String getCodigoOperador() {
		return codigoOperador;
	}

	/**
	 * Sets the codigo operador.
	 *
	 * @param codigoOperador
	 *            the new codigo operador
	 */
	public void setCodigoOperador(String codigoOperador) {
		this.codigoOperador = codigoOperador;
	}

	/**
	 * Gets the fecha desde.
	 *
	 * @return the fecha desde
	 */
	public String getFechaDesde() {
		return fechaDesde;
	}

	/**
	 * Sets the fecha desde.
	 *
	 * @param fechaDesde
	 *            the new fecha desde
	 */
	public void setFechaDesde(String fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	/**
	 * Gets the fecha hasta.
	 *
	 * @return the fecha hasta
	 */
	public String getFechaHasta() {
		return fechaHasta;
	}

	/**
	 * Sets the fecha hasta.
	 *
	 * @param fechaHasta
	 *            the new fecha hasta
	 */
	public void setFechaHasta(String fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

}
