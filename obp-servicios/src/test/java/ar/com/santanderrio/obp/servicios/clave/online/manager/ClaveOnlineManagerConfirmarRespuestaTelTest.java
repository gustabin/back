package ar.com.santanderrio.obp.servicios.clave.online.manager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clave.online.bo.ClaveOnlineBO;
import ar.com.santanderrio.obp.servicios.clave.online.entities.CredencialesClaveOnline;
import ar.com.santanderrio.obp.servicios.clave.online.entities.IdentificadorClienteOutEntity;
import ar.com.santanderrio.obp.servicios.clave.online.entities.PreguntaAutenticacion;
import ar.com.santanderrio.obp.servicios.clave.online.entities.PreguntaValidacionTel;
import ar.com.santanderrio.obp.servicios.clave.online.entities.PreguntasSeguridad;
import ar.com.santanderrio.obp.servicios.clave.online.entities.RespuestaAutenticacion;
import ar.com.santanderrio.obp.servicios.clave.online.entities.ValidacionPreguntaIn;
import ar.com.santanderrio.obp.servicios.clave.online.entities.VerificacionPasosClaveOnline;
import ar.com.santanderrio.obp.servicios.clave.online.manager.impl.ClaveOnlineManagerImpl;
import ar.com.santanderrio.obp.servicios.clave.online.view.DatosAutenticacion;
import ar.com.santanderrio.obp.servicios.clave.online.view.MetodoAutenticacionView;
import ar.com.santanderrio.obp.servicios.clientes.entities.RegistroSesion;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.mensajes.web.manager.MensajeManager;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.login.encrypines.EncryPinesUtil;

@RunWith(MockitoJUnitRunner.class)
@Ignore
public class ClaveOnlineManagerConfirmarRespuestaTelTest {

	/** The claveOnline manager. */
	@InjectMocks
	private ClaveOnlineManagerImpl claveOnlineManager;

	/** The Respuesta factory. */
	@Spy
	private RespuestaFactory RespuestaFactory = new RespuestaFactory();

	@Mock
	private MensajeManager mensajeManager;

	@Mock
	private EstadisticaManager estadisticaManager;

	@Mock
	private SesionParametros sesionParametros;

	@Mock
	private ClaveOnlineBO claveOnlineBO;

	@Mock
	private EncryPinesUtil encryPines;

	@Test
	public void confirmarRespuestaTelOkConOpcionNingunaMetodoAutenticacionTest1() {
		VerificacionPasosClaveOnline verificacionPasosClaveOnline = new VerificacionPasosClaveOnline();
		verificacionPasosClaveOnline.setValidarPreguntaTelefono(true);

		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) + 5);
		Date fechaExpirada = calendar.getTime();
		PreguntasSeguridad preguntasSeguridad = new PreguntasSeguridad();

		CredencialesClaveOnline credencialesClaveOnline = new CredencialesClaveOnline();
		credencialesClaveOnline.setCiclo(0);
		credencialesClaveOnline.setFechaDeNacimiento("20061988");
		credencialesClaveOnline.setRespuesta("NINGUNA");
		credencialesClaveOnline.setCheckWhatsapp(true);

		PreguntasSeguridad preguntasSeguridad2 = new PreguntasSeguridad();
		RegistroSesion registroSesion = new RegistroSesion();

		PreguntaValidacionTel preguntaValidacionTel = new PreguntaValidacionTel();
		preguntaValidacionTel.setOpcionCorrecta("RESPUESTA");
		preguntaValidacionTel.setCheckWhatsappHabilitado(true);
		
		DatosAutenticacion datosAutenticacion = new DatosAutenticacion();
		datosAutenticacion.setTipoAut("    ");
		datosAutenticacion.setNumero("12345678");

		registroSesion.setDatosAutenticacion(datosAutenticacion);

		Respuesta<IdentificadorClienteOutEntity> respuestaValidarTelBo = new Respuesta<IdentificadorClienteOutEntity>();
		IdentificadorClienteOutEntity identificadorClienteOutEntity = new IdentificadorClienteOutEntity();
		respuestaValidarTelBo.setRespuesta(identificadorClienteOutEntity);
		respuestaValidarTelBo.setEstadoRespuesta(EstadoRespuesta.OK);
		respuestaValidarTelBo.getRespuesta().setCiclos("0");
		respuestaValidarTelBo.getRespuesta().setNumero("1231231");

		when(this.sesionParametros.getPreguntaValidacionTel()).thenReturn(preguntaValidacionTel);
		when(this.sesionParametros.getRegistroSession()).thenReturn(registroSesion);
		when(this.claveOnlineBO.validarRespuestaTelefono(Matchers.any(ValidacionPreguntaIn.class))).thenReturn(respuestaValidarTelBo);
		when(this.sesionParametros.getCredencialesClaveOnline()).thenReturn(credencialesClaveOnline);
		when(this.sesionParametros.getPreguntasSeguridad()).thenReturn(preguntasSeguridad, preguntasSeguridad2);
		when(this.sesionParametros.getTimerClaveOnline()).thenReturn(fechaExpirada);
		when(this.sesionParametros.getVerificacionPasosClaveOnline()).thenReturn(verificacionPasosClaveOnline);

		String encriptado = "ISBANCRYPTO_v1.0_WOd+nV5D4zTWxJTwB1St5Tc3rvE7DNp5tvI3dw9rL3WcUXn8g2fPDsx2M+VodPoX3UHNtFJQJunAf8AO1WsLkiNcsjlGiOsF2xvrJr9PJDCVAzC7eDrG4FMrAc4gGMxymAUIU6sSsNpIxx+pB/prHnnRErYest6FUy0JyCDJdJ8wuqUV32JHthalH7LG0cNT5NstUnVRiMxt5fVlMcg3KONID/uspgw/XYl9QCR3BFEpqMFVc/j3BIBnbVbErxJjEL2E/18RDaXLk/VsiAeBX33FuPUIFYLp0L0O3iXpC3AtqeThJQbB0Rb/D6nRbQbOJIc5YXygoymjUUJJsV+boA==|1176BNm7FMotZfoXHcyi6HFxuJpzSdXmnJAnbN5lfPwD3HefyZyjsDv8+PJgVSx5MM9guFSHNZOsVJElMBYuXCuYAhqCn1EpOn+AQeavnNUWozQfKPt7MT3DfgpEfU6NbT7MSx/aEDpfv4fYHzWWq45FQJmmgNhRQJ31PX1BWKYEnMVJuS5a7qOM0on348xD9Uv261+o4tjbhHxZKSMP9Spmog6KVi/i4I2QeYfucylJTWROPHEGRFcOyoRqzVeHb38F3RyecQOwaQC9EyPkEL7dARU7UxvkZhsSuA5ckR1Xe9gQhXOvWzYbIMJp4NlbKUlW+bS7pMACJt6Fd34O3BqLG4fv6/cQ1z7GTAiUPWWvcBjwzbFsLm33JJF1CbHxlwroFBk1S2mySAh0hWHQOBeV8UMkyN2bOa2NYadwg2fx2zcbTlbqYf2fNuPhISbDdyzIjhCPx5vZn1S8KQumEFkdwmny9jD0bzJP8FdQidqxu0wg3Duf233AYwbyMb49/BAgEx9dQo9mj1RoJluE1mG6TPLQvkkN6DOnbnMHn+1yCW357o8pBblzpH00ND3CRracRmPsR5kBkE6RQtXsUxHl1yoghvdZlLELuNhAsCHSl0XU4gLNRsgFkMj8ID+L8/DIeeNF5J15mXduRPwIEl9g65czMSLCQLYBVuClL44yDP6pxYbIccQUX6QClZIF2pGtMlp8fTCOCEb79u0HHNPddxAhKGNGA7q0w0Z42uoClCOnxHxqz02shK4rK3XB96xiUr0J8Z7uSHF9WHSVpNodDK/YxD3HvK/PYsiA1MgIAwoPEpYX0P+ZzYU78JVLlPRkCMzN6a4Muz5BiSkGxaygnmOr9oJzwZmi4BvwpJFcgS58osAp391q0hZuVENz++jZW5cJe2Mbyb0POFhHHQ1JpaH9V6QJXgsQ0i7eE5iOeE9+qi9GpkE+3cUi3O8XfUSBxPzE1Z0lYiE6mEk6oDN3c+w1QkLy1yYAClY0qb5OkRcxNgezQVBhDCtnFxPBMLbkagsSChU3XStbE3/1XJKkpSz5NRHk72gZtFjbhPptAhfTeCtovDQgPU2Ivsn44sz3nKUPql3QQQI43uTfffjBla2GVog1JVW7n58SHwrd9UaSNr5CvvGz6GU8juxdJn872pp4bqBiqbaSpXCMEaoB+g==";
		String soloCrypto = "{\"dniOri\":\"21587183\",\"dni\":\"330A330A330A271A349A297A57A200A349A57A102A\",\"prefijo\":\"143A\",\"sufijo\":\"A\",\"dispositivo\":\"desktop\",\"navegador\":\"Chrome 68\",\"devicePrint\":\"version=3.0.0.0_5&pm_fpua=mozilla/5.0 (windows nt 10.0; win64; x64) applewebkit/537.36 (khtml, like gecko) chrome/68.0.3440.106 safari/537.36|5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36|Win32&pm_fpsc=24|1280|720|680&pm_fpsw=&pm_fptz=-3&pm_fpln=lang=en-US|syslang=|userlang=&pm_fpjv=0&pm_fpco=1&pm_fpasw=internal-pdf-viewer|mhjfbmdgcfjbbpaeojofohoefgiehjai|internal-nacl-plugin&pm_fpan=Netscape&pm_fpacn=Mozilla&pm_fpol=true&pm_fposp=&pm_fpup=&pm_fpsaw=1280&pm_fpspd=24&pm_fpsbd=&pm_fpsdx=&pm_fpsdy=&pm_fpslx=&pm_fpsly=&pm_fpsfse=&pm_fpsui=&pm_os=Windows&pm_brmjv=68&pm_br=Chrome&pm_inpt=&pm_expt=\",\"tipoTeclado\":\"F\",\"fechaDeNacimiento\":\"\"}";

		Mockito.when(encryPines.obtenerSoloCrypto(encriptado)).thenReturn(soloCrypto);
		Mockito.when(encryPines.obtenerViewFromJson(soloCrypto, CredencialesClaveOnline.class)).thenReturn(credencialesClaveOnline);

		Respuesta<MetodoAutenticacionView> respuesta = claveOnlineManager.confirmarRespuestaTelefono(request, encriptado);

		assertNotNull(respuesta);
		assertNotNull(respuesta.getItemsMensajeRespuesta());
		assertEquals(TipoError.METODO_AUTENTICACION.getDescripcion(), respuesta.getItemsMensajeRespuesta().get(0).getTipoError());
		assertEquals(EstadoRespuesta.WARNING, respuesta.getEstadoRespuesta());

	}
	
	@Test
	public void confirmarRespuestaTelOkConOpcionNingunaPreguntasSeguridadTest1() {
		VerificacionPasosClaveOnline verificacionPasosClaveOnline = new VerificacionPasosClaveOnline();
		verificacionPasosClaveOnline.setValidarPreguntaTelefono(true);
		
		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) + 5);
		Date fechaExpirada = calendar.getTime();
		
		List<PreguntaAutenticacion> listPreguntaAutenticacion = new ArrayList<PreguntaAutenticacion>();
        PreguntaAutenticacion preguntaAutenticacion = new PreguntaAutenticacion();
        List<RespuestaAutenticacion> listRespuestaAutenticacion = new ArrayList<RespuestaAutenticacion>();
        RespuestaAutenticacion respuestaAutenticacion = new RespuestaAutenticacion();
        PreguntasSeguridad preguntasSeguridad = new PreguntasSeguridad();
        Respuesta<PreguntasSeguridad> obtenerPreguntasSeguridadBoResponse = new Respuesta<PreguntasSeguridad>();
       
        listRespuestaAutenticacion.add(respuestaAutenticacion);
        preguntaAutenticacion.setRespuestas(listRespuestaAutenticacion);
        listPreguntaAutenticacion.add(preguntaAutenticacion);
        preguntasSeguridad.setPreguntas(listPreguntaAutenticacion);
    
        preguntasSeguridad.setCantidadPreguntas(1);
        preguntasSeguridad.setIndicePregunta(0);
        obtenerPreguntasSeguridadBoResponse.setRespuesta(preguntasSeguridad);
        obtenerPreguntasSeguridadBoResponse.setEstadoRespuesta(EstadoRespuesta.OK);
  
		CredencialesClaveOnline credencialesClaveOnline = new CredencialesClaveOnline();
		credencialesClaveOnline.setCiclo(0);
		credencialesClaveOnline.setFechaDeNacimiento("20061988");
		credencialesClaveOnline.setRespuesta("NINGUNA");
		credencialesClaveOnline.setCheckWhatsapp(true);

		PreguntasSeguridad preguntasSeguridad2 = new PreguntasSeguridad();

		PreguntaValidacionTel preguntaValidacionTel = new PreguntaValidacionTel();
		preguntaValidacionTel.setOpcionCorrecta("RESPUESTA");
		preguntaValidacionTel.setCheckWhatsappHabilitado(true);
		
		RegistroSesion registroSesion = new RegistroSesion();
		DatosAutenticacion datosAutenticacion = new DatosAutenticacion();
		datosAutenticacion.setTipoAut("    ");
		datosAutenticacion.setNumero("12345678");
		registroSesion.setDatosAutenticacion(datosAutenticacion);

		Respuesta<IdentificadorClienteOutEntity> respuestaValidarTelBo = new Respuesta<IdentificadorClienteOutEntity>();
		IdentificadorClienteOutEntity identificadorClienteOutEntity = new IdentificadorClienteOutEntity();
		respuestaValidarTelBo.setRespuesta(identificadorClienteOutEntity);
		respuestaValidarTelBo.setEstadoRespuesta(EstadoRespuesta.OK);
		respuestaValidarTelBo.getRespuesta().setCiclos("1");
		respuestaValidarTelBo.getRespuesta().setNumero("1231231");

	    when(this.claveOnlineBO.obtenerPreguntasSeguridad(Matchers.any(CredencialesClaveOnline.class),Matchers.any(IdentificadorClienteOutEntity.class))).thenReturn(obtenerPreguntasSeguridadBoResponse);
		when(this.sesionParametros.getPreguntaValidacionTel()).thenReturn(preguntaValidacionTel);
		when(this.sesionParametros.getRegistroSession()).thenReturn(registroSesion);
		when(this.claveOnlineBO.validarRespuestaTelefono(Matchers.any(ValidacionPreguntaIn.class))).thenReturn(respuestaValidarTelBo);
		when(this.sesionParametros.getCredencialesClaveOnline()).thenReturn(credencialesClaveOnline);
		when(this.sesionParametros.getPreguntasSeguridad()).thenReturn(preguntasSeguridad, preguntasSeguridad2);
		when(this.sesionParametros.getTimerClaveOnline()).thenReturn(fechaExpirada);
		when(this.sesionParametros.getVerificacionPasosClaveOnline()).thenReturn(verificacionPasosClaveOnline);

		String encriptado = "ISBANCRYPTO_v1.0_WOd+nV5D4zTWxJTwB1St5Tc3rvE7DNp5tvI3dw9rL3WcUXn8g2fPDsx2M+VodPoX3UHNtFJQJunAf8AO1WsLkiNcsjlGiOsF2xvrJr9PJDCVAzC7eDrG4FMrAc4gGMxymAUIU6sSsNpIxx+pB/prHnnRErYest6FUy0JyCDJdJ8wuqUV32JHthalH7LG0cNT5NstUnVRiMxt5fVlMcg3KONID/uspgw/XYl9QCR3BFEpqMFVc/j3BIBnbVbErxJjEL2E/18RDaXLk/VsiAeBX33FuPUIFYLp0L0O3iXpC3AtqeThJQbB0Rb/D6nRbQbOJIc5YXygoymjUUJJsV+boA==|1176BNm7FMotZfoXHcyi6HFxuJpzSdXmnJAnbN5lfPwD3HefyZyjsDv8+PJgVSx5MM9guFSHNZOsVJElMBYuXCuYAhqCn1EpOn+AQeavnNUWozQfKPt7MT3DfgpEfU6NbT7MSx/aEDpfv4fYHzWWq45FQJmmgNhRQJ31PX1BWKYEnMVJuS5a7qOM0on348xD9Uv261+o4tjbhHxZKSMP9Spmog6KVi/i4I2QeYfucylJTWROPHEGRFcOyoRqzVeHb38F3RyecQOwaQC9EyPkEL7dARU7UxvkZhsSuA5ckR1Xe9gQhXOvWzYbIMJp4NlbKUlW+bS7pMACJt6Fd34O3BqLG4fv6/cQ1z7GTAiUPWWvcBjwzbFsLm33JJF1CbHxlwroFBk1S2mySAh0hWHQOBeV8UMkyN2bOa2NYadwg2fx2zcbTlbqYf2fNuPhISbDdyzIjhCPx5vZn1S8KQumEFkdwmny9jD0bzJP8FdQidqxu0wg3Duf233AYwbyMb49/BAgEx9dQo9mj1RoJluE1mG6TPLQvkkN6DOnbnMHn+1yCW357o8pBblzpH00ND3CRracRmPsR5kBkE6RQtXsUxHl1yoghvdZlLELuNhAsCHSl0XU4gLNRsgFkMj8ID+L8/DIeeNF5J15mXduRPwIEl9g65czMSLCQLYBVuClL44yDP6pxYbIccQUX6QClZIF2pGtMlp8fTCOCEb79u0HHNPddxAhKGNGA7q0w0Z42uoClCOnxHxqz02shK4rK3XB96xiUr0J8Z7uSHF9WHSVpNodDK/YxD3HvK/PYsiA1MgIAwoPEpYX0P+ZzYU78JVLlPRkCMzN6a4Muz5BiSkGxaygnmOr9oJzwZmi4BvwpJFcgS58osAp391q0hZuVENz++jZW5cJe2Mbyb0POFhHHQ1JpaH9V6QJXgsQ0i7eE5iOeE9+qi9GpkE+3cUi3O8XfUSBxPzE1Z0lYiE6mEk6oDN3c+w1QkLy1yYAClY0qb5OkRcxNgezQVBhDCtnFxPBMLbkagsSChU3XStbE3/1XJKkpSz5NRHk72gZtFjbhPptAhfTeCtovDQgPU2Ivsn44sz3nKUPql3QQQI43uTfffjBla2GVog1JVW7n58SHwrd9UaSNr5CvvGz6GU8juxdJn872pp4bqBiqbaSpXCMEaoB+g==";
		String soloCrypto = "{\"dniOri\":\"21587183\",\"dni\":\"330A330A330A271A349A297A57A200A349A57A102A\",\"prefijo\":\"143A\",\"sufijo\":\"A\",\"dispositivo\":\"desktop\",\"navegador\":\"Chrome 68\",\"devicePrint\":\"version=3.0.0.0_5&pm_fpua=mozilla/5.0 (windows nt 10.0; win64; x64) applewebkit/537.36 (khtml, like gecko) chrome/68.0.3440.106 safari/537.36|5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36|Win32&pm_fpsc=24|1280|720|680&pm_fpsw=&pm_fptz=-3&pm_fpln=lang=en-US|syslang=|userlang=&pm_fpjv=0&pm_fpco=1&pm_fpasw=internal-pdf-viewer|mhjfbmdgcfjbbpaeojofohoefgiehjai|internal-nacl-plugin&pm_fpan=Netscape&pm_fpacn=Mozilla&pm_fpol=true&pm_fposp=&pm_fpup=&pm_fpsaw=1280&pm_fpspd=24&pm_fpsbd=&pm_fpsdx=&pm_fpsdy=&pm_fpslx=&pm_fpsly=&pm_fpsfse=&pm_fpsui=&pm_os=Windows&pm_brmjv=68&pm_br=Chrome&pm_inpt=&pm_expt=\",\"tipoTeclado\":\"F\",\"fechaDeNacimiento\":\"\"}";

		Mockito.when(encryPines.obtenerSoloCrypto(encriptado)).thenReturn(soloCrypto);
		Mockito.when(encryPines.obtenerViewFromJson(soloCrypto, CredencialesClaveOnline.class)).thenReturn(credencialesClaveOnline);

		Respuesta<MetodoAutenticacionView> respuesta = claveOnlineManager.confirmarRespuestaTelefono(request, encriptado);

		assertNotNull(respuesta);
		assertNotNull(respuesta.getItemsMensajeRespuesta());
		assertEquals(TipoError.PREGUNTAS.getDescripcion(), respuesta.getItemsMensajeRespuesta().get(0).getTipoError());
		assertEquals(EstadoRespuesta.WARNING, respuesta.getEstadoRespuesta());

	}
	
	@Test
	public void confirmarRespuestaTelOkSmsTest1() {
		VerificacionPasosClaveOnline verificacionPasosClaveOnline = new VerificacionPasosClaveOnline();
		verificacionPasosClaveOnline.setValidarPreguntaTelefono(true);

		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) + 5);
		Date fechaExpirada = calendar.getTime();
		PreguntasSeguridad preguntasSeguridad = new PreguntasSeguridad();

		CredencialesClaveOnline credencialesClaveOnline = new CredencialesClaveOnline();
		credencialesClaveOnline.setCiclo(0);
		credencialesClaveOnline.setFechaDeNacimiento("20061988");
		credencialesClaveOnline.setRespuesta("RESPUESTA");
		credencialesClaveOnline.setCheckWhatsapp(true);

		PreguntasSeguridad preguntasSeguridad2 = new PreguntasSeguridad();
		RegistroSesion registroSesion = new RegistroSesion();

		PreguntaValidacionTel preguntaValidacionTel = new PreguntaValidacionTel();
		preguntaValidacionTel.setOpcionCorrecta("RESPUESTA");
		preguntaValidacionTel.setCheckWhatsappHabilitado(true);
		
		DatosAutenticacion datosAutenticacion = new DatosAutenticacion();
		datosAutenticacion.setTipoAut("    ");
		datosAutenticacion.setNumero("12345678");

		registroSesion.setDatosAutenticacion(datosAutenticacion);

		Respuesta<IdentificadorClienteOutEntity> respuestaValidarTelBo = new Respuesta<IdentificadorClienteOutEntity>();
		IdentificadorClienteOutEntity identificadorClienteOutEntity = new IdentificadorClienteOutEntity();
		respuestaValidarTelBo.setRespuesta(identificadorClienteOutEntity);
		respuestaValidarTelBo.setEstadoRespuesta(EstadoRespuesta.OK);
		respuestaValidarTelBo.getRespuesta().setCiclos("0");
		respuestaValidarTelBo.getRespuesta().setNumero("1231231");
		
		
		Respuesta<Void> respuestaValidarClaveBo = this.RespuestaFactory.crearRespuestaOk(Void.class);        

		when(this.claveOnlineBO.validarClave(Matchers.any(DatosAutenticacion.class))).thenReturn(respuestaValidarClaveBo);
		when(this.sesionParametros.getPreguntaValidacionTel()).thenReturn(preguntaValidacionTel);
		when(this.sesionParametros.getRegistroSession()).thenReturn(registroSesion);
		when(this.claveOnlineBO.validarRespuestaTelefono(Matchers.any(ValidacionPreguntaIn.class))).thenReturn(respuestaValidarTelBo);
		when(this.sesionParametros.getCredencialesClaveOnline()).thenReturn(credencialesClaveOnline);
		when(this.sesionParametros.getPreguntasSeguridad()).thenReturn(preguntasSeguridad, preguntasSeguridad2);
		when(this.sesionParametros.getTimerClaveOnline()).thenReturn(fechaExpirada);
		when(this.sesionParametros.getVerificacionPasosClaveOnline()).thenReturn(verificacionPasosClaveOnline);

		String encriptado = "ISBANCRYPTO_v1.0_WOd+nV5D4zTWxJTwB1St5Tc3rvE7DNp5tvI3dw9rL3WcUXn8g2fPDsx2M+VodPoX3UHNtFJQJunAf8AO1WsLkiNcsjlGiOsF2xvrJr9PJDCVAzC7eDrG4FMrAc4gGMxymAUIU6sSsNpIxx+pB/prHnnRErYest6FUy0JyCDJdJ8wuqUV32JHthalH7LG0cNT5NstUnVRiMxt5fVlMcg3KONID/uspgw/XYl9QCR3BFEpqMFVc/j3BIBnbVbErxJjEL2E/18RDaXLk/VsiAeBX33FuPUIFYLp0L0O3iXpC3AtqeThJQbB0Rb/D6nRbQbOJIc5YXygoymjUUJJsV+boA==|1176BNm7FMotZfoXHcyi6HFxuJpzSdXmnJAnbN5lfPwD3HefyZyjsDv8+PJgVSx5MM9guFSHNZOsVJElMBYuXCuYAhqCn1EpOn+AQeavnNUWozQfKPt7MT3DfgpEfU6NbT7MSx/aEDpfv4fYHzWWq45FQJmmgNhRQJ31PX1BWKYEnMVJuS5a7qOM0on348xD9Uv261+o4tjbhHxZKSMP9Spmog6KVi/i4I2QeYfucylJTWROPHEGRFcOyoRqzVeHb38F3RyecQOwaQC9EyPkEL7dARU7UxvkZhsSuA5ckR1Xe9gQhXOvWzYbIMJp4NlbKUlW+bS7pMACJt6Fd34O3BqLG4fv6/cQ1z7GTAiUPWWvcBjwzbFsLm33JJF1CbHxlwroFBk1S2mySAh0hWHQOBeV8UMkyN2bOa2NYadwg2fx2zcbTlbqYf2fNuPhISbDdyzIjhCPx5vZn1S8KQumEFkdwmny9jD0bzJP8FdQidqxu0wg3Duf233AYwbyMb49/BAgEx9dQo9mj1RoJluE1mG6TPLQvkkN6DOnbnMHn+1yCW357o8pBblzpH00ND3CRracRmPsR5kBkE6RQtXsUxHl1yoghvdZlLELuNhAsCHSl0XU4gLNRsgFkMj8ID+L8/DIeeNF5J15mXduRPwIEl9g65czMSLCQLYBVuClL44yDP6pxYbIccQUX6QClZIF2pGtMlp8fTCOCEb79u0HHNPddxAhKGNGA7q0w0Z42uoClCOnxHxqz02shK4rK3XB96xiUr0J8Z7uSHF9WHSVpNodDK/YxD3HvK/PYsiA1MgIAwoPEpYX0P+ZzYU78JVLlPRkCMzN6a4Muz5BiSkGxaygnmOr9oJzwZmi4BvwpJFcgS58osAp391q0hZuVENz++jZW5cJe2Mbyb0POFhHHQ1JpaH9V6QJXgsQ0i7eE5iOeE9+qi9GpkE+3cUi3O8XfUSBxPzE1Z0lYiE6mEk6oDN3c+w1QkLy1yYAClY0qb5OkRcxNgezQVBhDCtnFxPBMLbkagsSChU3XStbE3/1XJKkpSz5NRHk72gZtFjbhPptAhfTeCtovDQgPU2Ivsn44sz3nKUPql3QQQI43uTfffjBla2GVog1JVW7n58SHwrd9UaSNr5CvvGz6GU8juxdJn872pp4bqBiqbaSpXCMEaoB+g==";
		String soloCrypto = "{\"dniOri\":\"21587183\",\"dni\":\"330A330A330A271A349A297A57A200A349A57A102A\",\"prefijo\":\"143A\",\"sufijo\":\"A\",\"dispositivo\":\"desktop\",\"navegador\":\"Chrome 68\",\"devicePrint\":\"version=3.0.0.0_5&pm_fpua=mozilla/5.0 (windows nt 10.0; win64; x64) applewebkit/537.36 (khtml, like gecko) chrome/68.0.3440.106 safari/537.36|5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36|Win32&pm_fpsc=24|1280|720|680&pm_fpsw=&pm_fptz=-3&pm_fpln=lang=en-US|syslang=|userlang=&pm_fpjv=0&pm_fpco=1&pm_fpasw=internal-pdf-viewer|mhjfbmdgcfjbbpaeojofohoefgiehjai|internal-nacl-plugin&pm_fpan=Netscape&pm_fpacn=Mozilla&pm_fpol=true&pm_fposp=&pm_fpup=&pm_fpsaw=1280&pm_fpspd=24&pm_fpsbd=&pm_fpsdx=&pm_fpsdy=&pm_fpslx=&pm_fpsly=&pm_fpsfse=&pm_fpsui=&pm_os=Windows&pm_brmjv=68&pm_br=Chrome&pm_inpt=&pm_expt=\",\"tipoTeclado\":\"F\",\"fechaDeNacimiento\":\"\"}";

		Mockito.when(encryPines.obtenerSoloCrypto(encriptado)).thenReturn(soloCrypto);
		Mockito.when(encryPines.obtenerViewFromJson(soloCrypto, CredencialesClaveOnline.class)).thenReturn(credencialesClaveOnline);

		Respuesta<MetodoAutenticacionView> respuesta = claveOnlineManager.confirmarRespuestaTelefono(request, encriptado);

		assertNotNull(respuesta);
		assertNotNull(respuesta.getItemsMensajeRespuesta());
		assertEquals(TipoError.METODO_AUTENTICACION.getDescripcion(), respuesta.getItemsMensajeRespuesta().get(0).getTipoError());
		assertEquals(EstadoRespuesta.WARNING, respuesta.getEstadoRespuesta());

	}
	
	@Test
	public void confirmarRespuestaTelErrorValTelTest1() {
		VerificacionPasosClaveOnline verificacionPasosClaveOnline = new VerificacionPasosClaveOnline();
		verificacionPasosClaveOnline.setValidarPreguntaTelefono(true);

		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) + 5);
		Date fechaExpirada = calendar.getTime();

		CredencialesClaveOnline credencialesClaveOnline = new CredencialesClaveOnline();
		credencialesClaveOnline.setCiclo(0);
		credencialesClaveOnline.setFechaDeNacimiento("20061988");
		credencialesClaveOnline.setRespuesta("RESPUESTA");
		credencialesClaveOnline.setCheckWhatsapp(true);


		RegistroSesion registroSesion = new RegistroSesion();

		PreguntaValidacionTel preguntaValidacionTel = new PreguntaValidacionTel();
		preguntaValidacionTel.setOpcionCorrecta("RESPUESTA");
		preguntaValidacionTel.setCheckWhatsappHabilitado(true);

		DatosAutenticacion datosAutenticacion = new DatosAutenticacion();
		datosAutenticacion.setTipoAut("    ");
		datosAutenticacion.setNumero("12345678");

		registroSesion.setDatosAutenticacion(datosAutenticacion);

		Respuesta<IdentificadorClienteOutEntity> respuestaValidarTelBo = new Respuesta<IdentificadorClienteOutEntity>();
		IdentificadorClienteOutEntity identificadorClienteOutEntity = new IdentificadorClienteOutEntity();
		respuestaValidarTelBo.setRespuesta(identificadorClienteOutEntity);
		respuestaValidarTelBo.setEstadoRespuesta(EstadoRespuesta.ERROR);
	
		when(this.sesionParametros.getPreguntaValidacionTel()).thenReturn(preguntaValidacionTel);
		when(this.sesionParametros.getRegistroSession()).thenReturn(registroSesion);
		when(this.claveOnlineBO.validarRespuestaTelefono(Matchers.any(ValidacionPreguntaIn.class))).thenReturn(respuestaValidarTelBo);
		when(this.sesionParametros.getCredencialesClaveOnline()).thenReturn(credencialesClaveOnline);
		when(this.sesionParametros.getTimerClaveOnline()).thenReturn(fechaExpirada);
		when(this.sesionParametros.getVerificacionPasosClaveOnline()).thenReturn(verificacionPasosClaveOnline);
		
		String encriptado = "ISBANCRYPTO_v1.0_WOd+nV5D4zTWxJTwB1St5Tc3rvE7DNp5tvI3dw9rL3WcUXn8g2fPDsx2M+VodPoX3UHNtFJQJunAf8AO1WsLkiNcsjlGiOsF2xvrJr9PJDCVAzC7eDrG4FMrAc4gGMxymAUIU6sSsNpIxx+pB/prHnnRErYest6FUy0JyCDJdJ8wuqUV32JHthalH7LG0cNT5NstUnVRiMxt5fVlMcg3KONID/uspgw/XYl9QCR3BFEpqMFVc/j3BIBnbVbErxJjEL2E/18RDaXLk/VsiAeBX33FuPUIFYLp0L0O3iXpC3AtqeThJQbB0Rb/D6nRbQbOJIc5YXygoymjUUJJsV+boA==|1176BNm7FMotZfoXHcyi6HFxuJpzSdXmnJAnbN5lfPwD3HefyZyjsDv8+PJgVSx5MM9guFSHNZOsVJElMBYuXCuYAhqCn1EpOn+AQeavnNUWozQfKPt7MT3DfgpEfU6NbT7MSx/aEDpfv4fYHzWWq45FQJmmgNhRQJ31PX1BWKYEnMVJuS5a7qOM0on348xD9Uv261+o4tjbhHxZKSMP9Spmog6KVi/i4I2QeYfucylJTWROPHEGRFcOyoRqzVeHb38F3RyecQOwaQC9EyPkEL7dARU7UxvkZhsSuA5ckR1Xe9gQhXOvWzYbIMJp4NlbKUlW+bS7pMACJt6Fd34O3BqLG4fv6/cQ1z7GTAiUPWWvcBjwzbFsLm33JJF1CbHxlwroFBk1S2mySAh0hWHQOBeV8UMkyN2bOa2NYadwg2fx2zcbTlbqYf2fNuPhISbDdyzIjhCPx5vZn1S8KQumEFkdwmny9jD0bzJP8FdQidqxu0wg3Duf233AYwbyMb49/BAgEx9dQo9mj1RoJluE1mG6TPLQvkkN6DOnbnMHn+1yCW357o8pBblzpH00ND3CRracRmPsR5kBkE6RQtXsUxHl1yoghvdZlLELuNhAsCHSl0XU4gLNRsgFkMj8ID+L8/DIeeNF5J15mXduRPwIEl9g65czMSLCQLYBVuClL44yDP6pxYbIccQUX6QClZIF2pGtMlp8fTCOCEb79u0HHNPddxAhKGNGA7q0w0Z42uoClCOnxHxqz02shK4rK3XB96xiUr0J8Z7uSHF9WHSVpNodDK/YxD3HvK/PYsiA1MgIAwoPEpYX0P+ZzYU78JVLlPRkCMzN6a4Muz5BiSkGxaygnmOr9oJzwZmi4BvwpJFcgS58osAp391q0hZuVENz++jZW5cJe2Mbyb0POFhHHQ1JpaH9V6QJXgsQ0i7eE5iOeE9+qi9GpkE+3cUi3O8XfUSBxPzE1Z0lYiE6mEk6oDN3c+w1QkLy1yYAClY0qb5OkRcxNgezQVBhDCtnFxPBMLbkagsSChU3XStbE3/1XJKkpSz5NRHk72gZtFjbhPptAhfTeCtovDQgPU2Ivsn44sz3nKUPql3QQQI43uTfffjBla2GVog1JVW7n58SHwrd9UaSNr5CvvGz6GU8juxdJn872pp4bqBiqbaSpXCMEaoB+g==";
		String soloCrypto = "{\"dniOri\":\"21587183\",\"dni\":\"330A330A330A271A349A297A57A200A349A57A102A\",\"prefijo\":\"143A\",\"sufijo\":\"A\",\"dispositivo\":\"desktop\",\"navegador\":\"Chrome 68\",\"devicePrint\":\"version=3.0.0.0_5&pm_fpua=mozilla/5.0 (windows nt 10.0; win64; x64) applewebkit/537.36 (khtml, like gecko) chrome/68.0.3440.106 safari/537.36|5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36|Win32&pm_fpsc=24|1280|720|680&pm_fpsw=&pm_fptz=-3&pm_fpln=lang=en-US|syslang=|userlang=&pm_fpjv=0&pm_fpco=1&pm_fpasw=internal-pdf-viewer|mhjfbmdgcfjbbpaeojofohoefgiehjai|internal-nacl-plugin&pm_fpan=Netscape&pm_fpacn=Mozilla&pm_fpol=true&pm_fposp=&pm_fpup=&pm_fpsaw=1280&pm_fpspd=24&pm_fpsbd=&pm_fpsdx=&pm_fpsdy=&pm_fpslx=&pm_fpsly=&pm_fpsfse=&pm_fpsui=&pm_os=Windows&pm_brmjv=68&pm_br=Chrome&pm_inpt=&pm_expt=\",\"tipoTeclado\":\"F\",\"fechaDeNacimiento\":\"\"}";

		Mockito.when(encryPines.obtenerSoloCrypto(encriptado)).thenReturn(soloCrypto);
		Mockito.when(encryPines.obtenerViewFromJson(soloCrypto, CredencialesClaveOnline.class)).thenReturn(credencialesClaveOnline);

		Respuesta<MetodoAutenticacionView> respuesta = claveOnlineManager.confirmarRespuestaTelefono(request, encriptado);

		assertNotNull(respuesta);
		assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());

	}
	
	@Test
	public void confirmarRespuestaTelErrorPasoVerificacionTest1() {
		VerificacionPasosClaveOnline verificacionPasosClaveOnline = new VerificacionPasosClaveOnline();
		verificacionPasosClaveOnline.setValidarPreguntaTelefono(false);

		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) + 5);
		Date fechaExpirada = calendar.getTime();

		CredencialesClaveOnline credencialesClaveOnline = new CredencialesClaveOnline();
		credencialesClaveOnline.setCiclo(0);
		credencialesClaveOnline.setFechaDeNacimiento("20061988");
		credencialesClaveOnline.setRespuesta("RESPUESTA");



		when(this.sesionParametros.getTimerClaveOnline()).thenReturn(fechaExpirada);
		when(this.sesionParametros.getVerificacionPasosClaveOnline()).thenReturn(verificacionPasosClaveOnline);

		String encriptado = "ISBANCRYPTO_v1.0_WOd+nV5D4zTWxJTwB1St5Tc3rvE7DNp5tvI3dw9rL3WcUXn8g2fPDsx2M+VodPoX3UHNtFJQJunAf8AO1WsLkiNcsjlGiOsF2xvrJr9PJDCVAzC7eDrG4FMrAc4gGMxymAUIU6sSsNpIxx+pB/prHnnRErYest6FUy0JyCDJdJ8wuqUV32JHthalH7LG0cNT5NstUnVRiMxt5fVlMcg3KONID/uspgw/XYl9QCR3BFEpqMFVc/j3BIBnbVbErxJjEL2E/18RDaXLk/VsiAeBX33FuPUIFYLp0L0O3iXpC3AtqeThJQbB0Rb/D6nRbQbOJIc5YXygoymjUUJJsV+boA==|1176BNm7FMotZfoXHcyi6HFxuJpzSdXmnJAnbN5lfPwD3HefyZyjsDv8+PJgVSx5MM9guFSHNZOsVJElMBYuXCuYAhqCn1EpOn+AQeavnNUWozQfKPt7MT3DfgpEfU6NbT7MSx/aEDpfv4fYHzWWq45FQJmmgNhRQJ31PX1BWKYEnMVJuS5a7qOM0on348xD9Uv261+o4tjbhHxZKSMP9Spmog6KVi/i4I2QeYfucylJTWROPHEGRFcOyoRqzVeHb38F3RyecQOwaQC9EyPkEL7dARU7UxvkZhsSuA5ckR1Xe9gQhXOvWzYbIMJp4NlbKUlW+bS7pMACJt6Fd34O3BqLG4fv6/cQ1z7GTAiUPWWvcBjwzbFsLm33JJF1CbHxlwroFBk1S2mySAh0hWHQOBeV8UMkyN2bOa2NYadwg2fx2zcbTlbqYf2fNuPhISbDdyzIjhCPx5vZn1S8KQumEFkdwmny9jD0bzJP8FdQidqxu0wg3Duf233AYwbyMb49/BAgEx9dQo9mj1RoJluE1mG6TPLQvkkN6DOnbnMHn+1yCW357o8pBblzpH00ND3CRracRmPsR5kBkE6RQtXsUxHl1yoghvdZlLELuNhAsCHSl0XU4gLNRsgFkMj8ID+L8/DIeeNF5J15mXduRPwIEl9g65czMSLCQLYBVuClL44yDP6pxYbIccQUX6QClZIF2pGtMlp8fTCOCEb79u0HHNPddxAhKGNGA7q0w0Z42uoClCOnxHxqz02shK4rK3XB96xiUr0J8Z7uSHF9WHSVpNodDK/YxD3HvK/PYsiA1MgIAwoPEpYX0P+ZzYU78JVLlPRkCMzN6a4Muz5BiSkGxaygnmOr9oJzwZmi4BvwpJFcgS58osAp391q0hZuVENz++jZW5cJe2Mbyb0POFhHHQ1JpaH9V6QJXgsQ0i7eE5iOeE9+qi9GpkE+3cUi3O8XfUSBxPzE1Z0lYiE6mEk6oDN3c+w1QkLy1yYAClY0qb5OkRcxNgezQVBhDCtnFxPBMLbkagsSChU3XStbE3/1XJKkpSz5NRHk72gZtFjbhPptAhfTeCtovDQgPU2Ivsn44sz3nKUPql3QQQI43uTfffjBla2GVog1JVW7n58SHwrd9UaSNr5CvvGz6GU8juxdJn872pp4bqBiqbaSpXCMEaoB+g==";
		String soloCrypto = "{\"dniOri\":\"21587183\",\"dni\":\"330A330A330A271A349A297A57A200A349A57A102A\",\"prefijo\":\"143A\",\"sufijo\":\"A\",\"dispositivo\":\"desktop\",\"navegador\":\"Chrome 68\",\"devicePrint\":\"version=3.0.0.0_5&pm_fpua=mozilla/5.0 (windows nt 10.0; win64; x64) applewebkit/537.36 (khtml, like gecko) chrome/68.0.3440.106 safari/537.36|5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36|Win32&pm_fpsc=24|1280|720|680&pm_fpsw=&pm_fptz=-3&pm_fpln=lang=en-US|syslang=|userlang=&pm_fpjv=0&pm_fpco=1&pm_fpasw=internal-pdf-viewer|mhjfbmdgcfjbbpaeojofohoefgiehjai|internal-nacl-plugin&pm_fpan=Netscape&pm_fpacn=Mozilla&pm_fpol=true&pm_fposp=&pm_fpup=&pm_fpsaw=1280&pm_fpspd=24&pm_fpsbd=&pm_fpsdx=&pm_fpsdy=&pm_fpslx=&pm_fpsly=&pm_fpsfse=&pm_fpsui=&pm_os=Windows&pm_brmjv=68&pm_br=Chrome&pm_inpt=&pm_expt=\",\"tipoTeclado\":\"F\",\"fechaDeNacimiento\":\"\"}";

		Mockito.when(encryPines.obtenerSoloCrypto(encriptado)).thenReturn(soloCrypto);
		Mockito.when(encryPines.obtenerViewFromJson(soloCrypto, CredencialesClaveOnline.class)).thenReturn(credencialesClaveOnline);

		Respuesta<MetodoAutenticacionView> respuesta = claveOnlineManager.confirmarRespuestaTelefono(request, encriptado);

		assertNotNull(respuesta);
		assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());

	}
	

}
