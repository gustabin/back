/**
 * 
 */
package ar.com.santanderrio.obp.generated.webservices.alias;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * The Class ConsultarDatosTitularidad.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "http://ws.transferencias.banelco.com/")
public class ConsultarDatosTitularidadResponse {
	private Titularidad titularidad;
	
	private String codigo;
	
	private String mensaje;
	
	/**
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return the mensaje
	 */
	public String getMensaje() {
		return mensaje;
	}

	/**
	 * @param mensaje the mensaje to set
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public Titularidad getTitularidad() {
		return titularidad;
	}

	public void setTitularidad(Titularidad titularidad) {
		this.titularidad = titularidad;
	}

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ConsultarDatosTitularidadResponse [titularidad=" + titularidad + ", codigo="
                + codigo + ", mensaje=" + mensaje + "]";
    }
	
	
	
}
