package ar.com.santanderrio.obp.servicios.login.bo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.login.bo.impl.MyaSuscripcionesBOImpl;
import ar.com.santanderrio.obp.servicios.login.dao.MyaWSDAO;
import ar.com.santanderrio.obp.servicios.mya.web.view.MyaUpdateMensajeView;
import ar.com.santanderrio.obp.servicios.mya.web.view.UpdateMensajeMyaView;
import ar.com.santanderrio.obp.servicios.mya.web.view.UpdateSuscripcionMensajeMyaView;

@RunWith(MockitoJUnitRunner.class)
public class MyaBOUpdateMensajesTest {
	
	/** The mya BO. */
	@InjectMocks
	private MyaSuscripcionesBOImpl myaSuscripcionesBO;

	/** The mya WSDAO. */
	@Mock
	private MyaWSDAO myaWSDAO;
	
	/** The respuesta factory. */
	@Spy
	private RespuestaFactory respuestaFactory;
	
	@Test
	public void updateMensajesTest(){
		Cliente cliente = new Cliente();
		MyaUpdateMensajeView myaUpdateMensajeView = new MyaUpdateMensajeView();
		UpdateMensajeMyaView datos = new UpdateMensajeMyaView();
		List<UpdateSuscripcionMensajeMyaView> listaUpdatesSuscripciones = new ArrayList<UpdateSuscripcionMensajeMyaView>();
		UpdateSuscripcionMensajeMyaView updateSuscripcionMensajeMyaView1 = new UpdateSuscripcionMensajeMyaView();
		UpdateSuscripcionMensajeMyaView updateSuscripcionMensajeMyaView2 = new UpdateSuscripcionMensajeMyaView();
		UpdateSuscripcionMensajeMyaView updateSuscripcionMensajeMyaView3 = new UpdateSuscripcionMensajeMyaView();
		List<String> listaDias = new ArrayList<String>();
		
		listaDias.add("Lunes");
		updateSuscripcionMensajeMyaView1.setImporteMinimo("200,00");
		updateSuscripcionMensajeMyaView1.setListaDias(listaDias);
		updateSuscripcionMensajeMyaView1.setOperacionUpdate("alta");
		updateSuscripcionMensajeMyaView2.setOperacionUpdate("modificacion");
		updateSuscripcionMensajeMyaView3.setOperacionUpdate("baja");
		listaUpdatesSuscripciones.add(updateSuscripcionMensajeMyaView1);
		listaUpdatesSuscripciones.add(updateSuscripcionMensajeMyaView2);
		listaUpdatesSuscripciones.add(updateSuscripcionMensajeMyaView3);
		datos.setListaUpdatesSuscripciones(listaUpdatesSuscripciones);
		myaUpdateMensajeView.setDatos(datos);
		
		
		Respuesta<Void> respuesta = myaSuscripcionesBO.updateMensajes(cliente, myaUpdateMensajeView);
		assertNotNull(respuesta);
		assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
	}
}
