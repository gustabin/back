package ar.com.santanderrio.obp.servicios.inversiones.perfil.dao;

import javax.xml.bind.JAXBException;

import org.junit.Test;

import ar.com.santanderrio.obp.base.comun.JaxbUtils;
import ar.com.santanderrio.obp.generated.webservices.inversiones.entities.Mensaje;

/**
 * The Class MarshallPerfilTest.
 */
public class MarshallPerfilTest {

    /**
     * Transfromar xml A objeto 2.
     *
     * @throws JAXBException
     *             the JAXB exception
     */
    @Test
    public void transfromarXmlAObjeto2() throws JAXBException {
        StringBuilder xml = new StringBuilder("");
        xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>").append("<Mensaje>")
                .append("<CantidadDeDisclaimers>2</CantidadDeDisclaimers>").append("<Disclaimers>")
                .append("<Disclaimer>").append("<Titulo>Tituloo 3 disclaimer</Titulo>")
                .append("<Text>Que la concentración máxima para \"Productos y FCI de Renta Fija Publica\" no debería superar el 50%. </Text>")
                .append("</Disclaimer>").append("<Disclaimer>").append("<Titulo>Titulo disclaimer</Titulo>")
                .append("<Text>Que la concentración máxima para \"Nominado en ARS\" no debería superar el 50%. </Text>")
                .append("</Disclaimer>").append("</Disclaimers>").append("</Mensaje>");

        Mensaje mensaje = JaxbUtils.transformarXmlAObject(xml.toString(), Mensaje.class);
        System.out.println(mensaje);
    }

}
