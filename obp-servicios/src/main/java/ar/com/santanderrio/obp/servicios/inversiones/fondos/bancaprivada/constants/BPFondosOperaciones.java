package ar.com.santanderrio.obp.servicios.inversiones.fondos.bancaprivada.constants;

public enum BPFondosOperaciones {
    SIMULACION_SUSCRIPCION("SU"),
    SUSCRIPCION("SU"),
    SIMULACION_RESCATE("RE"),
    RESCATE("RE"),
    SIMULACION_TRANSFERENCIA("TR"),
    TRANSFERENCIA("TR");

    private String codigoOperacion;

    BPFondosOperaciones(String codigoOperacion) {
        this.codigoOperacion = codigoOperacion;
    }

    public String getCodigoOperacion() {
        return codigoOperacion;
    }
}
