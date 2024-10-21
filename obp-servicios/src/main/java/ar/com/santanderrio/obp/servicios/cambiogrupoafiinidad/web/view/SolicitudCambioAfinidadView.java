/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cambiogrupoafiinidad.web.view;

import java.util.List;

import ar.com.santanderrio.obp.servicios.cambiogrupoafiinidad.dto.TarjetaAsociadaDTO;

/**
 * The Class SolicitudCambioAfinidadView.
 */
public class SolicitudCambioAfinidadView {
    
    /** The tarjetas asociadas. */
    private List<TarjetaAsociadaDTO> tarjetasAsociadas;

    /** The tyc. */
    private String tyc;

    /** The info cabecera. */
    private String infoCabecera;

    /** The info pie. */
    private String infoPie;

    /** The mensaje. */
    private String mensaje;

    /**
	 * Gets the tyc.
	 *
	 * @return the tyc
	 */
    public String getTyc() {
        return tyc;
    }

    /**
	 * Sets the tyc.
	 *
	 * @param tyc
	 *            the new tyc
	 */
    public void setTyc(String tyc) {
        this.tyc = tyc;
    }

    /**
	 * Gets the info cabecera.
	 *
	 * @return the info cabecera
	 */
    public String getInfoCabecera() {
        return infoCabecera;
    }

    /**
	 * Sets the info cabecera.
	 *
	 * @param infoCabecera
	 *            the new info cabecera
	 */
    public void setInfoCabecera(String infoCabecera) {
        this.infoCabecera = infoCabecera;
    }

    /**
	 * Gets the info pie.
	 *
	 * @return the info pie
	 */
    public String getInfoPie() {
        return infoPie;
    }

    /**
	 * Sets the info pie.
	 *
	 * @param infoPie
	 *            the new info pie
	 */
    public void setInfoPie(String infoPie) {
        this.infoPie = infoPie;
    }

    /**
	 * Gets the mensaje.
	 *
	 * @return the mensaje
	 */
    public String getMensaje() {
        return mensaje;
    }

    /**
	 * Sets the mensaje.
	 *
	 * @param mensaje
	 *            the new mensaje
	 */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    /**
	 * Gets the tarjetas asociadas.
	 *
	 * @return the tarjetas asociadas
	 */
    public List<TarjetaAsociadaDTO> getTarjetasAsociadas() {
        return tarjetasAsociadas;
    }

    /**
	 * Sets the tarjetas asociadas.
	 *
	 * @param tarjetasAsociadas
	 *            the new tarjetas asociadas
	 */
    public void setTarjetasAsociadas(List<TarjetaAsociadaDTO> tarjetasAsociadas) {
        this.tarjetasAsociadas = tarjetasAsociadas;
    }
}
