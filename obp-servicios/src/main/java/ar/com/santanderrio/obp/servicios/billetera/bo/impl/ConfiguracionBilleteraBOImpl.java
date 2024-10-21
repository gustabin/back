/*
 * 
 */
package ar.com.santanderrio.obp.servicios.billetera.bo.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.billetera.bo.ConfiguracionBilleteraBO;
import ar.com.santanderrio.obp.servicios.billetera.common.BilleteraConstants;
import ar.com.santanderrio.obp.servicios.billetera.dao.BilleteraDAO;
import ar.com.santanderrio.obp.servicios.billetera.dto.AdministrarMedioPagoDTO;
import ar.com.santanderrio.obp.servicios.billetera.dto.BilleteraDTO;
import ar.com.santanderrio.obp.servicios.billetera.dto.BilleteraInDTO;
import ar.com.santanderrio.obp.servicios.billetera.dto.MedioDePagoBilleteraDTO;
import ar.com.santanderrio.obp.servicios.billetera.web.util.BilleteraUtils;

/**
 * The Class ConfiguracionBilleteraBOImpl.
 *
 */
@Component("configuracionBilleteraBO")
public class ConfiguracionBilleteraBOImpl extends BilleteraBOImpl implements ConfiguracionBilleteraBO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ConfiguracionBilleteraBOImpl.class);

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * ar.com.santanderrio.obp.servicios.billetera.bo.ConfiguracionBilleteraBO#
	 * configurarBilletera(BilleteraInDTO)
	 */
	@Override
	public Respuesta<BilleteraDTO> configurarBilletera(BilleteraInDTO inDTO) {
		// Vincula medios de pago
		boolean impactTrjs = hayImpactTrjs(inDTO);
		Respuesta<AdministrarMedioPagoDTO> respuestaAdmMedioPago = null;
		if (impactTrjs) {
			respuestaAdmMedioPago = prcAdmMedioPago(inDTO, true);
			if (respuestaAdmMedioPago != null
					&& EstadoRespuesta.ERROR.equals(respuestaAdmMedioPago.getEstadoRespuesta())) {
				return respuestaFactory.crearRespuestaError(BilleteraDTO.class,
						respuestaAdmMedioPago.getItemsMensajeRespuesta());
			} else if (respuestaAdmMedioPago != null) {
				sesionBilletera.setAdministrarMedioPagoDto(respuestaAdmMedioPago.getRespuesta());
			}
		}

		// No se permite modificar la cuenta en la version productiva
		boolean impactCta = false;
		BilleteraDTO configuracionBilleteraDTO = crearBilleteraDTO(inDTO, null, respuestaAdmMedioPago, impactCta,
				impactTrjs);

		// Carga informacion para comprobante en sesionBilletera
		setSesionBilleteraUserData(Integer.parseInt(inDTO.getIdxCtaTp()), configuracionBilleteraDTO);

		// Invalida cache - Permite consultar nuevamente la cuenta en un nuevo
		// acceso
		sesionBilletera.setConsultaCuentaDto(null);

		return respuestaFactory.crearRespuestaOk(configuracionBilleteraDTO);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * ar.com.santanderrio.obp.servicios.billetera.bo.ConfiguracionBilleteraBO#
	 * generarComprobante()
	 */
	@Override
	public Respuesta<Reporte> generarComprobante() {
		Respuesta<Reporte> respuesta = new Respuesta<Reporte>();
		Reporte reporte = billeteraDAO.generarComprobante(BilleteraDAO.TipoReporte.MODIFICACION);
		respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
		respuesta.setRespuesta(reporte);
		return respuesta;
	}

	/**
	 * hayImpactTrjs.
	 *
	 * @param dto
	 *            the dto
	 * @return true si deben impactarse medios de pago, o false en caso
	 *         contrario
	 */
	private boolean hayImpactTrjs(BilleteraInDTO dto) {
		String principal = dto.getPrincipal();
		String[] vincular = dto.getVincular();
		LOGGER.info("Configuracion de medios de pago - vinculados: {}, favorito: {}", vincular, principal);
		List<MedioDePagoBilleteraDTO> ctasPrCs = sesionBilletera.getList(BilleteraConstants.LST_PR_CS);
		boolean impactTrjs = false;
		if (billeteraServiceHelper.cambioPrincipal(principal, ctasPrCs)) {
			impactTrjs = true;
		}
		if (!BilleteraUtils.getTarjetasModificadas(ctasPrCs).isEmpty()) {
			/*
			 * Existen medios de pago con novedad M por ajuste
			 */
			impactTrjs = true;
		}
		if (!sesionBilletera.getList(BilleteraConstants.LST_PR_NOCS).isEmpty()) {
			/*
			 * Existen medios de pago con novedad B por ajuste al no estar mas
			 * en el perfil de cliente
			 */
			impactTrjs = true;
		}
		if (billeteraServiceHelper.hayDesvinculados(vincular, ctasPrCs)) {
			/*
			 * Existen medios de pago desvinculados
			 */
			impactTrjs = true;
		}
		if (billeteraServiceHelper.hayNuevosVinculados(vincular, ctasPrCs)) {
			/*
			 * Existen medios de pago a vincular
			 */
			impactTrjs = true;
		}
		sesionBilletera.setImpactTrjs(impactTrjs);
		return impactTrjs;
	}

}