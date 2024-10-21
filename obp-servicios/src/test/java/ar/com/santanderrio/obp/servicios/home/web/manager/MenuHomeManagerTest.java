package ar.com.santanderrio.obp.servicios.home.web.manager;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.HttpStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.client.RestTemplate;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.security.credential.Credential;
import ar.com.santanderrio.obp.base.security.credential.CredentialSecurityFactory;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.home.entitites.AccessEntity;
import ar.com.santanderrio.obp.servicios.home.entitites.ItemEntity;
import ar.com.santanderrio.obp.servicios.home.entitites.MenuEntity;
import ar.com.santanderrio.obp.servicios.home.web.manager.impl.AdministradorPermisos;
import ar.com.santanderrio.obp.servicios.home.web.manager.impl.MenuHomeManagerImpl;
import ar.com.santanderrio.obp.servicios.home.web.view.AccionController;
import ar.com.santanderrio.obp.servicios.modulos.dao.ModuloPermisoDAO;
import ar.com.santanderrio.obp.servicios.modulos.dominio.ModuloEstado;
import ar.com.santanderrio.obp.servicios.modulos.dominio.ModuloPermiso;
import ar.com.santanderrio.obp.servicios.oauth2.connector.OAuthV2Connector;
import ar.com.santanderrio.obp.servicios.oauth2.token.OAuth2AccessToken;
import ar.com.santanderrio.obp.servicios.recuperaciones.dao.impl.RefinancingDAOImpl;

@RunWith(MockitoJUnitRunner.class)
public class MenuHomeManagerTest {
	
	 @InjectMocks
	 private MenuHomeManager menuHomeManager = new MenuHomeManagerImpl();
	 
	 @Mock
	 private ModuloPermisoDAO moduloPermisoDAO;
	 
	 @Mock
	 @Qualifier("restLoginTemplate")
	 private RestTemplate restTemplate;
	 
	 @Mock 
	 private AdministradorPermisos administradorPermisos;
	 
	 
	 @Mock
	 private OAuthV2Connector oAuthV2Connector;

	 @Mock
	 private CredentialSecurityFactory credentialSecurityFactory;
	 
	 @Mock
	 private SesionCliente sesionCliente;
	 
	 @Mock
	 private SesionParametros sesionParametros;
	 
	 @Mock
	 private RefinancingDAOImpl refinancingDao;
	 
	 private static Credential credential;
	 private static OAuth2AccessToken accessToken;
	
	 
	 static {
			credential = new Credential();
			credential.setUsuario("1234567890");
			credential.setPassword("1234567890123456");

			accessToken = new OAuth2AccessToken();
			accessToken.setAccessToken("dummy-token");

		}
	 
	 
	 @Test
	 public void utilizarApiMenuTrueTest() throws DAOException {
		 ModuloPermiso permiso = new ModuloPermiso();
		 permiso.setModuloEstado(ModuloEstado.MOSTRAR);
		 Map<String, ModuloPermiso> modulosPermisos = new HashMap<String, ModuloPermiso>();
		 modulosPermisos.put(AccionController.HABILITAR_API_MENU.getDescripcion(), permiso);
		 when(moduloPermisoDAO.obtenerModulosPermisos()).thenReturn(modulosPermisos);
         assertEquals(Boolean.TRUE, menuHomeManager.utilizarApiMenu());
	 }
	 
	 @Test
	 public void utilizarApiMenuFalseTest() throws DAOException {
		 ModuloPermiso permiso = new ModuloPermiso();
		 permiso.setModuloEstado(ModuloEstado.OCULTAR);
		 Map<String, ModuloPermiso> modulosPermisos = new HashMap<String, ModuloPermiso>();
		 modulosPermisos.put(AccionController.HABILITAR_API_MENU.getDescripcion(), permiso);
		 when(moduloPermisoDAO.obtenerModulosPermisos()).thenReturn(modulosPermisos);
         assertEquals(Boolean.FALSE, menuHomeManager.utilizarApiMenu());
	 }
	 
	 @Test
	 public void utilizarApiMenuFalse2Test() throws DAOException {
		 ModuloPermiso permiso = new ModuloPermiso();
		 permiso.setModuloEstado(ModuloEstado.OCULTAR);
		 Map<String, ModuloPermiso> modulosPermisos = new HashMap<String, ModuloPermiso>();
		 modulosPermisos.put("unModulo", permiso);
		 when(moduloPermisoDAO.obtenerModulosPermisos()).thenReturn(modulosPermisos);
         assertEquals(Boolean.FALSE, menuHomeManager.utilizarApiMenu());
	 }
	 
	 
	 @Test
	 public void utilizarApiMenuExceptionTest() throws DAOException {
		 ModuloPermiso permiso = new ModuloPermiso();
		 permiso.setModuloEstado(ModuloEstado.OCULTAR);
		 Map<String, ModuloPermiso> modulosPermisos = new HashMap<String, ModuloPermiso>();
		 modulosPermisos.put(AccionController.HABILITAR_API_MENU.getDescripcion(), permiso);
		 when(moduloPermisoDAO.obtenerModulosPermisos()).thenThrow(DAOException.class);
         assertEquals(Boolean.FALSE, menuHomeManager.utilizarApiMenu());
	 }
	 
	 @Test
	 public void construirMenuTestOK() throws Exception {
		 Cliente cliente = new Cliente();
		 crearLLamadaServicio(HttpStatus.SC_OK);
		 when(sesionCliente.getCliente()).thenReturn(cliente);
		 when(sesionParametros.isAplicaRefi()).thenReturn(true);
		 assertEquals(Boolean.FALSE,menuHomeManager.construirMenu(cliente).isEmpty());
	 }
	 

	 @Test
	 public void construirMenuTest2OK() throws Exception {
		 Cliente cliente = new Cliente();
		 crearLLamadaServicio(HttpStatus.SC_OK);
		 when(sesionCliente.getCliente()).thenReturn(cliente);
		 when(sesionParametros.isAplicaRefi()).thenReturn(null);
		 when(refinancingDao.aplicaRefinanciacion()).thenReturn(true);
		 assertEquals(Boolean.FALSE,menuHomeManager.construirMenu(cliente).isEmpty());
	 }
	 
	 @Test
	 public void construirMenuTest3OK() throws Exception {
		 Cliente cliente = new Cliente();
		 crearLLamadaServicio(HttpStatus.SC_OK);
		 when(sesionCliente.getCliente()).thenReturn(cliente);
		 when(sesionParametros.isAplicaRefi()).thenReturn(false);
		 assertEquals(Boolean.FALSE,menuHomeManager.construirMenu(cliente).isEmpty());
	 }
	 
	 @Test
	 public void construirMenuTestNotOK() throws Exception {
		 Cliente cliente = new Cliente();
		 crearLLamadaServicio(HttpStatus.SC_BAD_REQUEST);
		 assertEquals(Boolean.TRUE,menuHomeManager.construirMenu(cliente).isEmpty());
	 }
	 
	 @Test
	 public void construirMenuTestException() throws Exception {
		 Cliente cliente = new Cliente();
		 crearLLamadaServicioException();
		 assertEquals(Boolean.TRUE,menuHomeManager.construirMenu(cliente).isEmpty());
	 }
	 
	 @Test
	 public void construirMenuCredentialException() throws Exception {
		 Cliente cliente = new Cliente();
		 Mockito.when(credentialSecurityFactory.getCredentialById(Matchers.anyString())).thenThrow(SQLException.class);
		 assertEquals(Boolean.TRUE,menuHomeManager.construirMenu(cliente).isEmpty());
	 }
	 
	 
	private void crearLLamadaServicioException() {
		 Mockito.when(restTemplate.exchange(Matchers.anyString(), Matchers.any(HttpMethod.class),
					Matchers.any(HttpEntity.class), Mockito.eq(MenuEntity.class))).thenThrow(Exception.class);
	}

	private void crearLLamadaServicio(int codigoEsperado) throws Exception {
		Mockito.when(restTemplate.exchange(Matchers.anyString(), Matchers.any(HttpMethod.class),
				Matchers.any(HttpEntity.class), Mockito.eq(MenuEntity.class))).thenReturn(ResponseEntity.status(codigoEsperado).body(crearMockMenu()));
		Mockito.when(credentialSecurityFactory.getCredentialById(Matchers.anyString())).thenReturn(credential);
		Mockito.when(oAuthV2Connector.getAccessToken(Matchers.any(Credential.class), Matchers.anyString(), Matchers.anyString())).thenReturn(accessToken);
		
		Authentication authentication = Mockito.mock(Authentication.class);
	    SecurityContext securityContext = Mockito.mock(SecurityContext.class);
	    Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
	    SecurityContextHolder.setContext(securityContext);
	}
	 
	 

	private MenuEntity crearMockMenu() {
				 
		 List<ItemEntity> itemsEntitys = new ArrayList<ItemEntity>();
		 
		 ItemEntity seccionConsulta = crearItemEntity("Consultas","");
		 ItemEntity itemCuentas = crearItemEntity("Cuentas","irInicioCuentas");
		 ItemEntity itemTarjeta = crearItemEntity("Tarjetas","irInicioTarjetas");
		 ItemEntity itemNoValido = crearItemEntity("ItemMock","irItemMock");
		 ItemEntity itemFinanciacionDeuda = crearItemEntity("ITEM_FINANCIACION_DEUDA","ITEM_FINANCIACION_DEUDA");
		 itemTarjeta.setChildren(null);
		 itemTarjeta.getAccess().setMicrofrontName("");
		 seccionConsulta.getChildren().add(itemCuentas);
		 seccionConsulta.getChildren().add(itemTarjeta);
		 seccionConsulta.getChildren().add(itemNoValido);
		 seccionConsulta.getChildren().add(itemFinanciacionDeuda);
		 
		 ItemEntity seccionTransacciones = crearItemEntity("Transacciones","irTransaccion");
		 ItemEntity itemTransferencias = crearItemEntity("Transferencias","irInicioTransferencias");
		 ItemEntity itemExtraccion = crearItemEntity("Extracción en Santander Express","microfront-ponsa-appointments","ponsa-appointments","https://obp-static-rules-dev-static-rules.apps.ocp.ar.bsch/");
		 seccionTransacciones.getChildren().add(itemTransferencias);
		 seccionTransacciones.getChildren().add(itemExtraccion);
		 
		 
		 ItemEntity seccionInversiones = crearItemEntity("Inversiones","irInversiones");
		 ItemEntity itemPlazosFijos = crearItemEntity("Plazos Fijos","irPlazosFijos");
		 ItemEntity itemMenuDinamico = crearItemEntity("ITEM_ACCESO_INVERSIONES","gotoMenuDinamico");
		 ItemEntity itemMenuDinamico2 = crearItemEntity("ITEM_ACCESO_INVERSIONES","gotoMenuDinamico2");
		 seccionInversiones.getChildren().add(itemPlazosFijos);
		 seccionInversiones.getChildren().add(itemMenuDinamico);
		 seccionInversiones.getChildren().add(itemMenuDinamico2);
		 
		 
		 itemsEntitys.add(seccionConsulta);
		 itemsEntitys.add(seccionTransacciones);
		 itemsEntitys.add(seccionInversiones);
		 
		 MenuEntity menu = new MenuEntity();
		 menu.setName("principal");
		 menu.setProfile("ACCOUNTS_AND_CARDS");
		 menu.setItems(itemsEntitys);
		 return menu;
	}
	 
	 
	 private ItemEntity crearItemEntity (String titulo, String accion){
		return this.crearItemEntity(titulo,accion,"","");
	 }
	 
	 private ItemEntity crearItemEntity (String titulo, String accion, String mfName, String mfUrl){
		 ItemEntity item = new ItemEntity();
		 AccessEntity access = new AccessEntity();
		 item.setLabel(titulo);
		 item.setName(titulo);
		 if (!accion.isEmpty() || !mfName.isEmpty() || !mfUrl.isEmpty()) {
			 if (!mfName.isEmpty()) {
				 access.setMicrofrontName(mfName);
				 access.setUrl(mfUrl);
				 access.setFormId(accion);
			 }else {
				 if (accion !=null) {
					 access.setUrl(accion);
				 }
			 }
			 item.setAccess(access);
		 }
		 return item;
	 }
}
