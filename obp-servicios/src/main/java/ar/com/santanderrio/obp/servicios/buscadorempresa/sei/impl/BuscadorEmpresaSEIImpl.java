/**
 * 
 */
package ar.com.santanderrio.obp.servicios.buscadorempresa.sei.impl;

import ar.com.santanderrio.obp.servicios.debinrecurrente.view.EmpresasDebinRecurrenteView;
import ar.com.santanderrio.obp.servicios.debinrecurrente.view.VendedorPrestacionesView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.buscadorempresa.sei.BuscadorEmpresaSEI;
import ar.com.santanderrio.obp.servicios.comun.buscador.web.manager.BuscadorEmpresaManager;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.pagos.entities.BuscadorEmpresaRta;
import ar.com.santanderrio.obp.servicios.pagos.entities.BuscadorEmpresaView;
import ar.com.santanderrio.obp.servicios.pagos.web.view.EmpresaDebitoAutomaticoTarjetaView;
import ar.com.santanderrio.obp.servicios.pagos.web.view.EmpresaRecargaCelularView;

/**
 * The Class BuscadorEmpresaSEIImpl.
 *
 * @author sergio.e.goldentair
 */
@Component("buscadorEmpresaSEI")
public class BuscadorEmpresaSEIImpl implements BuscadorEmpresaSEI {
    /** The logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(BuscadorEmpresaSEIImpl.class);

    /** The estadistica manager. */
    @Autowired
    private EstadisticaManager estadisticaManager;

    /** The buscador medios de pago manager. */
    @Autowired
    private BuscadorEmpresaManager buscadorArchivoPagoManager;

    /**
     * Estadistica.
     *
     * @return the boolean
     */
    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.buscador.sei.BuscadorEmpresaSEI#estadistica()
     */
    @Override
    public Boolean estadistica() {
        estadisticaManager.add(EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_NUEVO_PAGO,
                EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        LOGGER.info("Log con estadistica {}.", EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_NUEVO_PAGO);
        return true;
    }

    /**
     * Search by rubro empresa.
     *
     * @param buscador the buscador
     * @return the respuesta
     */
    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.buscador.sei.BuscadorEmpresaSEI#searchByRubroEmpresa(
     * java.lang.String)
     */
    @Override
    public Respuesta<BuscadorEmpresaRta> searchByRubroEmpresa(BuscadorEmpresaView buscador) {
        return buscadorArchivoPagoManager.searchByRubroEmpresa(buscador.getQuery());
    }

    /**
     * Empresa pago automatico.
     *
     * @param buscador the buscador
     * @return the respuesta
     */
    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.buscador.sei.BuscadorEmpresaSEI#empresaPagoAutomatico
     * (java.lang.String)
     */
    @Override
    public Respuesta<BuscadorEmpresaRta> empresaPagoAutomatico(BuscadorEmpresaView buscador) {
        estadisticaManager.add(EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_NUEVO_PAGO_AUTOMATICO,
                EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        return buscadorArchivoPagoManager.searchEmpresaPagoAutomatico(buscador.getQuery());
    }

    /**
     * Buscador recarga celulares.
     *
     * @param terminoBusqueda the termino busqueda
     * @return the respuesta
     */
    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.buscadorempresa.sei.BuscadorEmpresaSEI#
     * buscadorRecargaCelulares(ar.com.santanderrio.obp.servicios.pagos.entities
     * .BuscadorEmpresaView)
     */
    @Override
    public Respuesta<EmpresaRecargaCelularView> buscadorRecargaCelulares() {
        return buscadorArchivoPagoManager.searchEmpresaRecargaCelulares();
    }

    /**
     * Buscador debito automatico en tarjeta.
     *
     * @param terminoBusqueda the termino busqueda
     * @return the respuesta
     */
    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.buscadorempresa.sei.BuscadorEmpresaSEI#
     * buscadorDebitoAutomaticoEnTarjeta(ar.com.santanderrio.obp.servicios.pagos
     * .entities.BuscadorEmpresaView)
     */
    @Override
    public Respuesta<EmpresaDebitoAutomaticoTarjetaView> buscadorDebitoAutomaticoEnTarjeta(
            BuscadorEmpresaView terminoBusqueda) {
        return buscadorArchivoPagoManager.searchEmpresaDebitoAutomaticoEnTarjeta(terminoBusqueda.getQuery());
    }

    /**
     * Estadistica adhesion debito automatico en tarjeta.
     *
     * @return the boolean
     */
    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.buscadorempresa.sei.BuscadorEmpresaSEI#
     * estadisticaAdhesionDebitoAutomaticoEnTarjeta()
     */
    @Override
    public Boolean estadisticaAdhesionDebitoAutomaticoEnTarjeta() {
        estadisticaManager.add(EstadisticasConstants.INGRESO_BUSCADOR_ADHESION_DEBITO_AUTOMATICO_EN_TARJETA,
                EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        return true;
    }

    /**
     * Empresa nuevo pago.
     *
     * @param buscador the buscador
     * @return the respuesta
     */
    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.buscadorempresa.sei.BuscadorEmpresaSEI#empresaNuevoPago(ar.com.santanderrio.obp.servicios.pagos.entities.BuscadorEmpresaView)
     */
    @Override
    public Respuesta<BuscadorEmpresaRta> empresaNuevoPago(BuscadorEmpresaView buscador) {
        return buscadorArchivoPagoManager.searchEmpresaNuevoPago(buscador.getQuery());
    }

	/**
	 * Buscador recarga transporte.
	 *
	 * @return the respuesta
	 */
	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.buscadorempresa.sei.BuscadorEmpresaSEI#buscadorRecargaTransporte()
	 */
	@Override
	public Respuesta<BuscadorEmpresaRta> buscadorRecargaTransporte() {
		 return buscadorArchivoPagoManager.searchEmpresaRecargaTransporte();
	}

	/**
	 * Obtener empresas.
	 *
	 * @param buscador the buscador
	 * @return the respuesta
	 */
	@Override
	public Respuesta<EmpresasDebinRecurrenteView> obtenerEmpresas(BuscadorEmpresaView buscador) {
		return buscadorArchivoPagoManager.obtenerEmpresas(buscador.getQuery());
	}

    /**
     * Obtener servicios.
     *
     * @param buscador the buscador
     * @return the respuesta
     */
    @Override
    public Respuesta<VendedorPrestacionesView> obtenerServicios(BuscadorEmpresaView buscador) {
        return buscadorArchivoPagoManager.obtenerServicios(buscador.getQuery());
    }

}
