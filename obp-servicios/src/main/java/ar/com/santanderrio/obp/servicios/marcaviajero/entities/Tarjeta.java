/*
 * 
 */
package ar.com.santanderrio.obp.servicios.marcaviajero.entities;

/**
 * The Class Tarjeta.
 */


public class Tarjeta {
    
    
    /**
	 * Instantiates a new tarjeta.
	 */
    public Tarjeta() {
        super();
        this.setPrioridad(1);
    }

    /** The nombre. */
    private String nombre;

    /** The is titular. */
    private Boolean isTitular;

    /** The marca. */
    private String marca;

    /** The numero. */
    private String numero;

    /** The alias. */
    private String alias;

    /** The ultimos cuatro. */
    private String ultimosCuatro;

    /** The prioridad. */
    private Integer prioridad;

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
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Gets the checks if is titular.
     *
     * @return the isTitular
     */
    public Boolean getIsTitular() {
        return isTitular;
    }

    /**
     * Sets the checks if is titular.
     *
     * @param isTitular the isTitular to set
     */
    public void setIsTitular(Boolean isTitular) {
        this.isTitular = isTitular;
    }

    /**
     * Gets the marca.
     *
     * @return the marca
     */
    public String getMarca() {
        return marca;
    }

    /**
     * Sets the marca.
     *
     * @param marca the marca to set
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }

    /**
     * Gets the numero.
     *
     * @return the numero
     */
    public String getNumero() {
        return numero;
    }

    /**
     * Sets the numero.
     *
     * @param numero the numero to set
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    /**
     * Gets the alias.
     *
     * @return the alias
     */
    public String getAlias() {
        return alias;
    }

    /**
     * Sets the alias.
     *
     * @param alias the alias to set
     */
    public void setAlias(String alias) {
        this.alias = alias;
    }

    /**
     * Gets the ultimos cuatro.
     *
     * @return the ultimosCuatro
     */
    public String getUltimosCuatro() {
        return ultimosCuatro;
    }

    /**
     * Sets the ultimos cuatro.
     *
     * @param ultimosCuatro the ultimosCuatro to set
     */
    public void setUltimosCuatro(String ultimosCuatro) {
        this.ultimosCuatro = ultimosCuatro;
    }

    
    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ultimosCuatro == null) ? 0 : ultimosCuatro.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tarjeta other = (Tarjeta) obj;
		if (ultimosCuatro == null) {
			if (other.ultimosCuatro != null)
				return false;
		} else if (!ultimosCuatro.equals(other.ultimosCuatro))
			return false;
		return true;
	}

	/* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Tarjeta [nombre=" + nombre + ", isTitular=" + isTitular
                + ", marca=" + marca + ", numero=" + numero + ", alias=" + alias + ", ultimosCuatro=" + ultimosCuatro
                + "]";
    }

    /**
	 * Gets the prioridad.
	 *
	 * @return the prioridad
	 */
    public Integer getPrioridad() {
        return prioridad;
    }

    /**
	 * Sets the prioridad.
	 *
	 * @param prioridad
	 *            the prioridad to set
	 */
    public void setPrioridad(Integer prioridad) {
        this.prioridad = prioridad;
    }
}
