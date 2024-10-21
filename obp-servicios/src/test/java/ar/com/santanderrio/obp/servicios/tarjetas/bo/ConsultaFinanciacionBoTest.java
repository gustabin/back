package ar.com.santanderrio.obp.servicios.tarjetas.bo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.generated.webservices.visa.planv.SolicitudPlanV;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.AbstractCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.tarjetas.bo.impl.ConsultaFinanciacionBoImpl;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.ConsultaFinanciacionDAO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ConsultaFinanciacionDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.CuentaMock;

/**
 * The Class ConsultaFinanciacionBoTest.
 */
@RunWith(MockitoJUnitRunner.class)
public class ConsultaFinanciacionBoTest {

    /** The consulta financiacion bo. */
    @InjectMocks
    ConsultaFinanciacionBo consultaFinanciacionBo = new ConsultaFinanciacionBoImpl();

    /** The consulta financiacion DAO. */
    @Mock
    private ConsultaFinanciacionDAO consultaFinanciacionDAO;

    /** The respuesta factory. */
    @InjectMocks
    @Spy
    private RespuestaFactory respuestaFactory = new RespuestaFactory();

    /** The cuenta BO. */
    @Mock
    private CuentaBO cuentaBO;

    /** The mensaje DAO. */
    @Mock
    private MensajeBO mensajeBO;

    /**
     * Obtener lista financiacion ok test.
     *
     * @throws BusinessException
     *             the business exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void obtenerListaFinanciacionOkTest() throws BusinessException, DAOException {
        Cuenta cuenta = CuentaMock.completarInfoCuenta();
        Mockito.when(cuentaBO.buscarCuentaPorId(Matchers.any(Cliente.class), Matchers.any(IdentificacionCuenta.class)))
                .thenReturn(cuenta);
        Mockito.when(consultaFinanciacionDAO.obtenerListaFinanciacion(Matchers.any(Cuenta.class)))
                .thenReturn(completarInfoListaSolicitudesPlanV());

        Respuesta<ConsultaFinanciacionDTO> respuesta = consultaFinanciacionBo.obtenerListaFinanciacion(
                new IdentificacionCuenta(cuenta.getNroSucursal(), cuenta.getNroCuentaProducto()), cuenta.getCliente());

        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
        Assert.assertEquals("AMEX", respuesta.getRespuesta().getDetallesFinanciaciones().get(0).getMarca());
        Assert.assertEquals("XXXX-20312",
                respuesta.getRespuesta().getDetallesFinanciaciones().get(0).getNumeroTarjeta());
        Assert.assertEquals(Integer.valueOf("3"), respuesta.getRespuesta().getDetallesFinanciaciones().get(0)
                .getFinanciaciones().get(0).getCantidadCuotas());
        Assert.assertEquals(BigDecimal.valueOf(500.00), respuesta.getRespuesta().getDetallesFinanciaciones().get(0)
                .getFinanciaciones().get(0).getMontoCuotaDelPlan());
    }

    /**
     * Obtener lista financiacion DAO exception test.
     *
     * @throws BusinessException
     *             the business exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void obtenerListaFinanciacionDAOExceptionTest() throws BusinessException, DAOException {
        Cuenta cuenta = CuentaMock.completarInfoCuenta();

        Mockito.when(cuentaBO.buscarCuentaPorId(Matchers.any(Cliente.class), Matchers.any(IdentificacionCuenta.class)))
                .thenReturn(cuenta);
        Mockito.when(consultaFinanciacionDAO.obtenerListaFinanciacion(Matchers.any(Cuenta.class)))
                .thenThrow(new DAOException());
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(obtenerMensajeERROR());

        Respuesta<ConsultaFinanciacionDTO> respuesta = consultaFinanciacionBo.obtenerListaFinanciacion(
                new IdentificacionCuenta(cuenta.getNroSucursal(), cuenta.getNroCuentaProducto()), cuenta.getCliente());

        Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
    }

    /**
     * Obtener lista financiacion DAO exception error sin consumos test.
     *
     * @throws BusinessException
     *             the business exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void obtenerListaFinanciacionDAOExceptionErrorSinConsumosTest() throws BusinessException, DAOException {
        Cuenta cuenta = CuentaMock.completarInfoCuenta();
        DAOException daoException = new DAOException();
        daoException.setErrorCode("fc1:1");

        Mockito.when(cuentaBO.buscarCuentaPorId(Matchers.any(Cliente.class), Matchers.any(IdentificacionCuenta.class)))
                .thenReturn(cuenta);
        Mockito.when(consultaFinanciacionDAO.obtenerListaFinanciacion(Matchers.any(Cuenta.class)))
                .thenThrow(daoException);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(obtenerMensajeERROR());

        Respuesta<ConsultaFinanciacionDTO> respuesta = consultaFinanciacionBo.obtenerListaFinanciacion(
                new IdentificacionCuenta(cuenta.getNroSucursal(), cuenta.getNroCuentaProducto()), cuenta.getCliente());

        Assert.assertEquals(EstadoRespuesta.WARNING, respuesta.getEstadoRespuesta());
    }

    /**
     * Obtener lista financiacion other exception test.
     *
     * @throws BusinessException
     *             the business exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test(expected = BusinessException.class)
    public void obtenerListaFinanciacionOtherExceptionTest() throws BusinessException, DAOException {
        Respuesta<ConsultaFinanciacionDTO> respuesta = consultaFinanciacionBo
                .obtenerListaFinanciacion(new IdentificacionCuenta(), new Cliente());

        Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
    }

    /**
     * Obtener financiaciones con nro tarjeta test.
     *
     * @throws BusinessException
     *             the business exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void obtenerFinanciacionesConNroTarjetaTest() throws BusinessException, DAOException {
        IdentificacionCuenta identificacionCuenta = new IdentificacionCuenta();
        Cliente cliente = new Cliente();
        AbstractCuenta abstractCuenta = new Cuenta();
        Respuesta<ConsultaFinanciacionDTO> resConsultaDTO = new Respuesta<ConsultaFinanciacionDTO>();
        List<SolicitudPlanV> listSolicitud = new ArrayList<SolicitudPlanV>();
        SolicitudPlanV solicitudPlanV = new SolicitudPlanV();

        resConsultaDTO.setEstadoRespuesta(EstadoRespuesta.OK);
        abstractCuenta.setTipoCuentaEnum(TipoCuenta.BANELCO);
        solicitudPlanV.setNumeroTarjeta(" ");

        listSolicitud.add(solicitudPlanV);

        Mockito.when(cuentaBO.buscarCuentaPorId(cliente, identificacionCuenta)).thenReturn(abstractCuenta);
        Mockito.when(consultaFinanciacionDAO.obtenerListaFinanciacion(Matchers.any(Cuenta.class)))
                .thenReturn(listSolicitud);
        Mockito.when(respuestaFactory.crearRespuestaOk(Matchers.eq(ConsultaFinanciacionDTO.class),
                Matchers.any(ConsultaFinanciacionDTO.class))).thenReturn(resConsultaDTO);
        Respuesta<ConsultaFinanciacionDTO> res = consultaFinanciacionBo.obtenerListaFinanciacion(identificacionCuenta,
                cliente);
        Assert.assertEquals(EstadoRespuesta.OK, res.getEstadoRespuesta());
    }

    /**
     * Obtener financiaciones con spv nulo test.
     *
     * @throws BusinessException
     *             the business exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test(expected = BusinessException.class)
    public void obtenerFinanciacionesConSpvNuloTest() throws BusinessException, DAOException {
        IdentificacionCuenta identificacionCuenta = new IdentificacionCuenta();
        Cliente cliente = new Cliente();
        AbstractCuenta abstractCuenta = new Cuenta();
        Respuesta<ConsultaFinanciacionDTO> resConsultaDTO = new Respuesta<ConsultaFinanciacionDTO>();
        List<SolicitudPlanV> listSolicitud = new ArrayList<SolicitudPlanV>();
        SolicitudPlanV solicitudPlanV = null;

        resConsultaDTO.setEstadoRespuesta(EstadoRespuesta.OK);
        abstractCuenta.setTipoCuentaEnum(TipoCuenta.BANELCO);

        listSolicitud.add(solicitudPlanV);

        Mockito.when(cuentaBO.buscarCuentaPorId(cliente, identificacionCuenta)).thenReturn(abstractCuenta);
        Mockito.when(consultaFinanciacionDAO.obtenerListaFinanciacion(Matchers.any(Cuenta.class)))
                .thenReturn(listSolicitud);

        Respuesta<ConsultaFinanciacionDTO> res = consultaFinanciacionBo.obtenerListaFinanciacion(identificacionCuenta,
                cliente);
        Assert.assertEquals(EstadoRespuesta.OK, res.getEstadoRespuesta());
    }

    /**
     * Obtiene el mensaje de ERROR.
     *
     * @return the mensaje
     */
    private Mensaje obtenerMensajeERROR() {
        Mensaje msj = new Mensaje();
        msj.setMensaje("ERROR DAO.");
        return msj;
    }

    /**
     * Completa la informacion de las solicitudes de Plan V.
     *
     * @return the list
     */
    private List<SolicitudPlanV> completarInfoListaSolicitudesPlanV() {
        List<SolicitudPlanV> solicitudes = new ArrayList<SolicitudPlanV>();
        solicitudes.add(completarInfoSolicitudPlanV(3, "23/07/2016", 500.0, 1500.0, "3777910057203120", "25798066"));
        return solicitudes;
    }

    /**
     * Completa la informacion de la solicitud de plan V.
     *
     * @param cantCuotas
     *            the cant cuotas
     * @param fecha
     *            the fecha
     * @param importeCuota
     *            the importe cuota
     * @param importePendiente
     *            the importe pendiente
     * @param nroTarjeta
     *            the nro tarjeta
     * @param nroCuenta
     *            the nro cuenta
     * @return the solicitud plan V
     */
    private SolicitudPlanV completarInfoSolicitudPlanV(Integer cantCuotas, String fecha, Double importeCuota,
            Double importePendiente, String nroTarjeta, String nroCuenta) {
        SolicitudPlanV solicitud = new SolicitudPlanV();
        solicitud.setCantidadCuotas(cantCuotas);
        solicitud.setFechaSolicitud(fecha);
        solicitud.setImporteCuota(importeCuota);
        solicitud.setImportePendienteDeLiquidar(importePendiente);
        solicitud.setNumeroTarjeta(nroTarjeta);
        solicitud.setNumeroCuenta(nroCuenta);
        return solicitud;
    }

}
