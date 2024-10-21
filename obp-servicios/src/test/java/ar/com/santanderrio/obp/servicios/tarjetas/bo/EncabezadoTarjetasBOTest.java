package ar.com.santanderrio.obp.servicios.tarjetas.bo;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.tarjetas.bo.impl.EncabezadoTarjetasBOImpl;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.AdhesionTarjetasDAO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.AdhesionTarjeta;

/**
 * The Class EncabezadoTarjetasBOTest.
 */
@RunWith(MockitoJUnitRunner.class)
public class EncabezadoTarjetasBOTest {

    /** The encabezado tarjetas BO. */
    @InjectMocks
    private EncabezadoTarjetasBO encabezadoTarjetasBO = new EncabezadoTarjetasBOImpl();

    /** The adhesion tarjetas DAO. */
    @Mock
    private AdhesionTarjetasDAO adhesionTarjetasDAO;

    /**
     * Obtener adhesion tarjeta ok test.
     *
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void obtenerAdhesionTarjetaOkTest() throws DAOException {
        Cliente cliente = new Cliente();
        Cuenta tarjeta = new Cuenta();
        AdhesionTarjeta adhesionTarjeta = new AdhesionTarjeta();

        Mockito.when(
                adhesionTarjetasDAO.consultarAdhesionTarjeta(Matchers.any(Cuenta.class), Matchers.any(Cliente.class)))
                .thenReturn(adhesionTarjeta);

        Respuesta<AdhesionTarjeta> res = encabezadoTarjetasBO.obtenerAdhesionTarjeta(cliente, tarjeta);
        Assert.assertEquals(res.getEstadoRespuesta(), EstadoRespuesta.OK);
        Assert.assertEquals(res.getRespuesta(), adhesionTarjeta);

    }

    /**
     * Obtener adhesion tarjeta DAO exception test.
     *
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void obtenerAdhesionTarjetaDAOExceptionTest() throws DAOException {
        Cliente cliente = new Cliente();
        Cuenta tarjeta = new Cuenta();

        Mockito.when(
                adhesionTarjetasDAO.consultarAdhesionTarjeta(Matchers.any(Cuenta.class), Matchers.any(Cliente.class)))
                .thenThrow(new DAOException());

        Respuesta<AdhesionTarjeta> res = encabezadoTarjetasBO.obtenerAdhesionTarjeta(cliente, tarjeta);
        Assert.assertEquals(res.getEstadoRespuesta(), EstadoRespuesta.ERROR);
    }

    /**
     * Obtener adhesion tarjetas recargables ok test.
     *
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void obtenerAdhesionTarjetasRecargablesOkTest() throws DAOException {
        Cliente cliente = new Cliente();
        List<Cuenta> cuentas = new ArrayList<Cuenta>();
        Cuenta cuenta = new Cuenta();
        cuenta.setTipoCuentaEnum(TipoCuenta.VISA);
        cuenta.setClaseCuenta("T");
        cuenta.setCalidadDeParticipacion("TI");
        cuentas.add(cuenta);
        cliente.setCuentas(cuentas);
        AdhesionTarjeta adhesionTarjeta = new AdhesionTarjeta();

        Mockito.when(
                adhesionTarjetasDAO.consultarAdhesionTarjeta(Matchers.any(Cuenta.class), Matchers.any(Cliente.class)))
                .thenReturn(adhesionTarjeta);

        Respuesta<List<AdhesionTarjeta>> res = encabezadoTarjetasBO.obtenerAdhesionTarjetasRecargables(cliente);
        Assert.assertEquals(res.getEstadoRespuesta(), EstadoRespuesta.OK);
    }

    /**
     * Obtener tarjetas recargables ok sin cuenta test.
     *
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void obtenerTarjetasRecargablesOkSinCuentaTest() throws DAOException {
        Cliente cliente = new Cliente();

        Respuesta<List<Cuenta>> res = encabezadoTarjetasBO.obtenerTarjetasRecargables(cliente);
        Assert.assertEquals(res.getEstadoRespuesta(), EstadoRespuesta.OK);
    }

    /**
     * Obtener tarjetas recargables ok VISATTI test.
     *
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void obtenerTarjetasRecargablesOkVISATTITest() throws DAOException {
        Cliente cliente = new Cliente();
        Cuenta cuenta = new Cuenta();
        cliente.setCuentas(new ArrayList<Cuenta>());

        cuenta.setTipoCuentaEnum(TipoCuenta.VISA);
        cuenta.setClaseCuenta("T");
        cuenta.setCalidadDeParticipacion("TI");
        cuenta.setNroOrdenFirmante("003");
        cliente.getCuentas().add(cuenta);

        Respuesta<List<Cuenta>> res = encabezadoTarjetasBO.obtenerTarjetasRecargables(cliente);
        Assert.assertEquals(res.getEstadoRespuesta(), EstadoRespuesta.OK);

    }

    /**
     * Obtener tarjetas recargables ok VISA 001 test.
     *
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void obtenerTarjetasRecargablesOkVISA001Test() throws DAOException {
        Cliente cliente = new Cliente();
        Cuenta cuenta = new Cuenta();
        cliente.setCuentas(new ArrayList<Cuenta>());

        cuenta.setTipoCuentaEnum(TipoCuenta.VISA);
        cuenta.setClaseCuenta("T");
        cuenta.setCalidadDeParticipacion("SI");
        cuenta.setNroOrdenFirmante("001");
        cliente.getCuentas().add(cuenta);

        Respuesta<List<Cuenta>> res = encabezadoTarjetasBO.obtenerTarjetasRecargables(cliente);
        Assert.assertEquals(res.getEstadoRespuesta(), EstadoRespuesta.OK);

    }

    /**
     * Obtener tarjetas recargables ok VISATAD 003 test.
     *
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void obtenerTarjetasRecargablesOkVISATAD003Test() throws DAOException {
        Cliente cliente = new Cliente();
        Cuenta cuenta = new Cuenta();
        cliente.setCuentas(new ArrayList<Cuenta>());

        cuenta.setTipoCuentaEnum(TipoCuenta.VISA);
        cuenta.setClaseCuenta("T");
        cuenta.setCalidadDeParticipacion("AD");
        cuenta.setNroOrdenFirmante("003");
        cliente.getCuentas().add(cuenta);

        Respuesta<List<Cuenta>> res = encabezadoTarjetasBO.obtenerTarjetasRecargables(cliente);
        Assert.assertEquals(res.getEstadoRespuesta(), EstadoRespuesta.OK);

    }

    /**
     * Obtener tarjetas recargables ok VISASTAD 003 test.
     *
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void obtenerTarjetasRecargablesOkVISASTAD003Test() throws DAOException {
        Cliente cliente = new Cliente();
        Cuenta cuenta = new Cuenta();
        cliente.setCuentas(new ArrayList<Cuenta>());

        cuenta.setTipoCuentaEnum(TipoCuenta.VISA);
        cuenta.setClaseCuenta("S");
        cuenta.setCalidadDeParticipacion("AD");
        cuenta.setNroOrdenFirmante("003");
        cliente.getCuentas().add(cuenta);

        Respuesta<List<Cuenta>> res = encabezadoTarjetasBO.obtenerTarjetasRecargables(cliente);
        Assert.assertEquals(res.getEstadoRespuesta(), EstadoRespuesta.OK);

    }

    /**
     * Obtener tarjetas recargables ok not VISAAD 003 test.
     *
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void obtenerTarjetasRecargablesOkNotVISAAD003Test() throws DAOException {
        Cliente cliente = new Cliente();
        Cuenta cuenta = new Cuenta();
        cliente.setCuentas(new ArrayList<Cuenta>());

        cuenta.setTipoCuentaEnum(TipoCuenta.AMEX);
        cuenta.setCalidadDeParticipacion("AD");
        cuenta.setNroOrdenFirmante("003");
        cliente.getCuentas().add(cuenta);

        Respuesta<List<Cuenta>> res = encabezadoTarjetasBO.obtenerTarjetasRecargables(cliente);
        Assert.assertEquals(res.getEstadoRespuesta(), EstadoRespuesta.OK);

    }

    /**
     * Obtener tarjetas recargables ok not VISAAD 001 test.
     *
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void obtenerTarjetasRecargablesOkNotVISAAD001Test() throws DAOException {
        Cliente cliente = new Cliente();
        Cuenta cuenta = new Cuenta();
        cliente.setCuentas(new ArrayList<Cuenta>());

        cuenta.setTipoCuentaEnum(TipoCuenta.AMEX);
        cuenta.setCalidadDeParticipacion("AD");
        cuenta.setNroOrdenFirmante("001");
        cliente.getCuentas().add(cuenta);

        Respuesta<List<Cuenta>> res = encabezadoTarjetasBO.obtenerTarjetasRecargables(cliente);
        Assert.assertEquals(res.getEstadoRespuesta(), EstadoRespuesta.OK);

    }

    /**
     * Obtener tarjetas recargables ok not VISANN 003 test.
     *
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void obtenerTarjetasRecargablesOkNotVISANN003Test() throws DAOException {
        Cliente cliente = new Cliente();
        Cuenta cuenta = new Cuenta();
        cliente.setCuentas(new ArrayList<Cuenta>());

        cuenta.setTipoCuentaEnum(TipoCuenta.AMEX);
        cuenta.setCalidadDeParticipacion("NN");
        cuenta.setNroOrdenFirmante("003");
        cliente.getCuentas().add(cuenta);

        Respuesta<List<Cuenta>> res = encabezadoTarjetasBO.obtenerTarjetasRecargables(cliente);
        Assert.assertEquals(res.getEstadoRespuesta(), EstadoRespuesta.OK);

    }

    /**
     * Obtener tarjetas recargables ok not VISANN 001 test.
     *
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void obtenerTarjetasRecargablesOkNotVISANN001Test() throws DAOException {
        Cliente cliente = new Cliente();
        Cuenta cuenta = new Cuenta();
        cliente.setCuentas(new ArrayList<Cuenta>());

        cuenta.setTipoCuentaEnum(TipoCuenta.AMEX);
        cuenta.setCalidadDeParticipacion("NN");
        cuenta.setNroOrdenFirmante("001");
        cliente.getCuentas().add(cuenta);

        Respuesta<List<Cuenta>> res = encabezadoTarjetasBO.obtenerTarjetasRecargables(cliente);
        Assert.assertEquals(res.getEstadoRespuesta(), EstadoRespuesta.OK);

    }
}
