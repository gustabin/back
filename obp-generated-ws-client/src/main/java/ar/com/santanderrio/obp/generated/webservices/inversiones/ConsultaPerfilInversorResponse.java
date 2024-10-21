/**
 * 
 */
package ar.com.santanderrio.obp.generated.webservices.inversiones;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * 
 * @author marcelo.ruiz
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class ConsultaPerfilInversorResponse {
	
	@XmlElement(name = "Descripcion")
    private String descripcion;
    @XmlElement(name = "IdPerfil")
    private String idPerfil;

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion
     *            the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the idPerfil
     */
    public String getIdPerfil() {
        return idPerfil;
    }

    /**
     * @param idPerfil
     *            the idPerfil to set
     */
    public void setIdPerfil(String idPerfil) {
        this.idPerfil = idPerfil;
    }
}
