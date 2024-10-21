/**
 * 
 */
package ar.com.santanderrio.obp.generated.webservices.alias;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * @author leonardo.medina
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "http://ws.transferencias.banelco.com/")
public class ConsultarDatosTitularidadExtendidoResponse {
	private TitularidadExtendido titularidadExtendido;
	
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

	public TitularidadExtendido getTitularidadExtendido() {
		return titularidadExtendido;
	}

	public void setTitularidadExtendido(TitularidadExtendido titularidadExtendido) {
		this.titularidadExtendido = titularidadExtendido;
	}

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ConsultarDatosTitularidadExtendidoResponse [titularidadExtendido=" + titularidadExtendido + ", codigo="
                + codigo + ", mensaje=" + mensaje + "]";
    }
	
	
	
}
