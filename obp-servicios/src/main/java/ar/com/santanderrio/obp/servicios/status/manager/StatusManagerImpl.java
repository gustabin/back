/**
 * 
 */
package ar.com.santanderrio.obp.servicios.status.manager;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.base.security.sign.Sign;
import ar.com.santanderrio.obp.servicios.comprobantes.dao.SubEmpresasDAO;
import ar.com.santanderrio.obp.servicios.comun.legal.dao.LegalDAO;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.modulos.dao.ModuloPermisoDAO;
import ar.com.santanderrio.obp.servicios.modulos.dominio.ModuloPermiso;
import ar.com.santanderrio.obp.servicios.monitoreo.manager.MonitoreoManager;
import ar.com.santanderrio.obp.servicios.simuladorprestamo.dao.DestinoPrestamoDAO;
import ar.com.santanderrio.obp.servicios.simuladorprestamo.entity.DestinoPrestamo;
import ar.com.santanderrio.obp.servicios.status.view.StatusView;
import ar.com.santanderrio.obp.servicios.tarjetas.util.TarjetaUtils;

/**
 * The Class StatusManagerImpl.
 *
 * @author sergio.e.goldentair
 */
/**
 * @author sergio.e.goldentair
 *
 */
/**
 * @author sergio.e.goldentair
 *
 */
@Component("statusManager")
public class StatusManagerImpl implements StatusManager {
    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(StatusManagerImpl.class);
    /** elemento con la fecha en que se genero el empaquetado. */
    private static final String BUILD_TIME = "Build-Time";
    /** Accion correcta. */
    private static final String OK = "OK";
    /** Accion incorrecta. */
    private static final String ERROR = "ERROR";
    /** Firma validadora. */
    private static final String FIRMA_END_OK = "-----END PKCS7-----\n";

    /** The respuesta factory. */
    @Autowired
    private RespuestaFactory respuestaFactory;

    /** The app encoding. */
    @Value("${APP.ENCODING}")
    private String appEncoding;

    /** generador de firmas digitales. */
    @Autowired
    private Sign sign;

    /** The environment. */
    @Autowired
    private Environment environment;

    /** The legal DAO. */
    @Autowired
    private LegalDAO legalDAO;

    /** The destino prestamo DAO. */
    @Autowired
    private DestinoPrestamoDAO destinoPrestamoDAO;

    /** The ModuloPermiso DAO. */
    @Autowired
    private ModuloPermisoDAO moduloPermisoDAO;

    /** The SubEmpresas DAO. */
    @Autowired
    private SubEmpresasDAO subEmpresasDAO;
    
    /** The monitoreo manager. */
    @Autowired
    private MonitoreoManager monitoreoManager;

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.status.manager.StatusManager#getStatus(
     * )
     */
    @Override
    public Respuesta<StatusView> getStatus() {
        StatusView view = getManifestInfo();
        view.setServidor(getServerName());
        view.setBc(getBCEnable());
        view.setBaseDatos(getBaseEnable());
        view.setIatx(getIatxEnable());
        view.setProps(getProps());
        view.setLegales(getLegales());
        view.setDestinosPrestamo(getDestinosPrestamo());
        view.setMapModuloPermiso(getModulosPermisos());
        view.setPesSubempresas(getPesSubempresas());
        return respuestaFactory.crearRespuestaOk(StatusView.class, view);
    }

    /**
	 * Ver si iatx esta disponible.
	 *
	 * @return the iatx enable
	 */
    private String getIatxEnable() {
        if (monitoreoManager.validarIatx()) {
            return OK;
        }
        return ERROR;
    }

    /**
	 * Ver si la base esta levantada y responde a un select from dual.
	 *
	 * @return the base enable
	 */
    private String getBaseEnable() {
        if (monitoreoManager.validarBase()) {
            return OK;
        }
        return ERROR;
    }

    /**
     * Obtener los modulos permisos que utiliza la app.
     * 
     * @return Map<String, ModuloPermiso>
     */
    private Map<String, ModuloPermiso> getModulosPermisos() {
        try {
            return moduloPermisoDAO.obtenerModulosPermisos();
        } catch (DAOException e) {
            LOGGER.error("No se puede mostrar los Modulos Excluidos por un error en el DAO.", e);
        }
        return null;
    }

    /**
     * Obtener los legales para poder validar que esten los correctos.
     * 
     * @return legalesMap
     */
    private Map<String, String> getLegales() {
        try {
            return legalDAO.obtenerLegales();
        } catch (DAOException e) {
            LOGGER.error("No se puede mostrar los legales por un error en el DAO.", e);
        }
        return null;
    }

    /**
     * Obtener el listado de destinos permitidos.
     * 
     * @return List DestinoPrestamo
     */
    private List<DestinoPrestamo> getDestinosPrestamo() {
        try {
            return destinoPrestamoDAO.obtener();
        } catch (DAOException e) {
            LOGGER.error("No se puede mostrar los motivos de destino de prestamo por un error en el DAO.", e);
        }
        return null;
    }

    /**
     * Obtener el listado de propiedades que esta cargando el env utilizado por
     * la app.
     * 
     * @return mapa de propiedades
     */
    private Map<String, Object> getProps() {
        Map<String, Object> map = new HashMap<String, Object>();
        for (Iterator<PropertySource<?>> it = ((AbstractEnvironment) environment).getPropertySources().iterator(); it
                .hasNext();) {
            PropertySource<?> propertySource = (PropertySource<?>) it.next();
            if (propertySource instanceof MapPropertySource && propertySource.getName().contains(".properties")) {
                map.putAll(((MapPropertySource) propertySource).getSource());
            }
        }
        return map;
    }

    /**
     * Invocar a BouncyCastle para validar que este bien cargado el provider y
     * pueda generar la firma digital.
     * 
     * @return OK o ERROR
     */
    private String getBCEnable() {
        try {
            String firma = sign.buildPemSignature("texto de prueba".getBytes(appEncoding),
                    TarjetaUtils.KEY_STORE_VISAAMEX, true);
            if (StringUtils.isNotEmpty(firma) && firma.endsWith(FIRMA_END_OK)) {
                return OK;
            }
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("Error al generar firma de test, no soporta el encoding seleccionado {}.", appEncoding, e);
            return ERROR;
        } catch (DAOException e) {
            LOGGER.error("Error al generar firma de test.", e);
            return ERROR;
        }
        return ERROR;
    }

    /**
     * Obtener el nombre del host donde esta corriendo la app.
     * 
     * @return host name
     */
    private String getServerName() {
        String name = null;
        try {
            name = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            LOGGER.error("Error al obtener informacion del host.", e);
        }
        return name;
    }

    /**
     * Obtener info del manifest del modulo hbservicios.war.
     * 
     * @return StatusView con version y fecha de la distribucion
     */
    private StatusView getManifestInfo() {
        StatusView view = new StatusView();
        try {
            Enumeration<URL> manifestEnum = this.getClass().getClassLoader().getResources(JarFile.MANIFEST_NAME);
            while (manifestEnum.hasMoreElements()) {
                URL url = (URL) manifestEnum.nextElement();
                if (url.toString().contains("war/" + JarFile.MANIFEST_NAME)) {
                    InputStream is = url.openStream();
                    Manifest manifest = new Manifest(is);
                    Attributes attr = manifest.getMainAttributes();
                    view.setVersionEnpaquetado(attr.getValue(Attributes.Name.IMPLEMENTATION_VERSION));
                    view.setFechaDistribucion(attr.getValue(BUILD_TIME));
                }
            }
        } catch (IOException e) {
            LOGGER.error("No es posible obtener la informacion del manifest de la aplicacion.", e);
        }
        return view;
    }

    /**
     * Indica si el ExternalPropertyType.FILE_PES_SUBEMPRESAS cargo
     * correctamente.
     *
     * @return the pes subempresas
     */
    private String getPesSubempresas() {
        return subEmpresasDAO.cargoPesSubempresasFile() ? OK : ERROR;
    }

}
