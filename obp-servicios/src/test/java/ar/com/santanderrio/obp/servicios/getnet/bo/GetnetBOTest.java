package ar.com.santanderrio.obp.servicios.getnet.bo;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.io.IOException;
import java.sql.SQLException;

import ar.com.santanderrio.obp.servicios.getnet.exception.GetnetEmailValidationException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.exceptions.ISBANRuntimeException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ContadorIntentos;
import ar.com.santanderrio.obp.servicios.comun.dao.entities.DatosSolicitanteEntity;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.ConsultaDetalleCuentaOutEntity;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.getnet.bo.impl.GetnetBOImpl;
import ar.com.santanderrio.obp.servicios.getnet.dao.GetnetDAO;
import ar.com.santanderrio.obp.servicios.getnet.dto.GetnetAdhesionDTO;
import ar.com.santanderrio.obp.servicios.getnet.entities.GetnetInEntity;
import ar.com.santanderrio.obp.servicios.getnet.exception.GetnetValidationException;
import ar.com.santanderrio.obp.servicios.getnet.view.GetnetAdhesionInView;


@RunWith(MockitoJUnitRunner.class)
public class GetnetBOTest {

	@InjectMocks
	private GetnetBO getnetBO = new GetnetBOImpl();
	
	@Mock
	private SesionCliente sesionCliente;
	
	@Mock
	private SesionParametros sesionParametros;
	
	@Mock
	private ContadorIntentos contadorIntentos;
	
	@Mock
	private GetnetDAO getNetDAO;
	
	@Mock
    private CuentaBO cuentaBO;
	
	@Mock
    private ConsultaDetalleCuentaOutEntity detalleCuenta;
	
	@Mock
	private RespuestaFactory respuestaFactory;
	
	
	@Before
	public void init() throws SQLException, DAOException, IOException, ISBANRuntimeException, GetnetValidationException, GetnetEmailValidationException {
		Cliente cliente = new Cliente();
		cliente.setNroDocCnsDocXNup("33444555");
		cliente.setNup("123455");
		cliente.setTipoDocumento("DNI");
		cliente.setDni("33444555");
		cliente.setFechaNacimiento("19870101");
		cliente.setNombre("Juan");
		cliente.setApellido1("Perez");
		Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
		Mockito.when(sesionParametros.getContadorAdhesionGetnet()).thenReturn(contadorIntentos);
		Mockito.when(contadorIntentos.permiteReintentar()).thenReturn(true);
		Mockito.when(sesionParametros.getLegalTycGetnet()).thenReturn("Legal Term");
		Mockito.when(sesionParametros.getCNSINFIMPO_categorizacionIibb()).thenReturn("");
		Mockito.when(sesionParametros.getCNSINFIMPO_categorizacionIva()).thenReturn("");
		Mockito.when(sesionParametros.getCNSINFIMPO_numeroIibb()).thenReturn("0");
		Mockito.when(sesionParametros.getACTADHECNL_isExpuestoPoliticamente()).thenReturn(Boolean.TRUE);
		
		DatosSolicitanteEntity solicitante = new DatosSolicitanteEntity();
		solicitante.setCodigoSujeto("");
		solicitante.setSexo("H");
		solicitante.setNacionalidad("ARG");
		solicitante.setCalle("");
		solicitante.setCalleNro("");
		solicitante.setPiso("");
		solicitante.setDepto("");
		solicitante.setCp("");
		solicitante.setLocalidad("");
		solicitante.setProvincia("");
		solicitante.setCodigoPais("");
		Mockito.when(sesionParametros.getDatosSolicitante()).thenReturn(solicitante);
		
		Mockito.when(sesionParametros.getCNSINFIMPO_numeroIibb()).thenReturn("0");

		Mockito.when(cuentaBO.obtenerDetalleCuenta(Matchers.any(Cuenta.class))).thenReturn(detalleCuenta);
		Mockito.when(detalleCuenta.getFechaApertura()).thenReturn("2011-08-23");
		
		Mockito.doThrow(new GetnetValidationException())
				.when(getNetDAO).postPersons(Matchers.any(GetnetInEntity.class), Matchers.any(String.class));		

	}	
	
	@Test
	public void confirmarAdhesion(){
		GetnetAdhesionInView view;
		GetnetAdhesionDTO dto;
		view = new GetnetAdhesionInView();
		dto = new GetnetAdhesionDTO(view);
		dto.setEmail("user@mail.com.ar");
		dto.setCelular("3855905585");
		dto.setNombreFantasia("Empresa S.A");
		dto.setCbu("0720151288000039591018");
		dto.setActividad("Varios");
		dto.setIngreso(1234);
		
		getnetBO.confirmarAdhesion(dto);
		verify(respuestaFactory, times(1)).crearRespuestaError("", TipoError.ERROR_GETNET_VALIDATION,CodigoMensajeConstantes.GETNET_VALIDATION_ERROR);

	}
	

}
