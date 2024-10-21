package ar.com.santanderrio.obp.servicios.home.web.manager.impl;

import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.http.client.utils.URIBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.security.credential.Credential;
import ar.com.santanderrio.obp.base.security.credential.CredentialSecurityFactory;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.AccesosInversiones;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.home.entitites.ItemEntity;
import ar.com.santanderrio.obp.servicios.home.entitites.MenuEntity;
import ar.com.santanderrio.obp.servicios.home.web.manager.MenuHomeManager;
import ar.com.santanderrio.obp.servicios.home.web.view.AccionController;
import ar.com.santanderrio.obp.servicios.home.web.view.MicrofrontView;
import ar.com.santanderrio.obp.servicios.home.web.view.SeccionMicrofrontView;
import ar.com.santanderrio.obp.servicios.home.web.view.SeccionView;
import ar.com.santanderrio.obp.servicios.inversiones.comun.entity.Menu;
import ar.com.santanderrio.obp.servicios.modulos.dao.ModuloPermisoDAO;
import ar.com.santanderrio.obp.servicios.modulos.dominio.ModuloPermiso;
import ar.com.santanderrio.obp.servicios.oauth2.connector.OAuthV2Connector;
import ar.com.santanderrio.obp.servicios.recuperaciones.dao.impl.RefinancingDAOImpl;

@Component
public class MenuHomeManagerImpl implements MenuHomeManager{
	
	/** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(MenuHomeManagerImpl.class);
	
	@Autowired
	private ModuloPermisoDAO moduloPermisoDAO;
	
	@Autowired
    protected AdministradorPermisos administradorPermisos;
	
	@Autowired
	private OAuthV2Connector oAuthV2Connector;
	
	@Autowired
    private CredentialSecurityFactory credentialSecurityFactory;
	
	@Autowired
	private SesionCliente sesionCliente;
	
	@Autowired
	private RefinancingDAOImpl refinancingDao;
	
	@Autowired
	private SesionParametros sesionParametros;
	
	@Autowired
    @Qualifier("restApiMenuTemplate")
    private RestTemplate restTemplate;

	@Value("${SERVICIO.API.MENU.URL}")
    private String apiMenuUrl;
	
	@Value("${SERVICIO.API.MENU.PATH}")
	String path;
	
	@Value("${SERVICIO.API.MENU.PRINCIPAL}")
    private String menuPrincipal;
	
	@Value("${SERVICIO.API.MENU.CHANNEL}")
    private String canal;
	
	@Value("${SERVICIO.API.MENU.SCOPE}")
	private String scope;
	
    @Value("${SERVICIO.API.MENU.SEC.ID}")
    private String apiMenuSecId;

	@Value("${SERVICIO.API.MENU.OAUTH.URL}")
	private String apiMenuOauthUrl;
    
  	private static final String AUTHORIZATION_BEARER = "Bearer ";
	private static final String HEADER_AUTHORIZATION = "Authorization";
	private static final String ITEM_FINANCIACION_DEUDA = "ITEM_FINANCIACION_DEUDA";
	private static final String ITEM_ACCESO_INVERSIONES = "ITEM_ACCESO_INVERSIONES";
	
	@Override
	public boolean utilizarApiMenu() {
		Boolean utilizarApiMenu = false;
		AccionController accion = AccionController.HABILITAR_API_MENU;
		try {
            Map<String, ModuloPermiso> modulosPermisos = moduloPermisoDAO.obtenerModulosPermisos();
            ModuloPermiso moduloPermiso = modulosPermisos.get(accion.getDescripcion());
            if (moduloPermiso != null) {
            	utilizarApiMenu = moduloPermiso.tienePermisoDeVisibilidad();
            }
        } catch (DAOException e) {
        	LOGGER.error("No fue posible consultar si el modulo {} tiene permitido la operacion solicitada. ",
        			accion, e);
        }
		return utilizarApiMenu;
	}
	
	
	@Override
	public List<SeccionView> construirMenu(Cliente cliente) {
		List<SeccionView> secciones = new ArrayList<SeccionView>();
		
		try {
			HttpEntity<MultiValueMap<String, String>> request = buildRequest();
			String url = this.buildUrl(cliente);
			ResponseEntity<MenuEntity> resp = restTemplate.exchange(url,
					HttpMethod.GET, request, MenuEntity.class);
			MenuEntity menu = resp.getBody();
			
			if (isServiceOK(resp.getStatusCode())) {
				SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(
						SecurityContextHolder.getContext().getAuthentication().getPrincipal(),
						SecurityContextHolder.getContext().getAuthentication().getCredentials(), new ArrayList<GrantedAuthority>()));
				cliente.setAccesosInversiones(null);
				construirSecciones(menu.getItems(),secciones,cliente);
			}else {
				LOGGER.error("Error llamando a la API de Menu. El codigo de error es: " + resp.getStatusCode().value() + "."
						+ "Parametros: "+canal+"-"+menuPrincipal+"-"+cliente.getNup());
			}
		} catch (Exception e) {
			LOGGER.error("Error llamando a la API de Menu {}",e.getMessage());
		} 
		
		return secciones;
	}
	
	private boolean isServiceOK(HttpStatus statusCode) {
		return HttpStatus.OK.equals(statusCode);
	}

	
	private HttpEntity<MultiValueMap<String, String>> buildRequest() throws DAOException {
		HttpHeaders headers = new HttpHeaders();
        headers.setContentType(org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED);
        headers.setAccept(Arrays.asList(org.springframework.http.MediaType.APPLICATION_JSON));
		headers.set(HEADER_AUTHORIZATION, AUTHORIZATION_BEARER + 
				oAuthV2Connector.getAccessToken(obtenerCredenciales(), scope, apiMenuOauthUrl).getAccessToken());
		return new HttpEntity<MultiValueMap<String, String>>(null, headers);
	}

	
	private String buildUrl(Cliente cliente) throws URISyntaxException {
		return new URIBuilder(apiMenuUrl+path)
				.addParameter("name", menuPrincipal)
				.addParameter("channel", canal)
				.addParameter("customerId", cliente.getNup())
				.build().toString();
	}


	private void construirSecciones(List<ItemEntity> items, List<SeccionView> secciones,Cliente cliente) {
		for (ItemEntity item : items) {	
			
			if (filtrarItem(item)) {
				continue;
			}
			
			SeccionView seccion = null;
			if (item.tieneLinkMicrofront()) {
				seccion = construirSeccionMicroFront(item);
			}else {
				seccion = construirSeccion(item);
			}
			if (item.tieneHijos()) {
				List<SeccionView> seccionesChildren = new ArrayList<SeccionView>();
				construirSecciones(item.getChildren(),seccionesChildren,cliente);
				seccion.setItems(seccionesChildren);
			}
			
			agregarPermisos(seccion.getAccion());
			
			//Logica para algunos items de la seccion de inversiones
			if (item.getName().equalsIgnoreCase(ITEM_ACCESO_INVERSIONES)) {
				setearAccesosInversiones(item,cliente);
			}
			
			secciones.add(seccion);
			
		}
	}
	
	private void setearAccesosInversiones(ItemEntity item, Cliente cliente) {
		if (cliente.getAccesosInversiones() == null) {
			AccesosInversiones accesosInversiones = new AccesosInversiones();
			cliente.setAccesosInversiones(accesosInversiones);
		}
		
		if (cliente.getAccesosInversiones().getAccesos() == null) {
			List<Menu> listaMenu = new ArrayList<Menu>();
			cliente.getAccesosInversiones().setAccesos(listaMenu);
		}
		
		Menu menu = new Menu();
		menu.setTitulo(item.getLabel());
		menu.setLink(item.getAccess().getUrl());
		
		cliente.getAccesosInversiones().getAccesos().add(menu);
	}
	

	private boolean filtrarItem(ItemEntity item) {
		boolean filtrar = false;
		//Identifico si el item de Financiacion de deuda se tiene que eliminar del menu
		if (item.getName().equalsIgnoreCase(ITEM_FINANCIACION_DEUDA) && !mostrarItemFinanciacionDeuda()) {
			filtrar=true;
		}
		return filtrar;
	}


	private void agregarPermisos(String seccion) {
		AccionController accion = AccionController.obtenerEnumPorDescripcion(seccion);
		if (accion != null) {
			administradorPermisos.addNewGrant(accion);
		}
	}


	private SeccionView construirSeccion(ItemEntity item) {
		SeccionView seccion = new SeccionView();
		seccion.setTitulo(item.getLabel());
		if (item.getAccess()!= null) {
			seccion.setAccion(item.getAccess().getUrl());
		}
		return seccion;
		
	}
	
	private SeccionView construirSeccionMicroFront(ItemEntity item) {
		SeccionMicrofrontView seccion = new SeccionMicrofrontView();
		seccion.setTitulo(item.getLabel());
		seccion.setAccion(item.getAccess().getFormId());
		seccion.setMicrofront(new MicrofrontView(item.getAccess().getMicrofrontName(), item.getAccess().getUrl()));
		return seccion;
	}
	
	private Credential obtenerCredenciales() throws DAOException {
		Credential credential;
		try {
			credential = credentialSecurityFactory.getCredentialById(apiMenuSecId);
		} catch (SQLException e) {
			throw new DAOException("Error al obtener credenciales");
		}
		return credential;
	}
	
	private boolean mostrarItemFinanciacionDeuda() {
		synchronized (sesionCliente.getCliente()) {
			if(sesionParametros.isAplicaRefi() == null) {
				boolean aplicaRefi = refinancingDao.aplicaRefinanciacion();
				sesionParametros.setAplicaRefi(aplicaRefi);
				return aplicaRefi;
			}
			else return sesionParametros.isAplicaRefi();
		}
	}



}
