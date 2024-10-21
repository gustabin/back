package ar.com.santanderrio.obp.servicios.debinrecurrente.view;

public class ActivarDebinRecurrenteView {
    private boolean activarRecurrencias;

    public ActivarDebinRecurrenteView(boolean activarRecurrencias) {
        this.activarRecurrencias = activarRecurrencias;
    }

    public boolean isActivarRecurrencias() {
        return activarRecurrencias;
    }

    public void setActivarRecurrencias(boolean activarRecurrencias) {
        this.activarRecurrencias = activarRecurrencias;
    }
}
