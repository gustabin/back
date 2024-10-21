/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.tarjetas.entity;

import java.util.List;

/**
 * The Class ConsultaDatosTarjetasOut.
 */
public class ConsultaDatosTarjetasOut {

    /** The tarjetas. */
    private List<TarjetaDatos> tarjetas;
    
    
    /** hayReEnganche. */
    private String hayReEnganche;
    
    /** marcaRellamada. */
    private String marcaReLlamada;


    /**
	 * Gets the hay re enganche.
	 *
	 * @return the hay re enganche
	 */
    public String getHayReEnganche() {
        return hayReEnganche;
    }

    /**
	 * Sets the hay re enganche.
	 *
	 * @param hayReEnganche
	 *            the new hay re enganche
	 */
    public void setHayReEnganche(String hayReEnganche) {
        this.hayReEnganche = hayReEnganche;
    }

    /**
	 * Gets the marca re llamada.
	 *
	 * @return the marca re llamada
	 */
    public String getMarcaReLlamada() {
        return marcaReLlamada;
    }

    /**
	 * Sets the marca re llamada.
	 *
	 * @param marcaReLlamada
	 *            the new marca re llamada
	 */
    public void setMarcaReLlamada(String marcaReLlamada) {
        this.marcaReLlamada = marcaReLlamada;
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

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ConsultaDatosTarjetasOut [tarjetas=" + tarjetas + ", hayReEnganche=" + hayReEnganche
                + ", marcaReLlamada=" + marcaReLlamada + "]";
    }
    
}