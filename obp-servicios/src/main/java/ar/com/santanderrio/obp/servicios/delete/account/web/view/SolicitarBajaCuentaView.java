package ar.com.santanderrio.obp.servicios.delete.account.web.view;

public class SolicitarBajaCuentaView {

    private boolean mostrarRetencion = false;

    public SolicitarBajaCuentaView () {}

    public boolean isMostrarRetencion() {
        return mostrarRetencion;
    }

    public void setMostrarRetencion(boolean mostrarRetencion) {
        this.mostrarRetencion = mostrarRetencion;
    }
}
