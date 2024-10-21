package ar.com.santanderrio.obp.servicios.titulos.operaciones.manager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.titulos.operaciones.bo.TitulosOperacionesBO;
import ar.com.santanderrio.obp.servicios.titulos.operaciones.dto.OperacionTitulosDTO;
import ar.com.santanderrio.obp.servicios.titulos.operaciones.manager.impl.TitulosOperacionesManagerImpl;
import ar.com.santanderrio.obp.servicios.titulos.operaciones.view.DetalleOperacionCompraVentaView;
import ar.com.santanderrio.obp.servicios.titulos.operaciones.view.DetalleOperacionLicitacionView;
import ar.com.santanderrio.obp.servicios.titulos.operaciones.view.OperacionTitulosView;
import ar.com.santanderrio.obp.servicios.titulos.operaciones.view.ParametrosOperacionesView;

@RunWith(MockitoJUnitRunner.class)
public class TitulosOperacionesManagerObtenerDetalleOperacionTest {

	@Mock
	SesionCliente sesionCliente;

	@Mock
	TitulosOperacionesBO titulosOperacionesBO;

	@Mock
	EstadisticaManager estadisticaManager;

	@Spy
	RespuestaFactory respuestaFactory;

	@InjectMocks
	TitulosOperacionesManager titulosOperacionesManager = new TitulosOperacionesManagerImpl();

	@Mock
	SesionParametros sesionParametros;

	@Test
	public void obtenerDetalleOperacionCompraTest() {

		Cliente cliente = new Cliente();
		cliente.setNup("123456");
		Respuesta<List<OperacionTitulosDTO>> respuestaListaOperacionesTitulosDTO = new Respuesta<List<OperacionTitulosDTO>>();
		respuestaListaOperacionesTitulosDTO.setEstadoRespuesta(EstadoRespuesta.OK);
		OperacionTitulosDTO operacionTituloDTO = new OperacionTitulosDTO();
		operacionTituloDTO.setEstado("C");
		operacionTituloDTO.setMoneda("ARS");
		operacionTituloDTO.setPlazoLiquidacion(48);
		operacionTituloDTO.setMercadoDescripcion("BYMA");
		operacionTituloDTO.setPrecioLimite(1D);
		operacionTituloDTO.setPrecioReferencia("2");
		operacionTituloDTO.setImporte(2.3);
		operacionTituloDTO.setCuentaTitulos("4116279");
		operacionTituloDTO.setSucursalCuentaDestino("123");
		operacionTituloDTO.setCuentaDestino("1234556");
		operacionTituloDTO.setTipoCuentaDestino("00");
		operacionTituloDTO.setComisiones(1D);
		operacionTituloDTO.setImpuestos(0.23);
		operacionTituloDTO.setCanalIngreso("79");
		operacionTituloDTO.setCantidadNominales(20.0);
		operacionTituloDTO.setDescripcion("accion de telecom 1");
		operacionTituloDTO.setFecha(new Date("2018/03/31"));
		operacionTituloDTO.setNumeroOperacion(117631L);
		operacionTituloDTO.setTipoOperacion("C");
		operacionTituloDTO.setTipo("Acciones");
		operacionTituloDTO.setPrecio(20.0);
		operacionTituloDTO.setImporte(25.12);
		operacionTituloDTO.setDerechos(20.0);

		List<OperacionTitulosDTO> listaOperaciones = new ArrayList<OperacionTitulosDTO>();
		listaOperaciones.add(operacionTituloDTO);
		respuestaListaOperacionesTitulosDTO.setRespuesta(listaOperaciones);

		ParametrosOperacionesView parametrosOperacionesView = new ParametrosOperacionesView();
		parametrosOperacionesView.setNumeroCuenta("123-123456/7");
		parametrosOperacionesView.setTipoOperacion("compra");
		parametrosOperacionesView.setNumero("117631");

		when(sesionCliente.getCliente()).thenReturn(cliente);
		when(titulosOperacionesBO.obtenerOperacionesCompraVenta(Matchers.any(ParametrosOperacionesView.class),
				Matchers.any(Cliente.class))).thenReturn(respuestaListaOperacionesTitulosDTO);

		Respuesta<OperacionTitulosView> respuesta = titulosOperacionesManager
				.obtenerDetalleOperacion(parametrosOperacionesView);
		DetalleOperacionCompraVentaView detalle = (DetalleOperacionCompraVentaView) respuesta.getRespuesta();

		assertNotNull(respuesta);
		assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
		assertEquals(detalle.getDescripcion(), "accion de telecom 1");
		assertEquals(detalle.getFecha(), "31/03/2018");
		assertEquals(detalle.getNumeroOperacion(), "117631");
		assertEquals(detalle.getAccion(), "Compra");
		assertEquals(detalle.getEstado(), "Cerrado");
		assertEquals(detalle.getTipo(), "Acciones");
		assertEquals(detalle.getMoneda(), "Pesos");
		assertEquals(detalle.getCantidadNominales(), "20,00");
		assertEquals(detalle.getPlazoDeLiquidacion(), "48 hrs");
		assertEquals(detalle.getMercado(), "BYMA");
		assertEquals(detalle.getPrecio(), "$ 20,00");
		assertEquals(detalle.getPrecioLimite(), "$ 1,00");
		assertEquals(detalle.getPrecioReferencia(), "$ 2,00");
		assertEquals(detalle.getImporte(), "$ 25,12");
		assertEquals(detalle.getCuentaTitulos(), "411627/9");
		assertEquals(detalle.getCuentaDestino(), "123-123455/6");
		assertEquals(detalle.getTipoCuentaDestino(), "Cuenta corriente");
		assertEquals(detalle.getComisiones(), "$ 1,00");
		assertEquals(detalle.getImpuestos(), "$ 0,23");
		assertEquals(detalle.getCanalIngreso(), "Online Banking BP");

	}

	@Test
	public void obtenerDetalleOperacionLicitacionTest() throws ParseException {

		Cliente cliente = new Cliente();
		cliente.setNup("123456");
		Respuesta<List<OperacionTitulosDTO>> respuestaListaOperacionesTitulosDTO = new Respuesta<List<OperacionTitulosDTO>>();
		respuestaListaOperacionesTitulosDTO.setEstadoRespuesta(EstadoRespuesta.OK);
		OperacionTitulosDTO operacionTituloDTO = new OperacionTitulosDTO();

		operacionTituloDTO.setFechaAdjudicacion("13/12/2012");
		operacionTituloDTO.setFechaCierre("15/03/2018 20:00");
		operacionTituloDTO.setFechaDebito("20/04/2018");
		operacionTituloDTO.setFechaLiquidacion("14/12/2018");
		operacionTituloDTO.setFecha(new SimpleDateFormat("dd/MM/yyyy").parse("20/03/2018"));
		operacionTituloDTO.setNumeroOperacion(117631L);
		operacionTituloDTO.setDescripcion("Descripción");
		operacionTituloDTO.setEstado("Estado");
		operacionTituloDTO.setTipo("Tipo");
		operacionTituloDTO.setMoneda("ARS");
		operacionTituloDTO.setMonedaEspecie("Pesos");
		operacionTituloDTO.setCantidad("20");
		operacionTituloDTO.setTramo("Competitivo");
		operacionTituloDTO.setTipoPrecio("Tasa");
		operacionTituloDTO.setPrecio(20.556D);
		operacionTituloDTO.setPrecioAdjudicado(43.66D);
		operacionTituloDTO.setImporteDebitar("$ 20,55");
		operacionTituloDTO.setImpuestos(1232.5678D);
		operacionTituloDTO.setComisiones(2.34D);
		operacionTituloDTO.setCuentaTitulos("123456/7");
		operacionTituloDTO.setCuentaDestino("123-123456/7");
		operacionTituloDTO.setTipoCuentaDestino("Cuenta Única");
		operacionTituloDTO.setCantidadNominales(24D);
		List<OperacionTitulosDTO> listaOperaciones = new ArrayList<OperacionTitulosDTO>();
		listaOperaciones.add(operacionTituloDTO);
		respuestaListaOperacionesTitulosDTO.setRespuesta(listaOperaciones);

		ParametrosOperacionesView parametrosOperacionesView = new ParametrosOperacionesView();
		parametrosOperacionesView.setNumeroCuenta("123-123456/7");
		parametrosOperacionesView.setTipoOperacion("licitaciones");
		parametrosOperacionesView.setNumero("117631");

		when(sesionCliente.getCliente()).thenReturn(cliente);
		when(titulosOperacionesBO.obtenerOperacionesLicitacion(Matchers.any(ParametrosOperacionesView.class),
				Matchers.any(Cliente.class))).thenReturn(respuestaListaOperacionesTitulosDTO);

		Respuesta<OperacionTitulosView> respuesta = titulosOperacionesManager
				.obtenerDetalleOperacion(parametrosOperacionesView);
		DetalleOperacionLicitacionView detalle = (DetalleOperacionLicitacionView) respuesta.getRespuesta();

		assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
		assertEquals(detalle.getCantidadAdjudicada(), "24,00");
		assertEquals(detalle.getCuentaDebito(), "123-123456/7");
		assertEquals(detalle.getTipoCuentaDebito(), "Cuenta Única");
		assertEquals(detalle.getCuentaTitulos(), "123456/7");
		assertEquals(detalle.getComisiones(), "$ 2,34");
		assertEquals(detalle.getImpuestos(), "$ 1.232,57");
		assertEquals(detalle.getImporteDebitar(), "$ 20,55");
		assertEquals(detalle.getPrecio(), "43,66 %");
		assertEquals(detalle.getPrecioAdjudicadoLabel(), "Tasa adjudicada");
		assertEquals(detalle.getPrecioAdjudicado(), "20,556 %");
		assertEquals(detalle.getPrecioLabel(), "Tasa");
		assertEquals(detalle.getTramo(), "Competitivo");
		assertEquals(detalle.getCantidadNominales(), "20");
		assertEquals(detalle.getMoneda(), "Pesos");
		assertEquals(detalle.getDescripcion(), "Descripción");
		assertEquals(detalle.getNumero(), "117631");
		assertEquals(detalle.getFecha(), "20/03/2018");
		assertEquals(detalle.getFechaDebito(), "20/04/2018");
		assertEquals(detalle.getFechaHoraCierre(), "15/03/2018 20:00");
		assertEquals(detalle.getFechaAdjudicacion(), "13/12/2012");
		assertEquals(detalle.getFechaLiquidacionTitulo(), "14/12/2018");
		assertEquals(detalle.getTipo(), "Tipo");
		assertEquals(detalle.getEstado(), "Estado");
	}
}
