/**
 * 
 */
package ar.com.santanderrio.obp.servicios.obp.comun.sucursales;

import java.io.File;
import java.sql.SQLException;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.clientes.entities.ResumenCliente;
import ar.com.santanderrio.obp.base.comun.FilePath;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.security.credential.Credential;
import ar.com.santanderrio.obp.base.security.credential.CredentialSecurityFactory;
import ar.com.santanderrio.obp.servicios.comun.PidFileUtil;
import ar.com.santanderrio.obp.servicios.comun.sucursales.bo.SucursalBO;
import ar.com.santanderrio.obp.servicios.comun.sucursales.bo.impl.SucursalBOImpl;
import ar.com.santanderrio.obp.servicios.comun.sucursales.dao.ConsultarSucursalesDAO;
import ar.com.santanderrio.obp.servicios.comun.sucursales.dao.SucursalDAO;
import ar.com.santanderrio.obp.servicios.comun.sucursales.dao.impl.SucursalOutEntity;

/**
 * @author sergio.e.goldentair
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class SucursalBOTest {
    
    @InjectMocks
    private SucursalBO sucursalBO = new SucursalBOImpl();

    /** The credential security factory. */
    @Mock
    private CredentialSecurityFactory credentialSecurityFactory;

    @Mock
    private SucursalDAO sucursalDAO;

    @Mock
    private ConsultarSucursalesDAO consultarSucursalesDAO;

    @Mock
    private FilePath filePath;

    @Mock
    /** The pidfile path. */
    private PidFileUtil pidFileUtil;

    
    @Test
    public void escribirPidyLimpiarlo() throws IllegalAccessException, SQLException, DAOException{
        FieldUtils.writeDeclaredField(sucursalBO, "monitorNup", "02616647", true);
        FieldUtils.writeDeclaredField(sucursalBO, "monitorDocumento", "12111211", true);
        
        Credential credential = new Credential();
        credential.setUsuario("XXXX");
        credential.setPassword("YYYYY");
        Mockito.when(credentialSecurityFactory.getCredentialById(Matchers.anyString())).thenReturn(credential);
        SucursalOutEntity sucursalOutEntity = new SucursalOutEntity();
        Mockito.when(sucursalDAO.cnsSucursales(Matchers.any(ResumenCliente.class))).thenReturn(sucursalOutEntity);
        String path = Object.class.getResource("/config").getPath() + File.separatorChar;
        Mockito.when(filePath.getFilePath()).thenReturn(path);
        
        sucursalBO.actualizarSucursales();
        File pidFile = new File(filePath.getFilePath() + path);
        Assert.assertTrue(!pidFile.exists());
    }

}
