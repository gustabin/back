package ar.com.santanderrio.obp.servicios.cuentas.bo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;

import javax.ws.rs.ProcessingException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.base.security.credential.Credential;
import ar.com.santanderrio.obp.base.security.credential.CredentialSecurityFactory;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ondemand.dao.OndemandDAO;
import ar.com.santanderrio.obp.servicios.comun.ondemand.dao.impl.OnDemandDAOException;
import ar.com.santanderrio.obp.servicios.comun.ondemand.dao.impl.OndemandDAOImpl;
import ar.com.santanderrio.obp.servicios.comun.ondemand.mapper.OnDemandMapper;
import ar.com.santanderrio.obp.servicios.comun.ondemand.utils.WSOnDemandClient;
import ar.com.santanderrio.obp.servicios.comun.resumen.dao.ResumenDAO;
import ar.com.santanderrio.obp.servicios.comun.resumen.entity.ResumenInEntity;
import ar.com.santanderrio.obp.servicios.cuentas.bo.impl.ResumenMensualCuentaBOImpl;
import ar.com.santanderrio.obp.servicios.cuentas.entities.AdhesionResumenDTO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.ondemand.entities.ResumenParams;
import ar.com.santanderrio.obp.servicios.ondemand.entities.WSODException;
import ar.com.santanderrio.obp.servicios.ondemand.entities.WSODRequest;
import ar.com.santanderrio.obp.servicios.ondemand.entities.WSODResponse;

/**
 * The Class ResumenMensualCuentaBOTest.
 */
@RunWith(MockitoJUnitRunner.class)
public class ResumenMensualCuentaBOAdhesionResumenTest {

    /** The resumen mensual cuenta BO. */
    @InjectMocks
    private ResumenMensualCuentaBO resumenMensualCuentaBO = new ResumenMensualCuentaBOImpl();

    /** The ondemand DAO. */
    @Mock
    private OndemandDAO ondemandDAO = new OndemandDAOImpl();

    /** The respuesta factory. */
    @InjectMocks
    @Spy
    private RespuestaFactory respuestaFactory = new RespuestaFactory();

    /** The on demand mapper. */
    @InjectMocks
    @Spy
    private OnDemandMapper onDemandMapper = new OnDemandMapper();

    /** The w S on demand client. */
    @Mock
    private WSOnDemandClient wSOnDemandClient;

    /** The credential security factory. */
    @Mock
    private CredentialSecurityFactory credentialSecurityFactory;

    /** The resumen DAO. */
    @Mock
    private ResumenDAO resumenDAO;

    /**
     * Modificar marca adhesion test.
     *
     * @throws WSODException
     *             the WSOD exception
     * @throws SQLException
     *             the SQL exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void modificarMarcaAdhesionTest() throws WSODException, SQLException, DAOException {
        AdhesionResumenDTO adhesionResumenDTO = new AdhesionResumenDTO();
        Cuenta cuenta = new Cuenta();
        Cliente cliente = new Cliente();
        Credential credential = new Credential();
        cliente.setNup("123123123");

        cuenta.setMonedaAltair("ARS");
        cuenta.setCodigoAplicacion("ACTE");
        cuenta.setCliente(cliente);

        adhesionResumenDTO.setAdhesionAutomatica("P");
        adhesionResumenDTO.setOpinionUsuario("Resumen Fisico");
        adhesionResumenDTO.setCuenta(cuenta);

        credential.setUsuario("user");
        credential.setPassword("123456");

        WSODResponse value = new WSODResponse("0", null, null, null);

        Mockito.when(wSOnDemandClient.send(Matchers.any(WSODRequest.class), Matchers.anyString())).thenReturn(value);
        Mockito.when(credentialSecurityFactory.getCredentialById(Matchers.anyString())).thenReturn(credential);
        ResumenInEntity input = new ResumenInEntity();
        input.setMotivo("asdasd");
        input.setNup("00000000");

        Mockito.when(resumenDAO.grabarMotivo(input)).thenReturn("0");

        Respuesta<Void> respuesta = resumenMensualCuentaBO.cambiarMarcaImpresion(adhesionResumenDTO);

        assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }

    /**
     * Modificar marca adhesion exceptions test.
     *
     * @throws WSODException
     *             the WSOD exception
     * @throws SQLException
     *             the SQL exception
     * @throws DAOException
     *             the DAO exception
     * @throws OnDemandDAOException
     *             the on demand DAO exception
     */
    @Test
    public void modificarMarcaAdhesionExceptionsTest()
            throws WSODException, SQLException, DAOException, OnDemandDAOException {
        AdhesionResumenDTO adhesionResumenDTO = new AdhesionResumenDTO();
        Cuenta cuenta = new Cuenta();
        Cliente cliente = new Cliente();
        Credential credential = new Credential();
        cliente.setNup("123123123");

        cuenta.setMonedaAltair("ARS");
        cuenta.setCodigoAplicacion("ACTE");
        cuenta.setCliente(cliente);

        adhesionResumenDTO.setAdhesionAutomatica("P");
        adhesionResumenDTO.setOpinionUsuario("Resumen Fisico");
        adhesionResumenDTO.setCuenta(cuenta);

        credential.setUsuario("user");
        credential.setPassword("123456");

        Mockito.when(credentialSecurityFactory.getCredentialById(Matchers.anyString())).thenReturn(credential);

        Mockito.doThrow(DAOException.class).when(resumenDAO).grabarMotivo(Matchers.any(ResumenInEntity.class));
        resumenMensualCuentaBO.cambiarMarcaImpresion(adhesionResumenDTO);
        Mockito.doThrow(WSODException.class).when(ondemandDAO)
                .modificarMarcaImpresion(Matchers.any(ResumenParams.class));
        resumenMensualCuentaBO.cambiarMarcaImpresion(adhesionResumenDTO);
        Mockito.doThrow(OnDemandDAOException.class).when(ondemandDAO)
                .modificarMarcaImpresion(Matchers.any(ResumenParams.class));
        resumenMensualCuentaBO.cambiarMarcaImpresion(adhesionResumenDTO);
        Mockito.doThrow(ProcessingException.class).when(ondemandDAO)
                .modificarMarcaImpresion(Matchers.any(ResumenParams.class));
        resumenMensualCuentaBO.cambiarMarcaImpresion(adhesionResumenDTO);
    }

    /**
     * Crear comprobante adhesion resumen test.
     */
    @Test
    public void crearComprobanteAdhesionResumenTest() {
        assertNotNull(resumenMensualCuentaBO.crearComprobanteAdhesionResumen("1234567890"));
    }

}
