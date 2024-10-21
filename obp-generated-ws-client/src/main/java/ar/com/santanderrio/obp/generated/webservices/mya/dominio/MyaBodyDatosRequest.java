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
 * @author sergio.e.goldentair
 *
 */
@XmlRootElement(name = "datos")
@XmlAccessorType(XmlAccessType.FIELD)
public class MyaBodyDatosRequest {
    @XmlElement(name = "TipoId")
    private MyaTipoIdEnum tipoId;
    @XmlElement(name = "IdCliente")
    private String clienteId;
    @XmlElement(name = "TipoPersona")
    private MyaTipoPersonaEnum tipoPersona;
    @XmlElement(name = "FechaNacimiento")
    private String fechaNacimiento;
    @XmlElement(name = "Nombre")
    private String nombre;
    @XmlElement(name = "PrimerApellido")
    private String primerNombre;
    @XmlElement(name = "SegundoApellido")
    private String segundoNombre;
    @XmlElementWrapper(name = "ListaDeCuentas")
    @XmlElement(name = "Cuenta")
    private List<MyaCuenta> listMyaCuenta;
    @XmlElementWrapper(name = "Destinos")
    @XmlElement(name = "Destino")
    private List<MyaDestino> listMyaDestino;
    @XmlElementWrapper(name = "ListaDeProductos")
    @XmlElement(name = "Producto")
    private List<MyaProductoReq> listMyaProductoReq;
    @XmlElementWrapper(name = "Suscripciones")
    @XmlElement(name = "Suscripcion")
    private List<MyaSuscripcion> listMyaSuscripcion;
    @XmlElement(name = "emailConfirmado")
    private String emailConfirmado;
    
    /**
     * @return the tipoId
     */
    public MyaTipoIdEnum getTipoId() {
        return tipoId;
    }

    /**
     * @param tipoId
     *            the tipoId to set
     */
    public void setTipoId(MyaTipoIdEnum tipoId) {
        this.tipoId = tipoId;
    }

    /**
     * @return the clienteId
     */
    public String getClienteId() {
        return clienteId;
    }

    /**
     * @param clienteId
     *            the clienteId to set
     */
    public void setClienteId(String clienteId) {
        this.clienteId = clienteId;
    }

    /**
     * @return the tipoPersona
     */
    public MyaTipoPersonaEnum getTipoPersona() {
        return tipoPersona;
    }

    /**
     * @param tipoPersona
     *            the tipoPersona to set
     */
    public void setTipoPersona(MyaTipoPersonaEnum tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    /**
     * @return the fechaNacimiento
     */
    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * @param fechaNacimiento
     *            the fechaNacimiento to set
     */
    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre
     *            the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the primerNombre
     */
    public String getPrimerNombre() {
        return primerNombre;
    }

    /**
     * @param primerNombre
     *            the primerNombre to set
     */
    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    /**
     * @return the segundoNombre
     */
    public String getSegundoNombre() {
        return segundoNombre;
    }

    /**
     * @param segundoNombre
     *            the segundoNombre to set
     */
    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    /**
     * @return the listMyaCuenta
     */
    public List<MyaCuenta> getListMyaCuenta() {
        return listMyaCuenta;
    }

    /**
     * @param listMyaCuenta
     *            the listMyaCuenta to set
     */
    public void setListMyaCuenta(List<MyaCuenta> listMyaCuenta) {
        this.listMyaCuenta = listMyaCuenta;
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
     * @return the listMyaProductorReq
     */
    public List<MyaProductoReq> getListMyaProductoReq() {
        return listMyaProductoReq;
    }

    /**
     * @param listMyaProducto
     *            the listMyaProducto to set
     */
    public void setListMyaProductoReq(List<MyaProductoReq> listMyaProductoReq) {
        this.listMyaProductoReq = listMyaProductoReq;
    }

	public List<MyaSuscripcion> getListMyaSuscripcion() {
		return listMyaSuscripcion;
	}

	public void setListMyaSuscripcion(List<MyaSuscripcion> listMyaSuscripcion) {
		this.listMyaSuscripcion = listMyaSuscripcion;
	}

	public String getEmailConfirmado() {
		return emailConfirmado;
	}

	public void setEmailConfirmado(String emailConfirmado) {
		this.emailConfirmado = emailConfirmado;
	}
	    
}
