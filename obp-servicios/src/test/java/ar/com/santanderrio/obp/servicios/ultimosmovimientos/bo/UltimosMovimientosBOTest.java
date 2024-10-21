package ar.com.santanderrio.obp.servicios.ultimosmovimientos.bo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.impl.MensajeBOImpl;
import ar.com.santanderrio.obp.servicios.comun.mensaje.dao.MensajeDAO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.dao.impl.MensajeDAOImpl;
import ar.com.santanderrio.obp.servicios.comun.sucursales.bo.ConsultarSucursalesBO;
import ar.com.santanderrio.obp.servicios.comun.sucursales.entities.Sucursal;
import ar.com.santanderrio.obp.servicios.cuentas.bo.MovimientosBO;
import ar.com.santanderrio.obp.servicios.cuentas.bo.RespuestaBO;
import ar.com.santanderrio.obp.servicios.cuentas.bo.UltimosMovimientosBO;
import ar.com.santanderrio.obp.servicios.cuentas.bo.impl.RespuestaBOImpl;
import ar.com.santanderrio.obp.servicios.cuentas.bo.impl.UltimosMovimientosBOImpl;
import ar.com.santanderrio.obp.servicios.cuentas.entities.AbstractCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.ConsultaUltimosMovimientos;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DetalleUltimosMovimientos;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Movimiento;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoConsultaMovimientos;
import ar.com.santanderrio.obp.servicios.cuentas.entities.UltimosMovimientosDTO;
import ar.com.santanderrio.obp.servicios.cuentas.web.manager.SessionMovimientos;
import ar.com.santanderrio.obp.servicios.cuentas.web.manager.impl.SessionMovimientosImpl;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.general.entities.RangoFechaEnum;

/**
 * The Class UltimosMovimientosBOTest.
 */
@RunWith(MockitoJUnitRunner.class)
public class UltimosMovimientosBOTest {
	
	/** The Constant INDICADOR_MOVIMIENTO_CAJA_DE_AHORRO. */
    private static final String INDICADOR_MOVIMIENTO_CAJA_DE_AHORRO = "01";

    /** The Constant CANTIDAD_DIAS. */
    private static final Integer CANTIDAD_DIAS = 30;

    /** The ultimos movimientos BO. */
    @InjectMocks
    private UltimosMovimientosBO ultimosMovimientosBO = new UltimosMovimientosBOImpl();

    /** The ultimos movimientos DAO. */
    @Mock
    private MovimientosBO movimientosBO;

    /** The sucursal BO. */
    @Mock
    private ConsultarSucursalesBO sucursalBO;

    /** The respuesta BO. */
    @InjectMocks
    @Spy
    private RespuestaBO respuestaBO = new RespuestaBOImpl();
    
    @InjectMocks
    @Spy
    private RespuestaFactory respuestaFactory = new RespuestaFactory();
    
    @InjectMocks
    @Spy
    private SessionMovimientos sessionMovimientos = new SessionMovimientosImpl();
    
    @Mock
    private MensajeBO mensajeBO = new MensajeBOImpl();

    /** The mensaje dao. */
    @Spy
    private MensajeDAO mensajeDao = new MensajeDAOImpl();

    /**
     * Obtener ultimos movimientos test.
     *
     * @throws BusinessException
     *             the business exception
     * @throws DAOException
     *             the DAO exception
     * @throws IllegalAccessException
     *             the illegal access exception
     */
    @Test
    public void obtenerUltimosMovimientosTest() throws BusinessException, DAOException, IllegalAccessException {

        List<String> stringArrayList = new ArrayList<String>();
        stringArrayList.add("0101");
        stringArrayList.add("0103");
        FieldUtils.writeField(ultimosMovimientosBO, "codigosChequeRechazadoList", stringArrayList, true);
        FieldUtils.writeField(ultimosMovimientosBO, "codigosChequeList", stringArrayList, true);

        ConsultaUltimosMovimientos consultaUltimosMovimientos = new ConsultaUltimosMovimientos();
        consultaUltimosMovimientos.setCuenta(crearCuenta());
        consultaUltimosMovimientos.setCantidadMovimientos(0);
        consultaUltimosMovimientos.setFechaDesde(null);
        consultaUltimosMovimientos.setFechaHasta(null);
        consultaUltimosMovimientos.setImporteDesde(null);
        consultaUltimosMovimientos.setImporteHasta(null);
        consultaUltimosMovimientos.setPalabraClave("");
        consultaUltimosMovimientos.setIsTraspasoAutomatico(true);
        consultaUltimosMovimientos.setTipoConsulta(TipoConsultaMovimientos.MOVIMIENTOS_DEL_DIA);
        consultaUltimosMovimientos.setRangoFecha(RangoFechaEnum.DEFAULT);
        consultaUltimosMovimientos.setMoneda(DivisaEnum.PESO);

        UltimosMovimientosDTO ultimosMovimientos = new UltimosMovimientosDTO();
        List<Movimiento> movimientos = getMovimientos();
        ultimosMovimientos.setMovimientos(movimientos);
        Respuesta<UltimosMovimientosDTO> respuestaMovimientosBO = new Respuesta<UltimosMovimientosDTO>();
        respuestaMovimientosBO.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaMovimientosBO.setRespuesta(ultimosMovimientos);
        when(movimientosBO.obtenerMovimientos(consultaUltimosMovimientos)).thenReturn(respuestaMovimientosBO);
        Respuesta<Sucursal> respuestaSucursal = new Respuesta<Sucursal>();
        Sucursal sucursal = new Sucursal();
        sucursal.setDescripcion("Sucursal Flores");
        respuestaSucursal.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaSucursal.setRespuesta(sucursal);
        when(sucursalBO.consultarSucursalPorId(Matchers.anyString())).thenReturn(respuestaSucursal);
        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("Mensaje de Warning");
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);

        Respuesta<DetalleUltimosMovimientos> respuesta = ultimosMovimientosBO
                .obtenerUltimosMovimientos(consultaUltimosMovimientos, null);

        respuesta = ultimosMovimientosBO.obtenerUltimosMovimientos(consultaUltimosMovimientos,
                consultaUltimosMovimientos);
        assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
        assertTrue(!respuesta.isRespuestaVacia());
        assertNotNull(respuesta.getRespuesta());
        assertNotNull(respuesta.getRespuesta().getDetalleMovimiento().get(0));

    }

    /**
     * Validar fechas rangos test.
     */
    @Test
    public void validarFechasRangosTest() {
        ConsultaUltimosMovimientos consultaUltimosMovimientos = new ConsultaUltimosMovimientos();
        consultaUltimosMovimientos.setRangoFecha(RangoFechaEnum.PERSONALIZADO);
        consultaUltimosMovimientos.setTipoConsulta(TipoConsultaMovimientos.MOVIMIENTOS_DEL_DIA);
        consultaUltimosMovimientos.setTipoConsulta(TipoConsultaMovimientos.MOVIMIENTOS_DEL_DIA);
        consultaUltimosMovimientos.setFechaDesde(UltimosMovimientosBOImpl.limpiarHoras(new Date()));
        consultaUltimosMovimientos
                .setFechaHasta(UltimosMovimientosBOImpl.limpiarHoras(operarDiasDate(new Date(), CANTIDAD_DIAS)));
        AbstractCuenta cuenta = crearCuenta();
        consultaUltimosMovimientos.setCuenta(cuenta);
        Boolean cuentaCerrada = Boolean.FALSE;
        boolean resultado = UltimosMovimientosBOImpl.validateFechasyRangos(consultaUltimosMovimientos, cuentaCerrada);

        assertTrue(resultado);
    }

    /**
     * Crear cuenta.
     *
     * @return the abstract cuenta
     */
    public AbstractCuenta crearCuenta() {
        AbstractCuenta cuenta = new Cuenta();
        cuenta.setNroCuentaProducto("0000000004043627");
        cuenta.setCbu("0720000788000040436272");
        cuenta.setNroSucursal("0000");
        return cuenta;
    }

    /**
     * Operar dias date.
     *
     * @param fecha
     *            the fecha
     * @param days
     *            the days
     * @return the date
     */
    public static Date operarDiasDate(Date fecha, int days) {
        Calendar c = Calendar.getInstance();
        c.setTime(fecha);
        c.add(Calendar.DATE, days);
        fecha.setTime(c.getTime().getTime());
        return fecha;
    }

    /**
     * Gets the movimientos.
     *
     * @return the movimientos
     */
    private List<Movimiento> getMovimientos() {
        List<Movimiento> movimientos = new ArrayList<Movimiento>();
        for (int i = 0; i < 25; i++) {
            Movimiento movimiento = new Movimiento();
            movimiento.setReferenciaInterna(String.valueOf(i));
            movimiento.setFechaMovimiento("10082016");
            movimiento.setFechaValor("10082016");
            movimiento.setImporteMovimiento("100" + i);
            movimiento.setSaldoCuenta("100" + i);
            movimiento.setMonedaMovimiento("ARS");
            movimiento.setIndicadorMovimiento("01");
            movimiento.setHoraMovimiento("100" + i);
            movimiento.setSucursalOrigen("000");
            if (i == 5) {
                movimiento.setCodigoAltaIr("0101");
            }
            movimientos.add(movimiento);
        }
        return movimientos;
    }
    
    /**
     * Crear datos cuenta.
     *
     * @return the list
     */
    private List<Movimiento> crearDatosCuenta(){
    	
        List<Movimiento> movimientos = new ArrayList<Movimiento>();
        Movimiento movimiento = null;
		movimiento = new Movimiento();
        movimiento.setFechaMovimiento("10082016");
        movimiento.setFechaValor("10082016");
        movimiento.setImporteMovimiento("200");
        movimiento.setSaldoCuenta("1000");
        movimiento.setMonedaMovimiento(DivisaEnum.PESO.getCodigo());
        movimiento.setIndicadorMovimiento(INDICADOR_MOVIMIENTO_CAJA_DE_AHORRO);
        movimiento.setHoraMovimiento("1000");
        movimiento.setSucursalOrigen("000");
        movimientos.add(movimiento);
        
        movimiento = new Movimiento();
        movimiento.setFechaMovimiento("10082016");
        movimiento.setFechaValor("10082016");
        movimiento.setImporteMovimiento("200");
        movimiento.setSaldoCuenta("1000");
        movimiento.setMonedaMovimiento(DivisaEnum.PESO.getCodigo());
        movimiento.setIndicadorMovimiento(INDICADOR_MOVIMIENTO_CAJA_DE_AHORRO);
        movimiento.setHoraMovimiento("1000");
        movimiento.setSucursalOrigen("000");
        movimientos.add(movimiento);
        
        movimiento = new Movimiento();
        movimiento.setFechaMovimiento("10082016");
        movimiento.setFechaValor("10082016");
        movimiento.setImporteMovimiento("100");
        movimiento.setSaldoCuenta("1000");
        movimiento.setMonedaMovimiento(DivisaEnum.PESO.getCodigo());
        movimiento.setIndicadorMovimiento(INDICADOR_MOVIMIENTO_CAJA_DE_AHORRO);
        movimiento.setHoraMovimiento("1000");
        movimiento.setSucursalOrigen("000");
        movimientos.add(movimiento);
        
        movimiento = new Movimiento();
        movimiento.setFechaMovimiento("10082016");
        movimiento.setFechaValor("10082016");
        movimiento.setImporteMovimiento("100");
        movimiento.setSaldoCuenta("1000");
        movimiento.setMonedaMovimiento(DivisaEnum.PESO.getCodigo());
        movimiento.setIndicadorMovimiento(INDICADOR_MOVIMIENTO_CAJA_DE_AHORRO);
        movimiento.setHoraMovimiento("1000");
        movimiento.setSucursalOrigen("000");
        movimientos.add(movimiento);
        
        movimiento = new Movimiento();
        movimiento.setFechaMovimiento("10082016");
        movimiento.setFechaValor("10082016");
        movimiento.setImporteMovimiento("100");
        movimiento.setSaldoCuenta("1000");
        movimiento.setMonedaMovimiento(DivisaEnum.PESO.getCodigo());
        movimiento.setIndicadorMovimiento(INDICADOR_MOVIMIENTO_CAJA_DE_AHORRO);
        movimiento.setHoraMovimiento("1000");
        movimiento.setSucursalOrigen("000");
        movimientos.add(movimiento);
        
        movimiento = new Movimiento();
        movimiento.setFechaMovimiento("10082016");
        movimiento.setFechaValor("10082016");
        movimiento.setImporteMovimiento("100");
        movimiento.setSaldoCuenta("1000");
        movimiento.setMonedaMovimiento(DivisaEnum.PESO.getCodigo());
        movimiento.setIndicadorMovimiento(INDICADOR_MOVIMIENTO_CAJA_DE_AHORRO);
        movimiento.setHoraMovimiento("1000");
        movimiento.setSucursalOrigen("000");
        movimientos.add(movimiento);
        
        movimiento = new Movimiento();
        movimiento.setFechaMovimiento("10082016");
        movimiento.setFechaValor("10082016");
        movimiento.setImporteMovimiento("100");
        movimiento.setSaldoCuenta("1000");
        movimiento.setMonedaMovimiento(DivisaEnum.PESO.getCodigo());
        movimiento.setIndicadorMovimiento(INDICADOR_MOVIMIENTO_CAJA_DE_AHORRO);
        movimiento.setHoraMovimiento("1000");
        movimiento.setSucursalOrigen("000");
        movimientos.add(movimiento);
        
        movimiento = new Movimiento();
        movimiento.setFechaMovimiento("10082016");
        movimiento.setFechaValor("10082016");
        movimiento.setImporteMovimiento("100");
        movimiento.setSaldoCuenta("1000");
        movimiento.setMonedaMovimiento(DivisaEnum.PESO.getCodigo());
        movimiento.setIndicadorMovimiento(INDICADOR_MOVIMIENTO_CAJA_DE_AHORRO);
        movimiento.setHoraMovimiento("1000");
        movimiento.setSucursalOrigen("000");
        movimientos.add(movimiento);
        
        movimiento = new Movimiento();
        movimiento.setFechaMovimiento("10082016");
        movimiento.setFechaValor("10082016");
        movimiento.setImporteMovimiento("100");
        movimiento.setSaldoCuenta("1000");
        movimiento.setMonedaMovimiento(DivisaEnum.PESO.getCodigo());
        movimiento.setIndicadorMovimiento(INDICADOR_MOVIMIENTO_CAJA_DE_AHORRO);
        movimiento.setHoraMovimiento("1000");
        movimiento.setSucursalOrigen("000");
        movimientos.add(movimiento);
        
        movimiento = new Movimiento();
        movimiento.setFechaMovimiento("10082016");
        movimiento.setFechaValor("10082016");
        movimiento.setImporteMovimiento("100");
        movimiento.setSaldoCuenta("1000");
        movimiento.setMonedaMovimiento(DivisaEnum.PESO.getCodigo());
        movimiento.setIndicadorMovimiento(INDICADOR_MOVIMIENTO_CAJA_DE_AHORRO);
        movimiento.setHoraMovimiento("1000");
        movimiento.setSucursalOrigen("000");
        movimientos.add(movimiento);
        
        movimiento = new Movimiento();
        movimiento.setFechaMovimiento("10082016");
        movimiento.setFechaValor("10082016");
        movimiento.setImporteMovimiento("100");
        movimiento.setSaldoCuenta("1000");
        movimiento.setMonedaMovimiento(DivisaEnum.PESO.getCodigo());
        movimiento.setIndicadorMovimiento(INDICADOR_MOVIMIENTO_CAJA_DE_AHORRO);
        movimiento.setHoraMovimiento("1000");
        movimiento.setSucursalOrigen("000");
        movimientos.add(movimiento);
        
        movimiento = new Movimiento();
        movimiento.setFechaMovimiento("10082016");
        movimiento.setFechaValor("10082016");
        movimiento.setImporteMovimiento("100");
        movimiento.setSaldoCuenta("1000");
        movimiento.setMonedaMovimiento(DivisaEnum.PESO.getCodigo());
        movimiento.setIndicadorMovimiento(INDICADOR_MOVIMIENTO_CAJA_DE_AHORRO);
        movimiento.setHoraMovimiento("1000");
        movimiento.setSucursalOrigen("000");
        movimientos.add(movimiento);
        
        movimiento = new Movimiento();
        movimiento.setFechaMovimiento("10082016");
        movimiento.setFechaValor("10082016");
        movimiento.setImporteMovimiento("100");
        movimiento.setSaldoCuenta("1000");
        movimiento.setMonedaMovimiento(DivisaEnum.PESO.getCodigo());
        movimiento.setIndicadorMovimiento(INDICADOR_MOVIMIENTO_CAJA_DE_AHORRO);
        movimiento.setHoraMovimiento("1000");
        movimiento.setSucursalOrigen("000");
        movimientos.add(movimiento);
        
        movimiento = new Movimiento();
        movimiento.setFechaMovimiento("12082016");
        movimiento.setFechaValor("12082016");
        movimiento.setImporteMovimiento("100");
        movimiento.setSaldoCuenta("1000");
        movimiento.setMonedaMovimiento(DivisaEnum.PESO.getCodigo());
        movimiento.setIndicadorMovimiento(INDICADOR_MOVIMIENTO_CAJA_DE_AHORRO);
        movimiento.setHoraMovimiento("1000");
        movimiento.setSucursalOrigen("000");
        movimientos.add(movimiento);
        return movimientos;
    }
    
}
