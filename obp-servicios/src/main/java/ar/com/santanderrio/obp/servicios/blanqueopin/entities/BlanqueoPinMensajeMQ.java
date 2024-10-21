package ar.com.santanderrio.obp.servicios.blanqueopin.entities;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;


public class BlanqueoPinMensajeMQ {

	private String nup = null;
	private String dni = null;
	private String entitlement = null;
	private String timestamp = null;
	private String ttl = null;
	private String legal_type = null;
	private String error_code = null;
	private String nombre = null;
	private String apellido = null;
	private String nro_tarjeta = null;

	public String getNup() {
		return nup;
	}

	public void setNup(String nup) {
		this.nup = nup;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getEntitlement() {
		return entitlement;
	}

	public void setEntitlement(String entitlement) {
		this.entitlement = entitlement;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getTtl() {
		return ttl;
	}

	public void setTtl(String ttl) {
		this.ttl = ttl;
	}

	public String getLegal_type() {
		return legal_type;
	}

	public void setLegal_type(String legal_type) {
		this.legal_type = legal_type;
	}

	public String getError_code() {
		return error_code;
	}

	public void setError_code(String error_code) {
		this.error_code = error_code;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNro_tarjeta() {
		return nro_tarjeta;
	}

	public void setNro_tarjeta(String nro_tarjeta) {
		this.nro_tarjeta = nro_tarjeta;
	}

	/**
	 * Gets the as DOM.
	 *
	 * @return the as DOM
	 */
	public Document getAsDOM() {
		Document dom = DocumentHelper.createDocument();
		Element root = DocumentHelper.createElement("MENSAJE");
		Element header = root.addElement("HEADER");
		if (this.nup != null) {
			header.addElement("RIO-NUP").setText(this.nup);
		}
		if (this.dni != null) {
			header.addElement("RIO-DNI-CUIT").setText(this.dni);
		}
		if (this.entitlement != null) {
			header.addElement("ENTITLEMENT").setText(this.entitlement);
		}
		if (this.timestamp != null) {
			header.addElement("TIMESTAMP").setText(this.timestamp);
		}
		if (this.ttl != null) {
			header.addElement("TTL").setText(this.ttl);
		}
		if (this.legal_type != null) {
			header.addElement("LEGAL-TYPE").setText(this.legal_type);
		}
		
		header.addElement("ERROR-CODE");
		//Element ciereheader = root.addElement("/HEADER");
		Element body = root.addElement("BODY");
		
		if (this.nombre != null) {
			body.addElement("NOMBRE").setText(this.nombre);
		}
		if (this.apellido != null) {
			body.addElement("APELLIDO").setText(this.apellido);
		}
		if (this.nro_tarjeta != null) {
			body.addElement("NRO_TARJETA").setText(this.nro_tarjeta);
		}
		
		dom.setRootElement(root);

		return dom;
	}
}