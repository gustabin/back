package ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.manager;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
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
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.legal.bo.LegalBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SessionResumenCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.bo.AliasCuentaBO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.AbstractCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DetalleDocumentoDTO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.manager.CuentaManager;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ConsultaCuentaView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasAdhesionDebitoView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasIntermedioView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ResumenesMensualesCuentaView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasaldo.dao.RtaLoadSaldo;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasaldo.entity.CuentaSaldoOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.bo.CuentasBancaPrivadaBO;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.dto.CuentaIntermedioDTO;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.entity.ConsultaSaldoCtasConAperturaOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.manager.impl.CuentasBancaPrivadaManagerImpl;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.view.DetalleCuentaCBUBancaPrivadaView;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.view.NuevaTransferenciaView;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.view.SelectorCuentasBancaPrivadaView;
import ar.com.santanderrio.obp.servicios.ondemand.entities.WSODException;
import ar.com.santanderrio.obp.servicios.resumen.entities.ResumenMensualCuenta;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ClienteMock;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.CuentaMock;

@RunWith(MockitoJUnitRunner.class)
public class CuentasBancaPrivadaManagerTest {

	@InjectMocks
	CuentasBancaPrivadaManagerImpl cuentasBancaPrivadaManager;
	
	@Mock
	private SesionCliente sesionCliente;
	
	@Mock
	private SesionParametros sesionParametros;
	
	@Mock
	private CuentasBancaPrivadaBO cuentasBancaPrivadaBO;
	
    @Mock
    private EstadisticaManager estadisticaManager;
    
    @Mock
    private MensajeBO mensajeBO;
    
    @Mock
    private AliasCuentaBO aliasCuentaBO;
    
    @Mock
    private LegalBO legalBO;
    
    @Mock
    private CuentaManager cuentaManager;
	
    @Mock
    private SessionResumenCuenta sesionResumenCuenta;
    
    @Spy 
    @InjectMocks
    private RespuestaFactory respuestaFactory = new RespuestaFactory();
	
	
	@Test
	public void obtenerInicioCuentasOk() throws SQLException, BusinessException {
		
		//When
		List<CuentaIntermedioDTO> respuestaSaldos = new ArrayList<CuentaIntermedioDTO>();
		CuentaIntermedioDTO cuentaIntermedioDTO = new CuentaIntermedioDTO();
		
		CuentaSaldoOutEntity cuentaSaldoOutEntity = new CuentaSaldoOutEntity();
		List<RtaLoadSaldo> listaRtaLoadSaldo = new ArrayList<RtaLoadSaldo>();
		RtaLoadSaldo rtaLoadSaldo = new RtaLoadSaldo("70035235508", "ALISAL SRL", "250", "505", "2018-01-22", "49985656", "50073297.96");
		listaRtaLoadSaldo.add(rtaLoadSaldo);
		cuentaSaldoOutEntity.setRespuesta(listaRtaLoadSaldo);
		cuentaIntermedioDTO.setSaldosStoredProcedure(cuentaSaldoOutEntity);
		cuentaIntermedioDTO.setSaldosServicioIatx(crearObjetoIatx());
        cuentaIntermedioDTO.setIsCuentaTransacional(Boolean.TRUE);
		cuentaIntermedioDTO.setIsCuentaUnica(true);
		cuentaIntermedioDTO.setTipoCuenta(TipoCuenta.CUENTA_UNICA_PESOS);
		respuestaSaldos.add(cuentaIntermedioDTO);
		
		when(cuentasBancaPrivadaBO.consultarSaldosCuenta(Matchers.any(Cliente.class))).thenReturn(respuestaSaldos);

		Cliente cliente = new Cliente();
		List<Cuenta> cuentasRetail = new ArrayList<Cuenta>();
		cliente.setCuentasRetail(cuentasRetail);;
		when(sesionCliente.getCliente()).thenReturn(cliente);
		
		//Then
		Respuesta<CuentasIntermedioView> respuesta = cuentasBancaPrivadaManager.obtenerInicioCuentas();
		
		//Expected
		Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
	}
	
	
	@Test
	public void obtenerInicioCuentasOkCajaAhorroPesos() throws SQLException, BusinessException {
		
		//When
		List<CuentaIntermedioDTO> respuestaSaldos = new ArrayList<CuentaIntermedioDTO>();
		CuentaIntermedioDTO cuentaIntermedioDTO = new CuentaIntermedioDTO();
		
		CuentaSaldoOutEntity cuentaSaldoOutEntity = new CuentaSaldoOutEntity();
		List<RtaLoadSaldo> listaRtaLoadSaldo = new ArrayList<RtaLoadSaldo>();
		RtaLoadSaldo rtaLoadSaldo = new RtaLoadSaldo("70035235508", "ALISAL SRL", "250", "505", "2018-01-22", "49985656", "50073297.96");
		listaRtaLoadSaldo.add(rtaLoadSaldo);
		cuentaSaldoOutEntity.setRespuesta(listaRtaLoadSaldo);
		cuentaIntermedioDTO.setSaldosStoredProcedure(cuentaSaldoOutEntity);
		cuentaIntermedioDTO.setSaldosServicioIatx(crearObjetoIatx());
        cuentaIntermedioDTO.setIsCuentaTransacional(Boolean.TRUE);
		cuentaIntermedioDTO.setIsCuentaUnica(false);
		cuentaIntermedioDTO.setTipoCuenta(TipoCuenta.CAJA_AHORRO_PESOS);
		respuestaSaldos.add(cuentaIntermedioDTO);
		
		when(cuentasBancaPrivadaBO.consultarSaldosCuenta(Matchers.any(Cliente.class))).thenReturn(respuestaSaldos);

		Cliente cliente = new Cliente();
		List<Cuenta> cuentasRetail = new ArrayList<Cuenta>();
		cliente.setCuentasRetail(cuentasRetail);;
		when(sesionCliente.getCliente()).thenReturn(cliente);
		
		//Then
		Respuesta<CuentasIntermedioView> respuesta = cuentasBancaPrivadaManager.obtenerInicioCuentas();
		
		//Expected
		Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
	}
	
	
	@Test
	public void obtenerInicioCuentasOkCajaAhorroDolares() throws SQLException, BusinessException {
		
		//When
		List<CuentaIntermedioDTO> respuestaSaldos = new ArrayList<CuentaIntermedioDTO>();
		CuentaIntermedioDTO cuentaIntermedioDTO = new CuentaIntermedioDTO();
		
		CuentaSaldoOutEntity cuentaSaldoOutEntity = new CuentaSaldoOutEntity();
		List<RtaLoadSaldo> listaRtaLoadSaldo = new ArrayList<RtaLoadSaldo>();
		RtaLoadSaldo rtaLoadSaldo = new RtaLoadSaldo("70035235508", "ALISAL SRL", "250", "505", "2018-01-22", "49985656", "50073297.96");
		listaRtaLoadSaldo.add(rtaLoadSaldo);
		cuentaSaldoOutEntity.setRespuesta(listaRtaLoadSaldo);
		cuentaIntermedioDTO.setSaldosStoredProcedure(cuentaSaldoOutEntity);
		cuentaIntermedioDTO.setSaldosServicioIatx(crearObjetoIatx());
        cuentaIntermedioDTO.setIsCuentaTransacional(Boolean.TRUE);
		cuentaIntermedioDTO.setIsCuentaUnica(false);
		cuentaIntermedioDTO.setTipoCuenta(TipoCuenta.CAJA_AHORRO_DOLARES);
		respuestaSaldos.add(cuentaIntermedioDTO);
		
		when(cuentasBancaPrivadaBO.consultarSaldosCuenta(Matchers.any(Cliente.class))).thenReturn(respuestaSaldos);
		
		Cliente cliente = new Cliente();
		List<Cuenta> cuentasRetail = new ArrayList<Cuenta>();
		cliente.setCuentasRetail(cuentasRetail);;
		when(sesionCliente.getCliente()).thenReturn(cliente);
				
		//Then
		Respuesta<CuentasIntermedioView> respuesta = cuentasBancaPrivadaManager.obtenerInicioCuentas();
		
		//Expected
		Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
	}
	
	
	@Test
	public void obtenerInicioCuentasOkCuentaCorrientePesos() throws SQLException, BusinessException {
		
		//When
		List<CuentaIntermedioDTO> respuestaSaldos = new ArrayList<CuentaIntermedioDTO>();
		CuentaIntermedioDTO cuentaIntermedioDTO = new CuentaIntermedioDTO();
		
		CuentaSaldoOutEntity cuentaSaldoOutEntity = new CuentaSaldoOutEntity();
		List<RtaLoadSaldo> listaRtaLoadSaldo = new ArrayList<RtaLoadSaldo>();
		RtaLoadSaldo rtaLoadSaldo = new RtaLoadSaldo("70035235508", "ALISAL SRL", "250", "505", "2018-01-22", "49985656", "50073297.96");
		listaRtaLoadSaldo.add(rtaLoadSaldo);
		cuentaSaldoOutEntity.setRespuesta(listaRtaLoadSaldo);
		cuentaIntermedioDTO.setSaldosStoredProcedure(cuentaSaldoOutEntity);
		cuentaIntermedioDTO.setSaldosServicioIatx(crearObjetoIatx());
		
		cuentaIntermedioDTO.setIsCuentaTransacional(Boolean.TRUE);
		cuentaIntermedioDTO.setIsCuentaUnica(false);
		cuentaIntermedioDTO.setTipoCuenta(TipoCuenta.CUENTA_CORRIENTE_PESOS);
		respuestaSaldos.add(cuentaIntermedioDTO);
		
		when(cuentasBancaPrivadaBO.consultarSaldosCuenta(Matchers.any(Cliente.class))).thenReturn(respuestaSaldos);

		Cliente cliente = new Cliente();
		List<Cuenta> cuentasRetail = new ArrayList<Cuenta>();
		cliente.setCuentasRetail(cuentasRetail);;
		when(sesionCliente.getCliente()).thenReturn(cliente);
		
		//Then
		Respuesta<CuentasIntermedioView> respuesta = cuentasBancaPrivadaManager.obtenerInicioCuentas();
		
		//Expected
		Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
	}
	
	
	@Test
	public void obtenerInicioCuentasOkCuentaCorrienteDolares() throws SQLException, BusinessException {
		
		//When
		List<CuentaIntermedioDTO> respuestaSaldos = new ArrayList<CuentaIntermedioDTO>();
		CuentaIntermedioDTO cuentaIntermedioDTO = new CuentaIntermedioDTO();
		
		CuentaSaldoOutEntity cuentaSaldoOutEntity = new CuentaSaldoOutEntity();
		List<RtaLoadSaldo> listaRtaLoadSaldo = new ArrayList<RtaLoadSaldo>();
		RtaLoadSaldo rtaLoadSaldo = new RtaLoadSaldo("70035235508", "ALISAL SRL", "250", "505", "2018-01-22", "49985656", "50073297.96");
		listaRtaLoadSaldo.add(rtaLoadSaldo);
		cuentaSaldoOutEntity.setRespuesta(listaRtaLoadSaldo);
		cuentaIntermedioDTO.setSaldosStoredProcedure(cuentaSaldoOutEntity);
		cuentaIntermedioDTO.setSaldosServicioIatx(crearObjetoIatx());
        cuentaIntermedioDTO.setIsCuentaTransacional(Boolean.TRUE);
		cuentaIntermedioDTO.setIsCuentaUnica(false);
		cuentaIntermedioDTO.setTipoCuenta(TipoCuenta.CUENTA_CORRIENTE_DOLARES);
		respuestaSaldos.add(cuentaIntermedioDTO);
		
		when(cuentasBancaPrivadaBO.consultarSaldosCuenta(Matchers.any(Cliente.class))).thenReturn(respuestaSaldos);
		
		Cliente cliente = new Cliente();
		List<Cuenta> cuentasRetail = new ArrayList<Cuenta>();
		cliente.setCuentasRetail(cuentasRetail);;
		when(sesionCliente.getCliente()).thenReturn(cliente);
				
		//Then
		Respuesta<CuentasIntermedioView> respuesta = cuentasBancaPrivadaManager.obtenerInicioCuentas();
		
		//Expected
		Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
	}
	
	
	@Test
	public void obtenerInicioCuentasErrorCabecera() throws SQLException, BusinessException {
		
		//When
		List<CuentaIntermedioDTO> respuestaSaldos = new ArrayList<CuentaIntermedioDTO>();
		CuentaIntermedioDTO cuentaIntermedioDTO = new CuentaIntermedioDTO();
		
		CuentaSaldoOutEntity cuentaSaldoOutEntity = new CuentaSaldoOutEntity();
		List<RtaLoadSaldo> listaRtaLoadSaldo = new ArrayList<RtaLoadSaldo>();
		RtaLoadSaldo rtaLoadSaldo = new RtaLoadSaldo();
		listaRtaLoadSaldo.add(rtaLoadSaldo);
		cuentaSaldoOutEntity.setRespuesta(listaRtaLoadSaldo);
		cuentaSaldoOutEntity.setErrorEnConsulta(true);
		cuentaIntermedioDTO.setSaldosStoredProcedure(cuentaSaldoOutEntity);
		cuentaIntermedioDTO.setSaldosServicioIatx(crearObjetoIatx());
        cuentaIntermedioDTO.setIsCuentaTransacional(Boolean.TRUE);
		cuentaIntermedioDTO.setIsCuentaUnica(true);
		cuentaIntermedioDTO.setTipoCuenta(TipoCuenta.CUENTA_UNICA_PESOS);
		respuestaSaldos.add(cuentaIntermedioDTO);
		
		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("ERROR BANCA PRIVADA");
		
		when(cuentasBancaPrivadaBO.consultarSaldosCuenta(Matchers.any(Cliente.class))).thenReturn(respuestaSaldos);
		when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_ERROR_RECUPERAR_SALDOS_BCAPRIV)).thenReturn(mensaje);

		Cliente cliente = new Cliente();
		List<Cuenta> cuentasRetail = new ArrayList<Cuenta>();
		cliente.setCuentasRetail(cuentasRetail);;
		when(sesionCliente.getCliente()).thenReturn(cliente);
		
		//Then
		Respuesta<CuentasIntermedioView> respuesta = cuentasBancaPrivadaManager.obtenerInicioCuentas();
		
		//Expected
		Assert.assertEquals(EstadoRespuesta.WARNING, respuesta.getEstadoRespuesta());
	}
	
	
	@Test
	public void obtenerCuentasOk() throws SQLException, BusinessException, DAOException {
		
		//When
		List<CuentaIntermedioDTO> respuestaSaldos = new ArrayList<CuentaIntermedioDTO>();
		CuentaIntermedioDTO cuentaIntermedioDTO = new CuentaIntermedioDTO();
		
		CuentaSaldoOutEntity cuentaSaldoOutEntity = new CuentaSaldoOutEntity();
		List<RtaLoadSaldo> listaRtaLoadSaldo = new ArrayList<RtaLoadSaldo>();
		RtaLoadSaldo rtaLoadSaldo = new RtaLoadSaldo("70035235508", "ALISAL SRL", "250", "505", "2018-01-22", "49985656", "50073297.96");
		listaRtaLoadSaldo.add(rtaLoadSaldo);
		cuentaSaldoOutEntity.setRespuesta(listaRtaLoadSaldo);
		cuentaIntermedioDTO.setSaldosStoredProcedure(cuentaSaldoOutEntity);
		cuentaIntermedioDTO.setSaldosServicioIatx(crearObjetoIatx());
		cuentaIntermedioDTO.setNumeroCuenta("250-352350/8");
		cuentaIntermedioDTO.setIsCuentaUnica(true);
        cuentaIntermedioDTO.setIsCuentaTransacional(Boolean.TRUE);
		cuentaIntermedioDTO.setTipoCuenta(TipoCuenta.CUENTA_UNICA_PESOS);
		respuestaSaldos.add(cuentaIntermedioDTO);
		
		Cliente cliente = mock(Cliente.class);
		
		AbstractCuenta cuenta = new AbstractCuenta() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Boolean isCuentaCerrada() {
				return null;
			}
		};
		
		cuenta.setCbu("47483392");
		cuenta.setTipoCuentaEnum(TipoCuenta.CUENTA_UNICA_PESOS);
		cuenta.setNroSucursal("0250");
		
		ConsultaCuentaView cuentaView = new ConsultaCuentaView();
		cuentaView.setNumeroCuenta("250-352350/8");
		
		when(aliasCuentaBO.obtenerDocumentoValidoDTO(Matchers.any(Cliente.class))).thenReturn(new DetalleDocumentoDTO());
		when(aliasCuentaBO.obtenerCuitPorDetalle(Matchers.any(DetalleDocumentoDTO.class))).thenReturn("20-35987123-3");
		when(cuentasBancaPrivadaBO.consultarSaldosCuenta(Matchers.any(Cliente.class))).thenReturn(respuestaSaldos);
		when(sesionCliente.getCliente()).thenReturn(cliente);
		when(cuentasBancaPrivadaBO.buscarCuentaPorId(Matchers.any(Cliente.class), Matchers.any(IdentificacionCuenta.class))).thenReturn(cuenta);
		when(aliasCuentaBO.obtenerDocumentoValidoDTO(Matchers.any(Cliente.class))).thenReturn(new DetalleDocumentoDTO());
		when(aliasCuentaBO.obtenerCuitPorDetalle(Matchers.any(DetalleDocumentoDTO.class))).thenReturn("23-48338221-2");
		
		//Then
		Respuesta<SelectorCuentasBancaPrivadaView> respuesta = cuentasBancaPrivadaManager.obtenerCuentas(cuentaView);

		
		//Expected
		Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());

		
	}
	
	
	@SuppressWarnings("unchecked")
	@Test
	public void obtenerCuentasSQLException() throws SQLException, BusinessException {

		//When
		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("ERROR BANCA PRIVADA");
		
		ConsultaCuentaView cuentaView = new ConsultaCuentaView();
		cuentaView.setNumeroCuenta("250-352350/8");
		
		when(cuentasBancaPrivadaBO.consultarSaldosCuenta(Matchers.any(Cliente.class))).thenThrow(SQLException.class);
		when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_ERROR_RECUPERAR_SALDOS_BCAPRIV)).thenReturn(mensaje);
		
		
		//Then
		Respuesta<SelectorCuentasBancaPrivadaView> respuesta = cuentasBancaPrivadaManager.obtenerCuentas(cuentaView);
		
		//Expected
		Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
		
	}
	
	
	@SuppressWarnings("unchecked")
	@Test
	public void obtenerInicioCuentasSQLException() throws SQLException, BusinessException {

		//When
		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("ERROR BANCA PRIVADA");
		
		when(cuentasBancaPrivadaBO.consultarSaldosCuenta(Matchers.any(Cliente.class))).thenThrow(SQLException.class);
		when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_ERROR_RECUPERAR_SALDOS_BCAPRIV)).thenReturn(mensaje);
		
		//Then
		Respuesta<CuentasIntermedioView> respuesta = cuentasBancaPrivadaManager.obtenerInicioCuentas();
		
		//Expected
		Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
		
	}
	
	
	@SuppressWarnings("unchecked")
	@Test
	public void obtenerInicioCuentasBusinessxception() throws SQLException, BusinessException {

		//When
		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("ERROR BANCA PRIVADA");
		
		when(cuentasBancaPrivadaBO.consultarSaldosCuenta(Matchers.any(Cliente.class))).thenThrow(BusinessException.class);
		when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_ERROR_RECUPERAR_SALDOS_BCAPRIV)).thenReturn(mensaje);
		
		//Then
		Respuesta<CuentasIntermedioView> respuesta = cuentasBancaPrivadaManager.obtenerInicioCuentas();
		
		//Expected
		Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
		
	}
	
	
	@SuppressWarnings("unchecked")
	@Test
	public void obtenerCuentasBusinessException() throws SQLException, BusinessException {

		//When
		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("ERROR BANCA PRIVADA");
		
		ConsultaCuentaView cuentaView = new ConsultaCuentaView();
		cuentaView.setNumeroCuenta("250-352350/8");
		
		when(cuentasBancaPrivadaBO.consultarSaldosCuenta(Matchers.any(Cliente.class))).thenThrow(BusinessException.class);
		when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_ERROR_RECUPERAR_SALDOS_BCAPRIV)).thenReturn(mensaje);
		
		//Then
		Respuesta<SelectorCuentasBancaPrivadaView> respuesta = cuentasBancaPrivadaManager.obtenerCuentas(cuentaView);
		
		//Expected
		Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
		
	}
	
	
	@Test
	public void obtenerCuentasErrorStoredProcedure() throws SQLException, BusinessException, DAOException {
		
		//When
		List<CuentaIntermedioDTO> respuestaSaldos = new ArrayList<CuentaIntermedioDTO>();
		CuentaIntermedioDTO cuentaIntermedioDTO = new CuentaIntermedioDTO();
		
		CuentaSaldoOutEntity cuentaSaldoOutEntity = new CuentaSaldoOutEntity();
		List<RtaLoadSaldo> listaRtaLoadSaldo = new ArrayList<RtaLoadSaldo>();
		RtaLoadSaldo rtaLoadSaldo = new RtaLoadSaldo("70035235508", "ALISAL SRL", "250", "505", "2018-01-22", "49985656", "50073297.96");
		listaRtaLoadSaldo.add(rtaLoadSaldo);
		cuentaSaldoOutEntity.setRespuesta(listaRtaLoadSaldo);
		cuentaSaldoOutEntity.setErrorEnConsulta(true);
		cuentaIntermedioDTO.setSaldosStoredProcedure(cuentaSaldoOutEntity);
		cuentaIntermedioDTO.setSaldosServicioIatx(crearObjetoIatx());
		cuentaIntermedioDTO.setNumeroCuenta("250-352350/8");
		cuentaIntermedioDTO.setIsCuentaTransacional(Boolean.TRUE);
		cuentaIntermedioDTO.setIsCuentaUnica(true);
		cuentaIntermedioDTO.setTipoCuenta(TipoCuenta.CUENTA_UNICA_PESOS);
		cuentaIntermedioDTO.setIsCuentaTransacional(Boolean.TRUE);
		respuestaSaldos.add(cuentaIntermedioDTO);
		
		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("ERROR BANCA PRIVADA");
		
		Cliente cliente = mock(Cliente.class);
		
		AbstractCuenta cuenta = new AbstractCuenta() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Boolean isCuentaCerrada() {
				return null;
			}
		};
		
		cuenta.setCbu("47483392");
		cuenta.setTipoCuentaEnum(TipoCuenta.CUENTA_UNICA_PESOS);
		cuenta.setNroSucursal("0250");
		
		ConsultaCuentaView cuentaView = new ConsultaCuentaView();
		cuentaView.setNumeroCuenta("250-352350/8");
		
		when(aliasCuentaBO.obtenerDocumentoValidoDTO(Matchers.any(Cliente.class))).thenReturn(new DetalleDocumentoDTO());
		when(aliasCuentaBO.obtenerCuitPorDetalle(Matchers.any(DetalleDocumentoDTO.class))).thenReturn("20-35987123-3");
		when(cuentasBancaPrivadaBO.consultarSaldosCuenta(Matchers.any(Cliente.class))).thenReturn(respuestaSaldos);
		when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_ERROR_RECUPERAR_SALDOS_BCAPRIV)).thenReturn(mensaje);
		when(sesionCliente.getCliente()).thenReturn(cliente);
		when(cuentasBancaPrivadaBO.buscarCuentaPorId(Matchers.any(Cliente.class), Matchers.any(IdentificacionCuenta.class))).thenReturn(cuenta);
		when(aliasCuentaBO.obtenerDocumentoValidoDTO(Matchers.any(Cliente.class))).thenReturn(new DetalleDocumentoDTO());
		when(aliasCuentaBO.obtenerCuitPorDetalle(Matchers.any(DetalleDocumentoDTO.class))).thenReturn("23-48338221-2");
		
		//Then
		Respuesta<SelectorCuentasBancaPrivadaView> respuesta = cuentasBancaPrivadaManager.obtenerCuentas(cuentaView);

		
		//Expected
		Assert.assertEquals(EstadoRespuesta.WARNING, respuesta.getEstadoRespuesta());

		
	}
	
	
	@Test
	public void obtenerCuentasErrorServicioIATX() throws SQLException, BusinessException, DAOException {
		
		//When
		List<CuentaIntermedioDTO> respuestaSaldos = new ArrayList<CuentaIntermedioDTO>();
		CuentaIntermedioDTO cuentaIntermedioDTO = new CuentaIntermedioDTO();
		
		CuentaSaldoOutEntity cuentaSaldoOutEntity = new CuentaSaldoOutEntity();
		List<RtaLoadSaldo> listaRtaLoadSaldo = new ArrayList<RtaLoadSaldo>();
		RtaLoadSaldo rtaLoadSaldo = new RtaLoadSaldo("70035235508", "ALISAL SRL", "250", "505", "2018-01-22", "49985656", "50073297.96");
		listaRtaLoadSaldo.add(rtaLoadSaldo);
		cuentaSaldoOutEntity.setRespuesta(listaRtaLoadSaldo);
		cuentaSaldoOutEntity.setErrorEnConsulta(Boolean.TRUE);
		cuentaIntermedioDTO.setSaldosStoredProcedure(cuentaSaldoOutEntity);
		ConsultaSaldoCtasConAperturaOutEntity saldosServicioIatx = new ConsultaSaldoCtasConAperturaOutEntity();
		saldosServicioIatx.setErrorEnConsulta(Boolean.TRUE);
		saldosServicioIatx.setErrorEnConsulta(true);
		cuentaIntermedioDTO.setSaldosServicioIatx(saldosServicioIatx);
		cuentaIntermedioDTO.setNumeroCuenta("250-352350/8");
		cuentaIntermedioDTO.setIsCuentaUnica(true);
		cuentaIntermedioDTO.setTipoCuenta(TipoCuenta.CUENTA_UNICA_PESOS);
        cuentaIntermedioDTO.setIsCuentaTransacional(Boolean.TRUE);
		respuestaSaldos.add(cuentaIntermedioDTO);
		
		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("ERROR BANCA PRIVADA");
		
		Cliente cliente = mock(Cliente.class);
		
		AbstractCuenta cuenta = new AbstractCuenta() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Boolean isCuentaCerrada() {
				return null;
			}
		};
		
		cuenta.setCbu("47483392");
		cuenta.setTipoCuentaEnum(TipoCuenta.CUENTA_UNICA_PESOS);
		cuenta.setNroSucursal("0250");
		
		DetalleDocumentoDTO detalleDoc = new DetalleDocumentoDTO();
		detalleDoc.setNroDocumento("20359871233");
		
		ConsultaCuentaView cuentaView = new ConsultaCuentaView();
		cuentaView.setNumeroCuenta("250-352350/8");
		
		when(aliasCuentaBO.obtenerDocumentoValidoDTO(Matchers.any(Cliente.class))).thenReturn(new DetalleDocumentoDTO());
		when(aliasCuentaBO.obtenerCuitPorDetalle(Matchers.any(DetalleDocumentoDTO.class))).thenReturn("20-35987123-3");
		when(cuentasBancaPrivadaBO.consultarSaldosCuenta(Matchers.any(Cliente.class))).thenReturn(respuestaSaldos);
		when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_ERROR_RECUPERAR_SALDOS_BCAPRIV)).thenReturn(mensaje);
		when(sesionCliente.getCliente()).thenReturn(cliente);
		when(cuentasBancaPrivadaBO.buscarCuentaPorId(Matchers.any(Cliente.class), Matchers.any(IdentificacionCuenta.class))).thenReturn(cuenta);
		when(aliasCuentaBO.obtenerDocumentoValidoDTO(Matchers.any(Cliente.class))).thenReturn(new DetalleDocumentoDTO());
		when(aliasCuentaBO.obtenerCuitPorDetalle(Matchers.any(DetalleDocumentoDTO.class))).thenReturn("23-48338221-2");	
		
		//Then
		Respuesta<SelectorCuentasBancaPrivadaView> respuesta = cuentasBancaPrivadaManager.obtenerCuentas(cuentaView);

		
		//Expected
		Assert.assertEquals(EstadoRespuesta.WARNING, respuesta.getEstadoRespuesta());

		
	}
	
	
	@Test
	public void modificarApodoOK() {
		
		//When
		Respuesta<Void> respuestaApodo = new Respuesta<Void>();
		respuestaApodo.setEstadoRespuesta(EstadoRespuesta.OK);
		
		when(cuentasBancaPrivadaBO.actualizarApodo(Matchers.any(IdentificacionCuenta.class), Matchers.anyString(), Matchers.any(Cliente.class))).thenReturn(respuestaApodo);
		
		//Then
		Respuesta<Void> respuesta = cuentasBancaPrivadaManager.modificarApodo("250-352350/8", "CuentaPepe");
		
		//Expected
		Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
	}
	
	
	@Test
	public void modificarApodoError() {
		
		//When
		Respuesta<Void> respuestaApodo = new Respuesta<Void>();
		respuestaApodo.setEstadoRespuesta(EstadoRespuesta.ERROR);
		
		when(cuentasBancaPrivadaBO.actualizarApodo(Matchers.any(IdentificacionCuenta.class), Matchers.anyString(), Matchers.any(Cliente.class))).thenReturn(respuestaApodo);
		
		//Then
		Respuesta<Void> respuesta = cuentasBancaPrivadaManager.modificarApodo("250-352350/8", "CuentaPepe");
		
		//Expected
		Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
	}
	
	
	@Test
	public void verDetalleCBUOK() throws DAOException {
		
		//When
		DetalleDocumentoDTO detalleDocumento = new DetalleDocumentoDTO();
		detalleDocumento.setNroDocumento("33119633");
		IdentificacionCuenta identificacionCuenta = new IdentificacionCuenta("250-352350/8");
		
		AbstractCuenta cuenta = new AbstractCuenta() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Boolean isCuentaCerrada() {
				return null;
			}
		};
		cuenta.setCbu("474884784783");
		cuenta.setNroSucursal("0250");
		cuenta.setTipoCuentaEnum(TipoCuenta.CAJA_AHORRO_PESOS);
		Cliente cliente = ClienteMock.completarInfoCliente();
		
		when(sesionCliente.getCliente()).thenReturn(cliente);
		when(aliasCuentaBO.obtenerDocumentoValidoDTO(cliente)).thenReturn(detalleDocumento);
		when(cuentasBancaPrivadaBO.buscarCuentaPorId(cliente, identificacionCuenta)).thenReturn(cuenta);
		
		//Then
		Respuesta<DetalleCuentaCBUBancaPrivadaView> respuesta = cuentasBancaPrivadaManager.verDetalleCBU("250-352350/8");
		
		//Expected
		Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
		
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
	public void nuevaTransferenciaOK() throws BusinessException{
		
		when(estadisticaManager.add(Matchers.anyString(), Matchers.anyString())).thenReturn(true);

		Cliente cliente = new Cliente();
		List<Cuenta> cuentasRetail = new ArrayList<Cuenta>();
		cliente.setCuentasRetail(cuentasRetail);;
		Cuenta cuentaRetail = new Cuenta();
		cuentasRetail.add(cuentaRetail);
		when(sesionCliente.getCliente()).thenReturn(cliente);
		
		Respuesta<CuentasView> respuestaCuentasSaldo = new Respuesta<CuentasView>();
		respuestaCuentasSaldo.setEstadoRespuesta(EstadoRespuesta.OK);
		CuentasView cuentasViewRespuesta = new CuentasView();
		List<CuentasAdhesionDebitoView> listaCuentasRetail = new ArrayList<CuentasAdhesionDebitoView>();
		cuentasViewRespuesta.setCuentas(listaCuentasRetail);
		respuestaCuentasSaldo.setRespuesta(cuentasViewRespuesta);
		when(cuentaManager.getCuentasSaldo()).thenReturn(respuestaCuentasSaldo);
		
		List<CuentasAdhesionDebitoView> listaCuentasPrivada = new ArrayList<CuentasAdhesionDebitoView>();
		when(cuentasBancaPrivadaBO.obtenerSaldosCuentas(Matchers.any(Cliente.class))).thenReturn(listaCuentasPrivada);
		
		Respuesta<NuevaTransferenciaView> respuesta = cuentasBancaPrivadaManager.nuevaTransferencia();
		Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
	}
	
	
	@Test
	public void nuevaTransferenciaSinCuentasRetail(){
		
		when(estadisticaManager.add(Matchers.anyString(), Matchers.anyString())).thenReturn(true);

		Cliente cliente = new Cliente();
		List<Cuenta> cuentasRetail = new ArrayList<Cuenta>();
		cliente.setCuentasRetail(cuentasRetail);
		when(sesionCliente.getCliente()).thenReturn(cliente);
		
		Respuesta<NuevaTransferenciaView> respuesta = cuentasBancaPrivadaManager.nuevaTransferencia();
		Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
	}
	
	@Ignore
	@SuppressWarnings("unchecked")
	@Test
	public void nuevaTransferenciaBussinessException() throws BusinessException{
		
		when(estadisticaManager.add(Matchers.anyString(), Matchers.anyString())).thenReturn(true);
		//When
		Cliente cliente = new Cliente();
		List<Cuenta> cuentasRetail = new ArrayList<Cuenta>();
		cliente.setCuentasRetail(cuentasRetail);
		Cuenta cuentaRetail = new Cuenta();
		cuentasRetail.add(cuentaRetail);
		when(sesionCliente.getCliente()).thenReturn(cliente);
		
		Respuesta<CuentasView> respuestaCuentasSaldo = new Respuesta<CuentasView>();
		respuestaCuentasSaldo.setEstadoRespuesta(EstadoRespuesta.ERROR);
		when(cuentaManager.getCuentasSaldo()).thenReturn(respuestaCuentasSaldo);

		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("ERROR BANCA PRIVADA");

		when(sesionCliente.getCliente()).thenReturn(cliente);
		when(cuentasBancaPrivadaBO.obtenerSaldosCuentas(cliente)).thenThrow(BusinessException.class);
		when(estadisticaManager.add(Matchers.anyString(), Matchers.anyString())).thenReturn(true);
		when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.ERROR_TRANSFERNCIA_BANCA_PRIVADA)).thenReturn(mensaje);
			
		//Then
		Respuesta<NuevaTransferenciaView> respuesta = cuentasBancaPrivadaManager.nuevaTransferencia();
	
		//Expected
		Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
	}
	
	@Test
	public void obtenerListaResumenOKTieneResumenesSesion() {
		
		//When
		Respuesta<ResumenesMensualesCuentaView> respuesta = new Respuesta<ResumenesMensualesCuentaView>();
		Cliente cliente = mock(Cliente.class);
		Cuenta cuenta = CuentaMock.completarInfoCuentaUnicaPesos();
		
		Respuesta<List<ResumenMensualCuenta>> resumenesSesion = crearRespuestaResumenesMock();
		
		when(sesionCliente.getCliente()).thenReturn(cliente);
		when(cliente.getCuentaPrivadaSiContieneNumero(Matchers.anyString())).thenReturn(cuenta);
		when(sesionResumenCuenta.getResumenesByCuentaBP(Matchers.any(AbstractCuenta.class))).thenReturn(resumenesSesion);
		
		//Then
		respuesta = cuentasBancaPrivadaManager.obtenerListaResumen("250-352350/8");
		
		//Expected
		Assert.assertNotNull(respuesta.getRespuesta());
		Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());	
	}

	
	@Test
	public void obtenerListaResumenOKNoTieneResumenesSesion() throws BusinessException, WSODException {
		
		//When
		Respuesta<ResumenesMensualesCuentaView> respuesta = new Respuesta<ResumenesMensualesCuentaView>();
		Cliente cliente = mock(Cliente.class);
		Cuenta cuenta = CuentaMock.completarInfoCuentaUnicaPesos();
		
		Respuesta<List<ResumenMensualCuenta>> respuestaResumenes = crearRespuestaResumenesMock();
		
		when(sesionCliente.getCliente()).thenReturn(cliente);
		when(cliente.getCuentaPrivadaSiContieneNumero(Matchers.anyString())).thenReturn(cuenta);
		when(sesionResumenCuenta.getResumenesByCuentaBP(Matchers.any(AbstractCuenta.class))).thenReturn(null);
		when(cuentasBancaPrivadaBO.obtenerListaResumen(cuenta)).thenReturn(respuestaResumenes);
		
		//Then
		respuesta = cuentasBancaPrivadaManager.obtenerListaResumen("250-352350/8");
		
		//Expected
		Assert.assertNotNull(respuesta.getRespuesta());
		Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());	
	}
	
	
	@Test
	public void obtenerListaResumenWarningNoHayResumenes() throws BusinessException, WSODException {
		
		//When
		Respuesta<ResumenesMensualesCuentaView> respuesta = new Respuesta<ResumenesMensualesCuentaView>();
		Cliente cliente = mock(Cliente.class);
		Cuenta cuenta = CuentaMock.completarInfoCuentaUnicaPesos();
		Respuesta<List<ResumenMensualCuenta>> respuestaResumenes = crearRespuestaResumenesMockVacia();
		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("Mensaje Warning");
		
		when(sesionCliente.getCliente()).thenReturn(cliente);
		when(cliente.getCuentaPrivadaSiContieneNumero(Matchers.anyString())).thenReturn(cuenta);
		when(sesionResumenCuenta.getResumenesByCuentaBP(Matchers.any(AbstractCuenta.class))).thenReturn(null);
		when(cuentasBancaPrivadaBO.obtenerListaResumen(cuenta)).thenReturn(respuestaResumenes);
		when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.WARNING_NO_TENES_RESUMENES)).thenReturn(mensaje);
		
		//Then
		respuesta = cuentasBancaPrivadaManager.obtenerListaResumen("250-352350/8");
		
		//Expected
		Assert.assertNull(respuesta.getRespuesta());
		Assert.assertEquals(EstadoRespuesta.WARNING, respuesta.getEstadoRespuesta());	
	}
	
	@Test
	public void obtenerListaResumenErrorServicio() throws BusinessException, WSODException {
		
		//When
		Respuesta<ResumenesMensualesCuentaView> respuesta = new Respuesta<ResumenesMensualesCuentaView>();
		Cliente cliente = mock(Cliente.class);
		Cuenta cuenta = CuentaMock.completarInfoCuentaUnicaPesos();
		Respuesta<List<ResumenMensualCuenta>> respuestaResumenes = new Respuesta<List<ResumenMensualCuenta>>();
		respuestaResumenes.setEstadoRespuesta(EstadoRespuesta.ERROR);
		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("Mensaje Error");
		
		when(sesionCliente.getCliente()).thenReturn(cliente);
		when(cliente.getCuentaPrivadaSiContieneNumero(Matchers.anyString())).thenReturn(cuenta);
		when(sesionResumenCuenta.getResumenesByCuentaBP(Matchers.any(AbstractCuenta.class))).thenReturn(null);
		when(cuentasBancaPrivadaBO.obtenerListaResumen(cuenta)).thenReturn(respuestaResumenes);
		when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS)).thenReturn(mensaje);
		
		//Then
		respuesta = cuentasBancaPrivadaManager.obtenerListaResumen("250-352350/8");
		
		//Expected
		Assert.assertNull(respuesta.getRespuesta());
		Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());	
	}
	
	
	@SuppressWarnings("unchecked")
	@Test
	public void obtenerListaResumenBusinessException() throws BusinessException, WSODException {
		
		//When
		Respuesta<ResumenesMensualesCuentaView> respuesta = new Respuesta<ResumenesMensualesCuentaView>();
		Cliente cliente = mock(Cliente.class);
		Cuenta cuenta = CuentaMock.completarInfoCuentaUnicaPesos();
		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("Mensaje Error");
		
		when(sesionCliente.getCliente()).thenReturn(cliente);
		when(cliente.getCuentaPrivadaSiContieneNumero(Matchers.anyString())).thenReturn(cuenta);
		when(sesionResumenCuenta.getResumenesByCuentaBP(Matchers.any(AbstractCuenta.class))).thenReturn(null);
		when(cuentasBancaPrivadaBO.obtenerListaResumen(cuenta)).thenThrow(BusinessException.class);
		when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS)).thenReturn(mensaje);
		
		//Then
		respuesta = cuentasBancaPrivadaManager.obtenerListaResumen("250-352350/8");
		
		//Expected
		Assert.assertNull(respuesta.getRespuesta());
		Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());	
	}
	
	
	@SuppressWarnings("unchecked")
	@Test
	public void obtenerListaResumenWSODException() throws BusinessException, WSODException {
		
		//When
		Respuesta<ResumenesMensualesCuentaView> respuesta = new Respuesta<ResumenesMensualesCuentaView>();
		Cliente cliente = mock(Cliente.class);
		Cuenta cuenta = CuentaMock.completarInfoCuentaUnicaPesos();
		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("Mensaje Error");
		
		when(sesionCliente.getCliente()).thenReturn(cliente);
		when(cliente.getCuentaPrivadaSiContieneNumero(Matchers.anyString())).thenReturn(cuenta);
		when(sesionResumenCuenta.getResumenesByCuentaBP(Matchers.any(AbstractCuenta.class))).thenReturn(null);
		when(cuentasBancaPrivadaBO.obtenerListaResumen(cuenta)).thenThrow(WSODException.class);
		when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS)).thenReturn(mensaje);
		
		//Then
		respuesta = cuentasBancaPrivadaManager.obtenerListaResumen("250-352350/8");
		
		//Expected
		Assert.assertNull(respuesta.getRespuesta());
		Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());	
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
	
	private Respuesta<List<ResumenMensualCuenta>> crearRespuestaResumenesMockVacia() {
		
		List<ResumenMensualCuenta> listaResumenesCuenta = new ArrayList<ResumenMensualCuenta>();
		
		return respuestaFactory.crearRespuestaOk(listaResumenesCuenta);
	}
	
	
}