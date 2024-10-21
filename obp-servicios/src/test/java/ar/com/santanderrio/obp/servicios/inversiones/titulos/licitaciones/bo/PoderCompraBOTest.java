package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.bo;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.inversiones.comun.TipoBancaEnum;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dao.PoderCompraDAO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dto.AdhesionPDCOutDTO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dto.AdhesionPDCRequestDTO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dto.FinalizarAdhesionDTO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dto.FinalizarAdhesionPDC;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.CheckboxCrearAdhesionResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.CrearAdhesionDatosResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.CrearAdhesionRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.CrearAdhesionResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.DisclaimersAdhesionPDCResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.FormCamposAdhesionPDCResponse;

@RunWith(MockitoJUnitRunner.class)
public class PoderCompraBOTest {

	@Mock
	private PoderCompraDAO poderCompraDAO;

	@InjectMocks
	private PoderCompraBOImpl poderCompraBOImpl;

	@Mock
	private RespuestaFactory respuestaFactory;

	@Test
	public void simularAdhesionTestOK() throws DAOException {
		CrearAdhesionResponse response = new CrearAdhesionResponse();
		CrearAdhesionDatosResponse datos = new CrearAdhesionDatosResponse();
		
		List<DisclaimersAdhesionPDCResponse> disclaimers = new ArrayList<DisclaimersAdhesionPDCResponse>();
		DisclaimersAdhesionPDCResponse disclaimer = new DisclaimersAdhesionPDCResponse();
		disclaimer.setTipo("I");
		disclaimer.setContenido("prueba");
		disclaimers.add(disclaimer);
		
		DisclaimersAdhesionPDCResponse disclaimer2 = new DisclaimersAdhesionPDCResponse();
		disclaimer2.setTipo("T");
		disclaimer2.setContenido("prueba");
		
		List<CheckboxCrearAdhesionResponse> listCheckbox = new ArrayList<CheckboxCrearAdhesionResponse>();
		CheckboxCrearAdhesionResponse checkbox = new CheckboxCrearAdhesionResponse();
		checkbox.setChecked(true);
		checkbox.setDesc("prueba");
		listCheckbox.add(checkbox);
		
		disclaimer2.setListaCheckboxs(listCheckbox);
		disclaimers.add(disclaimer2);
		
		datos.setDisclaimers(disclaimers);
		response.setDatos(datos);
		Mockito.when(poderCompraDAO.adherirPoderCompra(Matchers.any(CrearAdhesionRequestEntity.class)))
				.thenReturn(response);

		Respuesta<AdhesionPDCOutDTO> respBO = new Respuesta<AdhesionPDCOutDTO>();
		respBO.setEstadoRespuesta(EstadoRespuesta.OK);

		Mockito.when(
				respuestaFactory.crearRespuestaOk(Matchers.any(Class.class), Matchers.any(AdhesionPDCOutDTO.class)))
				.thenReturn(respBO);
		Respuesta<AdhesionPDCOutDTO> resp = poderCompraBOImpl.simularAdhesion(new AdhesionPDCRequestDTO(),
				new Cliente(), TipoBancaEnum.BANCA_PERSONAL);
		Assert.assertNotNull(resp);
		Assert.assertEquals(resp.getEstadoRespuesta(), EstadoRespuesta.OK);
	}

	@Test
	public void simularAdhesionTestError() throws DAOException {

		Mockito.when(poderCompraDAO.adherirPoderCompra(Matchers.any(CrearAdhesionRequestEntity.class)))
				.thenThrow(new DAOException());

		Respuesta respBO = new Respuesta();
		respBO.setEstadoRespuesta(EstadoRespuesta.ERROR);

		Mockito.when(respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
				CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS)).thenReturn(respBO);
		Respuesta<AdhesionPDCOutDTO> resp = poderCompraBOImpl.simularAdhesion(new AdhesionPDCRequestDTO(),
				new Cliente(), TipoBancaEnum.BANCA_PERSONAL);
		Assert.assertNotNull(resp);
		Assert.assertEquals(resp.getEstadoRespuesta(), EstadoRespuesta.ERROR);
	}

	@Test
	public void finalizarAdhesionTestOK() throws DAOException {
		CrearAdhesionResponse response = new CrearAdhesionResponse();
		CrearAdhesionDatosResponse datos = new CrearAdhesionDatosResponse();
		response.setDatos(datos);
		List<DisclaimersAdhesionPDCResponse> disclaimers = new ArrayList<DisclaimersAdhesionPDCResponse>();
		response.getDatos().setDisclaimers(disclaimers);
		response.setCodigo(0);
		response.getDatos().setResultado("1");
		
		List<ArrayList<FormCamposAdhesionPDCResponse>> list = new ArrayList<ArrayList<FormCamposAdhesionPDCResponse>>();
		response.getDatos().setFormCampos(list);
		ArrayList<FormCamposAdhesionPDCResponse> listForm = new ArrayList<FormCamposAdhesionPDCResponse>();
		FormCamposAdhesionPDCResponse form = new FormCamposAdhesionPDCResponse();
		listForm.add(form);
		response.getDatos().getFormCampos().add(listForm);

		Mockito.when(poderCompraDAO.adherirPoderCompra(Matchers.any(CrearAdhesionRequestEntity.class)))
				.thenReturn(response);

		Respuesta<FinalizarAdhesionDTO> respBO = new Respuesta<FinalizarAdhesionDTO>();
		respBO.setEstadoRespuesta(EstadoRespuesta.OK);

		Mockito.when(
				respuestaFactory.crearRespuestaOk(Matchers.any(Class.class), Matchers.any(FinalizarAdhesionDTO.class)))
				.thenReturn(respBO);
		Respuesta<FinalizarAdhesionDTO> resp = poderCompraBOImpl.finalizarAdhesion(new FinalizarAdhesionPDC(),
				new Cliente(), TipoBancaEnum.BANCA_PERSONAL);
		Assert.assertNotNull(resp);
		Assert.assertEquals(resp.getEstadoRespuesta(), EstadoRespuesta.OK);
	}

	@Test
	public void finalizarAdhesionTestError() throws DAOException {

		Mockito.when(poderCompraDAO.adherirPoderCompra(Matchers.any(CrearAdhesionRequestEntity.class)))
				.thenThrow(new DAOException());

		Respuesta respBO = new Respuesta();
		respBO.setEstadoRespuesta(EstadoRespuesta.ERROR);

		Mockito.when(respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
				CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS)).thenReturn(respBO);
		Respuesta<FinalizarAdhesionDTO> resp = poderCompraBOImpl.finalizarAdhesion(new FinalizarAdhesionPDC(),
				new Cliente(), TipoBancaEnum.BANCA_PERSONAL);
		Assert.assertNotNull(resp);
		Assert.assertEquals(resp.getEstadoRespuesta(), EstadoRespuesta.ERROR);
	}
}
