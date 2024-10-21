package ar.com.santanderrio.obp.servicios.inversiones.resumen.web.manager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.reporte.entities.TipoArchivoEnum;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.base.service.ServiceException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SessionResumenInversiones;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.CuentaBancaPrivada;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.inversiones.resumen.bo.ResumenMensualInversionesBO;
import ar.com.santanderrio.obp.servicios.inversiones.resumen.web.manager.impl.ResumenInversionesManagerImpl;
import ar.com.santanderrio.obp.servicios.inversiones.resumen.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.inversiones.resumen.web.view.ResumenInversionesView;
import ar.com.santanderrio.obp.servicios.inversiones.resumen.web.view.ResumenesInversionesView;
import ar.com.santanderrio.obp.servicios.inversiones.resumen.web.view.ResumenesMensualesInversionesView;
import ar.com.santanderrio.obp.servicios.resumen.entities.ReporteResumenMensualInversiones;
import ar.com.santanderrio.obp.servicios.resumen.entities.ResumenMensualInversiones;

/**
 * The Class ResumenInversionesManagerTest.
 */
@RunWith(MockitoJUnitRunner.class)
public class ResumenInversionesManagerTest {

	/** The resumen inversiones manager. */
	@InjectMocks
	private ResumenInversionesManagerImpl resumenInversionesManager = new ResumenInversionesManagerImpl();

	/** The sesion cliente. */
	@Mock
	private SesionCliente sesionCliente;

	/** The estadistica manager. */
	@Mock
	private EstadisticaManager estadisticaManager;

	/** The session resumen inversiones. */
	@Mock
	private SessionResumenInversiones sessionResumenInversiones;

	/** The mensaje BO. */
	@Mock
	private MensajeBO mensajeBO;

	/** The resumen mensual inversiones BO. */
	@Mock
	private ResumenMensualInversionesBO resumenMensualInversionesBO;

	/** The respuesta factory. */
	@InjectMocks
	@Spy
	private RespuestaFactory respuestaFactory = new RespuestaFactory();

	/** The cliente. */
	private Cliente cliente;

	/** The nro cuenta. */
	private String nroCuenta;

	/** The nro cuenta BP. */
	private String nroCuentaBP;

	/** The ids. */
	private List<String> ids;

	/**
	 * Init.
	 */
	@Before
	public void init() {
		obtenerCliente();
		this.nroCuenta = "000-123456/7";
		this.nroCuentaBP = "250-123456/7";
		this.ids = new ArrayList<String>();
		this.ids.add("0");
		this.ids.add("1");
	}

	/**
	 * Obtener lista resumen OK test.
	 *
	 * @throws ServiceException
	 *     the service exception
	 * @throws BusinessException
	 *     the business exception
	 */
	@Test
	public void obtenerListaResumenOKTest() throws ServiceException, BusinessException {
		obtenerListaResumenOKTest(false, this.nroCuenta);
	}

	/**
	 * Obtener lista resumen BP OK test.
	 *
	 * @throws ServiceException
	 *     the service exception
	 * @throws BusinessException
	 *     the business exception
	 */
	@Test
	public void obtenerListaResumenBPOKTest() throws ServiceException, BusinessException {
		obtenerListaResumenOKTest(true, this.nroCuentaBP);
	}

	/**
	 * Obtener lista resumen error test.
	 *
	 * @throws ServiceException
	 *     the service exception
	 * @throws BusinessException
	 *     the business exception
	 */
	@Test
	public void obtenerListaResumenErrorTest() throws ServiceException, BusinessException {
		obtenerListaResumenErrorTest(false);
	}

	/**
	 * Obtener lista resumen BP error test.
	 *
	 * @throws ServiceException
	 *     the service exception
	 * @throws BusinessException
	 *     the business exception
	 */
	@Test
	public void obtenerListaResumenBPErrorTest() throws ServiceException, BusinessException {
		obtenerListaResumenErrorTest(true);
	}

	/**
	 * Obtener resumenes descarga multiple OK test.
	 *
	 * @throws ServiceException
	 *     the service exception
	 * @throws BusinessException
	 *     the business exception
	 */
	@Test
	public void obtenerResumenesDescargaMultipleOKTest() throws ServiceException, BusinessException {
		obtenerResumenesDescargaMultipleOKTest(false);
	}

	/**
	 * Obtener resumenes descarga multiple BP OK test.
	 *
	 * @throws ServiceException
	 *     the service exception
	 * @throws BusinessException
	 *     the business exception
	 */
	@Test
	public void obtenerResumenesDescargaMultipleBPOKTest() throws ServiceException, BusinessException {
		obtenerResumenesDescargaMultipleOKTest(true);
	}

	/**
	 * Obtener resumenes descarga multiple error test.
	 *
	 * @throws ServiceException
	 *     the service exception
	 * @throws BusinessException
	 *     the business exception
	 */
	@Test
	public void obtenerResumenesDescargaMultipleErrorTest() throws ServiceException, BusinessException {
		obtenerResumenesDescargaMultipleErrorTest(false);
	}

	/**
	 * Obtener resumenes descarga multiple BP error test.
	 *
	 * @throws ServiceException
	 *     the service exception
	 * @throws BusinessException
	 *     the business exception
	 */
	@Test
	public void obtenerResumenesDescargaMultipleBPErrorTest() throws ServiceException, BusinessException {
		obtenerResumenesDescargaMultipleErrorTest(true);
	}

	/**
	 * Obtener resumenes PDF OK test.
	 *
	 * @throws ServiceException
	 *     the service exception
	 */
	@Test
	public void obtenerResumenesPDFOKTest() throws ServiceException {
		obtenerResumenesPDFOKTest(false);
	}

	/**
	 * Obtener resumenes PDF BP OK test.
	 *
	 * @throws ServiceException
	 *     the service exception
	 */
	@Test
	public void obtenerResumenesPDFBPOKTest() throws ServiceException {
		obtenerResumenesPDFOKTest(true);
	}

	/**
	 * Obtener resumenes PDF error test.
	 *
	 * @throws ServiceException
	 *     the service exception
	 */
	@Test
	public void obtenerResumenesPDFErrorTest() throws ServiceException {
		obtenerResumenesPDFErrorTest(false);
	}

	/**
	 * Obtener resumenes PDF BP error test.
	 *
	 * @throws ServiceException
	 *     the service exception
	 */
	@Test
	public void obtenerResumenesPDFBPErrorTest() throws ServiceException {
		obtenerResumenesPDFErrorTest(true);
	}

	/**
	 * Obtener lista resumen OK test.
	 *
	 * @param isBP
	 *     the is BP
	 * @param nroCta
	 *     the nro cta
	 * @throws ServiceException
	 *     the service exception
	 * @throws BusinessException
	 *     the business exception
	 */
	private void obtenerListaResumenOKTest(boolean isBP, String nroCta) throws ServiceException, BusinessException {

		Respuesta<List<ResumenMensualInversiones>> respuestaReporteMensual = new Respuesta<List<ResumenMensualInversiones>>();

		respuestaReporteMensual.setEstadoRespuesta(EstadoRespuesta.OK);
		List<ResumenMensualInversiones> resumenes = obtenerListaResumenesMensuales();

		respuestaReporteMensual.setRespuesta(resumenes);

		when(resumenMensualInversionesBO.obtenerListaResumen(Matchers.any(Cuenta.class), Matchers.anyBoolean()))
				.thenReturn(respuestaReporteMensual);
		when(sesionCliente.getCliente()).thenReturn(this.cliente);

		Respuesta<ResumenesMensualesInversionesView> respuesta = resumenInversionesManager.obtenerListaResumen(nroCta,
				isBP);

		assertNotNull(respuesta);
		assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());

		verify(estadisticaManager).add(
				isBP ? EstadisticasConstants.VER_RESUMENES_INV_BP : EstadisticasConstants.VER_RESUMENES_INV,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
	}

	/**
	 * Obtener lista resumen error test.
	 *
	 * @param isBP
	 *     the is BP
	 * @throws ServiceException
	 *     the service exception
	 * @throws BusinessException
	 *     the business exception
	 */
	@SuppressWarnings("unchecked")
	private void obtenerListaResumenErrorTest(boolean isBP) throws ServiceException, BusinessException {

		Mensaje mensajeErrorListaResumenes = new Mensaje();
		mensajeErrorListaResumenes.setMensaje("Mensaje error Lista Resumenes");

		when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.ERROR_LISTA_RESUMENES_INV))
				.thenReturn(mensajeErrorListaResumenes);
		when(resumenMensualInversionesBO.obtenerListaResumen(Matchers.any(Cuenta.class), Matchers.anyBoolean()))
				.thenThrow(BusinessException.class);
		when(sesionCliente.getCliente()).thenReturn(this.cliente);

		Respuesta<ResumenesMensualesInversionesView> respuesta = resumenInversionesManager
				.obtenerListaResumen(this.nroCuenta, isBP);

		assertNotNull(respuesta);
		assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());

		verify(estadisticaManager).add(
				isBP ? EstadisticasConstants.VER_RESUMENES_INV_BP : EstadisticasConstants.VER_RESUMENES_INV,
				EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
	}

	/**
	 * Obtener cuentas OK test.
	 *
	 * @throws ServiceException
	 *     the service exception
	 */
	@Test
	public void obtenerCuentasOKTest() throws ServiceException {

		when(sesionCliente.getCliente()).thenReturn(this.cliente);

		Respuesta<ResumenesInversionesView> respuesta = resumenInversionesManager.obtenerCuentas();

		assertNotNull(respuesta);
		assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
	}

	/**
	 * Obtener resumenes descarga multiple OK test.
	 *
	 * @param isBP
	 *     the is BP
	 * @throws ServiceException
	 *     the service exception
	 * @throws BusinessException
	 *     the business exception
	 */
	@SuppressWarnings("unchecked")
	private void obtenerResumenesDescargaMultipleOKTest(boolean isBP) throws ServiceException, BusinessException {
		List<ResumenMensualInversiones> listResumenesMensual = new ArrayList<ResumenMensualInversiones>();

		ReporteResumenMensualInversiones reporteResumenMensual = new ReporteResumenMensualInversiones();
		reporteResumenMensual.setTipoArchivo(TipoArchivoEnum.PDF);
		Respuesta<ReporteResumenMensualInversiones> respuestaReporteMensual = new Respuesta<ReporteResumenMensualInversiones>();
		respuestaReporteMensual.setEstadoRespuesta(EstadoRespuesta.OK);
		respuestaReporteMensual.setRespuesta(reporteResumenMensual);

		when(sessionResumenInversiones.getResumenesByIds(Matchers.anyList(), Matchers.any(Cuenta.class)))
				.thenReturn(listResumenesMensual);
		when(resumenMensualInversionesBO.obtenerResumenDescargaMultiple(Matchers.anyList(), Matchers.any(Cuenta.class),
				Matchers.anyBoolean(), Matchers.anyInt())).thenReturn(respuestaReporteMensual);
		when(sesionCliente.getCliente()).thenReturn(this.cliente);

		Respuesta<ReporteView> respuesta = resumenInversionesManager.obtenerResumenDescargaMultiple(this.ids,
				this.nroCuenta, isBP, 1);

		assertNotNull(respuesta);
		assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());

		ReporteView reporteView = respuesta.getRespuesta();
		assertNotNull(reporteView);
		assertEquals(TipoArchivoEnum.PDF.getMimeTipe(), reporteView.getTipoArchivo());

		verify(estadisticaManager).add(
				isBP ? EstadisticasConstants.VER_RESUMENES_DESCARGA_MULTIPLE_INV_BP
						: EstadisticasConstants.VER_RESUMENES_DESCARGA_MULTIPLE_INV,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
	}

	/**
	 * Obtener resumenes descarga multiple error test.
	 *
	 * @param isBP
	 *     the is BP
	 * @throws ServiceException
	 *     the service exception
	 * @throws BusinessException
	 *     the business exception
	 */
	@SuppressWarnings("unchecked")
	private void obtenerResumenesDescargaMultipleErrorTest(boolean isBP) throws ServiceException, BusinessException {
		List<ResumenMensualInversiones> listResumenesMensual = new ArrayList<ResumenMensualInversiones>();

		ReporteResumenMensualInversiones reporteResumenMensual = new ReporteResumenMensualInversiones();
		reporteResumenMensual.setTipoArchivo(TipoArchivoEnum.PDF);
		Respuesta<ReporteResumenMensualInversiones> respuestaReporteMensual = new Respuesta<ReporteResumenMensualInversiones>();
		respuestaReporteMensual.setEstadoRespuesta(EstadoRespuesta.OK);
		respuestaReporteMensual.setRespuesta(reporteResumenMensual);

		when(sessionResumenInversiones.getResumenesByIds(Matchers.anyList(), Matchers.any(Cuenta.class)))
				.thenReturn(listResumenesMensual);
		when(resumenMensualInversionesBO.obtenerResumenDescargaMultiple(Matchers.anyList(), Matchers.any(Cuenta.class),
				Matchers.anyBoolean(), Matchers.anyInt())).thenThrow(BusinessException.class);
		when(sesionCliente.getCliente()).thenReturn(this.cliente);

		Respuesta<ReporteView> respuesta = resumenInversionesManager.obtenerResumenDescargaMultiple(this.ids,
				this.nroCuenta, isBP, 1);

		assertNull(respuesta);

		verify(estadisticaManager).add(
				isBP ? EstadisticasConstants.VER_RESUMENES_DESCARGA_MULTIPLE_INV_BP
						: EstadisticasConstants.VER_RESUMENES_DESCARGA_MULTIPLE_INV,
				EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
	}

	/**
	 * Obtener resumenes PDF OK test.
	 *
	 * @param isBP
	 *     the is BP
	 * @throws ServiceException
	 *     the service exception
	 */
	@SuppressWarnings("unchecked")
	private void obtenerResumenesPDFOKTest(boolean isBP) throws ServiceException {
		List<ResumenMensualInversiones> listResumenesMensual = new ArrayList<ResumenMensualInversiones>();

		ReporteResumenMensualInversiones reporteResumenMensual = new ReporteResumenMensualInversiones();
		reporteResumenMensual.setTipoArchivo(TipoArchivoEnum.PDF);
		Respuesta<ReporteResumenMensualInversiones> respuestaReporteMensual = new Respuesta<ReporteResumenMensualInversiones>();
		respuestaReporteMensual.setEstadoRespuesta(EstadoRespuesta.OK);
		respuestaReporteMensual.setRespuesta(reporteResumenMensual);

		ResumenInversionesView resumenInversionesView = new ResumenInversionesView();
		resumenInversionesView.setNumeroCuenta("00001234567");
		resumenInversionesView.setBancaPrivada(isBP);
		String[] id = { "0" };
		resumenInversionesView.setId(id);

		when(sessionResumenInversiones.getResumenesByIds(Matchers.anyList(), Matchers.any(Cuenta.class)))
				.thenReturn(listResumenesMensual);
		when(resumenMensualInversionesBO.obtenerResumenesPDF(Matchers.anyList(), Matchers.any(Cuenta.class),
				Matchers.anyBoolean())).thenReturn(respuestaReporteMensual);
		when(sesionCliente.getCliente()).thenReturn(this.cliente);

		Respuesta<ReporteView> respuesta = resumenInversionesManager.obtenerResumenesPDF(resumenInversionesView);

		assertNotNull(respuesta);
		assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());

		ReporteView reporteView = respuesta.getRespuesta();
		assertNotNull(reporteView);
		assertEquals(TipoArchivoEnum.PDF.getMimeTipe(), reporteView.getTipoArchivo());

		verify(estadisticaManager).add(
				isBP ? EstadisticasConstants.VER_RESUMENES_DESCARGA_SIMPLE_INV_BP
						: EstadisticasConstants.VER_RESUMENES_DESCARGA_SIMPLE_INV,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
	}

	/**
	 * Obtener resumenes PDF error test.
	 *
	 * @param isBP
	 *     the is BP
	 * @throws ServiceException
	 *     the service exception
	 */
	@SuppressWarnings("unchecked")
	private void obtenerResumenesPDFErrorTest(boolean isBP) throws ServiceException {
		List<ResumenMensualInversiones> listResumenesMensual = new ArrayList<ResumenMensualInversiones>();

		ReporteResumenMensualInversiones reporteResumenMensual = new ReporteResumenMensualInversiones();
		reporteResumenMensual.setTipoArchivo(TipoArchivoEnum.PDF);
		Respuesta<ReporteResumenMensualInversiones> respuestaReporteMensual = new Respuesta<ReporteResumenMensualInversiones>();
		respuestaReporteMensual.setEstadoRespuesta(EstadoRespuesta.ERROR);
		respuestaReporteMensual.setRespuesta(reporteResumenMensual);

		ResumenInversionesView resumenInversionesView = new ResumenInversionesView();
		resumenInversionesView.setNumeroCuenta("00001234567");
		resumenInversionesView.setBancaPrivada(isBP);
		String[] id = { "0" };
		resumenInversionesView.setId(id);

		when(sessionResumenInversiones.getResumenesByIds(Matchers.anyList(), Matchers.any(Cuenta.class)))
				.thenReturn(listResumenesMensual);
		when(resumenMensualInversionesBO.obtenerResumenesPDF(Matchers.anyList(), Matchers.any(Cuenta.class),
				Matchers.anyBoolean())).thenReturn(respuestaReporteMensual);
		when(sesionCliente.getCliente()).thenReturn(this.cliente);

		Respuesta<ReporteView> respuesta = resumenInversionesManager.obtenerResumenesPDF(resumenInversionesView);

		assertNotNull(respuesta);
		assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());

		verify(estadisticaManager).add(
				isBP ? EstadisticasConstants.VER_RESUMENES_DESCARGA_SIMPLE_INV_BP
						: EstadisticasConstants.VER_RESUMENES_DESCARGA_SIMPLE_INV,
				EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
	}

	/**
	 * Obtener cliente.
	 */
	private void obtenerCliente() {
		this.cliente = new Cliente();
		List<Cuenta> cuentasRetail = new ArrayList<Cuenta>();
		Cuenta cuenta = new Cuenta();
		cuenta.setNroCuentaProducto("00000000001234567");
		cuenta.setNroSucursal("000");
		cuentasRetail.add(cuenta);
		List<CuentaBancaPrivada> cuentasBP = new ArrayList<CuentaBancaPrivada>();
		CuentaBancaPrivada cuentaBP = new CuentaBancaPrivada();
		Cuenta cuentaOperativa = new Cuenta();
		cuentaOperativa.setNroCuentaProducto("00000000001234567");
		cuentaOperativa.setNroSucursal("250");
		Cuenta cuentaTitulo = new Cuenta();
		cuentaTitulo.setNroCuentaProducto("00000000005555555");
		cuentaTitulo.setNroSucursal("000");
		cuentaBP.setCuentaOperativa(cuentaOperativa);
		cuentaBP.setCuentaTitulo(cuentaTitulo);
		cuentasBP.add(cuentaBP);
		this.cliente.setCuentasRetail(cuentasRetail);
		this.cliente.setCuentasBancaPrivada(cuentasBP);
	}

	/**
	 * Obtener lista resumenes mensuales.
	 *
	 * @return the list
	 */
	private List<ResumenMensualInversiones> obtenerListaResumenesMensuales() {
		List<ResumenMensualInversiones> resumenesMensuales = new ArrayList<ResumenMensualInversiones>();
		ResumenMensualInversiones resumenMensual = new ResumenMensualInversiones();
		resumenMensual.setFechaDesde("01/01/19");
		resumenMensual.setFechaHasta("31/01/19");
		resumenMensual.setPeriodo("ENERO 2019");
		resumenMensual.setId(new Long(0));
		resumenesMensuales.add(resumenMensual);
		resumenMensual = new ResumenMensualInversiones();
		resumenMensual.setFechaDesde("01/02/19");
		resumenMensual.setFechaHasta("28/02/19");
		resumenMensual.setPeriodo("FEBRERO 2019");
		resumenMensual.setId(new Long(1));
		resumenesMensuales.add(resumenMensual);
		return resumenesMensuales;
	}

}
