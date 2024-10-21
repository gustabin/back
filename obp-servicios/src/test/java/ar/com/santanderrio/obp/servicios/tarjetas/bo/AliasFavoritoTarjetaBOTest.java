package ar.com.santanderrio.obp.servicios.tarjetas.bo;

import java.util.ArrayList;

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
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO;
import ar.com.santanderrio.obp.servicios.cuentas.dao.AliasFavoritoProductoDAO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.tarjetas.bo.impl.AliasFavoritoTarjetaBoImpl;
import ar.com.santanderrio.obp.servicios.tarjetas.util.ManejoDeMensajesTarjeta;

/**
 * The Class AliasFavoritoTarjetaBOTest.
 */
@RunWith(MockitoJUnitRunner.class)
public class AliasFavoritoTarjetaBOTest {

    /** The alias favorito tarjeta bo. */
    @InjectMocks
    private AliasFavoritoTarjetaBoImpl aliasFavoritoTarjetaBo = new AliasFavoritoTarjetaBoImpl();

    /** The cuenta BO. */
    @Mock
    private CuentaBO cuentaBO;

    /** The alias favorito producto DAO. */
    @Mock
    private AliasFavoritoProductoDAO aliasFavoritoProductoDAO;

    /** The mensajes tarjeta. */
    @Mock
    private ManejoDeMensajesTarjeta mensajesTarjeta;

    /**
     * Actualiza el alias OK.
     *
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void actualizarAliasOkTest() throws BusinessException {
        Cliente cliente = new Cliente();
        cliente.setApellido1("Flores");
        cliente.setNombre("Federico");
        cliente.setNup("1234");

        Cuenta cuenta = new Cuenta();
        cuenta.setNroCuentaProducto("0000023234343454545453");
        cuenta.setCliente(cliente);

        Respuesta<Void> res = aliasFavoritoTarjetaBo.actualizarAlias(cuenta, "alias");

        Assert.assertEquals(res.getEstadoRespuesta(), EstadoRespuesta.OK);
        Assert.assertEquals(res.isRespuestaVacia(), false);
    }

    /**
     * Actualiza el alias con ERROR por tener un alias de mayor tamanio.
     *
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void actualizarAliasSizeOfAliasMayorMaxTest() throws BusinessException {
        Cliente cliente = new Cliente();
        cliente.setApellido1("Flores");
        cliente.setNombre("Federico");
        cliente.setNup("1234");

        Cuenta cuenta = new Cuenta();
        cuenta.setNroCuentaProducto("0000023234343454545453");
        cuenta.setCliente(cliente);

        Respuesta<Void> res = aliasFavoritoTarjetaBo.actualizarAlias(cuenta,
                "alias de prueba que tiene mas que el maximo");

        Assert.assertEquals(res.getEstadoRespuesta(), EstadoRespuesta.ERROR);
        Assert.assertEquals(res.isRespuestaVacia(), false);
    }

    /**
     * Actualiza el alias y se produca un DAO exception.
     *
     * @throws BusinessException
     *             the business exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void actualizarAliasDAOExceptionTest() throws BusinessException, DAOException {
        Cliente cliente = new Cliente();
        cliente.setApellido1("Flores");
        cliente.setNombre("Federico");
        cliente.setNup("1234");

        Cuenta cuenta = new Cuenta();
        cuenta.setNroCuentaProducto("0000023234343454545453");
        cuenta.setCliente(cliente);

        Mockito.doThrow(new DAOException()).when(aliasFavoritoProductoDAO).actualizaAlias(Matchers.anyLong(),
                Matchers.anyString(), Matchers.anyString());

        Respuesta<Void> res = aliasFavoritoTarjetaBo.actualizarAlias(cuenta, "alias");

        Assert.assertEquals(res.getEstadoRespuesta(), EstadoRespuesta.ERROR);
        Assert.assertEquals(res.isRespuestaVacia(), false);
    }

    /**
     * Marca la tarjeta como favorita con resultado OK.
     *
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void marcarFavoritaOkTest() throws BusinessException {
        Cliente cliente = new Cliente();
        IdentificacionCuenta identificacionCuenta = new IdentificacionCuenta();
        cliente.setApellido1("Flores");
        cliente.setNombre("Federico");
        cliente.setNup("1234");

        Cuenta cuenta = new Cuenta();
        cuenta.setNroCuentaProducto("0000023234343454545453");
        cuenta.setCliente(cliente);
        cuenta.setNroSucursal("1100");
        cuenta.setTipoCuentaEnum(TipoCuenta.VISA);
        cliente.setCuentas(new ArrayList<Cuenta>());
        cliente.getCuentas().add(cuenta);

        identificacionCuenta.setNroCuentaProducto("0000023234343454545452");
        identificacionCuenta.setNroSucursal("1100");
        Mockito.when(cuentaBO.buscarCuentaPorId(cliente, identificacionCuenta)).thenReturn(cuenta);

        Respuesta<Void> res = aliasFavoritoTarjetaBo.marcarFavorita(identificacionCuenta, cliente);

        Assert.assertEquals(EstadoRespuesta.OK, res.getEstadoRespuesta());
        Assert.assertEquals(res.isRespuestaVacia(), false);
    }

    /**
     * Marca una tarjeta como favorita con resultado OK, cuenta id coincidencia.
     *
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void marcarFavoritaOkCuentaIdCoincidenciaTest() throws BusinessException {
        Cliente cliente = new Cliente();
        IdentificacionCuenta identificacionCuenta = new IdentificacionCuenta();
        cliente.setApellido1("Flores");
        cliente.setNombre("Federico");
        cliente.setNup("1234");

        Cuenta cuenta = new Cuenta();
        cuenta.setNroCuentaProducto("0000023234343454545453");
        cuenta.setCliente(cliente);
        cuenta.setNroSucursal("1100");
        cliente.setCuentas(new ArrayList<Cuenta>());
        cliente.getCuentas().add(cuenta);

        identificacionCuenta.setNroCuentaProducto("0000023234343454545453");
        identificacionCuenta.setNroSucursal("1100");
        Mockito.when(cuentaBO.buscarCuentaPorId(Matchers.any(Cliente.class), Matchers.any(IdentificacionCuenta.class)))
                .thenReturn(cuenta);

        Respuesta<Void> res = aliasFavoritoTarjetaBo.marcarFavorita(identificacionCuenta, cliente);

        Assert.assertEquals(EstadoRespuesta.OK, res.getEstadoRespuesta());
        Assert.assertEquals(res.isRespuestaVacia(), false);
    }

    /**
     * Marca la tarjeta como favorita con resultado OK, no tipo cuenta tarjeta.
     *
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void marcarFavoritaOkNoTipoCuentaTarjetaTest() throws BusinessException {
        Cliente cliente = new Cliente();
        IdentificacionCuenta identificacionCuenta = new IdentificacionCuenta();
        cliente.setApellido1("Flores");
        cliente.setNombre("Federico");
        cliente.setNup("1234");

        Cuenta cuenta = new Cuenta();
        cuenta.setNroCuentaProducto("0000023234343454545453");
        cuenta.setCliente(cliente);
        cuenta.setNroSucursal("1101");
        cuenta.setTipoCuentaEnum(TipoCuenta.CUENTA_CORRIENTE_PESOS);
        cliente.setCuentas(new ArrayList<Cuenta>());
        cliente.getCuentas().add(cuenta);

        identificacionCuenta.setNroCuentaProducto("0000023234343454545453");
        identificacionCuenta.setNroSucursal("1100");
        Mockito.when(cuentaBO.buscarCuentaPorId(cliente, identificacionCuenta)).thenReturn(cuenta);

        Respuesta<Void> res = aliasFavoritoTarjetaBo.marcarFavorita(identificacionCuenta, cliente);

        Assert.assertEquals(EstadoRespuesta.OK, res.getEstadoRespuesta());
        Assert.assertEquals(res.isRespuestaVacia(), false);
    }

    /**
     * Al querer actualizar la tarjeta como favorito, se lanza una exception.
     *
     * @throws BusinessException
     *             the business exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void actualizarFavoritoExceptionTest() throws BusinessException, DAOException {
        // Igual a los de arriba, pero en este se ejecuta una exception en
        // actualizarFavorito
        Cliente cliente = new Cliente();
        IdentificacionCuenta identificacionCuenta = new IdentificacionCuenta();
        cliente.setApellido1("Flores");
        cliente.setNombre("Federico");
        cliente.setNup("1234");

        Cuenta cuenta = new Cuenta();
        cuenta.setNroCuentaProducto("0000023234343454545453");
        cuenta.setCliente(cliente);
        cuenta.setNroSucursal("1100");
        cuenta.setTipoCuentaEnum(TipoCuenta.VISA);
        cliente.setCuentas(new ArrayList<Cuenta>());
        cliente.getCuentas().add(cuenta);

        identificacionCuenta.setNroCuentaProducto("0000023234343454545452");
        identificacionCuenta.setNroSucursal("1100");
        Mockito.when(cuentaBO.buscarCuentaPorId(cliente, identificacionCuenta)).thenReturn(cuenta);
        Mockito.doThrow(new DAOException()).when(aliasFavoritoProductoDAO).actualizaFavorito(Matchers.anyLong(),
                Matchers.anyString(), Matchers.anyBoolean());

        Respuesta<Void> res = aliasFavoritoTarjetaBo.marcarFavorita(identificacionCuenta, cliente);

        Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
        Assert.assertEquals(res.isRespuestaVacia(), false);
    }

}