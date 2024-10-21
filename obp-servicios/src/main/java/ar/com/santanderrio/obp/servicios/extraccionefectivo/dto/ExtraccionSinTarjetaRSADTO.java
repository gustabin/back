package ar.com.santanderrio.obp.servicios.extraccionefectivo.dto;

import java.io.Serializable;

import ar.com.santanderrio.obp.base.comun.OperacionesRSAEnum;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.RsaDTO;
import ar.com.santanderrio.obp.servicios.comun.challenge.entities.TipoDesafioEnum;

public class ExtraccionSinTarjetaRSADTO extends RsaDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	/** The operacion. */
    private int operacion;

    /** Se guarda en sesion si hay un desafio en curso. */
    private boolean existeDesafioEnCurso = false;

    /** The metodo desafio. */
    private AutentificacionDTO desafio;

    /** The tipo desafio. */
    private TipoDesafioEnum tipoDesafio;

	public ExtraccionSinTarjetaRSADTO() {
		super(OperacionesRSAEnum.EXTRACCION_SIN_TARJETA);
	}
    
	public ExtraccionSinTarjetaRSADTO(int operacion) {
		this();
		this.operacion = operacion;
	}

	
	public int getOperacion() {
		return operacion;
	}

	public void setOperacion(int operacion) {
		this.operacion = operacion;
	}

	public boolean isExisteDesafioEnCurso() {
		return existeDesafioEnCurso;
	}

	public void setExisteDesafioEnCurso(boolean existeDesafioEnCurso) {
		this.existeDesafioEnCurso = existeDesafioEnCurso;
	}

	public AutentificacionDTO getDesafio() {
		return desafio;
	}

	public void setDesafio(AutentificacionDTO desafio) {
		this.desafio = desafio;
	}

	public TipoDesafioEnum getTipoDesafio() {
		return tipoDesafio;
	}

	public void setTipoDesafio(TipoDesafioEnum tipoDesafio) {
		this.tipoDesafio = tipoDesafio;
	}
	
}
