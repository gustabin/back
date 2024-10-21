/**
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.manager;

import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author sergio.e.goldentair
 *
 */
public class RescateFondoEspecie {

    private Map<String, FondoEspecie> fondosMap = null;

    /**
     * @return the fondosMap
     */
    public Map<String, FondoEspecie> getFondosMap() {
        return fondosMap;
    }

    /**
     * @param fondosMap
     *            the fondosMap to set
     */
    public void setFondosMap(Map<String, FondoEspecie> fondosMap) {
        this.fondosMap = fondosMap;
    }

    /**
     * To string.
     *
     * @return the string
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this).append("fondosMap", fondosMap).toString();
    }

}
