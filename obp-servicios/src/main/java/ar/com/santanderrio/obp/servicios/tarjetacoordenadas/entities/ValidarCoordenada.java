/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetacoordenadas.entities;

import java.io.Serializable;
import java.math.BigInteger;
import java.nio.charset.Charset;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.DigestUtils;

/**
 * The Class ValidarCoordenada.
 */
@Deprecated // usar CoordenadaOperacionDTO y acceder por medio de
			// AutentificacionManager
public class ValidarCoordenada implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 6542577541640944606L;

	/** The Constant PRIME. */
	private static final int PRIME = 31;

	/** The Constant CUATRO. */
	private static final int CUATRO = 4;

	/** The Constant DIECISEIS. */
	private static final int DIECISEIS = 16;

	/** The pedido coordenada. */
	private PedidoCoordenada pedidoCoordenada;

	/** The tarjeta coordenada. */
	private TarjetaCoordenada tarjetaCoordenada;

	/** The coordenadas hash. */
	private String coordenadasHash;

	/**
	 * Instantiates a new validar coordenada.
	 *
	 * @param tarjetaCoordenada
	 *            the tarjeta coordenada
	 * @param pedidoCoordenada
	 *            the pedido coordenada
	 */
	public ValidarCoordenada(TarjetaCoordenada tarjetaCoordenada, PedidoCoordenada pedidoCoordenada) {
		this.tarjetaCoordenada = tarjetaCoordenada;
		this.pedidoCoordenada = pedidoCoordenada;
		this.coordenadasHash = calcularHash(tarjetaCoordenada);
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
	 * Gets the tarjeta coordenada.
	 *
	 * @return the tarjeta coordenada
	 */
	public TarjetaCoordenada getTarjetaCoordenada() {
		return tarjetaCoordenada;
	}

	/**
	 * Sets the tarjeta coordenada.
	 *
	 * @param tarjetaCoordenada
	 *            the new tarjeta coordenada
	 */
	public void setTarjetaCoordenada(TarjetaCoordenada tarjetaCoordenada) {
		this.tarjetaCoordenada = tarjetaCoordenada;
	}

	/**
	 * Gets the coordenadas hash.
	 *
	 * @return the coordenadas hash
	 */
	public String getCoordenadasHash() {
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
		result = PRIME * result + ((tarjetaCoordenada == null) ? 0 : tarjetaCoordenada.hashCode());
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
		ValidarCoordenada other = (ValidarCoordenada) obj;
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
		if (tarjetaCoordenada == null) {
			if (other.tarjetaCoordenada != null) {
				return false;
			}
		} else if (!tarjetaCoordenada.equals(other.tarjetaCoordenada)) {
			return false;
		}
		return true;
	}

	/**
	 * Calcular hash.
	 *
	 * @param tarjetaCoordenada
	 *            the tarjeta coordenada
	 * @return the string
	 */
	public String calcularHash(TarjetaCoordenada tarjetaCoordenada) {
		BigInteger coordenadaValor1 = coordenadaValor(tarjetaCoordenada.getDescripcion1(),
				String.valueOf(tarjetaCoordenada.getCoordenadaValor1()));

		BigInteger coordenadaValor2 = coordenadaValor(tarjetaCoordenada.getDescripcion2(),
				String.valueOf(tarjetaCoordenada.getCoordenadaValor2()));

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

}
