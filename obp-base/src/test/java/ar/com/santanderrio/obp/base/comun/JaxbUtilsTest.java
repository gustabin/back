/**
 * 
 */
package ar.com.santanderrio.obp.base.comun;

import javax.xml.bind.JAXBException;

import org.hamcrest.core.StringContains;
import org.junit.Assert;
import org.junit.Test;


/**
 * The Class JaxbUtilsTest.
 *
 * @author sergio.e.goldentair
 */
public class JaxbUtilsTest {

    /**
     * Transfromar objeto A xml.
     *
     * @throws JAXBException
     *             the JAXB exception
     */
    @Test
    public void transfromarObjetoAXml() throws JAXBException {
        PruebaJaxb prueba = new PruebaJaxb();
        prueba.setNombre("luis");

        String salida = JaxbUtils.transformarObjetoAXml(prueba, "UTF-8", Boolean.TRUE, Boolean.TRUE, "");
        Assert.assertThat(salida, StringContains.containsString("<nombre>"));
    }

    /**
     * Transfromar objeto A xml lanzar exception.
     *
     * @throws JAXBException
     *             the JAXB exception
     */
    @Test(expected = JAXBException.class)
    public void transfromarObjetoAXmlLanzarException() throws JAXBException {
        PruebaJaxbError prueba = new PruebaJaxbError();
        prueba.setNombre("luis");
        JaxbUtils.transformarObjetoAXml(prueba, "UTF-8", Boolean.TRUE, Boolean.TRUE, "");
        Assert.fail("No debe llegar hasta este punto x haber lanzado exception");
    }

    /**
     * Transfromar xml A objeto.
     *
     * @throws JAXBException
     *             the JAXB exception
     */
    @Test
    public void transfromarXmlAObjeto() throws JAXBException {
        StringBuilder xml = new StringBuilder("");
        xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>").append("<prueba>")
                .append("    <nombre>luis</nombre>").append("</prueba>");

        PruebaJaxb prueba = JaxbUtils.transformarXmlAObject(xml.toString(), PruebaJaxb.class);
        Assert.assertNotNull(prueba.getNombre());
        Assert.assertThat(prueba.getNombre(), StringContains.containsString("luis"));
    }
    
    
}
