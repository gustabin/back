/*
 *
 */
package ar.com.santanderrio.obp.servicios.prestamos.view;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class PrestamosEncoladosView {

    private List<PrestamoEncoladoView> prestamos = new ArrayList<PrestamoEncoladoView>();

    public List<PrestamoEncoladoView> getPrestamos() {
        return prestamos;
    }

    public void setPrestamos(List<PrestamoEncoladoView> prestamos) {
        this.prestamos = prestamos;
    }
}