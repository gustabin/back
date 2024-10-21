package ar.com.santanderrio.obp.base.comun;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Locale;

import javax.xml.bind.JAXBException;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.springframework.util.ResourceUtils;

import ar.com.santanderrio.obp.base.comun.JaxbUtils;
import ar.com.santanderrio.obp.servicios.mya.entities.GetStatusClienteDTOOut;
import ar.com.santanderrio.obp.servicios.sieteporveinticuatro.version1.entities.XMLResponseEntity;

public class JaxbUtilsTest {
	
	private String getXML(String nombreArchivo) throws IOException {
		return IOUtils.toString(ResourceUtils.getURL(Object.class.getResource("/").getPath() + "/" + nombreArchivo),
			      "UTF-8"
			    );
	}
	
	@Test
	public void testXML7x24CantidadRegistrosCero() throws JAXBException, IOException {
		String xml = getXML("7x24CantidadCero.xml");
		
		
		XMLResponseEntity entity = JaxbUtils.transformarXmlAObject(xml, XMLResponseEntity.class);
		
		assertEquals("0",  entity.getDATOSRESULTADO().getCantidadRegistro());
		
	}
	
	@Test
	public void testXML7x24Comprobante() throws JAXBException, IOException {
		String xml = getXML("7x24Comprobante.xml");
		
		
		XMLResponseEntity entity = JaxbUtils.transformarXmlAObject(xml, XMLResponseEntity.class);
		
		assertEquals("3",  entity.getDATOSRESULTADO().getCantidadRegistro());
		
		assertEquals("TRANSF_BCO_RIO",  entity.getDATOSRESULTADO().getRegistro().get(0).getXmlEntrada().getNombre());
		
		assertEquals("Caja de Ahorro en Pesos",  entity.getDATOSRESULTADO().getRegistro().get(0).getXmlEntrada().getDatosAdicionales().getDatosMensAvisos().getTitularCredito());
		
	}
	
}
