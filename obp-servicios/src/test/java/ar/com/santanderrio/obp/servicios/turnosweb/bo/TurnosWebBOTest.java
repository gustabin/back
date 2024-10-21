package ar.com.santanderrio.obp.servicios.turnosweb.bo;

import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.JAXBElement;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.exceptions.ISBANRuntimeException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.base.service.ServiceException;
import ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.datacontracts.ArrayOfCitasConMotivoSvcResponse;
import ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.datacontracts.ArrayOfHorarioSvcResponse;
import ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.datacontracts.ArrayOfSucursalSvcResponse;
import ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.datacontracts.CitasConMotivoSvcResponse;
import ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.datacontracts.GetAltaCitaConMotivoSvcResponse;
import ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.datacontracts.GetAltaCitaSvcResponse;
import ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.datacontracts.GetBajaTurnoSvcResponse;
import ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.datacontracts.GetConsultaCitaConMotivoSvcResponse;
import ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.datacontracts.GetConsultaHorariosDisponiblesSvcResponse;
import ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.datacontracts.GetConsultaSucursalesSvcResponse;
import ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.datacontracts.GetModificacionCitaSvcResponse;
import ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.datacontracts.HorarioSvcResponse;
import ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.datacontracts.SucursalSvcResponse;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.comun.sucursales.dao.ConsultarSucursalesDAO;
import ar.com.santanderrio.obp.servicios.comun.sucursales.entities.Sucursal;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.transferencias.exception.TimeOutException;
import ar.com.santanderrio.obp.servicios.turnosweb.bo.impl.TurnosWebBOImpl;
import ar.com.santanderrio.obp.servicios.turnosweb.dao.TurnosWebDAO;
import ar.com.santanderrio.obp.servicios.turnosweb.dto.AltaModificacionInDTO;
import ar.com.santanderrio.obp.servicios.turnosweb.dto.AltaModificacionOutDTO;
import ar.com.santanderrio.obp.servicios.turnosweb.dto.CitaDTO;
import ar.com.santanderrio.obp.servicios.turnosweb.dto.CitaOutDTO;
import ar.com.santanderrio.obp.servicios.turnosweb.dto.ComprobanteTurnoInDTO;
import ar.com.santanderrio.obp.servicios.turnosweb.dto.HorariosDisponiblesInDTO;
import ar.com.santanderrio.obp.servicios.turnosweb.dto.HorariosDisponiblesOutDTO;
import ar.com.santanderrio.obp.servicios.turnosweb.dto.SucursalesOutDTO;
import ar.com.santanderrio.obp.servicios.turnosweb.entity.AltaModificacionCitaInEntity;
import ar.com.santanderrio.obp.servicios.turnosweb.entity.ComprobanteTurnoInEntity;
import ar.com.santanderrio.obp.servicios.turnosweb.entity.HorariosDisponiblesInEntity;

/**
 * The Class TurnosWebBOTest.
 * 
 * @author ITResources
 */
@RunWith(MockitoJUnitRunner.class)
public class TurnosWebBOTest {

	/** The turnos web dao. */
	@Mock
	private TurnosWebDAO turnosWebDAO;

	/** The turnos web bo. */
	@InjectMocks
	private TurnosWebBO turnosWebBO = new TurnosWebBOImpl();

	/** The respuesta factory. */
    @Spy
    @InjectMocks
    private RespuestaFactory respuestaFactory = new RespuestaFactory();

    /** The sesion cliente. */
    @Mock
    private SesionCliente sesionCliente;
    
    /** The sesion parametros. */
    @Mock
    private SesionParametros sesionParametros;
    
    /** The consultar sucursales dao. */
    @Mock
    private ConsultarSucursalesDAO consultarSucursalesDAO;

    /** The mensaje bo. */
    @Mock
    private MensajeBO mensajeBO;
    
    /**
     * Inits.
     */
    @Before
    public void init() throws ServiceException {
        MockitoAnnotations.initMocks(this);
		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("PRUEBA");
        when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_MENSAJE_ERROR_GENERICO)).thenReturn(mensaje);
    }

    
	@Test
	public void generarComprobanteTurnoWebOK() throws DAOException{
		Reporte outDAO = new Reporte();
		ComprobanteTurnoInDTO comprobanteTurnoInDTO = new ComprobanteTurnoInDTO();
		comprobanteTurnoInDTO.setIdTurno(null);
		comprobanteTurnoInDTO.setDia("Lunes 21, de Septiembre del 2018");
		comprobanteTurnoInDTO.setHora("09:00 hs");
		comprobanteTurnoInDTO.setSucursal("245 - Salta, Tres cerritos");
		comprobanteTurnoInDTO.setDireccion("Virrey Toledo 749, Salta");
		comprobanteTurnoInDTO.setSector("P");
		comprobanteTurnoInDTO.setAreaCelular("11");
		comprobanteTurnoInDTO.setNumeroCelular("22223333");
		comprobanteTurnoInDTO.setEmpresaCelular("Claro");
		comprobanteTurnoInDTO.setEmail("probando@mail.com");
		comprobanteTurnoInDTO.setFechaAlta(new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Date()));
		CitaOutDTO citaOutDTO  = new CitaOutDTO();
		List<CitaDTO> citaDTOs = new ArrayList<CitaDTO>();
		CitaDTO citaDTO = new CitaDTO();
		citaDTO.setFraccion("2000-2030");
		citaDTO.setFecha("04042018");
		citaDTO.setDireccion("Catamarca 2");
		citaDTO.setDia("Lunes");
		citaDTO.setDiaNumero(1);
		citaDTO.setMes("Febrero");
		citaDTO.setHorario("12:00hs");
		citaDTO.setSucursal("000");
		citaDTO.setSector("P");
		citaDTO.setAnio(2018);
		citaDTO.setIdTurno((long) 0);
		citaDTO.setDescripcionSucursal("Casa central");
		citaDTOs.add(citaDTO);
		citaOutDTO.setCitaDTOList(citaDTOs);	
		when(turnosWebDAO.generarComprobante(Matchers.any(ComprobanteTurnoInEntity.class), Matchers.any(Cliente.class))).thenReturn(outDAO);
        when(sesionParametros.getCitaOutDTO()).thenReturn(citaOutDTO);
		Respuesta<Reporte> respuesta = turnosWebBO.generarComprobanteTurnoWeb(comprobanteTurnoInDTO);
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);    
	}

	@Test
	public void generarComprobanteTurnoWebIdTurnoSessionOK() throws DAOException{
		Reporte outDAO = new Reporte();
		ComprobanteTurnoInDTO comprobanteTurnoInDTO = new ComprobanteTurnoInDTO();
		comprobanteTurnoInDTO.setIdTurno((long) 0);
		comprobanteTurnoInDTO.setDia("Lunes 21, de Septiembre del 2018");
		comprobanteTurnoInDTO.setHora("09:00 hs");
		comprobanteTurnoInDTO.setSucursal("245 - Salta, Tres cerritos");
		comprobanteTurnoInDTO.setDireccion("Virrey Toledo 749, Salta");
		comprobanteTurnoInDTO.setSector("P");
		comprobanteTurnoInDTO.setAreaCelular("11");
		comprobanteTurnoInDTO.setNumeroCelular("22223333");
		comprobanteTurnoInDTO.setEmpresaCelular("Claro");
		comprobanteTurnoInDTO.setEmail("probando@mail.com");
		comprobanteTurnoInDTO.setFechaAlta(new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Date()));
		CitaOutDTO citaOutDTO  = new CitaOutDTO();
		List<CitaDTO> citaDTOs = new ArrayList<CitaDTO>();
		CitaDTO citaDTO = new CitaDTO();
		citaDTO.setFraccion("2000-2030");
		citaDTO.setFecha("20180615");
		citaDTO.setDireccion("Catamarca 2");
		citaDTO.setDia("Lunes");
		citaDTO.setDiaNumero(1);
		citaDTO.setMes("Febrero");
		citaDTO.setHorario("12:00hs");
		citaDTO.setSucursal("000");
		citaDTO.setSector("P");
		citaDTO.setAnio(2018);
		citaDTO.setIdTurno((long) 0);
		citaDTO.setDescripcionSucursal("Casa central");
		citaDTOs.add(citaDTO);
		citaOutDTO.setCitaDTOList(citaDTOs);
        Sucursal sucursal = new Sucursal();
        sucursal.setDescripcion("Casa central");
        sucursal.setDireccion("Bartolome mitre");
        sucursal.setIdSucursal("000");
        sucursal.setTipoDeOficina("");
		when(turnosWebDAO.generarComprobante(Matchers.any(ComprobanteTurnoInEntity.class), Matchers.any(Cliente.class))).thenReturn(outDAO);
        when(sesionParametros.getCitaOutDTO()).thenReturn(citaOutDTO);
        when(consultarSucursalesDAO.consultarSucursalPorId(Matchers.anyString())).thenReturn(sucursal);
		Respuesta<Reporte> respuesta = turnosWebBO.generarComprobanteTurnoWeb(comprobanteTurnoInDTO);
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);    
	}

	@Test
	public void generarComprobanteTurnoWebIdTurnoNoSessionOK() throws DAOException{
		Reporte outDAO = new Reporte();
		ComprobanteTurnoInDTO comprobanteTurnoInDTO = new ComprobanteTurnoInDTO();
		comprobanteTurnoInDTO.setIdTurno((long) 1);
		comprobanteTurnoInDTO.setDia("Lunes 21, de Septiembre del 2018");
		comprobanteTurnoInDTO.setHora("09:00 hs");
		comprobanteTurnoInDTO.setSucursal("245 - Salta, Tres cerritos");
		comprobanteTurnoInDTO.setDireccion("Virrey Toledo 749, Salta");
		comprobanteTurnoInDTO.setSector("P");
		comprobanteTurnoInDTO.setAreaCelular("11");
		comprobanteTurnoInDTO.setNumeroCelular("22223333");
		comprobanteTurnoInDTO.setEmpresaCelular("Claro");
		comprobanteTurnoInDTO.setEmail("probando@mail.com");
		comprobanteTurnoInDTO.setFechaAlta(new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Date()));
		CitaOutDTO citaOutDTO  = new CitaOutDTO();
		List<CitaDTO> citaDTOs = new ArrayList<CitaDTO>();
		CitaDTO citaDTO = new CitaDTO();
		citaDTO.setFraccion("2000-2030");
		citaDTO.setFecha("20180615");
		citaDTO.setDireccion("Catamarca 2");
		citaDTO.setDia("Lunes");
		citaDTO.setDiaNumero(1);
		citaDTO.setMes("Febrero");
		citaDTO.setHorario("12:00hs");
		citaDTO.setSucursal("000");
		citaDTO.setSector("P");
		citaDTO.setAnio(2018);
		citaDTO.setIdTurno((long) 0);
		citaDTO.setDescripcionSucursal("Casa central");
		citaDTOs.add(citaDTO);
		citaOutDTO.setCitaDTOList(citaDTOs);	
		when(turnosWebDAO.generarComprobante(Matchers.any(ComprobanteTurnoInEntity.class), Matchers.any(Cliente.class))).thenReturn(outDAO);
        when(sesionParametros.getCitaOutDTO()).thenReturn(citaOutDTO);
        Respuesta<Reporte> respuesta = turnosWebBO.generarComprobanteTurnoWeb(comprobanteTurnoInDTO);
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
	}

	@Test
	public void generarComprobanteTurnoWebException() {
		ComprobanteTurnoInDTO comprobanteTurnoInDTO = new ComprobanteTurnoInDTO();
		comprobanteTurnoInDTO.setIdTurno(null);
		comprobanteTurnoInDTO.setDia("Lunes 21, de Septiembre del 2018");
		comprobanteTurnoInDTO.setHora("09:00 hs");
		comprobanteTurnoInDTO.setSucursal("245 - Salta, Tres cerritos");
		comprobanteTurnoInDTO.setDireccion("Virrey Toledo 749, Salta");
		comprobanteTurnoInDTO.setSector("P");
		comprobanteTurnoInDTO.setAreaCelular("11");
		comprobanteTurnoInDTO.setNumeroCelular("22223333");
		comprobanteTurnoInDTO.setEmpresaCelular("Claro");
		comprobanteTurnoInDTO.setEmail("probando@mail.com");
		comprobanteTurnoInDTO.setFechaAlta(new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Date()));
		when(turnosWebDAO.generarComprobante(Matchers.any(ComprobanteTurnoInEntity.class), Matchers.any(Cliente.class))).thenThrow(new ISBANRuntimeException());
        Respuesta<Reporte> respuesta = turnosWebBO.generarComprobanteTurnoWeb(comprobanteTurnoInDTO);
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);    
	}
	
	@Test
	public void bajaTurnoWebOK() throws DAOException{
		GetBajaTurnoSvcResponse getBajaTurnoSvcResponse = new GetBajaTurnoSvcResponse();
		getBajaTurnoSvcResponse.setCodigoError(BigDecimal.ZERO);
		when(turnosWebDAO.bajaTurno(Matchers.anyLong())).thenReturn(getBajaTurnoSvcResponse);
		Respuesta<Void> respuesta = turnosWebBO.bajaTurno((long) 1);
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);    
	}

	@Test
	public void bajaTurnoWebRetornoDistintoDeCeroOK() throws DAOException{
		GetBajaTurnoSvcResponse getBajaTurnoSvcResponse = new GetBajaTurnoSvcResponse();
		getBajaTurnoSvcResponse.setCodigoError(BigDecimal.valueOf(1));
		when(turnosWebDAO.bajaTurno(Matchers.anyLong())).thenReturn(getBajaTurnoSvcResponse);
		Respuesta<Void> respuesta = turnosWebBO.bajaTurno((long) 1);
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);    
	}
	
	@Test
	public void bajaTurnoWebRetornoDistintoException() throws DAOException{
		when(turnosWebDAO.bajaTurno(Matchers.anyLong())).thenThrow(new DAOException());
		Respuesta<Void> respuesta = turnosWebBO.bajaTurno((long) 1);
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);    
	}

	@Test
	public void altaTurnoOK() throws DAOException{
		GetAltaCitaConMotivoSvcResponse response = new GetAltaCitaConMotivoSvcResponse();
		response.setCodigoError(BigDecimal.ZERO);
		response.setIdTurno((long) 1234);
		AltaModificacionInDTO altaModificacionInDTO = new AltaModificacionInDTO();
		altaModificacionInDTO.setFecha("20180404");
		altaModificacionInDTO.setFraccion("1900-2030");
		altaModificacionInDTO.setIdTurno((long)0);
		altaModificacionInDTO.setNroSuc("000");
		altaModificacionInDTO.setNup("12345678");
		altaModificacionInDTO.setSector("P");
		altaModificacionInDTO.setDescripcionFecha("Desc fecha");
		altaModificacionInDTO.setDescripcionSucursal("Desc suc");
		altaModificacionInDTO.setHoraInicio("1900");
		altaModificacionInDTO.setAccion("A");
		when(turnosWebDAO.altaCita(Matchers.any(AltaModificacionCitaInEntity.class))).thenReturn(response);
		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("PRUEBA");
        when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.FEEDBACK_OK_ALTA_MODIFICACION_TURNO)).thenReturn(mensaje);
		Respuesta<AltaModificacionOutDTO> respuesta = turnosWebBO.altaModificacionCita(altaModificacionInDTO);
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);    
	}


	@Test
	public void altaTurnoCodigoErrorDistintoDeCero () throws DAOException{
		GetAltaCitaConMotivoSvcResponse response = new GetAltaCitaConMotivoSvcResponse();
		response.setCodigoError(BigDecimal.valueOf(1));
		AltaModificacionInDTO altaModificacionInDTO = new AltaModificacionInDTO();
		altaModificacionInDTO.setFecha("20180404");
		altaModificacionInDTO.setFraccion("1900-2030");
		altaModificacionInDTO.setIdTurno((long)0);
		altaModificacionInDTO.setNroSuc("000");
		altaModificacionInDTO.setNup("12345678");
		altaModificacionInDTO.setSector("P");
		altaModificacionInDTO.setDescripcionFecha("Desc fecha");
		altaModificacionInDTO.setDescripcionSucursal("Desc suc");
		altaModificacionInDTO.setHoraInicio("1900");
		altaModificacionInDTO.setAccion("A");
		when(turnosWebDAO.altaCita(Matchers.any(AltaModificacionCitaInEntity.class))).thenReturn(response);
		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("PRUEBA");
        when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.FEEDBACK_ERROR_ALTA_MODIFICACION_TURNO)).thenReturn(mensaje);
		Respuesta<AltaModificacionOutDTO> respuesta = turnosWebBO.altaModificacionCita(altaModificacionInDTO);
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);    
	}
	
	@Test
	public void modificacionTurnoCodigoErrorDistintoDeCero() throws DAOException{
		GetModificacionCitaSvcResponse response = new GetModificacionCitaSvcResponse();
		response.setCodigoError(BigDecimal.valueOf(1));
		AltaModificacionInDTO altaModificacionInDTO = new AltaModificacionInDTO();
		altaModificacionInDTO.setFecha("20180404");
		altaModificacionInDTO.setFraccion("1900-2030");
		altaModificacionInDTO.setIdTurno((long)3333);
		altaModificacionInDTO.setNroSuc("000");
		altaModificacionInDTO.setNup("12345678");
		altaModificacionInDTO.setSector("P");
		altaModificacionInDTO.setDescripcionFecha("Desc fecha");
		altaModificacionInDTO.setDescripcionSucursal("Desc suc");
		altaModificacionInDTO.setHoraInicio("1900");
		altaModificacionInDTO.setAccion("M");
		when(turnosWebDAO.modificacionCita(Matchers.any(AltaModificacionCitaInEntity.class))).thenReturn(response);
		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("PRUEBA");
        when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.FEEDBACK_ERROR_ALTA_MODIFICACION_TURNO)).thenReturn(mensaje);
		Respuesta<AltaModificacionOutDTO> respuesta = turnosWebBO.altaModificacionCita(altaModificacionInDTO);
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);    
	}

	@Test
	public void modificacionTurnoOK() throws DAOException{
		GetModificacionCitaSvcResponse response = new GetModificacionCitaSvcResponse();
		response.setCodigoError(BigDecimal.ZERO);
		response.setIdTurno((long) 1234);
		AltaModificacionInDTO altaModificacionInDTO = new AltaModificacionInDTO();
		altaModificacionInDTO.setFecha("20180404");
		altaModificacionInDTO.setFraccion("1900-2030");
		altaModificacionInDTO.setIdTurno((long)3333);
		altaModificacionInDTO.setNroSuc("000");
		altaModificacionInDTO.setNup("12345678");
		altaModificacionInDTO.setSector("P");
		altaModificacionInDTO.setDescripcionFecha("Desc fecha");
		altaModificacionInDTO.setDescripcionSucursal("Desc suc");
		altaModificacionInDTO.setHoraInicio("1900");
		altaModificacionInDTO.setAccion("M");
		when(turnosWebDAO.modificacionCita(Matchers.any(AltaModificacionCitaInEntity.class))).thenReturn(response);
		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("PRUEBA");
        when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.FEEDBACK_OK_ALTA_MODIFICACION_TURNO)).thenReturn(mensaje);
		Respuesta<AltaModificacionOutDTO> respuesta = turnosWebBO.altaModificacionCita(altaModificacionInDTO);
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);    
	}

	@Test
	public void altaModificacionAccionErronea() throws DAOException{
		GetAltaCitaConMotivoSvcResponse response = new GetAltaCitaConMotivoSvcResponse();
		response.setCodigoError(BigDecimal.ZERO);
		response.setIdTurno((long) 1234);
		AltaModificacionInDTO altaModificacionInDTO = new AltaModificacionInDTO();
		altaModificacionInDTO.setFecha("20180404");
		altaModificacionInDTO.setFraccion("1900-2030");
		altaModificacionInDTO.setIdTurno((long)0);
		altaModificacionInDTO.setNroSuc("000");
		altaModificacionInDTO.setNup("12345678");
		altaModificacionInDTO.setSector("P");
		altaModificacionInDTO.setDescripcionFecha("Desc fecha");
		altaModificacionInDTO.setDescripcionSucursal("Desc suc");
		altaModificacionInDTO.setHoraInicio("1900");
		altaModificacionInDTO.setAccion("X");
		when(turnosWebDAO.altaCita(Matchers.any(AltaModificacionCitaInEntity.class))).thenReturn(response);
		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("PRUEBA");
        when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.FEEDBACK_TIME_OUT_ALTA_MODIFICACION_TURNO)).thenReturn(mensaje);
		Respuesta<AltaModificacionOutDTO> respuesta = turnosWebBO.altaModificacionCita(altaModificacionInDTO);
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);    
	}

	@Test
	public void altaModificaciontTimeOutException() throws DAOException{
		GetAltaCitaSvcResponse response = new GetAltaCitaSvcResponse();
		response.setCodigoError(BigDecimal.ZERO);
		response.setIdTurno((long) 1234);
		AltaModificacionInDTO altaModificacionInDTO = new AltaModificacionInDTO();
		altaModificacionInDTO.setFecha("20180404");
		altaModificacionInDTO.setFraccion("1900-2030");
		altaModificacionInDTO.setIdTurno((long)0);
		altaModificacionInDTO.setNroSuc("000");
		altaModificacionInDTO.setNup("12345678");
		altaModificacionInDTO.setSector("P");
		altaModificacionInDTO.setDescripcionFecha("Desc fecha");
		altaModificacionInDTO.setDescripcionSucursal("Desc suc");
		altaModificacionInDTO.setHoraInicio("1900");
		altaModificacionInDTO.setAccion("A");
		when(turnosWebDAO.altaCita(Matchers.any(AltaModificacionCitaInEntity.class))).thenThrow(new TimeOutException("TIME OUT"));
		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("PRUEBA");
        when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.FEEDBACK_TIME_OUT_ALTA_MODIFICACION_TURNO)).thenReturn(mensaje);
		Respuesta<AltaModificacionOutDTO> respuesta = turnosWebBO.altaModificacionCita(altaModificacionInDTO);
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);    
	}
	
	@Test
	public void altaModificaciontDAOException() throws DAOException{
		GetAltaCitaSvcResponse response = new GetAltaCitaSvcResponse();
		response.setCodigoError(BigDecimal.ZERO);
		response.setIdTurno((long) 1234);
		AltaModificacionInDTO altaModificacionInDTO = new AltaModificacionInDTO();
		altaModificacionInDTO.setFecha("20180404");
		altaModificacionInDTO.setFraccion("1900-2030");
		altaModificacionInDTO.setIdTurno((long)0);
		altaModificacionInDTO.setNroSuc("000");
		altaModificacionInDTO.setNup("12345678");
		altaModificacionInDTO.setSector("P");
		altaModificacionInDTO.setDescripcionFecha("Desc fecha");
		altaModificacionInDTO.setDescripcionSucursal("Desc suc");
		altaModificacionInDTO.setHoraInicio("1900");
		altaModificacionInDTO.setAccion("A");
		when(turnosWebDAO.altaCita(Matchers.any(AltaModificacionCitaInEntity.class))).thenThrow(new DAOException());
		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("PRUEBA");
        when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.FEEDBACK_ERROR_ALTA_MODIFICACION_TURNO)).thenReturn(mensaje);
		Respuesta<AltaModificacionOutDTO> respuesta = turnosWebBO.altaModificacionCita(altaModificacionInDTO);
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);    
	}

	@Test
	public void consultaSucursalesOk() throws DAOException{
		GetConsultaSucursalesSvcResponse retorno = new GetConsultaSucursalesSvcResponse();
		retorno.setCodigoError(BigDecimal.ZERO);
		ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.datacontracts.ObjectFactory factory = 
				new ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.datacontracts.ObjectFactory();		JAXBElement<String> sucUnoDesc = factory.createSucursalSvcResponseDescri("Suc 1 desc");
		JAXBElement<String> sucUnoDom = factory.createSucursalSvcResponseDomicilio("Suc 1 dom");
		JAXBElement<String> sucUnoLoc = factory.createSucursalSvcResponseLocalidad("Suc 1 loc");
		JAXBElement<String> sucUnoSuc = factory.createSucursalSvcResponseSucursal("Suc 1 suc");		
		SucursalSvcResponse sucUno = new SucursalSvcResponse();
		sucUno.setDescri(sucUnoDesc);
		sucUno.setDomicilio(sucUnoDom);
		sucUno.setLocalidad(sucUnoLoc);
		sucUno.setSucursal(sucUnoSuc);
		ArrayOfSucursalSvcResponse arrayOfSucursalSvcResponse = new ArrayOfSucursalSvcResponse();
		arrayOfSucursalSvcResponse.getSucursalSvcResponse().add(sucUno);
		JAXBElement<ArrayOfSucursalSvcResponse> listSuc = factory.createArrayOfSucursalSvcResponse(arrayOfSucursalSvcResponse);
		retorno.setSucursales(listSuc);
		when(turnosWebDAO.consultaSucursales()).thenReturn(retorno);
		Respuesta<SucursalesOutDTO> respuesta = turnosWebBO.consultaSucursales();
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);    
	}


	@Test
	public void consultaSucursalesCodigoDeErrorDistintoDeCero() throws DAOException{
		GetConsultaSucursalesSvcResponse retorno = new GetConsultaSucursalesSvcResponse();
		retorno.setCodigoError(BigDecimal.valueOf(1));
		ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.datacontracts.ObjectFactory factory = 
				new ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.datacontracts.ObjectFactory();
		JAXBElement<String> sucUnoDesc = factory.createSucursalSvcResponseDescri("Suc 1 desc");
		JAXBElement<String> sucUnoDom = factory.createSucursalSvcResponseDomicilio("Suc 1 dom");
		JAXBElement<String> sucUnoLoc = factory.createSucursalSvcResponseLocalidad("Suc 1 loc");
		JAXBElement<String> sucUnoSuc = factory.createSucursalSvcResponseSucursal("Suc 1 suc");		
		SucursalSvcResponse sucUno = new SucursalSvcResponse();
		sucUno.setDescri(sucUnoDesc);
		sucUno.setDomicilio(sucUnoDom);
		sucUno.setLocalidad(sucUnoLoc);
		sucUno.setSucursal(sucUnoSuc);
		ArrayOfSucursalSvcResponse arrayOfSucursalSvcResponse = new ArrayOfSucursalSvcResponse();
		arrayOfSucursalSvcResponse.getSucursalSvcResponse().add(sucUno);
		JAXBElement<ArrayOfSucursalSvcResponse> listSuc = factory.createArrayOfSucursalSvcResponse(arrayOfSucursalSvcResponse);
		retorno.setSucursales(listSuc);
		when(turnosWebDAO.consultaSucursales()).thenReturn(retorno);
		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("PRUEBA");
        when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS)).thenReturn(mensaje);
        when(sesionParametros.getSucursalesTurnosWeb()).thenReturn(null);
        Respuesta<SucursalesOutDTO> respuesta = turnosWebBO.consultaSucursales();
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);    
	}

	@Test
	public void consultaSucursalesCodigoDeErrorIgualCero() throws DAOException{
		GetConsultaSucursalesSvcResponse retorno = new GetConsultaSucursalesSvcResponse();
		retorno.setCodigoError(BigDecimal.ZERO);
		ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.datacontracts.ObjectFactory factory = 
				new ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.datacontracts.ObjectFactory();
		JAXBElement<String> sucUnoDesc = factory.createSucursalSvcResponseDescri("Suc 1 desc");
		JAXBElement<String> sucUnoDom = factory.createSucursalSvcResponseDomicilio("Suc 1 dom");
		JAXBElement<String> sucUnoLoc = factory.createSucursalSvcResponseLocalidad("Suc 1 loc");
		JAXBElement<String> sucUnoSuc = factory.createSucursalSvcResponseSucursal("Suc 1 suc");		
		SucursalSvcResponse sucUno = new SucursalSvcResponse();
		sucUno.setDescri(sucUnoDesc);
		sucUno.setDomicilio(sucUnoDom);
		sucUno.setLocalidad(sucUnoLoc);
		sucUno.setSucursal(sucUnoSuc);
		ArrayOfSucursalSvcResponse arrayOfSucursalSvcResponse = new ArrayOfSucursalSvcResponse();
		arrayOfSucursalSvcResponse.getSucursalSvcResponse().add(sucUno);
		JAXBElement<ArrayOfSucursalSvcResponse> listSuc = factory.createArrayOfSucursalSvcResponse(arrayOfSucursalSvcResponse);
		retorno.setSucursales(listSuc);
		when(turnosWebDAO.consultaSucursales()).thenReturn(retorno);
		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("PRUEBA");
        when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS)).thenReturn(mensaje);
        when(sesionParametros.getSucursalesTurnosWeb()).thenReturn(null);
        Respuesta<SucursalesOutDTO> respuesta = turnosWebBO.consultaSucursales();
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);    
	}
	
	@Test
	public void consultaSucursalesException() throws DAOException{
		when(turnosWebDAO.consultaSucursales()).thenThrow(new DAOException());
		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("PRUEBA");
        when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS)).thenReturn(mensaje);
        when(sesionParametros.getSucursalesTurnosWeb()).thenReturn(null);
        Respuesta<SucursalesOutDTO> respuesta = turnosWebBO.consultaSucursales();
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);    
	}

	@Test
	public void consultarCitasOK() throws DAOException{
		GetConsultaCitaConMotivoSvcResponse retorno = new GetConsultaCitaConMotivoSvcResponse();
		retorno.setCodigoError(BigDecimal.ZERO);
		ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.datacontracts.ObjectFactory factory = 
				new ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.datacontracts.ObjectFactory();
		CitasConMotivoSvcResponse citaEje = new CitasConMotivoSvcResponse();
		citaEje.setFecha(factory.createCitaCajaSvcResponseFecha("20180404"));
		citaEje.setFraccion(factory.createCitaCajaSvcResponseFraccion("1900-2030"));
		citaEje.setIdSucursal(factory.createCitaSvcResponseIdSucursal("000"));
		citaEje.setIdTurno((long) 1234);
		citaEje.setSector(factory.createCitaSvcResponseSector("P"));
		citaEje.setIdMotivo(new Long(1));
		citaEje.setIdMotivo(123l);
		citaEje.setTipoTurno(factory.createCitasConMotivoSvcResponseTipoTurno("TT"));
		CitasConMotivoSvcResponse citaCaja = new CitasConMotivoSvcResponse();
		citaCaja.setFecha(factory.createCitaCajaSvcResponseFecha("20180404"));
		citaCaja.setFraccion(factory.createCitaCajaSvcResponseFraccion("1900-2030"));
		citaCaja.setIdSucursal(factory.createCitaSvcResponseIdSucursal("000"));
		citaCaja.setIdTurno((long) 1234);
		citaCaja.setSector(factory.createCitaSvcResponseSector("C"));
		citaCaja.setIdMotivo(new Long(99));
		citaCaja.setTipoTurno(factory.createCitasConMotivoSvcResponseTipoTurno("TT"));

		ArrayOfCitasConMotivoSvcResponse listaCitas = factory.createArrayOfCitasConMotivoSvcResponse();
		listaCitas.getCitasConMotivoSvcResponse().add(citaCaja);
		listaCitas.getCitasConMotivoSvcResponse().add(citaEje);
		retorno.setCitas(factory.createArrayOfCitasConMotivoSvcResponse(listaCitas));
	    Sucursal sucursal = new Sucursal();
        sucursal.setDescripcion("Casa central");
        sucursal.setDireccion("Bartolome mitre");
        sucursal.setIdSucursal("000");
        sucursal.setTipoDeOficina("");
	    when(consultarSucursalesDAO.consultarSucursalPorId(Matchers.anyString())).thenReturn(sucursal);
		when(turnosWebDAO.consultarCitas(Matchers.anyString())).thenReturn(retorno);
		Respuesta<CitaOutDTO> respuesta = turnosWebBO.consultarCitas("12345");
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);    
	}

	@Test
	public void consultarCitasCodigoDeErrorDistintoDeCero() throws DAOException{
		GetConsultaCitaConMotivoSvcResponse retorno = new GetConsultaCitaConMotivoSvcResponse();
		retorno.setCodigoError(BigDecimal.valueOf(1));
		when(turnosWebDAO.consultarCitas(Matchers.anyString())).thenReturn(retorno);
		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("PRUEBA");
        when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS)).thenReturn(mensaje);
		Respuesta<CitaOutDTO> respuesta = turnosWebBO.consultarCitas("12345");
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);    
	}

	@Test
	public void consultarCitasException() throws DAOException{
		when(turnosWebDAO.consultarCitas(Matchers.anyString())).thenThrow(new DAOException());
		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("PRUEBA");
        when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS)).thenReturn(mensaje);
		Respuesta<CitaOutDTO> respuesta = turnosWebBO.consultarCitas("12345");
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);    
	}

	
	@Test
	public void consultaHorariosDisponiblesOK() throws DAOException{
		HorariosDisponiblesInDTO horariosDisponiblesInDTO = new HorariosDisponiblesInDTO();
		horariosDisponiblesInDTO.setNroSuc("000");
		horariosDisponiblesInDTO.setSector("P");
		GetConsultaHorariosDisponiblesSvcResponse response = new GetConsultaHorariosDisponiblesSvcResponse();
		response.setCodigoError(BigDecimal.ZERO);
		ArrayOfHorarioSvcResponse arrayOfHorarioSvcResponse = new ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.datacontracts.ArrayOfHorarioSvcResponse();
		HorarioSvcResponse horarioUno = new HorarioSvcResponse();
		HorarioSvcResponse horarioDos = new HorarioSvcResponse();
		ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.datacontracts.ObjectFactory factory = 
				new ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.datacontracts.ObjectFactory();
		JAXBElement<String> horarioUnoDesc = factory.createHorarioSvcResponseDescri("Descripcion");
		JAXBElement<String> horarioUnoDire = factory.createHorarioSvcResponseDireccion("Bartolome mitre 100");
		JAXBElement<String> horarioUnoFecha = factory.createHorarioSvcResponseFecha("20180404");
		JAXBElement<String> horarioUnoFraccion = factory.createHorarioSvcResponseFraccion("1900-2030");
		JAXBElement<String> horarioUnoIdSuc = factory.createHorarioSvcResponseIdSuc("000");
		JAXBElement<String> horarioUnoLocalidad = factory.createHorarioSvcResponseLocalidad("Capital");
		JAXBElement<String> horarioUnoSector = factory.createHorarioSvcResponseSector("P");
		JAXBElement<String> horarioDosDesc = factory.createHorarioSvcResponseDescri("Descripcion");
		JAXBElement<String> horarioDosDire = factory.createHorarioSvcResponseDireccion("Bartolome mitre 100");
		JAXBElement<String> horarioDosFecha = factory.createHorarioSvcResponseFecha("20180404");
		JAXBElement<String> horarioDosFraccion = factory.createHorarioSvcResponseFraccion("2030-2130");
		JAXBElement<String> horarioDosIdSuc = factory.createHorarioSvcResponseIdSuc("000");
		JAXBElement<String> horarioDosLocalidad = factory.createHorarioSvcResponseLocalidad("Capital");
		JAXBElement<String> horarioDosSector = factory.createHorarioSvcResponseSector("P");
		horarioUno.setDescri(horarioUnoDesc);
		horarioUno.setDireccion(horarioUnoDire);
		horarioUno.setFecha(horarioUnoFecha);
		horarioUno.setFraccion(horarioUnoFraccion);
		horarioUno.setIdSuc(horarioUnoIdSuc);
		horarioUno.setIdTurno((long) 0);
		horarioUno.setLocalidad(horarioUnoLocalidad);
		horarioUno.setSector(horarioUnoSector);
		horarioDos.setDescri(horarioDosDesc);
		horarioDos.setDireccion(horarioDosDire);
		horarioDos.setFecha(horarioDosFecha);
		horarioDos.setFraccion(horarioDosFraccion);
		horarioDos.setIdSuc(horarioDosIdSuc);
		horarioDos.setIdTurno((long) 0);
		horarioDos.setLocalidad(horarioDosLocalidad);
		horarioDos.setSector(horarioDosSector);
		arrayOfHorarioSvcResponse.getHorarioSvcResponse().add(horarioUno);
		arrayOfHorarioSvcResponse.getHorarioSvcResponse().add(horarioDos);
		JAXBElement<ArrayOfHorarioSvcResponse> jaxbElementArrayOfHorarioSvcResponse =  factory.createArrayOfHorarioSvcResponse(arrayOfHorarioSvcResponse);
		response.setHorarios(jaxbElementArrayOfHorarioSvcResponse);
        Sucursal sucursal = new Sucursal();
        sucursal.setDescripcion("Casa central");
        sucursal.setDireccion("Bartolome mitre");
        sucursal.setIdSucursal("000");
        sucursal.setTipoDeOficina("");
        when(consultarSucursalesDAO.consultarSucursalPorId(Matchers.anyString())).thenReturn(sucursal);
		when(turnosWebDAO.consultaHorarioDisponibles(Matchers.any(HorariosDisponiblesInEntity.class))).thenReturn(response);
		Respuesta<HorariosDisponiblesOutDTO> respuesta = turnosWebBO.consultaHorariosDisponibles(horariosDisponiblesInDTO);
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);    
	}


	@Test
	public void consultaHorariosDisponiblesCodigoDeErrorDistintoDeCero() throws DAOException{
		HorariosDisponiblesInDTO horariosDisponiblesInDTO = new HorariosDisponiblesInDTO();
		horariosDisponiblesInDTO.setNroSuc("000");
		horariosDisponiblesInDTO.setSector("P");
		GetConsultaHorariosDisponiblesSvcResponse response = new GetConsultaHorariosDisponiblesSvcResponse();
		response.setCodigoError(BigDecimal.valueOf(1));
		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("PRUEBA");
        when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_ERROR_CONSULTA_HORARIOS_DISPONIBLES)).thenReturn(mensaje);
		when(turnosWebDAO.consultaHorarioDisponibles(Matchers.any(HorariosDisponiblesInEntity.class))).thenReturn(response);
		Respuesta<HorariosDisponiblesOutDTO> respuesta = turnosWebBO.consultaHorariosDisponibles(horariosDisponiblesInDTO);
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);    
	}
	
	@Test
	public void consultaHorariosDisponiblesException() throws DAOException{
		HorariosDisponiblesInDTO horariosDisponiblesInDTO = new HorariosDisponiblesInDTO();
		horariosDisponiblesInDTO.setNroSuc("000");
		horariosDisponiblesInDTO.setSector("P");
		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("PRUEBA");
        when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_ERROR_CONSULTA_HORARIOS_DISPONIBLES)).thenReturn(mensaje);
		when(turnosWebDAO.consultaHorarioDisponibles(Matchers.any(HorariosDisponiblesInEntity.class))).thenThrow(new DAOException());
		Respuesta<HorariosDisponiblesOutDTO> respuesta = turnosWebBO.consultaHorariosDisponibles(horariosDisponiblesInDTO);
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);    
	}

}
