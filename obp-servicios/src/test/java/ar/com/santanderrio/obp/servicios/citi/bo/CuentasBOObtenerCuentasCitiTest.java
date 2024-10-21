package ar.com.santanderrio.obp.servicios.citi.bo;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.citi.dao.CuentasCitiDAO;
import ar.com.santanderrio.obp.servicios.citi.entities.CuentaCitiEntity;
import ar.com.santanderrio.obp.servicios.citi.entities.CuentaCitiMigrada;
import ar.com.santanderrio.obp.servicios.citi.entities.CuentasCitiInEntity;
import ar.com.santanderrio.obp.servicios.citi.entities.CuentasCitiOutEntity;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.cuentas.bo.impl.CuentaBOImpl;
import ar.com.santanderrio.obp.servicios.cuentas.dao.AliasFavoritoProductoDAO;
import ar.com.santanderrio.obp.servicios.cuentas.dao.DetalleCuentaDAO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.pagos.dao.BanelcoDAO;

/**
 * The Class CuentasBOObtenerCuentasTest.
 */
@RunWith(MockitoJUnitRunner.class)
public class CuentasBOObtenerCuentasCitiTest {

    /** The cuentas BO. */
    @InjectMocks
	private CuentaBOImpl cuentaBO = new CuentaBOImpl();

    /** The detalle cuenta DAO. */
    @Mock
    private DetalleCuentaDAO detalleCuentaDAO;

    /** The mensaje BO. */
    @Mock
    private MensajeBO mensajeBO;
    
    /** The banelco DAO. */
    @Mock
    private BanelcoDAO banelcoDAO;

	/** The alias favorito producto DAO. */
	@Mock
	private AliasFavoritoProductoDAO aliasFavoritoProductoDAO;

	/** The respuesta factory. */
	@InjectMocks
	@Spy
	private RespuestaFactory respuestaFactory = new RespuestaFactory();

	/** The cuentas citi DAO. */
	@Mock
	private CuentasCitiDAO cuentasCitiDAO;
	
    @Test
    public void obtenerCuentasCitiTest() throws BusinessException, DAOException {
        List<TipoCuenta> cuentasUnimonetarias = new ArrayList<TipoCuenta>();
        llenarListaCuentasUnimonetarias(cuentasUnimonetarias);
        Cliente cliente = new Cliente();
        CuentasCitiOutEntity cuentasCitiOutEntity = new CuentasCitiOutEntity();

        for (TipoCuenta tipoCuenta : cuentasUnimonetarias) {
            Cuenta cuenta = crearCuenta(tipoCuenta);
            adicionarCuentasSalida(cuentasCitiOutEntity, tipoCuenta);

            when(cuentasCitiDAO.ejecutarConsultaCuentaCity(
                    Matchers.any(CuentasCitiInEntity.class),
                    Matchers.anyString())).thenReturn(cuentasCitiOutEntity);
        
            Respuesta<CuentaCitiMigrada> cuentaCitiMigrada = cuentaBO.obtenerCuentaCiti(cuenta, cliente);
            assertNotNull(cuentaCitiMigrada);
        }
    }

    private void llenarListaCuentasUnimonetarias(List<TipoCuenta> cuentasUnimonetarias) {
        cuentasUnimonetarias.add(TipoCuenta.CUENTA_UNICA);
        cuentasUnimonetarias.add(TipoCuenta.CAJA_AHORRO_PESOS);
        cuentasUnimonetarias.add(TipoCuenta.CUENTA_CORRIENTE_PESOS);
        cuentasUnimonetarias.add(TipoCuenta.CAJA_AHORRO_DOLARES);
    }
    
    public void adicionarCuentasSalida(CuentasCitiOutEntity cuentasCitiOutEntity, TipoCuenta tipoCuenta) throws BusinessException, DAOException {
        CuentaCitiEntity citiCajaAhorroPesos = null;
        CuentaCitiEntity citiCuentaCorriente = null;
        CuentaCitiEntity citiCajaAhorroDolares = null;
        switch (tipoCuenta) {
            case CUENTA_UNICA:
                citiCajaAhorroPesos = new CuentaCitiEntity();
                citiCuentaCorriente = new CuentaCitiEntity();
                cuentasCitiOutEntity.getCuentasCiti().add(citiCajaAhorroPesos);
                cuentasCitiOutEntity.getCuentasCiti().add(citiCuentaCorriente);
                break;
            case CAJA_AHORRO_PESOS:
                citiCajaAhorroPesos = new CuentaCitiEntity();
                cuentasCitiOutEntity.getCuentasCiti().add(citiCajaAhorroPesos);
                break;
            case CUENTA_CORRIENTE_PESOS:
                citiCuentaCorriente = new CuentaCitiEntity();
                cuentasCitiOutEntity.getCuentasCiti().add(citiCuentaCorriente);
                break;
            case CAJA_AHORRO_DOLARES:
                citiCajaAhorroDolares = new CuentaCitiEntity();
                cuentasCitiOutEntity.getCuentasCiti().add(citiCajaAhorroDolares);
                break;
            default :
                break;
        }
    }
    
    /**
     * Crear cuenta.
     *
     * @param tipoCuenta the tipo cuenta
     * @return the cuenta
     */
    private Cuenta crearCuenta(TipoCuenta tipoCuenta) {
        Cuenta cuenta = new Cuenta();
        cuenta.setTipoCuenta("02");
        cuenta.setTipoCuentaEnum(tipoCuenta);
        cuenta.setNroCuentaProducto("0000000001600148");
        cuenta.setNroSucursal("0099");
        cuenta.setNroTarjetaCredito("00000000000000000000");
        cuenta.setSaldoCuenta("");
        cuenta.setSaldoCUPesos("8457707.60");
        cuenta.setSaldoCUDls("9490505.58");
        cuenta.setOficinaAltair("Oficina central");
        cuenta.setContratoAltair("Contrato Altair1");
        cuenta.setMonedaAltair("ARS");
        return cuenta;
    }

}
