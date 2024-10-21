/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.comun.sei;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.inversiones.comun.TipoBancaEnum;
import ar.com.santanderrio.obp.servicios.inversiones.comun.entity.ConsultaPerfilInversorRequest;
import ar.com.santanderrio.obp.servicios.inversiones.comun.manager.BaseManager;
import ar.com.santanderrio.obp.servicios.inversiones.comun.manager.InversionesManager;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.ConsultaPerfilInversorViewResponse;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.DatosTestPerfilViewResponse;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.DetalleCustodiaInView;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.DetalleCustodiaView;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.InicioInversionesViewRequest;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.TarjetaTenenciaConsolidadaView;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.TenenciaConsolidadaBPrivView;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.TenenciaConsolidadaView;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.TenenciaPorProductoInView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.manager.FondoManager;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.InicioFondoView;

/**
 * The Class InversionesSEIImpl.
 */
@Component("inversiones")
public class InversionesSEIImpl implements InversionesSEI {

    /** The base manager. */
    @Autowired
    private BaseManager baseManager;

    /** The inversiones manager. */
    @Autowired
    private InversionesManager inversionesManager;
    
	@Autowired
	private FondoManager fondoManager;

    /**
     * {@inheritDoc}
     */
    @Override
    public Respuesta<ConsultaPerfilInversorViewResponse> consultarPerfilInversor(ConsultaPerfilInversorRequest request) {
        return baseManager.consultarPerfilInversor(request.isEsBancaPrivada());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Respuesta<DatosTestPerfilViewResponse> obtenerDatosTestPerfil() {
        return baseManager.obtenerDatosTestPerfil(false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Respuesta<DatosTestPerfilViewResponse> obtenerDatosTestPerfilBP() {
        return baseManager.obtenerDatosTestPerfil(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Respuesta<InicioFondoView> inicioInversiones(InicioInversionesViewRequest request) {
        return inversionesManager.inicioInversiones(request);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Respuesta<TenenciaConsolidadaView> obtenerTenenciaConsolidadaPorProducto(
            TenenciaPorProductoInView estadisticasTotales) {
        return inversionesManager.obtenerTenenciaConsolidadaPorProducto(estadisticasTotales);
    }

    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.inversiones.comun.sei.InversionesSEI#obtenerTenenciaConsolidadaPorProductoBPriv(ar.com.santanderrio.obp.servicios.inversiones.comun.view.TenenciaPorProductoInView)
     */
    @Override
    public Respuesta<TenenciaConsolidadaBPrivView> obtenerTenenciaConsolidadaPorProductoBPriv(
            TenenciaPorProductoInView estadisticasTotales) {
        return inversionesManager.obtenerTenenciaConsolidadaPorProductoBPriv(estadisticasTotales);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Respuesta<TarjetaTenenciaConsolidadaView> obtenerTenenciaConsolidadaHome(
            InicioInversionesViewRequest requestView) {
        return inversionesManager.obtenerTenenciaConsolidadaHome(requestView, TipoBancaEnum.BANCA_PERSONAL);
    }

    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.inversiones.comun.sei.InversionesSEI#obtenerDetalleCuentaCustodiaPorProducto(ar.com.santanderrio.obp.servicios.inversiones.comun.view.DetalleCustodiaInView)
     */
    @Override
    public Respuesta<DetalleCustodiaView> obtenerDetalleCuentaCustodiaPorProducto(DetalleCustodiaInView detalleIn) {
        return inversionesManager.obtenerDetalleCuentaCustodiaPorProducto(detalleIn);
    }

	@Override
	public Response exportarTenenciaConsolidadaRTL() {
		return inversionesManager.exportarTenenciaConsolidada(TipoBancaEnum.BANCA_PERSONAL);
	}
	
	@Override
	public Response exportarTenenciaConsolidadaBP() {
		return inversionesManager.exportarTenenciaConsolidada(TipoBancaEnum.BANCA_PRIVADA);
	}
	
	@Override
	public Response exportarGrillaFondosRTL() {
		return fondoManager.exportarGrillaFondos(TipoBancaEnum.BANCA_PERSONAL);
	}

	@Override
	public Response exportarGrillaFondosBP() {
		return fondoManager.exportarGrillaFondos(TipoBancaEnum.BANCA_PRIVADA);
	}

	@Override
	public Respuesta<TarjetaTenenciaConsolidadaView> obtenerTenenciaConsolidadaHomeBP(InicioInversionesViewRequest requestView){
		return inversionesManager.obtenerTenenciaConsolidadaHome(requestView, TipoBancaEnum.BANCA_PRIVADA);
	}

}
