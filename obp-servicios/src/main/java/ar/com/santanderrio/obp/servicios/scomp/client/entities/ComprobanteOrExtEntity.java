package ar.com.santanderrio.obp.servicios.scomp.client.entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "comprobanteOrExtEntity")
public class ComprobanteOrExtEntity {

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
