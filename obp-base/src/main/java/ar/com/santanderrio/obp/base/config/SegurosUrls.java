package ar.com.santanderrio.obp.base.config;

import java.util.Collections;
import java.util.List;
import java.util.Map;

// TODO: Auto-generated Javadoc
/**
 * Esta clase lee el archivo .yaml que describe los ruteos para las ofertas de
 * seguros cuando se saca un prestamo
 * 
 * @author marcelo.ruiz
 *
 */
public class SegurosUrls {
    /**
     * el ruteo por default cuando no se encuentra una oferta comercial para el
     * destino del credito.
     */
    public static final String OFERTA_DEFAULT = "ip";
    
    /** si no se carga en el archivo una oferta por defecto de usa OFERTA_DEFAULT. */
    private String ofertaDefault = OFERTA_DEFAULT;
    
    /** lista de destinos de creditos y urls de ruteo de ofertas. */
    private Map<String, List<String>> data = null;

    /**
     * Gets the data.
     *
     * @return the data
     */
    public Map<String, List<String>> getData() {
        return data;
    }

    /**
     * Sets the data.
     *
     * @param data the data
     */
    public void setData(Map<String, List<String>> data) {
        this.data = data;
    }

    /**
     * Retorna la lista de urls para el id de destino seleccionado *.
     *
     * @param id the id
     * @return the urls
     */
    public List<String> getUrls(String id) {
        if (data != null) {
            for (Map.Entry<String, List<String>> entry : data.entrySet()) {
                if (entry.getKey().equals(id)) {
                    return entry.getValue();
                }
            }
        }
        return Collections.emptyList();
    }

    /**
     * Gets the oferta default.
     *
     * @return the oferta default
     */
    public String getOfertaDefault() {
        return ofertaDefault;
    }

    /**
     * Sets the oferta default.
     *
     * @param ofertaDefault the new oferta default
     */
    public void setOfertaDefault(String ofertaDefault) {
        this.ofertaDefault = ofertaDefault;
    }

}
