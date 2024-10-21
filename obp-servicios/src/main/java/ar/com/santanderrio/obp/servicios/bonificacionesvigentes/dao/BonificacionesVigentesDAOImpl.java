package ar.com.santanderrio.obp.servicios.bonificacionesvigentes.dao;

import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.MediaType;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.http.HttpHeaders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.dao.impl.BaseDatoDAOImpl;
import ar.com.santanderrio.obp.base.security.aop.annotation.TargetSystem;
import ar.com.santanderrio.obp.base.security.credential.Credential;
import ar.com.santanderrio.obp.base.security.credential.CredentialSecurityFactory;
import ar.com.santanderrio.obp.base.security.credential.DataBase;
import ar.com.santanderrio.obp.config.ws.CacheConstants;
import ar.com.santanderrio.obp.servicios.bonificacionesvigentes.dto.AccountDto;
import ar.com.santanderrio.obp.servicios.bonificacionesvigentes.dto.BonificacionDto;
import ar.com.santanderrio.obp.servicios.bonificacionesvigentes.dto.ListPromocionDto;
import ar.com.santanderrio.obp.servicios.bonificacionesvigentes.dto.LoginCommand;
import ar.com.santanderrio.obp.servicios.bonificacionesvigentes.dto.ProductoClienteEntity;
import ar.com.santanderrio.obp.servicios.comun.utils.RestWebClient;
import oracle.jdbc.OracleTypes;

@Repository
@TargetSystem(DataBase.ESTADISTICAS)
public class BonificacionesVigentesDAOImpl extends BaseDatoDAOImpl implements BonificacionesVigentesDAO {

	private static final String NOMBRE_WS_BONIFICACIONES = "BONIFICACIONES.VIGENTES";
	private static final String NOMBRE_WS_TOKEN = "TOKEN.BONIFICACIONES.VIGENTES";
	private static final String PARAMETRO_NUP = "{nup}";
	private static final String PATH_OBTENER_BONIFICACIONES_VIGENTES = "/api/v1/Customers/" + PARAMETRO_NUP + "/account-bonifications";
	private static final String PATH_OBTENER_BONIFICACIONES_VIGENTES_CAJA_SEGURIDAD = "/api/v1/Customers/" + PARAMETRO_NUP + "/security-box-bonifications";
	private static final String IN_CODIGO_PRODUCTO = "p_codigo_producto";
	private static final String IN_CODIGO_SUBPRODUCTO = "p_codigo_subproducto";
	private static final String OUT_PRODUCTO = "p_cursor";
	private static final String OUT_RESULTADO = "p_resultado";
	private static final String OUT_ERROR_TECNICO = "p_err_tecnico";
	private static final String OUT_ERROR_AMIGABLE = "p_err_amigable";
	private static final String SCHEMA_NAME = "hbank";
	private static final String PACKAGE_NAME = "HB_PKG_LISTA_PRODUCTOS_BONIF";
	private static final String PROCEDURE_BUSCAR_PRODUCTO = "prc_buscar_producto";
	private static final String RESULTADO_OK = "0";
	private static final Logger LOGGER = LoggerFactory.getLogger(BonificacionesVigentesDAOImpl.class);
	private static final String PATH_OBTENER_PROMOCIONES = "/api/promotions/" + PARAMETRO_NUP;
	private static final String PATH_OBTENER_BONIFICACIONES = "/api/v1/insurance-bonifications/" + PARAMETRO_NUP;
	
	@Value("${PROMOCIONES.URL}")
	String urlPromocion;
	
	@Value("${BONIFICACIONES.URL}")
	String urlBonificacion;
	
	
	
	@Autowired
    private RestWebClient restWebClient;

	@Value("${BONIFICACIONES.VIGENTES.ID}")
	private String bonificacionesVigentesID;
	
    @Autowired
    private CredentialSecurityFactory credentialSecurityFactory;
    
    @Value("${FECHA.DESDE.PROMOCION}")
    private String fechaDesdePromocion;

	
	@SuppressWarnings("unchecked")
	@Override
	public List<LinkedHashMap<Object, Object>> obtenerBonificaciones(String nup) throws DAOException{

		AccountDto token = obtenerToken();
		
		System.setProperty("https.protocols", "TLSv1,TLSv1.1,TLSv1.2");
		
		String path = PATH_OBTENER_BONIFICACIONES_VIGENTES.replace(PARAMETRO_NUP, nup);
		WebClient client = restWebClient.obtenerClienteRest(NOMBRE_WS_BONIFICACIONES);
		String tokenBearer = "Bearer " + token.getToken();
		List<LinkedHashMap<Object, Object>> response = client.type(MediaType.APPLICATION_JSON + ";charset=UTF-8")
		.accept(MediaType.APPLICATION_JSON)
		.header(HttpHeaders.AUTHORIZATION, tokenBearer)
		.path(path)
		.query("accountMinCreationDate", "2021-03-08")
		.get(List.class);
		
		int status = client.getResponse().getStatus();
		if(status == HttpStatus.SC_OK) {
			return response;
		} else if(status == HttpStatus.SC_NOT_FOUND) {
			LOGGER.info("No se han encontrado bonificaciones para nup: {}", nup);
			throw new DAOException("Error en el servicio " + path + " - No se han encontrado bonificaciones para nup: " + nup);
		} else {
			LOGGER.error("Error en el servicio {}", path);
			throw new DAOException("Error en el servicio " + path);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<LinkedHashMap<Object, Object>> obtenerBonificacionesCajaSeguridad(String nup) throws DAOException{

		AccountDto token = obtenerToken();
		
		System.setProperty("https.protocols", "TLSv1,TLSv1.1,TLSv1.2");
		
		String path = PATH_OBTENER_BONIFICACIONES_VIGENTES_CAJA_SEGURIDAD.replace(PARAMETRO_NUP, nup);
		WebClient client = restWebClient.obtenerClienteRest(NOMBRE_WS_BONIFICACIONES);
		String tokenBearer = "Bearer " + token.getToken();
		List<LinkedHashMap<Object, Object>> response = new ArrayList<LinkedHashMap<Object,Object>>();
		try {
			response = client.type(MediaType.APPLICATION_JSON + ";charset=UTF-8")
			.accept(MediaType.APPLICATION_JSON)
			.header(HttpHeaders.AUTHORIZATION, tokenBearer)
			.path(path)
			.get(List.class);
		} catch (BadRequestException e) {
			return response;
		}
		
		int status = client.getResponse().getStatus();
		if(status == HttpStatus.SC_OK) {
			return response;
		} else if(status == HttpStatus.SC_NOT_FOUND) {
			LOGGER.info("No se han encontrado bonificaciones para nup: {}", nup);
			throw new DAOException("Error en el servicio " + path + " - No se han encontrado bonificaciones para nup: " + nup);
		} else {
			LOGGER.error("Error en el servicio {}", path);
			throw new DAOException("Error en el servicio " + path);
		}
	}
	
	public AccountDto obtenerToken() throws DAOException {
	
		Credential credential = getCredentials();
		
		LoginCommand datosIniciales = new LoginCommand();
		datosIniciales.setUserId(credential.getUsuario());
		datosIniciales.setPassword(credential.getPassword());
		
		WebClient client = restWebClient.obtenerClienteRest(NOMBRE_WS_TOKEN);
		client.accept(MediaType.APPLICATION_JSON);
		client.type(MediaType.APPLICATION_JSON + ";charset=UTF-8").accept(MediaType.APPLICATION_JSON);
		
		return client.post(datosIniciales, AccountDto.class);

	}
	
	public Credential getCredentials() {
		Credential credential = new Credential();
		try{
			credential = credentialSecurityFactory.getCredentialById(this.bonificacionesVigentesID);
		}catch (SQLException e) {
			LOGGER.error("No se pudo acceder a la base de seguridad");
		}
		return credential;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public ProductoClienteEntity obtenerProductoBonificado(String codigoProducto, String codigoSubproducto) throws DAOException {
		
		List<ProductoClienteEntity> productoBonificado = new ArrayList<ProductoClienteEntity>();
 
		try {
            List<SqlParameter> parametros = new ArrayList<SqlParameter>();
            parametros.add(new SqlParameter(IN_CODIGO_PRODUCTO, OracleTypes.VARCHAR));
            parametros.add(new SqlParameter(IN_CODIGO_SUBPRODUCTO, OracleTypes.VARCHAR));
            
            parametros.add(new SqlOutParameter(OUT_PRODUCTO, OracleTypes.CURSOR,
                    new BeanPropertyRowMapper<ProductoClienteEntity>(ProductoClienteEntity.class)));

    		parametros.add(new SqlOutParameter(OUT_RESULTADO, Types.VARCHAR));
    		parametros.add(new SqlOutParameter(OUT_ERROR_TECNICO, Types.VARCHAR));
    		parametros.add(new SqlOutParameter(OUT_ERROR_AMIGABLE, Types.VARCHAR));

            SqlParameterSource in = new MapSqlParameterSource().addValue(IN_CODIGO_PRODUCTO, codigoProducto).addValue(IN_CODIGO_SUBPRODUCTO, codigoSubproducto);
            
            Map<String, Object> respuesta = consultar(SCHEMA_NAME, PACKAGE_NAME, PROCEDURE_BUSCAR_PRODUCTO, in,
					parametros.toArray(new SqlParameter[parametros.size()]));

            
            String codRetorno = (String) respuesta.get(OUT_RESULTADO);

            if (RESULTADO_OK.equals(codRetorno)) {     	
            	productoBonificado = (ArrayList<ProductoClienteEntity>) respuesta.get(OUT_PRODUCTO);
				if (!productoBonificado.isEmpty()) {
					return productoBonificado.get(0);
				} else {
					throw new DAOException("no se encuentra el producto");
				}
            } else {
            	throw new DAOException("no se encuentra el producto");
            }
            
        } catch (SQLException e) {
            throw new DAOException(e);
        } catch (UncategorizedSQLException e) {
            throw new DAOException(e);
        } catch (InvalidDataAccessApiUsageException e) {
            throw new DAOException(e);
        }
		
	}

	@Override
	public ListPromocionDto obtenerPromociones(String nup) throws DAOException {
		String path = urlPromocion + PATH_OBTENER_PROMOCIONES.replace(PARAMETRO_NUP, nup);
		return restWebClient.obtenerPromociones(path);
	
	}

	@Cacheable(value = CacheConstants.Names.CACHE_BONIFICACIONES_SEGUROS, key = "#nup")
	@Override
	public List<BonificacionDto> obtenerBonificacionesSeguros(String nup) throws DAOException {
		String path = urlBonificacion + PATH_OBTENER_BONIFICACIONES.replace(PARAMETRO_NUP, nup)+"?date_from=" + fechaDesdePromocion;
		return restWebClient.obtenerBonificacionesSeguros(path);
	}

}
