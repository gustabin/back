package ar.com.santanderrio.obp.servicios.comun.autentificacion.manager;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.generated.webservices.rsa.ActionCode;
import ar.com.santanderrio.obp.servicios.base.mensaje.entities.MensajeMock;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionFactory;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.Desafio;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.RsaDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.manager.impl.AutentificacionManagerImpl;
import ar.com.santanderrio.obp.servicios.comun.challenge.entities.TipoDesafioEnum;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.rsa.web.manager.RsaManager;

/**
 * Test para {@link AutentificacionManagerImpl}.
 *
 * @author emilio.watemberg
 * @since Sep 26, 2016.
 */
@RunWith(MockitoJUnitRunner.class)
@Ignore
public class AutentificacionManagerTest {

	/** The autentificacion manager impl. */
	@InjectMocks
	private AutentificacionManagerImpl autentificacionManagerImpl;

	/** The respuesta factory. */
	@InjectMocks
	@Spy
	RespuestaFactory respuestaFactory;

	/** The sesion parametros. */
	@Spy
	SesionParametros sesionParametros;

	/** The mock factory. */
	@Mock
	private AutentificacionFactory autentificacionFactory;

	/** The rsa manager. */
	@Mock
	private RsaManager rsaManager;
	
	@Mock
	private MensajeBO mensajeBO;

	/** The desafio mock. */
	Desafio<AutentificacionDTO> desafioMock;

	/** The mock respuesta desafio solicitar. */
	Respuesta<AutentificacionDTO> mockRespuestaDesafioSolicitar;

	/** The mock respuesta desafio ejecutar. */
	Respuesta<AutentificacionDTO> mockRespuestaDesafioEjecutar;

	/**
	 * Inits the.
	 */
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		Mockito.when(autentificacionFactory.crearAutentificacion(Matchers.any(Class.class), Matchers.anyInt(),
				Matchers.anyString(), Matchers.anyString(), (AutentificacionDTO) Matchers.anyObject(), Matchers.anyBoolean()))
				.then(new Answer<Desafio<AutentificacionDTO>>() {

					@SuppressWarnings("rawtypes")
					@Override
					public Desafio<AutentificacionDTO> answer(InvocationOnMock invocation) throws Throwable {
						Class clazz = (Class) invocation.getArguments()[0];
						int prioridad = (Integer) invocation.getArguments()[1];
						return crearMockDesafio(clazz, prioridad);
					}

				});

		Mockito.when(autentificacionFactory.crearAutentificacion(Matchers.any(Class.class), Matchers.anyInt(),
				Matchers.anyString(), Matchers.anyString(), Matchers.any(AutentificacionDTO.class)))
				.then(new Answer<Desafio<AutentificacionDTO>>() {

					@SuppressWarnings("rawtypes")
					@Override
					public Desafio<AutentificacionDTO> answer(InvocationOnMock invocation) throws Throwable {
						Class clazz = (Class) invocation.getArguments()[0];
						int prioridad = (Integer) invocation.getArguments()[1];
						return crearMockDesafio(clazz, prioridad);
					}
				});

	}

	/**
	 * Crear mock desafio.
	 *
	 * @param clazz
	 *            the clazz
	 * @param prioridad
	 *            the prioridad
	 * @return the desafio
	 */
	private Desafio<AutentificacionDTO> crearMockDesafio(Class<Desafio<AutentificacionDTO>> clazz, int prioridad) {
		desafioMock = Mockito.mock(clazz);
		Mockito.when(desafioMock.getPrioridad()).thenReturn(prioridad);
		mockRespuestaDesafioSolicitar = new Respuesta<AutentificacionDTO>();
		mockRespuestaDesafioSolicitar.setEstadoRespuesta(EstadoRespuesta.WARNING);
		mockRespuestaDesafioSolicitar.setRespuesta(Mockito.mock(AutentificacionDTO.class));
		ItemMensajeRespuesta mockItemMsj = Mockito.mock(ItemMensajeRespuesta.class);
		List<ItemMensajeRespuesta> listMsj = new ArrayList<ItemMensajeRespuesta>();
		listMsj.add(mockItemMsj);
		mockRespuestaDesafioSolicitar.setItemMensajeRespuesta(listMsj);
		Mockito.when(desafioMock.solicitar()).thenReturn(mockRespuestaDesafioSolicitar);

		mockRespuestaDesafioEjecutar = new Respuesta<AutentificacionDTO>();
		mockRespuestaDesafioEjecutar.setEstadoRespuesta(EstadoRespuesta.OK);
		mockRespuestaDesafioEjecutar.setRespuesta(Mockito.mock(AutentificacionDTO.class));
		mockRespuestaDesafioEjecutar.setItemMensajeRespuesta(listMsj);
		Mockito.when(desafioMock.ejecutar(Matchers.any(AutentificacionDTO.class)))
				.thenReturn(mockRespuestaDesafioEjecutar);
		return desafioMock;
	}

	/**
	 * Analizar riesgo operacion.
	 */
	@Test
	public void analizarRiesgoOperacion() {
		RsaDTO rsaDto = Mockito.mock(RsaDTO.class);
		AutentificacionDTO autentificacionDTO = Mockito.mock(AutentificacionDTO.class);
		Mockito.when(autentificacionDTO.getRsaDTO()).thenReturn(rsaDto);
		Mockito.when(autentificacionDTO.getOperacion()).thenReturn(2);
		Respuesta<ActionCode> respuestaRsaManager = Mockito.mock(Respuesta.class);
		Mockito.when(rsaManager.analizar(rsaDto, Matchers.any(TipoDesafioEnum.class))).thenReturn(respuestaRsaManager);
		Respuesta<AutentificacionDTO> analizarOperacionDeRiesgo = null;

		// RSA ALLOW
		Mockito.when(respuestaRsaManager.getRespuesta()).thenReturn(ActionCode.ALLOW);
		analizarOperacionDeRiesgo = autentificacionManagerImpl
				.analizarRSAyObtenerAutenticacionValida(autentificacionDTO);
		Assert.assertEquals(EstadoRespuesta.OK, analizarOperacionDeRiesgo.getEstadoRespuesta());

		// RSA DENY
		Mockito.when(respuestaRsaManager.getRespuesta()).thenReturn(ActionCode.DENY);
		Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString()))
		.thenReturn(MensajeMock.completarInfoMensaje(CodigoMensajeConstantes.DENY_RSA, "Correspondiente con mensajes error"));
		analizarOperacionDeRiesgo = autentificacionManagerImpl
				.analizarRSAyObtenerAutenticacionValida(autentificacionDTO);
		Assert.assertEquals(EstadoRespuesta.ERROR, analizarOperacionDeRiesgo.getEstadoRespuesta());

		// RSA CHALLENGE
		Mockito.when(respuestaRsaManager.getRespuesta()).thenReturn(ActionCode.CHALLENGE);
		analizarOperacionDeRiesgo = autentificacionManagerImpl
				.analizarRSAyObtenerAutenticacionValida(autentificacionDTO);

		Assert.assertEquals(EstadoRespuesta.WARNING, analizarOperacionDeRiesgo.getEstadoRespuesta());
		Assert.assertNotNull(analizarOperacionDeRiesgo.getRespuesta());

	}

	/**
     * Evaluar desafio operacion de riesgo.
     */
    @Test
    public void evaluarDesafioOperacionDeRiesgo() {
        RsaDTO rsaDto = Mockito.mock(RsaDTO.class);
        AutentificacionDTO autentificacionDTO = new AutentificacionDTO();
        autentificacionDTO.setRsaDTO(rsaDto);
        autentificacionDTO.setOperacion(0);
        Respuesta<ActionCode> respuestaRsaManager = Mockito.mock(Respuesta.class);
        Mockito.when(rsaManager.analizar(rsaDto, Matchers.any(TipoDesafioEnum.class))).thenReturn(respuestaRsaManager);
        
        // Set challenge
        Mockito.when(respuestaRsaManager.getRespuesta()).thenReturn(ActionCode.CHALLENGE);
        Respuesta<AutentificacionDTO> analizarOperacionDeRiesgo = autentificacionManagerImpl
                .analizarRSAyObtenerAutenticacionValida(autentificacionDTO);
        
        Assert.assertEquals(EstadoRespuesta.WARNING, analizarOperacionDeRiesgo.getEstadoRespuesta());

    }

}
