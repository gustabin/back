/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.web.view;

import java.util.List;

/**
 * The Class SolicitudTarjetaCreditoAdicionalView.
 */
public class SolicitudReimpresionTarjetasView {

	/** The tarjetas candidatas. */
	private List<TarjetaCandidataView> tarjetasCandidatas;

	/** The titular. */
	private String titular;
	
	/** Mensaje cantidad maxima tarjetas. */
    private String msjAlertaCantidadTarjetas;

    
    /** Mensaje alerta robo/hurto. */
    private String msjAlertaRoboHurto;
	

    
    
	/**
	 * Gets the msj alerta cantidad tarjetas.
	 *
	 * @return the msj alerta cantidad tarjetas
	 */
	public String getMsjAlertaCantidadTarjetas() {
        return msjAlertaCantidadTarjetas;
    }

    /**
	 * Sets the msj alerta cantidad tarjetas.
	 *
	 * @param msjAlertaCantidadTarjetas
	 *            the new msj alerta cantidad tarjetas
	 */
    public void setMsjAlertaCantidadTarjetas(String msjAlertaCantidadTarjetas) {
        this.msjAlertaCantidadTarjetas = msjAlertaCantidadTarjetas;
    }

    /**
	 * Gets the msj alerta robo hurto.
	 *
	 * @return the msj alerta robo hurto
	 */
    public String getMsjAlertaRoboHurto() {
        return msjAlertaRoboHurto;
    }

    /**
	 * Sets the msj alerta robo hurto.
	 *
	 * @param msjAlertaRoboHurto
	 *            the new msj alerta robo hurto
	 */
    public void setMsjAlertaRoboHurto(String msjAlertaRoboHurto) {
        this.msjAlertaRoboHurto = msjAlertaRoboHurto;
    }

    /**
	 * Gets the tarjetas candidatas.
	 *
	 * @return the tarjetasCandidatas
	 */
	public List<TarjetaCandidataView> getTarjetasCandidatas() {
		return tarjetasCandidatas;
	}

	/**
	 * Sets the tarjetas candidatas.
	 *
	 * @param tarjetasCandidatas
	 *            the tarjetasCandidatas to set
	 */
	public void setTarjetasCandidatas(List<TarjetaCandidataView> tarjetasCandidatas) {
		this.tarjetasCandidatas = tarjetasCandidatas;
	}

	/**
	 * Gets the titular.
	 *
	 * @return the titular
	 */
	public String getTitular() {
		return titular;
	}

	/**
	 * Sets the titular.
	 *
	 * @param titular
	 *            the new titular
	 */
	public void setTitular(String titular) {
		this.titular = titular;
	}

}
