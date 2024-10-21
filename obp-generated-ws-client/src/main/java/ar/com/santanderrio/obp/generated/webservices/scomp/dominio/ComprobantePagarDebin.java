package ar.com.santanderrio.obp.generated.webservices.scomp.dominio;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * The class ComprobantePagarDebin
 * 
 */
@XmlRootElement
@XmlType
public class ComprobantePagarDebin extends Comprobante {

    /** The estado oper. */
    @XmlElement(name = "EstadoOper")
    protected OperacionEstado estadoOper;

    /** The desc estado. */
    @XmlElement(name = "DescEstado")
    protected String descEstado;

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

    /** The destino. */
    @XmlElement(name = "Debin")
    protected PagoDebin pagoDebin;

	/**
	 * @return the estadoOper
	 */
	public OperacionEstado getEstadoOper() {
		return estadoOper;
	}

	/**
	 * @param estadoOper the estadoOper to set
	 */
	public void setEstadoOper(OperacionEstado estadoOper) {
		this.estadoOper = estadoOper;
	}

	/**
	 * @return the descEstado
	 */
	public String getDescEstado() {
		return descEstado;
	}

	/**
	 * @param descEstado the descEstado to set
	 */
	public void setDescEstado(String descEstado) {
		this.descEstado = descEstado;
	}

	/**
	 * @return the fechaOper
	 */
	public String getFechaOper() {
		return fechaOper;
	}

	/**
	 * @param fechaOper the fechaOper to set
	 */
	public void setFechaOper(String fechaOper) {
		this.fechaOper = fechaOper;
	}

	/**
	 * @return the horaOper
	 */
	public String getHoraOper() {
		return horaOper;
	}

	/**
	 * @param horaOper the horaOper to set
	 */
	public void setHoraOper(String horaOper) {
		this.horaOper = horaOper;
	}

	/**
	 * @return the fechaGen
	 */
	public String getFechaGen() {
		return fechaGen;
	}

	/**
	 * @param fechaGen the fechaGen to set
	 */
	public void setFechaGen(String fechaGen) {
		this.fechaGen = fechaGen;
	}

	/**
	 * @return the pagoDebin
	 */
	public PagoDebin getPagoDebin() {
		return pagoDebin;
	}

	/**
	 * @param pagoDebin the pagoDebin to set
	 */
	public void setPagoDebin(PagoDebin pagoDebin) {
		this.pagoDebin = pagoDebin;
	}

	/**
	 * @return the cliente
	 */
	public Cliente getCliente() {
		return cliente;
	}

	/**
	 * @param cliente the cliente to set
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	
    
}
