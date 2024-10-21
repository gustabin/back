/**
 * 
 */
package ar.com.santanderrio.obp.generated.webservices.mya.dominio;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Maneja la respuesta obtenida del ws soap de mya.
 * 
 * @author sergio.e.goldentair
 *
 */
@XmlRootElement(name = "respuesta")
@XmlAccessorType(XmlAccessType.FIELD)
public class MyaXmlResponse {
    @XmlElement(name = "CodRet")
    private String codigoRetorno;
    @XmlElement(name = "ClienteEstado")
    private String clienteEstado;
    @XmlElement(name = "Cuenta")
    private String cuenta;
    @XmlElement(name = "TipoCuenta")
    private String tipoCuenta;
    @XmlElement(name = "IDSistema")
    private String sistemaId;
    @XmlElement(name = "CantDescStatusResultado")
    private String statusDescripcion;

    @XmlElementWrapper(name = "Destinos")
    @XmlElement(name = "Destino")
    private List<MyaDestino> listMyaDestino;

    @XmlElementWrapper(name = "ListaProductos")
    @XmlElement(name = "Producto")
    private List<MyaProducto> listMyaProducto;

    @XmlElementWrapper(name = "ListaFrecuencias")
    @XmlElement(name = "Frecuencia")
    private List<MyaFrecuencia> listMyaFrecuencia;

    @XmlElementWrapper(name = "ListaDiasAvisoPrevio")
    @XmlElement(name = "DiasAvisoPrevio")
    private List<MyaDiasAvisoPrevio> listMyaDiasAvisoPrevio;

    @XmlElementWrapper(name = "ListaTelCelulares")
    @XmlElement(name = "EmpresaCel")
    private List<MyaEmpresaCel> listMyaEmpresaCel;

    @XmlElementWrapper(name = "ListadoAlias")
    @XmlElement(name = "Alias")
    private List<MyaAlias> listMyaAlias;

    @XmlElementWrapper(name = "Mensajes")
    @XmlElement(name = "Mensaje")
    private List<MyaMensajeError> listMyaMensajeError;
    

    /**
     * @return the codigoRetorno
     */
    public String getCodigoRetorno() {
        return codigoRetorno;
    }

    /**
     * @param codigoRetorno
     *            the codigoRetorno to set
     */
    public void setCodigoRetorno(String codigoRetorno) {
        this.codigoRetorno = codigoRetorno;
    }

    /**
     * @return the clienteEstado
     */
    public String getClienteEstado() {
        return clienteEstado;
    }

    /**
     * @param clienteEstado
     *            the clienteEstado to set
     */
    public void setClienteEstado(String clienteEstado) {
        this.clienteEstado = clienteEstado;
    }

    /**
     * @return the listMyaDestino
     */
    public List<MyaDestino> getListMyaDestino() {
        return listMyaDestino;
    }

    /**
     * @param listMyaDestino
     *            the listMyaDestino to set
     */
    public void setListMyaDestino(List<MyaDestino> listMyaDestino) {
        this.listMyaDestino = listMyaDestino;
    }

    /**
     * @return the listMyaProducto
     */
    public List<MyaProducto> getListMyaProducto() {
        return listMyaProducto;
    }

    /**
     * @param listMyaProducto
     *            the listMyaProducto to set
     */
    public void setListMyaProducto(List<MyaProducto> listMyaProducto) {
        this.listMyaProducto = listMyaProducto;
    }

    /**
     * @return the listMyaFrecuencia
     */
    public List<MyaFrecuencia> getListMyaFrecuencia() {
        return listMyaFrecuencia;
    }

    /**
     * @param listMyaFrecuencia
     *            the listMyaFrecuencia to set
     */
    public void setListMyaFrecuencia(List<MyaFrecuencia> listMyaFrecuencia) {
        this.listMyaFrecuencia = listMyaFrecuencia;
    }

    /**
     * @return the listMyaDiasAvisoPrevio
     */
    public List<MyaDiasAvisoPrevio> getListMyaDiasAvisoPrevio() {
        return listMyaDiasAvisoPrevio;
    }

    /**
     * @param listMyaDiasAvisoPrevio
     *            the listMyaDiasAvisoPrevio to set
     */
    public void setListMyaDiasAvisoPrevio(List<MyaDiasAvisoPrevio> listMyaDiasAvisoPrevio) {
        this.listMyaDiasAvisoPrevio = listMyaDiasAvisoPrevio;
    }

    /**
     * @return the cuenta
     */
    public String getCuenta() {
        return cuenta;
    }

    /**
     * @param cuenta
     *            the cuenta to set
     */
    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    /**
     * @return the tipoCuenta
     */
    public String getTipoCuenta() {
        return tipoCuenta;
    }

    /**
     * @param tipoCuenta
     *            the tipoCuenta to set
     */
    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    /**
     * @return the listMyaEmpresaCel
     */
    public List<MyaEmpresaCel> getListMyaEmpresaCel() {
        return listMyaEmpresaCel;
    }

    /**
     * @param listMyaEmpresaCel
     *            the listMyaEmpresaCel to set
     */
    public void setListMyaEmpresaCel(List<MyaEmpresaCel> listMyaEmpresaCel) {
        this.listMyaEmpresaCel = listMyaEmpresaCel;
    }

    /**
     * @return the listMyaAlias
     */
    public List<MyaAlias> getListMyaAlias() {
        return listMyaAlias;
    }

    /**
     * @param listMyaAlias
     *            the listMyaAlias to set
     */
    public void setListMyaAlias(List<MyaAlias> listMyaAlias) {
        this.listMyaAlias = listMyaAlias;
    }

    /**
     * @return the sistemaId
     */
    public String getSistemaId() {
        return sistemaId;
    }

    /**
     * @param sistemaId
     *            the sistemaId to set
     */
    public void setSistemaId(String sistemaId) {
        this.sistemaId = sistemaId;
    }

    /**
     * @return the statusDescripcion
     */
    public String getStatusDescripcion() {
        return statusDescripcion;
    }

    /**
     * @param statusDescripcion
     *            the statusDescripcion to set
     */
    public void setStatusDescripcion(String statusDescripcion) {
        this.statusDescripcion = statusDescripcion;
    }

    /**
     * @return the listMyaMensajeError
     */
    public List<MyaMensajeError> getListMyaMensajeError() {
        return listMyaMensajeError;
    }

    /**
     * @param listMyaMensajeError
     *            the listMyaMensajeError to set
     */
    public void setListMyaMensajeError(List<MyaMensajeError> listMyaMensajeError) {
        this.listMyaMensajeError = listMyaMensajeError;
    }

}