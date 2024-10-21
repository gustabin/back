package ar.com.santanderrio.obp.servicios.inversiones.fondos.bancaprivada.model;

public class DatosBuscadosCuentaOperativa {
    private String cuentaTitulos;
    private String tipoCuenta;

    public String getCuentaTitulos() {
        return cuentaTitulos;
    }

    public void setCuentaTitulos(String cuentaTitulos) {
        this.cuentaTitulos = cuentaTitulos;
    }

    public String getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }
}
