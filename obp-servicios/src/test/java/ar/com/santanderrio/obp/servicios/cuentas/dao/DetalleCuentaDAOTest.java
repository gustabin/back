package ar.com.santanderrio.obp.servicios.cuentas.dao;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import ar.com.santanderrio.obp.base.dao.BaseDatoDAO;
import ar.com.santanderrio.obp.servicios.api.accounts.AccountsApi;
import ar.com.santanderrio.obp.servicios.api.accounts.AccountsApiClient;
import ar.com.santanderrio.obp.servicios.api.accounts.entities.AccountDetailsEntity;
import ar.com.santanderrio.obp.servicios.api.accounts.entities.AccountEntity;
import ar.com.santanderrio.obp.servicios.api.accounts.entities.BalancesEntity;
import ar.com.santanderrio.obp.servicios.api.common.config.ApiException;
import ar.com.santanderrio.obp.servicios.home.web.view.AccionController;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import java.util.LinkedHashMap;
import java.util.Map;

import ar.com.santanderrio.obp.base.dao.BaseDatoDAO;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.base.service.ServiceException;
import ar.com.santanderrio.obp.iatx.IatxBaseDAOTest;
import ar.com.santanderrio.obp.iatx.IatxMatcher;
import ar.com.santanderrio.obp.servicios.api.accounts.AccountsApi;
import ar.com.santanderrio.obp.servicios.api.accounts.AccountsApiClient;
import ar.com.santanderrio.obp.servicios.api.accounts.entities.AccountDetailsEntity;
import ar.com.santanderrio.obp.servicios.api.accounts.entities.AccountEntity;
import ar.com.santanderrio.obp.servicios.api.accounts.entities.AccountsResponseEntity;
import ar.com.santanderrio.obp.servicios.api.accounts.entities.BalancesEntity;
import ar.com.santanderrio.obp.servicios.api.common.config.ApiException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.Segmento;
import ar.com.santanderrio.obp.servicios.clientes.exceptions.CredencialesException;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.cuentas.dao.impl.DetalleCuentaDAOImpl;
import ar.com.santanderrio.obp.servicios.cuentas.entities.ConsultaDetalleCuentaInEntity;
import ar.com.santanderrio.obp.servicios.cuentas.entities.ConsultaDetalleCuentaOutEntity;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DetalleCuentaEntity;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.modulos.dao.ModuloPermisoDAO;
import ar.com.santanderrio.obp.servicios.modulos.dao.ModuloPermisoDAOImpl;
import ar.com.santanderrio.obp.servicios.modulos.dominio.ModuloEstado;
import ar.com.santanderrio.obp.servicios.modulos.dominio.ModuloPermiso;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ClienteMock;
import ar.com.santanderrio.obp.servicios.transferencias.exception.CuentaMigradaException;
import ar.com.santanderrio.obp.servicios.transferencias.exception.TimeOutException;
import ar.com.santanderrio.obp.servicios.modulos.dao.ModuloPermisoDAO;
import ar.com.santanderrio.obp.servicios.modulos.dao.ModuloPermisoDAOImpl;
import ar.com.santanderrio.obp.servicios.modulos.dominio.ModuloPermiso;
import ar.com.santanderrio.obp.servicios.modulos.dominio.ModuloEstado;


/**
 * The Class DetalleCuentaDAO.
 */

@ContextConfiguration(loader = AnnotationConfigContextLoader.class,
        classes = { DetalleCuentaDAOTest.DetalleCuentaDAOTestConfiguration.class })
@TestPropertySource(properties = {
		   "CANAL.BANCA.PRIVADA=79",
		   "SUB.CANAL.BANCA.PRIVADA=00",
		})

public class DetalleCuentaDAOTest extends IatxBaseDAOTest {

    /** The detalleCuenta DAO. */
    @Autowired
    @InjectMocks
    private DetalleCuentaDAO detalleCuentaDAO;
    
    @Mock
    private AccountsApiClient accountsApiClient;
    
    @Mock
    private ModuloPermisoDAOImpl moduloPermisoDAO;
    
    @Mock
    private SesionCliente sesionCliente;

    /** The cliente. */
    private Cliente cliente = new Cliente();

    /** The TRAMA ERROR */
    private final String TRAMA_ERROR = "200000000000P04HTML0009900010302ITKA69  ********00884515000000052020012311523300000000        346000000000CNSCTADATO2110000            02890069    00        00 000000000202001231151570000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ00073 - SERVICIO TRANSACCIONAL INEXISTENTE ";
    
    /** The TRAMA ERROR */
    private final String TRAMA_OK= "200000000000P04HTML0009900010301AJEA60  ********00191059000000052020021210153400000000IBF3018C000000000000CNSCTADATO2200000            01094060    00        00 000000000202002121014580000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0060000000000õNõ   õ000000421969223+õ000000000000000+õ000000000000000+õ000000000000000+õ000000000000000+õ000000000000000+õ000000000000000+õ000000000000000+õ000000001000000+õ00000õ2000-08-30õ000000000000004+õ000000000000000+õ000000000000000+õ000000000000000+õ000000000000000+õ000000000000000+õ000000000000000+õ000000000000000+õ000000000000000+õ00000õ2000-08-30õA õ000000000000000+õ000000421969223+õ000000000000000+õ000000000000004+õ000000000000000+õ000000000000000+õ000000000000000+õ000000000000000+õ000000000000004+õ000000422969223+õ000000000000004+õ000000422969223+õ000000000000004+õ000000422969223+õSõDõ";
	
    
    ConsultaDetalleCuentaInEntity consultaDetalleCuentaInEntity;
    /** The canal banca privada. */
    @Value("${CANAL.BANCA.PRIVADA}")
    String canalBP;
    
	/** The sub canal banca privada. */
    @Value("${SUB.CANAL.BANCA.PRIVADA}")
    String subCanalBP;

    /**
     * Inits the.
     *
     * @throws ServiceException
     *             the service exception
     */
    @Before
    public void init() throws ServiceException {
        cliente = new Cliente();
        Segmento segmento = new Segmento();
        segmento.setSelect(false);
        segmento.setDuo(false);
        segmento.setPyme(false);
        segmento.setUniversidad(false);
        cliente.setNombre("CONSTANCIO PERCY");
        cliente.setApellido1("MILANDO");
        cliente.setApellido2("M");
        cliente.setSegmento(segmento);
        cliente.setNup("02714865");
        
        consultaDetalleCuentaInEntity = new ConsultaDetalleCuentaInEntity();
        consultaDetalleCuentaInEntity.setTipoCuenta("09");
        consultaDetalleCuentaInEntity.setSucursalCuenta("066");
        consultaDetalleCuentaInEntity.setNroCuenta("3560954");
        consultaDetalleCuentaInEntity.setEntidad("0072");
        consultaDetalleCuentaInEntity.setSucursal("0066");
        consultaDetalleCuentaInEntity.setNroCuentaAdd("000003560954");
        consultaDetalleCuentaInEntity.setDivisa("ARS");
        consultaDetalleCuentaInEntity.setCliente(cliente);


    }

    /**
     * The Class DetalleCuentaDAOTestConfiguration.
     */
    @Configuration
    public static class DetalleCuentaDAOTestConfiguration {

        /**
         * DetalleCuenta DAO.
         *
         * @return the DetalleCuenta DAO
         */
        @Bean
        public DetalleCuentaDAO detalleCuentaDAO() {
            return new DetalleCuentaDAOImpl();
        }

        /**
         * Sesion cliente.
         *
         * @return the sesion cliente
         */
        @Bean
        public static SesionCliente sesionCliente() {
            return Mockito.mock(SesionCliente.class);
		}
        
        @Bean
        public AccountsApi AccountsApi() {
        	return Mockito.mock(AccountsApi.class);
        }

        @Bean
        public ModuloPermisoDAO moduloPermisoDAO() {
        	return Mockito.mock(ModuloPermisoDAO.class);
        }
        
        @Bean
        public BaseDatoDAO baseDatoDAO() {
        	return Mockito.mock(BaseDatoDAO.class);
        }
        
    }

    /**
     * Obtener saldo actalizado OK.
     *
     * @throws DAOException
     *             the DAO exception
     * @throws IatxException
     *             the iatx exception
     */
    @Test
    public void obtenerSaldoActualizadoOK() throws DAOException, IatxException {
        // Given
        Cuenta cuenta = new Cuenta();
        cuenta.setCliente(ClienteMock.infoCliente());
        cuenta.setTipoCuentaSinUnificar("00");
        cuenta.setTipoCuenta("00");
        cuenta.setNroSucursal("0000");
        cuenta.setNroCuentaProducto("0315618");
        cuenta.setTipoCuentaEnum(TipoCuenta.CUENTA_CORRIENTE_PESOS);
        String servicio = "CNSCTASDOM";
        String version = "150";
        String tramaResponse = "200000000000P04HTML0009900010300CRQJ37  ********00491494000000132017060111005500000000IBF002LM000000000000CNSCTASDOM1500000            00276937    00        00 000000000201706011100470000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0091100000000õDõ008õ09õ033õ3612532õ00000003080014õ+õ00000000000000õ+õ00000000012000õ00000000000000õ00000000000000õ  õ00000081186268õ10õ033õ3612532õ00000004885426õ+õ00000000000000õ+õ00000000010000õ00000000000000õ00000000000000õ  õ00000004885426õ09õ033õ3663936õ00000143819598õ+õ00000000000000õ+õ00000000000000õ00000010000000õ00000000000000õN õ00000153819598õ10õ033õ3663936õ00010000006243õ+õ00000000000000õ+õ00000000000000õ00000010000000õ00000000000000õN õ00010010006243õ00õ000õ0315618õ00000003080014õ+õ00000000000000õ+õ00000000000000õ00000000000000õ00000000000000õN õ00000100315913õ00õ033õ0081995õ00000033228883õ+õ00000000000000õ+õ00000000000000õ00000000000000õ00000000000000õN õ00000033228883õ01õ201õ3243370õ00010000070615õ+õ00000000000000õ+õ00000000000000õ00000000000000õ00000000000000õN õ00010000070615õ04õ033õ0083717õ00000000009253õ+õ00000000000000õ+õ00000000000000õ00000000000000õ00000000000000õN õ00000000009253õ";
        
        List<Cuenta> cuentasMonetarias = new ArrayList<Cuenta>();
        cuentasMonetarias.add(cuenta);
        
        // When
        when(sesionCliente.getCliente()).thenReturn(ClienteMock.infoCliente());
        Mockito.when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);

        // Then
        List<Cuenta> cuentasFinal = detalleCuentaDAO.actualizarSaldo(cuentasMonetarias, cuenta.getCliente(), 1);

        // Expected
        Assert.assertNotNull(cuentasFinal);
        Assert.assertEquals(1, cuentasFinal.size());
        Assert.assertEquals("30800.14", cuentasFinal.get(0).getSaldoCuenta());
        Assert.assertEquals("30800.14", cuenta.getSaldoCuenta());
    }

    /**
     * Obtener saldo actalizado OK con cuenta unica pesos.
     *
     * @throws DAOException
     *             the DAO exception
     * @throws IatxException
     *             the iatx exception
     */
    @Test
    public void obtenerSaldoActalizadoOKConCuentaUnica() throws DAOException, IatxException {
        // Given
        Cuenta cuenta = new Cuenta();
        cuenta.setCliente(ClienteMock.infoCliente());
        cuenta.setTipoCuentaSinUnificar("09");
        cuenta.setTipoCuenta("02");
        cuenta.setNroSucursal("0033");
        cuenta.setNroCuentaProducto("0000000003612532");
        cuenta.setTipoCuentaEnum(TipoCuenta.CUENTA_UNICA_PESOS);
        String servicio = "CNSCTASDOM";
        String version = "150";
        String tramaResponse = "200000000000P04HTML0009900010300CRQJ37  ********00491494000000132017060111005500000000IBF002LM000000000000CNSCTASDOM1500000            00276937    00        00 000000000201706011100470000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0091100000000õDõ008õ09õ033õ3612532õ00000003080014õ+õ00000000000000õ+õ00000000012000õ00000000000000õ00000000000000õ  õ00000081186268õ10õ033õ3612532õ00000004885426õ+õ00000000000000õ+õ00000000010000õ00000000000000õ00000000000000õ  õ00000004885426õ09õ033õ3663936õ00000143819598õ+õ00000000000000õ+õ00000000000000õ00000010000000õ00000000000000õN õ00000153819598õ10õ033õ3663936õ00010000006243õ+õ00000000000000õ+õ00000000000000õ00000010000000õ00000000000000õN õ00010010006243õ00õ000õ0315618õ00000003080014õ+õ00000000000000õ+õ00000000000000õ00000000000000õ00000000000000õN õ00000100315913õ00õ033õ0081995õ00000033228883õ+õ00000000000000õ+õ00000000000000õ00000000000000õ00000000000000õN õ00000033228883õ01õ201õ3243370õ00010000070615õ+õ00000000000000õ+õ00000000000000õ00000000000000õ00000000000000õN õ00010000070615õ04õ033õ0083717õ00000000009253õ+õ00000000000000õ+õ00000000000000õ00000000000000õ00000000000000õN õ00000000009253õ";
        List<Cuenta> cuentasMonetarias = new ArrayList<Cuenta>();
        cuentasMonetarias.add(cuenta);
        
        Map<String, ModuloPermiso> modulosPermisos = new LinkedHashMap<String, ModuloPermiso>();
        ModuloPermiso permiso = new ModuloPermiso();
        permiso.setAccionController(AccionController.UTILIZAR_API_CUENTAS);
        permiso.setModuloEstado(ModuloEstado.OCULTAR);
        permiso.setMenu(false);
        modulosPermisos.put("usarApiCuentas", permiso);
        
        // When
    	when(moduloPermisoDAO.obtenerModulosPermisos()).thenReturn(modulosPermisos);
        when(sesionCliente.getCliente()).thenReturn(ClienteMock.infoCliente());
        Mockito.when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);

        // Then
        List<Cuenta> cuentasFinal = detalleCuentaDAO.actualizarSaldo(cuentasMonetarias, cuenta.getCliente(), 2);

        // Expected

        Assert.assertNotNull(cuentasFinal);
        Assert.assertEquals(2, cuentasFinal.size());
        Assert.assertEquals("30800.14", cuenta.getSaldoCUPesos());
        Assert.assertEquals("48854.26", cuenta.getSaldoCUDls());
    }

    /**
     * Obtener saldo actalizado con cod error distinto A cero.
     *
     * @throws DAOException
     *             the DAO exception
     * @throws IatxException
     *             the iatx exception
     */
    @Test
    public void obtenerSaldoActalizadoConCodErrorDistintoACero() throws DAOException, IatxException {
        // Given
        Cuenta cuenta = new Cuenta();
        cuenta.setCliente(ClienteMock.infoCliente());
        cuenta.setTipoCuentaSinUnificar("09");
        cuenta.setTipoCuenta("02");
        cuenta.setNroSucursal("0021");
        cuenta.setNroCuentaProducto("0000000001234567");
        cuenta.setTipoCuentaEnum(TipoCuenta.CUENTA_UNICA_PESOS);
        cuenta.setSaldoCUDls("31000.10");
        cuenta.setSaldoCUPesos("31000.00");
        String servicio = "CNSCTASDOM";
        String version = "150";
        String tramaResponse = "200000000000P04HTML0009900010300CRQJ37  ********00491494000000132017060111005500000000IBF002LM000000000000CNSCTASDOM1500000            00276937    00        00 000000000201706011100470000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0091100000000õDõ008õ09õ033õ3612532õ00000003080014õ+õ00000000000000õ+õ00000000012000õ00000000000000õ00000000000000õ  õ00000081186268õ10õ033õ3612532õ00000004885426õ+õ00000000000000õ+õ00000000010000õ00000000000000õ00000000000000õ  õ00000004885426õ09õ033õ3663936õ00000143819598õ+õ00000000000000õ+õ00000000000000õ00000010000000õ00000000000000õN õ00000153819598õ10õ033õ3663936õ00010000006243õ+õ00000000000000õ+õ00000000000000õ00000010000000õ00000000000000õN õ00010010006243õ00õ000õ0315618õ00000003080014õ+õ00000000000000õ+õ00000000000000õ00000000000000õ00000000000000õN õ00000100315913õ00õ033õ0081995õ00000033228883õ+õ00000000000000õ+õ00000000000000õ00000000000000õ00000000000000õN õ00000033228883õ01õ201õ3243370õ00010000070615õ+õ00000000000000õ+õ00000000000000õ00000000000000õ00000000000000õN õ00010000070615õ04õ033õ0083717õ00000000009253õ+õ00000000000000õ+õ00000000000000õ00000000000000õ00000000000000õN õ00000000009253õ";
        List<Cuenta> cuentasMonetarias = new ArrayList<Cuenta>();
        cuentasMonetarias.add(cuenta);

        // When
        when(sesionCliente.getCliente()).thenReturn(ClienteMock.infoCliente());
        Mockito.when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);

        // Then
        List<Cuenta> cuentasFinal = detalleCuentaDAO.actualizarSaldo(cuentasMonetarias, cuenta.getCliente(), 2);

        // Expected
        Assert.assertNotNull(cuentasFinal);
        Assert.assertEquals(0, cuentasFinal.size());
        Assert.assertEquals("31000.00", cuenta.getSaldoCUPesos());
        Assert.assertEquals("31000.10", cuenta.getSaldoCUDls());
    }

    /**
     * Obtener saldo actalizado iatx exception.
     *
     * @throws DAOException
     *             the DAO exception
     * @throws IatxException
     *             the iatx exception
     */
    @Test(expected = DAOException.class)
    public void obtenerSaldoActalizadoIatxException() throws DAOException, IatxException {
        // Given
        Cuenta cuenta = new Cuenta();
        cuenta.setCliente(ClienteMock.infoCliente());
        cuenta.setTipoCuentaSinUnificar("09");
        cuenta.setTipoCuenta("02");
        cuenta.setNroSucursal("0021");
        cuenta.setNroCuentaProducto("0000000001234567");
        cuenta.setTipoCuentaEnum(TipoCuenta.CUENTA_UNICA_DOLARES);
        String servicio = "CNSCTASDOM";
        String version = "150";
        List<Cuenta> cuentasMonetarias = new ArrayList<Cuenta>();
        cuentasMonetarias.add(cuenta);

        // When
        when(sesionCliente.getCliente()).thenReturn(ClienteMock.infoCliente());
        Mockito.when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version))))
                .thenThrow(new IatxException("Error de Iatx."));

        // Then
        detalleCuentaDAO.actualizarSaldo(cuentasMonetarias, cuenta.getCliente(), 2);
    }

    /**
     * Obtener saldo actalizado exception.
     *
     * @throws DAOException
     *             the DAO exception
     * @throws IatxException
     *             the iatx exception
     */
    @Test(expected = DAOException.class)
    public void obtenerSaldoActalizadoException() throws DAOException, IatxException {
        // Given
        Cuenta cuenta = new Cuenta();
        cuenta.setCliente(ClienteMock.infoCliente());
        cuenta.setTipoCuentaSinUnificar("09");
        cuenta.setTipoCuenta("02");
        cuenta.setNroSucursal("0021");
        cuenta.setNroCuentaProducto("0000000001234567");
        cuenta.setTipoCuentaEnum(TipoCuenta.CUENTA_UNICA_DOLARES);
        String servicio = "CNSCTASDOM";
        String version = "150";
        List<Cuenta> cuentasMonetarias = new ArrayList<Cuenta>();
        cuentasMonetarias.add(cuenta);

        // When
        when(sesionCliente.getCliente()).thenReturn(ClienteMock.infoCliente());
        Mockito.when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version))))
                .thenThrow(new NumberFormatException("Error inesperado."));

        // Then
        detalleCuentaDAO.actualizarSaldo(cuentasMonetarias, cuenta.getCliente(), 2);
    }

    /**
     * Consulta detalle cuenta OK.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws DAOException
     *             the DAO exception
     * @throws CredencialesException
     *             the credenciales exception
     */
    @Test
    public void consultaDetalleCuentaOK() throws IatxException, DAOException, CredencialesException {
        // Given
        String servicio = "CNSCTADATO";
        String version = "220";
        String tramaResponse = TRAMA_OK;

        Map<String, ModuloPermiso> modulosPermisos = new LinkedHashMap<String, ModuloPermiso>();
        ModuloPermiso permiso = new ModuloPermiso();
        permiso.setAccionController(AccionController.CONSUMO_API_ACCOUNTS);
        permiso.setModuloEstado(ModuloEstado.OCULTAR);
        permiso.setMenu(false);
        modulosPermisos.put("consumoApiAccounts", permiso);

        // When
        when(moduloPermisoDAO.obtenerModulosPermisos()).thenReturn(modulosPermisos);
        Mockito.when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);

        // Then
        ConsultaDetalleCuentaOutEntity consultaDetalleCuentaOutEntity = detalleCuentaDAO
                .consultaDetalleCuenta(consultaDetalleCuentaInEntity);

        // Expected
        Assert.assertNotNull(consultaDetalleCuentaOutEntity);
        Mockito.verify(iatxSender).send(Matchers.argThat(new IatxMatcher(servicio, version)));

    }

    /**
     * Consulta detalle cuenta con cod error distinto A cero.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws DAOException
     *             the DAO exception
     * @throws CredencialesException
     *             the credenciales exception
     */
    @SuppressWarnings("unused")
    @Test
    public void consultaDetalleCuentaConCodErrorDistintoACero()
            throws IatxException, DAOException, CredencialesException {
        // Given
        
        String servicio = "CNSCTADATO";
        String version = "220";
        String tramaResponse = TRAMA_ERROR;
        
        // When
        Map<String, ModuloPermiso> modulosPermisos = new LinkedHashMap<String, ModuloPermiso>();
        ModuloPermiso permiso = new ModuloPermiso();
        permiso.setAccionController(AccionController.CONSUMO_API_ACCOUNTS);
        permiso.setModuloEstado(ModuloEstado.OCULTAR);
        permiso.setMenu(false);
        modulosPermisos.put("consumoApiAccounts", permiso);

        when(moduloPermisoDAO.obtenerModulosPermisos()).thenReturn(modulosPermisos);
        when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version))))
        	.thenReturn(tramaResponse);
        
        
        try {
        	ConsultaDetalleCuentaOutEntity consultaDetalleCuentaOutEntity = detalleCuentaDAO
                    .consultaDetalleCuenta(consultaDetalleCuentaInEntity);
        }catch (Exception e) {
        	Assert.assertEquals(DAOException.class, e.getClass() );
        }
    }

    /**
     * Consulta detalle cuenta iatx exception timeout.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws DAOException
     *             the DAO exception
     * @throws CredencialesException
     *             the credenciales exception
     */
    @SuppressWarnings("unused")
    @Test(expected = TimeOutException.class)
    public void consultaDetalleCuentaIatxExceptionTimeout() throws IatxException, DAOException, CredencialesException {
        // Given
        String servicio = "CNSCTADATO";
        String version = "220";
        Map<String, ModuloPermiso> modulosPermisos = new LinkedHashMap<String, ModuloPermiso>();
        ModuloPermiso permiso = new ModuloPermiso();
        permiso.setAccionController(AccionController.CONSUMO_API_ACCOUNTS);
        permiso.setModuloEstado(ModuloEstado.OCULTAR);
        permiso.setMenu(false);
        modulosPermisos.put("consumoApiAccounts", permiso);

        // When
        when(moduloPermisoDAO.obtenerModulosPermisos()).thenReturn(modulosPermisos);
        Mockito.when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenThrow(new IatxException(
                "iatx.exceptions.IatxConnectException: Se envió la transacción al CICS pero no se recibió respuesta.õ"));

        // Expected
        ConsultaDetalleCuentaOutEntity consultaDetalleCuentaOutEntity = detalleCuentaDAO
                .consultaDetalleCuenta(consultaDetalleCuentaInEntity);
    }

    /**
     * Consulta detalle cuenta iatx exception.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws DAOException
     *             the DAO exception
     * @throws CredencialesException
     *             the credenciales exception
     */
    @SuppressWarnings("unused")
    @Test(expected = DAOException.class)
    public void consultaDetalleCuentaIatxException() throws IatxException, DAOException, CredencialesException {
        // Given
        String servicio = "CNSCTADATO";
        String version = "220";

        Map<String, ModuloPermiso> modulosPermisos = new LinkedHashMap<String, ModuloPermiso>();
        ModuloPermiso permiso = new ModuloPermiso();
        permiso.setAccionController(AccionController.CONSUMO_API_ACCOUNTS);
        permiso.setModuloEstado(ModuloEstado.OCULTAR);
        permiso.setMenu(false);
        modulosPermisos.put("consumoApiAccounts", permiso);


        // When
        when(moduloPermisoDAO.obtenerModulosPermisos()).thenReturn(modulosPermisos);
        Mockito.when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version))))
                .thenThrow(new IatxException("Error distinto a timeout."));

        // Expected
        ConsultaDetalleCuentaOutEntity consultaDetalleCuentaOutEntity = detalleCuentaDAO
                .consultaDetalleCuenta(consultaDetalleCuentaInEntity);
    }

    /**
     * Obtener detalle cuenta OK.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws IllegalAccessException
     *             the illegal access exception
     */
    @Test
    public void obtenerDetalleCuentaOK() throws IatxException, IllegalAccessException {
        // Given
        Cuenta cuenta = new Cuenta();
        cuenta.setCliente(ClienteMock.infoCliente());
        cuenta.setOficinaAltair("0000");
        cuenta.setContratoAltair("0000007000639170");
        cuenta.setEstadoTarjetaCredito("A ");
        cuenta.setTipoCuenta("02");
        String servicio = "CNSCTADATO";
        String version = "160";
        String tramaResponse = "200000000000P04HTML0009900010302GLPE92  ********00541128000000152017100210294400000000IBF000UR000000000000CNSCTADATO1600000            02615492    00        00 010208277201710021029320000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0052400000000õ14õ02õ000õ0639170õCOMIGNAGHI  VALERIANO PAUL TADõNõ00õ00000002028214+õ00000000000000+õ00000000000000õ00000000000000õ00000000000000õ00000000000000õ00000000000000õ00000000300000õ00000õ19980430õ00000049409809+õ00000000000000+õ00000000000000õ00000000000000õ00000000000000õ00000000000000õ00000000000000õ00000000000000õ00000õ19980430õA õ00000000000000+õ00000002028214+õ00000000000000+õ00000049409809+õ00000000000000õ00000000000000õ00000000000000õ00000000000000õ00000049409809õ00000002328214õ00000049409809õ00000002328214õ";

        // When
        Mockito.when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);

        // Then
        Respuesta<DetalleCuentaEntity> respDetalleCuentaEntity = detalleCuentaDAO.obtenerDetalleCuenta(cuenta);

        // Expected
        Assert.assertNotNull(respDetalleCuentaEntity);
        Assert.assertEquals(EstadoRespuesta.OK, respDetalleCuentaEntity.getEstadoRespuesta());
    }

    /**
     * Obtener detalle cuenta OK con estado tarjeta N.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws IllegalAccessException
     *             the illegal access exception
     */
    @Test
    public void obtenerDetalleCuentaOKConEstadoTarjetaN() throws IatxException, IllegalAccessException {
        // Given
        Cuenta cuenta = new Cuenta();
        cuenta.setCliente(ClienteMock.infoCliente());
        cuenta.setOficinaAltair("0000");
        cuenta.setContratoAltair("0000007000639170");
        cuenta.setEstadoTarjetaCredito("N ");
        cuenta.setTipoCuenta("02");
        String servicio = "CNSCTADATO";
        String version = "160";
        String tramaResponse = "200000000000P04HTML0009900010302GLPE92  ********00541128000000152017100210294400000000IBF000UR000000000000CNSCTADATO1600000            02615492    00        00 010208277201710021029320000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0052400000000õ14õ02õ000õ0639170õCOMIGNAGHI  VALERIANO PAUL TADõNõ00õ00000002028214+õ00000000000000+õ00000000000000õ00000000000000õ00000000000000õ00000000000000õ00000000000000õ00000000300000õ00000õ19980430õ00000049409809+õ00000000000000+õ00000000000000õ00000000000000õ00000000000000õ00000000000000õ00000000000000õ00000000000000õ00000õ19980430õA õ00000000000000+õ00000002028214+õ00000000000000+õ00000049409809+õ00000000000000õ00000000000000õ00000000000000õ00000000000000õ00000049409809õ00000002328214õ00000049409809õ00000002328214õ";

        // When
        Mockito.when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);

        // Then
        Respuesta<DetalleCuentaEntity> respDetalleCuentaEntity = detalleCuentaDAO.obtenerDetalleCuenta(cuenta);

        // Expected
        Assert.assertNotNull(respDetalleCuentaEntity);
        Assert.assertEquals(EstadoRespuesta.OK, respDetalleCuentaEntity.getEstadoRespuesta());
    }

    /**
     * Obtener detalle cuenta iatx exception.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws IllegalAccessException
     *             the illegal access exception
     */
    @Test
    public void obtenerDetalleCuentaIatxException() throws IatxException, IllegalAccessException {
        // Given
        Cuenta cuenta = new Cuenta();
        cuenta.setCliente(ClienteMock.infoCliente());
        cuenta.setOficinaAltair("0000");
        cuenta.setContratoAltair("0000007000639170");
        cuenta.setEstadoTarjetaCredito("A ");
        cuenta.setTipoCuenta("02");
        String servicio = "CNSCTADATO";
        String version = "160";

        // When
        Mockito.when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version))))
                .thenThrow(new IatxException("Error de Iatx."));

        // Then
        Respuesta<DetalleCuentaEntity> respDetalleCuentaEntity = detalleCuentaDAO.obtenerDetalleCuenta(cuenta);

        // Expected
        Assert.assertNotNull(respDetalleCuentaEntity);
        Assert.assertEquals(EstadoRespuesta.ERROR, respDetalleCuentaEntity.getEstadoRespuesta());
        Assert.assertEquals(TipoError.ERROR_GENERICO.getDescripcion(),
                respDetalleCuentaEntity.getItemsMensajeRespuesta().get(0).getTipoError());
    }

    /**
     * Obtener detalle cuenta exception.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws IllegalAccessException
     *             the illegal access exception
     */
    @Test
    public void obtenerDetalleCuentaException() throws IatxException, IllegalAccessException {
        // Given
        Cuenta cuenta = new Cuenta();
        cuenta.setCliente(ClienteMock.infoCliente());
        cuenta.setOficinaAltair("0000");
        cuenta.setContratoAltair("0000007000639170");
        cuenta.setEstadoTarjetaCredito("A ");
        cuenta.setTipoCuenta("02");
        String servicio = "CNSCTADATO";
        String version = "160";

        // When
        Mockito.when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version))))
                .thenThrow(new NumberFormatException("Error de conversion numerica."));

        // Then
        Respuesta<DetalleCuentaEntity> respDetalleCuentaEntity = detalleCuentaDAO.obtenerDetalleCuenta(cuenta);

        // Expected
        Assert.assertNotNull(respDetalleCuentaEntity);
        Assert.assertEquals(EstadoRespuesta.ERROR, respDetalleCuentaEntity.getEstadoRespuesta());
        Assert.assertEquals(TipoError.ERROR_GENERICO.getDescripcion(),
                respDetalleCuentaEntity.getItemsMensajeRespuesta().get(0).getTipoError());
    }

    /**
     * Obtener cuenta OK.
     *
     * @throws DAOException
     *             the DAO exception
     * @throws IatxException
     *             the iatx exception
     * @throws CuentaMigradaException 
     *  		the CuentaMigrada exception
     */
    @Test
    public void obtenerCuentaOK() throws DAOException, IatxException, CuentaMigradaException {
        // Given
        Cliente cliente = ClienteMock.infoCliente();
        String tipoCuenta = "02";
        String sucursalCuenta = "0000";
        String nroCuenta = "1234567";
        String servicio = "CNSCTATIT_";
        String version = "110";
        String tramaResponse = "200000000000P04HTML0009900010302GLQG47  ********00777612000000052017080217105700000000IBF00FOU000000000000CNSCTATIT_1100000            02616647    00        00 017129266201708021710470000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0006400000000õABAD PEREZ  BRAULIO           õT õ20076564257õ00000263õ";

        // When
        Mockito.when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);

        // Then
        Cliente clienteResponse = detalleCuentaDAO.obtenerCuenta(cliente, tipoCuenta, sucursalCuenta, nroCuenta);

        // Expected
        Assert.assertNotNull(clienteResponse);
    }
    
    @Test
    public void actualizarSaldoViaApi() throws DAOException {
        //Given
        Cuenta cuenta = new Cuenta();
        cuenta.setCliente(ClienteMock.infoCliente());
        cuenta.setTipoCuentaSinUnificar("09");
        cuenta.setTipoCuenta("02");
        cuenta.setNroSucursal("0021");
        cuenta.setNroCuentaProducto("0000000001234567");
        cuenta.setTipoCuentaEnum(TipoCuenta.CUENTA_UNICA_DOLARES);
        List<Cuenta> cuentasMonetarias = new ArrayList<Cuenta>();
        cuentasMonetarias.add(cuenta);

        Cuenta cajaAhorro = new Cuenta();
        cajaAhorro.setCliente(ClienteMock.infoCliente());
        cajaAhorro.setTipoCuentaSinUnificar("01");
        cajaAhorro.setTipoCuenta("01");
        cajaAhorro.setNroSucursal("0021");
        cajaAhorro.setNroCuentaProducto("0000000001234567");
        cajaAhorro.setTipoCuentaEnum(TipoCuenta.CAJA_AHORRO_PESOS);
        cuentasMonetarias.add(cajaAhorro);

        Map<String, ModuloPermiso> modulosPermisos = new LinkedHashMap<String, ModuloPermiso>();
        ModuloPermiso permiso = new ModuloPermiso();
        permiso.setAccionController(AccionController.UTILIZAR_API_CUENTAS);
        permiso.setModuloEstado(ModuloEstado.MOSTRAR);
        permiso.setMenu(true);
        modulosPermisos.put("usarApiCuentas", permiso);

        AccountsResponseEntity cuentasApi = new AccountsResponseEntity();
        List<AccountEntity> listAccounts = new ArrayList<AccountEntity>();
        AccountEntity account = new AccountEntity();
        account.setAccountId("00720769007003719097");
        account.setAccountNumber("01234567");
        account.setAgreementNumber("000000");
        account.setBranch("0021");
        List<AccountDetailsEntity> accountDetails = new ArrayList<AccountDetailsEntity>();
        AccountDetailsEntity accountDetailsEntity = new AccountDetailsEntity();
        accountDetailsEntity.setCurrency("ars");
        accountDetailsEntity.setType("saving");

        List<BalancesEntity> listaBalances = new ArrayList<BalancesEntity>();
        BalancesEntity balanceArs = new BalancesEntity();
        balanceArs.setType("main");
        balanceArs.setAmount(100);

        listaBalances.add(balanceArs);
        accountDetailsEntity.setBalances(listaBalances);

        AccountDetailsEntity accountDetailsEntityUsd = new AccountDetailsEntity();
        accountDetailsEntityUsd.setCurrency("usd");
        accountDetailsEntityUsd.setType("saving");

        List<BalancesEntity> listaBalancesUsd = new ArrayList<BalancesEntity>();
        BalancesEntity balanceUsd = new BalancesEntity();
        balanceUsd.setType("main");
        balanceUsd.setAmount(200);

        listaBalancesUsd.add(balanceUsd);
        accountDetailsEntityUsd.setBalances(listaBalancesUsd);

        accountDetails.add(accountDetailsEntity);
        accountDetails.add(accountDetailsEntityUsd);
        account.setAccountDetails(accountDetails);
        account.setBalances(listaBalances);

        AccountEntity cajaAhorroPesos = new AccountEntity();
        cajaAhorroPesos.setAccountId("00720769007003719097");
        cajaAhorroPesos.setAccountNumber("01234567");
        cajaAhorroPesos.setAgreementNumber("000000");
        cajaAhorroPesos.setBranch("0021");
        cajaAhorroPesos.setAccountDetails(accountDetails);
        cajaAhorroPesos.setBalances(listaBalances);

        listAccounts.add(account);
        listAccounts.add(cajaAhorroPesos);
        cuentasApi.setAccounts(listAccounts);

        //When
        when(moduloPermisoDAO.obtenerModulosPermisos()).thenReturn(modulosPermisos);
        when(accountsApiClient.getAccountsByCustomerId(Matchers.anyString())).thenReturn(cuentasApi);

        //Then
        List<Cuenta> listaCuentas = detalleCuentaDAO.actualizarSaldo(cuentasMonetarias, cajaAhorro.getCliente(), 1);

        //Expected
        Assert.assertNotNull(listaCuentas);
    }
    
    @SuppressWarnings("unchecked")
	@Test(expected = DAOException.class)
    public void actualizarSaldoViaApiErrorException( ) throws DAOException, ApiException {
    	
    	//Given
        List<Cuenta> cuentasMonetarias = new ArrayList<Cuenta>();

        Cuenta cajaAhorro = new Cuenta();
        cajaAhorro.setCliente(ClienteMock.infoCliente());
        cuentasMonetarias.add(cajaAhorro);
    	
        Map<String, ModuloPermiso> modulosPermisos = new LinkedHashMap<String, ModuloPermiso>();
        ModuloPermiso permiso = new ModuloPermiso();
        permiso.setAccionController(AccionController.UTILIZAR_API_CUENTAS);
        permiso.setModuloEstado(ModuloEstado.MOSTRAR);
        permiso.setMenu(true);
        modulosPermisos.put("usarApiCuentas", permiso);
        
        //When
    	when(moduloPermisoDAO.obtenerModulosPermisos()).thenReturn(modulosPermisos);
    	when(accountsApiClient.getAccountsByCustomerId(Matchers.anyString())).thenThrow(ApiException.class);

        //Then
    	detalleCuentaDAO.actualizarSaldo(cuentasMonetarias, cajaAhorro.getCliente(), 1);
    }

    @Test

    public void consultaDetalleCuentaAPI() throws DAOException {
        Map<String, ModuloPermiso> modulosPermisos = new LinkedHashMap<String, ModuloPermiso>();
        ModuloPermiso permiso = new ModuloPermiso();
        permiso.setAccionController(AccionController.CONSUMO_API_ACCOUNTS);
        permiso.setModuloEstado(ModuloEstado.MOSTRAR);
        permiso.setMenu(true);
        modulosPermisos.put("consumoApiAccounts", permiso);
        when(moduloPermisoDAO.obtenerModulosPermisos()).thenReturn(modulosPermisos);

        List<AccountEntity> listAccounts = new ArrayList<AccountEntity>();
        AccountEntity account = new AccountEntity();
        account.setAccountId("00720769007003719097");
        account.setAccountNumber("01234567");
        account.setAgreementNumber("000000");
        account.setBranch("0021");
        List<AccountDetailsEntity> accountDetails = new ArrayList<AccountDetailsEntity>();
        AccountDetailsEntity accountDetailsEntity = new AccountDetailsEntity();
        accountDetailsEntity.setCurrency("ars");
        accountDetailsEntity.setType("saving");

        AccountDetailsEntity accountDetailsEntityCurrent = new AccountDetailsEntity();
        accountDetailsEntityCurrent.setCurrency("ars");
        accountDetailsEntityCurrent.setType("current");

        List<BalancesEntity> listaBalancesNeto = new ArrayList<BalancesEntity>();
        List<BalancesEntity> listaBalances = new ArrayList<BalancesEntity>();
        BalancesEntity balanceArsMain = new BalancesEntity();
        balanceArsMain.setType("main");
        balanceArsMain.setCurrency("ars");
        balanceArsMain.setAmount(100);

        BalancesEntity balanceArsReal = new BalancesEntity();
        balanceArsReal.setType("real");
        balanceArsReal.setCurrency("ars");
        balanceArsReal.setAmount(100);

        BalancesEntity balanceArsSobregiro = new BalancesEntity();
        balanceArsSobregiro.setType("total_agreement");
        balanceArsSobregiro.setCurrency("ars");
        balanceArsSobregiro.setAmount(100);

        listaBalances.add(balanceArsMain);
        listaBalancesNeto.add(balanceArsMain);

        listaBalances.add(balanceArsReal);
        listaBalancesNeto.add(balanceArsReal);

        listaBalances.add(balanceArsSobregiro);
        listaBalancesNeto.add(balanceArsSobregiro);
        accountDetailsEntity.setBalances(listaBalances);
        accountDetailsEntityCurrent.setBalances(listaBalances);

        AccountDetailsEntity accountDetailsEntityUsd = new AccountDetailsEntity();
        accountDetailsEntityUsd.setCurrency("usd");
        accountDetailsEntityUsd.setType("saving");

        AccountDetailsEntity accountDetailsEntityUsdCurrent = new AccountDetailsEntity();
        accountDetailsEntityUsdCurrent.setCurrency("usd");
        accountDetailsEntityUsdCurrent.setType("current");

        List<BalancesEntity> listaBalancesUsd = new ArrayList<BalancesEntity>();
        BalancesEntity balanceUsd = new BalancesEntity();
        balanceUsd.setType("main");
        balanceUsd.setCurrency("usd");
        balanceUsd.setAmount(200);

        BalancesEntity balanceUsdReal = new BalancesEntity();
        balanceUsdReal.setType("real");
        balanceUsdReal.setCurrency("usd");
        balanceUsdReal.setAmount(200);

        BalancesEntity balanceUsdSobregiro = new BalancesEntity();
        balanceUsdSobregiro.setType("total_agreement");
        balanceUsdSobregiro.setCurrency("usd");
        balanceUsdSobregiro.setAmount(200);

        listaBalancesUsd.add(balanceUsd);
        listaBalancesNeto.add(balanceUsd);

        listaBalancesUsd.add(balanceUsdReal);
        listaBalancesNeto.add(balanceUsdReal);

        listaBalancesUsd.add(balanceUsdSobregiro);
        listaBalancesNeto.add(balanceUsdSobregiro);

        accountDetailsEntityUsd.setBalances(listaBalancesUsd);
        accountDetailsEntityUsdCurrent.setBalances(listaBalancesUsd);

        accountDetails.add(accountDetailsEntity);
        accountDetails.add(accountDetailsEntityCurrent);
        accountDetails.add(accountDetailsEntityUsdCurrent);
        accountDetails.add(accountDetailsEntityUsd);
        account.setAccountDetails(accountDetails);
        account.setBalances(listaBalancesNeto);

        List<String> etiquetas = new ArrayList<String>();
        etiquetas.add("overdraft");
        account.setTags(etiquetas);
        account.setCreationDate("09-09-1999-");

        //When
        when(moduloPermisoDAO.obtenerModulosPermisos()).thenReturn(modulosPermisos);
        when(accountsApiClient.getAccountByAccountId(Matchers.anyString(),Matchers.anyString())).thenReturn(account);

        //Then
        ConsultaDetalleCuentaOutEntity consultaDetalleCuentaOutEntity = detalleCuentaDAO
                .consultaDetalleCuenta(consultaDetalleCuentaInEntity);

        //Expected
        Assert.assertNotNull(consultaDetalleCuentaOutEntity);
    }

    @SuppressWarnings("unchecked")
    @Test(expected = NullPointerException.class)
    public void consultaDetalleCuentaAPIException() throws DAOException, ApiException {

        //Given
        Map<String, ModuloPermiso> modulosPermisos = new LinkedHashMap<String, ModuloPermiso>();
        ModuloPermiso permiso = new ModuloPermiso();
        permiso.setAccionController(AccionController.CONSUMO_API_ACCOUNTS);
        permiso.setModuloEstado(ModuloEstado.MOSTRAR);
        permiso.setMenu(true);
        modulosPermisos.put("usarApiCuentas", permiso);

        //When
        when(moduloPermisoDAO.obtenerModulosPermisos()).thenReturn(modulosPermisos);
        when(accountsApiClient.getAccountByAccountId(Matchers.anyString(), Matchers.anyString())).thenThrow(ApiException.class);

        //Then
        detalleCuentaDAO.consultaDetalleCuenta(consultaDetalleCuentaInEntity);
    }
}