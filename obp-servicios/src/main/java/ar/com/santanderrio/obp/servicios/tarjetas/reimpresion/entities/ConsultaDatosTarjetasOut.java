/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.entities;

import java.util.List;

/**
 * The Class ConsultaDatosTarjetasOut.
 */
public class ConsultaDatosTarjetasOut {

	/** The tarjetas. */
	private List<TarjetaDatos> tarjetas;
	
	/** Marca de que hay mas datos. */
	private String hayMasDatos;
	
	/** claveSegundoLlamado. */
	private String claveSegundoLlamado;
	

	
		
	/**
	 * Gets the clave segundo llamado.
	 *
	 * @return the clave segundo llamado
	 */
	public String getClaveSegundoLlamado() {
        return claveSegundoLlamado;
    }



    /**
	 * Sets the clave segundo llamado.
	 *
	 * @param claveSegundoLlamado
	 *            the new clave segundo llamado
	 */
    public void setClaveSegundoLlamado(String claveSegundoLlamado) {
        this.claveSegundoLlamado = claveSegundoLlamado;
    }



    /**
	 * hayMasDatos.
	 *
	 * @return the hay mas datos
	 */
	public String getHayMasDatos() {
        return hayMasDatos;
    }
	
	

	/**
	 * setHayMasDatos.
	 *
	 * @param hayMasDatos
	 *            the new hay mas datos
	 */
    public void setHayMasDatos(String hayMasDatos) {
        this.hayMasDatos = hayMasDatos;
    }

    /**
	 * Gets the tarjetas.
	 *
	 * @return the tarjetas
	 */
	public List<TarjetaDatos> getTarjetas() {
		return tarjetas;
	}

	/**
	 * Sets the tarjetas.
	 *
	 * @param tarjetas
	 *            the new tarjetas
	 */
	public void setTarjetas(List<TarjetaDatos> tarjetas) {
		this.tarjetas = tarjetas;
	}

}