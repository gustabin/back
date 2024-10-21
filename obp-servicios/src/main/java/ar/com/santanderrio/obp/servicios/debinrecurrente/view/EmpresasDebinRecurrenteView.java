package ar.com.santanderrio.obp.servicios.debinrecurrente.view;

import java.io.Serializable;
import java.util.List;

public class EmpresasDebinRecurrenteView implements Serializable {
    private List<VendedorView> empresas;
    private String mensajeError;
    private String mensajeNoPermitePago;
    private String mensajeImporteLimite;
    private String pagoComprasAyuda;
    private String mensajeInformacionPagoAdebitar;

    public EmpresasDebinRecurrenteView() {
    }

    public EmpresasDebinRecurrenteView(List<VendedorView> empresas, String mensajeError, String mensajeNoPermitePago, String mensajeImporteLimite, String pagoComprasAyuda, String mensajeInformacionPagoAdebitar) {
        this.empresas = empresas;
        this.mensajeError = mensajeError;
        this.mensajeNoPermitePago = mensajeNoPermitePago;
        this.mensajeImporteLimite = mensajeImporteLimite;
        this.pagoComprasAyuda = pagoComprasAyuda;
        this.mensajeInformacionPagoAdebitar = mensajeInformacionPagoAdebitar;
    }

    public List<VendedorView> getEmpresas() {
        return empresas;
    }

    public void setEmpresas(List<VendedorView> empresas) {
        this.empresas = empresas;
    }

    public String getMensajeError() {
        return mensajeError;
    }

    public void setMensajeError(String mensajeError) {
        this.mensajeError = mensajeError;
    }

    public String getMensajeNoPermitePago() {
        return mensajeNoPermitePago;
    }

    public void setMensajeNoPermitePago(String mensajeNoPermitePago) {
        this.mensajeNoPermitePago = mensajeNoPermitePago;
    }

    public String getMensajeImporteLimite() {
        return mensajeImporteLimite;
    }

    public void setMensajeImporteLimite(String mensajeImporteLimite) {
        this.mensajeImporteLimite = mensajeImporteLimite;
    }

    public String getPagoComprasAyuda() {
        return pagoComprasAyuda;
    }

    public void setPagoComprasAyuda(String pagoComprasAyuda) {
        this.pagoComprasAyuda = pagoComprasAyuda;
    }

    public String getMensajeInformacionPagoAdebitar() {
        return mensajeInformacionPagoAdebitar;
    }

    public void setMensajeInformacionPagoAdebitar(String mensajeInformacionPagoAdebitar) {
        this.mensajeInformacionPagoAdebitar = mensajeInformacionPagoAdebitar;
    }
}
