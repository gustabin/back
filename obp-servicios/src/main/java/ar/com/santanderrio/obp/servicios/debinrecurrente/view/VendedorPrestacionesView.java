package ar.com.santanderrio.obp.servicios.debinrecurrente.view;

import java.util.List;

public class VendedorPrestacionesView {
    private String cuit;
    private String nombreFantasia;
    private List<PrestacionView> servicios;

    public VendedorPrestacionesView(String cuit, String nombreFantasia, List<PrestacionView> servicios) {
        this.cuit = cuit;
        this.nombreFantasia = nombreFantasia;
        this.servicios = servicios;
    }

    public String getCuit() {
        return cuit;
    }

    public String getNombreFantasia() {
        return nombreFantasia;
    }

    public List<PrestacionView> getServicios() {
        return servicios;
    }
}
