/**
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.bo;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ar.com.santanderrio.obp.servicios.api.funds.FundsApi;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.prototype.FondosTenenciasPrototype;
import org.apache.commons.collections.Predicate;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
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
import ar.com.santanderrio.obp.servicios.comun.contrato.bo.ContratoBO;
import ar.com.santanderrio.obp.servicios.comun.contrato.entity.CampoEnum;
import ar.com.santanderrio.obp.servicios.comun.contrato.entity.SinonimoEnum;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.legal.bo.LegalBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.dao.MensajeDAO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.inversiones.comun.TipoBancaEnum;
import ar.com.santanderrio.obp.servicios.inversiones.cuentatitulo.dao.CuentaTituloDAO;
import ar.com.santanderrio.obp.servicios.inversiones.cuentatitulo.entity.CuentaBP;
import ar.com.santanderrio.obp.servicios.inversiones.cuentatitulo.entity.CuentaTituloInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.cuentatitulo.entity.CuentaTituloOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.custodia.dao.CustodiaDAO;
import ar.com.santanderrio.obp.servicios.inversiones.custodia.entity.CustodiaInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.custodia.entity.CustodiaOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.custodia.entity.Tenencia;
import ar.com.santanderrio.obp.servicios.inversiones.exception.ImporteMenorAlMinimoException;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.bancaprivada.dao.FondoBPrivDAO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.consultas.dao.ConsultaFondoDAO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dao.FondoDAO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dao.TransferenciaFondoDAO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.ConfigTransferenciaDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.CuentaTituloDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.CuentasConsultaFondoDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.FinalizarRescateBPrivDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.FinalizarTransferenciaBprivDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.FondoResumidoDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.SimulacionInDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.SimularSuscripcionOutDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.TransferenciaDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.ComprobanteTransferenciaFondoInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.ComprobanteTransferenciaFondoOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.ConsultaFondoOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.ConsultaTenenciaFCIEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.ConsultaTenenciaFCIInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.ConsultaTenenciaFCIOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.EjecucionFondoBancaPrivadaInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.EjecucionFondoBancaPrivadaOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.SimulacionFondoBancaPrivadaInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.SimulacionFondoBancaPrivadaOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.TransferenciaInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.TransferenciaOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.manager.RescateFondoEspecie;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.manager.TransferenciaFondoManagerImpl;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.sei.TransferenciaFondoSEIImpl;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.ConfigTransferenciaInView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.CuentaTituloView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.CuentasConsultaFondoView;
import ar.com.santanderrio.obp.servicios.pagos.entities.Interviniente;
import ar.com.santanderrio.obp.servicios.tarjetas.exceptions.SimulacionDAOException;
import ar.com.santanderrio.obp.servicios.transferencias.exception.CuentaSinOperarException;
import ar.com.santanderrio.obp.servicios.transferencias.exception.FueraDeHorarioException;
import ar.com.santanderrio.obp.servicios.transferencias.exception.ImporteLimiteException;
import ar.com.santanderrio.obp.servicios.transferencias.exception.SaldoInsuficienteException;
import ar.com.santanderrio.obp.servicios.transferencias.exception.TimeOutException;
import ar.com.santanderrio.obp.servicios.transferencias.exception.TransferenciaBloqueadaException;

/**
 * The Class TransferenciaFondoBOTest.
 *
 * @author
 */
@RunWith(MockitoJUnitRunner.class)
public class TransferenciaFondoBOTest {

    /** The transferencia BO. */
    @InjectMocks
    private TransferenciaFondoBOImpl transferenciaFondoBO;

    @InjectMocks
    private TransferenciaFondoManagerImpl transferenciaFondoManager;
    
    @InjectMocks
    private FondoBOImpl fondoBOImpl;
    
    /** The rescate SEI. */
    @Spy
    private TransferenciaFondoSEIImpl transferenciaSEI;
    
    /** The transferencia DAO. */
    @Mock
    private TransferenciaFondoDAO transferenciaDAO;

    /** The respuesta factory. */
    @Mock
    private RespuestaFactory respuestaFactory;
    
    @Mock
    private CredentialSecurityFactory credentialSecurity;

    /** The sesion cliente. */
    @Mock
    private SesionCliente sesionCliente;

    @Mock
    private MensajeBO mensajeBO;
    
    @Mock
    private ContratoBO contratoBO;
    
    @Mock
    private LegalBO legalBO;
    
    @Mock
    private FondoBO fondoBO;
    
    @Mock
    private FondoDAO fondoDAO;
    
    /** The mensaje DAO. */
    @Mock
    private MensajeDAO mensajeDAO;
    
    @Mock
    private CuentaTituloDAO cuentaTituloDAO;
    
    @Mock
    private ConsultaFondoDAO consultaFondoDAO;
    
    @Mock
    private TransferenciaFondoDAO transferenciaFondoDAO;
    
    @Mock
    private FondoBPrivDAO fondoBPrivDAO;

    @Mock
    private EstadisticaManager estadisticaManager;
    
    /** The session parametros. */
    @Mock
    private SesionParametros sessionParametros;
    
    @Mock
    private MensajeBO mensajeBo;
    
    /** The custodia DAO. */
    @Mock
    private CustodiaDAO custodiaDAO;
    
    @Mock
    private RescateFondoEspecie rescateFondoEspecie;

	@Mock
	private FondosTenenciasPrototype fondosTenenciasPrototype;

	@Mock
	private FundsApi fundsApi;

    @Before
    public void init() {
        this.transferenciaFondoManager.validatorInit();
    }
    
    @SuppressWarnings("unchecked")
	@Test
	@Ignore
	public void iniciarTransferenciaTestOk() throws IllegalAccessException, DAOException {
        FieldUtils.writeField(transferenciaSEI, "transferenciaManager", transferenciaFondoManager, true);
        FieldUtils.writeField(transferenciaFondoManager, "transferenciaFondoBO", transferenciaFondoBO, true);
        
    	CuentasConsultaFondoView viewRequest = new CuentasConsultaFondoView();
    	String tipoBanca = "BR";
		viewRequest.setTipoBanca(tipoBanca);
    	List<CuentaTituloView> cuentasTitulo = new ArrayList<CuentaTituloView>();
		viewRequest.setCuentasTitulo(cuentasTitulo);
		
		Interviniente interviniente = new Interviniente();
		Long id = Long.valueOf("5761433928917170000");
		interviniente.setId(id);
		String nombre = "ALBINO CELSO";
		interviniente.setNombre(nombre);
		String apellido = "BYEZKAJLO";
		interviniente.setApellido(apellido);
		String ordenParticipacion = "001";
		interviniente.setOrdenParticipacion(ordenParticipacion);
		
		List<Interviniente> listaIntervinientes = new ArrayList<Interviniente>();
		listaIntervinientes.add(interviniente);
		
		CuentaTituloView cuenta = new CuentaTituloView();
		cuenta.setIntervinientes(listaIntervinientes);
		String nroCuenta = "0000000090005958";
		cuenta.setNroCuenta(nroCuenta);
		String sucursal = "000";
		cuenta.setSucursal(sucursal);
		String nroCuentaFormateado = "9000595/8";
		cuenta.setNroCuentaFormateado(nroCuentaFormateado);
		
		cuentasTitulo.add(cuenta);
		
        Cliente cliente = new Cliente();
        String apellido1 = "Apellido 1";
		cliente.setApellido1(apellido1);
		when(sesionCliente.getCliente()).thenReturn(cliente);
		
		List<ConsultaTenenciaFCIEntity> listaResult = new ArrayList<ConsultaTenenciaFCIEntity>();
		ConsultaTenenciaFCIEntity tenencia = new ConsultaTenenciaFCIEntity();
		tenencia.setNroCuenta(nroCuenta);
		String especieCodigo = "007";
		tenencia.setEspecieCodigo(especieCodigo);
		String monedaTipo = "00";
		tenencia.setMonedaTipo(monedaTipo);
		String teneciaValuada = "1234567";
		tenencia.setTeneciaValuada(teneciaValuada);
		listaResult.add(tenencia);
		ConsultaTenenciaFCIOutEntity consultaTenenciaFCI = new ConsultaTenenciaFCIOutEntity();
		consultaTenenciaFCI.setListaTenencia(listaResult);
		Mockito.when(fondoDAO.consultaTenenciaFCI(Matchers.any(Cliente.class),
				Matchers.any(ConsultaTenenciaFCIInEntity.class))).thenReturn(consultaTenenciaFCI);

        List<ConsultaFondoOutEntity> respuestaConsulta = new ArrayList<ConsultaFondoOutEntity>();
        ConsultaFondoOutEntity fondoEntity = new ConsultaFondoOutEntity();
        String moneda = "ARS";
		fondoEntity.setMoneda(moneda);
		String habilitarTransferencia = "1";
		fondoEntity.setHabilitarTransferencia(habilitarTransferencia);
		fondoEntity.setTipoBanca(TipoBancaEnum.BANCA_PERSONAL.getCodigo());
		fondoEntity.setCodigoFondo(" 13");
		respuestaConsulta.add(fondoEntity);
        Mockito.when(consultaFondoDAO.obtenerFondos((Matchers.any(Predicate.class)))).thenReturn(respuestaConsulta);
        
        ConsultaFondoOutEntity respuestaFondo = new ConsultaFondoOutEntity();
        respuestaFondo.setEspecie(especieCodigo);
        respuestaFondo.setMoneda(moneda);
        String nombreFondo = "Nombre fondo";
		respuestaFondo.setNombreFondo(nombreFondo);
		respuestaFondo.setHabilitarTransferencia(habilitarTransferencia);
		respuestaFondo.setCodigoFondo("13");
		Mockito.when(consultaFondoDAO.obtenerPorCodigo((Matchers.any(String.class)))).thenReturn(respuestaFondo);
		
        Respuesta<CuentasConsultaFondoDTO> respuestaCuentasConsultaFondoDTO = new Respuesta<CuentasConsultaFondoDTO>();
        respuestaCuentasConsultaFondoDTO.setEstadoRespuesta(EstadoRespuesta.OK);
        CuentasConsultaFondoDTO respuesta = new CuentasConsultaFondoDTO();
        List<CuentaTituloDTO> cuentasTituloDTO = new ArrayList<CuentaTituloDTO>();
        CuentaTituloDTO cuentaTituloDTO = new CuentaTituloDTO();
        cuentaTituloDTO.setCuentaOperativaSinFormatear(nroCuenta);
        cuentaTituloDTO.setNroCuenta(nroCuentaFormateado);
        cuentaTituloDTO.setSucursal(sucursal);
		cuentasTituloDTO.add(cuentaTituloDTO);
		respuesta.setCuentasTitulo(cuentasTituloDTO);
        List<FondoResumidoDTO> fondosTotales = new ArrayList<FondoResumidoDTO>();
        FondoResumidoDTO fondo = new FondoResumidoDTO();
        fondo.setCodigoFondo(especieCodigo);
        fondo.setNombreFondo(nombreFondo);
        fondo.setTipoBanca(tipoBanca);
		fondosTotales.add(fondo);
		respuesta.setFondosTotales(fondosTotales);
		respuestaCuentasConsultaFondoDTO.setRespuesta(respuesta);
		Mockito.when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class),
				Matchers.any(CuentasConsultaFondoDTO.class))).thenReturn(respuestaCuentasConsultaFondoDTO);
        
        boolean respuestaEstadistica = true;
		when(estadisticaManager.add(Matchers.any(String.class), Matchers.any(String.class)))
				.thenReturn(respuestaEstadistica);
		
		Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("mensaje");
		when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
		
		Respuesta<CuentasConsultaFondoView> respuestaReal = transferenciaSEI.iniciarTransferencia(viewRequest);
		Assert.assertNotNull(respuestaReal);
    }
    
	@Test
	@Ignore
    public void obtenerFondosSuscriptosYDisponiblesBprivTestOK() throws DAOException, IllegalAccessException{
    	
		FieldUtils.writeField(transferenciaFondoBO, "fondoBO", fondoBOImpl, true);
		Mensaje mensaje = Mockito.mock(Mensaje.class);
		
        List<ConsultaFondoOutEntity> respuestaConsulta = new ArrayList<ConsultaFondoOutEntity>();
        ConsultaFondoOutEntity fondoEntity = new ConsultaFondoOutEntity();
        String limiteMaximoSuscripcion = "999999";
		fondoEntity.setLimiteMaximoSuscripcion(limiteMaximoSuscripcion);
        String limiteMinimoSuscripcion = "1";
		fondoEntity.setLimiteMinimoSuscripcion(limiteMinimoSuscripcion);
        String moneda = "ARS";
		fondoEntity.setMoneda(moneda);
		String nombreFondo = "Super Ahorro $";
		fondoEntity.setNombreFondo(nombreFondo);
		String codigoFondo = "026";
		fondoEntity.setCodigoFondo(codigoFondo);
		String agrupadorSuscripcion = "Money market";
		fondoEntity.setAgrupadorSuscripcion(agrupadorSuscripcion);
		String codigoAgrupador = "02";
		fondoEntity.setCodigoAgrupador(codigoAgrupador);
		String ordenAgrupacion = "04";
		fondoEntity.setOrdenAgrupacion(ordenAgrupacion);
		String idMensajeGastos = "054";
		fondoEntity.setIdMensajeGastos(idMensajeGastos);
		String habilitarSuscripcion = "1";
		fondoEntity.setHabilitarSuscripcion(habilitarSuscripcion);
		fondoEntity.setTipoBanca(TipoBancaEnum.BANCA_PRIVADA.getCodigo());
		String horario = "Horario";
		fondoEntity.setHorario(horario);
		String descripcionLarga = "Descripcion larga del fondo";
		fondoEntity.setDescripcionLarga(descripcionLarga);
		String habilitarTransferencia = "1";
		fondoEntity.setHabilitarTransferencia(habilitarTransferencia);
		respuestaConsulta.add(fondoEntity);
		Mockito.when(consultaFondoDAO.obtenerFondos((Matchers.any(Predicate.class)))).thenReturn(respuestaConsulta);
		
		CuentaTituloOutEntity outEntityLoadAtits = new CuentaTituloOutEntity();
		List<CuentaBP> cuentasTit = new ArrayList<CuentaBP>();
		CuentaBP cuenta = new CuentaBP("1234567", "7001234567");
		cuentasTit.add(cuenta);
		outEntityLoadAtits.relacionesOperativaTitulo(cuentasTit);
		Mockito.when(cuentaTituloDAO.obtenerCuentasTitulo(Matchers.any(CuentaTituloInEntity.class))).thenReturn(outEntityLoadAtits);

        CustodiaOutEntity custodiaOut = new CustodiaOutEntity();
        List<Tenencia> tenencias = new ArrayList<Tenencia>();
        Tenencia tenencia = new Tenencia();
        tenencia.setEspecieCodigo(BigDecimal.valueOf(027));
        tenencia.setMonedaTipo(BigDecimal.valueOf(00));
        tenencia.setSaldoBruto(BigDecimal.valueOf(100.50));
        tenencias.add(tenencia);
        custodiaOut.setListaTenencia(tenencias);
        when(custodiaDAO.verTenenciaOl(Matchers.any(CustodiaInEntity.class))).thenReturn(custodiaOut);
        
        ConsultaFondoOutEntity respuestaConsultaFondo = new ConsultaFondoOutEntity();
        respuestaConsultaFondo.setHabilitarTransferencia(habilitarTransferencia);
        respuestaConsultaFondo.setTipoBanca(TipoBancaEnum.BANCA_PRIVADA.getCodigo());
        respuestaConsultaFondo.setMoneda(moneda);
        respuestaConsultaFondo.setCodigoFondo("13");
		Mockito.when(consultaFondoDAO.obtenerPorCodigo((Matchers.anyString()))).thenReturn(respuestaConsultaFondo);
        
		Respuesta<CuentasConsultaFondoDTO> respuesta = new Respuesta<CuentasConsultaFondoDTO>();
		respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
		Mockito.when(respuestaFactory.crearRespuestaOk(Matchers.eq(CuentasConsultaFondoDTO.class), Matchers.any(CuentasConsultaFondoDTO.class))).thenReturn(respuesta);
		
    	CuentasConsultaFondoDTO requestDTO = new CuentasConsultaFondoDTO();
    	String tipoBanca = "BP";
		requestDTO.setTipoBanca(tipoBanca);
		Cliente cliente = new Cliente();
		List<Cuenta> cuentasPrivadas = new ArrayList<Cuenta>();
		Cuenta cuentaBp = new Cuenta();
		cuentaBp.setNroCuentaProducto("1234567");
		String nroSucursal = "250";
		cuentaBp.setNroSucursal(nroSucursal);
		cuentasPrivadas.add(cuentaBp);
		cliente.setCuentasPrivadas(cuentasPrivadas);
	    Mockito.when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.TRANSFERENCIA_INFORMACION_GO_TO)).thenReturn(mensaje);
		Respuesta<CuentasConsultaFondoDTO> respuestaReal = transferenciaFondoBO.obtenerFondosSuscriptosYDisponiblesBpriv(requestDTO, cliente);
    	
		Assert.assertNotNull(respuestaReal);
    }
    
    @Test
    @Ignore
    public void obtenerFondosSuscriptosYDisponiblesBprivTestError() {
    	
        List<ConsultaFondoOutEntity> respuestaConsulta = new ArrayList<ConsultaFondoOutEntity>();
        ConsultaFondoOutEntity fondoEntity = new ConsultaFondoOutEntity();
        String limiteMaximoSuscripcion = "999999";
		fondoEntity.setLimiteMaximoSuscripcion(limiteMaximoSuscripcion);
        String limiteMinimoSuscripcion = "1";
		fondoEntity.setLimiteMinimoSuscripcion(limiteMinimoSuscripcion);
        String moneda = "ARS";
		fondoEntity.setMoneda(moneda);
		String nombreFondo = "Super Ahorro $";
		fondoEntity.setNombreFondo(nombreFondo);
		String codigoFondo = "026";
		fondoEntity.setCodigoFondo(codigoFondo);
		String agrupadorSuscripcion = "Money market";
		fondoEntity.setAgrupadorSuscripcion(agrupadorSuscripcion);
		String codigoAgrupador = "02";
		fondoEntity.setCodigoAgrupador(codigoAgrupador);
		String ordenAgrupacion = "04";
		fondoEntity.setOrdenAgrupacion(ordenAgrupacion);
		String idMensajeGastos = "054";
		fondoEntity.setIdMensajeGastos(idMensajeGastos);
		String habilitarSuscripcion = "1";
		fondoEntity.setHabilitarSuscripcion(habilitarSuscripcion);
		fondoEntity.setTipoBanca(TipoBancaEnum.BANCA_PERSONAL.getCodigo());
		String horario = "Horario";
		fondoEntity.setHorario(horario);
		String descripcionLarga = "Descripcion larga del fondo";
		fondoEntity.setDescripcionLarga(descripcionLarga);
		String habilitarTransferencia = "1";
		fondoEntity.setHabilitarTransferencia(habilitarTransferencia);
		respuestaConsulta.add(fondoEntity);
		try {
			Mockito.when(consultaFondoDAO.obtenerFondos((Matchers.any(Predicate.class)))).thenThrow(new DAOException());
		} catch (DAOException e) {
			e.printStackTrace();
		}
		
		Respuesta<Object> respuesta = new Respuesta<Object>();
		respuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);
		Mockito.when(respuestaFactory.crearRespuestaError(Matchers.anyString(), Matchers.any(TipoError.class), Matchers.anyString())).thenReturn(respuesta);
		
    	CuentasConsultaFondoDTO requestDTO = new CuentasConsultaFondoDTO();
    	String tipoBanca = "BP";
		requestDTO.setTipoBanca(tipoBanca);
		Cliente cliente = new Cliente();
		List<Cuenta> cuentasPrivadas = new ArrayList<Cuenta>();
		Cuenta cuentaBp = new Cuenta();
		cuentaBp.setNroCuentaProducto("1234567");
		String nroSucursal = "250";
		cuentaBp.setNroSucursal(nroSucursal);
		cuentasPrivadas.add(cuentaBp);
		cliente.setCuentasPrivadas(cuentasPrivadas);
		Respuesta<CuentasConsultaFondoDTO> respuestaReal = transferenciaFondoBO.obtenerFondosSuscriptosYDisponiblesBpriv(requestDTO, cliente);
    	
		Assert.assertNotNull(respuestaReal);
    }
    
    @Test
    public void obtenerDatosConfig() throws DAOException {
    	
    	Cliente cliente = new Cliente();
    	cliente.setFechaNacimiento("30/06/84");
    	cliente.setDni("31.557.678");
    	Mensaje mensaje = new Mensaje();
    	mensaje.setMensaje("El importe estimado a transferir supera la tenencia valorizada del fondo");
    	when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
		
		when(contratoBO.buscarContratoAceptadoOld(Matchers.any(String.class), Matchers.any(String.class),Matchers.any(CampoEnum.class),Matchers.any(SinonimoEnum.class))).thenReturn("1");	
		Respuesta<String> legal = new Respuesta<String>();
		
		legal.setEstadoRespuesta(EstadoRespuesta.OK);
		when(legalBO.buscarLegal(Matchers.anyString())).thenReturn(legal);
		
		Respuesta<ConfigTransferenciaDTO> respuestaOK = new Respuesta<ConfigTransferenciaDTO>();
		respuestaOK.setEstadoRespuesta(EstadoRespuesta.OK);
		Mockito.when(respuestaFactory.crearRespuestaOk(Matchers.eq(ConfigTransferenciaDTO.class), Matchers.any(ConfigTransferenciaDTO.class))).thenReturn(respuestaOK);
		
		ConfigTransferenciaInView configTransferenciaInView = new ConfigTransferenciaInView();
		Respuesta<ConfigTransferenciaDTO> respuesta = transferenciaFondoBO.obtenerDatosConfig(configTransferenciaInView,
				cliente);
		Assert.assertNotNull(respuesta);
    	
    }
    
    /**
     * Finalizar Fondo Transferencia OK test.
     *
     * @throws DAOException
     *             the DAO exception
     * @throws BusinessException 
     */
    @SuppressWarnings("unchecked")
    @Test
    public void finalizarTransferenciaOKTest() throws DAOException, BusinessException {
    	
    	ContadorIntentos contadorIntentos = new ContadorIntentos();
        contadorIntentos.permiteReintentar();
        TransferenciaDTO dtoRequest = new TransferenciaDTO();
        dtoRequest.setCodigoFondo("002");
        dtoRequest.setCodigoFondoDest("001");
        dtoRequest.setCuentaTitulo("00140742");
        dtoRequest.setImporteNeto("00000000100000");
        Cliente cliente = mock(Cliente.class);
    	
        ComprobanteTransferenciaFondoOutEntity comprobanteTransferenciaFondoOutEntity = new ComprobanteTransferenciaFondoOutEntity();
        comprobanteTransferenciaFondoOutEntity.setStatusResultado("00000000");
        comprobanteTransferenciaFondoOutEntity.setDescripcionMoneda("PESOS");
        comprobanteTransferenciaFondoOutEntity.setImporteBruto("00000002099999");
        comprobanteTransferenciaFondoOutEntity.setImporteNeto("00000002099999");
        comprobanteTransferenciaFondoOutEntity.setCantCuotasPartes("00000125460334");
        comprobanteTransferenciaFondoOutEntity.setPorcentajeComision("0000");
        comprobanteTransferenciaFondoOutEntity.setValorComision("00000000000000");
        comprobanteTransferenciaFondoOutEntity.setFormaPago("04");
        comprobanteTransferenciaFondoOutEntity.setEstadoActual("ACTIVA");
        comprobanteTransferenciaFondoOutEntity.setMotivoActual("SIN PAGAR");
        comprobanteTransferenciaFondoOutEntity.setDiasCarencia("000");
        comprobanteTransferenciaFondoOutEntity.setNroCertificadoDestino("0001100518");
        comprobanteTransferenciaFondoOutEntity.setPorcentajeComisD("0000");
        comprobanteTransferenciaFondoOutEntity.setEstadoDestino("PENDIENTE");
        comprobanteTransferenciaFondoOutEntity.setMotivoDestino("TRANSFEREN");
        comprobanteTransferenciaFondoOutEntity.setNombreFondo("SUPERFONDO RENTA $ C");
        comprobanteTransferenciaFondoOutEntity.setDescripcionMonedaDestino("PESOS");
        comprobanteTransferenciaFondoOutEntity.setNombreFondoDestino("SUPERFONDO ACCIONES CUOTA A");
        comprobanteTransferenciaFondoOutEntity.setNroOperacion("0000102063");
      
        when(sessionParametros.getContador()).thenReturn(contadorIntentos);                
		Mockito.when(
				transferenciaFondoDAO.obtenerComprobante(Matchers.any(ComprobanteTransferenciaFondoInEntity.class)))
                .thenReturn(comprobanteTransferenciaFondoOutEntity);
                
        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("Mensaje mockeado del servicio");
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
                  
        Respuesta<TransferenciaDTO> respuestaFinalizarTransferencia = new Respuesta<TransferenciaDTO>();
        respuestaFinalizarTransferencia.setEstadoRespuesta(EstadoRespuesta.OK);
        
		Mockito.when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class), Matchers.any(TransferenciaDTO.class)))
                .thenReturn(respuestaFinalizarTransferencia);

        ConsultaFondoOutEntity fondoOutEntity = new ConsultaFondoOutEntity();
        fondoOutEntity.setNombreFondo("nombre mockeado");
        
        when(consultaFondoDAO.obtenerPorCodigo(Matchers.anyString())).thenReturn(fondoOutEntity);
        
        Respuesta<TransferenciaDTO> respuesta = transferenciaFondoBO.finalizarTransferirFondos(dtoRequest, cliente);
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
    }
    
 /**
  * Finalizar fondo Transferencia Fallo DAOException Con Reintento test.   
	 * 
  * @throws DAOException
  * @throws BusinessException
  */
 
    @Test
    public void finalizarTransferenciaFalloDAOExceptionConReintentoTest() throws DAOException, BusinessException {
    	
    	ContadorIntentos contadorIntentos = new ContadorIntentos();
        contadorIntentos.permiteReintentar();
        TransferenciaDTO dtoRequest = new TransferenciaDTO();
        dtoRequest.setCodigoFondo("002");
        dtoRequest.setCodigoFondoDest("001");
        dtoRequest.setCuentaTitulo("00140742");
        dtoRequest.setImporteNeto("00000000100000");
        Cliente cliente = mock(Cliente.class);
    	
        when(sessionParametros.getContador()).thenReturn(contadorIntentos);                
		Mockito.when(
				transferenciaFondoDAO.obtenerComprobante(Matchers.any(ComprobanteTransferenciaFondoInEntity.class)))
                .thenThrow(new DAOException());
                
        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("Mensaje mockeado del servicio");
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        
        ConsultaFondoOutEntity fondoOutEntity = new ConsultaFondoOutEntity();
        fondoOutEntity.setNombreFondo("nombre mockeado");
        
    	when(consultaFondoDAO.obtenerPorCodigo(Matchers.anyString())).thenReturn(fondoOutEntity);
                
        Respuesta<TransferenciaDTO> responseFactoryError = new Respuesta<TransferenciaDTO>();
        responseFactoryError.setEstadoRespuesta(EstadoRespuesta.ERROR);
        responseFactoryError.setRespuestaVacia(true);
        
        Mockito.when(respuestaFactory.crearRespuestaError(TransferenciaDTO.class, "Mensaje mockeado del servicio",
				TipoError.ERROR_FINALIZAR_TRANSACCION_FONDO_CON_REINTENTO.toString())).thenReturn(responseFactoryError);
        
        Respuesta<TransferenciaDTO> respuesta = transferenciaFondoBO.finalizarTransferirFondos(dtoRequest, cliente);
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta, responseFactoryError);  
    }
    
    /**
     * Finalizar fondo Transferencia Fallo DAOException Sin Reintento test.   
	 * 
     * @throws DAOException
     * @throws BusinessException
     */
    
       @Test
       public void finalizarTransferenciaFalloDAOExceptionSinReintentoTest() throws DAOException, BusinessException {
       	
    	   ContadorIntentos contadorIntentos = new ContadorIntentos();
           contadorIntentos.permiteReintentar();
           contadorIntentos.permiteReintentar();
           contadorIntentos.permiteReintentar();
           TransferenciaDTO dtoRequest = new TransferenciaDTO();
           dtoRequest.setCodigoFondo("002");
           dtoRequest.setCodigoFondoDest("001");
           dtoRequest.setCuentaTitulo("00140742");
           dtoRequest.setImporteNeto("00000000100000");
           Cliente cliente = mock(Cliente.class); 	

           when(sessionParametros.getContador()).thenReturn(contadorIntentos);                
		Mockito.when(
				transferenciaFondoDAO.obtenerComprobante(Matchers.any(ComprobanteTransferenciaFondoInEntity.class)))
                   .thenThrow(new DAOException());
                   
           Mensaje mensaje = new Mensaje();
           mensaje.setMensaje("Mensaje mockeado del servicio");
           when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
           
           ConsultaFondoOutEntity fondoOutEntity = new ConsultaFondoOutEntity();
           fondoOutEntity.setNombreFondo("nombre mockeado");
           
       	   when(consultaFondoDAO.obtenerPorCodigo(Matchers.anyString())).thenReturn(fondoOutEntity);
       	   
           Respuesta<TransferenciaDTO> responseFactoryError = new Respuesta<TransferenciaDTO>();
           responseFactoryError.setEstadoRespuesta(EstadoRespuesta.ERROR);
           responseFactoryError.setRespuestaVacia(true);
           
           Mockito.when(respuestaFactory.crearRespuestaError(TransferenciaDTO.class, "Mensaje mockeado del servicio",
				TipoError.ERROR_FINALIZAR_TRANSACCION_FONDO_SIN_REINTENTO.toString())).thenReturn(responseFactoryError);
                     
           Respuesta<TransferenciaDTO> respuesta = transferenciaFondoBO.finalizarTransferirFondos(dtoRequest, cliente);
           Assert.assertNotNull(respuesta);
           Assert.assertEquals(respuesta, responseFactoryError);
           
       }
         
       /**
        * Finalizar fondo Transferencia Fallo FueraDeHorarioException
	 * 
        * @throws DAOException
        * @throws BusinessException
        */
    
       @Test
       public void finalizarTransferenciaFalloFueraDeHorarioExceptionTest() throws DAOException, BusinessException {
    	   ContadorIntentos contadorIntentos = new ContadorIntentos();
           contadorIntentos.permiteReintentar();
           contadorIntentos.permiteReintentar();
           contadorIntentos.permiteReintentar();
           TransferenciaDTO dtoRequest = new TransferenciaDTO();
           dtoRequest.setCodigoFondo("002");
           dtoRequest.setCodigoFondoDest("001");
           dtoRequest.setCuentaTitulo("00140742");
           dtoRequest.setImporteNeto("00000000100000");
           Cliente cliente = mock(Cliente.class);
       	
           when(sessionParametros.getContador()).thenReturn(contadorIntentos);                
		Mockito.when(
				transferenciaFondoDAO.obtenerComprobante(Matchers.any(ComprobanteTransferenciaFondoInEntity.class)))
           .thenThrow(new FueraDeHorarioException());
                   
           Mensaje mensaje = new Mensaje();
           mensaje.setMensaje("Mensaje mockeado del servicio");
           when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
           
           ConsultaFondoOutEntity fondoOutEntity = new ConsultaFondoOutEntity();
           fondoOutEntity.setNombreFondo("nombre mockeado");
           
       	   when(consultaFondoDAO.obtenerPorCodigo(Matchers.anyString())).thenReturn(fondoOutEntity);
       	   
           Respuesta<TransferenciaDTO> responseFactoryError = new Respuesta<TransferenciaDTO>();
           responseFactoryError.setEstadoRespuesta(EstadoRespuesta.ERROR);
           responseFactoryError.setRespuestaVacia(true);
                      
           Mockito.when(respuestaFactory.crearRespuestaWarning(Matchers.any(TransferenciaDTO.class), Matchers.anyString(),
   				Matchers.any(TipoError.class), Matchers.anyString())).thenReturn(responseFactoryError);
           
         Respuesta<TransferenciaDTO> respuesta = transferenciaFondoBO.finalizarTransferirFondos(dtoRequest, cliente);
         Assert.assertNotNull(respuesta);
         Assert.assertEquals(respuesta, responseFactoryError);
    	   
       }

       /**
        * FalloFueraDeHorarioException Fallo TimeOutException
	 * 
        * @throws DAOException
        */
       
       @Test
       public void finalizarTransferenciaTimeOutExceptionTest() throws DAOException, BusinessException {
    	   ContadorIntentos contadorIntentos = new ContadorIntentos();
           contadorIntentos.permiteReintentar();
           contadorIntentos.permiteReintentar();
           contadorIntentos.permiteReintentar();
           TransferenciaDTO dtoRequest = new TransferenciaDTO();
           dtoRequest.setCodigoFondo("002");
           dtoRequest.setCodigoFondoDest("001");
           dtoRequest.setCuentaTitulo("00140742");
           dtoRequest.setImporteNeto("00000000100000");
           Cliente cliente = mock(Cliente.class);
       	
           when(sessionParametros.getContador()).thenReturn(contadorIntentos);                
		Mockito.when(
				transferenciaFondoDAO.obtenerComprobante(Matchers.any(ComprobanteTransferenciaFondoInEntity.class)))
           .thenThrow(new TimeOutException(""));
                   
           Mensaje mensaje = new Mensaje();
           mensaje.setMensaje("Mensaje mockeado del servicio");
           when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
           
           ConsultaFondoOutEntity fondoOutEntity = new ConsultaFondoOutEntity();
           fondoOutEntity.setNombreFondo("nombre mockeado");
           
       	   when(consultaFondoDAO.obtenerPorCodigo(Matchers.anyString())).thenReturn(fondoOutEntity);
       	   
	       	Respuesta<TransferenciaDTO> responseFactoryError = new Respuesta<TransferenciaDTO>();
	        responseFactoryError.setEstadoRespuesta(EstadoRespuesta.ERROR);
	        responseFactoryError.setRespuestaVacia(true);
	        
	        Mockito.when(respuestaFactory.crearRespuestaError(TransferenciaDTO.class, "Mensaje mockeado del servicio",
				TipoError.ERROR_FINALIZAR_TRANSACCION_FONDO_SIN_REINTENTO.toString())).thenReturn(responseFactoryError);
	                  
	        Respuesta<TransferenciaDTO> respuesta = transferenciaFondoBO.finalizarTransferirFondos(dtoRequest, cliente);
	        Assert.assertNotNull(respuesta);
	        Assert.assertEquals(respuesta, responseFactoryError);
    	   
       }
       
       /**
	 * Fallo FueraDeHorarioException Fallo Cuenta Sin OperarException Con
	 * Reintento
	 * 
        * @throws DAOException
        * @throws BusinessException
        */
       @Test
	public void finalizarTransferenciaCuentaSinOperarExceptionConReintentoTest()
			throws DAOException, BusinessException {
    	   ContadorIntentos contadorIntentos = new ContadorIntentos();
           contadorIntentos.permiteReintentar();
           TransferenciaDTO dtoRequest = new TransferenciaDTO();
           dtoRequest.setCodigoFondo("002");
           dtoRequest.setCodigoFondoDest("001");
           dtoRequest.setCuentaTitulo("00140742");
           dtoRequest.setImporteNeto("00000000100000");
           Cliente cliente = mock(Cliente.class);
       	
           when(sessionParametros.getContador()).thenReturn(contadorIntentos);                
		Mockito.when(
				transferenciaFondoDAO.obtenerComprobante(Matchers.any(ComprobanteTransferenciaFondoInEntity.class)))
           .thenThrow(new CuentaSinOperarException());
                   
           Mensaje mensaje = new Mensaje();
           mensaje.setMensaje("Mensaje mockeado del servicio");
           when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
           
           ConsultaFondoOutEntity fondoOutEntity = new ConsultaFondoOutEntity();
           fondoOutEntity.setNombreFondo("nombre mockeado");
           
       	   when(consultaFondoDAO.obtenerPorCodigo(Matchers.anyString())).thenReturn(fondoOutEntity);
       	   
	       	Respuesta<TransferenciaDTO> responseFactoryError = new Respuesta<TransferenciaDTO>();
	        responseFactoryError.setEstadoRespuesta(EstadoRespuesta.ERROR);
	        responseFactoryError.setRespuestaVacia(true);
	        
	        Mockito.when(respuestaFactory.crearRespuestaError(TransferenciaDTO.class, "Mensaje mockeado del servicio",
				TipoError.ERROR_FINALIZAR_TRANSACCION_FONDO_CON_REINTENTO.toString())).thenReturn(responseFactoryError);
	                  
	        Respuesta<TransferenciaDTO> respuesta = transferenciaFondoBO.finalizarTransferirFondos(dtoRequest, cliente);
	        Assert.assertNotNull(respuesta);
	        Assert.assertEquals(respuesta, responseFactoryError);
       }
       
       /**
	 * Fallo FueraDeHorarioException Fallo Cuenta Con OperarException Con
	 * Reintento
	 * 
        * @throws DAOException
        * @throws BusinessException
        */
       @Test
	public void finalizarTransferenciaCuentaSinOperarExceptionSinReintentoTest()
			throws DAOException, BusinessException {
    	   ContadorIntentos contadorIntentos = new ContadorIntentos();
           contadorIntentos.permiteReintentar();
           contadorIntentos.permiteReintentar();
           contadorIntentos.permiteReintentar();
           TransferenciaDTO dtoRequest = new TransferenciaDTO();
           dtoRequest.setCodigoFondo("002");
           dtoRequest.setCodigoFondoDest("001");
           dtoRequest.setCuentaTitulo("00140742");
           dtoRequest.setImporteNeto("00000000100000");
           Cliente cliente = mock(Cliente.class);
       	
           when(sessionParametros.getContador()).thenReturn(contadorIntentos);                
		Mockito.when(
				transferenciaFondoDAO.obtenerComprobante(Matchers.any(ComprobanteTransferenciaFondoInEntity.class)))
           .thenThrow(new CuentaSinOperarException());
                   
           Mensaje mensaje = new Mensaje();
           mensaje.setMensaje("Mensaje mockeado del servicio");
           when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
           
           ConsultaFondoOutEntity fondoOutEntity = new ConsultaFondoOutEntity();
           fondoOutEntity.setNombreFondo("nombre mockeado");
           
       	   when(consultaFondoDAO.obtenerPorCodigo(Matchers.anyString())).thenReturn(fondoOutEntity);
       	   
	       	Respuesta<TransferenciaDTO> responseFactoryError = new Respuesta<TransferenciaDTO>();
	        responseFactoryError.setEstadoRespuesta(EstadoRespuesta.ERROR);
	        responseFactoryError.setRespuestaVacia(true);
	        
	        Mockito.when(respuestaFactory.crearRespuestaError(TransferenciaDTO.class, "Mensaje mockeado del servicio",
				TipoError.ERROR_FINALIZAR_TRANSACCION_FONDO_SIN_REINTENTO.toString())).thenReturn(responseFactoryError);
	                  
	        Respuesta<TransferenciaDTO> respuesta = transferenciaFondoBO.finalizarTransferirFondos(dtoRequest, cliente);
	        Assert.assertNotNull(respuesta);
	        Assert.assertEquals(respuesta, responseFactoryError);
       }
       
       /**
        * finalizar Transferencia Fallo Error Generico
	 * 
        * @throws DAOException
        * @throws BusinessException
        */
       @Test
       public void finalizarTransferenciaFalloErrorGenericoTest() throws DAOException, BusinessException {
    	   ContadorIntentos contadorIntentos = new ContadorIntentos();
           contadorIntentos.permiteReintentar();
           TransferenciaDTO dtoRequest = new TransferenciaDTO();
           dtoRequest.setCodigoFondo("002");
           dtoRequest.setCodigoFondoDest("001");
           dtoRequest.setCuentaTitulo("00140742");
           dtoRequest.setImporteNeto("00000000100000");
           Cliente cliente = mock(Cliente.class);
       	
           when(sessionParametros.getContador()).thenReturn(null);                	   
	       Respuesta<Object> responseFactoryError = new Respuesta<Object>();
	       responseFactoryError.setEstadoRespuesta(EstadoRespuesta.ERROR);
	       responseFactoryError.setRespuestaVacia(true);
	        
	       Mockito.when(respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
				CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS)).thenReturn(responseFactoryError);
	                  
	       Respuesta<TransferenciaDTO> respuesta = transferenciaFondoBO.finalizarTransferirFondos(dtoRequest, cliente);
	       Assert.assertNotNull(respuesta);
	       Assert.assertEquals(respuesta, responseFactoryError);
       }
       
       /**
        * FinalizarTransferencia Sin Datos de DtoRequest Test
	 * 
        * @throws DAOException
        * @throws BusinessException
        */
       @Test
       public void finalizarTransferenciaSinDtoRequestTest() throws DAOException, BusinessException {
       	
       	ContadorIntentos contadorIntentos = new ContadorIntentos();
           contadorIntentos.permiteReintentar();
           TransferenciaDTO dtoRequest = new TransferenciaDTO();
           dtoRequest.setCodigoFondo(null);
           dtoRequest.setCodigoFondoDest(null);
           dtoRequest.setCuentaTitulo(null);
           dtoRequest.setImporteNeto(null);
           Cliente cliente = mock(Cliente.class);
       	
           ComprobanteTransferenciaFondoOutEntity comprobanteTransferenciaFondoOutEntity = new ComprobanteTransferenciaFondoOutEntity();
           comprobanteTransferenciaFondoOutEntity.setStatusResultado("00000000");
           comprobanteTransferenciaFondoOutEntity.setDescripcionMoneda("PESOS");
           comprobanteTransferenciaFondoOutEntity.setImporteBruto("00000002099999");
           comprobanteTransferenciaFondoOutEntity.setImporteNeto("00000002099999");
           comprobanteTransferenciaFondoOutEntity.setCantCuotasPartes("00000125460334");
           comprobanteTransferenciaFondoOutEntity.setPorcentajeComision("0000");
           comprobanteTransferenciaFondoOutEntity.setValorComision("00000000000000");
           comprobanteTransferenciaFondoOutEntity.setFormaPago("04");
           comprobanteTransferenciaFondoOutEntity.setEstadoActual("ACTIVA");
           comprobanteTransferenciaFondoOutEntity.setMotivoActual("SIN PAGAR");
           comprobanteTransferenciaFondoOutEntity.setDiasCarencia("000");
           comprobanteTransferenciaFondoOutEntity.setNroCertificadoDestino("0001100518");
           comprobanteTransferenciaFondoOutEntity.setPorcentajeComisD("0000");
           comprobanteTransferenciaFondoOutEntity.setEstadoDestino("PENDIENTE");
           comprobanteTransferenciaFondoOutEntity.setMotivoDestino("TRANSFEREN");
           comprobanteTransferenciaFondoOutEntity.setNombreFondo("SUPERFONDO RENTA $ C");
           comprobanteTransferenciaFondoOutEntity.setDescripcionMonedaDestino("PESOS");
           comprobanteTransferenciaFondoOutEntity.setNombreFondoDestino("SUPERFONDO ACCIONES CUOTA A");
           comprobanteTransferenciaFondoOutEntity.setNroOperacion("0000102063");
         
           when(sessionParametros.getContador()).thenReturn(contadorIntentos);                
		Mockito.when(
				transferenciaFondoDAO.obtenerComprobante(Matchers.any(ComprobanteTransferenciaFondoInEntity.class)))
                   .thenReturn(comprobanteTransferenciaFondoOutEntity);
                   
           Mensaje mensaje = new Mensaje();
           mensaje.setMensaje("Mensaje mockeado del servicio");
           when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
                     
           Respuesta<Object> responseFactoryError = new Respuesta<Object>();
	       responseFactoryError.setEstadoRespuesta(EstadoRespuesta.ERROR);
	       responseFactoryError.setRespuestaVacia(true);
           
           Mockito.when(respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
				CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS)).thenReturn(responseFactoryError);
           
           Respuesta<TransferenciaDTO> respuesta = transferenciaFondoBO.finalizarTransferirFondos(dtoRequest, cliente);
           Assert.assertNotNull(respuesta);
           Assert.assertEquals(respuesta, responseFactoryError);
       }
       
    /**
     * SimularTransferenciaBPriv OK
     *    
     * @throws DAOException
     * @throws BusinessException
     */
   
	@SuppressWarnings("unchecked")
	@Test
	public void simularTransferenciaBPrivOKTest() throws DAOException, BusinessException {

		SimulacionInDTO viewRequest = new SimulacionInDTO();
		viewRequest.setNroCuentaBancaPrivada("7003523508");
		viewRequest.setImporte(new BigDecimal(890.00));
		viewRequest.setCodigoFondo("023");
		viewRequest.setCodigoFondoDes("082");

		SimulacionFondoBancaPrivadaOutEntity simulacionFondoBancaPrivadaOutEntity = new SimulacionFondoBancaPrivadaOutEntity();
		simulacionFondoBancaPrivadaOutEntity.setCodigoRetorno("0");
		simulacionFondoBancaPrivadaOutEntity.setMensajeDelServicio("simular_fondos se realizo en forma correcta");
		simulacionFondoBancaPrivadaOutEntity.setCapital(new BigDecimal(980.00));
		simulacionFondoBancaPrivadaOutEntity.setDentroDelPerfil("N62");
		simulacionFondoBancaPrivadaOutEntity.setDisclaimer(
				"El cliente ALISAL SRL, tras haber sido informado de las caracteristicas y riesgos de la inversion y en pleno conocimiento de que la misma excede, por ser mas riesgosa, el perfil de inversor que ha acordado con la entidad, ha decidido por su propia iniciativa y  habiendo realizado su propio analisis, proceder a la transferencia  por el importe de $980 del Fondo de SUPERFONDO RTA VARIABLE al fondo de 11100 - SUPER AHORRO PLUS A. Los fondos seran debitados de su cuenta 250-7003523508. Asimismo, toma conocimiento de que debera ajustar su cartera de inversion, al perfil acordado oportunamente o modificar sus inversiones en funcion de dicho perfil, no siendo la entidad responsable por las consecuencias que pudieran surgir y manifestando expresamente que no ha recibido asesoramiento o recomendacion para realizar la presente inversion.");
		simulacionFondoBancaPrivadaOutEntity.setCuotasPartes("cantCuotasParte");

		Mockito.when(fondoBPrivDAO.simularTransferenciaBPriv(Matchers.any(SimulacionFondoBancaPrivadaInEntity.class)))
				.thenReturn(simulacionFondoBancaPrivadaOutEntity);

		Respuesta<String> legal = new Respuesta<String>();
		legal.setRespuesta("Legal OK");
		legal.setEstadoRespuesta(EstadoRespuesta.OK);
		when(legalBO.buscarLegal(Matchers.anyString())).thenReturn(legal);

		Credential credential = new Credential();
		credential.setUsuario("ONLINEBP");
		credential.setPassword("DV09SA10");
		try {
			Mockito.when(credentialSecurity.getCredentialBySistema("BPRIVRACF")).thenReturn(credential);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		ConsultaFondoOutEntity consultaFondoOutEntity = new ConsultaFondoOutEntity();
		consultaFondoOutEntity.setNombreFondo("Super fondo pesos");
		consultaFondoOutEntity.setIdMensajeGastos("10079");
		consultaFondoOutEntity.setCodigoFondo("023");
		consultaFondoOutEntity.setHabilitarSuscripcion("1");
		consultaFondoOutEntity.setTipoBanca("BP");
		consultaFondoOutEntity.setAgrupadorSuscripcion("agrupadorSuscripcion");
		consultaFondoOutEntity.setCodigoAgrupador("codigoAgrupador");
		consultaFondoOutEntity.setContratoSuscripcion("contratoSuscripcion");
		consultaFondoOutEntity.setEspecie("especie");
		consultaFondoOutEntity.setMoneda("ARS");
		consultaFondoOutEntity.setLinkRegla("reg_ahorro_plus.pdf");

		Respuesta<SimularSuscripcionOutDTO> respuestaSimularTransferenciaBPriv = new Respuesta<SimularSuscripcionOutDTO>();
		respuestaSimularTransferenciaBPriv.setEstadoRespuesta(EstadoRespuesta.OK);

		Mockito.when(consultaFondoDAO.obtenerPorCodigo((Matchers.any(String.class))))
				.thenReturn(consultaFondoOutEntity);

		Mockito.when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class),
				Matchers.any(SimularSuscripcionOutDTO.class))).thenReturn(respuestaSimularTransferenciaBPriv);

		Respuesta<SimularSuscripcionOutDTO> respuesta = transferenciaFondoBO.simularTransferenciaBPriv(viewRequest);
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);

	}
    	   
	/**
	 * SimularTransferenciaBPriv ERROR sin Legal
	 *  
	 * @throws DAOException
	 * @throws BusinessException
	 */
	
	@Test
	public void simularTransferenciaBPrivERRORTest() throws DAOException, BusinessException {

		SimulacionInDTO viewRequest = new SimulacionInDTO();
		viewRequest.setNroCuentaBancaPrivada("7003523508");
		viewRequest.setImporte(new BigDecimal(890.00));
		viewRequest.setCodigoFondo("023");
		viewRequest.setCodigoFondoDes("082");

		SimulacionFondoBancaPrivadaOutEntity simulacionFondoBancaPrivadaOutEntity = new SimulacionFondoBancaPrivadaOutEntity();
		simulacionFondoBancaPrivadaOutEntity.setCodigoRetorno("0");
		simulacionFondoBancaPrivadaOutEntity.setMensajeDelServicio("simular_fondos se realizo en forma correcta");
		simulacionFondoBancaPrivadaOutEntity.setCapital(new BigDecimal(980.00));
		simulacionFondoBancaPrivadaOutEntity.setDentroDelPerfil("N62");
		simulacionFondoBancaPrivadaOutEntity.setDisclaimer(
				"El cliente ALISAL SRL, tras haber sido informado de las caracteristicas y riesgos de la inversion y en pleno conocimiento de que la misma excede, por ser mas riesgosa, el perfil de inversor que ha acordado con la entidad, ha decidido por su propia iniciativa y  habiendo realizado su propio analisis, proceder a la transferencia  por el importe de $980 del Fondo de SUPERFONDO RTA VARIABLE al fondo de 11100 - SUPER AHORRO PLUS A. Los fondos seran debitados de su cuenta 250-7003523508. Asimismo, toma conocimiento de que debera ajustar su cartera de inversion, al perfil acordado oportunamente o modificar sus inversiones en funcion de dicho perfil, no siendo la entidad responsable por las consecuencias que pudieran surgir y manifestando expresamente que no ha recibido asesoramiento o recomendacion para realizar la presente inversion.");
		simulacionFondoBancaPrivadaOutEntity.setCuotasPartes("cantCuotasParte");

		Mockito.when(fondoBPrivDAO.simularTransferenciaBPriv(Matchers.any(SimulacionFondoBancaPrivadaInEntity.class)))
				.thenReturn(simulacionFondoBancaPrivadaOutEntity);

		Respuesta<String> legal = new Respuesta<String>();
		legal.setRespuesta("Legal ERROR");
		legal.setEstadoRespuesta(EstadoRespuesta.ERROR);
		
		when(legalBO.buscarLegal(Matchers.anyString())).thenReturn(legal);

		Credential credential = new Credential();
		credential.setUsuario("ONLINEBP");
		credential.setPassword("DV09SA10");
		try {
			Mockito.when(credentialSecurity.getCredentialBySistema("BPRIVRACF")).thenReturn(credential);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		ConsultaFondoOutEntity consultaFondoOutEntity = new ConsultaFondoOutEntity();
		consultaFondoOutEntity.setNombreFondo("Super fondo pesos");
		consultaFondoOutEntity.setIdMensajeGastos("10079");
		consultaFondoOutEntity.setCodigoFondo("023");
		consultaFondoOutEntity.setHabilitarSuscripcion("1");
		consultaFondoOutEntity.setTipoBanca("BP");
		consultaFondoOutEntity.setAgrupadorSuscripcion("agrupadorSuscripcion");
		consultaFondoOutEntity.setCodigoAgrupador("codigoAgrupador");
		consultaFondoOutEntity.setContratoSuscripcion("contratoSuscripcion");
		consultaFondoOutEntity.setEspecie("especie");
		consultaFondoOutEntity.setMoneda("ARS");
		consultaFondoOutEntity.setLinkRegla("reg_ahorro_plus.pdf");

		Respuesta<SimularSuscripcionOutDTO> respuestaSimularTransferenciaBPriv = new Respuesta<SimularSuscripcionOutDTO>();
		respuestaSimularTransferenciaBPriv.setEstadoRespuesta(EstadoRespuesta.ERROR);

		Mockito.when(consultaFondoDAO.obtenerPorCodigo((Matchers.any(String.class))))
				.thenReturn(consultaFondoOutEntity);

		Respuesta<Object> responseFactoryError = new Respuesta<Object>();
		responseFactoryError.setEstadoRespuesta(EstadoRespuesta.ERROR);
		responseFactoryError.setRespuestaVacia(true);

		Mockito.when(respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
				CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS)).thenReturn(responseFactoryError);
		
		Respuesta<SimularSuscripcionOutDTO> respuesta = transferenciaFondoBO.simularTransferenciaBPriv(viewRequest);
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta, responseFactoryError);				

	}
    
	/**
      * SimularTransferenciaBPriv Error FueraDeHorarioException
      * 
      * @throws DAOException
      * @throws BusinessException
      */
	
	@Test
	public void simularTransferenciaBPrivERRORFueraDeHorarioTest() throws DAOException, BusinessException {

		SimulacionInDTO viewRequest = new SimulacionInDTO();
		viewRequest.setNroCuentaBancaPrivada("7003523508");
		viewRequest.setImporte(new BigDecimal(890.00));
		viewRequest.setCodigoFondo("023");
		viewRequest.setCodigoFondoDes("082");

		SimulacionFondoBancaPrivadaOutEntity simulacionFondoBancaPrivadaOutEntity = new SimulacionFondoBancaPrivadaOutEntity();
		simulacionFondoBancaPrivadaOutEntity.setCodigoRetorno("0");
		simulacionFondoBancaPrivadaOutEntity.setMensajeDelServicio("simular_fondos se realizo en forma correcta");
		simulacionFondoBancaPrivadaOutEntity.setCapital(new BigDecimal(980.00));
		simulacionFondoBancaPrivadaOutEntity.setDentroDelPerfil("N62");
		simulacionFondoBancaPrivadaOutEntity.setDisclaimer(
				"El cliente ALISAL SRL, tras haber sido informado de las caracteristicas y riesgos de la inversion y en pleno conocimiento de que la misma excede, por ser mas riesgosa, el perfil de inversor que ha acordado con la entidad, ha decidido por su propia iniciativa y  habiendo realizado su propio analisis, proceder a la transferencia  por el importe de $980 del Fondo de SUPERFONDO RTA VARIABLE al fondo de 11100 - SUPER AHORRO PLUS A. Los fondos seran debitados de su cuenta 250-7003523508. Asimismo, toma conocimiento de que debera ajustar su cartera de inversion, al perfil acordado oportunamente o modificar sus inversiones en funcion de dicho perfil, no siendo la entidad responsable por las consecuencias que pudieran surgir y manifestando expresamente que no ha recibido asesoramiento o recomendacion para realizar la presente inversion.");
		simulacionFondoBancaPrivadaOutEntity.setCuotasPartes("cantCuotasParte");

		Mockito.when(fondoBPrivDAO.simularTransferenciaBPriv(Matchers.any(SimulacionFondoBancaPrivadaInEntity.class)))
				.thenThrow(new FueraDeHorarioException());

		Respuesta<String> legal = new Respuesta<String>();
		legal.setRespuesta("Legal ERROR");
		legal.setEstadoRespuesta(EstadoRespuesta.ERROR);
		
		when(legalBO.buscarLegal(Matchers.anyString())).thenReturn(legal);

		Credential credential = new Credential();
		credential.setUsuario("ONLINEBP");
		credential.setPassword("DV09SA10");
		try {
			Mockito.when(credentialSecurity.getCredentialBySistema("BPRIVRACF")).thenReturn(credential);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		ConsultaFondoOutEntity consultaFondoOutEntity = new ConsultaFondoOutEntity();
		consultaFondoOutEntity.setNombreFondo("Super fondo pesos");
		consultaFondoOutEntity.setIdMensajeGastos("10079");
		consultaFondoOutEntity.setCodigoFondo("023");
		consultaFondoOutEntity.setHabilitarSuscripcion("1");
		consultaFondoOutEntity.setTipoBanca("BP");
		consultaFondoOutEntity.setAgrupadorSuscripcion("agrupadorSuscripcion");
		consultaFondoOutEntity.setCodigoAgrupador("codigoAgrupador");
		consultaFondoOutEntity.setContratoSuscripcion("contratoSuscripcion");
		consultaFondoOutEntity.setEspecie("especie");
		consultaFondoOutEntity.setMoneda("ARS");
		consultaFondoOutEntity.setLinkRegla("reg_ahorro_plus.pdf");

		Respuesta<SimularSuscripcionOutDTO> respuestaSimularTransferenciaBPriv = new Respuesta<SimularSuscripcionOutDTO>();
		respuestaSimularTransferenciaBPriv.setEstadoRespuesta(EstadoRespuesta.ERROR);

		Mockito.when(consultaFondoDAO.obtenerPorCodigo((Matchers.any(String.class))))
				.thenReturn(consultaFondoOutEntity);
		
		Respuesta<SimularSuscripcionOutDTO> responseFactoryError = new Respuesta<SimularSuscripcionOutDTO>();
        responseFactoryError.setEstadoRespuesta(EstadoRespuesta.ERROR);
        responseFactoryError.setRespuestaVacia(true);
        
        Mockito.when(respuestaFactory.crearRespuestaWarning(Matchers.any(SimularSuscripcionOutDTO.class), Matchers.anyString(),
				Matchers.any(TipoError.class), Matchers.anyString())).thenReturn(responseFactoryError);

		Respuesta<SimularSuscripcionOutDTO> respuesta = transferenciaFondoBO.simularTransferenciaBPriv(viewRequest);
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta, responseFactoryError);				

	}
	
	/**
	 * SimularTransferenciaBPriv Error DAOException
	 * 
	 * @throws DAOException
	 * @throws BusinessException
	 */

	@Test
	public void simularTransferenciaBPrivERRORDAOException() throws DAOException, BusinessException {

		SimulacionInDTO viewRequest = new SimulacionInDTO();
		viewRequest.setNroCuentaBancaPrivada("7003523508");
		viewRequest.setImporte(new BigDecimal(890.00));
		viewRequest.setCodigoFondo("023");
		viewRequest.setCodigoFondoDes("082");

		SimulacionFondoBancaPrivadaOutEntity simulacionFondoBancaPrivadaOutEntity = new SimulacionFondoBancaPrivadaOutEntity();
		simulacionFondoBancaPrivadaOutEntity.setCodigoRetorno("0");
		simulacionFondoBancaPrivadaOutEntity.setMensajeDelServicio("simular_fondos se realizo en forma correcta");
		simulacionFondoBancaPrivadaOutEntity.setCapital(new BigDecimal(980.00));
		simulacionFondoBancaPrivadaOutEntity.setDentroDelPerfil("N62");
		simulacionFondoBancaPrivadaOutEntity.setDisclaimer(
				"El cliente ALISAL SRL, tras haber sido informado de las caracteristicas y riesgos de la inversion y en pleno conocimiento de que la misma excede, por ser mas riesgosa, el perfil de inversor que ha acordado con la entidad, ha decidido por su propia iniciativa y  habiendo realizado su propio analisis, proceder a la transferencia  por el importe de $980 del Fondo de SUPERFONDO RTA VARIABLE al fondo de 11100 - SUPER AHORRO PLUS A. Los fondos seran debitados de su cuenta 250-7003523508. Asimismo, toma conocimiento de que debera ajustar su cartera de inversion, al perfil acordado oportunamente o modificar sus inversiones en funcion de dicho perfil, no siendo la entidad responsable por las consecuencias que pudieran surgir y manifestando expresamente que no ha recibido asesoramiento o recomendacion para realizar la presente inversion.");
		simulacionFondoBancaPrivadaOutEntity.setCuotasPartes("cantCuotasParte");

		Mockito.when(fondoBPrivDAO.simularTransferenciaBPriv(Matchers.any(SimulacionFondoBancaPrivadaInEntity.class)))
				.thenThrow(new DAOException());

		Respuesta<String> legal = new Respuesta<String>();
		legal.setRespuesta("Legal ERROR");
		legal.setEstadoRespuesta(EstadoRespuesta.ERROR);

		when(legalBO.buscarLegal(Matchers.anyString())).thenReturn(legal);

		Credential credential = new Credential();
		credential.setUsuario("ONLINEBP");
		credential.setPassword("DV09SA10");
		try {
			Mockito.when(credentialSecurity.getCredentialBySistema("BPRIVRACF")).thenReturn(credential);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		ConsultaFondoOutEntity consultaFondoOutEntity = new ConsultaFondoOutEntity();
		consultaFondoOutEntity.setNombreFondo("Super fondo pesos");
		consultaFondoOutEntity.setIdMensajeGastos("10079");
		consultaFondoOutEntity.setCodigoFondo("023");
		consultaFondoOutEntity.setHabilitarSuscripcion("1");
		consultaFondoOutEntity.setTipoBanca("BP");
		consultaFondoOutEntity.setAgrupadorSuscripcion("agrupadorSuscripcion");
		consultaFondoOutEntity.setCodigoAgrupador("codigoAgrupador");
		consultaFondoOutEntity.setContratoSuscripcion("contratoSuscripcion");
		consultaFondoOutEntity.setEspecie("especie");
		consultaFondoOutEntity.setMoneda("ARS");
		consultaFondoOutEntity.setLinkRegla("reg_ahorro_plus.pdf");

		Respuesta<SimularSuscripcionOutDTO> respuestaSimularTransferenciaBPriv = new Respuesta<SimularSuscripcionOutDTO>();
		respuestaSimularTransferenciaBPriv.setEstadoRespuesta(EstadoRespuesta.ERROR);

		Mockito.when(consultaFondoDAO.obtenerPorCodigo((Matchers.any(String.class))))
				.thenReturn(consultaFondoOutEntity);

		Respuesta<Object> responseFactoryError = new Respuesta<Object>();
		responseFactoryError.setEstadoRespuesta(EstadoRespuesta.ERROR);
		responseFactoryError.setRespuestaVacia(true);

		Mockito.when(respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
				CodigoMensajeConstantes.TENENCIA_RESCATE_FONDO_FALLO_GENERICO)).thenReturn(responseFactoryError);
		
		Respuesta<SimularSuscripcionOutDTO> respuesta = transferenciaFondoBO.simularTransferenciaBPriv(viewRequest);
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta, responseFactoryError);

	}
	
	/**
	 * SimularTransferenciaBPriv Error SimulacionDAOException
	 * 
	 * @throws DAOException
	 * @throws BusinessException
	 */

	@Test
	public void simularTransferenciaBPrivERRORSimulacionDAOException() throws DAOException, BusinessException {

		SimulacionInDTO viewRequest = new SimulacionInDTO();
		viewRequest.setNroCuentaBancaPrivada("7003523508");
		viewRequest.setImporte(new BigDecimal(890.00));
		viewRequest.setCodigoFondo("023");
		viewRequest.setCodigoFondoDes("082");

		SimulacionFondoBancaPrivadaOutEntity simulacionFondoBancaPrivadaOutEntity = new SimulacionFondoBancaPrivadaOutEntity();
		simulacionFondoBancaPrivadaOutEntity.setCodigoRetorno("0");
		simulacionFondoBancaPrivadaOutEntity.setMensajeDelServicio("simular_fondos se realizo en forma correcta");
		simulacionFondoBancaPrivadaOutEntity.setCapital(new BigDecimal(980.00));
		simulacionFondoBancaPrivadaOutEntity.setDentroDelPerfil("N62");
		simulacionFondoBancaPrivadaOutEntity.setDisclaimer(
				"El cliente ALISAL SRL, tras haber sido informado de las caracteristicas y riesgos de la inversion y en pleno conocimiento de que la misma excede, por ser mas riesgosa, el perfil de inversor que ha acordado con la entidad, ha decidido por su propia iniciativa y  habiendo realizado su propio analisis, proceder a la transferencia  por el importe de $980 del Fondo de SUPERFONDO RTA VARIABLE al fondo de 11100 - SUPER AHORRO PLUS A. Los fondos seran debitados de su cuenta 250-7003523508. Asimismo, toma conocimiento de que debera ajustar su cartera de inversion, al perfil acordado oportunamente o modificar sus inversiones en funcion de dicho perfil, no siendo la entidad responsable por las consecuencias que pudieran surgir y manifestando expresamente que no ha recibido asesoramiento o recomendacion para realizar la presente inversion.");
		simulacionFondoBancaPrivadaOutEntity.setCuotasPartes("cantCuotasParte");

		Mockito.when(fondoBPrivDAO.simularTransferenciaBPriv(Matchers.any(SimulacionFondoBancaPrivadaInEntity.class)))
				.thenThrow(new SimulacionDAOException(""));

		Respuesta<String> legal = new Respuesta<String>();
		legal.setRespuesta("Legal ERROR");
		legal.setEstadoRespuesta(EstadoRespuesta.ERROR);

		when(legalBO.buscarLegal(Matchers.anyString())).thenReturn(legal);

		Credential credential = new Credential();
		credential.setUsuario("ONLINEBP");
		credential.setPassword("DV09SA10");
		try {
			Mockito.when(credentialSecurity.getCredentialBySistema("BPRIVRACF")).thenReturn(credential);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		ConsultaFondoOutEntity consultaFondoOutEntity = new ConsultaFondoOutEntity();
		consultaFondoOutEntity.setNombreFondo("Super fondo pesos");
		consultaFondoOutEntity.setIdMensajeGastos("10079");
		consultaFondoOutEntity.setCodigoFondo("023");
		consultaFondoOutEntity.setHabilitarSuscripcion("1");
		consultaFondoOutEntity.setTipoBanca("BP");
		consultaFondoOutEntity.setAgrupadorSuscripcion("agrupadorSuscripcion");
		consultaFondoOutEntity.setCodigoAgrupador("codigoAgrupador");
		consultaFondoOutEntity.setContratoSuscripcion("contratoSuscripcion");
		consultaFondoOutEntity.setEspecie("especie");
		consultaFondoOutEntity.setMoneda("ARS");
		consultaFondoOutEntity.setLinkRegla("reg_ahorro_plus.pdf");

		Respuesta<SimularSuscripcionOutDTO> respuestaSimularTransferenciaBPriv = new Respuesta<SimularSuscripcionOutDTO>();
		respuestaSimularTransferenciaBPriv.setEstadoRespuesta(EstadoRespuesta.ERROR);

		Mockito.when(consultaFondoDAO.obtenerPorCodigo((Matchers.any(String.class))))
				.thenReturn(consultaFondoOutEntity);

		Respuesta<Object> responseFactoryError = new Respuesta<Object>();
		responseFactoryError.setEstadoRespuesta(EstadoRespuesta.ERROR);
		responseFactoryError.setRespuestaVacia(true);

		Mockito.when(respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
				CodigoMensajeConstantes.TENENCIA_RESCATE_FONDO_FALLO_GENERICO)).thenReturn(responseFactoryError);
		
		Respuesta<SimularSuscripcionOutDTO> respuesta = transferenciaFondoBO.simularTransferenciaBPriv(viewRequest);
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta, responseFactoryError);

	}
       
	
    /**
     * Finalizar Fondo TransferenciaBPRIV OK test.
     *
     * @throws DAOException
     *             the DAO exception
     * @throws BusinessException 
     */
    @SuppressWarnings("unchecked")
    @Test
    public void finalizarTransferenciaBPriv_OK_Test() throws DAOException, BusinessException, SQLException {
    	
    	ContadorIntentos contadorIntentos = new ContadorIntentos();
        contadorIntentos.permiteReintentar();
        FinalizarTransferenciaBprivDTO dtoRequest = new FinalizarTransferenciaBprivDTO();
        dtoRequest.setCodigoFondo("023");
        dtoRequest.setCodigoFondoDest("007");
        dtoRequest.setCuentaTitulo("250-352350/8");
        dtoRequest.setImporte("889");
        dtoRequest.setDentroDelPerfil("N65.2");
        dtoRequest.setMoneda("$");
        Cliente cliente = mock(Cliente.class);

		EjecucionFondoBancaPrivadaOutEntity ejecucionFondoBancaPrivadaOutEntity = new EjecucionFondoBancaPrivadaOutEntity();
		ejecucionFondoBancaPrivadaOutEntity.setCapital("889.99");
		ejecucionFondoBancaPrivadaOutEntity.setCuotaPartes("22.6626");
		ejecucionFondoBancaPrivadaOutEntity.setDisclaimer(
				"El cliente ALISAL SRL, tras haber sido informado de las caracteristicas.....");
		ejecucionFondoBancaPrivadaOutEntity.setNroCertificado("6176452");
		ejecucionFondoBancaPrivadaOutEntity.setNroOrden("0130454");
        
        
		Mockito.when(fondoBPrivDAO.transferir(Matchers.any(EjecucionFondoBancaPrivadaInEntity.class)))
				.thenReturn(ejecucionFondoBancaPrivadaOutEntity);
      	
		Credential credenciales = new Credential();
		String usuario = "usuario";
		credenciales.setUsuario(usuario);
		String password = "password";
		credenciales.setPassword(password );
		Mockito.when(credentialSecurity.getCredentialBySistema(Matchers.any(String.class))).thenReturn(credenciales);
		
		String especieCodigo = "007";
		String moneda = "ARS";
		String habilitarTransferencia = "1";
		ConsultaFondoOutEntity respuestaFondo = new ConsultaFondoOutEntity();
	    respuestaFondo.setEspecie(especieCodigo);
	    respuestaFondo.setMoneda(moneda);
	    String nombreFondo = "Nombre fondo";
	    respuestaFondo.setNombreFondo(nombreFondo);
	    respuestaFondo.setHabilitarTransferencia(habilitarTransferencia);
	    respuestaFondo.setCodigoFondo(" 13");
	    Mockito.when(consultaFondoDAO.obtenerPorCodigo((Matchers.any(String.class)))).thenReturn(respuestaFondo);
		
			    
        when(sessionParametros.getContador()).thenReturn(contadorIntentos);                
		             
        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("Mensaje mockeado del servicio");
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
                  
        Respuesta<FinalizarTransferenciaBprivDTO> respuestaFinalizarTransferenciaBpriv = new Respuesta<FinalizarTransferenciaBprivDTO>();
        respuestaFinalizarTransferenciaBpriv.setEstadoRespuesta(EstadoRespuesta.OK);
        
		Mockito.when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class), Matchers.any(FinalizarRescateBPrivDTO.class)))
                .thenReturn(respuestaFinalizarTransferenciaBpriv);
		
              
        Respuesta<FinalizarTransferenciaBprivDTO> respuesta = transferenciaFondoBO.finalizarTransferenciaBPriv(dtoRequest, cliente);
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
    }
	
    
    
	/**
	 * Finalizar fondo TransferenciaBpriv Fallo FueraDeHorarioException
	 * 
	 * @throws DAOException
	 * @throws BusinessException
	 */

	@Test
	public void finalizarTransferenciaBPriv_FueraDeHorarioExceptionTest()
			throws DAOException, BusinessException, SQLException {

		ContadorIntentos contadorIntentos = new ContadorIntentos();
		contadorIntentos.permiteReintentar();
		FinalizarTransferenciaBprivDTO dtoRequest = new FinalizarTransferenciaBprivDTO();
		dtoRequest.setCodigoFondo("023");
		dtoRequest.setCodigoFondoDest("007");
		dtoRequest.setCuentaTitulo("250-352350/8");
		dtoRequest.setImporte("889");
		dtoRequest.setDentroDelPerfil("N65.2");
		dtoRequest.setMoneda("$");
		Cliente cliente = mock(Cliente.class);

		Mockito.when(fondoBPrivDAO.transferir(Matchers.any(EjecucionFondoBancaPrivadaInEntity.class)))
				.thenThrow(new FueraDeHorarioException());

		Credential credenciales = new Credential();
		String usuario = "usuario";
		credenciales.setUsuario(usuario);
		String password = "password";
		credenciales.setPassword(password);
		Mockito.when(credentialSecurity.getCredentialBySistema(Matchers.any(String.class))).thenReturn(credenciales);

		String especieCodigo = "007";
		String moneda = "ARS";
		String habilitarTransferencia = "1";
		ConsultaFondoOutEntity respuestaFondo = new ConsultaFondoOutEntity();
		respuestaFondo.setEspecie(especieCodigo);
		respuestaFondo.setMoneda(moneda);
		String nombreFondo = "Nombre fondo";
		respuestaFondo.setNombreFondo(nombreFondo);
		respuestaFondo.setHabilitarTransferencia(habilitarTransferencia);
		Mockito.when(consultaFondoDAO.obtenerPorCodigo((Matchers.any(String.class)))).thenReturn(respuestaFondo);

		when(sessionParametros.getContador()).thenReturn(contadorIntentos);

		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("Mensaje mockeado del servicio");
		when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);

		Respuesta<FinalizarTransferenciaBprivDTO> responseFactoryError = new Respuesta<FinalizarTransferenciaBprivDTO>();
		responseFactoryError.setEstadoRespuesta(EstadoRespuesta.ERROR);
		responseFactoryError.setRespuestaVacia(true);

		Mockito.when(respuestaFactory.crearRespuestaWarning(Matchers.any(FinalizarTransferenciaBprivDTO.class), Matchers.anyString(),
				Matchers.any(TipoError.class), Matchers.anyString())).thenReturn(responseFactoryError);

		Respuesta<FinalizarTransferenciaBprivDTO> respuesta = transferenciaFondoBO
				.finalizarTransferenciaBPriv(dtoRequest, cliente);
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta, responseFactoryError);

	}
    
	/**
	 * Test FinalizarTransferenciaBPrivTimeOutException Sin Reintento
	 * @throws DAOException
	 * @throws BusinessException
	 * @throws SQLException
	 */
	@Test
	public void finalizarTransferenciaBPrivTimeOutExceptionSinReintentoTest() throws DAOException, BusinessException, SQLException {

		ContadorIntentos contadorIntentos = new ContadorIntentos();
		contadorIntentos.permiteReintentar();
		contadorIntentos.permiteReintentar();
		contadorIntentos.permiteReintentar();
		FinalizarTransferenciaBprivDTO dtoRequest = new FinalizarTransferenciaBprivDTO();
		dtoRequest.setCodigoFondo("023");
		dtoRequest.setCodigoFondoDest("007");
		dtoRequest.setCuentaTitulo("250-352350/8");
		dtoRequest.setImporte("889");
		dtoRequest.setDentroDelPerfil("N65.2");
		dtoRequest.setMoneda("$");
		Cliente cliente = mock(Cliente.class);

		Mockito.when(fondoBPrivDAO.transferir(Matchers.any(EjecucionFondoBancaPrivadaInEntity.class)))
				.thenThrow(new TimeOutException(""));

		Credential credenciales = new Credential();
		String usuario = "usuario";
		credenciales.setUsuario(usuario);
		String password = "password";
		credenciales.setPassword(password);
		Mockito.when(credentialSecurity.getCredentialBySistema(Matchers.any(String.class))).thenReturn(credenciales);

		String especieCodigo = "007";
		String moneda = "ARS";
		String habilitarTransferencia = "1";
		ConsultaFondoOutEntity respuestaFondo = new ConsultaFondoOutEntity();
		respuestaFondo.setEspecie(especieCodigo);
		respuestaFondo.setMoneda(moneda);
		String nombreFondo = "Nombre fondo";
		respuestaFondo.setNombreFondo(nombreFondo);
		respuestaFondo.setHabilitarTransferencia(habilitarTransferencia);
		Mockito.when(consultaFondoDAO.obtenerPorCodigo((Matchers.any(String.class)))).thenReturn(respuestaFondo);

		when(sessionParametros.getContador()).thenReturn(contadorIntentos);

		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("Mensaje mockeado del servicio");
		when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);

		Respuesta<FinalizarTransferenciaBprivDTO> responseFactoryError = new Respuesta<FinalizarTransferenciaBprivDTO>();
		responseFactoryError.setEstadoRespuesta(EstadoRespuesta.ERROR);
		responseFactoryError.setRespuestaVacia(true);

		Mockito.when(respuestaFactory.crearRespuestaError(FinalizarTransferenciaBprivDTO.class,
				"Mensaje mockeado del servicio", TipoError.ERROR_FINALIZAR_TRANSACCION_FONDO_SIN_REINTENTO.toString()))
				.thenReturn(responseFactoryError);

		Respuesta<FinalizarTransferenciaBprivDTO> respuesta = transferenciaFondoBO
				.finalizarTransferenciaBPriv(dtoRequest, cliente);
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta, responseFactoryError);
	}
	
	
	
	
	/** Test finalizarTransferenciaBPrivSaldoInsuficiente SinReintento
	 * 
	 * @throws DAOException
	 * @throws BusinessException
	 * @throws SQLException
	 */
	@Test
	public void finalizarTransferenciaBPrivSaldoInsuficienteExceptionSinReintento_Test() throws DAOException, BusinessException, SQLException {

		ContadorIntentos contadorIntentos = new ContadorIntentos();
		contadorIntentos.permiteReintentar();
		contadorIntentos.permiteReintentar();
		contadorIntentos.permiteReintentar();
		FinalizarTransferenciaBprivDTO dtoRequest = new FinalizarTransferenciaBprivDTO();
		dtoRequest.setCodigoFondo("023");
		dtoRequest.setCodigoFondoDest("007");
		dtoRequest.setCuentaTitulo("250-352350/8");
		dtoRequest.setImporte("889");
		dtoRequest.setDentroDelPerfil("N65.2");
		dtoRequest.setMoneda("$");
		Cliente cliente = mock(Cliente.class);

		Mockito.when(fondoBPrivDAO.transferir(Matchers.any(EjecucionFondoBancaPrivadaInEntity.class)))
				.thenThrow(new SaldoInsuficienteException());

		Credential credenciales = new Credential();
		String usuario = "usuario";
		credenciales.setUsuario(usuario);
		String password = "password";
		credenciales.setPassword(password);
		Mockito.when(credentialSecurity.getCredentialBySistema(Matchers.any(String.class))).thenReturn(credenciales);

		String especieCodigo = "007";
		String moneda = "ARS";
		String habilitarTransferencia = "1";
		ConsultaFondoOutEntity respuestaFondo = new ConsultaFondoOutEntity();
		respuestaFondo.setEspecie(especieCodigo);
		respuestaFondo.setMoneda(moneda);
		String nombreFondo = "Nombre fondo";
		respuestaFondo.setNombreFondo(nombreFondo);
		respuestaFondo.setHabilitarTransferencia(habilitarTransferencia);
		Mockito.when(consultaFondoDAO.obtenerPorCodigo((Matchers.any(String.class)))).thenReturn(respuestaFondo);

		when(sessionParametros.getContador()).thenReturn(contadorIntentos);

		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("Mensaje mockeado del servicio");
		when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);

		Respuesta<Object> responseFactoryError = new Respuesta<Object>();
		responseFactoryError.setEstadoRespuesta(EstadoRespuesta.ERROR);
		responseFactoryError.setRespuestaVacia(true);

		Mockito.when(respuestaFactory.crearRespuestaWarning("", TipoError.ERROR_SUPERA_SALDO_DISPONIBLE,
                CodigoMensajeConstantes.ERRO_SUPERA_SALDO_DISPONIBLE))
				.thenReturn(responseFactoryError);

		Respuesta<FinalizarTransferenciaBprivDTO> respuesta = transferenciaFondoBO
				.finalizarTransferenciaBPriv(dtoRequest, cliente);
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta, responseFactoryError);
	}
	
	
	/** Test finalizarTransferenciaBPrivSaldoInsuficiente ConReintento
	 * 
	 * @throws DAOException
	 * @throws BusinessException
	 * @throws SQLException
	 */
	@Test
	public void finalizarTransferenciaBPrivSaldoInsuficienteExceptionConReintento_Test() throws DAOException, BusinessException, SQLException {

		ContadorIntentos contadorIntentos = new ContadorIntentos();
		contadorIntentos.permiteReintentar();
		FinalizarTransferenciaBprivDTO dtoRequest = new FinalizarTransferenciaBprivDTO();
		dtoRequest.setCodigoFondo("023");
		dtoRequest.setCodigoFondoDest("007");
		dtoRequest.setCuentaTitulo("250-352350/8");
		dtoRequest.setImporte("889");
		dtoRequest.setDentroDelPerfil("N65.2");
		dtoRequest.setMoneda("$");
		Cliente cliente = mock(Cliente.class);

		Mockito.when(fondoBPrivDAO.transferir(Matchers.any(EjecucionFondoBancaPrivadaInEntity.class)))
				.thenThrow(new SaldoInsuficienteException());

		Credential credenciales = new Credential();
		String usuario = "usuario";
		credenciales.setUsuario(usuario);
		String password = "password";
		credenciales.setPassword(password);
		Mockito.when(credentialSecurity.getCredentialBySistema(Matchers.any(String.class))).thenReturn(credenciales);

		String especieCodigo = "007";
		String moneda = "ARS";
		String habilitarTransferencia = "1";
		ConsultaFondoOutEntity respuestaFondo = new ConsultaFondoOutEntity();
		respuestaFondo.setEspecie(especieCodigo);
		respuestaFondo.setMoneda(moneda);
		String nombreFondo = "Nombre fondo";
		respuestaFondo.setNombreFondo(nombreFondo);
		respuestaFondo.setHabilitarTransferencia(habilitarTransferencia);
		Mockito.when(consultaFondoDAO.obtenerPorCodigo((Matchers.any(String.class)))).thenReturn(respuestaFondo);

		when(sessionParametros.getContador()).thenReturn(contadorIntentos);

		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("Mensaje mockeado del servicio");
		when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);

		Respuesta<Object> responseFactoryError = new Respuesta<Object>();
        responseFactoryError.setEstadoRespuesta(EstadoRespuesta.ERROR);
        responseFactoryError.setRespuestaVacia(true);

        Mockito.when(respuestaFactory.crearRespuestaWarning("", TipoError.ERROR_SUPERA_SALDO_DISPONIBLE,
                CodigoMensajeConstantes.ERRO_SUPERA_SALDO_DISPONIBLE))
                .thenReturn(responseFactoryError);

		Respuesta<FinalizarTransferenciaBprivDTO> respuesta = transferenciaFondoBO
				.finalizarTransferenciaBPriv(dtoRequest, cliente);
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta, responseFactoryError);
	}
	
	
	/** FinalizarTransferenciaBPriv ImporteMinimo Exception
	 * 
	 * @throws DAOException
	 * @throws BusinessException
	 * @throws SQLException
	 */
	@Test
	public void finalizarTransferenciaBPrivImporteMinimoException_Test() throws DAOException, BusinessException, SQLException {

		ContadorIntentos contadorIntentos = new ContadorIntentos();
		contadorIntentos.permiteReintentar();
		FinalizarTransferenciaBprivDTO dtoRequest = new FinalizarTransferenciaBprivDTO();
		dtoRequest.setCodigoFondo("023");
		dtoRequest.setCodigoFondoDest("007");
		dtoRequest.setCuentaTitulo("250-352350/8");
		dtoRequest.setImporte("889");
		dtoRequest.setDentroDelPerfil("N65.2");
		dtoRequest.setMoneda("$");
		Cliente cliente = mock(Cliente.class);

		Mockito.when(fondoBPrivDAO.transferir(Matchers.any(EjecucionFondoBancaPrivadaInEntity.class)))
				.thenThrow(new ImporteLimiteException(""));

		Credential credenciales = new Credential();
		String usuario = "usuario";
		credenciales.setUsuario(usuario);
		String password = "password";
		credenciales.setPassword(password);
		Mockito.when(credentialSecurity.getCredentialBySistema(Matchers.any(String.class))).thenReturn(credenciales);

		String especieCodigo = "007";
		String moneda = "ARS";
		String habilitarTransferencia = "1";
		ConsultaFondoOutEntity respuestaFondo = new ConsultaFondoOutEntity();
		respuestaFondo.setEspecie(especieCodigo);
		respuestaFondo.setMoneda(moneda);
		String nombreFondo = "Nombre fondo";
		respuestaFondo.setNombreFondo(nombreFondo);
		respuestaFondo.setHabilitarTransferencia(habilitarTransferencia);
		Mockito.when(consultaFondoDAO.obtenerPorCodigo((Matchers.any(String.class)))).thenReturn(respuestaFondo);

		when(sessionParametros.getContador()).thenReturn(contadorIntentos);

		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("Mensaje mockeado del servicio");
		when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);

		Respuesta<Object> responseFactoryError = new Respuesta<Object>();
		responseFactoryError.setEstadoRespuesta(EstadoRespuesta.ERROR);
		responseFactoryError.setRespuestaVacia(true);

		 Mockito.when(respuestaFactory.crearRespuestaWarning(Matchers.anyString(), Matchers.any(TipoError.class),
		         Matchers.anyString())).thenReturn(responseFactoryError);


		Respuesta<FinalizarTransferenciaBprivDTO> respuesta = transferenciaFondoBO
				.finalizarTransferenciaBPriv(dtoRequest, cliente);
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta, responseFactoryError);
	}
	
	
	/** FinalizarTransferenciaBPriv Transferencia Bloqueada Con reintento Exception
	 * 
	 * @throws DAOException
	 * @throws BusinessException
	 * @throws SQLException
	 */
	@Test
	public void finalizarTransferenciaBPrivTransferenciaBloqueadaExceptionConReintento_Test() throws DAOException, BusinessException, SQLException {

		ContadorIntentos contadorIntentos = new ContadorIntentos();
		contadorIntentos.permiteReintentar();
		FinalizarTransferenciaBprivDTO dtoRequest = new FinalizarTransferenciaBprivDTO();
		dtoRequest.setCodigoFondo("023");
		dtoRequest.setCodigoFondoDest("007");
		dtoRequest.setCuentaTitulo("250-352350/8");
		dtoRequest.setImporte("889");
		dtoRequest.setDentroDelPerfil("N65.2");
		dtoRequest.setMoneda("$");
		Cliente cliente = mock(Cliente.class);

		Mockito.when(fondoBPrivDAO.transferir(Matchers.any(EjecucionFondoBancaPrivadaInEntity.class)))
				.thenThrow(new TransferenciaBloqueadaException());

		Credential credenciales = new Credential();
		String usuario = "usuario";
		credenciales.setUsuario(usuario);
		String password = "password";
		credenciales.setPassword(password);
		Mockito.when(credentialSecurity.getCredentialBySistema(Matchers.any(String.class))).thenReturn(credenciales);

		String especieCodigo = "007";
		String moneda = "ARS";
		String habilitarTransferencia = "1";
		ConsultaFondoOutEntity respuestaFondo = new ConsultaFondoOutEntity();
		respuestaFondo.setEspecie(especieCodigo);
		respuestaFondo.setMoneda(moneda);
		String nombreFondo = "Nombre fondo";
		respuestaFondo.setNombreFondo(nombreFondo);
		respuestaFondo.setHabilitarTransferencia(habilitarTransferencia);
		Mockito.when(consultaFondoDAO.obtenerPorCodigo((Matchers.any(String.class)))).thenReturn(respuestaFondo);

		when(sessionParametros.getContador()).thenReturn(contadorIntentos);

		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("Mensaje mockeado del servicio");
		when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
		
		Respuesta<FinalizarTransferenciaBprivDTO> responseFactoryError = new Respuesta<FinalizarTransferenciaBprivDTO>();
		responseFactoryError.setEstadoRespuesta(EstadoRespuesta.ERROR);
		responseFactoryError.setRespuestaVacia(true);

		Mockito.when(respuestaFactory.crearRespuestaError(FinalizarTransferenciaBprivDTO.class,
				"Mensaje mockeado del servicio", TipoError.ERROR_FINALIZAR_TRANSACCION_FONDO_CON_REINTENTO.toString()))
				.thenReturn(responseFactoryError);


		Respuesta<FinalizarTransferenciaBprivDTO> respuesta = transferenciaFondoBO
				.finalizarTransferenciaBPriv(dtoRequest, cliente);
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta, responseFactoryError);
	}

	
	
	/** FinalizarTransferenciaBPriv Transferencia Bloqueada Sin reintento Exception
	 * 
	 * @throws DAOException
	 * @throws BusinessException
	 * @throws SQLException
	 */
	@Test
	public void finalizarTransferenciaBPrivTransferenciaBloqueadaExceptionSinReintento_Test() throws DAOException, BusinessException, SQLException {
		ContadorIntentos contadorIntentos = new ContadorIntentos();
		contadorIntentos.permiteReintentar();
		contadorIntentos.permiteReintentar();
		contadorIntentos.permiteReintentar();
		FinalizarTransferenciaBprivDTO dtoRequest = new FinalizarTransferenciaBprivDTO();
		dtoRequest.setCodigoFondo("023");
		dtoRequest.setCodigoFondoDest("007");
		dtoRequest.setCuentaTitulo("250-352350/8");
		dtoRequest.setImporte("889");
		dtoRequest.setDentroDelPerfil("N65.2");
		dtoRequest.setMoneda("$");
		Cliente cliente = mock(Cliente.class);

		Mockito.when(fondoBPrivDAO.transferir(Matchers.any(EjecucionFondoBancaPrivadaInEntity.class)))
				.thenThrow(new TransferenciaBloqueadaException());

		Credential credenciales = new Credential();
		String usuario = "usuario";
		credenciales.setUsuario(usuario);
		String password = "password";
		credenciales.setPassword(password);
		Mockito.when(credentialSecurity.getCredentialBySistema(Matchers.any(String.class))).thenReturn(credenciales);

		String especieCodigo = "007";
		String moneda = "ARS";
		String habilitarTransferencia = "1";
		ConsultaFondoOutEntity respuestaFondo = new ConsultaFondoOutEntity();
		respuestaFondo.setEspecie(especieCodigo);
		respuestaFondo.setMoneda(moneda);
		String nombreFondo = "Nombre fondo";
		respuestaFondo.setNombreFondo(nombreFondo);
		respuestaFondo.setHabilitarTransferencia(habilitarTransferencia);
		Mockito.when(consultaFondoDAO.obtenerPorCodigo((Matchers.any(String.class)))).thenReturn(respuestaFondo);

		when(sessionParametros.getContador()).thenReturn(contadorIntentos);

		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("Mensaje mockeado del servicio");
		when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
		
		Respuesta<FinalizarTransferenciaBprivDTO> responseFactoryError = new Respuesta<FinalizarTransferenciaBprivDTO>();
		responseFactoryError.setEstadoRespuesta(EstadoRespuesta.ERROR);
		responseFactoryError.setRespuestaVacia(true);

		Mockito.when(respuestaFactory.crearRespuestaError(FinalizarTransferenciaBprivDTO.class,
				"Mensaje mockeado del servicio", TipoError.ERROR_FINALIZAR_TRANSACCION_FONDO_SIN_REINTENTO.toString()))
				.thenReturn(responseFactoryError);


		Respuesta<FinalizarTransferenciaBprivDTO> respuesta = transferenciaFondoBO
				.finalizarTransferenciaBPriv(dtoRequest, cliente);
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta, responseFactoryError);
	}
	
    
	/**
     * finalizar TransferenciaBPriv Fallo Error Generico
	 * 
     * @throws DAOException
     * @throws BusinessException
     */
	@Test
	public void finalizarTransferenciaBPrivFalloErrorGenericoTest() throws DAOException, BusinessException {
		ContadorIntentos contadorIntentos = new ContadorIntentos();
		contadorIntentos.permiteReintentar();
		FinalizarTransferenciaBprivDTO dtoRequest = new FinalizarTransferenciaBprivDTO();
		dtoRequest.setCodigoFondo("023");
		dtoRequest.setCodigoFondoDest("007");
		dtoRequest.setCuentaTitulo("250-352350/8");
		dtoRequest.setImporte("889");
		dtoRequest.setDentroDelPerfil("N65.2");
		dtoRequest.setMoneda("$");
		Cliente cliente = mock(Cliente.class);

		when(sessionParametros.getContador()).thenReturn(null);
		Respuesta<Object> responseFactoryError = new Respuesta<Object>();
		responseFactoryError.setEstadoRespuesta(EstadoRespuesta.ERROR);
		responseFactoryError.setRespuestaVacia(true);

		Mockito.when(respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
				CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS)).thenReturn(responseFactoryError);

		Respuesta<FinalizarTransferenciaBprivDTO> respuesta = transferenciaFondoBO
				.finalizarTransferenciaBPriv(dtoRequest, cliente);
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta, responseFactoryError);
	}

	
	
	/** Test finalizarTransferenciaBPrivDAOException ConReintento
	 * 
	 * @throws DAOException
	 * @throws BusinessException
	 * @throws SQLException
	 */
	@Test
	public void finalizarTransferenciaBPrivDAOExceptionConReintento_Test() throws DAOException, BusinessException, SQLException {
		ContadorIntentos contadorIntentos = new ContadorIntentos();
		contadorIntentos.permiteReintentar();
		FinalizarTransferenciaBprivDTO dtoRequest = new FinalizarTransferenciaBprivDTO();
		dtoRequest.setCodigoFondo("023");
		dtoRequest.setCodigoFondoDest("007");
		dtoRequest.setCuentaTitulo("250-352350/8");
		dtoRequest.setImporte("889");
		dtoRequest.setDentroDelPerfil("N65.2");
		dtoRequest.setMoneda("$");
		Cliente cliente = mock(Cliente.class);

		Mockito.when(fondoBPrivDAO.transferir(Matchers.any(EjecucionFondoBancaPrivadaInEntity.class)))
				.thenThrow(new DAOException());

		Credential credenciales = new Credential();
		String usuario = "usuario";
		credenciales.setUsuario(usuario);
		String password = "password";
		credenciales.setPassword(password);
		Mockito.when(credentialSecurity.getCredentialBySistema(Matchers.any(String.class))).thenReturn(credenciales);

		String especieCodigo = "007";
		String moneda = "ARS";
		String habilitarTransferencia = "1";
		ConsultaFondoOutEntity respuestaFondo = new ConsultaFondoOutEntity();
		respuestaFondo.setEspecie(especieCodigo);
		respuestaFondo.setMoneda(moneda);
		String nombreFondo = "Nombre fondo";
		respuestaFondo.setNombreFondo(nombreFondo);
		respuestaFondo.setHabilitarTransferencia(habilitarTransferencia);
		Mockito.when(consultaFondoDAO.obtenerPorCodigo((Matchers.any(String.class)))).thenReturn(respuestaFondo);

		when(sessionParametros.getContador()).thenReturn(contadorIntentos);

		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("Mensaje mockeado del servicio");
		when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);

		Respuesta<FinalizarTransferenciaBprivDTO> responseFactoryError = new Respuesta<FinalizarTransferenciaBprivDTO>();
		responseFactoryError.setEstadoRespuesta(EstadoRespuesta.ERROR);
		responseFactoryError.setRespuestaVacia(true);

		Mockito.when(respuestaFactory.crearRespuestaError(FinalizarTransferenciaBprivDTO.class,
				"Mensaje mockeado del servicio", TipoError.ERROR_FINALIZAR_TRANSACCION_FONDO_CON_REINTENTO.toString()))
				.thenReturn(responseFactoryError);

		Respuesta<FinalizarTransferenciaBprivDTO> respuesta = transferenciaFondoBO
				.finalizarTransferenciaBPriv(dtoRequest, cliente);
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta, responseFactoryError);
	}
	
	
	/** Test finalizarTransferenciaBPrivDAOException Con Reintento
	 * 
	 * @throws DAOException
	 * @throws BusinessException
	 * @throws SQLException
	 */
	@Test
	public void finalizarTransferenciaBPrivDAOExceptionSinReintento_Test() throws DAOException, BusinessException, SQLException {
		ContadorIntentos contadorIntentos = new ContadorIntentos();
		contadorIntentos.permiteReintentar();
		contadorIntentos.permiteReintentar();
		contadorIntentos.permiteReintentar();
		FinalizarTransferenciaBprivDTO dtoRequest = new FinalizarTransferenciaBprivDTO();
		dtoRequest.setCodigoFondo("023");
		dtoRequest.setCodigoFondoDest("007");
		dtoRequest.setCuentaTitulo("250-352350/8");
		dtoRequest.setImporte("889");
		dtoRequest.setDentroDelPerfil("N65.2");
		dtoRequest.setMoneda("$");
		Cliente cliente = mock(Cliente.class);

		Mockito.when(fondoBPrivDAO.transferir(Matchers.any(EjecucionFondoBancaPrivadaInEntity.class)))
				.thenThrow(new DAOException());

		Credential credenciales = new Credential();
		String usuario = "usuario";
		credenciales.setUsuario(usuario);
		String password = "password";
		credenciales.setPassword(password);
		Mockito.when(credentialSecurity.getCredentialBySistema(Matchers.any(String.class))).thenReturn(credenciales);

		String especieCodigo = "007";
		String moneda = "ARS";
		String habilitarTransferencia = "1";
		ConsultaFondoOutEntity respuestaFondo = new ConsultaFondoOutEntity();
		respuestaFondo.setEspecie(especieCodigo);
		respuestaFondo.setMoneda(moneda);
		String nombreFondo = "Nombre fondo";
		respuestaFondo.setNombreFondo(nombreFondo);
		respuestaFondo.setHabilitarTransferencia(habilitarTransferencia);
		Mockito.when(consultaFondoDAO.obtenerPorCodigo((Matchers.any(String.class)))).thenReturn(respuestaFondo);

		when(sessionParametros.getContador()).thenReturn(contadorIntentos);

		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("Mensaje mockeado del servicio");
		when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);

		Respuesta<FinalizarTransferenciaBprivDTO> responseFactoryError = new Respuesta<FinalizarTransferenciaBprivDTO>();
		responseFactoryError.setEstadoRespuesta(EstadoRespuesta.ERROR);
		responseFactoryError.setRespuestaVacia(true);

		Mockito.when(respuestaFactory.crearRespuestaError(FinalizarTransferenciaBprivDTO.class,
				"Mensaje mockeado del servicio", TipoError.ERROR_FINALIZAR_TRANSACCION_FONDO_SIN_REINTENTO.toString()))
				.thenReturn(responseFactoryError);

		Respuesta<FinalizarTransferenciaBprivDTO> respuesta = transferenciaFondoBO
				.finalizarTransferenciaBPriv(dtoRequest, cliente);
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta, responseFactoryError);
	}
	
	
    /**
     * SimularTransferencia OK
     *    
     * @throws DAOException
     * @throws BusinessException
     */
   
	@SuppressWarnings("unchecked")
	@Test
	public void simularTransferenciaOKTest() throws DAOException, BusinessException {

		TransferenciaDTO dtoRequest = new TransferenciaDTO();
        dtoRequest.setCodigoFondo("002");
        dtoRequest.setCodigoFondoDest("001");
        dtoRequest.setCuentaTitulo("00140742");
        dtoRequest.setImporteNeto("00000000100000");
        Cliente cliente = mock(Cliente.class);
		
        TransferenciaOutEntity simulacionTransferencia = new TransferenciaOutEntity();
        simulacionTransferencia.setCantCuotasPartes("00000015090947");
        simulacionTransferencia.setCodigoRetornoExtendido("00000000");
        simulacionTransferencia.setComisionValue("00000000000000");
        simulacionTransferencia.setDescripcionMoneda("PESOS");
        simulacionTransferencia.setDescripcionMonedaDestino("PESOS");
        simulacionTransferencia.setDiasCarencia("000");
        simulacionTransferencia.setEstadoActual("ACTIVA");
        simulacionTransferencia.setEstadoDestino("PENDIENTE");
        simulacionTransferencia.setFormaPago("00");
        simulacionTransferencia.setHeaderTrama("200000000000P04HTML0009900010301PRQP31  ********00629917000000092017050516291600000000IBF27528000000000000CNSTRFFCI_1200000            01576531    00        00 000000000201705051629430000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DH");
        simulacionTransferencia.setImporteBruto("00000000078899");
        simulacionTransferencia.setImporteNeto("00000000078899");
        simulacionTransferencia.setMotivoActual("SIN PAGAR");
        simulacionTransferencia.setMotivoDestino("TRANSFEREN");
        simulacionTransferencia.setNombreFondo("SUPER AHORRO $ CUOTA");
        simulacionTransferencia.setNombreFondoDestino("SUPERFDO RENTA FIJA CUOTA A");
        simulacionTransferencia.setPorcentComision("0000");
        simulacionTransferencia.setPorcentajeComisD("0000");

        Mockito.when(
        		transferenciaDAO.transferir(Matchers.any(TransferenciaInEntity.class)))
           .thenReturn(simulacionTransferencia);
   
		Respuesta<String> legal = new Respuesta<String>();
		legal.setEstadoRespuesta(EstadoRespuesta.OK);
		when(legalBO.buscarLegal(Matchers.anyString())).thenReturn(legal);
        
        ConsultaFondoOutEntity consultaFondoOutEntity = new ConsultaFondoOutEntity();
		consultaFondoOutEntity.setNombreFondo("Super fondo pesos");
		consultaFondoOutEntity.setIdMensajeGastos("10079");
		consultaFondoOutEntity.setCodigoFondo("023");
		consultaFondoOutEntity.setHabilitarSuscripcion("1");
		consultaFondoOutEntity.setTipoBanca("BP");
		consultaFondoOutEntity.setAgrupadorSuscripcion("agrupadorSuscripcion");
		consultaFondoOutEntity.setCodigoAgrupador("codigoAgrupador");
		consultaFondoOutEntity.setContratoSuscripcion("contratoSuscripcion");
		consultaFondoOutEntity.setEspecie("especie");
		consultaFondoOutEntity.setMoneda("ARS");
		consultaFondoOutEntity.setLinkRegla("reg_ahorro_plus.pdf");

		Mockito.when(consultaFondoDAO.obtenerPorCodigo((Matchers.any(String.class))))
				.thenReturn(consultaFondoOutEntity);
              
		Respuesta<TransferenciaDTO> respuestaSimularTransferencia = new Respuesta<TransferenciaDTO>();
		respuestaSimularTransferencia.setEstadoRespuesta(EstadoRespuesta.OK);
		
		Mockito.when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class),
				Matchers.any(SimularSuscripcionOutDTO.class))).thenReturn(respuestaSimularTransferencia);
		
		Respuesta<TransferenciaDTO> respuesta = transferenciaFondoBO.simularTransferencia(dtoRequest, cliente);
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);

	}
	
	
	
    /**
     * SimularTransferencia ERROR FueraDeHorario
     *    
     * @throws DAOException
     * @throws BusinessException
     */
   
	@Test
	public void simularTransferenciaERRORFueraDeHorarioTest() throws DAOException, BusinessException {

		TransferenciaDTO dtoRequest = new TransferenciaDTO();
        dtoRequest.setCodigoFondo("002");
        dtoRequest.setCodigoFondoDest("001");
        dtoRequest.setCuentaTitulo("00140742");
        dtoRequest.setImporteNeto("00000000100000");
        Cliente cliente = mock(Cliente.class);
		
        TransferenciaOutEntity simulacionTransferencia = new TransferenciaOutEntity();
        simulacionTransferencia.setCantCuotasPartes("00000015090947");
        simulacionTransferencia.setCodigoRetornoExtendido("00000000");
        simulacionTransferencia.setComisionValue("00000000000000");
        simulacionTransferencia.setDescripcionMoneda("PESOS");
        simulacionTransferencia.setDescripcionMonedaDestino("PESOS");
        simulacionTransferencia.setDiasCarencia("000");
        simulacionTransferencia.setEstadoActual("ACTIVA");
        simulacionTransferencia.setEstadoDestino("PENDIENTE");
        simulacionTransferencia.setFormaPago("00");
        simulacionTransferencia.setHeaderTrama("200000000000P04HTML0009900010301PRQP31  ********00629917000000092017050516291600000000IBF27528000000000000CNSTRFFCI_1200000            01576531    00        00 000000000201705051629430000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DH");
        simulacionTransferencia.setImporteBruto("00000000078899");
        simulacionTransferencia.setImporteNeto("00000000078899");
        simulacionTransferencia.setMotivoActual("SIN PAGAR");
        simulacionTransferencia.setMotivoDestino("TRANSFEREN");
        simulacionTransferencia.setNombreFondo("SUPER AHORRO $ CUOTA");
        simulacionTransferencia.setNombreFondoDestino("SUPERFDO RENTA FIJA CUOTA A");
        simulacionTransferencia.setPorcentComision("0000");
        simulacionTransferencia.setPorcentajeComisD("0000");

        Mockito.when(
        		transferenciaDAO.transferir(Matchers.any(TransferenciaInEntity.class)))
        	.thenThrow(new FueraDeHorarioException());
   
		Respuesta<String> legal = new Respuesta<String>();
		legal.setEstadoRespuesta(EstadoRespuesta.OK);
		when(legalBO.buscarLegal(Matchers.anyString())).thenReturn(legal);
        
        ConsultaFondoOutEntity consultaFondoOutEntity = new ConsultaFondoOutEntity();
		consultaFondoOutEntity.setNombreFondo("Super fondo pesos");
		consultaFondoOutEntity.setIdMensajeGastos("10079");
		consultaFondoOutEntity.setCodigoFondo("023");
		consultaFondoOutEntity.setHabilitarSuscripcion("1");
		consultaFondoOutEntity.setTipoBanca("BP");
		consultaFondoOutEntity.setAgrupadorSuscripcion("agrupadorSuscripcion");
		consultaFondoOutEntity.setCodigoAgrupador("codigoAgrupador");
		consultaFondoOutEntity.setContratoSuscripcion("contratoSuscripcion");
		consultaFondoOutEntity.setEspecie("especie");
		consultaFondoOutEntity.setMoneda("ARS");
		consultaFondoOutEntity.setLinkRegla("reg_ahorro_plus.pdf");

		Mockito.when(consultaFondoDAO.obtenerPorCodigo((Matchers.any(String.class))))
				.thenReturn(consultaFondoOutEntity);
              		
		Respuesta<TransferenciaDTO> respuestaSimularTransferencia = new Respuesta<TransferenciaDTO>();
		respuestaSimularTransferencia.setEstadoRespuesta(EstadoRespuesta.ERROR);
		respuestaSimularTransferencia.setRespuestaVacia(true);
                   
		Mockito.when(respuestaFactory.crearRespuestaWarning(Matchers.any(TransferenciaDTO.class), Matchers.anyString(),
				Matchers.any(TipoError.class), Matchers.anyString())).thenReturn(respuestaSimularTransferencia);

		Respuesta<TransferenciaDTO> respuesta = transferenciaFondoBO.simularTransferencia(dtoRequest, cliente);
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta, respuestaSimularTransferencia);

	}
	
	
	
	
    /**
     * SimularTransferencia ERROR ImporteMenorAlMinimo
     *    
     * @throws DAOException
     * @throws BusinessException
     */
   
	@Test
	public void simularTransferenciaERRORImporteMenorAlMinimoTest() throws DAOException, BusinessException {

		TransferenciaDTO dtoRequest = new TransferenciaDTO();
        dtoRequest.setCodigoFondo("002");
        dtoRequest.setCodigoFondoDest("001");
        dtoRequest.setCuentaTitulo("00140742");
        dtoRequest.setImporteNeto("00000000100000");
        Cliente cliente = mock(Cliente.class);
		
        TransferenciaOutEntity simulacionTransferencia = new TransferenciaOutEntity();
        simulacionTransferencia.setCantCuotasPartes("00000015090947");
        simulacionTransferencia.setCodigoRetornoExtendido("00000000");
        simulacionTransferencia.setComisionValue("00000000000000");
        simulacionTransferencia.setDescripcionMoneda("PESOS");
        simulacionTransferencia.setDescripcionMonedaDestino("PESOS");
        simulacionTransferencia.setDiasCarencia("000");
        simulacionTransferencia.setEstadoActual("ACTIVA");
        simulacionTransferencia.setEstadoDestino("PENDIENTE");
        simulacionTransferencia.setFormaPago("00");
        simulacionTransferencia.setHeaderTrama("200000000000P04HTML0009900010301PRQP31  ********00629917000000092017050516291600000000IBF27528000000000000CNSTRFFCI_1200000            01576531    00        00 000000000201705051629430000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DH");
        simulacionTransferencia.setImporteBruto("00000000078899");
        simulacionTransferencia.setImporteNeto("00000000078899");
        simulacionTransferencia.setMotivoActual("SIN PAGAR");
        simulacionTransferencia.setMotivoDestino("TRANSFEREN");
        simulacionTransferencia.setNombreFondo("SUPER AHORRO $ CUOTA");
        simulacionTransferencia.setNombreFondoDestino("SUPERFDO RENTA FIJA CUOTA A");
        simulacionTransferencia.setPorcentComision("0000");
        simulacionTransferencia.setPorcentajeComisD("0000");

        Mockito.when(
        		transferenciaDAO.transferir(Matchers.any(TransferenciaInEntity.class)))
        	.thenThrow(new ImporteMenorAlMinimoException());
   
		Respuesta<String> legal = new Respuesta<String>();
		legal.setEstadoRespuesta(EstadoRespuesta.OK);
		when(legalBO.buscarLegal(Matchers.anyString())).thenReturn(legal);
        
        ConsultaFondoOutEntity consultaFondoOutEntity = new ConsultaFondoOutEntity();
		consultaFondoOutEntity.setNombreFondo("Super fondo pesos");
		consultaFondoOutEntity.setIdMensajeGastos("10079");
		consultaFondoOutEntity.setCodigoFondo("023");
		consultaFondoOutEntity.setHabilitarSuscripcion("1");
		consultaFondoOutEntity.setTipoBanca("BP");
		consultaFondoOutEntity.setAgrupadorSuscripcion("agrupadorSuscripcion");
		consultaFondoOutEntity.setCodigoAgrupador("codigoAgrupador");
		consultaFondoOutEntity.setContratoSuscripcion("contratoSuscripcion");
		consultaFondoOutEntity.setEspecie("especie");
		consultaFondoOutEntity.setMoneda("ARS");
		consultaFondoOutEntity.setLinkRegla("reg_ahorro_plus.pdf");

		Mockito.when(consultaFondoDAO.obtenerPorCodigo((Matchers.any(String.class))))
				.thenReturn(consultaFondoOutEntity);
              		
		Respuesta<Object> respuestaSimularTransferencia = new Respuesta<Object>();
		respuestaSimularTransferencia.setEstadoRespuesta(EstadoRespuesta.ERROR);
		respuestaSimularTransferencia.setRespuestaVacia(true);
                   
        Mockito.when(respuestaFactory.crearRespuestaWarning("", TipoError.ERROR_LIMITE_MINIMO,
                "")).thenReturn(respuestaSimularTransferencia);
      
        
		Respuesta<TransferenciaDTO> respuesta = transferenciaFondoBO.simularTransferencia(dtoRequest, cliente);
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta, respuestaSimularTransferencia);

	}
	
	
	
	/**
	 * SimularTransferencia ERROR Transferencia Bloqueada
	 * 
	 * @throws DAOException
	 * @throws BusinessException
	 */

	@Test
	public void simularTransferenciaERRORTransferenciaBloqueadaTest() throws DAOException, BusinessException {

		TransferenciaDTO dtoRequest = new TransferenciaDTO();
		dtoRequest.setCodigoFondo("002");
		dtoRequest.setCodigoFondoDest("001");
		dtoRequest.setCuentaTitulo("00140742");
		dtoRequest.setImporteNeto("00000000100000");
		Cliente cliente = mock(Cliente.class);

		TransferenciaOutEntity simulacionTransferencia = new TransferenciaOutEntity();
		simulacionTransferencia.setCantCuotasPartes("00000015090947");
		simulacionTransferencia.setCodigoRetornoExtendido("00000000");
		simulacionTransferencia.setComisionValue("00000000000000");
		simulacionTransferencia.setDescripcionMoneda("PESOS");
		simulacionTransferencia.setDescripcionMonedaDestino("PESOS");
		simulacionTransferencia.setDiasCarencia("000");
		simulacionTransferencia.setEstadoActual("ACTIVA");
		simulacionTransferencia.setEstadoDestino("PENDIENTE");
		simulacionTransferencia.setFormaPago("00");
		simulacionTransferencia.setHeaderTrama(
				"200000000000P04HTML0009900010301PRQP31  ********00629917000000092017050516291600000000IBF27528000000000000CNSTRFFCI_1200000            01576531    00        00 000000000201705051629430000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DH");
		simulacionTransferencia.setImporteBruto("00000000078899");
		simulacionTransferencia.setImporteNeto("00000000078899");
		simulacionTransferencia.setMotivoActual("SIN PAGAR");
		simulacionTransferencia.setMotivoDestino("TRANSFEREN");
		simulacionTransferencia.setNombreFondo("SUPER AHORRO $ CUOTA");
		simulacionTransferencia.setNombreFondoDestino("SUPERFDO RENTA FIJA CUOTA A");
		simulacionTransferencia.setPorcentComision("0000");
		simulacionTransferencia.setPorcentajeComisD("0000");

		Mockito.when(transferenciaDAO.transferir(Matchers.any(TransferenciaInEntity.class)))
				.thenThrow(new TransferenciaBloqueadaException());

		Respuesta<String> legal = new Respuesta<String>();
		legal.setEstadoRespuesta(EstadoRespuesta.OK);
		when(legalBO.buscarLegal(Matchers.anyString())).thenReturn(legal);

		ConsultaFondoOutEntity consultaFondoOutEntity = new ConsultaFondoOutEntity();
		consultaFondoOutEntity.setNombreFondo("Super fondo pesos");
		consultaFondoOutEntity.setIdMensajeGastos("10079");
		consultaFondoOutEntity.setCodigoFondo("023");
		consultaFondoOutEntity.setHabilitarSuscripcion("1");
		consultaFondoOutEntity.setTipoBanca("BP");
		consultaFondoOutEntity.setAgrupadorSuscripcion("agrupadorSuscripcion");
		consultaFondoOutEntity.setCodigoAgrupador("codigoAgrupador");
		consultaFondoOutEntity.setContratoSuscripcion("contratoSuscripcion");
		consultaFondoOutEntity.setEspecie("especie");
		consultaFondoOutEntity.setMoneda("ARS");
		consultaFondoOutEntity.setLinkRegla("reg_ahorro_plus.pdf");

		Mockito.when(consultaFondoDAO.obtenerPorCodigo((Matchers.any(String.class))))
				.thenReturn(consultaFondoOutEntity);

		Respuesta<Object> respuestaSimularTransferencia = new Respuesta<Object>();
		respuestaSimularTransferencia.setEstadoRespuesta(EstadoRespuesta.ERROR);
		respuestaSimularTransferencia.setRespuestaVacia(true);

		Mockito.when(respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
				CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS)).thenReturn(respuestaSimularTransferencia);

		Respuesta<TransferenciaDTO> respuesta = transferenciaFondoBO.simularTransferencia(dtoRequest, cliente);
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta, respuestaSimularTransferencia);

	}
	
	
	
	/**
	 * SimularTransferencia ERROR DAOException 
	 * 
	 * @throws DAOException
	 * @throws BusinessException
	 */

	@Test
	public void simularTransferenciaERRORTransferenciaDAOExceptionTest() throws DAOException, BusinessException {

		TransferenciaDTO dtoRequest = new TransferenciaDTO();
		dtoRequest.setCodigoFondo("002");
		dtoRequest.setCodigoFondoDest("001");
		dtoRequest.setCuentaTitulo("00140742");
		dtoRequest.setImporteNeto("00000000100000");
		Cliente cliente = mock(Cliente.class);

		TransferenciaOutEntity simulacionTransferencia = new TransferenciaOutEntity();
		simulacionTransferencia.setCantCuotasPartes("00000015090947");
		simulacionTransferencia.setCodigoRetornoExtendido("00000000");
		simulacionTransferencia.setComisionValue("00000000000000");
		simulacionTransferencia.setDescripcionMoneda("PESOS");
		simulacionTransferencia.setDescripcionMonedaDestino("PESOS");
		simulacionTransferencia.setDiasCarencia("000");
		simulacionTransferencia.setEstadoActual("ACTIVA");
		simulacionTransferencia.setEstadoDestino("PENDIENTE");
		simulacionTransferencia.setFormaPago("00");
		simulacionTransferencia.setHeaderTrama(
				"200000000000P04HTML0009900010301PRQP31  ********00629917000000092017050516291600000000IBF27528000000000000CNSTRFFCI_1200000            01576531    00        00 000000000201705051629430000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DH");
		simulacionTransferencia.setImporteBruto("00000000078899");
		simulacionTransferencia.setImporteNeto("00000000078899");
		simulacionTransferencia.setMotivoActual("SIN PAGAR");
		simulacionTransferencia.setMotivoDestino("TRANSFEREN");
		simulacionTransferencia.setNombreFondo("SUPER AHORRO $ CUOTA");
		simulacionTransferencia.setNombreFondoDestino("SUPERFDO RENTA FIJA CUOTA A");
		simulacionTransferencia.setPorcentComision("0000");
		simulacionTransferencia.setPorcentajeComisD("0000");

		Mockito.when(transferenciaDAO.transferir(Matchers.any(TransferenciaInEntity.class)))
				.thenThrow(new DAOException());

		Respuesta<String> legal = new Respuesta<String>();
		legal.setEstadoRespuesta(EstadoRespuesta.OK);
		when(legalBO.buscarLegal(Matchers.anyString())).thenReturn(legal);

		ConsultaFondoOutEntity consultaFondoOutEntity = new ConsultaFondoOutEntity();
		consultaFondoOutEntity.setNombreFondo("Super fondo pesos");
		consultaFondoOutEntity.setIdMensajeGastos("10079");
		consultaFondoOutEntity.setCodigoFondo("023");
		consultaFondoOutEntity.setHabilitarSuscripcion("1");
		consultaFondoOutEntity.setTipoBanca("BP");
		consultaFondoOutEntity.setAgrupadorSuscripcion("agrupadorSuscripcion");
		consultaFondoOutEntity.setCodigoAgrupador("codigoAgrupador");
		consultaFondoOutEntity.setContratoSuscripcion("contratoSuscripcion");
		consultaFondoOutEntity.setEspecie("especie");
		consultaFondoOutEntity.setMoneda("ARS");
		consultaFondoOutEntity.setLinkRegla("reg_ahorro_plus.pdf");

		Mockito.when(consultaFondoDAO.obtenerPorCodigo((Matchers.any(String.class))))
				.thenReturn(consultaFondoOutEntity);

		Respuesta<Object> respuestaSimularTransferencia = new Respuesta<Object>();
		respuestaSimularTransferencia.setEstadoRespuesta(EstadoRespuesta.ERROR);
		respuestaSimularTransferencia.setRespuestaVacia(true);

		Mockito.when(respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
				CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS)).thenReturn(respuestaSimularTransferencia);
	
		Respuesta<TransferenciaDTO> respuesta = transferenciaFondoBO.simularTransferencia(dtoRequest, cliente);
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta, respuestaSimularTransferencia);

	}
	
	
    /**
     * SimularTransferencia ERROR
     *    
     * @throws DAOException
     * @throws BusinessException
     */
   

	@Test
	public void simularTransferenciaERRORTest() throws DAOException, BusinessException {

		TransferenciaDTO dtoRequest = new TransferenciaDTO();
        dtoRequest.setCodigoFondo(null);
        dtoRequest.setCodigoFondoDest(null);
        dtoRequest.setCuentaTitulo(null);
        dtoRequest.setImporteNeto(null);
        Cliente cliente = mock(Cliente.class);
		
        TransferenciaOutEntity simulacionTransferencia = new TransferenciaOutEntity();
        simulacionTransferencia.setCantCuotasPartes("00000015090947");
        simulacionTransferencia.setCodigoRetornoExtendido("00000000");
        simulacionTransferencia.setComisionValue("00000000000000");
        simulacionTransferencia.setDescripcionMoneda("PESOS");
        simulacionTransferencia.setDescripcionMonedaDestino("PESOS");
        simulacionTransferencia.setDiasCarencia("000");
        simulacionTransferencia.setEstadoActual("ACTIVA");
        simulacionTransferencia.setEstadoDestino("PENDIENTE");
        simulacionTransferencia.setFormaPago("00");
        simulacionTransferencia.setHeaderTrama("200000000000P04HTML0009900010301PRQP31  ********00629917000000092017050516291600000000IBF27528000000000000CNSTRFFCI_1200000            01576531    00        00 000000000201705051629430000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DH");
        simulacionTransferencia.setImporteBruto("00000000078899");
        simulacionTransferencia.setImporteNeto("00000000078899");
        simulacionTransferencia.setMotivoActual("SIN PAGAR");
        simulacionTransferencia.setMotivoDestino("TRANSFEREN");
        simulacionTransferencia.setNombreFondo("SUPER AHORRO $ CUOTA");
        simulacionTransferencia.setNombreFondoDestino("SUPERFDO RENTA FIJA CUOTA A");
        simulacionTransferencia.setPorcentComision("0000");
        simulacionTransferencia.setPorcentajeComisD("0000");

        Mockito.when(
        		transferenciaDAO.transferir(Matchers.any(TransferenciaInEntity.class)))
           .thenReturn(simulacionTransferencia);
   
		Respuesta<String> legal = new Respuesta<String>();
		legal.setEstadoRespuesta(EstadoRespuesta.OK);
		when(legalBO.buscarLegal(Matchers.anyString())).thenReturn(legal);
        
        ConsultaFondoOutEntity consultaFondoOutEntity = new ConsultaFondoOutEntity();
		consultaFondoOutEntity.setNombreFondo("Super fondo pesos");
		consultaFondoOutEntity.setIdMensajeGastos("10079");
		consultaFondoOutEntity.setCodigoFondo("023");
		consultaFondoOutEntity.setHabilitarSuscripcion("1");
		consultaFondoOutEntity.setTipoBanca("BP");
		consultaFondoOutEntity.setAgrupadorSuscripcion("agrupadorSuscripcion");
		consultaFondoOutEntity.setCodigoAgrupador("codigoAgrupador");
		consultaFondoOutEntity.setContratoSuscripcion("contratoSuscripcion");
		consultaFondoOutEntity.setEspecie("especie");
		consultaFondoOutEntity.setMoneda("ARS");
		consultaFondoOutEntity.setLinkRegla("reg_ahorro_plus.pdf");

		Mockito.when(consultaFondoDAO.obtenerPorCodigo((Matchers.any(String.class))))
				.thenReturn(consultaFondoOutEntity);
              
		Respuesta<Object> respuestaSimularTransferencia = new Respuesta<Object>();
		respuestaSimularTransferencia.setEstadoRespuesta(EstadoRespuesta.ERROR);
		respuestaSimularTransferencia.setRespuestaVacia(true);
		
		Mockito.when(respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
				CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS)).thenReturn(respuestaSimularTransferencia);
				
		Respuesta<TransferenciaDTO> respuesta = transferenciaFondoBO.simularTransferencia(dtoRequest, cliente);
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta, respuestaSimularTransferencia);

	}
	
	
	
}
