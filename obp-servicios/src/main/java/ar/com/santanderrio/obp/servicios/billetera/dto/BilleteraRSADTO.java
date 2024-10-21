/**
 *
 */
package ar.com.santanderrio.obp.servicios.billetera.dto;

import ar.com.santanderrio.obp.base.comun.OperacionesRSAEnum;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.RsaDTO;
import ar.com.santanderrio.obp.servicios.comun.challenge.entities.TipoDesafioEnum;

/**
 * The Class BilleteraRSADTO.
 *
 */
public class BilleteraRSADTO extends RsaDTO {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The metodo desafio. */
	private AutentificacionDTO desafio;

	/** Se guarda en sesion si hay un desafio en curso. */
	private boolean existeDesafioEnCurso = false;

	/** The tipo desafio. */
	private TipoDesafioEnum tipoDesafio;

	/**
	 * Instantiates a new billetera RSADTO.
	 */
	public BilleteraRSADTO() {
		super(OperacionesRSAEnum.BILLETERA);
	}

	/**
	 * Gets the serialversionuid.
	 *
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * Gets the desafio.
	 *
	 * @return the desafio
	 */
	public AutentificacionDTO getDesafio() {
		return desafio;
	}

	/**
	 * Gets the tipo desafio.
	 *
	 * @return the tipoDesafio
	 */
	public TipoDesafioEnum getTipoDesafio() {
		return tipoDesafio;
	}

	/**
	 * Checks if is existe desafio en curso.
	 *
	 * @return the existeDesafioEnCurso
	 */
	public boolean isExisteDesafioEnCurso() {
		return existeDesafioEnCurso;
	}

	/**
	 * Sets the desafio.
	 *
	 * @param desafio
	 *            the desafio to set
	 */
	public void setDesafio(AutentificacionDTO desafio) {
		this.desafio = desafio;
	}

	/**
	 * Sets the existe desafio en curso.
	 *
	 * @param existeDesafioEnCurso
	 *            the existeDesafioEnCurso to set
	 */
	public void setExisteDesafioEnCurso(boolean existeDesafioEnCurso) {
		this.existeDesafioEnCurso = existeDesafioEnCurso;
	}

	/**
	 * Sets the tipo desafio.
	 *
	 * @param tipoDesafio
	 *            the tipoDesafio to set
	 */
	public void setTipoDesafio(TipoDesafioEnum tipoDesafio) {
		this.tipoDesafio = tipoDesafio;
	}

}
