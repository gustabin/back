/**
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.sucursales.bo.impl;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.clientes.entities.ResumenCliente;
import ar.com.santanderrio.obp.base.security.credential.Credential;
import ar.com.santanderrio.obp.base.security.credential.CredentialSecurityFactory;
import ar.com.santanderrio.obp.base.types.ExternalPropertyType;
import ar.com.santanderrio.obp.servicios.comun.PidFileUtil;
import ar.com.santanderrio.obp.servicios.comun.sucursales.bo.SucursalBO;
import ar.com.santanderrio.obp.servicios.comun.sucursales.dao.ConsultarSucursalesDAO;
import ar.com.santanderrio.obp.servicios.comun.sucursales.dao.SucursalDAO;
import ar.com.santanderrio.obp.servicios.comun.sucursales.dao.impl.SucursalEntity;
import ar.com.santanderrio.obp.servicios.comun.sucursales.dao.impl.SucursalOutEntity;
import ar.com.santanderrio.obp.servicios.comun.sucursales.entities.Entidad;
import ar.com.santanderrio.obp.servicios.comun.sucursales.entities.Sucursal;
import ar.com.santanderrio.obp.servicios.comun.sucursales.entities.Sucursales;

/**
 * @author sergio.e.goldentair
 *
 */
/**
 * @author sergio.e.goldentair
 *
 */
@Component("sucursalBO")
public class SucursalBOImpl implements SucursalBO {
    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(SucursalBOImpl.class);

    /** The id. */
    @Value("${DB.RACFINICIAL.ID}")
    private String id;

    /** Nup de prueba. */
    @Value("${MONITOR_NUP:02616647}")
    private String monitorNup;
    /** Documento de prueba. */
    @Value("${MONITOR_DOCUMENTO:12111211}")
    private String monitorDocumento;

    /** The credential security factory. */
    @Autowired
    private CredentialSecurityFactory credentialSecurityFactory;

    /**
     * 
     */
    @Autowired
    private SucursalDAO sucursalDAO;

    /**
     * 
     */
    @Autowired
    private ConsultarSucursalesDAO consultarSucursalesDAO;

    /** The pidfile path. */
    @Autowired
    private PidFileUtil pidFileUtil;

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.comun.sucursales.bo.SucursalBO#
     * actualizarSucursales()
     */
    @Override
    public void actualizarSucursales() {
        File pidFile = pidFileUtil.getFileForPid(ExternalPropertyType.FILE_SUCURSALES);
        boolean fileCreado = false;
        if (!pidFileUtil.isPidActivo(pidFile)) {
            try {
                fileCreado = pidFileUtil.crearPid(pidFile);
                LOGGER.info("Generar info de cliente inicial para poder consultar iatx sin session.");
                ResumenCliente resumenCliente = obtenerResumenCliente();
                LOGGER.info("Obtener las sucursales desde iatx.");
                SucursalOutEntity sucursalOutEntity = sucursalDAO.cnsSucursales(resumenCliente);
                Sucursales sucursales = armarSucursalesModel(sucursalOutEntity);
                consultarSucursalesDAO.actualizarSucursales(sucursales);
            } catch (Exception e) {
                LOGGER.error("No se actualizo el archivo {}", ExternalPropertyType.FILE_SUCURSALES.getName(), e);
            } finally {
                pidFileUtil.borrarPid(pidFile, fileCreado);
            }
        }

    }

    /**
     * Transformar lo recibido en iatx a la estructura que maneja el xml que figura
     * en el filesystem.
     * 
     * @param sucursalOutEntity
     * @return
     */
    private Sucursales armarSucursalesModel(SucursalOutEntity sucursalOutEntity) {
        Sucursales sucursales = new Sucursales();

        List<Entidad> sucursalesList = new ArrayList<Entidad>();
        List<Sucursal> oficinas = new ArrayList<Sucursal>();
        Entidad entidad = new Entidad();
        entidad.setIdEntidad("0072");
        for (SucursalEntity sucursalEntity : sucursalOutEntity.getSucursales()) {
            if ("F".equals(sucursalEntity.getTipoOficina()) || "N".equals(sucursalEntity.getTipoOficina())
                    || "S".equals(sucursalEntity.getTipoOficina())) {
                Sucursal sucursal = new Sucursal();
                sucursal.setDescripcion(StringUtils.trim(sucursalEntity.getDescCompleta()));
                sucursal.setDireccion(StringUtils.trim(sucursalEntity.getDireccion()));
                sucursal.setTipoDeOficina(sucursalEntity.getTipoOficina());
                sucursal.setIdSucursal(sucursalEntity.getCodigoOficina());
                oficinas.add(sucursal);
            }
        }
        entidad.setSucursales(oficinas);
        sucursalesList.add(entidad);
        sucursales.setSucursales(sucursalesList);
        return sucursales;
    }

    /**
     * Generar un resumenCliente para poder setear los campos en la cabecera del
     * request al host.
     * 
     * @return
     * @throws SQLException
     */
    private ResumenCliente obtenerResumenCliente() throws SQLException {
        Credential credential = credentialSecurityFactory.getCredentialById(this.id);
        ResumenCliente resumenCliente = new ResumenCliente();
        resumenCliente.setUsuarioRacf(credential.getUsuario());
        resumenCliente.setPasswordRacf(credential.getPassword());
        resumenCliente.setNup(monitorNup);
        resumenCliente.setDni(monitorDocumento);
        return resumenCliente;
    }

}
