/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.autentificacion.entities;

import java.math.BigInteger;
import java.nio.charset.Charset;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.util.DigestUtils;

import ar.com.santanderrio.obp.servicios.comun.challenge.entities.TipoDesafioEnum;
import ar.com.santanderrio.obp.servicios.tarjetacoordenadas.entities.PedidoCoordenada;

/**
 * Esta clase representa el objeto DTO de Coordenadas para la autentificacion.
 *
 * @author ignacio.valek
 * @since Sep 26, 2016.
 * @lastUpdate emilio.watemberg - Nov 11, 2016: Se integraron todos los DTO que
 *             andan dando vueltas sobre coordenadas en este.
 */
public class CoordenadasOperacionDTO extends OperacionDTO {

	/** The Constant COLUMNAS. */
	static final String[] COLUMNAS = { "A", "B", "C", "D", "E", "F", "G", "H", "I" };

	/** The numero. */
	private String numero;

	/** The fila X 1. */
	private String filaX1;

	/** The columna Y 1. */
	private String columnaY1;

	/** The coordenada 1. */
	private String coordenada1;

	/** The fila X 2. */
	private String filaX2;

	/** The columna Y 2. */
	private String columnaY2;

	/** The coordenada 2. */
	private String coordenada2;

	/** The ingreso coordenada 1. */
	private String ingresoCoordenada1;

	/** The ingreso coordenada 2. */
	private String ingresoCoordenada2;

	/** The mensaje coordenadas. */
	private String mensajeCoordenadas;

	/** The descripcion 1. */
	private String descripcion1;

	/** The descripcion 2. */
	private String descripcion2;

	// inicio propiedades obtenidas de validarCoordenadas
	/** The Constant PRIME. */
	private static final int PRIME = 31;

	/** The Constant CUATRO. */
	private static final int CUATRO = 4;

	/** The Constant DIECISEIS. */
	private static final int DIECISEIS = 16;

	/** The pedido coordenada. */
	private PedidoCoordenada pedidoCoordenada;

	/** The coordenadas hash. */
	private String coordenadasHash;
	// fin propiedades obtenidas de validarCoordenadas

	/** The coordenadas validas. */
	private Boolean coordenadasValidas = new Boolean(false);

	/**
	 * Instantiates a new coordenadas operacion DTO.
	 */
	public CoordenadasOperacionDTO() {
		super(TipoDesafioEnum.COORDENADAS);
	}

	/**
	 * Instantiates a CoordenadasOperacionDTO.
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
	public CoordenadasOperacionDTO(String numero, String columnaY1, String filaX1, String columnaY2, String filaX2) {
		super(TipoDesafioEnum.COORDENADAS);
		this.numero = numero;
		this.filaX1 = filaX1;
		this.columnaY1 = columnaY1;
		this.coordenada1 = COLUMNAS[Integer.parseInt(columnaY1)] + (Integer.parseInt(filaX1) + 1);
		this.filaX2 = filaX2;
		this.columnaY2 = columnaY2;
		this.coordenada2 = COLUMNAS[Integer.parseInt(columnaY2)] + (Integer.parseInt(filaX2) + 1);
	}

	/**
	 * Instantiates a new coordenadas operacion DTO.
	 *
	 * @param pedidoCoordenada
	 *            the pedido coordenada
	 */
	public CoordenadasOperacionDTO(PedidoCoordenada pedidoCoordenada) {
		this();
		this.pedidoCoordenada = pedidoCoordenada;
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
	 * Sets the numero.
	 *
	 * @param numero
	 *            the new numero
	 */
	public void setNumero(String numero) {
		this.numero = numero;
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
	 * Sets the fila X 1.
	 *
	 * @param filaX1
	 *            the new fila X 1
	 */
	public void setFilaX1(String filaX1) {
		this.filaX1 = filaX1;
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
	 * Sets the columna Y 1.
	 *
	 * @param columnaY1
	 *            the new columna Y 1
	 */
	public void setColumnaY1(String columnaY1) {
		this.columnaY1 = columnaY1;
	}

	/**
	 * Gets the coordenada 1.
	 *
	 * @return the coordenada 1
	 */
	public String getCoordenada1() {
		return coordenada1;
	}

	/**
	 * Sets the coordenada 1.
	 *
	 * @param coordenada1
	 *            the new coordenada 1
	 */
	public void setCoordenada1(String coordenada1) {
		this.coordenada1 = coordenada1;
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
	 * Sets the fila X 2.
	 *
	 * @param filaX2
	 *            the new fila X 2
	 */
	public void setFilaX2(String filaX2) {
		this.filaX2 = filaX2;
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
	 * Sets the columna Y 2.
	 *
	 * @param columnaY2
	 *            the new columna Y 2
	 */
	public void setColumnaY2(String columnaY2) {
		this.columnaY2 = columnaY2;
	}

	/**
	 * Gets the coordenada 2.
	 *
	 * @return the coordenada 2
	 */
	public String getCoordenada2() {
		return coordenada2;
	}

	/**
	 * Sets the coordenada 2.
	 *
	 * @param coordenada2
	 *            the new coordenada 2
	 */
	public void setCoordenada2(String coordenada2) {
		this.coordenada2 = coordenada2;
	}

	/**
	 * Gets the ingreso coordenada 1.
	 *
	 * @return the ingreso coordenada 1
	 */
	public String getIngresoCoordenada1() {
		return ingresoCoordenada1;
	}

	/**
	 * Sets the ingreso coordenada 1.
	 *
	 * @param ingresoCoordenada1
	 *            the new ingreso coordenada 1
	 */
	public void setIngresoCoordenada1(String ingresoCoordenada1) {
		this.ingresoCoordenada1 = ingresoCoordenada1;
	}

	/**
	 * Gets the ingreso coordenada 2.
	 *
	 * @return the ingreso coordenada 2
	 */
	public String getIngresoCoordenada2() {
		return ingresoCoordenada2;
	}

	/**
	 * Sets the ingreso coordenada 2.
	 *
	 * @param ingresoCoordenada2
	 *            the new ingreso coordenada 2
	 */
	public void setIngresoCoordenada2(String ingresoCoordenada2) {
		this.ingresoCoordenada2 = ingresoCoordenada2;
	}

	/**
	 * Gets the pedido coordenada.
	 *
	 * @return the pedido coordenada
	 */
	public PedidoCoordenada getPedidoCoordenada() {
		return pedidoCoordenada;
	}

	/**
	 * Sets the pedido coordenada.
	 *
	 * @param pedidoCoordenada
	 *            the new pedido coordenada
	 */
	public void setPedidoCoordenada(PedidoCoordenada pedidoCoordenada) {
		this.pedidoCoordenada = pedidoCoordenada;
	}

	/**
	 * Gets the coordenadas hash.
	 *
	 * @return the coordenadas hash
	 */
	public String getCoordenadasHash() {
		coordenadasHash = this.calcularHash();
		return coordenadasHash;
	}

	/**
	 * Sets the coordenadas hash.
	 *
	 * @param coordenadasHash
	 *            the new coordenadas hash
	 */
	public void setCoordenadasHash(String coordenadasHash) {
		this.coordenadasHash = coordenadasHash;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {

		int result = super.hashCode();
		result = PRIME * result + ((coordenadasHash == null) ? 0 : coordenadasHash.hashCode());
		result = PRIME * result + ((pedidoCoordenada == null) ? 0 : pedidoCoordenada.hashCode());
		result = PRIME * result + (this.hashCodeTarjetaCoordenadaProperties());
		return result;
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
		if (getClass() != obj.getClass()) {
			return false;
		}
		CoordenadasOperacionDTO other = (CoordenadasOperacionDTO) obj;
		if (coordenadasHash == null) {
			if (other.coordenadasHash != null) {
				return false;
			}
		} else if (!coordenadasHash.equals(other.coordenadasHash)) {
			return false;
		}
		if (pedidoCoordenada == null) {
			if (other.pedidoCoordenada != null) {
				return false;
			}
		} else if (!pedidoCoordenada.equals(other.pedidoCoordenada)) {
			return false;
		}
		return true;
	}

	/**
	 * Calcular hash.
	 *
	 * @return the string
	 */
	public String calcularHash() {
		BigInteger coordenadaValor1 = coordenadaValor(this.coordenada1, String.valueOf(ingresoCoordenada1));

		BigInteger coordenadaValor2 = coordenadaValor(this.coordenada2, String.valueOf(ingresoCoordenada2));

		String hash = StringUtils.upperCase(coordenadaValor1.xor(coordenadaValor2).toString(DIECISEIS));

		return StringUtils.leftPad(hash, 32, "0");
	}

	/**
	 * Coordenada valor.
	 *
	 * @param descripcion
	 *            the descripcion
	 * @param coordenadaValor
	 *            the coordenada valor
	 * @return the big integer
	 */
	private BigInteger coordenadaValor(String descripcion, String coordenadaValor) {
		String formatoTransmision = descripcion + StringUtils.leftPad(coordenadaValor, CUATRO, "0");
		String formatoTransmisionMD5Hex = DigestUtils
				.md5DigestAsHex(formatoTransmision.getBytes(Charset.forName("UTF-8")));

		return new BigInteger(formatoTransmisionMD5Hex, DIECISEIS);
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

	/**
	 * Gets the coordenadas validas.
	 *
	 * @return the coordenadas validas
	 */
	public Boolean getCoordenadasValidas() {
		return coordenadasValidas;
	}

	/**
	 * Sets the coordenadas validas.
	 *
	 * @param coordenadasValidas
	 *            the new coordenadas validas
	 */
	public void setCoordenadasValidas(Boolean coordenadasValidas) {
		this.coordenadasValidas = coordenadasValidas;
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
	 * Sets the descripcion 1.
	 *
	 * @param descripcion1
	 *            the new descripcion 1
	 */
	public void setDescripcion1(String descripcion1) {
		this.descripcion1 = descripcion1;
	}

	/**
	 * Gets the descripcion 2.
	 *
	 * @return the descripcion 2
	 */
	public String getDescripcion2() {
		return descripcion2;
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
	 * Reemplaza al hashcode del objeto TarjetaCoordenada deprecado.
	 *
	 * @return the int
	 */
	public int hashCodeTarjetaCoordenadaProperties() {
		if (numero == null && filaX1 == null && filaX2 == null && columnaY1 == null && columnaY2 == null
				&& coordenada1 == null && coordenada2 == null && descripcion1 == null && descripcion2 == null) {
			return 0;
		} else {
			HashCodeBuilder hcb = new HashCodeBuilder();
			hcb.append(getDescripcion2());
			hcb.append(getDescripcion1());
			hcb.append(getCoordenada1());
			hcb.append(getCoordenada2());
			hcb.append(getColumnaY2());
			hcb.append(getFilaX2());
			hcb.append(getColumnaY1());
			hcb.append(getFilaX1());
			hcb.append(getNumero());
			return hcb.toHashCode();
		}

	}
}