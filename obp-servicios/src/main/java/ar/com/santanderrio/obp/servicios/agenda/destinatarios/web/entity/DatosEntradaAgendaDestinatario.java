/*
 * 
 */
package ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Clase con datos de entrada al SEI de agenda destinatarios.
 */
public class DatosEntradaAgendaDestinatario {

	/** tipoDestinatario (RIO, OTROS_BANCOS, ENVIO_EFECTIVO). */
	private String tipoDestinatario;

	/** id para eliminacion de destinatario. */
	private String id;

	/** The filtro destinatario. */
	private boolean filtroDestinatario;
	
	/** The ignorar estadisticas. */
	private boolean ignorarEstadisticas;

	/**
	 * Instantiates a new datos entrada agenda destinatario.
	 */
	public DatosEntradaAgendaDestinatario() {
		super();
	}

	/**
	 * Instantiates a new datos entrada agenda destinatario.
	 *
	 * @param filtroDestinatario
	 *            the filtro destinatario
	 */
	public DatosEntradaAgendaDestinatario(boolean filtroDestinatario) {
		this.filtroDestinatario = filtroDestinatario;
	}

	/**
	 * Gets the tipo destinatario.
	 *
	 * @return the tipoDestinatario
	 */
	public String getTipoDestinatario() {
		return tipoDestinatario;
	}

	/**
	 * Sets the tipo destinatario.
	 *
	 * @param tipoDestinatario
	 *            the tipoDestinatario to set
	 */
	public void setTipoDestinatario(String tipoDestinatario) {
		this.tipoDestinatario = tipoDestinatario;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Gets the filtro destinatario.
	 *
	 * @return the filtro destinatario
	 */
	public boolean getFiltroDestinatario() {
		return filtroDestinatario;
	}

	/**
	 * Sets the filtro destinatario.
	 *
	 * @param filtroDestinatario
	 *            the new filtro destinatario
	 */
	public void setFiltroDestinatario(boolean filtroDestinatario) {
		this.filtroDestinatario = filtroDestinatario;
	}

	/**
	 * Gets the ignorar estadisticas.
	 *
	 * @return the ignorar estadisticas
	 */
	public boolean getIgnorarEstadisticas() {
        return ignorarEstadisticas;
    }

    /**
	 * Sets the ignorar estadisticas.
	 *
	 * @param ignorarEstadisticas
	 *            the new ignorar estadisticas
	 */
    public void setIgnorarEstadisticas(boolean ignorarEstadisticas) {
        this.ignorarEstadisticas = ignorarEstadisticas;
    }
    
    /**
	 * Gets the graba estadisticas.
	 *
	 * @return the graba estadisticas
	 */
    public boolean getGrabaEstadisticas() {
        return !ignorarEstadisticas;
    }

    /*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(tipoDestinatario);
		hcb.append(id);
		return hcb.toHashCode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		DatosEntradaAgendaDestinatario other = (DatosEntradaAgendaDestinatario) obj;

		EqualsBuilder eb = new EqualsBuilder();
		eb.append(tipoDestinatario, other.getTipoDestinatario());
		eb.append(id, other.getId());
		return eb.isEquals();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DatosEntradaAgendaDestinatario [tipoDestinatario=" + tipoDestinatario + ", id=" + id + "]";
	}

}
