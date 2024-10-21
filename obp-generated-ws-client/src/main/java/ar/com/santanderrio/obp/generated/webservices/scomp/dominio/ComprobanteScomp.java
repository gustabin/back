/**
 * 
 */
package ar.com.santanderrio.obp.generated.webservices.scomp.dominio;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlType;

import ar.com.santanderrio.obp.generated.webservices.scomp.Categoria;

/**
 * Comprobante de scomp.
 * 
 * @author sergio.e.goldentair
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class ComprobanteScomp {
    /** El identificador del comprobante. */
    @XmlElement(name = "id")
    public String comprobanteId;
    /** El tipo del comprobante. */
    @XmlElement(name = "tipo")
    public String comprobanteTipo;
    /** El listado de categorias del comprobante. */
    @XmlElementWrapper(name = "categorias")
    @XmlElement(name = "categoria")
    protected List<Categoria> categoriasList;
    /** El listado con el detalle del comprobante. */
    @XmlElements({ @XmlElement(name = "PAGHABCCI", type = ComprobantePaghabcci.class),
            @XmlElement(name = "TRFCCI", type = ComprobanteTrfcci.class),
            @XmlElement(name = "TRFCTA", type = ComprobanteTrfcta.class),
            @XmlElement(name = "TRFCTA7X24", type = ComprobanteTrfcta7x24.class),
            @XmlElement(name = "TARJETAS-RECARGABLES-OK", type = ComprobanteTarjRecargOk.class),
            @XmlElement(name = "PAGO-COMPRAS-OK", type = ComprobantePagoCompraOk.class),
            @XmlElement(name = "TRANSF-INM-RIO-RIO", type = ComprobanteTransfInmRioRio.class),
            @XmlElement(name = "TRANSF-INM-OB", type = ComprobanteTransfInmRioOB.class),
            @XmlElement(name = "PMC-PAGO-CON-DEUDA", type = ComprobantePMCConDeuda.class),
            @XmlElement(name = "PMC-PAGO-SIN-DEUDA", type = ComprobantePMCSinDeuda.class),
            @XmlElement(name = "PMC-PAGO-AFIP", type = ComprobantePMCAfip.class),
            @XmlElement(name = "FINAN-RESUMEN-TARJETA-SANTANDER", type = ComprobanteFinanciacionResumen.class),
            @XmlElement(name = "PMC-PAGO-VEP", type = ComprobantePMCVEP.class),
            @XmlElement(name = "COMPRAVENTA-DOLARES", type = ComprobanteCompraVentaDolar.class)}
    )
    @XmlElementWrapper(name = "detalle")
    protected List<Comprobante> comprobanteList;
    
    @XmlElements({
        @XmlElement(name = "PMC-PAGO-CON-DEUDA-TC", type = ComprobantePMCConDeuda.class),
        @XmlElement(name = "PMC-PAGO-SIN-DEUDA-TC", type = ComprobantePMCSinDeuda.class)})
    protected Comprobante comprobante;
    
    /** The estadoOper. */
	@XmlAttribute(name = "estadoOper")
	protected String estadoOper;
	
	/** The fechaOper. */
	@XmlAttribute(name = "fechaOper")
	protected String fechaOper;
	
	/** The horaOper. */
	@XmlAttribute(name = "horaOper")
	protected String horaOper;
	
	/** The id. */
	@XmlAttribute(name = "id")
	protected String id;
	
	/** The nroDoc. */
	@XmlAttribute(name = "nroDoc")
	protected String nroDoc;
	
	/** The nup. */
	@XmlAttribute(name = "nup")
	protected String nup;
	
	/** The tpoDoc. */
	@XmlAttribute(name = "tpoDoc")
	protected String tpoDoc;

    /**
     * Gets the comprobante id.
     *
     * @return the comprobanteId
     */
    public String getComprobanteId() {
        return comprobanteId;
    }

    /**
     * Sets the comprobante id.
     *
     * @param comprobanteId
     *            the comprobanteId to set
     */
    public void setComprobanteId(String comprobanteId) {
        this.comprobanteId = comprobanteId;
    }

    /**
     * Gets the comprobante tipo.
     *
     * @return the comprobanteTipo
     */
    public String getComprobanteTipo() {
        return comprobanteTipo;
    }

    /**
     * Sets the comprobante tipo.
     *
     * @param comprobanteTipo
     *            the comprobanteTipo to set
     */
    public void setComprobanteTipo(String comprobanteTipo) {
        this.comprobanteTipo = comprobanteTipo;
    }

    /**
     * Gets the categorias list.
     *
     * @return the categoriasList
     */
    public List<Categoria> getCategoriasList() {
        return categoriasList;
    }

    /**
     * Sets the categorias list.
     *
     * @param categoriasList
     *            the categoriasList to set
     */
    public void setCategoriasList(List<Categoria> categoriasList) {
        this.categoriasList = categoriasList;
    }

    /**
     * Gets the comprobante list.
     *
     * @return the comprobanteList
     */
    public List<Comprobante> getComprobanteList() {
        return comprobanteList;
    }

    /**
     * Sets the comprobante list.
     *
     * @param comprobanteList
     *            the comprobanteList to set
     */
    public void setComprobanteList(List<Comprobante> comprobanteList) {
        this.comprobanteList = comprobanteList;
    }

	public Comprobante getComprobante() {
        return comprobante;
    }

    public void setComprobante(Comprobante comprobante) {
        this.comprobante = comprobante;
    }

    public String getEstadoOper() {
		return estadoOper;
	}

	public String getFechaOper() {
		return fechaOper;
	}

	public String getHoraOper() {
		return horaOper;
	}

	public String getId() {
		return id;
	}

	public String getNroDoc() {
		return nroDoc;
	}

	public String getNup() {
		return nup;
	}

	public String getTpoDoc() {
		return tpoDoc;
	}

	public void setEstadoOper(String estadoOper) {
		this.estadoOper = estadoOper;
	}

	public void setFechaOper(String fechaOper) {
		this.fechaOper = fechaOper;
	}

	public void setHoraOper(String horaOper) {
		this.horaOper = horaOper;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setNroDoc(String nroDoc) {
		this.nroDoc = nroDoc;
	}

	public void setNup(String nup) {
		this.nup = nup;
	}

	public void setTpoDoc(String tpoDoc) {
		this.tpoDoc = tpoDoc;
	}
}