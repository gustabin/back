package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.manager;

import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.reporte.entities.TipoArchivoEnum;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comprobantes.bo.ReporteComprobantePDFBO;
import ar.com.santanderrio.obp.servicios.comun.ContadorIntentos;
import ar.com.santanderrio.obp.servicios.comun.ContadorIntentosEnum;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.legal.bo.LegalBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.inversiones.comun.TipoBancaEnum;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.AccionesAlVencimientoOutView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.bo.PoderCompraBO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dto.AdhesionPDCOutDTO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dto.AdhesionPDCRequestDTO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dto.FinalizarAdhesionDTO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dto.FinalizarAdhesionPDC;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.ComprobanteActivarPoderCompra;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.ComprobanteOrdenCompraView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.EstadisticaComprobantePoderCompra;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.FinalizarAdhesionPDCResponse;

@RunWith(MockitoJUnitRunner.class)
public class PoderCompraManagerTest {

	@Mock
	private PoderCompraBO poderCompraBO;

	@Mock
	private LegalBO legalBO;
	
	@Mock
    private MensajeBO mensajeBO;

	@Mock
	private SesionCliente sesionCliente;

	@Mock
	private EstadisticaManager estadisticaManager;

	@InjectMocks
	private PoderCompraManagerImpl poderCompraManagerImpl;

	@Mock
	private RespuestaFactory respuestaFactory;

	@Mock
	private SesionParametros sessionParametros;

	@Mock
	private ReporteComprobantePDFBO reporteBO;

	@Test
	public void simularAdhesionPDCOK() throws DAOException {
		Respuesta<AdhesionPDCOutDTO> respBO = new Respuesta<AdhesionPDCOutDTO>();
		AdhesionPDCOutDTO resp = new AdhesionPDCOutDTO();
		resp.setIdSimCuentaPdc("1234");
		respBO.setRespuesta(resp);
		respBO.setEstadoRespuesta(EstadoRespuesta.OK);
		Mockito.when(sesionCliente.getCliente()).thenReturn(new Cliente());
		Mockito.when(poderCompraBO.simularAdhesion(Matchers.any(AdhesionPDCRequestDTO.class),
				Matchers.any(Cliente.class), Matchers.any(TipoBancaEnum.class))).thenReturn(respBO);

		String msj = "mensaje";
        Mockito.when(legalBO.obtenerLegal(Matchers.anyString())).thenReturn(msj);
		AdhesionPDCRequestDTO request = new AdhesionPDCRequestDTO();

		Respuesta<AdhesionPDCOutDTO> respManager = poderCompraManagerImpl.simularAdhesionPDC(request);
		Assert.assertNotNull(respManager);
		Assert.assertEquals(respManager.getEstadoRespuesta(), EstadoRespuesta.OK);
	}

	@Test
	public void simularAdhesionPDCOKBPriv() throws DAOException {
		Respuesta<AdhesionPDCOutDTO> respBO = new Respuesta<AdhesionPDCOutDTO>();
		AdhesionPDCOutDTO resp = new AdhesionPDCOutDTO();
		resp.setIdSimCuentaPdc("1234");
		respBO.setRespuesta(resp);
		respBO.setEstadoRespuesta(EstadoRespuesta.OK);
		Mockito.when(sesionCliente.getCliente()).thenReturn(new Cliente());
		Mockito.when(poderCompraBO.simularAdhesion(Matchers.any(AdhesionPDCRequestDTO.class),
				Matchers.any(Cliente.class), Matchers.any(TipoBancaEnum.class))).thenReturn(respBO);

		String msj = "mensaje";
		Mockito.when(legalBO.obtenerLegal(Matchers.anyString())).thenReturn(msj);
		AdhesionPDCRequestDTO request = new AdhesionPDCRequestDTO();

		Respuesta<AdhesionPDCOutDTO> respManager = poderCompraManagerImpl.simularAdhesionPDCBPriv(request);
		Assert.assertNotNull(respManager);
		Assert.assertEquals(respManager.getEstadoRespuesta(), EstadoRespuesta.OK);
	}

	@Test
	public void simularAdhesionPDCError() {
		Respuesta<AdhesionPDCOutDTO> respBO = new Respuesta<AdhesionPDCOutDTO>();
		AdhesionPDCOutDTO resp = new AdhesionPDCOutDTO();
		resp.setIdSimCuentaPdc("1234");
		respBO.setRespuesta(resp);
		respBO.setEstadoRespuesta(EstadoRespuesta.ERROR);
		Mockito.when(sesionCliente.getCliente()).thenReturn(new Cliente());
		Mockito.when(poderCompraBO.simularAdhesion(Matchers.any(AdhesionPDCRequestDTO.class),
				Matchers.any(Cliente.class), Matchers.any(TipoBancaEnum.class))).thenReturn(respBO);

		AdhesionPDCRequestDTO request = new AdhesionPDCRequestDTO();

		@SuppressWarnings("rawtypes")
		Respuesta rtaManager = new Respuesta();
		rtaManager.setEstadoRespuesta(EstadoRespuesta.ERROR);
		Mockito.when(respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
				CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS)).thenReturn(rtaManager);

		Respuesta<AdhesionPDCOutDTO> respManager = poderCompraManagerImpl.simularAdhesionPDC(request);
		Assert.assertNotNull(respManager);
		Assert.assertEquals(respManager.getEstadoRespuesta(), EstadoRespuesta.ERROR);
	}

	@Test
	public void simularAdhesionPDCErrorBPriv() {
		Respuesta<AdhesionPDCOutDTO> respBO = new Respuesta<AdhesionPDCOutDTO>();
		AdhesionPDCOutDTO resp = new AdhesionPDCOutDTO();
		resp.setIdSimCuentaPdc("1234");
		respBO.setRespuesta(resp);
		respBO.setEstadoRespuesta(EstadoRespuesta.ERROR);
		Mockito.when(sesionCliente.getCliente()).thenReturn(new Cliente());
		Mockito.when(poderCompraBO.simularAdhesion(Matchers.any(AdhesionPDCRequestDTO.class),
				Matchers.any(Cliente.class), Matchers.any(TipoBancaEnum.class))).thenReturn(respBO);

		AdhesionPDCRequestDTO request = new AdhesionPDCRequestDTO();

		@SuppressWarnings("rawtypes")
		Respuesta rtaManager = new Respuesta();
		rtaManager.setEstadoRespuesta(EstadoRespuesta.ERROR);
		Mockito.when(respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
				CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS)).thenReturn(rtaManager);

		Respuesta<AdhesionPDCOutDTO> respManager = poderCompraManagerImpl.simularAdhesionPDCBPriv(request);
		Assert.assertNotNull(respManager);
		Assert.assertEquals(respManager.getEstadoRespuesta(), EstadoRespuesta.ERROR);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void finalizarAdhesionPDCOK() throws DAOException {
		ContadorIntentos contadorIntentos = new ContadorIntentos();
		contadorIntentos.setIdView(ContadorIntentosEnum.ADHESION_PODER_COMPRA, 2, "");
		when(sessionParametros.getContador()).thenReturn(contadorIntentos);

		when(sessionParametros.getIdSimulacionPDC()).thenReturn("1234");

		Respuesta<FinalizarAdhesionDTO> respBO = new Respuesta<FinalizarAdhesionDTO>();
		respBO.setEstadoRespuesta(EstadoRespuesta.OK);
		FinalizarAdhesionDTO finalizarAdhesionDTO = new FinalizarAdhesionDTO();

		finalizarAdhesionDTO.getMapFormCampos().put("CodigoMoneda", "ARS");
		finalizarAdhesionDTO.getMapFormCampos().put("FechaVigenciaDesde", "18/06/2018");
		finalizarAdhesionDTO.getMapFormCampos().put("Comprobante", "123456");
		finalizarAdhesionDTO.getMapFormCampos().put("FechaSolicitud", "18/06/2018");

		respBO.setRespuesta(finalizarAdhesionDTO);

		Mockito.when(poderCompraBO.finalizarAdhesion(Matchers.any(FinalizarAdhesionPDC.class),
				Matchers.any(Cliente.class), Matchers.any(TipoBancaEnum.class))).thenReturn(respBO);

		Mensaje msj = new Mensaje();
        msj.setMensaje("mensaje");
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(msj);

		Respuesta<FinalizarAdhesionPDCResponse> resp = new Respuesta<FinalizarAdhesionPDCResponse>();
		resp.setEstadoRespuesta(EstadoRespuesta.OK);
		when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class),
				Matchers.any(AccionesAlVencimientoOutView.class))).thenReturn(resp);
		when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class))).thenReturn(resp);
		Respuesta<FinalizarAdhesionPDCResponse> rtaManager = poderCompraManagerImpl
				.finalizarAdhesionPDC(new FinalizarAdhesionPDC());
		Assert.assertNotNull(rtaManager);
		Assert.assertEquals(rtaManager.getEstadoRespuesta(), EstadoRespuesta.OK);

	}

	@SuppressWarnings("unchecked")
	@Test
	public void finalizarAdhesionPDCBPrivOK() throws DAOException {
		ContadorIntentos contadorIntentos = new ContadorIntentos();
		contadorIntentos.setIdView(ContadorIntentosEnum.ADHESION_PODER_COMPRA, 2, "");
		when(sessionParametros.getContador()).thenReturn(contadorIntentos);

		when(sessionParametros.getIdSimulacionPDC()).thenReturn("1234");

		Respuesta<FinalizarAdhesionDTO> respBO = new Respuesta<FinalizarAdhesionDTO>();
		respBO.setEstadoRespuesta(EstadoRespuesta.OK);
		FinalizarAdhesionDTO finalizarAdhesionDTO = new FinalizarAdhesionDTO();

		finalizarAdhesionDTO.getMapFormCampos().put("CodigoMoneda", "ARS");
		finalizarAdhesionDTO.getMapFormCampos().put("FechaVigenciaDesde", "18/06/2018");
		finalizarAdhesionDTO.getMapFormCampos().put("Comprobante", "123456");
		finalizarAdhesionDTO.getMapFormCampos().put("FechaSolicitud", "18/06/2018");

		respBO.setRespuesta(finalizarAdhesionDTO);

		Mockito.when(poderCompraBO.finalizarAdhesion(Matchers.any(FinalizarAdhesionPDC.class),
				Matchers.any(Cliente.class), Matchers.any(TipoBancaEnum.class))).thenReturn(respBO);

		Mensaje msj = new Mensaje();
        msj.setMensaje("mensaje");
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(msj);

		Respuesta<FinalizarAdhesionPDCResponse> resp = new Respuesta<FinalizarAdhesionPDCResponse>();
		resp.setEstadoRespuesta(EstadoRespuesta.OK);
		when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class),
				Matchers.any(AccionesAlVencimientoOutView.class))).thenReturn(resp);

		Respuesta<FinalizarAdhesionPDCResponse> rtaManager = poderCompraManagerImpl
				.finalizarAdhesionPDCBPriv(new FinalizarAdhesionPDC());
		Assert.assertNotNull(rtaManager);
		Assert.assertEquals(rtaManager.getEstadoRespuesta(), EstadoRespuesta.OK);

	}

	@Test
	public void finalizarAdhesionPDCErrorSimulacionPDC() {
		ContadorIntentos contadorIntentos = new ContadorIntentos();
		contadorIntentos.setIdView(ContadorIntentosEnum.ADHESION_PODER_COMPRA, 2, "");
		when(sessionParametros.getContador()).thenReturn(contadorIntentos);

		when(sessionParametros.getIdSimulacionPDC()).thenReturn(null);

		Respuesta resp = new Respuesta();
		resp.setEstadoRespuesta(EstadoRespuesta.ERROR);
		when(respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
				CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS)).thenReturn(resp);

		Respuesta<FinalizarAdhesionPDCResponse> rtaManager = poderCompraManagerImpl
				.finalizarAdhesionPDC(new FinalizarAdhesionPDC());
		Assert.assertNotNull(rtaManager);
		Assert.assertEquals(rtaManager.getEstadoRespuesta(), EstadoRespuesta.ERROR);
	}

	@Test
	public void finalizarAdhesionPDCErrorSimulacionPDCBPriv() {
		ContadorIntentos contadorIntentos = new ContadorIntentos();
		contadorIntentos.setIdView(ContadorIntentosEnum.ADHESION_PODER_COMPRA, 2, "");
		when(sessionParametros.getContador()).thenReturn(contadorIntentos);

		when(sessionParametros.getIdSimulacionPDC()).thenReturn(null);

		Respuesta resp = new Respuesta();
		resp.setEstadoRespuesta(EstadoRespuesta.ERROR);
		when(respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
				CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS)).thenReturn(resp);

		Respuesta<FinalizarAdhesionPDCResponse> rtaManager = poderCompraManagerImpl
				.finalizarAdhesionPDCBPriv(new FinalizarAdhesionPDC());
		Assert.assertNotNull(rtaManager);
		Assert.assertEquals(rtaManager.getEstadoRespuesta(), EstadoRespuesta.ERROR);
	}

	@Test
	public void finalizarAdhesionPDCErrorContadorPDC() {
		when(sessionParametros.getContador()).thenReturn(null);

		Respuesta resp = new Respuesta();
		resp.setEstadoRespuesta(EstadoRespuesta.ERROR);
		when(respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
				CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS)).thenReturn(resp);

		Respuesta<FinalizarAdhesionPDCResponse> rtaManager = poderCompraManagerImpl
				.finalizarAdhesionPDC(new FinalizarAdhesionPDC());
		Assert.assertNotNull(rtaManager);
		Assert.assertEquals(rtaManager.getEstadoRespuesta(), EstadoRespuesta.ERROR);
	}

	@Test
	public void finalizarAdhesionPDCErrorContadorPDCBPriv() {
		when(sessionParametros.getContador()).thenReturn(null);

		Respuesta resp = new Respuesta();
		resp.setEstadoRespuesta(EstadoRespuesta.ERROR);
		when(respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
				CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS)).thenReturn(resp);

		Respuesta<FinalizarAdhesionPDCResponse> rtaManager = poderCompraManagerImpl
				.finalizarAdhesionPDCBPriv(new FinalizarAdhesionPDC());
		Assert.assertNotNull(rtaManager);
		Assert.assertEquals(rtaManager.getEstadoRespuesta(), EstadoRespuesta.ERROR);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void finalizarAdhesionPDCBErrorReintentos() throws DAOException {
		ContadorIntentos contadorIntentos = new ContadorIntentos();
		contadorIntentos.setIdView(ContadorIntentosEnum.ADHESION_PODER_COMPRA, 2, "");
		when(sessionParametros.getContador()).thenReturn(contadorIntentos);

		when(sessionParametros.getIdSimulacionPDC()).thenReturn("1234");

		Respuesta<FinalizarAdhesionDTO> respBO = new Respuesta<FinalizarAdhesionDTO>();
		respBO.setEstadoRespuesta(EstadoRespuesta.ERROR);
		FinalizarAdhesionDTO finalizarAdhesionDTO = new FinalizarAdhesionDTO();

		finalizarAdhesionDTO.getMapFormCampos().put("CodigoMoneda", "ARS");
		finalizarAdhesionDTO.getMapFormCampos().put("FechaVigenciaDesde", "18/06/2018");
		finalizarAdhesionDTO.getMapFormCampos().put("Comprobante", "123456");
		finalizarAdhesionDTO.getMapFormCampos().put("FechaSolicitud", "18/06/2018");

		respBO.setRespuesta(finalizarAdhesionDTO);

		Mockito.when(poderCompraBO.finalizarAdhesion(Matchers.any(FinalizarAdhesionPDC.class),
				Matchers.any(Cliente.class), Matchers.any(TipoBancaEnum.class))).thenReturn(respBO);

		String msj = "mensaje";
        Mockito.when(legalBO.obtenerLegal(Matchers.anyString())).thenReturn(msj);

		Respuesta<FinalizarAdhesionPDCResponse> respOK = new Respuesta<FinalizarAdhesionPDCResponse>();
		respOK.setEstadoRespuesta(EstadoRespuesta.OK);
		when(respuestaFactory.crearRespuestaOk(FinalizarAdhesionPDCResponse.class)).thenReturn(respOK);
		Respuesta resp = new Respuesta();
		resp.setEstadoRespuesta(EstadoRespuesta.ERROR);
		when(respuestaFactory.crearRespuestaError("", TipoError.ERROR_CON_REINTENTOS,
				CodigoMensajeConstantes.FINALIZAR_ADHESION_PDC_ERROR)).thenReturn(resp);

		Respuesta<FinalizarAdhesionPDCResponse> rtaManager = poderCompraManagerImpl
				.finalizarAdhesionPDC(new FinalizarAdhesionPDC());
		Assert.assertNotNull(rtaManager);
		Assert.assertEquals(rtaManager.getEstadoRespuesta(), EstadoRespuesta.ERROR);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void finalizarAdhesionPDCBPrivErrorReintentos() throws DAOException {
		ContadorIntentos contadorIntentos = new ContadorIntentos();
		contadorIntentos.setIdView(ContadorIntentosEnum.ADHESION_PODER_COMPRA, 2, "");
		when(sessionParametros.getContador()).thenReturn(contadorIntentos);

		when(sessionParametros.getIdSimulacionPDC()).thenReturn("1234");

		Respuesta<FinalizarAdhesionDTO> respBO = new Respuesta<FinalizarAdhesionDTO>();
		respBO.setEstadoRespuesta(EstadoRespuesta.ERROR);
		FinalizarAdhesionDTO finalizarAdhesionDTO = new FinalizarAdhesionDTO();

		finalizarAdhesionDTO.getMapFormCampos().put("CodigoMoneda", "ARS");
		finalizarAdhesionDTO.getMapFormCampos().put("FechaVigenciaDesde", "18/06/2018");
		finalizarAdhesionDTO.getMapFormCampos().put("Comprobante", "123456");
		finalizarAdhesionDTO.getMapFormCampos().put("FechaSolicitud", "18/06/2018");

		respBO.setRespuesta(finalizarAdhesionDTO);

		Mockito.when(poderCompraBO.finalizarAdhesion(Matchers.any(FinalizarAdhesionPDC.class),
				Matchers.any(Cliente.class), Matchers.any(TipoBancaEnum.class))).thenReturn(respBO);

		String msj = "mensaje";
        Mockito.when(legalBO.obtenerLegal(Matchers.anyString())).thenReturn(msj);

		Respuesta resp = new Respuesta();
		resp.setEstadoRespuesta(EstadoRespuesta.ERROR);
		when(respuestaFactory.crearRespuestaError("", TipoError.ERROR_CON_REINTENTOS,
				CodigoMensajeConstantes.FINALIZAR_ADHESION_PDC_ERROR)).thenReturn(resp);

		Respuesta<FinalizarAdhesionPDCResponse> rtaManager = poderCompraManagerImpl
				.finalizarAdhesionPDCBPriv(new FinalizarAdhesionPDC());
		Assert.assertNotNull(rtaManager);
		Assert.assertEquals(rtaManager.getEstadoRespuesta(), EstadoRespuesta.ERROR);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void finalizarAdhesionPDCErrorSinReintentos() throws DAOException {
		ContadorIntentos contadorIntentos = new ContadorIntentos();
		contadorIntentos.setIdView(ContadorIntentosEnum.ADHESION_PODER_COMPRA, 0, "");
		when(sessionParametros.getContador()).thenReturn(contadorIntentos);

		when(sessionParametros.getIdSimulacionPDC()).thenReturn("1234");

		Respuesta<FinalizarAdhesionDTO> respBO = new Respuesta<FinalizarAdhesionDTO>();
		respBO.setEstadoRespuesta(EstadoRespuesta.ERROR);
		FinalizarAdhesionDTO finalizarAdhesionDTO = new FinalizarAdhesionDTO();

		finalizarAdhesionDTO.getMapFormCampos().put("CodigoMoneda", "ARS");
		finalizarAdhesionDTO.getMapFormCampos().put("FechaVigenciaDesde", "18/06/2018");
		finalizarAdhesionDTO.getMapFormCampos().put("Comprobante", "123456");
		finalizarAdhesionDTO.getMapFormCampos().put("FechaSolicitud", "18/06/2018");

		respBO.setRespuesta(finalizarAdhesionDTO);

		Mockito.when(poderCompraBO.finalizarAdhesion(Matchers.any(FinalizarAdhesionPDC.class),
				Matchers.any(Cliente.class), Matchers.any(TipoBancaEnum.class))).thenReturn(respBO);

		String msj = "mensaje";
        Mockito.when(legalBO.obtenerLegal(Matchers.anyString())).thenReturn(msj);

		Respuesta resp = new Respuesta();
		resp.setEstadoRespuesta(EstadoRespuesta.ERROR);
		when(respuestaFactory.crearRespuestaError("", TipoError.ERROR_REINTENTOS_AGOTADOS,
				CodigoMensajeConstantes.FINALIZAR_ADHESION_PDC_ERROR)).thenReturn(resp);
		
		Respuesta respOK = new Respuesta();
		respOK.setEstadoRespuesta(EstadoRespuesta.OK);
		when(respuestaFactory.crearRespuestaOk(FinalizarAdhesionPDCResponse.class)).thenReturn(respOK);

		Respuesta<FinalizarAdhesionPDCResponse> rtaManager = poderCompraManagerImpl
				.finalizarAdhesionPDC(new FinalizarAdhesionPDC());
		Assert.assertNotNull(rtaManager);
		Assert.assertEquals(rtaManager.getEstadoRespuesta(), EstadoRespuesta.ERROR);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void finalizarAdhesionPDCBPrivErrorSinReintentos() throws DAOException {
		ContadorIntentos contadorIntentos = new ContadorIntentos();
		contadorIntentos.setIdView(ContadorIntentosEnum.ADHESION_PODER_COMPRA, 0, "");
		when(sessionParametros.getContador()).thenReturn(contadorIntentos);

		when(sessionParametros.getIdSimulacionPDC()).thenReturn("1234");

		Respuesta<FinalizarAdhesionDTO> respBO = new Respuesta<FinalizarAdhesionDTO>();
		respBO.setEstadoRespuesta(EstadoRespuesta.ERROR);
		FinalizarAdhesionDTO finalizarAdhesionDTO = new FinalizarAdhesionDTO();

		finalizarAdhesionDTO.getMapFormCampos().put("CodigoMoneda", "ARS");
		finalizarAdhesionDTO.getMapFormCampos().put("FechaVigenciaDesde", "18/06/2018");
		finalizarAdhesionDTO.getMapFormCampos().put("Comprobante", "123456");
		finalizarAdhesionDTO.getMapFormCampos().put("FechaSolicitud", "18/06/2018");

		respBO.setRespuesta(finalizarAdhesionDTO);

		Mockito.when(poderCompraBO.finalizarAdhesion(Matchers.any(FinalizarAdhesionPDC.class),
				Matchers.any(Cliente.class), Matchers.any(TipoBancaEnum.class))).thenReturn(respBO);

		String msj = "mensaje";
        Mockito.when(legalBO.obtenerLegal(Matchers.anyString())).thenReturn(msj);

		Respuesta resp = new Respuesta();
		resp.setEstadoRespuesta(EstadoRespuesta.ERROR);
		when(respuestaFactory.crearRespuestaError("", TipoError.ERROR_REINTENTOS_AGOTADOS,
				CodigoMensajeConstantes.FINALIZAR_ADHESION_PDC_ERROR)).thenReturn(resp);

		Respuesta<FinalizarAdhesionPDCResponse> rtaManager = poderCompraManagerImpl
				.finalizarAdhesionPDCBPriv(new FinalizarAdhesionPDC());
		Assert.assertNotNull(rtaManager);
		Assert.assertEquals(rtaManager.getEstadoRespuesta(), EstadoRespuesta.ERROR);
	}

	@Test
	public void descargarPDFOkTest() {
		Respuesta<Reporte> reporte = new Respuesta<Reporte>();
		Reporte pdf = new Reporte();
		pdf.setTipoArchivo(TipoArchivoEnum.PDF);

		reporte.setEstadoRespuesta(EstadoRespuesta.OK);
		reporte.setRespuesta(pdf);

		pdf.setNombre("pdfTest");

		Mockito.when(reporteBO.obtenerComprobantePDF(Matchers.any(ComprobanteActivarPoderCompra.class))).thenReturn(reporte);
		when(sessionParametros.getLegalAdhesionPDC()).thenReturn("legal");
		
		Respuesta<ReporteView> resp = new Respuesta<ReporteView>();
		resp.setEstadoRespuesta(EstadoRespuesta.OK);
		Mockito.when(respuestaFactory.crearRespuestaOk(Matchers.any(ReporteView.class))).thenReturn(resp);
		
		Respuesta<ReporteView> res = poderCompraManagerImpl
				.descargarAceptarPoderCompra(new ComprobanteActivarPoderCompra(), TipoBancaEnum.BANCA_PERSONAL);
		Assert.assertNotNull(res);
		Assert.assertEquals(EstadoRespuesta.OK, res.getEstadoRespuesta());
	}
	
	@Test
	public void descargarPDFOkTestBPriv() {
		Respuesta<Reporte> reporte = new Respuesta<Reporte>();
		Reporte pdf = new Reporte();
		pdf.setTipoArchivo(TipoArchivoEnum.PDF);

		reporte.setEstadoRespuesta(EstadoRespuesta.OK);
		reporte.setRespuesta(pdf);

		pdf.setNombre("pdfTest");

		Mockito.when(reporteBO.obtenerComprobantePDF(Matchers.any(ComprobanteActivarPoderCompra.class))).thenReturn(reporte);
		when(sessionParametros.getLegalAdhesionPDC()).thenReturn("legal");
		
		Respuesta<ReporteView> resp = new Respuesta<ReporteView>();
		resp.setEstadoRespuesta(EstadoRespuesta.OK);
		Mockito.when(respuestaFactory.crearRespuestaOk(Matchers.any(ReporteView.class))).thenReturn(resp);
		
		Respuesta<ReporteView> res = poderCompraManagerImpl
				.descargarAceptarPoderCompra(new ComprobanteActivarPoderCompra(), TipoBancaEnum.BANCA_PRIVADA);
		Assert.assertNotNull(res);
		Assert.assertEquals(EstadoRespuesta.OK, res.getEstadoRespuesta());
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void verComprobanteActivarPDCOk() {
			
		boolean respuestaEstadistica = true;
		when(estadisticaManager.add(Matchers.any(String.class), Matchers.any(String.class)))
				.thenReturn(respuestaEstadistica);	
		
		Mensaje msj = new Mensaje();
		msj.setMensaje("mensaje");
		Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(msj);
		
		Respuesta<ComprobanteOrdenCompraView> rtaFactory = new Respuesta<ComprobanteOrdenCompraView>();
		rtaFactory.setEstadoRespuesta(EstadoRespuesta.OK);
		when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class), Matchers.any(ComprobanteOrdenCompraView.class))).thenReturn(rtaFactory);
		EstadisticaComprobantePoderCompra estadistica = new EstadisticaComprobantePoderCompra();
		estadistica.setLegal(true);
		Respuesta<ComprobanteOrdenCompraView> respuesta = poderCompraManagerImpl.verComprobanteActivarPoderComprar(estadistica);
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
	}

}
