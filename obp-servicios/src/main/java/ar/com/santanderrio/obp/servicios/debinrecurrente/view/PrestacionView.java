package ar.com.santanderrio.obp.servicios.debinrecurrente.view;

public class PrestacionView {
    private Integer minimo;
    private Integer maximo;
    private String nombre;
    private String tooltipReferencia;

    public PrestacionView(Integer minimo, Integer maximo, String nombre, String tooltipReferencia) {
        this.minimo = minimo;
        this.maximo = maximo;
        this.nombre = nombre;
        this.tooltipReferencia = tooltipReferencia;
    }

    public Integer getMinimo() {
        return minimo;
    }

    public Integer getMaximo() {
        return maximo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTooltipReferencia() {
        return tooltipReferencia;
    }
}
