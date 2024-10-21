package ar.com.santanderrio.obp.servicios.prestamos.bo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.prestamos.bo.impl.PrestamoPermitidoBOImpl;
import ar.com.santanderrio.obp.servicios.prestamos.dao.PrestamoPermitidoDAO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.PrestamoPermitidoDTO;
import ar.com.santanderrio.obp.servicios.prestamos.entity.PrestamoPermitidoEntity;
import ar.com.santanderrio.obp.servicios.prestamos.entity.PrestamoPermitidoInEntity;
import ar.com.santanderrio.obp.servicios.prestamos.entity.PrestamoPermitidoOutEntity;

/**
 * The Class PrestamoPermitidoBOTest.
 */
@RunWith(MockitoJUnitRunner.class)
public class PrestamoPermitidoBOTest {

    /** The prestamo permitido BO impl. */
    @InjectMocks
    private PrestamoPermitidoBOImpl prestamoPermitidoBOImpl = new PrestamoPermitidoBOImpl();

    /** The cuenta BO. */
    @Mock
    private CuentaBO cuentaBO;

    /** The prestamo permitido DAO. */
    @Mock
    private PrestamoPermitidoDAO prestamoPermitidoDAO;

    /** The respuesta factory. */
    @Spy
    private RespuestaFactory respuestaFactory;
    
    /** The sesion parametros. */
    @Mock
    private SesionParametros sesionParametros;

    /**
     * Obtener prestamo con saldo mayor test.
     *
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void obtenerPrestamoConSaldoMayorTest() throws DAOException {

        Cliente cliente = new Cliente();
        Cuenta cuenta = new Cuenta();
        cuenta.setTipoCuentaEnum(TipoCuenta.CUENTA_UNICA_PESOS);
        
        PrestamoPermitidoOutEntity prestamoPermitidoOutEntity = new PrestamoPermitidoOutEntity();
        List<PrestamoPermitidoEntity> listPrestamoPermitidoEntity = new ArrayList<PrestamoPermitidoEntity>();
        PrestamoPermitidoEntity prestamoPermitidoEntity1 = new PrestamoPermitidoEntity();
        PrestamoPermitidoEntity prestamoPermitidoEntity2 = new PrestamoPermitidoEntity();

        prestamoPermitidoEntity1.setMaxImpPrest("00000000002005000");
        prestamoPermitidoEntity2.setMaxImpPrest("00000000002007500");
        listPrestamoPermitidoEntity.add(prestamoPermitidoEntity1);
        listPrestamoPermitidoEntity.add(prestamoPermitidoEntity2);
        prestamoPermitidoOutEntity.setListaResult(listPrestamoPermitidoEntity);
        List<Cuenta> cuentas = new ArrayList<Cuenta>();
        cuentas.add(cuenta);
     cliente.setCuentas(cuentas);
        
        when(prestamoPermitidoDAO.consultarPrestamosPermitidosPreacordados(Matchers.any(PrestamoPermitidoInEntity.class)))
                .thenReturn(prestamoPermitidoOutEntity);
       when(cuentaBO.obtenerCuentaPrincipalCliente(Matchers.any(Cliente.class))).thenReturn(cuenta);

        Respuesta<PrestamoPermitidoDTO> respuesta = prestamoPermitidoBOImpl.obtenerPrestamoConSaldoMayor(cliente);

        assertNotNull(respuesta.getRespuesta());
        PrestamoPermitidoDTO prestamoPermitidoDTO = respuesta.getRespuesta();
        assertEquals(prestamoPermitidoDTO.getMontoPrestamo(), new BigDecimal("200.7500"));
    }

}
