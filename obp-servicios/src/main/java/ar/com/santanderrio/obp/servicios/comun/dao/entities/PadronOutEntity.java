package ar.com.santanderrio.obp.servicios.comun.dao.entities;

public class PadronOutEntity {

    /** Nro de CUIT *. */
    protected String cuit;
    
    /**
     * Gets the aba nro cuit.
     *
     * @return the nro cuit
     */
    public String getCuit() {
        return cuit;
    }

    /**
     * Sets the nro cuit.
     *
     * @param NroCuit
     *            the nro cuit
     */
    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    public PadronOutEntity() {
        super();
    }

}