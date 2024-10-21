package ar.com.santanderrio.obp.servicios.inversiones.resumen.bo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.reporte.entities.TipoArchivoEnum;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.dao.MensajeDAO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.ondemand.dao.OndemandDAO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.inversiones.resumen.bo.impl.ResumenMensualInversionesBOImpl;
import ar.com.santanderrio.obp.servicios.ondemand.entities.ResumenParams;
import ar.com.santanderrio.obp.servicios.ondemand.entities.WSODException;
import ar.com.santanderrio.obp.servicios.resumen.entities.ReporteResumenMensualInversiones;
import ar.com.santanderrio.obp.servicios.resumen.entities.ResumenMensualInversiones;

/**
 * The Class ResumenMensualInversionesBOTest.
 */
@RunWith(MockitoJUnitRunner.class)
public class ResumenMensualInversionesBOTest {

	/** The resumen mensual inversiones BO. */
	@InjectMocks
	private ResumenMensualInversionesBOImpl resumenMensualInversionesBO = new ResumenMensualInversionesBOImpl();

	/** The ondemand DAO. */
	@Mock
	private OndemandDAO ondemandDAO;

	/** The mensaje BO. */
	@Mock
	private MensajeBO mensajeBO;

	/** The mensaje DAO. */
	@Mock
	private MensajeDAO mensajeDAO;

	/** The respuesta factory. */
	@InjectMocks
	@Spy
	private RespuestaFactory respuestaFactory = new RespuestaFactory();

	/** The resumenes mensuales. */
	private List<ResumenMensualInversiones> resumenesMensuales;

	/** The cuenta. */
	private Cuenta cuenta;

	/**
	 * Init.
	 */
	@Before
	public void init() {
		obtenerListaResumenesMensuales();
		this.cuenta = new Cuenta();
		this.cuenta.setAlias("Mi cuenta");
		this.cuenta.setCliente(new Cliente());
	}

	/**
	 * Obtener resumen descarga multiple OK test.
	 *
	 * @throws BusinessException
	 *     the business exception
	 * @throws WSODException
	 *     the WSOD exception
	 */
	@Test
	public void obtenerResumenDescargaMultipleOKTest() throws BusinessException, WSODException {
		obtenerResumenDescargaMultipleOKTest(false);
	}

	/**
	 * Obtener resumen descarga multiple BP OK test.
	 *
	 * @throws BusinessException
	 *     the business exception
	 * @throws WSODException
	 *     the WSOD exception
	 */
	@Test
	public void obtenerResumenDescargaMultipleBPOKTest() throws BusinessException, WSODException {
		obtenerResumenDescargaMultipleOKTest(true);
	}

	/**
	 * Obtener resumen descarga multiple uno error test.
	 *
	 * @throws BusinessException
	 *     the business exception
	 * @throws WSODException
	 *     the WSOD exception
	 */
	@Test
	public void obtenerResumenDescargaMultipleUnoErrorTest() throws BusinessException, WSODException {
		obtenerResumenDescargaMultipleUnoErrorTest(false);
	}

	/**
	 * Obtener resumen descarga multiple uno BP error test.
	 *
	 * @throws BusinessException
	 *     the business exception
	 * @throws WSODException
	 *     the WSOD exception
	 */
	@Test
	public void obtenerResumenDescargaMultipleUnoBPErrorTest() throws BusinessException, WSODException {
		obtenerResumenDescargaMultipleUnoErrorTest(true);
	}

	/**
	 * Obtener resumen descarga multiple varios error test.
	 *
	 * @throws BusinessException
	 *     the business exception
	 * @throws WSODException
	 *     the WSOD exception
	 */
	@Test
	public void obtenerResumenDescargaMultipleVariosErrorTest() throws BusinessException, WSODException {
		obtenerResumenDescargaMultipleVariosErrorTest(false);
	}

	/**
	 * Obtener resumen descarga multiple varios BP error test.
	 *
	 * @throws BusinessException
	 *     the business exception
	 * @throws WSODException
	 *     the WSOD exception
	 */
	@Test
	public void obtenerResumenDescargaMultipleVariosBPErrorTest() throws BusinessException, WSODException {
		obtenerResumenDescargaMultipleVariosErrorTest(true);
	}

	/**
	 * Obtener resumen descarga simple test.
	 *
	 * @throws BusinessException
	 *     the business exception
	 * @throws WSODException
	 *     the WSOD exception
	 */
	@Test
	public void obtenerResumenDescargaSimpleTest() throws BusinessException, WSODException {
		obtenerResumenDescargaSimpleTest(false);
	}

	/**
	 * Obtener resumen descarga simple BP test.
	 *
	 * @throws BusinessException
	 *     the business exception
	 * @throws WSODException
	 *     the WSOD exception
	 */
	@Test
	public void obtenerResumenDescargaSimpleBPTest() throws BusinessException, WSODException {
		obtenerResumenDescargaSimpleTest(true);
	}

	/**
	 * Obtener lista resumen OK test.
	 *
	 * @throws BusinessException
	 *     the business exception
	 * @throws WSODException
	 *     the WSOD exception
	 * @throws IllegalAccessException
	 *     the illegal access exception
	 */
	@Test
	public void obtenerListaResumenOKTest() throws BusinessException, WSODException, IllegalAccessException {
		obtenerListaResumenOKTest(false);
	}

	/**
	 * Obtener lista resumen BP OK test.
	 *
	 * @throws BusinessException
	 *     the business exception
	 * @throws WSODException
	 *     the WSOD exception
	 * @throws IllegalAccessException
	 *     the illegal access exception
	 */
	@Test
	public void obtenerListaResumenBPOKTest() throws BusinessException, WSODException, IllegalAccessException {
		obtenerListaResumenOKTest(true);
	}

	/**
	 * Obtener lista resumen error test.
	 *
	 * @throws BusinessException
	 *     the business exception
	 * @throws WSODException
	 *     the WSOD exception
	 * @throws IllegalAccessException
	 *     the illegal access exception
	 */
	@Test
	public void obtenerListaResumenErrorTest() throws BusinessException, WSODException, IllegalAccessException {
		obtenerListaResumenErrorTest(false);
	}

	/**
	 * Obtener lista resumen BP error test.
	 *
	 * @throws BusinessException
	 *     the business exception
	 * @throws WSODException
	 *     the WSOD exception
	 * @throws IllegalAccessException
	 *     the illegal access exception
	 */
	@Test
	public void obtenerListaResumenBPErrorTest() throws BusinessException, WSODException, IllegalAccessException {
		obtenerListaResumenErrorTest(true);
	}

	/**
	 * Obtener resumen descarga multiple OK test.
	 *
	 * @param isBP
	 *     the is BP
	 * @throws BusinessException
	 *     the business exception
	 * @throws WSODException
	 *     the WSOD exception
	 */
	private void obtenerResumenDescargaMultipleOKTest(boolean isBP) throws BusinessException, WSODException {
		
		ReporteResumenMensualInversiones reporteResumenMensual = new ReporteResumenMensualInversiones();
		String bytes = "bytes";
		reporteResumenMensual.setBytes(bytes.getBytes());
		reporteResumenMensual.setNombre("Prueba");
		reporteResumenMensual.setTipoArchivo(TipoArchivoEnum.PDF);

		Mockito.when(ondemandDAO.obtenerReporteMensualInversiones(this.resumenesMensuales.get(0), this.cuenta, isBP))
				.thenReturn(reporteResumenMensual);

		Respuesta<ReporteResumenMensualInversiones> respuesta = resumenMensualInversionesBO
				.obtenerResumenDescargaMultiple(this.resumenesMensuales, this.cuenta, isBP, 1);

		assertNotNull(respuesta);
		assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
	}

	/**
	 * Obtener resumen descarga multiple uno error test.
	 *
	 * @param isBP
	 *     the is BP
	 * @throws BusinessException
	 *     the business exception
	 * @throws WSODException
	 *     the WSOD exception
	 */
	private void obtenerResumenDescargaMultipleUnoErrorTest(boolean isBP) throws BusinessException, WSODException {

		Mensaje mensaje = new Mensaje();
		mensaje.setCodigo(CodigoMensajeConstantes.ERROR_DESCARGA_MULTIPLE_UNO_INV);
		mensaje.setMensaje("No pudimos descargar tu resumen. Por favor, intentá nuevamente en unos minutos.");

		ReporteResumenMensualInversiones reporteResumenMensual = new ReporteResumenMensualInversiones();
		String bytes = "bytes";
		reporteResumenMensual.setBytes(bytes.getBytes());
		reporteResumenMensual.setNombre("Prueba.pdf");
		reporteResumenMensual.setTipoArchivo(TipoArchivoEnum.PDF);

		Mockito.when(ondemandDAO.obtenerReporteMensualInversiones(new ResumenMensualInversiones(), this.cuenta, isBP))
				.thenReturn(null);
		Mockito.when(mensajeDAO.obtenerMensajePorCodigo(CodigoMensajeConstantes.ERROR_DESCARGA_MULTIPLE_UNO_INV))
				.thenReturn(mensaje);

		Respuesta<ReporteResumenMensualInversiones> respuesta = resumenMensualInversionesBO
				.obtenerResumenDescargaMultiple(this.resumenesMensuales, this.cuenta, isBP, 1);

		assertNotNull(respuesta);
		assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
		assertEquals("No pudimos descargar tu resumen. Por favor, intentá nuevamente en unos minutos.",
				respuesta.getItemsMensajeRespuesta().get(0).getMensaje());
	}

	/**
	 * Obtener resumen descarga multiple varios error test.
	 *
	 * @param isBP
	 *     the is BP
	 * @throws BusinessException
	 *     the business exception
	 * @throws WSODException
	 *     the WSOD exception
	 */
	private void obtenerResumenDescargaMultipleVariosErrorTest(boolean isBP) throws BusinessException, WSODException {

		Mensaje mensaje = new Mensaje();
		mensaje.setCodigo(CodigoMensajeConstantes.ERROR_DESCARGA_MULTIPLE_VARIOS_INV);
		mensaje.setMensaje(
				"No pudimos descargar algunos de los resúmenes. Por favor, intentá nuevamente en unos minutos.");

		ReporteResumenMensualInversiones reporteResumenMensual = new ReporteResumenMensualInversiones();
		String bytes = "bytes";
		reporteResumenMensual.setBytes(bytes.getBytes());
		reporteResumenMensual.setNombre("Prueba.pdf");
		reporteResumenMensual.setTipoArchivo(TipoArchivoEnum.PDF);

		Mockito.when(ondemandDAO.obtenerReporteMensualInversiones(new ResumenMensualInversiones(), this.cuenta, isBP))
				.thenReturn(null);
		Mockito.when(mensajeDAO.obtenerMensajePorCodigo(CodigoMensajeConstantes.ERROR_DESCARGA_MULTIPLE_VARIOS_INV))
				.thenReturn(mensaje);

		Respuesta<ReporteResumenMensualInversiones> respuesta = resumenMensualInversionesBO
				.obtenerResumenDescargaMultiple(this.resumenesMensuales, this.cuenta, isBP, 2);

		assertNotNull(respuesta);
		assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
		assertEquals("No pudimos descargar algunos de los resúmenes. Por favor, intentá nuevamente en unos minutos.",
				respuesta.getItemsMensajeRespuesta().get(0).getMensaje());
	}

	/**
	 * Obtener resumen descarga simple test.
	 *
	 * @param isBP
	 *     the is BP
	 * @throws BusinessException
	 *     the business exception
	 * @throws WSODException
	 *     the WSOD exception
	 */
	private void obtenerResumenDescargaSimpleTest(boolean isBP) throws BusinessException, WSODException {

		ReporteResumenMensualInversiones reporteResumenMensual = new ReporteResumenMensualInversiones();
		String bytes = "bytes";
		reporteResumenMensual.setBytes(bytes.getBytes());
		reporteResumenMensual.setNombre("Prueba.pdf");
		reporteResumenMensual.setTipoArchivo(TipoArchivoEnum.PDF);

		Mockito.when(ondemandDAO.obtenerReporteMensualInversiones(this.resumenesMensuales.get(0), this.cuenta, isBP))
				.thenReturn(reporteResumenMensual);

		Respuesta<ReporteResumenMensualInversiones> respuesta = resumenMensualInversionesBO
				.obtenerResumenesPDF(this.resumenesMensuales, this.cuenta, isBP);

		assertNotNull(respuesta);
		assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
		assertEquals(TipoArchivoEnum.PDF, respuesta.getRespuesta().getTipoArchivo());
	}

	/**
	 * Obtener lista resumen OK test.
	 *
	 * @param isBP
	 *     the is BP
	 * @throws BusinessException
	 *     the business exception
	 * @throws WSODException
	 *     the WSOD exception
	 * @throws IllegalAccessException
	 *     the illegal access exception
	 */
	private void obtenerListaResumenOKTest(boolean isBP)
			throws BusinessException, WSODException, IllegalAccessException {

		Mockito.when(
				ondemandDAO.obtenerListaResumenInversiones(Matchers.any(ResumenParams.class), Matchers.anyBoolean()))
				.thenReturn(this.resumenesMensuales);
		FieldUtils.writeDeclaredField(resumenMensualInversionesBO, "cantidadMeses", "18", true);

		Respuesta<List<ResumenMensualInversiones>> respuesta = resumenMensualInversionesBO.obtenerListaResumen(this.cuenta,
				isBP);

		assertNotNull(respuesta);
		assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
	}

	/**
	 * Obtener lista resumen error test.
	 *
	 * @param isBP
	 *     the is BP
	 * @throws BusinessException
	 *     the business exception
	 * @throws WSODException
	 *     the WSOD exception
	 * @throws IllegalAccessException
	 *     the illegal access exception
	 */
	@SuppressWarnings("unchecked")
	private void obtenerListaResumenErrorTest(boolean isBP)
			throws BusinessException, WSODException, IllegalAccessException {

		Mensaje mensaje = new Mensaje();
		mensaje.setCodigo(CodigoMensajeConstantes.ERROR_LISTA_RESUMENES_INV);
		mensaje.setMensaje("No pudimos cargar tus resúmenes. Por favor, intentá nuevamente en unos minutos.");

		Mockito.when(
				ondemandDAO.obtenerListaResumenInversiones(Matchers.any(ResumenParams.class), Matchers.anyBoolean()))
				.thenThrow(WSODException.class);
		Mockito.when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.ERROR_LISTA_RESUMENES_INV))
				.thenReturn(mensaje);
		FieldUtils.writeDeclaredField(resumenMensualInversionesBO, "cantidadMeses", "18", true);

		Respuesta<List<ResumenMensualInversiones>> respuesta = resumenMensualInversionesBO.obtenerListaResumen(this.cuenta,
				isBP);

		assertNotNull(respuesta);
		assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
	}

	/**
	 * Obtener lista resumenes mensuales.
	 *
	 * @return the list
	 */
	private void obtenerListaResumenesMensuales() {
		this.resumenesMensuales = new ArrayList<ResumenMensualInversiones>();
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
	}
}
