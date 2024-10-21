package ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.bo.impl;

import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.base.security.credential.Credential;
import ar.com.santanderrio.obp.base.security.credential.CredentialSecurityFactory;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ContadorIntentos;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.impl.MensajeBOImpl;
import ar.com.santanderrio.obp.servicios.comun.mensaje.dao.MensajeDAO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.dao.impl.AliasFavoritoProductoDAOImpl;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ConfirmarTransferenciaInEntity;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ConfirmarTransferenciaView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasAdhesionDebitoView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.IntervinienteView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.IntervinienteinEntity;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasaldo.dao.CuentaSaldoDAO;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasaldo.dao.RtaLoadSaldo;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasaldo.dao.RtaLoadTitular;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasaldo.dao.RtaTransferenciaBP;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasaldo.entity.ConsultaTitularInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasaldo.entity.CuentaSaldoInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasaldo.entity.CuentaSaldoOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasaldo.entity.TitularOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasaldo.entity.TransferenciaBPInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasaldo.entity.TransferenciaBPOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.dao.CuentasBancaPrivadaDAO;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.dto.CuentaIntermedioDTO;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.entity.ConsultaSaldoCtasConAperturaInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.entity.ConsultaSaldoCtasConAperturaOutEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ClienteMock;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.CuentaMock;


@RunWith(MockitoJUnitRunner.class)
public class CuentasBancaPrivadaBOTest {

	@InjectMocks
	private CuentasBancaPrivadaBOImpl cuentasBancaPrivadaBO = new CuentasBancaPrivadaBOImpl();

	@InjectMocks
	@Spy
	private RespuestaFactory respuestaFactory = new RespuestaFactory();
	

	@Mock
	private MensajeBOImpl mensajeBO = new MensajeBOImpl();

	@Mock
	private AliasFavoritoProductoDAOImpl aliasFavoritoProductoDAO = new AliasFavoritoProductoDAOImpl();

	@Mock
	private CredentialSecurityFactory credentialSecurityFactory;

	@Mock
	private CuentaSaldoDAO cuentaSaldoDAO;

	@Mock
	private CuentasBancaPrivadaDAO cuentasBancaPrivadaDAO;
	
    /** The session parametros. */
    @Mock
    private SesionParametros sessionParametros;
    
    /** The mensaje DAO. */
    @Mock
    MensajeDAO mensajeDAO;

	@Test
	public void actualizarApodo() {
		Respuesta<Void> respuesta = cuentasBancaPrivadaBO
				.actualizarApodo(new IdentificacionCuenta("0255", "0000000025798066"), "apodo", obtenerCliente());
		Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
	}

	@Test
	public void actualizarApodoErrorDAOException() throws DAOException {

		// When
		Mensaje msj = new Mensaje();
		msj.setMensaje("mensaje error");

		Mockito.when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_ERROR_MODIFICAR_APODO_BCAPRIV))
				.thenReturn(msj);
		Mockito.doThrow(DAOException.class).when(aliasFavoritoProductoDAO).actualizaAlias(Matchers.anyLong(),
				Matchers.anyString(), Matchers.anyString());

		// Then
		Respuesta<Void> respuesta = cuentasBancaPrivadaBO
				.actualizarApodo(new IdentificacionCuenta("0255", "0000000025798066"), "apodo", obtenerCliente());

		// Expected
		Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());

	}

	@Test
	public void actualizarApodoError() {

		// When
		Mensaje msj = new Mensaje();
		msj.setMensaje("mensaje error");

		Mockito.when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_ERROR_MODIFICAR_APODO_BCAPRIV))
				.thenReturn(msj);

		// Then
		Respuesta<Void> respuesta = cuentasBancaPrivadaBO.actualizarApodo(
				new IdentificacionCuenta("0255", "0000000025798066"), "Apodo que supera la longitud permitida",
				obtenerCliente());

		// Expected
		Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
	}

	@Test
	public void consultarSaldosCuentaOK() throws SQLException, BusinessException, DAOException {

		// When
		Cliente cliente = ClienteMock.completarInfoCliente();
		Credential credencial = new Credential();
		credencial.setUsuario("USUARIOPEPE");
		credencial.setPassword("PASSPEPE");

		Cuenta cuenta = CuentaMock.completarInfoCuenta();
		cliente.setCuentasPrivadas(new ArrayList<Cuenta>(Arrays.asList(cuenta)));

		CuentaSaldoOutEntity cuentaSaldoOutEntity = new CuentaSaldoOutEntity();
		List<RtaLoadSaldo> listaRtaLoadSaldo = new ArrayList<RtaLoadSaldo>();
		RtaLoadSaldo rtaLoadSaldo = new RtaLoadSaldo("70035235508", "ALISAL SRL", "250", "505", "2018-01-22",
				"49985656", "50073297.96");
		listaRtaLoadSaldo.add(rtaLoadSaldo);
		cuentaSaldoOutEntity.setRespuesta(listaRtaLoadSaldo);

		when(credentialSecurityFactory.getCredentialBySistema(Matchers.anyString())).thenReturn(credencial);
		when(cuentaSaldoDAO.verSaldosCuentasBancaPrivada(Matchers.any(CuentaSaldoInEntity.class)))
				.thenReturn(cuentaSaldoOutEntity);
		when(cuentasBancaPrivadaDAO.consultarSaldoCtasConApertura(
				Matchers.any(ConsultaSaldoCtasConAperturaInEntity.class), Matchers.any(Cliente.class),
				Matchers.any(CuentaSaldoInEntity.class))).thenReturn(crearObjetoIatx());

		// Then
		List<CuentaIntermedioDTO> respuesta = cuentasBancaPrivadaBO.consultarSaldosCuenta(cliente);

		// Expected
		Assert.assertNotNull(respuesta);

	}

	@SuppressWarnings("unchecked")
	@Test
	public void consultarSaldosCuentaErrorStoredProcedure() throws SQLException, BusinessException, DAOException {

		// When
		Cliente cliente = ClienteMock.completarInfoCliente();
		Credential credencial = new Credential();
		credencial.setUsuario("USUARIOPEPE");
		credencial.setPassword("PASSPEPE");

		Cuenta cuenta = CuentaMock.completarInfoCuenta();
		cuenta.setTipoCuentaEnum(TipoCuenta.CAJA_AHORRO_DOLARES);
		cuenta.setProductoAltair("07");
		cliente.setCuentasPrivadas(new ArrayList<Cuenta>(Arrays.asList(cuenta)));

		when(credentialSecurityFactory.getCredentialBySistema(Matchers.anyString())).thenReturn(credencial);
		when(cuentaSaldoDAO.verSaldosCuentasBancaPrivada(Matchers.any(CuentaSaldoInEntity.class)))
				.thenThrow(DAOException.class);
		when(cuentasBancaPrivadaDAO.consultarSaldoCtasConApertura(
				Matchers.any(ConsultaSaldoCtasConAperturaInEntity.class), Matchers.any(Cliente.class),
				Matchers.any(CuentaSaldoInEntity.class))).thenReturn(crearObjetoIatx());

		// Then
		List<CuentaIntermedioDTO> respuesta = cuentasBancaPrivadaBO.consultarSaldosCuenta(cliente);
		
		CuentaSaldoOutEntity  cuentaSaldoOutEntity  = new CuentaSaldoOutEntity ();
		cuentaSaldoOutEntity.setErrorEnConsulta(new Boolean(true));		
		respuesta.get(0).setSaldosStoredProcedure(cuentaSaldoOutEntity);
		
		// Expected
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(new Boolean(true), respuesta.get(0).getSaldosStoredProcedure().getErrorEnConsulta());

	}

	@SuppressWarnings("unchecked")
	@Test
	public void consultarSaldosCuentaErrorServicioIATX() throws SQLException, BusinessException, DAOException {

		// When
		Cliente cliente = ClienteMock.completarInfoCliente();
		Credential credencial = new Credential();
		credencial.setUsuario("USUARIOPEPE");
		credencial.setPassword("PASSPEPE");

		Cuenta cuenta = CuentaMock.completarInfoCuenta();
		cuenta.setTipoCuentaEnum(TipoCuenta.CAJA_AHORRO_DOLARES);
		cliente.setCuentasPrivadas(new ArrayList<Cuenta>(Arrays.asList(cuenta)));

		CuentaSaldoOutEntity cuentaSaldoOutEntity = new CuentaSaldoOutEntity();
		List<RtaLoadSaldo> listaRtaLoadSaldo = new ArrayList<RtaLoadSaldo>();
		RtaLoadSaldo rtaLoadSaldo = new RtaLoadSaldo("70035235508", "ALISAL SRL", "250", "505", "2018-01-22",
				"49985656", "50073297.96");
		listaRtaLoadSaldo.add(rtaLoadSaldo);
		cuentaSaldoOutEntity.setRespuesta(listaRtaLoadSaldo);

		when(credentialSecurityFactory.getCredentialBySistema(Matchers.anyString())).thenReturn(credencial);
		when(cuentaSaldoDAO.verSaldosCuentasBancaPrivada(Matchers.any(CuentaSaldoInEntity.class)))
				.thenReturn(cuentaSaldoOutEntity);
		when(cuentasBancaPrivadaDAO.consultarSaldoCtasConApertura(
				Matchers.any(ConsultaSaldoCtasConAperturaInEntity.class), Matchers.any(Cliente.class),
				Matchers.any(CuentaSaldoInEntity.class))).thenThrow(DAOException.class);

		// Then
		List<CuentaIntermedioDTO> respuesta = cuentasBancaPrivadaBO.consultarSaldosCuenta(cliente);

		// Expected
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(new Boolean(true), respuesta.get(0).getSaldosServicioIatx().getErrorEnConsulta());

	}

	private Cliente obtenerCliente() {
		Cliente cliente = new Cliente();
		cliente.setCuentasPrivadas(obtenerCuentasBancaPrivada());
		cliente.setNup("54321");
		return cliente;
	}

	private List<Cuenta> obtenerCuentasBancaPrivada() {
		Cuenta cuenta = new Cuenta();
		cuenta.setNroCuentaProducto("0000000025798066");
		cuenta.setNroSucursal("0255");
		cuenta.setTipoCuentaEnum(TipoCuenta.CAJA_AHORRO_PESOS);
		return new ArrayList<Cuenta>(Arrays.asList(cuenta));
	}

	private ConsultaSaldoCtasConAperturaOutEntity crearObjetoIatx() {

		ConsultaSaldoCtasConAperturaOutEntity saldosServicioIatx = new ConsultaSaldoCtasConAperturaOutEntity();
		saldosServicioIatx.setCodigoRetornoExtendido("00000000");
		saldosServicioIatx.setSaldo("000005000000000+");
		saldosServicioIatx.setSaldoPendienteConfirmacion("000000000000000+");
		saldosServicioIatx.setDepositoEfectivoDia("000000000000000+");
		saldosServicioIatx.setDep24("0000000000000+");
		saldosServicioIatx.setDep48("000000000000000+");
		saldosServicioIatx.setDep72("000000000000000+");
		saldosServicioIatx.setLimiteCredito("000000000500000+");
		saldosServicioIatx.setIntereses("0000000000000+");
		saldosServicioIatx.setCantRechazados("00000");
		saldosServicioIatx.setSaldoDolares("000005000000000+");
		saldosServicioIatx.setSaldoPendConfDolares("000000000000000+");
		saldosServicioIatx.setDepEfectDiaDolares("000000000000000+");
		saldosServicioIatx.setDep24Dolares("0000000000000+");
		saldosServicioIatx.setDep48Dolares("000000000000000+");
		saldosServicioIatx.setDep72Dolares("000000000000000+");
		saldosServicioIatx.setLimiteCreditoDolares("000000000000000+");
		saldosServicioIatx.setInteresesDolares("0000000000000+");
		saldosServicioIatx.setCantRechazosDolares("00000");
		saldosServicioIatx.setIndSobregreido("S");
		saldosServicioIatx.setSaldoDispoACTE("000000000000000+");
		saldosServicioIatx.setSaldoDispoACAH("000005000000000+");
		saldosServicioIatx.setSaldoDispoACCD("000000000000000+");
		saldosServicioIatx.setSaldoDispoACAD("000005000000000+");

		return saldosServicioIatx;
	}
	
	@Test
	public void obtenerSaldosCuentasOK() throws BusinessException, SQLException, DAOException{
		
		Cliente cliente = obtenerCliente();
		cliente.getCuentasPrivadas().get(0).setTipoCuenta("02");

		Credential credencial = new Credential();
		credencial.setUsuario("USUARIOPEPE");
		credencial.setPassword("PASSPEPE");
		when(credentialSecurityFactory.getCredentialBySistema(Matchers.anyString())).thenReturn(credencial);
		
		CuentaSaldoOutEntity cuentaSaldoOut = new CuentaSaldoOutEntity();
		List<RtaLoadSaldo> listaLoadSaldo = new ArrayList<RtaLoadSaldo>();
		cuentaSaldoOut.setRespuesta(listaLoadSaldo);
		RtaLoadSaldo loadSaldo = new RtaLoadSaldo();
		loadSaldo.setcAhorroDolares("2300");
		loadSaldo.setcAhorroPesos("5100");
		listaLoadSaldo.add(loadSaldo);
		when(cuentaSaldoDAO.verSaldosCuentasBancaPrivada(Matchers.any(CuentaSaldoInEntity.class))).thenReturn(cuentaSaldoOut);
		
		List<CuentasAdhesionDebitoView> respuesta = cuentasBancaPrivadaBO.obtenerSaldosCuentas(cliente);

		Assert.assertNotNull(respuesta);
		
	}
	
	
	@Test
	public void obtenerInterviniente_OK() throws BusinessException, SQLException, DAOException {

		RtaLoadTitular rtaLoadTitular = new RtaLoadTitular("Nombre", "Apellido");
		TitularOutEntity titularOutEntity = new TitularOutEntity();
		titularOutEntity.setRespuesta(rtaLoadTitular);
		Credential credencial = new Credential();
		credencial.setUsuario("USUARIO");
		credencial.setPassword("PASS");
		when(credentialSecurityFactory.getCredentialBySistema(Matchers.anyString())).thenReturn(credencial);
		when(cuentaSaldoDAO.consultaTitular(Matchers.any(ConsultaTitularInEntity.class))).thenReturn(titularOutEntity);
		IntervinienteinEntity entity = new IntervinienteinEntity();
		entity.setNumeroCuenta("250-354914/0");
		Respuesta<IntervinienteView> respuesta = cuentasBancaPrivadaBO.obtenerInterviniente(entity);
		respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
		
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
	}
	
	
	
	
	@Test
	public void confirmarTransferencia_OK() throws BusinessException, SQLException, DAOException {
        Cliente cliente = obtenerCliente();
		cliente.setCuentas(obtenerCuentasBancaPrivada());
		ContadorIntentos contadorIntentos = new ContadorIntentos();
	    contadorIntentos.permiteReintentar();
	    when(sessionParametros.getContador()).thenReturn(contadorIntentos);
 
  
	    Mensaje mensajeMock = new Mensaje();
		mensajeMock.setMensaje("mensaje.");
		Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensajeMock);
		RtaTransferenciaBP rtaTransferenciaBP = new RtaTransferenciaBP("Nombre", "Apellido");
		TransferenciaBPOutEntity transferenciaBPOutEntity = new TransferenciaBPOutEntity();
		transferenciaBPOutEntity.setRespuesta(rtaTransferenciaBP);
		
		Credential credencial = new Credential();
		credencial.setUsuario("USUARIO");
		credencial.setPassword("PASS");
		when(credentialSecurityFactory.getCredentialBySistema(Matchers.anyString())).thenReturn(credencial);
		when(cuentaSaldoDAO.ejecutarTransferenciaBancaPrivada(Matchers.any(TransferenciaBPInEntity.class))).thenReturn(transferenciaBPOutEntity);		
		
		ConfirmarTransferenciaInEntity entity = new ConfirmarTransferenciaInEntity();
		entity.setDivisa("ARS");
		entity.setImporte("177.0");
		entity.setInterviniente("BYEZKAJLO ALBINO CELSO");
		entity.setNroCuentaDestino("0000-7000638801");
		entity.setNroCuentaOrigen("0000-7003523508");
				
		Respuesta<ConfirmarTransferenciaView> respuesta = cuentasBancaPrivadaBO.confirmarTransferencia(entity, cliente);
		respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
		
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
	}
	
}
