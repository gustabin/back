package ar.com.santanderrio.obp.servicios.inversiones.fondos.manager;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author sergio.e.goldentair
 *
 */
public class FondoEspecie {
    private String operacion;
    private String horarioInicio;
    private String horarioFin;
    private String importeMinimo;
    private String importeMaximo;

    /**
     * @return the operacion
     */
    public String getOperacion() {
        return operacion;
    }

    /**
     * @param operacion
     *            the operacion to set
     */
    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }

    /**
     * @return the horarioInicio
     */
    public String getHorarioInicio() {
        return horarioInicio;
    }

    /**
     * @param horarioInicio
     *            the horarioInicio to set
     */
    public void setHorarioInicio(String horarioInicio) {
        this.horarioInicio = horarioInicio;
    }

    /**
     * @return the horarioFin
     */
    public String getHorarioFin() {
        return horarioFin;
    }

    /**
     * @param horarioFin
     *            the horarioFin to set
     */
    public void setHorarioFin(String horarioFin) {
        this.horarioFin = horarioFin;
    }

    /**
     * @return the importeMinimo
     */
    public String getImporteMinimo() {
        return importeMinimo;
    }

    /**
     * @param importeMinimo
     *            the importeMinimo to set
     */
    public void setImporteMinimo(String importeMinimo) {
        this.importeMinimo = importeMinimo;
    }

    /**
     * @return the importeMaximo
     */
    public String getImporteMaximo() {
        return importeMaximo;
    }

    /**
     * @param importeMaximo
     *            the importeMaximo to set
     */
    public void setImporteMaximo(String importeMaximo) {
        this.importeMaximo = importeMaximo;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        HashCodeBuilder hcb = new HashCodeBuilder();
        hcb.append(operacion);
        hcb.append(horarioInicio);
        hcb.append(horarioFin);
        hcb.append(importeMinimo);
        hcb.append(importeMaximo);
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

        FondoEspecie other = (FondoEspecie) obj;
        EqualsBuilder eb = new EqualsBuilder();

        eb.append(operacion, other.getOperacion());
        eb.append(horarioInicio, other.getHorarioInicio());
        eb.append(horarioFin, other.getHorarioFin());
        eb.append(importeMinimo, other.getImporteMinimo());
        eb.append(importeMaximo, other.getImporteMaximo());
        return eb.isEquals();
    }

    /**
     * To string.
     *
     * @return the string
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this).append("operacion", operacion).append("horarioInicio", horarioInicio)
                .append("horarioFin", horarioFin).append("importeMinimo", importeMinimo)
                .append("importeMaximo", importeMaximo).toString();
    }
}
