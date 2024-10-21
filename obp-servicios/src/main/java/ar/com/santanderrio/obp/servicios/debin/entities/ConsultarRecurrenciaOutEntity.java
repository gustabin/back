package ar.com.santanderrio.obp.servicios.debin.entities;

/**
 * The Class ConsultarRecurrenciaOutEntity.
 */
public class ConsultarRecurrenciaOutEntity {
    
    /** nup. */
    private String nup;
    
    /** habilitadoRecurrencia. */
    
    private String habilitadoRecurrencia;
    
    /** categoriaLimitePrisma. */
    private String categoriaLimitePrisma;

    /**
	 * Gets the nup.
	 *
	 * @return the nup
	 */
    public String getNup() {
        return nup;
    }

    /**
	 * Sets the nup.
	 *
	 * @param nup
	 *            the nup to set
	 */
    public void setNup(String nup) {
        this.nup = nup;
    }

    /**
	 * Gets the habilitado recurrencia.
	 *
	 * @return the habilitadoRecurrencia
	 */
    public String getHabilitadoRecurrencia() {
        return habilitadoRecurrencia;
    }

    /**
	 * Sets the habilitado recurrencia.
	 *
	 * @param habilitadoRecurrencia
	 *            the habilitadoRecurrencia to set
	 */
    public void setHabilitadoRecurrencia(String habilitadoRecurrencia) {
        this.habilitadoRecurrencia = habilitadoRecurrencia;
    }

    /**
	 * Gets the categoria limite prisma.
	 *
	 * @return the categoriaLimitePrisma
	 */
    public String getCategoriaLimitePrisma() {
        return categoriaLimitePrisma;
    }

    /**
	 * Sets the categoria limite prisma.
	 *
	 * @param categoriaLimitePrisma
	 *            the categoriaLimitePrisma to set
	 */
    public void setCategoriaLimitePrisma(String categoriaLimitePrisma) {
        this.categoriaLimitePrisma = categoriaLimitePrisma;
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ConsultarRecurrenciaOutEntity [" + (nup != null ? "nup=" + nup + ", " : "")
				+ (habilitadoRecurrencia != null ? "habilitadoRecurrencia=" + habilitadoRecurrencia + ", " : "")
				+ (categoriaLimitePrisma != null ? "categoriaLimitePrisma=" + categoriaLimitePrisma : "") + "]";
	}
    
    

}
