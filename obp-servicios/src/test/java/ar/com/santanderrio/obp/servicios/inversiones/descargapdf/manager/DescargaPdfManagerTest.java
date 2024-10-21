package ar.com.santanderrio.obp.servicios.inversiones.descargapdf.manager;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.reporte.entities.TipoArchivoEnum;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SessionResumenCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.CuentaBancaPrivada;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ConsultaResumenCuentaBP;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ResumenMesualCuentaView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.inversiones.descargapdf.bo.DescargaPdfBO;
import ar.com.santanderrio.obp.servicios.inversiones.descargapdf.entities.ListadoPDF;
import ar.com.santanderrio.obp.servicios.inversiones.descargapdf.entities.TipoPDFEnum;
import ar.com.santanderrio.obp.servicios.inversiones.descargapdf.manager.impl.DescargaPdfManagerImpl;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.ComprobantesPorCuentaView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.ListaFechasComprobantes;
import ar.com.santanderrio.obp.servicios.ondemand.entities.WSODException;
import ar.com.santanderrio.obp.servicios.pagos.dao.IntervinientesDAO;
import ar.com.santanderrio.obp.servicios.pagos.entities.IntervinientesEntity;
import ar.com.santanderrio.obp.servicios.pagos.entities.IntervinientesOutEntity;
import ar.com.santanderrio.obp.servicios.resumen.entities.ReporteResumenMensualCuenta;
import ar.com.santanderrio.obp.servicios.resumen.entities.ResumenMensualCuenta;

@RunWith(MockitoJUnitRunner.class)
public class DescargaPdfManagerTest {

	@InjectMocks
	DescargaPdfManagerImpl descargaPdfManager;

	@Mock
	private SesionCliente sesionCliente;
	
    @Mock
    private SessionResumenCuenta sesionResumenCuenta;
    
    @Mock
    private DescargaPdfBO descargaPDFBO;
    
    @Mock
	private SesionParametros sessionParametros;
    
    @Mock
    private EstadisticaManager estadisticaManager;
    
    @Mock
    private MensajeBO mensajeBO;
    
    @Mock
    private IntervinientesDAO IntervinientesDAO;
    
    @Spy 
    @InjectMocks
    private RespuestaFactory respuestaFactory = new RespuestaFactory();
    
	@Before
	public void init() throws DAOException {
		
		IntervinientesOutEntity intervinientes = armarListaIntervinientesMock();
		Cliente cliente = armarCliente();

		when(IntervinientesDAO.obtenerIntervinientes(Matchers.any(Cuenta.class))).thenReturn(intervinientes);		
		when(sesionCliente.getCliente()).thenReturn(cliente);
		
	}

	@Test
	public void obtenerListaComprobantesOK() throws BusinessException, WSODException, DAOException {
		
		//When
		Respuesta<ListaFechasComprobantes> respuesta = new Respuesta<ListaFechasComprobantes>();
		Respuesta<List<ResumenMensualCuenta>> respuestaListaFechas = crearRespuestaResumenesMock();
		
		when(descargaPDFBO.obtenerListaComprobantes(Matchers.any(Cuenta.class), Matchers.any(TipoPDFEnum.class))).thenReturn(respuestaListaFechas);
		
		//Then
		respuesta = descargaPdfManager.obtenerListaComprobantes(TipoPDFEnum.FONDOS_COMUNES_INVERSION);
		
		//Expected
		Assert.assertNotNull(respuesta.getRespuesta());
		Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
		
	}
	
	
	@Test
	public void obtenerListaComprobantesOKTieneFechasEnSesion() throws BusinessException, WSODException {
		
		//When
		Respuesta<ListaFechasComprobantes> respuesta = new Respuesta<ListaFechasComprobantes>();

		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("Mensaje Error");
		
		when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS)).thenReturn(mensaje);
		when(sessionParametros.getListaFechasComprobantesTVBpriv()).thenReturn(obtenerListaFechasSesion());
		
		respuesta = descargaPdfManager.obtenerListaComprobantes(TipoPDFEnum.TITULOS_VALORES);
				
		Assert.assertNotNull(respuesta.getRespuesta());
		Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
	}

	
	@Test
	public void obtenerListaComprobantesOKNoHayFechas() throws BusinessException, WSODException, DAOException {
		
		//When
		Respuesta<ListaFechasComprobantes> respuesta = new Respuesta<ListaFechasComprobantes>();

		List<ResumenMensualCuenta> listaResumenesCuenta = new ArrayList<ResumenMensualCuenta>();
		Respuesta<List<ResumenMensualCuenta>> respuestaListaFechas = respuestaFactory.crearRespuestaOk(listaResumenesCuenta);
		
		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("Mensaje Warning");
		
		when(descargaPDFBO.obtenerListaComprobantes(Matchers.any(Cuenta.class), Matchers.any(TipoPDFEnum.class))).thenReturn(respuestaListaFechas);
		when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.WARNING_NO_TENES_COMPROBANTES_TV)).thenReturn(mensaje);

		//Then
		respuesta = descargaPdfManager.obtenerListaComprobantes(TipoPDFEnum.FONDOS_COMUNES_INVERSION);
		
		//Expected
		Assert.assertNotNull(respuesta.getRespuesta());
		Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
		
	}
	
	@Test
	public void obtenerListaComprobantesErrorBuscarResumenes() throws BusinessException, WSODException, DAOException {
		
		//When
		Respuesta<ListaFechasComprobantes> respuesta = new Respuesta<ListaFechasComprobantes>();

		Respuesta<List<ResumenMensualCuenta>> respuestaListaFechas = respuestaFactory.crearRespuestaError("", "", "");

		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("Mensaje Warning");
		
		when(descargaPDFBO.obtenerListaComprobantes(Matchers.any(Cuenta.class), Matchers.any(TipoPDFEnum.class))).thenReturn(respuestaListaFechas);
		when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS)).thenReturn(mensaje);

		//Then
		respuesta = descargaPdfManager.obtenerListaComprobantes(TipoPDFEnum.FONDOS_COMUNES_INVERSION);
		
		//Expected
		Assert.assertNotNull(respuesta.getRespuesta());
		Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
		
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void obtenerListaComprobantesBusinessException() throws BusinessException, WSODException, DAOException {
		
		//When
		Respuesta<ListaFechasComprobantes> respuesta = new Respuesta<ListaFechasComprobantes>();
		
		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("Mensaje Warning");

		when(descargaPDFBO.obtenerListaComprobantes(Matchers.any(Cuenta.class), Matchers.any(TipoPDFEnum.class))).thenThrow(BusinessException.class);
		when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS)).thenReturn(mensaje);

		//Then
		respuesta = descargaPdfManager.obtenerListaComprobantes(TipoPDFEnum.FONDOS_COMUNES_INVERSION);
		
		//Expected
		Assert.assertNotNull(respuesta.getRespuesta());
		Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
		
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void obtenerListaComprobantesWSODException() throws BusinessException, WSODException, DAOException {
		
		//When
		Respuesta<ListaFechasComprobantes> respuesta = new Respuesta<ListaFechasComprobantes>();
		
		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("Mensaje Warning");

		when(descargaPDFBO.obtenerListaComprobantes(Matchers.any(Cuenta.class), Matchers.any(TipoPDFEnum.class))).thenThrow(WSODException.class);
		when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS)).thenReturn(mensaje);

		//Then
		respuesta = descargaPdfManager.obtenerListaComprobantes(TipoPDFEnum.FONDOS_COMUNES_INVERSION);
		
		//Expected
		Assert.assertNotNull(respuesta.getRespuesta());
		Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
		
	}
	
	@Test
	public void obtenerListaComprobantesErrorGenericoListaVacia() throws BusinessException, WSODException {
		
		//When
		Respuesta<ListaFechasComprobantes> respuesta = new Respuesta<ListaFechasComprobantes>();
		Cliente cliente = armarClienteSinCuentas();

		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("Mensaje Error");
		
		when(sesionCliente.getCliente()).thenReturn(cliente);
		when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS)).thenReturn(mensaje);
		
		//Then
		respuesta = descargaPdfManager.obtenerListaComprobantes(TipoPDFEnum.FONDOS_COMUNES_INVERSION);
		
		//Expected
		Assert.assertNull(respuesta.getRespuesta());
		Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
		
	}
	
	
    @SuppressWarnings("unchecked")
	@Test
    public void obtenerPDFResumenCuentaBP() {
    
    	//When
    	ConsultaResumenCuentaBP consultaResumenCuentaBP = new ConsultaResumenCuentaBP();
    	consultaResumenCuentaBP.setNumeroCuenta("250-352350/8");
    	consultaResumenCuentaBP.setFechas(new String[] {"0", "1"});
    	
    	List<ResumenMensualCuenta> listaResumenesCuenta = crearListaResumenesMock();
    	Respuesta<ReporteResumenMensualCuenta> reporteMensual = reportePDFMock();
    	
    	when(sesionResumenCuenta.getResumenesBPByIds(Matchers.anyList(), Matchers.any(Cuenta.class))).thenReturn(listaResumenesCuenta);
    	when(descargaPDFBO.obtenerPDF(Matchers.any(ResumenMensualCuenta.class), Matchers.any(Cuenta.class), Matchers.any(TipoPDFEnum.class))).thenReturn(reporteMensual);
    	
    	//Then
    	Respuesta<ListadoPDF> respuesta = descargaPdfManager.obtenerPDF(consultaResumenCuentaBP, TipoPDFEnum.CUENTAS_BANCA_PRIVADA);
    	
    	//Expected
		Assert.assertNotNull(respuesta.getRespuesta());
		Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());	
    	
    }
    
    
    @Test
    public void obtenerPDFComprobanteTitulosValoresBP( ) {
    	
    	//When
    	ConsultaResumenCuentaBP consultaResumenCuentaBP = new ConsultaResumenCuentaBP();
    	consultaResumenCuentaBP.setNumeroCuenta("250-352350/8");
    	consultaResumenCuentaBP.setFechas(new String[] {"0", "1"});
    	
    	ListaFechasComprobantes listaFechasComprobantes = obtenerListaFechasSesion();
    	Respuesta<ReporteResumenMensualCuenta> reporteMensual = reportePDFMock();
    	
		when(sessionParametros.getListaFechasComprobantesTVBpriv()).thenReturn(listaFechasComprobantes);
    	when(descargaPDFBO.obtenerPDF(Matchers.any(ResumenMensualCuenta.class), Matchers.any(Cuenta.class), Matchers.any(TipoPDFEnum.class))).thenReturn(reporteMensual);
		
    	//Then
    	Respuesta<ListadoPDF> respuesta = descargaPdfManager.obtenerPDF(consultaResumenCuentaBP, TipoPDFEnum.TITULOS_VALORES);
    	
    	//Expected
		Assert.assertNotNull(respuesta.getRespuesta());
		Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }
    
    
    @Test
    public void obtenerPDFComprobanteTitulosValoresBPError( ) {
    	
    	//When
    	ConsultaResumenCuentaBP consultaResumenCuentaBP = new ConsultaResumenCuentaBP();
    	consultaResumenCuentaBP.setNumeroCuenta("250-352350/8");
    	consultaResumenCuentaBP.setFechas(new String[] {"0", "1"});
    	
    	ListaFechasComprobantes listaFechasComprobantes = obtenerListaFechasSesion();
    	Respuesta<ReporteResumenMensualCuenta> reporteMensual = respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, "");
    	
    	Mensaje mensaje = new Mensaje();
    	mensaje.setMensaje("ERROR EN LA DESCARGA");
    	
		when(sessionParametros.getListaFechasComprobantesTVBpriv()).thenReturn(listaFechasComprobantes);
    	when(descargaPDFBO.obtenerPDF(Matchers.any(ResumenMensualCuenta.class), Matchers.any(Cuenta.class), Matchers.any(TipoPDFEnum.class))).thenReturn(reporteMensual);
		when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.ERROR_TOTAL_DESCARGA_PDF_RESUMEN_CUENTA_BP)).thenReturn(mensaje);
    	
    	//Then
    	Respuesta<ListadoPDF> respuesta = descargaPdfManager.obtenerPDF(consultaResumenCuentaBP, TipoPDFEnum.TITULOS_VALORES);
    	
    	//Expected
		Assert.assertNull(respuesta.getRespuesta());
		Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
    }
    
    
    private Cliente armarClienteSinCuentas() {
		
		Cliente cliente = new Cliente();
		List<CuentaBancaPrivada> listaCuentasBancaPrivada = new ArrayList<CuentaBancaPrivada>();
		cliente.setCuentasBancaPrivada(listaCuentasBancaPrivada);
		return cliente;
	}
		
	private Respuesta<List<ResumenMensualCuenta>> crearRespuestaResumenesMock() {
		
		List<ResumenMensualCuenta> listaResumenesCuenta = new ArrayList<ResumenMensualCuenta>();
		
		ResumenMensualCuenta resumen1 = new ResumenMensualCuenta();
		resumen1.setId(new Long (0));
		resumen1.setFecha(new Date());
		listaResumenesCuenta.add(resumen1);
		
		ResumenMensualCuenta resumen2 = new ResumenMensualCuenta();
		resumen2.setId(new Long (1));
		resumen2.setFecha(new Date());
		listaResumenesCuenta.add(resumen2);
		
		ResumenMensualCuenta resumen3 = new ResumenMensualCuenta();
		resumen3.setId(new Long (2));
		resumen3.setFecha(new Date());
		listaResumenesCuenta.add(resumen3);
		
		return respuestaFactory.crearRespuestaOk(listaResumenesCuenta);
	}
    
	private List<ResumenMensualCuenta> crearListaResumenesMock() {
		
		List<ResumenMensualCuenta> listaResumenesCuenta = new ArrayList<ResumenMensualCuenta>();
		
		ResumenMensualCuenta resumen1 = new ResumenMensualCuenta();
		resumen1.setId(new Long (0));
		resumen1.setFecha(new Date());
		listaResumenesCuenta.add(resumen1);
		
		ResumenMensualCuenta resumen2 = new ResumenMensualCuenta();
		resumen2.setId(new Long (1));
		resumen2.setFecha(new Date());
		listaResumenesCuenta.add(resumen2);
		
		ResumenMensualCuenta resumen3 = new ResumenMensualCuenta();
		resumen3.setId(new Long (2));
		resumen3.setFecha(new Date());
		listaResumenesCuenta.add(resumen3);
		
		return listaResumenesCuenta;
	}
	
	private Respuesta<ReporteResumenMensualCuenta> reportePDFMock() {
		
		ReporteResumenMensualCuenta reporteResumenMensualCuenta = new ReporteResumenMensualCuenta();
		reporteResumenMensualCuenta.setBytes("58598498984982".getBytes());
		reporteResumenMensualCuenta.setTipoArchivo(TipoArchivoEnum.PDF);
		reporteResumenMensualCuenta.setNombre("Archivo");
		
		return respuestaFactory.crearRespuestaOk(reporteResumenMensualCuenta);	
		
	}
	
	private Cliente armarCliente() {
		
		Cliente cliente = new Cliente();
		
		List<CuentaBancaPrivada> listaCuentasBancaPrivada = new ArrayList<CuentaBancaPrivada>();
		CuentaBancaPrivada cuentaBancaPrivada = new CuentaBancaPrivada();
		Cuenta cuentaTitulo = new Cuenta();
		cuentaTitulo.setNroCuentaProducto("000003523508");
		Cuenta cuentaOperativa = new Cuenta();
		cuentaOperativa.setNroCuentaProducto("000003523508");
		cuentaOperativa.setNroSucursal("250");
		cuentaOperativa.setTipoCuenta(TipoCuenta.CUENTA_UNICA_PESOS.getCodigo().toString());
		cuentaBancaPrivada.setCuentaOperativa(cuentaOperativa);
		cuentaBancaPrivada.setCuentaTitulo(cuentaTitulo);
		
		listaCuentasBancaPrivada.add(cuentaBancaPrivada);
		listaCuentasBancaPrivada.add(cuentaBancaPrivada);
		listaCuentasBancaPrivada.add(cuentaBancaPrivada);
		
		cliente.setCuentasBancaPrivada(listaCuentasBancaPrivada);
		
		return cliente;
		
	}
	
	
	private ListaFechasComprobantes obtenerListaFechasSesion() {
		
		ListaFechasComprobantes listaFechasComprobantes = new ListaFechasComprobantes();
		List<ComprobantesPorCuentaView> cuentas = new ArrayList<ComprobantesPorCuentaView>();
		
		ComprobantesPorCuentaView comprobantesPorCuentaView = new ComprobantesPorCuentaView();
		comprobantesPorCuentaView.setEstado(EstadoRespuesta.OK);
		comprobantesPorCuentaView.setNumeroCuenta("250-352350/8");
		
		List<ResumenMesualCuentaView> resumenes = new ArrayList<ResumenMesualCuentaView>();
		ResumenMesualCuentaView resumen = new ResumenMesualCuentaView();
		resumen.setAnio("2019");
		resumen.setDia("23");
		resumen.setMes("Enero");
		resumen.setId(new Long(0));
		resumenes.add(resumen);
		
		ResumenMesualCuentaView resumen1 = new ResumenMesualCuentaView();
		resumen1.setAnio("2019");
		resumen1.setDia("23");
		resumen1.setMes("Febrero");
		resumen1.setId(new Long(1));
		resumenes.add(resumen1);
		
		ResumenMesualCuentaView resumen2 = new ResumenMesualCuentaView();
		resumen2.setAnio("2019");
		resumen2.setDia("23");
		resumen2.setMes("Marzo");
		resumen2.setId(new Long(2));
		resumenes.add(resumen2);
		
		ResumenMesualCuentaView resumen3 = new ResumenMesualCuentaView();
		resumen3.setAnio("2019");
		resumen3.setDia("23");
		resumen3.setMes("Abril");
		resumen3.setId(new Long(3));
		resumenes.add(resumen3);

		ResumenMesualCuentaView resumen4 = new ResumenMesualCuentaView();
		resumen4.setAnio("2019");
		resumen4.setDia("23");
		resumen4.setMes("Mayo");
		resumen4.setId(new Long(3));
		resumenes.add(resumen4);
		
		ResumenMesualCuentaView resumen5 = new ResumenMesualCuentaView();
		resumen5.setAnio("2019");
		resumen5.setDia("23");
		resumen5.setMes("Junio");
		resumen5.setId(new Long(3));
		resumenes.add(resumen5);
		
		ResumenMesualCuentaView resumen6 = new ResumenMesualCuentaView();
		resumen6.setAnio("2019");
		resumen6.setDia("23");
		resumen6.setMes("Julio");
		resumen6.setId(new Long(3));
		resumenes.add(resumen6);
		
		ResumenMesualCuentaView resumen7 = new ResumenMesualCuentaView();
		resumen7.setAnio("2019");
		resumen7.setDia("23");
		resumen7.setMes("Agosto");
		resumen7.setId(new Long(3));
		resumenes.add(resumen7);
		
		ResumenMesualCuentaView resumen8 = new ResumenMesualCuentaView();
		resumen8.setAnio("2019");
		resumen8.setDia("23");
		resumen8.setMes("Septiembre");
		resumen8.setId(new Long(3));
		resumenes.add(resumen8);
		
		ResumenMesualCuentaView resumen9 = new ResumenMesualCuentaView();
		resumen9.setAnio("2019");
		resumen9.setDia("23");
		resumen9.setMes("Octubre");
		resumen9.setId(new Long(3));
		resumenes.add(resumen9);
		
		ResumenMesualCuentaView resumen10 = new ResumenMesualCuentaView();
		resumen10.setAnio("2019");
		resumen10.setDia("23");
		resumen10.setMes("Noviembre");
		resumen10.setId(new Long(3));
		resumenes.add(resumen10);
		
		ResumenMesualCuentaView resumen11 = new ResumenMesualCuentaView();
		resumen11.setAnio("2019");
		resumen11.setDia("23");
		resumen11.setMes("Diciembre");
		resumen11.setId(new Long(3));
		resumenes.add(resumen11);
		
		comprobantesPorCuentaView.setResumenes(resumenes);
		cuentas.add(comprobantesPorCuentaView);
		listaFechasComprobantes.setCuentas(cuentas);
		
		return listaFechasComprobantes;
	}
	
	private IntervinientesOutEntity armarListaIntervinientesMock() {
		
		IntervinientesOutEntity intervinientesOutEntity = new IntervinientesOutEntity();
		
		List<IntervinientesEntity> listaIntervinientes = new ArrayList<IntervinientesEntity>();
		IntervinientesEntity interviniente = new IntervinientesEntity();
		interviniente.setNombre("Pepe");
		interviniente.setApellido("Tarjota");
		interviniente.setOrdenParticipacion("001");
		interviniente.setFechaBajaRel("31129999");
		listaIntervinientes.add(interviniente);
		
		IntervinientesEntity interviniente2 = new IntervinientesEntity();
		interviniente2.setNombre("Pipo");
		interviniente2.setApellido("Gorosito");
		interviniente2.setOrdenParticipacion("002");
		interviniente2.setFechaBajaRel("31129999");
		listaIntervinientes.add(interviniente2);
		
		intervinientesOutEntity.setListaRepeticiones(listaIntervinientes);
		
		return intervinientesOutEntity;
	}
    
}
