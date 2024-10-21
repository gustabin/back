package ar.com.santanderrio.obp.servicios.tenencias.manager;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.legal.bo.LegalBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.tenencias.bo.TenenciasBO;
import ar.com.santanderrio.obp.servicios.tenencias.dto.CuentaDTO;
import ar.com.santanderrio.obp.servicios.tenencias.dto.CustodiaDTO;
import ar.com.santanderrio.obp.servicios.tenencias.dto.FirmanteDTO;
import ar.com.santanderrio.obp.servicios.tenencias.dto.FirmantePrestamoDTO;
import ar.com.santanderrio.obp.servicios.tenencias.dto.FirmantesDTO;
import ar.com.santanderrio.obp.servicios.tenencias.dto.FondoDTO;
import ar.com.santanderrio.obp.servicios.tenencias.dto.FondoPendienteDTO;
import ar.com.santanderrio.obp.servicios.tenencias.dto.ImpuestoDTO;
import ar.com.santanderrio.obp.servicios.tenencias.dto.ImpuestoMonedaExtranjeraDTO;
import ar.com.santanderrio.obp.servicios.tenencias.dto.PlazoFijoDTO;
import ar.com.santanderrio.obp.servicios.tenencias.dto.PrestamoDTO;
import ar.com.santanderrio.obp.servicios.tenencias.dto.RendimientoFondoDTO;
import ar.com.santanderrio.obp.servicios.tenencias.dto.TarjetasDTO;
import ar.com.santanderrio.obp.servicios.tenencias.dto.TenenciasDTO;
import ar.com.santanderrio.obp.servicios.tenencias.dto.TenenciasInDTO;
import ar.com.santanderrio.obp.servicios.tenencias.helpers.TenenciasEstadisticaHelper;
import ar.com.santanderrio.obp.servicios.tenencias.view.CuentaView;
import ar.com.santanderrio.obp.servicios.tenencias.view.ImpuestoMonedaExtranjeraResumenView;
import ar.com.santanderrio.obp.servicios.tenencias.view.ImpuestoView;
import ar.com.santanderrio.obp.servicios.tenencias.view.InversionesView;
import ar.com.santanderrio.obp.servicios.tenencias.view.PrestamoView;
import ar.com.santanderrio.obp.servicios.tenencias.view.RendimientoFondoView;
import ar.com.santanderrio.obp.servicios.tenencias.view.TarjetaView;
import ar.com.santanderrio.obp.servicios.tenencias.view.TenenciasInView;
import ar.com.santanderrio.obp.servicios.tenencias.view.TenenciasView;

@RunWith(MockitoJUnitRunner.class)
public class TenenciasManagerTest {

	/** The Tenencias Manager. */
	@InjectMocks
	private TenenciasManagerImpl tenenciasManager = new TenenciasManagerImpl();

	/** The Tenencias BO. */
	@Mock
	private TenenciasBO tenenciasBO;

	/** The Tenencias BO. */
	@Mock
	private TenenciasEstadisticaHelper estHelper;

	/** The respuesta factory. */
	@Spy
	private RespuestaFactory respuestaFactory = new RespuestaFactory();

	/** The SesionCliente */
	@Mock
	private SesionCliente sesionCliente;
	
	/**  The LegalBO  */
	@Mock
	private LegalBO legalBo;

	@Test
	public void testConsultarTenencias() throws IllegalAccessException {
		TenenciasInView viewRequest = new TenenciasInView();
		viewRequest.setAnio("2015");
		viewRequest.setNup("123456");

		TenenciasInDTO inDTO = new TenenciasInDTO();
		inDTO.setAnioDesde("2015");
		inDTO.setAnioHasta("2015");
		inDTO.setNup("123456");

		String anio1 = "2017";
		String anio2 = "2016";
		String anio3 = "2015";
		String anio4 = "2014";
		String anio5 = "2013";
		String anio6 = "2012";
		String anio7 = "2011";
		String anio8 = "2010";
		String anio9 = "2009";
		String anio10 = "2008";
		String anio11 = "2007";
		List<String> stringAnios = Arrays.asList(anio11, anio10, anio9, anio8, anio7, anio6, anio5, anio4, anio3, anio2, anio1);

		TenenciasDTO tenenciasDTO = new TenenciasDTO();
		tenenciasDTO.setCotiDolar(new BigDecimal("10.00"));
		tenenciasDTO.setCuentasDTO(new ArrayList<CuentaDTO>());
		tenenciasDTO.setCustodiaDTO(new ArrayList<CustodiaDTO>());
		tenenciasDTO.setFondosDTO(new ArrayList<FondoDTO>());
		tenenciasDTO.setFondosPendientesDTO(new ArrayList<FondoPendienteDTO>());
		tenenciasDTO.setImpuestoMonedaExtranjeraDTO(new ArrayList<ImpuestoMonedaExtranjeraDTO>());
		tenenciasDTO.setImpuestosDTO(new ArrayList<ImpuestoDTO>());
		tenenciasDTO.setPlazoFijoDTO(new ArrayList<PlazoFijoDTO>());
		tenenciasDTO.setPrestamosDTO(new ArrayList<PrestamoDTO>());
		tenenciasDTO.setRendimientoFondosDTO(new ArrayList<RendimientoFondoDTO>());
		tenenciasDTO.setTarjetasDTO(new ArrayList<TarjetasDTO>());
		Respuesta<TenenciasDTO> respuestaTenenciasDTO = new Respuesta<TenenciasDTO>();
		respuestaTenenciasDTO.setEstadoRespuesta(EstadoRespuesta.OK);
		respuestaTenenciasDTO.setRespuesta(tenenciasDTO);

		FirmantesDTO firmantesDTO = new FirmantesDTO();
		firmantesDTO.setCuentas(new ArrayList<FirmanteDTO>());
		firmantesDTO.setCustodias(new ArrayList<FirmanteDTO>());
		firmantesDTO.setFondos(new ArrayList<FirmanteDTO>());
		firmantesDTO.setPlazoFijo(new ArrayList<FirmanteDTO>());
		firmantesDTO.setPrestamos(new ArrayList<FirmantePrestamoDTO>());
		Respuesta<FirmantesDTO> respuestaFirmantesDTO = new Respuesta<FirmantesDTO>();
		respuestaFirmantesDTO.setEstadoRespuesta(EstadoRespuesta.OK);
		respuestaFirmantesDTO.setRespuesta(firmantesDTO);

		Cliente cliente = new Cliente();
		cliente.setNup("123456");
		
		String legal = legalBo.obtenerLegalesPorCodigo(CodigoMensajeConstantes.LEGAL_GENERAL_PRINCIPAL_INVERSIONES);

		when(sesionCliente.getCliente()).thenReturn(cliente);
		when(legalBo.obtenerLegalesPorCodigo(CodigoMensajeConstantes.LEGAL_GENERAL_PRINCIPAL_INVERSIONES)).thenReturn(legal);
		when(tenenciasBO.consultarFirmantes(inDTO)).thenReturn(respuestaFirmantesDTO);
		when(tenenciasBO.consultarTenencias(inDTO)).thenReturn(respuestaTenenciasDTO);
		FieldUtils.writeField(tenenciasManager, "aniosTenencias", stringAnios, true);
		Respuesta<TenenciasView> respuesta = tenenciasManager.consultarTenencias(viewRequest);

		TenenciasView view = new TenenciasView();
		view.setCotiDolar("10,00");
		view.setCuentas(new ArrayList<CuentaView>());
		view.setCuentasME(new ArrayList<CuentaView>());
		view.setImpuestoMonedaExtranjera(new ArrayList<ImpuestoMonedaExtranjeraResumenView>());
		view.setImpuestos(new ArrayList<ImpuestoView>());
		

		view.setInversiones(new InversionesView());
		view.setPrestamos(new ArrayList<PrestamoView>());
		view.setRendimientoFondos(new ArrayList<RendimientoFondoView>());
		view.setSaldoTotalDolares("0,00");
		view.setSaldoTotalPesos("0,00");
		view.setTarjetas(new ArrayList<TarjetaView>());
		Respuesta<TenenciasView> expected = new Respuesta<TenenciasView>();
		expected.setEstadoRespuesta(EstadoRespuesta.OK);
		expected.setRespuesta(view);

		assertEquals(respuesta, expected);
	}
}
