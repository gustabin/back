package ar.com.santanderrio.obp.servicios.inversiones.titulos.bo;

import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.context.ContextHolder;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ContadorIntentos;
import ar.com.santanderrio.obp.servicios.comun.excel.dao.ReporteDAO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasAdhesionDebitoView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.inversiones.comun.InversionWSHelper;
import ar.com.santanderrio.obp.servicios.inversiones.comun.TipoBancaEnum;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.TenenciaTitulosCuentaDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.TenenciaTitulosDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.CuentaTituloView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.excel.CuentasTitulosExcelGeneral;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.bo.TitulosBOImpl;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dao.TitulosDAO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dao.TitulosMercadoCanalDAO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dto.ConsultaOperacionesDTO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dto.ConsultarOrdenesOutDTO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dto.LicitacionVigenteDTO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dto.LicitacionesVigentesOutDTO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dto.NuevaLicitacionDTOResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.CanalTramoCtaTitulo;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ConsultaOperaciones;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ConsultaOperacionesRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ConsultarOrdenLicitacion;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ConsultarOrdenResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ConsultarTenenciaRenovable;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ConsultarTenenciaRenovableResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.DatosConsultaOperacionesResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.DatosConsultarOrdenResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.DatosConsultarTenenciaRenovableResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.DatosObtenerCanalTramoResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.DatosObtenerCuentasTitulosResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ObtenerCanalTramo;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ObtenerCanalTramoResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ObtenerCuentasTitulos;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ObtenerCuentasTitulosResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ReversarOrdenEntity;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ReversarOrdenResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.TipoPliego;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.ConfigurarLicitacionOutView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.ConfigurarLicitacionViewReq;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.NuevaLicitacionViewReq;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.ReversaRequestView;
import ar.com.santanderrio.obp.servicios.pagos.entities.Interviniente;


/**
 * The Class TitulosBOTest.
 */

@RunWith(MockitoJUnitRunner.class)
public class TitulosBOTest {

    @InjectMocks
    private TitulosBOImpl tituloBOImp;

    @Mock
    private TitulosDAO licitacionesDAO;

    @Mock
    private TitulosMercadoCanalDAO titulosMercadoCanalDAO;

    /** The session parametros. */
    @Mock
    private SesionParametros sessionParametros;

    /** The respuesta factory. */
    @Mock
    private RespuestaFactory respuestaFactory;
    
    @Mock
    private InversionWSHelper inversionWSHelper;
    
    @Mock
    private SesionCliente sesionCliente;
    
    @Mock
    private ReporteDAO reporteDAO;
    
	
	@SuppressWarnings("unchecked")
	@Test
	public void consultarOrdenes() throws DAOException{
		
		Cliente cliente = mock(Cliente.class);
		TipoBancaEnum banca = TipoBancaEnum.BANCA_PERSONAL;
		
		ConsultarOrdenResponse response = new ConsultarOrdenResponse();
		
		List<DatosConsultarOrdenResponse> listaDatos = new ArrayList<DatosConsultarOrdenResponse>();
		for(int i = 0; i<4;i++){
		DatosConsultarOrdenResponse datos = new DatosConsultarOrdenResponse();
		datos.setDescripcionEspecie("Descripcion Especie"+i);
		datos.setFechaOrden("/Date(1502993271000)/");
		datos.setFechaAdjudicacion("/Date(1502993271000)/");
		datos.setFechaCierre("/Date(1502993271000)/");
		datos.setFechaLiquidación("/Date(1502993271000)/");
		datos.setCuentaTitulos("1394620"+i);
		datos.setNumeroOrden("numero ordens"+i);
		datos.setEstado("estado"+i);
		datos.setCantidad("100100"+i);
		datos.setTipoCambio("12.45");
		datos.setDescripcionMoneda("ARS");
		
		if (i==0){
			datos.setMonto("0");
		}else{
			datos.setMonto("12");
		} 
		 
		if (i ==0){
		 	datos.setTramoCompetitivo("S");
		} else{
		 	datos.setTramoCompetitivo("No");
		} 		
		datos.setPrecio(i+"90009,55");
		if(i==0){
			datos.setMontoADebitar("2000000"+i);
		}else{
		datos.setMontoADebitar("0");
		}
			switch (i) {
			case 0:
				datos.setTipoCuentaOperativa("0");
				datos.setMoneda("USD");
				datos.setMonedaEspecie("USD");
				datos.setTipoPrecio("TIR");
				break;
			case 1:
				datos.setTipoCuentaOperativa("2");
				datos.setMoneda("USD");
				datos.setMonedaEspecie("ARS");
				datos.setTipoPrecio("PRECIO");
				break;
			case 2:
				datos.setTipoCuentaOperativa("0");
				datos.setMoneda("ARS");
				datos.setMonedaEspecie("USD");
				datos.setTipoPrecio("PRECIO");
				break;
			case 3:
				datos.setTipoCuentaOperativa("4");
				datos.setMoneda("ARS");
				datos.setMonedaEspecie("ARS");
				datos.setTipoPrecio("TIR");
				break;
			}
			switch (i) {
			case 0:
				datos.setTipoCuenta("O");
				break;
			case 1:
				datos.setTipoCuenta("C");
				break;
			case 2:
				datos.setTipoCuenta("0");
				break;
			case 3:
				datos.setTipoCuenta("O");
				break;
			}
		datos.setSucursal("1"+i);
		if(i==2){
			datos.setCuentaOperativa(null);
		}else{		
			datos.setCuentaOperativa("357774"+i);
		}
		datos.setCuentaCustodia("357711"+i);
		datos.setCantidadAdjudicada("2000"+i);
		datos.setPrecioAdjudicado("3000"+i);
		datos.setMontoComision("2");
		datos.setMontoImpuesto("3");
		datos.setFechaDebProv("/Date(1502993271000)/");
		if(i==0){
			datos.setEstado("Simulada");
		}else{
			datos.setEstado("Confirmada");
		}
		datos.setPermiteReversa("S");
		datos.setCodMonedaEspecieDestino("ARS");
		
		listaDatos.add(datos);
		}
		response.setDatos(listaDatos);
		Mockito.when(licitacionesDAO.consultarOrdenLicitacion((Matchers.any(ConsultarOrdenLicitacion.class)))).thenReturn(response);
		
		Respuesta<ConsultarOrdenesOutDTO> respuestaOk = new Respuesta<ConsultarOrdenesOutDTO>(); 
		respuestaOk.setEstadoRespuesta(EstadoRespuesta.OK);
		
		 Mockito.when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class), Matchers.any(ConsultarOrdenesOutDTO.class)))
         .thenReturn(respuestaOk);
		 
		 String firma = "laFirma";
		 Mockito.when(inversionWSHelper.getDatosFirmados(Matchers.anyString())).thenReturn(firma);
		 
		 Respuesta<ConsultarOrdenesOutDTO> respuesta = tituloBOImp.consultarLicitaciones(cliente, banca, Boolean.FALSE);
		 
		 Assert.assertNotNull(respuesta);
		 
	}
	
	@Test
	public void consultarOrdenesDAOException() throws DAOException{
		
		Cliente cliente = mock(Cliente.class);
		TipoBancaEnum banca = TipoBancaEnum.BANCA_PERSONAL;
		
		ConsultarOrdenResponse response = new ConsultarOrdenResponse();
		
		List<DatosConsultarOrdenResponse> listaDatos = new ArrayList<DatosConsultarOrdenResponse>();
		for(int i = 0; i<3;i++){
		DatosConsultarOrdenResponse datos = new DatosConsultarOrdenResponse();
		datos.setDescripcionEspecie("Descripcion Especie"+i);
		datos.setFechaOrden("/Date(1502993271000)/");
		datos.setFechaAdjudicacion("/Date(1502993271000)/");
		datos.setFechaCierre("/Date(1502993271000)/");
		datos.setFechaLiquidación("/Date(1502993271000)/");
		datos.setCuentaTitulos("1394620"+i);
		datos.setNumeroOrden("numero ordens"+i);
		datos.setEstado("estado"+i);
		datos.setCantidad("100"+i);
		datos.setMoneda("moneda"+i);
		datos.setMonedaEspecie("moneda especie"+i);
		datos.setMonto("0");
		datos.setTramoCompetitivo("tramo competitivo"+i);
		datos.setPrecio("precio"+i);
		datos.setMontoADebitar("2000000"+i);
		datos.setTipoCuenta("0");
		datos.setTipoCuentaOperativa("2");
		datos.setSucursal("000"+i);
		datos.setCuentaOperativa("357774"+i);
		datos.setCuentaCustodia("357711"+i);
		datos.setCantidadAdjudicada("2000"+i);
		datos.setPrecioAdjudicado("3000"+i);
		datos.setMontoComision("0");
		datos.setMontoImpuesto("null");
		datos.setEstado("Confirmada");
		listaDatos.add(datos);
		}
		response.setDatos(listaDatos);
		
		Mockito.when(licitacionesDAO.consultarOrdenLicitacion((Matchers.any(ConsultarOrdenLicitacion.class)))).thenThrow(new DAOException());
		
		Respuesta<Object> rtaDetalle = new Respuesta<Object>();
        rtaDetalle.setEstadoRespuesta(EstadoRespuesta.ERROR);
        ItemMensajeRespuesta imr = new ItemMensajeRespuesta();
        imr.setTipoError(TipoError.ERROR_GENERICO.getDescripcion());
        rtaDetalle.add(imr);
		 
		 Mockito.when(
	                respuestaFactory.crearRespuestaError(Matchers.any(String.class), Matchers.eq(TipoError.ERROR_GENERICO),
	                        Matchers.eq(
	                                CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS))).thenReturn(rtaDetalle);
		 
		 String firma = "laFirma";
		 Mockito.when(inversionWSHelper.getDatosFirmados(Matchers.anyString())).thenReturn(firma);
		 
		 Respuesta<ConsultarOrdenesOutDTO> respuesta = tituloBOImp.consultarLicitaciones(cliente, banca, Boolean.FALSE);
		 
		 Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
		 Assert.assertNotNull(respuesta);
		 
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void consultarOrdenesBusinessException() throws DAOException{
		
		Cliente cliente = mock(Cliente.class);
		TipoBancaEnum banca = TipoBancaEnum.BANCA_PERSONAL;
		
		ConsultarOrdenResponse response = new ConsultarOrdenResponse();
		
		List<DatosConsultarOrdenResponse> listaDatos = new ArrayList<DatosConsultarOrdenResponse>();
		for(int i = 0; i<3;i++){
		DatosConsultarOrdenResponse datos = new DatosConsultarOrdenResponse();
		datos.setDescripcionEspecie("Descripcion Especie"+i);
		datos.setFechaOrden("/Date(1502993271000)/");
		datos.setFechaAdjudicacion("/Date(1502993271000)/");
		datos.setFechaCierre("/Date(1502993271000)/");
		datos.setFechaLiquidación("/Date(1502993271000)/");
		datos.setCuentaTitulos("1394620"+i);
		datos.setNumeroOrden("numero ordens"+i);
		datos.setEstado("estado"+i);
		datos.setCantidad("100"+i);
		datos.setMoneda("moneda"+i);
		datos.setMonedaEspecie("moneda especie"+i);
		datos.setMonto("0");
		datos.setTramoCompetitivo("tramo competitivo"+i);
		datos.setPrecio("precio"+i);
		datos.setMontoADebitar("2000000"+i);
		datos.setTipoCuenta("0");
		datos.setTipoCuentaOperativa("2");
		datos.setSucursal("000"+i);
		datos.setCuentaOperativa("357774"+i);
		datos.setCuentaCustodia("357711"+i);
		datos.setCantidadAdjudicada("2000"+i);
		datos.setPrecioAdjudicado("3000"+i);
		datos.setMontoComision("0");
		datos.setMontoImpuesto("null");
		datos.setEstado("Confirmada");
		listaDatos.add(datos);
		}
		response.setDatos(listaDatos);
		
		Mockito.when(licitacionesDAO.consultarOrdenLicitacion((Matchers.any(ConsultarOrdenLicitacion.class)))).thenThrow(new DAOException());
		
		Respuesta<Object> rtaDetalle = new Respuesta<Object>();
        rtaDetalle.setEstadoRespuesta(EstadoRespuesta.ERROR);
        ItemMensajeRespuesta imr = new ItemMensajeRespuesta();
        imr.setTipoError(TipoError.ERROR_GENERICO.getDescripcion());
        rtaDetalle.add(imr);
		 
		 Mockito.when(
	                respuestaFactory.crearRespuestaError(Matchers.any(String.class), Matchers.eq(TipoError.ERROR_GENERICO),
	                        Matchers.eq(
	                                CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS))).thenReturn(rtaDetalle);
			 
		 String firma = "laFirma";
		 Mockito.when(inversionWSHelper.getDatosFirmados(Matchers.anyString())).thenThrow(new DAOException());
		 
		 Respuesta<ConsultarOrdenesOutDTO> respuesta = tituloBOImp.consultarLicitaciones(cliente, banca, Boolean.FALSE);
		 
		 Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
		 Assert.assertNotNull(respuesta);
		 
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void reversarOrden() throws DAOException{
		
		Cliente cliente = mock(Cliente.class);
		
		ReversaRequestView request = new ReversaRequestView();
		request.setBanca("BR");
		request.setNumOrden("123456");
		 ReversarOrdenResponse response = new ReversarOrdenResponse();
		 response.setCodigo("0");
		 SesionParametros parametros = new SesionParametros();
		 ContadorIntentos intentos = new ContadorIntentos();
		 parametros.setContador(intentos);
		 intentos.permiteReintentar();
		 Mockito.when(sessionParametros.getContador()).thenReturn(parametros.getContador());
		
		Mockito.when(licitacionesDAO.reversarOrdenLicitacion(Matchers.any(ReversarOrdenEntity.class))).thenReturn(response);
		
		Respuesta<ReversarOrdenResponse> respuestaOk= new Respuesta<ReversarOrdenResponse>();
		respuestaOk.setEstadoRespuesta(EstadoRespuesta.OK);
		 
		 Mockito.when(respuestaFactory.crearRespuestaOk(Matchers.any(ReversarOrdenResponse.class),
				 Matchers.any(String.class), Matchers.any(String.class))).thenReturn(respuestaOk);
		
		Respuesta<ReversarOrdenResponse> respuesta = tituloBOImp.reversarOrdenLicitacion(request, cliente);
		Assert.assertNotNull(respuesta);


	}
	
	
	/** nuevaLicitacion_OK
	 * 
	 * @throws DAOException
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void nuevaLicitacion_OK() throws DAOException {

		Cliente cliente = mock(Cliente.class);

		TipoPliego tipoPliego = new TipoPliego();
		tipoPliego.setDescripcion("descripcion Pliego");

		DatosObtenerCanalTramoResponse datosObtenerCanalTramoResponse = new DatosObtenerCanalTramoResponse();
		datosObtenerCanalTramoResponse.setCanal(4);
		datosObtenerCanalTramoResponse.setCodigoEspecie("15224");
		datosObtenerCanalTramoResponse.setCodigoPliego(1259);
		datosObtenerCanalTramoResponse.setCodigoTramo(1713);
		datosObtenerCanalTramoResponse.setCodigoTramoCanal(4645);
		datosObtenerCanalTramoResponse.setCorreoElectronico("asd@asd.com");
		datosObtenerCanalTramoResponse.setDescripcionEspecie("LETRAS DEL TESORO EN U$S 26/01/18");
		datosObtenerCanalTramoResponse.setDescripcionMoneda("DOLAR BILLETE");
		datosObtenerCanalTramoResponse.setDescripcionPliego("LETRAS DEL TESORO EN U$S 26/01/18-NC-USD");
		datosObtenerCanalTramoResponse.setEnable(false);
		datosObtenerCanalTramoResponse.setFechaAdjudicacion("/Date(1509591600000)/");
		datosObtenerCanalTramoResponse.setFechaHoraInicio("/Date(1509591600000)/");
		datosObtenerCanalTramoResponse.setFechaHoraCierre("/Date(1509591600000)/");
		datosObtenerCanalTramoResponse.setFechaHoraReversa("/Date(1509591600000)/");
		datosObtenerCanalTramoResponse.setFechaLiquidacionTitulos("/Date(1509591600000)/");
		datosObtenerCanalTramoResponse.setFechaLiquidacionValores("/Date(1509591600000)/");
		datosObtenerCanalTramoResponse
				.setLeyendaLegal("Pliego exclusivo para personal juridicas. Y pesonas humanas.....");
		datosObtenerCanalTramoResponse.setMoneda("USD");
		datosObtenerCanalTramoResponse.setMonedaEspecie("USD");
		datosObtenerCanalTramoResponse.setMontoIncrement(0.0);
		datosObtenerCanalTramoResponse.setMontoMax(0.0);
		datosObtenerCanalTramoResponse.setMontoMin(0.0);
		datosObtenerCanalTramoResponse.setNombreArchivoPdf("nombreArchivoPdf.pdf");
		datosObtenerCanalTramoResponse.setNominalIncrement(1.0);
		datosObtenerCanalTramoResponse.setNominalMax(0.0);
		datosObtenerCanalTramoResponse.setNominalMin(1000.0);
		datosObtenerCanalTramoResponse.setPrecio(1.0);
		//datosObtenerCanalTramoResponse.setPrecioIncrement(0.0);
		datosObtenerCanalTramoResponse.setPrecioMax(0.0);
		datosObtenerCanalTramoResponse.setPrecioMin("0.0");
		datosObtenerCanalTramoResponse.setPrecioRescate(0.0);
		datosObtenerCanalTramoResponse.setRelacionDerechosCanje("0");
		datosObtenerCanalTramoResponse.setSubcanal(99);
		datosObtenerCanalTramoResponse.setTipoCambio(1.0);
		datosObtenerCanalTramoResponse.setTipoCuenta("O");
		datosObtenerCanalTramoResponse.setTipoOferta("C");
		datosObtenerCanalTramoResponse.setTipoPersona("A");
		datosObtenerCanalTramoResponse.setTipoPliego(tipoPliego);
		datosObtenerCanalTramoResponse.setTipoPrecio("PRECIO");
		datosObtenerCanalTramoResponse.setTramoCompetitivo("N");
		datosObtenerCanalTramoResponse.setValidaDerechos("N");
		datosObtenerCanalTramoResponse.setNombreArchivoPdf("Nombre_archivo_pdf");
		datosObtenerCanalTramoResponse.setTipoCambio(Double.valueOf("12345.1234"));
		datosObtenerCanalTramoResponse.setCodTipoInstrumento("PUB");
		datosObtenerCanalTramoResponse.setDescTipoInstrumento("Publicas");
		
		
		CanalTramoCtaTitulo canalTramoCtaTitulo = new CanalTramoCtaTitulo();		
		canalTramoCtaTitulo.setCuentaTitulo(1234567);
		canalTramoCtaTitulo.setCantidad(2);
		canalTramoCtaTitulo.setCantidadMaxima(2);
		
		List<CanalTramoCtaTitulo> cuentasTitulos = new ArrayList<CanalTramoCtaTitulo>();
		cuentasTitulos.add(canalTramoCtaTitulo);
		datosObtenerCanalTramoResponse.setCuentasTitulo(cuentasTitulos);
		

		List<DatosObtenerCanalTramoResponse> datos = new ArrayList<DatosObtenerCanalTramoResponse>();
		datos.add(datosObtenerCanalTramoResponse);

		ObtenerCanalTramoResponse outEntity = new ObtenerCanalTramoResponse();
		outEntity.setDatos(datos);

		Mockito.when(licitacionesDAO.obtenerLicitaciones(Matchers.any(ObtenerCanalTramo.class))).thenReturn(outEntity);

		List<CuentaTituloView> cuentasTitulo = new ArrayList<CuentaTituloView>();
		CuentaTituloView ctaTit = new CuentaTituloView();
		ctaTit.setNroCuenta("1234567");

		
		List<Interviniente> intervinientes = new ArrayList<Interviniente>();
		 
		 Interviniente Interviniente1 = new Interviniente();
		 Interviniente1.setApellido("RODRIGUEZ CASATTI");
		 Interviniente1.setNombre("FALCO HULLEN");
		 Interviniente1.setOrdenParticipacion("001");		 
		 Interviniente Interviniente2 = new Interviniente();
		 Interviniente2.setApellido("AGUILERA OLMEDO");
		 Interviniente2.setNombre("SOLEDAD ALBA");
		 Interviniente2.setOrdenParticipacion("002");		 		 
		 intervinientes.add(Interviniente1);
		 intervinientes.add(Interviniente2);
		 
		ctaTit.setIntervinientes(intervinientes);		
		cuentasTitulo.add(ctaTit);

		NuevaLicitacionViewReq request = new NuevaLicitacionViewReq();
		request.setCuentasTitulo(cuentasTitulo);
		

		Respuesta<LicitacionesVigentesOutDTO> respuestaOk = new Respuesta<LicitacionesVigentesOutDTO>();
		respuestaOk.setEstadoRespuesta(EstadoRespuesta.OK);
		LicitacionesVigentesOutDTO rtaLicitacionesVigentes = new LicitacionesVigentesOutDTO();
		LicitacionVigenteDTO licitacionVigente = new LicitacionVigenteDTO();
		licitacionVigente.setTipoCambio("1234567");
		licitacionVigente.setCuentasTitulo(cuentasTitulos);
		rtaLicitacionesVigentes.getLicitacionesVigentesList().add(licitacionVigente);
		
		
		respuestaOk.setRespuesta(rtaLicitacionesVigentes);

		Mockito.when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class),
				Matchers.any(LicitacionesVigentesOutDTO.class))).thenReturn(respuestaOk);

		DatosObtenerCuentasTitulosResponse datosObtenerCuentasTitulosResponse = new DatosObtenerCuentasTitulosResponse();
		datosObtenerCuentasTitulosResponse.setCuentaTitulos(1234567);

		List<DatosObtenerCuentasTitulosResponse> listaDatos = new ArrayList<DatosObtenerCuentasTitulosResponse>();
		listaDatos.add(datosObtenerCuentasTitulosResponse);

		ObtenerCuentasTitulosResponse obtenerCuentasTitulosResponse = new ObtenerCuentasTitulosResponse();
		obtenerCuentasTitulosResponse.setDatos(listaDatos);

		Mockito.when(licitacionesDAO.obtenerCuentasTitulos(Matchers.any(ObtenerCuentasTitulos.class)))
				.thenReturn(obtenerCuentasTitulosResponse);

		Mockito.when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class),
				Matchers.any(NuevaLicitacionDTOResponse.class))).thenReturn(respuestaOk);
		
		
		List<CuentasAdhesionDebitoView> cuentasDebitoPesos = new ArrayList<CuentasAdhesionDebitoView>();
				cuentasDebitoPesos.add(new CuentasAdhesionDebitoView());

		List<CuentasAdhesionDebitoView> cuentasDebitoDolares = new ArrayList<CuentasAdhesionDebitoView>();
				cuentasDebitoDolares.add(new CuentasAdhesionDebitoView());
		

		Respuesta<NuevaLicitacionDTOResponse> respuesta = tituloBOImp.nuevaLicitacion(cliente, request, cuentasDebitoPesos, cuentasDebitoDolares);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
		Assert.assertNotNull(respuesta);

	}
	
	@Test
	@Ignore
	public void reversarOrdenError() throws DAOException{
		
		Cliente cliente = mock(Cliente.class);
		
		ReversaRequestView request = new ReversaRequestView();
		request.setBanca("BR");
		request.setNumOrden("123456");
		 ReversarOrdenResponse response = new ReversarOrdenResponse();
		 response.setCodigo("1");
		 SesionParametros parametros = new SesionParametros();
		 ContadorIntentos intentos = new ContadorIntentos();
		 intentos.permiteReintentar();
		 intentos.permiteReintentar();
		 intentos.permiteReintentar();
		 intentos.permiteReintentar();
		 parametros.setContador(intentos);

		 Mockito.when(sessionParametros.getContador()).thenReturn(parametros.getContador());
		
		Mockito.when(licitacionesDAO.reversarOrdenLicitacion(Matchers.any(ReversarOrdenEntity.class))).thenThrow(new DAOException());
		
		Respuesta<Object> rtaDetalle = new Respuesta<Object>();
        rtaDetalle.setEstadoRespuesta(EstadoRespuesta.ERROR);
        ItemMensajeRespuesta imr = new ItemMensajeRespuesta();
        imr.setTipoError(TipoError.ERROR_GENERICO.getDescripcion());
        rtaDetalle.add(imr);
		 
		 Mockito.when(
	                respuestaFactory.crearRespuestaError(Matchers.any(String.class), Matchers.eq(TipoError.ERROR_REINTENTOS_AGOTADOS),
	                        Matchers.eq(
	                                CodigoMensajeConstantes.ERROR_REVERSA_ORDEN_LICITACION),Matchers.any(String.class))).thenReturn(rtaDetalle);
		 
		 Respuesta<ReversarOrdenResponse> respuesta = tituloBOImp.reversarOrdenLicitacion(request, cliente);
		 Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
		 Assert.assertNotNull(respuesta);		 
	}
	
	/**
	 * nuevaLicitacion_ERROR
	 * 
	 * @throws DAOException
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void nuevaLicitacion_ERROR() throws DAOException {

		Cliente cliente = mock(Cliente.class);

		TipoPliego tipoPliego = new TipoPliego();
		tipoPliego.setDescripcion("descripcion Pliego");

		DatosObtenerCanalTramoResponse datosObtenerCanalTramoResponse = new DatosObtenerCanalTramoResponse();
		datosObtenerCanalTramoResponse.setCanal(4);
		datosObtenerCanalTramoResponse.setCodTipoInstrumento("PUB");
		datosObtenerCanalTramoResponse.setCodigoEspecie("15224");
		datosObtenerCanalTramoResponse.setCodigoPliego(1259);
		datosObtenerCanalTramoResponse.setCodigoTramo(1713);
		datosObtenerCanalTramoResponse.setCodigoTramoCanal(4645);
		datosObtenerCanalTramoResponse.setCorreoElectronico("asd@asd.com");
		datosObtenerCanalTramoResponse.setDescripcionEspecie("LETRAS DEL TESORO EN U$S 26/01/18");
		datosObtenerCanalTramoResponse.setDescripcionMoneda("DOLAR BILLETE");
		datosObtenerCanalTramoResponse.setDescripcionPliego("LETRAS DEL TESORO EN U$S 26/01/18-NC-USD");
		datosObtenerCanalTramoResponse.setEnable(false);
		datosObtenerCanalTramoResponse.setFechaAdjudicacion("/Date(1509591600000)/");
		datosObtenerCanalTramoResponse.setFechaHoraInicio("/Date(1509591600000)/");
		datosObtenerCanalTramoResponse.setFechaHoraCierre("/Date(1509591600000)/");
		datosObtenerCanalTramoResponse.setFechaHoraReversa("/Date(1509591600000)/");
		datosObtenerCanalTramoResponse.setFechaLiquidacionTitulos("/Date(1509591600000)/");
		datosObtenerCanalTramoResponse.setFechaLiquidacionValores("/Date(1509591600000)/");
		datosObtenerCanalTramoResponse
				.setLeyendaLegal("Pliego exclusivo para personal juridicas. Y pesonas humanas.....");
		datosObtenerCanalTramoResponse.setMoneda("USD");
		datosObtenerCanalTramoResponse.setMonedaEspecie("USD");
		datosObtenerCanalTramoResponse.setMontoIncrement(0.0);
		datosObtenerCanalTramoResponse.setMontoMax(0.0);
		datosObtenerCanalTramoResponse.setMontoMin(0.0);
		datosObtenerCanalTramoResponse.setNombreArchivoPdf("nombreArchivoPdf.pdf");
		datosObtenerCanalTramoResponse.setNominalIncrement(1.0);
		datosObtenerCanalTramoResponse.setNominalMax(0.0);
		datosObtenerCanalTramoResponse.setNominalMin(1000.0);
		datosObtenerCanalTramoResponse.setPrecio(1.0);
		//datosObtenerCanalTramoResponse.setPrecioIncrement(0.0);
		datosObtenerCanalTramoResponse.setPrecioMax(0.0);
		datosObtenerCanalTramoResponse.setPrecioMin("0.0");
		datosObtenerCanalTramoResponse.setPrecioRescate(0.0);
		datosObtenerCanalTramoResponse.setRelacionDerechosCanje("0");
		datosObtenerCanalTramoResponse.setSubcanal(99);
		datosObtenerCanalTramoResponse.setTipoCambio(1.0);
		datosObtenerCanalTramoResponse.setTipoCuenta("O");
		datosObtenerCanalTramoResponse.setTipoOferta("C");
		datosObtenerCanalTramoResponse.setTipoPersona("A");
		datosObtenerCanalTramoResponse.setTipoPliego(tipoPliego);
		datosObtenerCanalTramoResponse.setTipoPrecio("PRECIO");
		datosObtenerCanalTramoResponse.setTramoCompetitivo("N");
		datosObtenerCanalTramoResponse.setValidaDerechos("N");

		List<DatosObtenerCanalTramoResponse> datos = new ArrayList<DatosObtenerCanalTramoResponse>();
		datos.add(datosObtenerCanalTramoResponse);

		ObtenerCanalTramoResponse outEntity = new ObtenerCanalTramoResponse();
		outEntity.setDatos(datos);

		Mockito.when(licitacionesDAO.obtenerLicitaciones(Matchers.any(ObtenerCanalTramo.class))).thenReturn(outEntity);

		List<CuentaTituloView> cuentasTitulo = new ArrayList<CuentaTituloView>();

		NuevaLicitacionViewReq request = new NuevaLicitacionViewReq();
		request.setCuentasTitulo(cuentasTitulo);

		Respuesta<ConsultarOrdenesOutDTO> respuestaOk = new Respuesta<ConsultarOrdenesOutDTO>();
		respuestaOk.setEstadoRespuesta(EstadoRespuesta.OK);

		Mockito.when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class),
				Matchers.any(LicitacionesVigentesOutDTO.class))).thenReturn(respuestaOk);

		DatosObtenerCuentasTitulosResponse datosObtenerCuentasTitulosResponse = new DatosObtenerCuentasTitulosResponse();
		datosObtenerCuentasTitulosResponse.setCuentaTitulos(1);

		List<DatosObtenerCuentasTitulosResponse> listaDatos = new ArrayList<DatosObtenerCuentasTitulosResponse>();
		listaDatos.add(datosObtenerCuentasTitulosResponse);

		ObtenerCuentasTitulosResponse obtenerCuentasTitulosResponse = new ObtenerCuentasTitulosResponse();
		obtenerCuentasTitulosResponse.setDatos(listaDatos);

		Mockito.when(licitacionesDAO.obtenerCuentasTitulos(Matchers.any(ObtenerCuentasTitulos.class)))
				.thenThrow(new DAOException());

		Respuesta<Object> responseFactoryError = new Respuesta<Object>();
		responseFactoryError.setEstadoRespuesta(EstadoRespuesta.ERROR);
		responseFactoryError.setRespuestaVacia(true);

		Mockito.when(respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
				CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS)).thenReturn(responseFactoryError);

		
		List<CuentasAdhesionDebitoView> cuentasDebitoPesos = new ArrayList<CuentasAdhesionDebitoView>();
		cuentasDebitoPesos.add(new CuentasAdhesionDebitoView());

        List<CuentasAdhesionDebitoView> cuentasDebitoDolares = new ArrayList<CuentasAdhesionDebitoView>();
		cuentasDebitoDolares.add(new CuentasAdhesionDebitoView());
		
		Respuesta<NuevaLicitacionDTOResponse> respuesta = tituloBOImp.nuevaLicitacion(cliente, request, cuentasDebitoPesos, cuentasDebitoDolares);
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta, responseFactoryError);

	}
	
	@Test
	public void formatoPRecio(){
		String precio = "134569,1";
		String resultado = formatoPrecioTasa(precio);
		System.out.println("Resultado= "+resultado);
		
	}
	
	private String formatoPrecioTasa(String precio) {

		if (precio.contains(",")) {
			int punto = precio.indexOf(",");
			String decimales = precio.substring(punto+1);
			String entera = formatoCantidad(precio.substring(0, punto));
			if (decimales.length() < 7) {
				for (int i = decimales.length(); i < 7; i++) {
					decimales = decimales.concat("0");
				}
				return entera+","+decimales;
			} else {
				return precio;
			}
		}
		return precio;

	}
	
    private String formatoCantidad(String cantidad){
    	
    	if(cantidad.length() > 6){
    		String montoModificado = cantidad;
    		montoModificado = new StringBuilder(montoModificado).insert(montoModificado.length() - 6, ".").toString();
    		montoModificado = new StringBuilder(montoModificado).insert(montoModificado.length() - 3, ".").toString();
    		return montoModificado;	
    	}else if (cantidad.length() > 3){
    		String montoModificado = cantidad;
    		montoModificado = new StringBuilder(montoModificado).insert(montoModificado.length() - 3, ".").toString();
    		return montoModificado;	
    	}
    	
    	return cantidad;
    }
	
//    @SuppressWarnings("unchecked")
//	@Test
//    public void descargarCondicionesTest() throws BusinessException, DAOException, IOException{
//    	DownloadArchivoResponse rtaDao = new DownloadArchivoResponse();
//    	DatosDownloadArchivoResponse datos = new DatosDownloadArchivoResponse();
//		rtaDao.setDatos(datos);
//		String archivo;
//		FileInputStream fis = new FileInputStream("");
//		archivo = IOUtils.toString(fis);
//		datos.setArchivoBase64(archivo);
//		
//		Mockito.when(licitacionesDAO.downloadArchivo(Matchers.any(DownloadArchivo.class))).thenReturn(rtaDao);
//
//		Respuesta <ReporteView> respuestaOk = new Respuesta<ReporteView>();
//		respuestaOk.setEstadoRespuesta(EstadoRespuesta.OK);
//		ReporteView reporteView = new ReporteView();
//		respuestaOk.setRespuesta(reporteView);
//		Mockito.when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class), Matchers.any(ReporteView.class))) .thenReturn(respuestaOk);
//		
//		Respuesta<ReporteView> respuesta = tituloBOImp.descargarCondiciones("nombreArchivo");
//    	
//		Assert.assertNotNull(respuesta);
//		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
//    }


    
    
    
	/** configurarLicitacionReinvertir Ok
	 * 
	 * @throws DAOException
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void configurarLicitacionReinvertir_OK() throws DAOException {

		Cliente cliente = mock(Cliente.class);

		List<String> coleccionMoneda = new ArrayList<String>();
		coleccionMoneda.add("USD");
		
		ConfigurarLicitacionViewReq request = new ConfigurarLicitacionViewReq();
		request.setCodigoEspecie("");
		request.setCuentaTitulo("1234567");
		request.setMonedas(coleccionMoneda);
		request.setRenovacion("S");
		
		List<DatosConsultarTenenciaRenovableResponse> datos = new ArrayList<DatosConsultarTenenciaRenovableResponse>();
		DatosConsultarTenenciaRenovableResponse datosConsultarTenenciaRenovableResponse = new DatosConsultarTenenciaRenovableResponse();
		datosConsultarTenenciaRenovableResponse.setCuentaTitulo(9010132);
		datosConsultarTenenciaRenovableResponse.setCno("00344516");
		datosConsultarTenenciaRenovableResponse.setEspecie("52425");
		datosConsultarTenenciaRenovableResponse.setEspecieDescripcion("LETRAS DEL BCRA 308 DIAS  VTO 22/07/2015                              ");
		datosConsultarTenenciaRenovableResponse.setLaminaMinima(1);
		datosConsultarTenenciaRenovableResponse.setIncrementoMinimo(1);
		datosConsultarTenenciaRenovableResponse.setLugar(new Short("0"));
		datosConsultarTenenciaRenovableResponse.setLugardescripcion("CAJA DE VALORES");
		datosConsultarTenenciaRenovableResponse.setCantidad(17000);
		datosConsultarTenenciaRenovableResponse.setCoeficiente(1);
		
		DatosConsultarTenenciaRenovableResponse datosConsultarTenenciaRenovableResponse2 = new DatosConsultarTenenciaRenovableResponse();
		datosConsultarTenenciaRenovableResponse2.setCuentaTitulo(9010132);
		datosConsultarTenenciaRenovableResponse2.setCno("00344516");
		datosConsultarTenenciaRenovableResponse2.setEspecie("52425");
		datosConsultarTenenciaRenovableResponse2.setEspecieDescripcion("LETRAS DEL BCRA  INTERNAS VTO 14/12/2016                              ");
		datosConsultarTenenciaRenovableResponse2.setLaminaMinima(1);
		datosConsultarTenenciaRenovableResponse2.setIncrementoMinimo(1);
		datosConsultarTenenciaRenovableResponse2.setLugar(new Short("0"));
		datosConsultarTenenciaRenovableResponse2.setLugardescripcion("CAJA DE VALORES");
		datosConsultarTenenciaRenovableResponse2.setCantidad(17000);
		datosConsultarTenenciaRenovableResponse2.setCoeficiente(1);
				
		datos.add(datosConsultarTenenciaRenovableResponse);
		datos.add(datosConsultarTenenciaRenovableResponse2);
		
		ConsultarTenenciaRenovableResponse resultadoConsultarTenenciaRenovable = new ConsultarTenenciaRenovableResponse();
		resultadoConsultarTenenciaRenovable.setCodigo("1");
		resultadoConsultarTenenciaRenovable.setDatos(datos);
					
		Mockito.when(licitacionesDAO.consultarTenenciaRenovable(Matchers.any(ConsultarTenenciaRenovable.class))).thenReturn(resultadoConsultarTenenciaRenovable);
		
		Respuesta<ConsultarOrdenesOutDTO> respuestaOk = new Respuesta<ConsultarOrdenesOutDTO>(); 
		respuestaOk.setEstadoRespuesta(EstadoRespuesta.OK);
		
		 Mockito.when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class), Matchers.any(ConfigurarLicitacionOutView.class)))
         .thenReturn(respuestaOk);
				
		Respuesta<ConfigurarLicitacionOutView> respuesta = tituloBOImp.configurarLicitacionReinvertir(cliente, request, "BR");
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
		Assert.assertNotNull(respuesta);

	}
    
    

		
	/** configurarLicitacionReinvertir WARNING
	 * 
	 * @throws DAOException
	 */
	@Test
	@Ignore
	public void configurarLicitacionReinvertir_WARNING() throws DAOException {

		Cliente cliente = mock(Cliente.class);
		List<String> coleccionMoneda = new ArrayList<String>();
		coleccionMoneda.add("USD");
		
		ConfigurarLicitacionViewReq request = new ConfigurarLicitacionViewReq();
		request.setCodigoEspecie("");
		request.setCuentaTitulo("1234567");
		request.setMonedas(coleccionMoneda);
		request.setRenovacion("S");
		
		List<DatosConsultarTenenciaRenovableResponse> datos = new ArrayList<DatosConsultarTenenciaRenovableResponse>();
		ConsultarTenenciaRenovableResponse resultadoConsultarTenenciaRenovable = new ConsultarTenenciaRenovableResponse();
		resultadoConsultarTenenciaRenovable.setCodigo("1");
		resultadoConsultarTenenciaRenovable.setDatos(datos);
					
		Mockito.when(licitacionesDAO.consultarTenenciaRenovable(Matchers.any(ConsultarTenenciaRenovable.class))).thenReturn(resultadoConsultarTenenciaRenovable);
		Respuesta<ConfigurarLicitacionOutView> respuestaWarning = new Respuesta<ConfigurarLicitacionOutView>(); 
		respuestaWarning.setEstadoRespuesta(EstadoRespuesta.WARNING);
				
		Mockito.when(respuestaFactory.crearRespuestaWarning(Matchers.any(ConfigurarLicitacionOutView.class),Matchers.any(String.class),Matchers.any(TipoError.class),Matchers.any(String.class))).
		thenReturn(respuestaWarning);
		 				
		Respuesta<ConfigurarLicitacionOutView> respuesta = tituloBOImp.configurarLicitacionReinvertir(cliente, request, "BR");
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.WARNING);
		Assert.assertNotNull(respuesta);

	}
	

    
	/** configurarLicitacionReinvertir Error
	 * 
	 * @throws DAOException
	 */
	@Test
	public void configurarLicitacionReinvertir_ERROR() throws DAOException {

		Cliente cliente = mock(Cliente.class);
		List<String> coleccionMoneda = new ArrayList<String>();
		coleccionMoneda.add("USD");
		
		ConfigurarLicitacionViewReq request = new ConfigurarLicitacionViewReq();
		request.setCodigoEspecie("");
		request.setCuentaTitulo("1234567");
		request.setMonedas(coleccionMoneda);
		request.setRenovacion("S");
		
		List<DatosConsultarTenenciaRenovableResponse> datos = new ArrayList<DatosConsultarTenenciaRenovableResponse>();
		ConsultarTenenciaRenovableResponse resultadoConsultarTenenciaRenovable = new ConsultarTenenciaRenovableResponse();
		resultadoConsultarTenenciaRenovable.setCodigo("1");
		resultadoConsultarTenenciaRenovable.setDatos(datos);
					
		Mockito.when(licitacionesDAO.consultarTenenciaRenovable(Matchers.any(ConsultarTenenciaRenovable.class))).thenThrow(new DAOException());
			
		Respuesta<Object> responseFactoryError = new Respuesta<Object>();
        responseFactoryError.setEstadoRespuesta(EstadoRespuesta.ERROR);
        responseFactoryError.setRespuestaVacia(true);

		Mockito.when(respuestaFactory.crearRespuestaError(Matchers.any(String.class), Matchers.any(TipoError.class),
				Matchers.any(String.class))).thenReturn(responseFactoryError);
									
		Respuesta<ConfigurarLicitacionOutView> respuesta = tituloBOImp.configurarLicitacionReinvertir(cliente, request, "BR");
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
		Assert.assertNotNull(respuesta);

	}
	@Ignore
	@SuppressWarnings("unchecked")
	@Test
	public void consultarOperacionesText_OK() throws DAOException {
						 		 
		 ConsultaOperaciones viewRequest = new ConsultaOperaciones();		 
		 List<CuentaTituloView> cuentaTituloViewList = new ArrayList<CuentaTituloView>(); 		 
		 CuentaTituloView cuentaTituloView = new CuentaTituloView();		 
		 List<Interviniente> intervinientes = new ArrayList<Interviniente>();
		 
		 Interviniente Interviniente1 = new Interviniente();
		 Interviniente1.setApellido("RODRIGUEZ CASATTI");
		 Interviniente1.setNombre("FALCO HULLEN");
		 Interviniente1.setOrdenParticipacion("001");
		 
		 Interviniente Interviniente2 = new Interviniente();
		 Interviniente2.setApellido("AGUILERA OLMEDO");
		 Interviniente2.setNombre("SOLEDAD ALBA");
		 Interviniente2.setOrdenParticipacion("002");
		 		 
		 intervinientes.add(Interviniente1);
		 intervinientes.add(Interviniente2);
		 cuentaTituloView.setIntervinientes(intervinientes);
		 cuentaTituloView.setNroCuenta("1234567");
		 
		 cuentaTituloViewList.add(cuentaTituloView);		 
		 viewRequest.setCuentasTitulo(cuentaTituloViewList);
		 
		 Cliente cliente = new Cliente();
		 cliente.setDni("23123456");
		 		 
	    Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
	    Mockito.when(ContextHolder.getContext().getEnvironment().getProperty(Matchers.any(String.class))).thenReturn("SMCFront");
		 	 
		List<DatosConsultaOperacionesResponse> responseDao = new ArrayList<DatosConsultaOperacionesResponse>();
		DatosConsultaOperacionesResponse datosConsultaOperacionesResponse  = new DatosConsultaOperacionesResponse(); 
		datosConsultaOperacionesResponse.setCuentaTitulos("01234567");
		datosConsultaOperacionesResponse.setCantidad("10150");
		datosConsultaOperacionesResponse.setCodMoneda("0");
		datosConsultaOperacionesResponse.setEspecie("AGROMETAL S.A.I. ESCR. 1 VOTO                                         ");
		datosConsultaOperacionesResponse.setFechaOrden("/Date(1510887600000)/");		
		datosConsultaOperacionesResponse.setOrdenId("117786");
		datosConsultaOperacionesResponse.setSigno("$");
		datosConsultaOperacionesResponse.setPrecio("1");
		datosConsultaOperacionesResponse.setTipoEspecie("SHS");
		datosConsultaOperacionesResponse.setTipoOperacion("V");		
		datosConsultaOperacionesResponse.setEstado("BG");
		datosConsultaOperacionesResponse.setPlazo("48");
		datosConsultaOperacionesResponse.setPrecioLimite("1");
		datosConsultaOperacionesResponse.setCapital("10017.35");
		datosConsultaOperacionesResponse.setSucCtaOper("000"); 
        datosConsultaOperacionesResponse.setNroCtaOper("3517486");			
		datosConsultaOperacionesResponse.setCanal("04");
		datosConsultaOperacionesResponse.setTipoCtaOper("09");		
		responseDao.add(datosConsultaOperacionesResponse);
		
		
		Mockito.when(titulosMercadoCanalDAO.consultarOperacionesText(Matchers.any(ConsultaOperacionesRequestEntity.class))).thenReturn(responseDao);

		
		Respuesta<ConsultarOrdenesOutDTO> respuestaOk = new Respuesta<ConsultarOrdenesOutDTO>(); 
		respuestaOk.setEstadoRespuesta(EstadoRespuesta.OK);
		

		 Mockito.when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class), Matchers.any(ConsultaOperacionesDTO.class)))
         .thenReturn(respuestaOk);
		
				
		Respuesta<ConsultaOperacionesDTO> respuesta = tituloBOImp.consultarOperacionesText(viewRequest);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
		Assert.assertNotNull(respuesta);
		
	}
	
	
	


	@Test
	public void consultarOperacionesText_Warning() throws DAOException {
						 		 
		 ConsultaOperaciones viewRequest = new ConsultaOperaciones();		 
		 List<CuentaTituloView> cuentaTituloViewList = new ArrayList<CuentaTituloView>(); 		 
		 CuentaTituloView cuentaTituloView = new CuentaTituloView();		 
		 List<Interviniente> intervinientes = new ArrayList<Interviniente>();
		 
		 Interviniente Interviniente1 = new Interviniente();
		 Interviniente1.setApellido("RODRIGUEZ CASATTI");
		 Interviniente1.setNombre("FALCO HULLEN");
		 Interviniente1.setOrdenParticipacion("001");
		 
		 Interviniente Interviniente2 = new Interviniente();
		 Interviniente2.setApellido("AGUILERA OLMEDO");
		 Interviniente2.setNombre("SOLEDAD ALBA");
		 Interviniente2.setOrdenParticipacion("002");
		 		 
		 intervinientes.add(Interviniente1);
		 intervinientes.add(Interviniente2);
		 cuentaTituloView.setIntervinientes(intervinientes);
		 cuentaTituloView.setNroCuenta("1234567");
		 
		 cuentaTituloViewList.add(cuentaTituloView);		 
		 viewRequest.setCuentasTitulo(cuentaTituloViewList);
		 
		 Cliente cliente = new Cliente();
		 cliente.setDni("23123456");
		 		 
		 Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
		 	 
		List<DatosConsultaOperacionesResponse> responseDao = new ArrayList<DatosConsultaOperacionesResponse>();
		DatosConsultaOperacionesResponse datosConsultaOperacionesResponse  = new DatosConsultaOperacionesResponse(); 
		datosConsultaOperacionesResponse.setCuentaTitulos("1234567");
				
		responseDao.add(datosConsultaOperacionesResponse);
		  
		Mockito.when(titulosMercadoCanalDAO.consultarOperacionesText(Matchers.any(ConsultaOperacionesRequestEntity.class))).thenReturn(responseDao);

		
		Respuesta<ConsultaOperacionesDTO> respuestaWarninig = new Respuesta<ConsultaOperacionesDTO>(); 
		respuestaWarninig.setEstadoRespuesta(EstadoRespuesta.WARNING);
		
		 Mockito.when(respuestaFactory.crearRespuestaWarning(Matchers.any(ConsultaOperacionesDTO.class), Matchers.any(String.class),Matchers.any(TipoError.class), Matchers.any(String.class)))
        .thenReturn(respuestaWarninig);
						
		Respuesta<ConsultaOperacionesDTO> respuesta = tituloBOImp.consultarOperacionesText(viewRequest);
		Assert.assertEquals(EstadoRespuesta.WARNING, respuesta.getEstadoRespuesta());
		Assert.assertNotNull(respuesta);
		
	}

	
	@Test
	public void obtenerTenenciasReporte() {
		
		//When
		Cliente cliente = Mockito.mock(Cliente.class);
		List<TenenciaTitulosCuentaDTO> tenenciaPFView = armarListaTitulosValores();			
		Respuesta<Reporte> respuestaMock = new Respuesta<Reporte>();
		respuestaMock.setEstadoRespuesta(EstadoRespuesta.OK);
		
		Cuenta cuenta = new Cuenta();
		List<Interviniente> intervinientes = new ArrayList<Interviniente>();
		Interviniente interviniente = new Interviniente();
		interviniente.setApellido("Alfaro");
		interviniente.setNombre("Gustavo");
		intervinientes.add(interviniente);
		cuenta.setIntervinientes(intervinientes);
		
		Mockito.when(cliente.getCuentaRetailSiContieneNumero(Matchers.anyString())).thenReturn(cuenta);
		Mockito.when(reporteDAO.obtenerReporte(Matchers.any(CuentasTitulosExcelGeneral.class), Matchers.anyString(), Matchers.any(Cliente.class), Matchers.anyBoolean())).thenReturn(respuestaMock);
		
		//Then
		respuestaMock = tituloBOImp.obtenerTenenciasReporte(tenenciaPFView, TipoBancaEnum.BANCA_PERSONAL, cliente);
		
		//Expected
		Assert.assertNotNull(respuestaMock);
		Assert.assertEquals(EstadoRespuesta.OK, respuestaMock.getEstadoRespuesta());
		
	}
	
	
	private List<TenenciaTitulosCuentaDTO> armarListaTitulosValores() {
		
		List<TenenciaTitulosCuentaDTO> listaTitulosValores = new ArrayList<TenenciaTitulosCuentaDTO>();
		TenenciaTitulosCuentaDTO cuentaTitulosDTO = new TenenciaTitulosCuentaDTO();
		cuentaTitulosDTO.setNumeroCuenta("026081/9");
		cuentaTitulosDTO.setTotalTenenciaValuadaPesos(140705.95);
		
		List<TenenciaTitulosDTO> listaPesos = new ArrayList<TenenciaTitulosDTO>();
		TenenciaTitulosDTO tenenciaDTO = new TenenciaTitulosDTO();
		tenenciaDTO.setTipo("Acciones");
		tenenciaDTO.setDescripcion("AGROMETAL S.A.I. ESCR. 1 VOTO");
		tenenciaDTO.setCantidadValorNominal(new Double(11123));
		tenenciaDTO.setPrecioMercado(12.65);
		tenenciaDTO.setTenenciaValuada(140705.95);
		tenenciaDTO.setResultado(null);
		listaPesos.add(tenenciaDTO);
		
		TenenciaTitulosDTO tenenciaDTO2 = new TenenciaTitulosDTO();
		tenenciaDTO2.setTipo("Títulos Públicos");
		tenenciaDTO2.setDescripcion("US TREASURY EN U$S VTO. 15/09/2021");
		tenenciaDTO2.setCantidadValorNominal(new Double(11123));
		tenenciaDTO2.setPrecioMercado(12.65);
		tenenciaDTO2.setTenenciaValuada(140705.95);
		tenenciaDTO2.setResultado(null);
		
		listaPesos.add(tenenciaDTO2);
		cuentaTitulosDTO.setListaPesos(listaPesos);
		listaTitulosValores.add(cuentaTitulosDTO);
		
		TenenciaTitulosCuentaDTO cuentaTitulosDTO2 = new TenenciaTitulosCuentaDTO();
		cuentaTitulosDTO2.setNumeroCuenta("556811/6");
		
		List<TenenciaTitulosDTO> listaPesos2 = new ArrayList<TenenciaTitulosDTO>();
		List<TenenciaTitulosDTO> listaDolares2 = new ArrayList<TenenciaTitulosDTO>();
		
		cuentaTitulosDTO2.setListaDolares(listaPesos2);
		cuentaTitulosDTO2.setListaDolares(listaDolares2);
		listaTitulosValores.add(cuentaTitulosDTO2);
		
		return listaTitulosValores;
		
	}
	
}
