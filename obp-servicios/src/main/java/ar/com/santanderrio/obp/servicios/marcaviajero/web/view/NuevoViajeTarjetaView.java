/*
 * 
 */
package ar.com.santanderrio.obp.servicios.marcaviajero.web.view;

import java.util.List;

/**
 * The Class TarjetaView.
 */
public class NuevoViajeTarjetaView {

	/** The nombre. */
	private String nombre;

	/** The checked. */
	private String checked;

	/** The tarjetas asociadas. */ 
	private List<TarjetaAsociadaView> tarjeta;

	/** The nombre Ws. */
	private String nombreWs;

	/**
	 * Gets the nombre.
	 *
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Sets the nombre.
	 *
	 * @param nombre
	 *            the new nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	/**
	 * Gets the tarjeta.
	 *
	 * @return the tarjeta
	 */
	public List<TarjetaAsociadaView> getTarjeta() {
		return tarjeta;
	}

	/**
	 * Sets the tarjeta.
	 *
	 * @param tarjeta
	 *            the new tarjeta
	 */
	public void setTarjeta(List<TarjetaAsociadaView> tarjeta) {
		this.tarjeta = tarjeta;
	}

    /**
	 * Gets the checked.
	 *
	 * @return the checked
	 */
    public String getChecked() {
        return checked;
    }

    /**
	 * Sets the checked.
	 *
	 * @param checked
	 *            the new checked
	 */
    public void setChecked(String checked) {
        this.checked = checked;
    }

    /**
	 * Gets the nombre ws.
	 *
	 * @return the nombreWs
	 */
    public String getNombreWs() {
        return nombreWs;
    }

    /**
	 * Sets the nombre ws.
	 *
	 * @param nombreWs
	 *            the nombreWs to set
	 */
    public void setNombreWs(String nombreWs) {
        this.nombreWs = nombreWs;
    }

}
