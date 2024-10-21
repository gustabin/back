package ar.com.santanderrio.obp.generated.webservices.scomp.dominio;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType
public class ComprobanteCompraVentaDolar  extends ComprobanteAltaBase{

    @XmlElement(name = "CuentaOrigen")
    protected CuentaOrigen cuentaOrigen;
    
    @XmlElement(name = "CuentaDestino")
    protected CuentaDestino cuentaDestino;
    
    @XmlElement(name = "ImporteAcreditado")
    protected String importeAcreditado;
    
    @XmlElement(name = "ImporteDebitado")
    protected String importeDebitado;
    
    @XmlElement(name = "CotizacionAplicada")
    protected String cotizacionAplicada;
    
    @XmlElement(name = "NumeroOperacion")
    protected String numeroOperacion;
    
    @XmlElement(name = "NroComprobante")
    protected String nroComprobante;

    public CuentaOrigen getCuentaOrigen() {
        return cuentaOrigen;
    }

    public void setCuentaOrigen(CuentaOrigen cuentaOrigen) {
        this.cuentaOrigen = cuentaOrigen;
    }

    public CuentaDestino getCuentaDestino() {
        return cuentaDestino;
    }

    public void setCuentaDestino(CuentaDestino cuentaDestino) {
        this.cuentaDestino = cuentaDestino;
    }

    public String getImporteAcreditado() {
        return importeAcreditado;
    }

    public void setImporteAcreditado(String importeAcreditado) {
        this.importeAcreditado = importeAcreditado;
    }

    public String getImporteDebitado() {
        return importeDebitado;
    }

    public void setImporteDebitado(String importeDebitado) {
        this.importeDebitado = importeDebitado;
    }

    public String getCotizacionAplicada() {
        return cotizacionAplicada;
    }

    public void setCotizacionAplicada(String cotizacionAplicada) {
        this.cotizacionAplicada = cotizacionAplicada;
    }

    public String getNumeroOperacion() {
        return numeroOperacion;
    }

    public void setNumeroOperacion(String numeroOperacion) {
        this.numeroOperacion = numeroOperacion;
    }

    public String getNroComprobante() {
        return nroComprobante;
    }

    public void setNroComprobante(String nroComprobante) {
        this.nroComprobante = nroComprobante;
    }
    
    
    
}
