/*
 * 
 */
package ar.com.santanderrio.obp.servicios.debinws.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The Class ConsultaDebinWSOutView.
 *
 * @author Silvina_Luque
 */
public class ConsultaDebinWSOutView extends CabeceraGrillaDebinView{
    
    /** listaDebin. */
    private List<DebinWSGrillaView> listaDebin = new ArrayList<DebinWSGrillaView>();
    
    /** si hay mas debines para consultar. */
    private boolean hayMasRegistros = false;
    
    /** estados*/
    private  Map<String,String> estadosBusqueda;
    
    /**
     * @return the estadosBusqueda
     */
    public Map<String, String> getEstadosBusqueda() {
        return estadosBusqueda;
    }

    /**
     * @param estadosBusqueda the estadosBusqueda to set
     */
    public void setEstadosBusqueda(Map<String, String> estadosBusqueda) {
        this.estadosBusqueda = estadosBusqueda;
    }

    /**
	 * Gets the lista debin.
	 *
	 * @return the lista debin
	 */
    public List<DebinWSGrillaView> getListaDebin() {
        return listaDebin;
    }

    /**
	 * Sets the lista debin.
	 *
	 * @param listaDebin
	 *            the new lista debin
	 */
    public void setListaDebin(List<DebinWSGrillaView> listaDebin) {
        this.listaDebin = listaDebin;
    }

    /**
	 * Checks if is hay mas registros.
	 *
	 * @return true, if is hay mas registros
	 */
    public boolean isHayMasRegistros() {
        return hayMasRegistros;
    }

    /**
	 * Sets the hay mas registros.
	 *
	 * @param hayMasRegistros
	 *            the new hay mas registros
	 */
    public void setHayMasRegistros(boolean hayMasRegistros) {
        this.hayMasRegistros = hayMasRegistros;
    }
    

}
