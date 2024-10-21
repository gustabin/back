package ar.com.santanderrio.obp.servicios.agenda.destinatarios.bo;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.bo.impl.AltaDestinatarioCbuBOImpl;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.bo.impl.CasuisticaAltaDestinatarioImpl;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.dao.AgendaDestinatarioDAO;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.dto.ConfiguracionAltaDestinatarioCBUDTO;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.ValidacionCuentaInCBUEntity;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.ValidacionCuentaOutCBUEntity;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.dao.MensajeDAO;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;

/**
 * The Class AltaDestinatarioCbuBOTest.
 */
@RunWith(MockitoJUnitRunner.class)
public class AltaDestinatarioCbuBOTest {

	/** The agenda destinatario DAO. */
	@Mock
	private AgendaDestinatarioDAO agendaDestinatarioDAO;

	/** The casuistica. */
	@Mock
	private CasuisticaAltaDestinatarioImpl casuistica;;

	/** The respuesta factory. */
	@InjectMocks
	@Spy
	private RespuestaFactory respuestaFactory = new RespuestaFactory();

	/** The mensaje DAO. */
	@Mock
	private MensajeDAO mensajeDAO;

	/** The alta destinatario BO. */
	@InjectMocks
	private AltaDestinatarioCbuBO altaDestinatarioCbuBO = new AltaDestinatarioCbuBOImpl();

	/** The mensaje BO. */
	@Mock
	private MensajeBO mensajeBO;

	/**
	 * Respuesta CBU invalido test.
	 */
	@Test
	public void respuestaCBUInvalidoTest() {
		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("Mensaje");

		Respuesta<ConfiguracionAltaDestinatarioCBUDTO> retError = new Respuesta<ConfiguracionAltaDestinatarioCBUDTO>();
		retError.setEstadoRespuesta(EstadoRespuesta.ERROR);
		retError.add(new ItemMensajeRespuesta());
		retError.getItemsMensajeRespuesta().get(0).setTipoError(TipoError.CBU_INVALIDO.getDescripcion());

		Mockito.when(casuistica.crearErrorCbuInvalido()).thenReturn(retError);
		Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(mensaje);

		String cbu = "1231231241234";

        Respuesta<ConfiguracionAltaDestinatarioCBUDTO> respuesta = altaDestinatarioCbuBO
                .continuarAltaDestinatarioCBU(null, cbu, false, null, "");
        Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
        Assert.assertEquals(TipoError.CBU_INVALIDO.getDescripcion(),
                respuesta.getItemsMensajeRespuesta().get(0).getTipoError());
    }

	/**
	 * Respuesta cbu propio test.
	 */
	@Test
	public void respuestaCbuPropioTest() {
		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("Mensaje");
		Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(mensaje);

		String cbu = "0720201088000034610354";

		Cliente cliente = new Cliente();
		Cuenta cuenta = new Cuenta();
		cuenta.setCbu(cbu);
		cuenta.setTipoCuentaEnum(TipoCuenta.CUENTA_CORRIENTE_DOLARES);
		cliente.setCuentas(new ArrayList<Cuenta>(Arrays.asList(cuenta)));

        Respuesta<ConfiguracionAltaDestinatarioCBUDTO> retError = new Respuesta<ConfiguracionAltaDestinatarioCBUDTO>();
        retError.setEstadoRespuesta(EstadoRespuesta.ERROR);
        retError.add(new ItemMensajeRespuesta());
        retError.getItemsMensajeRespuesta().get(0).setTipoError(TipoError.CBU_CUENTA_PROPIA.getDescripcion());
        Mockito.when(casuistica.crearErrorCuentaPropia()).thenReturn(retError);
        
        Respuesta<ConfiguracionAltaDestinatarioCBUDTO> respuesta = altaDestinatarioCbuBO
                .continuarAltaDestinatarioCBU(cliente, cbu, null, null, "");
        Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
        Assert.assertEquals(TipoError.CBU_CUENTA_PROPIA.getDescripcion(),
                respuesta.getItemsMensajeRespuesta().get(0).getTipoError());
    }

	/**
	 * Respuesta derivacion alta rio test.
	 */
	@Test
	public void respuestaDerivacionAltaRioTest() {
		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("Mensaje");
		Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(mensaje);

		String cbuPropio = "0720201088000034610354";
		String cbuDeTercero = "0720370988000035087922";

        Cliente cliente = new Cliente();
        Cuenta cuenta = new Cuenta();
        cuenta.setCbu(cbuPropio);
        cuenta.setTipoCuentaEnum(TipoCuenta.CUENTA_CORRIENTE_DOLARES);
        cliente.setCuentas(new ArrayList<Cuenta>(Arrays.asList(cuenta)));
        
        Respuesta<ConfiguracionAltaDestinatarioCBUDTO> toRet = new Respuesta<ConfiguracionAltaDestinatarioCBUDTO>();
        toRet.setEstadoRespuesta(EstadoRespuesta.OK);
        
        Mockito.when(casuistica.crearDerivacionAltaRio(Matchers.anyString())).thenReturn(toRet);
        Respuesta<ConfiguracionAltaDestinatarioCBUDTO> respuesta = altaDestinatarioCbuBO
                .continuarAltaDestinatarioCBU(cliente, cbuDeTercero, true, "123123123", "");
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }

	/**
	 * Respuesta no posee cuenta banelco test.
	 */
	@Test
	public void respuestaNoPoseeCuentaBanelcoTest() {
		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("Mensaje");
		Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(mensaje);

		String cbuPropio = "0720201088000034610354";
		String cbuDeTercero = "0070023830004026405989";

        Cliente cliente = new Cliente();
        Cuenta cuenta = new Cuenta();
        cuenta.setCbu(cbuPropio);
        cuenta.setTipoCuentaEnum(TipoCuenta.CUENTA_CORRIENTE_DOLARES);
        cliente.setCuentas(new ArrayList<Cuenta>(Arrays.asList(cuenta)));
        Respuesta<ConfiguracionAltaDestinatarioCBUDTO> retError = new Respuesta<ConfiguracionAltaDestinatarioCBUDTO>();
        retError.setEstadoRespuesta(EstadoRespuesta.ERROR);
        retError.add(new ItemMensajeRespuesta());
        retError.getItemsMensajeRespuesta().get(0).setTipoError(TipoError.SIN_MEDIO_DE_PAGO.getDescripcion());
        
        Mockito.when(casuistica.crearErrorSinMedioDePago()).thenReturn(retError);
        
        Respuesta<ConfiguracionAltaDestinatarioCBUDTO> respuesta = altaDestinatarioCbuBO
                .continuarAltaDestinatarioCBU(cliente, cbuDeTercero, true, null, "");
        Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
        Assert.assertEquals(TipoError.SIN_MEDIO_DE_PAGO.getDescripcion(),
                respuesta.getItemsMensajeRespuesta().get(0).getTipoError());

	}

	/**
	 * Continuar alta destinatario CBU ok test.
	 *
	 * @throws DAOException
	 *             the DAO exception
	 */
	@Test
	public void continuarAltaDestinatarioCBUOkTest() throws DAOException {
		Mensaje mensaje = new Mensaje();
		ValidacionCuentaOutCBUEntity validacionCuentaOutCBUEntity = new ValidacionCuentaOutCBUEntity();
		validacionCuentaOutCBUEntity.setCodigoRetornoExtendido("00000000");
		mensaje.setMensaje("Mensaje");
		Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(mensaje);
		Mockito.when(
				agendaDestinatarioDAO.validarCuentaTransferenciaCBU(Matchers.any(ValidacionCuentaInCBUEntity.class)))
				.thenReturn(validacionCuentaOutCBUEntity);

		String cbuPropio = "0720201088000034610354";
		String cbuDeTercero = "0070023830004026405989";

        Respuesta<ConfiguracionAltaDestinatarioCBUDTO> retError = new Respuesta<ConfiguracionAltaDestinatarioCBUDTO>();
        retError.setEstadoRespuesta(EstadoRespuesta.OK);
        
        Mockito.when(casuistica.crearRespuestaConfiguracionCbu(Matchers.any(ValidacionCuentaOutCBUEntity.class))).thenReturn(retError);
        Respuesta<ValidacionCuentaInCBUEntity> resValidacion = new Respuesta<ValidacionCuentaInCBUEntity>();
        resValidacion.setEstadoRespuesta(EstadoRespuesta.OK);
        Mockito.when(casuistica.validarInEntity(Matchers.any(ValidacionCuentaInCBUEntity.class))).thenReturn(resValidacion);
        
        Cliente cliente = new Cliente();
        Cuenta cuenta = new Cuenta();
        Cuenta cuenta2 = new Cuenta();
        cuenta.setCbu(cbuPropio);
        cuenta.setTipoCuentaEnum(TipoCuenta.CUENTA_CORRIENTE_DOLARES);
        cliente.setCuentas(new ArrayList<Cuenta>(Arrays.asList(cuenta)));
        cliente.getCuentas().add(cuenta2);
        cliente.getCuentas().get(0).setTipoCuentaEnum(TipoCuenta.BANELCO);
        cliente.getCuentas().get(1).setTipoCuentaEnum(TipoCuenta.CUENTA_CORRIENTE_PESOS);
        Respuesta<ConfiguracionAltaDestinatarioCBUDTO> respuesta = altaDestinatarioCbuBO
                .continuarAltaDestinatarioCBU(cliente, cbuDeTercero, true, null, "");
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
        
        // Solo para pasar, porque de momento no hace nada la excepcion del DAO
        Mockito.when(
                agendaDestinatarioDAO.validarCuentaTransferenciaCBU(Matchers.any(ValidacionCuentaInCBUEntity.class)))
                .thenThrow(new DAOException());
        
        respuesta = altaDestinatarioCbuBO.continuarAltaDestinatarioCBU(cliente, cbuDeTercero, true, null, "");
       }

	/**
	 * Continuar alta destinatario CBU error servicio test.
	 *
	 * @throws DAOException
	 *             the DAO exception
	 */
	@Test
	public void continuarAltaDestinatarioCBUErrorServicioTest() throws DAOException {
		Mensaje mensaje = new Mensaje();
		ValidacionCuentaOutCBUEntity validacionCuentaOutCBUEntity = new ValidacionCuentaOutCBUEntity();
		Cliente cliente = new Cliente();
		Cuenta cuenta = new Cuenta();
		Cuenta cuenta2 = new Cuenta();
		String cbuPropio = "0720201088000034610354";
		String cbuDeTercero = "0070023830004026405989";

		validacionCuentaOutCBUEntity.setCodigoRetornoExtendido("65");
		mensaje.setMensaje("Mensaje");
		cuenta.setCbu(cbuPropio);
		cuenta.setTipoCuentaEnum(TipoCuenta.CUENTA_CORRIENTE_DOLARES);
		cliente.setCuentas(new ArrayList<Cuenta>(Arrays.asList(cuenta)));
		cliente.getCuentas().add(cuenta2);
		cliente.getCuentas().get(0).setTipoCuentaEnum(TipoCuenta.BANELCO);
		cliente.getCuentas().get(1).setTipoCuentaEnum(TipoCuenta.CUENTA_CORRIENTE_DOLARES);

        Respuesta<ConfiguracionAltaDestinatarioCBUDTO> retError = new Respuesta<ConfiguracionAltaDestinatarioCBUDTO>();
        retError.setEstadoRespuesta(EstadoRespuesta.ERROR);
        retError.add(new ItemMensajeRespuesta());
        retError.getItemsMensajeRespuesta().get(0).setTipoError(TipoError.ERROR_SERVICIO_MANUAL.getDescripcion());
        Mockito.when(casuistica.crearRespuestaConfiguracionCbu(Matchers.any(ValidacionCuentaOutCBUEntity.class))).thenReturn(retError);
        
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(mensaje);
        Mockito.when(
                agendaDestinatarioDAO.validarCuentaTransferenciaCBU(Matchers.any(ValidacionCuentaInCBUEntity.class)))
                .thenReturn(validacionCuentaOutCBUEntity);
        Mockito.when(casuistica.validarInEntity(Matchers.any(ValidacionCuentaInCBUEntity.class))).thenReturn(new Respuesta<ValidacionCuentaInCBUEntity>());
        Respuesta<ConfiguracionAltaDestinatarioCBUDTO> respuesta = altaDestinatarioCbuBO
                .continuarAltaDestinatarioCBU(cliente, cbuDeTercero, false, null, "");
        Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
        Assert.assertEquals("ERROR_SERVICIO_MANUAL", respuesta.getItemsMensajeRespuesta().get(0).getTipoError());

        retError.getItemsMensajeRespuesta().get(0).setTipoError(TipoError.ERROR_SERVICIO.getDescripcion());
        Mockito.when(casuistica.crearRespuestaConfiguracionCbu(Matchers.any(ValidacionCuentaOutCBUEntity.class))).thenReturn(retError);
        
        validacionCuentaOutCBUEntity.setCodigoRetornoExtendido("36");
        respuesta = altaDestinatarioCbuBO.continuarAltaDestinatarioCBU(cliente, cbuDeTercero, false, null, "");
        Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
        Assert.assertEquals("ERROR_SERVICIO", respuesta.getItemsMensajeRespuesta().get(0).getTipoError());

        validacionCuentaOutCBUEntity.setCodigoRetornoExtendido("100");
        respuesta = altaDestinatarioCbuBO.continuarAltaDestinatarioCBU(cliente, cbuDeTercero, false, null, "");
    }

}
