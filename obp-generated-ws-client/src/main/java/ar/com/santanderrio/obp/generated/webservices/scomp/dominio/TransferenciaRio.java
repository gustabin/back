package ar.com.santanderrio.obp.generated.webservices.scomp.dominio;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType
public class TransferenciaRio extends Transferencia{
    @XmlElement(name = "Alias")
    protected String alias;
    
    @XmlElement(name = "AliasCBU")
    protected String aliasCBU;
    
    @XmlElement(name = "NombreDestinatario")
    protected String nombreDestinatario;

    @XmlElement(name = "Importe")
    protected String importe;

    @XmlElement(name = "TipoCuentaOrigen")
    protected String tipoCuentaOrigen;

    @XmlElement(name = "SucursalCuentaOrigen")
    protected String sucursalCuentaOrigen;

    @XmlElement(name = "NumeroCuentaOrigen")
    protected String numeroCuentaOrigen;

    @XmlElement(name = "Banco")
    protected String banco;

    @XmlElement(name = "TipoCuentaDestino")
    protected String tipoCuentaDestino;

    @XmlElement(name = "SucursalCuentaDestino")
    protected String sucursalCuentaDestino;

    @XmlElement(name = "NumeroCuentaDestino")
    protected String numeroCuentaDestino;

    @XmlElement(name = "Concepto")
    protected String concepto;

    @XmlElement(name = "DescripcionConcepto")
    protected String descripcionConcepto;

    @XmlElement(name = "PlazoAcreditacion")
    protected String plazoAcreditacion;

    @XmlElement(name = "EmailDestinatario")
    protected String emailDestinatario;

    @XmlElement(name = "Mensaje")
    protected String mensaje;

    @XmlElement(name = "NroComprobante")
    protected String nroComprobante;

    /**
     * @return the alias
     */
    public String getAlias() {
        return alias;
    }

    /**
     * @param alias
     *            the alias to set
     */
    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getAliasCBU() {
		return aliasCBU;
	}

	public void setAliasCBU(String aliasCBU) {
		this.aliasCBU = aliasCBU;
	}

	public String getNombreDestinatario() {
        return nombreDestinatario;
    }

    public void setNombreDestinatario(String nombreDestinatario) {
        this.nombreDestinatario = nombreDestinatario;
    }

    public String getImporte() {
        return importe;
    }

    public void setImporte(String importe) {
        this.importe = importe;
    }

    public String getTipoCuentaOrigen() {
        return tipoCuentaOrigen;
    }

    public void setTipoCuentaOrigen(String tipoCuentaOrigen) {
        this.tipoCuentaOrigen = tipoCuentaOrigen;
    }

    public String getSucursalCuentaOrigen() {
        return sucursalCuentaOrigen;
    }

    public void setSucursalCuentaOrigen(String sucursalCuentaOrigen) {
        this.sucursalCuentaOrigen = sucursalCuentaOrigen;
    }

    public String getNumeroCuentaOrigen() {
        return numeroCuentaOrigen;
    }

    public void setNumeroCuentaOrigen(String numeroCuentaOrigen) {
        this.numeroCuentaOrigen = numeroCuentaOrigen;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getTipoCuentaDestino() {
        return tipoCuentaDestino;
    }

    public void setTipoCuentaDestino(String tipoCuentaDestino) {
        this.tipoCuentaDestino = tipoCuentaDestino;
    }

    public String getSucursalCuentaDestino() {
        return sucursalCuentaDestino;
    }

    public void setSucursalCuentaDestino(String sucursalCuentaDestino) {
        this.sucursalCuentaDestino = sucursalCuentaDestino;
    }

    public String getNumeroCuentaDestino() {
        return numeroCuentaDestino;
    }

    public void setNumeroCuentaDestino(String numeroCuentaDestino) {
        this.numeroCuentaDestino = numeroCuentaDestino;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public String getDescripcionConcepto() {
        return descripcionConcepto;
    }

    public void setDescripcionConcepto(String descripcionConcepto) {
        this.descripcionConcepto = descripcionConcepto;
    }

    public String getPlazoAcreditacion() {
        return plazoAcreditacion;
    }

    public void setPlazoAcreditacion(String plazoAcreditacion) {
        this.plazoAcreditacion = plazoAcreditacion;
    }

    public String getEmailDestinatario() {
        return emailDestinatario;
    }

    public void setEmailDestinatario(String emailDestinatario) {
        this.emailDestinatario = emailDestinatario;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getNroComprobante() {
        return nroComprobante;
    }

    public void setNroComprobante(String nroComprobante) {
        this.nroComprobante = nroComprobante;
    }

}
