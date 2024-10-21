package ar.com.santanderrio.obp.servicios.scomp.client.domain;

import javax.xml.bind.annotation.XmlElement;

public class ComprobanteAltaBase extends Comprobante{

    /** The estado oper. */
    @XmlElement(name = "EstadoOper")
    protected OperacionEstado estadoOper;

    /** The desc estado. */
    @XmlElement(name = "DescEstado")
    protected String descEstado;

    /** The hora oper. */
    @XmlElement(name = "IdMedioPago")
    protected String idMedioPago;

    /** The desc estado. */
    @XmlElement(name = "DescMedioPago")
    protected String descMedioPago;

    /** The fecha oper. */
    @XmlElement(name = "FechaOper")
    protected String fechaOper;

    /** The hora oper. */
    @XmlElement(name = "HoraOper")
    protected String horaOper;

    /** The fecha oper. */
    @XmlElement(name = "FechaGen")
    protected String fechaGen;

    /** The cliente. */
    @XmlElement(name = "Cliente")
    protected Cliente cliente;
    
    public OperacionEstado getEstadoOper() {
        return estadoOper;
    }

    public void setEstadoOper(OperacionEstado estadoOper) {
        this.estadoOper = estadoOper;
    }

    public String getDescEstado() {
        return descEstado;
    }

    public void setDescEstado(String descEstado) {
        this.descEstado = descEstado;
    }

    public String getIdMedioPago() {
        return idMedioPago;
    }

    public void setIdMedioPago(String idMedioPago) {
        this.idMedioPago = idMedioPago;
    }

    public String getDescMedioPago() {
        return descMedioPago;
    }

    public void setDescMedioPago(String descMedioPago) {
        this.descMedioPago = descMedioPago;
    }

    public String getFechaOper() {
        return fechaOper;
    }

    public void setFechaOper(String fechaOper) {
        this.fechaOper = fechaOper;
    }

    public String getHoraOper() {
        return horaOper;
    }

    public void setHoraOper(String horaOper) {
        this.horaOper = horaOper;
    }

    public String getFechaGen() {
        return fechaGen;
    }

    public void setFechaGen(String fechaGen) {
        this.fechaGen = fechaGen;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
