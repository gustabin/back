/*
 * 
 */
package ar.com.santanderrio.obp.servicios.billetera.bo.impl;

import javax.xml.ws.Holder;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.generated.webservices.billetera.consultacuenta2.AltaCuentaRequest;
import ar.com.santanderrio.obp.servicios.billetera.bo.AdhesionBilleteraBO;
import ar.com.santanderrio.obp.servicios.billetera.common.BilleteraConstants;
import ar.com.santanderrio.obp.servicios.billetera.dao.BilleteraDAO;
import ar.com.santanderrio.obp.servicios.billetera.dto.AdministrarCBUDTO;
import ar.com.santanderrio.obp.servicios.billetera.dto.AdministrarClaveBilleteraInDTO;
import ar.com.santanderrio.obp.servicios.billetera.dto.AdministrarMedioPagoDTO;
import ar.com.santanderrio.obp.servicios.billetera.dto.AltaCuentaDTO;
import ar.com.santanderrio.obp.servicios.billetera.dto.BilleteraDTO;
import ar.com.santanderrio.obp.servicios.billetera.dto.BilleteraInDTO;
import ar.com.santanderrio.obp.servicios.billetera.dto.CuentaBilleteraDTO;
import ar.com.santanderrio.obp.servicios.billetera.web.util.BilleteraUtils;
import ar.com.santanderrio.obp.servicios.clientes.entities.RegistroSesion;
import ar.com.santanderrio.obp.servicios.comun.dao.entities.DatosSolicitanteEntity;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;

/**
 * The Class AdhesionBilleteraBOImpl.
 *
 */
@Component("adhesionBilleteraBO")
public class AdhesionBilleteraBOImpl extends BilleteraBOImpl implements AdhesionBilleteraBO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(AdhesionBilleteraBOImpl.class);

	/*
	 * (non-Javadoc)
	 *
	 * @see ar.com.santanderrio.obp.servicios.billetera.bo.AdhesionBilleteraBO#
	 * administrarClave(ar.com.santanderrio.obp.servicios.billetera.dto.
	 * AdministrarClaveBilleteraInDTO, java.lang.String)
	 */
	@Override
	public Respuesta<Void> administrarClave(AdministrarClaveBilleteraInDTO dto, String mode) {
		return prcAdministrarClave(dto, mode);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.billetera.bo.AdhesionBilleteraBO#
	 * altaUsuario(
	 * ar.com.santanderrio.obp.servicios.billetera.dto.BilleteraInDTO,
	 * ar.com.santanderrio.obp.servicios.clientes.entities.RegistroSesion)
	 */
	@Override
	public Respuesta<BilleteraDTO> altaUsuario(BilleteraInDTO inDto, RegistroSesion registroSesion) {
		// Registra usuario en TP
		sesionBilletera.setErrCta(false);
		Respuesta<AltaCuentaDTO> respuestaAltaCuenta = prcAdhesion(inDto);
		if (EstadoRespuesta.ERROR.equals(respuestaAltaCuenta.getEstadoRespuesta())) {
			return prcErrorAdhesion(respuestaAltaCuenta, registroSesion);
		}

		// Adhesion de cuenta
		Integer cta = inDto.getCuentaAcreditacion();
		Respuesta<AdministrarCBUDTO> respuestaAdmCbu = null;
		if (!getCuentasAcreditacion().isEmpty() && cta != null) {
			respuestaAdmCbu = prcAdmCbu(cta.intValue(), BilleteraConstants.MODE_ADH);
		}

		// Vincula medios de pago
		Respuesta<AdministrarMedioPagoDTO> respuestaAdmMedioPago = prcAdmMedioPago(inDto, false);
		if (respuestaAdmMedioPago != null && EstadoRespuesta.ERROR.equals(respuestaAdmMedioPago.getEstadoRespuesta())) {
			return respuestaFactory.crearRespuestaError(BilleteraDTO.class,
					respuestaAdmMedioPago.getItemsMensajeRespuesta());
		}

		boolean impactCta = cta != null;
		boolean impactTrjs = inDto.getMediosDePagoActivos() != null && !inDto.getMediosDePagoActivos().isEmpty();
		BilleteraDTO altaUsuarioDTO = crearBilleteraDTO(inDto, respuestaAdmCbu, respuestaAdmMedioPago, impactCta,
				impactTrjs);

		// Carga informacion para comprobante en sesionBilletera
		setSesionBilleteraUserData(0, altaUsuarioDTO);

		// Invalida cache - Permite consultar nuevamente la cuenta en un nuevo
		// acceso
		sesionBilletera.setConsultaCuentaDto(null);

		return respuestaFactory.crearRespuestaOk(altaUsuarioDTO);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see ar.com.santanderrio.obp.servicios.billetera.bo.AdhesionBilleteraBO#
	 * generarComprobante()
	 */
	@Override
	public Respuesta<Reporte> generarComprobante() {
		Respuesta<Reporte> respuesta = new Respuesta<Reporte>();
		Reporte reporte = billeteraDAO.generarComprobante(BilleteraDAO.TipoReporte.ADHESION);
		respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
		respuesta.setRespuesta(reporte);
		return respuesta;
	}

	/**
	 * Alta cuenta.
	 *
	 * @param dto
	 *            the dto
	 * @param datosSolicitanteEntity
	 *            the datos solicitante
	 * @return the respuesta
	 */
	private Respuesta<AltaCuentaDTO> altaCuenta(BilleteraInDTO dto, DatosSolicitanteEntity datosSolicitanteEntity) {
		try {
			AltaCuentaRequest parameters = billeteraServiceHelper.fillParamsAltaCuenta(dto, datosSolicitanteEntity,
					sesionCliente.getCliente(), sesionBilletera.getEmail());
			Holder<String> status = new Holder<String>();
			Holder<String> idCuenta = new Holder<String>();
			billeteraDAO.altaCuenta(parameters, status, idCuenta);
			AltaCuentaDTO altaCuentaDTO = crearAltaCuentaDTO(status.value, idCuenta.value);
			return billeteraServiceHelper.prcErrorAltaCuenta(altaCuentaDTO, sesionBilletera);
		} catch (DAOException e) {
			LOGGER.error("Error al realizar alta de cuenta Billetera.", e);
		}
		return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_ADHESION_BILLETERA,
				CodigoMensajeConstantes.CODIGO_ERROR_GENERICO_IMPACTO_BILLETERA);
	}

	/**
	 * Crear alta cuenta DTO.
	 *
	 * @param status
	 *            the status
	 * @param idCuenta
	 *            the id cuenta
	 * @return the alta cuenta DTO
	 */
	private AltaCuentaDTO crearAltaCuentaDTO(String status, String idCuenta) {
		AltaCuentaDTO altaCuentaDTO = new AltaCuentaDTO();
		altaCuentaDTO.setIdCuenta(idCuenta);
		altaCuentaDTO.setStatus(status);
		return altaCuentaDTO;
	}

	/**
	 * Implementa el proceso de adhesion a Billetera.
	 *
	 * @param dto
	 *            the dto
	 * @return the respuesta
	 */
	private Respuesta<AltaCuentaDTO> prcAdhesion(BilleteraInDTO dto) {
		// Invoca a AltaCuenta
		return altaCuenta(dto, sesionBilletera.getDatosSolicitante());
	}

	/**
	 * Implementa el proceso de administracion de clave Billetera.
	 *
	 * @param dto
	 *            the dto
	 * @param mode
	 *            the mode
	 * @return the respuesta
	 */
	private Respuesta<Void> prcAdministrarClave(AdministrarClaveBilleteraInDTO dto, String mode) {
		try {
			int indexCtaTp = Integer.parseInt(dto.getIdxCtaTp());
			CuentaBilleteraDTO cuentaBilleteraDTO = sesionBilletera.getConsultaCuentaDto().getCuentas().get(indexCtaTp);
			String idCuenta = cuentaBilleteraDTO.getIdCuenta();
			String contrasenia = StringUtils.EMPTY;
			String respPregSeguridad = StringUtils.EMPTY;
			String tipoNovedad = StringUtils.EMPTY;
			if (BilleteraConstants.MODE_ADMCLAVES_LOGIN.equals(mode)) {
				tipoNovedad = BilleteraConstants.TIPO_NOVEDAD_VER;
				contrasenia = BilleteraUtils.hash(dto.getClaveTp());
			} else if (BilleteraConstants.MODE_ADMCLAVES_REC.equals(mode)) {
				tipoNovedad = BilleteraConstants.TIPO_NOVEDAD_REC;
				contrasenia = "x";
				respPregSeguridad = BilleteraUtils.hash(dto.getRespuestaSeguridad().toUpperCase());
			}
			String status = billeteraDAO.administrarClaveBancos(Integer.parseInt(idCuenta), idBanco, contrasenia,
					respPregSeguridad, tipoNovedad, Integer.parseInt(canal));
			return billeteraServiceHelper.prcErrorAdmClave(status);
		} catch (DAOException e) {
			LOGGER.error("Error al administrar clave Billetera.", e);
		}
		return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_GENERICO,
				CodigoMensajeConstantes.CODIGO_ERROR_GENERICO);
	}

	/**
	 * Prc error adhesion.
	 *
	 * @param respuestaAltaCuenta
	 *            the respuesta alta cuenta
	 * @param registroSesion
	 *            the registro sesion
	 * @return the respuesta
	 */
	private Respuesta<BilleteraDTO> prcErrorAdhesion(Respuesta<AltaCuentaDTO> respuestaAltaCuenta,
			RegistroSesion registroSesion) {
		String err = respuestaAltaCuenta.getItemsMensajeRespuesta().get(0).getExtra();
		if (BilleteraConstants.COD_ERR_1.equals(err)) {
			return respuestaFactory.crearRespuestaError(BilleteraDTO.class,
					respuestaAltaCuenta.getItemsMensajeRespuesta());
		} else if (BilleteraConstants.COD_ERR_2.equals(err)) {
			// Fija la marca de adhesion en HB_CLIENT_MASTER
			marcarAdhesion(sesionCliente.getCliente(), registroSesion);
			return respuestaFactory.crearRespuestaError(BilleteraDTO.class,
					respuestaAltaCuenta.getItemsMensajeRespuesta());
		} else {
			return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_ADHESION_BILLETERA,
					CodigoMensajeConstantes.CODIGO_ERROR_GENERICO_IMPACTO_BILLETERA);
		}
	}

}