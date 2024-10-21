package ar.com.santanderrio.obp.servicios.agenda.destinatarios.bo;

import org.mockito.runners.MockitoJUnitRunner;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.generated.webservices.alias.ConsultarDatosTitularidadExtendido;
import ar.com.santanderrio.obp.generated.webservices.alias.ConsultarDatosTitularidadExtendidoResponse;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.mock.ConsultarDatosTitularidadExtendidoResponseMock;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.mock.CuentaDTOMock;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.mock.TitularidadExtendidoMock;
import ar.com.santanderrio.obp.servicios.alias.dao.AliasCbuDAO;
import ar.com.santanderrio.obp.servicios.alias.exception.ValidacionAliasInexistenteEliminadoException;
import ar.com.santanderrio.obp.servicios.base.mensaje.entities.MensajeMock;
import ar.com.santanderrio.obp.servicios.clientes.entities.RegistroSesion;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ClienteMock;
import ar.com.santanderrio.obp.servicios.transferencias.bo.impl.TransferenciaPorAliasBOImpl;
import ar.com.santanderrio.obp.servicios.transferencias.entities.TransferenciaPorAliasDTO;
import ar.com.santanderrio.obp.servicios.transferencias.web.view.TransferenciaView;

/**
 * The Class ConsultaDestinatarioCBUTest.
 *
 * @author Manuel.Vargas B041299
 * @version 1
 */
@RunWith(MockitoJUnitRunner.class)
public class ConsultaDestinatarioCBUTest {

	/** The mensaje BO. */
	@Mock
	private MensajeBO mensajeBO;

	/** The sesion parametros. */
	@Mock
	private SesionParametros sesionParametros;

    /** The respuesta factory. */
    @InjectMocks
    @Spy
    private RespuestaFactory respuestaFactory = new RespuestaFactory();

	/** The alias cbu DAO. */
	@Mock
	private AliasCbuDAO aliasCbuDAO;

	/** The transferencia por alias BO. */
	@InjectMocks
	private TransferenciaPorAliasBOImpl transferenciaPorAliasBO;

	private String CBUDestino = "1234567890123456789012";
	private String nroCuentaProdDestino = "0000000001234567";
	private String nroSucursal = "0072";
	private String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.115 Safari/537.36";

	/**
	 * Consulta datos titularidad OK.
	 *
	 * @throws DAOException
	 *             the DAO exception
	 * @throws ValidacionAliasInexistenteEliminadoException
	 *             the validacion alias inexistente eliminado exception
	 * @throws ValidacionAliasCuentaNoActivaException
	 *             the validacion alias cuenta no activa exception
	 * @throws ValidacionAliasNoCoincidenMonedasException
	 *             the validacion alias no coinciden monedas exception
	 * @throws ValidacionAliasNoExisteCuentaBanelcoException
	 *             the validacion alias no existe cuenta banelco exception
	 */
	@Test
	@Ignore
	public void consultaDatosTitularidad_OK()
			throws DAOException, ValidacionAliasInexistenteEliminadoException{

		Cuenta cuenta = new Cuenta();
		cuenta.setTipoCuentaEnum(TipoCuenta.CUENTA_CORRIENTE_PESOS);
		cuenta.setNroSucursal(nroSucursal);
		cuenta.setNroCuentaProducto(nroCuentaProdDestino);
		cuenta.setCbu(CBUDestino);

		TransferenciaView transferenciaView = null;
		ConsultarDatosTitularidadExtendidoResponse completarInfoRio = ConsultarDatosTitularidadExtendidoResponseMock
				.completarInfoRio(TitularidadExtendidoMock.completarInfoTitularidadRio(
						CuentaDTOMock.completarInfoCtaDestinoCCPesos(nroCuentaProdDestino, CBUDestino)));
		Mockito.when(
				aliasCbuDAO.consultarDatosTitularidadExtendido(Matchers.any(ConsultarDatosTitularidadExtendido.class)))
				.thenReturn(completarInfoRio);
		Mockito.when(sesionParametros.getRegistroSession()).thenReturn(obtenerRegistroSesion());
		// //
		// ConsultarDatosTitularidadExtendidoResponse respuesta =
		// altaDestinatarioAliasBO
		// .realizarConsultaWS(ClienteMock.completarInfoCliente(), userAgent,
		// cuenta, "FuncionaAlias014");
		// //
//		Respuesta<TransferenciaPorAliasDTO> respuesta = transferenciaPorAliasBO
//				.consultarDatosTitularidadExtendida(transferenciaView, ClienteMock.completarInfoCliente(), userAgent,
//						cuenta, "FuncionaAlias014");

		Respuesta<ConsultarDatosTitularidadExtendidoResponse> respuesta2 = new Respuesta<ConsultarDatosTitularidadExtendidoResponse>();

		Assert.assertNotNull(respuesta2);
		Assert.assertNotNull(respuesta2.getRespuesta());
		
		//TODO: tests:
//		Assert.assertNotNull(respuesta.getRespuesta().getTitularidadExtendido().getCtaDestino());
//		Assert.assertNotNull(respuesta.getRespuesta().getTitularidadExtendido().getCtaDestino().getNumeroCBU());
//		Assert.assertEquals(respuesta.getRespuesta().getTitularidadExtendido().getCtaDestino().getNumeroCBU(),
//				CBUDestino);
//		Assert.assertEquals(respuesta.getRespuesta().getTitularidadExtendido().getCtaDestino().getNumero(),
//				nroCuentaProdDestino);
	}

	/**
	 * Consulta datos titularidad WARNING. Error Alias 66299.
	 *
	 * @throws DAOException
	 *             the DAO exception
	 * @throws ValidacionAliasInexistenteEliminadoException
	 *             the validacion alias inexistente eliminado exception
	 * @throws ValidacionAliasCuentaNoActivaException
	 *             the validacion alias cuenta no activa exception
	 * @throws ValidacionAliasNoCoincidenMonedasException
	 *             the validacion alias no coinciden monedas exception
	 * @throws ValidacionAliasNoExisteCuentaBanelcoException
	 *             the validacion alias no existe cuenta banelco exception
	 */
	@Test  
	@Ignore
	public void consultaDatosTitularidad_WARNING_err_AliasNoExiste()
			throws DAOException, ValidacionAliasInexistenteEliminadoException  {

		Cuenta cuenta = new Cuenta();
		cuenta.setTipoCuentaEnum(TipoCuenta.CUENTA_CORRIENTE_PESOS);
		cuenta.setNroSucursal(nroSucursal);
		cuenta.setNroCuentaProducto(nroCuentaProdDestino);
		cuenta.setCbu(CBUDestino);

		TransferenciaView transferenciaView = null;
		ConsultarDatosTitularidadExtendidoResponse completarInfoRio = new ConsultarDatosTitularidadExtendidoResponse();
		completarInfoRio.setTitularidadExtendido(null);
		
        Mensaje mensaje = MensajeMock.completarInfoMensaje(CodigoMensajeConstantes.ALIAS_INEXISTENTE_O_ELIMINADO_DESDE_AGENDA,
        		"mensaje");
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
		Mockito.when(aliasCbuDAO.consultarDatosTitularidadExtendido(Matchers.any(ConsultarDatosTitularidadExtendido.class))).thenThrow(new ValidacionAliasInexistenteEliminadoException());
		Mockito.when(sesionParametros.getRegistroSession()).thenReturn(obtenerRegistroSesion());
		Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
		
//		Respuesta<TransferenciaPorAliasDTO> respuesta = transferenciaPorAliasBO
//				.consultarDatosTitularidadExtendida(transferenciaView, ClienteMock.completarInfoCliente(), userAgent,
//						cuenta, "FuncionaAlias014");

		Respuesta<TransferenciaPorAliasDTO> respuesta = new Respuesta<TransferenciaPorAliasDTO>();
		Assert.assertNotNull(respuesta);
		Assert.assertNotNull(respuesta.getItemsMensajeRespuesta());
		Assert.assertEquals(respuesta.getItemsMensajeRespuesta().get(0).getTipoError().toString(), TipoError.ALIAS_ELIMINADO.toString());
	}	
	
	/**
	 * Consulta datos titularidad WARNING. Error desconocido.
	 *
	 * @throws DAOException
	 *             the DAO exception
	 * @throws ValidacionAliasInexistenteEliminadoException
	 *             the validacion alias inexistente eliminado exception
	 * @throws ValidacionAliasCuentaNoActivaException
	 *             the validacion alias cuenta no activa exception
	 * @throws ValidacionAliasNoCoincidenMonedasException
	 *             the validacion alias no coinciden monedas exception
	 * @throws ValidacionAliasNoExisteCuentaBanelcoException
	 *             the validacion alias no existe cuenta banelco exception
	 */
	@Test
	@Ignore
	public void consultaDatosTitularidad_WARNING_errDesconocido()
			throws DAOException, ValidacionAliasInexistenteEliminadoException {

		Cuenta cuenta = new Cuenta();
		cuenta.setTipoCuentaEnum(TipoCuenta.CUENTA_CORRIENTE_PESOS);
		cuenta.setNroSucursal(nroSucursal);
		cuenta.setNroCuentaProducto(nroCuentaProdDestino);
		cuenta.setCbu(CBUDestino);

		TransferenciaView transferenciaView = null;
		ConsultarDatosTitularidadExtendidoResponse completarInfoRio = new ConsultarDatosTitularidadExtendidoResponse();
		completarInfoRio.setTitularidadExtendido(null);
		
        Mensaje mensaje = MensajeMock.completarInfoMensaje(CodigoMensajeConstantes.ALIAS_INEXISTENTE_O_ELIMINADO_DESDE_AGENDA,
        		"mensaje");
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
		Mockito.when(aliasCbuDAO.consultarDatosTitularidadExtendido(Matchers.any(ConsultarDatosTitularidadExtendido.class))).thenReturn(completarInfoRio);
		Mockito.when(sesionParametros.getRegistroSession()).thenReturn(obtenerRegistroSesion());
		Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
		
//		Respuesta<TransferenciaPorAliasDTO> respuesta = transferenciaPorAliasBO
//				.consultarDatosTitularidadExtendida(transferenciaView, ClienteMock.completarInfoCliente(), userAgent,
//						cuenta, "FuncionaAlias014");
		Respuesta<TransferenciaPorAliasDTO> respuesta = new Respuesta<TransferenciaPorAliasDTO>();
		Assert.assertNotNull(respuesta);
		Assert.assertNull(respuesta.getRespuesta());
		Assert.assertNotNull(respuesta.getItemsMensajeRespuesta());
		Assert.assertEquals(EstadoRespuesta.WARNING.toString(), respuesta.getEstadoRespuesta().toString());
	}

	/**
	 * Obtener registro sesion.
	 *
	 * @return the registro sesion
	 */
	private RegistroSesion obtenerRegistroSesion() {
		RegistroSesion rs = new RegistroSesion();
		rs.setIp("180.166.12.93");
		return rs;
	}
}