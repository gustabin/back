package ar.com.santanderrio.obp.servicios.aumentolimitetransferencia.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import ar.com.santanderrio.obp.base.config.SecurityProviderConfig;
import ar.com.santanderrio.obp.base.context.ContextHolder;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.profile.Profiles;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.base.security.credential.CredentialSecurityFactory;
import ar.com.santanderrio.obp.base.security.keystore.KeyStore;
import ar.com.santanderrio.obp.base.security.keystore.KeyStoreFactory;
import ar.com.santanderrio.obp.generated.webservices.productos.ArrayOf158770343432493182NillableInfoRequeridaWS;
import ar.com.santanderrio.obp.generated.webservices.productos.ArrayOf324931821440052060NillableString;
import ar.com.santanderrio.obp.generated.webservices.productos.InfoRequeridaWS;
import ar.com.santanderrio.obp.servicios.aumentolimitetransferencia.dao.impl.AumentoLimiteTransferenciaDAOImpl;
import ar.com.santanderrio.obp.servicios.aumentolimitetransferencia.dto.AumentoLimiteTransferenciaInDTO;
import ar.com.santanderrio.obp.servicios.aumentolimitetransferencia.entities.AumentoLimiteTransferenciaInEntity;
import ar.com.santanderrio.obp.servicios.aumentolimitetransferencia.entities.AumentoLimiteTransferenciaOutEntity;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.constantes.BancoEnum;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.inversiones.TipoMonedaInversionEnum;
import ar.com.santanderrio.obp.servicios.producto.dao.SolicitudProductoGestionarWSImpl;
import ar.com.santanderrio.obp.servicios.aumentolimitetransferencia.dao.AumentoLimiteTransferenciaDAOT.AumentoLimiteTransferenciaDAOITConfiguration;

/**
 * Clase test AumentoLimiteTransferenciaDAOT
 * @author Leonid_Komratov
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { AumentoLimiteTransferenciaDAOITConfiguration.class, SecurityProviderConfig.class })
@ActiveProfiles(Profiles.TEST)
@TestPropertySource(properties = {"SOLICITUDES.POOL.ACTIVO=false", 
        "SOLICITUDES.URL=http://washomofront03.ar.bsch:19085/gec/services/WS_GC",
        "SOLICITUDES.TIMEOUT=100", 
        "SOLICITUDES.POOL.SIZE=30", 
        "SOLICITUDES.POOL.MAXWAIT=50",
        "APP.ENCODING = UTF-8"})
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class AumentoLimiteTransferenciaDAOT {
    
    @Autowired
    private AumentoLimiteTransferenciaDAO aumentoLimiteTransferenciaDAO;
    
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * The Class GestionWSTestConfiguration.
     */
    @Configuration
    @ComponentScan(basePackageClasses = { SolicitudProductoGestionarWSImpl.class, AumentoLimiteTransferenciaDAOImpl.class, ContextHolder.class })
    public static class AumentoLimiteTransferenciaDAOITConfiguration {
        
        /**
         * Key store factory.
         *
         * @return the key store factory
         */
        @Bean
        public KeyStoreFactory keyStoreFactory() {
            return Mockito.mock(KeyStoreFactory.class, new Answer<KeyStore>() {

                /*
                 * (non-Javadoc)
                 * 
                 * @see
                 * org.mockito.stubbing.Answer#answer(org.mockito.invocation.
                 * InvocationOnMock)
                 */
                @Override
                public KeyStore answer(InvocationOnMock invocation) throws Throwable {
                    KeyStore keyStore = new KeyStore();
                    keyStore.setKeystoreType("JKS");
                    keyStore.setKeystorePath(Object.class.getResource("/config/test/keyStore/hbkey.jks").getPath());
                    keyStore.setKeystoreAlias("bcorio");
                    keyStore.setKeystorePassword("hbpassword");
                    return keyStore;
                }

            });
        }

        @Bean
        public CredentialSecurityFactory credentialSecurityFactory() {
            return Mockito.mock(CredentialSecurityFactory.class);
        }
        
        /**
         * Property configurer.
         *
         * @return the property sources placeholder configurer
         */
        @Bean
        public static PropertySourcesPlaceholderConfigurer propertyConfigurer() {
            return new PropertySourcesPlaceholderConfigurer();
        }
        
    }
    
    @Test
    public void altaSolicitudAumentoLimiteTransferenciaOKTest() throws DAOException {
        AumentoLimiteTransferenciaInEntity inEntity = new AumentoLimiteTransferenciaInEntity();
        inEntity.setCodAsociacion(Integer.parseInt("88"));
        inEntity.setTipoPersona("F");
        inEntity.setNup(Integer.parseInt("02615492"));
        inEntity.setCodUser("HBAN0003");
        inEntity.setCodSector("RLIN");
        inEntity.setMedioIngreso(Integer.parseInt("13"));
        inEntity.setComentario("Solicitud de aumento de lÃ­mite");
        
        ArrayOf158770343432493182NillableInfoRequeridaWS array = new ArrayOf158770343432493182NillableInfoRequeridaWS();
        array =  crearArrayInfoWS(false);
        inEntity.setInfoRequerida(array);
        Respuesta<AumentoLimiteTransferenciaOutEntity> respuesta = new Respuesta<AumentoLimiteTransferenciaOutEntity>();
        respuesta = aumentoLimiteTransferenciaDAO.altaSolicitudAumentoLimiteTransferencia(inEntity);
        
        Assert.assertNotNull(respuesta.getRespuesta());
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
        Assert.assertNotNull(respuesta.getRespuesta().getNroGestion());
    }
    
    @Test
    public void altaSolicitudAumentoLimiteTransferenciaERRORTest() throws DAOException {
        AumentoLimiteTransferenciaInEntity inEntity = new AumentoLimiteTransferenciaInEntity();
        inEntity.setCodAsociacion(Integer.parseInt("88"));
        inEntity.setTipoPersona("F");
        inEntity.setNup(Integer.parseInt("02615492"));
        inEntity.setCodUser("HBAN0003");
        inEntity.setCodSector("RLIN");
        inEntity.setMedioIngreso(Integer.parseInt("13"));
        inEntity.setComentario("Solicitud de aumento de lÃ­mite");
        
        ArrayOf158770343432493182NillableInfoRequeridaWS array = new ArrayOf158770343432493182NillableInfoRequeridaWS();
        array =  crearArrayInfoWS(true);
        inEntity.setInfoRequerida(array);
        Respuesta<AumentoLimiteTransferenciaOutEntity> respuesta = new Respuesta<AumentoLimiteTransferenciaOutEntity>();
        respuesta = aumentoLimiteTransferenciaDAO.altaSolicitudAumentoLimiteTransferencia(inEntity);
        
        Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
    }
    
    private ArrayOf158770343432493182NillableInfoRequeridaWS crearArrayInfoWS(boolean conError) {
    
        ArrayOf158770343432493182NillableInfoRequeridaWS infoRequerida = new ArrayOf158770343432493182NillableInfoRequeridaWS();
        List<InfoRequeridaWS> listInfo = new ArrayList<InfoRequeridaWS>();
    
        AumentoLimiteTransferenciaInDTO inDTO = obtenerInDto(false);
        
        Cliente cliente = inDTO.getCliente();
        
        String nroProductoCuenta = ISBANStringUtils
                .formateadorConCerosIzq(ISBANStringUtils.extraerCuenta(inDTO.getNroCuenta()), 16);
        Cuenta cuentaSeleccionada = cliente.getCuentaPorNumero(nroProductoCuenta);
        
        listInfo.add(this.crearCampoRequeridoWS("Fecha de la operacion", Integer.parseInt("2272"), inDTO.getFechaEjecucion()));
        listInfo.add(this.crearCampoRequeridoWS("Canal", Integer.parseInt("2240"), "OBP"));
        listInfo.add(this.crearCampoRequeridoWS("Codigo de canal", Integer.parseInt("2960"), "04"));
        listInfo.add(this.crearCampoRequeridoWS("Codigo de subcanal", Integer.parseInt("2961"), "99"));
        listInfo.add(this.crearCampoRequeridoWS("Tipo de operacion", Integer.parseInt("2966"), "TPMF"));
        listInfo.add(this.crearCampoRequeridoWS("Importe a transferir", Integer.parseInt("541"), formatearImporte(inDTO.getImporte())));
        listInfo.add(this.crearCampoRequeridoWS("Moneda", Integer.parseInt("2967"), TipoMonedaInversionEnum.fromMonedaString(inDTO.getMoneda()).getCodigo()));
        listInfo.add(this.crearCampoRequeridoWS("Sucursal de origen", Integer.parseInt("2962"), cuentaSeleccionada.getNroSucursal()));
        listInfo.add(this.crearCampoRequeridoWS("Cuenta de origen", Integer.parseInt("2963"), cuentaSeleccionada.getContratoAltair()));
        String monedaOrigen = TipoCuenta.CUENTA_UNICA.getDescripcion().equalsIgnoreCase(cuentaSeleccionada.getTipoCuenta()) ? cuentaSeleccionada.getMonedaAltair() : TipoMonedaInversionEnum.fromMonedaString(inDTO.getMoneda()).getCodigo2();
        listInfo.add(this.crearCampoRequeridoWS("Moneda de origen", Integer.parseInt("604"), monedaOrigen));
        listInfo.add(this.crearCampoRequeridoWS("Codigo del producto", Integer.parseInt("2968"), cuentaSeleccionada.getProductoAltair()));
        listInfo.add(this.crearCampoRequeridoWS("Codigo del subproducto", Integer.parseInt("2954"), cuentaSeleccionada.getSubproductoAltair()));
        int maxLengthCtaOrigen = inDTO.getTipoCuentaDescripcion().length() >= 22 ? 22 : inDTO.getTipoCuentaDescripcion().length();
        String descripcionProd = ISBANStringUtils.normalizarCaraceteres(inDTO.getTipoCuentaDescripcion().substring(0, maxLengthCtaOrigen));
        listInfo.add(this.crearCampoRequeridoWS("Descripcion del prooducto", Integer.parseInt("1684"), descripcionProd));
        
        boolean tieneAlias = !ISBANStringUtils.isEmptyOrNull(inDTO.getAliasDestino());
        String titular = inDTO.getTitular();
        String alias = StringUtils.EMPTY;
        String cbu = StringUtils.EMPTY;
        String tipoDestino = "Cuenta";
    
        String cuentaDestino = tieneAlias ? alias : !cbu.isEmpty() ? cbu : inDTO.getNroCuentaDestino();
        String titularDestino = !titular.isEmpty() ? ISBANStringUtils.normalizarCaraceteres(titular) : "No disponible";
        
        listInfo.add(this.crearCampoRequeridoWS("Tipo de destino", Integer.parseInt("2964"), tipoDestino));
        listInfo.add(this.crearCampoRequeridoWS("Cuenta de destino", Integer.parseInt("1133"), ISBANStringUtils.normalizarCaraceteres(cuentaDestino)));
        listInfo.add(this.crearCampoRequeridoWS("Titular de destino", Integer.parseInt("2965"), titularDestino));
        
        infoRequerida.getInfoRequeridaWS().addAll(listInfo);
        return infoRequerida;
    }
    
    private InfoRequeridaWS crearCampoRequeridoWS(String descripcion, int codigo, String valor) {
        InfoRequeridaWS info = new InfoRequeridaWS();
        info.setDescripcion(descripcion);
        info.setCodigo(codigo);
        info.setValor(new ArrayOf324931821440052060NillableString());
        info.getValor().getString().add(valor);
        return info;
    }
    
    private String formatearImporte(String importe) {
        return importe.indexOf('.') == -1 ? importe.concat(".00") : importe;
    }
    
    private AumentoLimiteTransferenciaInDTO obtenerInDto(boolean conError) {
        AumentoLimiteTransferenciaInDTO inDto = new AumentoLimiteTransferenciaInDTO();
        inDto.setBanco(BancoEnum.SANTANDER_RIO.getDescripcion());
        Cliente cliente = new Cliente();
        cliente.setNup("12312312");
        cliente.setApellido2("Apellido2");
        cliente.setApellido1("Apellido1");
        cliente.setNombre("Nombre");
        Cuenta cuenta = new Cuenta();
        cuenta.setNroCuentaProducto("0000000003632381");
        cuenta.setNroSucursal("2345");
        cuenta.setContratoAltair("201-363238/1");
        cuenta.setTipoCuenta("Cuenta Única");
        cuenta.setMonedaAltair(conError ? "CAMPO DE MONEDA ERRONEO" : "ARS");
        cuenta.setProductoAltair("201-363238/1");
        cuenta.setSubproductoAltair("201-363238/1");
        List<Cuenta> cuentas = new ArrayList<Cuenta>();
        cuentas.add(cuenta);
        cliente.setCuentas(cuentas);
        inDto.setNroCuenta("201-363238/1");
        inDto.setCliente(cliente);
        inDto.setFechaEjecucion("12/12/2017");
        inDto.setRioRio(false);
        inDto.setCuentaPropia(true);
        inDto.setImporte("400000");
        inDto.setMoneda("peso");
        inDto.setTipoCuentaDescripcion("Cuenta Única");
        inDto.setTitular("Nombre Apellido1 Apellido2");
        inDto.setNroCuentaDestino("400-872651/1");
        
        return inDto;
    }
}
