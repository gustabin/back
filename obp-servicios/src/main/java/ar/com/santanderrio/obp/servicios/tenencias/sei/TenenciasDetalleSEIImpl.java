/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tenencias.sei;

import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.tenencias.helpers.TenenciasHelper;
import ar.com.santanderrio.obp.servicios.tenencias.manager.TenenciasDetalleManager;
import ar.com.santanderrio.obp.servicios.tenencias.view.TenenciasDetalleInView;
import ar.com.santanderrio.obp.servicios.tenencias.view.TenenciasDetalleView;

/**
 * The Class TenenciasDetalleSEIImpl.
 */
@Component("tenenciasDetalleSEI")
public class TenenciasDetalleSEIImpl implements TenenciasDetalleSEI {

	/** The tenencias detalle manager. */
	@Autowired
	private TenenciasDetalleManager tenenciasDetalleManager;

	 /** The codigos BP. */
    @Value("#{'${TENENCIAS.PERIODO}'.split('\\|')}")
    private List<String> aniosTenencias;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.tenencias.sei.TenenciasDetalleSEI#
	 * consultarDetalleTenencias(ar.com.santanderrio.obp.servicios.tenencias.
	 * view.TenenciasDetalleInView)
	 */
	@Override
	public Respuesta<TenenciasDetalleView> consultarDetalleTenencias(TenenciasDetalleInView viewRequest) {
		Respuesta<TenenciasDetalleView> temp = null;
		
		if (StringUtils.isEmpty(viewRequest.getAnioDesde()) && StringUtils.isEmpty(viewRequest.getAnioHasta())) {
			List<String> listaAnios = armarListaAnios(aniosTenencias);
			viewRequest.setAnioDesde(listaAnios.get(0));
			viewRequest.setAnioHasta(listaAnios.get(0));
		}

		Respuesta<TenenciasDetalleView> respuesta = tenenciasDetalleManager.consultarDetalleTenencias(viewRequest);

		if (viewRequest.getTipoDetalle() == 5) {
			String espeTipos[] = { "MON", "CEF", "BON", "SHS", "FON" };

			viewRequest.setEspeTipo(espeTipos[0]);
			temp = tenenciasDetalleManager.consultarDetalleTenencias(viewRequest);
			if (temp.getEstadoRespuesta().equals(EstadoRespuesta.ERROR)) {
				respuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);
				return respuesta;
			}
			respuesta.getRespuesta()
					.setResumenCuentaInversionesViewsMON(temp.getRespuesta().getResumenCuentaInversionesViews());

			viewRequest.setEspeTipo(espeTipos[1]);
			temp = tenenciasDetalleManager.consultarDetalleTenencias(viewRequest);
			if (temp.getEstadoRespuesta().equals(EstadoRespuesta.ERROR)) {
				respuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);
				return respuesta;
			}
			respuesta.getRespuesta()
					.setResumenCuentaInversionesViewsCEF(temp.getRespuesta().getResumenCuentaInversionesViews());

			viewRequest.setEspeTipo(espeTipos[2]);
			temp = tenenciasDetalleManager.consultarDetalleTenencias(viewRequest);
			if (temp.getEstadoRespuesta().equals(EstadoRespuesta.ERROR)) {
				respuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);
				return respuesta;
			}
			TenenciasHelper.formatearCamposQueTenganTodosCeros(temp.getRespuesta().getResumenCuentaInversionesViews());
			respuesta.getRespuesta().setResumenCuentaInversionesViewsBON(temp.getRespuesta().getResumenCuentaInversionesViews());

			viewRequest.setEspeTipo(espeTipos[3]);
			temp = tenenciasDetalleManager.consultarDetalleTenencias(viewRequest);
			if (temp.getEstadoRespuesta().equals(EstadoRespuesta.ERROR)) {
				respuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);
				return respuesta;
			}
			respuesta.getRespuesta()
					.setResumenCuentaInversionesViewsSHS(temp.getRespuesta().getResumenCuentaInversionesViews());

			viewRequest.setEspeTipo(espeTipos[4]);
			temp = tenenciasDetalleManager.consultarDetalleTenencias(viewRequest);
			if (temp.getEstadoRespuesta().equals(EstadoRespuesta.ERROR)) {
				respuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);
				return respuesta;
			}
			respuesta.getRespuesta()
					.setResumenCuentaInversionesViewsFON(temp.getRespuesta().getResumenCuentaInversionesViews());
		}

		return respuesta;
	}
	
	private List<String> armarListaAnios(List<String> aniosTenencias) {
		Collections.sort(aniosTenencias);
		Collections.reverse(aniosTenencias);
		return aniosTenencias.subList(0, 10);
	}
}
