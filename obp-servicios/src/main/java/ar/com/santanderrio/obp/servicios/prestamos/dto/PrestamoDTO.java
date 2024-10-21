package ar.com.santanderrio.obp.servicios.prestamos.dto;

import ar.com.santanderrio.obp.servicios.pagos.entities.PreFormalizacion;
import ar.com.santanderrio.obp.servicios.pagos.entities.Prestamo;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;


public class PrestamoDTO {

    private Prestamo prestamo;
    private PreFormalizacion preFormalizacion;

    public PrestamoDTO() {}

    public PrestamoDTO(Prestamo prestamo, PreFormalizacion preFormalizacion) {
        this.prestamo = prestamo;
        this.preFormalizacion = preFormalizacion;
    }

    public Prestamo getPrestamo() {
        return prestamo;
    }

    public void setPrestamo(Prestamo prestamo) {
        this.prestamo = prestamo;
    }

    public PreFormalizacion getPreFormalizacion() {
        return preFormalizacion;
    }

    public void setPreFormalizacion(PreFormalizacion preFormalizacion) {
        this.preFormalizacion = preFormalizacion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PrestamoDTO that = (PrestamoDTO) o;
        return new EqualsBuilder()
                .append(prestamo, that.prestamo)
                .append(preFormalizacion, that.preFormalizacion)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(prestamo)
                .append(preFormalizacion)
                .toHashCode();
    }
}
