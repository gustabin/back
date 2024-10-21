package ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.bo;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.ApplicationContext;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.context.ContextHolder;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.SessionUsuarioData;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.Segmento;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dao.OrdenesTitulosDAO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.CompraVtaTitulosResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ComptaVtaTitulosRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ConsultaSuscripcionSaldoPDCRequest;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ConsultaSuscripcionSaldoPDCResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.DatosCompraVtaTitulosResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.DatosConsultaSuscripcionSaldoPDCResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.dto.CompraVentaTitulosValoresDTO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.dto.ConsultaDeTenenciaResponseDTO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.dto.ConsultaSuscripcionSaldoPDCDTO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.entities.ConsultaDeTenenciaRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.entities.ConsultaDeTenenciaResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.entities.DatosConsultaDeTenenciaResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.entities.DatosTenencia;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.view.ConfirmacionVentaTitulosInView;
import ar.com.santanderrio.obp.servicios.pagos.dao.IntervinientesDAO;
import ar.com.santanderrio.obp.servicios.pagos.entities.IntervinientesEntity;
import ar.com.santanderrio.obp.servicios.pagos.entities.IntervinientesOutEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.CuentaMock;

@RunWith(MockitoJUnitRunner.class)
public class TitulosOrdenVentaBOTest {

	@InjectMocks
	private TituloOrdenVentaBOImpl tituloOrdenVentaBO;

	@Mock
	private OrdenesTitulosDAO ordenesTitulosDAO;

	@Mock
	private IntervinientesDAO intervinientesDAO;

	@Mock
	private MensajeBO mensajeBO;

	@Spy
	@InjectMocks
	private RespuestaFactory respuestaFactory = new RespuestaFactory();

	@Test
	public void consultarCuentasTitulosWebServiceOk() throws DAOException, BusinessException, IllegalAccessException {

		// When
		Cliente cliente = mock(Cliente.class);
		ApplicationContext ctx = Mockito.mock(ApplicationContext.class);
		SessionUsuarioData request = Mockito.mock(SessionUsuarioData.class);
		FieldUtils.writeDeclaredStaticField(ContextHolder.class, "context", ctx, true);

		Segmento segmento = new Segmento();
		segmento.setDuo(true);
        segmento.setPyme(false);

		List<Cuenta> cuentas = new ArrayList<Cuenta>();
		Cuenta cuenta = CuentaMock.completarInfoCuenta();
		cuentas.add(cuenta);

		when(ordenesTitulosDAO.consultaDeTenencia(Matchers.any(ConsultaDeTenenciaRequestEntity.class)))
				.thenReturn(armarConsultaTenenciaResponse());
		when(ctx.getBean(SessionUsuarioData.class)).thenReturn(request);
		when(cliente.getSegmento()).thenReturn(segmento);

		// Then
		ConsultaDeTenenciaResponseDTO respuesta = tituloOrdenVentaBO.consultarCuentasTitulosWebService(cliente, cuentas,
				false);

		// Expected
		Assert.assertNotNull(respuesta);

	}

	@Test
	public void consultarCuentasTitulosBancaPrivadaWebServiceOk()
			throws DAOException, BusinessException, IllegalAccessException {

		// When
		Cliente cliente = mock(Cliente.class);
		ApplicationContext ctx = Mockito.mock(ApplicationContext.class);
		SessionUsuarioData request = Mockito.mock(SessionUsuarioData.class);
		FieldUtils.writeDeclaredStaticField(ContextHolder.class, "context", ctx, true);

		Segmento segmento = new Segmento();
		segmento.setDuo(true);
        segmento.setPyme(false);

		List<Cuenta> cuentas = new ArrayList<Cuenta>();
		Cuenta cuenta = CuentaMock.completarInfoCuenta();
		cuentas.add(cuenta);

		when(ordenesTitulosDAO.consultaDeTenencia(Matchers.any(ConsultaDeTenenciaRequestEntity.class)))
				.thenReturn(armarConsultaTenenciaResponse());
		when(ctx.getBean(SessionUsuarioData.class)).thenReturn(request);
		when(cliente.getSegmento()).thenReturn(segmento);

		// Then
		ConsultaDeTenenciaResponseDTO respuesta = tituloOrdenVentaBO.consultarCuentasTitulosWebService(cliente, cuentas,
				true);

		// Expected
		Assert.assertNotNull(respuesta);

	}

	@SuppressWarnings("unchecked")
	@Test(expected = BusinessException.class)
	public void consultarCuentasTitulosWebServiceErrorDAOException()
			throws DAOException, BusinessException, IllegalAccessException {

		// When
		Cliente cliente = mock(Cliente.class);
		ApplicationContext ctx = Mockito.mock(ApplicationContext.class);
		SessionUsuarioData request = Mockito.mock(SessionUsuarioData.class);
		FieldUtils.writeDeclaredStaticField(ContextHolder.class, "context", ctx, true);

		Segmento segmento = new Segmento();
		segmento.setDuo(true);
        segmento.setPyme(false);

		List<Cuenta> cuentas = new ArrayList<Cuenta>();
		Cuenta cuenta = CuentaMock.completarInfoCuenta();
		cuentas.add(cuenta);

		when(ordenesTitulosDAO.consultaDeTenencia(Matchers.any(ConsultaDeTenenciaRequestEntity.class)))
				.thenThrow(DAOException.class);
		when(ctx.getBean(SessionUsuarioData.class)).thenReturn(request);
		when(cliente.getSegmento()).thenReturn(segmento);

		// Then
		tituloOrdenVentaBO.consultarCuentasTitulosWebService(cliente, cuentas, false);

	}

	@Test
	public void consultarTitularesOk() throws BusinessException, DAOException {

		// When
		Cuenta cuenta = mock(Cuenta.class);
		when(intervinientesDAO.obtenerIntervinientes(Matchers.any(Cuenta.class))).thenReturn(armarIntervinientes());

		// Then
		List<String> respuesta = tituloOrdenVentaBO.consultarTitulares(cuenta.getIntervinientes());

		// Expected
		Assert.assertNotNull(respuesta);

	}

	@Ignore
	@Test
	public void ejecutarMetodoCompraVentaTitulosValores() throws BusinessException, DAOException {

		// When
		Cliente cliente = mock(Cliente.class);

		CompraVtaTitulosResponse response = new CompraVtaTitulosResponse();
		DatosCompraVtaTitulosResponse datos = new DatosCompraVtaTitulosResponse();
		response.setDatos(datos);

		ConfirmacionVentaTitulosInView confirmacion = armarConfirmacionInView();
		confirmacion.setEsBancaPrivada(false);

		when(ordenesTitulosDAO.compraVtaTitulos(Matchers.any(ComptaVtaTitulosRequestEntity.class)))
				.thenReturn(response);

		// Then
		CompraVentaTitulosValoresDTO respuestaDTO = tituloOrdenVentaBO.ejecutarMetodoCompraVentaTitulosValores(cliente,
				confirmacion, "D");

		// Expected
		Assert.assertNotNull(respuestaDTO);

	}

	@Test
	public void consultarDatosSuscripcionSaldoPDCOK() throws DAOException {

		// When
		Cliente cliente = mock(Cliente.class);

		ConsultaSuscripcionSaldoPDCResponse response = new ConsultaSuscripcionSaldoPDCResponse();
		DatosConsultaSuscripcionSaldoPDCResponse datos = new DatosConsultaSuscripcionSaldoPDCResponse();
		response.setDatos(datos);

		when(ordenesTitulosDAO.consultaSuscripcionSaldoPDC(Matchers.any(ConsultaSuscripcionSaldoPDCRequest.class)))
				.thenReturn(response);

		// Then
		ConsultaSuscripcionSaldoPDCDTO respuestaDTO = tituloOrdenVentaBO.consultarDatosSuscripcionSaldoPDC(cliente,
				armarConfirmacionInView());

		// Expected
		Assert.assertNotNull(respuestaDTO);

	}

	private ConsultaDeTenenciaResponse armarConsultaTenenciaResponse() {

		ConsultaDeTenenciaResponse consultaDeTenencia = new ConsultaDeTenenciaResponse();
		DatosConsultaDeTenenciaResponse datos = new DatosConsultaDeTenenciaResponse();

		List<DatosTenencia> listaTenencia = new ArrayList<DatosTenencia>();

		DatosTenencia datosTenencia = new DatosTenencia();
		datosTenencia.setFechaCustodia("2018/02/28");
		datosTenencia.setFechaLiquidacion("2018/02/28");
		datosTenencia.setTenenciaNominalNegociable(new BigDecimal("35056"));
		datosTenencia.setCtaTitulo(2013893);
		datosTenencia.setPlazo(48);
		datosTenencia.setCodigoAmigable("MTCFO");
		datosTenencia.setCodigoEspecieRossi("91689");
		datosTenencia.setCodigoEspecieCajaValores("91689");
		datosTenencia.setTipoEspecie("BON");
		datosTenencia.setInstrumento("TITULOS PRIVADOS");
		datosTenencia.setCodigoMonedaEmision("ARS");
		datosTenencia.setEstadoCuentaTitulo("0");
		datosTenencia.setMotivoBloqueo("");
		datosTenencia.setEspecieCodigo("");
		datosTenencia.setCodigoEstado("0");
		datosTenencia.setDescrEstado("NEGOCIABLE");
		datosTenencia.setSegmento("RTL");
		datosTenencia.setDescripcionEspecie("MASTELLONE HERMANOS S.A.12.625%  VTO 03/07/2021");
		datosTenencia.setEstadoTenencia("Libre Disponibilidad");
		datosTenencia.setTenenciaTotal(new BigDecimal("40000"));

		listaTenencia.add(datosTenencia);

		datos.setListaTenencia(listaTenencia);
		consultaDeTenencia.setDatos(datos);
		consultaDeTenencia.setCodigo(0);

		return consultaDeTenencia;
	}

	private IntervinientesOutEntity armarIntervinientes() {

		IntervinientesOutEntity respuesta = new IntervinientesOutEntity();

		List<IntervinientesEntity> listaRepeticiones = new ArrayList<IntervinientesEntity>();
		IntervinientesEntity intervinientesEntity = new IntervinientesEntity();
		intervinientesEntity.setNombre("Pepe");
		intervinientesEntity.setApellido("Tarjota");
		listaRepeticiones.add(intervinientesEntity);
		respuesta.setListaRepeticiones(listaRepeticiones);

		return respuesta;
	}

	private ConfirmacionVentaTitulosInView armarConfirmacionInView() {

		ConfirmacionVentaTitulosInView confirmacionInView = new ConfirmacionVentaTitulosInView();
		confirmacionInView.setFechaLiquidacion("2018-05-07T00:00:00-03:00");
		confirmacionInView.setCuentaTitulo("1323393/8");
		confirmacionInView.setNombreEspecie("Tenaris");
		confirmacionInView.setCodigoEspecie("TS");
		confirmacionInView.setPlazo("48 hs");
		confirmacionInView.setMonedaOperacion("Pesos");
		confirmacionInView.setCotizacion("28");
		confirmacionInView.setFechaReferencia("08/05/2018");
		confirmacionInView.setHoraReferencia("15:05");
		confirmacionInView.setCantidadTitulo("10");
		confirmacionInView.setCotizacionLimite("15,50");
		confirmacionInView.setNumeroCuenta("32029192");
		confirmacionInView.setTipoCuenta("09");
		confirmacionInView.setEspecieCodigo("13002");

		return confirmacionInView;

	}

}