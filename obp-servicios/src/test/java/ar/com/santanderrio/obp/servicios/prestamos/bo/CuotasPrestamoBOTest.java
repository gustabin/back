package ar.com.santanderrio.obp.servicios.prestamos.bo;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.prestamos.bo.impl.CuotasPrestamoBOImpl;
import ar.com.santanderrio.obp.servicios.prestamos.cuotaspagas.dao.PrestamoCuotaPagaDAO;
import ar.com.santanderrio.obp.servicios.prestamos.cuotaspagas.entities.ConsultaCuotaPagaInEntity;
import ar.com.santanderrio.obp.servicios.prestamos.cuotaspagas.entities.ConsultaCuotaPagaOutEntity;
import ar.com.santanderrio.obp.servicios.prestamos.cuotaspagas.entities.ConsultaCuotaPagaOutEntityMock;
import ar.com.santanderrio.obp.servicios.prestamos.dao.CuotasPrestamoDAO;
import ar.com.santanderrio.obp.servicios.prestamos.entity.DeudaPrestamoEntity;
import ar.com.santanderrio.obp.servicios.prestamos.exception.SinCuotasPagasException;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ClienteMock;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.CuentaMock;

/**
 * The Class CuotasPrestamoBOTest.
 */
@RunWith(MockitoJUnitRunner.class)
public class CuotasPrestamoBOTest {

    /** The cuotas prestamo BO. */
    @InjectMocks
    CuotasPrestamoBOImpl cuotasPrestamoBO;

    /** The cuotas prestamo DAO. */
    @Mock
    CuotasPrestamoDAO cuotasPrestamoDAO;

    /** The prestamo cuota paga DAO. */
    @Mock
    PrestamoCuotaPagaDAO prestamoCuotaPagaDAO;

    /**
     * Consultar proximas cuotas.
     *
     * @throws BusinessException
     *             the business exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void consultarProximasCuotas() throws BusinessException, DAOException {

        // When
        Cliente cliente = mock(Cliente.class);
        Cuenta cuenta = mock(Cuenta.class);

        DeudaPrestamoEntity deudaPrestamoEntity = new DeudaPrestamoEntity();

        when(cuotasPrestamoDAO.consultarProximasCuotas(Matchers.any(Cliente.class), Matchers.any(Cuenta.class)))
                .thenReturn(deudaPrestamoEntity);

        // Then
        DeudaPrestamoEntity respuesta = cuotasPrestamoBO.consultarProximasCuotas(cliente, cuenta);

        // Expected
        Assert.assertNotNull(respuesta);

    }

    /**
     * Dados un cliente, una cuenta y una fecha de inicio de un prestamo, cuando
     * se invoca al metodo "consultarCuotasPagas", obtengo un objeto consulta
     * cuota paga out entity OK.
     *
     * @author florencia.n.martinez
     * @throws BusinessException
     *             the business exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void dadosClienteCuentaFechaInicioCuandoInvocaConsultarCuotasPagasObtengoConsultaCuotaPagaOutEntityOK()
            throws BusinessException, DAOException, SinCuotasPagasException{
        Cliente cliente = ClienteMock.completarInfoCliente();
        Cuenta cuenta = CuentaMock.completarInfoCuenta();
        String fechaInicio = "2015-09-23";
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

        Mockito.when(prestamoCuotaPagaDAO.obtenerCuotasPagasPrestamo(cliente,
                new ConsultaCuotaPagaInEntity(cuenta.getNroSucursal(), cuenta.getNroCuentaProducto(), fechaInicio,
                        dateFormatter.format(new Date()))))
                .thenReturn(ConsultaCuotaPagaOutEntityMock.obtenerConsultaCuotaPagaOutEntity());
        ConsultaCuotaPagaOutEntity outEntity = cuotasPrestamoBO.consultarCuotasPagas(cliente, cuenta, fechaInicio);

        Assert.assertNotNull(outEntity.getCuotasPagas());
    }

    /**
     * Consultar cuotas pagas DAO exection test.
     *
     * @throws BusinessException
     *             the business exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test(expected = BusinessException.class)
    public void consultarCuotasPagasDAOExectionTest() throws BusinessException, DAOException, SinCuotasPagasException {
        Cliente cliente = ClienteMock.completarInfoCliente();
        Cuenta cuenta = CuentaMock.completarInfoCuenta();
        String fechaInicio = "2015-09-23";

        Mockito.when(prestamoCuotaPagaDAO.obtenerCuotasPagasPrestamo(Matchers.any(Cliente.class),
                Matchers.any(ConsultaCuotaPagaInEntity.class)))
                .thenThrow(new DAOException("Error al obtener las cuotas pagas."));
        ConsultaCuotaPagaOutEntity outEntity = cuotasPrestamoBO.consultarCuotasPagas(cliente, cuenta, fechaInicio);

        Assert.assertNotNull(outEntity.getCuotasPagas());
    }

    /**
     * Consultar cuotas pagas fecha inicio null test.
     *
     * @throws BusinessException
     *             the business exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test(expected = BusinessException.class)
    public void consultarCuotasPagasFechaInicioNullTest() throws BusinessException, DAOException, SinCuotasPagasException {
        // Given
        Cliente cliente = ClienteMock.completarInfoCliente();
        Cuenta cuenta = CuentaMock.completarInfoCuenta();
        String fechaInicio = null;

        // Then
        ConsultaCuotaPagaOutEntity outEntity = cuotasPrestamoBO.consultarCuotasPagas(cliente, cuenta, fechaInicio);

        // Expected
        Assert.assertNotNull(outEntity.getCuotasPagas());
    }

    /**
     * Consultar proximas cuotas DAO exception test.
     *
     * @throws BusinessException
     *             the business exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test(expected = BusinessException.class)
    public void consultarProximasCuotasDAOExceptionTest() throws BusinessException, DAOException {
        // Given
        Cliente cliente = ClienteMock.completarInfoCliente();
        Cuenta cuenta = CuentaMock.completarInfoCuenta();

        // When
        Mockito.when(cuotasPrestamoDAO.consultarProximasCuotas(Matchers.any(Cliente.class), Matchers.any(Cuenta.class)))
                .thenThrow(new DAOException("Error de validacion de bean."));

        // Then
        DeudaPrestamoEntity entity = cuotasPrestamoBO.consultarProximasCuotas(cliente, cuenta);

        // Expected
        Assert.assertNotNull(entity);
    }

}
