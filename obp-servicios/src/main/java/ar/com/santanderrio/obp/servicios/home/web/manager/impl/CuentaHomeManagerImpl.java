/*
 * 
 */
package ar.com.santanderrio.obp.servicios.home.web.manager.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.citi.manager.ClienteCitiMananager;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.SaldosConsolidadosCuentaDTO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.home.web.manager.CuentaHomeManager;
import ar.com.santanderrio.obp.servicios.home.web.view.AccionController;
import ar.com.santanderrio.obp.servicios.home.web.view.Caja;
import ar.com.santanderrio.obp.servicios.home.web.view.CajaHomeCuentaView;
import ar.com.santanderrio.obp.servicios.home.web.view.GrupoCajaHomeView;
import ar.com.santanderrio.obp.servicios.home.web.view.SaldosConsolidadosView;
import ar.com.santanderrio.obp.servicios.modulos.bo.ModuloPermisoBO;

/**
 * The Class CuentaHomeManagerImpl.
 */
@Component
public class CuentaHomeManagerImpl extends AbstractHomeManager implements CuentaHomeManager {

    /** The Constant ENCABEZADO_CAJA_CUENTA. */
    private static final String ENCABEZADO_CAJA_CUENTA = "Cuentas";

    /** The Constant ICONO_CAJA_CUENTA. */
    private static final String ICONO_CAJA_CUENTA = "icono-cuenta";

    /** The Constant TITULO_CAJA_CUENTA. */
    private static final String TITULO_CAJA_CUENTA = "Saldos totales";
    
    /** The Constant TEXTO_LINK_CAJA_CUENTA. */
    private static final String TEXTO_LINK_CAJA_CUENTA = "Ver mis cuentas";

    private static final String TEXTO_REPATRIACION = "Cuenta especial repatriación de fondos - Aporte solidario y extraordinario. Ley 27.605";

    private static final String CODIGO_PRODUCTO_REPATRIACION_2021 = "0003";
    private static final String CODIGO_SUBPRODUCTO_REPATRIACION_2021 = "0007";

    /** The respuesta factory. */
    @Autowired
    private RespuestaFactory respuestaFactory;

    /** The cuenta BO. */
    @Autowired
    private CuentaBO cuentaBO;

    /** The sesion cliente. */
    @Autowired
    private SesionCliente sesionCliente;

    /** The estadistica manager. */
    @Autowired
    private EstadisticaManager estadisticaManager;

    /** The cliente citi manager. */
    @Autowired
    private ClienteCitiMananager clienteCitiMananager;
      
	@Autowired
	private ModuloPermisoBO moduloPermisoBO;

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.home.web.manager.impl.
     * AbstractHomeManager#obtenerCajas()
     */
    @Override
    protected GrupoCajaHomeView obtenerCajas() {
        List<Caja> cajas = new ArrayList<Caja>();
        Caja caja = getCaja();
        cajas.add(caja);
        GrupoCajaHomeView grupoCajaHomeView = new GrupoCajaHomeView();
        grupoCajaHomeView.setCajas(cajas);
        grupoCajaHomeView.setSinError(Boolean.TRUE);
        return grupoCajaHomeView;
    }

    /**
     * Gets the caja.
     *
     * @return the caja
     */
    private Caja getCaja() {
        CajaHomeCuentaView cajaHomeCuentaView = new CajaHomeCuentaView();

        cajaHomeCuentaView.setIsCuenta(true);
        cajaHomeCuentaView.setIcono(ICONO_CAJA_CUENTA);
        cajaHomeCuentaView.setRecargar(true);
        cajaHomeCuentaView.setTextoLink(TEXTO_LINK_CAJA_CUENTA);
        fillCardTitles(cajaHomeCuentaView);

        return cajaHomeCuentaView;
    }

    private void fillCardTitles(CajaHomeCuentaView cardCuentas) {
        List<Cuenta> cuentasMonetarias = sesionCliente.getCliente().getCuentasMonetarias();
        boolean cuentaMonetariaUnica = cuentasMonetarias.size() == 1;
        if (cuentaMonetariaUnica) {
            Cuenta cuenta = cuentasMonetarias.get(0);
            if (isCuentaRepatriacion(cuenta)) {
                cardCuentas.setEncabezado(ISBANStringUtils.formatearNroCuentaProductoConSucursal(cuenta));
                cardCuentas.setTitulo(TEXTO_REPATRIACION);
            } else {
                cardCuentas.setEncabezado(ISBANStringUtils.formatearNroCuentaProductoConSucursal(cuenta));
                cardCuentas.setTitulo(cuenta.getTipoCuentaEnum().getDescripcion());
            }
        } else {
            cardCuentas.setEncabezado(ENCABEZADO_CAJA_CUENTA);
            cardCuentas.setTitulo(TITULO_CAJA_CUENTA);
        }
    }

    private boolean isCuentaRepatriacion(Cuenta cuenta) {
        return TipoCuenta.CAJA_AHORRO_DOLARES.equals(TipoCuenta.fromCodigo(cuenta.getTipoCuenta())) &&
                CODIGO_PRODUCTO_REPATRIACION_2021.equals(cuenta.getProductoAltair()) &&
                CODIGO_SUBPRODUCTO_REPATRIACION_2021.equals(cuenta.getSubproductoAltair());
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.home.web.manager.GrupoHomeManager#
     * aplicaGrupo()
     */
    @Override
    public Respuesta<Boolean> aplicaGrupo() {
        Cliente cliente = sesionCliente.getCliente();
        Boolean aplicaGrupo = cuentaBO.hasCuentasMonetariasActivas(cliente);
        return respuestaFactory.crearRespuestaOk(Boolean.class, aplicaGrupo);
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.home.web.manager.CuentaHomeManager#
     * obtenerSaldosCuentas()
     */
    @Override
    public Respuesta<SaldosConsolidadosView> obtenerSaldosCuentas() {
        Cliente cliente = sesionCliente.getCliente();

        this.clienteCitiMananager.marcarClienteCiti();
        
        if(moduloPermisoBO.obtenerModuloPermisoPorAccion(AccionController.VER_SALDOS_CUENTAS)
                .tienePermisoDeVisibilidad()) {
            Respuesta<SaldosConsolidadosCuentaDTO> respuesta = cuentaBO.obtenerSaldosConsolidadosActualizados(cliente);
            
            if (EstadoRespuesta.OK.equals(respuesta.getEstadoRespuesta())) {
                SaldosConsolidadosView saldosConsolidadosView = parsearSaldosConsolidados(respuesta.getRespuesta(), cliente);
                saldosConsolidadosView.setUnicaCuenta(cuentaBO.tieneUnaSolaCuenta(cliente));
                estadisticaManager.add(EstadisticasConstants.HOME_ACTUALIZAR_SALDOS_CUENTAS,
                        EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
                return respuestaFactory.crearRespuestaOk(SaldosConsolidadosView.class, saldosConsolidadosView);
            } else {
                estadisticaManager.add(EstadisticasConstants.HOME_ACTUALIZAR_SALDOS_CUENTAS,
                        EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
                return Respuesta.copy(SaldosConsolidadosView.class, respuesta);
            }
        } else {
			return respuestaFactory.crearRespuestaWarning(null, "", TipoError.WARNING_CAJA_VACIA, "");
        }

    }

    /**
     * Parsear saldos consolidados.
     *
     * @param saldosConsolidadosDTO
     *            the saldos consolidados DTO
     * @return the saldos consolidados view
     */
    private SaldosConsolidadosView parsearSaldosConsolidados(SaldosConsolidadosCuentaDTO saldosConsolidadosDTO, Cliente cliente) {
        SaldosConsolidadosView saldosView = new SaldosConsolidadosView();
        if (saldosConsolidadosDTO.getSaldoDolares() != null) {
            saldosView.setSaldoDolares(
                    ISBANStringUtils.formatearSaldoConSigno(saldosConsolidadosDTO.getSaldoDolaresValue()));
            saldosView.setSaldoDolaresValue(saldosConsolidadosDTO.getSaldoDolaresValue());
        }
        if (saldosConsolidadosDTO.getSaldoPesos() != null) {
            saldosView
                    .setSaldoPesos(ISBANStringUtils.formatearSaldoConSigno(saldosConsolidadosDTO.getSaldoPesosValue()));
            saldosView.setSaldoPesosValue(saldosConsolidadosDTO.getSaldoPesosValue());
        }
        
        //saldosView.setHostNocturno(sesionCliente.getCliente().isHostNocturno());
        
        if (sesionCliente.getCliente().isHostNocturno()){
        	saldosView = verificarSaldosCero(saldosView);
        }
        
        return saldosView;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.home.web.manager.GrupoHomeManager#
     * obtenerAccion()
     */
    @Override
    public AccionController obtenerAccion() {
        return AccionController.IR_HOME_CUENTA;
    }
    
    private SaldosConsolidadosView verificarSaldosCero (SaldosConsolidadosView view) {
    	//BigDecimal cero = new BigDecimal("0.00");
    	
    	if (view.getSaldoPesosValue().compareTo(BigDecimal.ZERO) == 0) {
    		view.setSaldoPesos("----");
    	}
    	
    	if (view.getSaldoDolaresValue().compareTo(BigDecimal.ZERO) == 0) {
    		view.setSaldoDolares("----");
    	}
    	
		return view;
    }
}
