package ar.com.santanderrio.obp.servicios.tenencias.entity;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;

@RunWith(MockitoJUnitRunner.class)
public class ConsultaResumenTokenEntityTest {
	
	//Para ver la forma que toma el objeto al parsearse a xml, y corroborar su funcionamiento
	@Test
	public void ConsultaResumenTokenEntityXmlTest(){
		Cliente cliente = new Cliente();
		cliente.setDni("37864981");
		Cuenta cuenta = new Cuenta();
		cuenta.setTipoCuentaEnum(TipoCuenta.BANELCO);
		cuenta.setNroTarjetaCredito("17924182347941234");
		cliente.setCuentas(new ArrayList<Cuenta>());
		cliente.getCuentas().add(cuenta);
		ConsultaResumenTokenEntity entity = new ConsultaResumenTokenEntity(cliente , "DNI");
		
		try {
			StringWriter stringWriter = new StringWriter();
			JAXBContext jaxbContext = JAXBContext.newInstance(ConsultaResumenTokenEntity.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(entity, stringWriter);
			String res = stringWriter.toString();
			stringWriter.close();
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
